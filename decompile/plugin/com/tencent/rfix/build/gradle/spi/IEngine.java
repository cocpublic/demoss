/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/spi;


// class: com/tencent/rfix/build/gradle/spi/IEngine
public interface IEngine {

    String getName();

    Transform createTransform(Project p0);

    Task createPatchTask(Project p0, String p1, int p2, String p3, String p4, ApkVariant p5);

}
