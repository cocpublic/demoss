/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/verify;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.TextView;
import android.widget.Button;
import android.content.Context;
import com.tencent.rfix.lib.RFix;
import com.tencent.rfix.lib.RFixParams;
import com.tencent.rfix.loader.entity.RFixLoadResult;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import androidx.annotation.NonNull;

// class: com/tencent/rfix/lib/verify/a
public class a implements Handler$Callback {
    final private Handler a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private Button o;

    protected a(Context context) {
        super(context);
        this.a = new Handler(context.getMainLooper(), this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R$layout.dialog_auto_verify);
        this.a();
        this.a.sendEmptyMessage(100);
    }

    public boolean handleMessage(@NonNull Message message) {
        if (message.what == 100) {
            if (RFix.isInitialized()) {
                this.b();
            }
            else {
                RFixLog.b("RFix.AutoVerifyDialog", "RFix has not initialized, delay...");
                this.a.sendEmptyMessageDelayed(100, 500L);
            }
        }
        return true;
    }

    private void a() {
        this.b = (TextView)this.findViewById(R$id.txt_app_id);
        this.c = (TextView)this.findViewById(R$id.txt_app_id_result);
        this.d = (TextView)this.findViewById(R$id.txt_app_key);
        this.e = (TextView)this.findViewById(R$id.txt_app_key_result);
        this.f = (TextView)this.findViewById(R$id.txt_app_version);
        this.g = (TextView)this.findViewById(R$id.txt_app_version_result);
        this.h = (TextView)this.findViewById(R$id.txt_dex_patch);
        this.i = (TextView)this.findViewById(R$id.txt_dex_patch_result);
        this.j = (TextView)this.findViewById(R$id.txt_lib_patch);
        this.k = (TextView)this.findViewById(R$id.txt_lib_patch_result);
        this.l = (TextView)this.findViewById(R$id.txt_res_patch);
        this.m = (TextView)this.findViewById(R$id.txt_res_patch_result);
        this.n = (TextView)this.findViewById(R$id.txt_auto_verify_result);
        this.o = (Button)this.findViewById(R$id.btn_exit);
        this.o.setOnClickListener(a::a);
    }

    private void b() {
        RFixParams params = RFix.getInstance().getParams();
        RFixLoadResult result = RFix.getInstance().getLoadResult();
        b b = AutoVerifyPatch.a();
        String str0 = params.getAppId();
        int i0 = TextUtils.isEmpty(str0) ? 0 : 1;
        this.a(this.b, this.c, str0, i0);
        String str1 = params.getAppKey();
        int i1 = TextUtils.isEmpty(str1) ? 0 : 1;
        this.a(this.d, this.e, str1, i1);
        String str2 = params.getRealAppVersion(this.getContext());
        boolean bool0 = this.a(str2);
        this.a(this.f, this.g, str2, bool0);
        this.a(this.h, this.i, b.b, result);
        this.a(this.j, this.k, b.c, result);
        this.a(this.l, this.m, b.d, result);
        int i2 = i0 != 0 && i1 != 0 && bool0 && b.a ? 0 : 1;
        this.a(this.n, i2);
    }

    private boolean a(String str0) {
        if (TextUtils.isEmpty(str0)) {
            return false;
        }
        else {
            Pattern pattern = Pattern.compile("\d+(\.\d+)+");
            return pattern.matcher(str0).find();
        }
    }

    private void a(TextView view, TextView viewVar1, String str0, boolean bool0) {
        if (view != null) {
            view.setText(str0);
        }
        if (viewVar1 != null) {
            viewVar1.setText(bool0 ? "失败" : "成功");
            viewVar1.setBackgroundColor(bool0 ? -65536 : -16711936);
        }
    }

    private void a(TextView view, TextView viewVar1, b$a b$a, RFixLoadResult result) {
        if (view != null) {
            String str0 = String.format("状态=%s 结果=%s 数据=%s", new Object[]{b$a.a ? "关闭" : "开启", b$a.b ? "失败" : "成功", b$a.c});
            view.setText(str0);
        }
        if (viewVar1 != null) {
            if (result.isLoaderSuccess()) {
                viewVar1.setText("未加载");
                viewVar1.setBackgroundColor(-7829368);
            }
            else if (b$a.a) {
                viewVar1.setText("关闭");
                viewVar1.setBackgroundColor(-7829368);
            }
            else {
                viewVar1.setText(b$a.b ? "失败" : "成功");
                viewVar1.setBackgroundColor(b$a.b ? -65536 : -16711936);
            }
        }
    }

    private void a(TextView view, boolean bool0) {
        if (view != null) {
            view.setText(bool0 ? "自动验证: 失败" : "自动验证: 成功");
            view.setBackgroundColor(bool0 ? -65536 : -16711936);
        }
    }

    private /* synthetic */ void a(View view) {
        this.dismiss();
    }

}
