/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/d;


// class: com/tencent/rfix/loader/d/a
public interface a {

    void a(Runnable p0);

    void a(Runnable p0, a$a p1);

    // class: com/tencent/rfix/loader/d/a$a
    public final enum a$a {
        final private static synthetic a$a[] d;

        privatevoid a$a(String str0, int i0) {
        }

        static  {
            a$a.a = new a$a("THREAD_DEFAULT", 0);
            a$a.b = new a$a("THREAD_NETWORK", 1);
            a$a.c = new a$a("THREAD_IO", 2);
            a$a.d = new a$a[]{a$a.a, a$a.b, a$a.c};
        }

    }
    // class: com/tencent/rfix/loader/d/a$a
    public final enum a$a {
        final private static synthetic a$a[] d;

        privatevoid a$a(String str0, int i0) {
        }

        static  {
            a$a.a = new a$a("THREAD_DEFAULT", 0);
            a$a.b = new a$a("THREAD_NETWORK", 1);
            a$a.c = new a$a("THREAD_IO", 2);
            a$a.d = new a$a[]{a$a.a, a$a.b, a$a.c};
        }

    }
}
