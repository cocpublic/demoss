/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.FieldId;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/FieldIdSectionPatchAlgorithm
public class FieldIdSectionPatchAlgorithm {
    private TableOfContents$Section patchedFieldIdTocSec;
    private Dex$Section patchedFieldIdSec;

    public FieldIdSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedFieldIdTocSec = null;
        this.patchedFieldIdSec = null;
        if (patchedDex != null) {
            this.patchedFieldIdTocSec = patchedDex.getTableOfContents().fieldIds;
            this.patchedFieldIdSec = patchedDex.openSection(this.patchedFieldIdTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().fieldIds;
    }

    protected FieldId nextItem(DexDataBuffer section) {
        return section.readFieldId();
    }

    protected int getItemSize(FieldId item) {
        return item.byteCountInDex();
    }

    protected FieldId adjustItem(AbstractIndexMap indexMap, FieldId item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(FieldId patchedItem) {
        this.patchedFieldIdTocSec.size = this.patchedFieldIdTocSec.size + 1;
        return this.patchedFieldIdSec.writeFieldId(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldIndex != newIndex) {
            sparseIndexMap.mapFieldIds(oldIndex, newIndex);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markFieldIdDeleted(deletedIndex);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((FieldId)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (FieldId)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((FieldId)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
