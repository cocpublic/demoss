/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/util;

import com.tencent.tinker.android.utils.SparseIntArray;
import com.tencent.tinker.android.utils.SparseBoolArray;

// class: com/tencent/tinker/commons/dexpatcher/util/SparseIndexMap
public class SparseIndexMap {
    final private SparseIntArray stringIdsMap;
    final private SparseIntArray typeIdsMap;
    final private SparseIntArray protoIdsMap;
    final private SparseIntArray fieldIdsMap;
    final private SparseIntArray methodIdsMap;
    final private SparseIntArray callSiteIdsMap;
    final public SparseIntArray methodHandleIdsMap;
    final private SparseIntArray typeListOffsetsMap;
    final private SparseIntArray annotationOffsetsMap;
    final private SparseIntArray annotationSetOffsetsMap;
    final private SparseIntArray annotationSetRefListOffsetsMap;
    final private SparseIntArray annotationsDirectoryOffsetsMap;
    final private SparseIntArray staticValuesOffsetsMap;
    final private SparseIntArray classDataOffsetsMap;
    final private SparseIntArray debugInfoItemOffsetsMap;
    final private SparseIntArray codeOffsetsMap;
    final private SparseBoolArray deletedStringIds;
    final private SparseBoolArray deletedTypeIds;
    final private SparseBoolArray deletedProtoIds;
    final private SparseBoolArray deletedFieldIds;
    final private SparseBoolArray deletedMethodIds;
    final private SparseBoolArray deletedCallSiteIds;
    final private SparseBoolArray deletedMethodHandleIds;
    final private SparseBoolArray deletedTypeListOffsets;
    final private SparseBoolArray deletedAnnotationOffsets;
    final private SparseBoolArray deletedAnnotationSetOffsets;
    final private SparseBoolArray deletedAnnotationSetRefListOffsets;
    final private SparseBoolArray deletedAnnotationsDirectoryOffsets;
    final private SparseBoolArray deletedStaticValuesOffsets;
    final private SparseBoolArray deletedClassDataOffsets;
    final private SparseBoolArray deletedDebugInfoItemOffsets;
    final private SparseBoolArray deletedCodeOffsets;

    public SparseIndexMap() {
        super();
        this.stringIdsMap = new SparseIntArray();
        this.typeIdsMap = new SparseIntArray();
        this.protoIdsMap = new SparseIntArray();
        this.fieldIdsMap = new SparseIntArray();
        this.methodIdsMap = new SparseIntArray();
        this.callSiteIdsMap = new SparseIntArray();
        this.methodHandleIdsMap = new SparseIntArray();
        this.typeListOffsetsMap = new SparseIntArray();
        this.annotationOffsetsMap = new SparseIntArray();
        this.annotationSetOffsetsMap = new SparseIntArray();
        this.annotationSetRefListOffsetsMap = new SparseIntArray();
        this.annotationsDirectoryOffsetsMap = new SparseIntArray();
        this.staticValuesOffsetsMap = new SparseIntArray();
        this.classDataOffsetsMap = new SparseIntArray();
        this.debugInfoItemOffsetsMap = new SparseIntArray();
        this.codeOffsetsMap = new SparseIntArray();
        this.deletedStringIds = new SparseBoolArray();
        this.deletedTypeIds = new SparseBoolArray();
        this.deletedProtoIds = new SparseBoolArray();
        this.deletedFieldIds = new SparseBoolArray();
        this.deletedMethodIds = new SparseBoolArray();
        this.deletedCallSiteIds = new SparseBoolArray();
        this.deletedMethodHandleIds = new SparseBoolArray();
        this.deletedTypeListOffsets = new SparseBoolArray();
        this.deletedAnnotationOffsets = new SparseBoolArray();
        this.deletedAnnotationSetOffsets = new SparseBoolArray();
        this.deletedAnnotationSetRefListOffsets = new SparseBoolArray();
        this.deletedAnnotationsDirectoryOffsets = new SparseBoolArray();
        this.deletedStaticValuesOffsets = new SparseBoolArray();
        this.deletedClassDataOffsets = new SparseBoolArray();
        this.deletedDebugInfoItemOffsets = new SparseBoolArray();
        this.deletedCodeOffsets = new SparseBoolArray();
    }

