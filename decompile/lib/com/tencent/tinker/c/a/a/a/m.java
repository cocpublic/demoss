/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.s;

// class: com/tencent/tinker/c/a/a/a/m
public class m {
    private x$a c;
    private j$g d;

    public m(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().f;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().f;
    }

    protected s a(a a) {
        return a.d();
    }

    protected s a(a a, s s) {
        return a.a(s);
    }

    protected int a(s s) {
        this.c.c = this.c.c + 1;
        return this.d.a(s);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i0 != i2) {
            c.e(i0, i2);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.u(i0);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((s)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (s)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
