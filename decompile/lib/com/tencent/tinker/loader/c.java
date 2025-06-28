/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import java.io.File;
import java.util.List;
import java.util.Iterator;
import dalvik.system.DelegateLastClassLoader;
import android.content.Context;
import android.content.res.Resources;
import android.annotation.SuppressLint;

// class: com/tencent/tinker/loader/c
public final class c {

    public static ClassLoader a(Application application, ClassLoader loader, File file, boolean bool0, List<File> list) {
        String str0 = new String[]{};
        for (int i0 = 0; i0 < str0.length; i0 += 1) {
            str0[i0] = (File)list.get(i0).getAbsolutePath();
        }
        ClassLoader loaderVar1 = c.a(loader, file, bool0, 1, str0);
        c.a(application, loaderVar1);
        return loaderVar1;
    }

    public static ClassLoader a(Context context, File file, boolean bool0, String[] stringArr0) {
        return c.a(context.getClassLoader(), file, bool0, 0, stringArr0);
    }

    @SuppressLint({"NewApi"})
    private static ClassLoader a(ClassLoader loader, File file, boolean bool0, boolean bool1, String[] stringArr0) {
        Field field = c.a(Class.forName("dalvik.system.BaseDexClassLoader", false, loader), "pathList");
        Object object = field.get(loader);
        StringBuilder builder = new StringBuilder();
        int i0 = stringArr0 != null && stringArr0.length > 0 ? 0 : 1;
        if (i0 != 0) {
            for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                if (i1 > 0) {
                    builder.append(File.pathSeparator);
                }
                builder.append(stringArr0[i1]);
            }
        }
        String str0 = builder.toString();
        Field fieldVar1 = c.a(object.getClass(), "nativeLibraryDirectories");
        Object objectVar1 = null;
        List list = fieldVar1.getType().isArray() ? (List)fieldVar1.get(object) : Arrays.asList((File[])fieldVar1.get(object));
        StringBuilder builderVar1 = new StringBuilder();
        int i3 = 1;
        Iterator iterator = var_11_1.iterator();
        while (iterator.hasNext()) {
            File fileVar1 = (File)iterator.next();
            if (fileVar1 == null) {
                continue;;
            }
            else {
                if (i3 != 0) {
                    i3 = 0;
                }
                else {
                    builderVar1.append(File.pathSeparator);
                }
                builderVar1.append(fileVar1.getAbsolutePath());
                continue;;
            }
        }
        String str1 = builderVar1.toString();
        Object objectVar2 = null;
        if (bool0 && m.a(27, 1)) {
            loader = m.a(31, 1) ? new DelegateLastClassLoader(str0, str1, ClassLoader.getSystemClassLoader()) : new DelegateLastClassLoader(str0, str1, loader);
            Field fieldVar2 = ClassLoader.class.getDeclaredField("parent");
            fieldVar2.setAccessible(true);
            fieldVar2.set(loader, loader);
        }
        else {
            loader = new TinkerClassLoader(str0, file, str1, loader);
        }
        if (bool1 && m.a(26, 1)) {
            c.a(object.getClass(), "definingContext").set(object, var_15_2);
        }
        return var_15_2;
    }

    public static void a(Application application, ClassLoader loader) {
        Thread.currentThread().setContextClassLoader(loader);
        Context context = (Context)c.a(application.getClass(), "mBase").get(application);
        try {
            c.a(context.getClass(), "mClassLoader").set(context, loader);
        }
        catch (Throwable var_3_1) {
        }
        Object object = c.a(context.getClass(), "mPackageInfo").get(context);
        c.a(object.getClass(), "mClassLoader").set(object, loader);
        Resources resources = application.getResources();
        try {
            c.a(resources.getClass(), "mClassLoader").set(resources, loader);
        }
        catch (Throwable var_5_1) {
        }
        try {
            Object objectVar1 = c.a(resources.getClass(), "mDrawableInflater").get(resources);
            if (objectVar1 != null) {
                c.a(objectVar1.getClass(), "mClassLoader").set(objectVar1, loader);
            }
        }
        catch (Throwable var_5_2) {
        }
    }

    private static Field a(Class<?> class, String str0) {
        while (true) {
            try {
                Field field = class.getDeclaredField(str0);
                field.setAccessible(true);
                return field;
            }
            catch (Throwable var_3_1) {
                if (class == Object.class) {
                    throw new NoSuchFieldException(new StringBuilder().append("Cannot find field ").append(str0).append(" in class ").append(class.getName()).append(" and its super classes.").toString());
                }
                else {
                    classVar2 = class.getSuperclass();
                }
            }
        }
    }

}
