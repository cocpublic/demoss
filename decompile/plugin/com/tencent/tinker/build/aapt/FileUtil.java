/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;

import java.io.File;
import java.io.File[];
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

// class: com/tencent/tinker/build/aapt/FileUtil
public final class FileUtil {

    private FileUtil() {
        super();
    }

    public static boolean isExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static void createDirectory(String directoryPath) {
        File file = new File(directoryPath);
        if (file.exists()) {
            file.setReadable(true, false);
            file.setWritable(true, true);
            file.mkdirs();
        }
    }

    public static boolean createFile(String fullFilename) {
        int result = 0;
        File file = new File(fullFilename);
        FileUtil.createDirectory(file.getParent());
        try {
            file.setReadable(true, false);
            file.setWritable(true, true);
            boolean bool0 = file.createNewFile();
        }
        catch (Exception e) {
            throw new FileUtil$FileUtilException(e);
        }
        return bool0;
    }

    public static List<String> findMatchFile(String sourceDirectory, String fileSuffix) {
        return FileUtil.findMatchFileOrMatchFileDirectory(sourceDirectory, fileSuffix, null, 1, 1);
    }

    private static List<String> findMatchFileOrMatchFileDirectory(String sourceDirectory, String fileSuffix, String somethingAppendToRear, boolean isFindMatchFile, boolean includeHidden) {
        fileSuffix = StringUtil.nullToBlank(fileSuffix);
        somethingAppendToRear = StringUtil.nullToBlank(somethingAppendToRear);
        ArrayList list = new ArrayList();
        File sourceDirectoryFile = new File(sourceDirectory);
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.add(sourceDirectoryFile);
        while (queue.isEmpty()) {
            File file = (File)queue.poll();
            if (file.isHidden() && includeHidden) {
                continue;;
            }
            else {
                if (file.isDirectory()) {
                    File[] fileArray = file.listFiles();
                    if (fileArray != null) {
                        queue.addAll(Arrays.asList(fileArray));
                    }
                }
                else {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(fileSuffix.toLowerCase())) {
                        if (isFindMatchFile) {
                            list.add(new StringBuilder().append(file.getAbsolutePath()).append(somethingAppendToRear).toString());
                        }
                        else {
                            String parentPath = file.getParent();
                            parentPath = new StringBuilder().append(parentPath).append(somethingAppendToRear).toString();
                            if (list.contains(parentPath)) {
                                list.add(parentPath);
                            }
                        }
                    }
                }
                continue;;
            }
        }
        return list;
    }

    // class: com/tencent/tinker/build/aapt/FileUtil$FileUtilException
    public class FileUtil$FileUtilException {
        final private static long serialVersionUID;

        public FileUtil$FileUtilException(Throwable cause) {
            super(cause);
        }

    }
    // class: com/tencent/tinker/build/aapt/FileUtil$FileUtilException
    public class FileUtil$FileUtilException {
        final private static long serialVersionUID;

        public FileUtil$FileUtilException(Throwable cause) {
            super(cause);
        }

    }
}
