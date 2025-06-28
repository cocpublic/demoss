/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/f;

import android.os.Handler;
import android.os.Looper;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import com.tencent.rfix.loader.d.a;
import com.tencent.rfix.loader.entity.a;
import com.tencent.rfix.loader.f.a;
import com.tencent.rfix.lib.atta.c;
import com.tencent.rfix.lib.RFix;
import com.tencent.rfix.lib.RFixParams;
import java.util.Map;
import java.util.HashMap;

// class: com/tencent/rfix/lib/f/c
public class c {

    public static boolean a(Context context, RFixLoadResult result, RFixParams params, boolean bool0, long l1) {
        if (g.a(context)) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed((context, result, params, bool0, l1) -> {
                b.a().a((context, result, params, bool0, l1) -> {
                    c.b(context, result, params, bool0, l1);
                });
            }, 5000L);
        }
        else {
            c.b(context, result, params, bool0, l1);
        }
        return true;
    }

    private static void b(Context context, RFixLoadResult result, RFixParams params, boolean bool0, long l1) {
        int i3 = 0;
        int i4 = 0;
        Object object = null;
        Object objectVar1 = null;
        int i2 = false;
        if (result != null && result.e != null) {
            a a = result.e;
            i3 = a.d > 0 ? a.a : a.d;
            i4 = a.e;
            String str0 = a.h;
            if (TextUtils.isEmpty(a.g) && a.g.length() >= 8) {
                objectVar1 = a.g.substring(0, 8);
            }
            i2 = result.isLoaderSuccess();
        }
        long l2 = b.c(a.a);
        long l3 = b.c(a.b);
        c.a(context, String.valueOf(i3), String.valueOf(i4), object, objectVar1, "Launch", bool0, null, null, l1, String.valueOf(i2), null, null, String.valueOf(l2), String.valueOf(l3), null, null, null, null, null, null, null, null, params);
    }

    public static boolean a(Context context, String str0, String str1, String str2, String str3, String str4, boolean bool0, String str5, String str6, long l1) {
        return c.a(context, str0, str1, str2, str3, str4, bool0, str5, str6, l1, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public static boolean a(Context context, String str0, String str1, String str2, String str3, String str4, boolean bool0, String str5, String str6, long l1, String l1, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18) {
        return c.a(context, str0, str1, str2, str3, str4, bool0, str5, str6, l1, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, null);
    }

    public static boolean a(Context context, String str0, String str1, String str2, String str3, String str4, boolean bool0, String str5, String str6, long l1, String l1, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, RFixParams str19) {
        if (context == null || str4 == null) {
            return false;
        }
        else {
            RFixLog.b("RFix.RFixQualityReporter", String.format("reportEvent eventName=%s eventSuccess=%s eventCode=%s eventCodeExt=%s eventTimeCost=%s configId=%s configType=%s patchType=%s patchVersion=%s", new Object[]{str4, Boolean.valueOf(bool0), str5, str6, Long.valueOf(l1), str0, str1, str2, str3}));
            d$b d$b = d.a(context, str4, bool0);
            if (d$b == d$b.d) {
                RFixLog.b("RFix.RFixQualityReporter", String.format("reportEvent eventName=%s intercept by sample!", new Object[]{str4}));
                return false;
            }
            else {
                Map map = c.a(context, params);
                map.put("config_id", str0);
                map.put("config_type", str1);
                map.put("patch_type", str2);
                map.put("patch_version", str3);
                map.put("event_name", str4);
                map.put("event_success", bool0 ? "0" : "1");
                map.put("event_code", str5);
                map.put("event_code_ext", str6);
                map.put("event_time_cost", String.valueOf(l1));
                map.put("report_type", d$b == null ? d$b.toString() : "");
                map.put("ext1", str7);
                map.put("ext2", str8);
                map.put("ext3", str9);
                map.put("time_track1", str10);
                map.put("time_track2", str11);
                map.put("time_track3", str12);
                map.put("time_track4", str13);
                map.put("time_track5", str14);
                map.put("time_track6", str15);
                map.put("time_track7", str16);
                map.put("time_track8", str17);
                map.put("time_track9", str18);
                map.put("time_track10", str19);
                return c.a(context).a(map);
            }
        }
    }

    protected static Map<String, String> a(Context context, RFixParams params) {
        String str0 = context.getPackageName();
        String str1 = g.c(context);
        Object object = null;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(str0, 0);
            String str2 = manager.getApplicationLabel(info.applicationInfo).toString();
            goto 50;
        }
        catch (Exception var_5_2) {
        }
        if (params == null) {
            params = RFix.getInstance().getParams();
        }
        HashMap map = new HashMap();
        map.put("attaid", "0ac00056535");
        map.put("token", "5422841524");
        map.put("device_id", params.getDeviceId());
        map.put("user_id", params.getUserId());
        map.put("client_type", "Android");
        map.put("client_version", String.valueOf(Build$VERSION.SDK_INT));
        map.put("client_brand", params.getDeviceManufacturer());
        map.put("client_model", params.getDeviceModel());
        map.put("app_bundle", str0);
        map.put("app_name", str2);
        map.put("app_version", params.getAppVersion(context));
        map.put("sdk_version", "2.0.5");
        map.put("sdk_appid", params.getAppId());
        map.put("process_name", str1);
        return map;
    }

    private static /* synthetic */ void c(Context context, RFixLoadResult result, RFixParams params, boolean bool0, long l1) {
        b.a().a((context, result, params, bool0, l1) -> {
            c.b(context, result, params, bool0, l1);
        });
    }

    private static /* synthetic */ void d(Context context, RFixLoadResult result, RFixParams params, boolean bool0, long l1) {
        c.b(context, result, params, bool0, l1);
    }

}
