/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dx/instruction;

import com.tencent.tinker.android.utils.SparseIntArray;
import com.tencent.tinker.android.dex.DexException;

// class: com/tencent/tinker/android/dx/instruction/InstructionPromoter
public final class InstructionPromoter {
    final private SparseIntArray addressMap;
    private int currentPromotedAddress;

    public InstructionPromoter() {
        super(null);
        this.addressMap = new SparseIntArray();
        this.currentPromotedAddress = 0;
    }

    private void mapAddressIfNeeded(int currentAddress) {
        if (currentAddress != this.currentPromotedAddress) {
            this.addressMap.append(currentAddress, this.currentPromotedAddress);
        }
    }

    public int getPromotedAddress(int currentAddress) {
        int index = this.addressMap.indexOfKey(currentAddress);
        if (index < 0) {
            return currentAddress;
        }
        else {
            return this.addressMap.valueAt(index);
        }
    }

    public int getPromotedAddressCount() {
        return this.addressMap.size();
    }

    public void visitZeroRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal) {
v_48 = this;
this.mapAddressIfNeeded(currentAddress);
        switch(opcode) {
            case -1: {
                this.currentPromotedAddress = this.currentPromotedAddress + 1;
                return;
            }
            int relativeTarget;
            case 40: {
                relativeTarget = InstructionCodec.getTarget(target, this.currentPromotedAddress);
                if (relativeTarget != (byte)relativeTarget) {
                    this.currentPromotedAddress = relativeTarget != (short)relativeTarget ? this.currentPromotedAddress + 2 : this.currentPromotedAddress + 3;
                }
                else {
                    this.currentPromotedAddress = this.currentPromotedAddress + 1;
                }
            }
            case 41: {
                relativeTarget = InstructionCodec.getTarget(target, this.currentPromotedAddress);
                this.currentPromotedAddress = relativeTarget != (short)relativeTarget ? this.currentPromotedAddress + 2 : this.currentPromotedAddress + 3;
                return;
            }
            case 42: {
                this.currentPromotedAddress = this.currentPromotedAddress + 3;
                return;
            }
            case 36: {
                this.currentPromotedAddress = this.currentPromotedAddress + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(Hex.u2or4(opcode)).toString());
            }
        }
    }

    public void visitOneRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal) {
v_29 = this;
this.mapAddressIfNeeded(currentAddress);
        switch(opcode) {
            case 26: {
                this.currentPromotedAddress = index > 65535 ? this.currentPromotedAddress + 2 : this.currentPromotedAddress + 3;
                return;
            }
            case 27: {
                this.currentPromotedAddress = this.currentPromotedAddress + 3;
                return;
            }
            case 10: {
                this.currentPromotedAddress = this.currentPromotedAddress + 1;
                return;
            }
            case 19: {
                this.currentPromotedAddress = this.currentPromotedAddress + 2;
                return;
            }
            case 20: {
                this.currentPromotedAddress = this.currentPromotedAddress + 3;
                return;
            }
            case 24: {
                this.currentPromotedAddress = this.currentPromotedAddress + 5;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(Hex.u2or4(opcode)).toString());
            }
        }
    }

    public void visitTwoRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
this.mapAddressIfNeeded(currentAddress);
        switch(opcode) {
            case 1: {
                this.currentPromotedAddress = this.currentPromotedAddress + 1;
                return;
            }
            case 2: {
                this.currentPromotedAddress = this.currentPromotedAddress + 2;
                return;
            }
            case 32: {
                this.currentPromotedAddress = this.currentPromotedAddress + 2;
                return;
            }
            case 3: {
                this.currentPromotedAddress = this.currentPromotedAddress + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(Hex.u2or4(opcode)).toString());
            }
        }
    }

    public void visitThreeRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b) {
this.mapAddressIfNeeded(currentAddress);
        switch(opcode) {
            case 45: {
                this.currentPromotedAddress = this.currentPromotedAddress + 2;
                return;
            }
            case 36: {
                this.currentPromotedAddress = this.currentPromotedAddress + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(Hex.u2or4(opcode)).toString());
            }
        }
    }

    public void visitFourRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c) {
this.mapAddressIfNeeded(currentAddress);
        switch(opcode) {
            case 36: {
                this.currentPromotedAddress = this.currentPromotedAddress + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(Hex.u2or4(opcode)).toString());
            }
        }
    }

    public void visitFiveRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c, int d) {
this.mapAddressIfNeeded(currentAddress);
        switch(opcode) {
            case 36: {
                this.currentPromotedAddress = this.currentPromotedAddress + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(Hex.u2or4(opcode)).toString());
            }
        }
    }

    public void visitRegisterRangeInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
this.mapAddressIfNeeded(currentAddress);
        switch(opcode) {
            case 37: {
                this.currentPromotedAddress = this.currentPromotedAddress + 3;
                return;
            }
            default: {
                throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(Hex.u2or4(opcode)).toString());
            }
        }
    }

    public void visitInvokePolymorphicInstruction(int currentAddress, int opcode, int methodIndex, int indexType, int protoIndex, int[] registers) {
        if (opcode == 250) {
            this.currentPromotedAddress = this.currentPromotedAddress + 4;
            return;
        }
        else {
            throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(Hex.u2or4(opcode)).toString());
        }
    }

    public void visitInvokePolymorphicRangeInstruction(int currentAddress, int opcode, int methodIndex, int indexType, int c, int registerCount, int protoIndex) {
        if (opcode == 251) {
            this.currentPromotedAddress = this.currentPromotedAddress + 4;
            return;
        }
        else {
            throw new IllegalStateException(new StringBuilder().append("unexpected opcode: ").append(Hex.u2or4(opcode)).toString());
        }
    }

    public void visitSparseSwitchPayloadInsn(int currentAddress, int opcode, int[] keys, int[] targets) {
        this.mapAddressIfNeeded(currentAddress);
        this.currentPromotedAddress = this.currentPromotedAddress + 2;
        this.currentPromotedAddress = this.currentPromotedAddress + keys.length * 2;
        this.currentPromotedAddress = this.currentPromotedAddress + targets.length * 2;
    }

    public void visitPackedSwitchPayloadInsn(int currentAddress, int opcode, int firstKey, int[] targets) {
        this.mapAddressIfNeeded(currentAddress);
        this.currentPromotedAddress = this.currentPromotedAddress + 4;
        this.currentPromotedAddress = this.currentPromotedAddress + targets.length * 2;
    }

    public void visitFillArrayDataPayloadInsn(int currentAddress, int opcode, Object data, int size, int elementWidth) {
this.mapAddressIfNeeded(currentAddress);
this.currentPromotedAddress = this.currentPromotedAddress + 4;
        switch(elementWidth) {
            case 1: {
                this.currentPromotedAddress = this.currentPromotedAddress + (byte[])data.length >> 1 + (byte[])data.length & 1;
                return;
            }
            case 2: {
                this.currentPromotedAddress = this.currentPromotedAddress + (short[])data.length * 1;
                return;
            }
            case 4: {
                this.currentPromotedAddress = this.currentPromotedAddress + (int[])data.length * 2;
                return;
            }
            case 8: {
                this.currentPromotedAddress = this.currentPromotedAddress + (long[])data.length * 4;
                return;
            }
            default: {
                throw new DexException(new StringBuilder().append("bogus element_width: ").append(Hex.u2(elementWidth)).toString());
            }
        }
    }

}
