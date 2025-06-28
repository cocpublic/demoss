/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher/algorithms/diff;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.MethodId;

// class: com/tencent/tinker/build/dexpatcher/algorithms/diff/MethodIdSectionDiffAlgorithm
public class MethodIdSectionDiffAlgorithm {

    public MethodIdSectionDiffAlgorithm(Dex oldDex, Dex newDex, SparseIndexMap oldToNewIndexMap, SparseIndexMap oldToPatchedIndexMap, SparseIndexMap newToPatchedIndexMap, SparseIndexMap selfIndexMapForSkip) {
        super(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().methodIds;
    }

    protected MethodId nextItem(DexDataBuffer section) {
        return section.readMethodId();
    }

    protected int getItemSize(MethodId item) {
        return 8;
    }

    protected MethodId adjustItem(AbstractIndexMap indexMap, MethodId item) {
        return indexMap.adjust(item);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldIndex != newIndex) {
            sparseIndexMap.mapMethodIds(oldIndex, newIndex);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markMethodIdDeleted(deletedIndex);
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
