/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.d;

// class: com/tencent/tinker/c/a/a/a/d
public class d {
    private x$a c;
    private j$g d;

    public d(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().t;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().t;
    }

    protected d a(a a) {
        return a.m();
    }

    protected d a(a a, d d) {
        return a.a(d);
    }

    protected int a(d d) {
        this.c.c = this.c.c + 1;
        return this.d.a(d);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i1 != i3) {
            c.l(i1, i3);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.B(i1);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((d)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (d)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
