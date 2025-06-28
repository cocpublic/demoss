/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/d;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;

// class: com/tencent/rfix/loader/d/b
public class b implements a {
    private static a a;
    final private ExecutorService b;

    public static a a() {
        if (b.a == null) {
            Class class = b.class;
            b.class;
            synchronized () {
                if (b.a == null) {
                    b.a = new b();
                }
            }
        }
        return b.a;
    }

    private b() {
        super();
        this.b = new ThreadPoolExecutor(0, 5, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public void a(Runnable runnable) {
        this.a(runnable, a$a.a);
    }

    public void a(Runnable runnable, a$a a$a) {
        this.b.execute(runnable);
    }

    static  {
        b.a = null;
    }

}
