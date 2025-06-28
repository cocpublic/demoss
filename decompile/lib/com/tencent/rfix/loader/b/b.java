/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/b;

import java.util.List;

// class: com/tencent/rfix/loader/b/b
public class b {

    public static Field a(Object object, String str0) {
        Class classVar1 = object.getClass();
        while (classVar1 != null) {
            try {
                Field field = classVar1.getDeclaredField(str0);
                if (field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            }
            catch (NoSuchFieldException var_3_1) {
                classVar1 = classVar1.getSuperclass();
                continue;;
            }
        }
        throw new NoSuchFieldException(new StringBuilder().append("Field ").append(str0).append(" not found in ").append(object.getClass()).toString());
    }

    public static Method a(Object object, String str0, Class<?>[] classArr0) {
        Class classVar1 = object.getClass();
        while (classVar1 != null) {
            try {
                Method method = classVar1.getDeclaredMethod(str0, classArr0);
                if (method.isAccessible()) {
                    method.setAccessible(true);
                }
                return method;
            }
            catch (NoSuchMethodException var_4_1) {
                classVar1 = classVar1.getSuperclass();
                continue;;
            }
        }
        throw new NoSuchMethodException(new StringBuilder().append("Method ").append(str0).append(" with parameters ").append(Arrays.asList(classArr0)).append(" not found in ").append(object.getClass()).toString());
    }

}
