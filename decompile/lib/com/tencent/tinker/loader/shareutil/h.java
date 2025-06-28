/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import android.content.pm.ApplicationInfo;
import android.annotation.SuppressLint;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.Closeable;
import java.io.File[];
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.security.MessageDigest;
import com.tencent.tinker.loader.k;

// class: com/tencent/tinker/loader/shareutil/h
public class h {
    private static char a;

    public static File a(Context context) {
        ApplicationInfo info = context.getApplicationInfo();
        if (info == null) {
            return null;
        }
        else {
            String str0 = "oppo".equalsIgnoreCase(Build.MANUFACTURER) && Build$VERSION.SDK_INT == 22 ? "tinker" : "wc_tinker_dir";
            return new File(info.dataDir, str0);
        }
    }

    public static File b(Context context) {
        ApplicationInfo info = context.getApplicationInfo();
        if (info == null) {
            return null;
        }
        else {
            return new File(info.dataDir, "tinker_temp");
        }
    }

    public static File c(Context context) {
        File file = h.b(context);
        if (file == null) {
            return null;
        }
        else {
            return new File(file, "tinker_last_crash");
        }
    }

    public static File a(String str0) {
        return new File(new StringBuilder().append(str0).append("/").append("patch_meta.info").toString());
    }

    public static File b(String str0) {
        return new File(new StringBuilder().append(str0).append("/").append("info.lock").toString());
    }

    public static String c(String str0) {
        if (str0 == null || str0.length() != 32) {
            return null;
        }
        else {
            return new StringBuilder().append("patch-").append(str0.substring(0, 8)).toString();
        }
    }

    public static String d(String str0) {
        if (str0 == null || str0.length() != 32) {
            return null;
        }
        else {
            return new StringBuilder().append(h.c(str0)).append(".apk").toString();
        }
    }

    public static boolean e(String str0) {
        if (str0 == null || str0.length() != 32) {
            return false;
        }
        else {
            return true;
        }
    }

    public static String d(Context context) {
        File file = h.c(context);
        if (h.a(file)) {
            return null;
        }
        else {
            StringBuffer buffer = new StringBuffer();
            Object object = null;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                while (true) {
                    String str0 = reader.readLine();
                    if (reader.readLine() != null) {
                        buffer.append(str0);
                        buffer.append("
");
                    }
                    else {
                        break;;
                    }
                }
            }
            catch (Exception var_4_1) {
                n.d("Tinker.PatchFileUtil", new StringBuilder().append("checkTinkerLastUncaughtCrash exception: ").append(var_4_1).toString(), new Object[]{});
                Object objectVar1 = null;
                h.a(reader);
                return objectVar1;
            }
            finally {
                Throwable throwable = v_12;
                h.a(reader);
                throw throwable;
            }
            return buffer.toString();
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
            else if ((object instanceof ZipFile)) {
                try {
                    (ZipFile)object.close();
                }
                catch (Throwable var_1_2) {
                }
            }
            else {
                throw new IllegalArgumentException(new StringBuilder().append("obj: ").append(object).append(" cannot be closed.").toString());
            }
        }
    }

    final public static boolean a(File file) {
        if (file != null && file.exists() && file.canRead() && file.isFile() && 0L > file.length()) {
            return true;
        }
        else {
            return false;
        }
    }

