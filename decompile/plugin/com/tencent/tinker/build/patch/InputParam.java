/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/patch;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

// class: com/tencent/tinker/build/patch/InputParam
public class InputParam {
    final public String oldApk;
    final public String newApk;
    final public String outFolder;
    final public File signFile;
    final public String keypass;
    final public String storealias;
    final public String storepass;
    final public String customDiffPath;
    final public String customDiffPathArgs;
    final public boolean ignoreWarning;
    final public boolean allowLoaderInAnyDex;
    final public boolean removeLoaderForAllDex;
    final public boolean isProtectedApp;
    final public boolean supportHotplugComponent;
    final public boolean useSign;
    final public ArrayList<String> dexFilePattern;
    final public ArrayList<String> dexLoaderPattern;
    final public ArrayList<String> dexIgnoreWarningLoaderPattern;
    final public String dexMode;
    final public ArrayList<String> soFilePattern;
    final public ArrayList<String> resourceFilePattern;
    final public ArrayList<String> resourceIgnoreChangePattern;
    final public ArrayList<String> resourceIgnoreChangeWarningPattern;
    final public int largeModSize;
    final public boolean useApplyResource;
    final public HashMap<String, String> configFields;
    final public String sevenZipPath;
    final public String arkHotPatchPath;
    final public String arkHotPatchName;

    privatevoid InputParam(String oldApk, String newApk, String outFolder, File signFile, String keypass, String storealias, String storepass, String customDiffPath, String customDiffPathArgs, boolean ignoreWarning, boolean allowLoaderInAnyDex, boolean removeLoaderForAllDex, boolean isProtectedApp, boolean supportHotplugComponent, boolean useSign, ArrayList<String> dexFilePattern, ArrayList<String> dexLoaderPattern, ArrayList<String> dexIgnoreChangeLoaderPattern, String dexMode, ArrayList<String> soFilePattern, ArrayList<String> resourceFilePattern, ArrayList<String> resourceIgnoreChangePattern, ArrayList<String> resourceIgnoreChangeWarningPattern, int largeModSize, boolean useApplyResource, HashMap<String, String> configFields, String sevenZipPath, String arkHotPatchPath, String arkHotPatchName) {
        super();
        this.oldApk = oldApk;
        this.newApk = newApk;
        this.outFolder = outFolder;
        this.signFile = signFile;
        this.keypass = keypass;
        this.storealias = storealias;
        this.storepass = storepass;
        this.customDiffPath = customDiffPath;
        this.customDiffPathArgs = customDiffPathArgs;
        this.ignoreWarning = ignoreWarning;
        this.allowLoaderInAnyDex = allowLoaderInAnyDex;
        this.removeLoaderForAllDex = removeLoaderForAllDex;
        this.isProtectedApp = isProtectedApp;
        this.supportHotplugComponent = supportHotplugComponent;
        this.useSign = useSign;
        this.dexFilePattern = dexFilePattern;
        this.dexLoaderPattern = dexLoaderPattern;
        this.dexIgnoreWarningLoaderPattern = dexIgnoreChangeLoaderPattern;
        this.dexMode = dexMode;
        this.soFilePattern = soFilePattern;
        this.resourceFilePattern = resourceFilePattern;
        this.resourceIgnoreChangePattern = resourceIgnoreChangePattern;
        this.resourceIgnoreChangeWarningPattern = resourceIgnoreChangeWarningPattern;
        this.largeModSize = largeModSize;
        this.useApplyResource = useApplyResource;
        this.configFields = configFields;
        this.sevenZipPath = sevenZipPath;
        this.arkHotPatchPath = arkHotPatchPath;
        this.arkHotPatchName = arkHotPatchName;
    }

    /* synthetic */ InputParam(String x0, String x1, String x2, File x3, String x4, String x5, String x6, String x7, String x8, boolean x9, boolean x10, boolean x11, boolean x12, boolean x13, boolean x14, ArrayList x15, ArrayList x16, ArrayList x17, String x18, ArrayList x19, ArrayList x20, ArrayList x21, ArrayList x22, int x23, boolean x24, HashMap x25, String x26, String x27, String x28, InputParam$1 x29) {
        super(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19, x20, x21, x22, x23, x24, x25, x26, x27, x28);
    }

