/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Iterator;
import java.io.File;
import java.io.InputStream;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.d.d;
import com.tencent.tinker.lib.a.a;
import com.tencent.tinker.loader.shareutil.b;
import com.tencent.tinker.loader.k;
import android.content.pm.ApplicationInfo;

// class: com/tencent/tinker/lib/c/l
public class l {

    protected static boolean a(b b, l l, Context context, String str0, File file, boolean bool0, b bVar1) {
        if (b.l()) {
            n.c("Tinker.BsDiffPatchInternal", "patch recover, library is not enabled", new Object[]{});
            return true;
        }
        else {
            String str1 = (String)l.a().get("assets/so_meta.txt");
            if (str1 == null) {
                n.c("Tinker.BsDiffPatchInternal", "patch recover, library is not contained", new Object[]{});
                return true;
            }
            else {
                long l0 = SystemClock.elapsedRealtime();
                boolean bool1 = l.a(context, str0, str1, file, bool0);
                long l1 = SystemClock.elapsedRealtime() - l0;
                bVar1.f = l1;
                n.b("Tinker.BsDiffPatchInternal", "recover lib result:%b, cost:%d", new Object[]{Boolean.valueOf(bool1), Long.valueOf(l1)});
                return bool1;
            }
        }
    }

    private static boolean a(Context context, String str0, String str1, File file, boolean bool0) {
        String str2 = new StringBuilder().append(str0).append("/").append("lib").append("/").toString();
        return l.a(context, str2, str1, file, 5, bool0);
    }

