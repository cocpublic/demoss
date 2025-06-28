/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/Leb128
public final class Leb128 {

    private Leb128() {
        super();
    }

    public static int unsignedLeb128Size(int value) {
        int count = 0;
        for (int remaining = value >>> 7; remaining != 0; count += 1) {
            remaining >>>= 7;
        }
        return count + 1;
    }

    public static int unsignedLeb128p1Size(int value) {
        return Leb128.unsignedLeb128Size(value + 1);
    }

    public static int signedLeb128Size(int value) {
        int remaining = value >> 7;
        int count = 0;
        int hasMore = 1;
        int end = value & -2147483648 == 0 ? -1 : 0;
        while (hasMore != 0) {
            hasMore = remaining != end || remaining & 1 != value >> 6 & 1 ? 0 : 1;
            value = remaining;
            remaining >>= 7;
            count += 1;
        }
        return count;
    }

    public static int readSignedLeb128(ByteInput in) {
        int result = 0;
        int count = 0;
        int signBits = -1;
        do {
            int cur = in.readByte() & 255;
            result |= cur & 127 << count * 7;
            signBits <<= 7;
            count += 1;
        } while(cur & 128 != 128 || count >= 5);
        if (cur & 128 == 128) {
            throw new DexException("invalid LEB128 sequence");
        }
        else {
            if (signBits >> 1 & result != 0) {
                result |= signBits;
            }
            return result;
        }
    }

    public static int readUnsignedLeb128(ByteInput in) {
        int result = 0;
        int count = 0;
        do {
            int cur = in.readByte() & 255;
            result |= cur & 127 << count * 7;
            count += 1;
        } while(cur & 128 != 128 || count >= 5);
        if (cur & 128 == 128) {
            throw new DexException("invalid LEB128 sequence");
        }
        else {
            return result;
        }
    }

    public static int readUnsignedLeb128p1(ByteInput in) {
        return Leb128.readUnsignedLeb128(in) - 1;
    }

    public static int writeUnsignedLeb128(ByteOutput out, int value) {
        int bytesWritten = 0;
        for (int remaining = value >>> 7; remaining != 0; remaining >>>= 7) {
            out.writeByte((byte)value & 127 | 128);
            bytesWritten += 1;
        }
        out.writeByte((byte)remaining & 127);
        bytesWritten += 1;
        return bytesWritten;
    }

    public static int writeUnsignedLeb128p1(ByteOutput out, int value) {
        return Leb128.writeUnsignedLeb128(out, value + 1);
    }

    public static int writeSignedLeb128(ByteOutput out, int value) {
        int remaining = value >> 7;
        int hasMore = 1;
        int end = value & -2147483648 == 0 ? -1 : 0;
        int bytesWritten = 0;
        while (hasMore != 0) {
            hasMore = remaining != end || remaining & 1 != value >> 6 & 1 ? 0 : 1;
            out.writeByte((byte)value & 127 | hasMore != 0 ? 0 : 128);
            bytesWritten += 1;
            value = remaining;
            remaining >>= 7;
        }
        return bytesWritten;
    }

}
