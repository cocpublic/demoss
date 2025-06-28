/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/util;

import com.tencent.tinker.android.dx.instruction.ShortArrayCodeOutput;
import com.tencent.tinker.android.dx.instruction.InstructionPromoter;
import com.tencent.tinker.android.dx.instruction.InstructionWriter;
import com.tencent.tinker.android.dx.instruction.InstructionReader;
import com.tencent.tinker.android.dx.instruction.ShortArrayCodeInput;
import com.tencent.tinker.android.dex.DexException;

// class: com/tencent/tinker/commons/dexpatcher/util/InstructionTransformer
public final class InstructionTransformer {
    final private AbstractIndexMap indexMap;

    public InstructionTransformer(AbstractIndexMap indexMap) {
        super();
        this.indexMap = indexMap;
    }

    public short[] transform(short[] encodedInstructions) {
        ShortArrayCodeOutput out = new ShortArrayCodeOutput(encodedInstructions.length);
        InstructionPromoter ipmo = new InstructionPromoter();
        InstructionWriter iw = new InstructionWriter(out, ipmo);
        InstructionReader ir = new InstructionReader(new ShortArrayCodeInput(encodedInstructions));
        try {
            ir.accept(new InstructionTransformer$InstructionTransformVisitor(this, ipmo));
            ir.accept(new InstructionTransformer$InstructionTransformVisitor(this, iw));
        }
        catch (EOFException e) {
            throw new DexException(e);
        }
        return out.getArray();
    }

    static /* synthetic */ AbstractIndexMap access$000(InstructionTransformer x0) {
        return x0.indexMap;
    }

    // class: com/tencent/tinker/commons/dexpatcher/util/InstructionTransformer$InstructionTransformVisitor
    final class InstructionTransformer$InstructionTransformVisitor {
        final synthetic InstructionTransformer this$0;

         InstructionTransformer$InstructionTransformVisitor(InstructionTransformer transformer, InstructionVisitor iv) {
            this.this$0 = transformer;
            super(iv);
        }

