/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/c;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import com.tencent.rfix.loader.h.c;

// class: com/tencent/rfix/loader/c/d
public class d implements b {
    final private File a;
    final private Properties b;

    public d(Context context, String str0) {
        super();
        File file = e.a(context);
        this.a = new File(file, new StringBuilder().append(str0).append(".prop").toString());
        this.b = new Properties();
    }

    public void a(boolean bool0) {
        if (this.a.exists()) {
            return;
        }
        else {
            Object object = null;
            Object objectVar1 = null;
            try {
                try {
                    if (bool0) {
                        File file = new File(new StringBuilder().append(this.a.getAbsolutePath()).append(".lock").toString());
                        object = c.a(file);
                    }
                    FileInputStream stream = new FileInputStream(this.a);
                    this.b.load(stream);
                    e.a(object);
                    e.a(stream);
                }
                catch (FileNotFoundException var_4_1) {
                }
            }
            catch (Exception var_4_2) {
                RFixLog.e("RFix.PropertiesStorage", "load fail!", var_4_2);
            }
            finally {
                Throwable throwable = v_7;
                e.a(object);
                e.a(objectVar1);
                throw throwable;
            }
        }
    }

    public void b(boolean bool0) {
        Object object = null;
        Object objectVar1 = null;
        try {
            if (bool0) {
                File file = new File(new StringBuilder().append(this.a.getAbsolutePath()).append(".lock").toString());
                object = c.a(file);
            }
            File fileVar1 = this.a.getParentFile();
            if (fileVar1.exists()) {
                fileVar1.mkdirs();
            }
            FileOutputStream stream = new FileOutputStream(this.a);
            this.b.store(stream, null);
            return;
        }
        catch (Exception var_4_2) {
            RFixLog.e("RFix.PropertiesStorage", "save fail!", var_4_2);
            return;
        }
        finally {
            Throwable throwable = v_4;
            e.a(object);
            e.a(objectVar1);
            throw throwable;
        }
    }

    public boolean c(boolean bool0) {
        if (this.a.exists()) {
            return true;
        }
        else {
            int i0 = 0;
            Object object = null;
            try {
                try {
                    if (bool0) {
                        File file = new File(new StringBuilder().append(this.a.getAbsolutePath()).append(".lock").toString());
                        object = c.a(file);
                    }
                    boolean bool1 = this.a.delete();
                    e.a(object);
                }
                catch (FileNotFoundException var_4_1) {
                }
            }
            catch (Exception var_4_2) {
                RFixLog.e("RFix.PropertiesStorage", "delete fail!", var_4_2);
            }
            finally {
                Throwable throwable = v_8;
                e.a(object);
                throw throwable;
            }
            return bool1;
        }
    }

    public String a(String str0, String str1) {
        if (this.b.containsKey(str0)) {
            return this.b.getProperty(str0);
        }
        else {
            return str1;
        }
    }

    public int a(String str0, int i0) {
        if (this.b.containsKey(str0)) {
            return Integer.parseInt(this.b.getProperty(str0));
        }
        else {
            return i0;
        }
    }

    public long a(String str0, long l1) {
        if (this.b.containsKey(str0)) {
            return Long.parseLong(this.b.getProperty(str0));
        }
        else {
            return l1;
        }
    }

    public boolean a(String str0, boolean bool0) {
        if (this.b.containsKey(str0)) {
            return Boolean.parseBoolean(this.b.getProperty(str0));
        }
        else {
            return bool0;
        }
    }

    public void b(String str0, String str1) {
        this.b.setProperty(str0, str1 == null ? str1 : "");
    }

    public void b(String str0, int i0) {
        this.b.setProperty(str0, String.valueOf(i0));
    }

    public void b(String str0, long l1) {
        this.b.setProperty(str0, String.valueOf(l1));
    }

    public void b(String str0, boolean bool0) {
        this.b.setProperty(str0, String.valueOf(bool0));
    }

    public void a(String str0) {
        this.b.remove(str0);
    }

}
