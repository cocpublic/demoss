/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher/algorithms/diff;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.AnnotationSetRefList;

// class: com/tencent/tinker/build/dexpatcher/algorithms/diff/AnnotationSetRefListSectionDiffAlgorithm
public class AnnotationSetRefListSectionDiffAlgorithm {

    public AnnotationSetRefListSectionDiffAlgorithm(Dex oldDex, Dex newDex, SparseIndexMap oldToNewIndexMap, SparseIndexMap oldToPatchedIndexMap, SparseIndexMap newToPatchedIndexMap, SparseIndexMap selfIndexMapForSkip) {
        super(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().annotationSetRefLists;
    }

    protected AnnotationSetRefList nextItem(DexDataBuffer section) {
        return section.readAnnotationSetRefList();
    }

    protected int getItemSize(AnnotationSetRefList item) {
        return item.byteCountInDex();
    }

    protected AnnotationSetRefList adjustItem(AbstractIndexMap indexMap, AnnotationSetRefList item) {
        return indexMap.adjust(item);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapAnnotationSetRefListOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markAnnotationSetRefListDeleted(deletedOffset);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (AnnotationSetRefList)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((AnnotationSetRefList)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
