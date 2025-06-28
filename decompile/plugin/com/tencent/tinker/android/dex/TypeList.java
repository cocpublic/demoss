/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/TypeList
public final class TypeList {
    final public static TypeList EMPTY;
    public short types;

    public TypeList(int off, short[] types) {
        super(off);
        this.types = types;
    }

    public int compareTo(TypeList other) {
        return CompareUtils.uArrCompare(this.types, other.types);
    }

    public int hashCode() {
        return Arrays.hashCode(this.types);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof TypeList)) {
            return false;
        }
        else if (this.compareTo((TypeList)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        return 4 + this.types.length * 2;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((TypeList)object);
    }

    static  {
        TypeList.EMPTY = new TypeList(0, Dex.EMPTY_SHORT_ARRAY);
    }

}
