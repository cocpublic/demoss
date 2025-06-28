/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import java.util.ArrayList;

// class: com/tencent/tinker/lib/c/f
public class f {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;

    public f() {
        super();
    }

    public String toString() {
        return new StringBuilder().append("CustomDiffPatchInfo{relativePath='").append(this.a).append(39).append(", diffType='").append(this.b).append(39).append(", oldMd5='").append(this.c).append(39).append(", newMd5='").append(this.d).append(39).append(", patchMd5='").append(this.e).append(39).append(125).toString();
    }

    public static List<f> a(String str0) {
        ArrayList list = new ArrayList();
        if (str0 != null && str0.isEmpty()) {
            String[] stringArr0 = str0.split("
");
            for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                String str1 = stringArr0[i1];
                n.b("Tinker.CustomDiffPatchInfo", new StringBuilder().append("parseDiffPatchInfo ").append(str1).toString(), new Object[]{});
                if (str1 != null) {
                    if (str1.length() <= 0) {
                        continue;;
                    }
                    else {
                        String[] stringArr0Var2 = str1.split(",", 5);
                        if (stringArr0Var2.length < 5) {
                            continue;;
                        }
                        else {
                            String str2 = stringArr0Var2[0].trim();
                            String str3 = stringArr0Var2[1].trim();
                            String str4 = stringArr0Var2[2].trim();
                            String str5 = stringArr0Var2[3].trim();
                            String str6 = stringArr0Var2[4].trim();
                            f f = new f();
                            f.a = str2;
                            f.b = str3;
                            f.c = str4;
                            f.d = str5;
                            f.e = str6;
                            list.add(f);
                        }
                    }
                }
            }
        }
        return list;
    }

}
