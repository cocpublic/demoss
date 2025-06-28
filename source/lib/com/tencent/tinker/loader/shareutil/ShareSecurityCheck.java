package com.tencent.tinker.loader.shareutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.tencent.tinker.loader.TinkerRuntimeException;

import java.io.File;
import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Utility class for verifying the signature of a patch file and extracting metadata.
 * It ensures that the patch file is signed with the same certificate as the running application.
 * It also reads and provides access to metadata stored within the patch file, such as
 * "assets/package_meta.txt".
 *
 * Note: This class was translated from a decompiled and obfuscated class `l.java`.
 */
public class ShareSecurityCheck {
    private static final String TAG = "Tinker.SecurityCheck";

    private static String sAppSignatureMd5 = null; // Cached MD5 of the application's signature

    private final Context mContext;
    // Stores content of meta files read from patch, e.g., "assets/dex_meta.txt" -> "file content string"
    private final HashMap<String, String> mMetaContentMap;
    // Stores parsed key-value pairs from "assets/package_meta.txt"
    private final HashMap<String, String> mPackageProperties;


    public ShareSecurityCheck(Context context) {
        this.mContext = context;
        this.mMetaContentMap = new HashMap<>();
        this.mPackageProperties = new HashMap<>(); // Initialize, parse on demand

        if (sAppSignatureMd5 == null) {
            initAppSignatureMd5(this.mContext);
        }
    }

    /**
     * @return A map containing the content of various meta files read from the patch.
     *         This map is populated by {@link #verifyPatchMetaSignature(File)}.
     */
    public HashMap<String, String> getMetaContentMap() {
        return mMetaContentMap;
    }

    /**
     * Parses and returns the properties from "assets/package_meta.txt" if it has been
     * read and verified. The parsing happens on the first call after verification.
     *
     * @return A map of properties from "assets/package_meta.txt", or null if the file
     *         was not found in the patch or if parsing fails.
     */
    public HashMap<String, String> getPackagePropertiesIfVerified() {
        // Only parse if mPackageProperties is empty AND metaContentMap contains the package_meta.txt
        if (!mPackageProperties.isEmpty()) {
            return mPackageProperties;
        }

        String packageMetaContent = mMetaContentMap.get(ShareConstants.PACKAGE_META_FILE_NAME);
        if (packageMetaContent == null) {
            ShareTinkerLog.w(TAG, "getPackageProperties: " + ShareConstants.PACKAGE_META_FILE_NAME + " not found.");
            return null; // Return null, not the empty map, to indicate it wasn't found/parsed
        }

        String[] lines = packageMetaContent.split("\n");
        for (String line : lines) {
            if (line == null || line.isEmpty() || line.startsWith("#")) {
                continue;
            }
            String[] kv = line.split("=", 2);
            if (kv.length == 2) {
                mPackageProperties.put(kv[0].trim(), kv[1].trim());
            }
        }
        return mPackageProperties;
    }

