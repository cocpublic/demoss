/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/task;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.reflection.ClassInfo;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.io.File;

// class: com/tencent/rfix/build/gradle/task/RFixCacheExpireTask
@CacheableTask
public class RFixCacheExpireTask implements GroovyObject {
    @Input
    private String patchId;
    @OutputFile
    private File outputFile;
    private boolean cacheExpired;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public RFixCacheExpireTask() {
        CallSite[] siteArr0 = RFixCacheExpireTask.$getCallSiteArray();
        super();
        int i0 = 0;
        i0.cacheExpired = this;
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    @TaskAction
    public void generate() {
        CallSite[] siteArr0 = RFixCacheExpireTask.$getCallSiteArray();
        siteArr0[0].callCurrent(this, siteArr0[1].call("RFixCacheExpireTask: patchId=", this.patchId));
        int i0 = 1;
        i0.cacheExpired = this;
        String str0 = this.patchId;
        ScriptBytecodeAdapter.setProperty(str0, null, this.outputFile, (String)"text");
    }

    public boolean cacheExpired() {
        CallSite[] siteArr0 = RFixCacheExpireTask.$getCallSiteArray();
        return this.cacheExpired;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixCacheExpireTask.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixCacheExpireTask.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixCacheExpireTask.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixCacheExpireTask.$staticClassInfo.getMetaClass();
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
    public String getPatchId() {
        return this.patchId;
    }

    @Generated
    public void setPatchId(String str0) {
        this.patchId = str0;
    }

    @Generated
    public File getOutputFile() {
        return this.outputFile;
    }

    @Generated
    public void setOutputFile(File file) {
        this.outputFile = file;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "println";
        stringArr0[1] = "plus";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixCacheExpireTask.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixCacheExpireTask.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixCacheExpireTask.$callSiteArray != null ? RFixCacheExpireTask.$createCallSiteArray() : (CallSiteArray)RFixCacheExpireTask.$callSiteArray.get();
        RFixCacheExpireTask.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
