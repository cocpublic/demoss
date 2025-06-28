/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/d;

import java.util.ArrayList;
import java.util.List;
import com.tencent.rfix.lib.RFixListener;
import com.tencent.rfix.lib.config.PatchConfig;
import com.tencent.rfix.lib.entity.RFixPatchResult;

// class: com/tencent/rfix/lib/d/c
public class c {
    final private List<RFixListener> a;

    public c() {
        super();
        this.a = new ArrayList();
    }

    public void a(RFixListener listener) {
        if (listener == null) {
            return;
        }
        else {
            this;
            synchronized (this) {
                if (this.a.contains(listener)) {
                    this.a.add(listener);
                }
            }
        }
    }

    public void b(RFixListener listener) {
        this;
        synchronized (this) {
            this.a.remove(listener);
            return;
        }
    }

    private Object[] a() {
        this;
        synchronized (this) {
            Object[] objectArr0 = this.a.toArray();
        }
        return objectArr0;
    }

    public void a(boolean bool0, int i0, a a) {
        Object[] objectArr0 = super.a();
        for (int i1 = objectArr0.length - 1; i1 >= 0; i1 += 255) {
            (RFixListener)objectArr0[i1].onConfig(bool0, i0, a.b);
        }
    }

    public void a(boolean bool0, int i0, b b) {
        Object[] objectArr0 = super.a();
        for (int i1 = objectArr0.length - 1; i1 >= 0; i1 += 255) {
            (RFixListener)objectArr0[i1].onDownload(bool0, i0, b.c, b.b);
        }
    }

    public void a(boolean bool0, int i0, d d) {
        Object[] objectArr0 = super.a();
        for (int i1 = objectArr0.length - 1; i1 >= 0; i1 += 255) {
            (RFixListener)objectArr0[i1].onInstall(bool0, i0, d.b);
        }
    }

}
