/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/d/a;

import java.nio.ByteOrder;

// class: com/tencent/tinker/d/a/e
public final class e {

    public static int a(byte[] byteArr0, int i0, ByteOrder order) {
        if (order == ByteOrder.BIG_ENDIAN) {
            i0 += 1;
            i0 += 1;
            i0 += 1;
            return byteArr0[i0] & 255 << 24 | byteArr0[i0] & 255 << 16 | byteArr0[i0] & 255 << 8 | byteArr0[i0] & 255 << 0;
        }
        else {
            i0 += 1;
            i0 += 1;
            i0 += 1;
            return byteArr0[i0] & 255 << 0 | byteArr0[i0] & 255 << 8 | byteArr0[i0] & 255 << 16 | byteArr0[i0] & 255 << 24;
        }
    }

    public static short b(byte[] byteArr0, int i0, ByteOrder order) {
        if (order == ByteOrder.BIG_ENDIAN) {
            return (short)byteArr0[i0] << 8 | byteArr0[i0 + 1] & 255;
        }
        else {
            return (short)byteArr0[i0 + 1] << 8 | byteArr0[i0] & 255;
        }
    }

}
