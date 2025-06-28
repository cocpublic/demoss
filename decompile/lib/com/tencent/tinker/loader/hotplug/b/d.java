/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/hotplug/b;

import android.content.ContextWrapper;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

// class: com/tencent/tinker/loader/hotplug/b/d
public class d {
    final private Context a;
    final private String b;
    final private d$a c;
    private static Class<?> d;
    private static Field e;
    private static Method f;

    public d(Context context, String str0, d$a d$a) {
        super();
        while (true) {
            if (context != null && (context instanceof ContextWrapper)) {
                context = (ContextWrapper)context.getBaseContext();
                continue;;
            }
            else {
                this.a = context;
                this.b = str0;
                this.c = d$a;
            }
        }
    }

    protected IBinder a() {
        return (IBinder)d.f.invoke(null, new Object[]{this.b});
    }

    protected IBinder a(IBinder binder) {
        if (binder == null) {
            throw new IllegalStateException("target is null.");
        }
        else if (c$a.class.isAssignableFrom(binder.getClass())) {
            return binder;
        }
        else {
            return (IBinder)d.b(d.b(binder.getClass()), new d$b(binder, this.c));
        }
    }

    protected void b(IBinder binder) {
        Map map = (Map)d.e.get(null);
        map.put(this.b, binder);
        if ("activity".equals(this.b)) {
            d.c(binder);
        }
        else {
            if ("package".equals(this.b)) {
                d.a(this.a, binder);
            }
        }
    }

    private static void c(IBinder binder) {
        Object objectVar2 = null;
        try {
            Class class = Class.forName("android.app.ActivityManagerNative");
            Field field = j.a(class, "gDefault");
            objectVar2 = field.get(null);
        }
        catch (Throwable var_2_2) {
            Class classVar1 = Class.forName("android.app.ActivityManager");
            Field fieldVar1 = j.a(classVar1, "IActivityManagerSingleton");
            objectVar2 = fieldVar1.get(null);
        }
        Field fieldVar2 = j.a(objectVar2, "mInstance");
        IInterface interface = (IInterface)fieldVar2.get(objectVar2);
        if (interface == null || c$a.class.isAssignableFrom(interface.getClass())) {
        }
        else {
            IInterface interfaceVar1 = binder.queryLocalInterface(binder.getInterfaceDescriptor());
            if (interfaceVar1 == null || c$a.class.isAssignableFrom(interfaceVar1.getClass())) {
                throw new IllegalStateException(new StringBuilder().append("fakeBinder does not return fakeInterface, binder: ").append(binder).append(", itf: ").append(interfaceVar1).toString());
            }
            else {
                fieldVar2.set(objectVar2, interfaceVar1);
            }
        }
    }

    private static void a(Context context, IBinder binder) {
        Class class = Class.forName("android.app.ActivityThread");
        Field field = j.a(class, "sPackageManager");
        IInterface interface = (IInterface)field.get(null);
        if (interface != null && c$a.class.isAssignableFrom(interface.getClass())) {
            IInterface interfaceVar1 = binder.queryLocalInterface(binder.getInterfaceDescriptor());
            if (interfaceVar1 == null || c$a.class.isAssignableFrom(interfaceVar1.getClass())) {
                throw new IllegalStateException(new StringBuilder().append("fakeBinder does not return fakeInterface, binder: ").append(binder).append(", itf: ").append(interfaceVar1).toString());
            }
            else {
                field.set(null, interfaceVar1);
            }
        }
        Class classVar1 = Class.forName("android.app.ApplicationPackageManager");
        Field fieldVar1 = j.a(classVar1, "mPM");
        PackageManager manager = context.getPackageManager();
        IInterface interfaceVar2 = (IInterface)fieldVar1.get(manager);
        if (interfaceVar2 != null && c$a.class.isAssignableFrom(interfaceVar2.getClass())) {
            IInterface interfaceVar3 = binder.queryLocalInterface(binder.getInterfaceDescriptor());
            if (interfaceVar3 == null || c$a.class.isAssignableFrom(interfaceVar3.getClass())) {
                throw new IllegalStateException(new StringBuilder().append("fakeBinder does not return fakeInterface, binder: ").append(binder).append(", itf: ").append(interfaceVar3).toString());
            }
            else {
                fieldVar1.set(manager, interfaceVar3);
            }
        }
    }

    private static <T> T b(Class<?>[] classArr0, InvocationHandler handler) {
        Class class = new Class[]{};
        System.arraycopy(classArr0, 0, class, 0, classArr0.length);
        class[classArr0.length] = c$a.class;
        Object object = null;
        ClassLoader loaderVar1;
        try {
            loaderVar1 = Thread.currentThread().getContextClassLoader();
            return Proxy.newProxyInstance(loaderVar1, class, handler);
        }
        catch (Throwable var_4_0) {
            HashSet set = new HashSet(4);
            for (int i1 = 0; i1 < class.length; i1 += 1) {
                Class classVar2 = class[i1];
                set.add(classVar2.getClassLoader());
            }
            if (set.size() == 1) {
                loaderVar1 = (ClassLoader)set.iterator().next();
            }
            else {
                d$1 d$1 = new d$1(set);
            }
            try {
                return Proxy.newProxyInstance(loaderVar1, class, handler);
            }
            catch (Throwable var_6_1) {
                throw new RuntimeException(new StringBuilder().append("cl: ").append(loaderVar1).toString(), var_4_0);
            }
        }
    }

