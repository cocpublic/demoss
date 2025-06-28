/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/b/b;


// class: com/tencent/tinker/a/b/b/a
public final class a {

    public static String a(long l1) {
        char[] charArr0 = new char[]{};
        for (int i0 = 0; i0 < 16; i0 += 1) {
            charArr0[15 - i0] = Character.forDigit((int)l1 & 15, 16);
            l1 >>= 4;
        }
        return new String(charArr0);
    }

    public static String a(int i0) {
        char[] charArr0 = new char[]{};
        for (int i1 = 0; i1 < 8; i1 += 1) {
            charArr0[7 - i1] = Character.forDigit(i0 & 15, 16);
            i0 >>= 4;
        }
        return new String(charArr0);
    }

    public static String b(int i0) {
        char[] charArr0 = new char[]{};
        for (int i1 = 0; i1 < 4; i1 += 1) {
            charArr0[3 - i1] = Character.forDigit(i0 & 15, 16);
            i0 >>= 4;
        }
        return new String(charArr0);
    }

    public static String c(int i0) {
        if (i0 == (char)i0) {
            return a.b(i0);
        }
        else {
            return a.a(i0);
        }
    }

    public static String d(int i0) {
        char[] charArr0 = new char[]{};
        charArr0[0] = Character.forDigit(i0 & 15, 16);
        return new String(charArr0);
    }

    public static String e(int i0) {
        char[] charArr0 = new char[]{};
        if (i0 < 0) {
            charArr0[0] = 45;
            i0 = - i0;
        }
        else {
            charArr0[0] = 43;
        }
        for (int i1 = 0; i1 < 8; i1 += 1) {
            charArr0[8 - i1] = Character.forDigit(i0 & 15, 16);
            i0 >>= 4;
        }
        return new String(charArr0);
    }

}
