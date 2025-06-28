/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import java.net.URL;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.File;
import android.annotation.SuppressLint;
import com.tencent.tinker.anno.Keep;

// class: com/tencent/tinker/loader/TinkerClassLoader
@SuppressLint({"NewApi"})
public final class TinkerClassLoader {
    final private ClassLoader a;

    @Keep
     TinkerClassLoader(String str0, File file, String str1, ClassLoader loader) {
        super("", str1, ClassLoader.getSystemClassLoader());
        this.a = loader;
        TinkerClassLoader.a(this, str0, file);
    }

    protected Class<?> findClass(String str0) {
        Object object = null;
        try {
            Class class = super.findClass(str0);
            goto 14;
        }
        catch (ClassNotFoundException var_3_0) {
            Object objectVar1 = null;
        }
        if (class != null) {
            return class;
        }
        else {
            return this.a.loadClass(str0);
        }
    }

    public URL getResource(String str0) {
        URL lVar1 = Object.class.getClassLoader().getResource(str0);
        if (lVar1 != null) {
            return lVar1;
        }
        else {
            lVar1 = this.findResource(str0);
            if (lVar1 != null) {
                return lVar1;
            }
            else {
                return this.a.getResource(str0);
            }
        }
    }

    public Enumeration<URL> getResources(String str0) {
        Enumeration enumeration = new Enumeration[]{Object.class.getClassLoader().getResources(str0), this.findResources(str0), this.a.getResources(str0)};
        return new TinkerClassLoader$CompoundEnumeration(this, enumeration);
    }

    private static void a(ClassLoader loader, String str0, File file) {
        try {
            ArrayList list = new ArrayList(16);
            String[] stringArr0 = str0.split(":");
            for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                String str1 = stringArr0[i1];
                if (str1.isEmpty()) {
                    continue;;
                }
                else {
                    list.add(new File(str1));
                }
            }
            if (list.isEmpty()) {
                d.a(loader, list, file);
            }
            return;
        }
        catch (Throwable var_3_1) {
            throw new k("Fail to create TinkerClassLoader.", var_3_1);
        }
    }

    // class: com/tencent/tinker/loader/TinkerClassLoader$CompoundEnumeration
@Keep
    class TinkerClassLoader$CompoundEnumeration<E> implements Enumeration<E> {
        private Enumeration<E>[] enums;
        private int index;
        final synthetic TinkerClassLoader this$0;

        publicvoid TinkerClassLoader$CompoundEnumeration(TinkerClassLoader loader, Enumeration[] enumerationArr0) {
            this.this$0 = loader;
            super();
            this.index = 0;
            this.enums = enumerationArr0;
        }

        public boolean hasMoreElements() {
            while (this.index < this.enums.length) {
                if (this.enums[this.index] != null && this.enums[this.index].hasMoreElements()) {
                    return true;
                }
                else {
                    this.index = this.index + 1;
                    continue;;
                }
            }
            return false;
        }

        public E nextElement() {
            if (this.hasMoreElements()) {
                throw new NoSuchElementException();
            }
            else {
                return this.enums[this.index].nextElement();
            }
        }

    }
    // class: com/tencent/tinker/loader/TinkerClassLoader$CompoundEnumeration
@Keep
    class TinkerClassLoader$CompoundEnumeration<E> implements Enumeration<E> {
        private Enumeration<E>[] enums;
        private int index;
        final synthetic TinkerClassLoader this$0;

        publicvoid TinkerClassLoader$CompoundEnumeration(TinkerClassLoader loader, Enumeration[] enumerationArr0) {
            this.this$0 = loader;
            super();
            this.index = 0;
            this.enums = enumerationArr0;
        }

        public boolean hasMoreElements() {
            while (this.index < this.enums.length) {
                if (this.enums[this.index] != null && this.enums[this.index].hasMoreElements()) {
                    return true;
                }
                else {
                    this.index = this.index + 1;
                    continue;;
                }
            }
            return false;
        }

        public E nextElement() {
            if (this.hasMoreElements()) {
                throw new NoSuchElementException();
            }
            else {
                return this.enums[this.index].nextElement();
            }
        }

    }
}