    // class: com/tencent/tinker/build/patch/InputParam$Builder
    public class InputParam$Builder {
        private String oldApk;
        private String newApk;
        private String outFolder;
        private File signFile;
        private String keypass;
        private String storealias;
        private String storepass;
        private String customDiffPath;
        private String customDiffPathArgs;
        private boolean ignoreWarning;
        private boolean allowLoaderInAnyDex;
        private boolean removeLoaderForAllDex;
        private boolean isProtectedApp;
        private boolean isComponentHotplugSupported;
        private boolean useSign;
        private ArrayList<String> dexFilePattern;
        private ArrayList<String> dexLoaderPattern;
        private ArrayList<String> dexIgnoreWarningLoaderPattern;
        private String dexMode;
        private ArrayList<String> soFilePattern;
        private ArrayList<String> resourceFilePattern;
        private ArrayList<String> resourceIgnoreChangePattern;
        private ArrayList<String> resourceIgnoreChangeWarningPattern;
        private int largeModSize;
        private boolean useApplyResource;
        private HashMap<String, String> configFields;
        private String sevenZipPath;
        private String arkHotPatchPath;
        private String arkHotPatchName;

        public InputParam$Builder() {
            super();
        }

        public InputParam$Builder setOldApk(String oldApk) {
            this.oldApk = oldApk;
            return this;
        }

        public InputParam$Builder setNewApk(String newApk) {
            this.newApk = newApk;
            return this;
        }

        public InputParam$Builder setSoFilePattern(ArrayList<String> soFilePattern) {
            this.soFilePattern = soFilePattern;
            return this;
        }

        public InputParam$Builder setResourceFilePattern(ArrayList<String> resourceFilePattern) {
            this.resourceFilePattern = resourceFilePattern;
            return this;
        }

        public InputParam$Builder setResourceIgnoreChangePattern(ArrayList<String> resourceIgnoreChangePattern) {
            this.resourceIgnoreChangePattern = resourceIgnoreChangePattern;
            return this;
        }

        public InputParam$Builder setResourceIgnoreChangeWarningPattern(ArrayList<String> resourceIgnoreChangeWarningPattern) {
            this.resourceIgnoreChangeWarningPattern = resourceIgnoreChangeWarningPattern;
            return this;
        }

        public InputParam$Builder setResourceLargeModSize(int largeModSize) {
            this.largeModSize = largeModSize;
            return this;
        }

        public InputParam$Builder setUseApplyResource(boolean useApplyResource) {
            this.useApplyResource = useApplyResource;
            return this;
        }

        public InputParam$Builder setDexFilePattern(ArrayList<String> dexFilePattern) {
            this.dexFilePattern = dexFilePattern;
            return this;
        }

        public InputParam$Builder setOutBuilder(String outFolder) {
            this.outFolder = outFolder;
            return this;
        }

        public InputParam$Builder setSignFile(File signFile) {
            this.signFile = signFile;
            return this;
        }

        public InputParam$Builder setKeypass(String keypass) {
            this.keypass = keypass;
            return this;
        }

        public InputParam$Builder setStorealias(String storealias) {
            this.storealias = storealias;
            return this;
        }

        public InputParam$Builder setStorepass(String storepass) {
            this.storepass = storepass;
            return this;
        }

        public InputParam$Builder setIgnoreWarning(boolean ignoreWarning) {
            this.ignoreWarning = ignoreWarning;
            return this;
        }

        public InputParam$Builder setAllowLoaderInAnyDex(boolean allowLoaderInAnyDex) {
            this.allowLoaderInAnyDex = allowLoaderInAnyDex;
            return this;
        }

        public InputParam$Builder setCustomDiffPath(String path) {
            this.customDiffPath = path;
            return this;
        }

        public InputParam$Builder setCustomDiffPathArgs(String args) {
            this.customDiffPathArgs = args;
            return this;
        }

        public InputParam$Builder setRemoveLoaderForAllDex(boolean removeLoaderForAllDex) {
            this.removeLoaderForAllDex = removeLoaderForAllDex;
            return this;
        }

        public InputParam$Builder setIsProtectedApp(boolean isProtectedApp) {
            this.isProtectedApp = isProtectedApp;
            return this;
        }

