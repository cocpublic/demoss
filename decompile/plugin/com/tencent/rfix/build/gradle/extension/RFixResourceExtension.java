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

// class: com/tencent/rfix/build/gradle/extension/RFixResourceExtension
public class RFixResourceExtension {
    @Deprecated
    private boolean qFixEnable;
    private boolean enableAssert;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixResourceExtension(Project project) {
        CallSite[] siteArr0 = RFixResourceExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        int i0 = 0;
        i0.qFixEnable = this;
        int i1 = 1;
        i1.enableAssert = this;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixResourceExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixResourceExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixResourceExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixResourceExtension.$staticClassInfo.getMetaClass();
        }
    }

    @Generated
    public boolean getqFixEnable() {
        return this.qFixEnable;
    }

    @Generated
    public boolean isqFixEnable() {
        return this.qFixEnable;
    }

    @Generated
    public void setqFixEnable(boolean bool0) {
        this.qFixEnable = bool0;
    }

    @Generated
    public boolean getEnableAssert() {
        return this.enableAssert;
    }

    @Generated
    public boolean isEnableAssert() {
        return this.enableAssert;
    }

    @Generated
    public void setEnableAssert(boolean bool0) {
        this.enableAssert = bool0;
    }

    public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
        return this.$getStaticMetaClass();
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        return new CallSiteArray(RFixResourceExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixResourceExtension.$callSiteArray != null ? RFixResourceExtension.$createCallSiteArray() : (CallSiteArray)RFixResourceExtension.$callSiteArray.get();
        RFixResourceExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
