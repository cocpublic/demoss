/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/bsdiff;

import java.io.ByteArrayOutputStream;

// class: com/tencent/tinker/bsdiff/BSUtil
public class BSUtil {
    final public static int HEADER_SIZE;
    final public static int BUFFER_SIZE;

    public BSUtil() {
        super();
    }

    final public static boolean readFromStream(InputStream in, byte[] buf, int offset, int len) {
        for (int totalBytesRead = 0; totalBytesRead < len; totalBytesRead += bytesRead) {
            int bytesRead = in.read(buf, offset + totalBytesRead, len - totalBytesRead);
            if (bytesRead < 0) {
                return false;
            }
            else {
            }
        }
        return true;
    }

    public static byte[] inputStreamToByte(InputStream in) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[]{};
        int count = -1;
        while (true) {
            count = in.read(data, 0, 8192);
            if (in.read(data, 0, 8192) != -1) {
                outStream.write(data, 0, count);
            }
            else {
                Object object = null;
                return outStream.toByteArray();
            }
        }
    }

}
