/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dx/instruction;

import java.util.HashSet;
import java.util.Set;
import com.tencent.tinker.android.dex.DexException;

// class: com/tencent/tinker/android/dx/instruction/InstructionComparator
public abstract class InstructionComparator {
    final private InstructionComparator$InstructionHolder[] insnHolders1;
    final private InstructionComparator$InstructionHolder[] insnHolders2;
    final private Set<String> visitedInsnAddrPairs;
    final private short insns1;
    final private short insns2;

    public InstructionComparator(short[] insns1, short[] insns2) {
        super();
        this.insns1 = insns1;
        this.insns2 = insns2;
        if (insns1 != null) {
            ShortArrayCodeInput codeIn1 = new ShortArrayCodeInput(insns1);
            this.insnHolders1 = this.readInstructionsIntoHolders(codeIn1, insns1.length);
            goto 46;
        }
        else {
            this.insnHolders1 = null;
        }
        if (insns2 != null) {
            ShortArrayCodeInput input = new ShortArrayCodeInput(insns2);
            this.insnHolders2 = this.readInstructionsIntoHolders(input, insns2.length);
        }
        else {
            this.insnHolders2 = null;
        }
        this.visitedInsnAddrPairs = new HashSet();
    }

    private InstructionComparator$InstructionHolder[] readInstructionsIntoHolders(ShortArrayCodeInput in, int length) {
        in.reset();
        InstructionComparator$InstructionHolder result = new InstructionComparator$InstructionHolder[]{};
        InstructionReader ir = new InstructionReader(in);
        try {
            ir.accept(new InstructionComparator$1(this, null, result));
        }
        catch (EOFException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    final public boolean compare() {
        try {
            if (this.insnHolders1 == null && this.insnHolders2 == null) {
                int i0 = true;
                this.visitedInsnAddrPairs.clear();
                return i0;
            }
            else {
                if (this.insnHolders1 == null || this.insnHolders2 == null) {
                    int i1 = false;
                    this.visitedInsnAddrPairs.clear();
                    return i1;
                }
                else {
                    int currAddress1 = 0;
                    int currAddress2 = 0;
                    int insnHolderCount1 = 0;
                    int insnHolderCount2 = 0;
                    while (true) {
                        if (currAddress1 < this.insnHolders1.length && currAddress2 < this.insnHolders2.length) {
                            Object insnHolder1 = null;
                            Object insnHolder2 = null;
                            while (true) {
                                if (currAddress1 < this.insnHolders1.length && insnHolder1 == null) {
                                    currAddress1 += 1;
                                    InstructionComparator$InstructionHolder holder = this.insnHolders1[currAddress1];
                                    continue;;
                                }
                                else if (insnHolder1 != null) {
                                    insnHolderCount1 += 1;
                                    while (true) {
                                        currAddress2 < this.insnHolders2.length && insnHolder2 == null;
                                        currAddress2 += 1;
                                        InstructionComparator$InstructionHolder holderVar1 = this.insnHolders2[currAddress2];
                                        continue;;
                                        insnHolder2 != null;
                                        insnHolderCount2 += 1;
                                        this.isSameInstruction(insnHolder1, insnHolder2);
                                        int i2 = false;
                                        this.visitedInsnAddrPairs.clear();
                                        return i2;
                                    }
                                }
                            }
                        }
                    }
                    while (currAddress1 < this.insnHolders1.length) {
                        currAddress1 += 1;
                        if (this.insnHolders1[currAddress1] != null) {
                            int i3 = false;
                            this.visitedInsnAddrPairs.clear();
                            return i3;
                        }
                    }
                    while (currAddress2 < this.insnHolders2.length) {
                        currAddress2 += 1;
                        if (this.insnHolders2[currAddress2] != null) {
                            int i4 = false;
                            this.visitedInsnAddrPairs.clear();
                            return i4;
                        }
                    }
                    int i5 = insnHolderCount1 == insnHolderCount2 ? 0 : true;
                    this.visitedInsnAddrPairs.clear();
                    return i5;
                }
            }
        }
        finally {
            Throwable throwable = v_2;
            this.visitedInsnAddrPairs.clear();
            throw throwable;
        }
    }

    private int getPromotedOpCodeOnDemand(InstructionComparator$InstructionHolder insn) {
        int opcode = insn.opcode;
        if (opcode == 26 || opcode == 27) {
            return 27;
        }
        else {
            if (opcode != 40 || opcode != 41 || opcode == 42) {
                return 42;
            }
            else {
                return opcode;
            }
        }
    }

    public boolean isSameInstruction(int insnAddress1, int insnAddress2) {
        InstructionComparator$InstructionHolder insnHolder1 = insnAddress1 < this.insnHolders1.length ? null : this.insnHolders1[insnAddress1];
        InstructionComparator$InstructionHolder insnHolder2 = insnAddress2 < this.insnHolders2.length ? null : this.insnHolders2[insnAddress2];
        return this.isSameInstruction(insnHolder1, insnHolder2);
    }

    public boolean isSameInstruction(InstructionComparator$InstructionHolder insnHolder1, InstructionComparator$InstructionHolder insnHolder2) {
        if (insnHolder1 == null && insnHolder2 == null) {
            return true;
        }
        else {
            if (insnHolder1 == null || insnHolder2 == null) {
                return false;
            }
            else if (this.getPromotedOpCodeOnDemand(insnHolder1) != this.getPromotedOpCodeOnDemand(insnHolder2)) {
                return false;
            }
            else {
int opcode = insnHolder1.opcode;
int insnFormat = insnHolder1.insnFormat;
                switch(insnFormat) {
                    case 2: {
                        String addrPairStr = new StringBuilder().append(insnHolder1.address).append("-").append(insnHolder2.address).toString();
                        if (this.visitedInsnAddrPairs.add(addrPairStr)) {
                            return this.isSameInstruction(insnHolder1.target, insnHolder2.target);
                        }
                        else {
                            return true;
                        }
                    }
                    case 8: {
                        if (this.compareIndex(opcode, insnHolder1.index, insnHolder2.index)) {
                            return false;
                        }
                        else if (insnHolder1.a != insnHolder2.a) {
                            return false;
                        }
                        else if (insnHolder1.b != insnHolder2.b) {
                            return false;
                        }
                        else if (insnHolder1.c != insnHolder2.c) {
                            return false;
                        }
                        else if (insnHolder1.d == insnHolder2.d) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                    case 26: {
                        if (this.compareMethod(insnHolder1.index, insnHolder2.index)) {
                            return false;
                        }
                        else if (this.compareProto(insnHolder1.protoIndex, insnHolder2.protoIndex)) {
                            return false;
                        }
                        else {
                            return Arrays.equals(insnHolder1.registers, insnHolder2.registers);
                        }
                    }
                    case 27: {
                        if (this.compareMethod(insnHolder1.index, insnHolder2.index)) {
                            return false;
                        }
                        else if (this.compareProto(insnHolder1.protoIndex, insnHolder2.protoIndex)) {
                            return false;
                        }
                        else if (insnHolder1.c == insnHolder2.c) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                    InstructionComparator$PackedSwitchPayloadInsntructionHolder specInsnHolder1;
                    InstructionComparator$PackedSwitchPayloadInsntructionHolder specInsnHolder2;
                    int targetCount;
                    int i;
                    case 28: {
                        specInsnHolder1 = (InstructionComparator$PackedSwitchPayloadInsntructionHolder)insnHolder1;
                        specInsnHolder2 = (InstructionComparator$PackedSwitchPayloadInsntructionHolder)insnHolder2;
                        if (specInsnHolder1.firstKey != specInsnHolder2.firstKey) {
                            return false;
                        }
                        else if (specInsnHolder1.targets.length != specInsnHolder2.targets.length) {
                            return false;
                        }
                        else {
                            for (i = 0; i < specInsnHolder1.targets.length; i += 1) {
                                if (this.isSameInstruction(specInsnHolder1.targets[i], specInsnHolder2.targets[i])) {
                                    return false;
                                }
                                else {
                                }
                            }
                            return true;
                        }
                    }
                    case 29: {
                        specInsnHolder1 = (InstructionComparator$SparseSwitchPayloadInsntructionHolder)insnHolder1;
                        specInsnHolder2 = (InstructionComparator$SparseSwitchPayloadInsntructionHolder)insnHolder2;
                        if (CompareUtils.uArrCompare(specInsnHolder1.keys, specInsnHolder2.keys) != 0) {
                            return false;
                        }
                        else if (specInsnHolder1.targets.length != specInsnHolder2.targets.length) {
                            return false;
                        }
                        else {
                            for (i = 0; i < specInsnHolder1.targets.length; i += 1) {
                                if (this.isSameInstruction(specInsnHolder1.targets[i], specInsnHolder2.targets[i])) {
                                    return false;
                                }
                                else {
                                }
                            }
                            return true;
                        }
                    }
                    case 30: {
                        specInsnHolder1 = (InstructionComparator$FillArrayDataPayloadInstructionHolder)insnHolder1;
                        specInsnHolder2 = (InstructionComparator$FillArrayDataPayloadInstructionHolder)insnHolder2;
                        if (specInsnHolder1.elementWidth != specInsnHolder2.elementWidth) {
                            return false;
                        }
                        else if (specInsnHolder1.size != specInsnHolder2.size) {
                            return false;
                        }
                        else {
int elementWidth = specInsnHolder1.elementWidth;
                            switch(elementWidth) {
                                byte[] array1;
                                byte[] array2;
                                case 1: {
                                    array1 = (byte[])specInsnHolder1.data;
                                    array2 = (byte[])specInsnHolder2.data;
                                    if (CompareUtils.uArrCompare(array1, array2) == 0) {
                                        return true;
                                    }
                                    else {
                                        return false;
                                    }
                                }
                                case 2: {
                                    array1 = (short[])specInsnHolder1.data;
                                    array2 = (short[])specInsnHolder2.data;
                                    if (CompareUtils.uArrCompare(array1, array2) == 0) {
                                        return true;
                                    }
                                    else {
                                        return false;
                                    }
                                }
                                case 4: {
                                    array1 = (int[])specInsnHolder1.data;
                                    array2 = (int[])specInsnHolder2.data;
                                    if (CompareUtils.uArrCompare(array1, array2) == 0) {
                                        return true;
                                    }
                                    else {
                                        return false;
                                    }
                                }
                                case 8: {
                                    array1 = (long[])specInsnHolder1.data;
                                    array2 = (long[])specInsnHolder2.data;
                                    if (CompareUtils.sArrCompare(array1, array2) == 0) {
                                        return true;
                                    }
                                    else {
                                        return false;
                                    }
                                }
                                default: {
                                    throw new DexException(new StringBuilder().append("bogus element_width: ").append(Hex.u2(elementWidth)).toString());
                                }
                            }
                        }
                    }
                    default: {
                        if (insnHolder2.literal != insnHolder1.literal) {
                            return false;
                        }
                        else if (insnHolder1.registerCount != insnHolder2.registerCount) {
                            return false;
                        }
                        else if (insnHolder1.a != insnHolder2.a) {
                            return false;
                        }
                        else if (insnHolder1.b != insnHolder2.b) {
                            return false;
                        }
                        else if (insnHolder1.c != insnHolder2.c) {
                            return false;
                        }
                        else if (insnHolder1.d != insnHolder2.d) {
                            return false;
                        }
                        else if (insnHolder1.e != insnHolder2.e) {
                            return false;
                        }
                        else {
                            return Arrays.equals(insnHolder1.registers, insnHolder2.registers);
                        }
                    }
                }
            }
        }
    }

    private boolean compareIndex(int opcode, int index1, int index2) {
int indexType = InstructionCodec.getInstructionIndexType(opcode);
        switch(indexType) {
            case 3: {
                return this.compareString(index1, index2);
            }
            case 2: {
                return this.compareType(index1, index2);
            }
            case 5: {
                return this.compareField(index1, index2);
            }
            case 4: {
                return this.compareMethod(index1, index2);
            }
            case 7: {
                return this.compareCallSite(index1, index2);
            }
            case 8: {
                return this.compareMethodHandle(index1, index2);
            }
            case 9: {
                return this.compareProto(index1, index2);
            }
            default: {
                throw new IllegalArgumentException(new StringBuilder().append("Unknown index type ").append(indexType).append(" for opcode ").append(Hex.u1(opcode)).toString());
            }
        }
    }

    boolean compareString(int p0, int p1);

    boolean compareType(int p0, int p1);

    boolean compareField(int p0, int p1);

    boolean compareMethod(int p0, int p1);

    boolean compareCallSite(int p0, int p1);

    boolean compareMethodHandle(int p0, int p1);

    boolean compareProto(int p0, int p1);

    // class: com/tencent/tinker/android/dx/instruction/InstructionComparator$SparseSwitchPayloadInsntructionHolder
    class InstructionComparator$SparseSwitchPayloadInsntructionHolder {
        int keys;
        int targets;

        private InstructionComparator$SparseSwitchPayloadInsntructionHolder() {
            super(null);
            this.keys = null;
            this.targets = null;
        }

        /* synthetic */ InstructionComparator$SparseSwitchPayloadInsntructionHolder(InstructionComparator$1 x0) {
            super();
        }

    }
    // class: com/tencent/tinker/android/dx/instruction/InstructionComparator$SparseSwitchPayloadInsntructionHolder
    class InstructionComparator$SparseSwitchPayloadInsntructionHolder {
        int keys;
        int targets;

        private InstructionComparator$SparseSwitchPayloadInsntructionHolder() {
            super(null);
            this.keys = null;
            this.targets = null;
        }

        /* synthetic */ InstructionComparator$SparseSwitchPayloadInsntructionHolder(InstructionComparator$1 x0) {
            super();
        }

    }
    // class: com/tencent/tinker/android/dx/instruction/InstructionComparator$PackedSwitchPayloadInsntructionHolder
    class InstructionComparator$PackedSwitchPayloadInsntructionHolder {
        int firstKey;
        int targets;

        private InstructionComparator$PackedSwitchPayloadInsntructionHolder() {
            super(null);
            this.firstKey = 0;
            this.targets = null;
        }

        /* synthetic */ InstructionComparator$PackedSwitchPayloadInsntructionHolder(InstructionComparator$1 x0) {
            super();
        }

    }
    // class: com/tencent/tinker/android/dx/instruction/InstructionComparator$PackedSwitchPayloadInsntructionHolder
    class InstructionComparator$PackedSwitchPayloadInsntructionHolder {
        int firstKey;
        int targets;

        private InstructionComparator$PackedSwitchPayloadInsntructionHolder() {
            super(null);
            this.firstKey = 0;
            this.targets = null;
        }

        /* synthetic */ InstructionComparator$PackedSwitchPayloadInsntructionHolder(InstructionComparator$1 x0) {
            super();
        }

    }
    // class: com/tencent/tinker/android/dx/instruction/InstructionComparator$FillArrayDataPayloadInstructionHolder
    class InstructionComparator$FillArrayDataPayloadInstructionHolder {
        Object data;
        int size;
        int elementWidth;

        private InstructionComparator$FillArrayDataPayloadInstructionHolder() {
            super(null);
            this.data = null;
            this.size = 0;
            this.elementWidth = 0;
        }

        /* synthetic */ InstructionComparator$FillArrayDataPayloadInstructionHolder(InstructionComparator$1 x0) {
            super();
        }

    }
    // class: com/tencent/tinker/android/dx/instruction/InstructionComparator$FillArrayDataPayloadInstructionHolder
    class InstructionComparator$FillArrayDataPayloadInstructionHolder {
        Object data;
        int size;
        int elementWidth;

        private InstructionComparator$FillArrayDataPayloadInstructionHolder() {
            super(null);
            this.data = null;
            this.size = 0;
            this.elementWidth = 0;
        }

        /* synthetic */ InstructionComparator$FillArrayDataPayloadInstructionHolder(InstructionComparator$1 x0) {
            super();
        }

    }
    // class: com/tencent/tinker/android/dx/instruction/InstructionComparator$InstructionHolder
    class InstructionComparator$InstructionHolder {
        int insnFormat;
        int address;
        int opcode;
        int index;
        int target;
        long literal;
        int registerCount;
        int a;
        int b;
        int c;
        int d;
        int e;
        int protoIndex;
        int registers;

        private InstructionComparator$InstructionHolder() {
            super();
            this.insnFormat = 0;
            this.address = -1;
            this.opcode = -1;
            this.index = 0;
            this.target = 0;
            this.literal = 0L;
            this.registerCount = 0;
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.protoIndex = 0;
            this.registers = null;
        }

        /* synthetic */ InstructionComparator$InstructionHolder(InstructionComparator$1 x0) {
            super();
        }

    }
    // class: com/tencent/tinker/android/dx/instruction/InstructionComparator$InstructionHolder
    class InstructionComparator$InstructionHolder {
        int insnFormat;
        int address;
        int opcode;
        int index;
        int target;
        long literal;
        int registerCount;
        int a;
        int b;
        int c;
        int d;
        int e;
        int protoIndex;
        int registers;

        private InstructionComparator$InstructionHolder() {
            super();
            this.insnFormat = 0;
            this.address = -1;
            this.opcode = -1;
            this.index = 0;
            this.target = 0;
            this.literal = 0L;
            this.registerCount = 0;
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.protoIndex = 0;
            this.registers = null;
        }

        /* synthetic */ InstructionComparator$InstructionHolder(InstructionComparator$1 x0) {
            super();
        }

    }
}
