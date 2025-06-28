/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/c;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.tencent.tinker.loader.g$b;

// class: com/tencent/tinker/lib/c/a
public abstract class a {

    public boolean a() {
        try {
            n.a("Tinker.AbsCustomDexOptimizer", new StringBuilder().append("run custom dex optimizer: ").append(this.d.getAbsolutePath()).toString(), new Object[]{});
            if (h.a(this.d) && this.h != null) {
                this.h.a(this.d, this.e, new IOException(new StringBuilder().append("dex file ").append(this.d.getAbsolutePath()).append(" is not exist!").toString()));
                return false;
            }
            else {
                if (this.h != null) {
                    this.h.a(this.d, this.e);
                }
                String str0 = h.b(this.d, this.e);
                if (m.c()) {
                    String str1 = this.d.getAbsolutePath();
                    this.a(str1, str0);
                }
                File file = new File(str0);
                if (! h.a(file) || m.a(29, 1)) {
                    if (this.h != null) {
                        this.h.a(this.d, this.e, file);
                    }
                    return true;
                }
                else {
                    FileNotFoundException exception = new FileNotFoundException(new StringBuilder().append("Odex file: ").append(file.getAbsolutePath()).append(" does not exist.").toString());
                    if (this.h != null) {
                        this.h.a(this.d, this.e, exception);
                    }
                    return false;
                }
            }
        }
        catch (Throwable var_1_1) {
            n.d("Tinker.AbsCustomDexOptimizer", new StringBuilder().append("Failed to optimize dex: ").append(this.d.getAbsolutePath()).toString(), new Object[]{var_1_1});
            this.h != null;
            this.h.a(this.d, this.e, var_1_1);
            return false;
        }
    }

    void a(String p0, String p1);

}
