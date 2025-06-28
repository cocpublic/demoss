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
import android.os.Parcelable;

// class: com/tencent/tinker/loader/hotplug/a/b
public class b implements a$b {
    final private static int a;
    final private Context b;

    public b(Context context) {
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
        this.b = contextVar1;
    }

    public boolean a(Message message) {
        int i0 = message.what;
        if (i0 == b.a) {
            try {
                Object object = message.obj;
                if (object == null) {
                    n.c("Tinker.MHMsgHndlr", new StringBuilder().append("msg: [").append(message.what).append("] has no 'obj' value.").toString(), new Object[]{});
                    return false;
                }
                else {
                    Field field = j.a(object, "intent");
                    Intent intent = (Intent)field.get(object);
                    if (intent == null) {
                        n.c("Tinker.MHMsgHndlr", "cannot fetch intent from message received by mH.", new Object[]{});
                        return false;
                    }
                    else {
                        g.a(intent, this.b.getClassLoader());
                        ComponentName name = (ComponentName)intent.getParcelableExtra("tinker_iek_old_component");
                        if (name == null) {
                            n.c("Tinker.MHMsgHndlr", new StringBuilder().append("oldComponent was null, start ").append(intent.getComponent()).append(" next.").toString(), new Object[]{});
                            return false;
                        }
                        else {
                            Field fieldVar1 = j.a(object, "activityInfo");
                            ActivityInfo info = (ActivityInfo)fieldVar1.get(object);
                            if (info == null) {
                                return false;
                            }
                            else {
                                ActivityInfo infoVar1 = c.b(name.getClassName());
                                if (infoVar1 == null) {
                                    n.d("Tinker.MHMsgHndlr", new StringBuilder().append("Failed to query target activity's info, perhaps the target is not hotpluged component. Target: ").append(name.getClassName()).toString(), new Object[]{});
                                    return false;
                                }
                                else {
                                    super.a(object, infoVar1.screenOrientation);
                                    super.a(info, infoVar1);
                                    intent.setComponent(name);
                                    intent.removeExtra("tinker_iek_old_component");
                                }
                            }
                        }
                    }
                }
            }
            catch (Throwable var_3_1) {
                n.d("Tinker.MHMsgHndlr", "exception in handleMessage.", new Object[]{var_3_1});
            }
        }
        return false;
    }

    private void a(ActivityInfo info, ActivityInfo infoVar1) {
        super.a(infoVar1, info);
    }

    private <T> void a(T object, T objectVar1) {
        if (object == null || objectVar1 == null) {
        }
        else {
            Class classVar1 = object.getClass();
            while (classVar1.equals(Object.class)) {
                Field[] fieldArr0 = classVar1.getDeclaredFields();
                for (int i1 = 0; i1 < fieldArr0.length; i1 += 1) {
                    Field field = fieldArr0[i1];
                    if (field.isSynthetic()) {
                        continue;;
                    }
                    else {
                        int i2 = field.getModifiers();
                        if (Modifier.isStatic(i2)) {
                            continue;;
                        }
                        else {
                            if (field.isAccessible()) {
                                field.setAccessible(true);
                            }
                            try {
                                field.set(objectVar1, field.get(object));
                                continue;;
                            }
                            catch (Throwable var_10_0) {
                            }
                        }
                    }
                }
                classVar1 = classVar1.getSuperclass();
            }
        }
    }

    private void a(Object object, int i0) {
        if (i0 == -1) {
            i0 = 2;
        }
        try {
            Field field = j.a(object, "token");
            Object objectVar1 = field.get(object);
            Class class = Class.forName("android.app.ActivityManagerNative");
            Method method = j.a(class, "getDefault", new Class[]{});
            Object objectVar2 = method.invoke(null, new Object[]{});
            Method methodVar1 = j.a(objectVar2, "setRequestedOrientation", new Class[]{IBinder.class, Integer.TYPE});
            methodVar1.invoke(objectVar2, new Object[]{objectVar1, Integer.valueOf(i0)});
        }
        catch (Throwable var_3_1) {
            n.d("Tinker.MHMsgHndlr", "Failed to fix screen orientation.", new Object[]{var_3_1});
        }
    }

    static  {
        int i2 = 100;
        if (Build$VERSION.SDK_INT < 27) {
            try {
                Class class = Class.forName("android.app.ActivityThread$H");
                i2 = j.a(class, "LAUNCH_ACTIVITY").getInt(null);
            }
            catch (Throwable var_1_1) {
                i2 = 100;
            }
        }
        b.a = i2;
    }

}
