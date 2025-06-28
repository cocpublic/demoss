/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/hotplug;

import java.util.Map;
import java.util.HashMap;

// class: com/tencent/tinker/loader/hotplug/a
public class a {
    private static Map<String, String> a;
    final private static int b;
    final private static int c;
    final private static int d;
    final private static int e;
    final private static int f;
    final private static int g;
    final private static int h;
    final private static int i;

    public static String a(String str0, int i0, boolean bool0) {
        String str5 = (String)a.a.get(str0);
        if (str5 != null) {
            return str5;
        }
        else {
            String str4;
            switch(i0) {
                case 1: {
                    String str2 = ActivityStubs.c;
                    int[] intArr0 = a.g;
                    int[] intArr0Var1 = a.c;
                    break;;
                }
                String str3;
                int[] intArr0Var2;
                int[] intArr0Var3;
                case 2: {
                    break;;
                }
                case 3: {
                    str4 = ActivityStubs.e;
                    v_8 = a.i;
                    intArr0Var2 = a.i;
                    intArr0Var3 = a.e;
                    break;;
                }
                default: {
                    str3 = ActivityStubs.b;
                    intArr0Var2 = a.f;
                    intArr0Var3 = a.b;
                }
            }
            int i1;
            if (bool0) {
                str4 = new StringBuilder().append(ActivityStubs.d).append("_T").toString();
                v_17 = 1;
                i1 = 1;
                goto 156;
            }
            else {
                i1 = 0;
            }
            v_27[v_28] = a.h[i1] + 1;
            int i3 = a.h[i1];
            if (i3 >= a.d[i1]) {
                a.h[i1] = 0;
                i3 = 0;
            }
            str5 = String.format(ActivityStubs.d, new Object[]{Integer.valueOf(i3)});
            a.a.put(str0, str5);
            return str5;
        }
    }

    static  {
        a.a = new HashMap();
        a.b = new int[]{10, 3};
        a.c = new int[]{10, 3};
        a.d = new int[]{10, 3};
        a.e = new int[]{10, 3};
        a.f = new int[]{0, 0};
        a.g = new int[]{0, 0};
        a.h = new int[]{0, 0};
        a.i = new int[]{0, 0};
    }

}
