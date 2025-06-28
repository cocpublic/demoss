/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/engine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.File;
import android.content.Context;
import com.tencent.rfix.lib.RFix;
import com.tencent.rfix.lib.RFixParams;
import com.tencent.rfix.lib.entity.RFixPatchResult;
import com.tencent.rfix.lib.b.b;
import com.tencent.rfix.loader.d.a;
import com.tencent.rfix.loader.d.a$a;
import com.tencent.rfix.loader.entity.a;
import com.tencent.rfix.loader.c.e;
import com.tencent.rfix.loader.h.h$b;
import com.tencent.rfix.loader.f.a;

// class: com/tencent/rfix/lib/engine/d
public class d implements a {
    final private static AtomicBoolean c;
    final protected Context a;
    final protected Map<String, IPatchInstaller> b;

    public d(Context context) {
        super();
        this.a = context;
        this.b = new HashMap();
        this.b();
    }

    protected void b() {
    }

    public boolean a(IPatchInstaller installer) {
        if (installer == null) {
            return false;
        }
        else {
            String str0 = installer.getPatchType();
            IPatchInstaller installerVar1 = (IPatchInstaller)this.b.get(str0);
            if (installerVar1 != null) {
                RFixLog.e("RFix.PatchEngineBase", String.format("registerInstaller installer already exist. patchType=%s installer=%s", new Object[]{str0, installerVar1}));
                return false;
            }
            else {
                RFixLog.c("RFix.PatchEngineBase", new StringBuilder().append("registerLoader patchType=").append(str0).toString());
                this.b.put(str0, installer);
                return true;
            }
        }
    }

    public IPatchInstaller a(String str0) {
        return (IPatchInstaller)this.b.get(str0);
    }

    public void a(String str0, PatchConfig config) {
        RFixLog.c("RFix.PatchEngineBase", String.format("onPatchReceived path=%s", new Object[]{str0}));
        if (g.a(this.a)) {
            RFixLog.e("RFix.PatchEngineBase", "onPatchReceived only execute in main process.");
        }
        else {
            RFixParams params = RFix.getInstance().getParams();
            if (params.isEnable()) {
                RFixLog.e("RFix.PatchEngineBase", "onPatchReceived enable is false.");
            }
            else {
                b.a().a(new d$1(this, str0, config), a$a.c);
            }
        }
    }

    public void a() {
        a a = new a(this.a);
        if (TextUtils.isEmpty(a.g)) {
            RFixLog.c("RFix.PatchEngineBase", "cleanPatch patch info empty.");
        }
        else {
            RFixLog.c("RFix.PatchEngineBase", String.format("cleanPatch version=%s patchType=%s", new Object[]{a.g, a.h}));
            a.n = true;
            a.c();
            e e = new e(this.a);
            e.g = null;
            e.h = 0;
            e.c();
            super.a(a);
        }
    }

    protected RFixPatchResult a(String str0, RFix fix, a a, PatchConfig config) {
        RFixPatchResult result = new RFixPatchResult();
        result.a = h$b.o;
        result.f = str0;
        if (config != null) {
            result.configId = config.configId;
            result.n = config.a;
        }
        if (d.c.compareAndSet(false, true)) {
            RFixLog.d("RFix.PatchEngineBase", "applyPatchAsync apply patch is running by another runner.");
            result.a = h$b.b;
            return result;
        }
        else {
            RFixLog.c("RFix.PatchEngineBase", "applyPatchAsync applying...");
            try {
                b.a(a.m);
                h$b h$b = super.a(str0, fix, a, result);
                b.b(a.m);
                if (h$b == h$b.a) {
                    b.a(a.n);
                    h$b h$bVar1 = this.a(fix, result);
                    b.b(a.n);
                    result.a = h$bVar1;
                    RFixLog.c("RFix.PatchEngineBase", String.format("applyPatchAsync apply patch installResult=%s", new Object[]{h$bVar1}));
                }
                a aVar1 = new a(this.a);
                aVar1.c = result.a.ordinal();
                if (result.a == h$b.a) {
                    aVar1.d = result.configId;
                    aVar1.e = result.m;
                    aVar1.f = result.n;
                    aVar1.g = result.patchVersion;
                    aVar1.h = result.patchType;
                    aVar1.i = result.g;
                    aVar1.j = result.i;
                    aVar1.k = result.j;
                    aVar1.l = result.k;
                    aVar1.m = result.l;
                    aVar1.n = false;
                    aVar1.o = super.a(fix.getParams(), result);
                }
                aVar1.c();
            }
            catch (Exception var_6_1) {
                result.a = h$b.o;
                result.e = var_6_1;
                RFixLog.e("RFix.PatchEngineBase", "doApplyAsync fail.", var_6_1);
            }
            d.c.set(false);
            RFixLog.c("RFix.PatchEngineBase", String.format("doApplyAsync done. result=%s", new Object[]{result}));
            return result;
        }
    }

    private boolean a(RFixParams params, RFixPatchResult result) {
        if ("Tinker".equals(result.patchType)) {
            return false;
        }
        else if (params.getMainVerifyEnable()) {
            return true;
        }
        else {
            return false;
        }
    }

    private h$b a(String str0, RFix fix, a a, RFixPatchResult result) {
        File file = new File(str0);
        String str1 = f.a(file);
        result.patchVersion = str1;
        result.patchType = a.a("PATCH_TYPE");
        result.i = a.a("EFFECT_IMMEDIATE", 0);
        result.j = a.a("enable_assert_dex", 0);
        result.k = a.a("enable_assert_lib", 0);
        result.l = a.a("enable_assert_res", 0);
        h$b h$b = this.a(fix, file, str1, a);
        if (h$b == h$b.a) {
            result.g = a.c();
            result.h = a.d();
        }
        result.a = h$b;
        result.b = new e(this.a).h;
        RFixLog.c("RFix.PatchEngineBase", String.format("doCheckPatch checkResult=%s path=%s md5=%s", new Object[]{h$b, str0, str1}));
        return h$b;
    }