    public void mapStringIds(int oldIndex, int newIndex) {
        this.stringIdsMap.put(oldIndex, newIndex);
    }

    public void markStringIdDeleted(int index) {
        if (index < 0) {
        }
        else {
            this.deletedStringIds.put(index, 1);
        }
    }

    public void mapTypeIds(int oldIndex, int newIndex) {
        this.typeIdsMap.put(oldIndex, newIndex);
    }

    public void markTypeIdDeleted(int index) {
        if (index < 0) {
        }
        else {
            this.deletedTypeIds.put(index, 1);
        }
    }

    public void mapProtoIds(int oldIndex, int newIndex) {
        this.protoIdsMap.put(oldIndex, newIndex);
    }

    public void markProtoIdDeleted(int index) {
        if (index < 0) {
        }
        else {
            this.deletedProtoIds.put(index, 1);
        }
    }

    public void mapFieldIds(int oldIndex, int newIndex) {
        this.fieldIdsMap.put(oldIndex, newIndex);
    }

    public void markFieldIdDeleted(int index) {
        if (index < 0) {
        }
        else {
            this.deletedFieldIds.put(index, 1);
        }
    }

    public void mapMethodIds(int oldIndex, int newIndex) {
        this.methodIdsMap.put(oldIndex, newIndex);
    }

    public void markMethodIdDeleted(int index) {
        if (index < 0) {
        }
        else {
            this.deletedMethodIds.put(index, 1);
        }
    }

    public void mapCallsiteIds(int oldIndex, int newIndex) {
        this.callSiteIdsMap.put(oldIndex, newIndex);
    }

    public void markCallsiteDeleted(int index) {
        if (index < 0) {
        }
        else {
            this.deletedCallSiteIds.put(index, 1);
        }
    }

    public void mapMethodHandleIds(int oldIndex, int newIndex) {
        this.methodHandleIdsMap.put(oldIndex, newIndex);
    }

    public void markMethodHandleDeleted(int index) {
        if (index < 0) {
        }
        else {
            this.deletedMethodHandleIds.put(index, 1);
        }
    }

    public void mapTypeListOffset(int oldOffset, int newOffset) {
        this.typeListOffsetsMap.put(oldOffset, newOffset);
    }

    public void markTypeListDeleted(int offset) {
        if (offset < 0) {
        }
        else {
            this.deletedTypeListOffsets.put(offset, 1);
        }
    }

    public void mapAnnotationOffset(int oldOffset, int newOffset) {
        this.annotationOffsetsMap.put(oldOffset, newOffset);
    }

    public void markAnnotationDeleted(int offset) {
        if (offset < 0) {
        }
        else {
            this.deletedAnnotationOffsets.put(offset, 1);
        }
    }

    public void mapAnnotationSetOffset(int oldOffset, int newOffset) {
        this.annotationSetOffsetsMap.put(oldOffset, newOffset);
    }

    public void markAnnotationSetDeleted(int offset) {
        if (offset < 0) {
        }
        else {
            this.deletedAnnotationSetOffsets.put(offset, 1);
        }
    }

    public void mapAnnotationSetRefListOffset(int oldOffset, int newOffset) {
        this.annotationSetRefListOffsetsMap.put(oldOffset, newOffset);
    }

    public void markAnnotationSetRefListDeleted(int offset) {
        if (offset < 0) {
        }
        else {
            this.deletedAnnotationSetRefListOffsets.put(offset, 1);
        }
    }

    public void mapAnnotationsDirectoryOffset(int oldOffset, int newOffset) {
        this.annotationsDirectoryOffsetsMap.put(oldOffset, newOffset);
    }

    public void markAnnotationsDirectoryDeleted(int offset) {
        if (offset < 0) {
        }
        else {
            this.deletedAnnotationsDirectoryOffsets.put(offset, 1);
        }
    }

    public void mapStaticValuesOffset(int oldOffset, int newOffset) {
        this.staticValuesOffsetsMap.put(oldOffset, newOffset);
    }

    public void markStaticValuesDeleted(int offset) {
        if (offset < 0) {
        }
        else {
            this.deletedStaticValuesOffsets.put(offset, 1);
        }
    }

