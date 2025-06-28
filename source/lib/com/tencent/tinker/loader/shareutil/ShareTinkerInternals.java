package com.tencent.tinker.loader.shareutil;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle; // For meta-data
import android.os.Process;
import android.text.TextUtils; // For TextUtils.isEmpty

import com.tencent.tinker.loader.TinkerRuntimeException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable; // For use in closeQuietly, though SharePatchFileUtil has it
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains internal utility methods used by Tinker, often dealing with system properties,
 * runtime environment checks (ART/Dalvik, API level, instruction set), process information,
 * and Tinker-specific configurations like TinkerID and safe mode.
 *
 * Note: This class was translated from a decompiled and obfuscated class `m.java`.
 */
public final class ShareTinkerInternals {
    private static final String TAG = "Tinker.TinkerInternals";

    private static final boolean IS_ART; // Original: a
    private static final boolean IS_VM_JIT_ENABLED; // Original: b
    private static Boolean isArkHotRunningCache = null; // Original: c
    private static Boolean isHuaweiSystemCache = null; // Original: d (was actually isArkHotRunningCache, let's assume 'd' was for something else or unused for isArkHot)
                                                // Decompiled 'c()' method assigned to 'd', which is isArkHotRunningCache
                                                // The other boolean 'd' in decompiled code was for isHuaweiSystem, not used by isArkHotRunning
                                                // For now, let's keep isArkHotRunningCache as 'c' and see if 'd' emerges elsewhere.

    private static final String[] processNameCache = {null}; // Original: e, used for caching process name
    private static String manifestTinkerIdCache = null; // Original: f
    private static String currentInstructionSetCache = null; // Original: g

    public static final String SHAREREF_PATH = "tinker_share_config";
    public static final String TINKER_ENABLE_CONFIG_PREFIX = "tinker_enable_"; // Prefix for SharedPreferences key
    public static final String TINKER_SAFE_MODE_COUNT_REC_PREFIX = "safemode_count_rec_";
    public static final String TINKER_SAFE_MODE_KEY = "safe_mode_count_1.9.14.20(RFix)"; // Key inside the count record file

    // Tinker patch type flags
    public static final int TYPE_DEX = 0x01;
    public static final int TYPE_SO = 0x02;
    public static final int TYPE_RES = 0x04;
    // public static final int TYPE_ARKHOT = 0x08; // Decompiled had d(int) for this, original Tinker might not.

    // Codes used in ShareDexDiffPatchInfo.dexMode and SharePatchInfo.oatDir
    public static final String DEX_MODE_JAR = "jar";
    public static final String DEX_MODE_RAW = "raw";
    public static final String OAT_DIR_ODEX = "odex"; // Common value for SharePatchInfo.oatDir
    public static final String OAT_DIR_INTERPRET = "interpret";


    static {
        IS_ART = isVmArt(System.getProperty("java.vm.version"));
        IS_VM_JIT_ENABLED = isVmJitInternal();
    }

    private ShareTinkerInternals() {
        // Utility class
    }

    /**
     * Checks if the current runtime is ART.
     * It also considers Android N preview where ART might be default but JIT is not fully ready.
     * Original: public static boolean a()
     */
    public static boolean isVmArt() {
        return IS_ART || Build.VERSION.SDK_INT >= 21; // Android L (API 21) and above are ART
    }

    /**
     * Checks if the VM has JIT enabled. This was particularly relevant for Android N preview.
     * Original: public static boolean b()
     */
    public static boolean isVmJit() {
        // Original logic: m.b && Build$VERSION.SDK_INT < 24
        // m.b was isVmJitInternal()
        return IS_VM_JIT_ENABLED && Build.VERSION.SDK_INT < 24; // True if JIT enabled AND pre-N final
    }

    /**
     * Checks if running in Huawei Ark Runtime.
     * Original: public static boolean c()
     */
    public static boolean isArkHotRunning() {
        if (isArkHotRunningCache != null) {
            return isArkHotRunningCache;
        }
        try {
            // The decompiled code used getParent().loadClass(...), which is unusual.
            // Standard reflection would be Class.forName directly if the class is in the classpath.
            // ClassLoader.getSystemClassLoader().getParent() is usually the bootclassloader.
            // This implies ArkApplicationInfo might be in bootclasspath on Ark systems.
            Class<?> arkInfoClass = Class.forName("com.huawei.ark.app.ArkApplicationInfo");
            Method isRunningMethod = arkInfoClass.getDeclaredMethod("isRunningInArk");
            isRunningMethod.setAccessible(true);
            isArkHotRunningCache = (Boolean) isRunningMethod.invoke(null);
        } catch (ClassNotFoundException e) {
            ShareTinkerLog.d(TAG, "isArkHotRunning: ArkApplicationInfo class not found.");
            isArkHotRunningCache = false;
        } catch (NoSuchMethodException e) {
            ShareTinkerLog.d(TAG, "isArkHotRunning: isRunningInArk method not found.");
            isArkHotRunningCache = false;
        } catch (Exception e) { // Catch broader exceptions as original did (Security, IllegalAccess, InvocationTarget, IllegalArgument)
            ShareTinkerLog.e(TAG, "isArkHotRunning: Failed to check Ark runtime", e);
            isArkHotRunningCache = false;
        }
        return isArkHotRunningCache;
    }


