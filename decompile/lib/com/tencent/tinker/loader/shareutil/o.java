/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import android.content.pm.ApplicationInfo;

// class: com/tencent/tinker/loader/shareutil/o
public class o {
    private static String a;

    public static void a(String str0) {
        n.b("Tinker.ProcessUtils", new StringBuilder().append("setMainProcessName oldProcessName=").append(o.a).append(", newProcessName=").append(str0).toString(), new Object[]{});
        o.a = str0;
    }

    public static boolean a(Context context, String str0) {
        if (TextUtils.isEmpty(o.a)) {
            o.a = o.b(context);
        }
        return TextUtils.equals(o.a, str0);
    }

    public static boolean a(Context context) {
        if (TextUtils.isEmpty(o.a)) {
            o.a = o.b(context);
        }
        String str1 = m.l(context);
        if (TextUtils.isEmpty(str1)) {
            str1 = "";
        }
        if (TextUtils.isEmpty(o.a) && o.a.equals(str1)) {
            return true;
        }
        else {
            return false;
        }
    }

    private static String b(Context context) {
        Object object = null;
        ApplicationInfo info = context.getApplicationInfo();
        if (info != null) {
            object = info.processName;
        }
        if (TextUtils.isEmpty(object)) {
            object = context.getPackageName();
        }
        return object;
    }

    static  {
        o.a = null;
    }

}
