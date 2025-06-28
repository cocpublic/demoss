/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/g;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

// class: com/tencent/tinker/lib/g/b
public class b {
    private static b a;
    private boolean b;
    private File c;
    private File d;
    private Context e;
    private int f;

    public b(Context context) {
        super();
        this.b = true;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 20;
        this.e = context;
        this.c = new File(h.b(context), "patch.retry");
        this.d = new File(h.b(context), "temp.apk");
    }

    public static b a(Context context) {
        if (b.a == null) {
            b.a = new b(context);
        }
        return b.a;
    }

    public void a(boolean bool0) {
        this.b = bool0;
    }

    public void a(Intent intent) {
        if (this.b) {
            n.c("Tinker.UpgradePatchRetry", "onPatchServiceStart retry disabled, just return", new Object[]{});
        }
        else if (intent == null) {
            n.d("Tinker.UpgradePatchRetry", "onPatchServiceStart intent is null, just return", new Object[]{});
        }
        else {
            String str0 = TinkerPatchService.a(intent);
            if (str0 == null) {
                n.c("Tinker.UpgradePatchRetry", "onPatchServiceStart patch path is null, just return", new Object[]{});
            }
            else {
                File file = new File(str0);
                String str1 = h.f(file);
                if (str1 == null) {
                    n.c("Tinker.UpgradePatchRetry", "onPatchServiceStart patch md5 is null, just return", new Object[]{});
                }
                else {
                    b$a b$a;
                    if (this.c.exists()) {
                        b$a = b$a.a(this.c);
                        if (b$a.a != null || b$a.b != null || str1.equals(b$a.a)) {
                            super.a(file);
                            b$a.a = str1;
                            b$a.b = "1";
                        }
                        else {
                            int i0 = Integer.parseInt(b$a.b);
                            if (i0 >= this.f) {
                                h.c(this.d);
                                n.c("Tinker.UpgradePatchRetry", "onPatchServiceStart retry more than max count, delete retry info file!", new Object[]{});
                            }
                            else {
                                b$a.b = String.valueOf(i0 + 1);
                            }
                        }
                    }
                    else {
                        super.a(file);
                        b$a = new b$a(str1, "1");
                    }
                    b$a.a(this.c, b$a);
                }
            }
        }
    }

    public boolean a(String str0) {
        if (this.b) {
            n.c("Tinker.UpgradePatchRetry", "onPatchListenerCheck retry disabled, just return", new Object[]{});
            return true;
        }
        else if (this.c.exists()) {
            n.c("Tinker.UpgradePatchRetry", "onPatchListenerCheck retry file is not exist, just return", new Object[]{});
            return true;
        }
        else if (str0 == null) {
            n.c("Tinker.UpgradePatchRetry", "onPatchListenerCheck md5 is null, just return", new Object[]{});
            return true;
        }
        else {
            b$a b$a = b$a.a(this.c);
            if (str0.equals(b$a.a)) {
                int i0 = Integer.parseInt(b$a.b);
                if (i0 >= this.f) {
                    n.c("Tinker.UpgradePatchRetry", "onPatchListenerCheck, retry count %d must exceed than max retry count", new Object[]{Integer.valueOf(i0)});
                    h.c(this.d);
                    return false;
                }
            }
            return true;
        }
    }

    public boolean b(String str0) {
        if (this.b) {
            n.c("Tinker.UpgradePatchRetry", "onPatchResetMaxCheck retry disabled, just return", new Object[]{});
            return true;
        }
        else if (this.c.exists()) {
            n.c("Tinker.UpgradePatchRetry", "onPatchResetMaxCheck retry file is not exist, just return", new Object[]{});
            return true;
        }
        else if (str0 == null) {
            n.c("Tinker.UpgradePatchRetry", "onPatchResetMaxCheck md5 is null, just return", new Object[]{});
            return true;
        }
        else {
            b$a b$a = b$a.a(this.c);
            if (str0.equals(b$a.a)) {
                n.b("Tinker.UpgradePatchRetry", "onPatchResetMaxCheck, reset max check to 1", new Object[]{});
                b$a.b = "1";
                b$a.a(this.c, b$a);
            }
            return true;
        }
    }

