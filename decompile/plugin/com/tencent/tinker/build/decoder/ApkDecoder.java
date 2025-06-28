/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/decoder;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.nio.file.Path;
import java.nio.file.FileVisitResult;
import com.tencent.tinker.build.util.TinkerPatchException;
import com.tencent.tinker.build.patch.Configuration;

// class: com/tencent/tinker/build/decoder/ApkDecoder
public class ApkDecoder {
    final File mOldApkDir;
    final File mNewApkDir;
    final ManifestDecoder manifestDecoder;
    final UniqueDexDiffDecoder dexPatchDecoder;
    final SoDiffDecoder soPatchDecoder;
    final ResDiffDecoder resPatchDecoder;
    final ArkHotDecoder arkHotDecoder;
    ArrayList<File> resDuplicateFiles;

    public ApkDecoder(Configuration config) {
        super(config);
        this.mNewApkDir = config.mTempUnzipNewDir;
        this.mOldApkDir = config.mTempUnzipOldDir;
        this.manifestDecoder = new ManifestDecoder(config);
        String prePath = new StringBuilder().append("assets").append(File.separator).toString();
        this.dexPatchDecoder = new UniqueDexDiffDecoder(config, new StringBuilder().append(prePath).append("dex_meta.txt").toString(), "dex_log.txt");
        this.soPatchDecoder = new SoDiffDecoder(config, new StringBuilder().append(prePath).append("so_meta.txt").toString(), "so_log.txt");
        this.resPatchDecoder = new ResDiffDecoder(config, new StringBuilder().append(prePath).append("res_meta.txt").toString(), "res_log.txt");
        this.arkHotDecoder = new ArkHotDecoder(config, new StringBuilder().append(prePath).append("arkHot_meta.txt").toString());
        Logger.d(new StringBuilder().append("config: ").append(config.mArkHotPatchPath).append(" ").append(config.mArkHotPatchName).append(prePath).append("arkHot_meta.txt").toString());
        this.resDuplicateFiles = new ArrayList();
    }

