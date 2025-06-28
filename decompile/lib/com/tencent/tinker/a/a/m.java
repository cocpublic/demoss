/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/m
public final class m {
    public byte a;

    public m(int i0, byte[] byteArr0) {
        super(i0);
        this.a = byteArr0;
    }

    public a a() {
        return new m$1(this);
    }

    public int a(m m) {
        return c.a(this.a, m.a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public boolean equals(Object object) {
        if ((object instanceof m)) {
            return false;
        }
        else if (this.a((m)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((m)object);
    }

}
