/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib;

import com.tencent.rfix.loader.entity.RFixLoadResult;
import com.tencent.rfix.loader.d.a;
import com.tencent.rfix.loader.i.a;
import com.tencent.rfix.loader.c.e;
import com.tencent.rfix.loader.b$a;
import java.io.File;
import android.content.Context;
import android.annotation.SuppressLint;
import androidx.annotation.Keep;

// class: com/tencent/rfix/lib/RFix
public class RFix {
    @SuppressLint({"StaticFieldLeak"})
    private static volatile RFix a;
    private static volatile boolean b;
    final private Context c;
    private RFixLoadResult d;
    final private RFixParams e;
    final private File f;
    final private b g;
    final private a h;
    final private a i;
    final private c j;
    private a k;

    private RFix(Context context, RFixLoadResult result, RFixParams params, File file, b b, a a, a aVar1, RFixListener listener) {
        super();
        this.c = context;
        this.d = result;
        this.e = params;
        this.f = file;
        this.g = b;
        this.h = a;
        this.i = aVar1;
        this.j = new c();
        this.j.a(listener);
        this.a(params);
        this.a(context, params);
    }

    public static void a(RFix fix) {
        if (RFix.a != null) {
            throw new RuntimeException("RFix instance is already set.");
        }
        else {
            RFix.a = fix;
            RFix.b = true;
            fix.e();
        }
    }

    @Keep
    public static RFix getInstance() {
        if (RFix.a == null) {
            throw new RuntimeException("you must create RFix instance first!");
        }
        else {
            return RFix.a;
        }
    }

    @Keep
    public static boolean isInitialized() {
        return RFix.b;
    }

    @Keep
    public RFixLoadResult getLoadResult() {
        return this.d;
    }

    public File a() {
        return this.f;
    }

    public a b() {
        return this.h;
    }

    public a c() {
        return this.i;
    }

    @Keep
    public RFixParams getParams() {
        return this.e;
    }

    @Keep
    public void requestConfig() {
        this.g.a();
    }

    @Keep
    public void cleanPatch() {
        this.i.a();
    }

    public c d() {
        return this.j;
    }

    @Keep
    public void addListener(RFixListener listener) {
        this.j.a(listener);
    }

    @Keep
    public void removeListener(RFixListener listener) {
        this.j.b(listener);
    }

    private void e() {
        if (this.e.getAutoRequestEnable()) {
            this.requestConfig();
        }
        this.f();
        RFixLog.b("RFix.RFix", new StringBuilder().append("RFix initialized! version=2.0.5 publishType=com params=").append(this.e).toString());
    }

    private void a(Context context, RFixParams params) {
        b.a().a((context, params) -> {
            a a = new a(context, 1);
            a aVar1 = new a(context, 0);
            aVar1.a = params.getAppId();
            aVar1.b = params.getAppKey();
            aVar1.c = params.getAppVersion(context);
            if (aVar1.equals(a)) {
                aVar1.c();
            }
        });
    }

    private void a(RFixParams params) {
        e e = new e(this.c);
        if (e.c) {
            params.setDummyAppVersion(e.d);
            params.setDummyUserId(e.e);
        }
    }

    private void f() {
        if (this.k == null) {
            this.k = new a(this.c);
        }
        this.k.a(this.d);
        RFixLoadResult result = b.a();
        if (result != null) {
            this.d = result;
            this.k.a(result);
            a.a(result);
        }
        b.a((result) -> {
            this.d = result;
            this.k.a(result);
            a.a(result);
        });
    }

    private /* synthetic */ void a(RFixLoadResult result) {
        this.d = result;
        this.k.a(result);
        a.a(result);
    }

    private static /* synthetic */ void b(Context context, RFixParams params) {
        a a = new a(context, 1);
        a aVar1 = new a(context, 0);
        aVar1.a = params.getAppId();
        aVar1.b = params.getAppKey();
        aVar1.c = params.getAppVersion(context);
        if (aVar1.equals(a)) {
            aVar1.c();
        }
    }

    /* synthetic */ RFix(Context context, RFixLoadResult result, RFixParams params, File file, b b, a a, a aVar1, RFixListener listener, RFix$1 fix$1) {
        super(context, result, params, file, b, a, aVar1, listener);
    }

    static  {
        RFix.b = false;
    }

    // class: com/tencent/rfix/lib/RFix$a
    public class RFix$a {
        final private Context a;
        final private RFixLoadResult b;
        final private RFixParams c;
        final private File d;
        private b e;
        private a f;
        private a g;
        private RFixListener h;

        public RFix$a(Context context, RFixLoadResult result, RFixParams params) {
            super();
            if (context == null) {
                throw new RuntimeException("context must not be null.");
            }
            else if (result == null) {
                throw new RuntimeException("loadResult must not be null.");
            }
            else if (params == null) {
                throw new RuntimeException("params must not be null.");
            }
            else {
                this.a = context;
                this.b = result;
                this.c = params;
                this.d = e.a(context);
            }
        }

        public RFix$a a(a a) {
            this.g = a;
            return this;
        }

        public RFix$a a(RFixListener listener) {
            this.h = listener;
            return this;
        }

        public RFix a() {
            if (this.e == null) {
                this.e = new a(this.a);
            }
            if (this.f == null) {
                this.f = new c(this.a);
            }
            if (this.g == null) {
                this.g = new d(this.a);
            }
            return new RFix(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, null);
        }

    }
    // class: com/tencent/rfix/lib/RFix$a
    public class RFix$a {
        final private Context a;
        final private RFixLoadResult b;
        final private RFixParams c;
        final private File d;
        private b e;
        private a f;
        private a g;
        private RFixListener h;

        public RFix$a(Context context, RFixLoadResult result, RFixParams params) {
            super();
            if (context == null) {
                throw new RuntimeException("context must not be null.");
            }
            else if (result == null) {
                throw new RuntimeException("loadResult must not be null.");
            }
            else if (params == null) {
                throw new RuntimeException("params must not be null.");
            }
            else {
                this.a = context;
                this.b = result;
                this.c = params;
                this.d = e.a(context);
            }
        }

        public RFix$a a(a a) {
            this.g = a;
            return this;
        }

        public RFix$a a(RFixListener listener) {
            this.h = listener;
            return this;
        }

        public RFix a() {
            if (this.e == null) {
                this.e = new a(this.a);
            }
            if (this.f == null) {
                this.f = new c(this.a);
            }
            if (this.g == null) {
                this.g = new d(this.a);
            }
            return new RFix(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, null);
        }

    }
}
