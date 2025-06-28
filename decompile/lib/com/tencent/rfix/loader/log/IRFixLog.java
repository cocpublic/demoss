/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/log;

import androidx.annotation.Keep;

// class: com/tencent/rfix/loader/log/IRFixLog
@Keep
public interface IRFixLog {

    void v(String p0, String p1);

    void d(String p0, String p1);

    void i(String p0, String p1);

    void w(String p0, String p1);

    void w(String p0, String p1, Throwable p2);

    void e(String p0, String p1);

    void e(String p0, String p1, Throwable p2);

}
