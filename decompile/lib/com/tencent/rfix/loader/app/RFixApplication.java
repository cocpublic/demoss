/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/app;

import com.tencent.rfix.loader.f.a;
import com.tencent.rfix.loader.entity.RFixLoadResult;
import com.tencent.rfix.loader.h.h$a;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Keep;

// class: com/tencent/rfix/loader/app/RFixApplication
public abstract class RFixApplication {
    final private String c;
    final private String d;

    public RFixApplication() {
        super("com.tencent.rfix.entry.DefaultRFixApplicationLike");
    }

    @Keep
    public RFixApplication(String str0) {
        super(str0, null);
    }

    @Keep
    public RFixApplication(String str0, String str1) {
        super(15, null, null, 0, 1);
        this.c = str0;
        this.d = str1;
    }

    @Keep
    protected void onBaseContextAttached(Context context, long l1, long l1) {
        try {
            b.a(a.a);
            b.a(a.b);
            if (TextUtils.isEmpty(this.d)) {
                g.a(this.d);
                o.a(this.d);
            }
            c.a(context);
            RFixLoadResult result = this.a(l1, l3);
            this.b = context.getClassLoader();
            this.a(context, this.c, result);
            c.b(context, result);
            b.b(a.a);
            return;
        }
        catch (Throwable var_6_1) {
            throw new RuntimeException("onBaseContextAttached fail.", var_6_1);
        }
    }

    protected RFixLoadResult a(long l1, long l1) {
        b.a(a.c);
        Object object = null;
        RFixLoadResult resultVar1;
        try {
            resultVar1 = a.a(this);
            b.a(this);
            goto 57;
        }
        catch (Throwable var_6_0) {
            RFixLog.e("RFixApplication", "loadPatch fail.", var_6_0);
            resultVar1 = new RFixLoadResult();
            resultVar1.a = h$a.m;
            resultVar1.d = var_6_0;
        }
        resultVar1.l = 15;
        resultVar1.m = false;
        if (resultVar1.n == null) {
            resultVar1.n = new Intent();
            g.a(resultVar1.n, 236);
        }
        this.a = resultVar1.n;
        resultVar1.o = l1;
        resultVar1.p = l3;
        b.b(a.c);
        resultVar1.c = b.c(a.c);
        return resultVar1;
    }

    protected void a(Context context, String str0, RFixLoadResult result) {
        try {
            Class class = Class.forName(str0, false, this.b);
            Constructor constructor = class.getConstructor(new Class[]{Application.class, RFixLoadResult.class});
            Object object = constructor.newInstance(new Object[]{this, result});
            Class classVar1 = Class.forName("com.tencent.tinker.entry.TinkerApplicationInlineFence", false, this.b);
            Class classVar2 = Class.forName("com.tencent.tinker.entry.ApplicationLike", false, this.b);
            Constructor constructorVar1 = classVar1.getConstructor(new Class[]{classVar2});
            constructorVar1.setAccessible(true);
            Handler handler = (Handler)constructorVar1.newInstance(new Object[]{object});
            Field field = TinkerApplication.class.getDeclaredField("i");
            field.setAccessible(true);
            field.set(this, handler);
            Message message = Message.obtain(handler, 1, context);
            handler.handleMessage(message);
            return;
        }
        catch (Throwable var_4_1) {
            throw new RuntimeException("createInlineFence fail.", var_4_1);
        }
    }

}