        public InputParam$Builder setIsComponentHotplugSupported(boolean isComponentHotplugSupported) {
            this.isComponentHotplugSupported = isComponentHotplugSupported;
            return this;
        }

        public InputParam$Builder setDexLoaderPattern(ArrayList<String> dexLoaderPattern) {
            this.dexLoaderPattern = dexLoaderPattern;
            return this;
        }

        public InputParam$Builder setDexIgnoreWarningLoaderPattern(ArrayList<String> loader) {
            this.dexIgnoreWarningLoaderPattern = loader;
            return this;
        }

        public InputParam$Builder setDexMode(String dexMode) {
            this.dexMode = dexMode;
            return this;
        }

        public InputParam$Builder setConfigFields(HashMap<String, String> configFields) {
            this.configFields = configFields;
            return this;
        }

        public InputParam$Builder setSevenZipPath(String sevenZipPath) {
            this.sevenZipPath = sevenZipPath;
            return this;
        }

        public InputParam$Builder setUseSign(boolean useSign) {
            this.useSign = useSign;
            return this;
        }

        public InputParam$Builder setArkHotPath(String path) {
            this.arkHotPatchPath = path;
            return this;
        }

        public InputParam$Builder setArkHotName(String name) {
            this.arkHotPatchName = name;
            return this;
        }

        public InputParam create() {
            return new InputParam(this.oldApk, this.newApk, this.outFolder, this.signFile, this.keypass, this.storealias, this.storepass, this.customDiffPath, this.customDiffPathArgs, this.ignoreWarning, this.allowLoaderInAnyDex, this.removeLoaderForAllDex, this.isProtectedApp, this.isComponentHotplugSupported, this.useSign, this.dexFilePattern, this.dexLoaderPattern, this.dexIgnoreWarningLoaderPattern, this.dexMode, this.soFilePattern, this.resourceFilePattern, this.resourceIgnoreChangePattern, this.resourceIgnoreChangeWarningPattern, this.largeModSize, this.useApplyResource, this.configFields, this.sevenZipPath, this.arkHotPatchPath, this.arkHotPatchName, null);
        }

    }
    // class: com/tencent/tinker/build/patch/InputParam$Builder
    public class InputParam$Builder {
        private String oldApk;
        private String newApk;
        private String outFolder;
        private File signFile;
        private String keypass;
        private String storealias;
        private String storepass;
        private String customDiffPath;
        private String customDiffPathArgs;
        private boolean ignoreWarning;
        private boolean allowLoaderInAnyDex;
        private boolean removeLoaderForAllDex;
        private boolean isProtectedApp;
        private boolean isComponentHotplugSupported;
        private boolean useSign;
        private ArrayList<String> dexFilePattern;
        private ArrayList<String> dexLoaderPattern;
        private ArrayList<String> dexIgnoreWarningLoaderPattern;
        private String dexMode;
        private ArrayList<String> soFilePattern;
        private ArrayList<String> resourceFilePattern;
        private ArrayList<String> resourceIgnoreChangePattern;
        private ArrayList<String> resourceIgnoreChangeWarningPattern;
        private int largeModSize;
        private boolean useApplyResource;
        private HashMap<String, String> configFields;
        private String sevenZipPath;
        private String arkHotPatchPath;
        private String arkHotPatchName;

        public InputParam$Builder() {
            super();
        }

        public InputParam$Builder setOldApk(String oldApk) {
            this.oldApk = oldApk;
            return this;
        }

        public InputParam$Builder setNewApk(String newApk) {
            this.newApk = newApk;
            return this;
        }

        public InputParam$Builder setSoFilePattern(ArrayList<String> soFilePattern) {
            this.soFilePattern = soFilePattern;
            return this;
        }

        public InputParam$Builder setResourceFilePattern(ArrayList<String> resourceFilePattern) {
            this.resourceFilePattern = resourceFilePattern;
            return this;
        }

        public InputParam$Builder setResourceIgnoreChangePattern(ArrayList<String> resourceIgnoreChangePattern) {
            this.resourceIgnoreChangePattern = resourceIgnoreChangePattern;
            return this;
        }

