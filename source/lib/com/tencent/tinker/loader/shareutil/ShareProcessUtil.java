package com.tencent.tinker.loader.shareutil;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;

/**
 * Utility class for process-related operations, primarily for determining
 * if the current process is the main application process.
 *
 * Note: This class was translated from a decompiled and obfuscated class `o.java`.
 */
public final class ShareProcessUtil {
    private static final String TAG = "Tinker.ProcessUtils";

    private static String sMainProcessNameCache = null;

    private ShareProcessUtil() {
        // Utility class
    }

    /**
     * Allows explicitly setting the cached main process name.
     * This might be used in scenarios where the automatic detection is insufficient
     * or needs to be overridden.
     *
     * @param newProcessName The process name to set as the main process name.
     */
    public static void setMainProcessName(String newProcessName) {
        ShareTinkerLog.i(TAG, "setMainProcessName oldProcessName=" + sMainProcessNameCache + ", newProcessName=" + newProcessName);
        sMainProcessNameCache = newProcessName;
    }

    /**
     * Checks if the given process name is the main process of the application.
     * It lazily initializes the main process name cache if not already set.
     *
     * @param context             The application context.
     * @param processNameToCompare The process name to compare against the main process name.
     * @return True if processNameToCompare matches the main process name, false otherwise.
     */
    public static boolean isProcessNameEqualToMain(Context context, String processNameToCompare) {
        if (TextUtils.isEmpty(sMainProcessNameCache)) {
            sMainProcessNameCache = extractMainProcessName(context);
        }
        return TextUtils.equals(sMainProcessNameCache, processNameToCompare);
    }

    /**
     * Checks if the current process is the main application process.
     * It lazily initializes the main process name cache if not already set.
     *
     * @param context The application context.
     * @return True if the current process is the main process, false otherwise.
     */
    public static boolean isMainProcess(Context context) {
        if (context == null) {
            return false;
        }
        if (TextUtils.isEmpty(sMainProcessNameCache)) {
            sMainProcessNameCache = extractMainProcessName(context);
        }

        String currentProcessName = ShareTinkerInternals.getProcessName(context);
        if (TextUtils.isEmpty(currentProcessName) && TextUtils.isEmpty(sMainProcessNameCache)) {
            // If both are empty (e.g. context is null for getProcessName, and main process name also couldn't be determined),
            // consider them "equal" in this edge case, or return false. Let's default to false for safety.
            return false;
        }
        // Corrected logic: return true if cached main process name is not empty AND it equals current process name.
        return !TextUtils.isEmpty(sMainProcessNameCache) && sMainProcessNameCache.equals(currentProcessName);
    }

    /**
     * Extracts the main process name from ApplicationInfo or defaults to package name.
     *
     * @param context The application context.
     * @return The determined main process name.
     */
    private static String extractMainProcessName(Context context) {
        if (context == null) {
            return ""; // Or null, depending on how ShareTinkerInternals.getProcessName handles null context
        }
        ApplicationInfo appInfo = context.getApplicationInfo();
        String processName = null;
        if (appInfo != null) {
            processName = appInfo.processName;
        }

        // If ApplicationInfo.processName is null or empty, default to package name
        if (TextUtils.isEmpty(processName)) {
            processName = context.getPackageName();
        }
        // It's possible that even package name could be null in very strange contexts, though unlikely.
        return processName == null ? "" : processName;
    }
}
