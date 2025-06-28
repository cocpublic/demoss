/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/res/aapt;

import java.io.Closeable;
import java.util.zip.ZipFile;

// class: com/tencent/rfix/build/res/aapt/IOHelper
public final class IOHelper {

    public IOHelper() {
        super();
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
