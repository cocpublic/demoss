/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/patch;

import com.tencent.tinker.build.decoder.ExtendApkDecoder;
import com.tencent.tinker.build.decoder.ApkDecoder;
import com.tencent.tinker.build.info.PatchInfo;
import com.tencent.tinker.build.builder.PatchBuilder;
import java.io.File;
import java.io.PrintStream;

// class: com/tencent/tinker/build/patch/Runner
public class Runner {
    final public static int ERRNO_ERRORS;
    final public static int ERRNO_USAGE;
    final private boolean mIsGradleEnv;
    protected static long mBeginTime;
    protected Configuration mConfig;

    public Runner(boolean isGradleEnv) {
        super();
        this.mIsGradleEnv = isGradleEnv;
    }

    public static void gradleRun(InputParam inputParam) {
        Runner.mBeginTime = System.currentTimeMillis();
        Runner m = new Runner(true);
        m.run(inputParam);
    }

    private void run(InputParam inputParam) {
        this.loadConfigFromGradle(inputParam);
        try {
            Logger.initLogger(this.mConfig);
            this.tinkerPatch();
            return;
        }
        catch (IOException e) {
            this.goToError(e, 1);
            return;
        }
        finally {
            Throwable throwable = v_6;
            Logger.closeLogger();
            throw throwable;
        }
    }

    protected void tinkerPatch() {
        Logger.d("-----------------------Tinker patch begin-----------------------");
        Logger.d(this.mConfig.toString());
        try {
            ApkDecoder decoder = ExtendConfiguration.sExtendEnable ? new ApkDecoder(this.mConfig) : new ExtendApkDecoder(this.mConfig);
            decoder.onAllPatchesStart();
            decoder.patch(this.mConfig.mOldApkFile, this.mConfig.mNewApkFile);
            decoder.onAllPatchesEnd();
            PatchInfo info = new PatchInfo(this.mConfig);
            info.gen();
            PatchBuilder builder = new PatchBuilder(this.mConfig);
            builder.buildPatch();
        }
        catch (Throwable e) {
            this.goToError(e, 2);
        }
        Logger.d("Tinker patch done, total time cost: %fs", new Object[]{Double.valueOf(this.diffTimeFromBegin())});
        Logger.d("Tinker patch done, you can go to file to find the output %s", new Object[]{this.mConfig.mOutFolder});
        Logger.d("-----------------------Tinker patch end-------------------------");
    }

    private void loadConfigFromGradle(InputParam inputParam) {
        try {
            this.mConfig = new Configuration(inputParam);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TinkerPatchException e) {
            e.printStackTrace();
        }
    }

    public void goToError(Throwable thr, int errCode) {
        if (this.mIsGradleEnv) {
            throw new RuntimeException(thr);
        }
        else {
            thr.printStackTrace(System.err);
            System.exit(errCode);
        }
    }

    public double diffTimeFromBegin() {
        long end = System.currentTimeMillis();
        return (double)end - Runner.mBeginTime / 1000.000000;
    }

}
