/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.ClassData;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/ClassDataSectionPatchAlgorithm
public class ClassDataSectionPatchAlgorithm {
    private TableOfContents$Section patchedClassDataTocSec;
    private Dex$Section patchedClassDataSec;

    public ClassDataSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedClassDataTocSec = null;
        this.patchedClassDataSec = null;
        if (patchedDex != null) {
            this.patchedClassDataTocSec = patchedDex.getTableOfContents().classDatas;
            this.patchedClassDataSec = patchedDex.openSection(this.patchedClassDataTocSec);
        }
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

    protected int writePatchedItem(ClassData patchedItem) {
        this.patchedClassDataTocSec.size = this.patchedClassDataTocSec.size + 1;
        return this.patchedClassDataSec.writeClassData(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapClassDataOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markClassDataDeleted(deletedOffset);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((ClassData)comparable);
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
