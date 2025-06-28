/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/a/a/b;


// class: com/tencent/tinker/a/a/b/e
public final class e {

    public static int a(Object[] objectArr0) {
        if (objectArr0 == null || objectArr0.length == 0) {
            return 0;
        }
        else {
            int i0 = 0;
            for (int i2 = 0; i2 < objectArr0.length; i2 += 1) {
                Object object = objectArr0[i2];
                if (object == null) {
                    continue;;
                }
                else if ((object instanceof Number)) {
                    i0 += object.hashCode();
                    continue;;
                }
                else if ((object instanceof boolean[])) {
                    i0 += Arrays.hashCode((boolean[])object);
                    continue;;
                }
                else if ((object instanceof byte[])) {
                    i0 += Arrays.hashCode((byte[])object);
                    continue;;
                }
                else if ((object instanceof char[])) {
                    i0 += Arrays.hashCode((char[])object);
                    continue;;
                }
                else if ((object instanceof short[])) {
                    i0 += Arrays.hashCode((short[])object);
                    continue;;
                }
                else if ((object instanceof int[])) {
                    i0 += Arrays.hashCode((int[])object);
                    continue;;
                }
                else if ((object instanceof long[])) {
                    i0 += Arrays.hashCode((long[])object);
                    continue;;
                }
                else if ((object instanceof float[])) {
                    i0 += Arrays.hashCode((float[])object);
                    continue;;
                }
                else if ((object instanceof double[])) {
                    i0 += Arrays.hashCode((double[])object);
                    continue;;
                }
                else if ((object instanceof Object[])) {
                    i0 += Arrays.hashCode((Object[])object);
                    continue;;
                }
                else if (object.getClass().isArray()) {
                    for (int i3 = 0; i3 < Array.getLength(object); i3 += 1) {
                        i0 += e.a(new Object[]{Array.get(object, i3)});
                    }
                    continue;;
                }
                else {
                    i0 += object.hashCode();
                }
            }
            return i0;
        }
    }

}
