/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/entity;

import com.tencent.rfix.loader.c.b;
import androidx.annotation.NonNull;

// class: com/tencent/rfix/loader/entity/a
public class a {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public String f;
    public String g;
    public String h;
    public String i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;

    public a(Context context) {
        super(context, "rfix_patch_info", 1, 1);
    }

    public void b() {
        Class class = a.class;
        a.class;
        synchronized () {
            super.b();
        }
        this.a = this.p.a("last_config_id", 156);
        this.b = this.p.a("last_download_result", 156);
        this.c = this.p.a("last_install_result", 156);
        this.d = this.p.a("config_id", 0);
        this.e = this.p.a("config_type", 0);
        this.f = this.p.a("patch_process", null);
        this.g = this.p.a("version", null);
        this.h = this.p.a("patch_type", null);
        this.i = this.p.a("patch_id", null);
        this.j = this.p.a("effectImmediate", 0);
        this.k = this.p.a("enable_assert_dex", 0);
        this.l = this.p.a("enable_assert_lib", 0);
        this.m = this.p.a("enable_assert_res", 0);
        this.n = this.p.a("remove_patch", 0);
        this.o = this.p.a("main_verified", 0);
        RFixLog.c("RFix.RFixPatchInfo", new StringBuilder().append("loadStoreInfo ").append(this).toString());
    }

    public void c() {
        this.p.b("last_config_id", this.a);
        this.p.b("last_download_result", this.b);
        this.p.b("last_install_result", this.c);
        this.p.b("config_id", this.d);
        this.p.b("config_type", this.e);
        this.p.b("patch_process", this.f);
        this.p.b("version", this.g);
        this.p.b("patch_type", this.h);
        this.p.b("patch_id", this.i);
        this.p.b("effectImmediate", this.j);
        this.p.b("enable_assert_dex", this.k);
        this.p.b("enable_assert_lib", this.l);
        this.p.b("enable_assert_res", this.m);
        this.p.b("remove_patch", this.n);
        this.p.b("main_verified", this.o);
        Class class = a.class;
        a.class;
        synchronized () {
            super.c();
        }
        RFixLog.c("RFix.RFixPatchInfo", new StringBuilder().append("saveStoreInfo ").append(this).toString());
    }

    @NonNull
    public String toString() {
        return new StringBuilder().append("RFixPatchInfo{lastConfigId=").append(this.a).append(", lastDownloadResult=").append(this.b).append(", lastInstallResult=").append(this.c).append(", configId=").append(this.d).append(", configType=").append(this.e).append(", patchProcess=").append(this.f).append(", version=").append(this.g).append(", patchType=").append(this.h).append(", patchId=").append(this.i).append(", effectImmediate=").append(this.j).append(", enableAssertDex=").append(this.k).append(", enableAssertLib=").append(this.l).append(", enableAssertRes=").append(this.m).append(", removePatch=").append(this.n).append(", mainVerified=").append(this.o).append(125).toString();
    }

    public static boolean a(String str0) {
        if ("QFix".equals(str0) || "Redirect".equals(str0) || "Tinker".equals(str0)) {
            return true;
        }
        else {
            return false;
        }
    }

}
