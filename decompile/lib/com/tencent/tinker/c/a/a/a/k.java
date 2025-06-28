/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.p;

// class: com/tencent/tinker/c/a/a/a/k
public class k {
    private x$a c;
    private j$g d;

    public k(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().e;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().e;
    }

    protected p a(a a) {
        return a.c();
    }

    protected p a(a a, p p) {
        return a.a(p);
    }

    protected int a(p p) {
        this.c.c = this.c.c + 1;
        return this.d.a(p);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i0 != i2) {
            c.d(i0, i2);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.t(i0);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((p)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (p)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
