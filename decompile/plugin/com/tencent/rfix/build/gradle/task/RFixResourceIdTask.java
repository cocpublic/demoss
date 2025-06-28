/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/task;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClass;
import com.tencent.rfix.build.gradle.extension.RFixPatchExtension;

// class: com/tencent/rfix/build/gradle/task/RFixResourceIdTask
public class RFixResourceIdTask {
    protected RFixPatchExtension configuration;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixResourceIdTask() {
        CallSite[] siteArr0 = RFixResourceIdTask.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object object = siteArr0[0].callGetProperty(RFixPatchPlugin.class);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object, RFixResourceIdTask.class, this, (String)"group");
        Object objectVar1 = siteArr0[1].callGetProperty(siteArr0[2].callGetProperty(siteArr0[3].callGroovyObjectGetProperty(this)));
        (RFixPatchExtension)ScriptBytecodeAdapter.castToType(objectVar1, RFixPatchExtension.class).configuration = this;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixResourceIdTask.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixResourceIdTask.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixResourceIdTask.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixResourceIdTask.$staticClassInfo.getMetaClass();
        }
    }

    public /* synthetic */ MetaClass super$4$$getStaticMetaClass() {
        return this.$getStaticMetaClass();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "GROUP";
        stringArr0[1] = "RFixPatch";
        stringArr0[2] = "extensions";
        stringArr0[3] = "project";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixResourceIdTask.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixResourceIdTask.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixResourceIdTask.$callSiteArray != null ? RFixResourceIdTask.$createCallSiteArray() : (CallSiteArray)RFixResourceIdTask.$callSiteArray.get();
        RFixResourceIdTask.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
