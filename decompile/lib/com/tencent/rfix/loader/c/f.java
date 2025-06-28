/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/c;


// class: com/tencent/rfix/loader/c/f
public class f implements c {
    private static c a;

    public f() {
        super();
    }

    public static c a() {
        return f.a;
    }

    public b a(Context context, String str0) {
        return new d(context, str0);
    }

    static  {
        f.a = new f();
    }

}
