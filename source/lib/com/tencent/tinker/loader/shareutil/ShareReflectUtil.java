package com.tencent.tinker.loader.shareutil;

import android.content.Context;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Utility class for common reflection operations.
 * Provides methods to find fields, methods, and constructors, and to manipulate arrays via reflection.
 * Handles class hierarchy traversal and makes members accessible.
 *
 * Note: This class was translated from a decompiled and obfuscated class `j.java`.
 */
public final class ShareReflectUtil {

    private ShareReflectUtil() {
        // Utility class
    }

    /**
     * Finds a field in the given object's class hierarchy.
     *
     * @param instance  The object to search in.
     * @param fieldName The name of the field.
     * @return The accessible {@link Field} object.
     * @throws NoSuchFieldException If the field is not found.
     */
    public static Field findField(Object instance, String fieldName) throws NoSuchFieldException {
        if (instance == null) {
            throw new NoSuchFieldException("Instance cannot be null when finding field " + fieldName);
        }
        return findField(instance.getClass(), fieldName);
    }

    /**
     * Finds a field in the given class hierarchy.
     *
     * @param clazz     The class to search in.
     * @param fieldName The name of the field.
     * @return The accessible {@link Field} object.
     * @throws NoSuchFieldException If the field is not found.
     */
    public static Field findField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            try {
                Field field = currentClass.getDeclaredField(fieldName);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                // Field not in current class, try superclass
                currentClass = currentClass.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field " + fieldName + " not found in " + clazz.getName() + " and its superclasses.");
    }

    /**
     * Finds a method in the given object's class hierarchy.
     *
     * @param instance       The object to search in.
     * @param methodName     The name of the method.
     * @param parameterTypes The parameter types of the method (varargs).
     * @return The accessible {@link Method} object.
     * @throws NoSuchMethodException If the method is not found.
     */
    public static Method findMethod(Object instance, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        if (instance == null) {
            throw new NoSuchMethodException("Instance cannot be null when finding method " + methodName);
        }
        return findMethod(instance.getClass(), methodName, parameterTypes);
    }

