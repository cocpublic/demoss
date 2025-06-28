/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/spi;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.reflection.ClassInfo;
import org.gradle.api.Task;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import com.android.build.api.transform.Transform;

// class: com/tencent/rfix/build/gradle/spi/TinkerEngine
public class TinkerEngine implements IEngine, GroovyObject {
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public TinkerEngine() {
        CallSite[] siteArr0 = TinkerEngine.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public String getName() {
        CallSite[] siteArr0 = TinkerEngine.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[0].callGetProperty(RFixConstants.class));
    }

    public Transform createTransform(Project project) {
        CallSite[] siteArr0 = TinkerEngine.$getCallSiteArray();
        return (Transform)ScriptBytecodeAdapter.castToType(null, Transform.class);
    }

    public Task createPatchTask(Project project, String taskName, int index, String oldApk, String newApk, ApkVariant variant) {
        CallSite[] siteArr0 = TinkerEngine.$getCallSiteArray();
        Object task = siteArr0[1].call(siteArr0[2].callGetProperty(project), taskName, TinkerPatchTask.class);
        siteArr0[3].call(task, Integer.valueOf(index), oldApk, newApk, variant);
        return (Task)ScriptBytecodeAdapter.castToType(task, Task.class);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerEngine.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerEngine.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerEngine.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerEngine.$staticClassInfo.getMetaClass();
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

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "PATCH_TYPE_TINKER";
        stringArr0[1] = "create";
        stringArr0[2] = "tasks";
        stringArr0[3] = "initTask";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerEngine.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerEngine.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerEngine.$callSiteArray != null ? TinkerEngine.$createCallSiteArray() : (CallSiteArray)TinkerEngine.$callSiteArray.get();
        TinkerEngine.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
