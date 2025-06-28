/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/h;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

// class: com/tencent/rfix/loader/h/c
public class c implements Closeable {
    final private FileOutputStream a;
    final private FileLock b;

    public static c a(File file) {
        return new c(file);
    }

    private c(File file) {
        super();
        File fileVar1 = file.getParentFile();
        if (fileVar1.exists()) {
            fileVar1.mkdirs();
        }
        this.a = new FileOutputStream(file);
        int i0 = 0;
        Object object = null;
        Object objectVar1 = null;
        while (i0 < 2) {
            i0 += 1;
            try {
                FileLock lock = this.a.getChannel().lock();
                int i1 = lock != null ? 0 : 1;
                if (i1 != 0) {
                    break;;
                }
                else {
                }
            }
            catch (Exception var_7_0) {
                Exception exception = var_7_0;
                RFixLog.e("RFix.FileLockHelper", "get lock fail, wait time: 10");
            }
            try {
                Thread.sleep(10L);
                continue;;
            }
            catch (Exception var_7_1) {
                RFixLog.e("RFix.FileLockHelper", "get lock fail, thread sleep exception.", var_7_1);
                continue;;
            }
        }
        if (object == null) {
            throw new IOException(new StringBuilder().append("lock file failed: ").append(file.getAbsolutePath()).toString(), objectVar1);
        }
        else {
            this.b = object;
        }
    }

    public void close() {
        try {
            if (this.b != null) {
                this.b.release();
            }
            return;
        }
        finally {
            Throwable throwable = v_2;
            if (this.a != null) {
                this.a.close();
            }
            throw throwable;
        }
    }

}
