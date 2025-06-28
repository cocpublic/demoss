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

// class: com/tencent/tinker/build/gradle/TinkerBuildPath
public class TinkerBuildPath implements GroovyObject {
    final private static String TINKER_INTERMEDIATES;
    final private static String MULTIDEX_CONFIG_FILE;
    final private static String PROGUARD_CONFIG_FILE;
    final private static String RESOURCE_PUBLIC_XML;
    final private static String RESOURCE_IDX_XML;
    final private static String RESOURCE_VALUES_BACKUP;
    final private static String RESOURCE_PUBLIC_TXT;
    final private static String RESOURCE_TO_COMPILE_PUBLIC_XML;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public TinkerBuildPath() {
        CallSite[] siteArr0 = TinkerBuildPath.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public static String getTinkerIntermediates(Project project) {
        CallSite[] siteArr0 = TinkerBuildPath.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[0].callGetProperty(project), TinkerBuildPath.TINKER_INTERMEDIATES}, new String[]{"", "", ""}));
    }

    public static String getMultidexConfigPath(Project project) {
        CallSite[] siteArr0 = TinkerBuildPath.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[1].callStatic(TinkerBuildPath.class, project), TinkerBuildPath.MULTIDEX_CONFIG_FILE}, new String[]{"", "", ""}));
    }

    public static String getProguardConfigPath(Project project) {
        CallSite[] siteArr0 = TinkerBuildPath.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[2].callStatic(TinkerBuildPath.class, project), TinkerBuildPath.PROGUARD_CONFIG_FILE}, new String[]{"", "", ""}));
    }

    public static String getResourcePublicXml(Project project) {
        CallSite[] siteArr0 = TinkerBuildPath.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[3].callStatic(TinkerBuildPath.class, project), TinkerBuildPath.RESOURCE_PUBLIC_XML}, new String[]{"", "", ""}));
    }

    public static String getResourceIdxXml(Project project) {
        CallSite[] siteArr0 = TinkerBuildPath.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[4].callStatic(TinkerBuildPath.class, project), TinkerBuildPath.RESOURCE_IDX_XML}, new String[]{"", "", ""}));
    }

    public static String getResourceValuesBackup(Project project) {
        CallSite[] siteArr0 = TinkerBuildPath.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[5].callStatic(TinkerBuildPath.class, project), TinkerBuildPath.RESOURCE_VALUES_BACKUP}, new String[]{"", "", ""}));
    }

    public static String getResourcePublicTxt(Project project) {
        CallSite[] siteArr0 = TinkerBuildPath.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[6].callStatic(TinkerBuildPath.class, project), TinkerBuildPath.RESOURCE_PUBLIC_TXT}, new String[]{"", "", ""}));
    }

    public static String getResourceToCompilePublicXml(Project project) {
        CallSite[] siteArr0 = TinkerBuildPath.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[7].callStatic(TinkerBuildPath.class, project), TinkerBuildPath.RESOURCE_TO_COMPILE_PUBLIC_XML}, new String[]{"", "", ""}));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerBuildPath.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerBuildPath.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerBuildPath.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerBuildPath.$staticClassInfo.getMetaClass();
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
        stringArr0[0] = "buildDir";
        stringArr0[1] = "getTinkerIntermediates";
        stringArr0[2] = "getTinkerIntermediates";
        stringArr0[3] = "getTinkerIntermediates";
        stringArr0[4] = "getTinkerIntermediates";
        stringArr0[5] = "getTinkerIntermediates";
        stringArr0[6] = "getTinkerIntermediates";
        stringArr0[7] = "getTinkerIntermediates";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerBuildPath.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerBuildPath.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerBuildPath.$callSiteArray != null ? TinkerBuildPath.$createCallSiteArray() : (CallSiteArray)TinkerBuildPath.$callSiteArray.get();
        TinkerBuildPath.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
