package com.tencent.tinker.loader.shareutil;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for reading and parsing ELF (Executable and Linkable Format) files.
 * This is commonly used for handling SO (shared object) files in Android.
 *
 * Note: This class was translated from a decompiled and obfuscated class `e.java`.
 * Field names and structure are mapped to standard ELF specifications.
 */
public class ShareElfFile implements Closeable {
    private final FileInputStream fis;
    private final Map<String, SectionHeader> sectionNameToHeaderMap;

    public ElfHeader elfHeader;
    public ProgramHeader[] programHeaders;
    public SectionHeader[] sectionHeaders;

    public static final int FILE_TYPE_ELF = 1;
    public static final int FILE_TYPE_DEX = 0; // Based on original magic check
    public static final int FILE_TYPE_UNKNOWN = -1;

    /**
     * ELF Header structure.
     */
    public static class ElfHeader {
        public static final int EI_NIDENT = 16;

        public final byte[] e_ident; // Magic number and other info
        public final short e_type;      // Object file type
        public final short e_machine;   // Architecture
        public final int e_version;   // Object file version
        public final long e_entry;     // Entry point virtual address
        public final long e_phoff;     // Program header table file offset
        public final long e_shoff;     // Section header table file offset
        public final int e_flags;     // Processor-specific flags
        public final short e_ehsize;    // ELF header size in bytes
        public final short e_phentsize; // Program header table entry size
        public final short e_phnum;     // Program header table entry count
        public final short e_shentsize; // Section header table entry size
        public final short e_shnum;     // Section header table entry count
        public final short e_shstrndx;  // Section header string table index

        private ElfHeader(FileChannel channel) throws IOException {
            this.e_ident = new byte[EI_NIDENT];
            channel.position(0);
            readExactly(channel, ByteBuffer.wrap(this.e_ident), "Failed to read ELF ident.");

            if (e_ident[0] != 0x7f || e_ident[1] != 'E' || e_ident[2] != 'L' || e_ident[3] != 'F') {
                throw new IOException(String.format(
                        "Invalid ELF magic: %x %x %x %x.", e_ident[0], e_ident[1], e_ident[2], e_ident[3]));
            }

            assertInRange(e_ident[4], 1, 2, "Unsupported ELF class: " + e_ident[4]); // EI_CLASS (1=32bit, 2=64bit)
            assertInRange(e_ident[5], 1, 2, "Unsupported ELF data encoding: " + e_ident[5]); // EI_DATA (1=LSB, 2=MSB)

            // EI_CLASS: 1 for 32-bit, 2 for 64-bit
            // EI_DATA: 1 for little-endian, 2 for big-endian
            boolean is32Bit = (e_ident[4] == 1);
            ByteOrder order = (e_ident[5] == 1) ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;

            // Size of "rest part of ehdr" is e_ehsize - EI_NIDENT, but e_ehsize is read later.
            // For 32-bit, ELF header is 52 bytes. For 64-bit, it's 64 bytes.
            // So, rest part is 52-16=36 for 32bit, 64-16=48 for 64bit.
            // The decompiled code had this reversed in allocate: (this.a[4] == 1 ? 48 : 36) -> (is32Bit ? 36 : 48)
            ByteBuffer ehdrRest = ByteBuffer.allocate(is32Bit ? (52 - EI_NIDENT) : (64 - EI_NIDENT));
            ehdrRest.order(order);
            readExactly(channel, ehdrRest, "Failed to read rest of ELF header.");

            this.e_type = ehdrRest.getShort();
            this.e_machine = ehdrRest.getShort();
            this.e_version = ehdrRest.getInt();
            assertInRange(this.e_version, 1, 1, "Unsupported ELF version: " + this.e_version);

            if (is32Bit) {
                this.e_entry = ehdrRest.getInt() & 0xFFFFFFFFL; // unsigned
                this.e_phoff = ehdrRest.getInt() & 0xFFFFFFFFL;
                this.e_shoff = ehdrRest.getInt() & 0xFFFFFFFFL;
            } else {
                this.e_entry = ehdrRest.getLong();
                this.e_phoff = ehdrRest.getLong();
                this.e_shoff = ehdrRest.getLong();
            }

            this.e_flags = ehdrRest.getInt();
            this.e_ehsize = ehdrRest.getShort();
            this.e_phentsize = ehdrRest.getShort();
            this.e_phnum = ehdrRest.getShort();
            this.e_shentsize = ehdrRest.getShort();
            this.e_shnum = ehdrRest.getShort();
            this.e_shstrndx = ehdrRest.getShort();
        }
    }