    private static Class<?>[] b(Class<?> class) {
        if (class == null) {
            return null;
        }
        else {
            HashSet set = new HashSet(10);
            while (Object.class.equals(class)) {
                set.addAll(Arrays.asList(class.getInterfaces()));
                class = class.getSuperclass();
            }
            return (Class[])set.toArray(new Class[]{});
        }
    }

    protected /* synthetic */ void a(Object object) {
        this.b((IBinder)object);
    }

    protected /* synthetic */ Object b(Object object) {
        return this.a((IBinder)object);
    }

    protected /* synthetic */ Object b() {
        return this.a();
    }

    static /* synthetic */ Class[] a(Class class) {
        return d.b(class);
    }

    static /* synthetic */ Object a(Class[] classArr0, InvocationHandler handler) {
        return d.b(classArr0, handler);
    }

    static  {
        d.d = null;
        d.e = null;
        d.f = null;
        Class class = d.class;
        d.class;
        synchronized () {
            if (d.d == null) {
                try {
                    d.d = Class.forName("android.os.ServiceManager");
                    d.e = j.a(d.d, "sCache");
                    d.f = j.a(d.d, "getService", new Class[]{String.class});
                }
                catch (Throwable var_1_0) {
                    n.d("Tinker.SvcBndrIntrcptr", "unexpected exception.", new Object[]{var_1_0});
                }
            }
            return;
        }
    }

    // class: com/tencent/tinker/loader/hotplug/b/d$b
    class d$b implements InvocationHandler {
        final private d$a a;
        final private IBinder b;

         d$b(IBinder binder, d$a d$a) {
            super();
            this.b = binder;
            this.a = d$a;
        }

        public Object invoke(Object object, Method method, Object[] objectArr0) {
            if ("queryLocalInterface".equals(method.getName())) {
                String str0 = this.b.getInterfaceDescriptor();
                Object objectVar1 = null;
                String str1 = str0.equals("android.app.IActivityManager") ? new StringBuilder().append(str0).append("$Stub").toString() : "android.app.ActivityManagerNative";
                Class class = Class.forName(var_5_1);
                Method methodVar1 = j.a(class, "asInterface", new Class[]{IBinder.class});
                IInterface interface = (IInterface)methodVar1.invoke(null, new Object[]{this.b});
                d$c d$c = new d$c(interface, (IBinder)object, this.a);
                return d.a(d.a(interface.getClass()), d$c);
            }
            else {
                return method.invoke(this.b, objectArr0);
            }
        }

    }
    // class: com/tencent/tinker/loader/hotplug/b/d$b
    class d$b implements InvocationHandler {
        final private d$a a;
        final private IBinder b;

         d$b(IBinder binder, d$a d$a) {
            super();
            this.b = binder;
            this.a = d$a;
        }

        public Object invoke(Object object, Method method, Object[] objectArr0) {
            if ("queryLocalInterface".equals(method.getName())) {
                String str0 = this.b.getInterfaceDescriptor();
                Object objectVar1 = null;
                String str1 = str0.equals("android.app.IActivityManager") ? new StringBuilder().append(str0).append("$Stub").toString() : "android.app.ActivityManagerNative";
                Class class = Class.forName(var_5_1);
                Method methodVar1 = j.a(class, "asInterface", new Class[]{IBinder.class});
                IInterface interface = (IInterface)methodVar1.invoke(null, new Object[]{this.b});
                d$c d$c = new d$c(interface, (IBinder)object, this.a);
                return d.a(d.a(interface.getClass()), d$c);
            }
            else {
                return method.invoke(this.b, objectArr0);
            }
        }

    }
    // class: com/tencent/tinker/loader/hotplug/b/d$a
    public interface d$a {

        Object a(Object p0, Method p1, Object[] p2);

    }
    // class: com/tencent/tinker/loader/hotplug/b/d$a
    public interface d$a {

        Object a(Object p0, Method p1, Object[] p2);

    }
    // class: com/tencent/tinker/loader/hotplug/b/d$c
    class d$c implements InvocationHandler {
        final private d$a a;
        final private IBinder b;
        final private IInterface c;

         d$c(IInterface interface, IBinder binder, d$a d$a) {
            super();
            this.c = interface;
            this.b = binder;
            this.a = d$a;
        }

        public Object invoke(Object object, Method method, Object[] objectArr0) {
            if ("asBinder".equals(method.getName())) {
                return this.b;
            }
            else {
                return this.a.a(this.c, method, objectArr0);
            }
        }

    }
    // class: com/tencent/tinker/loader/hotplug/b/d$c
    class d$c implements InvocationHandler {
        final private d$a a;
        final private IBinder b;
        final private IInterface c;

         d$c(IInterface interface, IBinder binder, d$a d$a) {
            super();
            this.c = interface;
            this.b = binder;
            this.a = d$a;
        }

        public Object invoke(Object object, Method method, Object[] objectArr0) {
            if ("asBinder".equals(method.getName())) {
                return this.b;
            }
            else {
                return this.a.a(this.c, method, objectArr0);
            }
        }

    }
}
