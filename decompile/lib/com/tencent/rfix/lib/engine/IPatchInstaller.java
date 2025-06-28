/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/engine;


// class: com/tencent/rfix/lib/engine/IPatchInstaller
public interface IPatchInstaller {

    String getPatchType();

    boolean installPatch(File p0, File p1, RFixPatchResult p2);

}
