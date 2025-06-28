/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

// class: com/tencent/tinker/commons/dexpatcher/DexPatchApplier
public class DexPatchApplier {
    final private Dex oldDex;
    final private Dex patchedDex;
    final private DexPatchFile patchFile;
    final private SparseIndexMap oldToPatchedIndexMap;
    private DexSectionPatchAlgorithm<StringData> stringDataSectionPatchAlg;
    private DexSectionPatchAlgorithm<Integer> typeIdSectionPatchAlg;
    private DexSectionPatchAlgorithm<ProtoId> protoIdSectionPatchAlg;
    private DexSectionPatchAlgorithm<FieldId> fieldIdSectionPatchAlg;
    private DexSectionPatchAlgorithm<MethodId> methodIdSectionPatchAlg;
    private DexSectionPatchAlgorithm<CallSiteId> callsiteIdSectionPatchAlg;
    private DexSectionPatchAlgorithm<MethodHandle> methodHandleSectionPatchAlg;
    private DexSectionPatchAlgorithm<ClassDef> classDefSectionPatchAlg;
    private DexSectionPatchAlgorithm<TypeList> typeListSectionPatchAlg;
    private DexSectionPatchAlgorithm<AnnotationSetRefList> annotationSetRefListSectionPatchAlg;
    private DexSectionPatchAlgorithm<AnnotationSet> annotationSetSectionPatchAlg;
    private DexSectionPatchAlgorithm<ClassData> classDataSectionPatchAlg;
    private DexSectionPatchAlgorithm<Code> codeSectionPatchAlg;
    private DexSectionPatchAlgorithm<DebugInfoItem> debugInfoSectionPatchAlg;
    private DexSectionPatchAlgorithm<Annotation> annotationSectionPatchAlg;
    private DexSectionPatchAlgorithm<EncodedValue> encodedArraySectionPatchAlg;
    private DexSectionPatchAlgorithm<AnnotationsDirectory> annotationsDirectorySectionPatchAlg;

    public DexPatchApplier(File oldDexIn, File patchFileIn) {
        super(new Dex(oldDexIn), new DexPatchFile(patchFileIn));
    }

    public DexPatchApplier(InputStream oldDexIn, InputStream patchFileIn) {
        super(new Dex(oldDexIn), new DexPatchFile(patchFileIn));
    }

    public DexPatchApplier(Dex oldDexIn, DexPatchFile patchFileIn) {
        super();
        this.oldDex = oldDexIn;
        this.patchFile = patchFileIn;
        this.patchedDex = new Dex(patchFileIn.getPatchedDexSize());
        this.oldToPatchedIndexMap = new SparseIndexMap();
    }

