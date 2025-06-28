/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;

import java.nio.charset.Charset;

// class: com/tencent/tinker/ziputils/ziputil/StandardCharsets
public final class StandardCharsets {
    final public static Charset UTF_8;

    private StandardCharsets() {
        super();
    }

    static  {
        StandardCharsets.UTF_8 = Charset.forName("UTF-8");
    }

}
