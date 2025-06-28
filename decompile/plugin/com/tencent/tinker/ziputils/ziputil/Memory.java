/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;

import java.nio.ByteOrder;

// class: com/tencent/tinker/ziputils/ziputil/Memory
public final class Memory {

    private Memory() {
        super();
    }

    public static int peekInt(byte[] src, int offset, ByteOrder order) {
        if (order == ByteOrder.BIG_ENDIAN) {
            offset += 1;
            offset += 1;
            offset += 1;
            return src[offset] & 255 << 24 | src[offset] & 255 << 16 | src[offset] & 255 << 8 | src[offset] & 255 << 0;
        }
        else {
            offset += 1;
            offset += 1;
            offset += 1;
            return src[offset] & 255 << 0 | src[offset] & 255 << 8 | src[offset] & 255 << 16 | src[offset] & 255 << 24;
        }
    }

    public static long peekLong(byte[] src, int offset, ByteOrder order) {
        int h;
        int l;
        if (order == ByteOrder.BIG_ENDIAN) {
            offset += 1;
            offset += 1;
            offset += 1;
            offset += 1;
            h = src[offset] & 255 << 24 | src[offset] & 255 << 16 | src[offset] & 255 << 8 | src[offset] & 255 << 0;
            offset += 1;
            offset += 1;
            offset += 1;
            l = src[offset] & 255 << 24 | src[offset] & 255 << 16 | src[offset] & 255 << 8 | src[offset] & 255 << 0;
            return (long)h << 32 | (long)l & 4294967295L;
        }
        else {
            offset += 1;
            offset += 1;
            offset += 1;
            offset += 1;
            l = src[offset] & 255 << 0 | src[offset] & 255 << 8 | src[offset] & 255 << 16 | src[offset] & 255 << 24;
            offset += 1;
            offset += 1;
            offset += 1;
            h = src[offset] & 255 << 0 | src[offset] & 255 << 8 | src[offset] & 255 << 16 | src[offset] & 255 << 24;
            return (long)h << 32 | (long)l & 4294967295L;
        }
    }

    public static short peekShort(byte[] src, int offset, ByteOrder order) {
        if (order == ByteOrder.BIG_ENDIAN) {
            return (short)src[offset] << 8 | src[offset + 1] & 255;
        }
        else {
            return (short)src[offset + 1] << 8 | src[offset] & 255;
        }
    }

    public static void pokeInt(byte[] dst, int offset, int value, ByteOrder order) {
        if (order == ByteOrder.BIG_ENDIAN) {
            offset += 1;
            dst[offset] = (byte)value >> 24 & 255;
            offset += 1;
            dst[offset] = (byte)value >> 16 & 255;
            offset += 1;
            dst[offset] = (byte)value >> 8 & 255;
            dst[offset] = (byte)value >> 0 & 255;
        }
        else {
            offset += 1;
            dst[offset] = (byte)value >> 0 & 255;
            offset += 1;
            dst[offset] = (byte)value >> 8 & 255;
            offset += 1;
            dst[offset] = (byte)value >> 16 & 255;
            dst[offset] = (byte)value >> 24 & 255;
        }
    }

    public static void pokeLong(byte[] dst, int offset, long value, ByteOrder value) {
        int i = order == ByteOrder.BIG_ENDIAN ? (int)value : (int)value >> 32;
        offset += 1;
        dst[offset] = (byte)i >> 0 & 255;
        offset += 1;
        dst[offset] = (byte)i >> 8 & 255;
        offset += 1;
        dst[offset] = (byte)i >> 16 & 255;
        offset += 1;
        dst[offset] = (byte)i >> 24 & 255;
        i = (int)value >> 32;
        offset += 1;
        dst[offset] = (byte)i >> 0 & 255;
        offset += 1;
        dst[offset] = (byte)i >> 8 & 255;
        offset += 1;
        dst[offset] = (byte)i >> 16 & 255;
        dst[offset] = (byte)i >> 24 & 255;
    }

    public static void pokeShort(byte[] dst, int offset, short value, ByteOrder order) {
        if (order == ByteOrder.BIG_ENDIAN) {
            offset += 1;
            dst[offset] = (byte)value >> 8 & 255;
            dst[offset] = (byte)value >> 0 & 255;
        }
        else {
            offset += 1;
            dst[offset] = (byte)value >> 0 & 255;
            dst[offset] = (byte)value >> 8 & 255;
        }
    }

}
