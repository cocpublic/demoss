/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/w
public class w {
    public String a;

    public w(int i0, String str0) {
        super(i0);
        this.a = str0;
    }

    public int a(w w) {
        return this.a.compareTo(w.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean equals(Object object) {
        if ((object instanceof w)) {
            return false;
        }
        else if (this.a((w)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((w)object);
    }

}
