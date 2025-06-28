/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;


// class: com/tencent/tinker/loader/shareutil/a
public class a {
    public String a;
    public String b;
    public String c;

    public a(String str0, String str1, String str2) {
        super();
        this.b = str1;
        this.c = str2;
        this.a = str0;
    }

    public static void a(String str0, ArrayList<a> list) {
        if (str0 == null || list == null) {
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
                        String[] stringArr0Var2 = str1.split(",", 4);
                        if (stringArr0Var2 != null) {
                            if (stringArr0Var2.length < 3) {
                                continue;;
                            }
                            else {
                                String str2 = stringArr0Var2[0].trim();
                                String str3 = stringArr0Var2[1].trim();
                                String str4 = stringArr0Var2[2].trim();
                                a a = new a(str3, str2, str4);
                                list.add(a);
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean a(a a) {
        if (a == null) {
            return false;
        }
        else {
            String str0 = a.b;
            String str1 = a.c;
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
        buffer.append(this.b);
        buffer.append(",");
        buffer.append(this.a);
        buffer.append(",");
        buffer.append(this.c);
        return buffer.toString();
    }

}
