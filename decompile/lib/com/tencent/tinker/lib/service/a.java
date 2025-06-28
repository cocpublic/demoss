/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/service;

import com.tencent.tinker.loader.k;
import android.content.Intent;
import android.content.ComponentName;
import java.io.Serializable;

// class: com/tencent/tinker/lib/service/a
public abstract class a {

    public a() {
        super("TinkerResultService");
    }

    public static void a(Context context, b b, String str0) {
        if (str0 == null) {
            throw new k("resultServiceClass is null.");
        }
        else {
            try {
                Intent intent = new Intent();
                intent.setClassName(context, str0);
                intent.putExtra("result_extra", b);
                context.startService(intent);
            }
            catch (Throwable var_3_1) {
                n.d("Tinker.AbstractResultService", new StringBuilder().append("run result service fail, exception:").append(var_3_1).toString(), new Object[]{});
            }
        }
    }

    public int onStartCommand(Intent intent, int i0, int i1) {
        super.onStartCommand(intent, i0, i1);
        return 2;
    }

    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            n.d("Tinker.AbstractResultService", "AbstractResultService received a null intent, ignoring.", new Object[]{});
        }
        else {
            b b = (b)g.b(intent, "result_extra");
            this.a(b);
        }
    }

    void a(b p0);

}
