/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/extension;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import org.gradle.api.Project;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.util.List;
import java.util.regex.Pattern;

// class: com/tencent/rfix/build/gradle/extension/RFixRedirectConfigExtension
public class RFixRedirectConfigExtension implements GroovyObject {
    final private static Object REDIRECT_DIR;
    final private static Object METHOD_IDS_PATH;
    private Project project;
    private String redirectDir;
    private boolean performanceMode;
    private boolean redirectByField;
    private boolean transformEnable;
    private String applyMethodIds;
    private String saveMethodIds;
    private boolean jarFilterEnable;
    private Iterable<String> includeJars;
    private Iterable<String> excludeClasses;
    private Iterable<String> excludeClassExtends;
    private Iterable<String> excludeIgnoreClasses;
    private boolean libFixEnable;
    private boolean effectImmediate;
    private boolean overrideSupport;
    private boolean patchCompleteCheck;
    private Iterable<String> oldMappings;
    private Iterable<String> newMappings;
    private Iterable<String> oldMethodIds;
    private Iterable<String> patchIncludeClasses;
    private Iterable<String> patchExcludeClasses;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixRedirectConfigExtension(Project project) {
        CallSite[] siteArr0 = RFixRedirectConfigExtension.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        (Project)ScriptBytecodeAdapter.castToType(project, Project.class).project = this;
        GStringImpl impl = new GStringImpl(new Object[]{siteArr0[0].callGetProperty(project), RFixRedirectConfigExtension.REDIRECT_DIR}, new String[]{"", "", ""});
        (String)ShortTypeHandling.castToString(impl).redirectDir = this;
        GStringImpl implVar1 = new GStringImpl(new Object[]{siteArr0[1].callGetProperty(project), RFixRedirectConfigExtension.METHOD_IDS_PATH}, new String[]{"", "", ""});
        (String)ShortTypeHandling.castToString(implVar1).saveMethodIds = this;
        int i0 = 0;
        i0.performanceMode = this;
        int i1 = 0;
        i1.redirectByField = this;
        int i2 = 0;
        i2.transformEnable = this;
        Object object = null;
        (String)ShortTypeHandling.castToString(object).applyMethodIds = this;
        int i3 = 0;
        i3.jarFilterEnable = this;
        List list = ScriptBytecodeAdapter.createList(new Object[]{});
        list.includeJars = this;
        List listVar1 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar1.excludeIgnoreClasses = this;
        List listVar2 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar2.excludeClasses = this;
        List listVar3 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar3.excludeClassExtends = this;
        int i4 = 0;
        i4.libFixEnable = this;
        int i5 = 0;
        i5.effectImmediate = this;
        int i6 = 0;
        i6.overrideSupport = this;
        int i7 = 1;
        i7.patchCompleteCheck = this;
        List listVar4 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar4.oldMappings = this;
        List listVar5 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar5.newMappings = this;
        List listVar6 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar6.oldMethodIds = this;
        List listVar7 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar7.patchIncludeClasses = this;
        List listVar8 = ScriptBytecodeAdapter.createList(new Object[]{});
        listVar8.patchExcludeClasses = this;
    }

