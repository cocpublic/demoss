/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.android.dex.StringData;
import com.tencent.tinker.android.dex.TypeList;
import com.tencent.tinker.android.dex.ProtoId;
import com.tencent.tinker.android.dex.FieldId;
import com.tencent.tinker.android.dex.MethodId;
import com.tencent.tinker.android.dex.CallSiteId;
import com.tencent.tinker.android.dex.MethodHandle;
import com.tencent.tinker.android.dex.Annotation;
import com.tencent.tinker.android.dex.AnnotationSet;
import com.tencent.tinker.android.dex.AnnotationSetRefList;
import com.tencent.tinker.android.dex.AnnotationsDirectory;
import com.tencent.tinker.android.dex.DebugInfoItem;
import com.tencent.tinker.android.dex.Code;
import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.EncodedValue;
import com.tencent.tinker.commons.dexpatcher.DexPatcherLogger;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;
import com.tencent.tinker.commons.dexpatcher.struct.PatchOperation;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

// class: com/tencent/tinker/build/dexpatcher/DexPatchGenerator
public class DexPatchGenerator {
    final private static String TAG;
    final private Dex oldDex;
    final private Dex newDex;
    final private DexPatcherLogger logger;
    private DexSectionDiffAlgorithm<StringData> stringDataSectionDiffAlg;
    private DexSectionDiffAlgorithm<Integer> typeIdSectionDiffAlg;
    private DexSectionDiffAlgorithm<ProtoId> protoIdSectionDiffAlg;
    private DexSectionDiffAlgorithm<FieldId> fieldIdSectionDiffAlg;
    private DexSectionDiffAlgorithm<MethodId> methodIdSectionDiffAlg;
    private DexSectionDiffAlgorithm<CallSiteId> callsiteIdSectionDiffAlg;
    private DexSectionDiffAlgorithm<MethodHandle> methodHandleSectionDiffAlg;
    private DexSectionDiffAlgorithm<ClassDef> classDefSectionDiffAlg;
    private DexSectionDiffAlgorithm<TypeList> typeListSectionDiffAlg;
    private DexSectionDiffAlgorithm<AnnotationSetRefList> annotationSetRefListSectionDiffAlg;
    private DexSectionDiffAlgorithm<AnnotationSet> annotationSetSectionDiffAlg;
    private DexSectionDiffAlgorithm<ClassData> classDataSectionDiffAlg;
    private DexSectionDiffAlgorithm<Code> codeSectionDiffAlg;
    private DexSectionDiffAlgorithm<DebugInfoItem> debugInfoSectionDiffAlg;
    private DexSectionDiffAlgorithm<Annotation> annotationSectionDiffAlg;
    private DexSectionDiffAlgorithm<EncodedValue> encodedArraySectionDiffAlg;
    private DexSectionDiffAlgorithm<AnnotationsDirectory> annotationsDirectorySectionDiffAlg;
    private Set<String> additionalRemovingClassPatternSet;
    private int patchedHeaderOffset;
    private int patchedStringIdsOffset;
    private int patchedTypeIdsOffset;
    private int patchedProtoIdsOffset;
    private int patchedFieldIdsOffset;
    private int patchedMethodIdsOffset;
    private int patchedCallSiteIdsOffset;
    private int patchedMethodHandlesOffset;
    private int patchedClassDefsOffset;
    private int patchedTypeListsOffset;
    private int patchedAnnotationItemsOffset;
    private int patchedAnnotationSetItemsOffset;
    private int patchedAnnotationSetRefListItemsOffset;
    private int patchedAnnotationsDirectoryItemsOffset;
    private int patchedDebugInfoItemsOffset;
    private int patchedCodeItemsOffset;
    private int patchedClassDataItemsOffset;
    private int patchedStringDataItemsOffset;
    private int patchedEncodedArrayItemsOffset;
    private int patchedMapListOffset;
    private int patchedDexSize;

