/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/atta;


// class: com/tencent/rfix/lib/atta/b
public class b {
    final private int a;
    final private String b;
    final private String c;

    public b(int i0, String str0, String str1) {
        super();
        this.a = i0;
        this.b = str0;
        this.c = str1;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public String toString() {
        return new StringBuilder().append("ATTARecord{id=").append(this.a).append(", process='").append(this.b).append(39).append(", params='").append(this.c).append(39).append(125).toString();
    }

}
