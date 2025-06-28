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
import java.util.List;

// class: com/tencent/rfix/build/gradle/extension/RFixQFixExtension
public class RFixQFixExtension implements GroovyObject {
    private boolean dexFixEnable;
    private boolean libFixEnable;
    private boolean resFixEnable;
    private boolean strictMode;
    private boolean enableClassHierarchy;
    private Iterable<String> abilityConfig;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixQFixExtension() {
        CallSite[] siteArr0 = RFixQFixExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        int i0 = 1;
        i0.dexFixEnable = this;
        int i1 = 0;
        i1.libFixEnable = this;
        int i2 = 0;
        i2.resFixEnable = this;
        int i3 = 0;
        i3.strictMode = this;
        int i4 = 1;
        i4.enableClassHierarchy = this;
        List list = ScriptBytecodeAdapter.createList(new Object[]{});
        list.abilityConfig = this;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixQFixExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixQFixExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixQFixExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixQFixExtension.$staticClassInfo.getMetaClass();
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
    public boolean getDexFixEnable() {
        return this.dexFixEnable;
    }

    @Generated
    public boolean isDexFixEnable() {
        return this.dexFixEnable;
    }

    @Generated
    public void setDexFixEnable(boolean bool0) {
        this.dexFixEnable = bool0;
    }

    @Generated
    public boolean getLibFixEnable() {
        return this.libFixEnable;
    }

    @Generated
    public boolean isLibFixEnable() {
        return this.libFixEnable;
    }

    @Generated
    public void setLibFixEnable(boolean bool0) {
        this.libFixEnable = bool0;
    }

    @Generated
    public boolean getResFixEnable() {
        return this.resFixEnable;
    }

    @Generated
    public boolean isResFixEnable() {
        return this.resFixEnable;
    }

    @Generated
    public void setResFixEnable(boolean bool0) {
        this.resFixEnable = bool0;
    }

    @Generated
    public boolean getStrictMode() {
        return this.strictMode;
    }

    @Generated
    public boolean isStrictMode() {
        return this.strictMode;
    }

    @Generated
    public void setStrictMode(boolean bool0) {
        this.strictMode = bool0;
    }

    @Generated
    public boolean getEnableClassHierarchy() {
        return this.enableClassHierarchy;
    }

    @Generated
    public boolean isEnableClassHierarchy() {
        return this.enableClassHierarchy;
    }

    @Generated
    public void setEnableClassHierarchy(boolean bool0) {
        this.enableClassHierarchy = bool0;
    }

    @Generated
    public Iterable<String> getAbilityConfig() {
        return this.abilityConfig;
    }

    @Generated
    public void setAbilityConfig(Iterable<String> iterable) {
        this.abilityConfig = iterable;
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        return new CallSiteArray(RFixQFixExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixQFixExtension.$callSiteArray != null ? RFixQFixExtension.$createCallSiteArray() : (CallSiteArray)RFixQFixExtension.$callSiteArray.get();
        RFixQFixExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
