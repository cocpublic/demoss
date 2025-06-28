/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.u;

// class: com/tencent/tinker/c/a/a/a/n
public class n {
    private x$a c;
    private j$g d;

    public n(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().d;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().d;
    }

    protected u a(a a) {
        return a.e();
    }

    protected u a(a a, u u) {
        return a.a(u);
    }

    protected int a(u u) {
        this.c.c = this.c.c + 1;
        return this.d.a(u);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i0 != i2) {
            c.c(i0, i2);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.s(i0);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((u)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (u)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
