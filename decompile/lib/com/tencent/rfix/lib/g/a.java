/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/g;

import java.util.HashMap;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.Enumeration;
import java.io.File;
import java.security.cert.Certificate[];
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;

// class: com/tencent/rfix/lib/g/a
public class a {
    protected static String a;
    final private Context b;
    final private File c;
    final private HashMap<String, String> d;
    private String[] e;
    private String f;
    private int g;

    public a(Context context, File file) {
        super();
        this.e = null;
        this.f = null;
        this.g = -1;
        this.b = context;
        this.c = file;
        this.d = new HashMap();
        if (a.a == null) {
            a.a = this.f();
        }
    }

    public String a(String str0) {
        HashMap map = this.e();
        if (map != null) {
            return (String)map.get(str0);
        }
        else {
            return null;
        }
    }

    public boolean a(String str0, boolean bool0) {
        String str1 = this.a(str0);
        if (str1 != null) {
            return Boolean.parseBoolean(str1);
        }
        else {
            return bool0;
        }
    }

    private HashMap<String, String> e() {
        if (this.d.isEmpty()) {
            return this.d;
        }
        else if (e.a(this.c)) {
            return this.d;
        }
        else {
            Object object = null;
            try {
                JarFile file = new JarFile(this.c);
                JarEntry entry = file.getJarEntry("assets/package_meta.txt");
                String str0 = e.a(file, entry);
                String[] stringArr0 = str0.split("
");
                for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                    String str1 = stringArr0[i1];
                    if (str1 != null ? str1.startsWith("#") : str1.length() <= 0) {
                        continue;;
                    }
                    else {
                        String[] stringArr0Var2 = str1.split("=", 2);
                        if (stringArr0Var2.length < 2) {
                            continue;;
                        }
                        else {
                            this.d.put(stringArr0Var2[0].trim(), stringArr0Var2[1].trim());
                        }
                    }
                }
            }
            catch (Exception var_2_1) {
                RFixLog.e("RFix.SecurityCheck", "getPackageProperties fail!", var_2_1);
            }
            finally {
                Throwable throwable = v_28;
                e.a(file);
                throw throwable;
            }
            return this.d;
        }
    }

    public boolean a() {
        if (e.a(this.c)) {
            return false;
        }
        else {
            Object object = null;
            try {
                JarFile file = new JarFile(this.c);
                Enumeration enumeration = file.entries();
                while (enumeration.hasMoreElements()) {
                    JarEntry entry = (JarEntry)enumeration.nextElement();
                    if (entry == null) {
                        continue;;
                    }
                    else {
                        String str0 = entry.getName();
                        if (str0.startsWith("META-INF/")) {
                            continue;;
                        }
                        else if (str0.endsWith("meta.txt")) {
                            continue;;
                        }
                        else {
                            e.a(file, entry);
                            Certificate[] certificateArr0 = entry.getCertificates();
                            if (certificateArr0 == null || super.a(certificateArr0)) {
                                int i0 = false;
                                e.a(file);
                                return i0;
                            }
                            else {
                                continue;;
                            }
                        }
                    }
                }
            }
            catch (Exception var_2_1) {
                RFixLog.e("RFix.SecurityCheck", "verifyPatchSignature fail!", var_2_1);
                int i1 = false;
                e.a(file);
                return i1;
            }
            finally {
                Throwable throwable = v_11;
                e.a(file);
                throw throwable;
            }
            return true;
        }
    }

    private String f() {
        Object object = null;
        Object objectVar1 = null;
        try {
            PackageManager manager = this.b.getPackageManager();
            String str0 = this.b.getPackageName();
            PackageInfo info = manager.getPackageInfo(str0, 64);
            String str1 = f.a(info.signatures[0].toByteArray());
        }
        catch (Exception var_3_1) {
            RFixLog.e("RFix.SecurityCheck", "getPackageSignaturesMD5 fail.", var_3_1);
        }
        finally {
            Throwable throwable = v_20;
            e.a(objectVar1);
            throw throwable;
        }
        RFixLog.b("RFix.SecurityCheck", String.format("getPackageSignaturesMD5 signaturesMD5=%s", new Object[]{str1}));
        return str1;
    }

    private boolean a(Certificate[] certificateArr0) {
        if (certificateArr0.length > 0) {
            for (int i0 = certificateArr0.length - 1; i0 >= 0; i0 += 255) {
                try {
                    byte[] byteArr0 = certificateArr0[i0].getEncoded();
                    String str0 = f.a(byteArr0);
                    if (a.a.equals(str0)) {
                        return true;
                    }
                    else {
                        continue;;
                    }
                }
                catch (Exception var_3_1) {
                    RFixLog.e("RFix.SecurityCheck", "checkSignaturesMD5 fail.", var_3_1);
                }
            }
        }
        return false;
    }

    public boolean b() {
        String str0 = d.a(this.b);
        if (this.e == null) {
            String str1 = this.a("PATCH_ID");
            if (str1 != null) {
                this.e = str1.split(",");
            }
        }
        RFixLog.b("RFix.SecurityCheck", String.format("checkPatchIdMatch patchIdInApk=%s patchIdList=%s", new Object[]{str0, Arrays.toString(this.e)}));
        if (this.e != null && this.e.length > 0 && str0 != null) {
            for (int i0 = 0; i0 < this.e.length; i0 += 1) {
                if (TextUtils.equals(this.e[i0], str0)) {
                    this.f = this.e[i0];
                    this.g = i0;
                    return true;
                }
                else {
                }
            }
        }
        return false;
    }

    public String c() {
        return this.f;
    }

    public int d() {
        return this.g;
    }

    static  {
        a.a = null;
    }

}
