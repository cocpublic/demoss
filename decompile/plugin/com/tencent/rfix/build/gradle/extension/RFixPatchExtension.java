/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/extension;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import java.util.List;

// class: com/tencent/rfix/build/gradle/extension/RFixPatchExtension
public class RFixPatchExtension {
    private String patchType;
    @Deprecated
    private String oldApkArm64;
    @Deprecated
    private String newApkArm64;
    private Iterable<String> oldApks;
    private Iterable<String> newApks;
    private RFixBuildConfigExtension buildConfig;
    private RFixDexExtension dex;
    private RFixLibExtension lib;
    private RFixResourceExtension res;
    private RFixArkHotExtension arkHot;
    private RFixPackageConfigExtension packageConfig;
    private RFixSevenZipExtension sevenZip;
    private RFixSigningConfigExtension signingConfig;
    private RFixRedirectConfigExtension redirectConfig;
    private RFixExtendExtension extend;
    private RFixQFixExtension qfixExtension;
    private RFixAutoVerifyExtension autoVerifyConfig;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixPatchExtension(Project project) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object object = siteArr0[0].callGetProperty(RFixConstants.class);
        (String)ShortTypeHandling.castToString(object).patchType = this;
        List list = ScriptBytecodeAdapter.createList(new Object[]{});
        list.oldApks = this;
        List listVar1 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar1.newApks = this;
        Object objectVar1 = siteArr0[1].callConstructor(RFixBuildConfigExtension.class, project);
        (RFixBuildConfigExtension)ScriptBytecodeAdapter.castToType(objectVar1, RFixBuildConfigExtension.class).buildConfig = this;
        Object objectVar2 = siteArr0[2].callConstructor(RFixDexExtension.class, project);
        (RFixDexExtension)ScriptBytecodeAdapter.castToType(objectVar2, RFixDexExtension.class).dex = this;
        Object objectVar3 = siteArr0[3].callConstructor(RFixLibExtension.class, project);
        (RFixLibExtension)ScriptBytecodeAdapter.castToType(objectVar3, RFixLibExtension.class).lib = this;
        Object objectVar4 = siteArr0[4].callConstructor(RFixResourceExtension.class, project);
        (RFixResourceExtension)ScriptBytecodeAdapter.castToType(objectVar4, RFixResourceExtension.class).res = this;
        Object objectVar5 = siteArr0[5].callConstructor(RFixArkHotExtension.class, project);
        (RFixArkHotExtension)ScriptBytecodeAdapter.castToType(objectVar5, RFixArkHotExtension.class).arkHot = this;
        Object objectVar6 = siteArr0[6].callConstructor(RFixPackageConfigExtension.class, project);
        (RFixPackageConfigExtension)ScriptBytecodeAdapter.castToType(objectVar6, RFixPackageConfigExtension.class).packageConfig = this;
        Object objectVar7 = siteArr0[7].callConstructor(RFixSevenZipExtension.class, project);
        (RFixSevenZipExtension)ScriptBytecodeAdapter.castToType(objectVar7, RFixSevenZipExtension.class).sevenZip = this;
        Object objectVar8 = siteArr0[8].callConstructor(RFixSigningConfigExtension.class, project);
        (RFixSigningConfigExtension)ScriptBytecodeAdapter.castToType(objectVar8, RFixSigningConfigExtension.class).signingConfig = this;
        Object objectVar9 = siteArr0[9].callConstructor(RFixRedirectConfigExtension.class, project);
        (RFixRedirectConfigExtension)ScriptBytecodeAdapter.castToType(objectVar9, RFixRedirectConfigExtension.class).redirectConfig = this;
        Object objectVar10 = siteArr0[10].callConstructor(RFixExtendExtension.class);
        (RFixExtendExtension)ScriptBytecodeAdapter.castToType(objectVar10, RFixExtendExtension.class).extend = this;
        Object objectVar11 = siteArr0[11].callConstructor(RFixQFixExtension.class);
        (RFixQFixExtension)ScriptBytecodeAdapter.castToType(objectVar11, RFixQFixExtension.class).qfixExtension = this;
        Object objectVar12 = siteArr0[12].callConstructor(RFixAutoVerifyExtension.class);
        (RFixAutoVerifyExtension)ScriptBytecodeAdapter.castToType(objectVar12, RFixAutoVerifyExtension.class).autoVerifyConfig = this;
    }

    public void buildConfig(Action<RFixBuildConfigExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[13].call(action, this.buildConfig);
    }

    public void buildConfig(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[14].call(ConfigureUtil.class, closure, this.buildConfig);
    }

    public void dex(Action<RFixDexExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[15].call(action, this.dex);
    }

    public void dex(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[16].call(ConfigureUtil.class, closure, this.dex);
    }

    public void lib(Action<RFixLibExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[17].call(action, this.lib);
    }

    public void lib(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[18].call(ConfigureUtil.class, closure, this.lib);
    }

    public void res(Action<RFixResourceExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[19].call(action, this.res);
    }

    public void res(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[20].call(ConfigureUtil.class, closure, this.res);
    }

    public void arkHot(Action<RFixArkHotExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[21].call(action, this.arkHot);
    }

    public void arkHot(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[22].call(ConfigureUtil.class, closure, this.arkHot);
    }

    public void packageConfig(Action<RFixPackageConfigExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[23].call(action, this.packageConfig);
    }

    public void packageConfig(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[24].call(ConfigureUtil.class, closure, this.packageConfig);
    }

    public void sevenZip(Action<RFixSevenZipExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[25].call(action, this.sevenZip);
    }

    public void sevenZip(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[26].call(ConfigureUtil.class, closure, this.sevenZip);
    }

    public void signingConfig(Action<RFixSigningConfigExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[27].call(action, this.signingConfig);
    }

    public void signingConfig(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[28].call(ConfigureUtil.class, closure, this.signingConfig);
    }

    public void redirectConfig(Action<RFixRedirectConfigExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[29].call(action, this.redirectConfig);
    }

    public void redirectConfig(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[30].call(ConfigureUtil.class, closure, this.redirectConfig);
    }

    public void extend(Action<RFixSevenZipExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[31].call(action, this.extend);
    }

    public void extend(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[32].call(ConfigureUtil.class, closure, this.extend);
    }

    public void qfixExtension(Action<RFixQFixExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[33].call(action, this.qfixExtension);
    }

    public void qfixExtension(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[34].call(ConfigureUtil.class, closure, this.qfixExtension);
    }

    public void autoVerifyConfig(Action<RFixAutoVerifyExtension> action) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[35].call(action, this.autoVerifyConfig);
    }

    public void autoVerifyConfig(Closure closure) {
        CallSite[] siteArr0 = RFixPatchExtension.$getCallSiteArray();
        siteArr0[36].call(ConfigureUtil.class, closure, this.autoVerifyConfig);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixPatchExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixPatchExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixPatchExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixPatchExtension.$staticClassInfo.getMetaClass();
        }
    }

    @Generated
    public String getPatchType() {
        return this.patchType;
    }

    @Generated
    public void setPatchType(String str0) {
        this.patchType = str0;
    }

    @Generated
    public String getOldApkArm64() {
        return this.oldApkArm64;
    }

    @Generated
    public void setOldApkArm64(String str0) {
        this.oldApkArm64 = str0;
    }

    @Generated
    public String getNewApkArm64() {
        return this.newApkArm64;
    }

    @Generated
    public void setNewApkArm64(String str0) {
        this.newApkArm64 = str0;
    }

    @Generated
    public Iterable<String> getOldApks() {
        return this.oldApks;
    }

    @Generated
    public void setOldApks(Iterable<String> iterable) {
        this.oldApks = iterable;
    }

    @Generated
    public Iterable<String> getNewApks() {
        return this.newApks;
    }

    @Generated
    public void setNewApks(Iterable<String> iterable) {
        this.newApks = iterable;
    }

    @Generated
    public RFixBuildConfigExtension getBuildConfig() {
        return this.buildConfig;
    }

    @Generated
    public void setBuildConfig(RFixBuildConfigExtension extension) {
        this.buildConfig = extension;
    }

    @Generated
    public RFixDexExtension getDex() {
        return this.dex;
    }

    @Generated
    public void setDex(RFixDexExtension extension) {
        this.dex = extension;
    }

    @Generated
    public RFixLibExtension getLib() {
        return this.lib;
    }

    @Generated
    public void setLib(RFixLibExtension extension) {
        this.lib = extension;
    }

    @Generated
    public RFixResourceExtension getRes() {
        return this.res;
    }

    @Generated
    public void setRes(RFixResourceExtension extension) {
        this.res = extension;
    }

    @Generated
    public RFixArkHotExtension getArkHot() {
        return this.arkHot;
    }

    @Generated
    public void setArkHot(RFixArkHotExtension extension) {
        this.arkHot = extension;
    }

    @Generated
    public RFixPackageConfigExtension getPackageConfig() {
        return this.packageConfig;
    }

    @Generated
    public void setPackageConfig(RFixPackageConfigExtension extension) {
        this.packageConfig = extension;
    }

    @Generated
    public RFixSevenZipExtension getSevenZip() {
        return this.sevenZip;
    }

    @Generated
    public void setSevenZip(RFixSevenZipExtension extension) {
        this.sevenZip = extension;
    }

    @Generated
    public RFixSigningConfigExtension getSigningConfig() {
        return this.signingConfig;
    }

    @Generated
    public void setSigningConfig(RFixSigningConfigExtension extension) {
        this.signingConfig = extension;
    }

    @Generated
    public RFixRedirectConfigExtension getRedirectConfig() {
        return this.redirectConfig;
    }

    @Generated
    public void setRedirectConfig(RFixRedirectConfigExtension extension) {
        this.redirectConfig = extension;
    }

    @Generated
    public RFixExtendExtension getExtend() {
        return this.extend;
    }

    @Generated
    public void setExtend(RFixExtendExtension extension) {
        this.extend = extension;
    }

    @Generated
    public RFixQFixExtension getQfixExtension() {
        return this.qfixExtension;
    }

    @Generated
    public void setQfixExtension(RFixQFixExtension extension) {
        this.qfixExtension = extension;
    }

    @Generated
    public RFixAutoVerifyExtension getAutoVerifyConfig() {
        return this.autoVerifyConfig;
    }

    @Generated
    public void setAutoVerifyConfig(RFixAutoVerifyExtension extension) {
        this.autoVerifyConfig = extension;
    }

    public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
        return this.$getStaticMetaClass();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "PATCH_TYPE_DISABLE";
        stringArr0[1] = "<$constructor$>";
        stringArr0[2] = "<$constructor$>";
        stringArr0[3] = "<$constructor$>";
        stringArr0[4] = "<$constructor$>";
        stringArr0[5] = "<$constructor$>";
        stringArr0[6] = "<$constructor$>";
        stringArr0[7] = "<$constructor$>";
        stringArr0[8] = "<$constructor$>";
        stringArr0[9] = "<$constructor$>";
        stringArr0[10] = "<$constructor$>";
        stringArr0[11] = "<$constructor$>";
        stringArr0[12] = "<$constructor$>";
        stringArr0[13] = "execute";
        stringArr0[14] = "configure";
        stringArr0[15] = "execute";
        stringArr0[16] = "configure";
        stringArr0[17] = "execute";
        stringArr0[18] = "configure";
        stringArr0[19] = "execute";
        stringArr0[20] = "configure";
        stringArr0[21] = "execute";
        stringArr0[22] = "configure";
        stringArr0[23] = "execute";
        stringArr0[24] = "configure";
        stringArr0[25] = "execute";
        stringArr0[26] = "configure";
        stringArr0[27] = "execute";
        stringArr0[28] = "configure";
        stringArr0[29] = "execute";
        stringArr0[30] = "configure";
        stringArr0[31] = "execute";
        stringArr0[32] = "configure";
        stringArr0[33] = "execute";
        stringArr0[34] = "configure";
        stringArr0[35] = "execute";
        stringArr0[36] = "configure";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixPatchExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixPatchExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixPatchExtension.$callSiteArray != null ? RFixPatchExtension.$createCallSiteArray() : (CallSiteArray)RFixPatchExtension.$callSiteArray.get();
        RFixPatchExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
