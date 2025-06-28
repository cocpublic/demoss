/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/e;

import com.tencent.tinker.loader.k;
import com.tencent.tinker.lib.d.c;
import com.tencent.tinker.lib.d.d;
import com.tencent.tinker.lib.d.a;
import com.tencent.tinker.lib.d.b;
import com.tencent.tinker.lib.a.a;
import com.tencent.tinker.lib.b.b;
import com.tencent.tinker.lib.b.a;
import android.content.Context;
import java.io.File;

// class: com/tencent/tinker/lib/e/b
public class b {
    private static b n;
    private static boolean o;
    final Context a;
    final File b;
    final b c;
    final c d;
    final d e;
    final File f;
    final File g;
    final a h;
    final boolean i;
    final boolean j;
    final boolean k;
    int l;
    d m;
    private boolean p;

    private b(Context context, int i0, c c, d d, b b, File file, File fileVar1, File fileVar2, a a, boolean bool0, boolean bool1, boolean bool2) {
        super();
        this.p = false;
        this.a = context;
        this.c = b;
        this.d = c;
        this.e = d;
        this.l = i0;
        this.b = file;
        this.f = fileVar1;
        this.g = fileVar2;
        this.h = a;
        this.i = bool0;
        this.k = bool2;
        this.j = bool1;
    }

    public static b a(Context context) {
        if (b.o) {
            throw new k("you must install tinker before get tinker sInstance");
        }
        else {
            Class class = b.class;
            b.class;
            synchronized () {
                if (b.n == null) {
                    b.n = new b$a(context).a();
                }
            }
            return b.n;
        }
    }

    public static void a(b b) {
        if (b.n != null) {
            throw new k("Tinker instance is already set.");
        }
        else {
            b.n = b;
        }
    }

    public static boolean a() {
        return b.o;
    }

    public void a(Intent intent, Class<? extends a> class, c c) {
        b.o = true;
        TinkerPatchService.a(c, class);
        n.b("Tinker.Tinker", "try to install tinker, isEnable: %b, version: %s", new Object[]{Boolean.valueOf(this.i()), "1.9.14.20(RFix)"});
        if (this.i()) {
            n.d("Tinker.Tinker", "tinker is disabled", new Object[]{});
        }
        else if (intent == null) {
            throw new k("intentResult must not be null.");
        }
        else {
            this.m = new d();
            this.m.a(this.c(), intent);
            this.d.a(this.b, this.m.p, this.m.q);
            if (this.p) {
                n.c("Tinker.Tinker", "tinker load fail!", new Object[]{});
            }
        }
    }

    public d b() {
        return this.m;
    }

    public Context c() {
        return this.a;
    }

    public boolean d() {
        return this.i;
    }

    public boolean e() {
        return this.j;
    }

    public void f() {
        this.l = 0;
    }

    public c g() {
        return this.d;
    }

    public d h() {
        return this.e;
    }

    public boolean i() {
        return m.f(this.l);
    }

    public boolean j() {
        return this.p;
    }

    public void a(boolean bool0) {
        this.p = bool0;
    }

    public boolean k() {
        return m.a(this.l);
    }

    public boolean l() {
        return m.b(this.l);
    }

    public boolean m() {
        return m.c(this.l);
    }

    public File n() {
        return this.b;
    }

    public File o() {
        return this.f;
    }

    public a p() {
        return this.h;
    }

    public b q() {
        return this.c;
    }

    public int r() {
        return this.l;
    }

    public void s() {
        m.m(this.c());
    }

    public void a(String str0) {
        if (this.b == null || str0 == null) {
        }
        else {
            String str1 = new StringBuilder().append(this.b.getAbsolutePath()).append("/").append(str0).toString();
            h.f(str1);
        }
    }

    public void a(File file) {
        if (this.b != null || file != null || file.exists()) {
        }
        else {
            String str0 = h.c(h.f(file));
            this.a(str0);
        }
    }

    /* synthetic */ b(Context context, int bool0, c c, d d, b b, File file, File fileVar1, File fileVar2, a a, boolean bool1, boolean bool2, boolean bool3, b$1 b$1) {
        super(context, bool0, c, d, b, file, fileVar1, fileVar2, a, bool1, bool2, bool3);
    }

    static  {
        b.o = false;
    }

    // class: com/tencent/tinker/lib/e/b$a
    public class b$a {
        final private Context a;
        final private boolean b;
        final private boolean c;
        private int d;
        private c e;
        private d f;
        private b g;
        private a h;
        private File i;
        private File j;
        private File k;
        private Boolean l;

