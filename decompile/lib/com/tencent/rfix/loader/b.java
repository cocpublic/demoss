/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader;

import android.app.Application;
import android.content.IntentFilter;
import android.content.Intent;

// class: com/tencent/rfix/loader/b
public class b {
    private static boolean a;
    private static Application b;
    private static b$a c;
    private static RFixLoadResult d;

    public static void a(Application application) {
        if (b.a) {
            b.b = application;
            if (g.a(application)) {
                b.g();
            }
            b.a = true;
        }
    }

    public static void a(b$a b$a) {
        b.c = b$a;
    }

    public static RFixLoadResult a() {
        return b.d;
    }

    public static void b() {
        if (b.a) {
            RFixLog.e("RFix.RFixLoaderImmediate", "tryLoad not initialized?");
        }
        else {
            RFixLog.c("RFix.RFixLoaderImmediate", new StringBuilder().append("tryLoad processName=").append(g.c(b.b)).toString());
            b.a().a(new b$1());
        }
    }

    public static void c() {
        if (b.a) {
            RFixLog.e("RFix.RFixLoaderImmediate", "tryUnload not initialized?");
        }
        else {
            RFixLog.c("RFix.RFixLoaderImmediate", new StringBuilder().append("tryUnload processName=").append(g.c(b.b)).toString());
            int i0 = 0;
            try {
                e e = new e(b.b);
                boolean bool0 = e.c();
                goto 73;
            }
            catch (Throwable var_1_2) {
                RFixLog.e("RFix.RFixLoaderImmediate", "tryUnload unload patch fail!", var_1_2);
            }
            if (bool0) {
                b.a("ACTION_IMMEDIATE_UNLOAD");
                RFixLoadResult result = new RFixLoadResult();
                result.a = h$a.e;
                result.j = true;
                b.d = result;
                if (b.c != null) {
                    b.c.onLoadResult(result);
                }
            }
        }
    }

    private static void f() {
        b.a(a.c);
        RFixLoadResult resultVar1;
        try {
            c.a(b.b);
            e e = new e(b.b);
            resultVar1 = e.a();
            c.b(b.b, resultVar1);
            goto 67;
        }
        catch (Throwable var_1_1) {
            RFixLog.e("RFix.RFixLoaderImmediate", "tryLoad fail.", var_1_1);
            resultVar1 = new RFixLoadResult();
            resultVar1.a = h$a.m;
            resultVar1.d = var_1_1;
        }
        b.b(a.c);
        resultVar1.c = b.c(a.c);
        if (resultVar1.isLoaderSuccess()) {
            b.a("ACTION_IMMEDIATE_LOAD");
        }
        b.d = resultVar1;
        if (b.c != null) {
            b.c.onLoadResult(resultVar1);
        }
    }

    private static void g() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("ACTION_IMMEDIATE_LOAD");
        filter.addAction("ACTION_IMMEDIATE_UNLOAD");
        filter.addCategory(b.b.getPackageName());
        b$2 b$2 = new b$2();
        if (Build$VERSION.SDK_INT >= 34) {
            b.b.registerReceiver(b$2, filter, 2);
        }
        else {
            b.b.registerReceiver(b$2, filter);
        }
    }

    private static void a(String str0) {
        if (g.a(b.b)) {
        }
        else {
            Intent intent = new Intent(str0);
            intent.addCategory(b.b.getPackageName());
            b.b.sendBroadcast(intent);
        }
    }

    static /* synthetic */ void d() {
        b.f();
    }

    static /* synthetic */ Application e() {
        return b.b;
    }

    // class: com/tencent/rfix/loader/b$a
    public interface b$a {

        void onLoadResult(RFixLoadResult p0);

    }
    // class: com/tencent/rfix/loader/b$a
    public interface b$a {

        void onLoadResult(RFixLoadResult p0);

    }
}
