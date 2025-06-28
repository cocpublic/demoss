/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/bsdiff;

import java.util.Stack;
import java.util.zip.GZIPOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;

// class: com/tencent/tinker/bsdiff/BSDiff
public class BSDiff {
    final private static byte MAGIC_BYTES;

    public BSDiff() {
        super();
    }

    private static void split(int[] arrayI, int[] arrayV, int start, int len, int h) {
        int STM_ENTER = 0;
        int STM_RECURSIVE_CALLSITE1_NEXT = 1;
        int STM_EXIT = 2;
        Stack emuStack = new Stack();
        emuStack.push(new BSDiff$1EmuStackFrame(2, start, len, h));
        int stmLabel = 0;
        while (emuStack.empty()) {
v_16 = (BSDiff$1EmuStackFrame)emuStack.peek();
BSDiff$1EmuStackFrame currFrame = (BSDiff$1EmuStackFrame)emuStack.peek();
            switch(stmLabel) {
                case 0: {
                    int tmp;
                    if (currFrame.len < 16) {
                        currFrame.k = currFrame.start;
                        while (currFrame.k < currFrame.start + currFrame.len) {
                            currFrame.j = 1;
                            currFrame.x = arrayV[arrayI[currFrame.k] + currFrame.h];
                            currFrame.i = 1;
                            while (currFrame.k + currFrame.i < currFrame.start + currFrame.len) {
                                arrayV[arrayI[currFrame.k + currFrame.i] + currFrame.h] < currFrame.x;
                                currFrame.x = arrayV[arrayI[currFrame.k + currFrame.i] + currFrame.h];
                                currFrame.j = 0;
                                arrayV[arrayI[currFrame.k + currFrame.i] + currFrame.h] == currFrame.x;
                                tmp = arrayI[currFrame.k + currFrame.j];
                                arrayI[currFrame.k + currFrame.j] = arrayI[currFrame.k + currFrame.i];
                                arrayI[currFrame.k + currFrame.i] = tmp;
                                v_466.j = currFrame.j + 1;
                                v_466.i = currFrame.i + 1;
                            }
                            currFrame.i = 0;
                            while (currFrame.i < currFrame.j) {
                                arrayV[arrayI[currFrame.k + currFrame.i]] = currFrame.k + currFrame.j - 1;
                                v_466.i = currFrame.i + 1;
                            }
                            currFrame.j == 1;
                            arrayI[currFrame.k] = -1;
                            v_466.k = currFrame.k + currFrame.j;
                        }
                        stmLabel = 2;
                        continue;;
                    }
                    else {
                        currFrame.x = arrayV[arrayI[currFrame.start + currFrame.len / 2] + currFrame.h];
                        currFrame.jj = 0;
                        currFrame.kk = 0;
                        currFrame.i = currFrame.start;
                        while (currFrame.i < currFrame.start + currFrame.len) {
                            arrayV[arrayI[currFrame.i] + currFrame.h] < currFrame.x;
                            v_466.jj = currFrame.jj + 1;
                            arrayV[arrayI[currFrame.i] + currFrame.h] == currFrame.x;
                            v_466.kk = currFrame.kk + 1;
                            v_466.i = currFrame.i + 1;
                        }
                        v_466.jj = currFrame.jj + currFrame.start;
                        v_466.kk = currFrame.kk + currFrame.jj;
                        currFrame.i = currFrame.start;
                        currFrame.j = 0;
                        currFrame.k = 0;
                        while (currFrame.i < currFrame.jj) {
                            if (arrayV[arrayI[currFrame.i] + currFrame.h] < currFrame.x) {
                                v_466.i = currFrame.i + 1;
                                continue;;
                            }
                            else {
                                tmp = arrayV[arrayI[currFrame.i] + currFrame.h] == currFrame.x ? arrayI[currFrame.i] : arrayI[currFrame.i];
                                arrayI[currFrame.i] = arrayI[currFrame.kk + currFrame.k];
                                arrayI[currFrame.kk + currFrame.k] = tmp;
                                v_466.k = currFrame.k + 1;
                                continue;;
                            }
                        }
                        while (currFrame.jj + currFrame.j < currFrame.kk) {
                            if (arrayV[arrayI[currFrame.jj + currFrame.j] + currFrame.h] == currFrame.x) {
                                v_466.j = currFrame.j + 1;
                                continue;;
                            }
                            else {
                                tmp = arrayI[currFrame.jj + currFrame.j];
                                arrayI[currFrame.jj + currFrame.j] = arrayI[currFrame.kk + currFrame.k];
                                arrayI[currFrame.kk + currFrame.k] = tmp;
                                v_466.k = currFrame.k + 1;
                                continue;;
                            }
                        }
                        stmLabel = 1;
                        if (currFrame.jj > currFrame.start) {
                            emuStack.push(new BSDiff$1EmuStackFrame(stmLabel, currFrame.start, currFrame.jj - currFrame.start, currFrame.h));
                            stmLabel = 0;
                            continue;;
                        }
                    }
                }
                case 1: {
                    currFrame.i = 0;
                    while (currFrame.i < currFrame.kk - currFrame.jj) {
                        arrayV[arrayI[currFrame.jj + currFrame.i]] = currFrame.kk - 1;
                        v_466.i = currFrame.i + 1;
                    }
                    if (currFrame.jj == currFrame.kk - 1) {
                        arrayI[currFrame.jj] = -1;
                    }
                    stmLabel = 2;
                    if (currFrame.start + currFrame.len > currFrame.kk) {
                        emuStack.push(new BSDiff$1EmuStackFrame(stmLabel, currFrame.kk, currFrame.start + currFrame.len - currFrame.kk, currFrame.h));
                        stmLabel = 0;
                        continue;;
                    }
                }
                default: {
                    stmLabel = currFrame.stmRetLabel;
                    emuStack.pop();
                }
            }
        }
    }

