/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;


// class: com/tencent/tinker/build/aapt/Constant
public interface Constant {

    // class: com/tencent/tinker/build/aapt/Constant$Timezone
    public interface Constant$Timezone {
        final public static String ASIA_SHANGHAI;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Timezone
    public interface Constant$Timezone {
        final public static String ASIA_SHANGHAI;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Method
    public interface Constant$Method {
        final public static String PREFIX_SET;
        final public static String PREFIX_GET;
        final public static String PREFIX_IS;
        final public static String GET_CLASS;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Method
    public interface Constant$Method {
        final public static String PREFIX_SET;
        final public static String PREFIX_GET;
        final public static String PREFIX_IS;
        final public static String GET_CLASS;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Protocol
    public interface Constant$Protocol {
        final public static String FILE;
        final public static String HTTP;
        final public static String FTP;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Protocol
    public interface Constant$Protocol {
        final public static String FILE;
        final public static String HTTP;
        final public static String FTP;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Symbol
    public interface Constant$Symbol {
        final public static String DOT;
        final public static char DOT_CHAR;
        final public static String COMMA;
        final public static String COLON;
        final public static String SEMICOLON;
        final public static String EQUAL;
        final public static String AND;
        final public static String QUESTION_MARK;
        final public static String WILDCARD;
        final public static String UNDERLINE;
        final public static String AT;
        final public static String MINUS;
        final public static String LOGIC_AND;
        final public static String LOGIC_OR;
        final public static String BRACKET_LEFT;
        final public static String BRACKET_RIGHT;
        final public static String MIDDLE_BRACKET_LEFT;
        final public static String MIDDLE_BRACKET_RIGHT;
        final public static String BIG_BRACKET_LEFT;
        final public static String BIG_BRACKET_RIGHT;
        final public static String SLASH_LEFT;
        final public static String SLASH_RIGHT;
        final public static String XOR;
        final public static String DOLLAR;
        final public static String SINGLE_QUOTES;
        final public static String DOUBLE_QUOTES;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Symbol
    public interface Constant$Symbol {
        final public static String DOT;
        final public static char DOT_CHAR;
        final public static String COMMA;
        final public static String COLON;
        final public static String SEMICOLON;
        final public static String EQUAL;
        final public static String AND;
        final public static String QUESTION_MARK;
        final public static String WILDCARD;
        final public static String UNDERLINE;
        final public static String AT;
        final public static String MINUS;
        final public static String LOGIC_AND;
        final public static String LOGIC_OR;
        final public static String BRACKET_LEFT;
        final public static String BRACKET_RIGHT;
        final public static String MIDDLE_BRACKET_LEFT;
        final public static String MIDDLE_BRACKET_RIGHT;
        final public static String BIG_BRACKET_LEFT;
        final public static String BIG_BRACKET_RIGHT;
        final public static String SLASH_LEFT;
        final public static String SLASH_RIGHT;
        final public static String XOR;
        final public static String DOLLAR;
        final public static String SINGLE_QUOTES;
        final public static String DOUBLE_QUOTES;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Encoding
    public interface Constant$Encoding {
        final public static String ISO88591;
        final public static String GB2312;
        final public static String GBK;
        final public static String UTF8;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Encoding
    public interface Constant$Encoding {
        final public static String ISO88591;
        final public static String GB2312;
        final public static String GBK;
        final public static String UTF8;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Http
    public interface Constant$Http {

