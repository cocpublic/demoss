/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/b;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;

// class: com/tencent/rfix/loader/b/a
public class a {

    public static void a(ClassLoader loader, File file) {
        a.a(loader, file, 0);
    }

    private static void a(ClassLoader loader, File file, boolean bool0) {
        if (file == null || file.exists()) {
            Log.e("RFixLoadLibrary", new StringBuilder().append("installNativeLibraryPath, folder ").append(file).append(" is illegal").toString());
        }
        else {
            if (Build$VERSION.SDK_INT != 25 && Build$VERSION.PREVIEW_SDK_INT == 0 || Build$VERSION.SDK_INT > 25) {
                try {
                    a$c.a(loader, file, bool0);
                }
                catch (Throwable var_3_0) {
                    Log.e("RFixLoadLibrary", new StringBuilder().append("installNativeLibraryPath, v25 fail, sdk: ").append(Build$VERSION.SDK_INT).append(", error: ").append(var_3_0.getMessage()).append(", try to fallback to V23").toString());
                    a$b.a(loader, file, bool0);
                }
            }
            else if (Build$VERSION.SDK_INT >= 23) {
                try {
                    a$b.a(loader, file, bool0);
                }
                catch (Throwable var_3_1) {
                    Log.e("RFixLoadLibrary", new StringBuilder().append("installNativeLibraryPath, v23 fail, sdk: ").append(Build$VERSION.SDK_INT).append(", error: ").append(var_3_1.getMessage()).append(", try to fallback to V14").toString());
                    a$a.a(loader, file, bool0);
                }
            }
            else {
                a$a.a(loader, file, bool0);
            }
        }
    }

    // class: com/tencent/rfix/loader/b/a$c
    final class a$c {

        private static void b(ClassLoader loader, File file, boolean bool0) {
            Field field = b.a(loader, "pathList");
            Object object = field.get(loader);
            Field fieldVar1 = b.a(object, "nativeLibraryDirectories");
            List list = (List)fieldVar1.get(object);
            if (list == null) {
                list = new ArrayList(2);
            }
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                File fileVar1 = (File)iterator.next();
                if (file.equals(fileVar1)) {
                    iterator.remove();
                    break;;
                }
                else {
                    continue;;
                }
            }
            if (bool0) {
                list.add(0, file);
            }
            Field fieldVar2 = b.a(object, "systemNativeLibraryDirectories");
            List listVar1 = (List)fieldVar2.get(object);
            if (listVar1 == null) {
                listVar1 = new ArrayList(2);
            }
            int i0 = list.size() + listVar1.size() + 1;
            list = new ArrayList(i0);
            list.addAll(list);
            list.addAll(listVar1);
            Method method = b.a(object, "makePathElements", new Class[]{List.class});
            Object[] objectArr0 = (Object[])method.invoke(object, new Object[]{list});
            Field fieldVar3 = b.a(object, "nativeLibraryPathElements");
            fieldVar3.set(object, objectArr0);
        }

