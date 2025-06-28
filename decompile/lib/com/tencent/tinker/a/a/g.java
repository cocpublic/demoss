/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/g
public final class g {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;

    public g(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        super(i0);
        this.a = i1;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
        this.h = i8;
    }

    public int a(g g) {
        int i6 = c.a(this.a, g.a);
        if (i6 != 0) {
            return i6;
        }
        else {
            i6 = c.b(this.b, g.b);
            if (i6 != 0) {
                return i6;
            }
            else {
                i6 = c.a(this.c, g.c);
                if (i6 != 0) {
                    return i6;
                }
                else {
                    i6 = c.b(this.d, g.d);
                    if (i6 != 0) {
                        return i6;
                    }
                    else {
                        i6 = c.a(this.e, g.e);
                        if (i6 != 0) {
                            return i6;
                        }
                        else {
                            i6 = c.b(this.f, g.f);
                            if (i6 != 0) {
                                return i6;
                            }
                            else {
                                i6 = c.b(this.g, g.g);
                                if (i6 != 0) {
                                    return i6;
                                }
                                else {
                                    return c.b(this.h, g.h);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public int hashCode() {
        return e.a(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f), Integer.valueOf(this.g), Integer.valueOf(this.h)});
    }

    public boolean equals(Object object) {
        if ((object instanceof g)) {
            return false;
        }
        else if (this.a((g)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((g)object);
    }

}
