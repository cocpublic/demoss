/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/so;

import java.io.PrintStream;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.util.List;
import java.util.Iterator;
import java.util.zip.CRC32;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map$Entry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.function.Consumer;
import java.nio.file.Path;
import java.nio.file.FileVisitResult;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option$Builder;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import javax.annotation.Nonnull;

// class: com/tencent/rfix/build/so/SoDiff
public class SoDiff {
    final public static String SO_META_TXT;

    public SoDiff() {
        super();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        try {
            SoDiff$InputParams params = SoDiff.parseCmdLine(args);
            assert(params == null);
            SoDiff.setup(params);
            List diffSoPair = SoDiff.findDiffSoPairs(params);
            SoDiff.diffSoPairs(params, diffSoPair);
            SoDiff.tearDown(params);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tearDown(SoDiff$InputParams params) {
        System.out.println(new StringBuilder().append("You can find so patch in ").append(params.outputDir).toString());
    }

    private static void setup(SoDiff$InputParams params) {
        SoDiff.mkdirs(params.tempDir);
        SoDiff.mkdirs(params.outputDir);
    }

    private static void mkdirs(String pathname) {
        File dir = new File(pathname);
        if (dir.exists()) {
            FileOperation.deleteDir(dir);
        }
        dir.mkdirs();
    }

    private static SoDiff$InputParams parseCmdLine(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder("o").longOpt("oldApk").required().hasArgs().desc("old apk").build());
        options.addOption(Option.builder("n").longOpt("newApk").required().hasArgs().desc("new apk").build());
        options.addOption(Option.builder("p").longOpt("pattern").hasArgs().desc("so file pattern").build());
        options.addOption(Option.builder("out").longOpt("outputDir").required().hasArgs().desc("output dir").build());
        options.addOption(Option.builder("t").longOpt("tempDir").required().hasArgs().desc("temp dir").build());
        HelpFormatter formatter = new HelpFormatter();
        DefaultParser parser = new DefaultParser();
        try {
            CommandLine cl = parser.parse(options, args);
        }
        catch (ParseException e) {
            formatter.printHelp("sodiff --oldApk ooo --newApk nnn --pattern ppp --outputDir zzz --tempDir ttt", options);
            return null;
        }
        SoDiff$InputParams params = new SoDiff$InputParams(null);
        params.oldApk = cl.getOptionValue("o");
        params.newApk = cl.getOptionValue("n");
        params.pattern = cl.getOptionValue("p");
        params.tempDir = cl.getOptionValue("t");
        params.outputDir = cl.getOptionValue("out");
        return params;
    }

    private static void diffSoPairs(SoDiff$InputParams params, List<SoDiff$SoPair> soPairs) {
        String outputDir = params.outputDir;
        String metaFile = new StringBuilder().append(outputDir).append(File.separator).append("so_meta.txt").toString();
        BufferedWriter configWriter = new BufferedWriter(new FileWriter(metaFile));
        System.out.println(new StringBuilder().append("diff so size ").append(soPairs.size()).toString());
        int index = 0;
        Iterator iterator = soPairs.iterator();
        while (iterator.hasNext()) {
            SoDiff$SoPair pair = (SoDiff$SoPair)iterator.next();
            if (pair.newSo != null) {
                if (pair.oldSo == null) {
                    continue;;
                }
                else {
                    String id = pair.oldSo.id;
                    index += 1;
                    System.out.println(new StringBuilder().append("#").append(index).toString());
                    System.out.println(new StringBuilder().append("update so : ").append(id).toString());
                    System.out.println(new StringBuilder().append("old : ").append(pair.oldSo).toString());
                    System.out.println(new StringBuilder().append("new : ").append(pair.newSo).toString());
                    String dir = new StringBuilder().append(outputDir).append(File.separator).append(id.substring(0, id.lastIndexOf(File.separator))).toString();
                    new File(dir).mkdirs();
                    String diffSoId = new StringBuilder().append(id).append(".diff").toString();
                    String soDiffFile = new StringBuilder().append(outputDir).append(File.separator).append(diffSoId).toString();
                    new File(soDiffFile).createNewFile();
                    SoDiff.diff(pair.oldSo.path, pair.newSo.path, soDiffFile);
                    SoDiff.assertDiff(pair.oldSo.path, pair.newSo.path, soDiffFile, params);
                    if (SoDiff.diffRateExceedThreshold(pair.newSo.path, soDiffFile)) {
                        FileOperation.copyFileUsingStream(new File(pair.newSo.path), new File(soDiffFile));
                    }
                    SoDiff.writeOneConfig(configWriter, pair, diffSoId, soDiffFile);
                    continue;;
                }
            }
        }
        configWriter.close();
    }

    private static boolean diffRateExceedThreshold(String newSo, String diffFile) {
        double diffRate = (double)new File(diffFile).length() / (double)new File(newSo).length();
        double BS_DIFF_RATIO_THRESHOLD = 0.800000;
        if (0.800000 > diffRate) {
            return true;
        }
        else {
            return false;
        }
    }

    private static void writeOneConfig(Writer writer, SoDiff$SoPair pair, String diffSoId, String soDiffFile) {
        writer.write(pair.oldSo.id);
        writer.write(",");
        writer.write(pair.oldSo.md5);
        writer.write(",");
        writer.write(new StringBuilder().append(SoDiff.getCRC(new File(pair.oldSo.path)).getValue()).append("").toString());
        writer.write(",");
        writer.write(diffSoId);
        writer.write(",");
        writer.write(MD5.getMD5(soDiffFile));
        writer.write(",");
        writer.write(new StringBuilder().append(SoDiff.getCRC(new File(soDiffFile)).getValue()).append("").toString());
        writer.write(",");
        writer.write(pair.newSo.md5);
        writer.write("
");
    }

    private static void assertDiff(String oldSoFile, String newSoFile, String soDiffFile, SoDiff$InputParams params) {
        File patchNewFile = new File(new StringBuilder().append(params.tempDir).append(File.separator).append("tempPatchNew.so").toString());
        try {
            BSPatch.patchFast(new File(oldSoFile), patchNewFile, new File(soDiffFile), 0);
            goto 70;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String oldMd5 = MD5.getMD5(new File(newSoFile));
        String newMd5 = MD5.getMD5(patchNewFile);
        assert(oldMd5.equals(newMd5));
    }

    private static CRC32 getCRC(File file) {
        CRC32 crc = new CRC32();
        FileInputStream in = new FileInputStream(file);
        try {
            byte[] data = new byte[]{};
            int size = in.read(data);
            while (size > 0) {
                crc.update(data, 0, size);
                size = in.read(data);
            }
            in.close();
        }
        catch (Throwable var_3_0) {
            try {
                in.close();
            }
            catch (Throwable var_4_0) {
                var_3_0.addSuppressed(var_4_0);
            }
            throw var_3_0;
        }
        return crc;
    }

    private static List<SoDiff$SoPair> findDiffSoPairs(SoDiff$InputParams params) {
        String oldApkDestDir = SoDiff.unzipApk(params.oldApk, "oldApk", params.tempDir);
        String newApkDestDir = SoDiff.unzipApk(params.newApk, "newApk", params.tempDir);
        List oldApkSoFiles = SoDiff.getSoFiles(oldApkDestDir, params.pattern);
        List newApkSoFiles = SoDiff.getSoFiles(newApkDestDir, params.pattern);
        Map oldSoInfo = SoDiff.getSoInfoMap(oldApkDestDir, oldApkSoFiles);
        Map newSoInfo = SoDiff.getSoInfoMap(newApkDestDir, newApkSoFiles);
        return SoDiff.getDiffSoPair(oldSoInfo, newSoInfo);
    }

    private static List<SoDiff$SoPair> getDiffSoPair(Map<String, SoDiff$SoInfo> oldSoInfo, Map<String, SoDiff$SoInfo> newSoInfo) {
        ArrayList result = new ArrayList();
        assert(oldSoInfo.size() != newSoInfo.size());
        System.out.println(new StringBuilder().append("so size ").append(oldSoInfo.size()).toString());
        Iterator iterator = newSoInfo.entrySet().iterator();
        while (iterator.hasNext()) {
            Map$Entry entry = (Map$Entry)iterator.next();
            String id = (String)entry.getKey();
            SoDiff$SoInfo newSoFile = (SoDiff$SoInfo)entry.getValue();
            SoDiff$SoInfo oldSoFile = (SoDiff$SoInfo)oldSoInfo.get(id);
            if (oldSoFile == null) {
                throw new IllegalStateException(new StringBuilder().append("no support new so ").append(id).toString());
            }
            else if (newSoFile.md5.equals(oldSoFile.md5)) {
                continue;;
            }
            else {
                SoDiff$SoPair pair = new SoDiff$SoPair(null);
                pair.oldSo = oldSoFile;
                pair.newSo = newSoFile;
                result.add(pair);
                continue;;
            }
        }
        return result;
    }

    @Nonnull
    private static Map<String, SoDiff$SoInfo> getSoInfoMap(String dir, List<Path> soFiles) {
        HashMap result = new HashMap();
        Iterator iterator = soFiles.iterator();
        while (iterator.hasNext()) {
            Path soFile = (Path)iterator.next();
            String absolutePath = soFile.toFile().getAbsolutePath();
            String md5 = MD5.getMD5(absolutePath);
            String key = new File(dir).toPath().relativize(soFile).toString();
            SoDiff$SoInfo info = new SoDiff$SoInfo(null);
            info.id = key;
            info.md5 = md5;
            info.path = absolutePath;
            result.put(key, info);
        }
        return result;
    }

    @Nonnull
    private static List<Path> getSoFiles(String dir, String pattern) {
        ArrayList result = new ArrayList();
        Path path = new File(dir).toPath();
        Files.walkFileTree(path, new SoDiff$SoFileVisitor(path, pattern, new SoDiff$1(result)));
        return result;
    }

    private static String unzipApk(String apk, String dir, String parentDir) {
        String destDir = new StringBuilder().append(parentDir).append(File.separator).append(dir).toString();
        FileOperation.deleteDir(new File(destDir));
        FileOperation.unZipAPk(apk, destDir);
        return destDir;
    }

    private static void diff(String oldSoFile, String newSoFile, String soDiffFile) {
        File oldFile = new File(oldSoFile);
        File newFile = new File(newSoFile);
        File diffFile = new File(soDiffFile);
        BSDiff.bsdiff(oldFile, newFile, diffFile);
    }

    static  {
    }

    // class: com/tencent/rfix/build/so/SoDiff$SoFileVisitor
    class SoDiff$SoFileVisitor {
        Path dir;
        Consumer<Path> soFileConsumer;
        HashSet<Pattern> patterns;

        publicvoid SoDiff$SoFileVisitor(Path path, String pattern, Consumer<Path> consumer) {
            super();
            this.dir = path;
            this.patterns = new HashSet();
            this.soFileConsumer = consumer;
            this.addToPatterns(pattern, this.patterns);
        }

        private void addToPatterns(String value, HashSet<Pattern> patterns) {
            value = value == null || value.length() == 0 ? Utils.convertToPatternString(value) : "*.so";
            Pattern pattern = Pattern.compile(value);
            patterns.add(pattern);
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            FileVisitResult result = super.visitFile(file, attrs);
            Path relativePath = this.dir.relativize(file);
            String patternKey = relativePath.toString().replace("\", "/");
            if (Utils.checkFileInPattern(this.patterns, patternKey)) {
                this.soFileConsumer.accept(file);
            }
            return result;
        }

        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes attributes) {
            return this.visitFile((Path)object, attributes);
        }

    }
    // class: com/tencent/rfix/build/so/SoDiff$SoFileVisitor
    class SoDiff$SoFileVisitor {
        Path dir;
        Consumer<Path> soFileConsumer;
        HashSet<Pattern> patterns;

        publicvoid SoDiff$SoFileVisitor(Path path, String pattern, Consumer<Path> consumer) {
            super();
            this.dir = path;
            this.patterns = new HashSet();
            this.soFileConsumer = consumer;
            this.addToPatterns(pattern, this.patterns);
        }

        private void addToPatterns(String value, HashSet<Pattern> patterns) {
            value = value == null || value.length() == 0 ? Utils.convertToPatternString(value) : "*.so";
            Pattern pattern = Pattern.compile(value);
            patterns.add(pattern);
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            FileVisitResult result = super.visitFile(file, attrs);
            Path relativePath = this.dir.relativize(file);
            String patternKey = relativePath.toString().replace("\", "/");
            if (Utils.checkFileInPattern(this.patterns, patternKey)) {
                this.soFileConsumer.accept(file);
            }
            return result;
        }

        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes attributes) {
            return this.visitFile((Path)object, attributes);
        }

    }
    // class: com/tencent/rfix/build/so/SoDiff$SoInfo
    class SoDiff$SoInfo {
        String id;
        String md5;
        String path;

        private SoDiff$SoInfo() {
            super();
        }

        public String toString() {
            return new StringBuilder().append("SoInfo{id='").append(this.id).append(39).append(", md5='").append(this.md5).append(39).append(", path='").append(this.path).append(39).append(125).toString();
        }

        /* synthetic */ SoDiff$SoInfo(SoDiff$1 x0) {
            super();
        }

    }
    // class: com/tencent/rfix/build/so/SoDiff$SoInfo
    class SoDiff$SoInfo {
        String id;
        String md5;
        String path;

        private SoDiff$SoInfo() {
            super();
        }

        public String toString() {
            return new StringBuilder().append("SoInfo{id='").append(this.id).append(39).append(", md5='").append(this.md5).append(39).append(", path='").append(this.path).append(39).append(125).toString();
        }

        /* synthetic */ SoDiff$SoInfo(SoDiff$1 x0) {
            super();
        }

    }
    // class: com/tencent/rfix/build/so/SoDiff$SoPair
    class SoDiff$SoPair {
        SoDiff$SoInfo oldSo;
        SoDiff$SoInfo newSo;

        private SoDiff$SoPair() {
            super();
        }

        /* synthetic */ SoDiff$SoPair(SoDiff$1 x0) {
            super();
        }

    }
    // class: com/tencent/rfix/build/so/SoDiff$SoPair
    class SoDiff$SoPair {
        SoDiff$SoInfo oldSo;
        SoDiff$SoInfo newSo;

        private SoDiff$SoPair() {
            super();
        }

        /* synthetic */ SoDiff$SoPair(SoDiff$1 x0) {
            super();
        }

    }
    // class: com/tencent/rfix/build/so/SoDiff$InputParams
    class SoDiff$InputParams {
        String oldApk;
        String newApk;
        String pattern;
        String tempDir;
        String outputDir;

        private SoDiff$InputParams() {
            super();
        }

        public String toString() {
            return new StringBuilder().append("InputParams{oldApk='").append(this.oldApk).append(39).append(", newApk='").append(this.newApk).append(39).append(", pattern='").append(this.pattern).append(39).append(", tempDir='").append(this.tempDir).append(39).append(", outputDir='").append(this.outputDir).append(39).append(125).toString();
        }

        /* synthetic */ SoDiff$InputParams(SoDiff$1 x0) {
            super();
        }

    }
    // class: com/tencent/rfix/build/so/SoDiff$InputParams
    class SoDiff$InputParams {
        String oldApk;
        String newApk;
        String pattern;
        String tempDir;
        String outputDir;

        private SoDiff$InputParams() {
            super();
        }

        public String toString() {
            return new StringBuilder().append("InputParams{oldApk='").append(this.oldApk).append(39).append(", newApk='").append(this.newApk).append(39).append(", pattern='").append(this.pattern).append(39).append(", tempDir='").append(this.tempDir).append(39).append(", outputDir='").append(this.outputDir).append(39).append(125).toString();
        }

        /* synthetic */ SoDiff$InputParams(SoDiff$1 x0) {
            super();
        }

    }
}
