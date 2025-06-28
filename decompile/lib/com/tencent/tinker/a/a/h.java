/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/h
public final class h {
    public int a;
    public int b;
    public int c;
    public int d;
    public short e;
    public h$b[] f;
    public h$a[] g;

    public h(int i0, int i1, int i2, int i3, int i4, short[] shortArr0, h$b[] h$bArr0, h$a[] h$aArr0) {
        super(i0);
        this.a = i1;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = shortArr0;
        this.f = h$bArr0;
        this.g = h$aArr0;
    }

    public int a(h h) {
        int i5 = c.b(this.a, h.a);
        if (i5 != 0) {
            return i5;
        }
        else {
            i5 = c.b(this.b, h.b);
            if (i5 != 0) {
                return i5;
            }
            else {
                i5 = c.b(this.c, h.c);
                if (i5 != 0) {
                    return i5;
                }
                else {
                    i5 = c.b(this.d, h.d);
                    if (i5 != 0) {
                        return i5;
                    }
                    else {
                        i5 = c.a(this.e, h.e);
                        if (i5 != 0) {
                            return i5;
                        }
                        else {
                            i5 = c.a(this.f, h.f);
                            if (i5 != 0) {
                                return i5;
                            }
                            else {
                                return c.a(this.g, h.g);
                            }
                        }
                    }
                }
            }
        }
    }

    public int hashCode() {
        return e.a(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), this.e, this.f, this.g});
    }

    public boolean equals(Object object) {
        if ((object instanceof h)) {
            return false;
        }
        else if (this.a((h)object) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((h)object);
    }

    // class: com/tencent/tinker/a/a/h$a
    public class h$a implements Comparable<h$a> {
        public int a;
        public int b;
        public int c;
        public int d;

        public h$a(int[] intArr0, int[] intArr0Var1, int i0, int i1) {
            super();
            this.a = intArr0;
            this.b = intArr0Var1;
            this.c = i0;
            this.d = i1;
        }

        public int a(h$a h$a) {
            int i1 = c.b(this.a, h$a.a);
            if (i1 != 0) {
                return i1;
            }
            else {
                i1 = c.b(this.b, h$a.b);
                if (i1 != 0) {
                    return i1;
                }
                else {
                    return c.b(this.c, h$a.c);
                }
            }
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.a((h$a)object);
        }

    }
    // class: com/tencent/tinker/a/a/h$a
    public class h$a implements Comparable<h$a> {
        public int a;
        public int b;
        public int c;
        public int d;

        public h$a(int[] intArr0, int[] intArr0Var1, int i0, int i1) {
            super();
            this.a = intArr0;
            this.b = intArr0Var1;
            this.c = i0;
            this.d = i1;
        }

        public int a(h$a h$a) {
            int i1 = c.b(this.a, h$a.a);
            if (i1 != 0) {
                return i1;
            }
            else {
                i1 = c.b(this.b, h$a.b);
                if (i1 != 0) {
                    return i1;
                }
                else {
                    return c.b(this.c, h$a.c);
                }
            }
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.a((h$a)object);
        }

    }
    // class: com/tencent/tinker/a/a/h$b
    public class h$b implements Comparable<h$b> {
        public int a;
        public int b;
        public int c;

        public h$b(int i0, int i1, int i2) {
            super();
            this.a = i0;
            this.b = i1;
            this.c = i2;
        }

        public int a(h$b h$b) {
            int i1 = c.b(this.a, h$b.a);
            if (i1 != 0) {
                return i1;
            }
            else {
                i1 = c.b(this.b, h$b.b);
                if (i1 != 0) {
                    return i1;
                }
                else {
                    return c.b(this.c, h$b.c);
                }
            }
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.a((h$b)object);
        }

    }
    // class: com/tencent/tinker/a/a/h$b
    public class h$b implements Comparable<h$b> {
        public int a;
        public int b;
        public int c;

        public h$b(int i0, int i1, int i2) {
            super();
            this.a = i0;
            this.b = i1;
            this.c = i2;
        }

        public int a(h$b h$b) {
            int i1 = c.b(this.a, h$b.a);
            if (i1 != 0) {
                return i1;
            }
            else {
                i1 = c.b(this.b, h$b.b);
                if (i1 != 0) {
                    return i1;
                }
                else {
                    return c.b(this.c, h$b.c);
                }
            }
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.a((h$b)object);
        }

    }
}
