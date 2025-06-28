/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a/b;

import java.io.ByteArrayOutputStream;

// class: com/tencent/tinker/a/a/b/d
public final class d {

    public static byte[] a(InputStream stream) {
        return d.a(stream, 32768);
    }

    public static byte[] a(InputStream stream, int i0) {
        if (i0 <= 0) {
            i0 = 32768;
        }
        stream = new ByteArrayOutputStream(i0);
        byte[] byteArr0 = new byte[]{};
        while (true) {
            int i1 = stream.read(byteArr0);
            if (stream.read(byteArr0) > 0) {
                stream.write(byteArr0, 0, i1);
            }
            else {
                return stream.toByteArray();
            }
        }
    }

}