    protected h$b a(RFix fix, File file, String str0, a a) {
        if (e.a(file)) {
            return h$b.c;
        }
        else if (f.a(str0)) {
            return h$b.d;
        }
        else {
            a aVar1 = new a(this.a);
            if (super.a(aVar1, str0)) {
                return h$b.g;
            }
            else {
                e e = new e(this.a);
                String str1 = e.f;
                if (TextUtils.equals(str1, str0)) {
                    return h$b.e;
                }
                else if (this.b(str0)) {
                    return h$b.f;
                }
                else if (a.a()) {
                    return h$b.h;
                }
                else {
                    String str2 = a.a("PATCH_TYPE");
                    if (a.a(str2)) {
                        return h$b.i;
                    }
                    else if (a.b()) {
                        return h$b.j;
                    }
                    else {
                        return h$b.a;
                    }
                }
            }
        }
    }

    private boolean b(String str0) {
        v_0 = new e(this.a);
        e e = new e(this.a);
        if (TextUtils.equals(e.g, str0)) {
            e.g = str0;
            e.h = 0;
            e.c();
            return false;
        }
        else if (e.h < 10) {
            v_20.h = e.h + 1;
            e.c();
            return false;
        }
        else {
            return true;
        }
    }

    protected h$b a(RFix fix, RFixPatchResult result) {
        File file = new File(result.f);
        String str0 = result.patchVersion;
        String str1 = result.patchType;
        String str2 = fix.a().getAbsolutePath();
        File fileVar1 = e.a(str2, str0);
        RFixLog.c("RFix.PatchEngineBase", String.format("installPatch patchVersionDirectory=%s", new Object[]{fileVar1.getAbsolutePath()}));
        File fileVar2 = e.b(str2, str0);
        if (super.a(file, str0, fileVar2)) {
            RFixLog.e("RFix.PatchEngineBase", "installPatch copy path file fail.");
            return h$b.k;
        }
        else {
            IPatchInstaller installer = this.a(str1);
            if (installer == null) {
                RFixLog.e("RFix.PatchEngineBase", String.format("installPatch no installer support. patchType=%s", new Object[]{str1}));
                return h$b.l;
            }
            else {
                boolean bool0 = installer.installPatch(fileVar2, fileVar1, result);
                if (bool0) {
                    RFixLog.e("RFix.PatchEngineBase", new StringBuilder().append("installPatch install patch fail. installResult=").append(result.o).toString());
                    return d.a(result, str1);
                }
                else {
                    RFixLog.c("RFix.PatchEngineBase", "installPatch install patch success.");
                    return h$b.a;
                }
            }
        }
    }

    private static h$b a(RFixPatchResult result, String str0) {
        if ("Tinker".equals(str0)) {
            if (result.o == 153) {
                return h$b.b;
            }
            else if (result.o == 150) {
                return h$b.g;
            }
            else if (result.o == 149) {
                return h$b.f;
            }
            else {
                return h$b.m;
            }
        }
        else {
            return h$b.m;
        }
    }

    private boolean a(File file, String str0, File fileVar1) {
        try {
            String str1 = f.a(fileVar1);
            if (str0.equals(str1)) {
                e.a(file, fileVar1);
            }
        }
        catch (IOException var_4_1) {
            RFixLog.e("RFix.PatchEngineBase", "copyFileWithMD5Check fail.", var_4_1);
            return false;
        }
        return true;
    }

    private boolean a(a a, String str0) {
        if (a != null) {
            if (a.n && TextUtils.equals(a.g, str0)) {
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

    private void a(a a) {
        if ("Redirect".equals(a.h) && a.j) {
            b.c();
        }
    }

    private void a(RFixPatchResult result) {
        if ("Redirect".equals(result.patchType) && result.i) {
            b.b();
        }
    }

    private void b(RFixPatchResult result) {
        int i0 = result.a() ? 0 : result.configId;
        b.a(this.a).b(i0);
    }

    private void c(RFixPatchResult result) {
        String str0 = String.valueOf(result.configId);
        String str1 = String.valueOf(result.m);
        String str2 = result.patchType;
        Object object = null;
        if (TextUtils.isEmpty(result.patchVersion)) {
            object = result.patchVersion.substring(0, 8);
        }
        boolean bool0 = result.a();
        String str3 = result.a.toString();
        String str4 = String.valueOf(result.o);
        long l0 = result.d;
        int i0 = result.b;
        boolean bool1 = result.c;
        long l1 = b.c(a.m);
        long l2 = b.c(a.n);
        long l3 = b.c(a.o);
        long l4 = b.c(a.p);
        long l5 = b.c(a.q);
        long l6 = b.c(a.r);
        long l7 = b.c(a.s);
        c.a(this.a, str0, str1, str2, object, "Install", bool0, str3, str4, l0, String.valueOf(i0), String.valueOf(bool1), null, String.valueOf(l1), String.valueOf(l2), String.valueOf(l3), String.valueOf(l4), String.valueOf(l5), String.valueOf(l6), String.valueOf(l7), null, null, null);
        b.a(this.a, bool0, l0, str3);
    }

    static /* synthetic */ void a(d d, RFixPatchResult result) {
        d.b(result);
    }

    static /* synthetic */ void b(d d, RFixPatchResult result) {
        d.c(result);
    }

    static /* synthetic */ void c(d d, RFixPatchResult result) {
        d.a(result);
    }

    static  {
        d.c = new AtomicBoolean(false);
    }

}
