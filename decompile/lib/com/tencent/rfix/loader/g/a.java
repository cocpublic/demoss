/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/g;

import java.io.File;
import com.tencent.tinker.loader.shareutil.i;

// class: com/tencent/rfix/loader/g/a
public class a {

    public static String a(Context context) {
        File file = h.a(context);
        String str0 = file.getAbsolutePath();
        File fileVar1 = h.a(str0);
        File fileVar2 = h.b(str0);
        i i = i.a(fileVar1, fileVar2);
        if (i != null) {
            return i.b;
        }
        else {
            return null;
        }
    }

}
