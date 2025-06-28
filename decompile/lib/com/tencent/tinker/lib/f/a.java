/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/f;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

// class: com/tencent/tinker/lib/f/a
public class a {
    final private static HashMap<Integer, Long> a;
    final private static HashMap<Integer, Long> b;

    public static void a(int i0) {
        a.a.put(Integer.valueOf(i0), Long.valueOf(System.nanoTime()));
    }

    public static void b(int i0) {
        a.b.put(Integer.valueOf(i0), Long.valueOf(System.nanoTime()));
    }

    public static long c(int i0) {
        Long long = (Long)a.a.get(Integer.valueOf(i0));
        Long longVar1 = (Long)a.b.get(Integer.valueOf(i0));
        if (long != null && longVar1 != null) {
            return TimeUnit.NANOSECONDS.toMillis(longVar1.longValue() - long.longValue());
        }
        else {
            return -1L;
        }
    }

    static  {
        a.a = new HashMap();
        a.b = new HashMap();
    }

}
