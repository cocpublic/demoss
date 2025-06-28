/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/service;

import android.content.Intent;
import android.content.ComponentName;
import android.app.ActivityManager;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.Notification;
import com.tencent.tinker.loader.k;
import com.tencent.tinker.lib.e.b;
import com.tencent.tinker.lib.d.d;
import com.tencent.tinker.lib.c.c;
import com.tencent.tinker.lib.a.a;
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.File;
import java.io.File[];

// class: com/tencent/tinker/lib/service/TinkerPatchService
public class TinkerPatchService {
    private static c a;
    private static int b;
    private static Class<? extends a> c;
    private static AtomicBoolean d;

    public TinkerPatchService() {
        super("TinkerPatchService");
        this.setIntentRedelivery(true);
    }

    public static void a(Context context, String str0) {
        TinkerPatchService.a(context, str0, 0);
    }

    public static void a(Context context, String str0, boolean bool0) {
        n.b("Tinker.TinkerPatchService", "run patch service...", new Object[]{});
        Intent intent = new Intent(context, TinkerPatchService.class);
        intent.putExtra("patch_path_extra", str0);
        intent.putExtra("patch_use_emergency_mode", bool0);
        intent.putExtra("patch_result_class", TinkerPatchService.c.getName());
        try {
            context.startService(intent);
        }
        catch (Throwable var_4_0) {
            n.d("Tinker.TinkerPatchService", new StringBuilder().append("run patch service fail, exception:").append(var_4_0).toString(), new Object[]{});
        }
    }

    public static void a(c c, Class<? extends a> class) {
        TinkerPatchService.a = c;
        TinkerPatchService.c = class;
        try {
            Class.forName(class.getName());
        }
        catch (ClassNotFoundException var_2_0) {
            n.a("Tinker.TinkerPatchService", var_2_0, "patch processor class not found.", new Object[]{});
        }
    }

    public static String a(Intent intent) {
        if (intent == null) {
            throw new k("getPatchPathExtra, but intent is null");
        }
        else {
            return g.a(intent, "patch_path_extra");
        }
    }

    public static boolean b(Intent intent) {
        if (intent == null) {
            throw new k("getPatchUseEmergencyMode, but intent is null");
        }
        else {
            return g.a(intent, "patch_use_emergency_mode", 0);
        }
    }

    public static String c(Intent intent) {
        if (intent == null) {
            throw new k("getPatchResultExtra, but intent is null");
        }
        else {
            return g.a(intent, "patch_result_class");
        }
    }

    public int onStartCommand(Intent intent, int i0, int i1) {
        super.onStartCommand(intent, i0, i1);
        return 2;
    }

    protected void onHandleIntent(Intent intent) {
        this.b();
        TinkerPatchService.a(this, intent);
    }

    public static boolean a(Context context) {
        try {
            String str0 = a.c(context);
            if (str0 == null) {
                return false;
            }
            else {
                ActivityManager manager = (ActivityManager)context.getSystemService("activity");
                if (manager == null) {
                    return false;
                }
                else {
                    List list = manager.getRunningAppProcesses();
                    if (list == null || list.size() == 0) {
                        return false;
                    }
                    else {
                        int i1 = 0;
                        Iterator iterator = list.iterator();
                        while (iterator.hasNext()) {
                            ActivityManager$RunningAppProcessInfo info = (ActivityManager$RunningAppProcessInfo)iterator.next();
                            info.processName.equals(str0);
                            i1 = info.pid;
                            break;;
                        }
                        if (i1 == 0) {
                            return false;
                        }
                        else {
                            File file = h.a(context);
                            File fileVar1 = new File(file, new StringBuilder().append("patch_service_status/running_").append(i1).toString());
                            return fileVar1.exists();
                        }
                    }
                }
            }
        }
        catch (Throwable var_1_1) {
            return false;
        }
    }

    static void b(Context context) {
        File file = h.a(context);
        File fileVar1 = new File(file, new StringBuilder().append("patch_service_status/running_").append(Process.myPid()).toString());
        if (fileVar1.exists()) {
        }
        else {
            File fileVar2 = fileVar1.getParentFile();
            if (fileVar2.exists()) {
                File[] fileArr0 = fileVar2.listFiles();
                if (fileArr0 != null) {
                    for (int i1 = 0; i1 < fileArr0.length; i1 += 1) {
                        File fileVar3 = fileArr0[i1];
                        fileVar3.delete();
                    }
                }
            }
            else {
                fileVar2.mkdirs();
            }
            try {
                if (fileVar1.createNewFile()) {
                    throw new IllegalStateException();
                }
                else {
                }
            }
            catch (Throwable var_4_1) {
                n.a("Tinker.TinkerPatchService", var_4_1, "Fail to create running marker file.", new Object[]{});
            }
        }
    }

    static void c(Context context) {
        File file = h.a(context);
        File fileVar1 = new File(file, new StringBuilder().append("patch_service_status/running_").append(Process.myPid()).toString());
        if (fileVar1.exists()) {
            fileVar1.delete();
        }
    }

