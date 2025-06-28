/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/o
public final class o {
    final protected a a;
    private int b;
    private int c;
    private int d;

    public o(a a, int i0) {
        super();
        this.b = -1;
        this.a = a;
        this.b = i0;
    }

    public o(m m, int i0) {
        super(m.a(), i0);
    }

    public int a() {
        if (this.b == -1) {
            int i0 = this.a.a() & 255;
            this.b = i0 & 31;
            this.d = i0 & 224 >> 5;
        }
        return this.b;
    }

    public int b() {
        this.a(28);
        this.b = -1;
        return q.b(this.a);
    }

    public int c() {
        this.a(29);
        this.b = -1;
        this.c = q.b(this.a);
        return q.b(this.a);
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return q.b(this.a);
    }

    public byte f() {
        this.a(0);
        this.b = -1;
        return (byte)n.a(this.a, this.d);
    }

    public short g() {
        this.a(2);
        this.b = -1;
        return (short)n.a(this.a, this.d);
    }

    public char h() {
        this.a(3);
        this.b = -1;
        return (char)n.a(this.a, this.d, 0);
    }

    public int i() {
        this.a(4);
        this.b = -1;
        return n.a(this.a, this.d);
    }

    public long j() {
        this.a(6);
        this.b = -1;
        return n.b(this.a, this.d);
    }

    public float k() {
        this.a(16);
        this.b = -1;
        return Float.intBitsToFloat(n.a(this.a, this.d, 1));
    }

    public double l() {
        this.a(17);
        this.b = -1;
        return Double.longBitsToDouble(n.b(this.a, this.d, 1));
    }

    public int m() {
        this.a(21);
        this.b = -1;
        return n.a(this.a, this.d, 0);
    }

    public int n() {
        this.a(22);
        this.b = -1;
        return n.a(this.a, this.d, 0);
    }

    public int o() {
        this.a(23);
        this.b = -1;
        return n.a(this.a, this.d, 0);
    }

    public int p() {
        this.a(24);
        this.b = -1;
        return n.a(this.a, this.d, 0);
    }

    public int q() {
        this.a(25);
        this.b = -1;
        return n.a(this.a, this.d, 0);
    }

    public int r() {
        this.a(27);
        this.b = -1;
        return n.a(this.a, this.d, 0);
    }

    public int s() {
        this.a(26);
        this.b = -1;
        return n.a(this.a, this.d, 0);
    }

    public void t() {
        this.a(30);
        this.b = -1;
    }

    public boolean u() {
        this.a(31);
        this.b = -1;
        if (this.d != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void v() {
        switch(this.a()) {
            case 0: {
                this.f();
                return;
            }
            case 2: {
                this.g();
                return;
            }
            case 3: {
                this.h();
                return;
            }
            case 4: {
                this.i();
                return;
            }
            case 6: {
                this.j();
                return;
            }
            case 16: {
                this.k();
                return;
            }
            case 17: {
                this.l();
                return;
            }
            case 21: {
                this.m();
                return;
            }
            case 22: {
                this.n();
                return;
            }
            case 23: {
                this.o();
                return;
            }
            case 24: {
                this.p();
                return;
            }
            case 25: {
                this.q();
                return;
            }
            case 27: {
                this.r();
                return;
            }
            case 26: {
                this.s();
                return;
            }
            case 28: {
                int i0 = 0;
                for (int i1 = this.b(); i0 < i1; i0 += 1) {
                    this.v();
                }
                return;
            }
            case 29: {
                int i2 = 0;
                for (int i3 = this.c(); i2 < i3; i2 += 1) {
                    this.e();
                    this.v();
                }
                return;
            }
            case 30: {
                this.t();
                return;
            }
            case 31: {
                this.u();
                return;
            }
            default: {
                throw new k(new StringBuilder().append("Unexpected type: ").append(Integer.toHexString(this.b)).toString());
            }
        }
    }

    private void a(int i0) {
        if (this.a() != i0) {
            throw new IllegalStateException(String.format("Expected %x but was %x", new Object[]{Integer.valueOf(i0), Integer.valueOf(this.a())}));
        }
        else {
        }
    }

}
