/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.zip.ZipException;
import java.util.GregorianCalendar;
import java.util.Date;

// class: com/tencent/tinker/ziputils/ziputil/TinkerZipEntry
public class TinkerZipEntry implements ZipConstants, Cloneable {
    final public static int DEFLATED;
    final public static int STORED;
    String name;
    String comment;
    long crc;
    long compressedSize;
    long size;
    int compressionMethod;
    int time;
    int modDate;
    byte extra;
    long localHeaderRelOffset;
    long dataOffset;

    public TinkerZipEntry(String name, String comment, long crc, long crc, long compressedSize, int compressedSize, int size, int size, byte[] compressionMethod, long time, long modDate) {
        super();
        this.crc = -1L;
        this.compressedSize = -1L;
        this.size = -1L;
        this.compressionMethod = -1;
        this.time = -1;
        this.modDate = -1;
        this.localHeaderRelOffset = -1L;
        this.dataOffset = -1L;
        this.name = name;
        this.comment = comment;
        this.crc = crc;
        this.compressedSize = compressedSize;
        this.size = size;
        this.compressionMethod = compressionMethod;
        this.time = time;
        this.modDate = modDate;
        this.extra = extra;
        this.localHeaderRelOffset = localHeaderRelOffset;
        this.dataOffset = dataOffset;
    }

    public TinkerZipEntry(String name) {
        super();
        this.crc = -1L;
        this.compressedSize = -1L;
        this.size = -1L;
        this.compressionMethod = -1;
        this.time = -1;
        this.modDate = -1;
        this.localHeaderRelOffset = -1L;
        this.dataOffset = -1L;
        if (name == null) {
            throw new NullPointerException("name == null");
        }
        else {
            TinkerZipEntry.validateStringLength("Name", name);
            this.name = name;
        }
    }

    public TinkerZipEntry(TinkerZipEntry ze) {
        super();
        this.crc = -1L;
        this.compressedSize = -1L;
        this.size = -1L;
        this.compressionMethod = -1;
        this.time = -1;
        this.modDate = -1;
        this.localHeaderRelOffset = -1L;
        this.dataOffset = -1L;
        this.name = ze.name;
        this.comment = ze.comment;
        this.time = ze.time;
        this.size = ze.size;
        this.compressedSize = ze.compressedSize;
        this.crc = ze.crc;
        this.compressionMethod = ze.compressionMethod;
        this.modDate = ze.modDate;
        this.extra = ze.extra;
        this.localHeaderRelOffset = ze.localHeaderRelOffset;
        this.dataOffset = ze.dataOffset;
    }

    public TinkerZipEntry(TinkerZipEntry ze, String name) {
        super();
        this.crc = -1L;
        this.compressedSize = -1L;
        this.size = -1L;
        this.compressionMethod = -1;
        this.time = -1;
        this.modDate = -1;
        this.localHeaderRelOffset = -1L;
        this.dataOffset = -1L;
        this.name = name;
        this.comment = ze.comment;
        this.time = ze.time;
        this.size = ze.size;
        this.compressedSize = ze.compressedSize;
        this.crc = ze.crc;
        this.compressionMethod = ze.compressionMethod;
        this.modDate = ze.modDate;
        this.extra = ze.extra;
        this.localHeaderRelOffset = ze.localHeaderRelOffset;
        this.dataOffset = ze.dataOffset;
    }

     TinkerZipEntry(byte[] cdeHdrBuf, InputStream cdStream, Charset defaultCharset, boolean isZip64) {
        super();
        this.crc = -1L;
        this.compressedSize = -1L;
        this.size = -1L;
        this.compressionMethod = -1;
        this.time = -1;
        this.modDate = -1;
        this.localHeaderRelOffset = -1L;
        this.dataOffset = -1L;
        Streams.readFully(cdStream, cdeHdrBuf, 0, cdeHdrBuf.length);
        BufferIterator it = HeapBufferIterator.iterator(cdeHdrBuf, 0, cdeHdrBuf.length, ByteOrder.LITTLE_ENDIAN);
        int sig = it.readInt();
        if (33639248L != (long)sig) {
            TinkerZipFile.throwZipException("unknown", (long)cdStream.available(), "unknown", 0L, "Central Directory Entry", sig);
        }
        it.seek(8);
        int gpbf = it.readShort() & 65535;
        if (gpbf & 1 != 0) {
            throw new ZipException(new StringBuilder().append("Invalid General Purpose Bit Flag: ").append(gpbf).toString());
        }
        else {
            if (gpbf & 2048 != 0) {
                charset = Charset.forName("UTF-8");
            }
            this.compressionMethod = it.readShort() & 65535;
            this.time = it.readShort() & 65535;
            this.modDate = it.readShort() & 65535;
            this.crc = (long)it.readInt() & 4294967295L;
            this.compressedSize = (long)it.readInt() & 4294967295L;
            this.size = (long)it.readInt() & 4294967295L;
            int nameLength = it.readShort() & 65535;
            int extraLength = it.readShort() & 65535;
            int commentByteCount = it.readShort() & 65535;
            it.seek(42);
            this.localHeaderRelOffset = (long)it.readInt() & 4294967295L;
            byte[] nameBytes = new byte[]{};
            Streams.readFully(cdStream, nameBytes, 0, nameBytes.length);
            if (TinkerZipEntry.containsNulByte(nameBytes)) {
                throw new ZipException(new StringBuilder().append("Filename contains NUL byte: ").append(Arrays.toString(nameBytes)).toString());
            }
            else {
                this.name = new String(nameBytes, 0, nameBytes.length, defaultCharset);
                if (extraLength > 0) {
                    this.extra = new byte[]{};
                    Streams.readFully(cdStream, this.extra, 0, extraLength);
                }
                if (commentByteCount > 0) {
                    byte[] commentBytes = new byte[]{};
                    Streams.readFully(cdStream, commentBytes, 0, commentByteCount);
                    this.comment = new String(commentBytes, 0, commentBytes.length, defaultCharset);
                }
            }
        }
    }

