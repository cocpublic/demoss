/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dx/instruction;

import java.io.EOFException;

// class: com/tencent/tinker/android/dx/instruction/ShortArrayCodeInput
public final class ShortArrayCodeInput {
    final private short array;

    public ShortArrayCodeInput(short[] array) {
        super();
        if (array == null) {
            throw new NullPointerException("array == null");
        }
        else {
            this.array = array;
        }
    }

    public boolean hasMore() {
        if (this.cursor() < this.array.length) {
            return true;
        }
        else {
            return false;
        }
    }

    public int read() {
        try {
            short value = this.array[this.cursor()];
            this.advance(1);
            return value & 65535;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new EOFException();
        }
    }

    public int readInt() {
        int short0 = this.read();
        int short1 = this.read();
        return short0 | short1 << 16;
    }

    public long readLong() {
        long short0 = (long)this.read();
        long short1 = (long)this.read();
        long short2 = (long)this.read();
        long short3 = (long)this.read();
        return short0 | short1 << 16 | short2 << 32 | short3 << 48;
    }

}
