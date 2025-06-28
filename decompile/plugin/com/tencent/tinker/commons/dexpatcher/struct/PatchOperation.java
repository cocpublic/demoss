/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher/struct;


// class: com/tencent/tinker/commons/dexpatcher/struct/PatchOperation
public final class PatchOperation<T> {
    final public static int OP_DEL;
    final public static int OP_ADD;
    final public static int OP_REPLACE;
    public int op;
    public int index;
    public T newItem;

    public PatchOperation(int op, int index) {
        super(op, index, null);
    }

    publicvoid PatchOperation(int op, int index, T newItem) {
        super();
        this.op = op;
        this.index = index;
        this.newItem = newItem;
    }

    public static String translateOpToString(int op) {
        switch(op) {
            case 0: {
                return "OP_DEL";
            }
            case 1: {
                return "OP_ADD";
            }
            case 2: {
                return "OP_REPLACE";
            }
            default: {
                return "OP_UNKNOWN";
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String opDesc = PatchOperation.translateOpToString(this.op);
        sb.append(123);
        sb.append("op: ").append(opDesc).append(", index: ").append(this.index).append(", newItem: ").append(this.newItem);
        sb.append(125);
        return sb.toString();
    }

}
