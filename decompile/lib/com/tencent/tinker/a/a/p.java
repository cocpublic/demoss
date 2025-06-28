/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/p
public final class p {
    public int a;
    public int b;
    public int c;

    public p(int i0, int i1, int i2, int i3) {
        super(i0);
        this.a = i1;
        this.b = i2;
        this.c = i3;
    }

    public int a(p p) {
        if (this.a != p.a) {
            return c.a(this.a, p.a);
        }
        else if (this.c != p.c) {
            return c.a(this.c, p.c);
        }
        else {
            return c.a(this.b, p.b);
        }
    }

    public int hashCode() {
        return e.a(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)});
    }

    public boolean equals(Object object) {
        if ((object instanceof p)) {
            return false;
        }
        else if (this.a((p)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((p)object);
    }

}
