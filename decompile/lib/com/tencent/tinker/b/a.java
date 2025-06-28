/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/b;

import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

// class: com/tencent/tinker/b/a
public class a {

    public static int a(InputStream stream, InputStream streamVar1, File file) {
        if (stream == null) {
            return 3;
        }
        else if (file == null) {
            return 4;
        }
        else if (streamVar1 == null) {
            return 2;
        }
        else {
            byte[] byteArr0 = b.a(stream);
            byte[] byteArr0Var1 = b.a(streamVar1);
            byte[] byteArr0Var2 = a.a(byteArr0, byteArr0.length, byteArr0Var1, byteArr0Var1.length, 0);
            stream = new FileOutputStream(file);
            try {
                stream.write(byteArr0Var2);
            }
            finally {
                Throwable throwable = v_23;
                stream.close();
                throw throwable;
            }
            return 1;
        }
    }

    public static byte[] a(byte[] byteArr0, int i0, byte[] byteArr0Var1, int i1, int i2) {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(byteArr0Var1, 0, i1));
        stream.skip(8L);
        long l0 = stream.readLong();
        long l1 = stream.readLong();
        int i3 = (int)stream.readLong();
        stream.close();
        ByteArrayInputStream streamVar2 = new ByteArrayInputStream(byteArr0Var1, 0, i1);
        streamVar2.skip(32L);
        DataInputStream streamVar1 = new DataInputStream(new GZIPInputStream(streamVar2));
        streamVar2 = new ByteArrayInputStream(byteArr0Var1, 0, i1);
        streamVar2.skip(l0 + 32L);
        stream = new GZIPInputStream(streamVar2);
        streamVar2 = new ByteArrayInputStream(byteArr0Var1, 0, i1);
        streamVar2.skip(l1 + l0 + 32L);
        streamVar1 = new GZIPInputStream(streamVar2);
        v_51 = new byte[]{};
        byte[] byteArr0Var2 = new byte[]{};
        int i4 = 0;
        int[] intArr0 = new int[]{};
        for (int i5 = 0; i5 < i3; i4 += intArr0[2]) {
            for (int i7 = 0; i7 <= 2; i7 += 1) {
                intArr0[i7] = streamVar1.readInt();
            }
            if (i5 + intArr0[0] > i3) {
                throw new IOException("Corrupt by wrong patch file.");
            }
            else if (b.a(stream, byteArr0Var2, i5, intArr0[0])) {
                throw new IOException("Corrupt by wrong patch file.");
            }
            else {
                for (i7 = 0; i7 < intArr0[0]; i7 += 1) {
                    i4 + i7 >= 0 && i4 + i7 < i0;
                    v_150[i5 + i7] = (byte)byteArr0Var2[i5 + i7] + byteArr0[i4 + i7];
                }
                i5 += intArr0[0];
                i4 += intArr0[0];
                if (i5 + intArr0[1] > i3) {
                    throw new IOException("Corrupt by wrong patch file.");
                }
                else if (b.a(streamVar1, byteArr0Var2, i5, intArr0[1])) {
                    throw new IOException("Corrupt by wrong patch file.");
                }
                else {
                    i5 += intArr0[1];
                }
            }
        }
        streamVar1.close();
        stream.close();
        streamVar1.close();
        return byteArr0Var2;
    }

}