    final public static boolean b(File file) {
        int i0 = "vivo".equalsIgnoreCase(Build.MANUFACTURER) || "oppo".equalsIgnoreCase(Build.MANUFACTURER) || "meizu".equalsIgnoreCase(Build.MANUFACTURER) ? 0 : 1;
        int i1 = ! m.a(29, 1) || m.c() ? 0 : 1;
        int i2 = ! file.exists() || 0L == file.length() ? 0 : 1;
        if (i0 != 0 || i1 != 0 && i2 != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    final public static boolean c(File file) {
        if (file == null) {
            return true;
        }
        else if (file.exists()) {
            n.b("Tinker.PatchFileUtil", new StringBuilder().append("safeDeleteFile, try to delete path: ").append(file.getPath()).toString(), new Object[]{});
            boolean bool0 = file.delete();
            if (bool0) {
                n.d("Tinker.PatchFileUtil", new StringBuilder().append("Failed to delete file, try to delete when exit. path: ").append(file.getPath()).toString(), new Object[]{});
                file.deleteOnExit();
            }
            return bool0;
        }
        else {
            return true;
        }
    }

    final public static boolean f(String str0) {
        if (str0 == null) {
            return false;
        }
        else {
            return h.d(new File(str0));
        }
    }

    final public static boolean d(File file) {
        if (file == null || file.exists()) {
            return false;
        }
        else {
            if (file.isFile()) {
                h.c(file);
            }
            else if (file.isDirectory()) {
                File[] fileArr0 = file.listFiles();
                if (fileArr0 != null) {
                    for (int i1 = 0; i1 < fileArr0.length; i1 += 1) {
                        File fileVar1 = fileArr0[i1];
                        h.d(fileVar1);
                    }
                    h.c(file);
                }
            }
            return true;
        }
    }

    public static void g(String str0) {
        h.e(new File(str0));
    }

    public static void e(File file) {
        new h$2(new h$1(file), "tinker-clean").start();
    }

    public static boolean a(File file, String str0) {
        if (str0 == null) {
            return false;
        }
        else {
            String str1 = h.f(file);
            if (str1 == null) {
                return false;
            }
            else {
                return str0.equals(str1);
            }
        }
    }

    public static boolean h(String str0) {
        if (str0 == null) {
            return false;
        }
        else {
            return str0.endsWith(".dex");
        }
    }

    public static boolean b(File file, String str0) {
        return h.a(file, "classes.dex", str0);
    }

    public static boolean a(File file, String str0, String str1) {
        if (file != null || str1 != null || str0 == null) {
            return false;
        }
        else {
            String str4 = "";
            if (h.h(file.getName())) {
                str4 = h.f(file);
            }
            else {
                Object object = null;
                try {
                    file = new ZipFile(file);
                    ZipEntry entry = file.getEntry(str0);
                    if (null == entry) {
                        n.d("Tinker.PatchFileUtil", new StringBuilder().append("There's no entry named: classes.dex in ").append(file.getAbsolutePath()).toString(), new Object[]{});
                        int i0 = false;
                        h.a(file);
                        return i0;
                    }
                    else {
                        Object objectVar1 = null;
                        try {
                            InputStream stream = file.getInputStream(entry);
                            str4 = h.a(stream);
                        }
                        catch (Throwable var_7_0) {
                            n.d("Tinker.PatchFileUtil", new StringBuilder().append("exception occurred when get md5: ").append(file.getAbsolutePath()).toString(), new Object[]{var_7_0});
                        }
                        finally {
                            Throwable throwable = v_43;
                            h.a(stream);
                            throw throwable;
                        }
                    }
                }
                catch (Throwable var_5_1) {
                    n.d("Tinker.PatchFileUtil", new StringBuilder().append("Bad dex jar file: ").append(file.getAbsolutePath()).toString(), new Object[]{var_5_1});
                    int i1 = false;
                    h.a(file);
                    return i1;
                }
                finally {
                    Throwable throwableVar1 = v_21;
                    h.a(file);
                    throw throwableVar1;
                }
            }
            return str1.equals(str4);
        }
    }

    public static void a(File file, File fileVar1) {
        if (! h.a(file) || fileVar1 == null) {
            return;
        }
        else if (file.getAbsolutePath().equals(fileVar1.getAbsolutePath())) {
            return;
        }
        else {
            Object object = null;
            Object objectVar1 = null;
            File fileVar2 = fileVar1.getParentFile();
            if (fileVar2 != null && fileVar2.exists()) {
                fileVar2.mkdirs();
            }
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
                Throwable throwable = v_24;
                h.a(stream);
                h.a(stream);
                throw throwable;
            }
        }
    }

    public static String a(JarFile file, JarEntry entry) {
        Object object = null;
        StringBuilder builder = new StringBuilder();
        try {
            InputStream stream = file.getInputStream(entry);
            byte[] byteArr0 = new byte[]{};
            stream = new BufferedInputStream(stream);
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
            h.a(stream);
            throw throwable;
        }
        return builder.toString();
    }

    final public static String a(InputStream stream) {
        if (stream == null) {
            return null;
        }
        else {
            try {
                stream = new BufferedInputStream(stream);
                MessageDigest digest = MessageDigest.getInstance("MD5");
                StringBuilder builder = new StringBuilder(32);
                byte[] byteArr0 = new byte[]{};
                while (true) {
                    int i0 = stream.read(byteArr0);
                    if (stream.read(byteArr0) != -1) {
                        digest.update(byteArr0, 0, i0);
                    }
                    else {
                        byte[] byteArr0Var1 = digest.digest();
                        for (int i1 = 0; i1 < byteArr0Var1.length; i1 += 1) {
                            builder.append(Integer.toString(byteArr0Var1[i1] & 255 + 256, 16).substring(1));
                        }
                        return builder.toString();
                    }
                }
            }
            catch (Exception var_1_1) {
                return null;
            }
        }
    }

    public static String a(byte[] byteArr0) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(byteArr0);
            byte[] byteArr0Var1 = digest.digest();
            char[] charArr0 = new char[]{};
            int i1 = 0;
            for (int i2 = 0; i2 < byteArr0Var1.length; i2 += 1) {
                byte byte0 = byteArr0Var1[i2];
                i1 += 1;
                charArr0[i1] = h.a[byte0 >>> 4 & 15];
                i1 += 1;
                charArr0[i1] = h.a[byte0 & 15];
            }
            return new String(charArr0);
        }
        catch (Exception var_1_1) {
            return null;
        }
    }

    public static String f(File file) {
        if (file == null || file.exists()) {
            return null;
        }
        else {
            Object object = null;
            try {
                FileInputStream stream = new FileInputStream(file);
                String str0 = h.a(stream);
                h.a(stream);
                return str0;
            }
            catch (Exception var_2_1) {
                n.d("Tinker.PatchFileUtil", var_2_1.getMessage(), new Object[]{});
                Object objectVar1 = null;
                h.a(stream);
                return objectVar1;
            }
            finally {
                Throwable throwable = v_12;
                h.a(stream);
                throw throwable;
            }
        }
    }

    public static String b(File file, File fileVar1) {
        if (m.d()) {
            String str2;
            try {
                String str0 = m.e();
                goto 25;
            }
            catch (Exception var_3_3) {
                throw new k("getCurrentInstructionSet fail:", var_3_3);
            }
            File fileVar2 = file.getParentFile();
            str2 = file.getName();
            int i0 = str2.lastIndexOf(46);
            if (i0 > 0) {
                str2 = str2.substring(0, i0);
            }
            String str3 = new StringBuilder().append(fileVar2.getAbsolutePath()).append("/oat/").append(str0).append("/").append(str2).append(".odex").toString();
            return str3;
        }
        else {
            String str6 = file.getName();
            if (str6.endsWith(".dex")) {
                int i1 = str6.lastIndexOf(".");
                if (i1 < 0) {
                    str6 = new StringBuilder().append(str6).append(".dex").toString();
                }
                else {
                    StringBuilder builder = new StringBuilder(i1 + 4);
                    builder.append(str6, 0, i1);
                    builder.append(".dex");
                    str6 = builder.toString();
                }
            }
            File fileVar3 = new File(fileVar1, str6);
            return fileVar3.getPath();
        }
    }

    public static void a(ZipFile file) {
        try {
            if (file != null) {
                file.close();
            }
        }
        catch (IOException var_1_0) {
            n.c("Tinker.PatchFileUtil", "Failed to close resource", new Object[]{var_1_0});
        }
    }

    public static boolean c(File file, String str0) {
        Object object = null;
        try {
            file = new ZipFile(file);
            ZipEntry entry = file.getEntry("resources.arsc");
            if (entry == null) {
                n.b("Tinker.PatchFileUtil", "checkResourceArscMd5 resources.arsc not found", new Object[]{});
                int i0 = false;
                h.a(file);
                return i0;
            }
            else {
                Object objectVar1 = null;
                try {
                    InputStream stream = file.getInputStream(entry);
                    String str1 = h.a(stream);
                    if (str1 != null && str1.equals(str0)) {
                        int i1 = true;
                        h.a(stream);
                        h.a(file);
                        return i1;
                    }
                    else {
                    }
                }
                finally {
                    Throwable throwable = v_23;
                    h.a(stream);
                    throw throwable;
                }
            }
        }
        catch (Throwable var_3_1) {
            n.b("Tinker.PatchFileUtil", new StringBuilder().append("checkResourceArscMd5 throwable:").append(var_3_1.getMessage()).toString(), new Object[]{});
        }
        finally {
            Throwable throwableVar1 = v_8;
            h.a(file);
            throw throwableVar1;
        }
        return false;
    }

    public static void g(File file) {
        if (file == null) {
        }
        else {
            File fileVar1 = file.getParentFile();
            if (fileVar1.exists()) {
                fileVar1.mkdirs();
            }
        }
    }

    static  {
        h.a = new char[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    }

}
