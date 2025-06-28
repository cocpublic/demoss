/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/util;

import java.io.Closeable;
import java.util.zip.ZipFile;

// class: com/tencent/tinker/commons/util/IOHelper
public final class IOHelper {

    public IOHelper() {
        super();
    }

    public static void copyStream(InputStream is, OutputStream os) {
        byte[] buffer = new byte[]{};
        int bytesRead = 0;
        while (true) {
            bytesRead = is.read(buffer);
            if (is.read(buffer) > 0) {
                os.write(buffer, 0, bytesRead);
            }
            else {
                os.flush();
            }
        }
    }

    public static void closeQuietly(Object obj) {
        if (obj == null) {
        }
        else {
            try {
                if ((obj instanceof Closeable)) {
                    (Closeable)obj.close();
                }
                else if ((obj instanceof AutoCloseable)) {
                    (AutoCloseable)obj.close();
                }
                else if ((obj instanceof ZipFile)) {
                    (ZipFile)obj.close();
                }
            }
            catch (Throwable var_1_0) {
            }
        }
    }

}
