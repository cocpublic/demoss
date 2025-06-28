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
import org.gradle.api.Project;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import com.tencent.rfix.build.gradle.extension.RFixPatchExtension;
import java.util.Map;
import java.util.Iterator;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.Enumeration;
import java.util.Scanner;
import java.io.InputStream;

// class: com/tencent/rfix/build/gradle/feature/TinkerSPIChecker
public class TinkerSPIChecker implements GroovyObject {
    final private static Object TAG;
    final private static String SPI_SERVICES_DIR;
    private Project project;
    private RFixPatchExtension patchExtension;
    private String oldApk;
    private String newApk;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerSPIChecker(Project project, RFixPatchExtension patchExtension, String oldApk, String newApk) {
        CallSite[] siteArr0 = TinkerSPIChecker.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        (Project)ScriptBytecodeAdapter.castToType(project, Project.class).project = this;
        (RFixPatchExtension)ScriptBytecodeAdapter.castToType(patchExtension, RFixPatchExtension.class).patchExtension = this;
        (String)ShortTypeHandling.castToString(oldApk).oldApk = this;
        (String)ShortTypeHandling.castToString(newApk).newApk = this;
    }

    public void check() {
        CallSite[] siteArr0 = TinkerSPIChecker.$getCallSiteArray();
        int hasSPIChanged = false;
        Object var_3_0 = null;
        if (TinkerSPIChecker.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 50;
            Map map = this.getApkSPIInfo(this.oldApk);
            Map mapVar1 = map;
        }
        else {
            Object object = siteArr0[0].callCurrent(this, this.oldApk);
        }
        Object var_6_0 = null;
        boolean bool0;
        if (TinkerSPIChecker.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 111;
            Map mapVar2 = this.getApkSPIInfo(this.newApk);
            Map mapVar3 = mapVar2;
        }
        else {
            Object objectVar1 = siteArr0[1].callCurrent(this, this.newApk);
        }
        Object oldKeysBackup = siteArr0[2].callConstructor(HashSet.class, siteArr0[3].call(object));
        Object newKeysBackup = siteArr0[4].callConstructor(HashSet.class, siteArr0[5].call(objectVar1));
        Object key = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[6].call(siteArr0[7].call(object)), Iterator.class);
        while (iterator.hasNext()) {
            key = iterator.next();
            Object oldValue = siteArr0[8].call(object, key);
            Object newValue = siteArr0[9].call(objectVar1, key);
            if (ScriptBytecodeAdapter.compareNotEqual(oldValue, null) && ScriptBytecodeAdapter.compareNotEqual(newValue, null) ? 0 : 1 != 0) {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[10].call(Arrays.class, siteArr0[11].call(siteArr0[12].call(oldValue)), siteArr0[13].call(siteArr0[14].call(newValue)))) ? 0 : 1 != 0) {
                    siteArr0[15].call(siteArr0[16].callGetProperty(this.project), new GStringImpl(new Object[]{TinkerSPIChecker.TAG, key}, new String[]{"", ": find modified SPI: ", ""}));
                    v_138 = siteArr0[17].call(Boolean.valueOf(hasSPIChanged), Boolean.valueOf(true));
                    bool0 = DefaultTypeTransformation.booleanUnbox(siteArr0[17].call(Boolean.valueOf(hasSPIChanged), Boolean.valueOf(true)));
                }
                siteArr0[18].call(oldKeysBackup, key);
                siteArr0[19].call(newKeysBackup, key);
            }
        }
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[20].call(newKeysBackup)) ? 0 : 1 != 0) {
            siteArr0[21].call(newKeysBackup, new TinkerSPIChecker$_check_closure1(this, this));
            v_176 = siteArr0[22].call(Boolean.valueOf(hasSPIChanged), Boolean.valueOf(true));
            bool0 = DefaultTypeTransformation.booleanUnbox(siteArr0[22].call(Boolean.valueOf(hasSPIChanged), Boolean.valueOf(true)));
        }
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[23].call(oldKeysBackup)) ? 0 : 1 != 0) {
            siteArr0[24].call(oldKeysBackup, new TinkerSPIChecker$_check_closure2(this, this));
            v_200 = siteArr0[25].call(Boolean.valueOf(hasSPIChanged), Boolean.valueOf(true));
            bool0 = DefaultTypeTransformation.booleanUnbox(siteArr0[25].call(Boolean.valueOf(hasSPIChanged), Boolean.valueOf(true)));
        }
        if (hasSPIChanged != 0) {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[26].callGroovyObjectGetProperty(this.patchExtension))) {
                siteArr0[27].call(siteArr0[28].callGetProperty(this.project), siteArr0[29].call(new GStringImpl(new Object[]{TinkerSPIChecker.TAG}, new String[]{"", ": find not support modify in META-INF/services/*."}), " this may cause crash when running! but ignoreWarning is true, skip it."));
            }
            else {
                throw (Throwable)siteArr0[30].callConstructor(TinkerPatchException.class, siteArr0[31].call("Find not support modify in META-INF/services/*.", " This may cause crash when running. Please check applyMapping is taking effect."));
            }
        }
        else {
        }
    }

    private Map<String, List<String>> getApkSPIInfo(String apkPath) {
        CallSite[] siteArr0 = TinkerSPIChecker.$getCallSiteArray();
        Object result = siteArr0[32].callConstructor(HashMap.class);
        Object zipFile = null;
        Object inputStream = null;
        Object scanner = null;
        try {
            try {
                Object object = siteArr0[33].callConstructor(ZipFile.class, apkPath);
                ZipFile file = (ZipFile)ScriptBytecodeAdapter.castToType(object, ZipFile.class);
                Enumeration entries = (Enumeration)ScriptBytecodeAdapter.castToType(siteArr0[34].call(file), Enumeration.class);
                while (DefaultTypeTransformation.booleanUnbox(siteArr0[35].call(entries))) {
                    ZipEntry entry = (ZipEntry)ScriptBytecodeAdapter.castToType(siteArr0[36].call(entries), ZipEntry.class);
                    if (DefaultTypeTransformation.booleanUnbox(siteArr0[37].call(siteArr0[38].callGetProperty(entry), TinkerSPIChecker.SPI_SERVICES_DIR))) {
                        Object list = siteArr0[39].callConstructor(ArrayList.class);
                        siteArr0[40].call(result, siteArr0[41].callGetProperty(entry), list);
                        Object objectVar1 = siteArr0[42].call(file, entry);
                        InputStream stream = (InputStream)ScriptBytecodeAdapter.castToType(objectVar1, InputStream.class);
                        Object objectVar2 = siteArr0[43].callConstructor(Scanner.class, stream);
                        scanner = (Scanner)ScriptBytecodeAdapter.castToType(objectVar2, Scanner.class);
                        while (DefaultTypeTransformation.booleanUnbox(siteArr0[44].call(scanner))) {
                            siteArr0[45].call(list, siteArr0[46].call(scanner));
                        }
                        siteArr0[47].call(stream);
                        siteArr0[48].call(scanner);
                    }
                }
            }
            catch (Exception e) {
                siteArr0[49].call(siteArr0[50].callGetProperty(this.project), new GStringImpl(new Object[]{TinkerSPIChecker.TAG}, new String[]{"", ": getApkSPIInfo fail!"}));
                siteArr0[51].call(e);
            }
            siteArr0[52].call(PatchFileUtils.class, file);
            siteArr0[53].call(PatchFileUtils.class, inputStream);
            siteArr0[54].call(PatchFileUtils.class, scanner);
        }
        finally {
            Throwable throwable = v_34;
            siteArr0[55].call(PatchFileUtils.class, file);
            siteArr0[56].call(PatchFileUtils.class, inputStream);
            siteArr0[57].call(PatchFileUtils.class, scanner);
            throw throwable;
        }
        return (Map)ScriptBytecodeAdapter.castToType(result, Map.class);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerSPIChecker.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerSPIChecker.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerSPIChecker.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerSPIChecker.$staticClassInfo.getMetaClass();
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
        String str0 = "TinkerSPIChecker";
        TinkerSPIChecker.TAG = str0;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "getApkSPIInfo";
        stringArr0[1] = "getApkSPIInfo";
        stringArr0[2] = "<$constructor$>";
        stringArr0[3] = "keySet";
        stringArr0[4] = "<$constructor$>";
        stringArr0[5] = "keySet";
        stringArr0[6] = "iterator";
        stringArr0[7] = "keySet";
        stringArr0[8] = "get";
        stringArr0[9] = "get";
        stringArr0[10] = "equals";
        stringArr0[11] = "toArray";
        stringArr0[12] = "sort";
        stringArr0[13] = "toArray";
        stringArr0[14] = "sort";
        stringArr0[15] = "error";
        stringArr0[16] = "logger";
        stringArr0[17] = "or";
        stringArr0[18] = "remove";
        stringArr0[19] = "remove";
        stringArr0[20] = "isEmpty";
        stringArr0[21] = "forEach";
        stringArr0[22] = "or";
        stringArr0[23] = "isEmpty";
        stringArr0[24] = "forEach";
        stringArr0[25] = "or";
        stringArr0[26] = "ignoreWarning";
        stringArr0[27] = "error";
        stringArr0[28] = "logger";
        stringArr0[29] = "plus";
        stringArr0[30] = "<$constructor$>";
        stringArr0[31] = "plus";
        stringArr0[32] = "<$constructor$>";
        stringArr0[33] = "<$constructor$>";
        stringArr0[34] = "entries";
        stringArr0[35] = "hasMoreElements";
        stringArr0[36] = "nextElement";
        stringArr0[37] = "startsWith";
        stringArr0[38] = "name";
        stringArr0[39] = "<$constructor$>";
        stringArr0[40] = "put";
        stringArr0[41] = "name";
        stringArr0[42] = "getInputStream";
        stringArr0[43] = "<$constructor$>";
        stringArr0[44] = "hasNextLine";
        stringArr0[45] = "add";
        stringArr0[46] = "nextLine";
        stringArr0[47] = "close";
        stringArr0[48] = "close";
        stringArr0[49] = "error";
        stringArr0[50] = "logger";
        stringArr0[51] = "printStackTrace";
        stringArr0[52] = "closeQuietly";
        stringArr0[53] = "closeQuietly";
        stringArr0[54] = "closeQuietly";
        stringArr0[55] = "closeQuietly";
        stringArr0[56] = "closeQuietly";
        stringArr0[57] = "closeQuietly";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerSPIChecker.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerSPIChecker.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerSPIChecker.$callSiteArray != null ? TinkerSPIChecker.$createCallSiteArray() : (CallSiteArray)TinkerSPIChecker.$callSiteArray.get();
        TinkerSPIChecker.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/rfix/build/gradle/feature/TinkerSPIChecker$_check_closure1
    public final class TinkerSPIChecker$_check_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerSPIChecker$_check_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure1.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].callGetProperty(siteArr0[2].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[3].callGetProperty(TinkerSPIChecker.class), it}, new String[]{"", ": find new SPI: ", ""}));
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerSPIChecker$_check_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerSPIChecker$_check_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerSPIChecker$_check_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerSPIChecker$_check_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "error";
            stringArr0[1] = "logger";
            stringArr0[2] = "project";
            stringArr0[3] = "TAG";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerSPIChecker$_check_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerSPIChecker$_check_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerSPIChecker$_check_closure1.$callSiteArray != null ? TinkerSPIChecker$_check_closure1.$createCallSiteArray() : (CallSiteArray)TinkerSPIChecker$_check_closure1.$callSiteArray.get();
            TinkerSPIChecker$_check_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/feature/TinkerSPIChecker$_check_closure1
    public final class TinkerSPIChecker$_check_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerSPIChecker$_check_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure1.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].callGetProperty(siteArr0[2].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[3].callGetProperty(TinkerSPIChecker.class), it}, new String[]{"", ": find new SPI: ", ""}));
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure1.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerSPIChecker$_check_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerSPIChecker$_check_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerSPIChecker$_check_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerSPIChecker$_check_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "error";
            stringArr0[1] = "logger";
            stringArr0[2] = "project";
            stringArr0[3] = "TAG";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerSPIChecker$_check_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerSPIChecker$_check_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerSPIChecker$_check_closure1.$callSiteArray != null ? TinkerSPIChecker$_check_closure1.$createCallSiteArray() : (CallSiteArray)TinkerSPIChecker$_check_closure1.$callSiteArray.get();
            TinkerSPIChecker$_check_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/feature/TinkerSPIChecker$_check_closure2
    public final class TinkerSPIChecker$_check_closure2 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerSPIChecker$_check_closure2(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure2.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].callGetProperty(siteArr0[2].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[3].callGetProperty(TinkerSPIChecker.class), it}, new String[]{"", ": find delete SPI: ", ""}));
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerSPIChecker$_check_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerSPIChecker$_check_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerSPIChecker$_check_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerSPIChecker$_check_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "error";
            stringArr0[1] = "logger";
            stringArr0[2] = "project";
            stringArr0[3] = "TAG";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerSPIChecker$_check_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerSPIChecker$_check_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerSPIChecker$_check_closure2.$callSiteArray != null ? TinkerSPIChecker$_check_closure2.$createCallSiteArray() : (CallSiteArray)TinkerSPIChecker$_check_closure2.$callSiteArray.get();
            TinkerSPIChecker$_check_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/feature/TinkerSPIChecker$_check_closure2
    public final class TinkerSPIChecker$_check_closure2 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public TinkerSPIChecker$_check_closure2(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure2.$getCallSiteArray();
            return siteArr0[0].call(siteArr0[1].callGetProperty(siteArr0[2].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[3].callGetProperty(TinkerSPIChecker.class), it}, new String[]{"", ": find delete SPI: ", ""}));
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = TinkerSPIChecker$_check_closure2.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != TinkerSPIChecker$_check_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (TinkerSPIChecker$_check_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    TinkerSPIChecker$_check_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return TinkerSPIChecker$_check_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "error";
            stringArr0[1] = "logger";
            stringArr0[2] = "project";
            stringArr0[3] = "TAG";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            TinkerSPIChecker$_check_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(TinkerSPIChecker$_check_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = TinkerSPIChecker$_check_closure2.$callSiteArray != null ? TinkerSPIChecker$_check_closure2.$createCallSiteArray() : (CallSiteArray)TinkerSPIChecker$_check_closure2.$callSiteArray.get();
            TinkerSPIChecker$_check_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
