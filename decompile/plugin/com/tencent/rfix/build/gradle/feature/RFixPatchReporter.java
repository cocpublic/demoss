/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/feature;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.wrappers.Wrapper;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.util.Map;
import java.util.Iterator;
import java.net.URL;
import java.net.HttpURLConnection;

// class: com/tencent/rfix/build/gradle/feature/RFixPatchReporter
public class RFixPatchReporter implements GroovyObject {
    final private static Object TAG;
    final private static String KEY_ATTA_ID;
    final private static String KEY_ATTA_TOKEN;
    final private static String KEY_APP_NAME;
    final private static String KEY_APP_BUNDLE;
    final private static String KEY_APP_VERSION;
    final private static String KEY_PLATFORM_TYPE;
    final private static String KEY_PATCH_TYPE;
    final private static String KEY_SDK_VERSION;
    final private static String KEY_EVENT_NAME;
    final private static String KEY_EVENT_SUCCESS;
    final private static String KEY_EVENT_CODE;
    final private static String KEY_EVENT_TIME_COST;
    final private static String KEY_EXT1;
    final private static String KEY_EXT2;
    final private static String KEY_EXT3;
    final private static String EVENT_NAME_BUILD_PATCH;
    final private static String ATTA_URL;
    final private static String ATTA_ID;
    final private static String ATTA_TOKEN;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public RFixPatchReporter() {
        CallSite[] siteArr0 = RFixPatchReporter.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public static void reportBuildPatchResult(String appName, String appBundle, String appVersion, String patchType, String sdkVersion, boolean success, int resultCode, long timeCost, String timeCost, String patchErrorMessage) {
        CallSite[] siteArr0 = RFixPatchReporter.$getCallSiteArray();
        siteArr0[0].callStatic(RFixPatchReporter.class, siteArr0[1].call(new GStringImpl(new Object[]{RFixPatchReporter.TAG, appBundle, appVersion}, new String[]{"", ": reportBuildPatchResult appBundle=", " appVersion=", ""}), new GStringImpl(new Object[]{patchType, Boolean.valueOf(success), Integer.valueOf(resultCode), Long.valueOf(timeCost)}, new String[]{" patchType=", " success=", " resultCode=", " timeCost=", ""})));
        Map params = (Map)ScriptBytecodeAdapter.castToType(siteArr0[2].callConstructor(HashMap.class), Map.class);
        siteArr0[3].call(params, RFixPatchReporter.KEY_APP_NAME, appName);
        siteArr0[4].call(params, RFixPatchReporter.KEY_APP_BUNDLE, appBundle);
        siteArr0[5].call(params, RFixPatchReporter.KEY_APP_VERSION, appVersion);
        siteArr0[6].call(params, RFixPatchReporter.KEY_PLATFORM_TYPE, "Android");
        siteArr0[7].call(params, RFixPatchReporter.KEY_PATCH_TYPE, patchType);
        siteArr0[8].call(params, RFixPatchReporter.KEY_SDK_VERSION, sdkVersion);
        siteArr0[9].call(params, RFixPatchReporter.KEY_EVENT_NAME, RFixPatchReporter.EVENT_NAME_BUILD_PATCH);
        siteArr0[10].call(params, RFixPatchReporter.KEY_EVENT_SUCCESS, siteArr0[11].call(String.class, Boolean.valueOf(success)));
        siteArr0[12].call(params, RFixPatchReporter.KEY_EVENT_CODE, siteArr0[13].call(String.class, Integer.valueOf(resultCode)));
        siteArr0[14].call(params, RFixPatchReporter.KEY_EVENT_TIME_COST, siteArr0[15].call(String.class, Long.valueOf(timeCost)));
        siteArr0[16].call(params, RFixPatchReporter.KEY_EXT1, patchErrorMessage);
        siteArr0[17].call(params, RFixPatchReporter.KEY_EXT2, patchErrorCause);
        siteArr0[18].callStatic(RFixPatchReporter.class, params);
    }

    private static void reportToATTA(Map<String, String> params) {
        CallSite[] siteArr0 = RFixPatchReporter.$getCallSiteArray();
        siteArr0[19].call(params, RFixPatchReporter.KEY_ATTA_ID, RFixPatchReporter.ATTA_ID);
        siteArr0[20].call(params, RFixPatchReporter.KEY_ATTA_TOKEN, RFixPatchReporter.ATTA_TOKEN);
        StringBuilder builder = (StringBuilder)ScriptBytecodeAdapter.castToType(siteArr0[21].callConstructor(StringBuilder.class), StringBuilder.class);
        int firstParams = 1;
        Object key = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[22].call(siteArr0[23].call(params)), Iterator.class);
        while (iterator.hasNext()) {
            String str0 = (String)ShortTypeHandling.castToString(iterator.next());
            String value = (String)ShortTypeHandling.castToString(siteArr0[24].call(params, str0));
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[25].call(TextUtils.class, value))) {
                continue;;
            }
            else {
                if (firstParams == 0 ? 0 : 1 != 0) {
                    siteArr0[26].call(builder, "&");
                }
                siteArr0[27].call(siteArr0[28].call(siteArr0[29].call(builder, str0), "="), siteArr0[30].callStatic(RFixPatchReporter.class, value));
                int i0 = 0;
                firstParams = i0;
                continue;;
            }
        }
        Object connection = null;
        try {
            try {
                URL requestURL = (URL)ScriptBytecodeAdapter.castToType(siteArr0[31].callConstructor(URL.class, RFixPatchReporter.ATTA_URL), URL.class);
                connection = (HttpURLConnection)ScriptBytecodeAdapter.castToType(siteArr0[32].call(requestURL), HttpURLConnection.class);
                siteArr0[33].call(connection, Integer.valueOf(5000));
                siteArr0[34].call(connection, Integer.valueOf(5000));
                siteArr0[35].call(connection, "Content-Type", "application/x-www-form-urlencoded");
                siteArr0[36].call(connection, "POST");
                siteArr0[37].call(connection, Boolean.valueOf(true));
                byte[] body = (byte[])ScriptBytecodeAdapter.castToType(siteArr0[38].call(siteArr0[39].call(builder), siteArr0[40].callGetProperty(StandardCharsets.class)), byte[].class);
                siteArr0[41].call(siteArr0[42].call(connection), body);
                int responseCode = DefaultTypeTransformation.intUnbox(siteArr0[43].call(connection));
                if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && RFixPatchReporter.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                    goto 588;
                    if (responseCode != 200 ? 0 : 1 != 0) {
                        siteArr0[46].callStatic(RFixPatchReporter.class, RFixPatchReporter.TAG, siteArr0[47].call("reportToATTA failed! responseCode=", Integer.valueOf(responseCode)));
                    }
                }
                else {
                    if (responseCode != 200 ? 0 : 1 != 0) {
                        siteArr0[44].callStatic(RFixPatchReporter.class, RFixPatchReporter.TAG, siteArr0[45].call("reportToATTA failed! responseCode=", Integer.valueOf(responseCode)));
                    }
                }
            }
            catch (Exception e) {
                siteArr0[48].call(e);
            }
            siteArr0[49].call(connection);
            return;
        }
        finally {
            Throwable throwable = v_187;
            siteArr0[50].call(connection);
            throw throwable;
        }
    }

    private static String encodeValue(String value) {
        CallSite[] siteArr0 = RFixPatchReporter.$getCallSiteArray();
        String encodeValue = "";
        try {
            try {
                char replaceChar = DefaultTypeTransformation.charUnbox("$");
                if (ScriptBytecodeAdapter.compareGreaterThanEqual(siteArr0[51].call(value, ScriptBytecodeAdapter.createPojoWrapper(Integer.valueOf(replaceChar), Integer.TYPE)), Integer.valueOf(0))) {
                    StringBuilder builder = (StringBuilder)ScriptBytecodeAdapter.castToType(siteArr0[52].callConstructor(StringBuilder.class), StringBuilder.class);
                    int i;
                    char c;
                    if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && RFixPatchReporter.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                        goto 236;
                        v_33 = 0;
                        i = 0;
                        while (ScriptBytecodeAdapter.compareLessThan(Integer.valueOf(i), siteArr0[58].call(value))) {
                            c = DefaultTypeTransformation.charUnbox(siteArr0[59].call(value, Integer.valueOf(i)));
                            if (c == replaceChar ? 0 : 1 != 0) {
                                siteArr0[60].call(builder, "\");
                            }
                            siteArr0[61].call(builder, Character.valueOf(c));
                            int i1 = i;
                            v_70 = v_68 + 1;
                            i = v_68 + 1;
                        }
                    }
                    else {
                        v_82 = 0;
                        i = 0;
                        while (ScriptBytecodeAdapter.compareLessThan(Integer.valueOf(i), siteArr0[53].call(value))) {
                            c = DefaultTypeTransformation.charUnbox(siteArr0[54].call(value, Integer.valueOf(i)));
                            if (c == replaceChar ? 0 : 1 != 0) {
                                siteArr0[55].call(builder, "\");
                            }
                            siteArr0[56].call(builder, Character.valueOf(c));
                            int i0 = i;
                            v_122 = v_117.call(Integer.valueOf(siteArr0[57]));
                            i = DefaultTypeTransformation.intUnbox(v_117.call(Integer.valueOf(siteArr0[57])));
                        }
                    }
                    Object object = siteArr0[62].call(builder);
                    value = (String)ShortTypeHandling.castToString(object);
                }
                Object objectVar1 = siteArr0[63].call(URLEncoder.class, value, "UTF-8");
                encodeValue = (String)ShortTypeHandling.castToString(objectVar1);
            }
            catch (Exception e) {
                siteArr0[64].call(e);
            }
        }
        finally {
            Throwable throwable = v_19;
            throw throwable;
        }
        return encodeValue;
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixPatchReporter.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixPatchReporter.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixPatchReporter.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixPatchReporter.$staticClassInfo.getMetaClass();
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

    static  {
        String str0 = "RFixPatchReporter";
        RFixPatchReporter.TAG = str0;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "println";
        stringArr0[1] = "plus";
        stringArr0[2] = "<$constructor$>";
        stringArr0[3] = "put";
        stringArr0[4] = "put";
        stringArr0[5] = "put";
        stringArr0[6] = "put";
        stringArr0[7] = "put";
        stringArr0[8] = "put";
        stringArr0[9] = "put";
        stringArr0[10] = "put";
        stringArr0[11] = "valueOf";
        stringArr0[12] = "put";
        stringArr0[13] = "valueOf";
        stringArr0[14] = "put";
        stringArr0[15] = "valueOf";
        stringArr0[16] = "put";
        stringArr0[17] = "put";
        stringArr0[18] = "reportToATTA";
        stringArr0[19] = "put";
        stringArr0[20] = "put";
        stringArr0[21] = "<$constructor$>";
        stringArr0[22] = "iterator";
        stringArr0[23] = "keySet";
        stringArr0[24] = "get";
        stringArr0[25] = "isEmpty";
        stringArr0[26] = "append";
        stringArr0[27] = "append";
        stringArr0[28] = "append";
        stringArr0[29] = "append";
        stringArr0[30] = "encodeValue";
        stringArr0[31] = "<$constructor$>";
        stringArr0[32] = "openConnection";
        stringArr0[33] = "setConnectTimeout";
        stringArr0[34] = "setReadTimeout";
        stringArr0[35] = "setRequestProperty";
        stringArr0[36] = "setRequestMethod";
        stringArr0[37] = "setDoOutput";
        stringArr0[38] = "getBytes";
        stringArr0[39] = "toString";
        stringArr0[40] = "UTF_8";
        stringArr0[41] = "write";
        stringArr0[42] = "getOutputStream";
        stringArr0[43] = "getResponseCode";
        stringArr0[44] = "println";
        stringArr0[45] = "plus";
        stringArr0[46] = "println";
        stringArr0[47] = "plus";
        stringArr0[48] = "printStackTrace";
        stringArr0[49] = "disconnect";
        stringArr0[50] = "disconnect";
        stringArr0[51] = "indexOf";
        stringArr0[52] = "<$constructor$>";
        stringArr0[53] = "length";
        stringArr0[54] = "charAt";
        stringArr0[55] = "append";
        stringArr0[56] = "append";
        stringArr0[57] = "next";
        stringArr0[58] = "length";
        stringArr0[59] = "charAt";
        stringArr0[60] = "append";
        stringArr0[61] = "append";
        stringArr0[62] = "toString";
        stringArr0[63] = "encode";
        stringArr0[64] = "printStackTrace";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixPatchReporter.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixPatchReporter.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixPatchReporter.$callSiteArray != null ? RFixPatchReporter.$createCallSiteArray() : (CallSiteArray)RFixPatchReporter.$callSiteArray.get();
        RFixPatchReporter.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
