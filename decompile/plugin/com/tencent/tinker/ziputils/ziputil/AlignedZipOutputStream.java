/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;

import java.util.zip.Deflater;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.HashSet;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

// class: com/tencent/tinker/ziputils/ziputil/AlignedZipOutputStream
public class AlignedZipOutputStream {
    final private static long LOCSIG;
    final private static long EXTSIG;
    final private static long CENSIG;
    final private static long ENDSIG;
    final private static int LOCHDR;
    final private static int EXTHDR;
    final private static int MOD_DATE_CONST;
    final private static int TIME_CONST;
    final private static int GPBF_DATA_DESCRIPTOR_FLAG;
    final private static int GPBF_UTF8_FLAG;
    final private static byte EMPTY_BYTE_ARRAY;
    final private static byte ONE_ELEM_BYTE_ARRAY;
    final public static int DEFLATED;
    final public static int STORED;
    final private static int ZIPLocalHeaderVersionNeeded;
    private byte commentBytes;
    final private HashSet<String> entries;
    private int defaultCompressionMethod;
    private int compressionLevel;
    private ByteArrayOutputStream cDir;
    private ZipEntry currentEntry;
    final private CRC32 crc;
    private long crcDataSize;
    private int offset;
    private int nameLength;
    private byte nameBytes;
    private boolean finished;
    private boolean closed;
    final private int alignBytes;
    private int padding;

    public AlignedZipOutputStream(OutputStream os) {
        super(os, 4);
    }

    public AlignedZipOutputStream(OutputStream os, int alignBytes) {
        super(os, new Deflater(-1, 1));
        this.commentBytes = AlignedZipOutputStream.EMPTY_BYTE_ARRAY;
        this.entries = new HashSet();
        this.defaultCompressionMethod = 8;
        this.compressionLevel = -1;
        this.cDir = new ByteArrayOutputStream();
        this.crc = new CRC32();
        this.crcDataSize = 0L;
        this.offset = 0;
        this.finished = false;
        this.closed = false;
        this.padding = 0;
        this.alignBytes = alignBytes;
    }

    public void close() {
        if (this.closed) {
            this.finish();
            this.def.end();
            this.out.close();
            this.out = null;
            this.closed = true;
        }
    }

    public void closeEntry() {
        this.checkOpen();
        if (this.currentEntry == null) {
        }
        else {
            if (this.currentEntry.getMethod() == 8) {
                this.finish();
            }
            if (this.currentEntry.getMethod() == 0) {
                if (this.currentEntry.getCrc() != this.crc.getValue()) {
                    throw new ZipException("CRC mismatch");
                }
                else if (this.crcDataSize != this.currentEntry.getSize()) {
                    throw new ZipException("Size mismatch");
                }
            }
            int curOffset = 30;
            if (this.currentEntry.getMethod() != 0) {
                curOffset += 16;
                this.writeLong(this.out, 134695760L);
                this.currentEntry.setCrc(this.crc.getValue());
                this.writeLong(this.out, this.currentEntry.getCrc());
                this.currentEntry.setCompressedSize((long)this.def.getTotalOut());
                this.writeLong(this.out, this.currentEntry.getCompressedSize());
                this.currentEntry.setSize((long)this.def.getTotalIn());
                this.writeLong(this.out, this.currentEntry.getSize());
            }
            int flags = this.currentEntry.getMethod() == 0 ? 8 : 0;
            flags |= 2048;
            this.writeLong(this.cDir, 33639248L);
            this.writeShort(this.cDir, 20);
            this.writeShort(this.cDir, 20);
            this.writeShort(this.cDir, flags);
            this.writeShort(this.cDir, this.currentEntry.getMethod());
            this.writeShort(this.cDir, 0);
            this.writeShort(this.cDir, 33);
            this.writeLong(this.cDir, this.crc.getValue());
            curOffset = this.currentEntry.getMethod() == 8 ? (int)(long)curOffset + this.writeLong(this.cDir, this.crcDataSize) : (int)(long)curOffset + this.writeLong(this.cDir, (long)this.def.getTotalOut());
            this.writeLong(this.cDir, this.crcDataSize);
            curOffset += this.writeShort(this.cDir, this.nameLength);
            if (this.currentEntry.getExtra() != null) {
                curOffset += this.writeShort(this.cDir, this.currentEntry.getExtra().length);
                goto 471;
            }
            else {
                this.writeShort(this.cDir, 0);
            }
            String comment = this.currentEntry.getComment();
            if (comment != null) {
                commentBytes = comment.getBytes(Charset.forName("UTF-8"));
            }
            this.writeShort(this.cDir, AlignedZipOutputStream.EMPTY_BYTE_ARRAY.length);
            this.writeShort(this.cDir, 0);
            this.writeShort(this.cDir, 0);
            this.writeLong(this.cDir, 0L);
            this.writeLong(this.cDir, (long)this.offset);
            this.cDir.write(this.nameBytes);
            this.nameBytes = null;
            if (this.currentEntry.getExtra() != null) {
                this.cDir.write(this.currentEntry.getExtra());
            }
            this.offset = this.offset + curOffset + this.padding;
            this.padding = 0;
            if (AlignedZipOutputStream.EMPTY_BYTE_ARRAY.length > 0) {
                this.cDir.write(AlignedZipOutputStream.EMPTY_BYTE_ARRAY);
            }
            this.currentEntry = null;
            this.crc.reset();
            this.crcDataSize = 0L;
            this.def.reset();
        }
    }

