/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/util;

import com.tencent.tinker.android.dex.TypeList;
import com.tencent.tinker.android.dex.MethodId;
import com.tencent.tinker.android.dex.FieldId;
import com.tencent.tinker.android.dex.ProtoId;
import com.tencent.tinker.android.dex.CallSiteId;
import com.tencent.tinker.android.dex.MethodHandle$MethodHandleType;
import com.tencent.tinker.android.dex.MethodHandle;
import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.android.dex.ClassData$Field;
import com.tencent.tinker.android.dex.ClassData$Field[];
import com.tencent.tinker.android.dex.ClassData$Method;
import com.tencent.tinker.android.dex.ClassData$Method[];
import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.Code$CatchHandler;
import com.tencent.tinker.android.dex.Code$CatchHandler[];
import com.tencent.tinker.android.dex.Code;
import com.tencent.tinker.android.dex.Code$Try;
import com.tencent.tinker.android.dex.DebugInfoItem;
import com.tencent.tinker.android.dex.EncodedValueReader;
import com.tencent.tinker.android.dex.EncodedValue;
import com.tencent.tinker.android.dex.Annotation;
import com.tencent.tinker.android.dex.AnnotationSet;
import com.tencent.tinker.android.dex.AnnotationSetRefList;
import com.tencent.tinker.android.dex.AnnotationsDirectory;
import com.tencent.tinker.android.dex.util.ByteOutput;
import com.tencent.tinker.android.dex.DexException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

// class: com/tencent/tinker/commons/dexpatcher/util/AbstractIndexMap
public abstract class AbstractIndexMap {

    public AbstractIndexMap() {
        super();
    }

    int adjustStringIndex(int p0);

    int adjustTypeIdIndex(int p0);

    int adjustProtoIdIndex(int p0);

    int adjustFieldIdIndex(int p0);

    int adjustMethodIdIndex(int p0);

    int adjustCallSiteIdIndex(int p0);

    int adjustMethodHandleIndex(int p0);

    int adjustTypeListOffset(int p0);

    int adjustAnnotationOffset(int p0);

    int adjustAnnotationSetOffset(int p0);

    int adjustAnnotationSetRefListOffset(int p0);

    int adjustAnnotationsDirectoryOffset(int p0);

    int adjustStaticValuesOffset(int p0);

    int adjustClassDataOffset(int p0);

    int adjustDebugInfoItemOffset(int p0);

    int adjustCodeOffset(int p0);

    public TypeList adjust(TypeList typeList) {
        if (typeList == TypeList.EMPTY) {
            return typeList;
        }
        else {
            short[] types = new short[]{};
            for (int i = 0; i < types.length; i += 1) {
                types[i] = (short)this.adjustTypeIdIndex(typeList.types[i]);
            }
            return new TypeList(typeList.off, types);
        }
    }

    public MethodId adjust(MethodId methodId) {
        int adjustedDeclaringClassIndex = this.adjustTypeIdIndex(methodId.declaringClassIndex);
        int adjustedProtoIndex = this.adjustProtoIdIndex(methodId.protoIndex);
        int adjustedNameIndex = this.adjustStringIndex(methodId.nameIndex);
        return new MethodId(methodId.off, adjustedDeclaringClassIndex, adjustedProtoIndex, adjustedNameIndex);
    }

    public FieldId adjust(FieldId fieldId) {
        int adjustedDeclaringClassIndex = this.adjustTypeIdIndex(fieldId.declaringClassIndex);
        int adjustedTypeIndex = this.adjustTypeIdIndex(fieldId.typeIndex);
        int adjustedNameIndex = this.adjustStringIndex(fieldId.nameIndex);
        return new FieldId(fieldId.off, adjustedDeclaringClassIndex, adjustedTypeIndex, adjustedNameIndex);
    }

    public ProtoId adjust(ProtoId protoId) {
        int adjustedShortyIndex = this.adjustStringIndex(protoId.shortyIndex);
        int adjustedReturnTypeIndex = this.adjustTypeIdIndex(protoId.returnTypeIndex);
        int adjustedParametersOffset = this.adjustTypeListOffset(protoId.parametersOffset);
        return new ProtoId(protoId.off, adjustedShortyIndex, adjustedReturnTypeIndex, adjustedParametersOffset);
    }

