package com.tencent.tinker.loader.shareutil;

import com.tencent.tinker.loader.TinkerRuntimeException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Holds information about resource patches, typically parsed from a "res_meta.txt" file.
 * This includes MD5/CRC of the main resources.arsc, lists of added, deleted, modified files,
 * patterns for matching resource paths, and details about large modified files.
 *
 * Note: This class was translated from a decompiled and obfuscated class `k.java`.
 */
public class ShareResPatchInfo {
    private static final String TAG = "Tinker.ShareResPatchInfo";

    // Meta line prefix for resources_out.zip info
    public static final String RES_ARSC_META_FILENAME = "resources_out.zip";

    // Prefixes for different sections in res_meta.txt
    public static final String PATTERN_SECTION_PREFIX = "pattern";
    public static final String ADD_SECTION_PREFIX = "add";
    public static final String MODIFY_SECTION_PREFIX = "modify";
    public static final String LARGE_MODIFY_SECTION_PREFIX = "large modify";
    public static final String DELETE_SECTION_PREFIX = "delete";
    public static final String STORE_SECTION_PREFIX = "store"; // Files to be stored, not compressed

    public String arscBaseCrc; // From resources_out.zip line, original field 'a'
    public String resArscMd5;  // From resources_out.zip line, original field 'b'

    public ArrayList<String> addedSet = new ArrayList<>();
    public ArrayList<String> deletedSet = new ArrayList<>();
    public ArrayList<String> modifiedSet = new ArrayList<>();
    public HashMap<String, File> storeSet = new HashMap<>(); // Key: resource name, Value: initially null, can be File object later

    public ArrayList<String> largeModifiedSet = new ArrayList<>();
    public HashMap<String, LargeModifiedInfo> largeModifiedFileMap = new HashMap<>();

    public HashSet<Pattern> patterns = new HashSet<>();

    public ShareResPatchInfo() {
        // Default constructor
    }

