/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/x
public final class x {
    final public x$a a;
    final public x$a b;
    final public x$a c;
    final public x$a d;
    final public x$a e;
    final public x$a f;
    final public x$a g;
    final public x$a h;
    final public x$a i;
    final public x$a j;
    final public x$a k;
    final public x$a l;
    final public x$a m;
    final public x$a n;
    final public x$a o;
    final public x$a p;
    final public x$a q;
    final public x$a r;
    final public x$a s;
    final public x$a t;
    final public x$a[] u;
    public int v;
    public int w;
    public byte x;
    public int y;
    public int z;
    public int A;
    public int B;
    public int C;

    public x() {
        super();
        this.a = new x$a(false, 1);
        this.b = new x$a(true, 1);
        this.c = new x$a(2, 1);
        this.d = new x$a(3, 1);
        this.e = new x$a(4, 1);
        this.f = new x$a(5, 1);
        this.g = new x$a(6, 1);
        this.h = new x$a(7, 1);
        this.i = new x$a(8, 1);
        this.j = new x$a(4096, 1);
        this.k = new x$a(4097, 1);
        this.l = new x$a(4098, 1);
        this.m = new x$a(4099, 1);
        this.n = new x$a(8192, 0);
        this.o = new x$a(8193, 1);
        this.p = new x$a(8194, 0);
        this.q = new x$a(8195, 0);
        this.r = new x$a(8196, 0);
        this.s = new x$a(8197, 0);
        this.t = new x$a(8198, 1);
        this.u = new x$a[]{this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.j, this.h, this.i, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t};
        this.v = 13;
        this.x = new byte[]{};
    }

    public void a(j j) {
        this.c(j.a(this.a));
        this.d(j.a(this.j.d));
        this.a();
    }

    private void c(j$g j$g) {
        byte[] byteArr0 = j$g.b(8);
        this.v = l.a(byteArr0);
        if (this.v == -1) {
            throw new k(new StringBuilder().append("Unexpected magic: ").append(Arrays.toString(byteArr0)).toString());
        }
        else {
            this.w = j$g.r();
            this.x = j$g.b(20);
            this.y = j$g.r();
            int i0 = j$g.r();
            if (i0 != 112) {
                throw new k(new StringBuilder().append("Unexpected header: 0x").append(Integer.toHexString(i0)).toString());
            }
            else {
                int i1 = j$g.r();
                if (i1 != 305419896) {
                    throw new k(new StringBuilder().append("Unexpected endian tag: 0x").append(Integer.toHexString(i1)).toString());
                }
                else {
                    this.z = j$g.r();
                    this.A = j$g.r();
                    this.j.d = j$g.r();
                    if (this.j.d == 0) {
                        throw new k("Cannot merge dex files that do not contain a map");
                    }
                    else {
                        this.b.c = j$g.r();
                        this.b.d = j$g.r();
                        this.c.c = j$g.r();
                        this.c.d = j$g.r();
                        this.d.c = j$g.r();
                        this.d.d = j$g.r();
                        this.e.c = j$g.r();
                        this.e.d = j$g.r();
                        this.f.c = j$g.r();
                        this.f.d = j$g.r();
                        this.g.c = j$g.r();
                        this.g.d = j$g.r();
                        this.B = j$g.r();
                        this.C = j$g.r();
                    }
                }
            }
        }
    }

    private void d(j$g j$g) {
        int i0 = j$g.r();
        Object object = null;
        for (int i4 = 0; i4 < i0; i4 += 1) {
            short short0 = j$g.p();
            j$g.p();
            x$a x$a = this.a(short0);
            int i2 = j$g.r();
            int i3 = j$g.r();
            if (x$a.c == 0 || x$a.c == i2) {
                if (x$a.d != -1 && x$a.d != i3) {
                    x$a.c = i2;
                    x$a.d = i3;
                    if (object != null && object.d > x$a.d) {
                        throw new k(new StringBuilder().append("Map is unsorted at ").append(object).append(", ").append(x$a).toString());
                    }
                    else {
                        object = x$a;
                    }
                }
            }
            throw new k(new StringBuilder().append("Unexpected map value for 0x").append(Integer.toHexString(short0)).toString());
        }
        this.a.d = 0;
        Arrays.sort(this.u);
        for (i4 = 1; i4 < this.u.length; i4 += 1) {
            this.u[i4].d == -1;
            this.u[i4].d = this.u[i4 - 1].d;
        }
    }

