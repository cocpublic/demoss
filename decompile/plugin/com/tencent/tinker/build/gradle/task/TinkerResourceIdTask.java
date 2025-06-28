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
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import sun.misc.Unsafe;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import com.tencent.tinker.build.aapt.AaptResourceCollector;

// class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask
public class TinkerResourceIdTask implements GroovyObject {
    @Internal
    private Object variant;
    @Internal
    private String resDir;
    @Input
    private String applicationId;
    @Input
    private boolean addPublicFlagForAapt2;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerResourceIdTask() {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        super();
        int i0 = 0;
        i0.addPublicFlagForAapt2 = this;
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        String str0 = "tinker";
        ScriptBytecodeAdapter.setGroovyObjectProperty(str0, TinkerResourceIdTask.class, this, (String)"group");
    }

    protected void addStableIdsFileToAdditionalParameters(Object processResourcesTask) {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        Object stableIdsFilePath = siteArr0[0].call(siteArr0[1].call(siteArr0[2].callGroovyObjectGetProperty(this), siteArr0[3].call(TinkerBuildPath.class, siteArr0[4].callGroovyObjectGetProperty(this))));
        Boolean injectSuccess = Boolean.valueOf(false);
        try {
            try {
                siteArr0[5].callStatic(TinkerResourceIdTask.class, processResourcesTask, stableIdsFilePath);
                int i0 = true;
                injectSuccess = Boolean.valueOf(i0);
            }
            catch (Exception e) {
                siteArr0[6].callCurrent(this, new GStringImpl(new Object[]{e}, new String[]{"tinker add additionalParameters fail with AGP 4.1.0+ method! exception=", ""}));
                int i1 = false;
                injectSuccess = Boolean.valueOf(i1);
            }
        }
        finally {
            Throwable throwable = v_40;
            throw throwable;
        }
        if (DefaultTypeTransformation.booleanUnbox(injectSuccess) ? 0 : 1 != 0) {
            try {
                try {
                    siteArr0[7].callStatic(TinkerResourceIdTask.class, processResourcesTask, stableIdsFilePath);
                    int i2 = true;
                    injectSuccess = Boolean.valueOf(i2);
                }
                catch (Exception e) {
                    siteArr0[8].callCurrent(this, new GStringImpl(new Object[]{e}, new String[]{"tinker add additionalParameters fail with AGP 3.2.1 ~ 4.0.2 method! exception=", ""}));
                    int i3 = false;
                    injectSuccess = Boolean.valueOf(i3);
                }
            }
            finally {
                Throwable throwableVar1 = v_56;
                throw throwableVar1;
            }
        }
        if (DefaultTypeTransformation.booleanUnbox(injectSuccess) ? 0 : 1 != 0) {
            throw (Throwable)siteArr0[9].callConstructor(GradleException.class, "rfix add additionalParameters fail! current AGP not support?");
        }
        else {
            siteArr0[10].callCurrent(this, new GStringImpl(new Object[]{stableIdsFilePath}, new String[]{"rfix add additionalParameters done: --stable-ids=", ""}));
        }
    }

    private static void addStableIdsFileForAGP321(Task processResourcesTask, String stableIdsFilePath) {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        Class taskClass = Class.forName("com.android.build.gradle.internal.res.LinkApplicationAndroidResourcesTask");
        Object aaptOptions = siteArr0[11].call(siteArr0[12].callGetProperty(taskClass), processResourcesTask, "aaptOptions");
        Object parameters = siteArr0[13].callGetProperty(aaptOptions);
        if (! ScriptBytecodeAdapter.compareEqual(parameters, null) || (parameters instanceof AbstractList) ? 0 : 1 != 0) {
            Object object = siteArr0[14].callConstructor(ArrayList.class);
            siteArr0[15].callStatic(TinkerResourceIdTask.class, AaptOptions.class, "additionalParameters", aaptOptions, object);
        }
        if (ScriptBytecodeAdapter.compareNotEqual(object, null)) {
            siteArr0[16].call(object, "--stable-ids");
            siteArr0[17].call(object, stableIdsFilePath);
        }
    }

    private static void addStableIdsFileForAGP410(Task processResourcesTask, String stableIdsFilePath) {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        Class taskClass = Class.forName("com.android.build.gradle.internal.res.LinkApplicationAndroidResourcesTask");
        Object aaptAdditionalParameters = siteArr0[18].call(siteArr0[19].callGetProperty(taskClass), processResourcesTask, "aaptAdditionalParameters");
        Class abstractPropertyClass = Class.forName("org.gradle.api.internal.provider.AbstractProperty");
        Object listPropertyValue = siteArr0[20].call(siteArr0[21].callGetProperty(abstractPropertyClass), aaptAdditionalParameters, "value");
        Class fixedSupplierClass = Class.forName("org.gradle.api.internal.provider.AbstractCollectionProperty$FixedSupplier");
        Object supplierValue = siteArr0[22].call(siteArr0[23].callGetProperty(fixedSupplierClass), listPropertyValue, "value");
        Object builder = siteArr0[24].callConstructor(ImmutableList$Builder.class);
        siteArr0[25].call(builder, siteArr0[26].call(supplierValue));
        siteArr0[27].call(builder, "--stable-ids");
        siteArr0[28].call(builder, stableIdsFilePath);
        Object newSupplierValue = siteArr0[29].call(builder);
        siteArr0[30].callStatic(TinkerResourceIdTask.class, fixedSupplierClass, "value", listPropertyValue, newSupplierValue);
    }

