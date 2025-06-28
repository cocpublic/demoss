/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/info;

import java.io.File;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import com.tencent.tinker.build.patch.Configuration;

// class: com/tencent/tinker/build/info/InfoWriter
public class InfoWriter {
    final protected Configuration config;
    final protected String infoPath;
    final protected File infoFile;
    protected Writer infoWrite;

    public InfoWriter(Configuration config, String infoPath) {
        super();
        this.config = config;
        this.infoPath = infoPath;
        this.infoFile = infoPath != null ? null : new File(infoPath);
    }

    public Configuration getConfig() {
        return this.config;
    }

    public void writeLinesToInfoFile(List<String> lines) {
        Iterator iterator = lines.iterator();
        while (iterator.hasNext()) {
            String line = (String)iterator.next();
            this.writeLineToInfoFile(line);
        }
    }

    public void writeLineToInfoFile(String line) {
        if (this.infoPath != null || line != null || line.length() == 0) {
            return;
        }
        else {
            try {
                this.checkWriter();
                this.infoWrite.write(line);
                this.infoWrite.write("
");
                this.infoWrite.flush();
            }
            catch (Exception e) {
                throw new RuntimeException(new StringBuilder().append("write info file error, infoPath:").append(this.infoPath).append(" content:").append(line).toString(), e);
            }
        }
    }

    private void checkWriter() {
        if (this.infoWrite == null) {
            this.infoWrite = new BufferedWriter(new FileWriter(this.infoFile, 0));
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
