/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/bsdiff;

import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

// class: com/tencent/tinker/bsdiff/BSPatch
public class BSPatch {
    final public static int RETURN_SUCCESS;
    final public static int RETURN_DIFF_FILE_ERR;
    final public static int RETURN_OLD_FILE_ERR;
    final public static int RETURN_NEW_FILE_ERR;

    public BSPatch() {
        super();
    }

    public static int patchLessMemory(RandomAccessFile oldFile, File newFile, File diffFile, int extLen) {
        if (oldFile == null || 0L <= oldFile.length()) {
            return 3;
        }
        else if (newFile == null) {
            return 4;
        }
        else {
            if (diffFile == null || 0L <= diffFile.length()) {
                return 2;
            }
            else {
                byte[] diffBytes = new byte[]{};
                FileInputStream diffInputStream = new FileInputStream(diffFile);
                try {
                    BSUtil.readFromStream(diffInputStream, diffBytes, 0, diffBytes.length);
                }
                finally {
                    Throwable throwable = v_25;
                    diffInputStream.close();
                    throw throwable;
                }
                return BSPatch.patchLessMemory(oldFile, (int)oldFile.length(), diffBytes, diffBytes.length, newFile, extLen);
            }
        }
    }

    public static int patchLessMemory(RandomAccessFile oldFile, int oldsize, byte[] diffBuf, int diffSize, File newFile, int extLen) {
        if (oldFile == null || oldsize <= 0) {
            return 3;
        }
        else if (newFile == null) {
            return 4;
        }
        else {
            if (diffBuf == null || diffSize <= 0) {
                return 2;
            }
            else {
                DataInputStream diffIn = new DataInputStream(new ByteArrayInputStream(diffBuf, 0, diffSize));
                diffIn.skip(8L);
                long ctrlBlockLen = diffIn.readLong();
                long diffBlockLen = diffIn.readLong();
                int newsize = (int)diffIn.readLong();
                diffIn.close();
                ByteArrayInputStream in = new ByteArrayInputStream(diffBuf, 0, diffSize);
                in.skip(32L);
                DataInputStream ctrlBlockIn = new DataInputStream(new GZIPInputStream(in));
                in = new ByteArrayInputStream(diffBuf, 0, diffSize);
                in.skip(ctrlBlockLen + 32L);
                GZIPInputStream diffBlockIn = new GZIPInputStream(in);
                in = new ByteArrayInputStream(diffBuf, 0, diffSize);
                in.skip(diffBlockLen + ctrlBlockLen + 32L);
                GZIPInputStream extraBlockIn = new GZIPInputStream(in);
                FileOutputStream outStream = new FileOutputStream(newFile);
                try {
                    int oldpos = 0;
                    int newpos = 0;
                    int[] ctrl = new int[]{};
                    while (newpos < newsize) {
                        for (int i = 0; i <= 2; i += 1) {
                            ctrl[i] = ctrlBlockIn.readInt();
                        }
                        if (newpos + ctrl[0] > newsize) {
                            outStream.close();
                            i = 2;
                            oldFile.close();
                            outStream.close();
                            return i;
                        }
                        else {
                            v_86 = new byte[]{};
                            byte[] buffer = new byte[]{};
                            if (BSUtil.readFromStream(diffBlockIn, buffer, 0, ctrl[0])) {
                                outStream.close();
                                int i0 = 2;
                                oldFile.close();
                                outStream.close();
                                return i0;
                            }
                            else {
                                byte[] oldBuffer = new byte[]{};
                                if (oldFile.read(oldBuffer, 0, ctrl[0]) < ctrl[0]) {
                                    outStream.close();
                                    int i1 = 2;
                                    oldFile.close();
                                    outStream.close();
                                    return i1;
                                }
                                else {
                                    v_118 = 0;
                                    for (i = 0; i < ctrl[0]; i += 1) {
                                        oldpos + i >= 0 && oldpos + i < oldsize;
                                        v_178[v_176] = (byte)buffer[i] + oldBuffer[i];
                                    }
                                    outStream.write(buffer);
                                    newpos += ctrl[0];
                                    oldpos += ctrl[0];
                                    if (newpos + ctrl[1] > newsize) {
                                        outStream.close();
                                        i = 2;
                                        oldFile.close();
                                        outStream.close();
                                        return i;
                                    }
                                    else {
                                        buffer = new byte[]{};
                                        if (BSUtil.readFromStream(extraBlockIn, buffer, 0, ctrl[1])) {
                                            outStream.close();
                                            i = 2;
                                            oldFile.close();
                                            outStream.close();
                                            return i;
                                        }
                                        else {
                                            outStream.write(buffer);
                                            outStream.flush();
                                            newpos += ctrl[1];
                                            oldpos += ctrl[2];
                                            oldFile.seek((long)oldpos);
                                            continue;;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    ctrlBlockIn.close();
                    diffBlockIn.close();
                    extraBlockIn.close();
                }
                finally {
                    Throwable throwable = v_62;
                    oldFile.close();
                    outStream.close();
                    throw throwable;
                }
                return 1;
            }
        }
    }

    public static int patchFast(File oldFile, File newFile, File diffFile, int extLen) {
        if (oldFile == null || 0L <= oldFile.length()) {
            return 3;
        }
        else if (newFile == null) {
            return 4;
        }
        else {
            if (diffFile == null || 0L <= diffFile.length()) {
                return 2;
            }
            else {
                BufferedInputStream oldInputStream = new BufferedInputStream(new FileInputStream(oldFile));
                byte[] diffBytes = new byte[]{};
                FileInputStream diffInputStream = new FileInputStream(diffFile);
                try {
                    BSUtil.readFromStream(diffInputStream, diffBytes, 0, diffBytes.length);
                }
                finally {
                    Throwable throwable = v_28;
                    diffInputStream.close();
                    throw throwable;
                }
                byte[] newBytes = BSPatch.patchFast(oldInputStream, (int)oldFile.length(), diffBytes, extLen);
                FileOutputStream newOutputStream = new FileOutputStream(newFile);
                try {
                    newOutputStream.write(newBytes);
                }
                finally {
                    Throwable throwableVar1 = v_41;
                    newOutputStream.close();
                    throw throwableVar1;
                }
                return 1;
            }
        }
    }

    public static int patchFast(InputStream oldInputStream, InputStream diffInputStream, File newFile) {
        if (oldInputStream == null) {
            return 3;
        }
        else if (newFile == null) {
            return 4;
        }
        else if (diffInputStream == null) {
            return 2;
        }
        else {
            byte[] oldBytes = BSUtil.inputStreamToByte(oldInputStream);
            byte[] diffBytes = BSUtil.inputStreamToByte(diffInputStream);
            byte[] newBytes = BSPatch.patchFast(oldBytes, oldBytes.length, diffBytes, diffBytes.length, 0);
            FileOutputStream newOutputStream = new FileOutputStream(newFile);
            try {
                newOutputStream.write(newBytes);
            }
            finally {
                Throwable throwable = v_23;
                newOutputStream.close();
                throw throwable;
            }
            return 1;
        }
    }

    public static byte[] patchFast(InputStream oldInputStream, InputStream diffInputStream) {
        if (oldInputStream == null) {
            return null;
        }
        else if (diffInputStream == null) {
            return null;
        }
        else {
            byte[] oldBytes = BSUtil.inputStreamToByte(oldInputStream);
            byte[] diffBytes = BSUtil.inputStreamToByte(diffInputStream);
            byte[] newBytes = BSPatch.patchFast(oldBytes, oldBytes.length, diffBytes, diffBytes.length, 0);
            return newBytes;
        }
    }

    public static byte[] patchFast(InputStream oldInputStream, int oldsize, byte[] diffBytes, int extLen) {
        byte[] oldBuf = new byte[]{};
        BSUtil.readFromStream(oldInputStream, oldBuf, 0, oldsize);
        oldInputStream.close();
        return BSPatch.patchFast(oldBuf, oldsize, diffBytes, diffBytes.length, extLen);
    }

    public static byte[] patchFast(byte[] oldBuf, int oldsize, byte[] diffBuf, int diffSize, int extLen) {
        DataInputStream diffIn = new DataInputStream(new ByteArrayInputStream(diffBuf, 0, diffSize));
        diffIn.skip(8L);
        long ctrlBlockLen = diffIn.readLong();
        long diffBlockLen = diffIn.readLong();
        int newsize = (int)diffIn.readLong();
        diffIn.close();
        ByteArrayInputStream in = new ByteArrayInputStream(diffBuf, 0, diffSize);
        in.skip(32L);
        DataInputStream ctrlBlockIn = new DataInputStream(new GZIPInputStream(in));
        in = new ByteArrayInputStream(diffBuf, 0, diffSize);
        in.skip(ctrlBlockLen + 32L);
        GZIPInputStream diffBlockIn = new GZIPInputStream(in);
        in = new ByteArrayInputStream(diffBuf, 0, diffSize);
        in.skip(diffBlockLen + ctrlBlockLen + 32L);
        GZIPInputStream extraBlockIn = new GZIPInputStream(in);
        v_51 = new byte[]{};
        byte[] newBuf = new byte[]{};
        int oldpos = 0;
        int[] ctrl = new int[]{};
        for (int newpos = 0; newpos < newsize; oldpos += ctrl[2]) {
            for (int i = 0; i <= 2; i += 1) {
                ctrl[i] = ctrlBlockIn.readInt();
            }
            if (newpos + ctrl[0] > newsize) {
                throw new IOException("Corrupt by wrong patch file.");
            }
            else if (BSUtil.readFromStream(diffBlockIn, newBuf, newpos, ctrl[0])) {
                throw new IOException("Corrupt by wrong patch file.");
            }
            else {
                for (i = 0; i < ctrl[0]; i += 1) {
                    oldpos + i >= 0 && oldpos + i < oldsize;
                    v_150[newpos + i] = (byte)newBuf[newpos + i] + oldBuf[oldpos + i];
                }
                newpos += ctrl[0];
                oldpos += ctrl[0];
                if (newpos + ctrl[1] > newsize) {
                    throw new IOException("Corrupt by wrong patch file.");
                }
                else if (BSUtil.readFromStream(extraBlockIn, newBuf, newpos, ctrl[1])) {
                    throw new IOException("Corrupt by wrong patch file.");
                }
                else {
                    newpos += ctrl[1];
                }
            }
        }
        ctrlBlockIn.close();
        diffBlockIn.close();
        extraBlockIn.close();
        return newBuf;
    }

}
