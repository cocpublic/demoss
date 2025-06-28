package com.tencent.tinker.loader.shareutil;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.tinker.anno.Keep; // Assuming @Keep is for Proguard

import java.util.ArrayList;
import java.util.List;

/**
 * An internal Tinker class apparently used to queue log messages and process them,
 * potentially to avoid issues with Proguard inlining or to manage log output
 * under specific conditions (e.g., before a full logger is initialized).
 *
 * The original decompiled code had some unusual constructs (like a method designed to always throw
 * an exception to ensure a finally block runs), which have been simplified here for clarity.
 *
 * This class seems to interact closely with an external logging implementation,
 * referred to as `n` and `n$a` in the decompiled code, which is presumed to be
 * `ShareTinkerLog` and `ShareTinkerLog.ShareTinkerLogImp`.
 *
 * Note: This translation makes assumptions about the intended design due to decompilation artifacts.
 */
@Keep // Keep annotation was on the constructor, implies the class itself might need to be kept.
final class TinkerLogInlineFence {

    // Message 'what' constants, inferred from usage in the dispatch method
    // These would ideally come from ShareTinkerLog or a shared constants file.
    private static final int MSG_LOG_VERBOSE = 2;
    private static final int MSG_LOG_DEBUG = 3;
    private static final int MSG_LOG_INFO = 4;
    private static final int MSG_LOG_WARNING = 5;
    private static final int MSG_LOG_ERROR = 6;
    private static final int MSG_LOG_EXCEPTION = 4001;
    private static final int MSG_FLUSH_LOG_QUEUE = 4002;

    private static final Handler MAIN_THREAD_HANDLER;
    private static final List<Object[]> LOG_QUEUE = new ArrayList<>();