    /**
     * Checks if ODEX mode is supported (typically Android O/API 26 and above).
     * Original: public static boolean d()
     */
    public static boolean isOdexModeSupport() {
        // Original: Build$VERSION.SDK_INT > 25
        // Official Tinker source uses >= 26 for this check in some places for ODEX.
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    /**
     * Gets the current instruction set of the runtime (e.g., "arm", "arm64", "x86").
     * Tries reflection on dalvik.system.VMRuntime first, then falls back to Build.CPU_ABI.
     * Original: public static String e()
     */
    public static String getCurrentInstructionSet() {
        if (currentInstructionSetCache != null) {
            return currentInstructionSetCache;
        }
        try {
            Class<?> vmRuntimeClass = Class.forName("dalvik.system.VMRuntime");
            Method getInstructionSetMethod = vmRuntimeClass.getDeclaredMethod("getCurrentInstructionSet");
            getInstructionSetMethod.setAccessible(true);
            currentInstructionSetCache = (String) getInstructionSetMethod.invoke(null);
        } catch (Exception e) { // Catching general Exception as original did (Throwable)
            String cpuAbi = Build.CPU_ABI;
            switch (cpuAbi.toLowerCase()) {
                case "armeabi":
                    currentInstructionSetCache = "arm";
                    break;
                case "armeabi-v7a":
                    currentInstructionSetCache = "arm"; // Often grouped with arm
                    break;
                case "arm64-v8a":
                    currentInstructionSetCache = "arm64";
                    break;
                case "x86":
                    currentInstructionSetCache = "x86";
                    break;
                case "x86_64":
                    currentInstructionSetCache = "x86_64";
                    break;
                case "mips":
                    currentInstructionSetCache = "mips";
                    break;
                case "mips64":
                    currentInstructionSetCache = "mips64";
                    break;
                default:
                    throw new TinkerRuntimeException("Unsupported CPU ABI: " + cpuAbi);
            }
        }
        ShareTinkerLog.i(TAG, "getCurrentInstructionSet: " + currentInstructionSetCache);
        return currentInstructionSetCache;
    }

    /**
     * Checks if the current instruction set is 32-bit.
     * Original: public static boolean f()
     */
    public static boolean is32BitAbi() {
        String instructionSet = getCurrentInstructionSet();
        return "arm".equals(instructionSet) || "x86".equals(instructionSet) || "mips".equals(instructionSet);
    }

    /**
     * Checks if the system has been OTA updated by comparing fingerprints.
     *
     * @param oldFingerprint The fingerprint stored in the patch info.
     * @return True if fingerprints differ (suggesting OTA), false otherwise or if inputs are invalid.
     * Original: public static boolean a(String str0)
     */
    public static boolean isSystemOTA(String oldFingerprint) {
        String currentFingerprint = Build.FINGERPRINT;
        if (isNullOrNil(oldFingerprint) || isNullOrNil(currentFingerprint)) {
            ShareTinkerLog.d(TAG, "isSystemOTA: fingerprint is empty." +
                                  " old: " + oldFingerprint + ", current: " + currentFingerprint);
            return false; // Cannot determine if one is empty
        }
        if (oldFingerprint.equals(currentFingerprint)) {
            ShareTinkerLog.d(TAG, "isSystemOTA: same fingerprint: " + currentFingerprint);
            return false;
        }
        ShareTinkerLog.i(TAG, "isSystemOTA: fingerprint not equal." +
                              " old: " + oldFingerprint + ", current: " + currentFingerprint);
        return true;
    }

    /**
     * Potentially modifies a ShareDexDiffPatchInfo path if it's a "test.dex".
     * The purpose of "test.dex" is unclear from this snippet alone.
     * Original: public static d a(d d_info, int i0_dexNum) -> ShareDexDiffPatchInfo a(ShareDexDiffPatchInfo, int)
     */
    public static ShareDexDiffPatchInfo adjustTestDexPath(ShareDexDiffPatchInfo dexInfo, int dexNumber) {
        if (dexInfo == null || dexInfo.path == null) return dexInfo; // Or null, original returned null if input was null

        if (dexInfo.path.startsWith("test.dex")) { // Magic name for special handling
            String newPath;
            if (dexNumber == 1 && !dexInfo.path.equals(ShareConstants.DEX_BASENAME)) { // dexNumber 1 might be primary
                newPath = ShareConstants.DEX_BASENAME; // "classes.dex"
            } else if (dexNumber > 1) {
                newPath = ShareConstants.DEX_PREFIX + dexNumber + ShareConstants.DEX_SUFFIX; // "classesN.dex"
            } else {
                 // If dexNumber is 0 or 1 but path is already "classes.dex", or other unhandled cases
                newPath = dexInfo.path; // No change
            }
            // Reconstruct with new path, keeping other fields.
            return new ShareDexDiffPatchInfo(newPath, dexInfo.patchMd5, dexInfo.dexMd5, dexInfo.oldDexCrc,
                                             dexInfo.newDexCrc, dexInfo.destMd5InDvm, dexInfo.destMd5InArt,
                                             dexInfo.dexMode);
        }
        return dexInfo; // Return original if not "test.dex"
    }


    /**
     * Checks if a string is null or empty.
     * Original: public static boolean b(String str0)
     */
    public static boolean isNullOrNil(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * Performs package check for Tinker.
     * Original: public static int a(Context context, int i0, File file, l l_secCheck)
     * TODO: Depends on ShareSecurityCheck (l) being translated.
     */
    public static int checkTinkerPackage(Context context, int currentPatchType, File patchFile, ShareSecurityCheck securityCheck) {
        int pkgCheckCode = checkPackageAndTinkerID(context, patchFile, securityCheck);
        if (pkgCheckCode == ShareConstants.ERROR_PACKAGE_OK) {
            return checkPackageContents(securityCheck, currentPatchType);
        }
        return pkgCheckCode;
    }

    /**
     * Checks patch file existence and TINKER_ID match.
     * Original: private static int a(Context context, File file, l l_secCheck)
     * TODO: Depends on ShareSecurityCheck (l) being translated.
     */
    private static int checkPackageAndTinkerID(Context context, File patchFile, ShareSecurityCheck securityCheck) {
        if (!securityCheck.verifyPatchMetaSignature(patchFile)) { // Original: l.a(file)
            return ShareConstants.ERROR_PACKAGE_PATCH_SIGNATURE_FAIL; // -1
        }

        String manifestTinkerID = getManifestTinkerID(context);
        if (manifestTinkerID == null) {
            return ShareConstants.ERROR_PACKAGE_GET_META_FAIL; // -5, but original was 251
        }

        HashMap<String, String> packageProperties = securityCheck.getPackagePropertiesIfVerified(); // Original: l.b()
        if (packageProperties == null) {
            return ShareConstants.ERROR_PACKAGE_NO_META; // -8, but original was 254
        }

        String patchTinkerID = packageProperties.get(ShareConstants.TINKER_ID);
        if (patchTinkerID == null) {
            return ShareConstants.ERROR_PACKAGE_NO_TINKER_ID_IN_META; // -6, but original was 250
        }

        if (!manifestTinkerID.equals(patchTinkerID)) {
            ShareTinkerLog.e(TAG, "Tinker منع ID mismatch! Base pack ID: " + manifestTinkerID + ", Patch ID: " + patchTinkerID);
            return ShareConstants.ERROR_PACKAGE_TINKER_ID_NOT_EQUAL; // -7, but original was 249
        }
        return ShareConstants.ERROR_PACKAGE_OK; // 0
    }

    /**
     * Checks if required meta files (dex_meta, so_meta, res_meta) exist based on currentPatchType.
     * Original: private static int a(l l_secCheck, int i0_currentPatchType)
     * TODO: Depends on ShareSecurityCheck (l) being translated.
     */
    private static int checkPackageContents(ShareSecurityCheck securityCheck, int currentPatchType) {
        if (isPatchTypeDisabled(currentPatchType)) { // Original: m.g(i0) -> isTypeDisabled(int)
            return ShareConstants.ERROR_PACKAGE_OK; // 0, if all types disabled, it's "ok" not to find metas
        }

        HashMap<String, String> metaContents = securityCheck.getMetaContentMap(); // Original: l.a()

        boolean dexEnabled = isDexPatch(currentPatchType);
        if (dexEnabled && !metaContents.containsKey(ShareConstants.DEX_META_FILE_NAME)) {
            ShareTinkerLog.e(TAG, "Dex meta file not found in meta map.");
            return ShareConstants.ERROR_PACKAGE_MISSING_DEX_META; // -3, original was 247
        }

        boolean soEnabled = isSoPatch(currentPatchType);
        if (soEnabled && !metaContents.containsKey(ShareConstants.SO_META_FILE_NAME)) {
            ShareTinkerLog.e(TAG, "SO meta file not found in meta map.");
            return ShareConstants.ERROR_PACKAGE_MISSING_SO_META; // -4, original was 247
        }

        boolean resEnabled = isResPatch(currentPatchType);
        if (resEnabled && !metaContents.containsKey(ShareConstants.RES_META_FILE_NAME)) {
            ShareTinkerLog.e(TAG, "Resource meta file not found in meta map.");
            return ShareConstants.ERROR_PACKAGE_MISSING_RES_META; // -9, original was 247
        }
        // Note: Original logic returned 247 for any missing meta if its type was enabled.
        // Here, specific error codes are better. If all enabled types have their metas, it's OK.
        return ShareConstants.ERROR_PACKAGE_OK; // 0
    }


    /**
     * Gets TINKER_ID from AndroidManifest.xml meta-data.
     * Original: public static String e(Context context)
     */
    public static String getManifestTinkerID(Context context) {
        if (manifestTinkerIdCache != null) {
            return manifestTinkerIdCache;
        }
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo == null || appInfo.metaData == null) {
                ShareTinkerLog.e(TAG, "getManifestTinkerID: ApplicationInfo or metaData is null.");
                return null;
            }
            Object tinkerIdObj = appInfo.metaData.get(ShareConstants.TINKER_ID_KEY); // Use constant
            if (tinkerIdObj != null) {
                manifestTinkerIdCache = String.valueOf(tinkerIdObj);
            } else {
                manifestTinkerIdCache = null; // Explicitly null if not found
                ShareTinkerLog.d(TAG, "getManifestTinkerID: TINKER_ID not found in meta-data.");
            }
        } catch (Exception e) {
            ShareTinkerLog.e(TAG, "getManifestTinkerID exception: " + e.getMessage());
            return null;
        }
        return manifestTinkerIdCache;
    }

