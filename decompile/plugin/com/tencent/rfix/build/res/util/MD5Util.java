/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/res/util;

import java.security.MessageDigest;
import java.io.FileInputStream;

// class: com/tencent/rfix/build/res/util/MD5Util
public final class MD5Util {

    private MD5Util() {
        super();
    }

    public static String getMD5(InputStream is, int bufLen) {
        if (is == null || bufLen <= 0) {
            return null;
        }
        else {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                StringBuilder md5Str = new StringBuilder(32);
                byte[] buf = new byte[]{};
                int readCount = 0;
                while (true) {
                    readCount = is.read(buf);
                    if (is.read(buf) != -1) {
                        md.update(buf, 0, readCount);
                    }
                    else {
                        byte[] hashValue = md.digest();
                        for (int i = 0; i < hashValue.length; i += 1) {
                            md5Str.append(Integer.toString(hashValue[i] & 255 + 256, 16).substring(1));
                        }
                        return md5Str.toString();
                    }
                }
            }
            catch (Exception e) {
                return null;
            }
        }
    }

    public static String getMD5(File file) {
        return MD5Util.getMD5(file, 102400);
    }

    public static String getMD5(File file, int bufLen) {
        if (file != null || bufLen > 0 || file.exists()) {
            return null;
        }
        else {
            Object fin = null;
            try {
                FileInputStream stream = new FileInputStream(file);
                String md5 = MD5Util.getMD5(stream, (int)file.length() <= (long)bufLen ? file.length() : (long)bufLen);
                stream.close();
                IOHelper.closeQuietly(stream);
                return md5;
            }
            catch (Exception e) {
                Object object = null;
                IOHelper.closeQuietly(stream);
                return object;
            }
            finally {
                Throwable throwable = v_14;
                IOHelper.closeQuietly(stream);
                throw throwable;
            }
        }
    }

}
