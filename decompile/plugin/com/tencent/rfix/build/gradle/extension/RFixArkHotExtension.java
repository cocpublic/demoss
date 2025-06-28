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

// class: com/tencent/rfix/build/gradle/extension/RFixArkHotExtension
public class RFixArkHotExtension {
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixArkHotExtension(Project project) {
        CallSite[] siteArr0 = RFixArkHotExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixArkHotExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixArkHotExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixArkHotExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixArkHotExtension.$staticClassInfo.getMetaClass();
        }
    }

    public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
        return this.$getStaticMetaClass();
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        return new CallSiteArray(RFixArkHotExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixArkHotExtension.$callSiteArray != null ? RFixArkHotExtension.$createCallSiteArray() : (CallSiteArray)RFixArkHotExtension.$callSiteArray.get();
        RFixArkHotExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
