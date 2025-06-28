/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/decoder;

import com.tencent.tinker.build.info.InfoWriter;
import com.tencent.tinker.build.patch.Configuration;
import com.tencent.tinker.build.util.TinkerPatchException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.util.Set;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.nio.file.Path;
import java.nio.file.FileVisitResult;
import tinker.net.dongliu.apk.parser.ApkParser;
import tinker.net.dongliu.apk.parser.struct.resource.ResourceTable;
import tinker.net.dongliu.apk.parser.struct.resource.ResourcePackage;
import tinker.net.dongliu.apk.parser.struct.resource.Type;
import tinker.net.dongliu.apk.parser.struct.resource.ResourceEntry;
import tinker.net.dongliu.apk.parser.struct.ResourceValue;
import tinker.net.dongliu.apk.parser.bean.ApkMeta;

// class: com/tencent/tinker/build/decoder/ResDiffDecoder
public class ResDiffDecoder {
    final private static String TEST_RESOURCE_NAME;
    final private static String TEST_RESOURCE_ASSETS_PATH;
    final private static String TEMP_RES_ZIP;
    final private InfoWriter logWriter;
    final private InfoWriter metaWriter;
    private ArrayList<String> addedSet;
    private ArrayList<String> modifiedSet;
    private ArrayList<String> storedSet;
    private ArrayList<String> largeModifiedSet;
    private HashMap<String, ResDiffDecoder$LargeModeInfo> largeModifiedMap;
    private ArrayList<String> deletedSet;
    private ApkParser newApkParser;
    private Set<String> newApkAnimResNames;

    public ResDiffDecoder(Configuration config, String metaPath, String logPath) {
        super(config);
        this.metaWriter = metaPath != null ? null : new InfoWriter(config, new StringBuilder().append(config.mTempResultDir).append(File.separator).append(metaPath).toString());
        this.logWriter = logPath != null ? null : new InfoWriter(config, new StringBuilder().append(config.mOutFolder).append(File.separator).append(logPath).toString());
        this.addedSet = new ArrayList();
        this.modifiedSet = new ArrayList();
        this.largeModifiedSet = new ArrayList();
        this.largeModifiedMap = new HashMap();
        this.deletedSet = new ArrayList();
        this.storedSet = new ArrayList();
        this.newApkParser = new ApkParser(config.mNewApkFile);
        this.newApkAnimResNames = new HashSet();
    }

    public void clean() {
        this.metaWriter.close();
        this.logWriter.close();
        try {
            this.newApkParser.close();
        }
        catch (Throwable var_1_0) {
        }
    }

    private boolean checkLargeModFile(File file) {
        long length = file.length();
        if ((long)this.config.mLargeModSize * 1024 > length) {
            return true;
        }
        else {
            return false;
        }
    }

