/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/engine;


// class: com/tencent/rfix/lib/engine/e
public class e implements n$a {

    public e() {
        super();
    }

    public void a(String str0, String str1, Object[] objectArr0) {
        RFixLog.a(str0, super.a(str1, objectArr0));
    }

    public void b(String str0, String str1, Object[] objectArr0) {
        RFixLog.b(str0, this.a(str1, objectArr0));
    }

    public void c(String str0, String str1, Object[] objectArr0) {
        RFixLog.c(str0, this.a(str1, objectArr0));
    }

    public void d(String str0, String str1, Object[] objectArr0) {
        RFixLog.d(str0, this.a(str1, objectArr0));
    }

    public void e(String str0, String str1, Object[] objectArr0) {
        RFixLog.e(str0, this.a(str1, objectArr0));
    }

    public void a(String str0, Throwable throwable, String str1, Object[] objectArr0) {
        RFixLog.e(str0, super.a(str1, objectArr0), throwable);
    }

    private String a(String str0, Object[] objectArr0) {
        Object object = null;
        try {
            String str1 = objectArr0 == null || objectArr0.length == 0 ? String.format(str0, objectArr0) : str0;
        }
        catch (Exception var_4_0) {
        }
        return str1;
    }

}
