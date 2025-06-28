/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/EncodedValueReader
public final class EncodedValueReader {
    final public static int ENCODED_BYTE;
    final public static int ENCODED_SHORT;
    final public static int ENCODED_CHAR;
    final public static int ENCODED_INT;
    final public static int ENCODED_LONG;
    final public static int ENCODED_FLOAT;
    final public static int ENCODED_DOUBLE;
    final public static int ENCODED_METHOD_TYPE;
    final public static int ENCODED_METHOD_HANDLE;
    final public static int ENCODED_STRING;
    final public static int ENCODED_TYPE;
    final public static int ENCODED_FIELD;
    final public static int ENCODED_ENUM;
    final public static int ENCODED_METHOD;
    final public static int ENCODED_ARRAY;
    final public static int ENCODED_ANNOTATION;
    final public static int ENCODED_NULL;
    final public static int ENCODED_BOOLEAN;
    final private static int MUST_READ;
    final protected ByteInput in;
    private int type;
    private int annotationType;
    private int arg;

    public EncodedValueReader(ByteInput in) {
        super();
        this.type = -1;
        this.in = in;
    }

    public EncodedValueReader(EncodedValue in) {
        super(in.asByteInput());
    }

    public EncodedValueReader(ByteInput in, int knownType) {
        super();
        this.type = -1;
        this.in = in;
        this.type = knownType;
    }

    public EncodedValueReader(EncodedValue in, int knownType) {
        super(in.asByteInput(), knownType);
    }

    public int peek() {
        if (this.type == -1) {
            int argAndType = this.in.readByte() & 255;
            this.type = argAndType & 31;
            this.arg = argAndType & 224 >> 5;
        }
        return this.type;
    }

    public int readArray() {
        this.checkType(28);
        this.type = -1;
        return Leb128.readUnsignedLeb128(this.in);
    }

    public int readAnnotation() {
        this.checkType(29);
        this.type = -1;
        this.annotationType = Leb128.readUnsignedLeb128(this.in);
        return Leb128.readUnsignedLeb128(this.in);
    }

    public int getAnnotationType() {
        return this.annotationType;
    }

    public int readAnnotationName() {
        return Leb128.readUnsignedLeb128(this.in);
    }

    public byte readByte() {
        this.checkType(0);
        this.type = -1;
        return (byte)EncodedValueCodec.readSignedInt(this.in, this.arg);
    }

    public short readShort() {
        this.checkType(2);
        this.type = -1;
        return (short)EncodedValueCodec.readSignedInt(this.in, this.arg);
    }

    public char readChar() {
        this.checkType(3);
        this.type = -1;
        return (char)EncodedValueCodec.readUnsignedInt(this.in, this.arg, 0);
    }

    public int readInt() {
        this.checkType(4);
        this.type = -1;
        return EncodedValueCodec.readSignedInt(this.in, this.arg);
    }

    public long readLong() {
        this.checkType(6);
        this.type = -1;
        return EncodedValueCodec.readSignedLong(this.in, this.arg);
    }

    public float readFloat() {
        this.checkType(16);
        this.type = -1;
        return Float.intBitsToFloat(EncodedValueCodec.readUnsignedInt(this.in, this.arg, 1));
    }

    public double readDouble() {
        this.checkType(17);
        this.type = -1;
        return Double.longBitsToDouble(EncodedValueCodec.readUnsignedLong(this.in, this.arg, 1));
    }

    public int readMethodType() {
        this.checkType(21);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, 0);
    }

    public int readMethodHandle() {
        this.checkType(22);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, 0);
    }

    public int readString() {
        this.checkType(23);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, 0);
    }

    public int readType() {
        this.checkType(24);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, 0);
    }

    public int readField() {
        this.checkType(25);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, 0);
    }

    public int readEnum() {
        this.checkType(27);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, 0);
    }

    public int readMethod() {
        this.checkType(26);
        this.type = -1;
        return EncodedValueCodec.readUnsignedInt(this.in, this.arg, 0);
    }

    public void readNull() {
        this.checkType(30);
        this.type = -1;
    }

    public boolean readBoolean() {
        this.checkType(31);
        this.type = -1;
        if (this.arg != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void skipValue() {
        switch(this.peek()) {
            case 0: {
                this.readByte();
                return;
            }
            case 2: {
                this.readShort();
                return;
            }
            case 3: {
                this.readChar();
                return;
            }
            case 4: {
                this.readInt();
                return;
            }
            case 6: {
                this.readLong();
                return;
            }
            case 16: {
                this.readFloat();
                return;
            }
            case 17: {
                this.readDouble();
                return;
            }
            case 21: {
                this.readMethodType();
                return;
            }
            case 22: {
                this.readMethodHandle();
                return;
            }
            case 23: {
                this.readString();
                return;
            }
            case 24: {
                this.readType();
                return;
            }
            case 25: {
                this.readField();
                return;
            }
            case 27: {
                this.readEnum();
                return;
            }
            case 26: {
                this.readMethod();
                return;
            }
            int i;
            int size;
            case 28: {
                i = 0;
                for (size = this.readArray(); i < size; i += 1) {
                    this.skipValue();
                }
                return;
            }
            case 29: {
                i = 0;
                for (size = this.readAnnotation(); i < size; i += 1) {
                    this.readAnnotationName();
                    this.skipValue();
                }
                return;
            }
            case 30: {
                this.readNull();
                return;
            }
            case 31: {
                this.readBoolean();
                return;
            }
            default: {
                throw new DexException(new StringBuilder().append("Unexpected type: ").append(Integer.toHexString(this.type)).toString());
            }
        }
    }

    private void checkType(int expected) {
        if (this.peek() != expected) {
            throw new IllegalStateException(String.format("Expected %x but was %x", new Object[]{Integer.valueOf(expected), Integer.valueOf(this.peek())}));
        }
        else {
        }
    }

}
