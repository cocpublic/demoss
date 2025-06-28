/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/entry;

import com.tencent.rfix.loader.entity.RFixLoadResult;
import androidx.annotation.Keep;

// class: com/tencent/rfix/entry/DefaultRFixApplicationLike
public class DefaultRFixApplicationLike {
    private static RFixLoadResult a;

    public DefaultRFixApplicationLike(Application application, RFixLoadResult result) {
        super(application, result);
    }

    public void onCreate() {
        super.onCreate();
        RFixLog.b("RFix.DefaultRFixApplicationLike", "onCreate");
    }

    public void onLowMemory() {
        super.onLowMemory();
        RFixLog.b("RFix.DefaultRFixApplicationLike", "onLowMemory");
    }

    public void onTrimMemory(int i0) {
        super.onTrimMemory(i0);
        RFixLog.b("RFix.DefaultRFixApplicationLike", new StringBuilder().append("onTrimMemory level: ").append(i0).toString());
    }

    public void onTerminate() {
        super.onTerminate();
        RFixLog.b("RFix.DefaultRFixApplicationLike", "onTerminate");
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        RFixLog.b("RFix.DefaultRFixApplicationLike", new StringBuilder().append("onConfigurationChanged: ").append(configuration).toString());
    }

    public void onBaseContextAttached(Context context) {
        super.onBaseContextAttached(context);
        RFixLog.b("RFix.DefaultRFixApplicationLike", new StringBuilder().append("onBaseContextAttached: ").append(context).toString());
        DefaultRFixApplicationLike.a = this.getLoadResult();
    }

    @Keep
    public static RFixApplicationLike createApplicationLike(Application application) {
        if (DefaultRFixApplicationLike.a != null) {
            return new DefaultRFixApplicationLike(application, DefaultRFixApplicationLike.a);
        }
        else {
            return null;
        }
    }

}
