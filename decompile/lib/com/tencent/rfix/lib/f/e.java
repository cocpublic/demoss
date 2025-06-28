/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/f;

import com.tencent.rfix.loader.h.h$a;
import com.tencent.rfix.lib.atta.c;
import com.tencent.rfix.lib.RFix;
import com.tencent.rfix.lib.RFixParams;
import java.util.Map;
import java.util.HashMap;

// class: com/tencent/rfix/lib/f/e
public class e {

    public static boolean a(Context context, RFixLoadResult result, String str0) {
        int i4 = 0;
        int i5 = 0;
        if (result.a() && result.k != 0) {
            i4 = 1;
            i5 = result.k;
            goto 48;
        }
        else if (result.a == h$a.j) {
            i4 = 1;
            i5 = result.b;
        }
        if (i4 == 0) {
            return false;
        }
        else {
            return e.a(context, str0, String.valueOf(i5));
        }
    }

    private static boolean a(Context context, String str0, String str1) {
        if (context == null) {
            return false;
        }
        else {
            Map map = e.a(context);
            map.put("config_id", str0);
            map.put("safemode_error_code", str1);
            return c.a(context).a(map);
        }
    }

    private static Map<String, String> a(Context context) {
        HashMap map = new HashMap();
        map.put("attaid", "0e600064423");
        map.put("token", "3528535772");
        RFixParams params = RFix.getInstance().getParams();
        map.put("device_id", params.getDeviceId());
        map.put("user_id", params.getUserId());
        map.put("app_id", params.getAppId());
        String str0 = context.getPackageName();
        map.put("app_bundle_id", str0);
        return map;
    }

}
