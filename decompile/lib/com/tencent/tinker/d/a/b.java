/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/d/a;


// class: com/tencent/tinker/d/a/b
public class b {

    public static void a(int i0, int i1, int i2) {
        if (i1 | i2 >= 0 || i1 <= i0 || i0 - i1 < i2) {
            throw new ArrayIndexOutOfBoundsException(i1);
        }
        else {
        }
    }

}
