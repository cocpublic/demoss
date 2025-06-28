/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.io.File;
import android.content.Intent;

// class: com/tencent/tinker/loader/l
public class l {

    public static boolean a(String str0, l l, Intent intent) {
        String str1 = (String)l.a().get("assets/so_meta.txt");
        if (str1 == null) {
            return true;
        }
        else {
            ArrayList list = new ArrayList();
            b.a(str1, list);
            if (list.isEmpty()) {
                return true;
            }
            else {
                String str2 = new StringBuilder().append(str0).append("/").append("lib").append("/").toString();
                HashMap map = new HashMap();
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    b b = (b)iterator.next();
                    if (b.a(b)) {
                        intent.putExtra("intent_patch_package_patch_check", 252);
                        g.a(intent, 248);
                        return false;
                    }
                    else {
                        String str3 = new StringBuilder().append(b.e).append("/").append(b.a).toString();
                        map.put(str3, b.b);
                        continue;;
                    }
                }
                File file = new File(str2);
                if (! file.exists() || file.isDirectory()) {
                    g.a(intent, 239);
                    return false;
                }
                else {
                    Iterator iteratorVar1 = map.keySet().iterator();
                    while (iteratorVar1.hasNext()) {
                        String str4 = (String)iteratorVar1.next();
                        File fileVar1 = new File(new StringBuilder().append(str2).append(str4).toString());
                        if (h.a(fileVar1)) {
                            g.a(intent, 238);
                            intent.putExtra("intent_patch_missing_lib_path", fileVar1.getAbsolutePath());
                            return false;
                        }
                        else {
                            continue;;
                        }
                    }
                    intent.putExtra("intent_patch_libs_path", map);
                    return true;
                }
            }
        }
    }

}
