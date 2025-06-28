/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/config;

import com.tencent.rdelivery.dependencyimpl.HttpsURLConnectionNetwork;
import com.tencent.raft.standard.net.IRNetwork$NetworkStatus;
import com.tencent.raft.standard.net.IRNetwork$ResultInfo;
import com.tencent.raft.standard.net.IRNetwork$ResultInfo$ErrorType;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.util.Set;
import java.util.Iterator;
import java.nio.charset.Charset;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// class: com/tencent/rfix/lib/config/f
public class f implements IRNetwork {
    final private HttpsURLConnectionNetwork a;

    public f(@NonNull Context context) {
        super();
        this.a = new HttpsURLConnectionNetwork(context);
    }

    public IRNetwork$NetworkStatus getNetworkStatus() {
        return this.a.getNetworkStatus();
    }

    public void requestWithMethod(@NotNull IRNetwork$HttpMethod method, @NotNull String str0, @NotNull Map<String, String> map, @NotNull Map<String, String> mapVar1, @Nullable Object object, @Nullable IRNetwork$INetworkResult result) {
        RFixLog.b("RFix.RDeliveryNetwork", new StringBuilder().append("requestWithMethod threadId=").append(Thread.currentThread().getId()).toString());
        Object objectVar1 = null;
        try {
            URL l = new URL(str0);
            HttpURLConnection connection = (HttpURLConnection)l.openConnection();
            connection.setRequestMethod(method.name());
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String str1 = (String)iterator.next();
                connection.setRequestProperty(str1, (String)map.get(str1));
            }
            a.a(connection);
            byte[] byteArr0 = object.toString().getBytes(StandardCharsets.UTF_8);
            connection.getOutputStream().write(byteArr0);
            int i0 = connection.getResponseCode();
            RFixLog.b("RFix.RDeliveryNetwork", new StringBuilder().append("requestWithMethod responseCode=").append(i0).toString());
            if (i0 == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder builder = new StringBuilder();
                while (true) {
                    String str2 = reader.readLine();
                    if (reader.readLine() != null) {
                        builder.append(str2);
                    }
                    else if (if (result != null ) break; /* target: 285 */) {
                    }
                }
                result.onSuccess(builder.toString());
            }
            else {
                IRNetwork$ResultInfo info = new IRNetwork$ResultInfo();
                info.setErrorType(IRNetwork$ResultInfo$ErrorType.HTTP_ERROR);
                info.setErrorCode(Integer.valueOf(i0));
                if (result != null) {
                    result.onFail(info);
                }
            }
            return;
        }
        catch (Exception var_8_1) {
            this.a(var_8_1, result);
            return;
        }
        finally {
            Throwable throwable = v_29;
            a.a(connection);
            throw throwable;
        }
    }

    private void a(Exception exception, @Nullable IRNetwork$INetworkResult result) {
        RFixLog.e("RFix.RDeliveryNetwork", "handleException!", exception);
        IRNetwork$ResultInfo info = new IRNetwork$ResultInfo();
        info.setErrorType(IRNetwork$ResultInfo$ErrorType.OTHER_ERROR);
        info.setErrorMessage(exception.getClass().getSimpleName());
        if (result != null) {
            result.onFail(info);
        }
    }

}
