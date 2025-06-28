/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/ziputils/ziputil;

import java.io.EOFException;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicReference;

// class: com/tencent/tinker/ziputils/ziputil/Streams
public final class Streams {
    private static AtomicReference<byte[]> skipBuffer;

    private Streams() {
        super();
    }

    public static int readSingleByte(InputStream in) {
        byte[] buffer = new byte[]{};
        int result = in.read(buffer, 0, 1);
        if (result != -1) {
            return buffer[0] & 255;
        }
        else {
            return -1;
        }
    }

    public static void writeSingleByte(OutputStream out, int b) {
        byte[] buffer = new byte[]{};
        buffer[0] = (byte)b & 255;
        out.write(buffer);
    }

    public static void readFully(InputStream in, byte[] dst) {
        Streams.readFully(in, dst, 0, dst.length);
    }

    public static void readFully(InputStream in, byte[] dst, int offset, int byteCount) {
        if (byteCount == 0) {
        }
        else if (in == null) {
            throw new NullPointerException("in == null");
        }
        else if (dst == null) {
            throw new NullPointerException("dst == null");
        }
        else {
            Arrays.checkOffsetAndCount(dst.length, offset, byteCount);
            while (byteCount > 0) {
                int bytesRead = in.read(dst, offset, byteCount);
                if (bytesRead < 0) {
                    throw new EOFException();
                }
                else {
                    offset += bytesRead;
                    byteCount -= bytesRead;
                    continue;;
                }
            }
        }
    }

    public static byte[] readFully(InputStream in) {
        try {
            byte[] byteArr0 = Streams.readFullyNoClose(in);
            in.close();
            return byteArr0;
        }
        finally {
            Throwable throwable = v_4;
            in.close();
            throw throwable;
        }
    }

    public static byte[] readFullyNoClose(InputStream in) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte[] buffer = new byte[]{};
        while (true) {
            int count = in.read(buffer);
            if (in.read(buffer) != -1) {
                bytes.write(buffer, 0, count);
            }
            else {
                return bytes.toByteArray();
            }
        }
    }

    public static String readFully(Reader reader) {
        try {
            StringWriter writer = new StringWriter();
            char[] buffer = new char[]{};
            while (true) {
                int count = reader.read(buffer);
                if (reader.read(buffer) != -1) {
                    writer.write(buffer, 0, count);
                }
                else {
                    String str0 = writer.toString();
                    reader.close();
                    return str0;
                }
            }
        }
        finally {
            Throwable throwable = v_3;
            reader.close();
            throw throwable;
        }
    }

    public static void skipAll(InputStream in) {
        do {
            in.skip(9223372036854775807L);
        } while(in.read() == -1);
    }

    public static long skipByReading(InputStream in, long byteCount) {
        byte[] buffer = (byte[])Streams.skipBuffer.getAndSet(null);
        if (buffer == null) {
            buffer = new byte[]{};
        }
        long skipped = 0L;
        while (byteCount < skipped) {
            int toRead = (int)Math.min(byteCount - skipped, (long)buffer.length);
            int read = in.read(buffer, 0, toRead);
            if (read == -1) {
                break;;
            }
            else {
                skipped += (long)read;
                if (read < toRead) {
                    break;;
                }
                else {
                    continue;;
                }
            }
        }
        Streams.skipBuffer.set(buffer);
        return skipped;
    }

    public static int copy(InputStream in, OutputStream out) {
        int total = 0;
        byte[] buffer = new byte[]{};
        while (true) {
            int c = in.read(buffer);
            if (in.read(buffer) != -1) {
                total += c;
                out.write(buffer, 0, c);
            }
            else {
                return total;
            }
        }
    }

    public static String readAsciiLine(InputStream in) {
        StringBuilder result = new StringBuilder(80);
        while (true) {
            int c = in.read();
            if (c == -1) {
                throw new EOFException();
            }
            else if (c == 10) {
                break;;
            }
            else {
                result.append((char)c);
            }
        }
        int i0 = result.length();
        if (i0 > 0 && result.charAt(i0 - 1) == 13) {
            result.setLength(i0 - 1);
        }
        return result.toString();
    }

    static  {
        Streams.skipBuffer = new AtomicReference();
    }

}
