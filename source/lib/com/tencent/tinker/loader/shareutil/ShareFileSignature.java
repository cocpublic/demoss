package com.tencent.tinker.loader.shareutil;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents signature information for a file, typically including a path, a name/identifier, and an MD5 checksum.
 * This class seems to be parsed from a string format like "path,name,md5".
 *
 * Note: This class was translated from a decompiled and obfuscated class `a.java`.
 * The specific meaning of 'path' and 'name' might vary based on context of use.
 * The validation method `isSimpleFileSignatureValid` suggests a specific use case
 * where the 'path' component is expected to be empty.
 */
public class ShareFileSignature {
    /**
     * The path component of the file signature. Corresponds to the first part of the parsed CSV line.
     */
    public final String path;

    /**
     * The name or identifier component. Corresponds to the second part of the parsed CSV line.
     */
    public final String name;

    /**
     * The MD5 checksum. Corresponds to the third part of the parsed CSV line.
     */
    public final String md5;

    public ShareFileSignature(String name, String path, String md5) {
        this.name = name; // Was 'a' in original, received str0 (line part 2)
        this.path = path; // Was 'b' in original, received str1 (line part 1)
        this.md5 = md5;   // Was 'c' in original, received str2 (line part 3)
    }

    /**
     * Parses a multi-line string into a list of {@link ShareFileSignature} objects.
     * Each line in the input string is expected to be in the format "path,name,md5".
     *
     * @param content The string content to parse, with entries separated by newlines.
     * @param signatures The list to populate with parsed signatures. Can be null.
     */
    public static void parseFileSignatures(String content, List<ShareFileSignature> signatures) {
        if (content == null || signatures == null) {
            return;
        }
        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line == null || line.length() <= 0) {
                continue;
            }
            // Original split was (",", 4), but only first 3 parts were used.
            // Using split(",", 3) is safer if lines might have more than 2 commas
            // but we only care about the first three segments.
            // However, to match original behavior if a line was "p,n,m,extra",
            // split(",", 4) would give ["p","n","m","extra"]
            // split(",", 3) would give ["p","n","m,extra"]
            // Since original used stringArr0Var2[0],[1],[2], it implies it expects exactly 3 fields or more,
            // and ignores fields beyond the third if split by 4.
            // If a line is "p,n", length is 2, fails.
            // If a line is "p,n,m", length is 3, ok.
            String[] parts = line.split(",", -1); // Split to get all parts, even if empty
            if (parts.length >= 3) {
                String parsedPath = parts[0].trim();
                String parsedName = parts[1].trim();
                String parsedMd5 = parts[2].trim();
                // Original constructor mapping: new a(parsedName, parsedPath, parsedMd5)
                signatures.add(new ShareFileSignature(parsedName, parsedPath, parsedMd5));
            }
        }
    }

    /**
     * Validates a {@link ShareFileSignature} object, specifically checking if its 'path'
     * is null or empty and if its 'md5' is a 32-character string.
     *
     * @param signature The signature object to validate.
     * @return {@code true} if the path is empty and MD5 is valid (32 chars), {@code false} otherwise.
     */
    public static boolean isSimpleFileSignatureValid(ShareFileSignature signature) {
        if (signature == null) {
            return false;
        }
        // Original logic:
        // String str0 = a.b; (path)
        // String str1 = a.c; (md5)
        // if (str0 != null && str0.length() > 0 || str1 != null || str1.length() != 32) { return false; } else { return true; }
        // This translates to: return !((path != null && path.length() > 0) || md5 == null || md5.length() != 32);
        // Which simplifies to: (path == null || path.isEmpty()) && (md5 != null && md5.length() == 32);

        boolean pathIsEmpty = (signature.path == null || signature.path.isEmpty());
        boolean md5IsValid = (signature.md5 != null && signature.md5.length() == 32);

        return pathIsEmpty && md5IsValid;
    }

    @Override
    public String toString() {
        // Original: this.b + "," + this.a + "," + this.c
        return (path == null ? "" : path) + "," +
               (name == null ? "" : name) + "," +
               (md5 == null ? "" : md5);
    }
}
