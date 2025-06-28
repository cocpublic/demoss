/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;


// class: com/tencent/tinker/ziputils/ziputil/Arrays
public class Arrays {

    public Arrays() {
        super();
    }

    public static void checkOffsetAndCount(int arrayLength, int offset, int count) {
        if (offset | count >= 0 || offset <= arrayLength || arrayLength - offset < count) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        else {
        }
    }

}
