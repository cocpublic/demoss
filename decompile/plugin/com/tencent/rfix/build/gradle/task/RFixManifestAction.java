/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/task;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import com.tencent.rfix.build.gradle.extension.RFixPatchExtension;

// class: com/tencent/rfix/build/gradle/task/RFixManifestAction
public class RFixManifestAction {
    protected RFixPatchExtension configuration;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixManifestAction(Project project) {
        CallSite[] siteArr0 = RFixManifestAction.$getCallSiteArray();
        super(project);
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object object = siteArr0[0].callGetProperty(siteArr0[1].callGetProperty(project));
        (RFixPatchExtension)ScriptBytecodeAdapter.castToType(object, RFixPatchExtension.class).configuration = this;
    }

    public void updateManifest() {
        CallSite[] siteArr0 = RFixManifestAction.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[2].callGetProperty(siteArr0[3].callGroovyObjectGetProperty(this.configuration))) ? 0 : 1 != 0) {
            siteArr0[4].call(siteArr0[5].callGetProperty(siteArr0[6].callGroovyObjectGetProperty(this)), "rfix add patchId to manifest disable.");
        }
        else {
            v_39 = alloc(Reference);
            new (String)ShortTypeHandling.castToString(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this.configuration))).<init>(v_39);
            Reference patchId = v_39;
            v_54 = alloc(Reference);
            new (Boolean)ScriptBytecodeAdapter.castToType(siteArr0[9].callGetProperty(siteArr0[10].callGroovyObjectGetProperty(this.configuration)), Boolean.class).<init>(v_54);
            Reference appendOutputNameToPatchId = v_54;
            if (! ScriptBytecodeAdapter.compareEqual((String)patchId.get(), null) || DefaultTypeTransformation.booleanUnbox(siteArr0[11].call((String)patchId.get())) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[12].callConstructor(GradleException.class, "patchId is not set!");
            }
            else {
                v_83 = alloc(Reference);
                new siteArr0[13].callGroovyObjectGetProperty(this).<init>(v_83);
                Reference TINKER_ID = v_83;
                v_90 = alloc(Reference);
                new siteArr0[14].callGroovyObjectGetProperty(this).<init>(v_90);
                Reference TINKER_ID_PREFIX = v_90;
                siteArr0[15].call(siteArr0[16].callGroovyObjectGetProperty(this), new RFixManifestAction$_updateManifest_closure1(this, this, patchId, appendOutputNameToPatchId, TINKER_ID_PREFIX, TINKER_ID));
            }
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixManifestAction.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixManifestAction.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixManifestAction.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixManifestAction.$staticClassInfo.getMetaClass();
        }
    }

    public /* synthetic */ void super$2$updateManifest() {
        this.updateManifest();
    }

    public /* synthetic */ MetaClass super$2$$getStaticMetaClass() {
        return this.$getStaticMetaClass();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "RFixPatch";
        stringArr0[1] = "extensions";
        stringArr0[2] = "enablePatchIdToManifest";
        stringArr0[3] = "buildConfig";
        stringArr0[4] = "warn";
        stringArr0[5] = "logger";
        stringArr0[6] = "project";
        stringArr0[7] = "patchId";
        stringArr0[8] = "buildConfig";
        stringArr0[9] = "appendOutputNameToPatchId";
        stringArr0[10] = "buildConfig";
        stringArr0[11] = "isEmpty";
        stringArr0[12] = "<$constructor$>";
        stringArr0[13] = "TINKER_ID";
        stringArr0[14] = "TINKER_ID_PREFIX";
        stringArr0[15] = "each";
        stringArr0[16] = "outputNameToManifestMap";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixManifestAction.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixManifestAction.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixManifestAction.$callSiteArray != null ? RFixManifestAction.$createCallSiteArray() : (CallSiteArray)RFixManifestAction.$callSiteArray.get();
        RFixManifestAction.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/rfix/build/gradle/task/RFixManifestAction$_updateManifest_closure1
    public final class RFixManifestAction$_updateManifest_closure1 implements GeneratedClosure {
        private synthetic Reference patchId;
        private synthetic Reference appendOutputNameToPatchId;
        private synthetic Reference TINKER_ID_PREFIX;
        private synthetic Reference TINKER_ID;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixManifestAction$_updateManifest_closure1(Object _outerInstance, Object _thisObject, Reference patchId, Reference appendOutputNameToPatchId, Reference TINKER_ID_PREFIX, Reference TINKER_ID) {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            patchId.patchId = this;
            appendOutputNameToPatchId.appendOutputNameToPatchId = this;
            TINKER_ID_PREFIX.TINKER_ID_PREFIX = this;
            TINKER_ID.TINKER_ID = this;
        }

        public Object doCall(String outputName, File manifest) {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            Object manifestPath = siteArr0[0].call(manifest);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(manifest)) ? 0 : 1 != 0) {
                siteArr0[2].call(siteArr0[3].callGetProperty(siteArr0[4].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{manifestPath}, new String[]{"rfix add patch id fail! ", " not exists."}));
                return null;
            }
            else {
                Object finalPatchId = siteArr0[5].call(siteArr0[6].callGetProperty(RFixConstants.class), this.patchId.get());
                if (DefaultTypeTransformation.booleanUnbox(this.appendOutputNameToPatchId.get())) {
                }
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(outputName)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                    v_79 = siteArr0[8].call(finalPatchId, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                    finalPatchId = siteArr0[8].call(finalPatchId, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                }
                siteArr0[9].callStatic(RFixManifestAction.class, manifestPath, siteArr0[10].callGetProperty(RFixConstants.class), finalPatchId);
                siteArr0[11].call(siteArr0[12].callGetProperty(siteArr0[13].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{finalPatchId, manifestPath}, new String[]{"rfix add ", " to your AndroidManifest.xml ", ""}));
                Object finalTinkerId = siteArr0[14].call(this.TINKER_ID_PREFIX.get(), this.patchId.get());
                if (DefaultTypeTransformation.booleanUnbox(this.appendOutputNameToPatchId.get())) {
                }
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[15].call(outputName)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                    v_158 = siteArr0[16].call(finalTinkerId, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                    finalTinkerId = siteArr0[16].call(finalTinkerId, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                }
                siteArr0[17].callStatic(RFixManifestAction.class, manifestPath, this.TINKER_ID.get(), finalTinkerId);
                siteArr0[18].call(siteArr0[19].callGetProperty(siteArr0[20].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{finalTinkerId, manifestPath}, new String[]{"tinker add ", " to your AndroidManifest.xml ", ""}));
                siteArr0[21].callCurrent(this, manifestPath);
                Object agpIntermediatesDir = siteArr0[22].callConstructor(File.class, siteArr0[23].callGetProperty(siteArr0[24].callGroovyObjectGetProperty(this)), "intermediates");
                Object manifestRelPath = siteArr0[25].call(siteArr0[26].call(siteArr0[27].call(agpIntermediatesDir), siteArr0[28].call(manifest)));
                Object manifestDestPath = siteArr0[29].callConstructor(File.class, siteArr0[30].call(siteArr0[31].callGroovyObjectGetProperty(this), siteArr0[32].call(TinkerBuildPath.class, siteArr0[33].callGroovyObjectGetProperty(this))), manifestRelPath);
                siteArr0[34].call(FileOperation.class, manifest, manifestDestPath);
                return siteArr0[35].call(siteArr0[36].callGetProperty(siteArr0[37].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{manifestDestPath}, new String[]{"tinker gen AndroidManifest.xml in ", ""}));
            }
        }

        public Object call(String outputName, File manifest) {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return siteArr0[38].callCurrent(this, outputName, manifest);
        }

        @Generated
        public String getPatchId() {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.patchId.get());
        }

        @Generated
        public Boolean getAppendOutputNameToPatchId() {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return (Boolean)ScriptBytecodeAdapter.castToType(this.appendOutputNameToPatchId.get(), Boolean.class);
        }

        @Generated
        public Object getTINKER_ID_PREFIX() {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return this.TINKER_ID_PREFIX.get();
        }

        @Generated
        public Object getTINKER_ID() {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return this.TINKER_ID.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixManifestAction$_updateManifest_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixManifestAction$_updateManifest_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixManifestAction$_updateManifest_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixManifestAction$_updateManifest_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "getAbsolutePath";
            stringArr0[1] = "exists";
            stringArr0[2] = "error";
            stringArr0[3] = "logger";
            stringArr0[4] = "project";
            stringArr0[5] = "plus";
            stringArr0[6] = "PATCH_ID_PREFIX";
            stringArr0[7] = "isEmpty";
            stringArr0[8] = "plus";
            stringArr0[9] = "writeManifestMeta";
            stringArr0[10] = "PATCH_ID";
            stringArr0[11] = "error";
            stringArr0[12] = "logger";
            stringArr0[13] = "project";
            stringArr0[14] = "plus";
            stringArr0[15] = "isEmpty";
            stringArr0[16] = "plus";
            stringArr0[17] = "writeManifestMeta";
            stringArr0[18] = "error";
            stringArr0[19] = "logger";
            stringArr0[20] = "project";
            stringArr0[21] = "addApplicationToLoaderPattern";
            stringArr0[22] = "<$constructor$>";
            stringArr0[23] = "buildDir";
            stringArr0[24] = "project";
            stringArr0[25] = "toString";
            stringArr0[26] = "relativize";
            stringArr0[27] = "toPath";
            stringArr0[28] = "toPath";
            stringArr0[29] = "<$constructor$>";
            stringArr0[30] = "file";
            stringArr0[31] = "project";
            stringArr0[32] = "getTinkerIntermediates";
            stringArr0[33] = "project";
            stringArr0[34] = "copyFileUsingStream";
            stringArr0[35] = "error";
            stringArr0[36] = "logger";
            stringArr0[37] = "project";
            stringArr0[38] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixManifestAction$_updateManifest_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixManifestAction$_updateManifest_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixManifestAction$_updateManifest_closure1.$callSiteArray != null ? RFixManifestAction$_updateManifest_closure1.$createCallSiteArray() : (CallSiteArray)RFixManifestAction$_updateManifest_closure1.$callSiteArray.get();
            RFixManifestAction$_updateManifest_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/task/RFixManifestAction$_updateManifest_closure1
    public final class RFixManifestAction$_updateManifest_closure1 implements GeneratedClosure {
        private synthetic Reference patchId;
        private synthetic Reference appendOutputNameToPatchId;
        private synthetic Reference TINKER_ID_PREFIX;
        private synthetic Reference TINKER_ID;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixManifestAction$_updateManifest_closure1(Object _outerInstance, Object _thisObject, Reference patchId, Reference appendOutputNameToPatchId, Reference TINKER_ID_PREFIX, Reference TINKER_ID) {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            patchId.patchId = this;
            appendOutputNameToPatchId.appendOutputNameToPatchId = this;
            TINKER_ID_PREFIX.TINKER_ID_PREFIX = this;
            TINKER_ID.TINKER_ID = this;
        }

        public Object doCall(String outputName, File manifest) {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            Object manifestPath = siteArr0[0].call(manifest);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(manifest)) ? 0 : 1 != 0) {
                siteArr0[2].call(siteArr0[3].callGetProperty(siteArr0[4].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{manifestPath}, new String[]{"rfix add patch id fail! ", " not exists."}));
                return null;
            }
            else {
                Object finalPatchId = siteArr0[5].call(siteArr0[6].callGetProperty(RFixConstants.class), this.patchId.get());
                if (DefaultTypeTransformation.booleanUnbox(this.appendOutputNameToPatchId.get())) {
                }
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[7].call(outputName)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                    v_79 = siteArr0[8].call(finalPatchId, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                    finalPatchId = siteArr0[8].call(finalPatchId, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                }
                siteArr0[9].callStatic(RFixManifestAction.class, manifestPath, siteArr0[10].callGetProperty(RFixConstants.class), finalPatchId);
                siteArr0[11].call(siteArr0[12].callGetProperty(siteArr0[13].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{finalPatchId, manifestPath}, new String[]{"rfix add ", " to your AndroidManifest.xml ", ""}));
                Object finalTinkerId = siteArr0[14].call(this.TINKER_ID_PREFIX.get(), this.patchId.get());
                if (DefaultTypeTransformation.booleanUnbox(this.appendOutputNameToPatchId.get())) {
                }
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[15].call(outputName)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                    v_158 = siteArr0[16].call(finalTinkerId, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                    finalTinkerId = siteArr0[16].call(finalTinkerId, new GStringImpl(new Object[]{outputName}, new String[]{"_", ""}));
                }
                siteArr0[17].callStatic(RFixManifestAction.class, manifestPath, this.TINKER_ID.get(), finalTinkerId);
                siteArr0[18].call(siteArr0[19].callGetProperty(siteArr0[20].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{finalTinkerId, manifestPath}, new String[]{"tinker add ", " to your AndroidManifest.xml ", ""}));
                siteArr0[21].callCurrent(this, manifestPath);
                Object agpIntermediatesDir = siteArr0[22].callConstructor(File.class, siteArr0[23].callGetProperty(siteArr0[24].callGroovyObjectGetProperty(this)), "intermediates");
                Object manifestRelPath = siteArr0[25].call(siteArr0[26].call(siteArr0[27].call(agpIntermediatesDir), siteArr0[28].call(manifest)));
                Object manifestDestPath = siteArr0[29].callConstructor(File.class, siteArr0[30].call(siteArr0[31].callGroovyObjectGetProperty(this), siteArr0[32].call(TinkerBuildPath.class, siteArr0[33].callGroovyObjectGetProperty(this))), manifestRelPath);
                siteArr0[34].call(FileOperation.class, manifest, manifestDestPath);
                return siteArr0[35].call(siteArr0[36].callGetProperty(siteArr0[37].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{manifestDestPath}, new String[]{"tinker gen AndroidManifest.xml in ", ""}));
            }
        }

        public Object call(String outputName, File manifest) {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return siteArr0[38].callCurrent(this, outputName, manifest);
        }

        @Generated
        public String getPatchId() {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return (String)ShortTypeHandling.castToString(this.patchId.get());
        }

        @Generated
        public Boolean getAppendOutputNameToPatchId() {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return (Boolean)ScriptBytecodeAdapter.castToType(this.appendOutputNameToPatchId.get(), Boolean.class);
        }

        @Generated
        public Object getTINKER_ID_PREFIX() {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return this.TINKER_ID_PREFIX.get();
        }

        @Generated
        public Object getTINKER_ID() {
            CallSite[] siteArr0 = RFixManifestAction$_updateManifest_closure1.$getCallSiteArray();
            return this.TINKER_ID.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixManifestAction$_updateManifest_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixManifestAction$_updateManifest_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixManifestAction$_updateManifest_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixManifestAction$_updateManifest_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "getAbsolutePath";
            stringArr0[1] = "exists";
            stringArr0[2] = "error";
            stringArr0[3] = "logger";
            stringArr0[4] = "project";
            stringArr0[5] = "plus";
            stringArr0[6] = "PATCH_ID_PREFIX";
            stringArr0[7] = "isEmpty";
            stringArr0[8] = "plus";
            stringArr0[9] = "writeManifestMeta";
            stringArr0[10] = "PATCH_ID";
            stringArr0[11] = "error";
            stringArr0[12] = "logger";
            stringArr0[13] = "project";
            stringArr0[14] = "plus";
            stringArr0[15] = "isEmpty";
            stringArr0[16] = "plus";
            stringArr0[17] = "writeManifestMeta";
            stringArr0[18] = "error";
            stringArr0[19] = "logger";
            stringArr0[20] = "project";
            stringArr0[21] = "addApplicationToLoaderPattern";
            stringArr0[22] = "<$constructor$>";
            stringArr0[23] = "buildDir";
            stringArr0[24] = "project";
            stringArr0[25] = "toString";
            stringArr0[26] = "relativize";
            stringArr0[27] = "toPath";
            stringArr0[28] = "toPath";
            stringArr0[29] = "<$constructor$>";
            stringArr0[30] = "file";
            stringArr0[31] = "project";
            stringArr0[32] = "getTinkerIntermediates";
            stringArr0[33] = "project";
            stringArr0[34] = "copyFileUsingStream";
            stringArr0[35] = "error";
            stringArr0[36] = "logger";
            stringArr0[37] = "project";
            stringArr0[38] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixManifestAction$_updateManifest_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixManifestAction$_updateManifest_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixManifestAction$_updateManifest_closure1.$callSiteArray != null ? RFixManifestAction$_updateManifest_closure1.$createCallSiteArray() : (CallSiteArray)RFixManifestAction$_updateManifest_closure1.$callSiteArray.get();
            RFixManifestAction$_updateManifest_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
