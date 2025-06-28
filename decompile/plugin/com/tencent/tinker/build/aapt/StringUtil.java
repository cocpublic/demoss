/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// class: com/tencent/tinker/build/aapt/StringUtil
public final class StringUtil {
    final public static String BLANK;
    final public static String SPACE;
    final public static String NULL;
    final public static String CRLF_STRING;
    final public static byte CR;
    final public static byte LF;
    final public static byte CRLF;
    final private static String METCH_PATTERN_REGEX;
    final private static String METCH_PATTERN;
    final private static String METCH_PATTERN_REPLACEMENT;
    final private static String ZERO;

    private StringUtil() {
        super();
    }

    public static String trim(String string) {
        Object result = null;
        String str0 = string == null ? string.trim() : "";
        return var_1_0;
    }

    public static String nullToBlank(String string) {
        if (string == null) {
            return "";
        }
        else {
            return string;
        }
    }

    public static String[] nullToBlank(String[] stringArray) {
        if (stringArray == null) {
            String str0 = new String[]{};
        }
        return stringArray;
    }

    public static boolean isBlank(String string) {
        int result = false;
        if (string != null) {
            int strLen = string.length();
            if (string.length() == 0) {
                for (int i = 0; i < strLen; i += 1) {
                    if (Character.isWhitespace(string.charAt(i))) {
                        result = 0;
                        break;;
                    }
                    else {
                    }
                }
            }
        }
        result = 1;
        return result;
    }

    public static boolean isNotBlank(String string) {
        if (StringUtil.isBlank(string)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static String[] compareString(String[] stringArray1, String[] stringArray2) {
        Object differentString = null;
        if (stringArray1 != null && stringArray2 != null) {
            ArrayList list = new ArrayList();
            for (int i = 0; i < stringArray1.length; i += 1) {
                int sign = 0;
                for (int j = 0; j < stringArray2.length; j += 1) {
                    if (stringArray1[i].equals(stringArray2[j])) {
                        sign = 1;
                        break;;
                    }
                    else {
                    }
                }
                if (sign == 0) {
                    list.add(stringArray1[i]);
                }
            }
            String str0 = new String[]{};
            String[] stringArr0 = (String[])list.toArray(str0);
        }
        return differentString;
    }

    public static boolean isMatchPattern(String string, String patternString) {
        int result = false;
        if (string != null && patternString != null) {
            if (patternString.indexOf("*") >= 0) {
                String matchPattern = new StringBuilder().append("^").append(patternString.replaceAll("[\*]+", "[\\S|\\s]*")).append("$").toString();
                boolean bool0 = StringUtil.isMatchRegex(string, matchPattern);
            }
            else if (string.equals(patternString)) {
                result = 1;
            }
        }
        return result;
    }

    public static boolean isMatchRegex(String string, String regex) {
        int result = false;
        if (string != null && regex != null) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(string);
            boolean bool0 = matcher.find();
        }
        return result;
    }

    public static List<String> parseRegexGroup(String string, String regex) {
        Object groupList = null;
        if (string != null && regex != null) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(string);
            int groupCount = matcher.groupCount();
            ArrayList list = new ArrayList();
            for (int count = 1; matcher.find() && count <= groupCount; count += 1) {
                list.add(matcher.group(count));
            }
        }
        return groupList;
    }

    public static List<String> parseStringGroup(String string, String regex, String firstRegex, String firstRegexReplace, int v_4) {
        Object list = null;
        if (string != null) {
            list = new ArrayList();
            int lastRegexLength = v_4 < 0 ? v_4 : 0;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(string);
            Object group = null;
            int start = 0;
            while (matcher.find(start)) {
                start = matcher.end();
                String str2 = matcher.group();
                str2 = str2.replaceFirst(firstRegex, firstRegexReplace);
                str2 = str2.substring(0, str2.length() - lastRegexLength);
                list.add(str2);
            }
        }
        return list;
    }

    public static String byteToHexString(byte[] byteArray) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < byteArray.length; i += 1) {
            int byteCode = byteArray[i] & 255;
            if (byteCode < 16) {
                builder.append(0);
            }
            builder.append(Integer.toHexString(byteCode));
        }
        return builder.toString();
    }

    public static byte[] hexStringToByte(String source) {
        Object bytes = null;
        if (source != null) {
            byte[] byteArr0 = new byte[]{};
            for (int i = 0; i < byteArr0.length; i += 1) {
                byteArr0[i] = (byte)Integer.parseInt(source.substring(i * 2, i + 1 * 2), 16);
            }
        }
        return bytes;
    }

    public static String fillZero(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i += 1) {
            stringBuilder.append("0");
        }
        return stringBuilder.toString();
    }

    public static int stringMod(String string, int mod) {
        int v_8 = 0;
        if (string != null) {
            v_8 = string.hashCode();
        }
        return v_8 < 0 ? v_8 : v_8 % mod > 0 ? 1 : mod;
    }

    static  {
        StringUtil.CRLF = new byte[]{13, 10};
    }

}
