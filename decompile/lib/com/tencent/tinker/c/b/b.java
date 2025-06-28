/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/b;

import java.io.Closeable;
import java.util.zip.ZipFile;

// class: com/tencent/tinker/c/b/b
public final class b {

    public static void a(InputStream stream, OutputStream stream) {
        byte[] byteArr0 = new byte[]{};
        int i1 = 0;
        while (true) {
            i1 = stream.read(byteArr0);
            if (stream.read(byteArr0) > 0) {
                stream.write(byteArr0, 0, i1);
            }
            else {
                stream.flush();
            }
        }
    }

    public static void a(Object object) {
        if (object == null) {
        }
        else {
            try {
                if ((object instanceof Closeable)) {
                    (Closeable)object.close();
                }
                else if ((object instanceof AutoCloseable)) {
                    (AutoCloseable)object.close();
                }
                else if ((object instanceof ZipFile)) {
                    (ZipFile)object.close();
                }
            }
            catch (Throwable var_1_0) {
            }
        }
    }

}
