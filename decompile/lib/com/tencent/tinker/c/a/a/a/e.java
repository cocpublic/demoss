/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.e;

// class: com/tencent/tinker/c/a/a/a/e
public class e {
    private x$a c;
    private j$g d;

    public e(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        this.c = jVar1.a().h;
        this.d = jVar1.a(this.c);
    }

    protected x$a a(j j) {
        return j.a().h;
    }

    protected e a(a a) {
        return a.v();
    }

    protected e a(a a, e e) {
        return a.a(e);
    }

    protected int a(e e) {
        this.c.c = this.c.c + 1;
        return this.d.a(e);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i0 != i2) {
            c.f(i0, i2);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.v(i0);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((e)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (e)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
