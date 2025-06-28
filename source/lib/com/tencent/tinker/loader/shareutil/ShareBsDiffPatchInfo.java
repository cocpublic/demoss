package com.tencent.tinker.loader.shareutil;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents information about a file that needs to be patched using BsDiff.
 * It typically includes the file's path, name, original MD5, the MD5 of the patch file,
 * and the MD5 of the file after patching.
 *
 * Parsed from a string format like "path,name,oldMd5,patchFileMd5,targetMd5InApk".
 *
 * Note: This class was translated from a decompiled and obfuscated class `b.java`.
 */
public class ShareBsDiffPatchInfo {

    /**
     * Relative path of the file to be patched.
     * (Original field: a, from line part 1)
     */
    public final String path;

    /**
     * The MD5 checksum of the original file before patching.
     * (Original field: b, from line part 3)
     */
    public final String oldMd5;

    /**
     * The MD5 checksum of the bsdiff patch file itself.
     * (Original field: c, from line part 4)
     */
    public final String patchFileMd5;

    /**
     * The MD5 checksum of the file after applying the patch (i.e., the target MD5 in the new APK).
     * (Original field: d, from line part 5)
     */
    public final String targetMd5InApk;

    /**
     * The name of the file.
     * (Original field: e, from line part 2)
     */
    public final String name;

    public ShareBsDiffPatchInfo(String path, String oldMd5, String name, String patchFileMd5, String targetMd5InApk) {
        this.path = path;
        this.oldMd5 = oldMd5;
        this.name = name;
        this.patchFileMd5 = patchFileMd5;
        this.targetMd5InApk = targetMd5InApk;
    }

    /**
     * Parses a multi-line string into a list of {@link ShareBsDiffPatchInfo} objects.
     * Each line is expected to be in the format "path,name,oldMd5,patchFileMd5,targetMd5InApk".
     *
     * @param content The string content to parse, with entries separated by newlines.
     * @param patchInfoList The list to populate with parsed objects. Can be null.
     */
    public static void parseBsDiffPatchInfo(String content, List<ShareBsDiffPatchInfo> patchInfoList) {
        if (content == null || content.isEmpty() || patchInfoList == null) {
            return;
        }

        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line == null || line.isEmpty()) {
                continue;
            }
            // Original split was (",", 5)
            String[] parts = line.split(",", -1); // Split to get all parts
            if (parts.length >= 5) {
                String pPath = parts[0].trim();
                String pName = parts[1].trim();
                String pOldMd5 = parts[2].trim();
                String pPatchFileMd5 = parts[3].trim();
                String pTargetMd5 = parts[4].trim();

                // Original constructor mapping from parser: new b(str2, str4, str3, str5, str6)
                // str2 (line part 1) -> param1 (this.a -> path)
                // str4 (line part 3) -> param2 (this.b -> oldMd5)
                // str3 (line part 2) -> param3 (this.e -> name)
                // str5 (line part 4) -> param4 (this.c -> patchFileMd5)
                // str6 (line part 5) -> param5 (this.d -> targetMd5InApk)
                // So, new ShareBsDiffPatchInfo(pPath, pOldMd5, pName, pPatchFileMd5, pTargetMd5)
                patchInfoList.add(new ShareBsDiffPatchInfo(pPath, pOldMd5, pName, pPatchFileMd5, pTargetMd5));
            }
        }
    }

    /**
     * Validates a {@link ShareBsDiffPatchInfo} object.
     * This specific validation checks if the 'path' is null or empty and if the 'oldMd5'
     * is a 32-character string. This might be for "simple" or "root" file entries.
     *
     * @param patchInfo The object to validate.
     * @return {@code true} if the path is empty and oldMd5 is valid (32 chars), {@code false} otherwise.
     */
    public static boolean isSimpleValidationValid(ShareBsDiffPatchInfo patchInfo) {
        if (patchInfo == null) {
            return false;
        }
        // Original validation was on fields 'a' (path) and 'b' (oldMd5)
        boolean pathIsEmpty = (patchInfo.path == null || patchInfo.path.isEmpty());
        boolean md5IsValid = (patchInfo.oldMd5 != null && patchInfo.oldMd5.length() == 32);

        return pathIsEmpty && md5IsValid;
    }

    @Override
    public String toString() {
        // Original: this.a + "," + this.e + "," + this.b + "," + this.c + "," + this.d
        // Maps to:   path + "," + name + "," + oldMd5 + "," + patchFileMd5 + "," + targetMd5InApk
        StringBuffer buffer = new StringBuffer();
        buffer.append(path == null ? "" : path);
        buffer.append(",");
        buffer.append(name == null ? "" : name);
        buffer.append(",");
        buffer.append(oldMd5 == null ? "" : oldMd5);
        buffer.append(",");
        buffer.append(patchFileMd5 == null ? "" : patchFileMd5);
        buffer.append(",");
        buffer.append(targetMd5InApk == null ? "" : targetMd5InApk);
        return buffer.toString();
    }
}
