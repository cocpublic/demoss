/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dx/instruction;


// class: com/tencent/tinker/android/dx/instruction/ShortArrayCodeOutput
public final class ShortArrayCodeOutput {
    private short array;

    public ShortArrayCodeOutput(int initSize) {
        super();
        if (initSize < 0) {
            throw new IllegalArgumentException("initSize < 0");
        }
        else {
            this.array = new short[]{};
        }
    }

    public ShortArrayCodeOutput(short[] array) {
        super();
        if (array == null) {
            throw new IllegalArgumentException("array is null.");
        }
        else {
            this.array = array;
        }
    }

    public short[] getArray() {
        int cursor = this.cursor();
        if (cursor == this.array.length) {
            return this.array;
        }
        else {
            short[] result = new short[]{};
            System.arraycopy(this.array, 0, result, 0, cursor);
            return result;
        }
    }

    public void write(short codeUnit) {
        this.ensureArrayLength(1);
        this.array[this.cursor()] = codeUnit;
        this.advance(1);
    }

    public void write(short u0, short u1) {
        this.write(u0);
        this.write(u1);
    }

    public void write(short u0, short u1, short u2) {
        this.write(u0);
        this.write(u1);
        this.write(u2);
    }

    public void write(short u0, short u1, short u2, short u3) {
        this.write(u0);
        this.write(u1);
        this.write(u2);
        this.write(u3);
    }

    public void write(short u0, short u1, short u2, short u3, short u4) {
        this.write(u0);
        this.write(u1);
        this.write(u2);
        this.write(u3);
        this.write(u4);
    }

    public void writeInt(int value) {
        this.write((short)value);
        this.write((short)value >> 16);
    }

    public void writeLong(long value) {
        this.write();
        this.write();
        this.write();
        this.write();
    }

    public void write(byte[] data) {
        int value = 0;
        int even = 1;
        for (int i1 = 0; i1 < data.length; i1 += 1) {
            byte b = data[i1];
            value = even != 0 ? value | b << 8 : b & 255;
            this.write((short)value);
            even = 1;
        }
        if (even == 0) {
            this.write((short)value);
        }
    }

    public void write(short[] data) {
        for (int i1 = 0; i1 < data.length; i1 += 1) {
            short unit = data[i1];
            this.write(unit);
        }
    }

    public void write(int[] data) {
        for (int i1 = 0; i1 < data.length; i1 += 1) {
            int i = data[i1];
            this.writeInt(i);
        }
    }

    public void write(long[] data) {
        for (int i1 = 0; i1 < data.length; i1 += 1) {
            long l = data[i1];
            this.writeLong(l);
        }
    }

    private void ensureArrayLength(int shortCountToWrite) {
        int currPos = this.cursor();
        if (this.array.length - currPos < shortCountToWrite) {
            short[] newArray = new short[]{};
            System.arraycopy(this.array, 0, newArray, 0, currPos);
            this.array = newArray;
        }
    }

}
