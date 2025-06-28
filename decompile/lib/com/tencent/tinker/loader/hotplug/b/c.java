/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/hotplug/b;


// class: com/tencent/tinker/loader/hotplug/b/c
public abstract class c<T_TARGET> {
    private T_TARGET a;
    private volatile boolean b;

    public c() {
        super();
        this.a = null;
        this.b = false;
    }

    T_TARGET b();

    protected T_TARGET b(T_TARGET object) {
        return object;
    }

    void a(T_TARGET p0);

    public void c() {
        try {
            Object object = this.b();
            this.a = object;
            Object objectVar1 = this.b(object);
            if (objectVar1 != object) {
                this.a(objectVar1);
            }
            else {
                n.c("Tinker.Interceptor", new StringBuilder().append("target: ").append(object).append(" was already hooked.").toString(), new Object[]{});
            }
            this.b = true;
            return;
        }
        catch (Throwable var_1_1) {
            this.a = null;
            throw new b(var_1_1);
        }
    }

    public void d() {
        if (this.b) {
            try {
                this.a(this.a);
                this.a = null;
                this.b = false;
            }
            catch (Throwable var_1_0) {
                throw new b(var_1_0);
            }
        }
        else {
        }
    }

    // class: com/tencent/tinker/loader/hotplug/b/c$a
    public interface c$a {

    }
    // class: com/tencent/tinker/loader/hotplug/b/c$a
    public interface c$a {

    }
}
