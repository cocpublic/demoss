/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.y;

// class: com/tencent/tinker/c/a/a/a/r
public class r {
    private x$a c;
    private j$g d;

    public r(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().k;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().k;
    }

    protected y a(a a) {
        return a.b();
    }

    protected y a(a a, y y) {
        return a.a(y);
    }

    protected int a(y y) {
        this.c.c = this.c.c + 1;
        return this.d.a(y);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i1 != i3) {
            c.h(i1, i3);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.x(i1);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((y)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (y)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
