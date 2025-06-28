/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import java.util.List;
import java.util.ArrayList;
import android.os.Handler;
import android.os.Looper;
import com.tencent.tinker.anno.Keep;

// class: com/tencent/tinker/loader/shareutil/TinkerLogInlineFence
final class TinkerLogInlineFence {
    final private static Handler a;
    final private static List<Object[]> b;

    @Keep
    public TinkerLogInlineFence() {
        super();
    }

    public void handleMessage(Message message) {
        this.a(message);
    }

    private void a(Message message) {
        try {
            TinkerLogInlineFence.c();
            return;
        }
        finally {
            Throwable throwable = v_2;
            this.b(message);
            throw throwable;
        }
    }

    private void b(Message message) {
n$a n$a = n.a();
n$a n$aVar1 = n.b();
        switch(message.what) {
            case 2: {
                Object[] objectArr0 = (Object[])message.obj;
                if (n$aVar1 != null) {
                    n$aVar1.a((String)objectArr0[2], (String)objectArr0[3], (Object[])objectArr0[4]);
                }
                if (n$aVar1 == null || n$aVar1 == n$a) {
                    TinkerLogInlineFence.b;
                    synchronized () {
                        TinkerLogInlineFence.b.add(objectArr0);
                    }
                }
            }
            case 3: {
                Object[] objectArr0Var1 = (Object[])message.obj;
                if (n$aVar1 != null) {
                    n$aVar1.b((String)objectArr0Var1[2], (String)objectArr0Var1[3], (Object[])objectArr0Var1[4]);
                }
                if (n$aVar1 == null || n$aVar1 == n$a) {
                    TinkerLogInlineFence.b;
                    synchronized () {
                        TinkerLogInlineFence.b.add(objectArr0Var1);
                    }
                }
            }
            case 4: {
                Object[] objectArr0Var2 = (Object[])message.obj;
                if (n$aVar1 != null) {
                    n$aVar1.c((String)objectArr0Var2[2], (String)objectArr0Var2[3], (Object[])objectArr0Var2[4]);
                }
                if (n$aVar1 == null || n$aVar1 == n$a) {
                    TinkerLogInlineFence.b;
                    synchronized () {
                        TinkerLogInlineFence.b.add(objectArr0Var2);
                    }
                }
            }
            case 5: {
                Object[] objectArr0Var3 = (Object[])message.obj;
                if (n$aVar1 != null) {
                    n$aVar1.d((String)objectArr0Var3[2], (String)objectArr0Var3[3], (Object[])objectArr0Var3[4]);
                }
                if (n$aVar1 == null || n$aVar1 == n$a) {
                    TinkerLogInlineFence.b;
                    synchronized () {
                        TinkerLogInlineFence.b.add(objectArr0Var3);
                    }
                }
            }
            case 6: {
                Object[] objectArr0Var4 = (Object[])message.obj;
                if (n$aVar1 != null) {
                    n$aVar1.e((String)objectArr0Var4[2], (String)objectArr0Var4[3], (Object[])objectArr0Var4[4]);
                }
                if (n$aVar1 == null || n$aVar1 == n$a) {
                    TinkerLogInlineFence.b;
                    synchronized () {
                        TinkerLogInlineFence.b.add(objectArr0Var4);
                    }
                }
            }
            case 4001: {
                Object[] objectArr0Var5 = (Object[])message.obj;
                if (n$aVar1 != null) {
                    n$aVar1.a((String)objectArr0Var5[2], (Throwable)objectArr0Var5[3], (String)objectArr0Var5[4], (Object[])objectArr0Var5[5]);
                }
                if (n$aVar1 == null || n$aVar1 == n$a) {
                    TinkerLogInlineFence.b;
                    synchronized () {
                        TinkerLogInlineFence.b.add(objectArr0Var5);
                    }
                }
            }
            case 4002: {
                TinkerLogInlineFence.a(n$aVar1);
                return;
            }
            default: {
                n$aVar1.e("Tinker.TinkerLogInlineFence", new StringBuilder().append("[-] Bad msg id: ").append(message.what).toString(), new Object[]{});
            }
        }
    }

    private static void a(n$a n$a) {
        TinkerLogInlineFence.b;
        synchronized () {
            if (n$a == null || TinkerLogInlineFence.b.isEmpty()) {
            }
            else {
            }
        }
        new Thread(new TinkerLogInlineFence$1(n$a), "tinker_log_printer").start();
    }

    private static void c() {
        if (TinkerLogInlineFence.class.isPrimitive()) {
            throw new RuntimeException();
        }
        else {
        }
    }

    static /* synthetic */ List a() {
        return TinkerLogInlineFence.b;
    }

    static /* synthetic */ Handler b() {
        return TinkerLogInlineFence.a;
    }

    static  {
        TinkerLogInlineFence.a = new Handler(Looper.getMainLooper());
        TinkerLogInlineFence.b = new ArrayList();
    }

}
