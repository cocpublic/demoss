/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/utils;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;

// class: com/tencent/rfix/build/gradle/utils/RFixConstants
public class RFixConstants implements GroovyObject {
    final private static String PATCH_TYPE;
    final private static String PATCH_ID;
    final private static String EFFECT_IMMEDIATE;
    final private static String ENABLE_ASSERT_DEX;
    final private static String ENABLE_ASSERT_LIB;
    final private static String ENABLE_ASSERT_RES;
    final private static String DEFAULT_PATCH_ID;
    final private static String PATCH_ID_PREFIX;
    final private static String PATCH_TYPE_DISABLE;
    final private static String PATCH_TYPE_QFIX;
    final private static String PATCH_TYPE_REDIRECT;
    final private static String PATCH_TYPE_TINKER;
    final private static String FOLDER_QFIX;
    final private static String FOLDER_REDIRECT;
    final private static String FOLDER_TINKER;
    final private static String FOLDER_SUFFIX_ARM64;
    final private static String FOLDER_ASSETS;
    final private static String SO_PATCH_DIR;
    final private static String DEX_FILE_NAME;
    final private static String DEX_CONFIG_NAME;
    final private static String RES_FILE_NAME;
    final private static String RES_CONFIG_NAME;
    final private static String PATCH_SUMMARY_NAME;
    final private static String PATCH_SUMMARY_OUTPUT_NAME;
    final private static String APK_PATCH_NAME;
    final private static String APK_RFIX_UNSIGNED_NAME;
    final private static String APK_RFIX_SIGNED_NAME;
    final private static String META_PACKAGE_NAME;
    final private static String PATCH_DIR;
    final private static String RFIX_TEMP_DIR;
    final private static String RFIX_OUTPUT_DIR;
    final private static String RFIX_SIGN_TEMP_DIR;
    final private static String QFIX_OUTPUT_DIR;
    final private static String REDIRECT_OUTPUT_DIR;
    final private static String TINKER_OUTPUT_DIR;
    final private static String VERIFY_CASE_DEP_NONE;
    final private static String VERIFY_CASE_DEP_BASE;
    final private static String VERIFY_CASE_DEP_PATCH;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public RFixConstants() {
        CallSite[] siteArr0 = RFixConstants.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixConstants.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixConstants.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixConstants.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixConstants.$staticClassInfo.getMetaClass();
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
    public static String getPATCH_TYPE() {
        return RFixConstants.PATCH_TYPE;
    }

    @Generated
    public static String getPATCH_ID() {
        return RFixConstants.PATCH_ID;
    }

    @Generated
    public static String getEFFECT_IMMEDIATE() {
        return RFixConstants.EFFECT_IMMEDIATE;
    }

    @Generated
    public static String getENABLE_ASSERT_DEX() {
        return RFixConstants.ENABLE_ASSERT_DEX;
    }

    @Generated
    public static String getENABLE_ASSERT_LIB() {
        return RFixConstants.ENABLE_ASSERT_LIB;
    }

    @Generated
    public static String getENABLE_ASSERT_RES() {
        return RFixConstants.ENABLE_ASSERT_RES;
    }

    @Generated
    public static String getDEFAULT_PATCH_ID() {
        return RFixConstants.DEFAULT_PATCH_ID;
    }

    @Generated
    public static String getPATCH_ID_PREFIX() {
        return RFixConstants.PATCH_ID_PREFIX;
    }

    @Generated
    public static String getPATCH_TYPE_DISABLE() {
        return RFixConstants.PATCH_TYPE_DISABLE;
    }

    @Generated
    public static String getPATCH_TYPE_QFIX() {
        return RFixConstants.PATCH_TYPE_QFIX;
    }

    @Generated
    public static String getPATCH_TYPE_REDIRECT() {
        return RFixConstants.PATCH_TYPE_REDIRECT;
    }

    @Generated
    public static String getPATCH_TYPE_TINKER() {
        return RFixConstants.PATCH_TYPE_TINKER;
    }

    @Generated
    public static String getFOLDER_QFIX() {
        return RFixConstants.FOLDER_QFIX;
    }

    @Generated
    public static String getFOLDER_REDIRECT() {
        return RFixConstants.FOLDER_REDIRECT;
    }

    @Generated
    public static String getFOLDER_TINKER() {
        return RFixConstants.FOLDER_TINKER;
    }

    @Generated
    public static String getFOLDER_SUFFIX_ARM64() {
        return RFixConstants.FOLDER_SUFFIX_ARM64;
    }

    @Generated
    public static String getFOLDER_ASSETS() {
        return RFixConstants.FOLDER_ASSETS;
    }

    @Generated
    public static String getSO_PATCH_DIR() {
        return RFixConstants.SO_PATCH_DIR;
    }

    @Generated
    public static String getDEX_FILE_NAME() {
        return RFixConstants.DEX_FILE_NAME;
    }

    @Generated
    public static String getDEX_CONFIG_NAME() {
        return RFixConstants.DEX_CONFIG_NAME;
    }

    @Generated
    public static String getRES_FILE_NAME() {
        return RFixConstants.RES_FILE_NAME;
    }

    @Generated
    public static String getRES_CONFIG_NAME() {
        return RFixConstants.RES_CONFIG_NAME;
    }

    @Generated
    public static String getPATCH_SUMMARY_NAME() {
        return RFixConstants.PATCH_SUMMARY_NAME;
    }

    @Generated
    public static String getPATCH_SUMMARY_OUTPUT_NAME() {
        return RFixConstants.PATCH_SUMMARY_OUTPUT_NAME;
    }

    @Generated
    public static String getAPK_PATCH_NAME() {
        return RFixConstants.APK_PATCH_NAME;
    }

    @Generated
    public static String getAPK_RFIX_UNSIGNED_NAME() {
        return RFixConstants.APK_RFIX_UNSIGNED_NAME;
    }

    @Generated
    public static String getAPK_RFIX_SIGNED_NAME() {
        return RFixConstants.APK_RFIX_SIGNED_NAME;
    }

    @Generated
    public static String getMETA_PACKAGE_NAME() {
        return RFixConstants.META_PACKAGE_NAME;
    }

    @Generated
    public static String getPATCH_DIR() {
        return RFixConstants.PATCH_DIR;
    }

    @Generated
    public static String getRFIX_TEMP_DIR() {
        return RFixConstants.RFIX_TEMP_DIR;
    }

    @Generated
    public static String getRFIX_OUTPUT_DIR() {
        return RFixConstants.RFIX_OUTPUT_DIR;
    }

    @Generated
    public static String getRFIX_SIGN_TEMP_DIR() {
        return RFixConstants.RFIX_SIGN_TEMP_DIR;
    }

    @Generated
    public static String getQFIX_OUTPUT_DIR() {
        return RFixConstants.QFIX_OUTPUT_DIR;
    }

    @Generated
    public static String getREDIRECT_OUTPUT_DIR() {
        return RFixConstants.REDIRECT_OUTPUT_DIR;
    }

    @Generated
    public static String getTINKER_OUTPUT_DIR() {
        return RFixConstants.TINKER_OUTPUT_DIR;
    }

    @Generated
    public static String getVERIFY_CASE_DEP_NONE() {
        return RFixConstants.VERIFY_CASE_DEP_NONE;
    }

    @Generated
    public static String getVERIFY_CASE_DEP_BASE() {
        return RFixConstants.VERIFY_CASE_DEP_BASE;
    }

    @Generated
    public static String getVERIFY_CASE_DEP_PATCH() {
        return RFixConstants.VERIFY_CASE_DEP_PATCH;
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        return new CallSiteArray(RFixConstants.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixConstants.$callSiteArray != null ? RFixConstants.$createCallSiteArray() : (CallSiteArray)RFixConstants.$callSiteArray.get();
        RFixConstants.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
