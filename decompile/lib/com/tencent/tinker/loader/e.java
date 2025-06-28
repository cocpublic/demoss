/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.io.File;
import dalvik.system.PathClassLoader;
import android.content.Intent;
import android.annotation.TargetApi;

// class: com/tencent/tinker/loader/e
public class e {
    private static HashSet<a> a;
    private static boolean b;

    private e() {
        super();
    }

    @TargetApi(14)
    public static boolean a(TinkerApplication application, String str0, Intent intent) {
        if (e.a.isEmpty()) {
            n.c("Tinker.TinkerArkHotLoader", "there is no apk to load", new Object[]{});
            return true;
        }
        else {
            PathClassLoader loader = (PathClassLoader)e.class.getClassLoader();
            if (loader != null) {
                n.b("Tinker.TinkerArkHotLoader", new StringBuilder().append("classloader: ").append(loader.toString()).toString(), new Object[]{});
                String str1 = new StringBuilder().append(str0).append("/").append("arkHot").append("/").toString();
                ArrayList list = new ArrayList();
                if (e.b && e.a.isEmpty()) {
                    Object object = null;
                    File file = new File(new StringBuilder().append(str1).append("patch.apk").toString());
                    list.add(file);
                }
                try {
                    d.a(loader, list);
                }
                catch (Throwable var_6_2) {
                    n.d("Tinker.TinkerArkHotLoader", "install dexes failed", new Object[]{});
                    intent.putExtra("intent_patch_exception", var_6_2);
                    g.a(intent, 242);
                    return false;
                }
                return true;
            }
            else {
                n.d("Tinker.TinkerArkHotLoader", "classloader is null", new Object[]{});
                g.a(intent, 244);
                return false;
            }
        }
    }

    public static boolean a(String str0, l l, Intent intent) {
        String str1 = (String)l.a().get("assets/arkHot_meta.txt");
        if (str1 == null) {
            return true;
        }
        else {
            e.a.clear();
            ArrayList list = new ArrayList();
            a.a(str1, list);
            if (list.isEmpty()) {
                return true;
            }
            else {
                HashMap map = new HashMap(1);
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    a a = (a)iterator.next();
                    if (a.a(a)) {
                        intent.putExtra("intent_patch_package_patch_check", 253);
                        g.a(intent, 248);
                        return false;
                    }
                    else {
                        if (e.b && "patch.apk".equals(a.b)) {
                            e.a.add(a);
                        }
                        continue;;
                    }
                }
                if (e.b && e.a.isEmpty()) {
                    map.put("patch.apk", "");
                }
                String str2 = new StringBuilder().append(str0).append("/").append("arkHot").append("/").toString();
                File file = new File(str2);
                if (! file.exists() || file.isDirectory()) {
                    g.a(intent, 247);
                    return false;
                }
                else {
                    Iterator iteratorVar1 = map.keySet().iterator();
                    while (iteratorVar1.hasNext()) {
                        String str3 = (String)iteratorVar1.next();
                        File fileVar1 = new File(new StringBuilder().append(str2).append(str3).toString());
                        h.a(fileVar1);
                        try {
                            intent.putExtra("intent_patch_missing_dex_path", fileVar1.getCanonicalPath());
                        }
                        catch (IOException var_11_0) {
                            var_11_0.printStackTrace();
                        }
                        g.a(intent, 246);
                        return false;
                    }
                    intent.putExtra("intent_patch_dexes_path", map);
                    return true;
                }
            }
        }
    }

    static  {
        e.a = new HashSet();
        e.b = m.c();
    }

}
