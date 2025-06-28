/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Set;
import java.io.File;
import android.content.Intent;

// class: com/tencent/tinker/loader/f
public class f {
    final private static ArrayList<d> a;
    private static HashSet<d> b;
    private static boolean c;

    private f() {
        super();
    }

    public static boolean a(TinkerApplication application, String str0, String str1, Intent intent, boolean bool0, boolean bool1) {
        if (f.a.isEmpty() && f.b.isEmpty()) {
            n.c("Tinker.TinkerDexLoader", "there is no dex to load", new Object[]{});
            return true;
        }
        else {
            ClassLoader loader = f.class.getClassLoader();
            if (loader != null) {
                File fileVar3;
                n.b("Tinker.TinkerDexLoader", new StringBuilder().append("classloader: ").append(loader.toString()).toString(), new Object[]{});
                String str2 = new StringBuilder().append(str0).append("/").append("dex").append("/").toString();
                ArrayList list = new ArrayList();
                Iterator iterator = f.a.iterator();
                while (iterator.hasNext()) {
                    d d = (d)iterator.next();
                    if (f.b(d)) {
                        continue;;
                    }
                    else {
                        String str3 = new StringBuilder().append(str2).append(d.j).toString();
                        File file = new File(str3);
                        if (application.b()) {
                            long l0 = System.currentTimeMillis();
                            String str4 = f.a(d);
                            if (h.b(file, str4)) {
                                g.a(intent, 243);
                                intent.putExtra("intent_patch_mismatch_dex_path", file.getAbsolutePath());
                                return false;
                            }
                            else {
                                n.b("Tinker.TinkerDexLoader", new StringBuilder().append("verify dex file:").append(file.getPath()).append(" md5, use time: ").append(System.currentTimeMillis() - l0).toString(), new Object[]{});
                            }
                        }
                        list.add(file);
                        continue;;
                    }
                }
                if (f.c && f.b.isEmpty()) {
                    File fileVar1 = new File(new StringBuilder().append(str2).append("tinker_classN.apk").toString());
                    long l1 = System.currentTimeMillis();
                    if (application.b()) {
                        Iterator iteratorVar1 = f.b.iterator();
                        while (iteratorVar1.hasNext()) {
                            d dVar1 = (d)iteratorVar1.next();
                            h.a(fileVar1, dVar1.a, dVar1.c);
                            g.a(intent, 243);
                            intent.putExtra("intent_patch_mismatch_dex_path", fileVar1.getAbsolutePath());
                            return false;
                        }
                    }
                    n.b("Tinker.TinkerDexLoader", new StringBuilder().append("verify dex file:").append(fileVar1.getPath()).append(" md5, use time: ").append(System.currentTimeMillis() - l1).toString(), new Object[]{});
                    list.add(fileVar1);
                }
                fileVar3 = new File(new StringBuilder().append(str0).append("/").append(str1).toString());
                if (bool0) {
                    boolean[] booleanArr0 = new boolean[]{1};
                    Throwable throwable = new Throwable[]{};
                    try {
                        String str5 = m.e();
                        goto 613;
                    }
                    catch (Throwable var_13_2) {
                        n.b("Tinker.TinkerDexLoader", new StringBuilder().append("getCurrentInstructionSet fail:").append(var_13_2).toString(), new Object[]{});
                        f.a(str0);
                        intent.putExtra("intent_patch_interpret_exception", var_13_2);
                        g.a(intent, 241);
                        return false;
                    }
                    f.a(str0);
                    n.c("Tinker.TinkerDexLoader", new StringBuilder().append("systemOTA, try parallel oat dexes, targetISA:").append(str5).toString(), new Object[]{});
                    fileVar3 = new File(new StringBuilder().append(str0).append("/").append("interpet").toString());
                    g.a(application, list, fileVar3, true, application.d(), str5, 0, new f$1(booleanArr0, throwable));
                    if (booleanArr0[0]) {
                        n.d("Tinker.TinkerDexLoader", "parallel oat dexes failed", new Object[]{});
                        intent.putExtra("intent_patch_interpret_exception", throwable[0]);
                        g.a(intent, 240);
                        return false;
                    }
                }
                try {
                    boolean bool2 = application.d();
                    d.a(application, loader, fileVar3, list, bool1, bool2);
                }
                catch (Throwable var_10_4) {
                    n.d("Tinker.TinkerDexLoader", "install dexes failed", new Object[]{});
                    intent.putExtra("intent_patch_exception", var_10_4);
                    g.a(intent, 242);
                    return false;
                }
                return true;
            }
            else {
                n.d("Tinker.TinkerDexLoader", "classloader is null", new Object[]{});
                g.a(intent, 244);
                return false;
            }
        }
    }

