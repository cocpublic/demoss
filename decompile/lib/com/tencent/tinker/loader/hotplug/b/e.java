/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/hotplug/b;

import android.app.Instrumentation;
import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.os.Parcelable;
import com.tencent.tinker.loader.k;

// class: com/tencent/tinker/loader/hotplug/b/e
public class e {
    final private Instrumentation a;
    final private Object b;
    final private Field c;

    public static e a(Context context) {
        try {
            Object object = j.a(context, null);
            Field field = j.a(object, "mInstrumentation");
            Instrumentation instrumentation = (Instrumentation)field.get(object);
            if ((instrumentation instanceof e)) {
                return (e)instrumentation;
            }
            else {
                return new e(instrumentation, object, field);
            }
        }
        catch (Throwable var_1_1) {
            throw new k("see next stacktrace", var_1_1);
        }
    }

    public void a() {
        if ((this.c.get(this.b) instanceof e)) {
            n.c("Tinker.Instrumentation", "already installed, skip rest logic.", new Object[]{});
        }
        else {
            this.c.set(this.b, this);
        }
    }

    public void b() {
        this.c.set(this.b, this.a);
    }

    private e(Instrumentation instrumentation, Object object, Field field) {
        super();
        this.a = instrumentation;
        this.b = object;
        this.c = field;
        try {
            this.a(instrumentation);
            return;
        }
        catch (Throwable var_4_0) {
            throw new k(var_4_0.getMessage(), var_4_0);
        }
    }

    public Activity newActivity(Class<?> class, Context context, IBinder binder, Application application, Intent intent, ActivityInfo info, CharSequence sequence, Activity activity, String str0, Object object) {
        this.a(context.getClassLoader(), intent);
        return super.newActivity(class, context, binder, application, intent, info, sequence, activity, str0, object);
    }

    public Activity newActivity(ClassLoader loader, String str0, Intent intent) {
        if (this.a(loader, intent)) {
            return super.newActivity(loader, intent.getComponent().getClassName(), intent);
        }
        else {
            return super.newActivity(loader, str0, intent);
        }
    }

    public void callActivityOnCreate(Activity activity, Bundle bundle) {
        if (activity != null) {
            ActivityInfo info = c.b(activity.getClass().getName());
            if (info != null) {
                this.a(activity, info);
            }
        }
        super.callActivityOnCreate(activity, bundle);
    }

    public void callActivityOnCreate(Activity activity, Bundle bundle, PersistableBundle bundle) {
        if (activity != null) {
            ActivityInfo info = c.b(activity.getClass().getName());
            if (info != null) {
                this.a(activity, info);
            }
        }
        super.callActivityOnCreate(activity, bundle, bundle);
    }

    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        if (activity != null) {
            this.a(activity.getClass().getClassLoader(), intent);
        }
        super.callActivityOnNewIntent(activity, intent);
    }

    private boolean a(ClassLoader loader, Intent intent) {
        if (intent == null) {
            return false;
        }
        else {
            g.a(intent, loader);
            ComponentName name = (ComponentName)intent.getParcelableExtra("tinker_iek_old_component");
            if (name == null) {
                n.c("Tinker.Instrumentation", new StringBuilder().append("oldComponent was null, start ").append(intent.getComponent()).append(" next.").toString(), new Object[]{});
                return false;
            }
            else {
                String str0 = name.getClassName();
                ActivityInfo info = c.b(str0);
                if (info == null) {
                    n.d("Tinker.Instrumentation", new StringBuilder().append("Failed to query target activity's info, perhaps the target is not hotpluged component. Target: ").append(str0).toString(), new Object[]{});
                    return false;
                }
                else {
                    intent.setComponent(name);
                    intent.removeExtra("tinker_iek_old_component");
                    return true;
                }
            }
        }
    }

    private void a(Activity activity, ActivityInfo info) {
        activity.setRequestedOrientation(info.screenOrientation);
        activity.setTheme(info.theme);
        try {
            Field field = j.a(activity, "mActivityInfo");
            field.set(activity, info);
            return;
        }
        catch (Throwable var_3_1) {
            throw new k("see next stacktrace.", var_3_1);
        }
    }

    private void a(Instrumentation instrumentation) {
        Field[] fieldArr0 = Instrumentation.class.getDeclaredFields();
        for (int i0 = 0; i0 < fieldArr0.length; i0 += 1) {
            fieldArr0[i0].setAccessible(true);
            Object object = fieldArr0[i0].get(instrumentation);
            fieldArr0[i0].set(this, object);
        }
    }

}
