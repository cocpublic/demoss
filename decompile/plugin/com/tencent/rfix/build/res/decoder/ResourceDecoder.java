/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/res/decoder;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.nio.file.Path;
import java.nio.file.FileVisitResult;
import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import com.tencent.rfix.build.res.InputConfig;
import com.tencent.rfix.build.res.util.InfoWriter;

// class: com/tencent/rfix/build/res/decoder/ResourceDecoder
class ResourceDecoder {
    final public static String TEST_RES_FILE_NAME;
    final public static String ASSETS_DIR_NAME;
    final public static String DUMMY_TEST_CONTENT;
    final private Set<String> mAddSet;
    final private Set<String> mDelSet;
    final private Set<String> mModifySet;
    final private Map<String, ResourceDecoder$LargeFileInfo> mLargeModifyMap;
    final private Set<String> mLargeModifySet;

     ResourceDecoder(InputConfig config, String metaPath) {
        super(config, metaPath);
        this.mAddSet = new HashSet();
        this.mDelSet = new HashSet();
        this.mModifySet = new HashSet();
        this.mLargeModifySet = new HashSet();
        this.mLargeModifyMap = new HashMap();
    }

    public boolean patch(File oldFile, File newFile) {
        String name = this.getRelativePathStringToNewFile(newFile);
        File outputFile = this.getOutputPath(newFile).toFile();
        if (oldFile == null || oldFile.exists()) {
            FileOperation.copy(newFile, outputFile);
            this.mAddSet.add(name);
            return true;
        }
        else {
            if (0L == oldFile.length() && 0L == newFile.length()) {
                return false;
            }
            else {
                String newMd5 = MD5Util.getMD5(newFile);
                String oldMd5 = MD5Util.getMD5(oldFile);
                if (oldMd5 != null && oldMd5.equals(newMd5)) {
                    return false;
                }
                else if (name.equals("AndroidManifest.xml")) {
                    System.err.println("the modification of 'AndroidManifest.xml' is not support!");
                    return false;
                }
                else {
                    if (this.isLargeFile(newFile)) {
                        if (outputFile.getParentFile().exists()) {
                            outputFile.getParentFile().mkdirs();
                        }
                        BSDiff.bsdiff(oldFile, newFile, outputFile);
                        if (FileOperation.checkBsDiffFileSize(outputFile, newFile)) {
                            ResourceDecoder$LargeFileInfo largeFileInfo = new ResourceDecoder$LargeFileInfo(this);
                            largeFileInfo.crc = FileOperation.getFileCrc32(newFile);
                            largeFileInfo.md5 = newMd5;
                            this.mLargeModifySet.add(name);
                            this.mLargeModifyMap.put(name, largeFileInfo);
                            return true;
                        }
                    }
                    this.mModifySet.add(name);
                    FileOperation.copy(newFile, outputFile);
                    return true;
                }
            }
        }
    }

