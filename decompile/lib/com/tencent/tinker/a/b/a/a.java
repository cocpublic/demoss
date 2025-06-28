/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/b/a;

import com.tencent.tinker.a.c.b;

// class: com/tencent/tinker/a/b/a/a
public abstract class a {
    final private b a;
    private int b;

    public a() {
        super();
        this.a = new b();
        this.b = 0;
    }

    final public int a() {
        return this.b;
    }

    final public int b() {
        int i0 = this.a.d(this.b);
        if (i0 < 0) {
            return this.b;
        }
        else {
            return this.a.c(i0);
        }
    }

    final public void a(int i0, int i1) {
        this.a.a(i0, i1);
    }

    public void c() {
        this.a.c();
        this.b = 0;
    }

    final protected void a(int i0) {
        this.b = this.b + i0;
    }

}
