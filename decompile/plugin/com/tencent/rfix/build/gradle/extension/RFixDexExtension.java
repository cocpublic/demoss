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

// class: com/tencent/rfix/build/gradle/extension/RFixDexExtension
public class RFixDexExtension {
    private Iterable<String> classExclude;
    private boolean enableAssert;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixDexExtension(Project project) {
        CallSite[] siteArr0 = RFixDexExtension.$getCallSiteArray();
        super(project);
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        List list = ScriptBytecodeAdapter.createList(new Object[]{});
        list.classExclude = this;
        int i0 = 1;
        i0.enableAssert = this;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixDexExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixDexExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixDexExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixDexExtension.$staticClassInfo.getMetaClass();
        }
    }

    @Generated
    public Iterable<String> getClassExclude() {
        return this.classExclude;
    }

    @Generated
    public void setClassExclude(Iterable<String> iterable) {
        this.classExclude = iterable;
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
        return new CallSiteArray(RFixDexExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixDexExtension.$callSiteArray != null ? RFixDexExtension.$createCallSiteArray() : (CallSiteArray)RFixDexExtension.$callSiteArray.get();
        RFixDexExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
