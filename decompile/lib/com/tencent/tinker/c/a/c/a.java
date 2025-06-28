/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/c;

import com.tencent.tinker.a.a.y;
import com.tencent.tinker.a.a.s;
import com.tencent.tinker.a.a.p;
import com.tencent.tinker.a.a.u;
import com.tencent.tinker.a.a.e;
import com.tencent.tinker.a.a.r$a;
import com.tencent.tinker.a.a.r;
import com.tencent.tinker.a.a.g;
import com.tencent.tinker.a.a.f$a;
import com.tencent.tinker.a.a.f$a[];
import com.tencent.tinker.a.a.f$b;
import com.tencent.tinker.a.a.f$b[];
import com.tencent.tinker.a.a.f;
import com.tencent.tinker.a.a.h$a;
import com.tencent.tinker.a.a.h$a[];
import com.tencent.tinker.a.a.h;
import com.tencent.tinker.a.a.h$b;
import com.tencent.tinker.a.a.i;
import com.tencent.tinker.a.a.o;
import com.tencent.tinker.a.a.m;
import com.tencent.tinker.a.a.a;
import com.tencent.tinker.a.a.b;
import com.tencent.tinker.a.a.b.b;
import com.tencent.tinker.a.a.c;
import com.tencent.tinker.a.a.d;
import com.tencent.tinker.a.a.k;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

// class: com/tencent/tinker/c/a/c/a
public abstract class a {

    public a() {
        super();
    }

    int a(int p0);

    int b(int p0);

    int c(int p0);

    int d(int p0);

    int e(int p0);

    int f(int p0);

    int g(int p0);

    int h(int p0);

    int i(int p0);

    int j(int p0);

    int k(int p0);

    int l(int p0);

    int m(int p0);

    int n(int p0);

    int o(int p0);

    int p(int p0);

    public y a(y y) {
        if (y == y.a) {
            return y;
        }
        else {
            short[] shortArr0 = new short[]{};
            for (int i0 = 0; i0 < shortArr0.length; i0 += 1) {
                shortArr0[i0] = (short)this.b(y.b[i0]);
            }
            return new y(y.i, shortArr0);
        }
    }

    public s a(s s) {
        int i0 = this.b(s.a);
        int i1 = this.c(s.b);
        int i2 = this.a(s.c);
        return new s(s.i, i0, i1, i2);
    }

    public p a(p p) {
        int i0 = this.b(p.a);
        int i1 = this.b(p.b);
        int i2 = this.a(p.c);
        return new p(p.i, i0, i1, i2);
    }

    public u a(u u) {
        int i0 = this.a(u.a);
        int i1 = this.b(u.b);
        int i2 = this.h(u.c);
        return new u(u.i, i0, i1, i2);
    }

    public e a(e e) {
        int i0 = this.m(e.a);
        return new e(e.i, i0);
    }

    public r a(r r) {
        int i0 = r.a.a() ? this.e(r.c) : this.d(r.c);
        return new r(r.i, r.a, r.b, i0, r.d);
    }

    public g a(g g) {
        int i0 = this.b(g.a);
        int i1 = this.b(g.c);
        int i2 = this.h(g.d);
        int i3 = this.a(g.e);
        int i4 = this.l(g.f);
        int i5 = this.n(g.g);
        int i6 = this.m(g.h);
        return new g(g.i, i0, g.b, i1, i2, i3, i4, i5, i6);
    }

    public f a(f f) {
        f$a[] f$aArr0 = super.a(f.a);
        f$a[] f$aArr0Var1 = super.a(f.b);
        f$b[] f$bArr0 = super.a(f.c);
        f$b[] f$bArr0Var1 = super.a(f.d);
        return new f(f.i, f$aArr0, f$aArr0Var1, f$bArr0, f$bArr0Var1);
    }

    public h a(h h) {
        int i0 = this.o(h.d);
        short[] shortArr0 = super.a(h.e);
        h$a[] h$aArr0 = super.a(h.g);
        return new h(h.i, h.a, h.b, h.c, i0, shortArr0, h.f, h$aArr0);
    }

