/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/res/util;

import java.io.File;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;

// class: com/tencent/rfix/build/res/util/InfoWriter
public class InfoWriter {
    private Writer infoWrite;
    final private File infoFile;

    public InfoWriter(String infoPath) {
        super();
        this.infoFile = new File(infoPath);
        if (this.infoFile.getParentFile().exists()) {
            this.infoFile.getParentFile().mkdirs();
        }
    }

    public void writeLineToInfoFile(String line) {
        try {
            if (this.infoWrite == null) {
                this.infoWrite = new BufferedWriter(new FileWriter(this.infoFile, 0));
            }
            this.infoWrite.write(line);
            this.infoWrite.write("
");
            this.infoWrite.flush();
            return;
        }
        catch (Exception e) {
            throw new RuntimeException(new StringBuilder().append("write info file error, infoPath:").append(this.infoFile).append(" content:").append(line).toString(), e);
        }
    }

    public void close() {
        try {
            if (this.infoWrite != null) {
                this.infoWrite.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
