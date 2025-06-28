/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import java.util.List;

// class: com/tencent/tinker/loader/shareutil/j
public class j {

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

    public static Field a(Class<?> class, String str0) {
        while (class != null) {
            try {
                Field field = class.getDeclaredField(str0);
                if (field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            }
            catch (NoSuchFieldException var_3_1) {
                classVar2 = class.getSuperclass();
                continue;;
            }
        }
        throw new NoSuchFieldException(new StringBuilder().append("Field ").append(str0).append(" not found in ").append(class).toString());
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

    public static Method a(Class<?> class, String str0, Class<?>[] classArr0) {
        while (class != null) {
            try {
                Method method = class.getDeclaredMethod(str0, classArr0);
                if (method.isAccessible()) {
                    method.setAccessible(true);
                }
                return method;
            }
            catch (NoSuchMethodException var_3_1) {
                class = class.getSuperclass();
                continue;;
            }
        }
        throw new NoSuchMethodException(new StringBuilder().append("Method ").append(str0).append(" with parameters ").append(Arrays.asList(classArr0)).append(" not found in ").append(class).toString());
    }

    public static Constructor<?> a(Object object, Class<?>[] classArr0) {
        return j.a(object.getClass(), classArr0);
    }

    public static Constructor<?> a(Class<?> class, Class<?>[] classArr0) {
        while (class != null) {
            try {
                Constructor constructor = class.getDeclaredConstructor(classArr0);
                if (constructor.isAccessible()) {
                    constructor.setAccessible(true);
                }
                return constructor;
            }
            catch (NoSuchMethodException var_3_1) {
                classVar2 = class.getSuperclass();
                continue;;
            }
        }
        throw new NoSuchMethodException(new StringBuilder().append("Constructor with parameters ").append(Arrays.asList(classArr0)).append(" not found in ").append(class).toString());
    }

    public static void a(Object object, String str0, Object[] objectArr0) {
        Field field = j.a(object, str0);
        Object[] objectArr0Var1 = (Object[])field.get(object);
        Object[] objectArr0Var2 = (Object[])Array.newInstance(objectArr0Var1.getClass().getComponentType(), objectArr0Var1.length + objectArr0.length);
        System.arraycopy(objectArr0, 0, objectArr0Var2, 0, objectArr0.length);
        System.arraycopy(objectArr0Var1, 0, objectArr0Var2, objectArr0.length, objectArr0Var1.length);
        field.set(object, objectArr0Var2);
    }

    public static void a(Object object, String str0, int i0) {
        if (i0 <= 0) {
        }
        else {
            Field field = j.a(object, str0);
            Object[] objectArr0 = (Object[])field.get(object);
            int i1 = objectArr0.length - i0;
            if (i1 <= 0) {
            }
            else {
                Object[] objectArr0Var1 = (Object[])Array.newInstance(objectArr0.getClass().getComponentType(), i1);
                System.arraycopy(objectArr0, i0, objectArr0Var1, 0, i1);
                field.set(object, objectArr0Var1);
            }
        }
    }

    public static Object a(Context context, Class<?> class) {
        try {
            if (class == null) {
                class = Class.forName("android.app.ActivityThread");
            }
            Method method = class.getMethod("currentActivityThread", new Class[]{});
            method.setAccessible(true);
            Object objectVar2 = method.invoke(null, new Object[]{});
            if (objectVar2 == null && context != null) {
                Field field = context.getClass().getField("mLoadedApk");
                field.setAccessible(true);
                Object objectVar1 = field.get(context);
                Field fieldVar1 = objectVar1.getClass().getDeclaredField("mActivityThread");
                fieldVar1.setAccessible(true);
                objectVar2 = fieldVar1.get(objectVar1);
            }
            return objectVar2;
        }
        catch (Throwable var_2_1) {
            return null;
        }
    }

    public static int a(Class<?> class, String str0, int i0) {
        try {
            Field field = j.a(class, str0);
            return field.getInt(null);
        }
        catch (Throwable var_3_1) {
            return i0;
        }
    }

}
