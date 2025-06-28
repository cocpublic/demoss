/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/util;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

// class: com/tencent/tinker/build/util/CustomDiff
public class CustomDiff {

    public CustomDiff() {
        super();
    }

    public static boolean checkHasCustomDiff(Configuration config) {
        if (config.mCustomDiffPath != null && config.mCustomDiffPath.trim().isEmpty() && config.mCustomDiffPathArgs != null && config.mCustomDiffPathArgs.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void diffFile(String mCustomDiffPath, String mCustomDiffPathArgs, File oldFile, File newFile, File diffFile) {
        String s;
        String outPath = diffFile.getAbsolutePath();
        ArrayList cmds = new ArrayList();
        String[] stringArr0Var1 = mCustomDiffPath.split(" ");
        for (int i3 = 0; i3 < stringArr0Var1.length; i3 += 1) {
            s = stringArr0Var1[i3];
            if (s.isEmpty()) {
                cmds.add(s);
            }
        }
        stringArr0Var1 = mCustomDiffPathArgs.split(" ");
        i2 = stringArr0Var1.length;
        for (i3 = 0; i3 < stringArr0Var1.length; i3 += 1) {
            s = stringArr0Var1[i3];
            if (s.isEmpty()) {
                cmds.add(s);
            }
        }
        cmds.add(oldFile.getAbsolutePath());
        cmds.add(newFile.getAbsolutePath());
        cmds.add(outPath);
        System.out.println(mCustomDiffPath);
        Iterator iterator = cmds.iterator();
        while (iterator.hasNext()) {
            s = (String)iterator.next();
            System.out.print(new StringBuilder().append(s).append(" ").toString());
        }
        System.out.println();
        ProcessBuilder builder = new ProcessBuilder(cmds);
        builder.redirectErrorStream(true);
        builder.inheritIO();
        Object pro = null;
        Object reader = null;
        try {
            try {
                boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
                if (isWindows) {
                    cmds.add(0, "cmd.exe");
                    builder = new ProcessBuilder(cmds);
                }
                else {
                    builder = new ProcessBuilder(cmds);
                }
                Process process = builder.start();
                BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while (true) {
                    String line = br.readLine();
                    if (br.readLine() != null) {
                        Logger.d(line);
                    }
                    else {
                        int exitCode = process.waitFor();
                        Logger.d("run init script done, exitCode: %d", new Object[]{Integer.valueOf(exitCode)});
                        process.destroy();
                    }
                }
                try {
                    pro.waitFor();
                }
                catch (Throwable var_11_0) {
                }
                try {
                    pro.destroy();
                }
                catch (Throwable var_11_1) {
                }
                IOHelper.closeQuietly(reader);
            }
            catch (IOException e) {
                FileOperation.deleteFile(diffFile);
                Logger.e(new StringBuilder().append("CustomDecoder error").append(e.getMessage()).toString());
                try {
                }
                catch (Throwable var_11_2) {
                }
                try {
                }
                catch (Throwable var_11_3) {
                }
            }
        }
        catch (InterruptedException e) {
            Logger.e(new StringBuilder().append("CustomDecoder error").append(e.getMessage()).toString());
            try {
            }
            catch (Throwable var_11_4) {
            }
            try {
            }
            catch (Throwable var_11_5) {
            }
            return;
        }
        finally {
            Throwable throwable = v_80;
            try {
                pro.waitFor();
            }
            catch (Throwable var_18_0) {
            }
            try {
                pro.destroy();
            }
            catch (Throwable var_18_1) {
            }
            IOHelper.closeQuietly(reader);
            throw throwable;
        }
    }

}
