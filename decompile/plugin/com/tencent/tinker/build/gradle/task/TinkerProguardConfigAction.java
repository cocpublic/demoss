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
import org.gradle.api.Task;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.io.FileWriter;
import java.util.Iterator;

// class: com/tencent/tinker/build/gradle/task/TinkerProguardConfigAction
public class TinkerProguardConfigAction implements Action<Task>, GroovyObject {
    final private static String PROGUARD_CONFIG_SETTINGS;
    private Object applicationVariant;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerProguardConfigAction(Object variant) {
        CallSite[] siteArr0 = TinkerProguardConfigAction.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        variant.applicationVariant = this;
    }

    public void execute(Task task) {
        CallSite[] siteArr0 = TinkerProguardConfigAction.$getCallSiteArray();
        siteArr0[0].callCurrent(this, siteArr0[1].call(task));
    }

    public Object updateTinkerProguardConfig(Object project) {
        CallSite[] siteArr0 = TinkerProguardConfigAction.$getCallSiteArray();
        Object file = siteArr0[2].call(project, siteArr0[3].call(TinkerBuildPath.class, project));
        siteArr0[4].call(siteArr0[5].callGetProperty(project), new GStringImpl(new Object[]{file}, new String[]{"try update tinker proguard file with ", ""}));
        siteArr0[6].call(siteArr0[7].call(file));
        FileWriter fr = (FileWriter)ScriptBytecodeAdapter.castToType(siteArr0[8].callConstructor(FileWriter.class, siteArr0[9].callGetProperty(file)), FileWriter.class);
        String applyMappingFile = (String)ShortTypeHandling.castToString(siteArr0[10].callGetProperty(siteArr0[11].callGetProperty(siteArr0[12].callGetProperty(siteArr0[13].callGetProperty(project)))));
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[14].call(FileOperation.class, applyMappingFile))) {
            siteArr0[15].call(siteArr0[16].callGetProperty(project), new GStringImpl(new Object[]{applyMappingFile}, new String[]{"try add applymapping ", " to build the package"}));
            siteArr0[17].call(fr, siteArr0[18].call("-applymapping ", applyMappingFile));
            siteArr0[19].call(fr, "
");
        }
        else {
            siteArr0[20].call(siteArr0[21].callGetProperty(project), new GStringImpl(new Object[]{applyMappingFile}, new String[]{"applymapping file ", " is illegal, just ignore"}));
        }
        siteArr0[22].call(fr, TinkerProguardConfigAction.PROGUARD_CONFIG_SETTINGS);
        siteArr0[23].call(fr, "#your dex.loader patterns here
");
        Iterable loader = (Iterable)ScriptBytecodeAdapter.castToType(siteArr0[24].callGetProperty(siteArr0[25].callGetProperty(siteArr0[26].callGetProperty(siteArr0[27].callGetProperty(project)))), Iterable.class);
        Object pattern = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[28].call(loader), Iterator.class);
        while (iterator.hasNext()) {
            String str1 = (String)ShortTypeHandling.castToString(iterator.next());
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[29].call(str1, "*"))) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[30].call(str1, "**")) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                v_189 = siteArr0[31].call(str1, "*");
                str1 = (String)ShortTypeHandling.castToString(siteArr0[31].call(str1, "*"));
            }
            siteArr0[32].call(fr, siteArr0[33].call("-keep class ", str1));
            siteArr0[34].call(fr, "
");
        }
        siteArr0[35].call(fr);
        return siteArr0[36].callCurrent(this, project, file);
    }

    private void injectTinkerProguardRuleFile(Object project, Object file) {
        CallSite[] siteArr0 = TinkerProguardConfigAction.$getCallSiteArray();
        Object agpObfuscateTask = siteArr0[37].call(Compatibilities.class, project, this.applicationVariant);
        Object configurationFilesOwner = null;
        Object configurationFilesField = null;
        try {
            try {
                Object object = agpObfuscateTask;
                Object objectVar1 = siteArr0[38].call(Compatibilities.class, siteArr0[39].call(object), "__configurationFiles__");
            }
            catch (Throwable ignored) {
                Object objectVar2 = null;
                configurationFilesOwner = objectVar2;
                Object objectVar3 = null;
                configurationFilesField = objectVar3;
            }
            goto 118;
        }
        finally {
            Throwable throwable = v_31;
            throw throwable;
        }
        if (ScriptBytecodeAdapter.compareEqual(objectVar1, null)) {
            try {
                try {
                    Object objectVar4 = agpObfuscateTask;
                    configurationFilesOwner = objectVar4;
                    Object objectVar5 = siteArr0[40].call(Compatibilities.class, siteArr0[41].call(object), "configurationFiles");
                    configurationFilesField = objectVar5;
                }
                catch (Throwable ignored) {
                    Object objectVar6 = null;
                    configurationFilesOwner = objectVar6;
                    Object objectVar7 = null;
                    configurationFilesField = objectVar7;
                }
            }
            finally {
                Throwable throwableVar1 = v_52;
                throw throwableVar1;
            }
        }
        if (ScriptBytecodeAdapter.compareEqual(objectVar1, null)) {
            try {
                try {
                    Object objectVar8 = siteArr0[42].callGetProperty(agpObfuscateTask);
                    configurationFilesOwner = objectVar8;
                    Object objectVar9 = siteArr0[43].call(Compatibilities.class, siteArr0[44].call(object), "configurationFiles");
                    configurationFilesField = objectVar9;
                }
                catch (Throwable ignored) {
                    Object objectVar10 = null;
                    configurationFilesOwner = objectVar10;
                    Object objectVar11 = null;
                    configurationFilesField = objectVar11;
                }
            }
            finally {
                Throwable throwableVar2 = v_77;
                throw throwableVar2;
            }
        }
        Object agpConfigurationFiles = null;
        int isOK = 0;
        if (BytecodeInterface8.isOrigZ() && TinkerProguardConfigAction.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 428;
            if (ScriptBytecodeAdapter.compareNotEqual(object, null) && ScriptBytecodeAdapter.compareNotEqual(objectVar1, null) ? 0 : 1 != 0) {
                try {
                    try {
                        Object objectVar13 = siteArr0[46].call(objectVar1, object);
                        agpConfigurationFiles = objectVar13;
                        int i2 = 1;
                        isOK = i2;
                    }
                    catch (Throwable ignored) {
                        int i3 = 0;
                        isOK = i3;
                    }
                }
                finally {
                    Throwable throwableVar4 = v_104;
                    throw throwableVar4;
                }
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareNotEqual(object, null) && ScriptBytecodeAdapter.compareNotEqual(objectVar1, null) ? 0 : 1 != 0) {
                try {
                    try {
                        Object objectVar12 = siteArr0[45].call(objectVar1, object);
                        int i0 = 1;
                    }
                    catch (Throwable ignored) {
                        int i1 = 0;
                        isOK = i1;
                    }
                }
                finally {
                    Throwable throwableVar3 = v_186;
                    throw throwableVar3;
                }
            }
            goto 513;
        }
        if (i0 != 0) {
            Object mergedConfigurationFiles = siteArr0[47].call(project, objectVar12, siteArr0[48].call(project, file));
            try {
                try {
                    siteArr0[49].call(objectVar1, object, mergedConfigurationFiles);
                    Object mergedConfigurationFilesForConfirm = siteArr0[50].call(objectVar1, object);
                    siteArr0[51].callCurrent(this, new GStringImpl(new Object[]{siteArr0[52].callGetProperty(mergedConfigurationFilesForConfirm)}, new String[]{"Now proguard rule files are: ", ""}));
                }
                catch (Throwable ignored) {
                    int i4 = 0;
                    isOK = i4;
                }
            }
            finally {
                Throwable throwableVar5 = v_154;
                throw throwableVar5;
            }
        }
        if (i0 == 0 ? 0 : 1 != 0) {
            throw (Throwable)siteArr0[53].callConstructor(GradleException.class, "Fail to inject tinker proguard rules file. Some compatibility works need to be done.");
        }
        else {
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerProguardConfigAction.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerProguardConfigAction.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerProguardConfigAction.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerProguardConfigAction.$staticClassInfo.getMetaClass();
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
    public static String getPROGUARD_CONFIG_SETTINGS() {
        return TinkerProguardConfigAction.PROGUARD_CONFIG_SETTINGS;
    }

    @Generated
    public Object getApplicationVariant() {
        return this.applicationVariant;
    }

    @Generated
    public void setApplicationVariant(Object object) {
        this.applicationVariant = object;
    }

    @Generated
    public /* synthetic */ void execute(Object object) {
        this.execute((Task)object);
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "updateTinkerProguardConfig";
        stringArr0[1] = "getProject";
        stringArr0[2] = "file";
        stringArr0[3] = "getProguardConfigPath";
        stringArr0[4] = "error";
        stringArr0[5] = "logger";
        stringArr0[6] = "mkdirs";
        stringArr0[7] = "getParentFile";
        stringArr0[8] = "<$constructor$>";
        stringArr0[9] = "path";
        stringArr0[10] = "applyMapping";
        stringArr0[11] = "buildConfig";
        stringArr0[12] = "tinkerPatch";
        stringArr0[13] = "extensions";
        stringArr0[14] = "isLegalFile";
        stringArr0[15] = "error";
        stringArr0[16] = "logger";
        stringArr0[17] = "write";
        stringArr0[18] = "plus";
        stringArr0[19] = "write";
        stringArr0[20] = "error";
        stringArr0[21] = "logger";
        stringArr0[22] = "write";
        stringArr0[23] = "write";
        stringArr0[24] = "loader";
        stringArr0[25] = "dex";
        stringArr0[26] = "tinkerPatch";
        stringArr0[27] = "extensions";
        stringArr0[28] = "iterator";
        stringArr0[29] = "endsWith";
        stringArr0[30] = "endsWith";
        stringArr0[31] = "plus";
        stringArr0[32] = "write";
        stringArr0[33] = "plus";
        stringArr0[34] = "write";
        stringArr0[35] = "close";
        stringArr0[36] = "injectTinkerProguardRuleFile";
        stringArr0[37] = "getObfuscateTask";
        stringArr0[38] = "getFieldRecursively";
        stringArr0[39] = "getClass";
        stringArr0[40] = "getFieldRecursively";
        stringArr0[41] = "getClass";
        stringArr0[42] = "transform";
        stringArr0[43] = "getFieldRecursively";
        stringArr0[44] = "getClass";
        stringArr0[45] = "get";
        stringArr0[46] = "get";
        stringArr0[47] = "files";
        stringArr0[48] = "files";
        stringArr0[49] = "set";
        stringArr0[50] = "get";
        stringArr0[51] = "println";
        stringArr0[52] = "files";
        stringArr0[53] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerProguardConfigAction.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerProguardConfigAction.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerProguardConfigAction.$callSiteArray != null ? TinkerProguardConfigAction.$createCallSiteArray() : (CallSiteArray)TinkerProguardConfigAction.$callSiteArray.get();
        TinkerProguardConfigAction.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
