/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/d/a;

import java.io.EOFException;
import java.util.concurrent.atomic.AtomicReference;

// class: com/tencent/tinker/d/a/g
public final class g {
    private static AtomicReference<byte[]> a;

    public static int a(InputStream stream) {
        byte[] byteArr0 = new byte[]{};
        int i0 = stream.read(byteArr0, 0, 1);
        if (i0 != -1) {
            return byteArr0[0] & 255;
        }
        else {
            return -1;
        }
    }

    public static void a(InputStream stream, byte[] byteArr0, int i0, int i1) {
        if (i1 == 0) {
        }
        else if (stream == null) {
            throw new NullPointerException("in == null");
        }
        else if (byteArr0 == null) {
            throw new NullPointerException("dst == null");
        }
        else {
            b.a(byteArr0.length, i0, i1);
            while (i1 > 0) {
                int i2 = stream.read(byteArr0, i0, i1);
                if (i2 < 0) {
                    throw new EOFException();
                }
                else {
                    i0 += i2;
                    i1 -= i2;
                    continue;;
                }
            }
        }
    }

    static  {
        g.a = new AtomicReference();
    }

}
