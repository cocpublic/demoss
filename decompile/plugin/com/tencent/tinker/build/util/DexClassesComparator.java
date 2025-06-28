/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.nio.ByteBuffer;
import java.io.File;
import com.tencent.tinker.commons.dexpatcher.DexPatcherLogger;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.ClassData$Field;
import com.tencent.tinker.android.dex.ClassData$Method;
import com.tencent.tinker.android.dex.FieldId;
import com.tencent.tinker.android.dex.MethodId;
import com.tencent.tinker.android.dex.ProtoId;
import com.tencent.tinker.android.dex.Dex$Section;
import com.tencent.tinker.android.dex.EncodedValue;
import com.tencent.tinker.android.dex.EncodedValueReader;
import com.tencent.tinker.android.dex.AnnotationsDirectory;
import com.tencent.tinker.android.dex.CallSiteId;
import com.tencent.tinker.android.dex.MethodHandle;
import com.tencent.tinker.android.dex.MethodHandle$MethodHandleType;
import com.tencent.tinker.android.dex.TypeList;
import com.tencent.tinker.android.dex.AnnotationSetRefList;
import com.tencent.tinker.android.dex.AnnotationSet;
import com.tencent.tinker.android.dex.Annotation;
import com.tencent.tinker.android.dex.Code;
import com.tencent.tinker.android.dex.Code$Try;
import com.tencent.tinker.android.dex.Code$CatchHandler;
import com.tencent.tinker.android.dex.DebugInfoItem;
import com.tencent.tinker.android.dex.io.DexDataBuffer;

// class: com/tencent/tinker/build/util/DexClassesComparator
public final class DexClassesComparator {
    final private static String TAG;
    final public static int COMPARE_MODE_NORMAL;
    final public static int COMPARE_MODE_REFERRER_AFFECTED_CHANGE_ONLY;
    final private static int DBG_FIRST_SPECIAL;
    final private static int DBG_LINE_BASE;
    final private static int DBG_LINE_RANGE;
    private int compareMode;
    final private List<DexClassesComparator$DexClassInfo> addedClassInfoList;
    final private List<DexClassesComparator$DexClassInfo> deletedClassInfoList;
    final private Map<String, DexClassesComparator$DexClassInfo[]> changedClassDescToClassInfosMap;
    final private Set<Pattern> patternsOfClassDescToCheck;
    final private Set<Pattern> patternsOfIgnoredRemovedClassDesc;
    final private Set<String> oldDescriptorOfClassesToCheck;
    final private Set<String> newDescriptorOfClassesToCheck;
    final private Map<String, DexClassesComparator$DexClassInfo> oldClassDescriptorToClassInfoMap;
    final private Map<String, DexClassesComparator$DexClassInfo> newClassDescriptorToClassInfoMap;
    final private Set<String> refAffectedClassDescs;
    final private DexPatcherLogger logger;

    public DexClassesComparator(String patternStringOfClassDescToCheck) {
        super();
        this.compareMode = 0;
        this.addedClassInfoList = new ArrayList();
        this.deletedClassInfoList = new ArrayList();
        this.changedClassDescToClassInfosMap = new HashMap();
        this.patternsOfClassDescToCheck = new HashSet();
        this.patternsOfIgnoredRemovedClassDesc = new HashSet();
        this.oldDescriptorOfClassesToCheck = new HashSet();
        this.newDescriptorOfClassesToCheck = new HashSet();
        this.oldClassDescriptorToClassInfoMap = new HashMap();
        this.newClassDescriptorToClassInfoMap = new HashMap();
        this.refAffectedClassDescs = new HashSet();
        this.logger = new DexPatcherLogger();
        this.patternsOfClassDescToCheck.add(Pattern.compile(PatternUtils.dotClassNamePatternToDescriptorRegEx(patternStringOfClassDescToCheck)));
    }

    public DexClassesComparator(String[] patternStringsOfClassDescToCheck) {
        super();
        this.compareMode = 0;
        this.addedClassInfoList = new ArrayList();
        this.deletedClassInfoList = new ArrayList();
        this.changedClassDescToClassInfosMap = new HashMap();
        this.patternsOfClassDescToCheck = new HashSet();
        this.patternsOfIgnoredRemovedClassDesc = new HashSet();
        this.oldDescriptorOfClassesToCheck = new HashSet();
        this.newDescriptorOfClassesToCheck = new HashSet();
        this.oldClassDescriptorToClassInfoMap = new HashMap();
        this.newClassDescriptorToClassInfoMap = new HashMap();
        this.refAffectedClassDescs = new HashSet();
        this.logger = new DexPatcherLogger();
        for (int i1 = 0; i1 < patternStringsOfClassDescToCheck.length; i1 += 1) {
            String patternStr = patternStringsOfClassDescToCheck[i1];
            this.patternsOfClassDescToCheck.add(Pattern.compile(PatternUtils.dotClassNamePatternToDescriptorRegEx(patternStr)));
        }
    }

    publicvoid DexClassesComparator(Collection<String> patternStringsOfClassDescToCheck) {
        super();
        this.compareMode = 0;
        this.addedClassInfoList = new ArrayList();
        this.deletedClassInfoList = new ArrayList();
        this.changedClassDescToClassInfosMap = new HashMap();
        this.patternsOfClassDescToCheck = new HashSet();
        this.patternsOfIgnoredRemovedClassDesc = new HashSet();
        this.oldDescriptorOfClassesToCheck = new HashSet();
        this.newDescriptorOfClassesToCheck = new HashSet();
        this.oldClassDescriptorToClassInfoMap = new HashMap();
        this.newClassDescriptorToClassInfoMap = new HashMap();
        this.refAffectedClassDescs = new HashSet();
        this.logger = new DexPatcherLogger();
        Iterator iterator = patternStringsOfClassDescToCheck.iterator();
        while (iterator.hasNext()) {
            String patternStr = (String)iterator.next();
            this.patternsOfClassDescToCheck.add(Pattern.compile(PatternUtils.dotClassNamePatternToDescriptorRegEx(patternStr)));
        }
    }

    public void setIgnoredRemovedClassDescPattern(String[] patternStringsOfLoaderClassDesc) {
        this.patternsOfIgnoredRemovedClassDesc.clear();
        for (int i1 = 0; i1 < patternStringsOfLoaderClassDesc.length; i1 += 1) {
            String patternStr = patternStringsOfLoaderClassDesc[i1];
            this.patternsOfIgnoredRemovedClassDesc.add(Pattern.compile(PatternUtils.dotClassNamePatternToDescriptorRegEx(patternStr)));
        }
    }

    public void setIgnoredRemovedClassDescPattern(Collection<String> patternStringsOfLoaderClassDesc) {
        this.patternsOfIgnoredRemovedClassDesc.clear();
        Iterator iterator = patternStringsOfLoaderClassDesc.iterator();
        while (iterator.hasNext()) {
            String patternStr = (String)iterator.next();
            this.patternsOfIgnoredRemovedClassDesc.add(Pattern.compile(PatternUtils.dotClassNamePatternToDescriptorRegEx(patternStr)));
        }
    }

    public void setCompareMode(int mode) {
        if (mode == 0 || mode == 1) {
            this.compareMode = mode;
            return;
        }
        else {
            throw new IllegalArgumentException(new StringBuilder().append("bad compare mode: ").append(mode).toString());
        }
    }

    public void setLogger(DexPatcherLogger$IDexPatcherLogger logger) {
        this.logger.setLoggerImpl(logger);
    }

    public List<DexClassesComparator$DexClassInfo> getAddedClassInfos() {
        return Collections.unmodifiableList(this.addedClassInfoList);
    }

    public List<DexClassesComparator$DexClassInfo> getDeletedClassInfos() {
        return Collections.unmodifiableList(this.deletedClassInfoList);
    }

    public Map<String, DexClassesComparator$DexClassInfo[]> getChangedClassDescToInfosMap() {
        return Collections.unmodifiableMap(this.changedClassDescToClassInfosMap);
    }

    public void startCheck(File oldDexFile, File newDexFile) {
        this.startCheck(new Dex(oldDexFile), new Dex(newDexFile));
    }

    public void startCheck(Dex oldDex, Dex newDex) {
        this.startCheck(DexClassesComparator$DexGroup.wrap(new Dex[]{oldDex}), DexClassesComparator$DexGroup.wrap(new Dex[]{newDex}));
    }

