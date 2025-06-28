/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.i;

// class: com/tencent/tinker/c/a/a/a/i
public class i {
    private x$a c;
    private j$g d;

    public i(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().q;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().q;
    }

    protected i a(a a) {
        return a.h();
    }

    protected i a(a a, i i) {
        return a.a(i);
    }

    protected int a(i i) {
        this.c.c = this.c.c + 1;
        return this.d.a(i);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i1 != i3) {
            c.o(i1, i3);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.E(i1);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((i)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (i)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
