/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/entry;

import android.app.Application;
import android.content.Intent;
import com.tencent.tinker.anno.Keep;

// class: com/tencent/tinker/entry/ApplicationLike
public abstract class ApplicationLike implements ApplicationLifeCycle {
    final private Application a;
    final private Intent b;
    final private long c;
    final private long d;
    final private int e;
    final private boolean f;

    public ApplicationLike(Application application, int i0, boolean bool0, long l1, long l1, Intent l3) {
        super();
        this.a = application;
        this.e = i0;
        this.f = bool0;
        this.c = l1;
        this.d = l3;
        this.b = intent;
    }

    @Keep
    public Application getApplication() {
        return this.a;
    }

    final public Intent a() {
        return this.b;
    }

    final public int b() {
        return this.e;
    }

    final public boolean c() {
        return this.f;
    }

    public void onCreate() {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int i0) {
    }

    public void onTerminate() {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onBaseContextAttached(Context context) {
    }

    @Keep
    public Resources getResources(Resources resources) {
        return resources;
    }

    @Keep
    public ClassLoader getClassLoader(ClassLoader loader) {
        return loader;
    }

    @Keep
    public AssetManager getAssets(AssetManager manager) {
        return manager;
    }

    @Keep
    public Object getSystemService(String str0, Object object) {
        return object;
    }

    @Keep
    public Context getBaseContext(Context context) {
        return context;
    }

    @Keep
    public Resources$Theme getTheme(Resources$Theme theme) {
        return theme;
    }

    @Keep
    public int mzNightModeUseOf() {
        return 1;
    }

}
