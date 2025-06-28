/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import java.io.InputStream;

// class: com/tencent/tinker/lib/c/g
public class g {

    public static boolean a(ZipFile file, ZipEntry entry, String str0) {
        if (str0 == null) {
            return false;
        }
        else {
            String str1 = g.a(file, entry);
            if (str1 == null) {
                return false;
            }
            else {
                return TextUtils.equals(str0, str1);
            }
        }
    }

    public static String a(ZipFile file, ZipEntry entry) {
        Object object = null;
        Object objectVar1 = null;
        try {
            InputStream stream = file.getInputStream(entry);
            String str0 = h.a(stream);
        }
        catch (Exception var_4_0) {
            n.d("Tinker.CustomDiffPatcherUtils", "getZipEntryMd5 fail!", new Object[]{var_4_0});
        }
        finally {
            Throwable throwable = v_9;
            h.a(stream);
            throw throwable;
        }
        return str0;
    }

}
