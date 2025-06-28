/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.MethodHandle;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/MethodHandleSectionPatchAlgorithm
public class MethodHandleSectionPatchAlgorithm {
    private TableOfContents$Section patchedMethodHandleTocSec;
    private Dex$Section patchedMethodHandleSec;

    public MethodHandleSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedMethodHandleTocSec = null;
        this.patchedMethodHandleSec = null;
        this.patchedMethodHandleTocSec = patchedDex.getTableOfContents().methodHandles;
        this.patchedMethodHandleSec = patchedDex.openSection(this.patchedMethodHandleTocSec);
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().methodHandles;
    }

    protected MethodHandle nextItem(DexDataBuffer section) {
        return section.readMethodHandle();
    }

    protected int getItemSize(MethodHandle item) {
        return item.byteCountInDex();
    }

    protected MethodHandle adjustItem(AbstractIndexMap indexMap, MethodHandle item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(MethodHandle patchedItem) {
        this.patchedMethodHandleTocSec.size = this.patchedMethodHandleTocSec.size + 1;
        return this.patchedMethodHandleSec.writeMethodHandle(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldIndex != newIndex) {
            sparseIndexMap.mapMethodHandleIds(oldIndex, newIndex);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markMethodHandleDeleted(deletedIndex);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((MethodHandle)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (MethodHandle)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((MethodHandle)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
