/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/utils;


// class: com/tencent/tinker/android/utils/SparseBoolArray
public class SparseBoolArray implements Cloneable {
    final private static int EMPTY_INT_ARRAY;
    final private static boolean EMPTY_BOOL_ARRAY;
    private int mKeys;
    private boolean mValues;
    private int mSize;

    public SparseBoolArray() {
        super(10);
    }

    public SparseBoolArray(int initialCapacity) {
        super();
        this.mKeys = initialCapacity == 0 ? new int[]{} : SparseBoolArray.EMPTY_INT_ARRAY;
        this.mValues = new boolean[]{};
        this.mSize = 0;
    }

    private static int growSize(int currentSize) {
        if (currentSize <= 4) {
            return 8;
        }
        else {
            return currentSize + currentSize >> 1;
        }
    }

    public SparseBoolArray clone() {
        Object clone = null;
        try {
            SparseBoolArray array = (SparseBoolArray)super.clone();
            array.mKeys = (int[])this.mKeys.clone();
            array.mValues = (boolean[])this.mValues.clone();
        }
        catch (CloneNotSupportedException var_2_0) {
        }
        return array;
    }

    public boolean get(int key) {
        int i = this.binarySearch(this.mKeys, this.mSize, key);
        if (i < 0) {
            throw new SparseBoolArray$KeyNotFoundException(new StringBuilder().append("").append(key).toString());
        }
        else {
            return this.mValues[i];
        }
    }

    public void delete(int key) {
        int i = this.binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0) {
            this.removeAt(i);
        }
    }

    public void removeAt(int index) {
        System.arraycopy(this.mKeys, index + 1, this.mKeys, index, this.mSize - index + 1);
        System.arraycopy(this.mValues, index + 1, this.mValues, index, this.mSize - index + 1);
        this.mSize = this.mSize - 1;
    }

    public void put(int key, boolean value) {
        int i = this.binarySearch(this.mKeys, this.mSize, key);
        if (i >= 0) {
            this.mValues[i] = value;
        }
        else {
            i ^= -1;
            this.mKeys = this.insertElementIntoIntArray(this.mKeys, this.mSize, i, key);
            this.mValues = this.insertElementIntoBoolArray(this.mValues, this.mSize, i, value);
            this.mSize = this.mSize + 1;
        }
    }

    public int size() {
        return this.mSize;
    }

    public int keyAt(int index) {
        return this.mKeys[index];
    }

    public boolean valueAt(int index) {
        return this.mValues[index];
    }

    public int indexOfKey(int key) {
        return this.binarySearch(this.mKeys, this.mSize, key);
    }

    public boolean containsKey(int key) {
        if (this.indexOfKey(key) >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int indexOfValue(boolean value) {
        for (int i = 0; i < this.mSize; i += 1) {
            if (this.mValues[i] == value) {
                return i;
            }
            else {
            }
        }
        return -1;
    }

    public void clear() {
        this.mSize = 0;
    }

    public void append(int key, boolean value) {
        if (this.mSize != 0 && key <= this.mKeys[this.mSize - 1]) {
            this.put(key, value);
        }
        else {
            this.mKeys = this.appendElementIntoIntArray(this.mKeys, this.mSize, key);
            this.mValues = this.appendElementIntoBoolArray(this.mValues, this.mSize, value);
            this.mSize = this.mSize + 1;
        }
    }

    private int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = lo + hi >>> 1;
            int midVal = array[mid];
            if (midVal < value) {
                lo = mid + 1;
                continue;;
            }
            else if (midVal > value) {
                hi = mid - 1;
                continue;;
            }
            else {
                return mid;
            }
        }
        return lo ^ -1;
    }

    private int[] appendElementIntoIntArray(int[] array, int currentSize, int element) {
        if (currentSize > array.length) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad currentSize, originalSize: ").append(array.length).append(" currentSize: ").append(currentSize).toString());
        }
        else {
            if (currentSize + 1 > array.length) {
                int[] newArray = new int[]{};
                System.arraycopy(array, 0, newArray, 0, currentSize);
            }
            newArray[currentSize] = element;
            return newArray;
        }
    }

    private boolean[] appendElementIntoBoolArray(boolean[] array, int currentSize, boolean element) {
        if (currentSize > array.length) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad currentSize, originalSize: ").append(array.length).append(" currentSize: ").append(currentSize).toString());
        }
        else {
            if (currentSize + 1 > array.length) {
                boolean[] newArray = new boolean[]{};
                System.arraycopy(array, 0, newArray, 0, currentSize);
            }
            newArray[currentSize] = element;
            return newArray;
        }
    }

    private int[] insertElementIntoIntArray(int[] array, int currentSize, int index, int element) {
        if (currentSize > array.length) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad currentSize, originalSize: ").append(array.length).append(" currentSize: ").append(currentSize).toString());
        }
        else if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        }
        else {
            int[] newArray = new int[]{};
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = element;
            System.arraycopy(array, index, newArray, index + 1, array.length - index);
            return newArray;
        }
    }

    private boolean[] insertElementIntoBoolArray(boolean[] array, int currentSize, int index, boolean element) {
        if (currentSize > array.length) {
            throw new IllegalArgumentException(new StringBuilder().append("Bad currentSize, originalSize: ").append(array.length).append(" currentSize: ").append(currentSize).toString());
        }
        else if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        }
        else {
            boolean[] newArray = new boolean[]{};
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = element;
            System.arraycopy(array, index, newArray, index + 1, array.length - index);
            return newArray;
        }
    }

    public String toString() {
        if (this.size() <= 0) {
            return "{}";
        }
        else {
            StringBuilder buffer = new StringBuilder(this.mSize * 28);
            buffer.append(123);
            for (int i = 0; i < this.mSize; i += 1) {
                if (i > 0) {
                    buffer.append(", ");
                }
                int key = this.keyAt(i);
                buffer.append(key);
                buffer.append(61);
                boolean value = this.valueAt(i);
                buffer.append(value);
            }
            buffer.append(125);
            return buffer.toString();
        }
    }

    public /* synthetic */ Object clone() {
        return this.clone();
    }

    static  {
        SparseBoolArray.EMPTY_INT_ARRAY = new int[]{};
        SparseBoolArray.EMPTY_BOOL_ARRAY = new boolean[]{};
    }

    // class: com/tencent/tinker/android/utils/SparseBoolArray$KeyNotFoundException
    public class SparseBoolArray$KeyNotFoundException {

        public SparseBoolArray$KeyNotFoundException() {
            super();
        }

        public SparseBoolArray$KeyNotFoundException(String msg) {
            super(msg);
        }

    }
    // class: com/tencent/tinker/android/utils/SparseBoolArray$KeyNotFoundException
    public class SparseBoolArray$KeyNotFoundException {

        public SparseBoolArray$KeyNotFoundException() {
            super();
        }

        public SparseBoolArray$KeyNotFoundException(String msg) {
            super(msg);
        }

    }
}
