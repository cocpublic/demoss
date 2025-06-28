/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/engine;

import java.io.File;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.d.c;
import com.tencent.rfix.lib.RFix;

// class: com/tencent/rfix/lib/engine/h
public class h {
    protected ServiceConnection a;

    public h(Context context) {
        super(context);
    }

    public int a(String str0) {
        File file = new File(str0);
        String str1 = h.f(file);
        int i0 = this.a(str0, str1);
        if (i0 == 0) {
            this.a();
            TinkerPatchService.a(this.b, str0);
        }
        else {
            b.a(this.b).g().a(file, i0);
        }
        return i0;
    }

    protected int a(String str0, String str1) {
        return super.a(str0, str1);
    }

    protected void a() {
        try {
            this.a = new h$1(this);
            Intent intent = new Intent(this.b, TinkerPatchForeService.class);
            this.b.bindService(intent, this.a, 1);
        }
        catch (Throwable var_1_1) {
            RFixLog.e("RFix.TinkerPatchListener", "runForegroundService fail!", var_1_1);
        }
    }

    protected void b() {
        RFix fix = RFix.getInstance();
        a a = fix.c();
        b b = (b)a.a("Tinker");
        if (b != null) {
            b.a();
        }
    }

    static /* synthetic */ Context a(h h) {
        return h.b;
    }

    static /* synthetic */ Context b(h h) {
        return h.b;
    }

}
