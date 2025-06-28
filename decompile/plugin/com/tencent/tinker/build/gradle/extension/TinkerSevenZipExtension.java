/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/gradle/extension;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.Dependency;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.util.Map;
import java.util.List;
import java.io.File;

// class: com/tencent/tinker/build/gradle/extension/TinkerSevenZipExtension
public class TinkerSevenZipExtension implements GroovyObject {
    private String zipArtifact;
    private String path;
    private Project project;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerSevenZipExtension(Project project) {
        CallSite[] siteArr0 = TinkerSevenZipExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object object = null;
        (String)ShortTypeHandling.castToString(object).zipArtifact = this;
        Object objectVar1 = null;
        (String)ShortTypeHandling.castToString(objectVar1).path = this;
        (Project)ScriptBytecodeAdapter.castToType(project, Project.class).project = this;
    }

    public void resolveZipFinalPath() {
        CallSite[] siteArr0 = TinkerSevenZipExtension.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual(this.path, null)) {
        }
        else {
            if (ScriptBytecodeAdapter.compareNotEqual(this.zipArtifact, null)) {
                Object groupId = null;
                Object finalArtifact = null;
                Object version = null;
                Configuration config = (Configuration)ScriptBytecodeAdapter.castToType(siteArr0[0].call(siteArr0[1].callGetProperty(this.project), "sevenZipToolsLocator", new TinkerSevenZipExtension$_resolveZipFinalPath_closure1(this, this)), Configuration.class);
                Object object = siteArr0[2].call(this.zipArtifact, ":");
                groupId = siteArr0[3].call(object, Integer.valueOf(0));
                finalArtifact = siteArr0[4].call(object, Integer.valueOf(1));
                version = siteArr0[5].call(object, Integer.valueOf(2));
                Map notation = ScriptBytecodeAdapter.createMap(new Object[]{"group", groupId, "name", finalArtifact, "version", version, "classifier", siteArr0[6].callGetProperty(siteArr0[7].callGetProperty(this.project)), "ext", "exe"});
                Dependency dep = (Dependency)ScriptBytecodeAdapter.castToType(siteArr0[8].call(siteArr0[9].callGetProperty(this.project), siteArr0[10].callGetProperty(config), notation), Dependency.class);
                File file = (File)ScriptBytecodeAdapter.castToType(siteArr0[11].callGetProperty(siteArr0[12].call(config, dep)), File.class);
                if (BytecodeInterface8.isOrigZ() && TinkerSevenZipExtension.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                    goto 455;
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[16].call(file)) ? 0 : 1 != 0) {
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[17].call(file, Boolean.valueOf(true))) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                        throw (Throwable)siteArr0[18].callConstructor(GradleException.class, new GStringImpl(new Object[]{file}, new String[]{"Cannot set ", " as executable"}));
                    }
                }
                else {
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[13].call(file)) ? 0 : 1 != 0) {
                    }
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[14].call(file, Boolean.valueOf(true))) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                        throw (Throwable)siteArr0[15].callConstructor(GradleException.class, new GStringImpl(new Object[]{file}, new String[]{"Cannot set ", " as executable"}));
                    }
                    else {
                    }
                }
                Object objectVar1 = siteArr0[19].callGetProperty(file);
                (String)ShortTypeHandling.castToString(objectVar1).path = this;
            }
            if (ScriptBytecodeAdapter.compareEqual(this.path, null)) {
                String str0 = "7za";
                (String)ShortTypeHandling.castToString(str0).path = this;
            }
        }
    }

    public String toString() {
        CallSite[] siteArr0 = TinkerSevenZipExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[20].call(new GStringImpl(new Object[]{this.zipArtifact, this.path}, new String[]{"| zipArtifact = ", "
           | path = ", "
        "})));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerSevenZipExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerSevenZipExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerSevenZipExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerSevenZipExtension.$staticClassInfo.getMetaClass();
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
    public String getZipArtifact() {
        return this.zipArtifact;
    }

    @Generated
    public void setZipArtifact(String str0) {
        this.zipArtifact = str0;
    }

    @Generated
    public String getPath() {
        return this.path;
    }

    @Generated
    public void setPath(String str0) {
        this.path = str0;
    }

    public /* synthetic */ String super$1$toString() {
        return this.toString();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "create";
        stringArr0[1] = "configurations";
        stringArr0[2] = "split";
        stringArr0[3] = "getAt";
        stringArr0[4] = "getAt";
        stringArr0[5] = "getAt";
        stringArr0[6] = "classifier";
        stringArr0[7] = "osdetector";
        stringArr0[8] = "add";
        stringArr0[9] = "dependencies";
        stringArr0[10] = "name";
        stringArr0[11] = "singleFile";
        stringArr0[12] = "fileCollection";
        stringArr0[13] = "canExecute";
        stringArr0[14] = "setExecutable";
        stringArr0[15] = "<$constructor$>";
        stringArr0[16] = "canExecute";
        stringArr0[17] = "setExecutable";
        stringArr0[18] = "<$constructor$>";
        stringArr0[19] = "path";
        stringArr0[20] = "stripMargin";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerSevenZipExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerSevenZipExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerSevenZipExtension.$callSiteArray != null ? TinkerSevenZipExtension.$createCallSiteArray() : (CallSiteArray)TinkerSevenZipExtension.$callSiteArray.get();
        TinkerSevenZipExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/tinker/build/gradle/extension/TinkerSevenZipExtension$_resolveZipFinalPath_closure1
    public final class TinkerSevenZipExtension$_resolveZipFinalPath_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerSevenZipExtension$_resolveZipFinalPath_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$getCallSiteArray();
            int i0 = false;
            ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.valueOf(i0), TinkerSevenZipExtension$_resolveZipFinalPath_closure1.class, this, (String)"visible");
            int i1 = false;
            ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.valueOf(i1), TinkerSevenZipExtension$_resolveZipFinalPath_closure1.class, this, (String)"transitive");
            List list = ScriptBytecodeAdapter.createList(new Object[]{});
            ScriptBytecodeAdapter.setGroovyObjectProperty(list, TinkerSevenZipExtension$_resolveZipFinalPath_closure1.class, this, (String)"extendsFrom");
            return list;
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerSevenZipExtension$_resolveZipFinalPath_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            return new CallSiteArray(TinkerSevenZipExtension$_resolveZipFinalPath_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$callSiteArray != null ? TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$createCallSiteArray() : (CallSiteArray)TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$callSiteArray.get();
            TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/tinker/build/gradle/extension/TinkerSevenZipExtension$_resolveZipFinalPath_closure1
    public final class TinkerSevenZipExtension$_resolveZipFinalPath_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerSevenZipExtension$_resolveZipFinalPath_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$getCallSiteArray();
            int i0 = false;
            ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.valueOf(i0), TinkerSevenZipExtension$_resolveZipFinalPath_closure1.class, this, (String)"visible");
            int i1 = false;
            ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.valueOf(i1), TinkerSevenZipExtension$_resolveZipFinalPath_closure1.class, this, (String)"transitive");
            List list = ScriptBytecodeAdapter.createList(new Object[]{});
            ScriptBytecodeAdapter.setGroovyObjectProperty(list, TinkerSevenZipExtension$_resolveZipFinalPath_closure1.class, this, (String)"extendsFrom");
            return list;
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerSevenZipExtension$_resolveZipFinalPath_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            return new CallSiteArray(TinkerSevenZipExtension$_resolveZipFinalPath_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$callSiteArray != null ? TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$createCallSiteArray() : (CallSiteArray)TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$callSiteArray.get();
            TinkerSevenZipExtension$_resolveZipFinalPath_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
