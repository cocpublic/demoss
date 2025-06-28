/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.List;
import com.google.common.base.Joiner;

// class: com/tencent/tinker/build/aapt/AaptResourceCollector
public class AaptResourceCollector {
    final private Map<RDotTxtEntry$RType, Map<String, Set<ResourceDirectory>>> rTypeResourceDirectoryMap;
    final private Map<RDotTxtEntry$RType, AaptResourceCollector$ResourceIdEnumerator> rTypeEnumeratorMap;
    final private Map<RDotTxtEntry, RDotTxtEntry> originalResourceMap;
    final private Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> rTypeResourceMap;
    final private Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> rTypeIncreaseResourceMap;
    final private Map<String, Set<String>> duplicateResourceMap;
    final private Map<RDotTxtEntry$RType, HashMap<String, String>> sanitizeTypeMap;
    final private Set<String> ignoreIdSet;
    private int currentTypeId;

    public AaptResourceCollector() {
        super();
        this.rTypeResourceDirectoryMap = new HashMap();
        this.rTypeEnumeratorMap = new HashMap();
        this.rTypeResourceMap = new HashMap();
        this.rTypeIncreaseResourceMap = new HashMap();
        this.duplicateResourceMap = new HashMap();
        this.sanitizeTypeMap = new HashMap();
        this.originalResourceMap = new HashMap();
        this.ignoreIdSet = new HashSet();
        this.currentTypeId = 2;
    }

