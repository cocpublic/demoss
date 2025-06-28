/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/b;

import java.io.File;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.e.d;
import com.tencent.tinker.lib.d.c;
import com.tencent.tinker.lib.g.b;
import com.tencent.tinker.loader.shareutil.i;

// class: com/tencent/tinker/lib/b/a
public class a implements b {
    final protected Context b;
    private ServiceConnection a;

    public a(Context context) {
        super();
        this.b = context;
    }

    public int a(String str0) {
        return this.a(str0, 0);
    }

    protected int a(String str0, boolean bool0) {
        File file = new File(str0);
        String str1 = h.f(file);
        int i0 = this.a(str0, str1);
        if (i0 == 0) {
            super.a();
            TinkerPatchService.a(this.b, str0, bool0);
        }
        else {
            b.a(this.b).g().a(new File(str0), i0);
        }
        return i0;
    }

    private void a() {
        try {
            this.a = new a$1(this);
            Intent intent = new Intent(this.b, TinkerPatchForeService.class);
            this.b.bindService(intent, this.a, 1);
        }
        catch (Throwable var_1_1) {
        }
    }

    protected int a(String str0, String str1) {
        b b = b.a(this.b);
        if (! b.i() || m.g(this.b)) {
            return -1;
        }
        else if (TextUtils.isEmpty(str1)) {
            return 254;
        }
        else {
            File file = new File(str0);
            if (h.a(file)) {
                return 254;
            }
            else if (b.e()) {
                return 252;
            }
            else if (a.b(this.b)) {
                return 253;
            }
            else if (m.b()) {
                return 251;
            }
            else {
                d d = b.b();
                int i0 = b.d() && d != null && d.e ? 0 : 1;
                if (i0 == 0) {
                    String str2 = b.n().getAbsolutePath();
                    File fileVar1 = h.b(str2);
                    File fileVar2 = h.a(str2);
                    try {
                        i i = i.a(fileVar2, fileVar1);
                        if (i != null && m.b(i.b) && i.b.equals(i.e) && str1.equals(i.b)) {
                            return 250;
                        }
                        else {
                        }
                    }
                    catch (Throwable var_10_1) {
                    }
                }
                if (b.a(this.b).a(str1)) {
                    return 249;
                }
                else {
                    return 0;
                }
            }
        }
    }

    static /* synthetic */ ServiceConnection a(a a) {
        return a.a;
    }

}
