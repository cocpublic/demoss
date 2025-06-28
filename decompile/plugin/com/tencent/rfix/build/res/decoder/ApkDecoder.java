/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/res/decoder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.FileVisitResult;
import java.util.HashSet;
import java.util.List;
import com.tencent.rfix.build.res.InputConfig;

// class: com/tencent/rfix/build/res/decoder/ApkDecoder
public class ApkDecoder {
    final private ResourceDecoder mResDecoder;
    final private File mOldApkDir;
    final private File mNewApkDir;

    public ApkDecoder(InputConfig config, String metaPath) {
        super(config, metaPath);
        this.mResDecoder = new ResourceDecoder(config, "resources.meta");
        this.mOldApkDir = config.oldApkTmpDir;
        this.mNewApkDir = config.newApkTmpDir;
    }

    public void start() {
        this.onAllPatchesStart();
        this.patch(this.mConfig.oldApk, this.mConfig.newApk);
        this.onAllPatchesEnd();
    }

    public void onAllPatchesStart() {
        this.mResDecoder.onAllPatchesStart();
    }

    public boolean patch(File oldFile, File newFile) {
        FileOperation.unZipAPk(oldFile.getAbsolutePath(), this.mOldApkDir.getAbsolutePath());
        FileOperation.unZipAPk(newFile.getAbsolutePath(), this.mNewApkDir.getAbsolutePath());
        ApkDecoder$ApkFilesVisitor visitor = new ApkDecoder$ApkFilesVisitor(this, this.mNewApkDir.toPath(), this.mOldApkDir.toPath(), this.mResDecoder);
        Files.walkFileTree(this.mNewApkDir.toPath(), visitor);
        return true;
    }

    public void onAllPatchesEnd() {
        this.mResDecoder.onAllPatchesEnd();
        if (this.mConfig.cleanTmpFile) {
            this.clean();
        }
    }

    private void clean() {
        FileOperation.deleteDir(this.mConfig.newApkTmpDir);
        FileOperation.deleteDir(this.mConfig.oldApkTmpDir);
        FileOperation.deleteDir(this.mConfig.tmpResResultDir);
    }

    // class: com/tencent/rfix/build/res/decoder/ApkDecoder$ApkFilesVisitor
    class ApkDecoder$ApkFilesVisitor {
        BaseDecoder resDecoder;
        Path newApkPath;
        Path oldApkPath;
        final synthetic ApkDecoder this$0;

         ApkDecoder$ApkFilesVisitor(ApkDecoder this$0, Path newPath, Path oldPath, BaseDecoder resDecoder) {
            this.this$0 = this$0;
            super();
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
            HashSet resMatchPattern = new HashSet(Arrays.asList(this.this$0.mConfig.pattern));
            HashSet whitelistPattern = new HashSet(Arrays.asList(this.this$0.mConfig.whitelist));
            if (FileUtil.checkFileInPattern(resMatchPattern, patternKey) && FileUtil.checkFileInPattern(whitelistPattern, patternKey)) {
                try {
                    this.resDecoder.patch(oldFile, file.toFile());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return FileVisitResult.CONTINUE;
        }

        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes attributes) {
            return this.visitFile((Path)object, attributes);
        }

    }
    // class: com/tencent/rfix/build/res/decoder/ApkDecoder$ApkFilesVisitor
    class ApkDecoder$ApkFilesVisitor {
        BaseDecoder resDecoder;
        Path newApkPath;
        Path oldApkPath;
        final synthetic ApkDecoder this$0;

         ApkDecoder$ApkFilesVisitor(ApkDecoder this$0, Path newPath, Path oldPath, BaseDecoder resDecoder) {
            this.this$0 = this$0;
            super();
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
            HashSet resMatchPattern = new HashSet(Arrays.asList(this.this$0.mConfig.pattern));
            HashSet whitelistPattern = new HashSet(Arrays.asList(this.this$0.mConfig.whitelist));
            if (FileUtil.checkFileInPattern(resMatchPattern, patternKey) && FileUtil.checkFileInPattern(whitelistPattern, patternKey)) {
                try {
                    this.resDecoder.patch(oldFile, file.toFile());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return FileVisitResult.CONTINUE;
        }

        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes attributes) {
            return this.visitFile((Path)object, attributes);
        }

    }
}
