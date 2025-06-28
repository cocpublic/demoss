/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher/algorithms/diff;

import java.util.HashSet;
import java.util.Set;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.Dex;

// class: com/tencent/tinker/build/dexpatcher/algorithms/diff/ClassDataSectionDiffAlgorithm
public class ClassDataSectionDiffAlgorithm {
    private Set<Integer> offsetOfClassDataToRemoveSet;

    public ClassDataSectionDiffAlgorithm(Dex oldDex, Dex newDex, SparseIndexMap oldToNewIndexMap, SparseIndexMap oldToPatchedIndexMap, SparseIndexMap newToPatchedIndexMap, SparseIndexMap selfIndexMapForSkip) {
        super(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.offsetOfClassDataToRemoveSet = new HashSet();
    }

    public void setOffsetOfClassDatasToRemove(Collection<Integer> offsetOfClassDatasToRemove) {
        this.offsetOfClassDataToRemoveSet.clear();
        this.offsetOfClassDataToRemoveSet.addAll(offsetOfClassDatasToRemove);
    }

    public void clearTypeIdOfClassDefsToRemove() {
        this.offsetOfClassDataToRemoveSet.clear();
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().classDatas;
    }

    protected ClassData nextItem(DexDataBuffer section) {
        return section.readClassData();
    }

    protected int getItemSize(ClassData item) {
        return item.byteCountInDex();
    }

    protected ClassData adjustItem(AbstractIndexMap indexMap, ClassData item) {
        return indexMap.adjust(item);
    }

    public int getPatchedSectionSize() {
        return super.getPatchedSectionSize() + this.newDex.getTableOfContents().classDatas.size * 2;
    }

    protected boolean shouldSkipInNewDex(ClassData newItem) {
        return this.offsetOfClassDataToRemoveSet.contains(Integer.valueOf(newItem.off));
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapClassDataOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markClassDataDeleted(deletedOffset);
    }

    protected /* synthetic */ boolean shouldSkipInNewDex(Comparable comparable) {
        return this.shouldSkipInNewDex((ClassData)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (ClassData)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((ClassData)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
