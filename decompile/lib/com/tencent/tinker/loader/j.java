/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Configuration;
import android.content.pm.ApplicationInfo;
import android.content.Context;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.os.Handler;
import android.os.Handler$Callback;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.List;
import java.io.File;
import java.io.InputStream;

// class: com/tencent/tinker/loader/j
class j {
    private static Collection<WeakReference<Resources>> a;
    private static Map<Object, WeakReference<Object>> b;
    private static Object c;
    private static AssetManager d;
    private static Constructor<?> e;
    private static Method f;
    private static Method g;
    private static Method h;
    private static Field i;
    private static Field j;
    private static Field k;
    private static Field l;
    private static Field m;
    private static Field n;
    private static Field o;
    private static long p;
    private static Context q;
    private static Context r;

    public static void a(Context context) {
        Class class = Class.forName("android.app.ActivityThread");
        j.c = j.a(context, class);
        Class classVar2;
        try {
            classVar2 = Class.forName("android.app.LoadedApk");
        }
        catch (ClassNotFoundException var_3_1) {
            classVar2 = Class.forName("android.app.ActivityThread$PackageInfo");
        }
        j.k = j.a(classVar2, "mResDir");
        j.l = j.a(class, "mPackages");
        try {
            j.m = j.a(class, "mResourcePackages");
            goto 77;
        }
        catch (Throwable var_3_2) {
            n.a("Tinker.ResourcePatcher", var_3_2, "Fail to get mResourcePackages field.", new Object[]{});
            j.m = null;
        }
        AssetManager manager = context.getAssets();
        j.f = j.a(manager, "addAssetPath", new Class[]{String.class});
        if (j.a(context.getApplicationInfo())) {
            j.g = j.a(manager, "addAssetPathAsSharedLibrary", new Class[]{String.class});
        }
        try {
            j.o = j.a(manager, "mStringBlocks");
            j.h = j.a(manager, "ensureStringBlocks", new Class[]{});
            goto 155;
        }
        catch (Throwable var_4_3) {
        }
        j.e = j.a(manager, new Class[]{});
        ArrayMap map;
        if (Build$VERSION.SDK_INT >= 19) {
            Class classVar3 = Class.forName("android.app.ResourcesManager");
            Method method = j.a(classVar3, "getInstance", new Class[]{});
            Object object = method.invoke(null, new Object[]{});
            try {
                Field field = j.a(classVar3, "mActiveResources");
                map = (ArrayMap)field.get(object);
                j.a = map.values();
            }
            catch (NoSuchFieldException var_7_1) {
                Field fieldVar1 = j.a(classVar3, "mResourceReferences");
                j.a = (Collection)fieldVar1.get(object);
                try {
                    Field fieldVar2 = j.a(classVar3, "mResourceImpls");
                    j.b = (Map)fieldVar2.get(object);
                }
                catch (Throwable var_9_1) {
                    j.b = null;
                }
            }
            goto 325;
        }
        else {
            Field fieldVar3 = j.a(class, "mActiveResources");
            map = (HashMap)fieldVar3.get(j.c);
            j.a = map.values();
        }
        if (j.a == null) {
            throw new IllegalStateException("resource references is null");
        }
        else {
            Resources resources = context.getResources();
            if (Build$VERSION.SDK_INT >= 24) {
                try {
                    j.j = j.a(resources, "mResourcesImpl");
                }
                catch (Throwable var_5_2) {
                    j.i = j.a(resources, "mAssets");
                }
            }
            else {
                j.i = j.a(resources, "mAssets");
            }
            try {
                j.n = j.a(ApplicationInfo.class, "publicSourceDir");
            }
            catch (NoSuchFieldException var_5_3) {
            }
        }
    }

