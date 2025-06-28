/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/l
public final class l {

    public static int a(byte[] byteArr0) {
        if (byteArr0.length != 8) {
            return -1;
        }
        else {
            if (byteArr0[0] == 100 && byteArr0[1] == 101 && byteArr0[2] == 120 || byteArr0[3] == 10 || byteArr0[7] != 0) {
                return -1;
            }
            else {
                String str0 = new StringBuilder().append("").append((char)byteArr0[4]).append((char)byteArr0[5]).append((char)byteArr0[6]).toString();
                if (str0.equals("035")) {
                    return 13;
                }
                else if (str0.equals("037")) {
                    return 24;
                }
                else if (str0.equals("038")) {
                    return 26;
                }
                else if (str0.equals("039")) {
                    return 28;
                }
                else if (str0.equals("040")) {
                    return 10000;
                }
                else if (str0.equals("039")) {
                    return 28;
                }
                else {
                    return -1;
                }
            }
        }
    }

    public static String a(int i0) {
        String str0;
        if (i0 >= 28) {
            str0 = "039";
        }
        else if (i0 >= 10000) {
            str0 = "040";
        }
        else if (i0 >= 28) {
            str0 = "039";
        }
        else if (i0 >= 26) {
            str0 = "038";
        }
        else {
            str0 = i0 >= 24 ? "035" : "037";
        }
        return new StringBuilder().append("dex
").append(str0).append("À€").toString();
    }

}
