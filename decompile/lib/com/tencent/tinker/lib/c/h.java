/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Set;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;
import java.util.Vector;
import java.io.File;
import java.io.File[];
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import com.tencent.tinker.lib.d.d;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.loader.k;
import com.tencent.tinker.loader.shareutil.e;
import com.tencent.tinker.loader.shareutil.d;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.d.a.a;
import com.tencent.tinker.c.a.a;
import android.content.pm.ApplicationInfo;

// class: com/tencent/tinker/lib/c/h
public class h {
    private static ArrayList<File> a;
    private static ArrayList<d> b;
    private static HashMap<d, File> c;
    private static boolean d;

    protected static boolean a(b b, l l, Context context, String str0, File file, boolean bool0, b bVar1) {
        if (b.k()) {
            n.c("Tinker.DexDiffPatchInternal", "patch recover, dex is not enabled", new Object[]{});
            return true;
        }
        else {
            String str1 = (String)l.a().get("assets/dex_meta.txt");
            if (str1 == null) {
                n.c("Tinker.DexDiffPatchInternal", "patch recover, dex is not contained", new Object[]{});
                return true;
            }
            else {
                long l0 = SystemClock.elapsedRealtime();
                boolean bool1 = h.a(context, str0, str1, file, bool0, bVar1);
                long l1 = SystemClock.elapsedRealtime() - l0;
                bVar1.e = l1;
                n.b("Tinker.DexDiffPatchInternal", "recover dex result:%b, cost:%d", new Object[]{Boolean.valueOf(bool1), Long.valueOf(l1)});
                return bool1;
            }
        }
    }

    protected static boolean a(File file, b b) {
        if (h.a.isEmpty()) {
            return true;
        }
        else {
            int i1 = h.b.size() * 30;
            if (i1 > 120) {
                i1 = 120;
            }
            n.b("Tinker.DexDiffPatchInternal", "raw dex count: %d, dex opt dex count: %d, final wait times: %d", new Object[]{Integer.valueOf(h.b.size()), Integer.valueOf(h.a.size()), Integer.valueOf(i1)});
            for (int i2 = 0; i2 < i1; i2 += 1) {
                if (h.a(h.a, i2 + 1)) {
                    try {
                        Thread.sleep(10000L);
                        continue;;
                    }
                    catch (InterruptedException var_4_2) {
                        n.d("Tinker.DexDiffPatchInternal", new StringBuilder().append("thread sleep InterruptedException e:").append(var_4_2).toString(), new Object[]{});
                    }
                }
            }
            ArrayList list = new ArrayList();
            Iterator iterator = h.a.iterator();
            while (iterator.hasNext()) {
                File fileVar1 = (File)iterator.next();
                n.b("Tinker.DexDiffPatchInternal", "check dex optimizer file exist: %s, size %d", new Object[]{fileVar1.getPath(), Long.valueOf(fileVar1.length())});
                if (h.a(fileVar1) && h.b(fileVar1)) {
                    n.d("Tinker.DexDiffPatchInternal", "final parallel dex optimizer file %s is not exist, return false", new Object[]{fileVar1.getName()});
                    list.add(fileVar1);
                }
            }
            if (list.isEmpty()) {
                b.h().a(file, list, new k("checkDexOptExist failed"));
                return false;
            }
            else {
                if (Build$VERSION.SDK_INT >= 21) {
                    Object object = null;
                    Iterator iteratorVar1 = h.a.iterator();
                    while (iteratorVar1.hasNext()) {
                        File fileVar2 = (File)iteratorVar1.next();
                        if (h.b(fileVar2)) {
                            continue;;
                        }
                        else {
                            n.b("Tinker.DexDiffPatchInternal", "check dex optimizer file format: %s, size %d", new Object[]{fileVar2.getName(), Long.valueOf(fileVar2.length())});
                            try {
                                int i3 = e.a(fileVar2);
                                goto 380;
                            }
                            catch (IOException var_8_2) {
                                continue;;
                            }
                            if (i3 == 1) {
                                Object objectVar1 = null;
                                try {
                                    e e = new e(fileVar2);
                                }
                                catch (Throwable var_9_0) {
                                    n.d("Tinker.DexDiffPatchInternal", "final parallel dex optimizer file %s is not elf format, return false", new Object[]{fileVar2.getName()});
                                    list.add(fileVar2);
                                    Throwable throwable = var_9_0;
                                }
                                finally {
                                    Throwable throwableVar1 = v_110;
                                    b.a(e);
                                    throw throwableVar1;
                                }
                            }
                            continue;;
                        }
                    }
                    if (list.isEmpty()) {
                        k k = object == null ? new k("checkDexOptFormat failed", object) : new k("checkDexOptFormat failed");
                        b.h().a(file, list, k);
                        return false;
                    }
                }
                return true;
            }
        }
    }

