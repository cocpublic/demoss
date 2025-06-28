/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/h;


// class: com/tencent/rfix/lib/h/b
public class b {
    private static volatile boolean a;

    public static boolean a() {
        return b.a;
    }

    static  {
        b.a = false;
    }

}