    public void mapClassDataOffset(int oldOffset, int newOffset) {
        this.classDataOffsetsMap.put(oldOffset, newOffset);
    }

    public void markClassDataDeleted(int offset) {
        if (offset < 0) {
        }
        else {
            this.deletedClassDataOffsets.put(offset, 1);
        }
    }

    public void mapDebugInfoItemOffset(int oldOffset, int newOffset) {
        this.debugInfoItemOffsetsMap.put(oldOffset, newOffset);
    }

    public void markDebugInfoItemDeleted(int offset) {
        if (offset < 0) {
        }
        else {
            this.deletedDebugInfoItemOffsets.put(offset, 1);
        }
    }

    public void mapCodeOffset(int oldOffset, int newOffset) {
        this.codeOffsetsMap.put(oldOffset, newOffset);
    }

    public void markCodeDeleted(int offset) {
        if (offset < 0) {
        }
        else {
            this.deletedCodeOffsets.put(offset, 1);
        }
    }

    public int adjustStringIndex(int stringIndex) {
        int index = this.stringIdsMap.indexOfKey(stringIndex);
        if (index < 0) {
            if (stringIndex >= 0 && this.deletedStringIds.containsKey(stringIndex)) {
                return -1;
            }
            else {
                return stringIndex;
            }
        }
        else {
            return this.stringIdsMap.valueAt(index);
        }
    }

    public int adjustTypeIdIndex(int typeIdIndex) {
        int index = this.typeIdsMap.indexOfKey(typeIdIndex);
        if (index < 0) {
            if (typeIdIndex >= 0 && this.deletedTypeIds.containsKey(typeIdIndex)) {
                return -1;
            }
            else {
                return typeIdIndex;
            }
        }
        else {
            return this.typeIdsMap.valueAt(index);
        }
    }

    public int adjustProtoIdIndex(int protoIndex) {
        int index = this.protoIdsMap.indexOfKey(protoIndex);
        if (index < 0) {
            if (protoIndex >= 0 && this.deletedProtoIds.containsKey(protoIndex)) {
                return -1;
            }
            else {
                return protoIndex;
            }
        }
        else {
            return this.protoIdsMap.valueAt(index);
        }
    }

    public int adjustFieldIdIndex(int fieldIndex) {
        int index = this.fieldIdsMap.indexOfKey(fieldIndex);
        if (index < 0) {
            if (fieldIndex >= 0 && this.deletedFieldIds.containsKey(fieldIndex)) {
                return -1;
            }
            else {
                return fieldIndex;
            }
        }
        else {
            return this.fieldIdsMap.valueAt(index);
        }
    }

    public int adjustMethodIdIndex(int methodIndex) {
        int index = this.methodIdsMap.indexOfKey(methodIndex);
        if (index < 0) {
            if (methodIndex >= 0 && this.deletedMethodIds.containsKey(methodIndex)) {
                return -1;
            }
            else {
                return methodIndex;
            }
        }
        else {
            return this.methodIdsMap.valueAt(index);
        }
    }

    public int adjustCallSiteIdIndex(int callsiteIdIndex) {
        int index = this.callSiteIdsMap.indexOfKey(callsiteIdIndex);
        if (index < 0) {
            if (callsiteIdIndex >= 0 && this.deletedCallSiteIds.containsKey(callsiteIdIndex)) {
                return -1;
            }
            else {
                return callsiteIdIndex;
            }
        }
        else {
            return this.callSiteIdsMap.valueAt(index);
        }
    }

    public int adjustMethodHandleIndex(int methodHandleIndex) {
        int index = this.methodHandleIdsMap.indexOfKey(methodHandleIndex);
        if (index < 0) {
            if (methodHandleIndex >= 0 && this.deletedMethodHandleIds.containsKey(methodHandleIndex)) {
                return -1;
            }
            else {
                return methodHandleIndex;
            }
        }
        else {
            return this.methodHandleIdsMap.valueAt(index);
        }
    }

