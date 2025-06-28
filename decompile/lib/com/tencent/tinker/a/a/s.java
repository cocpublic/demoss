/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/s
public final class s {
    public int a;
    public int b;
    public int c;

    public s(int i0, int i1, int i2, int i3) {
        super(i0);
        this.a = i1;
        this.b = i2;
        this.c = i3;
    }

    public int a(s s) {
        if (this.a != s.a) {
            return c.a(this.a, s.a);
        }
        else if (this.c != s.c) {
            return c.a(this.c, s.c);
        }
        else {
            return c.a(this.b, s.b);
        }
    }

    public int hashCode() {
        return e.a(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)});
    }

    public boolean equals(Object object) {
        if ((object instanceof s)) {
            return false;
        }
        else if (this.a((s)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((s)object);
    }

}