    private static boolean a(Context context, String str0, String str1, File file, boolean bool0, b b) {
        String str2 = new StringBuilder().append(str0).append("/").append("dex").append("/").toString();
        a.a(0);
        if (h.a(context, str2, str1, file, 3)) {
            n.c("Tinker.DexDiffPatchInternal", "patch recover, extractDiffInternals fail", new Object[]{});
            return false;
        }
        else {
            a.b(0);
            File fileVar1 = new File(str2);
            File[] fileArr0 = fileVar1.listFiles();
            ArrayList list = new ArrayList();
            if (fileArr0 != null) {
                for (int i1 = 0; i1 < fileArr0.length; i1 += 1) {
                    File fileVar2 = fileArr0[i1];
                    String str3 = fileVar2.getName();
                    if (fileVar2.isFile()) {
                        if (str3.endsWith(".dex") || str3.endsWith(".jar") || str3.endsWith(".apk")) {
                            list.add(fileVar2);
                        }
                    }
                }
            }
            n.b("Tinker.DexDiffPatchInternal", new StringBuilder().append("legal files to do dexopt: ").append(list).toString(), new Object[]{});
            String str4 = new StringBuilder().append(str0).append("/").append("odex").append("/").toString();
            a.a(1);
            boolean bool1 = h.a(context, list, str4, file, bool0, b);
            a.b(1);
            return bool1;
        }
    }

    private static boolean a(String str0) {
        h.c.clear();
        if (! h.b.isEmpty() || h.d) {
            return false;
        }
        else {
            Object object = null;
            Object objectVar1 = null;
            Iterator iterator = h.b.iterator();
            while (iterator.hasNext()) {
                d d = (d)iterator.next();
                File file = new File(new StringBuilder().append(str0).append(d.j).toString());
                String str1 = file.getName();
                if (c.a.matcher(str1).matches()) {
                    h.c.put(d, file);
                }
                if (d.a.startsWith("test.dex")) {
                }
            }
            if (d != null) {
                h.c.put(m.a(d, h.c.size() + 1), file);
            }
            File fileVar1 = new File(str0, "tinker_classN.apk");
            int i2 = true;
            Iterator iteratorVar2;
            if (fileVar1.exists()) {
                iteratorVar2 = h.c.keySet().iterator();
                while (iteratorVar2.hasNext()) {
                    d dVar1 = (d)iteratorVar2.next();
                    h.a(fileVar1, dVar1.a, dVar1.c);
                    n.d("Tinker.DexDiffPatchInternal", "verify dex file md5 error, entry name; %s, file len: %d", new Object[]{dVar1.a, Long.valueOf(fileVar1.length())});
                    i2 = 0;
                    break;;
                }
                if (i2 == 0) {
                    h.c(fileVar1);
                }
            }
            else {
                i2 = 0;
            }
            if (i2 != 0) {
                iteratorVar2 = h.c.values().iterator();
                while (iteratorVar2.hasNext()) {
                    File fileVar2 = (File)iteratorVar2.next();
                    h.c(fileVar2);
                }
            }
            return i2;
        }
    }

