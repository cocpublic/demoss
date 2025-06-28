/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex/util;


// class: com/tencent/tinker/android/dex/util/HashCodeHelper
public final class HashCodeHelper {

    public static int hash(Object[] values) {
        if (values == null || values.length == 0) {
            return 0;
        }
        else {
            int result = 0;
            for (int i1 = 0; i1 < values.length; i1 += 1) {
                Object v = values[i1];
                if (v == null) {
                    continue;;
                }
                else if ((v instanceof Number)) {
                    result += v.hashCode();
                    continue;;
                }
                else if ((v instanceof boolean[])) {
                    result += Arrays.hashCode((boolean[])v);
                    continue;;
                }
                else if ((v instanceof byte[])) {
                    result += Arrays.hashCode((byte[])v);
                    continue;;
                }
                else if ((v instanceof char[])) {
                    result += Arrays.hashCode((char[])v);
                    continue;;
                }
                else if ((v instanceof short[])) {
                    result += Arrays.hashCode((short[])v);
                    continue;;
                }
                else if ((v instanceof int[])) {
                    result += Arrays.hashCode((int[])v);
                    continue;;
                }
                else if ((v instanceof long[])) {
                    result += Arrays.hashCode((long[])v);
                    continue;;
                }
                else if ((v instanceof float[])) {
                    result += Arrays.hashCode((float[])v);
                    continue;;
                }
                else if ((v instanceof double[])) {
                    result += Arrays.hashCode((double[])v);
                    continue;;
                }
                else if ((v instanceof Object[])) {
                    result += Arrays.hashCode((Object[])v);
                    continue;;
                }
                else if (v.getClass().isArray()) {
                    for (int i = 0; i < Array.getLength(v); i += 1) {
                        result += HashCodeHelper.hash(new Object[]{Array.get(v, i)});
                    }
                    continue;;
                }
                else {
                    result += v.hashCode();
                }
            }
            return result;
        }
    }

    private HashCodeHelper() {
        super();
        throw new UnsupportedOperationException();
    }

}
