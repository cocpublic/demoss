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
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.util.Map;
import java.io.File;
import com.tencent.tinker.build.apkparser.AndroidParser;

// class: com/tencent/tinker/build/gradle/extension/TinkerPackageConfigExtension
public class TinkerPackageConfigExtension implements GroovyObject {
    final private static String GLOBAL_PACKAGE_CONFIG;
    private Map<String, Map<String, String>> fields;
    private Project project;
    private AndroidParser androidManifest;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerPackageConfigExtension(Object project) {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Map map = ScriptBytecodeAdapter.createMap(new Object[]{});
        map.fields = this;
        (Project)ScriptBytecodeAdapter.castToType(project, Project.class).project = this;
    }

    public void configField(String name, String value) {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        if (TinkerPackageConfigExtension.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            this.configApkSpecField(TinkerPackageConfigExtension.GLOBAL_PACKAGE_CONFIG, name, value);
            v_7 = null;
        }
        else {
            siteArr0[0].callCurrent(this, TinkerPackageConfigExtension.GLOBAL_PACKAGE_CONFIG, name, value);
        }
    }

    public void configApkSpecField(String apkName, String name, String value) {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        Object pkgFieldMap = siteArr0[1].call(this.fields, apkName);
        if (ScriptBytecodeAdapter.compareEqual(pkgFieldMap, null)) {
            Map map = ScriptBytecodeAdapter.createMap(new Object[]{});
            siteArr0[2].call(this.fields, apkName, map);
        }
        siteArr0[3].call(map, name, value);
    }

    public Map<String, String> getFields() {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        if (TinkerPackageConfigExtension.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 44;
            return this.getApkSpecFields(TinkerPackageConfigExtension.GLOBAL_PACKAGE_CONFIG);
        }
        else {
            return (Map)ScriptBytecodeAdapter.castToType(siteArr0[4].callCurrent(this, TinkerPackageConfigExtension.GLOBAL_PACKAGE_CONFIG), Map.class);
        }
    }

