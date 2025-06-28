/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/y
public final class y {
    final public static y a;
    public short b;

    public y(int i0, short[] shortArr0) {
        super(i0);
        this.b = shortArr0;
    }

    public int a(y y) {
        return c.a(this.b, y.b);
    }

    public int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public boolean equals(Object object) {
        if ((object instanceof y)) {
            return false;
        }
        else if (this.a((y)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((y)object);
    }

    static  {
        y.a = new y(0, j.a);
    }

}
