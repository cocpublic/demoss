/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import androidx.annotation.Keep;

// class: com/tencent/rfix/lib/RFixParams
@Keep
public class RFixParams {
    final private static String TAG;
    final private boolean enable;
    final private String appId;
    final private String appKey;
    private String appVersion;
    private boolean autoRequestEnable;
    private boolean initMMKVInternal;
    private boolean mainVerifyEnable;
    private String deviceId;
    private String deviceManufacturer;
    private String deviceModel;
    private String userId;
    private String appChannel;
    private String customServerUrl;
    final private Map<String, String> customProperties;
    private String dummyAppVersion;
    private String dummyUserId;

    public RFixParams(String str0, String str1) {
        super(1, str0, str1);
    }

    public RFixParams(boolean bool0, String str0, String str1) {
        super();
        this.enable = bool0;
        this.appId = str0;
        this.appKey = str1;
        this.autoRequestEnable = true;
        this.initMMKVInternal = true;
        this.mainVerifyEnable = true;
        this.customProperties = new HashMap();
    }

    public boolean isEnable() {
        return this.enable;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public RFixParams setAutoRequestEnable(boolean bool0) {
        this.autoRequestEnable = bool0;
        return this;
    }

    public boolean getAutoRequestEnable() {
        return this.autoRequestEnable;
    }

    public RFixParams setInitMMKVInternal(boolean bool0) {
        this.initMMKVInternal = bool0;
        return this;
    }

    public boolean getInitMMKVInternal() {
        return this.initMMKVInternal;
    }

    public RFixParams setMainVerifyEnable(boolean bool0) {
        this.mainVerifyEnable = bool0;
        return this;
    }

    public boolean getMainVerifyEnable() {
        return this.mainVerifyEnable;
    }

    public RFixParams setAppVersion(String str0) {
        this.appVersion = str0;
        return this;
    }

    public String getAppVersion(Context context) {
        if (TextUtils.isEmpty(this.dummyAppVersion)) {
            return this.dummyAppVersion;
        }
        else {
            return this.getRealAppVersion(context);
        }
    }

    public String getRealAppVersion(Context context) {
        if (TextUtils.isEmpty(this.appVersion)) {
            return this.appVersion;
        }
        else {
            try {
                String str0 = context.getPackageName();
                PackageManager manager = context.getPackageManager();
                PackageInfo info = manager.getPackageInfo(str0, 0);
                this.appVersion = info.versionName;
            }
            catch (Exception var_2_1) {
            }
            return this.appVersion;
        }
    }

    public RFixParams setAppChannel(String str0) {
        this.appChannel = str0;
        return this;
    }

    public String getAppChannel() {
        return this.appChannel;
    }

    public RFixParams setCustomServerUrl(String str0) {
        this.customServerUrl = str0;
        return this;
    }

    public String getCustomServerUrl() {
        return this.customServerUrl;
    }

    public RFixParams setDeviceId(String str0) {
        this.deviceId = str0;
        return this;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public RFixParams setDeviceManufacturer(String str0) {
        this.deviceManufacturer = str0;
        return this;
    }

    public String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }

    public RFixParams setDeviceModel(String str0) {
        this.deviceModel = str0;
        return this;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public RFixParams setUserId(String str0) {
        this.userId = str0;
        return this;
    }

    public String getUserId() {
        if (TextUtils.isEmpty(this.dummyUserId)) {
            return this.dummyUserId;
        }
        else {
            return this.userId;
        }
    }

    public RFixParams setCustomProperty(String str0, String str1) {
        if (TextUtils.isEmpty(str0)) {
            RFixLog.e("RFix.RFixParams", "setCustomProperty custom property not support empty string!");
            return this;
        }
        else {
            this.customProperties.put(str0, str1);
            return this;
        }
    }

    public Set<String> getCustomProperties() {
        return this.customProperties.keySet();
    }

    public String getCustomProperty(String str0) {
        return (String)this.customProperties.get(str0);
    }

    public RFixParams setDummyAppVersion(String str0) {
        this.dummyAppVersion = str0;
        return this;
    }

    public RFixParams setDummyUserId(String str0) {
        this.dummyUserId = str0;
        return this;
    }

    public String toString() {
        return new StringBuilder().append("RFixParams{enable=").append(this.enable).append(", appId='").append(this.appId).append(39).append(", appKey='").append(this.appKey).append(39).append(", appVersion='").append(this.appVersion).append(39).append(", autoRequestEnable=").append(this.autoRequestEnable).append(", initMMKVInternal=").append(this.initMMKVInternal).append(", deviceId='").append(this.deviceId).append(39).append(", deviceManufacturer='").append(this.deviceManufacturer).append(39).append(", deviceModel='").append(this.deviceModel).append(39).append(", userId='").append(this.userId).append(39).append(", customProperties=").append(this.customProperties).append(", dummyAppVersion='").append(this.dummyAppVersion).append(39).append(", dummyUserId='").append(this.dummyUserId).append(39).append(125).toString();
    }

}