    private static ZipEntry a(ZipEntry entry, String str0) {
        ZipEntry entryVar1 = new ZipEntry(str0);
        entryVar1.setMethod(0);
        entryVar1.setCompressedSize(entry.getSize());
        entryVar1.setSize(entry.getSize());
        entryVar1.setCrc(entry.getCrc());
        return entryVar1;
    }

    private static boolean a(Context context, File file, String str0) {
        if (! h.b.isEmpty() || h.d) {
            return true;
        }
        else {
            File fileVar1 = new File(str0, "tinker_classN.apk");
            if (h.c.isEmpty()) {
                n.c("Tinker.DexDiffPatchInternal", "classNDexInfo size: %d, no need to merge classN dex files", new Object[]{Integer.valueOf(h.c.size())});
                return true;
            }
            else {
                Iterator iteratorVar2;
                long l0 = System.currentTimeMillis();
                int i2 = true;
                Object object = null;
                try {
                    if (fileVar1.exists()) {
                        fileVar1.delete();
                    }
                    a a = new a(new BufferedOutputStream(new FileOutputStream(fileVar1)));
                    if (m.a(33, 1)) {
                        fileVar1.setReadOnly();
                    }
                    iteratorVar2 = h.c.keySet().iterator();
                    while (iteratorVar2.hasNext()) {
                        d d = (d)iteratorVar2.next();
                        File fileVar2 = (File)h.c.get(d);
                        InputStream stream;
                        if (d.i) {
                            Object objectVar1 = null;
                            Object objectVar2 = null;
                            try {
                                file = new ZipFile(fileVar2);
                                ZipEntry entry = file.getEntry("classes.dex");
                                ZipEntry entryVar1 = h.a(entry, d.a);
                                stream = file.getInputStream(entry);
                                try {
                                    a.a(entryVar1);
                                    b.a(stream, a);
                                }
                                finally {
                                    Throwable throwable = v_69;
                                    a.a();
                                    throw throwable;
                                }
                            }
                            finally {
                                Throwable throwableVar1 = v_63;
                                b.a(stream);
                                b.a(file);
                                throw throwableVar1;
                            }
                            continue;;
                        }
                        else {
                            ZipEntry entryVar2 = new ZipEntry(d.a);
                            entryVar2.setMethod(0);
                            entryVar2.setCompressedSize(fileVar2.length());
                            entryVar2.setSize(fileVar2.length());
                            entryVar2.setCrc(a.a(fileVar2));
                            Object objectVar3 = null;
                            try {
                                stream = new BufferedInputStream(new FileInputStream(fileVar2));
                                try {
                                    a.a(entryVar2);
                                    b.a(stream, a);
                                }
                                finally {
                                    Throwable throwableVar2 = v_96;
                                    a.a();
                                    throw throwableVar2;
                                }
                                continue;;
                            }
                            finally {
                                Throwable throwableVar3 = v_90;
                                b.a(stream);
                                throw throwableVar3;
                            }
                        }
                    }
                    goto 457;
                }
                catch (Throwable var_8_1) {
                    n.a("Tinker.DexDiffPatchInternal", var_8_1, "merge classN file", new Object[]{});
                    i2 = 0;
                    goto 457;
                }
                finally {
                    Throwable throwableVar4 = v_23;
                    b.a(object);
                    throw throwableVar4;
                }
                if (i2 != 0) {
                    iteratorVar2 = h.c.keySet().iterator();
                    while (iteratorVar2.hasNext()) {
                        d dVar1 = (d)iteratorVar2.next();
                        if (h.a(fileVar1, dVar1.a, dVar1.c)) {
                            i2 = 0;
                            n.d("Tinker.DexDiffPatchInternal", "verify dex file md5 error, entry name; %s, file len: %d", new Object[]{dVar1.a, Long.valueOf(fileVar1.length())});
                            break;;
                        }
                        else {
                            continue;;
                        }
                    }
                }
                if (i2 != 0) {
                    iteratorVar2 = h.c.values().iterator();
                    while (iteratorVar2.hasNext()) {
                        File fileVar3 = (File)iteratorVar2.next();
                        h.c(fileVar3);
                    }
                }
                else {
                    n.d("Tinker.DexDiffPatchInternal", "merge classN dex error, try delete temp file", new Object[]{});
                    h.c(fileVar1);
                    b.a(context).h().a(file, fileVar1, fileVar1.getName(), 7);
                }
                n.b("Tinker.DexDiffPatchInternal", "merge classN dex file %s, result: %b, size: %d, use: %dms", new Object[]{fileVar1.getPath(), Boolean.valueOf(i2), Long.valueOf(fileVar1.length()), Long.valueOf(System.currentTimeMillis() - l0)});
                return i2;
            }
        }
    }

