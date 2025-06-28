/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/c;

import com.tencent.tinker.a.b.a.i;
import com.tencent.tinker.a.b.a.c;
import com.tencent.tinker.a.b.a.f;
import com.tencent.tinker.a.b.a.d;
import com.tencent.tinker.a.b.a.h;
import com.tencent.tinker.a.a.k;

// class: com/tencent/tinker/c/a/c/b
public final class b {
    final private a a;

    public b(a a) {
        super();
        this.a = a;
    }

    public short[] a(short[] shortArr0) {
        i i = new i(shortArr0.length);
        c c = new c();
        f f = new f(i, c);
        d d = new d(new h(shortArr0));
        try {
            d.a(new b$a(this, c));
            d.a(new b$a(this, f));
        }
        catch (EOFException var_6_0) {
            throw new k(var_6_0);
        }
        return i.d();
    }

    static /* synthetic */ a a(b b) {
        return b.a;
    }

    // class: com/tencent/tinker/c/a/c/b$a
    final class b$a {
        final synthetic b a;

         b$a(b b, e e) {
            this.a = b;
            super(e);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1) {
            int i5 = super.a(i2, i3);
            super.a(i0, i1, i5, i3, i4, l1);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1) {
            int i6 = super.a(i2, i3);
            super.a(i0, i1, i6, i3, i4, l1, i5);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5) {
            int i7 = super.a(i2, i3);
            super.a(i0, i1, i7, i3, i4, l1, i5, i6);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6) {
            int i8 = super.a(i2, i3);
            super.a(i0, i1, i8, i3, i4, l1, i5, i6, i7);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6, int i7) {
            int i9 = super.a(i2, i3);
            super.a(i0, i1, i9, i3, i4, l1, i5, i6, i7, i8);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6, int i7, int i8) {
            int i10 = super.a(i2, i3);
            super.a(i0, i1, i10, i3, i4, l1, i5, i6, i7, i8, i9);
        }

        public void b(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5) {
            int i7 = this.a(i2, i3);
            super.b(i0, i1, i7, i3, i4, l1, i5, i6);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, int[] intArr0) {
            i2 = b.a(this.a).e(i2);
            i4 = b.a(this.a).c(i4);
            super.a(i0, i1, i2, i3, i4, intArr0);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, int i5, int i6) {
            i2 = b.a(this.a).e(i2);
            i6 = b.a(this.a).c(i6);
            super.a(i0, i1, i2, i3, i4, i5, i6);
        }

        private int a(int i0, int i1) {
            switch(i1) {
                case 3: {
                    return b.a(this.a).a(i0);
                }
                case 2: {
                    return b.a(this.a).b(i0);
                }
                case 5: {
                    return b.a(this.a).d(i0);
                }
                case 9: {
                    return b.a(this.a).c(i0);
                }
                case 4: {
                    return b.a(this.a).e(i0);
                }
                case 8: {
                    return b.a(this.a).g(i0);
                }
                case 7: {
                    return b.a(this.a).f(i0);
                }
                case 6: {
                    throw new IllegalArgumentException("METHOD_AND_PROTO_REF should not use this method to do transform.");
                }
                default: {
                    return i0;
                }
            }
        }

    }
    // class: com/tencent/tinker/c/a/c/b$a
    final class b$a {
        final synthetic b a;

         b$a(b b, e e) {
            this.a = b;
            super(e);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1) {
            int i5 = super.a(i2, i3);
            super.a(i0, i1, i5, i3, i4, l1);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1) {
            int i6 = super.a(i2, i3);
            super.a(i0, i1, i6, i3, i4, l1, i5);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5) {
            int i7 = super.a(i2, i3);
            super.a(i0, i1, i7, i3, i4, l1, i5, i6);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6) {
            int i8 = super.a(i2, i3);
            super.a(i0, i1, i8, i3, i4, l1, i5, i6, i7);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6, int i7) {
            int i9 = super.a(i2, i3);
            super.a(i0, i1, i9, i3, i4, l1, i5, i6, i7, i8);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5, int i6, int i7, int i8) {
            int i10 = super.a(i2, i3);
            super.a(i0, i1, i10, i3, i4, l1, i5, i6, i7, i8, i9);
        }

        public void b(int i0, int i1, int i2, int i3, int i4, long l1, int l1, int i5) {
            int i7 = this.a(i2, i3);
            super.b(i0, i1, i7, i3, i4, l1, i5, i6);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, int[] intArr0) {
            i2 = b.a(this.a).e(i2);
            i4 = b.a(this.a).c(i4);
            super.a(i0, i1, i2, i3, i4, intArr0);
        }

        public void a(int i0, int i1, int i2, int i3, int i4, int i5, int i6) {
            i2 = b.a(this.a).e(i2);
            i6 = b.a(this.a).c(i6);
            super.a(i0, i1, i2, i3, i4, i5, i6);
        }

        private int a(int i0, int i1) {
            switch(i1) {
                case 3: {
                    return b.a(this.a).a(i0);
                }
                case 2: {
                    return b.a(this.a).b(i0);
                }
                case 5: {
                    return b.a(this.a).d(i0);
                }
                case 9: {
                    return b.a(this.a).c(i0);
                }
                case 4: {
                    return b.a(this.a).e(i0);
                }
                case 8: {
                    return b.a(this.a).g(i0);
                }
                case 7: {
                    return b.a(this.a).f(i0);
                }
                case 6: {
                    throw new IllegalArgumentException("METHOD_AND_PROTO_REF should not use this method to do transform.");
                }
                default: {
                    return i0;
                }
            }
        }

    }
}