    public DexPatchGenerator(File oldDexFile, File newDexFile) {
        super(new Dex(oldDexFile), new Dex(newDexFile));
    }

    public DexPatchGenerator(File oldDexFile, InputStream newDexStream) {
        super(new Dex(oldDexFile), new Dex(newDexStream));
    }

    public DexPatchGenerator(InputStream oldDexStream, InputStream newDexStream) {
        super(new Dex(oldDexStream), new Dex(newDexStream));
    }

    public DexPatchGenerator(Dex oldDex, Dex newDex) {
        super();
        this.logger = new DexPatcherLogger();
        this.patchedHeaderOffset = 0;
        this.patchedStringIdsOffset = 0;
        this.patchedTypeIdsOffset = 0;
        this.patchedProtoIdsOffset = 0;
        this.patchedFieldIdsOffset = 0;
        this.patchedMethodIdsOffset = 0;
        this.patchedCallSiteIdsOffset = 0;
        this.patchedMethodHandlesOffset = 0;
        this.patchedClassDefsOffset = 0;
        this.patchedTypeListsOffset = 0;
        this.patchedAnnotationItemsOffset = 0;
        this.patchedAnnotationSetItemsOffset = 0;
        this.patchedAnnotationSetRefListItemsOffset = 0;
        this.patchedAnnotationsDirectoryItemsOffset = 0;
        this.patchedDebugInfoItemsOffset = 0;
        this.patchedCodeItemsOffset = 0;
        this.patchedClassDataItemsOffset = 0;
        this.patchedStringDataItemsOffset = 0;
        this.patchedEncodedArrayItemsOffset = 0;
        this.patchedMapListOffset = 0;
        this.patchedDexSize = 0;
        this.oldDex = oldDex;
        this.newDex = newDex;
        SparseIndexMap oldToNewIndexMap = new SparseIndexMap();
        SparseIndexMap oldToPatchedIndexMap = new SparseIndexMap();
        SparseIndexMap newToPatchedIndexMap = new SparseIndexMap();
        SparseIndexMap selfIndexMapForSkip = new SparseIndexMap();
        this.additionalRemovingClassPatternSet = new HashSet();
        this.stringDataSectionDiffAlg = new StringDataSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.typeIdSectionDiffAlg = new TypeIdSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.protoIdSectionDiffAlg = new ProtoIdSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.fieldIdSectionDiffAlg = new FieldIdSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.methodIdSectionDiffAlg = new MethodIdSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.callsiteIdSectionDiffAlg = new CallSiteIdSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.methodHandleSectionDiffAlg = new MethodHandleSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.classDefSectionDiffAlg = new ClassDefSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.typeListSectionDiffAlg = new TypeListSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.annotationSetRefListSectionDiffAlg = new AnnotationSetRefListSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.annotationSetSectionDiffAlg = new AnnotationSetSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.classDataSectionDiffAlg = new ClassDataSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.codeSectionDiffAlg = new CodeSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.debugInfoSectionDiffAlg = new DebugInfoItemSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.annotationSectionDiffAlg = new AnnotationSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.encodedArraySectionDiffAlg = new StaticValueSectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.annotationsDirectorySectionDiffAlg = new AnnotationsDirectorySectionDiffAlgorithm(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
    }

    public void addAdditionalRemovingClassPattern(String pattern) {
        this.additionalRemovingClassPatternSet.add(PatternUtils.dotClassNamePatternToDescriptorRegEx(pattern));
    }

    public void setAdditionalRemovingClassPatterns(Collection<String> patterns) {
        Iterator iterator = patterns.iterator();
        while (iterator.hasNext()) {
            String pattern = (String)iterator.next();
            this.additionalRemovingClassPatternSet.add(PatternUtils.dotClassNamePatternToDescriptorRegEx(pattern));
        }
    }

    public void clearAdditionalRemovingClassPatterns() {
        this.additionalRemovingClassPatternSet.clear();
    }

    public void setLogger(DexPatcherLogger$IDexPatcherLogger logger) {
        this.logger.setLoggerImpl(logger);
    }

    public void executeAndSaveTo(File file) {
        Object os = null;
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
            this.executeAndSaveTo(stream);
            return;
        }
        finally {
            Throwable throwable = v_7;
            IOHelper.closeQuietly(stream);
            throw throwable;
        }
    }

