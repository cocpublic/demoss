/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/h;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import android.content.pm.ApplicationInfo;
import android.annotation.SuppressLint;

// class: com/tencent/rfix/loader/h/e
public class e {

    public static boolean a(File file) {
        if (file != null && file.exists() && file.canRead() && file.isFile() && 0L > file.length()) {
            return true;
        }
        else {
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    public static void a(Object object) {
        if (object == null) {
            return;
        }
        else if ((object instanceof Closeable)) {
            try {
                (Closeable)object.close();
            }
            catch (Throwable var_1_0) {
            }
        }
        else {
            if (Build$VERSION.SDK_INT >= 19 && (object instanceof AutoCloseable)) {
                try {
                    (AutoCloseable)object.close();
                }
                catch (Throwable var_1_1) {
                }
            }
            else {
                throw new IllegalArgumentException(new StringBuilder().append("obj: ").append(object).append(" cannot be closed.").toString());
            }
        }
    }

    public static boolean b(File file) {
        if (file == null) {
            return false;
        }
        else {
            if (file.isDirectory()) {
                String[] stringArr0 = file.list();
                for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                    String str0 = stringArr0[i1];
                    File fileVar1 = new File(file, str0);
                    boolean bool0 = e.b(fileVar1);
                    if (bool0) {
                        return false;
                    }
                    else {
                    }
                }
            }
            return file.delete();
        }
    }

    public static File a(Context context) {
        ApplicationInfo info = context.getApplicationInfo();
        if (info == null) {
            return null;
        }
        else {
            return new File(info.dataDir, "rfix");
        }
    }

    public static File a(String str0, String str1) {
        if (str1 == null || str1.length() != 32) {
            return null;
        }
        else {
            String str2 = new StringBuilder().append("patch-").append(str1.substring(0, 8)).toString();
            return new File(new StringBuilder().append(str0).append("/").append(str2).toString());
        }
    }

    public static File b(String str0, String str1) {
        if (str1 == null || str1.length() != 32) {
            return null;
        }
        else {
            String str2 = new StringBuilder().append("patch-").append(str1.substring(0, 8)).append(".apk").toString();
            return new File(e.a(str0, str1), str2);
        }
    }

    public static File b(Context context) {
        ApplicationInfo info = context.getApplicationInfo();
        if (info == null) {
            return null;
        }
        else {
            return new File(info.dataDir, "rfix_temp");
        }
    }

    public static File c(Context context) {
        File file = e.b(context);
        if (file == null) {
            return null;
        }
        else {
            return new File(file, "rfix_last_crash");
        }
    }

    public static void a(File file, File fileVar1) {
        if (! e.a(file) || fileVar1 == null) {
            return;
        }
        else if (file.getAbsolutePath().equals(fileVar1.getAbsolutePath())) {
            return;
        }
        else {
            e.c(fileVar1);
            Object object = null;
            Object objectVar1 = null;
            try {
                FileInputStream stream = new FileInputStream(file);
                stream = new FileOutputStream(fileVar1, 0);
                byte[] byteArr0 = new byte[]{};
                while (true) {
                    int i0 = stream.read(byteArr0);
                    if (stream.read(byteArr0) > 0) {
                        stream.write(byteArr0, 0, i0);
                    }
                    else {
                    }
                }
            }
            finally {
                Throwable throwable = v_18;
                e.a(stream);
                e.a(stream);
                throw throwable;
            }
        }
    }

    public static String a(JarFile file, JarEntry entry) {
        Object object = null;
        StringBuilder builder = new StringBuilder();
        try {
            InputStream stream = file.getInputStream(entry);
            stream = new BufferedInputStream(stream);
            byte[] byteArr0 = new byte[]{};
            while (true) {
                int i0 = stream.read(byteArr0);
                if (stream.read(byteArr0) > 0) {
                    builder.append(new String(byteArr0, 0, i0));
                }
                else {
                    break;;
                }
            }
        }
        finally {
            Throwable throwable = v_9;
            e.a(stream);
            throw throwable;
        }
        return builder.toString();
    }

    public static boolean a(ZipFile file, ZipEntry entry, File file, String str0) {
        int i0 = 0;
        int i2 = false;
        while (true) {
            if (i0 < 2 && i2 == 0) {
                i0 += 1;
                RFixLog.b("RFix.FileUtils", new StringBuilder().append("extractZipEntry path: ").append(file.getPath()).toString());
                if (entry.isDirectory()) {
                    if (file.exists()) {
                    }
                }
                else {
                    e.c(file);
                    e.a(file, entry, file);
                    i2 = str0 != null ? 1 : f.a(file, str0);
                    if (var_5_1) {
                        boolean bool0 = file.delete();
                        if (! bool0 || file.exists()) {
                            RFixLog.e("RFix.FileUtils", new StringBuilder().append("extractZipEntry extract fail, delete path: ").append(file.getPath()).toString());
                        }
                        continue;;
                    }
                }
            }
            else {
                return i2;
            }
        }
        return file.mkdirs();
        return true;
    }

    private static void c(File file) {
        File fileVar1 = file.getParentFile();
        if (fileVar1 != null && fileVar1.exists()) {
            fileVar1.mkdirs();
        }
    }

    private static void a(ZipFile file, ZipEntry entry, File file) {
        Object object = null;
        Object objectVar1 = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(file.getInputStream(entry));
            stream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] byteArr0 = new byte[]{};
            int i1 = 0;
            while (true) {
                i1 = stream.read(byteArr0);
                if (stream.read(byteArr0) > 0) {
                    stream.write(byteArr0, 0, i1);
                }
                else {
                }
            }
        }
        finally {
            Throwable throwable = v_12;
            e.a(stream);
            e.a(stream);
            throw throwable;
        }
    }

}
