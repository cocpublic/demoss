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
import java.util.Map;
import java.io.File;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.util.Node;
import groovy.transform.Generated;
import groovy.transform.Internal;

// class: com/tencent/tinker/build/gradle/task/TinkerManifestTask
public class TinkerManifestTask implements GroovyObject {
    final private static String TINKER_ID;
    final private static String TINKER_ID_PREFIX;
    @Internal
    final private Map<String, File> outputNameToManifestMap;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerManifestTask() {
        CallSite[] siteArr0 = TinkerManifestTask.$getCallSiteArray();
        super();
        Object object = siteArr0[0].callConstructor(HashMap.class);
        (Map)ScriptBytecodeAdapter.castToType(object, Map.class).outputNameToManifestMap = this;
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        String str0 = "tinker";
        ScriptBytecodeAdapter.setGroovyObjectProperty(str0, TinkerManifestTask.class, this, (String)"group");
    }

    @TaskAction
    public Object updateManifest() {
        CallSite[] siteArr0 = TinkerManifestTask.$getCallSiteArray();
        v_24 = alloc(Reference);
        new (String)ShortTypeHandling.castToString(siteArr0[1].callGetProperty(siteArr0[2].callGetProperty(siteArr0[3].callGetProperty(siteArr0[4].callGetProperty(siteArr0[5].callGroovyObjectGetProperty(this)))))).<init>(v_24);
        Reference tinkerValue = v_24;
        v_50 = alloc(Reference);
        new (Boolean)ScriptBytecodeAdapter.castToType(siteArr0[6].callGetProperty(siteArr0[7].callGetProperty(siteArr0[8].callGetProperty(siteArr0[9].callGetProperty(siteArr0[10].callGroovyObjectGetProperty(this))))), Boolean.class).<init>(v_50);
        Reference appendOutputNameToTinkerId = v_50;
        if (! ScriptBytecodeAdapter.compareEqual((String)tinkerValue.get(), null) || DefaultTypeTransformation.booleanUnbox(siteArr0[11].call((String)tinkerValue.get())) ? 0 : 1 != 0) {
            throw (Throwable)siteArr0[12].callConstructor(GradleException.class, "tinkerId is not set!!!");
        }
        else {
            Object object = siteArr0[13].call(TinkerManifestTask.TINKER_ID_PREFIX, (String)tinkerValue.get());
            (String)ShortTypeHandling.castToString(object).set((Reference)tinkerValue);
            v_103 = alloc(Reference);
            new siteArr0[14].callConstructor(File.class, siteArr0[15].callGetProperty(siteArr0[16].callGroovyObjectGetProperty(this)), "intermediates").<init>(v_103);
            Reference agpIntermediatesDir = v_103;
            return siteArr0[17].call(this.outputNameToManifestMap, new TinkerManifestTask$_updateManifest_closure1(this, this, tinkerValue, appendOutputNameToTinkerId, agpIntermediatesDir));
        }
    }