        public InputParam$Builder setResourceIgnoreChangeWarningPattern(ArrayList<String> resourceIgnoreChangeWarningPattern) {
            this.resourceIgnoreChangeWarningPattern = resourceIgnoreChangeWarningPattern;
            return this;
        }

        public InputParam$Builder setResourceLargeModSize(int largeModSize) {
            this.largeModSize = largeModSize;
            return this;
        }

        public InputParam$Builder setUseApplyResource(boolean useApplyResource) {
            this.useApplyResource = useApplyResource;
            return this;
        }

        public InputParam$Builder setDexFilePattern(ArrayList<String> dexFilePattern) {
            this.dexFilePattern = dexFilePattern;
            return this;
        }

        public InputParam$Builder setOutBuilder(String outFolder) {
            this.outFolder = outFolder;
            return this;
        }

        public InputParam$Builder setSignFile(File signFile) {
            this.signFile = signFile;
            return this;
        }

        public InputParam$Builder setKeypass(String keypass) {
            this.keypass = keypass;
            return this;
        }

        public InputParam$Builder setStorealias(String storealias) {
            this.storealias = storealias;
            return this;
        }

        public InputParam$Builder setStorepass(String storepass) {
            this.storepass = storepass;
            return this;
        }

        public InputParam$Builder setIgnoreWarning(boolean ignoreWarning) {
            this.ignoreWarning = ignoreWarning;
            return this;
        }

        public InputParam$Builder setAllowLoaderInAnyDex(boolean allowLoaderInAnyDex) {
            this.allowLoaderInAnyDex = allowLoaderInAnyDex;
            return this;
        }

        public InputParam$Builder setCustomDiffPath(String path) {
            this.customDiffPath = path;
            return this;
        }

        public InputParam$Builder setCustomDiffPathArgs(String args) {
            this.customDiffPathArgs = args;
            return this;
        }

        public InputParam$Builder setRemoveLoaderForAllDex(boolean removeLoaderForAllDex) {
            this.removeLoaderForAllDex = removeLoaderForAllDex;
            return this;
        }

        public InputParam$Builder setIsProtectedApp(boolean isProtectedApp) {
            this.isProtectedApp = isProtectedApp;
            return this;
        }

        public InputParam$Builder setIsComponentHotplugSupported(boolean isComponentHotplugSupported) {
            this.isComponentHotplugSupported = isComponentHotplugSupported;
            return this;
        }

        public InputParam$Builder setDexLoaderPattern(ArrayList<String> dexLoaderPattern) {
            this.dexLoaderPattern = dexLoaderPattern;
            return this;
        }

        public InputParam$Builder setDexIgnoreWarningLoaderPattern(ArrayList<String> loader) {
            this.dexIgnoreWarningLoaderPattern = loader;
            return this;
        }

        public InputParam$Builder setDexMode(String dexMode) {
            this.dexMode = dexMode;
            return this;
        }

        public InputParam$Builder setConfigFields(HashMap<String, String> configFields) {
            this.configFields = configFields;
            return this;
        }

        public InputParam$Builder setSevenZipPath(String sevenZipPath) {
            this.sevenZipPath = sevenZipPath;
            return this;
        }

        public InputParam$Builder setUseSign(boolean useSign) {
            this.useSign = useSign;
            return this;
        }

        public InputParam$Builder setArkHotPath(String path) {
            this.arkHotPatchPath = path;
            return this;
        }

        public InputParam$Builder setArkHotName(String name) {
            this.arkHotPatchName = name;
            return this;
        }

        public InputParam create() {
            return new InputParam(this.oldApk, this.newApk, this.outFolder, this.signFile, this.keypass, this.storealias, this.storepass, this.customDiffPath, this.customDiffPathArgs, this.ignoreWarning, this.allowLoaderInAnyDex, this.removeLoaderForAllDex, this.isProtectedApp, this.isComponentHotplugSupported, this.useSign, this.dexFilePattern, this.dexLoaderPattern, this.dexIgnoreWarningLoaderPattern, this.dexMode, this.soFilePattern, this.resourceFilePattern, this.resourceIgnoreChangePattern, this.resourceIgnoreChangeWarningPattern, this.largeModSize, this.useApplyResource, this.configFields, this.sevenZipPath, this.arkHotPatchPath, this.arkHotPatchName, null);
        }

    }
}
