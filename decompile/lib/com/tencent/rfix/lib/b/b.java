/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/b;

import android.os.Handler;
import android.os.Looper;
import android.content.Context;
import android.annotation.SuppressLint;
import java.util.Map;
import java.util.HashMap;
import com.tencent.rfix.lib.atta.c;
import com.tencent.rfix.lib.RFix;
import com.tencent.rfix.lib.RFixParams;
import androidx.annotation.NonNull;

// class: com/tencent/rfix/lib/b/b
public class b implements Handler$Callback {
    @SuppressLint({"StaticFieldLeak"})
    private static volatile b a;
    final private Context b;
    final private Handler c;
    final private a d;
    private int e;
    private int f;
    private int g;

    public static b a(Context context) {
        if (b.a == null) {
            Class class = b.class;
            b.class;
            synchronized () {
                if (b.a == null) {
                    b.a = new b(context);
                }
            }
        }
        return b.a;
    }

    private b(Context context) {
        super();
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.b = context;
        this.c = new Handler(context.getMainLooper(), this);
        this.d = new a(context);
    }

    public boolean handleMessage(@NonNull Message message) {
        if (message.what == 100) {
            this.a();
        }
        return true;
    }

    public void a(int i0, boolean bool0) {
        RFixLog.b("RFix.TaskCoveredReporter", new StringBuilder().append("onLoadCovered versionId=").append(i0).append(", effectImmediate=").append(bool0).toString());
        this.g = i0;
        if (bool0) {
            this.c.removeMessages(100);
            this.c.sendEmptyMessage(100);
        }
    }

    public void a(int i0) {
        RFixLog.b("RFix.TaskCoveredReporter", new StringBuilder().append("onConfigCovered versionId=").append(i0).toString());
        this.e = i0;
        if (this.e == 0) {
            this.f = 0;
            this.c.sendEmptyMessage(100);
        }
        else {
            this.c.sendEmptyMessageDelayed(100, 5000L);
        }
    }

    public void b(int i0) {
        RFixLog.b("RFix.TaskCoveredReporter", new StringBuilder().append("onInstallCovered versionId=").append(i0).toString());
        this.f = i0;
        this.c.removeMessages(100);
        this.c.sendEmptyMessage(100);
    }

    private void a() {
        Map map = this.b();
        boolean bool0 = super.a(map);
        if (bool0) {
            this.d.a(this.e);
        }
        boolean bool1 = this.b(map);
        if (bool1) {
            this.d.b(this.f);
        }
        boolean bool2 = this.c(map);
        if (bool2) {
            this.d.c(this.g);
        }
        if (bool0 || bool1 || bool2) {
            c.a(this.b).a(map);
            this.d.c();
        }
        else {
            RFixLog.c("RFix.TaskCoveredReporter", "checkAndReportDailyCovered no version changed.");
        }
    }

    private Map<String, String> b() {
        String str0 = "com".equals("com") ? "0f500075739" : "05600079115";
        String str1 = "4334229441";
        RFixParams params = RFix.getInstance().getParams();
        HashMap map = new HashMap();
        map.put("attaid", var_1_0);
        map.put("token", var_2_0);
        map.put("app_id", params.getAppId());
        map.put("user_id", params.getUserId());
        map.put("device_id", params.getDeviceId());
        map.put("event_name", "DailyCovered");
        return map;
    }

    private boolean a(Map<String, String> map) {
        int i2 = false;
        if (this.e != -1) {
            int i1 = this.d.a();
            if (i1 != this.e) {
                map.put("old_config_version", String.valueOf(i1));
                map.put("new_config_version", String.valueOf(this.e));
                i2 = 1;
                RFixLog.c("RFix.TaskCoveredReporter", new StringBuilder().append("checkIfConfigVersionChanged config version changed, old: ").append(i1).append(" new: ").append(this.e).toString());
            }
        }
        return i2;
    }

    private boolean b(Map<String, String> map) {
        int i2 = false;
        if (this.f != -1) {
            int i1 = this.d.d();
            if (i1 != this.f) {
                map.put("old_install_version", String.valueOf(i1));
                map.put("new_install_version", String.valueOf(this.f));
                i2 = 1;
                RFixLog.c("RFix.TaskCoveredReporter", new StringBuilder().append("checkIfInstallVersionChanged install version changed, old: ").append(i1).append(" new: ").append(this.f).toString());
            }
        }
        return i2;
    }

    private boolean c(Map<String, String> map) {
        int i2 = false;
        if (this.g != -1) {
            int i1 = this.d.e();
            if (i1 != this.g) {
                map.put("old_load_version", String.valueOf(i1));
                map.put("new_load_version", String.valueOf(this.g));
                i2 = 1;
                RFixLog.c("RFix.TaskCoveredReporter", new StringBuilder().append("checkIfLoadVersionChanged load version changed, old: ").append(i1).append(" new: ").append(this.g).toString());
            }
        }
        return i2;
    }

}
