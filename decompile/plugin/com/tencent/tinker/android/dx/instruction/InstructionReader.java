/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dx/instruction;


// class: com/tencent/tinker/android/dx/instruction/InstructionReader
public final class InstructionReader {
    final private ShortArrayCodeInput codeIn;

    public InstructionReader(ShortArrayCodeInput in) {
        super();
        this.codeIn = in;
    }

    public void accept(InstructionVisitor iv) {
        InstructionCodec.decode(this.codeIn, iv);
    }

}
