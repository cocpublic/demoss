/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/decoder;

import com.tencent.tinker.build.info.InfoWriter;
import com.tencent.tinker.build.util.ExcludedClassModifiedChecker;
import com.tencent.tinker.build.util.TinkerPatchException;
import com.tencent.tinker.build.util.DexClassesComparator$DexGroup;
import com.tencent.tinker.build.util.DexClassesComparator$DexClassInfo;
import com.tencent.tinker.build.util.DexClassesComparator;
import com.tencent.tinker.build.patch.Configuration;
import com.tencent.tinker.build.dexpatcher.util.ChangedClassesDexClassInfoCollector;
import com.tencent.tinker.build.dexpatcher.DexPatchGenerator;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.commons.dexpatcher.DexPatchApplier;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.List;
import java.util.AbstractMap$SimpleEntry;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.nio.file.Path;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.Field;
import org.jf.dexlib2.iface.Method;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.instruction.Instruction;
import org.jf.dexlib2.iface.instruction.ReferenceInstruction;
import org.jf.dexlib2.iface.reference.Reference;
import org.jf.dexlib2.iface.reference.MethodReference;
import org.jf.dexlib2.iface.reference.FieldReference;
import org.jf.dexlib2.iface.reference.TypeReference;
import org.jf.dexlib2.iface.value.EncodedValue;
import org.jf.dexlib2.AccessFlags;
import org.jf.dexlib2.writer.builder.DexBuilder;
import org.jf.dexlib2.writer.builder.BuilderField;
import org.jf.dexlib2.writer.builder.BuilderMethod;
import org.jf.dexlib2.writer.builder.BuilderClassDef;
import org.jf.dexlib2.writer.io.FileDataStore;
import org.jf.dexlib2.builder.BuilderMutableMethodImplementation;

// class: com/tencent/tinker/build/decoder/DexDiffDecoder
public class DexDiffDecoder {
    final private static String TEST_DEX_NAME;
    final private static String CHANGED_CLASSES_DEX_NAME_PREFIX;
    final private InfoWriter logWriter;
    final private InfoWriter metaWriter;
    final private ExcludedClassModifiedChecker excludedClassModifiedChecker;
    final private Map<String, String> addedClassDescToDexNameMap;
    final private Map<String, String> deletedClassDescToDexNameMap;
    final private List<AbstractMap$SimpleEntry<File, File>> oldAndNewDexFilePairList;
    final private Map<String, DexDiffDecoder$RelatedInfo> dexNameToRelatedInfoMap;
    private boolean hasDexChanged;
    private DexDiffDecoder$DexPatcherLoggerBridge dexPatcherLoggerBridge;
    final private Set<Pattern> loaderClassPatterns;
    final private Set<String> descOfClassesInApk;
    final private Set<String> descOfSyntheticClassesInApk;
    final private List<File> oldDexFiles;

    public DexDiffDecoder(Configuration config, String metaPath, String logPath) {
        super(config);
        this.hasDexChanged = false;
        this.dexPatcherLoggerBridge = null;
        this.metaWriter = metaPath != null ? null : new InfoWriter(config, new StringBuilder().append(config.mTempResultDir).append(File.separator).append(metaPath).toString());
        this.logWriter = logPath != null ? null : new InfoWriter(config, new StringBuilder().append(config.mOutFolder).append(File.separator).append(logPath).toString());
        if (this.logWriter != null) {
            this.dexPatcherLoggerBridge = new DexDiffDecoder$DexPatcherLoggerBridge(this, this.logWriter);
        }
        this.excludedClassModifiedChecker = new ExcludedClassModifiedChecker(config);
        this.addedClassDescToDexNameMap = new HashMap();
        this.deletedClassDescToDexNameMap = new HashMap();
        this.oldAndNewDexFilePairList = new ArrayList();
        this.dexNameToRelatedInfoMap = new HashMap();
        this.loaderClassPatterns = new HashSet();
        Iterator iterator = config.mDexLoaderPattern.iterator();
        while (iterator.hasNext()) {
            String patternStr = (String)iterator.next();
            this.loaderClassPatterns.add(Pattern.compile(PatternUtils.dotClassNamePatternToDescriptorRegEx(patternStr)));
        }
        this.descOfClassesInApk = new HashSet();
        this.descOfSyntheticClassesInApk = new HashSet();
        this.oldDexFiles = new ArrayList();
    }

    public void onAllPatchesStart() {
        this.descOfClassesInApk.clear();
        this.descOfSyntheticClassesInApk.clear();
        this.oldDexFiles.clear();
    }

    protected String getRelativeDexName(File oldDexFile, File newDexFile) {
        if (oldDexFile != null) {
            return this.getRelativePathStringToOldFile(oldDexFile);
        }
        else {
            return this.getRelativePathStringToNewFile(newDexFile);
        }
    }

    private void collectClassesInDex(File dexFile) {
        Logger.d(new StringBuilder().append("Collect class descriptors in ").append(dexFile.getName()).toString());
        DexBackedDexFile dex = DexFileFactory.loadDexFile(dexFile, Opcodes.forApi(29));
        Iterator iterator = dex.getClasses().iterator();
        while (iterator.hasNext()) {
            ClassDef classDef = (ClassDef)iterator.next();
            this.descOfClassesInApk.add(classDef.getType());
            if (AccessFlags.SYNTHETIC.isSet(classDef.getAccessFlags())) {
                this.descOfSyntheticClassesInApk.add(classDef.getType());
            }
        }
    }