        // class: com/tencent/tinker/build/aapt/Constant$Http$StatusCode
        public interface Constant$Http$StatusCode {
            final public static int CONTINUE;
            final public static int SWITCHING_PROTOCOLS;
            final public static int PROCESSING;
            final public static int OK;
            final public static int CREATED;
            final public static int ACCEPTED;
            final public static int NON_AUTHORITATIVE_INFORMATION;
            final public static int NO_CONTENT;
            final public static int RESET_CONTENT;
            final public static int PARTIAL_CONTENT;
            final public static int MULTI_STATUS;
            final public static int MULTIPLE_CHOICES;
            final public static int MOVED_PERMANENTLY;
            final public static int FOUND;
            final public static int SEE_OTHER;
            final public static int NOT_MODIFIED;
            final public static int USE_PROXY;
            final public static int SWITCH_PROXY;
            final public static int TEMPORARY_REDIRECT;
            final public static int BAD_REQUEST;
            final public static int UNAUTHORIZED;
            final public static int PAYMENT_REQUIRED;
            final public static int FORBIDDEN;
            final public static int NOT_FOUND;
            final public static int METHOD_NOT_ALLOWED;
            final public static int NOT_ACCEPTABLE;
            final public static int REQUEST_TIMEOUT;
            final public static int CONFLICT;
            final public static int GONE;
            final public static int LENGTH_REQUIRED;
            final public static int PRECONDITION_FAILED;
            final public static int REQUEST_URI_TOO_LONG;
            final public static int EXPECTATION_FAILED;
            final public static int TOO_MANY_CONNECTIONS;
            final public static int UNPROCESSABLE_ENTITY;
            final public static int LOCKED;
            final public static int FAILED_DEPENDENCY;
            final public static int UNORDERED_COLLECTION;
            final public static int UPGRADE_REQUIRED;
            final public static int RETRY_WITH;
            final public static int INTERNAL_SERVER_ERROR;
            final public static int NOT_IMPLEMENTED;
            final public static int BAD_GATEWAY;
            final public static int SERVICE_UNAVAILABLE;
            final public static int GATEWAY_TIMEOUT;
            final public static int HTTP_VERSION_NOT_SUPPORTED;
            final public static int VARIANT_ALSO_NEGOTIATES;
            final public static int INSUFFICIENT_STORAGE;
            final public static int LOOP_DETECTED;
            final public static int BANDWIDTH_LIMIT_EXCEEDED;
            final public static int NOT_EXTENDED;
            final public static int UNPARSEABLE_RESPONSE_HEADERS;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$StatusCode
        public interface Constant$Http$StatusCode {
            final public static int CONTINUE;
            final public static int SWITCHING_PROTOCOLS;
            final public static int PROCESSING;
            final public static int OK;
            final public static int CREATED;
            final public static int ACCEPTED;
            final public static int NON_AUTHORITATIVE_INFORMATION;
            final public static int NO_CONTENT;
            final public static int RESET_CONTENT;
            final public static int PARTIAL_CONTENT;
            final public static int MULTI_STATUS;
            final public static int MULTIPLE_CHOICES;
            final public static int MOVED_PERMANENTLY;
            final public static int FOUND;
            final public static int SEE_OTHER;
            final public static int NOT_MODIFIED;
            final public static int USE_PROXY;
            final public static int SWITCH_PROXY;
            final public static int TEMPORARY_REDIRECT;
            final public static int BAD_REQUEST;
            final public static int UNAUTHORIZED;
            final public static int PAYMENT_REQUIRED;
            final public static int FORBIDDEN;
            final public static int NOT_FOUND;
            final public static int METHOD_NOT_ALLOWED;
            final public static int NOT_ACCEPTABLE;
            final public static int REQUEST_TIMEOUT;
            final public static int CONFLICT;
            final public static int GONE;
            final public static int LENGTH_REQUIRED;
            final public static int PRECONDITION_FAILED;
            final public static int REQUEST_URI_TOO_LONG;
            final public static int EXPECTATION_FAILED;
            final public static int TOO_MANY_CONNECTIONS;
            final public static int UNPROCESSABLE_ENTITY;
            final public static int LOCKED;
            final public static int FAILED_DEPENDENCY;
            final public static int UNORDERED_COLLECTION;
            final public static int UPGRADE_REQUIRED;
            final public static int RETRY_WITH;
            final public static int INTERNAL_SERVER_ERROR;
            final public static int NOT_IMPLEMENTED;
            final public static int BAD_GATEWAY;
            final public static int SERVICE_UNAVAILABLE;
            final public static int GATEWAY_TIMEOUT;
            final public static int HTTP_VERSION_NOT_SUPPORTED;
            final public static int VARIANT_ALSO_NEGOTIATES;
            final public static int INSUFFICIENT_STORAGE;
            final public static int LOOP_DETECTED;
            final public static int BANDWIDTH_LIMIT_EXCEEDED;
            final public static int NOT_EXTENDED;
            final public static int UNPARSEABLE_RESPONSE_HEADERS;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$ContentType
        public interface Constant$Http$ContentType {
            final public static String TEXT_PLAIN;
            final public static String APPLICATION_X_DOWNLOAD;
            final public static String APPLICATION_ANDROID_PACKAGE;
            final public static String MULTIPART_FORM_DATA;
            final public static String APPLICATION_OCTET_STREAM;
            final public static String BINARY_OCTET_STREAM;
            final public static String APPLICATION_X_WWW_FORM_URLENCODED;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$ContentType
        public interface Constant$Http$ContentType {
            final public static String TEXT_PLAIN;
            final public static String APPLICATION_X_DOWNLOAD;
            final public static String APPLICATION_ANDROID_PACKAGE;
            final public static String MULTIPART_FORM_DATA;
            final public static String APPLICATION_OCTET_STREAM;
            final public static String BINARY_OCTET_STREAM;
            final public static String APPLICATION_X_WWW_FORM_URLENCODED;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$RequestMethod
        public interface Constant$Http$RequestMethod {
            final public static String PUT;
            final public static String DELETE;
            final public static String GET;
            final public static String POST;
            final public static String HEAD;
            final public static String OPTIONS;
            final public static String TRACE;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$RequestMethod
        public interface Constant$Http$RequestMethod {
            final public static String PUT;
            final public static String DELETE;
            final public static String GET;
            final public static String POST;
            final public static String HEAD;
            final public static String OPTIONS;
            final public static String TRACE;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$HeaderKey
        public interface Constant$Http$HeaderKey {
            final public static String CONTENT_TYPE;
            final public static String CONTENT_DISPOSITION;
            final public static String ACCEPT_CHARSET;
            final public static String CONTENT_ENCODING;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$HeaderKey
        public interface Constant$Http$HeaderKey {
            final public static String CONTENT_TYPE;
            final public static String CONTENT_DISPOSITION;
            final public static String ACCEPT_CHARSET;
            final public static String CONTENT_ENCODING;

        }
    }
    // class: com/tencent/tinker/build/aapt/Constant$Http
    public interface Constant$Http {

