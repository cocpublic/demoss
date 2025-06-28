/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/config;

import com.tencent.rfix.loader.c.b;
import androidx.annotation.Keep;

// class: com/tencent/rfix/lib/config/PatchConfig
public class PatchConfig {
    @Keep
    public int configId;
    @Keep
    public String patchUrl;
    @Keep
    public String patchMD5;
    public String a;

    public PatchConfig(Context context, boolean bool0) {
        super(context, "rfix_patch_config", bool0);
    }

    public boolean a() {
        if (this.configId != 0 && TextUtils.isEmpty(this.patchUrl) && TextUtils.isEmpty(this.patchMD5)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void b() {
        super.b();
        this.configId = this.p.a("key_config_id", 0);
        this.patchUrl = this.p.a("key_patch_url", null);
        this.patchMD5 = this.p.a("key_patch_md5", null);
        this.a = this.p.a("key_patch_process", null);
        RFixLog.c("RFix.PatchConfig", new StringBuilder().append("loadStoreInfo ").append(this).toString());
    }

    public void c() {
        this.p.b("key_config_id", this.configId);
        this.p.b("key_patch_url", this.patchUrl);
        this.p.b("key_patch_md5", this.patchMD5);
        this.p.b("key_patch_process", this.a);
        super.c();
        RFixLog.c("RFix.PatchConfig", new StringBuilder().append("saveStoreInfo ").append(this).toString());
    }

    public String toString() {
        return new StringBuilder().append("PatchConfig{configId=").append(this.configId).append(", patchUrl='").append(this.patchUrl).append(39).append(", patchMD5='").append(this.patchMD5).append(39).append(", patchProcess='").append(this.a).append(39).append(125).toString();
    }

}
