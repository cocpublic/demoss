/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.ClassDef;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/ClassDefSectionPatchAlgorithm
public class ClassDefSectionPatchAlgorithm {
    private TableOfContents$Section patchedClassDefTocSec;
    private Dex$Section patchedClassDefSec;

    public ClassDefSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, Dex patchedDex, SparseIndexMap oldToPatchedIndexMap) {
        super(patchFile, oldDex, oldToPatchedIndexMap);
        this.patchedClassDefTocSec = null;
        this.patchedClassDefSec = null;
        if (patchedDex != null) {
            this.patchedClassDefTocSec = patchedDex.getTableOfContents().classDefs;
            this.patchedClassDefSec = patchedDex.openSection(this.patchedClassDefTocSec);
        }
    }

    protected TableOfContents$Section getTocSection(Dex dex) {
        return dex.getTableOfContents().classDefs;
    }

    protected ClassDef nextItem(DexDataBuffer section) {
        return section.readClassDef();
    }

    protected int getItemSize(ClassDef item) {
        return item.byteCountInDex();
    }

    protected ClassDef adjustItem(AbstractIndexMap indexMap, ClassDef item) {
        return indexMap.adjust(item);
    }

    protected int writePatchedItem(ClassDef patchedItem) {
        this.patchedClassDefTocSec.size = this.patchedClassDefTocSec.size + 1;
        return this.patchedClassDefSec.writeClassDef(patchedItem);
    }

    protected /* synthetic */ int writePatchedItem(Comparable comparable) {
        return this.writePatchedItem((ClassDef)comparable);
    }

    protected /* synthetic */ Comparable adjustItem(AbstractIndexMap map, Comparable comparable) {
        return this.adjustItem(map, (ClassDef)comparable);
    }

    protected /* synthetic */ int getItemSize(Comparable comparable) {
        return this.getItemSize((ClassDef)comparable);
    }

    protected /* synthetic */ Comparable nextItem(DexDataBuffer buffer) {
        return this.nextItem(buffer);
    }

}
