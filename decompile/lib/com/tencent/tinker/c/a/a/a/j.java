/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/c/a/a/a;

import com.tencent.tinker.c.a.b.a;
import com.tencent.tinker.c.a.c.c;
import com.tencent.tinker.a.a.a.a;
import com.tencent.tinker.a.a.x$a$a;
import com.tencent.tinker.a.a.j;
import com.tencent.tinker.a.a.x$a;
import com.tencent.tinker.a.a.j$g;

// class: com/tencent/tinker/c/a/a/a/j
public abstract class j<T> {
    final protected a a;
    final protected j b;
    final private c c;

    public j(a a, j j, c c) {
        super();
        this.a = a;
        this.b = j;
        this.c = c;
    }

    x$a a(j p0);

    T b(a p0);

    protected T a(a a, T comparable) {
        return comparable;
    }

    protected void a(c c, int i0, int i1, int i2, int i3) {
    }

    protected void a(c c, int i0, int i1) {
    }

    int a(T p0);

    private int[] a(int i0) {
        int[] intArr0 = new int[]{};
        int i1 = 0;
        for (int i2 = 0; i2 < i0; i2 += 1) {
            int i3 = this.a.y().u();
            i1 += i3;
            intArr0[i2] = i1;
        }
        return intArr0;
    }

    private int a(int i0, T comparable) {
        if ((comparable instanceof x$a$a)) {
            return (x$a$a)comparable.i;
        }
        else {
            return i0;
        }
    }

    public void a() {
        int i0 = this.a.y().s();
        int[] intArr0 = super.a(i0);
        int i1 = this.a.y().s();
        int[] intArr0Var1 = super.a(i1);
        int i2 = this.a.y().s();
        int[] intArr0Var2 = super.a(i2);
        x$a x$a = this.a(this.b);
        Object object = null;
        int i4 = 0;
        if (x$a.a()) {
            object = this.b.a(x$a);
            i4 = x$a.c;
        }
        super.a(object, i4, intArr0, intArr0Var1, intArr0Var2);
    }

    private void a(j$g j$g, int i0, int[] intArr0, int[] intArr0Var1, int[] intArr0Var2) {
        int i4 = i0 + intArr0Var1.length - intArr0.length;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (i8 < i0 || i9 < i4) {
                if (i6 < intArr0Var1.length && intArr0Var1[i6] == i9) {
                    Comparable comparable = this.b(this.a.y());
                    int i10 = this.a(comparable);
                    i6 += 1;
                    i9 += 1;
                    continue;;
                }
                else {
                    if (i7 < intArr0Var2.length && intArr0Var2[i7] == i9) {
                        Comparable comparableVar1 = this.b(this.a.y());
                        int i11 = this.a(comparableVar1);
                        i7 += 1;
                        i9 += 1;
                        continue;;
                    }
                    else if (Arrays.binarySearch(intArr0, i8) >= 0) {
                        Comparable comparableVar2 = this.b(j$g);
                        this.a(this.c, i8, super.a(i8, comparableVar2));
                        i8 += 1;
                        i5 += 1;
                        continue;;
                    }
                    else if (Arrays.binarySearch(intArr0Var2, i8) >= 0) {
                        Comparable comparableVar3 = this.b(j$g);
                        this.a(this.c, i8, super.a(i8, comparableVar3));
                        i8 += 1;
                        continue;;
                    }
                    else if (i8 < i0) {
                        Comparable comparableVar4 = this.a(this.c, this.b(j$g));
                        int i12 = this.a(comparableVar4);
                        this.a(this.c, i8, super.a(i8, comparableVar4), i9, i12);
                        i8 += 1;
                        i9 += 1;
                        continue;;
                    }
                }
            }
            else {
            }
        }
        if (i6 == intArr0Var1.length || i5 == intArr0.length || i7 != intArr0Var2.length) {
            throw new IllegalStateException(String.format("bad patch operation sequence. addCounter: %d, addCount: %d, delCounter: %d, delCount: %d, replaceCounter: %d, replaceCount:%d", new Object[]{Integer.valueOf(i6), Integer.valueOf(intArr0Var1.length), Integer.valueOf(i5), Integer.valueOf(intArr0.length), Integer.valueOf(i7), Integer.valueOf(intArr0Var2.length)}));
        }
        else {
        }
    }

}
