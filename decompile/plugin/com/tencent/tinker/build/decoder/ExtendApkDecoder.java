/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/decoder;

import com.tencent.tinker.build.patch.Configuration;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.FileVisitResult;
import java.util.ArrayList;
import java.util.Iterator;

// class: com/tencent/tinker/build/decoder/ExtendApkDecoder
public class ExtendApkDecoder {
    final protected AbsCustomDiffDecoder customDiffDecoder;

    public ExtendApkDecoder(Configuration config) {
        super(config);
        this.customDiffDecoder = this.createCustomDiffDecoder(config);
    }

    private AbsCustomDiffDecoder createCustomDiffDecoder(Configuration config) {
        try {
            Class clazz = Class.forName(ExtendConfiguration.sCustomDiffDecoder);
            Constructor constructor = clazz.getConstructor(new Class[]{Configuration.class});
            return (AbsCustomDiffDecoder)constructor.newInstance(new Object[]{config});
        }
        catch (Exception e) {
            throw new RuntimeException("create custom diff decoder fail.", e);
        }
    }

    public boolean patch(File oldFile, File newFile) {
        this.writeToLogFile(oldFile, newFile);
        this.manifestDecoder.patch(oldFile, newFile);
        this.unzipApkFiles(oldFile, newFile);
        ExtendApkDecoder$ExtendApkFilesVisitor visitor = new ExtendApkDecoder$ExtendApkFilesVisitor(this, this.config, this.mNewApkDir.toPath(), this.mOldApkDir.toPath(), this.dexPatchDecoder, this.soPatchDecoder, this.resPatchDecoder, this.customDiffDecoder);
        Files.walkFileTree(this.mNewApkDir.toPath(), visitor);
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
        this.customDiffDecoder.onAllPatchesEnd();
        this.dexPatchDecoder.clean();
        this.soPatchDecoder.clean();
        this.resPatchDecoder.clean();
        this.arkHotDecoder.clean();
        this.customDiffDecoder.clean();
        return true;
    }

    // class: com/tencent/tinker/build/decoder/ExtendApkDecoder$ExtendApkFilesVisitor
    public class ExtendApkDecoder$ExtendApkFilesVisitor {
        AbsCustomDiffDecoder customDiffDecoder;
        final synthetic ExtendApkDecoder this$0;

         ExtendApkDecoder$ExtendApkFilesVisitor(ExtendApkDecoder this$0, Configuration config, Path newPath, Path oldPath, BaseDecoder dex, BaseDecoder so, BaseDecoder resDecoder, AbsCustomDiffDecoder customDecoder) {
            this.this$0 = this$0;
            super(this$0, config, newPath, oldPath, dex, so, resDecoder);
            this.customDiffDecoder = customDecoder;
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Path relativePath = this.newApkPath.relativize(file);
            String relativePathStr = relativePath.toString().replace("\", "/");
            if (this.customDiffDecoder.interceptFile(relativePathStr)) {
                Object oldFile = null;
                Path oldPath = this.oldApkPath.resolve(relativePath);
                if (oldPath.toFile().exists()) {
                    file = oldPath.toFile();
                }
                try {
                    this.customDiffDecoder.patch(oldFile, file.toFile());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return FileVisitResult.CONTINUE;
            }
            else {
                return super.visitFile(file, attrs);
            }
        }

        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes attributes) {
            return this.visitFile((Path)object, attributes);
        }

    }
    // class: com/tencent/tinker/build/decoder/ExtendApkDecoder$ExtendApkFilesVisitor
    public class ExtendApkDecoder$ExtendApkFilesVisitor {
        AbsCustomDiffDecoder customDiffDecoder;
        final synthetic ExtendApkDecoder this$0;

         ExtendApkDecoder$ExtendApkFilesVisitor(ExtendApkDecoder this$0, Configuration config, Path newPath, Path oldPath, BaseDecoder dex, BaseDecoder so, BaseDecoder resDecoder, AbsCustomDiffDecoder customDecoder) {
            this.this$0 = this$0;
            super(this$0, config, newPath, oldPath, dex, so, resDecoder);
            this.customDiffDecoder = customDecoder;
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Path relativePath = this.newApkPath.relativize(file);
            String relativePathStr = relativePath.toString().replace("\", "/");
            if (this.customDiffDecoder.interceptFile(relativePathStr)) {
                Object oldFile = null;
                Path oldPath = this.oldApkPath.resolve(relativePath);
                if (oldPath.toFile().exists()) {
                    file = oldPath.toFile();
                }
                try {
                    this.customDiffDecoder.patch(oldFile, file.toFile());
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return FileVisitResult.CONTINUE;
            }
            else {
                return super.visitFile(file, attrs);
            }
        }

        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes attributes) {
            return this.visitFile((Path)object, attributes);
        }

    }
}
