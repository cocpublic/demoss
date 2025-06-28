package com.tencent.tinker.loader;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import dalvik.system.PathClassLoader;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareFileSignature; // Was 'a' in original context
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tencent.tinker.loader.shareutil.ShareTinkerLog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Handles the loading and verification of "ArkHot" specific patches.
 * ArkHot seems to be a specialized patch mechanism, possibly related to Huawei's Ark Compiler.
 * This loader reads metadata from "assets/arkHot_meta.txt", verifies file integrity,
 * and installs dexes (typically "patch.apk") using SystemClassLoaderAdder.
 *
 * Note: This class was translated from a decompiled and obfuscated class `e.java`.
 */
public final class TinkerArkHotLoader {
    private static final String TAG = "Tinker.TinkerArkHotLoader";

    private static final String ARK_HOT_SUBDIRECTORY = "arkHot";
    private static final String ARK_HOT_PATCH_APK_NAME = "patch.apk";
    private static final String ARK_HOT_META_FILE = "assets/arkHot_meta.txt";

    // Stores ShareFileSignature objects for dexes to be loaded if ArkHot is enabled.
    // Populated by checkPatch, used by loadPatch.
    private static final HashSet<ShareFileSignature> sPendingArkHotDexes = new HashSet<>();

    // Indicates if the current environment is recognized as an ArkHot environment.
    private static final boolean sIsArkHotEnvironment;

    static {
        sIsArkHotEnvironment = ShareTinkerInternals.isArkHotRunning();
    }

    private TinkerArkHotLoader() {
        // Utility class
    }

