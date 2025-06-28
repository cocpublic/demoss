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

// class: com/tencent/rfix/build/gradle/spi/FeatureManager
public class FeatureManager implements IFeature, GroovyObject {
    private static FeatureManager instance;
    private IFeature feature;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    private FeatureManager() {
        CallSite[] siteArr0 = FeatureManager.$getCallSiteArray();
        super();
        Object object = null;
        (IFeature)ScriptBytecodeAdapter.castToType(object, IFeature.class).feature = this;
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object loader = siteArr0[0].call(ServiceLoader.class, IFeature.class, siteArr0[1].call(IFeature.class));
        Object iterator = siteArr0[2].call(loader);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(iterator))) {
            Object objectVar1 = siteArr0[4].call(iterator);
            (IFeature)ScriptBytecodeAdapter.castToType(objectVar1, IFeature.class).feature = this;
        }
    }

    public static FeatureManager getInstance() {
        CallSite[] siteArr0 = FeatureManager.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(FeatureManager.instance, null)) {
            Object object = siteArr0[5].callConstructor(FeatureManager.class);
            FeatureManager.instance = (FeatureManager)ScriptBytecodeAdapter.castToType(object, FeatureManager.class);
        }
        return FeatureManager.instance;
    }

    public boolean redirectSupport() {
        CallSite[] siteArr0 = FeatureManager.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual(this.feature, null)) {
            return DefaultTypeTransformation.booleanUnbox(siteArr0[6].call(this.feature));
        }
        else {
            return true;
        }
    }

    public boolean flutterSupport() {
        CallSite[] siteArr0 = FeatureManager.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual(this.feature, null)) {
            return DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(this.feature));
        }
        else {
            return true;
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != FeatureManager.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (FeatureManager.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                FeatureManager.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return FeatureManager.$staticClassInfo.getMetaClass();
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

    static  {
        Object object = null;
        FeatureManager.instance = (FeatureManager)ScriptBytecodeAdapter.castToType(object, FeatureManager.class);
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "load";
        stringArr0[1] = "getClassLoader";
        stringArr0[2] = "iterator";
        stringArr0[3] = "hasNext";
        stringArr0[4] = "next";
        stringArr0[5] = "<$constructor$>";
        stringArr0[6] = "redirectSupport";
        stringArr0[7] = "flutterSupport";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        FeatureManager.$createCallSiteArray_1(str0);
        return new CallSiteArray(FeatureManager.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = FeatureManager.$callSiteArray != null ? FeatureManager.$createCallSiteArray() : (CallSiteArray)FeatureManager.$callSiteArray.get();
        FeatureManager.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
