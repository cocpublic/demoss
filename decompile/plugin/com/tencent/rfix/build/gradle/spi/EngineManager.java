/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/spi;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.Map;
import java.util.Set;
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;

// class: com/tencent/rfix/build/gradle/spi/EngineManager
public class EngineManager implements GroovyObject {
    private static EngineManager instance;
    private Map<String, IEngine> nameToEnginMap;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    private EngineManager() {
        CallSite[] siteArr0 = EngineManager.$getCallSiteArray();
        super();
        Object object = null;
        (Map)ScriptBytecodeAdapter.castToType(object, Map.class).nameToEnginMap = this;
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object objectVar1 = siteArr0[0].callConstructor(HashMap.class);
        (Map)ScriptBytecodeAdapter.castToType(objectVar1, Map.class).nameToEnginMap = this;
        Object loader = siteArr0[1].call(ServiceLoader.class, IEngine.class, siteArr0[2].call(IEngine.class));
        Object iterator = siteArr0[3].call(loader);
        while (DefaultTypeTransformation.booleanUnbox(siteArr0[4].call(iterator))) {
            Object engine = siteArr0[5].call(iterator);
            siteArr0[6].call(this.nameToEnginMap, siteArr0[7].call(engine), engine);
        }
    }

    public static EngineManager getInstance() {
        CallSite[] siteArr0 = EngineManager.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(EngineManager.instance, null)) {
            Object object = siteArr0[8].callConstructor(EngineManager.class);
            EngineManager.instance = (EngineManager)ScriptBytecodeAdapter.castToType(object, EngineManager.class);
        }
        return EngineManager.instance;
    }

    public Set<String> getSupportEngine() {
        CallSite[] siteArr0 = EngineManager.$getCallSiteArray();
        return (Set)ScriptBytecodeAdapter.castToType(siteArr0[9].call(this.nameToEnginMap), Set.class);
    }

    public boolean isSupport(String name) {
        CallSite[] siteArr0 = EngineManager.$getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(siteArr0[10].call(this.nameToEnginMap, name));
    }

    public IEngine getEngine(String name) {
        CallSite[] siteArr0 = EngineManager.$getCallSiteArray();
        return (IEngine)ScriptBytecodeAdapter.castToType(siteArr0[11].call(this.nameToEnginMap, name), IEngine.class);
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != EngineManager.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (EngineManager.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                EngineManager.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return EngineManager.$staticClassInfo.getMetaClass();
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
        Object object = null;
        EngineManager.instance = (EngineManager)ScriptBytecodeAdapter.castToType(object, EngineManager.class);
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "<$constructor$>";
        stringArr0[1] = "load";
        stringArr0[2] = "getClassLoader";
        stringArr0[3] = "iterator";
        stringArr0[4] = "hasNext";
        stringArr0[5] = "next";
        stringArr0[6] = "put";
        stringArr0[7] = "getName";
        stringArr0[8] = "<$constructor$>";
        stringArr0[9] = "keySet";
        stringArr0[10] = "containsKey";
        stringArr0[11] = "get";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        EngineManager.$createCallSiteArray_1(str0);
        return new CallSiteArray(EngineManager.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = EngineManager.$callSiteArray != null ? EngineManager.$createCallSiteArray() : (CallSiteArray)EngineManager.$callSiteArray.get();
        EngineManager.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
