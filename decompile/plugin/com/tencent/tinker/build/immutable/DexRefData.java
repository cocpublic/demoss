/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/immutable;

import java.util.HashSet;

// class: com/tencent/tinker/build/immutable/DexRefData
public class DexRefData {
    int methodNum;
    int fieldNum;
    public Set<String> refFields;
    public Set<String> refMtds;

     DexRefData() {
        super(0, 0);
    }

     DexRefData(int methodNum, int fieldNum) {
        super();
        this.methodNum = methodNum;
        this.fieldNum = fieldNum;
        this.refFields = new HashSet();
        this.refMtds = new HashSet();
    }

}