    // Type flag checkers
    public static boolean isDexPatch(int typeFlags) { return (typeFlags & TYPE_DEX) != 0; }
    public static boolean isSoPatch(int typeFlags) { return (typeFlags & TYPE_SO) != 0; }
    public static boolean isResPatch(int typeFlags) { return (typeFlags & TYPE_RES) != 0; }
    // public static boolean isArkHotPatch(int typeFlags) { return (typeFlags & TYPE_ARKHOT) != 0; } // If TYPE_ARKHOT is defined

    /**
     * Converts patch type code to string.
     * Original: public static String e(int i0_typeCode)
     */
    public static String getTypeString(int typeCode) {
        switch (typeCode) {
            case ShareConstants.TYPE_PATCH_FILE: return "patch_file"; // 1
            case ShareConstants.TYPE_PATCH_INFO: return "patch_info"; // 2
            case ShareConstants.TYPE_DEX: return "dex";            // 3
            case ShareConstants.TYPE_DEX_OPTIMIZED: return "dex_opt";  // 4
            case ShareConstants.TYPE_LIBRARY: return "lib";          // 5
            case ShareConstants.TYPE_RESOURCE: return "resource";     // 6
            default: return "unknown";
        }
    }

    /**
     * Sets the Tinker enabled flag for the current process to false in SharedPreferences.
     * Original: public static void f(Context context)
     */
    public static void setTinkerDisable(Context context) {
        if (context == null) return;
        SharedPreferences sp = context.getSharedPreferences(SHAREREF_PATH, Context.MODE_MULTI_PROCESS);
        sp.edit().putBoolean(getProcessTinkerEnableKey(context), false).apply();
    }

