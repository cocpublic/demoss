/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.zip.ZipFile;
import java.util.ListIterator;
import java.io.File;
import java.io.IOException;
import dalvik.system.DexFile;

// class: com/tencent/tinker/loader/d
public class d {
    private static int a;

    public static void a(Application application, ClassLoader loader, File file, List<File> list, boolean bool0, boolean bool1) {
        n.b("Tinker.ClassLoaderAdder", new StringBuilder().append("installDexes dexOptDir: ").append(file.getAbsolutePath()).append(", dex size:").append(list.size()).toString(), new Object[]{});
        if (list.isEmpty()) {
            list = d.a(list);
            if (Build$VERSION.SDK_INT >= 24 && bool0) {
                loaderVar3 = c.a(application, loader, file, bool1, list);
                loaderVar3 = d.a(application, loader, file, list, bool1, loader);
                goto 109;
            }
            else {
                d.a(loader, list, file);
            }
            d.a = list.size();
            n.b("Tinker.ClassLoaderAdder", new StringBuilder().append("after loaded classloader: ").append(loader).append(", dex size:").append(d.a).toString(), new Object[]{});
            if (d.b(loader)) {
                if (Build$VERSION.SDK_INT >= 24 && bool0) {
                    c.a(application, loader);
                }
                else {
                    d.a(loader);
                }
                throw new k("checkDexInstall failed");
            }
        }
    }

    private static ClassLoader a(Application application, ClassLoader loader, File file, List<File> list, boolean bool0, ClassLoader loaderVar1) {
        if (bool0 && d.b(loaderVar1)) {
            n.c("Tinker.ClassLoaderAdder", "preCheckDexInstall check dex install fail. try to remove odex and reinstall.", new Object[]{});
            list = new ArrayList();
            ArrayList listVar1 = new ArrayList();
            Iterator iteratorVar1 = list.iterator();
            while (iteratorVar1.hasNext()) {
                File fileVar1 = (File)iteratorVar1.next();
                String str0 = h.b(fileVar1, null);
                File fileVar2 = new File(str0);
                if (fileVar2.exists()) {
                    n.b("Tinker.ClassLoaderAdder", new StringBuilder().append("preCheckDexInstall find odex file: ").append(fileVar2).append(" length=").append(fileVar2.length()).toString(), new Object[]{});
                    list.add(fileVar2);
                }
                String str1 = new String(str0).replace(".odex", ".vdex");
                File fileVar3 = new File(str1);
                if (fileVar3.exists()) {
                    n.b("Tinker.ClassLoaderAdder", new StringBuilder().append("preCheckDexInstall find vdex file: ").append(fileVar3).append(" length=").append(fileVar3.length()).toString(), new Object[]{});
                    listVar1.add(fileVar3);
                }
            }
            iteratorVar1 = list.iterator();
            while (iteratorVar1.hasNext()) {
                File fileVar4 = (File)iteratorVar1.next();
                h.c(fileVar4);
            }
            loaderVar1 = c.a(application, loader, file, bool0, list);
        }
        return loaderVar1;
    }

    static void a(ClassLoader loader, List<File> list, File file) {
        if (Build$VERSION.SDK_INT >= 23) {
            d$d.a(loader, list, file);
        }
        else if (Build$VERSION.SDK_INT >= 19) {
            d$c.a(loader, list, file);
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            d$b.a(loader, list, file);
        }
        else {
            d$e.a(loader, list, file);
        }
    }

    public static void a(PathClassLoader loader, List<File> list) {
        if (list.isEmpty()) {
            list = d.a(list);
            d$a.a(loader, list);
            d.a = list.size();
            n.b("Tinker.ClassLoaderAdder", new StringBuilder().append("after loaded classloader: ").append(loader).append(", dex size:").append(d.a).toString(), new Object[]{});
            d.b(loader);
        }
    }

    public static void a(ClassLoader loader) {
        if (d.a <= 0) {
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            Field field = j.a(loader, "pathList");
            Object object = field.get(loader);
            j.a(object, "dexElements", d.a);
        }
        else {
            j.a(loader, "mPaths", d.a);
            j.a(loader, "mFiles", d.a);
            j.a(loader, "mZips", d.a);
            try {
                j.a(loader, "mDexs", d.a);
            }
            catch (Exception var_1_1) {
            }
        }
    }

