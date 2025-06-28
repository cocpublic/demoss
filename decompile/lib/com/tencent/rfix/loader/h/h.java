/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/h;


// class: com/tencent/rfix/loader/h/h
public class h {
    final public static char a;

    static  {
        h.a = new char[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    }

    // class: com/tencent/rfix/loader/h/h$b
    public final enum h$b {
        final private static synthetic h$b[] p;

        privatevoid h$b(String str0, int i0) {
        }

        static  {
            h$b.a = new h$b("PATCH_RESULT_OK", 0);
            h$b.b = new h$b("PATCH_RESULT_APPLYING", 1);
            h$b.c = new h$b("PATCH_RESULT_NOT_EXIST", 2);
            h$b.d = new h$b("PATCH_RESULT_MD5_INVALID", 3);
            h$b.e = new h$b("PATCH_RESULT_SAFE_MODE_INTERCEPT", 4);
            h$b.f = new h$b("PATCH_RESULT_RETRY_LIMIT", 5);
            h$b.g = new h$b("PATCH_RESULT_ALREADY_APPLY", 6);
            h$b.h = new h$b("PATCH_RESULT_SIGN_NOT_MATCH", 7);
            h$b.i = new h$b("PATCH_RESULT_TYPE_INVALID", 8);
            h$b.j = new h$b("PATCH_RESULT_ID_INVALID", 9);
            h$b.k = new h$b("PATCH_RESULT_COPY_PATCH_FAIL", 10);
            h$b.l = new h$b("PATCH_RESULT_NO_INSTALLER_SUPPORT", 11);
            h$b.m = new h$b("PATCH_RESULT_INSTALL_FAIL", 12);
            h$b.n = new h$b("PATCH_RESULT_WRITE_PATCH_INFO_FAIL", 13);
            h$b.o = new h$b("PATCH_RESULT_UNKNOWN_EXCEPTION", 14);
            h$b.p = new h$b[]{h$b.a, h$b.b, h$b.c, h$b.d, h$b.e, h$b.f, h$b.g, h$b.h, h$b.i, h$b.j, h$b.k, h$b.l, h$b.m, h$b.n, h$b.o};
        }

    }
    // class: com/tencent/rfix/loader/h/h$b
    public final enum h$b {
        final private static synthetic h$b[] p;

        privatevoid h$b(String str0, int i0) {
        }

        static  {
            h$b.a = new h$b("PATCH_RESULT_OK", 0);
            h$b.b = new h$b("PATCH_RESULT_APPLYING", 1);
            h$b.c = new h$b("PATCH_RESULT_NOT_EXIST", 2);
            h$b.d = new h$b("PATCH_RESULT_MD5_INVALID", 3);
            h$b.e = new h$b("PATCH_RESULT_SAFE_MODE_INTERCEPT", 4);
            h$b.f = new h$b("PATCH_RESULT_RETRY_LIMIT", 5);
            h$b.g = new h$b("PATCH_RESULT_ALREADY_APPLY", 6);
            h$b.h = new h$b("PATCH_RESULT_SIGN_NOT_MATCH", 7);
            h$b.i = new h$b("PATCH_RESULT_TYPE_INVALID", 8);
            h$b.j = new h$b("PATCH_RESULT_ID_INVALID", 9);
            h$b.k = new h$b("PATCH_RESULT_COPY_PATCH_FAIL", 10);
            h$b.l = new h$b("PATCH_RESULT_NO_INSTALLER_SUPPORT", 11);
            h$b.m = new h$b("PATCH_RESULT_INSTALL_FAIL", 12);
            h$b.n = new h$b("PATCH_RESULT_WRITE_PATCH_INFO_FAIL", 13);
            h$b.o = new h$b("PATCH_RESULT_UNKNOWN_EXCEPTION", 14);
            h$b.p = new h$b[]{h$b.a, h$b.b, h$b.c, h$b.d, h$b.e, h$b.f, h$b.g, h$b.h, h$b.i, h$b.j, h$b.k, h$b.l, h$b.m, h$b.n, h$b.o};
        }

    }
    // class: com/tencent/rfix/loader/h/h$a
    public final enum h$a {
        final private boolean n;
        final private static synthetic h$a[] o;

        privatevoid h$a(String str0, int i0) {
        }

        privatevoid h$a(String str0, int i0, boolean bool0) {
            this.n = bool0;
        }

        public boolean a() {
            return this.n;
        }

        static  {
            h$a.a = new h$a("LOAD_RESULT_SUCCESS", 0);
            h$a.b = new h$a("LOAD_RESULT_PATCH_INFO_BLANK", 1, 1);
            h$a.c = new h$a("LOAD_RESULT_PATCH_DOWNLOAD_FAIL", 2, 1);
            h$a.d = new h$a("LOAD_RESULT_PATCH_INSTALL_FAIL", 3, 1);
            h$a.e = new h$a("LOAD_RESULT_PATCH_REMOVED", 4, 1);
            h$a.f = new h$a("LOAD_RESULT_PROCESS_DISABLE", 5, 1);
            h$a.g = new h$a("LOAD_RESULT_REMOTE_VERIFY_FAIL", 6, 1);
            h$a.h = new h$a("LOAD_RESULT_MAIN_UNVERIFIED", 7, 1);
            h$a.i = new h$a("LOAD_RESULT_PATCH_VERSION_NOT_EXIST", 8);
            h$a.j = new h$a("LOAD_RESULT_SAFE_MODE_CHECK", 9);
            h$a.k = new h$a("LOAD_RESULT_NO_LOADER_SUPPORT", 10);
            h$a.l = new h$a("LOAD_RESULT_PATCH_LOADER_FAIL", 11);
            h$a.m = new h$a("LOAD_RESULT_UNKNOWN_EXCEPTION", 12);
            h$a.o = new h$a[]{h$a.a, h$a.b, h$a.c, h$a.d, h$a.e, h$a.f, h$a.g, h$a.h, h$a.i, h$a.j, h$a.k, h$a.l, h$a.m};
        }

    }
    // class: com/tencent/rfix/loader/h/h$a
    public final enum h$a {
        final private boolean n;
        final private static synthetic h$a[] o;

        privatevoid h$a(String str0, int i0) {
        }

        privatevoid h$a(String str0, int i0, boolean bool0) {
            this.n = bool0;
        }

        public boolean a() {
            return this.n;
        }

        static  {
            h$a.a = new h$a("LOAD_RESULT_SUCCESS", 0);
            h$a.b = new h$a("LOAD_RESULT_PATCH_INFO_BLANK", 1, 1);
            h$a.c = new h$a("LOAD_RESULT_PATCH_DOWNLOAD_FAIL", 2, 1);
            h$a.d = new h$a("LOAD_RESULT_PATCH_INSTALL_FAIL", 3, 1);
            h$a.e = new h$a("LOAD_RESULT_PATCH_REMOVED", 4, 1);
            h$a.f = new h$a("LOAD_RESULT_PROCESS_DISABLE", 5, 1);
            h$a.g = new h$a("LOAD_RESULT_REMOTE_VERIFY_FAIL", 6, 1);
            h$a.h = new h$a("LOAD_RESULT_MAIN_UNVERIFIED", 7, 1);
            h$a.i = new h$a("LOAD_RESULT_PATCH_VERSION_NOT_EXIST", 8);
            h$a.j = new h$a("LOAD_RESULT_SAFE_MODE_CHECK", 9);
            h$a.k = new h$a("LOAD_RESULT_NO_LOADER_SUPPORT", 10);
            h$a.l = new h$a("LOAD_RESULT_PATCH_LOADER_FAIL", 11);
            h$a.m = new h$a("LOAD_RESULT_UNKNOWN_EXCEPTION", 12);
            h$a.o = new h$a[]{h$a.a, h$a.b, h$a.c, h$a.d, h$a.e, h$a.f, h$a.g, h$a.h, h$a.i, h$a.j, h$a.k, h$a.l, h$a.m};
        }

    }
}
