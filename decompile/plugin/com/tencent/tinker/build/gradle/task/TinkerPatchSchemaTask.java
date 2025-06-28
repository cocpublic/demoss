/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/gradle/task;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.TaskAction;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import com.tencent.tinker.build.gradle.extension.TinkerPatchExtension;
import com.tencent.tinker.build.patch.InputParam$Builder;
import com.tencent.tinker.build.patch.InputParam;
import java.util.List;
import java.util.TreeSet;
import java.util.HashSet;
import java.io.File;

// class: com/tencent/tinker/build/gradle/task/TinkerPatchSchemaTask
public class TinkerPatchSchemaTask implements GroovyObject {
    @Internal
    private TinkerPatchExtension configuration;
    @Internal
    private String buildApkPath;
    @Internal
    private Object signConfig;
    @Internal
    private String outputFolder;
    @Internal
    private Object android;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerPatchSchemaTask() {
        CallSite[] siteArr0 = TinkerPatchSchemaTask.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        String str0 = "Assemble Tinker Patch";
        ScriptBytecodeAdapter.setGroovyObjectProperty(str0, TinkerPatchSchemaTask.class, this, (String)"description");
        String str1 = "tinker";
        ScriptBytecodeAdapter.setGroovyObjectProperty(str1, TinkerPatchSchemaTask.class, this, (String)"group");
        siteArr0[0].call(siteArr0[1].callGroovyObjectGetProperty(this), new TinkerPatchSchemaTask$_closure1(this, this));
        Object object = siteArr0[2].callGetProperty(siteArr0[3].callGroovyObjectGetProperty(this));
        (TinkerPatchExtension)ScriptBytecodeAdapter.castToType(object, TinkerPatchExtension.class).configuration = this;
        Object objectVar1 = siteArr0[4].callGetProperty(siteArr0[5].callGetProperty(siteArr0[6].callGroovyObjectGetProperty(this)));
        objectVar1.android = this;
    }

