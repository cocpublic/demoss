/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/immutable;

import org.objectweb.asm.ClassReader;

// class: com/tencent/tinker/build/immutable/ClassSimDef
public class ClassSimDef {
    int methodCount;
    int fieldCount;
    byte bytes;
    HashSet<String> refFieldSet;
    HashSet<String> refMtdSet;

    publicvoid ClassSimDef(byte[] bytes, HashSet<String> refFieldSet, HashSet<String> refMtdSet) {
        super();
        this.bytes = bytes;
        this.refFieldSet = refFieldSet;
        this.refMtdSet = refMtdSet;
        this.init();
    }

    public void init() {
        this.methodCount = 0;
        this.fieldCount = 0;
        ClassReader cr = new ClassReader(this.bytes);
        ClassSimDef$1 cv = new ClassSimDef$1(this, 262144);
        cr.accept(cv, 0);
    }

}
