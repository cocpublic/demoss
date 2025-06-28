/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dx/instruction;

import com.tencent.tinker.android.utils.SparseIntArray;

// class: com/tencent/tinker/android/dx/instruction/CodeCursor
public abstract class CodeCursor {
    final private SparseIntArray baseAddressMap;
    private int cursor;

    public CodeCursor() {
        super();
        this.baseAddressMap = new SparseIntArray();
        this.cursor = 0;
    }

    final public int cursor() {
        return this.cursor;
    }

    final public int baseAddressForCursor() {
        int index = this.baseAddressMap.indexOfKey(this.cursor);
        if (index < 0) {
            return this.cursor;
        }
        else {
            return this.baseAddressMap.valueAt(index);
        }
    }

    final public void setBaseAddress(int targetAddress, int baseAddress) {
        this.baseAddressMap.put(targetAddress, baseAddress);
    }

    public void reset() {
        this.baseAddressMap.clear();
        this.cursor = 0;
    }

    final protected void advance(int amount) {
        this.cursor = this.cursor + amount;
    }

}
