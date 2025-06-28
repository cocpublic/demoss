/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/res/decoder;

import com.tencent.rfix.build.res.util.InfoWriter;
import com.tencent.rfix.build.res.InputConfig;
import java.io.File;
import java.nio.file.Path;

// class: com/tencent/rfix/build/res/decoder/BaseDecoder
public abstract class BaseDecoder {
    protected InfoWriter mMetaInfoWriter;
    protected InputConfig mConfig;

    public BaseDecoder(InputConfig config, String metaPath) {
        super();
        this.mConfig = config;
        this.mMetaInfoWriter = new InfoWriter(new StringBuilder().append(config.outputDir).append(File.separator).append(metaPath).toString());
    }

    void onAllPatchesStart();

    boolean patch(File p0, File p1);

    void onAllPatchesEnd();

    public Path getRelativePath(File file) {
        return this.mConfig.newApkTmpDir.toPath().relativize(file.toPath());
    }

    public Path getOutputPath(File file) {
        return this.mConfig.tmpResResultDir.toPath().resolve(this.getRelativePath(file));
    }

    public String getRelativePathStringToOldFile(File oldFile) {
        return this.mConfig.oldApkTmpDir.toPath().relativize(oldFile.toPath()).toString().replace("\", "/");
    }

    public String getRelativePathStringToNewFile(File newFile) {
        return this.mConfig.newApkTmpDir.toPath().relativize(newFile.toPath()).toString();
    }

}
