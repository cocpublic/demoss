/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle;

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
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import com.android.build.gradle.AppExtension;
import com.tencent.thinflutter.plugin.ThinFlutterExtension;

// class: com/tencent/rfix/build/gradle/RFixPatchPlugin
public class RFixPatchPlugin implements Plugin<Project>, GroovyObject {
    final private static String GROUP;
    final private static String PROP_DISABLE_REDIRECT;
    final private static String PROP_DISABLE_THIN_FLUTTER;
    final protected static Object DEFAULT_LOADER_LIST;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public RFixPatchPlugin() {
        CallSite[] siteArr0 = RFixPatchPlugin.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public void apply(Project project) {
        CallSite[] siteArr0 = RFixPatchPlugin.$getCallSiteArray();
        siteArr0[0].call(siteArr0[1].callGetProperty(project), "RFixPatch", RFixPatchExtension.class, project);
        siteArr0[2].callCurrent(this, project);
        siteArr0[3].callCurrent(this, project);
        siteArr0[4].callCurrent(this, project);
        siteArr0[5].callCurrent(this, project);
    }

    protected void initAndroidConfig(Project project) {
        CallSite[] siteArr0 = RFixPatchPlugin.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[6].call(siteArr0[7].callGetProperty(project), "com.android.application")) ? 0 : 1 != 0) {
            siteArr0[8].call(siteArr0[9].callGetProperty(project), "RFixPatchPlugin init android config fail, no application plugin!");
            return;
        }
        else {
            try {
                try {
                    siteArr0[10].call(project, ScriptBytecodeAdapter.createMap(new Object[]{"plugin", "osdetector"}));
                }
                catch (Throwable e) {
                    siteArr0[11].call(project, ScriptBytecodeAdapter.createMap(new Object[]{"plugin", "com.google.osdetector"}));
                }
            }
            finally {
                Throwable throwable = v_36;
                throw throwable;
            }
            Object android = siteArr0[12].callGetProperty(siteArr0[13].callGetProperty(project));
            try {
                try {
                    Object defaultPatchId = siteArr0[14].call(siteArr0[15].callConstructor(Date.class), "yyyyMMddHHmmssSSS");
                    siteArr0[16].call(siteArr0[17].callGetProperty(android), "String", siteArr0[18].callGetProperty(RFixConstants.class), new GStringImpl(new Object[]{defaultPatchId}, new String[]{""", """}));
                    siteArr0[19].call(siteArr0[20].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[21].callGetProperty(RFixConstants.class), defaultPatchId}, new String[]{"rfix add buildConfigField ", "=", ""}));
                    int i0 = false;
                    ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i0), null, siteArr0[22].callGetProperty(android), (String)"preDexLibraries");
                    int i1 = true;
                    ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i1), null, siteArr0[23].callGetProperty(android), (String)"jumboMode");
                    siteArr0[24].callCurrent(this, project);
                    int i2 = false;
                    ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i2), null, siteArr0[25].callGetProperty(android), (String)"keepRuntimeAnnotatedClasses");
                }
                catch (Throwable thr) {
                    siteArr0[26].call(siteArr0[27].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[28].call(thr)}, new String[]{"initAndroidConfig error: ", "."}));
                }
            }
            finally {
                Throwable throwableVar1 = v_155;
                throw throwableVar1;
            }
        }
    }

    protected void disableDexArchive(Project project) {
        CallSite[] siteArr0 = RFixPatchPlugin.$getCallSiteArray();
        try {
            try {
                siteArr0[29].callGetProperty(BooleanOption.class);
                Class booleanOptClazz = Class.forName("com.android.build.gradle.options.BooleanOption");
                Object enableDexArchiveField = siteArr0[30].call(booleanOptClazz, "ENABLE_DEX_ARCHIVE");
                siteArr0[31].call(enableDexArchiveField, Boolean.valueOf(true));
                Object enableDexArchiveEnumObj = siteArr0[32].call(enableDexArchiveField, null);
                Object defValField = siteArr0[33].call(siteArr0[34].call(enableDexArchiveEnumObj), "defaultValue");
                siteArr0[35].call(defValField, Boolean.valueOf(true));
                siteArr0[36].call(defValField, enableDexArchiveEnumObj, Boolean.valueOf(false));
            }
            catch (Throwable thr) {
                if ((thr instanceof ClassNotFoundException) ? 0 : 1 != 0) {
                    siteArr0[37].call(siteArr0[38].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[39].call(thr)}, new String[]{"reflectDexArchiveFlag error: ", "."}));
                }
            }
            return;
        }
        finally {
            Throwable throwable = v_57;
            throw throwable;
        }
    }

    protected void initRFixPluginTask(Project project) {
        v_1 = alloc(Reference);
        new project.<init>(v_1);
        Reference reference = v_1;
        CallSite[] siteArr0 = RFixPatchPlugin.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[40].call(siteArr0[41].callGetProperty((Project)reference.get()), "com.android.application")) ? 0 : 1 != 0) {
            siteArr0[42].call(siteArr0[43].callGetProperty((Project)reference.get()), "RFixPatchPlugin init RFix plugin fail, no application plugin!");
        }
        else {
            siteArr0[44].call((Project)reference.get(), new RFixPatchPlugin$_initRFixPluginTask_closure1(this, this, reference));
        }
    }

    protected void initRedirectTransformer(Project project) {
        CallSite[] siteArr0 = RFixPatchPlugin.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[45].call(siteArr0[46].call(FeatureManager.class))) ? 0 : 1 != 0) {
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[47].call(project, RFixPatchPlugin.PROP_DISABLE_REDIRECT))) {
                Object disableRedirect = siteArr0[48].call(project, RFixPatchPlugin.PROP_DISABLE_REDIRECT);
                siteArr0[49].call(siteArr0[50].callGetProperty(project), new GStringImpl(new Object[]{disableRedirect}, new String[]{"init Redirect Transformer. disableRedirect=", ""}));
                if (ScriptBytecodeAdapter.compareEqual(disableRedirect, "true")) {
                }
            }
            Object engine = siteArr0[51].call(siteArr0[52].call(EngineManager.class), siteArr0[53].callGetProperty(RFixConstants.class));
            if (ScriptBytecodeAdapter.compareNotEqual(engine, null)) {
                AppExtension appExtension = (AppExtension)ScriptBytecodeAdapter.castToType(siteArr0[54].call(siteArr0[55].call(project), AppExtension.class), AppExtension.class);
                siteArr0[56].call(appExtension, siteArr0[57].call(engine, project));
            }
        }
    }

    protected void initThinFlutterPluginConfig(Project project) {
        v_1 = alloc(Reference);
        new project.<init>(v_1);
        Reference reference = v_1;
        CallSite[] siteArr0 = RFixPatchPlugin.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[58].call(siteArr0[59].call(FeatureManager.class))) ? 0 : 1 != 0) {
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[60].call((Project)reference.get(), RFixPatchPlugin.PROP_DISABLE_THIN_FLUTTER))) {
                Object disableFlutterSupport = siteArr0[61].call((Project)reference.get(), RFixPatchPlugin.PROP_DISABLE_THIN_FLUTTER);
                siteArr0[62].call(siteArr0[63].callGetProperty((Project)reference.get()), new GStringImpl(new Object[]{disableFlutterSupport}, new String[]{"init ThinFlutter Plugin. disableThinFlutter=", ""}));
                if (ScriptBytecodeAdapter.compareEqual(disableFlutterSupport, "true")) {
                }
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[64].call(siteArr0[65].callGetProperty((Project)reference.get()), "com.android.application")) ? 0 : 1 != 0) {
                siteArr0[66].call(siteArr0[67].callGetProperty((Project)reference.get()), "init ThinFlutter plugin fail, no application plugin!");
            }
            else {
                siteArr0[68].call((Project)reference.get(), new RFixPatchPlugin$_initThinFlutterPluginConfig_closure2(this, this, reference));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[69].call(siteArr0[70].callGetProperty((Project)reference.get()), "com.tencent.thinflutter")) ? 0 : 1 != 0) {
                    siteArr0[71].call((Project)reference.get(), ScriptBytecodeAdapter.createMap(new Object[]{"plugin", "com.tencent.thinflutter"}));
                }
            }
        }
    }

    private void initRFixConfig(Project project) {
        CallSite[] siteArr0 = RFixPatchPlugin.$getCallSiteArray();
        RFixPatchExtension patchExtension = (RFixPatchExtension)ScriptBytecodeAdapter.asType(siteArr0[72].callGetProperty(siteArr0[73].callGetProperty(project)), RFixPatchExtension.class);
        Object loader = siteArr0[74].callGetProperty(siteArr0[75].callGetProperty(patchExtension));
        Object item = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[76].call(RFixPatchPlugin.DEFAULT_LOADER_LIST), Iterator.class);
        while (iterator.hasNext()) {
            item = iterator.next();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[77].call(loader, item)) ? 0 : 1 != 0) {
                siteArr0[78].call(loader, item);
                siteArr0[79].call(siteArr0[80].callGetProperty(project), new GStringImpl(new Object[]{item}, new String[]{"rfix add ", " to dex loader pattern"}));
            }
        }
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[81].call(siteArr0[82].callGetProperty(siteArr0[83].callGetProperty(patchExtension))))) {
            List list = ScriptBytecodeAdapter.createList(new Object[]{"classes*.dex", "assets/secondary-dex-?.jar"});
            ScriptBytecodeAdapter.setProperty(list, null, siteArr0[84].callGetProperty(patchExtension), (String)"pattern");
        }
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[85].call(siteArr0[86].callGetProperty(siteArr0[87].callGetProperty(patchExtension))))) {
            List listVar1 = ScriptBytecodeAdapter.createList(new Object[]{"lib/*/*.so"});
            ScriptBytecodeAdapter.setProperty(listVar1, null, siteArr0[88].callGetProperty(patchExtension), (String)"pattern");
        }
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[89].call(siteArr0[90].callGetProperty(siteArr0[91].callGetProperty(patchExtension))))) {
            List listVar2 = ScriptBytecodeAdapter.createList(new Object[]{"r/*", "res/*", "assets/*", "resources.arsc", "AndroidManifest.xml"});
            ScriptBytecodeAdapter.setProperty(listVar2, null, siteArr0[92].callGetProperty(patchExtension), (String)"pattern");
        }
        if (BytecodeInterface8.isOrigZ() && RFixPatchPlugin.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 591;
            if (ScriptBytecodeAdapter.compareEqual(siteArr0[98].callGetProperty(siteArr0[99].callGetProperty(patchExtension)), null) && ScriptBytecodeAdapter.compareEqual(siteArr0[100].callGetProperty(siteArr0[101].callGetProperty(patchExtension)), null) ? 0 : 1 != 0) {
                String str1 = "com.tencent.mm:SevenZip:1.1.10";
                ScriptBytecodeAdapter.setProperty(str1, null, siteArr0[102].callGetProperty(patchExtension), (String)"zipArtifact");
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareEqual(siteArr0[93].callGetProperty(siteArr0[94].callGetProperty(patchExtension)), null) && ScriptBytecodeAdapter.compareEqual(siteArr0[95].callGetProperty(siteArr0[96].callGetProperty(patchExtension)), null) ? 0 : 1 != 0) {
                String str0 = "com.tencent.mm:SevenZip:1.1.10";
                ScriptBytecodeAdapter.setProperty(str0, null, siteArr0[97].callGetProperty(patchExtension), (String)"zipArtifact");
            }
        }
        Object excludeClasses = siteArr0[103].callGetProperty(siteArr0[104].callGetProperty(patchExtension));
        item = null;
        Iterator iteratorVar1 = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[105].call(RFixPatchPlugin.DEFAULT_LOADER_LIST), Iterator.class);
        while (iteratorVar1.hasNext()) {
            item = iteratorVar1.next();
            Object transformed = siteArr0[106].call(siteArr0[107].call(item, ".", "/"), "*", ".*");
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[108].call(excludeClasses, transformed)) ? 0 : 1 != 0) {
                siteArr0[109].call(excludeClasses, transformed);
            }
        }
    }

    private void initAutoVerifyConfig(Project project, RFixPatchExtension fixPatch) {
        CallSite[] siteArr0 = RFixPatchPlugin.$getCallSiteArray();
        Object verifyCase = siteArr0[110].callGetProperty(siteArr0[111].callGroovyObjectGetProperty(fixPatch));
        Object version = null;
        Object dep;
        if (BytecodeInterface8.isOrigZ() && RFixPatchPlugin.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 272;
            dep = null;
            Iterator iteratorVar1 = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[121].call(siteArr0[122].callGetProperty(siteArr0[123].call(siteArr0[124].callGetProperty(project), "implementation"))), Iterator.class);
            while (iteratorVar1.hasNext()) {
                dep = iteratorVar1.next();
                ScriptBytecodeAdapter.compareEqual(siteArr0[125].callGetProperty(dep), "com.tencent.rfix");
                ! ScriptBytecodeAdapter.compareEqual(siteArr0[126].callGetProperty(dep), "RFix-android-lib") || ScriptBytecodeAdapter.compareEqual(siteArr0[127].callGetProperty(dep), "RFix-android-lib-lite") ? 0 : 1 != 0 || ScriptBytecodeAdapter.compareEqual(siteArr0[128].callGetProperty(dep), "RFix-android-lib-no-op") ? 0 : 1 != 0 ? 0 : 1 != 0;
                Object objectVar1 = siteArr0[129].callGetProperty(dep);
                version = objectVar1;
                break;;
            }
        }
        else {
            dep = null;
            Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[112].call(siteArr0[113].callGetProperty(siteArr0[114].call(siteArr0[115].callGetProperty(project), "implementation"))), Iterator.class);
            while (iterator.hasNext()) {
                dep = iterator.next();
                if (ScriptBytecodeAdapter.compareEqual(siteArr0[116].callGetProperty(dep), "com.tencent.rfix")) {
                }
                if (! ScriptBytecodeAdapter.compareEqual(siteArr0[117].callGetProperty(dep), "RFix-android-lib") || ScriptBytecodeAdapter.compareEqual(siteArr0[118].callGetProperty(dep), "RFix-android-lib-lite") ? 0 : 1 != 0 || ScriptBytecodeAdapter.compareEqual(siteArr0[119].callGetProperty(dep), "RFix-android-lib-no-op") ? 0 : 1 != 0 ? 0 : 1 != 0) {
                    Object object = siteArr0[120].callGetProperty(dep);
                    break;;
                }
                else {
                    continue;;
                }
            }
            goto 484;
        }
        siteArr0[130].callCurrent(this, new GStringImpl(new Object[]{verifyCase, object}, new String[]{"initAutoVerifyConfig verifyCase=", " version=", ""}));
        if (ScriptBytecodeAdapter.compareEqual(verifyCase, siteArr0[131].callGetProperty(RFixConstants.class))) {
            siteArr0[132].call(siteArr0[133].callGetProperty(project), "implementation", siteArr0[134].call("com.tencent.rfix:RFix-verifycase-base:", object));
        }
        else {
            if (ScriptBytecodeAdapter.compareEqual(verifyCase, siteArr0[135].callGetProperty(RFixConstants.class))) {
                siteArr0[136].call(siteArr0[137].callGetProperty(project), "implementation", siteArr0[138].call("com.tencent.rfix:RFix-verifycase-patch:", object));
            }
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixPatchPlugin.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixPatchPlugin.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixPatchPlugin.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixPatchPlugin.$staticClassInfo.getMetaClass();
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
        List list = ScriptBytecodeAdapter.createList(new Object[]{"com.tencent.rfix.loader.*", "com.tencent.tinker.loader.*", "com.tencent.mobileqq.qfix.*"});
        RFixPatchPlugin.DEFAULT_LOADER_LIST = list;
    }

    @Generated
    public static String getGROUP() {
        return RFixPatchPlugin.GROUP;
    }

    @Generated
    public /* synthetic */ void apply(Object object) {
        this.apply((Project)object);
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "create";
        stringArr0[1] = "extensions";
        stringArr0[2] = "initAndroidConfig";
        stringArr0[3] = "initRFixPluginTask";
        stringArr0[4] = "initRedirectTransformer";
        stringArr0[5] = "initThinFlutterPluginConfig";
        stringArr0[6] = "hasPlugin";
        stringArr0[7] = "plugins";
        stringArr0[8] = "warn";
        stringArr0[9] = "logger";
        stringArr0[10] = "apply";
        stringArr0[11] = "apply";
        stringArr0[12] = "android";
        stringArr0[13] = "extensions";
        stringArr0[14] = "format";
        stringArr0[15] = "<$constructor$>";
        stringArr0[16] = "buildConfigField";
        stringArr0[17] = "defaultConfig";
        stringArr0[18] = "DEFAULT_PATCH_ID";
        stringArr0[19] = "warn";
        stringArr0[20] = "logger";
        stringArr0[21] = "DEFAULT_PATCH_ID";
        stringArr0[22] = "dexOptions";
        stringArr0[23] = "dexOptions";
        stringArr0[24] = "disableDexArchive";
        stringArr0[25] = "dexOptions";
        stringArr0[26] = "error";
        stringArr0[27] = "logger";
        stringArr0[28] = "getMessage";
        stringArr0[29] = "ENABLE_DEX_ARCHIVE";
        stringArr0[30] = "getDeclaredField";
        stringArr0[31] = "setAccessible";
        stringArr0[32] = "get";
        stringArr0[33] = "getDeclaredField";
        stringArr0[34] = "getClass";
        stringArr0[35] = "setAccessible";
        stringArr0[36] = "set";
        stringArr0[37] = "error";
        stringArr0[38] = "logger";
        stringArr0[39] = "getMessage";
        stringArr0[40] = "hasPlugin";
        stringArr0[41] = "plugins";
        stringArr0[42] = "warn";
        stringArr0[43] = "logger";
        stringArr0[44] = "afterEvaluate";
        stringArr0[45] = "redirectSupport";
        stringArr0[46] = "getInstance";
        stringArr0[47] = "hasProperty";
        stringArr0[48] = "property";
        stringArr0[49] = "warn";
        stringArr0[50] = "logger";
        stringArr0[51] = "getEngine";
        stringArr0[52] = "getInstance";
        stringArr0[53] = "PATCH_TYPE_REDIRECT";
        stringArr0[54] = "getByType";
        stringArr0[55] = "getExtensions";
        stringArr0[56] = "registerTransform";
        stringArr0[57] = "createTransform";
        stringArr0[58] = "flutterSupport";
        stringArr0[59] = "getInstance";
        stringArr0[60] = "hasProperty";
        stringArr0[61] = "property";
        stringArr0[62] = "warn";
        stringArr0[63] = "logger";
        stringArr0[64] = "hasPlugin";
        stringArr0[65] = "plugins";
        stringArr0[66] = "warn";
        stringArr0[67] = "logger";
        stringArr0[68] = "afterEvaluate";
        stringArr0[69] = "hasPlugin";
        stringArr0[70] = "plugins";
        stringArr0[71] = "apply";
        stringArr0[72] = "RFixPatch";
        stringArr0[73] = "extensions";
        stringArr0[74] = "loader";
        stringArr0[75] = "dex";
        stringArr0[76] = "iterator";
        stringArr0[77] = "contains";
        stringArr0[78] = "add";
        stringArr0[79] = "warn";
        stringArr0[80] = "logger";
        stringArr0[81] = "isEmpty";
        stringArr0[82] = "pattern";
        stringArr0[83] = "dex";
        stringArr0[84] = "dex";
        stringArr0[85] = "isEmpty";
        stringArr0[86] = "pattern";
        stringArr0[87] = "lib";
        stringArr0[88] = "lib";
        stringArr0[89] = "isEmpty";
        stringArr0[90] = "pattern";
        stringArr0[91] = "res";
        stringArr0[92] = "res";
        stringArr0[93] = "path";
        stringArr0[94] = "sevenZip";
        stringArr0[95] = "zipArtifact";
        stringArr0[96] = "sevenZip";
        stringArr0[97] = "sevenZip";
        stringArr0[98] = "path";
        stringArr0[99] = "sevenZip";
        stringArr0[100] = "zipArtifact";
        stringArr0[101] = "sevenZip";
        stringArr0[102] = "sevenZip";
        stringArr0[103] = "excludeClasses";
        stringArr0[104] = "redirectConfig";
        stringArr0[105] = "iterator";
        stringArr0[106] = "replace";
        stringArr0[107] = "replace";
        stringArr0[108] = "contains";
        stringArr0[109] = "add";
        stringArr0[110] = "verifyCase";
        stringArr0[111] = "autoVerifyConfig";
        stringArr0[112] = "iterator";
        stringArr0[113] = "dependencies";
        stringArr0[114] = "getByName";
        stringArr0[115] = "configurations";
        stringArr0[116] = "group";
        stringArr0[117] = "name";
        stringArr0[118] = "name";
        stringArr0[119] = "name";
        stringArr0[120] = "version";
        stringArr0[121] = "iterator";
        stringArr0[122] = "dependencies";
        stringArr0[123] = "getByName";
        stringArr0[124] = "configurations";
        stringArr0[125] = "group";
        stringArr0[126] = "name";
        stringArr0[127] = "name";
        stringArr0[128] = "name";
        stringArr0[129] = "version";
        stringArr0[130] = "println";
        stringArr0[131] = "VERIFY_CASE_DEP_BASE";
        stringArr0[132] = "add";
        stringArr0[133] = "dependencies";
        stringArr0[134] = "plus";
        stringArr0[135] = "VERIFY_CASE_DEP_PATCH";
        stringArr0[136] = "add";
        stringArr0[137] = "dependencies";
        stringArr0[138] = "plus";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixPatchPlugin.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixPatchPlugin.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixPatchPlugin.$callSiteArray != null ? RFixPatchPlugin.$createCallSiteArray() : (CallSiteArray)RFixPatchPlugin.$callSiteArray.get();
        RFixPatchPlugin.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/rfix/build/gradle/RFixPatchPlugin$_initRFixPluginTask_closure1
    public final class RFixPatchPlugin$_initRFixPluginTask_closure1 implements GeneratedClosure {
        private synthetic Reference project;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPlugin$_initRFixPluginTask_closure1(Object _outerInstance, Object _thisObject, Reference project) {
            CallSite[] siteArr0 = RFixPatchPlugin$_initRFixPluginTask_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            project.project = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = RFixPatchPlugin$_initRFixPluginTask_closure1.$getCallSiteArray();
            Object supportEngine = siteArr0[0].call(siteArr0[1].call(EngineManager.class));
            siteArr0[2].call(siteArr0[3].callGetProperty(this.project.get()), new GStringImpl(new Object[]{supportEngine}, new String[]{"RFixPatchPlugin support patch type: ", ""}));
            siteArr0[4].callCurrent(this, this.project.get());
            RFixPatchExtension fixPatch = (RFixPatchExtension)ScriptBytecodeAdapter.asType(siteArr0[5].callGetProperty(siteArr0[6].callGetProperty(this.project.get())), RFixPatchExtension.class);
            siteArr0[7].call(siteArr0[8].callGetProperty(this.project.get()), new GStringImpl(new Object[]{siteArr0[9].callGetProperty(fixPatch)}, new String[]{"RFixPatchPlugin patchType=", ""}));
            if (ScriptBytecodeAdapter.compareEqual(siteArr0[10].callGetProperty(fixPatch), siteArr0[11].callGetProperty(RFixConstants.class))) {
                siteArr0[12].call(siteArr0[13].callGetProperty(this.project.get()), "RFixPatchPlugin apply patch disabled.");
                return null;
            }
            else {
                Object type = null;
                Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[14].call(siteArr0[15].call(siteArr0[16].callGetProperty(fixPatch), "\|")), Iterator.class);
                while (iterator.hasNext()) {
                    type = iterator.next();
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[17].call(siteArr0[18].call(EngineManager.class), type)) ? 0 : 1 != 0) {
                        siteArr0[19].call(siteArr0[20].callGetProperty(this.project.get()), new GStringImpl(new Object[]{type}, new String[]{"RFixPatchPlugin patch type '", "' not support!"}));
                    }
                }
                Object pluginWrapper = siteArr0[21].callConstructor(TinkerPluginWrapper.class);
                siteArr0[22].call(pluginWrapper, this.project.get());
                siteArr0[23].callCurrent(this, this.project.get(), fixPatch);
                return siteArr0[24].call(RFixPatchMonitor.class, this.project.get(), fixPatch);
            }
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = RFixPatchPlugin$_initRFixPluginTask_closure1.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = RFixPatchPlugin$_initRFixPluginTask_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPlugin$_initRFixPluginTask_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPlugin$_initRFixPluginTask_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPlugin$_initRFixPluginTask_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPlugin$_initRFixPluginTask_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "getSupportEngine";
            stringArr0[1] = "getInstance";
            stringArr0[2] = "warn";
            stringArr0[3] = "logger";
            stringArr0[4] = "initRFixConfig";
            stringArr0[5] = "RFixPatch";
            stringArr0[6] = "extensions";
            stringArr0[7] = "warn";
            stringArr0[8] = "logger";
            stringArr0[9] = "patchType";
            stringArr0[10] = "patchType";
            stringArr0[11] = "PATCH_TYPE_DISABLE";
            stringArr0[12] = "warn";
            stringArr0[13] = "logger";
            stringArr0[14] = "iterator";
            stringArr0[15] = "split";
            stringArr0[16] = "patchType";
            stringArr0[17] = "isSupport";
            stringArr0[18] = "getInstance";
            stringArr0[19] = "error";
            stringArr0[20] = "logger";
            stringArr0[21] = "<$constructor$>";
            stringArr0[22] = "afterEvaluate";
            stringArr0[23] = "initAutoVerifyConfig";
            stringArr0[24] = "init";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPlugin$_initRFixPluginTask_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPlugin$_initRFixPluginTask_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPlugin$_initRFixPluginTask_closure1.$callSiteArray != null ? RFixPatchPlugin$_initRFixPluginTask_closure1.$createCallSiteArray() : (CallSiteArray)RFixPatchPlugin$_initRFixPluginTask_closure1.$callSiteArray.get();
            RFixPatchPlugin$_initRFixPluginTask_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/RFixPatchPlugin$_initRFixPluginTask_closure1
    public final class RFixPatchPlugin$_initRFixPluginTask_closure1 implements GeneratedClosure {
        private synthetic Reference project;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPlugin$_initRFixPluginTask_closure1(Object _outerInstance, Object _thisObject, Reference project) {
            CallSite[] siteArr0 = RFixPatchPlugin$_initRFixPluginTask_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            project.project = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = RFixPatchPlugin$_initRFixPluginTask_closure1.$getCallSiteArray();
            Object supportEngine = siteArr0[0].call(siteArr0[1].call(EngineManager.class));
            siteArr0[2].call(siteArr0[3].callGetProperty(this.project.get()), new GStringImpl(new Object[]{supportEngine}, new String[]{"RFixPatchPlugin support patch type: ", ""}));
            siteArr0[4].callCurrent(this, this.project.get());
            RFixPatchExtension fixPatch = (RFixPatchExtension)ScriptBytecodeAdapter.asType(siteArr0[5].callGetProperty(siteArr0[6].callGetProperty(this.project.get())), RFixPatchExtension.class);
            siteArr0[7].call(siteArr0[8].callGetProperty(this.project.get()), new GStringImpl(new Object[]{siteArr0[9].callGetProperty(fixPatch)}, new String[]{"RFixPatchPlugin patchType=", ""}));
            if (ScriptBytecodeAdapter.compareEqual(siteArr0[10].callGetProperty(fixPatch), siteArr0[11].callGetProperty(RFixConstants.class))) {
                siteArr0[12].call(siteArr0[13].callGetProperty(this.project.get()), "RFixPatchPlugin apply patch disabled.");
                return null;
            }
            else {
                Object type = null;
                Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[14].call(siteArr0[15].call(siteArr0[16].callGetProperty(fixPatch), "\|")), Iterator.class);
                while (iterator.hasNext()) {
                    type = iterator.next();
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[17].call(siteArr0[18].call(EngineManager.class), type)) ? 0 : 1 != 0) {
                        siteArr0[19].call(siteArr0[20].callGetProperty(this.project.get()), new GStringImpl(new Object[]{type}, new String[]{"RFixPatchPlugin patch type '", "' not support!"}));
                    }
                }
                Object pluginWrapper = siteArr0[21].callConstructor(TinkerPluginWrapper.class);
                siteArr0[22].call(pluginWrapper, this.project.get());
                siteArr0[23].callCurrent(this, this.project.get(), fixPatch);
                return siteArr0[24].call(RFixPatchMonitor.class, this.project.get(), fixPatch);
            }
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = RFixPatchPlugin$_initRFixPluginTask_closure1.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = RFixPatchPlugin$_initRFixPluginTask_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPlugin$_initRFixPluginTask_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPlugin$_initRFixPluginTask_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPlugin$_initRFixPluginTask_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPlugin$_initRFixPluginTask_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "getSupportEngine";
            stringArr0[1] = "getInstance";
            stringArr0[2] = "warn";
            stringArr0[3] = "logger";
            stringArr0[4] = "initRFixConfig";
            stringArr0[5] = "RFixPatch";
            stringArr0[6] = "extensions";
            stringArr0[7] = "warn";
            stringArr0[8] = "logger";
            stringArr0[9] = "patchType";
            stringArr0[10] = "patchType";
            stringArr0[11] = "PATCH_TYPE_DISABLE";
            stringArr0[12] = "warn";
            stringArr0[13] = "logger";
            stringArr0[14] = "iterator";
            stringArr0[15] = "split";
            stringArr0[16] = "patchType";
            stringArr0[17] = "isSupport";
            stringArr0[18] = "getInstance";
            stringArr0[19] = "error";
            stringArr0[20] = "logger";
            stringArr0[21] = "<$constructor$>";
            stringArr0[22] = "afterEvaluate";
            stringArr0[23] = "initAutoVerifyConfig";
            stringArr0[24] = "init";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPlugin$_initRFixPluginTask_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPlugin$_initRFixPluginTask_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPlugin$_initRFixPluginTask_closure1.$callSiteArray != null ? RFixPatchPlugin$_initRFixPluginTask_closure1.$createCallSiteArray() : (CallSiteArray)RFixPatchPlugin$_initRFixPluginTask_closure1.$callSiteArray.get();
            RFixPatchPlugin$_initRFixPluginTask_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/RFixPatchPlugin$_initThinFlutterPluginConfig_closure2
    public final class RFixPatchPlugin$_initThinFlutterPluginConfig_closure2 implements GeneratedClosure {
        private synthetic Reference project;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPlugin$_initThinFlutterPluginConfig_closure2(Object _outerInstance, Object _thisObject, Reference project) {
            CallSite[] siteArr0 = RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            project.project = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$getCallSiteArray();
            ThinFlutterExtension thinFlutter = (ThinFlutterExtension)ScriptBytecodeAdapter.asType(siteArr0[0].call(siteArr0[1].callGetProperty(this.project.get()), "thinFlutter"), ThinFlutterExtension.class);
            if (ScriptBytecodeAdapter.compareEqual(thinFlutter, null)) {
                Object object = siteArr0[2].call(siteArr0[3].callGetProperty(this.project.get()), "thinFlutter", ThinFlutterExtension.class, this.project.get());
            }
            RFixPatchExtension fixPatch = (RFixPatchExtension)ScriptBytecodeAdapter.asType(siteArr0[4].callGetProperty(siteArr0[5].callGetProperty(this.project.get())), RFixPatchExtension.class);
            Object objectVar2 = siteArr0[6].callGetProperty(siteArr0[7].callGetProperty(fixPatch));
            ScriptBytecodeAdapter.setProperty(objectVar2, null, object, (String)"enable");
            if (ScriptBytecodeAdapter.compareNotEqual(siteArr0[8].callGetProperty(siteArr0[9].callGetProperty(fixPatch)), null)) {
                Object objectVar3 = siteArr0[10].callGetProperty(siteArr0[11].callGetProperty(fixPatch));
                ScriptBytecodeAdapter.setProperty(objectVar3, null, object, (String)"flutterVersion");
                return objectVar3;
            }
            else {
                return null;
            }
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "findByName";
            stringArr0[1] = "extensions";
            stringArr0[2] = "create";
            stringArr0[3] = "extensions";
            stringArr0[4] = "RFixPatch";
            stringArr0[5] = "extensions";
            stringArr0[6] = "enableFlutterSupport";
            stringArr0[7] = "buildConfig";
            stringArr0[8] = "flutterVersion";
            stringArr0[9] = "buildConfig";
            stringArr0[10] = "flutterVersion";
            stringArr0[11] = "buildConfig";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$callSiteArray != null ? RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$createCallSiteArray() : (CallSiteArray)RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$callSiteArray.get();
            RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/RFixPatchPlugin$_initThinFlutterPluginConfig_closure2
    public final class RFixPatchPlugin$_initThinFlutterPluginConfig_closure2 implements GeneratedClosure {
        private synthetic Reference project;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPlugin$_initThinFlutterPluginConfig_closure2(Object _outerInstance, Object _thisObject, Reference project) {
            CallSite[] siteArr0 = RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            project.project = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$getCallSiteArray();
            ThinFlutterExtension thinFlutter = (ThinFlutterExtension)ScriptBytecodeAdapter.asType(siteArr0[0].call(siteArr0[1].callGetProperty(this.project.get()), "thinFlutter"), ThinFlutterExtension.class);
            if (ScriptBytecodeAdapter.compareEqual(thinFlutter, null)) {
                Object object = siteArr0[2].call(siteArr0[3].callGetProperty(this.project.get()), "thinFlutter", ThinFlutterExtension.class, this.project.get());
            }
            RFixPatchExtension fixPatch = (RFixPatchExtension)ScriptBytecodeAdapter.asType(siteArr0[4].callGetProperty(siteArr0[5].callGetProperty(this.project.get())), RFixPatchExtension.class);
            Object objectVar2 = siteArr0[6].callGetProperty(siteArr0[7].callGetProperty(fixPatch));
            ScriptBytecodeAdapter.setProperty(objectVar2, null, object, (String)"enable");
            if (ScriptBytecodeAdapter.compareNotEqual(siteArr0[8].callGetProperty(siteArr0[9].callGetProperty(fixPatch)), null)) {
                Object objectVar3 = siteArr0[10].callGetProperty(siteArr0[11].callGetProperty(fixPatch));
                ScriptBytecodeAdapter.setProperty(objectVar3, null, object, (String)"flutterVersion");
                return objectVar3;
            }
            else {
                return null;
            }
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "findByName";
            stringArr0[1] = "extensions";
            stringArr0[2] = "create";
            stringArr0[3] = "extensions";
            stringArr0[4] = "RFixPatch";
            stringArr0[5] = "extensions";
            stringArr0[6] = "enableFlutterSupport";
            stringArr0[7] = "buildConfig";
            stringArr0[8] = "flutterVersion";
            stringArr0[9] = "buildConfig";
            stringArr0[10] = "flutterVersion";
            stringArr0[11] = "buildConfig";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$callSiteArray != null ? RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$createCallSiteArray() : (CallSiteArray)RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$callSiteArray.get();
            RFixPatchPlugin$_initThinFlutterPluginConfig_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
