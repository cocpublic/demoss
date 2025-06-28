/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.a;

// class: com/tencent/tinker/c/a/a/a/a
public class a {
    private x$a c;
    private j$g d;

    public a(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().r;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().r;
    }

    protected a a(a a) {
        return a.j();
    }

    protected a a(a a, a aVar1) {
        return a.a(aVar1);
    }

    protected int a(a a) {
        this.c.c = this.c.c + 1;
        return this.d.a(a);
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i1 != i3) {
            c.i(i1, i3);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.y(i1);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((a)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (a)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