    private boolean isLargeFile(File file) {
        if (this.mConfig.largeFileSize * 1024L > file.length()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void onAllPatchesStart() {
    }

    public void onAllPatchesEnd() {
        if (this.mAddSet.isEmpty() && this.mModifySet.isEmpty() && this.mLargeModifySet.isEmpty()) {
        }
        else {
            this.addTestRes(this.mConfig.newApkTmpDir.getAbsolutePath());
            File resOutZip = new File(new StringBuilder().append(this.mConfig.outputDir).append(File.separator).append("resources.zip").toString());
            FileOperation.zipInputDir(this.mConfig.tmpResResultDir, resOutZip, null);
            String oldArscCrc = FileOperation.getZipEntryCrc(this.mConfig.oldApk, "resources.arsc");
            String resOutMD5 = MD5Util.getMD5(resOutZip);
            String newArscMd5 = FileOperation.getZipEntryMd5(this.mConfig.newApk, "resources.arsc");
            this.writeMetaFile(new StringBuilder().append("resources.zip,").append(oldArscCrc).append(",").append(newArscMd5).append(",").append(resOutMD5).toString());
            this.mDelSet.addAll(this.getDeletedResource(this.mConfig.oldApkTmpDir, this.mConfig.newApkTmpDir));
            this.mDelSet.remove("AndroidManifest.xml");
            this.removeIgnoreChangeFile(this.mDelSet);
            this.writeMetaFile(this.mLargeModifySet, 3);
            this.writeMetaFile(this.mModifySet, 2);
            this.writeMetaFile(this.mAddSet, 1);
            this.writeMetaFile(this.mDelSet, 4);
            this.writePattern();
            this.mMetaInfoWriter.close();
        }
    }

    private void addTestRes(String newApkDir) {
        File assets = new File(newApkDir, "assets");
        assets.mkdirs();
        File testFile = new File(assets, "only_use_to_test_rfix_resource.txt");
        try {
            testFile.createNewFile();
            FileOutputStream os = new FileOutputStream(testFile);
            os.write("hello world".getBytes());
            os.close();
            this.patch(null, testFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeIgnoreChangeFile(Set<String> set) {
        ArrayList removeList = new ArrayList();
        HashSet whitelist = new HashSet(Arrays.asList(this.mConfig.whitelist));
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String name = (String)iterator.next();
            if (FileUtil.checkFileInPattern(whitelist, name)) {
                removeList.add(name);
            }
        }
        set.removeAll(removeList);
    }

    private void writePattern() {
        String patternMeta = "pattern:";
        HashSet patterns = new HashSet(Arrays.asList(this.mConfig.pattern));
        patterns.remove("AndroidManifest.xml");
        this.writeMetaFile(new StringBuilder().append(patternMeta).append(patterns.size()).toString());
        Iterator iterator = patterns.iterator();
        while (iterator.hasNext()) {
            String item = (String)iterator.next();
            this.writeMetaFile(item);
        }
    }

    private void writeMetaFile(String line) {
        this.mMetaInfoWriter.writeLineToInfoFile(line);
    }

    private void writeMetaFile(Set<String> set, int mode) {
        if (set.isEmpty()) {
        }
        else {
            String title = this.getTitle(set, mode);
            this.mMetaInfoWriter.writeLineToInfoFile(title);
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                String name = (String)iterator.next();
                String line = this.getLine(mode, name);
                this.mMetaInfoWriter.writeLineToInfoFile(line);
            }
        }
    }

    private String getLine(int mode, String name) {
        if (mode == 3) {
            ResourceDecoder$LargeFileInfo info = (ResourceDecoder$LargeFileInfo)this.mLargeModifyMap.get(name);
            line = new StringBuilder().append(name).append(",").append(info.md5).append(",").append(info.crc).toString();
        }
        return name;
    }

    private String getTitle(Set<String> set, int mode) {
String title = "";
        switch(mode) {
            case 1: {
                title = new StringBuilder().append("add:").append(set.size()).toString();
                break;;
            }
            case 2: {
                title = new StringBuilder().append("modify:").append(set.size()).toString();
                break;;
            }
            case 3: {
                title = new StringBuilder().append("large modify:").append(set.size()).toString();
                break;;
            }
            case 4: {
                title = new StringBuilder().append("del:").append(set.size()).toString();
                break;;
            }
        }
        return title;
    }

    public ArrayList<String> getDeletedResource(File oldApkDir, File newApkDir) {
        ResourceDecoder$DeletedResVisitor deletedResVisitor = new ResourceDecoder$DeletedResVisitor(this, this.mConfig, newApkDir.toPath(), oldApkDir.toPath());
        Files.walkFileTree(oldApkDir.toPath(), deletedResVisitor);
        return deletedResVisitor.deletedFiles;
    }

    // class: com/tencent/rfix/build/res/decoder/ResourceDecoder$DeletedResVisitor
    class ResourceDecoder$DeletedResVisitor {
        InputConfig config;
        Path newApkPath;
        Path oldApkPath;
        ArrayList<String> deletedFiles;
        final synthetic ResourceDecoder this$0;

         ResourceDecoder$DeletedResVisitor(ResourceDecoder this$0, InputConfig config, Path newPath, Path oldPath) {
            this.this$0 = this$0;
            super();
            this.config = config;
            this.newApkPath = newPath;
            this.oldApkPath = oldPath;
            this.deletedFiles = new ArrayList();
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Path relativePath = this.oldApkPath.relativize(file);
            Path newPath = this.newApkPath.resolve(relativePath);
            String patternKey = relativePath.toString().replace("\", "/");
            if (FileUtil.checkFileInPattern(new HashSet(Arrays.asList(this.config.pattern)), patternKey)) {
                if (newPath.toFile().exists()) {
                    this.deletedFiles.add(patternKey);
                }
                return FileVisitResult.CONTINUE;
            }
            else {
                return FileVisitResult.CONTINUE;
            }
        }

        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes attributes) {
            return this.visitFile((Path)object, attributes);
        }

    }
    // class: com/tencent/rfix/build/res/decoder/ResourceDecoder$DeletedResVisitor
    class ResourceDecoder$DeletedResVisitor {
        InputConfig config;
        Path newApkPath;
        Path oldApkPath;
        ArrayList<String> deletedFiles;
        final synthetic ResourceDecoder this$0;

         ResourceDecoder$DeletedResVisitor(ResourceDecoder this$0, InputConfig config, Path newPath, Path oldPath) {
            this.this$0 = this$0;
            super();
            this.config = config;
            this.newApkPath = newPath;
            this.oldApkPath = oldPath;
            this.deletedFiles = new ArrayList();
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Path relativePath = this.oldApkPath.relativize(file);
            Path newPath = this.newApkPath.resolve(relativePath);
            String patternKey = relativePath.toString().replace("\", "/");
            if (FileUtil.checkFileInPattern(new HashSet(Arrays.asList(this.config.pattern)), patternKey)) {
                if (newPath.toFile().exists()) {
                    this.deletedFiles.add(patternKey);
                }
                return FileVisitResult.CONTINUE;
            }
            else {
                return FileVisitResult.CONTINUE;
            }
        }

        public /* synthetic */ FileVisitResult visitFile(Object object, BasicFileAttributes attributes) {
            return this.visitFile((Path)object, attributes);
        }

    }
    // class: com/tencent/rfix/build/res/decoder/ResourceDecoder$LargeFileInfo
    public class ResourceDecoder$LargeFileInfo {
        long crc;
        String md5;
        final synthetic ResourceDecoder this$0;

        public ResourceDecoder$LargeFileInfo(ResourceDecoder this$0) {
            this.this$0 = this$0;
            super();
            this.md5 = null;
        }

    }
    // class: com/tencent/rfix/build/res/decoder/ResourceDecoder$LargeFileInfo
    public class ResourceDecoder$LargeFileInfo {
        long crc;
        String md5;
        final synthetic ResourceDecoder this$0;

        public ResourceDecoder$LargeFileInfo(ResourceDecoder this$0) {
            this.this$0 = this$0;
            super();
            this.md5 = null;
        }

    }
}
