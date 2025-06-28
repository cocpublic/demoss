/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import java.util.HashMap;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.Enumeration;
import java.security.cert.Certificate[];
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.annotation.SuppressLint;
import com.tencent.tinker.loader.k;

// class: com/tencent/tinker/loader/shareutil/l
public class l {
    private static String a;
    final private Context b;
    final private HashMap<String, String> c;
    final private HashMap<String, String> d;

    public l(Context context) {
        super();
        this.b = context;
        this.c = new HashMap();
        this.d = new HashMap();
        if (l.a == null) {
            this.a(this.b);
        }
    }

    public HashMap<String, String> a() {
        return this.c;
    }

    public HashMap<String, String> b() {
        if (this.d.isEmpty()) {
            return this.d;
        }
        else {
            String str0 = (String)this.c.get("assets/package_meta.txt");
            if (str0 == null) {
                return null;
            }
            else {
                String[] stringArr0 = str0.split("
");
                for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                    String str1 = stringArr0[i1];
                    if (str1 != null ? str1.startsWith("#") : str1.length() <= 0) {
                        continue;;
                    }
                    else {
                        String[] stringArr0Var2 = str1.split("=", 2);
                        if (stringArr0Var2 != null) {
                            if (stringArr0Var2.length < 2) {
                                continue;;
                            }
                            else {
                                this.d.put(stringArr0Var2[0].trim(), stringArr0Var2[1].trim());
                            }
                        }
                    }
                }
                return this.d;
            }
        }
    }

    public boolean a(File file) {
        if (h.a(file)) {
            return false;
        }
        else {
            Object object = null;
            try {
                file = new JarFile(file);
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
                            this.c.put(str0, h.a(file, entry));
                            Certificate[] certificateArr0 = entry.getCertificates();
                            certificateArr0 == null || super.a(file, certificateArr0);
                            continue;;
                        }
                    }
                }
                try {
                }
                catch (IOException var_3_2) {
                }
            }
            catch (Exception var_3_1) {
                throw new k(String.format("ShareSecurityCheck file %s, size %d verifyPatchMetaSignature fail", new Object[]{file.getAbsolutePath(), Long.valueOf(file.length())}), var_3_1);
            }
            finally {
                Throwable throwable = v_9;
                try {
                    if (file != null) {
                        file.close();
                    }
                }
                catch (IOException var_8_0) {
                    n.d("Tinker.SecurityCheck", file.getAbsolutePath(), new Object[]{var_8_0});
                }
                throw throwable;
            }
            return true;
        }
    }

    private boolean a(File file, Certificate[] certificateArr0) {
        if (certificateArr0.length > 0) {
            for (int i0 = certificateArr0.length - 1; i0 >= 0; i0 += 255) {
                try {
                    if (l.a.equals(h.a(certificateArr0[i0].getEncoded()))) {
                        return true;
                    }
                    else {
                        continue;;
                    }
                }
                catch (Exception var_4_0) {
                    n.d("Tinker.SecurityCheck", file.getAbsolutePath(), new Object[]{var_4_0});
                }
            }
        }
        return false;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    private void a(Context context) {
        Object object = null;
        try {
            PackageManager manager = context.getPackageManager();
            String str0 = context.getPackageName();
            PackageInfo info = manager.getPackageInfo(str0, 64);
            l.a = h.a(info.signatures[0].toByteArray());
            if (l.a == null) {
                throw new k("get public key md5 is null");
            }
            else {
            }
        }
        catch (Exception var_3_1) {
            throw new k("ShareSecurityCheck init public key fail", var_3_1);
        }
        finally {
            Throwable throwable = v_17;
            h.a(object);
            throw throwable;
        }
    }

    static  {
        l.a = null;
    }

}
