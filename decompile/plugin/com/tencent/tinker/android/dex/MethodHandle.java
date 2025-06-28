/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/MethodHandle
public class MethodHandle {
    public MethodHandle$MethodHandleType methodHandleType;
    public int unused1;
    public int fieldOrMethodId;
    public int unused2;

    public MethodHandle(int off, MethodHandle$MethodHandleType methodHandleType, int unused1, int fieldOrMethodId, int unused2) {
        super(off);
        this.methodHandleType = methodHandleType;
        this.unused1 = unused1;
        this.fieldOrMethodId = fieldOrMethodId;
        this.unused2 = unused2;
    }

    public int byteCountInDex() {
        return 8;
    }

    public int compareTo(MethodHandle o) {
        if (this.methodHandleType != o.methodHandleType) {
            return this.methodHandleType.compareTo(o.methodHandleType);
        }
        else {
            return CompareUtils.uCompare(this.fieldOrMethodId, o.fieldOrMethodId);
        }
    }

    public void writeTo(Dex$Section out) {
        out.writeUnsignedShort(this.methodHandleType.value);
        out.writeUnsignedShort(this.unused1);
        out.writeUnsignedShort(this.fieldOrMethodId);
        out.writeUnsignedShort(this.unused2);
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((MethodHandle)object);
    }

    // class: com/tencent/tinker/android/dex/MethodHandle$MethodHandleType
    public final enum MethodHandle$MethodHandleType {
        final public int value;

        privatevoid MethodHandle$MethodHandleType(String str0, int i0, int value) {
            this.value = value;
        }

        public static MethodHandle$MethodHandleType fromValue(int value) {
            MethodHandle$MethodHandleType[] typeArr0 = MethodHandle$MethodHandleType.values();
            for (int i1 = 0; i1 < typeArr0.length; i1 += 1) {
                MethodHandle$MethodHandleType methodHandleType = typeArr0[i1];
                if (methodHandleType.value == value) {
                    return methodHandleType;
                }
                else {
                }
            }
            throw new IllegalArgumentException(String.valueOf(value));
        }

        public boolean isField() {
            switch(MethodHandle$1.$SwitchMap$com$tencent$tinker$android$dex$MethodHandle$MethodHandleType[this.ordinal()]) {
                case 1: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }

        static  {
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_STATIC_PUT = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_STATIC_PUT", 0, 0);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_STATIC_GET = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_STATIC_GET", 1, 1);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_PUT = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INSTANCE_PUT", 2, 2);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_GET = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INSTANCE_GET", 3, 3);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_STATIC = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_STATIC", 4, 4);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_INSTANCE = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_INSTANCE", 5, 5);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_DIRECT = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_DIRECT", 6, 6);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR", 7, 7);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_INTERFACE = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_INTERFACE", 8, 8);
            MethodHandle$MethodHandleType.$VALUES = new MethodHandle$MethodHandleType[]{MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_STATIC_PUT, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_STATIC_GET, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_PUT, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_GET, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_STATIC, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_INSTANCE, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_DIRECT, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_INTERFACE};
        }

    }
    // class: com/tencent/tinker/android/dex/MethodHandle$MethodHandleType
    public final enum MethodHandle$MethodHandleType {
        final public int value;

        privatevoid MethodHandle$MethodHandleType(String str0, int i0, int value) {
            this.value = value;
        }

        public static MethodHandle$MethodHandleType fromValue(int value) {
            MethodHandle$MethodHandleType[] typeArr0 = MethodHandle$MethodHandleType.values();
            for (int i1 = 0; i1 < typeArr0.length; i1 += 1) {
                MethodHandle$MethodHandleType methodHandleType = typeArr0[i1];
                if (methodHandleType.value == value) {
                    return methodHandleType;
                }
                else {
                }
            }
            throw new IllegalArgumentException(String.valueOf(value));
        }

        public boolean isField() {
            switch(MethodHandle$1.$SwitchMap$com$tencent$tinker$android$dex$MethodHandle$MethodHandleType[this.ordinal()]) {
                case 1: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }

        static  {
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_STATIC_PUT = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_STATIC_PUT", 0, 0);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_STATIC_GET = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_STATIC_GET", 1, 1);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_PUT = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INSTANCE_PUT", 2, 2);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_GET = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INSTANCE_GET", 3, 3);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_STATIC = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_STATIC", 4, 4);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_INSTANCE = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_INSTANCE", 5, 5);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_DIRECT = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_DIRECT", 6, 6);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR", 7, 7);
            MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_INTERFACE = new MethodHandle$MethodHandleType("METHOD_HANDLE_TYPE_INVOKE_INTERFACE", 8, 8);
            MethodHandle$MethodHandleType.$VALUES = new MethodHandle$MethodHandleType[]{MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_STATIC_PUT, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_STATIC_GET, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_PUT, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INSTANCE_GET, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_STATIC, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_INSTANCE, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_DIRECT, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_CONSTRUCTOR, MethodHandle$MethodHandleType.METHOD_HANDLE_TYPE_INVOKE_INTERFACE};
        }

    }
}
