/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a;


// class: com/tencent/tinker/a/a/r
public class r {
    public r$a a;
    public int b;
    public int c;
    public int d;

    public r(int i0, r$a r$a, int i1, int i2, int i3) {
        super(i0);
        this.a = r$a;
        this.b = i1;
        this.c = i2;
        this.d = i3;
    }

    public int a(r r) {
        if (this.a != r.a) {
            return this.a.compareTo(r.a);
        }
        else {
            return c.a(this.c, r.c);
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((r)object);
    }

    // class: com/tencent/tinker/a/a/r$a
    public final enum r$a {
        final public int j;
        final private static synthetic r$a[] k;

        privatevoid r$a(String str0, int i0, int i1) {
            this.j = i1;
        }

        public static r$a a(int i0) {
            r$a[] r$aArr0 = r$a.values();
            for (int i2 = 0; i2 < r$aArr0.length; i2 += 1) {
                r$a r$a = r$aArr0[i2];
                if (r$a.j == i0) {
                    return r$a;
                }
                else {
                }
            }
            throw new IllegalArgumentException(String.valueOf(i0));
        }

        public boolean a() {
            switch(r$1.a[this.ordinal()]) {
                case 1: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }

        static  {
            r$a.a = new r$a("METHOD_HANDLE_TYPE_STATIC_PUT", 0, 0);
            r$a.b = new r$a("METHOD_HANDLE_TYPE_STATIC_GET", 1, 1);
            r$a.c = new r$a("METHOD_HANDLE_TYPE_INSTANCE_PUT", 2, 2);
            r$a.d = new r$a("METHOD_HANDLE_TYPE_INSTANCE_GET", 3, 3);
            r$a.e = new r$a("METHOD_HANDLE_TYPE_INVOKE_STATIC", 4, 4);
            r$a.f = new r$a("METHOD_HANDLE_TYPE_INVOKE_INSTANCE", 5, 5);
            r$a.g = new r$a("METHOD_HANDLE_TYPE_INVOKE_DIRECT", 6, 6);
            r$a.h = new r$a("METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR", 7, 7);
            r$a.i = new r$a("METHOD_HANDLE_TYPE_INVOKE_INTERFACE", 8, 8);
            r$a.k = new r$a[]{r$a.a, r$a.b, r$a.c, r$a.d, r$a.e, r$a.f, r$a.g, r$a.h, r$a.i};
        }

    }
    // class: com/tencent/tinker/a/a/r$a
    public final enum r$a {
        final public int j;
        final private static synthetic r$a[] k;

        privatevoid r$a(String str0, int i0, int i1) {
            this.j = i1;
        }

        public static r$a a(int i0) {
            r$a[] r$aArr0 = r$a.values();
            for (int i2 = 0; i2 < r$aArr0.length; i2 += 1) {
                r$a r$a = r$aArr0[i2];
                if (r$a.j == i0) {
                    return r$a;
                }
                else {
                }
            }
            throw new IllegalArgumentException(String.valueOf(i0));
        }

        public boolean a() {
            switch(r$1.a[this.ordinal()]) {
                case 1: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }

        static  {
            r$a.a = new r$a("METHOD_HANDLE_TYPE_STATIC_PUT", 0, 0);
            r$a.b = new r$a("METHOD_HANDLE_TYPE_STATIC_GET", 1, 1);
            r$a.c = new r$a("METHOD_HANDLE_TYPE_INSTANCE_PUT", 2, 2);
            r$a.d = new r$a("METHOD_HANDLE_TYPE_INSTANCE_GET", 3, 3);
            r$a.e = new r$a("METHOD_HANDLE_TYPE_INVOKE_STATIC", 4, 4);
            r$a.f = new r$a("METHOD_HANDLE_TYPE_INVOKE_INSTANCE", 5, 5);
            r$a.g = new r$a("METHOD_HANDLE_TYPE_INVOKE_DIRECT", 6, 6);
            r$a.h = new r$a("METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR", 7, 7);
            r$a.i = new r$a("METHOD_HANDLE_TYPE_INVOKE_INTERFACE", 8, 8);
            r$a.k = new r$a[]{r$a.a, r$a.b, r$a.c, r$a.d, r$a.e, r$a.f, r$a.g, r$a.h, r$a.i};
        }

    }
}
