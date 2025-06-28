/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/engine;

import com.tencent.rfix.loader.entity.RFixLoadResult;
import com.tencent.rfix.loader.entity.a;
import com.tencent.rfix.loader.h.h$a;
import java.util.concurrent.TimeUnit;
import java.io.File;
import android.app.Application;

// class: com/tencent/rfix/loader/engine/e
public class e {

    public e(Application application) {
        super(application);
    }

    protected void b() {
        RFixLog.c("RFix.PatchLoadEngineImmediate", "initLoaders...");
        this.a(e.d);
    }

    public RFixLoadResult a() {
        RFixLog.c("RFix.PatchLoadEngineImmediate", "tryLoadPatch...");
        long l0 = System.nanoTime();
        RFixLoadResult result = new RFixLoadResult();
        result.a = h$a.m;
        result.j = true;
        this.a(result);
        result.c = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - l0);
        RFixLog.c("RFix.PatchLoadEngineImmediate", String.format("tryLoadPatch loadResult=%s", new Object[]{result}));
        return result;
    }

    public boolean c() {
        RFixLog.c("RFix.PatchLoadEngineImmediate", "tryUnloadPatch...");
        File file = e.a(this.a);
        if (file == null || file.exists()) {
            RFixLog.d("RFix.PatchLoadEngineImmediate", "tryUnloadPatch patch dir not exist.");
            return false;
        }
        else {
            a a = new a(this.a);
            if (TextUtils.isEmpty(a.g)) {
                RFixLog.d("RFix.PatchLoadEngineImmediate", "tryUnloadPatch patch info empty.");
                return false;
            }
            else {
                if (! a.j || a.n) {
                    RFixLog.d("RFix.PatchLoadEngineImmediate", "tryUnloadPatch patch info not match.");
                    return false;
                }
                else {
                    IPatchLoader loader = this.a("Redirect");
                    if (loader == null) {
                        RFixLog.d("RFix.PatchLoadEngineImmediate", "tryUnloadPatch no loader support.");
                        return false;
                    }
                    else {
                        return loader.unloadPatchImmediate();
                    }
                }
            }
        }
    }

}
