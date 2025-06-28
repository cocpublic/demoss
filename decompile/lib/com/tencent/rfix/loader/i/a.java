/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/i;

import com.tencent.rfix.loader.c.b;

// class: com/tencent/rfix/loader/i/a
public class a {
    public String a;
    public String b;
    public String c;

    public a(Context context, boolean bool0) {
        super(context, "remote_verify_info", bool0);
    }

    public void b() {
        super.b();
        this.a = this.p.a("app_id", null);
        this.b = this.p.a("app_key", null);
        this.c = this.p.a("app_version", null);
    }

    public void c() {
        this.p.b("app_id", this.a);
        this.p.b("app_key", this.b);
        this.p.b("app_version", this.c);
        super.c();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        else if ((object instanceof a)) {
            a a = (a)object;
            if (Objects.equals(this.a, a.a) && Objects.equals(this.b, a.b) && Objects.equals(this.c, a.c)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public String toString() {
        return new StringBuilder().append("RemoteVerifyInfo{appId='").append(this.a).append(39).append(", appKey='").append(this.b).append(39).append(", appVersion='").append(this.c).append(39).append(125).toString();
    }

}
