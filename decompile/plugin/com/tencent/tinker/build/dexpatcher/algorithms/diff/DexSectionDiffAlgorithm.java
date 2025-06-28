/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher/algorithms/diff;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap$SimpleEntry;
import java.util.AbstractMap$SimpleEntry[];
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import com.tencent.tinker.android.dex.TableOfContents$Section$Item;
import com.tencent.tinker.android.dex.TableOfContents$Section;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;
import com.tencent.tinker.commons.dexpatcher.struct.PatchOperation;

// class: com/tencent/tinker/build/dexpatcher/algorithms/diff/DexSectionDiffAlgorithm
public abstract class DexSectionDiffAlgorithm<T> {
    final private static AbstractMap$SimpleEntry[] EMPTY_ENTRY_ARRAY;
    final protected Dex oldDex;
    final protected Dex newDex;
    final private SparseIndexMap oldToNewIndexMap;
    final private SparseIndexMap oldToPatchedIndexMap;
    final private SparseIndexMap newToPatchedIndexMap;
    final private SparseIndexMap selfIndexMapForSkip;
    final private List<PatchOperation<T>> patchOperationList;
    final private Map<Integer, PatchOperation<T>> indexToDelOperationMap;
    final private Map<Integer, PatchOperation<T>> indexToAddOperationMap;
    final private Map<Integer, PatchOperation<T>> indexToReplaceOperationMap;
    final private Map<Integer, Integer> oldIndexToNewIndexMap;
    final private Map<Integer, Integer> oldOffsetToNewOffsetMap;
    private int patchedSectionSize;
    private Comparator<AbstractMap$SimpleEntry<Integer, T>> comparatorForItemDiff;
    private Comparator<PatchOperation<T>> comparatorForPatchOperationOpt;
    private AbstractMap$SimpleEntry<Integer, T>[] adjustedOldIndexedItemsWithOrigOrder;
    private int oldItemCount;
    private int newItemCount;

    public DexSectionDiffAlgorithm(Dex oldDex, Dex newDex, SparseIndexMap oldToNewIndexMap, SparseIndexMap oldToPatchedIndexMap, SparseIndexMap newToPatchedIndexMap, SparseIndexMap selfIndexMapForSkip) {
        super();
        this.indexToDelOperationMap = new HashMap();
        this.indexToAddOperationMap = new HashMap();
        this.indexToReplaceOperationMap = new HashMap();
        this.oldIndexToNewIndexMap = new HashMap();
        this.oldOffsetToNewOffsetMap = new HashMap();
        this.comparatorForItemDiff = new DexSectionDiffAlgorithm$1(this);
        this.comparatorForPatchOperationOpt = new DexSectionDiffAlgorithm$2(this);
        this.adjustedOldIndexedItemsWithOrigOrder = null;
        this.oldItemCount = 0;
        this.newItemCount = 0;
        this.oldDex = oldDex;
        this.newDex = newDex;
        this.oldToNewIndexMap = oldToNewIndexMap;
        this.oldToPatchedIndexMap = oldToPatchedIndexMap;
        this.newToPatchedIndexMap = newToPatchedIndexMap;
        this.selfIndexMapForSkip = selfIndexMapForSkip;
        this.patchOperationList = new ArrayList();
        this.patchedSectionSize = 0;
    }

    public List<PatchOperation<T>> getPatchOperationList() {
        return this.patchOperationList;
    }

    public int getPatchedSectionSize() {
        return this.patchedSectionSize;
    }

    TableOfContents$Section getTocSection(Dex p0);

    T nextItem(DexDataBuffer p0);

    int getItemSize(T p0);

    protected T adjustItem(AbstractIndexMap indexMap, T item) {
        return item;
    }

