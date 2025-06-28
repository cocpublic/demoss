/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import android.content.Intent;
import java.io.File;
import java.util.HashMap;

// class: com/tencent/tinker/loader/h
public class h {
    private i a;

    public h() {
        super();
    }

    public Intent a(TinkerApplication application) {
        n.a("Tinker.TinkerLoader", "tryLoad test test", new Object[]{});
        Intent intent = new Intent();
        long l0 = SystemClock.elapsedRealtime();
        super.a(application, intent);
        long l1 = SystemClock.elapsedRealtime() - l0;
        g.a(intent, l1);
        return intent;
    }

    private void a(TinkerApplication application, Intent intent) {
        int i0 = application.c();
        if (m.f(i0)) {
            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles: tinker is disable, just return", new Object[]{});
            g.a(intent, -1);
        }
        else if (m.j(application)) {
            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles: we don't load patch with :patch process itself, just return", new Object[]{});
            g.a(intent, -1);
        }
        else {
            File file = h.a(application);
            if (file == null) {
                n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:getPatchDirectory == null", new Object[]{});
                g.a(intent, 254);
            }
            else {
                String str0 = file.getAbsolutePath();
                if (file.exists()) {
                    n.c("Tinker.TinkerLoader", new StringBuilder().append("tryLoadPatchFiles:patch dir not exist:").append(str0).toString(), new Object[]{});
                    g.a(intent, 254);
                }
                else {
                    File fileVar1 = h.a(str0);
                    if (fileVar1.exists()) {
                        n.c("Tinker.TinkerLoader", new StringBuilder().append("tryLoadPatchFiles:patch info not exist:").append(fileVar1.getAbsolutePath()).toString(), new Object[]{});
                        g.a(intent, 253);
                    }
                    else {
                        File fileVar2 = h.b(str0);
                        this.a = i.a(fileVar1, fileVar2);
                        if (this.a == null) {
                            g.a(intent, 252);
                        }
                        else {
                            boolean bool0 = this.a.c;
                            intent.putExtra("intent_is_protected_app", bool0);
                            boolean bool1 = this.a.d;
                            intent.putExtra("intent_use_custom_patch", bool1);
                            String str10 = this.a.a;
                            String str7 = this.a.b;
                            String str14 = this.a.g;
                            if (str10 != null || str7 != null || str14 == null) {
                                n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchInfoCorrupted", new Object[]{});
                                g.a(intent, 252);
                            }
                            else {
                                boolean bool2 = m.i(application);
                                String str4 = this.a.e;
                                if (bool2) {
                                    if (m.b(str4)) {
                                        if (str7.equals(str4)) {
                                            n.c("Tinker.TinkerLoader", "found new version clean patch mark and we are in main process, delete patch file now.", new Object[]{});
                                            String str5 = h.c(str7);
                                            if (str5 != null) {
                                                boolean bool3 = str10.equals(str7);
                                                if (bool3) {
                                                    str10 = "";
                                                }
                                                this.a.a = str10;
                                                this.a.b = str10;
                                                this.a.e = "";
                                                i.a(fileVar1, this.a, fileVar2);
                                                String str8 = new StringBuilder().append(str0).append("/").append(str5).toString();
                                                if (bool3) {
                                                    m.k(application);
                                                    h.g(str8);
                                                    g.a(intent, 254);
                                                }
                                                else {
                                                    h.g(str8);
                                                }
                                            }
                                        }
                                        else if (str10.equals(str4)) {
                                            n.c("Tinker.TinkerLoader", "found old version clean patch mark and we are in main process, delete patch file now.", new Object[]{});
                                            String str9 = h.c(str10);
                                            if (str9 != null) {
                                                this.a.a = str7;
                                                this.a.b = str7;
                                                this.a.e = "";
                                                i.a(fileVar1, this.a, fileVar2);
                                                String str11 = new StringBuilder().append(str0).append("/").append(str9).toString();
                                                m.k(application);
                                                h.g(str11);
                                            }
                                        }
                                        else {
                                            this.a.e = "";
                                            i.a(fileVar1, this.a, fileVar2);
                                        }
                                    }
                                    if (this.a.h) {
                                        n.b("Tinker.TinkerLoader", "tryLoadPatchFiles: isRemoveInterpretOATDir is true, try to delete interpret optimize files", new Object[]{});
                                        this.a.h = false;
                                        i.a(fileVar1, this.a, fileVar2);
                                        m.k(application);
                                        String str12 = h.c(str7);
                                        String str13 = new StringBuilder().append(str0).append("/").append(str12).toString();
                                        h.g(new StringBuilder().append(str13).append("/").append("interpet").toString());
                                    }
                                }
                                intent.putExtra("intent_patch_old_version", str7);
                                intent.putExtra("intent_patch_new_version", str7);
                                int i1 = str7.equals(str7) ? 0 : 1;
                                boolean bool16 = str14.equals("changing");
                                str14 = m.a(application, str14);
                                intent.putExtra("intent_patch_oat_dir", str14);
                                if (i1 != 0 && bool2) {
                                    str16 = str7;
                                }
                                if (m.b(str10)) {
                                    n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:version is blank, wait main process to restart", new Object[]{});
                                    g.a(intent, 251);
                                }
                                else {
                                    String str17 = h.c(str10);
                                    if (str17 == null) {
                                        n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:patchName is null", new Object[]{});
                                        g.a(intent, 250);
                                    }
                                    else {
                                        String str18 = new StringBuilder().append(str0).append("/").append(str17).toString();
                                        File fileVar3 = new File(str18);
                                        if (fileVar3.exists()) {
                                            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchVersionDirectoryNotFound", new Object[]{});
                                            g.a(intent, 250);
                                        }
                                        else {
                                            String str19 = h.d(str10);
                                            File fileVar4 = str19 != null ? null : new File(fileVar3.getAbsolutePath(), str19);
                                            if (h.a(fileVar4)) {
                                                n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchVersionFileNotFound", new Object[]{});
                                                g.a(intent, 249);
                                            }
                                            else {
                                                l l = new l(application);
                                                int i2 = m.a(application, i0, fileVar4, l);
                                                if (i2 != 0) {
                                                    n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:checkTinkerPackage", new Object[]{});
                                                    intent.putExtra("intent_patch_package_patch_check", i2);
                                                    g.a(intent, 248);
                                                }
                                                else {
                                                    intent.putExtra("intent_patch_package_config", l.b());
                                                    boolean bool5 = m.a(i0);
                                                    boolean bool6 = m.c();
                                                    if (bool6 && bool5) {
                                                        boolean bool7 = f.a(str18, l, str14, intent);
                                                        if (bool7) {
                                                            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:dex check fail", new Object[]{});
                                                        }
                                                    }
                                                    boolean bool8 = m.d(i0);
                                                    if (bool6 && bool8) {
                                                        boolean bool9 = e.a(str18, l, intent);
                                                        if (bool9) {
                                                            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:dex check fail", new Object[]{});
                                                        }
                                                    }
                                                    boolean bool10 = m.b(i0);
                                                    if (bool10) {
                                                        boolean bool11 = l.a(str18, l, intent);
                                                        if (bool11) {
                                                            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:native lib check fail", new Object[]{});
                                                        }
                                                    }
                                                    boolean bool12 = m.c(i0);
                                                    n.c("Tinker.TinkerLoader", new StringBuilder().append("tryLoadPatchFiles:isEnabledForResource:").append(bool12).toString(), new Object[]{});
                                                    if (bool12) {
                                                        boolean bool13 = i.a(application, str18, l, intent);
                                                        if (bool13) {
                                                            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:resource check fail", new Object[]{});
                                                        }
                                                    }
                                                    int i3 = m.a() && m.a(this.a.f) && Build$VERSION.SDK_INT >= 21 && m.d() ? 0 : 1;
                                                    intent.putExtra("intent_patch_system_ota", i3);
                                                    if (bool2) {
                                                        if (i1 != 0) {
                                                            this.a.a = str10;
                                                        }
                                                        if (bool16) {
                                                            this.a.g = str14;
                                                            this.a.h = true;
                                                        }
                                                    }
                                                    if (this.b(application)) {
                                                        if (bool2) {
                                                            this.a.a = "";
                                                            this.a.b = "";
                                                            this.a.e = "";
                                                            i.a(fileVar1, this.a, fileVar2);
                                                            m.k(application);
                                                            String str20 = new StringBuilder().append(str0).append("/").append(str17).toString();
                                                            h.g(str20);
                                                            intent.putExtra("intent_patch_exception", new k("checkSafeModeCount fail"));
                                                            g.a(intent, 231);
                                                            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:checkSafeModeCount fail, patch was deleted.", new Object[]{});
                                                        }
                                                        else {
                                                            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:checkSafeModeCount fail, but we are not in main process, mark the patch to be deleted and continue load patch.", new Object[]{});
                                                            m.m(application);
                                                        }
                                                    }
                                                    if (bool12) {
                                                        boolean bool14 = i.a(application, str18, intent);
                                                        if (bool14) {
                                                            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchLoadResourcesFail", new Object[]{});
                                                        }
                                                    }
                                                    if (bool6 && bool5) {
                                                        boolean bool15 = f.a(application, str18, str14, intent, i3, bool0);
                                                        if (i3 != 0) {
                                                            this.a.f = Build.FINGERPRINT;
                                                            this.a.g = bool15 ? "odex" : "interpet";
                                                            bool16 = false;
                                                            if (i.a(fileVar1, this.a, fileVar2)) {
                                                                g.a(intent, 237);
                                                                n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:onReWritePatchInfoCorrupted", new Object[]{});
                                                            }
                                                            else {
                                                                intent.putExtra("intent_patch_oat_dir", this.a.g);
                                                            }
                                                        }
                                                        if (bool15) {
                                                            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchLoadDexesFail", new Object[]{});
                                                        }
                                                    }
                                                    if (bool6 && bool8) {
                                                        boolean bool17 = e.a(application, str18, intent);
                                                        if (bool17) {
                                                            n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:onPatchLoadArkApkFail", new Object[]{});
                                                        }
                                                    }
                                                    if (! bool5 || bool8 && bool12) {
                                                        b.a(application, l);
                                                    }
                                                    if (b.a(application)) {
                                                        n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:AppInfoChangedBlocker install fail.", new Object[]{});
                                                        g.a(intent, 228);
                                                    }
                                                    else {
                                                        if (bool2) {
                                                            if (i1 != 0 || bool16) {
                                                                if (i.a(fileVar1, this.a, fileVar2)) {
                                                                    g.a(intent, 237);
                                                                    n.c("Tinker.TinkerLoader", "tryLoadPatchFiles:onReWritePatchInfoCorrupted", new Object[]{});
                                                                }
                                                                else {
                                                                    m.k(application);
                                                                }
                                                            }
                                                        }
                                                        g.a(intent, 0);
                                                        n.b("Tinker.TinkerLoader", "tryLoadPatchFiles: load end, ok!", new Object[]{});
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean b(TinkerApplication application) {
        int i0 = m.h(application);
        if (i0 >= 2) {
            m.a(application, 0);
            return false;
        }
        else {
            application.a(true);
            m.a(application, i0 + 1);
            return true;
        }
    }

}
