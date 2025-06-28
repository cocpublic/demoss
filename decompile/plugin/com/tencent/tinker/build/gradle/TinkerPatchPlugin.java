/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/gradle;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import org.gradle.api.Project;
import org.gradle.api.file.FileCollection;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.util.Map;
import java.io.File;
import sun.misc.Unsafe;
import com.android.build.gradle.api.ApkVariant;

// class: com/tencent/tinker/build/gradle/TinkerPatchPlugin
public class TinkerPatchPlugin implements Plugin<Project>, GroovyObject {
    final public static String ISSUE_URL;
    private Project mProject;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public TinkerPatchPlugin() {
        CallSite[] siteArr0 = TinkerPatchPlugin.$getCallSiteArray();
        super();
        Object object = null;
        (Project)ScriptBytecodeAdapter.castToType(object, Project.class).mProject = this;
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public void apply(Project project) {
        v_1 = alloc(Reference);
        new project.<init>(v_1);
        Reference reference = v_1;
        CallSite[] siteArr0 = TinkerPatchPlugin.$getCallSiteArray();
        project = (Project)reference.get();
        project.mProject = this;
        try {
            try {
                siteArr0[0].call(this.mProject, ScriptBytecodeAdapter.createMap(new Object[]{"plugin", "osdetector"}));
            }
            catch (Throwable e) {
                siteArr0[1].call(this.mProject, ScriptBytecodeAdapter.createMap(new Object[]{"plugin", "com.google.osdetector"}));
            }
        }
        finally {
            Throwable throwable = v_23;
            throw throwable;
        }
        siteArr0[2].call(siteArr0[3].callGetProperty(this.mProject), "tinkerPatch", TinkerPatchExtension.class);
        siteArr0[4].call(siteArr0[5].callGetProperty(siteArr0[6].callGetProperty(this.mProject)), "buildConfig", TinkerBuildConfigExtension.class, this.mProject);
        siteArr0[7].call(siteArr0[8].callGetProperty(siteArr0[9].callGetProperty(this.mProject)), "dex", TinkerDexExtension.class, this.mProject);
        siteArr0[10].call(siteArr0[11].callGetProperty(siteArr0[12].callGetProperty(this.mProject)), "lib", TinkerLibExtension.class);
        siteArr0[13].call(siteArr0[14].callGetProperty(siteArr0[15].callGetProperty(this.mProject)), "res", TinkerResourceExtension.class);
        siteArr0[16].call(siteArr0[17].callGetProperty(siteArr0[18].callGetProperty(this.mProject)), "arkHot", TinkerArkHotExtension.class);
        siteArr0[19].call(siteArr0[20].callGetProperty(siteArr0[21].callGetProperty(this.mProject)), "packageConfig", TinkerPackageConfigExtension.class, this.mProject);
        siteArr0[22].call(siteArr0[23].callGetProperty(siteArr0[24].callGetProperty(this.mProject)), "sevenZip", TinkerSevenZipExtension.class, this.mProject);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[25].call(siteArr0[26].callGetProperty(this.mProject), "com.android.application")) ? 0 : 1 != 0) {
            throw (Throwable)siteArr0[27].callConstructor(GradleException.class, "generateTinkerApk: Android Application plugin required");
        }
        else {
            v_187 = alloc(Reference);
            new siteArr0[28].callGetProperty(siteArr0[29].callGetProperty(this.mProject)).<init>(v_187);
            Reference android = v_187;
            try {
                try {
                    int i0 = false;
                    ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i0), null, siteArr0[30].callGetProperty(android.get()), (String)"preDexLibraries");
                    int i1 = true;
                    ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i1), null, siteArr0[31].callGetProperty(android.get()), (String)"jumboMode");
                    if (TinkerPatchPlugin.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                        goto 594;
                        this.disableArchiveDex();
                        v_220 = null;
                    }
                    else {
                        siteArr0[32].callCurrent(this);
                    }
                    int i2 = false;
                    ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i2), null, siteArr0[33].callGetProperty(android.get()), (String)"keepRuntimeAnnotatedClasses");
                }
                catch (Throwable e) {
                }
            }
            finally {
                Throwable throwableVar1 = v_217;
                throw throwableVar1;
            }
            siteArr0[34].call(this.mProject, new TinkerPatchPlugin$_apply_closure1(this, this, reference, android));
        }
    }

    public void setPatchOutputFolder(Object configuration, Object output, Object variant, Object tinkerPatchBuildTask) {
        CallSite[] siteArr0 = TinkerPatchPlugin.$getCallSiteArray();
        File parentFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[35].callGetProperty(output), File.class);
        String outputFolder = (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[36].callGetProperty(configuration)}, new String[]{"", ""}));
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[37].call(Utils.class, outputFolder)) ? 0 : 1 != 0) {
            GStringImpl impl = new GStringImpl(new Object[]{outputFolder, siteArr0[38].callGetProperty(TypedValue.class), siteArr0[39].callGetProperty(variant)}, new String[]{"", "/", "/", ""});
            outputFolder = (String)ShortTypeHandling.castToString(impl);
        }
        else {
            GStringImpl implVar1 = new GStringImpl(new Object[]{siteArr0[40].call(siteArr0[41].call(siteArr0[42].call(parentFile))), siteArr0[43].callGetProperty(TypedValue.class), siteArr0[44].callGetProperty(variant)}, new String[]{"", "/", "/", ""});
            outputFolder = (String)ShortTypeHandling.castToString(implVar1);
        }
        ScriptBytecodeAdapter.setProperty(outputFolder, null, tinkerPatchBuildTask, (String)"outputFolder");
    }

    public void disableArchiveDex() {
        CallSite[] siteArr0 = TinkerPatchPlugin.$getCallSiteArray();
        try {
            try {
                Class booleanOptClazz = Class.forName("com.android.build.gradle.options.BooleanOption");
                Object enableDexArchiveField = siteArr0[45].call(booleanOptClazz, "ENABLE_DEX_ARCHIVE");
                siteArr0[46].call(enableDexArchiveField, Boolean.valueOf(true));
                Object enableDexArchiveEnumObj = siteArr0[47].call(enableDexArchiveField, null);
                Object defValField = siteArr0[48].call(siteArr0[49].call(enableDexArchiveEnumObj), "defaultValue");
                siteArr0[50].call(defValField, Boolean.valueOf(true));
                siteArr0[51].call(defValField, enableDexArchiveEnumObj, Boolean.valueOf(false));
            }
            catch (Throwable thr) {
                if ((thr instanceof ClassNotFoundException) ? 0 : 1 != 0) {
                    siteArr0[52].call(siteArr0[53].callGetProperty(this.mProject), new GStringImpl(new Object[]{siteArr0[54].call(thr)}, new String[]{"reflectDexArchiveFlag error: ", "."}));
                }
            }
            return;
        }
        finally {
            Throwable throwable = v_52;
            throw throwable;
        }
    }

    public void setPatchNewApkPath(Object configuration, Object output, Object variant, Object tinkerPatchBuildTask) {
        CallSite[] siteArr0 = TinkerPatchPlugin.$getCallSiteArray();
        Object newApkPath = siteArr0[55].callGetProperty(configuration);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[56].call(Utils.class, newApkPath)) ? 0 : 1 != 0 && DefaultTypeTransformation.booleanUnbox(siteArr0[57].call(FileOperation.class, newApkPath))) {
            ScriptBytecodeAdapter.setProperty(newApkPath, null, tinkerPatchBuildTask, (String)"buildApkPath");
        }
        else {
            Object objectVar1 = siteArr0[58].callGetProperty(output);
            ScriptBytecodeAdapter.setProperty(objectVar1, null, tinkerPatchBuildTask, (String)"buildApkPath");
            siteArr0[59].call(tinkerPatchBuildTask, siteArr0[60].call(Compatibilities.class, this.mProject, variant));
        }
    }

    public void replaceKotlinFinalField(String className, String filedName, Object instance, Object fieldValue) {
        CallSite[] siteArr0 = TinkerPatchPlugin.$getCallSiteArray();
        Field field = (Field)ScriptBytecodeAdapter.castToType(siteArr0[61].call(Class.forName(className), filedName), Field.class);
        Field unsafeField = (Field)ScriptBytecodeAdapter.castToType(siteArr0[62].call(Unsafe.class, "theUnsafe"), Field.class);
        siteArr0[63].call(unsafeField, Boolean.valueOf(true));
        Unsafe unsafe = (Unsafe)ScriptBytecodeAdapter.castToType(siteArr0[64].call(unsafeField, null), Unsafe.class);
        long fieldOffset = DefaultTypeTransformation.longUnbox(siteArr0[65].call(unsafe, field));
        siteArr0[66].call(unsafe, instance, Long.valueOf(fieldOffset), fieldValue);
    }

    public File getManifestMultiDexKeepProguard(Object applicationVariant) {
        Object file;
        CallSite[] siteArr0 = TinkerPatchPlugin.$getCallSiteArray();
        Object multiDexKeepProguard = null;
        try {
            try {
                file = siteArr0[67].callSafe(siteArr0[68].call(siteArr0[69].call(siteArr0[70].callGetProperty(siteArr0[71].callGetProperty(applicationVariant)), siteArr0[72].call(siteArr0[73].call(Class.forName("com.android.build.gradle.internal.scope.InternalArtifactType$LEGACY_MULTIDEX_AAPT_DERIVED_PROGUARD_RULES"), "INSTANCE"), null))));
                if (BytecodeInterface8.isOrigZ() && TinkerPatchPlugin.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                    goto 174;
                    if (ScriptBytecodeAdapter.compareNotEqual(file, null) && ScriptBytecodeAdapter.compareNotEqual(siteArr0[75].call(file), "__EMPTY_DIR__") ? 0 : 1 != 0) {
                        file = (File)ScriptBytecodeAdapter.castToType(file, File.class);
                    }
                }
                else {
                    if (ScriptBytecodeAdapter.compareNotEqual(file, null) && ScriptBytecodeAdapter.compareNotEqual(siteArr0[74].call(file), "__EMPTY_DIR__") ? 0 : 1 != 0) {
                        file = (File)ScriptBytecodeAdapter.castToType(file, File.class);
                    }
                }
            }
            catch (Throwable ignore) {
            }
        }
        finally {
            Throwable throwable = v_39;
            throw throwable;
        }
        if (BytecodeInterface8.isOrigZ() && TinkerPatchPlugin.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 448;
            if (ScriptBytecodeAdapter.compareEqual(multiDexKeepProguard, null)) {
                try {
                    try {
                        file = siteArr0[85].callSafe(siteArr0[86].call(siteArr0[87].call(siteArr0[88].call(siteArr0[89].call(siteArr0[90].call(applicationVariant))), siteArr0[91].call(siteArr0[92].call(Class.forName("com.android.build.gradle.internal.scope.InternalArtifactType$LEGACY_MULTIDEX_AAPT_DERIVED_PROGUARD_RULES"), "INSTANCE"), null))));
                        if (ScriptBytecodeAdapter.compareNotEqual(file, null) && ScriptBytecodeAdapter.compareNotEqual(siteArr0[93].call(file), "__EMPTY_DIR__") ? 0 : 1 != 0) {
                            file = (File)ScriptBytecodeAdapter.castToType(file, File.class);
                        }
                    }
                    catch (Throwable ignore) {
                    }
                }
                finally {
                    Throwable throwableVar2 = v_107;
                    throw throwableVar2;
                }
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareEqual(multiDexKeepProguard, null)) {
                try {
                    try {
                        file = siteArr0[76].callSafe(siteArr0[77].call(siteArr0[78].call(siteArr0[79].call(siteArr0[80].call(siteArr0[81].call(applicationVariant))), siteArr0[82].call(siteArr0[83].call(Class.forName("com.android.build.gradle.internal.scope.InternalArtifactType$LEGACY_MULTIDEX_AAPT_DERIVED_PROGUARD_RULES"), "INSTANCE"), null))));
                        if (ScriptBytecodeAdapter.compareNotEqual(file, null) && ScriptBytecodeAdapter.compareNotEqual(siteArr0[84].call(file), "__EMPTY_DIR__") ? 0 : 1 != 0) {
                            file = (File)ScriptBytecodeAdapter.castToType(file, File.class);
                        }
                    }
                    catch (Throwable ignore) {
                    }
                }
                finally {
                    Throwable throwableVar1 = v_509;
                    throw throwableVar1;
                }
            }
        }
        if (BytecodeInterface8.isOrigZ() && TinkerPatchPlugin.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 826;
            if (ScriptBytecodeAdapter.compareEqual(fileVar1, null)) {
                try {
                    try {
                        file = (File)ScriptBytecodeAdapter.castToType(siteArr0[103].callSafe(siteArr0[104].call(siteArr0[105].call(siteArr0[106].call(siteArr0[107].call(siteArr0[108].call(applicationVariant))), siteArr0[109].call(siteArr0[110].call(Class.forName("com.android.build.gradle.internal.scope.InternalArtifactType"), "LEGACY_MULTIDEX_AAPT_DERIVED_PROGUARD_RULES"), null)))), File.class);
                        if (ScriptBytecodeAdapter.compareNotEqual(file, null) && ScriptBytecodeAdapter.compareNotEqual(siteArr0[111].call(file), "__EMPTY_DIR__") ? 0 : 1 != 0) {
                            File fileVar2 = file;
                            file = fileVar2;
                        }
                    }
                    catch (Throwable ignore) {
                    }
                }
                finally {
                    Throwable throwableVar4 = v_174;
                    throw throwableVar4;
                }
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareEqual(multiDexKeepProguard, null)) {
                try {
                    try {
                        file = (File)ScriptBytecodeAdapter.castToType(siteArr0[94].callSafe(siteArr0[95].call(siteArr0[96].call(siteArr0[97].call(siteArr0[98].call(siteArr0[99].call(applicationVariant))), siteArr0[100].call(siteArr0[101].call(Class.forName("com.android.build.gradle.internal.scope.InternalArtifactType"), "LEGACY_MULTIDEX_AAPT_DERIVED_PROGUARD_RULES"), null)))), File.class);
                        if (ScriptBytecodeAdapter.compareNotEqual(file, null) && ScriptBytecodeAdapter.compareNotEqual(siteArr0[102].call(file), "__EMPTY_DIR__") ? 0 : 1 != 0) {
                            File fileVar1 = file;
                        }
                    }
                    catch (Throwable ignore) {
                    }
                }
                finally {
                    Throwable throwableVar3 = v_451;
                    throw throwableVar3;
                }
            }
        }
        Object buildableArtifact;
        if (BytecodeInterface8.isOrigZ() && TinkerPatchPlugin.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 1169;
            if (ScriptBytecodeAdapter.compareEqual(fileVar1, null)) {
                try {
                    try {
                        buildableArtifact = siteArr0[120].call(siteArr0[121].call(siteArr0[122].call(siteArr0[123].call(applicationVariant))), siteArr0[124].call(siteArr0[125].call(Class.forName("com.android.build.gradle.internal.scope.InternalArtifactType"), "LEGACY_MULTIDEX_AAPT_DERIVED_PROGUARD_RULES"), null));
                        Object objectVar5 = siteArr0[126].call(Iterators.class, siteArr0[127].call(buildableArtifact));
                        File fileVar4 = (File)ScriptBytecodeAdapter.castToType(objectVar5, File.class);
                    }
                    catch (Throwable ignore) {
                    }
                }
                finally {
                    Throwable throwableVar6 = v_239;
                    throw throwableVar6;
                }
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareEqual(fileVar1, null)) {
                try {
                    try {
                        buildableArtifact = siteArr0[112].call(siteArr0[113].call(siteArr0[114].call(siteArr0[115].call(applicationVariant))), siteArr0[116].call(siteArr0[117].call(Class.forName("com.android.build.gradle.internal.scope.InternalArtifactType"), "LEGACY_MULTIDEX_AAPT_DERIVED_PROGUARD_RULES"), null));
                        Object objectVar4 = siteArr0[118].call(Iterators.class, siteArr0[119].call(buildableArtifact));
                        File fileVar3 = (File)ScriptBytecodeAdapter.castToType(objectVar4, File.class);
                    }
                    catch (Throwable ignore) {
                    }
                }
                finally {
                    Throwable throwableVar5 = v_402;
                    throw throwableVar5;
                }
            }
        }
        if (BytecodeInterface8.isOrigZ() && TinkerPatchPlugin.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 1407;
            if (ScriptBytecodeAdapter.compareEqual(fileVar1, null)) {
                try {
                    try {
                        Object objectVar7 = siteArr0[131].call(siteArr0[132].call(siteArr0[133].call(applicationVariant)));
                        File fileVar6 = (File)ScriptBytecodeAdapter.castToType(objectVar7, File.class);
                    }
                    catch (Throwable ignore) {
                    }
                }
                finally {
                    Throwable throwableVar8 = v_265;
                    throw throwableVar8;
                }
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareEqual(fileVar1, null)) {
                try {
                    try {
                        Object objectVar6 = siteArr0[128].call(siteArr0[129].call(siteArr0[130].call(applicationVariant)));
                        File fileVar5 = (File)ScriptBytecodeAdapter.castToType(objectVar6, File.class);
                    }
                    catch (Throwable ignore) {
                    }
                }
                finally {
                    Throwable throwableVar7 = v_352;
                    throw throwableVar7;
                }
            }
        }
        if (BytecodeInterface8.isOrigZ() && TinkerPatchPlugin.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 1575;
            if (ScriptBytecodeAdapter.compareEqual(fileVar1, null)) {
                try {
                    try {
                        Object objectVar9 = siteArr0[137].call(siteArr0[138].call(siteArr0[139].call(applicationVariant)));
                        File fileVar8 = (File)ScriptBytecodeAdapter.castToType(objectVar9, File.class);
                    }
                    catch (Throwable ignore) {
                    }
                }
                finally {
                    Throwable throwableVar10 = v_291;
                    throw throwableVar10;
                }
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareEqual(fileVar1, null)) {
                try {
                    try {
                        Object objectVar8 = siteArr0[134].call(siteArr0[135].call(siteArr0[136].call(applicationVariant)));
                        File fileVar7 = (File)ScriptBytecodeAdapter.castToType(objectVar8, File.class);
                    }
                    catch (Throwable ignore) {
                    }
                }
                finally {
                    Throwable throwableVar9 = v_329;
                    throw throwableVar9;
                }
            }
            goto 1647;
        }
        if (ScriptBytecodeAdapter.compareEqual(fileVar1, null)) {
            siteArr0[140].call(siteArr0[141].callGetProperty(this.mProject), "can't get multiDexKeepProguard file");
        }
        return fileVar1;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerPatchPlugin.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerPatchPlugin.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerPatchPlugin.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerPatchPlugin.$staticClassInfo.getMetaClass();
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

    @Generated
    public /* synthetic */ void apply(Object object) {
        this.apply((Project)object);
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "apply";
        stringArr0[1] = "apply";
        stringArr0[2] = "create";
        stringArr0[3] = "extensions";
        stringArr0[4] = "create";
        stringArr0[5] = "extensions";
        stringArr0[6] = "tinkerPatch";
        stringArr0[7] = "create";
        stringArr0[8] = "extensions";
        stringArr0[9] = "tinkerPatch";
        stringArr0[10] = "create";
        stringArr0[11] = "extensions";
        stringArr0[12] = "tinkerPatch";
        stringArr0[13] = "create";
        stringArr0[14] = "extensions";
        stringArr0[15] = "tinkerPatch";
        stringArr0[16] = "create";
        stringArr0[17] = "extensions";
        stringArr0[18] = "tinkerPatch";
        stringArr0[19] = "create";
        stringArr0[20] = "extensions";
        stringArr0[21] = "tinkerPatch";
        stringArr0[22] = "create";
        stringArr0[23] = "extensions";
        stringArr0[24] = "tinkerPatch";
        stringArr0[25] = "hasPlugin";
        stringArr0[26] = "plugins";
        stringArr0[27] = "<$constructor$>";
        stringArr0[28] = "android";
        stringArr0[29] = "extensions";
        stringArr0[30] = "dexOptions";
        stringArr0[31] = "dexOptions";
        stringArr0[32] = "disableArchiveDex";
        stringArr0[33] = "dexOptions";
        stringArr0[34] = "afterEvaluate";
        stringArr0[35] = "outputFile";
        stringArr0[36] = "outputFolder";
        stringArr0[37] = "isNullOrNil";
        stringArr0[38] = "PATH_DEFAULT_OUTPUT";
        stringArr0[39] = "dirName";
        stringArr0[40] = "getAbsolutePath";
        stringArr0[41] = "getParentFile";
        stringArr0[42] = "getParentFile";
        stringArr0[43] = "PATH_DEFAULT_OUTPUT";
        stringArr0[44] = "dirName";
        stringArr0[45] = "getDeclaredField";
        stringArr0[46] = "setAccessible";
        stringArr0[47] = "get";
        stringArr0[48] = "getDeclaredField";
        stringArr0[49] = "getClass";
        stringArr0[50] = "setAccessible";
        stringArr0[51] = "set";
        stringArr0[52] = "error";
        stringArr0[53] = "logger";
        stringArr0[54] = "getMessage";
        stringArr0[55] = "newApk";
        stringArr0[56] = "isNullOrNil";
        stringArr0[57] = "isLegalFileOrDirectory";
        stringArr0[58] = "outputFile";
        stringArr0[59] = "dependsOn";
        stringArr0[60] = "getAssembleTask";
        stringArr0[61] = "getDeclaredField";
        stringArr0[62] = "getDeclaredField";
        stringArr0[63] = "setAccessible";
        stringArr0[64] = "get";
        stringArr0[65] = "objectFieldOffset";
        stringArr0[66] = "putObject";
        stringArr0[67] = "getAsFile";
        stringArr0[68] = "getOrNull";
        stringArr0[69] = "get";
        stringArr0[70] = "artifacts";
        stringArr0[71] = "variantData";
        stringArr0[72] = "get";
        stringArr0[73] = "getDeclaredField";
        stringArr0[74] = "getName";
        stringArr0[75] = "getName";
        stringArr0[76] = "getAsFile";
        stringArr0[77] = "getOrNull";
        stringArr0[78] = "getFinalProduct";
        stringArr0[79] = "getArtifacts";
        stringArr0[80] = "getScope";
        stringArr0[81] = "getVariantData";
        stringArr0[82] = "get";
        stringArr0[83] = "getDeclaredField";
        stringArr0[84] = "getName";
        stringArr0[85] = "getAsFile";
        stringArr0[86] = "getOrNull";
        stringArr0[87] = "getFinalProduct";
        stringArr0[88] = "getArtifacts";
        stringArr0[89] = "getScope";
        stringArr0[90] = "getVariantData";
        stringArr0[91] = "get";
        stringArr0[92] = "getDeclaredField";
        stringArr0[93] = "getName";
        stringArr0[94] = "getAsFile";
        stringArr0[95] = "getOrNull";
        stringArr0[96] = "getFinalProduct";
        stringArr0[97] = "getArtifacts";
        stringArr0[98] = "getScope";
        stringArr0[99] = "getVariantData";
        stringArr0[100] = "get";
        stringArr0[101] = "getDeclaredField";
        stringArr0[102] = "getName";
        stringArr0[103] = "getAsFile";
        stringArr0[104] = "getOrNull";
        stringArr0[105] = "getFinalProduct";
        stringArr0[106] = "getArtifacts";
        stringArr0[107] = "getScope";
        stringArr0[108] = "getVariantData";
        stringArr0[109] = "get";
        stringArr0[110] = "getDeclaredField";
        stringArr0[111] = "getName";
        stringArr0[112] = "getFinalArtifactFiles";
        stringArr0[113] = "getArtifacts";
        stringArr0[114] = "getScope";
        stringArr0[115] = "getVariantData";
        stringArr0[116] = "get";
        stringArr0[117] = "getDeclaredField";
        stringArr0[118] = "getOnlyElement";
        stringArr0[119] = "iterator";
        stringArr0[120] = "getFinalArtifactFiles";
        stringArr0[121] = "getArtifacts";
        stringArr0[122] = "getScope";
        stringArr0[123] = "getVariantData";
        stringArr0[124] = "get";
        stringArr0[125] = "getDeclaredField";
        stringArr0[126] = "getOnlyElement";
        stringArr0[127] = "iterator";
        stringArr0[128] = "getManifestKeepListProguardFile";
        stringArr0[129] = "getScope";
        stringArr0[130] = "getVariantData";
        stringArr0[131] = "getManifestKeepListProguardFile";
        stringArr0[132] = "getScope";
        stringArr0[133] = "getVariantData";
        stringArr0[134] = "getManifestKeepListFile";
        stringArr0[135] = "getScope";
        stringArr0[136] = "getVariantData";
        stringArr0[137] = "getManifestKeepListFile";
        stringArr0[138] = "getScope";
        stringArr0[139] = "getVariantData";
        stringArr0[140] = "error";
        stringArr0[141] = "logger";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerPatchPlugin.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerPatchPlugin.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerPatchPlugin.$callSiteArray != null ? TinkerPatchPlugin.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin.$callSiteArray.get();
        TinkerPatchPlugin.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1
    public final class TinkerPatchPlugin$_apply_closure1 implements GeneratedClosure {
        private synthetic Reference project;
        private synthetic Reference android;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPatchPlugin$_apply_closure1(Object _outerInstance, Object _thisObject, Reference project, Reference android) {
            CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            project.project = this;
            android.android = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1.$getCallSiteArray();
            v_10 = alloc(Reference);
            new siteArr0[0].callGetProperty(siteArr0[1].callGroovyObjectGetProperty(this)).<init>(v_10);
            Reference configuration = v_10;
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[2].callGetProperty(configuration.get())) ? 0 : 1 != 0) {
                siteArr0[3].call(siteArr0[4].callGetProperty(siteArr0[5].callGroovyObjectGetProperty(this)), "tinker tasks are disabled.");
                return null;
            }
            else {
                siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), "----------------------tinker build warning ------------------------------------");
                siteArr0[9].call(siteArr0[10].callGetProperty(siteArr0[11].callGroovyObjectGetProperty(this)), "tinker auto operation: ");
                siteArr0[12].call(siteArr0[13].callGetProperty(siteArr0[14].callGroovyObjectGetProperty(this)), "excluding annotation processor and source template from app packaging. Enable dx jumboMode to reduce package size.");
                siteArr0[15].call(siteArr0[16].callGetProperty(siteArr0[17].callGroovyObjectGetProperty(this)), "enable dx jumboMode to reduce package size.");
                siteArr0[18].call(siteArr0[19].callGetProperty(siteArr0[20].callGroovyObjectGetProperty(this)), "disable preDexLibraries to prevent ClassDefNotFoundException when your app is booting.");
                siteArr0[21].call(siteArr0[22].callGetProperty(siteArr0[23].callGroovyObjectGetProperty(this)), "disable archive dex mode so far for keeping dex apply.");
                siteArr0[24].call(siteArr0[25].callGetProperty(siteArr0[26].callGroovyObjectGetProperty(this)), "");
                siteArr0[27].call(siteArr0[28].callGetProperty(siteArr0[29].callGroovyObjectGetProperty(this)), "tinker will change your build configs:");
                siteArr0[30].call(siteArr0[31].callGetProperty(siteArr0[32].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[33].callGetProperty(siteArr0[34].callGetProperty(configuration.get())), siteArr0[35].callGetProperty(this.project.get())}, new String[]{"we will add TINKER_ID=", " in your build output manifest file ", "/intermediates/manifests/full/*"}));
                siteArr0[36].call(siteArr0[37].callGetProperty(siteArr0[38].callGroovyObjectGetProperty(this)), "");
                siteArr0[39].call(siteArr0[40].callGetProperty(siteArr0[41].callGroovyObjectGetProperty(this)), "if minifyEnabled is true");
                String tempMappingPath = (String)ShortTypeHandling.castToString(siteArr0[42].callGetProperty(siteArr0[43].callGetProperty(configuration.get())));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[44].call(FileOperation.class, tempMappingPath))) {
                    siteArr0[45].call(siteArr0[46].callGetProperty(siteArr0[47].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[48].call(siteArr0[49].callGroovyObjectGetProperty(this)), tempMappingPath}, new String[]{"we will build ", " apk with apply mapping file ", ""}));
                }
                siteArr0[50].call(siteArr0[51].callGetProperty(siteArr0[52].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[53].call(TinkerBuildPath.class, this.project.get())}, new String[]{"you will find the gen proguard rule file at ", ""}));
                siteArr0[54].call(siteArr0[55].callGetProperty(siteArr0[56].callGroovyObjectGetProperty(this)), "and we will help you to put it in the proguardFiles.");
                siteArr0[57].call(siteArr0[58].callGetProperty(siteArr0[59].callGroovyObjectGetProperty(this)), "");
                siteArr0[60].call(siteArr0[61].callGetProperty(siteArr0[62].callGroovyObjectGetProperty(this)), "if multiDexEnabled is true");
                siteArr0[63].call(siteArr0[64].callGetProperty(siteArr0[65].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[66].call(TinkerBuildPath.class, this.project.get())}, new String[]{"you will find the gen multiDexKeepProguard file at ", ""}));
                siteArr0[67].call(siteArr0[68].callGetProperty(siteArr0[69].callGroovyObjectGetProperty(this)), "and we will help you to put it in the MultiDexKeepProguardFile.");
                siteArr0[70].call(siteArr0[71].callGetProperty(siteArr0[72].callGroovyObjectGetProperty(this)), "");
                siteArr0[73].call(siteArr0[74].callGetProperty(siteArr0[75].callGroovyObjectGetProperty(this)), "if applyResourceMapping file is exist");
                String tempResourceMappingPath = (String)ShortTypeHandling.castToString(siteArr0[76].callGetProperty(siteArr0[77].callGetProperty(configuration.get())));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[78].call(FileOperation.class, tempResourceMappingPath))) {
                    siteArr0[79].call(siteArr0[80].callGetProperty(siteArr0[81].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[82].call(siteArr0[83].callGroovyObjectGetProperty(this)), tempResourceMappingPath}, new String[]{"we will build ", " apk with resource R.txt ", " file"}));
                }
                else {
                    siteArr0[84].call(siteArr0[85].callGetProperty(siteArr0[86].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[87].call(siteArr0[88].callGroovyObjectGetProperty(this))}, new String[]{"we will build ", " apk with resource R.txt file"}));
                }
                siteArr0[89].call(siteArr0[90].callGetProperty(siteArr0[91].callGroovyObjectGetProperty(this)), "if resources.arsc has changed, you should use applyResource mode to build the new apk!");
                siteArr0[92].call(siteArr0[93].callGetProperty(siteArr0[94].callGroovyObjectGetProperty(this)), "-----------------------------------------------------------------");
                return siteArr0[95].call(siteArr0[96].callGetProperty(this.android.get()), new TinkerPatchPlugin$_apply_closure1$_closure2(this, this.getThisObject(), this.project, configuration));
            }
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public Object getAndroid() {
            CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1.$getCallSiteArray();
            return this.android.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPatchPlugin$_apply_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPatchPlugin$_apply_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPatchPlugin$_apply_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPatchPlugin$_apply_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "tinkerPatch";
            stringArr0[1] = "mProject";
            stringArr0[2] = "tinkerEnable";
            stringArr0[3] = "error";
            stringArr0[4] = "logger";
            stringArr0[5] = "mProject";
            stringArr0[6] = "error";
            stringArr0[7] = "logger";
            stringArr0[8] = "mProject";
            stringArr0[9] = "error";
            stringArr0[10] = "logger";
            stringArr0[11] = "mProject";
            stringArr0[12] = "error";
            stringArr0[13] = "logger";
            stringArr0[14] = "mProject";
            stringArr0[15] = "error";
            stringArr0[16] = "logger";
            stringArr0[17] = "mProject";
            stringArr0[18] = "error";
            stringArr0[19] = "logger";
            stringArr0[20] = "mProject";
            stringArr0[21] = "error";
            stringArr0[22] = "logger";
            stringArr0[23] = "mProject";
            stringArr0[24] = "error";
            stringArr0[25] = "logger";
            stringArr0[26] = "mProject";
            stringArr0[27] = "error";
            stringArr0[28] = "logger";
            stringArr0[29] = "mProject";
            stringArr0[30] = "error";
            stringArr0[31] = "logger";
            stringArr0[32] = "mProject";
            stringArr0[33] = "tinkerId";
            stringArr0[34] = "buildConfig";
            stringArr0[35] = "buildDir";
            stringArr0[36] = "error";
            stringArr0[37] = "logger";
            stringArr0[38] = "mProject";
            stringArr0[39] = "error";
            stringArr0[40] = "logger";
            stringArr0[41] = "mProject";
            stringArr0[42] = "applyMapping";
            stringArr0[43] = "buildConfig";
            stringArr0[44] = "isLegalFile";
            stringArr0[45] = "error";
            stringArr0[46] = "logger";
            stringArr0[47] = "mProject";
            stringArr0[48] = "getName";
            stringArr0[49] = "mProject";
            stringArr0[50] = "error";
            stringArr0[51] = "logger";
            stringArr0[52] = "mProject";
            stringArr0[53] = "getProguardConfigPath";
            stringArr0[54] = "error";
            stringArr0[55] = "logger";
            stringArr0[56] = "mProject";
            stringArr0[57] = "error";
            stringArr0[58] = "logger";
            stringArr0[59] = "mProject";
            stringArr0[60] = "error";
            stringArr0[61] = "logger";
            stringArr0[62] = "mProject";
            stringArr0[63] = "error";
            stringArr0[64] = "logger";
            stringArr0[65] = "mProject";
            stringArr0[66] = "getMultidexConfigPath";
            stringArr0[67] = "error";
            stringArr0[68] = "logger";
            stringArr0[69] = "mProject";
            stringArr0[70] = "error";
            stringArr0[71] = "logger";
            stringArr0[72] = "mProject";
            stringArr0[73] = "error";
            stringArr0[74] = "logger";
            stringArr0[75] = "mProject";
            stringArr0[76] = "applyResourceMapping";
            stringArr0[77] = "buildConfig";
            stringArr0[78] = "isLegalFile";
            stringArr0[79] = "error";
            stringArr0[80] = "logger";
            stringArr0[81] = "mProject";
            stringArr0[82] = "getName";
            stringArr0[83] = "mProject";
            stringArr0[84] = "error";
            stringArr0[85] = "logger";
            stringArr0[86] = "mProject";
            stringArr0[87] = "getName";
            stringArr0[88] = "mProject";
            stringArr0[89] = "error";
            stringArr0[90] = "logger";
            stringArr0[91] = "mProject";
            stringArr0[92] = "error";
            stringArr0[93] = "logger";
            stringArr0[94] = "mProject";
            stringArr0[95] = "all";
            stringArr0[96] = "applicationVariants";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPatchPlugin$_apply_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPatchPlugin$_apply_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPatchPlugin$_apply_closure1.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1.$callSiteArray.get();
            TinkerPatchPlugin$_apply_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2
        public final class TinkerPatchPlugin$_apply_closure1$_closure2 implements GeneratedClosure {
            private synthetic Reference project;
            private synthetic Reference configuration;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerPatchPlugin$_apply_closure1$_closure2(Object _outerInstance, Object _thisObject, Reference project, Reference configuration) {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                project.project = this;
                configuration.configuration = this;
            }

            public Object doCall(ApkVariant variant) {
                v_1 = alloc(Reference);
                new variant.<init>(v_1);
                Reference reference = v_1;
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                Object variantName = siteArr0[0].callGetProperty((ApkVariant)reference.get());
                Object capitalizedVariantName = siteArr0[1].call(variantName);
                Object instantRunTask = siteArr0[2].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                if (ScriptBytecodeAdapter.compareNotEqual(instantRunTask, null)) {
                    throw (Throwable)siteArr0[3].callConstructor(GradleException.class, siteArr0[4].call(siteArr0[5].call("Tinker does not support instant run mode, please trigger build", new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{" by assemble", " or disable instant run"})), " in 'File->Settings...'."));
                }
                else {
                    v_88 = alloc(Reference);
                    new (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerPatch", ""}), TinkerPatchSchemaTask.class), TinkerPatchSchemaTask.class).<init>(v_88);
                    Reference tinkerPatchBuildTask = v_88;
                    Object object = siteArr0[9].callGetProperty((ApkVariant)reference.get());
                    ScriptBytecodeAdapter.setGroovyObjectProperty(object, TinkerPatchPlugin$_apply_closure1$_closure2.class, (TinkerPatchSchemaTask)tinkerPatchBuildTask.get(), (String)"signConfig");
                    v_116 = alloc(Reference);
                    new siteArr0[10].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get()).<init>(v_116);
                    Reference agpProcessManifestTask = v_116;
                    v_126 = alloc(Reference);
                    new siteArr0[11].callConstructor(TinkerManifestAction.class, this.project.get()).<init>(v_126);
                    Reference tinkerManifestAction = v_126;
                    siteArr0[12].call(agpProcessManifestTask.get(), tinkerManifestAction.get());
                    siteArr0[13].call(siteArr0[14].callGetProperty((ApkVariant)reference.get()), new TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(this, this.getThisObject(), this.configuration, reference, tinkerPatchBuildTask, tinkerManifestAction, this.project, agpProcessManifestTask));
                    Object agpProcessResourcesTask = siteArr0[15].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    TinkerResourceIdTask applyResourceTask = (TinkerResourceIdTask)ScriptBytecodeAdapter.castToType(siteArr0[16].call(siteArr0[17].callGetProperty(siteArr0[18].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerProcess", "ResourceId"}), TinkerResourceIdTask.class), TinkerResourceIdTask.class);
                    variant = (ApkVariant)reference.get();
                    ScriptBytecodeAdapter.setGroovyObjectProperty(variant, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"variant");
                    Object objectVar1 = siteArr0[19].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar1, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"applicationId");
                    Object objectVar2 = siteArr0[20].call(Compatibilities.class, this.project.get(), agpProcessResourcesTask);
                    ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar2, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"resDir");
                    siteArr0[21].call(applyResourceTask, agpProcessManifestTask.get());
                    siteArr0[22].call(agpProcessResourcesTask, applyResourceTask);
                    Object agpMergeResourcesTask = siteArr0[23].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    siteArr0[24].call(applyResourceTask, agpMergeResourcesTask);
                    if (! ScriptBytecodeAdapter.compareEqual(siteArr0[25].callGetProperty(tinkerManifestAction.get()), null) || DefaultTypeTransformation.booleanUnbox(siteArr0[26].call(siteArr0[27].callGetProperty(tinkerManifestAction.get()))) ? 0 : 1 != 0) {
                        throw (Throwable)siteArr0[28].callConstructor(GradleException.class, "No manifest output path was found.");
                    }
                    else if (ScriptBytecodeAdapter.compareEqual(siteArr0[29].callGroovyObjectGetProperty(applyResourceTask), null)) {
                        throw (Throwable)siteArr0[30].callConstructor(GradleException.class, "applyResourceTask.resDir is null.");
                    }
                    else {
                        boolean proguardEnable = DefaultTypeTransformation.booleanUnbox(siteArr0[31].callGetProperty(siteArr0[32].callGetProperty(siteArr0[33].call((ApkVariant)reference.get()))));
                        if (proguardEnable) {
                            Object obfuscateTask = siteArr0[34].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            siteArr0[35].call(obfuscateTask, siteArr0[36].callConstructor(TinkerProguardConfigAction.class, (ApkVariant)reference.get()));
                        }
                        boolean multiDexEnabled = DefaultTypeTransformation.booleanUnbox(siteArr0[37].callGetProperty(siteArr0[38].callGetProperty((ApkVariant)reference.get())));
                        if (multiDexEnabled) {
                            TinkerMultidexConfigTask multidexConfigTask = (TinkerMultidexConfigTask)ScriptBytecodeAdapter.castToType(siteArr0[39].call(siteArr0[40].callGetProperty(siteArr0[41].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerProcess", "MultidexKeep"}), TinkerMultidexConfigTask.class), TinkerMultidexConfigTask.class);
                            ApkVariant variantVar1 = (ApkVariant)reference.get();
                            ScriptBytecodeAdapter.setGroovyObjectProperty(variantVar1, TinkerPatchPlugin$_apply_closure1$_closure2.class, multidexConfigTask, (String)"applicationVariant");
                            Object objectVar3 = siteArr0[42].callCurrent(this, (ApkVariant)reference.get());
                            ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar3, TinkerPatchPlugin$_apply_closure1$_closure2.class, multidexConfigTask, (String)"multiDexKeepProguard");
                            siteArr0[43].call(multidexConfigTask, agpProcessResourcesTask);
                            Object agpMultidexTask = siteArr0[44].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            Object agpR8Task = siteArr0[45].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            Object r8Transform;
                            File manifestMultiDexKeepProguard;
                            FileCollection originalFiles;
                            FileCollection replacedFiles;
                            if (BytecodeInterface8.isOrigZ() && TinkerPatchPlugin$_apply_closure1$_closure2.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                                goto 1619;
                                if (ScriptBytecodeAdapter.compareNotEqual(agpMultidexTask, null)) {
                                    siteArr0[66].call(agpMultidexTask, multidexConfigTask);
                                }
                                else {
                                    if (ScriptBytecodeAdapter.compareEqual(agpMultidexTask, null) && ScriptBytecodeAdapter.compareNotEqual(agpR8Task, null) ? 0 : 1 != 0) {
                                        siteArr0[67].call(agpR8Task, multidexConfigTask);
                                        try {
                                            try {
                                                r8Transform = siteArr0[68].call(agpR8Task);
                                                if (DefaultTypeTransformation.booleanUnbox(siteArr0[69].call(siteArr0[70].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"))) {
                                                    manifestMultiDexKeepProguard = (File)ScriptBytecodeAdapter.castToType(siteArr0[71].callCurrent(this, (ApkVariant)reference.get()), File.class);
                                                    if (ScriptBytecodeAdapter.compareNotEqual(manifestMultiDexKeepProguard, null)) {
                                                        originalFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[72].call(siteArr0[73].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"), FileCollection.class);
                                                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[74].call(originalFiles, manifestMultiDexKeepProguard)) ? 0 : 1 != 0) {
                                                            replacedFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[75].call(siteArr0[76].callGroovyObjectGetProperty(this), originalFiles, manifestMultiDexKeepProguard), FileCollection.class);
                                                            siteArr0[77].call(siteArr0[78].callGetProperty(siteArr0[79].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[80].callGetProperty(originalFiles)}, new String[]{"R8Transform original mainDexRulesFiles: ", ""}));
                                                            siteArr0[81].call(siteArr0[82].callGetProperty(siteArr0[83].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[84].callGetProperty(replacedFiles)}, new String[]{"R8Transform replaced mainDexRulesFiles: ", ""}));
                                                            siteArr0[85].callCurrent(this, "com.android.build.gradle.internal.transforms.R8Transform", "mainDexRulesFiles", r8Transform, replacedFiles);
                                                        }
                                                    }
                                                }
                                            }
                                            catch (Exception ignore) {
                                            }
                                        }
                                        finally {
                                            Throwable throwableVar1 = v_519;
                                            throw throwableVar1;
                                        }
                                    }
                                }
                            }
                            else {
                                if (ScriptBytecodeAdapter.compareNotEqual(agpMultidexTask, null)) {
                                    siteArr0[46].call(agpMultidexTask, multidexConfigTask);
                                }
                                else {
                                    if (ScriptBytecodeAdapter.compareEqual(agpMultidexTask, null) && ScriptBytecodeAdapter.compareNotEqual(agpR8Task, null) ? 0 : 1 != 0) {
                                        siteArr0[47].call(agpR8Task, multidexConfigTask);
                                        try {
                                            try {
                                                r8Transform = siteArr0[48].call(agpR8Task);
                                                if (DefaultTypeTransformation.booleanUnbox(siteArr0[49].call(siteArr0[50].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"))) {
                                                    manifestMultiDexKeepProguard = (File)ScriptBytecodeAdapter.castToType(siteArr0[51].callCurrent(this, (ApkVariant)reference.get()), File.class);
                                                    if (ScriptBytecodeAdapter.compareNotEqual(manifestMultiDexKeepProguard, null)) {
                                                        originalFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[52].call(siteArr0[53].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"), FileCollection.class);
                                                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[54].call(originalFiles, manifestMultiDexKeepProguard)) ? 0 : 1 != 0) {
                                                            replacedFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[55].call(siteArr0[56].callGroovyObjectGetProperty(this), originalFiles, manifestMultiDexKeepProguard), FileCollection.class);
                                                            siteArr0[57].call(siteArr0[58].callGetProperty(siteArr0[59].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[60].callGetProperty(originalFiles)}, new String[]{"R8Transform original mainDexRulesFiles: ", ""}));
                                                            siteArr0[61].call(siteArr0[62].callGetProperty(siteArr0[63].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[64].callGetProperty(replacedFiles)}, new String[]{"R8Transform replaced mainDexRulesFiles: ", ""}));
                                                            siteArr0[65].callCurrent(this, "com.android.build.gradle.internal.transforms.R8Transform", "mainDexRulesFiles", r8Transform, replacedFiles);
                                                        }
                                                    }
                                                }
                                            }
                                            catch (Exception ignore) {
                                            }
                                        }
                                        finally {
                                            Throwable throwable = v_681;
                                            throw throwable;
                                        }
                                    }
                                }
                                goto 2062;
                            }
                            Object collectMultiDexComponentsTask = siteArr0[86].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            if (ScriptBytecodeAdapter.compareNotEqual(collectMultiDexComponentsTask, null)) {
                                siteArr0[87].call(multidexConfigTask, collectMultiDexComponentsTask);
                            }
                        }
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[88].callGetProperty(siteArr0[89].callGetProperty(this.configuration.get()))) && DefaultTypeTransformation.booleanUnbox(siteArr0[90].call(FileOperation.class, siteArr0[91].callGetProperty(siteArr0[92].callGetProperty(siteArr0[93].callGroovyObjectGetProperty(this))))) ? 0 : 1 != 0) {
                            return siteArr0[94].call(ImmutableDexTransform.class, siteArr0[95].callGroovyObjectGetProperty(this), (ApkVariant)reference.get());
                        }
                        else {
                            return null;
                        }
                    }
                }
            }

            public Object call(ApkVariant variant) {
                v_1 = alloc(Reference);
                new variant.<init>(v_1);
                Reference reference = v_1;
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return siteArr0[96].callCurrent(this, (ApkVariant)reference.get());
            }

            @Generated
            public Project getProject() {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
            }

            @Generated
            public Object getConfiguration() {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return this.configuration.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "name";
                stringArr0[1] = "capitalize";
                stringArr0[2] = "getInstantRunTask";
                stringArr0[3] = "<$constructor$>";
                stringArr0[4] = "plus";
                stringArr0[5] = "plus";
                stringArr0[6] = "create";
                stringArr0[7] = "tasks";
                stringArr0[8] = "mProject";
                stringArr0[9] = "signingConfig";
                stringArr0[10] = "getProcessManifestTask";
                stringArr0[11] = "<$constructor$>";
                stringArr0[12] = "doLast";
                stringArr0[13] = "each";
                stringArr0[14] = "outputs";
                stringArr0[15] = "getProcessResourcesTask";
                stringArr0[16] = "create";
                stringArr0[17] = "tasks";
                stringArr0[18] = "mProject";
                stringArr0[19] = "getApplicationId";
                stringArr0[20] = "getInputResourcesDirectory";
                stringArr0[21] = "mustRunAfter";
                stringArr0[22] = "dependsOn";
                stringArr0[23] = "getMergeResourcesTask";
                stringArr0[24] = "dependsOn";
                stringArr0[25] = "outputNameToManifestMap";
                stringArr0[26] = "isEmpty";
                stringArr0[27] = "outputNameToManifestMap";
                stringArr0[28] = "<$constructor$>";
                stringArr0[29] = "resDir";
                stringArr0[30] = "<$constructor$>";
                stringArr0[31] = "minifyEnabled";
                stringArr0[32] = "buildType";
                stringArr0[33] = "getBuildType";
                stringArr0[34] = "getObfuscateTask";
                stringArr0[35] = "doFirst";
                stringArr0[36] = "<$constructor$>";
                stringArr0[37] = "multiDexEnabled";
                stringArr0[38] = "mergedFlavor";
                stringArr0[39] = "create";
                stringArr0[40] = "tasks";
                stringArr0[41] = "mProject";
                stringArr0[42] = "getManifestMultiDexKeepProguard";
                stringArr0[43] = "mustRunAfter";
                stringArr0[44] = "getMultiDexTask";
                stringArr0[45] = "getR8Task";
                stringArr0[46] = "dependsOn";
                stringArr0[47] = "dependsOn";
                stringArr0[48] = "getTransform";
                stringArr0[49] = "hasProperty";
                stringArr0[50] = "metaClass";
                stringArr0[51] = "getManifestMultiDexKeepProguard";
                stringArr0[52] = "getProperty";
                stringArr0[53] = "metaClass";
                stringArr0[54] = "contains";
                stringArr0[55] = "files";
                stringArr0[56] = "mProject";
                stringArr0[57] = "error";
                stringArr0[58] = "logger";
                stringArr0[59] = "mProject";
                stringArr0[60] = "files";
                stringArr0[61] = "error";
                stringArr0[62] = "logger";
                stringArr0[63] = "mProject";
                stringArr0[64] = "files";
                stringArr0[65] = "replaceKotlinFinalField";
                stringArr0[66] = "dependsOn";
                stringArr0[67] = "dependsOn";
                stringArr0[68] = "getTransform";
                stringArr0[69] = "hasProperty";
                stringArr0[70] = "metaClass";
                stringArr0[71] = "getManifestMultiDexKeepProguard";
                stringArr0[72] = "getProperty";
                stringArr0[73] = "metaClass";
                stringArr0[74] = "contains";
                stringArr0[75] = "files";
                stringArr0[76] = "mProject";
                stringArr0[77] = "error";
                stringArr0[78] = "logger";
                stringArr0[79] = "mProject";
                stringArr0[80] = "files";
                stringArr0[81] = "error";
                stringArr0[82] = "logger";
                stringArr0[83] = "mProject";
                stringArr0[84] = "files";
                stringArr0[85] = "replaceKotlinFinalField";
                stringArr0[86] = "getCollectMultiDexComponentsTask";
                stringArr0[87] = "mustRunAfter";
                stringArr0[88] = "keepDexApply";
                stringArr0[89] = "buildConfig";
                stringArr0[90] = "isLegalFile";
                stringArr0[91] = "oldApk";
                stringArr0[92] = "tinkerPatch";
                stringArr0[93] = "mProject";
                stringArr0[94] = "inject";
                stringArr0[95] = "mProject";
                stringArr0[96] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerPatchPlugin$_apply_closure1$_closure2.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray.get();
                TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

            // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2$_closure3
            public final class TinkerPatchPlugin$_apply_closure1$_closure2$_closure3 implements GeneratedClosure {
                private synthetic Reference configuration;
                private synthetic Reference variant;
                private synthetic Reference tinkerPatchBuildTask;
                private synthetic Reference tinkerManifestAction;
                private synthetic Reference project;
                private synthetic Reference agpProcessManifestTask;
                private static synthetic ClassInfo $staticClassInfo;
                public static transient synthetic boolean __$stMC;
                private static synthetic SoftReference $callSiteArray;

                public TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(Object _outerInstance, Object _thisObject, Reference configuration, Reference variant, Reference tinkerPatchBuildTask, Reference tinkerManifestAction, Reference project, Reference agpProcessManifestTask) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    configuration.configuration = this;
                    variant.variant = this;
                    tinkerPatchBuildTask.tinkerPatchBuildTask = this;
                    tinkerManifestAction.tinkerManifestAction = this;
                    project.project = this;
                    agpProcessManifestTask.agpProcessManifestTask = this;
                }

                public Object doCall(Object variantOutput) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    siteArr0[0].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    siteArr0[1].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    Object outputName = siteArr0[2].callGetProperty(variantOutput);
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(outputName, "/"))) {
                        Object object = siteArr0[4].call(outputName, Integer.valueOf(0), siteArr0[5].call(siteArr0[6].call(outputName), Integer.valueOf(1)));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(siteArr0[8].callGetProperty(this.tinkerManifestAction.get()), object))) {
                        throw (Throwable)siteArr0[9].callConstructor(GradleException.class, new GStringImpl(new Object[]{object}, new String[]{"Duplicate tinker manifest output name: '", "'"}));
                    }
                    else {
                        Object manifestPath = siteArr0[10].call(Compatibilities.class, this.project.get(), this.agpProcessManifestTask.get(), variantOutput);
                        return siteArr0[11].call(siteArr0[12].callGetProperty(this.tinkerManifestAction.get()), object, manifestPath);
                    }
                }

                @Generated
                public Object getConfiguration() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.configuration.get();
                }

                @Generated
                public ApkVariant getVariant() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
                }

                @Generated
                public TinkerPatchSchemaTask getTinkerPatchBuildTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(this.tinkerPatchBuildTask.get(), TinkerPatchSchemaTask.class);
                }

                @Generated
                public Object getTinkerManifestAction() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.tinkerManifestAction.get();
                }

                @Generated
                public Project getProject() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
                }

                @Generated
                public Object getAgpProcessManifestTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.agpProcessManifestTask.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    else {
                        if (TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo == null) {
                            infoVar1 = ClassInfo.getClassInfo(this.getClass());
                            TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo.getMetaClass();
                    }
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                    stringArr0[0] = "setPatchNewApkPath";
                    stringArr0[1] = "setPatchOutputFolder";
                    stringArr0[2] = "dirName";
                    stringArr0[3] = "endsWith";
                    stringArr0[4] = "substring";
                    stringArr0[5] = "minus";
                    stringArr0[6] = "length";
                    stringArr0[7] = "containsKey";
                    stringArr0[8] = "outputNameToManifestMap";
                    stringArr0[9] = "<$constructor$>";
                    stringArr0[10] = "getOutputManifestPath";
                    stringArr0[11] = "put";
                    stringArr0[12] = "outputNameToManifestMap";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String str0 = new String[]{};
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray_1(str0);
                    return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class, str0);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray.get();
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray = new SoftReference(array);
                    return var_0_0.array;
                }

            }
            // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2$_closure3
            public final class TinkerPatchPlugin$_apply_closure1$_closure2$_closure3 implements GeneratedClosure {
                private synthetic Reference configuration;
                private synthetic Reference variant;
                private synthetic Reference tinkerPatchBuildTask;
                private synthetic Reference tinkerManifestAction;
                private synthetic Reference project;
                private synthetic Reference agpProcessManifestTask;
                private static synthetic ClassInfo $staticClassInfo;
                public static transient synthetic boolean __$stMC;
                private static synthetic SoftReference $callSiteArray;

                public TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(Object _outerInstance, Object _thisObject, Reference configuration, Reference variant, Reference tinkerPatchBuildTask, Reference tinkerManifestAction, Reference project, Reference agpProcessManifestTask) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    configuration.configuration = this;
                    variant.variant = this;
                    tinkerPatchBuildTask.tinkerPatchBuildTask = this;
                    tinkerManifestAction.tinkerManifestAction = this;
                    project.project = this;
                    agpProcessManifestTask.agpProcessManifestTask = this;
                }

                public Object doCall(Object variantOutput) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    siteArr0[0].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    siteArr0[1].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    Object outputName = siteArr0[2].callGetProperty(variantOutput);
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(outputName, "/"))) {
                        Object object = siteArr0[4].call(outputName, Integer.valueOf(0), siteArr0[5].call(siteArr0[6].call(outputName), Integer.valueOf(1)));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(siteArr0[8].callGetProperty(this.tinkerManifestAction.get()), object))) {
                        throw (Throwable)siteArr0[9].callConstructor(GradleException.class, new GStringImpl(new Object[]{object}, new String[]{"Duplicate tinker manifest output name: '", "'"}));
                    }
                    else {
                        Object manifestPath = siteArr0[10].call(Compatibilities.class, this.project.get(), this.agpProcessManifestTask.get(), variantOutput);
                        return siteArr0[11].call(siteArr0[12].callGetProperty(this.tinkerManifestAction.get()), object, manifestPath);
                    }
                }

                @Generated
                public Object getConfiguration() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.configuration.get();
                }

                @Generated
                public ApkVariant getVariant() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
                }

                @Generated
                public TinkerPatchSchemaTask getTinkerPatchBuildTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(this.tinkerPatchBuildTask.get(), TinkerPatchSchemaTask.class);
                }

                @Generated
                public Object getTinkerManifestAction() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.tinkerManifestAction.get();
                }

                @Generated
                public Project getProject() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
                }

                @Generated
                public Object getAgpProcessManifestTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.agpProcessManifestTask.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    else {
                        if (TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo == null) {
                            infoVar1 = ClassInfo.getClassInfo(this.getClass());
                            TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo.getMetaClass();
                    }
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                    stringArr0[0] = "setPatchNewApkPath";
                    stringArr0[1] = "setPatchOutputFolder";
                    stringArr0[2] = "dirName";
                    stringArr0[3] = "endsWith";
                    stringArr0[4] = "substring";
                    stringArr0[5] = "minus";
                    stringArr0[6] = "length";
                    stringArr0[7] = "containsKey";
                    stringArr0[8] = "outputNameToManifestMap";
                    stringArr0[9] = "<$constructor$>";
                    stringArr0[10] = "getOutputManifestPath";
                    stringArr0[11] = "put";
                    stringArr0[12] = "outputNameToManifestMap";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String str0 = new String[]{};
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray_1(str0);
                    return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class, str0);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray.get();
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray = new SoftReference(array);
                    return var_0_0.array;
                }

            }
        }
        // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2
        public final class TinkerPatchPlugin$_apply_closure1$_closure2 implements GeneratedClosure {
            private synthetic Reference project;
            private synthetic Reference configuration;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerPatchPlugin$_apply_closure1$_closure2(Object _outerInstance, Object _thisObject, Reference project, Reference configuration) {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                project.project = this;
                configuration.configuration = this;
            }

            public Object doCall(ApkVariant variant) {
                v_1 = alloc(Reference);
                new variant.<init>(v_1);
                Reference reference = v_1;
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                Object variantName = siteArr0[0].callGetProperty((ApkVariant)reference.get());
                Object capitalizedVariantName = siteArr0[1].call(variantName);
                Object instantRunTask = siteArr0[2].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                if (ScriptBytecodeAdapter.compareNotEqual(instantRunTask, null)) {
                    throw (Throwable)siteArr0[3].callConstructor(GradleException.class, siteArr0[4].call(siteArr0[5].call("Tinker does not support instant run mode, please trigger build", new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{" by assemble", " or disable instant run"})), " in 'File->Settings...'."));
                }
                else {
                    v_88 = alloc(Reference);
                    new (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerPatch", ""}), TinkerPatchSchemaTask.class), TinkerPatchSchemaTask.class).<init>(v_88);
                    Reference tinkerPatchBuildTask = v_88;
                    Object object = siteArr0[9].callGetProperty((ApkVariant)reference.get());
                    ScriptBytecodeAdapter.setGroovyObjectProperty(object, TinkerPatchPlugin$_apply_closure1$_closure2.class, (TinkerPatchSchemaTask)tinkerPatchBuildTask.get(), (String)"signConfig");
                    v_116 = alloc(Reference);
                    new siteArr0[10].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get()).<init>(v_116);
                    Reference agpProcessManifestTask = v_116;
                    v_126 = alloc(Reference);
                    new siteArr0[11].callConstructor(TinkerManifestAction.class, this.project.get()).<init>(v_126);
                    Reference tinkerManifestAction = v_126;
                    siteArr0[12].call(agpProcessManifestTask.get(), tinkerManifestAction.get());
                    siteArr0[13].call(siteArr0[14].callGetProperty((ApkVariant)reference.get()), new TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(this, this.getThisObject(), this.configuration, reference, tinkerPatchBuildTask, tinkerManifestAction, this.project, agpProcessManifestTask));
                    Object agpProcessResourcesTask = siteArr0[15].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    TinkerResourceIdTask applyResourceTask = (TinkerResourceIdTask)ScriptBytecodeAdapter.castToType(siteArr0[16].call(siteArr0[17].callGetProperty(siteArr0[18].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerProcess", "ResourceId"}), TinkerResourceIdTask.class), TinkerResourceIdTask.class);
                    variant = (ApkVariant)reference.get();
                    ScriptBytecodeAdapter.setGroovyObjectProperty(variant, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"variant");
                    Object objectVar1 = siteArr0[19].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar1, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"applicationId");
                    Object objectVar2 = siteArr0[20].call(Compatibilities.class, this.project.get(), agpProcessResourcesTask);
                    ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar2, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"resDir");
                    siteArr0[21].call(applyResourceTask, agpProcessManifestTask.get());
                    siteArr0[22].call(agpProcessResourcesTask, applyResourceTask);
                    Object agpMergeResourcesTask = siteArr0[23].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    siteArr0[24].call(applyResourceTask, agpMergeResourcesTask);
                    if (! ScriptBytecodeAdapter.compareEqual(siteArr0[25].callGetProperty(tinkerManifestAction.get()), null) || DefaultTypeTransformation.booleanUnbox(siteArr0[26].call(siteArr0[27].callGetProperty(tinkerManifestAction.get()))) ? 0 : 1 != 0) {
                        throw (Throwable)siteArr0[28].callConstructor(GradleException.class, "No manifest output path was found.");
                    }
                    else if (ScriptBytecodeAdapter.compareEqual(siteArr0[29].callGroovyObjectGetProperty(applyResourceTask), null)) {
                        throw (Throwable)siteArr0[30].callConstructor(GradleException.class, "applyResourceTask.resDir is null.");
                    }
                    else {
                        boolean proguardEnable = DefaultTypeTransformation.booleanUnbox(siteArr0[31].callGetProperty(siteArr0[32].callGetProperty(siteArr0[33].call((ApkVariant)reference.get()))));
                        if (proguardEnable) {
                            Object obfuscateTask = siteArr0[34].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            siteArr0[35].call(obfuscateTask, siteArr0[36].callConstructor(TinkerProguardConfigAction.class, (ApkVariant)reference.get()));
                        }
                        boolean multiDexEnabled = DefaultTypeTransformation.booleanUnbox(siteArr0[37].callGetProperty(siteArr0[38].callGetProperty((ApkVariant)reference.get())));
                        if (multiDexEnabled) {
                            TinkerMultidexConfigTask multidexConfigTask = (TinkerMultidexConfigTask)ScriptBytecodeAdapter.castToType(siteArr0[39].call(siteArr0[40].callGetProperty(siteArr0[41].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerProcess", "MultidexKeep"}), TinkerMultidexConfigTask.class), TinkerMultidexConfigTask.class);
                            ApkVariant variantVar1 = (ApkVariant)reference.get();
                            ScriptBytecodeAdapter.setGroovyObjectProperty(variantVar1, TinkerPatchPlugin$_apply_closure1$_closure2.class, multidexConfigTask, (String)"applicationVariant");
                            Object objectVar3 = siteArr0[42].callCurrent(this, (ApkVariant)reference.get());
                            ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar3, TinkerPatchPlugin$_apply_closure1$_closure2.class, multidexConfigTask, (String)"multiDexKeepProguard");
                            siteArr0[43].call(multidexConfigTask, agpProcessResourcesTask);
                            Object agpMultidexTask = siteArr0[44].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            Object agpR8Task = siteArr0[45].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            Object r8Transform;
                            File manifestMultiDexKeepProguard;
                            FileCollection originalFiles;
                            FileCollection replacedFiles;
                            if (BytecodeInterface8.isOrigZ() && TinkerPatchPlugin$_apply_closure1$_closure2.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                                goto 1619;
                                if (ScriptBytecodeAdapter.compareNotEqual(agpMultidexTask, null)) {
                                    siteArr0[66].call(agpMultidexTask, multidexConfigTask);
                                }
                                else {
                                    if (ScriptBytecodeAdapter.compareEqual(agpMultidexTask, null) && ScriptBytecodeAdapter.compareNotEqual(agpR8Task, null) ? 0 : 1 != 0) {
                                        siteArr0[67].call(agpR8Task, multidexConfigTask);
                                        try {
                                            try {
                                                r8Transform = siteArr0[68].call(agpR8Task);
                                                if (DefaultTypeTransformation.booleanUnbox(siteArr0[69].call(siteArr0[70].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"))) {
                                                    manifestMultiDexKeepProguard = (File)ScriptBytecodeAdapter.castToType(siteArr0[71].callCurrent(this, (ApkVariant)reference.get()), File.class);
                                                    if (ScriptBytecodeAdapter.compareNotEqual(manifestMultiDexKeepProguard, null)) {
                                                        originalFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[72].call(siteArr0[73].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"), FileCollection.class);
                                                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[74].call(originalFiles, manifestMultiDexKeepProguard)) ? 0 : 1 != 0) {
                                                            replacedFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[75].call(siteArr0[76].callGroovyObjectGetProperty(this), originalFiles, manifestMultiDexKeepProguard), FileCollection.class);
                                                            siteArr0[77].call(siteArr0[78].callGetProperty(siteArr0[79].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[80].callGetProperty(originalFiles)}, new String[]{"R8Transform original mainDexRulesFiles: ", ""}));
                                                            siteArr0[81].call(siteArr0[82].callGetProperty(siteArr0[83].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[84].callGetProperty(replacedFiles)}, new String[]{"R8Transform replaced mainDexRulesFiles: ", ""}));
                                                            siteArr0[85].callCurrent(this, "com.android.build.gradle.internal.transforms.R8Transform", "mainDexRulesFiles", r8Transform, replacedFiles);
                                                        }
                                                    }
                                                }
                                            }
                                            catch (Exception ignore) {
                                            }
                                        }
                                        finally {
                                            Throwable throwableVar1 = v_519;
                                            throw throwableVar1;
                                        }
                                    }
                                }
                            }
                            else {
                                if (ScriptBytecodeAdapter.compareNotEqual(agpMultidexTask, null)) {
                                    siteArr0[46].call(agpMultidexTask, multidexConfigTask);
                                }
                                else {
                                    if (ScriptBytecodeAdapter.compareEqual(agpMultidexTask, null) && ScriptBytecodeAdapter.compareNotEqual(agpR8Task, null) ? 0 : 1 != 0) {
                                        siteArr0[47].call(agpR8Task, multidexConfigTask);
                                        try {
                                            try {
                                                r8Transform = siteArr0[48].call(agpR8Task);
                                                if (DefaultTypeTransformation.booleanUnbox(siteArr0[49].call(siteArr0[50].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"))) {
                                                    manifestMultiDexKeepProguard = (File)ScriptBytecodeAdapter.castToType(siteArr0[51].callCurrent(this, (ApkVariant)reference.get()), File.class);
                                                    if (ScriptBytecodeAdapter.compareNotEqual(manifestMultiDexKeepProguard, null)) {
                                                        originalFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[52].call(siteArr0[53].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"), FileCollection.class);
                                                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[54].call(originalFiles, manifestMultiDexKeepProguard)) ? 0 : 1 != 0) {
                                                            replacedFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[55].call(siteArr0[56].callGroovyObjectGetProperty(this), originalFiles, manifestMultiDexKeepProguard), FileCollection.class);
                                                            siteArr0[57].call(siteArr0[58].callGetProperty(siteArr0[59].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[60].callGetProperty(originalFiles)}, new String[]{"R8Transform original mainDexRulesFiles: ", ""}));
                                                            siteArr0[61].call(siteArr0[62].callGetProperty(siteArr0[63].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[64].callGetProperty(replacedFiles)}, new String[]{"R8Transform replaced mainDexRulesFiles: ", ""}));
                                                            siteArr0[65].callCurrent(this, "com.android.build.gradle.internal.transforms.R8Transform", "mainDexRulesFiles", r8Transform, replacedFiles);
                                                        }
                                                    }
                                                }
                                            }
                                            catch (Exception ignore) {
                                            }
                                        }
                                        finally {
                                            Throwable throwable = v_681;
                                            throw throwable;
                                        }
                                    }
                                }
                                goto 2062;
                            }
                            Object collectMultiDexComponentsTask = siteArr0[86].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            if (ScriptBytecodeAdapter.compareNotEqual(collectMultiDexComponentsTask, null)) {
                                siteArr0[87].call(multidexConfigTask, collectMultiDexComponentsTask);
                            }
                        }
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[88].callGetProperty(siteArr0[89].callGetProperty(this.configuration.get()))) && DefaultTypeTransformation.booleanUnbox(siteArr0[90].call(FileOperation.class, siteArr0[91].callGetProperty(siteArr0[92].callGetProperty(siteArr0[93].callGroovyObjectGetProperty(this))))) ? 0 : 1 != 0) {
                            return siteArr0[94].call(ImmutableDexTransform.class, siteArr0[95].callGroovyObjectGetProperty(this), (ApkVariant)reference.get());
                        }
                        else {
                            return null;
                        }
                    }
                }
            }

            public Object call(ApkVariant variant) {
                v_1 = alloc(Reference);
                new variant.<init>(v_1);
                Reference reference = v_1;
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return siteArr0[96].callCurrent(this, (ApkVariant)reference.get());
            }

            @Generated
            public Project getProject() {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
            }

            @Generated
            public Object getConfiguration() {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return this.configuration.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "name";
                stringArr0[1] = "capitalize";
                stringArr0[2] = "getInstantRunTask";
                stringArr0[3] = "<$constructor$>";
                stringArr0[4] = "plus";
                stringArr0[5] = "plus";
                stringArr0[6] = "create";
                stringArr0[7] = "tasks";
                stringArr0[8] = "mProject";
                stringArr0[9] = "signingConfig";
                stringArr0[10] = "getProcessManifestTask";
                stringArr0[11] = "<$constructor$>";
                stringArr0[12] = "doLast";
                stringArr0[13] = "each";
                stringArr0[14] = "outputs";
                stringArr0[15] = "getProcessResourcesTask";
                stringArr0[16] = "create";
                stringArr0[17] = "tasks";
                stringArr0[18] = "mProject";
                stringArr0[19] = "getApplicationId";
                stringArr0[20] = "getInputResourcesDirectory";
                stringArr0[21] = "mustRunAfter";
                stringArr0[22] = "dependsOn";
                stringArr0[23] = "getMergeResourcesTask";
                stringArr0[24] = "dependsOn";
                stringArr0[25] = "outputNameToManifestMap";
                stringArr0[26] = "isEmpty";
                stringArr0[27] = "outputNameToManifestMap";
                stringArr0[28] = "<$constructor$>";
                stringArr0[29] = "resDir";
                stringArr0[30] = "<$constructor$>";
                stringArr0[31] = "minifyEnabled";
                stringArr0[32] = "buildType";
                stringArr0[33] = "getBuildType";
                stringArr0[34] = "getObfuscateTask";
                stringArr0[35] = "doFirst";
                stringArr0[36] = "<$constructor$>";
                stringArr0[37] = "multiDexEnabled";
                stringArr0[38] = "mergedFlavor";
                stringArr0[39] = "create";
                stringArr0[40] = "tasks";
                stringArr0[41] = "mProject";
                stringArr0[42] = "getManifestMultiDexKeepProguard";
                stringArr0[43] = "mustRunAfter";
                stringArr0[44] = "getMultiDexTask";
                stringArr0[45] = "getR8Task";
                stringArr0[46] = "dependsOn";
                stringArr0[47] = "dependsOn";
                stringArr0[48] = "getTransform";
                stringArr0[49] = "hasProperty";
                stringArr0[50] = "metaClass";
                stringArr0[51] = "getManifestMultiDexKeepProguard";
                stringArr0[52] = "getProperty";
                stringArr0[53] = "metaClass";
                stringArr0[54] = "contains";
                stringArr0[55] = "files";
                stringArr0[56] = "mProject";
                stringArr0[57] = "error";
                stringArr0[58] = "logger";
                stringArr0[59] = "mProject";
                stringArr0[60] = "files";
                stringArr0[61] = "error";
                stringArr0[62] = "logger";
                stringArr0[63] = "mProject";
                stringArr0[64] = "files";
                stringArr0[65] = "replaceKotlinFinalField";
                stringArr0[66] = "dependsOn";
                stringArr0[67] = "dependsOn";
                stringArr0[68] = "getTransform";
                stringArr0[69] = "hasProperty";
                stringArr0[70] = "metaClass";
                stringArr0[71] = "getManifestMultiDexKeepProguard";
                stringArr0[72] = "getProperty";
                stringArr0[73] = "metaClass";
                stringArr0[74] = "contains";
                stringArr0[75] = "files";
                stringArr0[76] = "mProject";
                stringArr0[77] = "error";
                stringArr0[78] = "logger";
                stringArr0[79] = "mProject";
                stringArr0[80] = "files";
                stringArr0[81] = "error";
                stringArr0[82] = "logger";
                stringArr0[83] = "mProject";
                stringArr0[84] = "files";
                stringArr0[85] = "replaceKotlinFinalField";
                stringArr0[86] = "getCollectMultiDexComponentsTask";
                stringArr0[87] = "mustRunAfter";
                stringArr0[88] = "keepDexApply";
                stringArr0[89] = "buildConfig";
                stringArr0[90] = "isLegalFile";
                stringArr0[91] = "oldApk";
                stringArr0[92] = "tinkerPatch";
                stringArr0[93] = "mProject";
                stringArr0[94] = "inject";
                stringArr0[95] = "mProject";
                stringArr0[96] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerPatchPlugin$_apply_closure1$_closure2.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray.get();
                TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

            // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2$_closure3
            public final class TinkerPatchPlugin$_apply_closure1$_closure2$_closure3 implements GeneratedClosure {
                private synthetic Reference configuration;
                private synthetic Reference variant;
                private synthetic Reference tinkerPatchBuildTask;
                private synthetic Reference tinkerManifestAction;
                private synthetic Reference project;
                private synthetic Reference agpProcessManifestTask;
                private static synthetic ClassInfo $staticClassInfo;
                public static transient synthetic boolean __$stMC;
                private static synthetic SoftReference $callSiteArray;

                public TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(Object _outerInstance, Object _thisObject, Reference configuration, Reference variant, Reference tinkerPatchBuildTask, Reference tinkerManifestAction, Reference project, Reference agpProcessManifestTask) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    configuration.configuration = this;
                    variant.variant = this;
                    tinkerPatchBuildTask.tinkerPatchBuildTask = this;
                    tinkerManifestAction.tinkerManifestAction = this;
                    project.project = this;
                    agpProcessManifestTask.agpProcessManifestTask = this;
                }

                public Object doCall(Object variantOutput) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    siteArr0[0].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    siteArr0[1].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    Object outputName = siteArr0[2].callGetProperty(variantOutput);
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(outputName, "/"))) {
                        Object object = siteArr0[4].call(outputName, Integer.valueOf(0), siteArr0[5].call(siteArr0[6].call(outputName), Integer.valueOf(1)));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(siteArr0[8].callGetProperty(this.tinkerManifestAction.get()), object))) {
                        throw (Throwable)siteArr0[9].callConstructor(GradleException.class, new GStringImpl(new Object[]{object}, new String[]{"Duplicate tinker manifest output name: '", "'"}));
                    }
                    else {
                        Object manifestPath = siteArr0[10].call(Compatibilities.class, this.project.get(), this.agpProcessManifestTask.get(), variantOutput);
                        return siteArr0[11].call(siteArr0[12].callGetProperty(this.tinkerManifestAction.get()), object, manifestPath);
                    }
                }

                @Generated
                public Object getConfiguration() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.configuration.get();
                }

                @Generated
                public ApkVariant getVariant() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
                }

                @Generated
                public TinkerPatchSchemaTask getTinkerPatchBuildTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(this.tinkerPatchBuildTask.get(), TinkerPatchSchemaTask.class);
                }

                @Generated
                public Object getTinkerManifestAction() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.tinkerManifestAction.get();
                }

                @Generated
                public Project getProject() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
                }

                @Generated
                public Object getAgpProcessManifestTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.agpProcessManifestTask.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    else {
                        if (TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo == null) {
                            infoVar1 = ClassInfo.getClassInfo(this.getClass());
                            TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo.getMetaClass();
                    }
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                    stringArr0[0] = "setPatchNewApkPath";
                    stringArr0[1] = "setPatchOutputFolder";
                    stringArr0[2] = "dirName";
                    stringArr0[3] = "endsWith";
                    stringArr0[4] = "substring";
                    stringArr0[5] = "minus";
                    stringArr0[6] = "length";
                    stringArr0[7] = "containsKey";
                    stringArr0[8] = "outputNameToManifestMap";
                    stringArr0[9] = "<$constructor$>";
                    stringArr0[10] = "getOutputManifestPath";
                    stringArr0[11] = "put";
                    stringArr0[12] = "outputNameToManifestMap";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String str0 = new String[]{};
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray_1(str0);
                    return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class, str0);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray.get();
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray = new SoftReference(array);
                    return var_0_0.array;
                }

            }
            // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2$_closure3
            public final class TinkerPatchPlugin$_apply_closure1$_closure2$_closure3 implements GeneratedClosure {
                private synthetic Reference configuration;
                private synthetic Reference variant;
                private synthetic Reference tinkerPatchBuildTask;
                private synthetic Reference tinkerManifestAction;
                private synthetic Reference project;
                private synthetic Reference agpProcessManifestTask;
                private static synthetic ClassInfo $staticClassInfo;
                public static transient synthetic boolean __$stMC;
                private static synthetic SoftReference $callSiteArray;

                public TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(Object _outerInstance, Object _thisObject, Reference configuration, Reference variant, Reference tinkerPatchBuildTask, Reference tinkerManifestAction, Reference project, Reference agpProcessManifestTask) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    configuration.configuration = this;
                    variant.variant = this;
                    tinkerPatchBuildTask.tinkerPatchBuildTask = this;
                    tinkerManifestAction.tinkerManifestAction = this;
                    project.project = this;
                    agpProcessManifestTask.agpProcessManifestTask = this;
                }

                public Object doCall(Object variantOutput) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    siteArr0[0].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    siteArr0[1].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    Object outputName = siteArr0[2].callGetProperty(variantOutput);
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(outputName, "/"))) {
                        Object object = siteArr0[4].call(outputName, Integer.valueOf(0), siteArr0[5].call(siteArr0[6].call(outputName), Integer.valueOf(1)));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(siteArr0[8].callGetProperty(this.tinkerManifestAction.get()), object))) {
                        throw (Throwable)siteArr0[9].callConstructor(GradleException.class, new GStringImpl(new Object[]{object}, new String[]{"Duplicate tinker manifest output name: '", "'"}));
                    }
                    else {
                        Object manifestPath = siteArr0[10].call(Compatibilities.class, this.project.get(), this.agpProcessManifestTask.get(), variantOutput);
                        return siteArr0[11].call(siteArr0[12].callGetProperty(this.tinkerManifestAction.get()), object, manifestPath);
                    }
                }

                @Generated
                public Object getConfiguration() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.configuration.get();
                }

                @Generated
                public ApkVariant getVariant() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
                }

                @Generated
                public TinkerPatchSchemaTask getTinkerPatchBuildTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(this.tinkerPatchBuildTask.get(), TinkerPatchSchemaTask.class);
                }

                @Generated
                public Object getTinkerManifestAction() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.tinkerManifestAction.get();
                }

                @Generated
                public Project getProject() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
                }

                @Generated
                public Object getAgpProcessManifestTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.agpProcessManifestTask.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    else {
                        if (TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo == null) {
                            infoVar1 = ClassInfo.getClassInfo(this.getClass());
                            TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo.getMetaClass();
                    }
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                    stringArr0[0] = "setPatchNewApkPath";
                    stringArr0[1] = "setPatchOutputFolder";
                    stringArr0[2] = "dirName";
                    stringArr0[3] = "endsWith";
                    stringArr0[4] = "substring";
                    stringArr0[5] = "minus";
                    stringArr0[6] = "length";
                    stringArr0[7] = "containsKey";
                    stringArr0[8] = "outputNameToManifestMap";
                    stringArr0[9] = "<$constructor$>";
                    stringArr0[10] = "getOutputManifestPath";
                    stringArr0[11] = "put";
                    stringArr0[12] = "outputNameToManifestMap";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String str0 = new String[]{};
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray_1(str0);
                    return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class, str0);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray.get();
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray = new SoftReference(array);
                    return var_0_0.array;
                }

            }
        }
    }
    // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1
    public final class TinkerPatchPlugin$_apply_closure1 implements GeneratedClosure {
        private synthetic Reference project;
        private synthetic Reference android;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPatchPlugin$_apply_closure1(Object _outerInstance, Object _thisObject, Reference project, Reference android) {
            CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            project.project = this;
            android.android = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1.$getCallSiteArray();
            v_10 = alloc(Reference);
            new siteArr0[0].callGetProperty(siteArr0[1].callGroovyObjectGetProperty(this)).<init>(v_10);
            Reference configuration = v_10;
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[2].callGetProperty(configuration.get())) ? 0 : 1 != 0) {
                siteArr0[3].call(siteArr0[4].callGetProperty(siteArr0[5].callGroovyObjectGetProperty(this)), "tinker tasks are disabled.");
                return null;
            }
            else {
                siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), "----------------------tinker build warning ------------------------------------");
                siteArr0[9].call(siteArr0[10].callGetProperty(siteArr0[11].callGroovyObjectGetProperty(this)), "tinker auto operation: ");
                siteArr0[12].call(siteArr0[13].callGetProperty(siteArr0[14].callGroovyObjectGetProperty(this)), "excluding annotation processor and source template from app packaging. Enable dx jumboMode to reduce package size.");
                siteArr0[15].call(siteArr0[16].callGetProperty(siteArr0[17].callGroovyObjectGetProperty(this)), "enable dx jumboMode to reduce package size.");
                siteArr0[18].call(siteArr0[19].callGetProperty(siteArr0[20].callGroovyObjectGetProperty(this)), "disable preDexLibraries to prevent ClassDefNotFoundException when your app is booting.");
                siteArr0[21].call(siteArr0[22].callGetProperty(siteArr0[23].callGroovyObjectGetProperty(this)), "disable archive dex mode so far for keeping dex apply.");
                siteArr0[24].call(siteArr0[25].callGetProperty(siteArr0[26].callGroovyObjectGetProperty(this)), "");
                siteArr0[27].call(siteArr0[28].callGetProperty(siteArr0[29].callGroovyObjectGetProperty(this)), "tinker will change your build configs:");
                siteArr0[30].call(siteArr0[31].callGetProperty(siteArr0[32].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[33].callGetProperty(siteArr0[34].callGetProperty(configuration.get())), siteArr0[35].callGetProperty(this.project.get())}, new String[]{"we will add TINKER_ID=", " in your build output manifest file ", "/intermediates/manifests/full/*"}));
                siteArr0[36].call(siteArr0[37].callGetProperty(siteArr0[38].callGroovyObjectGetProperty(this)), "");
                siteArr0[39].call(siteArr0[40].callGetProperty(siteArr0[41].callGroovyObjectGetProperty(this)), "if minifyEnabled is true");
                String tempMappingPath = (String)ShortTypeHandling.castToString(siteArr0[42].callGetProperty(siteArr0[43].callGetProperty(configuration.get())));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[44].call(FileOperation.class, tempMappingPath))) {
                    siteArr0[45].call(siteArr0[46].callGetProperty(siteArr0[47].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[48].call(siteArr0[49].callGroovyObjectGetProperty(this)), tempMappingPath}, new String[]{"we will build ", " apk with apply mapping file ", ""}));
                }
                siteArr0[50].call(siteArr0[51].callGetProperty(siteArr0[52].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[53].call(TinkerBuildPath.class, this.project.get())}, new String[]{"you will find the gen proguard rule file at ", ""}));
                siteArr0[54].call(siteArr0[55].callGetProperty(siteArr0[56].callGroovyObjectGetProperty(this)), "and we will help you to put it in the proguardFiles.");
                siteArr0[57].call(siteArr0[58].callGetProperty(siteArr0[59].callGroovyObjectGetProperty(this)), "");
                siteArr0[60].call(siteArr0[61].callGetProperty(siteArr0[62].callGroovyObjectGetProperty(this)), "if multiDexEnabled is true");
                siteArr0[63].call(siteArr0[64].callGetProperty(siteArr0[65].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[66].call(TinkerBuildPath.class, this.project.get())}, new String[]{"you will find the gen multiDexKeepProguard file at ", ""}));
                siteArr0[67].call(siteArr0[68].callGetProperty(siteArr0[69].callGroovyObjectGetProperty(this)), "and we will help you to put it in the MultiDexKeepProguardFile.");
                siteArr0[70].call(siteArr0[71].callGetProperty(siteArr0[72].callGroovyObjectGetProperty(this)), "");
                siteArr0[73].call(siteArr0[74].callGetProperty(siteArr0[75].callGroovyObjectGetProperty(this)), "if applyResourceMapping file is exist");
                String tempResourceMappingPath = (String)ShortTypeHandling.castToString(siteArr0[76].callGetProperty(siteArr0[77].callGetProperty(configuration.get())));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[78].call(FileOperation.class, tempResourceMappingPath))) {
                    siteArr0[79].call(siteArr0[80].callGetProperty(siteArr0[81].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[82].call(siteArr0[83].callGroovyObjectGetProperty(this)), tempResourceMappingPath}, new String[]{"we will build ", " apk with resource R.txt ", " file"}));
                }
                else {
                    siteArr0[84].call(siteArr0[85].callGetProperty(siteArr0[86].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[87].call(siteArr0[88].callGroovyObjectGetProperty(this))}, new String[]{"we will build ", " apk with resource R.txt file"}));
                }
                siteArr0[89].call(siteArr0[90].callGetProperty(siteArr0[91].callGroovyObjectGetProperty(this)), "if resources.arsc has changed, you should use applyResource mode to build the new apk!");
                siteArr0[92].call(siteArr0[93].callGetProperty(siteArr0[94].callGroovyObjectGetProperty(this)), "-----------------------------------------------------------------");
                return siteArr0[95].call(siteArr0[96].callGetProperty(this.android.get()), new TinkerPatchPlugin$_apply_closure1$_closure2(this, this.getThisObject(), this.project, configuration));
            }
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public Object getAndroid() {
            CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1.$getCallSiteArray();
            return this.android.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPatchPlugin$_apply_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPatchPlugin$_apply_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPatchPlugin$_apply_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPatchPlugin$_apply_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "tinkerPatch";
            stringArr0[1] = "mProject";
            stringArr0[2] = "tinkerEnable";
            stringArr0[3] = "error";
            stringArr0[4] = "logger";
            stringArr0[5] = "mProject";
            stringArr0[6] = "error";
            stringArr0[7] = "logger";
            stringArr0[8] = "mProject";
            stringArr0[9] = "error";
            stringArr0[10] = "logger";
            stringArr0[11] = "mProject";
            stringArr0[12] = "error";
            stringArr0[13] = "logger";
            stringArr0[14] = "mProject";
            stringArr0[15] = "error";
            stringArr0[16] = "logger";
            stringArr0[17] = "mProject";
            stringArr0[18] = "error";
            stringArr0[19] = "logger";
            stringArr0[20] = "mProject";
            stringArr0[21] = "error";
            stringArr0[22] = "logger";
            stringArr0[23] = "mProject";
            stringArr0[24] = "error";
            stringArr0[25] = "logger";
            stringArr0[26] = "mProject";
            stringArr0[27] = "error";
            stringArr0[28] = "logger";
            stringArr0[29] = "mProject";
            stringArr0[30] = "error";
            stringArr0[31] = "logger";
            stringArr0[32] = "mProject";
            stringArr0[33] = "tinkerId";
            stringArr0[34] = "buildConfig";
            stringArr0[35] = "buildDir";
            stringArr0[36] = "error";
            stringArr0[37] = "logger";
            stringArr0[38] = "mProject";
            stringArr0[39] = "error";
            stringArr0[40] = "logger";
            stringArr0[41] = "mProject";
            stringArr0[42] = "applyMapping";
            stringArr0[43] = "buildConfig";
            stringArr0[44] = "isLegalFile";
            stringArr0[45] = "error";
            stringArr0[46] = "logger";
            stringArr0[47] = "mProject";
            stringArr0[48] = "getName";
            stringArr0[49] = "mProject";
            stringArr0[50] = "error";
            stringArr0[51] = "logger";
            stringArr0[52] = "mProject";
            stringArr0[53] = "getProguardConfigPath";
            stringArr0[54] = "error";
            stringArr0[55] = "logger";
            stringArr0[56] = "mProject";
            stringArr0[57] = "error";
            stringArr0[58] = "logger";
            stringArr0[59] = "mProject";
            stringArr0[60] = "error";
            stringArr0[61] = "logger";
            stringArr0[62] = "mProject";
            stringArr0[63] = "error";
            stringArr0[64] = "logger";
            stringArr0[65] = "mProject";
            stringArr0[66] = "getMultidexConfigPath";
            stringArr0[67] = "error";
            stringArr0[68] = "logger";
            stringArr0[69] = "mProject";
            stringArr0[70] = "error";
            stringArr0[71] = "logger";
            stringArr0[72] = "mProject";
            stringArr0[73] = "error";
            stringArr0[74] = "logger";
            stringArr0[75] = "mProject";
            stringArr0[76] = "applyResourceMapping";
            stringArr0[77] = "buildConfig";
            stringArr0[78] = "isLegalFile";
            stringArr0[79] = "error";
            stringArr0[80] = "logger";
            stringArr0[81] = "mProject";
            stringArr0[82] = "getName";
            stringArr0[83] = "mProject";
            stringArr0[84] = "error";
            stringArr0[85] = "logger";
            stringArr0[86] = "mProject";
            stringArr0[87] = "getName";
            stringArr0[88] = "mProject";
            stringArr0[89] = "error";
            stringArr0[90] = "logger";
            stringArr0[91] = "mProject";
            stringArr0[92] = "error";
            stringArr0[93] = "logger";
            stringArr0[94] = "mProject";
            stringArr0[95] = "all";
            stringArr0[96] = "applicationVariants";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPatchPlugin$_apply_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPatchPlugin$_apply_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPatchPlugin$_apply_closure1.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1.$callSiteArray.get();
            TinkerPatchPlugin$_apply_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2
        public final class TinkerPatchPlugin$_apply_closure1$_closure2 implements GeneratedClosure {
            private synthetic Reference project;
            private synthetic Reference configuration;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerPatchPlugin$_apply_closure1$_closure2(Object _outerInstance, Object _thisObject, Reference project, Reference configuration) {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                project.project = this;
                configuration.configuration = this;
            }

            public Object doCall(ApkVariant variant) {
                v_1 = alloc(Reference);
                new variant.<init>(v_1);
                Reference reference = v_1;
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                Object variantName = siteArr0[0].callGetProperty((ApkVariant)reference.get());
                Object capitalizedVariantName = siteArr0[1].call(variantName);
                Object instantRunTask = siteArr0[2].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                if (ScriptBytecodeAdapter.compareNotEqual(instantRunTask, null)) {
                    throw (Throwable)siteArr0[3].callConstructor(GradleException.class, siteArr0[4].call(siteArr0[5].call("Tinker does not support instant run mode, please trigger build", new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{" by assemble", " or disable instant run"})), " in 'File->Settings...'."));
                }
                else {
                    v_88 = alloc(Reference);
                    new (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerPatch", ""}), TinkerPatchSchemaTask.class), TinkerPatchSchemaTask.class).<init>(v_88);
                    Reference tinkerPatchBuildTask = v_88;
                    Object object = siteArr0[9].callGetProperty((ApkVariant)reference.get());
                    ScriptBytecodeAdapter.setGroovyObjectProperty(object, TinkerPatchPlugin$_apply_closure1$_closure2.class, (TinkerPatchSchemaTask)tinkerPatchBuildTask.get(), (String)"signConfig");
                    v_116 = alloc(Reference);
                    new siteArr0[10].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get()).<init>(v_116);
                    Reference agpProcessManifestTask = v_116;
                    v_126 = alloc(Reference);
                    new siteArr0[11].callConstructor(TinkerManifestAction.class, this.project.get()).<init>(v_126);
                    Reference tinkerManifestAction = v_126;
                    siteArr0[12].call(agpProcessManifestTask.get(), tinkerManifestAction.get());
                    siteArr0[13].call(siteArr0[14].callGetProperty((ApkVariant)reference.get()), new TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(this, this.getThisObject(), this.configuration, reference, tinkerPatchBuildTask, tinkerManifestAction, this.project, agpProcessManifestTask));
                    Object agpProcessResourcesTask = siteArr0[15].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    TinkerResourceIdTask applyResourceTask = (TinkerResourceIdTask)ScriptBytecodeAdapter.castToType(siteArr0[16].call(siteArr0[17].callGetProperty(siteArr0[18].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerProcess", "ResourceId"}), TinkerResourceIdTask.class), TinkerResourceIdTask.class);
                    variant = (ApkVariant)reference.get();
                    ScriptBytecodeAdapter.setGroovyObjectProperty(variant, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"variant");
                    Object objectVar1 = siteArr0[19].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar1, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"applicationId");
                    Object objectVar2 = siteArr0[20].call(Compatibilities.class, this.project.get(), agpProcessResourcesTask);
                    ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar2, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"resDir");
                    siteArr0[21].call(applyResourceTask, agpProcessManifestTask.get());
                    siteArr0[22].call(agpProcessResourcesTask, applyResourceTask);
                    Object agpMergeResourcesTask = siteArr0[23].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    siteArr0[24].call(applyResourceTask, agpMergeResourcesTask);
                    if (! ScriptBytecodeAdapter.compareEqual(siteArr0[25].callGetProperty(tinkerManifestAction.get()), null) || DefaultTypeTransformation.booleanUnbox(siteArr0[26].call(siteArr0[27].callGetProperty(tinkerManifestAction.get()))) ? 0 : 1 != 0) {
                        throw (Throwable)siteArr0[28].callConstructor(GradleException.class, "No manifest output path was found.");
                    }
                    else if (ScriptBytecodeAdapter.compareEqual(siteArr0[29].callGroovyObjectGetProperty(applyResourceTask), null)) {
                        throw (Throwable)siteArr0[30].callConstructor(GradleException.class, "applyResourceTask.resDir is null.");
                    }
                    else {
                        boolean proguardEnable = DefaultTypeTransformation.booleanUnbox(siteArr0[31].callGetProperty(siteArr0[32].callGetProperty(siteArr0[33].call((ApkVariant)reference.get()))));
                        if (proguardEnable) {
                            Object obfuscateTask = siteArr0[34].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            siteArr0[35].call(obfuscateTask, siteArr0[36].callConstructor(TinkerProguardConfigAction.class, (ApkVariant)reference.get()));
                        }
                        boolean multiDexEnabled = DefaultTypeTransformation.booleanUnbox(siteArr0[37].callGetProperty(siteArr0[38].callGetProperty((ApkVariant)reference.get())));
                        if (multiDexEnabled) {
                            TinkerMultidexConfigTask multidexConfigTask = (TinkerMultidexConfigTask)ScriptBytecodeAdapter.castToType(siteArr0[39].call(siteArr0[40].callGetProperty(siteArr0[41].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerProcess", "MultidexKeep"}), TinkerMultidexConfigTask.class), TinkerMultidexConfigTask.class);
                            ApkVariant variantVar1 = (ApkVariant)reference.get();
                            ScriptBytecodeAdapter.setGroovyObjectProperty(variantVar1, TinkerPatchPlugin$_apply_closure1$_closure2.class, multidexConfigTask, (String)"applicationVariant");
                            Object objectVar3 = siteArr0[42].callCurrent(this, (ApkVariant)reference.get());
                            ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar3, TinkerPatchPlugin$_apply_closure1$_closure2.class, multidexConfigTask, (String)"multiDexKeepProguard");
                            siteArr0[43].call(multidexConfigTask, agpProcessResourcesTask);
                            Object agpMultidexTask = siteArr0[44].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            Object agpR8Task = siteArr0[45].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            Object r8Transform;
                            File manifestMultiDexKeepProguard;
                            FileCollection originalFiles;
                            FileCollection replacedFiles;
                            if (BytecodeInterface8.isOrigZ() && TinkerPatchPlugin$_apply_closure1$_closure2.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                                goto 1619;
                                if (ScriptBytecodeAdapter.compareNotEqual(agpMultidexTask, null)) {
                                    siteArr0[66].call(agpMultidexTask, multidexConfigTask);
                                }
                                else {
                                    if (ScriptBytecodeAdapter.compareEqual(agpMultidexTask, null) && ScriptBytecodeAdapter.compareNotEqual(agpR8Task, null) ? 0 : 1 != 0) {
                                        siteArr0[67].call(agpR8Task, multidexConfigTask);
                                        try {
                                            try {
                                                r8Transform = siteArr0[68].call(agpR8Task);
                                                if (DefaultTypeTransformation.booleanUnbox(siteArr0[69].call(siteArr0[70].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"))) {
                                                    manifestMultiDexKeepProguard = (File)ScriptBytecodeAdapter.castToType(siteArr0[71].callCurrent(this, (ApkVariant)reference.get()), File.class);
                                                    if (ScriptBytecodeAdapter.compareNotEqual(manifestMultiDexKeepProguard, null)) {
                                                        originalFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[72].call(siteArr0[73].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"), FileCollection.class);
                                                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[74].call(originalFiles, manifestMultiDexKeepProguard)) ? 0 : 1 != 0) {
                                                            replacedFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[75].call(siteArr0[76].callGroovyObjectGetProperty(this), originalFiles, manifestMultiDexKeepProguard), FileCollection.class);
                                                            siteArr0[77].call(siteArr0[78].callGetProperty(siteArr0[79].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[80].callGetProperty(originalFiles)}, new String[]{"R8Transform original mainDexRulesFiles: ", ""}));
                                                            siteArr0[81].call(siteArr0[82].callGetProperty(siteArr0[83].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[84].callGetProperty(replacedFiles)}, new String[]{"R8Transform replaced mainDexRulesFiles: ", ""}));
                                                            siteArr0[85].callCurrent(this, "com.android.build.gradle.internal.transforms.R8Transform", "mainDexRulesFiles", r8Transform, replacedFiles);
                                                        }
                                                    }
                                                }
                                            }
                                            catch (Exception ignore) {
                                            }
                                        }
                                        finally {
                                            Throwable throwableVar1 = v_519;
                                            throw throwableVar1;
                                        }
                                    }
                                }
                            }
                            else {
                                if (ScriptBytecodeAdapter.compareNotEqual(agpMultidexTask, null)) {
                                    siteArr0[46].call(agpMultidexTask, multidexConfigTask);
                                }
                                else {
                                    if (ScriptBytecodeAdapter.compareEqual(agpMultidexTask, null) && ScriptBytecodeAdapter.compareNotEqual(agpR8Task, null) ? 0 : 1 != 0) {
                                        siteArr0[47].call(agpR8Task, multidexConfigTask);
                                        try {
                                            try {
                                                r8Transform = siteArr0[48].call(agpR8Task);
                                                if (DefaultTypeTransformation.booleanUnbox(siteArr0[49].call(siteArr0[50].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"))) {
                                                    manifestMultiDexKeepProguard = (File)ScriptBytecodeAdapter.castToType(siteArr0[51].callCurrent(this, (ApkVariant)reference.get()), File.class);
                                                    if (ScriptBytecodeAdapter.compareNotEqual(manifestMultiDexKeepProguard, null)) {
                                                        originalFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[52].call(siteArr0[53].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"), FileCollection.class);
                                                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[54].call(originalFiles, manifestMultiDexKeepProguard)) ? 0 : 1 != 0) {
                                                            replacedFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[55].call(siteArr0[56].callGroovyObjectGetProperty(this), originalFiles, manifestMultiDexKeepProguard), FileCollection.class);
                                                            siteArr0[57].call(siteArr0[58].callGetProperty(siteArr0[59].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[60].callGetProperty(originalFiles)}, new String[]{"R8Transform original mainDexRulesFiles: ", ""}));
                                                            siteArr0[61].call(siteArr0[62].callGetProperty(siteArr0[63].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[64].callGetProperty(replacedFiles)}, new String[]{"R8Transform replaced mainDexRulesFiles: ", ""}));
                                                            siteArr0[65].callCurrent(this, "com.android.build.gradle.internal.transforms.R8Transform", "mainDexRulesFiles", r8Transform, replacedFiles);
                                                        }
                                                    }
                                                }
                                            }
                                            catch (Exception ignore) {
                                            }
                                        }
                                        finally {
                                            Throwable throwable = v_681;
                                            throw throwable;
                                        }
                                    }
                                }
                                goto 2062;
                            }
                            Object collectMultiDexComponentsTask = siteArr0[86].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            if (ScriptBytecodeAdapter.compareNotEqual(collectMultiDexComponentsTask, null)) {
                                siteArr0[87].call(multidexConfigTask, collectMultiDexComponentsTask);
                            }
                        }
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[88].callGetProperty(siteArr0[89].callGetProperty(this.configuration.get()))) && DefaultTypeTransformation.booleanUnbox(siteArr0[90].call(FileOperation.class, siteArr0[91].callGetProperty(siteArr0[92].callGetProperty(siteArr0[93].callGroovyObjectGetProperty(this))))) ? 0 : 1 != 0) {
                            return siteArr0[94].call(ImmutableDexTransform.class, siteArr0[95].callGroovyObjectGetProperty(this), (ApkVariant)reference.get());
                        }
                        else {
                            return null;
                        }
                    }
                }
            }

            public Object call(ApkVariant variant) {
                v_1 = alloc(Reference);
                new variant.<init>(v_1);
                Reference reference = v_1;
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return siteArr0[96].callCurrent(this, (ApkVariant)reference.get());
            }

            @Generated
            public Project getProject() {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
            }

            @Generated
            public Object getConfiguration() {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return this.configuration.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "name";
                stringArr0[1] = "capitalize";
                stringArr0[2] = "getInstantRunTask";
                stringArr0[3] = "<$constructor$>";
                stringArr0[4] = "plus";
                stringArr0[5] = "plus";
                stringArr0[6] = "create";
                stringArr0[7] = "tasks";
                stringArr0[8] = "mProject";
                stringArr0[9] = "signingConfig";
                stringArr0[10] = "getProcessManifestTask";
                stringArr0[11] = "<$constructor$>";
                stringArr0[12] = "doLast";
                stringArr0[13] = "each";
                stringArr0[14] = "outputs";
                stringArr0[15] = "getProcessResourcesTask";
                stringArr0[16] = "create";
                stringArr0[17] = "tasks";
                stringArr0[18] = "mProject";
                stringArr0[19] = "getApplicationId";
                stringArr0[20] = "getInputResourcesDirectory";
                stringArr0[21] = "mustRunAfter";
                stringArr0[22] = "dependsOn";
                stringArr0[23] = "getMergeResourcesTask";
                stringArr0[24] = "dependsOn";
                stringArr0[25] = "outputNameToManifestMap";
                stringArr0[26] = "isEmpty";
                stringArr0[27] = "outputNameToManifestMap";
                stringArr0[28] = "<$constructor$>";
                stringArr0[29] = "resDir";
                stringArr0[30] = "<$constructor$>";
                stringArr0[31] = "minifyEnabled";
                stringArr0[32] = "buildType";
                stringArr0[33] = "getBuildType";
                stringArr0[34] = "getObfuscateTask";
                stringArr0[35] = "doFirst";
                stringArr0[36] = "<$constructor$>";
                stringArr0[37] = "multiDexEnabled";
                stringArr0[38] = "mergedFlavor";
                stringArr0[39] = "create";
                stringArr0[40] = "tasks";
                stringArr0[41] = "mProject";
                stringArr0[42] = "getManifestMultiDexKeepProguard";
                stringArr0[43] = "mustRunAfter";
                stringArr0[44] = "getMultiDexTask";
                stringArr0[45] = "getR8Task";
                stringArr0[46] = "dependsOn";
                stringArr0[47] = "dependsOn";
                stringArr0[48] = "getTransform";
                stringArr0[49] = "hasProperty";
                stringArr0[50] = "metaClass";
                stringArr0[51] = "getManifestMultiDexKeepProguard";
                stringArr0[52] = "getProperty";
                stringArr0[53] = "metaClass";
                stringArr0[54] = "contains";
                stringArr0[55] = "files";
                stringArr0[56] = "mProject";
                stringArr0[57] = "error";
                stringArr0[58] = "logger";
                stringArr0[59] = "mProject";
                stringArr0[60] = "files";
                stringArr0[61] = "error";
                stringArr0[62] = "logger";
                stringArr0[63] = "mProject";
                stringArr0[64] = "files";
                stringArr0[65] = "replaceKotlinFinalField";
                stringArr0[66] = "dependsOn";
                stringArr0[67] = "dependsOn";
                stringArr0[68] = "getTransform";
                stringArr0[69] = "hasProperty";
                stringArr0[70] = "metaClass";
                stringArr0[71] = "getManifestMultiDexKeepProguard";
                stringArr0[72] = "getProperty";
                stringArr0[73] = "metaClass";
                stringArr0[74] = "contains";
                stringArr0[75] = "files";
                stringArr0[76] = "mProject";
                stringArr0[77] = "error";
                stringArr0[78] = "logger";
                stringArr0[79] = "mProject";
                stringArr0[80] = "files";
                stringArr0[81] = "error";
                stringArr0[82] = "logger";
                stringArr0[83] = "mProject";
                stringArr0[84] = "files";
                stringArr0[85] = "replaceKotlinFinalField";
                stringArr0[86] = "getCollectMultiDexComponentsTask";
                stringArr0[87] = "mustRunAfter";
                stringArr0[88] = "keepDexApply";
                stringArr0[89] = "buildConfig";
                stringArr0[90] = "isLegalFile";
                stringArr0[91] = "oldApk";
                stringArr0[92] = "tinkerPatch";
                stringArr0[93] = "mProject";
                stringArr0[94] = "inject";
                stringArr0[95] = "mProject";
                stringArr0[96] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerPatchPlugin$_apply_closure1$_closure2.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray.get();
                TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

            // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2$_closure3
            public final class TinkerPatchPlugin$_apply_closure1$_closure2$_closure3 implements GeneratedClosure {
                private synthetic Reference configuration;
                private synthetic Reference variant;
                private synthetic Reference tinkerPatchBuildTask;
                private synthetic Reference tinkerManifestAction;
                private synthetic Reference project;
                private synthetic Reference agpProcessManifestTask;
                private static synthetic ClassInfo $staticClassInfo;
                public static transient synthetic boolean __$stMC;
                private static synthetic SoftReference $callSiteArray;

                public TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(Object _outerInstance, Object _thisObject, Reference configuration, Reference variant, Reference tinkerPatchBuildTask, Reference tinkerManifestAction, Reference project, Reference agpProcessManifestTask) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    configuration.configuration = this;
                    variant.variant = this;
                    tinkerPatchBuildTask.tinkerPatchBuildTask = this;
                    tinkerManifestAction.tinkerManifestAction = this;
                    project.project = this;
                    agpProcessManifestTask.agpProcessManifestTask = this;
                }

                public Object doCall(Object variantOutput) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    siteArr0[0].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    siteArr0[1].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    Object outputName = siteArr0[2].callGetProperty(variantOutput);
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(outputName, "/"))) {
                        Object object = siteArr0[4].call(outputName, Integer.valueOf(0), siteArr0[5].call(siteArr0[6].call(outputName), Integer.valueOf(1)));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(siteArr0[8].callGetProperty(this.tinkerManifestAction.get()), object))) {
                        throw (Throwable)siteArr0[9].callConstructor(GradleException.class, new GStringImpl(new Object[]{object}, new String[]{"Duplicate tinker manifest output name: '", "'"}));
                    }
                    else {
                        Object manifestPath = siteArr0[10].call(Compatibilities.class, this.project.get(), this.agpProcessManifestTask.get(), variantOutput);
                        return siteArr0[11].call(siteArr0[12].callGetProperty(this.tinkerManifestAction.get()), object, manifestPath);
                    }
                }

                @Generated
                public Object getConfiguration() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.configuration.get();
                }

                @Generated
                public ApkVariant getVariant() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
                }

                @Generated
                public TinkerPatchSchemaTask getTinkerPatchBuildTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(this.tinkerPatchBuildTask.get(), TinkerPatchSchemaTask.class);
                }

                @Generated
                public Object getTinkerManifestAction() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.tinkerManifestAction.get();
                }

                @Generated
                public Project getProject() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
                }

                @Generated
                public Object getAgpProcessManifestTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.agpProcessManifestTask.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    else {
                        if (TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo == null) {
                            infoVar1 = ClassInfo.getClassInfo(this.getClass());
                            TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo.getMetaClass();
                    }
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                    stringArr0[0] = "setPatchNewApkPath";
                    stringArr0[1] = "setPatchOutputFolder";
                    stringArr0[2] = "dirName";
                    stringArr0[3] = "endsWith";
                    stringArr0[4] = "substring";
                    stringArr0[5] = "minus";
                    stringArr0[6] = "length";
                    stringArr0[7] = "containsKey";
                    stringArr0[8] = "outputNameToManifestMap";
                    stringArr0[9] = "<$constructor$>";
                    stringArr0[10] = "getOutputManifestPath";
                    stringArr0[11] = "put";
                    stringArr0[12] = "outputNameToManifestMap";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String str0 = new String[]{};
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray_1(str0);
                    return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class, str0);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray.get();
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray = new SoftReference(array);
                    return var_0_0.array;
                }

            }
            // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2$_closure3
            public final class TinkerPatchPlugin$_apply_closure1$_closure2$_closure3 implements GeneratedClosure {
                private synthetic Reference configuration;
                private synthetic Reference variant;
                private synthetic Reference tinkerPatchBuildTask;
                private synthetic Reference tinkerManifestAction;
                private synthetic Reference project;
                private synthetic Reference agpProcessManifestTask;
                private static synthetic ClassInfo $staticClassInfo;
                public static transient synthetic boolean __$stMC;
                private static synthetic SoftReference $callSiteArray;

                public TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(Object _outerInstance, Object _thisObject, Reference configuration, Reference variant, Reference tinkerPatchBuildTask, Reference tinkerManifestAction, Reference project, Reference agpProcessManifestTask) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    configuration.configuration = this;
                    variant.variant = this;
                    tinkerPatchBuildTask.tinkerPatchBuildTask = this;
                    tinkerManifestAction.tinkerManifestAction = this;
                    project.project = this;
                    agpProcessManifestTask.agpProcessManifestTask = this;
                }

                public Object doCall(Object variantOutput) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    siteArr0[0].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    siteArr0[1].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    Object outputName = siteArr0[2].callGetProperty(variantOutput);
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(outputName, "/"))) {
                        Object object = siteArr0[4].call(outputName, Integer.valueOf(0), siteArr0[5].call(siteArr0[6].call(outputName), Integer.valueOf(1)));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(siteArr0[8].callGetProperty(this.tinkerManifestAction.get()), object))) {
                        throw (Throwable)siteArr0[9].callConstructor(GradleException.class, new GStringImpl(new Object[]{object}, new String[]{"Duplicate tinker manifest output name: '", "'"}));
                    }
                    else {
                        Object manifestPath = siteArr0[10].call(Compatibilities.class, this.project.get(), this.agpProcessManifestTask.get(), variantOutput);
                        return siteArr0[11].call(siteArr0[12].callGetProperty(this.tinkerManifestAction.get()), object, manifestPath);
                    }
                }

                @Generated
                public Object getConfiguration() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.configuration.get();
                }

                @Generated
                public ApkVariant getVariant() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
                }

                @Generated
                public TinkerPatchSchemaTask getTinkerPatchBuildTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(this.tinkerPatchBuildTask.get(), TinkerPatchSchemaTask.class);
                }

                @Generated
                public Object getTinkerManifestAction() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.tinkerManifestAction.get();
                }

                @Generated
                public Project getProject() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
                }

                @Generated
                public Object getAgpProcessManifestTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.agpProcessManifestTask.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    else {
                        if (TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo == null) {
                            infoVar1 = ClassInfo.getClassInfo(this.getClass());
                            TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo.getMetaClass();
                    }
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                    stringArr0[0] = "setPatchNewApkPath";
                    stringArr0[1] = "setPatchOutputFolder";
                    stringArr0[2] = "dirName";
                    stringArr0[3] = "endsWith";
                    stringArr0[4] = "substring";
                    stringArr0[5] = "minus";
                    stringArr0[6] = "length";
                    stringArr0[7] = "containsKey";
                    stringArr0[8] = "outputNameToManifestMap";
                    stringArr0[9] = "<$constructor$>";
                    stringArr0[10] = "getOutputManifestPath";
                    stringArr0[11] = "put";
                    stringArr0[12] = "outputNameToManifestMap";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String str0 = new String[]{};
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray_1(str0);
                    return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class, str0);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray.get();
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray = new SoftReference(array);
                    return var_0_0.array;
                }

            }
        }
        // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2
        public final class TinkerPatchPlugin$_apply_closure1$_closure2 implements GeneratedClosure {
            private synthetic Reference project;
            private synthetic Reference configuration;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerPatchPlugin$_apply_closure1$_closure2(Object _outerInstance, Object _thisObject, Reference project, Reference configuration) {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                project.project = this;
                configuration.configuration = this;
            }

            public Object doCall(ApkVariant variant) {
                v_1 = alloc(Reference);
                new variant.<init>(v_1);
                Reference reference = v_1;
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                Object variantName = siteArr0[0].callGetProperty((ApkVariant)reference.get());
                Object capitalizedVariantName = siteArr0[1].call(variantName);
                Object instantRunTask = siteArr0[2].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                if (ScriptBytecodeAdapter.compareNotEqual(instantRunTask, null)) {
                    throw (Throwable)siteArr0[3].callConstructor(GradleException.class, siteArr0[4].call(siteArr0[5].call("Tinker does not support instant run mode, please trigger build", new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{" by assemble", " or disable instant run"})), " in 'File->Settings...'."));
                }
                else {
                    v_88 = alloc(Reference);
                    new (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerPatch", ""}), TinkerPatchSchemaTask.class), TinkerPatchSchemaTask.class).<init>(v_88);
                    Reference tinkerPatchBuildTask = v_88;
                    Object object = siteArr0[9].callGetProperty((ApkVariant)reference.get());
                    ScriptBytecodeAdapter.setGroovyObjectProperty(object, TinkerPatchPlugin$_apply_closure1$_closure2.class, (TinkerPatchSchemaTask)tinkerPatchBuildTask.get(), (String)"signConfig");
                    v_116 = alloc(Reference);
                    new siteArr0[10].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get()).<init>(v_116);
                    Reference agpProcessManifestTask = v_116;
                    v_126 = alloc(Reference);
                    new siteArr0[11].callConstructor(TinkerManifestAction.class, this.project.get()).<init>(v_126);
                    Reference tinkerManifestAction = v_126;
                    siteArr0[12].call(agpProcessManifestTask.get(), tinkerManifestAction.get());
                    siteArr0[13].call(siteArr0[14].callGetProperty((ApkVariant)reference.get()), new TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(this, this.getThisObject(), this.configuration, reference, tinkerPatchBuildTask, tinkerManifestAction, this.project, agpProcessManifestTask));
                    Object agpProcessResourcesTask = siteArr0[15].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    TinkerResourceIdTask applyResourceTask = (TinkerResourceIdTask)ScriptBytecodeAdapter.castToType(siteArr0[16].call(siteArr0[17].callGetProperty(siteArr0[18].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerProcess", "ResourceId"}), TinkerResourceIdTask.class), TinkerResourceIdTask.class);
                    variant = (ApkVariant)reference.get();
                    ScriptBytecodeAdapter.setGroovyObjectProperty(variant, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"variant");
                    Object objectVar1 = siteArr0[19].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar1, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"applicationId");
                    Object objectVar2 = siteArr0[20].call(Compatibilities.class, this.project.get(), agpProcessResourcesTask);
                    ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar2, TinkerPatchPlugin$_apply_closure1$_closure2.class, applyResourceTask, (String)"resDir");
                    siteArr0[21].call(applyResourceTask, agpProcessManifestTask.get());
                    siteArr0[22].call(agpProcessResourcesTask, applyResourceTask);
                    Object agpMergeResourcesTask = siteArr0[23].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                    siteArr0[24].call(applyResourceTask, agpMergeResourcesTask);
                    if (! ScriptBytecodeAdapter.compareEqual(siteArr0[25].callGetProperty(tinkerManifestAction.get()), null) || DefaultTypeTransformation.booleanUnbox(siteArr0[26].call(siteArr0[27].callGetProperty(tinkerManifestAction.get()))) ? 0 : 1 != 0) {
                        throw (Throwable)siteArr0[28].callConstructor(GradleException.class, "No manifest output path was found.");
                    }
                    else if (ScriptBytecodeAdapter.compareEqual(siteArr0[29].callGroovyObjectGetProperty(applyResourceTask), null)) {
                        throw (Throwable)siteArr0[30].callConstructor(GradleException.class, "applyResourceTask.resDir is null.");
                    }
                    else {
                        boolean proguardEnable = DefaultTypeTransformation.booleanUnbox(siteArr0[31].callGetProperty(siteArr0[32].callGetProperty(siteArr0[33].call((ApkVariant)reference.get()))));
                        if (proguardEnable) {
                            Object obfuscateTask = siteArr0[34].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            siteArr0[35].call(obfuscateTask, siteArr0[36].callConstructor(TinkerProguardConfigAction.class, (ApkVariant)reference.get()));
                        }
                        boolean multiDexEnabled = DefaultTypeTransformation.booleanUnbox(siteArr0[37].callGetProperty(siteArr0[38].callGetProperty((ApkVariant)reference.get())));
                        if (multiDexEnabled) {
                            TinkerMultidexConfigTask multidexConfigTask = (TinkerMultidexConfigTask)ScriptBytecodeAdapter.castToType(siteArr0[39].call(siteArr0[40].callGetProperty(siteArr0[41].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerProcess", "MultidexKeep"}), TinkerMultidexConfigTask.class), TinkerMultidexConfigTask.class);
                            ApkVariant variantVar1 = (ApkVariant)reference.get();
                            ScriptBytecodeAdapter.setGroovyObjectProperty(variantVar1, TinkerPatchPlugin$_apply_closure1$_closure2.class, multidexConfigTask, (String)"applicationVariant");
                            Object objectVar3 = siteArr0[42].callCurrent(this, (ApkVariant)reference.get());
                            ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar3, TinkerPatchPlugin$_apply_closure1$_closure2.class, multidexConfigTask, (String)"multiDexKeepProguard");
                            siteArr0[43].call(multidexConfigTask, agpProcessResourcesTask);
                            Object agpMultidexTask = siteArr0[44].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            Object agpR8Task = siteArr0[45].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            Object r8Transform;
                            File manifestMultiDexKeepProguard;
                            FileCollection originalFiles;
                            FileCollection replacedFiles;
                            if (BytecodeInterface8.isOrigZ() && TinkerPatchPlugin$_apply_closure1$_closure2.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                                goto 1619;
                                if (ScriptBytecodeAdapter.compareNotEqual(agpMultidexTask, null)) {
                                    siteArr0[66].call(agpMultidexTask, multidexConfigTask);
                                }
                                else {
                                    if (ScriptBytecodeAdapter.compareEqual(agpMultidexTask, null) && ScriptBytecodeAdapter.compareNotEqual(agpR8Task, null) ? 0 : 1 != 0) {
                                        siteArr0[67].call(agpR8Task, multidexConfigTask);
                                        try {
                                            try {
                                                r8Transform = siteArr0[68].call(agpR8Task);
                                                if (DefaultTypeTransformation.booleanUnbox(siteArr0[69].call(siteArr0[70].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"))) {
                                                    manifestMultiDexKeepProguard = (File)ScriptBytecodeAdapter.castToType(siteArr0[71].callCurrent(this, (ApkVariant)reference.get()), File.class);
                                                    if (ScriptBytecodeAdapter.compareNotEqual(manifestMultiDexKeepProguard, null)) {
                                                        originalFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[72].call(siteArr0[73].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"), FileCollection.class);
                                                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[74].call(originalFiles, manifestMultiDexKeepProguard)) ? 0 : 1 != 0) {
                                                            replacedFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[75].call(siteArr0[76].callGroovyObjectGetProperty(this), originalFiles, manifestMultiDexKeepProguard), FileCollection.class);
                                                            siteArr0[77].call(siteArr0[78].callGetProperty(siteArr0[79].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[80].callGetProperty(originalFiles)}, new String[]{"R8Transform original mainDexRulesFiles: ", ""}));
                                                            siteArr0[81].call(siteArr0[82].callGetProperty(siteArr0[83].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[84].callGetProperty(replacedFiles)}, new String[]{"R8Transform replaced mainDexRulesFiles: ", ""}));
                                                            siteArr0[85].callCurrent(this, "com.android.build.gradle.internal.transforms.R8Transform", "mainDexRulesFiles", r8Transform, replacedFiles);
                                                        }
                                                    }
                                                }
                                            }
                                            catch (Exception ignore) {
                                            }
                                        }
                                        finally {
                                            Throwable throwableVar1 = v_519;
                                            throw throwableVar1;
                                        }
                                    }
                                }
                            }
                            else {
                                if (ScriptBytecodeAdapter.compareNotEqual(agpMultidexTask, null)) {
                                    siteArr0[46].call(agpMultidexTask, multidexConfigTask);
                                }
                                else {
                                    if (ScriptBytecodeAdapter.compareEqual(agpMultidexTask, null) && ScriptBytecodeAdapter.compareNotEqual(agpR8Task, null) ? 0 : 1 != 0) {
                                        siteArr0[47].call(agpR8Task, multidexConfigTask);
                                        try {
                                            try {
                                                r8Transform = siteArr0[48].call(agpR8Task);
                                                if (DefaultTypeTransformation.booleanUnbox(siteArr0[49].call(siteArr0[50].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"))) {
                                                    manifestMultiDexKeepProguard = (File)ScriptBytecodeAdapter.castToType(siteArr0[51].callCurrent(this, (ApkVariant)reference.get()), File.class);
                                                    if (ScriptBytecodeAdapter.compareNotEqual(manifestMultiDexKeepProguard, null)) {
                                                        originalFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[52].call(siteArr0[53].callGetProperty(r8Transform), r8Transform, "mainDexRulesFiles"), FileCollection.class);
                                                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[54].call(originalFiles, manifestMultiDexKeepProguard)) ? 0 : 1 != 0) {
                                                            replacedFiles = (FileCollection)ScriptBytecodeAdapter.castToType(siteArr0[55].call(siteArr0[56].callGroovyObjectGetProperty(this), originalFiles, manifestMultiDexKeepProguard), FileCollection.class);
                                                            siteArr0[57].call(siteArr0[58].callGetProperty(siteArr0[59].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[60].callGetProperty(originalFiles)}, new String[]{"R8Transform original mainDexRulesFiles: ", ""}));
                                                            siteArr0[61].call(siteArr0[62].callGetProperty(siteArr0[63].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[64].callGetProperty(replacedFiles)}, new String[]{"R8Transform replaced mainDexRulesFiles: ", ""}));
                                                            siteArr0[65].callCurrent(this, "com.android.build.gradle.internal.transforms.R8Transform", "mainDexRulesFiles", r8Transform, replacedFiles);
                                                        }
                                                    }
                                                }
                                            }
                                            catch (Exception ignore) {
                                            }
                                        }
                                        finally {
                                            Throwable throwable = v_681;
                                            throw throwable;
                                        }
                                    }
                                }
                                goto 2062;
                            }
                            Object collectMultiDexComponentsTask = siteArr0[86].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get());
                            if (ScriptBytecodeAdapter.compareNotEqual(collectMultiDexComponentsTask, null)) {
                                siteArr0[87].call(multidexConfigTask, collectMultiDexComponentsTask);
                            }
                        }
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[88].callGetProperty(siteArr0[89].callGetProperty(this.configuration.get()))) && DefaultTypeTransformation.booleanUnbox(siteArr0[90].call(FileOperation.class, siteArr0[91].callGetProperty(siteArr0[92].callGetProperty(siteArr0[93].callGroovyObjectGetProperty(this))))) ? 0 : 1 != 0) {
                            return siteArr0[94].call(ImmutableDexTransform.class, siteArr0[95].callGroovyObjectGetProperty(this), (ApkVariant)reference.get());
                        }
                        else {
                            return null;
                        }
                    }
                }
            }

            public Object call(ApkVariant variant) {
                v_1 = alloc(Reference);
                new variant.<init>(v_1);
                Reference reference = v_1;
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return siteArr0[96].callCurrent(this, (ApkVariant)reference.get());
            }

            @Generated
            public Project getProject() {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
            }

            @Generated
            public Object getConfiguration() {
                CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2.$getCallSiteArray();
                return this.configuration.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerPatchPlugin$_apply_closure1$_closure2.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "name";
                stringArr0[1] = "capitalize";
                stringArr0[2] = "getInstantRunTask";
                stringArr0[3] = "<$constructor$>";
                stringArr0[4] = "plus";
                stringArr0[5] = "plus";
                stringArr0[6] = "create";
                stringArr0[7] = "tasks";
                stringArr0[8] = "mProject";
                stringArr0[9] = "signingConfig";
                stringArr0[10] = "getProcessManifestTask";
                stringArr0[11] = "<$constructor$>";
                stringArr0[12] = "doLast";
                stringArr0[13] = "each";
                stringArr0[14] = "outputs";
                stringArr0[15] = "getProcessResourcesTask";
                stringArr0[16] = "create";
                stringArr0[17] = "tasks";
                stringArr0[18] = "mProject";
                stringArr0[19] = "getApplicationId";
                stringArr0[20] = "getInputResourcesDirectory";
                stringArr0[21] = "mustRunAfter";
                stringArr0[22] = "dependsOn";
                stringArr0[23] = "getMergeResourcesTask";
                stringArr0[24] = "dependsOn";
                stringArr0[25] = "outputNameToManifestMap";
                stringArr0[26] = "isEmpty";
                stringArr0[27] = "outputNameToManifestMap";
                stringArr0[28] = "<$constructor$>";
                stringArr0[29] = "resDir";
                stringArr0[30] = "<$constructor$>";
                stringArr0[31] = "minifyEnabled";
                stringArr0[32] = "buildType";
                stringArr0[33] = "getBuildType";
                stringArr0[34] = "getObfuscateTask";
                stringArr0[35] = "doFirst";
                stringArr0[36] = "<$constructor$>";
                stringArr0[37] = "multiDexEnabled";
                stringArr0[38] = "mergedFlavor";
                stringArr0[39] = "create";
                stringArr0[40] = "tasks";
                stringArr0[41] = "mProject";
                stringArr0[42] = "getManifestMultiDexKeepProguard";
                stringArr0[43] = "mustRunAfter";
                stringArr0[44] = "getMultiDexTask";
                stringArr0[45] = "getR8Task";
                stringArr0[46] = "dependsOn";
                stringArr0[47] = "dependsOn";
                stringArr0[48] = "getTransform";
                stringArr0[49] = "hasProperty";
                stringArr0[50] = "metaClass";
                stringArr0[51] = "getManifestMultiDexKeepProguard";
                stringArr0[52] = "getProperty";
                stringArr0[53] = "metaClass";
                stringArr0[54] = "contains";
                stringArr0[55] = "files";
                stringArr0[56] = "mProject";
                stringArr0[57] = "error";
                stringArr0[58] = "logger";
                stringArr0[59] = "mProject";
                stringArr0[60] = "files";
                stringArr0[61] = "error";
                stringArr0[62] = "logger";
                stringArr0[63] = "mProject";
                stringArr0[64] = "files";
                stringArr0[65] = "replaceKotlinFinalField";
                stringArr0[66] = "dependsOn";
                stringArr0[67] = "dependsOn";
                stringArr0[68] = "getTransform";
                stringArr0[69] = "hasProperty";
                stringArr0[70] = "metaClass";
                stringArr0[71] = "getManifestMultiDexKeepProguard";
                stringArr0[72] = "getProperty";
                stringArr0[73] = "metaClass";
                stringArr0[74] = "contains";
                stringArr0[75] = "files";
                stringArr0[76] = "mProject";
                stringArr0[77] = "error";
                stringArr0[78] = "logger";
                stringArr0[79] = "mProject";
                stringArr0[80] = "files";
                stringArr0[81] = "error";
                stringArr0[82] = "logger";
                stringArr0[83] = "mProject";
                stringArr0[84] = "files";
                stringArr0[85] = "replaceKotlinFinalField";
                stringArr0[86] = "getCollectMultiDexComponentsTask";
                stringArr0[87] = "mustRunAfter";
                stringArr0[88] = "keepDexApply";
                stringArr0[89] = "buildConfig";
                stringArr0[90] = "isLegalFile";
                stringArr0[91] = "oldApk";
                stringArr0[92] = "tinkerPatch";
                stringArr0[93] = "mProject";
                stringArr0[94] = "inject";
                stringArr0[95] = "mProject";
                stringArr0[96] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerPatchPlugin$_apply_closure1$_closure2.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray.get();
                TinkerPatchPlugin$_apply_closure1$_closure2.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

            // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2$_closure3
            public final class TinkerPatchPlugin$_apply_closure1$_closure2$_closure3 implements GeneratedClosure {
                private synthetic Reference configuration;
                private synthetic Reference variant;
                private synthetic Reference tinkerPatchBuildTask;
                private synthetic Reference tinkerManifestAction;
                private synthetic Reference project;
                private synthetic Reference agpProcessManifestTask;
                private static synthetic ClassInfo $staticClassInfo;
                public static transient synthetic boolean __$stMC;
                private static synthetic SoftReference $callSiteArray;

                public TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(Object _outerInstance, Object _thisObject, Reference configuration, Reference variant, Reference tinkerPatchBuildTask, Reference tinkerManifestAction, Reference project, Reference agpProcessManifestTask) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    configuration.configuration = this;
                    variant.variant = this;
                    tinkerPatchBuildTask.tinkerPatchBuildTask = this;
                    tinkerManifestAction.tinkerManifestAction = this;
                    project.project = this;
                    agpProcessManifestTask.agpProcessManifestTask = this;
                }

                public Object doCall(Object variantOutput) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    siteArr0[0].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    siteArr0[1].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    Object outputName = siteArr0[2].callGetProperty(variantOutput);
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(outputName, "/"))) {
                        Object object = siteArr0[4].call(outputName, Integer.valueOf(0), siteArr0[5].call(siteArr0[6].call(outputName), Integer.valueOf(1)));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(siteArr0[8].callGetProperty(this.tinkerManifestAction.get()), object))) {
                        throw (Throwable)siteArr0[9].callConstructor(GradleException.class, new GStringImpl(new Object[]{object}, new String[]{"Duplicate tinker manifest output name: '", "'"}));
                    }
                    else {
                        Object manifestPath = siteArr0[10].call(Compatibilities.class, this.project.get(), this.agpProcessManifestTask.get(), variantOutput);
                        return siteArr0[11].call(siteArr0[12].callGetProperty(this.tinkerManifestAction.get()), object, manifestPath);
                    }
                }

                @Generated
                public Object getConfiguration() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.configuration.get();
                }

                @Generated
                public ApkVariant getVariant() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
                }

                @Generated
                public TinkerPatchSchemaTask getTinkerPatchBuildTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(this.tinkerPatchBuildTask.get(), TinkerPatchSchemaTask.class);
                }

                @Generated
                public Object getTinkerManifestAction() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.tinkerManifestAction.get();
                }

                @Generated
                public Project getProject() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
                }

                @Generated
                public Object getAgpProcessManifestTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.agpProcessManifestTask.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    else {
                        if (TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo == null) {
                            infoVar1 = ClassInfo.getClassInfo(this.getClass());
                            TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo.getMetaClass();
                    }
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                    stringArr0[0] = "setPatchNewApkPath";
                    stringArr0[1] = "setPatchOutputFolder";
                    stringArr0[2] = "dirName";
                    stringArr0[3] = "endsWith";
                    stringArr0[4] = "substring";
                    stringArr0[5] = "minus";
                    stringArr0[6] = "length";
                    stringArr0[7] = "containsKey";
                    stringArr0[8] = "outputNameToManifestMap";
                    stringArr0[9] = "<$constructor$>";
                    stringArr0[10] = "getOutputManifestPath";
                    stringArr0[11] = "put";
                    stringArr0[12] = "outputNameToManifestMap";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String str0 = new String[]{};
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray_1(str0);
                    return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class, str0);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray.get();
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray = new SoftReference(array);
                    return var_0_0.array;
                }

            }
            // class: com/tencent/tinker/build/gradle/TinkerPatchPlugin$_apply_closure1$_closure2$_closure3
            public final class TinkerPatchPlugin$_apply_closure1$_closure2$_closure3 implements GeneratedClosure {
                private synthetic Reference configuration;
                private synthetic Reference variant;
                private synthetic Reference tinkerPatchBuildTask;
                private synthetic Reference tinkerManifestAction;
                private synthetic Reference project;
                private synthetic Reference agpProcessManifestTask;
                private static synthetic ClassInfo $staticClassInfo;
                public static transient synthetic boolean __$stMC;
                private static synthetic SoftReference $callSiteArray;

                public TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(Object _outerInstance, Object _thisObject, Reference configuration, Reference variant, Reference tinkerPatchBuildTask, Reference tinkerManifestAction, Reference project, Reference agpProcessManifestTask) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    super(_outerInstance, _thisObject);
                    configuration.configuration = this;
                    variant.variant = this;
                    tinkerPatchBuildTask.tinkerPatchBuildTask = this;
                    tinkerManifestAction.tinkerManifestAction = this;
                    project.project = this;
                    agpProcessManifestTask.agpProcessManifestTask = this;
                }

                public Object doCall(Object variantOutput) {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    siteArr0[0].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    siteArr0[1].callCurrent(this, this.configuration.get(), variantOutput, this.variant.get(), this.tinkerPatchBuildTask.get());
                    Object outputName = siteArr0[2].callGetProperty(variantOutput);
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(outputName, "/"))) {
                        Object object = siteArr0[4].call(outputName, Integer.valueOf(0), siteArr0[5].call(siteArr0[6].call(outputName), Integer.valueOf(1)));
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(siteArr0[8].callGetProperty(this.tinkerManifestAction.get()), object))) {
                        throw (Throwable)siteArr0[9].callConstructor(GradleException.class, new GStringImpl(new Object[]{object}, new String[]{"Duplicate tinker manifest output name: '", "'"}));
                    }
                    else {
                        Object manifestPath = siteArr0[10].call(Compatibilities.class, this.project.get(), this.agpProcessManifestTask.get(), variantOutput);
                        return siteArr0[11].call(siteArr0[12].callGetProperty(this.tinkerManifestAction.get()), object, manifestPath);
                    }
                }

                @Generated
                public Object getConfiguration() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.configuration.get();
                }

                @Generated
                public ApkVariant getVariant() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
                }

                @Generated
                public TinkerPatchSchemaTask getTinkerPatchBuildTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(this.tinkerPatchBuildTask.get(), TinkerPatchSchemaTask.class);
                }

                @Generated
                public Object getTinkerManifestAction() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.tinkerManifestAction.get();
                }

                @Generated
                public Project getProject() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
                }

                @Generated
                public Object getAgpProcessManifestTask() {
                    CallSite[] siteArr0 = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$getCallSiteArray();
                    return this.agpProcessManifestTask.get();
                }

                protected /* synthetic */ MetaClass $getStaticMetaClass() {
                    if (this.getClass() != TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class) {
                        return ScriptBytecodeAdapter.initMetaClass(this);
                    }
                    else {
                        if (TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo == null) {
                            infoVar1 = ClassInfo.getClassInfo(this.getClass());
                            TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                        }
                        return TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$staticClassInfo.getMetaClass();
                    }
                }

                private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                    stringArr0[0] = "setPatchNewApkPath";
                    stringArr0[1] = "setPatchOutputFolder";
                    stringArr0[2] = "dirName";
                    stringArr0[3] = "endsWith";
                    stringArr0[4] = "substring";
                    stringArr0[5] = "minus";
                    stringArr0[6] = "length";
                    stringArr0[7] = "containsKey";
                    stringArr0[8] = "outputNameToManifestMap";
                    stringArr0[9] = "<$constructor$>";
                    stringArr0[10] = "getOutputManifestPath";
                    stringArr0[11] = "put";
                    stringArr0[12] = "outputNameToManifestMap";
                }

                private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                    String str0 = new String[]{};
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray_1(str0);
                    return new CallSiteArray(TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.class, str0);
                }

                private static /* synthetic */ CallSite[] $getCallSiteArray() {
                    CallSiteArray array = TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray != null ? TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray.get();
                    TinkerPatchPlugin$_apply_closure1$_closure2$_closure3.$callSiteArray = new SoftReference(array);
                    return var_0_0.array;
                }

            }
        }
    }
}