    public void finish() {
        this.checkOpen();
        if (this.finished) {
        }
        else if (this.entries.isEmpty()) {
            throw new ZipException("No entries");
        }
        else {
            if (this.currentEntry != null) {
                this.closeEntry();
            }
            int cdirSize = this.cDir.size();
            this.writeLong(this.cDir, 101010256L);
            this.writeShort(this.cDir, 0);
            this.writeShort(this.cDir, 0);
            this.writeShort(this.cDir, this.entries.size());
            this.writeShort(this.cDir, this.entries.size());
            this.writeLong(this.cDir, (long)cdirSize);
            this.writeLong(this.cDir, (long)this.offset + this.padding);
            this.writeShort(this.cDir, this.commentBytes.length);
            if (this.commentBytes.length > 0) {
                this.cDir.write(this.commentBytes);
            }
            this.cDir.writeTo(this.out);
            this.cDir = null;
            this.finished = true;
        }
    }

    private int getPaddingByteCount(ZipEntry entry, int entryFileOffset) {
        if (entry.getMethod() != 0 || this.alignBytes == 0) {
            return 0;
        }
        else {
            return this.alignBytes - entryFileOffset % this.alignBytes % this.alignBytes;
        }
    }

    private void makePaddingToStream(OutputStream os, int padding) {
        if (padding <= 0) {
        }
        else {
            while (true) {
                padding += 255;
                if (padding > 0) {
                    os.write(0);
                }
                else {
                }
            }
        }
    }

    public void putNextEntry(ZipEntry ze) {
        if (this.currentEntry != null) {
            this.closeEntry();
        }
        int method = ze.getMethod();
        if (method == -1) {
            method = this.defaultCompressionMethod;
        }
        if (method == 0) {
            if (-1L == ze.getCompressedSize()) {
                ze.setCompressedSize(ze.getSize());
                goto 71;
            }
            else if (-1L == ze.getSize()) {
                ze.setSize(ze.getCompressedSize());
            }
            if (-1L == ze.getCrc()) {
                throw new ZipException("STORED entry missing CRC");
            }
            else if (-1L == ze.getSize()) {
                throw new ZipException("STORED entry missing size");
            }
            else if (ze.getCompressedSize() != ze.getSize()) {
                throw new ZipException("STORED entry size/compressed size mismatch");
            }
        }
        this.checkOpen();
        if (this.entries.contains(ze.getName())) {
            throw new ZipException(new StringBuilder().append("Entry already exists: ").append(ze.getName()).toString());
        }
        else if (this.entries.size() == 65535) {
            throw new ZipException("Too many entries for the zip file format's 16-bit entry count");
        }
        else {
            this.nameBytes = ze.getName().getBytes(Charset.forName("UTF-8"));
            this.nameLength = this.nameBytes.length;
            if (this.nameLength > 65535) {
                throw new IllegalArgumentException(new StringBuilder().append("Name too long: ").append(this.nameLength).append(" UTF-8 bytes").toString());
            }
            else {
                this.def.setLevel(this.compressionLevel);
                ze.setMethod(method);
                this.currentEntry = ze;
                this.entries.add(this.currentEntry.getName());
                int flags = method == 0 ? 8 : 0;
                flags |= 2048;
                this.writeLong(this.out, 67324752L);
                this.writeShort(this.out, 20);
                this.writeShort(this.out, flags);
                this.writeShort(this.out, method);
                if (-1L == this.currentEntry.getTime()) {
                    this.currentEntry.setTime(System.currentTimeMillis());
                }
                this.writeShort(this.out, 0);
                this.writeShort(this.out, 33);
                if (method == 0) {
                    this.writeLong(this.out, this.currentEntry.getCrc());
                    this.writeLong(this.out, this.currentEntry.getSize());
                    this.writeLong(this.out, this.currentEntry.getSize());
                }
                else {
                    this.writeLong(this.out, 0L);
                    this.writeLong(this.out, 0L);
                    this.writeLong(this.out, 0L);
                }
                this.writeShort(this.out, this.nameLength);
                int currDataOffset = this.offset + 30 + this.nameLength + this.currentEntry.getExtra() != null ? 0 : this.currentEntry.getExtra().length;
                this.padding = this.getPaddingByteCount(this.currentEntry, currDataOffset);
                if (this.currentEntry.getExtra() != null) {
                    this.writeShort(this.out, this.currentEntry.getExtra().length + this.padding);
                    goto 612;
                }
                else {
                    this.writeShort(this.out, this.padding);
                }
                this.out.write(this.nameBytes);
                if (this.currentEntry.getExtra() != null) {
                    this.out.write(this.currentEntry.getExtra());
                }
                this.makePaddingToStream(this.out, this.padding);
            }
        }
    }

