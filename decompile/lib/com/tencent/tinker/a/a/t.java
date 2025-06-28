/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;

import java.io.UTFDataFormatException;

// class: com/tencent/tinker/a/a/t
public final class t {

    public static String a(a a, char[] charArr0) {
        int i0 = 0;
        while (true) {
            int i1 = (char)a.a() & 255;
            if (i1 == 0) {
                return new String(charArr0, 0, i0);
            }
            else {
                charArr0[i0] = i1;
                if (i1 < 128) {
                    i0 += 1;
                }
                else if (i1 & 224 == 192) {
                    int i2 = a.a() & 255;
                    if (i2 & 192 != 128) {
                        throw new UTFDataFormatException("bad second byte");
                    }
                    else {
                        i0 += 1;
                        charArr0[i0] = (char)i1 & 31 << 6 | i2 & 63;
                    }
                }
                else if (i1 & 240 == 224) {
                    int i3 = a.a() & 255;
                    int i4 = a.a() & 255;
                    if (i3 & 192 != 128 || i4 & 192 != 128) {
                        throw new UTFDataFormatException("bad second or third byte");
                    }
                    else {
                        i0 += 1;
                        charArr0[i0] = (char)i1 & 15 << 12 | i3 & 63 << 6 | i4 & 63;
                    }
                }
                else {
                    throw new UTFDataFormatException("bad byte");
                }
            }
        }
    }

    public static long a(String str0, boolean bool0) {
        long l1 = 0L;
        int i0 = str0.length();
        for (int i1 = 0; i1 < i0; i1 += 1) {
            char char = str0.charAt(i1);
            if (char != 0 && char <= 127) {
                l1 += 1L;
            }
            else {
                l1 = char <= 2047 ? l1 + 3L : l1 + 2L;
            }
            if (bool0 && 65535L > l1) {
                throw new UTFDataFormatException("String more than 65535 UTF bytes long");
            }
            else {
            }
        }
        return l1;
    }

    public static void a(byte[] byteArr0, int i0, String str0) {
        int i1 = str0.length();
        for (int i2 = 0; i2 < i1; i2 += 1) {
            char char = str0.charAt(i2);
            if (char != 0 && char <= 127) {
                i0 += 1;
                byteArr0[i0] = (byte)char;
                continue;;
            }
            else if (char <= 2047) {
                i0 += 1;
                byteArr0[i0] = (byte)192 | 31 & char >> 6;
                i0 += 1;
                byteArr0[i0] = (byte)128 | 63 & char;
                continue;;
            }
            else {
                i0 += 1;
                byteArr0[i0] = (byte)224 | 15 & char >> 12;
                i0 += 1;
                byteArr0[i0] = (byte)128 | 63 & char >> 6;
                i0 += 1;
                byteArr0[i0] = (byte)128 | 63 & char;
            }
        }
    }

    public static byte[] a(String str0) {
        int i0 = (int)t.a(str0, 0);
        byte[] byteArr0 = new byte[]{};
        t.a(byteArr0, 0, str0);
        return byteArr0;
    }

}
