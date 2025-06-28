/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/c;


// class: com/tencent/rfix/loader/c/e
public class e {
    public boolean a;
    public boolean b;
    public boolean c;
    public String d;
    public String e;
    public String f;
    public String g;
    public int h;

    public e(Context context) {
        super(context, "rfix_global_preferences");
    }

    public void b() {
        super.b();
        this.a = this.p.a("key_test_env", 0);
        this.b = this.p.a("key_disable_config", 0);
        this.c = this.p.a("key_auto_verify_enable", 0);
        this.d = this.p.a("key_dummy_app_version", null);
        this.e = this.p.a("key_dummy_app_uid", null);
        this.f = this.p.a("key_safe_mode_intercept_version", null);
        this.g = this.p.a("key_last_install_version", null);
        this.h = this.p.a("key_last_install_retry_count", 0);
    }

    public void c() {
        this.p.b("key_test_env", this.a);
        this.p.b("key_disable_config", this.b);
        this.p.b("key_auto_verify_enable", this.c);
        this.p.b("key_dummy_app_version", this.d);
        this.p.b("key_dummy_app_uid", this.e);
        this.p.b("key_safe_mode_intercept_version", this.f);
        this.p.b("key_last_install_version", this.g);
        this.p.b("key_last_install_retry_count", this.h);
        super.c();
    }

}
