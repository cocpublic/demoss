/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/dev;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Button;
import android.text.method.MovementMethod;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.Intent;
import android.annotation.SuppressLint;
import com.tencent.rfix.lib.RFix;
import com.tencent.rfix.lib.RFixParams;
import com.tencent.rfix.loader.h.h$b;
import com.tencent.rfix.loader.h.h$a;
import com.tencent.rfix.loader.c.e;
import com.tencent.rfix.loader.entity.RFixLoadResult;
import com.tencent.rfix.loader.entity.a;
import java.util.Set;
import java.util.Iterator;
import java.util.Date;
import java.text.SimpleDateFormat;
import androidx.annotation.NonNull;
import androidx.annotation.Keep;

// class: com/tencent/rfix/lib/dev/AbsRFixDevActivity
@Keep
public abstract class AbsRFixDevActivity implements Handler$Callback, View$OnClickListener, RFixListener {
    final private static String TAG;
    final private static int MSG_INIT;
    protected StringBuilder logContents;
    protected Handler uiHandler;
    protected TextView appInfoTxt;
    protected TextView patchInfoTxt;
    protected TextView logInfoTxt;
    protected CheckBox disableConfigCheck;
    protected CheckBox autoVerifyCheck;
    protected CheckBox testEnvCheck;
    protected Button requestConfigBtn;
    protected Button cleanPatchBtn;
    protected Button restartAppBtn;
    protected boolean hasAddListener;
    protected boolean hasInitViews;

