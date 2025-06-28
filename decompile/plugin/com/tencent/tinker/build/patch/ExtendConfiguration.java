/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/patch;


// class: com/tencent/tinker/build/patch/ExtendConfiguration
public class ExtendConfiguration {
    public static boolean sExtendEnable;
    public static String sCustomDiffDecoder;

    public ExtendConfiguration() {
        super();
    }

    static  {
        ExtendConfiguration.sExtendEnable = false;
        ExtendConfiguration.sCustomDiffDecoder = null;
    }

}