    public static void writeManifestMeta(String manifestPath, String name, String value) {
        v_1 = alloc(Reference);
        new name.<init>(v_1);
        Reference reference = v_1;
        CallSite[] siteArr0 = TinkerManifestTask.$getCallSiteArray();
        v_10 = alloc(Reference);
        new siteArr0[18].callConstructor(Namespace.class, "http://schemas.android.com/apk/res/android", "android").<init>(v_10);
        Reference ns = v_10;
        Object isr = null;
        Object pw = null;
        try {
            Object object = siteArr0[19].callConstructor(InputStreamReader.class, siteArr0[20].callConstructor(FileInputStream.class, manifestPath), "utf-8");
            Node xml = (Node)ScriptBytecodeAdapter.asType(siteArr0[21].call(siteArr0[22].callConstructor(XmlParser.class), object), Node.class);
            Node application = (Node)ScriptBytecodeAdapter.asType(siteArr0[23].call(siteArr0[24].callGetProperty(xml), Integer.valueOf(0)), Node.class);
            if (DefaultTypeTransformation.booleanUnbox(application)) {
                Object metaDataTags = siteArr0[25].call(application, "meta-data");
                Object tinkerId = siteArr0[26].call(siteArr0[27].call(metaDataTags, new TinkerManifestTask$_writeManifestMeta_closure2(TinkerManifestTask.class, TinkerManifestTask.class, ns, reference)), new TinkerManifestTask$_writeManifestMeta_closure3(TinkerManifestTask.class, TinkerManifestTask.class));
                siteArr0[28].call(application, "meta-data", ScriptBytecodeAdapter.createMap(new Object[]{siteArr0[29].call(siteArr0[30].callGetProperty(ns.get()), ":name"), (String)reference.get(), siteArr0[31].call(siteArr0[32].callGetProperty(ns.get()), ":value"), value}));
                Object objectVar1 = siteArr0[33].callConstructor(PrintWriter.class, manifestPath, "utf-8");
                Object printer = siteArr0[34].callConstructor(XmlNodePrinter.class, objectVar1);
                int i0 = true;
                ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i0), null, printer, (String)"preserveWhitespace");
                siteArr0[35].call(printer, xml);
            }
            goto 426;
            siteArr0[36].call(IOHelper.class, objectVar1);
            siteArr0[37].call(IOHelper.class, object);
            return;
        }
        finally {
            Throwable throwable = v_61;
            siteArr0[38].call(IOHelper.class, objectVar1);
            siteArr0[39].call(IOHelper.class, object);
            throw throwable;
        }
    }

    public void addApplicationToLoaderPattern(String manifestPath) {
        CallSite[] siteArr0 = TinkerManifestTask.$getCallSiteArray();
        Iterable loader = (Iterable)ScriptBytecodeAdapter.castToType(siteArr0[40].callGetProperty(siteArr0[41].callGetProperty(siteArr0[42].callGetProperty(siteArr0[43].callGetProperty(siteArr0[44].callGroovyObjectGetProperty(this))))), Iterable.class);
        Object applicationName = null;
        String str0;
        if (TinkerManifestTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 116;
            String str1 = TinkerManifestTask.readManifestApplicationName(manifestPath);
        }
        else {
            Object object = siteArr0[45].callStatic(TinkerManifestTask.class, manifestPath);
            str0 = (String)ShortTypeHandling.castToString(object);
        }
        if (BytecodeInterface8.isOrigZ() && TinkerManifestTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 275;
            if (ScriptBytecodeAdapter.compareNotEqual(str1, null)) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[51].call(loader, str1)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[52].call(loader, str1);
                siteArr0[53].call(siteArr0[54].callGetProperty(siteArr0[55].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{str1}, new String[]{"tinker add ", " to dex loader pattern"}));
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareNotEqual(str1, null)) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[46].call(loader, str1)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[47].call(loader, str1);
                siteArr0[48].call(siteArr0[49].callGetProperty(siteArr0[50].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{str1}, new String[]{"tinker add ", " to dex loader pattern"}));
            }
        }
        String loaderClass = "com.tencent.tinker.loader.*";
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[56].call(loader, loaderClass)) ? 0 : 1 != 0) {
            siteArr0[57].call(loader, loaderClass);
            siteArr0[58].call(siteArr0[59].callGetProperty(siteArr0[60].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{loaderClass}, new String[]{"tinker add ", " to dex loader pattern"}));
        }
    }

    public static String readManifestApplicationName(String manifestPath) {
        CallSite[] siteArr0 = TinkerManifestTask.$getCallSiteArray();
        Object isr = null;
        try {
            Object object = siteArr0[61].callConstructor(InputStreamReader.class, siteArr0[62].callConstructor(FileInputStream.class, manifestPath), "utf-8");
            Object xml = siteArr0[63].call(siteArr0[64].callConstructor(XmlParser.class), object);
            Object ns = siteArr0[65].callConstructor(Namespace.class, "http://schemas.android.com/apk/res/android", "android");
            Object application = siteArr0[66].call(siteArr0[67].callGetProperty(xml), Integer.valueOf(0));
            if (DefaultTypeTransformation.booleanUnbox(application)) {
                String str0 = (String)ShortTypeHandling.castToString(siteArr0[68].call(siteArr0[69].call(application), siteArr0[70].callGetProperty(ns)));
                siteArr0[71].call(IOHelper.class, object);
                return str0;
            }
            else {
                String str1 = (String)ShortTypeHandling.castToString(null);
                siteArr0[72].call(IOHelper.class, object);
                return str1;
            }
        }
        finally {
            Throwable throwable = v_50;
            siteArr0[74].call(IOHelper.class, object);
            throw throwable;
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerManifestTask.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerManifestTask.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerManifestTask.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerManifestTask.$staticClassInfo.getMetaClass();
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
    public static String getTINKER_ID() {
        return TinkerManifestTask.TINKER_ID;
    }

    @Generated
    public static String getTINKER_ID_PREFIX() {
        return TinkerManifestTask.TINKER_ID_PREFIX;
    }

    @Generated
    final public Map<String, File> getOutputNameToManifestMap() {
        return this.outputNameToManifestMap;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "<$constructor$>";
        stringArr0[1] = "tinkerId";
        stringArr0[2] = "buildConfig";
        stringArr0[3] = "tinkerPatch";
        stringArr0[4] = "extensions";
        stringArr0[5] = "project";
        stringArr0[6] = "appendOutputNameToTinkerId";
        stringArr0[7] = "buildConfig";
        stringArr0[8] = "tinkerPatch";
        stringArr0[9] = "extensions";
        stringArr0[10] = "project";
        stringArr0[11] = "isEmpty";
        stringArr0[12] = "<$constructor$>";
        stringArr0[13] = "plus";
        stringArr0[14] = "<$constructor$>";
        stringArr0[15] = "buildDir";
        stringArr0[16] = "project";
        stringArr0[17] = "each";
        stringArr0[18] = "<$constructor$>";
        stringArr0[19] = "<$constructor$>";
        stringArr0[20] = "<$constructor$>";
        stringArr0[21] = "parse";
        stringArr0[22] = "<$constructor$>";
        stringArr0[23] = "getAt";
        stringArr0[24] = "application";
        stringArr0[25] = "getAt";
        stringArr0[26] = "each";
        stringArr0[27] = "findAll";
        stringArr0[28] = "appendNode";
        stringArr0[29] = "plus";
        stringArr0[30] = "prefix";
        stringArr0[31] = "plus";
        stringArr0[32] = "prefix";
        stringArr0[33] = "<$constructor$>";
        stringArr0[34] = "<$constructor$>";
        stringArr0[35] = "print";
        stringArr0[36] = "closeQuietly";
        stringArr0[37] = "closeQuietly";
        stringArr0[38] = "closeQuietly";
        stringArr0[39] = "closeQuietly";
        stringArr0[40] = "loader";
        stringArr0[41] = "dex";
        stringArr0[42] = "tinkerPatch";
        stringArr0[43] = "extensions";
        stringArr0[44] = "project";
        stringArr0[45] = "readManifestApplicationName";
        stringArr0[46] = "contains";
        stringArr0[47] = "add";
        stringArr0[48] = "error";
        stringArr0[49] = "logger";
        stringArr0[50] = "project";
        stringArr0[51] = "contains";
        stringArr0[52] = "add";
        stringArr0[53] = "error";
        stringArr0[54] = "logger";
        stringArr0[55] = "project";
        stringArr0[56] = "contains";
        stringArr0[57] = "add";
        stringArr0[58] = "error";
        stringArr0[59] = "logger";
        stringArr0[60] = "project";
        stringArr0[61] = "<$constructor$>";
        stringArr0[62] = "<$constructor$>";
        stringArr0[63] = "parse";
        stringArr0[64] = "<$constructor$>";
        stringArr0[65] = "<$constructor$>";
        stringArr0[66] = "getAt";
        stringArr0[67] = "application";
        stringArr0[68] = "getAt";
        stringArr0[69] = "attributes";
        stringArr0[70] = "name";
        stringArr0[71] = "closeQuietly";
        stringArr0[72] = "closeQuietly";
        stringArr0[73] = "closeQuietly";
        stringArr0[74] = "closeQuietly";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerManifestTask.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerManifestTask.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerManifestTask.$callSiteArray != null ? TinkerManifestTask.$createCallSiteArray() : (CallSiteArray)TinkerManifestTask.$callSiteArray.get();
        TinkerManifestTask.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/tinker/build/gradle/task/TinkerManifestTask$_updateManifest_closure1
    public final class TinkerManifestTask$_updateManifest_closure1 implements GeneratedClosure {
        private synthetic Reference tinkerValue;
        private synthetic Reference appendOutputNameToTinkerId;
        private synthetic Reference agpIntermediatesDir;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestTask$_updateManifest_closure1(Object _outerInstance, Object _thisObject, Reference tinkerValue, Reference appendOutputNameToTinkerId, Reference agpIntermediatesDir) {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            tinkerValue.tinkerValue = this;
            appendOutputNameToTinkerId.appendOutputNameToTinkerId = this;
            agpIntermediatesDir.agpIntermediatesDir = this;
        }

        public Object doCall(String outputName, File manifest) {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            Object manifestPath = siteArr0[0].call(manifest);
            Object finalTinkerValue = this.tinkerValue.get();
            if (DefaultTypeTransformation.booleanUnbox(this.appendOutputNameToTinkerId.get())) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(outputName)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                v_38 = siteArr0[2].call(finalTinkerValue, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                finalTinkerValue = siteArr0[2].call(finalTinkerValue, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
            }
            siteArr0[3].call(siteArr0[4].callGetProperty(siteArr0[5].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{finalTinkerValue, manifestPath}, new String[]{"tinker add ", " to your AndroidManifest.xml ", ""}));
            siteArr0[6].callStatic(TinkerManifestTask.class, manifestPath, siteArr0[7].callGetProperty(TinkerManifestTask.class), finalTinkerValue);
            siteArr0[8].callCurrent(this, manifestPath);
            File manifestFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[9].callConstructor(File.class, manifestPath), File.class);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[10].call(manifestFile))) {
                Object manifestRelPath = siteArr0[11].call(siteArr0[12].call(siteArr0[13].call(this.agpIntermediatesDir.get()), siteArr0[14].call(manifestFile)));
                Object manifestDestPath = siteArr0[15].callConstructor(File.class, siteArr0[16].call(siteArr0[17].callGroovyObjectGetProperty(this), siteArr0[18].call(TinkerBuildPath.class, siteArr0[19].callGroovyObjectGetProperty(this))), manifestRelPath);
                siteArr0[20].call(FileOperation.class, manifestFile, manifestDestPath);
                return siteArr0[21].call(siteArr0[22].callGetProperty(siteArr0[23].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{manifestDestPath}, new String[]{"tinker gen AndroidManifest.xml in ", ""}));
            }
            else {
                return null;
            }
        }

        public Object call(String outputName, File manifest) {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            return siteArr0[24].callCurrent(this, outputName, manifest);
        }

        @Generated
        public String getTinkerValue() {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.tinkerValue.get());
        }

        @Generated
        public Boolean getAppendOutputNameToTinkerId() {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            return (Boolean)ScriptBytecodeAdapter.castToType(this.appendOutputNameToTinkerId.get(), Boolean.class);
        }

        @Generated
        public Object getAgpIntermediatesDir() {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            return this.agpIntermediatesDir.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestTask$_updateManifest_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestTask$_updateManifest_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestTask$_updateManifest_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestTask$_updateManifest_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "getAbsolutePath";
            stringArr0[1] = "isEmpty";
            stringArr0[2] = "plus";
            stringArr0[3] = "error";
            stringArr0[4] = "logger";
            stringArr0[5] = "project";
            stringArr0[6] = "writeManifestMeta";
            stringArr0[7] = "TINKER_ID";
            stringArr0[8] = "addApplicationToLoaderPattern";
            stringArr0[9] = "<$constructor$>";
            stringArr0[10] = "exists";
            stringArr0[11] = "toString";
            stringArr0[12] = "relativize";
            stringArr0[13] = "toPath";
            stringArr0[14] = "toPath";
            stringArr0[15] = "<$constructor$>";
            stringArr0[16] = "file";
            stringArr0[17] = "project";
            stringArr0[18] = "getTinkerIntermediates";
            stringArr0[19] = "project";
            stringArr0[20] = "copyFileUsingStream";
            stringArr0[21] = "error";
            stringArr0[22] = "logger";
            stringArr0[23] = "project";
            stringArr0[24] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerManifestTask$_updateManifest_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestTask$_updateManifest_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestTask$_updateManifest_closure1.$callSiteArray != null ? TinkerManifestTask$_updateManifest_closure1.$createCallSiteArray() : (CallSiteArray)TinkerManifestTask$_updateManifest_closure1.$callSiteArray.get();
            TinkerManifestTask$_updateManifest_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerManifestTask$_updateManifest_closure1
    public final class TinkerManifestTask$_updateManifest_closure1 implements GeneratedClosure {
        private synthetic Reference tinkerValue;
        private synthetic Reference appendOutputNameToTinkerId;
        private synthetic Reference agpIntermediatesDir;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestTask$_updateManifest_closure1(Object _outerInstance, Object _thisObject, Reference tinkerValue, Reference appendOutputNameToTinkerId, Reference agpIntermediatesDir) {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            tinkerValue.tinkerValue = this;
            appendOutputNameToTinkerId.appendOutputNameToTinkerId = this;
            agpIntermediatesDir.agpIntermediatesDir = this;
        }

        public Object doCall(String outputName, File manifest) {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            Object manifestPath = siteArr0[0].call(manifest);
            Object finalTinkerValue = this.tinkerValue.get();
            if (DefaultTypeTransformation.booleanUnbox(this.appendOutputNameToTinkerId.get())) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(outputName)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                v_38 = siteArr0[2].call(finalTinkerValue, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                finalTinkerValue = siteArr0[2].call(finalTinkerValue, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
            }
            siteArr0[3].call(siteArr0[4].callGetProperty(siteArr0[5].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{finalTinkerValue, manifestPath}, new String[]{"tinker add ", " to your AndroidManifest.xml ", ""}));
            siteArr0[6].callStatic(TinkerManifestTask.class, manifestPath, siteArr0[7].callGetProperty(TinkerManifestTask.class), finalTinkerValue);
            siteArr0[8].callCurrent(this, manifestPath);
            File manifestFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[9].callConstructor(File.class, manifestPath), File.class);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[10].call(manifestFile))) {
                Object manifestRelPath = siteArr0[11].call(siteArr0[12].call(siteArr0[13].call(this.agpIntermediatesDir.get()), siteArr0[14].call(manifestFile)));
                Object manifestDestPath = siteArr0[15].callConstructor(File.class, siteArr0[16].call(siteArr0[17].callGroovyObjectGetProperty(this), siteArr0[18].call(TinkerBuildPath.class, siteArr0[19].callGroovyObjectGetProperty(this))), manifestRelPath);
                siteArr0[20].call(FileOperation.class, manifestFile, manifestDestPath);
                return siteArr0[21].call(siteArr0[22].callGetProperty(siteArr0[23].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{manifestDestPath}, new String[]{"tinker gen AndroidManifest.xml in ", ""}));
            }
            else {
                return null;
            }
        }

        public Object call(String outputName, File manifest) {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            return siteArr0[24].callCurrent(this, outputName, manifest);
        }

        @Generated
        public String getTinkerValue() {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.tinkerValue.get());
        }

        @Generated
        public Boolean getAppendOutputNameToTinkerId() {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            return (Boolean)ScriptBytecodeAdapter.castToType(this.appendOutputNameToTinkerId.get(), Boolean.class);
        }

        @Generated
        public Object getAgpIntermediatesDir() {
            CallSite[] siteArr0 = TinkerManifestTask$_updateManifest_closure1.$getCallSiteArray();
            return this.agpIntermediatesDir.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestTask$_updateManifest_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestTask$_updateManifest_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestTask$_updateManifest_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestTask$_updateManifest_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "getAbsolutePath";
            stringArr0[1] = "isEmpty";
            stringArr0[2] = "plus";
            stringArr0[3] = "error";
            stringArr0[4] = "logger";
            stringArr0[5] = "project";
            stringArr0[6] = "writeManifestMeta";
            stringArr0[7] = "TINKER_ID";
            stringArr0[8] = "addApplicationToLoaderPattern";
            stringArr0[9] = "<$constructor$>";
            stringArr0[10] = "exists";
            stringArr0[11] = "toString";
            stringArr0[12] = "relativize";
            stringArr0[13] = "toPath";
            stringArr0[14] = "toPath";
            stringArr0[15] = "<$constructor$>";
            stringArr0[16] = "file";
            stringArr0[17] = "project";
            stringArr0[18] = "getTinkerIntermediates";
            stringArr0[19] = "project";
            stringArr0[20] = "copyFileUsingStream";
            stringArr0[21] = "error";
            stringArr0[22] = "logger";
            stringArr0[23] = "project";
            stringArr0[24] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerManifestTask$_updateManifest_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestTask$_updateManifest_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestTask$_updateManifest_closure1.$callSiteArray != null ? TinkerManifestTask$_updateManifest_closure1.$createCallSiteArray() : (CallSiteArray)TinkerManifestTask$_updateManifest_closure1.$callSiteArray.get();
            TinkerManifestTask$_updateManifest_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerManifestTask$_writeManifestMeta_closure3
    public final class TinkerManifestTask$_writeManifestMeta_closure3 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestTask$_writeManifestMeta_closure3(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure3.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].call(it), it);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure3.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestTask$_writeManifestMeta_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestTask$_writeManifestMeta_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestTask$_writeManifestMeta_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestTask$_writeManifestMeta_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "remove";
            stringArr0[1] = "parent";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerManifestTask$_writeManifestMeta_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestTask$_writeManifestMeta_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestTask$_writeManifestMeta_closure3.$callSiteArray != null ? TinkerManifestTask$_writeManifestMeta_closure3.$createCallSiteArray() : (CallSiteArray)TinkerManifestTask$_writeManifestMeta_closure3.$callSiteArray.get();
            TinkerManifestTask$_writeManifestMeta_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerManifestTask$_writeManifestMeta_closure3
    public final class TinkerManifestTask$_writeManifestMeta_closure3 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestTask$_writeManifestMeta_closure3(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure3.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].call(it), it);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure3.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestTask$_writeManifestMeta_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestTask$_writeManifestMeta_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestTask$_writeManifestMeta_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestTask$_writeManifestMeta_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "remove";
            stringArr0[1] = "parent";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerManifestTask$_writeManifestMeta_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestTask$_writeManifestMeta_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestTask$_writeManifestMeta_closure3.$callSiteArray != null ? TinkerManifestTask$_writeManifestMeta_closure3.$createCallSiteArray() : (CallSiteArray)TinkerManifestTask$_writeManifestMeta_closure3.$callSiteArray.get();
            TinkerManifestTask$_writeManifestMeta_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerManifestTask$_writeManifestMeta_closure2
    public final class TinkerManifestTask$_writeManifestMeta_closure2 implements GeneratedClosure {
        private synthetic Reference ns;
        private synthetic Reference name;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestTask$_writeManifestMeta_closure2(Object _outerInstance, Object _thisObject, Reference ns, Reference name) {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            ns.ns = this;
            name.name = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure2.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].call(siteArr0[2].call(it), siteArr0[3].callGetProperty(this.ns.get())), this.name.get());
        }

        @Generated
        public Object getNs() {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure2.$getCallSiteArray();
            return this.ns.get();
        }

        @Generated
        public String getName() {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure2.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.name.get());
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestTask$_writeManifestMeta_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestTask$_writeManifestMeta_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestTask$_writeManifestMeta_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestTask$_writeManifestMeta_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "equals";
            stringArr0[1] = "getAt";
            stringArr0[2] = "attributes";
            stringArr0[3] = "name";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerManifestTask$_writeManifestMeta_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestTask$_writeManifestMeta_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestTask$_writeManifestMeta_closure2.$callSiteArray != null ? TinkerManifestTask$_writeManifestMeta_closure2.$createCallSiteArray() : (CallSiteArray)TinkerManifestTask$_writeManifestMeta_closure2.$callSiteArray.get();
            TinkerManifestTask$_writeManifestMeta_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerManifestTask$_writeManifestMeta_closure2
    public final class TinkerManifestTask$_writeManifestMeta_closure2 implements GeneratedClosure {
        private synthetic Reference ns;
        private synthetic Reference name;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestTask$_writeManifestMeta_closure2(Object _outerInstance, Object _thisObject, Reference ns, Reference name) {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            ns.ns = this;
            name.name = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure2.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].call(siteArr0[2].call(it), siteArr0[3].callGetProperty(this.ns.get())), this.name.get());
        }

        @Generated
        public Object getNs() {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure2.$getCallSiteArray();
            return this.ns.get();
        }

        @Generated
        public String getName() {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure2.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.name.get());
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerManifestTask$_writeManifestMeta_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestTask$_writeManifestMeta_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestTask$_writeManifestMeta_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestTask$_writeManifestMeta_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestTask$_writeManifestMeta_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "equals";
            stringArr0[1] = "getAt";
            stringArr0[2] = "attributes";
            stringArr0[3] = "name";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerManifestTask$_writeManifestMeta_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestTask$_writeManifestMeta_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestTask$_writeManifestMeta_closure2.$callSiteArray != null ? TinkerManifestTask$_writeManifestMeta_closure2.$createCallSiteArray() : (CallSiteArray)TinkerManifestTask$_writeManifestMeta_closure2.$callSiteArray.get();
            TinkerManifestTask$_writeManifestMeta_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
