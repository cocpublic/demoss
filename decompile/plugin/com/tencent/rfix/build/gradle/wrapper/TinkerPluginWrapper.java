/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/wrapper;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import org.gradle.api.Project;
import org.gradle.api.Task;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import com.tencent.rfix.build.gradle.extension.RFixPatchExtension;
import com.tencent.rfix.build.gradle.task.RFixManifestAction;
import com.tencent.rfix.build.gradle.task.RFixResourceIdTask;
import com.tencent.rfix.build.gradle.task.RFixPatchPackageTask;
import com.tencent.tinker.build.gradle.TinkerPatchPlugin;
import com.android.build.gradle.api.ApkVariant;
import java.util.List;
import java.util.Iterator;

// class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper
public class TinkerPluginWrapper implements IPluginWrapper, GroovyObject {
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public TinkerPluginWrapper() {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public void afterEvaluate(Project project) {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        siteArr0[0].callCurrent(this, project);
        siteArr0[1].callCurrent(this, project);
        siteArr0[2].callCurrent(this, project);
    }

    protected void initTinkerConfig(Project project) {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        RFixPatchExtension patchExtension = (RFixPatchExtension)ScriptBytecodeAdapter.asType(siteArr0[3].callGetProperty(siteArr0[4].callGetProperty(project)), RFixPatchExtension.class);
        Object object = siteArr0[5].call(siteArr0[6].call(siteArr0[7].callGetProperty(patchExtension), "\|"), siteArr0[8].callGetProperty(RFixConstants.class));
        ScriptBytecodeAdapter.setProperty(object, null, patchExtension, (String)"tinkerEnable");
        int i0 = true;
        ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i0), null, patchExtension, (String)"allowLoaderInAnyDex");
        int i1 = true;
        ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i1), null, patchExtension, (String)"removeLoaderForAllDex");
        int i2 = false;
        ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i2), null, patchExtension, (String)"useSign");
        Object objectVar1 = siteArr0[9].callGetProperty(siteArr0[10].callGetProperty(patchExtension));
        ScriptBytecodeAdapter.setProperty(objectVar1, null, siteArr0[11].callGetProperty(patchExtension), (String)"tinkerId");
        Object objectVar2 = siteArr0[12].callGetProperty(siteArr0[13].callGetProperty(patchExtension));
        ScriptBytecodeAdapter.setProperty(objectVar2, null, siteArr0[14].callGetProperty(patchExtension), (String)"appendOutputNameToTinkerId");
        siteArr0[15].call(siteArr0[16].callGetProperty(project), "tinkerPatch", patchExtension);
        Object extendEnable = siteArr0[17].callGetProperty(siteArr0[18].callGetProperty(patchExtension));
        Object customDiffDecoder = siteArr0[19].callGetProperty(siteArr0[20].callGetProperty(patchExtension));
        ScriptBytecodeAdapter.setProperty(extendEnable, null, ExtendConfiguration.class, (String)"sExtendEnable");
        ScriptBytecodeAdapter.setProperty(customDiffDecoder, null, ExtendConfiguration.class, (String)"sCustomDiffDecoder");
        siteArr0[21].call(siteArr0[22].callGetProperty(project), new GStringImpl(new Object[]{extendEnable, customDiffDecoder}, new String[]{"initTinkerConfig extendEnable=", " customDiffDecoder=", ""}));
    }

    protected void printTinkerWarning(Project project) {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        RFixPatchExtension tinkerPatch = (RFixPatchExtension)ScriptBytecodeAdapter.asType(siteArr0[23].callGetProperty(siteArr0[24].callGetProperty(project)), RFixPatchExtension.class);
        siteArr0[25].call(siteArr0[26].callGetProperty(project), "----------------------tinker build warning ------------------------------------");
        siteArr0[27].call(siteArr0[28].callGetProperty(project), "tinker auto operation: ");
        siteArr0[29].call(siteArr0[30].callGetProperty(project), "excluding annotation processor and source template from app packaging. Enable dx jumboMode to reduce package size.");
        siteArr0[31].call(siteArr0[32].callGetProperty(project), "enable dx jumboMode to reduce package size.");
        siteArr0[33].call(siteArr0[34].callGetProperty(project), "disable preDexLibraries to prevent ClassDefNotFoundException when your app is booting.");
        siteArr0[35].call(siteArr0[36].callGetProperty(project), "disable archive dex mode so far for keeping dex apply.");
        siteArr0[37].call(siteArr0[38].callGetProperty(project), "");
        siteArr0[39].call(siteArr0[40].callGetProperty(project), "tinker will change your build configs:");
        siteArr0[41].call(siteArr0[42].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[43].callGetProperty(siteArr0[44].callGetProperty(tinkerPatch)), siteArr0[45].callGetProperty(project)}, new String[]{"we will add TINKER_ID=", " in your build output manifest file ", "/intermediates/manifests/full/*"}));
        siteArr0[46].call(siteArr0[47].callGetProperty(project), "");
        siteArr0[48].call(siteArr0[49].callGetProperty(project), "if minifyEnabled is true");
        String tempMappingPath = (String)ShortTypeHandling.castToString(siteArr0[50].callGetProperty(siteArr0[51].callGetProperty(tinkerPatch)));
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[52].call(PatchFileUtils.class, tempMappingPath))) {
            siteArr0[53].call(siteArr0[54].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[55].call(project), tempMappingPath}, new String[]{"we will build ", " apk with apply mapping file ", ""}));
        }
        siteArr0[56].call(siteArr0[57].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[58].call(TinkerBuildPath.class, project)}, new String[]{"you will find the gen proguard rule file at ", ""}));
        siteArr0[59].call(siteArr0[60].callGetProperty(project), "and we will help you to put it in the proguardFiles.");
        siteArr0[61].call(siteArr0[62].callGetProperty(project), "");
        siteArr0[63].call(siteArr0[64].callGetProperty(project), "if multiDexEnabled is true");
        siteArr0[65].call(siteArr0[66].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[67].call(TinkerBuildPath.class, project)}, new String[]{"you will find the gen multiDexKeepProguard file at ", ""}));
        siteArr0[68].call(siteArr0[69].callGetProperty(project), "and we will help you to put it in the MultiDexKeepProguardFile.");
        siteArr0[70].call(siteArr0[71].callGetProperty(project), "");
        siteArr0[72].call(siteArr0[73].callGetProperty(project), "if applyResourceMapping file is exist");
        String tempResourceMappingPath = (String)ShortTypeHandling.castToString(siteArr0[74].callGetProperty(siteArr0[75].callGetProperty(tinkerPatch)));
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[76].call(PatchFileUtils.class, tempResourceMappingPath))) {
            siteArr0[77].call(siteArr0[78].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[79].call(project), tempResourceMappingPath}, new String[]{"we will build ", " apk with resource R.txt ", " file"}));
        }
        else {
            siteArr0[80].call(siteArr0[81].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[82].call(project)}, new String[]{"we will build ", " apk with resource R.txt file"}));
        }
        siteArr0[83].call(siteArr0[84].callGetProperty(project), "if resources.arsc has changed, you should use applyResource mode to build the new apk!");
        siteArr0[85].call(siteArr0[86].callGetProperty(project), "-----------------------------------------------------------------");
    }

    protected void applyTinkerTask(Project project) {
        v_1 = alloc(Reference);
        new project.<init>(v_1);
        Reference reference = v_1;
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        Object android = siteArr0[87].callGetProperty(siteArr0[88].callGetProperty((Project)reference.get()));
        v_29 = alloc(Reference);
        new (RFixPatchExtension)ScriptBytecodeAdapter.asType(siteArr0[89].callGetProperty(siteArr0[90].callGetProperty((Project)reference.get())), RFixPatchExtension.class).<init>(v_29);
        Reference tinkerPatchConfig = v_29;
        v_39 = alloc(Reference);
        new siteArr0[91].callCurrent(this, (Project)reference.get()).<init>(v_39);
        Reference tinkerPatchPlugin = v_39;
        siteArr0[92].call(siteArr0[93].callGetProperty(android), new TinkerPluginWrapper$_applyTinkerTask_closure1(this, this, reference, tinkerPatchPlugin, tinkerPatchConfig));
        siteArr0[94].call(siteArr0[95].callGetProperty((Project)reference.get()), "RFixSignPatch", RFixSignPatchTask.class);
    }

    protected TinkerPatchPlugin createTinkerPatchPlugin(Project project) {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        Object plugin = siteArr0[96].callConstructor(TinkerPatchPlugin.class);
        Class clazz = Class.forName((String)ShortTypeHandling.castToString(siteArr0[97].callGetProperty(TinkerPatchPlugin.class)));
        Object field = siteArr0[98].call(clazz, "mProject");
        siteArr0[99].call(field, Boolean.valueOf(true));
        siteArr0[100].call(field, plugin, project);
        return (TinkerPatchPlugin)ScriptBytecodeAdapter.castToType(plugin, TinkerPatchPlugin.class);
    }

    protected void applyBuildTaskConfig(Project project, ApkVariant variant, TinkerPatchPlugin tinkerPatchPlugin, RFixPatchExtension patchExtension) {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        Object variantName = siteArr0[101].callGetProperty(variant);
        Object capitalizedVariantName = siteArr0[102].call(variantName);
        Object processManifestTask = siteArr0[103].call(Compatibilities.class, project, variant);
        Object processResourcesTask = siteArr0[104].call(siteArr0[105].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"process", "Resources"}));
        siteArr0[106].callCurrent(this, project, variant, processManifestTask);
        siteArr0[107].callCurrent(this, project, variant, capitalizedVariantName, processResourcesTask);
        siteArr0[108].callCurrent(this, project, variant, capitalizedVariantName, processManifestTask);
        siteArr0[109].callCurrent(this, project, variant);
        siteArr0[110].callCurrent(this, project, patchExtension, variant);
        Object proguardJavaResFix = siteArr0[111].callConstructor(ProguardJavaResFix.class, project, patchExtension, variant);
        siteArr0[112].call(proguardJavaResFix);
    }

    protected void createManifestTask(Project project, ApkVariant variant, Task processManifestTask) {
        v_1 = alloc(Reference);
        new project.<init>(v_1);
        Reference reference = v_1;
        v_3 = alloc(Reference);
        new processManifestTask.<init>(v_3);
        Reference referenceVar1 = v_3;
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        v_16 = alloc(Reference);
        new (RFixManifestAction)ScriptBytecodeAdapter.castToType(siteArr0[113].callConstructor(RFixManifestAction.class, (Project)reference.get()), RFixManifestAction.class).<init>(v_16);
        Reference manifestAction = v_16;
        siteArr0[114].call((Task)referenceVar1.get(), (RFixManifestAction)manifestAction.get());
        siteArr0[115].call(siteArr0[116].callGetProperty(variant), new TinkerPluginWrapper$_createManifestTask_closure2(this, this, manifestAction, reference, referenceVar1));
        if (! ScriptBytecodeAdapter.compareEqual(siteArr0[117].callGroovyObjectGetProperty((RFixManifestAction)manifestAction.get()), null) || DefaultTypeTransformation.booleanUnbox(siteArr0[118].call(siteArr0[119].callGroovyObjectGetProperty((RFixManifestAction)manifestAction.get()))) ? 0 : 1 != 0) {
            throw (Throwable)siteArr0[120].callConstructor(GradleException.class, "manifestAction.manifestPath is null.");
        }
        else {
        }
    }

    private void createCacheExpireTask(Project project, ApkVariant variant, String capitalizedVariantName, Task processManifestTask) {
        v_1 = alloc(Reference);
        new project.<init>(v_1);
        Reference reference = v_1;
        v_3 = alloc(Reference);
        new variant.<init>(v_3);
        Reference referenceVar1 = v_3;
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        GStringImpl taskName = new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"RFixCacheExpire", ""});
        v_35 = alloc(Reference);
        new siteArr0[121].call(siteArr0[122].callGetProperty((Project)reference.get()), taskName, RFixCacheExpireTask.class, new TinkerPluginWrapper$_createCacheExpireTask_closure3(this, this, reference, referenceVar1)).<init>(v_35);
        Reference cacheExpireTask = v_35;
        siteArr0[123].call(processManifestTask, cacheExpireTask.get());
        siteArr0[124].call(siteArr0[125].callGetProperty(processManifestTask), new TinkerPluginWrapper$_createCacheExpireTask_closure4(this, this, cacheExpireTask));
    }

    protected void createResourceIdTask(Project project, ApkVariant variant, String capitalizedVariantName, Task processResourcesTask) {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        GStringImpl taskName = new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"RFixKeepResource", ""});
        RFixResourceIdTask keepResourceIdTask = (RFixResourceIdTask)ScriptBytecodeAdapter.castToType(siteArr0[126].call(siteArr0[127].callGetProperty(project), taskName, RFixResourceIdTask.class), RFixResourceIdTask.class);
        Object object = siteArr0[128].call(Compatibilities.class, project, variant);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object, TinkerPluginWrapper.class, keepResourceIdTask, (String)"applicationId");
        ScriptBytecodeAdapter.setGroovyObjectProperty(variant, TinkerPluginWrapper.class, keepResourceIdTask, (String)"variant");
        Object objectVar1 = siteArr0[129].call(Compatibilities.class, project, processResourcesTask);
        ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar1, TinkerPluginWrapper.class, keepResourceIdTask, (String)"resDir");
        if (ScriptBytecodeAdapter.compareEqual(siteArr0[130].callGroovyObjectGetProperty(keepResourceIdTask), null)) {
            throw (Throwable)siteArr0[131].callConstructor(GradleException.class, "keepResourceIdTask.resDir is null.");
        }
        else {
            siteArr0[132].call(processResourcesTask, keepResourceIdTask);
            Object mergeResourcesTask = siteArr0[133].call(siteArr0[134].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"merge", "Resources"}));
            siteArr0[135].call(keepResourceIdTask, mergeResourcesTask);
        }
    }

    protected void createProguardConfigTask(Project project, ApkVariant variant) {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        boolean proguardEnable = DefaultTypeTransformation.booleanUnbox(siteArr0[136].callGetProperty(siteArr0[137].callGetProperty(siteArr0[138].call(variant))));
        if (proguardEnable) {
            Object obfuscateTask = siteArr0[139].call(Compatibilities.class, project, variant);
            siteArr0[140].call(obfuscateTask, siteArr0[141].callConstructor(TinkerProguardConfigAction.class, variant));
        }
    }

    protected void applyKeepDex(Project project, RFixPatchExtension tinkerPatchConfig, ApkVariant variant) {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[142].callGetProperty(siteArr0[143].callGroovyObjectGetProperty(tinkerPatchConfig))) && DefaultTypeTransformation.booleanUnbox(siteArr0[144].call(PatchFileUtils.class, siteArr0[145].callGroovyObjectGetProperty(tinkerPatchConfig))) ? 0 : 1 != 0) {
            siteArr0[146].call(ImmutableDexTransform.class, project, variant);
        }
    }

    protected void applyPatchTaskConfig(Project project, ApkVariant variant, RFixPatchExtension patchExtension) {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[147].call(siteArr0[148].callGroovyObjectGetProperty(patchExtension))) && DefaultTypeTransformation.booleanUnbox(siteArr0[149].call(siteArr0[150].callGroovyObjectGetProperty(patchExtension))) ? 0 : 1 != 0) {
            Object oldApks = siteArr0[151].callConstructor(ArrayList.class);
            Object newApks = siteArr0[152].callConstructor(ArrayList.class);
            if (BytecodeInterface8.isOrigZ() && TinkerPluginWrapper.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 231;
                if (ScriptBytecodeAdapter.compareNotEqual(siteArr0[159].callGroovyObjectGetProperty(patchExtension), null) && ScriptBytecodeAdapter.compareNotEqual(siteArr0[160].callGroovyObjectGetProperty(patchExtension), null) ? 0 : 1 != 0) {
                    siteArr0[161].call(oldApks, siteArr0[162].callGroovyObjectGetProperty(patchExtension));
                    siteArr0[163].call(newApks, siteArr0[164].callGroovyObjectGetProperty(patchExtension));
                }
            }
            else {
                if (ScriptBytecodeAdapter.compareNotEqual(siteArr0[153].callGroovyObjectGetProperty(patchExtension), null) && ScriptBytecodeAdapter.compareNotEqual(siteArr0[154].callGroovyObjectGetProperty(patchExtension), null) ? 0 : 1 != 0) {
                    siteArr0[155].call(oldApks, siteArr0[156].callGroovyObjectGetProperty(patchExtension));
                    siteArr0[157].call(newApks, siteArr0[158].callGroovyObjectGetProperty(patchExtension));
                }
            }
            if (BytecodeInterface8.isOrigZ() && TinkerPluginWrapper.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 451;
                if (ScriptBytecodeAdapter.compareNotEqual(siteArr0[171].callGroovyObjectGetProperty(patchExtension), null) && ScriptBytecodeAdapter.compareNotEqual(siteArr0[172].callGroovyObjectGetProperty(patchExtension), null) ? 0 : 1 != 0) {
                    siteArr0[173].call(oldApks, siteArr0[174].callGroovyObjectGetProperty(patchExtension));
                    siteArr0[175].call(newApks, siteArr0[176].callGroovyObjectGetProperty(patchExtension));
                }
            }
            else {
                if (ScriptBytecodeAdapter.compareNotEqual(siteArr0[165].callGroovyObjectGetProperty(patchExtension), null) && ScriptBytecodeAdapter.compareNotEqual(siteArr0[166].callGroovyObjectGetProperty(patchExtension), null) ? 0 : 1 != 0) {
                    siteArr0[167].call(oldApks, siteArr0[168].callGroovyObjectGetProperty(patchExtension));
                    siteArr0[169].call(newApks, siteArr0[170].callGroovyObjectGetProperty(patchExtension));
                }
            }
            ScriptBytecodeAdapter.setGroovyObjectProperty(oldApks, TinkerPluginWrapper.class, patchExtension, (String)"oldApks");
            ScriptBytecodeAdapter.setGroovyObjectProperty(newApks, TinkerPluginWrapper.class, patchExtension, (String)"newApks");
        }
        siteArr0[177].call(siteArr0[178].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[179].callGroovyObjectGetProperty(patchExtension)}, new String[]{"oldApks=", ""}));
        siteArr0[180].call(siteArr0[181].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[182].callGroovyObjectGetProperty(patchExtension)}, new String[]{"newApks=", ""}));
        if (! ScriptBytecodeAdapter.compareNotEqual(siteArr0[183].call(siteArr0[184].callGroovyObjectGetProperty(patchExtension)), siteArr0[185].call(siteArr0[186].callGroovyObjectGetProperty(patchExtension))) || DefaultTypeTransformation.booleanUnbox(siteArr0[187].call(siteArr0[188].callGroovyObjectGetProperty(patchExtension))) ? 0 : 1 != 0 || DefaultTypeTransformation.booleanUnbox(siteArr0[189].call(siteArr0[190].callGroovyObjectGetProperty(patchExtension))) ? 0 : 1 != 0) {
            throw (Throwable)siteArr0[191].callConstructor(RuntimeException.class, "'oldApks' and 'newApks' need the same size, and not empty!");
        }
        else {
            List packageTasks = (List)ScriptBytecodeAdapter.castToType(siteArr0[192].callConstructor(ArrayList.class), List.class);
            Object patchType = null;
            Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[193].call(siteArr0[194].call(siteArr0[195].callGroovyObjectGetProperty(patchExtension), "\|")), Iterator.class);
            while (iterator.hasNext()) {
                patchType = iterator.next();
                Object patchTasks = siteArr0[196].callConstructor(ArrayList.class);
                Object outputFolders = siteArr0[197].callConstructor(ArrayList.class);
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[198].call(siteArr0[199].call(EngineManager.class), patchType)) ? 0 : 1 != 0) {
                    continue;;
                }
                else {
                    Object length = siteArr0[200].call(siteArr0[201].callGroovyObjectGetProperty(patchExtension));
                    v_360 = 0;
                    int index = 0;
                    while (ScriptBytecodeAdapter.compareLessThan(Integer.valueOf(index), length)) {
                        Object taskName = null;
                        GStringImpl implVar1;
                        if (ScriptBytecodeAdapter.compareEqual(patchType, siteArr0[202].callGetProperty(RFixConstants.class))) {
                            GStringImpl impl = new GStringImpl(new Object[]{siteArr0[203].call(siteArr0[204].callGetProperty(variant)), Integer.valueOf(index)}, new String[]{"RFixPatchQFix", "", ""});
                            continue;;
                        }
                        else if (ScriptBytecodeAdapter.compareEqual(patchType, siteArr0[205].callGetProperty(RFixConstants.class))) {
                            GStringImpl implVar2 = new GStringImpl(new Object[]{siteArr0[206].call(siteArr0[207].callGetProperty(variant)), Integer.valueOf(index)}, new String[]{"RFixPatchRedirect", "", ""});
                            implVar1 = implVar2;
                            continue;;
                        }
                        else if (ScriptBytecodeAdapter.compareEqual(patchType, siteArr0[208].callGetProperty(RFixConstants.class))) {
                            GStringImpl implVar3 = new GStringImpl(new Object[]{siteArr0[209].call(siteArr0[210].callGetProperty(variant)), Integer.valueOf(index)}, new String[]{"RFixPatchTinker", "", ""});
                            implVar1 = implVar3;
                        }
                        Object oldApk = siteArr0[211].call(siteArr0[212].callGroovyObjectGetProperty(patchExtension), Integer.valueOf(index));
                        Object newApk = siteArr0[213].call(siteArr0[214].callGroovyObjectGetProperty(patchExtension), Integer.valueOf(index));
                        Object engine = siteArr0[215].call(siteArr0[216].call(EngineManager.class), patchType);
                        Object task = siteArr0[217].call(engine, ArrayUtil.createArray(project, impl, Integer.valueOf(index), oldApk, newApk, variant));
                        siteArr0[218].call(patchTasks, task);
                        siteArr0[219].call(outputFolders, siteArr0[220].call(task));
                        int i0 = index;
                        v_471 = v_528.call(Integer.valueOf(siteArr0[221]));
                        index = DefaultTypeTransformation.intUnbox(v_528.call(Integer.valueOf(siteArr0[221])));
                    }
                    Object packageTask = siteArr0[222].callCurrent(this, ArrayUtil.createArray(project, variant, patchExtension, patchType, patchTasks, outputFolders));
                    siteArr0[223].call(packageTasks, packageTask);
                    continue;;
                }
            }
            siteArr0[224].callCurrent(this, packageTasks, variant, project);
        }
    }

    protected Task createPackageTask(Project project, ApkVariant variant, RFixPatchExtension patchExtension, String patchType, List<Task> patchTasks, List<String> inputFolders) {
        v_1 = alloc(Reference);
        new variant.<init>(v_1);
        Reference reference = v_1;
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        Object signingConfig = siteArr0[225].callGetProperty((ApkVariant)reference.get());
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[226].call(siteArr0[227].callGroovyObjectGetProperty(patchExtension)))) {
            Object object = siteArr0[228].call(siteArr0[229].callGroovyObjectGetProperty(patchExtension));
        }
        GStringImpl taskName = new GStringImpl(new Object[]{patchType, siteArr0[230].call(siteArr0[231].callGetProperty((ApkVariant)reference.get()))}, new String[]{"RFixPackage", "", ""});
        v_69 = alloc(Reference);
        new siteArr0[232].call(siteArr0[233].callGetProperty(project), taskName, RFixPatchPackageTask.class).<init>(v_69);
        Reference packageTask = v_69;
        siteArr0[234].call(packageTask.get(), patchType);
        siteArr0[235].call(packageTask.get(), siteArr0[236].callGetProperty(siteArr0[237].callGroovyObjectGetProperty(patchExtension)));
        siteArr0[238].call(packageTask.get(), object);
        siteArr0[239].call(packageTask.get(), inputFolders);
        siteArr0[240].call(siteArr0[241].callGetProperty((ApkVariant)reference.get()), new TinkerPluginWrapper$_createPackageTask_closure5(this, this, packageTask, reference));
        Object lastTask = null;
        Object task = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[242].call(patchTasks), Iterator.class);
        while (iterator.hasNext()) {
            task = iterator.next();
            if (ScriptBytecodeAdapter.compareNotEqual(lastTask, null)) {
                siteArr0[243].call(task, lastTask);
            }
            Object objectVar1 = task;
        }
        if (ScriptBytecodeAdapter.compareNotEqual(objectVar1, null)) {
            siteArr0[244].call(packageTask.get(), objectVar1);
        }
        return (Task)ScriptBytecodeAdapter.castToType(packageTask.get(), Task.class);
    }

    protected Task createBuildTask(List<Task> packageTasks, ApkVariant variant, Project project) {
        CallSite[] siteArr0 = TinkerPluginWrapper.$getCallSiteArray();
        GStringImpl taskName = new GStringImpl(new Object[]{siteArr0[245].call(siteArr0[246].callGetProperty(variant))}, new String[]{"RFixBuild", ""});
        Object buildTask = siteArr0[247].call(siteArr0[248].callGetProperty(project), taskName);
        Object object = siteArr0[249].callGetProperty(RFixPatchPlugin.class);
        ScriptBytecodeAdapter.setProperty(object, null, buildTask, (String)"group");
        int firstTask = true;
        Object task = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[250].call(packageTasks), Iterator.class);
        while (iterator.hasNext()) {
            task = iterator.next();
            RFixPatchPackageTask packageTask = (RFixPatchPackageTask)ScriptBytecodeAdapter.asType(task, RFixPatchPackageTask.class);
            siteArr0[251].call(packageTask, Boolean.valueOf(firstTask));
            siteArr0[252].call(buildTask, packageTask);
            int i0 = 0;
            firstTask = i0;
        }
        return (Task)ScriptBytecodeAdapter.castToType(null, Task.class);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerPluginWrapper.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerPluginWrapper.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerPluginWrapper.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerPluginWrapper.$staticClassInfo.getMetaClass();
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
        stringArr0[0] = "initTinkerConfig";
        stringArr0[1] = "printTinkerWarning";
        stringArr0[2] = "applyTinkerTask";
        stringArr0[3] = "RFixPatch";
        stringArr0[4] = "extensions";
        stringArr0[5] = "contains";
        stringArr0[6] = "split";
        stringArr0[7] = "patchType";
        stringArr0[8] = "PATCH_TYPE_TINKER";
        stringArr0[9] = "patchId";
        stringArr0[10] = "buildConfig";
        stringArr0[11] = "buildConfig";
        stringArr0[12] = "appendOutputNameToPatchId";
        stringArr0[13] = "buildConfig";
        stringArr0[14] = "buildConfig";
        stringArr0[15] = "add";
        stringArr0[16] = "extensions";
        stringArr0[17] = "enable";
        stringArr0[18] = "extend";
        stringArr0[19] = "customDiffDecoder";
        stringArr0[20] = "extend";
        stringArr0[21] = "warn";
        stringArr0[22] = "logger";
        stringArr0[23] = "tinkerPatch";
        stringArr0[24] = "extensions";
        stringArr0[25] = "error";
        stringArr0[26] = "logger";
        stringArr0[27] = "error";
        stringArr0[28] = "logger";
        stringArr0[29] = "error";
        stringArr0[30] = "logger";
        stringArr0[31] = "error";
        stringArr0[32] = "logger";
        stringArr0[33] = "error";
        stringArr0[34] = "logger";
        stringArr0[35] = "error";
        stringArr0[36] = "logger";
        stringArr0[37] = "error";
        stringArr0[38] = "logger";
        stringArr0[39] = "error";
        stringArr0[40] = "logger";
        stringArr0[41] = "error";
        stringArr0[42] = "logger";
        stringArr0[43] = "tinkerId";
        stringArr0[44] = "buildConfig";
        stringArr0[45] = "buildDir";
        stringArr0[46] = "error";
        stringArr0[47] = "logger";
        stringArr0[48] = "error";
        stringArr0[49] = "logger";
        stringArr0[50] = "applyMapping";
        stringArr0[51] = "buildConfig";
        stringArr0[52] = "isLegalFile";
        stringArr0[53] = "error";
        stringArr0[54] = "logger";
        stringArr0[55] = "getName";
        stringArr0[56] = "error";
        stringArr0[57] = "logger";
        stringArr0[58] = "getProguardConfigPath";
        stringArr0[59] = "error";
        stringArr0[60] = "logger";
        stringArr0[61] = "error";
        stringArr0[62] = "logger";
        stringArr0[63] = "error";
        stringArr0[64] = "logger";
        stringArr0[65] = "error";
        stringArr0[66] = "logger";
        stringArr0[67] = "getMultidexConfigPath";
        stringArr0[68] = "error";
        stringArr0[69] = "logger";
        stringArr0[70] = "error";
        stringArr0[71] = "logger";
        stringArr0[72] = "error";
        stringArr0[73] = "logger";
        stringArr0[74] = "applyResourceMapping";
        stringArr0[75] = "buildConfig";
        stringArr0[76] = "isLegalFile";
        stringArr0[77] = "error";
        stringArr0[78] = "logger";
        stringArr0[79] = "getName";
        stringArr0[80] = "error";
        stringArr0[81] = "logger";
        stringArr0[82] = "getName";
        stringArr0[83] = "error";
        stringArr0[84] = "logger";
        stringArr0[85] = "error";
        stringArr0[86] = "logger";
        stringArr0[87] = "android";
        stringArr0[88] = "extensions";
        stringArr0[89] = "tinkerPatch";
        stringArr0[90] = "extensions";
        stringArr0[91] = "createTinkerPatchPlugin";
        stringArr0[92] = "all";
        stringArr0[93] = "applicationVariants";
        stringArr0[94] = "create";
        stringArr0[95] = "tasks";
        stringArr0[96] = "<$constructor$>";
        stringArr0[97] = "name";
        stringArr0[98] = "getDeclaredField";
        stringArr0[99] = "setAccessible";
        stringArr0[100] = "set";
        stringArr0[101] = "name";
        stringArr0[102] = "capitalize";
        stringArr0[103] = "getProcessManifestTask";
        stringArr0[104] = "findByName";
        stringArr0[105] = "tasks";
        stringArr0[106] = "createManifestTask";
        stringArr0[107] = "createResourceIdTask";
        stringArr0[108] = "createCacheExpireTask";
        stringArr0[109] = "createProguardConfigTask";
        stringArr0[110] = "applyKeepDex";
        stringArr0[111] = "<$constructor$>";
        stringArr0[112] = "fixProguardJavaRes";
        stringArr0[113] = "<$constructor$>";
        stringArr0[114] = "doLast";
        stringArr0[115] = "each";
        stringArr0[116] = "outputs";
        stringArr0[117] = "outputNameToManifestMap";
        stringArr0[118] = "isEmpty";
        stringArr0[119] = "outputNameToManifestMap";
        stringArr0[120] = "<$constructor$>";
        stringArr0[121] = "create";
        stringArr0[122] = "tasks";
        stringArr0[123] = "dependsOn";
        stringArr0[124] = "upToDateWhen";
        stringArr0[125] = "outputs";
        stringArr0[126] = "create";
        stringArr0[127] = "tasks";
        stringArr0[128] = "getApplicationId";
        stringArr0[129] = "getInputResourcesDirectory";
        stringArr0[130] = "resDir";
        stringArr0[131] = "<$constructor$>";
        stringArr0[132] = "dependsOn";
        stringArr0[133] = "findByName";
        stringArr0[134] = "tasks";
        stringArr0[135] = "dependsOn";
        stringArr0[136] = "minifyEnabled";
        stringArr0[137] = "buildType";
        stringArr0[138] = "getBuildType";
        stringArr0[139] = "getObfuscateTask";
        stringArr0[140] = "doFirst";
        stringArr0[141] = "<$constructor$>";
        stringArr0[142] = "keepDexApply";
        stringArr0[143] = "buildConfig";
        stringArr0[144] = "isLegalFile";
        stringArr0[145] = "oldApk";
        stringArr0[146] = "inject";
        stringArr0[147] = "isEmpty";
        stringArr0[148] = "oldApks";
        stringArr0[149] = "isEmpty";
        stringArr0[150] = "newApks";
        stringArr0[151] = "<$constructor$>";
        stringArr0[152] = "<$constructor$>";
        stringArr0[153] = "oldApk";
        stringArr0[154] = "newApk";
        stringArr0[155] = "add";
        stringArr0[156] = "oldApk";
        stringArr0[157] = "add";
        stringArr0[158] = "newApk";
        stringArr0[159] = "oldApk";
        stringArr0[160] = "newApk";
        stringArr0[161] = "add";
        stringArr0[162] = "oldApk";
        stringArr0[163] = "add";
        stringArr0[164] = "newApk";
        stringArr0[165] = "oldApkArm64";
        stringArr0[166] = "newApkArm64";
        stringArr0[167] = "add";
        stringArr0[168] = "oldApkArm64";
        stringArr0[169] = "add";
        stringArr0[170] = "newApkArm64";
        stringArr0[171] = "oldApkArm64";
        stringArr0[172] = "newApkArm64";
        stringArr0[173] = "add";
        stringArr0[174] = "oldApkArm64";
        stringArr0[175] = "add";
        stringArr0[176] = "newApkArm64";
        stringArr0[177] = "error";
        stringArr0[178] = "logger";
        stringArr0[179] = "oldApks";
        stringArr0[180] = "error";
        stringArr0[181] = "logger";
        stringArr0[182] = "newApks";
        stringArr0[183] = "size";
        stringArr0[184] = "oldApks";
        stringArr0[185] = "size";
        stringArr0[186] = "newApks";
        stringArr0[187] = "isEmpty";
        stringArr0[188] = "oldApks";
        stringArr0[189] = "isEmpty";
        stringArr0[190] = "newApks";
        stringArr0[191] = "<$constructor$>";
        stringArr0[192] = "<$constructor$>";
        stringArr0[193] = "iterator";
        stringArr0[194] = "split";
        stringArr0[195] = "patchType";
        stringArr0[196] = "<$constructor$>";
        stringArr0[197] = "<$constructor$>";
        stringArr0[198] = "isSupport";
        stringArr0[199] = "getInstance";
        stringArr0[200] = "size";
        stringArr0[201] = "oldApks";
        stringArr0[202] = "PATCH_TYPE_QFIX";
        stringArr0[203] = "capitalize";
        stringArr0[204] = "name";
        stringArr0[205] = "PATCH_TYPE_REDIRECT";
        stringArr0[206] = "capitalize";
        stringArr0[207] = "name";
        stringArr0[208] = "PATCH_TYPE_TINKER";
        stringArr0[209] = "capitalize";
        stringArr0[210] = "name";
        stringArr0[211] = "getAt";
        stringArr0[212] = "oldApks";
        stringArr0[213] = "getAt";
        stringArr0[214] = "newApks";
        stringArr0[215] = "getEngine";
        stringArr0[216] = "getInstance";
        stringArr0[217] = "createPatchTask";
        stringArr0[218] = "add";
        stringArr0[219] = "add";
        stringArr0[220] = "getOutputFolder";
        stringArr0[221] = "next";
        stringArr0[222] = "createPackageTask";
        stringArr0[223] = "add";
        stringArr0[224] = "createBuildTask";
        stringArr0[225] = "signingConfig";
        stringArr0[226] = "valid";
        stringArr0[227] = "signingConfig";
        stringArr0[228] = "toSigningConfig";
        stringArr0[229] = "signingConfig";
        stringArr0[230] = "capitalize";
        stringArr0[231] = "name";
        stringArr0[232] = "create";
        stringArr0[233] = "tasks";
        stringArr0[234] = "setPatchType";
        stringArr0[235] = "setPackageSeparateMode";
        stringArr0[236] = "enablePackageSeparate";
        stringArr0[237] = "buildConfig";
        stringArr0[238] = "setSigningConfig";
        stringArr0[239] = "setInputFolders";
        stringArr0[240] = "each";
        stringArr0[241] = "outputs";
        stringArr0[242] = "iterator";
        stringArr0[243] = "dependsOn";
        stringArr0[244] = "dependsOn";
        stringArr0[245] = "capitalize";
        stringArr0[246] = "name";
        stringArr0[247] = "create";
        stringArr0[248] = "tasks";
        stringArr0[249] = "GROUP";
        stringArr0[250] = "iterator";
        stringArr0[251] = "setDeleteOutputFolder";
        stringArr0[252] = "dependsOn";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerPluginWrapper.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerPluginWrapper.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerPluginWrapper.$callSiteArray != null ? TinkerPluginWrapper.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper.$callSiteArray.get();
        TinkerPluginWrapper.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper$_createCacheExpireTask_closure4
    public final class TinkerPluginWrapper$_createCacheExpireTask_closure4 implements GeneratedClosure {
        private synthetic Reference cacheExpireTask;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPluginWrapper$_createCacheExpireTask_closure4(Object _outerInstance, Object _thisObject, Reference cacheExpireTask) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure4.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            cacheExpireTask.cacheExpireTask = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure4.$getCallSiteArray();
            return Boolean.valueOf(DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(this.cacheExpireTask.get())) ? 0 : true);
        }

        @Generated
        public Object getCacheExpireTask() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure4.$getCallSiteArray();
            return this.cacheExpireTask.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure4.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPluginWrapper$_createCacheExpireTask_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPluginWrapper$_createCacheExpireTask_closure4.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPluginWrapper$_createCacheExpireTask_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPluginWrapper$_createCacheExpireTask_closure4.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "cacheExpired";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPluginWrapper$_createCacheExpireTask_closure4.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPluginWrapper$_createCacheExpireTask_closure4.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPluginWrapper$_createCacheExpireTask_closure4.$callSiteArray != null ? TinkerPluginWrapper$_createCacheExpireTask_closure4.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper$_createCacheExpireTask_closure4.$callSiteArray.get();
            TinkerPluginWrapper$_createCacheExpireTask_closure4.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper$_createCacheExpireTask_closure4
    public final class TinkerPluginWrapper$_createCacheExpireTask_closure4 implements GeneratedClosure {
        private synthetic Reference cacheExpireTask;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPluginWrapper$_createCacheExpireTask_closure4(Object _outerInstance, Object _thisObject, Reference cacheExpireTask) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure4.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            cacheExpireTask.cacheExpireTask = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure4.$getCallSiteArray();
            return Boolean.valueOf(DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(this.cacheExpireTask.get())) ? 0 : true);
        }

        @Generated
        public Object getCacheExpireTask() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure4.$getCallSiteArray();
            return this.cacheExpireTask.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure4.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPluginWrapper$_createCacheExpireTask_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPluginWrapper$_createCacheExpireTask_closure4.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPluginWrapper$_createCacheExpireTask_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPluginWrapper$_createCacheExpireTask_closure4.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "cacheExpired";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPluginWrapper$_createCacheExpireTask_closure4.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPluginWrapper$_createCacheExpireTask_closure4.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPluginWrapper$_createCacheExpireTask_closure4.$callSiteArray != null ? TinkerPluginWrapper$_createCacheExpireTask_closure4.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper$_createCacheExpireTask_closure4.$callSiteArray.get();
            TinkerPluginWrapper$_createCacheExpireTask_closure4.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper$_createPackageTask_closure5
    public final class TinkerPluginWrapper$_createPackageTask_closure5 implements GeneratedClosure {
        private synthetic Reference packageTask;
        private synthetic Reference variant;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPluginWrapper$_createPackageTask_closure5(Object _outerInstance, Object _thisObject, Reference packageTask, Reference variant) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createPackageTask_closure5.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            packageTask.packageTask = this;
            variant.variant = this;
        }

        public Object doCall(Object variantOutput) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createPackageTask_closure5.$getCallSiteArray();
            return siteArr0[0].call(this.packageTask.get(), this.variant.get(), variantOutput);
        }

        @Generated
        public Object getPackageTask() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createPackageTask_closure5.$getCallSiteArray();
            return this.packageTask.get();
        }

        @Generated
        public ApkVariant getVariant() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createPackageTask_closure5.$getCallSiteArray();
            return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPluginWrapper$_createPackageTask_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPluginWrapper$_createPackageTask_closure5.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPluginWrapper$_createPackageTask_closure5.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPluginWrapper$_createPackageTask_closure5.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "setOutputFolder";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPluginWrapper$_createPackageTask_closure5.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPluginWrapper$_createPackageTask_closure5.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPluginWrapper$_createPackageTask_closure5.$callSiteArray != null ? TinkerPluginWrapper$_createPackageTask_closure5.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper$_createPackageTask_closure5.$callSiteArray.get();
            TinkerPluginWrapper$_createPackageTask_closure5.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper$_createPackageTask_closure5
    public final class TinkerPluginWrapper$_createPackageTask_closure5 implements GeneratedClosure {
        private synthetic Reference packageTask;
        private synthetic Reference variant;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPluginWrapper$_createPackageTask_closure5(Object _outerInstance, Object _thisObject, Reference packageTask, Reference variant) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createPackageTask_closure5.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            packageTask.packageTask = this;
            variant.variant = this;
        }

        public Object doCall(Object variantOutput) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createPackageTask_closure5.$getCallSiteArray();
            return siteArr0[0].call(this.packageTask.get(), this.variant.get(), variantOutput);
        }

        @Generated
        public Object getPackageTask() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createPackageTask_closure5.$getCallSiteArray();
            return this.packageTask.get();
        }

        @Generated
        public ApkVariant getVariant() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createPackageTask_closure5.$getCallSiteArray();
            return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPluginWrapper$_createPackageTask_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPluginWrapper$_createPackageTask_closure5.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPluginWrapper$_createPackageTask_closure5.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPluginWrapper$_createPackageTask_closure5.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "setOutputFolder";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPluginWrapper$_createPackageTask_closure5.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPluginWrapper$_createPackageTask_closure5.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPluginWrapper$_createPackageTask_closure5.$callSiteArray != null ? TinkerPluginWrapper$_createPackageTask_closure5.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper$_createPackageTask_closure5.$callSiteArray.get();
            TinkerPluginWrapper$_createPackageTask_closure5.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper$_applyTinkerTask_closure1
    public final class TinkerPluginWrapper$_applyTinkerTask_closure1 implements GeneratedClosure {
        private synthetic Reference project;
        private synthetic Reference tinkerPatchPlugin;
        private synthetic Reference tinkerPatchConfig;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPluginWrapper$_applyTinkerTask_closure1(Object _outerInstance, Object _thisObject, Reference project, Reference tinkerPatchPlugin, Reference tinkerPatchConfig) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            project.project = this;
            tinkerPatchPlugin.tinkerPatchPlugin = this;
            tinkerPatchConfig.tinkerPatchConfig = this;
        }

        public Object doCall(ApkVariant variant) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            Object variantName = siteArr0[0].callGetProperty(variant);
            Object capitalizedVariantName = siteArr0[1].call(variantName);
            Object instantRunTask = siteArr0[2].call(Compatibilities.class, this.project.get(), variant);
            if (ScriptBytecodeAdapter.compareNotEqual(instantRunTask, null)) {
                throw (Throwable)siteArr0[3].callConstructor(GradleException.class, siteArr0[4].call("Tinker does not support instant run mode, please trigger build", new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{" by assemble", " or disable instant run in 'File->Settings...'."})));
            }
            else {
                siteArr0[5].callCurrent(this, this.project.get(), variant, this.tinkerPatchPlugin.get(), this.tinkerPatchConfig.get());
                return siteArr0[6].callCurrent(this, this.project.get(), variant, this.tinkerPatchConfig.get());
            }
        }

        public Object call(ApkVariant variant) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            return siteArr0[7].callCurrent(this, variant);
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public Object getTinkerPatchPlugin() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            return this.tinkerPatchPlugin.get();
        }

        @Generated
        public Object getTinkerPatchConfig() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            return this.tinkerPatchConfig.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPluginWrapper$_applyTinkerTask_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPluginWrapper$_applyTinkerTask_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPluginWrapper$_applyTinkerTask_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPluginWrapper$_applyTinkerTask_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "name";
            stringArr0[1] = "capitalize";
            stringArr0[2] = "getInstantRunTask";
            stringArr0[3] = "<$constructor$>";
            stringArr0[4] = "plus";
            stringArr0[5] = "applyBuildTaskConfig";
            stringArr0[6] = "applyPatchTaskConfig";
            stringArr0[7] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPluginWrapper$_applyTinkerTask_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPluginWrapper$_applyTinkerTask_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPluginWrapper$_applyTinkerTask_closure1.$callSiteArray != null ? TinkerPluginWrapper$_applyTinkerTask_closure1.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper$_applyTinkerTask_closure1.$callSiteArray.get();
            TinkerPluginWrapper$_applyTinkerTask_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper$_applyTinkerTask_closure1
    public final class TinkerPluginWrapper$_applyTinkerTask_closure1 implements GeneratedClosure {
        private synthetic Reference project;
        private synthetic Reference tinkerPatchPlugin;
        private synthetic Reference tinkerPatchConfig;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPluginWrapper$_applyTinkerTask_closure1(Object _outerInstance, Object _thisObject, Reference project, Reference tinkerPatchPlugin, Reference tinkerPatchConfig) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            project.project = this;
            tinkerPatchPlugin.tinkerPatchPlugin = this;
            tinkerPatchConfig.tinkerPatchConfig = this;
        }

        public Object doCall(ApkVariant variant) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            Object variantName = siteArr0[0].callGetProperty(variant);
            Object capitalizedVariantName = siteArr0[1].call(variantName);
            Object instantRunTask = siteArr0[2].call(Compatibilities.class, this.project.get(), variant);
            if (ScriptBytecodeAdapter.compareNotEqual(instantRunTask, null)) {
                throw (Throwable)siteArr0[3].callConstructor(GradleException.class, siteArr0[4].call("Tinker does not support instant run mode, please trigger build", new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{" by assemble", " or disable instant run in 'File->Settings...'."})));
            }
            else {
                siteArr0[5].callCurrent(this, this.project.get(), variant, this.tinkerPatchPlugin.get(), this.tinkerPatchConfig.get());
                return siteArr0[6].callCurrent(this, this.project.get(), variant, this.tinkerPatchConfig.get());
            }
        }

        public Object call(ApkVariant variant) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            return siteArr0[7].callCurrent(this, variant);
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public Object getTinkerPatchPlugin() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            return this.tinkerPatchPlugin.get();
        }

        @Generated
        public Object getTinkerPatchConfig() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_applyTinkerTask_closure1.$getCallSiteArray();
            return this.tinkerPatchConfig.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPluginWrapper$_applyTinkerTask_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPluginWrapper$_applyTinkerTask_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPluginWrapper$_applyTinkerTask_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPluginWrapper$_applyTinkerTask_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "name";
            stringArr0[1] = "capitalize";
            stringArr0[2] = "getInstantRunTask";
            stringArr0[3] = "<$constructor$>";
            stringArr0[4] = "plus";
            stringArr0[5] = "applyBuildTaskConfig";
            stringArr0[6] = "applyPatchTaskConfig";
            stringArr0[7] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPluginWrapper$_applyTinkerTask_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPluginWrapper$_applyTinkerTask_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPluginWrapper$_applyTinkerTask_closure1.$callSiteArray != null ? TinkerPluginWrapper$_applyTinkerTask_closure1.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper$_applyTinkerTask_closure1.$callSiteArray.get();
            TinkerPluginWrapper$_applyTinkerTask_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper$_createManifestTask_closure2
    public final class TinkerPluginWrapper$_createManifestTask_closure2 implements GeneratedClosure {
        private synthetic Reference manifestAction;
        private synthetic Reference project;
        private synthetic Reference processManifestTask;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPluginWrapper$_createManifestTask_closure2(Object _outerInstance, Object _thisObject, Reference manifestAction, Reference project, Reference processManifestTask) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createManifestTask_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            manifestAction.manifestAction = this;
            project.project = this;
            processManifestTask.processManifestTask = this;
        }

        public Object doCall(Object variantOutput) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createManifestTask_closure2.$getCallSiteArray();
            Object outputName = siteArr0[0].callGetProperty(variantOutput);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(outputName, "/"))) {
                Object object = siteArr0[2].call(outputName, Integer.valueOf(0), siteArr0[3].call(siteArr0[4].call(outputName), Integer.valueOf(1)));
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(siteArr0[6].callGroovyObjectGetProperty(this.manifestAction.get()), object))) {
                throw (Throwable)siteArr0[7].callConstructor(GradleException.class, new GStringImpl(new Object[]{object}, new String[]{"Duplicate tinker manifest output name: '", "'"}));
            }
            else {
                Object manifestPath = siteArr0[8].call(Compatibilities.class, this.project.get(), this.processManifestTask.get(), variantOutput);
                return siteArr0[9].call(siteArr0[10].callGroovyObjectGetProperty(this.manifestAction.get()), object, manifestPath);
            }
        }

        @Generated
        public RFixManifestAction getManifestAction() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createManifestTask_closure2.$getCallSiteArray();
            return (RFixManifestAction)ScriptBytecodeAdapter.castToType(this.manifestAction.get(), RFixManifestAction.class);
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createManifestTask_closure2.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public Task getProcessManifestTask() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createManifestTask_closure2.$getCallSiteArray();
            return (Task)ScriptBytecodeAdapter.castToType(this.processManifestTask.get(), Task.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPluginWrapper$_createManifestTask_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPluginWrapper$_createManifestTask_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPluginWrapper$_createManifestTask_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPluginWrapper$_createManifestTask_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "dirName";
            stringArr0[1] = "endsWith";
            stringArr0[2] = "substring";
            stringArr0[3] = "minus";
            stringArr0[4] = "length";
            stringArr0[5] = "containsKey";
            stringArr0[6] = "outputNameToManifestMap";
            stringArr0[7] = "<$constructor$>";
            stringArr0[8] = "getOutputManifestPath";
            stringArr0[9] = "put";
            stringArr0[10] = "outputNameToManifestMap";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPluginWrapper$_createManifestTask_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPluginWrapper$_createManifestTask_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPluginWrapper$_createManifestTask_closure2.$callSiteArray != null ? TinkerPluginWrapper$_createManifestTask_closure2.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper$_createManifestTask_closure2.$callSiteArray.get();
            TinkerPluginWrapper$_createManifestTask_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper$_createManifestTask_closure2
    public final class TinkerPluginWrapper$_createManifestTask_closure2 implements GeneratedClosure {
        private synthetic Reference manifestAction;
        private synthetic Reference project;
        private synthetic Reference processManifestTask;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPluginWrapper$_createManifestTask_closure2(Object _outerInstance, Object _thisObject, Reference manifestAction, Reference project, Reference processManifestTask) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createManifestTask_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            manifestAction.manifestAction = this;
            project.project = this;
            processManifestTask.processManifestTask = this;
        }

        public Object doCall(Object variantOutput) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createManifestTask_closure2.$getCallSiteArray();
            Object outputName = siteArr0[0].callGetProperty(variantOutput);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(outputName, "/"))) {
                Object object = siteArr0[2].call(outputName, Integer.valueOf(0), siteArr0[3].call(siteArr0[4].call(outputName), Integer.valueOf(1)));
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(siteArr0[6].callGroovyObjectGetProperty(this.manifestAction.get()), object))) {
                throw (Throwable)siteArr0[7].callConstructor(GradleException.class, new GStringImpl(new Object[]{object}, new String[]{"Duplicate tinker manifest output name: '", "'"}));
            }
            else {
                Object manifestPath = siteArr0[8].call(Compatibilities.class, this.project.get(), this.processManifestTask.get(), variantOutput);
                return siteArr0[9].call(siteArr0[10].callGroovyObjectGetProperty(this.manifestAction.get()), object, manifestPath);
            }
        }

        @Generated
        public RFixManifestAction getManifestAction() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createManifestTask_closure2.$getCallSiteArray();
            return (RFixManifestAction)ScriptBytecodeAdapter.castToType(this.manifestAction.get(), RFixManifestAction.class);
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createManifestTask_closure2.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public Task getProcessManifestTask() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createManifestTask_closure2.$getCallSiteArray();
            return (Task)ScriptBytecodeAdapter.castToType(this.processManifestTask.get(), Task.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPluginWrapper$_createManifestTask_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPluginWrapper$_createManifestTask_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPluginWrapper$_createManifestTask_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPluginWrapper$_createManifestTask_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "dirName";
            stringArr0[1] = "endsWith";
            stringArr0[2] = "substring";
            stringArr0[3] = "minus";
            stringArr0[4] = "length";
            stringArr0[5] = "containsKey";
            stringArr0[6] = "outputNameToManifestMap";
            stringArr0[7] = "<$constructor$>";
            stringArr0[8] = "getOutputManifestPath";
            stringArr0[9] = "put";
            stringArr0[10] = "outputNameToManifestMap";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPluginWrapper$_createManifestTask_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPluginWrapper$_createManifestTask_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPluginWrapper$_createManifestTask_closure2.$callSiteArray != null ? TinkerPluginWrapper$_createManifestTask_closure2.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper$_createManifestTask_closure2.$callSiteArray.get();
            TinkerPluginWrapper$_createManifestTask_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper$_createCacheExpireTask_closure3
    public final class TinkerPluginWrapper$_createCacheExpireTask_closure3 implements GeneratedClosure {
        private synthetic Reference project;
        private synthetic Reference variant;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPluginWrapper$_createCacheExpireTask_closure3(Object _outerInstance, Object _thisObject, Reference project, Reference variant) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            project.project = this;
            variant.variant = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure3.$getCallSiteArray();
            Object object = siteArr0[0].callGetProperty(RFixPatchPlugin.class);
            ScriptBytecodeAdapter.setGroovyObjectProperty(object, TinkerPluginWrapper$_createCacheExpireTask_closure3.class, this, (String)"group");
            Object objectVar1 = siteArr0[1].callGetProperty(siteArr0[2].callGetProperty(siteArr0[3].callGetProperty(siteArr0[4].callGetProperty(this.project.get()))));
            ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar1, TinkerPluginWrapper$_createCacheExpireTask_closure3.class, this, (String)"patchId");
            Object objectVar2 = siteArr0[5].callConstructor(File.class, siteArr0[6].call(siteArr0[7].call(TinkerBuildPath.class, this.project.get()), new GStringImpl(new Object[]{siteArr0[8].callGetProperty(this.variant.get())}, new String[]{"", "/cache.txt"})));
            ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar2, TinkerPluginWrapper$_createCacheExpireTask_closure3.class, this, (String)"outputFile");
            return objectVar2;
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure3.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public ApkVariant getVariant() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure3.$getCallSiteArray();
            return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure3.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPluginWrapper$_createCacheExpireTask_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPluginWrapper$_createCacheExpireTask_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPluginWrapper$_createCacheExpireTask_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPluginWrapper$_createCacheExpireTask_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "GROUP";
            stringArr0[1] = "tinkerId";
            stringArr0[2] = "buildConfig";
            stringArr0[3] = "tinkerPatch";
            stringArr0[4] = "extensions";
            stringArr0[5] = "<$constructor$>";
            stringArr0[6] = "plus";
            stringArr0[7] = "getTinkerIntermediates";
            stringArr0[8] = "name";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPluginWrapper$_createCacheExpireTask_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPluginWrapper$_createCacheExpireTask_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPluginWrapper$_createCacheExpireTask_closure3.$callSiteArray != null ? TinkerPluginWrapper$_createCacheExpireTask_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper$_createCacheExpireTask_closure3.$callSiteArray.get();
            TinkerPluginWrapper$_createCacheExpireTask_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/wrapper/TinkerPluginWrapper$_createCacheExpireTask_closure3
    public final class TinkerPluginWrapper$_createCacheExpireTask_closure3 implements GeneratedClosure {
        private synthetic Reference project;
        private synthetic Reference variant;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerPluginWrapper$_createCacheExpireTask_closure3(Object _outerInstance, Object _thisObject, Reference project, Reference variant) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            project.project = this;
            variant.variant = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure3.$getCallSiteArray();
            Object object = siteArr0[0].callGetProperty(RFixPatchPlugin.class);
            ScriptBytecodeAdapter.setGroovyObjectProperty(object, TinkerPluginWrapper$_createCacheExpireTask_closure3.class, this, (String)"group");
            Object objectVar1 = siteArr0[1].callGetProperty(siteArr0[2].callGetProperty(siteArr0[3].callGetProperty(siteArr0[4].callGetProperty(this.project.get()))));
            ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar1, TinkerPluginWrapper$_createCacheExpireTask_closure3.class, this, (String)"patchId");
            Object objectVar2 = siteArr0[5].callConstructor(File.class, siteArr0[6].call(siteArr0[7].call(TinkerBuildPath.class, this.project.get()), new GStringImpl(new Object[]{siteArr0[8].callGetProperty(this.variant.get())}, new String[]{"", "/cache.txt"})));
            ScriptBytecodeAdapter.setGroovyObjectProperty(objectVar2, TinkerPluginWrapper$_createCacheExpireTask_closure3.class, this, (String)"outputFile");
            return objectVar2;
        }

        @Generated
        public Project getProject() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure3.$getCallSiteArray();
            return (Project)ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        @Generated
        public ApkVariant getVariant() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure3.$getCallSiteArray();
            return (ApkVariant)ScriptBytecodeAdapter.castToType(this.variant.get(), ApkVariant.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerPluginWrapper$_createCacheExpireTask_closure3.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerPluginWrapper$_createCacheExpireTask_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerPluginWrapper$_createCacheExpireTask_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerPluginWrapper$_createCacheExpireTask_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerPluginWrapper$_createCacheExpireTask_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "GROUP";
            stringArr0[1] = "tinkerId";
            stringArr0[2] = "buildConfig";
            stringArr0[3] = "tinkerPatch";
            stringArr0[4] = "extensions";
            stringArr0[5] = "<$constructor$>";
            stringArr0[6] = "plus";
            stringArr0[7] = "getTinkerIntermediates";
            stringArr0[8] = "name";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerPluginWrapper$_createCacheExpireTask_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerPluginWrapper$_createCacheExpireTask_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerPluginWrapper$_createCacheExpireTask_closure3.$callSiteArray != null ? TinkerPluginWrapper$_createCacheExpireTask_closure3.$createCallSiteArray() : (CallSiteArray)TinkerPluginWrapper$_createCacheExpireTask_closure3.$callSiteArray.get();
            TinkerPluginWrapper$_createCacheExpireTask_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