    public void a() {
        int i2 = this.y;
        for (int i1 = this.u.length - 1; i1 >= 0; i1 += 255) {
            x$a x$a = this.u[i1];
            if (x$a.d == -1) {
                continue;;
            }
            else if (x$a.d > i2) {
                throw new k(new StringBuilder().append("Map is unsorted at ").append(x$a).toString());
            }
            else {
                x$a.e = i2 - x$a.d;
                i2 = x$a.d;
            }
        }
        this.C = this.a.e + this.b.e + this.c.e + this.d.e + this.e.e + this.f.e + this.g.e;
        this.B = this.y - this.C;
    }

    private x$a a(short short0) {
        x$a x$a = this.u;
        for (int i1 = 0; i1 < x$a.length; i1 += 1) {
            x$a x$aVar1 = x$a[i1];
            if (x$aVar1.a == short0) {
                return x$aVar1;
            }
            else {
            }
        }
        throw new IllegalArgumentException(new StringBuilder().append("No such map item: ").append(short0).toString());
    }

    public void a(j$g j$g) {
        j$g.a(l.a(this.v).getBytes("UTF-8"));
        j$g.h(this.w);
        j$g.a(this.x);
        j$g.h(this.y);
        j$g.h(112);
        j$g.h(305419896);
        j$g.h(this.z);
        j$g.h(this.A);
        j$g.h(this.j.d);
        j$g.h(this.b.c);
        j$g.h(this.b.a() ? 0 : this.b.d);
        j$g.h(this.c.c);
        j$g.h(this.c.a() ? 0 : this.c.d);
        j$g.h(this.d.c);
        j$g.h(this.d.a() ? 0 : this.d.d);
        j$g.h(this.e.c);
        j$g.h(this.e.a() ? 0 : this.e.d);
        j$g.h(this.f.c);
        j$g.h(this.f.a() ? 0 : this.f.d);
        j$g.h(this.g.c);
        j$g.h(this.g.a() ? 0 : this.g.d);
        j$g.h(this.B);
        j$g.h(this.C);
    }

    public void b(j$g j$g) {
        int i0 = 0;
        x$a x$aVar2 = this.u;
        for (int i4 = 0; i4 < x$aVar2.length; i4 += 1) {
            x$a x$aVar1 = x$aVar2[i4];
            if (x$aVar1.a()) {
                i0 += 1;
            }
        }
        j$g.h(i0);
        x$aVar2 = this.u;
        i3 = x$aVar2.length;
        for (i4 = 0; i4 < x$aVar2.length; i4 += 1) {
            x$a x$aVar3 = x$aVar2[i4];
            if (x$aVar3.a()) {
                j$g.a(x$aVar3.a);
                j$g.a(0);
                j$g.h(x$aVar3.c);
                j$g.h(x$aVar3.d);
            }
        }
    }

    // class: com/tencent/tinker/a/a/x$a
    public class x$a implements Comparable<x$a> {
        final public short a;
        public boolean b;
        public int c;
        public int d;
        public int e;

        public x$a(int i0, boolean bool0) {
            super();
            this.c = 0;
            this.d = -1;
            this.e = 0;
            this.a = (short)i0;
            this.b = bool0;
            if (i0 == 0) {
                this.d = 0;
                this.c = 1;
                this.e = 112;
            }
            else {
                if (i0 == 4096) {
                    this.c = 1;
                }
            }
        }

        public boolean a() {
            if (this.c > 0) {
                return true;
            }
            else {
                return false;
            }
        }

        private int a(int i0) {
            switch(i0) {
                case 0: {
                    return 0;
                }
                case 1: {
                    return 1;
                }
                case 2: {
                    return 2;
                }
                case 3: {
                    return 3;
                }
                case 4: {
                    return 4;
                }
                case 5: {
                    return 5;
                }
                case 8: {
                    return 6;
                }
                case 6: {
                    return 7;
                }
                case 8194: {
                    return 8;
                }
                case 4097: {
                    return 9;
                }
                case 8196: {
                    return 10;
                }
                case 4099: {
                    return 11;
                }
                case 4098: {
                    return 12;
                }
                case 8198: {
                    return 13;
                }
                case 8195: {
                    return 14;
                }
                case 8193: {
                    return 15;
                }
                case 8192: {
                    return 16;
                }
                case 8197: {
                    return 17;
                }
                case 7: {
                    return 18;
                }
                case 4096: {
                    return 19;
                }
                default: {
                    throw new IllegalArgumentException(new StringBuilder().append("unknown section type: ").append(i0).toString());
                }
            }
        }

