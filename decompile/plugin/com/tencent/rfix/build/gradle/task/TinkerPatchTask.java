/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/task;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.powerassert.ValueRecorder;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClass;
import com.tencent.rfix.build.gradle.extension.RFixPatchExtension;

// class: com/tencent/rfix/build/gradle/task/TinkerPatchTask
public class TinkerPatchTask implements RFixPatchMonitor$IShouldMonitorTask {
    protected RFixPatchExtension configuration;
    protected int index;
    protected String oldApk;
    protected String newApk;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerPatchTask() {
        CallSite[] siteArr0 = TinkerPatchTask.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object object = siteArr0[0].callGetProperty(RFixPatchPlugin.class);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object, TinkerPatchTask.class, this, (String)"group");
        Object objectVar1 = siteArr0[1].callGetProperty(siteArr0[2].callGetProperty(siteArr0[3].callGroovyObjectGetProperty(this)));
        (RFixPatchExtension)ScriptBytecodeAdapter.castToType(objectVar1, RFixPatchExtension.class).configuration = this;
    }

    public boolean shouldMonitorTask() {
        CallSite[] siteArr0 = TinkerPatchTask.$getCallSiteArray();
        return true;
    }

    public void initTask(int index, String oldApk, String newApk, ApkVariant variant) {
        CallSite[] siteArr0 = TinkerPatchTask.$getCallSiteArray();
        DefaultTypeTransformation.intUnbox(Integer.valueOf(index)).index = this;
        (String)ShortTypeHandling.castToString(oldApk).oldApk = this;
        (String)ShortTypeHandling.castToString(newApk).newApk = this;
        String outputFolder = (String)ShortTypeHandling.castToString(siteArr0[4].call(siteArr0[5].call(siteArr0[6].callGetProperty(siteArr0[7].callGroovyObjectGetProperty(this))), new GStringImpl(new Object[]{siteArr0[8].callGetProperty(RFixConstants.class)}, new String[]{"/", ""})));
        GStringImpl impl = new GStringImpl(new Object[]{outputFolder, siteArr0[9].callGetProperty(RFixConstants.class), siteArr0[10].callGetProperty(variant), Integer.valueOf(index)}, new String[]{"", "/", "/", "-", ""});
        outputFolder = (String)ShortTypeHandling.castToString(impl);
        siteArr0[11].callCurrent(this, outputFolder);
    }

    public Object tinkerPatch() {
        CallSite[] siteArr0 = TinkerPatchTask.$getCallSiteArray();
        String resourceMappingFile = (String)ShortTypeHandling.castToString(siteArr0[12].callGetProperty(siteArr0[13].callGroovyObjectGetProperty(this.configuration)));
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[14].call(FileOperation.class, resourceMappingFile))) {
            int i0 = true;
            ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i0), null, siteArr0[15].callGroovyObjectGetProperty(this.configuration), (String)"usingResourceMapping");
        }
        ValueRecorder recorder = new ValueRecorder();
        try {
            this.oldApk.record(recorder, 17);
            this.oldApk.record(recorder, 17);
            this.newApk.record(recorder, 25);
            this.newApk.record(recorder, 25);
            siteArr0[16].callCurrent(this, this.oldApk, this.newApk).record(recorder, 8);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[16].callCurrent(this, this.oldApk, this.newApk))) {
                recorder.clear();
            }
            else {
                ScriptBytecodeAdapter.assertFailed(AssertionRenderer.render("assert checkEnv(oldApk, newApk)", recorder), null);
            }
        }
        finally {
            recorder.clear();
            throw v_60;
        }
        Object tinkerSPIChecker = siteArr0[17].callConstructor(TinkerSPIChecker.class, siteArr0[18].callGroovyObjectGetProperty(this), this.configuration, this.oldApk, this.newApk);
        siteArr0[19].call(tinkerSPIChecker);
        Object backupOldApk = siteArr0[20].callGroovyObjectGetProperty(this.configuration);
        String str0 = this.oldApk;
        ScriptBytecodeAdapter.setGroovyObjectProperty(str0, TinkerPatchTask.class, this.configuration, (String)"oldApk");
        String str1 = this.newApk;
        ScriptBytecodeAdapter.setGroovyObjectProperty(str1, TinkerPatchTask.class, this, (String)"buildApkPath");
        Object result = ScriptBytecodeAdapter.invokeMethodOnSuper0(TinkerPatchSchemaTask.class, this, (String)"tinkerPatch");
        ScriptBytecodeAdapter.setGroovyObjectProperty(backupOldApk, TinkerPatchTask.class, this.configuration, (String)"oldApk");
        return result;
    }

    private boolean checkEnv(String oldApk, String newApk) {
        CallSite[] siteArr0 = TinkerPatchTask.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[21].call(PatchFileUtils.class, oldApk)) ? 0 : 1 != 0) {
            siteArr0[22].call(siteArr0[23].callGetProperty(siteArr0[24].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{oldApk}, new String[]{"old apk '", "' illegal!"}));
            return false;
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[25].call(PatchFileUtils.class, newApk)) ? 0 : 1 != 0) {
                siteArr0[26].call(siteArr0[27].callGetProperty(siteArr0[28].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{newApk}, new String[]{"new apk '", "' illegal!"}));
                return false;
            }
            else {
                return true;
            }
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerPatchTask.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerPatchTask.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerPatchTask.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerPatchTask.$staticClassInfo.getMetaClass();
        }
    }

    public /* synthetic */ Object super$4$tinkerPatch() {
        return this.tinkerPatch();
    }

    public /* synthetic */ MetaClass super$4$$getStaticMetaClass() {
        return this.$getStaticMetaClass();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "GROUP";
        stringArr0[1] = "RFixPatch";
        stringArr0[2] = "extensions";
        stringArr0[3] = "project";
        stringArr0[4] = "plus";
        stringArr0[5] = "getAbsolutePath";
        stringArr0[6] = "buildDir";
        stringArr0[7] = "project";
        stringArr0[8] = "RFIX_TEMP_DIR";
        stringArr0[9] = "TINKER_OUTPUT_DIR";
        stringArr0[10] = "dirName";
        stringArr0[11] = "setOutputFolder";
        stringArr0[12] = "applyResourceMapping";
        stringArr0[13] = "buildConfig";
        stringArr0[14] = "isLegalFile";
        stringArr0[15] = "buildConfig";
        stringArr0[16] = "checkEnv";
        stringArr0[17] = "<$constructor$>";
        stringArr0[18] = "project";
        stringArr0[19] = "check";
        stringArr0[20] = "oldApk";
        stringArr0[21] = "isLegalFile";
        stringArr0[22] = "error";
        stringArr0[23] = "logger";
        stringArr0[24] = "project";
        stringArr0[25] = "isLegalFile";
        stringArr0[26] = "error";
        stringArr0[27] = "logger";
        stringArr0[28] = "project";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerPatchTask.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerPatchTask.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerPatchTask.$callSiteArray != null ? TinkerPatchTask.$createCallSiteArray() : (CallSiteArray)TinkerPatchTask.$callSiteArray.get();
        TinkerPatchTask.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