    public Pattern[] getIncludeJarPatterns() {
        CallSite[] siteArr0 = RFixRedirectConfigExtension.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[2].call(this.includeJars))) {
            return new Pattern[][]{};
        }
        else {
            v_18 = alloc(Reference);
            new new Pattern[][].<init>(v_18);
            Reference includes = v_18;
            siteArr0[4].call(this.includeJars, new RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1(this, this, includes));
            return (Pattern[])includes.get();
        }
    }

    public Pattern[] getExcludeClassPatterns() {
        CallSite[] siteArr0 = RFixRedirectConfigExtension.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[5].call(this.excludeClasses))) {
            return new Pattern[][]{};
        }
        else {
            v_18 = alloc(Reference);
            new new Pattern[][].<init>(v_18);
            Reference excludes = v_18;
            siteArr0[7].call(this.excludeClasses, new RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2(this, this, excludes));
            return (Pattern[])excludes.get();
        }
    }

    public Pattern[] getExcludeIgnoreClassPatterns() {
        CallSite[] siteArr0 = RFixRedirectConfigExtension.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[8].call(this.excludeIgnoreClasses))) {
            return new Pattern[][]{};
        }
        else {
            v_18 = alloc(Reference);
            new new Pattern[][].<init>(v_18);
            Reference includes = v_18;
            siteArr0[10].call(this.excludeIgnoreClasses, new RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3(this, this, includes));
            return (Pattern[])includes.get();
        }
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixRedirectConfigExtension.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixRedirectConfigExtension.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixRedirectConfigExtension.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixRedirectConfigExtension.$staticClassInfo.getMetaClass();
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
        String str0 = "/redirect";
        RFixRedirectConfigExtension.REDIRECT_DIR = str0;
        GStringImpl impl = new GStringImpl(new Object[]{RFixRedirectConfigExtension.REDIRECT_DIR}, new String[]{"", "/methodIds.txt"});
        RFixRedirectConfigExtension.METHOD_IDS_PATH = impl;
    }

    @Generated
    public static Object getREDIRECT_DIR() {
        return RFixRedirectConfigExtension.REDIRECT_DIR;
    }

    @Generated
    public static Object getMETHOD_IDS_PATH() {
        return RFixRedirectConfigExtension.METHOD_IDS_PATH;
    }

    @Generated
    public boolean getPerformanceMode() {
        return this.performanceMode;
    }

    @Generated
    public boolean isPerformanceMode() {
        return this.performanceMode;
    }

    @Generated
    public void setPerformanceMode(boolean bool0) {
        this.performanceMode = bool0;
    }

    @Generated
    public boolean getRedirectByField() {
        return this.redirectByField;
    }

    @Generated
    public boolean isRedirectByField() {
        return this.redirectByField;
    }

    @Generated
    public void setRedirectByField(boolean bool0) {
        this.redirectByField = bool0;
    }

    @Generated
    public boolean getTransformEnable() {
        return this.transformEnable;
    }

    @Generated
    public boolean isTransformEnable() {
        return this.transformEnable;
    }

    @Generated
    public void setTransformEnable(boolean bool0) {
        this.transformEnable = bool0;
    }

    @Generated
    public String getApplyMethodIds() {
        return this.applyMethodIds;
    }

    @Generated
    public void setApplyMethodIds(String str0) {
        this.applyMethodIds = str0;
    }

    @Generated
    public String getSaveMethodIds() {
        return this.saveMethodIds;
    }

    @Generated
    public void setSaveMethodIds(String str0) {
        this.saveMethodIds = str0;
    }

    @Generated
    public boolean getJarFilterEnable() {
        return this.jarFilterEnable;
    }

    @Generated
    public boolean isJarFilterEnable() {
        return this.jarFilterEnable;
    }

    @Generated
    public void setJarFilterEnable(boolean bool0) {
        this.jarFilterEnable = bool0;
    }

    @Generated
    public Iterable<String> getIncludeJars() {
        return this.includeJars;
    }

    @Generated
    public void setIncludeJars(Iterable<String> iterable) {
        this.includeJars = iterable;
    }

    @Generated
    public Iterable<String> getExcludeClasses() {
        return this.excludeClasses;
    }

    @Generated
    public void setExcludeClasses(Iterable<String> iterable) {
        this.excludeClasses = iterable;
    }

    @Generated
    public Iterable<String> getExcludeClassExtends() {
        return this.excludeClassExtends;
    }

    @Generated
    public void setExcludeClassExtends(Iterable<String> iterable) {
        this.excludeClassExtends = iterable;
    }

    @Generated
    public Iterable<String> getExcludeIgnoreClasses() {
        return this.excludeIgnoreClasses;
    }

    @Generated
    public void setExcludeIgnoreClasses(Iterable<String> iterable) {
        this.excludeIgnoreClasses = iterable;
    }

    @Generated
    public boolean getLibFixEnable() {
        return this.libFixEnable;
    }

    @Generated
    public boolean isLibFixEnable() {
        return this.libFixEnable;
    }

    @Generated
    public void setLibFixEnable(boolean bool0) {
        this.libFixEnable = bool0;
    }

    @Generated
    public boolean getEffectImmediate() {
        return this.effectImmediate;
    }

    @Generated
    public boolean isEffectImmediate() {
        return this.effectImmediate;
    }

    @Generated
    public void setEffectImmediate(boolean bool0) {
        this.effectImmediate = bool0;
    }

    @Generated
    public boolean getOverrideSupport() {
        return this.overrideSupport;
    }

    @Generated
    public boolean isOverrideSupport() {
        return this.overrideSupport;
    }

    @Generated
    public void setOverrideSupport(boolean bool0) {
        this.overrideSupport = bool0;
    }

    @Generated
    public boolean getPatchCompleteCheck() {
        return this.patchCompleteCheck;
    }

    @Generated
    public boolean isPatchCompleteCheck() {
        return this.patchCompleteCheck;
    }

    @Generated
    public void setPatchCompleteCheck(boolean bool0) {
        this.patchCompleteCheck = bool0;
    }

    @Generated
    public Iterable<String> getOldMappings() {
        return this.oldMappings;
    }

    @Generated
    public void setOldMappings(Iterable<String> iterable) {
        this.oldMappings = iterable;
    }

    @Generated
    public Iterable<String> getNewMappings() {
        return this.newMappings;
    }

    @Generated
    public void setNewMappings(Iterable<String> iterable) {
        this.newMappings = iterable;
    }

    @Generated
    public Iterable<String> getOldMethodIds() {
        return this.oldMethodIds;
    }

    @Generated
    public void setOldMethodIds(Iterable<String> iterable) {
        this.oldMethodIds = iterable;
    }

    @Generated
    public Iterable<String> getPatchIncludeClasses() {
        return this.patchIncludeClasses;
    }

    @Generated
    public void setPatchIncludeClasses(Iterable<String> iterable) {
        this.patchIncludeClasses = iterable;
    }

    @Generated
    public Iterable<String> getPatchExcludeClasses() {
        return this.patchExcludeClasses;
    }

    @Generated
    public void setPatchExcludeClasses(Iterable<String> iterable) {
        this.patchExcludeClasses = iterable;
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "buildDir";
        stringArr0[1] = "buildDir";
        stringArr0[2] = "isEmpty";
        stringArr0[3] = "size";
        stringArr0[4] = "eachWithIndex";
        stringArr0[5] = "isEmpty";
        stringArr0[6] = "size";
        stringArr0[7] = "eachWithIndex";
        stringArr0[8] = "isEmpty";
        stringArr0[9] = "size";
        stringArr0[10] = "eachWithIndex";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixRedirectConfigExtension.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixRedirectConfigExtension.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixRedirectConfigExtension.$callSiteArray != null ? RFixRedirectConfigExtension.$createCallSiteArray() : (CallSiteArray)RFixRedirectConfigExtension.$callSiteArray.get();
        RFixRedirectConfigExtension.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/rfix/build/gradle/extension/RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2
    public final class RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2 implements GeneratedClosure {
        private synthetic Reference excludes;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2(Object _outerInstance, Object _thisObject, Reference excludes) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            excludes.excludes = this;
        }

        public Object doCall(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$getCallSiteArray();
            if (BytecodeInterface8.isOrigInt() && RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 68;
                Object objectVar1 = siteArr0[2].call(Pattern.class, entry);
                BytecodeInterface8.objectArraySet((Pattern[])ScriptBytecodeAdapter.castToType(this.excludes.get(), Pattern[].class), index, (Pattern)ScriptBytecodeAdapter.castToType(objectVar1, Pattern.class));
                return objectVar1;
            }
            else {
                Object object = siteArr0[0].call(Pattern.class, entry);
                siteArr0[1].call(this.excludes.get(), Integer.valueOf(index), object);
                return object;
            }
        }

        public Object call(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$getCallSiteArray();
            if (RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 38;
                return this.doCall(entry, index);
            }
            else {
                return siteArr0[3].callCurrent(this, entry, Integer.valueOf(index));
            }
        }

        @Generated
        public Pattern[] getExcludes() {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$getCallSiteArray();
            return (Pattern[])ScriptBytecodeAdapter.castToType(this.excludes.get(), Pattern[].class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "compile";
            stringArr0[1] = "putAt";
            stringArr0[2] = "compile";
            stringArr0[3] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$callSiteArray != null ? RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$createCallSiteArray() : (CallSiteArray)RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$callSiteArray.get();
            RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/extension/RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2
    public final class RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2 implements GeneratedClosure {
        private synthetic Reference excludes;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2(Object _outerInstance, Object _thisObject, Reference excludes) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            excludes.excludes = this;
        }

        public Object doCall(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$getCallSiteArray();
            if (BytecodeInterface8.isOrigInt() && RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 68;
                Object objectVar1 = siteArr0[2].call(Pattern.class, entry);
                BytecodeInterface8.objectArraySet((Pattern[])ScriptBytecodeAdapter.castToType(this.excludes.get(), Pattern[].class), index, (Pattern)ScriptBytecodeAdapter.castToType(objectVar1, Pattern.class));
                return objectVar1;
            }
            else {
                Object object = siteArr0[0].call(Pattern.class, entry);
                siteArr0[1].call(this.excludes.get(), Integer.valueOf(index), object);
                return object;
            }
        }

        public Object call(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$getCallSiteArray();
            if (RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 38;
                return this.doCall(entry, index);
            }
            else {
                return siteArr0[3].callCurrent(this, entry, Integer.valueOf(index));
            }
        }

        @Generated
        public Pattern[] getExcludes() {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$getCallSiteArray();
            return (Pattern[])ScriptBytecodeAdapter.castToType(this.excludes.get(), Pattern[].class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "compile";
            stringArr0[1] = "putAt";
            stringArr0[2] = "compile";
            stringArr0[3] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$callSiteArray != null ? RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$createCallSiteArray() : (CallSiteArray)RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$callSiteArray.get();
            RFixRedirectConfigExtension$_getExcludeClassPatterns_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/extension/RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3
    public final class RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3 implements GeneratedClosure {
        private synthetic Reference includes;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3(Object _outerInstance, Object _thisObject, Reference includes) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            includes.includes = this;
        }

        public Object doCall(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$getCallSiteArray();
            if (BytecodeInterface8.isOrigInt() && RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 68;
                Object objectVar1 = siteArr0[2].call(Pattern.class, entry);
                BytecodeInterface8.objectArraySet((Pattern[])ScriptBytecodeAdapter.castToType(this.includes.get(), Pattern[].class), index, (Pattern)ScriptBytecodeAdapter.castToType(objectVar1, Pattern.class));
                return objectVar1;
            }
            else {
                Object object = siteArr0[0].call(Pattern.class, entry);
                siteArr0[1].call(this.includes.get(), Integer.valueOf(index), object);
                return object;
            }
        }

        public Object call(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$getCallSiteArray();
            if (RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 38;
                return this.doCall(entry, index);
            }
            else {
                return siteArr0[3].callCurrent(this, entry, Integer.valueOf(index));
            }
        }

        @Generated
        public Pattern[] getIncludes() {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$getCallSiteArray();
            return (Pattern[])ScriptBytecodeAdapter.castToType(this.includes.get(), Pattern[].class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "compile";
            stringArr0[1] = "putAt";
            stringArr0[2] = "compile";
            stringArr0[3] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$callSiteArray != null ? RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$createCallSiteArray() : (CallSiteArray)RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$callSiteArray.get();
            RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/extension/RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3
    public final class RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3 implements GeneratedClosure {
        private synthetic Reference includes;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3(Object _outerInstance, Object _thisObject, Reference includes) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            includes.includes = this;
        }

        public Object doCall(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$getCallSiteArray();
            if (BytecodeInterface8.isOrigInt() && RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 68;
                Object objectVar1 = siteArr0[2].call(Pattern.class, entry);
                BytecodeInterface8.objectArraySet((Pattern[])ScriptBytecodeAdapter.castToType(this.includes.get(), Pattern[].class), index, (Pattern)ScriptBytecodeAdapter.castToType(objectVar1, Pattern.class));
                return objectVar1;
            }
            else {
                Object object = siteArr0[0].call(Pattern.class, entry);
                siteArr0[1].call(this.includes.get(), Integer.valueOf(index), object);
                return object;
            }
        }

        public Object call(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$getCallSiteArray();
            if (RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 38;
                return this.doCall(entry, index);
            }
            else {
                return siteArr0[3].callCurrent(this, entry, Integer.valueOf(index));
            }
        }

        @Generated
        public Pattern[] getIncludes() {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$getCallSiteArray();
            return (Pattern[])ScriptBytecodeAdapter.castToType(this.includes.get(), Pattern[].class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "compile";
            stringArr0[1] = "putAt";
            stringArr0[2] = "compile";
            stringArr0[3] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$callSiteArray != null ? RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$createCallSiteArray() : (CallSiteArray)RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$callSiteArray.get();
            RFixRedirectConfigExtension$_getExcludeIgnoreClassPatterns_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/extension/RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1
    public final class RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1 implements GeneratedClosure {
        private synthetic Reference includes;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1(Object _outerInstance, Object _thisObject, Reference includes) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            includes.includes = this;
        }

        public Object doCall(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$getCallSiteArray();
            if (BytecodeInterface8.isOrigInt() && RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 68;
                Object objectVar1 = siteArr0[2].call(Pattern.class, entry);
                BytecodeInterface8.objectArraySet((Pattern[])ScriptBytecodeAdapter.castToType(this.includes.get(), Pattern[].class), index, (Pattern)ScriptBytecodeAdapter.castToType(objectVar1, Pattern.class));
                return objectVar1;
            }
            else {
                Object object = siteArr0[0].call(Pattern.class, entry);
                siteArr0[1].call(this.includes.get(), Integer.valueOf(index), object);
                return object;
            }
        }

        public Object call(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$getCallSiteArray();
            if (RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 38;
                return this.doCall(entry, index);
            }
            else {
                return siteArr0[3].callCurrent(this, entry, Integer.valueOf(index));
            }
        }

        @Generated
        public Pattern[] getIncludes() {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$getCallSiteArray();
            return (Pattern[])ScriptBytecodeAdapter.castToType(this.includes.get(), Pattern[].class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "compile";
            stringArr0[1] = "putAt";
            stringArr0[2] = "compile";
            stringArr0[3] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$callSiteArray != null ? RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$createCallSiteArray() : (CallSiteArray)RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$callSiteArray.get();
            RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/extension/RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1
    public final class RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1 implements GeneratedClosure {
        private synthetic Reference includes;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1(Object _outerInstance, Object _thisObject, Reference includes) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            includes.includes = this;
        }

        public Object doCall(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$getCallSiteArray();
            if (BytecodeInterface8.isOrigInt() && RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 68;
                Object objectVar1 = siteArr0[2].call(Pattern.class, entry);
                BytecodeInterface8.objectArraySet((Pattern[])ScriptBytecodeAdapter.castToType(this.includes.get(), Pattern[].class), index, (Pattern)ScriptBytecodeAdapter.castToType(objectVar1, Pattern.class));
                return objectVar1;
            }
            else {
                Object object = siteArr0[0].call(Pattern.class, entry);
                siteArr0[1].call(this.includes.get(), Integer.valueOf(index), object);
                return object;
            }
        }

        public Object call(String entry, int index) {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$getCallSiteArray();
            if (RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
                goto 38;
                return this.doCall(entry, index);
            }
            else {
                return siteArr0[3].callCurrent(this, entry, Integer.valueOf(index));
            }
        }

        @Generated
        public Pattern[] getIncludes() {
            CallSite[] siteArr0 = RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$getCallSiteArray();
            return (Pattern[])ScriptBytecodeAdapter.castToType(this.includes.get(), Pattern[].class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "compile";
            stringArr0[1] = "putAt";
            stringArr0[2] = "compile";
            stringArr0[3] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$callSiteArray != null ? RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$createCallSiteArray() : (CallSiteArray)RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$callSiteArray.get();
            RFixRedirectConfigExtension$_getIncludeJarPatterns_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
