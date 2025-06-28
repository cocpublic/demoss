package com.tencent.tinker.loader;

import android.content.Context;
import android.os.Process;
import android.util.Log; // For Log.getStackTraceString in initial log

import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tencent.tinker.loader.shareutil.ShareTinkerLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A custom {@link Thread.UncaughtExceptionHandler} for Tinker.
 * Its primary responsibilities are:
 * 1. To ensure the original uncaught exception handler is still called.
 * 2. To write information about the crash (process name and stack trace) to a
 *    designated crash file (usually "tinker_last_crash").
 * 3. To forcefully kill the current process after logging, to prevent the system
 *    from potentially showing multiple crash dialogs or leaving the app in an unstable state.
 *
 * Note: This class was translated from a decompiled and obfuscated class `m.java`.
 */
public class TinkerUncaughtHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "Tinker.UncaughtHandler";

    private final Context mContext;
    private final File mCrashLogFile;
    private final Thread.UncaughtExceptionHandler mOriginalHandler;

    public TinkerUncaughtHandler(Context context) {
        this.mContext = context.getApplicationContext(); // Use application context
        this.mOriginalHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.mCrashLogFile = SharePatchFileUtil.getPatchLastCrashFile(this.mContext);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ShareTinkerLog.e(TAG, "TinkerUncaughtHandler caught exception: " + Log.getStackTraceString(ex));

        // First, let the original handler do its job (e.g., report to Crashlytics, system default handling)
        if (mOriginalHandler != null && mOriginalHandler != this) { // Avoid recursive call if this is somehow set twice
            mOriginalHandler.uncaughtException(thread, ex);
        }

        // Then, try to write our own crash log and kill the process
        if (mCrashLogFile != null) {
            // Check if this handler is still the default one.
            // This is a safeguard against other handlers potentially being set after this one.
            Thread.UncaughtExceptionHandler currentDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (currentDefaultHandler == this || (currentDefaultHandler instanceof TinkerUncaughtHandler)) {
                File parentDir = mCrashLogFile.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    if (!parentDir.mkdirs()) {
                        ShareTinkerLog.e(TAG, "Failed to create directory for crash log: " + parentDir.getAbsolutePath());
                        // Fall through to kill process, as logging failed but original handler ran.
                        Process.killProcess(Process.myPid());
                        System.exit(10); // Exit with a specific code
                        return;
                    }
                }

                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(new FileWriter(mCrashLogFile, false)); // Overwrite existing
                    writer.println("Process: " + ShareTinkerInternals.getProcessName(mContext));
                    // Get the sanitized, root cause stack trace string
                    String stackTrace = ShareTinkerInternals.getExceptionCauseString(ex);
                    writer.println(stackTrace);
                    ShareTinkerLog.i(TAG, "Crash details written to: " + mCrashLogFile.getAbsolutePath());
                } catch (IOException e) {
                    ShareTinkerLog.e(TAG, "Failed to write crash log: " + Log.getStackTraceString(e));
                } finally {
                    SharePatchFileUtil.closeQuietly(writer);
                }
            } else {
                ShareTinkerLog.w(TAG, "TinkerUncaughtHandler is not the default handler anymore. Skipping crash log write.");
            }
        } else {
            ShareTinkerLog.w(TAG, "Crash log file is null. Cannot write crash details.");
        }

        // Finally, kill the process to ensure a clean exit after an uncaught exception.
        ShareTinkerLog.i(TAG, "Killing process " + Process.myPid() + " after uncaught exception.");
        Process.killProcess(Process.myPid());
        System.exit(10); // Ensure process termination
    }
}
