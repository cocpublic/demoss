/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

// class: com/tencent/tinker/loader/shareutil/f
public class f implements Closeable {
    final private FileOutputStream a;
    final private FileLock b;

    private f(File file) {
        super();
        this.a = new FileOutputStream(file);
        int i0 = 0;
        Object object = null;
        Object objectVar1 = null;
        while (i0 < 3) {
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
            catch (Exception var_6_0) {
                Exception exception = var_6_0;
                n.d("Tinker.FileLockHelper", "getInfoLock Thread failed time:10", new Object[]{});
            }
            try {
                Thread.sleep(10L);
                continue;;
            }
            catch (Exception var_6_1) {
                n.d("Tinker.FileLockHelper", "getInfoLock Thread sleep exception", new Object[]{var_6_1});
                continue;;
            }
        }
        if (object == null) {
            throw new IOException(new StringBuilder().append("Tinker Exception:FileLockHelper lock file failed: ").append(file.getAbsolutePath()).toString(), objectVar1);
        }
        else {
            this.b = object;
        }
    }

    public static f a(File file) {
        return new f(file);
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
