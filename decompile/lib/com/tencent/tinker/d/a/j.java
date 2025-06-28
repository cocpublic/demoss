/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/d/a;

import java.util.HashSet;
import java.util.zip.ZipException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

// class: com/tencent/tinker/d/a/j
public class j {
    final public static byte a;
    final private static byte b;
    final private HashSet<String> c;
    final private boolean d;
    private byte e;
    private int f;
    private ByteArrayOutputStream g;
    private h h;
    private long i;
    private byte j;
    private byte k;
    private boolean l;
    final private int m;
    private int n;

    public j(OutputStream stream) {
        super(stream, 0);
    }

    public j(OutputStream stream, boolean bool0) {
        super(stream, bool0, 4);
    }

    public j(OutputStream stream, boolean bool0, int i0) {
        super(stream);
        this.c = new HashSet();
        this.e = j.a;
        this.f = 8;
        this.g = new ByteArrayOutputStream();
        this.i = 0L;
        this.n = 0;
        this.d = bool0;
        this.m = i0;
    }

    static long a(OutputStream stream, long l1) {
        stream.write((int)l1 & 255L);
        stream.write((int)l1 >> 8 & 255);
        stream.write((int)l1 >> 16 & 255);
        stream.write((int)l1 >> 24 & 255);
        return l1;
    }

    static int a(OutputStream stream, int i0) {
        stream.write(i0 & 255);
        stream.write(i0 >> 8 & 255);
        return i0;
    }

    public void close() {
        if (this.out != null) {
            this.b();
            this.out.close();
            this.out = null;
        }
    }

    public void a() {
        this.c();
        if (this.h == null) {
        }
        else {
            long l1 = 30L;
            if (this.h.d() != 0) {
                l1 += 16L;
                j.a(this.out, 134695760L);
                j.a(this.out, this.h.c);
                j.a(this.out, this.h.d);
                j.a(this.out, this.h.e);
            }
            int i0 = this.h.d() == 0 ? 8 : 0;
            i0 |= 2048;
            j.a(this.g, 33639248L);
            j.a(this.g, 20);
            j.a(this.g, 20);
            j.a(this.g, i0);
            j.a(this.g, this.h.d());
            j.a(this.g, this.h.g);
            j.a(this.g, this.h.h);
            j.a(this.g, this.h.c);
            l1 = this.h.d() == 8 ? l1 + this.h.f() : l1 + this.h.a();
            j.a(this.g, this.h.a());
            j.a(this.g, this.h.f());
            l1 += (long)j.a(this.g, this.j.length);
            if (this.h.i != null) {
                l1 += (long)j.a(this.g, this.h.i.length);
                goto 333;
            }
            else {
                j.a(this.g, 0);
            }
            j.a(this.g, this.k.length);
            j.a(this.g, 0);
            j.a(this.g, 0);
            j.a(this.g, 0L);
            j.a(this.g, this.h.j);
            this.g.write(this.j);
            this.j = null;
            if (this.h.i != null) {
                this.g.write(this.h.i);
            }
            this.i = this.i + l1 + (long)this.n;
            this.n = 0;
            if (this.k.length > 0) {
                this.g.write(this.k);
                this.k = j.a;
            }
            this.h = null;
        }
    }

    public void b() {
        if (this.out == null) {
            throw new IOException("Stream is closed");
        }
        else if (this.g == null) {
        }
        else if (this.c.isEmpty()) {
            throw new ZipException("No entries");
        }
        else {
            if (this.h != null) {
                this.a();
            }
            int i0 = this.g.size();
            j.a(this.g, 101010256L);
            j.a(this.g, 0);
            j.a(this.g, 0);
            if (this.l) {
                j.a(this.g, 65535);
                j.a(this.g, 65535);
                j.a(this.g, -1L);
                j.a(this.g, -1L);
                goto 203;
            }
            else {
                j.a(this.g, this.c.size());
                j.a(this.g, this.c.size());
                j.a(this.g, (long)i0);
                j.a(this.g, this.i + (long)this.n);
            }
            j.a(this.g, this.e.length);
            if (this.e.length > 0) {
                this.g.write(this.e);
            }
            this.g.writeTo(this.out);
            this.g = null;
        }
    }

