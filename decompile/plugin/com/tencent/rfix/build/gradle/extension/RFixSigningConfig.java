/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/extension;

import java.io.File;

// class: com/tencent/rfix/build/gradle/extension/RFixSigningConfig
class RFixSigningConfig implements SigningConfig {
    final protected String mName;
    private File mStoreFile;
    private String mStorePassword;
    private String mKeyAlias;
    private String mKeyPassword;
    private String mStoreType;
    private boolean mV1SigningEnabled;
    private boolean mV2SigningEnabled;

     RFixSigningConfig(String name) {
        super();
        this.mStoreFile = null;
        this.mStorePassword = null;
        this.mKeyAlias = null;
        this.mKeyPassword = null;
        this.mStoreType = KeyStore.getDefaultType();
        this.mV1SigningEnabled = true;
        this.mV2SigningEnabled = true;
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }

    public File getStoreFile() {
        return this.mStoreFile;
    }

    public RFixSigningConfig setStoreFile(File storeFile) {
        this.mStoreFile = storeFile;
        return this;
    }

    public String getStorePassword() {
        return this.mStorePassword;
    }

    public RFixSigningConfig setStorePassword(String storePassword) {
        this.mStorePassword = storePassword;
        return this;
    }

    public String getKeyAlias() {
        return this.mKeyAlias;
    }

    public RFixSigningConfig setKeyAlias(String keyAlias) {
        this.mKeyAlias = keyAlias;
        return this;
    }

    public String getKeyPassword() {
        return this.mKeyPassword;
    }

    RFixSigningConfig setKeyPassword(String keyPassword) {
        this.mKeyPassword = keyPassword;
        return this;
    }

    public String getStoreType() {
        return this.mStoreType;
    }

    RFixSigningConfig setStoreType(String storeType) {
        this.mStoreType = storeType;
        return this;
    }

    public boolean isV1SigningEnabled() {
        return this.mV1SigningEnabled;
    }

    public void setV1SigningEnabled(boolean enabled) {
        this.mV1SigningEnabled = enabled;
    }

    public boolean isV2SigningEnabled() {
        return this.mV2SigningEnabled;
    }

    public void setV2SigningEnabled(boolean enabled) {
        this.mV2SigningEnabled = enabled;
    }

    public boolean isSigningReady() {
        if (this.mStoreFile != null && this.mStorePassword != null && this.mKeyAlias != null && this.mKeyPassword != null) {
            return true;
        }
        else {
            return false;
        }
    }

}
