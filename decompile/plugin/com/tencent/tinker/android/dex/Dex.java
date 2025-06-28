/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.Buffer;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.Adler32;
import java.util.Set;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;

// class: com/tencent/tinker/android/dex/Dex
public final class Dex {
    final static short EMPTY_SHORT_ARRAY;
    final private static int CHECKSUM_OFFSET;
    final private static int SIGNATURE_OFFSET;
    final private TableOfContents tableOfContents;
    final private Dex$StringTable strings;
    final private Dex$TypeIndexToDescriptorIndexTable typeIds;
    final private Dex$TypeIndexToDescriptorTable typeNames;
    final private Dex$ProtoIdTable protoIds;
    final private Dex$FieldIdTable fieldIds;
    final private Dex$MethodIdTable methodIds;
    final private Dex$CallSiteIdTable callsiteIds;
    final private Dex$MethodHandleTable methodHandles;
    final private Dex$ClassDefTable classDefs;
    private ByteBuffer data;
    private int nextSectionStart;
    private byte signature;

    public Dex(byte[] data) {
        super(ByteBuffer.wrap(data));
    }

    private Dex(ByteBuffer data) {
        super();
        this.tableOfContents = new TableOfContents();
        this.strings = new Dex$StringTable(this, null);
        this.typeIds = new Dex$TypeIndexToDescriptorIndexTable(this, null);
        this.typeNames = new Dex$TypeIndexToDescriptorTable(this, null);
        this.protoIds = new Dex$ProtoIdTable(this, null);
        this.fieldIds = new Dex$FieldIdTable(this, null);
        this.methodIds = new Dex$MethodIdTable(this, null);
        this.callsiteIds = new Dex$CallSiteIdTable(this, null);
        this.methodHandles = new Dex$MethodHandleTable(this, null);
        this.classDefs = new Dex$ClassDefTable(this, null);
        this.nextSectionStart = 0;
        this.signature = null;
        this.data = data;
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.tableOfContents.readFrom(this);
    }

    public Dex(int byteCount) {
        super();
        this.tableOfContents = new TableOfContents();
        this.strings = new Dex$StringTable(this, null);
        this.typeIds = new Dex$TypeIndexToDescriptorIndexTable(this, null);
        this.typeNames = new Dex$TypeIndexToDescriptorTable(this, null);
        this.protoIds = new Dex$ProtoIdTable(this, null);
        this.fieldIds = new Dex$FieldIdTable(this, null);
        this.methodIds = new Dex$MethodIdTable(this, null);
        this.callsiteIds = new Dex$CallSiteIdTable(this, null);
        this.methodHandles = new Dex$MethodHandleTable(this, null);
        this.classDefs = new Dex$ClassDefTable(this, null);
        this.nextSectionStart = 0;
        this.signature = null;
        this.data = ByteBuffer.wrap(new byte[]{});
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.tableOfContents.fileSize = byteCount;
    }

    public Dex(InputStream in) {
        super();
        this.tableOfContents = new TableOfContents();
        this.strings = new Dex$StringTable(this, null);
        this.typeIds = new Dex$TypeIndexToDescriptorIndexTable(this, null);
        this.typeNames = new Dex$TypeIndexToDescriptorTable(this, null);
        this.protoIds = new Dex$ProtoIdTable(this, null);
        this.fieldIds = new Dex$FieldIdTable(this, null);
        this.methodIds = new Dex$MethodIdTable(this, null);
        this.callsiteIds = new Dex$CallSiteIdTable(this, null);
        this.methodHandles = new Dex$MethodHandleTable(this, null);
        this.classDefs = new Dex$ClassDefTable(this, null);
        this.nextSectionStart = 0;
        this.signature = null;
        this.loadFrom(in);
    }

    public Dex(InputStream in, int initSize) {
        super();
        this.tableOfContents = new TableOfContents();
        this.strings = new Dex$StringTable(this, null);
        this.typeIds = new Dex$TypeIndexToDescriptorIndexTable(this, null);
        this.typeNames = new Dex$TypeIndexToDescriptorTable(this, null);
        this.protoIds = new Dex$ProtoIdTable(this, null);
        this.fieldIds = new Dex$FieldIdTable(this, null);
        this.methodIds = new Dex$MethodIdTable(this, null);
        this.callsiteIds = new Dex$CallSiteIdTable(this, null);
        this.methodHandles = new Dex$MethodHandleTable(this, null);
        this.classDefs = new Dex$ClassDefTable(this, null);
        this.nextSectionStart = 0;
        this.signature = null;
        this.loadFrom(in, initSize);
    }

