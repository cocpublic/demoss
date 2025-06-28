/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/a;

import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import com.tencent.rfix.loader.h.h$a;
import android.os.Handler;
import android.os.Looper;

// class: com/tencent/rfix/loader/a/c
public class c {
    protected static volatile boolean a;
    protected static volatile boolean b;
    protected static volatile long c;
    protected static volatile boolean d;
    private static c$a e;
    private static volatile boolean f;

    public static c$a a() {
        return c.e;
    }

    public static void a(Context context) {
        RFixLog.b("RFix.RFixSafeModeKeeper", new StringBuilder().append("initialize strategy=").append(c.e).toString());
        c.a = true;
        c.b = false;
        if (c.f) {
            c.f = true;
            a a = new a(context);
            Thread.setDefaultUncaughtExceptionHandler(a);
        }
    }

    public static int a(Context context, RFixLoadResult result) {
        v_0 = new b(context, c.c(context));
        b b = new b(context, c.c(context));
        if (c.a(b)) {
            return -1;
        }
        else {
            if (c.b(b)) {
                result.k = 254;
            }
            if (c.a(b.c)) {
                return 253;
            }
            else {
                v_20.a = b.a + 1;
                c.d = true;
                b.c();
                return 0;
            }
        }
    }

    private static boolean a(b b) {
        if (b.a >= c.e.c) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean b(b b) {
        if (b.b >= c.e.d) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean a(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        else {
            if (list.size() >= c.e.e && 120000L <= (Long)list.get(list.size() - 1).longValue() - (Long)list.get(0).longValue()) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public static void b(Context context, RFixLoadResult result) {
        c.a = false;
        if (result.a == h$a.a) {
            c.b = true;
            c.c = SystemClock.elapsedRealtime();
        }
        if (c.d) {
        }
        else {
            v_7 = new b(context, c.c(context));
            b b = new b(context, c.c(context));
            b.a = 0;
            if (result.a == h$a.a) {
                v_27.b = b.b + 1;
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new c$1(b), 2000L);
            }
            b.c();
        }
    }

    public static void b(Context context) {
        b b = new b(context, c.c(context));
        b.a = 0;
        b.b = 0;
        b.c = new ArrayList();
        b.c();
    }

    protected static void a(Context context, Throwable throwable) {
        String str0 = c.a(throwable);
        if (c.a) {
            c.b(context, str0);
        }
        else if (c.b) {
            c.a(context, str0);
        }
        else {
            RFixLog.d("RFix.RFixSafeModeKeeper", "handleJavaCrash no patch load, ignore!");
        }
    }

    protected static void a(Context context, String str0) {
        long l0 = SystemClock.elapsedRealtime();
        boolean bool0 = c.a(l0, c.c);
        RFixLog.c("RFix.RFixSafeModeKeeper", String.format("handlePatchCrash isP3Crash=%s", new Object[]{Boolean.valueOf(bool0)}));
        if (bool0) {
            b b = new b(context, c.c(context));
            b.c.add(Long.valueOf(System.currentTimeMillis()));
            if (b.c.size() > c.e.e) {
                b.c.remove(0);
            }
            b.c();
            c.b(context, str0);
        }
    }

    protected static boolean a(long l1, long l1) {
        long l4 = l1 - l3;
        RFixLog.c("RFix.RFixSafeModeKeeper", new StringBuilder().append("isP3Crash loadTime = ").append(l3).append(", currentTime = ").append(l1).append(", elapsedTime = ").append(l4).toString());
        if (0L > l1 && 0L > l3 && 0L > l4 && 10000L < l4) {
            return true;
        }
        else {
            return false;
        }
    }

    public static String a(Throwable throwable) {
        Object object = null;
        Object objectVar1 = null;
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(stream);
            throwable.printStackTrace(writer);
            writer.flush();
            String str0 = stream.toString();
        }
        catch (Throwable var_3_1) {
            RFixLog.e("RFix.RFixSafeModeKeeper", "throwableToString fail!", var_3_1);
        }
        finally {
            Throwable throwableVar1 = v_12;
            e.a(writer);
            throw throwableVar1;
        }
        return str0;
    }

    public static void b(Context context, String str0) {
        RFixLog.d("RFix.RFixSafeModeKeeper", String.format("writeLastCrashFile content=
%s", new Object[]{str0}));
        File file = e.c(context);
        File fileVar1 = file.getParentFile();
        if (fileVar1.exists()) {
            fileVar1.mkdirs();
        }
        Object object = null;
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file, 0));
            writer.write(str0);
            return;
        }
        catch (Throwable var_5_0) {
            RFixLog.e("RFix.RFixSafeModeKeeper", "writeLastCrashFile fail!", var_5_0);
            return;
        }
        finally {
            Throwable throwable = v_24;
            e.a(writer);
            throw throwable;
        }
    }

    private static String c(Context context) {
        if (c.e == c$a.b) {
            return "rfix_safe_mode_info";
        }
        else {
            String str2 = "";
            String str1 = g.c(context);
            int i0 = str1.indexOf(58);
            if (i0 >= 0) {
                str2 = str1.substring(i0);
            }
            return new StringBuilder().append("rfix_safe_mode_info").append(str2).toString();
        }
    }

    static  {
        c.e = c$a.a;
    }

    // class: com/tencent/rfix/loader/a/c$a
    public final enum c$a {
        final public int c;
        final public int d;
        final public int e;
        final private static synthetic c$a[] f;

        privatevoid c$a(String str0, int i0, int i1, int i2, int i3) {
            this.c = i1;
            this.d = i2;
            this.e = i3;
        }

        static  {
            c$a.a = new c$a("MAIN_PROCESS", 0, 2, 5, 3);
            c$a.b = new c$a("MULTI_PROCESS", 1, 5, 5, 5);
            c$a.f = new c$a[]{c$a.a, c$a.b};
        }

    }
    // class: com/tencent/rfix/loader/a/c$a
    public final enum c$a {
        final public int c;
        final public int d;
        final public int e;
        final private static synthetic c$a[] f;

        privatevoid c$a(String str0, int i0, int i1, int i2, int i3) {
            this.c = i1;
            this.d = i2;
            this.e = i3;
        }

        static  {
            c$a.a = new c$a("MAIN_PROCESS", 0, 2, 5, 3);
            c$a.b = new c$a("MULTI_PROCESS", 1, 5, 5, 5);
            c$a.f = new c$a[]{c$a.a, c$a.b};
        }

    }
}
