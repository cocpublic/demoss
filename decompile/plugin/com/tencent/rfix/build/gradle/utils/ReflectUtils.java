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
import sun.misc.Unsafe;

// class: com/tencent/rfix/build/gradle/utils/ReflectUtils
public class ReflectUtils implements GroovyObject {
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public ReflectUtils() {
        CallSite[] siteArr0 = ReflectUtils.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public static void replaceFinalField(Class targetClazz, String fieldName, Object instance, Object newFieldValue) {
        CallSite[] siteArr0 = ReflectUtils.$getCallSiteArray();
        Object field = null;
        while (true) {
            try {
                Object object = siteArr0[0].call(targetClazz, fieldName);
                field = (Field)ScriptBytecodeAdapter.castToType(object, Field.class);
                break;;
            }
            catch (NoSuchFieldException e) {
                if (ScriptBytecodeAdapter.compareEqual(targetClazz, Object.class)) {
                    throw (Throwable)e;
                }
                else {
                    Object objectVar1 = siteArr0[1].call(targetClazz);
                    curClazz = (Class)ShortTypeHandling.castToClass(objectVar1);
                    goto 107;
                    continue;;
                }
            }
            finally {
                Throwable throwable = v_17;
                throw throwable;
            }
        }
        Field unsafeField = (Field)ScriptBytecodeAdapter.castToType(siteArr0[2].call(Unsafe.class, "theUnsafe"), Field.class);
        siteArr0[3].call(unsafeField, Boolean.valueOf(true));
        Unsafe unsafe = (Unsafe)ScriptBytecodeAdapter.castToType(siteArr0[4].call(unsafeField, null), Unsafe.class);
        long fieldOffset = DefaultTypeTransformation.longUnbox(siteArr0[5].call(unsafe, field));
        siteArr0[6].call(unsafe, instance, Long.valueOf(fieldOffset), newFieldValue);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != ReflectUtils.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (ReflectUtils.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                ReflectUtils.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return ReflectUtils.$staticClassInfo.getMetaClass();
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
        stringArr0[0] = "getDeclaredField";
        stringArr0[1] = "getSuperclass";
        stringArr0[2] = "getDeclaredField";
        stringArr0[3] = "setAccessible";
        stringArr0[4] = "get";
        stringArr0[5] = "objectFieldOffset";
        stringArr0[6] = "putObject";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        ReflectUtils.$createCallSiteArray_1(str0);
        return new CallSiteArray(ReflectUtils.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = ReflectUtils.$callSiteArray != null ? ReflectUtils.$createCallSiteArray() : (CallSiteArray)ReflectUtils.$callSiteArray.get();
        ReflectUtils.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
