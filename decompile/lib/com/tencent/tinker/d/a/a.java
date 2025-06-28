/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/d/a;

import java.util.zip.Deflater;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.HashSet;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

// class: com/tencent/tinker/d/a/a
public class a {
    final private static byte a;
    final private static byte b;
    private byte c;
    final private HashSet<String> d;
    private int e;
    private int f;
    private ByteArrayOutputStream g;
    private ZipEntry h;
    final private CRC32 i;
    private long j;
    private int k;
    private int l;
    private byte m;
    private boolean n;
    private boolean o;
    final private int p;
    private int q;

    public a(OutputStream stream) {
        super(stream, 4);
    }

    public a(OutputStream stream, int i0) {
        super(stream, new Deflater(-1, 1));
        this.c = a.a;
        this.d = new HashSet();
        this.e = 8;
        this.f = -1;
        this.g = new ByteArrayOutputStream();
        this.i = new CRC32();
        this.j = 0L;
        this.k = 0;
        this.n = false;
        this.o = false;
        this.q = 0;
        this.p = i0;
    }

    public void close() {
        if (this.o) {
            this.finish();
            this.def.end();
            this.out.close();
            this.out = null;
            this.o = true;
        }
    }

    public void a() {
        this.b();
        if (this.h == null) {
        }
        else {
            if (this.h.getMethod() == 8) {
                this.finish();
            }
            if (this.h.getMethod() == 0) {
                if (this.h.getCrc() != this.i.getValue()) {
                    throw new ZipException("CRC mismatch");
                }
                else if (this.j != this.h.getSize()) {
                    throw new ZipException("Size mismatch");
                }
            }
            int i2 = 30;
            if (this.h.getMethod() != 0) {
                i2 += 16;
                super.a(this.out, 134695760L);
                this.h.setCrc(this.i.getValue());
                super.a(this.out, this.h.getCrc());
                this.h.setCompressedSize((long)this.def.getTotalOut());
                super.a(this.out, this.h.getCompressedSize());
                this.h.setSize((long)this.def.getTotalIn());
                super.a(this.out, this.h.getSize());
            }
            int i1 = this.h.getMethod() == 0 ? 8 : 0;
            i1 |= 2048;
            super.a(this.g, 33639248L);
            this.b(this.g, 20);
            this.b(this.g, 20);
            this.b(this.g, i1);
            this.b(this.g, this.h.getMethod());
            this.b(this.g, 0);
            this.b(this.g, 33);
            super.a(this.g, this.i.getValue());
            i2 = this.h.getMethod() == 8 ? (int)(long)i2 + super.a(this.g, this.j) : (int)(long)i2 + super.a(this.g, (long)this.def.getTotalOut());
            super.a(this.g, this.j);
            i2 += this.b(this.g, this.l);
            if (this.h.getExtra() != null) {
                i2 += this.b(this.g, this.h.getExtra().length);
                goto 471;
            }
            else {
                this.b(this.g, 0);
            }
            String str0 = this.h.getComment();
            if (str0 != null) {
                byteArr0Var1 = str0.getBytes(Charset.forName("UTF-8"));
            }
            this.b(this.g, a.a.length);
            this.b(this.g, 0);
            this.b(this.g, 0);
            super.a(this.g, 0L);
            super.a(this.g, (long)this.k);
            this.g.write(this.m);
            this.m = null;
            if (this.h.getExtra() != null) {
                this.g.write(this.h.getExtra());
            }
            this.k = this.k + i2 + this.q;
            this.q = 0;
            if (a.a.length > 0) {
                this.g.write(a.a);
            }
            this.h = null;
            this.i.reset();
            this.j = 0L;
            this.def.reset();
        }
    }

    public void finish() {
        this.b();
        if (this.n) {
        }
        else if (this.d.isEmpty()) {
            throw new ZipException("No entries");
        }
        else {
            if (this.h != null) {
                this.a();
            }
            int i0 = this.g.size();
            this.a(this.g, 101010256L);
            this.b(this.g, 0);
            this.b(this.g, 0);
            this.b(this.g, this.d.size());
            this.b(this.g, this.d.size());
            this.a(this.g, (long)i0);
            this.a(this.g, (long)this.k + this.q);
            this.b(this.g, this.c.length);
            if (this.c.length > 0) {
                this.g.write(this.c);
            }
            this.g.writeTo(this.out);
            this.g = null;
            this.n = true;
        }
    }

    private int a(ZipEntry entry, int i0) {
        if (entry.getMethod() != 0 || this.p == 0) {
            return 0;
        }
        else {
            return this.p - i0 % this.p % this.p;
        }
    }

