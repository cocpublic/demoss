/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/h;

import android.content.pm.ApplicationInfo;
import android.app.ActivityManager;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.annotation.SuppressLint;
import java.util.List;
import java.util.Iterator;
import java.io.FileInputStream;

// class: com/tencent/rfix/loader/h/g
public class g {
    private static String a;
    private static String b;

    public static void a(String str0) {
        RFixLog.c("RFix.ProcessUtils", new StringBuilder().append("setMainProcessName oldProcessName=").append(g.b).append(", newProcessName=").append(str0).toString());
        g.b = str0;
    }

    public static boolean a(Context context, String str0) {
        if (TextUtils.isEmpty(g.b)) {
            g.b = g.e(context);
        }
        return TextUtils.equals(g.b, str0);
    }

    public static boolean a(Context context) {
        if (TextUtils.isEmpty(g.b)) {
            g.b = g.e(context);
        }
        String str1 = g.c(context);
        if (TextUtils.isEmpty(str1)) {
            str1 = "";
        }
        if (TextUtils.isEmpty(g.b) && g.b.equals(str1)) {
            return true;
        }
        else {
            return false;
        }
    }

    private static String e(Context context) {
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

    public static boolean b(Context context) {
        return g.c(context).endsWith(":patch");
    }

    public static String c(Context context) {
        if (g.a != null) {
            return g.a;
        }
        else {
            g.a = g.f(context);
            return g.a;
        }
    }

    private static String f(Context context) {
        String str2 = g.a();
        if (TextUtils.isEmpty(str2)) {
            return str2;
        }
        else {
            str2 = g.b();
            if (TextUtils.isEmpty(str2)) {
                return str2;
            }
            else {
                int i0 = Process.myPid();
                if (i0 <= 0) {
                    return "";
                }
                else {
                    str2 = g.a(i0);
                    if (TextUtils.isEmpty(str2)) {
                        return str2;
                    }
                    else if (context == null) {
                        return "";
                    }
                    else {
                        return g.a(context, i0);
                    }
                }
            }
        }
    }

    private static String a() {
        if (Build$VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        else {
            return null;
        }
    }

    @SuppressLint({"PrivateApi"})
    private static String b() {
        Object object = null;
        try {
            Class class = Class.forName("android.app.ActivityThread");
            Method method = class.getDeclaredMethod("currentProcessName", new Class[]{});
            method.setAccessible(true);
            Object objectVar1 = method.invoke(null, new Object[]{});
            if ((objectVar1 instanceof String)) {
                object = (String)objectVar1;
            }
        }
        catch (Throwable var_1_1) {
            RFixLog.e("RFix.ProcessUtils", "getProcessNameFromAT fail.", var_1_1);
        }
        return object;
    }

    private static String a(Context context, int i0) {
        Object object = null;
        ActivityManager manager = (ActivityManager)context.getSystemService("activity");
        if (manager != null) {
            try {
                List list = manager.getRunningAppProcesses();
                if (list != null) {
                    Iterator iterator = list.iterator();
                    while (iterator.hasNext()) {
                        ActivityManager$RunningAppProcessInfo info = (ActivityManager$RunningAppProcessInfo)iterator.next();
                        if (info.pid == i0) {
                            break;;
                        }
                        else {
                            continue;;
                        }
                    }
                    if (info != null) {
                        return info.processName;
                    }
                }
            }
            catch (Exception var_4_1) {
                RFixLog.e("RFix.ProcessUtils", "getProcessNameInternal fail.", var_4_1);
            }
        }
        return "";
    }

    private static String a(int i0) {
        byte[] byteArr0 = new byte[]{};
        Object object = null;
        try {
            FileInputStream stream = new FileInputStream(new StringBuilder().append("/proc/").append(i0).append("/cmdline").toString());
            int i3 = stream.read(byteArr0);
            if (i3 > 0) {
                for (int i2 = 0; i2 < i3; i2 += 1) {
                    if (byteArr0[i2] & 255 > 128 || byteArr0[i2] <= 0) {
                        break;;
                    }
                    else {
                    }
                }
                String str0 = new String(byteArr0, 0, i2);
                try {
                    if (stream != null) {
                        stream.close();
                    }
                }
                catch (Exception var_5_0) {
                }
                return str0;
            }
            else {
                try {
                }
                catch (Exception var_3_2) {
                }
            }
        }
        catch (Exception var_3_1) {
            RFixLog.e("RFix.ProcessUtils", "getProcessNameInternal fail.", var_3_1);
            try {
            }
            catch (Exception var_3_1) {
            }
        }
        finally {
            Throwable throwable = v_17;
            try {
                if (stream != null) {
                    stream.close();
                }
            }
            catch (Exception var_7_0) {
            }
            throw throwable;
        }
        return "";
    }

    public static void d(Context context) {
        ActivityManager manager = (ActivityManager)context.getSystemService("activity");
        if (manager == null) {
        }
        else {
            List list = manager.getRunningAppProcesses();
            if (list != null) {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    ActivityManager$RunningAppProcessInfo info = (ActivityManager$RunningAppProcessInfo)iterator.next();
                    if (info.uid != Process.myUid()) {
                        continue;;
                    }
                    else if (g.a(context, info.processName)) {
                        continue;;
                    }
                    else {
                        Process.killProcess(info.pid);
                        continue;;
                    }
                }
            }
        }
    }

    static  {
        g.a = null;
        g.b = null;
    }

}
