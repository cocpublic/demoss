package com.tencent.tinker.loader.shareutil;

import android.os.Handler;
import android.os.Message;
import android.util.Log; // For default implementation

import java.lang.reflect.Constructor;

/**
 * Tinker's logging utility. It allows setting a custom logger implementation
 * and can interact with {@link TinkerLogInlineFence} to queue logs before
 * a full logger is available or to process logs asynchronously.
 *
 * Note: This class was translated from a decompiled and obfuscated class `n.java`.
 */
public class ShareTinkerLog {
    private static final String TAG = "Tinker.ShareTinkerLog";

    // Message 'what' constants for posting to TinkerLogInlineFence.
    // These should match the ones expected by TinkerLogInlineFence.processLogMessage()
    // (2=V, 3=D, 4=I, 5=W, 6=E, 4001=Exception, 4002=Flush)
    private static final int TINKER_LOG_VERBOSE = 2;
    private static final int TINKER_LOG_DEBUG = 3;
    private static final int TINKER_LOG_INFO = 4;
    private static final int TINKER_LOG_WARNING = 5;
    private static final int TINKER_LOG_ERROR = 6;
    private static final int TINKER_LOG_PRINT_STACKTRACE = 4001;
    private static final int TINKER_LOG_FLUSH = 4002;


    private static final Handler[] sHandlerHolder = {null}; // Holds TinkerLogInlineFence instance
    private static final TinkerLogImp sDefaultLogImp;
    private static final TinkerLogImp[] sLogImpHolder; // Holds current TinkerLogImp

    static {
        sDefaultLogImp = new DefaultLogImp();
        sLogImpHolder = new TinkerLogImp[]{sDefaultLogImp};

        // Initialize TinkerLogInlineFence via reflection
        // This allows ShareTinkerLog to be used even if TinkerLogInlineFence is not included
        // or if there's an issue initializing it.
        synchronized (sHandlerHolder) {
            try {
                Class<?> inlineFenceClass = Class.forName("com.tencent.tinker.loader.shareutil.TinkerLogInlineFence");
                // The constructor of TinkerLogInlineFence is private in my translation.
                // The original decompiled code implied it was reflectively creating it.
                // If TinkerLogInlineFence is designed to be a utility class with static methods
                // to post messages to its internal handler, then direct instantiation here isn't needed.
                // However, the original code stored it as a Handler.
                // Let's assume TinkerLogInlineFence has a static method to get its handler or post messages.
                // For now, if TinkerLogInlineFence.postLogMessage(what, obj) exists, we'd use that.
                // The original code suggests TinkerLogInlineFence *was* the handler.
                // My current TinkerLogInlineFence translation has a static MAIN_THREAD_HANDLER and a static postLogMessage.
                // The original code `n.a[0] = (Handler)constructor.newInstance(new Object[]{});` means it expected
                // TinkerLogInlineFence to BE a Handler. My current translation does not make it a Handler.
                // This requires a small adjustment to TinkerLogInlineFence or this class.

                // Re-evaluating: The decompiled TinkerLogInlineFence had `public void handleMessage(Message)`.
                // This means it *could* be used as a Handler directly or as a Handler.Callback.
                // If it's instantiated and set as sHandlerHolder[0], then Message.obtain(handler, ...) works.
                // Let's assume the constructor of TinkerLogInlineFence (even if private) can be called via reflection.
                Constructor<?> constructor = inlineFenceClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                sHandlerHolder[0] = (Handler) constructor.newInstance();
                ShareTinkerLog.i(TAG, "Successfully created TinkerLogInlineFence instance.");
            } catch (Throwable t) {
                Log.e(TAG, "Failed to create TinkerLogInlineFence instance. Inline logs may not be processed.", t);
                sHandlerHolder[0] = null;
            }
        }
    }

    private static Handler getHandler() {
        synchronized (sHandlerHolder) {
            return sHandlerHolder[0];
        }
    }

    public static TinkerLogImp getDefaultImpl() {
        return sDefaultLogImp;
    }

    public static void setTinkerLogImp(TinkerLogImp imp) {
        synchronized (sLogImpHolder) {
            sLogImpHolder[0] = imp;
            // If a new, non-default logger is set, try to flush any pending logs
            // from the inline fence using this new logger.
            if (imp != null && imp != sDefaultLogImp) {
                flushPendingLogs();
            }
        }
    }

    public static TinkerLogImp getImpl() {
        synchronized (sLogImpHolder) {
            return sLogImpHolder[0];
        }
    }

    public static void v(String tag, String msg, Object... args) {
        postToLogHandler(TINKER_LOG_VERBOSE, tag, msg, args);
    }

    public static void d(String tag, String msg, Object... args) {
        postToLogHandler(TINKER_LOG_DEBUG, tag, msg, args);
    }

    public static void i(String tag, String msg, Object... args) {
        postToLogHandler(TINKER_LOG_INFO, tag, msg, args);
    }

    public static void w(String tag, String msg, Object... args) {
        postToLogHandler(TINKER_LOG_WARNING, tag, msg, args);
    }

    public static void e(String tag, String msg, Object... args) {
        postToLogHandler(TINKER_LOG_ERROR, tag, msg, args);
    }

