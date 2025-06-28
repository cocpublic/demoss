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

// class: com/tencent/rfix/build/gradle/extension/RFixBuildConfigExtension
public class RFixBuildConfigExtension {
    private String patchId;
    private boolean appendOutputNameToPatchId;
    private boolean enablePatchIdToManifest;
    private boolean enableProguardJavaResFix;
    private boolean enableFlutterSupport;
    private String flutterVersion;
    private boolean enablePackageSeparate;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixBuildConfigExtension(Project project) {
        CallSite[] siteArr0 = RFixBuildConfigExtension.$getCallSiteArray();
        super(project);
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object object = null;
        (String)ShortTypeHandling.castToString(object).patchId = this;
        int i0 = 0;
        i0.appendOutputNameToPatchId = this;
        int i1 = 1;
        i1.enablePatchIdToManifest = this;
        int i2 = 0;
        i2.enableProguardJavaResFix = this;
        int i3 = 0;
        i3.enableFlutterSupport = this;
        Object objectVar1 = null;
        (String)ShortTypeHandling.castToString(objectVar1).flutterVersion = this;
        int i4 = 0;
        i4.enablePackageSeparate = this;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixBuildConfigExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixBuildConfigExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixBuildConfigExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixBuildConfigExtension.$staticClassInfo.getMetaClass();
        }
    }

    @Generated
    public String getPatchId() {
        return this.patchId;
    }

    @Generated
    public void setPatchId(String str0) {
        this.patchId = str0;
    }

    @Generated
    public boolean getAppendOutputNameToPatchId() {
        return this.appendOutputNameToPatchId;
    }

    @Generated
    public boolean isAppendOutputNameToPatchId() {
        return this.appendOutputNameToPatchId;
    }

    @Generated
    public void setAppendOutputNameToPatchId(boolean bool0) {
        this.appendOutputNameToPatchId = bool0;
    }

    @Generated
    public boolean getEnablePatchIdToManifest() {
        return this.enablePatchIdToManifest;
    }

    @Generated
    public boolean isEnablePatchIdToManifest() {
        return this.enablePatchIdToManifest;
    }

    @Generated
    public void setEnablePatchIdToManifest(boolean bool0) {
        this.enablePatchIdToManifest = bool0;
    }

    @Generated
    public boolean getEnableProguardJavaResFix() {
        return this.enableProguardJavaResFix;
    }

    @Generated
    public boolean isEnableProguardJavaResFix() {
        return this.enableProguardJavaResFix;
    }

    @Generated
    public void setEnableProguardJavaResFix(boolean bool0) {
        this.enableProguardJavaResFix = bool0;
    }

    @Generated
    public boolean getEnableFlutterSupport() {
        return this.enableFlutterSupport;
    }

    @Generated
    public boolean isEnableFlutterSupport() {
        return this.enableFlutterSupport;
    }

    @Generated
    public void setEnableFlutterSupport(boolean bool0) {
        this.enableFlutterSupport = bool0;
    }

    @Generated
    public String getFlutterVersion() {
        return this.flutterVersion;
    }

    @Generated
    public void setFlutterVersion(String str0) {
        this.flutterVersion = str0;
    }

    @Generated
    public boolean getEnablePackageSeparate() {
        return this.enablePackageSeparate;
    }

    @Generated
    public boolean isEnablePackageSeparate() {
        return this.enablePackageSeparate;
    }

    @Generated
    public void setEnablePackageSeparate(boolean bool0) {
        this.enablePackageSeparate = bool0;
    }

    public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
        return this.$getStaticMetaClass();
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        return new CallSiteArray(RFixBuildConfigExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixBuildConfigExtension.$callSiteArray != null ? RFixBuildConfigExtension.$createCallSiteArray() : (CallSiteArray)RFixBuildConfigExtension.$callSiteArray.get();
        RFixBuildConfigExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
