/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/i
public class i {
    public int a;
    public int b;
    public byte c;

    public i(int i0, int i1, int[] intArr0, byte[] byteArr0) {
        super(i0);
        this.a = i1;
        this.b = intArr0;
        this.c = byteArr0;
    }

    public int a(i i) {
        int i0 = this.a;
        int i1 = i.a;
        if (i0 != i1) {
            return i0 - i1;
        }
        else {
            int i3 = c.a(this.b, i.b);
            if (i3 != 0) {
                return i3;
            }
            else {
                i3 = c.a(this.c, i.c);
                return i3;
            }
        }
    }

    public int hashCode() {
        return e.a(new Object[]{Integer.valueOf(this.a), this.b, this.c});
    }

    public boolean equals(Object object) {
        if ((object instanceof i)) {
            return false;
        }
        else if (this.a((i)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((i)object);
    }

}
