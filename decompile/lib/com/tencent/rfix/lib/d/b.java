/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/d;


// class: com/tencent/rfix/lib/d/b
public class b {
    public int a;
    public String b;
    public PatchConfig c;

    public b() {
        super();
    }

    public boolean a() {
        if (this.a >= b.b) {
            return true;
        }
        else {
            return false;
        }
    }

    public String toString() {
        return new StringBuilder().append("DownloadEvent{resultCode=").append(this.a).append("filePath=").append(this.b).append(125).toString();
    }

}
