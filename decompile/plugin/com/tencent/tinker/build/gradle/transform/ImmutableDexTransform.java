/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/gradle/transform;

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
import java.io.File;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.util.Set;
import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map$Entry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.Enumeration;
import com.google.common.base.Joiner;
import com.android.build.api.transform.TransformInput;
import com.tencent.tinker.build.immutable.DexRefData;
import com.tencent.tinker.build.immutable.ClassSimDef;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.ClassDef;

// class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform
public class ImmutableDexTransform implements GroovyObject {
    final public static String TASK_WORK_DIR;
    final private static Joiner PATH_JOINER;
    private Project project;
    private String oldApkPath;
    private File classPreDir;
    private File baseDexDir;
    private File mainDexListFile;
    private String varName;
    private String varDirName;
    private Object variant;
    private Object dexTransform;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public ImmutableDexTransform(Project project, Object variant, Object dexTransform) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        dexTransform.dexTransform = this;
        (Project)ScriptBytecodeAdapter.castToType(project, Project.class).project = this;
        variant.variant = this;
        Object objectVar2 = siteArr0[0].call(siteArr0[1].callGetProperty(variant));
        (String)ShortTypeHandling.castToString(objectVar2).varName = this;
        Object objectVar3 = siteArr0[2].call(variant);
        (String)ShortTypeHandling.castToString(objectVar3).varDirName = this;
        Object objectVar4 = siteArr0[3].callGetProperty(siteArr0[4].callGetProperty(project));
        (String)ShortTypeHandling.castToString(objectVar4).oldApkPath = this;
        if ((siteArr0[5].callGetProperty(dexTransform) instanceof File)) {
            Object objectVar5 = siteArr0[6].callGetProperty(dexTransform);
            (File)ScriptBytecodeAdapter.castToType(objectVar5, File.class).mainDexListFile = this;
        }
        else {
            Object objectVar6 = siteArr0[7].call(siteArr0[8].callGetProperty(dexTransform));
            (File)ScriptBytecodeAdapter.castToType(objectVar6, File.class).mainDexListFile = this;
        }
    }

    public void initFileEnv(TransformOutputProvider outputProvider) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        if (ImmutableDexTransform.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 51;
            File file = this.getDirInWorkDir("class_pre");
            file.classPreDir = this;
        }
        else {
            Object object = siteArr0[9].callCurrent(this, "class_pre");
            (File)ScriptBytecodeAdapter.castToType(object, File.class).classPreDir = this;
        }
        if (ImmutableDexTransform.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 120;
            File fileVar1 = this.getDirInWorkDir("base_dex");
            fileVar1.baseDexDir = this;
        }
        else {
            Object objectVar1 = siteArr0[10].callCurrent(this, "base_dex");
            (File)ScriptBytecodeAdapter.castToType(objectVar1, File.class).baseDexDir = this;
        }
        siteArr0[11].call(this.classPreDir);
        siteArr0[12].call(this.baseDexDir);
        siteArr0[13].call(FileOperation.class, this.classPreDir);
        siteArr0[14].call(FileOperation.class, this.baseDexDir);
    }

    private File getDirInWorkDir(String name) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return (File)ScriptBytecodeAdapter.castToType(siteArr0[15].callConstructor(File.class, siteArr0[16].call(ImmutableDexTransform.PATH_JOINER, siteArr0[17].call(TinkerBuildPath.class, this.project), ImmutableDexTransform.TASK_WORK_DIR, name, this.varDirName)), File.class);
    }

    public Set<QualifiedContent$ContentType> getOutputTypes() {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return (Set)ScriptBytecodeAdapter.castToType(siteArr0[18].call(this.dexTransform), Set.class);
    }

    public Collection<File> getSecondaryFileInputs() {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return (Collection)ScriptBytecodeAdapter.castToType(siteArr0[19].call(this.dexTransform), Collection.class);
    }

    public Collection<File> getSecondaryDirectoryOutputs() {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return (Collection)ScriptBytecodeAdapter.castToType(siteArr0[20].call(this.dexTransform), Collection.class);
    }

    public Map<String, Object> getParameterInputs() {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return (Map)ScriptBytecodeAdapter.castToType(siteArr0[21].call(this.dexTransform), Map.class);
    }

    public String getName() {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[22].call(this.dexTransform));
    }

    public Set<QualifiedContent$ContentType> getInputTypes() {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return (Set)ScriptBytecodeAdapter.castToType(siteArr0[23].call(this.dexTransform), Set.class);
    }

    public Set<QualifiedContent$Scope> getScopes() {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return (Set)ScriptBytecodeAdapter.castToType(siteArr0[24].call(this.dexTransform), Set.class);
    }

    public boolean isIncremental() {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(siteArr0[25].call(this.dexTransform));
    }

    public void transform(TransformInvocation transformInvocation) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        List jarInputs = (List)ScriptBytecodeAdapter.castToType(siteArr0[26].call(Lists.class), List.class);
        Object input = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[27].call(siteArr0[28].call(transformInvocation)), Iterator.class);
        while (iterator.hasNext()) {
            input = (TransformInput)ScriptBytecodeAdapter.castToType(iterator.next(), TransformInput.class);
            siteArr0[29].call(jarInputs, siteArr0[30].call(input));
        }
        if (ScriptBytecodeAdapter.compareNotEqual(siteArr0[31].call(jarInputs), Integer.valueOf(1))) {
            siteArr0[32].call(siteArr0[33].callGetProperty(this.project), new GStringImpl(new Object[]{siteArr0[34].call(jarInputs)}, new String[]{"jar input size is ", ", expected is 1. we will skip immutable dex!"}));
            siteArr0[35].call(this.dexTransform, transformInvocation);
        }
        else {
            siteArr0[36].callCurrent(this, siteArr0[37].call(transformInvocation));
            v_98 = alloc(Reference);
            new (ArrayList)ScriptBytecodeAdapter.castToType(siteArr0[38].callConstructor(ArrayList.class), ArrayList.class).<init>(v_98);
            Reference oldDexList = v_98;
            siteArr0[39].callStatic(ImmutableDexTransform.class, siteArr0[40].callConstructor(ZipFile.class, this.oldApkPath), new ImmutableDexTransform$_transform_closure1(this, this, oldDexList));
            v_124 = alloc(Reference);
            new (HashMap)ScriptBytecodeAdapter.castToType(siteArr0[41].callConstructor(HashMap.class), HashMap.class).<init>(v_124);
            Reference pathDexMap = v_124;
            siteArr0[42].call(siteArr0[43].callGetProperty(this.project), new GStringImpl(new Object[]{(ArrayList)oldDexList.get()}, new String[]{"old dex list is : ", "."}));
            siteArr0[44].call((ArrayList)oldDexList.get(), new ImmutableDexTransform$_transform_closure2(this, this, pathDexMap));
            v_167 = DefaultTypeTransformation.intUnbox(siteArr0[45].call((ArrayList)oldDexList.get()));
            int newDexIndex = DefaultTypeTransformation.intUnbox(siteArr0[45].call((ArrayList)oldDexList.get()));
            HashSet mainDexSets = (HashSet)ScriptBytecodeAdapter.castToType(siteArr0[46].callCurrent(this, this.mainDexListFile), HashSet.class);
            siteArr0[47].call(siteArr0[48].callGetProperty(this.project), new GStringImpl(new Object[]{mainDexSets}, new String[]{"mainDexSets is ", "."}));
            HashMap osMap = (HashMap)ScriptBytecodeAdapter.castToType(siteArr0[49].callConstructor(HashMap.class), HashMap.class);
            HashMap methodAndFieldsNum = (HashMap)ScriptBytecodeAdapter.castToType(siteArr0[50].callConstructor(HashMap.class), HashMap.class);
            HashMap orphanMap = (HashMap)ScriptBytecodeAdapter.castToType(siteArr0[51].callConstructor(HashMap.class), HashMap.class);
            HashSet allClassSet = (HashSet)ScriptBytecodeAdapter.castToType(siteArr0[52].callConstructor(HashSet.class), HashSet.class);
            siteArr0[53].callCurrent(this, ArrayUtil.createArray(siteArr0[54].callGetProperty(siteArr0[55].call(jarInputs, Integer.valueOf(0))), allClassSet, (HashMap)pathDexMap.get(), mainDexSets, methodAndFieldsNum, osMap, orphanMap));
            iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[56].call(siteArr0[57].call(orphanMap)), Iterator.class);
            Object leaveEntry = null;
            while (DefaultTypeTransformation.booleanUnbox(siteArr0[58].call(iterator))) {
                int writeResult = 1;
                while (true) {
                    if (writeResult != 0 && DefaultTypeTransformation.booleanUnbox(siteArr0[59].call(iterator)) ? 0 : 1 != 0) {
                        Map$Entry entryVar2;
                        String newDexName;
                        if (ScriptBytecodeAdapter.compareNotEqual(leaveEntry, null)) {
                            newDexName = (String)ShortTypeHandling.castToString(siteArr0[60].callCurrent(this, Integer.valueOf(newDexIndex), ""));
                            siteArr0[61].call(siteArr0[62].callGetProperty(this.project), new GStringImpl(new Object[]{siteArr0[63].callGetProperty(siteArr0[64].callGetProperty(leaveEntry)), newDexName}, new String[]{"write level orphan class: ", " to zip: ", ""}));
                            Object object = siteArr0[65].callCurrent(this, ArrayUtil.createArray(methodAndFieldsNum, osMap, newDexName, siteArr0[66].call(siteArr0[67].callGetProperty(leaveEntry)), siteArr0[68].callGetProperty(leaveEntry)));
                            boolean bool0 = DefaultTypeTransformation.booleanUnbox(object);
                            if (bool0 ? 0 : 1 != 0) {
                                throw (Throwable)siteArr0[69].callConstructor(GradleException.class, siteArr0[70].call(siteArr0[71].call(siteArr0[72].call(siteArr0[73].call("add one class to a new zip failed!
", "	 class:"), siteArr0[74].callGetProperty(siteArr0[75].callGetProperty(leaveEntry))), "  zip: "), newDexName));
                            }
                        }
                        Map$Entry entry = (Map$Entry)ScriptBytecodeAdapter.castToType(siteArr0[76].call(iterator), Map$Entry.class);
                        entry = entry;
                        entryVar2 = entry;
                        newDexName = (String)ShortTypeHandling.castToString(siteArr0[77].callCurrent(this, Integer.valueOf(newDexIndex), ""));
                        siteArr0[78].call(siteArr0[79].callGetProperty(this.project), new GStringImpl(new Object[]{siteArr0[80].callGetProperty(siteArr0[81].callGetProperty(entry)), newDexName}, new String[]{"write orphan class: ", " to zip: ", ""}));
                        Object objectVar1 = siteArr0[82].callCurrent(this, ArrayUtil.createArray(methodAndFieldsNum, osMap, newDexName, siteArr0[83].call(siteArr0[84].callGetProperty(entry)), siteArr0[85].callGetProperty(entry)));
                        boolean bool1 = DefaultTypeTransformation.booleanUnbox(objectVar1);
                        if (bool1) {
                            Object objectVar2 = null;
                            entryVar2 = (Map$Entry)ScriptBytecodeAdapter.castToType(objectVar2, Map$Entry.class);
                        }
                        continue;;
                    }
                    else {
                        int i0 = newDexIndex;
                        v_499 = v_494.call(Integer.valueOf(siteArr0[86]));
                        newDexIndex = DefaultTypeTransformation.intUnbox(v_494.call(Integer.valueOf(siteArr0[86])));
                    }
                }
            }
            siteArr0[87].call(osMap, new ImmutableDexTransform$_transform_closure3(this, this));
            v_519 = alloc(Reference);
            new (ArrayList)ScriptBytecodeAdapter.castToType(siteArr0[88].callConstructor(ArrayList.class), ArrayList.class).<init>(v_519);
            Reference dexPathList = v_519;
            v_522 = alloc(Reference);
            new null.<init>(v_522);
            Reference dxOutDir = v_522;
            if (ImmutableDexTransform.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 1540;
                Object objectVar4 = siteArr0[94].call(siteArr0[95].callGetProperty(transformInvocation), "main", this.getOutputTypes(), siteArr0[96].callGetProperty(TransformManager.class), siteArr0[97].callGetProperty(Format.class));
                objectVar4.set((Reference)dxOutDir);
            }
            else {
                Object objectVar3 = siteArr0[89].call(siteArr0[90].callGetProperty(transformInvocation), "main", siteArr0[91].callCurrent(this), siteArr0[92].callGetProperty(TransformManager.class), siteArr0[93].callGetProperty(Format.class));
                objectVar3.set((Reference)dxOutDir);
                goto 1611;
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[98].call(dxOutDir.get()))) {
                siteArr0[99].call(FileOperation.class, dxOutDir.get());
            }
            else {
                siteArr0[100].call(dxOutDir.get());
            }
            siteArr0[101].call(this.classPreDir, new ImmutableDexTransform$_transform_closure4(this, this, dxOutDir, dexPathList));
            siteArr0[102].callCurrent(this, (ArrayList)dexPathList.get(), allClassSet);
        }
    }

    private void processJar(File jarFile, HashSet<String> allClassSet, HashMap<String, String> pathDexMap, HashSet<String> mainDexSets, HashMap<String, DexRefData> methodAndFieldsNum, HashMap<String, ZipOutputStream> osMap, HashMap<ZipEntry, ByteArrayOutputStream> orphanMap) {
        v_1 = alloc(Reference);
        new allClassSet.<init>(v_1);
        Reference reference = v_1;
        v_3 = alloc(Reference);
        new pathDexMap.<init>(v_3);
        Reference referenceVar1 = v_3;
        v_5 = alloc(Reference);
        new mainDexSets.<init>(v_5);
        Reference referenceVar2 = v_5;
        v_7 = alloc(Reference);
        new methodAndFieldsNum.<init>(v_7);
        Reference referenceVar3 = v_7;
        v_9 = alloc(Reference);
        new osMap.<init>(v_9);
        Reference referenceVar4 = v_9;
        v_11 = alloc(Reference);
        new orphanMap.<init>(v_11);
        Reference referenceVar5 = v_11;
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        ZipFile zipFile = (ZipFile)ScriptBytecodeAdapter.castToType(siteArr0[103].callConstructor(ZipFile.class, jarFile), ZipFile.class);
        siteArr0[104].callStatic(ImmutableDexTransform.class, zipFile, new ImmutableDexTransform$_processJar_closure5(this, this, referenceVar2, reference, referenceVar3, referenceVar4));
        siteArr0[105].callStatic(ImmutableDexTransform.class, zipFile, new ImmutableDexTransform$_processJar_closure6(this, this, reference, referenceVar1, referenceVar3, referenceVar4, referenceVar5));
    }

    public HashSet<String> initMainDexSet(File mainDexList) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        v_9 = alloc(Reference);
        new (HashSet)ScriptBytecodeAdapter.castToType(siteArr0[106].callConstructor(HashSet.class), HashSet.class).<init>(v_9);
        Reference mainDexSets = v_9;
        BufferedReader reader = (BufferedReader)ScriptBytecodeAdapter.castToType(siteArr0[107].call(mainDexList), BufferedReader.class);
        List lines = (List)ScriptBytecodeAdapter.castToType(siteArr0[108].call(reader), List.class);
        siteArr0[109].call(lines, new ImmutableDexTransform$_initMainDexSet_closure7(this, this, mainDexSets));
        return (HashSet)mainDexSets.get();
    }

    private String rePathToClassPath(String rePath) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        int eIndex = DefaultTypeTransformation.intUnbox(siteArr0[110].call(rePath, ".class"));
        if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && ImmutableDexTransform.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 130;
            if (eIndex >= 0 ? 0 : 1 != 0) {
                return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[112].call(rePath, Integer.valueOf(0), Integer.valueOf(eIndex))}, new String[]{"L", ";"}));
            }
            else {
                return "";
            }
        }
        else {
            if (eIndex >= 0 ? 0 : 1 != 0) {
                return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[111].call(rePath, Integer.valueOf(0), Integer.valueOf(eIndex))}, new String[]{"L", ";"}));
            }
            else {
                return "";
            }
        }
    }

    private void doDex(String dexPath, File classZip, Object dexOptions) {
        v_1 = alloc(Reference);
        new dexPath.<init>(v_1);
        Reference reference = v_1;
        v_3 = alloc(Reference);
        new classZip.<init>(v_3);
        Reference referenceVar1 = v_3;
        v_5 = alloc(Reference);
        new dexOptions.<init>(v_5);
        Reference referenceVar2 = v_5;
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        v_40 = alloc(Reference);
        new new GStringImpl(new Object[]{siteArr0[113].call(siteArr0[114].callGetProperty(this.project)), siteArr0[115].callGetProperty(siteArr0[116].callGetProperty(this.project))}, new String[]{"", "/build-tools/", "/lib/dx.jar"}).<init>(v_40);
        Reference dexJar = v_40;
        Object task = siteArr0[117].call(siteArr0[118].callGetProperty(this.project), siteArr0[119].call(siteArr0[120].call("dx", siteArr0[121].call(siteArr0[122].callGetProperty((File)referenceVar1.get()), ".jar")), this.varName), JavaExec.class, new ImmutableDexTransform$1(this, referenceVar2, reference, referenceVar1, dexJar));
        siteArr0[123].call(task);
    }

    public static void inject(Project project, Object variant) {
        v_1 = alloc(Reference);
        new project.<init>(v_1);
        Reference reference = v_1;
        v_3 = alloc(Reference);
        new variant.<init>(v_3);
        Reference referenceVar1 = v_3;
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        siteArr0[124].call(siteArr0[125].callGetProperty((Project)reference.get()), "prepare inject dex transform ");
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[126].callGetProperty(siteArr0[127].callGetProperty(referenceVar1.get()))) ? 0 : 1 != 0) {
            siteArr0[128].call(siteArr0[129].callGetProperty((Project)reference.get()), "multidex is disabled. we will not replace the dex transform.");
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[130].call(FileOperation.class, siteArr0[131].callGetProperty(siteArr0[132].callGetProperty((Project)reference.get())))) ? 0 : 1 != 0) {
                siteArr0[133].call(siteArr0[134].callGetProperty((Project)reference.get()), "oldApk is illegal. we will not replace the dex transform.");
            }
            else {
                try {
                    try {
                        Class.forName("com.android.build.gradle.internal.transforms.DexTransform");
                        goto 241;
                    }
                    catch (ClassNotFoundException e) {
                        return;
                    }
                }
                finally {
                    Throwable throwable = v_74;
                    throw throwable;
                }
                siteArr0[135].call(siteArr0[136].call(siteArr0[137].call((Project)reference.get())), new ImmutableDexTransform$2(ImmutableDexTransform.class, reference, referenceVar1));
            }
        }
    }

    public void checkClassConsistence(ArrayList<String> dexPathList, HashSet<String> allClassSet) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        siteArr0[138].call(siteArr0[139].callGetProperty(this.project), "start check class's consistence ..");
        if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && ImmutableDexTransform.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 119;
            if (! ScriptBytecodeAdapter.compareEqual(dexPathList, null) || ScriptBytecodeAdapter.compareEqual(siteArr0[142].call(dexPathList), Integer.valueOf(0)) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[143].callConstructor(GradleException.class, "immutable dex list is null! ");
            }
        }
        else {
            if (! ScriptBytecodeAdapter.compareEqual(dexPathList, null) || ScriptBytecodeAdapter.compareEqual(siteArr0[140].call(dexPathList), Integer.valueOf(0)) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[141].callConstructor(GradleException.class, "immutable dex list is null! ");
            }
            else {
            }
        }
        siteArr0[144].call(siteArr0[145].callGetProperty(this.project), siteArr0[146].call("check dex list: ", dexPathList));
        v_60 = alloc(Reference);
        new (HashSet)ScriptBytecodeAdapter.castToType(siteArr0[147].callConstructor(HashSet.class), HashSet.class).<init>(v_60);
        Reference dexClassSet = v_60;
        v_64 = alloc(Reference);
        new Integer.valueOf(0).<init>(v_64);
        Reference classSize = v_64;
        siteArr0[148].call(dexPathList, new ImmutableDexTransform$_checkClassConsistence_closure8(this, this, classSize, dexClassSet));
        HashSet hashSet1 = (HashSet)ScriptBytecodeAdapter.castToType(siteArr0[149].callConstructor(HashSet.class, (HashSet)dexClassSet.get()), HashSet.class);
        HashSet hashSet2 = (HashSet)ScriptBytecodeAdapter.castToType(siteArr0[150].callConstructor(HashSet.class, allClassSet), HashSet.class);
        siteArr0[151].call(hashSet1, allClassSet);
        siteArr0[152].call(hashSet2, (HashSet)dexClassSet.get());
        if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && ImmutableDexTransform.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 700;
            if (! ScriptBytecodeAdapter.compareNotEqual(siteArr0[172].call(hashSet1), Integer.valueOf(0)) || ScriptBytecodeAdapter.compareNotEqual(siteArr0[173].call(hashSet2), Integer.valueOf(0)) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[174].callConstructor(GradleException.class, siteArr0[175].call(siteArr0[176].call(siteArr0[177].call(siteArr0[178].call(siteArr0[179].call(siteArr0[180].call(siteArr0[181].call(siteArr0[182].call(siteArr0[183].call(siteArr0[184].call(siteArr0[185].call(siteArr0[186].call("class is inconsistent! ", "
	"), "allClassSet size is "), siteArr0[187].call(allClassSet)), ",dexClassSet size is "), siteArr0[188].call((HashSet)dexClassSet.get())), "
"), "allClassSet has extra class: "), hashSet2), ",
"), "dexClassSet has extra class: "), hashSet1), ".
"));
            }
            else {
                siteArr0[189].call(siteArr0[190].callGetProperty(this.project), "check class consistence successful! ");
            }
        }
        else {
            if (! ScriptBytecodeAdapter.compareNotEqual(siteArr0[153].call(hashSet1), Integer.valueOf(0)) || ScriptBytecodeAdapter.compareNotEqual(siteArr0[154].call(hashSet2), Integer.valueOf(0)) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[155].callConstructor(GradleException.class, siteArr0[156].call(siteArr0[157].call(siteArr0[158].call(siteArr0[159].call(siteArr0[160].call(siteArr0[161].call(siteArr0[162].call(siteArr0[163].call(siteArr0[164].call(siteArr0[165].call(siteArr0[166].call(siteArr0[167].call("class is inconsistent! ", "
	"), "allClassSet size is "), siteArr0[168].call(allClassSet)), ",dexClassSet size is "), siteArr0[169].call((HashSet)dexClassSet.get())), "
"), "allClassSet has extra class: "), hashSet2), ",
"), "dexClassSet has extra class: "), hashSet1), ".
"));
            }
            else {
                siteArr0[170].call(siteArr0[171].callGetProperty(this.project), "check class consistence successful! ");
            }
        }
    }

    public boolean writeClassToZip(HashMap<String, DexRefData> methodAndFieldsNum, HashMap<String, ZipOutputStream> osMap, String belongDex, byte[] bytes, ZipEntry zipEntry) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        File jarFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[191].callConstructor(File.class, this.classPreDir, siteArr0[192].call(belongDex, ".jar")), File.class);
        DexRefData mfData = (DexRefData)ScriptBytecodeAdapter.castToType(siteArr0[193].call(methodAndFieldsNum, siteArr0[194].callGetProperty(jarFile)), DexRefData.class);
        if (ScriptBytecodeAdapter.compareEqual(mfData, null)) {
            Object object = siteArr0[195].callConstructor(DexRefData.class);
            mfData = (DexRefData)ScriptBytecodeAdapter.castToType(object, DexRefData.class);
            siteArr0[196].call(methodAndFieldsNum, siteArr0[197].callGetProperty(jarFile), mfData);
        }
        ClassSimDef cf = (ClassSimDef)ScriptBytecodeAdapter.castToType(siteArr0[198].callConstructor(ClassSimDef.class, bytes, siteArr0[199].callGetProperty(mfData), siteArr0[200].callGetProperty(mfData)), ClassSimDef.class);
        ZipOutputStream zos = (ZipOutputStream)ScriptBytecodeAdapter.castToType(siteArr0[201].call(osMap, belongDex), ZipOutputStream.class);
        if (ScriptBytecodeAdapter.compareEqual(zos, null)) {
            siteArr0[202].call(siteArr0[203].callGetProperty(this.project), new GStringImpl(new Object[]{jarFile}, new String[]{"jarFile is  ", "."}));
            Object objectVar1 = siteArr0[204].callConstructor(ZipOutputStream.class, siteArr0[205].callConstructor(FileOutputStream.class, jarFile));
            zos = (ZipOutputStream)ScriptBytecodeAdapter.castToType(objectVar1, ZipOutputStream.class);
            siteArr0[206].call(osMap, belongDex, zos);
        }
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[207].callCurrent(this, ArrayUtil.createArray(mfData, cf, zos, zipEntry, bytes))) ? 0 : 1 != 0) {
            siteArr0[208].call(siteArr0[209].callGetProperty(this.project), new GStringImpl(new Object[]{siteArr0[210].callGetProperty(zipEntry), siteArr0[211].callGetProperty(mfData), siteArr0[212].callGetProperty(mfData), belongDex}, new String[]{"except limit! 
 	find class ", " method num: ", ",field num: ", ",belong dex: ", " "}));
            return false;
        }
        else {
            return true;
        }
    }

    public boolean writeClassToZipNoCheck(DexRefData mfData, ClassSimDef cf, ZipOutputStream zos, ZipEntry zipEntry, byte[] bytes) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && ImmutableDexTransform.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 283;
            if (! ScriptBytecodeAdapter.compareGreaterThanEqual(siteArr0[228].call(siteArr0[229].callGetProperty(mfData), siteArr0[230].callGetProperty(cf)), Integer.valueOf(65536)) || ScriptBytecodeAdapter.compareGreaterThanEqual(siteArr0[231].call(siteArr0[232].callGetProperty(mfData), siteArr0[233].callGetProperty(cf)), Integer.valueOf(64536)) ? 0 : 1 != 0) {
                return false;
            }
            else {
                v_54 = siteArr0[234].call(siteArr0[235].callGetProperty(mfData), siteArr0[236].callGetProperty(cf));
                ScriptBytecodeAdapter.setProperty(siteArr0[234].call(siteArr0[235].callGetProperty(mfData), siteArr0[236].callGetProperty(cf)), null, mfData, (String)"methodNum");
                v_72 = siteArr0[237].call(siteArr0[238].callGetProperty(mfData), siteArr0[239].callGetProperty(cf));
                ScriptBytecodeAdapter.setProperty(siteArr0[237].call(siteArr0[238].callGetProperty(mfData), siteArr0[239].callGetProperty(cf)), null, mfData, (String)"fieldNum");
                siteArr0[240].call(zos, zipEntry);
                siteArr0[241].call(zos, bytes);
                siteArr0[242].call(zos);
                return true;
            }
        }
        else {
            if (! ScriptBytecodeAdapter.compareGreaterThanEqual(siteArr0[213].call(siteArr0[214].callGetProperty(mfData), siteArr0[215].callGetProperty(cf)), Integer.valueOf(65536)) || ScriptBytecodeAdapter.compareGreaterThanEqual(siteArr0[216].call(siteArr0[217].callGetProperty(mfData), siteArr0[218].callGetProperty(cf)), Integer.valueOf(64536)) ? 0 : 1 != 0) {
                return false;
            }
            else {
                v_145 = siteArr0[219].call(siteArr0[220].callGetProperty(mfData), siteArr0[221].callGetProperty(cf));
                ScriptBytecodeAdapter.setProperty(siteArr0[219].call(siteArr0[220].callGetProperty(mfData), siteArr0[221].callGetProperty(cf)), null, mfData, (String)"methodNum");
                v_163 = siteArr0[222].call(siteArr0[223].callGetProperty(mfData), siteArr0[224].callGetProperty(cf));
                ScriptBytecodeAdapter.setProperty(siteArr0[222].call(siteArr0[223].callGetProperty(mfData), siteArr0[224].callGetProperty(cf)), null, mfData, (String)"fieldNum");
                siteArr0[225].call(zos, zipEntry);
                siteArr0[226].call(zos, bytes);
                siteArr0[227].call(zos);
                return true;
            }
        }
    }

    public void saveOrphan(HashMap<ZipEntry, ByteArrayOutputStream> orphanMap, ZipEntry zipEntry, byte[] bytes) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        ByteArrayOutputStream bos = (ByteArrayOutputStream)ScriptBytecodeAdapter.castToType(siteArr0[243].callConstructor(ByteArrayOutputStream.class, siteArr0[244].callGetProperty(bytes)), ByteArrayOutputStream.class);
        siteArr0[245].call(bos, bytes, Integer.valueOf(0), siteArr0[246].callGetProperty(bytes));
        siteArr0[247].call(bos);
        siteArr0[248].call(orphanMap, zipEntry, bos);
    }

    public static String getNextClassName(int index) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        if (BytecodeInterface8.isOrigInt() && ImmutableDexTransform.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 83;
            return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{Integer.valueOf(index + 1)}, new String[]{"classes", ".dex"}));
        }
        else {
            return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[249].call(Integer.valueOf(index), Integer.valueOf(1))}, new String[]{"classes", ".dex"}));
        }
    }

    public String dexIndexToName(int index, String suffix) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && ImmutableDexTransform.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 88;
            return (String)ShortTypeHandling.castToString(siteArr0[252].call(siteArr0[253].call("classes", index == 1 ? 0 : 1 != 0 ? Integer.valueOf(index) : ""), suffix));
        }
        else {
            return (String)ShortTypeHandling.castToString(siteArr0[250].call(siteArr0[251].call("classes", index == 1 ? 0 : 1 != 0 ? Integer.valueOf(index) : ""), suffix));
        }
    }

    public String belongTo(HashMap<String, String> pathDexMap, String classPath) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[254].call(pathDexMap, classPath));
    }

    public static void traversal(ZipFile zipFile, Closure callback) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        try {
            try {
                Enumeration enumeration = (Enumeration)ScriptBytecodeAdapter.castToType(siteArr0[255].call(zipFile), Enumeration.class);
                while (DefaultTypeTransformation.booleanUnbox(siteArr0[256].call(enumeration))) {
                    ZipEntry entry = (ZipEntry)ScriptBytecodeAdapter.castToType(siteArr0[257].call(enumeration), ZipEntry.class);
                    siteArr0[258].call(callback, entry, siteArr0[259].callGetProperty(siteArr0[260].call(zipFile, entry)));
                }
            }
            catch (IOException e) {
                siteArr0[261].call(e);
                siteArr0[262].call(Utils.class, zipFile);
            }
            return;
        }
        finally {
            Throwable throwable = v_11;
            throw throwable;
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ImmutableDexTransform.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (ImmutableDexTransform.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                ImmutableDexTransform.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return ImmutableDexTransform.$staticClassInfo.getMetaClass();
        }
    }

    public /* synthetic */ Object this$dist$invoke$2(String name, Object args) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(ImmutableDexTransform.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[]{}, new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$2(String name, Object value) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty(value, ImmutableDexTransform.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
    }

    public /* synthetic */ Object this$dist$get$2(String name) {
        CallSite[] siteArr0 = ImmutableDexTransform.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectProperty(ImmutableDexTransform.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
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
        Object object = ImmutableDexTransform.$getCallSiteArray()[263].call(Joiner.class, ImmutableDexTransform.$getCallSiteArray()[264].callGetProperty(File.class));
        ImmutableDexTransform.PATH_JOINER = (Joiner)ScriptBytecodeAdapter.castToType(object, Joiner.class);
    }

    @Generated
    public Project getProject() {
        return this.project;
    }

    @Generated
    public void setProject(Project project) {
        this.project = project;
    }

    @Generated
    public String getOldApkPath() {
        return this.oldApkPath;
    }

    @Generated
    public void setOldApkPath(String str0) {
        this.oldApkPath = str0;
    }

    @Generated
    public File getClassPreDir() {
        return this.classPreDir;
    }

    @Generated
    public void setClassPreDir(File file) {
        this.classPreDir = file;
    }

    @Generated
    public File getBaseDexDir() {
        return this.baseDexDir;
    }

    @Generated
    public void setBaseDexDir(File file) {
        this.baseDexDir = file;
    }

    @Generated
    public File getMainDexListFile() {
        return this.mainDexListFile;
    }

    @Generated
    public void setMainDexListFile(File file) {
        this.mainDexListFile = file;
    }

    @Generated
    public String getVarName() {
        return this.varName;
    }

    @Generated
    public void setVarName(String str0) {
        this.varName = str0;
    }

    @Generated
    public String getVarDirName() {
        return this.varDirName;
    }

    @Generated
    public void setVarDirName(String str0) {
        this.varDirName = str0;
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
    public Object getDexTransform() {
        return this.dexTransform;
    }

    @Generated
    public void setDexTransform(Object object) {
        this.dexTransform = object;
    }

    public /* synthetic */ Set super$2$getOutputTypes() {
        return this.getOutputTypes();
    }

    public /* synthetic */ void super$2$transform(TransformInvocation invocation) {
        this.transform(invocation);
    }

    public /* synthetic */ Collection super$2$getSecondaryFileInputs() {
        return this.getSecondaryFileInputs();
    }

    public /* synthetic */ Collection super$2$getSecondaryDirectoryOutputs() {
        return this.getSecondaryDirectoryOutputs();
    }

    public /* synthetic */ Map super$2$getParameterInputs() {
        return this.getParameterInputs();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "capitalize";
        stringArr0[1] = "name";
        stringArr0[2] = "getDirName";
        stringArr0[3] = "oldApk";
        stringArr0[4] = "tinkerPatch";
        stringArr0[5] = "mainDexListFile";
        stringArr0[6] = "mainDexListFile";
        stringArr0[7] = "getSingleFile";
        stringArr0[8] = "mainDexListFile";
        stringArr0[9] = "getDirInWorkDir";
        stringArr0[10] = "getDirInWorkDir";
        stringArr0[11] = "mkdirs";
        stringArr0[12] = "mkdirs";
        stringArr0[13] = "cleanDir";
        stringArr0[14] = "cleanDir";
        stringArr0[15] = "<$constructor$>";
        stringArr0[16] = "join";
        stringArr0[17] = "getTinkerIntermediates";
        stringArr0[18] = "getOutputTypes";
        stringArr0[19] = "getSecondaryFileInputs";
        stringArr0[20] = "getSecondaryDirectoryOutputs";
        stringArr0[21] = "getParameterInputs";
        stringArr0[22] = "getName";
        stringArr0[23] = "getInputTypes";
        stringArr0[24] = "getScopes";
        stringArr0[25] = "isIncremental";
        stringArr0[26] = "newArrayList";
        stringArr0[27] = "iterator";
        stringArr0[28] = "getInputs";
        stringArr0[29] = "addAll";
        stringArr0[30] = "getJarInputs";
        stringArr0[31] = "size";
        stringArr0[32] = "error";
        stringArr0[33] = "logger";
        stringArr0[34] = "size";
        stringArr0[35] = "transform";
        stringArr0[36] = "initFileEnv";
        stringArr0[37] = "getOutputProvider";
        stringArr0[38] = "<$constructor$>";
        stringArr0[39] = "traversal";
        stringArr0[40] = "<$constructor$>";
        stringArr0[41] = "<$constructor$>";
        stringArr0[42] = "info";
        stringArr0[43] = "logger";
        stringArr0[44] = "each";
        stringArr0[45] = "size";
        stringArr0[46] = "initMainDexSet";
        stringArr0[47] = "info";
        stringArr0[48] = "logger";
        stringArr0[49] = "<$constructor$>";
        stringArr0[50] = "<$constructor$>";
        stringArr0[51] = "<$constructor$>";
        stringArr0[52] = "<$constructor$>";
        stringArr0[53] = "processJar";
        stringArr0[54] = "file";
        stringArr0[55] = "get";
        stringArr0[56] = "iterator";
        stringArr0[57] = "entrySet";
        stringArr0[58] = "hasNext";
        stringArr0[59] = "hasNext";
        stringArr0[60] = "dexIndexToName";
        stringArr0[61] = "info";
        stringArr0[62] = "logger";
        stringArr0[63] = "name";
        stringArr0[64] = "key";
        stringArr0[65] = "writeClassToZip";
        stringArr0[66] = "toByteArray";
        stringArr0[67] = "value";
        stringArr0[68] = "key";
        stringArr0[69] = "<$constructor$>";
        stringArr0[70] = "plus";
        stringArr0[71] = "plus";
        stringArr0[72] = "plus";
        stringArr0[73] = "plus";
        stringArr0[74] = "name";
        stringArr0[75] = "key";
        stringArr0[76] = "next";
        stringArr0[77] = "dexIndexToName";
        stringArr0[78] = "info";
        stringArr0[79] = "logger";
        stringArr0[80] = "name";
        stringArr0[81] = "key";
        stringArr0[82] = "writeClassToZip";
        stringArr0[83] = "toByteArray";
        stringArr0[84] = "value";
        stringArr0[85] = "key";
        stringArr0[86] = "next";
        stringArr0[87] = "each";
        stringArr0[88] = "<$constructor$>";
        stringArr0[89] = "getContentLocation";
        stringArr0[90] = "outputProvider";
        stringArr0[91] = "getOutputTypes";
        stringArr0[92] = "SCOPE_FULL_PROJECT";
        stringArr0[93] = "DIRECTORY";
        stringArr0[94] = "getContentLocation";
        stringArr0[95] = "outputProvider";
        stringArr0[96] = "SCOPE_FULL_PROJECT";
        stringArr0[97] = "DIRECTORY";
        stringArr0[98] = "exists";
        stringArr0[99] = "cleanDir";
        stringArr0[100] = "mkdirs";
        stringArr0[101] = "eachFile";
        stringArr0[102] = "checkClassConsistence";
        stringArr0[103] = "<$constructor$>";
        stringArr0[104] = "traversal";
        stringArr0[105] = "traversal";
        stringArr0[106] = "<$constructor$>";
        stringArr0[107] = "newReader";
        stringArr0[108] = "readLines";
        stringArr0[109] = "each";
        stringArr0[110] = "lastIndexOf";
        stringArr0[111] = "substring";
        stringArr0[112] = "substring";
        stringArr0[113] = "getSdkDirectory";
        stringArr0[114] = "android";
        stringArr0[115] = "buildToolsVersion";
        stringArr0[116] = "android";
        stringArr0[117] = "create";
        stringArr0[118] = "tasks";
        stringArr0[119] = "plus";
        stringArr0[120] = "plus";
        stringArr0[121] = "minus";
        stringArr0[122] = "name";
        stringArr0[123] = "execute";
        stringArr0[124] = "info";
        stringArr0[125] = "logger";
        stringArr0[126] = "multiDexEnabled";
        stringArr0[127] = "mergedFlavor";
        stringArr0[128] = "warn";
        stringArr0[129] = "logger";
        stringArr0[130] = "isLegalFile";
        stringArr0[131] = "oldApk";
        stringArr0[132] = "tinkerPatch";
        stringArr0[133] = "warn";
        stringArr0[134] = "logger";
        stringArr0[135] = "addTaskExecutionGraphListener";
        stringArr0[136] = "getTaskGraph";
        stringArr0[137] = "getGradle";
        stringArr0[138] = "info";
        stringArr0[139] = "logger";
        stringArr0[140] = "size";
        stringArr0[141] = "<$constructor$>";
        stringArr0[142] = "size";
        stringArr0[143] = "<$constructor$>";
        stringArr0[144] = "info";
        stringArr0[145] = "logger";
        stringArr0[146] = "plus";
        stringArr0[147] = "<$constructor$>";
        stringArr0[148] = "each";
        stringArr0[149] = "<$constructor$>";
        stringArr0[150] = "<$constructor$>";
        stringArr0[151] = "removeAll";
        stringArr0[152] = "removeAll";
        stringArr0[153] = "size";
        stringArr0[154] = "size";
        stringArr0[155] = "<$constructor$>";
        stringArr0[156] = "plus";
        stringArr0[157] = "plus";
        stringArr0[158] = "plus";
        stringArr0[159] = "plus";
        stringArr0[160] = "plus";
        stringArr0[161] = "plus";
        stringArr0[162] = "plus";
        stringArr0[163] = "plus";
        stringArr0[164] = "plus";
        stringArr0[165] = "plus";
        stringArr0[166] = "plus";
        stringArr0[167] = "plus";
        stringArr0[168] = "size";
        stringArr0[169] = "size";
        stringArr0[170] = "info";
        stringArr0[171] = "logger";
        stringArr0[172] = "size";
        stringArr0[173] = "size";
        stringArr0[174] = "<$constructor$>";
        stringArr0[175] = "plus";
        stringArr0[176] = "plus";
        stringArr0[177] = "plus";
        stringArr0[178] = "plus";
        stringArr0[179] = "plus";
        stringArr0[180] = "plus";
        stringArr0[181] = "plus";
        stringArr0[182] = "plus";
        stringArr0[183] = "plus";
        stringArr0[184] = "plus";
        stringArr0[185] = "plus";
        stringArr0[186] = "plus";
        stringArr0[187] = "size";
        stringArr0[188] = "size";
        stringArr0[189] = "info";
        stringArr0[190] = "logger";
        stringArr0[191] = "<$constructor$>";
        stringArr0[192] = "plus";
        stringArr0[193] = "get";
        stringArr0[194] = "name";
        stringArr0[195] = "<$constructor$>";
        stringArr0[196] = "put";
        stringArr0[197] = "name";
        stringArr0[198] = "<$constructor$>";
        stringArr0[199] = "refFields";
        stringArr0[200] = "refMtds";
        stringArr0[201] = "get";
        stringArr0[202] = "info";
        stringArr0[203] = "logger";
        stringArr0[204] = "<$constructor$>";
        stringArr0[205] = "<$constructor$>";
        stringArr0[206] = "put";
        stringArr0[207] = "writeClassToZipNoCheck";
        stringArr0[208] = "error";
        stringArr0[209] = "logger";
        stringArr0[210] = "name";
        stringArr0[211] = "methodNum";
        stringArr0[212] = "fieldNum";
        stringArr0[213] = "plus";
        stringArr0[214] = "methodNum";
        stringArr0[215] = "methodCount";
        stringArr0[216] = "plus";
        stringArr0[217] = "fieldNum";
        stringArr0[218] = "fieldCount";
        stringArr0[219] = "plus";
        stringArr0[220] = "methodNum";
        stringArr0[221] = "methodCount";
        stringArr0[222] = "plus";
        stringArr0[223] = "fieldNum";
        stringArr0[224] = "fieldCount";
        stringArr0[225] = "putNextEntry";
        stringArr0[226] = "write";
        stringArr0[227] = "closeEntry";
        stringArr0[228] = "plus";
        stringArr0[229] = "methodNum";
        stringArr0[230] = "methodCount";
        stringArr0[231] = "plus";
        stringArr0[232] = "fieldNum";
        stringArr0[233] = "fieldCount";
        stringArr0[234] = "plus";
        stringArr0[235] = "methodNum";
        stringArr0[236] = "methodCount";
        stringArr0[237] = "plus";
        stringArr0[238] = "fieldNum";
        stringArr0[239] = "fieldCount";
        stringArr0[240] = "putNextEntry";
        stringArr0[241] = "write";
        stringArr0[242] = "closeEntry";
        stringArr0[243] = "<$constructor$>";
        stringArr0[244] = "length";
        stringArr0[245] = "write";
        stringArr0[246] = "length";
        stringArr0[247] = "flush";
        stringArr0[248] = "put";
        stringArr0[249] = "plus";
        stringArr0[250] = "plus";
        stringArr0[251] = "plus";
        stringArr0[252] = "plus";
        stringArr0[253] = "plus";
        stringArr0[254] = "get";
        stringArr0[255] = "entries";
        stringArr0[256] = "hasMoreElements";
        stringArr0[257] = "nextElement";
        stringArr0[258] = "call";
        stringArr0[259] = "bytes";
        stringArr0[260] = "getInputStream";
        stringArr0[261] = "printStackTrace";
        stringArr0[262] = "closeQuietly";
        stringArr0[263] = "on";
        stringArr0[264] = "separatorChar";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        ImmutableDexTransform.$createCallSiteArray_1(str0);
        return new CallSiteArray(ImmutableDexTransform.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = ImmutableDexTransform.$callSiteArray != null ? ImmutableDexTransform.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform.$callSiteArray.get();
        ImmutableDexTransform.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_processJar_closure6
    public final class ImmutableDexTransform$_processJar_closure6 implements GeneratedClosure {
        private synthetic Reference allClassSet;
        private synthetic Reference pathDexMap;
        private synthetic Reference methodAndFieldsNum;
        private synthetic Reference osMap;
        private synthetic Reference orphanMap;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_processJar_closure6(Object _outerInstance, Object _thisObject, Reference allClassSet, Reference pathDexMap, Reference methodAndFieldsNum, Reference osMap, Reference orphanMap) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            allClassSet.allClassSet = this;
            pathDexMap.pathDexMap = this;
            methodAndFieldsNum.methodAndFieldsNum = this;
            osMap.osMap = this;
            orphanMap.orphanMap = this;
        }

        public Object doCall(ZipEntry zipEntry, byte[] bytes) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(zipEntry), ".class"))) {
                String classPath = (String)ShortTypeHandling.castToString(siteArr0[2].callCurrent(this, siteArr0[3].callGetProperty(zipEntry)));
                String belongDex;
                if (BytecodeInterface8.isOrigZ() && ImmutableDexTransform$_processJar_closure6.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[22].call(Utils.class, classPath)) ? 0 : 1 != 0) {
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[23].call(this.allClassSet.get(), classPath)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                        siteArr0[24].call(this.allClassSet.get(), classPath);
                        belongDex = (String)ShortTypeHandling.castToString(siteArr0[25].callCurrent(this, this.pathDexMap.get(), classPath));
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[26].call(Utils.class, belongDex))) {
                        }
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[27].callCurrent(this, ArrayUtil.createArray(this.methodAndFieldsNum.get(), this.osMap.get(), belongDex, bytes, zipEntry))) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                            if (DefaultTypeTransformation.booleanUnbox(siteArr0[28].call(Utils.class, belongDex))) {
                                siteArr0[29].call(siteArr0[30].callGetProperty(siteArr0[31].callGroovyObjectGetProperty(this)), siteArr0[32].call("find new class: ", classPath));
                            }
                            return siteArr0[33].callCurrent(this, this.orphanMap.get(), zipEntry, bytes);
                        }
                        else {
                            return null;
                        }
                    }
                    else if (DefaultTypeTransformation.booleanUnbox(siteArr0[34].call(Utils.class, classPath))) {
                        return siteArr0[35].call(siteArr0[36].callGetProperty(siteArr0[37].callGroovyObjectGetProperty(this)), siteArr0[38].call("illegal zip entry: ", siteArr0[39].callGetProperty(zipEntry)));
                    }
                    else {
                        return null;
                    }
                }
                else {
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[4].call(Utils.class, classPath)) ? 0 : 1 != 0) {
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(this.allClassSet.get(), classPath)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                        siteArr0[6].call(this.allClassSet.get(), classPath);
                        belongDex = (String)ShortTypeHandling.castToString(siteArr0[7].callCurrent(this, this.pathDexMap.get(), classPath));
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[8].call(Utils.class, belongDex))) {
                        }
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[9].callCurrent(this, ArrayUtil.createArray(this.methodAndFieldsNum.get(), this.osMap.get(), belongDex, bytes, zipEntry))) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                            if (DefaultTypeTransformation.booleanUnbox(siteArr0[10].call(Utils.class, belongDex))) {
                                siteArr0[11].call(siteArr0[12].callGetProperty(siteArr0[13].callGroovyObjectGetProperty(this)), siteArr0[14].call("find new class: ", classPath));
                            }
                            return siteArr0[15].callCurrent(this, this.orphanMap.get(), zipEntry, bytes);
                        }
                        else {
                            return null;
                        }
                    }
                    else if (DefaultTypeTransformation.booleanUnbox(siteArr0[16].call(Utils.class, classPath))) {
                        return siteArr0[17].call(siteArr0[18].callGetProperty(siteArr0[19].callGroovyObjectGetProperty(this)), siteArr0[20].call("illegal zip entry: ", siteArr0[21].callGetProperty(zipEntry)));
                    }
                    else {
                        return null;
                    }
                }
            }
            else {
                return null;
            }
        }

        public Object call(ZipEntry zipEntry, byte[] bytes) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return siteArr0[40].callCurrent(this, zipEntry, bytes);
        }

        @Generated
        public HashSet getAllClassSet() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return (HashSet)ScriptBytecodeAdapter.castToType(this.allClassSet.get(), HashSet.class);
        }

        @Generated
        public HashMap getPathDexMap() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.pathDexMap.get(), HashMap.class);
        }

        @Generated
        public HashMap getMethodAndFieldsNum() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.methodAndFieldsNum.get(), HashMap.class);
        }

        @Generated
        public HashMap getOsMap() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.osMap.get(), HashMap.class);
        }

        @Generated
        public HashMap getOrphanMap() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.orphanMap.get(), HashMap.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_processJar_closure6.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_processJar_closure6.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_processJar_closure6.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_processJar_closure6.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "rePathToClassPath";
            stringArr0[3] = "name";
            stringArr0[4] = "isBlank";
            stringArr0[5] = "contains";
            stringArr0[6] = "add";
            stringArr0[7] = "belongTo";
            stringArr0[8] = "isBlank";
            stringArr0[9] = "writeClassToZip";
            stringArr0[10] = "isBlank";
            stringArr0[11] = "warn";
            stringArr0[12] = "logger";
            stringArr0[13] = "project";
            stringArr0[14] = "plus";
            stringArr0[15] = "saveOrphan";
            stringArr0[16] = "isBlank";
            stringArr0[17] = "error";
            stringArr0[18] = "logger";
            stringArr0[19] = "project";
            stringArr0[20] = "plus";
            stringArr0[21] = "name";
            stringArr0[22] = "isBlank";
            stringArr0[23] = "contains";
            stringArr0[24] = "add";
            stringArr0[25] = "belongTo";
            stringArr0[26] = "isBlank";
            stringArr0[27] = "writeClassToZip";
            stringArr0[28] = "isBlank";
            stringArr0[29] = "warn";
            stringArr0[30] = "logger";
            stringArr0[31] = "project";
            stringArr0[32] = "plus";
            stringArr0[33] = "saveOrphan";
            stringArr0[34] = "isBlank";
            stringArr0[35] = "error";
            stringArr0[36] = "logger";
            stringArr0[37] = "project";
            stringArr0[38] = "plus";
            stringArr0[39] = "name";
            stringArr0[40] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_processJar_closure6.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_processJar_closure6.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_processJar_closure6.$callSiteArray != null ? ImmutableDexTransform$_processJar_closure6.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_processJar_closure6.$callSiteArray.get();
            ImmutableDexTransform$_processJar_closure6.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_processJar_closure6
    public final class ImmutableDexTransform$_processJar_closure6 implements GeneratedClosure {
        private synthetic Reference allClassSet;
        private synthetic Reference pathDexMap;
        private synthetic Reference methodAndFieldsNum;
        private synthetic Reference osMap;
        private synthetic Reference orphanMap;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_processJar_closure6(Object _outerInstance, Object _thisObject, Reference allClassSet, Reference pathDexMap, Reference methodAndFieldsNum, Reference osMap, Reference orphanMap) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            allClassSet.allClassSet = this;
            pathDexMap.pathDexMap = this;
            methodAndFieldsNum.methodAndFieldsNum = this;
            osMap.osMap = this;
            orphanMap.orphanMap = this;
        }

        public Object doCall(ZipEntry zipEntry, byte[] bytes) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(zipEntry), ".class"))) {
                String classPath = (String)ShortTypeHandling.castToString(siteArr0[2].callCurrent(this, siteArr0[3].callGetProperty(zipEntry)));
                String belongDex;
                if (BytecodeInterface8.isOrigZ() && ImmutableDexTransform$_processJar_closure6.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[22].call(Utils.class, classPath)) ? 0 : 1 != 0) {
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[23].call(this.allClassSet.get(), classPath)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                        siteArr0[24].call(this.allClassSet.get(), classPath);
                        belongDex = (String)ShortTypeHandling.castToString(siteArr0[25].callCurrent(this, this.pathDexMap.get(), classPath));
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[26].call(Utils.class, belongDex))) {
                        }
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[27].callCurrent(this, ArrayUtil.createArray(this.methodAndFieldsNum.get(), this.osMap.get(), belongDex, bytes, zipEntry))) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                            if (DefaultTypeTransformation.booleanUnbox(siteArr0[28].call(Utils.class, belongDex))) {
                                siteArr0[29].call(siteArr0[30].callGetProperty(siteArr0[31].callGroovyObjectGetProperty(this)), siteArr0[32].call("find new class: ", classPath));
                            }
                            return siteArr0[33].callCurrent(this, this.orphanMap.get(), zipEntry, bytes);
                        }
                        else {
                            return null;
                        }
                    }
                    else if (DefaultTypeTransformation.booleanUnbox(siteArr0[34].call(Utils.class, classPath))) {
                        return siteArr0[35].call(siteArr0[36].callGetProperty(siteArr0[37].callGroovyObjectGetProperty(this)), siteArr0[38].call("illegal zip entry: ", siteArr0[39].callGetProperty(zipEntry)));
                    }
                    else {
                        return null;
                    }
                }
                else {
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[4].call(Utils.class, classPath)) ? 0 : 1 != 0) {
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(this.allClassSet.get(), classPath)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                        siteArr0[6].call(this.allClassSet.get(), classPath);
                        belongDex = (String)ShortTypeHandling.castToString(siteArr0[7].callCurrent(this, this.pathDexMap.get(), classPath));
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[8].call(Utils.class, belongDex))) {
                        }
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[9].callCurrent(this, ArrayUtil.createArray(this.methodAndFieldsNum.get(), this.osMap.get(), belongDex, bytes, zipEntry))) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                            if (DefaultTypeTransformation.booleanUnbox(siteArr0[10].call(Utils.class, belongDex))) {
                                siteArr0[11].call(siteArr0[12].callGetProperty(siteArr0[13].callGroovyObjectGetProperty(this)), siteArr0[14].call("find new class: ", classPath));
                            }
                            return siteArr0[15].callCurrent(this, this.orphanMap.get(), zipEntry, bytes);
                        }
                        else {
                            return null;
                        }
                    }
                    else if (DefaultTypeTransformation.booleanUnbox(siteArr0[16].call(Utils.class, classPath))) {
                        return siteArr0[17].call(siteArr0[18].callGetProperty(siteArr0[19].callGroovyObjectGetProperty(this)), siteArr0[20].call("illegal zip entry: ", siteArr0[21].callGetProperty(zipEntry)));
                    }
                    else {
                        return null;
                    }
                }
            }
            else {
                return null;
            }
        }

        public Object call(ZipEntry zipEntry, byte[] bytes) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return siteArr0[40].callCurrent(this, zipEntry, bytes);
        }

        @Generated
        public HashSet getAllClassSet() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return (HashSet)ScriptBytecodeAdapter.castToType(this.allClassSet.get(), HashSet.class);
        }

        @Generated
        public HashMap getPathDexMap() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.pathDexMap.get(), HashMap.class);
        }

        @Generated
        public HashMap getMethodAndFieldsNum() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.methodAndFieldsNum.get(), HashMap.class);
        }

        @Generated
        public HashMap getOsMap() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.osMap.get(), HashMap.class);
        }

        @Generated
        public HashMap getOrphanMap() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure6.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.orphanMap.get(), HashMap.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_processJar_closure6.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_processJar_closure6.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_processJar_closure6.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_processJar_closure6.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "rePathToClassPath";
            stringArr0[3] = "name";
            stringArr0[4] = "isBlank";
            stringArr0[5] = "contains";
            stringArr0[6] = "add";
            stringArr0[7] = "belongTo";
            stringArr0[8] = "isBlank";
            stringArr0[9] = "writeClassToZip";
            stringArr0[10] = "isBlank";
            stringArr0[11] = "warn";
            stringArr0[12] = "logger";
            stringArr0[13] = "project";
            stringArr0[14] = "plus";
            stringArr0[15] = "saveOrphan";
            stringArr0[16] = "isBlank";
            stringArr0[17] = "error";
            stringArr0[18] = "logger";
            stringArr0[19] = "project";
            stringArr0[20] = "plus";
            stringArr0[21] = "name";
            stringArr0[22] = "isBlank";
            stringArr0[23] = "contains";
            stringArr0[24] = "add";
            stringArr0[25] = "belongTo";
            stringArr0[26] = "isBlank";
            stringArr0[27] = "writeClassToZip";
            stringArr0[28] = "isBlank";
            stringArr0[29] = "warn";
            stringArr0[30] = "logger";
            stringArr0[31] = "project";
            stringArr0[32] = "plus";
            stringArr0[33] = "saveOrphan";
            stringArr0[34] = "isBlank";
            stringArr0[35] = "error";
            stringArr0[36] = "logger";
            stringArr0[37] = "project";
            stringArr0[38] = "plus";
            stringArr0[39] = "name";
            stringArr0[40] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_processJar_closure6.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_processJar_closure6.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_processJar_closure6.$callSiteArray != null ? ImmutableDexTransform$_processJar_closure6.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_processJar_closure6.$callSiteArray.get();
            ImmutableDexTransform$_processJar_closure6.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_initMainDexSet_closure7
    public final class ImmutableDexTransform$_initMainDexSet_closure7 implements GeneratedClosure {
        private synthetic Reference mainDexSets;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_initMainDexSet_closure7(Object _outerInstance, Object _thisObject, Reference mainDexSets) {
            CallSite[] siteArr0 = ImmutableDexTransform$_initMainDexSet_closure7.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            mainDexSets.mainDexSets = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = ImmutableDexTransform$_initMainDexSet_closure7.$getCallSiteArray();
            return siteArr0[0].call(this.mainDexSets.get(), it);
        }

        @Generated
        public HashSet getMainDexSets() {
            CallSite[] siteArr0 = ImmutableDexTransform$_initMainDexSet_closure7.$getCallSiteArray();
            return (HashSet)ScriptBytecodeAdapter.castToType(this.mainDexSets.get(), HashSet.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = ImmutableDexTransform$_initMainDexSet_closure7.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_initMainDexSet_closure7.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_initMainDexSet_closure7.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_initMainDexSet_closure7.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_initMainDexSet_closure7.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "add";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_initMainDexSet_closure7.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_initMainDexSet_closure7.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_initMainDexSet_closure7.$callSiteArray != null ? ImmutableDexTransform$_initMainDexSet_closure7.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_initMainDexSet_closure7.$callSiteArray.get();
            ImmutableDexTransform$_initMainDexSet_closure7.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_initMainDexSet_closure7
    public final class ImmutableDexTransform$_initMainDexSet_closure7 implements GeneratedClosure {
        private synthetic Reference mainDexSets;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_initMainDexSet_closure7(Object _outerInstance, Object _thisObject, Reference mainDexSets) {
            CallSite[] siteArr0 = ImmutableDexTransform$_initMainDexSet_closure7.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            mainDexSets.mainDexSets = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = ImmutableDexTransform$_initMainDexSet_closure7.$getCallSiteArray();
            return siteArr0[0].call(this.mainDexSets.get(), it);
        }

        @Generated
        public HashSet getMainDexSets() {
            CallSite[] siteArr0 = ImmutableDexTransform$_initMainDexSet_closure7.$getCallSiteArray();
            return (HashSet)ScriptBytecodeAdapter.castToType(this.mainDexSets.get(), HashSet.class);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = ImmutableDexTransform$_initMainDexSet_closure7.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_initMainDexSet_closure7.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_initMainDexSet_closure7.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_initMainDexSet_closure7.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_initMainDexSet_closure7.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "add";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_initMainDexSet_closure7.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_initMainDexSet_closure7.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_initMainDexSet_closure7.$callSiteArray != null ? ImmutableDexTransform$_initMainDexSet_closure7.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_initMainDexSet_closure7.$callSiteArray.get();
            ImmutableDexTransform$_initMainDexSet_closure7.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure2
    public final class ImmutableDexTransform$_transform_closure2 implements GeneratedClosure {
        private synthetic Reference pathDexMap;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_transform_closure2(Object _outerInstance, Object _thisObject, Reference pathDexMap) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            pathDexMap.pathDexMap = this;
        }

        public Object doCall(Object dexFile) {
            v_1 = alloc(Reference);
            new dexFile.<init>(v_1);
            Reference reference = v_1;
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2.$getCallSiteArray();
            v_13 = alloc(Reference);
            new (Dex)ScriptBytecodeAdapter.castToType(siteArr0[0].callConstructor(Dex.class, reference.get()), Dex.class).<init>(v_13);
            Reference dex = v_13;
            return siteArr0[1].call(siteArr0[2].call((Dex)dex.get()), new ImmutableDexTransform$_transform_closure2$_closure10(this, this.getThisObject(), dex, this.pathDexMap, reference));
        }

        @Generated
        public HashMap getPathDexMap() {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.pathDexMap.get(), HashMap.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_transform_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_transform_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_transform_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_transform_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "<$constructor$>";
            stringArr0[1] = "each";
            stringArr0[2] = "classDefs";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_transform_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_transform_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_transform_closure2.$callSiteArray != null ? ImmutableDexTransform$_transform_closure2.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure2.$callSiteArray.get();
            ImmutableDexTransform$_transform_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure2$_closure10
        public final class ImmutableDexTransform$_transform_closure2$_closure10 implements GeneratedClosure {
            private synthetic Reference dex;
            private synthetic Reference pathDexMap;
            private synthetic Reference dexFile;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ImmutableDexTransform$_transform_closure2$_closure10(Object _outerInstance, Object _thisObject, Reference dex, Reference pathDexMap, Reference dexFile) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                dex.dex = this;
                pathDexMap.pathDexMap = this;
                dexFile.dexFile = this;
            }

            public Object doCall(ClassDef classDef) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                String classPath = (String)ShortTypeHandling.castToString(siteArr0[0].call(siteArr0[1].call(this.dex.get()), siteArr0[2].callGetProperty(classDef)));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(this.pathDexMap.get(), classPath))) {
                    throw (Throwable)siteArr0[4].callConstructor(GradleException.class, new GStringImpl(new Object[]{classPath, siteArr0[5].callGetProperty(this.dexFile.get())}, new String[]{"double class: ", " in dex: ", " "}));
                }
                else {
                    return siteArr0[6].call(this.pathDexMap.get(), classPath, siteArr0[7].call(siteArr0[8].callGetProperty(this.dexFile.get()), ".dex"));
                }
            }

            public Object call(ClassDef classDef) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                if (ImmutableDexTransform$_transform_closure2$_closure10.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                    goto 34;
                    return this.doCall(classDef);
                }
                else {
                    return siteArr0[9].callCurrent(this, classDef);
                }
            }

            @Generated
            public Dex getDex() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return (Dex)ScriptBytecodeAdapter.castToType(this.dex.get(), Dex.class);
            }

            @Generated
            public HashMap getPathDexMap() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return (HashMap)ScriptBytecodeAdapter.castToType(this.pathDexMap.get(), HashMap.class);
            }

            @Generated
            public Object getDexFile() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return this.dexFile.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ImmutableDexTransform$_transform_closure2$_closure10.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "get";
                stringArr0[1] = "typeNames";
                stringArr0[2] = "typeIndex";
                stringArr0[3] = "get";
                stringArr0[4] = "<$constructor$>";
                stringArr0[5] = "name";
                stringArr0[6] = "put";
                stringArr0[7] = "minus";
                stringArr0[8] = "name";
                stringArr0[9] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ImmutableDexTransform$_transform_closure2$_closure10.$createCallSiteArray_1(str0);
                return new CallSiteArray(ImmutableDexTransform$_transform_closure2$_closure10.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray != null ? ImmutableDexTransform$_transform_closure2$_closure10.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray.get();
                ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure2$_closure10
        public final class ImmutableDexTransform$_transform_closure2$_closure10 implements GeneratedClosure {
            private synthetic Reference dex;
            private synthetic Reference pathDexMap;
            private synthetic Reference dexFile;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ImmutableDexTransform$_transform_closure2$_closure10(Object _outerInstance, Object _thisObject, Reference dex, Reference pathDexMap, Reference dexFile) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                dex.dex = this;
                pathDexMap.pathDexMap = this;
                dexFile.dexFile = this;
            }

            public Object doCall(ClassDef classDef) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                String classPath = (String)ShortTypeHandling.castToString(siteArr0[0].call(siteArr0[1].call(this.dex.get()), siteArr0[2].callGetProperty(classDef)));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(this.pathDexMap.get(), classPath))) {
                    throw (Throwable)siteArr0[4].callConstructor(GradleException.class, new GStringImpl(new Object[]{classPath, siteArr0[5].callGetProperty(this.dexFile.get())}, new String[]{"double class: ", " in dex: ", " "}));
                }
                else {
                    return siteArr0[6].call(this.pathDexMap.get(), classPath, siteArr0[7].call(siteArr0[8].callGetProperty(this.dexFile.get()), ".dex"));
                }
            }

            public Object call(ClassDef classDef) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                if (ImmutableDexTransform$_transform_closure2$_closure10.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                    goto 34;
                    return this.doCall(classDef);
                }
                else {
                    return siteArr0[9].callCurrent(this, classDef);
                }
            }

            @Generated
            public Dex getDex() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return (Dex)ScriptBytecodeAdapter.castToType(this.dex.get(), Dex.class);
            }

            @Generated
            public HashMap getPathDexMap() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return (HashMap)ScriptBytecodeAdapter.castToType(this.pathDexMap.get(), HashMap.class);
            }

            @Generated
            public Object getDexFile() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return this.dexFile.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ImmutableDexTransform$_transform_closure2$_closure10.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "get";
                stringArr0[1] = "typeNames";
                stringArr0[2] = "typeIndex";
                stringArr0[3] = "get";
                stringArr0[4] = "<$constructor$>";
                stringArr0[5] = "name";
                stringArr0[6] = "put";
                stringArr0[7] = "minus";
                stringArr0[8] = "name";
                stringArr0[9] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ImmutableDexTransform$_transform_closure2$_closure10.$createCallSiteArray_1(str0);
                return new CallSiteArray(ImmutableDexTransform$_transform_closure2$_closure10.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray != null ? ImmutableDexTransform$_transform_closure2$_closure10.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray.get();
                ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure2
    public final class ImmutableDexTransform$_transform_closure2 implements GeneratedClosure {
        private synthetic Reference pathDexMap;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_transform_closure2(Object _outerInstance, Object _thisObject, Reference pathDexMap) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            pathDexMap.pathDexMap = this;
        }

        public Object doCall(Object dexFile) {
            v_1 = alloc(Reference);
            new dexFile.<init>(v_1);
            Reference reference = v_1;
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2.$getCallSiteArray();
            v_13 = alloc(Reference);
            new (Dex)ScriptBytecodeAdapter.castToType(siteArr0[0].callConstructor(Dex.class, reference.get()), Dex.class).<init>(v_13);
            Reference dex = v_13;
            return siteArr0[1].call(siteArr0[2].call((Dex)dex.get()), new ImmutableDexTransform$_transform_closure2$_closure10(this, this.getThisObject(), dex, this.pathDexMap, reference));
        }

        @Generated
        public HashMap getPathDexMap() {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.pathDexMap.get(), HashMap.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_transform_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_transform_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_transform_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_transform_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "<$constructor$>";
            stringArr0[1] = "each";
            stringArr0[2] = "classDefs";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_transform_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_transform_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_transform_closure2.$callSiteArray != null ? ImmutableDexTransform$_transform_closure2.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure2.$callSiteArray.get();
            ImmutableDexTransform$_transform_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure2$_closure10
        public final class ImmutableDexTransform$_transform_closure2$_closure10 implements GeneratedClosure {
            private synthetic Reference dex;
            private synthetic Reference pathDexMap;
            private synthetic Reference dexFile;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ImmutableDexTransform$_transform_closure2$_closure10(Object _outerInstance, Object _thisObject, Reference dex, Reference pathDexMap, Reference dexFile) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                dex.dex = this;
                pathDexMap.pathDexMap = this;
                dexFile.dexFile = this;
            }

            public Object doCall(ClassDef classDef) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                String classPath = (String)ShortTypeHandling.castToString(siteArr0[0].call(siteArr0[1].call(this.dex.get()), siteArr0[2].callGetProperty(classDef)));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(this.pathDexMap.get(), classPath))) {
                    throw (Throwable)siteArr0[4].callConstructor(GradleException.class, new GStringImpl(new Object[]{classPath, siteArr0[5].callGetProperty(this.dexFile.get())}, new String[]{"double class: ", " in dex: ", " "}));
                }
                else {
                    return siteArr0[6].call(this.pathDexMap.get(), classPath, siteArr0[7].call(siteArr0[8].callGetProperty(this.dexFile.get()), ".dex"));
                }
            }

            public Object call(ClassDef classDef) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                if (ImmutableDexTransform$_transform_closure2$_closure10.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                    goto 34;
                    return this.doCall(classDef);
                }
                else {
                    return siteArr0[9].callCurrent(this, classDef);
                }
            }

            @Generated
            public Dex getDex() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return (Dex)ScriptBytecodeAdapter.castToType(this.dex.get(), Dex.class);
            }

            @Generated
            public HashMap getPathDexMap() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return (HashMap)ScriptBytecodeAdapter.castToType(this.pathDexMap.get(), HashMap.class);
            }

            @Generated
            public Object getDexFile() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return this.dexFile.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ImmutableDexTransform$_transform_closure2$_closure10.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "get";
                stringArr0[1] = "typeNames";
                stringArr0[2] = "typeIndex";
                stringArr0[3] = "get";
                stringArr0[4] = "<$constructor$>";
                stringArr0[5] = "name";
                stringArr0[6] = "put";
                stringArr0[7] = "minus";
                stringArr0[8] = "name";
                stringArr0[9] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ImmutableDexTransform$_transform_closure2$_closure10.$createCallSiteArray_1(str0);
                return new CallSiteArray(ImmutableDexTransform$_transform_closure2$_closure10.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray != null ? ImmutableDexTransform$_transform_closure2$_closure10.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray.get();
                ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure2$_closure10
        public final class ImmutableDexTransform$_transform_closure2$_closure10 implements GeneratedClosure {
            private synthetic Reference dex;
            private synthetic Reference pathDexMap;
            private synthetic Reference dexFile;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ImmutableDexTransform$_transform_closure2$_closure10(Object _outerInstance, Object _thisObject, Reference dex, Reference pathDexMap, Reference dexFile) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                dex.dex = this;
                pathDexMap.pathDexMap = this;
                dexFile.dexFile = this;
            }

            public Object doCall(ClassDef classDef) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                String classPath = (String)ShortTypeHandling.castToString(siteArr0[0].call(siteArr0[1].call(this.dex.get()), siteArr0[2].callGetProperty(classDef)));
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(this.pathDexMap.get(), classPath))) {
                    throw (Throwable)siteArr0[4].callConstructor(GradleException.class, new GStringImpl(new Object[]{classPath, siteArr0[5].callGetProperty(this.dexFile.get())}, new String[]{"double class: ", " in dex: ", " "}));
                }
                else {
                    return siteArr0[6].call(this.pathDexMap.get(), classPath, siteArr0[7].call(siteArr0[8].callGetProperty(this.dexFile.get()), ".dex"));
                }
            }

            public Object call(ClassDef classDef) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                if (ImmutableDexTransform$_transform_closure2$_closure10.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                    goto 34;
                    return this.doCall(classDef);
                }
                else {
                    return siteArr0[9].callCurrent(this, classDef);
                }
            }

            @Generated
            public Dex getDex() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return (Dex)ScriptBytecodeAdapter.castToType(this.dex.get(), Dex.class);
            }

            @Generated
            public HashMap getPathDexMap() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return (HashMap)ScriptBytecodeAdapter.castToType(this.pathDexMap.get(), HashMap.class);
            }

            @Generated
            public Object getDexFile() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure2$_closure10.$getCallSiteArray();
                return this.dexFile.get();
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ImmutableDexTransform$_transform_closure2$_closure10.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ImmutableDexTransform$_transform_closure2$_closure10.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "get";
                stringArr0[1] = "typeNames";
                stringArr0[2] = "typeIndex";
                stringArr0[3] = "get";
                stringArr0[4] = "<$constructor$>";
                stringArr0[5] = "name";
                stringArr0[6] = "put";
                stringArr0[7] = "minus";
                stringArr0[8] = "name";
                stringArr0[9] = "doCall";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ImmutableDexTransform$_transform_closure2$_closure10.$createCallSiteArray_1(str0);
                return new CallSiteArray(ImmutableDexTransform$_transform_closure2$_closure10.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray != null ? ImmutableDexTransform$_transform_closure2$_closure10.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray.get();
                ImmutableDexTransform$_transform_closure2$_closure10.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure3
    public final class ImmutableDexTransform$_transform_closure3 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_transform_closure3(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object key, Object value) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure3.$getCallSiteArray();
            return siteArr0[0].call(value);
        }

        public Object call(Object key, Object value) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure3.$getCallSiteArray();
            return siteArr0[1].callCurrent(this, key, value);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_transform_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_transform_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_transform_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_transform_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "close";
            stringArr0[1] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_transform_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_transform_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_transform_closure3.$callSiteArray != null ? ImmutableDexTransform$_transform_closure3.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure3.$callSiteArray.get();
            ImmutableDexTransform$_transform_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure3
    public final class ImmutableDexTransform$_transform_closure3 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_transform_closure3(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object key, Object value) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure3.$getCallSiteArray();
            return siteArr0[0].call(value);
        }

        public Object call(Object key, Object value) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure3.$getCallSiteArray();
            return siteArr0[1].callCurrent(this, key, value);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_transform_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_transform_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_transform_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_transform_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "close";
            stringArr0[1] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_transform_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_transform_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_transform_closure3.$callSiteArray != null ? ImmutableDexTransform$_transform_closure3.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure3.$callSiteArray.get();
            ImmutableDexTransform$_transform_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure1
    public final class ImmutableDexTransform$_transform_closure1 implements GeneratedClosure {
        private synthetic Reference oldDexList;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_transform_closure1(Object _outerInstance, Object _thisObject, Reference oldDexList) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            oldDexList.oldDexList = this;
        }

        public Object doCall(ZipEntry zipEntry, byte[] bytes) {
            v_1 = alloc(Reference);
            new bytes.<init>(v_1);
            Reference reference = v_1;
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(zipEntry), "classes")) && DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(siteArr0[3].callGetProperty(zipEntry), ".dex")) ? 0 : 1 != 0) {
                siteArr0[4].call(siteArr0[5].callGetProperty(siteArr0[6].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[7].callGetProperty(zipEntry)}, new String[]{"find dex: ", " in old apk. "}));
                File classDxFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[8].callConstructor(File.class, siteArr0[9].callGroovyObjectGetProperty(this), siteArr0[10].callGetProperty(zipEntry)), File.class);
                siteArr0[11].call(classDxFile, new ImmutableDexTransform$_transform_closure1$_closure9(this, this.getThisObject(), reference));
                return siteArr0[12].call(this.oldDexList.get(), classDxFile);
            }
            else {
                return null;
            }
        }

        public Object call(ZipEntry zipEntry, byte[] bytes) {
            v_1 = alloc(Reference);
            new bytes.<init>(v_1);
            Reference reference = v_1;
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1.$getCallSiteArray();
            return siteArr0[13].callCurrent(this, zipEntry, (byte[])reference.get());
        }

        @Generated
        public ArrayList getOldDexList() {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1.$getCallSiteArray();
            return (ArrayList)ScriptBytecodeAdapter.castToType(this.oldDexList.get(), ArrayList.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_transform_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_transform_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_transform_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_transform_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "startsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "endsWith";
            stringArr0[3] = "name";
            stringArr0[4] = "info";
            stringArr0[5] = "logger";
            stringArr0[6] = "project";
            stringArr0[7] = "name";
            stringArr0[8] = "<$constructor$>";
            stringArr0[9] = "baseDexDir";
            stringArr0[10] = "name";
            stringArr0[11] = "withDataOutputStream";
            stringArr0[12] = "add";
            stringArr0[13] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_transform_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_transform_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_transform_closure1.$callSiteArray != null ? ImmutableDexTransform$_transform_closure1.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure1.$callSiteArray.get();
            ImmutableDexTransform$_transform_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure1$_closure9
        public final class ImmutableDexTransform$_transform_closure1$_closure9 implements GeneratedClosure {
            private synthetic Reference bytes;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ImmutableDexTransform$_transform_closure1$_closure9(Object _outerInstance, Object _thisObject, Reference bytes) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                bytes.bytes = this;
            }

            public Object doCall(Object output) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                siteArr0[0].call(output, this.bytes.get(), Integer.valueOf(0), siteArr0[1].callGetProperty(this.bytes.get()));
                return siteArr0[2].call(output);
            }

            @Generated
            public byte[] getBytes() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                return (byte[])ScriptBytecodeAdapter.castToType(this.bytes.get(), byte[].class);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ImmutableDexTransform$_transform_closure1$_closure9.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "write";
                stringArr0[1] = "length";
                stringArr0[2] = "close";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ImmutableDexTransform$_transform_closure1$_closure9.$createCallSiteArray_1(str0);
                return new CallSiteArray(ImmutableDexTransform$_transform_closure1$_closure9.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray != null ? ImmutableDexTransform$_transform_closure1$_closure9.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray.get();
                ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure1$_closure9
        public final class ImmutableDexTransform$_transform_closure1$_closure9 implements GeneratedClosure {
            private synthetic Reference bytes;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ImmutableDexTransform$_transform_closure1$_closure9(Object _outerInstance, Object _thisObject, Reference bytes) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                bytes.bytes = this;
            }

            public Object doCall(Object output) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                siteArr0[0].call(output, this.bytes.get(), Integer.valueOf(0), siteArr0[1].callGetProperty(this.bytes.get()));
                return siteArr0[2].call(output);
            }

            @Generated
            public byte[] getBytes() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                return (byte[])ScriptBytecodeAdapter.castToType(this.bytes.get(), byte[].class);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ImmutableDexTransform$_transform_closure1$_closure9.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "write";
                stringArr0[1] = "length";
                stringArr0[2] = "close";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ImmutableDexTransform$_transform_closure1$_closure9.$createCallSiteArray_1(str0);
                return new CallSiteArray(ImmutableDexTransform$_transform_closure1$_closure9.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray != null ? ImmutableDexTransform$_transform_closure1$_closure9.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray.get();
                ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure1
    public final class ImmutableDexTransform$_transform_closure1 implements GeneratedClosure {
        private synthetic Reference oldDexList;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_transform_closure1(Object _outerInstance, Object _thisObject, Reference oldDexList) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            oldDexList.oldDexList = this;
        }

        public Object doCall(ZipEntry zipEntry, byte[] bytes) {
            v_1 = alloc(Reference);
            new bytes.<init>(v_1);
            Reference reference = v_1;
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(zipEntry), "classes")) && DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(siteArr0[3].callGetProperty(zipEntry), ".dex")) ? 0 : 1 != 0) {
                siteArr0[4].call(siteArr0[5].callGetProperty(siteArr0[6].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[7].callGetProperty(zipEntry)}, new String[]{"find dex: ", " in old apk. "}));
                File classDxFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[8].callConstructor(File.class, siteArr0[9].callGroovyObjectGetProperty(this), siteArr0[10].callGetProperty(zipEntry)), File.class);
                siteArr0[11].call(classDxFile, new ImmutableDexTransform$_transform_closure1$_closure9(this, this.getThisObject(), reference));
                return siteArr0[12].call(this.oldDexList.get(), classDxFile);
            }
            else {
                return null;
            }
        }

        public Object call(ZipEntry zipEntry, byte[] bytes) {
            v_1 = alloc(Reference);
            new bytes.<init>(v_1);
            Reference reference = v_1;
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1.$getCallSiteArray();
            return siteArr0[13].callCurrent(this, zipEntry, (byte[])reference.get());
        }

        @Generated
        public ArrayList getOldDexList() {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1.$getCallSiteArray();
            return (ArrayList)ScriptBytecodeAdapter.castToType(this.oldDexList.get(), ArrayList.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_transform_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_transform_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_transform_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_transform_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "startsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "endsWith";
            stringArr0[3] = "name";
            stringArr0[4] = "info";
            stringArr0[5] = "logger";
            stringArr0[6] = "project";
            stringArr0[7] = "name";
            stringArr0[8] = "<$constructor$>";
            stringArr0[9] = "baseDexDir";
            stringArr0[10] = "name";
            stringArr0[11] = "withDataOutputStream";
            stringArr0[12] = "add";
            stringArr0[13] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_transform_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_transform_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_transform_closure1.$callSiteArray != null ? ImmutableDexTransform$_transform_closure1.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure1.$callSiteArray.get();
            ImmutableDexTransform$_transform_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

        // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure1$_closure9
        public final class ImmutableDexTransform$_transform_closure1$_closure9 implements GeneratedClosure {
            private synthetic Reference bytes;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ImmutableDexTransform$_transform_closure1$_closure9(Object _outerInstance, Object _thisObject, Reference bytes) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                bytes.bytes = this;
            }

            public Object doCall(Object output) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                siteArr0[0].call(output, this.bytes.get(), Integer.valueOf(0), siteArr0[1].callGetProperty(this.bytes.get()));
                return siteArr0[2].call(output);
            }

            @Generated
            public byte[] getBytes() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                return (byte[])ScriptBytecodeAdapter.castToType(this.bytes.get(), byte[].class);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ImmutableDexTransform$_transform_closure1$_closure9.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "write";
                stringArr0[1] = "length";
                stringArr0[2] = "close";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ImmutableDexTransform$_transform_closure1$_closure9.$createCallSiteArray_1(str0);
                return new CallSiteArray(ImmutableDexTransform$_transform_closure1$_closure9.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray != null ? ImmutableDexTransform$_transform_closure1$_closure9.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray.get();
                ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
        // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure1$_closure9
        public final class ImmutableDexTransform$_transform_closure1$_closure9 implements GeneratedClosure {
            private synthetic Reference bytes;
            private static synthetic ClassInfo $staticClassInfo;
            public static transient synthetic boolean __$stMC;
            private static synthetic SoftReference $callSiteArray;

            public ImmutableDexTransform$_transform_closure1$_closure9(Object _outerInstance, Object _thisObject, Reference bytes) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                super(_outerInstance, _thisObject);
                bytes.bytes = this;
            }

            public Object doCall(Object output) {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                siteArr0[0].call(output, this.bytes.get(), Integer.valueOf(0), siteArr0[1].callGetProperty(this.bytes.get()));
                return siteArr0[2].call(output);
            }

            @Generated
            public byte[] getBytes() {
                CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure1$_closure9.$getCallSiteArray();
                return (byte[])ScriptBytecodeAdapter.castToType(this.bytes.get(), byte[].class);
            }

            protected /* synthetic */ MetaClass $getStaticMetaClass() {
                if (this.getClass() != ImmutableDexTransform$_transform_closure1$_closure9.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                else {
                    if (ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo == null) {
                        infoVar1 = ClassInfo.getClassInfo(this.getClass());
                        ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                    }
                    return ImmutableDexTransform$_transform_closure1$_closure9.$staticClassInfo.getMetaClass();
                }
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
                stringArr0[0] = "write";
                stringArr0[1] = "length";
                stringArr0[2] = "close";
            }

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String str0 = new String[]{};
                ImmutableDexTransform$_transform_closure1$_closure9.$createCallSiteArray_1(str0);
                return new CallSiteArray(ImmutableDexTransform$_transform_closure1$_closure9.class, str0);
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray array = ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray != null ? ImmutableDexTransform$_transform_closure1$_closure9.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray.get();
                ImmutableDexTransform$_transform_closure1$_closure9.$callSiteArray = new SoftReference(array);
                return var_0_0.array;
            }

        }
    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_processJar_closure5
    public final class ImmutableDexTransform$_processJar_closure5 implements GeneratedClosure {
        private synthetic Reference mainDexSets;
        private synthetic Reference allClassSet;
        private synthetic Reference methodAndFieldsNum;
        private synthetic Reference osMap;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_processJar_closure5(Object _outerInstance, Object _thisObject, Reference mainDexSets, Reference allClassSet, Reference methodAndFieldsNum, Reference osMap) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            mainDexSets.mainDexSets = this;
            allClassSet.allClassSet = this;
            methodAndFieldsNum.methodAndFieldsNum = this;
            osMap.osMap = this;
        }

        public Object doCall(ZipEntry zipEntry, byte[] bytes) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(zipEntry), ".class"))) {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(this.mainDexSets.get(), siteArr0[3].callGetProperty(zipEntry)))) {
                    String classPath = (String)ShortTypeHandling.castToString(siteArr0[4].callCurrent(this, siteArr0[5].callGetProperty(zipEntry)));
                    siteArr0[6].call(this.allClassSet.get(), classPath);
                    siteArr0[7].call(siteArr0[8].callGetProperty(siteArr0[9].callGroovyObjectGetProperty(this)), siteArr0[10].call("process main dex list's class ", classPath));
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[11].callCurrent(this, ArrayUtil.createArray(this.methodAndFieldsNum.get(), this.osMap.get(), "classes", bytes, zipEntry))) ? 0 : 1 != 0) {
                        throw (Throwable)siteArr0[12].callConstructor(GradleException.class, "main dex is exceed the limit! reduce the class number on your main dex keep please.");
                    }
                    else {
                        return null;
                    }
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        }

        public Object call(ZipEntry zipEntry, byte[] bytes) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            return siteArr0[13].callCurrent(this, zipEntry, bytes);
        }

        @Generated
        public HashSet getMainDexSets() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            return (HashSet)ScriptBytecodeAdapter.castToType(this.mainDexSets.get(), HashSet.class);
        }

        @Generated
        public HashSet getAllClassSet() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            return (HashSet)ScriptBytecodeAdapter.castToType(this.allClassSet.get(), HashSet.class);
        }

        @Generated
        public HashMap getMethodAndFieldsNum() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.methodAndFieldsNum.get(), HashMap.class);
        }

        @Generated
        public HashMap getOsMap() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.osMap.get(), HashMap.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_processJar_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_processJar_closure5.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_processJar_closure5.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_processJar_closure5.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "contains";
            stringArr0[3] = "name";
            stringArr0[4] = "rePathToClassPath";
            stringArr0[5] = "name";
            stringArr0[6] = "add";
            stringArr0[7] = "info";
            stringArr0[8] = "logger";
            stringArr0[9] = "project";
            stringArr0[10] = "plus";
            stringArr0[11] = "writeClassToZip";
            stringArr0[12] = "<$constructor$>";
            stringArr0[13] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_processJar_closure5.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_processJar_closure5.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_processJar_closure5.$callSiteArray != null ? ImmutableDexTransform$_processJar_closure5.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_processJar_closure5.$callSiteArray.get();
            ImmutableDexTransform$_processJar_closure5.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_processJar_closure5
    public final class ImmutableDexTransform$_processJar_closure5 implements GeneratedClosure {
        private synthetic Reference mainDexSets;
        private synthetic Reference allClassSet;
        private synthetic Reference methodAndFieldsNum;
        private synthetic Reference osMap;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_processJar_closure5(Object _outerInstance, Object _thisObject, Reference mainDexSets, Reference allClassSet, Reference methodAndFieldsNum, Reference osMap) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            mainDexSets.mainDexSets = this;
            allClassSet.allClassSet = this;
            methodAndFieldsNum.methodAndFieldsNum = this;
            osMap.osMap = this;
        }

        public Object doCall(ZipEntry zipEntry, byte[] bytes) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(zipEntry), ".class"))) {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(this.mainDexSets.get(), siteArr0[3].callGetProperty(zipEntry)))) {
                    String classPath = (String)ShortTypeHandling.castToString(siteArr0[4].callCurrent(this, siteArr0[5].callGetProperty(zipEntry)));
                    siteArr0[6].call(this.allClassSet.get(), classPath);
                    siteArr0[7].call(siteArr0[8].callGetProperty(siteArr0[9].callGroovyObjectGetProperty(this)), siteArr0[10].call("process main dex list's class ", classPath));
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[11].callCurrent(this, ArrayUtil.createArray(this.methodAndFieldsNum.get(), this.osMap.get(), "classes", bytes, zipEntry))) ? 0 : 1 != 0) {
                        throw (Throwable)siteArr0[12].callConstructor(GradleException.class, "main dex is exceed the limit! reduce the class number on your main dex keep please.");
                    }
                    else {
                        return null;
                    }
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        }

        public Object call(ZipEntry zipEntry, byte[] bytes) {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            return siteArr0[13].callCurrent(this, zipEntry, bytes);
        }

        @Generated
        public HashSet getMainDexSets() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            return (HashSet)ScriptBytecodeAdapter.castToType(this.mainDexSets.get(), HashSet.class);
        }

        @Generated
        public HashSet getAllClassSet() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            return (HashSet)ScriptBytecodeAdapter.castToType(this.allClassSet.get(), HashSet.class);
        }

        @Generated
        public HashMap getMethodAndFieldsNum() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.methodAndFieldsNum.get(), HashMap.class);
        }

        @Generated
        public HashMap getOsMap() {
            CallSite[] siteArr0 = ImmutableDexTransform$_processJar_closure5.$getCallSiteArray();
            return (HashMap)ScriptBytecodeAdapter.castToType(this.osMap.get(), HashMap.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_processJar_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_processJar_closure5.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_processJar_closure5.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_processJar_closure5.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
            stringArr0[2] = "contains";
            stringArr0[3] = "name";
            stringArr0[4] = "rePathToClassPath";
            stringArr0[5] = "name";
            stringArr0[6] = "add";
            stringArr0[7] = "info";
            stringArr0[8] = "logger";
            stringArr0[9] = "project";
            stringArr0[10] = "plus";
            stringArr0[11] = "writeClassToZip";
            stringArr0[12] = "<$constructor$>";
            stringArr0[13] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_processJar_closure5.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_processJar_closure5.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_processJar_closure5.$callSiteArray != null ? ImmutableDexTransform$_processJar_closure5.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_processJar_closure5.$callSiteArray.get();
            ImmutableDexTransform$_processJar_closure5.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure4
    public final class ImmutableDexTransform$_transform_closure4 implements GeneratedClosure {
        private synthetic Reference dxOutDir;
        private synthetic Reference dexPathList;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_transform_closure4(Object _outerInstance, Object _thisObject, Reference dxOutDir, Reference dexPathList) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure4.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            dxOutDir.dxOutDir = this;
            dexPathList.dexPathList = this;
        }

        public Object doCall(Object classZip) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure4.$getCallSiteArray();
            String classIndexName = (String)ShortTypeHandling.castToString(siteArr0[0].call(siteArr0[1].callGetProperty(classZip), ".jar"));
            String dexPath = (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[2].callGetProperty(this.dxOutDir.get()), classIndexName}, new String[]{"", "/", ".dex"}));
            siteArr0[3].call(this.dexPathList.get(), dexPath);
            return siteArr0[4].callCurrent(this, dexPath, classZip, siteArr0[5].call(siteArr0[6].callGetProperty(siteArr0[7].callGroovyObjectGetProperty(this))));
        }

        @Generated
        public Object getDxOutDir() {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure4.$getCallSiteArray();
            return this.dxOutDir.get();
        }

        @Generated
        public ArrayList getDexPathList() {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure4.$getCallSiteArray();
            return (ArrayList)ScriptBytecodeAdapter.castToType(this.dexPathList.get(), ArrayList.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_transform_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_transform_closure4.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_transform_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_transform_closure4.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "minus";
            stringArr0[1] = "name";
            stringArr0[2] = "absolutePath";
            stringArr0[3] = "add";
            stringArr0[4] = "doDex";
            stringArr0[5] = "getDexOptions";
            stringArr0[6] = "android";
            stringArr0[7] = "project";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_transform_closure4.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_transform_closure4.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_transform_closure4.$callSiteArray != null ? ImmutableDexTransform$_transform_closure4.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure4.$callSiteArray.get();
            ImmutableDexTransform$_transform_closure4.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_transform_closure4
    public final class ImmutableDexTransform$_transform_closure4 implements GeneratedClosure {
        private synthetic Reference dxOutDir;
        private synthetic Reference dexPathList;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_transform_closure4(Object _outerInstance, Object _thisObject, Reference dxOutDir, Reference dexPathList) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure4.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            dxOutDir.dxOutDir = this;
            dexPathList.dexPathList = this;
        }

        public Object doCall(Object classZip) {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure4.$getCallSiteArray();
            String classIndexName = (String)ShortTypeHandling.castToString(siteArr0[0].call(siteArr0[1].callGetProperty(classZip), ".jar"));
            String dexPath = (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[2].callGetProperty(this.dxOutDir.get()), classIndexName}, new String[]{"", "/", ".dex"}));
            siteArr0[3].call(this.dexPathList.get(), dexPath);
            return siteArr0[4].callCurrent(this, dexPath, classZip, siteArr0[5].call(siteArr0[6].callGetProperty(siteArr0[7].callGroovyObjectGetProperty(this))));
        }

        @Generated
        public Object getDxOutDir() {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure4.$getCallSiteArray();
            return this.dxOutDir.get();
        }

        @Generated
        public ArrayList getDexPathList() {
            CallSite[] siteArr0 = ImmutableDexTransform$_transform_closure4.$getCallSiteArray();
            return (ArrayList)ScriptBytecodeAdapter.castToType(this.dexPathList.get(), ArrayList.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_transform_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_transform_closure4.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_transform_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_transform_closure4.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "minus";
            stringArr0[1] = "name";
            stringArr0[2] = "absolutePath";
            stringArr0[3] = "add";
            stringArr0[4] = "doDex";
            stringArr0[5] = "getDexOptions";
            stringArr0[6] = "android";
            stringArr0[7] = "project";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_transform_closure4.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_transform_closure4.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_transform_closure4.$callSiteArray != null ? ImmutableDexTransform$_transform_closure4.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_transform_closure4.$callSiteArray.get();
            ImmutableDexTransform$_transform_closure4.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_checkClassConsistence_closure8
    public final class ImmutableDexTransform$_checkClassConsistence_closure8 implements GeneratedClosure {
        private synthetic Reference classSize;
        private synthetic Reference dexClassSet;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_checkClassConsistence_closure8(Object _outerInstance, Object _thisObject, Reference classSize, Reference dexClassSet) {
            CallSite[] siteArr0 = ImmutableDexTransform$_checkClassConsistence_closure8.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            classSize.classSize = this;
            dexClassSet.dexClassSet = this;
        }

        public Object doCall(Object path) {
            CallSite[] siteArr0 = ImmutableDexTransform$_checkClassConsistence_closure8.$getCallSiteArray();
            File dexFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[0].callConstructor(File.class, path), File.class);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(dexFile))) {
                Dex dex = (Dex)ScriptBytecodeAdapter.castToType(siteArr0[2].callConstructor(Dex.class, dexFile), Dex.class);
                v_42 = siteArr0[3].call(this.classSize.get(), siteArr0[4].call(siteArr0[5].call(dex)));
                (Integer)ScriptBytecodeAdapter.castToType(siteArr0[3].call(this.classSize.get(), siteArr0[4].call(siteArr0[5].call(dex))), Integer.class).set(this.classSize);
                Object item = null;
                Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[6].call(siteArr0[7].call(dex)), Iterator.class);
                while (iterator.hasNext()) {
                    ClassDef def = (ClassDef)ScriptBytecodeAdapter.castToType(iterator.next(), ClassDef.class);
                    int index = DefaultTypeTransformation.intUnbox(siteArr0[8].callGetProperty(def));
                    siteArr0[9].call(this.dexClassSet.get(), siteArr0[10].call(siteArr0[11].call(dex), Integer.valueOf(index)));
                }
                return null;
            }
            else {
                throw (Throwable)siteArr0[12].callConstructor(GradleException.class, new GStringImpl(new Object[]{dexFile}, new String[]{"dex: ", " is illegal!"}));
            }
        }

        @Generated
        public Integer getClassSize() {
            CallSite[] siteArr0 = ImmutableDexTransform$_checkClassConsistence_closure8.$getCallSiteArray();
            return (Integer)ScriptBytecodeAdapter.castToType(this.classSize.get(), Integer.class);
        }

        @Generated
        public HashSet getDexClassSet() {
            CallSite[] siteArr0 = ImmutableDexTransform$_checkClassConsistence_closure8.$getCallSiteArray();
            return (HashSet)ScriptBytecodeAdapter.castToType(this.dexClassSet.get(), HashSet.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_checkClassConsistence_closure8.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_checkClassConsistence_closure8.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_checkClassConsistence_closure8.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_checkClassConsistence_closure8.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "<$constructor$>";
            stringArr0[1] = "isFile";
            stringArr0[2] = "<$constructor$>";
            stringArr0[3] = "plus";
            stringArr0[4] = "size";
            stringArr0[5] = "classDefs";
            stringArr0[6] = "iterator";
            stringArr0[7] = "classDefs";
            stringArr0[8] = "typeIndex";
            stringArr0[9] = "add";
            stringArr0[10] = "get";
            stringArr0[11] = "typeNames";
            stringArr0[12] = "<$constructor$>";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_checkClassConsistence_closure8.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_checkClassConsistence_closure8.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_checkClassConsistence_closure8.$callSiteArray != null ? ImmutableDexTransform$_checkClassConsistence_closure8.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_checkClassConsistence_closure8.$callSiteArray.get();
            ImmutableDexTransform$_checkClassConsistence_closure8.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/transform/ImmutableDexTransform$_checkClassConsistence_closure8
    public final class ImmutableDexTransform$_checkClassConsistence_closure8 implements GeneratedClosure {
        private synthetic Reference classSize;
        private synthetic Reference dexClassSet;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public ImmutableDexTransform$_checkClassConsistence_closure8(Object _outerInstance, Object _thisObject, Reference classSize, Reference dexClassSet) {
            CallSite[] siteArr0 = ImmutableDexTransform$_checkClassConsistence_closure8.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            classSize.classSize = this;
            dexClassSet.dexClassSet = this;
        }

        public Object doCall(Object path) {
            CallSite[] siteArr0 = ImmutableDexTransform$_checkClassConsistence_closure8.$getCallSiteArray();
            File dexFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[0].callConstructor(File.class, path), File.class);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(dexFile))) {
                Dex dex = (Dex)ScriptBytecodeAdapter.castToType(siteArr0[2].callConstructor(Dex.class, dexFile), Dex.class);
                v_42 = siteArr0[3].call(this.classSize.get(), siteArr0[4].call(siteArr0[5].call(dex)));
                (Integer)ScriptBytecodeAdapter.castToType(siteArr0[3].call(this.classSize.get(), siteArr0[4].call(siteArr0[5].call(dex))), Integer.class).set(this.classSize);
                Object item = null;
                Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[6].call(siteArr0[7].call(dex)), Iterator.class);
                while (iterator.hasNext()) {
                    ClassDef def = (ClassDef)ScriptBytecodeAdapter.castToType(iterator.next(), ClassDef.class);
                    int index = DefaultTypeTransformation.intUnbox(siteArr0[8].callGetProperty(def));
                    siteArr0[9].call(this.dexClassSet.get(), siteArr0[10].call(siteArr0[11].call(dex), Integer.valueOf(index)));
                }
                return null;
            }
            else {
                throw (Throwable)siteArr0[12].callConstructor(GradleException.class, new GStringImpl(new Object[]{dexFile}, new String[]{"dex: ", " is illegal!"}));
            }
        }

        @Generated
        public Integer getClassSize() {
            CallSite[] siteArr0 = ImmutableDexTransform$_checkClassConsistence_closure8.$getCallSiteArray();
            return (Integer)ScriptBytecodeAdapter.castToType(this.classSize.get(), Integer.class);
        }

        @Generated
        public HashSet getDexClassSet() {
            CallSite[] siteArr0 = ImmutableDexTransform$_checkClassConsistence_closure8.$getCallSiteArray();
            return (HashSet)ScriptBytecodeAdapter.castToType(this.dexClassSet.get(), HashSet.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != ImmutableDexTransform$_checkClassConsistence_closure8.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (ImmutableDexTransform$_checkClassConsistence_closure8.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    ImmutableDexTransform$_checkClassConsistence_closure8.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return ImmutableDexTransform$_checkClassConsistence_closure8.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "<$constructor$>";
            stringArr0[1] = "isFile";
            stringArr0[2] = "<$constructor$>";
            stringArr0[3] = "plus";
            stringArr0[4] = "size";
            stringArr0[5] = "classDefs";
            stringArr0[6] = "iterator";
            stringArr0[7] = "classDefs";
            stringArr0[8] = "typeIndex";
            stringArr0[9] = "add";
            stringArr0[10] = "get";
            stringArr0[11] = "typeNames";
            stringArr0[12] = "<$constructor$>";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            ImmutableDexTransform$_checkClassConsistence_closure8.$createCallSiteArray_1(str0);
            return new CallSiteArray(ImmutableDexTransform$_checkClassConsistence_closure8.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = ImmutableDexTransform$_checkClassConsistence_closure8.$callSiteArray != null ? ImmutableDexTransform$_checkClassConsistence_closure8.$createCallSiteArray() : (CallSiteArray)ImmutableDexTransform$_checkClassConsistence_closure8.$callSiteArray.get();
            ImmutableDexTransform$_checkClassConsistence_closure8.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
