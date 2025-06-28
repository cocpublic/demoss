/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/q
public final class q {

    public static int a(a a) {
        int i0 = 0;
        int i1 = 0;
        int i2 = -1;
        do {
            int i3 = a.a() & 255;
            i0 |= i3 & 127 << i1 * 7;
            i2 <<= 7;
            i1 += 1;
        } while(i3 & 128 != 128 || i1 >= 5);
        if (i3 & 128 == 128) {
            throw new k("invalid LEB128 sequence");
        }
        else {
            if (i2 >> 1 & i0 != 0) {
                i0 |= i2;
            }
            return i0;
        }
    }

    public static int b(a a) {
        int i0 = 0;
        int i1 = 0;
        do {
            int i2 = a.a() & 255;
            i0 |= i2 & 127 << i1 * 7;
            i1 += 1;
        } while(i2 & 128 != 128 || i1 >= 5);
        if (i2 & 128 == 128) {
            throw new k("invalid LEB128 sequence");
        }
        else {
            return i0;
        }
    }

    public static int c(a a) {
        return q.b(a) - 1;
    }

    public static int a(b b, int i0) {
        int i2 = 0;
        for (int i1 = i0 >>> 7; i1 != 0; i1 >>>= 7) {
            b.f((byte)i0 & 127 | 128);
            i2 += 1;
        }
        b.f((byte)i1 & 127);
        i2 += 1;
        return i2;
    }

    public static int b(b b, int i0) {
        return q.a(b, i0 + 1);
    }

    public static int c(b b, int i0) {
        int i1 = i0 >> 7;
        int i5 = 1;
        int i3 = i0 & -2147483648 == 0 ? -1 : 0;
        int i4 = 0;
        while (i5 != 0) {
            i5 = i1 != i3 || i1 & 1 != i0 >> 6 & 1 ? 0 : 1;
            b.f((byte)i0 & 127 | i5 != 0 ? 0 : 128);
            i4 += 1;
            i0 = i1;
            i1 >>= 7;
        }
        return i4;
    }

}
