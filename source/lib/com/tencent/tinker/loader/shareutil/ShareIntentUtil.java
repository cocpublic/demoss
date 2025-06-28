package com.tencent.tinker.loader.shareutil;

import android.content.Intent;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Utility class for safely handling Intent extras, particularly for communication
 * within the Tinker patch process (e.g., between services and activities).
 *
 * Note: This class was translated from a decompiled and obfuscated class `g.java`.
 */
public final class ShareIntentUtil {

    public static final String INTENT_RETURN_CODE = "intent_return_code";
    public static final String INTENT_PATCH_COST_TIME = "intent_patch_cost_time";
    public static final String INTENT_PATCH_EXCEPTION = "intent_patch_exception";
    public static final String INTENT_PATCH_INTERPRET_EXCEPTION = "intent_patch_interpret_exception";
    public static final String INTENT_PATCH_DEXES_PATH = "intent_patch_dexes_path";
    public static final String INTENT_PATCH_LIBS_PATH = "intent_patch_libs_path";
    public static final String INTENT_PATCH_PACKAGE_CONFIG = "intent_patch_package_config";

    // A default value for return code if not found, from original decompiled code (55536).
    // This value seems arbitrary and might be specific to an internal Tinker convention.
    // Standard Tinker return codes are usually small integers (e.g., ShareConstants.ERROR_LOAD_OK).
    // For now, preserving the original default.
    private static final int DEFAULT_RETURN_CODE = -10000; // Original was 55536, but Tinker usually uses negative for errors. Let's use a more common "not found" indicator.

    private ShareIntentUtil() {
        // Utility class, not meant to be instantiated.
    }

    public static void setIntentReturnCode(Intent intent, int returnCode) {
        if (intent != null) {
            intent.putExtra(INTENT_RETURN_CODE, returnCode);
        }
    }

    public static int getIntentReturnCode(Intent intent) {
        return getIntExtraSafe(intent, INTENT_RETURN_CODE, DEFAULT_RETURN_CODE);
    }

    public static void setIntentPatchCostTime(Intent intent, long costTime) {
        if (intent != null) {
            intent.putExtra(INTENT_PATCH_COST_TIME, costTime);
        }
    }

    public static long getIntentPatchCostTime(Intent intent) {
        if (intent == null) {
            return 0L;
        }
        return intent.getLongExtra(INTENT_PATCH_COST_TIME, 0L);
    }

    public static Throwable getIntentPatchException(Intent intent) {
        Serializable serializable = getSerializableExtraSafe(intent, INTENT_PATCH_EXCEPTION);
        if (serializable instanceof Throwable) {
            return (Throwable) serializable;
        }
        return null;
    }

    public static Throwable getIntentPatchInterpretException(Intent intent) {
        Serializable serializable = getSerializableExtraSafe(intent, INTENT_PATCH_INTERPRET_EXCEPTION);
        if (serializable instanceof Throwable) {
            return (Throwable) serializable;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, String> getIntentPatchDexPaths(Intent intent) {
        Serializable serializable = getSerializableExtraSafe(intent, INTENT_PATCH_DEXES_PATH);
        if (serializable instanceof HashMap) {
            return (HashMap<String, String>) serializable;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, String> getIntentPatchLibsPaths(Intent intent) {
        Serializable serializable = getSerializableExtraSafe(intent, INTENT_PATCH_LIBS_PATH);
        if (serializable instanceof HashMap) {
            return (HashMap<String, String>) serializable;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, String> getIntentPatchPackageConfig(Intent intent) {
        Serializable serializable = getSerializableExtraSafe(intent, INTENT_PATCH_PACKAGE_CONFIG);
        if (serializable instanceof HashMap) {
            return (HashMap<String, String>) serializable;
        }
        return null;
    }

    public static String getStringExtraSafe(Intent intent, String key) {
        if (intent == null) {
            return null;
        }
        try {
            return intent.getStringExtra(key);
        } catch (Exception e) {
            ShareTinkerLog.d("ShareIntentUtil", "getStringExtraSafe exception:" + e.getMessage());
            return null;
        }
    }

    public static Serializable getSerializableExtraSafe(Intent intent, String key) {
        if (intent == null) {
            return null;
        }
        try {
            return intent.getSerializableExtra(key);
        } catch (Exception e) {
            // Using ShareTinkerLog.e for potentially more severe ClassNotFoundException or similar issues
            ShareTinkerLog.e("ShareIntentUtil", "getSerializableExtraSafe exception:" + e.getMessage());
            return null;
        }
    }

    public static int getIntExtraSafe(Intent intent, String key, int defaultValue) {
        if (intent == null) {
            return defaultValue;
        }
        try {
            return intent.getIntExtra(key, defaultValue);
        } catch (Exception e) {
            ShareTinkerLog.d("ShareIntentUtil", "getIntExtraSafe exception:" + e.getMessage());
            return defaultValue; // Return defaultValue on exception, correcting original bug
        }
    }

    public static boolean getBooleanExtraSafe(Intent intent, String key, boolean defaultValue) {
        if (intent == null) {
            return defaultValue;
        }
        try {
            return intent.getBooleanExtra(key, defaultValue);
        } catch (Exception e) {
            ShareTinkerLog.d("ShareIntentUtil", "getBooleanExtraSafe exception:" + e.getMessage());
            return defaultValue; // Return defaultValue on exception, correcting original bug
        }
    }

    /**
     * Sets the ClassLoader that will be used when unmarshalling any
     * Parcelable values from the extras of this Intent.
     *
     * @param intent The intent to modify.
     * @param loader The ClassLoader to use, or null to use the default.
     */
    public static void setIntentClassLoader(Intent intent, ClassLoader loader) {
        if (intent != null) {
            try {
                intent.setExtrasClassLoader(loader);
            } catch (Throwable t) {
                // In some environments or older Android versions, this might not be available
                // or could throw errors. Log it but don't crash.
                ShareTinkerLog.w("ShareIntentUtil", "setExtrasClassLoader exception:" + t.getMessage());
            }
        }
    }
}
