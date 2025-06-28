/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/engine;

import android.app.Application;
import com.tencent.rfix.loader.entity.b;
import com.tencent.rfix.loader.entity.a;

// class: com/tencent/rfix/loader/engine/c
public class c {

    public c(Application application) {
        super(application);
    }

    protected void b() {
        RFixLog.c("RFix.PatchLoadEngine", "initLoaders...");
        if ("normal".equals("com")) {
            this.a(new f(this.a));
            this.a(new QFixPatchLoader(this.a));
            this.a(new RedirectPatchLoader(this.a));
        }
        else {
            if ("com".equals("com")) {
                this.a(new f(this.a));
            }
        }
    }

    protected void b(String str0) {
        boolean bool0 = g.a(this.a);
        b b = new b(this.a, 1);
        if (! bool0 || b.a()) {
        }
        else {
            RFixLog.c("RFix.PatchLoadEngine", new StringBuilder().append("checkUnfinishedPatchInstall installRecord=").append(b).toString());
            if (TextUtils.equals(b.b, "Tinker")) {
                String str1 = a.a(this.a);
                if (TextUtils.equals(str1, b.d)) {
                    RFixLog.c("RFix.PatchLoadEngine", "checkUnfinishedPatchInstall tinker patch has installed, fix patch info.");
                    a a = new a(this.a);
                    a.d = b.e;
                    a.e = b.f;
                    a.f = b.g;
                    a.g = b.a;
                    a.h = b.b;
                    a.i = b.c;
                    a.j = false;
                    a.k = b.h;
                    a.l = b.i;
                    a.m = b.j;
                    a.n = false;
                    a.o = false;
                    a.c();
                }
            }
            b.d();
        }
    }

}
