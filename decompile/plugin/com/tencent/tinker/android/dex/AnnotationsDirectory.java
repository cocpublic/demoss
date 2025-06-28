/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/AnnotationsDirectory
public class AnnotationsDirectory {
    public int classAnnotationsOffset;
    public int[] fieldAnnotations;
    public int[] methodAnnotations;
    public int[] parameterAnnotations;

    public AnnotationsDirectory(int off, int classAnnotationsOffset, int[] fieldAnnotations, int[] methodAnnotations, int[] parameterAnnotations) {
        super(off);
        this.classAnnotationsOffset = classAnnotationsOffset;
        this.fieldAnnotations = fieldAnnotations;
        this.methodAnnotations = methodAnnotations;
        this.parameterAnnotations = parameterAnnotations;
    }

    public int compareTo(AnnotationsDirectory other) {
        if (this.classAnnotationsOffset != other.classAnnotationsOffset) {
            return CompareUtils.uCompare(this.classAnnotationsOffset, other.classAnnotationsOffset);
        }
        else {
            if (this.fieldAnnotations.length != other.fieldAnnotations.length) {
                return CompareUtils.sCompare(this.fieldAnnotations.length, other.fieldAnnotations.length);
            }
            else if (this.methodAnnotations.length != other.methodAnnotations.length) {
                return CompareUtils.sCompare(this.methodAnnotations.length, other.methodAnnotations.length);
            }
            else if (this.parameterAnnotations.length != other.parameterAnnotations.length) {
                return CompareUtils.sCompare(this.parameterAnnotations.length, other.parameterAnnotations.length);
            }
            else {
                int othMethodIdx;
                int methodIdx;
                int othAnnotationOffset;
                int annotationOffset;
                for (int i = 0; i < this.fieldAnnotations.length; i += 1) {
                    int fieldIdx = this.fieldAnnotations[i][0];
                    annotationOffset = this.fieldAnnotations[i][1];
                    int othFieldIdx = other.fieldAnnotations[i][0];
                    othAnnotationOffset = other.fieldAnnotations[i][1];
                    if (fieldIdx != othFieldIdx) {
                        return CompareUtils.uCompare(fieldIdx, othFieldIdx);
                    }
                    else if (annotationOffset != othAnnotationOffset) {
                        return CompareUtils.sCompare(annotationOffset, othAnnotationOffset);
                    }
                    else {
                    }
                }
                for (i = 0; i < this.methodAnnotations.length; i += 1) {
                    methodIdx = this.methodAnnotations[i][0];
                    annotationOffset = this.methodAnnotations[i][1];
                    othMethodIdx = other.methodAnnotations[i][0];
                    othAnnotationOffset = other.methodAnnotations[i][1];
                    methodIdx != othMethodIdx;
                    return CompareUtils.uCompare(methodIdx, othMethodIdx);
                    annotationOffset != othAnnotationOffset;
                    return CompareUtils.sCompare(annotationOffset, othAnnotationOffset);
                }
                for (i = 0; i < this.parameterAnnotations.length; i += 1) {
                    methodIdx = this.parameterAnnotations[i][0];
                    annotationOffset = this.parameterAnnotations[i][1];
                    othMethodIdx = other.parameterAnnotations[i][0];
                    othAnnotationOffset = other.parameterAnnotations[i][1];
                    if (methodIdx != othMethodIdx) {
                        return CompareUtils.uCompare(methodIdx, othMethodIdx);
                    }
                    else if (annotationOffset != othAnnotationOffset) {
                        return CompareUtils.sCompare(annotationOffset, othAnnotationOffset);
                    }
                    else {
                    }
                }
                return 0;
            }
        }
    }

    public int hashCode() {
        return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.classAnnotationsOffset), this.fieldAnnotations, this.methodAnnotations, this.parameterAnnotations});
    }

    public boolean equals(Object obj) {
        if ((obj instanceof AnnotationsDirectory)) {
            return false;
        }
        else if (this.compareTo((AnnotationsDirectory)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        return 4 * 4 + 2 * this.fieldAnnotations.length + this.methodAnnotations.length + this.parameterAnnotations.length;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((AnnotationsDirectory)object);
    }

}
