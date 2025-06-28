/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/MethodId
public final class MethodId {
    public int declaringClassIndex;
    public int protoIndex;
    public int nameIndex;

    public MethodId(int off, int declaringClassIndex, int protoIndex, int nameIndex) {
        super(off);
        this.declaringClassIndex = declaringClassIndex;
        this.protoIndex = protoIndex;
        this.nameIndex = nameIndex;
    }

    public int compareTo(MethodId other) {
        if (this.declaringClassIndex != other.declaringClassIndex) {
            return CompareUtils.uCompare(this.declaringClassIndex, other.declaringClassIndex);
        }
        else if (this.nameIndex != other.nameIndex) {
            return CompareUtils.uCompare(this.nameIndex, other.nameIndex);
        }
        else {
            return CompareUtils.uCompare(this.protoIndex, other.protoIndex);
        }
    }

    public int hashCode() {
        return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.declaringClassIndex), Integer.valueOf(this.protoIndex), Integer.valueOf(this.nameIndex)});
    }

    public boolean equals(Object obj) {
        if ((obj instanceof MethodId)) {
            return false;
        }
        else if (this.compareTo((MethodId)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        return 8;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((MethodId)object);
    }

}