    public void startCheck(DexClassesComparator$DexGroup oldDexGroup, DexClassesComparator$DexGroup newDexGroup) {
        DexClassesComparator$DexClassInfo classInfo;
        String desc;
        int classDefIndex;
        this.addedClassInfoList.clear();
        this.deletedClassInfoList.clear();
        this.changedClassDescToClassInfosMap.clear();
        this.oldDescriptorOfClassesToCheck.clear();
        this.newDescriptorOfClassesToCheck.clear();
        this.oldClassDescriptorToClassInfoMap.clear();
        this.newClassDescriptorToClassInfoMap.clear();
        this.refAffectedClassDescs.clear();
        Dex dexVar1 = oldDexGroup.dexes;
        for (int i3 = 0; i3 < dexVar1.length; i3 += 1) {
            Dex oldDex = dexVar1[i3];
            classDefIndex = 0;
            Iterator iterator = oldDex.classDefs().iterator();
            while (iterator.hasNext()) {
                ClassDef oldClassDef = (ClassDef)iterator.next();
                desc = (String)oldDex.typeNames().get(oldClassDef.typeIndex);
                if (Utils.isStringMatchesPatterns(desc, this.patternsOfClassDescToCheck) && this.oldDescriptorOfClassesToCheck.add(desc)) {
                    throw new IllegalStateException(String.format("duplicate class descriptor [%s] in different old dexes.", new Object[]{desc}));
                }
                else {
                    classInfo = new DexClassesComparator$DexClassInfo(desc, classDefIndex, oldClassDef, oldDex, null);
                    classDefIndex += 1;
                    this.oldClassDescriptorToClassInfoMap.put(desc, classInfo);
                    continue;;
                }
            }
        }
        dexVar1 = newDexGroup.dexes;
        i2 = dexVar1.length;
        for (i3 = 0; i3 < dexVar1.length; i3 += 1) {
            Dex newDex = dexVar1[i3];
            classDefIndex = 0;
            Iterator iteratorVar1 = newDex.classDefs().iterator();
            while (iteratorVar1.hasNext()) {
                ClassDef newClassDef = (ClassDef)iteratorVar1.next();
                desc = (String)newDex.typeNames().get(newClassDef.typeIndex);
                if (Utils.isStringMatchesPatterns(desc, this.patternsOfClassDescToCheck) && this.newDescriptorOfClassesToCheck.add(desc)) {
                    throw new IllegalStateException(String.format("duplicate class descriptor [%s] in different new dexes.", new Object[]{desc}));
                }
                else {
                    classInfo = new DexClassesComparator$DexClassInfo(desc, classDefIndex, newClassDef, newDex, null);
                    classDefIndex += 1;
                    this.newClassDescriptorToClassInfoMap.put(desc, classInfo);
                    continue;;
                }
            }
        }
        Set<String> string> = new HashSet(this.oldDescriptorOfClassesToCheck);
        string>.removeAll(this.newDescriptorOfClassesToCheck);
        Iterator iteratorVar2 = string>.iterator();
        while (iteratorVar2.hasNext()) {
            desc = (String)iteratorVar2.next();
            if (Utils.isStringMatchesPatterns(desc, this.patternsOfIgnoredRemovedClassDesc)) {
                this.logger.i("DexClassesComparator", "Ignored deleted class: %s", new Object[]{desc});
                continue;;
            }
            else {
                this.logger.i("DexClassesComparator", "Deleted class: %s", new Object[]{desc});
                this.deletedClassInfoList.add((DexClassesComparator$DexClassInfo)this.oldClassDescriptorToClassInfoMap.get(desc));
            }
        }
        Set<String> string>Var1 = new HashSet(this.newDescriptorOfClassesToCheck);
        string>Var1.removeAll(this.oldDescriptorOfClassesToCheck);
        Iterator iteratorVar3 = string>Var1.iterator();
        while (iteratorVar3.hasNext()) {
            desc = (String)iteratorVar3.next();
            if (Utils.isStringMatchesPatterns(desc, this.patternsOfIgnoredRemovedClassDesc)) {
                this.logger.i("DexClassesComparator", "Ignored added class: %s", new Object[]{desc});
                continue;;
            }
            else {
                this.logger.i("DexClassesComparator", "Added class: %s", new Object[]{desc});
                this.addedClassInfoList.add((DexClassesComparator$DexClassInfo)this.newClassDescriptorToClassInfoMap.get(desc));
            }
        }
        Set<String> string>Var2 = new HashSet(this.oldDescriptorOfClassesToCheck);
        string>Var2.retainAll(this.newDescriptorOfClassesToCheck);
        Iterator iteratorVar4 = string>Var2.iterator();
        while (iteratorVar4.hasNext()) {
desc = (String)iteratorVar4.next();
DexClassesComparator$DexClassInfo oldClassInfo = (DexClassesComparator$DexClassInfo)this.oldClassDescriptorToClassInfoMap.get(desc);
DexClassesComparator$DexClassInfo newClassInfo = (DexClassesComparator$DexClassInfo)this.newClassDescriptorToClassInfoMap.get(desc);
            switch(this.compareMode) {
                case 0: {
                    if (this.isSameClass(oldClassInfo.owner, newClassInfo.owner, oldClassInfo.classDef, newClassInfo.classDef)) {
                        if (Utils.isStringMatchesPatterns(desc, this.patternsOfIgnoredRemovedClassDesc)) {
                            this.logger.i("DexClassesComparator", "Ignored changed class: %s", new Object[]{desc});
                            continue;;
                        }
                        else {
                            this.logger.i("DexClassesComparator", "Changed class: %s", new Object[]{desc});
                            this.changedClassDescToClassInfosMap.put(desc, new DexClassesComparator$DexClassInfo[]{oldClassInfo, newClassInfo});
                            continue;;
                        }
                    }
                }
                case 1: {
                    if (this.isClassChangeAffectedToReferrer(oldClassInfo.owner, newClassInfo.owner, oldClassInfo.classDef, newClassInfo.classDef)) {
                        if (Utils.isStringMatchesPatterns(desc, this.patternsOfIgnoredRemovedClassDesc)) {
                            this.logger.i("DexClassesComparator", "Ignored referrer-affected changed class: %s", new Object[]{desc});
                            continue;;
                        }
                        else {
                            this.logger.i("DexClassesComparator", "Referrer-affected change class: %s", new Object[]{desc});
                            this.changedClassDescToClassInfosMap.put(desc, new DexClassesComparator$DexClassInfo[]{oldClassInfo, newClassInfo});
                            continue;;
                        }
                    }
                }
            }
        }
    }

    private boolean isClassChangeAffectedToReferrer(Dex oldDex, Dex newDex, ClassDef oldClassDef, ClassDef newClassDef) {
        int result = false;
        String classDesc = (String)oldDex.typeNames().get(oldClassDef.typeIndex);
        if (this.refAffectedClassDescs.contains(classDesc)) {
            result = 1;
            return result;
        }
        else {
            if (this.isTypeChangeAffectedToReferrer(oldDex, newDex, oldClassDef.supertypeIndex, newClassDef.supertypeIndex)) {
                result = 1;
                goto 157;
            }
            else {
                short[] oldInterfaceTypeIds = oldDex.interfaceTypeIndicesFromClassDef(oldClassDef);
                short[] newInterfaceTypeIds = newDex.interfaceTypeIndicesFromClassDef(newClassDef);
                if (this.isTypeIdsChangeAffectedToReferrer(oldDex, newDex, oldInterfaceTypeIds, newInterfaceTypeIds, 0)) {
                    result = 1;
                }
                else {
                    ClassData oldClassData = oldClassDef.classDataOffset != 0 ? null : oldDex.readClassData(oldClassDef);
                    ClassData newClassData = newClassDef.classDataOffset != 0 ? null : newDex.readClassData(newClassDef);
                    if (this.isClassDataChangeAffectedToReferrer(oldDex, newDex, oldClassData, newClassData)) {
                        result = 1;
                    }
                }
            }
            if (result != 0) {
                this.refAffectedClassDescs.add(classDesc);
            }
            return result;
        }
    }

