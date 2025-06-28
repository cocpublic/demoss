/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/h;

import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;

// class: com/tencent/rfix/loader/h/d
public class d {
    private static String a;

    public static String a(Context context) {
        if (d.a != null) {
            return d.a;
        }
        else {
            d.a = d.a(context, "PATCH_ID");
            if (d.a == null) {
                d.a = d.b(context);
            }
            RFixLog.b("RFix.ManifestUtils", String.format("getManifestPatchID patchId=%s", new Object[]{d.a}));
            return d.a;
        }
    }

    public static String b(Context context) {
        Object object = null;
        try {
            PackageManager manager = context.getPackageManager();
            ApplicationInfo info = manager.getApplicationInfo(context.getPackageName(), 128);
            String str0 = new StringBuilder().append(info.packageName).append(".BuildConfig").toString();
            Class class = Class.forName(str0);
            String str1 = (String)class.getField("DEFAULT_PATCH_ID").get(null);
        }
        catch (ClassNotFoundException var_2_1) {
            RFixLog.d("RFix.ManifestUtils", "getDefaultPatchId BuildConfig not exist!");
        }
        catch (NoSuchFieldException var_2_2) {
            RFixLog.d("RFix.ManifestUtils", "getDefaultPatchId BuildConfig.DEFAULT_PATCH_ID not exist!");
        }
        catch (Exception var_2_3) {
            RFixLog.e("RFix.ManifestUtils", "getDefaultPatchId fail!", var_2_3);
        }
        return str1;
    }

    public static String a(Context context, String str0) {
        Object object = null;
        try {
            PackageManager manager = context.getPackageManager();
            ApplicationInfo info = manager.getApplicationInfo(context.getPackageName(), 128);
            if (info.metaData != null) {
                Object objectVar1 = info.metaData.get(str0);
                if (objectVar1 != null) {
                    object = String.valueOf(objectVar1);
                }
            }
        }
        catch (Exception var_3_1) {
            RFixLog.e("RFix.ManifestUtils", "getManifestMetaData fail!", var_3_1);
        }
        return object;
    }

}
