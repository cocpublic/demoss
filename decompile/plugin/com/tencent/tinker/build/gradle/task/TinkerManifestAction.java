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
import org.gradle.api.Project;
import org.gradle.api.Task;
import java.util.Map;
import java.io.File;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.util.Node;
import groovy.transform.Generated;
import groovy.transform.Internal;

// class: com/tencent/tinker/build/gradle/task/TinkerManifestAction
public class TinkerManifestAction implements Action<Task>, GroovyObject {
    final private static String TINKER_ID;
    final private static String TINKER_ID_PREFIX;
    final protected Project project;
    final private Map<String, File> outputNameToManifestMap;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerManifestAction(Project project) {
        CallSite[] siteArr0 = TinkerManifestAction.$getCallSiteArray();
        super();
        Object object = siteArr0[0].callConstructor(HashMap.class);
        (Map)ScriptBytecodeAdapter.castToType(object, Map.class).outputNameToManifestMap = this;
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        (Project)ScriptBytecodeAdapter.castToType(project, Project.class).project = this;
    }

    public void execute(Task task) {
        CallSite[] siteArr0 = TinkerManifestAction.$getCallSiteArray();
        if (TinkerManifestAction.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            this.updateManifest();
            v_4 = null;
        }
        else {
            siteArr0[1].callCurrent(this);
        }
    }

    protected void updateManifest() {
        CallSite[] siteArr0 = TinkerManifestAction.$getCallSiteArray();
        v_21 = alloc(Reference);
        new (String)ShortTypeHandling.castToString(siteArr0[2].callGetProperty(siteArr0[3].callGetProperty(siteArr0[4].callGetProperty(siteArr0[5].callGetProperty(this.project))))).<init>(v_21);
        Reference tinkerValue = v_21;
        v_44 = alloc(Reference);
        new (Boolean)ScriptBytecodeAdapter.castToType(siteArr0[6].callGetProperty(siteArr0[7].callGetProperty(siteArr0[8].callGetProperty(siteArr0[9].callGetProperty(this.project)))), Boolean.class).<init>(v_44);
        Reference appendOutputNameToTinkerId = v_44;
        if (! ScriptBytecodeAdapter.compareEqual((String)tinkerValue.get(), null) || DefaultTypeTransformation.booleanUnbox(siteArr0[10].call((String)tinkerValue.get())) ? 0 : 1 != 0) {
            throw (Throwable)siteArr0[11].callConstructor(GradleException.class, "tinkerId is not set!!!");
        }
        else {
            Object object = siteArr0[12].call(TinkerManifestAction.TINKER_ID_PREFIX, (String)tinkerValue.get());
            (String)ShortTypeHandling.castToString(object).set((Reference)tinkerValue);
            v_94 = alloc(Reference);
            new siteArr0[13].callConstructor(File.class, siteArr0[14].callGetProperty(this.project), "intermediates").<init>(v_94);
            Reference agpIntermediatesDir = v_94;
            siteArr0[15].call(this.outputNameToManifestMap, new TinkerManifestAction$_updateManifest_closure1(this, this, tinkerValue, appendOutputNameToTinkerId, agpIntermediatesDir));
        }
    }