    public static void replaceFinalField(Class<?> clazz, String fieldName, Object instance, Object fieldValue) {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        Object field = null;
        while (true) {
            try {
                Object object = siteArr0[31].call(clazz, fieldName);
                field = (Field)ScriptBytecodeAdapter.castToType(object, Field.class);
                break;;
            }
            catch (NoSuchFieldException e) {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[32].call(clazz, Object.class))) {
                    throw (Throwable)e;
                }
                else {
                    Object objectVar1 = siteArr0[33].call(clazz);
                    currClazz = (Class)ShortTypeHandling.castToClass(objectVar1);
                    goto 117;
                    continue;;
                }
            }
            finally {
                Throwable throwable = v_17;
                throw throwable;
            }
        }
        Field unsafeField = (Field)ScriptBytecodeAdapter.castToType(siteArr0[34].call(Unsafe.class, "theUnsafe"), Field.class);
        siteArr0[35].call(unsafeField, Boolean.valueOf(true));
        Unsafe unsafe = (Unsafe)ScriptBytecodeAdapter.castToType(siteArr0[36].call(unsafeField, null), Unsafe.class);
        long fieldOffset = DefaultTypeTransformation.longUnbox(siteArr0[37].call(unsafe, field));
        siteArr0[38].call(unsafe, instance, Long.valueOf(fieldOffset), fieldValue);
    }

    public static String getAndroidGradlePluginVersionCompat() {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        Object version = null;
        try {
            try {
                Class versionModel = Class.forName("com.android.builder.model.Version");
                Object versionFiled = siteArr0[39].call(versionModel, "ANDROID_GRADLE_PLUGIN_VERSION");
                siteArr0[40].call(versionFiled, Boolean.valueOf(true));
                Object object = siteArr0[41].call(versionFiled, null);
                String str0 = (String)ShortTypeHandling.castToString(object);
            }
            catch (Exception e) {
            }
        }
        finally {
            Throwable throwable = v_31;
            throw throwable;
        }
        return str0;
    }

    public static <T> T resolveEnumValue(String value, Class<T> type) {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        Object constant = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[42].call(siteArr0[43].call(type)), Iterator.class);
        while (iterator.hasNext()) {
            constant = iterator.next();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[44].call(siteArr0[45].call(constant), value))) {
                return constant;
            }
            else {
                continue;;
            }
        }
        return null;
    }

    public static Object getProjectOptions(Project project) {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        try {
            Object basePlugin = DefaultTypeTransformation.booleanUnbox(siteArr0[46].call(siteArr0[47].call(project), "com.android.application")) ? siteArr0[50].call(siteArr0[51].call(project), "com.android.library") : siteArr0[48].call(siteArr0[49].call(project), "com.android.application");
            Object object = siteArr0[52].call(siteArr0[53].call(Class.forName("com.android.build.gradle.BasePlugin")), basePlugin, "projectOptions");
            return object;
        }
        catch (Exception e) {
            goto 132;
            goto 140;
            Throwable throwable = v_13;
            throw throwable;
            return null;
        }
        try {
        }
        finally {
        }
    }

    public static boolean isAapt2EnabledCompat(Project project) {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        if (BytecodeInterface8.isOrigZ() && TinkerResourceIdTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 51;
            if (ScriptBytecodeAdapter.compareGreaterThanEqual(TinkerResourceIdTask.getAndroidGradlePluginVersionCompat(), "3.3.0")) {
                return true;
            }
        }
        else if (ScriptBytecodeAdapter.compareGreaterThanEqual(siteArr0[54].callStatic(TinkerResourceIdTask.class), "3.3.0")) {
            return true;
        }
        else {
        }
        int aapt2Enabled = false;
        try {
            boolean bool3;
            try {
                Object projectOptions = siteArr0[55].callStatic(TinkerResourceIdTask.class, project);
                Object enumValue = siteArr0[56].callStatic(TinkerResourceIdTask.class, "ENABLE_AAPT2", Class.forName("com.android.build.gradle.options.BooleanOption"));
                Object object = siteArr0[57].call(projectOptions, enumValue);
                bool3 = DefaultTypeTransformation.booleanUnbox(object);
            }
            catch (Exception e) {
                try {
                    try {
                        Class classAndroidGradleOptions = Class.forName("com.android.build.gradle.AndroidGradleOptions");
                        Object isAapt2Enabled = siteArr0[58].call(classAndroidGradleOptions, "isAapt2Enabled", Project.class);
                        siteArr0[59].call(isAapt2Enabled, Boolean.valueOf(true));
                        Object objectVar1 = siteArr0[60].call(isAapt2Enabled, null, project);
                        bool3 = DefaultTypeTransformation.booleanUnbox(objectVar1);
                    }
                    catch (Exception e1) {
                        boolean bool2 = false;
                    }
                }
                finally {
                    Throwable throwable = v_75;
                    throw throwable;
                }
            }
        }
        finally {
            Throwable throwableVar1 = v_36;
            throw throwableVar1;
        }
        return bool2;
    }

    private Map<String, String> getRealNameMap() {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        v_9 = alloc(Reference);
        new (Map)ScriptBytecodeAdapter.castToType(siteArr0[61].callConstructor(HashMap.class), Map.class).<init>(v_9);
        Reference realNameMap = v_9;
        Object mergeResourcesTask = siteArr0[62].call(Compatibilities.class, siteArr0[63].callGroovyObjectGetProperty(this), this.variant);
        List resDirCandidateList = (List)ScriptBytecodeAdapter.castToType(siteArr0[64].callConstructor(ArrayList.class), List.class);
        try {
            try {
                Object output = siteArr0[65].callGetProperty(mergeResourcesTask);
                if ((output instanceof File)) {
                    siteArr0[66].call(resDirCandidateList, output);
                }
                else {
                    siteArr0[67].call(resDirCandidateList, siteArr0[68].call(siteArr0[69].call(output)));
                }
            }
            catch (Exception ignore) {
            }
            goto 188;
        }
        finally {
            Throwable throwable = v_42;
            throw throwable;
        }
        Object incFolder = siteArr0[70].call(mergeResourcesTask);
        if ((incFolder instanceof File)) {
            siteArr0[71].call(resDirCandidateList, siteArr0[72].callConstructor(File.class, incFolder, "merged.dir"));
        }
        else {
            siteArr0[73].call(resDirCandidateList, siteArr0[74].callConstructor(File.class, siteArr0[75].call(siteArr0[76].call(incFolder)), "merged.dir"));
        }
        siteArr0[77].call(resDirCandidateList, new TinkerResourceIdTask$_getRealNameMap_closure1(this, this));
        siteArr0[78].call(siteArr0[79].call(siteArr0[80].callGroovyObjectGetProperty(this), siteArr0[81].call(TinkerBuildPath.class, siteArr0[82].callGroovyObjectGetProperty(this))), siteArr0[83].callGetProperty(FileType.class), new TinkerResourceIdTask$_getRealNameMap_closure2(this, this, realNameMap));
        return (Map)realNameMap.get();
    }

    private ArrayList<String> getSortedStableIds(Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> rTypeResourceMap) {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        v_9 = alloc(Reference);
        new (List)ScriptBytecodeAdapter.castToType(siteArr0[84].callConstructor(ArrayList.class), List.class).<init>(v_9);
        Reference sortedLines = v_9;
        v_12 = alloc(Reference);
        new null.<init>(v_12);
        Reference realNameMap = v_12;
        if (TinkerResourceIdTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 101;
            Map map = this.getRealNameMap();
            map.set((Reference)realNameMap);
        }
        else {
            Object object = siteArr0[85].callCurrent(this);
            (Map)ScriptBytecodeAdapter.castToType(object, Map.class).set((Reference)realNameMap);
        }
        siteArr0[86].callSafe(rTypeResourceMap, new TinkerResourceIdTask$_getSortedStableIds_closure3(this, this, realNameMap, sortedLines));
        siteArr0[87].call(Collections.class, (List)sortedLines.get());
        return (ArrayList)ScriptBytecodeAdapter.castToType((List)sortedLines.get(), ArrayList.class);
    }

    public void convertPublicTxtToPublicXml(File publicTxtFile, File publicXmlFile, boolean withId) {
        v_1 = alloc(Reference);
        new publicXmlFile.<init>(v_1);
        Reference reference = v_1;
        v_4 = alloc(Reference);
        new Boolean.valueOf(withId).<init>(v_4);
        Reference referenceVar1 = v_4;
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(publicTxtFile, null)) {
        }
        else {
            siteArr0[88].call(GFileUtils.class, (File)reference.get());
            siteArr0[89].call(GFileUtils.class, siteArr0[90].call((File)reference.get()));
            siteArr0[91].call(GFileUtils.class, (File)reference.get());
            siteArr0[92].call((File)reference.get(), "<!-- AUTO-GENERATED FILE.  DO NOT MODIFY -->");
            siteArr0[93].call((File)reference.get(), "
");
            siteArr0[94].call((File)reference.get(), "<resources>");
            siteArr0[95].call((File)reference.get(), "
");
            v_78 = alloc(Reference);
            new (Pattern)ScriptBytecodeAdapter.castToType(siteArr0[96].call(Pattern.class, ".*?:(.*?)/(.*?)\s+=\s+(.*?)"), Pattern.class).<init>(v_78);
            Reference linePattern = v_78;
            siteArr0[97].callSafe(publicTxtFile, new TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4(this, this, linePattern, referenceVar1, reference));
            siteArr0[98].call((File)reference.get(), "</resources>");
        }
    }

    public void compileXmlForAapt2(File xmlFile) {
        v_1 = alloc(Reference);
        new xmlFile.<init>(v_1);
        Reference reference = v_1;
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        if (BytecodeInterface8.isOrigZ() && TinkerResourceIdTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 92;
            if (ScriptBytecodeAdapter.compareEqual((File)reference.get(), null)) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[100].call((File)reference.get())) ? 0 : 1 != 0 ? 0 : 1 != 0) {
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareEqual((File)reference.get(), null)) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[99].call((File)reference.get())) ? 0 : 1 != 0 ? 0 : 1 != 0) {
            }
            else {
            }
        }
        Object variantData = siteArr0[101].call(siteArr0[102].call(this.variant), this.variant, "variantData");
        Object variantScope = siteArr0[103].call(variantData);
        Object globalScope = siteArr0[104].call(variantScope);
        Object androidBuilder = siteArr0[105].call(globalScope);
        Object targetInfo = siteArr0[106].call(androidBuilder);
        Object buildTools = siteArr0[107].call(targetInfo);
        Map paths = (Map)ScriptBytecodeAdapter.castToType(siteArr0[108].call(siteArr0[109].call(buildTools), buildTools, "mPaths"), Map.class);
        v_95 = alloc(Reference);
        new (String)ShortTypeHandling.castToString(siteArr0[110].call(paths, siteArr0[111].callStatic(TinkerResourceIdTask.class, "AAPT2", Class.forName("com.android.sdklib.BuildToolInfo$PathId")))).<init>(v_95);
        Reference aapt2Path = v_95;
        if (! ScriptBytecodeAdapter.compareEqual((String)aapt2Path.get(), null) || DefaultTypeTransformation.booleanUnbox(siteArr0[112].call((String)aapt2Path.get())) ? 0 : 1 != 0) {
            try {
                try {
                    Class aapt2MavenUtilsClass = Class.forName("com.android.build.gradle.internal.res.Aapt2MavenUtils");
                    Object getAapt2FromMavenMethod = siteArr0[113].call(aapt2MavenUtilsClass, "getAapt2FromMaven", Class.forName("com.android.build.gradle.internal.scope.GlobalScope"));
                    siteArr0[114].call(getAapt2FromMavenMethod, Boolean.valueOf(true));
                    Object aapt2FromMaven = siteArr0[115].call(getAapt2FromMavenMethod, null, globalScope);
                    Object object = siteArr0[116].call(siteArr0[117].call(siteArr0[118].callGetProperty(aapt2FromMaven)), siteArr0[119].callGetProperty(SdkConstants.class));
                    (String)ShortTypeHandling.castToString(object).set((Reference)aapt2Path);
                    goto 578;
                }
                catch (Throwable thr) {
                    throw (Throwable)siteArr0[120].callConstructor(GradleException.class, "Fail to get aapt2 path", thr);
                }
            }
            finally {
                Throwable throwable = v_163;
                throw throwable;
            }
        }
        siteArr0[121].call(siteArr0[122].callGetProperty(siteArr0[123].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{(String)aapt2Path.get()}, new String[]{"tinker get aapt2 path ", ""}));
        v_202 = alloc(Reference);
        new siteArr0[124].call(Compatibilities.class, siteArr0[125].callGroovyObjectGetProperty(this), this.variant).<init>(v_202);
        Reference mergeResourcesTask = v_202;
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[126].call((File)reference.get()))) {
            siteArr0[127].call(siteArr0[128].callGroovyObjectGetProperty(this), new TinkerResourceIdTask$_compileXmlForAapt2_closure5(this, this, aapt2Path, mergeResourcesTask, reference));
        }
    }

    @TaskAction
    public Object applyResourceId() {
        CallSite[] siteArr0 = TinkerResourceIdTask.$getCallSiteArray();
        String resourceMappingFile = (String)ShortTypeHandling.castToString(siteArr0[129].callGetProperty(siteArr0[130].callGetProperty(siteArr0[131].callGetProperty(siteArr0[132].callGetProperty(siteArr0[133].callGroovyObjectGetProperty(this))))));
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[134].call(FileOperation.class, resourceMappingFile)) ? 0 : 1 != 0) {
            siteArr0[135].call(siteArr0[136].callGetProperty(siteArr0[137].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{resourceMappingFile}, new String[]{"apply resource mapping file ", " is illegal, just ignore"}));
            return null;
        }
        else {
            siteArr0[138].call(siteArr0[139].callGetProperty(siteArr0[140].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[141].call(siteArr0[142].callGroovyObjectGetProperty(this)), resourceMappingFile}, new String[]{"we build ", " apk with apply resource mapping file ", ""}));
            int i0 = true;
            ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i0), null, siteArr0[143].callGetProperty(siteArr0[144].callGetProperty(siteArr0[145].callGetProperty(siteArr0[146].callGroovyObjectGetProperty(this)))), (String)"usingResourceMapping");
            Map rTypeResourceMap = (Map)ScriptBytecodeAdapter.castToType(siteArr0[147].call(PatchUtil.class, resourceMappingFile), Map.class);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[148].callStatic(TinkerResourceIdTask.class, siteArr0[149].callGroovyObjectGetProperty(this))) ? 0 : 1 != 0) {
                String idsXml = (String)ShortTypeHandling.castToString(siteArr0[150].call(this.resDir, "/values/ids.xml"));
                String publicXml = (String)ShortTypeHandling.castToString(siteArr0[151].call(this.resDir, "/values/public.xml"));
                siteArr0[152].call(FileOperation.class, idsXml);
                siteArr0[153].call(FileOperation.class, publicXml);
                List resourceDirectoryList = (List)ScriptBytecodeAdapter.castToType(siteArr0[154].callConstructor(ArrayList.class), List.class);
                siteArr0[155].call(resourceDirectoryList, this.resDir);
                AaptResourceCollector aaptResourceCollector = (AaptResourceCollector)ScriptBytecodeAdapter.castToType(siteArr0[156].call(AaptUtil.class, resourceDirectoryList, rTypeResourceMap), AaptResourceCollector.class);
                siteArr0[157].call(PatchUtil.class, aaptResourceCollector, idsXml, publicXml);
                File publicFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[158].callConstructor(File.class, publicXml), File.class);
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[159].call(publicFile))) {
                    String resourcePublicXml = (String)ShortTypeHandling.castToString(siteArr0[160].call(TinkerBuildPath.class, siteArr0[161].callGroovyObjectGetProperty(this)));
                    siteArr0[162].call(FileOperation.class, publicFile, siteArr0[163].call(siteArr0[164].callGroovyObjectGetProperty(this), resourcePublicXml));
                    siteArr0[165].call(siteArr0[166].callGetProperty(siteArr0[167].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{resourcePublicXml}, new String[]{"tinker gen resource public.xml in ", ""}));
                }
                File idxFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[168].callConstructor(File.class, idsXml), File.class);
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[169].call(idxFile))) {
                    String resourceIdxXml = (String)ShortTypeHandling.castToString(siteArr0[170].call(TinkerBuildPath.class, siteArr0[171].callGroovyObjectGetProperty(this)));
                    siteArr0[172].call(FileOperation.class, idxFile, siteArr0[173].call(siteArr0[174].callGroovyObjectGetProperty(this), resourceIdxXml));
                    return siteArr0[175].call(siteArr0[176].callGetProperty(siteArr0[177].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{resourceIdxXml}, new String[]{"tinker gen resource idx.xml in ", ""}));
                }
                else {
                    return null;
                }
            }
            else {
                v_368 = alloc(Reference);
                new (File)ScriptBytecodeAdapter.castToType(siteArr0[178].call(siteArr0[179].callGroovyObjectGetProperty(this), siteArr0[180].call(TinkerBuildPath.class, siteArr0[181].callGroovyObjectGetProperty(this))), File.class).<init>(v_368);
                Reference stableIdsFile = v_368;
                siteArr0[182].call(FileOperation.class, (File)stableIdsFile.get());
                ArrayList sortedLines = (ArrayList)ScriptBytecodeAdapter.castToType(siteArr0[183].callCurrent(this, rTypeResourceMap), ArrayList.class);
                siteArr0[184].callSafe(sortedLines, new TinkerResourceIdTask$_applyResourceId_closure6(this, this, stableIdsFile));
                v_409 = alloc(Reference);
                new siteArr0[185].call(Compatibilities.class, siteArr0[186].callGroovyObjectGetProperty(this), this.variant).<init>(v_409);
                Reference processResourcesTask = v_409;
                return siteArr0[187].call(processResourcesTask.get(), new TinkerResourceIdTask$_applyResourceId_closure7(this, this, processResourcesTask, stableIdsFile));
            }
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerResourceIdTask.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerResourceIdTask.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerResourceIdTask.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerResourceIdTask.$staticClassInfo.getMetaClass();
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
    public Object getVariant() {
        return this.variant;
    }

    @Generated
    public void setVariant(Object object) {
        this.variant = object;
    }

    @Generated
    public String getResDir() {
        return this.resDir;
    }

    @Generated
    public void setResDir(String str0) {
        this.resDir = str0;
    }

    @Generated
    public String getApplicationId() {
        return this.applicationId;
    }

    @Generated
    public void setApplicationId(String str0) {
        this.applicationId = str0;
    }

    @Generated
    public boolean getAddPublicFlagForAapt2() {
        return this.addPublicFlagForAapt2;
    }

    @Generated
    public boolean isAddPublicFlagForAapt2() {
        return this.addPublicFlagForAapt2;
    }

    @Generated
    public void setAddPublicFlagForAapt2(boolean bool0) {
        this.addPublicFlagForAapt2 = bool0;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "getAbsolutePath";
        stringArr0[1] = "file";
        stringArr0[2] = "project";
        stringArr0[3] = "getResourcePublicTxt";
        stringArr0[4] = "project";
        stringArr0[5] = "addStableIdsFileForAGP410";
        stringArr0[6] = "println";
        stringArr0[7] = "addStableIdsFileForAGP321";
        stringArr0[8] = "println";
        stringArr0[9] = "<$constructor$>";
        stringArr0[10] = "println";
        stringArr0[11] = "getProperty";
        stringArr0[12] = "metaClass";
        stringArr0[13] = "additionalParameters";
        stringArr0[14] = "<$constructor$>";
        stringArr0[15] = "replaceFinalField";
        stringArr0[16] = "add";
        stringArr0[17] = "add";
        stringArr0[18] = "getProperty";
        stringArr0[19] = "metaClass";
        stringArr0[20] = "getProperty";
        stringArr0[21] = "metaClass";
        stringArr0[22] = "getProperty";
        stringArr0[23] = "metaClass";
        stringArr0[24] = "<$constructor$>";
        stringArr0[25] = "addAll";
        stringArr0[26] = "iterator";
        stringArr0[27] = "add";
        stringArr0[28] = "add";
        stringArr0[29] = "build";
        stringArr0[30] = "replaceFinalField";
        stringArr0[31] = "getDeclaredField";
        stringArr0[32] = "equals";
        stringArr0[33] = "getSuperclass";
        stringArr0[34] = "getDeclaredField";
        stringArr0[35] = "setAccessible";
        stringArr0[36] = "get";
        stringArr0[37] = "objectFieldOffset";
        stringArr0[38] = "putObject";
        stringArr0[39] = "getDeclaredField";
        stringArr0[40] = "setAccessible";
        stringArr0[41] = "get";
        stringArr0[42] = "iterator";
        stringArr0[43] = "getEnumConstants";
        stringArr0[44] = "equalsIgnoreCase";
        stringArr0[45] = "toString";
        stringArr0[46] = "hasPlugin";
        stringArr0[47] = "getPlugins";
        stringArr0[48] = "findPlugin";
        stringArr0[49] = "getPlugins";
        stringArr0[50] = "findPlugin";
        stringArr0[51] = "getPlugins";
        stringArr0[52] = "getProperty";
        stringArr0[53] = "getMetaClass";
        stringArr0[54] = "getAndroidGradlePluginVersionCompat";
        stringArr0[55] = "getProjectOptions";
        stringArr0[56] = "resolveEnumValue";
        stringArr0[57] = "get";
        stringArr0[58] = "getDeclaredMethod";
        stringArr0[59] = "setAccessible";
        stringArr0[60] = "invoke";
        stringArr0[61] = "<$constructor$>";
        stringArr0[62] = "getMergeResourcesTask";
        stringArr0[63] = "project";
        stringArr0[64] = "<$constructor$>";
        stringArr0[65] = "outputDir";
        stringArr0[66] = "add";
        stringArr0[67] = "add";
        stringArr0[68] = "get";
        stringArr0[69] = "getAsFile";
        stringArr0[70] = "getIncrementalFolder";
        stringArr0[71] = "add";
        stringArr0[72] = "<$constructor$>";
        stringArr0[73] = "add";
        stringArr0[74] = "<$constructor$>";
        stringArr0[75] = "get";
        stringArr0[76] = "getAsFile";
        stringArr0[77] = "each";
        stringArr0[78] = "eachFileRecurse";
        stringArr0[79] = "file";
        stringArr0[80] = "project";
        stringArr0[81] = "getResourceValuesBackup";
        stringArr0[82] = "project";
        stringArr0[83] = "FILES";
        stringArr0[84] = "<$constructor$>";
        stringArr0[85] = "getRealNameMap";
        stringArr0[86] = "each";
        stringArr0[87] = "sort";
        stringArr0[88] = "deleteQuietly";
        stringArr0[89] = "mkdirs";
        stringArr0[90] = "getParentFile";
        stringArr0[91] = "touch";
        stringArr0[92] = "append";
        stringArr0[93] = "append";
        stringArr0[94] = "append";
        stringArr0[95] = "append";
        stringArr0[96] = "compile";
        stringArr0[97] = "eachLine";
        stringArr0[98] = "append";
        stringArr0[99] = "exists";
        stringArr0[100] = "exists";
        stringArr0[101] = "getProperty";
        stringArr0[102] = "getMetaClass";
        stringArr0[103] = "getScope";
        stringArr0[104] = "getGlobalScope";
        stringArr0[105] = "getAndroidBuilder";
        stringArr0[106] = "getTargetInfo";
        stringArr0[107] = "getBuildTools";
        stringArr0[108] = "getProperty";
        stringArr0[109] = "getMetaClass";
        stringArr0[110] = "get";
        stringArr0[111] = "resolveEnumValue";
        stringArr0[112] = "isEmpty";
        stringArr0[113] = "getDeclaredMethod";
        stringArr0[114] = "setAccessible";
        stringArr0[115] = "invoke";
        stringArr0[116] = "resolve";
        stringArr0[117] = "toPath";
        stringArr0[118] = "singleFile";
        stringArr0[119] = "FN_AAPT2";
        stringArr0[120] = "<$constructor$>";
        stringArr0[121] = "error";
        stringArr0[122] = "logger";
        stringArr0[123] = "project";
        stringArr0[124] = "getMergeResourcesTask";
        stringArr0[125] = "project";
        stringArr0[126] = "exists";
        stringArr0[127] = "exec";
        stringArr0[128] = "project";
        stringArr0[129] = "applyResourceMapping";
        stringArr0[130] = "buildConfig";
        stringArr0[131] = "tinkerPatch";
        stringArr0[132] = "extensions";
        stringArr0[133] = "project";
        stringArr0[134] = "isLegalFile";
        stringArr0[135] = "error";
        stringArr0[136] = "logger";
        stringArr0[137] = "project";
        stringArr0[138] = "error";
        stringArr0[139] = "logger";
        stringArr0[140] = "project";
        stringArr0[141] = "getName";
        stringArr0[142] = "project";
        stringArr0[143] = "buildConfig";
        stringArr0[144] = "tinkerPatch";
        stringArr0[145] = "extensions";
        stringArr0[146] = "project";
        stringArr0[147] = "readRTxt";
        stringArr0[148] = "isAapt2EnabledCompat";
        stringArr0[149] = "project";
        stringArr0[150] = "plus";
        stringArr0[151] = "plus";
        stringArr0[152] = "deleteFile";
        stringArr0[153] = "deleteFile";
        stringArr0[154] = "<$constructor$>";
        stringArr0[155] = "add";
        stringArr0[156] = "collectResource";
        stringArr0[157] = "generatePublicResourceXml";
        stringArr0[158] = "<$constructor$>";
        stringArr0[159] = "exists";
        stringArr0[160] = "getResourcePublicXml";
        stringArr0[161] = "project";
        stringArr0[162] = "copyFileUsingStream";
        stringArr0[163] = "file";
        stringArr0[164] = "project";
        stringArr0[165] = "error";
        stringArr0[166] = "logger";
        stringArr0[167] = "project";
        stringArr0[168] = "<$constructor$>";
        stringArr0[169] = "exists";
        stringArr0[170] = "getResourceIdxXml";
        stringArr0[171] = "project";
        stringArr0[172] = "copyFileUsingStream";
        stringArr0[173] = "file";
        stringArr0[174] = "project";
        stringArr0[175] = "error";
        stringArr0[176] = "logger";
        stringArr0[177] = "project";
        stringArr0[178] = "file";
        stringArr0[179] = "project";
        stringArr0[180] = "getResourcePublicTxt";
        stringArr0[181] = "project";
        stringArr0[182] = "deleteFile";
        stringArr0[183] = "getSortedStableIds";
        stringArr0[184] = "each";
        stringArr0[185] = "getProcessResourcesTask";
        stringArr0[186] = "project";
        stringArr0[187] = "doFirst";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerResourceIdTask.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerResourceIdTask.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerResourceIdTask.$callSiteArray != null ? TinkerResourceIdTask.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask.$callSiteArray.get();
        TinkerResourceIdTask.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getSortedStableIds_closure3
    public final class TinkerResourceIdTask$_getSortedStableIds_closure3 implements GeneratedClosure {
        private synthetic Reference realNameMap;
        private synthetic Reference sortedLines;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_getSortedStableIds_closure3(Object _outerInstance, Object _thisObject, Reference realNameMap, Reference sortedLines) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            realNameMap.realNameMap = this;
            sortedLines.sortedLines = this;
        }

        public Object doCall(Object key, Object entries) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3.$getCallSiteArray();
            return siteArr0[0].call(entries, new TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10(this, this.getThisObject(), this.realNameMap, this.sortedLines));
        }

        public Object call(Object key, Object entries) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3.$getCallSiteArray();
            return siteArr0[1].callCurrent(this, key, entries);
        }

        @Generated
        public Map getRealNameMap() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3.$getCallSiteArray();
            return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
        }

        @Generated
        public List getSortedLines() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3.$getCallSiteArray();
            return (List)ScriptBytecodeAdapter.castToType(this.sortedLines.get(), List.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_getSortedStableIds_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_getSortedStableIds_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_getSortedStableIds_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_getSortedStableIds_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "each";
            stringArr0[1] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_getSortedStableIds_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_getSortedStableIds_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_getSortedStableIds_closure3.$callSiteArray != null ? TinkerResourceIdTask$_getSortedStableIds_closure3.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getSortedStableIds_closure3.$callSiteArray.get();
            TinkerResourceIdTask$_getSortedStableIds_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10
        public final class TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10 implements GeneratedClosure {
            private synthetic Reference realNameMap;
            private synthetic Reference sortedLines;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10(Object _outerInstance, Object _thisObject, Reference realNameMap, Reference sortedLines) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                realNameMap.realNameMap = this;
                sortedLines.sortedLines = this;
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                Object object = siteArr0[0].call(this.realNameMap.get(), siteArr0[1].callGetProperty(it));
                Object name = DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(this.realNameMap.get(), siteArr0[1].callGetProperty(it))) ? siteArr0[2].callGetProperty(it) : object;
                if (ScriptBytecodeAdapter.compareEqual(siteArr0[3].callGetProperty(it), siteArr0[4].callGetProperty(RDotTxtEntry$RType.class))) {
                    return null;
                }
                else {
                    return siteArr0[5].call(this.sortedLines.get(), new GStringImpl(new Object[]{siteArr0[6].callGroovyObjectGetProperty(this), siteArr0[7].callGetProperty(it), name, siteArr0[8].callGetProperty(it)}, new String[]{"", ":", "/", " = ", ""}));
                }
            }

            @Generated
            public Map getRealNameMap() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
            }

            @Generated
            public List getSortedLines() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return (List)ScriptBytecodeAdapter.castToType(this.sortedLines.get(), List.class);
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "get";
                stringArr0[1] = "name";
                stringArr0[2] = "name";
                stringArr0[3] = "type";
                stringArr0[4] = "STYLEABLE";
                stringArr0[5] = "add";
                stringArr0[6] = "applicationId";
                stringArr0[7] = "type";
                stringArr0[8] = "idValue";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray != null ? TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray.get();
                TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10
        public final class TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10 implements GeneratedClosure {
            private synthetic Reference realNameMap;
            private synthetic Reference sortedLines;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10(Object _outerInstance, Object _thisObject, Reference realNameMap, Reference sortedLines) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                realNameMap.realNameMap = this;
                sortedLines.sortedLines = this;
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                Object object = siteArr0[0].call(this.realNameMap.get(), siteArr0[1].callGetProperty(it));
                Object name = DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(this.realNameMap.get(), siteArr0[1].callGetProperty(it))) ? siteArr0[2].callGetProperty(it) : object;
                if (ScriptBytecodeAdapter.compareEqual(siteArr0[3].callGetProperty(it), siteArr0[4].callGetProperty(RDotTxtEntry$RType.class))) {
                    return null;
                }
                else {
                    return siteArr0[5].call(this.sortedLines.get(), new GStringImpl(new Object[]{siteArr0[6].callGroovyObjectGetProperty(this), siteArr0[7].callGetProperty(it), name, siteArr0[8].callGetProperty(it)}, new String[]{"", ":", "/", " = ", ""}));
                }
            }

            @Generated
            public Map getRealNameMap() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
            }

            @Generated
            public List getSortedLines() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return (List)ScriptBytecodeAdapter.castToType(this.sortedLines.get(), List.class);
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "get";
                stringArr0[1] = "name";
                stringArr0[2] = "name";
                stringArr0[3] = "type";
                stringArr0[4] = "STYLEABLE";
                stringArr0[5] = "add";
                stringArr0[6] = "applicationId";
                stringArr0[7] = "type";
                stringArr0[8] = "idValue";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray != null ? TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray.get();
                TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getSortedStableIds_closure3
    public final class TinkerResourceIdTask$_getSortedStableIds_closure3 implements GeneratedClosure {
        private synthetic Reference realNameMap;
        private synthetic Reference sortedLines;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_getSortedStableIds_closure3(Object _outerInstance, Object _thisObject, Reference realNameMap, Reference sortedLines) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            realNameMap.realNameMap = this;
            sortedLines.sortedLines = this;
        }

        public Object doCall(Object key, Object entries) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3.$getCallSiteArray();
            return siteArr0[0].call(entries, new TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10(this, this.getThisObject(), this.realNameMap, this.sortedLines));
        }

        public Object call(Object key, Object entries) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3.$getCallSiteArray();
            return siteArr0[1].callCurrent(this, key, entries);
        }

        @Generated
        public Map getRealNameMap() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3.$getCallSiteArray();
            return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
        }

        @Generated
        public List getSortedLines() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3.$getCallSiteArray();
            return (List)ScriptBytecodeAdapter.castToType(this.sortedLines.get(), List.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_getSortedStableIds_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_getSortedStableIds_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_getSortedStableIds_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_getSortedStableIds_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "each";
            stringArr0[1] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_getSortedStableIds_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_getSortedStableIds_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_getSortedStableIds_closure3.$callSiteArray != null ? TinkerResourceIdTask$_getSortedStableIds_closure3.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getSortedStableIds_closure3.$callSiteArray.get();
            TinkerResourceIdTask$_getSortedStableIds_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10
        public final class TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10 implements GeneratedClosure {
            private synthetic Reference realNameMap;
            private synthetic Reference sortedLines;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10(Object _outerInstance, Object _thisObject, Reference realNameMap, Reference sortedLines) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                realNameMap.realNameMap = this;
                sortedLines.sortedLines = this;
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                Object object = siteArr0[0].call(this.realNameMap.get(), siteArr0[1].callGetProperty(it));
                Object name = DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(this.realNameMap.get(), siteArr0[1].callGetProperty(it))) ? siteArr0[2].callGetProperty(it) : object;
                if (ScriptBytecodeAdapter.compareEqual(siteArr0[3].callGetProperty(it), siteArr0[4].callGetProperty(RDotTxtEntry$RType.class))) {
                    return null;
                }
                else {
                    return siteArr0[5].call(this.sortedLines.get(), new GStringImpl(new Object[]{siteArr0[6].callGroovyObjectGetProperty(this), siteArr0[7].callGetProperty(it), name, siteArr0[8].callGetProperty(it)}, new String[]{"", ":", "/", " = ", ""}));
                }
            }

            @Generated
            public Map getRealNameMap() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
            }

            @Generated
            public List getSortedLines() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return (List)ScriptBytecodeAdapter.castToType(this.sortedLines.get(), List.class);
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "get";
                stringArr0[1] = "name";
                stringArr0[2] = "name";
                stringArr0[3] = "type";
                stringArr0[4] = "STYLEABLE";
                stringArr0[5] = "add";
                stringArr0[6] = "applicationId";
                stringArr0[7] = "type";
                stringArr0[8] = "idValue";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray != null ? TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray.get();
                TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10
        public final class TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10 implements GeneratedClosure {
            private synthetic Reference realNameMap;
            private synthetic Reference sortedLines;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10(Object _outerInstance, Object _thisObject, Reference realNameMap, Reference sortedLines) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                realNameMap.realNameMap = this;
                sortedLines.sortedLines = this;
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                Object object = siteArr0[0].call(this.realNameMap.get(), siteArr0[1].callGetProperty(it));
                Object name = DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(this.realNameMap.get(), siteArr0[1].callGetProperty(it))) ? siteArr0[2].callGetProperty(it) : object;
                if (ScriptBytecodeAdapter.compareEqual(siteArr0[3].callGetProperty(it), siteArr0[4].callGetProperty(RDotTxtEntry$RType.class))) {
                    return null;
                }
                else {
                    return siteArr0[5].call(this.sortedLines.get(), new GStringImpl(new Object[]{siteArr0[6].callGroovyObjectGetProperty(this), siteArr0[7].callGetProperty(it), name, siteArr0[8].callGetProperty(it)}, new String[]{"", ":", "/", " = ", ""}));
                }
            }

            @Generated
            public Map getRealNameMap() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
            }

            @Generated
            public List getSortedLines() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return (List)ScriptBytecodeAdapter.castToType(this.sortedLines.get(), List.class);
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "get";
                stringArr0[1] = "name";
                stringArr0[2] = "name";
                stringArr0[3] = "type";
                stringArr0[4] = "STYLEABLE";
                stringArr0[5] = "add";
                stringArr0[6] = "applicationId";
                stringArr0[7] = "type";
                stringArr0[8] = "idValue";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray != null ? TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray.get();
                TinkerResourceIdTask$_getSortedStableIds_closure3$_closure10.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure1
    public final class TinkerResourceIdTask$_getRealNameMap_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_getRealNameMap_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1.$getCallSiteArray();
            return siteArr0[0].call(it, siteArr0[1].callGetProperty(FileType.class), new TinkerResourceIdTask$_getRealNameMap_closure1$_closure8(this, this.getThisObject()));
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_getRealNameMap_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_getRealNameMap_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_getRealNameMap_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "eachFileRecurse";
            stringArr0[1] = "FILES";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_getRealNameMap_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure1.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure1.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure1.$callSiteArray.get();
            TinkerResourceIdTask$_getRealNameMap_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure1$_closure8
        public final class TinkerResourceIdTask$_getRealNameMap_closure1$_closure8 implements GeneratedClosure {
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getRealNameMap_closure1$_closure8(Object _outerInstance, Object _thisObject) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].call(siteArr0[2].call(it)), "values")) && DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(siteArr0[4].call(it), "values")) ? 0 : 1 != 0 && DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(siteArr0[6].call(it), ".xml")) ? 0 : 1 != 0) {
                    File destFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[7].callConstructor(File.class, siteArr0[8].call(siteArr0[9].callGroovyObjectGetProperty(this), siteArr0[10].call(TinkerBuildPath.class, siteArr0[11].callGroovyObjectGetProperty(this))), new GStringImpl(new Object[]{siteArr0[12].call(siteArr0[13].call(it)), siteArr0[14].call(it)}, new String[]{"", "/", ""})), File.class);
                    siteArr0[15].call(GFileUtils.class, destFile);
                    siteArr0[16].call(GFileUtils.class, siteArr0[17].call(destFile));
                    return siteArr0[18].call(GFileUtils.class, it, destFile);
                }
                else {
                    return null;
                }
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "startsWith";
                stringArr0[1] = "getName";
                stringArr0[2] = "getParentFile";
                stringArr0[3] = "startsWith";
                stringArr0[4] = "getName";
                stringArr0[5] = "endsWith";
                stringArr0[6] = "getName";
                stringArr0[7] = "<$constructor$>";
                stringArr0[8] = "file";
                stringArr0[9] = "project";
                stringArr0[10] = "getResourceValuesBackup";
                stringArr0[11] = "project";
                stringArr0[12] = "getName";
                stringArr0[13] = "getParentFile";
                stringArr0[14] = "getName";
                stringArr0[15] = "deleteQuietly";
                stringArr0[16] = "mkdirs";
                stringArr0[17] = "getParentFile";
                stringArr0[18] = "copyFile";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray.get();
                TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure1$_closure8
        public final class TinkerResourceIdTask$_getRealNameMap_closure1$_closure8 implements GeneratedClosure {
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getRealNameMap_closure1$_closure8(Object _outerInstance, Object _thisObject) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].call(siteArr0[2].call(it)), "values")) && DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(siteArr0[4].call(it), "values")) ? 0 : 1 != 0 && DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(siteArr0[6].call(it), ".xml")) ? 0 : 1 != 0) {
                    File destFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[7].callConstructor(File.class, siteArr0[8].call(siteArr0[9].callGroovyObjectGetProperty(this), siteArr0[10].call(TinkerBuildPath.class, siteArr0[11].callGroovyObjectGetProperty(this))), new GStringImpl(new Object[]{siteArr0[12].call(siteArr0[13].call(it)), siteArr0[14].call(it)}, new String[]{"", "/", ""})), File.class);
                    siteArr0[15].call(GFileUtils.class, destFile);
                    siteArr0[16].call(GFileUtils.class, siteArr0[17].call(destFile));
                    return siteArr0[18].call(GFileUtils.class, it, destFile);
                }
                else {
                    return null;
                }
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "startsWith";
                stringArr0[1] = "getName";
                stringArr0[2] = "getParentFile";
                stringArr0[3] = "startsWith";
                stringArr0[4] = "getName";
                stringArr0[5] = "endsWith";
                stringArr0[6] = "getName";
                stringArr0[7] = "<$constructor$>";
                stringArr0[8] = "file";
                stringArr0[9] = "project";
                stringArr0[10] = "getResourceValuesBackup";
                stringArr0[11] = "project";
                stringArr0[12] = "getName";
                stringArr0[13] = "getParentFile";
                stringArr0[14] = "getName";
                stringArr0[15] = "deleteQuietly";
                stringArr0[16] = "mkdirs";
                stringArr0[17] = "getParentFile";
                stringArr0[18] = "copyFile";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray.get();
                TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure1
    public final class TinkerResourceIdTask$_getRealNameMap_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_getRealNameMap_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1.$getCallSiteArray();
            return siteArr0[0].call(it, siteArr0[1].callGetProperty(FileType.class), new TinkerResourceIdTask$_getRealNameMap_closure1$_closure8(this, this.getThisObject()));
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_getRealNameMap_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_getRealNameMap_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_getRealNameMap_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "eachFileRecurse";
            stringArr0[1] = "FILES";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_getRealNameMap_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure1.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure1.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure1.$callSiteArray.get();
            TinkerResourceIdTask$_getRealNameMap_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure1$_closure8
        public final class TinkerResourceIdTask$_getRealNameMap_closure1$_closure8 implements GeneratedClosure {
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getRealNameMap_closure1$_closure8(Object _outerInstance, Object _thisObject) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].call(siteArr0[2].call(it)), "values")) && DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(siteArr0[4].call(it), "values")) ? 0 : 1 != 0 && DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(siteArr0[6].call(it), ".xml")) ? 0 : 1 != 0) {
                    File destFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[7].callConstructor(File.class, siteArr0[8].call(siteArr0[9].callGroovyObjectGetProperty(this), siteArr0[10].call(TinkerBuildPath.class, siteArr0[11].callGroovyObjectGetProperty(this))), new GStringImpl(new Object[]{siteArr0[12].call(siteArr0[13].call(it)), siteArr0[14].call(it)}, new String[]{"", "/", ""})), File.class);
                    siteArr0[15].call(GFileUtils.class, destFile);
                    siteArr0[16].call(GFileUtils.class, siteArr0[17].call(destFile));
                    return siteArr0[18].call(GFileUtils.class, it, destFile);
                }
                else {
                    return null;
                }
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "startsWith";
                stringArr0[1] = "getName";
                stringArr0[2] = "getParentFile";
                stringArr0[3] = "startsWith";
                stringArr0[4] = "getName";
                stringArr0[5] = "endsWith";
                stringArr0[6] = "getName";
                stringArr0[7] = "<$constructor$>";
                stringArr0[8] = "file";
                stringArr0[9] = "project";
                stringArr0[10] = "getResourceValuesBackup";
                stringArr0[11] = "project";
                stringArr0[12] = "getName";
                stringArr0[13] = "getParentFile";
                stringArr0[14] = "getName";
                stringArr0[15] = "deleteQuietly";
                stringArr0[16] = "mkdirs";
                stringArr0[17] = "getParentFile";
                stringArr0[18] = "copyFile";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray.get();
                TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure1$_closure8
        public final class TinkerResourceIdTask$_getRealNameMap_closure1$_closure8 implements GeneratedClosure {
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getRealNameMap_closure1$_closure8(Object _outerInstance, Object _thisObject) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                super(_outerInstance, _thisObject);
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].call(siteArr0[2].call(it)), "values")) && DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(siteArr0[4].call(it), "values")) ? 0 : 1 != 0 && DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(siteArr0[6].call(it), ".xml")) ? 0 : 1 != 0) {
                    File destFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[7].callConstructor(File.class, siteArr0[8].call(siteArr0[9].callGroovyObjectGetProperty(this), siteArr0[10].call(TinkerBuildPath.class, siteArr0[11].callGroovyObjectGetProperty(this))), new GStringImpl(new Object[]{siteArr0[12].call(siteArr0[13].call(it)), siteArr0[14].call(it)}, new String[]{"", "/", ""})), File.class);
                    siteArr0[15].call(GFileUtils.class, destFile);
                    siteArr0[16].call(GFileUtils.class, siteArr0[17].call(destFile));
                    return siteArr0[18].call(GFileUtils.class, it, destFile);
                }
                else {
                    return null;
                }
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "startsWith";
                stringArr0[1] = "getName";
                stringArr0[2] = "getParentFile";
                stringArr0[3] = "startsWith";
                stringArr0[4] = "getName";
                stringArr0[5] = "endsWith";
                stringArr0[6] = "getName";
                stringArr0[7] = "<$constructor$>";
                stringArr0[8] = "file";
                stringArr0[9] = "project";
                stringArr0[10] = "getResourceValuesBackup";
                stringArr0[11] = "project";
                stringArr0[12] = "getName";
                stringArr0[13] = "getParentFile";
                stringArr0[14] = "getName";
                stringArr0[15] = "deleteQuietly";
                stringArr0[16] = "mkdirs";
                stringArr0[17] = "getParentFile";
                stringArr0[18] = "copyFile";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray.get();
                TinkerResourceIdTask$_getRealNameMap_closure1$_closure8.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure2
    public final class TinkerResourceIdTask$_getRealNameMap_closure2 implements GeneratedClosure {
        private synthetic Reference realNameMap;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_getRealNameMap_closure2(Object _outerInstance, Object _thisObject, Reference realNameMap) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            realNameMap.realNameMap = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].call(siteArr0[2].callConstructor(XmlParser.class), it), new TinkerResourceIdTask$_getRealNameMap_closure2$_closure9(this, this.getThisObject(), this.realNameMap));
        }

        @Generated
        public Map getRealNameMap() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2.$getCallSiteArray();
            return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_getRealNameMap_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_getRealNameMap_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_getRealNameMap_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "each";
            stringArr0[1] = "parse";
            stringArr0[2] = "<$constructor$>";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_getRealNameMap_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure2.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure2.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure2.$callSiteArray.get();
            TinkerResourceIdTask$_getRealNameMap_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure2$_closure9
        public final class TinkerResourceIdTask$_getRealNameMap_closure2$_closure9 implements GeneratedClosure {
            private synthetic Reference realNameMap;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getRealNameMap_closure2$_closure9(Object _outerInstance, Object _thisObject, Reference realNameMap) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                realNameMap.realNameMap = this;
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                String originalName = (String)ShortTypeHandling.castToString(siteArr0[0].call(new GStringImpl(new Object[]{ScriptBytecodeAdapter.getField(TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class, it, (String)"name")}, new String[]{"", ""})));
                if (! DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(originalName, ".")) || DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(originalName, ":")) ? 0 : 1 != 0) {
                    String sanitizeName = (String)ShortTypeHandling.castToString(siteArr0[3].call(originalName, "[.:]", "_"));
                    return siteArr0[4].call(this.realNameMap.get(), sanitizeName, originalName);
                }
                else {
                    return null;
                }
            }

            @Generated
            public Map getRealNameMap() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "toString";
                stringArr0[1] = "contains";
                stringArr0[2] = "contains";
                stringArr0[3] = "replaceAll";
                stringArr0[4] = "put";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray.get();
                TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure2$_closure9
        public final class TinkerResourceIdTask$_getRealNameMap_closure2$_closure9 implements GeneratedClosure {
            private synthetic Reference realNameMap;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getRealNameMap_closure2$_closure9(Object _outerInstance, Object _thisObject, Reference realNameMap) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                realNameMap.realNameMap = this;
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                String originalName = (String)ShortTypeHandling.castToString(siteArr0[0].call(new GStringImpl(new Object[]{ScriptBytecodeAdapter.getField(TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class, it, (String)"name")}, new String[]{"", ""})));
                if (! DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(originalName, ".")) || DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(originalName, ":")) ? 0 : 1 != 0) {
                    String sanitizeName = (String)ShortTypeHandling.castToString(siteArr0[3].call(originalName, "[.:]", "_"));
                    return siteArr0[4].call(this.realNameMap.get(), sanitizeName, originalName);
                }
                else {
                    return null;
                }
            }

            @Generated
            public Map getRealNameMap() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "toString";
                stringArr0[1] = "contains";
                stringArr0[2] = "contains";
                stringArr0[3] = "replaceAll";
                stringArr0[4] = "put";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray.get();
                TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure2
    public final class TinkerResourceIdTask$_getRealNameMap_closure2 implements GeneratedClosure {
        private synthetic Reference realNameMap;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_getRealNameMap_closure2(Object _outerInstance, Object _thisObject, Reference realNameMap) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            realNameMap.realNameMap = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].call(siteArr0[2].callConstructor(XmlParser.class), it), new TinkerResourceIdTask$_getRealNameMap_closure2$_closure9(this, this.getThisObject(), this.realNameMap));
        }

        @Generated
        public Map getRealNameMap() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2.$getCallSiteArray();
            return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_getRealNameMap_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_getRealNameMap_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_getRealNameMap_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "each";
            stringArr0[1] = "parse";
            stringArr0[2] = "<$constructor$>";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_getRealNameMap_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure2.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure2.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure2.$callSiteArray.get();
            TinkerResourceIdTask$_getRealNameMap_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure2$_closure9
        public final class TinkerResourceIdTask$_getRealNameMap_closure2$_closure9 implements GeneratedClosure {
            private synthetic Reference realNameMap;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getRealNameMap_closure2$_closure9(Object _outerInstance, Object _thisObject, Reference realNameMap) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                realNameMap.realNameMap = this;
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                String originalName = (String)ShortTypeHandling.castToString(siteArr0[0].call(new GStringImpl(new Object[]{ScriptBytecodeAdapter.getField(TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class, it, (String)"name")}, new String[]{"", ""})));
                if (! DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(originalName, ".")) || DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(originalName, ":")) ? 0 : 1 != 0) {
                    String sanitizeName = (String)ShortTypeHandling.castToString(siteArr0[3].call(originalName, "[.:]", "_"));
                    return siteArr0[4].call(this.realNameMap.get(), sanitizeName, originalName);
                }
                else {
                    return null;
                }
            }

            @Generated
            public Map getRealNameMap() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "toString";
                stringArr0[1] = "contains";
                stringArr0[2] = "contains";
                stringArr0[3] = "replaceAll";
                stringArr0[4] = "put";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray.get();
                TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_getRealNameMap_closure2$_closure9
        public final class TinkerResourceIdTask$_getRealNameMap_closure2$_closure9 implements GeneratedClosure {
            private synthetic Reference realNameMap;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public TinkerResourceIdTask$_getRealNameMap_closure2$_closure9(Object _outerInstance, Object _thisObject, Reference realNameMap) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                realNameMap.realNameMap = this;
            }

            public Object doCall(Object it) {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                String originalName = (String)ShortTypeHandling.castToString(siteArr0[0].call(new GStringImpl(new Object[]{ScriptBytecodeAdapter.getField(TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class, it, (String)"name")}, new String[]{"", ""})));
                if (! DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(originalName, ".")) || DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(originalName, ":")) ? 0 : 1 != 0) {
                    String sanitizeName = (String)ShortTypeHandling.castToString(siteArr0[3].call(originalName, "[.:]", "_"));
                    return siteArr0[4].call(this.realNameMap.get(), sanitizeName, originalName);
                }
                else {
                    return null;
                }
            }

            @Generated
            public Map getRealNameMap() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                return (Map)ScriptBytecodeAdapter.castToType(this.realNameMap.get(), Map.class);
            }

            @Generated
            public Object doCall() {
                CallSite[] siteArr0 = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$getCallSiteArray();
                return this.doCall(null);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "toString";
                stringArr0[1] = "contains";
                stringArr0[2] = "contains";
                stringArr0[3] = "replaceAll";
                stringArr0[4] = "put";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$createCallSiteArray_1(str0);
                return new CallSiteArray(TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray != null ? TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray.get();
                TinkerResourceIdTask$_getRealNameMap_closure2$_closure9.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4
    public final class TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4 implements GeneratedClosure {
        private synthetic Reference linePattern;
        private synthetic Reference withId;
        private synthetic Reference publicXmlFile;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4(Object _outerInstance, Object _thisObject, Reference linePattern, Reference withId, Reference publicXmlFile) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            linePattern.linePattern = this;
            withId.withId = this;
            publicXmlFile.publicXmlFile = this;
        }

        public Object doCall(Object line) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$getCallSiteArray();
            Matcher matcher = (Matcher)ScriptBytecodeAdapter.castToType(siteArr0[0].call(this.linePattern.get(), line), Matcher.class);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(matcher)) && ScriptBytecodeAdapter.compareEqual(siteArr0[2].call(matcher), Integer.valueOf(3)) ? 0 : 1 != 0) {
                String resType = (String)ShortTypeHandling.castToString(siteArr0[3].call(matcher, Integer.valueOf(1)));
                String resName = (String)ShortTypeHandling.castToString(siteArr0[4].call(matcher, Integer.valueOf(2)));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(resName, "$"))) {
                    return siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{resName}, new String[]{"ignore convert to public res ", " because it's a nested resource"}));
                }
                else if (DefaultTypeTransformation.booleanUnbox(siteArr0[9].call(resType, "styleable"))) {
                    return siteArr0[10].call(siteArr0[11].callGetProperty(siteArr0[12].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{resName}, new String[]{"ignore convert to public res ", " because it's a styleable resource"}));
                }
                else if (DefaultTypeTransformation.booleanUnbox(this.withId.get())) {
                    return siteArr0[13].call(this.publicXmlFile.get(), new GStringImpl(new Object[]{resType, resName, siteArr0[14].call(matcher, Integer.valueOf(3))}, new String[]{"	<public type="", "" name="", "" id="", "" />
"}));
                }
                else {
                    return siteArr0[15].call(this.publicXmlFile.get(), new GStringImpl(new Object[]{resType, resName}, new String[]{"	<public type="", "" name="", "" />
"}));
                }
            }
            else {
                return null;
            }
        }

        @Generated
        public Pattern getLinePattern() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$getCallSiteArray();
            return (Pattern)ScriptBytecodeAdapter.castToType(this.linePattern.get(), Pattern.class);
        }

        @Generated
        public boolean getWithId() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$getCallSiteArray();
            return DefaultTypeTransformation.booleanUnbox(this.withId.get());
        }

        @Generated
        public File getPublicXmlFile() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.publicXmlFile.get(), File.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "matcher";
            stringArr0[1] = "matches";
            stringArr0[2] = "groupCount";
            stringArr0[3] = "group";
            stringArr0[4] = "group";
            stringArr0[5] = "startsWith";
            stringArr0[6] = "error";
            stringArr0[7] = "logger";
            stringArr0[8] = "project";
            stringArr0[9] = "equalsIgnoreCase";
            stringArr0[10] = "error";
            stringArr0[11] = "logger";
            stringArr0[12] = "project";
            stringArr0[13] = "append";
            stringArr0[14] = "group";
            stringArr0[15] = "append";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$callSiteArray != null ? TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$callSiteArray.get();
            TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4
    public final class TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4 implements GeneratedClosure {
        private synthetic Reference linePattern;
        private synthetic Reference withId;
        private synthetic Reference publicXmlFile;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4(Object _outerInstance, Object _thisObject, Reference linePattern, Reference withId, Reference publicXmlFile) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            linePattern.linePattern = this;
            withId.withId = this;
            publicXmlFile.publicXmlFile = this;
        }

        public Object doCall(Object line) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$getCallSiteArray();
            Matcher matcher = (Matcher)ScriptBytecodeAdapter.castToType(siteArr0[0].call(this.linePattern.get(), line), Matcher.class);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(matcher)) && ScriptBytecodeAdapter.compareEqual(siteArr0[2].call(matcher), Integer.valueOf(3)) ? 0 : 1 != 0) {
                String resType = (String)ShortTypeHandling.castToString(siteArr0[3].call(matcher, Integer.valueOf(1)));
                String resName = (String)ShortTypeHandling.castToString(siteArr0[4].call(matcher, Integer.valueOf(2)));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(resName, "$"))) {
                    return siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{resName}, new String[]{"ignore convert to public res ", " because it's a nested resource"}));
                }
                else if (DefaultTypeTransformation.booleanUnbox(siteArr0[9].call(resType, "styleable"))) {
                    return siteArr0[10].call(siteArr0[11].callGetProperty(siteArr0[12].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{resName}, new String[]{"ignore convert to public res ", " because it's a styleable resource"}));
                }
                else if (DefaultTypeTransformation.booleanUnbox(this.withId.get())) {
                    return siteArr0[13].call(this.publicXmlFile.get(), new GStringImpl(new Object[]{resType, resName, siteArr0[14].call(matcher, Integer.valueOf(3))}, new String[]{"	<public type="", "" name="", "" id="", "" />
"}));
                }
                else {
                    return siteArr0[15].call(this.publicXmlFile.get(), new GStringImpl(new Object[]{resType, resName}, new String[]{"	<public type="", "" name="", "" />
"}));
                }
            }
            else {
                return null;
            }
        }

        @Generated
        public Pattern getLinePattern() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$getCallSiteArray();
            return (Pattern)ScriptBytecodeAdapter.castToType(this.linePattern.get(), Pattern.class);
        }

        @Generated
        public boolean getWithId() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$getCallSiteArray();
            return DefaultTypeTransformation.booleanUnbox(this.withId.get());
        }

        @Generated
        public File getPublicXmlFile() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.publicXmlFile.get(), File.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "matcher";
            stringArr0[1] = "matches";
            stringArr0[2] = "groupCount";
            stringArr0[3] = "group";
            stringArr0[4] = "group";
            stringArr0[5] = "startsWith";
            stringArr0[6] = "error";
            stringArr0[7] = "logger";
            stringArr0[8] = "project";
            stringArr0[9] = "equalsIgnoreCase";
            stringArr0[10] = "error";
            stringArr0[11] = "logger";
            stringArr0[12] = "project";
            stringArr0[13] = "append";
            stringArr0[14] = "group";
            stringArr0[15] = "append";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$callSiteArray != null ? TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$callSiteArray.get();
            TinkerResourceIdTask$_convertPublicTxtToPublicXml_closure4.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_compileXmlForAapt2_closure5
    public final class TinkerResourceIdTask$_compileXmlForAapt2_closure5 implements GeneratedClosure {
        private synthetic Reference aapt2Path;
        private synthetic Reference mergeResourcesTask;
        private synthetic Reference xmlFile;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_compileXmlForAapt2_closure5(Object _outerInstance, Object _thisObject, Reference aapt2Path, Reference mergeResourcesTask, Reference xmlFile) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            aapt2Path.aapt2Path = this;
            mergeResourcesTask.mergeResourcesTask = this;
            xmlFile.xmlFile = this;
        }

        public Object doCall(Object execSpec) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$getCallSiteArray();
            siteArr0[0].call(execSpec, new GStringImpl(new Object[]{this.aapt2Path.get()}, new String[]{"", ""}));
            siteArr0[1].call(execSpec, "compile");
            siteArr0[2].call(execSpec, "--legacy");
            siteArr0[3].call(execSpec, "-o");
            siteArr0[4].call(execSpec, new GStringImpl(new Object[]{siteArr0[5].callGetProperty(this.mergeResourcesTask.get())}, new String[]{"", ""}));
            return siteArr0[6].call(execSpec, new GStringImpl(new Object[]{this.xmlFile.get()}, new String[]{"", ""}));
        }

        @Generated
        public String getAapt2Path() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.aapt2Path.get());
        }

        @Generated
        public Object getMergeResourcesTask() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$getCallSiteArray();
            return this.mergeResourcesTask.get();
        }

        @Generated
        public File getXmlFile() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.xmlFile.get(), File.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_compileXmlForAapt2_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_compileXmlForAapt2_closure5.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_compileXmlForAapt2_closure5.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_compileXmlForAapt2_closure5.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "executable";
            stringArr0[1] = "args";
            stringArr0[2] = "args";
            stringArr0[3] = "args";
            stringArr0[4] = "args";
            stringArr0[5] = "outputDir";
            stringArr0[6] = "args";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_compileXmlForAapt2_closure5.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_compileXmlForAapt2_closure5.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$callSiteArray != null ? TinkerResourceIdTask$_compileXmlForAapt2_closure5.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_compileXmlForAapt2_closure5.$callSiteArray.get();
            TinkerResourceIdTask$_compileXmlForAapt2_closure5.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_compileXmlForAapt2_closure5
    public final class TinkerResourceIdTask$_compileXmlForAapt2_closure5 implements GeneratedClosure {
        private synthetic Reference aapt2Path;
        private synthetic Reference mergeResourcesTask;
        private synthetic Reference xmlFile;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_compileXmlForAapt2_closure5(Object _outerInstance, Object _thisObject, Reference aapt2Path, Reference mergeResourcesTask, Reference xmlFile) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            aapt2Path.aapt2Path = this;
            mergeResourcesTask.mergeResourcesTask = this;
            xmlFile.xmlFile = this;
        }

        public Object doCall(Object execSpec) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$getCallSiteArray();
            siteArr0[0].call(execSpec, new GStringImpl(new Object[]{this.aapt2Path.get()}, new String[]{"", ""}));
            siteArr0[1].call(execSpec, "compile");
            siteArr0[2].call(execSpec, "--legacy");
            siteArr0[3].call(execSpec, "-o");
            siteArr0[4].call(execSpec, new GStringImpl(new Object[]{siteArr0[5].callGetProperty(this.mergeResourcesTask.get())}, new String[]{"", ""}));
            return siteArr0[6].call(execSpec, new GStringImpl(new Object[]{this.xmlFile.get()}, new String[]{"", ""}));
        }

        @Generated
        public String getAapt2Path() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.aapt2Path.get());
        }

        @Generated
        public Object getMergeResourcesTask() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$getCallSiteArray();
            return this.mergeResourcesTask.get();
        }

        @Generated
        public File getXmlFile() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.xmlFile.get(), File.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_compileXmlForAapt2_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_compileXmlForAapt2_closure5.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_compileXmlForAapt2_closure5.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_compileXmlForAapt2_closure5.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "executable";
            stringArr0[1] = "args";
            stringArr0[2] = "args";
            stringArr0[3] = "args";
            stringArr0[4] = "args";
            stringArr0[5] = "outputDir";
            stringArr0[6] = "args";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_compileXmlForAapt2_closure5.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_compileXmlForAapt2_closure5.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_compileXmlForAapt2_closure5.$callSiteArray != null ? TinkerResourceIdTask$_compileXmlForAapt2_closure5.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_compileXmlForAapt2_closure5.$callSiteArray.get();
            TinkerResourceIdTask$_compileXmlForAapt2_closure5.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_applyResourceId_closure6
    public final class TinkerResourceIdTask$_applyResourceId_closure6 implements GeneratedClosure {
        private synthetic Reference stableIdsFile;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_applyResourceId_closure6(Object _outerInstance, Object _thisObject, Reference stableIdsFile) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure6.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            stableIdsFile.stableIdsFile = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure6.$getCallSiteArray();
            return siteArr0[0].call(this.stableIdsFile.get(), new GStringImpl(new Object[]{it}, new String[]{"", "
"}));
        }

        @Generated
        public File getStableIdsFile() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure6.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.stableIdsFile.get(), File.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure6.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_applyResourceId_closure6.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_applyResourceId_closure6.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_applyResourceId_closure6.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_applyResourceId_closure6.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "append";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_applyResourceId_closure6.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_applyResourceId_closure6.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_applyResourceId_closure6.$callSiteArray != null ? TinkerResourceIdTask$_applyResourceId_closure6.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_applyResourceId_closure6.$callSiteArray.get();
            TinkerResourceIdTask$_applyResourceId_closure6.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_applyResourceId_closure6
    public final class TinkerResourceIdTask$_applyResourceId_closure6 implements GeneratedClosure {
        private synthetic Reference stableIdsFile;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_applyResourceId_closure6(Object _outerInstance, Object _thisObject, Reference stableIdsFile) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure6.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            stableIdsFile.stableIdsFile = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure6.$getCallSiteArray();
            return siteArr0[0].call(this.stableIdsFile.get(), new GStringImpl(new Object[]{it}, new String[]{"", "
"}));
        }

        @Generated
        public File getStableIdsFile() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure6.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.stableIdsFile.get(), File.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure6.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_applyResourceId_closure6.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_applyResourceId_closure6.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_applyResourceId_closure6.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_applyResourceId_closure6.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "append";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_applyResourceId_closure6.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_applyResourceId_closure6.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_applyResourceId_closure6.$callSiteArray != null ? TinkerResourceIdTask$_applyResourceId_closure6.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_applyResourceId_closure6.$callSiteArray.get();
            TinkerResourceIdTask$_applyResourceId_closure6.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_applyResourceId_closure7
    public final class TinkerResourceIdTask$_applyResourceId_closure7 implements GeneratedClosure {
        private synthetic Reference processResourcesTask;
        private synthetic Reference stableIdsFile;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_applyResourceId_closure7(Object _outerInstance, Object _thisObject, Reference processResourcesTask, Reference stableIdsFile) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure7.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            processResourcesTask.processResourcesTask = this;
            stableIdsFile.stableIdsFile = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure7.$getCallSiteArray();
            Object aaptParams = siteArr0[0].callGetProperty(siteArr0[1].callGetProperty(siteArr0[2].callGetProperty(siteArr0[3].callGroovyObjectGetProperty(this))));
            if (ScriptBytecodeAdapter.compareNotEqual(aaptParams, null)) {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[4].call(aaptParams, "--stable-ids")) ? 0 : 1 != 0) {
                    siteArr0[5].callCurrent(this, this.processResourcesTask.get());
                }
                else {
                    siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), siteArr0[9].call(siteArr0[10].call("** [NOTICE] ** Manually specified stable-ids file was detected, ", "Tinker will give up injecting generated stable-ids file. Please ensure your stable-ids file "), "keep ids of all resources in base apk."));
                }
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[11].call(siteArr0[12].callGroovyObjectGetProperty(this), "tinker.aapt2.public"))) {
                Object object = siteArr0[13].callSafe(siteArr0[14].callSafe(siteArr0[15].call(siteArr0[16].callGetProperty(siteArr0[17].callGroovyObjectGetProperty(this)), "tinker.aapt2.public")));
                ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.valueOf(DefaultTypeTransformation.booleanUnbox(object)), TinkerResourceIdTask$_applyResourceId_closure7.class, this, (String)"addPublicFlagForAapt2");
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[18].callGroovyObjectGetProperty(this))) {
                File publicXmlFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[19].call(siteArr0[20].callGroovyObjectGetProperty(this), siteArr0[21].call(TinkerBuildPath.class, siteArr0[22].callGroovyObjectGetProperty(this))), File.class);
                siteArr0[23].callCurrent(this, this.stableIdsFile.get(), publicXmlFile, Boolean.valueOf(false));
                return siteArr0[24].callCurrent(this, publicXmlFile);
            }
            else {
                return null;
            }
        }

        @Generated
        public Object getProcessResourcesTask() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure7.$getCallSiteArray();
            return this.processResourcesTask.get();
        }

        @Generated
        public File getStableIdsFile() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure7.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.stableIdsFile.get(), File.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure7.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_applyResourceId_closure7.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_applyResourceId_closure7.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_applyResourceId_closure7.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_applyResourceId_closure7.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "additionalParameters";
            stringArr0[1] = "aaptOptions";
            stringArr0[2] = "android";
            stringArr0[3] = "project";
            stringArr0[4] = "contains";
            stringArr0[5] = "addStableIdsFileToAdditionalParameters";
            stringArr0[6] = "error";
            stringArr0[7] = "logger";
            stringArr0[8] = "project";
            stringArr0[9] = "plus";
            stringArr0[10] = "plus";
            stringArr0[11] = "hasProperty";
            stringArr0[12] = "project";
            stringArr0[13] = "toBoolean";
            stringArr0[14] = "toString";
            stringArr0[15] = "getAt";
            stringArr0[16] = "ext";
            stringArr0[17] = "project";
            stringArr0[18] = "addPublicFlagForAapt2";
            stringArr0[19] = "file";
            stringArr0[20] = "project";
            stringArr0[21] = "getResourceToCompilePublicXml";
            stringArr0[22] = "project";
            stringArr0[23] = "convertPublicTxtToPublicXml";
            stringArr0[24] = "compileXmlForAapt2";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_applyResourceId_closure7.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_applyResourceId_closure7.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_applyResourceId_closure7.$callSiteArray != null ? TinkerResourceIdTask$_applyResourceId_closure7.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_applyResourceId_closure7.$callSiteArray.get();
            TinkerResourceIdTask$_applyResourceId_closure7.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerResourceIdTask$_applyResourceId_closure7
    public final class TinkerResourceIdTask$_applyResourceId_closure7 implements GeneratedClosure {
        private synthetic Reference processResourcesTask;
        private synthetic Reference stableIdsFile;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerResourceIdTask$_applyResourceId_closure7(Object _outerInstance, Object _thisObject, Reference processResourcesTask, Reference stableIdsFile) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure7.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            processResourcesTask.processResourcesTask = this;
            stableIdsFile.stableIdsFile = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure7.$getCallSiteArray();
            Object aaptParams = siteArr0[0].callGetProperty(siteArr0[1].callGetProperty(siteArr0[2].callGetProperty(siteArr0[3].callGroovyObjectGetProperty(this))));
            if (ScriptBytecodeAdapter.compareNotEqual(aaptParams, null)) {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[4].call(aaptParams, "--stable-ids")) ? 0 : 1 != 0) {
                    siteArr0[5].callCurrent(this, this.processResourcesTask.get());
                }
                else {
                    siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), siteArr0[9].call(siteArr0[10].call("** [NOTICE] ** Manually specified stable-ids file was detected, ", "Tinker will give up injecting generated stable-ids file. Please ensure your stable-ids file "), "keep ids of all resources in base apk."));
                }
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[11].call(siteArr0[12].callGroovyObjectGetProperty(this), "tinker.aapt2.public"))) {
                Object object = siteArr0[13].callSafe(siteArr0[14].callSafe(siteArr0[15].call(siteArr0[16].callGetProperty(siteArr0[17].callGroovyObjectGetProperty(this)), "tinker.aapt2.public")));
                ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.valueOf(DefaultTypeTransformation.booleanUnbox(object)), TinkerResourceIdTask$_applyResourceId_closure7.class, this, (String)"addPublicFlagForAapt2");
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[18].callGroovyObjectGetProperty(this))) {
                File publicXmlFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[19].call(siteArr0[20].callGroovyObjectGetProperty(this), siteArr0[21].call(TinkerBuildPath.class, siteArr0[22].callGroovyObjectGetProperty(this))), File.class);
                siteArr0[23].callCurrent(this, this.stableIdsFile.get(), publicXmlFile, Boolean.valueOf(false));
                return siteArr0[24].callCurrent(this, publicXmlFile);
            }
            else {
                return null;
            }
        }

        @Generated
        public Object getProcessResourcesTask() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure7.$getCallSiteArray();
            return this.processResourcesTask.get();
        }

        @Generated
        public File getStableIdsFile() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure7.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.stableIdsFile.get(), File.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerResourceIdTask$_applyResourceId_closure7.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerResourceIdTask$_applyResourceId_closure7.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerResourceIdTask$_applyResourceId_closure7.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerResourceIdTask$_applyResourceId_closure7.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerResourceIdTask$_applyResourceId_closure7.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "additionalParameters";
            stringArr0[1] = "aaptOptions";
            stringArr0[2] = "android";
            stringArr0[3] = "project";
            stringArr0[4] = "contains";
            stringArr0[5] = "addStableIdsFileToAdditionalParameters";
            stringArr0[6] = "error";
            stringArr0[7] = "logger";
            stringArr0[8] = "project";
            stringArr0[9] = "plus";
            stringArr0[10] = "plus";
            stringArr0[11] = "hasProperty";
            stringArr0[12] = "project";
            stringArr0[13] = "toBoolean";
            stringArr0[14] = "toString";
            stringArr0[15] = "getAt";
            stringArr0[16] = "ext";
            stringArr0[17] = "project";
            stringArr0[18] = "addPublicFlagForAapt2";
            stringArr0[19] = "file";
            stringArr0[20] = "project";
            stringArr0[21] = "getResourceToCompilePublicXml";
            stringArr0[22] = "project";
            stringArr0[23] = "convertPublicTxtToPublicXml";
            stringArr0[24] = "compileXmlForAapt2";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerResourceIdTask$_applyResourceId_closure7.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerResourceIdTask$_applyResourceId_closure7.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerResourceIdTask$_applyResourceId_closure7.$callSiteArray != null ? TinkerResourceIdTask$_applyResourceId_closure7.$createCallSiteArray() : (CallSiteArray)TinkerResourceIdTask$_applyResourceId_closure7.$callSiteArray.get();
            TinkerResourceIdTask$_applyResourceId_closure7.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