    /**
     * Program Header structure.
     */
    public static class ProgramHeader {
        public final int p_type;    // Segment type
        public final int p_flags;   // Segment flags (comes after p_type in 64-bit, after p_memsz in 32-bit)
        public final long p_offset;  // Segment file offset
        public final long p_vaddr;   // Segment virtual address
        public final long p_paddr;   // Segment physical address
        public final long p_filesz;  // Segment size in file
        public final long p_memsz;   // Segment size in memory
        public final long p_align;   // Segment alignment

        private ProgramHeader(ByteBuffer buffer, int elfClass) throws IOException {
            boolean is32Bit = (elfClass == 1);
            if (is32Bit) {
                this.p_type = buffer.getInt();
                this.p_offset = buffer.getInt() & 0xFFFFFFFFL;
                this.p_vaddr = buffer.getInt() & 0xFFFFFFFFL;
                this.p_paddr = buffer.getInt() & 0xFFFFFFFFL;
                this.p_filesz = buffer.getInt() & 0xFFFFFFFFL;
                this.p_memsz = buffer.getInt() & 0xFFFFFFFFL;
                this.p_flags = buffer.getInt();
                this.p_align = buffer.getInt() & 0xFFFFFFFFL;
            } else { // 64-bit
                this.p_type = buffer.getInt();
                this.p_flags = buffer.getInt(); // Flags come earlier in 64-bit struct
                this.p_offset = buffer.getLong();
                this.p_vaddr = buffer.getLong();
                this.p_paddr = buffer.getLong();
                this.p_filesz = buffer.getLong();
                this.p_memsz = buffer.getLong();
                this.p_align = buffer.getLong();
            }
        }
    }

    /**
     * Section Header structure.
     */
    public static class SectionHeader {
        public final int sh_name;      // Section name (string tbl index)
        public final int sh_type;      // Section type
        public final long sh_flags;     // Section flags
        public final long sh_addr;      // Section virtual addr at execution
        public final long sh_offset;    // Section file offset
        public final long sh_size;      // Section size in bytes
        public final int sh_link;      // Link to other section
        public final int sh_info;      // Additional section information
        public final long sh_addralign; // Section alignment
        public final long sh_entsize;   // Entry size if section holds table
        public String sectionName;     // Resolved section name

        private SectionHeader(ByteBuffer buffer, int elfClass) throws IOException {
            boolean is32Bit = (elfClass == 1);
            if (is32Bit) {
                this.sh_name = buffer.getInt();
                this.sh_type = buffer.getInt();
                this.sh_flags = buffer.getInt() & 0xFFFFFFFFL;
                this.sh_addr = buffer.getInt() & 0xFFFFFFFFL;
                this.sh_offset = buffer.getInt() & 0xFFFFFFFFL;
                this.sh_size = buffer.getInt() & 0xFFFFFFFFL;
                this.sh_link = buffer.getInt();
                this.sh_info = buffer.getInt();
                this.sh_addralign = buffer.getInt() & 0xFFFFFFFFL;
                this.sh_entsize = buffer.getInt() & 0xFFFFFFFFL;
            } else { // 64-bit
                this.sh_name = buffer.getInt();
                this.sh_type = buffer.getInt();
                this.sh_flags = buffer.getLong();
                this.sh_addr = buffer.getLong();
                this.sh_offset = buffer.getLong();
                this.sh_size = buffer.getLong();
                this.sh_link = buffer.getInt();
                this.sh_info = buffer.getInt();
                this.sh_addralign = buffer.getLong();
                this.sh_entsize = buffer.getLong();
            }
            this.sectionName = null;
        }
    }

