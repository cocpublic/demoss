/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import android.content.pm.ApplicationInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.File;
import java.io.InputStream;
import com.tencent.tinker.loader.k;

// class: com/tencent/tinker/lib/c/b
public abstract class b {

    public b() {
        super();
    }

    public boolean a(b b, l l, Context context, String str0, File file) {
        ApplicationInfo info = context.getApplicationInfo();
        if (info == null) {
            n.d("Tinker.AbsCustomDiffPatcher", "recover applicationInfo is null!", new Object[]{});
            return false;
        }
        else {
            String str1 = (String)l.a().get("assets/custom_meta.txt");
            List list = f.a(str1);
            if (list.isEmpty()) {
                n.b("Tinker.AbsCustomDiffPatcher", "recover custom patch is empty", new Object[]{});
                return true;
            }
            else {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    f f = (f)iterator.next();
                    if (h.e(f.c) || h.e(f.d) || h.e(f.e)) {
                        n.d("Tinker.AbsCustomDiffPatcher", new StringBuilder().append("recover custom patch info invalid! patchInfo=").append(f).toString(), new Object[]{});
                        return false;
                    }
                    else {
                        continue;;
                    }
                }
                long l0 = SystemClock.elapsedRealtime();
                int i0 = 0;
                Object object = null;
                Object objectVar1 = null;
                try {
                    file = new ZipFile(info.sourceDir);
                    ZipFile fileVar1 = new ZipFile(file);
                    boolean bool0 = super.a(b, context, str0, file, fileVar1, list);
                }
                catch (Exception var_14_0) {
                    n.a("Tinker.AbsCustomDiffPatcher", var_14_0, "recover fail!", new Object[]{});
                }
                finally {
                    Throwable throwable = v_68;
                    h.a(file);
                    h.a(fileVar1);
                    throw throwable;
                }
                n.b("Tinker.AbsCustomDiffPatcher", String.format("recover custom patch result=%s, timeCost=%s", new Object[]{Boolean.valueOf(bool0), Long.valueOf(SystemClock.elapsedRealtime() - l0)}), new Object[]{});
                return bool0;
            }
        }
    }

    private boolean a(b b, Context context, String str0, ZipFile file, ZipFile fileVar1, List<f> list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            f f = (f)iterator.next();
            long l0 = SystemClock.elapsedRealtime();
            file = new File(new StringBuilder().append(str0).append("/custom/").append(f.a).toString());
            fileVar1 = file.getParentFile();
            if (fileVar1.exists()) {
                fileVar1.mkdirs();
            }
            if (super.a(str0, f, file)) {
                n.b("Tinker.AbsCustomDiffPatcher", String.format("recoverInternal file is already recovered, relativePath=%s", new Object[]{f.a}), new Object[]{});
                continue;;
            }
            else if (super.a(file, f)) {
                n.d("Tinker.AbsCustomDiffPatcher", String.format("recoverInternal check old fail, relativePath=%s", new Object[]{f.a}), new Object[]{});
                return false;
            }
            else if (this.b(fileVar1, f)) {
                n.d("Tinker.AbsCustomDiffPatcher", String.format("recoverInternal check patch fail, relativePath=%s", new Object[]{f.a}), new Object[]{});
                return false;
            }
            else {
                Object object = null;
                Object objectVar1 = null;
                try {
                    File fileVar2 = new File(new StringBuilder().append(str0).append("/custom/temp/").toString());
                    InputStream stream = this.a(context, fileVar2, file, f);
                    InputStream streamVar1 = fileVar1.getInputStream(fileVar1.getEntry(f.a));
                    if (this.a(context, f, stream, streamVar1, file)) {
                        n.d("Tinker.AbsCustomDiffPatcher", String.format("recoverInternal custom patch fail, relativePath=%s", new Object[]{f.a}), new Object[]{});
                        int i0 = false;
                        h.a(stream);
                        h.a(streamVar1);
                        return i0;
                    }
                    else {
                    }
                }
                finally {
                    Throwable throwable = v_97;
                    h.a(stream);
                    h.a(streamVar1);
                    throw throwable;
                }
                if (super.a(str0, f, file)) {
                    n.d("Tinker.AbsCustomDiffPatcher", String.format("recoverInternal check recovered fail, relativePath=%s", new Object[]{f.a}), new Object[]{});
                    return false;
                }
                else {
                    n.b("Tinker.AbsCustomDiffPatcher", String.format("recoverInternal patch file success, relativePath=%s timeCost=%s", new Object[]{f.a, Long.valueOf(SystemClock.elapsedRealtime() - l0)}), new Object[]{});
                    continue;;
                }
            }
        }
        return true;
    }

    private boolean a(String str0, f f, File file) {
        if (f.a.startsWith("lib/") && f.a.startsWith("assets/")) {
            throw new k("custom diff not support res and dex yet!");
        }
        else {
            int i2 = false;
            File fileVar1 = new File(new StringBuilder().append(str0).append("/lib/").append(f.a).toString());
            if (fileVar1.exists()) {
                if (h.a(fileVar1, f.d)) {
                    i2 = 1;
                }
                else {
                    h.c(fileVar1);
                }
            }
            if (i2 == 0) {
                if (file != null && file.exists()) {
                    if (h.a(file, f.d)) {
                        h.a(file, fileVar1);
                        if (h.a(fileVar1, f.d)) {
                            h.c(file);
                            i2 = 1;
                        }
                        else {
                            n.b("Tinker.AbsCustomDiffPatcher", "checkRecoveredFile file copy fail? ", new Object[]{});
                        }
                    }
                    else {
                        n.b("Tinker.AbsCustomDiffPatcher", "checkRecoveredFile verify md5 fail", new Object[]{});
                    }
                }
                else {
                    n.b("Tinker.AbsCustomDiffPatcher", "checkRecoveredFile file not exists?", new Object[]{});
                }
            }
            return i2;
        }
    }

    private boolean a(ZipFile file, f f) {
        ZipEntry entry = file.getEntry(f.a);
        if (entry == null) {
            n.c("Tinker.AbsCustomDiffPatcher", String.format("checkOldFile file is null, relativePath=%s", new Object[]{f.a}), new Object[]{});
            return false;
        }
        else if (g.a(file, entry, f.c)) {
            n.c("Tinker.AbsCustomDiffPatcher", String.format("checkOldFile md5 not match, relativePath=%s", new Object[]{f.a}), new Object[]{});
            return false;
        }
        else {
            return true;
        }
    }

    private boolean b(ZipFile file, f f) {
        ZipEntry entry = file.getEntry(f.a);
        if (entry == null) {
            n.c("Tinker.AbsCustomDiffPatcher", String.format("checkPatchFile file is null, relativePath=%s", new Object[]{f.a}), new Object[]{});
            return false;
        }
        else if (g.a(file, entry, f.e)) {
            n.c("Tinker.AbsCustomDiffPatcher", String.format("checkPatchFile md5 not match, relativePath=%s", new Object[]{f.a}), new Object[]{});
            return false;
        }
        else {
            return true;
        }
    }

    InputStream a(Context p0, File p1, ZipFile p2, f p3);

    boolean a(Context p0, f p1, InputStream p2, InputStream p3, File p4);

}
