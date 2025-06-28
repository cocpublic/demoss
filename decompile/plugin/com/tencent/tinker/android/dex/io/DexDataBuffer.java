/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex/io;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.Buffer;
import java.io.ByteArrayOutputStream;
import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dex.StringData;
import com.tencent.tinker.android.dex.TypeList;
import com.tencent.tinker.android.dex.FieldId;
import com.tencent.tinker.android.dex.MethodId;
import com.tencent.tinker.android.dex.ProtoId;
import com.tencent.tinker.android.dex.CallSiteId;
import com.tencent.tinker.android.dex.MethodHandle$MethodHandleType;
import com.tencent.tinker.android.dex.MethodHandle;
import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.android.dex.Code$CatchHandler[];
import com.tencent.tinker.android.dex.Code$Try[];
import com.tencent.tinker.android.dex.Code;
import com.tencent.tinker.android.dex.Code$Try;
import com.tencent.tinker.android.dex.Code$CatchHandler;
import com.tencent.tinker.android.dex.DebugInfoItem;
import com.tencent.tinker.android.dex.ClassData$Field[];
import com.tencent.tinker.android.dex.ClassData$Method[];
import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.ClassData$Field;
import com.tencent.tinker.android.dex.ClassData$Method;
import com.tencent.tinker.android.dex.EncodedValueReader;
import com.tencent.tinker.android.dex.Annotation;
import com.tencent.tinker.android.dex.EncodedValue;
import com.tencent.tinker.android.dex.AnnotationSet;
import com.tencent.tinker.android.dex.AnnotationSetRefList;
import com.tencent.tinker.android.dex.AnnotationsDirectory;

// class: com/tencent/tinker/android/dex/io/DexDataBuffer
public class DexDataBuffer implements ByteInput, ByteOutput {
    final public static int DEFAULT_BUFFER_SIZE;
    final private static short EMPTY_SHORT_ARRAY;
    final private static Code$Try[] EMPTY_TRY_ARRAY;
    final private static Code$CatchHandler[] EMPTY_CATCHHANDLER_ARRAY;
    private ByteBuffer data;
    private int dataBound;
    private boolean isResizeAllowed;

    public DexDataBuffer() {
        super();
        this.data = ByteBuffer.allocate(512);
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.dataBound = this.data.position();
        this.data.limit(this.data.capacity());
        this.isResizeAllowed = true;
    }

    public DexDataBuffer(ByteBuffer data) {
        super();
        this.data = data;
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.dataBound = data.limit();
        this.isResizeAllowed = false;
    }

    public DexDataBuffer(ByteBuffer data, boolean isResizeAllowed) {
        super();
        this.data = data;
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.dataBound = data.limit();
        this.isResizeAllowed = isResizeAllowed;
    }

    public int position() {
        return this.data.position();
    }

    public void position(int pos) {
        this.data.position(pos);
    }

    public int available() {
        return this.dataBound - this.data.position();
    }

    private void ensureBufferSize(int bytes) {
        if (this.data.position() + bytes > this.data.limit() && this.isResizeAllowed) {
            byte[] array = this.data.array();
            byte[] newArray = new byte[]{};
            System.arraycopy(array, 0, newArray, 0, this.data.position());
            int lastPos = this.data.position();
            this.data = ByteBuffer.wrap(newArray);
            this.data.order(ByteOrder.LITTLE_ENDIAN);
            this.data.position(lastPos);
            this.data.limit(this.data.capacity());
        }
    }

    public byte[] array() {
        byte[] result = new byte[]{};
        byte[] dataArray = this.data.array();
        System.arraycopy(dataArray, 0, result, 0, this.dataBound);
        return result;
    }

    public byte readByte() {
        return this.data.get();
    }

    public int readUnsignedByte() {
        return this.readByte() & 255;
    }

    public short readShort() {
        return this.data.getShort();
    }

    public int readUnsignedShort() {
        return this.readShort() & 65535;
    }

    public int readInt() {
        return this.data.getInt();
    }

    public byte[] readByteArray(int length) {
        byte[] result = new byte[]{};
        this.data.get(result);
        return result;
    }

