/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/decoder;

import com.tencent.tinker.build.info.InfoWriter;
import com.tencent.tinker.build.patch.Configuration;
import java.io.File;

// class: com/tencent/tinker/build/decoder/ArkHotDecoder
public class ArkHotDecoder {
    final private static String ARKHOT_PATCH_NAME;
    final private static String ARKHOT_PATCH_PATH;
    final private InfoWriter metaWriter;

    public ArkHotDecoder(Configuration config, String metaPath) {
        super(config);
        this.metaWriter = metaPath != null ? null : new InfoWriter(config, new StringBuilder().append(config.mTempResultDir).append(File.separator).append(metaPath).toString());
    }

    public void clean() {
        this.metaWriter.close();
    }

    public void onAllPatchesStart() {
    }

    public void onAllPatchesEnd() {
        File patchFile = new File(new StringBuilder().append(this.config.mArkHotPatchPath).append("/").append(this.config.mArkHotPatchName).toString());
        if (patchFile.exists()) {
        }
        else {
            String md5 = MD5.getMD5(patchFile);
            File dest = new File(new StringBuilder().append(this.config.mTempResultDir).append("/").append("arkHot").append("/").append("patch.apk").toString());
            FileOperation.copyFileUsingStream(patchFile, dest);
            this.writeMetaFile(md5);
        }
    }

    public boolean patch(File oldFile, File newFile) {
        return true;
    }

    private void writeMetaFile(String md5) {
        if (this.metaWriter == null) {
        }
        else {
            if (this.metaWriter != null) {
                String path = "arkHot";
                String fileName = "patch.apk";
                if (md5 == null) {
                }
                else {
                    String meta = new StringBuilder().append(fileName).append(",").append(path).append(",").append(md5).toString();
                    this.metaWriter.writeLineToInfoFile(meta);
                }
            }
        }
    }

}
