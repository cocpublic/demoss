package com.tencent.tinker.loader.shareutil;

import android.os.Build;
import android.util.Log; // For Log.getStackTraceString

import com.tencent.tinker.loader.TinkerRuntimeException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Represents the metadata for a patch, typically stored in a "patch.info" file.
 * This includes information about the old and new versions of the patch,
 * build fingerprint, oat directory, and other patch-specific configurations.
 *
 * Note: This class was translated from a decompiled and obfuscated class `i.java`.
 */
public class SharePatchInfo {
    private static final String TAG = "Tinker.PatchInfo";

    public static final String OLD_VERSION_KEY = "old";
    public static final String NEW_VERSION_KEY = "new";
    public static final String IS_PROTECTED_APP_KEY = "is_protected_app"; // Original might be inverted "0" for true
    public static final String USE_CUSTOM_FILE_PATCH_KEY = "use_custom_file_patch"; // Original might be inverted
    public static final String VERSION_TO_REMOVE_KEY = "version_to_remove";
    public static final String FINGERPRINT_KEY = "print"; // "print" was used in decompiled for fingerprint
    public static final String OAT_DIR_KEY = "dir";       // "dir" was used in decompiled for oatDir
    public static final String IS_REMOVE_INTERPRET_OAT_DIR_KEY = "is_remove_interpret_oat_dir"; // Original might be inverted

    public String oldVersion; // MD5 of the old version (base)
    public String newVersion; // MD5 of the new version (patch)
    public boolean isProtectedApp;
    public boolean useCustomFilePatch; // This field was read but not set by constructor in decompiled
    public String versionToRemove;    // Specific version to remove during patching
    public String fingerprint;        // Build fingerprint
    public String oatDir;             // OAT directory (e.g., "odex", "dex", "interpret")
    public boolean isRemoveInterpretOATDir;

    // Max retry attempts for reading/writing patch info
    private static final int MAX_RETRY_ATTEMPTS = 2;


    public SharePatchInfo(String oldVersion, String newVersion, boolean isProtectedApp,
                          boolean useCustomFilePatch, String versionToRemove, String fingerprint,
                          String oatDir, boolean isRemoveInterpretOATDir) {
        this.oldVersion = oldVersion;
        this.newVersion = newVersion;
        this.isProtectedApp = isProtectedApp;
        this.useCustomFilePatch = useCustomFilePatch;
        this.versionToRemove = versionToRemove;
        this.fingerprint = fingerprint;
        this.oatDir = oatDir;
        this.isRemoveInterpretOATDir = isRemoveInterpretOATDir;
    }

    /**
     * Reads patch information from a specified file, acquiring a lock first.
     *
     * @param propertyFile The file to read patch info from (e.g., patch.info).
     * @param lockFile     The lock file to use for synchronization.
     * @return The parsed SharePatchInfo object, or null if reading fails or info is invalid.
     */
    public static SharePatchInfo readAndCheckPropertyWithLock(File propertyFile, File lockFile) {
        if (propertyFile == null || lockFile == null) {
            return null;
        }

        File parentDir = lockFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        ShareFileLockHelper fileLock = null;
        try {
            fileLock = ShareFileLockHelper.getFileLock(lockFile);
            return readPropertiesFromFile(propertyFile);
        } catch (IOException e) {
            throw new TinkerRuntimeException("readAndCheckPropertyWithLock fail", e);
        } finally {
            SharePatchFileUtil.closeQuietly(fileLock);
        }
    }

    /**
     * Writes patch information to a specified file, acquiring a lock first.
     *
     * @param propertyFile The file to write patch info to.
     * @param patchInfo    The SharePatchInfo object to write.
     * @param lockFile     The lock file to use for synchronization.
     * @return True if writing and verification were successful, false otherwise.
     */
    public static boolean rewritePatchInfoFileWithLock(File propertyFile, SharePatchInfo patchInfo, File lockFile) {
        if (propertyFile == null || patchInfo == null || lockFile == null) {
            return false;
        }

        File parentDir = lockFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        ShareFileLockHelper fileLock = null;
        try {
            fileLock = ShareFileLockHelper.getFileLock(lockFile);
            return writePropertiesToFile(propertyFile, patchInfo);
        } catch (Exception e) { // Catch broader Exception as original did
            throw new TinkerRuntimeException("rewritePatchInfoFileWithLock fail", e);
        } finally {
            SharePatchFileUtil.closeQuietly(fileLock);
        }
    }

