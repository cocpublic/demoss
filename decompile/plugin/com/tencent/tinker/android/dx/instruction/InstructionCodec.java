/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dx/instruction;

import com.tencent.tinker.android.dex.DexException;

// class: com/tencent/tinker/android/dx/instruction/InstructionCodec
public final class InstructionCodec {
    final public static int INDEX_TYPE_UNKNOWN;
    final public static int INDEX_TYPE_NONE;
    final public static int INDEX_TYPE_TYPE_REF;
    final public static int INDEX_TYPE_STRING_REF;
    final public static int INDEX_TYPE_METHOD_REF;
    final public static int INDEX_TYPE_FIELD_REF;
    final public static int INDEX_TYPE_METHOD_AND_PROTO_REF;
    final public static int INDEX_TYPE_CALL_SITE_REF;
    final public static int INDEX_TYPE_METHOD_HANDLE_REF;
    final public static int INDEX_TYPE_PROTO_REF;
    final public static int INSN_FORMAT_UNKNOWN;
    final public static int INSN_FORMAT_00X;
    final public static int INSN_FORMAT_10T;
    final public static int INSN_FORMAT_10X;
    final public static int INSN_FORMAT_11N;
    final public static int INSN_FORMAT_11X;
    final public static int INSN_FORMAT_12X;
    final public static int INSN_FORMAT_20T;
    final public static int INSN_FORMAT_21C;
    final public static int INSN_FORMAT_21H;
    final public static int INSN_FORMAT_21S;
    final public static int INSN_FORMAT_21T;
    final public static int INSN_FORMAT_22B;
    final public static int INSN_FORMAT_22C;
    final public static int INSN_FORMAT_22S;
    final public static int INSN_FORMAT_22T;
    final public static int INSN_FORMAT_22X;
    final public static int INSN_FORMAT_23X;
    final public static int INSN_FORMAT_30T;
    final public static int INSN_FORMAT_31C;
    final public static int INSN_FORMAT_31I;
    final public static int INSN_FORMAT_31T;
    final public static int INSN_FORMAT_32X;
    final public static int INSN_FORMAT_35C;
    final public static int INSN_FORMAT_3RC;
    final public static int INSN_FORMAT_51L;
    final public static int INSN_FORMAT_45CC;
    final public static int INSN_FORMAT_4RCC;
    final public static int INSN_FORMAT_PACKED_SWITCH_PAYLOAD;
    final public static int INSN_FORMAT_SPARSE_SWITCH_PAYLOAD;
    final public static int INSN_FORMAT_FILL_ARRAY_DATA_PAYLOAD;

    private InstructionCodec() {
        super();
        throw new UnsupportedOperationException();
    }

