/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;


// class: com/tencent/tinker/loader/k
public class k {

    public k(String str0) {
        super(new StringBuilder().append("Tinker Exception:").append(str0).toString());
    }

    public k(String str0, Throwable throwable) {
        super(new StringBuilder().append("Tinker Exception:").append(str0).toString(), throwable);
    }

}
