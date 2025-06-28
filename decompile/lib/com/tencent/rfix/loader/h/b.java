/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/h;

import java.nio.charset.Charset;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

// class: com/tencent/rfix/loader/h/b
public class b {

    public static String a(String str0, String str1) {
        Object object = null;
        try {
            byte[] byteArr0 = str0.getBytes(StandardCharsets.UTF_8);
            byte[] byteArr0Var1 = str1.getBytes(StandardCharsets.UTF_8);
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(byteArr0Var1, "HmacSHA256"));
            byte[] byteArr0Var2 = mac.doFinal(byteArr0);
            StringBuilder builder = new StringBuilder();
            for (int i1 = 0; i1 < byteArr0Var2.length; i1 += 1) {
                byte byte0 = byteArr0Var2[i1];
                builder.append(Integer.toHexString(byte0 & 255 | 256).substring(1, 3));
            }
            String str2 = builder.toString().toLowerCase();
        }
        catch (Exception var_3_1) {
            RFixLog.e("RFix.EncryptUtils", "encrypt exception!", var_3_1);
        }
        return str2;
    }

}
