/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/a;

import android.content.SharedPreferences;
import android.content.SharedPreferences$Editor;
import com.tencent.rfix.loader.entity.a;
import java.io.File;
import java.io.File[];

// class: com/tencent/rfix/lib/a/a
public class a {

    public static void a(Context context) {
        if (g.a(context)) {
        }
        else {
            try {
                SharedPreferences preferences = context.getSharedPreferences("BuglySdkInfos", 0);
                SharedPreferences$Editor editor = preferences.edit();
                editor.putString("425f7fa217", "2.0.5");
                editor.apply();
            }
            catch (Exception var_1_1) {
                RFixLog.e("RFix.BuglyInitializer", "initialize fail!", var_1_1);
            }
        }
    }

    public static void a(Context context, RFixLoadResult result) {
        if (g.a(context)) {
        }
        else {
            try {
                boolean bool0 = result.isLoaderSuccess();
                String str0 = bool0 ? "0" : "1";
                String str2 = "";
                if (bool0 && result.e != null && result.e.d != 0) {
                    str2 = String.valueOf(result.e.d);
                }
                String str3 = new StringBuilder().append("patch_tag_flag_").append(str0).append("_").append(str2).toString();
                File file = e.a(context);
                File fileVar1 = new File(file, str3);
                if (fileVar1.exists()) {
                    RFixLog.b("RFix.BuglyInitializer", new StringBuilder().append("updatePatchTag inHotFix=").append(str0).append(" patchVersion=").append(str2).toString());
                    SharedPreferences preferences = context.getSharedPreferences("BUGLY_COMMON_VALUES", 0);
                    SharedPreferences$Editor editor = preferences.edit();
                    editor.putString("D4", str0);
                    editor.putString("G15", str2);
                    editor.apply();
                    fileVar1.getParentFile().mkdirs();
                    fileVar1.createNewFile();
                    a.a(file, str3);
                }
            }
            catch (Exception var_2_1) {
                RFixLog.e("RFix.BuglyInitializer", "updatePatchTag fail!", var_2_1);
            }
        }
    }

    private static void a(File file, String str0) {
        File[] fileArr0 = file.listFiles();
        if (fileArr0 != null) {
            for (int i1 = 0; i1 < fileArr0.length; i1 += 1) {
                File fileVar1 = fileArr0[i1];
                String str1 = fileVar1.getName();
                if (str1.startsWith("patch_tag_flag_") && str1.equals(str0)) {
                    fileVar1.delete();
                }
            }
        }
    }

}