    /**
     * Attempts to load ArkHot patch dexes into the ClassLoader.
     *
     * @param application     The TinkerApplication instance.
     * @param patchDirectory  The root directory of the current patch.
     * @param resultIntent    Intent to store results and error codes.
     * @return True if loading was successful or not needed, false on failure.
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) // For PathClassLoader
    public static boolean loadPatch(TinkerApplication application, String patchDirectory, Intent resultIntent) {
        if (sPendingArkHotDexes.isEmpty() && !sIsArkHotEnvironment) { // If sIsArkHotEnvironment is false, sPendingArkHotDexes should be empty from checkPatch logic
            ShareTinkerLog.i(TAG, "loadPatch: No ArkHot dexes pending or not an ArkHot environment.");
            return true; // Nothing to do
        }

        ClassLoader currentClassLoader = TinkerArkHotLoader.class.getClassLoader();
        if (!(currentClassLoader instanceof PathClassLoader)) {
            ShareTinkerLog.e(TAG, "loadPatch: Current ClassLoader is not a PathClassLoader: " + currentClassLoader);
            ShareIntentUtil.setIntentReturnCode(resultIntent, ShareConstants.ERROR_LOAD_PATCH_CLASSPATH_LOADER_FAIL);
            return false;
        }
        PathClassLoader pathClassLoader = (PathClassLoader) currentClassLoader;
        ShareTinkerLog.i(TAG, "loadPatch: Using PathClassLoader: " + pathClassLoader.toString());

        String arkHotDirStr = patchDirectory + File.separator + ARK_HOT_SUBDIRECTORY + File.separator;
        ArrayList<File> dexFilesToLoad = new ArrayList<>();

        // If ArkHot environment is active AND sPendingArkHotDexes is empty (meaning checkPatch determined
        // that a generic patch.apk should be loaded without specific meta entries for it),
        // then add the default patch.apk.
        // Original: if (e.b && e.a.isEmpty()) -> sIsArkHotEnvironment && sPendingArkHotDexes.isEmpty()
        if (sIsArkHotEnvironment && sPendingArkHotDexes.isEmpty()) {
             File defaultPatchApk = new File(arkHotDirStr + ARK_HOT_PATCH_APK_NAME);
             if (defaultPatchApk.exists()){
                dexFilesToLoad.add(defaultPatchApk);
                ShareTinkerLog.i(TAG, "loadPatch: ArkHot environment, no specific dexes, adding default " + defaultPatchApk.getPath());
             } else {
                ShareTinkerLog.w(TAG, "loadPatch: ArkHot environment, default patch.apk not found at " + defaultPatchApk.getPath());
             }
        } else {
            // If sPendingArkHotDexes has entries, it means checkPatch found specific "patch.apk" entries.
            // Their paths in ShareFileSignature are relative to "assets/" or similar, not full paths.
            // Here, we expect the physical file to be patchDirectory/arkHot/patch.apk
            for (ShareFileSignature sig : sPendingArkHotDexes) {
                // The 'path' field in ShareFileSignature for arkHot_meta.txt seems to be the entry name itself.
                // We are interested if it's "patch.apk"
                if (ARK_HOT_PATCH_APK_NAME.equals(sig.path)) { // sig.path was original b field
                    File patchApkFile = new File(arkHotDirStr + sig.path);
                     if (patchApkFile.exists()) {
                        dexFilesToLoad.add(patchApkFile);
                        ShareTinkerLog.i(TAG, "loadPatch: Adding ArkHot dex from meta: " + patchApkFile.getPath());
                    } else {
                        ShareTinkerLog.w(TAG, "loadPatch: ArkHot dex from meta not found: " + patchApkFile.getPath());
                    }
                }
            }
        }

        if (dexFilesToLoad.isEmpty()){
            ShareTinkerLog.i(TAG, "loadPatch: No actual dex files found to load for ArkHot.");
            return true; // Nothing to load
        }

        try {
            // SystemClassLoaderAdder.installDexesForHotplug is designed for PathClassLoader
            SystemClassLoaderAdder.installDexesForHotplug(pathClassLoader, dexFilesToLoad);
            ShareTinkerLog.i(TAG, "loadPatch: Successfully installed ArkHot dexes.");
        } catch (Throwable t) {
            ShareTinkerLog.e(TAG, "loadPatch: Failed to install ArkHot dexes.", t);
            resultIntent.putExtra(ShareIntentUtil.INTENT_PATCH_EXCEPTION, t);
            ShareIntentUtil.setIntentReturnCode(resultIntent, ShareConstants.ERROR_LOAD_PATCH_ARKHOT_DEX_LOAD_FAIL); // Original: 242
            return false;
        }
        return true;
    }

    /**
     * Checks the ArkHot patch metadata ("assets/arkHot_meta.txt").
     * Populates sPendingArkHotDexes if valid entries are found.
     *
     * @param patchDirectory  The root directory of the current patch.
     * @param securityCheck   ShareSecurityCheck instance containing meta file contents.
     * @param resultIntent    Intent to store results and error codes.
     * @return True if check is successful, false on failure.
     */
    public static boolean checkPatch(String patchDirectory, ShareSecurityCheck securityCheck, Intent resultIntent) {
        if (!sIsArkHotEnvironment) { // Original code: e.b
            ShareTinkerLog.i(TAG, "checkPatch: Not an ArkHot environment, skipping ArkHot checks.");
            return true;
        }

        String arkHotMetaContent = securityCheck.getMetaContentMap().get(ARK_HOT_META_FILE);
        if (arkHotMetaContent == null) {
            ShareTinkerLog.i(TAG, "checkPatch: No " + ARK_HOT_META_FILE + " found. Assuming no ArkHot patch.");
            return true; // No meta file, so nothing to check or load for ArkHot specifically
        }

        sPendingArkHotDexes.clear();
        ArrayList<ShareFileSignature> signatureList = new ArrayList<>();
        ShareFileSignature.parseFileSignatures(arkHotMetaContent, signatureList);

        if (signatureList.isEmpty()) {
            ShareTinkerLog.i(TAG, "checkPatch: " + ARK_HOT_META_FILE + " is empty.");
            return true; // Empty meta file
        }

        // This map stores expected dex file names and their (currently empty) target paths in the patch structure
        HashMap<String, String> expectedDexFilesMap = new HashMap<>(1);

        for (ShareFileSignature sig : signatureList) {
            // Original: if (a.a(a)) -> ShareFileSignature.isSimpleFileSignatureValid(sig)
            // This checks if sig.path is empty and sig.md5 is valid 32-char.
            // If this is true for an ArkHot entry, it's considered an error.
            if (ShareFileSignature.isSimpleFileSignatureValid(sig)) {
                ShareTinkerLog.e(TAG, "checkPatch: Invalid ArkHot meta entry (simple file signature): " + sig.toString());
                resultIntent.putExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_PATCH_CHECK, // Using existing constant, though meaning is different
                        ShareConstants.ERROR_PACKAGE_PATCH_ARKHOT_META_MALFORMED); // Original: 253
                ShareIntentUtil.setIntentReturnCode(resultIntent, ShareConstants.ERROR_PATCH_ARKHOT_META_CORRUPTED); // Original: 248
                return false;
            }

            // Original: if (e.b && "patch.apk".equals(a.b)) { e.a.add(a); }
            // e.b is sIsArkHotEnvironment. a.b is sig.path (ShareFileSignature.path)
            if (sIsArkHotEnvironment && ARK_HOT_PATCH_APK_NAME.equals(sig.path)) {
                sPendingArkHotDexes.add(sig);
                ShareTinkerLog.i(TAG, "checkPatch: Added pending ArkHot dex: " + sig.path + " (MD5: " + sig.md5 + ")");
            }
        }

