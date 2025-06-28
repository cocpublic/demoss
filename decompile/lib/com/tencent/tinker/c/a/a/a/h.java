/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.h;

// class: com/tencent/tinker/c/a/a/a/h
public class h {
    private x$a c;
    private j$g d;

    public h(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().o;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().o;
    }

    protected h a(a a) {
        return a.g();
    }

    protected h a(a a, h h) {
        return a.a(h);
    }

    protected int a(h h) {
        this.c.c = this.c.c + 1;
        return this.d.a(h);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i1 != i3) {
            c.p(i1, i3);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.F(i1);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((h)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (h)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
