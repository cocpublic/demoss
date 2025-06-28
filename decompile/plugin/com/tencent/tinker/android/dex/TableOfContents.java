/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/android/dex;


// class: com/tencent/tinker/android/dex/TableOfContents
public final class TableOfContents {
    final public static short SECTION_TYPE_HEADER;
    final public static short SECTION_TYPE_STRINGIDS;
    final public static short SECTION_TYPE_TYPEIDS;
    final public static short SECTION_TYPE_PROTOIDS;
    final public static short SECTION_TYPE_FIELDIDS;
    final public static short SECTION_TYPE_METHODIDS;
    final public static short SECTION_TYPE_CLASSDEFS;
    final public static short SECTION_TYPE_CALLSITEIDS;
    final public static short SECTION_TYPE_METHODHANDLES;
    final public static short SECTION_TYPE_MAPLIST;
    final public static short SECTION_TYPE_TYPELISTS;
    final public static short SECTION_TYPE_ANNOTATIONSETREFLISTS;
    final public static short SECTION_TYPE_ANNOTATIONSETS;
    final public static short SECTION_TYPE_CLASSDATA;
    final public static short SECTION_TYPE_CODES;
    final public static short SECTION_TYPE_STRINGDATAS;
    final public static short SECTION_TYPE_DEBUGINFOS;
    final public static short SECTION_TYPE_ANNOTATIONS;
    final public static short SECTION_TYPE_ENCODEDARRAYS;
    final public static short SECTION_TYPE_ANNOTATIONSDIRECTORIES;
    final public TableOfContents$Section header;
    final public TableOfContents$Section stringIds;
    final public TableOfContents$Section typeIds;
    final public TableOfContents$Section protoIds;
    final public TableOfContents$Section fieldIds;
    final public TableOfContents$Section methodIds;
    final public TableOfContents$Section classDefs;
    final public TableOfContents$Section callSiteIds;
    final public TableOfContents$Section methodHandles;
    final public TableOfContents$Section mapList;
    final public TableOfContents$Section typeLists;
    final public TableOfContents$Section annotationSetRefLists;
    final public TableOfContents$Section annotationSets;
    final public TableOfContents$Section classDatas;
    final public TableOfContents$Section codes;
    final public TableOfContents$Section stringDatas;
    final public TableOfContents$Section debugInfos;
    final public TableOfContents$Section annotations;
    final public TableOfContents$Section encodedArrays;
    final public TableOfContents$Section annotationsDirectories;
    final public TableOfContents$Section[] sections;
    public int api;
    public int checksum;
    public byte signature;
    public int fileSize;
    public int linkSize;
    public int linkOff;
    public int dataSize;
    public int dataOff;

    public TableOfContents() {
        super();
        this.header = new TableOfContents$Section(false, 1);
        this.stringIds = new TableOfContents$Section(true, 1);
        this.typeIds = new TableOfContents$Section(2, 1);
        this.protoIds = new TableOfContents$Section(3, 1);
        this.fieldIds = new TableOfContents$Section(4, 1);
        this.methodIds = new TableOfContents$Section(5, 1);
        this.classDefs = new TableOfContents$Section(6, 1);
        this.callSiteIds = new TableOfContents$Section(7, 1);
        this.methodHandles = new TableOfContents$Section(8, 1);
        this.mapList = new TableOfContents$Section(4096, 1);
        this.typeLists = new TableOfContents$Section(4097, 1);
        this.annotationSetRefLists = new TableOfContents$Section(4098, 1);
        this.annotationSets = new TableOfContents$Section(4099, 1);
        this.classDatas = new TableOfContents$Section(8192, 0);
        this.codes = new TableOfContents$Section(8193, 1);
        this.stringDatas = new TableOfContents$Section(8194, 0);
        this.debugInfos = new TableOfContents$Section(8195, 0);
        this.annotations = new TableOfContents$Section(8196, 0);
        this.encodedArrays = new TableOfContents$Section(8197, 0);
        this.annotationsDirectories = new TableOfContents$Section(8198, 1);
        this.sections = new TableOfContents$Section[]{this.header, this.stringIds, this.typeIds, this.protoIds, this.fieldIds, this.methodIds, this.classDefs, this.mapList, this.callSiteIds, this.methodHandles, this.typeLists, this.annotationSetRefLists, this.annotationSets, this.classDatas, this.codes, this.stringDatas, this.debugInfos, this.annotations, this.encodedArrays, this.annotationsDirectories};
        this.api = 13;
        this.signature = new byte[]{};
    }

