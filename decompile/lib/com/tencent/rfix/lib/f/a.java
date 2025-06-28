/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/f;

import com.tencent.rfix.loader.d.a;
import com.tencent.rfix.loader.entity.a;
import com.tencent.rfix.loader.h.h$a;
import com.tencent.rfix.loader.f.a;
import com.tencent.rfix.lib.b.b;
import android.content.Context;

// class: com/tencent/rfix/lib/f/a
public class a {
    final protected Context a;

    public a(Context context) {
        super();
        this.a = context;
    }

    public void a(RFixLoadResult result) {
        RFixLog.b("RFix.DefaultLoadReporter", new StringBuilder().append("onLoadResult loadResult=").append(result).toString());
        b.a().a((result) -> {
            this.c(result);
            this.b(result);
        });
    }

    private void c(RFixLoadResult result) {
        if (g.a(this.a)) {
        }
        else {
            int i0 = result.isLoaderSuccess() ? 0 : result.e.d;
            b.a(this.a).a(i0, result.j);
        }
    }

    protected void b(RFixLoadResult result) {
        if (result.a == h$a.b) {
        }
        else {
            int i2 = 0;
            int i3 = 0;
            Object object = null;
            Object objectVar1 = null;
            a a = result.e;
            if (a != null) {
                i2 = a.d > 0 ? a.a : a.d;
                i3 = a.e;
                objectVar1 = a.h;
                if (TextUtils.isEmpty(a.g) && a.g.length() >= 8) {
                    object = a.g.substring(0, 8);
                }
            }
            e.a(this.a, result, String.valueOf(i2));
            long l0 = b.c(a.d);
            long l1 = b.c(a.e);
            long l2 = b.c(a.f);
            long l3 = b.c(a.g);
            long l4 = b.c(a.h);
            c.a(this.a, String.valueOf(i2), String.valueOf(i3), objectVar1, object, "Load", result.a(), result.a.toString(), String.valueOf(result.b), result.c, null, null, null, String.valueOf(l0), String.valueOf(l1), String.valueOf(l2), String.valueOf(l3), String.valueOf(l4), null, null, null, null, null);
        }
    }

    private /* synthetic */ void d(RFixLoadResult result) {
        this.c(result);
        this.b(result);
    }

}
