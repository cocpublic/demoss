/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/res/aapt;

import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// class: com/tencent/rfix/build/res/aapt/FileUtil
public final class FileUtil {

    private FileUtil() {
        super();
    }

    public static boolean checkFileInPattern(HashSet<String> patterns, String key) {
        if (patterns.isEmpty()) {
            return false;
        }
        else {
            Iterator iterator = patterns.iterator();
            while (iterator.hasNext()) {
                String pattern = (String)iterator.next();
                Pattern p = Pattern.compile(FileUtil.convertToPatternString(pattern));
                if (p.matcher(key).matches()) {
                    return true;
                }
                else {
                    continue;;
                }
            }
            return false;
        }
    }

    public static String convertToPatternString(String input) {
        if (input.contains(".")) {
            input = input.replaceAll("\.", "\\.");
        }
        if (input.contains("?")) {
            input = input.replaceAll("\?", "\.");
        }
        if (input.contains("*")) {
            input = input.replace("*", ".*");
        }
        return input;
    }

}
