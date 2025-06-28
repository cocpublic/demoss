/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/dexpatcher/util;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;
import com.tencent.tinker.commons.dexpatcher.DexPatcherLogger;
import com.tencent.tinker.commons.dexpatcher.DexPatcherLogger$IDexPatcherLogger;
import com.tencent.tinker.build.util.DexClassesComparator;
import com.tencent.tinker.build.util.DexClassesComparator$DexClassInfo;
import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.ClassData$Method;
import com.tencent.tinker.android.dex.Code;
import com.tencent.tinker.android.dex.FieldId;
import com.tencent.tinker.android.dex.MethodId;
import com.tencent.tinker.android.dex.ProtoId;
import com.tencent.tinker.android.dx.instruction.InstructionReader;
import com.tencent.tinker.android.dx.instruction.ShortArrayCodeInput;

// class: com/tencent/tinker/build/dexpatcher/util/ChangedClassesDexClassInfoCollector
public class ChangedClassesDexClassInfoCollector {
    final private static String TAG;
    final private static DexPatcherLogger LOGGER;
    final private Set<String> excludedClassPatterns;
    private boolean includeRefererToRefererAffectedClasses;

    public ChangedClassesDexClassInfoCollector() {
        super();
        this.excludedClassPatterns = new HashSet();
        this.includeRefererToRefererAffectedClasses = false;
    }

    public ChangedClassesDexClassInfoCollector setExcludedClassPatterns(Collection<String> loaderClassPatterns) {
        this.excludedClassPatterns.clear();
        this.excludedClassPatterns.addAll(loaderClassPatterns);
        return this;
    }

    public ChangedClassesDexClassInfoCollector clearExcludedClassPatterns() {
        this.excludedClassPatterns.clear();
        return this;
    }

    public ChangedClassesDexClassInfoCollector setLogger(DexPatcherLogger$IDexPatcherLogger loggerImpl) {
        ChangedClassesDexClassInfoCollector.LOGGER.setLoggerImpl(loggerImpl);
        return this;
    }

    public ChangedClassesDexClassInfoCollector setIncludeRefererToRefererAffectedClasses(boolean enabled) {
        this.includeRefererToRefererAffectedClasses = enabled;
        return this;
    }

    public Set<DexClassesComparator$DexClassInfo> doCollect(DexClassesComparator$DexGroup oldDexGroup, DexClassesComparator$DexGroup newDexGroup) {
        HashSet classDescsInResult = new HashSet();
        HashSet result = new HashSet();
        DexClassesComparator dexClassCmptor = new DexClassesComparator("*");
        dexClassCmptor.setCompareMode(0);
        dexClassCmptor.setIgnoredRemovedClassDescPattern(this.excludedClassPatterns);
        dexClassCmptor.setLogger(ChangedClassesDexClassInfoCollector.LOGGER.getLoggerImpl());
        dexClassCmptor.startCheck(oldDexGroup, newDexGroup);
        result.addAll(dexClassCmptor.getAddedClassInfos());
        Collection changedClassInfos = dexClassCmptor.getChangedClassDescToInfosMap().values();
        Iterator iteratorVar1 = changedClassInfos.iterator();
        while (iteratorVar1.hasNext()) {
            DexClassesComparator$DexClassInfo[] oldAndNewInfoPair = (DexClassesComparator$DexClassInfo[])iteratorVar1.next();
            DexClassesComparator$DexClassInfo newClassInfo = oldAndNewInfoPair[1];
            ChangedClassesDexClassInfoCollector.LOGGER.i("ChangedClassesDexClassInfoCollector", "Add class %s to changed classes dex.", new Object[]{newClassInfo.classDesc});
            result.add(newClassInfo);
        }
        iteratorVar1 = result.iterator();
        while (iteratorVar1.hasNext()) {
            DexClassesComparator$DexClassInfo classInfo = (DexClassesComparator$DexClassInfo)iteratorVar1.next();
            classDescsInResult.add(classInfo.classDesc);
        }
        if (this.includeRefererToRefererAffectedClasses) {
            dexClassCmptor.setCompareMode(1);
            dexClassCmptor.startCheck(oldDexGroup, newDexGroup);
            Set<String> string> = dexClassCmptor.getChangedClassDescToInfosMap().keySet();
            Set oldClassInfos = oldDexGroup.getClassInfosInDexesWithDuplicateCheck();
            Iterator iteratorVar2 = oldClassInfos.iterator();
            while (iteratorVar2.hasNext()) {
                DexClassesComparator$DexClassInfo oldClassInfo = (DexClassesComparator$DexClassInfo)iteratorVar2.next();
                if (classDescsInResult.contains(oldClassInfo.classDesc) && this.isClassReferToAnyClasses(oldClassInfo, string>)) {
                    ChangedClassesDexClassInfoCollector.LOGGER.i("ChangedClassesDexClassInfoCollector", "Add class %s in old dex to changed classes dex since it is affected by modified referee.", new Object[]{oldClassInfo.classDesc});
                    result.add(oldClassInfo);
                }
            }
        }
        return result;
    }

