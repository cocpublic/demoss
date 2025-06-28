package com.tencent.tinker.loader;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log; // For initial logging if ShareTinkerLog is not ready

import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerLog;

import java.lang.reflect.Field;

/**
 * Blocks the application from processing certain ApplicationInfo changes at runtime
 * on Android O (API 26) and above, which can cause issues with loaded patches.
 * It achieves this by intercepting messages on the main thread's Handler (ActivityThread.mH)
 * and killing the process if the specific APPLICATION_INFO_CHANGED message is detected.
 *
 * Note: This class was translated from a decompiled and obfuscated class `b.java`.
 */
public final class AppInfoChangedBlocker {
    private static final String TAG = "Tinker.AppInfoChangedBlocker";

    private AppInfoChangedBlocker() {
        // Utility class
    }

    /**
     * Attempts to install the blocker. This should be called early in the application setup.
     * The blocker is only active on Android O (API 26) and above.
     *
     * @param application The application instance.
     * @return True if the blocker was successfully installed or not needed (below API 26),
     *         false if installation failed (but errors are generally ignored).
     */
    public static boolean tryStart(Application application) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) { // Android O = API 26
            ShareTinkerLog.i(TAG, "tryStart: SDK_INT is less than 26, blocker is not needed.");
            return true;
        }
        try {
            ShareTinkerLog.i(TAG, "tryStart called.");
            Handler activityThreadHandler = getActivityThreadHandler(application);
            if (activityThreadHandler != null) {
                installCallback(activityThreadHandler);
                ShareTinkerLog.i(TAG, "tryStart done.");
                return true;
            } else {
                ShareTinkerLog.w(TAG, "tryStart: Failed to get ActivityThread Handler. Blocker not installed.");
                return false;
            }
        } catch (Throwable t) {
            ShareTinkerLog.e(TAG, "AppInfoChangedBlocker start failed, simply ignore.", t);
            return false; // Indicate failure but don't crash the app
        }
    }

    /**
     * Retrieves the mH handler from the ActivityThread instance.
     */
    private static Handler getActivityThreadHandler(Context context) throws Exception {
        Object activityThread = ShareReflectUtil.getActivityThread(context, null);
        if (activityThread == null) {
            throw new IllegalStateException("Failed to get ActivityThread instance.");
        }
        Field mHField = ShareReflectUtil.findField(activityThread, "mH");
        return (Handler) mHField.get(activityThread);
    }

    /**
     * Installs a custom Handler.Callback to intercept messages.
     */
    private static void installCallback(Handler mainHandler) throws Exception {
        Field mCallbackField = ShareReflectUtil.findField(Handler.class, "mCallback");
        Handler.Callback originalCallback = (Handler.Callback) mCallbackField.get(mainHandler);

        if (originalCallback instanceof AppInfoChangedBlockerCallback) {
            ShareTinkerLog.w(TAG, "installCallback: Already intercepted by AppInfoChangedBlockerCallback. Skipping.");
        } else {
            // The Handler class (ActivityThread$H) is needed to reflectively get APPLICATION_INFO_CHANGED constant.
            AppInfoChangedBlockerCallback newCallback = new AppInfoChangedBlockerCallback(originalCallback, mainHandler.getClass());
            mCallbackField.set(mainHandler, newCallback);
            ShareTinkerLog.i(TAG, "installCallback: Successfully installed AppInfoChangedBlockerCallback.");
        }
    }

    /**
     * Custom Handler.Callback to intercept and handle the APPLICATION_INFO_CHANGED message.
     */
    private static class AppInfoChangedBlockerCallback implements Handler.Callback {
        private final int MSG_APPLICATION_INFO_CHANGED;
        private final Handler.Callback mOriginalCallback;

        AppInfoChangedBlockerCallback(Handler.Callback originalCallback, Class<? extends Handler> handlerClass) {
            this.mOriginalCallback = originalCallback;
            int appInfoChangedMsgCode;
            try {
                // ActivityThread.H.APPLICATION_INFO_CHANGED (usually 156)
                appInfoChangedMsgCode = ShareReflectUtil.getStaticIntField(handlerClass, "APPLICATION_INFO_CHANGED", -1);
                if (appInfoChangedMsgCode == -1) { // Fallback if reflection failed
                    ShareTinkerLog.w(TAG, "Failed to reflect APPLICATION_INFO_CHANGED from " + handlerClass + ", using default 156.");
                    appInfoChangedMsgCode = 156;
                }
            } catch (Throwable t) {
                ShareTinkerLog.w(TAG, "Failed to get APPLICATION_INFO_CHANGED value, using default 156.", t);
                appInfoChangedMsgCode = 156; // Default value if reflection fails
            }
            this.MSG_APPLICATION_INFO_CHANGED = appInfoChangedMsgCode;
            ShareTinkerLog.i(TAG, "AppInfoChangedBlockerCallback created, monitoring message code: " + this.MSG_APPLICATION_INFO_CHANGED);
        }

        @Override
        public boolean handleMessage(Message msg) {
            if (isApplicationInfoChangedMessageAndKill(msg)) {
                return true; // Message handled (by killing process)
            }
            if (mOriginalCallback != null) {
                // Delegate to the original callback if it exists and we didn't handle it.
                return mOriginalCallback.handleMessage(msg); // Corrected: return the result of original callback
            }
            return false; // No original callback and message not handled by us
        }

        private boolean isApplicationInfoChangedMessageAndKill(Message msg) {
            if (msg.what == MSG_APPLICATION_INFO_CHANGED) {
                ShareTinkerLog.w(TAG, "APPLICATION_INFO_CHANGED message received. Process will be killed to prevent potential crashes.");
                Process.killProcess(Process.myPid());
                System.exit(0); // Ensure exit
                return true;
            }
            return false;
        }
    }
}
