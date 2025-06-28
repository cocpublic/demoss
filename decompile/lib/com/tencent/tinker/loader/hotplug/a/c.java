/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/hotplug/a;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.Intent;

// class: com/tencent/tinker/loader/hotplug/a/c
public class c implements d$a {

    public c() {
        super();
    }

    public Object a(Object object, Method method, Object[] objectArr0) {
        String str0 = method.getName();
        if ("getActivityInfo".equals(str0)) {
            return this.b(object, method, objectArr0);
        }
        else if ("resolveIntent".equals(str0)) {
            return this.c(object, method, objectArr0);
        }
        else {
            return method.invoke(object, objectArr0);
        }
    }

    private Object b(Object object, Method method, Object[] objectArr0) {
        Class[] classArr0 = method.getExceptionTypes();
        try {
            Object objectVar1 = method.invoke(object, objectArr0);
            if (objectVar1 != null) {
                return objectVar1;
            }
            else {
                Object objectVar2 = null;
                for (int i0 = 0; i0 < objectArr0.length; i0 += 1) {
                    if ((objectArr0[i0] instanceof ComponentName)) {
                        n.b("Tinker.PMSIntrcptHndlr", new StringBuilder().append("locate componentName field of ").append(method.getName()).append(" done at idx: ").append(i0).toString(), new Object[]{});
                        objectVar2 = (ComponentName)objectArr0[i0];
                        break;;
                    }
                    else {
                    }
                }
                if (objectVar2 != null) {
                    return c.b(objectVar2.getClassName());
                }
                else {
                    n.c("Tinker.PMSIntrcptHndlr", new StringBuilder().append("failed to locate componentName field of ").append(method.getName()).append(", notice any crashes or mistakes after resolve works.").toString(), new Object[]{});
                    return null;
                }
            }
        }
        catch (InvocationTargetException var_6_2) {
            Throwable throwable = var_6_2.getTargetException();
            classArr0 != null && classArr0.length > 0;
            throw throwable != null ? var_6_2 : throwable;
            n.d("Tinker.PMSIntrcptHndlr", "unexpected exception.", new Object[]{throwable != null ? var_6_2 : throwable});
            return null;
        }
        catch (Throwable var_5_2) {
            n.d("Tinker.PMSIntrcptHndlr", "unexpected exception.", new Object[]{var_5_2});
            return null;
        }
    }

    private Object c(Object object, Method method, Object[] objectArr0) {
        Class[] classArr0 = method.getExceptionTypes();
        try {
            Object objectVar1 = method.invoke(object, objectArr0);
            if (objectVar1 != null) {
                return objectVar1;
            }
            else {
                n.c("Tinker.PMSIntrcptHndlr", "failed to resolve activity in base package, try again in patch package.", new Object[]{});
                Object objectVar2 = null;
                for (int i0 = 0; i0 < objectArr0.length; i0 += 1) {
                    if ((objectArr0[i0] instanceof Intent)) {
                        n.b("Tinker.PMSIntrcptHndlr", new StringBuilder().append("locate intent field of ").append(method.getName()).append(" done at idx: ").append(i0).toString(), new Object[]{});
                        objectVar2 = (Intent)objectArr0[i0];
                        break;;
                    }
                    else {
                    }
                }
                if (objectVar2 != null) {
                    return c.a(objectVar2);
                }
                else {
                    n.c("Tinker.PMSIntrcptHndlr", new StringBuilder().append("failed to locate intent field of ").append(method.getName()).append(", notice any crashes or mistakes after resolve works.").toString(), new Object[]{});
                    return null;
                }
            }
        }
        catch (InvocationTargetException var_6_2) {
            Throwable throwable = var_6_2.getTargetException();
            classArr0 != null && classArr0.length > 0;
            throw throwable != null ? var_6_2 : throwable;
            n.d("Tinker.PMSIntrcptHndlr", "unexpected exception.", new Object[]{throwable != null ? var_6_2 : throwable});
            return null;
        }
        catch (Throwable var_5_2) {
            n.d("Tinker.PMSIntrcptHndlr", "unexpected exception.", new Object[]{var_5_2});
            return null;
        }
    }

}
