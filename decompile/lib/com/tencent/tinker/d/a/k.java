/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/d/a;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

// class: com/tencent/tinker/d/a/k
public class k {

    public static void a(i i, h h, j j) {
        Object object = null;
        try {
            InputStream stream = i.a(h);
            j.a(new h(h));
            byte[] byteArr0 = new byte[]{};
            int i1 = stream.read(byteArr0);
            while (i1 != -1) {
                j.write(byteArr0, 0, i1);
                i1 = stream.read(byteArr0);
            }
            j.a();
            return;
        }
        finally {
            Throwable throwable = v_12;
            if (stream != null) {
                stream.close();
            }
            throw throwable;
        }
    }

    public static void a(h h, File file, long l1, j l1) {
        h hVar1 = new h(h);
        hVar1.a(0);
        hVar1.c(file.length());
        hVar1.a(file.length());
        hVar1.b(l1);
        Object object = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
            j.a(new h(hVar1));
            byte[] byteArr0 = new byte[]{};
            int i1 = stream.read(byteArr0);
            while (i1 != -1) {
                j.write(byteArr0, 0, i1);
                i1 = stream.read(byteArr0);
            }
            j.a();
            return;
        }
        finally {
            Throwable throwable = v_24;
            if (stream != null) {
                stream.close();
            }
            throw throwable;
        }
    }

}
