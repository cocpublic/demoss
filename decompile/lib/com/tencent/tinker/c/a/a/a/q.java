/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;

// class: com/tencent/tinker/c/a/a/a/q
public class q {
    private x$a c;
    private j$g d;

    public q(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().c;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().c;
    }

    protected Integer a(a a) {
        return Integer.valueOf(a.r());
    }

    protected Integer a(a a, Integer integer) {
        return Integer.valueOf(a.a(integer.intValue()));
    }

    protected int a(Integer integer) {
        int i0 = this.d.o();
        this.d.h(integer.intValue());
        this.c.c = this.c.c + 1;
        return i0;
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i0 != i2) {
            c.b(i0, i2);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.r(i0);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((Integer)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (Integer)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