    public static void decode(ShortArrayCodeInput in, InstructionVisitor iv) {
        in.reset();
        while (in.hasMore()) {
int currentAddress = in.cursor();
int opcodeUnit = in.read();
int opcode = Opcodes.extractOpcodeFromUnit(opcodeUnit);
int insnFormat = InstructionCodec.getInstructionFormat(opcode);
            switch(insnFormat) {
                case 1: {
                    iv.visitZeroRegisterInsn(currentAddress, opcode, 0, 1, 0, 0L);
                    continue;;
                }
                int literal;
                case 3: {
                    literal = InstructionCodec.byte1(opcodeUnit);
                    iv.visitZeroRegisterInsn(currentAddress, opcode, 0, 1, 0, (long)literal);
                    continue;;
                }
                int a;
                int b;
                case 6: {
                    a = InstructionCodec.nibble2(opcodeUnit);
                    b = InstructionCodec.nibble3(opcodeUnit);
                    iv.visitTwoRegisterInsn(currentAddress, opcode, 0, 1, 0, 0L, a, b);
                    continue;;
                }
                case 4: {
                    a = InstructionCodec.nibble2(opcodeUnit);
                    literal = InstructionCodec.nibble3(opcodeUnit) << 28 >> 28;
                    iv.visitOneRegisterInsn(currentAddress, opcode, 0, 1, 0, (long)literal, a);
                    continue;;
                }
                case 5: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    iv.visitOneRegisterInsn(currentAddress, opcode, 0, 1, 0, 0L, a);
                    continue;;
                }
                int target;
                case 2: {
                    target = currentAddress + (byte)InstructionCodec.byte1(opcodeUnit);
                    iv.visitZeroRegisterInsn(currentAddress, opcode, 0, 1, target, 0L);
                    continue;;
                }
                case 7: {
                    literal = InstructionCodec.byte1(opcodeUnit);
                    target = currentAddress + (short)in.read();
                    iv.visitZeroRegisterInsn(currentAddress, opcode, 0, 1, target, (long)literal);
                    continue;;
                }
                case 16: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    b = in.read();
                    iv.visitTwoRegisterInsn(currentAddress, opcode, 0, 1, 0, 0L, a, b);
                    continue;;
                }
                case 11: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    target = currentAddress + (short)in.read();
                    iv.visitOneRegisterInsn(currentAddress, opcode, 0, 1, target, 0L, a);
                    continue;;
                }
                case 10: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    literal = (short)in.read();
                    iv.visitOneRegisterInsn(currentAddress, opcode, 0, 1, 0, (long)literal, a);
                    continue;;
                }
                case 9: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    literal = ;
                    literal <<= opcode == 21 ? 48 : 16;
                    iv.visitOneRegisterInsn(currentAddress, opcode, 0, 1, 0, literal, a);
                    continue;;
                }
                int index;
                int indexType;
                case 8: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    index = in.read();
                    indexType = InstructionCodec.getInstructionIndexType(opcode);
                    iv.visitOneRegisterInsn(currentAddress, opcode, index, indexType, 0, 0L, a);
                    continue;;
                }
                int bc;
                int c;
                case 17: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    bc = in.read();
                    b = InstructionCodec.byte0(bc);
                    c = InstructionCodec.byte1(bc);
                    iv.visitThreeRegisterInsn(currentAddress, opcode, 0, 1, 0, 0L, a, b, c);
                    continue;;
                }
                case 12: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    bc = in.read();
                    b = InstructionCodec.byte0(bc);
                    literal = (byte)InstructionCodec.byte1(bc);
                    iv.visitTwoRegisterInsn(currentAddress, opcode, 0, 1, 0, (long)literal, a, b);
                    continue;;
                }
                case 15: {
                    a = InstructionCodec.nibble2(opcodeUnit);
                    b = InstructionCodec.nibble3(opcodeUnit);
                    target = currentAddress + (short)in.read();
                    iv.visitTwoRegisterInsn(currentAddress, opcode, 0, 1, target, 0L, a, b);
                    continue;;
                }
                case 14: {
                    a = InstructionCodec.nibble2(opcodeUnit);
                    b = InstructionCodec.nibble3(opcodeUnit);
                    literal = (short)in.read();
                    iv.visitTwoRegisterInsn(currentAddress, opcode, 0, 1, 0, (long)literal, a, b);
                    continue;;
                }
                case 13: {
                    a = InstructionCodec.nibble2(opcodeUnit);
                    b = InstructionCodec.nibble3(opcodeUnit);
                    index = in.read();
                    indexType = InstructionCodec.getInstructionIndexType(opcode);
                    iv.visitTwoRegisterInsn(currentAddress, opcode, index, indexType, 0, 0L, a, b);
                    continue;;
                }
                case 18: {
                    literal = InstructionCodec.byte1(opcodeUnit);
                    target = currentAddress + in.readInt();
                    iv.visitZeroRegisterInsn(currentAddress, opcode, 0, 1, target, (long)literal);
                    continue;;
                }
                case 22: {
                    literal = InstructionCodec.byte1(opcodeUnit);
                    a = in.read();
                    b = in.read();
                    iv.visitTwoRegisterInsn(currentAddress, opcode, 0, 1, 0, (long)literal, a, b);
                    continue;;
                }
                case 20: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    literal = in.readInt();
                    iv.visitOneRegisterInsn(currentAddress, opcode, 0, 1, 0, (long)literal, a);
                    continue;;
                }
                case 21: {
a = InstructionCodec.byte1(opcodeUnit);
target = currentAddress + in.readInt();
                    switch(opcode) {
                        case 43: {
                            in.setBaseAddress(target + 1, currentAddress);
                        }
                    }
                    iv.visitOneRegisterInsn(currentAddress, opcode, 0, 1, target, 0L, a);
                    continue;;
                }
                case 19: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    index = in.readInt();
                    indexType = InstructionCodec.getInstructionIndexType(opcode);
                    iv.visitOneRegisterInsn(currentAddress, opcode, index, indexType, 0, 0L, a);
                    continue;;
                }
                int e;
                int registerCount;
                int d;
                case 23: {
e = InstructionCodec.nibble2(opcodeUnit);
registerCount = InstructionCodec.nibble3(opcodeUnit);
index = in.read();
int abcd = in.read();
a = InstructionCodec.nibble0(abcd);
b = InstructionCodec.nibble1(abcd);
c = InstructionCodec.nibble2(abcd);
d = InstructionCodec.nibble3(abcd);
indexType = InstructionCodec.getInstructionIndexType(opcode);
                    switch(registerCount) {
                        case 0: {
                            iv.visitZeroRegisterInsn(currentAddress, opcode, index, indexType, 0, 0L);
                            continue;;
                        }
                        case 1: {
                            iv.visitOneRegisterInsn(currentAddress, opcode, index, indexType, 0, 0L, a);
                            continue;;
                        }
                        case 2: {
                            iv.visitTwoRegisterInsn(currentAddress, opcode, index, indexType, 0, 0L, a, b);
                            continue;;
                        }
                        case 3: {
                            iv.visitThreeRegisterInsn(currentAddress, opcode, index, indexType, 0, 0L, a, b, c);
                            continue;;
                        }
                        case 4: {
                            iv.visitFourRegisterInsn(currentAddress, opcode, index, indexType, 0, 0L, a, b, c, d);
                            continue;;
                        }
                        case 5: {
                            iv.visitFiveRegisterInsn(currentAddress, opcode, index, indexType, 0, 0L, a, b, c, d, e);
                            continue;;
                        }
                        default: {
                            throw new DexException(new StringBuilder().append("bogus registerCount: ").append(Hex.uNibble(registerCount)).toString());
                        }
                    }
                }
                case 24: {
                    registerCount = InstructionCodec.byte1(opcodeUnit);
                    index = in.read();
                    a = in.read();
                    indexType = InstructionCodec.getInstructionIndexType(opcode);
                    iv.visitRegisterRangeInsn(currentAddress, opcode, index, indexType, 0, 0L, a, registerCount);
                    continue;;
                }
                case 25: {
                    a = InstructionCodec.byte1(opcodeUnit);
                    literal = in.readLong();
                    iv.visitOneRegisterInsn(currentAddress, opcode, 0, 1, 0, literal, a);
                    continue;;
                }
                int methodIndex;
                int protoIndex;
                case 26: {
                    if (opcode != 250) {
                        throw new UnsupportedOperationException(String.valueOf(opcode));
                    }
                    else {
                        int g = InstructionCodec.nibble2(opcodeUnit);
                        registerCount = InstructionCodec.nibble3(opcodeUnit);
                        methodIndex = in.read();
                        int cdef = in.read();
                        c = InstructionCodec.nibble0(cdef);
                        d = InstructionCodec.nibble1(cdef);
                        e = InstructionCodec.nibble2(cdef);
                        int f = InstructionCodec.nibble3(cdef);
                        protoIndex = in.read();
                        indexType = InstructionCodec.getInstructionIndexType(opcode);
                        if (registerCount < 1 || registerCount > 5) {
                            throw new DexException(new StringBuilder().append("bogus registerCount: ").append(Hex.uNibble(registerCount)).toString());
                        }
                        else {
                            int[] registers = Arrays.copyOfRange(new int[]{c, d, e, f, g}, 0, registerCount);
                            iv.visitInvokePolymorphicInstruction(currentAddress, opcode, methodIndex, indexType, protoIndex, registers);
                            continue;;
                        }
                    }
                }
                case 27: {
                    if (opcode != 251) {
                        throw new UnsupportedOperationException(String.valueOf(opcode));
                    }
                    else {
                        registerCount = InstructionCodec.byte1(opcodeUnit);
                        methodIndex = in.read();
                        c = in.read();
                        protoIndex = in.read();
                        indexType = InstructionCodec.getInstructionIndexType(opcode);
                        iv.visitInvokePolymorphicRangeInstruction(currentAddress, opcode, methodIndex, indexType, c, registerCount, protoIndex);
                        continue;;
                    }
                }
                int baseAddress;
                int size;
                int[] targets;
                int i;
                case 28: {
                    baseAddress = in.baseAddressForCursor();
                    size = in.read();
                    int firstKey = in.readInt();
                    targets = new int[]{};
                    for (i = 0; i < size; i += 1) {
                        targets[i] = baseAddress + in.readInt();
                    }
                    iv.visitPackedSwitchPayloadInsn(currentAddress, opcodeUnit, firstKey, targets);
                    continue;;
                }
                case 29: {
                    baseAddress = in.baseAddressForCursor();
                    size = in.read();
                    int[] keys = new int[]{};
                    targets = new int[]{};
                    for (i = 0; i < size; i += 1) {
                        keys[i] = in.readInt();
                    }
                    for (i = 0; i < size; i += 1) {
                        targets[i] = baseAddress + in.readInt();
                    }
                    iv.visitSparseSwitchPayloadInsn(currentAddress, opcodeUnit, keys, targets);
                    continue;;
                }
                case 30: {
int elementWidth = in.read();
size = in.readInt();
                    switch(elementWidth) {
                        byte[] array;
                        case 1: {
                            array = new byte[]{};
                            int even = 1;
                            i = 0;
                            int value = 0;
                            while (i < size) {
                                even != 0;
                                value = in.read();
                                array[i] = (byte)value & 255;
                                value >>= 8;
                                i += 1;
                                even = even == 0 ? 0 : 1;
                            }
                            iv.visitFillArrayDataPayloadInsn(currentAddress, opcodeUnit, array, array.length, 1);
                            continue;;
                        }
                        case 2: {
                            array = new short[]{};
                            for (i = 0; i < size; i += 1) {
                                array[i] = (short)in.read();
                            }
                            iv.visitFillArrayDataPayloadInsn(currentAddress, opcodeUnit, array, array.length, 2);
                            continue;;
                        }
                        case 4: {
                            array = new int[]{};
                            for (i = 0; i < size; i += 1) {
                                array[i] = in.readInt();
                            }
                            iv.visitFillArrayDataPayloadInsn(currentAddress, opcodeUnit, array, array.length, 4);
                            continue;;
                        }
                        case 8: {
                            array = new long[]{};
                            for (i = 0; i < size; i += 1) {
                                array[i] = in.readLong();
                            }
                            iv.visitFillArrayDataPayloadInsn(currentAddress, opcodeUnit, array, array.length, 8);
                            continue;;
                        }
                        default: {
                            throw new DexException(new StringBuilder().append("bogus element_width: ").append(Hex.u2(elementWidth)).toString());
                        }
                    }
                }
                default: {
                    throw new DexException(new StringBuilder().append("Unknown instruction format: ").append(insnFormat).toString());
                }
            }
        }
    }

    public static void encode(ShortArrayCodeOutput out, InstructionWriter iw) {
int opcode = iw.currOpcode;
int insnFormat = InstructionCodec.getInstructionFormat(opcode);
        switch(insnFormat) {
            case 1: {
                out.write((short)opcode);
                return;
            }
            case 6: {
                out.write(InstructionCodec.codeUnit(opcode, InstructionCodec.makeByte(iw.currRegA, iw.currRegB)));
                return;
            }
            case 4: {
                out.write(InstructionCodec.codeUnit(opcode, InstructionCodec.makeByte(iw.currRegA, InstructionCodec.getLiteralNibble(iw.currLiteral))));
                return;
            }
            case 5: {
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA));
                return;
            }
            int relativeTarget;
            case 2: {
                relativeTarget = InstructionCodec.getTargetByte(iw.currTarget, out.cursor());
                out.write(InstructionCodec.codeUnit(opcode, relativeTarget));
                return;
            }
            case 7: {
                relativeTarget = InstructionCodec.getTargetUnit(iw.currTarget, out.cursor());
                out.write((short)opcode, relativeTarget);
                return;
            }
            case 16: {
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), InstructionCodec.getBUnit(iw.currRegB));
                return;
            }
            case 11: {
                relativeTarget = InstructionCodec.getTargetUnit(iw.currTarget, out.cursor());
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), relativeTarget);
                return;
            }
            case 10: {
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), InstructionCodec.getLiteralUnit(iw.currLiteral));
                return;
            }
            int literal;
            case 9: {
                int shift = opcode == 21 ? 48 : 16;
                literal = ;
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), literal);
                return;
            }
            case 8: {
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), (short)iw.currIndex);
                return;
            }
            case 17: {
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), InstructionCodec.codeUnit(iw.currRegB, iw.currRegC));
                return;
            }
            case 12: {
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), InstructionCodec.codeUnit(iw.currRegB, InstructionCodec.getLiteralByte(iw.currLiteral)));
                return;
            }
            case 15: {
                relativeTarget = InstructionCodec.getTargetUnit(iw.currTarget, out.cursor());
                out.write(InstructionCodec.codeUnit(opcode, InstructionCodec.makeByte(iw.currRegA, iw.currRegB)), relativeTarget);
                return;
            }
            case 14: {
                out.write(InstructionCodec.codeUnit(opcode, InstructionCodec.makeByte(iw.currRegA, iw.currRegB)), InstructionCodec.getLiteralUnit(iw.currLiteral));
                return;
            }
            case 13: {
                out.write(InstructionCodec.codeUnit(opcode, InstructionCodec.makeByte(iw.currRegA, iw.currRegB)), (short)iw.currIndex);
                return;
            }
            case 18: {
                relativeTarget = InstructionCodec.getTarget(iw.currTarget, out.cursor());
                out.write((short)opcode, InstructionCodec.unit0(relativeTarget), InstructionCodec.unit1(relativeTarget));
                return;
            }
            case 22: {
                out.write((short)opcode, InstructionCodec.getAUnit(iw.currRegA), InstructionCodec.getBUnit(iw.currRegB));
                return;
            }
            case 20: {
                literal = InstructionCodec.getLiteralInt(iw.currLiteral);
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), InstructionCodec.unit0(literal), InstructionCodec.unit1(literal));
                return;
            }
            case 21: {
                switch(opcode) {
                    case 43: {
                        out.setBaseAddress(iw.currTarget, out.cursor());
                        break;;
                    }
                }
                relativeTarget = InstructionCodec.getTarget(iw.currTarget, out.cursor());
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), InstructionCodec.unit0(relativeTarget), InstructionCodec.unit1(relativeTarget));
                return;
            }
            case 19: {
                int index = iw.currIndex;
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), InstructionCodec.unit0(index), InstructionCodec.unit1(index));
                return;
            }
            case 23: {
                out.write(InstructionCodec.codeUnit(opcode, InstructionCodec.makeByte(iw.currRegE, iw.currRegisterCount)), (short)iw.currIndex, InstructionCodec.codeUnit(iw.currRegA, iw.currRegB, iw.currRegC, iw.currRegD));
                return;
            }
            case 24: {
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegisterCount), (short)iw.currIndex, InstructionCodec.getAUnit(iw.currRegA));
                return;
            }
            case 25: {
                literal = iw.currLiteral;
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegA), InstructionCodec.unit0(literal), InstructionCodec.unit1(literal), InstructionCodec.unit2(literal), InstructionCodec.unit3(literal));
                return;
            }
            case 26: {
                out.write(InstructionCodec.codeUnit(opcode, InstructionCodec.makeByte(iw.currRegG, iw.currRegisterCount)), (short)iw.currIndex, InstructionCodec.codeUnit(iw.currRegC, iw.currRegD, iw.currRegE, iw.currRegF), (short)iw.currProtoIndex);
                return;
            }
            case 27: {
                out.write(InstructionCodec.codeUnit(opcode, iw.currRegisterCount), (short)iw.currIndex, InstructionCodec.getCUnit(iw.currRegC), (short)iw.currProtoIndex);
                return;
            }
            int[] targets;
            int baseAddress;
            int target;
            case 28: {
                targets = iw.currTargets;
                baseAddress = out.baseAddressForCursor();
                out.write((short)opcode);
                out.write(InstructionCodec.asUnsignedUnit(targets.length));
                out.writeInt(iw.currFirstKey);
                for (int i1 = 0; i1 < targets.length; i1 += 1) {
                    target = targets[i1];
                    out.writeInt(target - baseAddress);
                }
                return;
            }
            case 29: {
                int[] keys = iw.currKeys;
                targets = iw.currTargets;
                baseAddress = out.baseAddressForCursor();
                out.write((short)opcode);
                out.write(InstructionCodec.asUnsignedUnit(targets.length));
                for (int i5 = 0; i5 < keys.length; i5 += 1) {
                    int key = keys[i5];
                    out.writeInt(key);
                }
                intArr0Var2 = targets;
                i4 = keys.length;
                for (i5 = 0; i5 < keys.length; i5 += 1) {
                    target = keys[i5];
                    out.writeInt(target - baseAddress);
                }
                return;
            }
            case 30: {
int elementWidth = (short)iw.currElementWidth;
out.write((short)opcode);
out.write(elementWidth);
out.writeInt(iw.currSize);
Object data = iw.currData;
                switch(elementWidth) {
                    case 1: {
                        out.write((byte[])data);
                        return;
                    }
                    case 2: {
                        out.write((short[])data);
                        return;
                    }
                    case 4: {
                        out.write((int[])data);
                        return;
                    }
                    case 8: {
                        out.write((long[])data);
                        return;
                    }
                    default: {
                        throw new DexException(new StringBuilder().append("bogus element_width: ").append(Hex.u2(elementWidth)).toString());
                    }
                }
            }
            default: {
                throw new DexException(new StringBuilder().append("Unknown instruction format: ").append(insnFormat).toString());
            }
        }
    }

    public static short codeUnit(int lowByte, int highByte) {
        if (lowByte & 65280 != 0) {
            throw new IllegalArgumentException("bogus lowByte");
        }
        else if (highByte & 65280 != 0) {
            throw new IllegalArgumentException("bogus highByte");
        }
        else {
            return (short)lowByte | highByte << 8;
        }
    }

    public static short codeUnit(int nibble0, int nibble1, int nibble2, int nibble3) {
        if (nibble0 & 240 != 0) {
            throw new IllegalArgumentException("bogus nibble0");
        }
        else if (nibble1 & 240 != 0) {
            throw new IllegalArgumentException("bogus nibble1");
        }
        else if (nibble2 & 240 != 0) {
            throw new IllegalArgumentException("bogus nibble2");
        }
        else if (nibble3 & 240 != 0) {
            throw new IllegalArgumentException("bogus nibble3");
        }
        else {
            return (short)nibble0 | nibble1 << 4 | nibble2 << 8 | nibble3 << 12;
        }
    }

    public static int makeByte(int lowNibble, int highNibble) {
        if (lowNibble & 240 != 0) {
            throw new IllegalArgumentException("bogus lowNibble");
        }
        else if (highNibble & 240 != 0) {
            throw new IllegalArgumentException("bogus highNibble");
        }
        else {
            return lowNibble | highNibble << 4;
        }
    }

    public static short asUnsignedUnit(int value) {
        if (value & -65536 != 0) {
            throw new IllegalArgumentException("bogus unsigned code unit");
        }
        else {
            return (short)value;
        }
    }

    public static short unit0(int value) {
        return (short)value;
    }

    public static short unit1(int value) {
        return (short)value >> 16;
    }

    public static short unit0(long value) {
        return ;
    }

    public static short unit1(long value) {
        return ;
    }

    public static short unit2(long value) {
        return ;
    }

    public static short unit3(long value) {
        return ;
    }

    private static int byte0(int value) {
        return value & 255;
    }

    private static int byte1(int value) {
        return value >> 8 & 255;
    }

    private static int nibble0(int value) {
        return value & 15;
    }

    private static int nibble1(int value) {
        return value >> 4 & 15;
    }

    private static int nibble2(int value) {
        return value >> 8 & 15;
    }

    private static int nibble3(int value) {
        return value >> 12 & 15;
    }

    public static int getTargetByte(int target, int baseAddress) {
        int relativeTarget = InstructionCodec.getTarget(target, baseAddress);
        if (relativeTarget != (byte)relativeTarget) {
            throw new DexException(new StringBuilder().append("Target out of range: ").append(Hex.s4(relativeTarget)).append(", perhaps you need to enable force jumbo mode.").toString());
        }
        else {
            return relativeTarget & 255;
        }
    }

    public static short getTargetUnit(int target, int baseAddress) {
        int relativeTarget = InstructionCodec.getTarget(target, baseAddress);
        if (relativeTarget != (short)relativeTarget) {
            throw new DexException(new StringBuilder().append("Target out of range: ").append(Hex.s4(relativeTarget)).append(", perhaps you need to enable force jumbo mode.").toString());
        }
        else {
            return (short)relativeTarget;
        }
    }

    public static int getTarget(int target, int baseAddress) {
        return target - baseAddress;
    }

    public static int getLiteralByte(long literal) {
        if ( != literal) {
            throw new DexException(new StringBuilder().append("Literal out of range: ").append(Hex.u8(literal)).toString());
        }
        else {
            return (int)literal & 255;
        }
    }

    public static short getLiteralUnit(long literal) {
        if ( != literal) {
            throw new DexException(new StringBuilder().append("Literal out of range: ").append(Hex.u8(literal)).toString());
        }
        else {
            return ;
        }
    }

    public static int getLiteralInt(long literal) {
        if ( != literal) {
            throw new DexException(new StringBuilder().append("Literal out of range: ").append(Hex.u8(literal)).toString());
        }
        else {
            return (int)literal;
        }
    }

    public static int getLiteralNibble(long literal) {
        if (-8L < literal || 7L > literal) {
            throw new DexException(new StringBuilder().append("Literal out of range: ").append(Hex.u8(literal)).toString());
        }
        else {
            return (int)literal & 15;
        }
    }

    public static short getAUnit(int a) {
        if (a & -65536 != 0) {
            throw new DexException(new StringBuilder().append("Register A out of range: ").append(Hex.u8((long)a)).toString());
        }
        else {
            return (short)a;
        }
    }

    public static short getBUnit(int b) {
        if (b & -65536 != 0) {
            throw new DexException(new StringBuilder().append("Register B out of range: ").append(Hex.u8((long)b)).toString());
        }
        else {
            return (short)b;
        }
    }

    public static short getCUnit(int c) {
        if (c & -65536 != 0) {
            throw new DexException(new StringBuilder().append("Register C out of range: ").append(Hex.u8((long)c)).toString());
        }
        else {
            return (short)c;
        }
    }

    public static int getInstructionIndexType(int opcode) {
        switch(opcode) {
            case 26: {
                return 3;
            }
            case 254: {
                return 8;
            }
            case 255: {
                return 9;
            }
            case 28: {
                return 2;
            }
            case 82: {
                return 5;
            }
            case 110: {
                return 4;
            }
            case 250: {
                return 6;
            }
            case 252: {
                return 7;
            }
            case -1: {
                return 1;
            }
            default: {
                return 0;
            }
        }
    }

    public static int getInstructionFormat(int opcode) {
        switch(opcode) {
            case -1: {
                return 1;
            }
            case 0: {
                return 3;
            }
            case 1: {
                return 6;
            }
            case 2: {
                return 16;
            }
            case 3: {
                return 22;
            }
            case 10: {
                return 5;
            }
            case 18: {
                return 4;
            }
            case 19: {
                return 10;
            }
            case 20: {
                return 20;
            }
            case 21: {
                return 9;
            }
            case 24: {
                return 25;
            }
            case 26: {
                return 8;
            }
            case 27: {
                return 19;
            }
            case 32: {
                return 13;
            }
            case 36: {
                return 23;
            }
            case 37: {
                return 24;
            }
            case 38: {
                return 21;
            }
            case 40: {
                return 2;
            }
            case 41: {
                return 7;
            }
            case 42: {
                return 18;
            }
            case 45: {
                return 17;
            }
            case 50: {
                return 15;
            }
            case 56: {
                return 11;
            }
            case 208: {
                return 14;
            }
            case 216: {
                return 12;
            }
            case 250: {
                return 26;
            }
            case 251: {
                return 27;
            }
            case 256: {
                return 28;
            }
            case 512: {
                return 29;
            }
            case 768: {
                return 30;
            }
            default: {
                return 0;
            }
        }
    }

}
