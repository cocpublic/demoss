/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/ClassData
public final class ClassData {
    public ClassData$Field[] staticFields;
    public ClassData$Field[] instanceFields;
    public ClassData$Method[] directMethods;
    public ClassData$Method[] virtualMethods;

    public ClassData(int off, ClassData$Field[] staticFields, ClassData$Field[] instanceFields, ClassData$Method[] directMethods, ClassData$Method[] virtualMethods) {
        super(off);
        this.staticFields = staticFields;
        this.instanceFields = instanceFields;
        this.directMethods = directMethods;
        this.virtualMethods = virtualMethods;
    }

    public int compareTo(ClassData other) {
        int res = CompareUtils.aArrCompare(this.staticFields, other.staticFields);
        if (res != 0) {
            return res;
        }
        else {
            res = CompareUtils.aArrCompare(this.instanceFields, other.instanceFields);
            if (res != 0) {
                return res;
            }
            else {
                res = CompareUtils.aArrCompare(this.directMethods, other.directMethods);
                if (res != 0) {
                    return res;
                }
                else {
                    return CompareUtils.aArrCompare(this.virtualMethods, other.virtualMethods);
                }
            }
        }
    }

    public int hashCode() {
        return HashCodeHelper.hash(new Object[]{this.staticFields, this.instanceFields, this.directMethods, this.virtualMethods});
    }

    public boolean equals(Object obj) {
        if ((obj instanceof ClassData)) {
            return false;
        }
        else if (this.compareTo((ClassData)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        int res = Leb128.unsignedLeb128Size(this.staticFields.length);
        res += Leb128.unsignedLeb128Size(this.instanceFields.length);
        res += Leb128.unsignedLeb128Size(this.directMethods.length);
        res += Leb128.unsignedLeb128Size(this.virtualMethods.length);
        res += this.calcFieldsSize(this.staticFields);
        res += this.calcFieldsSize(this.instanceFields);
        res += this.calcMethodsSize(this.directMethods);
        res += this.calcMethodsSize(this.virtualMethods);
        return res;
    }

    private int calcFieldsSize(ClassData$Field[] fields) {
        int res = 0;
        int prevFieldIndex = 0;
        for (int i1 = 0; i1 < fields.length; i1 += 1) {
            ClassData$Field field = fields[i1];
            int fieldIndexDelta = field.fieldIndex - prevFieldIndex;
            prevFieldIndex = field.fieldIndex;
            res += Leb128.unsignedLeb128Size(fieldIndexDelta) + Leb128.unsignedLeb128Size(field.accessFlags);
        }
        return res;
    }

    private int calcMethodsSize(ClassData$Method[] methods) {
        int res = 0;
        int prevMethodIndex = 0;
        for (int i1 = 0; i1 < methods.length; i1 += 1) {
            ClassData$Method method = methods[i1];
            int methodIndexDelta = method.methodIndex - prevMethodIndex;
            prevMethodIndex = method.methodIndex;
            res += Leb128.unsignedLeb128Size(methodIndexDelta) + Leb128.unsignedLeb128Size(method.accessFlags) + Leb128.unsignedLeb128Size(method.codeOffset);
        }
        return res;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((ClassData)object);
    }

    // class: com/tencent/tinker/android/dex/ClassData$Field
    public class ClassData$Field implements Comparable<ClassData$Field> {
        public int fieldIndex;
        public int accessFlags;

        public ClassData$Field(int fieldIndex, int accessFlags) {
            super();
            this.fieldIndex = fieldIndex;
            this.accessFlags = accessFlags;
        }

        public int compareTo(ClassData$Field other) {
            int res = CompareUtils.uCompare(this.fieldIndex, other.fieldIndex);
            if (res != 0) {
                return res;
            }
            else {
                return CompareUtils.sCompare(this.accessFlags, other.accessFlags);
            }
        }

        public boolean equals(Object obj) {
            if ((obj instanceof ClassData$Field)) {
                return false;
            }
            else if (this.compareTo((ClassData$Field)obj) == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        public int hashCode() {
            return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.fieldIndex), Integer.valueOf(this.accessFlags)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.compareTo((ClassData$Field)object);
        }

    }
    // class: com/tencent/tinker/android/dex/ClassData$Field
    public class ClassData$Field implements Comparable<ClassData$Field> {
        public int fieldIndex;
        public int accessFlags;

        public ClassData$Field(int fieldIndex, int accessFlags) {
            super();
            this.fieldIndex = fieldIndex;
            this.accessFlags = accessFlags;
        }

        public int compareTo(ClassData$Field other) {
            int res = CompareUtils.uCompare(this.fieldIndex, other.fieldIndex);
            if (res != 0) {
                return res;
            }
            else {
                return CompareUtils.sCompare(this.accessFlags, other.accessFlags);
            }
        }

        public boolean equals(Object obj) {
            if ((obj instanceof ClassData$Field)) {
                return false;
            }
            else if (this.compareTo((ClassData$Field)obj) == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        public int hashCode() {
            return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.fieldIndex), Integer.valueOf(this.accessFlags)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.compareTo((ClassData$Field)object);
        }

    }
    // class: com/tencent/tinker/android/dex/ClassData$Method
    public class ClassData$Method implements Comparable<ClassData$Method> {
        public int methodIndex;
        public int accessFlags;
        public int codeOffset;

        public ClassData$Method(int methodIndex, int accessFlags, int codeOffset) {
            super();
            this.methodIndex = methodIndex;
            this.accessFlags = accessFlags;
            this.codeOffset = codeOffset;
        }

        public int compareTo(ClassData$Method other) {
            int res = CompareUtils.uCompare(this.methodIndex, other.methodIndex);
            if (res != 0) {
                return res;
            }
            else {
                res = CompareUtils.sCompare(this.accessFlags, other.accessFlags);
                if (res != 0) {
                    return res;
                }
                else {
                    return CompareUtils.sCompare(this.codeOffset, other.codeOffset);
                }
            }
        }

        public boolean equals(Object obj) {
            if ((obj instanceof ClassData$Method)) {
                return false;
            }
            else if (this.compareTo((ClassData$Method)obj) == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        public int hashCode() {
            return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.methodIndex), Integer.valueOf(this.accessFlags), Integer.valueOf(this.codeOffset)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.compareTo((ClassData$Method)object);
        }

    }
    // class: com/tencent/tinker/android/dex/ClassData$Method
    public class ClassData$Method implements Comparable<ClassData$Method> {
        public int methodIndex;
        public int accessFlags;
        public int codeOffset;

        public ClassData$Method(int methodIndex, int accessFlags, int codeOffset) {
            super();
            this.methodIndex = methodIndex;
            this.accessFlags = accessFlags;
            this.codeOffset = codeOffset;
        }

        public int compareTo(ClassData$Method other) {
            int res = CompareUtils.uCompare(this.methodIndex, other.methodIndex);
            if (res != 0) {
                return res;
            }
            else {
                res = CompareUtils.sCompare(this.accessFlags, other.accessFlags);
                if (res != 0) {
                    return res;
                }
                else {
                    return CompareUtils.sCompare(this.codeOffset, other.codeOffset);
                }
            }
        }

        public boolean equals(Object obj) {
            if ((obj instanceof ClassData$Method)) {
                return false;
            }
            else if (this.compareTo((ClassData$Method)obj) == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        public int hashCode() {
            return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.methodIndex), Integer.valueOf(this.accessFlags), Integer.valueOf(this.codeOffset)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.compareTo((ClassData$Method)object);
        }

    }
}