    public int adjustTypeListOffset(int typeListOffset) {
        int index = this.typeListOffsetsMap.indexOfKey(typeListOffset);
        if (index < 0) {
            if (typeListOffset >= 0 && this.deletedTypeListOffsets.containsKey(typeListOffset)) {
                return -1;
            }
            else {
                return typeListOffset;
            }
        }
        else {
            return this.typeListOffsetsMap.valueAt(index);
        }
    }

    public int adjustAnnotationOffset(int annotationOffset) {
        int index = this.annotationOffsetsMap.indexOfKey(annotationOffset);
        if (index < 0) {
            if (annotationOffset >= 0 && this.deletedAnnotationOffsets.containsKey(annotationOffset)) {
                return -1;
            }
            else {
                return annotationOffset;
            }
        }
        else {
            return this.annotationOffsetsMap.valueAt(index);
        }
    }

    public int adjustAnnotationSetOffset(int annotationSetOffset) {
        int index = this.annotationSetOffsetsMap.indexOfKey(annotationSetOffset);
        if (index < 0) {
            if (annotationSetOffset >= 0 && this.deletedAnnotationSetOffsets.containsKey(annotationSetOffset)) {
                return -1;
            }
            else {
                return annotationSetOffset;
            }
        }
        else {
            return this.annotationSetOffsetsMap.valueAt(index);
        }
    }

    public int adjustAnnotationSetRefListOffset(int annotationSetRefListOffset) {
        int index = this.annotationSetRefListOffsetsMap.indexOfKey(annotationSetRefListOffset);
        if (index < 0) {
            if (annotationSetRefListOffset >= 0 && this.deletedAnnotationSetRefListOffsets.containsKey(annotationSetRefListOffset)) {
                return -1;
            }
            else {
                return annotationSetRefListOffset;
            }
        }
        else {
            return this.annotationSetRefListOffsetsMap.valueAt(index);
        }
    }

    public int adjustAnnotationsDirectoryOffset(int annotationsDirectoryOffset) {
        int index = this.annotationsDirectoryOffsetsMap.indexOfKey(annotationsDirectoryOffset);
        if (index < 0) {
            if (annotationsDirectoryOffset >= 0 && this.deletedAnnotationsDirectoryOffsets.containsKey(annotationsDirectoryOffset)) {
                return -1;
            }
            else {
                return annotationsDirectoryOffset;
            }
        }
        else {
            return this.annotationsDirectoryOffsetsMap.valueAt(index);
        }
    }

    public int adjustStaticValuesOffset(int staticValuesOffset) {
        int index = this.staticValuesOffsetsMap.indexOfKey(staticValuesOffset);
        if (index < 0) {
            if (staticValuesOffset >= 0 && this.deletedStaticValuesOffsets.containsKey(staticValuesOffset)) {
                return -1;
            }
            else {
                return staticValuesOffset;
            }
        }
        else {
            return this.staticValuesOffsetsMap.valueAt(index);
        }
    }

    public int adjustClassDataOffset(int classDataOffset) {
        int index = this.classDataOffsetsMap.indexOfKey(classDataOffset);
        if (index < 0) {
            if (classDataOffset >= 0 && this.deletedClassDataOffsets.containsKey(classDataOffset)) {
                return -1;
            }
            else {
                return classDataOffset;
            }
        }
        else {
            return this.classDataOffsetsMap.valueAt(index);
        }
    }

    public int adjustDebugInfoItemOffset(int debugInfoItemOffset) {
        int index = this.debugInfoItemOffsetsMap.indexOfKey(debugInfoItemOffset);
        if (index < 0) {
            if (debugInfoItemOffset >= 0 && this.deletedDebugInfoItemOffsets.containsKey(debugInfoItemOffset)) {
                return -1;
            }
            else {
                return debugInfoItemOffset;
            }
        }
        else {
            return this.debugInfoItemOffsetsMap.valueAt(index);
        }
    }

    public int adjustCodeOffset(int codeOffset) {
        int index = this.codeOffsetsMap.indexOfKey(codeOffset);
        if (index < 0) {
            if (codeOffset >= 0 && this.deletedCodeOffsets.containsKey(codeOffset)) {
                return -1;
            }
            else {
                return codeOffset;
            }
        }
        else {
            return this.codeOffsetsMap.valueAt(index);
        }
    }

}
