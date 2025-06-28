/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/c;


// class: com/tencent/tinker/a/c/b
public class b implements Cloneable {
    final private static int a;
    private int b;
    private int c;
    private int d;

    public b() {
        super(10);
    }

    public b(int i0) {
        super();
        this.b = i0 == 0 ? new int[]{} : b.a;
        this.c = new int[]{};
        this.d = 0;
    }

    public static int a(int i0) {
        if (i0 <= 4) {
            return 8;
        }
        else {
            return i0 + i0 >> 1;
        }
    }

    public b a() {
        Object object = null;
        try {
            b b = (b)this.clone();
            b.b = (int[])this.b.clone();
            b.c = (int[])this.c.clone();
        }
        catch (CloneNotSupportedException var_2_0) {
        }
        return b;
    }

    public void a(int i0, int i1) {
        int i2 = super.a(this.b, this.d, i0);
        if (i2 >= 0) {
            this.c[i2] = i1;
        }
        else {
            i2 ^= -1;
            this.b = super.a(this.b, this.d, i2, i0);
            this.c = super.a(this.c, this.d, i2, i1);
            this.d = this.d + 1;
        }
    }

    public int b() {
        return this.d;
    }

    public int b(int i0) {
        return this.b[i0];
    }

    public int c(int i0) {
        return this.c[i0];
    }

    public int d(int i0) {
        return this.a(this.b, this.d, i0);
    }

    public void c() {
        this.d = 0;
    }

    public void b(int i0, int i1) {
        if (this.d != 0 && i0 <= this.b[this.d - 1]) {
            this.a(i0, i1);
        }
        else {
            this.b = super.b(this.b, this.d, i0);
            this.c = super.b(this.c, this.d, i1);
            this.d = this.d + 1;
        }
    }

    private int a(int[] intArr0, int i0, int i1) {
        int i6 = 0;
        int i7 = i0 - 1;
        while (i6 <= i7) {
            int i4 = i6 + i7 >>> 1;
            int i5 = intArr0[i4];
            if (i5 < i1) {
                i6 = i4 + 1;
                continue;;
            }
            else if (i5 > i1) {
                i7 = i4 - 1;
                continue;;
            }
            else {
                return i4;
            }
        }
        return i6 ^ -1;
    }

    private int[] b(int[] intArr0, int i0, int i1) {
        if (i0 > intArr0.length) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad currentSize, originalSize: ").append(intArr0.length).append(" currentSize: ").append(i0).toString());
        }
        else {
            if (i0 + 1 > intArr0.length) {
                int[] intArr0Var1 = new int[]{};
                System.arraycopy(intArr0, 0, intArr0Var1, 0, i0);
            }
            intArr0Var1[i0] = i1;
            return intArr0Var1;
        }
    }

    private int[] a(int[] intArr0, int i0, int i1, int i2) {
        if (i0 > intArr0.length) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad currentSize, originalSize: ").append(intArr0.length).append(" currentSize: ").append(i0).toString());
        }
        else if (i0 + 1 <= intArr0.length) {
            System.arraycopy(intArr0, i1, intArr0, i1 + 1, i0 - i1);
            intArr0[i1] = i2;
            return intArr0;
        }
        else {
            int[] intArr0Var1 = new int[]{};
            System.arraycopy(intArr0, 0, intArr0Var1, 0, i1);
            intArr0Var1[i1] = i2;
            System.arraycopy(intArr0, i1, intArr0Var1, i1 + 1, intArr0.length - i1);
            return intArr0Var1;
        }
    }

    public String toString() {
        if (this.b() <= 0) {
            return "{}";
        }
        else {
            StringBuilder builder = new StringBuilder(this.d * 28);
            builder.append(123);
            for (int i0 = 0; i0 < this.d; i0 += 1) {
                if (i0 > 0) {
                    builder.append(", ");
                }
                int i1 = this.b(i0);
                builder.append(i1);
                builder.append(61);
                int i2 = this.c(i0);
                builder.append(i2);
            }
            builder.append(125);
            return builder.toString();
        }
    }

    public /* synthetic */ Object clone() {
        return this.a();
    }

    static  {
        b.a = new int[]{};
    }

}
