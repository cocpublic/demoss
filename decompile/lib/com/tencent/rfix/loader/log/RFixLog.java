/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/log;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.Keep;

// class: com/tencent/rfix/loader/log/RFixLog
public class RFixLog {
    final private static IRFixLog a;
    final private static Object b;
    private static IRFixLog c;
    private static Handler d;

    public RFixLog() {
        super();
    }

    @Keep
    public static void setLogImpl(IRFixLog log) {
        RFixLog.b;
        synchronized () {
            RFixLog.c = log;
            if (RFixLog.c != RFixLog.a) {
                RFixLog.c();
            }
            return;
        }
    }

    public static IRFixLog a() {
        RFixLog.b;
        synchronized () {
            return RFixLog.c;
        }
    }

    public static boolean b() {
        RFixLog.b;
        synchronized () {
            return RFixLog.c == RFixLog.a ? 0 : true;
        }
    }

    private static void a(int i0, String str0, String str1, Throwable throwable) {
        long l0 = System.currentTimeMillis();
        Object object = new Object[]{Integer.valueOf(i0), Long.valueOf(l0), str0, str1, throwable};
        if (RFixLog.d != null) {
            Message message = Message.obtain(RFixLog.d, i0, object);
            RFixLog.d.handleMessage(message);
            message.recycle();
        }
    }

    private static void c() {
        if (RFixLog.d != null) {
            Message message = Message.obtain(RFixLog.d, 240);
            RFixLog.d.handleMessage(message);
            message.recycle();
        }
    }

    public static void a(String str0, String str1) {
        RFixLog.a(2, str0, str1, null);
    }

    public static void a(String str0, String str1, Throwable throwable) {
        RFixLog.a(2, str0, str1, throwable);
    }

    public static void b(String str0, String str1) {
        RFixLog.a(3, str0, str1, null);
    }

    public static void b(String str0, String str1, Throwable throwable) {
        RFixLog.a(3, str0, str1, throwable);
    }

    public static void c(String str0, String str1) {
        RFixLog.a(4, str0, str1, null);
    }

    public static void c(String str0, String str1, Throwable throwable) {
        RFixLog.a(5, str0, str1, throwable);
    }

    public static void d(String str0, String str1) {
        RFixLog.a(5, str0, str1, null);
    }

    public static void d(String str0, String str1, Throwable throwable) {
        RFixLog.a(5, str0, str1, throwable);
    }

    public static void e(String str0, String str1) {
        RFixLog.a(6, str0, str1, null);
    }

    public static void e(String str0, String str1, Throwable throwable) {
        RFixLog.a(6, str0, str1, throwable);
    }

    static  {
        RFixLog.a = new a();
        RFixLog.b = new Object();
        RFixLog.c = RFixLog.a;
        RFixLog.d = null;
        RFixLog.b;
        synchronized () {
            try {
                Class class = Class.forName("com.tencent.rfix.loader.log.RFixLogInlineFence");
                Constructor constructor = class.getDeclaredConstructor(new Class[]{});
                constructor.setAccessible(true);
                RFixLog.d = (Handler)constructor.newInstance(new Object[]{});
            }
            catch (Throwable var_1_1) {
                Log.e("RFix.RFixLog", "create inline fence instance fail.", var_1_1);
            }
            return;
        }
    }

}
