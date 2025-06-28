/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/entity;

import com.tencent.rfix.loader.c.b;

// class: com/tencent/rfix/loader/entity/b
public class b {
    public String a;
    public String b;
    public String c;
    public String d;
    public int e;
    public int f;
    public String g;
    public boolean h;
    public boolean i;
    public boolean j;

    public b(Context context, boolean bool0) {
        super(context, "tinker_install_record", bool0);
    }

    public boolean a() {
        if (TextUtils.isEmpty(this.a) && TextUtils.isEmpty(this.b) && TextUtils.isEmpty(this.c) && TextUtils.isEmpty(this.d)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void b() {
        super.b();
        this.a = this.p.a("patch_version", null);
        this.b = this.p.a("patch_type", null);
        this.c = this.p.a("patch_id", null);
        this.d = this.p.a("tinker_patch_version", null);
        this.e = this.p.a("config_id", 0);
        this.f = this.p.a("config_type", 0);
        this.g = this.p.a("patch_process", null);
        this.h = this.p.a("enable_assert_dex", 0);
        this.i = this.p.a("enable_assert_lib", 0);
        this.j = this.p.a("enable_assert_res", 0);
        RFixLog.c("RFix.TinkerInstallRecord", new StringBuilder().append("loadStoreInfo ").append(this).toString());
    }

    public void c() {
        this.p.b("patch_version", this.a);
        this.p.b("patch_type", this.b);
        this.p.b("patch_id", this.c);
        this.p.b("tinker_patch_version", this.d);
        this.p.b("config_id", this.e);
        this.p.b("config_type", this.f);
        this.p.b("patch_process", this.g);
        this.p.b("enable_assert_dex", this.h);
        this.p.b("enable_assert_lib", this.i);
        this.p.b("enable_assert_res", this.j);
        super.c();
        RFixLog.c("RFix.TinkerInstallRecord", new StringBuilder().append("saveStoreInfo ").append(this).toString());
    }

    public void d() {
        if (this.p.c(this.q)) {
            this.p.a("patch_version");
            this.p.a("patch_type");
            this.p.a("patch_id");
            this.p.a("tinker_patch_version");
            this.p.a("config_id");
            this.p.a("config_type");
            this.p.a("patch_process");
            this.p.a("enable_assert_dex");
            this.p.a("enable_assert_lib");
            this.p.a("enable_assert_res");
            this.p.b(this.q);
        }
    }

    public String toString() {
        return new StringBuilder().append("TinkerInstallRecord{patchVersion='").append(this.a).append(39).append(", patchType='").append(this.b).append(39).append(", patchId='").append(this.c).append(39).append(", tinkerPatchVersion='").append(this.d).append(39).append(", configId=").append(this.e).append(", configType=").append(this.f).append(", patchProcess='").append(this.g).append(39).append(", enableAssertDex=").append(this.h).append(", enableAssertLib=").append(this.i).append(", enableAssertRes=").append(this.j).append(125).toString();
    }

}
