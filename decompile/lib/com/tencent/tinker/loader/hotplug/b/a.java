/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/hotplug/b;

import android.os.Handler;
import android.os.Handler$Callback;

// class: com/tencent/tinker/loader/hotplug/b/a
public class a {
    final private Handler a;
    final private a$b b;
    private static Field c;

    public a(Handler handler, a$b a$b) {
        super();
        this.a = handler;
        this.b = a$b;
    }

    protected Handler$Callback a() {
        return (Handler$Callback)a.c.get(this.a);
    }

    protected Handler$Callback a(Handler$Callback callback) {
        if (callback != null && c$a.class.isAssignableFrom(callback.getClass())) {
            return callback;
        }
        else {
            return new a$a(this.b, callback);
        }
    }

    protected void b(Handler$Callback callback) {
        a.c.set(this.a, callback);
    }

    protected /* synthetic */ void a(Object object) {
        this.b((Handler$Callback)object);
    }

    protected /* synthetic */ Object b(Object object) {
        return this.a((Handler$Callback)object);
    }

    protected /* synthetic */ Object b() {
        return this.a();
    }

    static  {
        a.c = null;
        Class class = a.class;
        a.class;
        synchronized () {
            if (a.c == null) {
                try {
                    a.c = j.a(Handler.class, "mCallback");
                }
                catch (Throwable var_1_0) {
                }
            }
            return;
        }
    }

    // class: com/tencent/tinker/loader/hotplug/b/a$a
    class a$a implements Handler$Callback, c$a {
        final private a$b a;
        final private Handler$Callback b;
        private volatile boolean c;

         a$a(a$b a$b, Handler$Callback callback) {
            super();
            this.a = a$b;
            this.b = callback;
            this.c = false;
        }

        public boolean handleMessage(Message message) {
            int i1 = false;
            if (this.c) {
                return i1;
            }
            else {
                this.c = true;
                if (this.a.a(message)) {
                    i1 = 1;
                }
                else if (this.b != null) {
                    boolean i0 = this.b.handleMessage(message);
                }
                this.c = false;
                return i1;
            }
        }

    }
    // class: com/tencent/tinker/loader/hotplug/b/a$a
    class a$a implements Handler$Callback, c$a {
        final private a$b a;
        final private Handler$Callback b;
        private volatile boolean c;

         a$a(a$b a$b, Handler$Callback callback) {
            super();
            this.a = a$b;
            this.b = callback;
            this.c = false;
        }

        public boolean handleMessage(Message message) {
            int i1 = false;
            if (this.c) {
                return i1;
            }
            else {
                this.c = true;
                if (this.a.a(message)) {
                    i1 = 1;
                }
                else if (this.b != null) {
                    boolean i0 = this.b.handleMessage(message);
                }
                this.c = false;
                return i1;
            }
        }

    }
    // class: com/tencent/tinker/loader/hotplug/b/a$b
    public interface a$b {

        boolean a(Message p0);

    }
    // class: com/tencent/tinker/loader/hotplug/b/a$b
    public interface a$b {

        boolean a(Message p0);

    }
}
