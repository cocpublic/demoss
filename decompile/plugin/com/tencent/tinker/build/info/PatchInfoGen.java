/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/info;

import java.io.File;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.Iterator;
import com.tencent.tinker.build.patch.Configuration;
import com.tencent.tinker.build.apkparser.AndroidParser;
import com.tencent.tinker.build.util.TinkerPatchException;

// class: com/tencent/tinker/build/info/PatchInfoGen
public class PatchInfoGen {
    final private Configuration config;
    final private File packageInfoFile;

    public PatchInfoGen(Configuration config) {
        super();
        this.config = config;
        this.packageInfoFile = new File(new StringBuilder().append(config.mTempResultDir).append(File.separator).append("assets").append(File.separator).append("package_meta.txt").toString());
    }

    private void addTinkerID() {
        String tinkerID;
        if (this.config.mPackageFields.containsKey("TINKER_ID")) {
            AndroidParser oldAndroidManifest = AndroidParser.getAndroidManifest(this.config.mOldApkFile);
            tinkerID = (String)oldAndroidManifest.metaDatas.get("TINKER_ID");
            if (tinkerID == null) {
                throw new TinkerPatchException("can't find TINKER_ID from the old apk manifest file, it must be set!");
            }
            else {
                this.config.mPackageFields.put("TINKER_ID", tinkerID);
            }
        }
        if (this.config.mPackageFields.containsKey("NEW_TINKER_ID")) {
            AndroidParser newAndroidManifest = AndroidParser.getAndroidManifest(this.config.mNewApkFile);
            tinkerID = (String)newAndroidManifest.metaDatas.get("TINKER_ID");
            if (tinkerID == null) {
                throw new TinkerPatchException("can't find TINKER_ID from the new apk manifest file, it must be set!");
            }
            else {
                this.config.mPackageFields.put("NEW_TINKER_ID", tinkerID);
            }
        }
    }

    private void addProtectedAppFlag() {
        this.config.mPackageFields.put("is_protected_app", this.config.mIsProtectedApp ? "0" : "1");
    }

    private void addFilePatchFlag() {
        this.config.mPackageFields.put("use_custom_file_patch", Utils.isEmpty(this.config.mCustomDiffPath) ? "1" : "0");
    }

    public void gen() {
        this.addTinkerID();
        this.addProtectedAppFlag();
        this.addFilePatchFlag();
        Properties newProperties = new Properties();
        Iterator iterator = this.config.mPackageFields.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            newProperties.put(key, this.config.mPackageFields.get(key));
        }
        String str0 = "base package config field";
        Object os = null;
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(this.packageInfoFile, 0));
            newProperties.store(stream, str0);
            return;
        }
        finally {
            Throwable throwable = v_33;
            IOHelper.closeQuietly(stream);
            throw throwable;
        }
    }

}
