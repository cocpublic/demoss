/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/entry;

import android.content.Intent;
import android.app.Application;
import com.tencent.rfix.loader.entity.RFixLoadResult;
import com.tencent.rfix.lib.engine.e;
import com.tencent.tinker.lib.e.b;
import androidx.annotation.Keep;

// class: com/tencent/rfix/entry/RFixApplicationLike
public abstract class RFixApplicationLike {
    final private RFixLoadResult a;

    public RFixApplicationLike(Application application, RFixLoadResult result) {
        super(application, result.l, result.m, result.o, result.p, result.n);
        this.a = result;
    }

    @Keep
    public RFixLoadResult getLoadResult() {
        return this.a;
    }

    public void onBaseContextAttached(Context context) {
        super.onBaseContextAttached(context);
        boolean bool0 = this.a(this);
        if (bool0) {
            RFixLog.e("RFix.RFixApplicationLike", "onBaseContextAttached init tinker fail!");
        }
        a.a(this.a);
        AutoVerifyPatch.a(this.getApplication(), this.a);
        a.a(this.getApplication(), this.a);
    }

    private boolean a(ApplicationLike like) {
        int i1 = false;
        try {
            n.a(new e());
            f.a(like);
            Application application = like.getApplication();
            if (g.b(application)) {
                f.a();
            }
            i1 = 1;
        }
        catch (Exception var_3_1) {
            RFixLog.e("RFix.RFixApplicationLike", "initializeTinker fail!", var_3_1);
        }
        return i1;
    }

}
