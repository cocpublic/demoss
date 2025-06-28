/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher/algorithms/diff;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.DebugInfoItem;

// class: com/tencent/tinker/build/dexpatcher/algorithms/diff/DebugInfoItemSectionDiffAlgorithm
public class DebugInfoItemSectionDiffAlgorithm {

    public DebugInfoItemSectionDiffAlgorithm(Dex oldDex, Dex newDex, SparseIndexMap oldToNewIndexMap, SparseIndexMap oldToPatchedIndexMap, SparseIndexMap newToPatchedIndexMap, SparseIndexMap selfIndexMapForSkip) {
        super(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
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

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapDebugInfoItemOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markDebugInfoItemDeleted(deletedOffset);
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
