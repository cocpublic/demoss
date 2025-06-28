/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import java.util.HashMap;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Set;
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import com.tencent.tinker.loader.shareutil.k;
import com.tencent.tinker.loader.shareutil.k$a;
import com.tencent.tinker.loader.k;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.d.d;
import com.tencent.tinker.lib.a.a;
import com.tencent.tinker.d.a.j;
import com.tencent.tinker.d.a.i;
import com.tencent.tinker.d.a.h;
import android.content.pm.ApplicationInfo;

// class: com/tencent/tinker/lib/c/k
public class k {

    protected static boolean a(b b, l l, Context context, String str0, File file, boolean bool0, b bVar1) {
        if (b.m()) {
            n.c("Tinker.ResDiffPatchInternal", "patch recover, resource is not enabled", new Object[]{});
            return true;
        }
        else {
            String str1 = (String)l.a().get("assets/res_meta.txt");
            if (str1 == null || str1.length() == 0) {
                n.c("Tinker.ResDiffPatchInternal", "patch recover, resource is not contained", new Object[]{});
                return true;
            }
            else {
                long l0 = SystemClock.elapsedRealtime();
                boolean bool1 = k.a(context, str0, str1, file, bool0);
                long l1 = SystemClock.elapsedRealtime() - l0;
                bVar1.g = l1;
                n.b("Tinker.ResDiffPatchInternal", "recover resource result:%b, cost:%d", new Object[]{Boolean.valueOf(bool1), Long.valueOf(l1)});
                return bool1;
            }
        }
    }

    private static boolean a(Context context, String str0, String str1, File file, boolean bool0) {
        String str2 = new StringBuilder().append(str0).append("/").append("res").append("/").toString();
        if (k.a(context, str2, str1, file, 6, bool0)) {
            n.c("Tinker.ResDiffPatchInternal", "patch recover, extractDiffInternals fail", new Object[]{});
            return false;
        }
        else {
            return true;
        }
    }

