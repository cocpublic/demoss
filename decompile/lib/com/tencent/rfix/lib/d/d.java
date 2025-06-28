/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/d;

import com.tencent.rfix.lib.entity.RFixPatchResult;
import com.tencent.rfix.loader.h.h$b;

// class: com/tencent/rfix/lib/d/d
public class d {
    public int a;
    public RFixPatchResult b;

    public d() {
        super();
    }

    public boolean a() {
        return this.b.a();
    }

    public String toString() {
        return new StringBuilder().append("InstallEvent{resultCode=").append(this.a).append("resultDesc=").append(this.b.a).append(125).toString();
    }

}
