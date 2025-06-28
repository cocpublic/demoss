/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.MethodId;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/MethodIdSectionPatchAlgorithm
public class MethodIdSectionPatchAlgorithm {
    private TableOfContents$Section patchedMethodIdTocSec;
    private Dex$Section patchedMethodIdSec;

    public MethodIdSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedMethodIdTocSec = null;
        this.patchedMethodIdSec = null;
        if (patchedDex != null) {
            this.patchedMethodIdTocSec = patchedDex.getTableOfContents().methodIds;
            this.patchedMethodIdSec = patchedDex.openSection(this.patchedMethodIdTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().methodIds;
    }

    protected MethodId nextItem(DexDataBuffer section) {
        return section.readMethodId();
    }

    protected int getItemSize(MethodId item) {
        return item.byteCountInDex();
    }

    protected MethodId adjustItem(AbstractIndexMap indexMap, MethodId item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(MethodId patchedItem) {
        this.patchedMethodIdTocSec.size = this.patchedMethodIdTocSec.size + 1;
        return this.patchedMethodIdSec.writeMethodId(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldIndex != newIndex) {
            sparseIndexMap.mapMethodIds(oldIndex, newIndex);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markMethodIdDeleted(deletedIndex);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((MethodId)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (MethodId)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((MethodId)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