    private static boolean a(Context context, String str0, String str1, File file, int i0, boolean bool0) {
        k k = new k();
        k.a(str1, k);
        n.b("Tinker.ResDiffPatchInternal", "res dir: %s, meta: %s", new Object[]{str0, k.toString()});
        b b = b.a(context);
        if (h.e(k.b)) {
            n.c("Tinker.ResDiffPatchInternal", "resource meta file md5 mismatch, type:%s, md5: %s", new Object[]{m.e(i0), k.b});
            b.h().a(file, e.a(i0));
            return false;
        }
        else {
            File fileVar1 = new File(str0);
            File fileVar2 = new File(fileVar1, "res_temp");
            File fileVar3 = new File(fileVar1, "resources.apk");
            if (fileVar3.exists()) {
                if (h.c(fileVar3, k.b)) {
                    n.c("Tinker.ResDiffPatchInternal", "resource file %s is already exist, and md5 match, just return true", new Object[]{fileVar3.getPath()});
                    return true;
                }
                else {
                    n.c("Tinker.ResDiffPatchInternal", new StringBuilder().append("have a mismatch corrupted resource ").append(fileVar3.getPath()).toString(), new Object[]{});
                    fileVar3.delete();
                }
            }
            else {
                fileVar3.getParentFile().mkdirs();
            }
            try {
                ApplicationInfo info = context.getApplicationInfo();
                if (info == null) {
                    n.c("Tinker.ResDiffPatchInternal", "applicationInfo == null!!!!", new Object[]{});
                    return false;
                }
                else {
                    String str2 = info.sourceDir;
                    if (k.a(context, str2, fileVar1, fileVar2, file, k, i0, bool0)) {
                        return false;
                    }
                    else {
                        try {
                            Object object = null;
                            Object objectVar1 = null;
                            Object objectVar2 = null;
                            int i1 = 0;
                            if (fileVar3.exists()) {
                                fileVar3.delete();
                            }
                            j j = new j(new BufferedOutputStream(new FileOutputStream(fileVar3)));
                            if (m.a(33, 1)) {
                                fileVar3.setReadOnly();
                            }
                            i i = new i(str2);
                            i iVar1 = new i(file);
                            for (Enumeration enumeration = i.a(); enumeration.hasMoreElements(); i1 += 1) {
                                h h = (h)enumeration.nextElement();
                                h == null;
                                throw new k("zipEntry is null when get from oldApk");
                                String str3 = h.e();
                                str3.contains("../");
                                continue;;
                                k.a(k.i, str3) && k.d.contains(str3) && k.e.contains(str3) && k.g.contains(str3) && str3.equals("AndroidManifest.xml");
                                k.a(i, h, j);
                            }
                            h hVar1 = i.a("AndroidManifest.xml");
                            if (hVar1 == null) {
                                n.c("Tinker.ResDiffPatchInternal", "manifest patch entry is null. path:AndroidManifest.xml", new Object[]{});
                                b.h().a(file, fileVar3, "AndroidManifest.xml", i0);
                                int i2 = false;
                                b.a(j);
                                b.a(i);
                                b.a(iVar1);
                                h.d(fileVar2);
                                return i2;
                            }
                            else {
                                k.a(i, hVar1, j);
                                i1 += 1;
                                for (Iterator iteratorVar2 = k.g.iterator(); iteratorVar2.hasNext(); i1 += 1) {
                                    String str4 = (String)iteratorVar2.next();
                                    h hVar2 = i.a(str4);
                                    if (hVar2 == null) {
                                        n.c("Tinker.ResDiffPatchInternal", new StringBuilder().append("large patch entry is null. path:").append(str4).toString(), new Object[]{});
                                        b.h().a(file, fileVar3, str4, i0);
                                        int i3 = false;
                                        b.a(j);
                                        b.a(i);
                                        b.a(iVar1);
                                        h.d(fileVar2);
                                        return i3;
                                    }
                                    else {
                                        k$a k$a = (k$a)k.h.get(str4);
                                        k.a(hVar2, k$a.c, k$a.b, j);
                                    }
                                }
                                for (iteratorVar2 = k.c.iterator(); iteratorVar2.hasNext(); i1 += 1) {
                                    String str5 = (String)iteratorVar2.next();
                                    h hVar3 = iVar1.a(str5);
                                    if (hVar3 == null) {
                                        n.c("Tinker.ResDiffPatchInternal", new StringBuilder().append("add patch entry is null. path:").append(str5).toString(), new Object[]{});
                                        b.h().a(file, fileVar3, str5, i0);
                                        int i4 = false;
                                        b.a(j);
                                        b.a(i);
                                        b.a(iVar1);
                                        h.d(fileVar2);
                                        return i4;
                                    }
                                    else {
                                        if (k.f.containsKey(str5)) {
                                            File fileVar4 = (File)k.f.get(str5);
                                            k.a(hVar3, fileVar4, hVar3.b(), j);
                                        }
                                        else {
                                            k.a(iVar1, hVar3, j);
                                        }
                                    }
                                }
                                for (iteratorVar2 = k.e.iterator(); iteratorVar2.hasNext(); i1 += 1) {
                                    String str6 = (String)iteratorVar2.next();
                                    h hVar4 = iVar1.a(str6);
                                    hVar4 == null;
                                    n.c("Tinker.ResDiffPatchInternal", new StringBuilder().append("mod patch entry is null. path:").append(str6).toString(), new Object[]{});
                                    b.h().a(file, fileVar3, str6, i0);
                                    int i5 = false;
                                    b.a(j);
                                    b.a(i);
                                    b.a(iVar1);
                                    h.d(fileVar2);
                                    return i5;
                                    k.f.containsKey(str6);
                                    File fileVar5 = (File)k.f.get(str6);
                                    k.a(hVar4, fileVar5, hVar4.b(), j);
                                    continue;;
                                    k.a(iVar1, hVar4, j);
                                }
                                j.a(i.b());
                            }
                        }
                        finally {
                            Throwable throwable = v_96;
                            b.a(object);
                            b.a(objectVar1);
                            b.a(objectVar2);
                            h.d(fileVar2);
                            throw throwable;
                        }
                        boolean bool1 = h.c(fileVar3, k.b);
                        if (bool1) {
                            n.b("Tinker.ResDiffPatchInternal", "check final new resource file fail path:%s, entry count:%d, size:%d", new Object[]{fileVar3.getAbsolutePath(), Integer.valueOf(i1), Long.valueOf(fileVar3.length())});
                            h.c(fileVar3);
                            b.h().a(file, fileVar3, "resources.apk", i0);
                            return false;
                        }
                        else {
                            n.b("Tinker.ResDiffPatchInternal", "final new resource file:%s, entry count:%d, size:%d", new Object[]{fileVar3.getAbsolutePath(), Integer.valueOf(i1), Long.valueOf(fileVar3.length())});
                        }
                    }
                }
            }
            catch (Throwable var_11_1) {
                throw new k(new StringBuilder().append("patch ").append(m.e(i0)).append(" extract failed (").append(var_11_1.getMessage()).append(").").toString(), var_11_1);
            }
            return true;
        }
    }

