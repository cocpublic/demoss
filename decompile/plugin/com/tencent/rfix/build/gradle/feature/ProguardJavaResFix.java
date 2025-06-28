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
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import com.tencent.rfix.build.gradle.extension.RFixPatchExtension;
import com.android.build.gradle.api.ApkVariant;
import java.io.File;

// class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix
public class ProguardJavaResFix implements GroovyObject {
    final private static Object TAG;
    protected Project project;
    protected RFixPatchExtension patchExtension;
    protected ApkVariant variant;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public ProguardJavaResFix(Project project, RFixPatchExtension patchExtension, ApkVariant variant) {
        CallSite[] siteArr0 = ProguardJavaResFix.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        (Project)ScriptBytecodeAdapter.castToType(project, Project.class).project = this;
        (RFixPatchExtension)ScriptBytecodeAdapter.castToType(patchExtension, RFixPatchExtension.class).patchExtension = this;
        (ApkVariant)ScriptBytecodeAdapter.castToType(variant, ApkVariant.class).variant = this;
    }

    public void fixProguardJavaRes() {
        CallSite[] siteArr0 = ProguardJavaResFix.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].callGetProperty(siteArr0[1].callGroovyObjectGetProperty(this.patchExtension))) ? 0 : 1 != 0) {
            siteArr0[2].callCurrent(this, new GStringImpl(new Object[]{ProguardJavaResFix.TAG}, new String[]{"", ": proguard java res fix disabled."}));
        }
        else {
            Object disableR8 = siteArr0[3].callCurrent(this, this.project);
            if (DefaultTypeTransformation.booleanUnbox(disableR8) ? 0 : 1 != 0) {
                siteArr0[4].callCurrent(this, new GStringImpl(new Object[]{ProguardJavaResFix.TAG}, new String[]{"", ": proguard not used, do not need fix java res."}));
            }
            else {
                Object variantName = siteArr0[5].callGetProperty(this.variant);
                Object capitalizedVariantName = siteArr0[6].call(variantName);
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(capitalizedVariantName, "Debug"))) {
                    siteArr0[8].callCurrent(this, new GStringImpl(new Object[]{ProguardJavaResFix.TAG, variantName}, new String[]{"", ": debug version no need fix, variantName=", "."}));
                }
                else {
                    GStringImpl taskName = new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"package", ""});
                    Object packageTask = siteArr0[9].call(siteArr0[10].callGetProperty(this.project), taskName);
                    if (ScriptBytecodeAdapter.compareEqual(packageTask, null)) {
                        siteArr0[11].callCurrent(this, new GStringImpl(new Object[]{ProguardJavaResFix.TAG, taskName}, new String[]{"", ": do not find task: ", "."}));
                    }
                    else {
                        v_163 = alloc(Reference);
                        new new GStringImpl(new Object[]{siteArr0[12].callGetProperty(this.project), variantName}, new String[]{"", "/intermediates/merged_java_res/", "/out.jar"}).<init>(v_163);
                        Reference javaResPath = v_163;
                        v_174 = alloc(Reference);
                        new siteArr0[13].callCurrent(this, this.project, this.variant).<init>(v_174);
                        Reference minifiedPath = v_174;
                        v_188 = alloc(Reference);
                        new new GStringImpl(new Object[]{minifiedPath.get()}, new String[]{"", ".bak"}).<init>(v_188);
                        Reference minifiedBackupPath = v_188;
                        siteArr0[14].call(packageTask, new ProguardJavaResFix$_fixProguardJavaRes_closure1(this, this, javaResPath, minifiedPath, minifiedBackupPath));
                        siteArr0[15].call(packageTask, new ProguardJavaResFix$_fixProguardJavaRes_closure2(this, this, minifiedPath, minifiedBackupPath));
                        siteArr0[16].callCurrent(this, new GStringImpl(new Object[]{ProguardJavaResFix.TAG, taskName}, new String[]{"", ": task '", "' config complete."}));
                    }
                }
            }
        }
    }

    protected boolean isR8Disabled(Project project) {
        CallSite[] siteArr0 = ProguardJavaResFix.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[17].call(project, "android.enableR8")) && ScriptBytecodeAdapter.compareEqual(siteArr0[18].call(project, "android.enableR8"), "false")) {
            return true;
        }
        else {
            return false;
        }
    }

    protected String getMinifiedPath(Project project, ApkVariant variant) {
        CallSite[] siteArr0 = ProguardJavaResFix.$getCallSiteArray();
        Object agpVersion = siteArr0[19].call(GradleUtils.class, project);
        if (ScriptBytecodeAdapter.compareNotEqual(agpVersion, null) && DefaultTypeTransformation.booleanUnbox(siteArr0[20].call(agpVersion, "3.5")) ? 0 : 1 != 0) {
            Object flavorName = siteArr0[21].callGetProperty(variant);
            return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[22].callGetProperty(project), flavorName}, new String[]{"", "/intermediates/transforms/proguard/", "/release/0.jar"}));
        }
        else {
            Object variantName = siteArr0[23].callGetProperty(variant);
            return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[24].callGetProperty(project), variantName}, new String[]{"", "/intermediates/shrunk_jar/", "/minified.jar"}));
        }
    }

    protected void replaceMinified(String javaResPath, String minifiedPath, String minifiedBackupPath) {
        CallSite[] siteArr0 = ProguardJavaResFix.$getCallSiteArray();
        siteArr0[25].callCurrent(this, new GStringImpl(new Object[]{ProguardJavaResFix.TAG, minifiedPath, javaResPath}, new String[]{"", ": replace ", " by ", ""}));
        Object javaRes = siteArr0[26].callConstructor(File.class, javaResPath);
        Object minified = siteArr0[27].callConstructor(File.class, minifiedPath);
        Object minifiedBackup = siteArr0[28].callConstructor(File.class, minifiedBackupPath);
        if (BytecodeInterface8.isOrigZ() && ProguardJavaResFix.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 261;
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[33].call(javaRes)) ? 0 : 1 == 0) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[34].call(minified)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[35].call(siteArr0[36].callGetProperty(this.project), new GStringImpl(new Object[]{ProguardJavaResFix.TAG}, new String[]{"", ": javaRes or minified not exists? "}));
            }
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[29].call(javaRes)) ? 0 : 1 == 0) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[30].call(minified)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[31].call(siteArr0[32].callGetProperty(this.project), new GStringImpl(new Object[]{ProguardJavaResFix.TAG}, new String[]{"", ": javaRes or minified not exists? "}));
            }
            else {
            }
        }
        siteArr0[37].callCurrent(this, minified, minifiedBackup);
        siteArr0[38].callCurrent(this, javaRes, minified);
    }

    protected void revertMinified(String minifiedPath, String minifiedBackupPath) {
        CallSite[] siteArr0 = ProguardJavaResFix.$getCallSiteArray();
        siteArr0[39].callCurrent(this, new GStringImpl(new Object[]{ProguardJavaResFix.TAG, minifiedPath}, new String[]{"", ": revert ", ""}));
        Object minified = siteArr0[40].callConstructor(File.class, minifiedPath);
        Object minifiedBackup = siteArr0[41].callConstructor(File.class, minifiedBackupPath);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[42].call(minifiedBackup)) ? 0 : 1 != 0) {
            siteArr0[43].callCurrent(this, new GStringImpl(new Object[]{ProguardJavaResFix.TAG}, new String[]{"", ": minifiedBackup not exists? "}));
        }
        else {
            siteArr0[44].callCurrent(this, minifiedBackup, minified);
            siteArr0[45].call(minifiedBackup);
        }
    }

    protected void copyFile(File src, File dest) {
        v_1 = alloc(Reference);
        new dest.<init>(v_1);
        Reference reference = v_1;
        CallSite[] siteArr0 = ProguardJavaResFix.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[46].call((File)reference.get())) ? 0 : 1 != 0) {
            siteArr0[47].call((File)reference.get());
        }
        siteArr0[48].call(src, new ProguardJavaResFix$_copyFile_closure3(this, this, reference));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ProguardJavaResFix.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (ProguardJavaResFix.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                ProguardJavaResFix.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return ProguardJavaResFix.$staticClassInfo.getMetaClass();
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
        String str0 = "ProguardJavaResFix";
        ProguardJavaResFix.TAG = str0;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "enableProguardJavaResFix";
        stringArr0[1] = "buildConfig";
        stringArr0[2] = "println";
        stringArr0[3] = "isR8Disabled";
        stringArr0[4] = "println";
        stringArr0[5] = "name";
        stringArr0[6] = "capitalize";
        stringArr0[7] = "endsWith";
        stringArr0[8] = "println";
        stringArr0[9] = "findByName";
        stringArr0[10] = "tasks";
        stringArr0[11] = "println";
        stringArr0[12] = "buildDir";
        stringArr0[13] = "getMinifiedPath";
        stringArr0[14] = "doFirst";
        stringArr0[15] = "doLast";
        stringArr0[16] = "println";
        stringArr0[17] = "hasProperty";
        stringArr0[18] = "property";
        stringArr0[19] = "getAGPVersion";
        stringArr0[20] = "startsWith";
        stringArr0[21] = "flavorName";
        stringArr0[22] = "buildDir";
        stringArr0[23] = "name";
        stringArr0[24] = "buildDir";
        stringArr0[25] = "println";
        stringArr0[26] = "<$constructor$>";
        stringArr0[27] = "<$constructor$>";
        stringArr0[28] = "<$constructor$>";
        stringArr0[29] = "exists";
        stringArr0[30] = "exists";
        stringArr0[31] = "error";
        stringArr0[32] = "logger";
        stringArr0[33] = "exists";
        stringArr0[34] = "exists";
        stringArr0[35] = "error";
        stringArr0[36] = "logger";
        stringArr0[37] = "copyFile";
        stringArr0[38] = "copyFile";
        stringArr0[39] = "println";
        stringArr0[40] = "<$constructor$>";
        stringArr0[41] = "<$constructor$>";
        stringArr0[42] = "exists";
        stringArr0[43] = "println";
        stringArr0[44] = "copyFile";
        stringArr0[45] = "delete";
        stringArr0[46] = "exists";
        stringArr0[47] = "createNewFile";
        stringArr0[48] = "withDataInputStream";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        ProguardJavaResFix.$createCallSiteArray_1(str0);
        return new CallSiteArray(ProguardJavaResFix.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = ProguardJavaResFix.$callSiteArray != null ? ProguardJavaResFix.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix.$callSiteArray.get();
        ProguardJavaResFix.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix$_fixProguardJavaRes_closure1
    public final class ProguardJavaResFix$_fixProguardJavaRes_closure1 implements GeneratedClosure {
        private synthetic Reference javaResPath;
        private synthetic Reference minifiedPath;
        private synthetic Reference minifiedBackupPath;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ProguardJavaResFix$_fixProguardJavaRes_closure1(Object _outerInstance, Object _thisObject, Reference javaResPath, Reference minifiedPath, Reference minifiedBackupPath) {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            javaResPath.javaResPath = this;
            minifiedPath.minifiedPath = this;
            minifiedBackupPath.minifiedBackupPath = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            return siteArr0[0].callCurrent(this, this.javaResPath.get(), this.minifiedPath.get(), this.minifiedBackupPath.get());
        }

        @Generated
        public Object getJavaResPath() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            return this.javaResPath.get();
        }

        @Generated
        public Object getMinifiedPath() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            return this.minifiedPath.get();
        }

        @Generated
        public Object getMinifiedBackupPath() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            return this.minifiedBackupPath.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ProguardJavaResFix$_fixProguardJavaRes_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ProguardJavaResFix$_fixProguardJavaRes_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ProguardJavaResFix$_fixProguardJavaRes_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ProguardJavaResFix$_fixProguardJavaRes_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "replaceMinified";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ProguardJavaResFix$_fixProguardJavaRes_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(ProguardJavaResFix$_fixProguardJavaRes_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ProguardJavaResFix$_fixProguardJavaRes_closure1.$callSiteArray != null ? ProguardJavaResFix$_fixProguardJavaRes_closure1.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix$_fixProguardJavaRes_closure1.$callSiteArray.get();
            ProguardJavaResFix$_fixProguardJavaRes_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix$_fixProguardJavaRes_closure1
    public final class ProguardJavaResFix$_fixProguardJavaRes_closure1 implements GeneratedClosure {
        private synthetic Reference javaResPath;
        private synthetic Reference minifiedPath;
        private synthetic Reference minifiedBackupPath;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ProguardJavaResFix$_fixProguardJavaRes_closure1(Object _outerInstance, Object _thisObject, Reference javaResPath, Reference minifiedPath, Reference minifiedBackupPath) {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            javaResPath.javaResPath = this;
            minifiedPath.minifiedPath = this;
            minifiedBackupPath.minifiedBackupPath = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            return siteArr0[0].callCurrent(this, this.javaResPath.get(), this.minifiedPath.get(), this.minifiedBackupPath.get());
        }

        @Generated
        public Object getJavaResPath() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            return this.javaResPath.get();
        }

        @Generated
        public Object getMinifiedPath() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            return this.minifiedPath.get();
        }

        @Generated
        public Object getMinifiedBackupPath() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            return this.minifiedBackupPath.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ProguardJavaResFix$_fixProguardJavaRes_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ProguardJavaResFix$_fixProguardJavaRes_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ProguardJavaResFix$_fixProguardJavaRes_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ProguardJavaResFix$_fixProguardJavaRes_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "replaceMinified";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ProguardJavaResFix$_fixProguardJavaRes_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(ProguardJavaResFix$_fixProguardJavaRes_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ProguardJavaResFix$_fixProguardJavaRes_closure1.$callSiteArray != null ? ProguardJavaResFix$_fixProguardJavaRes_closure1.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix$_fixProguardJavaRes_closure1.$callSiteArray.get();
            ProguardJavaResFix$_fixProguardJavaRes_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix$_fixProguardJavaRes_closure2
    public final class ProguardJavaResFix$_fixProguardJavaRes_closure2 implements GeneratedClosure {
        private synthetic Reference minifiedPath;
        private synthetic Reference minifiedBackupPath;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ProguardJavaResFix$_fixProguardJavaRes_closure2(Object _outerInstance, Object _thisObject, Reference minifiedPath, Reference minifiedBackupPath) {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            minifiedPath.minifiedPath = this;
            minifiedBackupPath.minifiedBackupPath = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure2.$getCallSiteArray();
            return siteArr0[0].callCurrent(this, this.minifiedPath.get(), this.minifiedBackupPath.get());
        }

        @Generated
        public Object getMinifiedPath() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure2.$getCallSiteArray();
            return this.minifiedPath.get();
        }

        @Generated
        public Object getMinifiedBackupPath() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure2.$getCallSiteArray();
            return this.minifiedBackupPath.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ProguardJavaResFix$_fixProguardJavaRes_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ProguardJavaResFix$_fixProguardJavaRes_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ProguardJavaResFix$_fixProguardJavaRes_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ProguardJavaResFix$_fixProguardJavaRes_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "revertMinified";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ProguardJavaResFix$_fixProguardJavaRes_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(ProguardJavaResFix$_fixProguardJavaRes_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ProguardJavaResFix$_fixProguardJavaRes_closure2.$callSiteArray != null ? ProguardJavaResFix$_fixProguardJavaRes_closure2.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix$_fixProguardJavaRes_closure2.$callSiteArray.get();
            ProguardJavaResFix$_fixProguardJavaRes_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix$_fixProguardJavaRes_closure2
    public final class ProguardJavaResFix$_fixProguardJavaRes_closure2 implements GeneratedClosure {
        private synthetic Reference minifiedPath;
        private synthetic Reference minifiedBackupPath;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ProguardJavaResFix$_fixProguardJavaRes_closure2(Object _outerInstance, Object _thisObject, Reference minifiedPath, Reference minifiedBackupPath) {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            minifiedPath.minifiedPath = this;
            minifiedBackupPath.minifiedBackupPath = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure2.$getCallSiteArray();
            return siteArr0[0].callCurrent(this, this.minifiedPath.get(), this.minifiedBackupPath.get());
        }

        @Generated
        public Object getMinifiedPath() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure2.$getCallSiteArray();
            return this.minifiedPath.get();
        }

        @Generated
        public Object getMinifiedBackupPath() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure2.$getCallSiteArray();
            return this.minifiedBackupPath.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = ProguardJavaResFix$_fixProguardJavaRes_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ProguardJavaResFix$_fixProguardJavaRes_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ProguardJavaResFix$_fixProguardJavaRes_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ProguardJavaResFix$_fixProguardJavaRes_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ProguardJavaResFix$_fixProguardJavaRes_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "revertMinified";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ProguardJavaResFix$_fixProguardJavaRes_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(ProguardJavaResFix$_fixProguardJavaRes_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ProguardJavaResFix$_fixProguardJavaRes_closure2.$callSiteArray != null ? ProguardJavaResFix$_fixProguardJavaRes_closure2.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix$_fixProguardJavaRes_closure2.$callSiteArray.get();
            ProguardJavaResFix$_fixProguardJavaRes_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix$_copyFile_closure3
    public final class ProguardJavaResFix$_copyFile_closure3 implements GeneratedClosure {
        private synthetic Reference dest;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ProguardJavaResFix$_copyFile_closure3(Object _outerInstance, Object _thisObject, Reference dest) {
            CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            dest.dest = this;
        }

        public Object doCall(Object input) {
            v_1 = alloc(Reference);
            new input.<init>(v_1);
            Reference reference = v_1;
            CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3.$getCallSiteArray();
            return siteArr0[0].call(this.dest.get(), new ProguardJavaResFix$_copyFile_closure3$_closure4(this, this.getThisObject(), reference));
        }

        @Generated
        public File getDest() {
            CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.dest.get(), File.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ProguardJavaResFix$_copyFile_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ProguardJavaResFix$_copyFile_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ProguardJavaResFix$_copyFile_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ProguardJavaResFix$_copyFile_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "withOutputStream";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ProguardJavaResFix$_copyFile_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(ProguardJavaResFix$_copyFile_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ProguardJavaResFix$_copyFile_closure3.$callSiteArray != null ? ProguardJavaResFix$_copyFile_closure3.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix$_copyFile_closure3.$callSiteArray.get();
            ProguardJavaResFix$_copyFile_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix$_copyFile_closure3$_closure4
        public final class ProguardJavaResFix$_copyFile_closure3$_closure4 implements GeneratedClosure {
            private synthetic Reference input;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ProguardJavaResFix$_copyFile_closure3$_closure4(Object _outerInstance, Object _thisObject, Reference input) {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                input.input = this;
            }

            public Object doCall(Object output) {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                return siteArr0[0].call(output, this.input.get());
            }

            @Generated
            public Object getInput() {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                return this.input.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ProguardJavaResFix$_copyFile_closure3$_closure4.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "leftShift";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ProguardJavaResFix$_copyFile_closure3$_closure4.$createCallSiteArray_1(str0);
                return new CallSiteArray(ProguardJavaResFix$_copyFile_closure3$_closure4.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray != null ? ProguardJavaResFix$_copyFile_closure3$_closure4.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray.get();
                ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix$_copyFile_closure3$_closure4
        public final class ProguardJavaResFix$_copyFile_closure3$_closure4 implements GeneratedClosure {
            private synthetic Reference input;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ProguardJavaResFix$_copyFile_closure3$_closure4(Object _outerInstance, Object _thisObject, Reference input) {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                input.input = this;
            }

            public Object doCall(Object output) {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                return siteArr0[0].call(output, this.input.get());
            }

            @Generated
            public Object getInput() {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                return this.input.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ProguardJavaResFix$_copyFile_closure3$_closure4.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "leftShift";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ProguardJavaResFix$_copyFile_closure3$_closure4.$createCallSiteArray_1(str0);
                return new CallSiteArray(ProguardJavaResFix$_copyFile_closure3$_closure4.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray != null ? ProguardJavaResFix$_copyFile_closure3$_closure4.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray.get();
                ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix$_copyFile_closure3
    public final class ProguardJavaResFix$_copyFile_closure3 implements GeneratedClosure {
        private synthetic Reference dest;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ProguardJavaResFix$_copyFile_closure3(Object _outerInstance, Object _thisObject, Reference dest) {
            CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            dest.dest = this;
        }

        public Object doCall(Object input) {
            v_1 = alloc(Reference);
            new input.<init>(v_1);
            Reference reference = v_1;
            CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3.$getCallSiteArray();
            return siteArr0[0].call(this.dest.get(), new ProguardJavaResFix$_copyFile_closure3$_closure4(this, this.getThisObject(), reference));
        }

        @Generated
        public File getDest() {
            CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.dest.get(), File.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ProguardJavaResFix$_copyFile_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ProguardJavaResFix$_copyFile_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ProguardJavaResFix$_copyFile_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ProguardJavaResFix$_copyFile_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "withOutputStream";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ProguardJavaResFix$_copyFile_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(ProguardJavaResFix$_copyFile_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ProguardJavaResFix$_copyFile_closure3.$callSiteArray != null ? ProguardJavaResFix$_copyFile_closure3.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix$_copyFile_closure3.$callSiteArray.get();
            ProguardJavaResFix$_copyFile_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix$_copyFile_closure3$_closure4
        public final class ProguardJavaResFix$_copyFile_closure3$_closure4 implements GeneratedClosure {
            private synthetic Reference input;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ProguardJavaResFix$_copyFile_closure3$_closure4(Object _outerInstance, Object _thisObject, Reference input) {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                input.input = this;
            }

            public Object doCall(Object output) {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                return siteArr0[0].call(output, this.input.get());
            }

            @Generated
            public Object getInput() {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                return this.input.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ProguardJavaResFix$_copyFile_closure3$_closure4.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "leftShift";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ProguardJavaResFix$_copyFile_closure3$_closure4.$createCallSiteArray_1(str0);
                return new CallSiteArray(ProguardJavaResFix$_copyFile_closure3$_closure4.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray != null ? ProguardJavaResFix$_copyFile_closure3$_closure4.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray.get();
                ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/rfix/build/gradle/feature/ProguardJavaResFix$_copyFile_closure3$_closure4
        public final class ProguardJavaResFix$_copyFile_closure3$_closure4 implements GeneratedClosure {
            private synthetic Reference input;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ProguardJavaResFix$_copyFile_closure3$_closure4(Object _outerInstance, Object _thisObject, Reference input) {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                input.input = this;
            }

            public Object doCall(Object output) {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                return siteArr0[0].call(output, this.input.get());
            }

            @Generated
            public Object getInput() {
                CallSite[] siteArr0 = ProguardJavaResFix$_copyFile_closure3$_closure4.$getCallSiteArray();
                return this.input.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ProguardJavaResFix$_copyFile_closure3$_closure4.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ProguardJavaResFix$_copyFile_closure3$_closure4.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "leftShift";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ProguardJavaResFix$_copyFile_closure3$_closure4.$createCallSiteArray_1(str0);
                return new CallSiteArray(ProguardJavaResFix$_copyFile_closure3$_closure4.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray != null ? ProguardJavaResFix$_copyFile_closure3$_closure4.$createCallSiteArray() : (CallSiteArray)ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray.get();
                ProguardJavaResFix$_copyFile_closure3$_closure4.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
}