    public short[] readShortArray(int length) {
        if (length == 0) {
            return DexDataBuffer.EMPTY_SHORT_ARRAY;
        }
        else {
            short[] result = new short[]{};
            for (int i = 0; i < length; i += 1) {
                result[i] = this.readShort();
            }
            return result;
        }
    }

    public int readUleb128() {
        return Leb128.readUnsignedLeb128(this);
    }

    public int readUleb128p1() {
        return Leb128.readUnsignedLeb128(this) - 1;
    }

    public int readSleb128() {
        return Leb128.readSignedLeb128(this);
    }

    public StringData readStringData() {
        int off = this.data.position();
        try {
            int expectedLength = this.readUleb128();
            String result = Mutf8.decode(this, new char[]{});
            if (result.length() != expectedLength) {
                throw new DexException(new StringBuilder().append("Declared length ").append(expectedLength).append(" doesn't match decoded length of ").append(result.length()).toString());
            }
            else {
                return new StringData(off, result);
            }
        }
        catch (UTFDataFormatException e) {
            throw new DexException(e);
        }
    }

    public TypeList readTypeList() {
        int off = this.data.position();
        int size = this.readInt();
        short[] types = this.readShortArray(size);
        return new TypeList(off, types);
    }

    public FieldId readFieldId() {
        int off = this.data.position();
        int declaringClassIndex = this.readUnsignedShort();
        int typeIndex = this.readUnsignedShort();
        int nameIndex = this.readInt();
        return new FieldId(off, declaringClassIndex, typeIndex, nameIndex);
    }

    public MethodId readMethodId() {
        int off = this.data.position();
        int declaringClassIndex = this.readUnsignedShort();
        int protoIndex = this.readUnsignedShort();
        int nameIndex = this.readInt();
        return new MethodId(off, declaringClassIndex, protoIndex, nameIndex);
    }

    public ProtoId readProtoId() {
        int off = this.data.position();
        int shortyIndex = this.readInt();
        int returnTypeIndex = this.readInt();
        int parametersOffset = this.readInt();
        return new ProtoId(off, shortyIndex, returnTypeIndex, parametersOffset);
    }

    public CallSiteId readCallSiteId() {
        int off = this.data.position();
        int callsiteOffset = this.readInt();
        return new CallSiteId(off, callsiteOffset);
    }

    public MethodHandle readMethodHandle() {
        int off = this.data.position();
        MethodHandle$MethodHandleType methodHandleType = MethodHandle$MethodHandleType.fromValue(this.readUnsignedShort());
        int unused1 = this.readUnsignedShort();
        int fieldOrMethodId = this.readUnsignedShort();
        int unused2 = this.readUnsignedShort();
        return new MethodHandle(off, methodHandleType, unused1, fieldOrMethodId, unused2);
    }

    public ClassDef readClassDef() {
        int off = this.position();
        int type = this.readInt();
        int accessFlags = this.readInt();
        int supertype = this.readInt();
        int interfacesOffset = this.readInt();
        int sourceFileIndex = this.readInt();
        int annotationsOffset = this.readInt();
        int classDataOffset = this.readInt();
        int staticValuesOffset = this.readInt();
        return new ClassDef(off, type, accessFlags, supertype, interfacesOffset, sourceFileIndex, annotationsOffset, classDataOffset, staticValuesOffset);
    }

    public Code readCode() {
        int off = this.data.position();
        int registersSize = this.readUnsignedShort();
        int insSize = this.readUnsignedShort();
        int outsSize = this.readUnsignedShort();
        int triesSize = this.readUnsignedShort();
        int debugInfoOffset = this.readInt();
        int instructionsSize = this.readInt();
        short[] instructions = this.readShortArray(instructionsSize);
        Code$CatchHandler[] catchHandlers;
        Code$Try[] tries;
        if (triesSize > 0) {
            if (instructions.length & 1 == 1) {
                this.skip(2);
            }
            int posBeforeTries = this.data.position();
            this.skip(triesSize * 8);
            catchHandlers = this.readCatchHandlers();
            int posAfterCatchHandlers = this.data.position();
            this.data.position(posBeforeTries);
            tries = this.readTries(triesSize, catchHandlers);
            this.data.position(posAfterCatchHandlers);
        }
        else {
        }
        return new Code(off, registersSize, insSize, outsSize, debugInfoOffset, instructions, DexDataBuffer.EMPTY_TRY_ARRAY, DexDataBuffer.EMPTY_CATCHHANDLER_ARRAY);
    }

