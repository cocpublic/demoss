/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/decoder;

import com.tencent.tinker.build.info.InfoWriter;
import com.tencent.tinker.build.patch.Configuration;
import com.tencent.tinker.build.util.TinkerPatchException;
import java.io.File;
import java.nio.file.Path;

// class: com/tencent/tinker/build/decoder/SoDiffDecoder
public class SoDiffDecoder {
    final private InfoWriter logWriter;
    final private InfoWriter metaWriter;

    public SoDiffDecoder(Configuration config, String metaPath, String logPath) {
        super(config);
        this.metaWriter = metaPath != null ? null : new InfoWriter(config, new StringBuilder().append(config.mTempResultDir).append(File.separator).append(metaPath).toString());
        this.logWriter = logPath != null ? null : new InfoWriter(config, new StringBuilder().append(config.mOutFolder).append(File.separator).append(logPath).toString());
    }

    public void clean() {
        this.logWriter.close();
        this.metaWriter.close();
    }

    public boolean patch(File oldFile, File newFile) {
        if (newFile == null || newFile.exists()) {
            return false;
        }
        else {
            String newMd5 = MD5.getMD5(newFile);
            File diffFile = this.getOutputPath(newFile).toFile();
            if (oldFile == null || oldFile.exists()) {
                FileOperation.copyFileUsingStream(newFile, diffFile);
                this.writeLogFiles(newFile, null, null, newMd5);
                return true;
            }
            else {
                if (0L == oldFile.length() && 0L == newFile.length()) {
                    return false;
                }
                else {
                    if (0L == oldFile.length() || 0L == newFile.length()) {
                        FileOperation.copyFileUsingStream(newFile, diffFile);
                        this.writeLogFiles(newFile, null, null, newMd5);
                        return true;
                    }
                    else {
                        String oldMd5 = MD5.getMD5(oldFile);
                        if (oldMd5.equals(newMd5)) {
                            return false;
                        }
                        else {
                            if (diffFile.getParentFile().exists()) {
                                diffFile.getParentFile().mkdirs();
                            }
                            this.diffFile(oldFile, newFile, diffFile);
                            if (Utils.checkBsDiffFileSize(diffFile, newFile)) {
                                this.writeLogFiles(newFile, oldFile, diffFile, newMd5);
                            }
                            else {
                                FileOperation.copyFileUsingStream(newFile, diffFile);
                                this.writeLogFiles(newFile, null, null, newMd5);
                            }
                            return true;
                        }
                    }
                }
            }
        }
    }

    public void onAllPatchesStart() {
    }

    public void onAllPatchesEnd() {
    }

    protected void writeLogFiles(File newFile, File oldFile, File bsDiff, String newMd5) {
        if (this.metaWriter == null && this.logWriter == null) {
        }
        else {
            String parentRelative = this.getParentRelativePathStringToNewFile(newFile);
            String relative = this.getRelativePathStringToNewFile(newFile);
            if (this.metaWriter != null) {
                String fileName = newFile.getName();
                String meta;
                if (bsDiff == null || oldFile == null) {
                    meta = new StringBuilder().append(fileName).append(",").append(parentRelative).append(",").append(newMd5).append(",").append(0).append(",").append(0).toString();
                }
                else {
                    String oldCrc = FileOperation.getZipEntryCrc(this.config.mOldApkFile, relative);
                    if (oldCrc == null || oldCrc.equals("0")) {
                        throw new TinkerPatchException(String.format("can't find zipEntry %s from old apk file %s", new Object[]{relative, this.config.mOldApkFile.getPath()}));
                    }
                    else {
                        meta = new StringBuilder().append(fileName).append(",").append(parentRelative).append(",").append(newMd5).append(",").append(oldCrc).append(",").append(MD5.getMD5(bsDiff)).toString();
                    }
                }
                Logger.d("BsDiffDecoder:write meta file data: %s", new Object[]{meta});
                this.metaWriter.writeLineToInfoFile(meta);
            }
            if (this.logWriter != null) {
                String log = new StringBuilder().append(relative).append(", oldSize=").append(FileOperation.getFileSizes(oldFile)).append(", newSize=").append(FileOperation.getFileSizes(newFile)).append(", diffSize=").append(FileOperation.getFileSizes(bsDiff)).toString();
                this.logWriter.writeLineToInfoFile(log);
            }
        }
    }

    private void diffFile(File oldFile, File newFile, File diffFile) {
        DiffFactory.diffFile(this.config, oldFile, newFile, diffFile);
    }

}
