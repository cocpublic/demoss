/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/c;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import android.content.Context;
import com.tencent.rfix.loader.entity.a;
import com.tencent.rfix.loader.f.a;
import com.tencent.rfix.lib.RFix;
import com.tencent.rfix.lib.engine.a;
import com.tencent.rfix.lib.d.b;
import com.tencent.rfix.lib.d.c;

// class: com/tencent/rfix/lib/c/c
public class c implements a {
    final private Context a;
    final private ExecutorService b;
    private PatchConfig c;
    private int d;

    public c(Context context) {
        super();
        this.a = context;
        this.b = Executors.newSingleThreadExecutor();
    }

    public void a(PatchConfig config) {
        RFixLog.b("RFix.PatchDownloader", String.format("downloadPatch config=%s", new Object[]{config}));
        if (b.a()) {
            RFixLog.e("RFix.PatchDownloader", "downloadPatch silence mode is enabled.");
        }
        else {
            this.c = config;
            this.d = 0;
            b b = new b(this.a, config, this);
            this.b.submit(b);
        }
    }

    protected void a(boolean bool0, int i0, String str0, PatchConfig config) {
        int i1 = config != this.c ? 0 : 1;
        if (i1 != 0) {
            RFixLog.b("RFix.PatchDownloader", "onPatchDownload config changed, ignore this.");
        }
        else {
            if (bool0 && this.d < 2) {
                this.d = this.d + 1;
                RFixLog.b("RFix.PatchDownloader", String.format("onPatchDownload retry curRetryCount=%s", new Object[]{Integer.valueOf(this.d)}));
                b b = new b(this.a, config, this);
                this.b.submit(b);
            }
            else {
                a a = new a(this.a);
                a.b = i0;
                a.c();
                this.a(bool0, i0, config);
                if (bool0) {
                    a aVar1 = RFix.getInstance().c();
                    aVar1.a(str0, config);
                }
                b bVar1 = new b();
                bVar1.a = i0;
                bVar1.b = str0;
                bVar1.c = config;
                RFix.getInstance().d().a(bVar1.a(), bVar1.a, bVar1);
            }
        }
    }

    protected void a(boolean bool0, int i0, PatchConfig config) {
        String str0 = String.valueOf(config.configId);
        String str1 = String.valueOf(i0);
        long l0 = b.c(a.k);
        c.a(this.a, str0, null, null, null, "Download", bool0, str1, null, l0);
    }

}
