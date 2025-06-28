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
import org.gradle.api.Project;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;

// class: com/tencent/tinker/build/gradle/extension/TinkerBuildConfigExtension
public class TinkerBuildConfigExtension implements GroovyObject {
    private String applyMapping;
    private String applyResourceMapping;
    private String tinkerId;
    private boolean appendOutputNameToTinkerId;
    private boolean isProtectedApp;
    private boolean supportHotplugComponent;
    private Project project;
    private boolean usingResourceMapping;
    private boolean keepDexApply;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerBuildConfigExtension(Project project) {
        CallSite[] siteArr0 = TinkerBuildConfigExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        (Project)ScriptBytecodeAdapter.castToType(project, Project.class).project = this;
        String str0 = "";
        str0.applyMapping = this;
        String str1 = "";
        str1.applyResourceMapping = this;
        Object object = null;
        (String)ShortTypeHandling.castToString(object).tinkerId = this;
        int i0 = 0;
        i0.appendOutputNameToTinkerId = this;
        int i1 = 0;
        i1.usingResourceMapping = this;
        int i2 = 0;
        i2.keepDexApply = this;
        int i3 = 0;
        i3.isProtectedApp = this;
    }

    public void checkParameter() {
        CallSite[] siteArr0 = TinkerBuildConfigExtension.$getCallSiteArray();
        if (! ScriptBytecodeAdapter.compareEqual(this.tinkerId, null) || DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(this.tinkerId)) ? 0 : 1 != 0) {
            throw (Throwable)siteArr0[1].callConstructor(GradleException.class, "you must set your tinkerId to identify the base apk!");
        }
        else {
        }
    }

    public String toString() {
        CallSite[] siteArr0 = TinkerBuildConfigExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[2].call(new GStringImpl(new Object[]{this.applyMapping, this.applyResourceMapping, Boolean.valueOf(this.isProtectedApp), Boolean.valueOf(this.supportHotplugComponent), Boolean.valueOf(this.keepDexApply), this.tinkerId, Boolean.valueOf(this.appendOutputNameToTinkerId)}, new String[]{"| applyMapping = ", "
           | applyResourceMapping = ", "
           | isProtectedApp = ", "
           | supportHotplugComponent = ", "
           | keepDexApply = ", "
           | tinkerId = ", "
           | appendOutputNameToTinkerId = ", "
        "})));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerBuildConfigExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerBuildConfigExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerBuildConfigExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerBuildConfigExtension.$staticClassInfo.getMetaClass();
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
    public String getApplyMapping() {
        return this.applyMapping;
    }

    @Generated
    public void setApplyMapping(String str0) {
        this.applyMapping = str0;
    }

    @Generated
    public String getApplyResourceMapping() {
        return this.applyResourceMapping;
    }

    @Generated
    public void setApplyResourceMapping(String str0) {
        this.applyResourceMapping = str0;
    }

    @Generated
    public String getTinkerId() {
        return this.tinkerId;
    }

    @Generated
    public void setTinkerId(String str0) {
        this.tinkerId = str0;
    }

    @Generated
    public boolean getAppendOutputNameToTinkerId() {
        return this.appendOutputNameToTinkerId;
    }

    @Generated
    public boolean isAppendOutputNameToTinkerId() {
        return this.appendOutputNameToTinkerId;
    }

    @Generated
    public void setAppendOutputNameToTinkerId(boolean bool0) {
        this.appendOutputNameToTinkerId = bool0;
    }

    @Generated
    public boolean getIsProtectedApp() {
        return this.isProtectedApp;
    }

    @Generated
    public boolean isIsProtectedApp() {
        return this.isProtectedApp;
    }

    @Generated
    public void setIsProtectedApp(boolean bool0) {
        this.isProtectedApp = bool0;
    }

    @Generated
    public boolean getSupportHotplugComponent() {
        return this.supportHotplugComponent;
    }

    @Generated
    public boolean isSupportHotplugComponent() {
        return this.supportHotplugComponent;
    }

    @Generated
    public void setSupportHotplugComponent(boolean bool0) {
        this.supportHotplugComponent = bool0;
    }

    @Generated
    public boolean getUsingResourceMapping() {
        return this.usingResourceMapping;
    }

    @Generated
    public boolean isUsingResourceMapping() {
        return this.usingResourceMapping;
    }

    @Generated
    public void setUsingResourceMapping(boolean bool0) {
        this.usingResourceMapping = bool0;
    }

    @Generated
    public boolean getKeepDexApply() {
        return this.keepDexApply;
    }

    @Generated
    public boolean isKeepDexApply() {
        return this.keepDexApply;
    }

    @Generated
    public void setKeepDexApply(boolean bool0) {
        this.keepDexApply = bool0;
    }

    public /* synthetic */ String super$1$toString() {
        return this.toString();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "isEmpty";
        stringArr0[1] = "<$constructor$>";
        stringArr0[2] = "stripMargin";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerBuildConfigExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerBuildConfigExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerBuildConfigExtension.$callSiteArray != null ? TinkerBuildConfigExtension.$createCallSiteArray() : (CallSiteArray)TinkerBuildConfigExtension.$callSiteArray.get();
        TinkerBuildConfigExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
