/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/entry;

import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.Context;
import com.tencent.tinker.anno.Keep;

// class: com/tencent/tinker/entry/TinkerApplicationInlineFence
public final class TinkerApplicationInlineFence {
    final private ApplicationLike a;

    @Keep
    public TinkerApplicationInlineFence(ApplicationLike like) {
        super();
        this.a = like;
    }

    public void handleMessage(Message message) {
        this.a(message);
    }

    private void a(Message message) {
        try {
            TinkerApplicationInlineFence.a();
            return;
        }
        finally {
            Throwable throwable = v_2;
            this.b(message);
            throw throwable;
        }
    }

    public void dispatchMessage(Message message) {
    }

    private void b(Message message) {
        switch(message.what) {
            case 1: {
                this.a.onBaseContextAttached((Context)message.obj);
                return;
            }
            case 2: {
                this.a.onCreate();
                return;
            }
            case 3: {
                this.a.onConfigurationChanged((Configuration)message.obj);
                return;
            }
            case 4: {
                this.a.onTrimMemory((Integer)message.obj.intValue());
                return;
            }
            case 5: {
                this.a.onLowMemory();
                return;
            }
            case 6: {
                this.a.onTerminate();
                return;
            }
            case 7: {
                message.obj = this.a.getClassLoader((ClassLoader)message.obj);
                return;
            }
            case 8: {
                message.obj = this.a.getBaseContext((Context)message.obj);
                return;
            }
            case 9: {
                message.obj = this.a.getAssets((AssetManager)message.obj);
                return;
            }
            case 10: {
                message.obj = this.a.getResources((Resources)message.obj);
                return;
            }
            case 11: {
                Object[] objectArr0 = (Object[])message.obj;
                message.obj = this.a.getSystemService((String)objectArr0[0], objectArr0[1]);
                return;
            }
            case 13: {
                message.obj = this.a.getTheme((Resources$Theme)message.obj);
                return;
            }
            case 12: {
                message.obj = Integer.valueOf(this.a.mzNightModeUseOf());
                return;
            }
            default: {
                throw new IllegalStateException("Should not be here.");
            }
        }
    }

    private static void a() {
        if (TinkerApplicationInlineFence.class.isPrimitive()) {
            throw new RuntimeException();
        }
        else {
        }
    }

}
