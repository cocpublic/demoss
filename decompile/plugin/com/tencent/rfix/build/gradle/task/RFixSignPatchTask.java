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
import org.gradle.api.tasks.TaskAction;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.io.File;
import com.android.builder.model.SigningConfig;
import com.tencent.rfix.build.gradle.feature.RFixPatchSigner;

// class: com/tencent/rfix/build/gradle/task/RFixSignPatchTask
public class RFixSignPatchTask implements GroovyObject {
    final private static Object TAG;
    private SigningConfig signingConfig;
    private File inputFile;
    private File outputFile;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixSignPatchTask() {
        CallSite[] siteArr0 = RFixSignPatchTask.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object object = siteArr0[0].callGetProperty(RFixPatchPlugin.class);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object, RFixSignPatchTask.class, this, (String)"group");
    }

    @TaskAction
    public void signPatch() {
        CallSite[] siteArr0 = RFixSignPatchTask.$getCallSiteArray();
        siteArr0[1].call(siteArr0[2].callGroovyObjectGetProperty(this), new GStringImpl(new Object[]{RFixSignPatchTask.TAG}, new String[]{"", ": sign RFix patch..."}));
        if (RFixSignPatchTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 176;
            if (this.initSignParams() ? 0 : 1 != 0) {
                siteArr0[8].call(siteArr0[9].callGroovyObjectGetProperty(this), siteArr0[10].call(new GStringImpl(new Object[]{RFixSignPatchTask.TAG}, new String[]{"", ": usage: ./gradlew :app:RFixSignPatch -PstoreFile=xxx -PstorePassword=xxx"}), " -PkeyPassword=xxx -PkeyAlias=xxx -Pinput=xxx -Poutput=xxx"));
                throw (Throwable)siteArr0[11].callConstructor(RuntimeException.class, "sign RFix patch fail, params not valid!");
            }
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].callCurrent(this)) ? 0 : 1 != 0) {
                siteArr0[4].call(siteArr0[5].callGroovyObjectGetProperty(this), siteArr0[6].call(new GStringImpl(new Object[]{RFixSignPatchTask.TAG}, new String[]{"", ": usage: ./gradlew :app:RFixSignPatch -PstoreFile=xxx -PstorePassword=xxx"}), " -PkeyPassword=xxx -PkeyAlias=xxx -Pinput=xxx -Poutput=xxx"));
                throw (Throwable)siteArr0[7].callConstructor(RuntimeException.class, "sign RFix patch fail, params not valid!");
            }
            else {
            }
        }
        siteArr0[12].call(siteArr0[13].callGroovyObjectGetProperty(this), new GStringImpl(new Object[]{RFixSignPatchTask.TAG, this.inputFile}, new String[]{"", ": sign RFix patch inputFile=", ""}));
        RFixPatchSigner patchSigner = (RFixPatchSigner)ScriptBytecodeAdapter.castToType(siteArr0[14].callConstructor(RFixPatchSigner.class, this.inputFile, this.outputFile, this.signingConfig), RFixPatchSigner.class);
        siteArr0[15].call(patchSigner);
        siteArr0[16].call(siteArr0[17].callGroovyObjectGetProperty(this), new GStringImpl(new Object[]{RFixSignPatchTask.TAG, this.outputFile}, new String[]{"", ": sign RFix patch success. outputFile=", ""}));
    }

    private boolean initSignParams() {
        CallSite[] siteArr0 = RFixSignPatchTask.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[18].call(siteArr0[19].callGroovyObjectGetProperty(this), "storeFile")) ? 0 : 1 != 0) {
            siteArr0[20].call(siteArr0[21].callGroovyObjectGetProperty(this), new GStringImpl(new Object[]{RFixSignPatchTask.TAG}, new String[]{"", ": has not set storeFile!"}));
            return false;
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[22].call(siteArr0[23].callGroovyObjectGetProperty(this), "storePassword")) ? 0 : 1 != 0) {
                siteArr0[24].call(siteArr0[25].callGroovyObjectGetProperty(this), new GStringImpl(new Object[]{RFixSignPatchTask.TAG}, new String[]{"", ": has not set storePassword!"}));
                return false;
            }
            else {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[26].call(siteArr0[27].callGroovyObjectGetProperty(this), "keyPassword")) ? 0 : 1 != 0) {
                    siteArr0[28].call(siteArr0[29].callGroovyObjectGetProperty(this), new GStringImpl(new Object[]{RFixSignPatchTask.TAG}, new String[]{"", ": has not set storePassword!"}));
                    return false;
                }
                else {
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[30].call(siteArr0[31].callGroovyObjectGetProperty(this), "keyAlias")) ? 0 : 1 != 0) {
                        siteArr0[32].call(siteArr0[33].callGroovyObjectGetProperty(this), new GStringImpl(new Object[]{RFixSignPatchTask.TAG}, new String[]{"", ": has not set keyAlias!"}));
                        return false;
                    }
                    else {
                        if (DefaultTypeTransformation.booleanUnbox(siteArr0[34].call(siteArr0[35].callGroovyObjectGetProperty(this), "input")) ? 0 : 1 != 0) {
                            siteArr0[36].call(siteArr0[37].callGroovyObjectGetProperty(this), new GStringImpl(new Object[]{RFixSignPatchTask.TAG}, new String[]{"", ": has not set keyAlias!"}));
                            return false;
                        }
                        else {
                            if (DefaultTypeTransformation.booleanUnbox(siteArr0[38].call(siteArr0[39].callGroovyObjectGetProperty(this), "output")) ? 0 : 1 != 0) {
                                siteArr0[40].call(siteArr0[41].callGroovyObjectGetProperty(this), new GStringImpl(new Object[]{RFixSignPatchTask.TAG}, new String[]{"", ": has not set keyAlias!"}));
                                return false;
                            }
                            else {
                                Object storeFile = siteArr0[42].call(siteArr0[43].callGroovyObjectGetProperty(this), "storeFile");
                                Object storePassword = siteArr0[44].call(siteArr0[45].callGroovyObjectGetProperty(this), "storePassword");
                                Object keyPassword = siteArr0[46].call(siteArr0[47].callGroovyObjectGetProperty(this), "keyPassword");
                                Object keyAlias = siteArr0[48].call(siteArr0[49].callGroovyObjectGetProperty(this), "keyAlias");
                                Object object = siteArr0[50].callConstructor(DefaultSigningConfig.class, "patch");
                                (SigningConfig)ScriptBytecodeAdapter.castToType(object, SigningConfig.class).signingConfig = this;
                                Object objectVar1 = siteArr0[51].callConstructor(File.class, storeFile);
                                ScriptBytecodeAdapter.setProperty(objectVar1, null, this.signingConfig, (String)"storeFile");
                                ScriptBytecodeAdapter.setProperty(storePassword, null, this.signingConfig, (String)"storePassword");
                                ScriptBytecodeAdapter.setProperty(keyPassword, null, this.signingConfig, (String)"keyPassword");
                                ScriptBytecodeAdapter.setProperty(keyAlias, null, this.signingConfig, (String)"keyAlias");
                                Object input = siteArr0[52].call(siteArr0[53].callGroovyObjectGetProperty(this), "input");
                                Object objectVar5 = siteArr0[54].callConstructor(File.class, input);
                                (File)ScriptBytecodeAdapter.castToType(objectVar5, File.class).inputFile = this;
                                Object output = siteArr0[55].call(siteArr0[56].callGroovyObjectGetProperty(this), "output");
                                Object objectVar6 = siteArr0[57].callConstructor(File.class, output);
                                (File)ScriptBytecodeAdapter.castToType(objectVar6, File.class).outputFile = this;
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixSignPatchTask.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixSignPatchTask.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixSignPatchTask.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixSignPatchTask.$staticClassInfo.getMetaClass();
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

    static  {
        String str0 = "RFixSignPatchTask";
        RFixSignPatchTask.TAG = str0;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "GROUP";
        stringArr0[1] = "error";
        stringArr0[2] = "logger";
        stringArr0[3] = "initSignParams";
        stringArr0[4] = "error";
        stringArr0[5] = "logger";
        stringArr0[6] = "plus";
        stringArr0[7] = "<$constructor$>";
        stringArr0[8] = "error";
        stringArr0[9] = "logger";
        stringArr0[10] = "plus";
        stringArr0[11] = "<$constructor$>";
        stringArr0[12] = "error";
        stringArr0[13] = "logger";
        stringArr0[14] = "<$constructor$>";
        stringArr0[15] = "signPatch";
        stringArr0[16] = "error";
        stringArr0[17] = "logger";
        stringArr0[18] = "hasProperty";
        stringArr0[19] = "project";
        stringArr0[20] = "error";
        stringArr0[21] = "logger";
        stringArr0[22] = "hasProperty";
        stringArr0[23] = "project";
        stringArr0[24] = "error";
        stringArr0[25] = "logger";
        stringArr0[26] = "hasProperty";
        stringArr0[27] = "project";
        stringArr0[28] = "error";
        stringArr0[29] = "logger";
        stringArr0[30] = "hasProperty";
        stringArr0[31] = "project";
        stringArr0[32] = "error";
        stringArr0[33] = "logger";
        stringArr0[34] = "hasProperty";
        stringArr0[35] = "project";
        stringArr0[36] = "error";
        stringArr0[37] = "logger";
        stringArr0[38] = "hasProperty";
        stringArr0[39] = "project";
        stringArr0[40] = "error";
        stringArr0[41] = "logger";
        stringArr0[42] = "getProperty";
        stringArr0[43] = "project";
        stringArr0[44] = "getProperty";
        stringArr0[45] = "project";
        stringArr0[46] = "getProperty";
        stringArr0[47] = "project";
        stringArr0[48] = "getProperty";
        stringArr0[49] = "project";
        stringArr0[50] = "<$constructor$>";
        stringArr0[51] = "<$constructor$>";
        stringArr0[52] = "getProperty";
        stringArr0[53] = "project";
        stringArr0[54] = "<$constructor$>";
        stringArr0[55] = "getProperty";
        stringArr0[56] = "project";
        stringArr0[57] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixSignPatchTask.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixSignPatchTask.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixSignPatchTask.$callSiteArray != null ? RFixSignPatchTask.$createCallSiteArray() : (CallSiteArray)RFixSignPatchTask.$callSiteArray.get();
        RFixSignPatchTask.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