    public Dex(File file) {
        super();
        this.tableOfContents = new TableOfContents();
        this.strings = new Dex$StringTable(this, null);
        this.typeIds = new Dex$TypeIndexToDescriptorIndexTable(this, null);
        this.typeNames = new Dex$TypeIndexToDescriptorTable(this, null);
        this.protoIds = new Dex$ProtoIdTable(this, null);
        this.fieldIds = new Dex$FieldIdTable(this, null);
        this.methodIds = new Dex$MethodIdTable(this, null);
        this.callsiteIds = new Dex$CallSiteIdTable(this, null);
        this.methodHandles = new Dex$MethodHandleTable(this, null);
        this.classDefs = new Dex$ClassDefTable(this, null);
        this.nextSectionStart = 0;
        this.signature = null;
        if (file == null) {
            throw new IllegalArgumentException("file is null.");
        }
        InputStream stream;
        else if (FileUtils.hasArchiveSuffix(file.getName())) {
            Object zipFile = null;
            try {
                file = new ZipFile(file);
                ZipEntry entry = file.getEntry("classes.dex");
                if (entry != null) {
                    Object inputStream = null;
                    try {
                        stream = file.getInputStream(entry);
                        this.loadFrom(stream, (int)entry.getSize());
                    }
                    finally {
                        Throwable throwable = v_68;
                        if (stream != null) {
                            stream.close();
                        }
                        throw throwable;
                    }
                    try {
                    }
                    catch (Exception var_3_0) {
                    }
                }
                else {
                    throw new DexException(new StringBuilder().append("Expected classes.dex in ").append(file).toString());
                }
            }
            finally {
                Throwable throwableVar1 = v_57;
                if (file != null) {
                    try {
                        file.close();
                    }
                    catch (Exception var_7_0) {
                    }
                }
                throw throwableVar1;
            }
            return;
        }
        else if (file.getName().endsWith(".dex")) {
            Object in = null;
            try {
                stream = new BufferedInputStream(new FileInputStream(file));
                this.loadFrom(stream, (int)file.length());
                try {
                }
                catch (Exception var_3_1) {
                }
            }
            catch (Exception e) {
                throw new DexException(e);
            }
            finally {
                Throwable throwableVar2 = v_96;
                if (stream != null) {
                    try {
                        stream.close();
                    }
                    catch (Exception var_9_0) {
                    }
                }
                throw throwableVar2;
            }
            return;
        }
        else {
            throw new DexException(new StringBuilder().append("unknown output extension: ").append(file).toString());
        }
    }