    private boolean isClassReferToAnyClasses(DexClassesComparator$DexClassInfo classInfo, Set<String> refereeClassDescs) {
        if (classInfo.classDef.classDataOffset == 0) {
            return false;
        }
        else {
            ClassData$Method method;
            ClassData classData = classInfo.owner.readClassData(classInfo.classDef);
            ClassData$Method methodVar1 = classData.directMethods;
            for (int i3 = 0; i3 < methodVar1.length; i3 += 1) {
                method = methodVar1[i3];
                if (this.isMethodReferToAnyClasses(classInfo, method, refereeClassDescs)) {
                    return true;
                }
                else {
                }
            }
            methodVar1 = classData.virtualMethods;
            i2 = methodVar1.length;
            for (i3 = 0; i3 < methodVar1.length; i3 += 1) {
                method = methodVar1[i3];
                if (this.isMethodReferToAnyClasses(classInfo, method, refereeClassDescs)) {
                    return true;
                }
                else {
                }
            }
            return false;
        }
    }

    private boolean isMethodReferToAnyClasses(DexClassesComparator$DexClassInfo classInfo, ClassData$Method method, Set<String> refereeClassDescs) {
        if (method.codeOffset == 0) {
            return false;
        }
        else {
            Code methodCode = classInfo.owner.readCode(method);
            InstructionReader ir = new InstructionReader(new ShortArrayCodeInput(methodCode.instructions));
            ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor rtcv = new ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor(classInfo.owner, method, refereeClassDescs);
            try {
                ir.accept(rtcv);
            }
            catch (EOFException var_7_0) {
            }
            return ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor.access$000(rtcv);
        }
    }

    static /* synthetic */ DexPatcherLogger access$100() {
        return ChangedClassesDexClassInfoCollector.LOGGER;
    }

    static  {
        ChangedClassesDexClassInfoCollector.LOGGER = new DexPatcherLogger();
    }

    // class: com/tencent/tinker/build/dexpatcher/util/ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor
    class ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor {
        final private Dex owner;
        final private ClassData$Method method;
        final private Collection<String> refereeClassDescs;
        private boolean isReferToAnyRefereeClasses;

        void ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor(Dex owner, ClassData$Method method, Collection<String> refereeClassDescs) {
            super(null);
            this.isReferToAnyRefereeClasses = false;
            this.owner = owner;
            this.method = method;
            this.refereeClassDescs = refereeClassDescs;
        }

        public void visitZeroRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal) {
            this.processIndexByType(index, indexType);
        }

