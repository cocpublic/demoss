/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.Code;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/CodeSectionPatchAlgorithm
public class CodeSectionPatchAlgorithm {
    private TableOfContents$Section patchedCodeTocSec;
    private Dex$Section patchedCodeSec;

    public CodeSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedCodeTocSec = null;
        this.patchedCodeSec = null;
        if (patchedDex != null) {
            this.patchedCodeTocSec = patchedDex.getTableOfContents().codes;
            this.patchedCodeSec = patchedDex.openSection(this.patchedCodeTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().codes;
    }

    protected Code nextItem(DexDataBuffer section) {
        return section.readCode();
    }

    protected int getItemSize(Code item) {
        return item.byteCountInDex();
    }

    protected Code adjustItem(AbstractIndexMap indexMap, Code item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(Code patchedItem) {
        this.patchedCodeTocSec.size = this.patchedCodeTocSec.size + 1;
        return this.patchedCodeSec.writeCode(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapCodeOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markCodeDeleted(deletedOffset);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((Code)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (Code)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((Code)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