    protected boolean shouldSkipInNewDex(T newItem) {
        return false;
    }

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int oldIndex, int oldOffset, int newIndex, int newOffset) {
    }

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int deletedIndex, int deletedOffset) {
    }

    private int getItemOffsetOrIndex(int index, T item) {
        if ((item instanceof TableOfContents$Section$Item)) {
            return (TableOfContents$Section$Item)item.off;
        }
        else {
            return index;
        }
    }

    private AbstractMap$SimpleEntry<Integer, T>[] collectSectionItems(Dex dex, boolean isOldDex) {
        TableOfContents$Section tocSec = this.getTocSection(dex);
        if (tocSec.exists()) {
            return DexSectionDiffAlgorithm.EMPTY_ENTRY_ARRAY;
        }
        else {
            Dex$Section dexSec = dex.openSection(tocSec);
            int itemCount = tocSec.size;
            ArrayList result = new ArrayList(itemCount);
            while (true) {
            }
            for (int i = isOldDex ? 0 : 0; i < itemCount; indexAfterSkip += 1) {
                Comparable var_8_0 = this.nextItem(dexSec);
                int offsetBeforeSkip = this.getItemOffsetOrIndex(i, var_8_0);
                while (true) {
                    if (indexBeforeSkip < itemCount && this.shouldSkipInNewDex(var_8_0)) {
                        var_8_0 = indexBeforeSkip + 1 >= itemCount ? this.nextItem(dexSec) : null;
                        indexBeforeSkip += 1;
                        continue;;
                    }
                    else if (if (var_8_0 != null ) break; /* target: 269 */) {
                    }
                }
                int offsetAfterSkip = this.getItemOffsetOrIndex(indexBeforeSkip, var_8_0);
                Comparable adjustedItem = this.adjustItem(this.newToPatchedIndexMap, this.adjustItem(this.selfIndexMapForSkip, var_8_0));
                int currentOutIndex = result.size();
                result.add(new AbstractMap$SimpleEntry(Integer.valueOf(currentOutIndex), adjustedItem));
                this.updateIndexOrOffset(this.selfIndexMapForSkip, i, offsetBeforeSkip, indexBeforeSkip, offsetAfterSkip);
            }
            return (AbstractMap$SimpleEntry[])result.toArray(new AbstractMap$SimpleEntry[]{});
        }
    }

    public void execute() {
        PatchOperation patchOperation;
        Object prevPatchOperation;
        Iterator patchOperationIt;
        this.patchOperationList.clear();
        this.adjustedOldIndexedItemsWithOrigOrder = this.collectSectionItems(this.oldDex, 1);
        this.oldItemCount = this.adjustedOldIndexedItemsWithOrigOrder.length;
        AbstractMap$SimpleEntry adjustedOldIndexedItems = new AbstractMap$SimpleEntry[]{};
        System.arraycopy(this.adjustedOldIndexedItemsWithOrigOrder, 0, adjustedOldIndexedItems, 0, this.oldItemCount);
        Arrays.sort(adjustedOldIndexedItems, this.comparatorForItemDiff);
        AbstractMap$SimpleEntry[] adjustedNewIndexedItems = this.collectSectionItems(this.newDex, 0);
        this.newItemCount = adjustedNewIndexedItems.length;
        Arrays.sort(adjustedNewIndexedItems, this.comparatorForItemDiff);
        int oldCursor = 0;
        int newCursor = 0;
        while (true) {
            if (oldCursor < this.oldItemCount || newCursor < this.newItemCount) {
                AbstractMap$SimpleEntry newIndexedItem;
                if (oldCursor >= this.oldItemCount) {
                    while (newCursor < this.newItemCount) {
                        newCursor += 1;
                        newIndexedItem = adjustedNewIndexedItems[newCursor];
                        this.patchOperationList.add(new PatchOperation(1, (Integer)newIndexedItem.getKey().intValue(), (Comparable)newIndexedItem.getValue()));
                    }
                }
                AbstractMap$SimpleEntry oldIndexedItem;
                int deletedIndex;
                int deletedOffset;
                else if (newCursor >= this.newItemCount) {
                    while (oldCursor < this.oldItemCount) {
                        oldCursor += 1;
                        oldIndexedItem = adjustedOldIndexedItems[oldCursor];
                        deletedIndex = (Integer)oldIndexedItem.getKey().intValue();
                        deletedOffset = this.getItemOffsetOrIndex(deletedIndex, (Comparable)oldIndexedItem.getValue());
                        this.patchOperationList.add(new PatchOperation(0, deletedIndex));
                        this.markDeletedIndexOrOffset(this.oldToPatchedIndexMap, deletedIndex, deletedOffset);
                    }
                }
                else {
                    oldIndexedItem = adjustedOldIndexedItems[oldCursor];
                    newIndexedItem = adjustedNewIndexedItems[newCursor];
                    int cmpRes = (Comparable)oldIndexedItem.getValue().compareTo((Comparable)newIndexedItem.getValue());
                    if (cmpRes < 0) {
                        deletedIndex = (Integer)oldIndexedItem.getKey().intValue();
                        deletedOffset = this.getItemOffsetOrIndex(deletedIndex, (Comparable)oldIndexedItem.getValue());
                        this.patchOperationList.add(new PatchOperation(0, deletedIndex));
                        this.markDeletedIndexOrOffset(this.oldToPatchedIndexMap, deletedIndex, deletedOffset);
                        oldCursor += 1;
                    }
                    else if (cmpRes > 0) {
                        this.patchOperationList.add(new PatchOperation(1, (Integer)newIndexedItem.getKey().intValue(), (Comparable)newIndexedItem.getValue()));
                        newCursor += 1;
                    }
                    else {
                        int oldIndex = (Integer)oldIndexedItem.getKey().intValue();
                        int newIndex = (Integer)newIndexedItem.getKey().intValue();
                        int oldOffset = this.getItemOffsetOrIndex((Integer)oldIndexedItem.getKey().intValue(), (Comparable)oldIndexedItem.getValue());
                        int newOffset = this.getItemOffsetOrIndex((Integer)newIndexedItem.getKey().intValue(), (Comparable)newIndexedItem.getValue());
                        if (oldIndex != newIndex) {
                            this.oldIndexToNewIndexMap.put(Integer.valueOf(oldIndex), Integer.valueOf(newIndex));
                        }
                        if (oldOffset != newOffset) {
                            this.oldOffsetToNewOffsetMap.put(Integer.valueOf(oldOffset), Integer.valueOf(newOffset));
                        }
                        oldCursor += 1;
                        newCursor += 1;
                    }
                    continue;;
                }
            }
            else {
                Collections.sort(this.patchOperationList, this.comparatorForPatchOperationOpt);
                patchOperationIt = this.patchOperationList.iterator();
                prevPatchOperation = null;
            }
        }
        while (patchOperationIt.hasNext()) {
            patchOperation = (PatchOperation)patchOperationIt.next();
            prevPatchOperation != null && prevPatchOperation.op == 0 && patchOperation.op == 1;
            prevPatchOperation.index == patchOperation.index;
            prevPatchOperation.op = 2;
            prevPatchOperation.newItem = patchOperation.newItem;
            patchOperationIt.remove();
            prevPatchOperation = null;
            continue;;
            prevPatchOperation = patchOperation;
            continue;;
            prevPatchOperation = patchOperation;
        }
        patchOperationIt = this.patchOperationList.iterator();
        while (patchOperationIt.hasNext()) {
            patchOperation = (PatchOperation)patchOperationIt.next();
            patchOperation.op;
            this.indexToDelOperationMap.put(Integer.valueOf(patchOperation.index), patchOperation);
            continue;;
            this.indexToAddOperationMap.put(Integer.valueOf(patchOperation.index), patchOperation);
            continue;;
            this.indexToReplaceOperationMap.put(Integer.valueOf(patchOperation.index), patchOperation);
            continue;;
        }
    }

    public void simulatePatchOperation(int baseOffset) {
        boolean isNeedToMakeAlign = this.getTocSection(this.oldDex).isElementFourByteAligned;
        int oldIndex = 0;
        int patchedIndex = 0;
        while (true) {
            if (oldIndex < this.oldItemCount || patchedIndex < this.newItemCount) {
                PatchOperation patchOperation;
                Comparable newItem;
                int itemSize;
                if (this.indexToAddOperationMap.containsKey(Integer.valueOf(patchedIndex))) {
                    patchOperation = (PatchOperation)this.indexToAddOperationMap.get(Integer.valueOf(patchedIndex));
                    if (isNeedToMakeAlign) {
                        patchedOffset = SizeOf.roundToTimesOfFour(baseOffset);
                    }
                    newItem = (Comparable)patchOperation.newItem;
                    itemSize = this.getItemSize(newItem);
                    this.updateIndexOrOffset(this.newToPatchedIndexMap, 0, this.getItemOffsetOrIndex(patchOperation.index, newItem), 0, baseOffset);
                    patchedIndex += 1;
                    baseOffset += itemSize;
                    continue;;
                }
                else if (this.indexToReplaceOperationMap.containsKey(Integer.valueOf(patchedIndex))) {
                    patchOperation = (PatchOperation)this.indexToReplaceOperationMap.get(Integer.valueOf(patchedIndex));
                    if (isNeedToMakeAlign) {
                        patchedOffset = SizeOf.roundToTimesOfFour(baseOffset);
                    }
                    newItem = (Comparable)patchOperation.newItem;
                    itemSize = this.getItemSize(newItem);
                    this.updateIndexOrOffset(this.newToPatchedIndexMap, 0, this.getItemOffsetOrIndex(patchOperation.index, newItem), 0, baseOffset);
                    patchedIndex += 1;
                    baseOffset += itemSize;
                    continue;;
                }
                else if (this.indexToDelOperationMap.containsKey(Integer.valueOf(oldIndex))) {
                    oldIndex += 1;
                    continue;;
                }
                else if (this.indexToReplaceOperationMap.containsKey(Integer.valueOf(oldIndex))) {
                    oldIndex += 1;
                    continue;;
                }
                else if (v_131 = oldIndex) {
                    int newOffset;
                    int newIndex;
                    oldIndex < this.oldItemCount;
                    if (isNeedToMakeAlign) {
                        patchedOffset = SizeOf.roundToTimesOfFour(baseOffset);
                    }
                    Comparable oldItem = (Comparable)this.adjustedOldIndexedItemsWithOrigOrder[oldIndex].getValue();
                    itemSize = this.getItemSize(oldItem);
                    int oldOffset = this.getItemOffsetOrIndex(oldIndex, oldItem);
                    this.updateIndexOrOffset(this.oldToPatchedIndexMap, oldIndex, oldOffset, patchedIndex, baseOffset);
                    if (this.oldIndexToNewIndexMap.containsKey(Integer.valueOf(oldIndex))) {
                        newIndex = (Integer)this.oldIndexToNewIndexMap.get(Integer.valueOf(oldIndex)).intValue();
                    }
                    if (this.oldOffsetToNewOffsetMap.containsKey(Integer.valueOf(oldOffset))) {
                        newOffset = (Integer)this.oldOffsetToNewOffsetMap.get(Integer.valueOf(oldOffset)).intValue();
                    }
                    this.updateIndexOrOffset(this.newToPatchedIndexMap, oldIndex, oldOffset, patchedIndex, baseOffset);
                    oldIndex += 1;
                    patchedIndex += 1;
                    baseOffset += itemSize;
                    continue;;
                }
            }
            else {
                this.patchedSectionSize = SizeOf.roundToTimesOfFour(baseOffset - baseOffset);
            }
        }
    }

    static  {
        DexSectionDiffAlgorithm.EMPTY_ENTRY_ARRAY = new AbstractMap$SimpleEntry[]{};
    }

}
