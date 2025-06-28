/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.f;

// class: com/tencent/tinker/c/a/a/a/f
public class f {
    private x$a c;
    private j$g d;

    public f(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().n;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().n;
    }

    protected f a(a a) {
        return a.i();
    }

    protected f a(a a, f f) {
        return a.a(f);
    }

    protected int a(f f) {
        this.c.c = this.c.c + 1;
        return this.d.a(f);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i1 != i3) {
            c.n(i1, i3);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.D(i1);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((f)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (f)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
