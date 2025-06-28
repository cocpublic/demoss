/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.w;

// class: com/tencent/tinker/c/a/a/a/p
public class p {
    private x$a c;
    private x$a d;
    private j$g e;
    private j$g f;

    public p(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        if (jVar1 != null) {
            this.c = jVar1.a().p;
            this.d = jVar1.a().b;
            this.e = jVar1.a(this.c);
            this.f = jVar1.a(this.d);
        }
    }

    protected x$a a(j j) {
        return j.a().p;
    }

    protected w a(a a) {
        return a.a_();
    }

    protected int a(w w) {
        int i0 = this.e.a(w);
        this.f.h(i0);
        this.c.c = this.c.c + 1;
        this.d.c = this.d.c + 1;
        return i0;
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
        if (i0 != i2) {
            c.a(i0, i2);
        }
    }

    protected void a(c c, int i0, int i1) {
        c.q(i0);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((w)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