    /**
     * Reads patch information from the given file. Retries on failure.
     */
    private static SharePatchInfo readPropertiesFromFile(File file) {
        SharePatchInfo patchInfo = null;
        boolean success = false;

        for (int attempt = 0; attempt < MAX_RETRY_ATTEMPTS && !success; attempt++) {
            if (attempt > 0) {
                ShareTinkerLog.i(TAG, "Retry reading patch info file: " + file.getAbsolutePath() + " attempt: " + attempt);
            }
            Properties properties = new Properties();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                properties.load(fis);
                String oldVer = properties.getProperty(OLD_VERSION_KEY);
                String newVer = properties.getProperty(NEW_VERSION_KEY);

                // Tinker's boolean property convention: "0" means true, "1" means false (or vice-versa sometimes)
                // Decompiled: (str2 != null && str2.isEmpty() && "0".equals(str2) ? 0 : 1); this is problematic.
                // Let's assume: "0" represents TRUE, "1" (or missing/empty) represents FALSE for these specific flags.
                // This needs to be consistent with how they are written.
                String isProtectedAppStr = properties.getProperty(IS_PROTECTED_APP_KEY);
                boolean isProtected = isProtectedAppStr != null && isProtectedAppStr.equals("0");

                String useCustomFilePatchStr = properties.getProperty(USE_CUSTOM_FILE_PATCH_KEY);
                boolean useCustomFile = useCustomFilePatchStr != null && useCustomFilePatchStr.equals("0");

                String versionToRemoveStr = properties.getProperty(VERSION_TO_REMOVE_KEY);
                String fingerprintStr = properties.getProperty(FINGERPRINT_KEY);
                String oatDirStr = properties.getProperty(OAT_DIR_KEY);

                String isRemoveOatStr = properties.getProperty(IS_REMOVE_INTERPRET_OAT_DIR_KEY);
                boolean isRemoveOat = isRemoveOatStr != null && isRemoveOatStr.equals("0");

                // Validation from original: oldVer must be valid MD5, newVer must be valid MD5
                // And oldVer must not be empty string.
                if (oldVer != null && !oldVer.isEmpty() && SharePatchFileUtil.isValidMd5(oldVer) &&
                    SharePatchFileUtil.isValidMd5(newVer)) {
                    patchInfo = new SharePatchInfo(oldVer, newVer, isProtected, useCustomFile,
                                                   versionToRemoveStr, fingerprintStr, oatDirStr, isRemoveOat);
                    success = true;
                } else {
                    ShareTinkerLog.e(TAG, "Patch info file corrupted: " + file.getAbsolutePath() +
                                        " oldVer: " + oldVer + " newVer: " + newVer);
                }
            } catch (IOException e) {
                ShareTinkerLog.e(TAG, "read property failed, e:" + e.getMessage());
            } finally {
                SharePatchFileUtil.closeQuietly(fis);
            }
        }
        return success ? patchInfo : null;
    }

    /**
     * Writes patch information to the given file. Retries and verifies on failure.
     */
    private static boolean writePropertiesToFile(File file, SharePatchInfo patchInfo) {
        if (file == null || patchInfo == null) {
            return false;
        }

        // Defaulting logic from original
        if (ShareTinkerInternals.isNullOrNil(patchInfo.fingerprint)) {
            patchInfo.fingerprint = Build.FINGERPRINT;
        }
        if (ShareTinkerInternals.isNullOrNil(patchInfo.oatDir)) {
            patchInfo.oatDir = ShareConstants.INTERPRET_PATCH_OAT_DIR; // Default to "odex" or "interpret"
        }

        ShareTinkerLog.i(TAG, "rewritePatchInfoFile file path: " + file.getAbsolutePath() +
                " , oldVer:" + patchInfo.oldVersion +
                ", newVer:" + patchInfo.newVersion +
                ", isProtectedApp:" + patchInfo.isProtectedApp + // Log boolean directly
                ", useCustomFilePatch:" + patchInfo.useCustomFilePatch +
                ", versionToRemove:" + patchInfo.versionToRemove +
                ", fingerprint:" + patchInfo.fingerprint +
                ", oatDir:" + patchInfo.oatDir +
                ", isRemoveInterpretOATDir:" + patchInfo.isRemoveInterpretOATDir +
                ", stack: " + Log.getStackTraceString(new Throwable())); // For debugging context

        boolean success = false;
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        for (int attempt = 0; attempt < MAX_RETRY_ATTEMPTS && !success; attempt++) {
            if (attempt > 0) {
                 ShareTinkerLog.i(TAG, "Retry writing patch info file: " + file.getAbsolutePath() + " attempt: " + attempt);
            }
            Properties properties = new Properties();
            // Assuming "0" means true, "1" means false for boolean properties in the file
            properties.put(OLD_VERSION_KEY, patchInfo.oldVersion != null ? patchInfo.oldVersion : "");
            properties.put(NEW_VERSION_KEY, patchInfo.newVersion != null ? patchInfo.newVersion : "");
            properties.put(IS_PROTECTED_APP_KEY, patchInfo.isProtectedApp ? "0" : "1");
            properties.put(USE_CUSTOM_FILE_PATCH_KEY, patchInfo.useCustomFilePatch ? "0" : "1");
            properties.put(VERSION_TO_REMOVE_KEY, patchInfo.versionToRemove != null ? patchInfo.versionToRemove : "");
            properties.put(FINGERPRINT_KEY, patchInfo.fingerprint != null ? patchInfo.fingerprint : "");
            properties.put(OAT_DIR_KEY, patchInfo.oatDir != null ? patchInfo.oatDir : "");
            properties.put(IS_REMOVE_INTERPRET_OAT_DIR_KEY, patchInfo.isRemoveInterpretOATDir ? "0" : "1");

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file, false); // false to overwrite
                String comment = "from old version:" + patchInfo.oldVersion + " to new version:" + patchInfo.newVersion;
                properties.store(fos, comment);
            } catch (Exception e) {
                ShareTinkerLog.e(TAG, "write property failed, e:" + e.getMessage());
                // Continue to next attempt if exception occurs
            } finally {
                SharePatchFileUtil.closeQuietly(fos);
            }

            // Verify by re-reading
            SharePatchInfo rereadInfo = readPropertiesFromFile(file);
            if (rereadInfo != null &&
                equals(rereadInfo.oldVersion, patchInfo.oldVersion) &&
                equals(rereadInfo.newVersion, patchInfo.newVersion)) {
                success = true;
            } else {
                 ShareTinkerLog.w(TAG, "Verification after writing patch info failed. Attempt: " + (attempt + 1));
                 if (file.exists()) file.delete(); // Clean up failed write attempt
            }
        }
        return success;
    }

    // Helper for null-safe equals
    private static boolean equals(String s1, String s2) {
        if (s1 == null) {
            return s2 == null;
        }
        return s1.equals(s2);
    }
}
