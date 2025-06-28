/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/d
public class d {
    public int a;
    public int[] b;
    public int[] c;
    public int[] d;

    public d(int i0, int i1, int[] intArr0, int[] intArr0Var1, int[] intArr0Var2) {
        super(i0);
        this.a = i1;
        this.b = intArr0;
        this.c = intArr0Var1;
        this.d = intArr0Var2;
    }

    public int a(d d) {
        if (this.a != d.a) {
            return c.a(this.a, d.a);
        }
        else {
            if (this.b.length != d.b.length) {
                return c.b(this.b.length, d.b.length);
            }
            else if (this.c.length != d.c.length) {
                return c.b(this.c.length, d.c.length);
            }
            else if (this.d.length != d.d.length) {
                return c.b(this.d.length, d.d.length);
            }
            else {
                for (int i16 = 0; i16 < this.b.length; i16 += 1) {
                    int i7 = this.b[i16][0];
                    int i8 = this.b[i16][1];
                    int i9 = d.b[i16][0];
                    int i10 = d.b[i16][1];
                    if (i7 != i9) {
                        return c.a(i7, i9);
                    }
                    else if (i8 != i10) {
                        return c.b(i8, i10);
                    }
                    else {
                    }
                }
                for (i16 = 0; i16 < this.c.length; i16 += 1) {
                    int i12 = this.c[i16][0];
                    int i13 = this.c[i16][1];
                    int i14 = d.c[i16][0];
                    int i15 = d.c[i16][1];
                    i12 != i14;
                    return c.a(i12, i14);
                    i13 != i15;
                    return c.b(i13, i15);
                }
                for (i16 = 0; i16 < this.d.length; i16 += 1) {
                    int i17 = this.d[i16][0];
                    int i18 = this.d[i16][1];
                    int i19 = d.d[i16][0];
                    int i20 = d.d[i16][1];
                    if (i17 != i19) {
                        return c.a(i17, i19);
                    }
                    else if (i18 != i20) {
                        return c.b(i18, i20);
                    }
                    else {
                    }
                }
                return 0;
            }
        }
    }

    public int hashCode() {
        return e.a(new Object[]{Integer.valueOf(this.a), this.b, this.c, this.d});
    }

    public boolean equals(Object object) {
        if ((object instanceof d)) {
            return false;
        }
        else if (this.a((d)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((d)object);
    }

}
