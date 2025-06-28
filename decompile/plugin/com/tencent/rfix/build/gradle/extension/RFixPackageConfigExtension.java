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

// class: com/tencent/rfix/build/gradle/extension/RFixPackageConfigExtension
public class RFixPackageConfigExtension {
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixPackageConfigExtension(Project project) {
        CallSite[] siteArr0 = RFixPackageConfigExtension.$getCallSiteArray();
        super(project);
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixPackageConfigExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixPackageConfigExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixPackageConfigExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixPackageConfigExtension.$staticClassInfo.getMetaClass();
        }
    }

    public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
        return this.$getStaticMetaClass();
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        return new CallSiteArray(RFixPackageConfigExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixPackageConfigExtension.$callSiteArray != null ? RFixPackageConfigExtension.$createCallSiteArray() : (CallSiteArray)RFixPackageConfigExtension.$callSiteArray.get();
        RFixPackageConfigExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
