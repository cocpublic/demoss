/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/f;

import com.tencent.raft.measure.config.RAFTComConfig;

// class: com/tencent/rfix/lib/f/b
public class b {

    public static boolean a(Context context, RFixLoadResult result, boolean bool0, long l1) {
        if (context == null || result == null) {
            return false;
        }
        else if (RFixATTASwitch.a()) {
            return false;
        }
        else {
            try {
                RAFTComConfig config = new RAFTComConfig("RFix-Android", "2.0.5");
                RAFTMeasure.enableCrashMonitor(context, config);
                RAFTMeasure.reportAvg(context, config, "init_cost", l1);
                RAFTMeasure.reportSuccess(context, config, "init_status", bool0);
                RAFTMeasure.reportAvg(context, config, "patch_cost", result.c);
                RAFTMeasure.reportSuccess(context, config, "patch_status", result.a());
            }
            catch (Error var_5_1) {
            }
            return true;
        }
    }

    public static void a(Context context, boolean bool0, long l1, String l1) {
        if (RFixATTASwitch.a()) {
        }
        else {
            try {
                RAFTComConfig config = new RAFTComConfig("RFix-Android", "2.0.5");
                RAFTMeasure.reportSuccess(context, config, "install_status", bool0);
                RAFTMeasure.reportAvg(context, config, "install_cost", l1);
                RAFTMeasure.reportDistribution(context, config, "install_result", str0);
            }
            catch (Error var_5_1) {
            }
        }
    }

}
