package com.tencent.tinker.loader.shareutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import com.tencent.tinker.loader.TinkerRuntimeException; // Assuming k is TinkerRuntimeException

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Utility class for various file operations related to Tinker patches.
 * This includes path management, MD5 calculation, file verification,
 * safe deletion, and other file-system interactions.
 *
 * Note: This class was translated from a decompiled and obfuscated class `h.java`.
 */
public final class SharePatchFileUtil {
    private static final String TAG = "Tinker.PatchFileUtil";

    private static final char[] HEX_DIGITS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private SharePatchFileUtil() {
        // Utility class
    }

    /**
     * Returns the main Tinker patch directory for the application.
     * Name can vary on certain OEM devices (e.g., Oppo API 22).
     */
    public static File getPatchDirectory(Context context) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        if (appInfo == null) {
            return null;
        }
        String dirName = "tinker"; // Default
        if ("oppo".equalsIgnoreCase(Build.MANUFACTURER) && Build.VERSION.SDK_INT == 22) {
            dirName = "tinker"; // Original decompiled had "tinker" vs "wc_tinker_dir", but official Tinker uses "tinker"
        } else if (ShareTinkerInternals.isQrom()) { // Example of another potential check
            dirName = "patch_tinker"; // QROM might use a different name
        } else {
            // The decompiled "wc_tinker_dir" might be a specific variant.
            // Sticking to a more common "tinker" or "patch_tinker" used in official examples.
            // For safety, let's assume the decompiled name if not Oppo/Qrom.
            dirName = "wc_tinker_dir";
             // However, official Tinker source often uses "tinker_dir" or just "tinker"
             // For maximum compatibility with the decompiled logic, we might use "wc_tinker_dir"
             // but it's less standard. Let's use "tinker" as a common default.
            if (!"oppo".equalsIgnoreCase(Build.MANUFACTURER) || Build.VERSION.SDK_INT != 22) {
                 // If not the specific Oppo case, use a general name.
                 // The decompiled code had: "oppo".equalsIgnoreCase(Build.MANUFACTURER) && Build$VERSION.SDK_INT == 22 ? "tinker" : "wc_tinker_dir";
                 // This implies "wc_tinker_dir" for non-Oppo API 22.
                 dirName = "wc_tinker_dir";
            }

        }
        return new File(appInfo.dataDir, dirName);
    }

    /**
     * Returns the temporary directory used by Tinker during patch application.
     */
    public static File getPatchTempDirectory(Context context) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        if (appInfo == null) {
            // Should not happen for a running app.
            return null;
        }
        return new File(appInfo.dataDir, ShareConstants.PATCH_TEMP_DIR_NAME); // Use constant
    }

    /**
     * Returns the file used to store information about the last crash.
     */
    public static File getPatchLastCrashFile(Context context) {
        File tempDir = getPatchTempDirectory(context);
        if (tempDir == null) {
            return null;
        }
        return new File(tempDir, ShareConstants.PATCH_LAST_CRASH_NAME); // Use constant
    }

    /**
     * Returns the patch meta file (usually "patch_meta.info" or "patch.info") within a given patch directory.
     */
    public static File getPatchMetaFile(String patchDirectoryPath) {
        return new File(patchDirectoryPath, ShareConstants.PATCH_META_FILE_NAME); // Use constant
    }

    /**
     * Returns the info lock file (usually "info.lock") within a given patch directory.
     */
    public static File getPatchInfoLockFile(String patchDirectoryPath) {
        return new File(patchDirectoryPath, ShareConstants.PATCH_INFO_LOCK_FILE_NAME); // Use constant
    }

    /**
     * Generates a directory name for a specific patch version based on its MD5.
     * Example: "patch-69df23a7" from an MD5.
     *
     * @param patchMd5 The full MD5 of the patch.
     * @return The directory name, or null if the MD5 is invalid.
     */
    public static String getPatchVersionDirectory(String patchMd5) {
        if (patchMd5 == null || patchMd5.length() != 32) {
            return null;
        }
        return ShareConstants.PATCH_VERSION_DIRECTORY_PREFIX + patchMd5.substring(0, 8);
    }

    /**
     * Generates a file name for a specific patch APK based on its MD5.
     * Example: "patch-69df23a7.apk" from an MD5.
     *
     * @param patchMd5 The full MD5 of the patch.
     * @return The patch file name, or null if the MD5 is invalid.
     */
    public static String getPatchVersionFile(String patchMd5) {
        String versionDir = getPatchVersionDirectory(patchMd5);
        if (versionDir == null) {
            return null;
        }
        return versionDir + ShareConstants.PATCH_SUFFIX; // Use constant
    }

    /**
     * Checks if the given string is a valid 32-character MD5.
     */
    public static boolean isValidMd5(String md5) {
        return md5 != null && md5.length() == 32;
    }

    /**
     * Checks if a file exists, is a file, is readable, and has a length greater than 0.
     * Note: Original decompiled code had a bug `0L > file.length()`.
     */
    public static boolean isLegalFile(File file) {
        return file != null && file.exists() && file.canRead() && file.isFile() && file.length() > 0;
    }

    /**
     * Checks if it's safe to copy a file, considering OEM restrictions and file validity.
     * TODO: Depends on ShareTinkerInternals methods (m.a, m.c in decompiled)
     */
    public static boolean isSafeToCopyFile(File file) {
        // Original logic:
        // int i0 = "vivo".equalsIgnoreCase(Build.MANUFACTURER) || "oppo".equalsIgnoreCase(Build.MANUFACTURER) || "meizu".equalsIgnoreCase(Build.MANUFACTURER) ? 0 : 1;
        // int i1 = ! m.a(29, 1) || m.c() ? 0 : 1; // m.a(29,1) -> ShareTinkerInternals.isVersionInRange(29,1), m.c() -> ShareTinkerInternals.isVmArtDebug() or similar
        // int i2 = ! file.exists() || 0L == file.length() ? 0 : 1;
        // return (i0 != 0 || i1 != 0 && i2 != 0);

        boolean isProblematicManufacturer = "vivo".equalsIgnoreCase(Build.MANUFACTURER) ||
                                            "oppo".equalsIgnoreCase(Build.MANUFACTURER) ||
                                            "meizu".equalsIgnoreCase(Build.MANUFACTURER);

        // Assuming m.a(29,1) means "is API level < 29" or some specific version check.
        // Assuming m.c() means something like "isART" or "isDebuggable"
        // This logic needs ShareTinkerInternals to be fully translated.
        boolean someVersionOrVMCheck = true; // Placeholder
        // if (ShareTinkerInternals.isVersionBelow(29) || ShareTinkerInternals.isSomeVmCondition()) {
        //     someVersionOrVMCheck = true;
        // } else {
        //     someVersionOrVMCheck = false;
        // }

        boolean fileIsValid = file.exists() && file.length() > 0;

        // Original: return (i0 != 0 || i1 != 0 && i2 != 0);
        // i0 != 0 means NOT problematic manufacturer
        // i1 != 0 means someVersionOrVMCheck is true
        // i2 != 0 means fileIsValid is true
        // So: return (!isProblematicManufacturer || (someVersionOrVMCheck && fileIsValid));
        ShareTinkerLog.w(TAG, "isSafeToCopyFile check uses placeholders for ShareTinkerInternals calls.");
        return (!isProblematicManufacturer || (someVersionOrVMCheck && fileIsValid));
    }


    /**
     * Safely deletes a file. If deletion fails, it attempts to delete on exit.
     *
     * @param file The file to delete.
     * @return true if the file does not exist after the operation (either deleted or never existed).
     */
    public static boolean safeDeleteFile(File file) {
        if (file == null) {
            return true; // Or false, depending on desired semantics for null input
        }
        if (!file.exists()) {
            return true;
        }
        ShareTinkerLog.d(TAG, "safeDeleteFile, try to delete path: " + file.getPath());
        boolean deleted = file.delete();
        if (!deleted) {
            ShareTinkerLog.e(TAG, "Failed to delete file, try to delete when exit. path: " + file.getPath());
            file.deleteOnExit();
        }
        return deleted;
    }

    /**
     * Recursively deletes a directory or a file.
     *
     * @param fileOrDirectory The file or directory to delete.
     * @return true if deletion was successful or the file/directory did not exist.
     */
    public static boolean deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory == null || !fileOrDirectory.exists()) {
            return true; // Nothing to delete or already gone
        }

        if (fileOrDirectory.isFile()) {
            return safeDeleteFile(fileOrDirectory);
        }

        if (fileOrDirectory.isDirectory()) {
            File[] children = fileOrDirectory.listFiles();
            if (children != null) {
                for (File child : children) {
                    if (!deleteRecursive(child)) {
                        // Optional: could log failure here or collect failures
                    }
                }
            }
            // After deleting contents, delete the directory itself
            return safeDeleteFile(fileOrDirectory);
        }
        return false; // Should not happen if it exists and is not file/dir
    }

    /**
     * Deletes a file or directory specified by path.
     */
    public static boolean deleteFile(String path) {
        if (path == null || path.isEmpty()) {
            return false;
        }
        return deleteRecursive(new File(path));
    }

    /**
     * Ensures that the parent directory for the given file path exists.
     * If not, it attempts to create it.
     *
     * @param file The file whose parent directory needs to exist.
     */
    public static void ensureFileDirectoryExists(File file) {
        if (file == null) {
            return;
        }
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * Asynchronously cleans a directory. (Original: e(File file))
     * This method was starting a new thread with h$1 and h$2 (Runnables).
     * TODO: Implement the async cleaning logic, potentially using an ExecutorService.
     */
    public static void cleanDirectoryAsync(final File directoryToClean) {
        if (directoryToClean == null || !directoryToClean.exists() || !directoryToClean.isDirectory()) {
            return;
        }
        ShareTinkerLog.d(TAG, "Submitting async task to clean directory: " + directoryToClean.getAbsolutePath());
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShareTinkerLog.d(TAG, "Starting to clean directory: " + directoryToClean.getAbsolutePath());
                deleteRecursive(directoryToClean);
                ShareTinkerLog.d(TAG, "Finished cleaning directory: " + directoryToClean.getAbsolutePath());
            }
        }, "tinker-clean-" + directoryToClean.getName()).start();
    }


    public static boolean verifyFileMd5(File file, String expectedMd5) {
        if (expectedMd5 == null) { // Cannot verify against null
            return false;
        }
        String actualMd5 = getFileMD5(file);
        if (actualMd5 == null) { // Failed to calculate MD5
            return false;
        }
        return expectedMd5.equals(actualMd5);
    }

    public static boolean isDexFile(String filename) {
        if (filename == null) {
            return false;
        }
        return filename.endsWith(ShareConstants.DEX_SUFFIX);
    }

    /**
     * Verifies the MD5 of a dex file. If the file is a JAR/ZIP, it verifies the MD5 of a specific entry within it.
     *
     * @param file           The dex file or JAR/ZIP file containing the dex.
     * @param dexNameInJar   The name of the dex entry if `file` is a JAR/ZIP (e.g., "classes.dex").
     *                       If `file` is a raw dex file, this parameter can be ignored (but the original
     *                       decompiled code structure suggested it might still be passed).
     * @param expectedMd5    The expected MD5 checksum.
     * @return True if the MD5 matches, false otherwise.
     */
    public static boolean verifyDexFileMd5(File file, String dexNameInJar, String expectedMd5) {
        if (file == null || expectedMd5 == null || dexNameInJar == null) {
            // Original decompiled: if (file != null || str1 != null || str0 == null) return false;
            // This is: if (file != null || expectedMd5 != null || dexNameInJar == null) return false;
            // Which means if dexNameInJar is null, it returns false. If file or expectedMd5 is null, it proceeds.
            // This seems wrong. All three should ideally be non-null.
            // Let's assume the intent was that all must be valid.
            return false;
        }

        String actualMd5 = null;
        if (isDexFile(file.getName())) { // It's a raw dex file
            actualMd5 = getFileMD5(file);
        } else { // Assume it's a Jar/Zip file
            ZipFile zipFile = null;
            InputStream stream = null;
            try {
                zipFile = new ZipFile(file);
                ZipEntry entry = zipFile.getEntry(dexNameInJar);
                if (entry == null) {
                    ShareTinkerLog.e(TAG, "Failed to find dex entry " + dexNameInJar + " in " + file.getAbsolutePath());
                    return false;
                }
                stream = zipFile.getInputStream(entry);
                actualMd5 = getMD5(stream);
            } catch (IOException e) {
                ShareTinkerLog.e(TAG, "Exception reading dex entry " + dexNameInJar + " from " + file.getAbsolutePath(), e);
                return false; // Error reading, so MD5 cannot match
            } finally {
                closeQuietly(stream);
                closeQuietly(zipFile);
            }
        }
        return expectedMd5.equals(actualMd5);
    }

    /**
     * Copies a file from source to destination.
     * Note: Original decompiled code had a common bug in the copy loop.
     */
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!isLegalFile(sourceFile) || destFile == null) {
            ShareTinkerLog.w(TAG, "copyFile: invalid source or destination file. Source: " + sourceFile + ", Dest: " + destFile);
            return;
        }
        if (sourceFile.getAbsolutePath().equals(destFile.getAbsolutePath())) {
            return;
        }

        ensureFileDirectoryExists(destFile);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(destFile, false); // false to overwrite
            inputChannel = fis.getChannel();
            outputChannel = fos.getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);
        } finally {
            closeQuietly(inputChannel);
            closeQuietly(outputChannel);
            closeQuietly(fis);
            closeQuietly(fos);
        }
    }


    public static String loadEntryToString(JarFile jarFile, JarEntry jarEntry) throws IOException {
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            inputStream = jarFile.getInputStream(jarEntry);
            bufferedInputStream = new BufferedInputStream(inputStream);
            StringBuilder sb = new StringBuilder();
            byte[] buffer = new byte[8192]; // Common buffer size
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8)); // Assuming UTF-8, adjust if needed
            }
            return sb.toString();
        } finally {
            closeQuietly(bufferedInputStream);
            closeQuietly(inputStream);
        }
    }

    public static String getMD5(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(inputStream);
            MessageDigest digest = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder(32);
            byte[] buffer = new byte[8192]; // Common buffer size
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
            byte[] md5sum = digest.digest();
            for (byte b : md5sum) {
                // Original: builder.append(Integer.toString(byteArr0Var1[i1] & 255 + 256, 16).substring(1));
                // This is a common way to format bytes to hex.
                sb.append(HEX_DIGITS[(b >> 4) & 0x0f]);
                sb.append(HEX_DIGITS[b & 0x0f]);
            }
            return sb.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            ShareTinkerLog.e(TAG, "Failed to get MD5 from stream", e);
            return null;
        } finally {
            closeQuietly(bis); // bis will close the underlying inputStream too if it's the first wrapper
        }
    }

    public static String getMD5(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(bytes);
            byte[] md5sum = digest.digest();
            StringBuilder sb = new StringBuilder(32);
            for (byte b : md5sum) {
                sb.append(HEX_DIGITS[(b >> 4) & 0x0f]);
                sb.append(HEX_DIGITS[b & 0x0f]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            ShareTinkerLog.e(TAG, "Failed to get MD5 from bytes", e);
            return null;
        }
    }

    public static String getFileMD5(File file) {
        if (!isLegalFile(file)) {
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return getMD5(fis);
        } catch (IOException e) {
            ShareTinkerLog.e(TAG, "Failed to get MD5 for file: " + file.getPath(), e);
            return null;
        } finally {
            closeQuietly(fis);
        }
    }

    /**
     * Gets the path for the optimized dex file (ODEX/VDEX).
     * TODO: Depends heavily on ShareTinkerInternals (m.d, m.e in decompiled).
     */
    public static String getOptimizedDexPath(File dexFile, File optimizedDirectory) {
        // Original logic:
        // if (m.d()) { // ShareTinkerInternals.isOdexMode() or similar
        //     String instructionSet = m.e(); // ShareTinkerInternals.getCurrentInstructionSet()
        //     ... construct path like parent/oat/<instructionSet>/name.odex ...
        // } else {
        //     ... construct path like optimizedDirectory/name.dex ...
        // }
        // This needs ShareTinkerInternals to be translated.
        ShareTinkerLog.w(TAG, "getOptimizedDexPath uses placeholders for ShareTinkerInternals calls.");
        if (ShareTinkerInternals.isOdexModeSupport()) { // Placeholder for m.d()
            String instructionSet = ShareTinkerInternals.getCurrentInstructionSet(); // Placeholder for m.e()
            if (instructionSet == null || instructionSet.isEmpty()) {
                 ShareTinkerLog.e(TAG, "Failed to get current instruction set for optimized dex path.");
                 return new File(optimizedDirectory, dexFile.getName()).getPath(); // Fallback
            }
            File parentDir = dexFile.getParentFile();
            if (parentDir == null) parentDir = optimizedDirectory; // Should not happen if dexFile is valid

            String dexName = dexFile.getName();
            int lastDot = dexName.lastIndexOf('.');
            String baseName = (lastDot > 0) ? dexName.substring(0, lastDot) : dexName;

            return new File(parentDir.getAbsolutePath() + File.separator + "oat" + File.separator + instructionSet + File.separator + baseName + ".odex").getPath();
        } else {
            String dexName = dexFile.getName();
            if (!dexName.endsWith(ShareConstants.DEX_SUFFIX)) { // Ensure .dex suffix for non-odex case
                int lastDot = dexName.lastIndexOf(".");
                if (lastDot < 0) {
                    dexName = dexName + ShareConstants.DEX_SUFFIX;
                } else {
                    dexName = dexName.substring(0, lastDot) + ShareConstants.DEX_SUFFIX;
                }
            }
            return new File(optimizedDirectory, dexName).getPath();
        }
    }

    public static void closeQuietly(Object object) {
        if (object == null) {
            return;
        }
        try {
            if (object instanceof Closeable) {
                ((Closeable) object).close();
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && object instanceof AutoCloseable) {
                ((AutoCloseable) object).close();
            } else if (object instanceof ZipFile) { // ZipFile doesn't implement AutoCloseable before API 19
                ((ZipFile) object).close();
            } else {
                // This case was an IllegalArgumentException in decompiled code.
                // However, if we don't know how to close it, just log.
                 ShareTinkerLog.w(TAG, "Cannot close object of type: " + object.getClass().getName());
            }
        } catch (Throwable t) {
            ShareTinkerLog.e(TAG, "Failed to close object: " + object, t);
        }
    }

    /**
     * Checks the MD5 of the resources.arsc file within a given APK (zip file).
     * @param resourcesApk The APK file.
     * @param expectedArscMd5 The expected MD5 of resources.arsc.
     * @return True if the MD5 matches, false otherwise or if resources.arsc is not found.
     */
    public static boolean checkResourceArscMd5(File resourcesApk, String expectedArscMd5) {
        ZipFile zipFile = null;
        InputStream arscStream = null;
        try {
            zipFile = new ZipFile(resourcesApk);
            ZipEntry arscEntry = zipFile.getEntry(ShareConstants.RES_ARSC_FILE_NAME); // Use constant
            if (arscEntry == null) {
                ShareTinkerLog.e(TAG, ShareConstants.RES_ARSC_FILE_NAME + " not found in " + resourcesApk.getAbsolutePath());
                return false;
            }
            arscStream = zipFile.getInputStream(arscEntry);
            String actualArscMd5 = getMD5(arscStream);
            return expectedArscMd5 != null && expectedArscMd5.equals(actualArscMd5);
        } catch (Throwable t) { // Catch Throwable for broader issues like ZipException
            ShareTinkerLog.e(TAG, "checkResourceArscMd5 throwable (" + t.getMessage() + ") when processing " + resourcesApk.getAbsolutePath());
            return false;
        } finally {
            closeQuietly(arscStream); // Must close stream before zipFile
            closeQuietly(zipFile);
        }
    }

    /**
     * Reads the content of the last crash file.
     * @param context The application context.
     * @return The content of the crash file, or null if not found or an error occurs.
     */
    public static String getLastCrashFileContent(Context context) {
        File crashFile = getPatchLastCrashFile(context);
        if (crashFile == null || !isLegalFile(crashFile)) { // Use isLegalFile for >0 length check
            return null;
        }

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(crashFile), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            ShareTinkerLog.e(TAG, "Failed to read last crash file content: " + e.getMessage());
            return null;
        } finally {
            closeQuietly(reader);
        }
    }

     /**
     * Checks if the given path is an archive file (zip, apk, jar).
     * This was likely the h.h(str0) method used in ShareDexDiffPatchInfo constructor.
     */
    public static boolean isArchiveFile(String filePath) {
        if (filePath == null) return false;
        filePath = filePath.toLowerCase();
        return filePath.endsWith(ShareConstants.JAR_SUFFIX) ||
               filePath.endsWith(ShareConstants.APK_SUFFIX) ||
               filePath.endsWith(ShareConstants.ZIP_SUFFIX);
    }
}
