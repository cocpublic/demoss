/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/entity;

import com.tencent.rfix.loader.h.h$b;
import androidx.annotation.Keep;

// class: com/tencent/rfix/lib/entity/RFixPatchResult
public class RFixPatchResult {
    public h$b a;
    public int b;
    public boolean c;
    public long d;
    public Exception e;
    public String f;
    @Keep
    public String patchVersion;
    @Keep
    public String patchType;
    public String g;
    public int h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    @Keep
    public int configId;
    public int m;
    public String n;
    public int o;

    public RFixPatchResult() {
        super();
    }

    public boolean a() {
        if (this.a == h$b.a || this.a == h$b.g) {
            return true;
        }
        else {
            return false;
        }
    }

    @Keep
    public boolean isPatchSuccessFirstTime() {
        if (this.a == h$b.a) {
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return new StringBuilder().append("RFixPatchResult{result=").append(this.a).append(", timeCost=").append(this.d).append(", patchFilePath=").append(this.f).append(", patchVersion=").append(this.patchVersion).append(", patchType=").append(this.patchType).append(", patchId=").append(this.g).append(", patchIndex=").append(this.h).append(", installResult=").append(this.o).append(", configId=").append(this.configId).append(", configType=").append(this.m).append(", patchProcess=").append(this.n).append(125).toString();
    }

}
