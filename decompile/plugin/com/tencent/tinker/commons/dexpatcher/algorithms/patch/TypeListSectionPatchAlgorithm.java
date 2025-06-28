/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.TypeList;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/TypeListSectionPatchAlgorithm
public class TypeListSectionPatchAlgorithm {
    private TableOfContents$Section patchedTypeListTocSec;
    private Dex$Section patchedTypeListSec;

    public TypeListSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedTypeListTocSec = null;
        this.patchedTypeListSec = null;
        if (patchedDex != null) {
            this.patchedTypeListTocSec = patchedDex.getTableOfContents().typeLists;
            this.patchedTypeListSec = patchedDex.openSection(this.patchedTypeListTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().typeLists;
    }

    protected TypeList nextItem(DexDataBuffer section) {
        return section.readTypeList();
    }

    protected int getItemSize(TypeList item) {
        return item.byteCountInDex();
    }

    protected TypeList adjustItem(AbstractIndexMap indexMap, TypeList item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(TypeList patchedItem) {
        this.patchedTypeListTocSec.size = this.patchedTypeListTocSec.size + 1;
        return this.patchedTypeListSec.writeTypeList(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapTypeListOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markTypeListDeleted(deletedOffset);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((TypeList)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (TypeList)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((TypeList)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
