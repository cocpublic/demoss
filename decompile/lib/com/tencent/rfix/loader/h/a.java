/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/h;


// class: com/tencent/rfix/loader/h/a
public class a {

    public static void a(HttpURLConnection connection) {
        try {
            if (connection != null) {
                connection.disconnect();
            }
        }
        catch (Throwable var_1_0) {
        }
    }

}
