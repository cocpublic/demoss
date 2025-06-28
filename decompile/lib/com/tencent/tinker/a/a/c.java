/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/c
public class c {
    public int a;

    public c(int i0, int[] intArr0) {
        super(i0);
        this.a = intArr0;
    }

    public int a(c c) {
        if (this.a.length != c.a.length) {
            return c.a(this.a.length, c.a.length);
        }
        else {
            for (int i2 = 0; i2 < this.a.length; i2 += 1) {
                if (this.a[i2] != c.a[i2]) {
                    return c.a(this.a[i2], c.a[i2]);
                }
                else {
                }
            }
            return 0;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public boolean equals(Object object) {
        if ((object instanceof c)) {
            return false;
        }
        else if (this.a((c)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((c)object);
    }

}
