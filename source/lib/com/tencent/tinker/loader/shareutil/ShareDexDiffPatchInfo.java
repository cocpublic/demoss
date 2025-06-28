package com.tencent.tinker.loader.shareutil;

import com.tencent.tinker.loader.TinkerRuntimeException; // Assuming k is TinkerRuntimeException

import java.util.ArrayList;
import java.util.List;

/**
 * Represents information for a single dex file that needs to be patched.
 * It includes details about the dex file's path, various MD5 checksums, CRCs, and the dex mode.
 * This information is typically parsed from a meta file like "dex_meta.txt".
 *
 * Note: This class was translated from a decompiled and obfuscated class `d.java`.
 * The field names and structure are mapped to the known fields of Tinker's official ShareDexDiffPatchInfo.
 */
public class ShareDexDiffPatchInfo {

    public final String path;
    public final String patchMd5; // Server-provided MD5 for the patch segment, not the whole patch file.
    public final String dexMd5; // MD5 of the original dex file (in old APK, often from classes.dex in OAT).
    public final String oldDexCrc; // CRC32 of the original dex file.
    public final String newDexCrc; // CRC32 of the new dex file (after patching).
    public final String destMd5InDvm; // Expected MD5 of the patched dex in Dalvik/interpreter mode.
    public final String destMd5InArt; // Expected MD5 of the patched dex in ART mode.
    public final String dexMode; // "jar" or "raw", indicating how the dex is stored/patched.

    public final boolean isJarMode;
    public final String realPatchFileName; // Derived: actual name on disk, might include .jar suffix.

    /**
     * Constructor for ShareDexDiffPatchInfo.
     * The parameter order matches the typical order found in dex_meta.txt lines.
     *
     * @param path          Relative path of the dex file (e.g., "assets/classes.dex").
     * @param patchMd5      MD5 of the patch segment from the server.
     * @param dexMd5        MD5 of the original dex.
     * @param oldDexCrc     CRC of the original dex.
     * @param newDexCrc     CRC of the new dex (after patching).
     * @param destMd5InDvm  Target MD5 for Dalvik.
     * @param destMd5InArt  Target MD5 for ART.
     * @param dexMode       Dex mode, either "jar" or "raw".
     */
    public ShareDexDiffPatchInfo(String path, String patchMd5, String dexMd5, String oldDexCrc,
                                 String newDexCrc, String destMd5InDvm, String destMd5InArt, String dexMode) {
        this.path = path;
        this.patchMd5 = patchMd5;
        this.dexMd5 = dexMd5;
        this.oldDexCrc = oldDexCrc;
        this.newDexCrc = newDexCrc;
        this.destMd5InDvm = destMd5InDvm;
        this.destMd5InArt = destMd5InArt;
        this.dexMode = dexMode;

        if ("jar".equals(dexMode)) {
            this.isJarMode = true;
            // Original logic: h.h(str0) ? str0 : new StringBuilder().append(str0).append(".jar").toString()
            // h.h(str0) likely SharePatchFileUtil.isLegalFile(path) or SharePatchFileUtil.isArchiveFile(path)
            // This means if path already ends with .jar (or other archive extension), use it, else append .jar
            if (SharePatchFileUtil.isArchiveFile(path)) { // Assumption: h.h checks if it's already an archive
                this.realPatchFileName = path;
            } else {
                this.realPatchFileName = path + ".jar";
            }
        } else if ("raw".equals(dexMode)) {
            this.isJarMode = false;
            this.realPatchFileName = path;
        } else {
            throw new TinkerRuntimeException("Unknown dex mode: " + dexMode);
        }
    }

