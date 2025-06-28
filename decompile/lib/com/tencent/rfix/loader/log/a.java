/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/log;


// class: com/tencent/rfix/loader/log/a
public class a implements IRFixLog {

    public a() {
        super();
    }

    public void v(String str0, String str1) {
        Log.v(str0, str1);
    }

    public void d(String str0, String str1) {
        Log.d(str0, str1);
    }

    public void i(String str0, String str1) {
        Log.i(str0, str1);
    }

    public void w(String str0, String str1) {
        Log.w(str0, str1);
    }

    public void w(String str0, String str1, Throwable throwable) {
        Log.w(str0, str1, throwable);
    }

    public void e(String str0, String str1) {
        Log.e(str0, str1);
    }

    public void e(String str0, String str1, Throwable throwable) {
        Log.e(str0, str1, throwable);
    }

}
