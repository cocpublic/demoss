/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/c;


// class: com/tencent/tinker/a/c/a
public class a implements Cloneable {
    final private static int a;
    final private static boolean b;
    private int c;
    private boolean d;
    private int e;

    public a() {
        super(10);
    }

    public a(int i0) {
        super();
        this.c = i0 == 0 ? new int[]{} : a.a;
        this.d = new boolean[]{};
        this.e = 0;
    }

    private static int e(int i0) {
        if (i0 <= 4) {
            return 8;
        }
        else {
            return i0 + i0 >> 1;
        }
    }

    public a a() {
        Object object = null;
        try {
            a a = (a)this.clone();
            a.c = (int[])this.c.clone();
            a.d = (boolean[])this.d.clone();
        }
        catch (CloneNotSupportedException var_2_0) {
        }
        return a;
    }

    public void a(int i0, boolean byte0) {
        int i1 = super.a(this.c, this.e, i0);
        if (i1 >= 0) {
            this.d[i1] = byte0;
        }
        else {
            i1 ^= -1;
            this.c = super.a(this.c, this.e, i1, i0);
            this.d = super.a(this.d, this.e, i1, byte0);
            this.e = this.e + 1;
        }
    }

    public int b() {
        return this.e;
    }

    public int a(int i0) {
        return this.c[i0];
    }

    public boolean b(int i0) {
        return this.d[i0];
    }

    public int c(int i0) {
        return this.a(this.c, this.e, i0);
    }

    public boolean d(int i0) {
        if (this.c(i0) >= 0) {
            return true;
        }
        else {
            return false;
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

    private boolean[] a(boolean[] booleanArr0, int i0, int i1, boolean byte0) {
        if (i0 > booleanArr0.length) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad currentSize, originalSize: ").append(booleanArr0.length).append(" currentSize: ").append(i0).toString());
        }
        else if (i0 + 1 <= booleanArr0.length) {
            System.arraycopy(booleanArr0, i1, booleanArr0, i1 + 1, i0 - i1);
            booleanArr0[i1] = byte0;
            return booleanArr0;
        }
        else {
            boolean[] booleanArr0Var1 = new boolean[]{};
            System.arraycopy(booleanArr0, 0, booleanArr0Var1, 0, i1);
            booleanArr0Var1[i1] = byte0;
            System.arraycopy(booleanArr0, i1, booleanArr0Var1, i1 + 1, booleanArr0.length - i1);
            return booleanArr0Var1;
        }
    }

    public String toString() {
        if (this.b() <= 0) {
            return "{}";
        }
        else {
            StringBuilder builder = new StringBuilder(this.e * 28);
            builder.append(123);
            for (int i0 = 0; i0 < this.e; i0 += 1) {
                if (i0 > 0) {
                    builder.append(", ");
                }
                int i1 = this.a(i0);
                builder.append(i1);
                builder.append(61);
                boolean bool0 = this.b(i0);
                builder.append(bool0);
            }
            builder.append(125);
            return builder.toString();
        }
    }

    public /* synthetic */ Object clone() {
        return this.a();
    }

    static  {
        a.a = new int[]{};
        a.b = new boolean[]{};
    }

}
