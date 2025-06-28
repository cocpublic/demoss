/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/entity;

import com.tencent.rfix.loader.h.h$a;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

// class: com/tencent/rfix/loader/entity/RFixLoadResult
public class RFixLoadResult {
    public h$a a;
    public int b;
    public long c;
    public Throwable d;
    public a e;
    public File f;
    public File g;
    public File h;
    public boolean i;
    public boolean j;
    public int k;
    public int l;
    public boolean m;
    public Intent n;
    public long o;
    public long p;

    @Keep
    public RFixLoadResult() {
        super();
        this.a = h$a.m;
        this.l = 0;
        this.m = false;
        this.n = null;
        this.o = 0L;
        this.p = 0L;
    }

    public boolean a() {
        if (! this.a.a() || this.a == h$a.a) {
            return true;
        }
        else {
            return false;
        }
    }

    @Keep
    public boolean isLoaderSuccess() {
        if (this.a == h$a.a) {
            return true;
        }
        else {
            return false;
        }
    }

    @NonNull
    public String toString() {
        return new StringBuilder().append("RFixLoadResult{result=").append(this.a).append(", subResult=").append(this.b).append(", timeCost=").append(this.c).append(", patchInfo=").append(this.e).append(125).toString();
    }

}
