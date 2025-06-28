/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/f
public final class f {
    public f$a[] a;
    public f$a[] b;
    public f$b[] c;
    public f$b[] d;

    public f(int i0, f$a[] f$aArr0, f$a[] f$aArr0Var1, f$b[] f$bArr0, f$b[] f$bArr0Var1) {
        super(i0);
        this.a = f$aArr0;
        this.b = f$aArr0Var1;
        this.c = f$bArr0;
        this.d = f$bArr0Var1;
    }

    public int a(f f) {
        int i2 = c.a(this.a, f.a);
        if (i2 != 0) {
            return i2;
        }
        else {
            i2 = c.a(this.b, f.b);
            if (i2 != 0) {
                return i2;
            }
            else {
                i2 = c.a(this.c, f.c);
                if (i2 != 0) {
                    return i2;
                }
                else {
                    return c.a(this.d, f.d);
                }
            }
        }
    }

    public int hashCode() {
        return e.a(new Object[]{this.a, this.b, this.c, this.d});
    }

    public boolean equals(Object object) {
        if ((object instanceof f)) {
            return false;
        }
        else if (this.a((f)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((f)object);
    }

    // class: com/tencent/tinker/a/a/f$b
    public class f$b implements Comparable<f$b> {
        public int a;
        public int b;
        public int c;

        public f$b(int i0, int i1, int i2) {
            super();
            this.a = i0;
            this.b = i1;
            this.c = i2;
        }

        public int a(f$b f$b) {
            int i1 = c.a(this.a, f$b.a);
            if (i1 != 0) {
                return i1;
            }
            else {
                i1 = c.b(this.b, f$b.b);
                if (i1 != 0) {
                    return i1;
                }
                else {
                    return c.b(this.c, f$b.c);
                }
            }
        }

        public boolean equals(Object object) {
            if ((object instanceof f$b)) {
                return false;
            }
            else if (this.a((f$b)object) == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        public int hashCode() {
            return e.a(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.a((f$b)object);
        }

    }
    // class: com/tencent/tinker/a/a/f$b
    public class f$b implements Comparable<f$b> {
        public int a;
        public int b;
        public int c;

        public f$b(int i0, int i1, int i2) {
            super();
            this.a = i0;
            this.b = i1;
            this.c = i2;
        }

        public int a(f$b f$b) {
            int i1 = c.a(this.a, f$b.a);
            if (i1 != 0) {
                return i1;
            }
            else {
                i1 = c.b(this.b, f$b.b);
                if (i1 != 0) {
                    return i1;
                }
                else {
                    return c.b(this.c, f$b.c);
                }
            }
        }

        public boolean equals(Object object) {
            if ((object instanceof f$b)) {
                return false;
            }
            else if (this.a((f$b)object) == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        public int hashCode() {
            return e.a(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.a((f$b)object);
        }

    }
    // class: com/tencent/tinker/a/a/f$a
    public class f$a implements Comparable<f$a> {
        public int a;
        public int b;

        public f$a(int i0, int i1) {
            super();
            this.a = i0;
            this.b = i1;
        }

        public int a(f$a f$a) {
            int i0 = c.a(this.a, f$a.a);
            if (i0 != 0) {
                return i0;
            }
            else {
                return c.b(this.b, f$a.b);
            }
        }

        public boolean equals(Object object) {
            if ((object instanceof f$a)) {
                return false;
            }
            else if (this.a((f$a)object) == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        public int hashCode() {
            return e.a(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.a((f$a)object);
        }

    }
    // class: com/tencent/tinker/a/a/f$a
    public class f$a implements Comparable<f$a> {
        public int a;
        public int b;

        public f$a(int i0, int i1) {
            super();
            this.a = i0;
            this.b = i1;
        }

        public int a(f$a f$a) {
            int i0 = c.a(this.a, f$a.a);
            if (i0 != 0) {
                return i0;
            }
            else {
                return c.b(this.b, f$a.b);
            }
        }

        public boolean equals(Object object) {
            if ((object instanceof f$a)) {
                return false;
            }
            else if (this.a((f$a)object) == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        public int hashCode() {
            return e.a(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.a((f$a)object);
        }

    }
}
