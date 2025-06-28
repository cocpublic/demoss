/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex/util;


// class: com/tencent/tinker/android/dex/util/CompareUtils
public final class CompareUtils {

    private CompareUtils() {
        super();
    }

    public static int uCompare(byte ubyteA, byte ubyteB) {
        if (ubyteA == ubyteB) {
            return 0;
        }
        else {
            int a = ubyteA & 255;
            int b = ubyteB & 255;
            if (a < b) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    public static int uCompare(short ushortA, short ushortB) {
        if (ushortA == ushortB) {
            return 0;
        }
        else {
            int a = ushortA & 65535;
            int b = ushortB & 65535;
            if (a < b) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    public static int uCompare(int uintA, int uintB) {
        if (uintA == uintB) {
            return 0;
        }
        else {
            long a = (long)uintA & 4294967295L;
            long b = (long)uintB & 4294967295L;
            if (b < a) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    public static int uArrCompare(byte[] ubyteArrA, byte[] ubyteArrB) {
        if (ubyteArrA.length < ubyteArrB.length) {
            return -1;
        }
        else if (ubyteArrA.length > ubyteArrB.length) {
            return 1;
        }
        else {
            for (int i = 0; i < ubyteArrA.length; i += 1) {
                int res = CompareUtils.uCompare(ubyteArrA[i], ubyteArrB[i]);
                if (res != 0) {
                    return res;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static int uArrCompare(short[] ushortArrA, short[] ushortArrB) {
        if (ushortArrA.length < ushortArrB.length) {
            return -1;
        }
        else if (ushortArrA.length > ushortArrB.length) {
            return 1;
        }
        else {
            for (int i = 0; i < ushortArrA.length; i += 1) {
                int res = CompareUtils.uCompare(ushortArrA[i], ushortArrB[i]);
                if (res != 0) {
                    return res;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static int uArrCompare(int[] uintArrA, int[] uintArrB) {
        if (uintArrA.length < uintArrB.length) {
            return -1;
        }
        else if (uintArrA.length > uintArrB.length) {
            return 1;
        }
        else {
            for (int i = 0; i < uintArrA.length; i += 1) {
                int res = CompareUtils.uCompare(uintArrA[i], uintArrB[i]);
                if (res != 0) {
                    return res;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static int sCompare(byte sbyteA, byte sbyteB) {
        if (sbyteA == sbyteB) {
            return 0;
        }
        else if (sbyteA < sbyteB) {
            return -1;
        }
        else {
            return 1;
        }
    }

    public static int sCompare(short sshortA, short sshortB) {
        if (sshortA == sshortB) {
            return 0;
        }
        else if (sshortA < sshortB) {
            return -1;
        }
        else {
            return 1;
        }
    }

    public static int sCompare(int sintA, int sintB) {
        if (sintA == sintB) {
            return 0;
        }
        else if (sintA < sintB) {
            return -1;
        }
        else {
            return 1;
        }
    }

    public static int sCompare(long slongA, long slongA) {
        if (slongB == slongA) {
            return 0;
        }
        else if (slongB < slongA) {
            return -1;
        }
        else {
            return 1;
        }
    }

    public static int sArrCompare(byte[] sbyteArrA, byte[] sbyteArrB) {
        if (sbyteArrA.length < sbyteArrB.length) {
            return -1;
        }
        else if (sbyteArrA.length > sbyteArrB.length) {
            return 1;
        }
        else {
            for (int i = 0; i < sbyteArrA.length; i += 1) {
                int res = CompareUtils.sCompare(sbyteArrA[i], sbyteArrB[i]);
                if (res != 0) {
                    return res;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static int sArrCompare(short[] sshortArrA, short[] sshortArrB) {
        if (sshortArrA.length < sshortArrB.length) {
            return -1;
        }
        else if (sshortArrA.length > sshortArrB.length) {
            return 1;
        }
        else {
            for (int i = 0; i < sshortArrA.length; i += 1) {
                int res = CompareUtils.sCompare(sshortArrA[i], sshortArrB[i]);
                if (res != 0) {
                    return res;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static int sArrCompare(int[] sintArrA, int[] sintArrB) {
        if (sintArrA.length < sintArrB.length) {
            return -1;
        }
        else if (sintArrA.length > sintArrB.length) {
            return 1;
        }
        else {
            for (int i = 0; i < sintArrA.length; i += 1) {
                int res = CompareUtils.sCompare(sintArrA[i], sintArrB[i]);
                if (res != 0) {
                    return res;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static int sArrCompare(long[] slongArrA, long[] slongArrB) {
        if (slongArrA.length < slongArrB.length) {
            return -1;
        }
        else if (slongArrA.length > slongArrB.length) {
            return 1;
        }
        else {
            for (int i = 0; i < slongArrA.length; i += 1) {
                int res = CompareUtils.sCompare(slongArrA[i], slongArrB[i]);
                if (res != 0) {
                    return res;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static <T> int aArrCompare(T[] aArrA, T[] aArrB) {
        if (aArrA.length < aArrB.length) {
            return -1;
        }
        else if (aArrA.length > aArrB.length) {
            return 1;
        }
        else {
            for (int i = 0; i < aArrA.length; i += 1) {
                int res = aArrA[i].compareTo(aArrB[i]);
                if (res != 0) {
                    return res;
                }
                else {
                }
            }
            return 0;
        }
    }

    public static <T> int aArrCompare(T[] aArrA, T[] aArrB, Comparator<T> cmptor) {
        if (aArrA.length < aArrB.length) {
            return -1;
        }
        else if (aArrA.length > aArrB.length) {
            return 1;
        }
        else {
            for (int i = 0; i < aArrA.length; i += 1) {
                int res = cmptor.compare(aArrA[i], aArrB[i]);
                if (res != 0) {
                    return res;
                }
                else {
                }
            }
            return 0;
        }
    }

}
