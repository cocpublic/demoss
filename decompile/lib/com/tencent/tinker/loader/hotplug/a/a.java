/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/hotplug/a;

import android.content.ContextWrapper;
import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.content.res.Resources$Theme;
import android.content.res.TypedArray;

// class: com/tencent/tinker/loader/hotplug/a/a
public class a implements d$a {
    final private static int a;
    final private static int b;
    final private Context c;

    public a(Context context) {
        super();
        while ((context instanceof ContextWrapper)) {
            Context contextVar1 = (ContextWrapper)context.getBaseContext();
            if (contextVar1 == null) {
                break;;
            }
            else {
                continue;;
            }
        }
        this.c = contextVar1;
    }

    public Object a(Object object, Method method, Object[] objectArr0) {
        String str0 = method.getName();
        if ("startActivity".equals(str0)) {
            return this.b(object, method, objectArr0);
        }
        else if ("startActivities".equals(str0)) {
            return this.c(object, method, objectArr0);
        }
        else if ("startActivityAndWait".equals(str0)) {
            return this.b(object, method, objectArr0);
        }
        else if ("startActivityWithConfig".equals(str0)) {
            return this.b(object, method, objectArr0);
        }
        else if ("startActivityAsUser".equals(str0)) {
            return this.b(object, method, objectArr0);
        }
        else if ("getIntentSender".equals(str0)) {
            return this.d(object, method, objectArr0);
        }
        else {
            return method.invoke(object, objectArr0);
        }
    }

    private Object b(Object object, Method method, Object[] objectArr0) {
        int i2 = -1;
        for (int i1 = 0; i1 < objectArr0.length; i1 += 1) {
            if ((objectArr0[i1] instanceof Intent)) {
                break;;
            }
            else {
            }
        }
        if (i1 != -1) {
            Intent intent = new Intent((Intent)objectArr0[i1]);
            this.a(intent);
            objectArr0[i1] = intent;
        }
        return method.invoke(object, objectArr0);
    }

    private Object c(Object object, Method method, Object[] objectArr0) {
        int i2 = -1;
        for (int i1 = 0; i1 < objectArr0.length; i1 += 1) {
            if ((objectArr0[i1] instanceof Intent[])) {
                break;;
            }
            else {
            }
        }
        if (i1 != -1) {
            Intent[] intentArr0 = (Intent[])objectArr0[i1];
            for (int i3 = 0; i3 < intentArr0.length; i3 += 1) {
                Intent intent = new Intent(intentArr0[i3]);
                this.a(intent);
                intentArr0[i3] = intent;
            }
        }
        return method.invoke(object, objectArr0);
    }

    private Object d(Object object, Method method, Object[] objectArr0) {
        int i2 = -1;
        for (int i3 = 0; i3 < objectArr0.length; i3 += 1) {
            if ((objectArr0[i3] instanceof Intent[])) {
                break;;
            }
            else {
            }
        }
        if (i3 != -1) {
            i3 = (Integer)objectArr0[0].intValue();
            if (i3 == a.b) {
                Intent[] intentArr0 = (Intent[])objectArr0[i3];
                for (int i4 = 0; i4 < intentArr0.length; i4 += 1) {
                    Intent intent = new Intent(intentArr0[i4]);
                    this.a(intent);
                    intentArr0[i4] = intent;
                }
            }
        }
        return method.invoke(object, objectArr0);
    }

    private void a(Intent intent) {
        Object object = null;
        Object objectVar1 = null;
        String str0;
        String str1;
        if (intent.getComponent() != null) {
            str0 = intent.getComponent().getPackageName();
            str1 = intent.getComponent().getClassName();
            goto 99;
        }
        else {
            ResolveInfo infoVar1 = this.c.getPackageManager().resolveActivity(intent, 0);
            if (infoVar1 == null) {
                infoVar1 = c.a(intent);
            }
            if (infoVar1 != null && infoVar1.filter != null && infoVar1.filter.hasCategory("android.intent.category.DEFAULT")) {
                str0 = infoVar1.activityInfo.packageName;
                str1 = infoVar1.activityInfo.name;
            }
        }
        if (c.a(str1)) {
            ActivityInfo info = c.b(str1);
            boolean bool0 = super.a(info);
            String str2 = a.a(str1, info.launchMode, bool0);
            super.a(intent, str0, str1, str2);
        }
    }

    private void a(Intent intent, String str0, String str1, String str2) {
        ComponentName name = new ComponentName(str0, str1);
        g.a(intent, this.c.getClassLoader());
        intent.putExtra("tinker_iek_old_component", name);
        ComponentName nameVar1 = new ComponentName(str0, str2);
        intent.setComponent(nameVar1);
    }

    private boolean a(ActivityInfo info) {
        int i0 = info.getThemeResource();
        Resources$Theme theme = this.c.getResources().newTheme();
        theme.applyStyle(i0, 1);
        Object object = null;
        try {
            TypedArray array = theme.obtainStyledAttributes(a.a);
            boolean bool0 = array.getBoolean(false, 0);
            if (array != null) {
                array.recycle();
            }
            return bool0;
        }
        catch (Throwable var_5_1) {
            int i1 = false;
            if (array != null) {
                array.recycle();
            }
            return i1;
        }
        finally {
            Throwable throwable = v_19;
            if (array != null) {
                array.recycle();
            }
            throw throwable;
        }
    }

    static  {
        a.a = new int[]{16842840};
        int i2 = 2;
        if (Build$VERSION.SDK_INT < 27) {
            try {
                i2 = (Integer)j.a(ActivityManager.class, "INTENT_SENDER_ACTIVITY").get(null).intValue();
            }
            catch (Throwable var_1_0) {
                var_1_0.printStackTrace();
                i2 = 2;
            }
        }
        a.b = i2;
    }

}