        public b$a(Context context) {
            super();
            this.d = -1;
            if (context == null) {
                throw new k("Context must not be null.");
            }
            else {
                this.a = context;
                this.b = a.i(context);
                this.c = a.d(context);
                this.i = h.a(context);
                if (this.i == null) {
                    n.d("Tinker.Tinker", "patchDirectory is null!", new Object[]{});
                }
                else {
                    this.j = h.a(this.i.getAbsolutePath());
                    this.k = h.b(this.i.getAbsolutePath());
                    n.c("Tinker.Tinker", "tinker patch directory: %s", new Object[]{this.i});
                }
            }
        }

        public b$a a(int i0) {
            if (this.d != -1) {
                throw new k("tinkerFlag is already set.");
            }
            else {
                this.d = i0;
                return this;
            }
        }

        public b$a a(Boolean boolean) {
            if (boolean == null) {
                throw new k("tinkerLoadVerifyFlag must not be null.");
            }
            else if (this.l != null) {
                throw new k("tinkerLoadVerifyFlag is already set.");
            }
            else {
                this.l = boolean;
                return this;
            }
        }

        public b$a a(d d) {
            if (d == null) {
                throw new k("patchReporter must not be null.");
            }
            else if (this.f != null) {
                throw new k("patchReporter is already set.");
            }
            else {
                this.f = d;
                return this;
            }
        }

        public b$a a(b b) {
            if (b == null) {
                throw new k("listener must not be null.");
            }
            else if (this.g != null) {
                throw new k("listener is already set.");
            }
            else {
                this.g = b;
                return this;
            }
        }

        public b a() {
            if (this.d == -1) {
                this.d = 15;
            }
            if (this.e == null) {
                this.e = new a(this.a);
            }
            if (this.f == null) {
                this.f = new b(this.a);
            }
            if (this.g == null) {
                this.g = new a(this.a);
            }
            if (this.l == null) {
                this.l = Boolean.valueOf(false);
            }
            return new b(this.a, this.d, this.e, this.f, this.g, this.i, this.j, this.k, this.h, this.b, this.c, this.l.booleanValue(), null);
        }

    }
    // class: com/tencent/tinker/lib/e/b$a
    public class b$a {
        final private Context a;
        final private boolean b;
        final private boolean c;
        private int d;
        private c e;
        private d f;
        private b g;
        private a h;
        private File i;
        private File j;
        private File k;
        private Boolean l;

        public b$a(Context context) {
            super();
            this.d = -1;
            if (context == null) {
                throw new k("Context must not be null.");
            }
            else {
                this.a = context;
                this.b = a.i(context);
                this.c = a.d(context);
                this.i = h.a(context);
                if (this.i == null) {
                    n.d("Tinker.Tinker", "patchDirectory is null!", new Object[]{});
                }
                else {
                    this.j = h.a(this.i.getAbsolutePath());
                    this.k = h.b(this.i.getAbsolutePath());
                    n.c("Tinker.Tinker", "tinker patch directory: %s", new Object[]{this.i});
                }
            }
        }

        public b$a a(int i0) {
            if (this.d != -1) {
                throw new k("tinkerFlag is already set.");
            }
            else {
                this.d = i0;
                return this;
            }
        }

        public b$a a(Boolean boolean) {
            if (boolean == null) {
                throw new k("tinkerLoadVerifyFlag must not be null.");
            }
            else if (this.l != null) {
                throw new k("tinkerLoadVerifyFlag is already set.");
            }
            else {
                this.l = boolean;
                return this;
            }
        }

        public b$a a(d d) {
            if (d == null) {
                throw new k("patchReporter must not be null.");
            }
            else if (this.f != null) {
                throw new k("patchReporter is already set.");
            }
            else {
                this.f = d;
                return this;
            }
        }

        public b$a a(b b) {
            if (b == null) {
                throw new k("listener must not be null.");
            }
            else if (this.g != null) {
                throw new k("listener is already set.");
            }
            else {
                this.g = b;
                return this;
            }
        }

        public b a() {
            if (this.d == -1) {
                this.d = 15;
            }
            if (this.e == null) {
                this.e = new a(this.a);
            }
            if (this.f == null) {
                this.f = new b(this.a);
            }
            if (this.g == null) {
                this.g = new a(this.a);
            }
            if (this.l == null) {
                this.l = Boolean.valueOf(false);
            }
            return new b(this.a, this.d, this.e, this.f, this.g, this.i, this.j, this.k, this.h, this.b, this.c, this.l.booleanValue(), null);
        }

    }
}