    public TableOfContents$Section getSectionByType(int type) {
        switch(type) {
            case 0: {
                return this.header;
            }
            case 1: {
                return this.stringIds;
            }
            case 2: {
                return this.typeIds;
            }
            case 3: {
                return this.protoIds;
            }
            case 4: {
                return this.fieldIds;
            }
            case 5: {
                return this.methodIds;
            }
            case 6: {
                return this.classDefs;
            }
            case 4096: {
                return this.mapList;
            }
            case 4097: {
                return this.typeLists;
            }
            case 7: {
                return this.callSiteIds;
            }
            case 8: {
                return this.methodHandles;
            }
            case 4098: {
                return this.annotationSetRefLists;
            }
            case 4099: {
                return this.annotationSets;
            }
            case 8192: {
                return this.classDatas;
            }
            case 8193: {
                return this.codes;
            }
            case 8194: {
                return this.stringDatas;
            }
            case 8195: {
                return this.debugInfos;
            }
            case 8196: {
                return this.annotations;
            }
            case 8197: {
                return this.encodedArrays;
            }
            case 8198: {
                return this.annotationsDirectories;
            }
            default: {
                throw new IllegalArgumentException(new StringBuilder().append("unknown section type: ").append(type).toString());
            }
        }
    }

    public void readFrom(Dex dex) {
        this.readHeader(dex.openSection(this.header));
        this.readMap(dex.openSection(this.mapList.off));
        this.computeSizesFromOffsets();
    }

    private void readHeader(Dex$Section headerIn) {
        byte[] magic = headerIn.readByteArray(8);
        this.api = DexFormat.magicToApi(magic);
        if (this.api == -1) {
            throw new DexException(new StringBuilder().append("Unexpected magic: ").append(Arrays.toString(magic)).toString());
        }
        else {
            this.checksum = headerIn.readInt();
            this.signature = headerIn.readByteArray(20);
            this.fileSize = headerIn.readInt();
            int headerSize = headerIn.readInt();
            if (headerSize != 112) {
                throw new DexException(new StringBuilder().append("Unexpected header: 0x").append(Integer.toHexString(headerSize)).toString());
            }
            else {
                int endianTag = headerIn.readInt();
                if (endianTag != 305419896) {
                    throw new DexException(new StringBuilder().append("Unexpected endian tag: 0x").append(Integer.toHexString(endianTag)).toString());
                }
                else {
                    this.linkSize = headerIn.readInt();
                    this.linkOff = headerIn.readInt();
                    this.mapList.off = headerIn.readInt();
                    if (this.mapList.off == 0) {
                        throw new DexException("Cannot merge dex files that do not contain a map");
                    }
                    else {
                        this.stringIds.size = headerIn.readInt();
                        this.stringIds.off = headerIn.readInt();
                        this.typeIds.size = headerIn.readInt();
                        this.typeIds.off = headerIn.readInt();
                        this.protoIds.size = headerIn.readInt();
                        this.protoIds.off = headerIn.readInt();
                        this.fieldIds.size = headerIn.readInt();
                        this.fieldIds.off = headerIn.readInt();
                        this.methodIds.size = headerIn.readInt();
                        this.methodIds.off = headerIn.readInt();
                        this.classDefs.size = headerIn.readInt();
                        this.classDefs.off = headerIn.readInt();
                        this.dataSize = headerIn.readInt();
                        this.dataOff = headerIn.readInt();
                    }
                }
            }
        }
    }

