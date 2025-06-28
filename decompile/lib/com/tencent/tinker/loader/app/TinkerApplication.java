/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/app;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.AssetManager;
import android.content.res.Resources$Theme;
import android.content.Context;
import android.os.Handler;
import android.annotation.TargetApi;
import com.tencent.tinker.loader.k;
import com.tencent.tinker.loader.m;
import com.tencent.tinker.anno.Keep;

// class: com/tencent/tinker/loader/app/TinkerApplication
public abstract class TinkerApplication {
    final private static TinkerApplication[] c;
    final private int d;
    final private boolean e;
    final private String f;
    final private String g;
    private boolean h;
    protected Intent a;
    protected ClassLoader b;
    private Handler i;
    final private boolean j;
    final private boolean k;

    protected TinkerApplication(int bool0, String str0, String str1, boolean bool1, boolean bool2) {
        super(bool0, str0, str1, bool1, bool2, 0);
    }

    protected TinkerApplication(int i0, String str0, String str1, boolean bool0, boolean bool1, boolean bool2) {
        super();
        this.b = null;
        this.i = null;
        TinkerApplication.c;
        synchronized () {
            TinkerApplication.c[0] = this;
        }
        this.d = i0;
        this.f = str0;
        this.g = str1;
        this.e = bool0;
        this.j = bool1;
        this.k = bool2;
    }

    public static TinkerApplication a() {
        TinkerApplication.c;
        synchronized () {
            if (TinkerApplication.c[0] == null) {
                throw new IllegalStateException("TinkerApplication is not initialized.");
            }
            else {
                return TinkerApplication.c[0];
            }
        }
    }

    private void f() {
        try {
            Class class = Class.forName(this.g, false, TinkerApplication.class.getClassLoader());
            Method method = class.getMethod("tryLoad", new Class[]{TinkerApplication.class});
            Constructor constructor = class.getConstructor(new Class[]{});
            this.a = (Intent)method.invoke(constructor.newInstance(new Object[]{}), new Object[]{this});
        }
        catch (Throwable var_1_1) {
            this.a = new Intent();
            g.a(this.a, 236);
            this.a.putExtra("intent_patch_exception", var_1_1);
        }
    }

    private Handler a(Application application, int i0, String str0, boolean bool0, long l1, long l1, Intent l3) {
        try {
            Class class = Class.forName(str0, false, this.b);
            Constructor constructor = class.getConstructor(new Class[]{Application.class, Integer.TYPE, Boolean.TYPE, Long.TYPE, Long.TYPE, Intent.class});
            Object object = constructor.newInstance(new Object[]{application, Integer.valueOf(i0), Boolean.valueOf(bool0), Long.valueOf(l1), Long.valueOf(l3), intent});
            Class classVar1 = Class.forName("com.tencent.tinker.entry.TinkerApplicationInlineFence", false, this.b);
            Class classVar2 = Class.forName("com.tencent.tinker.entry.ApplicationLike", false, this.b);
            Constructor constructorVar1 = classVar1.getConstructor(new Class[]{classVar2});
            constructorVar1.setAccessible(true);
            return (Handler)constructorVar1.newInstance(new Object[]{object});
        }
        catch (Throwable var_10_1) {
            throw new k("createInlineFence failed", var_10_1);
        }
    }

    protected void onBaseContextAttached(Context context, long l1, long l1) {
        try {
            this.f();
            this.b = context.getClassLoader();
            this.i = this.a(this, this.d, this.f, this.e, l1, l3, this.a);
            a.a(this.i, context);
            if (this.h) {
                m.a(this, 0);
            }
            return;
        }
        catch (k var_6_0) {
            throw var_6_0;
        }
        catch (Throwable var_6_1) {
            throw new k(var_6_1.getMessage(), var_6_1);
        }
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        long l0 = SystemClock.elapsedRealtime();
        long l1 = System.currentTimeMillis();
        Thread.setDefaultUncaughtExceptionHandler(new m(this));
        this.onBaseContextAttached(context, l0, l1);
    }

    public void onCreate() {
        super.onCreate();
        if (this.i == null) {
        }
        else {
            a.a(this.i);
        }
    }

    public void onTerminate() {
        super.onTerminate();
        if (this.i == null) {
        }
        else {
            a.c(this.i);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.i == null) {
        }
        else {
            a.b(this.i);
        }
    }

    @TargetApi(14)
    public void onTrimMemory(int i0) {
        super.onTrimMemory(i0);
        if (this.i == null) {
        }
        else {
            a.a(this.i, i0);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.i == null) {
        }
        else {
            a.a(this.i, configuration);
        }
    }

    public Resources getResources() {
        Resources resources = super.getResources();
        if (this.i == null) {
            return resources;
        }
        else {
            return a.a(this.i, resources);
        }
    }

    public ClassLoader getClassLoader() {
        ClassLoader loader = super.getClassLoader();
        if (this.i == null) {
            return loader;
        }
        else {
            return a.a(this.i, loader);
        }
    }

    public AssetManager getAssets() {
        AssetManager manager = super.getAssets();
        if (this.i == null) {
            return manager;
        }
        else {
            return a.a(this.i, manager);
        }
    }

    public Object getSystemService(String str0) {
        Object object = super.getSystemService(str0);
        if (this.i == null) {
            return object;
        }
        else {
            return a.a(this.i, str0, object);
        }
    }

    public Context getBaseContext() {
        Context context = super.getBaseContext();
        if (this.i == null) {
            return context;
        }
        else {
            return a.b(this.i, context);
        }
    }

    public Resources$Theme getTheme() {
        Resources$Theme theme = super.getTheme();
        if (this.i == null) {
            return theme;
        }
        else {
            return a.a(this.i, theme);
        }
    }

    @Keep
    public int mzNightModeUseOf() {
        if (this.i == null) {
            return 1;
        }
        else {
            return a.d(this.i);
        }
    }

    public void a(boolean bool0) {
        this.h = bool0;
    }

    public boolean b() {
        return this.e;
    }

    public int c() {
        return this.d;
    }

    public boolean d() {
        return this.j;
    }

    public boolean e() {
        return this.k;
    }

    static  {
        TinkerApplication.c = new TinkerApplication[]{null};
    }

}
