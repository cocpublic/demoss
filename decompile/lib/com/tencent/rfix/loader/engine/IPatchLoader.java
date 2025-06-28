/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/engine;


// class: com/tencent/rfix/loader/engine/IPatchLoader
public interface IPatchLoader {

    String getPatchType();

    boolean loadPatch(RFixLoadResult p0);

    boolean cleanPatch(File p0);

    boolean unloadPatchImmediate();

}
