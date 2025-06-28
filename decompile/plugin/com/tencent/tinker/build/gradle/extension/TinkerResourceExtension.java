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
import groovy.lang.MetaClass;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.util.List;

// class: com/tencent/tinker/build/gradle/extension/TinkerResourceExtension
public class TinkerResourceExtension implements GroovyObject {
    private Iterable<String> pattern;
    private Iterable<String> ignoreChange;
    private Iterable<String> ignoreChangeWarning;
    private int largeModSize;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerResourceExtension() {
        CallSite[] siteArr0 = TinkerResourceExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        List list = ScriptBytecodeAdapter.createList(new Object[]{});
        list.pattern = this;
        List listVar1 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar1.ignoreChange = this;
        List listVar2 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar2.ignoreChangeWarning = this;
        int i0 = 100;
        i0.largeModSize = this;
    }

    public void checkParameter() {
        CallSite[] siteArr0 = TinkerResourceExtension.$getCallSiteArray();
        if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && TinkerResourceExtension.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            if (this.largeModSize <= 0 ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[1].callConstructor(GradleException.class, "largeModSize must be larger than 0");
            }
            else {
            }
        }
        else {
            if (this.largeModSize <= 0 ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[0].callConstructor(GradleException.class, "largeModSize must be larger than 0");
            }
            else {
            }
        }
    }

    public String toString() {
        CallSite[] siteArr0 = TinkerResourceExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[2].call(new GStringImpl(new Object[]{this.pattern, this.ignoreChange, this.ignoreChangeWarning, Integer.valueOf(this.largeModSize)}, new String[]{"| pattern = ", "
           | exclude = ", "
           | ignoreWarning = ", "
           | largeModSize = ", "kb
        "})));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerResourceExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerResourceExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerResourceExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerResourceExtension.$staticClassInfo.getMetaClass();
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
    public Iterable<String> getPattern() {
        return this.pattern;
    }

    @Generated
    public void setPattern(Iterable<String> iterable) {
        this.pattern = iterable;
    }

    @Generated
    public Iterable<String> getIgnoreChange() {
        return this.ignoreChange;
    }

    @Generated
    public void setIgnoreChange(Iterable<String> iterable) {
        this.ignoreChange = iterable;
    }

    @Generated
    public Iterable<String> getIgnoreChangeWarning() {
        return this.ignoreChangeWarning;
    }

    @Generated
    public void setIgnoreChangeWarning(Iterable<String> iterable) {
        this.ignoreChangeWarning = iterable;
    }

    @Generated
    public int getLargeModSize() {
        return this.largeModSize;
    }

    @Generated
    public void setLargeModSize(int i0) {
        this.largeModSize = i0;
    }

    public /* synthetic */ String super$1$toString() {
        return this.toString();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "<$constructor$>";
        stringArr0[1] = "<$constructor$>";
        stringArr0[2] = "stripMargin";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerResourceExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerResourceExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerResourceExtension.$callSiteArray != null ? TinkerResourceExtension.$createCallSiteArray() : (CallSiteArray)TinkerResourceExtension.$callSiteArray.get();
        TinkerResourceExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
