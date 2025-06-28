/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;

import java.util.HashSet;
import java.util.zip.ZipException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

// class: com/tencent/tinker/ziputils/ziputil/TinkerZipOutputStream
public class TinkerZipOutputStream implements ZipConstants {
    final public static int DEFLATED;
    final public static int STORED;
    final public static byte BYTE;
    final static int TIME_CONST;
    final static int MOD_DATE_CONST;
    final private static int ZIP_VERSION_2_0;
    final private static byte ZIP64_PLACEHOLDER_BYTES;
    final private HashSet<String> entries;
    final private boolean forceZip64;
    private byte commentBytes;
    private int defaultCompressionMethod;
    private ByteArrayOutputStream cDir;
    private TinkerZipEntry currentEntry;
    private long offset;
    private byte nameBytes;
    private byte entryCommentBytes;
    private boolean archiveNeedsZip64EocdRecord;
    private boolean currentEntryNeedsZip64;
    final private int alignBytes;
    private int padding;

    public TinkerZipOutputStream(OutputStream os) {
        super(os, 0);
    }

    public TinkerZipOutputStream(OutputStream os, boolean forceZip64) {
        super(os, forceZip64, 4);
    }

    public TinkerZipOutputStream(OutputStream os, boolean forceZip64, int alignBytes) {
        super(os);
        this.entries = new HashSet();
        this.commentBytes = TinkerZipOutputStream.BYTE;
        this.defaultCompressionMethod = 8;
        this.cDir = new ByteArrayOutputStream();
        this.offset = 0L;
        this.padding = 0;
        this.forceZip64 = forceZip64;
        this.alignBytes = alignBytes;
    }

    static long writeLongAsUint32(OutputStream os, long i) {
        os.write((int)i & 255L);
        os.write((int)i >> 8 & 255);
        os.write((int)i >> 16 & 255);
        os.write((int)i >> 24 & 255);
        return i;
    }

    static long writeLongAsUint64(OutputStream os, long i) {
        int i1 = (int)i;
        os.write(i1 & 255);
        os.write(i1 >> 8 & 255);
        os.write(i1 >> 16 & 255);
        os.write(i1 >> 24 & 255);
        int i2 = (int)i >> 32;
        os.write(i2 & 255);
        os.write(i2 >> 8 & 255);
        os.write(i2 >> 16 & 255);
        os.write(i2 >> 24 & 255);
        return i;
    }

    static int writeIntAsUint16(OutputStream os, int i) {
        os.write(i & 255);
        os.write(i >> 8 & 255);
        return i;
    }

    public void close() {
        if (this.out != null) {
            this.finish();
            this.out.close();
            this.out = null;
        }
    }

