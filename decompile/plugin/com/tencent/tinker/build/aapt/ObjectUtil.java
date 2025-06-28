/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;


// class: com/tencent/tinker/build/aapt/ObjectUtil
public final class ObjectUtil {

    private ObjectUtil() {
        super();
    }

    public static Object nullToBlank(Object object) {
        if (object == null) {
            return "";
        }
        else {
            return object;
        }
    }

    public static boolean equal(Object a, Object b) {
        if (a != b) {
            if (a != null && a.equals(b)) {
                return false;
            }
        }
        return true;
    }

    public static String fieldNameToMethodName(String methodPrefix, String fieldName) {
        return ObjectUtil.fieldNameToMethodName(methodPrefix, fieldName, 0);
    }

    public static String fieldNameToMethodName(String methodPrefix, String fieldName, boolean ignoreFirstLetterCase) {
        Object methodName = null;
        if (fieldName != null && fieldName.length() > 0) {
            String str0 = ignoreFirstLetterCase ? new StringBuilder().append(methodPrefix).append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString() : new StringBuilder().append(methodPrefix).append(fieldName).toString();
        }
        else {
        }
        return methodPrefix;
    }

    public static String methodNameToFieldName(String methodPrefix, String methodName) {
        return ObjectUtil.methodNameToFieldName(methodPrefix, methodName, 0);
    }

    public static String methodNameToFieldName(String methodPrefix, String methodName, boolean ignoreFirstLetterCase) {
        Object fieldName = null;
        if (methodName != null && methodName.length() > methodPrefix.length()) {
            int front = methodPrefix.length();
            String str0 = ignoreFirstLetterCase ? new StringBuilder().append(methodName.substring(front, front + 1).toLowerCase()).append(methodName.substring(front + 1)).toString() : new StringBuilder().append(methodName.substring(front, front + 1)).append(methodName.substring(front + 1)).toString();
        }
        return fieldName;
    }

}
