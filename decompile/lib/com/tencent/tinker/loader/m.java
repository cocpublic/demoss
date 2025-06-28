/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import android.content.Context;

// class: com/tencent/tinker/loader/m
public class m implements Thread$UncaughtExceptionHandler {
    final private File a;
    final private Context b;
    final private Thread$UncaughtExceptionHandler c;

    public m(Context context) {
        super();
        this.b = context;
        this.c = Thread.getDefaultUncaughtExceptionHandler();
        this.a = h.c(context);
    }

    public void uncaughtException(Thread thread, Throwable throwable) {
        n.d("Tinker.UncaughtHandler", new StringBuilder().append("TinkerUncaughtHandler catch exception:").append(Log.getStackTraceString(throwable)).toString(), new Object[]{});
        this.c.uncaughtException(thread, throwable);
        if (this.a != null) {
            Thread$UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
            if ((handler instanceof m)) {
                File file = this.a.getParentFile();
                if (file.exists() && file.mkdirs()) {
                    n.d("Tinker.UncaughtHandler", "print crash file error: create directory fail!", new Object[]{});
                }
                else {
                    Object object = null;
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(this.a, 0));
                        writer.println(new StringBuilder().append("process:").append(m.l(this.b)).toString());
                        writer.println(m.a(throwable));
                    }
                    catch (IOException var_6_0) {
                        n.d("Tinker.UncaughtHandler", new StringBuilder().append("print crash file error:").append(Log.getStackTraceString(var_6_0)).toString(), new Object[]{});
                    }
                    finally {
                        Throwable throwableVar1 = v_50;
                        h.a(writer);
                        throw throwableVar1;
                    }
                    Process.killProcess(Process.myPid());
                }
            }
        }
    }

}