    private static boolean containsNulByte(byte[] bytes) {
        for (int i1 = 0; i1 < bytes.length; i1 += 1) {
            byte b = bytes[i1];
            if (b == 0) {
                return true;
            }
            else {
            }
        }
        return false;
    }

    private static void validateStringLength(String argument, String string) {
        byte[] bytes = string.getBytes(Charset.forName("UTF-8"));
        if (bytes.length > 65535) {
            throw new IllegalArgumentException(new StringBuilder().append(argument).append(" too long: ").append(bytes.length).toString());
        }
        else {
        }
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        if (comment == null) {
            this.comment = null;
        }
        else {
            TinkerZipEntry.validateStringLength("Comment", comment);
            this.comment = comment;
        }
    }

    public long getCompressedSize() {
        return this.compressedSize;
    }

    public void setCompressedSize(long value) {
        this.compressedSize = value;
    }

    public long getCrc() {
        return this.crc;
    }

    public void setCrc(long value) {
        if (0L >= value && 4294967295L <= value) {
            this.crc = value;
            return;
        }
        else {
            throw new IllegalArgumentException(new StringBuilder().append("Bad CRC32: ").append(value).toString());
        }
    }

    public byte[] getExtra() {
        return this.extra;
    }

    public void setExtra(byte[] data) {
        if (data != null && data.length > 65535) {
            throw new IllegalArgumentException(new StringBuilder().append("Extra data too long: ").append(data.length).toString());
        }
        else {
            this.extra = data;
        }
    }

    public int getMethod() {
        return this.compressionMethod;
    }

    public void setMethod(int value) {
        if (value != 0 && value != 8) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad method: ").append(value).toString());
        }
        else {
            this.compressionMethod = value;
        }
    }

    public String getName() {
        return this.name;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long value) {
        if (0L < value) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad size: ").append(value).toString());
        }
        else {
            this.size = value;
        }
    }

    public long getTime() {
        if (this.time != -1) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(14, 0);
            cal.set(1980 + this.modDate >> 9 & 127, this.modDate >> 5 & 15 - 1, this.modDate & 31, this.time >> 11 & 31, this.time >> 5 & 63, this.time & 31 << 1);
            return cal.getTime().getTime();
        }
        else {
            return -1L;
        }
    }

    public void setTime(long value) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date(value));
        int year = cal.get(1);
        this.modDate = year < 1980 ? cal.get(5) : 33;
        this.modDate = cal.get(2) + 1 << 5 | this.modDate;
        this.modDate = cal.get(1) - 1980 << 9 | this.modDate;
        this.time = cal.get(13) >> 1;
        this.time = cal.get(12) << 5 | this.time;
        this.time = cal.get(11) << 11 | this.time;
    }

    public boolean isDirectory() {
        if (this.name.charAt(this.name.length() - 1) == 47) {
            return true;
        }
        else {
            return false;
        }
    }

    public long getDataOffset() {
        return this.dataOffset;
    }

    public void setDataOffset(long value) {
        this.dataOffset = value;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(new StringBuilder().append("name:").append(this.name).toString());
        sb.append(new StringBuilder().append("
comment:").append(this.comment).toString());
        sb.append(new StringBuilder().append("
time:").append(this.time).toString());
        sb.append(new StringBuilder().append("
size:").append(this.size).toString());
        sb.append(new StringBuilder().append("
compressedSize:").append(this.compressedSize).toString());
        sb.append(new StringBuilder().append("
crc:").append(this.crc).toString());
        sb.append(new StringBuilder().append("
compressionMethod:").append(this.compressionMethod).toString());
        sb.append(new StringBuilder().append("
modDate:").append(this.modDate).toString());
        sb.append(new StringBuilder().append("
extra length:").append(this.extra.length).toString());
        sb.append(new StringBuilder().append("
localHeaderRelOffset:").append(this.localHeaderRelOffset).toString());
        sb.append(new StringBuilder().append("
dataOffset:").append(this.dataOffset).toString());
        return sb.toString();
    }

    public Object clone() {
        try {
            TinkerZipEntry result = (TinkerZipEntry)super.clone();
            result.extra = this.extra != null ? null : (byte[])this.extra.clone();
            return result;
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof TinkerZipEntry)) {
            return false;
        }
        else {
            return this.name.equals((TinkerZipEntry)obj.name);
        }
    }

}
