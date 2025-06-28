/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/b;

import com.tencent.tinker.a.a.a.a;
import java.nio.ByteBuffer;

// class: com/tencent/tinker/c/a/b/a
public final class a {
    final public static byte a;
    final private a b;
    private short c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;
    private byte A;

    public a(InputStream stream) {
        super();
        this.b = new a(ByteBuffer.wrap(d.a(stream)));
        this.z();
    }

    private void z() {
        byte[] byteArr0 = this.b.b(a.a.length);
        if (c.a(byteArr0, a.a) != 0) {
            throw new IllegalStateException(new StringBuilder().append("bad dex patch file magic: ").append(Arrays.toString(byteArr0)).toString());
        }
        else {
            this.c = this.b.p();
            if (this.c != 2 && this.c != 3) {
                throw new IllegalStateException(new StringBuilder().append("bad dex patch file version: ").append(this.c).toString());
            }
            else {
                if (this.c > 2) {
                    this.d = this.b.r();
                    this.e = this.b.r();
                }
                this.f = this.b.r();
                this.g = this.b.r();
                this.h = this.b.r();
                this.i = this.b.r();
                this.j = this.b.r();
                this.k = this.b.r();
                this.l = this.b.r();
                if (this.c > 2) {
                    this.m = this.b.r();
                    this.n = this.b.r();
                }
                this.o = this.b.r();
                this.p = this.b.r();
                this.q = this.b.r();
                this.r = this.b.r();
                this.s = this.b.r();
                this.t = this.b.r();
                this.u = this.b.r();
                this.v = this.b.r();
                this.w = this.b.r();
                this.x = this.b.r();
                this.y = this.b.r();
                this.z = this.b.r();
                this.A = this.b.b(20);
                this.b.a(this.g);
            }
        }
    }

    public short a() {
        return this.c;
    }

    public byte[] b() {
        return this.A;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public int e() {
        return this.f;
    }

    public int f() {
        return this.h;
    }

    public int g() {
        return this.i;
    }

    public int h() {
        return this.j;
    }

    public int i() {
        return this.k;
    }

    public int j() {
        return this.l;
    }

    public int k() {
        return this.m;
    }

    public int l() {
        return this.n;
    }

    public int m() {
        return this.o;
    }

    public int n() {
        return this.p;
    }

    public int o() {
        return this.q;
    }

    public int p() {
        return this.r;
    }

    public int q() {
        return this.s;
    }

    public int r() {
        return this.t;
    }

    public int s() {
        return this.u;
    }

    public int t() {
        return this.v;
    }

    public int u() {
        return this.w;
    }

    public int v() {
        return this.x;
    }

    public int w() {
        return this.y;
    }

    public int x() {
        return this.z;
    }

    public a y() {
        return this.b;
    }

    static  {
        a.a = new byte[]{68, 88, 68, 73, 70, 70};
    }

}