    public ShareElfFile(File file) throws IOException {
        this.sectionNameToHeaderMap = new HashMap<>();
        this.fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();

        this.elfHeader = new ElfHeader(channel);

        ByteBuffer headerBuffer = ByteBuffer.allocate(128); // Max possible size for PHT/SHT entries is less
        headerBuffer.order(elfHeader.e_ident[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
        int elfClass = elfHeader.e_ident[4]; // 1 for 32-bit, 2 for 64-bit

        // Read Program Headers
        channel.position(this.elfHeader.e_phoff);
        this.programHeaders = new ProgramHeader[this.elfHeader.e_phnum];
        for (int i = 0; i < this.elfHeader.e_phnum; ++i) {
            headerBuffer.limit(this.elfHeader.e_phentsize);
            readExactly(channel, headerBuffer, "Failed to read program header.");
            this.programHeaders[i] = new ProgramHeader(headerBuffer, elfClass);
        }

        // Read Section Headers
        channel.position(this.elfHeader.e_shoff);
        this.sectionHeaders = new SectionHeader[this.elfHeader.e_shnum];
        for (int i = 0; i < this.elfHeader.e_shnum; ++i) {
            headerBuffer.limit(this.elfHeader.e_shentsize);
            readExactly(channel, headerBuffer, "Failed to read section header.");
            this.sectionHeaders[i] = new SectionHeader(headerBuffer, elfClass);
        }

        // Read section names if string table exists
        if (this.elfHeader.e_shstrndx > 0 && this.elfHeader.e_shstrndx < this.sectionHeaders.length) {
            SectionHeader strTableHeader = this.sectionHeaders[this.elfHeader.e_shstrndx];
            ByteBuffer strTableBuffer = readSectionData(strTableHeader);
            for (SectionHeader sh : this.sectionHeaders) {
                strTableBuffer.position(sh.sh_name);
                sh.sectionName = readCString(strTableBuffer);
                this.sectionNameToHeaderMap.put(sh.sectionName, sh);
            }
        }
    }

    /**
     * Determines the file type (ELF, DEX, or UNKNOWN) by inspecting its magic number.
     *
     * @param file The file to inspect.
     * @return An integer representing the file type:
     *         {@link #FILE_TYPE_ELF}, {@link #FILE_TYPE_DEX}, or {@link #FILE_TYPE_UNKNOWN}.
     * @throws IOException If an I/O error occurs.
     */
    public static int getFileTypeByMagic(File file) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] magic = new byte[4];
            int bytesRead = fis.read(magic);
            if (bytesRead < 4) {
                return FILE_TYPE_UNKNOWN;
            }

            if (magic[0] == 0x7f && magic[1] == 'E' && magic[2] == 'L' && magic[3] == 'F') {
                return FILE_TYPE_ELF;
            } else if (magic[0] == 'd' && magic[1] == 'e' && magic[2] == 'x' && magic[3] == '\n') {
                // Standard DEX magic: "dex\n035\0" or "dex\n036\0" etc. First 4 bytes are "dex\n".
                return FILE_TYPE_DEX;
            } else {
                return FILE_TYPE_UNKNOWN;
            }
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


    private static void readExactly(FileChannel channel, ByteBuffer buffer, String errorMessage) throws IOException {
        buffer.rewind(); // Prepare buffer for reading from channel
        int bytesRead = channel.read(buffer);
        if (bytesRead != buffer.limit()) {
            throw new IOException(errorMessage + " Expected to read " + buffer.limit() +
                                  " bytes, but only " + bytesRead + " were read.");
        }
        buffer.flip(); // Prepare buffer for reading its content
    }

    private static String readCString(ByteBuffer buffer) {
        byte[] bytes = buffer.array();
        int currentPosition = buffer.position();
        int end = currentPosition;
        while (end < buffer.limit() && bytes[end] != 0) {
            end++;
        }
        // Consume the null terminator if found within limit
        if (end < buffer.limit() && bytes[end] == 0) {
           buffer.position(end + 1);
        } else {
           buffer.position(end); // Position at limit if no null found
        }
        return new String(bytes, currentPosition, end - currentPosition, StandardCharsets.US_ASCII);
    }

    public ByteBuffer readSectionData(SectionHeader section) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate((int) section.sh_size);
        FileChannel channel = this.fis.getChannel();
        channel.position(section.sh_offset);
        readExactly(channel, buffer, "Failed to read section: " + section.sectionName);
        return buffer;
    }

    public SectionHeader getSectionHeaderByName(String sectionName) {
        return this.sectionNameToHeaderMap.get(sectionName);
    }

    @Override
    public void close() throws IOException {
        if (fis != null) {
            fis.close();
        }
        sectionNameToHeaderMap.clear();
        elfHeader = null;
        programHeaders = null;
        sectionHeaders = null;
    }

    private static void assertInRange(int value, int min, int max, String message) throws IOException {
        if (value < min || value > max) {
            throw new IOException(message);
        }
    }
}