    public CallSiteId adjust(CallSiteId callSiteId) {
        int adjustedCallSiteIdOffset = this.adjustStaticValuesOffset(callSiteId.offset);
        return new CallSiteId(callSiteId.off, adjustedCallSiteIdOffset);
    }

    public MethodHandle adjust(MethodHandle methodHandle) {
        int adjustedFieldOrMethodId = methodHandle.methodHandleType.isField() ? this.adjustMethodIdIndex(methodHandle.fieldOrMethodId) : this.adjustFieldIdIndex(methodHandle.fieldOrMethodId);
        return new MethodHandle(methodHandle.off, methodHandle.methodHandleType, methodHandle.unused1, adjustedFieldOrMethodId, methodHandle.unused2);
    }

    public ClassDef adjust(ClassDef classDef) {
        int adjustedTypeIndex = this.adjustTypeIdIndex(classDef.typeIndex);
        int adjustedSupertypeIndex = this.adjustTypeIdIndex(classDef.supertypeIndex);
        int adjustedInterfacesOffset = this.adjustTypeListOffset(classDef.interfacesOffset);
        int adjustedSourceFileIndex = this.adjustStringIndex(classDef.sourceFileIndex);
        int adjustedAnnotationsOffset = this.adjustAnnotationsDirectoryOffset(classDef.annotationsOffset);
        int adjustedClassDataOffset = this.adjustClassDataOffset(classDef.classDataOffset);
        int adjustedStaticValuesOffset = this.adjustStaticValuesOffset(classDef.staticValuesOffset);
        return new ClassDef(classDef.off, adjustedTypeIndex, classDef.accessFlags, adjustedSupertypeIndex, adjustedInterfacesOffset, adjustedSourceFileIndex, adjustedAnnotationsOffset, adjustedClassDataOffset, adjustedStaticValuesOffset);
    }

    public ClassData adjust(ClassData classData) {
        ClassData$Field[] adjustedStaticFields = this.adjustFields(classData.staticFields);
        ClassData$Field[] adjustedInstanceFields = this.adjustFields(classData.instanceFields);
        ClassData$Method[] adjustedDirectMethods = this.adjustMethods(classData.directMethods);
        ClassData$Method[] adjustedVirtualMethods = this.adjustMethods(classData.virtualMethods);
        return new ClassData(classData.off, adjustedStaticFields, adjustedInstanceFields, adjustedDirectMethods, adjustedVirtualMethods);
    }

    public Code adjust(Code code) {
        int adjustedDebugInfoOffset = this.adjustDebugInfoItemOffset(code.debugInfoOffset);
        short[] adjustedInstructions = this.adjustInstructions(code.instructions);
        Code$CatchHandler[] adjustedCatchHandlers = this.adjustCatchHandlers(code.catchHandlers);
        return new Code(code.off, code.registersSize, code.insSize, code.outsSize, adjustedDebugInfoOffset, adjustedInstructions, code.tries, adjustedCatchHandlers);
    }

    private short[] adjustInstructions(short[] instructions) {
        if (instructions == null || instructions.length == 0) {
            return instructions;
        }
        else {
            InstructionTransformer insTrans = new InstructionTransformer(this);
            return insTrans.transform(instructions);
        }
    }

    private Code$CatchHandler[] adjustCatchHandlers(Code$CatchHandler[] catchHandlers) {
        if (catchHandlers == null || catchHandlers.length == 0) {
            return catchHandlers;
        }
        else {
            Code$CatchHandler adjustedCatchHandlers = new Code$CatchHandler[]{};
            for (int i = 0; i < catchHandlers.length; i += 1) {
                Code$CatchHandler catchHandler = catchHandlers[i];
                int[] adjustedTypeIndexes = new int[]{};
                for (int j = 0; j < catchHandler.typeIndexes.length; j += 1) {
                    adjustedTypeIndexes[j] = this.adjustTypeIdIndex(catchHandler.typeIndexes[j]);
                }
                adjustedCatchHandlers[i] = new Code$CatchHandler(adjustedTypeIndexes, catchHandler.addresses, catchHandler.catchAllAddress, catchHandler.offset);
            }
            return adjustedCatchHandlers;
        }
    }