    private static boolean a(Context context, List<File> list, String str0, File file, boolean bool0, b b) {
        b bVar1 = b.a(context);
        h.a.clear();
        if (list != null) {
            File fileVar1 = new File(str0);
            if (fileVar1.exists() && fileVar1.mkdirs()) {
                n.c("Tinker.DexDiffPatchInternal", "patch recover, make optimizeDexDirectoryFile fail", new Object[]{});
                return false;
            }
            else {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    File fileVar2 = (File)iterator.next();
                    String str1 = h.b(fileVar2, fileVar1);
                    h.a.add(new File(str1));
                }
                n.b("Tinker.DexDiffPatchInternal", "patch recover, try to optimize dex file count:%d, optimizeDexDirectory:%s", new Object[]{Integer.valueOf(list.size()), str0});
                Vector vector = new Vector();
                Throwable throwable = new Throwable[]{};
                if (b != null) {
                    b.i = System.currentTimeMillis();
                }
                boolean bool1 = TinkerApplication.a().d();
                v_48 = new boolean[]{0};
                boolean[] booleanArr0 = new boolean[]{0};
                h$1 h$1 = new h$1(booleanArr0, vector, throwable);
                if (a.a()) {
                    i.a(context, list, fileVar1, false, bool1, null, bool0, h$1);
                    goto 239;
                }
                else {
                    g.a(context, list, fileVar1, bool1, bool0, h$1);
                }
                if (b != null) {
                    v_68;
                    synchronized () {
                        b.j = booleanArr0[0] ? 0 : true;
                    }
                }
                if (vector.isEmpty()) {
                    bVar1.h().a(file, vector, throwable[0]);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean a(ArrayList<File> list, int i0) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            File file = (File)iterator.next();
            if (h.a(file)) {
                if (h.b(file)) {
                    continue;;
                }
                else {
                    n.d("Tinker.DexDiffPatchInternal", "parallel dex optimizer file %s is not exist, just wait %d times", new Object[]{file.getName(), Integer.valueOf(i0)});
                    return false;
                }
            }
            else {
                continue;;
            }
        }
        return true;
    }

    private static boolean a(Context context, String str0, String str1, File file, int i0) {
        h.b.clear();
        d.a(str1, h.b);
        if (h.b.isEmpty()) {
            n.c("Tinker.DexDiffPatchInternal", "extract patch list is empty! type:%s:", new Object[]{m.e(i0)});
            return true;
        }
        else {
            File fileVar1 = new File(str0);
            if (fileVar1.exists()) {
                fileVar1.mkdirs();
            }
            b b = b.a(context);
            Object object = null;
            Object objectVar1 = null;
            try {
                ApplicationInfo info = context.getApplicationInfo();
                if (info == null) {
                    n.c("Tinker.DexDiffPatchInternal", "applicationInfo == null!!!!", new Object[]{});
                    int i1 = false;
                    h.a(object);
                    h.a(objectVar1);
                    return i1;
                }
                else {
                    String str2 = info.sourceDir;
                    file = new ZipFile(str2);
                    fileVar1 = new ZipFile(file);
                    if (h.a(str0)) {
                        n.c("Tinker.DexDiffPatchInternal", "class n dex file %s is already exist, and md5 match, just continue", new Object[]{"tinker_classN.apk"});
                        int i2 = true;
                        h.a(file);
                        h.a(fileVar1);
                        return i2;
                    }
                    else {
                        Iterator iterator = h.b.iterator();
                        while (iterator.hasNext()) {
                            d d = (d)iterator.next();
                            long l0 = System.currentTimeMillis();
                            String str3 = d.g;
                            String str4 = str3.equals("") ? new StringBuilder().append(d.g).append("/").append(d.a).toString() : d.a;
                            String str5 = d.f;
                            String str6 = d.d;
                            if (h.d && d.b.equals("0")) {
                                n.c("Tinker.DexDiffPatchInternal", "patch dex %s is only for art, just continue", new Object[]{var_16_0});
                                continue;;
                            }
                            else {
                                String str7 = h.d ? d.b : d.c;
                                if (h.e(str7)) {
                                    n.c("Tinker.DexDiffPatchInternal", "meta file md5 invalid, type:%s, name: %s, md5: %s", new Object[]{m.e(i0), d.a, str7});
                                    b.h().a(file, e.a(i0));
                                    int i3 = false;
                                    h.a(file);
                                    h.a(fileVar1);
                                    return i3;
                                }
                                else {
                                    File fileVar2 = new File(new StringBuilder().append(str0).append(d.j).toString());
                                    if (fileVar2.exists()) {
                                        if (h.b(fileVar2, str7)) {
                                            n.c("Tinker.DexDiffPatchInternal", "dex file %s is already exist, and md5 match, just continue", new Object[]{fileVar2.getPath()});
                                            continue;;
                                        }
                                        else {
                                            n.c("Tinker.DexDiffPatchInternal", new StringBuilder().append("have a mismatch corrupted dex ").append(fileVar2.getPath()).toString(), new Object[]{});
                                            fileVar2.delete();
                                        }
                                    }
                                    else {
                                        fileVar2.getParentFile().mkdirs();
                                    }
                                    ZipEntry entry = fileVar1.getEntry(var_16_0);
                                    ZipEntry entryVar1 = file.getEntry(var_16_0);
                                    if (str6.equals("0")) {
                                        if (entry == null) {
                                            n.c("Tinker.DexDiffPatchInternal", new StringBuilder().append("patch entry is null. path:").append(var_16_0).toString(), new Object[]{});
                                            b.h().a(file, fileVar2, d.a, i0);
                                            int i4 = false;
                                            h.a(file);
                                            h.a(fileVar1);
                                            return i4;
                                        }
                                        else if (h.a(fileVar1, entry, fileVar2, d)) {
                                            n.c("Tinker.DexDiffPatchInternal", new StringBuilder().append("Failed to extract raw patch file ").append(fileVar2.getPath()).toString(), new Object[]{});
                                            b.h().a(file, fileVar2, d.a, i0);
                                            int i5 = false;
                                            h.a(file);
                                            h.a(fileVar1);
                                            return i5;
                                        }
                                    }
                                    else {
                                        if (str5.equals("0") ? entryVar1 == null : h.d) {
                                            n.c("Tinker.DexDiffPatchInternal", new StringBuilder().append("apk entry is null. path:").append(var_16_0).toString(), new Object[]{});
                                            b.h().a(file, fileVar2, d.a, i0);
                                            int i6 = false;
                                            h.a(file);
                                            h.a(fileVar1);
                                            return i6;
                                        }
                                        else {
                                            String str8 = String.valueOf(entryVar1.getCrc());
                                            if (str8.equals(str6)) {
                                                n.d("Tinker.DexDiffPatchInternal", "apk entry %s crc is not equal, expect crc: %s, got crc: %s", new Object[]{var_16_0, str6, str8});
                                                b.h().a(file, fileVar2, d.a, i0);
                                                int i7 = false;
                                                h.a(file);
                                                h.a(fileVar1);
                                                return i7;
                                            }
                                            else {
                                                h.a(file, entryVar1, fileVar2, d);
                                                if (h.b(fileVar2, str7)) {
                                                    n.c("Tinker.DexDiffPatchInternal", new StringBuilder().append("Failed to recover dex file when verify patched dex: ").append(fileVar2.getPath()).toString(), new Object[]{});
                                                    b.h().a(file, fileVar2, d.a, i0);
                                                    h.c(fileVar2);
                                                    int i8 = false;
                                                    h.a(file);
                                                    h.a(fileVar1);
                                                    return i8;
                                                }
                                                else {
                                                }
                                            }
                                        }
                                        if (entry == null) {
                                            n.c("Tinker.DexDiffPatchInternal", new StringBuilder().append("patch entry is null. path:").append(var_16_0).toString(), new Object[]{});
                                            b.h().a(file, fileVar2, d.a, i0);
                                            int i9 = false;
                                            h.a(file);
                                            h.a(fileVar1);
                                            return i9;
                                        }
                                        else if (h.e(str5)) {
                                            n.c("Tinker.DexDiffPatchInternal", "meta file md5 invalid, type:%s, name: %s, md5: %s", new Object[]{m.e(i0), d.a, str5});
                                            b.h().a(file, e.a(i0));
                                            int i10 = false;
                                            h.a(file);
                                            h.a(fileVar1);
                                            return i10;
                                        }
                                        else if (entryVar1 == null) {
                                            n.c("Tinker.DexDiffPatchInternal", new StringBuilder().append("apk entry is null. path:").append(var_16_0).toString(), new Object[]{});
                                            b.h().a(file, fileVar2, d.a, i0);
                                            int i11 = false;
                                            h.a(file);
                                            h.a(fileVar1);
                                            return i11;
                                        }
                                        else {
                                            String str9 = String.valueOf(entryVar1.getCrc());
                                            if (str9.equals(str6)) {
                                                n.d("Tinker.DexDiffPatchInternal", "apk entry %s crc is not equal, expect crc: %s, got crc: %s", new Object[]{var_16_0, str6, str9});
                                                b.h().a(file, fileVar2, d.a, i0);
                                                int i12 = false;
                                                h.a(file);
                                                h.a(fileVar1);
                                                return i12;
                                            }
                                            else {
                                                h.a(file, fileVar1, entryVar1, entry, d, fileVar2);
                                                if (h.b(fileVar2, str7)) {
                                                    n.c("Tinker.DexDiffPatchInternal", new StringBuilder().append("Failed to recover dex file when verify patched dex: ").append(fileVar2.getPath()).toString(), new Object[]{});
                                                    b.h().a(file, fileVar2, d.a, i0);
                                                    h.c(fileVar2);
                                                    int i13 = false;
                                                    h.a(file);
                                                    h.a(fileVar1);
                                                    return i13;
                                                }
                                                else {
                                                    n.c("Tinker.DexDiffPatchInternal", "success recover dex file: %s, size: %d, use time: %d", new Object[]{fileVar2.getPath(), Long.valueOf(fileVar2.length()), Long.valueOf(System.currentTimeMillis() - l0)});
                                                }
                                            }
                                        }
                                    }
                                    continue;;
                                }
                            }
                        }
                        if (h.a(context, file, str0)) {
                            int i14 = false;
                            h.a(file);
                            h.a(fileVar1);
                            return i14;
                        }
                        else {
                        }
                    }
                }
            }
            catch (Throwable var_9_1) {
                throw new k(new StringBuilder().append("patch ").append(m.e(i0)).append(" extract failed (").append(var_9_1.getMessage()).append(").").toString(), var_9_1);
            }
            finally {
                Throwable throwable = v_27;
                h.a(object);
                h.a(objectVar1);
                throw throwable;
            }
            return true;
        }
    }

    private static boolean a(ZipFile file, ZipEntry entry, File file, String str0) {
        int i0 = 0;
        int i1 = false;
        while (true) {
            if (i0 < 2 && i1 == 0) {
                i0 += 1;
                Object object = null;
                Object objectVar1 = null;
                n.b("Tinker.DexDiffPatchInternal", new StringBuilder().append("try Extracting ").append(file.getPath()).toString(), new Object[]{});
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    ZipOutputStream stream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                    if (m.a(33, 1)) {
                        file.setReadOnly();
                    }
                    stream = new BufferedInputStream(file.getInputStream(entry));
                    byte[] byteArr0 = new byte[]{};
                    ZipEntry entryVar1 = new ZipEntry("classes.dex");
                    stream.putNextEntry(entryVar1);
                    int i3 = stream.read(byteArr0);
                    while (i3 != -1) {
                        stream.write(byteArr0, 0, i3);
                        i3 = stream.read(byteArr0);
                    }
                    stream.closeEntry();
                    goto 216;
                }
                finally {
                    Throwable throwable = v_19;
                    b.a(objectVar1);
                    b.a(object);
                    throw throwable;
                }
                boolean bool0 = h.b(file, str0);
                n.b("Tinker.DexDiffPatchInternal", "isExtractionSuccessful: %b", new Object[]{Boolean.valueOf(bool0)});
                if (bool0) {
                    boolean bool1 = file.delete();
                    if (! bool1 || file.exists()) {
                        n.d("Tinker.DexDiffPatchInternal", new StringBuilder().append("Failed to delete corrupted dex ").append(file.getPath()).toString(), new Object[]{});
                    }
                }
                continue;;
            }
        }
        return i1;
    }

