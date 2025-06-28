/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/AnnotationSet
public class AnnotationSet {
    public int annotationOffsets;

    public AnnotationSet(int off, int[] annotationOffsets) {
        super(off);
        this.annotationOffsets = annotationOffsets;
    }

    public int compareTo(AnnotationSet other) {
        if (this.annotationOffsets.length != other.annotationOffsets.length) {
            return CompareUtils.uCompare(this.annotationOffsets.length, other.annotationOffsets.length);
        }
        else {
            for (int i = 0; i < this.annotationOffsets.length; i += 1) {
                if (this.annotationOffsets[i] != other.annotationOffsets[i]) {
                    return CompareUtils.uCompare(this.annotationOffsets[i], other.annotationOffsets[i]);
                }
                else {
                }
            }
            return 0;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(this.annotationOffsets);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof AnnotationSet)) {
            return false;
        }
        else if (this.compareTo((AnnotationSet)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        return 4 * 1 + this.annotationOffsets.length;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((AnnotationSet)object);
    }

}
