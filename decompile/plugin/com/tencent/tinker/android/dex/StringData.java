/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/StringData
public class StringData {
    public String value;

    public StringData(int offset, String value) {
        super(offset);
        this.value = value;
    }

    public int compareTo(StringData other) {
        return this.value.compareTo(other.value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof StringData)) {
            return false;
        }
        else if (this.compareTo((StringData)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        try {
            return Leb128.unsignedLeb128Size(this.value.length()) + (int)Mutf8.countBytes(this.value, 0) + 1;
        }
        catch (UTFDataFormatException e) {
            throw new DexException(e);
        }
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((StringData)object);
    }

}