    public static boolean a(String str0, l l, String str1, Intent intent) {
        String str2 = (String)l.a().get("assets/dex_meta.txt");
        if (str2 == null) {
            return true;
        }
        else {
            f.a.clear();
            f.b.clear();
            ArrayList list = new ArrayList();
            d.a(str2, list);
            if (list.isEmpty()) {
                return true;
            }
            else {
                HashMap map = new HashMap();
                Object object = null;
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    d d = (d)iterator.next();
                    if (f.b(d)) {
                        continue;;
                    }
                    else if (d.a(d)) {
                        intent.putExtra("intent_patch_package_patch_check", 253);
                        g.a(intent, 248);
                        return false;
                    }
                    else {
                        if (f.c && d.a.startsWith("test.dex")) {
                        }
                        else {
                            if (f.c && c.a.matcher(d.j).matches()) {
                                f.b.add(d);
                            }
                            else {
                                map.put(d.j, f.a(d));
                                f.a.add(d);
                            }
                        }
                        continue;;
                    }
                }
                if (f.c) {
                    if (d != null || f.b.isEmpty()) {
                        if (d != null) {
                            f.b.add(m.a(d, f.b.size() + 1));
                        }
                        map.put("tinker_classN.apk", "");
                    }
                }
                String str3 = new StringBuilder().append(str0).append("/").append("dex").append("/").toString();
                File file = new File(str3);
                if (! file.exists() || file.isDirectory()) {
                    g.a(intent, 247);
                    return false;
                }
                else {
                    String str4 = new StringBuilder().append(str0).append("/").append(str1).append("/").toString();
                    File fileVar1 = new File(str4);
                    Iterator iteratorVar1 = map.keySet().iterator();
                    while (iteratorVar1.hasNext()) {
                        String str5 = (String)iteratorVar1.next();
                        File fileVar2 = new File(new StringBuilder().append(str3).append(str5).toString());
                        h.a(fileVar2);
                        intent.putExtra("intent_patch_missing_dex_path", fileVar2.getAbsolutePath());
                        g.a(intent, 246);
                        return false;
                        File fileVar3 = new File(h.b(fileVar2, fileVar1));
                        h.a(fileVar3);
                        h.b(fileVar3);
                        continue;;
                        intent.putExtra("intent_patch_missing_dex_path", fileVar3.getAbsolutePath());
                        g.a(intent, 245);
                        return false;
                    }
                    intent.putExtra("intent_patch_dexes_path", map);
                    return true;
                }
            }
        }
    }

    private static String a(d d) {
        if (f.c) {
            return d.c;
        }
        else {
            return d.b;
        }
    }

    private static void a(String str0) {
        String str1 = new StringBuilder().append(str0).append("/").append("odex").append("/").toString();
        h.f(str1);
        if (m.d()) {
            String str2 = new StringBuilder().append(str0).append("/").append("dex").append("/").append("oat").append("/").toString();
            h.f(str2);
        }
    }

    private static boolean b(d d) {
        if (f.c) {
            return false;
        }
        else {
            String str0 = d.b;
            if (str0.equals("0")) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    static  {
        f.a = new ArrayList();
        f.b = new HashSet();
        f.c = m.a();
    }

}
