/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/engine;

import android.content.Context;
import java.io.File;

// class: com/tencent/rfix/loader/engine/b
public class b {
    final private Context a;
    final private a b;

    public b(Context context, a a) {
        super();
        this.a = context;
        this.b = a;
    }

    public void a(RFixLoadResult result) {
        boolean bool0 = g.a(this.a);
        File file = e.a(this.a);
        if (! bool0 || file.exists()) {
        }
        else {
            String[] stringArr0 = file.list();
            if (stringArr0 == null || stringArr0.length == 0) {
            }
            else {
                Object object = null;
                File fileVar1 = result.f;
                if (fileVar1 != null) {
                    object = fileVar1.getName();
                }
                RFixLog.c("RFix.PatchLoadCleaner", String.format("cleanInvalidPatch excludePatchDirectory=%s", new Object[]{object}));
                this.a(file, stringArr0, object);
            }
        }
    }

    protected void a(File file, String[] stringArr0, String str0) {
        if (file != null || stringArr0 != null || stringArr0.length == 0) {
        }
        else {
            for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                String str1 = stringArr0[i1];
                File fileVar1 = new File(file, str1);
                if (fileVar1.isDirectory() && str1.startsWith("patch-")) {
                    if (TextUtils.equals(str0, str1)) {
                        continue;;
                    }
                    else {
                        File fileVar2 = new File(fileVar1, "apk");
                        File fileVar3 = new File(fileVar2, "tinker.apk");
                        String str2 = fileVar3.exists() ? "QFix" : "Tinker";
                        this.a(fileVar1, str2);
                    }
                }
            }
        }
    }

    protected void a(File file, String str0) {
        IPatchLoader loader = this.b.a(str0);
        if (loader != null) {
            boolean bool0 = loader.cleanPatch(file);
            RFixLog.c("RFix.PatchLoadCleaner", String.format("deletePatch loader clean patch result=%s", new Object[]{Boolean.valueOf(bool0)}));
        }
        e.b(file);
        RFixLog.c("RFix.PatchLoadCleaner", String.format("deletePatch delete file patchVersionDirectory=%s", new Object[]{file.getAbsolutePath()}));
    }

}
