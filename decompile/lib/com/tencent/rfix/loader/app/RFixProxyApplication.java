/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.Executor;
import android.app.Application;
import android.app.Application$ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import androidx.annotation.Keep;

// class: com/tencent/rfix/loader/app/RFixProxyApplication
public abstract class RFixProxyApplication {
    final private String c;
    private boolean d;
    private Application e;
    private boolean f;
    final private ArrayList<Application$ActivityLifecycleCallbacks> g;
    final private Map<ServiceConnection, RFixProxyApplication$a> h;

    @Keep
    public RFixProxyApplication(String str0) {
        super(str0, null);
    }

    @Keep
    public RFixProxyApplication(String str0, String str1) {
        super("com.tencent.rfix.entry.DefaultRFixApplicationLike", str1);
        this.g = new ArrayList();
        this.h = new HashMap();
        this.c = str0;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        this.d = true;
        this.a(context);
        this.b(this.e);
    }

    public void onCreate() {
        this.d = false;
        super.onCreate();
        this.a(this.e);
        this.f = true;
        this.c(this.e);
        this.e.onCreate();
    }

    public String getPackageName() {
        if (this.d) {
            return super.getPackageName();
        }
        else {
            Throwable throwable = new Throwable();
            if (this.a(throwable)) {
                return "";
            }
            else {
                return super.getPackageName();
            }
        }
    }

    protected boolean a(Throwable throwable) {
        if (this.d && throwable != null) {
            StackTraceElement[] elementArr0 = throwable.getStackTrace();
            if (elementArr0.length >= 2) {
                StackTraceElement element = elementArr0[1];
                return "android.app.ActivityThread".equals(element.getClassName());
            }
        }
        return false;
    }

    protected void a(Context context) {
        long l0 = SystemClock.elapsedRealtime();
        try {
            Class class = Class.forName(this.c, true, this.b);
            Application application = (Application)class.newInstance();
            super.a(application, "attach", new Class[]{Context.class}, new Object[]{context});
            this.e = application;
        }
        catch (Throwable var_4_2) {
            throw new RuntimeException("initDelegateApplication fail.", var_4_2);
        }
        long l1 = SystemClock.elapsedRealtime() - l0;
        RFixLog.c("RFix.RFixProxyApplication", new StringBuilder().append("initDelegateApplication time cost: ").append(l1).toString());
    }

    protected void a(Application application) {
        long l0 = SystemClock.elapsedRealtime();
        try {
            Context context = this.getBaseContext();
            super.a(context, "mOuterContext", application);
            Object object = super.a(context, "mPackageInfo");
            super.a(object, "mApplication", application);
            Object objectVar1 = super.a(object, "mActivityThread");
            super.a(objectVar1, "mInitialApplication", application);
            List list = (List)super.a(objectVar1, "mAllApplications");
            list.clear();
            list.add(application);
        }
        catch (Throwable var_4_2) {
            throw new RuntimeException("replaceApplication fail.", var_4_2);
        }
        long l1 = SystemClock.elapsedRealtime() - l0;
        RFixLog.c("RFix.RFixProxyApplication", new StringBuilder().append("replaceApplication time cost: ").append(l1).toString());
    }

    private void a(Object object, String str0, Object objectVar1) {
        Field field = j.a(object, str0);
        field.set(object, objectVar1);
    }

    private Object a(Object object, String str0) {
        Field field = j.a(object, str0);
        return field.get(object);
    }

    private void a(Object object, String str0, Class<?>[] classArr0, Object[] objectArr0) {
        Method method = j.a(object, str0, classArr0);
        method.invoke(object, objectArr0);
    }

    private void b(Application application) {
        if (application == null) {
            return;
        }
        else {
            ArrayList list = this.g;
            this.g;
            synchronized () {
                Iterator iterator = this.g.iterator();
                while (iterator.hasNext()) {
                    Application$ActivityLifecycleCallbacks callbacks = (Application$ActivityLifecycleCallbacks)iterator.next();
                    application.registerActivityLifecycleCallbacks(callbacks);
                }
                this.g.clear();
            }
        }
    }

    public void registerActivityLifecycleCallbacks(Application$ActivityLifecycleCallbacks callbacks) {
        if (this.e != null) {
            this.e.registerActivityLifecycleCallbacks(callbacks);
            return;
        }
        else {
            RFixLog.d("RFix.RFixProxyApplication", "registerActivityLifecycleCallbacks delegateApplication is null? backup it first.");
            ArrayList list = this.g;
            this.g;
            synchronized () {
                this.g.add(callbacks);
            }
        }
    }

