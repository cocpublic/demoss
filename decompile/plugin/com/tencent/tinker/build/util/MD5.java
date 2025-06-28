/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/util;

import java.security.MessageDigest;
import java.io.File;
import java.io.FileInputStream;

// class: com/tencent/tinker/build/util/MD5
public final class MD5 {

    private MD5() {
        super();
    }

    public static String getMessageDigest(byte[] buffer) {
        char[] hexDigits = new char[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            char[] str = new char[]{};
            int k = 0;
            for (int i = 0; i < md.length; i += 1) {
                byte byte0 = md[i];
                k += 1;
                str[k] = hexDigits[byte0 >>> 4 & 15];
                k += 1;
                str[k] = hexDigits[byte0 & 15];
            }
            return new String(str);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static byte[] getRawDigest(byte[] buffer) {
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            return mdTemp.digest();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String getMD5(InputStream is, int bufLen, int offset, int length) {
        return MD5.getMD5ExtendBytes(is, bufLen, offset, length, null);
    }

    public static String getMD5ExtendBytes(InputStream is, int bufLen, int offset, int length, byte[] extendBytes) {
        if (is != null && bufLen > 0 || offset >= 0 || length <= 0) {
            return null;
        }
        else {
            try {
                long skipLen = is.skip((long)offset);
                if ((long)offset < skipLen) {
                    return null;
                }
                else {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    StringBuilder md5Str = new StringBuilder(32);
                    byte[] buf = new byte[]{};
                    int readCount = 0;
                    int totalRead = 0;
                    while (is.read(buf) != -1) {
                        readCount = is.read(buf);
                        if (is.read(buf) != -1 && totalRead < length) {
                            if (totalRead + readCount <= length) {
                                md.update(buf, 0, readCount);
                                totalRead += readCount;
                                continue;;
                            }
                            else {
                                md.update(buf, 0, length - totalRead);
                                totalRead = length;
                                continue;;
                            }
                        }
                    }
                    if (extendBytes != null && extendBytes.length > 0) {
                        md.update(extendBytes);
                    }
                    byte[] hashValue = md.digest();
                    for (int i = 0; i < hashValue.length; i += 1) {
                        md5Str.append(Integer.toString(hashValue[i] & 255 + 256, 16).substring(1));
                    }
                    return md5Str.toString();
                }
            }
            catch (Exception e) {
                return null;
            }
        }
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

    public static String getMD5(String file) {
        if (file == null) {
            return null;
        }
        else {
            File f = new File(file);
            if (f.exists()) {
                return MD5.getMD5(f, 102400);
            }
            else {
                return null;
            }
        }
    }

    public static String getMD5(File file) {
        return MD5.getMD5(file, 102400);
    }

    public static String getMD5(File file, int bufLen) {
        if (file != null || bufLen > 0 || file.exists()) {
            return null;
        }
        else {
            Object fin = null;
            try {
                FileInputStream stream = new FileInputStream(file);
                String md5 = MD5.getMD5(stream, (int)file.length() <= (long)bufLen ? file.length() : (long)bufLen);
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

    public static String getMD5(String file, int offset, int length) {
        if (file == null) {
            return null;
        }
        else {
            File f = new File(file);
            if (f.exists()) {
                return MD5.getMD5(f, offset, length);
            }
            else {
                return null;
            }
        }
    }

    public static String getMD5(File file, int offset, int length) {
        if (file != null && file.exists() || offset >= 0 || length <= 0) {
            return null;
        }
        else {
            Object fin = null;
            try {
                FileInputStream stream = new FileInputStream(file);
                String md5 = MD5.getMD5(stream, 102400, offset, length);
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
                Throwable throwable = v_18;
                IOHelper.closeQuietly(stream);
                throw throwable;
            }
        }
    }

    public static String getMD5ExtendBytes(File file, int offset, int length, byte[] extend) {
        if (file != null && file.exists() || offset >= 0 || length <= 0) {
            return null;
        }
        else {
            Object fin = null;
            try {
                FileInputStream stream = new FileInputStream(file);
                String md5 = MD5.getMD5ExtendBytes(stream, 102400, offset, length, extend);
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
                Throwable throwable = v_19;
                IOHelper.closeQuietly(stream);
                throw throwable;
            }
        }
    }

}