    public AbsRFixDevActivity() {
        super();
        this.logContents = null;
        this.uiHandler = null;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R$layout.activity_rfix_dev);
        this.logContents = new StringBuilder();
        this.uiHandler = new Handler(this.getMainLooper(), this);
        this.uiHandler.sendEmptyMessage(100);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.uiHandler.removeMessages(100);
        if (this.hasAddListener) {
            RFix.getInstance().removeListener(this);
            this.hasAddListener = false;
        }
    }

    public void onClick(View view) {
        int i0 = view.getId();
        if (i0 == R$id.btn_request_config) {
            this.requestConfig();
        }
        else if (i0 == R$id.btn_clean_patch) {
            this.cleanPatch();
        }
        else {
            if (i0 == R$id.btn_restart_app) {
                this.restartApp();
            }
        }
    }

    public boolean handleMessage(@NonNull Message message) {
        switch(message.what) {
            case 100: {
                if (RFix.isInitialized()) {
                    this.initDebugViews();
                    break;;
                }
                else {
                    RFixLog.b("RFix.AbsRFixDevActivity", "RFix has not initialized, delay...");
                    this.uiHandler.sendEmptyMessageDelayed(100, 500L);
                    break;;
                }
            }
        }
        return true;
    }

    public void onConfig(boolean bool0, int i0, PatchConfig config) {
        this.runOnUiThread((bool0, config, i0) -> {
            String str0 = bool0 ? String.format("配置获取失败: 错误码=%s", new Object[]{Integer.valueOf(i0)}) : String.format("获得补丁配置: 任务ID=%s", new Object[]{Integer.valueOf(config.configId)});
            this.addLogInfo(var_4_0);
        });
    }

    public void onDownload(boolean bool0, int i0, PatchConfig config, String str0) {
        this.runOnUiThread((bool0, str0, config, i0) -> {
            String str3;
            if (bool0) {
                int i1 = str0.lastIndexOf(47);
                String str1 = str0.substring(i1 + 1);
                String str2 = config.patchMD5.substring(0, 8);
                str3 = String.format("补丁下载成功: 文件名=%S MD5=%s", new Object[]{str1, str2});
            }
            else {
                str3 = String.format("补丁下载失败: 错误码=%s", new Object[]{Integer.valueOf(i0)});
            }
            this.addLogInfo(str3);
        });
    }

    public void onInstall(boolean bool0, int i0, RFixPatchResult result) {
        this.runOnUiThread((bool0, result) -> {
            String str2;
            if (bool0) {
                int i0 = result.configId;
                String str0 = result.patchType;
                String str1 = result.patchVersion.substring(0, 8);
                str2 = String.format("补丁安装成功: 任务ID=%s 类型=%s 版本=%s", new Object[]{Integer.valueOf(i0), str0, str1});
            }
            else {
                str2 = String.format("补丁安装失败: 错误码=%s", new Object[]{result.a});
            }
            this.addLogInfo(str2);
        });
    }

    protected void initDebugViews() {
        e e = new e(this);
        String str0 = this.getAppInfo();
        this.appInfoTxt = (TextView)this.findViewById(R$id.txt_app_info);
        this.appInfoTxt.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.appInfoTxt.setText(str0);
        String str1 = this.getLoadResult();
        this.patchInfoTxt = (TextView)this.findViewById(R$id.txt_patch_info);
        this.patchInfoTxt.setText(str1);
        this.logInfoTxt = (TextView)this.findViewById(R$id.txt_log_info);
        this.logInfoTxt.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.disableConfigCheck = (CheckBox)this.findViewById(R$id.check_disable_config);
        this.disableConfigCheck.setChecked(e.b);
        this.autoVerifyCheck = (CheckBox)this.findViewById(R$id.check_auto_verify);
        this.autoVerifyCheck.setChecked(e.c);
        this.testEnvCheck = (CheckBox)this.findViewById(R$id.check_test_env);
        this.testEnvCheck.setChecked(e.a);
        this.requestConfigBtn = (Button)this.findViewById(R$id.btn_request_config);
        this.requestConfigBtn.setOnClickListener(this);
        this.cleanPatchBtn = (Button)this.findViewById(R$id.btn_clean_patch);
        this.cleanPatchBtn.setOnClickListener(this);
        this.restartAppBtn = (Button)this.findViewById(R$id.btn_restart_app);
        this.restartAppBtn.setOnClickListener(this);
        RFix.getInstance().addListener(this);
        this.hasAddListener = true;
        String str2 = String.format("进程名：%s", new Object[]{g.c(this)});
        this.addLogInfo(str2);
        this.hasInitViews = true;
    }

    protected String getAppInfo() {
        Object object = null;
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String str0 = manager.getApplicationLabel(info.applicationInfo).toString();
            goto 35;
        }
        catch (Exception var_2_2) {
        }
        RFixParams params = RFix.getInstance().getParams();
        StringBuilder builder = new StringBuilder();
        String str1 = String.format("应用名称: %s   应用版本: %s", new Object[]{str0, params.getAppVersion(this)});
        builder.append(str1).append("
");
        String str2 = String.format("设备厂商: %s   设备型号: %s   系统版本: %s", new Object[]{params.getDeviceManufacturer(), params.getDeviceModel(), Integer.valueOf(Build$VERSION.SDK_INT)});
        builder.append(str2).append("
");
        String str3 = String.format("AppId: %s   UserId: %s", new Object[]{params.getAppId(), params.getUserId()});
        builder.append(str3).append("
");
        builder.append("自定义属性: ");
        Set set = params.getCustomProperties();
        if (set.isEmpty()) {
            builder.append("无");
        }
        else {
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                String str4 = (String)iterator.next();
                builder.append(String.format("%s=%s ", new Object[]{str4, params.getCustomProperty(str4)}));
            }
        }
        return builder.toString();
    }

    protected String getLoadResult() {
        RFixLoadResult result = RFix.getInstance().getLoadResult();
        String str3;
        if (result.isLoaderSuccess()) {
            a a = result.e;
            String str0 = String.valueOf(a.d);
            String str1 = a.h;
            String str2 = a.g.substring(0, 8);
            str3 = String.format("补丁加载成功: 任务ID=%s 类型=%s 版本=%s", new Object[]{str0, str1, str2});
        }
        else {
            str3 = String.format("补丁加载失败: 错误码=%s", new Object[]{result.a});
        }
        return str3;
    }

    @SuppressLint({"SimpleDateFormat"})
    protected void addLogInfo(String str0) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        String str1 = String.format("%s: %s
", new Object[]{format.format(date), str0});
        this.logContents.insert(0, str1);
        TextView view = (TextView)this.findViewById(R$id.txt_log_info);
        view.setText(this.logContents);
    }

    protected void requestConfig() {
        this.addLogInfo("请求补丁配置...");
        RFix fix = RFix.getInstance();
        fix.requestConfig();
    }

    protected void cleanPatch() {
        RFix fix = RFix.getInstance();
        fix.cleanPatch();
        this.addLogInfo("安装补丁已清除.");
    }

    protected void restartApp() {
        Intent intent = new Intent(this, this.getClass());
        intent.addFlags(268435456);
        this.startActivity(intent);
        this.savePropToFile();
        Process.killProcess(Process.myPid());
    }

    protected void onPause() {
        super.onPause();
        this.savePropToFile();
    }

    private void savePropToFile() {
        if (this.hasInitViews) {
        }
        else {
            e e = new e(this);
            e.b = this.disableConfigCheck.isChecked();
            e.a = this.testEnvCheck.isChecked();
            e.c = this.autoVerifyCheck.isChecked();
            e.c();
        }
    }

    private /* synthetic */ void lambda$onInstall$2(boolean bool0, RFixPatchResult result) {
        String str2;
        if (bool0) {
            int i0 = result.configId;
            String str0 = result.patchType;
            String str1 = result.patchVersion.substring(0, 8);
            str2 = String.format("补丁安装成功: 任务ID=%s 类型=%s 版本=%s", new Object[]{Integer.valueOf(i0), str0, str1});
        }
        else {
            str2 = String.format("补丁安装失败: 错误码=%s", new Object[]{result.a});
        }
        this.addLogInfo(str2);
    }

    private /* synthetic */ void lambda$onDownload$1(boolean bool0, String str0, PatchConfig config, int i0) {
        String str3;
        if (bool0) {
            int i1 = str0.lastIndexOf(47);
            String str1 = str0.substring(i1 + 1);
            String str2 = config.patchMD5.substring(0, 8);
            str3 = String.format("补丁下载成功: 文件名=%S MD5=%s", new Object[]{str1, str2});
        }
        else {
            str3 = String.format("补丁下载失败: 错误码=%s", new Object[]{Integer.valueOf(i0)});
        }
        this.addLogInfo(str3);
    }

    private /* synthetic */ void lambda$onConfig$0(boolean bool0, PatchConfig config, int i0) {
        String str0 = bool0 ? String.format("配置获取失败: 错误码=%s", new Object[]{Integer.valueOf(i0)}) : String.format("获得补丁配置: 任务ID=%s", new Object[]{Integer.valueOf(config.configId)});
        this.addLogInfo(var_4_0);
    }

}
