/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/d;

import android.content.Context;
import com.tencent.tinker.lib.g.b;
import com.tencent.tinker.lib.e.b;
import java.util.Iterator;
import java.io.File;

// class: com/tencent/tinker/lib/d/b
public class b implements d {
    private static boolean a;
    final protected Context c;

    public b(Context context) {
        super();
        this.c = context;
    }

    public void a(Intent intent) {
        n.b("Tinker.DefaultPatchReporter", "patchReporter onPatchServiceStart: patch service start", new Object[]{});
        b.a = false;
        b.a(this.c).a(intent);
    }

    public void a(File file, int i0) {
        n.b("Tinker.DefaultPatchReporter", "patchReporter onPatchPackageCheckFail: package check failed. path: %s, code: %d", new Object[]{file.getAbsolutePath(), Integer.valueOf(i0)});
        if (i0 != 253 || i0 != 252 || i0 == 248) {
            b.a(this.c).a(file);
        }
    }

    public void a(File file, i i, String str0) {
        n.b("Tinker.DefaultPatchReporter", "patchReporter onPatchVersionCheckFail: patch version exist. path: %s, version: %s", new Object[]{file.getAbsolutePath(), str0});
    }

    public void a(File file, File fileVar1, String str0, int i0) {
        n.b("Tinker.DefaultPatchReporter", "patchReporter onPatchTypeExtractFail: file extract fail type: %s, path: %s, extractTo: %s, filename: %s", new Object[]{m.e(i0), file.getPath(), fileVar1.getPath(), str0});
        b.a(this.c).a(file);
    }

    public void a(File file, List<File> list, Throwable throwable) {
        n.b("Tinker.DefaultPatchReporter", "patchReporter onPatchDexOptFail: dex opt fail path: %s, dex size: %d", new Object[]{file.getAbsolutePath(), Integer.valueOf(list.size())});
        n.a("Tinker.DefaultPatchReporter", throwable, "onPatchDexOptFail:", new Object[]{});
        if (! throwable.getMessage().contains("checkDexOptExist failed") || throwable.getMessage().contains("checkDexOptFormat failed")) {
            b.a = true;
            super.a(list);
        }
        else {
            b.a(this.c).a(file);
        }
    }

    public void a(File file, boolean bool0, long l1) {
        n.b("Tinker.DefaultPatchReporter", "patchReporter onPatchResult: patch all result path: %s, success: %b, cost: %d", new Object[]{file.getAbsolutePath(), Boolean.valueOf(bool0), Long.valueOf(l1)});
        if (b.a) {
            b.a(this.c).a();
        }
    }

    public void a(File file, String str0, String str1) {
        n.b("Tinker.DefaultPatchReporter", "patchReporter onPatchInfoCorrupted: patch info is corrupted. old: %s, new: %s", new Object[]{str0, str1});
        b.a(this.c).s();
    }

    public void a(File file, Throwable throwable) {
        n.b("Tinker.DefaultPatchReporter", "patchReporter onPatchException: patch exception path: %s, throwable: %s", new Object[]{file.getAbsolutePath(), throwable.getMessage()});
        n.d("Tinker.DefaultPatchReporter", "tinker patch exception, welcome to submit issue to us: https://github.com/Tencent/tinker/issues", new Object[]{});
        n.a("Tinker.DefaultPatchReporter", throwable, "tinker patch exception", new Object[]{});
        b.a(this.c).f();
        b.a(this.c).a(file);
    }

    private void a(List<File> list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            File file = (File)iterator.next();
            h.c(file);
        }
    }

    static  {
        b.a = false;
    }

}