    /**
     * Parses a multi-line string (typically from dex_meta.txt) into a list of ShareDexDiffPatchInfo objects.
     * Each line is expected to be in the format:
     * path,patchMd5,dexMd5,oldDexCrc,newDexCrc,destMd5InDvm,destMd5InArt,dexMode
     *
     * @param content       The string content to parse.
     * @param patchInfoList The list to populate with parsed objects.
     */
    public static void parseDexDiffPatchInfo(String content, List<ShareDexDiffPatchInfo> patchInfoList) {
        if (content == null || content.isEmpty() || patchInfoList == null) {
            return;
        }

        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line == null || line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(",", -1); // Use -1 to keep empty trailing strings
            if (parts.length >= 8) {
                // Order of parts from the line corresponds directly to constructor parameters
                patchInfoList.add(new ShareDexDiffPatchInfo(
                        parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
                        parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[7].trim()
                ));
            }
        }
    }

    /**
     * Validates a ShareDexDiffPatchInfo object.
     * The original validation logic was: path must be empty/null, and a selected MD5
     * (dexMd5 on ART, oldDexCrc on Dalvik - though this seems reversed for MD5 length check)
     * must be a 32-character string. This suggests a specific check for "root" dex files
     * and primarily validating the dexMd5.
     *
     * @param dexInfo The object to validate.
     * @return True if validation passes, false otherwise.
     */
    public static boolean isDexInfoValid(ShareDexDiffPatchInfo dexInfo) {
        if (dexInfo == null) {
            return false;
        }

        String path = dexInfo.path;
        String md5ToCheck;

        // Original: str1 = m.a() ? d.b : d.c;
        // m.a() is ShareTinkerInternals.isVmArt()
        // d.b is dexMd5 (original field b, from line part 3)
        // d.c is oldDexCrc (original field c, from line part 4)
        if (ShareTinkerInternals.isVmArt()) {
            md5ToCheck = dexInfo.dexMd5; // On ART, check the full dexMd5
        } else {
            md5ToCheck = dexInfo.oldDexCrc; // On Dalvik, it was checking oldDexCrc for 32-char length
                                         // This is unusual, CRCs are not 32-char MD5s.
                                         // This might indicate a misinterpretation or a specific edge case
                                         // in the original code, or that `oldDexCrc` field could sometimes hold an MD5.
                                         // For robust validation, one might expect to always check dexMd5.
                                         // Or, if Dalvik mode truly implies a different check, it needs to be specific.
                                         // Given the 32-char check, it strongly implies an MD5 is expected.
                                         // It's possible that on non-ART, the `oldDexCrc` field might actually store an MD5,
                                         // or the check against `oldDexCrc` is flawed if it's always a numeric CRC.
                                         // Sticking to the literal interpretation for now, but flagging it:
                                         // If ShareTinkerInternals.isVmArt() is false, it checks oldDexCrc.length() == 32
        }

        boolean pathIsEmpty = (path == null || path.isEmpty());
        boolean md5LengthValid = (md5ToCheck != null && md5ToCheck.length() == 32);

        // Original logic: return !((path != null && path.length() > 0) || md5ToCheck == null || md5ToCheck.length() != 32);
        // This is equivalent to: (path == null || path.isEmpty()) && (md5ToCheck != null && md5ToCheck.length() == 32);
        return pathIsEmpty && md5LengthValid;
    }

    @Override
    public String toString() {
        // Original toString: a,g,b,c,d,e,f,h
        // Corresponds to fields: path, patchMd5, dexMd5, oldDexCrc, destMd5InDvm, destMd5InArt, newDexCrc, dexMode
        // This order matches the typical order of fields in the official ShareDexDiffPatchInfo.
        StringBuffer buffer = new StringBuffer();
        buffer.append(path);
        buffer.append(",");
        buffer.append(patchMd5);
        buffer.append(",");
        buffer.append(dexMd5);
        buffer.append(",");
        buffer.append(oldDexCrc);
        buffer.append(",");
        buffer.append(destMd5InDvm);
        buffer.append(",");
        buffer.append(destMd5InArt);
        buffer.append(",");
        buffer.append(newDexCrc); // Swapped with destMd5InArt/Dvm compared to typical file line order
        buffer.append(",");
        buffer.append(dexMode);
        return buffer.toString();
    }
}
