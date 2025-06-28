/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import android.os.Handler;
import android.os.Message;

// class: com/tencent/tinker/loader/shareutil/n
public class n {
    final private static Handler[] a;
    final private static n$a b;
    final private static n$a[] c;

    private static Handler d() {
        n.a;
        synchronized () {
            return n.a[0];
        }
    }

    public static n$a a() {
        return n.b;
    }

    public static void a(n$a n$a) {
        n.c;
        synchronized () {
            n.c[0] = n$a;
            if (n$a != null && n$a != n.b) {
                n.c();
            }
            return;
        }
    }

    public static n$a b() {
        n.c;
        synchronized () {
            return n.c[0];
        }
    }

    public static void a(String str0, String str1, Object[] objectArr0) {
        n.a(3, str0, str1, objectArr0);
    }

    public static void b(String str0, String str1, Object[] objectArr0) {
        n.a(4, str0, str1, objectArr0);
    }

    public static void c(String str0, String str1, Object[] objectArr0) {
        n.a(5, str0, str1, objectArr0);
    }

    public static void d(String str0, String str1, Object[] objectArr0) {
        n.a(6, str0, str1, objectArr0);
    }

    public static void a(String str0, Throwable throwable, String str1, Object[] objectArr0) {
        n.b(str0, throwable, str1, objectArr0);
    }

    public static void c() {
        Handler handler = n.d();
        if (handler != null) {
            Message message = Message.obtain(handler, 4002);
            handler.handleMessage(message);
            message.recycle();
        }
    }

    private static void a(int i0, String str0, String str1, Object[] objectArr0) {
        long l0 = System.currentTimeMillis();
        Object object = new Object[]{Integer.valueOf(i0), Long.valueOf(l0), str0, str1, objectArr0};
        Handler handler = n.d();
        if (handler != null) {
            Message message = Message.obtain(handler, i0, object);
            handler.handleMessage(message);
            message.recycle();
        }
        else {
            n.b.e(str0, new StringBuilder().append("!! NO_LOG_IMPL !! Original Log: ").append(str1).toString(), objectArr0);
        }
    }

    private static void b(String str0, Throwable throwable, String str1, Object[] objectArr0) {
        long l0 = System.currentTimeMillis();
        Object object = new Object[]{Integer.valueOf(4001), Long.valueOf(l0), str0, throwable, str1, objectArr0};
        Handler handler = n.d();
        if (handler != null) {
            Message message = Message.obtain(handler, 4001, object);
            handler.handleMessage(message);
            message.recycle();
        }
        else {
            n.b.a(str0, throwable, new StringBuilder().append("!! NO_LOG_IMPL !! Original Log: ").append(str1).toString(), objectArr0);
        }
    }

    static  {
        n.a = new Handler[]{null};
        n.b = new n$1();
        n.c = new n$a[]{n.b};
        n.a;
        synchronized () {
            try {
                Class class = Class.forName("com.tencent.tinker.loader.shareutil.TinkerLogInlineFence");
                Constructor constructor = class.getDeclaredConstructor(new Class[]{});
                constructor.setAccessible(true);
                n.a[0] = (Handler)constructor.newInstance(new Object[]{});
            }
            catch (Throwable var_1_1) {
                Log.e("Tinker.ShareTinkerLog", "[-] Fail to create inline fence instance.", var_1_1);
                n.a[0] = null;
            }
            return;
        }
    }

    // class: com/tencent/tinker/loader/shareutil/n$a
    public interface n$a {

        void a(String p0, String p1, Object[] p2);

        void b(String p0, String p1, Object[] p2);

        void c(String p0, String p1, Object[] p2);

        void d(String p0, String p1, Object[] p2);

        void e(String p0, String p1, Object[] p2);

        void a(String p0, Throwable p1, String p2, Object[] p3);

    }
    // class: com/tencent/tinker/loader/shareutil/n$a
    public interface n$a {

        void a(String p0, String p1, Object[] p2);

        void b(String p0, String p1, Object[] p2);

        void c(String p0, String p1, Object[] p2);

        void d(String p0, String p1, Object[] p2);

        void e(String p0, String p1, Object[] p2);

        void a(String p0, Throwable p1, String p2, Object[] p3);

    }
}
