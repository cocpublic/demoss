/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/info;


// class: com/tencent/tinker/build/info/PatchInfo
public class PatchInfo {
    final private PatchInfoGen infoGen;

    public PatchInfo(Configuration config) {
        super();
        this.infoGen = new PatchInfoGen(config);
    }

    public void gen() {
        this.infoGen.gen();
    }

}
