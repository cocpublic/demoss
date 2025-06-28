/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.AnnotationsDirectory;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/AnnotationsDirectorySectionPatchAlgorithm
public class AnnotationsDirectorySectionPatchAlgorithm {
    private TableOfContents$Section patchedAnnotationsDirectoryTocSec;
    private Dex$Section patchedAnnotationsDirectorySec;

    public AnnotationsDirectorySectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedAnnotationsDirectoryTocSec = null;
        this.patchedAnnotationsDirectorySec = null;
        if (patchedDex != null) {
            this.patchedAnnotationsDirectoryTocSec = patchedDex.getTableOfContents().annotationsDirectories;
            this.patchedAnnotationsDirectorySec = patchedDex.openSection(this.patchedAnnotationsDirectoryTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().annotationsDirectories;
    }

    protected AnnotationsDirectory nextItem(DexDataBuffer section) {
        return section.readAnnotationsDirectory();
    }

    protected int getItemSize(AnnotationsDirectory item) {
        return item.byteCountInDex();
    }

    protected AnnotationsDirectory adjustItem(AbstractIndexMap indexMap, AnnotationsDirectory item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(AnnotationsDirectory patchedItem) {
        this.patchedAnnotationsDirectoryTocSec.size = this.patchedAnnotationsDirectoryTocSec.size + 1;
        return this.patchedAnnotationsDirectorySec.writeAnnotationsDirectory(patchedItem);
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
        if (oldOffset != newOffset) {
            sparseIndexMap.mapAnnotationsDirectoryOffset(oldOffset, newOffset);
        }
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
        sparseIndexMap.markAnnotationsDirectoryDeleted(deletedOffset);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((AnnotationsDirectory)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (AnnotationsDirectory)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((AnnotationsDirectory)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
