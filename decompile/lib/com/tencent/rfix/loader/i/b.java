/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/i;

import com.tencent.rfix.loader.d.a;
import com.tencent.rfix.loader.d.a$a;
import com.tencent.rfix.loader.c.e;
import android.content.Context;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// class: com/tencent/rfix/loader/i/b
public class b {
    final private static String[] a;
    private static long b;
    final private static Object c;
    final private Context d;
    private volatile boolean e;

    public b(Context context) {
        super();
        this.d = context;
    }

    public boolean a() {
        if (this.c()) {
            try {
                b.c;
                synchronized () {
                    this.e = false;
                    b.a().a(new b$1(this), a$a.b);
                    b.c.wait(3500L);
                }
            }
            catch (Throwable var_1_1) {
                RFixLog.e("RFix.RemoteVerifyTask", "remoteVerify fail!", var_1_1);
            }
            return this.e;
        }
        else {
            RFixLog.b("RFix.RemoteVerifyTask", "remoteVerify verify ignored.");
            return true;
        }
    }

    private boolean c() {
        String str0 = this.d.getPackageName();
        for (int i1 = 0; i1 < b.a.length; i1 += 1) {
            String str2 = b.a[i1];
            if (TextUtils.equals(str0, str2)) {
                return true;
            }
            else {
            }
        }
        return false;
    }

    private boolean d() {
        int i2 = false;
        try {
            long l0 = System.nanoTime();
            a a = new a(this.d, 1);
            JSONObject object = this.a(a);
            RFixLog.c("RFix.RemoteVerifyTask", new StringBuilder().append("doRemoteVerify request=").append(object).toString());
            JSONObject objectVar1 = this.a(object);
            RFixLog.c("RFix.RemoteVerifyTask", new StringBuilder().append("doRemoteVerify response=").append(objectVar1).toString());
            int i1 = objectVar1.getInt("ret_code");
            i2 = i1 == 0 ? 0 : 1;
            long l1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - l0);
            RFixLog.c("RFix.RemoteVerifyTask", String.format("doRemoteVerify retCode=%s verifyResult=%s timeCost=%s", new Object[]{Integer.valueOf(i1), Boolean.valueOf(i2), Long.valueOf(l1)}));
        }
        catch (Throwable var_2_1) {
            RFixLog.e("RFix.RemoteVerifyTask", "doRemoteVerify fail!", var_2_1);
        }
        return i2;
    }

    private JSONObject a(a a) {
        String str0 = a.a == null ? a.a : "";
        String str1 = a.b == null ? a.b : "";
        String str2 = a.c == null ? a.c : "";
        b.b = b.b + 1L;
        long l1 = System.currentTimeMillis();
        String str3 = String.format("%s-%s-%s", new Object[]{Long.valueOf(b.b), Long.valueOf(l1), str0});
        String str4 = b.a(str3, str1);
        JSONObject object = new JSONObject();
        try {
            object.put("app_id", str0);
            object.put("app_version", str2);
            object.put("platform_id", 1);
            object.put("bundle_id", this.d.getPackageName());
            object.put("sdk_version", "2.0.5");
            object.put("seq", b.b);
            object.put("time_stamp", l1);
            object.put("sign", str4);
        }
        catch (JSONException var_12_0) {
            RFixLog.e("RFix.RemoteVerifyTask", "buildRequest fail!", var_12_0);
        }
        return object;
    }

    private JSONObject a(int i0, String str0) {
        JSONObject object = new JSONObject();
        try {
            object.put("ret_code", i0);
            object.put("ret_msg", str0);
        }
        catch (JSONException var_4_0) {
            RFixLog.e("RFix.RemoteVerifyTask", "buildResponse fail!", var_4_0);
        }
        return object;
    }

    private JSONObject a(JSONObject object) {
        object = null;
        Object objectVar1 = null;
        Object objectVar2 = null;
        Object objectVar3 = null;
        Object objectVar4;
        try {
            try {
                URL l = new URL(this.e());
                HttpURLConnection connection = (HttpURLConnection)l.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(3000);
                connection.setDoOutput(true);
                connection.setDoInput(true);
                a.a(connection);
                OutputStream stream = connection.getOutputStream();
                stream.write(object.toString().getBytes());
                int i0 = connection.getResponseCode();
                RFixLog.c("RFix.RemoteVerifyTask", String.format("doRequest requestURL=%s responseCode=%s", new Object[]{l.getHost(), Integer.valueOf(i0)}));
                if (i0 == 200) {
                    stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                    objectVar4 = null;
                    StringBuilder builder = new StringBuilder();
                    while (true) {
                        String str0 = reader.readLine();
                        if (reader.readLine() != null) {
                            builder.append(str0);
                            builder.append("
");
                        }
                        else {
                            objectVar1 = new JSONObject(builder.toString());
                            break;;
                        }
                    }
                }
                else {
                    objectVar1 = super.a(254, new StringBuilder().append("responseCode=").append(i0).toString());
                }
                a.a(connection);
                e.a(stream);
                e.a(stream);
            }
            catch (SocketTimeoutException var_6_1) {
                RFixLog.e("RFix.RemoteVerifyTask", "doRequest timeout!", var_6_1);
                objectVar4 = super.a(-1, "request timeout!");
                a.a(connection);
                e.a(stream);
                e.a(objectVar2);
            }
            catch (JSONException var_6_2) {
                RFixLog.e("RFix.RemoteVerifyTask", "doRequest json fail!", var_6_2);
                objectVar3 = super.a(253, "json fail!");
            }
        }
        catch (Exception var_6_3) {
            RFixLog.e("RFix.RemoteVerifyTask", "doRequest fail!", var_6_3);
            objectVar4 = super.a(247, "unknown exception!");
        }
        finally {
            Throwable throwable = v_45;
            a.a(connection);
            e.a(stream);
            e.a(objectVar2);
            throw throwable;
        }
        return objectVar1;
    }

    private String e() {
        e e = new e(this.d);
        if (e.a) {
            return "https://t.rconfig.qq.com/trpc.rconfig.task_svr.GetConfigService/NativeLoadPatch";
        }
        else {
            return "https://content.rconfig.qq.com/trpc.rconfig.task_svr.GetConfigService/NativeLoadPatch";
        }
    }

    static /* synthetic */ Object b() {
        return b.c;
    }

    static /* synthetic */ boolean a(b b, boolean bool0) {
        b.e = bool0;
        return bool0;
    }

    static /* synthetic */ boolean a(b b) {
        return b.d();
    }

    static  {
        b.a = new String[]{"com.tencent.mtt"};
        b.b = 0L;
        b.c = new Object();
    }

}
