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
import java.util.List;

// class: com/tencent/tinker/build/gradle/extension/TinkerDexExtension
public class TinkerDexExtension implements GroovyObject {
    private String dexMode;
    private Iterable<String> pattern;
    private Iterable<String> loader;
    private Iterable<String> ignoreWarningLoader;
    private Project project;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerDexExtension(Project project) {
        CallSite[] siteArr0 = TinkerDexExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        String str0 = "jar";
        str0.dexMode = this;
        List list = ScriptBytecodeAdapter.createList(new Object[]{});
        list.pattern = this;
        List listVar1 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar1.loader = this;
        List listVar2 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar2.ignoreWarningLoader = this;
        (Project)ScriptBytecodeAdapter.castToType(project, Project.class).project = this;
    }

    public void checkDexMode() {
        CallSite[] siteArr0 = TinkerDexExtension.$getCallSiteArray();
        if (BytecodeInterface8.isOrigZ() && TinkerDexExtension.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[3].call(this.dexMode, "raw")) ? 0 : 1 != 0) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[4].call(this.dexMode, "jar")) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[5].callConstructor(GradleException.class, "dexMode can be only one of 'jar' or 'raw'!");
            }
            else {
            }
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(this.dexMode, "raw")) ? 0 : 1 != 0) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[1].call(this.dexMode, "jar")) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[2].callConstructor(GradleException.class, "dexMode can be only one of 'jar' or 'raw'!");
            }
            else {
            }
        }
    }

    public String toString() {
        CallSite[] siteArr0 = TinkerDexExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[6].call(new GStringImpl(new Object[]{this.dexMode, this.pattern, this.loader, this.ignoreWarningLoader}, new String[]{"| dexMode = ", "
           | pattern = ", "
           | loader = ", "
           | ignoreWarningLoader = ", "
        "})));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerDexExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerDexExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerDexExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerDexExtension.$staticClassInfo.getMetaClass();
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
    public String getDexMode() {
        return this.dexMode;
    }

    @Generated
    public void setDexMode(String str0) {
        this.dexMode = str0;
    }

    @Generated
    public Iterable<String> getPattern() {
        return this.pattern;
    }

    @Generated
    public void setPattern(Iterable<String> iterable) {
        this.pattern = iterable;
    }

    @Generated
    public Iterable<String> getLoader() {
        return this.loader;
    }

    @Generated
    public void setLoader(Iterable<String> iterable) {
        this.loader = iterable;
    }

    @Generated
    public Iterable<String> getIgnoreWarningLoader() {
        return this.ignoreWarningLoader;
    }

    @Generated
    public void setIgnoreWarningLoader(Iterable<String> iterable) {
        this.ignoreWarningLoader = iterable;
    }

    public /* synthetic */ String super$1$toString() {
        return this.toString();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "equals";
        stringArr0[1] = "equals";
        stringArr0[2] = "<$constructor$>";
        stringArr0[3] = "equals";
        stringArr0[4] = "equals";
        stringArr0[5] = "<$constructor$>";
        stringArr0[6] = "stripMargin";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerDexExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerDexExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerDexExtension.$callSiteArray != null ? TinkerDexExtension.$createCallSiteArray() : (CallSiteArray)TinkerDexExtension.$callSiteArray.get();
        TinkerDexExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