    public void closeEntry() {
        this.checkOpen();
        if (this.currentEntry == null) {
        }
        else {
            long curOffset = 30L;
            if (this.currentEntry.getMethod() != 0) {
                curOffset += 16L;
                TinkerZipOutputStream.writeLongAsUint32(this.out, 134695760L);
                TinkerZipOutputStream.writeLongAsUint32(this.out, this.currentEntry.crc);
                TinkerZipOutputStream.writeLongAsUint32(this.out, this.currentEntry.compressedSize);
                TinkerZipOutputStream.writeLongAsUint32(this.out, this.currentEntry.size);
            }
            int flags = this.currentEntry.getMethod() == 0 ? 8 : 0;
            flags |= 2048;
            TinkerZipOutputStream.writeLongAsUint32(this.cDir, 33639248L);
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, 20);
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, 20);
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, flags);
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, this.currentEntry.getMethod());
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, this.currentEntry.time);
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, this.currentEntry.modDate);
            TinkerZipOutputStream.writeLongAsUint32(this.cDir, this.currentEntry.crc);
            curOffset = this.currentEntry.getMethod() == 8 ? curOffset + this.currentEntry.getSize() : curOffset + this.currentEntry.getCompressedSize();
            TinkerZipOutputStream.writeLongAsUint32(this.cDir, this.currentEntry.getCompressedSize());
            TinkerZipOutputStream.writeLongAsUint32(this.cDir, this.currentEntry.getSize());
            curOffset += (long)TinkerZipOutputStream.writeIntAsUint16(this.cDir, this.nameBytes.length);
            if (this.currentEntry.extra != null) {
                curOffset += (long)TinkerZipOutputStream.writeIntAsUint16(this.cDir, this.currentEntry.extra.length);
                goto 333;
            }
            else {
                TinkerZipOutputStream.writeIntAsUint16(this.cDir, 0);
            }
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, this.entryCommentBytes.length);
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, 0);
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, 0);
            TinkerZipOutputStream.writeLongAsUint32(this.cDir, 0L);
            TinkerZipOutputStream.writeLongAsUint32(this.cDir, this.currentEntry.localHeaderRelOffset);
            this.cDir.write(this.nameBytes);
            this.nameBytes = null;
            if (this.currentEntry.extra != null) {
                this.cDir.write(this.currentEntry.extra);
            }
            this.offset = this.offset + curOffset + (long)this.padding;
            this.padding = 0;
            if (this.entryCommentBytes.length > 0) {
                this.cDir.write(this.entryCommentBytes);
                this.entryCommentBytes = TinkerZipOutputStream.BYTE;
            }
            this.currentEntry = null;
        }
    }

    public void finish() {
        if (this.out == null) {
            throw new IOException("Stream is closed");
        }
        else if (this.cDir == null) {
        }
        else if (this.entries.isEmpty()) {
            throw new ZipException("No entries");
        }
        else {
            if (this.currentEntry != null) {
                this.closeEntry();
            }
            int cdirEntriesSize = this.cDir.size();
            TinkerZipOutputStream.writeLongAsUint32(this.cDir, 101010256L);
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, 0);
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, 0);
            if (this.archiveNeedsZip64EocdRecord) {
                TinkerZipOutputStream.writeIntAsUint16(this.cDir, 65535);
                TinkerZipOutputStream.writeIntAsUint16(this.cDir, 65535);
                TinkerZipOutputStream.writeLongAsUint32(this.cDir, -1L);
                TinkerZipOutputStream.writeLongAsUint32(this.cDir, -1L);
                goto 203;
            }
            else {
                TinkerZipOutputStream.writeIntAsUint16(this.cDir, this.entries.size());
                TinkerZipOutputStream.writeIntAsUint16(this.cDir, this.entries.size());
                TinkerZipOutputStream.writeLongAsUint32(this.cDir, (long)cdirEntriesSize);
                TinkerZipOutputStream.writeLongAsUint32(this.cDir, this.offset + (long)this.padding);
            }
            TinkerZipOutputStream.writeIntAsUint16(this.cDir, this.commentBytes.length);
            if (this.commentBytes.length > 0) {
                this.cDir.write(this.commentBytes);
            }
            this.cDir.writeTo(this.out);
            this.cDir = null;
        }
    }

    private int getPaddingByteCount(TinkerZipEntry entry, long entryFileOffset) {
        if (entry.getMethod() != 0 || this.alignBytes == 0) {
            return 0;
        }
        else {
            return (int)(long)this.alignBytes - entryFileOffset % (long)this.alignBytes % (long)this.alignBytes;
        }
    }

    private void makePaddingToStream(OutputStream os, long padding) {
        if (0L <= padding) {
        }
        else {
            while (true) {
                padding -= 1L;
                if (0L > padding) {
                    os.write(0);
                }
                else {
                }
            }
        }
    }

    public void putNextEntry(TinkerZipEntry ze) {
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
            else if (ze.compressedSize != ze.size) {
                throw new ZipException("STORED entry size/compressed size mismatch");
            }
        }
        this.checkOpen();
        ze.comment = null;
        ze.extra = null;
        ze.time = 40691;
        ze.modDate = 18698;
        this.nameBytes = ze.name.getBytes(StandardCharsets.UTF_8);
        this.checkSizeIsWithinShort("Name", this.nameBytes);
        this.entryCommentBytes = TinkerZipOutputStream.BYTE;
        if (ze.comment != null) {
            this.entryCommentBytes = ze.comment.getBytes(StandardCharsets.UTF_8);
            this.checkSizeIsWithinShort("Comment", this.entryCommentBytes);
        }
        ze.setMethod(method);
        this.currentEntry = ze;
        this.currentEntry.localHeaderRelOffset = this.offset;
        this.entries.add(this.currentEntry.name);
        int flags = method == 0 ? 8 : 0;
        flags |= 2048;
        TinkerZipOutputStream.writeLongAsUint32(this.out, 67324752L);
        TinkerZipOutputStream.writeIntAsUint16(this.out, 20);
        TinkerZipOutputStream.writeIntAsUint16(this.out, flags);
        TinkerZipOutputStream.writeIntAsUint16(this.out, method);
        TinkerZipOutputStream.writeIntAsUint16(this.out, this.currentEntry.time);
        TinkerZipOutputStream.writeIntAsUint16(this.out, this.currentEntry.modDate);
        if (method == 0) {
            TinkerZipOutputStream.writeLongAsUint32(this.out, this.currentEntry.crc);
            TinkerZipOutputStream.writeLongAsUint32(this.out, this.currentEntry.size);
            TinkerZipOutputStream.writeLongAsUint32(this.out, this.currentEntry.size);
        }
        else {
            TinkerZipOutputStream.writeLongAsUint32(this.out, 0L);
            TinkerZipOutputStream.writeLongAsUint32(this.out, 0L);
            TinkerZipOutputStream.writeLongAsUint32(this.out, 0L);
        }
        TinkerZipOutputStream.writeIntAsUint16(this.out, this.nameBytes.length);
        long currDataOffset = this.offset + 30L + (long)this.nameBytes.length + (long)this.currentEntry.getExtra() != null ? 0 : this.currentEntry.getExtra().length;
        this.padding = this.getPaddingByteCount(this.currentEntry, currDataOffset);
        if (this.currentEntry.extra != null) {
            TinkerZipOutputStream.writeIntAsUint16(this.out, this.currentEntry.extra.length + this.padding);
            goto 540;
        }
        else {
            TinkerZipOutputStream.writeIntAsUint16(this.out, this.padding);
        }
        this.out.write(this.nameBytes);
        if (this.currentEntry.extra != null) {
            this.out.write(this.currentEntry.extra);
        }
        this.makePaddingToStream(this.out, (long)this.padding);
    }

    public void setComment(String comment) {
        if (comment == null) {
            this.commentBytes = TinkerZipOutputStream.BYTE;
        }
        else {
            byte[] newCommentBytes = comment.getBytes(StandardCharsets.UTF_8);
            this.checkSizeIsWithinShort("Comment", newCommentBytes);
            this.commentBytes = newCommentBytes;
        }
    }

    public void write(byte[] buffer, int offset, int byteCount) {
        Arrays.checkOffsetAndCount(buffer.length, offset, byteCount);
        if (this.currentEntry == null) {
            throw new ZipException("No active entry");
        }
        else if (this.currentEntry.getMethod() == 0) {
            this.out.write(buffer, offset, byteCount);
        }
        else {
            this.out.write(buffer, offset, byteCount);
        }
    }

    private void checkOpen() {
        if (this.cDir == null) {
            throw new IOException("Stream is closed");
        }
        else {
        }
    }

    private void checkSizeIsWithinShort(String property, byte[] bytes) {
        if (bytes.length > 65535) {
            throw new IllegalArgumentException(new StringBuilder().append(property).append(" too long in UTF-8:").append(bytes.length).append(" bytes").toString());
        }
        else {
        }
    }

    static  {
        TinkerZipOutputStream.BYTE = new byte[]{};
        TinkerZipOutputStream.ZIP64_PLACEHOLDER_BYTES = new byte[]{-1, -1, -1, -1};
    }

}