        public void visitOneRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal) {
            this.processIndexByType(index, indexType);
        }

        public void visitTwoRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
            this.processIndexByType(index, indexType);
        }

        public void visitThreeRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b) {
            this.processIndexByType(index, indexType);
        }

        public void visitFourRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c) {
            this.processIndexByType(index, indexType);
        }

        public void visitFiveRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c, int d) {
            this.processIndexByType(index, indexType);
        }

        public void visitRegisterRangeInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
            this.processIndexByType(index, indexType);
        }

        private void processIndexByType(int index, int indexType) {
            MethodId methodId;
Object typeName = null;
Object refInfoInLog = null;
            switch(indexType) {
                String str0;
                String str1;
                case 2: {
                    str0 = (String)this.owner.typeNames().get(index);
                    str1 = "init referrer-affected class";
                    break;;
                }
                case 5: {
                    FieldId fieldId = (FieldId)this.owner.fieldIds().get(index);
                    str0 = (String)this.owner.typeNames().get(fieldId.declaringClassIndex);
                    str1 = new StringBuilder().append("referencing to field: ").append((String)this.owner.strings().get(fieldId.nameIndex)).toString();
                    break;;
                }
                case 4: {
                    methodId = (MethodId)this.owner.methodIds().get(index);
                    str0 = (String)this.owner.typeNames().get(methodId.declaringClassIndex);
                    str1 = new StringBuilder().append("invoking method: ").append(this.getMethodProtoTypeStr(methodId)).toString();
                    break;;
                }
            }
            if (typeName != null && this.refereeClassDescs.contains(typeName)) {
                methodId = (MethodId)this.owner.methodIds().get(this.method.methodIndex);
                ChangedClassesDexClassInfoCollector.access$100().i("ChangedClassesDexClassInfoCollector", "Method %s in class %s referenced referrer-affected class %s by %s", new Object[]{this.getMethodProtoTypeStr(methodId), this.owner.typeNames().get(methodId.declaringClassIndex), typeName, refInfoInLog});
                this.isReferToAnyRefereeClasses = true;
            }
        }

        private String getMethodProtoTypeStr(MethodId methodId) {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append((String)this.owner.strings().get(methodId.nameIndex));
            ProtoId protoId = (ProtoId)this.owner.protoIds().get(methodId.protoIndex);
            strBuilder.append(40);
            short[] paramTypeIds = this.owner.parameterTypeIndicesFromMethodId(methodId);
            for (int i1 = 0; i1 < paramTypeIds.length; i1 += 1) {
                short typeId = paramTypeIds[i1];
                strBuilder.append((String)this.owner.typeNames().get(typeId));
            }
            strBuilder.append(41).append((String)this.owner.typeNames().get(protoId.returnTypeIndex));
            return strBuilder.toString();
        }

        static /* synthetic */ boolean access$000(ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor x0) {
            return x0.isReferToAnyRefereeClasses;
        }

    }
    // class: com/tencent/tinker/build/dexpatcher/util/ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor
    class ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor {
        final private Dex owner;
        final private ClassData$Method method;
        final private Collection<String> refereeClassDescs;
        private boolean isReferToAnyRefereeClasses;

        void ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor(Dex owner, ClassData$Method method, Collection<String> refereeClassDescs) {
            super(null);
            this.isReferToAnyRefereeClasses = false;
            this.owner = owner;
            this.method = method;
            this.refereeClassDescs = refereeClassDescs;
        }

        public void visitZeroRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal) {
            this.processIndexByType(index, indexType);
        }

        public void visitOneRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal) {
            this.processIndexByType(index, indexType);
        }

        public void visitTwoRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
            this.processIndexByType(index, indexType);
        }

        public void visitThreeRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b) {
            this.processIndexByType(index, indexType);
        }

        public void visitFourRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c) {
            this.processIndexByType(index, indexType);
        }

        public void visitFiveRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c, int d) {
            this.processIndexByType(index, indexType);
        }

        public void visitRegisterRangeInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
            this.processIndexByType(index, indexType);
        }

        private void processIndexByType(int index, int indexType) {
            MethodId methodId;
Object typeName = null;
Object refInfoInLog = null;
            switch(indexType) {
                String str0;
                String str1;
                case 2: {
                    str0 = (String)this.owner.typeNames().get(index);
                    str1 = "init referrer-affected class";
                    break;;
                }
                case 5: {
                    FieldId fieldId = (FieldId)this.owner.fieldIds().get(index);
                    str0 = (String)this.owner.typeNames().get(fieldId.declaringClassIndex);
                    str1 = new StringBuilder().append("referencing to field: ").append((String)this.owner.strings().get(fieldId.nameIndex)).toString();
                    break;;
                }
                case 4: {
                    methodId = (MethodId)this.owner.methodIds().get(index);
                    str0 = (String)this.owner.typeNames().get(methodId.declaringClassIndex);
                    str1 = new StringBuilder().append("invoking method: ").append(this.getMethodProtoTypeStr(methodId)).toString();
                    break;;
                }
            }
            if (typeName != null && this.refereeClassDescs.contains(typeName)) {
                methodId = (MethodId)this.owner.methodIds().get(this.method.methodIndex);
                ChangedClassesDexClassInfoCollector.access$100().i("ChangedClassesDexClassInfoCollector", "Method %s in class %s referenced referrer-affected class %s by %s", new Object[]{this.getMethodProtoTypeStr(methodId), this.owner.typeNames().get(methodId.declaringClassIndex), typeName, refInfoInLog});
                this.isReferToAnyRefereeClasses = true;
            }
        }

        private String getMethodProtoTypeStr(MethodId methodId) {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append((String)this.owner.strings().get(methodId.nameIndex));
            ProtoId protoId = (ProtoId)this.owner.protoIds().get(methodId.protoIndex);
            strBuilder.append(40);
            short[] paramTypeIds = this.owner.parameterTypeIndicesFromMethodId(methodId);
            for (int i1 = 0; i1 < paramTypeIds.length; i1 += 1) {
                short typeId = paramTypeIds[i1];
                strBuilder.append((String)this.owner.typeNames().get(typeId));
            }
            strBuilder.append(41).append((String)this.owner.typeNames().get(protoId.returnTypeIndex));
            return strBuilder.toString();
        }

        static /* synthetic */ boolean access$000(ChangedClassesDexClassInfoCollector$ReferToClassesCheckVisitor x0) {
            return x0.isReferToAnyRefereeClasses;
        }

    }
}