    private short[] a(short[] shortArr0) {
        if (shortArr0 == null || shortArr0.length == 0) {
            return shortArr0;
        }
        else {
            b b = new b(this);
            return b.a(shortArr0);
        }
    }

    private h$a[] a(h$a[] h$aArr0) {
        if (h$aArr0 == null || h$aArr0.length == 0) {
            return h$aArr0;
        }
        else {
            h$a h$a = new h$a[]{};
            for (int i0 = 0; i0 < h$aArr0.length; i0 += 1) {
                h$a h$aVar1 = h$aArr0[i0];
                int[] intArr0 = new int[]{};
                for (int i2 = 0; i2 < h$aVar1.a.length; i2 += 1) {
                    intArr0[i2] = this.b(h$aVar1.a[i2]);
                }
                h$a[i0] = new h$a(intArr0, h$aVar1.b, h$aVar1.c, h$aVar1.d);
            }
            return h$a;
        }
    }

    private f$a[] a(f$a[] f$aArr0) {
        f$a f$a = new f$a[]{};
        for (int i0 = 0; i0 < f$aArr0.length; i0 += 1) {
            f$a f$aVar1 = f$aArr0[i0];
            int i1 = this.d(f$aVar1.a);
            f$a[i0] = new f$a(i1, f$aVar1.b);
        }
        return f$a;
    }

    private f$b[] a(f$b[] f$bArr0) {
        f$b f$b = new f$b[]{};
        for (int i0 = 0; i0 < f$bArr0.length; i0 += 1) {
            f$b f$bVar1 = f$bArr0[i0];
            int i1 = this.e(f$bVar1.a);
            int i2 = this.p(f$bVar1.c);
            f$b[i0] = new f$b(i1, f$bVar1.b, i2);
        }
        return f$b;
    }

    public i a(i i) {
        int[] intArr0 = super.a(i.b);
        byte[] byteArr0 = super.a(i.c);
        return new i(i.i, i.a, intArr0, byteArr0);
    }

    private int[] a(int[] intArr0) {
        int[] intArr0Var1 = new int[]{};
        for (int i1 = 0; i1 < intArr0.length; i1 += 1) {
            intArr0Var1[i1] = this.a(intArr0[i1]);
        }
        return intArr0Var1;
    }

    private byte[] a(byte[] byteArr0) {
        ByteArrayInputStream stream = new ByteArrayInputStream(byteArr0);
        a$1 a$1 = new a$1(this, stream);
        stream = new ByteArrayOutputStream(byteArr0.length + 512);
        a$2 a$2 = new a$2(this, stream);
        while (true) {
int i0 = stream.read() & 255;
stream.write(i0);
            switch(i0) {
                case 0: {
                    break;;
                    return stream.toByteArray();
                }
                case 1: {
                    int i1 = q.b(a$1);
                    q.a(a$2, i1);
                    continue;;
                }
                case 2: {
                    int i2 = q.a(a$1);
                    q.c(a$2, i2);
                    continue;;
                }
                case 3: {
                    int i3 = q.b(a$1);
                    q.a(a$2, i3);
                    int i4 = this.a(q.c(a$1));
                    q.b(a$2, i4);
                    int i5 = this.b(q.c(a$1));
                    q.b(a$2, i5);
                    if (i0 == 4) {
                        int i6 = this.a(q.c(a$1));
                        q.b(a$2, i6);
                        continue;;
                    }
                }
                case 5: {
                    int i7 = q.b(a$1);
                    q.a(a$2, i7);
                    continue;;
                }
                case 9: {
                    int i8 = this.a(q.c(a$1));
                    q.b(a$2, i8);
                    continue;;
                }
            }
        }
    }

