/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/f;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

// class: com/tencent/rfix/loader/f/b
public class b {
    final private static HashMap<a, Long> a;
    final private static HashMap<a, Long> b;
    final private static HashMap<a, Long> c;

    public static void a(a a) {
        b.a.put(a, Long.valueOf(System.nanoTime()));
    }

    public static void b(a a) {
        b.b.put(a, Long.valueOf(System.nanoTime()));
    }

    public static void a(a a, long l1) {
        b.c.put(a, Long.valueOf(l1));
    }

    public static long c(a a) {
        Long long = (Long)b.a.get(a);
        Long longVar1 = (Long)b.b.get(a);
        if (long != null && longVar1 != null) {
            return TimeUnit.NANOSECONDS.toMillis(longVar1.longValue() - long.longValue());
        }
        else {
            Long longVar2 = (Long)b.c.get(a);
            if (longVar2 != null) {
                return longVar2.longValue();
            }
            else {
                return -1L;
            }
        }
    }

    static  {
        b.a = new HashMap();
        b.b = new HashMap();
        b.c = new HashMap();
    }

}