    public void onAllPatchesStart() {
        this.newApkParser.parseResourceTable();
        Map newApkResPkgNameMap = this.newApkParser.getResourceTable().getPackageNameMap();
        if (newApkResPkgNameMap == null) {
        }
        else {
            ResourcePackage newApkResPackage = (ResourcePackage)newApkResPkgNameMap.get(this.newApkParser.getApkMeta().getPackageName());
            if (newApkResPackage == null) {
            }
            else {
                Map newApkResTypesNameMap = newApkResPackage.getTypesNameMap();
                if (newApkResTypesNameMap == null) {
                }
                else {
                    List newApkAnimResTypes = (List)newApkResTypesNameMap.get("anim");
                    if (newApkAnimResTypes == null) {
                    }
                    else {
                        Iterator iterator = newApkAnimResTypes.iterator();
                        while (iterator.hasNext()) {
                            Type animType = (Type)iterator.next();
                            Iterator iteratorVar1 = animType.getResourceEntryNameHashMap().values().iterator();
                            while (iteratorVar1.hasNext()) {
                                ResourceEntry value = (ResourceEntry)iteratorVar1.next();
                                if (value == null) {
                                    continue;;
                                }
                                else {
                                    ResourceValue resValue = value.getValue();
                                    if (resValue == null) {
                                        continue;;
                                    }
                                    else {
                                        this.newApkAnimResNames.add(resValue.toStringValue());
                                        continue;;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean patch(File oldFile, File newFile) {
        String name = this.getRelativePathStringToNewFile(newFile);
        if (newFile == null || newFile.exists()) {
            String relativeStringByOldDir = this.getRelativePathStringToOldFile(oldFile);
            if (Utils.checkFileInPattern(this.config.mResIgnoreChangePattern, relativeStringByOldDir)) {
                Logger.e(new StringBuilder().append("found delete resource: ").append(relativeStringByOldDir).append(" ,but it match ignore change pattern, just ignore!").toString());
                return false;
            }
            else {
                this.deletedSet.add(relativeStringByOldDir);
                this.writeResLog(newFile, oldFile, 3);
                return true;
            }
        }
        else {
            File outputFile = this.getOutputPath(newFile).toFile();
            if (oldFile == null || oldFile.exists()) {
                if (Utils.checkFileInPattern(this.config.mResIgnoreChangePattern, name)) {
                    Logger.e(new StringBuilder().append("found add resource: ").append(name).append(" ,but it match ignore change pattern, just ignore!").toString());
                    return false;
                }
                else {
                    FileOperation.copyFileUsingStream(newFile, outputFile);
                    this.addedSet.add(name);
                    this.writeResLog(newFile, oldFile, 1);
                    return true;
                }
            }
            else {
                if (0L == oldFile.length() && 0L == newFile.length()) {
                    return false;
                }
                else {
                    String newMd5 = MD5.getMD5(newFile);
                    String oldMd5 = MD5.getMD5(oldFile);
                    if (oldMd5 != null && oldMd5.equals(newMd5)) {
                        return false;
                    }
                    else if (Utils.checkFileInPattern(this.config.mResIgnoreChangePattern, name)) {
                        Logger.d(new StringBuilder().append("found modify resource: ").append(name).append(", but it match ignore change pattern, just ignore!").toString());
                        return false;
                    }
                    else if (name.equals("AndroidManifest.xml")) {
                        Logger.d(new StringBuilder().append("found modify resource: ").append(name).append(", but it is AndroidManifest.xml, just ignore!").toString());
                        return false;
                    }
                    else {
                        if (name.equals("resources.arsc") && AndroidParser.resourceTableLogicalChange(this.config)) {
                            Logger.d(new StringBuilder().append("found modify resource: ").append(name).append(", but it is logically the same as original new resources.arsc, just ignore!").toString());
                            return false;
                        }
                        else {
                            this.dealWithModifyFile(name, newMd5, oldFile, newFile, outputFile);
                            return true;
                        }
                    }
                }
            }
        }
    }

    private boolean dealWithModifyFile(String name, String newMd5, File oldFile, File newFile, File outputFile) {
        if (this.checkLargeModFile(newFile)) {
            if (outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            DiffFactory.diffFile(this.config, oldFile, newFile, outputFile);
            if (Utils.checkBsDiffFileSize(outputFile, newFile)) {
                ResDiffDecoder$LargeModeInfo largeModeInfo = new ResDiffDecoder$LargeModeInfo(this);
                largeModeInfo.path = newFile;
                largeModeInfo.crc = FileOperation.getFileCrc32(newFile);
                largeModeInfo.md5 = newMd5;
                this.largeModifiedSet.add(name);
                this.largeModifiedMap.put(name, largeModeInfo);
                this.writeResLog(newFile, oldFile, 4);
                return true;
            }
        }
        this.modifiedSet.add(name);
        FileOperation.copyFileUsingStream(newFile, outputFile);
        this.writeResLog(newFile, oldFile, 2);
        return false;
    }

    private void writeResLog(File newFile, File oldFile, int mode) {
        if (this.logWriter != null) {
            String log;
            log = "";
            switch(mode) {
                String relative;
                case 1: {
                    relative = this.getRelativePathStringToNewFile(newFile);
                    Logger.d(new StringBuilder().append("Found add resource: ").append(relative).toString());
                    log = new StringBuilder().append("add resource: ").append(relative).append(", oldSize=").append(FileOperation.getFileSizes(oldFile)).append(", newSize=").append(FileOperation.getFileSizes(newFile)).toString();
                    break;;
                }
                case 2: {
                    relative = this.getRelativePathStringToNewFile(newFile);
                    Logger.d(new StringBuilder().append("Found modify resource: ").append(relative).toString());
                    log = new StringBuilder().append("modify resource: ").append(relative).append(", oldSize=").append(FileOperation.getFileSizes(oldFile)).append(", newSize=").append(FileOperation.getFileSizes(newFile)).toString();
                    break;;
                }
                case 3: {
                    relative = this.getRelativePathStringToOldFile(oldFile);
                    Logger.d(new StringBuilder().append("Found deleted resource: ").append(relative).toString());
                    log = new StringBuilder().append("deleted resource: ").append(relative).append(", oldSize=").append(FileOperation.getFileSizes(oldFile)).append(", newSize=").append(FileOperation.getFileSizes(newFile)).toString();
                    break;;
                }
                case 4: {
                    relative = this.getRelativePathStringToNewFile(newFile);
                    Logger.d(new StringBuilder().append("Found large modify resource: ").append(relative).append(" size:").append(newFile.length()).toString());
                    log = new StringBuilder().append("large modify resource: ").append(relative).append(", oldSize=").append(FileOperation.getFileSizes(oldFile)).append(", newSize=").append(FileOperation.getFileSizes(newFile)).toString();
                    break;;
                }
            }
            this.logWriter.writeLineToInfoFile(log);
        }
    }

    private void addAssetsFileForTestResource() {
        File dest = new File(new StringBuilder().append(this.config.mTempResultDir).append("/").append("assets/only_use_to_test_tinker_resource.txt").toString());
        FileOperation.copyResourceUsingStream("only_use_to_test_tinker_resource.txt", dest);
        this.addedSet.add("assets/only_use_to_test_tinker_resource.txt");
        Logger.d("Add Test resource file: assets/only_use_to_test_tinker_resource.txt");
        String log = new StringBuilder().append("add test resource: assets/only_use_to_test_tinker_resource.txt, oldSize=0, newSize=").append(FileOperation.getFileSizes(dest)).toString();
        this.logWriter.writeLineToInfoFile(log);
    }

    public void onAllPatchesEnd() {
        if (this.addedSet.isEmpty() && this.modifiedSet.isEmpty() && this.largeModifiedSet.isEmpty()) {
        }
        else if (this.config.mResRawPattern.contains("resources.arsc")) {
            throw new TinkerPatchException("resource must contain resources.arsc pattern");
        }
        else if (this.config.mResRawPattern.contains("AndroidManifest.xml")) {
            throw new TinkerPatchException("resource must contain AndroidManifest.xml pattern");
        }
        else {
            if (this.config.mUsingGradle) {
                boolean ignoreWarning = this.config.mIgnoreWarning;
                int resourceArscChanged = ! this.modifiedSet.contains("resources.arsc") || this.largeModifiedSet.contains("resources.arsc") ? 0 : 1;
                if (resourceArscChanged != 0 && this.config.mUseApplyResource) {
                    if (ignoreWarning) {
                        Logger.e("Warning:ignoreWarning is true, but resources.arsc is changed, you should use applyResourceMapping mode to build the new apk, otherwise, it may be crash at some times");
                    }
                    else {
                        Logger.e("Warning:ignoreWarning is false, but resources.arsc is changed, you should use applyResourceMapping mode to build the new apk, otherwise, it may be crash at some times");
                        throw new TinkerPatchException(String.format("ignoreWarning is false, but resources.arsc is changed, you should use applyResourceMapping mode to build the new apk, otherwise, it may be crash at some times", new Object[]{}));
                    }
                }
            }
            this.deletedSet.addAll(this.getDeletedResource(this.config.mTempUnzipOldDir, this.config.mTempUnzipNewDir));
            this.addedSet.remove("AndroidManifest.xml");
            this.deletedSet.remove("AndroidManifest.xml");
            this.modifiedSet.remove("AndroidManifest.xml");
            this.largeModifiedSet.remove("AndroidManifest.xml");
            this.removeIgnoreChangeFile(this.modifiedSet);
            this.removeIgnoreChangeFile(this.deletedSet);
            this.removeIgnoreChangeFile(this.addedSet);
            this.removeIgnoreChangeFile(this.largeModifiedSet);
            this.checkIfSpecificResWasAnimRes(this.addedSet);
            this.checkIfSpecificResWasAnimRes(this.modifiedSet);
            this.checkIfSpecificResWasAnimRes(this.largeModifiedSet);
            this.addAssetsFileForTestResource();
            File tempResZip = new File(new StringBuilder().append(this.config.mOutFolder).append(File.separator).append("temp_res.zip").toString());
            File tempResFiles = this.config.mTempResultDir;
            FileOperation.zipInputDir(tempResFiles, tempResZip, null);
            File extractToZip = new File(new StringBuilder().append(this.config.mOutFolder).append(File.separator).append("resources_out.zip").toString());
            String resZipMd5 = Utils.genResOutputFile(extractToZip, tempResZip, this.config, this.addedSet, this.modifiedSet, this.deletedSet, this.largeModifiedSet, this.largeModifiedMap);
            Logger.e("Final normal zip resource: %s, size=%d, md5=%s", new Object[]{extractToZip.getName(), Long.valueOf(extractToZip.length()), resZipMd5});
            this.logWriter.writeLineToInfoFile(String.format("Final normal zip resource: %s, size=%d, md5=%s", new Object[]{extractToZip.getName(), Long.valueOf(extractToZip.length()), resZipMd5}));
            FileOperation.deleteFile(tempResZip);
            String arscBaseCrc = FileOperation.getZipEntryCrc(this.config.mOldApkFile, "resources.arsc");
            String arscMd5 = FileOperation.getZipEntryMd5(extractToZip, "resources.arsc");
            if (arscBaseCrc == null || arscMd5 == null) {
                throw new TinkerPatchException("can't find resources.arsc's base crc or md5");
            }
            else {
                String resourceMeta = Utils.getResourceMeta(arscBaseCrc, arscMd5);
                this.writeMetaFile(resourceMeta);
                String patternMeta = "pattern:";
                HashSet patterns = new HashSet(this.config.mResRawPattern);
                patterns.remove("AndroidManifest.xml");
                this.writeMetaFile(new StringBuilder().append(patternMeta).append(patterns.size()).toString());
                Iterator iterator = patterns.iterator();
                while (iterator.hasNext()) {
                    String item = (String)iterator.next();
                    this.writeMetaFile(item);
                }
                this.getCompressMethodFromApk();
                this.writeMetaFile(this.largeModifiedSet, 4);
                this.writeMetaFile(this.modifiedSet, 2);
                this.writeMetaFile(this.addedSet, 1);
                this.writeMetaFile(this.deletedSet, 3);
                this.writeMetaFile(this.storedSet, 5);
            }
        }
    }

    private void checkIfSpecificResWasAnimRes(Collection<String> specificFileNames) {
        HashSet changedAnimResNames = new HashSet();
        Iterator iterator = specificFileNames.iterator();
        while (iterator.hasNext()) {
            String resFileName = (String)iterator.next();
            if (this.newApkAnimResNames.contains(resFileName)) {
                if (Utils.isStringMatchesPatterns(resFileName, this.config.mResIgnoreChangeWarningPattern)) {
                    Logger.d(new StringBuilder().append("
Animation resource: ").append(resFileName).append(" was changed, but it's filtered by ignoreChangeWarning pattern, just ignore.
").toString());
                    continue;;
                }
                else {
                    changedAnimResNames.add(resFileName);
                }
            }
        }
        if (changedAnimResNames.isEmpty()) {
            if (this.config.mIgnoreWarning) {
                Logger.e(new StringBuilder().append("Warning:ignoreWarning is true, but we found animation resource is changed. Please check if any one was used in 'overridePendingTransition' which may leads to crash. If all of them were not used in that method, just add them into 'res { ignoreChangeWarning }' option.
related res: ").append(changedAnimResNames).append("
").toString());
            }
            else {
                Logger.e(new StringBuilder().append("Warning:ignoreWarning is false, but we found animation resource is changed. Please check if any one was used in 'overridePendingTransition' which may leads to crash. If all of them were not used in that method, just add them into 'res { ignoreChangeWarning }' option.
related res: ").append(changedAnimResNames).append("
").toString());
                throw new TinkerPatchException(new StringBuilder().append("ignoreWarning is false, but we found animation resource is changed. Please check if any one was used in 'overridePendingTransition' which may leads to crash. If all of them were not used in that method, just add them into 'res { ignoreChangeWarning }' option.
related res: ").append(changedAnimResNames).toString());
            }
        }
        else {
        }
    }

    private void getCompressMethodFromApk() {
        Object zipFile = null;
        try {
            ZipFile file = new ZipFile(this.config.mNewApkFile);
            ArrayList sets = new ArrayList();
            sets.addAll(this.modifiedSet);
            sets.addAll(this.addedSet);
            Iterator iterator = sets.iterator();
            while (iterator.hasNext()) {
                String name = (String)iterator.next();
                ZipEntry zipEntry = file.getEntry(name);
                if (zipEntry != null && zipEntry.getMethod() == 0) {
                    this.storedSet.add(name);
                }
            }
            if (file != null) {
                try {
                    file.close();
                }
                catch (IOException var_2_1) {
                }
            }
        }
        catch (Throwable var_2_0) {
            if (file != null) {
                try {
                    file.close();
                }
                catch (IOException var_2_2) {
                }
            }
        }
        finally {
            Throwable throwable = v_17;
            if (file != null) {
                try {
                    file.close();
                }
                catch (IOException var_7_0) {
                }
            }
            throw throwable;
        }
    }

    private void removeIgnoreChangeFile(ArrayList<String> array) {
        ArrayList removeList = new ArrayList();
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            String name = (String)iterator.next();
            if (Utils.checkFileInPattern(this.config.mResIgnoreChangePattern, name)) {
                Logger.e(new StringBuilder().append("ignore change resource file: ").append(name).toString());
                removeList.add(name);
            }
        }
        array.removeAll(removeList);
    }

    private void writeMetaFile(String line) {
        this.metaWriter.writeLineToInfoFile(line);
    }

    private void writeMetaFile(ArrayList<String> set, int mode) {
        if (set.isEmpty()) {
            String title;
            title = "";
            switch(mode) {
                case 1: {
                    title = new StringBuilder().append("add:").append(set.size()).toString();
                    break;;
                }
                case 2: {
                    title = new StringBuilder().append("modify:").append(set.size()).toString();
                    break;;
                }
                case 4: {
                    title = new StringBuilder().append("large modify:").append(set.size()).toString();
                    break;;
                }
                case 3: {
                    title = new StringBuilder().append("delete:").append(set.size()).toString();
                    break;;
                }
                case 5: {
                    title = new StringBuilder().append("store:").append(set.size()).toString();
                    break;;
                }
            }
            this.metaWriter.writeLineToInfoFile(title);
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                String name = (String)iterator.next();
                if (mode == 4) {
                    ResDiffDecoder$LargeModeInfo info = (ResDiffDecoder$LargeModeInfo)this.largeModifiedMap.get(name);
                    line = new StringBuilder().append(name).append(",").append(info.md5).append(",").append(info.crc).toString();
                }
                this.metaWriter.writeLineToInfoFile(name);
            }
        }
    }

    public ArrayList<String> getDeletedResource(File oldApkDir, File newApkDir) {
        ResDiffDecoder$DeletedResVisitor deletedResVisitor = new ResDiffDecoder$DeletedResVisitor(this, this.config, newApkDir.toPath(), oldApkDir.toPath());
        Files.walkFileTree(oldApkDir.toPath(), deletedResVisitor);
        return deletedResVisitor.deletedFiles;
    }

    static /* synthetic */ void access$000(ResDiffDecoder x0, File x1, File x2, int x3) {
        x0.writeResLog(x1, x2, x3);
    }

    // class: com/tencent/tinker/build/decoder/ResDiffDecoder$LargeModeInfo
    public class ResDiffDecoder$LargeModeInfo {
        public File path;
        public long crc;
        public String md5;
        final synthetic ResDiffDecoder this$0;

        public ResDiffDecoder$LargeModeInfo(ResDiffDecoder this$0) {
            this.this$0 = this$0;
            super();
            this.path = null;
            this.md5 = null;
        }

    }
    // class: com/tencent/tinker/build/decoder/ResDiffDecoder$LargeModeInfo
    public class ResDiffDecoder$LargeModeInfo {
        public File path;
        public long crc;
        public String md5;
        final synthetic ResDiffDecoder this$0;

        public ResDiffDecoder$LargeModeInfo(ResDiffDecoder this$0) {
            this.this$0 = this$0;
            super();
            this.path = null;
            this.md5 = null;
        }

    }
    // class: com/tencent/tinker/build/decoder/ResDiffDecoder$DeletedResVisitor
    class ResDiffDecoder$DeletedResVisitor {
        Configuration config;
        Path newApkPath;
        Path oldApkPath;
        ArrayList<String> deletedFiles;
        final synthetic ResDiffDecoder this$0;

         ResDiffDecoder$DeletedResVisitor(ResDiffDecoder this$0, Configuration config, Path newPath, Path oldPath) {
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
            if (Utils.checkFileInPattern(this.config.mResFilePattern, patternKey)) {
                if (newPath.toFile().exists()) {
                    this.deletedFiles.add(patternKey);
                    ResDiffDecoder.access$000(this.this$0, newPath.toFile(), file.toFile(), 3);
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
    // class: com/tencent/tinker/build/decoder/ResDiffDecoder$DeletedResVisitor
    class ResDiffDecoder$DeletedResVisitor {
        Configuration config;
        Path newApkPath;
        Path oldApkPath;
        ArrayList<String> deletedFiles;
        final synthetic ResDiffDecoder this$0;

         ResDiffDecoder$DeletedResVisitor(ResDiffDecoder this$0, Configuration config, Path newPath, Path oldPath) {
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
            if (Utils.checkFileInPattern(this.config.mResFilePattern, patternKey)) {
                if (newPath.toFile().exists()) {
                    this.deletedFiles.add(patternKey);
                    ResDiffDecoder.access$000(this.this$0, newPath.toFile(), file.toFile(), 3);
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
}
