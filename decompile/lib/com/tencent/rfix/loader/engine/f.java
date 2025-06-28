/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/engine;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.h;
import com.tencent.tinker.loader.shareutil.i;
import com.tencent.rfix.loader.f.a;
import com.tencent.rfix.loader.entity.a;
import android.content.Intent;
import java.io.File;
import androidx.annotation.Nullable;

// class: com/tencent/rfix/loader/engine/f
public class f implements IPatchLoader {
    private TinkerApplication a;

    public f(Application application) {
        super();
        this.a = (TinkerApplication)application;
    }

    public String getPatchType() {
        return "Tinker";
    }

    public boolean loadPatch(RFixLoadResult result) {
        Intent intentVar1;
        try {
            m.a(this.a, 0);
            b.a(a.f);
            h h = new h();
            intentVar1 = h.a(this.a);
            b.b(a.f);
            b.a(a.h);
            this.a(intentVar1, result);
            b.b(a.h);
        }
        catch (Throwable var_3_2) {
            RFixLog.e("RFix.TinkerPatchLoader", "loadPath fail.", var_3_2);
            intentVar1 = new Intent();
            g.a(intentVar1, 236);
            intentVar1.putExtra("intent_patch_exception", var_3_2);
        }
        result.n = intentVar1;
        int i0 = g.a(intentVar1);
        boolean bool0 = this.a(i0);
        result.b = i0;
        boolean bool1 = this.a(bool0, i0);
        result.i = bool1;
        RFixLog.c("RFix.TinkerPatchLoader", String.format("loadPath returnCode=%s loadSuccess=%s needCleanPatch=%s", new Object[]{Integer.valueOf(i0), Boolean.valueOf(bool0), Boolean.valueOf(bool1)}));
        return bool0;
    }

    public boolean cleanPatch(File file) {
        File fileVar1 = new File(file, "apk");
        File fileVar2 = new File(fileVar1, "tinker.apk");
        if (e.a(fileVar2)) {
            String str0 = h.f(fileVar2);
            String str1 = h.c(str0);
            File fileVar3 = h.a(this.a);
            File fileVar4 = new File(fileVar3, str1);
            e.b(fileVar4);
            RFixLog.c("RFix.TinkerPatchLoader", String.format("cleanPatch delete tinkerPatchVersionDirectory=%s", new Object[]{fileVar4.getAbsolutePath()}));
            i i = this.a(fileVar3);
            RFixLog.c("RFix.TinkerPatchLoader", String.format("cleanPatch fix tinkerPatchInfo=%s", new Object[]{i}));
            return true;
        }
        else {
            return false;
        }
    }

    public boolean unloadPatchImmediate() {
        return false;
    }

    protected void a(Intent intent, RFixLoadResult result) {
        File file = f.a(intent, this.a);
        if (file == null) {
            RFixLog.e("RFix.TinkerPatchLoader", "patchVersionDir invalid.");
        }
        else {
            String str0 = new String[]{Build.CPU_ABI2, Build.CPU_ABI};
            RFixLog.c("RFix.TinkerPatchLoader", new StringBuilder().append("loadPatchSo supportedABIs=").append(Arrays.toString(str0)).toString());
            Object object = null;
            for (int i1 = 0; i1 < str0.length; i1 += 1) {
                String str2 = str0[i1];
                if (TextUtils.isEmpty(str2)) {
                    File fileVar1 = new File(file, new StringBuilder().append("/lib/lib/").append(str2).toString());
                    if (fileVar1.exists()) {
                        RFixLog.c("RFix.TinkerPatchLoader", new StringBuilder().append("loadPatchSo lib patch not exists. libPath=").append(fileVar1).toString());
                        continue;;
                    }
                    else {
                        ClassLoader loader = this.a.getClassLoader();
                        RFixLog.c("RFix.TinkerPatchLoader", new StringBuilder().append("loadPatchSo before hack classLoader=").append(loader).append(" libPath=").append(fileVar1).toString());
                        a.a(loader, fileVar1);
                        RFixLog.c("RFix.TinkerPatchLoader", new StringBuilder().append("loadPatchSo after hack classLoader=").append(loader).toString());
                    }
                }
            }
            result.h = fileVar1;
        }
    }

    public static File a(Intent intent, Context context) {
        int i0 = g.a(intent);
        if (i0 != 0) {
            return null;
        }
        else {
            String str0 = f.b(intent, context);
            String str1 = h.c(str0);
            if (TextUtils.isEmpty(str1)) {
                RFixLog.e("RFix.TinkerPatchLoader", "getTinkerPatchVersionDir fail to get current patch version.");
                return null;
            }
            else {
                File file = h.a(context);
                return new File(file, str1);
            }
        }
    }

    @Nullable
    public static String b(Intent intent, Context context) {
        Object object = null;
        String str0 = g.a(intent, "intent_patch_old_version");
        String str1 = g.a(intent, "intent_patch_new_version");
        boolean bool0 = m.i(context);
        if (str0 != null && str1 != null) {
            object = bool0 ? str0 : str1;
        }
        return object;
    }

    protected boolean a(int i0) {
        if (g.a(this.a)) {
            if (i0 == 0 || i0 == 254) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (i0 == 0 || i0 == 251) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    protected boolean a(boolean bool0, int i0) {
        if (bool0 && g.a(this.a)) {
            if (i0 != 253 || i0 != 250 || i0 == 249) {
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

    protected i a(File file) {
        String str0 = file.getAbsolutePath();
        File fileVar1 = h.a(str0);
        File fileVar2 = h.b(str0);
        i i = i.a(fileVar1, fileVar2);
        if (i != null) {
            File fileVar3 = e.a(this.a);
            a a = new a(this.a);
            if ("Tinker".equals(a.h)) {
                String str1 = fileVar3.getAbsolutePath();
                File fileVar4 = e.a(str1, a.g);
                File fileVar5 = new File(fileVar4, "apk");
                File fileVar6 = new File(fileVar5, "tinker.apk");
                String str2 = h.f(fileVar6);
                if (str2 != null) {
                    i.a = str2;
                    i.b = str2;
                }
            }
            else {
                i.a = "";
                i.b = "";
            }
            i.a(fileVar1, i, fileVar2);
        }
        return i;
    }

}
