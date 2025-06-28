/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/engine;

import android.content.Intent;
import android.content.ComponentName;
import com.tencent.rfix.lib.RFix;

// class: com/tencent/rfix/lib/engine/TinkerResultService
public class TinkerResultService {
    protected int a;
    protected long b;
    protected long c;
    protected long d;
    protected long e;
    protected long f;

    public TinkerResultService() {
        super();
        this.a = 0;
        this.b = 0L;
        this.c = 0L;
        this.d = 0L;
        this.e = 0L;
        this.f = 0L;
    }

    public static void a(Context context, String str0, int i0, long l1, long l1, long l3, long l3, long l5) {
        if (str0 == null) {
            throw new RuntimeException("resultServiceClass is null.");
        }
        else {
            try {
                Intent intent = new Intent();
                intent.setClassName(context, str0);
                intent.putExtra("result_code", i0);
                intent.putExtra("result_dex_rec_time", l1);
                intent.putExtra("result_dex_opt_time", l3);
                intent.putExtra("result_lib_rec_time", l5);
                intent.putExtra("result_res_rec_time", l7);
                intent.putExtra("result_dex_opt_wait_time", l9);
                context.startService(intent);
            }
            catch (Throwable var_13_1) {
                RFixLog.e("RFix.TinkerResultService", "runResultServiceExt fail!", var_13_1);
            }
        }
    }

    protected void onHandleIntent(Intent intent) {
        try {
            if (intent != null && intent.hasExtra("result_code")) {
                this.a = intent.getIntExtra("result_code", 0);
                this.b = intent.getLongExtra("result_dex_rec_time", 0L);
                this.c = intent.getLongExtra("result_dex_opt_time", 0L);
                this.d = intent.getLongExtra("result_lib_rec_time", 0L);
                this.e = intent.getLongExtra("result_res_rec_time", 0L);
                this.f = intent.getLongExtra("result_dex_opt_wait_time", 0L);
                RFixLog.c("RFix.TinkerResultService", String.format("onHandleIntent received last result code: %s", new Object[]{Integer.valueOf(this.a)}));
            }
            else {
                super.onHandleIntent(intent);
            }
        }
        catch (Exception var_2_0) {
            RFixLog.e("RFix.TinkerResultService", "onHandleIntent fail!", var_2_0);
        }
    }

    public void a(b b) {
        this.c(b);
        super.a(b);
    }

    public boolean b(b b) {
        return false;
    }

    protected void c(b b) {
        if (RFix.isInitialized()) {
            RFixLog.d("RFix.TinkerResultService", "notifyTinkerPatchResult RFix not initialized?");
        }
        else {
            RFix fix = RFix.getInstance();
            a a = fix.c();
            IPatchInstaller installer = a.a("Tinker");
            if (installer != null) {
                (b)installer.a(b.a, this.a, b.l, b.j, this.b, this.c, this.d, this.e, this.f);
            }
        }
    }

}
