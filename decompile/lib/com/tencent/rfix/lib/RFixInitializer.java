/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib;

import com.tencent.rfix.loader.f.a;
import com.tencent.rfix.loader.d.a;
import com.tencent.rfix.loader.entity.RFixLoadResult;
import android.app.Application;
import androidx.annotation.Keep;

// class: com/tencent/rfix/lib/RFixInitializer
public class RFixInitializer {

    public RFixInitializer() {
        super();
    }

    @Keep
    public static RFix initialize(RFixApplicationLike like, RFixParams params) {
        return RFixInitializer.a(like, params, null);
    }

    @Keep
    public static RFix initialize(RFixApplicationLike like, RFixParams params, RFixListener listener) {
        return RFixInitializer.a(like, params, listener);
    }

    protected static RFix a(RFixApplicationLike like, RFixParams params, RFixListener listener) {
        b.a(a.i);
        Application application = like.getApplication();
        b.a().a(application::a);
        RFix fix = RFixInitializer.b(like, params, listener);
        b.b(a.i);
        long l0 = b.c(a.i);
        int i0 = fix != null ? 0 : 1;
        RFixInitializer.a(like, params, i0, l0);
        return fix;
    }

    protected static RFix b(RFixApplicationLike like, RFixParams params, RFixListener listener) {
        boolean bool0 = RFixInitializer.c(like, params, listener);
        if (bool0) {
            RFixLog.e("RFix.RFixInitializer", "initialize init rfix fail!");
            return null;
        }
        else {
            return RFix.getInstance();
        }
    }

    protected static void a(RFixApplicationLike like, RFixParams params, boolean bool0, long l1) {
        Application application = like.getApplication();
        RFixLoadResult result = like.getLoadResult();
        b.a().a(application::a);
    }

    protected static boolean c(RFixApplicationLike like, RFixParams params, RFixListener listener) {
        int i1 = false;
        try {
            Application application = like.getApplication();
            RFixLoadResult result = like.getLoadResult();
            c c = new c(application);
            RFix fix = new RFix$a(application, result, params).a(c).a(listener).a();
            RFix.a(fix);
            i1 = 1;
        }
        catch (Exception var_4_1) {
            RFixLog.e("RFix.RFixInitializer", "initializeRFix fail!", var_4_1);
        }
        return i1;
    }

    private static /* synthetic */ void a(Context context, RFixLoadResult result, boolean bool0, long l1, RFixParams l1) {
        b.a(context, result, bool0, l1);
        c.a(context, result, params, bool0, l1);
    }

    private static /* synthetic */ void a(Context context) {
        a.a(context);
    }

}
