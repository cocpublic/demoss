/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher/algorithms/diff;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;

// class: com/tencent/tinker/build/dexpatcher/algorithms/diff/TypeIdSectionDiffAlgorithm
public class TypeIdSectionDiffAlgorithm {

    public TypeIdSectionDiffAlgorithm(Dex oldDex, Dex newDex, SparseIndexMap oldToNewIndexMap, SparseIndexMap oldToPatchedIndexMap, SparseIndexMap newToPatchedIndexMap, SparseIndexMap selfIndexMapForSkip) {
        super(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().typeIds;
    }

    protected Integer nextItem(DexDataBuffer section) {
        return Integer.valueOf(section.readInt());
    }

    protected int getItemSize(Integer item) {
        return 4;
    }

    protected Integer adjustItem(AbstractIndexMap indexMap, Integer item) {
        return Integer.valueOf(indexMap.adjustStringIndex(item.intValue()));
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldIndex != newIndex) {
            sparseIndexMap.mapTypeIds(oldIndex, newIndex);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markTypeIdDeleted(deletedIndex);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (Integer)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((Integer)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