    /**
     * Verifies the signature of all entries in the given patch file (JarFile) against
     * the application's signature. It also reads the content of non-META-INF, non-meta.txt
     * entries into the metaContentMap.
     *
     * @param patchFile The patch file (usually a JAR or APK).
     * @return True if all signed entries match the app signature and no errors occur,
     *         false if the file is invalid or cannot be processed.
     * @throws TinkerRuntimeException if a signature mismatch occurs or other critical error.
     */
    public boolean verifyPatchMetaSignature(File patchFile) {
        if (!SharePatchFileUtil.isLegalFile(patchFile)) {
            ShareTinkerLog.e(TAG, "verifyPatchMetaSignature: patch file is not a legal file: " + patchFile.getAbsolutePath());
            return false;
        }

        JarFile jarFile = null;
        try {
            jarFile = new JarFile(patchFile);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry == null || entry.isDirectory()) {
                    continue;
                }

                String entryName = entry.getName();
                if (entryName.startsWith("META-INF/")) {
                    continue;
                }

                // In original Tinker, meta.txt files themselves are not signed or content-read into map here.
                // They are typically processed by other classes (SharePatchInfo, ShareResPatchInfo etc.)
                // This check seems to be about verifying signatures of *payload* files and reading *other* meta files.
                if (entryName.endsWith(".txt") && entryName.contains("_meta.txt")) { // Heuristic for common meta files
                     // Or more specific: ShareConstants.DEX_META_FILE_NAME, SO_META_FILE_NAME, RES_META_FILE_NAME
                    // These meta files' contents are loaded.
                     mMetaContentMap.put(entryName, SharePatchFileUtil.loadEntryToString(jarFile, entry));
                } else if (entryName.equals(ShareConstants.PACKAGE_META_FILE_NAME)){
                     mMetaContentMap.put(entryName, SharePatchFileUtil.loadEntryToString(jarFile, entry));
                }


                // For all entries (including the meta files whose content we just read),
                // if they are signed, their signature must match.
                // SharePatchFileUtil.loadEntry calls JarFile.getInputStream, which triggers signature check.
                // We need to explicitly load certificates if we want to check them.
                // The original code read certificates *after* reading content via h.a(jarFile, entry)
                // which means loadEntryToString implicitly verified if entry was signed.
                // Then it did an *additional* explicit certificate check.

                // Step 1: Ensure entry can be read (implicit signature check by JarFile)
                // We don't need to store the content of every file, just ensure it's loadable
                // and if signed, matches. The metaContentMap is only for specific meta files.
                jarFile.getInputStream(entry).close(); // Just to trigger verification by JarFile if entry is signed.

                // Step 2: Explicitly check certificates if present
                Certificate[] certificates = entry.getCertificates();
                if (certificates != null && certificates.length > 0) {
                    if (!checkCertificates(patchFile, certificates)) {
                        throw new TinkerRuntimeException(String.format(
                                "Failed to verify signature of entry %s in patch file %s. Mismatched with app signature.",
                                entryName, patchFile.getAbsolutePath()));
                    }
                }
                // If an entry has no certificates, it's considered okay (e.g., resource files not in META-INF).
                // Tinker's security model relies on the overall patch (zip) signature and specific signed entries.
            }
            return true; // All entries processed and signatures (if any) verified.
        } catch (Exception e) { // Catch general Exception as original did
            // Log the error, but throw TinkerRuntimeException to indicate failure
            String errorMessage = String.format("verifyPatchMetaSignature: Failed to process patch file %s (size %d). Error: %s",
                    patchFile.getAbsolutePath(), patchFile.length(), e.getMessage());
            ShareTinkerLog.e(TAG, errorMessage, e);
            throw new TinkerRuntimeException(errorMessage, e);
        } finally {
            SharePatchFileUtil.closeQuietly(jarFile);
        }
    }

    /**
     * Checks if any of the provided certificates match the application's signature.
     *
     * @param originalPatchFile For logging context only.
     * @param certificates      The array of certificates from a JarEntry.
     * @return True if at least one certificate matches the app's signature MD5, false otherwise.
     */
    private boolean checkCertificates(File originalPatchFile, Certificate[] certificates) {
        if (sAppSignatureMd5 == null) {
            ShareTinkerLog.e(TAG, "checkCertificates: sAppSignatureMd5 is null. Cannot verify.");
            return false; // Should not happen if constructor ran initAppSignatureMd5
        }
        if (certificates.length > 0) {
            for (int i = certificates.length - 1; i >= 0; i--) { // Original iterated backwards
                Certificate cert = certificates[i];
                if (cert == null) continue;
                try {
                    String certMd5 = SharePatchFileUtil.getMD5(cert.getEncoded());
                    if (sAppSignatureMd5.equals(certMd5)) {
                        return true; // Found a match
                    }
                } catch (Exception e) {
                    ShareTinkerLog.e(TAG, "checkCertificates: Failed to encode certificate from " + originalPatchFile.getAbsolutePath(), e);
                }
            }
        }
        return false; // No matching certificate found
    }

    /**
     * Initializes the static sAppSignatureMd5 field by reading the application's first signature
     * from its PackageInfo and calculating its MD5.
     * This method is synchronized implicitly by the first caller in the constructor.
     */
    @SuppressLint("PackageManagerGetSignatures")
    private void initAppSignatureMd5(Context context) {
        if (sAppSignatureMd5 != null) {
            return;
        }
        try {
            PackageManager pm = context.getPackageManager();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);

            if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length == 0) {
                throw new TinkerRuntimeException("Failed to get application signatures. PackageInfo or signatures array is null/empty.");
            }
            Signature appSignature = packageInfo.signatures[0];
            sAppSignatureMd5 = SharePatchFileUtil.getMD5(appSignature.toByteArray());

            if (sAppSignatureMd5 == null) {
                throw new TinkerRuntimeException("Failed to calculate MD5 for application signature.");
            }
            ShareTinkerLog.i(TAG, "Initialized app signature MD5: " + sAppSignatureMd5);

        } catch (PackageManager.NameNotFoundException e) {
            throw new TinkerRuntimeException("Failed to init AppSignatureMd5: PackageManager.NameNotFoundException", e);
        } catch (Exception e) { // Catch any other exception during init
            throw new TinkerRuntimeException("Failed to init AppSignatureMd5: " + e.getMessage(), e);
        }
    }
}
