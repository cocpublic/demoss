/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/g;

import android.app.ActivityManager;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.ComponentName;
import java.util.List;
import java.util.Iterator;

// class: com/tencent/tinker/lib/g/a
public class a {
    private static String a;

    public static void a(Context context) {
        String str0 = a.c(context);
        if (str0 == null) {
        }
        else {
            ActivityManager manager = (ActivityManager)context.getSystemService("activity");
            List list = manager.getRunningAppProcesses();
            if (list == null) {
            }
            else {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    ActivityManager$RunningAppProcessInfo info = (ActivityManager$RunningAppProcessInfo)iterator.next();
                    String str1 = info.processName;
                    if (str1.equals(str0)) {
                        Process.killProcess(info.pid);
                    }
                }
            }
        }
    }

    public static boolean b(Context context) {
        return TinkerPatchService.a(context);
    }

    public static String c(Context context) {
        if (a.a != null) {
            return a.a;
        }
        else {
            String str0 = a.a(context, TinkerPatchService.class);
            if (str0 == null) {
                return null;
            }
            else {
                a.a = str0;
                return a.a;
            }
        }
    }

    public static boolean d(Context context) {
        String str0 = a.l(context);
        String str1 = a.c(context);
        if (str1 == null || str1.length() == 0) {
            return false;
        }
        else {
            return str0.equals(str1);
        }
    }

    private static String a(Context context, Class<? extends Service> class) {
        PackageManager manager = context.getPackageManager();
        ComponentName name = new ComponentName(context, class);
        try {
            ServiceInfo info = manager.getServiceInfo(name, 0);
        }
        catch (Throwable var_5_0) {
            return null;
        }
        return info.processName;
    }

    static  {
        a.a = null;
    }

}
