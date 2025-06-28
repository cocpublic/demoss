/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.Annotation;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/AnnotationSectionPatchAlgorithm
public class AnnotationSectionPatchAlgorithm {
    private TableOfContents$Section patchedAnnotationTocSec;
    private Dex$Section patchedAnnotationSec;

    public AnnotationSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedAnnotationTocSec = null;
        this.patchedAnnotationSec = null;
        if (patchedDex != null) {
            this.patchedAnnotationTocSec = patchedDex.getTableOfContents().annotations;
            this.patchedAnnotationSec = patchedDex.openSection(this.patchedAnnotationTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().annotations;
    }

    protected Annotation nextItem(DexDataBuffer section) {
        return section.readAnnotation();
    }

    protected int getItemSize(Annotation item) {
        return item.byteCountInDex();
    }

    protected Annotation adjustItem(AbstractIndexMap indexMap, Annotation item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(Annotation patchedItem) {
        this.patchedAnnotationTocSec.size = this.patchedAnnotationTocSec.size + 1;
        return this.patchedAnnotationSec.writeAnnotation(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapAnnotationOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markAnnotationDeleted(deletedOffset);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((Annotation)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (Annotation)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((Annotation)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