    /**
     * Checks if Tinker is enabled for the current process from SharedPreferences. Defaults to true.
     * Original: public static boolean g(Context context)
     */
    public static boolean isTinkerEnabled(Context context) {
        if (context == null) {
            return false; // Or true, depending on desired default for null context
        }
        SharedPreferences sp = context.getSharedPreferences(SHAREREF_PATH, Context.MODE_MULTI_PROCESS);
        return sp.getBoolean(getProcessTinkerEnableKey(context), true); // Default true
    }

    /**
     * Generates the SharedPreferences key for storing Tinker enabled status for the current process.
     * Original: private static String a(Context context)
     */
    private static String getProcessTinkerEnableKey(Context context) {
        String tinkerId = getManifestTinkerID(context);
        if (isNullOrNil(tinkerId)) {
            tinkerId = "@@"; // Default if no ID found, as per original
        }
        // The "(RFix)" part in the original key suggests this code might be from a modified Tinker version.
        return TINKER_ENABLE_CONFIG_PREFIX + ShareConstants.TINKER_VERSION + "(RFix)_" + tinkerId;
    }

    /**
     * Reads the safe mode retry count from a record file.
     * Original: public static int h(Context context)
     */
    public static int getSafeModeCount(Context context) {
        String processName = getProcessName(context); // Used to form record file name
        String recordFileName = TINKER_SAFE_MODE_COUNT_REC_PREFIX + processName;
        File recordFile = new File(SharePatchFileUtil.getPatchDirectory(context), recordFileName);

        DataInputStream dis = null;
        try {
            if (!recordFile.exists()) {
                ShareTinkerLog.d(TAG, "getSafeModeCount: Record file not found: " + recordFile.getPath());
                return 0;
            }
            dis = new DataInputStream(new FileInputStream(recordFile));
            String keyInFile = dis.readUTF();
            if (!TINKER_SAFE_MODE_KEY.equals(keyInFile)) {
                ShareTinkerLog.e(TAG, "getSafeModeCount: Key mismatch. Expected: " + TINKER_SAFE_MODE_KEY + ", Found: " + keyInFile);
                return 0; // Treat as corrupted or invalid
            }
            int count = dis.readInt();
            ShareTinkerLog.i(TAG, "getSafeModeCount: count = " + count);
            return count;
        } catch (IOException e) {
            ShareTinkerLog.e(TAG, "getSafeModeCount: Failed to read record file " + recordFile.getPath(), e);
            return 0;
        } finally {
            SharePatchFileUtil.closeQuietly(dis);
        }
    }