    private int a(h h, long l1) {
        if (h.d() != 0 || this.m == 0) {
            return 0;
        }
        else {
            return (int)(long)this.m - l1 % (long)this.m % (long)this.m;
        }
    }

    private void b(OutputStream stream, long l1) {
        if (0L <= l1) {
        }
        else {
            while (true) {
                l1 -= 1L;
                if (0L > l1) {
                    stream.write(0);
                }
                else {
                }
            }
        }
    }

    public void a(h h) {
        if (this.h != null) {
            this.a();
        }
        int i1 = h.d();
        if (i1 == -1) {
            i1 = this.f;
        }
        if (i1 == 0) {
            if (-1L == h.a()) {
                h.a(h.f());
                goto 71;
            }
            else if (-1L == h.f()) {
                h.c(h.a());
            }
            if (-1L == h.b()) {
                throw new ZipException("STORED entry missing CRC");
            }
            else if (-1L == h.f()) {
                throw new ZipException("STORED entry missing size");
            }
            else if (h.d != h.e) {
                throw new ZipException("STORED entry size/compressed size mismatch");
            }
        }
        this.c();
        h.b = null;
        h.i = null;
        h.g = 40691;
        h.h = 18698;
        this.j = h.a.getBytes(f.a);
        super.a("Name", this.j);
        this.k = j.a;
        if (h.b != null) {
            this.k = h.b.getBytes(f.a);
            super.a("Comment", this.k);
        }
        h.a(i1);
        this.h = h;
        this.h.j = this.i;
        this.c.add(this.h.a);
        int i2 = i1 == 0 ? 8 : 0;
        i2 |= 2048;
        j.a(this.out, 67324752L);
        j.a(this.out, 20);
        j.a(this.out, i2);
        j.a(this.out, i1);
        j.a(this.out, this.h.g);
        j.a(this.out, this.h.h);
        if (i1 == 0) {
            j.a(this.out, this.h.c);
            j.a(this.out, this.h.e);
            j.a(this.out, this.h.e);
        }
        else {
            j.a(this.out, 0L);
            j.a(this.out, 0L);
            j.a(this.out, 0L);
        }
        j.a(this.out, this.j.length);
        long l0 = this.i + 30L + (long)this.j.length + (long)this.h.c() != null ? 0 : this.h.c().length;
        this.n = super.a(this.h, l0);
        if (this.h.i != null) {
            j.a(this.out, this.h.i.length + this.n);
            goto 540;
        }
        else {
            j.a(this.out, this.n);
        }
        this.out.write(this.j);
        if (this.h.i != null) {
            this.out.write(this.h.i);
        }
        this.b(this.out, (long)this.n);
    }

    public void a(String str0) {
        if (str0 == null) {
            this.e = j.a;
        }
        else {
            byte[] byteArr0 = str0.getBytes(f.a);
            super.a("Comment", byteArr0);
            this.e = byteArr0;
        }
    }

    public void write(byte[] byteArr0, int i0, int i1) {
        b.a(byteArr0.length, i0, i1);
        if (this.h == null) {
            throw new ZipException("No active entry");
        }
        else if (this.h.d() == 0) {
            this.out.write(byteArr0, i0, i1);
        }
        else {
            this.out.write(byteArr0, i0, i1);
        }
    }

    private void c() {
        if (this.g == null) {
            throw new IOException("Stream is closed");
        }
        else {
        }
    }

    private void a(String str0, byte[] byteArr0) {
        if (byteArr0.length > 65535) {
            throw new IllegalArgumentException(new StringBuilder().append(str0).append(" too long in UTF-8:").append(byteArr0.length).append(" bytes").toString());
        }
        else {
        }
    }

    static  {
        j.a = new byte[]{};
        j.b = new byte[]{-1, -1, -1, -1};
    }

}
