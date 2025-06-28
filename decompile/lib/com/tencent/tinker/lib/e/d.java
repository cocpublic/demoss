/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/e;

import java.io.File;
import java.util.HashMap;
import com.tencent.tinker.loader.shareutil.i;
import com.tencent.tinker.loader.k;
import com.tencent.tinker.lib.d.c;

// class: com/tencent/tinker/lib/e/d
public class d {
    public i a;
    public String b;
    public String c;
    public boolean d;
    public boolean e;
    public boolean f;
    public File g;
    public File h;
    public File i;
    public File j;
    public File k;
    public File l;
    public HashMap<String, String> m;
    public HashMap<String, String> n;
    public HashMap<String, String> o;
    public int p;
    public long q;

    public d() {
        super();
    }

    public boolean a(Context context, Intent intent) {
        b b = b.a(context);
        this.p = g.a(intent);
        this.q = g.b(intent);
        this.f = g.a(intent, "intent_patch_system_ota", 0);
        this.c = g.a(intent, "intent_patch_oat_dir");
        this.e = "interpet".equals(this.c);
        boolean bool0 = b.d();
        n.b("Tinker.TinkerLoadResult", "parseTinkerResult loadCode:%d, process name:%s, main process:%b, systemOTA:%b, fingerPrint:%s, oatDir:%s, useInterpretMode:%b", new Object[]{Integer.valueOf(this.p), m.l(context), Boolean.valueOf(bool0), Boolean.valueOf(this.f), Build.FINGERPRINT, this.c, Boolean.valueOf(this.e)});
        String str0 = g.a(intent, "intent_patch_old_version");
        String str1 = g.a(intent, "intent_patch_new_version");
        File file = b.n();
        File fileVar1 = b.o();
        if (str0 != null && str1 != null) {
            this.b = bool0 ? str0 : str1;
            n.b("Tinker.TinkerLoadResult", "parseTinkerResult oldVersion:%s, newVersion:%s, current:%s", new Object[]{str0, str1, this.b});
            String str2 = h.c(this.b);
            if (m.b(str2)) {
                this.g = new File(new StringBuilder().append(file.getAbsolutePath()).append("/").append(str2).toString());
                this.h = new File(this.g.getAbsolutePath(), h.d(this.b));
                this.i = new File(this.g, "dex");
                this.j = new File(this.g, "lib");
                this.k = new File(this.g, "res");
                this.l = new File(this.k, "resources.apk");
            }
            boolean bool1 = g.a(intent, "intent_is_protected_app", 0);
            boolean bool2 = g.a(intent, "intent_use_custom_patch", 0);
            this.a = new i(str0, str1, bool1, bool2, "", Build.FINGERPRINT, this.c, 0);
            this.d = str0.equals(str1) ? 0 : true;
        }
        Throwable throwable = g.c(intent);
        if (throwable != null) {
            int i4;
            n.b("Tinker.TinkerLoadResult", "Tinker load have exception loadCode:%d", new Object[]{Integer.valueOf(this.p)});
            i4 = -1;
            switch(this.p) {
                case -20: {
                    i4 = -1;
                    break;;
                }
                case -14: {
                    i4 = 254;
                    break;;
                }
                case -23: {
                    i4 = 253;
                    break;;
                }
                case -25: {
                    i4 = 252;
                    break;;
                }
            }
            b.g().a(throwable, i4);
            return false;
        }
        else {
            switch(this.p) {
                case -10000: {
                    n.d("Tinker.TinkerLoadResult", "can't get the right intent return code", new Object[]{});
                    throw new k("can't get the right intent return code");
                }
                case -1: {
                    n.c("Tinker.TinkerLoadResult", "tinker is disable, just return", new Object[]{});
                    break;;
                }
                case -3: {
                    n.c("Tinker.TinkerLoadResult", "can't find patch file, is ok, just return", new Object[]{});
                    break;;
                }
                case -4: {
                    n.d("Tinker.TinkerLoadResult", "path info corrupted", new Object[]{});
                    b.g().a(str0, str1, fileVar1);
                    break;;
                }
                case -5: {
                    n.d("Tinker.TinkerLoadResult", "path info blank, wait main process to restart", new Object[]{});
                    break;;
                }
                case -6: {
                    n.d("Tinker.TinkerLoadResult", "patch version directory not found, current version:%s", new Object[]{this.b});
                    b.g().a(this.g, 1, 1);
                    break;;
                }
                case -7: {
                    n.d("Tinker.TinkerLoadResult", "patch version file not found, current version:%s", new Object[]{this.b});
                    if (this.h == null) {
                        throw new k("error load patch version file not exist, but file is null");
                    }
                    else {
                        b.g().a(this.h, 1, 0);
                        break;;
                    }
                }
                case -8: {
                    n.b("Tinker.TinkerLoadResult", "patch package check fail", new Object[]{});
                    if (this.h == null) {
                        throw new k("error patch package check fail , but file is null");
                    }
                    else {
                        int i5 = intent.getIntExtra("intent_patch_package_patch_check", 55536);
                        b.g().c(this.h, i5);
                        break;;
                    }
                }
                case -9: {
                    if (this.i != null) {
                        n.d("Tinker.TinkerLoadResult", "patch dex file directory not found:%s", new Object[]{this.i.getAbsolutePath()});
                        b.g().a(this.i, 3, 1);
                        break;;
                    }
                    else {
                        n.d("Tinker.TinkerLoadResult", "patch dex file directory not found, warning why the path is null!!!!", new Object[]{});
                        throw new k("patch dex file directory not found, warning why the path is null!!!!");
                    }
                }
                case -10: {
                    String str3 = g.a(intent, "intent_patch_missing_dex_path");
                    if (str3 != null) {
                        n.d("Tinker.TinkerLoadResult", "patch dex file not found:%s", new Object[]{str3});
                        b.g().a(new File(str3), 3, 0);
                        break;;
                    }
                    else {
                        n.d("Tinker.TinkerLoadResult", "patch dex file not found, but path is null!!!!", new Object[]{});
                        throw new k("patch dex file not found, but path is null!!!!");
                    }
                }
                case -11: {
                    String str4 = g.a(intent, "intent_patch_missing_dex_path");
                    if (str4 != null) {
                        n.d("Tinker.TinkerLoadResult", "patch dex opt file not found:%s", new Object[]{str4});
                        b.g().a(new File(str4), 4, 0);
                        break;;
                    }
                    else {
                        n.d("Tinker.TinkerLoadResult", "patch dex opt file not found, but path is null!!!!", new Object[]{});
                        throw new k("patch dex opt file not found, but path is null!!!!");
                    }
                }
                case -17: {
                    if (this.g != null) {
                        n.d("Tinker.TinkerLoadResult", "patch lib file directory not found:%s", new Object[]{this.j.getAbsolutePath()});
                        b.g().a(this.j, 5, 1);
                        break;;
                    }
                    else {
                        n.d("Tinker.TinkerLoadResult", "patch lib file directory not found, warning why the path is null!!!!", new Object[]{});
                        throw new k("patch lib file directory not found, warning why the path is null!!!!");
                    }
                }
                case -18: {
                    String str5 = g.a(intent, "intent_patch_missing_lib_path");
                    if (str5 != null) {
                        n.d("Tinker.TinkerLoadResult", "patch lib file not found:%s", new Object[]{str5});
                        b.g().a(new File(str5), 5, 0);
                        break;;
                    }
                    else {
                        n.d("Tinker.TinkerLoadResult", "patch lib file not found, but path is null!!!!", new Object[]{});
                        throw new k("patch lib file not found, but path is null!!!!");
                    }
                }
                case -12: {
                    n.d("Tinker.TinkerLoadResult", "patch dex load fail, classloader is null", new Object[]{});
                    break;;
                }
                case -13: {
                    String str6 = g.a(intent, "intent_patch_mismatch_dex_path");
                    if (str6 == null) {
                        n.d("Tinker.TinkerLoadResult", "patch dex file md5 is mismatch, but path is null!!!!", new Object[]{});
                        throw new k("patch dex file md5 is mismatch, but path is null!!!!");
                    }
                    else {
                        n.d("Tinker.TinkerLoadResult", "patch dex file md5 is mismatch: %s", new Object[]{str6});
                        b.g().b(new File(str6), 3);
                        break;;
                    }
                }
                case -19: {
                    n.b("Tinker.TinkerLoadResult", "rewrite patch info file corrupted", new Object[]{});
                    b.g().a(str0, str1, fileVar1);
                    break;;
                }
                case -21: {
                    if (this.g != null) {
                        n.d("Tinker.TinkerLoadResult", "patch resource file directory not found:%s", new Object[]{this.k.getAbsolutePath()});
                        b.g().a(this.k, 6, 1);
                        break;;
                    }
                    else {
                        n.d("Tinker.TinkerLoadResult", "patch resource file directory not found, warning why the path is null!!!!", new Object[]{});
                        throw new k("patch resource file directory not found, warning why the path is null!!!!");
                    }
                }
                case -22: {
                    if (this.g != null) {
                        n.d("Tinker.TinkerLoadResult", "patch resource file not found:%s", new Object[]{this.l.getAbsolutePath()});
                        b.g().a(this.l, 6, 0);
                        break;;
                    }
                    else {
                        n.d("Tinker.TinkerLoadResult", "patch resource file not found, warning why the path is null!!!!", new Object[]{});
                        throw new k("patch resource file not found, warning why the path is null!!!!");
                    }
                }
                case -24: {
                    if (this.l == null) {
                        n.d("Tinker.TinkerLoadResult", "resource file md5 mismatch, but patch resource file not found!", new Object[]{});
                        throw new k("resource file md5 mismatch, but patch resource file not found!");
                    }
                    else {
                        n.d("Tinker.TinkerLoadResult", "patch resource file md5 is mismatch: %s", new Object[]{this.l.getAbsolutePath()});
                        b.g().b(this.l, 6);
                        break;;
                    }
                }
                case -15: {
                    b.g().a(1, g.d(intent));
                    break;;
                }
                case -16: {
                    b.g().a(2, g.d(intent));
                    break;;
                }
                case 0: {
                    n.b("Tinker.TinkerLoadResult", "oh yeah, tinker load all success", new Object[]{});
                    b.a(true);
                    this.m = g.e(intent);
                    this.n = g.f(intent);
                    this.o = g.g(intent);
                    if (this.e) {
                        b.g().a(0, null);
                    }
                    if (bool0 && this.d) {
                        b.g().a(str0, str1, file, this.g.getName());
                    }
                    return true;
                }
            }
            return false;
        }
    }

}
