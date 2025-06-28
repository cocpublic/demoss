/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/gradle/extension;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.io.File;

// class: com/tencent/tinker/build/gradle/extension/TinkerPatchExtension
public class TinkerPatchExtension implements GroovyObject {
    private String oldApk;
    private String outputFolder;
    private String newApk;
    private boolean ignoreWarning;
    private boolean allowLoaderInAnyDex;
    private boolean removeLoaderForAllDex;
    private boolean useSign;
    private boolean tinkerEnable;
    private String customPath;
    private String customDiffPathArgs;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerPatchExtension() {
        CallSite[] siteArr0 = TinkerPatchExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        String str0 = "";
        str0.oldApk = this;
        String str1 = "";
        str1.outputFolder = this;
        String str2 = "";
        str2.newApk = this;
        int i0 = 0;
        i0.ignoreWarning = this;
        int i1 = 0;
        i1.allowLoaderInAnyDex = this;
        int i2 = 0;
        i2.removeLoaderForAllDex = this;
        int i3 = 1;
        i3.useSign = this;
        int i4 = 1;
        i4.tinkerEnable = this;
        Object object = null;
        (String)ShortTypeHandling.castToString(object).customPath = this;
        Object objectVar1 = null;
        (String)ShortTypeHandling.castToString(objectVar1).customDiffPathArgs = this;
    }

    public void checkParameter() {
        CallSite[] siteArr0 = TinkerPatchExtension.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(this.oldApk, null)) {
            throw (Throwable)siteArr0[0].callConstructor(GradleException.class, "old apk is null, you must set the correct old apk value!");
        }
        else {
            File apk = (File)ScriptBytecodeAdapter.castToType(siteArr0[1].callConstructor(File.class, this.oldApk), File.class);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(apk)) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[3].callConstructor(GradleException.class, new GStringImpl(new Object[]{this.oldApk}, new String[]{"old apk ", " is not exist, you must set the correct old apk value!"}));
            }
            else {
            }
        }
    }

    public String toString() {
        CallSite[] siteArr0 = TinkerPatchExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[4].call(new GStringImpl(new Object[]{this.oldApk, this.outputFolder, this.newApk, Boolean.valueOf(this.ignoreWarning), Boolean.valueOf(this.removeLoaderForAllDex), Boolean.valueOf(this.tinkerEnable), Boolean.valueOf(this.useSign)}, new String[]{"| oldApk = ", "
           | outputFolder = ", "
           | newApk = ", "
           | ignoreWarning = ", "
           | removeLoaderForAllDex = ", "
           | tinkerEnable = ", "
           | useSign = ", "
        "})));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerPatchExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerPatchExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerPatchExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerPatchExtension.$staticClassInfo.getMetaClass();
        }
    }

    @Generated
    @Internal
    public /* synthetic */ MetaClass getMetaClass() {
        if (this.metaClass != null) {
            return this.metaClass;
        }
        else {
            this.metaClass = this.$getStaticMetaClass();
            return this.metaClass;
        }
    }

    @Generated
    @Internal
    public /* synthetic */ void setMetaClass(MetaClass class) {
        this.metaClass = class;
    }

    @Generated
    @Internal
    public /* synthetic */ Object invokeMethod(String str0, Object object) {
        return this.getMetaClass().invokeMethod(this, str0, object);
    }

    @Generated
    @Internal
    public /* synthetic */ Object getProperty(String str0) {
        return this.getMetaClass().getProperty(this, str0);
    }

    @Generated
    @Internal
    public /* synthetic */ void setProperty(String str0, Object object) {
        this.getMetaClass().setProperty(this, str0, object);
    }

    @Generated
    public String getOldApk() {
        return this.oldApk;
    }

    @Generated
    public void setOldApk(String str0) {
        this.oldApk = str0;
    }

    @Generated
    public String getOutputFolder() {
        return this.outputFolder;
    }

    @Generated
    public void setOutputFolder(String str0) {
        this.outputFolder = str0;
    }

    @Generated
    public String getNewApk() {
        return this.newApk;
    }

    @Generated
    public void setNewApk(String str0) {
        this.newApk = str0;
    }

    @Generated
    public boolean getIgnoreWarning() {
        return this.ignoreWarning;
    }

    @Generated
    public boolean isIgnoreWarning() {
        return this.ignoreWarning;
    }

    @Generated
    public void setIgnoreWarning(boolean bool0) {
        this.ignoreWarning = bool0;
    }

    @Generated
    public boolean getAllowLoaderInAnyDex() {
        return this.allowLoaderInAnyDex;
    }

    @Generated
    public boolean isAllowLoaderInAnyDex() {
        return this.allowLoaderInAnyDex;
    }

    @Generated
    public void setAllowLoaderInAnyDex(boolean bool0) {
        this.allowLoaderInAnyDex = bool0;
    }

    @Generated
    public boolean getRemoveLoaderForAllDex() {
        return this.removeLoaderForAllDex;
    }

    @Generated
    public boolean isRemoveLoaderForAllDex() {
        return this.removeLoaderForAllDex;
    }

    @Generated
    public void setRemoveLoaderForAllDex(boolean bool0) {
        this.removeLoaderForAllDex = bool0;
    }

    @Generated
    public boolean getUseSign() {
        return this.useSign;
    }

    @Generated
    public boolean isUseSign() {
        return this.useSign;
    }

    @Generated
    public void setUseSign(boolean bool0) {
        this.useSign = bool0;
    }

    @Generated
    public boolean getTinkerEnable() {
        return this.tinkerEnable;
    }

    @Generated
    public boolean isTinkerEnable() {
        return this.tinkerEnable;
    }

    @Generated
    public void setTinkerEnable(boolean bool0) {
        this.tinkerEnable = bool0;
    }

    @Generated
    public String getCustomPath() {
        return this.customPath;
    }

    @Generated
    public void setCustomPath(String str0) {
        this.customPath = str0;
    }

    @Generated
    public String getCustomDiffPathArgs() {
        return this.customDiffPathArgs;
    }

    @Generated
    public void setCustomDiffPathArgs(String str0) {
        this.customDiffPathArgs = str0;
    }

    public /* synthetic */ String super$1$toString() {
        return this.toString();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "<$constructor$>";
        stringArr0[1] = "<$constructor$>";
        stringArr0[2] = "exists";
        stringArr0[3] = "<$constructor$>";
        stringArr0[4] = "stripMargin";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerPatchExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerPatchExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerPatchExtension.$callSiteArray != null ? TinkerPatchExtension.$createCallSiteArray() : (CallSiteArray)TinkerPatchExtension.$callSiteArray.get();
        TinkerPatchExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
