/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher/algorithms/diff;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.EncodedValue;

// class: com/tencent/tinker/build/dexpatcher/algorithms/diff/StaticValueSectionDiffAlgorithm
public class StaticValueSectionDiffAlgorithm {

    public StaticValueSectionDiffAlgorithm(Dex oldDex, Dex newDex, SparseIndexMap oldToNewIndexMap, SparseIndexMap oldToPatchedIndexMap, SparseIndexMap newToPatchedIndexMap, SparseIndexMap selfIndexMapForSkip) {
        super(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().encodedArrays;
    }

    protected EncodedValue nextItem(DexDataBuffer section) {
        return section.readEncodedArray();
    }

    protected int getItemSize(EncodedValue item) {
        return item.byteCountInDex();
    }

    protected EncodedValue adjustItem(AbstractIndexMap indexMap, EncodedValue item) {
        return indexMap.adjust(item);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapStaticValuesOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markStaticValuesDeleted(deletedOffset);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (EncodedValue)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((EncodedValue)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
