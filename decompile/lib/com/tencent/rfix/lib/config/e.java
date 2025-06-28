/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/config;

import com.tencent.rfix.lib.RFixParams;
import com.tencent.rfix.loader.c.e;
import com.tencent.rdelivery.RDelivery;
import com.tencent.rdelivery.data.RDeliveryData;
import com.tencent.rdelivery.RDeliverySetting$Builder;
import com.tencent.rdelivery.net.BaseProto$PullTarget;
import com.tencent.rdelivery.net.BaseProto$ServerType;
import com.tencent.rdelivery.RDeliverySetting;
import com.tencent.rdelivery.DependencyInjector;
import com.tencent.rdelivery.dependencyimpl.MmkvStorage$MmkvStorageFactory;
import com.tencent.rdelivery.dependencyimpl.HandlerTask;
import android.content.Context;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import androidx.annotation.NonNull;

// class: com/tencent/rfix/lib/config/e
public class e implements FullReqResultListener {
    public static int a;
    public static int b;
    final private Context c;
    final private RFixParams d;
    final private a e;
    private RDelivery f;

    public e(Context context, RFixParams params, a a) {
        super();
        this.c = context;
        this.d = params;
        this.e = a;
    }

    public void a() {
        RDelivery delivery = super.a(this.d);
        delivery.requestFullRemoteData(this);
    }

    public void onSuccess() {
        String str0 = new StringBuilder().append("fix_portal_").append(this.d.getAppId()).toString();
        RDeliveryData data = this.f.getRDeliveryDataByKey(str0);
        RFixLog.b("RFix.RDeliveryManager", new StringBuilder().append("onSuccess data: ").append(data).toString());
        if (data != null) {
            this.a(data);
        }
        else {
            this.a(e.a, null);
        }
    }

    public void onFail(@NonNull String str0) {
        RFixLog.b("RFix.RDeliveryManager", new StringBuilder().append("onFail msg: ").append(str0).toString());
        if ("config result empty".equals(str0)) {
            this.a(e.a, null);
        }
        else {
            this.a(e.b, null);
        }
    }

    private RDelivery a(RFixParams params) {
        if (this.f != null) {
            String str0 = super.a(params.getUserId());
            this.f.switchUserId(str0);
            super.a(this.f, params);
            return this.f;
        }
        else {
            if (params.getInitMMKVInternal()) {
                MMKV.initialize(this.c);
            }
            RDeliverySetting setting = new RDeliverySetting$Builder().setAppId(params.getAppId()).setAppKey(params.getAppKey()).setUserId(super.a(params.getUserId())).setDevModel(super.a(params.getDeviceModel())).setDevManufacturer(super.a(params.getDeviceManufacturer())).setHostAppVersion(super.a(params.getAppVersion(this.c))).setAndroidSystemVersion(String.valueOf(Build$VERSION.SDK_INT)).setCustomProperties(this.b(params)).setSystemId("10021").setPullTarget(BaseProto$PullTarget.APP).setCustomServerType(this.b()).setEnableDetailLog(true).setAppChannel(super.a(params.getAppChannel())).setCustomServerUrl(super.a(params.getCustomServerUrl())).build();
            DependencyInjector injector = new DependencyInjector(new f(this.c), new MmkvStorage$MmkvStorageFactory(), new HandlerTask(), new d());
            this.f = RDelivery.create(this.c, setting, injector, null);
            return this.f;
        }
    }

    private String a(String str0) {
        if (str0 == null) {
            return "";
        }
        else {
            return str0;
        }
    }

    private Map<String, String> b(RFixParams params) {
        HashMap map = new HashMap();
        Iterator iterator = params.getCustomProperties().iterator();
        while (iterator.hasNext()) {
            String str0 = (String)iterator.next();
            map.put(str0, params.getCustomProperty(str0));
        }
        return map;
    }

    private void a(RDelivery delivery, RFixParams params) {
        Iterator iterator = params.getCustomProperties().iterator();
        while (iterator.hasNext()) {
            String str0 = (String)iterator.next();
            String str1 = params.getCustomProperty(str0);
            delivery.setCustomParam(str0, str1);
        }
    }

    private BaseProto$ServerType b() {
        e e = new e(this.c);
        if (e.a) {
            return BaseProto$ServerType.TEST;
        }
        else {
            return BaseProto$ServerType.RELEASE;
        }
    }

    private void a(RDeliveryData data) {
        if (data.getConfigValue() == null) {
            RFixLog.e("RFix.RDeliveryManager", "processData config value is empty!");
        }
        else {
            Object object = null;
            try {
                int i0 = this.b(data.getDebugInfo());
                String str0 = data.getConfigValue();
                c c = new c(i0, str0);
                goto 55;
            }
            catch (Exception var_3_1) {
                RFixLog.e("RFix.RDeliveryManager", "processData fail!", var_3_1);
            }
            if (c != null) {
                super.a(e.a, c);
            }
        }
    }

    private int b(String str0) {
        int i0 = str0.indexOf(35);
        String str1 = str0.substring(0, i0);
        return Integer.parseInt(str1);
    }

    private void a(int i0, c c) {
        try {
            this.e.a(i0, c);
        }
        catch (Exception var_3_0) {
            RFixLog.e("RFix.RDeliveryManager", "updateDeliveryConfig fail!", var_3_0);
        }
    }

    static  {
        e.a = 0;
        e.b = -1;
    }

}