    private static void qsufsort(int[] arrayI, int[] arrayV, byte[] oldBuf, int oldsize) {
        v_1 = new int[]{};
        int[] buckets = new int[]{};
        v_2 = 0;
        for (int i = 0; i < oldsize; i += 1) {
            v_84[oldBuf[i] & 255] = buckets[oldBuf[i] & 255] + 1;
        }
        for (i = 1; i < 256; i += 1) {
            v_84[v_85] = buckets[i] + buckets[i - 1];
        }
        for (i = 255; i > 0; i += 255) {
            buckets[i] = buckets[i - 1];
        }
        buckets[0] = 0;
        for (i = 0; i < oldsize; i += 1) {
            v_84[oldBuf[i] & 255] = buckets[oldBuf[i] & 255] + 1;
            arrayI[buckets[oldBuf[i] & 255] + 1] = i;
        }
        arrayI[0] = oldsize;
        for (i = 0; i < oldsize; i += 1) {
            arrayV[i] = buckets[oldBuf[i] & 255];
        }
        arrayV[oldsize] = 0;
        for (i = 1; i < 256; i += 1) {
            if (buckets[i] == buckets[i - 1] + 1) {
                arrayI[buckets[i]] = -1;
            }
        }
        arrayI[0] = -1;
        int i0 = 1;
        while (arrayI[0] != - oldsize + 1) {
            int len = 0;
            i = 0;
            while (i < oldsize + 1) {
                if (arrayI[i] < 0) {
                    len -= arrayI[i];
                    i -= arrayI[i];
                    continue;;
                }
                else {
                    if (len != 0) {
                        arrayI[i - len] = - len;
                    }
                    len = arrayV[arrayI[i]] + 1 - i;
                    BSDiff.split(arrayI, arrayV, i, len, i0);
                    i += len;
                    len = 0;
                    continue;;
                }
            }
            if (len != 0) {
                arrayI[i - len] = - len;
            }
            i0 += i0;
        }
        for (int i1 = 0; i1 < oldsize + 1; i1 += 1) {
            arrayI[arrayV[i1]] = i1;
        }
    }

    private static int search(int[] arrayI, byte[] oldBuf, int oldSize, byte[] newBuf, int newSize, int newBufOffset, int start, int end, BSDiff$IntByRef pos) {
        int x = end - start < 2 ? start + end - start / 2 : BSDiff.matchlen(oldBuf, oldSize, arrayI[start], newBuf, newSize, newBufOffset);
        if (BSDiff.memcmp(oldBuf, oldSize, arrayI[x], newBuf, newSize, newBufOffset) < 0) {
            return BSDiff.search(arrayI, oldBuf, oldSize, newBuf, newSize, newBufOffset, x, end, pos);
        }
        else {
            return BSDiff.search(arrayI, oldBuf, oldSize, newBuf, newSize, newBufOffset, start, x, pos);
        }
    }

    private static int matchlen(byte[] oldBuf, int oldSize, int oldOffset, byte[] newBuf, int newSize, int newOffset) {
        int end = Math.min(oldSize - oldOffset, newSize - newOffset);
        for (int i = 0; i < end; i += 1) {
            if (oldBuf[oldOffset + i] != newBuf[newOffset + i]) {
                return i;
            }
            else {
            }
        }
        return end;
    }

