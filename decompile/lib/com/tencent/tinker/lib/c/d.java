/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import java.util.HashMap;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.d.d;
import com.tencent.tinker.loader.shareutil.a;
import com.tencent.tinker.loader.k;

// class: com/tencent/tinker/lib/c/d
public class d {
    private static ArrayList<a> a;

    protected static boolean a(b b, l l, Context context, String str0, File file) {
        String str1 = (String)l.a().get("assets/arkHot_meta.txt");
        if (str1 == null) {
            return true;
        }
        else {
            d.a(context, str0, str1, file);
            return true;
        }
    }

    private static boolean a(Context context, String str0, File file, int i0) {
        b b = b.a(context);
        Object object = null;
        try {
            file = new ZipFile(file);
            Iterator iterator = d.a.iterator();
            while (iterator.hasNext()) {
                a a = (a)iterator.next();
                String str1 = a.a;
                String str2 = str1.equals("") ? new StringBuilder().append(str1).append("/").append(a.b).toString() : a.b;
                String str3 = a.c;
                if (h.e(str3)) {
                    b.h().a(file, e.a(i0));
                    int i1 = false;
                    h.a(file);
                    return i1;
                }
                else {
                    File fileVar1 = new File(new StringBuilder().append(str0).append(a.b).toString());
                    if (fileVar1.exists()) {
                        if (str3.equals(h.f(fileVar1))) {
                            continue;;
                        }
                        else {
                            fileVar1.delete();
                        }
                    }
                    else {
                        fileVar1.getParentFile().mkdirs();
                    }
                    ZipEntry entry = file.getEntry(var_9_0);
                    if (d.a(file, entry, fileVar1, str3, 0)) {
                        b.h().a(file, fileVar1, a.b, i0);
                        int i2 = false;
                        h.a(file);
                        return i2;
                    }
                    else {
                        continue;;
                    }
                }
            }
        }
        catch (IOException var_6_1) {
            throw new k(new StringBuilder().append("patch ").append(m.e(i0)).append(" extract failed (").append(var_6_1.getMessage()).append(").").toString(), var_6_1);
        }
        finally {
            Throwable throwable = v_8;
            h.a(file);
            throw throwable;
        }
        return true;
    }

    private static boolean a(Context context, String str0, String str1, File file) {
        String str2 = new StringBuilder().append(str0).append("/").append("arkHot").append("/").toString();
        d.a.clear();
        a.a(str1, d.a);
        if (d.a(context, str2, file, 8)) {
            return false;
        }
        else {
            return true;
        }
    }

    static  {
        d.a = new ArrayList();
    }

}