    publicvoid AaptResourceCollector(Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> rTypeResourceMap) {
        super();
        if (rTypeResourceMap != null) {
            Iterator iterator = rTypeResourceMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map$Entry entry = (Map$Entry)iterator.next();
                RDotTxtEntry$RType rType = (RDotTxtEntry$RType)entry.getKey();
                Set set = (Set)entry.getValue();
                iterator = set.iterator();
                while (iterator.hasNext()) {
                    RDotTxtEntry rDotTxtEntry = (RDotTxtEntry)iterator.next();
                    this.originalResourceMap.put(rDotTxtEntry, rDotTxtEntry);
                    Object resourceIdEnumerator = null;
                    if (rDotTxtEntry.idType.equals(RDotTxtEntry$IdType.INT_ARRAY)) {
                        int resourceId = Integer.decode(rDotTxtEntry.idValue.trim()).intValue();
                        int typeId = resourceId & 16711680 / 65536;
                        if (typeId >= this.currentTypeId) {
                            this.currentTypeId = typeId + 1;
                        }
                        if (this.rTypeEnumeratorMap.containsKey(rType)) {
                            AaptResourceCollector$ResourceIdEnumerator enumerator = (AaptResourceCollector$ResourceIdEnumerator)this.rTypeEnumeratorMap.get(rType);
                            if (AaptResourceCollector$ResourceIdEnumerator.access$000(enumerator) < resourceId) {
                                AaptResourceCollector$ResourceIdEnumerator.access$002(enumerator, resourceId);
                                continue;;
                            }
                        }
                        else {
                            AaptResourceCollector$ResourceIdEnumerator enumeratorVar1 = new AaptResourceCollector$ResourceIdEnumerator();
                            AaptResourceCollector$ResourceIdEnumerator.access$002(enumeratorVar1, resourceId);
                            this.rTypeEnumeratorMap.put(rType, enumeratorVar1);
                        }
                    }
                }
            }
        }
    }

    public void addIntResourceIfNotPresent(RDotTxtEntry$RType rType, String name) {
        if (this.rTypeEnumeratorMap.containsKey(rType)) {
            if (rType.equals(RDotTxtEntry$RType.ATTR)) {
                this.rTypeEnumeratorMap.put(rType, new AaptResourceCollector$ResourceIdEnumerator(1));
            }
            else {
                this.currentTypeId = this.currentTypeId + 1;
                this.rTypeEnumeratorMap.put(rType, new AaptResourceCollector$ResourceIdEnumerator(this.currentTypeId));
            }
        }
        FakeRDotTxtEntry entry = new FakeRDotTxtEntry(RDotTxtEntry$IdType.INT, rType, name);
        Object resourceSet = null;
        HashSet set = this.rTypeResourceMap.containsKey(rType) ? new HashSet() : (Set)this.rTypeResourceMap.get(rType);
        this.rTypeResourceMap.put(rType, set);
        if (var_4_0.contains(entry)) {
            String idValue = String.format("0x%08x", new Object[]{Integer.valueOf((AaptResourceCollector$ResourceIdEnumerator)this.rTypeEnumeratorMap.get(rType).next())});
            this.addResource(rType, RDotTxtEntry$IdType.INT, name, idValue);
        }
    }

    public void addIntArrayResourceIfNotPresent(RDotTxtEntry$RType rType, String name, int numValues) {
        String idValue = String.format("{ %s }", new Object[]{Joiner.on(",").join(Collections.nCopies(numValues, "0x7f000000"))});
        this.addResource(rType, RDotTxtEntry$IdType.INT_ARRAY, name, idValue);
    }

    public void addResource(RDotTxtEntry$RType rType, RDotTxtEntry$IdType idType, String name, String idValue) {
        Object resourceSet = null;
        HashSet set = this.rTypeResourceMap.containsKey(rType) ? new HashSet() : (Set)this.rTypeResourceMap.get(rType);
        this.rTypeResourceMap.put(rType, set);
        RDotTxtEntry rDotTxtEntry = new RDotTxtEntry(idType, rType, name, idValue);
        int increaseResource = 0;
        if (var_5_0.contains(rDotTxtEntry)) {
            if (this.originalResourceMap.containsKey(rDotTxtEntry)) {
                (AaptResourceCollector$ResourceIdEnumerator)this.rTypeEnumeratorMap.get(rType).previous();
                rDotTxtEntry = (RDotTxtEntry)this.originalResourceMap.get(rDotTxtEntry);
            }
            else {
                increaseResource = 1;
            }
            var_5_0.add(rDotTxtEntry);
        }
        Object increaseResourceSet = null;
        HashSet setVar1 = this.rTypeIncreaseResourceMap.containsKey(rType) ? new HashSet() : (Set)this.rTypeIncreaseResourceMap.get(rType);
        this.rTypeIncreaseResourceMap.put(rType, setVar1);
        if (increaseResource != 0) {
            var_8_0.add(rDotTxtEntry);
        }
    }

    public boolean isContainResource(RDotTxtEntry$RType rType, RDotTxtEntry$IdType idType, String name) {
        int result = false;
        if (this.rTypeResourceMap.containsKey(rType)) {
            Set resourceSet = (Set)this.rTypeResourceMap.get(rType);
            if (resourceSet.contains(new RDotTxtEntry(idType, rType, name, "0x7f000000"))) {
                result = 1;
            }
        }
        return result;
    }

    void addRTypeResourceName(RDotTxtEntry$RType rType, String resourceName, String resourceValue, ResourceDirectory resourceDirectory) {
        ResourceDirectory oldResourceDirectory;
        Object directoryResourceDirectoryMap = null;
        HashMap map = this.rTypeResourceDirectoryMap.containsKey(rType) ? new HashMap() : (Map)this.rTypeResourceDirectoryMap.get(rType);
        this.rTypeResourceDirectoryMap.put(rType, map);
        Object resourceDirectorySet = null;
        HashSet set = var_5_0.containsKey(resourceDirectory.directoryName) ? new HashSet() : (Set)var_5_0.get(resourceDirectory.directoryName);
        var_5_0.put(resourceDirectory.directoryName, set);
        int find = 0;
        ResourceDirectory newResourceDirectory = new ResourceDirectory(resourceDirectory.directoryName, resourceDirectory.resourceFullFilename);
        if (var_6_0.contains(newResourceDirectory)) {
            var_6_0.add(newResourceDirectory);
        }
        Iterator iteratorVar1 = var_6_0.iterator();
        while (iteratorVar1.hasNext()) {
            oldResourceDirectory = (ResourceDirectory)iteratorVar1.next();
            if (oldResourceDirectory.resourceEntrySet.contains(new ResourceEntry(resourceName, resourceValue))) {
                find = 1;
                String resourceKey = new StringBuilder().append(rType).append("/").append(resourceDirectory.directoryName).append("/").append(resourceName).toString();
                Object fullFilenameSet = null;
                set = this.duplicateResourceMap.containsKey(resourceKey) ? (Set)this.duplicateResourceMap.get(resourceKey) : new HashSet();
                var_12_0.add(resourceDirectory.resourceFullFilename);
            }
        }
        if (find == 0) {
            iteratorVar1 = var_6_0.iterator();
            while (iteratorVar1.hasNext()) {
                oldResourceDirectory = (ResourceDirectory)iteratorVar1.next();
                if (oldResourceDirectory.equals(newResourceDirectory) && oldResourceDirectory.resourceEntrySet.contains(new ResourceEntry(resourceName, resourceValue))) {
                    oldResourceDirectory.resourceEntrySet.add(new ResourceEntry(resourceName, resourceValue));
                }
            }
        }
    }

    void putSanitizeName(RDotTxtEntry$RType rType, String sanitizeName, String rawName) {
        HashMap sanitizeNameMap = this.sanitizeTypeMap.containsKey(rType) ? (HashMap)this.sanitizeTypeMap.get(rType) : new HashMap();
        if (sanitizeNameMap.containsKey(sanitizeName)) {
            sanitizeNameMap.put(sanitizeName, rawName);
        }
    }

    public String getRawName(RDotTxtEntry$RType rType, String sanitizeName) {
        if (this.sanitizeTypeMap.containsKey(rType)) {
            return null;
        }
        else {
            return (String)(HashMap)this.sanitizeTypeMap.get(rType).get(sanitizeName);
        }
    }

    public Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> getRTypeResourceMap() {
        return this.rTypeResourceMap;
    }

    public Map<String, Set<String>> getDuplicateResourceMap() {
        return this.duplicateResourceMap;
    }

    public Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> getRTypeIncreaseResourceMap() {
        return this.rTypeIncreaseResourceMap;
    }

    public Map<RDotTxtEntry$RType, Map<String, Set<ResourceDirectory>>> getRTypeResourceDirectoryMap() {
        return this.rTypeResourceDirectoryMap;
    }

    void addIgnoreId(String name) {
        this.ignoreIdSet.add(name);
    }

    public Set<String> getIgnoreIdSet() {
        return this.ignoreIdSet;
    }

    // class: com/tencent/tinker/build/aapt/AaptResourceCollector$ResourceIdEnumerator
    class AaptResourceCollector$ResourceIdEnumerator {
        private int currentId;

         AaptResourceCollector$ResourceIdEnumerator() {
            super();
            this.currentId = 0;
        }

         AaptResourceCollector$ResourceIdEnumerator(int typeId) {
            super();
            this.currentId = 0;
            this.currentId = 2130706432 + 65536 * typeId + -1;
        }

        int previous() {
            this.currentId = this.currentId - 1;
            return this.currentId - 1;
        }

        int next() {
            this.currentId = this.currentId + 1;
            return this.currentId + 1;
        }

        static /* synthetic */ int access$000(AaptResourceCollector$ResourceIdEnumerator x0) {
            return x0.currentId;
        }

        static /* synthetic */ int access$002(AaptResourceCollector$ResourceIdEnumerator x0, int x1) {
            x0.currentId = x1;
            return x1;
        }

    }
    // class: com/tencent/tinker/build/aapt/AaptResourceCollector$ResourceIdEnumerator
    class AaptResourceCollector$ResourceIdEnumerator {
        private int currentId;

         AaptResourceCollector$ResourceIdEnumerator() {
            super();
            this.currentId = 0;
        }

         AaptResourceCollector$ResourceIdEnumerator(int typeId) {
            super();
            this.currentId = 0;
            this.currentId = 2130706432 + 65536 * typeId + -1;
        }

        int previous() {
            this.currentId = this.currentId - 1;
            return this.currentId - 1;
        }

        int next() {
            this.currentId = this.currentId + 1;
            return this.currentId + 1;
        }

        static /* synthetic */ int access$000(AaptResourceCollector$ResourceIdEnumerator x0) {
            return x0.currentId;
        }

        static /* synthetic */ int access$002(AaptResourceCollector$ResourceIdEnumerator x0, int x1) {
            x0.currentId = x1;
            return x1;
        }

    }
}
