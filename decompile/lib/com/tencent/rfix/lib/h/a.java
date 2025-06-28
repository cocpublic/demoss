/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/h;


// class: com/tencent/rfix/lib/h/a
public class a {

    public static void a(RFixLoadResult result) {
        try {
            Class class = Class.forName("com.tencent.rfix.lib.verify.AutoVerifyPatch");
            Method method = class.getDeclaredMethod("updateLoadResult", new Class[]{RFixLoadResult.class});
            method.invoke(null, new Object[]{result});
        }
        catch (Exception var_1_1) {
            RFixLog.d("RFix.AutoVerifyUtils", "updateLoadResult fail!", var_1_1);
        }
    }

}
