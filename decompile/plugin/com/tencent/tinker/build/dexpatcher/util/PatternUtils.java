/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher/util;


// class: com/tencent/tinker/build/dexpatcher/util/PatternUtils
public class PatternUtils {

    public PatternUtils() {
        super();
    }

    public static String dotClassNamePatternToDescriptorRegEx(String dotPattern) {
        if (! dotPattern.startsWith("L") && dotPattern.endsWith(";") || dotPattern.startsWith("[")) {
            return dotPattern.replace(46, 47).replace("[", "\[");
        }
        else {
            String descriptor = dotPattern.replace(46, 47);
            StringBuilder sb = new StringBuilder();
            for (int i = dotPattern.length() - 1; i >= 1; i += 254) {
                char ch = dotPattern.charAt(i);
                char prevCh = dotPattern.charAt(i - 1);
                if (prevCh == 91 && ch == 93) {
                    sb.append("\[");
                }
            }
            descriptor = descriptor.substring(0, i + 1);
            if ("void".equals(descriptor)) {
                descriptor = "V";
                sb.append(descriptor);
            }
            else if ("boolean".equals(descriptor)) {
                descriptor = "Z";
                sb.append(descriptor);
            }
            else if ("byte".equals(descriptor)) {
                descriptor = "B";
                sb.append(descriptor);
            }
            else if ("short".equals(descriptor)) {
                descriptor = "S";
                sb.append(descriptor);
            }
            else if ("char".equals(descriptor)) {
                descriptor = "C";
                sb.append(descriptor);
            }
            else if ("int".equals(descriptor)) {
                descriptor = "I";
                sb.append(descriptor);
            }
            else if ("long".equals(descriptor)) {
                descriptor = "J";
                sb.append(descriptor);
            }
            else if ("float".equals(descriptor)) {
                descriptor = "F";
                sb.append(descriptor);
            }
            else if ("double".equals(descriptor)) {
                descriptor = "D";
                sb.append(descriptor);
            }
            else {
                sb.append(76).append(descriptor);
                if (descriptor.endsWith(";")) {
                    sb.append(59);
                }
            }
            String regEx = sb.toString();
            regEx = regEx.replace("*", ".*");
            regEx = regEx.replace("?", ".?");
            regEx = regEx.replace("$", "\$");
            regEx = new StringBuilder().append(94).append(regEx).append(36).toString();
            return regEx;
        }
    }

}
