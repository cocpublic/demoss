/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/decoder;

import java.util.ArrayList;
import com.tencent.tinker.build.util.TinkerPatchException;

// class: com/tencent/tinker/build/decoder/UniqueDexDiffDecoder
public class UniqueDexDiffDecoder {
    private ArrayList<String> addedDexFiles;

    public UniqueDexDiffDecoder(Configuration config, String metaPath, String logPath) {
        super(config, metaPath, logPath);
        this.addedDexFiles = new ArrayList();
    }

    public boolean patch(File oldFile, File newFile) {
        boolean added = super.patch(oldFile, newFile);
        if (added) {
            String name = newFile.getName();
            if (this.addedDexFiles.contains(name)) {
                throw new TinkerPatchException(new StringBuilder().append("illegal dex name, dex name should be unique, dex:").append(name).toString());
            }
            else {
                this.addedDexFiles.add(name);
            }
        }
        return added;
    }

}
