/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/c;


// class: com/tencent/rfix/loader/c/a
public abstract class a {
    final protected b p;
    final protected boolean q;

    public a(Context context, String str0) {
        super(context, str0, 1, 0);
    }

    public a(Context context, String str0, boolean bool0) {
        super(context, str0, bool0, 0);
    }

    public a(Context context, String str0, boolean bool0, boolean bool1) {
        super();
        this.p = f.a().a(context, str0);
        this.q = bool1;
        if (bool0) {
            this.b();
        }
    }

    public void b() {
        this.p.a(this.q);
    }

    public void c() {
        this.p.b(this.q);
    }

}
