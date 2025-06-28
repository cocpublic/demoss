/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import com.tencent.tinker.loader.k;

// class: com/tencent/tinker/loader/shareutil/d
public class d {
    final public String a;
    final public String b;
    final public String c;
    final public String d;
    final public String e;
    final public String f;
    final public String g;
    final public String h;
    final public boolean i;
    final public String j;

    public d(String str0, String str1, String str2, String str3, String str4, String str5, String str6, String str7) {
        super();
        this.a = str0;
        this.g = str1;
        this.b = str2;
        this.c = str3;
        this.f = str4;
        this.d = str5;
        this.e = str6;
        this.h = str7;
        if (str7.equals("jar")) {
            this.i = true;
            this.j = h.h(str0) ? str0 : new StringBuilder().append(str0).append(".jar").toString();
            return;
        }
        else if (str7.equals("raw")) {
            this.i = false;
            this.j = str0;
            return;
        }
        else {
            throw new k(new StringBuilder().append("can't recognize dex mode:").append(str7).toString());
        }
    }

    public static void a(String str0, ArrayList<d> list) {
        if (str0 == null || str0.length() == 0) {
        }
        else {
            String[] stringArr0 = str0.split("
");
            for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                String str1 = stringArr0[i1];
                if (str1 != null) {
                    if (str1.length() <= 0) {
                        continue;;
                    }
                    else {
                        String[] stringArr0Var2 = str1.split(",", 8);
                        if (stringArr0Var2 != null) {
                            if (stringArr0Var2.length < 8) {
                                continue;;
                            }
                            else {
                                String str2 = stringArr0Var2[0].trim();
                                String str3 = stringArr0Var2[1].trim();
                                String str4 = stringArr0Var2[2].trim();
                                String str5 = stringArr0Var2[3].trim();
                                String str6 = stringArr0Var2[4].trim();
                                String str7 = stringArr0Var2[5].trim();
                                String str8 = stringArr0Var2[6].trim();
                                String str9 = stringArr0Var2[7].trim();
                                d d = new d(str2, str3, str4, str5, str6, str7, str8, str9);
                                list.add(d);
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean a(d d) {
        if (d == null) {
            return false;
        }
        else {
            String str0 = d.a;
            String str1 = m.a() ? d.b : d.c;
            if (str0 != null && str0.length() > 0 || str1 != null || str1.length() != 32) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.a);
        buffer.append(",");
        buffer.append(this.g);
        buffer.append(",");
        buffer.append(this.b);
        buffer.append(",");
        buffer.append(this.c);
        buffer.append(",");
        buffer.append(this.d);
        buffer.append(",");
        buffer.append(this.e);
        buffer.append(",");
        buffer.append(this.f);
        buffer.append(",");
        buffer.append(this.h);
        return buffer.toString();
    }

}
