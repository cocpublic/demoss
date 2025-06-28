/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/verify;

import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tencent.rfix.loader.c.e;
import com.tencent.rfix.loader.entity.RFixLoadResult;
import com.tencent.rfix.loader.entity.a;
import com.tencent.rfix.loader.f.a;
import androidx.annotation.NonNull;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;

// class: com/tencent/rfix/lib/verify/AutoVerifyPatch
public class AutoVerifyPatch {
    private static Application a;
    private static RFixLoadResult b;
    private static AutoVerifyPatch$a c;
    private static boolean d;
    private static WeakReference<Activity> e;
    private static Dialog f;

    public AutoVerifyPatch() {
        super();
    }

    public static void a(Application application, RFixLoadResult result) {
        AutoVerifyPatch.a = application;
        AutoVerifyPatch.b = result;
        AutoVerifyPatch.c = new AutoVerifyPatch$a(null);
        AutoVerifyPatch.a.registerActivityLifecycleCallbacks(AutoVerifyPatch.c);
    }

    @Keep
    public static void updateLoadResult(RFixLoadResult result) {
        AutoVerifyPatch.b = result;
    }

    private static void c(@NonNull Activity activity) {
        Intent intent = activity.getIntent();
        if (intent.hasExtra("auto_verify_enable")) {
            boolean bool0 = intent.getBooleanExtra("auto_verify_enable", 0);
            String str0 = intent.getStringExtra("app_version");
            String str1 = intent.getStringExtra("user_id");
            RFixLog.c("RFix.AutoVerifyPatch", String.format("extractAutoVerifyParams autoVerifyEnable=%s appVersion=%s userId=%s", new Object[]{Boolean.valueOf(bool0), str0, str1}));
            e e = new e(activity);
            e.c = bool0;
            e.d = str0;
            e.e = str1;
            e.c();
        }
    }

    private static void d(@NonNull Activity activity) {
        e e = new e(activity);
        if (e.c) {
            AutoVerifyPatch.c();
        }
        else {
            long l0 = activity.getIntent().getLongExtra("show_dialog_delay", 0L) * 1000L;
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(AutoVerifyPatch::d, l0);
        }
    }

    private static void c() {
        if (AutoVerifyPatch.c != null) {
            AutoVerifyPatch.a.unregisterActivityLifecycleCallbacks(AutoVerifyPatch.c);
            AutoVerifyPatch.c = null;
        }
    }

    public static b a() {
        b b = new b();
        if (AutoVerifyPatch.b.isLoaderSuccess()) {
            b.b.a = AutoVerifyPatch.b.e.k;
            b.c.a = AutoVerifyPatch.b.e.l;
            b.d.a = AutoVerifyPatch.b.e.m;
            Object object = AutoVerifyPatch.a(AutoVerifyPatch.a.getClassLoader(), "com.tencent.rfix.verifycase.TestDex", null, new Object[]{});
            Object objectVar1 = AutoVerifyPatch.a(AutoVerifyPatch.a.getClassLoader(), "com.tencent.rfix.verifycase.TestLib", null, new Object[]{});
            Object objectVar2 = AutoVerifyPatch.a(AutoVerifyPatch.a.getClassLoader(), "com.tencent.rfix.verifycase.TestRes", new Class[]{Context.class}, new Object[]{AutoVerifyPatch.a});
            b.a = true;
            AutoVerifyPatch.a(b, b.b, Boolean.TRUE, object);
            AutoVerifyPatch.a(b, b.c, Boolean.TRUE, objectVar1);
            AutoVerifyPatch.a(b, b.d, Boolean.TRUE, objectVar2);
        }
        RFixLog.c("RFix.AutoVerifyPatch", new StringBuilder().append("getAutoVerifyResult result=").append(b).toString());
        return b;
    }

