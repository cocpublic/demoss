/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

// class: com/tencent/tinker/loader/shareutil/e
public class e implements Closeable {
    final private FileInputStream d;
    final private Map<String, e$c> e;
    public e$a a;
    public e$b[] b;
    public e$c[] c;

    public e(File file) {
        super();
        this.e = new HashMap();
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = new FileInputStream(file);
        FileChannel channel = this.d.getChannel();
        this.a = new e$a(channel, null);
        ByteBuffer buffer = ByteBuffer.allocate(128);
        buffer.limit(this.a.j);
        buffer.order(this.a.a[5] == 1 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        channel.position(this.a.f);
        this.b = new e$b[]{};
        for (int i1 = 0; i1 < this.b.length; i1 += 1) {
            e.a(channel, buffer, "failed to read phdr.");
            this.b[i1] = new e$b(buffer, this.a.a[4], null);
        }
        channel.position(this.a.g);
        buffer.limit(this.a.l);
        this.c = new e$c[]{};
        for (i1 = 0; i1 < this.c.length; i1 += 1) {
            e.a(channel, buffer, "failed to read shdr.");
            this.c[i1] = new e$c(buffer, this.a.a[4], null);
        }
        if (this.a.n > 0) {
            e$c e$c = this.c[this.a.n];
            ByteBuffer bufferVar1 = this.a(e$c);
            e$c e$cVar1 = this.c;
            for (int i3 = 0; i3 < e$cVar1.length; i3 += 1) {
                e$c e$cVar2 = e$cVar1[i3];
                bufferVar1.position(e$cVar2.a);
                e$cVar2.k = e.a(bufferVar1);
                this.e.put(e$cVar2.k, e$cVar2);
            }
        }
    }

    private static void b(int i0, int i1, int i2, String str0) {
        if (i0 < i1 || i0 > i2) {
            throw new IOException(str0);
        }
        else {
        }
    }

    public static int a(File file) {
        Object object = null;
        try {
            byte[] byteArr0 = new byte[]{};
            FileInputStream stream = new FileInputStream(file);
            stream.read(byteArr0);
            if (byteArr0[0] == 100 && byteArr0[1] == 101 && byteArr0[2] == 121 && byteArr0[3] == 10) {
                int i0 = 0;
                if (stream != null) {
                    try {
                        stream.close();
                    }
                    catch (Throwable var_4_0) {
                    }
                }
                return i0;
            }
            else {
                if (byteArr0[0] == 127 && byteArr0[1] == 69 && byteArr0[2] == 76 && byteArr0[3] == 70) {
                    int i1 = 1;
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (Throwable var_4_1) {
                        }
                    }
                    return i1;
                }
                else {
                    int i2 = -1;
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (Throwable var_4_2) {
                        }
                    }
                    return i2;
                }
            }
        }
        finally {
            Throwable throwable = v_12;
            stream != null;
            try {
                stream.close();
            }
            catch (Throwable var_6_0) {
            }
            throw throwable;
        }
    }

    public static void a(FileChannel channel, ByteBuffer buffer, String str0) {
        buffer.rewind();
        int i0 = channel.read(buffer);
        if (i0 != buffer.limit()) {
            throw new IOException(new StringBuilder().append(str0).append(" Rest bytes insufficient, expect to read ").append(buffer.limit()).append(" bytes but only ").append(i0).append(" bytes were read.").toString());
        }
        else {
            buffer.flip();
        }
    }

    public static String a(ByteBuffer buffer) {
        byte[] byteArr0 = buffer.array();
        int i0 = buffer.position();
        while (true) {
            if (buffer.hasRemaining() && byteArr0[buffer.position()] != 0) {
                buffer.position(buffer.position() + 1);
                continue;;
            }
            else {
                buffer.position(buffer.position() + 1);
                return new String(byteArr0, i0, buffer.position() - i0 - 1, Charset.forName("ASCII"));
            }
        }
    }

    public ByteBuffer a(e$c e$c) {
        ByteBuffer buffer = ByteBuffer.allocate((int)e$c.f);
        this.d.getChannel().position(e$c.e);
        e.a(this.d.getChannel(), buffer, new StringBuilder().append("failed to read section: ").append(e$c.k).toString());
        return buffer;
    }

    public void close() {
        this.d.close();
        this.e.clear();
        this.b = null;
        this.c = null;
    }

    static /* synthetic */ void a(int i0, int i1, int i2, String str0) {
        e.b(i0, i1, i2, str0);
    }

    // class: com/tencent/tinker/loader/shareutil/e$c
    public class e$c {
        final public int a;
        final public int b;
        final public long c;
        final public long d;
        final public long e;
        final public long f;
        final public int g;
        final public int h;
        final public long i;
        final public long j;
        public String k;

        private e$c(ByteBuffer buffer, int i0) {
super();
            switch(i0) {
                case 1: {
                    this.a = buffer.getInt();
                    this.b = buffer.getInt();
                    this.c = (long)buffer.getInt();
                    this.d = (long)buffer.getInt();
                    this.e = (long)buffer.getInt();
                    this.f = (long)buffer.getInt();
                    this.g = buffer.getInt();
                    this.h = buffer.getInt();
                    this.i = (long)buffer.getInt();
                    this.j = (long)buffer.getInt();
                    break;;
                }
                case 2: {
                    this.a = buffer.getInt();
                    this.b = buffer.getInt();
                    this.c = buffer.getLong();
                    this.d = buffer.getLong();
                    this.e = buffer.getLong();
                    this.f = buffer.getLong();
                    this.g = buffer.getInt();
                    this.h = buffer.getInt();
                    this.i = buffer.getLong();
                    this.j = buffer.getLong();
                    break;;
                }
                default: {
                    throw new IOException(new StringBuilder().append("Unexpected elf class: ").append(i0).toString());
                }
            }
            this.k = null;
        }

        /* synthetic */ e$c(ByteBuffer buffer, int i0, e$1 e$1) {
            super(buffer, i0);
        }

    }
    // class: com/tencent/tinker/loader/shareutil/e$c
    public class e$c {
        final public int a;
        final public int b;
        final public long c;
        final public long d;
        final public long e;
        final public long f;
        final public int g;
        final public int h;
        final public long i;
        final public long j;
        public String k;

        private e$c(ByteBuffer buffer, int i0) {
super();
            switch(i0) {
                case 1: {
                    this.a = buffer.getInt();
                    this.b = buffer.getInt();
                    this.c = (long)buffer.getInt();
                    this.d = (long)buffer.getInt();
                    this.e = (long)buffer.getInt();
                    this.f = (long)buffer.getInt();
                    this.g = buffer.getInt();
                    this.h = buffer.getInt();
                    this.i = (long)buffer.getInt();
                    this.j = (long)buffer.getInt();
                    break;;
                }
                case 2: {
                    this.a = buffer.getInt();
                    this.b = buffer.getInt();
                    this.c = buffer.getLong();
                    this.d = buffer.getLong();
                    this.e = buffer.getLong();
                    this.f = buffer.getLong();
                    this.g = buffer.getInt();
                    this.h = buffer.getInt();
                    this.i = buffer.getLong();
                    this.j = buffer.getLong();
                    break;;
                }
                default: {
                    throw new IOException(new StringBuilder().append("Unexpected elf class: ").append(i0).toString());
                }
            }
            this.k = null;
        }

        /* synthetic */ e$c(ByteBuffer buffer, int i0, e$1 e$1) {
            super(buffer, i0);
        }

    }
    // class: com/tencent/tinker/loader/shareutil/e$b
    public class e$b {
        final public int a;
        final public int b;
        final public long c;
        final public long d;
        final public long e;
        final public long f;
        final public long g;
        final public long h;

        private e$b(ByteBuffer buffer, int i0) {
super();
            switch(i0) {
                case 1: {
                    this.a = buffer.getInt();
                    this.c = (long)buffer.getInt();
                    this.d = (long)buffer.getInt();
                    this.e = (long)buffer.getInt();
                    this.f = (long)buffer.getInt();
                    this.g = (long)buffer.getInt();
                    this.b = buffer.getInt();
                    this.h = (long)buffer.getInt();
                    return;
                }
                case 2: {
                    this.a = buffer.getInt();
                    this.b = buffer.getInt();
                    this.c = buffer.getLong();
                    this.d = buffer.getLong();
                    this.e = buffer.getLong();
                    this.f = buffer.getLong();
                    this.g = buffer.getLong();
                    this.h = buffer.getLong();
                    return;
                }
                default: {
                    throw new IOException(new StringBuilder().append("Unexpected elf class: ").append(i0).toString());
                }
            }
        }

        /* synthetic */ e$b(ByteBuffer buffer, int i0, e$1 e$1) {
            super(buffer, i0);
        }

    }
    // class: com/tencent/tinker/loader/shareutil/e$b
    public class e$b {
        final public int a;
        final public int b;
        final public long c;
        final public long d;
        final public long e;
        final public long f;
        final public long g;
        final public long h;

        private e$b(ByteBuffer buffer, int i0) {
super();
            switch(i0) {
                case 1: {
                    this.a = buffer.getInt();
                    this.c = (long)buffer.getInt();
                    this.d = (long)buffer.getInt();
                    this.e = (long)buffer.getInt();
                    this.f = (long)buffer.getInt();
                    this.g = (long)buffer.getInt();
                    this.b = buffer.getInt();
                    this.h = (long)buffer.getInt();
                    return;
                }
                case 2: {
                    this.a = buffer.getInt();
                    this.b = buffer.getInt();
                    this.c = buffer.getLong();
                    this.d = buffer.getLong();
                    this.e = buffer.getLong();
                    this.f = buffer.getLong();
                    this.g = buffer.getLong();
                    this.h = buffer.getLong();
                    return;
                }
                default: {
                    throw new IOException(new StringBuilder().append("Unexpected elf class: ").append(i0).toString());
                }
            }
        }

        /* synthetic */ e$b(ByteBuffer buffer, int i0, e$1 e$1) {
            super(buffer, i0);
        }

    }
    // class: com/tencent/tinker/loader/shareutil/e$a
    public class e$a {
        final public byte a;
        final public short b;
        final public short c;
        final public int d;
        final public long e;
        final public long f;
        final public long g;
        final public int h;
        final public short i;
        final public short j;
        final public short k;
        final public short l;
        final public short m;
        final public short n;

        private e$a(FileChannel channel) {
            super();
            this.a = new byte[]{};
            channel.position(0L);
            channel.read(ByteBuffer.wrap(this.a));
            if (this.a[0] == 127 && this.a[1] == 69 || this.a[2] == 76 || this.a[3] != 70) {
                throw new IOException(String.format("bad elf magic: %x %x %x %x.", new Object[]{Byte.valueOf(this.a[0]), Byte.valueOf(this.a[1]), Byte.valueOf(this.a[2]), Byte.valueOf(this.a[3])}));
            }
            else {
                e.a(this.a[4], 1, 2, new StringBuilder().append("bad elf class: ").append(this.a[4]).toString());
                e.a(this.a[5], 1, 2, new StringBuilder().append("bad elf data encoding: ").append(this.a[5]).toString());
                ByteBuffer buffer = ByteBuffer.allocate(this.a[4] == 1 ? 48 : 36);
buffer.order(this.a[5] == 1 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
e.a(channel, buffer, "failed to read rest part of ehdr.");
this.b = buffer.getShort();
this.c = buffer.getShort();
this.d = buffer.getInt();
e.a(this.d, 1, 1, new StringBuilder().append("bad elf version: ").append(this.d).toString());
                switch(this.a[4]) {
                    case 1: {
                        this.e = (long)buffer.getInt();
                        this.f = (long)buffer.getInt();
                        this.g = (long)buffer.getInt();
                        break;;
                    }
                    case 2: {
                        this.e = buffer.getLong();
                        this.f = buffer.getLong();
                        this.g = buffer.getLong();
                        break;;
                    }
                    default: {
                        throw new IOException(new StringBuilder().append("Unexpected elf class: ").append(this.a[4]).toString());
                    }
                }
                this.h = buffer.getInt();
                this.i = buffer.getShort();
                this.j = buffer.getShort();
                this.k = buffer.getShort();
                this.l = buffer.getShort();
                this.m = buffer.getShort();
                this.n = buffer.getShort();
            }
        }

        /* synthetic */ e$a(FileChannel channel, e$1 e$1) {
            super(channel);
        }

    }
    // class: com/tencent/tinker/loader/shareutil/e$a
    public class e$a {
        final public byte a;
        final public short b;
        final public short c;
        final public int d;
        final public long e;
        final public long f;
        final public long g;
        final public int h;
        final public short i;
        final public short j;
        final public short k;
        final public short l;
        final public short m;
        final public short n;

        private e$a(FileChannel channel) {
            super();
            this.a = new byte[]{};
            channel.position(0L);
            channel.read(ByteBuffer.wrap(this.a));
            if (this.a[0] == 127 && this.a[1] == 69 || this.a[2] == 76 || this.a[3] != 70) {
                throw new IOException(String.format("bad elf magic: %x %x %x %x.", new Object[]{Byte.valueOf(this.a[0]), Byte.valueOf(this.a[1]), Byte.valueOf(this.a[2]), Byte.valueOf(this.a[3])}));
            }
            else {
                e.a(this.a[4], 1, 2, new StringBuilder().append("bad elf class: ").append(this.a[4]).toString());
                e.a(this.a[5], 1, 2, new StringBuilder().append("bad elf data encoding: ").append(this.a[5]).toString());
                ByteBuffer buffer = ByteBuffer.allocate(this.a[4] == 1 ? 48 : 36);
buffer.order(this.a[5] == 1 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
e.a(channel, buffer, "failed to read rest part of ehdr.");
this.b = buffer.getShort();
this.c = buffer.getShort();
this.d = buffer.getInt();
e.a(this.d, 1, 1, new StringBuilder().append("bad elf version: ").append(this.d).toString());
                switch(this.a[4]) {
                    case 1: {
                        this.e = (long)buffer.getInt();
                        this.f = (long)buffer.getInt();
                        this.g = (long)buffer.getInt();
                        break;;
                    }
                    case 2: {
                        this.e = buffer.getLong();
                        this.f = buffer.getLong();
                        this.g = buffer.getLong();
                        break;;
                    }
                    default: {
                        throw new IOException(new StringBuilder().append("Unexpected elf class: ").append(this.a[4]).toString());
                    }
                }
                this.h = buffer.getInt();
                this.i = buffer.getShort();
                this.j = buffer.getShort();
                this.k = buffer.getShort();
                this.l = buffer.getShort();
                this.m = buffer.getShort();
                this.n = buffer.getShort();
            }
        }

        /* synthetic */ e$a(FileChannel channel, e$1 e$1) {
            super(channel);
        }

    }
}
