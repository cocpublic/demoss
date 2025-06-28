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
import groovy.transform.Internal;

// class: com/tencent/rfix/build/gradle/extension/RFixAutoVerifyExtension
public class RFixAutoVerifyExtension implements GroovyObject {
    private String verifyCase;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixAutoVerifyExtension() {
        CallSite[] siteArr0 = RFixAutoVerifyExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object object = siteArr0[0].callGetProperty(RFixConstants.class);
        (String)ShortTypeHandling.castToString(object).verifyCase = this;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixAutoVerifyExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixAutoVerifyExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixAutoVerifyExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixAutoVerifyExtension.$staticClassInfo.getMetaClass();
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
    public String getVerifyCase() {
        return this.verifyCase;
    }

    @Generated
    public void setVerifyCase(String str0) {
        this.verifyCase = str0;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "VERIFY_CASE_DEP_NONE";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixAutoVerifyExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixAutoVerifyExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixAutoVerifyExtension.$callSiteArray != null ? RFixAutoVerifyExtension.$createCallSiteArray() : (CallSiteArray)RFixAutoVerifyExtension.$callSiteArray.get();
        RFixAutoVerifyExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