        static /* synthetic */ void a(ClassLoader loader, File file, boolean bool0) {
            a$c.b(loader, file, bool0);
        }

    }
    // class: com/tencent/rfix/loader/b/a$c
    final class a$c {

        private static void b(ClassLoader loader, File file, boolean bool0) {
            Field field = b.a(loader, "pathList");
            Object object = field.get(loader);
            Field fieldVar1 = b.a(object, "nativeLibraryDirectories");
            List list = (List)fieldVar1.get(object);
            if (list == null) {
                list = new ArrayList(2);
            }
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                File fileVar1 = (File)iterator.next();
                if (file.equals(fileVar1)) {
                    iterator.remove();
                    break;;
                }
                else {
                    continue;;
                }
            }
            if (bool0) {
                list.add(0, file);
            }
            Field fieldVar2 = b.a(object, "systemNativeLibraryDirectories");
            List listVar1 = (List)fieldVar2.get(object);
            if (listVar1 == null) {
                listVar1 = new ArrayList(2);
            }
            int i0 = list.size() + listVar1.size() + 1;
            list = new ArrayList(i0);
            list.addAll(list);
            list.addAll(listVar1);
            Method method = b.a(object, "makePathElements", new Class[]{List.class});
            Object[] objectArr0 = (Object[])method.invoke(object, new Object[]{list});
            Field fieldVar3 = b.a(object, "nativeLibraryPathElements");
            fieldVar3.set(object, objectArr0);
        }

        static /* synthetic */ void a(ClassLoader loader, File file, boolean bool0) {
            a$c.b(loader, file, bool0);
        }

    }
    // class: com/tencent/rfix/loader/b/a$a
    final class a$a {

        private static void b(ClassLoader loader, File file, boolean bool0) {
            Field field = b.a(loader, "pathList");
            Object object = field.get(loader);
            Field fieldVar1 = b.a(object, "nativeLibraryDirectories");
            File[] fileArr0 = (File[])fieldVar1.get(object);
            int i0 = fileArr0.length + 1;
            ArrayList list = new ArrayList(i0);
            if (bool0) {
                list.add(file);
            }
            for (int i2 = 0; i2 < fileArr0.length; i2 += 1) {
                File fileVar1 = fileArr0[i2];
                if (file.equals(fileVar1)) {
                    list.add(fileVar1);
                }
            }
            fieldVar1.set(object, list.toArray(new File[]{}));
        }

        static /* synthetic */ void a(ClassLoader loader, File file, boolean bool0) {
            a$a.b(loader, file, bool0);
        }

    }
    // class: com/tencent/rfix/loader/b/a$a
    final class a$a {

        private static void b(ClassLoader loader, File file, boolean bool0) {
            Field field = b.a(loader, "pathList");
            Object object = field.get(loader);
            Field fieldVar1 = b.a(object, "nativeLibraryDirectories");
            File[] fileArr0 = (File[])fieldVar1.get(object);
            int i0 = fileArr0.length + 1;
            ArrayList list = new ArrayList(i0);
            if (bool0) {
                list.add(file);
            }
            for (int i2 = 0; i2 < fileArr0.length; i2 += 1) {
                File fileVar1 = fileArr0[i2];
                if (file.equals(fileVar1)) {
                    list.add(fileVar1);
                }
            }
            fieldVar1.set(object, list.toArray(new File[]{}));
        }

        static /* synthetic */ void a(ClassLoader loader, File file, boolean bool0) {
            a$a.b(loader, file, bool0);
        }

    }
    // class: com/tencent/rfix/loader/b/a$b
    final class a$b {

        private static void b(ClassLoader loader, File file, boolean bool0) {
            Field field = b.a(loader, "pathList");
            Object object = field.get(loader);
            Field fieldVar1 = b.a(object, "nativeLibraryDirectories");
            List list = (List)fieldVar1.get(object);
            if (list == null) {
                list = new ArrayList(2);
            }
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                File fileVar1 = (File)iterator.next();
                if (file.equals(fileVar1)) {
                    iterator.remove();
                    break;;
                }
                else {
                    continue;;
                }
            }
            if (bool0) {
                list.add(0, file);
            }
            Field fieldVar2 = b.a(object, "systemNativeLibraryDirectories");
            List listVar1 = (List)fieldVar2.get(object);
            if (listVar1 == null) {
                listVar1 = new ArrayList(2);
            }
            int i0 = list.size() + listVar1.size() + 1;
            list = new ArrayList(i0);
            list.addAll(list);
            list.addAll(listVar1);
            Method method = b.a(object, "makePathElements", new Class[]{List.class, File.class, List.class});
            listVar1 = new ArrayList();
            Object[] objectArr0 = (Object[])method.invoke(object, new Object[]{list, null, listVar1});
            Field fieldVar3 = b.a(object, "nativeLibraryPathElements");
            fieldVar3.set(object, objectArr0);
        }

        static /* synthetic */ void a(ClassLoader loader, File file, boolean bool0) {
            a$b.b(loader, file, bool0);
        }

    }
    // class: com/tencent/rfix/loader/b/a$b
    final class a$b {

        private static void b(ClassLoader loader, File file, boolean bool0) {
            Field field = b.a(loader, "pathList");
            Object object = field.get(loader);
            Field fieldVar1 = b.a(object, "nativeLibraryDirectories");
            List list = (List)fieldVar1.get(object);
            if (list == null) {
                list = new ArrayList(2);
            }
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                File fileVar1 = (File)iterator.next();
                if (file.equals(fileVar1)) {
                    iterator.remove();
                    break;;
                }
                else {
                    continue;;
                }
            }
            if (bool0) {
                list.add(0, file);
            }
            Field fieldVar2 = b.a(object, "systemNativeLibraryDirectories");
            List listVar1 = (List)fieldVar2.get(object);
            if (listVar1 == null) {
                listVar1 = new ArrayList(2);
            }
            int i0 = list.size() + listVar1.size() + 1;
            list = new ArrayList(i0);
            list.addAll(list);
            list.addAll(listVar1);
            Method method = b.a(object, "makePathElements", new Class[]{List.class, File.class, List.class});
            listVar1 = new ArrayList();
            Object[] objectArr0 = (Object[])method.invoke(object, new Object[]{list, null, listVar1});
            Field fieldVar3 = b.a(object, "nativeLibraryPathElements");
            fieldVar3.set(object, objectArr0);
        }

        static /* synthetic */ void a(ClassLoader loader, File file, boolean bool0) {
            a$b.b(loader, file, bool0);
        }

    }
}
