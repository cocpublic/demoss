/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/app;

import android.os.Message;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources$Theme;

// class: com/tencent/tinker/loader/app/a
public final class a {

    static void a(Handler handler, Context context) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 1, context);
            handler.handleMessage(message);
            return;
        }
        finally {
            Throwable throwable = v_8;
            message.recycle();
            throw throwable;
        }
    }

    static void a(Handler handler) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 2);
            handler.handleMessage(message);
            return;
        }
        finally {
            Throwable throwable = v_7;
            message.recycle();
            throw throwable;
        }
    }

    static void a(Handler handler, Configuration configuration) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 3, configuration);
            handler.handleMessage(message);
            return;
        }
        finally {
            Throwable throwable = v_8;
            message.recycle();
            throw throwable;
        }
    }

    static void a(Handler handler, int i0) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 4, Integer.valueOf(i0));
            handler.handleMessage(message);
            return;
        }
        finally {
            Throwable throwable = v_9;
            message.recycle();
            throw throwable;
        }
    }

    static void b(Handler handler) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 5);
            handler.handleMessage(message);
            return;
        }
        finally {
            Throwable throwable = v_7;
            message.recycle();
            throw throwable;
        }
    }

    static void c(Handler handler) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 6);
            handler.handleMessage(message);
            return;
        }
        finally {
            Throwable throwable = v_7;
            message.recycle();
            throw throwable;
        }
    }

    static ClassLoader a(Handler handler, ClassLoader loader) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 7, loader);
            handler.handleMessage(message);
            ClassLoader loaderVar1 = (ClassLoader)message.obj;
            message.recycle();
            return loaderVar1;
        }
        finally {
            Throwable throwable = v_12;
            message.recycle();
            throw throwable;
        }
    }

    static Context b(Handler handler, Context context) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 8, context);
            handler.handleMessage(message);
            Context contextVar1 = (Context)message.obj;
            message.recycle();
            return contextVar1;
        }
        finally {
            Throwable throwable = v_12;
            message.recycle();
            throw throwable;
        }
    }

    static AssetManager a(Handler handler, AssetManager manager) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 9, manager);
            handler.handleMessage(message);
            AssetManager managerVar1 = (AssetManager)message.obj;
            message.recycle();
            return managerVar1;
        }
        finally {
            Throwable throwable = v_12;
            message.recycle();
            throw throwable;
        }
    }

    static Resources a(Handler handler, Resources resources) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 10, resources);
            handler.handleMessage(message);
            Resources resourcesVar1 = (Resources)message.obj;
            message.recycle();
            return resourcesVar1;
        }
        finally {
            Throwable throwable = v_12;
            message.recycle();
            throw throwable;
        }
    }

    static Object a(Handler handler, String str0, Object object) {
        Object objectVar1 = null;
        try {
            Message message = Message.obtain(handler, 11, new Object[]{str0, object});
            handler.handleMessage(message);
            Object objectVar2 = message.obj;
            message.recycle();
            return objectVar2;
        }
        finally {
            Throwable throwable = v_16;
            message.recycle();
            throw throwable;
        }
    }

    static Resources$Theme a(Handler handler, Resources$Theme theme) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 13, theme);
            handler.handleMessage(message);
            Resources$Theme themeVar1 = (Resources$Theme)message.obj;
            message.recycle();
            return themeVar1;
        }
        finally {
            Throwable throwable = v_12;
            message.recycle();
            throw throwable;
        }
    }

    static int d(Handler handler) {
        Object object = null;
        try {
            Message message = Message.obtain(handler, 12);
            handler.handleMessage(message);
            int i0 = (Integer)message.obj.intValue();
            message.recycle();
            return i0;
        }
        finally {
            Throwable throwable = v_12;
            message.recycle();
            throw throwable;
        }
    }

}
