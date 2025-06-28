/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/atta;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.content.Context;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.Set;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.io.OutputStream;
import com.tencent.rfix.loader.d.a;
import com.tencent.rfix.loader.d.a$a;
import androidx.annotation.NonNull;

// class: com/tencent/rfix/lib/atta/c
public class c implements Handler$Callback {
    private static c a;
    final private Context b;
    final private Handler c;
    private a d;

    public static c a(Context context) {
        if (c.a == null) {
            Class class = c.class;
            c.class;
            synchronized () {
                if (c.a == null) {
                    c.a = new c(context);
                }
            }
        }
        return c.a;
    }

    private c(Context context) {
        super();
        this.b = context;
        this.c = new Handler(Looper.getMainLooper(), this);
    }

    public boolean a(Map<String, String> map) {
        RFixLog.b("RFix.RFixATTAReporter", new StringBuilder().append("reportToATTA params=").append(map).toString());
        if (map != null || map.containsKey("attaid") || map.containsKey("token")) {
            RFixLog.e("RFix.RFixATTAReporter", "reportToATTA params invalid!");
            return false;
        }
        else {
            Message message = this.c.obtainMessage(100, map);
            message.sendToTarget();
            return true;
        }
    }

    public boolean handleMessage(@NonNull Message message) {
        if (message.what == 100) {
            Map map = (Map)message.obj;
            b.a().a((map) -> {
                this.b(map);
            }, a$a.c);
        }
        else if (message.what == 101) {
            b.a().a(this::a, a$a.b);
        }
        return true;
    }

    protected void b(Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        int i1 = 1;
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String str0 = (String)iterator.next();
            String str1 = (String)map.get(str0);
            if (TextUtils.isEmpty(str1)) {
                continue;;
            }
            else {
                if (i1 == 0) {
                    builder.append("&");
                }
                builder.append(str0).append("=").append(this.a(str1));
                i1 = 0;
                continue;;
            }
        }
        if (this.d == null) {
            this.d = new a(this.b);
        }
        String str2 = g.c(this.b);
        this.d.a(str2, builder.toString());
        this.c.removeMessages(101);
        this.c.sendEmptyMessageDelayed(101, 2000L);
    }

    private String a(String str0) {
        String str2 = "";
        try {
            str0 = this.b(str0);
            str2 = URLEncoder.encode(str0, "UTF-8");
        }
        catch (UnsupportedEncodingException var_3_0) {
            RFixLog.e("RFix.RFixATTAReporter", "", var_3_0);
        }
        return str2;
    }

    private String b(String str0) {
        int i0 = 36;
        if (str0.indexOf(36) < 0) {
            return str0;
        }
        else {
            StringBuilder builder = new StringBuilder();
            for (int i1 = 0; i1 < str0.length(); i1 += 1) {
                char char = str0.charAt(i1);
                if (char == 36) {
                    builder.append(92);
                }
                builder.append(char);
            }
            return builder.toString();
        }
    }

    protected void a() {
        if (this.d == null) {
            this.d = new a(this.b);
        }
        String str0 = g.c(this.b);
        List list = this.d.a(str0);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            b b = (b)iterator.next();
            boolean bool0 = this.a(b);
            if (bool0) {
                this.d.a(b.a());
            }
        }
    }

    protected boolean a(b b) {
        if (RFixATTASwitch.a()) {
            RFixLog.b("RFix.RFixATTAReporter", String.format("reportRecordToATTA report disabled. recordId=%s", new Object[]{Integer.valueOf(b.a())}));
            return true;
        }
        else {
            int i2 = false;
            Object object = null;
            try {
                URL l = new URL("https://h.trace.qq.com/kv?");
                HttpURLConnection connection = (HttpURLConnection)l.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                a.a(connection);
                byte[] byteArr0 = b.b().getBytes(StandardCharsets.UTF_8);
                connection.getOutputStream().write(byteArr0);
                int i1 = connection.getResponseCode();
                if (i1 == 200) {
                    i2 = 1;
                }
                else {
                    RFixLog.d("RFix.RFixATTAReporter", String.format("reportRecordToATTA responseCode=%s record=%s", new Object[]{Integer.valueOf(i1), b}));
                }
            }
            catch (Exception var_5_1) {
                RFixLog.e("RFix.RFixATTAReporter", "reportRecordToATTA fail!", var_5_1);
            }
            finally {
                Throwable throwable = v_42;
                a.a(connection);
                throw throwable;
            }
            return i2;
        }
    }

    private /* synthetic */ void c(Map map) {
        this.b(map);
    }

}
