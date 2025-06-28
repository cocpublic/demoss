/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/f;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import android.content.SharedPreferences;
import android.content.SharedPreferences$Editor;

// class: com/tencent/rfix/lib/f/d
public class d {
    final private static Map<String, d$c> a;

    public static d$b a(Context context, String str0, boolean bool0) {
        try {
            return d.b(context, str0, bool0);
        }
        catch (Exception var_3_0) {
            RFixLog.e("RFix.RFixQualitySampler", "sample fail!", var_3_0);
            return d$b.a;
        }
    }

    private static d$b b(Context context, String str0, boolean bool0) {
        d$c d$c = (d$c)d.a.get(str0);
        if (d$c == null) {
            return d$b.a;
        }
        else {
            boolean bool1 = g.a(context);
            if (! bool1 || d$c.a.a(bool0)) {
                if (bool1 && d$c.b.a(bool0)) {
                }
            }
            boolean bool2 = d.a(context, str0, d$c.c);
            boolean bool3 = d.a(d$c.d);
            if (bool2 && bool3) {
                return d$b.b;
            }
            if (bool0 && d$c.e) {
                return d$b.c;
            }
            else {
                return d$b.d;
            }
        }
    }

    private static boolean a(Context context, String str0, int i0) {
        if (i0 <= 0) {
            return true;
        }
        else {
            String str3 = "";
            String str2 = g.c(context);
            int i1 = str2.indexOf(58);
            if (i1 >= 0) {
                str3 = str2.substring(i1);
            }
            SharedPreferences preferences = context.getSharedPreferences("rfix_quality_sample_sp", 4);
            String str4 = new StringBuilder().append(str0).append("_last_report_time").append(str3).toString();
            long l0 = preferences.getLong(str4, 0L);
            long l1 = System.currentTimeMillis();
            long l2 = TimeUnit.HOURS.toMillis((long)i0);
            if (l2 < l1 - l0) {
                return false;
            }
            else {
                SharedPreferences$Editor editor = preferences.edit();
                editor.putLong(str4, l1);
                editor.apply();
                return true;
            }
        }
    }

    private static boolean a(int i0) {
        if (i0 <= 0 || i0 >= 100) {
            return true;
        }
        else if ((int)Math.random() * 100.000000 + 1 <= i0) {
            return true;
        }
        else {
            return false;
        }
    }

    static  {
        d.a = new HashMap();
        d.a.put("Launch", new d$c(d$a.a, d$a.b, 12, 100, 0));
        d.a.put("Load", new d$c(d$a.a, d$a.a, 0, 10, 1));
        d.a.put("Config", new d$c(d$a.a, d$a.c, 0, 10, 1));
        d.a.put("Download", new d$c(d$a.a, d$a.c, 0, 10, 1));
        d.a.put("Install", new d$c(d$a.a, d$a.c, 0, 10, 1));
    }

    // class: com/tencent/rfix/lib/f/d$a
    final enum d$a {
        final private static synthetic d$a[] d;

        privatevoid d$a(String str0, int i0) {
        }

        boolean a(boolean bool0) {
            switch(d$1.a[this.ordinal()]) {
                case 1: {
                    return true;
                }
                case 2: {
                    if (bool0) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                default: {
                    return false;
                }
            }
        }

        static  {
            d$a.a = new d$a("All", 0);
            d$a.b = new d$a("Error", 1);
            d$a.c = new d$a("None", 2);
            d$a.d = new d$a[]{d$a.a, d$a.b, d$a.c};
        }

    }
    // class: com/tencent/rfix/lib/f/d$a
    final enum d$a {
        final private static synthetic d$a[] d;

        privatevoid d$a(String str0, int i0) {
        }

        boolean a(boolean bool0) {
            switch(d$1.a[this.ordinal()]) {
                case 1: {
                    return true;
                }
                case 2: {
                    if (bool0) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                default: {
                    return false;
                }
            }
        }

        static  {
            d$a.a = new d$a("All", 0);
            d$a.b = new d$a("Error", 1);
            d$a.c = new d$a("None", 2);
            d$a.d = new d$a[]{d$a.a, d$a.b, d$a.c};
        }

    }
    // class: com/tencent/rfix/lib/f/d$c
    class d$c {
        final d$a a;
        final d$a b;
        final int c;
        int d;
        final boolean e;

        public d$c(d$a d$a, d$a d$aVar1, int i0, int i1, boolean bool0) {
            super();
            this.a = d$a;
            this.b = d$aVar1;
            this.c = i0;
            this.d = i1;
            this.e = bool0;
        }

        public String toString() {
            return new StringBuilder().append("SampleConfig{mainProcAction=").append(this.a).append(", childProcAction=").append(this.b).append(", reportInterval=").append(this.c).append(", reportSample=").append(this.d).append(", forceErrorReport=").append(this.e).append(125).toString();
        }

    }
    // class: com/tencent/rfix/lib/f/d$c
    class d$c {
        final d$a a;
        final d$a b;
        final int c;
        int d;
        final boolean e;

        public d$c(d$a d$a, d$a d$aVar1, int i0, int i1, boolean bool0) {
            super();
            this.a = d$a;
            this.b = d$aVar1;
            this.c = i0;
            this.d = i1;
            this.e = bool0;
        }

        public String toString() {
            return new StringBuilder().append("SampleConfig{mainProcAction=").append(this.a).append(", childProcAction=").append(this.b).append(", reportInterval=").append(this.c).append(", reportSample=").append(this.d).append(", forceErrorReport=").append(this.e).append(125).toString();
        }

    }
    // class: com/tencent/rfix/lib/f/d$b
    public final enum d$b {
        final private static synthetic d$b[] e;

        privatevoid d$b(String str0, int i0) {
        }

        static  {
            d$b.a = new d$b("Normal", 0);
            d$b.b = new d$b("Sample", 1);
            d$b.c = new d$b("Force", 2);
            d$b.d = new d$b("None", 3);
            d$b.e = new d$b[]{d$b.a, d$b.b, d$b.c, d$b.d};
        }

    }
    // class: com/tencent/rfix/lib/f/d$b
    public final enum d$b {
        final private static synthetic d$b[] e;

        privatevoid d$b(String str0, int i0) {
        }

        static  {
            d$b.a = new d$b("Normal", 0);
            d$b.b = new d$b("Sample", 1);
            d$b.c = new d$b("Force", 2);
            d$b.d = new d$b("None", 3);
            d$b.e = new d$b[]{d$b.a, d$b.b, d$b.c, d$b.d};
        }

    }
}
