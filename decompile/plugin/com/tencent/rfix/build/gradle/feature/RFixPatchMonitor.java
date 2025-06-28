/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/feature;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import org.gradle.api.Project;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import com.tencent.rfix.build.gradle.extension.RFixPatchExtension;
import java.util.Iterator;
import java.util.List;

// class: com/tencent/rfix/build/gradle/feature/RFixPatchMonitor
public class RFixPatchMonitor implements GroovyObject {
    final private static Object TAG;
    private static Project project;
    private static RFixPatchExtension fixPatch;
    private static boolean isBuildPatch;
    private static boolean patchSuccess;
    private static String patchErrorMessage;
    private static String patchErrorCause;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public RFixPatchMonitor() {
        CallSite[] siteArr0 = RFixPatchMonitor.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public static Object init(Project project, RFixPatchExtension fixPatch) {
        CallSite[] siteArr0 = RFixPatchMonitor.$getCallSiteArray();
        RFixPatchMonitor.project = (Project)ScriptBytecodeAdapter.castToType(project, Project.class);
        RFixPatchMonitor.fixPatch = (RFixPatchExtension)ScriptBytecodeAdapter.castToType(fixPatch, RFixPatchExtension.class);
        Object gradle = siteArr0[0].call(project);
        siteArr0[1].call(siteArr0[2].callGetProperty(gradle), new RFixPatchMonitor$1(RFixPatchMonitor.class));
        return siteArr0[3].call(gradle, new RFixPatchMonitor$_init_closure1(RFixPatchMonitor.class, RFixPatchMonitor.class));
    }

    private static void processMonitorTask(Task task, TaskState state) {
        CallSite[] siteArr0 = RFixPatchMonitor.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[4].callStatic(RFixPatchMonitor.class, task))) {
            int i0 = true;
            RFixPatchMonitor.isBuildPatch = i0;
            int i1 = true;
            RFixPatchMonitor.patchSuccess = i1;
            Object object = null;
            RFixPatchMonitor.patchErrorMessage = (String)ShortTypeHandling.castToString(object);
            Object objectVar1 = null;
            RFixPatchMonitor.patchErrorCause = (String)ShortTypeHandling.castToString(objectVar1);
            if (ScriptBytecodeAdapter.compareNotEqual(siteArr0[5].callGetProperty(state), null)) {
                siteArr0[6].callStatic(RFixPatchMonitor.class, new GStringImpl(new Object[]{RFixPatchMonitor.TAG, siteArr0[7].callGetProperty(task)}, new String[]{"", ": task execute fail! task: ", ""}));
                int i2 = false;
                RFixPatchMonitor.patchSuccess = i2;
                Object objectVar2 = siteArr0[8].callGetProperty(siteArr0[9].callGetProperty(state));
                RFixPatchMonitor.patchErrorMessage = (String)ShortTypeHandling.castToString(objectVar2);
                Object objectVar3 = siteArr0[10].callGetProperty(siteArr0[11].callGetProperty(state));
                RFixPatchMonitor.patchErrorCause = (String)ShortTypeHandling.castToString(objectVar3);
            }
        }
    }

    private static boolean shouldMonitorTask(Task task) {
        CallSite[] siteArr0 = RFixPatchMonitor.$getCallSiteArray();
        if ((task instanceof RFixPatchMonitor$IShouldMonitorTask)) {
            return DefaultTypeTransformation.booleanUnbox(siteArr0[12].call(task));
        }
        else {
            return false;
        }
    }

    private static void reportPatchResult() {
        CallSite[] siteArr0 = RFixPatchMonitor.$getCallSiteArray();
        if (RFixPatchMonitor.isBuildPatch ? 0 : 1 != 0) {
        }
        else {
            String appName = (String)ShortTypeHandling.castToString(siteArr0[13].call(siteArr0[14].call(RFixPatchMonitor.project)));
            Object appBundle = null;
            Object appVersion = null;
            String patchType = (String)ShortTypeHandling.castToString(siteArr0[15].callGroovyObjectGetProperty(RFixPatchMonitor.fixPatch));
            Object sdkVersion = null;
            String str0;
            if (RFixPatchMonitor.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 127;
                String str1 = RFixPatchMonitor.getSDKVersion();
            }
            else {
                Object object = siteArr0[16].callStatic(RFixPatchMonitor.class);
                str0 = (String)ShortTypeHandling.castToString(object);
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[17].call(siteArr0[18].callGroovyObjectGetProperty(RFixPatchMonitor.fixPatch))) ? 0 : 1 != 0) {
                Object apkInfo = siteArr0[19].callStatic(RFixPatchMonitor.class, siteArr0[20].call(siteArr0[21].callGroovyObjectGetProperty(RFixPatchMonitor.fixPatch), Integer.valueOf(0)));
                Object objectVar1 = siteArr0[22].call(apkInfo, Integer.valueOf(0));
                String str2 = (String)ShortTypeHandling.castToString(objectVar1);
                Object objectVar2 = siteArr0[23].call(apkInfo, Integer.valueOf(1));
                String str3 = (String)ShortTypeHandling.castToString(objectVar2);
            }
            siteArr0[24].call(RFixPatchReporter.class, ArrayUtil.createArray(appName, appBundle, appVersion, patchType, str1, Boolean.valueOf(RFixPatchMonitor.patchSuccess), Integer.valueOf(0), Integer.valueOf(0), RFixPatchMonitor.patchErrorMessage, RFixPatchMonitor.patchErrorCause));
        }
    }

    private static String getSDKVersion() {
        CallSite[] siteArr0 = RFixPatchMonitor.$getCallSiteArray();
        Object version = null;
        Object buildScript = siteArr0[25].call(siteArr0[26].call(RFixPatchMonitor.project));
        Object dep;
        if (BytecodeInterface8.isOrigZ() && RFixPatchMonitor.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 193;
            dep = null;
            Iterator iteratorVar1 = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[34].call(siteArr0[35].call(siteArr0[36].call(siteArr0[37].call(buildScript), "classpath"))), Iterator.class);
            while (iteratorVar1.hasNext()) {
                dep = iteratorVar1.next();
                if (ScriptBytecodeAdapter.compareEqual(siteArr0[38].callGetProperty(dep), "com.tencent.rfix") && ScriptBytecodeAdapter.compareEqual(siteArr0[39].callGetProperty(dep), "RFix-gradle-plugin") ? 0 : 1 != 0) {
                    Object objectVar1 = siteArr0[40].callGetProperty(dep);
                    version = objectVar1;
                    break;;
                }
                else {
                    continue;;
                }
            }
        }
        else {
            dep = null;
            Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[27].call(siteArr0[28].call(siteArr0[29].call(siteArr0[30].call(buildScript), "classpath"))), Iterator.class);
            while (iterator.hasNext()) {
                dep = iterator.next();
                if (ScriptBytecodeAdapter.compareEqual(siteArr0[31].callGetProperty(dep), "com.tencent.rfix") && ScriptBytecodeAdapter.compareEqual(siteArr0[32].callGetProperty(dep), "RFix-gradle-plugin") ? 0 : 1 != 0) {
                    Object object = siteArr0[33].callGetProperty(dep);
                    break;;
                }
                else {
                    continue;;
                }
            }
        }
        return (String)ShortTypeHandling.castToString(object);
    }

    private static String[] getApkInfo(String apkPath) {
        CallSite[] siteArr0 = RFixPatchMonitor.$getCallSiteArray();
        Object packageName = null;
        Object versionName = null;
        try {
            try {
                Object parser = siteArr0[41].call(AndroidParser.class, siteArr0[42].callConstructor(File.class, apkPath));
                Object object = siteArr0[43].callGetProperty(siteArr0[44].callGetProperty(parser));
                String str0 = (String)ShortTypeHandling.castToString(object);
                Object objectVar1 = siteArr0[45].callGetProperty(siteArr0[46].callGetProperty(parser));
                String str1 = (String)ShortTypeHandling.castToString(objectVar1);
            }
            catch (Exception ignore) {
            }
        }
        finally {
            Throwable throwable = v_44;
            throw throwable;
        }
        return (String[])ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[]{str0, str1}), String[].class);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixPatchMonitor.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixPatchMonitor.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixPatchMonitor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixPatchMonitor.$staticClassInfo.getMetaClass();
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] siteArr0 = RFixPatchMonitor.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(RFixPatchMonitor.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[]{}, new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] siteArr0 = RFixPatchMonitor.$getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty(value, RFixPatchMonitor.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] siteArr0 = RFixPatchMonitor.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectProperty(RFixPatchMonitor.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
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
        String str0 = "RFixPatchMonitor";
        RFixPatchMonitor.TAG = str0;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "getGradle";
        stringArr0[1] = "addTaskExecutionListener";
        stringArr0[2] = "taskGraph";
        stringArr0[3] = "buildFinished";
        stringArr0[4] = "shouldMonitorTask";
        stringArr0[5] = "failure";
        stringArr0[6] = "println";
        stringArr0[7] = "name";
        stringArr0[8] = "message";
        stringArr0[9] = "failure";
        stringArr0[10] = "cause";
        stringArr0[11] = "failure";
        stringArr0[12] = "shouldMonitorTask";
        stringArr0[13] = "getName";
        stringArr0[14] = "getRootProject";
        stringArr0[15] = "patchType";
        stringArr0[16] = "getSDKVersion";
        stringArr0[17] = "isEmpty";
        stringArr0[18] = "oldApks";
        stringArr0[19] = "getApkInfo";
        stringArr0[20] = "getAt";
        stringArr0[21] = "oldApks";
        stringArr0[22] = "getAt";
        stringArr0[23] = "getAt";
        stringArr0[24] = "reportBuildPatchResult";
        stringArr0[25] = "getBuildscript";
        stringArr0[26] = "getRootProject";
        stringArr0[27] = "iterator";
        stringArr0[28] = "getDependencies";
        stringArr0[29] = "getByName";
        stringArr0[30] = "getConfigurations";
        stringArr0[31] = "group";
        stringArr0[32] = "name";
        stringArr0[33] = "version";
        stringArr0[34] = "iterator";
        stringArr0[35] = "getDependencies";
        stringArr0[36] = "getByName";
        stringArr0[37] = "getConfigurations";
        stringArr0[38] = "group";
        stringArr0[39] = "name";
        stringArr0[40] = "version";
        stringArr0[41] = "getAndroidManifest";
        stringArr0[42] = "<$constructor$>";
        stringArr0[43] = "packageName";
        stringArr0[44] = "apkMeta";
        stringArr0[45] = "versionName";
        stringArr0[46] = "apkMeta";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixPatchMonitor.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixPatchMonitor.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixPatchMonitor.$callSiteArray != null ? RFixPatchMonitor.$createCallSiteArray() : (CallSiteArray)RFixPatchMonitor.$callSiteArray.get();
        RFixPatchMonitor.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/rfix/build/gradle/feature/RFixPatchMonitor$IShouldMonitorTask
    public interface RFixPatchMonitor$IShouldMonitorTask {

        boolean shouldMonitorTask();

    }
    // class: com/tencent/rfix/build/gradle/feature/RFixPatchMonitor$IShouldMonitorTask
    public interface RFixPatchMonitor$IShouldMonitorTask {

        boolean shouldMonitorTask();

    }
    // class: com/tencent/rfix/build/gradle/feature/RFixPatchMonitor$_init_closure1
    public final class RFixPatchMonitor$_init_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchMonitor$_init_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = RFixPatchMonitor$_init_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = RFixPatchMonitor$_init_closure1.$getCallSiteArray();
            return siteArr0[0].callStatic(RFixPatchMonitor.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = RFixPatchMonitor$_init_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchMonitor$_init_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchMonitor$_init_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchMonitor$_init_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchMonitor$_init_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "reportPatchResult";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchMonitor$_init_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchMonitor$_init_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchMonitor$_init_closure1.$callSiteArray != null ? RFixPatchMonitor$_init_closure1.$createCallSiteArray() : (CallSiteArray)RFixPatchMonitor$_init_closure1.$callSiteArray.get();
            RFixPatchMonitor$_init_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/feature/RFixPatchMonitor$_init_closure1
    public final class RFixPatchMonitor$_init_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchMonitor$_init_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = RFixPatchMonitor$_init_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = RFixPatchMonitor$_init_closure1.$getCallSiteArray();
            return siteArr0[0].callStatic(RFixPatchMonitor.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = RFixPatchMonitor$_init_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchMonitor$_init_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchMonitor$_init_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchMonitor$_init_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchMonitor$_init_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "reportPatchResult";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchMonitor$_init_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchMonitor$_init_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchMonitor$_init_closure1.$callSiteArray != null ? RFixPatchMonitor$_init_closure1.$createCallSiteArray() : (CallSiteArray)RFixPatchMonitor$_init_closure1.$callSiteArray.get();
            RFixPatchMonitor$_init_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