    private void readMap(Dex$Section in) {
        int mapSize = in.readInt();
        Object previous = null;
        for (int i = 0; i < mapSize; i += 1) {
            short type = in.readShort();
            in.readShort();
            TableOfContents$Section section = this.getSection(type);
            int size = in.readInt();
            int offset = in.readInt();
            if (section.size == 0 || section.size == size) {
                if (section.off != -1 && section.off != offset) {
                    section.size = size;
                    section.off = offset;
                    if (previous != null && previous.off > section.off) {
                        throw new DexException(new StringBuilder().append("Map is unsorted at ").append(previous).append(", ").append(section).toString());
                    }
                    else {
                        previous = section;
                    }
                }
            }
            throw new DexException(new StringBuilder().append("Unexpected map value for 0x").append(Integer.toHexString(type)).toString());
        }
        this.header.off = 0;
        Arrays.sort(this.sections);
        for (i = 1; i < this.sections.length; i += 1) {
            this.sections[i].off == -1;
            this.sections[i].off = this.sections[i - 1].off;
        }
    }

    public void computeSizesFromOffsets() {
        int end = this.fileSize;
        for (int i = this.sections.length - 1; i >= 0; i += 255) {
            TableOfContents$Section section = this.sections[i];
            if (section.off == -1) {
                continue;;
            }
            else if (section.off > end) {
                throw new DexException(new StringBuilder().append("Map is unsorted at ").append(section).toString());
            }
            else {
                section.byteCount = end - section.off;
                end = section.off;
            }
        }
        this.dataOff = this.header.byteCount + this.stringIds.byteCount + this.typeIds.byteCount + this.protoIds.byteCount + this.fieldIds.byteCount + this.methodIds.byteCount + this.classDefs.byteCount;
        this.dataSize = this.fileSize - this.dataOff;
    }

    private TableOfContents$Section getSection(short type) {
        TableOfContents$Section section = this.sections;
        for (int i1 = 0; i1 < section.length; i1 += 1) {
            section = section[i1];
            if (section.type == type) {
                return section;
            }
            else {
            }
        }
        throw new IllegalArgumentException(new StringBuilder().append("No such map item: ").append(type).toString());
    }

    public void writeHeader(Dex$Section out) {
        out.write(DexFormat.apiToMagic(this.api).getBytes("UTF-8"));
        out.writeInt(this.checksum);
        out.write(this.signature);
        out.writeInt(this.fileSize);
        out.writeInt(112);
        out.writeInt(305419896);
        out.writeInt(this.linkSize);
        out.writeInt(this.linkOff);
        out.writeInt(this.mapList.off);
        out.writeInt(this.stringIds.size);
        out.writeInt(this.stringIds.exists() ? 0 : this.stringIds.off);
        out.writeInt(this.typeIds.size);
        out.writeInt(this.typeIds.exists() ? 0 : this.typeIds.off);
        out.writeInt(this.protoIds.size);
        out.writeInt(this.protoIds.exists() ? 0 : this.protoIds.off);
        out.writeInt(this.fieldIds.size);
        out.writeInt(this.fieldIds.exists() ? 0 : this.fieldIds.off);
        out.writeInt(this.methodIds.size);
        out.writeInt(this.methodIds.exists() ? 0 : this.methodIds.off);
        out.writeInt(this.classDefs.size);
        out.writeInt(this.classDefs.exists() ? 0 : this.classDefs.off);
        out.writeInt(this.dataSize);
        out.writeInt(this.dataOff);
    }

