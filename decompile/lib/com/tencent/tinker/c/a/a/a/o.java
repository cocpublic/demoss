/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.m;

// class: com/tencent/tinker/c/a/a/a/o
public class o {
    private x$a c;
    private j$g d;

    public o(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().s;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().s;
    }

    protected m a(a a) {
        return a.n();
    }

    protected m a(a a, m m) {
        return a.a(m);
    }

    protected int a(m m) {
        this.c.c = this.c.c + 1;
        return this.d.a(m);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i1 != i3) {
            c.m(i1, i3);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.C(i1);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((m)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (m)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
