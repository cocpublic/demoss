/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;

// class: com/tencent/tinker/ziputils/ziputil/TinkerZipUtil
public class TinkerZipUtil {
    final private static int BUFFER_SIZE;

    public TinkerZipUtil() {
        super();
    }

    public static void extractTinkerEntry(TinkerZipFile apk, TinkerZipEntry zipEntry, TinkerZipOutputStream outputStream) {
        Object in = null;
        try {
            InputStream stream = apk.getInputStream(zipEntry);
            outputStream.putNextEntry(new TinkerZipEntry(zipEntry));
            byte[] buffer = new byte[]{};
            int length = stream.read(buffer);
            while (length != -1) {
                outputStream.write(buffer, 0, length);
                length = stream.read(buffer);
            }
            outputStream.closeEntry();
            return;
        }
        finally {
            Throwable throwable = v_12;
            if (stream != null) {
                stream.close();
            }
            throw throwable;
        }
    }

    public static void extractLargeModifyFile(TinkerZipEntry sourceArscEntry, File newFile, long newFileCrc, TinkerZipOutputStream newFileCrc) {
        TinkerZipEntry newArscZipEntry = new TinkerZipEntry(sourceArscEntry);
        newArscZipEntry.setMethod(0);
        newArscZipEntry.setSize(newFile.length());
        newArscZipEntry.setCompressedSize(newFile.length());
        newArscZipEntry.setCrc(newFileCrc);
        Object in = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(newFile));
            outputStream.putNextEntry(new TinkerZipEntry(newArscZipEntry));
            byte[] buffer = new byte[]{};
            int length = stream.read(buffer);
            while (length != -1) {
                outputStream.write(buffer, 0, length);
                length = stream.read(buffer);
            }
            outputStream.closeEntry();
            return;
        }
        finally {
            Throwable throwable = v_24;
            if (stream != null) {
                stream.close();
            }
            throw throwable;
        }
    }

    public static boolean validateZipEntryName(File destDir, String entryName) {
        if (entryName == null || entryName.isEmpty()) {
            return false;
        }
        else {
            try {
                String canonicalDestinationDir = destDir.getCanonicalPath();
                File destEntryFile = new File(destDir, entryName);
                return destEntryFile.getCanonicalPath().startsWith(new StringBuilder().append(canonicalDestinationDir).append(File.separator).toString());
            }
            catch (Throwable ignored) {
                return false;
            }
        }
    }

}
