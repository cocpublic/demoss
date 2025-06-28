/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/n
public final class n {

    public static void a(b b, int i0, long l1) {
        int i1 = 65 - Long.numberOfLeadingZeros(l1 ^ l1 >> 63);
        b.f(i0 | i2 - 1 << 5);
        for (int i2 = i1 + 7 >> 3; i2 > 0; i2 += 255) {
            b.f();
            l1 >>= 8;
        }
    }

    public static void b(b b, int i0, long l1) {
        int i2 = 64 - Long.numberOfLeadingZeros(l1);
        if (i2 == 0) {
            i2 = 1;
        }
        b.f(i0 | i3 - 1 << 5);
        for (int i3 = i2 + 7 >> 3; i3 > 0; i3 += 255) {
            b.f();
            l1 >>= 8;
        }
    }

    public static void c(b b, int i0, long l1) {
        int i2 = 64 - Long.numberOfTrailingZeros(l1);
        if (i2 == 0) {
            i2 = 1;
        }
        l1 >>= 64 - i3 * 8;
        b.f(i0 | i3 - 1 << 5);
        for (int i3 = i2 + 7 >> 3; i3 > 0; i3 += 255) {
            b.f();
            l1 >>= 8;
        }
    }

    public static int a(a a, int i0) {
        int i3 = 0;
        while (true) {
            i3 = i0 >= 0 ? i3 >> 3 - i0 * 8 : i3 >>> 8 | a.a() & 255 << 24;
            return i3;
        }
    }

    public static int a(a a, int i0, boolean bool0) {
        int i5 = 0;
        if (bool0) {
            while (true) {
                i5 = i0 >= 0 ? i5 >>> 3 - i0 * 8 : i5 >>> 8 | a.a() & 255 << 24;
                break;;
            }
        }
        else {
            while (i0 >= 0) {
                i5 = i5 >>> 8 | a.a() & 255 << 24;
                i0 += 255;
            }
        }
        return i5;
    }

    public static long b(a a, int i0) {
        long l1 = 0L;
        while (true) {
            l1 = i0 >= 0 ? l1 >> 7 - i0 * 8 : l1 >>> 8 | (long)a.a() & 255L << 56;
            return l1;
        }
    }

    public static long b(a a, int i0, boolean bool0) {
        long l2 = 0L;
        if (bool0) {
            while (true) {
                l2 = i0 >= 0 ? l2 >>> 7 - i0 * 8 : l2 >>> 8 | (long)a.a() & 255L << 56;
                break;;
            }
        }
        else {
            while (i0 >= 0) {
                l2 = l2 >>> 8 | (long)a.a() & 255L << 56;
                i0 += 255;
            }
        }
        return l2;
    }

}
