/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/b/a;

import java.io.EOFException;

// class: com/tencent/tinker/a/b/a/h
public final class h {
    final private short a;

    public h(short[] shortArr0) {
        super();
        if (shortArr0 == null) {
            throw new NullPointerException("array == null");
        }
        else {
            this.a = shortArr0;
        }
    }

    public boolean d() {
        if (this.a() < this.a.length) {
            return true;
        }
        else {
            return false;
        }
    }

    public int e() {
        try {
            short short0 = this.a[this.a()];
            this.a(1);
            return short0 & 65535;
        }
        catch (ArrayIndexOutOfBoundsException var_1_1) {
            throw new EOFException();
        }
    }

    public int f() {
        int i0 = this.e();
        int i1 = this.e();
        return i0 | i1 << 16;
    }

    public long g() {
        long l0 = (long)this.e();
        long l1 = (long)this.e();
        long l2 = (long)this.e();
        long l3 = (long)this.e();
        return l0 | l1 << 16 | l2 << 32 | l3 << 48;
    }

}
