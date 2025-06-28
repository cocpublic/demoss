/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.zip.CRC32;

// class: com/tencent/tinker/commons/util/DigestUtil
public final class DigestUtil {

    public static long getCRC32(File file) {
        Object is = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
            long l0 = DigestUtil.getCRC32(stream);
            IOHelper.closeQuietly(stream);
            return l0;
        }
        finally {
            Throwable throwable = v_8;
            IOHelper.closeQuietly(stream);
            throw throwable;
        }
    }

    public static long getCRC32(byte[] data, int off, int length) {
        CRC32 crc32 = new CRC32();
        crc32.update(data, off, length);
        return crc32.getValue();
    }

    public static long getCRC32(InputStream is) {
        CRC32 crc32 = new CRC32();
        byte[] buffer = new byte[]{};
        int bytesRead = 0;
        while (true) {
            bytesRead = is.read(buffer);
            if (is.read(buffer) > 0) {
                crc32.update(buffer, 0, bytesRead);
            }
            else {
                return crc32.getValue();
            }
        }
    }

    private DigestUtil() {
        super();
        throw new UnsupportedOperationException();
    }

}
