/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.StringData;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/StringDataSectionPatchAlgorithm
public class StringDataSectionPatchAlgorithm {
    private TableOfContents$Section patchedStringDataTocSec;
    private TableOfContents$Section patchedStringIdTocSec;
    private Dex$Section patchedStringDataSec;
    private Dex$Section patchedStringIdSec;

    public StringDataSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedStringDataTocSec = null;
        this.patchedStringIdTocSec = null;
        this.patchedStringDataSec = null;
        this.patchedStringIdSec = null;
        if (patchedDex != null) {
            this.patchedStringDataTocSec = patchedDex.getTableOfContents().stringDatas;
            this.patchedStringIdTocSec = patchedDex.getTableOfContents().stringIds;
            this.patchedStringDataSec = patchedDex.openSection(this.patchedStringDataTocSec);
            this.patchedStringIdSec = patchedDex.openSection(this.patchedStringIdTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().stringDatas;
    }

    protected StringData nextItem(DexDataBuffer section) {
        return section.readStringData();
    }

    protected int getItemSize(StringData item) {
        return item.byteCountInDex();
    }

    protected int writePatchedItem(StringData patchedItem) {
        int off = this.patchedStringDataSec.writeStringData(patchedItem);
        this.patchedStringIdSec.writeInt(off);
        this.patchedStringDataTocSec.size = this.patchedStringDataTocSec.size + 1;
        this.patchedStringIdTocSec.size = this.patchedStringIdTocSec.size + 1;
        return off;
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldIndex != newIndex) {
            sparseIndexMap.mapStringIds(oldIndex, newIndex);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markStringIdDeleted(deletedIndex);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((StringData)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((StringData)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
