/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/service;

import android.content.Context;
import java.io.File;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.e.d;

// class: com/tencent/tinker/lib/service/DefaultTinkerResultService
public class DefaultTinkerResultService {

    public DefaultTinkerResultService() {
        super();
    }

    public void a(b b) {
        if (b == null) {
            n.d("Tinker.DefaultTinkerResultService", "DefaultTinkerResultService received null result!!!!", new Object[]{});
        }
        else {
            n.b("Tinker.DefaultTinkerResultService", "DefaultTinkerResultService received a result:%s ", new Object[]{b.toString()});
            a.a(this.getApplicationContext());
            if (b.a) {
                this.a(new File(b.b));
                if (this.b(b)) {
                    Process.killProcess(Process.myPid());
                }
                else {
                    n.b("Tinker.DefaultTinkerResultService", "I have already install the newly patch version!", new Object[]{});
                }
            }
        }
    }

    public void a(File file) {
        if (h.a(file)) {
        }
        else {
            n.c("Tinker.DefaultTinkerResultService", "deleteRawPatchFile rawFile path: %s", new Object[]{file.getPath()});
            String str0 = file.getName();
            if (! str0.startsWith("patch-") || str0.endsWith(".apk")) {
                h.c(file);
            }
            else {
                File fileVar1 = file.getParentFile();
                if (fileVar1.getName().startsWith("patch-")) {
                    h.c(file);
                }
                else {
                    File fileVar2 = fileVar1.getParentFile();
                    if (fileVar2.getName().equals("tinker") && fileVar2.getName().equals("wc_tinker_dir")) {
                        h.c(file);
                    }
                }
            }
        }
    }

    public boolean b(b b) {
        b bVar1 = b.a(this.getApplicationContext());
        if (bVar1.j()) {
            d d = bVar1.b();
            if (d != null) {
                String str0 = d.b;
                if (b.l != null && b.l.equals(str0)) {
                    return false;
                }
            }
        }
        return true;
    }

}
