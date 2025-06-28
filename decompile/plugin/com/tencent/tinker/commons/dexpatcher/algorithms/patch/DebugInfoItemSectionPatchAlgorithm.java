/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.DebugInfoItem;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/DebugInfoItemSectionPatchAlgorithm
public class DebugInfoItemSectionPatchAlgorithm {
    private TableOfContents$Section patchedDebugInfoItemTocSec;
    private Dex$Section patchedDebugInfoItemSec;

    public DebugInfoItemSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedDebugInfoItemTocSec = null;
        this.patchedDebugInfoItemSec = null;
        if (patchedDex != null) {
            this.patchedDebugInfoItemTocSec = patchedDex.getTableOfContents().debugInfos;
            this.patchedDebugInfoItemSec = patchedDex.openSection(this.patchedDebugInfoItemTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().debugInfos;
    }

    protected DebugInfoItem nextItem(DexDataBuffer section) {
        return section.readDebugInfoItem();
    }

    protected int getItemSize(DebugInfoItem item) {
        return item.byteCountInDex();
    }

    protected DebugInfoItem adjustItem(AbstractIndexMap indexMap, DebugInfoItem item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(DebugInfoItem patchedItem) {
        this.patchedDebugInfoItemTocSec.size = this.patchedDebugInfoItemTocSec.size + 1;
        return this.patchedDebugInfoItemSec.writeDebugInfoItem(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapDebugInfoItemOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markDebugInfoItemDeleted(deletedOffset);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((DebugInfoItem)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (DebugInfoItem)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((DebugInfoItem)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
