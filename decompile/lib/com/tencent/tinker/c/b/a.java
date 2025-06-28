/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/b;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.zip.CRC32;

// class: com/tencent/tinker/c/b/a
public final class a {

    public static long a(File file) {
        Object object = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
            long l0 = a.a(stream);
            b.a(stream);
            return l0;
        }
        finally {
            Throwable throwable = v_8;
            b.a(stream);
            throw throwable;
        }
    }

    public static long a(InputStream stream) {
        CRC32 c32 = new CRC32();
        byte[] byteArr0 = new byte[]{};
        int i1 = 0;
        while (true) {
            i1 = stream.read(byteArr0);
            if (stream.read(byteArr0) > 0) {
                c32.update(byteArr0, 0, i1);
            }
            else {
                return c32.getValue();
            }
        }
    }

}
