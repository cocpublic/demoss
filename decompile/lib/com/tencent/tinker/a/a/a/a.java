/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a/a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.Buffer;
import java.io.ByteArrayOutputStream;
import com.tencent.tinker.a.a.k;
import com.tencent.tinker.a.a.w;
import com.tencent.tinker.a.a.y;
import com.tencent.tinker.a.a.p;
import com.tencent.tinker.a.a.s;
import com.tencent.tinker.a.a.u;
import com.tencent.tinker.a.a.e;
import com.tencent.tinker.a.a.r$a;
import com.tencent.tinker.a.a.r;
import com.tencent.tinker.a.a.g;
import com.tencent.tinker.a.a.h$a[];
import com.tencent.tinker.a.a.h$b[];
import com.tencent.tinker.a.a.h;
import com.tencent.tinker.a.a.h$b;
import com.tencent.tinker.a.a.h$a;
import com.tencent.tinker.a.a.i;
import com.tencent.tinker.a.a.f$a[];
import com.tencent.tinker.a.a.f$b[];
import com.tencent.tinker.a.a.f;
import com.tencent.tinker.a.a.f$a;
import com.tencent.tinker.a.a.f$b;
import com.tencent.tinker.a.a.o;
import com.tencent.tinker.a.a.m;
import com.tencent.tinker.a.a.b;
import com.tencent.tinker.a.a.c;
import com.tencent.tinker.a.a.d;

// class: com/tencent/tinker/a/a/a/a
public class a implements a, b {
    final private static short a;
    final private static h$b[] b;
    final private static h$a[] c;
    private ByteBuffer d;
    private int e;
    private boolean f;

    public a() {
        super();
        this.d = ByteBuffer.allocate(512);
        this.d.order(ByteOrder.LITTLE_ENDIAN);
        this.e = this.d.position();
        this.d.limit(this.d.capacity());
        this.f = true;
    }

    public a(ByteBuffer buffer) {
        super();
        this.d = buffer;
        this.d.order(ByteOrder.LITTLE_ENDIAN);
        this.e = buffer.limit();
        this.f = false;
    }

    public int o() {
        return this.d.position();
    }

    public void a(int i0) {
        this.d.position(i0);
    }

    private void l(int i0) {
        if (this.d.position() + i0 > this.d.limit() && this.f) {
            byte[] byteArr0 = this.d.array();
            byte[] byteArr0Var1 = new byte[]{};
            System.arraycopy(byteArr0, 0, byteArr0Var1, 0, this.d.position());
            int i1 = this.d.position();
            this.d = ByteBuffer.wrap(byteArr0Var1);
            this.d.order(ByteOrder.LITTLE_ENDIAN);
            this.d.position(i1);
            this.d.limit(this.d.capacity());
        }
    }

    public byte a() {
        return this.d.get();
    }

    public short p() {
        return this.d.getShort();
    }

    public int q() {
        return this.p() & 65535;
    }

    public int r() {
        return this.d.getInt();
    }

    public byte[] b(int i0) {
        byte[] byteArr0 = new byte[]{};
        this.d.get(byteArr0);
        return byteArr0;
    }

    public short[] c(int i0) {
        if (i0 == 0) {
            return a.a;
        }
        else {
            short[] shortArr0 = new short[]{};
            for (int i1 = 0; i1 < i0; i1 += 1) {
                shortArr0[i1] = this.p();
            }
            return shortArr0;
        }
    }

    public int s() {
        return q.b(this);
    }

    public int t() {
        return q.b(this) - 1;
    }

    public int u() {
        return q.a(this);
    }

    public w a_() {
        int i0 = this.d.position();
        try {
            int i1 = this.s();
            String str0 = t.a(this, new char[]{});
            if (str0.length() != i1) {
                throw new k(new StringBuilder().append("Declared length ").append(i1).append(" doesn't match decoded length of ").append(str0.length()).toString());
            }
            else {
                return new w(i0, str0);
            }
        }
        catch (UTFDataFormatException var_2_1) {
            throw new k(var_2_1);
        }
    }

    public y b() {
        int i0 = this.d.position();
        int i1 = this.r();
        short[] shortArr0 = this.c(i1);
        return new y(i0, shortArr0);
    }

    public p c() {
        int i0 = this.d.position();
        int i1 = this.q();
        int i2 = this.q();
        int i3 = this.r();
        return new p(i0, i1, i2, i3);
    }

