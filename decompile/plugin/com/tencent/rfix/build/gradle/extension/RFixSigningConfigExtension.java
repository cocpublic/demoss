/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/extension;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.reflection.ClassInfo;
import org.gradle.api.Project;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.io.File;
import com.android.builder.model.SigningConfig;

// class: com/tencent/rfix/build/gradle/extension/RFixSigningConfigExtension
public class RFixSigningConfigExtension implements GroovyObject {
    private Project project;
    private File storeFile;
    private String storePassword;
    private String keyAlias;
    private String keyPassword;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;
    private static synthetic Class $class$com$tencent$rfix$build$gradle$extension$RFixSigningConfig;

    public RFixSigningConfigExtension(Project project) {
        CallSite[] siteArr0 = RFixSigningConfigExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        (Project)ScriptBytecodeAdapter.castToType(project, Project.class).project = this;
    }

    public boolean valid() {
        CallSite[] siteArr0 = RFixSigningConfigExtension.$getCallSiteArray();
        if (BytecodeInterface8.isOrigZ() && RFixSigningConfigExtension.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 94;
            if (ScriptBytecodeAdapter.compareNotEqual(this.storeFile, null) && ScriptBytecodeAdapter.compareNotEqual(this.storePassword, null) ? 0 : 1 != 0 && ScriptBytecodeAdapter.compareNotEqual(this.keyAlias, null) ? 0 : 1 != 0 && ScriptBytecodeAdapter.compareNotEqual(this.keyPassword, null)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareNotEqual(this.storeFile, null) && ScriptBytecodeAdapter.compareNotEqual(this.storePassword, null) ? 0 : 1 != 0 && ScriptBytecodeAdapter.compareNotEqual(this.keyAlias, null) ? 0 : 1 != 0 && ScriptBytecodeAdapter.compareNotEqual(this.keyPassword, null)) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public SigningConfig toSigningConfig() {
        CallSite[] siteArr0 = RFixSigningConfigExtension.$getCallSiteArray();
        return (SigningConfig)ScriptBytecodeAdapter.castToType(siteArr0[0].call(siteArr0[1].call(siteArr0[2].call(siteArr0[3].call(siteArr0[4].callConstructor(RFixSigningConfigExtension.$get$$class$com$tencent$rfix$build$gradle$extension$RFixSigningConfig(), "patch"), this.storeFile), this.storePassword), this.keyAlias), this.keyPassword), SigningConfig.class);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixSigningConfigExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixSigningConfigExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixSigningConfigExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixSigningConfigExtension.$staticClassInfo.getMetaClass();
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
    public File getStoreFile() {
        return this.storeFile;
    }

    @Generated
    public void setStoreFile(File file) {
        this.storeFile = file;
    }

    @Generated
    public String getStorePassword() {
        return this.storePassword;
    }

    @Generated
    public void setStorePassword(String str0) {
        this.storePassword = str0;
    }

    @Generated
    public String getKeyAlias() {
        return this.keyAlias;
    }

    @Generated
    public void setKeyAlias(String str0) {
        this.keyAlias = str0;
    }

    @Generated
    public String getKeyPassword() {
        return this.keyPassword;
    }

    @Generated
    public void setKeyPassword(String str0) {
        this.keyPassword = str0;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "setKeyPassword";
        stringArr0[1] = "setKeyAlias";
        stringArr0[2] = "setStorePassword";
        stringArr0[3] = "setStoreFile";
        stringArr0[4] = "<$constructor$>";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixSigningConfigExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixSigningConfigExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixSigningConfigExtension.$callSiteArray != null ? RFixSigningConfigExtension.$createCallSiteArray() : (CallSiteArray)RFixSigningConfigExtension.$callSiteArray.get();
        RFixSigningConfigExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    private static /* synthetic */ Class $get$$class$com$tencent$rfix$build$gradle$extension$RFixSigningConfig() {
        if (RFixSigningConfigExtension.$class$com$tencent$rfix$build$gradle$extension$RFixSigningConfig == null) {
            v_0 = RFixSigningConfigExtension.class$("com.tencent.rfix.build.gradle.extension.RFixSigningConfig");
            RFixSigningConfigExtension.$class$com$tencent$rfix$build$gradle$extension$RFixSigningConfig = RFixSigningConfigExtension.class$("com.tencent.rfix.build.gradle.extension.RFixSigningConfig");
        }
        return RFixSigningConfigExtension.$class$com$tencent$rfix$build$gradle$extension$RFixSigningConfig;
    }

    static /* synthetic */ Class class$(String str0) {
        try {
            return Class.forName(str0);
        }
        catch (ClassNotFoundException var_1_0) {
            throw new NoClassDefFoundError(var_1_0.getMessage());
        }
    }

}
