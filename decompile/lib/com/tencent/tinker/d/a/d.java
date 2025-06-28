/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/d/a;

import java.nio.ByteOrder;

// class: com/tencent/tinker/d/a/d
public final class d {
    final private byte a;
    final private int b;
    final private int c;
    final private ByteOrder d;
    private int e;

     d(byte[] byteArr0, int i0, int i1, ByteOrder order) {
        super();
        this.a = byteArr0;
        this.b = i0;
        this.c = i1;
        this.d = order;
    }

    public static c a(byte[] byteArr0, int i0, int i1, ByteOrder order) {
        return new d(byteArr0, i0, i1, order);
    }

    public void a(int i0) {
        this.e = i0;
    }

    public void b(int i0) {
        this.e = this.e + i0;
    }

    public int a() {
        int i0 = e.a(this.a, this.b + this.e, this.d);
        this.e = this.e + 4;
        return i0;
    }

    public short b() {
        short short0 = e.b(this.a, this.b + this.e, this.d);
        this.e = this.e + 2;
        return short0;
    }

}
