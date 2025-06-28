/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a;

import com.tencent.tinker.a.a.j;
import com.tencent.tinker.a.a.x;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

// class: com/tencent/tinker/c/a/a
public class a {
    final private j a;
    final private j b;
    final private a c;
    final private c d;
    private j<w> e;
    private j<Integer> f;
    private j<u> g;
    private j<p> h;
    private j<s> i;
    private j<e> j;
    private j<r> k;
    private j<g> l;
    private j<y> m;
    private j<c> n;
    private j<b> o;
    private j<f> p;
    private j<h> q;
    private j<i> r;
    private j<a> s;
    private j<m> t;
    private j<d> u;

    public a(InputStream stream, InputStream streamVar1) {
        super(new j(stream), new a(streamVar1));
    }

    public a(j j, a a) {
        super();
        this.a = j;
        this.c = a;
        this.b = new j(a.e());
        this.d = new c();
    }

    public void a(OutputStream stream) {
        if (this.c == null) {
            throw new IllegalArgumentException("patch file is null.");
        }
        else {
            if (this.c.a() > 2) {
                int i0 = this.a.a().v;
                int i1 = this.c.c();
                if (i0 != i1) {
                    throw new IOException(new StringBuilder().append("old dex version mismatch! expetced: ").append(i1).append(", actual: ").append(i0).toString());
                }
            }
            byte[] byteArr0 = this.a.a(false);
            if (byteArr0 == null) {
                throw new IOException("failed to compute old dex's signature.");
            }
            else {
                byte[] byteArr0Var1 = this.c.b();
                if (c.a(byteArr0, byteArr0Var1) != 0) {
                    throw new IOException(String.format("old dex signature mismatch! expected: %s, actual: %s", new Object[]{Arrays.toString(byteArr0), Arrays.toString(byteArr0Var1)}));
                }
                else {
                    x x = this.b.a();
                    x.v = this.c.d();
                    x.a.d = 0;
                    x.a.c = 1;
                    x.j.c = 1;
                    x.b.d = this.c.f();
                    x.c.d = this.c.g();
                    x.k.d = this.c.o();
                    x.d.d = this.c.h();
                    x.e.d = this.c.i();
                    x.f.d = this.c.j();
                    x.g.d = this.c.a() > 2 ? this.c.m() : this.c.k();
                    x.j.d = this.c.n();
                    x.p.d = this.c.t();
                    x.r.d = this.c.v();
                    x.m.d = this.c.q();
                    x.l.d = this.c.p();
                    x.t.d = this.c.x();
                    x.s.d = this.c.w();
                    x.q.d = this.c.u();
                    x.o.d = this.c.s();
                    x.n.d = this.c.r();
                    x.y = this.c.e();
                    Arrays.sort(x.u);
                    x.a();
                    this.e = new p(this.c, this.a, this.b, this.d);
                    this.f = new q(this.c, this.a, this.b, this.d);
                    this.g = new n(this.c, this.a, this.b, this.d);
                    this.h = new k(this.c, this.a, this.b, this.d);
                    this.i = new m(this.c, this.a, this.b, this.d);
                    if (this.c.a() > 2) {
                        this.j = new e(this.c, this.a, this.b, this.d);
                        this.k = new l(this.c, this.a, this.b, this.d);
                    }
                    this.l = new g(this.c, this.a, this.b, this.d);
                    this.m = new r(this.c, this.a, this.b, this.d);
                    this.n = new b(this.c, this.a, this.b, this.d);
                    this.o = new c(this.c, this.a, this.b, this.d);
                    this.p = new f(this.c, this.a, this.b, this.d);
                    this.q = new h(this.c, this.a, this.b, this.d);
                    this.r = new i(this.c, this.a, this.b, this.d);
                    this.s = new a(this.c, this.a, this.b, this.d);
                    this.t = new o(this.c, this.a, this.b, this.d);
                    this.u = new d(this.c, this.a, this.b, this.d);
                    this.e.a();
                    this.f.a();
                    this.m.a();
                    this.g.a();
                    this.h.a();
                    this.i.a();
                    if (this.c.a() > 2) {
                        this.k.a();
                    }
                    this.s.a();
                    this.o.a();
                    this.n.a();
                    this.u.a();
                    this.r.a();
                    this.q.a();
                    this.p.a();
                    this.t.a();
                    if (this.c.a() > 2) {
                        this.j.a();
                    }
                    this.l.a();
                    j$g j$g = this.b.a(x.a.d);
                    x.a(j$g);
                    j$g j$gVar1 = this.b.a(x.j.d);
                    x.b(j$gVar1);
                    this.b.c();
                    this.b.a(stream);
                }
            }
        }
    }

    public void a(File file) {
        Object object = null;
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
            this.a(stream);
            return;
        }
        finally {
            Throwable throwable = v_7;
            b.a(stream);
            throw throwable;
        }
    }

}