    @TaskAction
    public Object tinkerPatch() {
        CallSite[] siteArr0 = TinkerPatchSchemaTask.$getCallSiteArray();
        siteArr0[7].call(this.configuration);
        siteArr0[8].call(siteArr0[9].callGroovyObjectGetProperty(this.configuration));
        siteArr0[10].call(siteArr0[11].callGroovyObjectGetProperty(this.configuration));
        siteArr0[12].call(siteArr0[13].callGroovyObjectGetProperty(this.configuration));
        siteArr0[14].call(siteArr0[15].callGroovyObjectGetProperty(this.configuration));
        InputParam$Builder builder = (InputParam$Builder)ScriptBytecodeAdapter.castToType(siteArr0[16].callConstructor(InputParam$Builder.class), InputParam$Builder.class);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[17].callGroovyObjectGetProperty(this.configuration))) {
            if (ScriptBytecodeAdapter.compareEqual(this.signConfig, null)) {
                throw (Throwable)siteArr0[18].callConstructor(GradleException.class, "can't the get signConfig for this build");
            }
            else {
                siteArr0[19].call(siteArr0[20].call(siteArr0[21].call(siteArr0[22].call(builder, siteArr0[23].callGetProperty(this.signConfig)), siteArr0[24].callGetProperty(this.signConfig)), siteArr0[25].callGetProperty(this.signConfig)), siteArr0[26].callGetProperty(this.signConfig));
            }
        }
        Object buildApkFile = siteArr0[27].callConstructor(File.class, this.buildApkPath);
        Object oldApkFile = siteArr0[28].callConstructor(File.class, siteArr0[29].callGroovyObjectGetProperty(this.configuration));
        v_142 = alloc(Reference);
        new (TreeSet)ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[]), TreeSet.class).<init>(v_142);
        Reference newApks = v_142;
        v_150 = alloc(Reference);
        new (TreeSet)ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[]), TreeSet.class).<init>(v_150);
        Reference oldApks = v_150;
        v_158 = alloc(Reference);
        new (HashSet)ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[]), HashSet.class).<init>(v_158);
        Reference oldApkNames = v_158;
        v_166 = alloc(Reference);
        new (HashSet)ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[]), HashSet.class).<init>(v_166);
        Reference newApkNames = v_166;
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[30].call(buildApkFile)) && DefaultTypeTransformation.booleanUnbox(siteArr0[31].call(oldApkFile)) ? 0 : 1 != 0) {
            siteArr0[32].call(oldApkFile, new TinkerPatchSchemaTask$_tinkerPatch_closure2(this, this, oldApks, oldApkNames));
            siteArr0[33].call(buildApkFile, new TinkerPatchSchemaTask$_tinkerPatch_closure3(this, this, newApks, newApkNames));
            Object unmatchedOldApkNames = siteArr0[34].callConstructor(HashSet.class, oldApkNames.get());
            siteArr0[35].call(unmatchedOldApkNames, newApkNames.get());
            Object unmatchedNewApkNames = siteArr0[36].callConstructor(HashSet.class, newApkNames.get());
            siteArr0[37].call(unmatchedNewApkNames, oldApkNames.get());
            if (BytecodeInterface8.isOrigZ() && TinkerPatchSchemaTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 782;
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[44].call(unmatchedOldApkNames)) ? 0 : 1 == 0) {
                }
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[45].call(unmatchedNewApkNames)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                    throw (Throwable)siteArr0[46].callConstructor(GradleException.class, siteArr0[47].call(siteArr0[48].call(siteArr0[49].call("Both oldApk and newApk args are directories", " but apks inside them are not matched.
"), new GStringImpl(new Object[]{unmatchedOldApkNames}, new String[]{" unmatched old apks: ", "
"})), new GStringImpl(new Object[]{unmatchedNewApkNames}, new String[]{" unmatched new apks: ", "."})));
                }
            }
            else {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[38].call(unmatchedOldApkNames)) ? 0 : 1 == 0) {
                }
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[39].call(unmatchedNewApkNames)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                    throw (Throwable)siteArr0[40].callConstructor(GradleException.class, siteArr0[41].call(siteArr0[42].call(siteArr0[43].call("Both oldApk and newApk args are directories", " but apks inside them are not matched.
"), new GStringImpl(new Object[]{unmatchedOldApkNames}, new String[]{" unmatched old apks: ", "
"})), new GStringImpl(new Object[]{unmatchedNewApkNames}, new String[]{" unmatched new apks: ", "."})));
                }
                else {
                }
            }
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[50].call(buildApkFile)) && DefaultTypeTransformation.booleanUnbox(siteArr0[51].call(oldApkFile)) ? 0 : 1 != 0) {
                siteArr0[52].call(newApks.get(), buildApkFile);
                siteArr0[53].call(oldApks.get(), oldApkFile);
            }
            else {
                throw (Throwable)siteArr0[54].callConstructor(GradleException.class, new GStringImpl(new Object[]{siteArr0[55].call(oldApkFile), siteArr0[56].call(buildApkFile)}, new String[]{"oldApk [", "] and newApk [", "] must be both files or directories."}));
            }
        }
        Object tmpDir = siteArr0[57].callConstructor(File.class, new GStringImpl(new Object[]{siteArr0[58].callGetProperty(siteArr0[59].callGroovyObjectGetProperty(this))}, new String[]{"", "/tmp/tinkerPatch"}));
        siteArr0[60].call(tmpDir);
        v_329 = alloc(Reference);
        new siteArr0[61].callConstructor(File.class, this.outputFolder).<init>(v_329);
        Reference outputDir = v_329;
        siteArr0[62].call(outputDir.get());
        Integer i = Integer.valueOf(0);
        while (ScriptBytecodeAdapter.compareLessThan(i, siteArr0[63].call(newApks.get()))) {
            File oldApk = (File)ScriptBytecodeAdapter.asType(siteArr0[64].call(oldApks.get(), i), File.class);
            File newApk = (File)ScriptBytecodeAdapter.asType(siteArr0[65].call(newApks.get(), i), File.class);
            Object packageConfigFields = siteArr0[66].callConstructor(HashMap.class, siteArr0[67].call(siteArr0[68].callGroovyObjectGetProperty(this.configuration)));
            siteArr0[69].call(packageConfigFields, siteArr0[70].call(siteArr0[71].callGroovyObjectGetProperty(this.configuration), siteArr0[72].call(newApk)));
            siteArr0[73].call(siteArr0[74].call(siteArr0[75].call(siteArr0[76].call(siteArr0[77].call(siteArr0[78].call(siteArr0[79].call(siteArr0[80].call(siteArr0[81].call(siteArr0[82].call(siteArr0[83].call(siteArr0[84].call(siteArr0[85].call(siteArr0[86].call(siteArr0[87].call(siteArr0[88].call(siteArr0[89].call(siteArr0[90].call(siteArr0[91].call(siteArr0[92].call(siteArr0[93].call(siteArr0[94].call(siteArr0[95].call(siteArr0[96].call(siteArr0[97].call(builder, siteArr0[98].call(oldApk)), siteArr0[99].call(newApk)), siteArr0[100].call(tmpDir)), siteArr0[101].callGroovyObjectGetProperty(this.configuration)), siteArr0[102].callGroovyObjectGetProperty(this.configuration)), siteArr0[103].callGroovyObjectGetProperty(this.configuration)), siteArr0[104].callGroovyObjectGetProperty(this.configuration)), siteArr0[105].callGroovyObjectGetProperty(this.configuration)), siteArr0[106].callConstructor(ArrayList.class, siteArr0[107].callGetProperty(siteArr0[108].callGroovyObjectGetProperty(this.configuration)))), siteArr0[109].callGetProperty(siteArr0[110].callGroovyObjectGetProperty(this.configuration))), siteArr0[111].callGetProperty(siteArr0[112].callGroovyObjectGetProperty(this.configuration))), siteArr0[113].callConstructor(ArrayList.class, siteArr0[114].callGetProperty(siteArr0[115].callGroovyObjectGetProperty(this.configuration)))), siteArr0[116].callConstructor(ArrayList.class, siteArr0[117].callGetProperty(siteArr0[118].callGroovyObjectGetProperty(this.configuration)))), siteArr0[119].callGetProperty(siteArr0[120].callGroovyObjectGetProperty(this.configuration))), siteArr0[121].callConstructor(ArrayList.class, siteArr0[122].callGetProperty(siteArr0[123].callGroovyObjectGetProperty(this.configuration)))), siteArr0[124].callConstructor(ArrayList.class, siteArr0[125].callGetProperty(siteArr0[126].callGroovyObjectGetProperty(this.configuration)))), siteArr0[127].callConstructor(ArrayList.class, siteArr0[128].callGetProperty(siteArr0[129].callGroovyObjectGetProperty(this.configuration)))), siteArr0[130].callConstructor(ArrayList.class, siteArr0[131].callGetProperty(siteArr0[132].callGroovyObjectGetProperty(this.configuration)))), siteArr0[133].callGetProperty(siteArr0[134].callGroovyObjectGetProperty(this.configuration))), siteArr0[135].callGetProperty(siteArr0[136].callGroovyObjectGetProperty(this.configuration))), packageConfigFields), siteArr0[137].callGetProperty(siteArr0[138].callGroovyObjectGetProperty(this.configuration))), siteArr0[139].callGroovyObjectGetProperty(this.configuration)), siteArr0[140].callGetProperty(siteArr0[141].callGroovyObjectGetProperty(this.configuration))), siteArr0[142].callGetProperty(siteArr0[143].callGroovyObjectGetProperty(this.configuration)));
            InputParam inputParam = (InputParam)ScriptBytecodeAdapter.castToType(siteArr0[144].call(builder), InputParam.class);
            siteArr0[145].call(Runner.class, inputParam);
            v_778 = alloc(Reference);
            new siteArr0[146].call(siteArr0[147].callGetProperty(newApk), siteArr0[148].call(siteArr0[149].callGetProperty(newApk), ".")).<init>(v_778);
            Reference prefix = v_778;
            siteArr0[150].call(tmpDir, siteArr0[151].callGetProperty(FileType.class), new TinkerPatchSchemaTask$_tinkerPatch_closure4(this, this, outputDir, prefix));
            v_799 = i.call(siteArr0[152]);
            Object object = i.call(siteArr0[152]);
        }
        return null;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerPatchSchemaTask.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerPatchSchemaTask.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerPatchSchemaTask.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerPatchSchemaTask.$staticClassInfo.getMetaClass();
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
    public TinkerPatchExtension getConfiguration() {
        return this.configuration;
    }

    @Generated
    public void setConfiguration(TinkerPatchExtension extension) {
        this.configuration = extension;
    }

    @Generated
    public String getBuildApkPath() {
        return this.buildApkPath;
    }

    @Generated
    public void setBuildApkPath(String str0) {
        this.buildApkPath = str0;
    }

    @Generated
    public Object getSignConfig() {
        return this.signConfig;
    }

    @Generated
    public void setSignConfig(Object object) {
        this.signConfig = object;
    }

    @Generated
    public String getOutputFolder() {
        return this.outputFolder;
    }

    @Generated
    public void setOutputFolder(String str0) {
        this.outputFolder = str0;
    }

    @Generated
    public Object getAndroid() {
        return this.android;
    }

    @Generated
    public void setAndroid(Object object) {
        this.android = object;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "upToDateWhen";
        stringArr0[1] = "outputs";
        stringArr0[2] = "tinkerPatch";
        stringArr0[3] = "project";
        stringArr0[4] = "android";
        stringArr0[5] = "extensions";
        stringArr0[6] = "project";
        stringArr0[7] = "checkParameter";
        stringArr0[8] = "checkParameter";
        stringArr0[9] = "buildConfig";
        stringArr0[10] = "checkParameter";
        stringArr0[11] = "res";
        stringArr0[12] = "checkDexMode";
        stringArr0[13] = "dex";
        stringArr0[14] = "resolveZipFinalPath";
        stringArr0[15] = "sevenZip";
        stringArr0[16] = "<$constructor$>";
        stringArr0[17] = "useSign";
        stringArr0[18] = "<$constructor$>";
        stringArr0[19] = "setStorepass";
        stringArr0[20] = "setStorealias";
        stringArr0[21] = "setKeypass";
        stringArr0[22] = "setSignFile";
        stringArr0[23] = "storeFile";
        stringArr0[24] = "keyPassword";
        stringArr0[25] = "keyAlias";
        stringArr0[26] = "storePassword";
        stringArr0[27] = "<$constructor$>";
        stringArr0[28] = "<$constructor$>";
        stringArr0[29] = "oldApk";
        stringArr0[30] = "isDirectory";
        stringArr0[31] = "isDirectory";
        stringArr0[32] = "eachFile";
        stringArr0[33] = "eachFile";
        stringArr0[34] = "<$constructor$>";
        stringArr0[35] = "removeAll";
        stringArr0[36] = "<$constructor$>";
        stringArr0[37] = "removeAll";
        stringArr0[38] = "isEmpty";
        stringArr0[39] = "isEmpty";
        stringArr0[40] = "<$constructor$>";
        stringArr0[41] = "plus";
        stringArr0[42] = "plus";
        stringArr0[43] = "plus";
        stringArr0[44] = "isEmpty";
        stringArr0[45] = "isEmpty";
        stringArr0[46] = "<$constructor$>";
        stringArr0[47] = "plus";
        stringArr0[48] = "plus";
        stringArr0[49] = "plus";
        stringArr0[50] = "isFile";
        stringArr0[51] = "isFile";
        stringArr0[52] = "leftShift";
        stringArr0[53] = "leftShift";
        stringArr0[54] = "<$constructor$>";
        stringArr0[55] = "getAbsolutePath";
        stringArr0[56] = "getAbsolutePath";
        stringArr0[57] = "<$constructor$>";
        stringArr0[58] = "buildDir";
        stringArr0[59] = "project";
        stringArr0[60] = "mkdirs";
        stringArr0[61] = "<$constructor$>";
        stringArr0[62] = "mkdirs";
        stringArr0[63] = "size";
        stringArr0[64] = "getAt";
        stringArr0[65] = "getAt";
        stringArr0[66] = "<$constructor$>";
        stringArr0[67] = "getFields";
        stringArr0[68] = "packageConfig";
        stringArr0[69] = "putAll";
        stringArr0[70] = "getApkSpecFields";
        stringArr0[71] = "packageConfig";
        stringArr0[72] = "getName";
        stringArr0[73] = "setArkHotName";
        stringArr0[74] = "setArkHotPath";
        stringArr0[75] = "setUseSign";
        stringArr0[76] = "setSevenZipPath";
        stringArr0[77] = "setConfigFields";
        stringArr0[78] = "setUseApplyResource";
        stringArr0[79] = "setResourceLargeModSize";
        stringArr0[80] = "setResourceIgnoreChangeWarningPattern";
        stringArr0[81] = "setResourceIgnoreChangePattern";
        stringArr0[82] = "setResourceFilePattern";
        stringArr0[83] = "setSoFilePattern";
        stringArr0[84] = "setDexMode";
        stringArr0[85] = "setDexIgnoreWarningLoaderPattern";
        stringArr0[86] = "setDexLoaderPattern";
        stringArr0[87] = "setIsComponentHotplugSupported";
        stringArr0[88] = "setIsProtectedApp";
        stringArr0[89] = "setDexFilePattern";
        stringArr0[90] = "setRemoveLoaderForAllDex";
        stringArr0[91] = "setCustomDiffPathArgs";
        stringArr0[92] = "setCustomDiffPath";
        stringArr0[93] = "setAllowLoaderInAnyDex";
        stringArr0[94] = "setIgnoreWarning";
        stringArr0[95] = "setOutBuilder";
        stringArr0[96] = "setNewApk";
        stringArr0[97] = "setOldApk";
        stringArr0[98] = "getAbsolutePath";
        stringArr0[99] = "getAbsolutePath";
        stringArr0[100] = "getAbsolutePath";
        stringArr0[101] = "ignoreWarning";
        stringArr0[102] = "allowLoaderInAnyDex";
        stringArr0[103] = "customPath";
        stringArr0[104] = "customDiffPathArgs";
        stringArr0[105] = "removeLoaderForAllDex";
        stringArr0[106] = "<$constructor$>";
        stringArr0[107] = "pattern";
        stringArr0[108] = "dex";
        stringArr0[109] = "isProtectedApp";
        stringArr0[110] = "buildConfig";
        stringArr0[111] = "supportHotplugComponent";
        stringArr0[112] = "buildConfig";
        stringArr0[113] = "<$constructor$>";
        stringArr0[114] = "loader";
        stringArr0[115] = "dex";
        stringArr0[116] = "<$constructor$>";
        stringArr0[117] = "ignoreWarningLoader";
        stringArr0[118] = "dex";
        stringArr0[119] = "dexMode";
        stringArr0[120] = "dex";
        stringArr0[121] = "<$constructor$>";
        stringArr0[122] = "pattern";
        stringArr0[123] = "lib";
        stringArr0[124] = "<$constructor$>";
        stringArr0[125] = "pattern";
        stringArr0[126] = "res";
        stringArr0[127] = "<$constructor$>";
        stringArr0[128] = "ignoreChange";
        stringArr0[129] = "res";
        stringArr0[130] = "<$constructor$>";
        stringArr0[131] = "ignoreChangeWarning";
        stringArr0[132] = "res";
        stringArr0[133] = "largeModSize";
        stringArr0[134] = "res";
        stringArr0[135] = "usingResourceMapping";
        stringArr0[136] = "buildConfig";
        stringArr0[137] = "path";
        stringArr0[138] = "sevenZip";
        stringArr0[139] = "useSign";
        stringArr0[140] = "path";
        stringArr0[141] = "arkHot";
        stringArr0[142] = "name";
        stringArr0[143] = "arkHot";
        stringArr0[144] = "create";
        stringArr0[145] = "gradleRun";
        stringArr0[146] = "take";
        stringArr0[147] = "name";
        stringArr0[148] = "lastIndexOf";
        stringArr0[149] = "name";
        stringArr0[150] = "eachFile";
        stringArr0[151] = "FILES";
        stringArr0[152] = "next";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerPatchSchemaTask.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerPatchSchemaTask.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerPatchSchemaTask.$callSiteArray != null ? TinkerPatchSchemaTask.$createCallSiteArray() : (CallSiteArray)TinkerPatchSchemaTask.$callSiteArray.get();
        TinkerPatchSchemaTask.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/tinker/build/gradle/task/TinkerPatchSchemaTask$_tinkerPatch_closure2
    public final class TinkerPatchSchemaTask$_tinkerPatch_closure2 implements GeneratedClosure {
        private synthetic Reference oldApks;
        private synthetic Reference oldApkNames;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPatchSchemaTask$_tinkerPatch_closure2(Object _outerInstance, Object _thisObject, Reference oldApks, Reference oldApkNames) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            oldApks.oldApks = this;
            oldApkNames.oldApkNames = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure2.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(it), ".apk"))) {
                siteArr0[2].call(this.oldApks.get(), it);
                return siteArr0[3].call(this.oldApkNames.get(), siteArr0[4].call(it));
            }
            else {
                return null;
            }
        }

        @Generated
        public Object getOldApks() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure2.$getCallSiteArray();
            return this.oldApks.get();
        }

        @Generated
        public Object getOldApkNames() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure2.$getCallSiteArray();
            return this.oldApkNames.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPatchSchemaTask$_tinkerPatch_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPatchSchemaTask$_tinkerPatch_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPatchSchemaTask$_tinkerPatch_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPatchSchemaTask$_tinkerPatch_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "leftShift";
            stringArr0[3] = "leftShift";
            stringArr0[4] = "getName";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPatchSchemaTask$_tinkerPatch_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPatchSchemaTask$_tinkerPatch_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPatchSchemaTask$_tinkerPatch_closure2.$callSiteArray != null ? TinkerPatchSchemaTask$_tinkerPatch_closure2.$createCallSiteArray() : (CallSiteArray)TinkerPatchSchemaTask$_tinkerPatch_closure2.$callSiteArray.get();
            TinkerPatchSchemaTask$_tinkerPatch_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerPatchSchemaTask$_tinkerPatch_closure2
    public final class TinkerPatchSchemaTask$_tinkerPatch_closure2 implements GeneratedClosure {
        private synthetic Reference oldApks;
        private synthetic Reference oldApkNames;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPatchSchemaTask$_tinkerPatch_closure2(Object _outerInstance, Object _thisObject, Reference oldApks, Reference oldApkNames) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            oldApks.oldApks = this;
            oldApkNames.oldApkNames = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure2.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(it), ".apk"))) {
                siteArr0[2].call(this.oldApks.get(), it);
                return siteArr0[3].call(this.oldApkNames.get(), siteArr0[4].call(it));
            }
            else {
                return null;
            }
        }

        @Generated
        public Object getOldApks() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure2.$getCallSiteArray();
            return this.oldApks.get();
        }

        @Generated
        public Object getOldApkNames() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure2.$getCallSiteArray();
            return this.oldApkNames.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPatchSchemaTask$_tinkerPatch_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPatchSchemaTask$_tinkerPatch_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPatchSchemaTask$_tinkerPatch_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPatchSchemaTask$_tinkerPatch_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "leftShift";
            stringArr0[3] = "leftShift";
            stringArr0[4] = "getName";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPatchSchemaTask$_tinkerPatch_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPatchSchemaTask$_tinkerPatch_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPatchSchemaTask$_tinkerPatch_closure2.$callSiteArray != null ? TinkerPatchSchemaTask$_tinkerPatch_closure2.$createCallSiteArray() : (CallSiteArray)TinkerPatchSchemaTask$_tinkerPatch_closure2.$callSiteArray.get();
            TinkerPatchSchemaTask$_tinkerPatch_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerPatchSchemaTask$_tinkerPatch_closure4
    public final class TinkerPatchSchemaTask$_tinkerPatch_closure4 implements GeneratedClosure {
        private synthetic Reference outputDir;
        private synthetic Reference prefix;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPatchSchemaTask$_tinkerPatch_closure4(Object _outerInstance, Object _thisObject, Reference outputDir, Reference prefix) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure4.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            outputDir.outputDir = this;
            prefix.prefix = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure4.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(it), ".apk")) ? 0 : 1 != 0) {
                return null;
            }
            else {
                File dest = (File)ScriptBytecodeAdapter.castToType(siteArr0[2].callConstructor(File.class, this.outputDir.get(), new GStringImpl(new Object[]{this.prefix.get(), siteArr0[3].callGetProperty(it)}, new String[]{"", "-", ""})), File.class);
                return siteArr0[4].call(it, dest);
            }
        }

        @Generated
        public Object getOutputDir() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure4.$getCallSiteArray();
            return this.outputDir.get();
        }

        @Generated
        public Object getPrefix() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure4.$getCallSiteArray();
            return this.prefix.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure4.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPatchSchemaTask$_tinkerPatch_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPatchSchemaTask$_tinkerPatch_closure4.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPatchSchemaTask$_tinkerPatch_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPatchSchemaTask$_tinkerPatch_closure4.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "<$constructor$>";
            stringArr0[3] = "name";
            stringArr0[4] = "renameTo";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPatchSchemaTask$_tinkerPatch_closure4.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPatchSchemaTask$_tinkerPatch_closure4.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPatchSchemaTask$_tinkerPatch_closure4.$callSiteArray != null ? TinkerPatchSchemaTask$_tinkerPatch_closure4.$createCallSiteArray() : (CallSiteArray)TinkerPatchSchemaTask$_tinkerPatch_closure4.$callSiteArray.get();
            TinkerPatchSchemaTask$_tinkerPatch_closure4.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerPatchSchemaTask$_tinkerPatch_closure4
    public final class TinkerPatchSchemaTask$_tinkerPatch_closure4 implements GeneratedClosure {
        private synthetic Reference outputDir;
        private synthetic Reference prefix;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPatchSchemaTask$_tinkerPatch_closure4(Object _outerInstance, Object _thisObject, Reference outputDir, Reference prefix) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure4.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            outputDir.outputDir = this;
            prefix.prefix = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure4.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(it), ".apk")) ? 0 : 1 != 0) {
                return null;
            }
            else {
                File dest = (File)ScriptBytecodeAdapter.castToType(siteArr0[2].callConstructor(File.class, this.outputDir.get(), new GStringImpl(new Object[]{this.prefix.get(), siteArr0[3].callGetProperty(it)}, new String[]{"", "-", ""})), File.class);
                return siteArr0[4].call(it, dest);
            }
        }

        @Generated
        public Object getOutputDir() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure4.$getCallSiteArray();
            return this.outputDir.get();
        }

        @Generated
        public Object getPrefix() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure4.$getCallSiteArray();
            return this.prefix.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure4.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPatchSchemaTask$_tinkerPatch_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPatchSchemaTask$_tinkerPatch_closure4.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPatchSchemaTask$_tinkerPatch_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPatchSchemaTask$_tinkerPatch_closure4.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "<$constructor$>";
            stringArr0[3] = "name";
            stringArr0[4] = "renameTo";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPatchSchemaTask$_tinkerPatch_closure4.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPatchSchemaTask$_tinkerPatch_closure4.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPatchSchemaTask$_tinkerPatch_closure4.$callSiteArray != null ? TinkerPatchSchemaTask$_tinkerPatch_closure4.$createCallSiteArray() : (CallSiteArray)TinkerPatchSchemaTask$_tinkerPatch_closure4.$callSiteArray.get();
            TinkerPatchSchemaTask$_tinkerPatch_closure4.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerPatchSchemaTask$_closure1
    public final class TinkerPatchSchemaTask$_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPatchSchemaTask$_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_closure1.$getCallSiteArray();
            return Boolean.valueOf(false);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPatchSchemaTask$_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPatchSchemaTask$_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPatchSchemaTask$_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPatchSchemaTask$_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            return new CallSiteArray(TinkerPatchSchemaTask$_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPatchSchemaTask$_closure1.$callSiteArray != null ? TinkerPatchSchemaTask$_closure1.$createCallSiteArray() : (CallSiteArray)TinkerPatchSchemaTask$_closure1.$callSiteArray.get();
            TinkerPatchSchemaTask$_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerPatchSchemaTask$_closure1
    public final class TinkerPatchSchemaTask$_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPatchSchemaTask$_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_closure1.$getCallSiteArray();
            return Boolean.valueOf(false);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPatchSchemaTask$_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPatchSchemaTask$_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPatchSchemaTask$_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPatchSchemaTask$_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            return new CallSiteArray(TinkerPatchSchemaTask$_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPatchSchemaTask$_closure1.$callSiteArray != null ? TinkerPatchSchemaTask$_closure1.$createCallSiteArray() : (CallSiteArray)TinkerPatchSchemaTask$_closure1.$callSiteArray.get();
            TinkerPatchSchemaTask$_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerPatchSchemaTask$_tinkerPatch_closure3
    public final class TinkerPatchSchemaTask$_tinkerPatch_closure3 implements GeneratedClosure {
        private synthetic Reference newApks;
        private synthetic Reference newApkNames;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPatchSchemaTask$_tinkerPatch_closure3(Object _outerInstance, Object _thisObject, Reference newApks, Reference newApkNames) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            newApks.newApks = this;
            newApkNames.newApkNames = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure3.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(it), ".apk"))) {
                siteArr0[2].call(this.newApks.get(), it);
                return siteArr0[3].call(this.newApkNames.get(), siteArr0[4].call(it));
            }
            else {
                return null;
            }
        }

        @Generated
        public Object getNewApks() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure3.$getCallSiteArray();
            return this.newApks.get();
        }

        @Generated
        public Object getNewApkNames() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure3.$getCallSiteArray();
            return this.newApkNames.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure3.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPatchSchemaTask$_tinkerPatch_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPatchSchemaTask$_tinkerPatch_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPatchSchemaTask$_tinkerPatch_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPatchSchemaTask$_tinkerPatch_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "leftShift";
            stringArr0[3] = "leftShift";
            stringArr0[4] = "getName";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPatchSchemaTask$_tinkerPatch_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPatchSchemaTask$_tinkerPatch_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPatchSchemaTask$_tinkerPatch_closure3.$callSiteArray != null ? TinkerPatchSchemaTask$_tinkerPatch_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPatchSchemaTask$_tinkerPatch_closure3.$callSiteArray.get();
            TinkerPatchSchemaTask$_tinkerPatch_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerPatchSchemaTask$_tinkerPatch_closure3
    public final class TinkerPatchSchemaTask$_tinkerPatch_closure3 implements GeneratedClosure {
        private synthetic Reference newApks;
        private synthetic Reference newApkNames;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPatchSchemaTask$_tinkerPatch_closure3(Object _outerInstance, Object _thisObject, Reference newApks, Reference newApkNames) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            newApks.newApks = this;
            newApkNames.newApkNames = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure3.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(it), ".apk"))) {
                siteArr0[2].call(this.newApks.get(), it);
                return siteArr0[3].call(this.newApkNames.get(), siteArr0[4].call(it));
            }
            else {
                return null;
            }
        }

        @Generated
        public Object getNewApks() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure3.$getCallSiteArray();
            return this.newApks.get();
        }

        @Generated
        public Object getNewApkNames() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure3.$getCallSiteArray();
            return this.newApkNames.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPatchSchemaTask$_tinkerPatch_closure3.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPatchSchemaTask$_tinkerPatch_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPatchSchemaTask$_tinkerPatch_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPatchSchemaTask$_tinkerPatch_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPatchSchemaTask$_tinkerPatch_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "leftShift";
            stringArr0[3] = "leftShift";
            stringArr0[4] = "getName";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPatchSchemaTask$_tinkerPatch_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPatchSchemaTask$_tinkerPatch_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPatchSchemaTask$_tinkerPatch_closure3.$callSiteArray != null ? TinkerPatchSchemaTask$_tinkerPatch_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPatchSchemaTask$_tinkerPatch_closure3.$callSiteArray.get();
            TinkerPatchSchemaTask$_tinkerPatch_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