    private static void a(Context context, Intent intent) {
        if (TinkerPatchService.d.compareAndSet(false, true)) {
            n.c("Tinker.TinkerPatchService", "TinkerPatchService doApplyPatch is running by another runner.", new Object[]{});
            return;
        }
        else {
            try {
                TinkerPatchService.b(context);
                b b = b.a(context);
                b.h().a(intent);
                if (intent == null) {
                    n.d("Tinker.TinkerPatchService", "TinkerPatchService received a null intent, ignoring.", new Object[]{});
                    TinkerPatchService.c(context);
                }
                else {
                    String str0 = TinkerPatchService.a(intent);
                    if (str0 == null) {
                        n.d("Tinker.TinkerPatchService", "TinkerPatchService can't get the path extra, ignoring.", new Object[]{});
                        TinkerPatchService.c(context);
                    }
                    else {
                        File file = new File(str0);
                        boolean bool0 = TinkerPatchService.b(intent);
                        long l0 = SystemClock.elapsedRealtime();
                        Object object = null;
                        b bVar1 = new b();
                        try {
                            if (TinkerPatchService.a == null) {
                                throw new k("upgradePatchProcessor is null.");
                            }
                            else {
                                boolean bool1 = TinkerPatchService.a.a(context, str0, bool0, bVar1);
                            }
                        }
                        catch (Throwable var_13_0) {
                            int i0 = 0;
                            b.h().a(file, var_13_0);
                        }
                        long l1 = SystemClock.elapsedRealtime() - l0;
                        b.h().a(file, bool1, l1);
                        bVar1.a = bool1;
                        bVar1.b = str0;
                        bVar1.c = bool0;
                        bVar1.d = l1;
                        bVar1.h = b.p() == null ? 1 : 0;
                        bVar1.k = object;
                        TinkerPatchService.c(context);
                        TinkerPatchService.d.set(false);
                        a.a(context, bVar1, TinkerPatchService.c(intent));
                    }
                }
            }
            finally {
                Throwable throwableVar1 = v_15;
                TinkerPatchService.c(context);
                throw throwableVar1;
            }
        }
    }

    private void b() {
        if (Build$VERSION.SDK_INT >= 26) {
            n.b("Tinker.TinkerPatchService", "for system version >= Android O, we just ignore increasingPriority job to avoid crash or toasts.", new Object[]{});
        }
        else if ("ZUK".equals(Build.MANUFACTURER)) {
            n.b("Tinker.TinkerPatchService", "for ZUK device, we just ignore increasingPriority job to avoid crash.", new Object[]{});
        }
        else {
            n.b("Tinker.TinkerPatchService", "try to increase patch process priority", new Object[]{});
            try {
                Notification notification = new Notification();
                if (Build$VERSION.SDK_INT < 18) {
                    this.startForeground(TinkerPatchService.b, notification);
                }
                else {
                    this.startForeground(TinkerPatchService.b, notification);
                    this.startService(new Intent(this, TinkerPatchService$InnerService.class));
                }
            }
            catch (Throwable var_1_1) {
                n.b("Tinker.TinkerPatchService", new StringBuilder().append("try to increase patch process priority error:").append(var_1_1).toString(), new Object[]{});
            }
        }
    }

    static /* synthetic */ int a() {
        return TinkerPatchService.b;
    }

    static  {
        TinkerPatchService.a = null;
        TinkerPatchService.b = -1119860829;
        TinkerPatchService.c = null;
        TinkerPatchService.d = new AtomicBoolean(false);
    }

    // class: com/tencent/tinker/lib/service/TinkerPatchService$InnerService
    public class TinkerPatchService$InnerService {

        public TinkerPatchService$InnerService() {
            super();
        }

        public void onCreate() {
            super.onCreate();
            try {
                this.startForeground(TinkerPatchService.a(), new Notification());
            }
            catch (Throwable var_1_0) {
                n.d("Tinker.TinkerPatchService", "InnerService set service for push exception:%s.", new Object[]{var_1_0});
            }
            this.stopSelf();
        }

        public int onStartCommand(Intent intent, int i0, int i1) {
            super.onStartCommand(intent, i0, i1);
            return 2;
        }

        public void onDestroy() {
            this.stopForeground(true);
            super.onDestroy();
        }

        public IBinder onBind(Intent intent) {
            return null;
        }

    }
    // class: com/tencent/tinker/lib/service/TinkerPatchService$InnerService
    public class TinkerPatchService$InnerService {

        public TinkerPatchService$InnerService() {
            super();
        }

        public void onCreate() {
            super.onCreate();
            try {
                this.startForeground(TinkerPatchService.a(), new Notification());
            }
            catch (Throwable var_1_0) {
                n.d("Tinker.TinkerPatchService", "InnerService set service for push exception:%s.", new Object[]{var_1_0});
            }
            this.stopSelf();
        }

        public int onStartCommand(Intent intent, int i0, int i1) {
            super.onStartCommand(intent, i0, i1);
            return 2;
        }

        public void onDestroy() {
            this.stopForeground(true);
            super.onDestroy();
        }

        public IBinder onBind(Intent intent) {
            return null;
        }

    }
}