    private static boolean a(Context context, String str0, String str1, File file, int i0, boolean bool0) {
        ArrayList list = new ArrayList();
        b.a(str1, list);
        if (list.isEmpty()) {
            n.c("Tinker.BsDiffPatchInternal", "extract patch list is empty! type:%s:", new Object[]{m.e(i0)});
            return true;
        }
        else {
            File fileVar1 = new File(str0);
            if (fileVar1.exists()) {
                fileVar1.mkdirs();
            }
            b b = b.a(context);
            ApplicationInfo info = context.getApplicationInfo();
            if (info == null) {
                n.c("Tinker.BsDiffPatchInternal", "applicationInfo == null!!!!", new Object[]{});
                return false;
            }
            else {
                Object object = null;
                Object objectVar1 = null;
                try {
                    String str2 = info.sourceDir;
                    file = new ZipFile(str2);
                    fileVar1 = new ZipFile(file);
                    Iterator iterator = list.iterator();
                    while (iterator.hasNext()) {
                        b bVar1 = (b)iterator.next();
                        long l0 = System.currentTimeMillis();
                        String str3 = bVar1.e;
                        String str4 = str3.equals("") ? new StringBuilder().append(bVar1.e).append("/").append(bVar1.a).toString() : bVar1.a;
                        String str5 = bVar1.b;
                        if (h.e(str5)) {
                            n.c("Tinker.BsDiffPatchInternal", "meta file md5 mismatch, type:%s, name: %s, md5: %s", new Object[]{m.e(i0), bVar1.a, bVar1.b});
                            b.h().a(file, e.a(i0));
                            int i1 = false;
                            h.a(file);
                            h.a(fileVar1);
                            return i1;
                        }
                        else {
                            String str6 = new StringBuilder().append(bVar1.e).append("/").append(bVar1.a).toString();
                            File fileVar2 = new File(new StringBuilder().append(str0).append(str6).toString());
                            if (fileVar2.exists()) {
                                if (str5.equals(h.f(fileVar2))) {
                                    n.c("Tinker.BsDiffPatchInternal", "bsdiff file %s is already exist, and md5 match, just continue", new Object[]{fileVar2.getPath()});
                                    continue;;
                                }
                                else {
                                    n.c("Tinker.BsDiffPatchInternal", new StringBuilder().append("have a mismatch corrupted dex ").append(fileVar2.getPath()).toString(), new Object[]{});
                                    fileVar2.delete();
                                }
                            }
                            else {
                                fileVar2.getParentFile().mkdirs();
                            }
                            String str7 = bVar1.d;
                            ZipEntry entry = fileVar1.getEntry(var_18_0);
                            if (entry == null) {
                                n.c("Tinker.BsDiffPatchInternal", new StringBuilder().append("patch entry is null. path:").append(var_18_0).toString(), new Object[]{});
                                b.h().a(file, fileVar2, bVar1.a, i0);
                                int i2 = false;
                                h.a(file);
                                h.a(fileVar1);
                                return i2;
                            }
                            else {
                                if (str7.equals("0")) {
                                    if (l.a(fileVar1, entry, fileVar2, str5, 0)) {
                                        n.c("Tinker.BsDiffPatchInternal", new StringBuilder().append("Failed to extract file ").append(fileVar2.getPath()).toString(), new Object[]{});
                                        b.h().a(file, fileVar2, bVar1.a, i0);
                                        int i3 = false;
                                        h.a(file);
                                        h.a(fileVar1);
                                        return i3;
                                    }
                                }
                                else if (h.e(str7)) {
                                    n.c("Tinker.BsDiffPatchInternal", "meta file md5 mismatch, type:%s, name: %s, md5: %s", new Object[]{m.e(i0), bVar1.a, str7});
                                    b.h().a(file, e.a(i0));
                                    int i4 = false;
                                    h.a(file);
                                    h.a(fileVar1);
                                    return i4;
                                }
                                else {
                                    ZipEntry entryVar1 = file.getEntry(var_18_0);
                                    if (entryVar1 == null) {
                                        n.c("Tinker.BsDiffPatchInternal", new StringBuilder().append("apk entry is null. path:").append(var_18_0).toString(), new Object[]{});
                                        b.h().a(file, fileVar2, bVar1.a, i0);
                                        int i5 = false;
                                        h.a(file);
                                        h.a(fileVar1);
                                        return i5;
                                    }
                                    else {
                                        String str8 = bVar1.c;
                                        String str9 = String.valueOf(entryVar1.getCrc());
                                        if (str9.equals(str8)) {
                                            n.d("Tinker.BsDiffPatchInternal", "apk entry %s crc is not equal, expect crc: %s, got crc: %s", new Object[]{var_18_0, str8, str9});
                                            b.h().a(file, fileVar2, bVar1.a, i0);
                                            int i6 = false;
                                            h.a(file);
                                            h.a(fileVar1);
                                            return i6;
                                        }
                                        else {
                                            Object objectVar2 = null;
                                            Object objectVar3 = null;
                                            try {
                                                InputStream stream = file.getInputStream(entryVar1);
                                                InputStream streamVar1 = fileVar1.getInputStream(entry);
                                                c.a(context, bool0).a(stream, streamVar1, fileVar2);
                                                goto 949;
                                            }
                                            finally {
                                                Throwable throwable = v_271;
                                                b.a(stream);
                                                b.a(streamVar1);
                                                throw throwable;
                                            }
                                            if (h.a(fileVar2, str5)) {
                                                n.c("Tinker.BsDiffPatchInternal", new StringBuilder().append("Failed to recover diff file ").append(fileVar2.getPath()).toString(), new Object[]{});
                                                b.h().a(file, fileVar2, bVar1.a, i0);
                                                h.c(fileVar2);
                                                int i7 = false;
                                                h.a(file);
                                                h.a(fileVar1);
                                                return i7;
                                            }
                                            else {
                                                n.c("Tinker.BsDiffPatchInternal", "success recover bsdiff file: %s, use time: %d", new Object[]{fileVar2.getPath(), Long.valueOf(System.currentTimeMillis() - l0)});
                                            }
                                        }
                                    }
                                }
                                continue;;
                            }
                        }
                    }
                }
                catch (Throwable var_12_1) {
                    throw new k(new StringBuilder().append("patch ").append(m.e(i0)).append(" extract failed (").append(var_12_1.getMessage()).append(").").toString(), var_12_1);
                }
                finally {
                    Throwable throwableVar1 = v_40;
                    h.a(file);
                    h.a(fileVar1);
                    throw throwableVar1;
                }
                return true;
            }
        }
    }

}
