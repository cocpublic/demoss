/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.c;

// class: com/tencent/tinker/c/a/a/a/b
public class b {
    private x$a c;
    private j$g d;

    public b(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().l;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().l;
    }

    protected c a(a a) {
        return a.l();
    }

    protected c a(a a, c c) {
        return a.a(c);
    }

    protected int a(c c) {
        this.c.c = this.c.c + 1;
        return this.d.a(c);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i1 != i3) {
            c.k(i1, i3);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.A(i1);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((c)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (c)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