    private static int memcmp(byte[] s1, int s1Size, int s1offset, byte[] s2, int s2Size, int s2offset) {
        int n = s1Size - s1offset;
        if (n > s2Size - s2offset) {
            n = s2Size - s2offset;
        }
        for (int i = 0; i < n; i += 1) {
            if (s1[i + s1offset] != s2[i + s2offset]) {
                if (s1[i + s1offset] < s2[i + s2offset]) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
            else {
            }
        }
        return 0;
    }

    public static void bsdiff(File oldFile, File newFile, File diffFile) {
        BufferedInputStream oldInputStream = new BufferedInputStream(new FileInputStream(oldFile));
        BufferedInputStream newInputStream = new BufferedInputStream(new FileInputStream(newFile));
        FileOutputStream diffOutputStream = new FileOutputStream(diffFile);
        try {
            byte[] diffBytes = BSDiff.bsdiff(oldInputStream, (int)oldFile.length(), newInputStream, (int)newFile.length());
            diffOutputStream.write(diffBytes);
            return;
        }
        finally {
            Throwable throwable = v_20;
            diffOutputStream.close();
            throw throwable;
        }
    }

    public static byte[] bsdiff(InputStream oldInputStream, int oldsize, InputStream newInputStream, int newsize) {
        byte[] oldBuf = new byte[]{};
        BSUtil.readFromStream(oldInputStream, oldBuf, 0, oldsize);
        oldInputStream.close();
        byte[] newBuf = new byte[]{};
        BSUtil.readFromStream(newInputStream, newBuf, 0, newsize);
        newInputStream.close();
        return BSDiff.bsdiff(oldBuf, oldsize, newBuf, newsize);
    }

    public static byte[] bsdiff(byte[] oldBuf, int oldsize, byte[] newBuf, int newsize) {
        int[] arrayI = new int[]{};
        BSDiff.qsufsort(arrayI, new int[]{}, oldBuf, oldsize);
        int diffBLockLen = 0;
        byte[] diffBlock = new byte[]{};
        int extraBlockLen = 0;
        byte[] extraBlock = new byte[]{};
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream diffOut = new DataOutputStream(byteOut);
        diffOut.write(BSDiff.MAGIC_BYTES);
        diffOut.writeLong(-1L);
        diffOut.writeLong(-1L);
        diffOut.writeLong((long)newsize);
        diffOut.flush();
        GZIPOutputStream bzip2Out = new GZIPOutputStream(diffOut);
        DataOutputStream dataOut = new DataOutputStream(bzip2Out);
        int scan = 0;
        int matchLen = 0;
        int lastscan = 0;
        int lastpos = 0;
        int lastoffset = 0;
        BSDiff$IntByRef pos = new BSDiff$IntByRef(null);
        while (scan < newsize) {
            int oldscore = 0;
            scan += matchLen;
            int scsc = scan += matchLen;
            while (scan < newsize) {
                for (matchLen = BSDiff.search(arrayI, oldBuf, oldsize, newBuf, newsize, scan, 0, oldsize, pos); scsc < scan + matchLen; scsc += 1) {
                    if (scsc + lastoffset < oldsize && oldBuf[scsc + lastoffset] == newBuf[scsc]) {
                        oldscore += 1;
                    }
                }
                matchLen != oldscore || matchLen == 0 ? scan + lastoffset < oldsize && oldBuf[scan + lastoffset] == newBuf[scan] : matchLen > oldscore + 8;
                oldscore += 255;
                scan += 1;
            }
            if (matchLen != oldscore || scan == newsize) {
                int lenb;
                int equalNum = 0;
                int sf = 0;
                int lenFromOld = 0;
                int i = 0;
                while (true) {
                    if (lastscan + i < scan && lastpos + i < oldsize) {
                        if (oldBuf[lastpos + i] == newBuf[lastscan + i]) {
                            equalNum += 1;
                        }
                        i += 1;
                        if (equalNum * 2 - i > sf * 2 - lenFromOld) {
                            sf = equalNum;
                            continue;;
                        }
                    }
                    else {
                        lenb = 0;
                        if (if (scan < newsize ) break; /* target: 466 */) {
                        }
                    }
                }
                equalNum = 0;
                int sb = 0;
                i = 1;
                while (true) {
                    scan >= lastscan + i && BSDiff$IntByRef.access$000(pos) >= i;
                    oldBuf[BSDiff$IntByRef.access$000(pos) - i] == newBuf[scan - i];
                    equalNum += 1;
                    equalNum * 2 - i > sb * 2 - lenb;
                    sb = equalNum;
                    i += 1;
                }
                if (lastscan + i > scan - i) {
                    int overlap = lastscan + i - scan - i;
                    equalNum = 0;
                    int ss = 0;
                    int lens = 0;
                    for (i = 0; i < overlap; i += 1) {
                        newBuf[lastscan + i - overlap + i] == oldBuf[lastpos + i - overlap + i];
                        equalNum += 1;
                        newBuf[scan - i + i] == oldBuf[BSDiff$IntByRef.access$000(pos) - i + i];
                        equalNum += 255;
                        equalNum > ss;
                        ss = equalNum;
                        lens = i + 1;
                    }
                    i += lens - overlap;
                    i -= lens;
                }
                for (i = 0; i < i; i += 1) {
                    diffBlock[diffBLockLen + i] = (byte)newBuf[lastscan + i] - oldBuf[lastpos + i];
                }
                for (i = 0; i < scan - i - lastscan + i; i += 1) {
                    extraBlock[extraBlockLen + i] = newBuf[lastscan + i + i];
                }
                diffBLockLen += i;
                extraBlockLen += scan - i - lastscan + i;
                dataOut.writeInt(i);
                dataOut.writeInt(scan - i - lastscan + i);
                dataOut.writeInt(BSDiff$IntByRef.access$000(pos) - i - lastpos + i);
                lastscan = scan - i;
                lastpos = BSDiff$IntByRef.access$000(pos) - i;
                lastoffset = BSDiff$IntByRef.access$000(pos) - scan;
                continue;;
            }
        }
        dataOut.flush();
        bzip2Out.finish();
        int ctrlBlockLen = diffOut.size() - 32;
        bzip2Out = new GZIPOutputStream(diffOut);
        bzip2Out.write(diffBlock, 0, diffBLockLen);
        bzip2Out.finish();
        bzip2Out.flush();
        int diffBlockLen = diffOut.size() - ctrlBlockLen - 32;
        bzip2Out = new GZIPOutputStream(diffOut);
        bzip2Out.write(extraBlock, 0, extraBlockLen);
        bzip2Out.finish();
        bzip2Out.flush();
        diffOut.close();
        ByteArrayOutputStream byteHeaderOut = new ByteArrayOutputStream(32);
        DataOutputStream headerOut = new DataOutputStream(byteHeaderOut);
        headerOut.write(BSDiff.MAGIC_BYTES);
        headerOut.writeLong((long)ctrlBlockLen);
        headerOut.writeLong((long)diffBlockLen);
        headerOut.writeLong((long)newsize);
        headerOut.close();
        byte[] diffBytes = byteOut.toByteArray();
        byte[] headerBytes = byteHeaderOut.toByteArray();
        System.arraycopy(headerBytes, 0, diffBytes, 0, headerBytes.length);
        return diffBytes;
    }

    public static void main(String[] args) {
        File oldFile = new File("/Users/tomystang/bsdiff-test/old/classes.dex");
        File newFile = new File("/Users/tomystang/bsdiff-test/new/classes.dex");
        File diffFile = new File("/Users/tomystang/bsdiff-test/test_bsdiff.diff");
        BSDiff.bsdiff(oldFile, newFile, diffFile);
    }

    static  {
        BSDiff.MAGIC_BYTES = new byte[]{77, 105, 99, 114, 111, 77, 115, 103};
    }

    // class: com/tencent/tinker/bsdiff/BSDiff$IntByRef
    class BSDiff$IntByRef {
        private int value;

        private BSDiff$IntByRef() {
            super();
        }

        static /* synthetic */ int access$002(BSDiff$IntByRef x0, int x1) {
            x0.value = x1;
            return x1;
        }

        /* synthetic */ BSDiff$IntByRef(BSDiff$1 x0) {
            super();
        }

        static /* synthetic */ int access$000(BSDiff$IntByRef x0) {
            return x0.value;
        }

    }
    // class: com/tencent/tinker/bsdiff/BSDiff$IntByRef
    class BSDiff$IntByRef {
        private int value;

        private BSDiff$IntByRef() {
            super();
        }

        static /* synthetic */ int access$002(BSDiff$IntByRef x0, int x1) {
            x0.value = x1;
            return x1;
        }

        /* synthetic */ BSDiff$IntByRef(BSDiff$1 x0) {
            super();
        }

        static /* synthetic */ int access$000(BSDiff$IntByRef x0) {
            return x0.value;
        }

    }
}
