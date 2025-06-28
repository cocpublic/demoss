/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.nio.file.Path;
import java.io.File;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.build.patch.Configuration;

// class: com/tencent/tinker/build/util/ExcludedClassModifiedChecker
public final class ExcludedClassModifiedChecker {
    final private static int STMCODE_START;
    final private static int STMCODE_ERROR_PRIMARY_OLD_DEX_IS_MISSING;
    final private static int STMCODE_ERROR_PRIMARY_NEW_DEX_IS_MISSING;
    final private static int STMCODE_ERROR_LOADER_CLASS_NOT_IN_PRIMARY_OLD_DEX;
    final private static int STMCODE_ERROR_LOADER_CLASS_IN_PRIMARY_DEX_MISMATCH;
    final private static int STMCODE_ERROR_LOADER_CLASS_FOUND_IN_SECONDARY_OLD_DEX;
    final private static int STMCODE_ERROR_LOADER_CLASS_FOUND_IN_SECONDARY_NEW_DEX;
    final private static int STMCODE_ERROR_LOADER_CLASS_CHANGED;
    final private static int STMCODE_END;
    final private Configuration config;
    final private DexClassesComparator dexCmptor;
    private Dex oldDex;
    private Dex newDex;
    private List<DexClassesComparator$DexClassInfo> deletedClassInfos;
    private List<DexClassesComparator$DexClassInfo> addedClassInfos;
    private Map<String, DexClassesComparator$DexClassInfo[]> changedClassInfosMap;
    private Set<String> oldClassesDescToCheck;
    private Set<String> newClassesDescToCheck;
    private HashSet<Pattern> ignoreChangeWarning;

    public ExcludedClassModifiedChecker(Configuration config) {
        super();
        this.oldDex = null;
        this.newDex = null;
        this.deletedClassInfos = null;
        this.addedClassInfos = null;
        this.changedClassInfosMap = null;
        this.oldClassesDescToCheck = new HashSet();
        this.newClassesDescToCheck = new HashSet();
        this.ignoreChangeWarning = new HashSet();
        this.config = config;
        this.dexCmptor = new DexClassesComparator(config.mDexLoaderPattern);
        Iterator iterator = config.mDexIgnoreWarningLoaderPattern.iterator();
        while (iterator.hasNext()) {
            String classname = (String)iterator.next();
            this.ignoreChangeWarning.add(Pattern.compile(PatternUtils.dotClassNamePatternToDescriptorRegEx(classname)));
        }
    }

