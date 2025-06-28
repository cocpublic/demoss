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
import java.io.File;
import java.io.Closeable;
import java.util.Iterator;

// class: com/tencent/rfix/build/gradle/utils/PatchFileUtils
public class PatchFileUtils implements GroovyObject {
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public PatchFileUtils() {
        CallSite[] siteArr0 = PatchFileUtils.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public static boolean isLegalFile(String path) {
        CallSite[] siteArr0 = PatchFileUtils.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(path, null)) {
            return false;
        }
        else {
            File file = (File)ScriptBytecodeAdapter.castToType(siteArr0[0].callConstructor(File.class, path), File.class);
            return DefaultTypeTransformation.booleanUnbox(siteArr0[1].callStatic(PatchFileUtils.class, file));
        }
    }

    public static boolean isLegalFile(File file) {
        CallSite[] siteArr0 = PatchFileUtils.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(file, null)) {
            return false;
        }
        else {
            if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && PatchFileUtils.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(file)) && DefaultTypeTransformation.booleanUnbox(siteArr0[6].call(file)) ? 0 : 1 != 0 && ScriptBytecodeAdapter.compareGreaterThan(siteArr0[7].call(file), Integer.valueOf(0))) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(file)) && DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(file)) ? 0 : 1 != 0 && ScriptBytecodeAdapter.compareGreaterThan(siteArr0[4].call(file), Integer.valueOf(0))) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }

    public static boolean deleteFile(File file) {
        CallSite[] siteArr0 = PatchFileUtils.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(file, null)) {
            return false;
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[8].call(file))) {
                String[] children = (String[])ScriptBytecodeAdapter.castToType(siteArr0[9].call(file), String[].class);
                Object child = null;
                Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[10].call(children), Iterator.class);
                while (iterator.hasNext()) {
                    String str0 = (String)ShortTypeHandling.castToString(iterator.next());
                    File tmpFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[11].callConstructor(File.class, file, str0), File.class);
                    boolean success = DefaultTypeTransformation.booleanUnbox(siteArr0[12].callStatic(PatchFileUtils.class, tmpFile));
                    if (success ? 0 : 1 != 0) {
                        return false;
                    }
                    else {
                        continue;;
                    }
                }
            }
            return DefaultTypeTransformation.booleanUnbox(siteArr0[13].call(file));
        }
    }

    public static void closeQuietly(Object obj) {
        CallSite[] siteArr0 = PatchFileUtils.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(obj, null)) {
            return;
        }
        else {
            if (PatchFileUtils.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                if ((obj instanceof Closeable)) {
                    try {
                        try {
                            siteArr0[18].call((Closeable)ScriptBytecodeAdapter.castToType(obj, Closeable.class));
                        }
                        catch (Throwable ignored) {
                        }
                    }
                    finally {
                        Throwable throwableVar1 = v_17;
                        throw throwableVar1;
                    }
                }
                else {
                    throw (Throwable)siteArr0[19].callConstructor(IllegalArgumentException.class, siteArr0[20].call(siteArr0[21].call("obj: ", obj), " cannot be closed."));
                }
            }
            else if ((obj instanceof Closeable)) {
                try {
                    try {
                        siteArr0[14].call((Closeable)ScriptBytecodeAdapter.castToType(obj, Closeable.class));
                    }
                    catch (Throwable ignored) {
                    }
                }
                finally {
                    Throwable throwable = v_47;
                    throw throwable;
                }
            }
            else {
                throw (Throwable)siteArr0[15].callConstructor(IllegalArgumentException.class, siteArr0[16].call(siteArr0[17].call("obj: ", obj), " cannot be closed."));
            }
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != PatchFileUtils.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (PatchFileUtils.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                PatchFileUtils.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return PatchFileUtils.$staticClassInfo.getMetaClass();
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
        stringArr0[0] = "<$constructor$>";
        stringArr0[1] = "isLegalFile";
        stringArr0[2] = "exists";
        stringArr0[3] = "isFile";
        stringArr0[4] = "length";
        stringArr0[5] = "exists";
        stringArr0[6] = "isFile";
        stringArr0[7] = "length";
        stringArr0[8] = "isDirectory";
        stringArr0[9] = "list";
        stringArr0[10] = "iterator";
        stringArr0[11] = "<$constructor$>";
        stringArr0[12] = "deleteFile";
        stringArr0[13] = "delete";
        stringArr0[14] = "close";
        stringArr0[15] = "<$constructor$>";
        stringArr0[16] = "plus";
        stringArr0[17] = "plus";
        stringArr0[18] = "close";
        stringArr0[19] = "<$constructor$>";
        stringArr0[20] = "plus";
        stringArr0[21] = "plus";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        PatchFileUtils.$createCallSiteArray_1(str0);
        return new CallSiteArray(PatchFileUtils.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = PatchFileUtils.$callSiteArray != null ? PatchFileUtils.$createCallSiteArray() : (CallSiteArray)PatchFileUtils.$callSiteArray.get();
        PatchFileUtils.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
