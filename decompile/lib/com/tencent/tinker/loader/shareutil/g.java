/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import android.content.Intent;
import java.io.Serializable;
import java.util.HashMap;

// class: com/tencent/tinker/loader/shareutil/g
public class g {

    public static void a(Intent intent, int i0) {
        intent.putExtra("intent_return_code", i0);
    }

    public static int a(Intent intent) {
        return g.a(intent, "intent_return_code", 55536);
    }

    public static void a(Intent intent, long l1) {
        intent.putExtra("intent_patch_cost_time", l1);
    }

    public static long b(Intent intent) {
        return intent.getLongExtra("intent_patch_cost_time", 0L);
    }

    public static Throwable c(Intent intent) {
        Serializable serializable = g.b(intent, "intent_patch_exception");
        if (serializable != null) {
            return (Throwable)serializable;
        }
        else {
            return null;
        }
    }

    public static Throwable d(Intent intent) {
        Serializable serializable = g.b(intent, "intent_patch_interpret_exception");
        if (serializable != null) {
            return (Throwable)serializable;
        }
        else {
            return null;
        }
    }

    public static HashMap<String, String> e(Intent intent) {
        Serializable serializable = g.b(intent, "intent_patch_dexes_path");
        if (serializable != null) {
            return (HashMap)serializable;
        }
        else {
            return null;
        }
    }

    public static HashMap<String, String> f(Intent intent) {
        Serializable serializable = g.b(intent, "intent_patch_libs_path");
        if (serializable != null) {
            return (HashMap)serializable;
        }
        else {
            return null;
        }
    }

    public static HashMap<String, String> g(Intent intent) {
        Serializable serializable = g.b(intent, "intent_patch_package_config");
        if (serializable != null) {
            return (HashMap)serializable;
        }
        else {
            return null;
        }
    }

    public static String a(Intent intent, String str0) {
        if (null == intent) {
            return null;
        }
        else {
            Object object = null;
            try {
                String str1 = intent.getStringExtra(str0);
            }
            catch (Exception var_3_0) {
                n.d("ShareIntentUtil", new StringBuilder().append("getStringExtra exception:").append(var_3_0.getMessage()).toString(), new Object[]{});
                Object objectVar1 = null;
            }
            return str1;
        }
    }

    public static Serializable b(Intent intent, String str0) {
        if (null == intent) {
            return null;
        }
        else {
            Object object = null;
            try {
                Serializable serializable = intent.getSerializableExtra(str0);
            }
            catch (Exception var_3_0) {
                n.d("ShareIntentUtil", new StringBuilder().append("getSerializableExtra exception:").append(var_3_0.getMessage()).toString(), new Object[]{});
                Object objectVar1 = null;
            }
            return serializable;
        }
    }

    public static int a(Intent intent, String str0, int i0) {
        if (null == intent) {
            return i0;
        }
        else {
            try {
                i3 = intent.getIntExtra(str0, i0);
            }
            catch (Exception var_4_0) {
                n.d("ShareIntentUtil", new StringBuilder().append("getIntExtra exception:").append(var_4_0.getMessage()).toString(), new Object[]{});
                i3 = i0;
            }
            return i0;
        }
    }

    public static boolean a(Intent intent, String str0, boolean bool0) {
        if (null == intent) {
            return bool0;
        }
        else {
            try {
                bool3 = intent.getBooleanExtra(str0, bool0);
            }
            catch (Exception var_4_0) {
                n.d("ShareIntentUtil", new StringBuilder().append("getBooleanExtra exception:").append(var_4_0.getMessage()).toString(), new Object[]{});
                bool3 = bool0;
            }
            return bool0;
        }
    }

    public static void a(Intent intent, ClassLoader loader) {
        try {
            intent.setExtrasClassLoader(loader);
        }
        catch (Throwable var_2_0) {
            var_2_0.printStackTrace();
        }
    }

}
