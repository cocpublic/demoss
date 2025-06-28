/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/CallSiteId
public class CallSiteId {
    public int offset;

    public CallSiteId(int off, int offset) {
        super(off);
        this.offset = offset;
    }

    public void writeTo(Dex$Section out) {
        out.writeInt(this.offset);
    }

    public int byteCountInDex() {
        return 4;
    }

    public int compareTo(CallSiteId o) {
        return CompareUtils.uCompare(this.offset, o.offset);
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((CallSiteId)object);
    }

}