    private static boolean b(ClassLoader loader) {
        Class class = Class.forName("com.tencent.tinker.loader.TinkerTestDexLoad", true, loader);
        Field field = j.a(class, "isPatch");
        boolean bool0 = (Boolean)field.get(null).booleanValue();
        n.b("Tinker.ClassLoaderAdder", "checkDexInstall result: %s, checker_classloader: %s", new Object[]{Boolean.valueOf(bool0), class.getClassLoader()});
        return bool0;
    }

    private static List<File> a(List<File> list) {
        list = new ArrayList(list);
        HashMap map = new HashMap();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            File file = (File)iterator.next();
            String str0 = file.getName();
            map.put(str0, Boolean.valueOf(c.a.matcher(str0).matches()));
        }
        Collections.sort(list, new d$1(map));
        return list;
    }

    static  {
        d.a = 0;
    }

    // class: com/tencent/tinker/loader/d$e
    final class d$e {

        private static void b(ClassLoader loader, List<File> list, File file) {
            int i0 = list.size();
            Field field = j.a(loader, "path");
            StringBuilder builder = new StringBuilder((String)field.get(loader));
            String str0 = new String[]{};
            File fileVar1 = new File[]{};
            file = new ZipFile[]{};
            file = new DexFile[]{};
            ListIterator iterator = list.listIterator();
            while (iterator.hasNext()) {
                File fileVar2 = (File)iterator.next();
                String str1 = fileVar2.getAbsolutePath();
                builder.append(58).append(str1);
                int i1 = iterator.previousIndex();
                str0[i1] = str1;
                fileVar1[i1] = fileVar2;
                file[i1] = new ZipFile(fileVar2);
                String str2 = h.b(fileVar2, file);
                file[i1] = DexFile.loadDex(str1, str2, 0);
            }
            field.set(loader, builder.toString());
            j.a(loader, "mPaths", str0);
            j.a(loader, "mFiles", fileVar1);
            j.a(loader, "mZips", file);
            try {
                j.a(loader, "mDexs", file);
            }
            catch (Exception var_10_1) {
            }
        }

        static /* synthetic */ void a(ClassLoader loader, List list, File file) {
            d$e.b(loader, list, file);
        }

    }
    // class: com/tencent/tinker/loader/d$e
    final class d$e {

        private static void b(ClassLoader loader, List<File> list, File file) {
            int i0 = list.size();
            Field field = j.a(loader, "path");
            StringBuilder builder = new StringBuilder((String)field.get(loader));
            String str0 = new String[]{};
            File fileVar1 = new File[]{};
            file = new ZipFile[]{};
            file = new DexFile[]{};
            ListIterator iterator = list.listIterator();
            while (iterator.hasNext()) {
                File fileVar2 = (File)iterator.next();
                String str1 = fileVar2.getAbsolutePath();
                builder.append(58).append(str1);
                int i1 = iterator.previousIndex();
                str0[i1] = str1;
                fileVar1[i1] = fileVar2;
                file[i1] = new ZipFile(fileVar2);
                String str2 = h.b(fileVar2, file);
                file[i1] = DexFile.loadDex(str1, str2, 0);
            }
            field.set(loader, builder.toString());
            j.a(loader, "mPaths", str0);
            j.a(loader, "mFiles", fileVar1);
            j.a(loader, "mZips", file);
            try {
                j.a(loader, "mDexs", file);
            }
            catch (Exception var_10_1) {
            }
        }

        static /* synthetic */ void a(ClassLoader loader, List list, File file) {
            d$e.b(loader, list, file);
        }

    }
    // class: com/tencent/tinker/loader/d$c
    final class d$c {

        private static void b(ClassLoader loader, List<File> list, File file) {
            Field field = j.a(loader, "pathList");
            Object object = field.get(loader);
            list = new ArrayList();
            j.a(object, "dexElements", d$c.b(object, new ArrayList(list), file, list));
            if (list.size() > 0) {
                Iterator iterator = list.iterator();
                if (iterator.hasNext()) {
                    IOException exception = (IOException)iterator.next();
                    n.c("Tinker.ClassLoaderAdder", "Exception in makeDexElement", new Object[]{exception});
                    throw exception;
                }
            }
        }

        private static Object[] b(Object object, ArrayList<File> list, File file, ArrayList<IOException> listVar1) {
            Object objectVar1 = null;
            Method methodVar1;
            try {
                methodVar1 = j.a(object, "makeDexElements", new Class[]{ArrayList.class, File.class, ArrayList.class});
            }
            catch (NoSuchMethodException var_5_0) {
                n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(ArrayList,File,ArrayList) failure", new Object[]{});
                try {
                    methodVar1 = j.a(object, "makeDexElements", new Class[]{List.class, File.class, List.class});
                }
                catch (NoSuchMethodException var_6_0) {
                    n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(List,File,List) failure", new Object[]{});
                    throw var_6_0;
                }
            }
            return (Object[])methodVar1.invoke(object, new Object[]{list, file, listVar1});
        }

        static /* synthetic */ void a(ClassLoader loader, List list, File file) {
            d$c.b(loader, list, file);
        }

        static /* synthetic */ Object[] a(Object object, ArrayList list, File file, ArrayList listVar1) {
            return d$c.b(object, list, file, listVar1);
        }

    }
    // class: com/tencent/tinker/loader/d$c
    final class d$c {

        private static void b(ClassLoader loader, List<File> list, File file) {
            Field field = j.a(loader, "pathList");
            Object object = field.get(loader);
            list = new ArrayList();
            j.a(object, "dexElements", d$c.b(object, new ArrayList(list), file, list));
            if (list.size() > 0) {
                Iterator iterator = list.iterator();
                if (iterator.hasNext()) {
                    IOException exception = (IOException)iterator.next();
                    n.c("Tinker.ClassLoaderAdder", "Exception in makeDexElement", new Object[]{exception});
                    throw exception;
                }
            }
        }

        private static Object[] b(Object object, ArrayList<File> list, File file, ArrayList<IOException> listVar1) {
            Object objectVar1 = null;
            Method methodVar1;
            try {
                methodVar1 = j.a(object, "makeDexElements", new Class[]{ArrayList.class, File.class, ArrayList.class});
            }
            catch (NoSuchMethodException var_5_0) {
                n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(ArrayList,File,ArrayList) failure", new Object[]{});
                try {
                    methodVar1 = j.a(object, "makeDexElements", new Class[]{List.class, File.class, List.class});
                }
                catch (NoSuchMethodException var_6_0) {
                    n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(List,File,List) failure", new Object[]{});
                    throw var_6_0;
                }
            }
            return (Object[])methodVar1.invoke(object, new Object[]{list, file, listVar1});
        }

        static /* synthetic */ void a(ClassLoader loader, List list, File file) {
            d$c.b(loader, list, file);
        }

        static /* synthetic */ Object[] a(Object object, ArrayList list, File file, ArrayList listVar1) {
            return d$c.b(object, list, file, listVar1);
        }

    }
    // class: com/tencent/tinker/loader/d$d
    final class d$d {

        private static void b(ClassLoader loader, List<File> list, File file) {
            Field field = j.a(loader, "pathList");
            Object object = field.get(loader);
            list = new ArrayList();
            j.a(object, "dexElements", d$d.a(object, new ArrayList(list), file, list));
            if (list.size() > 0) {
                Iterator iterator = list.iterator();
                if (iterator.hasNext()) {
                    IOException exception = (IOException)iterator.next();
                    n.c("Tinker.ClassLoaderAdder", "Exception in makePathElement", new Object[]{exception});
                    throw exception;
                }
            }
        }

        private static Object[] a(Object object, ArrayList<File> list, File file, ArrayList<IOException> listVar1) {
            Method methodVar1;
            try {
                methodVar1 = j.a(object, "makePathElements", new Class[]{List.class, File.class, List.class});
            }
            catch (NoSuchMethodException var_5_0) {
                n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: makePathElements(List,File,List) failure", new Object[]{});
                try {
                    methodVar1 = j.a(object, "makePathElements", new Class[]{ArrayList.class, File.class, ArrayList.class});
                }
                catch (NoSuchMethodException var_6_0) {
                    n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(ArrayList,File,ArrayList) failure", new Object[]{});
                    try {
                        n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: try use v19 instead", new Object[]{});
                        return d$c.a(object, list, file, listVar1);
                    }
                    catch (NoSuchMethodException var_7_0) {
                        n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(List,File,List) failure", new Object[]{});
                        throw var_7_0;
                    }
                }
            }
            return (Object[])methodVar1.invoke(object, new Object[]{list, file, listVar1});
        }

        static /* synthetic */ void a(ClassLoader loader, List list, File file) {
            d$d.b(loader, list, file);
        }

    }
    // class: com/tencent/tinker/loader/d$d
    final class d$d {

        private static void b(ClassLoader loader, List<File> list, File file) {
            Field field = j.a(loader, "pathList");
            Object object = field.get(loader);
            list = new ArrayList();
            j.a(object, "dexElements", d$d.a(object, new ArrayList(list), file, list));
            if (list.size() > 0) {
                Iterator iterator = list.iterator();
                if (iterator.hasNext()) {
                    IOException exception = (IOException)iterator.next();
                    n.c("Tinker.ClassLoaderAdder", "Exception in makePathElement", new Object[]{exception});
                    throw exception;
                }
            }
        }

        private static Object[] a(Object object, ArrayList<File> list, File file, ArrayList<IOException> listVar1) {
            Method methodVar1;
            try {
                methodVar1 = j.a(object, "makePathElements", new Class[]{List.class, File.class, List.class});
            }
            catch (NoSuchMethodException var_5_0) {
                n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: makePathElements(List,File,List) failure", new Object[]{});
                try {
                    methodVar1 = j.a(object, "makePathElements", new Class[]{ArrayList.class, File.class, ArrayList.class});
                }
                catch (NoSuchMethodException var_6_0) {
                    n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(ArrayList,File,ArrayList) failure", new Object[]{});
                    try {
                        n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: try use v19 instead", new Object[]{});
                        return d$c.a(object, list, file, listVar1);
                    }
                    catch (NoSuchMethodException var_7_0) {
                        n.d("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(List,File,List) failure", new Object[]{});
                        throw var_7_0;
                    }
                }
            }
            return (Object[])methodVar1.invoke(object, new Object[]{list, file, listVar1});
        }

        static /* synthetic */ void a(ClassLoader loader, List list, File file) {
            d$d.b(loader, list, file);
        }

    }
    // class: com/tencent/tinker/loader/d$a
    final class d$a {

        private static void b(ClassLoader loader, List<File> list) {
            Class class = ClassLoader.getSystemClassLoader().getParent().loadClass("com.huawei.ark.classloader.ExtendedClassLoaderHelper");
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                File file = (File)iterator.next();
                String str0 = file.getCanonicalPath();
                Method method = class.getDeclaredMethod("applyPatch", new Class[]{ClassLoader.class, String.class});
                method.setAccessible(true);
                method.invoke(null, new Object[]{loader, str0});
                n.b("Tinker.ClassLoaderAdder", new StringBuilder().append("ArkHot install path = ").append(str0).toString(), new Object[]{});
            }
        }

        static /* synthetic */ void a(ClassLoader loader, List list) {
            d$a.b(loader, list);
        }

    }
    // class: com/tencent/tinker/loader/d$a
    final class d$a {

        private static void b(ClassLoader loader, List<File> list) {
            Class class = ClassLoader.getSystemClassLoader().getParent().loadClass("com.huawei.ark.classloader.ExtendedClassLoaderHelper");
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                File file = (File)iterator.next();
                String str0 = file.getCanonicalPath();
                Method method = class.getDeclaredMethod("applyPatch", new Class[]{ClassLoader.class, String.class});
                method.setAccessible(true);
                method.invoke(null, new Object[]{loader, str0});
                n.b("Tinker.ClassLoaderAdder", new StringBuilder().append("ArkHot install path = ").append(str0).toString(), new Object[]{});
            }
        }

        static /* synthetic */ void a(ClassLoader loader, List list) {
            d$a.b(loader, list);
        }

    }
    // class: com/tencent/tinker/loader/d$b
    final class d$b {

        private static void b(ClassLoader loader, List<File> list, File file) {
            Field field = j.a(loader, "pathList");
            Object object = field.get(loader);
            j.a(object, "dexElements", d$b.a(object, new ArrayList(list), file));
        }

        private static Object[] a(Object object, ArrayList<File> list, File file) {
            Method method = j.a(object, "makeDexElements", new Class[]{ArrayList.class, File.class});
            return (Object[])method.invoke(object, new Object[]{list, file});
        }

        static /* synthetic */ void a(ClassLoader loader, List list, File file) {
            d$b.b(loader, list, file);
        }

    }
    // class: com/tencent/tinker/loader/d$b
    final class d$b {

        private static void b(ClassLoader loader, List<File> list, File file) {
            Field field = j.a(loader, "pathList");
            Object object = field.get(loader);
            j.a(object, "dexElements", d$b.a(object, new ArrayList(list), file));
        }

        private static Object[] a(Object object, ArrayList<File> list, File file) {
            Method method = j.a(object, "makeDexElements", new Class[]{ArrayList.class, File.class});
            return (Object[])method.invoke(object, new Object[]{list, file});
        }

        static /* synthetic */ void a(ClassLoader loader, List list, File file) {
            d$b.b(loader, list, file);
        }

    }
}
