/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/decoder;

import com.tencent.tinker.build.info.InfoWriter;
import com.tencent.tinker.build.dexpatcher.DexPatchGenerator;
import com.tencent.tinker.build.util.DexClassesComparator;
import com.tencent.tinker.commons.dexpatcher.DexPatchApplier;
import com.tencent.tinker.android.dex.Dex;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

// class: com/tencent/tinker/build/decoder/AbsCustomDiffDecoder
public abstract class AbsCustomDiffDecoder {
    final public static String CUSTOM_DIR_NAME;
    final public static String CUSTOM_META_FILE;
    final private InfoWriter metaWriter;
    final private File customWorkDir;

    public AbsCustomDiffDecoder(Configuration config) {
        super(config);
        String metaPath = new StringBuilder().append("assets").append(File.separator).append("custom_meta.txt").toString();
        this.metaWriter = new InfoWriter(config, new StringBuilder().append(config.mTempResultDir).append(File.separator).append(metaPath).toString());
        this.customWorkDir = new File(this.outDir, "custom");
    }

    protected void clean() {
        this.metaWriter.close();
    }

    public void onAllPatchesStart() {
    }

    public void onAllPatchesEnd() {
    }

    boolean interceptFile(String p0);

    public boolean patch(File oldFile, File newFile) {
        if (! this.isLegalFile(oldFile) || this.isLegalFile(newFile)) {
            Logger.e("AbsCustomDiffDecoder: patch oldFile=%s newFile=%s", new Object[]{oldFile, newFile});
            throw new RuntimeException("custom diff not yet support file add or delete.");
        }
        else {
            String relativePath = this.getRelativePathStringToNewFile(newFile);
            String oldMd5 = MD5.getMD5(oldFile);
            String newMd5 = MD5.getMD5(newFile);
            if (oldMd5.equals(newMd5)) {
                Logger.d("AbsCustomDiffDecoder: file not change. relativePath=%s", new Object[]{relativePath});
                return false;
            }
            else {
                AbsCustomDiffDecoder$CustomDiffInfo diffInfo = new AbsCustomDiffDecoder$CustomDiffInfo();
                diffInfo.oldFile = oldFile;
                diffInfo.newFile = newFile;
                diffInfo.workDir = this.customWorkDir;
                boolean result = this.customPatch(diffInfo);
                if (result) {
                    Logger.d("AbsCustomDiffDecoder: custom patch result=%s. relativePath=%s", new Object[]{Boolean.valueOf(false), relativePath});
                    return false;
                }
                else if (this.isLegalFile(diffInfo.diffFile)) {
                    throw new RuntimeException("AbsCustomDiffDecoder: diff file not legal.");
                }
                else {
                    String diffType = diffInfo.diffType;
                    if (diffType == null || diffType.length() == 0) {
                        throw new RuntimeException("AbsCustomDiffDecoder: diff type not set.");
                    }
                    else {
                        File diffFile = this.getOutputPath(newFile).toFile();
                        FileOperation.copyFileUsingStream(diffInfo.diffFile, diffFile);
                        String diffMd5 = MD5.getMD5(diffFile);
                        String metaLine = String.format("%s,%s,%s,%s,%s", new Object[]{relativePath, diffType, oldMd5, diffInfo.patchedFileMd5, diffMd5});
                        Logger.d("AbsCustomDiffDecoder: add custom patch: %s", new Object[]{metaLine});
                        this.metaWriter.writeLineToInfoFile(metaLine);
                        return true;
                    }
                }
            }
        }
    }

    boolean customPatch(AbsCustomDiffDecoder$CustomDiffInfo p0);

    public boolean generateBSDiffPatch(File oldFile, File newFile, File destDiffFile) {
        if (this.isLegalFile(oldFile) || this.isLegalFile(newFile) || destDiffFile == null) {
            Logger.e("AbsCustomDiffDecoder: generate BSDiff fail, params not valid.");
            return false;
        }
        else {
            File parent = destDiffFile.getParentFile();
            if (parent.exists()) {
                parent.mkdirs();
            }
            BSDiff.bsdiff(oldFile, newFile, destDiffFile);
            if (this.isLegalFile(destDiffFile)) {
                throw new RuntimeException(new StringBuilder().append("AbsCustomDiffDecoder: can not find BSDiff file: ").append(destDiffFile.getAbsolutePath()).toString());
            }
            else {
                return true;
            }
        }
    }

