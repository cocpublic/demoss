/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/atta;

import androidx.annotation.Keep;

// class: com/tencent/rfix/lib/atta/RFixATTASwitch
public class RFixATTASwitch {
    private static volatile boolean a;

    public RFixATTASwitch() {
        super();
    }

    @Keep
    public static void disableBelowM() {
        RFixLog.b("RFix.RFixATTASwitch", "disableBelowM");
        RFixATTASwitch.a = true;
    }

    public static boolean a() {
        if (b.a()) {
            return false;
        }
        else if (Build$VERSION.SDK_INT <= 23) {
            if (RFixATTASwitch.a) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }

    static  {
        RFixATTASwitch.a = false;
    }

}