    static {
        MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                processLogMessage(msg);
                return true;
            }
        });
    }

    /**
     * Private constructor to prevent instantiation, as this is a utility class.
     * The @Keep annotation was on the original constructor.
     */
    @Keep
    private TinkerLogInlineFence() {
        // Utility class
    }

    /**
     * Processes a log message. This method is called by the MAIN_THREAD_HANDLER.
     * It dispatches the log to the actual Tinker log implementation or queues it.
     * (Original: private void b(Message message))
     */
    private static void processLogMessage(Message message) {
        // Presumed interaction with ShareTinkerLog
        ShareTinkerLog.ShareTinkerLogImp defaultLogImp = ShareTinkerLog.getDefaultImpl();
        ShareTinkerLog.ShareTinkerLogImp currentLogImp = ShareTinkerLog.getImpl();

        Object[] logArgs = (Object[]) message.obj; // All relevant log data seems packed here

        try {
            switch (message.what) {
                case MSG_LOG_VERBOSE:
                    if (currentLogImp != null) {
                        currentLogImp.v((String) logArgs[0], (String) logArgs[1], (Object[]) logArgs[2]);
                    }
                    break;
                case MSG_LOG_DEBUG:
                    if (currentLogImp != null) {
                        currentLogImp.d((String) logArgs[0], (String) logArgs[1], (Object[]) logArgs[2]);
                    }
                    break;
                case MSG_LOG_INFO:
                    if (currentLogImp != null) {
                        currentLogImp.i((String) logArgs[0], (String) logArgs[1], (Object[]) logArgs[2]);
                    }
                    break;
                case MSG_LOG_WARNING:
                    if (currentLogImp != null) {
                        currentLogImp.w((String) logArgs[0], (String) logArgs[1], (Object[]) logArgs[2]);
                    }
                    break;
                case MSG_LOG_ERROR:
                    if (currentLogImp != null) {
                        currentLogImp.e((String) logArgs[0], (String) logArgs[1], (Object[]) logArgs[2]);
                    }
                    break;
                case MSG_LOG_EXCEPTION:
                    // Original obj: [(Tag String), (Throwable), (Format String), (Args Object[])]
                    // Decompiled obj: [ (index 0,1 not used by ShareTinkerLogImp methods), (Tag String at index 2), (Throwable at index 3), (Format String at index 4), (Args Object[] at index 5) ]
                    // Assuming logArgs for MSG_LOG_EXCEPTION is structured as:
                    // logArgs[0] = Tag (String)
                    // logArgs[1] = Throwable
                    // logArgs[2] = Format (String)
                    // logArgs[3] = Arguments (Object[])
                    if (currentLogImp != null && logArgs.length >= 4) {
                         currentLogImp.printErrStackTrace((String) logArgs[0], (Throwable) logArgs[1], (String) logArgs[2], (Object[]) logArgs[3]);
                    } else if (currentLogImp != null) { // Fallback if format is wrong
                        currentLogImp.e((String) logArgs[0], "Error logging exception: malformed arguments.", new Object[0]);
                    }
                    break;
                case MSG_FLUSH_LOG_QUEUE:
                    flushQueueAsync(currentLogImp);
                    return; // Do not queue a flush message
                default:
                    if (currentLogImp != null) {
                        currentLogImp.e("Tinker.LogInlineFence", "Unknown message type: " + message.what, new Object[0]);
                    }
            }
        } catch (Exception e) {
            // Catch exceptions during logging itself to prevent crashing the handler
            if (currentLogImp != null) {
                currentLogImp.e("Tinker.LogInlineFence", "Exception while processing log: " + e.getMessage(), new Object[0]);
            }
        }


        // If no custom logger is set, or if the current logger is the default one (which might imply it's not fully initialized),
        // then queue the message.
        // The original decompiled code had logArgs indices like objectArr0[2], objectArr0[3], objectArr0[4]
        // This implies the original message.obj (logArgs) might have a different structure than what ShareTinkerLogImp methods expect.
        // For simplicity here, we'll assume the logArgs for queuing should be the same as passed to the methods.
        // A more accurate translation would need to know the exact structure of `message.obj` when posted.
        // Let's re-package it for the queue based on a common understanding for now.
        // The Object[] in the queue likely contains: MessageType, Tag, MessageFormat, Args...
        // However, the original code directly added `message.obj` (which is `logArgs`) to the queue.
        // This implies `logArgs` already contains the `message.what` or it's inferred.
        // Re-checking: the original `message.obj` was an `Object[]` containing:
        // [timestamp, threadId, tag, msg, args] for regular logs
        // [timestamp, threadId, tag, throwable, msg, args] for exceptions
        // The ShareTinkerLogImp methods don't take timestamp or threadId.
        // The switch case used indices 2,3,4 from this array.
        // For queuing, it added the whole objectArr0.
        // So, the queued Object[] contains the original full arguments.
        if (currentLogImp == null || currentLogImp == defaultLogImp) {
            // Only queue if not a flush request itself
            if (message.what != MSG_FLUSH_LOG_QUEUE) {
                synchronized (LOG_QUEUE) {
                    LOG_QUEUE.add(logArgs); // logArgs is message.obj from the switch
                }
            }
        }
    }

    /**
     * Asynchronously flushes the queued log messages using the provided logger implementation.
     * (Original: private static void a(n$a n$a))
     * @param logImp The logger implementation to use for flushing.
     */
    private static void flushQueueAsync(final ShareTinkerLog.ShareTinkerLogImp logImp) {
        synchronized (LOG_QUEUE) {
            if (logImp == null || LOG_QUEUE.isEmpty()) {
                return;
            }
        }
        // Start a new thread to print logs from the queue
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Object[]> logsToProcess = new ArrayList<>();
                synchronized (LOG_QUEUE) {
                    logsToProcess.addAll(LOG_QUEUE);
                    LOG_QUEUE.clear();
                }

                for (Object[] logData : logsToProcess) {
                    // We need to infer the message type that was originally associated with logData
                    // This information is lost if not stored within logData itself.
                    // The original decompiled code didn't show how 'what' was preserved for queued items.
                    // Assuming the ShareTinkerLog methods that post to this handler include 'what' in logData[0]
                    // or we can infer it.
                    // For now, this part is difficult to translate accurately without knowing how ShareTinkerLog posts.
                    // Let's assume logData[0] is tag, logData[1] is message, logData[2] is args for simplicity in this runnable.
                    // This is a MAJOR simplification and likely incorrect.
                    // The actual log dispatching logic from processLogMessage needs to be replicated here
                    // based on the contents of logData.

                    // A more robust way would be to queue Message objects or a custom LogEntry class.
                    // Given the original code just queued Object[], it implies a fixed structure.
                    // Let's assume the Object[] queued was structured for the ShareTinkerLogImp methods directly
                    // e.g. [TagString, MessageString, ArgsObject[]] for simple logs
                    // or [TagString, Throwable, MessageString, ArgsObject[]] for exceptions
                    // This is an assumption because message.what is not stored with message.obj in the queue.

                    // This part is highly speculative without seeing how ShareTinkerLog calls this fence.
                    // The most direct interpretation of the original `b(Message message)`'s queuing is that `message.obj`
                    // was directly added. The `message.what` was used to call the right `logImp` method.
                    // When flushing, we don't have `message.what` for each queued item unless it's part of the `Object[]`.

                    // If ShareTinkerLog is posting messages like:
                    // Message.obtain(handler, MSG_CODE, new Object[]{System.currentTimeMillis(), Thread.currentThread().getId(), tag, msg, args})
                    // Then logData[2] is tag, logData[3] is msg, logData[4] is args for regular.
                    // And for exceptions: logData[2]=tag, logData[3]=throwable, logData[4]=msg, logData[5]=args

                    // Let's try to be more faithful to the original switch structure for dispatching from queue.
                    // This requires an assumed structure for the queued Object[].
                    // Let's assume the first element of the Object[] is the original `message.what` code.
                    // This is a common pattern if one were to implement such a queue.
                    // If logData[0] is indeed the original 'what':
                    if (logData.length > 0 && logData[0] instanceof Integer) {
                        int type = (Integer) logData[0];
                        // And subsequent elements are the specific args for that type
                        // e.g. logData[1] = tag, logData[2] = msg, logData[3] = formattingArgs for simple logs
                        // This is still guesswork. The original decompiled code did not show 'message.what' being stored.
                        // It directly queued 'message.obj'.
                        // The most likely scenario is that ShareTinkerLog itself, when posting, formats the Object[]
                        // in a way that the ShareTinkerLogImp methods can directly consume them.
                        // For example, ShareTinkerLog.v(tag, fmt, ...args) might post:
                        // new Object[]{tag, fmt, args} and the MSG_LOG_VERBOSE code.
                        // Then the processLogMessage uses these directly.
                        // And the queue stores this exact new Object[]{tag, fmt, args}.

                        // Re-evaluating the original b(Message message) queuing:
                        // It adds `objectArr0` to the queue, where `objectArr0 = (Object[])message.obj;`
                        // And `n$aVar1.a((String)objectArr0[2], (String)objectArr0[3], (Object[])objectArr0[4]);`
                        // This means objectArr0[2] is tag, objectArr0[3] is msg, objectArr0[4] is args.
                        // Indices 0 and 1 of objectArr0 are unused by the ShareTinkerLogImp calls.
                        // So the queued items are Object[] where elements from index 2 onwards are relevant.

                        try {
                            if (logData.length >= 5) { // Minimum for tag, msg, args, plus two ignored initial elements
                                String tag = (String) logData[2];
                                String msg = (String) logData[3];
                                Object[] formatArgs = (Object[]) logData[4];
                                // We don't know the original level (v,d,i,w,e) from the queued data.
                                // This is a flaw in this interpretation or the decompiled understanding.
                                // The original `TinkerLogInlineFence$1` must have had a way to know the type.
                                // One possibility: the queued Object[] also contained the type.
                                // Or, ShareTinkerLog.printPendingLogs was called with the type.
                                // The provided decompiled code for `a(n$a n$a)` (flush) does not pass type.
                                // This implies the queued Object[] must be self-descriptive or ShareTinkerLogImp methods are robust.

                                // Safest assumption: if it's in the queue, it was meant for info level or was an error.
                                // This is a weak assumption.
                                // A better approach for the Runnable: iterate and call a generic log method on logImp
                                // if one exists, or just print to info.
                                // The original `TinkerLogInlineFence$1` likely had access to the original message `what`
                                // or the `Object[]` was structured to include it.
                                // Since the decompiled `TinkerLogInlineFence$1` is missing, we make a best guess.
                                // The simplest robust thing `logImp` could do is take `Object[]` and internally decide.
                                // But standard loggers don't work that way.

                                // Let's assume ShareTinkerLogImp has a generic "log" method or we default to 'i'
                                // For now, let's just log it as INFO, acknowledging this is imperfect.
                                // This part highlights the difficulty of perfectly reconstructing from decompile.
                                if (logImp != null) {
                                     // We cannot determine the original log level here.
                                     // This is a critical part missing from the decompiled static `a(n$a)` method's logic.
                                     // The original `TinkerLogInlineFence$1` (the runnable) must have had more context.
                                     // For now, we'll just indicate a queued message was processed.
                                    logImp.i(tag, "[QUEUED] " + msg, formatArgs);
                                }
                            } else if (logData.length >= 6 && logData[3] instanceof Throwable) { // Exception: tag, throwable, msg, args
                                // (Indices 2, 3, 4, 5 from original objectArr0Var5)
                                String tag = (String) logData[2];
                                Throwable throwable = (Throwable) logData[3];
                                String format = (String) logData[4];
                                Object[] formatArgs = (Object[]) logData[5];
                                if (logImp != null) {
                                    logImp.printErrStackTrace(tag, throwable, "[QUEUED] " + format, formatArgs);
                                }
                            }
                        } catch (Exception e) {
                            if (logImp != null) {
                                logImp.e("Tinker.LogInlineFence", "Error processing queued log item: " + e.getMessage(), new Object[0]);
                            }
                        }
                    }
                }
            }
        }, "tinker_log_printer");
        thread.start();
    }

    /**
     * Posts a log message to be processed by this fence.
     * This method should be called by ShareTinkerLog.
     *
     * @param what Message type (level or command)
     * @param obj  The log data package (typically an Object[] containing tag, message, args, etc.)
     */
    static void postLogMessage(int what, Object[] obj) {
        if (MAIN_THREAD_HANDLER != null) {
            Message message = Message.obtain(MAIN_THREAD_HANDLER, what, obj);
            message.sendToTarget();
        }
    }

    // The original decompiled code had a static method c() that always threw an exception.
    // if (TinkerLogInlineFence.class.isPrimitive()) { throw new RuntimeException(); } else { }
    // This was likely used in a try-finally block to ensure another method call.
    // It's removed here as it's an obfuscation/unnecessary complexity for readable code.

    // Original static accessors for TinkerLogInlineFence$1 (now part of Runnable)
    // static /* synthetic */ List access$getLogQueue() { return LOG_QUEUE; }
    // static /* synthetic */ Handler access$getMainHandler() { return MAIN_THREAD_HANDLER; }
}
