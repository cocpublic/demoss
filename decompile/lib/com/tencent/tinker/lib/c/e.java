/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

// class: com/tencent/tinker/lib/c/e
public class e {

    public static boolean a(ZipFile file, ZipEntry entry, File file, String str0, boolean bool0) {
        int i0 = 0;
        int i4 = false;
        while (true) {
            if (i0 < 2 && i4 == 0) {
                i0 += 1;
                Object object = null;
                Object objectVar1 = null;
                n.b("Tinker.BasePatchInternal", new StringBuilder().append("try Extracting ").append(file.getPath()).toString(), new Object[]{});
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    BufferedInputStream stream = new BufferedInputStream(file.getInputStream(entry));
                    stream = new BufferedOutputStream(new FileOutputStream(file));
                    if (m.a(33, 1)) {
                        file.setReadOnly();
                    }
                    byte[] byteArr0 = new byte[]{};
                    int i3 = 0;
                    while (true) {
                        i3 = stream.read(byteArr0);
                        stream.read(byteArr0) > 0;
                        stream.write(byteArr0, 0, i3);
                        continue;;
                        break;;
                    }
                }
                finally {
                    Throwable throwable = v_19;
                    b.a(objectVar1);
                    b.a(object);
                    throw throwable;
                }
                if (str0 != null) {
                    boolean i1 = bool0 ? h.a(file, str0) : h.b(file, str0);
                    goto 210;
                }
                else {
                    i4 = 1;
                }
                n.b("Tinker.BasePatchInternal", "isExtractionSuccessful: %b", new Object[]{Boolean.valueOf(var_6_1)});
                if (var_6_1) {
                    boolean bool1 = file.delete();
                    if (! bool1 || file.exists()) {
                        n.d("Tinker.BasePatchInternal", new StringBuilder().append("Failed to delete corrupted dex ").append(file.getPath()).toString(), new Object[]{});
                    }
                }
                continue;;
            }
        }
        return i4;
    }

    public static int a(int i0) {
        if (i0 == 3) {
            return 253;
        }
        else if (i0 == 5) {
            return 252;
        }
        else if (i0 == 6) {
            return 248;
        }
        else {
            return 0;
        }
    }

}