    public m a(m m) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream(m.a.length);
        a$a.a(new a$a(this, new a$3(this, stream)), new o(m, 28));
        return new m(m.i, stream.toByteArray());
    }

    public a a(a a) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream(a.b.a.length);
        a$a.b(new a$a(this, new a$4(this, stream)), a.a());
        return new a(a.i, a.a, new m(a.b.i, stream.toByteArray()));
    }

    public b a(b b) {
        int[] intArr0 = new int[]{};
        for (int i1 = 0; i1 < b.a.length; i1 += 1) {
            intArr0[i1] = this.i(b.a[i1]);
        }
        return new b(b.i, intArr0);
    }

    public c a(c c) {
        int[] intArr0 = new int[]{};
        for (int i1 = 0; i1 < c.a.length; i1 += 1) {
            intArr0[i1] = this.j(c.a[i1]);
        }
        return new c(c.i, intArr0);
    }

    public d a(d d) {
        int i0 = this.j(d.a);
        v_6 = d.b.length;
        int[][] int[]Arr0 = new int[][][]{};
        for (int i1 = 0; i1 < int[]Arr0.length; i1 += 1) {
            int[]Arr0[i1][0] = this.d(d.b[i1][0]);
            int[]Arr0[i1][1] = this.j(d.b[i1][1]);
        }
        v_39 = d.c.length;
        int[][] int[]Arr0Var1 = new int[][][]{};
        for (int i2 = 0; i2 < int[]Arr0Var1.length; i2 += 1) {
            int[]Arr0Var1[i2][0] = this.e(d.c[i2][0]);
            int[]Arr0Var1[i2][1] = this.j(d.c[i2][1]);
        }
        v_72 = d.d.length;
        int[][] int[]Arr0Var2 = new int[][][]{};
        for (int i3 = 0; i3 < int[]Arr0Var2.length; i3 += 1) {
            int[]Arr0Var2[i3][0] = this.e(d.d[i3][0]);
            int[]Arr0Var2[i3][1] = this.k(d.d[i3][1]);
        }
        return new d(d.i, i0, int[]Arr0, int[]Arr0Var1, int[]Arr0Var2);
    }

    // class: com/tencent/tinker/c/a/c/a$a
    final class a$a {
        final private b b;
        final synthetic a a;

         a$a(a a, b b) {
            this.a = a;
            super();
            this.b = b;
        }

        public void a(o o) {
            switch(o.a()) {
                case 0: {
                    n.a(this.b, 0, (long)o.f());
                    return;
                }
                case 2: {
                    n.a(this.b, 2, (long)o.g());
                    return;
                }
                case 4: {
                    n.a(this.b, 4, (long)o.i());
                    return;
                }
                case 6: {
                    n.a(this.b, 6, o.j());
                    return;
                }
                case 3: {
                    n.b(this.b, 3, (long)o.h());
                    return;
                }
                case 16: {
                    long l0 = (long)Float.floatToIntBits(o.k()) << 32;
                    n.c(this.b, 16, l0);
                    return;
                }
                case 17: {
                    n.c(this.b, 17, Double.doubleToLongBits(o.l()));
                    return;
                }
                case 21: {
                    n.b(this.b, 21, (long)this.a.c(o.m()));
                    return;
                }
                case 22: {
                    n.b(this.b, 22, (long)this.a.g(o.n()));
                    return;
                }
                case 23: {
                    n.b(this.b, 23, (long)this.a.a(o.o()));
                    return;
                }
                case 24: {
                    n.b(this.b, 24, (long)this.a.b(o.p()));
                    return;
                }
                case 25: {
                    n.b(this.b, 25, (long)this.a.d(o.q()));
                    return;
                }
                case 27: {
                    n.b(this.b, 27, (long)this.a.d(o.r()));
                    return;
                }
                case 26: {
                    n.b(this.b, 26, (long)this.a.e(o.s()));
                    return;
                }
                case 28: {
                    super.a(28, 0);
                    this.c(o);
                    return;
                }
                case 29: {
                    super.a(29, 0);
                    this.b(o);
                    return;
                }
                case 30: {
                    o.t();
                    super.a(30, 0);
                    return;
                }
                case 31: {
                    boolean bool0 = o.u();
                    super.a(31, bool0 ? 0 : 1);
                    return;
                }
                default: {
                    throw new k(new StringBuilder().append("Unexpected type: ").append(Integer.toHexString(o.a())).toString());
                }
            }
        }

        private void b(o o) {
            int i0 = o.c();
            q.a(this.b, this.a.b(o.d()));
            q.a(this.b, i0);
            for (int i1 = 0; i1 < i0; i1 += 1) {
                q.a(this.b, this.a.a(o.e()));
                this.a(o);
            }
        }

        private void c(o o) {
            int i0 = o.b();
            q.a(this.b, i0);
            for (int i1 = 0; i1 < i0; i1 += 1) {
                this.a(o);
            }
        }

        private void a(int i0, int i1) {
            this.b.f(i1 << 5 | i0);
        }

        static /* synthetic */ void a(a$a a$a, o o) {
            a$a.c(o);
        }

        static /* synthetic */ void b(a$a a$a, o o) {
            a$a.b(o);
        }

    }
    // class: com/tencent/tinker/c/a/c/a$a
    final class a$a {
        final private b b;
        final synthetic a a;

         a$a(a a, b b) {
            this.a = a;
            super();
            this.b = b;
        }

        public void a(o o) {
            switch(o.a()) {
                case 0: {
                    n.a(this.b, 0, (long)o.f());
                    return;
                }
                case 2: {
                    n.a(this.b, 2, (long)o.g());
                    return;
                }
                case 4: {
                    n.a(this.b, 4, (long)o.i());
                    return;
                }
                case 6: {
                    n.a(this.b, 6, o.j());
                    return;
                }
                case 3: {
                    n.b(this.b, 3, (long)o.h());
                    return;
                }
                case 16: {
                    long l0 = (long)Float.floatToIntBits(o.k()) << 32;
                    n.c(this.b, 16, l0);
                    return;
                }
                case 17: {
                    n.c(this.b, 17, Double.doubleToLongBits(o.l()));
                    return;
                }
                case 21: {
                    n.b(this.b, 21, (long)this.a.c(o.m()));
                    return;
                }
                case 22: {
                    n.b(this.b, 22, (long)this.a.g(o.n()));
                    return;
                }
                case 23: {
                    n.b(this.b, 23, (long)this.a.a(o.o()));
                    return;
                }
                case 24: {
                    n.b(this.b, 24, (long)this.a.b(o.p()));
                    return;
                }
                case 25: {
                    n.b(this.b, 25, (long)this.a.d(o.q()));
                    return;
                }
                case 27: {
                    n.b(this.b, 27, (long)this.a.d(o.r()));
                    return;
                }
                case 26: {
                    n.b(this.b, 26, (long)this.a.e(o.s()));
                    return;
                }
                case 28: {
                    super.a(28, 0);
                    this.c(o);
                    return;
                }
                case 29: {
                    super.a(29, 0);
                    this.b(o);
                    return;
                }
                case 30: {
                    o.t();
                    super.a(30, 0);
                    return;
                }
                case 31: {
                    boolean bool0 = o.u();
                    super.a(31, bool0 ? 0 : 1);
                    return;
                }
                default: {
                    throw new k(new StringBuilder().append("Unexpected type: ").append(Integer.toHexString(o.a())).toString());
                }
            }
        }

        private void b(o o) {
            int i0 = o.c();
            q.a(this.b, this.a.b(o.d()));
            q.a(this.b, i0);
            for (int i1 = 0; i1 < i0; i1 += 1) {
                q.a(this.b, this.a.a(o.e()));
                this.a(o);
            }
        }

        private void c(o o) {
            int i0 = o.b();
            q.a(this.b, i0);
            for (int i1 = 0; i1 < i0; i1 += 1) {
                this.a(o);
            }
        }

        private void a(int i0, int i1) {
            this.b.f(i1 << 5 | i0);
        }

        static /* synthetic */ void a(a$a a$a, o o) {
            a$a.c(o);
        }

        static /* synthetic */ void b(a$a a$a, o o) {
            a$a.b(o);
        }

    }
}
