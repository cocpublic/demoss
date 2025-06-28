/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.ProtoId;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/ProtoIdSectionPatchAlgorithm
public class ProtoIdSectionPatchAlgorithm {
    private TableOfContents$Section patchedProtoIdTocSec;
    private Dex$Section patchedProtoIdSec;

    public ProtoIdSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedProtoIdTocSec = null;
        this.patchedProtoIdSec = null;
        if (patchedDex != null) {
            this.patchedProtoIdTocSec = patchedDex.getTableOfContents().protoIds;
            this.patchedProtoIdSec = patchedDex.openSection(this.patchedProtoIdTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().protoIds;
    }

    protected ProtoId nextItem(DexDataBuffer section) {
        return section.readProtoId();
    }

    protected int getItemSize(ProtoId item) {
        return item.byteCountInDex();
    }

    protected ProtoId adjustItem(AbstractIndexMap indexMap, ProtoId item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(ProtoId patchedItem) {
        this.patchedProtoIdTocSec.size = this.patchedProtoIdTocSec.size + 1;
        return this.patchedProtoIdSec.writeProtoId(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldIndex != newIndex) {
            sparseIndexMap.mapProtoIds(oldIndex, newIndex);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markProtoIdDeleted(deletedIndex);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((ProtoId)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (ProtoId)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((ProtoId)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
