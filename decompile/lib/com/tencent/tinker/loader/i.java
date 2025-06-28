/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import java.io.File;
import java.util.HashMap;
import android.content.Intent;

// class: com/tencent/tinker/loader/i
public class i {
    private static k a;

    public static boolean a(TinkerApplication application, String str0, Intent intent) {
        if (i.a == null || i.a.b == null) {
            return true;
        }
        else {
            String str1 = new StringBuilder().append(str0).append("/").append("res").append("/").append("resources.apk").toString();
            File file = new File(str1);
            long l0 = System.currentTimeMillis();
            if (application.b()) {
                if (h.c(file, i.a.b)) {
                    n.d("Tinker.ResourceLoader", new StringBuilder().append("Failed to load resource file, path: ").append(file.getPath()).append(", expect md5: ").append(i.a.b).toString(), new Object[]{});
                    g.a(intent, 232);
                    return false;
                }
                else {
                    n.b("Tinker.ResourceLoader", new StringBuilder().append("verify resource file:").append(file.getPath()).append(" md5, use time: ").append(System.currentTimeMillis() - l0).toString(), new Object[]{});
                }
            }
            try {
                j.a(application, str1, 0);
                n.b("Tinker.ResourceLoader", new StringBuilder().append("monkeyPatchExistingResources resource file:").append(str1).append(", use time: ").append(System.currentTimeMillis() - l0).toString(), new Object[]{});
            }
            catch (Throwable var_7_0) {
                n.d("Tinker.ResourceLoader", "install resources failed", new Object[]{});
                try {
                    d.a(application.getClassLoader());
                }
                catch (Throwable var_8_0) {
                    n.d("Tinker.ResourceLoader", "uninstallPatchDex failed", new Object[]{var_7_0});
                }
                intent.putExtra("intent_patch_exception", var_7_0);
                g.a(intent, 233);
                return false;
            }
            return true;
        }
    }

    public static boolean a(Context context, String str0, l l, Intent intent) {
        String str1 = (String)l.a().get("assets/res_meta.txt");
        if (str1 == null) {
            return true;
        }
        else {
            k.b(str1, i.a);
            if (i.a.b == null) {
                return true;
            }
            else if (k.a(i.a)) {
                intent.putExtra("intent_patch_package_patch_check", 248);
                g.a(intent, 248);
                return false;
            }
            else {
                String str2 = new StringBuilder().append(str0).append("/").append("res").append("/").toString();
                File file = new File(str2);
                if (! file.exists() || file.isDirectory()) {
                    g.a(intent, 235);
                    return false;
                }
                else {
                    File fileVar1 = new File(new StringBuilder().append(str2).append("resources.apk").toString());
                    if (h.a(fileVar1)) {
                        g.a(intent, 234);
                        return false;
                    }
                    else {
                        try {
                            j.a(context);
                        }
                        catch (Throwable var_8_0) {
                            n.d("Tinker.ResourceLoader", "resource hook check failed.", new Object[]{var_8_0});
                            intent.putExtra("intent_patch_exception", var_8_0);
                            g.a(intent, 233);
                            return false;
                        }
                        return true;
                    }
                }
            }
        }
    }

    static  {
        i.a = new k();
    }

}
