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
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;

// class: com/tencent/tinker/build/gradle/Compatibilities
public class Compatibilities implements GroovyObject {
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public Compatibilities() {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public static Object getApplicationId(Object project, Object variant) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        return siteArr0[0].call(variant);
    }

    public static Object getOutputManifestPath(Object project, Object manifestTask, Object variantOutput) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        try {
            Object object = siteArr0[1].callConstructor(File.class, siteArr0[2].callGetProperty(siteArr0[3].call(siteArr0[4].callGetProperty(manifestTask))), new GStringImpl(new Object[]{siteArr0[5].callGetProperty(variantOutput)}, new String[]{"", "/AndroidManifest.xml"}));
            return object;
        }
        catch (Throwable ignored) {
            goto 97;
            goto 105;
            Throwable throwable = v_36;
            throw throwable;
            try {
                Object objectVar1 = siteArr0[6].callConstructor(File.class, siteArr0[7].callGetProperty(siteArr0[8].call(siteArr0[9].callGetProperty(manifestTask))), new GStringImpl(new Object[]{siteArr0[10].callGetProperty(variantOutput)}, new String[]{"", "/AndroidManifest.xml"}));
                return objectVar1;
            }
            catch (Throwable ignored) {
                goto 198;
                goto 206;
                Throwable throwableVar1 = v_74;
                throw throwableVar1;
                try {
                    Object objectVar2 = siteArr0[11].callConstructor(File.class, siteArr0[12].callGetProperty(manifestTask), new GStringImpl(new Object[]{siteArr0[13].callGetProperty(variantOutput)}, new String[]{"", "/AndroidManifest.xml"}));
                    return objectVar2;
                }
                catch (Throwable ignored) {
                    goto 281;
                    goto 289;
                    Throwable throwableVar2 = v_104;
                    throw throwableVar2;
                    return siteArr0[14].callGetProperty(manifestTask);
                }
                try {
                }
                finally {
                }
            }
            try {
            }
            finally {
            }
        }
        try {
        }
        finally {
        }
    }

    public static Object getInputResourcesDirectory(Object project, Object resourcesTask) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        try {
            Object object = siteArr0[15].call(siteArr0[16].call(siteArr0[17].callGetProperty(resourcesTask)));
            return object;
        }
        catch (Throwable ignored) {
            goto 46;
            goto 54;
            Throwable throwable = v_16;
            throw throwable;
            try {
                Object objectVar1 = siteArr0[18].call(siteArr0[19].call(siteArr0[20].callGetProperty(resourcesTask)));
                return objectVar1;
            }
            catch (Throwable ignored) {
                goto 98;
                goto 106;
                Throwable throwableVar1 = v_34;
                throw throwableVar1;
                return siteArr0[21].callGetProperty(resourcesTask);
            }
            try {
            }
            finally {
            }
        }
        try {
        }
        finally {
        }
    }

    public static Object getProcessManifestTask(Object project, Object variant) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        return siteArr0[22].call(siteArr0[23].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[24].call(siteArr0[25].callGetProperty(variant))}, new String[]{"process", "Manifest"}));
    }

    public static Object getMergeResourcesTask(Object project, Object variant) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        return siteArr0[26].call(siteArr0[27].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[28].call(siteArr0[29].callGetProperty(variant))}, new String[]{"merge", "Resources"}));
    }

    public static Object getProcessResourcesTask(Object project, Object variant) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        return siteArr0[30].call(siteArr0[31].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[32].call(siteArr0[33].callGetProperty(variant))}, new String[]{"process", "Resources"}));
    }

    public static Object getAssembleTask(Object project, Object variant) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        return siteArr0[34].call(siteArr0[35].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[36].call(siteArr0[37].callGetProperty(variant))}, new String[]{"assemble", ""}));
    }

    public static Object getMultiDexTask(Object project, Object variant) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        Object capitalizedVariantName = siteArr0[38].call(siteArr0[39].callGetProperty(variant));
        Object multiDexTask = siteArr0[40].call(siteArr0[41].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"multiDexList", ""}));
        if (ScriptBytecodeAdapter.compareNotEqual(multiDexTask, null)) {
            return multiDexTask;
        }
        else {
            return siteArr0[42].call(siteArr0[43].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"transformClassesWithMultidexlistFor", ""}));
        }
    }

    public static Object getR8Task(Object project, Object variant) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        Object capitalizedVariantName = siteArr0[44].call(siteArr0[45].callGetProperty(variant));
        Object r8TransformTask = siteArr0[46].call(siteArr0[47].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"transformClassesAndResourcesWithR8For", ""}));
        if (ScriptBytecodeAdapter.compareNotEqual(r8TransformTask, null)) {
            return r8TransformTask;
        }
        else {
            return siteArr0[48].call(siteArr0[49].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"minify", "WithR8"}));
        }
    }

    public static Object getObfuscateTask(Object project, Object variant) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        Object capitalizedVariantName = siteArr0[50].call(siteArr0[51].callGetProperty(variant));
        Object customProguardTransformTask = siteArr0[52].call(siteArr0[53].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"transformClassesWithCustomProguardFor", ""}));
        if (ScriptBytecodeAdapter.compareNotEqual(customProguardTransformTask, null) && DefaultTypeTransformation.booleanUnbox(siteArr0[54].callGetProperty(customProguardTransformTask)) ? 0 : 1 != 0) {
            return customProguardTransformTask;
        }
        else {
            Object proguardTransformTask = siteArr0[55].call(siteArr0[56].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"transformClassesAndResourcesWithProguardFor", ""}));
            if (ScriptBytecodeAdapter.compareNotEqual(proguardTransformTask, null) && DefaultTypeTransformation.booleanUnbox(siteArr0[57].callGetProperty(proguardTransformTask)) ? 0 : 1 != 0) {
                return proguardTransformTask;
            }
            else {
                Object r8TransformTask = siteArr0[58].call(siteArr0[59].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"transformClassesAndResourcesWithR8For", ""}));
                if (ScriptBytecodeAdapter.compareNotEqual(r8TransformTask, null) && DefaultTypeTransformation.booleanUnbox(siteArr0[60].callGetProperty(r8TransformTask)) ? 0 : 1 != 0) {
                    return r8TransformTask;
                }
                else {
                    Object r8Task = siteArr0[61].call(siteArr0[62].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"minify", "WithR8"}));
                    if (ScriptBytecodeAdapter.compareNotEqual(r8Task, null) && DefaultTypeTransformation.booleanUnbox(siteArr0[63].callGetProperty(r8Task)) ? 0 : 1 != 0) {
                        return r8Task;
                    }
                    else {
                        Object proguardTask = siteArr0[64].call(siteArr0[65].callGetProperty(project), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"minify", "WithProguard"}));
                        if (ScriptBytecodeAdapter.compareNotEqual(proguardTask, null) && DefaultTypeTransformation.booleanUnbox(siteArr0[66].callGetProperty(proguardTask)) ? 0 : 1 != 0) {
                            return proguardTask;
                        }
                        else {
                            throw (Throwable)siteArr0[67].callConstructor(GradleException.class, siteArr0[68].call(String.class, siteArr0[69].call("The minifyEnabled is enabled for '%s', but ", "tinker cannot find the task. Please submit issue to us: %s"), siteArr0[70].callGetProperty(variant), siteArr0[71].callGetProperty(TinkerPatchPlugin.class)));
                        }
                    }
                }
            }
        }
    }

    public static Object getInstantRunTask(Object project, Object variant) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        return siteArr0[72].call(siteArr0[73].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[74].call(siteArr0[75].callGetProperty(variant))}, new String[]{"transformClassesWithInstantRunFor", ""}));
    }

    public static Object getCollectMultiDexComponentsTask(Object project, Object variant) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        return siteArr0[76].call(siteArr0[77].callGetProperty(project), new GStringImpl(new Object[]{siteArr0[78].call(siteArr0[79].callGetProperty(variant))}, new String[]{"collect", "MultiDexComponents"}));
    }

    public static Object getFieldRecursively(Object ownerClazz, Object name) {
        CallSite[] siteArr0 = Compatibilities.$getCallSiteArray();
        Class currClazz = (Class)ScriptBytecodeAdapter.asType(ownerClazz, Class.class);
        try {
        }
        finally {
        }
        while (true) {
            try {
                Object field = siteArr0[80].call(currClazz, name);
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[81].call(field)) ? 0 : 1 != 0) {
                    siteArr0[82].call(field, Boolean.valueOf(true));
                }
                return field;
            }
            catch (NoSuchFieldException e) {
                if (ScriptBytecodeAdapter.compareNotEqual(currClazz, Object.class)) {
                    Object objectVar1 = siteArr0[83].call(currClazz);
                    Object objectVar2 = objectVar1;
                    goto 179;
                    goto 183;
                    goto 191;
                    goto 16;
                }
                else {
                    throw (Throwable)siteArr0[84].callConstructor(NoSuchFieldException.class, new GStringImpl(new Object[]{name, siteArr0[85].call(ownerClazz)}, new String[]{"Cannot find field ", " in ", " and its super classes."}));
                }
                Throwable throwable = v_20;
                throw throwable;
            }
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != Compatibilities.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (Compatibilities.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                Compatibilities.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return Compatibilities.$staticClassInfo.getMetaClass();
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
        stringArr0[0] = "getApplicationId";
        stringArr0[1] = "<$constructor$>";
        stringArr0[2] = "asFile";
        stringArr0[3] = "get";
        stringArr0[4] = "multiApkManifestOutputDirectory";
        stringArr0[5] = "dirName";
        stringArr0[6] = "<$constructor$>";
        stringArr0[7] = "asFile";
        stringArr0[8] = "get";
        stringArr0[9] = "manifestOutputDirectory";
        stringArr0[10] = "dirName";
        stringArr0[11] = "<$constructor$>";
        stringArr0[12] = "manifestOutputDirectory";
        stringArr0[13] = "dirName";
        stringArr0[14] = "manifestOutputFile";
        stringArr0[15] = "get";
        stringArr0[16] = "getAsFile";
        stringArr0[17] = "inputResourcesDir";
        stringArr0[18] = "first";
        stringArr0[19] = "getFiles";
        stringArr0[20] = "inputResourcesDir";
        stringArr0[21] = "resDir";
        stringArr0[22] = "findByName";
        stringArr0[23] = "tasks";
        stringArr0[24] = "capitalize";
        stringArr0[25] = "name";
        stringArr0[26] = "findByName";
        stringArr0[27] = "tasks";
        stringArr0[28] = "capitalize";
        stringArr0[29] = "name";
        stringArr0[30] = "findByName";
        stringArr0[31] = "tasks";
        stringArr0[32] = "capitalize";
        stringArr0[33] = "name";
        stringArr0[34] = "findByName";
        stringArr0[35] = "tasks";
        stringArr0[36] = "capitalize";
        stringArr0[37] = "name";
        stringArr0[38] = "capitalize";
        stringArr0[39] = "name";
        stringArr0[40] = "findByName";
        stringArr0[41] = "tasks";
        stringArr0[42] = "findByName";
        stringArr0[43] = "tasks";
        stringArr0[44] = "capitalize";
        stringArr0[45] = "name";
        stringArr0[46] = "findByName";
        stringArr0[47] = "tasks";
        stringArr0[48] = "findByName";
        stringArr0[49] = "tasks";
        stringArr0[50] = "capitalize";
        stringArr0[51] = "name";
        stringArr0[52] = "findByName";
        stringArr0[53] = "tasks";
        stringArr0[54] = "enabled";
        stringArr0[55] = "findByName";
        stringArr0[56] = "tasks";
        stringArr0[57] = "enabled";
        stringArr0[58] = "findByName";
        stringArr0[59] = "tasks";
        stringArr0[60] = "enabled";
        stringArr0[61] = "findByName";
        stringArr0[62] = "tasks";
        stringArr0[63] = "enabled";
        stringArr0[64] = "findByName";
        stringArr0[65] = "tasks";
        stringArr0[66] = "enabled";
        stringArr0[67] = "<$constructor$>";
        stringArr0[68] = "format";
        stringArr0[69] = "plus";
        stringArr0[70] = "name";
        stringArr0[71] = "ISSUE_URL";
        stringArr0[72] = "findByName";
        stringArr0[73] = "tasks";
        stringArr0[74] = "capitalize";
        stringArr0[75] = "name";
        stringArr0[76] = "findByName";
        stringArr0[77] = "tasks";
        stringArr0[78] = "capitalize";
        stringArr0[79] = "name";
        stringArr0[80] = "getDeclaredField";
        stringArr0[81] = "isAccessible";
        stringArr0[82] = "setAccessible";
        stringArr0[83] = "getSuperclass";
        stringArr0[84] = "<$constructor$>";
        stringArr0[85] = "getName";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        Compatibilities.$createCallSiteArray_1(str0);
        return new CallSiteArray(Compatibilities.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = Compatibilities.$callSiteArray != null ? Compatibilities.$createCallSiteArray() : (CallSiteArray)Compatibilities.$callSiteArray.get();
        Compatibilities.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