    public void unregisterActivityLifecycleCallbacks(Application$ActivityLifecycleCallbacks callbacks) {
        if (this.e != null) {
            this.e.unregisterActivityLifecycleCallbacks(callbacks);
            return;
        }
        else {
            RFixLog.d("RFix.RFixProxyApplication", "unregisterActivityLifecycleCallbacks delegateApplication is null? backup it first.");
            ArrayList list = this.g;
            this.g;
            synchronized () {
                this.g.remove(callbacks);
            }
        }
    }

    public void registerComponentCallbacks(ComponentCallbacks callbacks) {
        if (this.e != null) {
            this.e.registerComponentCallbacks(callbacks);
        }
        else {
            RFixLog.e("RFix.RFixProxyApplication", "registerComponentCallbacks delegateApplication is null?");
        }
    }

    public void unregisterComponentCallbacks(ComponentCallbacks callbacks) {
        if (this.e != null) {
            this.e.unregisterComponentCallbacks(callbacks);
        }
        else {
            RFixLog.e("RFix.RFixProxyApplication", "unregisterComponentCallbacks delegateApplication is null?");
        }
    }

    public void registerOnProvideAssistDataListener(Application$OnProvideAssistDataListener listener) {
        if (this.e != null) {
            this.e.registerOnProvideAssistDataListener(listener);
        }
        else {
            RFixLog.e("RFix.RFixProxyApplication", "registerOnProvideAssistDataListener delegateApplication is null?");
        }
    }

    public void unregisterOnProvideAssistDataListener(Application$OnProvideAssistDataListener listener) {
        if (this.e != null) {
            this.e.unregisterOnProvideAssistDataListener(listener);
        }
        else {
            RFixLog.e("RFix.RFixProxyApplication", "unregisterOnProvideAssistDataListener delegateApplication is null?");
        }
    }

    private void c(Application application) {
        if (application == null) {
            return;
        }
        else {
            Map map = this.h;
            this.h;
            synchronized () {
                Iterator iterator = this.h.values().iterator();
                while (iterator.hasNext()) {
                    RFixProxyApplication$a application$a = (RFixProxyApplication$a)iterator.next();
                    if (application$a.d == null) {
                        application.bindService(application$a.a, application$a.b, application$a.c);
                        continue;;
                    }
                    else if (Build$VERSION.SDK_INT >= 29) {
                        application.bindService(application$a.a, application$a.c, application$a.d, application$a.b);
                    }
                }
                this.h.clear();
            }
        }
    }

    public boolean bindService(Intent intent, ServiceConnection connection, int i0) {
        if (this.f) {
            return this.e.bindService(intent, connection, i0);
        }
        else {
            RFixLog.d("RFix.RFixProxyApplication", new StringBuilder().append("bindService delegateApplication not replaced? backup is first. conn=").append(connection).toString());
            RFixProxyApplication$a application$a = new RFixProxyApplication$a(null);
            application$a.a = intent;
            application$a.b = connection;
            application$a.c = i0;
            Map map = this.h;
            this.h;
            synchronized () {
                this.h.put(connection, application$a);
            }
            return true;
        }
    }

    public boolean bindService(Intent intent, int i0, Executor executor, ServiceConnection connection) {
        if (this.f) {
            return this.e.bindService(intent, connection, i0);
        }
        else {
            RFixLog.d("RFix.RFixProxyApplication", new StringBuilder().append("bindService delegateApplication not replaced? backup is first. conn=").append(connection).toString());
            RFixProxyApplication$a application$a = new RFixProxyApplication$a(null);
            application$a.a = intent;
            application$a.c = i0;
            application$a.d = executor;
            application$a.b = connection;
            Map map = this.h;
            this.h;
            synchronized () {
                this.h.put(connection, application$a);
            }
            return true;
        }
    }

    public void unbindService(ServiceConnection connection) {
        if (this.f) {
            this.e.unbindService(connection);
            return;
        }
        else {
            RFixLog.d("RFix.RFixProxyApplication", new StringBuilder().append("unbindService delegateApplication not replaced? conn=").append(connection).toString());
            Map map = this.h;
            this.h;
            synchronized () {
                this.h.remove(connection);
            }
        }
    }

    // class: com/tencent/rfix/loader/app/RFixProxyApplication$a
    class RFixProxyApplication$a {
        public Intent a;
        public ServiceConnection b;
        int c;
        Executor d;

        private RFixProxyApplication$a() {
            super();
        }

        /* synthetic */ RFixProxyApplication$a(RFixProxyApplication$1 application$1) {
            super();
        }

    }
    // class: com/tencent/rfix/loader/app/RFixProxyApplication$a
    class RFixProxyApplication$a {
        public Intent a;
        public ServiceConnection b;
        int c;
        Executor d;

        private RFixProxyApplication$a() {
            super();
        }

        /* synthetic */ RFixProxyApplication$a(RFixProxyApplication$1 application$1) {
            super();
        }

    }
}