    /**
     * Finds a method in the given class hierarchy.
     *
     * @param clazz          The class to search in.
     * @param methodName     The name of the method.
     * @param parameterTypes The parameter types of the method (varargs).
     * @return The accessible {@link Method} object.
     * @throws NoSuchMethodException If the method is not found.
     */
    public static Method findMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            try {
                Method method = currentClass.getDeclaredMethod(methodName, parameterTypes);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                return method;
            } catch (NoSuchMethodException e) {
                // Method not in current class, try superclass
                currentClass = currentClass.getSuperclass();
            }
        }
        throw new NoSuchMethodException("Method " + methodName + " with parameters " +
                Arrays.toString(parameterTypes) + " not found in " + clazz.getName() + " and its superclasses.");
    }

    /**
     * Finds a constructor in the given object's class.
     *
     * @param instance       The object whose class constructor is to be found.
     * @param parameterTypes The parameter types of the constructor (varargs).
     * @return The accessible {@link Constructor} object.
     * @throws NoSuchMethodException If the constructor is not found.
     */
    public static Constructor<?> findConstructor(Object instance, Class<?>... parameterTypes) throws NoSuchMethodException {
        if (instance == null) {
            throw new NoSuchMethodException("Instance cannot be null when finding constructor");
        }
        return findConstructor(instance.getClass(), parameterTypes);
    }

    /**
     * Finds a constructor in the given class.
     * Note: Constructors are not inherited, so only the declared constructors of the given class are searched.
     * The original decompiled code iterated through superclasses, which is incorrect for constructors.
     *
     * @param clazz          The class to search in.
     * @param parameterTypes The parameter types of the constructor (varargs).
     * @return The accessible {@link Constructor} object.
     * @throws NoSuchMethodException If the constructor is not found.
     */
    public static Constructor<?> findConstructor(Class<?> clazz, Class<?>... parameterTypes) throws NoSuchMethodException {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor;
        } catch (NoSuchMethodException e) {
            // Constructor not found in this class
            throw new NoSuchMethodException("Constructor with parameters " + Arrays.toString(parameterTypes) +
                    " not found in " + clazz.getName());
        }
    }

    /**
     * Prepends elements to an array field of an object.
     * The original decompiled code prepended elements. This method is named `expandFieldArray`
     * as per Tinker's typical naming for such an operation.
     *
     * @param instance      The object whose array field is to be modified.
     * @param fieldName     The name of the array field.
     * @param newElements   The elements to prepend to the array.
     * @throws NoSuchFieldException  If the field is not found.
     * @throws IllegalAccessException If the field cannot be accessed.
     * @throws IllegalArgumentException If the field is not an array or types are incompatible.
     */
    public static void expandFieldArray(Object instance, String fieldName, Object[] newElements)
            throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        Field field = findField(instance, fieldName);
        Object originalArray = field.get(instance);
        if (!originalArray.getClass().isArray()) {
            throw new IllegalArgumentException("Field " + fieldName + " is not an array.");
        }

        Object[] combinedArray = (Object[]) Array.newInstance(
                originalArray.getClass().getComponentType(),
                newElements.length + Array.getLength(originalArray)
        );

        System.arraycopy(newElements, 0, combinedArray, 0, newElements.length);
        System.arraycopy(originalArray, 0, combinedArray, newElements.length, Array.getLength(originalArray));

        field.set(instance, combinedArray);
    }

    /**
     * Removes a specified number of elements from the front of an array field of an object.
     *
     * @param instance      The object whose array field is to be modified.
     * @param fieldName     The name of the array field.
     * @param countToRemove The number of elements to remove from the beginning of the array.
     * @throws NoSuchFieldException  If the field is not found.
     * @throws IllegalAccessException If the field cannot be accessed.
     * @throws IllegalArgumentException If the field is not an array or countToRemove is invalid.
     */
    public static void reduceFieldArray(Object instance, String fieldName, int countToRemove)
            throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException {
        if (countToRemove <= 0) {
            return;
        }
        Field field = findField(instance, fieldName);
        Object originalArray = field.get(instance);
        if (!originalArray.getClass().isArray()) {
            throw new IllegalArgumentException("Field " + fieldName + " is not an array.");
        }

        int originalLength = Array.getLength(originalArray);
        if (countToRemove > originalLength) {
            // Or throw IllegalArgumentException, or set to empty array.
            // Original code implies if newLength <=0, nothing happens, but that's after calculating newLength.
            // If countToRemove > originalLength, newLength would be negative.
            // Let's set to an empty array of the same component type.
             Object[] newArray = (Object[]) Array.newInstance(originalArray.getClass().getComponentType(), 0);
             field.set(instance, newArray);
            return;
        }

        int newLength = originalLength - countToRemove;
        if (newLength <= 0) { // Decompiled code had this check, implies if all or more are removed, result is empty or no change.
            Object[] newArray = (Object[]) Array.newInstance(originalArray.getClass().getComponentType(), 0);
            field.set(instance, newArray);
            return;
        }

        Object[] newArray = (Object[]) Array.newInstance(originalArray.getClass().getComponentType(), newLength);
        System.arraycopy(originalArray, countToRemove, newArray, 0, newLength);
        field.set(instance, newArray);
    }


    /**
     * Retrieves the current ActivityThread instance.
     *
     * @param context The application context.
     * @param activityThreadClass The ActivityThread class, or null to use "android.app.ActivityThread".
     * @return The ActivityThread instance, or null if not found.
     */
    public static Object getActivityThread(Context context, Class<?> activityThreadClass) {
        try {
            if (activityThreadClass == null) {
                activityThreadClass = Class.forName("android.app.ActivityThread");
            }
            Method currentActivityThreadMethod = activityThreadClass.getMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            Object activityThread = currentActivityThreadMethod.invoke(null);

            if (activityThread == null && context != null) {
                // Fallback for some environments if currentActivityThread() returns null
                Field mLoadedApkField = findField(context.getClass(), "mLoadedApk"); // context.getClass() usually ContextImpl
                Object loadedApk = mLoadedApkField.get(context);

                Field mActivityThreadField = findField(loadedApk.getClass(), "mActivityThread");
                activityThread = mActivityThreadField.get(loadedApk);
            }
            return activityThread;
        } catch (Throwable t) {
            ShareTinkerLog.e("ShareReflectUtil", "getActivityThread exception: " + t.getMessage());
            return null;
        }
    }

    /**
     * Gets the value of a static int field from a class.
     *
     * @param clazz        The class containing the static field.
     * @param fieldName    The name of the static int field.
     * @param defaultValue The value to return if the field is not found or an error occurs.
     * @return The value of the static int field, or the defaultValue.
     */
    public static int getStaticIntField(Class<?> clazz, String fieldName, int defaultValue) {
        try {
            Field field = findField(clazz, fieldName); // findField already makes it accessible
            return field.getInt(null); // For static fields, pass null as the object
        } catch (Throwable t) {
            // Catching Throwable broadly as original did.
            ShareTinkerLog.e("ShareReflectUtil", "getStaticIntField failed for " + clazz.getName() + "#" + fieldName, t);
            return defaultValue;
        }
    }
}
