/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/feature;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;
import com.android.builder.model.SigningConfig;

// class: com/tencent/rfix/build/gradle/feature/RFixPatchSigner
public class RFixPatchSigner implements GroovyObject {
    final private static Object TAG;
    protected File inputFile;
    protected File outputFile;
    protected SigningConfig signingConfig;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixPatchSigner(File inputFile, File outputFile, SigningConfig signingConfig) {
        CallSite[] siteArr0 = RFixPatchSigner.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        (File)ScriptBytecodeAdapter.castToType(inputFile, File.class).inputFile = this;
        (File)ScriptBytecodeAdapter.castToType(outputFile, File.class).outputFile = this;
        (SigningConfig)ScriptBytecodeAdapter.castToType(signingConfig, SigningConfig.class).signingConfig = this;
    }

    public void signPatch() {
        CallSite[] siteArr0 = RFixPatchSigner.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(PatchFileUtils.class, this.inputFile)) ? 0 : 1 != 0) {
        }
        else {
            Object parent = siteArr0[1].call(this.inputFile);
            Object tmpExtractDir = siteArr0[2].callConstructor(File.class, parent, siteArr0[3].callGetProperty(RFixConstants.class));
            Object tmpPatchFile = siteArr0[4].callConstructor(File.class, parent, siteArr0[5].call(siteArr0[6].call(this.inputFile), ".tmp"));
            siteArr0[7].call(FileOperation.class, tmpExtractDir);
            siteArr0[8].call(FileOperation.class, tmpPatchFile);
            siteArr0[9].call(FileOperation.class, siteArr0[10].call(this.inputFile), siteArr0[11].call(tmpExtractDir));
            Object tinkerDirNames = siteArr0[12].call(tmpExtractDir, new RFixPatchSigner$1(this));
            Object tinkerDirName = null;
            Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[13].call(tinkerDirNames), Iterator.class);
            while (iterator.hasNext()) {
                tinkerDirName = iterator.next();
                Object tinkerDir = siteArr0[14].callConstructor(File.class, tmpExtractDir, tinkerDirName);
                siteArr0[15].call(siteArr0[16].call(tinkerDir), new RFixPatchSigner$_signPatch_closure1(this, this));
            }
            siteArr0[17].call(FileOperation.class, tmpExtractDir, tmpPatchFile, null);
            siteArr0[18].callCurrent(this, tmpPatchFile, this.outputFile);
            siteArr0[19].call(FileOperation.class, tmpExtractDir);
            siteArr0[20].call(FileOperation.class, tmpPatchFile);
        }
    }

    protected void signPatch(File input, File output) {
        CallSite[] siteArr0 = RFixPatchSigner.$getCallSiteArray();
        siteArr0[21].callCurrent(this, new GStringImpl(new Object[]{RFixPatchSigner.TAG, siteArr0[22].call(input)}, new String[]{"", ": signPatch input=", ""}));
        ArrayList command = (ArrayList)ScriptBytecodeAdapter.castToType(siteArr0[23].callConstructor(ArrayList.class), ArrayList.class);
        siteArr0[24].call(command, "jarsigner");
        siteArr0[25].call(command, "-keystore");
        siteArr0[26].call(command, siteArr0[27].call(siteArr0[28].callGetProperty(this.signingConfig)));
        siteArr0[29].call(command, "-storepass");
        siteArr0[30].call(command, siteArr0[31].callGetProperty(this.signingConfig));
        siteArr0[32].call(command, "-keypass");
        siteArr0[33].call(command, siteArr0[34].callGetProperty(this.signingConfig));
        siteArr0[35].call(command, "-signedjar");
        siteArr0[36].call(command, siteArr0[37].call(output));
        siteArr0[38].call(command, siteArr0[39].call(input));
        siteArr0[40].call(command, siteArr0[41].callGetProperty(this.signingConfig));
        Process process = (Process)ScriptBytecodeAdapter.castToType(siteArr0[42].call(siteArr0[43].callConstructor(ProcessBuilder.class, command)), Process.class);
        siteArr0[44].call(process);
        siteArr0[45].call(process);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[46].call(output)) ? 0 : 1 != 0) {
            throw (Throwable)siteArr0[47].callConstructor(RuntimeException.class, "sign patch fail!");
        }
        else {
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixPatchSigner.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixPatchSigner.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixPatchSigner.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixPatchSigner.$staticClassInfo.getMetaClass();
        }
    }

    public /* synthetic */ Object this$dist$invoke$1(String name, Object args) {
        CallSite[] siteArr0 = RFixPatchSigner.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(RFixPatchSigner.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[]{}, new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String name, Object value) {
        CallSite[] siteArr0 = RFixPatchSigner.$getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty(value, RFixPatchSigner.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
    }

    public /* synthetic */ Object this$dist$get$1(String name) {
        CallSite[] siteArr0 = RFixPatchSigner.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectProperty(RFixPatchSigner.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
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
        String str0 = "RFixSignPatchTask";
        RFixPatchSigner.TAG = str0;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "isLegalFile";
        stringArr0[1] = "getParentFile";
        stringArr0[2] = "<$constructor$>";
        stringArr0[3] = "RFIX_SIGN_TEMP_DIR";
        stringArr0[4] = "<$constructor$>";
        stringArr0[5] = "plus";
        stringArr0[6] = "getName";
        stringArr0[7] = "deleteDir";
        stringArr0[8] = "deleteFile";
        stringArr0[9] = "unZipAPk";
        stringArr0[10] = "getAbsolutePath";
        stringArr0[11] = "getAbsolutePath";
        stringArr0[12] = "list";
        stringArr0[13] = "iterator";
        stringArr0[14] = "<$constructor$>";
        stringArr0[15] = "each";
        stringArr0[16] = "listFiles";
        stringArr0[17] = "zipInputDir";
        stringArr0[18] = "signPatch";
        stringArr0[19] = "deleteDir";
        stringArr0[20] = "deleteFile";
        stringArr0[21] = "println";
        stringArr0[22] = "getAbsoluteFile";
        stringArr0[23] = "<$constructor$>";
        stringArr0[24] = "add";
        stringArr0[25] = "add";
        stringArr0[26] = "add";
        stringArr0[27] = "getAbsolutePath";
        stringArr0[28] = "storeFile";
        stringArr0[29] = "add";
        stringArr0[30] = "add";
        stringArr0[31] = "storePassword";
        stringArr0[32] = "add";
        stringArr0[33] = "add";
        stringArr0[34] = "keyPassword";
        stringArr0[35] = "add";
        stringArr0[36] = "add";
        stringArr0[37] = "getAbsolutePath";
        stringArr0[38] = "add";
        stringArr0[39] = "getAbsolutePath";
        stringArr0[40] = "add";
        stringArr0[41] = "keyAlias";
        stringArr0[42] = "start";
        stringArr0[43] = "<$constructor$>";
        stringArr0[44] = "waitFor";
        stringArr0[45] = "destroy";
        stringArr0[46] = "exists";
        stringArr0[47] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixPatchSigner.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixPatchSigner.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixPatchSigner.$callSiteArray != null ? RFixPatchSigner.$createCallSiteArray() : (CallSiteArray)RFixPatchSigner.$callSiteArray.get();
        RFixPatchSigner.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/rfix/build/gradle/feature/RFixPatchSigner$_signPatch_closure1
    public final class RFixPatchSigner$_signPatch_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchSigner$_signPatch_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = RFixPatchSigner$_signPatch_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = RFixPatchSigner$_signPatch_closure1.$getCallSiteArray();
            return siteArr0[0].callCurrent(this, it, it);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = RFixPatchSigner$_signPatch_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchSigner$_signPatch_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchSigner$_signPatch_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchSigner$_signPatch_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchSigner$_signPatch_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "signPatch";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchSigner$_signPatch_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchSigner$_signPatch_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchSigner$_signPatch_closure1.$callSiteArray != null ? RFixPatchSigner$_signPatch_closure1.$createCallSiteArray() : (CallSiteArray)RFixPatchSigner$_signPatch_closure1.$callSiteArray.get();
            RFixPatchSigner$_signPatch_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/feature/RFixPatchSigner$_signPatch_closure1
    public final class RFixPatchSigner$_signPatch_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchSigner$_signPatch_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = RFixPatchSigner$_signPatch_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = RFixPatchSigner$_signPatch_closure1.$getCallSiteArray();
            return siteArr0[0].callCurrent(this, it, it);
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = RFixPatchSigner$_signPatch_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchSigner$_signPatch_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchSigner$_signPatch_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchSigner$_signPatch_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchSigner$_signPatch_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "signPatch";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchSigner$_signPatch_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchSigner$_signPatch_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchSigner$_signPatch_closure1.$callSiteArray != null ? RFixPatchSigner$_signPatch_closure1.$createCallSiteArray() : (CallSiteArray)RFixPatchSigner$_signPatch_closure1.$callSiteArray.get();
            RFixPatchSigner$_signPatch_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
