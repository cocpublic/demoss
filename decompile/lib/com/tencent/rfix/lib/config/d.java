/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/config;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

// class: com/tencent/rfix/lib/config/d
public class d {

    public d() {
        super();
    }

    public void log(@Nullable String str0, @NotNull AbsLog$Level level, @Nullable String str1) {
        switch(d$1.a[level.ordinal()]) {
            case 1: {
                RFixLog.a(str0, str1);
                return;
            }
            case 2: {
                RFixLog.b(str0, str1);
                return;
            }
            case 3: {
                RFixLog.c(str0, str1);
                return;
            }
            case 4: {
                RFixLog.d(str0, str1);
                return;
            }
            case 5: {
                RFixLog.e(str0, str1);
                return;
            }
            default: {
            }
        }
    }

    public void log(@Nullable String str0, @NotNull AbsLog$Level level, @Nullable String str1, @Nullable Throwable throwable) {
        switch(d$1.a[level.ordinal()]) {
            case 1: {
                RFixLog.a(str0, str1, throwable);
                return;
            }
            case 2: {
                RFixLog.b(str0, str1, throwable);
                return;
            }
            case 3: {
                RFixLog.c(str0, str1, throwable);
                return;
            }
            case 4: {
                RFixLog.d(str0, str1, throwable);
                return;
            }
            case 5: {
                RFixLog.e(str0, str1, throwable);
                return;
            }
            default: {
            }
        }
    }

}