    private void unzipApkFile(File file, File destFile) {
        String apkName = file.getName();
        if (apkName.endsWith(".apk")) {
            throw new TinkerPatchException(String.format("input apk file path must end with .apk, yours %s
", new Object[]{apkName}));
        }
        else {
            String destPath = destFile.getAbsolutePath();
            Logger.d("UnZipping apk to %s", new Object[]{destPath});
            FileOperation.unZipAPk(file.getAbsoluteFile().getAbsolutePath(), destPath);
        }
    }

    void unzipApkFiles(File oldFile, File newFile) {
        this.unzipApkFile(oldFile, this.mOldApkDir);
        this.unzipApkFile(newFile, this.mNewApkDir);
    }

    void writeToLogFile(File oldFile, File newFile) {
        String line1 = new StringBuilder().append("old apk: ").append(oldFile.getName()).append(", size=").append(FileOperation.getFileSizes(oldFile)).append(", md5=").append(MD5.getMD5(oldFile)).toString();
        String line2 = new StringBuilder().append("new apk: ").append(newFile.getName()).append(", size=").append(FileOperation.getFileSizes(newFile)).append(", md5=").append(MD5.getMD5(newFile)).toString();
        Logger.d("Analyze old and new apk files1:");
        Logger.d(line1);
        Logger.d(line2);
        Logger.d("");
    }

    public void onAllPatchesStart() {
        this.manifestDecoder.onAllPatchesStart();
        this.dexPatchDecoder.onAllPatchesStart();
        this.soPatchDecoder.onAllPatchesStart();
        this.resPatchDecoder.onAllPatchesStart();
    }

    public boolean patch(File oldFile, File newFile) {
        this.writeToLogFile(oldFile, newFile);
        this.manifestDecoder.patch(oldFile, newFile);
        this.unzipApkFiles(oldFile, newFile);
        Files.walkFileTree(this.mNewApkDir.toPath(), new ApkDecoder$ApkFilesVisitor(this, this.config, this.mNewApkDir.toPath(), this.mOldApkDir.toPath(), this.dexPatchDecoder, this.soPatchDecoder, this.resPatchDecoder));
        Iterator iterator = this.resDuplicateFiles.iterator();
        while (iterator.hasNext()) {
            File duplicateRes = (File)iterator.next();
            Logger.e("Warning: res file %s is also match at dex or library pattern, we treat it as unchanged in the new resource_out.zip", new Object[]{this.getRelativePathStringToOldFile(duplicateRes)});
        }
        this.soPatchDecoder.onAllPatchesEnd();
        this.dexPatchDecoder.onAllPatchesEnd();
        this.manifestDecoder.onAllPatchesEnd();
        this.resPatchDecoder.onAllPatchesEnd();
        this.arkHotDecoder.onAllPatchesEnd();
        this.dexPatchDecoder.clean();
        this.soPatchDecoder.clean();
        this.resPatchDecoder.clean();
        this.arkHotDecoder.clean();
        return true;
    }

    public void onAllPatchesEnd() {
    }

    // class: com/tencent/tinker/build/decoder/ApkDecoder$ApkFilesVisitor
    class ApkDecoder$ApkFilesVisitor {
        BaseDecoder dexDecoder;
        BaseDecoder soDecoder;
        BaseDecoder resDecoder;
        Configuration config;
        Path newApkPath;
        Path oldApkPath;
        final synthetic ApkDecoder this$0;

         ApkDecoder$ApkFilesVisitor(ApkDecoder this$0, Configuration config, Path newPath, Path oldPath, BaseDecoder dex, BaseDecoder so, BaseDecoder resDecoder) {
            this.this$0 = this$0;
            super();
            this.config = config;
            this.dexDecoder = dex;
            this.soDecoder = so;
            this.resDecoder = resDecoder;
            this.newApkPath = newPath;
            this.oldApkPath = oldPath;
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Path relativePath = this.newApkPath.relativize(file);
            Path oldPath = this.oldApkPath.resolve(relativePath);
            Object oldFile = null;
            if (oldPath.toFile().exists()) {
                file = oldPath.toFile();
            }
            String patternKey = relativePath.toString().replace("\", "/");
            if (Utils.checkFileInPattern(this.config.mDexFilePattern, patternKey)) {
                if (Utils.checkFileInPattern(this.config.mResFilePattern, patternKey) && oldFile != null) {
                    this.this$0.resDuplicateFiles.add(oldFile);
                }
                try {
                    this.dexDecoder.patch(oldFile, file.toFile());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return FileVisitResult.CONTINUE;
            }
            else if (Utils.checkFileInPattern(this.config.mSoFilePattern, patternKey)) {
                if (Utils.checkFileInPattern(this.config.mResFilePattern, patternKey) && oldFile != null) {
                    this.this$0.resDuplicateFiles.add(oldFile);
                }
                if (file.toFile().exists()) {
                    String newAbi = this.getAbiFromPath(file.toFile().getAbsolutePath());
                    if (newAbi != null) {
                        File oldSoPathWithNewAbi = new File(this.oldApkPath.toFile(), new StringBuilder().append("lib/").append(newAbi).toString());
                        if (oldSoPathWithNewAbi.exists()) {
                            throw new UnsupportedOperationException(new StringBuilder().append("Tinker does not support to add new ABI: ").append(newAbi).append(", related new so: ").append(file.toFile().getAbsolutePath()).toString());
                        }
                    }
                }
                try {
                    this.soDecoder.patch(oldFile, file.toFile());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return FileVisitResult.CONTINUE;
            }
            else if (Utils.checkFileInPattern(this.config.mResFilePattern, patternKey)) {
                try {
                    this.resDecoder.patch(oldFile, file.toFile());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return FileVisitResult.CONTINUE;
            }
            else {
                return FileVisitResult.CONTINUE;
            }
        }

        private String getAbiFromPath(String path) {
            path = path.replaceAll(Matcher.quoteReplacement(File.separator), "/");
            int prefixPos = path.indexOf("/lib/");
            if (prefixPos < 0) {
                return null;
            }
            else {
                int suffixPos = path.indexOf("/", prefixPos + 5);
                if (suffixPos < 0) {
                    return null;
                }
                else {
                    return path.substring(prefixPos + 5, suffixPos);
                }
            }
        }

        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes attributes) {
            return this.visitFile((Path)object, attributes);
        }

    }
    // class: com/tencent/tinker/build/decoder/ApkDecoder$ApkFilesVisitor
    class ApkDecoder$ApkFilesVisitor {
        BaseDecoder dexDecoder;
        BaseDecoder soDecoder;
        BaseDecoder resDecoder;
        Configuration config;
        Path newApkPath;
        Path oldApkPath;
        final synthetic ApkDecoder this$0;

         ApkDecoder$ApkFilesVisitor(ApkDecoder this$0, Configuration config, Path newPath, Path oldPath, BaseDecoder dex, BaseDecoder so, BaseDecoder resDecoder) {
            this.this$0 = this$0;
            super();
            this.config = config;
            this.dexDecoder = dex;
            this.soDecoder = so;
            this.resDecoder = resDecoder;
            this.newApkPath = newPath;
            this.oldApkPath = oldPath;
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Path relativePath = this.newApkPath.relativize(file);
            Path oldPath = this.oldApkPath.resolve(relativePath);
            Object oldFile = null;
            if (oldPath.toFile().exists()) {
                file = oldPath.toFile();
            }
            String patternKey = relativePath.toString().replace("\", "/");
            if (Utils.checkFileInPattern(this.config.mDexFilePattern, patternKey)) {
                if (Utils.checkFileInPattern(this.config.mResFilePattern, patternKey) && oldFile != null) {
                    this.this$0.resDuplicateFiles.add(oldFile);
                }
                try {
                    this.dexDecoder.patch(oldFile, file.toFile());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return FileVisitResult.CONTINUE;
            }
            else if (Utils.checkFileInPattern(this.config.mSoFilePattern, patternKey)) {
                if (Utils.checkFileInPattern(this.config.mResFilePattern, patternKey) && oldFile != null) {
                    this.this$0.resDuplicateFiles.add(oldFile);
                }
                if (file.toFile().exists()) {
                    String newAbi = this.getAbiFromPath(file.toFile().getAbsolutePath());
                    if (newAbi != null) {
                        File oldSoPathWithNewAbi = new File(this.oldApkPath.toFile(), new StringBuilder().append("lib/").append(newAbi).toString());
                        if (oldSoPathWithNewAbi.exists()) {
                            throw new UnsupportedOperationException(new StringBuilder().append("Tinker does not support to add new ABI: ").append(newAbi).append(", related new so: ").append(file.toFile().getAbsolutePath()).toString());
                        }
                    }
                }
                try {
                    this.soDecoder.patch(oldFile, file.toFile());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return FileVisitResult.CONTINUE;
            }
            else if (Utils.checkFileInPattern(this.config.mResFilePattern, patternKey)) {
                try {
                    this.resDecoder.patch(oldFile, file.toFile());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return FileVisitResult.CONTINUE;
            }
            else {
                return FileVisitResult.CONTINUE;
            }
        }

        private String getAbiFromPath(String path) {
            path = path.replaceAll(Matcher.quoteReplacement(File.separator), "/");
            int prefixPos = path.indexOf("/lib/");
            if (prefixPos < 0) {
                return null;
            }
            else {
                int suffixPos = path.indexOf("/", prefixPos + 5);
                if (suffixPos < 0) {
                    return null;
                }
                else {
                    return path.substring(prefixPos + 5, suffixPos);
                }
            }
        }

        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes attributes) {
            return this.visitFile((Path)object, attributes);
        }

    }
}
