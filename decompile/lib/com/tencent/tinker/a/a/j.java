/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.Buffer;
import java.security.MessageDigest;
import java.util.zip.Adler32;

// class: com/tencent/tinker/a/a/j
public final class j {
    final static short a;
    final private x b;
    final private j$h c;
    final private j$i d;
    final private j$j e;
    final private j$f f;
    final private j$c g;
    final private j$e h;
    final private j$a i;
    final private j$d j;
    final private j$b k;
    private ByteBuffer l;
    private int m;
    private byte n;

    public j(int i0) {
        super();
        this.b = new x();
        this.c = new j$h(this, null);
        this.d = new j$i(this, null);
        this.e = new j$j(this, null);
        this.f = new j$f(this, null);
        this.g = new j$c(this, null);
        this.h = new j$e(this, null);
        this.i = new j$a(this, null);
        this.j = new j$d(this, null);
        this.k = new j$b(this, null);
        this.m = 0;
        this.n = null;
        this.l = ByteBuffer.wrap(new byte[]{});
        this.l.order(ByteOrder.LITTLE_ENDIAN);
        this.b.y = i0;
    }

    public j(InputStream stream) {
        super();
        this.b = new x();
        this.c = new j$h(this, null);
        this.d = new j$i(this, null);
        this.e = new j$j(this, null);
        this.f = new j$f(this, null);
        this.g = new j$c(this, null);
        this.h = new j$e(this, null);
        this.i = new j$a(this, null);
        this.j = new j$d(this, null);
        this.k = new j$b(this, null);
        this.m = 0;
        this.n = null;
        this.a(stream);
    }

    private static void b(int i0, int i1) {
        if (i0 < 0 || i0 >= i1) {
            throw new IndexOutOfBoundsException(new StringBuilder().append("index:").append(i0).append(", length=").append(i1).toString());
        }
        else {
        }
    }

    private void a(InputStream stream) {
        super.a(stream, 0);
    }

    private void a(InputStream stream, int i0) {
        byte[] byteArr0 = d.a(stream, i0);
        this.l = ByteBuffer.wrap(byteArr0);
        this.l.order(ByteOrder.LITTLE_ENDIAN);
        this.b.a(this);
    }

    public void a(OutputStream stream) {
        byte[] byteArr0 = this.l.array();
        stream.write(byteArr0);
        stream.flush();
    }

    public x a() {
        return this.b;
    }

    public j$g a(int i0) {
        if (i0 < 0 || i0 >= this.l.capacity()) {
            throw new IllegalArgumentException(new StringBuilder().append("position=").append(i0).append(" length=").append(this.l.capacity()).toString());
        }
        else {
            ByteBuffer buffer = this.l.duplicate();
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            buffer.position(i0);
            buffer.limit(this.l.capacity());
            return new j$g(this, "temp-section", buffer, null);
        }
    }

    public j$g a(x$a x$a) {
        int i0 = x$a.d;
        if (i0 < 0 || i0 >= this.l.capacity()) {
            throw new IllegalArgumentException(new StringBuilder().append("position=").append(i0).append(" length=").append(this.l.capacity()).toString());
        }
        else {
            ByteBuffer buffer = this.l.duplicate();
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            buffer.position(i0);
            buffer.limit(i0 + x$a.e);
            return new j$g(this, "section", buffer, null);
        }
    }

