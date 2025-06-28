/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/verify;


// class: com/tencent/rfix/lib/verify/b
public class b {
    public boolean a;
    final public b$a b;
    final public b$a c;
    final public b$a d;

    public b() {
        super();
        this.b = new b$a();
        this.c = new b$a();
        this.d = new b$a();
    }

    public String toString() {
        return new StringBuilder().append("AutoVerifyResult{success=").append(this.a).append(", dex=").append(this.b).append(", lib=").append(this.c).append(", res=").append(this.d).append(125).toString();
    }

    // class: com/tencent/rfix/lib/verify/b$a
    public class b$a {
        public boolean a;
        public boolean b;
        public Object c;

        public b$a() {
            super();
        }

        public String toString() {
            return new StringBuilder().append("VerifyItem{enable=").append(this.a).append(", result=").append(this.b).append(", data=").append(this.c).append(125).toString();
        }

    }
    // class: com/tencent/rfix/lib/verify/b$a
    public class b$a {
        public boolean a;
        public boolean b;
        public Object c;

        public b$a() {
            super();
        }

        public String toString() {
            return new StringBuilder().append("VerifyItem{enable=").append(this.a).append(", result=").append(this.b).append(", data=").append(this.c).append(125).toString();
        }

    }
}
