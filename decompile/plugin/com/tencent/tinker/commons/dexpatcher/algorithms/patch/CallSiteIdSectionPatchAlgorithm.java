/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.CallSiteId;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/CallSiteIdSectionPatchAlgorithm
public class CallSiteIdSectionPatchAlgorithm {
    private TableOfContents$Section patchedCallSiteIdTocSec;
    private Dex$Section patchedCallSiteIdSec;

    public CallSiteIdSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedCallSiteIdTocSec = null;
        this.patchedCallSiteIdSec = null;
        this.patchedCallSiteIdTocSec = patchedDex.getTableOfContents().callSiteIds;
        this.patchedCallSiteIdSec = patchedDex.openSection(this.patchedCallSiteIdTocSec);
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().callSiteIds;
    }

    protected CallSiteId nextItem(DexDataBuffer section) {
        return section.readCallSiteId();
    }

    protected int getItemSize(CallSiteId item) {
        return item.byteCountInDex();
    }

    protected CallSiteId adjustItem(AbstractIndexMap indexMap, CallSiteId item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(CallSiteId patchedItem) {
        this.patchedCallSiteIdTocSec.size = this.patchedCallSiteIdTocSec.size + 1;
        return this.patchedCallSiteIdSec.writeCallSiteId(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldIndex != newIndex) {
            sparseIndexMap.mapCallsiteIds(oldIndex, newIndex);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markCallsiteDeleted(deletedIndex);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((CallSiteId)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (CallSiteId)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((CallSiteId)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