    protected static void writeManifestMeta(String manifestPath, String name, String value) {
        v_1 = alloc(Reference);
        new name.<init>(v_1);
        Reference reference = v_1;
        CallSite[] siteArr0 = TinkerManifestAction.$getCallSiteArray();
        v_10 = alloc(Reference);
        new siteArr0[16].callConstructor(Namespace.class, "http://schemas.android.com/apk/res/android", "android").<init>(v_10);
        Reference ns = v_10;
        Object isr = null;
        Object pw = null;
        try {
            Object object = siteArr0[17].callConstructor(InputStreamReader.class, siteArr0[18].callConstructor(FileInputStream.class, manifestPath), "utf-8");
            Node xml = (Node)ScriptBytecodeAdapter.asType(siteArr0[19].call(siteArr0[20].callConstructor(XmlParser.class), object), Node.class);
            Node application = (Node)ScriptBytecodeAdapter.asType(siteArr0[21].call(siteArr0[22].callGetProperty(xml), Integer.valueOf(0)), Node.class);
            if (DefaultTypeTransformation.booleanUnbox(application)) {
                Object metaDataTags = siteArr0[23].call(application, "meta-data");
                Object tinkerId = siteArr0[24].call(siteArr0[25].call(metaDataTags, new TinkerManifestAction$_writeManifestMeta_closure2(TinkerManifestAction.class, TinkerManifestAction.class, ns, reference)), new TinkerManifestAction$_writeManifestMeta_closure3(TinkerManifestAction.class, TinkerManifestAction.class));
                siteArr0[26].call(application, "meta-data", ScriptBytecodeAdapter.createMap(new Object[]{siteArr0[27].call(siteArr0[28].callGetProperty(ns.get()), ":name"), (String)reference.get(), siteArr0[29].call(siteArr0[30].callGetProperty(ns.get()), ":value"), value}));
                Object objectVar1 = siteArr0[31].callConstructor(PrintWriter.class, manifestPath, "utf-8");
                Object printer = siteArr0[32].callConstructor(XmlNodePrinter.class, objectVar1);
                int i0 = true;
                ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i0), null, printer, (String)"preserveWhitespace");
                siteArr0[33].call(printer, xml);
            }
            goto 426;
            siteArr0[34].call(IOHelper.class, objectVar1);
            siteArr0[35].call(IOHelper.class, object);
            return;
        }
        finally {
            Throwable throwable = v_61;
            siteArr0[36].call(IOHelper.class, objectVar1);
            siteArr0[37].call(IOHelper.class, object);
            throw throwable;
        }
    }

    protected void addApplicationToLoaderPattern(String manifestPath) {
        CallSite[] siteArr0 = TinkerManifestAction.$getCallSiteArray();
        Iterable loader = (Iterable)ScriptBytecodeAdapter.castToType(siteArr0[38].callGetProperty(siteArr0[39].callGetProperty(siteArr0[40].callGetProperty(siteArr0[41].callGetProperty(this.project)))), Iterable.class);
        Object applicationName = null;
        String str0;
        if (TinkerManifestAction.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 109;
            String str1 = TinkerManifestAction.readManifestApplicationName(manifestPath);
        }
        else {
            Object object = siteArr0[42].callStatic(TinkerManifestAction.class, manifestPath);
            str0 = (String)ShortTypeHandling.castToString(object);
        }
        if (BytecodeInterface8.isOrigZ() && TinkerManifestAction.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 261;
            if (ScriptBytecodeAdapter.compareNotEqual(str1, null)) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[47].call(loader, str1)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[48].call(loader, str1);
                siteArr0[49].call(siteArr0[50].callGetProperty(this.project), new GStringImpl(new Object[]{str1}, new String[]{"tinker add ", " to dex loader pattern"}));
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareNotEqual(str1, null)) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[43].call(loader, str1)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[44].call(loader, str1);
                siteArr0[45].call(siteArr0[46].callGetProperty(this.project), new GStringImpl(new Object[]{str1}, new String[]{"tinker add ", " to dex loader pattern"}));
            }
        }
        String loaderClass = "com.tencent.tinker.loader.*";
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[51].call(loader, loaderClass)) ? 0 : 1 != 0) {
            siteArr0[52].call(loader, loaderClass);
            siteArr0[53].call(siteArr0[54].callGetProperty(this.project), new GStringImpl(new Object[]{loaderClass}, new String[]{"tinker add ", " to dex loader pattern"}));
        }
    }

    protected static String readManifestApplicationName(String manifestPath) {
        CallSite[] siteArr0 = TinkerManifestAction.$getCallSiteArray();
        Object isr = null;
        try {
            Object object = siteArr0[55].callConstructor(InputStreamReader.class, siteArr0[56].callConstructor(FileInputStream.class, manifestPath), "utf-8");
            Object xml = siteArr0[57].call(siteArr0[58].callConstructor(XmlParser.class), object);
            Object ns = siteArr0[59].callConstructor(Namespace.class, "http://schemas.android.com/apk/res/android", "android");
            Object application = siteArr0[60].call(siteArr0[61].callGetProperty(xml), Integer.valueOf(0));
            if (DefaultTypeTransformation.booleanUnbox(application)) {
                String str0 = (String)ShortTypeHandling.castToString(siteArr0[62].call(siteArr0[63].call(application), siteArr0[64].callGetProperty(ns)));
                siteArr0[65].call(IOHelper.class, object);
                return str0;
            }
            else {
                String str1 = (String)ShortTypeHandling.castToString(null);
                siteArr0[66].call(IOHelper.class, object);
                return str1;
            }
        }
        finally {
            Throwable throwable = v_50;
            siteArr0[68].call(IOHelper.class, object);
            throw throwable;
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerManifestAction.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerManifestAction.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerManifestAction.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerManifestAction.$staticClassInfo.getMetaClass();
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
    public static String getTINKER_ID() {
        return TinkerManifestAction.TINKER_ID;
    }

    @Generated
    public static String getTINKER_ID_PREFIX() {
        return TinkerManifestAction.TINKER_ID_PREFIX;
    }

    @Generated
    final public Map<String, File> getOutputNameToManifestMap() {
        return this.outputNameToManifestMap;
    }

    @Generated
    public /* synthetic */ void execute(Object object) {
        this.execute((Task)object);
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "<$constructor$>";
        stringArr0[1] = "updateManifest";
        stringArr0[2] = "tinkerId";
        stringArr0[3] = "buildConfig";
        stringArr0[4] = "tinkerPatch";
        stringArr0[5] = "extensions";
        stringArr0[6] = "appendOutputNameToTinkerId";
        stringArr0[7] = "buildConfig";
        stringArr0[8] = "tinkerPatch";
        stringArr0[9] = "extensions";
        stringArr0[10] = "isEmpty";
        stringArr0[11] = "<$constructor$>";
        stringArr0[12] = "plus";
        stringArr0[13] = "<$constructor$>";
        stringArr0[14] = "buildDir";
        stringArr0[15] = "each";
        stringArr0[16] = "<$constructor$>";
        stringArr0[17] = "<$constructor$>";
        stringArr0[18] = "<$constructor$>";
        stringArr0[19] = "parse";
        stringArr0[20] = "<$constructor$>";
        stringArr0[21] = "getAt";
        stringArr0[22] = "application";
        stringArr0[23] = "getAt";
        stringArr0[24] = "each";
        stringArr0[25] = "findAll";
        stringArr0[26] = "appendNode";
        stringArr0[27] = "plus";
        stringArr0[28] = "prefix";
        stringArr0[29] = "plus";
        stringArr0[30] = "prefix";
        stringArr0[31] = "<$constructor$>";
        stringArr0[32] = "<$constructor$>";
        stringArr0[33] = "print";
        stringArr0[34] = "closeQuietly";
        stringArr0[35] = "closeQuietly";
        stringArr0[36] = "closeQuietly";
        stringArr0[37] = "closeQuietly";
        stringArr0[38] = "loader";
        stringArr0[39] = "dex";
        stringArr0[40] = "tinkerPatch";
        stringArr0[41] = "extensions";
        stringArr0[42] = "readManifestApplicationName";
        stringArr0[43] = "contains";
        stringArr0[44] = "add";
        stringArr0[45] = "error";
        stringArr0[46] = "logger";
        stringArr0[47] = "contains";
        stringArr0[48] = "add";
        stringArr0[49] = "error";
        stringArr0[50] = "logger";
        stringArr0[51] = "contains";
        stringArr0[52] = "add";
        stringArr0[53] = "error";
        stringArr0[54] = "logger";
        stringArr0[55] = "<$constructor$>";
        stringArr0[56] = "<$constructor$>";
        stringArr0[57] = "parse";
        stringArr0[58] = "<$constructor$>";
        stringArr0[59] = "<$constructor$>";
        stringArr0[60] = "getAt";
        stringArr0[61] = "application";
        stringArr0[62] = "getAt";
        stringArr0[63] = "attributes";
        stringArr0[64] = "name";
        stringArr0[65] = "closeQuietly";
        stringArr0[66] = "closeQuietly";
        stringArr0[67] = "closeQuietly";
        stringArr0[68] = "closeQuietly";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerManifestAction.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerManifestAction.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerManifestAction.$callSiteArray != null ? TinkerManifestAction.$createCallSiteArray() : (CallSiteArray)TinkerManifestAction.$callSiteArray.get();
        TinkerManifestAction.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/tinker/build/gradle/task/TinkerManifestAction$_updateManifest_closure1
    public final class TinkerManifestAction$_updateManifest_closure1 implements GeneratedClosure {
        private synthetic Reference tinkerValue;
        private synthetic Reference appendOutputNameToTinkerId;
        private synthetic Reference agpIntermediatesDir;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestAction$_updateManifest_closure1(Object _outerInstance, Object _thisObject, Reference tinkerValue, Reference appendOutputNameToTinkerId, Reference agpIntermediatesDir) {
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            tinkerValue.tinkerValue = this;
            appendOutputNameToTinkerId.appendOutputNameToTinkerId = this;
            agpIntermediatesDir.agpIntermediatesDir = this;
        }

        public Object doCall(String outputName, File manifest) {
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            Object manifestPath = siteArr0[0].call(manifest);
            Object finalTinkerValue = this.tinkerValue.get();
            if (DefaultTypeTransformation.booleanUnbox(this.appendOutputNameToTinkerId.get())) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(outputName)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                v_38 = siteArr0[2].call(finalTinkerValue, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                finalTinkerValue = siteArr0[2].call(finalTinkerValue, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
            }
            siteArr0[3].call(siteArr0[4].callGetProperty(siteArr0[5].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{finalTinkerValue, manifestPath}, new String[]{"tinker add ", " to your AndroidManifest.xml ", ""}));
            siteArr0[6].callStatic(TinkerManifestAction.class, manifestPath, siteArr0[7].callGetProperty(TinkerManifestAction.class), finalTinkerValue);
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
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return siteArr0[24].callCurrent(this, outputName, manifest);
        }

        @Generated
        public String getTinkerValue() {
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.tinkerValue.get());
        }

        @Generated
        public Boolean getAppendOutputNameToTinkerId() {
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return (Boolean)ScriptBytecodeAdapter.castToType(this.appendOutputNameToTinkerId.get(), Boolean.class);
        }

        @Generated
        public Object getAgpIntermediatesDir() {
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return this.agpIntermediatesDir.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestAction$_updateManifest_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestAction$_updateManifest_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestAction$_updateManifest_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestAction$_updateManifest_closure1.$staticClassInfo.getMetaClass();
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
            TinkerManifestAction$_updateManifest_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestAction$_updateManifest_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestAction$_updateManifest_closure1.$callSiteArray != null ? TinkerManifestAction$_updateManifest_closure1.$createCallSiteArray() : (CallSiteArray)TinkerManifestAction$_updateManifest_closure1.$callSiteArray.get();
            TinkerManifestAction$_updateManifest_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerManifestAction$_updateManifest_closure1
    public final class TinkerManifestAction$_updateManifest_closure1 implements GeneratedClosure {
        private synthetic Reference tinkerValue;
        private synthetic Reference appendOutputNameToTinkerId;
        private synthetic Reference agpIntermediatesDir;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestAction$_updateManifest_closure1(Object _outerInstance, Object _thisObject, Reference tinkerValue, Reference appendOutputNameToTinkerId, Reference agpIntermediatesDir) {
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            tinkerValue.tinkerValue = this;
            appendOutputNameToTinkerId.appendOutputNameToTinkerId = this;
            agpIntermediatesDir.agpIntermediatesDir = this;
        }

        public Object doCall(String outputName, File manifest) {
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            Object manifestPath = siteArr0[0].call(manifest);
            Object finalTinkerValue = this.tinkerValue.get();
            if (DefaultTypeTransformation.booleanUnbox(this.appendOutputNameToTinkerId.get())) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(outputName)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                v_38 = siteArr0[2].call(finalTinkerValue, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                finalTinkerValue = siteArr0[2].call(finalTinkerValue, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
            }
            siteArr0[3].call(siteArr0[4].callGetProperty(siteArr0[5].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{finalTinkerValue, manifestPath}, new String[]{"tinker add ", " to your AndroidManifest.xml ", ""}));
            siteArr0[6].callStatic(TinkerManifestAction.class, manifestPath, siteArr0[7].callGetProperty(TinkerManifestAction.class), finalTinkerValue);
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
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return siteArr0[24].callCurrent(this, outputName, manifest);
        }

        @Generated
        public String getTinkerValue() {
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.tinkerValue.get());
        }

        @Generated
        public Boolean getAppendOutputNameToTinkerId() {
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return (Boolean)ScriptBytecodeAdapter.castToType(this.appendOutputNameToTinkerId.get(), Boolean.class);
        }

        @Generated
        public Object getAgpIntermediatesDir() {
            CallSite[] siteArr0 = TinkerManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return this.agpIntermediatesDir.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestAction$_updateManifest_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestAction$_updateManifest_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestAction$_updateManifest_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestAction$_updateManifest_closure1.$staticClassInfo.getMetaClass();
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
            TinkerManifestAction$_updateManifest_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestAction$_updateManifest_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestAction$_updateManifest_closure1.$callSiteArray != null ? TinkerManifestAction$_updateManifest_closure1.$createCallSiteArray() : (CallSiteArray)TinkerManifestAction$_updateManifest_closure1.$callSiteArray.get();
            TinkerManifestAction$_updateManifest_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerManifestAction$_writeManifestMeta_closure3
    public final class TinkerManifestAction$_writeManifestMeta_closure3 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestAction$_writeManifestMeta_closure3(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure3.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].call(it), it);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure3.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestAction$_writeManifestMeta_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestAction$_writeManifestMeta_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestAction$_writeManifestMeta_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestAction$_writeManifestMeta_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "remove";
            stringArr0[1] = "parent";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerManifestAction$_writeManifestMeta_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestAction$_writeManifestMeta_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestAction$_writeManifestMeta_closure3.$callSiteArray != null ? TinkerManifestAction$_writeManifestMeta_closure3.$createCallSiteArray() : (CallSiteArray)TinkerManifestAction$_writeManifestMeta_closure3.$callSiteArray.get();
            TinkerManifestAction$_writeManifestMeta_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerManifestAction$_writeManifestMeta_closure3
    public final class TinkerManifestAction$_writeManifestMeta_closure3 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestAction$_writeManifestMeta_closure3(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure3.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].call(it), it);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure3.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestAction$_writeManifestMeta_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestAction$_writeManifestMeta_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestAction$_writeManifestMeta_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestAction$_writeManifestMeta_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "remove";
            stringArr0[1] = "parent";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerManifestAction$_writeManifestMeta_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestAction$_writeManifestMeta_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestAction$_writeManifestMeta_closure3.$callSiteArray != null ? TinkerManifestAction$_writeManifestMeta_closure3.$createCallSiteArray() : (CallSiteArray)TinkerManifestAction$_writeManifestMeta_closure3.$callSiteArray.get();
            TinkerManifestAction$_writeManifestMeta_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerManifestAction$_writeManifestMeta_closure2
    public final class TinkerManifestAction$_writeManifestMeta_closure2 implements GeneratedClosure {
        private synthetic Reference ns;
        private synthetic Reference name;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestAction$_writeManifestMeta_closure2(Object _outerInstance, Object _thisObject, Reference ns, Reference name) {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            ns.ns = this;
            name.name = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure2.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].call(siteArr0[2].call(it), siteArr0[3].callGetProperty(this.ns.get())), this.name.get());
        }

        @Generated
        public Object getNs() {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure2.$getCallSiteArray();
            return this.ns.get();
        }

        @Generated
        public String getName() {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure2.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.name.get());
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestAction$_writeManifestMeta_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestAction$_writeManifestMeta_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestAction$_writeManifestMeta_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestAction$_writeManifestMeta_closure2.$staticClassInfo.getMetaClass();
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
            TinkerManifestAction$_writeManifestMeta_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestAction$_writeManifestMeta_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestAction$_writeManifestMeta_closure2.$callSiteArray != null ? TinkerManifestAction$_writeManifestMeta_closure2.$createCallSiteArray() : (CallSiteArray)TinkerManifestAction$_writeManifestMeta_closure2.$callSiteArray.get();
            TinkerManifestAction$_writeManifestMeta_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/task/TinkerManifestAction$_writeManifestMeta_closure2
    public final class TinkerManifestAction$_writeManifestMeta_closure2 implements GeneratedClosure {
        private synthetic Reference ns;
        private synthetic Reference name;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerManifestAction$_writeManifestMeta_closure2(Object _outerInstance, Object _thisObject, Reference ns, Reference name) {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            ns.ns = this;
            name.name = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure2.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].call(siteArr0[2].call(it), siteArr0[3].callGetProperty(this.ns.get())), this.name.get());
        }

        @Generated
        public Object getNs() {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure2.$getCallSiteArray();
            return this.ns.get();
        }

        @Generated
        public String getName() {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure2.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.name.get());
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerManifestAction$_writeManifestMeta_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerManifestAction$_writeManifestMeta_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerManifestAction$_writeManifestMeta_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerManifestAction$_writeManifestMeta_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerManifestAction$_writeManifestMeta_closure2.$staticClassInfo.getMetaClass();
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
            TinkerManifestAction$_writeManifestMeta_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerManifestAction$_writeManifestMeta_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerManifestAction$_writeManifestMeta_closure2.$callSiteArray != null ? TinkerManifestAction$_writeManifestMeta_closure2.$createCallSiteArray() : (CallSiteArray)TinkerManifestAction$_writeManifestMeta_closure2.$callSiteArray.get();
            TinkerManifestAction$_writeManifestMeta_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