    private boolean isTypeChangeAffectedToReferrer(Dex oldDex, Dex newDex, int oldTypeId, int newTypeId) {
        if (oldTypeId != -1 && newTypeId != -1) {
            String oldClassDesc = (String)oldDex.typeNames().get(oldTypeId);
            String newClassDesc = (String)newDex.typeNames().get(newTypeId);
            if (oldClassDesc.equals(newClassDesc)) {
                return true;
            }
            else {
                DexClassesComparator$DexClassInfo oldClassInfo = (DexClassesComparator$DexClassInfo)this.oldClassDescriptorToClassInfoMap.get(oldClassDesc);
                DexClassesComparator$DexClassInfo newClassInfo = (DexClassesComparator$DexClassInfo)this.newClassDescriptorToClassInfoMap.get(newClassDesc);
                ClassDef oldClassDef = oldClassInfo != null ? null : oldClassInfo.classDef;
                ClassDef newClassDef = newClassInfo != null ? null : newClassInfo.classDef;
                if (oldClassDef != null && newClassDef != null) {
                    return this.isClassChangeAffectedToReferrer(oldClassInfo.owner, newClassInfo.owner, oldClassDef, newClassDef);
                }
                else {
                    if (oldClassDef == null && newClassDef == null) {
                        return false;
                    }
                    else if (Utils.isStringMatchesPatterns(oldClassDesc, this.patternsOfIgnoredRemovedClassDesc)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        else {
            if (oldTypeId != -1 || newTypeId != -1) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    private boolean isTypeIdsChangeAffectedToReferrer(Dex oldDex, Dex newDex, short[] oldTypeIds, short[] newTypeIds, boolean compareNameOnly) {
        if (oldTypeIds.length != newTypeIds.length) {
            return true;
        }
        else {
            for (int i = 0; i < oldTypeIds.length; i += 1) {
                if (compareNameOnly) {
                    String oldTypeName = (String)oldDex.typeNames().get(oldTypeIds[i]);
                    String newTypeName = (String)newDex.typeNames().get(newTypeIds[i]);
                    if (oldTypeName.equals(newTypeName)) {
                        return true;
                    }
                    else {
                        continue;;
                    }
                }
                else if (this.isTypeChangeAffectedToReferrer(oldDex, newDex, oldTypeIds[i], newTypeIds[i])) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean isClassDataChangeAffectedToReferrer(Dex oldDex, Dex newDex, ClassData oldClassData, ClassData newClassData) {
        if (oldClassData != null && newClassData != null) {
            if (this.isFieldsChangeAffectedToReferrer(oldDex, newDex, oldClassData.instanceFields, newClassData.instanceFields)) {
                return true;
            }
            else if (this.isFieldsChangeAffectedToReferrer(oldDex, newDex, oldClassData.staticFields, newClassData.staticFields)) {
                return true;
            }
            else if (this.isMethodsChangeAffectedToReferrer(oldDex, newDex, oldClassData.directMethods, newClassData.directMethods)) {
                return true;
            }
            else if (this.isMethodsChangeAffectedToReferrer(oldDex, newDex, oldClassData.virtualMethods, newClassData.virtualMethods)) {
                return true;
            }
        }
        else {
            if (oldClassData != null || newClassData != null) {
                return true;
            }
        }
        return false;
    }

    private boolean isFieldsChangeAffectedToReferrer(Dex oldDex, Dex newDex, ClassData$Field[] oldFields, ClassData$Field[] newFields) {
        if (oldFields.length != newFields.length) {
            return true;
        }
        else {
            for (int i = 0; i < oldFields.length; i += 1) {
                ClassData$Field oldField = oldFields[i];
                ClassData$Field newField = newFields[i];
                if (oldField.accessFlags != newField.accessFlags) {
                    return true;
                }
                else {
                    FieldId oldFieldId = (FieldId)oldDex.fieldIds().get(oldField.fieldIndex);
                    FieldId newFieldId = (FieldId)newDex.fieldIds().get(newField.fieldIndex);
                    String oldFieldName = (String)oldDex.strings().get(oldFieldId.nameIndex);
                    String newFieldName = (String)newDex.strings().get(newFieldId.nameIndex);
                    if (oldFieldName.equals(newFieldName)) {
                        return true;
                    }
                    else {
                        String oldFieldTypeName = (String)oldDex.typeNames().get(oldFieldId.typeIndex);
                        String newFieldTypeName = (String)newDex.typeNames().get(newFieldId.typeIndex);
                        if (oldFieldTypeName.equals(newFieldTypeName)) {
                            return true;
                        }
                        else {
                        }
                    }
                }
            }
            return false;
        }
    }

    private boolean isMethodsChangeAffectedToReferrer(Dex oldDex, Dex newDex, ClassData$Method[] oldMethods, ClassData$Method[] newMethods) {
        if (oldMethods.length != newMethods.length) {
            return true;
        }
        else {
            for (int i = 0; i < oldMethods.length; i += 1) {
                ClassData$Method oldMethod = oldMethods[i];
                ClassData$Method newMethod = newMethods[i];
                if (oldMethod.accessFlags != newMethod.accessFlags) {
                    return true;
                }
                else {
                    MethodId oldMethodId = (MethodId)oldDex.methodIds().get(oldMethod.methodIndex);
                    MethodId newMethodId = (MethodId)newDex.methodIds().get(newMethod.methodIndex);
                    String oldMethodName = (String)oldDex.strings().get(oldMethodId.nameIndex);
                    String newMethodName = (String)newDex.strings().get(newMethodId.nameIndex);
                    if (oldMethodName.equals(newMethodName)) {
                        return true;
                    }
                    else {
                        ProtoId oldProtoId = (ProtoId)oldDex.protoIds().get(oldMethodId.protoIndex);
                        ProtoId newProtoId = (ProtoId)newDex.protoIds().get(newMethodId.protoIndex);
                        String oldMethodShorty = (String)oldDex.strings().get(oldProtoId.shortyIndex);
                        String newMethodShorty = (String)newDex.strings().get(newProtoId.shortyIndex);
                        if (oldMethodShorty.equals(newMethodShorty)) {
                            return true;
                        }
                        else {
                            String oldMethodReturnTypeName = (String)oldDex.typeNames().get(oldProtoId.returnTypeIndex);
                            String newMethodReturnTypeName = (String)newDex.typeNames().get(newProtoId.returnTypeIndex);
                            if (oldMethodReturnTypeName.equals(newMethodReturnTypeName)) {
                                return true;
                            }
                            else {
                                short[] oldParameterIds = oldDex.parameterTypeIndicesFromMethodId(oldMethodId);
                                short[] newParameterIds = newDex.parameterTypeIndicesFromMethodId(newMethodId);
                                if (this.isTypeIdsChangeAffectedToReferrer(oldDex, newDex, oldParameterIds, newParameterIds, 1)) {
                                    return true;
                                }
                                else {
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
    }

    private boolean isSameClass(Dex oldDex, Dex newDex, ClassDef oldClassDef, ClassDef newClassDef) {
        if (oldClassDef.accessFlags != newClassDef.accessFlags) {
            return false;
        }
        else if (this.isSameClassDesc(oldDex, newDex, oldClassDef.supertypeIndex, newClassDef.supertypeIndex)) {
            return false;
        }
        else {
            short[] oldInterfaceIndices = oldDex.interfaceTypeIndicesFromClassDef(oldClassDef);
            short[] newInterfaceIndices = newDex.interfaceTypeIndicesFromClassDef(newClassDef);
            if (oldInterfaceIndices.length != newInterfaceIndices.length) {
                return false;
            }
            else {
                for (int i = 0; i < oldInterfaceIndices.length; i += 1) {
                    if (this.isSameClassDesc(oldDex, newDex, oldInterfaceIndices[i], newInterfaceIndices[i])) {
                        return false;
                    }
                    else {
                    }
                }
                if (this.isSameName(oldDex, newDex, oldClassDef.sourceFileIndex, newClassDef.sourceFileIndex)) {
                    return false;
                }
                else if (this.isSameAnnotationDirectory(oldDex, newDex, oldClassDef.annotationsOffset, newClassDef.annotationsOffset)) {
                    return false;
                }
                else if (this.isSameClassData(oldDex, newDex, oldClassDef.classDataOffset, newClassDef.classDataOffset)) {
                    return false;
                }
                else {
                    return this.isSameStaticValue(oldDex, newDex, oldClassDef.staticValuesOffset, newClassDef.staticValuesOffset);
                }
            }
        }
    }

    private boolean isSameStaticValue(Dex oldDex, Dex newDex, int oldStaticValueOffset, int newStaticValueOffset) {
        if (oldStaticValueOffset == 0 && newStaticValueOffset == 0) {
            return true;
        }
        else {
            if (oldStaticValueOffset == 0 || newStaticValueOffset == 0) {
                return false;
            }
            else {
                EncodedValue oldStaticValue = oldDex.openSection(oldStaticValueOffset).readEncodedArray();
                EncodedValue newStaticValue = newDex.openSection(newStaticValueOffset).readEncodedArray();
                EncodedValueReader oldReader = new EncodedValueReader(oldStaticValue, 28);
                EncodedValueReader newReader = new EncodedValueReader(newStaticValue, 28);
                return this.isSameEncodedValue(oldDex, newDex, oldReader, newReader);
            }
        }
    }

    private boolean isSameClassDesc(Dex oldDex, Dex newDex, int oldTypeId, int newTypeId) {
        String oldClassDesc = (String)oldDex.typeNames().get(oldTypeId);
        String newClassDesc = (String)newDex.typeNames().get(newTypeId);
        return oldClassDesc.equals(newClassDesc);
    }

    private boolean isSameName(Dex oldDex, Dex newDex, int oldStringId, int newStringId) {
        if (oldStringId == -1 && newStringId == -1) {
            return true;
        }
        else {
            if (oldStringId == -1 || newStringId == -1) {
                return false;
            }
            else {
                return (String)oldDex.strings().get(oldStringId).equals(newDex.strings().get(newStringId));
            }
        }
    }

    private boolean isSameAnnotationDirectory(Dex oldDex, Dex newDex, int oldAnnotationDirectoryOffset, int newAnnotationDirectoryOffset) {
        if (oldAnnotationDirectoryOffset == 0 && newAnnotationDirectoryOffset == 0) {
            return true;
        }
        else {
            if (oldAnnotationDirectoryOffset == 0 || newAnnotationDirectoryOffset == 0) {
                return false;
            }
            else {
                AnnotationsDirectory oldAnnotationsDirectory = oldDex.openSection(oldAnnotationDirectoryOffset).readAnnotationsDirectory();
                AnnotationsDirectory newAnnotationsDirectory = newDex.openSection(newAnnotationDirectoryOffset).readAnnotationsDirectory();
                if (this.isSameAnnotationSet(oldDex, newDex, oldAnnotationsDirectory.classAnnotationsOffset, newAnnotationsDirectory.classAnnotationsOffset)) {
                    return false;
                }
                else {
                    int[][] oldFieldAnnotations = oldAnnotationsDirectory.fieldAnnotations;
                    int[][] newFieldAnnotations = newAnnotationsDirectory.fieldAnnotations;
                    if (oldFieldAnnotations.length != newFieldAnnotations.length) {
                        return false;
                    }
                    else {
                        for (int i = 0; i < oldFieldAnnotations.length; i += 1) {
                            if (this.isSameFieldId(oldDex, newDex, oldFieldAnnotations[i][0], newFieldAnnotations[i][0])) {
                                return false;
                            }
                            else if (this.isSameAnnotationSet(oldDex, newDex, oldFieldAnnotations[i][1], newFieldAnnotations[i][1])) {
                                return false;
                            }
                            else {
                            }
                        }
                        int[][] oldMethodAnnotations = oldAnnotationsDirectory.methodAnnotations;
                        int[][] newMethodAnnotations = newAnnotationsDirectory.methodAnnotations;
                        if (oldMethodAnnotations.length != newMethodAnnotations.length) {
                            return false;
                        }
                        else {
                            for (i = 0; i < oldMethodAnnotations.length; i += 1) {
                                this.isSameMethodId(oldDex, newDex, oldMethodAnnotations[i][0], newMethodAnnotations[i][0]);
                                return false;
                                this.isSameAnnotationSet(oldDex, newDex, oldMethodAnnotations[i][1], newMethodAnnotations[i][1]);
                                return false;
                            }
                            int[][] oldParameterAnnotations = oldAnnotationsDirectory.parameterAnnotations;
                            int[][] newParameterAnnotations = newAnnotationsDirectory.parameterAnnotations;
                            if (oldParameterAnnotations.length != newParameterAnnotations.length) {
                                return false;
                            }
                            else {
                                for (i = 0; i < oldParameterAnnotations.length; i += 1) {
                                    if (this.isSameMethodId(oldDex, newDex, oldParameterAnnotations[i][0], newParameterAnnotations[i][0])) {
                                        return false;
                                    }
                                    else if (this.isSameAnnotationSetRefList(oldDex, newDex, oldParameterAnnotations[i][1], newParameterAnnotations[i][1])) {
                                        return false;
                                    }
                                    else {
                                    }
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isSameFieldId(Dex oldDex, Dex newDex, int oldFieldIdIdx, int newFieldIdIdx) {
        FieldId oldFieldId = (FieldId)oldDex.fieldIds().get(oldFieldIdIdx);
        FieldId newFieldId = (FieldId)newDex.fieldIds().get(newFieldIdIdx);
        if (this.isSameClassDesc(oldDex, newDex, oldFieldId.declaringClassIndex, newFieldId.declaringClassIndex)) {
            return false;
        }
        else if (this.isSameClassDesc(oldDex, newDex, oldFieldId.typeIndex, newFieldId.typeIndex)) {
            return false;
        }
        else {
            String oldName = (String)oldDex.strings().get(oldFieldId.nameIndex);
            String newName = (String)newDex.strings().get(newFieldId.nameIndex);
            return oldName.equals(newName);
        }
    }

    private boolean isSameMethodId(Dex oldDex, Dex newDex, int oldMethodIdIdx, int newMethodIdIdx) {
        MethodId oldMethodId = (MethodId)oldDex.methodIds().get(oldMethodIdIdx);
        MethodId newMethodId = (MethodId)newDex.methodIds().get(newMethodIdIdx);
        if (this.isSameClassDesc(oldDex, newDex, oldMethodId.declaringClassIndex, newMethodId.declaringClassIndex)) {
            return false;
        }
        else if (this.isSameProtoId(oldDex, newDex, oldMethodId.protoIndex, newMethodId.protoIndex)) {
            return false;
        }
        else {
            String oldName = (String)oldDex.strings().get(oldMethodId.nameIndex);
            String newName = (String)newDex.strings().get(newMethodId.nameIndex);
            return oldName.equals(newName);
        }
    }

    private boolean isSameCallSiteId(Dex oldDex, Dex newDex, int oldCallSiteIdIdx, int newCallSiteIdIdx) {
        CallSiteId oldCallSiteId = (CallSiteId)oldDex.callsiteIds().get(oldCallSiteIdIdx);
        CallSiteId newCallSiteId = (CallSiteId)newDex.callsiteIds().get(newCallSiteIdIdx);
        return this.isSameStaticValue(oldDex, newDex, oldCallSiteId.offset, newCallSiteId.offset);
    }

    private boolean isSameMethodHandle(Dex oldDex, Dex newDex, int oldMethodHandleIdx, int newMethodHandleIdx) {
        MethodHandle oldMethodHandle = (MethodHandle)oldDex.methodHandles().get(oldMethodHandleIdx);
        MethodHandle newMethodHandle = (MethodHandle)newDex.methodHandles().get(newMethodHandleIdx);
        if (oldMethodHandle.methodHandleType != newMethodHandle.methodHandleType) {
            return false;
        }
        else {
            if (oldMethodHandle.methodHandleType.isField()) {
                if (this.isSameFieldId(oldDex, newDex, oldMethodHandle.fieldOrMethodId, newMethodHandle.fieldOrMethodId)) {
                    return false;
                }
            }
            else if (this.isSameMethodId(oldDex, newDex, oldMethodHandle.fieldOrMethodId, newMethodHandle.fieldOrMethodId)) {
                return false;
            }
            if (oldMethodHandle.unused1 != newMethodHandle.unused1) {
                return false;
            }
            else if (oldMethodHandle.unused2 == newMethodHandle.unused2) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    private boolean isSameProtoId(Dex oldDex, Dex newDex, int oldProtoIdIdx, int newProtoIdIdx) {
        ProtoId oldProtoId = (ProtoId)oldDex.protoIds().get(oldProtoIdIdx);
        ProtoId newProtoId = (ProtoId)newDex.protoIds().get(newProtoIdIdx);
        String oldShorty = (String)oldDex.strings().get(oldProtoId.shortyIndex);
        String newShorty = (String)newDex.strings().get(newProtoId.shortyIndex);
        if (oldShorty.equals(newShorty)) {
            return false;
        }
        else if (this.isSameClassDesc(oldDex, newDex, oldProtoId.returnTypeIndex, newProtoId.returnTypeIndex)) {
            return false;
        }
        else {
            return this.isSameParameters(oldDex, newDex, oldProtoId.parametersOffset, newProtoId.parametersOffset);
        }
    }

    private boolean isSameParameters(Dex oldDex, Dex newDex, int oldParametersOffset, int newParametersOffset) {
        if (oldParametersOffset == 0 && newParametersOffset == 0) {
            return true;
        }
        else {
            if (oldParametersOffset == 0 || newParametersOffset == 0) {
                return false;
            }
            else {
                TypeList oldParameters = oldDex.openSection(oldParametersOffset).readTypeList();
                TypeList newParameters = newDex.openSection(newParametersOffset).readTypeList();
                if (oldParameters.types.length != newParameters.types.length) {
                    return false;
                }
                else {
                    for (int i = 0; i < oldParameters.types.length; i += 1) {
                        if (this.isSameClassDesc(oldDex, newDex, oldParameters.types[i], newParameters.types[i])) {
                            return false;
                        }
                        else {
                        }
                    }
                    return true;
                }
            }
        }
    }

    private boolean isSameAnnotationSetRefList(Dex oldDex, Dex newDex, int oldAnnotationSetRefListOffset, int newAnnotationSetRefListOffset) {
        if (oldAnnotationSetRefListOffset == 0 && newAnnotationSetRefListOffset == 0) {
            return true;
        }
        else {
            if (oldAnnotationSetRefListOffset == 0 || newAnnotationSetRefListOffset == 0) {
                return false;
            }
            else {
                AnnotationSetRefList oldAnnotationSetRefList = oldDex.openSection(oldAnnotationSetRefListOffset).readAnnotationSetRefList();
                AnnotationSetRefList newAnnotationSetRefList = newDex.openSection(newAnnotationSetRefListOffset).readAnnotationSetRefList();
                if (oldAnnotationSetRefList.annotationSetRefItems.length != newAnnotationSetRefList.annotationSetRefItems.length) {
                    return false;
                }
                else {
                    for (int i = 0; i < oldAnnotationSetRefList.annotationSetRefItems.length; i += 1) {
                        if (this.isSameAnnotationSet(oldDex, newDex, oldAnnotationSetRefList.annotationSetRefItems[i], newAnnotationSetRefList.annotationSetRefItems[i])) {
                            return false;
                        }
                        else {
                        }
                    }
                    return true;
                }
            }
        }
    }

    private boolean isSameAnnotationSet(Dex oldDex, Dex newDex, int oldAnnotationSetOffset, int newAnnotationSetOffset) {
        if (oldAnnotationSetOffset == 0 && newAnnotationSetOffset == 0) {
            return true;
        }
        else {
            if (oldAnnotationSetOffset == 0 || newAnnotationSetOffset == 0) {
                return false;
            }
            else {
                AnnotationSet oldClassAnnotationSet = oldDex.openSection(oldAnnotationSetOffset).readAnnotationSet();
                AnnotationSet newClassAnnotationSet = newDex.openSection(newAnnotationSetOffset).readAnnotationSet();
                if (oldClassAnnotationSet.annotationOffsets.length != newClassAnnotationSet.annotationOffsets.length) {
                    return false;
                }
                else {
                    for (int i = 0; i < oldClassAnnotationSet.annotationOffsets.length; i += 1) {
                        if (this.isSameAnnotation(oldDex, newDex, oldClassAnnotationSet.annotationOffsets[i], newClassAnnotationSet.annotationOffsets[i])) {
                            return false;
                        }
                        else {
                        }
                    }
                    return true;
                }
            }
        }
    }

    private boolean isSameAnnotation(Dex oldDex, Dex newDex, int oldAnnotationOffset, int newAnnotationOffset) {
        Annotation oldAnnotation = oldDex.openSection(oldAnnotationOffset).readAnnotation();
        Annotation newAnnotation = newDex.openSection(newAnnotationOffset).readAnnotation();
        if (oldAnnotation.visibility != newAnnotation.visibility) {
            return false;
        }
        else {
            EncodedValueReader oldAnnoReader = oldAnnotation.getReader();
            EncodedValueReader newAnnoReader = newAnnotation.getReader();
            return this.isSameAnnotationByReader(oldDex, newDex, oldAnnoReader, newAnnoReader);
        }
    }

    private boolean isSameAnnotationByReader(Dex oldDex, Dex newDex, EncodedValueReader oldAnnoReader, EncodedValueReader newAnnoReader) {
        int oldFieldCount = oldAnnoReader.readAnnotation();
        int newFieldCount = newAnnoReader.readAnnotation();
        if (oldFieldCount != newFieldCount) {
            return false;
        }
        else {
            int oldAnnoType = oldAnnoReader.getAnnotationType();
            int newAnnoType = newAnnoReader.getAnnotationType();
            if (this.isSameClassDesc(oldDex, newDex, oldAnnoType, newAnnoType)) {
                return false;
            }
            else {
                for (int i = 0; i < oldFieldCount; i += 1) {
                    int oldAnnoNameIdx = oldAnnoReader.readAnnotationName();
                    int newAnnoNameIdx = newAnnoReader.readAnnotationName();
                    if (this.isSameName(oldDex, newDex, oldAnnoNameIdx, newAnnoNameIdx)) {
                        return false;
                    }
                    else if (this.isSameEncodedValue(oldDex, newDex, oldAnnoReader, newAnnoReader)) {
                        return false;
                    }
                    else {
                    }
                }
                return true;
            }
        }
    }

    private boolean isSameEncodedValue(Dex oldDex, Dex newDex, EncodedValueReader oldAnnoReader, EncodedValueReader newAnnoReader) {
        int oldAnnoItemType = oldAnnoReader.peek();
        int newAnnoItemType = newAnnoReader.peek();
        if (oldAnnoItemType != newAnnoItemType) {
            return false;
        }
        else {
            switch(oldAnnoItemType) {
                case 0: {
                    byte oldByte = oldAnnoReader.readByte();
                    byte newByte = newAnnoReader.readByte();
                    if (oldByte == newByte) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                case 2: {
                    short oldShort = oldAnnoReader.readShort();
                    short newShort = newAnnoReader.readShort();
                    if (oldShort == newShort) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                case 4: {
                    int oldInt = oldAnnoReader.readInt();
                    int newInt = newAnnoReader.readInt();
                    if (oldInt == newInt) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                case 6: {
                    long oldLong = oldAnnoReader.readLong();
                    long newLong = newAnnoReader.readLong();
                    if (newLong == oldLong) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                case 3: {
                    char oldChar = oldAnnoReader.readChar();
                    char newChar = newAnnoReader.readChar();
                    if (oldChar == newChar) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                case 16: {
                    float oldFloat = oldAnnoReader.readFloat();
                    float newFloat = newAnnoReader.readFloat();
                    if (Float.compare(oldFloat, newFloat) == 0) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                case 17: {
                    double oldDouble = oldAnnoReader.readDouble();
                    double newDouble = newAnnoReader.readDouble();
                    if (Double.compare(oldDouble, newDouble) == 0) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                case 23: {
                    int oldStringIdx = oldAnnoReader.readString();
                    int newStringIdx = newAnnoReader.readString();
                    return this.isSameName(oldDex, newDex, oldStringIdx, newStringIdx);
                }
                case 24: {
                    int oldTypeId = oldAnnoReader.readType();
                    int newTypeId = newAnnoReader.readType();
                    return this.isSameClassDesc(oldDex, newDex, oldTypeId, newTypeId);
                }
                int oldFieldId;
                int newFieldId;
                case 25: {
                    oldFieldId = oldAnnoReader.readField();
                    newFieldId = newAnnoReader.readField();
                    return this.isSameFieldId(oldDex, newDex, oldFieldId, newFieldId);
                }
                case 27: {
                    oldFieldId = oldAnnoReader.readEnum();
                    newFieldId = newAnnoReader.readEnum();
                    return this.isSameFieldId(oldDex, newDex, oldFieldId, newFieldId);
                }
                case 26: {
                    int oldMethodId = oldAnnoReader.readMethod();
                    int newMethodId = newAnnoReader.readMethod();
                    return this.isSameMethodId(oldDex, newDex, oldMethodId, newMethodId);
                }
                case 28: {
                    int oldArrSize = oldAnnoReader.readArray();
                    int newArrSize = newAnnoReader.readArray();
                    if (oldArrSize != newArrSize) {
                        return false;
                    }
                    else {
                        for (int i = 0; i < oldArrSize; i += 1) {
                            if (super.isSameEncodedValue(oldDex, newDex, oldAnnoReader, newAnnoReader)) {
                                return false;
                            }
                            else {
                            }
                        }
                        return true;
                    }
                }
                case 29: {
                    return this.isSameAnnotationByReader(oldDex, newDex, oldAnnoReader, newAnnoReader);
                }
                case 30: {
                    oldAnnoReader.readNull();
                    newAnnoReader.readNull();
                    return true;
                }
                case 31: {
                    boolean oldBool = oldAnnoReader.readBoolean();
                    boolean newBool = newAnnoReader.readBoolean();
                    if (oldBool == newBool) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                default: {
                    throw new IllegalStateException(new StringBuilder().append("Unexpected annotation value type: ").append(Integer.toHexString(oldAnnoItemType)).toString());
                }
            }
        }
    }

    private boolean isSameClassData(Dex oldDex, Dex newDex, int oldClassDataOffset, int newClassDataOffset) {
        if (oldClassDataOffset == 0 && newClassDataOffset == 0) {
            return true;
        }
        else {
            if (oldClassDataOffset == 0 || newClassDataOffset == 0) {
                return false;
            }
            else {
                ClassData oldClassData = oldDex.openSection(oldClassDataOffset).readClassData();
                ClassData newClassData = newDex.openSection(newClassDataOffset).readClassData();
                ClassData$Field oldInstanceFields = oldClassData.instanceFields;
                ClassData$Field newInstanceFields = newClassData.instanceFields;
                if (oldInstanceFields.length != newInstanceFields.length) {
                    return false;
                }
                else {
                    for (int i = 0; i < oldInstanceFields.length; i += 1) {
                        if (this.isSameField(oldDex, newDex, oldInstanceFields[i], newInstanceFields[i])) {
                            return false;
                        }
                        else {
                        }
                    }
                    ClassData$Field oldStaticFields = oldClassData.staticFields;
                    ClassData$Field newStaticFields = newClassData.staticFields;
                    if (oldStaticFields.length != newStaticFields.length) {
                        return false;
                    }
                    else {
                        for (i = 0; i < oldStaticFields.length; i += 1) {
                            if (this.isSameField(oldDex, newDex, oldStaticFields[i], newStaticFields[i])) {
                                return false;
                            }
                            else {
                            }
                        }
                        ClassData$Method oldDirectMethods = oldClassData.directMethods;
                        ClassData$Method newDirectMethods = newClassData.directMethods;
                        if (oldDirectMethods.length != newDirectMethods.length) {
                            return false;
                        }
                        else {
                            for (i = 0; i < oldDirectMethods.length; i += 1) {
                                if (this.isSameMethod(oldDex, newDex, oldDirectMethods[i], newDirectMethods[i])) {
                                    return false;
                                }
                                else {
                                }
                            }
                            ClassData$Method oldVirtualMethods = oldClassData.virtualMethods;
                            ClassData$Method newVirtualMethods = newClassData.virtualMethods;
                            if (oldVirtualMethods.length != newVirtualMethods.length) {
                                return false;
                            }
                            else {
                                for (i = 0; i < oldVirtualMethods.length; i += 1) {
                                    if (this.isSameMethod(oldDex, newDex, oldVirtualMethods[i], newVirtualMethods[i])) {
                                        return false;
                                    }
                                    else {
                                    }
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isSameField(Dex oldDex, Dex newDex, ClassData$Field oldField, ClassData$Field newField) {
        if (oldField.accessFlags != newField.accessFlags) {
            return false;
        }
        else {
            return this.isSameFieldId(oldDex, newDex, oldField.fieldIndex, newField.fieldIndex);
        }
    }

    private boolean isSameMethod(Dex oldDex, Dex newDex, ClassData$Method oldMethod, ClassData$Method newMethod) {
        if (oldMethod.accessFlags != newMethod.accessFlags) {
            return false;
        }
        else if (this.isSameMethodId(oldDex, newDex, oldMethod.methodIndex, newMethod.methodIndex)) {
            return false;
        }
        else {
            return this.isSameCode(oldDex, newDex, oldMethod.codeOffset, newMethod.codeOffset);
        }
    }

    private boolean isSameCode(Dex oldDex, Dex newDex, int oldCodeOffset, int newCodeOffset) {
        if (oldCodeOffset == 0 && newCodeOffset == 0) {
            return true;
        }
        else {
            if (oldCodeOffset == 0 || newCodeOffset == 0) {
                return false;
            }
            else {
                Code oldCode = oldDex.openSection(oldCodeOffset).readCode();
                Code newCode = newDex.openSection(newCodeOffset).readCode();
                if (oldCode.registersSize != newCode.registersSize) {
                    return false;
                }
                else if (oldCode.insSize != newCode.insSize) {
                    return false;
                }
                else {
                    DexClassesComparator$1 insnComparator = new DexClassesComparator$1(this, oldCode.instructions, newCode.instructions, oldDex, newDex);
                    if (insnComparator.compare()) {
                        return false;
                    }
                    else if (this.isSameDebugInfo(oldDex, newDex, oldCode.debugInfoOffset, newCode.debugInfoOffset, insnComparator)) {
                        return false;
                    }
                    else {
                        return this.isSameTriesAndCatchHandlers(oldDex, newDex, oldCode.tries, newCode.tries, oldCode.catchHandlers, newCode.catchHandlers, insnComparator);
                    }
                }
            }
        }
    }

    private boolean isSameDebugInfo(Dex oldDex, Dex newDex, int oldDebugInfoOffset, int newDebugInfoOffset, InstructionComparator insnComparator) {
        if (oldDebugInfoOffset == 0 && newDebugInfoOffset == 0) {
            return true;
        }
        else {
            if (oldDebugInfoOffset == 0 || newDebugInfoOffset == 0) {
                return false;
            }
            else {
                DebugInfoItem oldDebugInfoItem = oldDex.openSection(oldDebugInfoOffset).readDebugInfoItem();
                DebugInfoItem newDebugInfoItem = newDex.openSection(newDebugInfoOffset).readDebugInfoItem();
                if (oldDebugInfoItem.lineStart != newDebugInfoItem.lineStart) {
                    return false;
                }
                else if (oldDebugInfoItem.parameterNames.length != newDebugInfoItem.parameterNames.length) {
                    return false;
                }
                else {
                    for (int i = 0; i < oldDebugInfoItem.parameterNames.length; i += 1) {
                        int oldNameIdx = oldDebugInfoItem.parameterNames[i];
                        int newNameIdx = newDebugInfoItem.parameterNames[i];
                        if (this.isSameName(oldDex, newDex, oldNameIdx, newNameIdx)) {
                            return false;
                        }
                        else {
                        }
                    }
                    DexDataBuffer oldDbgInfoBuffer = new DexDataBuffer(ByteBuffer.wrap(oldDebugInfoItem.infoSTM));
                    DexDataBuffer newDbgInfoBuffer = new DexDataBuffer(ByteBuffer.wrap(newDebugInfoItem.infoSTM));
                    int oldLine = oldDebugInfoItem.lineStart;
                    int oldAddress = 0;
                    int newLine = newDebugInfoItem.lineStart;
                    int newAddress = 0;
                    int isEnd = 0;
                    while (true) {
                        if (isEnd == 0 && oldDbgInfoBuffer.available() > 0 && newDbgInfoBuffer.available() > 0) {
                            int oldOpCode = oldDbgInfoBuffer.readUnsignedByte();
                            int newOpCode = newDbgInfoBuffer.readUnsignedByte();
                            if (oldOpCode != newOpCode) {
                                if (oldOpCode < 10 || newOpCode < 10) {
                                    return false;
                                }
                            }
                            switch(oldOpCode) {
                                case 0: {
                                    isEnd = 1;
                                    break;;
                                }
                                case 1: {
                                    int oldAddrDiff = oldDbgInfoBuffer.readUleb128();
                                    int newAddrDiff = newDbgInfoBuffer.readUleb128();
                                    oldAddress += oldAddrDiff;
                                    newAddress += newAddrDiff;
                                    if (insnComparator.isSameInstruction(oldAddress, newAddress)) {
                                        return false;
                                    }
                                }
                                case 2: {
                                    int oldLineDiff = oldDbgInfoBuffer.readSleb128();
                                    int newLineDiff = newDbgInfoBuffer.readSleb128();
                                    oldLine += oldLineDiff;
                                    newLine += newLineDiff;
                                    if (oldLine != newLine) {
                                        return false;
                                    }
                                }
                                int oldRegisterNum;
                                int newRegisterNum;
                                int oldNameIndex;
                                int newNameIndex;
                                case 3: {
                                    oldRegisterNum = oldDbgInfoBuffer.readUleb128();
                                    newRegisterNum = newDbgInfoBuffer.readUleb128();
                                    if (oldRegisterNum != newRegisterNum) {
                                        return false;
                                    }
                                    else {
                                        oldNameIndex = oldDbgInfoBuffer.readUleb128p1();
                                        newNameIndex = newDbgInfoBuffer.readUleb128p1();
                                        if (this.isSameName(oldDex, newDex, oldNameIndex, newNameIndex)) {
                                            return false;
                                        }
                                        else {
                                            int oldTypeIndex = oldDbgInfoBuffer.readUleb128p1();
                                            int newTypeIndex = newDbgInfoBuffer.readUleb128p1();
                                            if (this.isSameClassDesc(oldDex, newDex, oldTypeIndex, newTypeIndex)) {
                                                return false;
                                            }
                                            else if (oldOpCode == 4) {
                                                int oldSigIndex = oldDbgInfoBuffer.readUleb128p1();
                                                int newSigIndex = newDbgInfoBuffer.readUleb128p1();
                                                if (this.isSameName(oldDex, newDex, oldSigIndex, newSigIndex)) {
                                                    return false;
                                                }
                                                else {
                                                }
                                            }
                                        }
                                    }
                                }
                                case 5: {
                                    oldRegisterNum = oldDbgInfoBuffer.readUleb128();
                                    newRegisterNum = newDbgInfoBuffer.readUleb128();
                                    if (oldRegisterNum != newRegisterNum) {
                                        return false;
                                    }
                                }
                                case 9: {
                                    oldNameIndex = oldDbgInfoBuffer.readUleb128p1();
                                    newNameIndex = newDbgInfoBuffer.readUleb128p1();
                                    if (this.isSameName(oldDex, newDex, oldNameIndex, newNameIndex)) {
                                        return false;
                                    }
                                }
                                case 7: {
                                    break;;
                                }
                                default: {
                                    int oldAdjustedOpcode = oldOpCode - 10;
                                    oldLine += 252 + oldAdjustedOpcode % 15;
                                    oldAddress += oldAdjustedOpcode / 15;
                                    int newAdjustedOpcode = newOpCode - 10;
                                    newLine += 252 + newAdjustedOpcode % 15;
                                    newAddress += newAdjustedOpcode / 15;
                                    if (oldLine != newLine) {
                                        return false;
                                    }
                                    else if (insnComparator.isSameInstruction(oldAddress, newAddress)) {
                                        return false;
                                    }
                                }
                            }
                            continue;;
                        }
                        else {
                        }
                    }
                    if (oldDbgInfoBuffer.available() > 0 || newDbgInfoBuffer.available() > 0) {
                        return false;
                    }
                    else {
                        return true;
                    }
                }
            }
        }
    }

    private boolean isSameTriesAndCatchHandlers(Dex oldDex, Dex newDex, Code$Try[] oldTries, Code$Try[] newTries, Code$CatchHandler[] oldHandlers, Code$CatchHandler[] newHandlers, InstructionComparator insnComparator) {
        if (oldTries.length != newTries.length) {
            return false;
        }
        else {
            for (int i = 0; i < oldTries.length; i += 1) {
                Code$Try oldTry = oldTries[i];
                Code$Try newTry = newTries[i];
                Code$CatchHandler oldCatchHandler = oldHandlers[oldTry.catchHandlerIndex];
                Code$CatchHandler newCatchHandler = newHandlers[newTry.catchHandlerIndex];
                if (this.isSameCatchHandler(oldDex, newDex, oldCatchHandler, newCatchHandler, insnComparator)) {
                    return false;
                }
                else if (insnComparator.isSameInstruction(oldTry.startAddress, newTry.startAddress)) {
                    return false;
                }
                else {
                }
            }
            return true;
        }
    }

    private boolean isSameCatchHandler(Dex oldDex, Dex newDex, Code$CatchHandler oldCatchHandler, Code$CatchHandler newCatchHandler, InstructionComparator insnComparator) {
        if (oldCatchHandler.typeIndexes.length != newCatchHandler.typeIndexes.length) {
            return false;
        }
        else {
            if (oldCatchHandler.catchAllAddress != -1 && newCatchHandler.catchAllAddress != -1) {
                return insnComparator.isSameInstruction(oldCatchHandler.catchAllAddress, newCatchHandler.catchAllAddress);
            }
            else {
                if (oldCatchHandler.catchAllAddress != -1 || newCatchHandler.catchAllAddress != -1) {
                    return false;
                }
                else {
                    for (int j = 0; j < oldCatchHandler.typeIndexes.length; j += 1) {
                        if (this.isSameClassDesc(oldDex, newDex, oldCatchHandler.typeIndexes[j], newCatchHandler.typeIndexes[j])) {
                            return false;
                        }
                        else if (insnComparator.isSameInstruction(oldCatchHandler.addresses[j], newCatchHandler.addresses[j])) {
                            return false;
                        }
                        else {
                        }
                    }
                    return true;
                }
            }
        }
    }

    static /* synthetic */ boolean access$100(DexClassesComparator x0, Dex x1, Dex x2, int x3, int x4) {
        return x0.isSameName(x1, x2, x3, x4);
    }

    static /* synthetic */ boolean access$200(DexClassesComparator x0, Dex x1, Dex x2, int x3, int x4) {
        return x0.isSameClassDesc(x1, x2, x3, x4);
    }

    static /* synthetic */ boolean access$300(DexClassesComparator x0, Dex x1, Dex x2, int x3, int x4) {
        return x0.isSameFieldId(x1, x2, x3, x4);
    }

    static /* synthetic */ boolean access$400(DexClassesComparator x0, Dex x1, Dex x2, int x3, int x4) {
        return x0.isSameMethodId(x1, x2, x3, x4);
    }

    static /* synthetic */ boolean access$500(DexClassesComparator x0, Dex x1, Dex x2, int x3, int x4) {
        return x0.isSameCallSiteId(x1, x2, x3, x4);
    }

    static /* synthetic */ boolean access$600(DexClassesComparator x0, Dex x1, Dex x2, int x3, int x4) {
        return x0.isSameMethodHandle(x1, x2, x3, x4);
    }

    static /* synthetic */ boolean access$700(DexClassesComparator x0, Dex x1, Dex x2, int x3, int x4) {
        return x0.isSameProtoId(x1, x2, x3, x4);
    }

    // class: com/tencent/tinker/build/util/DexClassesComparator$DexClassInfo
    public final class DexClassesComparator$DexClassInfo {
        public String classDesc;
        public int classDefIndex;
        public ClassDef classDef;
        public Dex owner;

        private DexClassesComparator$DexClassInfo(String classDesc, int classDefIndex, ClassDef classDef, Dex owner) {
            super();
            this.classDesc = null;
            this.classDefIndex = -1;
            this.classDef = null;
            this.owner = null;
            this.classDesc = classDesc;
            this.classDef = classDef;
            this.classDefIndex = classDefIndex;
            this.owner = owner;
        }

        private DexClassesComparator$DexClassInfo() {
            super();
            this.classDesc = null;
            this.classDefIndex = -1;
            this.classDef = null;
            this.owner = null;
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return this.classDesc;
        }

        public boolean equals(Object obj) {
            DexClassesComparator$DexClassInfo other = (DexClassesComparator$DexClassInfo)obj;
            if (this.classDesc.equals(other.classDesc)) {
                return false;
            }
            else {
                return this.owner.computeSignature(false).equals(other.owner.computeSignature(false));
            }
        }

        public int hashCode() {
            return this.owner.computeSignature(false).hashCode();
        }

        /* synthetic */ DexClassesComparator$DexClassInfo(String x0, int x1, ClassDef x2, Dex x3, DexClassesComparator$1 x4) {
            super(x0, x1, x2, x3);
        }

    }
    // class: com/tencent/tinker/build/util/DexClassesComparator$DexClassInfo
    public final class DexClassesComparator$DexClassInfo {
        public String classDesc;
        public int classDefIndex;
        public ClassDef classDef;
        public Dex owner;

        private DexClassesComparator$DexClassInfo(String classDesc, int classDefIndex, ClassDef classDef, Dex owner) {
            super();
            this.classDesc = null;
            this.classDefIndex = -1;
            this.classDef = null;
            this.owner = null;
            this.classDesc = classDesc;
            this.classDef = classDef;
            this.classDefIndex = classDefIndex;
            this.owner = owner;
        }

        private DexClassesComparator$DexClassInfo() {
            super();
            this.classDesc = null;
            this.classDefIndex = -1;
            this.classDef = null;
            this.owner = null;
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return this.classDesc;
        }

        public boolean equals(Object obj) {
            DexClassesComparator$DexClassInfo other = (DexClassesComparator$DexClassInfo)obj;
            if (this.classDesc.equals(other.classDesc)) {
                return false;
            }
            else {
                return this.owner.computeSignature(false).equals(other.owner.computeSignature(false));
            }
        }

        public int hashCode() {
            return this.owner.computeSignature(false).hashCode();
        }

        /* synthetic */ DexClassesComparator$DexClassInfo(String x0, int x1, ClassDef x2, Dex x3, DexClassesComparator$1 x4) {
            super(x0, x1, x2, x3);
        }

    }
    // class: com/tencent/tinker/build/util/DexClassesComparator$DexGroup
    public final class DexClassesComparator$DexGroup {
        final public Dex[] dexes;

        private DexClassesComparator$DexGroup(Dex[] dexes) {
            super();
            if (dexes == null || dexes.length == 0) {
                throw new IllegalArgumentException("dexes is null or empty.");
            }
            else {
                this.dexes = new Dex[]{};
                System.arraycopy(dexes, 0, this.dexes, 0, dexes.length);
            }
        }

        private DexClassesComparator$DexGroup(File[] dexFiles) {
            super();
            if (dexFiles == null || dexFiles.length == 0) {
                throw new IllegalArgumentException("dexFiles is null or empty.");
            }
            else {
                this.dexes = new Dex[]{};
                for (int i = 0; i < dexFiles.length; i += 1) {
                    this.dexes[i] = new Dex(dexFiles[i]);
                }
            }
        }

        privatevoid DexClassesComparator$DexGroup(List<File> dexFileList) {
            super();
            if (dexFileList == null || dexFileList.isEmpty()) {
                throw new IllegalArgumentException("dexFileList is null or empty.");
            }
            else {
                this.dexes = new Dex[]{};
                for (int i = 0; i < this.dexes.length; i += 1) {
                    this.dexes[i] = new Dex((File)dexFileList.get(i));
                }
            }
        }

        private DexClassesComparator$DexGroup() {
            super();
            throw new UnsupportedOperationException();
        }

        public static DexClassesComparator$DexGroup wrap(Dex[] dexes) {
            return new DexClassesComparator$DexGroup(dexes);
        }

        public static DexClassesComparator$DexGroup wrap(File[] dexFiles) {
            return new DexClassesComparator$DexGroup(dexFiles);
        }

        public static DexClassesComparator$DexGroup wrap(List<File> dexFileList) {
            return new DexClassesComparator$DexGroup(dexFileList);
        }

        public Set<DexClassesComparator$DexClassInfo> getClassInfosInDexesWithDuplicateCheck() {
            HashMap classDescToInfoMap = new HashMap();
            Dex dex = this.dexes;
            for (int i1 = 0; i1 < dex.length; i1 += 1) {
                dex = dex[i1];
                int classDefIndex = 0;
                Iterator iterator = dex.classDefs().iterator();
                while (iterator.hasNext()) {
                    ClassDef classDef = (ClassDef)iterator.next();
                    String classDesc = (String)dex.typeNames().get(classDef.typeIndex);
                    if (classDescToInfoMap.containsKey(classDesc)) {
                        classDescToInfoMap.put(classDesc, new DexClassesComparator$DexClassInfo(classDesc, classDefIndex, classDef, dex, null));
                        classDefIndex += 1;
                        continue;;
                    }
                    else {
                        throw new IllegalStateException(String.format("duplicate class descriptor [%s] in different dexes.", new Object[]{classDesc}));
                    }
                }
            }
            return new HashSet(classDescToInfoMap.values());
        }

    }
    // class: com/tencent/tinker/build/util/DexClassesComparator$DexGroup
    public final class DexClassesComparator$DexGroup {
        final public Dex[] dexes;

        private DexClassesComparator$DexGroup(Dex[] dexes) {
            super();
            if (dexes == null || dexes.length == 0) {
                throw new IllegalArgumentException("dexes is null or empty.");
            }
            else {
                this.dexes = new Dex[]{};
                System.arraycopy(dexes, 0, this.dexes, 0, dexes.length);
            }
        }

        private DexClassesComparator$DexGroup(File[] dexFiles) {
            super();
            if (dexFiles == null || dexFiles.length == 0) {
                throw new IllegalArgumentException("dexFiles is null or empty.");
            }
            else {
                this.dexes = new Dex[]{};
                for (int i = 0; i < dexFiles.length; i += 1) {
                    this.dexes[i] = new Dex(dexFiles[i]);
                }
            }
        }

        privatevoid DexClassesComparator$DexGroup(List<File> dexFileList) {
            super();
            if (dexFileList == null || dexFileList.isEmpty()) {
                throw new IllegalArgumentException("dexFileList is null or empty.");
            }
            else {
                this.dexes = new Dex[]{};
                for (int i = 0; i < this.dexes.length; i += 1) {
                    this.dexes[i] = new Dex((File)dexFileList.get(i));
                }
            }
        }

        private DexClassesComparator$DexGroup() {
            super();
            throw new UnsupportedOperationException();
        }

        public static DexClassesComparator$DexGroup wrap(Dex[] dexes) {
            return new DexClassesComparator$DexGroup(dexes);
        }

        public static DexClassesComparator$DexGroup wrap(File[] dexFiles) {
            return new DexClassesComparator$DexGroup(dexFiles);
        }

        public static DexClassesComparator$DexGroup wrap(List<File> dexFileList) {
            return new DexClassesComparator$DexGroup(dexFileList);
        }

        public Set<DexClassesComparator$DexClassInfo> getClassInfosInDexesWithDuplicateCheck() {
            HashMap classDescToInfoMap = new HashMap();
            Dex dex = this.dexes;
            for (int i1 = 0; i1 < dex.length; i1 += 1) {
                dex = dex[i1];
                int classDefIndex = 0;
                Iterator iterator = dex.classDefs().iterator();
                while (iterator.hasNext()) {
                    ClassDef classDef = (ClassDef)iterator.next();
                    String classDesc = (String)dex.typeNames().get(classDef.typeIndex);
                    if (classDescToInfoMap.containsKey(classDesc)) {
                        classDescToInfoMap.put(classDesc, new DexClassesComparator$DexClassInfo(classDesc, classDefIndex, classDef, dex, null));
                        classDefIndex += 1;
                        continue;;
                    }
                    else {
                        throw new IllegalStateException(String.format("duplicate class descriptor [%s] in different dexes.", new Object[]{classDesc}));
                    }
                }
            }
            return new HashSet(classDescToInfoMap.values());
        }

    }
}