        // class: com/tencent/tinker/build/aapt/Constant$Http$StatusCode
        public interface Constant$Http$StatusCode {
            final public static int CONTINUE;
            final public static int SWITCHING_PROTOCOLS;
            final public static int PROCESSING;
            final public static int OK;
            final public static int CREATED;
            final public static int ACCEPTED;
            final public static int NON_AUTHORITATIVE_INFORMATION;
            final public static int NO_CONTENT;
            final public static int RESET_CONTENT;
            final public static int PARTIAL_CONTENT;
            final public static int MULTI_STATUS;
            final public static int MULTIPLE_CHOICES;
            final public static int MOVED_PERMANENTLY;
            final public static int FOUND;
            final public static int SEE_OTHER;
            final public static int NOT_MODIFIED;
            final public static int USE_PROXY;
            final public static int SWITCH_PROXY;
            final public static int TEMPORARY_REDIRECT;
            final public static int BAD_REQUEST;
            final public static int UNAUTHORIZED;
            final public static int PAYMENT_REQUIRED;
            final public static int FORBIDDEN;
            final public static int NOT_FOUND;
            final public static int METHOD_NOT_ALLOWED;
            final public static int NOT_ACCEPTABLE;
            final public static int REQUEST_TIMEOUT;
            final public static int CONFLICT;
            final public static int GONE;
            final public static int LENGTH_REQUIRED;
            final public static int PRECONDITION_FAILED;
            final public static int REQUEST_URI_TOO_LONG;
            final public static int EXPECTATION_FAILED;
            final public static int TOO_MANY_CONNECTIONS;
            final public static int UNPROCESSABLE_ENTITY;
            final public static int LOCKED;
            final public static int FAILED_DEPENDENCY;
            final public static int UNORDERED_COLLECTION;
            final public static int UPGRADE_REQUIRED;
            final public static int RETRY_WITH;
            final public static int INTERNAL_SERVER_ERROR;
            final public static int NOT_IMPLEMENTED;
            final public static int BAD_GATEWAY;
            final public static int SERVICE_UNAVAILABLE;
            final public static int GATEWAY_TIMEOUT;
            final public static int HTTP_VERSION_NOT_SUPPORTED;
            final public static int VARIANT_ALSO_NEGOTIATES;
            final public static int INSUFFICIENT_STORAGE;
            final public static int LOOP_DETECTED;
            final public static int BANDWIDTH_LIMIT_EXCEEDED;
            final public static int NOT_EXTENDED;
            final public static int UNPARSEABLE_RESPONSE_HEADERS;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$StatusCode
        public interface Constant$Http$StatusCode {
            final public static int CONTINUE;
            final public static int SWITCHING_PROTOCOLS;
            final public static int PROCESSING;
            final public static int OK;
            final public static int CREATED;
            final public static int ACCEPTED;
            final public static int NON_AUTHORITATIVE_INFORMATION;
            final public static int NO_CONTENT;
            final public static int RESET_CONTENT;
            final public static int PARTIAL_CONTENT;
            final public static int MULTI_STATUS;
            final public static int MULTIPLE_CHOICES;
            final public static int MOVED_PERMANENTLY;
            final public static int FOUND;
            final public static int SEE_OTHER;
            final public static int NOT_MODIFIED;
            final public static int USE_PROXY;
            final public static int SWITCH_PROXY;
            final public static int TEMPORARY_REDIRECT;
            final public static int BAD_REQUEST;
            final public static int UNAUTHORIZED;
            final public static int PAYMENT_REQUIRED;
            final public static int FORBIDDEN;
            final public static int NOT_FOUND;
            final public static int METHOD_NOT_ALLOWED;
            final public static int NOT_ACCEPTABLE;
            final public static int REQUEST_TIMEOUT;
            final public static int CONFLICT;
            final public static int GONE;
            final public static int LENGTH_REQUIRED;
            final public static int PRECONDITION_FAILED;
            final public static int REQUEST_URI_TOO_LONG;
            final public static int EXPECTATION_FAILED;
            final public static int TOO_MANY_CONNECTIONS;
            final public static int UNPROCESSABLE_ENTITY;
            final public static int LOCKED;
            final public static int FAILED_DEPENDENCY;
            final public static int UNORDERED_COLLECTION;
            final public static int UPGRADE_REQUIRED;
            final public static int RETRY_WITH;
            final public static int INTERNAL_SERVER_ERROR;
            final public static int NOT_IMPLEMENTED;
            final public static int BAD_GATEWAY;
            final public static int SERVICE_UNAVAILABLE;
            final public static int GATEWAY_TIMEOUT;
            final public static int HTTP_VERSION_NOT_SUPPORTED;
            final public static int VARIANT_ALSO_NEGOTIATES;
            final public static int INSUFFICIENT_STORAGE;
            final public static int LOOP_DETECTED;
            final public static int BANDWIDTH_LIMIT_EXCEEDED;
            final public static int NOT_EXTENDED;
            final public static int UNPARSEABLE_RESPONSE_HEADERS;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$ContentType
        public interface Constant$Http$ContentType {
            final public static String TEXT_PLAIN;
            final public static String APPLICATION_X_DOWNLOAD;
            final public static String APPLICATION_ANDROID_PACKAGE;
            final public static String MULTIPART_FORM_DATA;
            final public static String APPLICATION_OCTET_STREAM;
            final public static String BINARY_OCTET_STREAM;
            final public static String APPLICATION_X_WWW_FORM_URLENCODED;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$ContentType
        public interface Constant$Http$ContentType {
            final public static String TEXT_PLAIN;
            final public static String APPLICATION_X_DOWNLOAD;
            final public static String APPLICATION_ANDROID_PACKAGE;
            final public static String MULTIPART_FORM_DATA;
            final public static String APPLICATION_OCTET_STREAM;
            final public static String BINARY_OCTET_STREAM;
            final public static String APPLICATION_X_WWW_FORM_URLENCODED;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$RequestMethod
        public interface Constant$Http$RequestMethod {
            final public static String PUT;
            final public static String DELETE;
            final public static String GET;
            final public static String POST;
            final public static String HEAD;
            final public static String OPTIONS;
            final public static String TRACE;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$RequestMethod
        public interface Constant$Http$RequestMethod {
            final public static String PUT;
            final public static String DELETE;
            final public static String GET;
            final public static String POST;
            final public static String HEAD;
            final public static String OPTIONS;
            final public static String TRACE;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$HeaderKey
        public interface Constant$Http$HeaderKey {
            final public static String CONTENT_TYPE;
            final public static String CONTENT_DISPOSITION;
            final public static String ACCEPT_CHARSET;
            final public static String CONTENT_ENCODING;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Http$HeaderKey
        public interface Constant$Http$HeaderKey {
            final public static String CONTENT_TYPE;
            final public static String CONTENT_DISPOSITION;
            final public static String ACCEPT_CHARSET;
            final public static String CONTENT_ENCODING;

        }
    }
    // class: com/tencent/tinker/build/aapt/Constant$Base
    public interface Constant$Base {
        final public static String EXCEPTION;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Base
    public interface Constant$Base {
        final public static String EXCEPTION;

    }
    // class: com/tencent/tinker/build/aapt/Constant$RequestScope
    public interface Constant$RequestScope {
        final public static String SESSION;

    }
    // class: com/tencent/tinker/build/aapt/Constant$RequestScope
    public interface Constant$RequestScope {
        final public static String SESSION;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Capacity
    public interface Constant$Capacity {
        final public static int BYTES_PER_KB;
        final public static int BYTES_PER_MB;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Capacity
    public interface Constant$Capacity {
        final public static int BYTES_PER_KB;
        final public static int BYTES_PER_MB;

    }
    // class: com/tencent/tinker/build/aapt/Constant$File
    public interface Constant$File {
        final public static String CLASS;
        final public static String JPEG;
        final public static String JPG;
        final public static String GIF;
        final public static String JAR;
        final public static String JAVA;
        final public static String EXE;
        final public static String DEX;
        final public static String AIDL;
        final public static String SO;
        final public static String XML;
        final public static String CSV;
        final public static String TXT;
        final public static String APK;

    }
    // class: com/tencent/tinker/build/aapt/Constant$File
    public interface Constant$File {
        final public static String CLASS;
        final public static String JPEG;
        final public static String JPG;
        final public static String GIF;
        final public static String JAR;
        final public static String JAVA;
        final public static String EXE;
        final public static String DEX;
        final public static String AIDL;
        final public static String SO;
        final public static String XML;
        final public static String CSV;
        final public static String TXT;
        final public static String APK;

    }
    // class: com/tencent/tinker/build/aapt/Constant$Database
    public interface Constant$Database {
        final public static String COLUMN_NAME_TOTAL;

        // class: com/tencent/tinker/build/aapt/Constant$Database$MySql
        public interface Constant$Database$MySql {
            final public static String PAGINATION;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Database$MySql
        public interface Constant$Database$MySql {
            final public static String PAGINATION;

        }
    }
    // class: com/tencent/tinker/build/aapt/Constant$Database
    public interface Constant$Database {
        final public static String COLUMN_NAME_TOTAL;

        // class: com/tencent/tinker/build/aapt/Constant$Database$MySql
        public interface Constant$Database$MySql {
            final public static String PAGINATION;

        }
        // class: com/tencent/tinker/build/aapt/Constant$Database$MySql
        public interface Constant$Database$MySql {
            final public static String PAGINATION;

        }
    }
    // class: com/tencent/tinker/build/aapt/Constant$RequestParameter
    public interface Constant$RequestParameter {
        final public static String RETURN_URL;

    }
    // class: com/tencent/tinker/build/aapt/Constant$RequestParameter
    public interface Constant$RequestParameter {
        final public static String RETURN_URL;

    }
}
