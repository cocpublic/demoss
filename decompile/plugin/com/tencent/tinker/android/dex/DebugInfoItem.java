/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/DebugInfoItem
public class DebugInfoItem {
    final public static byte DBG_END_SEQUENCE;
    final public static byte DBG_ADVANCE_PC;
    final public static byte DBG_ADVANCE_LINE;
    final public static byte DBG_START_LOCAL;
    final public static byte DBG_START_LOCAL_EXTENDED;
    final public static byte DBG_END_LOCAL;
    final public static byte DBG_RESTART_LOCAL;
    final public static byte DBG_SET_PROLOGUE_END;
    final public static byte DBG_SET_EPILOGUE_BEGIN;
    final public static byte DBG_SET_FILE;
    public int lineStart;
    public int parameterNames;
    public byte infoSTM;

    public DebugInfoItem(int off, int lineStart, int[] parameterNames, byte[] infoSTM) {
        super(off);
        this.lineStart = lineStart;
        this.parameterNames = parameterNames;
        this.infoSTM = infoSTM;
    }

    public int compareTo(DebugInfoItem o) {
        int origLineStart = this.lineStart;
        int destLineStart = o.lineStart;
        if (origLineStart != destLineStart) {
            return origLineStart - destLineStart;
        }
        else {
            int cmpRes = CompareUtils.uArrCompare(this.parameterNames, o.parameterNames);
            if (cmpRes != 0) {
                return cmpRes;
            }
            else {
                cmpRes = CompareUtils.uArrCompare(this.infoSTM, o.infoSTM);
                return cmpRes;
            }
        }
    }

    public int hashCode() {
        return HashCodeHelper.hash(new Object[]{Integer.valueOf(this.lineStart), this.parameterNames, this.infoSTM});
    }

    public boolean equals(Object obj) {
        if ((obj instanceof DebugInfoItem)) {
            return false;
        }
        else if (this.compareTo((DebugInfoItem)obj) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int byteCountInDex() {
        int byteCount = Leb128.unsignedLeb128Size(this.lineStart) + Leb128.unsignedLeb128Size(this.parameterNames.length);
        int[] intArr0 = this.parameterNames;
        for (int i1 = 0; i1 < intArr0.length; i1 += 1) {
            int pn = intArr0[i1];
            byteCount += Leb128.unsignedLeb128p1Size(pn);
        }
        byteCount += this.infoSTM.length * 1;
        return byteCount;
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((DebugInfoItem)object);
    }

}
