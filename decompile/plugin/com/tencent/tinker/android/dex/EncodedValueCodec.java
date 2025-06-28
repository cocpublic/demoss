/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/EncodedValueCodec
public final class EncodedValueCodec {

    private EncodedValueCodec() {
        super();
    }

    public static void writeSignedIntegralValue(ByteOutput out, int type, long value) {
        int requiredBits = 65 - Long.numberOfLeadingZeros(value ^ value >> 63);
        out.writeByte(type | requiredBytes - 1 << 5);
        for (int requiredBytes = requiredBits + 7 >> 3; requiredBytes > 0; requiredBytes += 255) {
            out.writeByte();
            value >>= 8;
        }
    }

    public static void writeUnsignedIntegralValue(ByteOutput out, int type, long value) {
        int requiredBits = 64 - Long.numberOfLeadingZeros(value);
        if (requiredBits == 0) {
            requiredBits = 1;
        }
        out.writeByte(type | requiredBytes - 1 << 5);
        for (int requiredBytes = requiredBits + 7 >> 3; requiredBytes > 0; requiredBytes += 255) {
            out.writeByte();
            value >>= 8;
        }
    }

    public static void writeRightZeroExtendedValue(ByteOutput out, int type, long value) {
        int requiredBits = 64 - Long.numberOfTrailingZeros(value);
        if (requiredBits == 0) {
            requiredBits = 1;
        }
        value >>= 64 - requiredBytes * 8;
        out.writeByte(type | requiredBytes - 1 << 5);
        for (int requiredBytes = requiredBits + 7 >> 3; requiredBytes > 0; requiredBytes += 255) {
            out.writeByte();
            value >>= 8;
        }
    }

    public static int readSignedInt(ByteInput in, int zwidth) {
        int result = 0;
        while (true) {
            result = zwidth >= 0 ? result >> 3 - zwidth * 8 : result >>> 8 | in.readByte() & 255 << 24;
            return result;
        }
    }

    public static int readUnsignedInt(ByteInput in, int zwidth, boolean fillOnRight) {
        int result = 0;
        while (true) {
        }
        for (int i = fillOnRight ? zwidth : zwidth; i >= 0; i += 255) {
            result = result >>> 8 | in.readByte() & 255 << 24;
        }
        return result;
    }

    public static long readSignedLong(ByteInput in, int zwidth) {
        long result = 0L;
        while (true) {
            result = zwidth >= 0 ? result >> 7 - zwidth * 8 : result >>> 8 | (long)in.readByte() & 255L << 56;
            return result;
        }
    }

    public static long readUnsignedLong(ByteInput in, int zwidth, boolean fillOnRight) {
        long result = 0L;
        while (true) {
        }
        for (int i = fillOnRight ? zwidth : zwidth; i >= 0; i += 255) {
            result = result >>> 8 | (long)in.readByte() & 255L << 56;
        }
        return result;
    }

}
