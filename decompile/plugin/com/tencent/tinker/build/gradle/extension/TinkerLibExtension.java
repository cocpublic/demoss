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

// class: com/tencent/tinker/build/gradle/extension/TinkerLibExtension
public class TinkerLibExtension implements GroovyObject {
    private Iterable<String> pattern;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerLibExtension() {
        CallSite[] siteArr0 = TinkerLibExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        List list = ScriptBytecodeAdapter.createList(new Object[]{});
        list.pattern = this;
    }

    public String toString() {
        CallSite[] siteArr0 = TinkerLibExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[0].call(new GStringImpl(new Object[]{this.pattern}, new String[]{"| pattern = ", "
        "})));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerLibExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerLibExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerLibExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerLibExtension.$staticClassInfo.getMetaClass();
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

    public /* synthetic */ String super$1$toString() {
        return this.toString();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "stripMargin";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerLibExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerLibExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerLibExtension.$callSiteArray != null ? TinkerLibExtension.$createCallSiteArray() : (CallSiteArray)TinkerLibExtension.$callSiteArray.get();
        TinkerLibExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
