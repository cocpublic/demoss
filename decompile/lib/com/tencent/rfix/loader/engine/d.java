/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/engine;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import com.tencent.rfix.loader.entity.RFixLoadResult;
import com.tencent.rfix.loader.entity.a;
import com.tencent.rfix.loader.h.h$a;
import com.tencent.rfix.loader.f.a;
import com.tencent.rfix.loader.a.c$a;
import com.tencent.rfix.loader.c.e;
import com.tencent.rfix.loader.i.b;
import android.app.Application;

// class: com/tencent/rfix/loader/engine/d
public class d implements a {
    protected Application a;
    protected Map<String, IPatchLoader> b;
    protected b c;
    protected static IPatchLoader d;

    public d(Application application) {
        super();
        this.a = application;
        this.b = new HashMap();
        this.c = new b(application, this);
        this.b();
    }

    protected void b() {
    }

    public boolean a(IPatchLoader loader) {
        if (loader == null) {
            return false;
        }
        else {
            String str0 = loader.getPatchType();
            IPatchLoader loaderVar1 = (IPatchLoader)this.b.get(str0);
            if (loaderVar1 != null) {
                RFixLog.e("RFix.PatchLoadEngineBase", String.format("registerLoader loader already exist. patchType=%s loader=%s", new Object[]{str0, loaderVar1}));
                return false;
            }
            else {
                RFixLog.c("RFix.PatchLoadEngineBase", new StringBuilder().append("registerLoader patchType=").append(str0).toString());
                this.b.put(str0, loader);
                if ("Redirect".equals(str0)) {
                    d.d = loader;
                }
                return true;
            }
        }
    }

    public IPatchLoader a(String str0) {
        return (IPatchLoader)this.b.get(str0);
    }

    public RFixLoadResult a() {
        RFixLog.c("RFix.PatchLoadEngineBase", "tryLoadPatch...");
        RFixLoadResult result = new RFixLoadResult();
        result.a = h$a.m;
        this.a(result);
        this.b(result);
        RFixLog.c("RFix.PatchLoadEngineBase", String.format("tryLoadPatch loadResult=%s", new Object[]{result}));
        return result;
    }

    protected void a(RFixLoadResult result) {
        b.a(a.d);
        h$a h$a = this.c(result);
        b.b(a.d);
        if (h$a != h$a.a) {
            result.a = h$a;
            RFixLog.d("RFix.PatchLoadEngineBase", String.format("tryLoadPatchInternal check patch fail. checkResult=%s", new Object[]{h$a}));
        }
        else {
            b.a(a.e);
            boolean bool0 = this.h(result);
            b.b(a.e);
            result.a = bool0 ? h$a.a : h$a.l;
        }
    }

    protected void b(RFixLoadResult result) {
        try {
            this.c.a(result);
        }
        catch (Exception var_2_0) {
            RFixLog.e("RFix.PatchLoadEngineBase", "tryCleanInvalidPatch fail!", var_2_0);
        }
    }

    protected h$a c(RFixLoadResult result) {
        File file = e.a(this.a);
        String str0 = file.getAbsolutePath();
        this.b(str0);
        h$a h$aVar4 = this.a(file, result);
        if (h$aVar4 != h$a.a) {
            return h$aVar4;
        }
        else {
            if (! g.b(this.a) || this.f(result)) {
                return h$a.f;
            }
            else {
                h$aVar4 = this.e(result);
                if (h$aVar4 != h$a.a) {
                    return h$aVar4;
                }
                else {
                    h$aVar4 = this.a(str0, result);
                    if (h$aVar4 != h$a.a) {
                        return h$aVar4;
                    }
                    else {
                        h$aVar4 = this.d(result);
                        if (h$aVar4 != h$a.a) {
                            return h$aVar4;
                        }
                        else {
                            h$aVar4 = this.g(result);
                            if (h$aVar4 != h$a.a) {
                                return h$aVar4;
                            }
                            else {
                                return h$a.a;
                            }
                        }
                    }
                }
            }
        }
    }

    protected void b(String str0) {
    }

    protected h$a a(File file, RFixLoadResult result) {
        if (file == null || file.exists()) {
            return h$a.b;
        }
        else {
            a a = new a(this.a);
            result.e = a;
            if (TextUtils.isEmpty(a.g)) {
                if (a.a <= 0) {
                    return h$a.b;
                }
                else {
                    result.b = a.b < 0 ? a.c : a.b;
                    return h$a.d;
                }
            }
            else {
                boolean bool0 = this.a(this.a, a);
                if (bool0) {
                    boolean bool1 = g.a(this.a);
                    if (bool1) {
                        this.a(a);
                    }
                    return h$a.e;
                }
                else {
                    return h$a.a;
                }
            }
        }
    }