    public void writeMap(Dex$Section out) {
        TableOfContents$Section section;
        int count = 0;
        TableOfContents$Section sectionVar1 = this.sections;
        for (int i3 = 0; i3 < sectionVar1.length; i3 += 1) {
            section = sectionVar1[i3];
            if (section.exists()) {
                count += 1;
            }
        }
        out.writeInt(count);
        sectionVar1 = this.sections;
        i2 = sectionVar1.length;
        for (i3 = 0; i3 < sectionVar1.length; i3 += 1) {
            section = sectionVar1[i3];
            if (section.exists()) {
                out.writeShort(section.type);
                out.writeShort(0);
                out.writeInt(section.size);
                out.writeInt(section.off);
            }
        }
    }

    // class: com/tencent/tinker/android/dex/TableOfContents$Section
    public class TableOfContents$Section implements Comparable<TableOfContents$Section> {
        final public static int UNDEF_INDEX;
        final public static int UNDEF_OFFSET;
        final public short type;
        public boolean isElementFourByteAligned;
        public int size;
        public int off;
        public int byteCount;

        public TableOfContents$Section(int type, boolean isElementFourByteAligned) {
            super();
            this.size = 0;
            this.off = -1;
            this.byteCount = 0;
            this.type = (short)type;
            this.isElementFourByteAligned = isElementFourByteAligned;
            if (type == 0) {
                this.off = 0;
                this.size = 1;
                this.byteCount = 112;
            }
            else {
                if (type == 4096) {
                    this.size = 1;
                }
            }
        }

        public boolean exists() {
            if (this.size > 0) {
                return true;
            }
            else {
                return false;
            }
        }

        private int remapTypeOrderId(int type) {
            switch(type) {
                case 0: {
                    return 0;
                }
                case 1: {
                    return 1;
                }
                case 2: {
                    return 2;
                }
                case 3: {
                    return 3;
                }
                case 4: {
                    return 4;
                }
                case 5: {
                    return 5;
                }
                case 8: {
                    return 6;
                }
                case 6: {
                    return 7;
                }
                case 8194: {
                    return 8;
                }
                case 4097: {
                    return 9;
                }
                case 8196: {
                    return 10;
                }
                case 4099: {
                    return 11;
                }
                case 4098: {
                    return 12;
                }
                case 8198: {
                    return 13;
                }
                case 8195: {
                    return 14;
                }
                case 8193: {
                    return 15;
                }
                case 8192: {
                    return 16;
                }
                case 8197: {
                    return 17;
                }
                case 7: {
                    return 18;
                }
                case 4096: {
                    return 19;
                }
                default: {
                    throw new IllegalArgumentException(new StringBuilder().append("unknown section type: ").append(type).toString());
                }
            }
        }

