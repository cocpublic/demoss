/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/SizeOf
public final class SizeOf {
    final public static int UBYTE;
    final public static int USHORT;
    final public static int UINT;
    final public static int SIGNATURE;
    final public static int CHECKSUM;
    final public static int HEADER_ITEM;
    final public static int STRING_ID_ITEM;
    final public static int TYPE_ID_ITEM;
    final public static int TYPE_ITEM;
    final public static int PROTO_ID_ITEM;
    final public static int MEMBER_ID_ITEM;
    final public static int CALLSITE_ID_ITEM;
    final public static int METHOD_HANDLE_ITEM;
    final public static int CLASS_DEF_ITEM;
    final public static int MAP_ITEM;
    final public static int TRY_ITEM;

    private SizeOf() {
        super();
    }

    public static int roundToTimesOfFour(int value) {
        return value + 3 & 252;
    }

}
