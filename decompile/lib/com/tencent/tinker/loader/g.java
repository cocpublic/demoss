/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.os.Handler;
import android.os.Looper;
import android.content.pm.PackageManager;
import android.content.ContextWrapper;
import android.content.Context;
import dalvik.system.DexFile;

// class: com/tencent/tinker/loader/g
public final class g {
    final private static int a;
    final private static IBinder[] b;
    final private static Handler c;
    final private static ResultReceiver d;
    final private static PackageManager[] e;

    public static boolean a(Context context, Collection<File> collection, File file, boolean bool0, boolean bool1, g$b g$b) {
        String str0 = m.e();
        return g.a(context, collection, file, false, bool0, str0, bool1, g$b);
    }

    public static boolean a(Context context, Collection<File> collection, File file, boolean bool0, boolean bool1, String str0, boolean bool2, g$b g$b) {
        ArrayList list = new ArrayList(collection);
        Collections.sort(list, new g$1());
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            File fileVar1 = (File)iterator.next();
            g$a g$a = new g$a(context, fileVar1, file, bool0, bool1, str0, bool2, g$b);
            if (g$a.a()) {
                return false;
            }
            else {
                continue;;
            }
        }
        return true;
    }

    public static void a(String str0) {
        if (m.a(29, 1)) {
        }
        else {
            n.b("Tinker.ParallelDex", "Creating fake odex path structure.", new Object[]{});
            File file = new File(str0);
            if (file.exists()) {
                File fileVar1 = file.getParentFile();
                if (fileVar1.exists()) {
                    fileVar1.mkdirs();
                }
                try {
                    file.createNewFile();
                }
                catch (Throwable var_3_0) {
                }
            }
        }
    }

    public static void a(Context context, String str0, String str1) {
        if (m.a(29, 1)) {
            n.c("Tinker.ParallelDex", "[+] Not API 29, 30 and newer device, skip triggering dexopt.", new Object[]{});
        }
        else {
            n.b("Tinker.ParallelDex", "[+] Hit target device, do dexopt logic now.", new Object[]{});
            File file = new File(str1);
            if (h.a(file)) {
                n.b("Tinker.ParallelDex", "[+] Oat file %s should be valid, skip triggering dexopt.", new Object[]{str1});
            }
            else {
                File fileVar1 = new File(str0);
                for (int i1 = 0; i1 < 10; i1 += 1) {
                    if (g.a(context, fileVar1, file, 1)) {
                    }
                    else {
                    }
                }
                if (h.a(file)) {
                    if (! "huawei".equalsIgnoreCase(Build.MANUFACTURER) || "honor".equalsIgnoreCase(Build.MANUFACTURER)) {
                        for (i1 = 0; i1 < 5; i1 += 1) {
                            try {
                                g.a(context, str0);
                                if (h.a(file)) {
                                    break;;
                                }
                                else {
                                    continue;;
                                }
                            }
                            catch (Throwable var_6_0) {
                                n.a("Tinker.ParallelDex", var_6_0, "[-] Error.", new Object[]{});
                            }
                            SystemClock.sleep(3000L);
                        }
                        if (h.a(file)) {
                            throw new IllegalStateException("No odex file was generated after calling registerDexModule");
                        }
                    }
                    else {
                        throw new IllegalStateException("No odex file was generated after calling performDexOptSecondary");
                    }
                }
            }
        }
    }

    private static boolean a(Context context, File file, File fileVar1, boolean bool0) {
        try {
            g.a(context);
            if (h.a(fileVar1)) {
                return true;
            }
            else {
            }
        }
        catch (Throwable var_4_0) {
            n.a("Tinker.ParallelDex", var_4_0, "[-] Error.", new Object[]{});
        }
        try {
            g.b(context);
            if (h.a(fileVar1)) {
                return true;
            }
            else {
            }
        }
        catch (Throwable var_4_1) {
            n.a("Tinker.ParallelDex", var_4_1, "[-] Error.", new Object[]{});
        }
        try {
            g.c(context);
            if (h.a(fileVar1)) {
                return true;
            }
            else {
            }
        }
        catch (Throwable var_4_2) {
            n.a("Tinker.ParallelDex", var_4_2, "[-] Error.", new Object[]{});
        }
        if (bool0) {
            return g.a(context, fileVar1.getAbsolutePath(), new Long[]{Long.valueOf(3000L)});
        }
        else {
            return h.a(fileVar1);
        }
    }

    private static void a(Context context) {
        String str0 = new String[]{"compile", "-f", "--secondary-dex", "-m", m.a(31, 1) ? "speed-profile" : "verify", context.getPackageName()};
        g.a(context, str0);
    }

    private static void b(Context context) {
        String str0 = new String[]{"bg-dexopt-job", context.getPackageName()};
        g.a(context, str0);
    }

    private static void c(Context context) {
        g.a;
        synchronized () {
            if (g.a[0] == -1) {
                try {
                    Method method = j.a(Class.class, "getDeclaredField", new Class[]{String.class});
                    method.setAccessible(true);
                    Field field = (Field)method.invoke(Class.forName("android.content.pm.IPackageManager$Stub"), new Object[]{"TRANSACTION_performDexOptSecondary"});
                    field.setAccessible(true);
                    g.a[0] = (Integer)field.get(null).intValue();
                }
                catch (Throwable var_2_3) {
                    throw new IllegalStateException("Cannot query transaction code of performDexOptSecondary.", var_2_3);
                }
            }
        }
        n.b("Tinker.ParallelDex", "[+] performDexOptSecondaryByTransactionCode, code: %s", new Object[]{Integer.valueOf(g.a[0])});
        IBinder binder = g.d(context);
        Object object = null;
        Object objectVar1 = null;
        try {
            Parcel parcel = Parcel.obtain();
            Parcel parcelVar1 = Parcel.obtain();
            try {
                parcel.writeInterfaceToken(binder.getInterfaceDescriptor());
                parcel.writeString(context.getPackageName());
                String str0 = m.a(31, 1) ? "speed-profile" : "verify";
                parcel.writeString(str0);
                parcel.writeInt(1);
                boolean bool0 = binder.transact(g.a[0], parcel, parcelVar1, 0);
                if (bool0) {
                    throw new IllegalStateException("Binder transaction failure.");
                }
                else {
                }
            }
            catch (RemoteException var_5_1) {
                throw new IllegalStateException(var_5_1);
            }
            try {
                parcelVar1.readException();
            }
            catch (Throwable var_5_2) {
                throw new IllegalStateException(var_5_2);
            }
            int i0 = 0 != parcelVar1.readInt() ? 0 : 1;
            if (i0 == 0) {
                n.c("Tinker.ParallelDex", "[!] System API return false.", new Object[]{});
            }
            return;
        }
        finally {
            Throwable throwableVar1 = v_50;
            if (parcelVar1 != null) {
                parcelVar1.recycle();
            }
            if (parcel != null) {
                parcel.recycle();
            }
            throw throwableVar1;
        }
    }

    private static IBinder d(Context context) {
        g.b;
        synchronized () {
            IBinder binderVar1 = g.b[0];
            if (binderVar1 != null && binderVar1.isBinderAlive()) {
                return binderVar1;
            }
            else {
                try {
                    Class class = Class.forName("android.os.ServiceManager");
                    Method method = j.a(class, "getService", new Class[]{String.class});
                    g.b[0] = (IBinder)method.invoke(null, new Object[]{"package"});
                    g.b;
                    return g.b[0];
                }
                catch (Throwable var_3_1) {
                    if ((var_3_1 instanceof InvocationTargetException)) {
                        throw new IllegalStateException((InvocationTargetException)var_3_1.getTargetException());
                    }
                    else {
                        throw new IllegalStateException(var_3_1);
                    }
                }
            }
        }
    }

    private static void a(Context context, String[] stringArr0) {
        IBinder binder = g.d(context);
        Object object = null;
        Object objectVar1 = null;
        long l0 = Binder.clearCallingIdentity();
        try {
            n.b("Tinker.ParallelDex", "[+] Execute shell cmd, args: %s", new Object[]{Arrays.toString(stringArr0)});
            Parcel parcel = Parcel.obtain();
            Parcel parcelVar1 = Parcel.obtain();
            parcel.writeFileDescriptor(FileDescriptor.in);
            parcel.writeFileDescriptor(FileDescriptor.out);
            parcel.writeFileDescriptor(FileDescriptor.err);
            parcel.writeStringArray(stringArr0);
            parcel.writeStrongBinder(null);
            g.d.writeToParcel(parcel, 0);
            binder.transact(1598246212, parcel, parcelVar1, 0);
            parcelVar1.readException();
            n.b("Tinker.ParallelDex", "[+] Execute shell cmd done.", new Object[]{});
            return;
        }
        catch (Throwable var_7_0) {
            throw new IllegalStateException("Failure on executing shell cmd.", var_7_0);
        }
        finally {
            Throwable throwable = v_40;
            if (parcelVar1 != null) {
                parcelVar1.recycle();
            }
            if (parcel != null) {
                parcel.recycle();
            }
            Binder.restoreCallingIdentity(l0);
            throw throwable;
        }
    }

    private static void a(Context context, String str0) {
        PackageManager manager = g.e(context);
        try {
            Class class = Class.forName("android.content.pm.PackageManager$DexModuleRegisterCallback");
            j.a(manager, "registerDexModule", new Class[]{String.class, class}).invoke(manager, new Object[]{str0, null});
            return;
        }
        catch (InvocationTargetException var_3_1) {
            throw new IllegalStateException(var_3_1.getTargetException());
        }
        catch (Throwable var_3_2) {
            if ((var_3_2 instanceof IllegalStateException)) {
                throw (IllegalStateException)var_3_2;
            }
            else {
                throw new IllegalStateException(var_3_2);
            }
        }
    }

    final private static PackageManager e(Context context) {
        g.e;
        synchronized () {
            try {
                if (g.e[0] != null) {
                    g.b;
                    synchronized () {
                        if (g.b[0] != null && g.b[0].isBinderAlive()) {
                            return g.e[0];
                        }
                        else {
                        }
                    }
                }
                IBinder binderVar1 = g.d(context);
                IBinder binderVar2 = (IBinder)Proxy.newProxyInstance(context.getClassLoader(), binderVar1.getClass().getInterfaces(), new g$2(binderVar1));
                Class class = Class.forName("android.content.pm.IPackageManager$Stub");
                Object object = j.a(class, "asInterface", new Class[]{IBinder.class}).invoke(null, new Object[]{binderVar2});
                Class classVar1 = Class.forName("android.app.ApplicationPackageManager");
                Context contextVar1 = (context instanceof ContextWrapper) ? context : context;
                Class classVar2 = Class.forName("android.content.pm.IPackageManager");
                PackageManager managerVar1 = (PackageManager)j.a(classVar1, new Class[]{contextVar1.getClass(), classVar2}).newInstance(new Object[]{contextVar1, object});
                g.e[0] = managerVar1;
                g.e;
                return managerVar1;
            }
            catch (InvocationTargetException var_2_2) {
                throw new IllegalStateException(var_2_2.getTargetException());
            }
            catch (Throwable var_2_3) {
                if ((var_2_3 instanceof IllegalStateException)) {
                    throw (IllegalStateException)var_2_3;
                }
                else {
                    throw new IllegalStateException(var_2_3);
                }
            }
        }
    }

    public static boolean a(Context context, String str0, Long[] longArr0) {
        File file = new File(str0);
        longArr0 != null ? new Long[]{} : longArr0[0] = Long.valueOf(1000L);
        v_5[1] = Long.valueOf(2000L);
        v_5[2] = Long.valueOf(4000L);
        v_5[3] = Long.valueOf(8000L);
        v_5[4] = Long.valueOf(16000L);
        Long[] longArr0Var1 = v_5;
        int i0 = 0;
        while (true) {
            if (h.a(file) && i0 < longArr0Var1.length) {
                i0 += 1;
                SystemClock.sleep(longArr0Var1[i0].longValue());
                n.c("Tinker.ParallelDex", "[!] File %s does not exist after waiting %s time(s), wait again.", new Object[]{str0, Integer.valueOf(i0)});
                continue;;
            }
            else if (if (h.a(file) ) break; /* target: 155 */) {
            }
        }
        n.b("Tinker.ParallelDex", "[+] File %s was found.", new Object[]{str0});
        return true;
        n.d("Tinker.ParallelDex", "[-] File %s does not exist after waiting for %s times.", new Object[]{str0, Integer.valueOf(longArr0Var1.length)});
        return false;
    }

    public static void a(String str0, String str1, String str2) {
        File file = new File(str1);
        if (file.exists()) {
            file.getParentFile().mkdirs();
        }
        File fileVar1 = new File(file.getParentFile(), "interpret.lock");
        Object object = null;
        try {
            f f = f.a(fileVar1);
            ArrayList list = new ArrayList();
            list.add("dex2oat");
            if (Build$VERSION.SDK_INT >= 24) {
                list.add("--runtime-arg");
                list.add("-classpath");
                list.add("--runtime-arg");
                list.add("&");
            }
            list.add(new StringBuilder().append("--dex-file=").append(str0).toString());
            list.add(new StringBuilder().append("--oat-file=").append(str1).toString());
            list.add(new StringBuilder().append("--instruction-set=").append(str2).toString());
            if (Build$VERSION.SDK_INT > 25) {
                list.add("--compiler-filter=quicken");
            }
            else {
                list.add("--compiler-filter=interpret-only");
            }
            try {
                ProcessBuilder builder = new ProcessBuilder(list);
                builder.redirectErrorStream(true);
                Process process = builder.start();
                g$c.a(process.getInputStream());
                g$c.a(process.getErrorStream());
                int i0 = process.waitFor();
                if (i0 != 0) {
                    throw new IOException(new StringBuilder().append("dex2oat works unsuccessfully, exit code: ").append(i0).toString());
                }
                else {
                }
            }
            catch (InterruptedException var_9_1) {
                throw new IOException(new StringBuilder().append("dex2oat is interrupted, msg: ").append(var_9_1.getMessage()).toString(), var_9_1);
            }
            try {
                if (f != null) {
                    f.close();
                }
            }
            catch (IOException var_6_1) {
                n.c("Tinker.ParallelDex", "release interpret Lock error", new Object[]{var_6_1});
            }
        }
        finally {
            Throwable throwable = v_20;
            try {
                if (f != null) {
                    f.close();
                }
            }
            catch (IOException var_11_0) {
                n.c("Tinker.ParallelDex", "release interpret Lock error", new Object[]{var_11_0});
            }
            throw throwable;
        }
    }

    static  {
        g.a = new int[]{-1};
        g.b = new IBinder[]{null};
        g.c = new Handler(Looper.getMainLooper());
        g.d = new ResultReceiver(g.c);
        g.e = new PackageManager[]{null};
    }

    // class: com/tencent/tinker/loader/g$b
    public interface g$b {

        void a(File p0, File p1);

        void a(File p0, File p1, File p2);

        void a(File p0, File p1, Throwable p2);

    }
    // class: com/tencent/tinker/loader/g$b
    public interface g$b {

        void a(File p0, File p1);

        void a(File p0, File p1, File p2);

        void a(File p0, File p1, Throwable p2);

    }
    // class: com/tencent/tinker/loader/g$a
    public class g$a {
        public static ClassLoader a;
        final public String b;
        final public Context c;
        final public File d;
        final public File e;
        final public boolean f;
        final public boolean g;
        final private boolean i;
        final public g$b h;

        public g$a(Context context, File file, File fileVar1, boolean bool0, boolean bool1, String str0, boolean bool2, g$b g$b) {
            super();
            this.c = context;
            this.d = file;
            this.e = fileVar1;
            this.f = bool0;
            this.g = bool1;
            this.h = g$b;
            this.b = str0;
            this.i = bool2;
        }

        public boolean a() {
            try {
                if (h.a(this.d) && this.h != null) {
                    this.h.a(this.d, this.e, new IOException(new StringBuilder().append("dex file ").append(this.d.getAbsolutePath()).append(" is not exist!").toString()));
                    return false;
                }
                else {
                    if (this.h != null) {
                        this.h.a(this.d, this.e);
                    }
                    String str0 = h.b(this.d, this.e);
                    if (m.c()) {
                        if (this.f) {
                            g.a(this.d.getAbsolutePath(), str0, this.b);
                        }
                        else {
                            if (TinkerApplication.a().e() && m.a(21, 25, 1) && m.f()) {
                                try {
                                    n.b("Tinker.ParallelDex", "dexopt with interpret mode on 32bit supported system was enabled.", new Object[]{});
                                    g.a(this.d.getAbsolutePath(), str0, this.b);
                                    goto 208;
                                }
                                catch (Throwable var_2_2) {
                                    n.a("Tinker.ParallelDex", var_2_2, "exception occurred on dexopt triggering.", new Object[]{});
                                }
                                if (h.a(new File(str0))) {
                                    n.c("Tinker.ParallelDex", "interpret dexopt failure, compensate with system method.", new Object[]{});
                                    DexFile.loadDex(this.d.getAbsolutePath(), str0, 0);
                                }
                            }
                            else if (m.a(26, 1)) {
                                if (m.a(29, 1)) {
                                    g.a(str0);
                                    g$a.a = c.a(this.c, this.e, this.g, new String[]{this.d.getAbsolutePath()});
                                    g$a$1 g$a$1 = new g$a$1(this, str0);
                                    if (this.i) {
                                        new Thread(g$a$1, "TinkerDex2oatTrigger").start();
                                    }
                                    else {
                                        g$a$1.run();
                                    }
                                }
                                else {
                                    g$a.a = c.a(this.c, this.e, this.g, new String[]{this.d.getAbsolutePath()});
                                }
                            }
                            else {
                                DexFile.loadDex(this.d.getAbsolutePath(), str0, 0);
                            }
                        }
                    }
                    File file = new File(str0);
                    if (! h.a(file) || h.b(file)) {
                        if (this.h != null) {
                            this.h.a(this.d, this.e, file);
                        }
                        return true;
                    }
                    else {
                        FileNotFoundException exception = new FileNotFoundException(new StringBuilder().append("Odex file: ").append(file.getAbsolutePath()).append(" does not exist.").toString());
                        if (this.h != null) {
                            this.h.a(this.d, this.e, exception);
                        }
                        return false;
                    }
                }
            }
            catch (Throwable var_1_1) {
                n.d("Tinker.ParallelDex", new StringBuilder().append("Failed to optimize dex: ").append(this.d.getAbsolutePath()).toString(), new Object[]{var_1_1});
                this.h != null;
                this.h.a(this.d, this.e, var_1_1);
                return false;
            }
        }

        static /* synthetic */ boolean a(g$a g$a) {
            return g$a.i;
        }

        static  {
            g$a.a = null;
        }

    }
    // class: com/tencent/tinker/loader/g$a
    public class g$a {
        public static ClassLoader a;
        final public String b;
        final public Context c;
        final public File d;
        final public File e;
        final public boolean f;
        final public boolean g;
        final private boolean i;
        final public g$b h;

        public g$a(Context context, File file, File fileVar1, boolean bool0, boolean bool1, String str0, boolean bool2, g$b g$b) {
            super();
            this.c = context;
            this.d = file;
            this.e = fileVar1;
            this.f = bool0;
            this.g = bool1;
            this.h = g$b;
            this.b = str0;
            this.i = bool2;
        }

        public boolean a() {
            try {
                if (h.a(this.d) && this.h != null) {
                    this.h.a(this.d, this.e, new IOException(new StringBuilder().append("dex file ").append(this.d.getAbsolutePath()).append(" is not exist!").toString()));
                    return false;
                }
                else {
                    if (this.h != null) {
                        this.h.a(this.d, this.e);
                    }
                    String str0 = h.b(this.d, this.e);
                    if (m.c()) {
                        if (this.f) {
                            g.a(this.d.getAbsolutePath(), str0, this.b);
                        }
                        else {
                            if (TinkerApplication.a().e() && m.a(21, 25, 1) && m.f()) {
                                try {
                                    n.b("Tinker.ParallelDex", "dexopt with interpret mode on 32bit supported system was enabled.", new Object[]{});
                                    g.a(this.d.getAbsolutePath(), str0, this.b);
                                    goto 208;
                                }
                                catch (Throwable var_2_2) {
                                    n.a("Tinker.ParallelDex", var_2_2, "exception occurred on dexopt triggering.", new Object[]{});
                                }
                                if (h.a(new File(str0))) {
                                    n.c("Tinker.ParallelDex", "interpret dexopt failure, compensate with system method.", new Object[]{});
                                    DexFile.loadDex(this.d.getAbsolutePath(), str0, 0);
                                }
                            }
                            else if (m.a(26, 1)) {
                                if (m.a(29, 1)) {
                                    g.a(str0);
                                    g$a.a = c.a(this.c, this.e, this.g, new String[]{this.d.getAbsolutePath()});
                                    g$a$1 g$a$1 = new g$a$1(this, str0);
                                    if (this.i) {
                                        new Thread(g$a$1, "TinkerDex2oatTrigger").start();
                                    }
                                    else {
                                        g$a$1.run();
                                    }
                                }
                                else {
                                    g$a.a = c.a(this.c, this.e, this.g, new String[]{this.d.getAbsolutePath()});
                                }
                            }
                            else {
                                DexFile.loadDex(this.d.getAbsolutePath(), str0, 0);
                            }
                        }
                    }
                    File file = new File(str0);
                    if (! h.a(file) || h.b(file)) {
                        if (this.h != null) {
                            this.h.a(this.d, this.e, file);
                        }
                        return true;
                    }
                    else {
                        FileNotFoundException exception = new FileNotFoundException(new StringBuilder().append("Odex file: ").append(file.getAbsolutePath()).append(" does not exist.").toString());
                        if (this.h != null) {
                            this.h.a(this.d, this.e, exception);
                        }
                        return false;
                    }
                }
            }
            catch (Throwable var_1_1) {
                n.d("Tinker.ParallelDex", new StringBuilder().append("Failed to optimize dex: ").append(this.d.getAbsolutePath()).toString(), new Object[]{var_1_1});
                this.h != null;
                this.h.a(this.d, this.e, var_1_1);
                return false;
            }
        }

        static /* synthetic */ boolean a(g$a g$a) {
            return g$a.i;
        }

        static  {
            g$a.a = null;
        }

    }
    // class: com/tencent/tinker/loader/g$c
    class g$c {
        final static Executor a;

        static void a(InputStream stream) {
            g$c.a.execute(new g$c$1(stream));
        }

        static  {
            g$c.a = Executors.newSingleThreadExecutor();
        }

    }
    // class: com/tencent/tinker/loader/g$c
    class g$c {
        final static Executor a;

        static void a(InputStream stream) {
            g$c.a.execute(new g$c$1(stream));
        }

        static  {
            g$c.a = Executors.newSingleThreadExecutor();
        }

    }
}
