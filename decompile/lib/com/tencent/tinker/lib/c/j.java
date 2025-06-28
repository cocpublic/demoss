/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;


// class: com/tencent/tinker/lib/c/j
public class j {
    final protected b a;

    public j() {
        super();
        this.a = this.a();
    }

    private b a() {
        Object object = null;
        try {
            Class class = Class.forName(a.b());
            Constructor constructor = class.getConstructor(new Class[]{});
            b b = (b)constructor.newInstance(new Object[]{});
        }
        catch (Exception var_2_1) {
            n.d("Tinker.ExtendUpgradePatch", "create custom diff patcher fail.", new Object[]{var_2_1});
        }
        return b;
    }

    boolean a(b b, l l, Context context, String str0, File file) {
        if (this.a != null) {
            return this.a.a(b, l, context, str0, file);
        }
        else {
            return true;
        }
    }

}