    public boolean patch(File oldFile, File newFile) {
        String dexName = this.getRelativeDexName(oldFile, newFile);
        Logger.d("Check for loader classes in dex: %s", new Object[]{dexName});
        try {
            this.excludedClassModifiedChecker.checkIfExcludedClassWasModifiedInNewDex(oldFile, newFile);
        }
        catch (IOException e) {
            throw new TinkerPatchException(e);
        }
        catch (TinkerPatchException e) {
            if (this.config.mIgnoreWarning) {
                Logger.e("Warning:ignoreWarning is true, but we found %s", new Object[]{e.getMessage()});
            }
            else {
                Logger.e("Warning:ignoreWarning is false, but we found %s", new Object[]{e.getMessage()});
                throw e;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (newFile != null || newFile.exists() || 0L == newFile.length()) {
            return false;
        }
        else {
            File dexDiffOut = this.getOutputPath(newFile).toFile();
            String newMd5 = this.getRawOrWrappedDexMD5(newFile);
            if (oldFile != null || oldFile.exists() || 0L == oldFile.length()) {
                this.hasDexChanged = true;
                this.copyNewDexAndLogToDexMeta(newFile, newMd5, dexDiffOut);
                return true;
            }
            else {
                this.collectClassesInDex(oldFile);
                this.oldDexFiles.add(oldFile);
                String oldMd5 = this.getRawOrWrappedDexMD5(oldFile);
                if (oldMd5 == null || oldMd5.equals(newMd5)) {
                    if (oldMd5 == null && newMd5 != null) {
                    }
                }
                this.hasDexChanged = true;
                if (oldMd5 != null) {
                    this.collectAddedOrDeletedClasses(oldFile, newFile);
                }
                DexDiffDecoder$RelatedInfo relatedInfo = new DexDiffDecoder$RelatedInfo(this, null);
                relatedInfo.oldMd5 = oldMd5;
                relatedInfo.newMd5 = newMd5;
                this.oldAndNewDexFilePairList.add(new AbstractMap$SimpleEntry(oldFile, newFile));
                this.dexNameToRelatedInfoMap.put(dexName, relatedInfo);
                return true;
            }
        }
    }

    public void onAllPatchesEnd() {
        if (this.hasDexChanged) {
            Logger.d("No dexes were changed, nothing needs to be done next.");
        }
        else {
            this.checkIfLoaderClassesReferToNonLoaderClasses();
            if (this.config.mIsProtectedApp) {
                this.generateChangedClassesDexFile();
            }
            else {
                this.generatePatchInfoFile();
            }
            this.addTestDex();
        }
    }

    private boolean isReferenceFromLoaderClassValid(String refereeTypeDesc) {
        if (TypeUtils.isPrimitiveType(refereeTypeDesc)) {
            return true;
        }
        else if (this.descOfClassesInApk.contains(refereeTypeDesc)) {
            return true;
        }
        else if (Utils.isStringMatchesPatterns(refereeTypeDesc, this.loaderClassPatterns)) {
            return true;
        }
        else if (this.descOfSyntheticClassesInApk.contains(refereeTypeDesc)) {
            return true;
        }
        else {
            return false;
        }
    }

    private void checkIfLoaderClassesReferToNonLoaderClasses() {
        int hasInvalidCases = 0;
        Iterator iterator = this.oldDexFiles.iterator();
        while (iterator.hasNext()) {
            File dexFile = (File)iterator.next();
            Logger.d(new StringBuilder().append("Check if loader classes in ").append(dexFile.getName()).append(" refer to any classes that is not in loader class patterns.").toString());
            DexBackedDexFile dex = DexFileFactory.loadDexFile(dexFile, Opcodes.forApi(29));
            Iterator iteratorVar1 = dex.getClasses().iterator();
            while (iteratorVar1.hasNext()) {
                ClassDef classDef = (ClassDef)iteratorVar1.next();
                String currClassDesc = classDef.getType();
                if (Utils.isStringMatchesPatterns(currClassDesc, this.loaderClassPatterns)) {
                    continue;;
                }
                else {
                    Iterator iteratorVar3 = classDef.getFields().iterator();
                    while (iteratorVar3.hasNext()) {
                        Field field = (Field)iteratorVar3.next();
                        String currFieldTypeDesc = field.getType();
                        if (this.isReferenceFromLoaderClassValid(currFieldTypeDesc)) {
                            Logger.e("FATAL: field '%s' in loader class '%s' refers to class '%s' which is not loader class, this may cause crash when patch is loaded.", new Object[]{field.getName(), currClassDesc, currFieldTypeDesc});
                            hasInvalidCases = 1;
                        }
                    }
                    iteratorVar3 = classDef.getMethods().iterator();
                    while (iteratorVar3.hasNext()) {
                        Method method = (Method)iteratorVar3.next();
                        int isCurrentMethodInvalid = 0;
                        String currMethodRetTypeDesc = method.getReturnType();
                        if (this.isReferenceFromLoaderClassValid(currMethodRetTypeDesc)) {
                            Logger.e("FATAL: method '%s:%s' in loader class '%s' refers to class '%s' which is not loader class, this may cause crash when patch is loaded.", new Object[]{method.getName(), MethodUtil.getShorty(method.getParameterTypes(), currMethodRetTypeDesc), currClassDesc, currMethodRetTypeDesc});
                            isCurrentMethodInvalid = 1;
                            goto 430;
                        }
                        else {
                            Iterator iteratorVar4 = method.getParameterTypes().iterator();
                            while (iteratorVar4.hasNext()) {
                                CharSequence paramTypeDesc = (CharSequence)iteratorVar4.next();
                                this.isReferenceFromLoaderClassValid(paramTypeDesc.toString());
                                Logger.e("FATAL: method '%s:%s' in loader class '%s' refers to class '%s' which is not loader class, this may cause crash when patch is loaded.", new Object[]{method.getName(), MethodUtil.getShorty(method.getParameterTypes(), currMethodRetTypeDesc), currClassDesc, paramTypeDesc});
                                isCurrentMethodInvalid = 1;
                                break;;
                            }
                        }
                        MethodImplementation methodImpl = method.getImplementation();
                        if (methodImpl == null) {
                            goto 861;
                        }
                        else {
                            Iterable insns = methodImpl.getInstructions();
                            if (insns.iterator().hasNext()) {
                            }
                            else {
                                Iterator iteratorVar5 = insns.iterator();
                                while (iteratorVar5.hasNext()) {
                                    Instruction insn = (Instruction)iteratorVar5.next();
                                    (insn instanceof ReferenceInstruction);
                                    ReferenceInstruction refInsn = (ReferenceInstruction)insn;
                                    refInsn.getReferenceType();
                                    TypeReference typeRefInsn = (TypeReference)refInsn.getReference();
                                    String refereeTypeDesc = typeRefInsn.getType();
                                    this.isReferenceFromLoaderClassValid(refereeTypeDesc);
                                    continue;;
                                    Logger.e("FATAL: method '%s:%s' in loader class '%s' refers to class '%s' which is not loader class, this may cause crash when patch is loaded.", new Object[]{method.getName(), MethodUtil.getShorty(method.getParameterTypes(), currMethodRetTypeDesc), currClassDesc, refereeTypeDesc});
                                    isCurrentMethodInvalid = 1;
                                    continue;;
                                    FieldReference fieldRefInsn = (FieldReference)refInsn.getReference();
                                    String refereeFieldName = fieldRefInsn.getName();
                                    String refereeFieldDefTypeDesc = fieldRefInsn.getDefiningClass();
                                    this.isReferenceFromLoaderClassValid(refereeFieldDefTypeDesc);
                                    continue;;
                                    Logger.e("FATAL: method '%s:%s' in loader class '%s' refers to field '%s' in class '%s' which is not in loader class, this may cause crash when patch is loaded.", new Object[]{method.getName(), MethodUtil.getShorty(method.getParameterTypes(), currMethodRetTypeDesc), currClassDesc, refereeFieldName, refereeFieldDefTypeDesc});
                                    isCurrentMethodInvalid = 1;
                                    continue;;
                                    MethodReference methodRefInsn = (MethodReference)refInsn.getReference();
                                    String refereeMethodName = methodRefInsn.getName();
                                    List refereeMethodParamTypes = methodRefInsn.getParameterTypes();
                                    String refereeMethodRetType = methodRefInsn.getReturnType();
                                    String refereeMethodDefClassDesc = methodRefInsn.getDefiningClass();
                                    this.isReferenceFromLoaderClassValid(refereeMethodDefClassDesc);
                                    continue;;
                                    Logger.e("FATAL: method '%s:%s' in loader class '%s' refers to method '%s:%s' in class '%s' which is not in loader class, this may cause crash when patch is loaded.", new Object[]{method.getName(), MethodUtil.getShorty(method.getParameterTypes(), currMethodRetTypeDesc), currClassDesc, refereeMethodName, MethodUtil.getShorty(refereeMethodParamTypes, refereeMethodRetType), refereeMethodDefClassDesc});
                                    isCurrentMethodInvalid = 1;
                                    continue;;
                                }
                            }
                        }
                        if (isCurrentMethodInvalid != 0) {
                            hasInvalidCases = 1;
                        }
                    }
                    continue;;
                }
            }
        }
        if (hasInvalidCases != 0) {
            throw new TinkerPatchException("There are fatal reasons that cause Tinker interrupt patch generating procedure, see logs above.");
        }
        else {
        }
    }

    private void generateChangedClassesDexFile() {
        String dexMode = this.config.mDexRaw ? "jar" : "raw";
        ArrayList oldDexList = new ArrayList();
        ArrayList newDexList = new ArrayList();
        Iterator iterator = this.oldAndNewDexFilePairList.iterator();
        while (iterator.hasNext()) {
            AbstractMap$SimpleEntry oldAndNewDexFilePair = (AbstractMap$SimpleEntry)iterator.next();
            File oldDexFile = (File)oldAndNewDexFilePair.getKey();
            File newDexFile = (File)oldAndNewDexFilePair.getValue();
            if (oldDexFile != null) {
                oldDexList.add(oldDexFile);
            }
            if (newDexFile != null) {
                newDexList.add(newDexFile);
            }
        }
        DexClassesComparator$DexGroup group = DexClassesComparator$DexGroup.wrap(oldDexList);
        DexClassesComparator$DexGroup newDexGroup = DexClassesComparator$DexGroup.wrap(newDexList);
        ChangedClassesDexClassInfoCollector collector = new ChangedClassesDexClassInfoCollector();
        collector.setExcludedClassPatterns(this.config.mDexLoaderPattern);
        collector.setLogger(this.dexPatcherLoggerBridge);
        collector.setIncludeRefererToRefererAffectedClasses(true);
        Set classInfosInChangedClassesDex = collector.doCollect(group, newDexGroup);
        HashSet owners = new HashSet();
        HashMap ownerToDescOfChangedClassesMap = new HashMap();
        Iterator iteratorVar1 = classInfosInChangedClassesDex.iterator();
        while (iteratorVar1.hasNext()) {
            DexClassesComparator$DexClassInfo classInfo = (DexClassesComparator$DexClassInfo)iteratorVar1.next();
            owners.add(classInfo.owner);
            Set descOfChangedClasses = (Set)ownerToDescOfChangedClassesMap.get(classInfo.owner);
            descOfChangedClasses == null;
            HashSet set = new HashSet();
            ownerToDescOfChangedClassesMap.put(classInfo.owner, set);
            descOfChangedClasses.add(classInfo.classDesc);
        }
        StringBuilder builder = new StringBuilder();
        int changedDexId = 1;
        for (v_84 = iteratorVar2) {
            ClassDef classDef;
            Iterator iteratorVar2 = owners.iterator(); iteratorVar2.hasNext(); changedDexId += 1;
            Dex dex = (Dex)iteratorVar2.next();
            int dexApi = dex.getTableOfContents().api;
            Logger.d(new StringBuilder().append("generateChangedClassesDexFile, dexApi = ").append(dexApi).toString());
            Set descOfChangedClassesInCurrDex = (Set)ownerToDescOfChangedClassesMap.get(dex);
            DexBackedDexFile dexFile = new DexBackedDexFile(Opcodes.forApi(dexApi), dex.getBytes());
            int dexFileApi = dexFile.getOpcodes().api;
            int dexFileArtVersion = dexFile.getOpcodes().artVersion;
            Logger.d(new StringBuilder().append("generateChangedClassesDexFile dexFileApi = ").append(dexFileApi).append(", dexFileArtVersion = ").append(dexFileArtVersion).toString());
            int isCurrentDexHasChangedClass = 0;
            Iterator iteratorVar3 = dexFile.getClasses().iterator();
            while (iteratorVar3.hasNext()) {
                classDef = (ClassDef)iteratorVar3.next();
                if (descOfChangedClassesInCurrDex.contains(classDef.getType())) {
                    isCurrentDexHasChangedClass = 1;
                    break;;
                }
                else {
                    continue;;
                }
            }
            if (isCurrentDexHasChangedClass == 0) {
                continue;;
            }
            else {
                builder = new DexBuilder(Opcodes.forApi(dexApi));
                Iterator iteratorVar4 = dexFile.getClasses().iterator();
                while (iteratorVar4.hasNext()) {
                    classDef = (ClassDef)iteratorVar4.next();
                    if (descOfChangedClassesInCurrDex.contains(classDef.getType())) {
                        continue;;
                    }
                    else {
                        Logger.d("Class %s will be added into changed classes dex ...", new Object[]{classDef.getType()});
                        ArrayList builderFields = new ArrayList();
                        Iterator iteratorVar5 = classDef.getFields().iterator();
                        while (iteratorVar5.hasNext()) {
                            Field field = (Field)iteratorVar5.next();
                            BuilderField builderField = builder.internField(field.getDefiningClass(), field.getName(), field.getType(), field.getAccessFlags(), field.getInitialValue(), field.getAnnotations(), field.getHiddenApiRestrictions());
                            builderFields.add(builderField);
                        }
                        List<BuilderMethod> method> = new ArrayList();
                        Iterator iteratorVar6 = classDef.getMethods().iterator();
                        while (iteratorVar6.hasNext()) {
                            Method method = (Method)iteratorVar6.next();
                            MethodImplementation methodImpl = method.getImplementation();
                            if (methodImpl != null) {
                                BuilderMutableMethodImplementation implementation = new BuilderMutableMethodImplementation(builder, methodImpl);
                            }
                            BuilderMethod builderMethod = builder.internMethod(method.getDefiningClass(), method.getName(), method.getParameters(), method.getReturnType(), method.getAccessFlags(), method.getAnnotations(), method.getHiddenApiRestrictions(), methodImpl);
                            method>.add(builderMethod);
                        }
                        builder.internClassDef(classDef.getType(), classDef.getAccessFlags(), classDef.getSuperclass(), classDef.getInterfaces(), classDef.getSourceFile(), classDef.getAnnotations(), builderFields, method>);
                        continue;;
                    }
                }
                String str1 = null;
                str1 = changedDexId == 1 ? new StringBuilder().append("classes").append(changedDexId).append(".dex").toString() : "classes.dex";
                File dest = new File(new StringBuilder().append(this.config.mTempResultDir).append("/").append(str1).toString());
                FileDataStore fileDataStore = new FileDataStore(dest);
                builder.writeTo(fileDataStore);
                String md5 = MD5.getMD5(dest);
                this.appendMetaLine(builder, new Object[]{str1, "", md5, md5, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), dexMode});
            }
        }
        String str2 = builder.toString();
        Logger.d("
DexDecoder:write changed classes dex meta file data:
%s", new Object[]{str2});
        this.metaWriter.writeLineToInfoFile(str2);
    }

    private void appendMetaLine(StringBuilder sb, Object[] vals) {
        if (vals == null || vals.length == 0) {
        }
        else {
            int isFirstItem = 1;
            for (int i1 = 0; i1 < vals.length; i1 += 1) {
                Object val = vals[i1];
                if (isFirstItem != 0) {
                    isFirstItem = 0;
                    continue;;
                }
                else {
                    sb.append(44);
                }
                sb.append(val);
            }
            sb.append(10);
        }
    }

    private void generatePatchInfoFile() {
        this.generatePatchedDexInfoFile();
        this.logDexesToDexMeta();
        this.checkCrossDexMovingClasses();
    }

    private void logDexesToDexMeta() {
        File oldDexFile;
        String dexName;
        AbstractMap$SimpleEntry oldAndNewDexFilePair;
        HashMap dexNameToClassNOldDexFileMap = new HashMap();
        HashSet realClassNDexFiles = new HashSet();
        Iterator iterator = this.oldAndNewDexFilePairList.iterator();
        while (iterator.hasNext()) {
            oldAndNewDexFilePair = (AbstractMap$SimpleEntry)iterator.next();
            File oldFile = (File)oldAndNewDexFilePair.getKey();
            dexName = this.getRelativeDexName(oldFile, null);
            if (this.isDexNameMatchesClassNPattern(dexName)) {
                dexNameToClassNOldDexFileMap.put(dexName, oldFile);
            }
        }
        for (int i = 0; i < dexNameToClassNOldDexFileMap.size(); i += 1) {
            String expectedDexName = i == 0 ? new StringBuilder().append("classes").append(i + 1).append(".dex").toString() : "classes.dex";
            if (dexNameToClassNOldDexFileMap.containsKey(expectedDexName)) {
                oldDexFile = (File)dexNameToClassNOldDexFileMap.get(expectedDexName);
                realClassNDexFiles.add(oldDexFile);
            }
        }
        Iterator iteratorVar1 = this.oldAndNewDexFilePairList.iterator();
        while (iteratorVar1.hasNext()) {
            oldAndNewDexFilePair = (AbstractMap$SimpleEntry)iteratorVar1.next();
            oldDexFile = (File)oldAndNewDexFilePair.getKey();
            File newDexFile = (File)oldAndNewDexFilePair.getValue();
            dexName = this.getRelativeDexName(oldDexFile, newDexFile);
            DexDiffDecoder$RelatedInfo relatedInfo = (DexDiffDecoder$RelatedInfo)this.dexNameToRelatedInfoMap.get(dexName);
            if (relatedInfo.oldMd5.equals(relatedInfo.newMd5)) {
                this.logToDexMeta(newDexFile, oldDexFile, relatedInfo.dexDiffFile, relatedInfo.newOrFullPatchedMd5, relatedInfo.newOrFullPatchedMd5, relatedInfo.dexDiffMd5, relatedInfo.newOrFullPatchedCRC);
                continue;;
            }
            else if (realClassNDexFiles.contains(oldDexFile)) {
                if (! this.config.mRemoveLoaderForAllDex || dexName.equals("classes.dex")) {
                    if (this.config.mRemoveLoaderForAllDex) {
                        Logger.d("
Do additional diff on every dex to remove loader classes in it, because removeLoaderForAllDex = true");
                    }
                    else {
                        Logger.d("
Do additional diff on main dex to remove loader classes in it.");
                    }
                    this.diffDexPairAndFillRelatedInfo(oldDexFile, newDexFile, relatedInfo);
                    this.logToDexMeta(newDexFile, oldDexFile, relatedInfo.dexDiffFile, relatedInfo.newOrFullPatchedMd5, relatedInfo.newOrFullPatchedMd5, relatedInfo.dexDiffMd5, relatedInfo.newOrFullPatchedCRC);
                    continue;;
                }
                else {
                    this.logToDexMeta(newDexFile, oldDexFile, null, "0", relatedInfo.oldMd5, "0", relatedInfo.newOrFullPatchedCRC);
                }
            }
        }
    }

    private void generatePatchedDexInfoFile() {
        for (Iterator iterator = this.oldAndNewDexFilePairList.iterator(); iterator.hasNext(); relatedInfo.newOrFullPatchedCRC = FileOperation.getFileCrc32(newFile)) {
            AbstractMap$SimpleEntry oldAndNewDexFilePair = (AbstractMap$SimpleEntry)iterator.next();
            File oldFile = (File)oldAndNewDexFilePair.getKey();
            File newFile = (File)oldAndNewDexFilePair.getValue();
            String dexName = this.getRelativeDexName(oldFile, newFile);
            DexDiffDecoder$RelatedInfo relatedInfo = (DexDiffDecoder$RelatedInfo)this.dexNameToRelatedInfoMap.get(dexName);
            if (relatedInfo.oldMd5.equals(relatedInfo.newMd5)) {
                this.diffDexPairAndFillRelatedInfo(oldFile, newFile, relatedInfo);
                continue;;
            }
            else {
                relatedInfo.newOrFullPatchedFile = newFile;
                relatedInfo.newOrFullPatchedMd5 = relatedInfo.newMd5;
            }
        }
    }

    private void diffDexPairAndFillRelatedInfo(File oldDexFile, File newDexFile, DexDiffDecoder$RelatedInfo relatedInfo) {
        File tempFullPatchDexPath = new File(new StringBuilder().append(this.config.mOutFolder).append(File.separator).append("tempPatchedDexes").toString());
        String dexName = this.getRelativeDexName(oldDexFile, newDexFile);
        File dexDiffOut = this.getOutputPath(newDexFile).toFile();
        this.ensureDirectoryExist(dexDiffOut.getParentFile());
        try {
            DexPatchGenerator dexPatchGen = new DexPatchGenerator(oldDexFile, newDexFile);
            dexPatchGen.setAdditionalRemovingClassPatterns(this.config.mDexLoaderPattern);
            this.logWriter.writeLineToInfoFile(String.format("Start diff between [%s] as old and [%s] as new:", new Object[]{this.getRelativeStringBy(oldDexFile, this.config.mTempUnzipOldDir), this.getRelativeStringBy(newDexFile, this.config.mTempUnzipNewDir)}));
            dexPatchGen.executeAndSaveTo(dexDiffOut);
            goto 160;
        }
        catch (Exception e) {
            throw new TinkerPatchException(e);
        }
        if (dexDiffOut.exists()) {
            throw new TinkerPatchException(new StringBuilder().append("can not find the diff file:").append(dexDiffOut.getAbsolutePath()).toString());
        }
        else {
            relatedInfo.dexDiffFile = dexDiffOut;
            relatedInfo.dexDiffMd5 = MD5.getMD5(dexDiffOut);
            Logger.d("
Gen %s patch file:%s, size:%d, md5:%s", new Object[]{dexName, relatedInfo.dexDiffFile.getAbsolutePath(), Long.valueOf(relatedInfo.dexDiffFile.length()), relatedInfo.dexDiffMd5});
            File file = new File(tempFullPatchDexPath, dexName);
            if (file.exists()) {
                this.ensureDirectoryExist(file.getParentFile());
            }
            try {
                new DexPatchApplier(oldDexFile, dexDiffOut).executeAndSaveTo(file);
                Logger.d(String.format("Verifying if patched new dex is logically the same as original new dex: %s ...", new Object[]{this.getRelativeStringBy(newDexFile, this.config.mTempUnzipNewDir)}));
                Dex origNewDex = new Dex(newDexFile);
                Dex patchedNewDex = new Dex(file);
                this.checkDexChange(origNewDex, patchedNewDex);
                relatedInfo.newOrFullPatchedFile = file;
                relatedInfo.newOrFullPatchedMd5 = MD5.getMD5(file);
                relatedInfo.newOrFullPatchedCRC = FileOperation.getFileCrc32(file);
                goto 405;
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new TinkerPatchException("Failed to generate temporary patched dex, which makes MD5 generating procedure of new dex failed, either.", e);
            }
            if (file.exists()) {
                throw new TinkerPatchException(new StringBuilder().append("can not find the temporary full patched dex file:").append(file.getAbsolutePath()).toString());
            }
            else {
                Logger.d("
Gen %s for dalvik full dex file:%s, size:%d, md5:%s", new Object[]{dexName, file.getAbsolutePath(), Long.valueOf(file.length()), relatedInfo.newOrFullPatchedMd5});
            }
        }
    }

    private void addTestDex() {
        String dexMode = "jar";
        if (this.config.mDexRaw) {
            dexMode = "raw";
        }
        InputStream is = DexDiffDecoder.class.getResourceAsStream("/test.dex");
        String md5 = MD5.getMD5(is, 1024);
        is.close();
        String meta = new StringBuilder().append("test.dex,,").append(md5).append(",").append(md5).append(",").append(0).append(",").append(0).append(",").append(0).append(",").append(dexMode).toString();
        File dest = new File(new StringBuilder().append(this.config.mTempResultDir).append("/").append("test.dex").toString());
        FileOperation.copyResourceUsingStream("test.dex", dest);
        Logger.d("
Add test install result dex: %s, size:%d", new Object[]{dest.getAbsolutePath(), Long.valueOf(dest.length())});
        Logger.d("DexDecoder:write test dex meta file data: %s", new Object[]{meta});
        this.metaWriter.writeLineToInfoFile(meta);
    }

    private void checkCrossDexMovingClasses() {
        HashSet deletedClassDescs = new HashSet(this.deletedClassDescToDexNameMap.keySet());
        HashSet addedClassDescs = new HashSet(this.addedClassDescToDexNameMap.keySet());
        deletedClassDescs.retainAll(addedClassDescs);
        if (deletedClassDescs.isEmpty()) {
            Logger.e("Warning:Class Moved. Some classes are just moved from one dex to another. This behavior may leads to unnecessary enlargement of patch file. you should try to check them:");
            Iterator iterator = deletedClassDescs.iterator();
            while (iterator.hasNext()) {
                String classDesc = (String)iterator.next();
                StringBuilder sb = new StringBuilder();
                sb.append(123);
                sb.append("classDesc:").append(classDesc).append(44);
                sb.append("from:").append((String)this.deletedClassDescToDexNameMap.get(classDesc)).append(44);
                sb.append("to:").append((String)this.addedClassDescToDexNameMap.get(classDesc));
                sb.append(125);
                Logger.e(sb.toString());
            }
        }
    }

    private void collectAddedOrDeletedClasses(File oldFile, File newFile) {
        Dex oldDex = new Dex(oldFile);
        Dex newDex = new Dex(newFile);
        HashSet oldClassDescs = new HashSet();
        Iterator iterator = oldDex.classDefs().iterator();
        while (iterator.hasNext()) {
            ClassDef oldClassDef = (ClassDef)iterator.next();
            oldClassDescs.add((String)oldDex.typeNames().get(oldClassDef.typeIndex));
        }
        Set<String> string> = new HashSet();
        Iterator iteratorVar1 = newDex.classDefs().iterator();
        while (iteratorVar1.hasNext()) {
            ClassDef newClassDef = (ClassDef)iteratorVar1.next();
            string>.add((String)newDex.typeNames().get(newClassDef.typeIndex));
        }
        Set<String> string>Var1 = new HashSet(string>);
        string>Var1.removeAll(oldClassDescs);
        HashSet deletedClassDescs = new HashSet(oldClassDescs);
        deletedClassDescs.removeAll(string>);
        Iterator iteratorVar3 = string>Var1.iterator();
        while (iteratorVar3.hasNext()) {
            String addedClassDesc = (String)iteratorVar3.next();
            if (this.addedClassDescToDexNameMap.containsKey(addedClassDesc)) {
                throw new TinkerPatchException(String.format("Class Duplicate. Class [%s] is added in both new dex: [%s] and [%s]. Please check your newly apk.", new Object[]{addedClassDesc, this.addedClassDescToDexNameMap.get(addedClassDesc), newFile.toString()}));
            }
            else {
                this.addedClassDescToDexNameMap.put(addedClassDesc, newFile.toString());
                continue;;
            }
        }
        iteratorVar3 = deletedClassDescs.iterator();
        while (iteratorVar3.hasNext()) {
            String deletedClassDesc = (String)iteratorVar3.next();
            if (this.deletedClassDescToDexNameMap.containsKey(deletedClassDesc)) {
                throw new TinkerPatchException(String.format("Class Duplicate. Class [%s] is deleted in both old dex: [%s] and [%s]. Please check your base apk.", new Object[]{deletedClassDesc, this.addedClassDescToDexNameMap.get(deletedClassDesc), oldFile.toString()}));
            }
            else {
                this.deletedClassDescToDexNameMap.put(deletedClassDesc, newFile.toString());
                continue;;
            }
        }
    }

    private boolean isDexNameMatchesClassNPattern(String dexName) {
        return dexName.matches("^classes[0-9]*\.dex$");
    }

    private void copyNewDexAndLogToDexMeta(File newFile, String newMd5, File output) {
        FileOperation.copyFileUsingStream(newFile, output);
        long newFileCrc = FileOperation.getFileCrc32(newFile);
        this.logToDexMeta(newFile, null, null, newMd5, newMd5, "0", newFileCrc);
    }

    private void checkDexChange(Dex originDex, Dex newDex) {
        DexClassesComparator classesCmptor = new DexClassesComparator("*");
        classesCmptor.setIgnoredRemovedClassDescPattern(this.config.mDexLoaderPattern);
        classesCmptor.startCheck(originDex, newDex);
        List addedClassInfos = classesCmptor.getAddedClassInfos();
        boolean isNoClassesAdded = addedClassInfos.isEmpty();
        if (isNoClassesAdded) {
            Map changedClassDescToClassInfosMap = classesCmptor.getChangedClassDescToInfosMap();
            boolean isNoClassesChanged = changedClassDescToClassInfosMap.isEmpty();
            goto 98;
            if (isNoClassesChanged) {
                List deletedClassInfos = classesCmptor.getDeletedClassInfos();
                if (deletedClassInfos.isEmpty()) {
                    throw new TinkerPatchException(new StringBuilder().append("some classes that are not matched to loader class pattern was unexpectedly deleted in patched new dex, check if there's any bugs in patch algorithm. Related classes: ").append(Utils.collectionToString(deletedClassInfos)).toString());
                }
                else {
                }
            }
            else {
                throw new TinkerPatchException(new StringBuilder().append("some classes was unexpectedly changed in patched new dex, check if there's any bugs in patch algorithm. Related classes: ").append(Utils.collectionToString(changedClassDescToClassInfosMap.keySet())).toString());
            }
        }
        else {
            throw new TinkerPatchException(new StringBuilder().append("some classes was unexpectedly added in patched new dex, check if there's any bugs in patch algorithm. Related classes: ").append(Utils.collectionToString(addedClassInfos)).toString());
        }
    }

    protected void logToDexMeta(File newFile, File oldFile, File dexDiffFile, String destMd5InDvm, String destMd5InArt, String dexDiffMd5, long newOrFullPatchedCrc) {
        if (this.metaWriter == null && this.logWriter == null) {
        }
        else {
            String parentRelative = this.getParentRelativePathStringToNewFile(newFile);
            String relative = this.getRelativePathStringToNewFile(newFile);
            if (this.metaWriter != null) {
                String dexMode;
                String fileName = newFile.getName();
                dexMode = "jar";
                if (this.config.mDexRaw) {
                    dexMode = "raw";
                }
                String oldCrc = oldFile == null ? FileOperation.getZipEntryCrc(this.config.mOldApkFile, relative) : "0";
                if (oldCrc == null || oldCrc.equals("0")) {
                    throw new TinkerPatchException(String.format("can't find zipEntry %s from old apk file %s", new Object[]{relative, this.config.mOldApkFile.getPath()}));
                }
                String meta = new StringBuilder().append(fileName).append(",").append(parentRelative).append(",").append(destMd5InDvm).append(",").append(destMd5InArt).append(",").append(dexDiffMd5).append(",").append(oldCrc).append(",").append(newOrFullPatchedCrc).append(",").append(dexMode).toString();
                Logger.d("DexDecoder:write meta file data: %s", new Object[]{meta});
                this.metaWriter.writeLineToInfoFile(meta);
            }
            if (this.logWriter != null) {
                String log = new StringBuilder().append(relative).append(", oldSize=").append(FileOperation.getFileSizes(oldFile)).append(", newSize=").append(FileOperation.getFileSizes(newFile)).append(", diffSize=").append(FileOperation.getFileSizes(dexDiffFile)).toString();
                this.logWriter.writeLineToInfoFile(log);
            }
        }
    }

    public void clean() {
        this.metaWriter.close();
        this.logWriter.close();
    }

    private String getRawOrWrappedDexMD5(File dexOrJarFile) {
        String name = dexOrJarFile.getName();
        if (name.endsWith(".dex")) {
            return MD5.getMD5(dexOrJarFile);
        }
        else {
            Object dexJar = null;
            try {
                JarFile file = new JarFile(dexOrJarFile);
                ZipEntry classesDex = file.getEntry("classes.dex");
                if (classesDex == null) {
                    throw new TinkerPatchException(String.format("Jar file %s do not contain 'classes.dex', it is not a correct dex jar file!", new Object[]{dexOrJarFile.getAbsolutePath()}));
                }
                else {
                    String str0 = MD5.getMD5(file.getInputStream(classesDex), 102400);
                    if (file != null) {
                        try {
                            file.close();
                        }
                        catch (Exception var_6_0) {
                        }
                    }
                    return str0;
                }
            }
            catch (IOException e) {
                throw new TinkerPatchException(String.format("File %s is not end with '.dex', but it is not a correct dex jar file !", new Object[]{dexOrJarFile.getAbsolutePath()}), e);
            }
            finally {
                Throwable throwable = v_15;
                if (file != null) {
                    try {
                        file.close();
                    }
                    catch (Exception var_8_0) {
                    }
                }
                throw throwable;
            }
        }
    }

    private String getRelativeStringBy(File file, File reference) {
        File actualReference = reference.getParentFile();
        if (actualReference == null) {
        }
        return reference.toPath().relativize(file.toPath()).toString().replace("\", "/");
    }

    private void ensureDirectoryExist(File dir) {
        if (dir.exists() && dir.mkdirs()) {
            throw new TinkerPatchException(new StringBuilder().append("failed to create directory: ").append(dir).toString());
        }
        else {
        }
    }

    // class: com/tencent/tinker/build/decoder/DexDiffDecoder$DexPatcherLoggerBridge
    final class DexDiffDecoder$DexPatcherLoggerBridge implements DexPatcherLogger$IDexPatcherLogger {
        final private InfoWriter logWriter;
        final synthetic DexDiffDecoder this$0;

         DexDiffDecoder$DexPatcherLoggerBridge(DexDiffDecoder decoder, InfoWriter logWritter) {
            this.this$0 = decoder;
            super();
            this.logWriter = logWritter;
        }

        public void v(String msg) {
            this.logWriter.writeLineToInfoFile(msg);
        }

        public void d(String msg) {
            this.logWriter.writeLineToInfoFile(msg);
        }

        public void i(String msg) {
            this.logWriter.writeLineToInfoFile(msg);
        }

        public void w(String msg) {
            this.logWriter.writeLineToInfoFile(msg);
        }

        public void e(String msg) {
            this.logWriter.writeLineToInfoFile(msg);
        }

    }
    // class: com/tencent/tinker/build/decoder/DexDiffDecoder$DexPatcherLoggerBridge
    final class DexDiffDecoder$DexPatcherLoggerBridge implements DexPatcherLogger$IDexPatcherLogger {
        final private InfoWriter logWriter;
        final synthetic DexDiffDecoder this$0;

         DexDiffDecoder$DexPatcherLoggerBridge(DexDiffDecoder decoder, InfoWriter logWritter) {
            this.this$0 = decoder;
            super();
            this.logWriter = logWritter;
        }

        public void v(String msg) {
            this.logWriter.writeLineToInfoFile(msg);
        }

        public void d(String msg) {
            this.logWriter.writeLineToInfoFile(msg);
        }

        public void i(String msg) {
            this.logWriter.writeLineToInfoFile(msg);
        }

        public void w(String msg) {
            this.logWriter.writeLineToInfoFile(msg);
        }

        public void e(String msg) {
            this.logWriter.writeLineToInfoFile(msg);
        }

    }
    // class: com/tencent/tinker/build/decoder/DexDiffDecoder$RelatedInfo
    final class DexDiffDecoder$RelatedInfo {
        File newOrFullPatchedFile;
        File dexDiffFile;
        String oldMd5;
        String newMd5;
        String dexDiffMd5;
        String newOrFullPatchedMd5;
        long newOrFullPatchedCRC;
        final synthetic DexDiffDecoder this$0;

        private DexDiffDecoder$RelatedInfo(DexDiffDecoder decoder) {
            this.this$0 = decoder;
            super();
            this.newOrFullPatchedFile = null;
            this.dexDiffFile = null;
            this.oldMd5 = "0";
            this.newMd5 = "0";
            this.dexDiffMd5 = "0";
            this.newOrFullPatchedMd5 = "0";
            this.newOrFullPatchedCRC = 0L;
        }

        /* synthetic */ DexDiffDecoder$RelatedInfo(DexDiffDecoder x0, DexDiffDecoder$1 x1) {
            super(x0);
        }

    }
    // class: com/tencent/tinker/build/decoder/DexDiffDecoder$RelatedInfo
    final class DexDiffDecoder$RelatedInfo {
        File newOrFullPatchedFile;
        File dexDiffFile;
        String oldMd5;
        String newMd5;
        String dexDiffMd5;
        String newOrFullPatchedMd5;
        long newOrFullPatchedCRC;
        final synthetic DexDiffDecoder this$0;

        private DexDiffDecoder$RelatedInfo(DexDiffDecoder decoder) {
            this.this$0 = decoder;
            super();
            this.newOrFullPatchedFile = null;
            this.dexDiffFile = null;
            this.oldMd5 = "0";
            this.newMd5 = "0";
            this.dexDiffMd5 = "0";
            this.newOrFullPatchedMd5 = "0";
            this.newOrFullPatchedCRC = 0L;
        }

        /* synthetic */ DexDiffDecoder$RelatedInfo(DexDiffDecoder x0, DexDiffDecoder$1 x1) {
            super(x0);
        }

    }
}
