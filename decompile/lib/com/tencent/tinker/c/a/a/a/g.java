/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import com.tencent.tinker.a.a.g;

// class: com/tencent/tinker/c/a/a/a/g
public class g {
    private x$a c;
    private j$g d;

    public g(a a, j j, j jVar1, c c) {
        super(a, j, c);
        this.c = null;
        this.d = null;
        if (jVar1 != null) {
            this.c = jVar1.a().g;
            this.d = jVar1.a(this.c);
        }
    }

    protected x$a a(j j) {
        return j.a().g;
    }

    protected g a(a a) {
        return a.f();
    }

    protected g a(a a, g g) {
        return a.a(g);
    }

    protected int a(g g) {
        this.c.c = this.c.c + 1;
        return this.d.a(g);
    }

    protected /* synthetic */ int a(Comparable comparable) {
        return this.a((g)comparable);
    }

    protected /* synthetic */ Comparable a(a a, Comparable comparable) {
        return this.a(a, (g)comparable);
    }

    protected /* synthetic */ Comparable b(a a) {
        return this.a(a);
    }

}
