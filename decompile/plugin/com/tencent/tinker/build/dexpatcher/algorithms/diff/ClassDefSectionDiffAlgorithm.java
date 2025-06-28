/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher/algorithms/diff;

import java.util.HashSet;
import java.util.Set;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.ClassDef;

// class: com/tencent/tinker/build/dexpatcher/algorithms/diff/ClassDefSectionDiffAlgorithm
public class ClassDefSectionDiffAlgorithm {
    private Set<Integer> typeIdOfClassDefToRemoveSet;

    public ClassDefSectionDiffAlgorithm(Dex oldDex, Dex newDex, SparseIndexMap oldToNewIndexMap, SparseIndexMap oldToPatchedIndexMap, SparseIndexMap newToPatchedIndexMap, SparseIndexMap selfIndexMapForSkip) {
        super(oldDex, newDex, oldToNewIndexMap, oldToPatchedIndexMap, newToPatchedIndexMap, selfIndexMapForSkip);
        this.typeIdOfClassDefToRemoveSet = new HashSet();
    }

    public void setTypeIdOfClassDefsToRemove(Collection<Integer> typeIdOfClassDefsToRemove) {
        this.typeIdOfClassDefToRemoveSet.clear();
        this.typeIdOfClassDefToRemoveSet.addAll(typeIdOfClassDefsToRemove);
    }

    public void clearTypeIdOfClassDefsToRemove() {
        this.typeIdOfClassDefToRemoveSet.clear();
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().classDefs;
    }

    protected ClassDef nextItem(DexDataBuffer section) {
        return section.readClassDef();
    }

    protected boolean shouldSkipInNewDex(ClassDef newItem) {
        return this.typeIdOfClassDefToRemoveSet.contains(Integer.valueOf(newItem.typeIndex));
    }

    protected int getItemSize(ClassDef item) {
        return 32;
    }

    protected ClassDef adjustItem(AbstractIndexMap indexMap, ClassDef item) {
        return indexMap.adjust(item);
    }

    protected /* synthetic */ boolean shouldSkipInNewDex(Comparable comparable) {
        return this.shouldSkipInNewDex((ClassDef)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (ClassDef)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((ClassDef)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
