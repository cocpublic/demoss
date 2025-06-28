/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;


// class: com/tencent/tinker/build/aapt/ResourceEntry
public class ResourceEntry {
    public String name;
    public String value;

    public ResourceEntry(String name, String value) {
        super();
        this.name = null;
        this.value = null;
        this.name = name;
        this.value = value;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.name});
    }

    public boolean equals(Object object) {
        if ((object instanceof ResourceEntry)) {
            return false;
        }
        else {
            ResourceEntry that = (ResourceEntry)object;
            return ObjectUtil.equal(this.name, that.name);
        }
    }

}
