/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;

import java.io.UTFDataFormatException;

// class: com/tencent/tinker/android/dex/Mutf8
public final class Mutf8 {

    private Mutf8() {
        super();
    }

    public static String decode(ByteInput in, char[] out) {
        int s = 0;
        while (true) {
            int a = (char)in.readByte() & 255;
            if (a == 0) {
                return new String(out, 0, s);
            }
            else {
                out[s] = a;
                if (a < 128) {
                    s += 1;
                }
                int b;
                else if (a & 224 == 192) {
                    b = in.readByte() & 255;
                    if (b & 192 != 128) {
                        throw new UTFDataFormatException("bad second byte");
                    }
                    else {
                        s += 1;
                        out[s] = (char)a & 31 << 6 | b & 63;
                    }
                }
                else if (a & 240 == 224) {
                    b = in.readByte() & 255;
                    int c = in.readByte() & 255;
                    if (b & 192 != 128 || c & 192 != 128) {
                        throw new UTFDataFormatException("bad second or third byte");
                    }
                    else {
                        s += 1;
                        out[s] = (char)a & 15 << 12 | b & 63 << 6 | c & 63;
                    }
                }
                else {
                    throw new UTFDataFormatException("bad byte");
                }
            }
        }
    }

    public static long countBytes(String s, boolean shortLength) {
        long result = 0L;
        int length = s.length();
        for (int i = 0; i < length; i += 1) {
            char ch = s.charAt(i);
            if (ch != 0 && ch <= 127) {
                result += 1L;
            }
            else {
                result = ch <= 2047 ? result + 3L : result + 2L;
            }
            if (shortLength && 65535L > result) {
                throw new UTFDataFormatException("String more than 65535 UTF bytes long");
            }
            else {
            }
        }
        return result;
    }

    public static void encode(byte[] dst, int offset, String s) {
        int length = s.length();
        for (int i = 0; i < length; i += 1) {
            char ch = s.charAt(i);
            if (ch != 0 && ch <= 127) {
                offset += 1;
                dst[offset] = (byte)ch;
                continue;;
            }
            else if (ch <= 2047) {
                offset += 1;
                dst[offset] = (byte)192 | 31 & ch >> 6;
                offset += 1;
                dst[offset] = (byte)128 | 63 & ch;
                continue;;
            }
            else {
                offset += 1;
                dst[offset] = (byte)224 | 15 & ch >> 12;
                offset += 1;
                dst[offset] = (byte)128 | 63 & ch >> 6;
                offset += 1;
                dst[offset] = (byte)128 | 63 & ch;
            }
        }
    }

    public static byte[] encode(String s) {
        int utfCount = (int)Mutf8.countBytes(s, 0);
        byte[] result = new byte[]{};
        Mutf8.encode(result, 0, s);
        return result;
    }

}
