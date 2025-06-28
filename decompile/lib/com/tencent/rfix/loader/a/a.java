/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/a;

import android.content.Context;
import androidx.annotation.NonNull;

// class: com/tencent/rfix/loader/a/a
public class a implements Thread$UncaughtExceptionHandler {
    final private Context a;
    final private Thread$UncaughtExceptionHandler b;

    public a(Context context) {
        super();
        this.a = context;
        this.b = Thread.getDefaultUncaughtExceptionHandler();
    }

    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        RFixLog.e("RFix.RFixExceptionHandler", String.format("handle uncaught Exception!!! thread=%s", new Object[]{thread}), throwable);
        c.a(this.a, throwable);
        if (this.b != null) {
            this.b.uncaughtException(thread, throwable);
        }
    }

}