    private ClassData$Field[] adjustFields(ClassData$Field[] fields) {
        ClassData$Field adjustedFields = new ClassData$Field[]{};
        for (int i = 0; i < fields.length; i += 1) {
            ClassData$Field field = fields[i];
            int adjustedFieldIndex = this.adjustFieldIdIndex(field.fieldIndex);
            adjustedFields[i] = new ClassData$Field(adjustedFieldIndex, field.accessFlags);
        }
        return adjustedFields;
    }

    private ClassData$Method[] adjustMethods(ClassData$Method[] methods) {
        ClassData$Method adjustedMethods = new ClassData$Method[]{};
        for (int i = 0; i < methods.length; i += 1) {
            ClassData$Method method = methods[i];
            int adjustedMethodIndex = this.adjustMethodIdIndex(method.methodIndex);
            int adjustedCodeOffset = this.adjustCodeOffset(method.codeOffset);
            adjustedMethods[i] = new ClassData$Method(adjustedMethodIndex, method.accessFlags, adjustedCodeOffset);
        }
        return adjustedMethods;
    }

    public DebugInfoItem adjust(DebugInfoItem debugInfoItem) {
        int[] parameterNames = this.adjustParameterNames(debugInfoItem.parameterNames);
        byte[] infoSTM = this.adjustDebugInfoItemSTM(debugInfoItem.infoSTM);
        return new DebugInfoItem(debugInfoItem.off, debugInfoItem.lineStart, parameterNames, infoSTM);
    }

    private int[] adjustParameterNames(int[] parameterNames) {
        int[] adjustedParameterNames = new int[]{};
        for (int i = 0; i < parameterNames.length; i += 1) {
            adjustedParameterNames[i] = this.adjustStringIndex(parameterNames[i]);
        }
        return adjustedParameterNames;
    }

    private byte[] adjustDebugInfoItemSTM(byte[] infoSTM) {
        ByteArrayInputStream bais = new ByteArrayInputStream(infoSTM);
        AbstractIndexMap$1 inAdapter = new AbstractIndexMap$1(this, bais);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(infoSTM.length + 512);
        AbstractIndexMap$2 outAdapter = new AbstractIndexMap$2(this, baos);
        while (true) {
int opcode = bais.read() & 255;
baos.write(opcode);
            switch(opcode) {
                case 0: {
                    break;;
                    return baos.toByteArray();
                }
                case 1: {
                    int addrDiff = Leb128.readUnsignedLeb128(inAdapter);
                    Leb128.writeUnsignedLeb128(outAdapter, addrDiff);
                    continue;;
                }
                case 2: {
                    int lineDiff = Leb128.readSignedLeb128(inAdapter);
                    Leb128.writeSignedLeb128(outAdapter, lineDiff);
                    continue;;
                }
                int registerNum;
                int nameIndex;
                case 3: {
                    registerNum = Leb128.readUnsignedLeb128(inAdapter);
                    Leb128.writeUnsignedLeb128(outAdapter, registerNum);
                    nameIndex = this.adjustStringIndex(Leb128.readUnsignedLeb128p1(inAdapter));
                    Leb128.writeUnsignedLeb128p1(outAdapter, nameIndex);
                    int typeIndex = this.adjustTypeIdIndex(Leb128.readUnsignedLeb128p1(inAdapter));
                    Leb128.writeUnsignedLeb128p1(outAdapter, typeIndex);
                    if (opcode == 4) {
                        int sigIndex = this.adjustStringIndex(Leb128.readUnsignedLeb128p1(inAdapter));
                        Leb128.writeUnsignedLeb128p1(outAdapter, sigIndex);
                        continue;;
                    }
                }
                case 5: {
                    registerNum = Leb128.readUnsignedLeb128(inAdapter);
                    Leb128.writeUnsignedLeb128(outAdapter, registerNum);
                    continue;;
                }
                case 9: {
                    nameIndex = this.adjustStringIndex(Leb128.readUnsignedLeb128p1(inAdapter));
                    Leb128.writeUnsignedLeb128p1(outAdapter, nameIndex);
                    continue;;
                }
            }
        }
    }

