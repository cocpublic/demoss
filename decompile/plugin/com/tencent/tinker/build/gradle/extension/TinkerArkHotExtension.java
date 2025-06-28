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

// class: com/tencent/tinker/build/gradle/extension/TinkerArkHotExtension
public class TinkerArkHotExtension implements GroovyObject {
    private String path;
    private String name;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public TinkerArkHotExtension() {
        CallSite[] siteArr0 = TinkerArkHotExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        String str0 = "arkHot";
        str0.path = this;
        String str1 = "patch.apk";
        str1.name = this;
    }

    public String toString() {
        CallSite[] siteArr0 = TinkerArkHotExtension.$getCallSiteArray();
        return (String)ShortTypeHandling.castToString(siteArr0[0].call(new GStringImpl(new Object[]{this.path, this.name}, new String[]{"| path= ", "
           | name= ", "
         "})));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != TinkerArkHotExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (TinkerArkHotExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                TinkerArkHotExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return TinkerArkHotExtension.$staticClassInfo.getMetaClass();
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
    public String getPath() {
        return this.path;
    }

    @Generated
    public void setPath(String str0) {
        this.path = str0;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public void setName(String str0) {
        this.name = str0;
    }

    public /* synthetic */ String super$1$toString() {
        return this.toString();
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "stripMargin";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        TinkerArkHotExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(TinkerArkHotExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = TinkerArkHotExtension.$callSiteArray != null ? TinkerArkHotExtension.$createCallSiteArray() : (CallSiteArray)TinkerArkHotExtension.$callSiteArray.get();
        TinkerArkHotExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

}
