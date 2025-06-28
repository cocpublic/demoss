/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;


// class: com/tencent/tinker/ziputils/ziputil/BufferIterator
public abstract class BufferIterator {

    public BufferIterator() {
        super();
    }

    void seek(int p0);

    void skip(int p0);

    int readInt();

    short readShort();

}
