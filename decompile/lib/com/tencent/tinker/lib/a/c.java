/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/a;

import com.tencent.tinker.lib.e.b;

// class: com/tencent/tinker/lib/a/c
public class c {

    public static a a(Context context, boolean bool0) {
        if (b.a(context).p() == null || bool0) {
            Log.i("MicroMsg.FilePatchFactory", "BsFilePatch");
            return new b();
        }
        else {
            Log.i("MicroMsg.FilePatchFactory", "CustomPatcher");
            return b.a(context).p();
        }
    }

}
