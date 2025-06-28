/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/b/a;

import com.tencent.tinker.a.c.b;
import com.tencent.tinker.a.a.k;

// class: com/tencent/tinker/a/b/a/c
public final class c {
    final private b a;
    private int b;

    public c() {
        super(null);
        this.a = new b();
        this.b = 0;
    }

    private void b(int i0) {
        if (i0 != this.b) {
            this.a.b(i0, this.b);
        }
    }

    public int a(int i0) {
        int i1 = this.a.d(i0);
        if (i1 < 0) {
            return i0;
        }
        else {
            return this.a.c(i1);
        }
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1) {
v_48 = this;
this.b(i0);
        switch(i1) {
            case -1: {
                this.b = this.b + 1;
                return;
            }
            case 40: {
                int i5 = b.e(i4, this.b);
                if (i5 != (byte)i5) {
                    this.b = i5 != (short)i5 ? this.b + 2 : this.b + 3;
                }
                else {
                    this.b = this.b + 1;
                }
            }
            case 41: {
                int i6 = b.e(i4, this.b);
                this.b = i6 != (short)i6 ? this.b + 2 : this.b + 3;
                return;
            }
            case 42: {
                this.b = this.b + 3;
                return;
            }
            case 36: {
                this.b = this.b + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(a.c(i1)).toString());
            }
        }
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1) {
v_29 = this;
this.b(i0);
        switch(i1) {
            case 26: {
                this.b = i2 > 65535 ? this.b + 2 : this.b + 3;
                return;
            }
            case 27: {
                this.b = this.b + 3;
                return;
            }
            case 10: {
                this.b = this.b + 1;
                return;
            }
            case 19: {
                this.b = this.b + 2;
                return;
            }
            case 20: {
                this.b = this.b + 3;
                return;
            }
            case 24: {
                this.b = this.b + 5;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(a.c(i1)).toString());
            }
        }
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5) {
this.b(i0);
        switch(i1) {
            case 1: {
                this.b = this.b + 1;
                return;
            }
            case 2: {
                this.b = this.b + 2;
                return;
            }
            case 32: {
                this.b = this.b + 2;
                return;
            }
            case 3: {
                this.b = this.b + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(a.c(i1)).toString());
            }
        }
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6) {
this.b(i0);
        switch(i1) {
            case 45: {
                this.b = this.b + 2;
                return;
            }
            case 36: {
                this.b = this.b + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(a.c(i1)).toString());
            }
        }
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6, int i7) {
this.b(i0);
        switch(i1) {
            case 36: {
                this.b = this.b + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(a.c(i1)).toString());
            }
        }
    }

    public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6, int i7, int i8) {
this.b(i0);
        switch(i1) {
            case 36: {
                this.b = this.b + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(a.c(i1)).toString());
            }
        }
    }

    public void b(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5) {
super.b(i0);
        switch(i1) {
            case 37: {
                this.b = this.b + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(a.c(i1)).toString());
            }
        }
    }

    public void a(int i0, int i1, int i2, int i3, int i4, int[] intArr0) {
        if (i1 == 250) {
            this.b = this.b + 4;
            return;
        }
        else {
            throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(a.c(i1)).toString());
        }
    }

    public void a(int i0, int i1, int i2, int i3, int i4, int i5, int i6) {
        if (i1 == 251) {
            this.b = this.b + 4;
            return;
        }
        else {
            throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(a.c(i1)).toString());
        }
    }

    public void a(int i0, int i1, int[] intArr0, int[] intArr0Var1) {
        this.b(i0);
        this.b = this.b + 2;
        this.b = this.b + intArr0.length * 2;
        this.b = this.b + intArr0Var1.length * 2;
    }

    public void a(int i0, int i1, int i2, int[] intArr0) {
        this.b(i0);
        this.b = this.b + 4;
        this.b = this.b + intArr0.length * 2;
    }

    public void a(int i0, int i1, Object object, int i2, int i3) {
this.b(i0);
this.b = this.b + 4;
        switch(i3) {
            case 1: {
                this.b = this.b + (byte[])object.length >> 1 + (byte[])object.length & 1;
                return;
            }
            case 2: {
                this.b = this.b + (short[])object.length * 1;
                return;
            }
            case 4: {
                this.b = this.b + (int[])object.length * 2;
                return;
            }
            case 8: {
                this.b = this.b + (long[])object.length * 4;
                return;
            }
            default: {
                throw new k(new StringBuilder().append("bogus element_width: ").append(a.b(i3)).toString());
            }
        }
    }

}
