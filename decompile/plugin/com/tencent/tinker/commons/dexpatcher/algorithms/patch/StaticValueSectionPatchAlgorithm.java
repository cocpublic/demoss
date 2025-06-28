/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.EncodedValue;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/StaticValueSectionPatchAlgorithm
public class StaticValueSectionPatchAlgorithm {
    private TableOfContents$Section patchedEncodedValueTocSec;
    private Dex$Section patchedEncodedValueSec;

    public StaticValueSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedEncodedValueTocSec = null;
        this.patchedEncodedValueSec = null;
        if (patchedDex != null) {
            this.patchedEncodedValueTocSec = patchedDex.getTableOfContents().encodedArrays;
            this.patchedEncodedValueSec = patchedDex.openSection(this.patchedEncodedValueTocSec);
        }
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

    protected int writePatchedItem(EncodedValue patchedItem) {
        this.patchedEncodedValueTocSec.size = this.patchedEncodedValueTocSec.size + 1;
        return this.patchedEncodedValueSec.writeEncodedArray(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapStaticValuesOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markStaticValuesDeleted(deletedOffset);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((EncodedValue)comparable);
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