    public void executeAndSaveTo(OutputStream out) {
        if (this.patchFile == null) {
            throw new IllegalArgumentException("patch file is null.");
        }
        else {
            if (this.patchFile.getVersion() > 2) {
                int oldDexAPI = this.oldDex.getTableOfContents().api;
                int expectedOldDexAPI = this.patchFile.getOldDexAPI();
                if (oldDexAPI != expectedOldDexAPI) {
                    throw new IOException(new StringBuilder().append("old dex version mismatch! expetced: ").append(expectedOldDexAPI).append(", actual: ").append(oldDexAPI).toString());
                }
            }
            byte[] oldDexSign = this.oldDex.computeSignature(false);
            if (oldDexSign == null) {
                throw new IOException("failed to compute old dex's signature.");
            }
            else {
                byte[] oldDexSignInPatchFile = this.patchFile.getOldDexSignature();
                if (CompareUtils.uArrCompare(oldDexSign, oldDexSignInPatchFile) != 0) {
                    throw new IOException(String.format("old dex signature mismatch! expected: %s, actual: %s", new Object[]{Arrays.toString(oldDexSign), Arrays.toString(oldDexSignInPatchFile)}));
                }
                else {
                    TableOfContents patchedToc = this.patchedDex.getTableOfContents();
                    patchedToc.api = this.patchFile.getPatchedDexAPI();
                    patchedToc.header.off = 0;
                    patchedToc.header.size = 1;
                    patchedToc.mapList.size = 1;
                    patchedToc.stringIds.off = this.patchFile.getPatchedStringIdSectionOffset();
                    patchedToc.typeIds.off = this.patchFile.getPatchedTypeIdSectionOffset();
                    patchedToc.typeLists.off = this.patchFile.getPatchedTypeListSectionOffset();
                    patchedToc.protoIds.off = this.patchFile.getPatchedProtoIdSectionOffset();
                    patchedToc.fieldIds.off = this.patchFile.getPatchedFieldIdSectionOffset();
                    patchedToc.methodIds.off = this.patchFile.getPatchedMethodIdSectionOffset();
                    patchedToc.classDefs.off = this.patchFile.getVersion() > 2 ? this.patchFile.getPatchedClassDefSectionOffset() : this.patchFile.getPatchedCallSiteIdSectionOffset();
                    patchedToc.mapList.off = this.patchFile.getPatchedMapListSectionOffset();
                    patchedToc.stringDatas.off = this.patchFile.getPatchedStringDataSectionOffset();
                    patchedToc.annotations.off = this.patchFile.getPatchedAnnotationSectionOffset();
                    patchedToc.annotationSets.off = this.patchFile.getPatchedAnnotationSetSectionOffset();
                    patchedToc.annotationSetRefLists.off = this.patchFile.getPatchedAnnotationSetRefListSectionOffset();
                    patchedToc.annotationsDirectories.off = this.patchFile.getPatchedAnnotationsDirectorySectionOffset();
                    patchedToc.encodedArrays.off = this.patchFile.getPatchedEncodedArraySectionOffset();
                    patchedToc.debugInfos.off = this.patchFile.getPatchedDebugInfoSectionOffset();
                    patchedToc.codes.off = this.patchFile.getPatchedCodeSectionOffset();
                    patchedToc.classDatas.off = this.patchFile.getPatchedClassDataSectionOffset();
                    patchedToc.fileSize = this.patchFile.getPatchedDexSize();
                    Arrays.sort(patchedToc.sections);
                    patchedToc.computeSizesFromOffsets();
                    this.stringDataSectionPatchAlg = new StringDataSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.typeIdSectionPatchAlg = new TypeIdSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.protoIdSectionPatchAlg = new ProtoIdSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.fieldIdSectionPatchAlg = new FieldIdSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.methodIdSectionPatchAlg = new MethodIdSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    if (this.patchFile.getVersion() > 2) {
                        this.callsiteIdSectionPatchAlg = new CallSiteIdSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                        this.methodHandleSectionPatchAlg = new MethodHandleSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    }
                    this.classDefSectionPatchAlg = new ClassDefSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.typeListSectionPatchAlg = new TypeListSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.annotationSetRefListSectionPatchAlg = new AnnotationSetRefListSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.annotationSetSectionPatchAlg = new AnnotationSetSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.classDataSectionPatchAlg = new ClassDataSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.codeSectionPatchAlg = new CodeSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.debugInfoSectionPatchAlg = new DebugInfoItemSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.annotationSectionPatchAlg = new AnnotationSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.encodedArraySectionPatchAlg = new StaticValueSectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.annotationsDirectorySectionPatchAlg = new AnnotationsDirectorySectionPatchAlgorithm(this.patchFile, this.oldDex, this.patchedDex, this.oldToPatchedIndexMap);
                    this.stringDataSectionPatchAlg.execute();
                    this.typeIdSectionPatchAlg.execute();
                    this.typeListSectionPatchAlg.execute();
                    this.protoIdSectionPatchAlg.execute();
                    this.fieldIdSectionPatchAlg.execute();
                    this.methodIdSectionPatchAlg.execute();
                    if (this.patchFile.getVersion() > 2) {
                        this.methodHandleSectionPatchAlg.execute();
                    }
                    this.annotationSectionPatchAlg.execute();
                    this.annotationSetSectionPatchAlg.execute();
                    this.annotationSetRefListSectionPatchAlg.execute();
                    this.annotationsDirectorySectionPatchAlg.execute();
                    this.debugInfoSectionPatchAlg.execute();
                    this.codeSectionPatchAlg.execute();
                    this.classDataSectionPatchAlg.execute();
                    this.encodedArraySectionPatchAlg.execute();
                    if (this.patchFile.getVersion() > 2) {
                        this.callsiteIdSectionPatchAlg.execute();
                    }
                    this.classDefSectionPatchAlg.execute();
                    Dex$Section headerOut = this.patchedDex.openSection(patchedToc.header.off);
                    patchedToc.writeHeader(headerOut);
                    Dex$Section mapListOut = this.patchedDex.openSection(patchedToc.mapList.off);
                    patchedToc.writeMap(mapListOut);
                    this.patchedDex.writeHashes();
                    this.patchedDex.writeTo(out);
                }
            }
        }
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

}
