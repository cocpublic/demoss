/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/Code
public final class Code {
    public int registersSize;
    public int insSize;
    public int outsSize;
    public int debugInfoOffset;
    public short instructions;
    public Code$Try[] tries;
    public Code$CatchHandler[] catchHandlers;

    public Code(int off, int registersSize, int insSize, int outsSize, int debugInfoOffset, short[] instructions, Code$Try[] tries, Code$CatchHandler[] catchHandlers) {
        super(off);
        this.registersSize = registersSize;
        this.insSize = insSize;
        this.outsSize = outsSize;
        this.debugInfoOffset = debugInfoOffset;
        this.instructions = instructions;
        this.tries = tries;
        this.catchHandlers = catchHandlers;
    }

    public int compareTo(Code other) {
        int res = CompareUtils.sCompare(this.registersSize, other.registersSize);
        if (res != 0) {
            return res;
        }
        else {
            res = CompareUtils.sCompare(this.insSize, other.insSize);
            if (res != 0) {
                return res;
            }
            else {
                res = CompareUtils.sCompare(this.outsSize, other.outsSize);
                if (res != 0) {
                    return res;
                }
                else {
                    res = CompareUtils.sCompare(this.debugInfoOffset, other.debugInfoOffset);
                    if (res != 0) {
                        return res;
                    }
                    else {
                        res = CompareUtils.uArrCompare(this.instructions, other.instructions);
                        if (res != 0) {
                            return res;
                        }
                        else {
                            res = CompareUtils.aArrCompare(this.tries, other.tries);
                            if (res != 0) {
                                return res;
                            }
                            else {
                                return CompareUtils.aArrCompare(this.catchHandlers, other.catchHandlers);
                            }
                        }
                    }
                }
            }
        }
    }

    public int hashCode() {
        return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.registersSize), Integer.valueOf(this.insSize), Integer.valueOf(this.outsSize), Integer.valueOf(this.debugInfoOffset), this.instructions, this.tries, this.catchHandlers});
    }

    public boolean equals(Object obj) {
        if ((obj instanceof Code)) {
            return false;
        }
        else if (this.compareTo((Code)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        int res = 16 + this.instructions.length * 2;
        if (this.tries.length > 0) {
            if (this.instructions.length & 1 == 1) {
                res += 2;
            }
            res += this.tries.length * 8;
            res += Leb128.unsignedLeb128Size(this.catchHandlers.length);
            Code$CatchHandler handler = this.catchHandlers;
            for (int i1 = 0; i1 < handler.length; i1 += 1) {
                Code$CatchHandler catchHandler = handler[i1];
                res = catchHandler.catchAllAddress != -1 ? res + Leb128.signedLeb128Size(catchHandler.typeIndexes.length) : res + Leb128.signedLeb128Size(- catchHandler.typeIndexes.length) + Leb128.unsignedLeb128Size(catchHandler.catchAllAddress);
                for (int i = 0; i < catchHandler.typeIndexes.length; i += 1) {
                    res += Leb128.unsignedLeb128Size(catchHandler.typeIndexes[i]) + Leb128.unsignedLeb128Size(catchHandler.addresses[i]);
                }
            }
        }
        return res;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((Code)object);
    }

    // class: com/tencent/tinker/android/dex/Code$Try
    public class Code$Try implements Comparable<Code$Try> {
        public int startAddress;
        public int instructionCount;
        public int catchHandlerIndex;

        public Code$Try(int startAddress, int instructionCount, int catchHandlerIndex) {
            super();
            this.startAddress = startAddress;
            this.instructionCount = instructionCount;
            this.catchHandlerIndex = catchHandlerIndex;
        }

        public int compareTo(Code$Try other) {
            int res = CompareUtils.sCompare(this.startAddress, other.startAddress);
            if (res != 0) {
                return res;
            }
            else {
                res = CompareUtils.sCompare(this.instructionCount, other.instructionCount);
                if (res != 0) {
                    return res;
                }
                else {
                    return CompareUtils.sCompare(this.catchHandlerIndex, other.catchHandlerIndex);
                }
            }
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.compareTo((Code$Try)object);
        }

    }
    // class: com/tencent/tinker/android/dex/Code$Try
    public class Code$Try implements Comparable<Code$Try> {
        public int startAddress;
        public int instructionCount;
        public int catchHandlerIndex;

        public Code$Try(int startAddress, int instructionCount, int catchHandlerIndex) {
            super();
            this.startAddress = startAddress;
            this.instructionCount = instructionCount;
            this.catchHandlerIndex = catchHandlerIndex;
        }

        public int compareTo(Code$Try other) {
            int res = CompareUtils.sCompare(this.startAddress, other.startAddress);
            if (res != 0) {
                return res;
            }
            else {
                res = CompareUtils.sCompare(this.instructionCount, other.instructionCount);
                if (res != 0) {
                    return res;
                }
                else {
                    return CompareUtils.sCompare(this.catchHandlerIndex, other.catchHandlerIndex);
                }
            }
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.compareTo((Code$Try)object);
        }

    }
    // class: com/tencent/tinker/android/dex/Code$CatchHandler
    public class Code$CatchHandler implements Comparable<Code$CatchHandler> {
        public int typeIndexes;
        public int addresses;
        public int catchAllAddress;
        public int offset;

        public Code$CatchHandler(int[] typeIndexes, int[] addresses, int catchAllAddress, int offset) {
            super();
            this.typeIndexes = typeIndexes;
            this.addresses = addresses;
            this.catchAllAddress = catchAllAddress;
            this.offset = offset;
        }

        public int compareTo(Code$CatchHandler other) {
            int res = CompareUtils.sArrCompare(this.typeIndexes, other.typeIndexes);
            if (res != 0) {
                return res;
            }
            else {
                res = CompareUtils.sArrCompare(this.addresses, other.addresses);
                if (res != 0) {
                    return res;
                }
                else {
                    return CompareUtils.sCompare(this.catchAllAddress, other.catchAllAddress);
                }
            }
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.compareTo((Code$CatchHandler)object);
        }

    }
    // class: com/tencent/tinker/android/dex/Code$CatchHandler
    public class Code$CatchHandler implements Comparable<Code$CatchHandler> {
        public int typeIndexes;
        public int addresses;
        public int catchAllAddress;
        public int offset;

        public Code$CatchHandler(int[] typeIndexes, int[] addresses, int catchAllAddress, int offset) {
            super();
            this.typeIndexes = typeIndexes;
            this.addresses = addresses;
            this.catchAllAddress = catchAllAddress;
            this.offset = offset;
        }

        public int compareTo(Code$CatchHandler other) {
            int res = CompareUtils.sArrCompare(this.typeIndexes, other.typeIndexes);
            if (res != 0) {
                return res;
            }
            else {
                res = CompareUtils.sArrCompare(this.addresses, other.addresses);
                if (res != 0) {
                    return res;
                }
                else {
                    return CompareUtils.sCompare(this.catchAllAddress, other.catchAllAddress);
                }
            }
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.compareTo((Code$CatchHandler)object);
        }

    }
}
