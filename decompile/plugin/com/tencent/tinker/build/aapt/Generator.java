/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;

// class: com/tencent/tinker/build/aapt/Generator
public final class Generator {
    final private static char CHARACTERS;
    final private static String FONT_FAMILY_TIMES_NEW_ROMAN;

    public Generator() {
        super();
    }

    public static String md5File(String fullFilename) {
        Object result = null;
        if (fullFilename != null) {
            Object is = null;
            try {
                BufferedInputStream stream = new BufferedInputStream(new FileInputStream(fullFilename));
                String str0 = Generator.md5File(stream);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                Throwable throwable = v_10;
                IOHelper.closeQuietly(stream);
                throw throwable;
            }
        }
        return result;
    }

    public static String md5File(InputStream inputStream) {
        Object result = null;
        if (inputStream != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] buffer = new byte[]{};
                int readCount = 0;
                while (true) {
                    readCount = inputStream.read(buffer, 0, buffer.length);
                    if (inputStream.read(buffer, 0, buffer.length) != -1) {
                        md.update(buffer, 0, readCount);
                    }
                    else {
                        String str0 = StringUtil.byteToHexString(md.digest());
                        break;;
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                Throwable throwable = v_8;
                IOHelper.closeQuietly(inputStream);
                throw throwable;
            }
        }
        return result;
    }

    static  {
        Generator.CHARACTERS = new char[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    }

}
