/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/b/a;


// class: com/tencent/tinker/a/b/a/i
public final class i {
    private short a;

    public i(int i0) {
        super();
        if (i0 < 0) {
            throw new IllegalArgumentException("initSize < 0");
        }
        else {
            this.a = new short[]{};
        }
    }

    public short[] d() {
        int i0 = this.a();
        if (i0 == this.a.length) {
            return this.a;
        }
        else {
            short[] shortArr0 = new short[]{};
            System.arraycopy(this.a, 0, shortArr0, 0, i0);
            return shortArr0;
        }
    }

    public void a(short short0) {
        this.c(1);
        this.a[this.a()] = short0;
        this.a(1);
    }

    public void a(short short0, short short1) {
        this.a(short0);
        this.a(short1);
    }

    public void a(short short0, short short1, short short2) {
        this.a(short0);
        this.a(short1);
        this.a(short2);
    }

    public void a(short short0, short short1, short short2, short short3) {
        this.a(short0);
        this.a(short1);
        this.a(short2);
        this.a(short3);
    }

    public void a(short short0, short short1, short short2, short short3, short short4) {
        this.a(short0);
        this.a(short1);
        this.a(short2);
        this.a(short3);
        this.a(short4);
    }

    public void b(int i0) {
        this.a((short)i0);
        this.a((short)i0 >> 16);
    }

    public void a(long l1) {
        this.a();
        this.a();
        this.a();
        this.a();
    }

    public void a(byte[] byteArr0) {
        int i4 = 0;
        int i5 = 1;
        for (int i3 = 0; i3 < byteArr0.length; i3 += 1) {
            byte byte0 = byteArr0[i3];
            i4 = i5 != 0 ? i4 | byte0 << 8 : byte0 & 255;
            this.a((short)i4);
            i5 = 1;
        }
        if (i5 == 0) {
            this.a((short)i4);
        }
    }

    public void a(short[] shortArr0) {
        for (int i1 = 0; i1 < shortArr0.length; i1 += 1) {
            short short0 = shortArr0[i1];
            this.a(short0);
        }
    }

    public void a(int[] intArr0) {
        for (int i1 = 0; i1 < intArr0.length; i1 += 1) {
            int i2 = intArr0[i1];
            this.b(i2);
        }
    }

    public void a(long[] longArr0) {
        for (int i1 = 0; i1 < longArr0.length; i1 += 1) {
            long l0 = longArr0[i1];
            this.a(l0);
        }
    }

    private void c(int i0) {
        int i1 = this.a();
        if (this.a.length - i1 < i0) {
            short[] shortArr0 = new short[]{};
            System.arraycopy(this.a, 0, shortArr0, 0, i1);
            this.a = shortArr0;
        }
    }

}
