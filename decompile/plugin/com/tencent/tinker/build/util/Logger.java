/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/util;

import com.tencent.tinker.build.info.InfoWriter;
import java.io.PrintStream;

// class: com/tencent/tinker/build/util/Logger
public class Logger {
    private static InfoWriter logWriter;

    public Logger() {
        super();
    }

    public static void initLogger(Configuration config) {
        String logPath = new StringBuilder().append(config.mOutFolder).append(File.separator).append("log.txt").toString();
        Logger.logWriter = new InfoWriter(config, logPath);
    }

    public static void closeLogger() {
        if (Logger.logWriter != null) {
            Logger.logWriter.close();
        }
    }

    public static void d(String msg) {
        Logger.d("%s", new Object[]{msg});
    }

    public static void d(String format, Object[] obj) {
        String log = String.format(obj.length == 0 ? format : format, obj);
        if (log == null) {
            log = "";
        }
        System.out.println(log);
        System.out.flush();
        Logger.logWriter.writeLineToInfoFile(log);
    }

    public static void e(String msg) {
        Logger.e("%s", new Object[]{msg});
    }

    public static void e(String format, Object[] obj) {
        String log = String.format(obj.length == 0 ? format : format, obj);
        if (log == null) {
            log = "";
        }
        System.err.println(log);
        System.err.flush();
        Logger.logWriter.writeLineToInfoFile(log);
    }

}
