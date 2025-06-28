/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;

import java.nio.ByteOrder;

// class: com/tencent/tinker/ziputils/ziputil/HeapBufferIterator
public final class HeapBufferIterator {
    final private byte buffer;
    final private int offset;
    final private int byteCount;
    final private ByteOrder order;
    private int position;

     HeapBufferIterator(byte[] buffer, int offset, int byteCount, ByteOrder order) {
        super();
        this.buffer = buffer;
        this.offset = offset;
        this.byteCount = byteCount;
        this.order = order;
    }

    public static BufferIterator iterator(byte[] buffer, int offset, int byteCount, ByteOrder order) {
        return new HeapBufferIterator(buffer, offset, byteCount, order);
    }

    public void seek(int offset) {
        this.position = offset;
    }

    public void skip(int byteCount) {
        this.position = this.position + byteCount;
    }

    public void readByteArray(byte[] dst, int dstOffset, int byteCount) {
        System.arraycopy(this.buffer, this.offset + this.position, dst, dstOffset, byteCount);
        this.position = this.position + byteCount;
    }

    public byte readByte() {
        byte result = this.buffer[this.offset + this.position];
        this.position = this.position + 1;
        return result;
    }

    public int readInt() {
        int result = Memory.peekInt(this.buffer, this.offset + this.position, this.order);
        this.position = this.position + 4;
        return result;
    }

    public short readShort() {
        short result = Memory.peekShort(this.buffer, this.offset + this.position, this.order);
        this.position = this.position + 2;
        return result;
    }

}