    public EncodedValue adjust(EncodedValue encodedArray) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(encodedArray.data.length);
        AbstractIndexMap$EncodedValueTransformer.access$000(new AbstractIndexMap$EncodedValueTransformer(this, new AbstractIndexMap$3(this, baos)), new EncodedValueReader(encodedArray, 28));
        return new EncodedValue(encodedArray.off, baos.toByteArray());
    }

    public Annotation adjust(Annotation annotation) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(annotation.encodedAnnotation.data.length);
        AbstractIndexMap$EncodedValueTransformer.access$100(new AbstractIndexMap$EncodedValueTransformer(this, new AbstractIndexMap$4(this, baos)), annotation.getReader());
        return new Annotation(annotation.off, annotation.visibility, new EncodedValue(annotation.encodedAnnotation.off, baos.toByteArray()));
    }

    public AnnotationSet adjust(AnnotationSet annotationSet) {
        int[] adjustedAnnotationOffsets = new int[]{};
        for (int i = 0; i < annotationSet.annotationOffsets.length; i += 1) {
            adjustedAnnotationOffsets[i] = this.adjustAnnotationOffset(annotationSet.annotationOffsets[i]);
        }
        return new AnnotationSet(annotationSet.off, adjustedAnnotationOffsets);
    }

    public AnnotationSetRefList adjust(AnnotationSetRefList annotationSetRefList) {
        int[] adjustedAnnotationSetRefItems = new int[]{};
        for (int i = 0; i < annotationSetRefList.annotationSetRefItems.length; i += 1) {
            adjustedAnnotationSetRefItems[i] = this.adjustAnnotationSetOffset(annotationSetRefList.annotationSetRefItems[i]);
        }
        return new AnnotationSetRefList(annotationSetRefList.off, adjustedAnnotationSetRefItems);
    }

    public AnnotationsDirectory adjust(AnnotationsDirectory annotationsDirectory) {
        int adjustedClassAnnotationsOffset = this.adjustAnnotationSetOffset(annotationsDirectory.classAnnotationsOffset);
        v_6 = annotationsDirectory.fieldAnnotations.length;
        int[][] adjustedFieldAnnotations = new int[][][]{};
        for (int i = 0; i < adjustedFieldAnnotations.length; i += 1) {
            adjustedFieldAnnotations[i][0] = this.adjustFieldIdIndex(annotationsDirectory.fieldAnnotations[i][0]);
            adjustedFieldAnnotations[i][1] = this.adjustAnnotationSetOffset(annotationsDirectory.fieldAnnotations[i][1]);
        }
        v_39 = annotationsDirectory.methodAnnotations.length;
        int[][] adjustedMethodAnnotations = new int[][][]{};
        for (i = 0; i < adjustedMethodAnnotations.length; i += 1) {
            adjustedMethodAnnotations[i][0] = this.adjustMethodIdIndex(annotationsDirectory.methodAnnotations[i][0]);
            adjustedMethodAnnotations[i][1] = this.adjustAnnotationSetOffset(annotationsDirectory.methodAnnotations[i][1]);
        }
        v_72 = annotationsDirectory.parameterAnnotations.length;
        int[][] adjustedParameterAnnotations = new int[][][]{};
        for (i = 0; i < adjustedParameterAnnotations.length; i += 1) {
            adjustedParameterAnnotations[i][0] = this.adjustMethodIdIndex(annotationsDirectory.parameterAnnotations[i][0]);
            adjustedParameterAnnotations[i][1] = this.adjustAnnotationSetRefListOffset(annotationsDirectory.parameterAnnotations[i][1]);
        }
        return new AnnotationsDirectory(annotationsDirectory.off, adjustedClassAnnotationsOffset, adjustedFieldAnnotations, adjustedMethodAnnotations, adjustedParameterAnnotations);
    }

    // class: com/tencent/tinker/commons/dexpatcher/util/AbstractIndexMap$EncodedValueTransformer
    final class AbstractIndexMap$EncodedValueTransformer {
        final private ByteOutput out;
        final synthetic AbstractIndexMap this$0;

         AbstractIndexMap$EncodedValueTransformer(AbstractIndexMap map, ByteOutput out) {
            this.this$0 = map;
            super();
            this.out = out;
        }

        public void transform(EncodedValueReader reader) {
            switch(reader.peek()) {
                case 0: {
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 0, (long)reader.readByte());
                    return;
                }
                case 2: {
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 2, (long)reader.readShort());
                    return;
                }
                case 4: {
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 4, (long)reader.readInt());
                    return;
                }
                case 6: {
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 6, reader.readLong());
                    return;
                }
                case 3: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 3, (long)reader.readChar());
                    return;
                }
                case 16: {
                    long longBits = (long)Float.floatToIntBits(reader.readFloat()) << 32;
                    EncodedValueCodec.writeRightZeroExtendedValue(this.out, 16, longBits);
                    return;
                }
                case 17: {
                    EncodedValueCodec.writeRightZeroExtendedValue(this.out, 17, Double.doubleToLongBits(reader.readDouble()));
                    return;
                }
                case 21: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 21, (long)this.this$0.adjustProtoIdIndex(reader.readMethodType()));
                    return;
                }
                case 22: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 22, (long)this.this$0.adjustMethodHandleIndex(reader.readMethodHandle()));
                    return;
                }
                case 23: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 23, (long)this.this$0.adjustStringIndex(reader.readString()));
                    return;
                }
                case 24: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 24, (long)this.this$0.adjustTypeIdIndex(reader.readType()));
                    return;
                }
                case 25: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 25, (long)this.this$0.adjustFieldIdIndex(reader.readField()));
                    return;
                }
                case 27: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 27, (long)this.this$0.adjustFieldIdIndex(reader.readEnum()));
                    return;
                }
                case 26: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 26, (long)this.this$0.adjustMethodIdIndex(reader.readMethod()));
                    return;
                }
                case 28: {
                    this.writeTypeAndArg(28, 0);
                    this.transformArray(reader);
                    return;
                }
                case 29: {
                    this.writeTypeAndArg(29, 0);
                    this.transformAnnotation(reader);
                    return;
                }
                case 30: {
                    reader.readNull();
                    this.writeTypeAndArg(30, 0);
                    return;
                }
                case 31: {
                    boolean value = reader.readBoolean();
                    this.writeTypeAndArg(31, value ? 0 : 1);
                    return;
                }
                default: {
                    throw new DexException(new StringBuilder().append("Unexpected type: ").append(Integer.toHexString(reader.peek())).toString());
                }
            }
        }

        private void transformAnnotation(EncodedValueReader reader) {
            int fieldCount = reader.readAnnotation();
            Leb128.writeUnsignedLeb128(this.out, this.this$0.adjustTypeIdIndex(reader.getAnnotationType()));
            Leb128.writeUnsignedLeb128(this.out, fieldCount);
            for (int i = 0; i < fieldCount; i += 1) {
                Leb128.writeUnsignedLeb128(this.out, this.this$0.adjustStringIndex(reader.readAnnotationName()));
                this.transform(reader);
            }
        }

        private void transformArray(EncodedValueReader reader) {
            int size = reader.readArray();
            Leb128.writeUnsignedLeb128(this.out, size);
            for (int i = 0; i < size; i += 1) {
                this.transform(reader);
            }
        }

        private void writeTypeAndArg(int type, int arg) {
            this.out.writeByte(arg << 5 | type);
        }

        static /* synthetic */ void access$000(AbstractIndexMap$EncodedValueTransformer x0, EncodedValueReader x1) {
            x0.transformArray(x1);
        }

        static /* synthetic */ void access$100(AbstractIndexMap$EncodedValueTransformer x0, EncodedValueReader x1) {
            x0.transformAnnotation(x1);
        }

    }
    // class: com/tencent/tinker/commons/dexpatcher/util/AbstractIndexMap$EncodedValueTransformer
    final class AbstractIndexMap$EncodedValueTransformer {
        final private ByteOutput out;
        final synthetic AbstractIndexMap this$0;

         AbstractIndexMap$EncodedValueTransformer(AbstractIndexMap map, ByteOutput out) {
            this.this$0 = map;
            super();
            this.out = out;
        }

        public void transform(EncodedValueReader reader) {
            switch(reader.peek()) {
                case 0: {
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 0, (long)reader.readByte());
                    return;
                }
                case 2: {
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 2, (long)reader.readShort());
                    return;
                }
                case 4: {
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 4, (long)reader.readInt());
                    return;
                }
                case 6: {
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 6, reader.readLong());
                    return;
                }
                case 3: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 3, (long)reader.readChar());
                    return;
                }
                case 16: {
                    long longBits = (long)Float.floatToIntBits(reader.readFloat()) << 32;
                    EncodedValueCodec.writeRightZeroExtendedValue(this.out, 16, longBits);
                    return;
                }
                case 17: {
                    EncodedValueCodec.writeRightZeroExtendedValue(this.out, 17, Double.doubleToLongBits(reader.readDouble()));
                    return;
                }
                case 21: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 21, (long)this.this$0.adjustProtoIdIndex(reader.readMethodType()));
                    return;
                }
                case 22: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 22, (long)this.this$0.adjustMethodHandleIndex(reader.readMethodHandle()));
                    return;
                }
                case 23: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 23, (long)this.this$0.adjustStringIndex(reader.readString()));
                    return;
                }
                case 24: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 24, (long)this.this$0.adjustTypeIdIndex(reader.readType()));
                    return;
                }
                case 25: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 25, (long)this.this$0.adjustFieldIdIndex(reader.readField()));
                    return;
                }
                case 27: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 27, (long)this.this$0.adjustFieldIdIndex(reader.readEnum()));
                    return;
                }
                case 26: {
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 26, (long)this.this$0.adjustMethodIdIndex(reader.readMethod()));
                    return;
                }
                case 28: {
                    this.writeTypeAndArg(28, 0);
                    this.transformArray(reader);
                    return;
                }
                case 29: {
                    this.writeTypeAndArg(29, 0);
                    this.transformAnnotation(reader);
                    return;
                }
                case 30: {
                    reader.readNull();
                    this.writeTypeAndArg(30, 0);
                    return;
                }
                case 31: {
                    boolean value = reader.readBoolean();
                    this.writeTypeAndArg(31, value ? 0 : 1);
                    return;
                }
                default: {
                    throw new DexException(new StringBuilder().append("Unexpected type: ").append(Integer.toHexString(reader.peek())).toString());
                }
            }
        }

        private void transformAnnotation(EncodedValueReader reader) {
            int fieldCount = reader.readAnnotation();
            Leb128.writeUnsignedLeb128(this.out, this.this$0.adjustTypeIdIndex(reader.getAnnotationType()));
            Leb128.writeUnsignedLeb128(this.out, fieldCount);
            for (int i = 0; i < fieldCount; i += 1) {
                Leb128.writeUnsignedLeb128(this.out, this.this$0.adjustStringIndex(reader.readAnnotationName()));
                this.transform(reader);
            }
        }

        private void transformArray(EncodedValueReader reader) {
            int size = reader.readArray();
            Leb128.writeUnsignedLeb128(this.out, size);
            for (int i = 0; i < size; i += 1) {
                this.transform(reader);
            }
        }

        private void writeTypeAndArg(int type, int arg) {
            this.out.writeByte(arg << 5 | type);
        }

        static /* synthetic */ void access$000(AbstractIndexMap$EncodedValueTransformer x0, EncodedValueReader x1) {
            x0.transformArray(x1);
        }

        static /* synthetic */ void access$100(AbstractIndexMap$EncodedValueTransformer x0, EncodedValueReader x1) {
            x0.transformAnnotation(x1);
        }

    }
}
