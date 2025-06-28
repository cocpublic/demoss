/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import android.os.Handler;
import android.os.Handler$Callback;

// class: com/tencent/tinker/loader/b
public final class b {

    public static boolean a(Application application) {
        if (Build$VERSION.SDK_INT < 26) {
            Log.i("Tinker.AppInfoChangedBlocker", "tryStart: SDK_INT is less than 26, skip rest logic.");
            return true;
        }
        else {
            try {
                n.b("Tinker.AppInfoChangedBlocker", "tryStart called.", new Object[]{});
                b.a(b.a(application));
                n.b("Tinker.AppInfoChangedBlocker", "tryStart done.", new Object[]{});
                return true;
            }
            catch (Throwable var_1_0) {
                n.d("Tinker.AppInfoChangedBlocker", "AppInfoChangedBlocker start failed, simply ignore.", new Object[]{var_1_0});
                return false;
            }
        }
    }

    private static Handler a(Context context) {
        Object object = j.a(context, null);
        Field field = j.a(object, "mH");
        return (Handler)field.get(object);
    }

    private static void a(Handler handler) {
        Field field = j.a(Handler.class, "mCallback");
        Handler$Callback callback = (Handler$Callback)field.get(handler);
        if ((callback instanceof b$a)) {
            b$a b$a = new b$a(callback, handler.getClass());
            field.set(handler, b$a);
        }
        else {
            n.c("Tinker.AppInfoChangedBlocker", "Already intercepted, skip rest logic.", new Object[]{});
        }
    }

    // class: com/tencent/tinker/loader/b$a
    class b$a implements Handler$Callback {
        final private int a;
        private Handler$Callback b;

         b$a(Handler$Callback callback, Class class) {
            super();
            this.b = callback;
            int i1;
            try {
                i1 = j.a(class, "APPLICATION_INFO_CHANGED").getInt(null);
            }
            catch (Throwable var_4_0) {
                i1 = 156;
            }
            this.a = i1;
        }

        public boolean handleMessage(Message message) {
            int i1 = false;
            if (this.a(message)) {
                i1 = 1;
            }
            else if (this.b != null) {
                boolean i0 = this.b.handleMessage(message);
            }
            return i1;
        }

        private boolean a(Message message) {
            if (message.what == this.a) {
                n.c("Tinker.AppInfoChangedBlocker", "Suicide now.", new Object[]{});
                Process.killProcess(Process.myPid());
                return true;
            }
            else {
                return false;
            }
        }

    }
    // class: com/tencent/tinker/loader/b$a
    class b$a implements Handler$Callback {
        final private int a;
        private Handler$Callback b;

         b$a(Handler$Callback callback, Class class) {
            super();
            this.b = callback;
            int i1;
            try {
                i1 = j.a(class, "APPLICATION_INFO_CHANGED").getInt(null);
            }
            catch (Throwable var_4_0) {
                i1 = 156;
            }
            this.a = i1;
        }

        public boolean handleMessage(Message message) {
            int i1 = false;
            if (this.a(message)) {
                i1 = 1;
            }
            else if (this.b != null) {
                boolean i0 = this.b.handleMessage(message);
            }
            return i1;
        }

        private boolean a(Message message) {
            if (message.what == this.a) {
                n.c("Tinker.AppInfoChangedBlocker", "Suicide now.", new Object[]{});
                Process.killProcess(Process.myPid());
                return true;
            }
            else {
                return false;
            }
        }

    }
}