    private static void checkBounds(int index, int length) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException(new StringBuilder().append("index:").append(index).append(", length=").append(length).toString());
        }
        else {
        }
    }

    private void loadFrom(InputStream in) {
        super.loadFrom(in, 0);
    }

    private void loadFrom(InputStream in, int initSize) {
        byte[] rawData = FileUtils.readStream(in, initSize);
        this.data = ByteBuffer.wrap(rawData);
        this.data.order(ByteOrder.LITTLE_ENDIAN);
        this.tableOfContents.readFrom(this);
    }

    public void writeTo(OutputStream out) {
        byte[] rawData = this.data.array();
        out.write(rawData);
        out.flush();
    }

    public void writeTo(File dexOut) {
        Object out = null;
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(dexOut));
            this.writeTo(stream);
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (Exception var_3_0) {
                }
            }
        }
        catch (Exception e) {
            throw new DexException(e);
        }
        finally {
            Throwable throwable = v_8;
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (Exception var_5_0) {
                }
            }
            throw throwable;
        }
    }

    public TableOfContents getTableOfContents() {
        return this.tableOfContents;
    }

    public Dex$Section openSection(int position) {
        if (position < 0 || position >= this.data.capacity()) {
            throw new IllegalArgumentException(new StringBuilder().append("position=").append(position).append(" length=").append(this.data.capacity()).toString());
        }
        else {
            ByteBuffer sectionData = this.data.duplicate();
            sectionData.order(ByteOrder.LITTLE_ENDIAN);
            sectionData.position(position);
            sectionData.limit(this.data.capacity());
            return new Dex$Section(this, "temp-section", sectionData, null);
        }
    }

    public Dex$Section openSection(TableOfContents$Section tocSec) {
        int position = tocSec.off;
        if (position < 0 || position >= this.data.capacity()) {
            throw new IllegalArgumentException(new StringBuilder().append("position=").append(position).append(" length=").append(this.data.capacity()).toString());
        }
        else {
            ByteBuffer sectionData = this.data.duplicate();
            sectionData.order(ByteOrder.LITTLE_ENDIAN);
            sectionData.position(position);
            sectionData.limit(position + tocSec.byteCount);
            return new Dex$Section(this, "section", sectionData, null);
        }
    }

    public Dex$Section appendSection(int maxByteCount, String name) {
        int limit = this.nextSectionStart + maxByteCount;
        ByteBuffer sectionData = this.data.duplicate();
        sectionData.order(ByteOrder.LITTLE_ENDIAN);
        sectionData.position(this.nextSectionStart);
        sectionData.limit(limit);
        Dex$Section result = new Dex$Section(this, name, sectionData, null);
        this.nextSectionStart = limit;
        return result;
    }

    public int getLength() {
        return this.data.capacity();
    }

    public int getNextSectionStart() {
        return this.nextSectionStart;
    }

    public byte[] getBytes() {
        ByteBuffer data = this.data.duplicate();
        byte[] result = new byte[]{};
        data.position(0);
        data.get(result);
        return result;
    }

    public List<String> strings() {
        return this.strings;
    }

    public List<Integer> typeIds() {
        return this.typeIds;
    }

    public List<String> typeNames() {
        return this.typeNames;
    }

    public List<ProtoId> protoIds() {
        return this.protoIds;
    }

    public List<FieldId> fieldIds() {
        return this.fieldIds;
    }

    public List<MethodId> methodIds() {
        return this.methodIds;
    }

    public List<CallSiteId> callsiteIds() {
        return this.callsiteIds;
    }

    public List<MethodHandle> methodHandles() {
        return this.methodHandles;
    }

    public List<ClassDef> classDefs() {
        return this.classDefs;
    }

    public Iterable<ClassDef> classDefIterable() {
        return new Dex$ClassDefIterable(this, null);
    }

    public ClassData readClassData(ClassDef classDef) {
        int offset = classDef.classDataOffset;
        if (offset == 0) {
            throw new IllegalArgumentException("offset == 0");
        }
        else {
            return this.openSection(offset).readClassData();
        }
    }

    public Code readCode(ClassData$Method method) {
        int offset = method.codeOffset;
        if (offset == 0) {
            throw new IllegalArgumentException("offset == 0");
        }
        else {
            return this.openSection(offset).readCode();
        }
    }

    public byte[] computeSignature(boolean forceRecompute) {
        if (this.signature != null && forceRecompute) {
            return this.signature;
        }
        else {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
            }
            catch (NoSuchAlgorithmException e) {
                throw new AssertionError();
            }
            byte[] buffer = new byte[]{};
            ByteBuffer data = this.data.duplicate();
            data.limit(data.capacity());
            data.position(32);
            while (data.hasRemaining()) {
                int count = Math.min(buffer.length, data.remaining());
                data.get(buffer, 0, count);
                digest.update(buffer, 0, count);
            }
            this.signature = digest.digest();
            return digest.digest();
        }
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder strBuilder = new StringBuilder(bytes.length << 1);
        for (int i1 = 0; i1 < bytes.length; i1 += 1) {
            byte b = bytes[i1];
            strBuilder.append(Hex.u1(b));
        }
        return strBuilder.toString();
    }

    public int computeChecksum() {
        Adler32 adler32 = new Adler32();
        byte[] buffer = new byte[]{};
        ByteBuffer data = this.data.duplicate();
        data.limit(data.capacity());
        data.position(12);
        while (data.hasRemaining()) {
            int count = Math.min(buffer.length, data.remaining());
            data.get(buffer, 0, count);
            adler32.update(buffer, 0, count);
        }
        return (int)adler32.getValue();
    }

    public void writeHashes() {
        this.openSection(12).write(this.computeSignature(true));
        this.openSection(8).writeInt(this.computeChecksum());
    }

    public int nameIndexFromFieldIndex(int fieldIndex) {
        Dex.checkBounds(fieldIndex, this.tableOfContents.fieldIds.size);
        int position = this.tableOfContents.fieldIds.off + 8 * fieldIndex;
        position += 2;
        position += 2;
        return this.data.getInt(position);
    }

    public int findStringIndex(String s) {
        return Collections.binarySearch(this.strings, s);
    }

    public int findTypeIndex(String descriptor) {
        return Collections.binarySearch(this.typeNames, descriptor);
    }

    public int findFieldIndex(FieldId fieldId) {
        return Collections.binarySearch(this.fieldIds, fieldId);
    }

    public int findMethodIndex(MethodId methodId) {
        return Collections.binarySearch(this.methodIds, methodId);
    }

    public int findClassDefIndexFromTypeIndex(int typeIndex) {
        Dex.checkBounds(typeIndex, this.tableOfContents.typeIds.size);
        if (this.tableOfContents.classDefs.exists()) {
            return -1;
        }
        else {
            for (int i = 0; i < this.tableOfContents.classDefs.size; i += 1) {
                if (this.typeIndexFromClassDefIndex(i) == typeIndex) {
                    return i;
                }
                else {
                }
            }
            return -1;
        }
    }

    public int typeIndexFromFieldIndex(int fieldIndex) {
        Dex.checkBounds(fieldIndex, this.tableOfContents.fieldIds.size);
        int position = this.tableOfContents.fieldIds.off + 8 * fieldIndex;
        position += 2;
        return this.data.getShort(position) & 65535;
    }

    public int declaringClassIndexFromMethodIndex(int methodIndex) {
        Dex.checkBounds(methodIndex, this.tableOfContents.methodIds.size);
        int position = this.tableOfContents.methodIds.off + 8 * methodIndex;
        return this.data.getShort(position) & 65535;
    }

    public int nameIndexFromMethodIndex(int methodIndex) {
        Dex.checkBounds(methodIndex, this.tableOfContents.methodIds.size);
        int position = this.tableOfContents.methodIds.off + 8 * methodIndex;
        position += 2;
        position += 2;
        return this.data.getInt(position);
    }

    public short[] parameterTypeIndicesFromMethodIndex(int methodIndex) {
        Dex.checkBounds(methodIndex, this.tableOfContents.methodIds.size);
        int position = this.tableOfContents.methodIds.off + 8 * methodIndex;
        position += 2;
        int protoIndex = this.data.getShort(position) & 65535;
        Dex.checkBounds(protoIndex, this.tableOfContents.protoIds.size);
        position = this.tableOfContents.protoIds.off + 12 * protoIndex;
        position += 4;
        position += 4;
        int parametersOffset = this.data.getInt(position);
        if (parametersOffset == 0) {
            return Dex.EMPTY_SHORT_ARRAY;
        }
        else {
            int size = this.data.getInt(parametersOffset);
            if (size <= 0) {
                throw new AssertionError(new StringBuilder().append("Unexpected parameter type list size: ").append(size).toString());
            }
            else {
                parametersOffset += 4;
                short[] types = new short[]{};
                for (int i = 0; i < size; i += 1) {
                    types[i] = this.data.getShort(parametersOffset);
                    parametersOffset += 2;
                }
                return types;
            }
        }
    }

    public short[] parameterTypeIndicesFromMethodId(MethodId methodId) {
        int protoIndex = methodId.protoIndex & 65535;
        Dex.checkBounds(protoIndex, this.tableOfContents.protoIds.size);
        int position = this.tableOfContents.protoIds.off + 12 * protoIndex;
        position += 4;
        position += 4;
        int parametersOffset = this.data.getInt(position);
        if (parametersOffset == 0) {
            return Dex.EMPTY_SHORT_ARRAY;
        }
        else {
            int size = this.data.getInt(parametersOffset);
            if (size <= 0) {
                throw new AssertionError(new StringBuilder().append("Unexpected parameter type list size: ").append(size).toString());
            }
            else {
                parametersOffset += 4;
                short[] types = new short[]{};
                for (int i = 0; i < size; i += 1) {
                    types[i] = this.data.getShort(parametersOffset);
                    parametersOffset += 2;
                }
                return types;
            }
        }
    }

    public int returnTypeIndexFromMethodIndex(int methodIndex) {
        Dex.checkBounds(methodIndex, this.tableOfContents.methodIds.size);
        int position = this.tableOfContents.methodIds.off + 8 * methodIndex;
        position += 2;
        int protoIndex = this.data.getShort(position) & 65535;
        Dex.checkBounds(protoIndex, this.tableOfContents.protoIds.size);
        position = this.tableOfContents.protoIds.off + 12 * protoIndex;
        position += 4;
        return this.data.getInt(position);
    }

    public int descriptorIndexFromTypeIndex(int typeIndex) {
        Dex.checkBounds(typeIndex, this.tableOfContents.typeIds.size);
        int position = this.tableOfContents.typeIds.off + 4 * typeIndex;
        return this.data.getInt(position);
    }

    public int typeIndexFromClassDefIndex(int classDefIndex) {
        Dex.checkBounds(classDefIndex, this.tableOfContents.classDefs.size);
        int position = this.tableOfContents.classDefs.off + 32 * classDefIndex;
        return this.data.getInt(position);
    }

    public int annotationDirectoryOffsetFromClassDefIndex(int classDefIndex) {
        Dex.checkBounds(classDefIndex, this.tableOfContents.classDefs.size);
        int position = this.tableOfContents.classDefs.off + 32 * classDefIndex;
        position += 4;
        position += 4;
        position += 4;
        position += 4;
        position += 4;
        return this.data.getInt(position);
    }

    public short[] interfaceTypeIndicesFromClassDefIndex(int classDefIndex) {
        Dex.checkBounds(classDefIndex, this.tableOfContents.classDefs.size);
        int position = this.tableOfContents.classDefs.off + 32 * classDefIndex;
        position += 4;
        position += 4;
        position += 4;
        int interfacesOffset = this.data.getInt(position);
        if (interfacesOffset == 0) {
            return Dex.EMPTY_SHORT_ARRAY;
        }
        else {
            int size = this.data.getInt(interfacesOffset);
            if (size <= 0) {
                throw new AssertionError(new StringBuilder().append("Unexpected interfaces list size: ").append(size).toString());
            }
            else {
                interfacesOffset += 4;
                short[] types = new short[]{};
                for (int i = 0; i < size; i += 1) {
                    types[i] = this.data.getShort(interfacesOffset);
                    interfacesOffset += 2;
                }
                return types;
            }
        }
    }

    public short[] interfaceTypeIndicesFromClassDef(ClassDef classDef) {
        int position = classDef.off;
        position += 4;
        position += 4;
        position += 4;
        int interfacesOffset = this.data.getInt(position);
        if (interfacesOffset == 0) {
            return Dex.EMPTY_SHORT_ARRAY;
        }
        else {
            int size = this.data.getInt(interfacesOffset);
            if (size <= 0) {
                throw new AssertionError(new StringBuilder().append("Unexpected interfaces list size: ").append(size).toString());
            }
            else {
                interfacesOffset += 4;
                short[] types = new short[]{};
                for (int i = 0; i < size; i += 1) {
                    types[i] = this.data.getShort(interfacesOffset);
                    interfacesOffset += 2;
                }
                return types;
            }
        }
    }

    static /* synthetic */ TableOfContents access$1100(Dex x0) {
        return x0.tableOfContents;
    }

    static /* synthetic */ void access$1200(int x0, int x1) {
        Dex.checkBounds(x0, x1);
    }

    static /* synthetic */ Dex$StringTable access$1300(Dex x0) {
        return x0.strings;
    }

    static  {
        Dex.EMPTY_SHORT_ARRAY = new short[]{};
    }

    // class: com/tencent/tinker/android/dex/Dex$TypeIndexToDescriptorTable
    final class Dex$TypeIndexToDescriptorTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$TypeIndexToDescriptorTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public String get(int index) {
            return Dex.access$1300(this.this$0).get(this.this$0.descriptorIndexFromTypeIndex(index));
        }

        public int size() {
            return Dex.access$1100(this.this$0).typeIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$TypeIndexToDescriptorTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$TypeIndexToDescriptorTable
    final class Dex$TypeIndexToDescriptorTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$TypeIndexToDescriptorTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public String get(int index) {
            return Dex.access$1300(this.this$0).get(this.this$0.descriptorIndexFromTypeIndex(index));
        }

        public int size() {
            return Dex.access$1100(this.this$0).typeIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$TypeIndexToDescriptorTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$MethodHandleTable
    final class Dex$MethodHandleTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$MethodHandleTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public MethodHandle get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).methodHandles.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).methodHandles.off + 8 * index).readMethodHandle();
        }

        public int size() {
            return Dex.access$1100(this.this$0).methodHandles.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$MethodHandleTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$MethodHandleTable
    final class Dex$MethodHandleTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$MethodHandleTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public MethodHandle get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).methodHandles.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).methodHandles.off + 8 * index).readMethodHandle();
        }

        public int size() {
            return Dex.access$1100(this.this$0).methodHandles.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$MethodHandleTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$CallSiteIdTable
    final class Dex$CallSiteIdTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$CallSiteIdTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public CallSiteId get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).callSiteIds.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).callSiteIds.off + 4 * index).readCallSiteId();
        }

        public int size() {
            return Dex.access$1100(this.this$0).callSiteIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$CallSiteIdTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$CallSiteIdTable
    final class Dex$CallSiteIdTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$CallSiteIdTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public CallSiteId get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).callSiteIds.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).callSiteIds.off + 4 * index).readCallSiteId();
        }

        public int size() {
            return Dex.access$1100(this.this$0).callSiteIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$CallSiteIdTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$MethodIdTable
    final class Dex$MethodIdTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$MethodIdTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public MethodId get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).methodIds.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).methodIds.off + 8 * index).readMethodId();
        }

        public int size() {
            return Dex.access$1100(this.this$0).methodIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$MethodIdTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$MethodIdTable
    final class Dex$MethodIdTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$MethodIdTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public MethodId get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).methodIds.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).methodIds.off + 8 * index).readMethodId();
        }

        public int size() {
            return Dex.access$1100(this.this$0).methodIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$MethodIdTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$ProtoIdTable
    final class Dex$ProtoIdTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$ProtoIdTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public ProtoId get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).protoIds.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).protoIds.off + 12 * index).readProtoId();
        }

        public int size() {
            return Dex.access$1100(this.this$0).protoIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$ProtoIdTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$ProtoIdTable
    final class Dex$ProtoIdTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$ProtoIdTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public ProtoId get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).protoIds.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).protoIds.off + 12 * index).readProtoId();
        }

        public int size() {
            return Dex.access$1100(this.this$0).protoIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$ProtoIdTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$TypeIndexToDescriptorIndexTable
    final class Dex$TypeIndexToDescriptorIndexTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$TypeIndexToDescriptorIndexTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public Integer get(int index) {
            return Integer.valueOf(this.this$0.descriptorIndexFromTypeIndex(index));
        }

        public int size() {
            return Dex.access$1100(this.this$0).typeIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$TypeIndexToDescriptorIndexTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$TypeIndexToDescriptorIndexTable
    final class Dex$TypeIndexToDescriptorIndexTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$TypeIndexToDescriptorIndexTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public Integer get(int index) {
            return Integer.valueOf(this.this$0.descriptorIndexFromTypeIndex(index));
        }

        public int size() {
            return Dex.access$1100(this.this$0).typeIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$TypeIndexToDescriptorIndexTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$ClassDefIterable
    final class Dex$ClassDefIterable implements Iterable<ClassDef> {
        final synthetic Dex this$0;

        private Dex$ClassDefIterable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public Iterator<ClassDef> iterator() {
            if (Dex.access$1100(this.this$0).classDefs.exists()) {
                return Collections.emptySet().iterator();
            }
            else {
                return new Dex$ClassDefIterator(this.this$0, null);
            }
        }

        /* synthetic */ Dex$ClassDefIterable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$ClassDefIterable
    final class Dex$ClassDefIterable implements Iterable<ClassDef> {
        final synthetic Dex this$0;

        private Dex$ClassDefIterable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public Iterator<ClassDef> iterator() {
            if (Dex.access$1100(this.this$0).classDefs.exists()) {
                return Collections.emptySet().iterator();
            }
            else {
                return new Dex$ClassDefIterator(this.this$0, null);
            }
        }

        /* synthetic */ Dex$ClassDefIterable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$StringTable
    final class Dex$StringTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$StringTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public String get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).stringIds.size);
            int stringOff = this.this$0.openSection(Dex.access$1100(this.this$0).stringIds.off + index * 4).readInt();
            return this.this$0.openSection(stringOff).readStringData().value;
        }

        public int size() {
            return Dex.access$1100(this.this$0).stringIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$StringTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$StringTable
    final class Dex$StringTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$StringTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public String get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).stringIds.size);
            int stringOff = this.this$0.openSection(Dex.access$1100(this.this$0).stringIds.off + index * 4).readInt();
            return this.this$0.openSection(stringOff).readStringData().value;
        }

        public int size() {
            return Dex.access$1100(this.this$0).stringIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$StringTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$ClassDefIterator
    final class Dex$ClassDefIterator implements Iterator<ClassDef> {
        final private Dex$Section in;
        private int count;
        final synthetic Dex this$0;

        private Dex$ClassDefIterator(Dex dex) {
            this.this$0 = dex;
            super();
            this.in = this.this$0.openSection(Dex.access$1100(this.this$0).classDefs);
            this.count = 0;
        }

        public boolean hasNext() {
            if (this.count < Dex.access$1100(this.this$0).classDefs.size) {
                return true;
            }
            else {
                return false;
            }
        }

        public ClassDef next() {
            if (this.hasNext()) {
                throw new NoSuchElementException();
            }
            else {
                this.count = this.count + 1;
                return this.in.readClassDef();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public /* synthetic */ Object next() {
            return this.next();
        }

        /* synthetic */ Dex$ClassDefIterator(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$ClassDefIterator
    final class Dex$ClassDefIterator implements Iterator<ClassDef> {
        final private Dex$Section in;
        private int count;
        final synthetic Dex this$0;

        private Dex$ClassDefIterator(Dex dex) {
            this.this$0 = dex;
            super();
            this.in = this.this$0.openSection(Dex.access$1100(this.this$0).classDefs);
            this.count = 0;
        }

        public boolean hasNext() {
            if (this.count < Dex.access$1100(this.this$0).classDefs.size) {
                return true;
            }
            else {
                return false;
            }
        }

        public ClassDef next() {
            if (this.hasNext()) {
                throw new NoSuchElementException();
            }
            else {
                this.count = this.count + 1;
                return this.in.readClassDef();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public /* synthetic */ Object next() {
            return this.next();
        }

        /* synthetic */ Dex$ClassDefIterator(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$Section
    public final class Dex$Section {
        final private String name;
        final synthetic Dex this$0;

        private Dex$Section(Dex this$0, String name, ByteBuffer data) {
            this.this$0 = this$0;
            super(data);
            this.name = name;
        }

        public StringData readStringData() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).stringDatas, 0);
            return super.readStringData();
        }

        public TypeList readTypeList() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).typeLists, 0);
            return super.readTypeList();
        }

        public FieldId readFieldId() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).fieldIds, 0);
            return super.readFieldId();
        }

        public MethodId readMethodId() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).methodIds, 0);
            return super.readMethodId();
        }

        public ProtoId readProtoId() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).protoIds, 0);
            return super.readProtoId();
        }

        public ClassDef readClassDef() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).classDefs, 0);
            return super.readClassDef();
        }

        public Code readCode() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).codes, 0);
            return super.readCode();
        }

        public DebugInfoItem readDebugInfoItem() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).debugInfos, 0);
            return super.readDebugInfoItem();
        }

        public ClassData readClassData() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).classDatas, 0);
            return super.readClassData();
        }

        public Annotation readAnnotation() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotations, 0);
            return super.readAnnotation();
        }

        public AnnotationSet readAnnotationSet() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationSets, 0);
            return super.readAnnotationSet();
        }

        public AnnotationSetRefList readAnnotationSetRefList() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationSetRefLists, 0);
            return super.readAnnotationSetRefList();
        }

        public AnnotationsDirectory readAnnotationsDirectory() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationsDirectories, 0);
            return super.readAnnotationsDirectory();
        }

        public EncodedValue readEncodedArray() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).encodedArrays, 0);
            return super.readEncodedArray();
        }

        private void ensureFourBytesAligned(TableOfContents$Section tocSec, boolean isFillWithZero) {
            if (tocSec.isElementFourByteAligned) {
                if (isFillWithZero) {
                    this.alignToFourBytesWithZeroFill();
                }
                else {
                    this.alignToFourBytes();
                }
            }
        }

        public int writeStringData(StringData stringData) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).stringDatas, 1);
            return super.writeStringData(stringData);
        }

        public int writeTypeList(TypeList typeList) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).typeLists, 1);
            return super.writeTypeList(typeList);
        }

        public int writeFieldId(FieldId fieldId) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).fieldIds, 1);
            return super.writeFieldId(fieldId);
        }

        public int writeMethodId(MethodId methodId) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).methodIds, 1);
            return super.writeMethodId(methodId);
        }

        public int writeProtoId(ProtoId protoId) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).protoIds, 1);
            return super.writeProtoId(protoId);
        }

        public int writeClassDef(ClassDef classDef) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).classDefs, 1);
            return super.writeClassDef(classDef);
        }

        public int writeCode(Code code) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).codes, 1);
            return super.writeCode(code);
        }

        public int writeDebugInfoItem(DebugInfoItem debugInfoItem) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).debugInfos, 1);
            return super.writeDebugInfoItem(debugInfoItem);
        }

        public int writeClassData(ClassData classData) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).classDatas, 1);
            return super.writeClassData(classData);
        }

        public int writeAnnotation(Annotation annotation) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotations, 1);
            return super.writeAnnotation(annotation);
        }

        public int writeAnnotationSet(AnnotationSet annotationSet) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationSets, 1);
            return super.writeAnnotationSet(annotationSet);
        }

        public int writeAnnotationSetRefList(AnnotationSetRefList annotationSetRefList) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationSetRefLists, 1);
            return super.writeAnnotationSetRefList(annotationSetRefList);
        }

        public int writeAnnotationsDirectory(AnnotationsDirectory annotationsDirectory) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationsDirectories, 1);
            return super.writeAnnotationsDirectory(annotationsDirectory);
        }

        public int writeEncodedArray(EncodedValue encodedValue) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).encodedArrays, 1);
            return super.writeEncodedArray(encodedValue);
        }

        /* synthetic */ Dex$Section(Dex x0, String x1, ByteBuffer x2, Dex$1 x3) {
            super(x0, x1, x2);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$Section
    public final class Dex$Section {
        final private String name;
        final synthetic Dex this$0;

        private Dex$Section(Dex this$0, String name, ByteBuffer data) {
            this.this$0 = this$0;
            super(data);
            this.name = name;
        }

        public StringData readStringData() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).stringDatas, 0);
            return super.readStringData();
        }

        public TypeList readTypeList() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).typeLists, 0);
            return super.readTypeList();
        }

        public FieldId readFieldId() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).fieldIds, 0);
            return super.readFieldId();
        }

        public MethodId readMethodId() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).methodIds, 0);
            return super.readMethodId();
        }

        public ProtoId readProtoId() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).protoIds, 0);
            return super.readProtoId();
        }

        public ClassDef readClassDef() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).classDefs, 0);
            return super.readClassDef();
        }

        public Code readCode() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).codes, 0);
            return super.readCode();
        }

        public DebugInfoItem readDebugInfoItem() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).debugInfos, 0);
            return super.readDebugInfoItem();
        }

        public ClassData readClassData() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).classDatas, 0);
            return super.readClassData();
        }

        public Annotation readAnnotation() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotations, 0);
            return super.readAnnotation();
        }

        public AnnotationSet readAnnotationSet() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationSets, 0);
            return super.readAnnotationSet();
        }

        public AnnotationSetRefList readAnnotationSetRefList() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationSetRefLists, 0);
            return super.readAnnotationSetRefList();
        }

        public AnnotationsDirectory readAnnotationsDirectory() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationsDirectories, 0);
            return super.readAnnotationsDirectory();
        }

        public EncodedValue readEncodedArray() {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).encodedArrays, 0);
            return super.readEncodedArray();
        }

        private void ensureFourBytesAligned(TableOfContents$Section tocSec, boolean isFillWithZero) {
            if (tocSec.isElementFourByteAligned) {
                if (isFillWithZero) {
                    this.alignToFourBytesWithZeroFill();
                }
                else {
                    this.alignToFourBytes();
                }
            }
        }

        public int writeStringData(StringData stringData) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).stringDatas, 1);
            return super.writeStringData(stringData);
        }

        public int writeTypeList(TypeList typeList) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).typeLists, 1);
            return super.writeTypeList(typeList);
        }

        public int writeFieldId(FieldId fieldId) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).fieldIds, 1);
            return super.writeFieldId(fieldId);
        }

        public int writeMethodId(MethodId methodId) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).methodIds, 1);
            return super.writeMethodId(methodId);
        }

        public int writeProtoId(ProtoId protoId) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).protoIds, 1);
            return super.writeProtoId(protoId);
        }

        public int writeClassDef(ClassDef classDef) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).classDefs, 1);
            return super.writeClassDef(classDef);
        }

        public int writeCode(Code code) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).codes, 1);
            return super.writeCode(code);
        }

        public int writeDebugInfoItem(DebugInfoItem debugInfoItem) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).debugInfos, 1);
            return super.writeDebugInfoItem(debugInfoItem);
        }

        public int writeClassData(ClassData classData) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).classDatas, 1);
            return super.writeClassData(classData);
        }

        public int writeAnnotation(Annotation annotation) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotations, 1);
            return super.writeAnnotation(annotation);
        }

        public int writeAnnotationSet(AnnotationSet annotationSet) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationSets, 1);
            return super.writeAnnotationSet(annotationSet);
        }

        public int writeAnnotationSetRefList(AnnotationSetRefList annotationSetRefList) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationSetRefLists, 1);
            return super.writeAnnotationSetRefList(annotationSetRefList);
        }

        public int writeAnnotationsDirectory(AnnotationsDirectory annotationsDirectory) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).annotationsDirectories, 1);
            return super.writeAnnotationsDirectory(annotationsDirectory);
        }

        public int writeEncodedArray(EncodedValue encodedValue) {
            this.ensureFourBytesAligned(Dex.access$1100(this.this$0).encodedArrays, 1);
            return super.writeEncodedArray(encodedValue);
        }

        /* synthetic */ Dex$Section(Dex x0, String x1, ByteBuffer x2, Dex$1 x3) {
            super(x0, x1, x2);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$FieldIdTable
    final class Dex$FieldIdTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$FieldIdTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public FieldId get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).fieldIds.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).fieldIds.off + 8 * index).readFieldId();
        }

        public int size() {
            return Dex.access$1100(this.this$0).fieldIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$FieldIdTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$FieldIdTable
    final class Dex$FieldIdTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$FieldIdTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public FieldId get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).fieldIds.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).fieldIds.off + 8 * index).readFieldId();
        }

        public int size() {
            return Dex.access$1100(this.this$0).fieldIds.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$FieldIdTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$ClassDefTable
    final class Dex$ClassDefTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$ClassDefTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public ClassDef get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).classDefs.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).classDefs.off + 32 * index).readClassDef();
        }

        public int size() {
            return Dex.access$1100(this.this$0).classDefs.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$ClassDefTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/android/dex/Dex$ClassDefTable
    final class Dex$ClassDefTable implements RandomAccess {
        final synthetic Dex this$0;

        private Dex$ClassDefTable(Dex dex) {
            this.this$0 = dex;
            super();
        }

        public ClassDef get(int index) {
            Dex.access$1200(index, Dex.access$1100(this.this$0).classDefs.size);
            return this.this$0.openSection(Dex.access$1100(this.this$0).classDefs.off + 32 * index).readClassDef();
        }

        public int size() {
            return Dex.access$1100(this.this$0).classDefs.size;
        }

        public /* synthetic */ Object get(int i0) {
            return this.get(i0);
        }

        /* synthetic */ Dex$ClassDefTable(Dex x0, Dex$1 x1) {
            super(x0);
        }

    }
}
