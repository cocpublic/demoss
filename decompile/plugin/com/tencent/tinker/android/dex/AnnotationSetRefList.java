/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/AnnotationSetRefList
public class AnnotationSetRefList {
    public int annotationSetRefItems;

    public AnnotationSetRefList(int off, int[] annotationSetRefItems) {
        super(off);
        this.annotationSetRefItems = annotationSetRefItems;
    }

    public int compareTo(AnnotationSetRefList other) {
        if (this.annotationSetRefItems.length != other.annotationSetRefItems.length) {
            return CompareUtils.uCompare(this.annotationSetRefItems.length, other.annotationSetRefItems.length);
        }
        else {
            for (int i = 0; i < this.annotationSetRefItems.length; i += 1) {
                if (this.annotationSetRefItems[i] != other.annotationSetRefItems[i]) {
                    return CompareUtils.uCompare(this.annotationSetRefItems[i], other.annotationSetRefItems[i]);
                }
                else {
                }
            }
            return 0;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(this.annotationSetRefItems);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof AnnotationSetRefList)) {
            return false;
        }
        else if (this.compareTo((AnnotationSetRefList)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        return 4 * 1 + this.annotationSetRefItems.length;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((AnnotationSetRefList)object);
    }

}
