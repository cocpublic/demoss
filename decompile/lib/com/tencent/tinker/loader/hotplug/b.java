/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/hotplug;

import android.os.Handler;

// class: com/tencent/tinker/loader/hotplug/b
public final class b {
    private static volatile boolean a;
    private static d b;
    private static d c;
    private static a d;
    private static e e;

    public static void a(TinkerApplication application, l l) {
        if (b.a) {
            try {
                if (c.a(application, l)) {
                    b.b = new d(application, "activity", new a(application));
                    b.c = new d(application, "package", new c());
                    b.b.c();
                    b.c.c();
                    if (Build$VERSION.SDK_INT < 27) {
                        Handler handler = b.a(application);
                        b.d = new a(handler, new b(application));
                        b.d.c();
                    }
                    else {
                        b.e = e.a(application);
                        b.e.a();
                    }
                    b.a = true;
                    n.b("Tinker.ComponentHotplug", "installed successfully.", new Object[]{});
                }
            }
            catch (Throwable var_2_1) {
                b.a();
                throw new d(var_2_1);
            }
        }
        else {
        }
    }

    private static Handler a(Context context) {
        Object object = j.a(context, null);
        if (object == null) {
            throw new IllegalStateException("failed to fetch instance of ActivityThread.");
        }
        else {
            try {
                Field field = j.a(object, "mH");
                Handler handler = (Handler)field.get(object);
                return handler;
            }
            catch (Throwable var_2_1) {
                throw new IllegalStateException(var_2_1);
            }
        }
    }

    public static void a() {
        if (b.a) {
            try {
                b.b.d();
                b.c.d();
                if (Build$VERSION.SDK_INT < 27) {
                    b.d.d();
                }
                else {
                    b.e.b();
                }
            }
            catch (Throwable var_0_0) {
                n.d("Tinker.ComponentHotplug", "exception when uninstall.", new Object[]{var_0_0});
            }
            b.a = false;
        }
    }

    static  {
        b.a = false;
    }

}
