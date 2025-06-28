package com.tencent.tinker.loader;

/**
 * Custom runtime exception used throughout the Tinker loader process.
 * It prefixes messages with "Tinker Exception:" for easy identification.
 *
 * Note: This class was translated from a decompiled and obfuscated class `k.java`.
 */
public class TinkerRuntimeException extends RuntimeException {

    private static final String TINKER_EXCEPTION_PREFIX = "Tinker Exception:";

    public TinkerRuntimeException(String message) {
        super(TINKER_EXCEPTION_PREFIX + (message == null ? "" : message));
    }

    public TinkerRuntimeException(String message, Throwable cause) {
        super(TINKER_EXCEPTION_PREFIX + (message == null ? "" : message), cause);
    }
}