    private void a(OutputStream stream, int i0) {
        if (i0 <= 0) {
        }
        else {
            while (true) {
                i0 += 255;
                if (i0 > 0) {
                    stream.write(0);
                }
                else {
                }
            }
        }
    }

    public void a(ZipEntry entry) {
        if (this.h != null) {
            this.a();
        }
        int i1 = entry.getMethod();
        if (i1 == -1) {
            i1 = this.e;
        }
        if (i1 == 0) {
            if (-1L == entry.getCompressedSize()) {
                entry.setCompressedSize(entry.getSize());
                goto 71;
            }
            else if (-1L == entry.getSize()) {
                entry.setSize(entry.getCompressedSize());
            }
            if (-1L == entry.getCrc()) {
                throw new ZipException("STORED entry missing CRC");
            }
            else if (-1L == entry.getSize()) {
                throw new ZipException("STORED entry missing size");
            }
            else if (entry.getCompressedSize() != entry.getSize()) {
                throw new ZipException("STORED entry size/compressed size mismatch");
            }
        }
        this.b();
        if (this.d.contains(entry.getName())) {
            throw new ZipException(new StringBuilder().append("Entry already exists: ").append(entry.getName()).toString());
        }
        else if (this.d.size() == 65535) {
            throw new ZipException("Too many entries for the zip file format's 16-bit entry count");
        }
        else {
            this.m = entry.getName().getBytes(Charset.forName("UTF-8"));
            this.l = this.m.length;
            if (this.l > 65535) {
                throw new IllegalArgumentException(new StringBuilder().append("Name too long: ").append(this.l).append(" UTF-8 bytes").toString());
            }
            else {
                this.def.setLevel(this.f);
                entry.setMethod(i1);
                this.h = entry;
                this.d.add(this.h.getName());
                int i2 = i1 == 0 ? 8 : 0;
                i2 |= 2048;
                super.a(this.out, 67324752L);
                this.b(this.out, 20);
                this.b(this.out, i2);
                this.b(this.out, i1);
                if (-1L == this.h.getTime()) {
                    this.h.setTime(System.currentTimeMillis());
                }
                this.b(this.out, 0);
                this.b(this.out, 33);
                if (i1 == 0) {
                    super.a(this.out, this.h.getCrc());
                    super.a(this.out, this.h.getSize());
                    super.a(this.out, this.h.getSize());
                }
                else {
                    super.a(this.out, 0L);
                    super.a(this.out, 0L);
                    super.a(this.out, 0L);
                }
                this.b(this.out, this.l);
                int i3 = this.k + 30 + this.l + this.h.getExtra() != null ? 0 : this.h.getExtra().length;
                this.q = super.a(this.h, i3);
                if (this.h.getExtra() != null) {
                    this.b(this.out, this.h.getExtra().length + this.q);
                    goto 612;
                }
                else {
                    this.b(this.out, this.q);
                }
                this.out.write(this.m);
                if (this.h.getExtra() != null) {
                    this.out.write(this.h.getExtra());
                }
                super.a(this.out, this.q);
            }
        }
    }

    private long a(OutputStream stream, long l1) {
        stream.write((int)l1 & 255L);
        stream.write((int)l1 >> 8 & 255);
        stream.write((int)l1 >> 16 & 255);
        stream.write((int)l1 >> 24 & 255);
        return l1;
    }

    private int b(OutputStream stream, int i0) {
        if (i0 > 65535) {
            throw new IllegalArgumentException(new StringBuilder().append("value ").append(i0).append(" is too large for type 'short'.").toString());
        }
        else {
            stream.write(i0 & 255);
            stream.write(i0 >> 8 & 255);
            return i0;
        }
    }

    public void write(int i0) {
        a.b[0] = (byte)i0 & 255;
        this.write(a.b, 0, 1);
    }

    public void write(byte[] byteArr0, int i0, int i1) {
        this.a(byteArr0.length, i0, i1);
        if (this.h == null) {
            throw new ZipException("No active entry");
        }
        else {
            if (this.h.getMethod() == 0) {
                this.out.write(byteArr0, i0, i1);
            }
            else {
                super.write(byteArr0, i0, i1);
            }
            this.i.update(byteArr0, i0, i1);
            this.j = this.j + (long)i1;
        }
    }

    private void a(int i0, int i1, int i2) {
        if (i1 | i2 >= 0 || i1 <= i0 || i0 - i1 < i2) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("length=").append(i0).append("; regionStart=").append(i1).append("; regionLength=").append(i2).toString());
        }
        else {
        }
    }

    private void b() {
        if (this.o) {
            throw new IOException("Stream is closed");
        }
        else {
        }
    }

    static  {
        a.a = new byte[]{};
        a.b = new byte[]{0};
    }

}
