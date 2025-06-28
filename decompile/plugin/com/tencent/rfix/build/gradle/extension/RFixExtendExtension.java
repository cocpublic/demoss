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

// class: com/tencent/rfix/build/gradle/extension/RFixExtendExtension
public class RFixExtendExtension implements GroovyObject {
    private boolean enable;
    private String customDiffDecoder;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixExtendExtension() {
        CallSite[] siteArr0 = RFixExtendExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        int i0 = 0;
        i0.enable = this;
        Object object = null;
        (String)ShortTypeHandling.castToString(object).customDiffDecoder = this;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixExtendExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixExtendExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixExtendExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixExtendExtension.$staticClassInfo.getMetaClass();
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
    public boolean getEnable() {
        return this.enable;
    }

    @Generated
    public boolean isEnable() {
        return this.enable;
    }

    @Generated
    public void setEnable(boolean bool0) {
        this.enable = bool0;
    }

    @Generated
    public String getCustomDiffDecoder() {
        return this.customDiffDecoder;
    }

    @Generated
    public void setCustomDiffDecoder(String str0) {
        this.customDiffDecoder = str0;
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        return new CallSiteArray(RFixExtendExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixExtendExtension.$callSiteArray != null ? RFixExtendExtension.$createCallSiteArray() : (CallSiteArray)RFixExtendExtension.$callSiteArray.get();
        RFixExtendExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
