/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/a;

import com.tencent.rfix.loader.c.b;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

// class: com/tencent/rfix/loader/a/b
public class b {
    public int a;
    public int b;
    public List<Long> c;

    public b(Context context, String str0) {
        super(context, str0);
    }

    public void b() {
        super.b();
        this.a = this.p.a("p1_count", 0);
        this.b = this.p.a("p2_count", 0);
        this.c = b.a(this.p.a("p3_crash_times", ""));
        RFixLog.c("RFix.RFixSafeModeInfo", new StringBuilder().append("loadStoreInfo ").append(this).toString());
    }

    public void c() {
        this.p.b("p1_count", this.a);
        this.p.b("p2_count", this.b);
        this.p.b("p3_crash_times", b.a(this.c));
        super.c();
        RFixLog.c("RFix.RFixSafeModeInfo", new StringBuilder().append("saveStoreInfo ").append(this).toString());
    }

    public String toString() {
        return new StringBuilder().append("RFixSafeModeInfo{p1Count=").append(this.a).append(", p2Count=").append(this.b).append(", p3CrashTimes=").append(this.c).append(125).toString();
    }

    private static String a(List<Long> list) {
        StringBuilder builder = new StringBuilder();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Long long = (Long)iterator.next();
            if (builder.length() != 0) {
                builder.append(44);
            }
            builder.append(long);
        }
        return builder.toString();
    }

    private static List<Long> a(String str0) {
        ArrayList list = new ArrayList();
        if (TextUtils.isEmpty(str0)) {
            return list;
        }
        else {
            String[] stringArr0 = str0.split(",");
            if (stringArr0.length == 0) {
                return list;
            }
            else {
                for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                    String str1 = stringArr0[i1];
                    if (TextUtils.isEmpty(str1)) {
                        continue;;
                    }
                    else {
                        list.add(Long.valueOf(Long.parseLong(str1)));
                    }
                }
                Collections.sort(list);
                return list;
            }
        }
    }

}
