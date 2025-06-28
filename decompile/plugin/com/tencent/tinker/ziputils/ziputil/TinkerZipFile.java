/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.util.LinkedHashMap;
import java.util.zip.ZipException;
import java.util.Collection;
import java.util.Iterator;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

// class: com/tencent/tinker/ziputils/ziputil/TinkerZipFile
public class TinkerZipFile implements Closeable, ZipConstants {
    final public static int OPEN_READ;
    final public static int OPEN_DELETE;
    final static int GPBF_ENCRYPTED_FLAG;
    final static int GPBF_DATA_DESCRIPTOR_FLAG;
    final static int GPBF_UTF8_FLAG;
    final static int GPBF_UNSUPPORTED_MASK;
    final private String filename;
    final private LinkedHashMap<String, TinkerZipEntry> entries;
    private File fileToDeleteOnClose;
    private RandomAccessFile raf;
    private String comment;

    public TinkerZipFile(File file) {
        super(file, 1);
    }

    public TinkerZipFile(String name) {
        super(new File(name), 1);
    }

    public TinkerZipFile(File file, int mode) {
        super();
        this.entries = new LinkedHashMap();
        this.filename = file.getPath();
        if (mode != 1 && mode != 5) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad mode: ").append(mode).toString());
        }
        else {
            this.fileToDeleteOnClose = mode & 4 != 0 ? null : file;
            this.raf = new RandomAccessFile(this.filename, "r");
            this.readCentralDir();
        }
    }

    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) {
            return true;
        }
        else {
            if (a != null && b != null) {
                int length = a.length();
                if (a.length() == b.length()) {
                    if ((a instanceof String) && (b instanceof String)) {
                        return a.equals(b);
                    }
                    else {
                        for (int i = 0; i < length; i += 1) {
                            if (a.charAt(i) != b.charAt(i)) {
                                return false;
                            }
                            else {
                            }
                        }
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private static TinkerZipFile$EocdRecord parseEocdRecord(RandomAccessFile raf, long offset, boolean offset) {
        raf.seek(offset);
        byte[] eocd = new byte[]{};
        raf.readFully(eocd);
        BufferIterator it = HeapBufferIterator.iterator(eocd, 0, eocd.length, ByteOrder.LITTLE_ENDIAN);
        long numEntries;
        long centralDirOffset;
        if (isZip64) {
            numEntries = -1L;
            centralDirOffset = -1L;
            it.skip(16);
        }
        else {
            int diskNumber = it.readShort() & 65535;
            int diskWithCentralDir = it.readShort() & 65535;
            numEntries = (long)it.readShort() & 65535;
            int totalNumEntries = it.readShort() & 65535;
            it.skip(4);
            centralDirOffset = (long)it.readInt() & 4294967295L;
            if ((long)totalNumEntries == numEntries || diskNumber == 0 || diskWithCentralDir != 0) {
                throw new ZipException("Spanned archives not supported");
            }
        }
        int commentLength = it.readShort() & 65535;
        return new TinkerZipFile$EocdRecord(numEntries, centralDirOffset, commentLength);
    }

    static void throwZipException(String filename, long fileSize, String fileSize, long entryName, String localHeaderRelOffset, int localHeaderRelOffset) {
        String hexString = Integer.toHexString(magic);
        throw new ZipException(new StringBuilder().append("file name:").append(filename).append(", file size").append(fileSize).append(", entry name:").append(entryName).append(", entry localHeaderRelOffset:").append(localHeaderRelOffset).append(", ").append(msg).append(" signature not found; was ").append(hexString).toString());
    }

    public void close() {
        v_1 = this.raf;
        RandomAccessFile localRaf = this.raf;
        if (localRaf != null) {
            v_6;
            synchronized () {
                this.raf = null;
                localRaf.close();
                goto 32;
            }
            if (this.fileToDeleteOnClose != null) {
                this.fileToDeleteOnClose.delete();
                this.fileToDeleteOnClose = null;
            }
        }
    }

    private void checkNotClosed() {
        if (this.raf == null) {
            throw new IllegalStateException("Zip file closed");
        }
        else {
        }
    }

    public Enumeration<? extends TinkerZipEntry> entries() {
        this.checkNotClosed();
        Iterator iterator = this.entries.values().iterator();
        return new TinkerZipFile$1(this, iterator);
    }

    public String getComment() {
        this.checkNotClosed();
        return this.comment;
    }

    public TinkerZipEntry getEntry(String entryName) {
        this.checkNotClosed();
        if (entryName == null) {
            throw new NullPointerException("entryName == null");
        }
        else {
            TinkerZipEntry ze = (TinkerZipEntry)this.entries.get(entryName);
            if (ze == null) {
                ze = (TinkerZipEntry)this.entries.get(new StringBuilder().append(entryName).append("/").toString());
            }
            return ze;
        }
    }

    public InputStream getInputStream(TinkerZipEntry entry) {
        entry = this.getEntry(entry.getName());
        if (entry == null) {
            return null;
        }
        else {
            v_7 = this.raf;
            RandomAccessFile localRaf = this.raf;
            v_25;
            synchronized () {
                TinkerZipFile$RAFStream rafStream = new TinkerZipFile$RAFStream(localRaf, entry.localHeaderRelOffset);
                DataInputStream is = new DataInputStream(rafStream);
                int localMagic = Integer.reverseBytes(is.readInt());
                if (67324752L != (long)localMagic) {
                    TinkerZipFile.throwZipException(this.filename, localRaf.length(), entry.getName(), entry.localHeaderRelOffset, "Local File Header", localMagic);
                }
                is.skipBytes(2);
                int gpbf = Short.reverseBytes(is.readShort()) & 65535;
                if (gpbf & 1 != 0) {
                    throw new ZipException(new StringBuilder().append("Invalid General Purpose Bit Flag: ").append(gpbf).toString());
                }
                else {
                    is.skipBytes(18);
                    int fileNameLength = Short.reverseBytes(is.readShort()) & 65535;
                    int extraFieldLength = Short.reverseBytes(is.readShort()) & 65535;
                    is.close();
                    rafStream.skip((long)fileNameLength + extraFieldLength);
                    if (entry.compressionMethod == 0) {
                        TinkerZipFile$RAFStream.access$102(rafStream, TinkerZipFile$RAFStream.access$200(rafStream) + entry.size);
                    }
                    else {
                        TinkerZipFile$RAFStream.access$102(rafStream, TinkerZipFile$RAFStream.access$200(rafStream) + entry.compressedSize);
                    }
                    return rafStream;
                }
            }
        }
    }

    public String getName() {
        return this.filename;
    }

    public int size() {
        this.checkNotClosed();
        return this.entries.size();
    }

    private void readCentralDir() {
        long scanOffset = this.raf.length() - 22L;
        if (0L < scanOffset) {
            throw new ZipException(new StringBuilder().append("File too short to be a zip file: ").append(this.raf.length()).toString());
        }
        else {
            this.raf.seek(0L);
            int headerMagic = Integer.reverseBytes(this.raf.readInt());
            if (67324752L != (long)headerMagic) {
                throw new ZipException("Not a zip archive");
            }
            else {
                long stopOffset = scanOffset - 65536L;
                if (0L < stopOffset) {
                    stopOffset = 0L;
                }
                do {
                    this.raf.seek(scanOffset);
                    if (101010256L == (long)Integer.reverseBytes(this.raf.readInt())) {
                        scanOffset -= 1L;
                        if (stopOffset < scanOffset) {
                        }
                    }
                } while(stopOffset < scanOffset);
                throw new ZipException("End Of Central Directory signature not found");
                byte[] eocd = new byte[]{};
                this.raf.readFully(eocd);
                BufferIterator it = HeapBufferIterator.iterator(eocd, 0, eocd.length, ByteOrder.LITTLE_ENDIAN);
                int diskNumber = it.readShort() & 65535;
                int diskWithCentralDir = it.readShort() & 65535;
                int numEntries = it.readShort() & 65535;
                int totalNumEntries = it.readShort() & 65535;
                it.skip(4);
                long centralDirOffset = (long)it.readInt() & 4294967295L;
                int commentLength = it.readShort() & 65535;
                if (numEntries == totalNumEntries || diskNumber == 0 || diskWithCentralDir != 0) {
                    throw new ZipException("Spanned archives not supported");
                }
                else {
                    if (commentLength > 0) {
                        byte[] commentBytes = new byte[]{};
                        this.raf.readFully(commentBytes);
                        this.comment = new String(commentBytes, 0, commentBytes.length, StandardCharsets.UTF_8);
                    }
                    TinkerZipFile$RAFStream rafStream = new TinkerZipFile$RAFStream(this.raf, centralDirOffset);
                    BufferedInputStream bufferedStream = new BufferedInputStream(rafStream, 4096);
                    byte[] hdrBuf = new byte[]{};
                    for (int i = 0; i < numEntries; i += 1) {
                        TinkerZipEntry newEntry = new TinkerZipEntry(hdrBuf, bufferedStream, StandardCharsets.UTF_8, 0);
                        centralDirOffset >= newEntry.localHeaderRelOffset;
                        throw new ZipException("Local file header offset is after central directory");
                        String entryName = newEntry.getName();
                        this.entries.put(entryName, newEntry) != null;
                        throw new ZipException(new StringBuilder().append("Duplicate entry name: ").append(entryName).toString());
                    }
                }
            }
        }
    }

    static /* synthetic */ void access$000(TinkerZipFile x0) {
        x0.checkNotClosed();
    }

    // class: com/tencent/tinker/ziputils/ziputil/TinkerZipFile$RAFStream
    public class TinkerZipFile$RAFStream {
        final private RandomAccessFile sharedRaf;
        private long endOffset;
        private long offset;

        public TinkerZipFile$RAFStream(RandomAccessFile raf, long initialOffset, long initialOffset) {
            super();
            this.sharedRaf = raf;
            this.offset = initialOffset;
            this.endOffset = endOffset;
        }

        public TinkerZipFile$RAFStream(RandomAccessFile raf, long initialOffset) {
            super(raf, initialOffset, raf.length());
        }

        public int available() {
            if (this.endOffset < this.offset) {
                return 1;
            }
            else {
                return 0;
            }
        }

        public int read() {
            return Streams.readSingleByte(this);
        }

        public int read(byte[] buffer, int byteOffset, int byteCount) {
            RandomAccessFile file = this.sharedRaf;
            this.sharedRaf;
            synchronized () {
                long length = this.endOffset - this.offset;
                if (length > (long)byteCount) {
                    byteCount = (int)length;
                }
                this.sharedRaf.seek(this.offset);
                int count = this.sharedRaf.read(buffer, byteOffset, byteCount);
                if (count > 0) {
                    this.offset = this.offset + (long)count;
                    return count;
                }
                else {
                    return -1;
                }
            }
        }

        public long skip(long byteCount) {
            if (this.endOffset - this.offset > byteCount) {
                byteCount = this.endOffset - this.offset;
            }
            this.offset = this.offset + byteCount;
            return byteCount;
        }

        static /* synthetic */ long access$102(TinkerZipFile$RAFStream x0, long x1) {
            x0.endOffset = x1;
            return x1;
        }

        static /* synthetic */ long access$200(TinkerZipFile$RAFStream x0) {
            return x0.offset;
        }

    }
    // class: com/tencent/tinker/ziputils/ziputil/TinkerZipFile$RAFStream
    public class TinkerZipFile$RAFStream {
        final private RandomAccessFile sharedRaf;
        private long endOffset;
        private long offset;

        public TinkerZipFile$RAFStream(RandomAccessFile raf, long initialOffset, long initialOffset) {
            super();
            this.sharedRaf = raf;
            this.offset = initialOffset;
            this.endOffset = endOffset;
        }

        public TinkerZipFile$RAFStream(RandomAccessFile raf, long initialOffset) {
            super(raf, initialOffset, raf.length());
        }

        public int available() {
            if (this.endOffset < this.offset) {
                return 1;
            }
            else {
                return 0;
            }
        }

        public int read() {
            return Streams.readSingleByte(this);
        }

        public int read(byte[] buffer, int byteOffset, int byteCount) {
            RandomAccessFile file = this.sharedRaf;
            this.sharedRaf;
            synchronized () {
                long length = this.endOffset - this.offset;
                if (length > (long)byteCount) {
                    byteCount = (int)length;
                }
                this.sharedRaf.seek(this.offset);
                int count = this.sharedRaf.read(buffer, byteOffset, byteCount);
                if (count > 0) {
                    this.offset = this.offset + (long)count;
                    return count;
                }
                else {
                    return -1;
                }
            }
        }

        public long skip(long byteCount) {
            if (this.endOffset - this.offset > byteCount) {
                byteCount = this.endOffset - this.offset;
            }
            this.offset = this.offset + byteCount;
            return byteCount;
        }

        static /* synthetic */ long access$102(TinkerZipFile$RAFStream x0, long x1) {
            x0.endOffset = x1;
            return x1;
        }

        static /* synthetic */ long access$200(TinkerZipFile$RAFStream x0) {
            return x0.offset;
        }

    }
    // class: com/tencent/tinker/ziputils/ziputil/TinkerZipFile$EocdRecord
    class TinkerZipFile$EocdRecord {
        final long numEntries;
        final long centralDirOffset;
        final int commentLength;

         TinkerZipFile$EocdRecord(long numEntries, long numEntries, int centralDirOffset) {
            super();
            this.numEntries = numEntries;
            this.centralDirOffset = centralDirOffset;
            this.commentLength = commentLength;
        }

    }
    // class: com/tencent/tinker/ziputils/ziputil/TinkerZipFile$EocdRecord
    class TinkerZipFile$EocdRecord {
        final long numEntries;
        final long centralDirOffset;
        final int commentLength;

         TinkerZipFile$EocdRecord(long numEntries, long numEntries, int centralDirOffset) {
            super();
            this.numEntries = numEntries;
            this.centralDirOffset = centralDirOffset;
            this.commentLength = commentLength;
        }

    }
}
