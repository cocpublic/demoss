/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/d/a;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.util.LinkedHashMap;
import java.util.zip.ZipException;
import java.util.Collection;
import java.util.Iterator;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

// class: com/tencent/tinker/d/a/i
public class i implements Closeable {
    final private String a;
    final private LinkedHashMap<String, h> b;
    private File c;
    private RandomAccessFile d;
    private String e;

    public i(File file) {
        super(file, 1);
    }

    public i(String str0) {
        super(new File(str0), 1);
    }

    public i(File file, int i0) {
        super();
        this.b = new LinkedHashMap();
        this.a = file.getPath();
        if (i0 != 1 && i0 != 5) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad mode: ").append(i0).toString());
        }
        else {
            this.c = i0 & 4 != 0 ? null : file;
            this.d = new RandomAccessFile(this.a, "r");
            this.d();
        }
    }

    static void a(String str0, long l1, String l1, long str1, String l3, int l3) {
        String str3 = Integer.toHexString(i0);
        throw new ZipException(new StringBuilder().append("file name:").append(str0).append(", file size").append(l1).append(", entry name:").append(str1).append(", entry localHeaderRelOffset:").append(l3).append(", ").append(str2).append(" signature not found; was ").append(str3).toString());
    }

    public void close() {
        v_1 = this.d;
        RandomAccessFile file = this.d;
        if (file != null) {
            v_6;
            synchronized () {
                this.d = null;
                file.close();
                goto 32;
            }
            if (this.c != null) {
                this.c.delete();
                this.c = null;
            }
        }
    }

    private void c() {
        if (this.d == null) {
            throw new IllegalStateException("Zip file closed");
        }
        else {
        }
    }

    public Enumeration<? extends h> a() {
        this.c();
        Iterator iterator = this.b.values().iterator();
        return new i$1(this, iterator);
    }

    public String b() {
        this.c();
        return this.e;
    }

    public h a(String str0) {
        this.c();
        if (str0 == null) {
            throw new NullPointerException("entryName == null");
        }
        else {
            h hVar1 = (h)this.b.get(str0);
            if (hVar1 == null) {
                hVar1 = (h)this.b.get(new StringBuilder().append(str0).append("/").toString());
            }
            return hVar1;
        }
    }

    public InputStream a(h h) {
        h = this.a(h.e());
        if (h == null) {
            return null;
        }
        else {
            v_7 = this.d;
            RandomAccessFile file = this.d;
            v_25;
            synchronized () {
                i$a i$a = new i$a(file, h.j);
                DataInputStream stream = new DataInputStream(i$a);
                int i0 = Integer.reverseBytes(stream.readInt());
                if (67324752L != (long)i0) {
                    i.a(this.a, file.length(), h.e(), h.j, "Local File Header", i0);
                }
                stream.skipBytes(2);
                int i1 = Short.reverseBytes(stream.readShort()) & 65535;
                if (i1 & 1 != 0) {
                    throw new ZipException(new StringBuilder().append("Invalid General Purpose Bit Flag: ").append(i1).toString());
                }
                else {
                    stream.skipBytes(18);
                    int i2 = Short.reverseBytes(stream.readShort()) & 65535;
                    int i3 = Short.reverseBytes(stream.readShort()) & 65535;
                    stream.close();
                    i$a.skip((long)i2 + i3);
                    if (h.f == 0) {
                        i$a.a(i$a, i$a.a(i$a) + h.e);
                    }
                    else {
                        i$a.a(i$a, i$a.a(i$a) + h.d);
                    }
                    return i$a;
                }
            }
        }
    }

    private void d() {
        long l0 = this.d.length() - 22L;
        if (0L < l0) {
            throw new ZipException(new StringBuilder().append("File too short to be a zip file: ").append(this.d.length()).toString());
        }
        else {
            this.d.seek(0L);
            int i0 = Integer.reverseBytes(this.d.readInt());
            if (67324752L != (long)i0) {
                throw new ZipException("Not a zip archive");
            }
            else {
                long l2 = l0 - 65536L;
                if (0L < l2) {
                    l2 = 0L;
                }
                do {
                    this.d.seek(l0);
                    if (101010256L == (long)Integer.reverseBytes(this.d.readInt())) {
                        l0 -= 1L;
                        if (l2 < l0) {
                        }
                    }
                } while(l2 < l0);
                throw new ZipException("End Of Central Directory signature not found");
                byte[] byteArr0 = new byte[]{};
                this.d.readFully(byteArr0);
                c c = d.a(byteArr0, 0, byteArr0.length, ByteOrder.LITTLE_ENDIAN);
                int i1 = c.b() & 65535;
                int i2 = c.b() & 65535;
                int i3 = c.b() & 65535;
                int i4 = c.b() & 65535;
                c.b(4);
                long l3 = (long)c.a() & 4294967295L;
                int i5 = c.b() & 65535;
                if (i3 == i4 || i1 == 0 || i2 != 0) {
                    throw new ZipException("Spanned archives not supported");
                }
                else {
                    if (i5 > 0) {
                        byte[] byteArr0Var1 = new byte[]{};
                        this.d.readFully(byteArr0Var1);
                        this.e = new String(byteArr0Var1, 0, byteArr0Var1.length, f.a);
                    }
                    i$a i$a = new i$a(this.d, l3);
                    BufferedInputStream stream = new BufferedInputStream(i$a, 4096);
                    byte[] byteArr0Var2 = new byte[]{};
                    for (int i6 = 0; i6 < i3; i6 += 1) {
                        h h = new h(byteArr0Var2, stream, f.a, 0);
                        l3 >= h.j;
                        throw new ZipException("Local file header offset is after central directory");
                        String str0 = h.e();
                        this.b.put(str0, h) != null;
                        throw new ZipException(new StringBuilder().append("Duplicate entry name: ").append(str0).toString());
                    }
                }
            }
        }
    }

    static /* synthetic */ void a(i i) {
        i.c();
    }

    // class: com/tencent/tinker/d/a/i$a
    public class i$a {
        final private RandomAccessFile a;
        private long b;
        private long c;

        public i$a(RandomAccessFile file, long l1, long l1) {
            super();
            this.a = file;
            this.c = l1;
            this.b = l3;
        }

        public i$a(RandomAccessFile file, long l1) {
            super(file, l1, file.length());
        }

        public int available() {
            if (this.b < this.c) {
                return 1;
            }
            else {
                return 0;
            }
        }

        public int read() {
            return g.a(this);
        }

        public int read(byte[] byteArr0, int i0, int i1) {
            RandomAccessFile file = this.a;
            this.a;
            synchronized () {
                long l0 = this.b - this.c;
                if (l0 > (long)i1) {
                    i1 = (int)l0;
                }
                this.a.seek(this.c);
                int i2 = this.a.read(byteArr0, i0, i1);
                if (i2 > 0) {
                    this.c = this.c + (long)i2;
                    return i2;
                }
                else {
                    return -1;
                }
            }
        }

        public long skip(long l1) {
            if (this.b - this.c > l1) {
                l1 = this.b - this.c;
            }
            this.c = this.c + l1;
            return l1;
        }

        static /* synthetic */ long a(i$a i$a, long l1) {
            i$a.b = l1;
            return l1;
        }

        static /* synthetic */ long a(i$a i$a) {
            return i$a.c;
        }

    }
    // class: com/tencent/tinker/d/a/i$a
    public class i$a {
        final private RandomAccessFile a;
        private long b;
        private long c;

        public i$a(RandomAccessFile file, long l1, long l1) {
            super();
            this.a = file;
            this.c = l1;
            this.b = l3;
        }

        public i$a(RandomAccessFile file, long l1) {
            super(file, l1, file.length());
        }

        public int available() {
            if (this.b < this.c) {
                return 1;
            }
            else {
                return 0;
            }
        }

        public int read() {
            return g.a(this);
        }

        public int read(byte[] byteArr0, int i0, int i1) {
            RandomAccessFile file = this.a;
            this.a;
            synchronized () {
                long l0 = this.b - this.c;
                if (l0 > (long)i1) {
                    i1 = (int)l0;
                }
                this.a.seek(this.c);
                int i2 = this.a.read(byteArr0, i0, i1);
                if (i2 > 0) {
                    this.c = this.c + (long)i2;
                    return i2;
                }
                else {
                    return -1;
                }
            }
        }

        public long skip(long l1) {
            if (this.b - this.c > l1) {
                l1 = this.b - this.c;
            }
            this.c = this.c + l1;
            return l1;
        }

        static /* synthetic */ long a(i$a i$a, long l1) {
            i$a.b = l1;
            return l1;
        }

        static /* synthetic */ long a(i$a i$a) {
            return i$a.c;
        }

    }
}
