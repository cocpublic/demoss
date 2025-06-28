/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.b;

// class: com/tencent/tinker/c/a/a/a/c
public class c {
    private x$a c;
    private j$g d;

    public c(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().m;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().m;
    }

    protected b a(a a) {
        return a.k();
    }

    protected b a(a a, b b) {
        return a.a(b);
    }

    protected int a(b b) {
        this.c.c = this.c.c + 1;
        return this.d.a(b);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i1 != i3) {
            c.j(i1, i3);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.z(i1);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((b)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (b)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
