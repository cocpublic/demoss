/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/d;

import com.tencent.rfix.lib.config.PatchConfig;

// class: com/tencent/rfix/lib/d/a
public class a {
    public int a;
    public PatchConfig b;

    public a() {
        super();
    }

    public boolean a() {
        if (this.a == e.a) {
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return new StringBuilder().append("ConfigEvent{resultCode=").append(this.a).append("configId=").append(this.b.configId).append(125).toString();
    }

}