    public s d() {
        int i0 = this.d.position();
        int i1 = this.q();
        int i2 = this.q();
        int i3 = this.r();
        return new s(i0, i1, i2, i3);
    }

    public u e() {
        int i0 = this.d.position();
        int i1 = this.r();
        int i2 = this.r();
        int i3 = this.r();
        return new u(i0, i1, i2, i3);
    }

    public e v() {
        int i0 = this.d.position();
        int i1 = this.r();
        return new e(i0, i1);
    }

    public r w() {
        int i0 = this.d.position();
        r$a r$a = r$a.a(this.q());
        int i1 = this.q();
        int i2 = this.q();
        int i3 = this.q();
        return new r(i0, r$a, i1, i2, i3);
    }

    public g f() {
        int i0 = this.o();
        int i1 = this.r();
        int i2 = this.r();
        int i3 = this.r();
        int i4 = this.r();
        int i5 = this.r();
        int i6 = this.r();
        int i7 = this.r();
        int i8 = this.r();
        return new g(i0, i1, i2, i3, i4, i5, i6, i7, i8);
    }

    public h g() {
        int i0 = this.d.position();
        int i1 = this.q();
        int i2 = this.q();
        int i3 = this.q();
        int i4 = this.q();
        int i5 = this.r();
        int i6 = this.r();
        short[] shortArr0 = this.c(i6);
        h$a[] h$aArr0;
        h$b[] h$bArr0;
        if (i4 > 0) {
            if (shortArr0.length & 1 == 1) {
                this.d(2);
            }
            int i7 = this.d.position();
            this.d(i4 * 8);
            h$aArr0 = this.z();
            int i8 = this.d.position();
            this.d.position(i7);
            h$bArr0 = this.a(i4, h$aArr0);
            this.d.position(i8);
        }
        else {
        }
        return new h(i0, i1, i2, i3, i5, shortArr0, a.b, a.c);
    }

    private h$a[] z() {
        int i0 = this.d.position();
        int i1 = this.s();
        h$a h$a = new h$a[]{};
        for (int i2 = 0; i2 < i1; i2 += 1) {
            int i3 = this.d.position() - i0;
            h$a[i2] = this.m(i3);
        }
        return h$a;
    }

    private h$b[] a(int i0, h$a[] h$aArr0) {
        h$b h$b = new h$b[]{};
        for (int i1 = 0; i1 < i0; i1 += 1) {
            int i2 = this.r();
            int i3 = this.q();
            int i4 = this.q();
            int i5 = super.a(h$aArr0, i4);
            h$b[i1] = new h$b(i2, i3, i5);
        }
        return h$b;
    }

    private int a(h$a[] h$aArr0, int i0) {
        for (int i1 = 0; i1 < h$aArr0.length; i1 += 1) {
            h$a h$a = h$aArr0[i1];
            if (h$a.d == i0) {
                return i1;
            }
            else {
            }
        }
        throw new IllegalArgumentException();
    }

    private h$a m(int i0) {
        int i1 = this.u();
        int i2 = Math.abs(i1);
        int[] intArr0 = new int[]{};
        int[] intArr0Var1 = new int[]{};
        for (int i4 = 0; i4 < i2; i4 += 1) {
            intArr0[i4] = this.s();
            intArr0Var1[i4] = this.s();
        }
        i4 = i1 <= 0 ? -1 : this.s();
        return new h$a(intArr0, intArr0Var1, i4, i0);
    }