    /**
     * Writes the safe mode retry count to a record file.
     * Original: public static void a(Context context, int i0_count)
     */
    public static void setSafeModeCount(Context context, int count) {
        String processName = getProcessName(context);
        String recordFileName = TINKER_SAFE_MODE_COUNT_REC_PREFIX + processName;
        File patchDir = SharePatchFileUtil.getPatchDirectory(context);
        if (patchDir == null) {
            ShareTinkerLog.e(TAG, "setSafeModeCount: Patch directory is null.");
            return;
        }
        File recordFile = new File(patchDir, recordFileName);

        if (!recordFile.getParentFile().exists()) {
            recordFile.getParentFile().mkdirs();
        }
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(recordFile, false)); // Overwrite
            dos.writeUTF(TINKER_SAFE_MODE_KEY);
            dos.writeInt(count);
            ShareTinkerLog.i(TAG, "setSafeModeCount: count = " + count + " to file " + recordFile.getPath());
        } catch (IOException e) {
            ShareTinkerLog.e(TAG, "setSafeModeCount: Failed to write record file " + recordFile.getPath(), e);
        } finally {
            SharePatchFileUtil.closeQuietly(dos);
        }
    }

    /**
     * Checks if a patch type is disabled (all types disabled).
     * Original: public static boolean f(int i0_patchTypeFlags) - returned true if i0 != 0
     * This seems to be inverted or misnamed. A type is "disabled" if its bit is NOT set.
     * This method in original code checks if ANY type is enabled.
     * Let's rename to reflect that: isAnyPatchTypeEnabled
     */
    public static boolean isAnyPatchTypeEnabled(int patchTypeFlags) {
        return patchTypeFlags != 0;
    }

    /**
     * Checks if all primary patch types (Dex, SO, Res) are enabled.
     * Original: public static boolean g(int i0_patchTypeFlags) - returned true if i0 == 15 (0b1111)
     * Assuming 1=DEX, 2=SO, 4=RES. 1+2+4 = 7. If 8 (ARKHOT) is also primary, then 15.
     * Let's assume for now it means DEX, SO, and RES.
     */
    public static boolean isAllPrimaryPatchTypesEnabled(int patchTypeFlags) {
        boolean dex = isDexPatch(patchTypeFlags);
        boolean so = isSoPatch(patchTypeFlags);
        boolean res = isResPatch(patchTypeFlags);
        return dex && so && res; // Or check against a bitmask like (TYPE_DEX | TYPE_SO | TYPE_RES)
                                 // Original used `i0 == 15`. If 15 is 0b1111, it implies 4 types.
                                 // Let's stick to the 15 check for now if it implies a 4th type bit.
                                 // It's possible TYPE_ARKHOT (0x08) was included.
                                 // So: (TYPE_DEX | TYPE_SO | TYPE_RES | TYPE_ARKHOT) == 0xF == 15
                                 // This needs TYPE_ARKHOT to be defined as 0x08.
                                 // For now, let's keep the literal 15.
        return patchTypeFlags == (TYPE_DEX | TYPE_SO | TYPE_RES | 0x08); // Assuming 0x08 is the 4th type
    }

    /**
     * Checks if the current process is the main process.
     * Delegates to ShareProcessUtil.
     * Original: public static boolean i(Context context) -> o.a(context)
     */
    public static boolean isMainProcess(Context context) {
        return ShareProcessUtil.isMainProcess(context); // Assuming ShareProcessUtil.isMainProcess
    }

    /**
     * Checks if the current process is the patch process (name ends with ":patch").
     * Original: public static boolean j(Context context)
     */
    public static boolean isPatchProcess(Context context) {
        // Original had a static Boolean cache `c` for this.
        // For simplicity and to avoid issues with context changes, compute each time or use a robust cache.
        String processName = getProcessName(context);
        return processName != null && processName.endsWith(ShareConstants.PATCH_PROCESS_NAME_SUFFIX); // Use constant
    }

    /**
     * Gets the effective OAT directory ("odex" or "interpret") based on current settings.
     * Original: public static String a(Context context, String str0_oatDirFromPatchInfo)
     */
    public static StringgetEffectiveOatDir(Context context, String oatDirFromPatchInfo) {
        // Original logic: if (str0.equals("changing")) { str0 = m.i(context) ? "interpet" : "odex"; }
        // m.i(context) -> isMainProcess(context)
        // This means if oatDirFromPatchInfo is "changing", decide based on whether it's the main process.
        // Main process might use odex, other processes (like patch process) might use interpret.
        if (ShareConstants.CHANING_OAT_DIR_SYMBOL.equals(oatDirFromPatchInfo)) { // Use Constant
            return isMainProcess(context) ? OAT_DIR_ODEX : OAT_DIR_INTERPRET;
        }
        return oatDirFromPatchInfo;
    }

    /**
     * Kills all other processes of the current application, except those allowed by ShareProcessUtil.
     * Original: public static void k(Context context)
     */
    @SuppressLint("MissingPermission") // Suppress if GET_TASKS or KILL_BACKGROUND_PROCESSES is needed and checked elsewhere
    public static void killAllOtherProcess(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return;
        }
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
        if (runningProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.uid == Process.myUid() && processInfo.pid != Process.myPid()) {
                    // Check if this process is allowed to run (e.g. main process, specific services)
                    if (!ShareProcessUtil.isProcessAllowedToRun(context, processInfo.processName)) { // Assuming ShareProcessUtil has this
                        ShareTinkerLog.w(TAG, "Killing process: " + processInfo.processName + " (PID: " + processInfo.pid + ")");
                        Process.killProcess(processInfo.pid);
                    }
                }
            }
        }
    }

    /**
     * Gets the current process name. Result is cached.
     * Original: public static String l(Context context)
     */
    public static String getProcessName(Context context) {
        if (processNameCache[0] == null) {
            synchronized (processNameCache) {
                if (processNameCache[0] == null) {
                    processNameCache[0] = getProcessNameInternal(context);
                }
            }
        }
        return processNameCache[0] != null ? processNameCache[0] : ""; // Return empty string if null, as per original
    }

    /**
     * Internal method to determine the current process name using various strategies.
     * Original: private static String b(Context context)
     */
    @SuppressLint("NewApi") // For Application.getProcessName()
    private static String getProcessNameInternal(Context context) {
        // 1. Try Application.getProcessName() (API 28+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = Application.getProcessName();
            if (!isNullOrNil(processName)) {
                return processName;
            }
        }

        // 2. Try ActivityThread.currentProcessName() via reflection (API 18+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            try {
                Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
                Method currentProcessNameMethod = ShareReflectUtil.findMethod(activityThreadClass, "currentProcessName");
                String processName = (String) currentProcessNameMethod.invoke(null);
                if (!isNullOrNil(processName)) {
                    return processName;
                }
            } catch (Exception e) { // ClassNotFound, NoSuchMethod, IllegalAccess, InvocationTarget
                ShareTinkerLog.d(TAG, "getProcessNameInternal: ActivityThread reflection failed.", e);
            }
        }

        // 3. Try reading /proc/self/cmdline
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream("/proc/self/cmdline");
            reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.US_ASCII));
            String cmdline = reader.readLine();
            if (cmdline != null) {
                cmdline = cmdline.trim();
                if (!cmdline.isEmpty()) {
                    return cmdline;
                }
            }
        } catch (IOException e) {
            ShareTinkerLog.d(TAG, "getProcessNameInternal: Failed to read /proc/self/cmdline.", e);
        } finally {
            SharePatchFileUtil.closeQuietly(reader);
            SharePatchFileUtil.closeQuietly(fis);
        }

        // 4. Fallback to ActivityManager
        if (context != null) {
            try {
                int pid = Process.myPid();
                ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                if (am != null) {
                    List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
                    if (runningApps != null) {
                        for (ActivityManager.RunningAppProcessInfo appProcess : runningApps) {
                            if (appProcess.pid == pid) {
                                return appProcess.processName;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                ShareTinkerLog.d(TAG, "getProcessNameInternal: ActivityManager fallback failed.", e);
            }
        }
        return null; // Could not determine
    }


    /**
     * Checks if the VM version string indicates ART.
     * Original: private static boolean d(String str0_vmVersion)
     * @param vmVersion System.getProperty("java.vm.version")
     * @return true if ART, false otherwise.
     */
    private static boolean isVmArt(String vmVersion) {
        if (vmVersion != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(vmVersion);
            if (matcher.matches()) {
                try {
                    int major = Integer.parseInt(matcher.group(1));
                    int minor = Integer.parseInt(matcher.group(2));
                    // ART was introduced in 2.0.0 (Lollipop preview), official in 2.1.0 (Lollipop)
                    return major > 2 || (major == 2 && minor >= 1);
                } catch (NumberFormatException ignored) {
                    // Should not happen if regex matches
                }
            }
        }
        return false; // Default to false if version string is weird or too old
    }

    /**
     * Checks if JIT is enabled based on system properties.
     * Original: private static boolean g()
     */
    private static boolean isVmJitInternal() {
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method getMethod = systemPropertiesClass.getDeclaredMethod("get", String.class);
            String useJit = (String) getMethod.invoke(null, "dalvik.vm.usejit");
            String useJitProfiles = (String) getMethod.invoke(null, "dalvik.vm.usejitprofiles");
            // If both are null/empty OR useJit is "true"
            return (isNullOrNil(useJit) && isNullOrNil(useJitProfiles)) || "true".equals(useJit);
        } catch (Throwable t) {
            ShareTinkerLog.d(TAG, "isVmJitInternal exception: " + t);
        }
        return false;
    }

    /**
     * Checks if current API level is >= specified API level.
     * Handles preview SDKs if isTargetSDK is true.
     * Original: public static boolean a(int i0_apiLevel, boolean bool0_isTarget)
     */
    public static boolean isAboveApiLevel(int apiLevel, boolean isTargetSDKBehavior) {
        if (isTargetSDKBehavior && Build.VERSION.SDK_INT >= 23) { // Android M, when PREVIEW_SDK_INT was introduced
            // If targeting a preview SDK, Build.VERSION.SDK_INT might be base level,
            // and PREVIEW_SDK_INT > 0.
            // If apiLevel is for a future final release, and current is a preview for it:
            // e.g. apiLevel = 23 (M final), current is SDK_INT=22, PREVIEW_SDK_INT for M > 0.
            // This means current IS NOT >= apiLevel yet.
            // If apiLevel = 22 (L final), current is SDK_INT=22, PREVIEW_SDK_INT for M > 0.
            // This means current IS >= apiLevel.
            if (Build.VERSION.SDK_INT < apiLevel) { // Base SDK is less
                // If current is a preview for apiLevel (or higher), then it's "effectively" >= apiLevel for target behavior
                // This part of logic in original was: if (SDK_INT == apiLevel - 1 && PREVIEW_SDK_INT > 0) return false; else return true;
                // Which means, if current is exactly one less AND it's a preview, it's NOT considered >= apiLevel yet.
                // This seems to be about checking if we've *passed* the preview stage for that level.
                return !(Build.VERSION.SDK_INT == apiLevel - 1 && Build.VERSION.PREVIEW_SDK_INT > 0);
            }
            return true; // Base SDK is already >= apiLevel
        }
        return Build.VERSION.SDK_INT >= apiLevel;
    }

    /**
     * Checks if current API level is <= specified API level.
     * Handles preview SDKs if isTargetSDK is true.
     * Original: public static boolean b(int i0_apiLevel, boolean bool0_isTarget)
     */
    public static boolean isBelowApiLevel(int apiLevel, boolean isTargetSDKBehavior) {
         if (isTargetSDKBehavior && Build.VERSION.SDK_INT >= 23) {
            // Original: if (SDK_INT > apiLevel) { if (SDK_INT == apiLevel -1 && PREVIEW_SDK_INT > 0) return false; } return true;
            // This seems to have a typo. Should be SDK_INT == apiLevel + 1 for the preview check if it's about being "below or equal".
            // Let's re-evaluate: we want to return true if current is <= apiLevel.
            // If current SDK_INT > apiLevel, it's definitely false.
            // If current SDK_INT == apiLevel, it's true.
            // If current SDK_INT < apiLevel, it's true.
            // The preview logic seems to be: if current is (apiLevel + 1)'s preview, it's still considered <= apiLevel.
            // This is complex. The original logic:
            // if (SDK_INT > apiLevel) {
            //    if (SDK_INT == apiLevel - 1 && PREVIEW_SDK_INT > 0) return false; // This inner if is unreachable if outer is true
            // }
            // return true; // This would mean if SDK_INT <= apiLevel, it's always true. This doesn't use PREVIEW_SDK_INT.
            // The original logic for `b` was:
            // if (SDK_INT > i0) { // current > target
            //    // This part seems to be for an isAboveOrEqual check, not isBelowOrEqual
            //    // if (SDK_INT == i0 - 1 && PREVIEW_SDK_INT > 0) return false;
            // }
            // return true; // This makes it always true if SDK_INT <= i0.
            // The logic from a(api, isTarget) was: `if (SDK_INT < api) { if (SDK_INT == api -1 && PREVIEW > 0) return false; } return true;`
            // This is likely more correct for an "is effectively at least X" check.
            // For "is effectively at most X", it's simpler:
            if (Build.VERSION.SDK_INT > apiLevel) return false;
            if (Build.VERSION.SDK_INT == apiLevel && Build.VERSION.PREVIEW_SDK_INT > 0 && isTargetSDKBehavior) return false; // If current is preview of target, it's not "below or equal" final target.
            return true; // Otherwise, it is below or equal.
        }
        return Build.VERSION.SDK_INT <= apiLevel;
    }

    /**
     * Checks if current API level is within [minApiLevel, maxApiLevel].
     * Handles preview SDKs if isTargetSDK is true.
     * Original: public static boolean a(int bool0_min, int bool1_max, boolean bool2_isTarget)
     */
    public static boolean isApiLevelInRange(int minApiLevel, int maxApiLevel, boolean isTargetSDKBehavior) {
        return isAboveApiLevel(minApiLevel, isTargetSDKBehavior) && isBelowApiLevel(maxApiLevel, isTargetSDKBehavior);
    }


    /**
     * Gets the root cause of a throwable and returns its stack trace as a string.
     * Original: public static String a(Throwable throwable)
     */
    public static String getExceptionCauseString(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        try {
            Throwable current = throwable;
            while (current.getCause() != null) {
                current = current.getCause();
            }
            current.printStackTrace(ps); // Print root cause stack trace
            return sanitizeStringForAscii(baos.toString());
        } finally {
            SharePatchFileUtil.closeQuietly(ps); // PrintStream also implements Closeable
            SharePatchFileUtil.closeQuietly(baos);
        }
    }

    /**
     * Replaces non-ASCII characters in a string with (char)0.
     * Original: public static String c(String str0)
     */
    public static String sanitizeStringForAscii(String input) {
        if (input == null) {
            return null;
        }
        char[] chars = input.toCharArray();
        boolean modified = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 127) { // Standard ASCII range is 0-127
                chars[i] = '\0'; // Replace with null character
                modified = true;
                // Original code had: break; then `return new String(charArr0, 0, i1);` where i1 was current index.
                // This means it truncated at first non-ASCII.
                // Let's replicate that truncation.
                return new String(chars, 0, i);
            }
        }
        return modified ? new String(chars) : input; // If no modification, return original to save allocation
    }


    /**
     * Cleans up patch files if no patch is applied or if patch info is inconsistent.
     * Original: public static void m(Context context)
     * TODO: Depends on SharePatchInfo, SharePatchFileUtil being fully translated and available.
     */
    public static void cleanPatch(Context context) {
        if (context == null) {
            throw new TinkerRuntimeException("Context is null in cleanPatch");
        }
        File patchDirectory = SharePatchFileUtil.getPatchDirectory(context);
        if (!patchDirectory.exists()) { // No main tinker directory, nothing to clean.
            ShareTinkerLog.i(TAG, "try to clean patch while tinker directory does not exist.");
            return;
        }

        File patchInfoFile = SharePatchFileUtil.getPatchMetaFile(patchDirectory.getAbsolutePath());
        if (!patchInfoFile.exists()) {
            ShareTinkerLog.i(TAG, "try to clean patch while patch info file does not exist.");
            // If patch info doesn't exist, but patch directory does, clean the whole patch dir.
            SharePatchFileUtil.deleteRecursive(patchDirectory);
            return;
        }

        File patchInfoLockFile = SharePatchFileUtil.getPatchInfoLockFile(patchDirectory.getAbsolutePath());
        SharePatchInfo patchInfo = SharePatchInfo.readAndCheckPropertyWithLock(patchInfoFile, patchInfoLockFile);

        if (patchInfo != null) {
            if (isNullOrNil(patchInfo.oldVersion) || isNullOrNil(patchInfo.newVersion) || patchInfo.newVersion.equals(patchInfo.oldVersion)) {
                // If oldVersion or newVersion is empty, or if newVersion equals oldVersion (meaning no patch applied or reverted)
                // then it implies we should clean up any patch specific subdirectories.
                // The original logic `if (i.b.equals(i.a))` means newVersion == oldVersion.
                // The original logic `String str0 = h.c(i.b); h.d(new File(file, str0));`
                // means getPatchVersionDirectory(newVersion) and delete it.
                ShareTinkerLog.i(TAG, "Patch info is clean or invalid, cleaning patch directory: " + patchInfo.newVersion);
                if (!isNullOrNil(patchInfo.newVersion)) { // If newVersion is set (even if same as old)
                    String patchVersionDirName = SharePatchFileUtil.getPatchVersionDirectory(patchInfo.newVersion);
                    if (patchVersionDirName != null) {
                        SharePatchFileUtil.deleteRecursive(new File(patchDirectory, patchVersionDirName));
                    }
                }
                // Reset patchInfo to indicate no patch is active or to remove invalid entries.
                patchInfo.oldVersion = ""; // Or null
                patchInfo.newVersion = ""; // Or null
                patchInfo.versionToRemove = "";
                SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, patchInfo, patchInfoLockFile);

            } else {
                // A valid patch is applied (newVersion is different from oldVersion and both are valid).
                // The original code `i.e = i.b;` (versionToRemove = newVersion) seems to be preparing for a future rollback.
                // This might not be a "clean" operation but rather setting up state.
                // For a "cleanPatch" method, this branch might not do anything, or it might clean older, unused patch version dirs.
                // The decompiled logic was:
                // if (i.b.equals(i.a)) { /* delete current patch version dir, set new=old, versionToRemove="" */ }
                // else { i.e = i.b; /* versionToRemove = newVersion */ }
                // i.a(fileVar1, i, fileVar2); /* rewrite patch info */
                // This implies if a patch is active (new != old), it sets versionToRemove = newVersion.
                // This is not really "cleaning" but "marking for future removal/rollback".
                // Let's stick to the name "cleanPatch" and assume it means ensure no active patch remnants if state is inconsistent.
                // If patchInfo is valid and newVersion != oldVersion, no cleaning is done by this specific logic.
                ShareTinkerLog.i(TAG, "CleanPatch: Valid patch applied (old: " + patchInfo.oldVersion + ", new: " + patchInfo.newVersion + "). No cleaning needed based on this logic.");
            }
        } else {
            ShareTinkerLog.e(TAG, "Failed to get patch info during cleanPatch, cleaning patch directory.");
            SharePatchFileUtil.deleteRecursive(patchDirectory); // If cannot read patchInfo, clean everything.
        }
    }
}