        public int a(x$a x$a) {
            if (this.d != x$a.d) {
                if (this.d < x$a.d) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
            else {
                int i0 = super.a(this.a);
                int i1 = super.a(x$a.a);
                if (i0 != i1) {
                    if (i0 < i1) {
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
                else {
                    return 0;
                }
            }
        }

        public String toString() {
            return String.format("Section[type=%#x,off=%#x,size=%#x,byteCount=%#x]", new Object[]{Short.valueOf(this.a), Integer.valueOf(this.d), Integer.valueOf(this.c), Integer.valueOf(this.e)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.a((x$a)object);
        }

        // class: com/tencent/tinker/a/a/x$a$a
        public abstract class x$a$a<T> implements Comparable<T> {
            public int i;

            public x$a$a(int i0) {
                super();
                this.i = i0;
            }

            public int hashCode() {
                return super.hashCode();
            }

            public boolean equals(Object object) {
                if (this.compareTo(object) == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

        }
        // class: com/tencent/tinker/a/a/x$a$a
        public abstract class x$a$a<T> implements Comparable<T> {
            public int i;

            public x$a$a(int i0) {
                super();
                this.i = i0;
            }

            public int hashCode() {
                return super.hashCode();
            }

            public boolean equals(Object object) {
                if (this.compareTo(object) == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

        }
    }
    // class: com/tencent/tinker/a/a/x$a
    public class x$a implements Comparable<x$a> {
        final public short a;
        public boolean b;
        public int c;
        public int d;
        public int e;

        public x$a(int i0, boolean bool0) {
            super();
            this.c = 0;
            this.d = -1;
            this.e = 0;
            this.a = (short)i0;
            this.b = bool0;
            if (i0 == 0) {
                this.d = 0;
                this.c = 1;
                this.e = 112;
            }
            else {
                if (i0 == 4096) {
                    this.c = 1;
                }
            }
        }

        public boolean a() {
            if (this.c > 0) {
                return true;
            }
            else {
                return false;
            }
        }

        private int a(int i0) {
            switch(i0) {
                case 0: {
                    return 0;
                }
                case 1: {
                    return 1;
                }
                case 2: {
                    return 2;
                }
                case 3: {
                    return 3;
                }
                case 4: {
                    return 4;
                }
                case 5: {
                    return 5;
                }
                case 8: {
                    return 6;
                }
                case 6: {
                    return 7;
                }
                case 8194: {
                    return 8;
                }
                case 4097: {
                    return 9;
                }
                case 8196: {
                    return 10;
                }
                case 4099: {
                    return 11;
                }
                case 4098: {
                    return 12;
                }
                case 8198: {
                    return 13;
                }
                case 8195: {
                    return 14;
                }
                case 8193: {
                    return 15;
                }
                case 8192: {
                    return 16;
                }
                case 8197: {
                    return 17;
                }
                case 7: {
                    return 18;
                }
                case 4096: {
                    return 19;
                }
                default: {
                    throw new IllegalArgumentException(new StringBuilder().append("unknown section type: ").append(i0).toString());
                }
            }
        }

        public int a(x$a x$a) {
            if (this.d != x$a.d) {
                if (this.d < x$a.d) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
            else {
                int i0 = super.a(this.a);
                int i1 = super.a(x$a.a);
                if (i0 != i1) {
                    if (i0 < i1) {
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
                else {
                    return 0;
                }
            }
        }

        public String toString() {
            return String.format("Section[type=%#x,off=%#x,size=%#x,byteCount=%#x]", new Object[]{Short.valueOf(this.a), Integer.valueOf(this.d), Integer.valueOf(this.c), Integer.valueOf(this.e)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.a((x$a)object);
        }

        // class: com/tencent/tinker/a/a/x$a$a
        public abstract class x$a$a<T> implements Comparable<T> {
            public int i;

            public x$a$a(int i0) {
                super();
                this.i = i0;
            }

            public int hashCode() {
                return super.hashCode();
            }

            public boolean equals(Object object) {
                if (this.compareTo(object) == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

        }
        // class: com/tencent/tinker/a/a/x$a$a
        public abstract class x$a$a<T> implements Comparable<T> {
            public int i;

            public x$a$a(int i0) {
                super();
                this.i = i0;
            }

            public int hashCode() {
                return super.hashCode();
            }

            public boolean equals(Object object) {
                if (this.compareTo(object) == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

        }
    }
}