    public i h() {
        int i0 = this.d.position();
        int i1 = this.s();
        int i2 = this.s();
        int[] intArr0 = new int[]{};
        for (int i3 = 0; i3 < i2; i3 += 1) {
            intArr0[i3] = this.t();
        }
        Object object = null;
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream(64);
            a$1 a$1 = new a$1(this, stream);
            while (true) {
byte byte0 = this.a();
stream.write(byte0);
                switch(byte0) {
                    case 0: {
                        break;;
                        byte[] byteArr0 = stream.toByteArray();
                        i i = new i(i0, i1, intArr0, byteArr0);
                        if (stream != null) {
                            try {
                                stream.close();
                            }
                            catch (Exception var_10_1) {
                            }
                        }
                        return i;
                    }
                    case 1: {
                        int i4 = this.s();
                        q.a(a$1, i4);
                        continue;;
                    }
                    case 2: {
                        int i5 = this.u();
                        q.c(a$1, i5);
                        continue;;
                    }
                    case 3: {
                        int i6 = this.s();
                        q.a(a$1, i6);
                        int i7 = this.t();
                        q.b(a$1, i7);
                        int i8 = this.t();
                        q.b(a$1, i8);
                        if (byte0 == 4) {
                            int i9 = this.t();
                            q.b(a$1, i9);
                            continue;;
                        }
                    }
                    case 5: {
                        int i10 = this.s();
                        q.a(a$1, i10);
                        continue;;
                    }
                    case 9: {
                        int i11 = this.t();
                        q.b(a$1, i11);
                        continue;;
                    }
                }
            }
        }
        finally {
            Throwable throwable = v_23;
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (Exception var_14_0) {
                }
            }
            throw throwable;
        }
    }

    public f i() {
        int i0 = this.d.position();
        int i1 = this.s();
        int i2 = this.s();
        int i3 = this.s();
        int i4 = this.s();
        f$a[] f$aArr0 = this.n(i1);
        f$a[] f$aArr0Var1 = this.n(i2);
        f$b[] f$bArr0 = this.o(i3);
        f$b[] f$bArr0Var1 = this.o(i4);
        return new f(i0, f$aArr0, f$aArr0Var1, f$bArr0, f$bArr0Var1);
    }

    private f$a[] n(int i0) {
        f$a f$a = new f$a[]{};
        int i1 = 0;
        for (int i2 = 0; i2 < i0; i2 += 1) {
            i1 += this.s();
            int i3 = this.s();
            f$a[i2] = new f$a(i1, i3);
        }
        return f$a;
    }

    private f$b[] o(int i0) {
        f$b f$b = new f$b[]{};
        int i1 = 0;
        for (int i2 = 0; i2 < i0; i2 += 1) {
            i1 += this.s();
            int i3 = this.s();
            int i4 = this.s();
            f$b[i2] = new f$b(i1, i3, i4);
        }
        return f$b;
    }

    private byte[] p(int i0) {
        int i1 = this.d.position();
        byte[] byteArr0 = new byte[]{};
        this.d.position(i0);
        this.d.get(byteArr0);
        return byteArr0;
    }

    public a j() {
        int i0 = this.d.position();
        byte byte0 = this.a();
        int i1 = this.d.position();
        new o(this, 29).v();
        return new a(i0, byte0, new m(i1, this.p(i1)));
    }

    public b k() {
        int i0 = this.d.position();
        int i1 = this.r();
        int[] intArr0 = new int[]{};
        for (int i2 = 0; i2 < i1; i2 += 1) {
            intArr0[i2] = this.r();
        }
        return new b(i0, intArr0);
    }

    public c l() {
        int i0 = this.d.position();
        int i1 = this.r();
        int[] intArr0 = new int[]{};
        for (int i2 = 0; i2 < i1; i2 += 1) {
            intArr0[i2] = this.r();
        }
        return new c(i0, intArr0);
    }

    public d m() {
        int i0 = this.d.position();
        int i1 = this.r();
        int i2 = this.r();
        int i3 = this.r();
        int i4 = this.r();
        v_16 = i2;
        int[][] int[]Arr0 = new int[][][]{};
        int i5 = 0;
        while (true) {
            v_16 = i2;
            if (i5 < v_16) {
                int[]Arr0[i5][0] = this.r();
                int[]Arr0[i5][1] = this.r();
                i5 += 1;
            }
            else {
                v_34 = i3;
                int[][] int[]Arr0Var1 = new int[][][]{};
                int i6 = 0;
            }
        }
        while (true) {
            v_34 = i3;
            if (i6 < v_34) {
                int[]Arr0Var1[i6][0] = this.r();
                int[]Arr0Var1[i6][1] = this.r();
                i6 += 1;
            }
            else {
                v_52 = i4;
                int[][] int[]Arr0Var2 = new int[][][]{};
                int i7 = 0;
            }
        }
        while (true) {
            v_52 = i4;
            if (i7 < v_52) {
                int[]Arr0Var2[i7][0] = this.r();
                int[]Arr0Var2[i7][1] = this.r();
                i7 += 1;
            }
            else {
                return new d(i0, i1, int[]Arr0, int[]Arr0Var1, int[]Arr0Var2);
            }
        }
    }

    public m n() {
        int i0 = this.d.position();
        new o(this, 28).v();
        return new m(i0, this.p(i0));
    }

    public void d(int i0) {
        if (i0 < 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.d.position(this.d.position() + i0);
        }
    }

    public void e(int i0) {
        this.l(1 * i0);
        this.d(i0);
    }

    public void x() {
        this.d.position(this.d.position() + 3 & 252);
    }

    public void y() {
        int i0 = v.a(this.d.position());
        this.l(i0 - this.d.position() * 1);
        while (this.d.position() & 3 != 0) {
            this.d.put(0);
        }
        if (this.d.position() > this.e) {
            this.e = this.d.position();
        }
    }

    public void f(int i0) {
        this.l(1);
        this.d.put((byte)i0);
        if (this.d.position() > this.e) {
            this.e = this.d.position();
        }
    }

    public void a(short short0) {
        this.l(2);
        this.d.putShort(short0);
        if (this.d.position() > this.e) {
            this.e = this.d.position();
        }
    }

    public void g(int i0) {
        int i1 = (short)i0;
        if (i0 != i1 & 65535) {
            throw new IllegalArgumentException(new StringBuilder().append("Expected an unsigned short: ").append(i0).toString());
        }
        else {
            this.a(i1);
        }
    }

    public void h(int i0) {
        this.l(4);
        this.d.putInt(i0);
        if (this.d.position() > this.e) {
            this.e = this.d.position();
        }
    }

    public void a(byte[] byteArr0) {
        this.l(byteArr0.length * 1);
        this.d.put(byteArr0);
        if (this.d.position() > this.e) {
            this.e = this.d.position();
        }
    }

    public void a(short[] shortArr0) {
        this.l(shortArr0.length * 2);
        for (int i1 = 0; i1 < shortArr0.length; i1 += 1) {
            short short0 = shortArr0[i1];
            this.a(short0);
        }
        if (this.d.position() > this.e) {
            this.e = this.d.position();
        }
    }

    public void i(int i0) {
        q.a(this, i0);
    }

    public void j(int i0) {
        this.i(i0 + 1);
    }

    public void k(int i0) {
        q.c(this, i0);
    }

    public int a(w w) {
        int i0 = this.d.position();
        try {
            int i1 = w.a.length();
            this.i(i1);
            this.a(t.a(w.a));
            this.f(0);
            return i0;
        }
        catch (UTFDataFormatException var_3_1) {
            throw new AssertionError(var_3_1);
        }
    }

    public int a(y y) {
        int i0 = this.d.position();
        short[] shortArr0 = y.b;
        this.h(shortArr0.length);
        for (int i2 = 0; i2 < shortArr0.length; i2 += 1) {
            short short0 = shortArr0[i2];
            this.a(short0);
        }
        return i0;
    }

    public int a(p p) {
        int i0 = this.d.position();
        this.g(p.a);
        this.g(p.b);
        this.h(p.c);
        return i0;
    }

    public int a(s s) {
        int i0 = this.d.position();
        this.g(s.a);
        this.g(s.b);
        this.h(s.c);
        return i0;
    }

    public int a(u u) {
        int i0 = this.d.position();
        this.h(u.a);
        this.h(u.b);
        this.h(u.c);
        return i0;
    }

    public int a(e e) {
        int i0 = this.d.position();
        this.h(e.a);
        return i0;
    }

    public int a(r r) {
        int i0 = this.d.position();
        this.g(r.a.j);
        this.g(r.b);
        this.g(r.c);
        this.g(r.d);
        return i0;
    }

    public int a(g g) {
        int i0 = this.d.position();
        this.h(g.a);
        this.h(g.b);
        this.h(g.c);
        this.h(g.d);
        this.h(g.e);
        this.h(g.f);
        this.h(g.g);
        this.h(g.h);
        return i0;
    }

    public int a(h h) {
        int i0 = this.d.position();
        this.g(h.a);
        this.g(h.b);
        this.g(h.c);
        this.g(h.f.length);
        this.h(h.d);
        this.h(h.e.length);
        this.a(h.e);
        if (h.f.length > 0) {
            if (h.e.length & 1 == 1) {
                this.a(0);
            }
            int i1 = this.d.position();
            this.e(h.f.length * 8);
            int[] intArr0 = super.a(h.g);
            int i2 = this.d.position();
            this.d.position(i1);
            super.a(h.f, intArr0);
            this.d.position(i2);
        }
        return i0;
    }

    private int[] a(h$a[] h$aArr0) {
        int i0 = this.d.position();
        this.i(h$aArr0.length);
        int[] intArr0 = new int[]{};
        for (int i1 = 0; i1 < h$aArr0.length; i1 += 1) {
            intArr0[i1] = this.d.position() - i0;
            super.a(h$aArr0[i1]);
        }
        return intArr0;
    }

    private void a(h$a h$a) {
        int i0 = h$a.c;
        int[] intArr0 = h$a.a;
        int[] intArr0Var1 = h$a.b;
        if (i0 != -1) {
            this.k(- intArr0.length);
        }
        else {
            this.k(intArr0.length);
        }
        for (int i1 = 0; i1 < intArr0.length; i1 += 1) {
            this.i(intArr0[i1]);
            this.i(intArr0Var1[i1]);
        }
        if (i0 != -1) {
            this.i(i0);
        }
    }

    private void a(h$b[] h$bArr0, int[] intArr0) {
        for (int i1 = 0; i1 < h$bArr0.length; i1 += 1) {
            h$b h$b = h$bArr0[i1];
            this.h(h$b.a);
            this.g(h$b.b);
            this.g(intArr0[h$b.c]);
        }
    }

    public int a(i i) {
        int i0 = this.d.position();
        this.i(i.a);
        this.i(i.b.length);
        for (int i2 = 0; i2 < i.b.length; i2 += 1) {
            int i3 = i.b[i2];
            this.j(i3);
        }
        this.a(i.c);
        return i0;
    }

    public int a(f f) {
        int i0 = this.d.position();
        this.i(f.a.length);
        this.i(f.b.length);
        this.i(f.c.length);
        this.i(f.d.length);
        super.a(f.a);
        super.a(f.b);
        super.a(f.c);
        super.a(f.d);
        return i0;
    }

    private void a(f$a[] f$aArr0) {
        int i3 = 0;
        for (int i2 = 0; i2 < f$aArr0.length; i2 += 1) {
            f$a f$a = f$aArr0[i2];
            this.i(f$a.a - i3);
            i3 = f$a.a;
            this.i(f$a.b);
        }
    }

    private void a(f$b[] f$bArr0) {
        int i3 = 0;
        for (int i2 = 0; i2 < f$bArr0.length; i2 += 1) {
            f$b f$b = f$bArr0[i2];
            this.i(f$b.a - i3);
            i3 = f$b.a;
            this.i(f$b.b);
            this.i(f$b.c);
        }
    }

    public int a(a a) {
        int i0 = this.d.position();
        this.f(a.a);
        this.a(a.b);
        return i0;
    }

    public int a(b b) {
        int i0 = this.d.position();
        this.h(b.a.length);
        int[] intArr0 = b.a;
        for (int i2 = 0; i2 < intArr0.length; i2 += 1) {
            int i3 = intArr0[i2];
            this.h(i3);
        }
        return i0;
    }

    public int a(c c) {
        int i0 = this.d.position();
        this.h(c.a.length);
        int[] intArr0 = c.a;
        for (int i2 = 0; i2 < intArr0.length; i2 += 1) {
            int i3 = intArr0[i2];
            this.h(i3);
        }
        return i0;
    }

    public int a(d d) {
        int i0 = this.d.position();
        this.h(d.a);
        this.h(d.b.length);
        this.h(d.c.length);
        this.h(d.d.length);
        int[][] int[]Arr0Var2 = d.b;
        for (int i6 = 0; i6 < int[]Arr0Var2.length; i6 += 1) {
            int[] intArr0 = int[]Arr0Var2[i6];
            this.h(intArr0[0]);
            this.h(intArr0[1]);
        }
        int[]Arr0Var2 = d.c;
        i5 = int[]Arr0Var2.length;
        for (i6 = 0; i6 < int[]Arr0Var2.length; i6 += 1) {
            int[] intArr0Var1 = int[]Arr0Var2[i6];
            this.h(intArr0Var1[0]);
            this.h(intArr0Var1[1]);
        }
        int[]Arr0Var2 = d.d;
        i5 = int[]Arr0Var2.length;
        for (i6 = 0; i6 < int[]Arr0Var2.length; i6 += 1) {
            int[] intArr0Var2 = int[]Arr0Var2[i6];
            this.h(intArr0Var2[0]);
            this.h(intArr0Var2[1]);
        }
        return i0;
    }

    public int a(m m) {
        int i0 = this.d.position();
        this.a(m.a);
        return i0;
    }

    static  {
        a.a = new short[]{};
        a.b = new h$b[]{};
        a.c = new h$a[]{};
    }

}
