/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/utils;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;

// class: com/tencent/rfix/build/gradle/utils/GradleUtils
public class GradleUtils implements GroovyObject {
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    @Generated
    public GradleUtils() {
        CallSite[] siteArr0 = GradleUtils.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
    }

    public static String getAGPVersion(Project project) {
        CallSite[] siteArr0 = GradleUtils.$getCallSiteArray();
        v_2 = alloc(Reference);
        new null.<init>(v_2);
        Reference version = v_2;
        Object rootProject = siteArr0[0].call(project);
        Object dependencies = siteArr0[1].call(siteArr0[2].call(siteArr0[3].call(siteArr0[4].callGetProperty(rootProject)), "classpath"));
        siteArr0[5].call(dependencies, new GradleUtils$_getAGPVersion_closure1(GradleUtils.class, GradleUtils.class, version));
        return (String)ShortTypeHandling.castToString(version.get());
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != GradleUtils.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (GradleUtils.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                GradleUtils.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return GradleUtils.$staticClassInfo.getMetaClass();
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
        stringArr0[0] = "getRootProject";
        stringArr0[1] = "getDependencies";
        stringArr0[2] = "getByName";
        stringArr0[3] = "getConfigurations";
        stringArr0[4] = "buildscript";
        stringArr0[5] = "each";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        GradleUtils.$createCallSiteArray_1(str0);
        return new CallSiteArray(GradleUtils.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = GradleUtils.$callSiteArray != null ? GradleUtils.$createCallSiteArray() : (CallSiteArray)GradleUtils.$callSiteArray.get();
        GradleUtils.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/rfix/build/gradle/utils/GradleUtils$_getAGPVersion_closure1
    public final class GradleUtils$_getAGPVersion_closure1 implements GeneratedClosure {
        private synthetic Reference version;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public GradleUtils$_getAGPVersion_closure1(Object _outerInstance, Object _thisObject, Reference version) {
            CallSite[] siteArr0 = GradleUtils$_getAGPVersion_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            version.version = this;
        }

        public Object doCall(Object dep) {
            CallSite[] siteArr0 = GradleUtils$_getAGPVersion_closure1.$getCallSiteArray();
            if (ScriptBytecodeAdapter.compareEqual(new GStringImpl(new Object[]{siteArr0[0].callGetProperty(dep), siteArr0[1].callGetProperty(dep)}, new String[]{"", ":", ""}), "com.android.tools.build:gradle")) {
                Object object = siteArr0[2].callGetProperty(dep);
                object.set(this.version);
                return object;
            }
            else {
                return null;
            }
        }

        @Generated
        public Object getVersion() {
            CallSite[] siteArr0 = GradleUtils$_getAGPVersion_closure1.$getCallSiteArray();
            return this.version.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != GradleUtils$_getAGPVersion_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (GradleUtils$_getAGPVersion_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    GradleUtils$_getAGPVersion_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return GradleUtils$_getAGPVersion_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "group";
            stringArr0[1] = "name";
            stringArr0[2] = "version";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            GradleUtils$_getAGPVersion_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(GradleUtils$_getAGPVersion_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = GradleUtils$_getAGPVersion_closure1.$callSiteArray != null ? GradleUtils$_getAGPVersion_closure1.$createCallSiteArray() : (CallSiteArray)GradleUtils$_getAGPVersion_closure1.$callSiteArray.get();
            GradleUtils$_getAGPVersion_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/utils/GradleUtils$_getAGPVersion_closure1
    public final class GradleUtils$_getAGPVersion_closure1 implements GeneratedClosure {
        private synthetic Reference version;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public GradleUtils$_getAGPVersion_closure1(Object _outerInstance, Object _thisObject, Reference version) {
            CallSite[] siteArr0 = GradleUtils$_getAGPVersion_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            version.version = this;
        }

        public Object doCall(Object dep) {
            CallSite[] siteArr0 = GradleUtils$_getAGPVersion_closure1.$getCallSiteArray();
            if (ScriptBytecodeAdapter.compareEqual(new GStringImpl(new Object[]{siteArr0[0].callGetProperty(dep), siteArr0[1].callGetProperty(dep)}, new String[]{"", ":", ""}), "com.android.tools.build:gradle")) {
                Object object = siteArr0[2].callGetProperty(dep);
                object.set(this.version);
                return object;
            }
            else {
                return null;
            }
        }

        @Generated
        public Object getVersion() {
            CallSite[] siteArr0 = GradleUtils$_getAGPVersion_closure1.$getCallSiteArray();
            return this.version.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != GradleUtils$_getAGPVersion_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (GradleUtils$_getAGPVersion_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    GradleUtils$_getAGPVersion_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return GradleUtils$_getAGPVersion_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "group";
            stringArr0[1] = "name";
            stringArr0[2] = "version";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            GradleUtils$_getAGPVersion_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(GradleUtils$_getAGPVersion_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = GradleUtils$_getAGPVersion_closure1.$callSiteArray != null ? GradleUtils$_getAGPVersion_closure1.$createCallSiteArray() : (CallSiteArray)GradleUtils$_getAGPVersion_closure1.$callSiteArray.get();
            GradleUtils$_getAGPVersion_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