    /**
     * Parses the complete resource patch metadata from a string content (typically from res_meta.txt).
     *
     * @param content   The string content of the res_meta.txt file.
     * @param patchInfo The ShareResPatchInfo instance to populate.
     */
    public static void parseAllResPatchInfo(String content, ShareResPatchInfo patchInfo) {
        if (content == null || content.isEmpty() || patchInfo == null) {
            return;
        }

        String[] lines = content.split("\n");
        for (int i = 0; i < lines.length; ++i) {
            String line = lines[i];
            if (line == null || line.isEmpty()) {
                continue;
            }

            if (line.startsWith(RES_ARSC_META_FILENAME)) {
                String[] parts = line.split(",", 3);
                if (parts.length >= 3) {
                    patchInfo.arscBaseCrc = parts[1]; // Originally assigned to field 'a'
                    patchInfo.resArscMd5 = parts[2];  // Originally assigned to field 'b'
                }
            } else if (line.startsWith(PATTERN_SECTION_PREFIX + ":")) {
                String[] parts = line.split(":", 2);
                if (parts.length >= 2) {
                    try {
                        int count = Integer.parseInt(parts[1]);
                        for (int j = 0; j < count; ++j) {
                            i++; // Move to next line for pattern string
                            if (i < lines.length) {
                                patchInfo.patterns.add(compileResourcePattern(lines[i]));
                            }
                        }
                    } catch (NumberFormatException e) {
                        ShareTinkerLog.e(TAG, "Error parsing count for " + PATTERN_SECTION_PREFIX, e);
                    }
                }
            } else if (line.startsWith(ADD_SECTION_PREFIX + ":")) {
                parseFileList(lines, i, Integer.parseInt(line.substring(ADD_SECTION_PREFIX.length() + 1)), patchInfo.addedSet);
                i += Integer.parseInt(line.substring(ADD_SECTION_PREFIX.length() + 1)); // Adjust outer loop index
            } else if (line.startsWith(MODIFY_SECTION_PREFIX + ":")) {
                parseFileList(lines, i, Integer.parseInt(line.substring(MODIFY_SECTION_PREFIX.length() + 1)), patchInfo.modifiedSet);
                i += Integer.parseInt(line.substring(MODIFY_SECTION_PREFIX.length() + 1));
            } else if (line.startsWith(LARGE_MODIFY_SECTION_PREFIX + ":")) {
                String[] parts = line.split(":", 2);
                 if (parts.length >= 2) {
                    try {
                        int count = Integer.parseInt(parts[1]);
                        for (int j = 0; j < count; ++j) {
                            i++; // Move to next line for large modify info
                            if (i < lines.length) {
                                String entryLine = lines[i];
                                String[] entryParts = entryLine.split(",", 3);
                                if (entryParts.length >= 3) {
                                    String name = entryParts[0];
                                    LargeModifiedInfo info = new LargeModifiedInfo();
                                    info.md5 = entryParts[1];
                                    info.crc = Long.parseLong(entryParts[2]);
                                    patchInfo.largeModifiedSet.add(name);
                                    patchInfo.largeModifiedFileMap.put(name, info);
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        ShareTinkerLog.e(TAG, "Error parsing count for " + LARGE_MODIFY_SECTION_PREFIX, e);
                    }
                }
            } else if (line.startsWith(DELETE_SECTION_PREFIX + ":")) {
                parseFileList(lines, i, Integer.parseInt(line.substring(DELETE_SECTION_PREFIX.length() + 1)), patchInfo.deletedSet);
                i += Integer.parseInt(line.substring(DELETE_SECTION_PREFIX.length() + 1));
            } else if (line.startsWith(STORE_SECTION_PREFIX + ":")) {
                String[] parts = line.split(":", 2);
                if (parts.length >= 2) {
                     try {
                        int count = Integer.parseInt(parts[1]);
                        for (int j = 0; j < count; ++j) {
                            i++; // Move to next line for store entry
                            if (i < lines.length) {
                                patchInfo.storeSet.put(lines[i], null); // Value is initially null
                            }
                        }
                    } catch (NumberFormatException e) {
                        ShareTinkerLog.e(TAG, "Error parsing count for " + STORE_SECTION_PREFIX, e);
                    }
                }
            }
        }
    }

    private static void parseFileList(String[] allLines, int currentIndex, int count, ArrayList<String> targetList) {
        for (int k = 0; k < count; ++k) {
            int lineIndex = currentIndex + 1 + k;
            if (lineIndex < allLines.length) {
                targetList.add(allLines[lineIndex]);
            }
        }
    }


    /**
     * Parses only the first line of a resource patch metadata string,
     * expecting it to contain the arscBaseCrc and resArscMd5.
     *
     * @param content   The string content (should be just the first line or more).
     * @param patchInfo The ShareResPatchInfo instance to populate.
     */
    public static void parseResPatchInfoFirstLine(String content, ShareResPatchInfo patchInfo) {
        if (content == null || content.isEmpty() || patchInfo == null) {
            return;
        }
        String[] lines = content.split("\n");
        if (lines.length == 0 || lines[0] == null || lines[0].isEmpty()) {
            throw new TinkerRuntimeException("Res meta Corrupted: First line is empty or null: " + content);
        }

        String firstLine = lines[0];
        if (firstLine.startsWith(RES_ARSC_META_FILENAME)) { // Ensure it's the correct line type
             String[] parts = firstLine.split(",", 3);
            if (parts.length >= 3) {
                patchInfo.arscBaseCrc = parts[1];
                patchInfo.resArscMd5 = parts[2];
            } else {
                 throw new TinkerRuntimeException("Res meta Corrupted: Invalid format for resources_out.zip line: " + firstLine);
            }
        } else {
             throw new TinkerRuntimeException("Res meta Corrupted: Expected first line to start with " + RES_ARSC_META_FILENAME + ", found: " + firstLine);
        }
    }

    /**
     * Checks if a given resource path matches any of the patterns in the provided set.
     *
     * @param patterns     Set of compiled regex Patterns.
     * @param resourcePath The resource path string to check.
     * @return True if the path matches any pattern, false otherwise.
     */
    public static boolean checkFileInPattern(HashSet<Pattern> patterns, String resourcePath) {
        if (patterns == null || patterns.isEmpty()) { // Corrected bug: was if(isEmpty()) then iterate
            return false;
        }
        Iterator<Pattern> iterator = patterns.iterator();
        while (iterator.hasNext()) {
            Pattern pattern = iterator.next();
            if (pattern.matcher(resourcePath).matches()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates if the resArscMd5 in the ShareResPatchInfo is a valid 32-character MD5 string.
     *
     * @param patchInfo The ShareResPatchInfo object to validate.
     * @return True if resArscMd5 is valid, false otherwise.
     */
    public static boolean isResArscMd5Valid(ShareResPatchInfo patchInfo) {
        if (patchInfo == null) {
            return false;
        }
        String md5 = patchInfo.resArscMd5;
        return md5 != null && md5.length() == 32;
    }

    /**
     * Compiles a resource path string (potentially with wildcards) into a regex Pattern.
     * Converts '.' to '\.', '?' to '.', and '*' to '.*'.
     */
    private static Pattern compileResourcePattern(String patternString) {
        if (patternString.contains(".")) {
            patternString = patternString.replaceAll("\\.", "\\\\.");
        }
        if (patternString.contains("?")) {
            patternString = patternString.replaceAll("\\?", "\\."); // Single char wildcard
        }
        if (patternString.contains("*")) {
            patternString = patternString.replaceAll("\\*", ".*");   // Zero or more chars wildcard
        }
        return Pattern.compile(patternString);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        // Consistent with original toString labels
        buffer.append("resArscMd5:").append(this.resArscMd5).append("\n");
        buffer.append("arscBaseCrc:").append(this.arscBaseCrc).append("\n");

        for (Pattern pattern : this.patterns) {
            buffer.append("pattern:").append(pattern.pattern()).append("\n");
        }
        for (String entry : this.addedSet) {
            buffer.append("add:").append(entry).append("\n");
        }
        for (String entry : this.modifiedSet) {
            buffer.append("modify:").append(entry).append("\n");
        }
        for (String entry : this.largeModifiedSet) {
            buffer.append("large modify:").append(entry).append("\n");
        }
        for (String entry : this.deletedSet) {
            buffer.append("delete:").append(entry).append("\n");
        }
        for (String entry : this.storeSet.keySet()) {
            buffer.append("store:").append(entry).append("\n");
        }
        return buffer.toString();
    }

    /**
     * Inner class to hold information about large modified files.
     */
    public static class LargeModifiedInfo {
        public String md5;
        public long crc; // Or size, based on context of use
        public File file; // Not part of parsed data, set externally if needed

        public LargeModifiedInfo() {
            // Default constructor
        }
         public LargeModifiedInfo(String md5, long crc) {
            this.md5 = md5;
            this.crc = crc;
        }
    }
}