    public Map<String, String> getApkSpecFields(String apkName) {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        Object result = siteArr0[5].call(this.fields, apkName);
        return (Map)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareNotEqual(result, null) ? siteArr0[6].call(Collections.class) : result, Map.class);
    }

    private void createApkMetaFile() {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(this.androidManifest, null)) {
            File oldPakFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[7].callConstructor(File.class, siteArr0[8].callGetProperty(siteArr0[9].callGetProperty(this.project))), File.class);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[10].call(oldPakFile)) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[11].callConstructor(GradleException.class, siteArr0[12].call(String.class, "old apk file %s is not exist, you can set the value directly!", oldPakFile));
            }
            else {
                Object object = siteArr0[13].call(AndroidParser.class, oldPakFile);
                (AndroidParser)ScriptBytecodeAdapter.castToType(object, AndroidParser.class).androidManifest = this;
            }
        }
    }

    public String getVersionCodeFromOldAPk() {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        if (TinkerPackageConfigExtension.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 33;
            this.createApkMetaFile();
            v_4 = null;
        }
        else {
            siteArr0[14].callCurrent(this);
        }
        return (String)ShortTypeHandling.castToString(siteArr0[15].callGetProperty(siteArr0[16].callGetProperty(this.androidManifest)));
    }

    public String getVersionCodeFromApk(File apkPath) {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[17].callGetProperty(siteArr0[18].callGetProperty(siteArr0[19].call(AndroidParser.class, apkPath))));
    }

    public String getVersionNameFromOldAPk() {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        if (TinkerPackageConfigExtension.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 33;
            this.createApkMetaFile();
            v_4 = null;
        }
        else {
            siteArr0[20].callCurrent(this);
        }
        return (String)ShortTypeHandling.castToString(siteArr0[21].callGetProperty(siteArr0[22].callGetProperty(this.androidManifest)));
    }

    public String getVersionNameFromApk(File apkPath) {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[23].callGetProperty(siteArr0[24].callGetProperty(siteArr0[25].call(AndroidParser.class, apkPath))));
    }

    public String getMinSdkVersionFromOldAPk() {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        if (TinkerPackageConfigExtension.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 33;
            this.createApkMetaFile();
            v_4 = null;
        }
        else {
            siteArr0[26].callCurrent(this);
        }
        return (String)ShortTypeHandling.castToString(siteArr0[27].callGetProperty(siteArr0[28].callGetProperty(this.androidManifest)));
    }

    public String getMinSdkVersionFromApk(File apkPath) {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[29].callGetProperty(siteArr0[30].callGetProperty(siteArr0[31].call(AndroidParser.class, apkPath))));
    }

    public String getMetaDataFromOldApk(String name) {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        if (TinkerPackageConfigExtension.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 33;
            this.createApkMetaFile();
            v_4 = null;
        }
        else {
            siteArr0[32].callCurrent(this);
            goto 40;
        }
        String value = (String)ShortTypeHandling.castToString(siteArr0[33].call(siteArr0[34].callGetProperty(this.androidManifest), name));
        if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            throw (Throwable)siteArr0[35].callConstructor(GradleException.class, new GStringImpl(new Object[]{name}, new String[]{"can't find meta data ", " from the old apk manifest file!"}));
        }
        else {
            return value;
        }
    }

    public String getMetaDataFromApk(File apkPath, String name) {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        String value = (String)ShortTypeHandling.castToString(siteArr0[36].call(siteArr0[37].callGetProperty(siteArr0[38].call(AndroidParser.class, apkPath)), name));
        if (ScriptBytecodeAdapter.compareEqual(value, null)) {
            throw (Throwable)siteArr0[39].callConstructor(GradleException.class, new GStringImpl(new Object[]{name, siteArr0[40].call(apkPath)}, new String[]{"can't find meta data ", " from the manifest file in [", "]!"}));
        }
        else {
            return value;
        }
    }

    public String toString() {
        CallSite[] siteArr0 = TinkerPackageConfigExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[41].call(new GStringImpl(new Object[]{this.fields}, new String[]{"| fields = ", "
        "})));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerPackageConfigExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerPackageConfigExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerPackageConfigExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerPackageConfigExtension.$staticClassInfo.getMetaClass();
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

    public /* synthetic */ String super$1$toString() {
        return this.toString();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "configApkSpecField";
        stringArr0[1] = "get";
        stringArr0[2] = "put";
        stringArr0[3] = "put";
        stringArr0[4] = "getApkSpecFields";
        stringArr0[5] = "get";
        stringArr0[6] = "emptyMap";
        stringArr0[7] = "<$constructor$>";
        stringArr0[8] = "oldApk";
        stringArr0[9] = "tinkerPatch";
        stringArr0[10] = "exists";
        stringArr0[11] = "<$constructor$>";
        stringArr0[12] = "format";
        stringArr0[13] = "getAndroidManifest";
        stringArr0[14] = "createApkMetaFile";
        stringArr0[15] = "versionCode";
        stringArr0[16] = "apkMeta";
        stringArr0[17] = "versionCode";
        stringArr0[18] = "apkMeta";
        stringArr0[19] = "getAndroidManifest";
        stringArr0[20] = "createApkMetaFile";
        stringArr0[21] = "versionName";
        stringArr0[22] = "apkMeta";
        stringArr0[23] = "versionName";
        stringArr0[24] = "apkMeta";
        stringArr0[25] = "getAndroidManifest";
        stringArr0[26] = "createApkMetaFile";
        stringArr0[27] = "minSdkVersion";
        stringArr0[28] = "apkMeta";
        stringArr0[29] = "minSdkVersion";
        stringArr0[30] = "apkMeta";
        stringArr0[31] = "getAndroidManifest";
        stringArr0[32] = "createApkMetaFile";
        stringArr0[33] = "get";
        stringArr0[34] = "metaDatas";
        stringArr0[35] = "<$constructor$>";
        stringArr0[36] = "get";
        stringArr0[37] = "metaDatas";
        stringArr0[38] = "getAndroidManifest";
        stringArr0[39] = "<$constructor$>";
        stringArr0[40] = "getAbsolutePath";
        stringArr0[41] = "stripMargin";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerPackageConfigExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerPackageConfigExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerPackageConfigExtension.$callSiteArray != null ? TinkerPackageConfigExtension.$createCallSiteArray() : (CallSiteArray)TinkerPackageConfigExtension.$callSiteArray.get();
        TinkerPackageConfigExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