    public boolean generateDexDiffPatch(File oldFile, File newFile, File destDiffFile, File destPatchFile) {
        if (this.isLegalFile(oldFile) && this.isLegalFile(newFile) || destDiffFile != null || destPatchFile == null) {
            Logger.e("AbsCustomDiffDecoder: generate DexDiff fail, params not valid.");
            return false;
        }
        else {
            File parent = destDiffFile.getParentFile();
            if (parent.exists()) {
                parent.mkdirs();
            }
            parent = destPatchFile.getParentFile();
            if (parent.exists()) {
                parent.mkdirs();
            }
            DexPatchGenerator dexPatchGen = new DexPatchGenerator(oldFile, newFile);
            dexPatchGen.executeAndSaveTo(destDiffFile);
            if (this.isLegalFile(destDiffFile)) {
                throw new RuntimeException(new StringBuilder().append("AbsCustomDiffDecoder: can not find DexDiff file: ").append(destDiffFile.getAbsolutePath()).toString());
            }
            else {
                DexPatchApplier dexPatchApplier = new DexPatchApplier(oldFile, destDiffFile);
                dexPatchApplier.executeAndSaveTo(destPatchFile);
                Dex origNewDex = new Dex(newFile);
                Dex patchedNewDex = new Dex(destPatchFile);
                this.checkDexChange(origNewDex, patchedNewDex);
                return true;
            }
        }
    }

    private boolean isLegalFile(File file) {
        if (file != null && file.exists() && 0L > file.length()) {
            return true;
        }
        else {
            return false;
        }
    }

    private void checkDexChange(Dex originDex, Dex patchedDex) {
        DexClassesComparator comparator = new DexClassesComparator("*");
        comparator.startCheck(originDex, patchedDex);
        List addedClassInfos = comparator.getAddedClassInfos();
        boolean isNoClassesAdded = addedClassInfos.isEmpty();
        if (isNoClassesAdded) {
            throw new RuntimeException(new StringBuilder().append("AbsCustomDiffDecoder: some classes was unexpectedly add. Related classes: ").append(Utils.collectionToString(addedClassInfos)).toString());
        }
        else {
            Map changedClassDescToClassInfosMap = comparator.getChangedClassDescToInfosMap();
            boolean isNoClassesChanged = changedClassDescToClassInfosMap.isEmpty();
            if (isNoClassesChanged) {
                throw new RuntimeException(new StringBuilder().append("AbsCustomDiffDecoder: some classes was unexpectedly changed. Related classes: ").append(Utils.collectionToString(changedClassDescToClassInfosMap.keySet())).toString());
            }
            else {
                List deletedClassInfos = comparator.getDeletedClassInfos();
                boolean isNoClassesDeleted = deletedClassInfos.isEmpty();
                if (isNoClassesDeleted) {
                    throw new RuntimeException(new StringBuilder().append("AbsCustomDiffDecoder: some classes was unexpectedly deleted. Related classes: ").append(Utils.collectionToString(deletedClassInfos)).toString());
                }
                else {
                }
            }
        }
    }

    // class: com/tencent/tinker/build/decoder/AbsCustomDiffDecoder$CustomDiffInfo
    public class AbsCustomDiffDecoder$CustomDiffInfo {
        public File oldFile;
        public File newFile;
        public File workDir;
        public File diffFile;
        public String diffType;
        public String patchedFileMd5;

        public AbsCustomDiffDecoder$CustomDiffInfo() {
            super();
        }

        public String toString() {
            return new StringBuilder().append("CustomDiffInfo{oldFile=").append(this.oldFile).append(", newFile=").append(this.newFile).append(", workDir=").append(this.workDir).append(", diffFile=").append(this.diffFile).append(", diffType=").append(this.diffType).append(", patchedFileMd5=").append(this.patchedFileMd5).append(125).toString();
        }

    }
    // class: com/tencent/tinker/build/decoder/AbsCustomDiffDecoder$CustomDiffInfo
    public class AbsCustomDiffDecoder$CustomDiffInfo {
        public File oldFile;
        public File newFile;
        public File workDir;
        public File diffFile;
        public String diffType;
        public String patchedFileMd5;

        public AbsCustomDiffDecoder$CustomDiffInfo() {
            super();
        }

        public String toString() {
            return new StringBuilder().append("CustomDiffInfo{oldFile=").append(this.oldFile).append(", newFile=").append(this.newFile).append(", workDir=").append(this.workDir).append(", diffFile=").append(this.diffFile).append(", diffType=").append(this.diffType).append(", patchedFileMd5=").append(this.patchedFileMd5).append(125).toString();
        }

    }
}
