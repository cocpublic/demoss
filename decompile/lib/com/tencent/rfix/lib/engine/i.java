/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/engine;

import android.content.Context;

// class: com/tencent/rfix/lib/engine/i
public class i {
    protected String a;
    protected int b;

    public i(Context context) {
        super(context);
        this.b = 0;
    }

    public void a(Intent intent) {
        super.a(intent);
        this.a = TinkerPatchService.c(intent);
        RFixLog.c("RFix.TinkerPatchReporter", String.format("onPatchServiceStart resultServiceClass=%s", new Object[]{this.a}));
    }

    public void a(File file, int i0) {
        super.a(file, i0);
        this.b = 65336 + i0;
    }

    public void a(File file, i i, String str0) {
        super.a(file, i, str0);
        this.b = 65236;
    }

    public void a(File file, File fileVar1, String str0, int i0) {
        super.a(file, fileVar1, str0, i0);
        this.b = 65235;
    }

    public void a(File file, List<File> list, Throwable throwable) {
        super.a(file, list, throwable);
        this.b = 65234;
    }

    public void a(File file, String str0, String str1) {
        super.a(file, str0, str1);
        this.b = 65233;
    }

    public void a(File file, Throwable throwable) {
        super.a(file, throwable);
        this.b = 65232;
    }

    public void a(File file, boolean bool0, long l1) {
        super.a(file, bool0, l1);
        long l2 = a.c(0);
        long l3 = a.c(1);
        long l4 = a.c(2);
        long l5 = a.c(3);
        long l6 = a.c(4);
        RFixLog.c("RFix.TinkerPatchReporter", String.format("onPatchResult resultServiceClass=%s lastResultCode=%s dexRecTime=%s dexOptTime=%s libRecTime=%s resRecTime=%s dexOptWaitTime=%s", new Object[]{this.a, Integer.valueOf(this.b), Long.valueOf(l2), Long.valueOf(l3), Long.valueOf(l4), Long.valueOf(l5), Long.valueOf(l6)}));
        TinkerResultService.a(this.c, this.a, this.b, l2, l3, l4, l5, l6);
    }

}
