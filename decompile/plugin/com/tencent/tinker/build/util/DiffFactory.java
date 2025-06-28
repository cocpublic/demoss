/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

// class: com/tencent/tinker/build/util/DiffFactory
public class DiffFactory {
    private static boolean diffShellPermission;

    public DiffFactory() {
        super();
    }

    public static void diffFile(Configuration config, File oldFile, File newFile, File diffFile) {
        Logger.d(new StringBuilder().append("path:").append(config.mCustomDiffPath).append(" oldFile:").append(oldFile.getPath()).toString());
        if (CustomDiff.checkHasCustomDiff(config)) {
            if (DiffFactory.diffShellPermission) {
                DiffFactory.diffShellPermission = true;
                DiffFactory.makeSurePermission(config.mCustomDiffPath);
            }
            CustomDiff.diffFile(config.mCustomDiffPath, config.mCustomDiffPathArgs, oldFile, newFile, diffFile);
        }
        else {
            BSDiff.bsdiff(oldFile, newFile, diffFile);
        }
    }

    private static void makeSurePermission(String path) {
        try {
            Process process = new ProcessBuilder(new String[]{"chmod", "777", path.split(" ")[0]}).start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (true) {
                String line = br.readLine();
                if (br.readLine() != null) {
                    Logger.d(line);
                }
                else {
                    int exitCode = process.waitFor();
                    Logger.d(new StringBuilder().append("run makeSurePermission done, exitCode: ").append(exitCode).toString());
                    process.destroy();
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static  {
        DiffFactory.diffShellPermission = false;
    }

}