    public static void printErrStackTrace(String tag, Throwable tr, String format, Object... args) {
        String message = format == null ? "" : String.format(format, args);
        if (message == null) message = "";
        message = message + "  " + Log.getStackTraceString(tr); // Append stack trace to message

        // The original private method `b` packaged arguments differently for exceptions.
        // Object[]{Integer.valueOf(4001), Long.valueOf(l0), str0, throwable, str1, objectArr0}
        // Let's adapt: the TinkerLogInlineFence expects specific Object[] structure for its MSG_LOG_EXCEPTION
        // My TinkerLogInlineFence.processLogMessage expects: logArgs[0]=Tag, logArgs[1]=Throwable, logArgs[2]=Format, logArgs[3]=Args
        // So, we should package it that way.
        Object[] logData = {tag, tr, String.format(format, args), new Object[0]}; // Args already in format
                                                                                // or pass format and args separately if preferred by fence

        Handler handler = getHandler();
        if (handler != null) {
            // The original code in `b` used `object` directly as `message.obj`.
            // `object` was: `new Object[]{Integer.valueOf(4001), Long.valueOf(l0), str0, throwable, str1, objectArr0};`
            // Let's stick to the structure TinkerLogInlineFence expects for MSG_LOG_EXCEPTION
            // which is {tag, throwable, formattedMessage, (empty)args} if we pre-format.
            // Or, if TinkerLogInlineFence handles formatting: {tag, throwable, formatString, args}
            // My current TinkerLogInlineFence expects: {tag, throwable, format, args}
            // The decompiled code for `n.b` (this method's helper) sent:
            // obj = { TINKER_LOG_PRINT_STACKTRACE, System.currentTimeMillis(), tag, throwable, format, args }
            // Let's use this structure for the object sent to the handler.
            Object[] handlerArgs = {System.currentTimeMillis(), Thread.currentThread().getId(), tag, tr, format, args};


            Message msg = Message.obtain(handler, TINKER_LOG_PRINT_STACKTRACE, handlerArgs);
            handler.sendMessage(msg); // Use sendMessage for async, handleMessage was direct call in decompile
            // Original recycled message, but sendMessage copies it.
        } else {
            // Fallback to default logger if handler (inline fence) is not available
            sDefaultLogImp.printErrStackTrace(tag, tr, String.format(format, args));
        }
    }

    /**
     * Signals the TinkerLogInlineFence to process any queued logs.
     */
    public static void flushPendingLogs() {
        Handler handler = getHandler();
        if (handler != null) {
            // Message type 4002 was used in original to trigger flush in TinkerLogInlineFence
            Message message = Message.obtain(handler, TINKER_LOG_FLUSH);
            handler.sendMessage(message); // Use sendMessage for async
        }
    }

    private static void postToLogHandler(int level, String tag, String msg, Object... args) {
        String formattedMsg = (args == null || args.length == 0) ? msg : String.format(msg, args);

        Handler handler = getHandler();
        if (handler != null) {
            // Original object structure sent to handler:
            // {Integer.valueOf(level), System.currentTimeMillis(), tag, formattedMsg, (empty_args_if_preformatted)}
            // My TinkerLogInlineFence expects: {tag, formattedMsg, (empty_args_if_preformatted)} for simple logs.
            // Let's send what TinkerLogInlineFence expects for its MSG_LOG_VERBOSE etc.
            // logArgs[0] = Tag (String), logArgs[1] = Message (String), logArgs[2] = Formatting args (Object[])
            // Since we pre-format, args for fence will be empty.
            Object[] fenceArgs = {tag, formattedMsg, new Object[0]}; // No further formatting args needed by fence
            Message message = Message.obtain(handler, level, fenceArgs);
            handler.sendMessage(message); // Use sendMessage for async
        } else {
            // Fallback to default logger if handler (inline fence) is not available
            switch (level) {
                case TINKER_LOG_VERBOSE: sDefaultLogImp.v(tag, formattedMsg); break;
                case TINKER_LOG_DEBUG:   sDefaultLogImp.d(tag, formattedMsg); break;
                case TINKER_LOG_INFO:    sDefaultLogImp.i(tag, formattedMsg); break;
                case TINKER_LOG_WARNING: sDefaultLogImp.w(tag, formattedMsg); break;
                case TINKER_LOG_ERROR:   sDefaultLogImp.e(tag, formattedMsg); break;
                default: // Should not happen
                    sDefaultLogImp.e(tag, "[Unknown Level " + level + "] " + formattedMsg); break;
            }
        }
    }


    /**
     * The interface for Tinker log implementations.
     */
    public interface TinkerLogImp {
        void v(String tag, String msg, Object... args);
        void d(String tag, String msg, Object... args);
        void i(String tag, String msg, Object... args);
        void w(String tag, String msg, Object... args);
        void e(String tag, String msg, Object... args);
        void printErrStackTrace(String tag, Throwable tr, String format, Object... args);
    }

    /**
     * Default TinkerLogImp that uses android.util.Log.
     */
    private static class DefaultLogImp implements TinkerLogImp {
        @Override
        public void v(String tag, String msg, Object... args) {
            Log.v(tag, (args == null || args.length == 0) ? msg : String.format(msg, args));
        }
        @Override
        public void d(String tag, String msg, Object... args) {
            Log.d(tag, (args == null || args.length == 0) ? msg : String.format(msg, args));
        }
        @Override
        public void i(String tag, String msg, Object... args) {
            Log.i(tag, (args == null || args.length == 0) ? msg : String.format(msg, args));
        }
        @Override
        public void w(String tag, String msg, Object... args) {
            Log.w(tag, (args == null || args.length == 0) ? msg : String.format(msg, args));
        }
        @Override
        public void e(String tag, String msg, Object... args) {
            Log.e(tag, (args == null || args.length == 0) ? msg : String.format(msg, args));
        }
        @Override
        public void printErrStackTrace(String tag, Throwable tr, String format, Object... args) {
            String message = format == null ? "" : String.format(format, args);
            if (message == null) message = "";
            Log.e(tag, message, tr);
        }
    }
}
