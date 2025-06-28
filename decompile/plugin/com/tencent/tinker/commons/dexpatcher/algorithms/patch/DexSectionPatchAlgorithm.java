/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/algorithms/patch;

import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.android.dex.TableOfContents$Section$Item;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;

// class: com/tencent/tinker/commons/dexpatcher/algorithms/patch/DexSectionPatchAlgorithm
public abstract class DexSectionPatchAlgorithm<T> {
    final protected DexPatchFile patchFile;
    final protected Dex oldDex;
    final private SparseIndexMap oldToPatchedIndexMap;

    public DexSectionPatchAlgorithm(DexPatchFile patchFile, Dex oldDex, SparseIndexMap oldToPatchedIndexMap) {
        super();
        this.patchFile = patchFile;
        this.oldDex = oldDex;
        this.oldToPatchedIndexMap = oldToPatchedIndexMap;
    }

    TableOfContents$Section getTocSection(Dex p0);

    T nextItem(DexDataBuffer p0);

    int getItemSize(T p0);

    protected T adjustItem(AbstractIndexMap indexMap, T item) {
        return item;
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
    }

    int writePatchedItem(T p0);

    private int[] readDeltaIndiciesOrOffsets(int count) {
        int[] result = new int[]{};
        int lastVal = 0;
        for (int i = 0; i < count; i += 1) {
            int delta = this.patchFile.getBuffer().readSleb128();
            lastVal += delta;
            result[i] = lastVal;
        }
        return result;
    }

    private int getItemOffsetOrIndex(int index, T item) {
        if ((item instanceof TableOfContents$Section$Item)) {
            return (TableOfContents$Section$Item)item.off;
        }
        else {
            return index;
        }
    }

    public void execute() {
        int deletedItemCount = this.patchFile.getBuffer().readUleb128();
        int[] deletedIndices = this.readDeltaIndiciesOrOffsets(deletedItemCount);
        int addedItemCount = this.patchFile.getBuffer().readUleb128();
        int[] addedIndices = this.readDeltaIndiciesOrOffsets(addedItemCount);
        int replacedItemCount = this.patchFile.getBuffer().readUleb128();
        int[] replacedIndices = this.readDeltaIndiciesOrOffsets(replacedItemCount);
        TableOfContents$Section tocSec = this.getTocSection(this.oldDex);
        Object oldSection = null;
        int oldItemCount = 0;
        if (tocSec.exists()) {
            Dex$Section section = this.oldDex.openSection(tocSec);
            oldItemCount = tocSec.size;
        }
        this.doFullPatch(oldSection, oldItemCount, deletedIndices, addedIndices, replacedIndices);
    }

    private void doFullPatch(Dex$Section oldSection, int oldItemCount, int[] deletedIndices, int[] addedIndices, int[] replacedIndices) {
        int newItemCount = oldItemCount + addedIndices.length - deletedIndices.length;
        int deletedItemCounter = 0;
        int addActionCursor = 0;
        int replaceActionCursor = 0;
        int oldIndex = 0;
        int patchedIndex = 0;
        while (true) {
            if (oldIndex < oldItemCount || patchedIndex < newItemCount) {
                int patchedOffset;
                if (addActionCursor < addedIndices.length && addedIndices[addActionCursor] == patchedIndex) {
                    Comparable addedItem = this.nextItem(this.patchFile.getBuffer());
                    patchedOffset = this.writePatchedItem(addedItem);
                    addActionCursor += 1;
                    patchedIndex += 1;
                    continue;;
                }
                else {
                    if (replaceActionCursor < replacedIndices.length && replacedIndices[replaceActionCursor] == patchedIndex) {
                        Comparable replacedItem = this.nextItem(this.patchFile.getBuffer());
                        patchedOffset = this.writePatchedItem(replacedItem);
                        replaceActionCursor += 1;
                        patchedIndex += 1;
                        continue;;
                    }
                    Comparable skippedOldItem;
                    else if (Arrays.binarySearch(deletedIndices, oldIndex) >= 0) {
                        skippedOldItem = this.nextItem(oldSection);
                        this.markDeletedIndexOrOffset(this.oldToPatchedIndexMap, oldIndex, this.getItemOffsetOrIndex(oldIndex, skippedOldItem));
                        oldIndex += 1;
                        deletedItemCounter += 1;
                        continue;;
                    }
                    else if (Arrays.binarySearch(replacedIndices, oldIndex) >= 0) {
                        skippedOldItem = this.nextItem(oldSection);
                        this.markDeletedIndexOrOffset(this.oldToPatchedIndexMap, oldIndex, this.getItemOffsetOrIndex(oldIndex, skippedOldItem));
                        oldIndex += 1;
                        continue;;
                    }
                    else if (oldIndex < oldItemCount) {
                        Comparable oldItem = this.adjustItem(this.oldToPatchedIndexMap, this.nextItem(oldSection));
                        patchedOffset = this.writePatchedItem(oldItem);
                        this.updateIndexOrOffset(this.oldToPatchedIndexMap, oldIndex, this.getItemOffsetOrIndex(oldIndex, oldItem), patchedIndex, patchedOffset);
                        oldIndex += 1;
                        patchedIndex += 1;
                        continue;;
                    }
                }
            }
            else {
            }
        }
        if (addActionCursor == addedIndices.length || deletedItemCounter == deletedIndices.length || replaceActionCursor != replacedIndices.length) {
            throw new IllegalStateException(String.format("bad patch operation sequence. addCounter: %d, addCount: %d, delCounter: %d, delCount: %d, replaceCounter: %d, replaceCount:%d", new Object[]{Integer.valueOf(addActionCursor), Integer.valueOf(addedIndices.length), Integer.valueOf(deletedItemCounter), Integer.valueOf(deletedIndices.length), Integer.valueOf(replaceActionCursor), Integer.valueOf(replacedIndices.length)}));
        }
        else {
        }
    }

}
