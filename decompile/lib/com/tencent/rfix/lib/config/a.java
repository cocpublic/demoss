/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/config;

import java.util.concurrent.atomic.AtomicBoolean;
import android.content.Context;
import com.tencent.rfix.lib.RFix;
import com.tencent.rfix.lib.RFixParams;
import com.tencent.rfix.lib.d.a;
import com.tencent.rfix.lib.d.c;
import com.tencent.rfix.lib.b.b;
import com.tencent.rfix.lib.c.a;
import com.tencent.rfix.loader.c.e;
import com.tencent.rfix.loader.entity.a;

// class: com/tencent/rfix/lib/config/a
public class a implements b {
    final private Context a;
    private PatchConfig b;
    private PatchConfig c;
    final private AtomicBoolean d;
    private e e;

    public a(Context context) {
        super();
        this.d = new AtomicBoolean();
        this.a = context;
        this.c = new PatchConfig(context, 1);
    }

    public void a() {
        if (g.a(this.a)) {
            RFixLog.e("RFix.ConfigManager", "requestConfig only execute in main process.");
        }
        else {
            RFixParams params = RFix.getInstance().getParams();
            if (params.isEnable()) {
                RFixLog.e("RFix.ConfigManager", "requestConfig enable is false.");
            }
            else if (b.a()) {
                RFixLog.e("RFix.ConfigManager", "requestConfig silence mode is enabled.");
            }
            else {
                if (! TextUtils.isEmpty(params.getAppId()) || TextUtils.isEmpty(params.getAppKey())) {
                    RFixLog.e("RFix.ConfigManager", "requestConfig appId or appKey is invalid.");
                }
                else if (this.d.get()) {
                }
                else {
                    this.d.set(true);
                    e e = super.a(params);
                    e.a();
                    RFixLog.c("RFix.ConfigManager", "requestConfig submit request task.");
                }
            }
        }
    }

    protected void a(int i0, c c) {
        RFixLog.c("RFix.ConfigManager", new StringBuilder().append("onGetRDeliveryConfig result=").append(i0).append(" config=").append(c).toString());
        this.d.set(false);
        e e = new e(this.a);
        if (e.b) {
            RFixLog.c("RFix.ConfigManager", "onGetConfigResponse disable config.");
        }
        else {
            PatchConfig config = super.a(c);
            if (this.b != null && this.b.configId >= config.configId) {
                RFixLog.c("RFix.ConfigManager", "onGetRDeliveryConfig configId <= extraConfig.configId, ignored!");
            }
            else {
                if (i0 == e.a) {
                    super.a(config);
                }
                a a = new a();
                a.a = i0;
                a.b = config;
                RFix.getInstance().d().a(a.a(), a.a, a);
            }
        }
    }

    private e a(RFixParams params) {
        if (this.e == null) {
            this.e = new e(this.a, params, this);
        }
        return this.e;
    }

    private PatchConfig a(c c) {
        PatchConfig config = new PatchConfig(this.a, 0);
        if (c != null) {
            config.configId = c.a();
            config.a = c.b();
            c$a c$a = c.a(this.a);
            if (c$a != null) {
                config.patchUrl = c$a.a;
                config.patchMD5 = c$a.b;
            }
        }
        return config;
    }

    private void a(PatchConfig config) {
        RFixLog.c("RFix.ConfigManager", new StringBuilder().append("onUpdatePatchConfig patchConfig=").append(config).toString());
        int i2 = 0;
        if (config == null && this.c.a()) {
            RFixLog.c("RFix.ConfigManager", "onUpdatePatchConfig remove patch.");
            i2 = 1;
            this.c = new PatchConfig(this.a, 0);
            goto 110;
        }
        else {
            if (config != null && config.configId != this.c.configId) {
                RFixLog.c("RFix.ConfigManager", "onUpdatePatchConfig update patch.");
                i2 = 1;
                this.c = config;
            }
            else {
                RFixLog.c("RFix.ConfigManager", "onUpdatePatchConfig no change.");
            }
        }
        b.a(this.a).a(this.c.configId);
        this.c.c();
        a a = new a(this.a);
        if (a.a != this.c.configId) {
            a.a = this.c.configId;
            a.b = 156;
            a.c = 156;
            a.c();
        }
        if (i2 != 0) {
            RFix.getInstance().cleanPatch();
        }
        this.b(this.c);
    }

    private void b(PatchConfig config) {
        if (config.a()) {
            RFixLog.c("RFix.ConfigManager", "downloadPatchIfNeed config invalid.");
        }
        else {
            a a = RFix.getInstance().b();
            a.a(config);
        }
    }

}
