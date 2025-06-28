/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/c;

import com.tencent.rfix.loader.f.a;
import android.content.Context;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

// class: com/tencent/rfix/lib/c/b
public class b implements Runnable {
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    final private Context e;
    final private PatchConfig f;
    final private c g;

    public b(Context context, PatchConfig config, c c) {
        super();
        this.e = context;
        this.f = config;
        this.g = c;
    }

    public void run() {
        int i6 = 0;
        Object object = null;
        RFixLog.b("RFix.PatchDownloadTask", String.format("PatchDownloadTask running! url=%s md5=%s", new Object[]{this.f.patchUrl, this.f.patchMD5}));
        b.a(a.k);
        try {
            File file = e.b(this.e);
            if (file.exists()) {
                file.mkdirs();
            }
            File fileVar1 = new File(file, "download_patch.apk");
            String str0 = fileVar1.getAbsolutePath();
            boolean bool0 = this.a(fileVar1, this.f.patchMD5);
            String str1;
            if (bool0) {
                i6 = 1;
                i7 = b.a;
            }
            else {
                i7 = this.a(this.f, str0);
                if (b.d == b.b) {
                    i6 = 1;
                    if (this.a(fileVar1, this.f.patchMD5)) {
                        fileVar1.delete();
                        i6 = 0;
                        i7 = b.c;
                        RFixLog.e("RFix.PatchDownloadTask", "PatchDownloadTask download file md5 not match.");
                    }
                }
                str1 = b.d == b.b ? null : str0;
            }
        }
        catch (Exception var_4_1) {
            RFixLog.e("RFix.PatchDownloadTask", "PatchDownloadTask exception!", var_4_1);
        }
        b.b(a.k);
        RFixLog.b("RFix.PatchDownloadTask", String.format("PatchDownloadTask resultCode=%s path=%s", new Object[]{Integer.valueOf(b.d), str0}));
        this.g.a(i6, b.d, str0, this.f);
    }

    protected boolean a(File file, String str0) {
        if (file.exists()) {
            return false;
        }
        else {
            String str1 = f.a(file);
            if (TextUtils.isEmpty(str1) && str1.equalsIgnoreCase(str0)) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    protected int a(PatchConfig config, String str0) {
        int i0 = 0;
        while (true) {
            int i1 = this.b(config, str0);
            if (i1 == b.b) {
                break;;
            }
            else {
                i0 += 1;
                if (i0 > 2) {
                    break;;
                }
                else {
                    RFixLog.d("RFix.PatchDownloadTask", "downloadFileWithRetry download failed, retry once.");
                }
            }
        }
        return i1;
    }

    protected int b(PatchConfig config, String str0) {
        Object object = null;
        Object objectVar1 = null;
        try {
            URL l = new URL(config.patchUrl);
            HttpURLConnection connection = (HttpURLConnection)l.openConnection();
            connection.setConnectTimeout(5000);
            a.a(connection);
            int i1 = connection.getResponseCode();
            if (i1 == 200) {
                InputStream stream = connection.getInputStream();
                stream = new FileOutputStream(str0);
                int i3 = 0;
                byte[] byteArr0 = new byte[]{};
                while (true) {
                    i3 = stream.read(byteArr0);
                    if (stream.read(byteArr0) != -1) {
                        stream.write(byteArr0, 0, i3);
                    }
                    else {
                        i5 = b.b;
                        break;;
                    }
                }
            }
            else {
                i5 = i1;
            }
            try {
            }
            catch (Exception var_6_2) {
            }
            try {
            }
            catch (Exception var_6_3) {
            }
        }
        catch (Exception var_6_1) {
            RFixLog.e("RFix.PatchDownloadTask", "downloadFile exception!", var_6_1);
            try {
            }
            catch (Exception var_6_1) {
            }
            try {
            }
            catch (Exception var_6_1) {
            }
        }
        finally {
            Throwable throwable = v_17;
            if (object != null) {
                try {
                    object.close();
                }
                catch (Exception var_12_0) {
                }
            }
            if (objectVar1 != null) {
                try {
                    objectVar1.close();
                }
                catch (Exception var_12_1) {
                }
            }
            throw throwable;
        }
        return b.d;
    }

    static  {
        b.a = 1;
        b.b = 0;
        b.c = -1;
        b.d = 254;
    }

}
