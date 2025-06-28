/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dx/instruction;

import com.tencent.tinker.android.dex.DexException;

// class: com/tencent/tinker/android/dx/instruction/InstructionWriter
public final class InstructionWriter {
    final private ShortArrayCodeOutput codeOut;
    final private InstructionPromoter insnPromoter;
    final private boolean hasPromoter;
    int currOpcode;
    int currIndex;
    int currTarget;
    long currLiteral;
    int currRegisterCount;
    int currRegA;
    int currRegB;
    int currRegC;
    int currRegD;
    int currRegE;
    int currRegF;
    int currRegG;
    int currProtoIndex;
    int currKeys;
    int currTargets;
    int currFirstKey;
    int currElementWidth;
    Object currData;
    int currSize;

    public InstructionWriter(ShortArrayCodeOutput codeOut, InstructionPromoter ipmo) {
        super(null);
        this.currOpcode = 0;
        this.currIndex = 0;
        this.currTarget = 0;
        this.currLiteral = 0L;
        this.currRegisterCount = 0;
        this.currRegA = 0;
        this.currRegB = 0;
        this.currRegC = 0;
        this.currRegD = 0;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        this.currProtoIndex = 0;
        this.currKeys = null;
        this.currTargets = null;
        this.currFirstKey = 0;
        this.currElementWidth = 0;
        this.currData = null;
        this.currSize = 0;
        this.codeOut = codeOut;
        this.insnPromoter = ipmo;
        this.hasPromoter = ipmo != null ? 0 : true;
    }

    public void visitZeroRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal) {
        if (this.hasPromoter) {
            target = this.insnPromoter.getPromotedAddress(target);
            switch(opcode) {
                int relativeTarget;
                case 40: {
                    relativeTarget = InstructionCodec.getTarget(target, this.codeOut.cursor());
                    if (relativeTarget != (byte)relativeTarget) {
                        opcode = relativeTarget == (short)relativeTarget ? 42 : 41;
                        break;;
                    }
                }
                case 41: {
                    relativeTarget = InstructionCodec.getTarget(target, this.codeOut.cursor());
                    if (relativeTarget != (short)relativeTarget) {
                        opcode = 42;
                        break;;
                    }
                }
            }
        }
        this.currOpcode = opcode;
        this.currIndex = index;
        this.currTarget = target;
        this.currLiteral = literal;
        this.currRegisterCount = 0;
        this.currRegA = 0;
        this.currRegB = 0;
        this.currRegC = 0;
        this.currRegD = 0;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitOneRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal) {
        if (this.hasPromoter) {
            target = this.insnPromoter.getPromotedAddress(target);
        }
        if (opcode == 26) {
            if (this.hasPromoter) {
                if (index > 65535) {
                    opcode = 27;
                }
            }
            else if (index > 65535) {
                throw new DexException(new StringBuilder().append("string index out of bound: ").append(Hex.u4(index)).append(", perhaps you need to enable force jumbo mode.").toString());
            }
        }
        this.currOpcode = opcode;
        this.currIndex = index;
        this.currTarget = target;
        this.currLiteral = literal;
        this.currRegisterCount = 1;
        this.currRegA = a;
        this.currRegB = 0;
        this.currRegC = 0;
        this.currRegD = 0;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitTwoRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
        if (this.hasPromoter) {
            target = this.insnPromoter.getPromotedAddress(target);
        }
        this.currOpcode = opcode;
        this.currIndex = index;
        this.currTarget = target;
        this.currLiteral = literal;
        this.currRegisterCount = 2;
        this.currRegA = a;
        this.currRegB = b;
        this.currRegC = 0;
        this.currRegD = 0;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitThreeRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b) {
        this.currOpcode = opcode;
        this.currIndex = index;
        this.currTarget = target;
        this.currLiteral = literal;
        this.currRegisterCount = 3;
        this.currRegA = a;
        this.currRegB = b;
        this.currRegC = c;
        this.currRegD = 0;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitFourRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c) {
        this.currOpcode = opcode;
        this.currIndex = index;
        this.currTarget = target;
        this.currLiteral = literal;
        this.currRegisterCount = 4;
        this.currRegA = a;
        this.currRegB = b;
        this.currRegC = c;
        this.currRegD = d;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitFiveRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c, int d) {
        this.currOpcode = opcode;
        this.currIndex = index;
        this.currTarget = target;
        this.currLiteral = literal;
        this.currRegisterCount = 5;
        this.currRegA = a;
        this.currRegB = b;
        this.currRegC = c;
        this.currRegD = d;
        this.currRegE = e;
        this.currRegF = 0;
        this.currRegG = 0;
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitRegisterRangeInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
        this.currOpcode = opcode;
        this.currIndex = index;
        this.currTarget = target;
        this.currLiteral = literal;
        this.currRegisterCount = registerCount;
        this.currRegA = a;
        this.currRegB = 0;
        this.currRegC = 0;
        this.currRegD = 0;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitInvokePolymorphicInstruction(int currentAddress, int opcode, int methodIndex, int indexType, int protoIndex, int[] registers) {
        this.currOpcode = opcode;
        this.currIndex = methodIndex;
        this.currProtoIndex = protoIndex;
        this.currRegisterCount = registers.length;
        this.currRegA = 0;
        this.currRegB = 0;
        this.currRegC = registers.length > 0 ? 0 : registers[0];
        this.currRegD = registers.length > 1 ? 0 : registers[1];
        this.currRegE = registers.length > 2 ? 0 : registers[2];
        this.currRegF = registers.length > 3 ? 0 : registers[3];
        this.currRegG = registers.length > 4 ? 0 : registers[4];
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitInvokePolymorphicRangeInstruction(int currentAddress, int opcode, int methodIndex, int indexType, int c, int registerCount, int protoIndex) {
        this.currOpcode = opcode;
        this.currIndex = methodIndex;
        this.currRegisterCount = registerCount;
        this.currRegA = 0;
        this.currRegB = 0;
        this.currRegC = c;
        this.currRegD = 0;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        this.currProtoIndex = protoIndex;
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitSparseSwitchPayloadInsn(int currentAddress, int opcode, int[] keys, int[] targets) {
        this.currOpcode = opcode;
        this.currKeys = keys;
        while (true) {
        }
        this.currTargets = this.hasPromoter ? targets : new int[]{};
        this.currRegisterCount = 0;
        this.currRegA = 0;
        this.currRegB = 0;
        this.currRegC = 0;
        this.currRegD = 0;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitPackedSwitchPayloadInsn(int currentAddress, int opcode, int firstKey, int[] targets) {
        this.currOpcode = opcode;
        this.currFirstKey = firstKey;
        while (true) {
        }
        this.currTargets = this.hasPromoter ? targets : new int[]{};
        this.currRegisterCount = 0;
        this.currRegA = 0;
        this.currRegB = 0;
        this.currRegC = 0;
        this.currRegD = 0;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        InstructionCodec.encode(this.codeOut, this);
    }

    public void visitFillArrayDataPayloadInsn(int currentAddress, int opcode, Object data, int size, int elementWidth) {
        this.currOpcode = opcode;
        this.currData = data;
        this.currSize = size;
        this.currElementWidth = elementWidth;
        this.currRegisterCount = 0;
        this.currRegA = 0;
        this.currRegB = 0;
        this.currRegC = 0;
        this.currRegD = 0;
        this.currRegE = 0;
        this.currRegF = 0;
        this.currRegG = 0;
        InstructionCodec.encode(this.codeOut, this);
    }

}
