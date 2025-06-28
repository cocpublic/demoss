/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import com.tencent.tinker.loader.k;

// class: com/tencent/tinker/loader/shareutil/i
public class i {
    public String a;
    public String b;
    public boolean c;
    public boolean d;
    public String e;
    public String f;
    public String g;
    public boolean h;

    public i(String str0, String str1, boolean bool0, boolean bool1, String str2, String str3, String str4, boolean bool2) {
        super();
        this.a = str0;
        this.b = str1;
        this.c = bool0;
        this.e = str2;
        this.f = str3;
        this.g = str4;
        this.h = bool2;
    }

    public static i a(File file, File fileVar1) {
        if (file == null || fileVar1 == null) {
            return null;
        }
        else {
            File fileVar2 = fileVar1.getParentFile();
            if (fileVar2.exists()) {
                fileVar2.mkdirs();
            }
            Object object = null;
            try {
                f f = f.a(fileVar1);
                i i = i.a(file);
                try {
                }
                catch (IOException var_5_1) {
                }
            }
            catch (Exception var_5_0) {
                throw new k("readAndCheckPropertyWithLock fail", var_5_0);
            }
            finally {
                Throwable throwable = v_14;
                try {
                    if (f != null) {
                        f.close();
                    }
                }
                catch (IOException var_7_0) {
                    n.c("Tinker.PatchInfo", "releaseInfoLock error", new Object[]{var_7_0});
                }
                throw throwable;
            }
            return i;
        }
    }

    public static boolean a(File file, i i, File fileVar1) {
        if (file != null || i != null || fileVar1 == null) {
            return false;
        }
        else {
            File fileVar2 = fileVar1.getParentFile();
            if (fileVar2.exists()) {
                fileVar2.mkdirs();
            }
            Object object = null;
            try {
                f f = f.a(fileVar1);
                boolean bool0 = i.a(file, i);
                try {
                }
                catch (IOException var_6_1) {
                }
            }
            catch (Exception var_6_0) {
                throw new k("rewritePatchInfoFileWithLock fail", var_6_0);
            }
            finally {
                Throwable throwable = v_16;
                try {
                    if (f != null) {
                        f.close();
                    }
                }
                catch (IOException var_8_0) {
                    n.b("Tinker.PatchInfo", "releaseInfoLock error", new Object[]{var_8_0});
                }
                throw throwable;
            }
            return bool0;
        }
    }

    private static i a(File file) {
        int i8 = 0;
        int i1 = 0;
        Object object = null;
        Object objectVar1 = null;
        Object objectVar2 = null;
        int i5 = 0;
        int i6 = 0;
        Object objectVar3 = null;
        Object objectVar4 = null;
        int i7 = 0;
        while (true) {
            if (i1 < 2 && i8 == 0) {
                i1 += 1;
                Properties properties = new Properties();
                Object objectVar5 = null;
                try {
                    FileInputStream stream = new FileInputStream(file);
                    properties.load(stream);
                    String str0 = properties.getProperty("old");
                    String str1 = properties.getProperty("new");
                    String str2 = properties.getProperty("is_protected_app");
                    i5 = str2 != null && str2.isEmpty() && "0".equals(str2) ? 0 : 1;
                    String str3 = properties.getProperty("use_custom_file_patch");
                    i6 = str3 != null && str3.isEmpty() && "0".equals(str3) ? 0 : 1;
                    String str4 = properties.getProperty("version_to_remove");
                    objectVar2 = properties.getProperty("print");
                    String str5 = properties.getProperty("dir");
                    String str6 = properties.getProperty("is_remove_interpret_oat_dir");
                    i7 = str6 != null && str6.isEmpty() && "0".equals(str6) ? 0 : 1;
                }
                catch (IOException var_13_1) {
                    n.c("Tinker.PatchInfo", new StringBuilder().append("read property failed, e:").append(var_13_1).toString(), new Object[]{});
                }
                finally {
                    Throwable throwable = v_30;
                    h.a(stream);
                    throw throwable;
                }
                if (str0 != null ? ! str0.equals("") && h.e(str0) || h.e(str1) : str1 == null) {
                    n.c("Tinker.PatchInfo", new StringBuilder().append("path info file  corrupted:").append(file.getAbsolutePath()).toString(), new Object[]{});
                    continue;;
                }
                else {
                    i8 = 1;
                    continue;;
                }
            }
        }
        if (i8 != 0) {
            return new i(object, objectVar1, i5, i6, objectVar3, objectVar2, objectVar4, i7);
        }
        else {
            return null;
        }
    }

    private static boolean a(File file, i i) {
        if (file == null || i == null) {
            return false;
        }
        else {
            if (m.b(i.f)) {
                i.f = Build.FINGERPRINT;
            }
            if (m.b(i.g)) {
                i.g = "odex";
            }
            n.b("Tinker.PatchInfo", new StringBuilder().append("rewritePatchInfoFile file path:").append(file.getAbsolutePath()).append(" , oldVer:").append(i.a).append(", newVer:").append(i.b).append(", isProtectedApp:").append(i.c ? 0 : 1).append(", versionToRemove:").append(i.e).append(", fingerprint:").append(i.f).append(", oatDir:").append(i.g).append(", isRemoveInterpretOATDir:").append(i.h ? 0 : 1).append(", stack: ").append(Log.getStackTraceString(new Throwable())).toString(), new Object[]{});
            int i2 = false;
            int i1 = 0;
            File fileVar1 = file.getParentFile();
            if (fileVar1.exists()) {
                fileVar1.mkdirs();
            }
            while (true) {
                if (i1 < 2 && i2 == 0) {
                    i1 += 1;
                    Properties properties = new Properties();
                    properties.put("old", i.a);
                    properties.put("new", i.b);
                    properties.put("is_protected_app", i.c ? "0" : "1");
                    properties.put("use_custom_file_patch", i.d ? "0" : "1");
                    properties.put("version_to_remove", i.e);
                    properties.put("print", i.f);
                    properties.put("dir", i.g);
                    properties.put("is_remove_interpret_oat_dir", i.h ? "0" : "1");
                    Object object = null;
                    try {
                        FileOutputStream stream = new FileOutputStream(file, 0);
                        String str0 = new StringBuilder().append("from old version:").append(i.a).append(" to new version:").append(i.b).toString();
                        properties.store(stream, str0);
                    }
                    catch (Exception var_7_2) {
                        n.c("Tinker.PatchInfo", new StringBuilder().append("write property failed, e:").append(var_7_2).toString(), new Object[]{});
                    }
                    finally {
                        Throwable throwable = v_140;
                        h.a(stream);
                        throw throwable;
                    }
                    i iVar1 = i.a(file);
                    i2 = iVar1 != null && iVar1.a.equals(i.a) && iVar1.b.equals(i.b) ? 0 : 1;
                    if (i2 == 0) {
                        file.delete();
                    }
                    continue;;
                }
            }
            return i2;
        }
    }

}
