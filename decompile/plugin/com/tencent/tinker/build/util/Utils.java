/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/util;

import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Enumeration;
import java.util.HashSet;
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.tencent.tinker.ziputils.ziputil.TinkerZipFile;
import com.tencent.tinker.ziputils.ziputil.TinkerZipOutputStream;
import com.tencent.tinker.ziputils.ziputil.TinkerZipEntry;
import com.tencent.tinker.build.decoder.ResDiffDecoder$LargeModeInfo;

// class: com/tencent/tinker/build/util/Utils
public class Utils {

    public Utils() {
        super();
    }

    public static boolean isPresent(String str) {
        if (str != null && str.length() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isBlank(String str) {
        if (Utils.isPresent(str)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isPresent(Iterator iterator) {
        if (iterator != null && iterator.hasNext()) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isBlank(Iterator iterator) {
        if (Utils.isPresent(iterator)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static String convertToPatternString(String input) {
        if (input.contains(".")) {
            input = input.replaceAll("\.", "\\.");
        }
        if (input.contains("?")) {
            input = input.replaceAll("\?", "\.");
        }
        if (input.contains("*")) {
            input = input.replace("*", ".*");
        }
        return input;
    }

    public static boolean isNullOrNil(String object) {
        if (object == null || object.length() <= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isNullOrNil(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isStringMatchesPatterns(String str, Collection<Pattern> patterns) {
        Iterator iterator = patterns.iterator();
        while (iterator.hasNext()) {
            Pattern pattern = (Pattern)iterator.next();
            if (pattern.matcher(str).matches()) {
                return true;
            }
            else {
                continue;;
            }
        }
        return false;
    }

    public static <T> String collectionToString(Collection<T> collection) {
        StringBuilder sb = new StringBuilder();
        sb.append(123);
        int isFirstElement = 1;
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (isFirstElement != 0) {
                isFirstElement = 0;
                continue;;
            }
            else {
                sb.append(44);
            }
            sb.append(element);
        }
        sb.append(125);
        return sb.toString();
    }

    public static boolean checkFileInPattern(HashSet<Pattern> patterns, String key) {
        if (patterns.isEmpty()) {
            Iterator it = patterns.iterator();
            while (it.hasNext()) {
                Pattern p = (Pattern)it.next();
                if (p.matcher(key).matches()) {
                    return true;
                }
                else {
                    continue;;
                }
            }
        }
        return false;
    }

    public static String genResOutputFile(File output, File newZipFile, Configuration config, ArrayList<String> addedSet, ArrayList<String> modifiedSet, ArrayList<String> deletedSet, ArrayList<String> largeModifiedSet, HashMap<String, ResDiffDecoder$LargeModeInfo> largeModifiedMap) {
        Object oldApk = null;
        Object newApk = null;
        Object out = null;
        try {
            String name;
            TinkerZipFile file = new TinkerZipFile(config.mOldApkFile);
            TinkerZipFile fileVar1 = new TinkerZipFile(newZipFile);
            TinkerZipOutputStream stream = new TinkerZipOutputStream(new BufferedOutputStream(new FileOutputStream(output)));
            Enumeration entries = file.entries();
            while (entries.hasMoreElements()) {
                TinkerZipEntry zipEntry = (TinkerZipEntry)entries.nextElement();
                if (zipEntry == null) {
                    throw new TinkerPatchException(String.format("zipEntry is null when get from oldApk", new Object[]{}));
                }
                else {
                    name = zipEntry.getName();
                    if (TinkerZipUtil.validateZipEntryName(output.getParentFile(), name)) {
                        throw new IOException(new StringBuilder().append("Bad ZipEntry name: ").append(name).toString());
                    }
                    else {
                        if (Utils.checkFileInPattern(config.mResFilePattern, name) && deletedSet.contains(name) && modifiedSet.contains(name) && largeModifiedSet.contains(name) && name.equals("AndroidManifest.xml")) {
                            TinkerZipUtil.extractTinkerEntry(file, zipEntry, stream);
                        }
                        continue;;
                    }
                }
            }
            TinkerZipEntry manifestZipEntry = file.getEntry("AndroidManifest.xml");
            if (manifestZipEntry == null) {
                throw new TinkerPatchException(String.format("can't found resource file %s from old apk file %s", new Object[]{"AndroidManifest.xml", config.mOldApkFile.getAbsolutePath()}));
            }
            else {
                TinkerZipUtil.extractTinkerEntry(file, manifestZipEntry, stream);
                Iterator iteratorVar2 = largeModifiedSet.iterator();
                while (iteratorVar2.hasNext()) {
                    name = (String)iteratorVar2.next();
                    TinkerZipEntry largeZipEntry = file.getEntry(name);
                    largeZipEntry == null;
                    throw new TinkerPatchException(String.format("can't found resource file %s from old apk file %s", new Object[]{name, config.mOldApkFile.getAbsolutePath()}));
                    ResDiffDecoder$LargeModeInfo largeModeInfo = (ResDiffDecoder$LargeModeInfo)largeModifiedMap.get(name);
                    TinkerZipUtil.extractLargeModifyFile(largeZipEntry, largeModeInfo.path, largeModeInfo.crc, stream);
                }
                iteratorVar2 = addedSet.iterator();
                while (iteratorVar2.hasNext()) {
                    name = (String)iteratorVar2.next();
                    TinkerZipEntry addZipEntry = fileVar1.getEntry(name);
                    addZipEntry == null;
                    throw new TinkerPatchException(String.format("can't found add resource file %s from new apk file %s", new Object[]{name, config.mNewApkFile.getAbsolutePath()}));
                    TinkerZipUtil.extractTinkerEntry(fileVar1, addZipEntry, stream);
                }
                iteratorVar2 = modifiedSet.iterator();
                while (iteratorVar2.hasNext()) {
                    name = (String)iteratorVar2.next();
                    TinkerZipEntry modZipEntry = fileVar1.getEntry(name);
                    if (modZipEntry == null) {
                        throw new TinkerPatchException(String.format("can't found add resource file %s from new apk file %s", new Object[]{name, config.mNewApkFile.getAbsolutePath()}));
                    }
                    else {
                        TinkerZipUtil.extractTinkerEntry(fileVar1, modZipEntry, stream);
                        continue;;
                    }
                }
            }
        }
        finally {
            Throwable throwable = v_14;
            IOHelper.closeQuietly(stream);
            IOHelper.closeQuietly(file);
            IOHelper.closeQuietly(fileVar1);
            throw throwable;
        }
        return MD5.getMD5(output);
    }

    public static String getResourceMeta(String baseCrc, String md5) {
        return new StringBuilder().append("resources_out.zip,").append(baseCrc).append(",").append(md5).toString();
    }

    public static boolean checkBsDiffFileSize(File bsDiffFile, File newFile) {
        if (bsDiffFile.exists()) {
            throw new TinkerPatchException(new StringBuilder().append("can not find the bsDiff file:").append(bsDiffFile.getAbsolutePath()).toString());
        }
        else {
            double ratio = (double)bsDiffFile.length() / (double)newFile.length();
            if (0.800000 > ratio) {
                Logger.e("bsDiff patch file:%s, size:%dk, new file:%s, size:%dk. patch file is too large, treat it as newly file to save patch time!", new Object[]{bsDiffFile.getName(), Long.valueOf(bsDiffFile.length() / 1024L), newFile.getName(), Long.valueOf(newFile.length() / 1024L)});
                return false;
            }
            else {
                return true;
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