    protected boolean a(Context context, a a) {
        if (context == null || a == null) {
            return false;
        }
        else {
            String str0 = d.a(context);
            RFixLog.c("RFix.PatchLoadEngineBase", String.format("needCleanPatch removePatch=%s patchId=%s patchIdInApk=%s", new Object[]{Boolean.valueOf(a.n), a.i, str0}));
            boolean bool0 = TextUtils.equals(str0, a.i);
            if (! a.n || bool0) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    protected void a(a a) {
        RFixLog.c("RFix.PatchLoadEngineBase", String.format("cleanPatch version=%s patchType=%s", new Object[]{a.g, a.h}));
        a.d = 0;
        a.e = 0;
        a.f = "";
        a.g = "";
        a.h = "";
        a.i = "";
        a.j = false;
        a.k = false;
        a.l = false;
        a.m = false;
        a.n = false;
        a.o = false;
        a.c();
        g.d(this.a);
    }

    private h$a a(String str0, RFixLoadResult result) {
        a a = result.e;
        File file = e.a(str0, a.g);
        if (file == null || file.exists()) {
            return h$a.i;
        }
        else {
            File fileVar1 = e.b(str0, a.g);
            if (fileVar1 == null || fileVar1.exists()) {
                return h$a.i;
            }
            else {
                result.f = file;
                result.g = fileVar1;
                return h$a.a;
            }
        }
    }

    protected h$a d(RFixLoadResult result) {
        int i1 = 0;
        c$a c$a = c.a();
        if (c$a == c$a.a) {
            boolean i0 = g.a(this.a);
            goto 33;
        }
        else if (c$a == c$a.b) {
            i1 = 1;
        }
        if (i0) {
            int i2 = c.a(this.a, result);
            if (i2 != 0) {
                RFixLog.e("RFix.PatchLoadEngineBase", new StringBuilder().append("checkPatchSafeMode fail! safeModeResult=").append(i2).toString());
                a a = result.e;
                e e = new e(this.a);
                e.f = a.g;
                e.c();
                this.a(a);
                result.b = i2;
                return h$a.j;
            }
        }
        return h$a.a;
    }

    protected h$a e(RFixLoadResult result) {
        a a = result.e;
        boolean bool0 = g.a(this.a);
        if (bool0 && a.d == 0) {
            b b = new b(this.a);
            if (b.a()) {
                RFixLog.e("RFix.PatchLoadEngineBase", "checkPatchVerified remote verify fail!");
                this.a(a);
                return h$a.g;
            }
        }
        if (bool0 && a.o) {
            a.o = true;
            a.c();
            if (result.j) {
                g.d(this.a);
            }
        }
        if (a.o) {
            return h$a.a;
        }
        else {
            return h$a.h;
        }
    }

    protected boolean f(RFixLoadResult result) {
        a a = result.e;
        if (! "QFix".equals(a.h) || "Redirect".equals(a.h) && TextUtils.isEmpty(a.f)) {
            String str0 = g.c(this.a);
            String[] stringArr0 = a.f.split("\|");
            for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                String str1 = stringArr0[i1];
                if (TextUtils.equals(str1, str0)) {
                    return true;
                }
                else {
                }
            }
            return false;
        }
        else {
            return true;
        }
    }

    protected h$a g(RFixLoadResult result) {
        String str0 = result.e.h;
        IPatchLoader loader = this.a(str0);
        if (loader == null) {
            RFixLog.e("RFix.PatchLoadEngineBase", String.format("no loader support. patchType=%s", new Object[]{str0}));
            return h$a.k;
        }
        else {
            return h$a.a;
        }
    }

    protected boolean h(RFixLoadResult result) {
        String str0 = result.e.h;
        File file = result.f;
        RFixLog.c("RFix.PatchLoadEngineBase", String.format("loadPatch patchType=%s patchVersionDirectory=%s", new Object[]{str0, file.getAbsolutePath()}));
        IPatchLoader loader = this.a(str0);
        boolean bool0 = loader.loadPatch(result);
        if (bool0) {
            if (result.i) {
                this.a(result.e);
            }
            RFixLog.e("RFix.PatchLoadEngineBase", "loadPatch loader fail.");
        }
        return bool0;
    }

}