        public int compareTo(TableOfContents$Section section) {
            if (this.off != section.off) {
                if (this.off < section.off) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
            else {
                int remappedType = this.remapTypeOrderId(this.type);
                int otherRemappedType = this.remapTypeOrderId(section.type);
                if (remappedType != otherRemappedType) {
                    if (remappedType < otherRemappedType) {
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
                else {
                    return 0;
                }
            }
        }

        public String toString() {
            return String.format("Section[type=%#x,off=%#x,size=%#x,byteCount=%#x]", new Object[]{Short.valueOf(this.type), Integer.valueOf(this.off), Integer.valueOf(this.size), Integer.valueOf(this.byteCount)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.compareTo((TableOfContents$Section)object);
        }

        // class: com/tencent/tinker/android/dex/TableOfContents$Section$Item
        public abstract class TableOfContents$Section$Item<T> implements Comparable<T> {
            public int off;

            public TableOfContents$Section$Item(int off) {
                super();
                this.off = off;
            }

            public int hashCode() {
                return super.hashCode();
            }

            public boolean equals(Object obj) {
                if (this.compareTo(obj) == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

            int byteCountInDex();

        }
        // class: com/tencent/tinker/android/dex/TableOfContents$Section$Item
        public abstract class TableOfContents$Section$Item<T> implements Comparable<T> {
            public int off;

            public TableOfContents$Section$Item(int off) {
                super();
                this.off = off;
            }

            public int hashCode() {
                return super.hashCode();
            }

            public boolean equals(Object obj) {
                if (this.compareTo(obj) == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

            int byteCountInDex();

        }
    }
    // class: com/tencent/tinker/android/dex/TableOfContents$Section
    public class TableOfContents$Section implements Comparable<TableOfContents$Section> {
        final public static int UNDEF_INDEX;
        final public static int UNDEF_OFFSET;
        final public short type;
        public boolean isElementFourByteAligned;
        public int size;
        public int off;
        public int byteCount;

        public TableOfContents$Section(int type, boolean isElementFourByteAligned) {
            super();
            this.size = 0;
            this.off = -1;
            this.byteCount = 0;
            this.type = (short)type;
            this.isElementFourByteAligned = isElementFourByteAligned;
            if (type == 0) {
                this.off = 0;
                this.size = 1;
                this.byteCount = 112;
            }
            else {
                if (type == 4096) {
                    this.size = 1;
                }
            }
        }

        public boolean exists() {
            if (this.size > 0) {
                return true;
            }
            else {
                return false;
            }
        }

        private int remapTypeOrderId(int type) {
            switch(type) {
                case 0: {
                    return 0;
                }
                case 1: {
                    return 1;
                }
                case 2: {
                    return 2;
                }
                case 3: {
                    return 3;
                }
                case 4: {
                    return 4;
                }
                case 5: {
                    return 5;
                }
                case 8: {
                    return 6;
                }
                case 6: {
                    return 7;
                }
                case 8194: {
                    return 8;
                }
                case 4097: {
                    return 9;
                }
                case 8196: {
                    return 10;
                }
                case 4099: {
                    return 11;
                }
                case 4098: {
                    return 12;
                }
                case 8198: {
                    return 13;
                }
                case 8195: {
                    return 14;
                }
                case 8193: {
                    return 15;
                }
                case 8192: {
                    return 16;
                }
                case 8197: {
                    return 17;
                }
                case 7: {
                    return 18;
                }
                case 4096: {
                    return 19;
                }
                default: {
                    throw new IllegalArgumentException(new StringBuilder().append("unknown section type: ").append(type).toString());
                }
            }
        }

        public int compareTo(TableOfContents$Section section) {
            if (this.off != section.off) {
                if (this.off < section.off) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
            else {
                int remappedType = this.remapTypeOrderId(this.type);
                int otherRemappedType = this.remapTypeOrderId(section.type);
                if (remappedType != otherRemappedType) {
                    if (remappedType < otherRemappedType) {
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
                else {
                    return 0;
                }
            }
        }

        public String toString() {
            return String.format("Section[type=%#x,off=%#x,size=%#x,byteCount=%#x]", new Object[]{Short.valueOf(this.type), Integer.valueOf(this.off), Integer.valueOf(this.size), Integer.valueOf(this.byteCount)});
        }

        public /* synthetic */ int compareTo(Object object) {
            return this.compareTo((TableOfContents$Section)object);
        }

        // class: com/tencent/tinker/android/dex/TableOfContents$Section$Item
        public abstract class TableOfContents$Section$Item<T> implements Comparable<T> {
            public int off;

            public TableOfContents$Section$Item(int off) {
                super();
                this.off = off;
            }

            public int hashCode() {
                return super.hashCode();
            }

            public boolean equals(Object obj) {
                if (this.compareTo(obj) == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

            int byteCountInDex();

        }
        // class: com/tencent/tinker/android/dex/TableOfContents$Section$Item
        public abstract class TableOfContents$Section$Item<T> implements Comparable<T> {
            public int off;

            public TableOfContents$Section$Item(int off) {
                super();
                this.off = off;
            }

            public int hashCode() {
                return super.hashCode();
            }

            public boolean equals(Object obj) {
                if (this.compareTo(obj) == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }

            int byteCountInDex();

        }
    }
}
