/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/u
public final class u {
    public int a;
    public int b;
    public int c;

    public u(int i0, int i1, int i2, int i3) {
        super(i0);
        this.a = i1;
        this.b = i2;
        this.c = i3;
    }

    public int a(u u) {
        int i1 = c.a(this.a, u.a);
        if (i1 != 0) {
            return i1;
        }
        else {
            i1 = c.a(this.b, u.b);
            if (i1 != 0) {
                return i1;
            }
            else {
                return c.b(this.c, u.c);
            }
        }
    }

    public int hashCode() {
        return e.a(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)});
    }

    public boolean equals(Object object) {
        if ((object instanceof u)) {
            return false;
        }
        else if (this.a((u)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((u)object);
    }

}
