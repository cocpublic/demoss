/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/res;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option$Builder;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import java.io.File;
import java.io.PrintStream;

// class: com/tencent/rfix/build/res/ResDiff
public class ResDiff {

    public ResDiff() {
        super();
    }

    private static InputConfig parseCmdLine(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder("o").longOpt("oldApk").required().hasArgs().desc("old apk").build());
        options.addOption(Option.builder("n").longOpt("newApk").required().hasArgs().desc("new apk").build());
        options.addOption(Option.builder("p").longOpt("pattern").hasArgs().desc("res file pattern").build());
        options.addOption(Option.builder("out").longOpt("outputDir").required().hasArgs().desc("output dir").build());
        options.addOption(Option.builder("w").longOpt("whitelist").hasArgs().desc("res file whitelist").build());
        options.addOption(Option.builder("t").longOpt("tempDir").required().hasArgs().desc("temp dir").build());
        options.addOption(Option.builder("l").longOpt("largeFileSize").hasArgs().desc("largeFileSize").build());
        HelpFormatter formatter = new HelpFormatter();
        DefaultParser parser = new DefaultParser();
        try {
            CommandLine cl = parser.parse(options, args);
        }
        catch (ParseException e) {
            formatter.printHelp("ResDiff --oldApk ooo --newApk nnn --pattern ppp --outputDir zzz --tempDir ttt--largeFileSize lll--whitelist www", options);
            return null;
        }
        InputConfig params = new InputConfig();
        params.oldApk = new File(cl.getOptionValue("o"));
        params.newApk = new File(cl.getOptionValue("n"));
        String pattern = cl.getOptionValue("p");
        if (pattern != null && pattern.isEmpty()) {
            params.pattern = new String[]{pattern};
        }
        String whitelist = cl.getOptionValue("w");
        if (whitelist != null) {
            params.whitelist = new String[]{whitelist};
        }
        params.outputDir = new File(cl.getOptionValue("out"));
        File tmp = new File(cl.getOptionValue("t"));
        params.tmpResResultDir = new File(tmp, "tmpResult");
        params.oldApkTmpDir = new File(tmp, "old");
        params.newApkTmpDir = new File(tmp, "new");
        String largeFileSize = cl.getOptionValue("l");
        try {
            if (largeFileSize != null && largeFileSize.isEmpty()) {
                params.largeFileSize = Long.parseLong(largeFileSize);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        return params;
    }

    public static void main(String[] args) {
        System.out.println(new StringBuilder().append("res diff args: ").append(Arrays.toString(args)).toString());
        InputConfig config = ResDiff.parseCmdLine(args);
        try {
            new ApkDecoder(config, null).start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
