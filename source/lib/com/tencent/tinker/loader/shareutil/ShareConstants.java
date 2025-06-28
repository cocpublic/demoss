package com.tencent.tinker.loader.shareutil;

import java.util.regex.Pattern;

/**
 * Contains shared constants used throughout the Tinker framework.
 * This file will be populated with more constants as other related decompiled
 * classes are processed.
 *
 * Note: This class was initially translated from a decompiled and obfuscated class `c.java`,
 * which only contained the CLASS_N_PATTERN.
 */
public class ShareConstants {

    /**
     * Pattern to identify secondary dex files (e.g., classes2.dex, classes3.dex, ... classesN.dex).
     * It can also optionally match if these dex files are within a .jar extension (e.g., classes2.dex.jar).
     * The regex is: {@code classes(?:[2-9]?|[1-9][0-9]+)\.dex(\.jar)?}
     * Matches:
     * - classes2.dex ... classes9.dex
     * - classes10.dex ... classesN.dex (where N >= 10)
     * - And their .jar equivalents like classes2.dex.jar.
     * Note: This pattern does not strictly match "classes.dex" (the primary dex) without a number.
     */
    public static final Pattern CLASS_N_PATTERN;

    static {
        CLASS_N_PATTERN = Pattern.compile("classes(?:[2-9]?|[1-9][0-9]+)\\.dex(\\.jar)?");
    }

    // Private constructor to prevent instantiation, as this is a utility class for constants.
    private ShareConstants() {
        // This class should not be instantiated.
    }

    // TODO: Add other constants from Tinker's ShareConstants.java as they are identified
    // from other decompiled files or as needed. Some common constants include:
    //    public static final String TINKER_ID = "TINKER_ID";
    //    public static final String TINKER_OLD_APPLICATION = "TINKER_OLD_APPLICATION";
    //    public static final String PATCH_META_FILE = "patch.info";
    //    public static final String PATCH_INFO_LOCK_FILE = "patch.lock";
    //    public static final String DEX_SUFFIX = ".dex";
    //    public static final String APK_SUFFIX = ".apk";
    //    public static final String JAR_SUFFIX = ".jar";
    //    public static final String SO_SUFFIX = ".so";
    //    public static final int MAX_PATCH_ATTEMPTS = 3;
    //    ... and many more related to paths, file names, keys, etc.
}
