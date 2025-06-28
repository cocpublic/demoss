/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/ProtoId
public final class ProtoId {
    public int shortyIndex;
    public int returnTypeIndex;
    public int parametersOffset;

    public ProtoId(int off, int shortyIndex, int returnTypeIndex, int parametersOffset) {
        super(off);
        this.shortyIndex = shortyIndex;
        this.returnTypeIndex = returnTypeIndex;
        this.parametersOffset = parametersOffset;
    }

    public int compareTo(ProtoId other) {
        int res = CompareUtils.uCompare(this.shortyIndex, other.shortyIndex);
        if (res != 0) {
            return res;
        }
        else {
            res = CompareUtils.uCompare(this.returnTypeIndex, other.returnTypeIndex);
            if (res != 0) {
                return res;
            }
            else {
                return CompareUtils.sCompare(this.parametersOffset, other.parametersOffset);
            }
        }
    }

    public int hashCode() {
        return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.shortyIndex), Integer.valueOf(this.returnTypeIndex), Integer.valueOf(this.parametersOffset)});
    }

    public boolean equals(Object obj) {
        if ((obj instanceof ProtoId)) {
            return false;
        }
        else if (this.compareTo((ProtoId)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        return 12;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((ProtoId)object);
    }

}
