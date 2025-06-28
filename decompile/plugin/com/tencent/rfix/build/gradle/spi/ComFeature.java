/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/spi;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;

// class: com/tencent/rfix/build/gradle/spi/ComFeature
public class ComFeature implements IFeature, GroovyObject {
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public ComFeature() {
        CallSite[] siteArr0 = ComFeature.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public boolean redirectSupport() {
        CallSite[] siteArr0 = ComFeature.$getCallSiteArray();
        return false;
    }

    public boolean flutterSupport() {
        CallSite[] siteArr0 = ComFeature.$getCallSiteArray();
        return false;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ComFeature.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (ComFeature.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                ComFeature.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return ComFeature.$staticClassInfo.getMetaClass();
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

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        return new CallSiteArray(ComFeature.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = ComFeature.$callSiteArray != null ? ComFeature.$createCallSiteArray() : (CallSiteArray)ComFeature.$callSiteArray.get();
        ComFeature.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
