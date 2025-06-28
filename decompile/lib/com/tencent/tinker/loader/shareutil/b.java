/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;


// class: com/tencent/tinker/loader/shareutil/b
public class b {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;

    public b(String str0, String str1, String str2, String str3, String str4) {
        super();
        this.a = str0;
        this.b = str1;
        this.c = str3;
        this.d = str4;
        this.e = str2;
    }

    public static void a(String str0, ArrayList<b> list) {
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
                        String[] stringArr0Var2 = str1.split(",", 5);
                        if (stringArr0Var2 != null) {
                            if (stringArr0Var2.length < 5) {
                                continue;;
                            }
                            else {
                                String str2 = stringArr0Var2[0].trim();
                                String str3 = stringArr0Var2[1].trim();
                                String str4 = stringArr0Var2[2].trim();
                                String str5 = stringArr0Var2[3].trim();
                                String str6 = stringArr0Var2[4].trim();
                                b b = new b(str2, str4, str3, str5, str6);
                                list.add(b);
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean a(b b) {
        if (b == null) {
            return false;
        }
        else {
            String str0 = b.a;
            String str1 = b.b;
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
        buffer.append(this.e);
        buffer.append(",");
        buffer.append(this.b);
        buffer.append(",");
        buffer.append(this.c);
        buffer.append(",");
        buffer.append(this.d);
        return buffer.toString();
    }

}