    public void checkIfExcludedClassWasModifiedInNewDex(File newFile, File newFile) {
        v_90 = newFile;
        v_90 = v_90 == null ? newFile : newFile;
        if (v_90 != null) {
            v_90 = newFile;
            v_7 = new Dex(v_90);
            goto 35;
        }
        else {
            v_7 = this.oldDex = null;
        }
        v_90 = newFile;
        if (v_90 != null) {
            v_90 = newFile;
            v_11 = new Dex(v_90);
        }
        else {
            v_11 = this.newDex = null;
        }
        int stmCode = 0;
        while (stmCode != 8) {
            switch(stmCode) {
                case 0: {
                    v_90 = newFile;
                    v_90 = v_90 == null ? newFile : newFile;
                    boolean isPrimaryDex = this.isPrimaryDex(v_90);
                    if (isPrimaryDex) {
                        v_90 = newFile;
                        if (v_90 == null) {
                            stmCode = 1;
                            continue;;
                        }
                        else {
                            v_90 = newFile;
                            if (v_90 == null) {
                                stmCode = 2;
                                continue;;
                            }
                            else {
                                this.dexCmptor.startCheck(this.oldDex, this.newDex);
                                this.deletedClassInfos = this.dexCmptor.getDeletedClassInfos();
                                this.addedClassInfos = this.dexCmptor.getAddedClassInfos();
                                this.changedClassInfosMap = new HashMap(this.dexCmptor.getChangedClassDescToInfosMap());
                                if (this.deletedClassInfos.isEmpty() && this.changedClassInfosMap.isEmpty() && this.addedClassInfos.isEmpty()) {
                                    stmCode = 3;
                                    continue;;
                                }
                                else if (this.addedClassInfos.isEmpty()) {
                                    ArrayList removeClasses = new ArrayList();
                                    Iterator iterator = this.changedClassInfosMap.keySet().iterator();
                                    while (iterator.hasNext()) {
                                        String classname = (String)iterator.next();
                                        Utils.checkFileInPattern(this.ignoreChangeWarning, classname);
                                        Logger.e(new StringBuilder().append("loader class pattern: ").append(classname).append(" has changed, but it match ignore change pattern, just ignore!").toString());
                                        removeClasses.add(classname);
                                    }
                                    this.changedClassInfosMap.keySet().removeAll(removeClasses);
                                    stmCode = this.changedClassInfosMap.isEmpty() ? 7 : 8;
                                    continue;;
                                }
                                else {
                                    stmCode = 4;
                                    continue;;
                                }
                            }
                        }
                    }
                    else {
                        String desc;
                        ClassDef classDef;
                        HashSet patternsOfClassDescToCheck = new HashSet();
                        Iterator iteratorVar3 = this.config.mDexLoaderPattern.iterator();
                        while (iteratorVar3.hasNext()) {
                            String patternStr = (String)iteratorVar3.next();
                            patternsOfClassDescToCheck.add(Pattern.compile(PatternUtils.dotClassNamePatternToDescriptorRegEx(patternStr)));
                        }
                        if (this.oldDex != null) {
                            this.oldClassesDescToCheck.clear();
                            iteratorVar3 = this.oldDex.classDefs().iterator();
                            while (iteratorVar3.hasNext()) {
                                classDef = (ClassDef)iteratorVar3.next();
                                desc = (String)this.oldDex.typeNames().get(classDef.typeIndex);
                                Utils.isStringMatchesPatterns(desc, patternsOfClassDescToCheck);
                                this.oldClassesDescToCheck.add(desc);
                            }
                            if (this.oldClassesDescToCheck.isEmpty()) {
                                stmCode = 5;
                                continue;;
                            }
                        }
                        if (this.newDex != null) {
                            this.newClassesDescToCheck.clear();
                            iteratorVar3 = this.newDex.classDefs().iterator();
                            while (iteratorVar3.hasNext()) {
                                classDef = (ClassDef)iteratorVar3.next();
                                desc = (String)this.newDex.typeNames().get(classDef.typeIndex);
                                Utils.isStringMatchesPatterns(desc, patternsOfClassDescToCheck);
                                this.newClassesDescToCheck.add(desc);
                            }
                        }
                        stmCode = this.newClassesDescToCheck.isEmpty() ? 8 : 6;
                        continue;;
                    }
                }
                case 1: {
                    throw new TinkerPatchException("old primary dex is missing.");
                }
                case 2: {
                    throw new TinkerPatchException("new primary dex is missing.");
                }
                String msg;
                case 3: {
                    msg = "all loader classes don't appear in old primary dex.";
                    if (this.config.mAllowLoaderInAnyDex) {
                        Logger.d("all loader classes don't appear in old primary dex.");
                    }
                    else {
                        throw new TinkerPatchException("all loader classes don't appear in old primary dex.");
                    }
                }
                case 4: {
                    throw new TinkerPatchException(new StringBuilder().append("there's loader classes added in new primary dex, such these changes will not take effect.
added classes: ").append(Utils.collectionToString(this.addedClassInfos)).toString());
                }
                case 5: {
                    msg = new StringBuilder().append("loader classes are found in old secondary dex. Found classes: ").append(Utils.collectionToString(this.oldClassesDescToCheck)).toString();
                    if (this.config.mAllowLoaderInAnyDex) {
                        Logger.d(msg);
                    }
                    else {
                        throw new TinkerPatchException(msg);
                    }
                }
                case 6: {
                    msg = new StringBuilder().append("loader classes are found in new secondary dex. Found classes: ").append(Utils.collectionToString(this.newClassesDescToCheck)).toString();
                    if (this.config.mAllowLoaderInAnyDex) {
                        Logger.d(msg);
                    }
                    else {
                        throw new TinkerPatchException(msg);
                    }
                }
                case 7: {
                    msg = new StringBuilder().append("some loader class has been changed in new primary dex. Such these changes will not take effect!! related classes: ").append(Utils.collectionToString(this.changedClassInfosMap.keySet())).toString();
                    throw new TinkerPatchException(msg);
                }
                default: {
                    Logger.e("internal-error: unexpected stmCode.");
                    stmCode = 8;
                    continue;;
                }
            }
        }
    }

    public boolean isPrimaryDex(File dexFile) {
        Path dexFilePath = dexFile.toPath();
        Path parentPath = this.config.mTempUnzipOldDir.toPath();
        if (dexFilePath.startsWith(parentPath)) {
            parentPath = this.config.mTempUnzipNewDir.toPath();
        }
        return "classes.dex".equals(parentPath.relativize(dexFilePath).toString().replace(92, 47));
    }

}
