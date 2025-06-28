/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;

import java.util.HashSet;

// class: com/tencent/tinker/build/aapt/ResourceDirectory
public class ResourceDirectory {
    public String directoryName;
    public String resourceFullFilename;
    public Set<ResourceEntry> resourceEntrySet;

    public ResourceDirectory(String directoryName, String resourceFullFilename) {
        super();
        this.directoryName = null;
        this.resourceFullFilename = null;
        this.resourceEntrySet = new HashSet();
        this.directoryName = directoryName;
        this.resourceFullFilename = resourceFullFilename;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.directoryName, this.resourceFullFilename});
    }

    public boolean equals(Object object) {
        if ((object instanceof ResourceDirectory)) {
            return false;
        }
        else {
            ResourceDirectory that = (ResourceDirectory)object;
            if (ObjectUtil.equal(this.directoryName, that.directoryName) && ObjectUtil.equal(this.resourceFullFilename, that.resourceFullFilename)) {
                return true;
            }
            else {
                return false;
            }
        }
    }

}
