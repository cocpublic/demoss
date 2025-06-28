/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/ClassDef
public final class ClassDef {
    final public static int NO_INDEX;
    final public static int NO_OFFSET;
    public int typeIndex;
    public int accessFlags;
    public int supertypeIndex;
    public int interfacesOffset;
    public int sourceFileIndex;
    public int annotationsOffset;
    public int classDataOffset;
    public int staticValuesOffset;

    public ClassDef(int off, int typeIndex, int accessFlags, int supertypeIndex, int interfacesOffset, int sourceFileIndex, int annotationsOffset, int classDataOffset, int staticValuesOffset) {
        super(off);
        this.typeIndex = typeIndex;
        this.accessFlags = accessFlags;
        this.supertypeIndex = supertypeIndex;
        this.interfacesOffset = interfacesOffset;
        this.sourceFileIndex = sourceFileIndex;
        this.annotationsOffset = annotationsOffset;
        this.classDataOffset = classDataOffset;
        this.staticValuesOffset = staticValuesOffset;
    }

    public int compareTo(ClassDef other) {
        int res = CompareUtils.uCompare(this.typeIndex, other.typeIndex);
        if (res != 0) {
            return res;
        }
        else {
            res = CompareUtils.sCompare(this.accessFlags, other.accessFlags);
            if (res != 0) {
                return res;
            }
            else {
                res = CompareUtils.uCompare(this.supertypeIndex, other.supertypeIndex);
                if (res != 0) {
                    return res;
                }
                else {
                    res = CompareUtils.sCompare(this.interfacesOffset, other.interfacesOffset);
                    if (res != 0) {
                        return res;
                    }
                    else {
                        res = CompareUtils.uCompare(this.sourceFileIndex, other.sourceFileIndex);
                        if (res != 0) {
                            return res;
                        }
                        else {
                            res = CompareUtils.sCompare(this.annotationsOffset, other.annotationsOffset);
                            if (res != 0) {
                                return res;
                            }
                            else {
                                res = CompareUtils.sCompare(this.classDataOffset, other.classDataOffset);
                                if (res != 0) {
                                    return res;
                                }
                                else {
                                    return CompareUtils.sCompare(this.staticValuesOffset, other.staticValuesOffset);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public int hashCode() {
        return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.typeIndex), Integer.valueOf(this.accessFlags), Integer.valueOf(this.supertypeIndex), Integer.valueOf(this.interfacesOffset), Integer.valueOf(this.sourceFileIndex), Integer.valueOf(this.annotationsOffset), Integer.valueOf(this.classDataOffset), Integer.valueOf(this.staticValuesOffset)});
    }

    public boolean equals(Object obj) {
        if ((obj instanceof ClassDef)) {
            return false;
        }
        else if (this.compareTo((ClassDef)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        return 32;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((ClassDef)object);
    }

}