    public void executeAndSaveTo(OutputStream out) {
        Pattern classNamePatterns = new Pattern[]{};
        int classNamePatternCount = 0;
        Iterator iterator = this.additionalRemovingClassPatternSet.iterator();
        while (iterator.hasNext()) {
            String regExStr = (String)iterator.next();
            classNamePatternCount += 1;
            classNamePatterns[classNamePatternCount] = Pattern.compile(regExStr);
        }
        List<Integer> integer> = new ArrayList(classNamePatternCount);
        ArrayList offsetOfClassDatasToRemove = new ArrayList(classNamePatternCount);
        for (Iterator iteratorVar1 = this.newDex.classDefs().iterator(); iteratorVar1.hasNext(); i1 += 1) {
            ClassDef classDef = (ClassDef)iteratorVar1.next();
            String typeName = (String)this.newDex.typeNames().get(classDef.typeIndex);
            int i1 = 0;
            while (i1 < classNamePatterns.length) {
                pattern = classNamePatterns[i1];
                if (pattern.matcher(typeName).matches()) {
                    integer>.add(Integer.valueOf(classDef.typeIndex));
                    offsetOfClassDatasToRemove.add(Integer.valueOf(classDef.classDataOffset));
                    break;;
                }
                else {
                }
            }
        }
        (ClassDefSectionDiffAlgorithm)this.classDefSectionDiffAlg.setTypeIdOfClassDefsToRemove(integer>);
        (ClassDataSectionDiffAlgorithm)this.classDataSectionDiffAlg.setOffsetOfClassDatasToRemove(offsetOfClassDatasToRemove);
        int patchedheaderSize = 112;
        int patchedStringIdsSize = this.newDex.getTableOfContents().stringIds.size * 4;
        int patchedTypeIdsSize = this.newDex.getTableOfContents().typeIds.size * 4;
        int patchedProtoIdsSize = this.newDex.getTableOfContents().protoIds.size * 12;
        int patchedFieldIdsSize = this.newDex.getTableOfContents().fieldIds.size * 8;
        int patchedMethodIdsSize = this.newDex.getTableOfContents().methodIds.size * 8;
        int patchedCallSiteIdsSize = this.newDex.getTableOfContents().callSiteIds.size * 4;
        int patchedMethodHandlesSize = this.newDex.getTableOfContents().callSiteIds.size * 8;
        int patchedClassDefsSize = this.newDex.getTableOfContents().classDefs.size * 32;
        int patchedIdSectionSize = patchedStringIdsSize + patchedTypeIdsSize + patchedProtoIdsSize + patchedFieldIdsSize + patchedMethodIdsSize + patchedCallSiteIdsSize + patchedMethodHandlesSize + patchedClassDefsSize;
        this.patchedHeaderOffset = 0;
        this.patchedStringIdsOffset = this.patchedHeaderOffset + patchedheaderSize;
        if (this.oldDex.getTableOfContents().stringIds.isElementFourByteAligned) {
            this.patchedStringIdsOffset = SizeOf.roundToTimesOfFour(this.patchedStringIdsOffset);
        }
        this.stringDataSectionDiffAlg.execute();
        this.patchedStringDataItemsOffset = patchedheaderSize + patchedIdSectionSize;
        if (this.oldDex.getTableOfContents().stringDatas.isElementFourByteAligned) {
            this.patchedStringDataItemsOffset = SizeOf.roundToTimesOfFour(this.patchedStringDataItemsOffset);
        }
        this.stringDataSectionDiffAlg.simulatePatchOperation(this.patchedStringDataItemsOffset);
        this.typeIdSectionDiffAlg.execute();
        this.patchedTypeIdsOffset = this.patchedStringIdsOffset + patchedStringIdsSize;
        if (this.oldDex.getTableOfContents().typeIds.isElementFourByteAligned) {
            this.patchedTypeIdsOffset = SizeOf.roundToTimesOfFour(this.patchedTypeIdsOffset);
        }
        this.typeIdSectionDiffAlg.simulatePatchOperation(this.patchedTypeIdsOffset);
        this.typeListSectionDiffAlg.execute();
        this.patchedTypeListsOffset = patchedheaderSize + patchedIdSectionSize + this.stringDataSectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().typeLists.isElementFourByteAligned) {
            this.patchedTypeListsOffset = SizeOf.roundToTimesOfFour(this.patchedTypeListsOffset);
        }
        this.typeListSectionDiffAlg.simulatePatchOperation(this.patchedTypeListsOffset);
        this.protoIdSectionDiffAlg.execute();
        this.patchedProtoIdsOffset = this.patchedTypeIdsOffset + patchedTypeIdsSize;
        if (this.oldDex.getTableOfContents().protoIds.isElementFourByteAligned) {
            this.patchedProtoIdsOffset = SizeOf.roundToTimesOfFour(this.patchedProtoIdsOffset);
        }
        this.protoIdSectionDiffAlg.simulatePatchOperation(this.patchedProtoIdsOffset);
        this.fieldIdSectionDiffAlg.execute();
        this.patchedFieldIdsOffset = this.patchedProtoIdsOffset + patchedProtoIdsSize;
        if (this.oldDex.getTableOfContents().fieldIds.isElementFourByteAligned) {
            this.patchedFieldIdsOffset = SizeOf.roundToTimesOfFour(this.patchedFieldIdsOffset);
        }
        this.fieldIdSectionDiffAlg.simulatePatchOperation(this.patchedFieldIdsOffset);
        this.methodIdSectionDiffAlg.execute();
        this.patchedMethodIdsOffset = this.patchedFieldIdsOffset + patchedFieldIdsSize;
        if (this.oldDex.getTableOfContents().methodIds.isElementFourByteAligned) {
            this.patchedMethodIdsOffset = SizeOf.roundToTimesOfFour(this.patchedMethodIdsOffset);
        }
        this.methodIdSectionDiffAlg.simulatePatchOperation(this.patchedMethodIdsOffset);
        this.methodHandleSectionDiffAlg.execute();
        this.patchedMethodHandlesOffset = this.patchedMethodIdsOffset + patchedMethodIdsSize;
        if (this.oldDex.getTableOfContents().methodHandles.isElementFourByteAligned) {
            this.patchedMethodHandlesOffset = SizeOf.roundToTimesOfFour(this.patchedMethodHandlesOffset);
        }
        this.methodHandleSectionDiffAlg.simulatePatchOperation(this.patchedMethodHandlesOffset);
        this.annotationSectionDiffAlg.execute();
        this.patchedAnnotationItemsOffset = this.patchedTypeListsOffset + this.typeListSectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().annotations.isElementFourByteAligned) {
            this.patchedAnnotationItemsOffset = SizeOf.roundToTimesOfFour(this.patchedAnnotationItemsOffset);
        }
        this.annotationSectionDiffAlg.simulatePatchOperation(this.patchedAnnotationItemsOffset);
        this.annotationSetSectionDiffAlg.execute();
        this.patchedAnnotationSetItemsOffset = this.patchedAnnotationItemsOffset + this.annotationSectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().annotationSets.isElementFourByteAligned) {
            this.patchedAnnotationSetItemsOffset = SizeOf.roundToTimesOfFour(this.patchedAnnotationSetItemsOffset);
        }
        this.annotationSetSectionDiffAlg.simulatePatchOperation(this.patchedAnnotationSetItemsOffset);
        this.annotationSetRefListSectionDiffAlg.execute();
        this.patchedAnnotationSetRefListItemsOffset = this.patchedAnnotationSetItemsOffset + this.annotationSetSectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().annotationSetRefLists.isElementFourByteAligned) {
            this.patchedAnnotationSetRefListItemsOffset = SizeOf.roundToTimesOfFour(this.patchedAnnotationSetRefListItemsOffset);
        }
        this.annotationSetRefListSectionDiffAlg.simulatePatchOperation(this.patchedAnnotationSetRefListItemsOffset);
        this.annotationsDirectorySectionDiffAlg.execute();
        this.patchedAnnotationsDirectoryItemsOffset = this.patchedAnnotationSetRefListItemsOffset + this.annotationSetRefListSectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().annotationsDirectories.isElementFourByteAligned) {
            this.patchedAnnotationsDirectoryItemsOffset = SizeOf.roundToTimesOfFour(this.patchedAnnotationsDirectoryItemsOffset);
        }
        this.annotationsDirectorySectionDiffAlg.simulatePatchOperation(this.patchedAnnotationsDirectoryItemsOffset);
        this.debugInfoSectionDiffAlg.execute();
        this.patchedDebugInfoItemsOffset = this.patchedAnnotationsDirectoryItemsOffset + this.annotationsDirectorySectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().debugInfos.isElementFourByteAligned) {
            this.patchedDebugInfoItemsOffset = SizeOf.roundToTimesOfFour(this.patchedDebugInfoItemsOffset);
        }
        this.debugInfoSectionDiffAlg.simulatePatchOperation(this.patchedDebugInfoItemsOffset);
        this.codeSectionDiffAlg.execute();
        this.patchedCodeItemsOffset = this.patchedDebugInfoItemsOffset + this.debugInfoSectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().codes.isElementFourByteAligned) {
            this.patchedCodeItemsOffset = SizeOf.roundToTimesOfFour(this.patchedCodeItemsOffset);
        }
        this.codeSectionDiffAlg.simulatePatchOperation(this.patchedCodeItemsOffset);
        this.classDataSectionDiffAlg.execute();
        this.patchedClassDataItemsOffset = this.patchedCodeItemsOffset + this.codeSectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().classDatas.isElementFourByteAligned) {
            this.patchedClassDataItemsOffset = SizeOf.roundToTimesOfFour(this.patchedClassDataItemsOffset);
        }
        this.classDataSectionDiffAlg.simulatePatchOperation(this.patchedClassDataItemsOffset);
        this.encodedArraySectionDiffAlg.execute();
        this.patchedEncodedArrayItemsOffset = this.patchedClassDataItemsOffset + this.classDataSectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().encodedArrays.isElementFourByteAligned) {
            this.patchedEncodedArrayItemsOffset = SizeOf.roundToTimesOfFour(this.patchedEncodedArrayItemsOffset);
        }
        this.encodedArraySectionDiffAlg.simulatePatchOperation(this.patchedEncodedArrayItemsOffset);
        this.callsiteIdSectionDiffAlg.execute();
        this.patchedCallSiteIdsOffset = this.patchedEncodedArrayItemsOffset + this.encodedArraySectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().callSiteIds.isElementFourByteAligned) {
            this.patchedCallSiteIdsOffset = SizeOf.roundToTimesOfFour(this.patchedCallSiteIdsOffset);
        }
        this.callsiteIdSectionDiffAlg.simulatePatchOperation(this.patchedCallSiteIdsOffset);
        this.classDefSectionDiffAlg.execute();
        this.patchedClassDefsOffset = this.patchedMethodHandlesOffset + patchedMethodHandlesSize;
        if (this.oldDex.getTableOfContents().classDefs.isElementFourByteAligned) {
            this.patchedClassDefsOffset = SizeOf.roundToTimesOfFour(this.patchedClassDefsOffset);
        }
        this.patchedMapListOffset = this.patchedEncodedArrayItemsOffset + this.encodedArraySectionDiffAlg.getPatchedSectionSize();
        if (this.oldDex.getTableOfContents().mapList.isElementFourByteAligned) {
            this.patchedMapListOffset = SizeOf.roundToTimesOfFour(this.patchedMapListOffset);
        }
        int patchedMapListSize = this.newDex.getTableOfContents().mapList.byteCount;
        this.patchedDexSize = this.patchedMapListOffset + patchedMapListSize;
        this.writeResultToStream(out);
    }

    private void writeResultToStream(OutputStream os) {
        DexDataBuffer buffer = new DexDataBuffer();
        buffer.write(DexPatchFile.MAGIC);
        buffer.writeShort(3);
        buffer.writeInt(this.oldDex.getTableOfContents().api);
        buffer.writeInt(this.newDex.getTableOfContents().api);
        buffer.writeInt(this.patchedDexSize);
        int posOfFirstChunkOffsetField = buffer.position();
        buffer.writeInt(0);
        buffer.writeInt(this.patchedStringIdsOffset);
        buffer.writeInt(this.patchedTypeIdsOffset);
        buffer.writeInt(this.patchedProtoIdsOffset);
        buffer.writeInt(this.patchedFieldIdsOffset);
        buffer.writeInt(this.patchedMethodIdsOffset);
        buffer.writeInt(this.patchedCallSiteIdsOffset);
        buffer.writeInt(this.patchedMethodHandlesOffset);
        buffer.writeInt(this.patchedClassDefsOffset);
        buffer.writeInt(this.patchedMapListOffset);
        buffer.writeInt(this.patchedTypeListsOffset);
        buffer.writeInt(this.patchedAnnotationSetRefListItemsOffset);
        buffer.writeInt(this.patchedAnnotationSetItemsOffset);
        buffer.writeInt(this.patchedClassDataItemsOffset);
        buffer.writeInt(this.patchedCodeItemsOffset);
        buffer.writeInt(this.patchedStringDataItemsOffset);
        buffer.writeInt(this.patchedDebugInfoItemsOffset);
        buffer.writeInt(this.patchedAnnotationItemsOffset);
        buffer.writeInt(this.patchedEncodedArrayItemsOffset);
        buffer.writeInt(this.patchedAnnotationsDirectoryItemsOffset);
        buffer.write(this.oldDex.computeSignature(false));
        int firstChunkOffset = buffer.position();
        buffer.position(posOfFirstChunkOffsetField);
        buffer.writeInt(firstChunkOffset);
        buffer.position(firstChunkOffset);
        this.writePatchOperations(buffer, this.stringDataSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.typeIdSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.typeListSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.protoIdSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.fieldIdSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.methodIdSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.methodHandleSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.annotationSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.annotationSetSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.annotationSetRefListSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.annotationsDirectorySectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.debugInfoSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.codeSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.classDataSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.encodedArraySectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.callsiteIdSectionDiffAlg.getPatchOperationList());
        this.writePatchOperations(buffer, this.classDefSectionDiffAlg.getPatchOperationList());
        byte[] bufferData = buffer.array();
        os.write(bufferData);
        os.flush();
    }

    private <T> void writePatchOperations(DexDataBuffer buffer, List<PatchOperation<T>> patchOperationList) {
        Integer index;
        ArrayList delOpIndexList = new ArrayList(patchOperationList.size());
        ArrayList addOpIndexList = new ArrayList(patchOperationList.size());
        ArrayList replaceOpIndexList = new ArrayList(patchOperationList.size());
        ArrayList newItemList = new ArrayList(patchOperationList.size());
        Iterator iterator = patchOperationList.iterator();
        while (iterator.hasNext()) {
PatchOperation patchOperation = (PatchOperation)iterator.next();
            switch(patchOperation.op) {
                case 0: {
                    delOpIndexList.add(Integer.valueOf(patchOperation.index));
                    continue;;
                }
                case 1: {
                    addOpIndexList.add(Integer.valueOf(patchOperation.index));
                    newItemList.add((Comparable)patchOperation.newItem);
                    continue;;
                }
                case 2: {
                    replaceOpIndexList.add(Integer.valueOf(patchOperation.index));
                    newItemList.add((Comparable)patchOperation.newItem);
                    continue;;
                }
            }
        }
        buffer.writeUleb128(delOpIndexList.size());
        int lastIndex = 0;
        Iterator iteratorVar4 = delOpIndexList.iterator();
        while (iteratorVar4.hasNext()) {
            index = (Integer)iteratorVar4.next();
            buffer.writeSleb128(index.intValue() - lastIndex);
            lastIndex = index.intValue();
        }
        buffer.writeUleb128(addOpIndexList.size());
        lastIndex = 0;
        iteratorVar4 = addOpIndexList.iterator();
        while (iteratorVar4.hasNext()) {
            index = (Integer)iteratorVar4.next();
            buffer.writeSleb128(index.intValue() - lastIndex);
            lastIndex = index.intValue();
        }
        buffer.writeUleb128(replaceOpIndexList.size());
        lastIndex = 0;
        iteratorVar4 = replaceOpIndexList.iterator();
        while (iteratorVar4.hasNext()) {
            index = (Integer)iteratorVar4.next();
            buffer.writeSleb128(index.intValue() - lastIndex);
            lastIndex = index.intValue();
        }
        iteratorVar4 = newItemList.iterator();
        while (iteratorVar4.hasNext()) {
            Comparable newItem = (Comparable)iteratorVar4.next();
            if ((newItem instanceof StringData)) {
                buffer.writeStringData((StringData)newItem);
                continue;;
            }
            else if ((newItem instanceof Integer)) {
                buffer.writeInt((Integer)newItem.intValue());
                continue;;
            }
            else if ((newItem instanceof TypeList)) {
                buffer.writeTypeList((TypeList)newItem);
                continue;;
            }
            else if ((newItem instanceof ProtoId)) {
                buffer.writeProtoId((ProtoId)newItem);
                continue;;
            }
            else if ((newItem instanceof FieldId)) {
                buffer.writeFieldId((FieldId)newItem);
                continue;;
            }
            else if ((newItem instanceof MethodId)) {
                buffer.writeMethodId((MethodId)newItem);
                continue;;
            }
            else if ((newItem instanceof CallSiteId)) {
                buffer.writeCallSiteId((CallSiteId)newItem);
                continue;;
            }
            else if ((newItem instanceof MethodHandle)) {
                buffer.writeMethodHandle((MethodHandle)newItem);
                continue;;
            }
            else if ((newItem instanceof Annotation)) {
                buffer.writeAnnotation((Annotation)newItem);
                continue;;
            }
            else if ((newItem instanceof AnnotationSet)) {
                buffer.writeAnnotationSet((AnnotationSet)newItem);
                continue;;
            }
            else if ((newItem instanceof AnnotationSetRefList)) {
                buffer.writeAnnotationSetRefList((AnnotationSetRefList)newItem);
                continue;;
            }
            else if ((newItem instanceof AnnotationsDirectory)) {
                buffer.writeAnnotationsDirectory((AnnotationsDirectory)newItem);
                continue;;
            }
            else if ((newItem instanceof DebugInfoItem)) {
                buffer.writeDebugInfoItem((DebugInfoItem)newItem);
                continue;;
            }
            else if ((newItem instanceof Code)) {
                buffer.writeCode((Code)newItem);
                continue;;
            }
            else if ((newItem instanceof ClassData)) {
                buffer.writeClassData((ClassData)newItem);
                continue;;
            }
            else if ((newItem instanceof EncodedValue)) {
                buffer.writeEncodedArray((EncodedValue)newItem);
                continue;;
            }
            else if ((newItem instanceof ClassDef)) {
                buffer.writeClassDef((ClassDef)newItem);
                continue;;
            }
            else {
                throw new IllegalStateException(new StringBuilder().append("Unknown item type: ").append(newItem.getClass()).toString());
            }
        }
    }

}
