/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/e;

import java.io.File;

// class: com/tencent/rfix/lib/e/a
public class a {

    public static void a(RFixLoadResult result) {
        if (result == null || result.isLoaderSuccess()) {
        }
        else {
            File file = result.h;
            RFixLog.c("RFix.FlutterPatchInjector", new StringBuilder().append("tryInject libPath=").append(file).toString());
            if (file == null) {
            }
            else {
                File fileVar1 = new File(file, "libapp.so");
                if (fileVar1.exists()) {
                    a.a(file.getAbsolutePath());
                }
                else {
                    RFixLog.c("RFix.FlutterPatchInjector", "tryInject no flutter app patch, ignore!");
                }
            }
        }
    }

    private static boolean a(String str0) {
        int i1 = false;
        try {
            Class class = Class.forName("io.flutter.embedding.engine.loader.FlutterLoader");
            Field field = class.getDeclaredField("injectAppLibDir");
            field.setAccessible(true);
            field.set(null, str0);
            i1 = 1;
        }
        catch (Exception var_2_1) {
            RFixLog.e("RFix.FlutterPatchInjector", "injectAppLib fail!", var_2_1);
        }
        RFixLog.c("RFix.FlutterPatchInjector", new StringBuilder().append("injectAppLib result=").append(i1).toString());
        return i1;
    }

}
