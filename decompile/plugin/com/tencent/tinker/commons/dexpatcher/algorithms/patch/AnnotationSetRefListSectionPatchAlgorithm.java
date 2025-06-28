/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.AnnotationSetRefList;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/AnnotationSetRefListSectionPatchAlgorithm
public class AnnotationSetRefListSectionPatchAlgorithm {
    private TableOfContents$Section patchedAnnotationSetRefListTocSec;
    private Dex$Section patchedAnnotationSetRefListSec;

    public AnnotationSetRefListSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedAnnotationSetRefListTocSec = null;
        this.patchedAnnotationSetRefListSec = null;
        if (patchedDex != null) {
            this.patchedAnnotationSetRefListTocSec = patchedDex.getTableOfContents().annotationSetRefLists;
            this.patchedAnnotationSetRefListSec = patchedDex.openSection(this.patchedAnnotationSetRefListTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().annotationSetRefLists;
    }

    protected AnnotationSetRefList nextItem(DexDataBuffer section) {
        return section.readAnnotationSetRefList();
    }

    protected int getItemSize(AnnotationSetRefList item) {
        return item.byteCountInDex();
    }

    protected AnnotationSetRefList adjustItem(AbstractIndexMap indexMap, AnnotationSetRefList item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(AnnotationSetRefList patchedItem) {
        this.patchedAnnotationSetRefListTocSec.size = this.patchedAnnotationSetRefListTocSec.size + 1;
        return this.patchedAnnotationSetRefListSec.writeAnnotationSetRefList(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapAnnotationSetRefListOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markAnnotationSetRefListDeleted(deletedOffset);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((AnnotationSetRefList)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (AnnotationSetRefList)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((AnnotationSetRefList)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
