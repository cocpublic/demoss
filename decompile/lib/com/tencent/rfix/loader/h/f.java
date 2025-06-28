/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/h;

import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.security.MessageDigest;

// class: com/tencent/rfix/loader/h/f
public class f {

    public static boolean a(String str0) {
        if (str0 == null || str0.length() != 32) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean a(File file, String str0) {
        if (str0 == null) {
            return false;
        }
        else {
            String str1 = f.a(file);
            return TextUtils.equals(str1, str0);
        }
    }

    public static String a(File file) {
        if (file == null || file.exists()) {
            return null;
        }
        else {
            Object object = null;
            Object objectVar1 = null;
            try {
                FileInputStream stream = new FileInputStream(file);
                String str0 = f.a(stream);
            }
            catch (Exception var_3_0) {
                RFixLog.e("RFix.MD5Utils", "getMD5 fail.", var_3_0);
            }
            finally {
                Throwable throwable = v_11;
                e.a(stream);
                throw throwable;
            }
            return str0;
        }
    }

    public static String a(InputStream stream) {
        if (stream == null) {
            return null;
        }
        else {
            Object object = null;
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                StringBuilder builder = new StringBuilder(32);
                stream = new BufferedInputStream(stream);
                byte[] byteArr0 = new byte[]{};
                while (true) {
                    int i0 = stream.read(byteArr0);
                    if (stream.read(byteArr0) != -1) {
                        digest.update(byteArr0, 0, i0);
                    }
                    else {
                        byte[] byteArr0Var1 = digest.digest();
                        for (int i1 = 0; i1 < byteArr0Var1.length; i1 += 1) {
                            builder.append(Integer.toString(byteArr0Var1[i1] & 255 + 256, 16).substring(1));
                        }
                        String str0 = builder.toString();
                        break;;
                    }
                }
            }
            catch (Exception var_2_1) {
                RFixLog.e("RFix.MD5Utils", "getMD5 fail.", var_2_1);
            }
            return str0;
        }
    }

    public static String a(byte[] byteArr0) {
        Object object = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(byteArr0);
            byte[] byteArr0Var1 = digest.digest();
            char[] charArr0 = new char[]{};
            int i1 = 0;
            for (int i2 = 0; i2 < byteArr0Var1.length; i2 += 1) {
                byte byte0 = byteArr0Var1[i2];
                i1 += 1;
                charArr0[i1] = h.a[byte0 >>> 4 & 15];
                i1 += 1;
                charArr0[i1] = h.a[byte0 & 15];
            }
            String str0 = new String(charArr0);
        }
        catch (Exception var_2_1) {
            RFixLog.e("RFix.MD5Utils", "getMD5 fail.", var_2_1);
        }
        return str0;
    }

}
