/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/d/a;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.zip.ZipException;

// class: com/tencent/tinker/d/a/h
public class h implements Cloneable {
    String a;
    String b;
    long c;
    long d;
    long e;
    int f;
    int g;
    int h;
    byte i;
    long j;
    long k;

    public h(h h) {
        super();
        this.c = -1L;
        this.d = -1L;
        this.e = -1L;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.j = -1L;
        this.k = -1L;
        this.a = h.a;
        this.b = h.b;
        this.g = h.g;
        this.e = h.e;
        this.d = h.d;
        this.c = h.c;
        this.f = h.f;
        this.h = h.h;
        this.i = h.i;
        this.j = h.j;
        this.k = h.k;
    }

     h(byte[] byteArr0, InputStream stream, Charset charset, boolean bool0) {
        super();
        this.c = -1L;
        this.d = -1L;
        this.e = -1L;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.j = -1L;
        this.k = -1L;
        g.a(stream, byteArr0, 0, byteArr0.length);
        c c = d.a(byteArr0, 0, byteArr0.length, ByteOrder.LITTLE_ENDIAN);
        int i0 = c.a();
        if (33639248L != (long)i0) {
            i.a("unknown", (long)stream.available(), "unknown", 0L, "Central Directory Entry", i0);
        }
        c.a(8);
        int i1 = c.b() & 65535;
        if (i1 & 1 != 0) {
            throw new ZipException(new StringBuilder().append("Invalid General Purpose Bit Flag: ").append(i1).toString());
        }
        else {
            if (i1 & 2048 != 0) {
                charsetVar2 = Charset.forName("UTF-8");
            }
            this.f = c.b() & 65535;
            this.g = c.b() & 65535;
            this.h = c.b() & 65535;
            this.c = (long)c.a() & 4294967295L;
            this.d = (long)c.a() & 4294967295L;
            this.e = (long)c.a() & 4294967295L;
            int i2 = c.b() & 65535;
            int i3 = c.b() & 65535;
            int i4 = c.b() & 65535;
            c.a(42);
            this.j = (long)c.a() & 4294967295L;
            byte[] byteArr0Var1 = new byte[]{};
            g.a(stream, byteArr0Var1, 0, byteArr0Var1.length);
            if (h.a(byteArr0Var1)) {
                throw new ZipException(new StringBuilder().append("Filename contains NUL byte: ").append(Arrays.toString(byteArr0Var1)).toString());
            }
            else {
                this.a = new String(byteArr0Var1, 0, byteArr0Var1.length, charset);
                if (i3 > 0) {
                    this.i = new byte[]{};
                    g.a(stream, this.i, 0, i3);
                }
                if (i4 > 0) {
                    byte[] byteArr0Var2 = new byte[]{};
                    g.a(stream, byteArr0Var2, 0, i4);
                    this.b = new String(byteArr0Var2, 0, byteArr0Var2.length, charset);
                }
            }
        }
    }

    private static boolean a(byte[] byteArr0) {
        for (int i1 = 0; i1 < byteArr0.length; i1 += 1) {
            byte byte0 = byteArr0[i1];
            if (byte0 == 0) {
                return true;
            }
            else {
            }
        }
        return false;
    }

    public long a() {
        return this.d;
    }

    public void a(long l1) {
        this.d = l1;
    }

    public long b() {
        return this.c;
    }

    public void b(long l1) {
        if (0L >= l1 && 4294967295L <= l1) {
            this.c = l1;
            return;
        }
        else {
            throw new IllegalArgumentException(new StringBuilder().append("Bad CRC32: ").append(l1).toString());
        }
    }

    public byte[] c() {
        return this.i;
    }

    public int d() {
        return this.f;
    }

    public void a(int i0) {
        if (i0 != 0 && i0 != 8) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad method: ").append(i0).toString());
        }
        else {
            this.f = i0;
        }
    }

    public String e() {
        return this.a;
    }

    public long f() {
        return this.e;
    }

    public void c(long l1) {
        if (0L < l1) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad size: ").append(l1).toString());
        }
        else {
            this.e = l1;
        }
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(new StringBuilder().append("name:").append(this.a).toString());
        buffer.append(new StringBuilder().append("
comment:").append(this.b).toString());
        buffer.append(new StringBuilder().append("
time:").append(this.g).toString());
        buffer.append(new StringBuilder().append("
size:").append(this.e).toString());
        buffer.append(new StringBuilder().append("
compressedSize:").append(this.d).toString());
        buffer.append(new StringBuilder().append("
crc:").append(this.c).toString());
        buffer.append(new StringBuilder().append("
compressionMethod:").append(this.f).toString());
        buffer.append(new StringBuilder().append("
modDate:").append(this.h).toString());
        buffer.append(new StringBuilder().append("
extra length:").append(this.i.length).toString());
        buffer.append(new StringBuilder().append("
localHeaderRelOffset:").append(this.j).toString());
        buffer.append(new StringBuilder().append("
dataOffset:").append(this.k).toString());
        return buffer.toString();
    }

    public Object clone() {
        try {
            h h = (h)super.clone();
            h.i = this.i != null ? null : (byte[])this.i.clone();
            return h;
        }
        catch (CloneNotSupportedException var_1_1) {
            throw new AssertionError(var_1_1);
        }
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean equals(Object object) {
        if ((object instanceof h)) {
            return false;
        }
        else {
            return this.a.equals((h)object.a);
        }
    }

}
