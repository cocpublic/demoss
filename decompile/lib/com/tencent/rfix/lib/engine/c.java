/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/engine;

import android.content.Context;

// class: com/tencent/rfix/lib/engine/c
public class c {

    public c(Context context) {
        super(context);
    }

    protected void b() {
        if ("normal".equals("com")) {
            this.a(new g(this.a));
            this.a(new QFixPatchInstaller(this.a));
            this.a(new RedirectPatchInstaller(this.a));
        }
        else {
            if ("com".equals("com")) {
                this.a(new g(this.a));
            }
        }
    }

}
