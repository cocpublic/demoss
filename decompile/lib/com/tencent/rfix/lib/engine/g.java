/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/engine;

import java.io.File;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import android.content.Context;
import com.tencent.rfix.loader.f.a;
import com.tencent.rfix.loader.entity.b;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.b.b;
import com.tencent.tinker.loader.shareutil.i;

// class: com/tencent/rfix/lib/engine/g
public class g implements b {
    final private static Object h;
    protected Context a;
    protected String b;
    protected String c;
    protected boolean d;
    protected int e;
    protected boolean f;
    protected boolean g;

    public g(Context context) {
        super();
        this.a = context;
    }

    public String getPatchType() {
        return "Tinker";
    }

    public boolean installPatch(File file, File fileVar1, RFixPatchResult result) {
        this.b = new StringBuilder().append("tinker-").append(result.h).toString();
        RFixLog.c("RFix.TinkerPatchInstaller", new StringBuilder().append("installPatch subDirName=").append(this.b).toString());
        File fileVar2 = new File(fileVar1, "apk");
        File fileVar3 = e.b(this.a);
        if (this.a(file, fileVar2)) {
            RFixLog.e("RFix.TinkerPatchInstaller", "installPatch extract patch file fail.");
            result.o = 254;
            return false;
        }
        else {
            File fileVar4 = new File(fileVar2, "tinker.apk");
            File fileVar5 = new File(fileVar3, "tinker.apk");
            if (this.b(fileVar4, fileVar5)) {
                RFixLog.e("RFix.TinkerPatchInstaller", "installPatch copy patch file fail.");
                result.o = 253;
                return false;
            }
            else if (this.b()) {
                RFixLog.e("RFix.TinkerPatchInstaller", "installPatch init tinker instance fail.");
                result.o = 252;
                return false;
            }
            else if (this.a(fileVar5, result)) {
                RFixLog.e("RFix.TinkerPatchInstaller", "installPatch install patch fail.");
                return false;
            }
            else {
                result.o = 0;
                return true;
            }
        }
    }

    public void a(boolean bool0, int i0, String str0, boolean bool1, long l1, long l1, long l3, long l3, long l5) {
        g.h;
        synchronized () {
            RFixLog.c("RFix.TinkerPatchInstaller", String.format("onTinkerPatchResultReceived success=%s resultCode=%s patchVersion=%s oatGenerated=%s", new Object[]{Boolean.valueOf(bool0), Integer.valueOf(i0), str0, Boolean.valueOf(bool1)}));
            if (str0 == null || TextUtils.equals(str0, this.c)) {
                this.d = bool0;
                this.e = i0;
                this.f = bool1;
                this.g = true;
                b.a(a.o, l1);
                b.a(a.q, l5);
                b.a(a.p, l7);
                b.a(a.r, l3);
                b.a(a.s, l9);
                g.h.notify();
                RFixLog.c("RFix.TinkerPatchInstaller", "onTinkerPatchResultReceived sWaitLock notify!");
            }
            return;
        }
    }

    public void a() {
        g.h;
        synchronized () {
            RFixLog.c("RFix.TinkerPatchInstaller", String.format("onTinkerPatchProcessKilled tinkerPatchResultReceived=%s", new Object[]{Boolean.valueOf(this.g)}));
            if (this.g) {
                String str0 = a.a(this.a);
                if (TextUtils.equals(str0, this.c)) {
                    RFixLog.c("RFix.TinkerPatchInstaller", "onTinkerPatchProcessKilled patch has installed.");
                    this.d = true;
                }
                else {
                    this.d = false;
                    this.e = 65231;
                }
                this.g = true;
                g.h.notify();
                RFixLog.c("RFix.TinkerPatchInstaller", "onTinkerPatchProcessKilled sWaitLock notify!");
            }
            return;
        }
    }