    public static void a(Context context, String str0, boolean bool0) {
        if (str0 == null) {
        }
        else {
            ApplicationInfo info = context.getApplicationInfo();
            j.q = context.createPackageContext(context.getPackageName(), 1);
            j.r = context.createPackageContext(context.getPackageName(), 0);
            Field field = new Field[]{j.l, j.m};
            for (int i3 = 0; i3 < field.length; i3 += 1) {
                Field fieldVar2 = field[i3];
                if (fieldVar2 == null) {
                    continue;;
                }
                else {
                    Object object = fieldVar2.get(j.c);
                    Iterator iterator = (Map)object.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map$Entry entry = (Map$Entry)iterator.next();
                        Object objectVar1 = (WeakReference)entry.getValue().get();
                        if (objectVar1 == null) {
                            continue;;
                        }
                        else {
                            String str1 = (String)j.k.get(objectVar1);
                            if (info.sourceDir.equals(str1)) {
                                j.k.set(objectVar1, str0);
                            }
                            continue;;
                        }
                    }
                }
            }
            if (bool0) {
                n.b("Tinker.ResourcePatcher", "Re-injecting, skip rest logic.", new Object[]{});
                j.c(str0);
            }
            else {
                j.d = (AssetManager)j.e.newInstance(new Object[]{});
                if ((Integer)j.f.invoke(j.d, new Object[]{str0}).intValue() == 0) {
                    throw new IllegalStateException("Could not create new AssetManager");
                }
                else {
                    j.c(str0);
                    if (j.a(info)) {
                        String str2 = info.sharedLibraryFiles;
                        i2 = str2.length;
                        for (i3 = 0; i3 < field.length; i3 += 1) {
                            String str3 = str2[i3];
                            str3.endsWith(".apk");
                            continue;;
                            (Integer)j.g.invoke(j.d, new Object[]{str3}).intValue() == 0;
                            throw new IllegalStateException("AssetManager add SharedLibrary Fail");
                            n.b("Tinker.ResourcePatcher", new StringBuilder().append("addAssetPathAsSharedLibrary ").append(str3).toString(), new Object[]{});
                        }
                    }
                    if (j.o != null && j.h != null) {
                        j.o.set(j.d, null);
                        j.h.invoke(j.d, new Object[]{});
                    }
                    Iterator iteratorVar1 = j.a.iterator();
                    while (iteratorVar1.hasNext()) {
                        WeakReference reference = (WeakReference)iteratorVar1.next();
                        Resources resources = (Resources)reference.get();
                        if (resources == null) {
                            continue;;
                        }
                        else {
                            try {
                                j.i.set(resources, j.d);
                            }
                            catch (Throwable var_8_3) {
                                Object objectVar2 = j.j.get(resources);
                                Field fieldVar3 = j.a(objectVar2, "mAssets");
                                fieldVar3.set(objectVar2, j.d);
                            }
                            j.a(resources);
                            resources.updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());
                            continue;;
                        }
                    }
                    if (j.b != null) {
                        try {
                            Object objectVar3 = null;
                            Object objectVar4 = null;
                            Iterator iteratorVar2 = j.b.entrySet().iterator();
                            while (iteratorVar2.hasNext()) {
                                Map$Entry entryVar1 = (Map$Entry)iteratorVar2.next();
                                Object objectVar5 = entryVar1.getKey();
                                if (objectVar4 == null) {
                                    objectVar4 = j.a(objectVar5.getClass(), "mResDir");
                                }
                                String str4 = (String)objectVar4.get(objectVar5);
                                if (info.sourceDir.equals(str4)) {
                                    continue;;
                                }
                                else {
                                    if (Build$VERSION.SDK_INT >= 35) {
                                        objectVar4.set(objectVar5, str0);
                                    }
                                    WeakReference referenceVar1 = (WeakReference)entryVar1.getValue();
                                    Object objectVar6 = referenceVar1.get();
                                    if (objectVar6 != null) {
                                        if (objectVar3 == null) {
                                            objectVar3 = j.a(objectVar6, "mAssets");
                                        }
                                        objectVar3.set(objectVar6, j.d);
                                    }
                                    continue;;
                                }
                            }
                        }
                        catch (Throwable var_5_5) {
                            throw new k("Fail to hack resourceImpls field.");
                        }
                    }
                    if (Build$VERSION.SDK_INT >= 24) {
                        try {
                            if (j.n != null) {
                                j.n.set(context.getApplicationInfo(), str0);
                            }
                        }
                        catch (Throwable var_5_6) {
                            n.a("Tinker.ResourcePatcher", var_5_6, "fail to process publicSourceDirField field hack.", new Object[]{});
                        }
                    }
                    if (j.b(context)) {
                        throw new k("checkResInstall failed");
                    }
                    else {
                        j.a(context, str0);
                    }
                }
            }
        }
    }

    private static void a(Context context, String str0) {
        try {
            Object object = j.a(context, null);
            Field field = j.a(object, "mH");
            Handler handler = (Handler)field.get(object);
            Field fieldVar1 = j.a(Handler.class, "mCallback");
            Handler$Callback callback = (Handler$Callback)fieldVar1.get(handler);
            if ((callback instanceof j$a)) {
                j$a j$a = new j$a(context, str0, callback, handler.getClass());
                fieldVar1.set(handler, j$a);
            }
            else {
                n.c("Tinker.ResourcePatcher", "installResourceInsuranceHacks: already installed, skip rest logic.", new Object[]{});
            }
        }
        catch (Throwable var_2_1) {
            n.a("Tinker.ResourcePatcher", var_2_1, "failed to install resource insurance hack.", new Object[]{});
        }
    }

    private static boolean b(String str0) {
        long l1;
        try {
            l1 = new File(str0).lastModified();
            goto 30;
        }
        catch (Throwable var_3_0) {
            n.a("Tinker.ResourcePatcher", var_3_0, "Fail to get patched res modified time.", new Object[]{});
            l1 = 0L;
        }
        if (0L == l1) {
            return false;
        }
        else if (j.p == l1) {
            return false;
        }
        else {
            return true;
        }
    }

    private static void c(String str0) {
        try {
            j.p = new File(str0).lastModified();
        }
        catch (Throwable var_1_0) {
            n.a("Tinker.ResourcePatcher", var_1_0, "Fail to store patched res modified time.", new Object[]{});
            j.p = 0L;
        }
    }

    private static void a(Resources resources) {
        n.c("Tinker.ResourcePatcher", "try to clear typedArray cache!", new Object[]{});
        try {
            Field field = j.a(Resources.class, "mTypedArrayPool");
            Object object = field.get(resources);
            Method method = j.a(object, "acquire", new Class[]{});
            while (method.invoke(object, new Object[]{}) == null) {
                continue;;
            }
        }
        catch (Throwable var_1_1) {
            n.d("Tinker.ResourcePatcher", new StringBuilder().append("clearPreloadTypedArrayIssue failed, ignore error: ").append(var_1_1).toString(), new Object[]{});
        }
    }

    private static boolean b(Context context) {
        Object object = null;
        try {
            InputStream stream = context.getAssets().open("only_use_to_test_tinker_resource.txt");
        }
        catch (Throwable var_2_0) {
            n.d("Tinker.ResourcePatcher", new StringBuilder().append("checkResUpdate failed, can't find test resource assets file only_use_to_test_tinker_resource.txt e:").append(var_2_0.getMessage()).toString(), new Object[]{});
            int i0 = false;
            h.a(stream);
            return i0;
        }
        finally {
            Throwable throwable = v_7;
            h.a(stream);
            throw throwable;
        }
        n.b("Tinker.ResourcePatcher", "checkResUpdate success, found test resource assets file only_use_to_test_tinker_resource.txt", new Object[]{});
        return true;
    }

    private static boolean a(ApplicationInfo info) {
        if (Build$VERSION.SDK_INT >= 24 && info != null && info.sharedLibraryFiles != null) {
            return true;
        }
        else {
            return false;
        }
    }

    static /* synthetic */ boolean a(String str0) {
        return j.b(str0);
    }

    static  {
        j.a = null;
        j.b = null;
        j.c = null;
        j.d = null;
        j.e = null;
        j.f = null;
        j.g = null;
        j.h = null;
        j.i = null;
        j.j = null;
        j.k = null;
        j.l = null;
        j.m = null;
        j.n = null;
        j.o = null;
        j.p = 0L;
        j.q = null;
        j.r = null;
    }

    // class: com/tencent/tinker/loader/j$a
    final class j$a implements Handler$Callback {
        final private Context a;
        final private String b;
        final private Handler$Callback c;
        final private int d;
        final private int e;
        final private int f;
        private Method g;
        private boolean h;

        void j$a(Context context, String str0, Handler$Callback callback, Class<?> class) {
            super();
            this.g = null;
            this.h = false;
            context = context.getApplicationContext();
            this.a = context != null ? context : context;
            this.b = str0;
            this.c = callback;
            this.d = this.a(class, "LAUNCH_ACTIVITY", 100);
            this.e = this.a(class, "RELAUNCH_ACTIVITY", 126);
            this.f = m.a(28, 1) ? -1 : this.a(class, "EXECUTE_TRANSACTION ", 159);
        }

        private int a(Class<?> class, String str0, int i0) {
            int i2;
            try {
                i2 = j.a(class, str0).getInt(null);
            }
            catch (Throwable var_5_0) {
            }
            return i0;
        }

        public boolean handleMessage(Message message) {
            int i1 = false;
            if (this.a(message)) {
                i1 = 1;
            }
            else if (this.c != null) {
                boolean i0 = this.c.handleMessage(message);
            }
            return i1;
        }

        private boolean a(Message message) {
            int i3 = 0;
            if (j.a(this.b)) {
                i3 = 0;
                goto 225;
            }
            else {
                if (message.what == this.d || message.what == this.e) {
                    i3 = 1;
                }
                else if (message.what == this.f) {
                    if (this.h) {
                    }
                    else {
                        Object object = message.obj;
                        if (object == null) {
                            n.c("Tinker.ResourcePatcher", "transaction is null, skip rest insurance logic.", new Object[]{});
                        }
                        else {
                            if (this.g == null) {
                                try {
                                    this.g = j.a(object, "getCallbacks", new Class[]{});
                                }
                                catch (Throwable var_4_1) {
                                }
                            }
                            if (this.g == null) {
                                n.d("Tinker.ResourcePatcher", "fail to find getLifecycleStateRequest method, skip rest insurance logic.", new Object[]{});
                                this.h = true;
                            }
                            else {
                                try {
                                    List list = (List)this.g.invoke(object, new Object[]{});
                                    if (list != null && list.size() > 0) {
                                        Object objectVar1 = list.get(0);
                                        i3 = objectVar1 != null && objectVar1.getClass().getName().equals("android.app.servertransaction.LaunchActivityItem") ? 0 : 1;
                                    }
                                }
                                catch (Throwable var_4_2) {
                                    n.d("Tinker.ResourcePatcher", "fail to call getLifecycleStateRequest method, skip rest insurance logic.", new Object[]{});
                                }
                            }
                        }
                    }
                }
            }
            if (i3 != 0) {
                try {
                    j.a(this.a, this.b, 1);
                }
                catch (Throwable var_3_1) {
                    n.a("Tinker.ResourcePatcher", var_3_1, "fail to ensure patched resources available after it's modified.", new Object[]{});
                }
            }
            return false;
        }

    }
    // class: com/tencent/tinker/loader/j$a
    final class j$a implements Handler$Callback {
        final private Context a;
        final private String b;
        final private Handler$Callback c;
        final private int d;
        final private int e;
        final private int f;
        private Method g;
        private boolean h;

        void j$a(Context context, String str0, Handler$Callback callback, Class<?> class) {
            super();
            this.g = null;
            this.h = false;
            context = context.getApplicationContext();
            this.a = context != null ? context : context;
            this.b = str0;
            this.c = callback;
            this.d = this.a(class, "LAUNCH_ACTIVITY", 100);
            this.e = this.a(class, "RELAUNCH_ACTIVITY", 126);
            this.f = m.a(28, 1) ? -1 : this.a(class, "EXECUTE_TRANSACTION ", 159);
        }

        private int a(Class<?> class, String str0, int i0) {
            int i2;
            try {
                i2 = j.a(class, str0).getInt(null);
            }
            catch (Throwable var_5_0) {
            }
            return i0;
        }

        public boolean handleMessage(Message message) {
            int i1 = false;
            if (this.a(message)) {
                i1 = 1;
            }
            else if (this.c != null) {
                boolean i0 = this.c.handleMessage(message);
            }
            return i1;
        }

        private boolean a(Message message) {
            int i3 = 0;
            if (j.a(this.b)) {
                i3 = 0;
                goto 225;
            }
            else {
                if (message.what == this.d || message.what == this.e) {
                    i3 = 1;
                }
                else if (message.what == this.f) {
                    if (this.h) {
                    }
                    else {
                        Object object = message.obj;
                        if (object == null) {
                            n.c("Tinker.ResourcePatcher", "transaction is null, skip rest insurance logic.", new Object[]{});
                        }
                        else {
                            if (this.g == null) {
                                try {
                                    this.g = j.a(object, "getCallbacks", new Class[]{});
                                }
                                catch (Throwable var_4_1) {
                                }
                            }
                            if (this.g == null) {
                                n.d("Tinker.ResourcePatcher", "fail to find getLifecycleStateRequest method, skip rest insurance logic.", new Object[]{});
                                this.h = true;
                            }
                            else {
                                try {
                                    List list = (List)this.g.invoke(object, new Object[]{});
                                    if (list != null && list.size() > 0) {
                                        Object objectVar1 = list.get(0);
                                        i3 = objectVar1 != null && objectVar1.getClass().getName().equals("android.app.servertransaction.LaunchActivityItem") ? 0 : 1;
                                    }
                                }
                                catch (Throwable var_4_2) {
                                    n.d("Tinker.ResourcePatcher", "fail to call getLifecycleStateRequest method, skip rest insurance logic.", new Object[]{});
                                }
                            }
                        }
                    }
                }
            }
            if (i3 != 0) {
                try {
                    j.a(this.a, this.b, 1);
                }
                catch (Throwable var_3_1) {
                    n.a("Tinker.ResourcePatcher", var_3_1, "fail to ensure patched resources available after it's modified.", new Object[]{});
                }
            }
            return false;
        }

    }
}
