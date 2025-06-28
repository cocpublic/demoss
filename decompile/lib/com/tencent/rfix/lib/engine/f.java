/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/engine;

import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.e.b$a;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.c.j;
import com.tencent.tinker.lib.c.m;
import com.tencent.tinker.lib.g.b;
import android.app.Application;
import android.content.Intent;

// class: com/tencent/rfix/lib/engine/f
public class f {
    private static ApplicationLike a;
    private static Class<? extends TinkerResultService> b;

    public static void a(ApplicationLike like) {
        f.a = like;
    }

    public static b a() {
        Application application = f.a.getApplication();
        b b = new b$a(application).a(f.a.b()).a(Boolean.valueOf(f.a.c())).a(new i(application)).a(new h(application)).a();
        b.a(b);
        Class class = f.b != null ? TinkerResultService.class : f.b;
        j j = a.a() ? new j() : new j();
        b.a(f.a.a(), class, j);
        b.a(application).a(false);
        return b;
    }

}
