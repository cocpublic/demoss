/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dx/util;


// class: com/tencent/tinker/android/dx/util/Hex
public final class Hex {

    private Hex() {
        super();
    }

    public static String u8(long v) {
        char[] result = new char[]{};
        for (int i = 0; i < 16; i += 1) {
            result[15 - i] = Character.forDigit((int)v & 15, 16);
            v >>= 4;
        }
        return new String(result);
    }

    public static String u4(int v) {
        char[] result = new char[]{};
        for (int i = 0; i < 8; i += 1) {
            result[7 - i] = Character.forDigit(v & 15, 16);
            v >>= 4;
        }
        return new String(result);
    }

    public static String u3(int v) {
        char[] result = new char[]{};
        for (int i = 0; i < 6; i += 1) {
            result[5 - i] = Character.forDigit(v & 15, 16);
            v >>= 4;
        }
        return new String(result);
    }

    public static String u2(int v) {
        char[] result = new char[]{};
        for (int i = 0; i < 4; i += 1) {
            result[3 - i] = Character.forDigit(v & 15, 16);
            v >>= 4;
        }
        return new String(result);
    }

    public static String u2or4(int v) {
        if (v == (char)v) {
            return Hex.u2(v);
        }
        else {
            return Hex.u4(v);
        }
    }

    public static String u1(int v) {
        char[] result = new char[]{};
        for (int i = 0; i < 2; i += 1) {
            result[1 - i] = Character.forDigit(v & 15, 16);
            v >>= 4;
        }
        return new String(result);
    }

    public static String uNibble(int v) {
        char[] result = new char[]{};
        result[0] = Character.forDigit(v & 15, 16);
        return new String(result);
    }

    public static String s8(long v) {
        char[] result = new char[]{};
        if (0L < v) {
            result[0] = 45;
            v = - v;
        }
        else {
            result[0] = 43;
        }
        for (int i = 0; i < 16; i += 1) {
            result[16 - i] = Character.forDigit((int)v & 15, 16);
            v >>= 4;
        }
        return new String(result);
    }

    public static String s4(int v) {
        char[] result = new char[]{};
        if (v < 0) {
            result[0] = 45;
            v = - v;
        }
        else {
            result[0] = 43;
        }
        for (int i = 0; i < 8; i += 1) {
            result[8 - i] = Character.forDigit(v & 15, 16);
            v >>= 4;
        }
        return new String(result);
    }

    public static String s2(int v) {
        char[] result = new char[]{};
        if (v < 0) {
            result[0] = 45;
            v = - v;
        }
        else {
            result[0] = 43;
        }
        for (int i = 0; i < 4; i += 1) {
            result[4 - i] = Character.forDigit(v & 15, 16);
            v >>= 4;
        }
        return new String(result);
    }

    public static String s1(int v) {
        char[] result = new char[]{};
        if (v < 0) {
            result[0] = 45;
            v = - v;
        }
        else {
            result[0] = 43;
        }
        for (int i = 0; i < 2; i += 1) {
            result[2 - i] = Character.forDigit(v & 15, 16);
            v >>= 4;
        }
        return new String(result);
    }

    public static String dump(byte[] arr, int offset, int length, int outOffset, int bpl, int addressLength) {
        int end = offset + length;
        if (offset | length | end < 0 || end > arr.length) {
            throw new IndexOutOfBoundsException(new StringBuilder().append("arr.length ").append(arr.length).append("; ").append(offset).append("..!").append(end).toString());
        }
        else if (outOffset < 0) {
            throw new IllegalArgumentException("outOffset < 0");
        }
        else if (length == 0) {
            return "";
        }
        else {
            StringBuffer sb = new StringBuffer(length * 4 + 6);
            int col = 0;
            while (length > 0) {
                if (col == 0) {
                    switch(addressLength) {
                        String astr;
                        case 2: {
                            astr = Hex.u1(outOffset);
                            break;;
                        }
                        case 4: {
                            astr = Hex.u2(outOffset);
                            break;;
                        }
                        case 6: {
                            astr = Hex.u3(outOffset);
                            break;;
                        }
                        default: {
                            astr = Hex.u4(outOffset);
                        }
                    }
                    sb.append(astr);
                    sb.append(": ");
                    goto 219;
                }
                else if (col & 1 == 0) {
                    sb.append(32);
                }
                sb.append(Hex.u1(arr[offset]));
                outOffset += 1;
                offset += 1;
                col += 1;
                if (col == bpl) {
                    sb.append(10);
                    col = 0;
                }
                length += 255;
            }
            if (col != 0) {
                sb.append(10);
            }
            return sb.toString();
        }
    }

    public static String toHexString(byte[] ubytes) {
        StringBuilder strBuilder = new StringBuilder(ubytes.length << 1);
        for (int i1 = 0; i1 < ubytes.length; i1 += 1) {
            byte b = ubytes[i1];
            strBuilder.append(Hex.u1(b));
        }
        return strBuilder.toString();
    }

}