    private static boolean a(Context context, String str0, File file, File fileVar1, File fileVar2, k k, int i0, boolean bool0) {
        long l0 = System.currentTimeMillis();
        b b = b.a(context);
        Object object = null;
        Object objectVar1 = null;
        try {
            file = new ZipFile(str0);
            ZipEntry entry = file.getEntry("resources.arsc");
            File fileVar3 = new File(file, "resources.arsc");
            if (entry == null) {
                n.c("Tinker.ResDiffPatchInternal", "resources apk entry is null. path:resources.arsc", new Object[]{});
                b.h().a(fileVar2, fileVar3, "resources.arsc", i0);
                int i1 = false;
                h.a(file);
                h.a(objectVar1);
                return i1;
            }
            else {
                String str1 = String.valueOf(entry.getCrc());
                if (str1.equals(k.a)) {
                    n.d("Tinker.ResDiffPatchInternal", "resources.arsc's crc is not equal, expect crc: %s, got crc: %s", new Object[]{k.a, str1});
                    b.h().a(fileVar2, fileVar3, "resources.arsc", i0);
                    int i2 = false;
                    h.a(file);
                    h.a(objectVar1);
                    return i2;
                }
                else {
                    if (k.g.isEmpty() && k.f.isEmpty()) {
                        n.b("Tinker.ResDiffPatchInternal", "no large modify or store resources, just return", new Object[]{});
                        int i3 = true;
                        h.a(file);
                        h.a(objectVar1);
                        return i3;
                    }
                    else {
                        fileVar1 = new ZipFile(fileVar2);
                        Iterator iteratorVar1 = k.f.keySet().iterator();
                        while (iteratorVar1.hasNext()) {
                            String str2 = (String)iteratorVar1.next();
                            long l1 = System.currentTimeMillis();
                            File fileVar4 = new File(fileVar1, str2);
                            h.g(fileVar4);
                            ZipEntry entryVar1 = fileVar1.getEntry(str2);
                            if (entryVar1 == null) {
                                n.c("Tinker.ResDiffPatchInternal", new StringBuilder().append("store patch entry is null. path:").append(str2).toString(), new Object[]{});
                                b.h().a(fileVar2, fileVar4, str2, i0);
                                int i4 = false;
                                h.a(file);
                                h.a(fileVar1);
                                return i4;
                            }
                            else {
                                k.a(fileVar1, entryVar1, fileVar4, null, 0);
                                if (fileVar4.length() != entryVar1.getSize()) {
                                    n.c("Tinker.ResDiffPatchInternal", "resource meta file size mismatch, type:%s, name: %s, patch size: %d, file size; %d", new Object[]{m.e(i0), str2, Long.valueOf(entryVar1.getSize()), Long.valueOf(fileVar4.length())});
                                    b.h().a(fileVar2, e.a(i0));
                                    int i5 = false;
                                    h.a(file);
                                    h.a(fileVar1);
                                    return i5;
                                }
                                else {
                                    k.f.put(str2, fileVar4);
                                    n.c("Tinker.ResDiffPatchInternal", "success recover store file:%s, file size:%d, use time:%d", new Object[]{fileVar4.getPath(), Long.valueOf(fileVar4.length()), Long.valueOf(System.currentTimeMillis() - l1)});
                                    continue;;
                                }
                            }
                        }
                        iteratorVar1 = k.g.iterator();
                        while (iteratorVar1.hasNext()) {
                            String str3 = (String)iteratorVar1.next();
                            long l2 = System.currentTimeMillis();
                            k$a k$a = (k$a)k.h.get(str3);
                            k$a == null;
                            n.c("Tinker.ResDiffPatchInternal", "resource not found largeModeInfo, type:%s, name: %s", new Object[]{m.e(i0), str3});
                            b.h().a(fileVar2, e.a(i0));
                            int i6 = false;
                            h.a(file);
                            h.a(fileVar1);
                            return i6;
                            k$a.c = new File(fileVar1, str3);
                            h.g(k$a.c);
                            h.e(k$a.a);
                            n.c("Tinker.ResDiffPatchInternal", "resource meta file md5 mismatch, type:%s, name: %s, md5: %s", new Object[]{m.e(i0), str3, k$a.a});
                            b.h().a(fileVar2, e.a(i0));
                            int i7 = false;
                            h.a(file);
                            h.a(fileVar1);
                            return i7;
                            ZipEntry entryVar2 = fileVar1.getEntry(str3);
                            entryVar2 == null;
                            n.c("Tinker.ResDiffPatchInternal", new StringBuilder().append("large mod patch entry is null. path:").append(str3).toString(), new Object[]{});
                            b.h().a(fileVar2, k$a.c, str3, i0);
                            int i8 = false;
                            h.a(file);
                            h.a(fileVar1);
                            return i8;
                            ZipEntry entryVar3 = file.getEntry(str3);
                            entryVar3 == null;
                            n.c("Tinker.ResDiffPatchInternal", new StringBuilder().append("resources apk entry is null. path:").append(str3).toString(), new Object[]{});
                            b.h().a(fileVar2, k$a.c, str3, i0);
                            int i9 = false;
                            h.a(file);
                            h.a(fileVar1);
                            return i9;
                            Object objectVar2 = null;
                            Object objectVar3 = null;
                            try {
                                InputStream stream = file.getInputStream(entryVar3);
                                InputStream streamVar1 = fileVar1.getInputStream(entryVar2);
                                c.a(context, bool0).a(stream, streamVar1, k$a.c);
                            }
                            finally {
                                Throwable throwable = v_296;
                                b.a(stream);
                                b.a(streamVar1);
                                throw throwable;
                            }
                            h.a(k$a.c, k$a.a);
                            n.c("Tinker.ResDiffPatchInternal", "Failed to recover large modify file:%s", new Object[]{k$a.c.getPath()});
                            h.c(k$a.c);
                            b.h().a(fileVar2, k$a.c, str3, i0);
                            int i10 = false;
                            h.a(file);
                            h.a(fileVar1);
                            return i10;
                            n.c("Tinker.ResDiffPatchInternal", "success recover large modify file:%s, file size:%d, use time:%d", new Object[]{k$a.c.getPath(), Long.valueOf(k$a.c.length()), Long.valueOf(System.currentTimeMillis() - l2)});
                        }
                        n.c("Tinker.ResDiffPatchInternal", "success recover all large modify and store resources use time:%d", new Object[]{Long.valueOf(System.currentTimeMillis() - l0)});
                    }
                }
            }
        }
        catch (Throwable var_13_1) {
            throw new k(new StringBuilder().append("patch ").append(m.e(i0)).append(" extract failed (").append(var_13_1.getMessage()).append(").").toString(), var_13_1);
        }
        finally {
            Throwable throwableVar1 = v_15;
            h.a(file);
            h.a(objectVar1);
            throw throwableVar1;
        }
        return true;
    }

}
