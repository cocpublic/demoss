/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import java.util.regex.Pattern;

// class: com/tencent/tinker/loader/shareutil/c
public class c {
    final public static Pattern a;

    static  {
        c.a = Pattern.compile("classes(?:[2-9]?|[1-9][0-9]+)\.dex(\.jar)?");
    }

}
