/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/d;

import android.content.Context;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.e.d;
import com.tencent.tinker.lib.g.b;
import java.io.File[];
import java.io.File;

// class: com/tencent/tinker/lib/d/a
public class a implements c {
    final protected Context a;

    public a(Context context) {
        super();
        this.a = context;
    }

    public void a(File file, int i0) {
        n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadPatchListenerReceiveFail: patch receive fail: %s, code: %d", new Object[]{file.getAbsolutePath(), Integer.valueOf(i0)});
    }

    public void a(String str0, String str1, File file, String str2) {
        n.b("Tinker.DefaultLoadReporter", new StringBuilder().append("patch loadReporter onLoadPatchVersionChanged: patch version change from ").append(str0).append(" to ").append(str1).toString(), new Object[]{});
        if (str0 == null || str1 == null) {
        }
        else if (str0.equals(str1)) {
        }
        else if (b.a(this.a).d()) {
        }
        else {
            b.a(this.a).b(str1);
            File[] fileArr0 = file.listFiles();
            if (fileArr0 != null) {
                for (int i1 = 0; i1 < fileArr0.length; i1 += 1) {
                    File fileVar1 = fileArr0[i1];
                    String str3 = fileVar1.getName();
                    if (fileVar1.isDirectory() && str3.equals(str2)) {
                        h.d(fileVar1);
                    }
                }
            }
        }
    }

    public void a(int i0, Throwable throwable) {
n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadInterpret: type: %d, throwable: %s", new Object[]{Integer.valueOf(i0), throwable});
        switch(i0) {
            case 1: {
                n.d("Tinker.DefaultLoadReporter", "patch loadReporter onLoadInterpret fail, can get instruction set from existed oat file", new Object[]{});
                break;;
            }
            case 2: {
                n.d("Tinker.DefaultLoadReporter", "patch loadReporter onLoadInterpret fail, command line to interpret return error", new Object[]{});
                break;;
            }
            case 0: {
                n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadInterpret ok", new Object[]{});
                break;;
            }
        }
        this.b();
    }

    public void a(File file, int i0, boolean bool0) {
        n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadFileNotFound: patch file not found: %s, fileType: %d, isDirectory: %b", new Object[]{file.getAbsolutePath(), Integer.valueOf(i0), Boolean.valueOf(bool0)});
        if (i0 == 4) {
            this.b();
        }
        else {
            this.a();
        }
    }

    public void b(File file, int i0) {
        n.b("Tinker.DefaultLoadReporter", "patch load Reporter onLoadFileMd5Mismatch: patch file md5 mismatch file: %s, fileType: %d", new Object[]{file.getAbsolutePath(), Integer.valueOf(i0)});
        this.a();
    }

    public void a(String str0, String str1, File file) {
        n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadPatchInfoCorrupted: patch info file damage: %s, from version: %s to version: %s", new Object[]{file.getAbsolutePath(), str0, str1});
        this.a();
    }

    public void a(File file, int i0, long l1) {
        n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadResult: patch load result, path:%s, code: %d, cost: %dms", new Object[]{file.getAbsolutePath(), Integer.valueOf(i0), Long.valueOf(l1)});
    }

    public void a(Throwable throwable, int i0) {
        switch(i0) {
            case -2: {
                if (throwable.getMessage().contains("checkDexInstall failed")) {
                    n.d("Tinker.DefaultLoadReporter", new StringBuilder().append("patch loadReporter onLoadException: tinker dex check fail:").append(throwable.getMessage()).toString(), new Object[]{});
                }
                else {
                    n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadException: patch load dex exception: %s", new Object[]{throwable});
                }
                m.f(this.a);
                n.b("Tinker.DefaultLoadReporter", "dex exception disable tinker forever with sp", new Object[]{});
                break;;
            }
            case -3: {
                if (throwable.getMessage().contains("checkResInstall failed")) {
                    n.d("Tinker.DefaultLoadReporter", new StringBuilder().append("patch loadReporter onLoadException: tinker res check fail:").append(throwable.getMessage()).toString(), new Object[]{});
                }
                else {
                    n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadException: patch load resource exception: %s", new Object[]{throwable});
                }
                m.f(this.a);
                n.b("Tinker.DefaultLoadReporter", "res exception disable tinker forever with sp", new Object[]{});
                break;;
            }
            case -4: {
                n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadException: patch load unCatch exception: %s", new Object[]{throwable});
                m.f(this.a);
                n.b("Tinker.DefaultLoadReporter", "unCaught exception disable tinker forever with sp", new Object[]{});
                String str0 = h.d(this.a);
                if (m.b(str0)) {
                    File file = h.c(this.a);
                    h.c(file);
                    n.d("Tinker.DefaultLoadReporter", new StringBuilder().append("tinker uncaught real exception:").append(str0).toString(), new Object[]{});
                    break;;
                }
            }
            case -1: {
                n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadException: patch load unknown exception: %s", new Object[]{throwable});
                break;;
            }
        }
        n.d("Tinker.DefaultLoadReporter", "tinker load exception, welcome to submit issue to us: https://github.com/Tencent/tinker/issues", new Object[]{});
        n.a("Tinker.DefaultLoadReporter", throwable, "tinker load exception", new Object[]{});
        b.a(this.a).f();
        this.a();
    }

    public void c(File file, int i0) {
        n.b("Tinker.DefaultLoadReporter", "patch loadReporter onLoadPackageCheckFail: load patch package check fail file path: %s, errorCode: %d", new Object[]{file.getAbsolutePath(), Integer.valueOf(i0)});
        this.a();
    }

    public void a() {
        b b = b.a(this.a);
        b.s();
    }

    public boolean b() {
        b b = b.a(this.a);
        if (b.d()) {
            return false;
        }
        else {
            File file = b.b().h;
            if (file != null && b.a(this.a).a(h.f(file))) {
                n.b("Tinker.DefaultLoadReporter", "try to repair oat file on patch process", new Object[]{});
                c.a(this.a, file.getAbsolutePath());
                return true;
            }
            else {
                return false;
            }
        }
    }

}