    public void a() {
        if (this.b) {
            n.c("Tinker.UpgradePatchRetry", "onPatchServiceResult retry disabled, just return", new Object[]{});
        }
        else {
            if (this.d.exists()) {
                h.c(this.d);
            }
        }
    }

    private void a(File file) {
        if (file.getAbsolutePath().equals(this.d.getAbsolutePath())) {
        }
        else {
            n.c("Tinker.UpgradePatchRetry", "try copy file: %s to %s", new Object[]{file.getAbsolutePath(), this.d.getAbsolutePath()});
            try {
                h.a(file, this.d);
            }
            catch (IOException var_2_0) {
                n.d("Tinker.UpgradePatchRetry", "fail to copy file: %s to %s", new Object[]{file.getAbsolutePath(), this.d.getAbsolutePath()});
            }
        }
    }

    // class: com/tencent/tinker/lib/g/b$a
    class b$a {
        String a;
        String b;

         b$a(String str0, String str1) {
            super();
            this.a = str0;
            this.b = str1;
        }

        static b$a a(File file) {
            Object object = null;
            Object objectVar1 = null;
            Properties properties = new Properties();
            Object objectVar2 = null;
            try {
                FileInputStream stream = new FileInputStream(file);
                properties.load(stream);
                String str0 = properties.getProperty("md5");
                String str1 = properties.getProperty("times");
            }
            catch (IOException var_5_0) {
                n.d("Tinker.UpgradePatchRetry", new StringBuilder().append("fail to readRetryProperty:").append(var_5_0).toString(), new Object[]{});
            }
            finally {
                Throwable throwable = v_16;
                b.a(stream);
                throw throwable;
            }
            return new b$a(str0, str1);
        }

        static void a(File file, b$a b$a) {
            if (b$a == null) {
                return;
            }
            else {
                File fileVar1 = file.getParentFile();
                if (fileVar1.exists()) {
                    fileVar1.mkdirs();
                }
                Properties properties = new Properties();
                properties.put("md5", b$a.a);
                properties.put("times", b$a.b);
                Object object = null;
                try {
                    FileOutputStream stream = new FileOutputStream(file, 0);
                    properties.store(stream, null);
                }
                catch (Exception var_5_0) {
                    n.a("Tinker.UpgradePatchRetry", var_5_0, "retry write property fail", new Object[]{});
                }
                finally {
                    Throwable throwable = v_27;
                    b.a(stream);
                    throw throwable;
                }
            }
        }

    }
    // class: com/tencent/tinker/lib/g/b$a
    class b$a {
        String a;
        String b;

         b$a(String str0, String str1) {
            super();
            this.a = str0;
            this.b = str1;
        }

        static b$a a(File file) {
            Object object = null;
            Object objectVar1 = null;
            Properties properties = new Properties();
            Object objectVar2 = null;
            try {
                FileInputStream stream = new FileInputStream(file);
                properties.load(stream);
                String str0 = properties.getProperty("md5");
                String str1 = properties.getProperty("times");
            }
            catch (IOException var_5_0) {
                n.d("Tinker.UpgradePatchRetry", new StringBuilder().append("fail to readRetryProperty:").append(var_5_0).toString(), new Object[]{});
            }
            finally {
                Throwable throwable = v_16;
                b.a(stream);
                throw throwable;
            }
            return new b$a(str0, str1);
        }

        static void a(File file, b$a b$a) {
            if (b$a == null) {
                return;
            }
            else {
                File fileVar1 = file.getParentFile();
                if (fileVar1.exists()) {
                    fileVar1.mkdirs();
                }
                Properties properties = new Properties();
                properties.put("md5", b$a.a);
                properties.put("times", b$a.b);
                Object object = null;
                try {
                    FileOutputStream stream = new FileOutputStream(file, 0);
                    properties.store(stream, null);
                }
                catch (Exception var_5_0) {
                    n.a("Tinker.UpgradePatchRetry", var_5_0, "retry write property fail", new Object[]{});
                }
                finally {
                    Throwable throwable = v_27;
                    b.a(stream);
                    throw throwable;
                }
            }
        }

    }
}
