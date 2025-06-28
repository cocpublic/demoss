/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/EncodedValue
public final class EncodedValue {
    public byte data;

    public EncodedValue(int off, byte[] data) {
        super(off);
        this.data = data;
    }

    public ByteInput asByteInput() {
        return new EncodedValue$1(this);
    }

    public int compareTo(EncodedValue other) {
        return CompareUtils.uArrCompare(this.data, other.data);
    }

    public int hashCode() {
        return Arrays.hashCode(this.data);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof EncodedValue)) {
            return false;
        }
        else if (this.compareTo((EncodedValue)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        return 1 * this.data.length;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((EncodedValue)object);
    }

}
