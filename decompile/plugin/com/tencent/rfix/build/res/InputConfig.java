/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/res;


// class: com/tencent/rfix/build/res/InputConfig
public class InputConfig {
    public File oldApk;
    public File newApk;
    public File outputDir;
    public boolean cleanTmpFile;
    public File tmpResResultDir;
    public String[] pattern;
    public String[] whitelist;
    public File oldApkTmpDir;
    public File newApkTmpDir;
    public long largeFileSize;

    public InputConfig() {
        super();
        this.pattern = new String[]{"res/*", "assets/*", "resources.arsc", "AndroidManifest.xml"};
        this.whitelist = new String[]{};
    }

}