    private static boolean a(ZipFile file, ZipEntry entry, File file, d d) {
        String str0 = h.d ? d.b : d.c;
        String str1 = d.a;
        boolean bool0 = d.i;
        if (h.h(str1) && bool0) {
            return h.a(file, entry, file, str0);
        }
        else {
            return h.a(file, entry, file, str0, 1);
        }
    }

    private static void a(ZipFile file, ZipFile fileVar1, ZipEntry entry, ZipEntry entryVar1, d d, File file) {
        Object object = null;
        Object objectVar1 = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(file.getInputStream(entry));
            BufferedInputStream streamVar1 = entryVar1 != null ? null : new BufferedInputStream(fileVar1.getInputStream(entryVar1));
            if (m.a(33, 1)) {
                file.setReadOnly();
            }
            boolean bool0 = h.h(d.a);
            if (! bool0 || d.i) {
                Object objectVar2 = null;
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    stream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                    stream.putNextEntry(new ZipEntry("classes.dex"));
                    if (bool0) {
                        Object objectVar3 = null;
                        try {
                            stream = new ZipInputStream(stream);
                            while (stream.getNextEntry() != null) {
                                ZipEntry entryVar2 = stream.getNextEntry();
                            }
                            if (stream.getNextEntry() != null ? entryVar2 == null : "classes.dex".equals(entryVar2.getName())) {
                                throw new k(new StringBuilder().append("can't recognize zip dex format file:").append(file.getAbsolutePath()).toString());
                            }
                            else {
                                new a(stream, streamVar1).a(stream);
                            }
                        }
                        finally {
                            Throwable throwable = v_46;
                            b.a(stream);
                            throw throwable;
                        }
                    }
                    else {
                        new a(stream, streamVar1).a(stream);
                    }
                    stream.closeEntry();
                }
                finally {
                    Throwable throwableVar1 = v_32;
                    b.a(objectVar2);
                    throw throwableVar1;
                }
            }
            else {
                new a(stream, streamVar1).a(file);
            }
            return;
        }
        finally {
            Throwable throwableVar2 = v_7;
            b.a(stream);
            b.a(objectVar1);
            throw throwableVar2;
        }
    }

    static  {
        h.a = new ArrayList();
        h.b = new ArrayList();
        h.c = new HashMap();
        h.d = m.a();
    }

}