    public byte[] a(boolean bool0) {
        if (this.n != null && bool0) {
            return this.n;
        }
        else {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
            }
            catch (NoSuchAlgorithmException var_3_1) {
                throw new AssertionError();
            }
            byte[] byteArr0 = new byte[]{};
            ByteBuffer buffer = this.l.duplicate();
            buffer.limit(buffer.capacity());
            buffer.position(32);
            while (buffer.hasRemaining()) {
                int i0 = Math.min(byteArr0.length, buffer.remaining());
                buffer.get(byteArr0, 0, i0);
                digest.update(byteArr0, 0, i0);
            }
            this.n = digest.digest();
            return digest.digest();
        }
    }

    public int b() {
        Adler32 adler32 = new Adler32();
        byte[] byteArr0 = new byte[]{};
        ByteBuffer buffer = this.l.duplicate();
        buffer.limit(buffer.capacity());
        buffer.position(12);
        while (buffer.hasRemaining()) {
            int i0 = Math.min(byteArr0.length, buffer.remaining());
            buffer.get(byteArr0, 0, i0);
            adler32.update(byteArr0, 0, i0);
        }
        return (int)adler32.getValue();
    }

    public void c() {
        this.a(12).a(this.a(true));
        this.a(8).h(this.b());
    }

    public int b(int i0) {
        j.b(i0, this.b.c.c);
        int i1 = this.b.c.d + 4 * i0;
        return this.l.getInt(i1);
    }

    static /* synthetic */ x a(j j) {
        return j.b;
    }

    static /* synthetic */ void a(int i0, int i1) {
        j.b(i0, i1);
    }

    static /* synthetic */ j$h b(j j) {
        return j.c;
    }

    static  {
        j.a = new short[]{};
    }

    // class: com/tencent/tinker/a/a/j$e
    final class j$e implements RandomAccess {
        final synthetic j a;

        private j$e(j j) {
            this.a = j;
            super();
        }

        public s a(int i0) {
            j.a(i0, j.a(this.a).f.c);
            return this.a.a(j.a(this.a).f.d + 8 * i0).d();
        }

        public int size() {
            return j.a(this.a).f.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$e(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$e
    final class j$e implements RandomAccess {
        final synthetic j a;

        private j$e(j j) {
            this.a = j;
            super();
        }

        public s a(int i0) {
            j.a(i0, j.a(this.a).f.c);
            return this.a.a(j.a(this.a).f.d + 8 * i0).d();
        }

        public int size() {
            return j.a(this.a).f.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$e(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$j
    final class j$j implements RandomAccess {
        final synthetic j a;

        private j$j(j j) {
            this.a = j;
            super();
        }

        public String a(int i0) {
            return j.b(this.a).a(this.a.b(i0));
        }

        public int size() {
            return j.a(this.a).c.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$j(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$j
    final class j$j implements RandomAccess {
        final synthetic j a;

        private j$j(j j) {
            this.a = j;
            super();
        }

        public String a(int i0) {
            return j.b(this.a).a(this.a.b(i0));
        }

        public int size() {
            return j.a(this.a).c.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$j(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$g
    public final class j$g {
        final private String b;
        final synthetic j a;

        private j$g(j j, String str0, ByteBuffer buffer) {
            this.a = j;
            super(buffer);
            this.b = str0;
        }

        public w a_() {
            this.a(j.a(this.a).p, 0);
            return super.a_();
        }

        public y b() {
            this.a(j.a(this.a).k, 0);
            return super.b();
        }

        public p c() {
            this.a(j.a(this.a).e, 0);
            return super.c();
        }

        public s d() {
            this.a(j.a(this.a).f, 0);
            return super.d();
        }

        public u e() {
            this.a(j.a(this.a).d, 0);
            return super.e();
        }

        public g f() {
            this.a(j.a(this.a).g, 0);
            return super.f();
        }

        public h g() {
            this.a(j.a(this.a).o, 0);
            return super.g();
        }

        public i h() {
            this.a(j.a(this.a).q, 0);
            return super.h();
        }

        public f i() {
            this.a(j.a(this.a).n, 0);
            return super.i();
        }

        public a j() {
            this.a(j.a(this.a).r, 0);
            return super.j();
        }

        public b k() {
            this.a(j.a(this.a).m, 0);
            return super.k();
        }

        public c l() {
            this.a(j.a(this.a).l, 0);
            return super.l();
        }

        public d m() {
            this.a(j.a(this.a).t, 0);
            return super.m();
        }

        public m n() {
            this.a(j.a(this.a).s, 0);
            return super.n();
        }

        private void a(x$a x$a, boolean bool0) {
            if (x$a.b) {
                if (bool0) {
                    this.y();
                }
                else {
                    this.x();
                }
            }
        }

        public int a(w w) {
            super.a(j.a(this.a).p, 1);
            return super.a(w);
        }

        public int a(y y) {
            super.a(j.a(this.a).k, 1);
            return super.a(y);
        }

        public int a(p p) {
            super.a(j.a(this.a).e, 1);
            return super.a(p);
        }

        public int a(s s) {
            super.a(j.a(this.a).f, 1);
            return super.a(s);
        }

        public int a(u u) {
            super.a(j.a(this.a).d, 1);
            return super.a(u);
        }

        public int a(g g) {
            super.a(j.a(this.a).g, 1);
            return super.a(g);
        }

        public int a(h h) {
            super.a(j.a(this.a).o, 1);
            return super.a(h);
        }

        public int a(i i) {
            super.a(j.a(this.a).q, 1);
            return super.a(i);
        }

        public int a(f f) {
            super.a(j.a(this.a).n, 1);
            return super.a(f);
        }

        public int a(a a) {
            super.a(j.a(this.a).r, 1);
            return super.a(a);
        }

        public int a(b b) {
            super.a(j.a(this.a).m, 1);
            return super.a(b);
        }

        public int a(c c) {
            super.a(j.a(this.a).l, 1);
            return super.a(c);
        }

        public int a(d d) {
            super.a(j.a(this.a).t, 1);
            return super.a(d);
        }

        public int a(m m) {
            super.a(j.a(this.a).s, 1);
            return super.a(m);
        }

        /* synthetic */ j$g(j j, String str0, ByteBuffer buffer, j$1 j$1) {
            super(j, str0, buffer);
        }

    }
    // class: com/tencent/tinker/a/a/j$g
    public final class j$g {
        final private String b;
        final synthetic j a;

        private j$g(j j, String str0, ByteBuffer buffer) {
            this.a = j;
            super(buffer);
            this.b = str0;
        }

        public w a_() {
            this.a(j.a(this.a).p, 0);
            return super.a_();
        }

        public y b() {
            this.a(j.a(this.a).k, 0);
            return super.b();
        }

        public p c() {
            this.a(j.a(this.a).e, 0);
            return super.c();
        }

        public s d() {
            this.a(j.a(this.a).f, 0);
            return super.d();
        }

        public u e() {
            this.a(j.a(this.a).d, 0);
            return super.e();
        }

        public g f() {
            this.a(j.a(this.a).g, 0);
            return super.f();
        }

        public h g() {
            this.a(j.a(this.a).o, 0);
            return super.g();
        }

        public i h() {
            this.a(j.a(this.a).q, 0);
            return super.h();
        }

        public f i() {
            this.a(j.a(this.a).n, 0);
            return super.i();
        }

        public a j() {
            this.a(j.a(this.a).r, 0);
            return super.j();
        }

        public b k() {
            this.a(j.a(this.a).m, 0);
            return super.k();
        }

        public c l() {
            this.a(j.a(this.a).l, 0);
            return super.l();
        }

        public d m() {
            this.a(j.a(this.a).t, 0);
            return super.m();
        }

        public m n() {
            this.a(j.a(this.a).s, 0);
            return super.n();
        }

        private void a(x$a x$a, boolean bool0) {
            if (x$a.b) {
                if (bool0) {
                    this.y();
                }
                else {
                    this.x();
                }
            }
        }

        public int a(w w) {
            super.a(j.a(this.a).p, 1);
            return super.a(w);
        }

        public int a(y y) {
            super.a(j.a(this.a).k, 1);
            return super.a(y);
        }

        public int a(p p) {
            super.a(j.a(this.a).e, 1);
            return super.a(p);
        }

        public int a(s s) {
            super.a(j.a(this.a).f, 1);
            return super.a(s);
        }

        public int a(u u) {
            super.a(j.a(this.a).d, 1);
            return super.a(u);
        }

        public int a(g g) {
            super.a(j.a(this.a).g, 1);
            return super.a(g);
        }

        public int a(h h) {
            super.a(j.a(this.a).o, 1);
            return super.a(h);
        }

        public int a(i i) {
            super.a(j.a(this.a).q, 1);
            return super.a(i);
        }

        public int a(f f) {
            super.a(j.a(this.a).n, 1);
            return super.a(f);
        }

        public int a(a a) {
            super.a(j.a(this.a).r, 1);
            return super.a(a);
        }

        public int a(b b) {
            super.a(j.a(this.a).m, 1);
            return super.a(b);
        }

        public int a(c c) {
            super.a(j.a(this.a).l, 1);
            return super.a(c);
        }

        public int a(d d) {
            super.a(j.a(this.a).t, 1);
            return super.a(d);
        }

        public int a(m m) {
            super.a(j.a(this.a).s, 1);
            return super.a(m);
        }

        /* synthetic */ j$g(j j, String str0, ByteBuffer buffer, j$1 j$1) {
            super(j, str0, buffer);
        }

    }
    // class: com/tencent/tinker/a/a/j$b
    final class j$b implements RandomAccess {
        final synthetic j a;

        private j$b(j j) {
            this.a = j;
            super();
        }

        public g a(int i0) {
            j.a(i0, j.a(this.a).g.c);
            return this.a.a(j.a(this.a).g.d + 32 * i0).f();
        }

        public int size() {
            return j.a(this.a).g.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$b(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$b
    final class j$b implements RandomAccess {
        final synthetic j a;

        private j$b(j j) {
            this.a = j;
            super();
        }

        public g a(int i0) {
            j.a(i0, j.a(this.a).g.c);
            return this.a.a(j.a(this.a).g.d + 32 * i0).f();
        }

        public int size() {
            return j.a(this.a).g.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$b(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$d
    final class j$d implements RandomAccess {
        final synthetic j a;

        private j$d(j j) {
            this.a = j;
            super();
        }

        public r a(int i0) {
            j.a(i0, j.a(this.a).i.c);
            return this.a.a(j.a(this.a).i.d + 8 * i0).w();
        }

        public int size() {
            return j.a(this.a).i.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$d(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$d
    final class j$d implements RandomAccess {
        final synthetic j a;

        private j$d(j j) {
            this.a = j;
            super();
        }

        public r a(int i0) {
            j.a(i0, j.a(this.a).i.c);
            return this.a.a(j.a(this.a).i.d + 8 * i0).w();
        }

        public int size() {
            return j.a(this.a).i.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$d(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$c
    final class j$c implements RandomAccess {
        final synthetic j a;

        private j$c(j j) {
            this.a = j;
            super();
        }

        public p a(int i0) {
            j.a(i0, j.a(this.a).e.c);
            return this.a.a(j.a(this.a).e.d + 8 * i0).c();
        }

        public int size() {
            return j.a(this.a).e.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$c(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$c
    final class j$c implements RandomAccess {
        final synthetic j a;

        private j$c(j j) {
            this.a = j;
            super();
        }

        public p a(int i0) {
            j.a(i0, j.a(this.a).e.c);
            return this.a.a(j.a(this.a).e.d + 8 * i0).c();
        }

        public int size() {
            return j.a(this.a).e.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$c(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$f
    final class j$f implements RandomAccess {
        final synthetic j a;

        private j$f(j j) {
            this.a = j;
            super();
        }

        public u a(int i0) {
            j.a(i0, j.a(this.a).d.c);
            return this.a.a(j.a(this.a).d.d + 12 * i0).e();
        }

        public int size() {
            return j.a(this.a).d.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$f(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$f
    final class j$f implements RandomAccess {
        final synthetic j a;

        private j$f(j j) {
            this.a = j;
            super();
        }

        public u a(int i0) {
            j.a(i0, j.a(this.a).d.c);
            return this.a.a(j.a(this.a).d.d + 12 * i0).e();
        }

        public int size() {
            return j.a(this.a).d.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$f(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$a
    final class j$a implements RandomAccess {
        final synthetic j a;

        private j$a(j j) {
            this.a = j;
            super();
        }

        public e a(int i0) {
            j.a(i0, j.a(this.a).h.c);
            return this.a.a(j.a(this.a).h.d + 4 * i0).v();
        }

        public int size() {
            return j.a(this.a).h.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$a(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$a
    final class j$a implements RandomAccess {
        final synthetic j a;

        private j$a(j j) {
            this.a = j;
            super();
        }

        public e a(int i0) {
            j.a(i0, j.a(this.a).h.c);
            return this.a.a(j.a(this.a).h.d + 4 * i0).v();
        }

        public int size() {
            return j.a(this.a).h.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$a(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$h
    final class j$h implements RandomAccess {
        final synthetic j a;

        private j$h(j j) {
            this.a = j;
            super();
        }

        public String a(int i0) {
            j.a(i0, j.a(this.a).b.c);
            int i1 = this.a.a(j.a(this.a).b.d + i0 * 4).r();
            return this.a.a(i1).a_().a;
        }

        public int size() {
            return j.a(this.a).b.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$h(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$h
    final class j$h implements RandomAccess {
        final synthetic j a;

        private j$h(j j) {
            this.a = j;
            super();
        }

        public String a(int i0) {
            j.a(i0, j.a(this.a).b.c);
            int i1 = this.a.a(j.a(this.a).b.d + i0 * 4).r();
            return this.a.a(i1).a_().a;
        }

        public int size() {
            return j.a(this.a).b.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$h(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$i
    final class j$i implements RandomAccess {
        final synthetic j a;

        private j$i(j j) {
            this.a = j;
            super();
        }

        public Integer a(int i0) {
            return Integer.valueOf(this.a.b(i0));
        }

        public int size() {
            return j.a(this.a).c.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$i(j j, j$1 j$1) {
            super(j);
        }

    }
    // class: com/tencent/tinker/a/a/j$i
    final class j$i implements RandomAccess {
        final synthetic j a;

        private j$i(j j) {
            this.a = j;
            super();
        }

        public Integer a(int i0) {
            return Integer.valueOf(this.a.b(i0));
        }

        public int size() {
            return j.a(this.a).c.c;
        }

        public /* synthetic */ Object get(int i0) {
            return this.a(i0);
        }

        /* synthetic */ j$i(j j, j$1 j$1) {
            super(j);
        }

    }
}
