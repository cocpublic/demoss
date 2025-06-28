/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a/b;


// class: com/tencent/tinker/a/a/b/c
public final class c {

    public static int a(byte byte0, byte byte1) {
        if (byte0 == byte1) {
            return 0;
        }
        else {
            int i0 = byte0 & 255;
            int i1 = byte1 & 255;
            if (i0 < i1) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    public static int a(short short0, short short1) {
        if (short0 == short1) {
            return 0;
        }
        else {
            int i0 = short0 & 65535;
            int i1 = short1 & 65535;
            if (i0 < i1) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    public static int a(int i0, int i1) {
        if (i0 == i1) {
            return 0;
        }
        else {
            long l0 = (long)i0 & 4294967295L;
            long l1 = (long)i1 & 4294967295L;
            if (l1 < l0) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    public static int a(byte[] byteArr0, byte[] byteArr0Var1) {
        if (byteArr0.length < byteArr0Var1.length) {
            return -1;
        }
        else if (byteArr0.length > byteArr0Var1.length) {
            return 1;
        }
        else {
            for (int i2 = 0; i2 < byteArr0.length; i2 += 1) {
                int i3 = c.a(byteArr0[i2], byteArr0Var1[i2]);
                if (i3 != 0) {
                    return i3;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static int a(short[] shortArr0, short[] shortArr0Var1) {
        if (shortArr0.length < shortArr0Var1.length) {
            return -1;
        }
        else if (shortArr0.length > shortArr0Var1.length) {
            return 1;
        }
        else {
            for (int i2 = 0; i2 < shortArr0.length; i2 += 1) {
                int i3 = c.a(shortArr0[i2], shortArr0Var1[i2]);
                if (i3 != 0) {
                    return i3;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static int a(int[] intArr0, int[] intArr0Var1) {
        if (intArr0.length < intArr0Var1.length) {
            return -1;
        }
        else if (intArr0.length > intArr0Var1.length) {
            return 1;
        }
        else {
            for (int i2 = 0; i2 < intArr0.length; i2 += 1) {
                int i3 = c.a(intArr0[i2], intArr0Var1[i2]);
                if (i3 != 0) {
                    return i3;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static int b(int i0, int i1) {
        if (i0 == i1) {
            return 0;
        }
        else if (i0 < i1) {
            return -1;
        }
        else {
            return 1;
        }
    }

    public static int b(int[] intArr0, int[] intArr0Var1) {
        if (intArr0.length < intArr0Var1.length) {
            return -1;
        }
        else if (intArr0.length > intArr0Var1.length) {
            return 1;
        }
        else {
            for (int i2 = 0; i2 < intArr0.length; i2 += 1) {
                int i3 = c.b(intArr0[i2], intArr0Var1[i2]);
                if (i3 != 0) {
                    return i3;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static <T> int a(T[] comparableArr0, T[] comparableArr0Var1) {
        if (comparableArr0.length < comparableArr0Var1.length) {
            return -1;
        }
        else if (comparableArr0.length > comparableArr0Var1.length) {
            return 1;
        }
        else {
            for (int i2 = 0; i2 < comparableArr0.length; i2 += 1) {
                int i3 = comparableArr0[i2].compareTo(comparableArr0Var1[i2]);
                if (i3 != 0) {
                    return i3;
                }
                else {
                }
            }
            return 0;
        }
    }

}
