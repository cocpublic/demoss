/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/TypeIdSectionPatchAlgorithm
public class TypeIdSectionPatchAlgorithm {
    private TableOfContents$Section patchedTypeIdTocSec;
    private Dex$Section patchedTypeIdSec;

    public TypeIdSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedTypeIdTocSec = null;
        this.patchedTypeIdSec = null;
        if (patchedDex != null) {
            this.patchedTypeIdTocSec = patchedDex.getTableOfContents().typeIds;
            this.patchedTypeIdSec = patchedDex.openSection(this.patchedTypeIdTocSec);
        }
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

    protected int writePatchedItem(Integer patchedItem) {
        int off = this.patchedTypeIdSec.position();
        this.patchedTypeIdSec.writeInt(patchedItem.intValue());
        this.patchedTypeIdTocSec.size = this.patchedTypeIdTocSec.size + 1;
        return off;
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldIndex != newIndex) {
            sparseIndexMap.mapTypeIds(oldIndex, newIndex);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markTypeIdDeleted(deletedIndex);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((Integer)comparable);
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
