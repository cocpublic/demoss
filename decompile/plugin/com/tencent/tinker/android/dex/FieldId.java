/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/FieldId
public final class FieldId {
    public int declaringClassIndex;
    public int typeIndex;
    public int nameIndex;

    public FieldId(int off, int declaringClassIndex, int typeIndex, int nameIndex) {
        super(off);
        this.declaringClassIndex = declaringClassIndex;
        this.typeIndex = typeIndex;
        this.nameIndex = nameIndex;
    }

    public int compareTo(FieldId other) {
        if (this.declaringClassIndex != other.declaringClassIndex) {
            return CompareUtils.uCompare(this.declaringClassIndex, other.declaringClassIndex);
        }
        else if (this.nameIndex != other.nameIndex) {
            return CompareUtils.uCompare(this.nameIndex, other.nameIndex);
        }
        else {
            return CompareUtils.uCompare(this.typeIndex, other.typeIndex);
        }
    }

    public int hashCode() {
        return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.declaringClassIndex), Integer.valueOf(this.typeIndex), Integer.valueOf(this.nameIndex)});
    }

    public boolean equals(Object obj) {
        if ((obj instanceof FieldId)) {
            return false;
        }
        else if (this.compareTo((FieldId)obj) == 0) {
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
        return this.compareTo((FieldId)object);
    }

}