    private static Object a(ClassLoader loader, String str0, Class<?>[] classArr0, Object[] objectArr0) {
        try {
            Class class = Class.forName(str0, true, loader);
            Method method = class.getDeclaredMethod("isInPatch", classArr0);
            method.setAccessible(true);
            return method.invoke(null, objectArr0);
        }
        catch (Exception var_4_1) {
            RFixLog.c("RFix.AutoVerifyPatch", new StringBuilder().append("callIsInPatch fail! className=").append(str0).toString());
            return null;
        }
    }

    private static void a(b b, b$a b$a, Object object, Object objectVar1) {
        if (b$a.a) {
            b$a.b = object == objectVar1 ? 0 : true;
            b$a.c = objectVar1;
            b.a = b.a & b$a.b;
        }
    }

    private static /* synthetic */ void d() {
        AutoVerifyPatch.c();
        AutoVerifyPatch.f = new a((Context)AutoVerifyPatch.e.get());
        AutoVerifyPatch.f.setCancelable(false);
        AutoVerifyPatch.f.show();
    }

    static /* synthetic */ WeakReference a(WeakReference reference) {
        AutoVerifyPatch.e = reference;
        return reference;
    }

    static /* synthetic */ boolean b() {
        return AutoVerifyPatch.d;
    }

    static /* synthetic */ boolean a(boolean bool0) {
        AutoVerifyPatch.d = bool0;
        return bool0;
    }

    static /* synthetic */ void a(Activity activity) {
        AutoVerifyPatch.c(activity);
    }

    static /* synthetic */ void b(Activity activity) {
        AutoVerifyPatch.d(activity);
    }

    // class: com/tencent/rfix/lib/verify/AutoVerifyPatch$a
    class AutoVerifyPatch$a implements Application$ActivityLifecycleCallbacks {

        private AutoVerifyPatch$a() {
            super();
        }

        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        }

        public void onActivityStarted(@NonNull Activity activity) {
        }

        public void onActivityResumed(@NonNull Activity activity) {
            AutoVerifyPatch.a(new WeakReference(activity));
            if (AutoVerifyPatch.b()) {
            }
            else {
                try {
                    AutoVerifyPatch.a(true);
                    b.b(a.b);
                    AutoVerifyPatch.a(activity);
                    AutoVerifyPatch.b(activity);
                }
                catch (Exception var_2_0) {
                    RFixLog.e("RFix.AutoVerifyPatch", "onActivityResumed fail!", var_2_0);
                }
            }
        }

        public void onActivityPaused(@NonNull Activity activity) {
        }

        public void onActivityStopped(@NonNull Activity activity) {
        }

        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        }

        public void onActivityDestroyed(@NonNull Activity activity) {
        }

        /* synthetic */ AutoVerifyPatch$a(AutoVerifyPatch$1 patch$1) {
            super();
        }

    }
    // class: com/tencent/rfix/lib/verify/AutoVerifyPatch$a
    class AutoVerifyPatch$a implements Application$ActivityLifecycleCallbacks {

        private AutoVerifyPatch$a() {
            super();
        }

        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        }

        public void onActivityStarted(@NonNull Activity activity) {
        }

        public void onActivityResumed(@NonNull Activity activity) {
            AutoVerifyPatch.a(new WeakReference(activity));
            if (AutoVerifyPatch.b()) {
            }
            else {
                try {
                    AutoVerifyPatch.a(true);
                    b.b(a.b);
                    AutoVerifyPatch.a(activity);
                    AutoVerifyPatch.b(activity);
                }
                catch (Exception var_2_0) {
                    RFixLog.e("RFix.AutoVerifyPatch", "onActivityResumed fail!", var_2_0);
                }
            }
        }

        public void onActivityPaused(@NonNull Activity activity) {
        }

        public void onActivityStopped(@NonNull Activity activity) {
        }

        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        }

        public void onActivityDestroyed(@NonNull Activity activity) {
        }

        /* synthetic */ AutoVerifyPatch$a(AutoVerifyPatch$1 patch$1) {
            super();
        }

    }
}