    private Code$CatchHandler[] readCatchHandlers() {
        int baseOffset = this.data.position();
        int catchHandlersSize = this.readUleb128();
        Code$CatchHandler result = new Code$CatchHandler[]{};
        for (int i = 0; i < catchHandlersSize; i += 1) {
            int offset = this.data.position() - baseOffset;
            result[i] = this.readCatchHandler(offset);
        }
        return result;
    }

    private Code$Try[] readTries(int triesSize, Code$CatchHandler[] catchHandlers) {
        Code$Try result = new Code$Try[]{};
        for (int i = 0; i < triesSize; i += 1) {
            int startAddress = this.readInt();
            int instructionCount = this.readUnsignedShort();
            int handlerOffset = this.readUnsignedShort();
            int catchHandlerIndex = this.findCatchHandlerIndex(catchHandlers, handlerOffset);
            result[i] = new Code$Try(startAddress, instructionCount, catchHandlerIndex);
        }
        return result;
    }

    private int findCatchHandlerIndex(Code$CatchHandler[] catchHandlers, int offset) {
        for (int i = 0; i < catchHandlers.length; i += 1) {
            Code$CatchHandler catchHandler = catchHandlers[i];
            if (catchHandler.offset == offset) {
                return i;
            }
            else {
            }
        }
        throw new IllegalArgumentException();
    }

    private Code$CatchHandler readCatchHandler(int offset) {
        int size = this.readSleb128();
        int handlersCount = Math.abs(size);
        int[] typeIndexes = new int[]{};
        int[] addresses = new int[]{};
        for (int i = 0; i < handlersCount; i += 1) {
            typeIndexes[i] = this.readUleb128();
            addresses[i] = this.readUleb128();
        }
        int i0 = size <= 0 ? -1 : this.readUleb128();
        return new Code$CatchHandler(typeIndexes, addresses, i0, offset);
    }

