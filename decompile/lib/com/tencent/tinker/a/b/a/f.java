/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/b/a;

import com.tencent.tinker.a.a.k;

// class: com/tencent/tinker/a/b/a/f
public final class f {
    final private i t;
    final private c u;
    final private boolean v;
    int a;
    int b;
    int c;
    long d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    Object r;
    int s;

    public f(i i, c c) {
        super(null);
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0L;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = null;
        this.o = null;
        this.p = 0;
        this.q = 0;
        this.r = null;
        this.s = 0;
        this.t = i;
        this.u = c;
        this.v = c != null ? 0 : true;
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1) {
        if (this.v) {
            i4 = this.u.a(i4);
            switch(i1) {
                case 40: {
                    int i5 = b.e(i4, this.t.a());
                    if (i5 != (byte)i5) {
                        i1 = i5 == (short)i5 ? 42 : 41;
                        break;;
                    }
                }
                case 41: {
                    int i6 = b.e(i4, this.t.a());
                    if (i6 != (short)i6) {
                        i1 = 42;
                        break;;
                    }
                }
            }
        }
        this.a = i1;
        this.b = i2;
        this.c = i4;
        this.d = l1;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        b.a(this.t, this);
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1) {
        if (this.v) {
            i4 = this.u.a(i4);
        }
        if (i1 == 26) {
            if (this.v) {
                if (i2 > 65535) {
                    i1 = 27;
                }
            }
            else if (i2 > 65535) {
                throw new k(new StringBuilder().append("string index out of bound: ").append(a.a(i2)).append(", perhaps you need to enable force jumbo mode.").toString());
            }
        }
        this.a = i1;
        this.b = i2;
        this.c = i4;
        this.d = l1;
        this.e = 1;
        this.f = i5;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        b.a(this.t, this);
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5) {
        if (this.v) {
            i4 = this.u.a(i4);
        }
        this.a = i1;
        this.b = i2;
        this.c = i4;
        this.d = l1;
        this.e = 2;
        this.f = i5;
        this.g = i6;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        b.a(this.t, this);
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6) {
        this.a = i1;
        this.b = i2;
        this.c = i4;
        this.d = l1;
        this.e = 3;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        b.a(this.t, this);
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6, int i7) {
        this.a = i1;
        this.b = i2;
        this.c = i4;
        this.d = l1;
        this.e = 4;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        b.a(this.t, this);
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6, int i7, int i8) {
        this.a = i1;
        this.b = i2;
        this.c = i4;
        this.d = l1;
        this.e = 5;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
        this.j = i9;
        this.k = 0;
        this.l = 0;
        b.a(this.t, this);
    }

    public void b(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5) {
        this.a = i1;
        this.b = i2;
        this.c = i4;
        this.d = l1;
        this.e = i6;
        this.f = i5;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        b.a(this.t, this);
    }

    public void a(int i0, int i1, int i2, int i3, int i4, int[] intArr0) {
        this.a = i1;
        this.b = i2;
        this.m = i4;
        this.e = intArr0.length;
        this.f = 0;
        this.g = 0;
        this.h = intArr0.length > 0 ? 0 : intArr0[0];
        this.i = intArr0.length > 1 ? 0 : intArr0[1];
        this.j = intArr0.length > 2 ? 0 : intArr0[2];
        this.k = intArr0.length > 3 ? 0 : intArr0[3];
        this.l = intArr0.length > 4 ? 0 : intArr0[4];
        b.a(this.t, this);
    }

    public void a(int i0, int i1, int i2, int i3, int i4, int i5, int i6) {
        this.a = i1;
        this.b = i2;
        this.e = i5;
        this.f = 0;
        this.g = 0;
        this.h = i4;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = i6;
        b.a(this.t, this);
    }

    public void a(int i0, int i1, int[] intArr0, int[] intArr0Var1) {
        this.a = i1;
        this.n = intArr0;
        while (true) {
        }
        this.o = this.v ? intArr0Var1 : new int[]{};
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        b.a(this.t, this);
    }

    public void a(int i0, int i1, int i2, int[] intArr0) {
        this.a = i1;
        this.p = i2;
        while (true) {
        }
        this.o = this.v ? intArr0 : new int[]{};
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        b.a(this.t, this);
    }

    public void a(int i0, int i1, Object object, int i2, int i3) {
        this.a = i1;
        this.r = object;
        this.s = i2;
        this.q = i3;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        b.a(this.t, this);
    }

}