    public void setComment(String comment) {
        if (comment == null) {
            this.commentBytes = null;
        }
        else {
            byte[] newCommentBytes = comment.getBytes(Charset.forName("UTF-8"));
            if (newCommentBytes.length > 65535) {
                throw new IllegalArgumentException(new StringBuilder().append("Comment too long: ").append(newCommentBytes.length).append(" bytes").toString());
            }
            else {
                this.commentBytes = newCommentBytes;
            }
        }
    }

    public void setLevel(int level) {
        if (level < -1 || level > 9) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad level: ").append(level).toString());
        }
        else {
            this.compressionLevel = level;
        }
    }

    public void setMethod(int method) {
        if (method != 0 && method != 8) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad method: ").append(method).toString());
        }
        else {
            this.defaultCompressionMethod = method;
        }
    }

    private long writeLong(OutputStream os, long i) {
        os.write((int)i & 255L);
        os.write((int)i >> 8 & 255);
        os.write((int)i >> 16 & 255);
        os.write((int)i >> 24 & 255);
        return i;
    }

    private int writeShort(OutputStream os, int i) {
        if (i > 65535) {
            throw new IllegalArgumentException(new StringBuilder().append("value ").append(i).append(" is too large for type 'short'.").toString());
        }
        else {
            os.write(i & 255);
            os.write(i >> 8 & 255);
            return i;
        }
    }

    public void write(int b) {
        AlignedZipOutputStream.ONE_ELEM_BYTE_ARRAY[0] = (byte)b & 255;
        this.write(AlignedZipOutputStream.ONE_ELEM_BYTE_ARRAY, 0, 1);
    }

    public void write(byte[] buffer, int offset, int byteCount) {
        this.checkOffsetAndCount(buffer.length, offset, byteCount);
        if (this.currentEntry == null) {
            throw new ZipException("No active entry");
        }
        else {
            if (this.currentEntry.getMethod() == 0) {
                this.out.write(buffer, offset, byteCount);
            }
            else {
                super.write(buffer, offset, byteCount);
            }
            this.crc.update(buffer, offset, byteCount);
            this.crcDataSize = this.crcDataSize + (long)byteCount;
        }
    }

    private void checkOffsetAndCount(int arrayLength, int offset, int count) {
        if (offset | count >= 0 || offset <= arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("length=").append(arrayLength).append("; regionStart=").append(offset).append("; regionLength=").append(count).toString());
        }
        else {
        }
    }

    private void checkOpen() {
        if (this.closed) {
            throw new IOException("Stream is closed");
        }
        else {
        }
    }

    static  {
        AlignedZipOutputStream.EMPTY_BYTE_ARRAY = new byte[]{};
        AlignedZipOutputStream.ONE_ELEM_BYTE_ARRAY = new byte[]{0};
    }

}
