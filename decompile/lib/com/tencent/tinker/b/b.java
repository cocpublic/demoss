/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/b;

import java.io.ByteArrayOutputStream;

// class: com/tencent/tinker/b/b
public class b {

    final public static boolean a(InputStream stream, byte[] byteArr0, int i0, int i1) {
        for (int i2 = 0; i2 < i1; i2 += i3) {
            int i3 = stream.read(byteArr0, i0 + i2, i1 - i2);
            if (i3 < 0) {
                return false;
            }
            else {
            }
        }
        return true;
    }

    public static byte[] a(InputStream stream) {
        stream = new ByteArrayOutputStream();
        byte[] byteArr0 = new byte[]{};
        int i1 = -1;
        while (true) {
            i1 = stream.read(byteArr0, 0, 8192);
            if (stream.read(byteArr0, 0, 8192) != -1) {
                stream.write(byteArr0, 0, i1);
            }
            else {
                Object object = null;
                return stream.toByteArray();
            }
        }
    }

}
