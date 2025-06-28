/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/decoder;

import java.io.File;
import java.nio.file.Path;
import com.tencent.tinker.build.patch.Configuration;

// class: com/tencent/tinker/build/decoder/BaseDecoder
public abstract class BaseDecoder {
    final protected Configuration config;
    final protected File outDir;
    final protected File resultDir;

    public BaseDecoder(Configuration config) {
        super();
        this.config = config;
        this.outDir = new File(config.mOutFolder);
        this.resultDir = config.mTempResultDir;
    }

    public Configuration getConfig() {
        return this.config;
    }

    protected void clean() {
    }

    public Path getRelativePath(File file) {
        return this.config.mTempUnzipNewDir.toPath().relativize(file.toPath());
    }

    public Path getOutputPath(File file) {
        return this.config.mTempResultDir.toPath().resolve(this.getRelativePath(file));
    }

    public String getRelativePathStringToOldFile(File oldFile) {
        return this.config.mTempUnzipOldDir.toPath().relativize(oldFile.toPath()).toString().replace("\", "/");
    }

    public String getRelativePathStringToNewFile(File newFile) {
        return this.config.mTempUnzipNewDir.toPath().relativize(newFile.toPath()).toString().replace("\", "/");
    }

    public String getParentRelativePathStringToNewFile(File newFile) {
        return this.config.mTempUnzipNewDir.toPath().relativize(newFile.getParentFile().toPath()).toString().replace("\", "/");
    }

    boolean patch(File p0, File p1);

    void onAllPatchesStart();

    void onAllPatchesEnd();

}
