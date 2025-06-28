package com.tencent.tinker.loader.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;

/**
 * A utility class that dispatches Application lifecycle events and resource access calls
 * to a target Handler, which is typically an instance of
 * {@code com.tencent.tinker.entry.TinkerApplicationInlineFence}.
 *
 * This class converts direct method calls into Handler messages, allowing the
 * target Handler to process them, often by delegating to an ApplicationLike instance.
 *
 * Note: This class was translated from a decompiled and obfuscated class `a.java`.
 * The target Handler (mLifeCycleProxy in TinkerApplication) is expected to implement
 * ApplicationLifeCycle and handle these messages.
 */
public final class ShareApplicationLifeCycleDispatcher {

    // Message 'what' codes, corresponding to ApplicationLifeCycle methods
    private static final int MSG_ON_BASE_CONTEXT_ATTACHED = 1;
    private static final int MSG_ON_CREATE = 2;
    private static final int MSG_ON_CONFIGURATION_CHANGED = 3;
    private static final int MSG_ON_TRIM_MEMORY = 4;
    private static final int MSG_ON_LOW_MEMORY = 5;
    private static final int MSG_ON_TERMINATE = 6;
    private static final int MSG_GET_CLASS_LOADER = 7;
    private static final int MSG_GET_BASE_CONTEXT = 8;
    private static final int MSG_GET_ASSETS = 9;
    private static final int MSG_GET_RESOURCES = 10;
    private static final int MSG_GET_SYSTEM_SERVICE = 11;
    private static final int MSG_MZ_NIGHT_MODE_USE_OF = 12; // For Meizu night mode
    private static final int MSG_GET_THEME = 13;


    private ShareApplicationLifeCycleDispatcher() {
        // Utility class
    }

    static void dispatchOnBaseContextAttached(Handler lifeCycleProxyHandler, Context base) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_ON_BASE_CONTEXT_ATTACHED, base);
            lifeCycleProxyHandler.handleMessage(message);
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static void dispatchOnCreate(Handler lifeCycleProxyHandler) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_ON_CREATE);
            lifeCycleProxyHandler.handleMessage(message);
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static void dispatchOnConfigurationChanged(Handler lifeCycleProxyHandler, Configuration newConfig) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_ON_CONFIGURATION_CHANGED, newConfig);
            lifeCycleProxyHandler.handleMessage(message);
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static void dispatchOnTrimMemory(Handler lifeCycleProxyHandler, int level) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_ON_TRIM_MEMORY, level, 0); // Store level in arg1
            lifeCycleProxyHandler.handleMessage(message);
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static void dispatchOnLowMemory(Handler lifeCycleProxyHandler) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_ON_LOW_MEMORY);
            lifeCycleProxyHandler.handleMessage(message);
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static void dispatchOnTerminate(Handler lifeCycleProxyHandler) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_ON_TERMINATE);
            lifeCycleProxyHandler.handleMessage(message);
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static ClassLoader dispatchGetClassLoader(Handler lifeCycleProxyHandler, ClassLoader original) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_GET_CLASS_LOADER, original);
            lifeCycleProxyHandler.handleMessage(message);
            return (ClassLoader) message.obj; // Result is placed back in message.obj by the handler
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static Context dispatchGetBaseContext(Handler lifeCycleProxyHandler, Context original) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_GET_BASE_CONTEXT, original);
            lifeCycleProxyHandler.handleMessage(message);
            return (Context) message.obj;
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static AssetManager dispatchGetAssets(Handler lifeCycleProxyHandler, AssetManager original) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_GET_ASSETS, original);
            lifeCycleProxyHandler.handleMessage(message);
            return (AssetManager) message.obj;
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static Resources dispatchGetResources(Handler lifeCycleProxyHandler, Resources original) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_GET_RESOURCES, original);
            lifeCycleProxyHandler.handleMessage(message);
            return (Resources) message.obj;
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static Object dispatchGetSystemService(Handler lifeCycleProxyHandler, String name, Object originalService) {
        Message message = null;
        try {
            // Package name and original service as an Object array
            message = Message.obtain(lifeCycleProxyHandler, MSG_GET_SYSTEM_SERVICE, new Object[]{name, originalService});
            lifeCycleProxyHandler.handleMessage(message);
            return message.obj; // Result is placed back in message.obj
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static Resources.Theme dispatchGetTheme(Handler lifeCycleProxyHandler, Resources.Theme original) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_GET_THEME, original);
            lifeCycleProxyHandler.handleMessage(message);
            return (Resources.Theme) message.obj;
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }

    static int dispatchMzNightModeUseOf(Handler lifeCycleProxyHandler) {
        Message message = null;
        try {
            message = Message.obtain(lifeCycleProxyHandler, MSG_MZ_NIGHT_MODE_USE_OF);
            lifeCycleProxyHandler.handleMessage(message);
            // The result is expected to be an Integer in message.obj
            if (message.obj instanceof Integer) {
                return (Integer) message.obj;
            }
            return 1; // Default if not handled or wrong type
        } finally {
            if (message != null) {
                message.recycle();
            }
        }
    }
}
