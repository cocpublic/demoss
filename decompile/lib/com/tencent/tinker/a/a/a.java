/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/a
public final class a {
    public byte a;
    public m b;

    public a(int i0, byte byte0, m m) {
        super(i0);
        this.a = byte0;
        this.b = m;
    }

    public o a() {
        return new o(this.b, 29);
    }

    public int a(a a) {
        int i0 = this.b.a(a.b);
        if (i0 != 0) {
            return i0;
        }
        else {
            return c.a(this.a, a.a);
        }
    }

    public int hashCode() {
        return e.a(new Object[]{Byte.valueOf(this.a), this.b});
    }

    public boolean equals(Object object) {
        if ((object instanceof a)) {
            return false;
        }
        else if (this.a((a)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((a)object);
    }

}
