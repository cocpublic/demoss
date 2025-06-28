/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Iterator;
import java.util.Set;
import com.tencent.tinker.loader.k;

// class: com/tencent/tinker/loader/shareutil/k
public class k {
    public String a;
    public String b;
    public ArrayList<String> c;
    public ArrayList<String> d;
    public ArrayList<String> e;
    public HashMap<String, File> f;
    public ArrayList<String> g;
    public HashMap<String, k$a> h;
    public HashSet<Pattern> i;

    public k() {
        super();
        this.a = null;
        this.b = null;
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new HashMap();
        this.g = new ArrayList();
        this.h = new HashMap();
        this.i = new HashSet();
    }

    public static void a(String str0, k k) {
        if (str0 == null || str0.length() == 0) {
        }
        else {
            String[] stringArr0 = str0.split("
");
            for (int i0 = 0; i0 < stringArr0.length; i0 += 1) {
                String str1 = stringArr0[i0];
                if (str1 != null ? str1.startsWith("resources_out.zip") : str1.length() <= 0) {
                    String[] stringArr0Var1 = str1.split(",", 3);
                    k.a = stringArr0Var1[1];
                    k.b = stringArr0Var1[2];
                    continue;;
                }
                else if (str1.startsWith("pattern:")) {
                    String[] stringArr0Var2 = str1.split(":", 2);
                    for (int i1 = Integer.parseInt(stringArr0Var2[1]); i1 > 0; i1 += 255) {
                        k.i.add(k.a(stringArr0[i0 + 1]));
                        i0 += 1;
                    }
                    continue;;
                }
                else if (str1.startsWith("add:")) {
                    String[] stringArr0Var3 = str1.split(":", 2);
                    for (int i2 = Integer.parseInt(stringArr0Var3[1]); i2 > 0; i2 += 255) {
                        k.c.add(stringArr0[i0 + 1]);
                        i0 += 1;
                    }
                    continue;;
                }
                else if (str1.startsWith("modify:")) {
                    String[] stringArr0Var4 = str1.split(":", 2);
                    for (int i3 = Integer.parseInt(stringArr0Var4[1]); i3 > 0; i3 += 255) {
                        k.e.add(stringArr0[i0 + 1]);
                        i0 += 1;
                    }
                    continue;;
                }
                else if (str1.startsWith("large modify:")) {
                    String[] stringArr0Var5 = str1.split(":", 2);
                    for (int i4 = Integer.parseInt(stringArr0Var5[1]); i4 > 0; i4 += 255) {
                        String str2 = stringArr0[i0 + 1];
                        String[] stringArr0Var6 = str2.split(",", 3);
                        String str3 = stringArr0Var6[0];
                        k$a k$a = new k$a();
                        k$a.a = stringArr0Var6[1];
                        k$a.b = Long.parseLong(stringArr0Var6[2]);
                        k.g.add(str3);
                        k.h.put(str3, k$a);
                        i0 += 1;
                    }
                    continue;;
                }
                else if (str1.startsWith("delete:")) {
                    String[] stringArr0Var7 = str1.split(":", 2);
                    for (int i5 = Integer.parseInt(stringArr0Var7[1]); i5 > 0; i5 += 255) {
                        k.d.add(stringArr0[i0 + 1]);
                        i0 += 1;
                    }
                    continue;;
                }
                else if (str1.startsWith("store:")) {
                    String[] stringArr0Var8 = str1.split(":", 2);
                    for (int i6 = Integer.parseInt(stringArr0Var8[1]); i6 > 0; i6 += 255) {
                        k.f.put(stringArr0[i0 + 1], null);
                        i0 += 1;
                    }
                }
            }
        }
    }

    public static boolean a(HashSet<Pattern> set, String str0) {
        if (set.isEmpty()) {
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Pattern pattern = (Pattern)iterator.next();
                if (pattern.matcher(str0).matches()) {
                    return true;
                }
                else {
                    continue;;
                }
            }
        }
        return false;
    }

    public static boolean a(k k) {
        if (k == null) {
            return false;
        }
        else {
            String str0 = k.b;
            if (str0 == null || str0.length() != 32) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    private static Pattern a(String str0) {
        if (str0.contains(".")) {
            str0 = str0.replaceAll("\.", "\\.");
        }
        if (str0.contains("?")) {
            str0 = str0.replaceAll("\?", "\.");
        }
        if (str0.contains("*")) {
            str0 = str0.replace("*", ".*");
        }
        Pattern pattern = Pattern.compile(str0);
        return pattern;
    }

    public static void b(String str0, k k) {
        if (str0 == null || str0.length() == 0) {
        }
        else {
            String[] stringArr0 = str0.split("
");
            String str1 = stringArr0[0];
            if (str1 == null || str1.length() <= 0) {
                throw new k(new StringBuilder().append("res meta Corrupted:").append(str0).toString());
            }
            else {
                String[] stringArr0Var1 = str1.split(",", 3);
                k.a = stringArr0Var1[1];
                k.b = stringArr0Var1[2];
            }
        }
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(new StringBuilder().append("resArscMd5:").append(this.b).append("
").toString());
        buffer.append(new StringBuilder().append("arscBaseCrc:").append(this.a).append("
").toString());
        Iterator iteratorVar5 = this.i.iterator();
        while (iteratorVar5.hasNext()) {
            Pattern pattern = (Pattern)iteratorVar5.next();
            buffer.append(new StringBuilder().append("pattern:").append(pattern).append("
").toString());
        }
        iteratorVar5 = this.c.iterator();
        while (iteratorVar5.hasNext()) {
            String str0 = (String)iteratorVar5.next();
            buffer.append(new StringBuilder().append("addedSet:").append(str0).append("
").toString());
        }
        iteratorVar5 = this.e.iterator();
        while (iteratorVar5.hasNext()) {
            String str1 = (String)iteratorVar5.next();
            buffer.append(new StringBuilder().append("modifiedSet:").append(str1).append("
").toString());
        }
        iteratorVar5 = this.g.iterator();
        while (iteratorVar5.hasNext()) {
            String str2 = (String)iteratorVar5.next();
            buffer.append(new StringBuilder().append("largeModifiedSet:").append(str2).append("
").toString());
        }
        iteratorVar5 = this.d.iterator();
        while (iteratorVar5.hasNext()) {
            String str3 = (String)iteratorVar5.next();
            buffer.append(new StringBuilder().append("deletedSet:").append(str3).append("
").toString());
        }
        iteratorVar5 = this.f.keySet().iterator();
        while (iteratorVar5.hasNext()) {
            String str4 = (String)iteratorVar5.next();
            buffer.append(new StringBuilder().append("storeSet:").append(str4).append("
").toString());
        }
        return buffer.toString();
    }

    // class: com/tencent/tinker/loader/shareutil/k$a
    public class k$a {
        public String a;
        public long b;
        public File c;

        public k$a() {
            super();
            this.a = null;
            this.c = null;
        }

    }
    // class: com/tencent/tinker/loader/shareutil/k$a
    public class k$a {
        public String a;
        public long b;
        public File c;

        public k$a() {
            super();
            this.a = null;
            this.c = null;
        }

    }
}