    public DebugInfoItem readDebugInfoItem() {
        int off = this.data.position();
        int lineStart = this.readUleb128();
        int parametersSize = this.readUleb128();
        int[] parameterNames = new int[]{};
        for (int i = 0; i < parametersSize; i += 1) {
            parameterNames[i] = this.readUleb128p1();
        }
        Object baos = null;
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream(64);
            DexDataBuffer$1 outAdapter = new DexDataBuffer$1(this, stream);
            while (true) {
byte opcode = this.readByte();
stream.write(opcode);
                switch(opcode) {
                    case 0: {
                        break;;
                        byte[] infoSTM = stream.toByteArray();
                        DebugInfoItem item = new DebugInfoItem(off, lineStart, parameterNames, infoSTM);
                        if (stream != null) {
                            try {
                                stream.close();
                            }
                            catch (Exception var_10_0) {
                            }
                        }
                        return item;
                    }
                    case 1: {
                        int addrDiff = this.readUleb128();
                        Leb128.writeUnsignedLeb128(outAdapter, addrDiff);
                        continue;;
                    }
                    case 2: {
                        int lineDiff = this.readSleb128();
                        Leb128.writeSignedLeb128(outAdapter, lineDiff);
                        continue;;
                    }
                    int registerNum;
                    int nameIndex;
                    case 3: {
                        registerNum = this.readUleb128();
                        Leb128.writeUnsignedLeb128(outAdapter, registerNum);
                        nameIndex = this.readUleb128p1();
                        Leb128.writeUnsignedLeb128p1(outAdapter, nameIndex);
                        int typeIndex = this.readUleb128p1();
                        Leb128.writeUnsignedLeb128p1(outAdapter, typeIndex);
                        if (opcode == 4) {
                            int sigIndex = this.readUleb128p1();
                            Leb128.writeUnsignedLeb128p1(outAdapter, sigIndex);
                            continue;;
                        }
                    }
                    case 5: {
                        registerNum = this.readUleb128();
                        Leb128.writeUnsignedLeb128(outAdapter, registerNum);
                        continue;;
                    }
                    case 9: {
                        nameIndex = this.readUleb128p1();
                        Leb128.writeUnsignedLeb128p1(outAdapter, nameIndex);
                        continue;;
                    }
                }
            }
        }
        finally {
            Throwable throwable = v_23;
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (Exception var_14_0) {
                }
            }
            throw throwable;
        }
    }

    public ClassData readClassData() {
        int off = this.data.position();
        int staticFieldsSize = this.readUleb128();
        int instanceFieldsSize = this.readUleb128();
        int directMethodsSize = this.readUleb128();
        int virtualMethodsSize = this.readUleb128();
        ClassData$Field[] staticFields = this.readFields(staticFieldsSize);
        ClassData$Field[] instanceFields = this.readFields(instanceFieldsSize);
        ClassData$Method[] directMethods = this.readMethods(directMethodsSize);
        ClassData$Method[] virtualMethods = this.readMethods(virtualMethodsSize);
        return new ClassData(off, staticFields, instanceFields, directMethods, virtualMethods);
    }

    private ClassData$Field[] readFields(int count) {
        ClassData$Field result = new ClassData$Field[]{};
        int fieldIndex = 0;
        for (int i = 0; i < count; i += 1) {
            fieldIndex += this.readUleb128();
            int accessFlags = this.readUleb128();
            result[i] = new ClassData$Field(fieldIndex, accessFlags);
        }
        return result;
    }

    private ClassData$Method[] readMethods(int count) {
        ClassData$Method result = new ClassData$Method[]{};
        int methodIndex = 0;
        for (int i = 0; i < count; i += 1) {
            methodIndex += this.readUleb128();
            int accessFlags = this.readUleb128();
            int codeOff = this.readUleb128();
            result[i] = new ClassData$Method(methodIndex, accessFlags, codeOff);
        }
        return result;
    }

    private byte[] getBytesFrom(int start) {
        int end = this.data.position();
        byte[] result = new byte[]{};
        this.data.position(start);
        this.data.get(result);
        return result;
    }

    public Annotation readAnnotation() {
        int off = this.data.position();
        byte visibility = this.readByte();
        int start = this.data.position();
        new EncodedValueReader(this, 29).skipValue();
        return new Annotation(off, visibility, new EncodedValue(start, this.getBytesFrom(start)));
    }

    public AnnotationSet readAnnotationSet() {
        int off = this.data.position();
        int size = this.readInt();
        int[] annotationOffsets = new int[]{};
        for (int i = 0; i < size; i += 1) {
            annotationOffsets[i] = this.readInt();
        }
        return new AnnotationSet(off, annotationOffsets);
    }

    public AnnotationSetRefList readAnnotationSetRefList() {
        int off = this.data.position();
        int size = this.readInt();
        int[] annotationSetRefItems = new int[]{};
        for (int i = 0; i < size; i += 1) {
            annotationSetRefItems[i] = this.readInt();
        }
        return new AnnotationSetRefList(off, annotationSetRefItems);
    }

    public AnnotationsDirectory readAnnotationsDirectory() {
        int off = this.data.position();
        int classAnnotationsOffset = this.readInt();
        int fieldsSize = this.readInt();
        int methodsSize = this.readInt();
        int parameterListSize = this.readInt();
        v_16 = fieldsSize;
        int[][] fieldAnnotations = new int[][][]{};
        int i = 0;
        while (true) {
            v_16 = fieldsSize;
            if (i < v_16) {
                fieldAnnotations[i][0] = this.readInt();
                fieldAnnotations[i][1] = this.readInt();
                i += 1;
            }
            else {
                v_34 = methodsSize;
                int[][] methodAnnotations = new int[][][]{};
                i = 0;
            }
        }
        while (true) {
            v_34 = methodsSize;
            if (i < v_34) {
                methodAnnotations[i][0] = this.readInt();
                methodAnnotations[i][1] = this.readInt();
                i += 1;
            }
            else {
                v_52 = parameterListSize;
                int[][] parameterAnnotations = new int[][][]{};
                i = 0;
            }
        }
        while (true) {
            v_52 = parameterListSize;
            if (i < v_52) {
                parameterAnnotations[i][0] = this.readInt();
                parameterAnnotations[i][1] = this.readInt();
                i += 1;
            }
            else {
                return new AnnotationsDirectory(off, classAnnotationsOffset, fieldAnnotations, methodAnnotations, parameterAnnotations);
            }
        }
    }

    public EncodedValue readEncodedArray() {
        int start = this.data.position();
        new EncodedValueReader(this, 28).skipValue();
        return new EncodedValue(start, this.getBytesFrom(start));
    }

    public void skip(int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.data.position(this.data.position() + count);
        }
    }

    public void skipWithAutoExpand(int count) {
        this.ensureBufferSize(1 * count);
        this.skip(count);
    }

    public void alignToFourBytes() {
        this.data.position(this.data.position() + 3 & 252);
    }

    public void alignToFourBytesWithZeroFill() {
        int alignedPos = SizeOf.roundToTimesOfFour(this.data.position());
        this.ensureBufferSize(alignedPos - this.data.position() * 1);
        while (this.data.position() & 3 != 0) {
            this.data.put(0);
        }
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void writeByte(int b) {
        this.ensureBufferSize(1);
        this.data.put((byte)b);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void writeShort(short i) {
        this.ensureBufferSize(2);
        this.data.putShort(i);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void writeUnsignedShort(int i) {
        int s = (short)i;
        if (i != s & 65535) {
            throw new IllegalArgumentException(new StringBuilder().append("Expected an unsigned short: ").append(i).toString());
        }
        else {
            this.writeShort(s);
        }
    }

    public void writeInt(int i) {
        this.ensureBufferSize(4);
        this.data.putInt(i);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void write(byte[] bytes) {
        this.ensureBufferSize(bytes.length * 1);
        this.data.put(bytes);
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void write(short[] shorts) {
        this.ensureBufferSize(shorts.length * 2);
        for (int i1 = 0; i1 < shorts.length; i1 += 1) {
            short s = shorts[i1];
            this.writeShort(s);
        }
        if (this.data.position() > this.dataBound) {
            this.dataBound = this.data.position();
        }
    }

    public void writeUleb128(int i) {
        Leb128.writeUnsignedLeb128(this, i);
    }

    public void writeUleb128p1(int i) {
        this.writeUleb128(i + 1);
    }

    public void writeSleb128(int i) {
        Leb128.writeSignedLeb128(this, i);
    }

    public int writeStringData(StringData stringData) {
        int off = this.data.position();
        try {
            int length = stringData.value.length();
            this.writeUleb128(length);
            this.write(Mutf8.encode(stringData.value));
            this.writeByte(0);
            return off;
        }
        catch (UTFDataFormatException e) {
            throw new AssertionError(e);
        }
    }

    public int writeTypeList(TypeList typeList) {
        int off = this.data.position();
        short[] types = typeList.types;
        this.writeInt(types.length);
        for (int i1 = 0; i1 < types.length; i1 += 1) {
            short type = types[i1];
            this.writeShort(type);
        }
        return off;
    }

    public int writeFieldId(FieldId fieldId) {
        int off = this.data.position();
        this.writeUnsignedShort(fieldId.declaringClassIndex);
        this.writeUnsignedShort(fieldId.typeIndex);
        this.writeInt(fieldId.nameIndex);
        return off;
    }

    public int writeMethodId(MethodId methodId) {
        int off = this.data.position();
        this.writeUnsignedShort(methodId.declaringClassIndex);
        this.writeUnsignedShort(methodId.protoIndex);
        this.writeInt(methodId.nameIndex);
        return off;
    }

    public int writeProtoId(ProtoId protoId) {
        int off = this.data.position();
        this.writeInt(protoId.shortyIndex);
        this.writeInt(protoId.returnTypeIndex);
        this.writeInt(protoId.parametersOffset);
        return off;
    }

    public int writeCallSiteId(CallSiteId callSiteId) {
        int off = this.data.position();
        this.writeInt(callSiteId.offset);
        return off;
    }

    public int writeMethodHandle(MethodHandle methodHandle) {
        int off = this.data.position();
        this.writeUnsignedShort(methodHandle.methodHandleType.value);
        this.writeUnsignedShort(methodHandle.unused1);
        this.writeUnsignedShort(methodHandle.fieldOrMethodId);
        this.writeUnsignedShort(methodHandle.unused2);
        return off;
    }

    public int writeClassDef(ClassDef classDef) {
        int off = this.data.position();
        this.writeInt(classDef.typeIndex);
        this.writeInt(classDef.accessFlags);
        this.writeInt(classDef.supertypeIndex);
        this.writeInt(classDef.interfacesOffset);
        this.writeInt(classDef.sourceFileIndex);
        this.writeInt(classDef.annotationsOffset);
        this.writeInt(classDef.classDataOffset);
        this.writeInt(classDef.staticValuesOffset);
        return off;
    }

    public int writeCode(Code code) {
        int off = this.data.position();
        this.writeUnsignedShort(code.registersSize);
        this.writeUnsignedShort(code.insSize);
        this.writeUnsignedShort(code.outsSize);
        this.writeUnsignedShort(code.tries.length);
        this.writeInt(code.debugInfoOffset);
        this.writeInt(code.instructions.length);
        this.write(code.instructions);
        if (code.tries.length > 0) {
            if (code.instructions.length & 1 == 1) {
                this.writeShort(0);
            }
            int posBeforeTries = this.data.position();
            this.skipWithAutoExpand(code.tries.length * 8);
            int[] offsets = this.writeCatchHandlers(code.catchHandlers);
            int posAfterCatchHandlers = this.data.position();
            this.data.position(posBeforeTries);
            this.writeTries(code.tries, offsets);
            this.data.position(posAfterCatchHandlers);
        }
        return off;
    }

    private int[] writeCatchHandlers(Code$CatchHandler[] catchHandlers) {
        int baseOffset = this.data.position();
        this.writeUleb128(catchHandlers.length);
        int[] offsets = new int[]{};
        for (int i = 0; i < catchHandlers.length; i += 1) {
            offsets[i] = this.data.position() - baseOffset;
            this.writeCatchHandler(catchHandlers[i]);
        }
        return offsets;
    }

    private void writeCatchHandler(Code$CatchHandler catchHandler) {
        int catchAllAddress = catchHandler.catchAllAddress;
        int[] typeIndexes = catchHandler.typeIndexes;
        int[] addresses = catchHandler.addresses;
        if (catchAllAddress != -1) {
            this.writeSleb128(- typeIndexes.length);
        }
        else {
            this.writeSleb128(typeIndexes.length);
        }
        for (int i = 0; i < typeIndexes.length; i += 1) {
            this.writeUleb128(typeIndexes[i]);
            this.writeUleb128(addresses[i]);
        }
        if (catchAllAddress != -1) {
            this.writeUleb128(catchAllAddress);
        }
    }

    private void writeTries(Code$Try[] tries, int[] catchHandlerOffsets) {
        for (int i1 = 0; i1 < tries.length; i1 += 1) {
            Code$Try tryItem = tries[i1];
            this.writeInt(tryItem.startAddress);
            this.writeUnsignedShort(tryItem.instructionCount);
            this.writeUnsignedShort(catchHandlerOffsets[tryItem.catchHandlerIndex]);
        }
    }

    public int writeDebugInfoItem(DebugInfoItem debugInfoItem) {
        int off = this.data.position();
        this.writeUleb128(debugInfoItem.lineStart);
        this.writeUleb128(debugInfoItem.parameterNames.length);
        for (int i = 0; i < debugInfoItem.parameterNames.length; i += 1) {
            int parameterName = debugInfoItem.parameterNames[i];
            this.writeUleb128p1(parameterName);
        }
        this.write(debugInfoItem.infoSTM);
        return off;
    }

    public int writeClassData(ClassData classData) {
        int off = this.data.position();
        this.writeUleb128(classData.staticFields.length);
        this.writeUleb128(classData.instanceFields.length);
        this.writeUleb128(classData.directMethods.length);
        this.writeUleb128(classData.virtualMethods.length);
        this.writeFields(classData.staticFields);
        this.writeFields(classData.instanceFields);
        this.writeMethods(classData.directMethods);
        this.writeMethods(classData.virtualMethods);
        return off;
    }

    private void writeFields(ClassData$Field[] fields) {
        int lastOutFieldIndex = 0;
        for (int i1 = 0; i1 < fields.length; i1 += 1) {
            ClassData$Field field = fields[i1];
            this.writeUleb128(field.fieldIndex - lastOutFieldIndex);
            lastOutFieldIndex = field.fieldIndex;
            this.writeUleb128(field.accessFlags);
        }
    }

    private void writeMethods(ClassData$Method[] methods) {
        int lastOutMethodIndex = 0;
        for (int i1 = 0; i1 < methods.length; i1 += 1) {
            ClassData$Method method = methods[i1];
            this.writeUleb128(method.methodIndex - lastOutMethodIndex);
            lastOutMethodIndex = method.methodIndex;
            this.writeUleb128(method.accessFlags);
            this.writeUleb128(method.codeOffset);
        }
    }

    public int writeAnnotation(Annotation annotation) {
        int off = this.data.position();
        this.writeByte(annotation.visibility);
        this.writeEncodedArray(annotation.encodedAnnotation);
        return off;
    }

    public int writeAnnotationSet(AnnotationSet annotationSet) {
        int off = this.data.position();
        this.writeInt(annotationSet.annotationOffsets.length);
        int[] intArr0 = annotationSet.annotationOffsets;
        for (int i1 = 0; i1 < intArr0.length; i1 += 1) {
            int annotationOffset = intArr0[i1];
            this.writeInt(annotationOffset);
        }
        return off;
    }

    public int writeAnnotationSetRefList(AnnotationSetRefList annotationSetRefList) {
        int off = this.data.position();
        this.writeInt(annotationSetRefList.annotationSetRefItems.length);
        int[] intArr0 = annotationSetRefList.annotationSetRefItems;
        for (int i1 = 0; i1 < intArr0.length; i1 += 1) {
            int annotationSetRefItem = intArr0[i1];
            this.writeInt(annotationSetRefItem);
        }
        return off;
    }

    public int writeAnnotationsDirectory(AnnotationsDirectory annotationsDirectory) {
        int off = this.data.position();
        this.writeInt(annotationsDirectory.classAnnotationsOffset);
        this.writeInt(annotationsDirectory.fieldAnnotations.length);
        this.writeInt(annotationsDirectory.methodAnnotations.length);
        this.writeInt(annotationsDirectory.parameterAnnotations.length);
        int[][] int[]Arr0Var2 = annotationsDirectory.fieldAnnotations;
        for (int i5 = 0; i5 < int[]Arr0Var2.length; i5 += 1) {
            int[] fieldAnnotation = int[]Arr0Var2[i5];
            this.writeInt(fieldAnnotation[0]);
            this.writeInt(fieldAnnotation[1]);
        }
        int[]Arr0Var2 = annotationsDirectory.methodAnnotations;
        i4 = int[]Arr0Var2.length;
        for (i5 = 0; i5 < int[]Arr0Var2.length; i5 += 1) {
            int[] methodAnnotation = int[]Arr0Var2[i5];
            this.writeInt(methodAnnotation[0]);
            this.writeInt(methodAnnotation[1]);
        }
        int[]Arr0Var2 = annotationsDirectory.parameterAnnotations;
        i4 = int[]Arr0Var2.length;
        for (i5 = 0; i5 < int[]Arr0Var2.length; i5 += 1) {
            int[] parameterAnnotation = int[]Arr0Var2[i5];
            this.writeInt(parameterAnnotation[0]);
            this.writeInt(parameterAnnotation[1]);
        }
        return off;
    }

    public int writeEncodedArray(EncodedValue encodedValue) {
        int off = this.data.position();
        this.write(encodedValue.data);
        return off;
    }

    static  {
        DexDataBuffer.EMPTY_SHORT_ARRAY = new short[]{};
        DexDataBuffer.EMPTY_TRY_ARRAY = new Code$Try[]{};
        DexDataBuffer.EMPTY_CATCHHANDLER_ARRAY = new Code$CatchHandler[]{};
    }

}
