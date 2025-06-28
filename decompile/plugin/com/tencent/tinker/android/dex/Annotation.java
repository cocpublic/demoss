/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/Annotation
public final class Annotation {
    public byte visibility;
    public EncodedValue encodedAnnotation;

    public Annotation(int off, byte visibility, EncodedValue encodedAnnotation) {
        super(off);
        this.visibility = visibility;
        this.encodedAnnotation = encodedAnnotation;
    }

    public EncodedValueReader getReader() {
        return new EncodedValueReader(this.encodedAnnotation, 29);
    }

    public int getTypeIndex() {
        EncodedValueReader reader = this.getReader();
        reader.readAnnotation();
        return reader.getAnnotationType();
    }

    public int compareTo(Annotation other) {
        int cmpRes = this.encodedAnnotation.compareTo(other.encodedAnnotation);
        if (cmpRes != 0) {
            return cmpRes;
        }
        else {
            return CompareUtils.uCompare(this.visibility, other.visibility);
        }
    }

    public int hashCode() {
        return HashCodeHelper.hash(new Object[]{Byte.valueOf(this.visibility), this.encodedAnnotation});
    }

    public boolean equals(Object obj) {
        if ((obj instanceof Annotation)) {
            return false;
        }
        else if (this.compareTo((Annotation)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        return 1 + this.encodedAnnotation.byteCountInDex();
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((Annotation)object);
    }

}
