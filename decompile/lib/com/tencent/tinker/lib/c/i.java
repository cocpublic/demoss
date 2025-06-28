/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import java.util.Iterator;
import java.io.File;
import com.tencent.tinker.loader.g$a;

// class: com/tencent/tinker/lib/c/i
public class i {

    public static boolean a(Context context, Collection<File> collection, File file, boolean bool0, boolean bool1, String str0, boolean bool2, g$b g$b) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            File fileVar1 = (File)iterator.next();
            Object object = null;
            if (a.a()) {
                object = i.a(context, fileVar1, file, bool0, bool1, str0, g$b);
            }
            if (object == null) {
                object = new g$a(context, fileVar1, file, bool0, bool1, str0, bool2, g$b);
            }
            if (object.a()) {
                return false;
            }
            else {
                continue;;
            }
        }
        return true;
    }

    private static a a(Context context, File file, File fileVar1, boolean bool0, boolean bool1, String str0, g$b g$b) {
        Object object = null;
        try {
            Class class = Class.forName(a.c());
            Constructor constructor = class.getConstructor(new Class[]{Context.class, File.class, File.class, Boolean.TYPE, Boolean.TYPE, String.class, g$b.class});
            a a = (a)constructor.newInstance(new Object[]{context, file, fileVar1, Boolean.valueOf(bool0), Boolean.valueOf(bool1), str0, g$b});
        }
        catch (Exception var_8_1) {
            n.a("Tinker.ExtendUpgradePatch", var_8_1, "create custom dex optimizer fail.", new Object[]{});
        }
        return a;
    }

}