    protected boolean a(File file, File fileVar1) {
        Object object = null;
        try {
            file = new ZipFile(file);
            String str0 = new StringBuilder().append(this.b).append("/").append("patch.apk").toString();
            ZipEntry entry = file.getEntry(str0);
            File fileVar2 = new File(fileVar1, "tinker.apk");
            if (e.a(file, entry, fileVar2, null)) {
                RFixLog.e("RFix.TinkerPatchInstaller", "tryExtractPatchFile extract patch file fail.");
                int i0 = false;
                e.a(file);
                return i0;
            }
            else {
            }
        }
        catch (IOException var_4_1) {
            RFixLog.e("RFix.TinkerPatchInstaller", "tryExtractPatchFile fail.", var_4_1);
            int i1 = false;
            e.a(file);
            return i1;
        }
        finally {
            Throwable throwable = v_24;
            e.a(file);
            throw throwable;
        }
        RFixLog.c("RFix.TinkerPatchInstaller", "tryExtractPatchFile extract patch file success.");
        return true;
    }

    protected boolean b(File file, File fileVar1) {
        try {
            e.a(file, fileVar1);
        }
        catch (IOException var_3_0) {
            RFixLog.e("RFix.TinkerPatchInstaller", "tryCopyPatchFile fail.", var_3_0);
            return false;
        }
        return true;
    }

    protected boolean b() {
        try {
            if (b.a()) {
                f.a();
            }
        }
        catch (Exception var_1_0) {
            RFixLog.e("RFix.TinkerPatchInstaller", "tryCheckAndInstallTinker fail.", var_1_0);
            return false;
        }
        return true;
    }

    protected boolean a(File file, RFixPatchResult result) {
        try {
            b b = b.a(this.a);
            int i0 = b.q().a(file.getAbsolutePath());
            if (i0 == 250) {
                RFixLog.c("RFix.TinkerPatchInstaller", "tryInstallPatchAndWaitResult patch already installed.");
                result.o = 156 + i0;
                String str0 = f.a(file);
                this.a(str0);
                return true;
            }
            else if (i0 != 0) {
                RFixLog.e("RFix.TinkerPatchInstaller", "tryInstallPatchAndWaitResult tinker receive fail.");
                result.o = 156 + i0;
                return false;
            }
            else {
                g.h;
                synchronized () {
                    this.c = f.a(file);
                    this.d = false;
                    this.e = 0;
                    this.f = false;
                    this.g = false;
                    b bVar1 = new b(this.a, 0);
                    bVar1.a = result.patchVersion;
                    bVar1.b = result.patchType;
                    bVar1.c = result.g;
                    bVar1.d = this.c;
                    bVar1.e = result.configId;
                    bVar1.f = result.m;
                    bVar1.g = result.n;
                    bVar1.h = result.j;
                    bVar1.i = result.k;
                    bVar1.j = result.l;
                    bVar1.c();
                    RFixLog.c("RFix.TinkerPatchInstaller", "tryInstallPatchAndWaitResult sWaitLock waiting...");
                    g.h.wait(600000L);
                    if (this.g) {
                        String str1 = a.a(this.a);
                        if (TextUtils.equals(str1, this.c)) {
                            RFixLog.c("RFix.TinkerPatchInstaller", "tryInstallPatchAndWaitResult patch has installed.");
                            this.d = true;
                        }
                        else {
                            this.d = false;
                            this.e = 65230;
                        }
                        this.g = true;
                    }
                    bVar1.d();
                    if (this.d) {
                        RFixLog.e("RFix.TinkerPatchInstaller", "tryInstallPatchAndWaitResult tinker install fail.");
                        result.o = this.e;
                        return false;
                    }
                    else {
                        result.c = this.f;
                    }
                }
            }
        }
        catch (Exception var_3_1) {
            RFixLog.e("RFix.TinkerPatchInstaller", "tryInstallPatchAndWaitResult fail.", var_3_1);
            result.o = 64537;
            return false;
        }
        RFixLog.c("RFix.TinkerPatchInstaller", "tryInstallPatchAndWaitResult install patch success.");
        result.o = 0;
        return true;
    }

    protected void a(String str0) {
        File file = h.a(this.a);
        String str1 = file.getAbsolutePath();
        File fileVar1 = h.a(str1);
        File fileVar2 = h.b(str1);
        i i = i.a(fileVar1, fileVar2);
        if (i != null && TextUtils.equals(i.b, str0)) {
            i.b = str0;
            i.a(fileVar1, i, fileVar2);
        }
    }

    static  {
        g.h = new Object();
    }

}
