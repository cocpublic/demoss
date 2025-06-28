/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/res/util;

import java.io.File[];
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.CRC32;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ArrayList;
import java.nio.file.Path;

// class: com/tencent/rfix/build/res/util/FileOperation
public class FileOperation {

    public FileOperation() {
        super();
    }

    public static boolean deleteFile(File file) {
        if (file == null) {
            return true;
        }
        else if (file.exists()) {
            return file.delete();
        }
        else {
            return true;
        }
    }

    public static boolean deleteDir(File file) {
        if (file == null || file.exists()) {
            return false;
        }
        else {
            if (file.isFile()) {
                file.delete();
            }
            else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i += 1) {
                    FileOperation.deleteDir(files[i]);
                }
            }
            file.delete();
            return true;
        }
    }

    public static void copy(File source, File dest) {
        Object is = null;
        Object os = null;
        File parent = dest.getParentFile();
        if (parent != null && parent.exists()) {
            parent.mkdirs();
        }
        try {
            FileInputStream stream = new FileInputStream(source);
            stream = new FileOutputStream(dest, 0);
            byte[] buffer = new byte[]{};
            while (true) {
                int length = stream.read(buffer);
                if (stream.read(buffer) > 0) {
                    stream.write(buffer, 0, length);
                }
                else {
                }
            }
        }
        finally {
            Throwable throwable = v_16;
            IOHelper.closeQuietly(stream);
            IOHelper.closeQuietly(stream);
            throw throwable;
        }
    }

    public static boolean checkDirectory(String dir) {
        File dirObj = new File(dir);
        FileOperation.deleteDir(dirObj);
        if (dirObj.exists()) {
            dirObj.mkdirs();
        }
        return true;
    }

    public static void unZipAPk(String fileName, String filePath) {
        FileOperation.checkDirectory(filePath);
        ZipFile zipFile = new ZipFile(fileName);
        Enumeration enumeration = zipFile.entries();
        try {
            while (enumeration.hasMoreElements()) {
                ZipEntry entry = (ZipEntry)enumeration.nextElement();
                FileOperation.validateZipEntryName(new File(filePath), entry.getName());
                throw new IOException(new StringBuilder().append("Bad ZipEntry name: ").append(entry.getName()).toString());
                entry.isDirectory();
                new File(filePath, entry.getName()).mkdirs();
                continue;;
                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
                File file = new File(new StringBuilder().append(filePath).append(File.separator).append(entry.getName()).toString());
                File parentFile = file.getParentFile();
                parentFile != null && parentFile.exists();
                parentFile.mkdirs();
                Object fos = null;
                Object bos = null;
                try {
                    FileOutputStream stream = new FileOutputStream(file);
                    stream = new BufferedOutputStream(stream, 16384);
                    byte[] buf = new byte[]{};
                    while (true) {
                        int len = bis.read(buf, 0, 16384);
                        if (bis.read(buf, 0, 16384) != -1) {
                            stream.write(buf, 0, len);
                        }
                        else {
                            break;;
                        }
                    }
                }
                finally {
                    Throwable throwable = v_63;
                    if (stream != null) {
                        stream.flush();
                        stream.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                    throw throwable;
                }
            }
            return;
        }
        finally {
            Throwable throwableVar1 = v_8;
            if (zipFile != null) {
                zipFile.close();
            }
            throw throwableVar1;
        }
    }

    public static void zipFiles(Collection<File> resFileList, File zipFile, String comment) {
        FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 16384);
        ZipOutputStream zipout = new ZipOutputStream(bufferedOutputStream);
        Iterator iterator = resFileList.iterator();
        while (iterator.hasNext()) {
            File resFile = (File)iterator.next();
            if (resFile.exists()) {
                FileOperation.zipFile(resFile, zipout, "");
            }
        }
        if (comment != null) {
            zipout.setComment(comment);
        }
        zipout.close();
    }

    private static void zipFile(File resFile, ZipOutputStream zipout, String rootpath) {
        rootpath = new StringBuilder().append(rootpath).append(rootpath.trim().length() == 0 ? File.separator : "").append(resFile.getName()).toString();
        if (resFile.isDirectory()) {
            File[] fileList = resFile.listFiles();
            for (int i1 = 0; i1 < fileList.length; i1 += 1) {
                File file = fileList[i1];
                FileOperation.zipFile(file, zipout, rootpath);
            }
        }
        else {
            byte[] fileContents = FileOperation.readContents(resFile);
            if (rootpath.contains("\")) {
                rootpath = rootpath.replace("\", "/");
            }
            ZipEntry entry = new ZipEntry(rootpath);
            entry.setMethod(8);
            zipout.putNextEntry(entry);
            zipout.write(fileContents);
            zipout.flush();
            zipout.closeEntry();
        }
    }

    private static byte[] readContents(File file) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int bufferSize = 16384;
        Object in = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[]{};
            while (true) {
                int length = stream.read(buffer, 0, 16384);
                if (stream.read(buffer, 0, 16384) > 0) {
                    byte[] bufferCopy = new byte[]{};
                    System.arraycopy(buffer, 0, bufferCopy, 0, length);
                    output.write(bufferCopy);
                }
                else {
                    break;;
                }
            }
        }
        finally {
            Throwable throwable = v_8;
            IOHelper.closeQuietly(output);
            IOHelper.closeQuietly(stream);
            throw throwable;
        }
        return output.toByteArray();
    }

    public static long getFileCrc32(File file) {
        Object inputStream = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
            CRC32 crc = new CRC32();
            while (true) {
                int cnt = stream.read();
                if (stream.read() != -1) {
                    crc.update(cnt);
                }
                else {
                    long l0 = crc.getValue();
                    IOHelper.closeQuietly(stream);
                    return l0;
                }
            }
        }
        finally {
            Throwable throwable = v_5;
            IOHelper.closeQuietly(stream);
            throw throwable;
        }
    }

    public static String getZipEntryCrc(File file, String entryName) {
        Object zipFile = null;
        try {
            file = new ZipFile(file);
            ZipEntry entry = file.getEntry(entryName);
            if (entry == null) {
                Object object = null;
                if (file != null) {
                    try {
                        file.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return object;
            }
            else {
                String str0 = String.valueOf(entry.getCrc());
                if (file != null) {
                    try {
                        file.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return str0;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            try {
            }
            catch (IOException e) {
            }
        }
        finally {
            Throwable throwable = v_8;
            file != null;
            try {
                file.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            throw throwable;
        }
        return null;
    }

    public static String getZipEntryMd5(File file, String entryName) {
        Object zipFile = null;
        try {
            file = new ZipFile(file);
            ZipEntry entry = file.getEntry(entryName);
            if (entry == null) {
                Object object = null;
                if (file != null) {
                    try {
                        file.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return object;
            }
            else {
                String str0 = MD5Util.getMD5(file.getInputStream(entry), 102400);
                if (file != null) {
                    try {
                        file.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return str0;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            try {
            }
            catch (IOException e) {
            }
        }
        finally {
            Throwable throwable = v_8;
            file != null;
            try {
                file.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            throw throwable;
        }
        return null;
    }

    public static void zipInputDir(File inputDir, File outputFile, String comment) {
        File[] unzipFiles = inputDir.listFiles();
        ArrayList collectFiles = new ArrayList();
        for (int i1 = 0; i1 < unzipFiles.length; i1 += 1) {
            File f = unzipFiles[i1];
            collectFiles.add(f);
        }
        FileOperation.zipFiles(collectFiles, outputFile, comment);
    }

    private static boolean validateZipEntryName(File destDir, String entryName) {
        if (entryName == null || entryName.isEmpty()) {
            return false;
        }
        else {
            try {
                String canonicalDestinationDir = destDir.getCanonicalPath();
                File destEntryFile = destDir.toPath().resolve(entryName).toFile();
                return destEntryFile.getCanonicalPath().startsWith(new StringBuilder().append(canonicalDestinationDir).append(File.separator).toString());
            }
            catch (Throwable ignored) {
                return false;
            }
        }
    }

    public static boolean checkBsDiffFileSize(File bsDiffFile, File newFile) {
        if (bsDiffFile.exists()) {
            return false;
        }
        else {
            double ratio = (double)bsDiffFile.length() / (double)newFile.length();
            if (0.800000 <= ratio) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public static boolean isLegalFile(String path) {
        if (path == null) {
            return false;
        }
        else {
            File file = new File(path);
            if (file.exists() && file.isFile() && 0L > file.length()) {
                return true;
            }
            else {
                return false;
            }
        }
    }

}