        // If ArkHot is enabled, but sPendingArkHotDexes is still empty (meaning no "patch.apk" entry was found,
        // or it wasn't specifically listed with MD5), then we assume a default "patch.apk" is expected.
        // Original: if (e.b && e.a.isEmpty()) { map.put("patch.apk", ""); }
        if (sIsArkHotEnvironment && sPendingArkHotDexes.isEmpty()) {
            ShareTinkerLog.i(TAG, "checkPatch: ArkHot environment and no specific 'patch.apk' in meta, expecting default.");
            expectedDexFilesMap.put(ARK_HOT_PATCH_APK_NAME, ""); // Value is not used, just presence of key
        } else if (sIsArkHotEnvironment && !sPendingArkHotDexes.isEmpty()){
            // If specific entries were found and added to sPendingArkHotDexes,
            // then these are the files we expect to exist.
             for(ShareFileSignature sig : sPendingArkHotDexes) {
                 expectedDexFilesMap.put(sig.path, ""); // sig.path should be "patch.apk"
             }
        }


        String arkHotDirStr = patchDirectory + File.separator + ARK_HOT_SUBDIRECTORY + File.separator;
        File arkHotDirFile = new File(arkHotDirStr);

        if (!arkHotDirFile.exists() || !arkHotDirFile.isDirectory()) {
            if (!expectedDexFilesMap.isEmpty()) { // Only error if we were expecting files
                ShareTinkerLog.e(TAG, "checkPatch: ArkHot directory not found or not a directory: " + arkHotDirStr);
                ShareIntentUtil.setIntentReturnCode(resultIntent, ShareConstants.ERROR_PATCH_ARKHOT_PATH_NOT_FOUND); // Original: 247
                return false;
            } else {
                 ShareTinkerLog.i(TAG, "checkPatch: ArkHot directory not found, but no dexes were expected.");
                 return true; // Not an error if no dexes were expected
            }
        }

        // Check if all expected dex files physically exist
        for (String dexFileName : expectedDexFilesMap.keySet()) {
            File dexFile = new File(arkHotDirStr + dexFileName);
            if (!SharePatchFileUtil.isLegalFile(dexFile)) { // isLegalFile checks existence and >0 length
                ShareTinkerLog.e(TAG, "checkPatch: Expected ArkHot file not found or invalid: " + dexFile.getAbsolutePath());
                try {
                    resultIntent.putExtra(ShareIntentUtil.INTENT_PATCH_MISSING_DEX_PATH, dexFile.getCanonicalPath());
                } catch (IOException e) {
                    ShareTinkerLog.e(TAG, "checkPatch: IOException when getting canonical path for " + dexFile.getAbsolutePath(), e);
                    resultIntent.putExtra(ShareIntentUtil.INTENT_PATCH_MISSING_DEX_PATH, dexFile.getAbsolutePath());
                }
                ShareIntentUtil.setIntentReturnCode(resultIntent, ShareConstants.ERROR_PATCH_ARKHOT_FILE_NOT_FOUND); // Original: 246
                return false;
            }
        }

        // Store the map of dex files that will be attempted to load.
        // The keys are relative paths within the arkHot directory (e.g., "patch.apk").
        // The values are not strictly used beyond presence in Tinker 1.9.14.
        resultIntent.putExtra(ShareIntentUtil.INTENT_PATCH_DEXES_PATH, expectedDexFilesMap);
        ShareTinkerLog.i(TAG, "checkPatch: ArkHot patch check successful. Expected dex files: " + expectedDexFilesMap.keySet());
        return true;
    }
}