        public void visitZeroRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitZeroRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal);
        }

        public void visitOneRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitOneRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a);
        }

        public void visitTwoRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitTwoRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a, b);
        }

        public void visitThreeRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitThreeRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a, b, c);
        }

        public void visitFourRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitFourRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a, b, c, d);
        }

        public void visitFiveRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c, int d) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitFiveRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a, b, c, d, e);
        }

        public void visitRegisterRangeInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitRegisterRangeInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a, registerCount);
        }

        public void visitInvokePolymorphicInstruction(int currentAddress, int opcode, int methodIndex, int indexType, int protoIndex, int[] registers) {
            methodIndex = InstructionTransformer.access$000(this.this$0).adjustMethodIdIndex(methodIndex);
            protoIndex = InstructionTransformer.access$000(this.this$0).adjustProtoIdIndex(protoIndex);
            super.visitInvokePolymorphicInstruction(currentAddress, opcode, methodIndex, indexType, protoIndex, registers);
        }

        public void visitInvokePolymorphicRangeInstruction(int currentAddress, int opcode, int methodIndex, int indexType, int c, int registerCount, int protoIndex) {
            methodIndex = InstructionTransformer.access$000(this.this$0).adjustMethodIdIndex(methodIndex);
            protoIndex = InstructionTransformer.access$000(this.this$0).adjustProtoIdIndex(protoIndex);
            super.visitInvokePolymorphicRangeInstruction(currentAddress, opcode, methodIndex, indexType, c, registerCount, protoIndex);
        }

        private int transformIndexIfNeeded(int index, int indexType) {
            switch(indexType) {
                case 3: {
                    return InstructionTransformer.access$000(this.this$0).adjustStringIndex(index);
                }
                case 2: {
                    return InstructionTransformer.access$000(this.this$0).adjustTypeIdIndex(index);
                }
                case 5: {
                    return InstructionTransformer.access$000(this.this$0).adjustFieldIdIndex(index);
                }
                case 9: {
                    return InstructionTransformer.access$000(this.this$0).adjustProtoIdIndex(index);
                }
                case 4: {
                    return InstructionTransformer.access$000(this.this$0).adjustMethodIdIndex(index);
                }
                case 8: {
                    return InstructionTransformer.access$000(this.this$0).adjustMethodHandleIndex(index);
                }
                case 7: {
                    return InstructionTransformer.access$000(this.this$0).adjustCallSiteIdIndex(index);
                }
                case 6: {
                    throw new IllegalArgumentException("METHOD_AND_PROTO_REF should not use this method to do transform.");
                }
                default: {
                    return index;
                }
            }
        }

    }
    // class: com/tencent/tinker/commons/dexpatcher/util/InstructionTransformer$InstructionTransformVisitor
    final class InstructionTransformer$InstructionTransformVisitor {
        final synthetic InstructionTransformer this$0;

         InstructionTransformer$InstructionTransformVisitor(InstructionTransformer transformer, InstructionVisitor iv) {
            this.this$0 = transformer;
            super(iv);
        }

        public void visitZeroRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitZeroRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal);
        }

        public void visitOneRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitOneRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a);
        }

        public void visitTwoRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitTwoRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a, b);
        }

        public void visitThreeRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitThreeRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a, b, c);
        }

        public void visitFourRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitFourRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a, b, c, d);
        }

        public void visitFiveRegisterInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a, int b, int c, int d) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitFiveRegisterInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a, b, c, d, e);
        }

        public void visitRegisterRangeInsn(int currentAddress, int opcode, int index, int indexType, int target, long literal, int literal, int a) {
            int mappedIndex = this.transformIndexIfNeeded(index, indexType);
            super.visitRegisterRangeInsn(currentAddress, opcode, mappedIndex, indexType, target, literal, a, registerCount);
        }

        public void visitInvokePolymorphicInstruction(int currentAddress, int opcode, int methodIndex, int indexType, int protoIndex, int[] registers) {
            methodIndex = InstructionTransformer.access$000(this.this$0).adjustMethodIdIndex(methodIndex);
            protoIndex = InstructionTransformer.access$000(this.this$0).adjustProtoIdIndex(protoIndex);
            super.visitInvokePolymorphicInstruction(currentAddress, opcode, methodIndex, indexType, protoIndex, registers);
        }

        public void visitInvokePolymorphicRangeInstruction(int currentAddress, int opcode, int methodIndex, int indexType, int c, int registerCount, int protoIndex) {
            methodIndex = InstructionTransformer.access$000(this.this$0).adjustMethodIdIndex(methodIndex);
            protoIndex = InstructionTransformer.access$000(this.this$0).adjustProtoIdIndex(protoIndex);
            super.visitInvokePolymorphicRangeInstruction(currentAddress, opcode, methodIndex, indexType, c, registerCount, protoIndex);
        }

        private int transformIndexIfNeeded(int index, int indexType) {
            switch(indexType) {
                case 3: {
                    return InstructionTransformer.access$000(this.this$0).adjustStringIndex(index);
                }
                case 2: {
                    return InstructionTransformer.access$000(this.this$0).adjustTypeIdIndex(index);
                }
                case 5: {
                    return InstructionTransformer.access$000(this.this$0).adjustFieldIdIndex(index);
                }
                case 9: {
                    return InstructionTransformer.access$000(this.this$0).adjustProtoIdIndex(index);
                }
                case 4: {
                    return InstructionTransformer.access$000(this.this$0).adjustMethodIdIndex(index);
                }
                case 8: {
                    return InstructionTransformer.access$000(this.this$0).adjustMethodHandleIndex(index);
                }
                case 7: {
                    return InstructionTransformer.access$000(this.this$0).adjustCallSiteIdIndex(index);
                }
                case 6: {
                    throw new IllegalArgumentException("METHOD_AND_PROTO_REF should not use this method to do transform.");
                }
                default: {
                    return index;
                }
            }
        }

    }
}
