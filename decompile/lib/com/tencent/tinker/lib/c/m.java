/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.d.d;
import com.tencent.tinker.lib.g.b;
import com.tencent.tinker.loader.shareutil.l;
import com.tencent.tinker.loader.shareutil.i;
import java.io.File;
import java.util.HashMap;

// class: com/tencent/tinker/lib/c/m
public class m {

    public m() {
        super();
    }

    public boolean a(Context context, String str0, boolean bool0, b b) {
        b bVar1 = b.a(context);
        File file = new File(str0);
        if (! bVar1.i() || m.g(context)) {
            n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:patch is disabled, just return", new Object[]{});
            return false;
        }
        else if (h.a(file)) {
            n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:patch file is not found, just return", new Object[]{});
            return false;
        }
        else {
            l l = new l(context);
            int i0 = m.a(context, bVar1.r(), file, l);
            if (i0 != 0) {
                n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:onPatchPackageCheckFail", new Object[]{});
                bVar1.h().a(file, i0);
                return false;
            }
            else {
                String str1 = h.f(file);
                if (str1 == null) {
                    n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:patch md5 is null, just return", new Object[]{});
                    return false;
                }
                else {
                    b.l = str1;
                    n.b("Tinker.UpgradePatch", "UpgradePatch tryPatch:patchMd5:%s", new Object[]{str1});
                    String str2 = bVar1.n().getAbsolutePath();
                    File fileVar1 = h.b(str2);
                    File fileVar2 = h.a(str2);
                    HashMap map = l.b();
                    if (map == null) {
                        n.d("Tinker.UpgradePatch", "UpgradePatch packageProperties is null, do we process a valid patch apk ?", new Object[]{});
                        return false;
                    }
                    else {
                        String str3 = (String)map.get("is_protected_app");
                        int i1 = str3 != null && str3.isEmpty() && "0".equals(str3) ? 0 : 1;
                        String str4 = (String)map.get("use_custom_file_patch");
                        int i2 = str4 != null && str4.isEmpty() && "0".equals(str4) ? 0 : 1;
                        i i = i.a(fileVar2, fileVar1);
                        String str10;
                        i iVar1;
                        if (i != null) {
                            if (i.a != null || i.b != null || i.g == null) {
                                n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:onPatchInfoCorrupted", new Object[]{});
                                bVar1.h().a(file, i.a, i.b);
                                return false;
                            }
                            else if (h.e(str1)) {
                                n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:onPatchVersionCheckFail md5 %s is valid", new Object[]{str1});
                                bVar1.h().a(file, i, str1);
                                return false;
                            }
                            else {
                                boolean bool1 = i.g.equals("interpet");
                                if (bool1 && m.b(i.b) && i.b.equals(str1) && i.b.equals(i.e)) {
                                    n.d("Tinker.UpgradePatch", "patch already applied, md5: %s", new Object[]{str1});
                                    b.a(context).b(str1);
                                    return true;
                                }
                                else {
                                    str10 = bool1 ? i.g : "changing";
                                    if (str1.equals(i.b) && i.b.equals(i.a)) {
                                        String str6 = i.b;
                                        i.b = i.a;
                                        i.a(fileVar2, i, fileVar1);
                                        String str7 = h.c(str6);
                                        h.d(new File(str2, str7));
                                    }
                                    String str8 = str1.equals(i.e) ? i.e : "";
                                    iVar1 = new i(i.a, str1, i1, i2, var_22_1, Build.FINGERPRINT, str10, 0);
                                }
                            }
                        }
                        else {
                            iVar1 = new i("", str1, i1, i2, "", Build.FINGERPRINT, "odex", 0);
                        }
                        String str9 = h.c(str1);
                        str10 = new StringBuilder().append(str2).append("/").append(str9).toString();
                        n.b("Tinker.UpgradePatch", "UpgradePatch tryPatch:patchVersionDirectory:%s", new Object[]{str10});
                        File fileVar3 = new File(new StringBuilder().append(str10).append("/").append(h.d(str1)).toString());
                        try {
                            if (str1.equals(h.f(fileVar3))) {
                                h.a(file, fileVar3);
                                n.c("Tinker.UpgradePatch", "UpgradePatch copy patch file, src file: %s size: %d, dest file: %s size:%d", new Object[]{file.getAbsolutePath(), Long.valueOf(file.length()), fileVar3.getAbsolutePath(), Long.valueOf(fileVar3.length())});
                            }
                            goto 895;
                        }
                        catch (IOException var_23_1) {
                            n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:copy patch file fail from %s to %s", new Object[]{file.getPath(), fileVar3.getPath()});
                            bVar1.h().a(file, fileVar3, file.getName(), 1);
                            return false;
                        }
                        if (h.a(bVar1, l, context, str10, fileVar3, bool0, b)) {
                            n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, try patch dex failed", new Object[]{});
                            return false;
                        }
                        else if (d.a(bVar1, l, context, str10, fileVar3)) {
                            return false;
                        }
                        else {
                            a.a(2);
                            if (l.a(bVar1, l, context, str10, fileVar3, i2, b)) {
                                n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, try patch library failed", new Object[]{});
                                return false;
                            }
                            else {
                                a.b(2);
                                a.a(3);
                                if (k.a(bVar1, l, context, str10, fileVar3, i2, b)) {
                                    n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, try patch resource failed", new Object[]{});
                                    return false;
                                }
                                else {
                                    a.b(3);
                                    if (this.a(bVar1, l, context, str10, fileVar3)) {
                                        n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, try patch custom failed", new Object[]{});
                                        return false;
                                    }
                                    else {
                                        a.a(4);
                                        if (h.a(file, bVar1)) {
                                            n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, check dex opt file failed", new Object[]{});
                                            return false;
                                        }
                                        else {
                                            a.b(4);
                                            if (i.a(fileVar2, iVar1, fileVar1)) {
                                                n.d("Tinker.UpgradePatch", "UpgradePatch tryPatch:new patch recover, rewrite patch info failed", new Object[]{});
                                                bVar1.h().a(file, iVar1.a, iVar1.b);
                                                return false;
                                            }
                                            else {
                                                b.a(context).b(str1);
                                                n.c("Tinker.UpgradePatch", "UpgradePatch tryPatch: done, it is ok", new Object[]{});
                                                return true;
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

    boolean a(b b, l l, Context context, String str0, File file) {
        return true;
    }

}
