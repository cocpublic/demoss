/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex/util;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

// class: com/tencent/tinker/android/dex/util/FileUtils
public final class FileUtils {

    private FileUtils() {
        super();
    }

    public static byte[] readFile(String fileName) {
        File file = new File(fileName);
        return FileUtils.readFile(file);
    }

    public static byte[] readFile(File file) {
        if (file.exists()) {
            throw new RuntimeException(new StringBuilder().append(file).append(": file not found").toString());
        }
        else if (file.isFile()) {
            throw new RuntimeException(new StringBuilder().append(file).append(": not a file").toString());
        }
        else if (file.canRead()) {
            throw new RuntimeException(new StringBuilder().append(file).append(": file not readable").toString());
        }
        else {
            long longLength = file.length();
            int length = (int)longLength;
            if (longLength != (long)length) {
                throw new RuntimeException(new StringBuilder().append(file).append(": file too long").toString());
            }
            else {
                ByteArrayOutputStream baos = new ByteArrayOutputStream(length);
                Object in = null;
                try {
                    BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
                    byte[] buffer = new byte[]{};
                    int bytesRead = 0;
                    while (true) {
                        bytesRead = stream.read(buffer);
                        if (stream.read(buffer) > 0) {
                            baos.write(buffer, 0, bytesRead);
                        }
                        else {
                            try {
                            }
                            catch (Exception var_6_0) {
                                break;;
                            }
                        }
                    }
                }
                finally {
                    Throwable throwable = v_55;
                    if (stream != null) {
                        try {
                            stream.close();
                        }
                        catch (Exception var_9_0) {
                        }
                    }
                    throw throwable;
                }
                return baos.toByteArray();
            }
        }
    }

    public static byte[] readStream(InputStream is) {
        return FileUtils.readStream(is, 32768);
    }

    public static byte[] readStream(InputStream is, int initSize) {
        if (initSize <= 0) {
            initSize = 32768;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream(initSize);
        byte[] buffer = new byte[]{};
        while (true) {
            int bytesRead = is.read(buffer);
            if (is.read(buffer) > 0) {
                baos.write(buffer, 0, bytesRead);
            }
            else {
                return baos.toByteArray();
            }
        }
    }

    public static boolean hasArchiveSuffix(String fileName) {
        if (fileName.endsWith(".zip") || fileName.endsWith(".jar") || fileName.endsWith(".apk")) {
            return true;
        }
        else {
            return false;
        }
    }

}
