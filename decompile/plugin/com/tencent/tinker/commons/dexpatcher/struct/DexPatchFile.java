/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/struct;

import com.tencent.tinker.android.dex.io.DexDataBuffer;
import java.nio.ByteBuffer;

// class: com/tencent/tinker/commons/dexpatcher/struct/DexPatchFile
public final class DexPatchFile {
    final public static short VERSION_02;
    final public static short VERSION_03;
    final public static byte MAGIC;
    final public static short CURRENT_VERSION;
    final private DexDataBuffer buffer;
    private short version;
    private int oldDexAPI;
    private int patchedDexAPI;
    private int patchedDexSize;
    private int firstChunkOffset;
    private int patchedStringIdSectionOffset;
    private int patchedTypeIdSectionOffset;
    private int patchedProtoIdSectionOffset;
    private int patchedFieldIdSectionOffset;
    private int patchedMethodIdSectionOffset;
    private int patchedCallSiteIdSectionOffset;
    private int patchedMethodHandlesSectionOffset;
    private int patchedClassDefSectionOffset;
    private int patchedMapListSectionOffset;
    private int patchedTypeListSectionOffset;
    private int patchedAnnotationSetRefListSectionOffset;
    private int patchedAnnotationSetSectionOffset;
    private int patchedClassDataSectionOffset;
    private int patchedCodeSectionOffset;
    private int patchedStringDataSectionOffset;
    private int patchedDebugInfoSectionOffset;
    private int patchedAnnotationSectionOffset;
    private int patchedEncodedArraySectionOffset;
    private int patchedAnnotationsDirectorySectionOffset;
    private byte oldDexSignature;

    public DexPatchFile(File file) {
        super();
        this.buffer = new DexDataBuffer(ByteBuffer.wrap(FileUtils.readFile(file)));
        this.init();
    }

    public DexPatchFile(InputStream is) {
        super();
        this.buffer = new DexDataBuffer(ByteBuffer.wrap(FileUtils.readStream(is)));
        this.init();
    }

    private void init() {
        byte[] magic = this.buffer.readByteArray(DexPatchFile.MAGIC.length);
        if (CompareUtils.uArrCompare(magic, DexPatchFile.MAGIC) != 0) {
            throw new IllegalStateException(new StringBuilder().append("bad dex patch file magic: ").append(Arrays.toString(magic)).toString());
        }
        else {
            this.version = this.buffer.readShort();
            if (this.version != 2 && this.version != 3) {
                throw new IllegalStateException(new StringBuilder().append("bad dex patch file version: ").append(this.version).toString());
            }
            else {
                if (this.version > 2) {
                    this.oldDexAPI = this.buffer.readInt();
                    this.patchedDexAPI = this.buffer.readInt();
                }
                this.patchedDexSize = this.buffer.readInt();
                this.firstChunkOffset = this.buffer.readInt();
                this.patchedStringIdSectionOffset = this.buffer.readInt();
                this.patchedTypeIdSectionOffset = this.buffer.readInt();
                this.patchedProtoIdSectionOffset = this.buffer.readInt();
                this.patchedFieldIdSectionOffset = this.buffer.readInt();
                this.patchedMethodIdSectionOffset = this.buffer.readInt();
                if (this.version > 2) {
                    this.patchedCallSiteIdSectionOffset = this.buffer.readInt();
                    this.patchedMethodHandlesSectionOffset = this.buffer.readInt();
                }
                this.patchedClassDefSectionOffset = this.buffer.readInt();
                this.patchedMapListSectionOffset = this.buffer.readInt();
                this.patchedTypeListSectionOffset = this.buffer.readInt();
                this.patchedAnnotationSetRefListSectionOffset = this.buffer.readInt();
                this.patchedAnnotationSetSectionOffset = this.buffer.readInt();
                this.patchedClassDataSectionOffset = this.buffer.readInt();
                this.patchedCodeSectionOffset = this.buffer.readInt();
                this.patchedStringDataSectionOffset = this.buffer.readInt();
                this.patchedDebugInfoSectionOffset = this.buffer.readInt();
                this.patchedAnnotationSectionOffset = this.buffer.readInt();
                this.patchedEncodedArraySectionOffset = this.buffer.readInt();
                this.patchedAnnotationsDirectorySectionOffset = this.buffer.readInt();
                this.oldDexSignature = this.buffer.readByteArray(20);
                this.buffer.position(this.firstChunkOffset);
            }
        }
    }

    public short getVersion() {
        return this.version;
    }

    public byte[] getOldDexSignature() {
        return this.oldDexSignature;
    }

    public int getOldDexAPI() {
        return this.oldDexAPI;
    }

    public int getPatchedDexAPI() {
        return this.patchedDexAPI;
    }

    public int getPatchedDexSize() {
        return this.patchedDexSize;
    }

    public int getPatchedStringIdSectionOffset() {
        return this.patchedStringIdSectionOffset;
    }

    public int getPatchedTypeIdSectionOffset() {
        return this.patchedTypeIdSectionOffset;
    }

    public int getPatchedProtoIdSectionOffset() {
        return this.patchedProtoIdSectionOffset;
    }

    public int getPatchedFieldIdSectionOffset() {
        return this.patchedFieldIdSectionOffset;
    }

    public int getPatchedMethodIdSectionOffset() {
        return this.patchedMethodIdSectionOffset;
    }

    public int getPatchedCallSiteIdSectionOffset() {
        return this.patchedCallSiteIdSectionOffset;
    }

    public int getPatchedMethodHandlesSectionOffset() {
        return this.patchedMethodHandlesSectionOffset;
    }

    public int getPatchedClassDefSectionOffset() {
        return this.patchedClassDefSectionOffset;
    }

    public int getPatchedMapListSectionOffset() {
        return this.patchedMapListSectionOffset;
    }

    public int getPatchedTypeListSectionOffset() {
        return this.patchedTypeListSectionOffset;
    }

    public int getPatchedAnnotationSetRefListSectionOffset() {
        return this.patchedAnnotationSetRefListSectionOffset;
    }

    public int getPatchedAnnotationSetSectionOffset() {
        return this.patchedAnnotationSetSectionOffset;
    }

    public int getPatchedClassDataSectionOffset() {
        return this.patchedClassDataSectionOffset;
    }

    public int getPatchedCodeSectionOffset() {
        return this.patchedCodeSectionOffset;
    }

    public int getPatchedStringDataSectionOffset() {
        return this.patchedStringDataSectionOffset;
    }

    public int getPatchedDebugInfoSectionOffset() {
        return this.patchedDebugInfoSectionOffset;
    }

    public int getPatchedAnnotationSectionOffset() {
        return this.patchedAnnotationSectionOffset;
    }

    public int getPatchedEncodedArraySectionOffset() {
        return this.patchedEncodedArraySectionOffset;
    }

    public int getPatchedAnnotationsDirectorySectionOffset() {
        return this.patchedAnnotationsDirectorySectionOffset;
    }

    public DexDataBuffer getBuffer() {
        return this.buffer;
    }

    static  {
        DexPatchFile.MAGIC = new byte[]{68, 88, 68, 73, 70, 70};
    }

}
