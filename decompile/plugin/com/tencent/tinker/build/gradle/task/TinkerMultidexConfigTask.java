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
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

// class: com/tencent/tinker/build/gradle/task/TinkerMultidexConfigTask
public class TinkerMultidexConfigTask implements GroovyObject {
    final private static String MULTIDEX_CONFIG_SETTINGS;
    @Internal
    private Object applicationVariant;
    @Input
    @Optional
    private Object multiDexKeepProguard;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerMultidexConfigTask() {
        CallSite[] siteArr0 = TinkerMultidexConfigTask.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        String str0 = "tinker";
        ScriptBytecodeAdapter.setGroovyObjectProperty(str0, TinkerMultidexConfigTask.class, this, (String)"group");
    }

    @TaskAction
    public Object updateTinkerProguardConfig() {
        Object line;
        CallSite[] siteArr0 = TinkerMultidexConfigTask.$getCallSiteArray();
        File file = (File)ScriptBytecodeAdapter.castToType(siteArr0[0].call(siteArr0[1].callGroovyObjectGetProperty(this), siteArr0[2].call(TinkerBuildPath.class, siteArr0[3].callGroovyObjectGetProperty(this))), File.class);
        siteArr0[4].call(siteArr0[5].callGetProperty(siteArr0[6].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{file}, new String[]{"try update tinker multidex keep proguard file with ", ""}));
        siteArr0[7].call(siteArr0[8].call(file));
        StringBuffer lines = (StringBuffer)ScriptBytecodeAdapter.castToType(siteArr0[9].callConstructor(StringBuffer.class), StringBuffer.class);
        siteArr0[10].call(siteArr0[11].call(siteArr0[12].call(siteArr0[13].call(lines, "
"), "#tinker multidex keep patterns:
"), TinkerMultidexConfigTask.MULTIDEX_CONFIG_SETTINGS), "
");
        siteArr0[14].call(siteArr0[15].call(lines, siteArr0[16].call(siteArr0[17].call("-keep class com.tencent.tinker.loader.TinkerTestAndroidNClassLoader {
", "    <init>(...);
"), "}
")), "
");
        siteArr0[18].call(lines, "#your dex.loader patterns here
");
        Iterable loader = (Iterable)ScriptBytecodeAdapter.castToType(siteArr0[19].callGetProperty(siteArr0[20].callGetProperty(siteArr0[21].callGetProperty(siteArr0[22].callGetProperty(siteArr0[23].callGroovyObjectGetProperty(this))))), Iterable.class);
        Object pattern = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[24].call(loader), Iterator.class);
        while (iterator.hasNext()) {
            String str1 = (String)ShortTypeHandling.castToString(iterator.next());
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[25].call(str1, "*"))) {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[26].call(str1, "**")) ? 0 : 1 != 0) {
                    v_174 = siteArr0[27].call(str1, "*");
                    str1 = (String)ShortTypeHandling.castToString(siteArr0[27].call(str1, "*"));
                }
            }
            siteArr0[28].call(siteArr0[29].call(lines, siteArr0[30].call(siteArr0[31].call(siteArr0[32].call(siteArr0[33].call("-keep class ", str1), " {
"), "    <init>(...);
"), "}
")), "
");
        }
        FileWriter fr = (FileWriter)ScriptBytecodeAdapter.castToType(siteArr0[34].callConstructor(FileWriter.class, siteArr0[35].callGetProperty(file)), FileWriter.class);
        try {
            line = null;
            Iterator iteratorVar1 = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[36].call(lines), Iterator.class);
            while (iteratorVar1.hasNext()) {
                String str2 = (String)ShortTypeHandling.castToString(iteratorVar1.next());
                siteArr0[37].call(fr, str2);
            }
            goto 611;
            siteArr0[38].call(fr);
            goto 643;
        }
        finally {
            Throwable throwable = v_232;
            siteArr0[39].call(fr);
            throw throwable;
        }
        if (ScriptBytecodeAdapter.compareEqual(this.multiDexKeepProguard, null)) {
            siteArr0[40].call(siteArr0[41].callGetProperty(siteArr0[42].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{file}, new String[]{"auto add multidex keep pattern fail, you can only copy ", " to your own multiDex keep proguard file yourself."}));
            return null;
        }
        else {
            FileWriter manifestWriter = (FileWriter)ScriptBytecodeAdapter.castToType(siteArr0[43].callConstructor(FileWriter.class, this.multiDexKeepProguard, Boolean.valueOf(true)), FileWriter.class);
            try {
                line = null;
                Iterator iteratorVar2 = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[44].call(lines), Iterator.class);
                while (iteratorVar2.hasNext()) {
                    String str3 = (String)ShortTypeHandling.castToString(iteratorVar2.next());
                    siteArr0[45].call(manifestWriter, str3);
                }
                Object object = null;
                siteArr0[46].call(manifestWriter);
                return object;
            }
            finally {
                Throwable throwableVar1 = v_301;
                siteArr0[48].call(manifestWriter);
                throw throwableVar1;
            }
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerMultidexConfigTask.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerMultidexConfigTask.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerMultidexConfigTask.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerMultidexConfigTask.$staticClassInfo.getMetaClass();
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
    public static String getMULTIDEX_CONFIG_SETTINGS() {
        return TinkerMultidexConfigTask.MULTIDEX_CONFIG_SETTINGS;
    }

    @Generated
    public Object getApplicationVariant() {
        return this.applicationVariant;
    }

    @Generated
    public void setApplicationVariant(Object object) {
        this.applicationVariant = object;
    }

    @Generated
    public Object getMultiDexKeepProguard() {
        return this.multiDexKeepProguard;
    }

    @Generated
    public void setMultiDexKeepProguard(Object object) {
        this.multiDexKeepProguard = object;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "file";
        stringArr0[1] = "project";
        stringArr0[2] = "getMultidexConfigPath";
        stringArr0[3] = "project";
        stringArr0[4] = "error";
        stringArr0[5] = "logger";
        stringArr0[6] = "project";
        stringArr0[7] = "mkdirs";
        stringArr0[8] = "getParentFile";
        stringArr0[9] = "<$constructor$>";
        stringArr0[10] = "append";
        stringArr0[11] = "append";
        stringArr0[12] = "append";
        stringArr0[13] = "append";
        stringArr0[14] = "append";
        stringArr0[15] = "append";
        stringArr0[16] = "plus";
        stringArr0[17] = "plus";
        stringArr0[18] = "append";
        stringArr0[19] = "loader";
        stringArr0[20] = "dex";
        stringArr0[21] = "tinkerPatch";
        stringArr0[22] = "extensions";
        stringArr0[23] = "project";
        stringArr0[24] = "iterator";
        stringArr0[25] = "endsWith";
        stringArr0[26] = "endsWith";
        stringArr0[27] = "plus";
        stringArr0[28] = "append";
        stringArr0[29] = "append";
        stringArr0[30] = "plus";
        stringArr0[31] = "plus";
        stringArr0[32] = "plus";
        stringArr0[33] = "plus";
        stringArr0[34] = "<$constructor$>";
        stringArr0[35] = "path";
        stringArr0[36] = "iterator";
        stringArr0[37] = "write";
        stringArr0[38] = "close";
        stringArr0[39] = "close";
        stringArr0[40] = "error";
        stringArr0[41] = "logger";
        stringArr0[42] = "project";
        stringArr0[43] = "<$constructor$>";
        stringArr0[44] = "iterator";
        stringArr0[45] = "write";
        stringArr0[46] = "close";
        stringArr0[47] = "close";
        stringArr0[48] = "close";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerMultidexConfigTask.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerMultidexConfigTask.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerMultidexConfigTask.$callSiteArray != null ? TinkerMultidexConfigTask.$createCallSiteArray() : (CallSiteArray)TinkerMultidexConfigTask.$callSiteArray.get();
        TinkerMultidexConfigTask.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
