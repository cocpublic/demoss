/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/shareutil;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;
import android.content.SharedPreferences;
import android.content.SharedPreferences$Editor;
import android.os.Bundle;
import android.app.ActivityManager;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.annotation.SuppressLint;
import com.tencent.tinker.loader.k;

// class: com/tencent/tinker/loader/shareutil/m
public class m {
    final private static boolean a;
    final private static boolean b;
    private static Boolean c;
    private static Boolean d;
    final private static String[] e;
    private static String f;
    private static String g;

    public static boolean a() {
        if (! m.a || Build$VERSION.SDK_INT >= 21) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean b() {
        if (m.b && Build$VERSION.SDK_INT < 24) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean c() {
        if (m.d != null) {
            return m.d.booleanValue();
        }
        else {
            m.d = Boolean.valueOf(false);
            Object object = null;
            try {
                Class class = ClassLoader.getSystemClassLoader().getParent().loadClass("com.huawei.ark.app.ArkApplicationInfo");
                Object objectVar1 = null;
                Method method = class.getDeclaredMethod("isRunningInArk", new Class[]{});
                method.setAccessible(true);
                m.d = (Boolean)method.invoke(null, new Object[]{});
            }
            catch (ClassNotFoundException var_1_2) {
                n.b("Tinker.TinkerInternals", "class not found exception", new Object[]{});
            }
            catch (NoSuchMethodException var_1_3) {
                n.b("Tinker.TinkerInternals", "no such method exception", new Object[]{});
            }
            catch (SecurityException var_1_4) {
                n.b("Tinker.TinkerInternals", "security exception", new Object[]{});
            }
            catch (IllegalAccessException var_1_5) {
                n.b("Tinker.TinkerInternals", "illegal access exception", new Object[]{});
            }
            catch (InvocationTargetException var_1_6) {
                n.b("Tinker.TinkerInternals", "invocation target exception", new Object[]{});
            }
            catch (IllegalArgumentException var_1_7) {
                n.b("Tinker.TinkerInternals", "illegal argument exception", new Object[]{});
            }
            return m.d.booleanValue();
        }
    }

    public static boolean d() {
        if (Build$VERSION.SDK_INT > 25) {
            return true;
        }
        else {
            return false;
        }
    }

    public static String e() {
        if (m.g != null) {
            return m.g;
        }
        else {
            try {
                Class class = Class.forName("dalvik.system.VMRuntime");
                Method method = class.getDeclaredMethod("getCurrentInstructionSet", new Class[]{});
                method.setAccessible(true);
                m.g = (String)method.invoke(null, new Object[]{});
            }
            catch (Throwable var_0_1) {
int i7 = -1;
                switch(Build.CPU_ABI.hashCode()) {
                    case -738963905: {
                        if (Build.CPU_ABI.equals("armeabi")) {
                            i7 = 0;
                            break;;
                        }
                    }
                    case 145444210: {
                        if (Build.CPU_ABI.equals("armeabi-v7a")) {
                            i7 = 1;
                            break;;
                        }
                    }
                    case 1431565292: {
                        if (Build.CPU_ABI.equals("arm64-v8a")) {
                            i7 = 2;
                            break;;
                        }
                    }
                    case 117110: {
                        if (Build.CPU_ABI.equals("x86")) {
                            i7 = 3;
                            break;;
                        }
                    }
                    case -806050265: {
                        if (Build.CPU_ABI.equals("x86_64")) {
                            i7 = 4;
                            break;;
                        }
                    }
                    case 3351711: {
                        if (Build.CPU_ABI.equals("mips")) {
                            i7 = 5;
                            break;;
                        }
                    }
                    case -1073971299: {
                        if (Build.CPU_ABI.equals("mips64")) {
                            i7 = 6;
                        }
                    }
                }
                switch(i7) {
                    case 0: {
                        m.g = "arm";
                        break;;
                    }
                    case 2: {
                        m.g = "arm64";
                        break;;
                    }
                    case 3: {
                        m.g = "x86";
                        break;;
                    }
                    case 4: {
                        m.g = "x86_64";
                        break;;
                    }
                    case 5: {
                        m.g = "mips";
                        break;;
                    }
                    case 6: {
                        m.g = "mips64";
                        break;;
                    }
                    default: {
                        throw new IllegalStateException(new StringBuilder().append("Unsupported abi: ").append(Build.CPU_ABI).toString());
                    }
                }
            }
            n.a("Tinker.TinkerInternals", new StringBuilder().append("getCurrentInstructionSet:").append(m.g).toString(), new Object[]{});
            return m.g;
        }
    }

    public static boolean f() {
        String str0 = m.e();
        if ("arm".equals(str0) || "x86".equals(str0) || "mips".equals(str0)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean a(String str0) {
        if (str0 != null && str0.equals("") || Build.FINGERPRINT != null || Build.FINGERPRINT.equals("")) {
            n.a("Tinker.TinkerInternals", new StringBuilder().append("fingerprint empty:").append(str0).append(",current:").append(Build.FINGERPRINT).toString(), new Object[]{});
            return false;
        }
        else if (str0.equals(Build.FINGERPRINT)) {
            n.a("Tinker.TinkerInternals", new StringBuilder().append("same fingerprint:").append(Build.FINGERPRINT).toString(), new Object[]{});
            return false;
        }
        else {
            n.a("Tinker.TinkerInternals", new StringBuilder().append("system OTA,fingerprint not equal:").append(str0).append(",").append(Build.FINGERPRINT).toString(), new Object[]{});
            return true;
        }
    }

    public static d a(d d, int i0) {
        if (d.a.startsWith("test.dex")) {
            String str0 = i0 != 1 ? "classes.dex" : new StringBuilder().append("classes").append(i0).append(".dex").toString();
            return new d(var_2_0, d.g, d.b, d.c, d.f, d.d, d.e, d.h);
        }
        else {
            return null;
        }
    }

    public static boolean b(String str0) {
        if (str0 == null || str0.length() <= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static int a(Context context, int i0, File file, l l) {
        int i2 = m.a(context, file, l);
        if (i2 == 0) {
            i2 = m.a(l, i0);
        }
        return i2;
    }

    public static int a(Context context, File file, l l) {
        if (l.a(file)) {
            return -1;
        }
        else {
            String str0 = m.e(context);
            if (str0 == null) {
                return 251;
            }
            else {
                HashMap map = l.b();
                if (map == null) {
                    return 254;
                }
                else {
                    String str1 = (String)map.get("TINKER_ID");
                    if (str1 == null) {
                        return 250;
                    }
                    else if (str0.equals(str1)) {
                        n.d("Tinker.TinkerInternals", "tinkerId in patch is not matched with the one in base pack, base: %s, patch: %s.", new Object[]{str0, str1});
                        return 249;
                    }
                    else {
                        return 0;
                    }
                }
            }
        }
    }

    public static int a(l l, int i0) {
        if (m.g(i0)) {
            return 0;
        }
        else {
            HashMap map = l.a();
            boolean bool0 = m.a(i0);
            if (bool0 && map.containsKey("assets/dex_meta.txt")) {
                return 247;
            }
            else {
                boolean bool1 = m.b(i0);
                if (bool1 && map.containsKey("assets/so_meta.txt")) {
                    return 247;
                }
                else {
                    boolean bool2 = m.c(i0);
                    if (bool2 && map.containsKey("assets/res_meta.txt")) {
                        return 247;
                    }
                    else {
                        return 0;
                    }
                }
            }
        }
    }

    public static String e(Context context) {
        if (m.f != null) {
            return m.f;
        }
        else {
            try {
                ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                Object object = info.metaData.get("TINKER_ID");
                m.f = object != null ? null : String.valueOf(object);
            }
            catch (Exception var_1_1) {
                n.d("Tinker.TinkerInternals", new StringBuilder().append("getManifestTinkerID exception:").append(var_1_1.getMessage()).toString(), new Object[]{});
                return null;
            }
            return m.f;
        }
    }

    public static boolean a(int i0) {
        if (i0 & 1 != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean b(int i0) {
        if (i0 & 2 != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean c(int i0) {
        if (i0 & 4 != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean d(int i0) {
        if (i0 & 8 != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static String e(int i0) {
        switch(i0) {
            case 3: {
                return "dex";
            }
            case 4: {
                return "dex_opt";
            }
            case 5: {
                return "lib";
            }
            case 1: {
                return "patch_file";
            }
            case 2: {
                return "patch_info";
            }
            case 6: {
                return "resource";
            }
            default: {
                return "unknown";
            }
        }
    }

    public static void f(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("tinker_share_config", 4);
        String str0 = m.a(context);
        preferences.edit().putBoolean(str0, 0).commit();
    }

    public static boolean g(Context context) {
        if (context == null) {
            return false;
        }
        else {
            SharedPreferences preferences = context.getSharedPreferences("tinker_share_config", 4);
            String str0 = m.a(context);
            return preferences.getBoolean(str0, 1);
        }
    }

    private static String a(Context context) {
        String str1 = m.e(context);
        if (m.b(str1)) {
            str1 = "@@";
        }
        return new StringBuilder().append("tinker_enable_1.9.14.20(RFix)_").append(str1).toString();
    }

    public static int h(Context context) {
        String str0 = m.l(context);
        String str1 = new StringBuilder().append("safemode_count_rec_").append(str0).toString();
        File file = new File(h.a(context), str1);
        Object object = null;
        try {
            DataInputStream stream = new DataInputStream(new FileInputStream(file));
            String str2 = "safe_mode_count_1.9.14.20(RFix)";
            String str3 = stream.readUTF();
            if ("safe_mode_count_1.9.14.20(RFix)".equals(str3)) {
                n.c("Tinker.TinkerInternals", "getSafeModeCount: key is not equal, expt: %s, actul: %s, return 0 instead.", new Object[]{"safe_mode_count_1.9.14.20(RFix)", str3});
                int i0 = 0;
                h.a(stream);
                return i0;
            }
            else {
                int i1 = stream.readInt();
                n.b("Tinker.TinkerInternals", "getSafeModeCount: count: %s", new Object[]{Integer.valueOf(i1)});
                h.a(stream);
                return i1;
            }
        }
        catch (Throwable var_5_1) {
            n.c("Tinker.TinkerInternals", new StringBuilder().append("getSafeModeCount: recFileName:").append(str1).append(" failed, return 0 instead.").toString(), new Object[]{});
            int i3 = 0;
            h.a(stream);
            return i3;
        }
        finally {
            Throwable throwable = v_23;
            h.a(stream);
            throw throwable;
        }
    }

    public static void a(Context context, int i0) {
        String str0 = m.l(context);
        String str1 = new StringBuilder().append("safemode_count_rec_").append(str0).toString();
        File file = new File(h.a(context), str1);
        if (file.exists()) {
            file.getParentFile().mkdirs();
        }
        Object object = null;
        try {
            DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));
            String str2 = "safe_mode_count_1.9.14.20(RFix)";
            stream.writeUTF("safe_mode_count_1.9.14.20(RFix)");
            stream.writeInt(i0);
            n.b("Tinker.TinkerInternals", "setSafeModeCount: count: %s", new Object[]{Integer.valueOf(i0)});
            return;
        }
        catch (Throwable var_6_1) {
            n.c("Tinker.TinkerInternals", new StringBuilder().append("setSafeModeCount: recFileName:").append(str1).append(" failed, return 0 instead.").toString(), new Object[]{});
            return;
        }
        finally {
            Throwable throwable = v_35;
            h.a(stream);
            throw throwable;
        }
    }

    public static boolean f(int i0) {
        if (i0 != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean g(int i0) {
        if (i0 == 15) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean i(Context context) {
        return o.a(context);
    }

    public static boolean j(Context context) {
        if (m.c != null) {
            return m.c.booleanValue();
        }
        else {
            m.c = Boolean.valueOf(m.l(context).endsWith(":patch"));
            return m.c.booleanValue();
        }
    }

    public static String a(Context context, String str0) {
        if (str0.equals("changing")) {
            str0 = m.i(context) ? "interpet" : "odex";
        }
        return str0;
    }

    public static void k(Context context) {
        ActivityManager manager = (ActivityManager)context.getSystemService("activity");
        if (manager == null) {
        }
        else {
            List list = manager.getRunningAppProcesses();
            if (list != null) {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    ActivityManager$RunningAppProcessInfo info = (ActivityManager$RunningAppProcessInfo)iterator.next();
                    if (info.uid != Process.myUid()) {
                        continue;;
                    }
                    else if (o.a(context, info.processName)) {
                        continue;;
                    }
                    else {
                        Process.killProcess(info.pid);
                        continue;;
                    }
                }
            }
        }
    }

    public static String l(Context context) {
        if (m.e[0] == null) {
            m.e;
            synchronized () {
                if (m.e[0] == null) {
                    m.e[0] = m.b(context);
                }
            }
        }
        if (m.e[0] != null) {
            return m.e[0];
        }
        else {
            return "";
        }
    }

    @SuppressLint({"NewApi"})
    private static String b(Context context) {
        if (m.a(28, 1)) {
            String str0 = Application.getProcessName();
            if (TextUtils.isEmpty(str0)) {
                return str0;
            }
        }
        if (m.a(18, 1)) {
            try {
                Class class = Class.forName("android.app.ActivityThread");
                Method method = j.a(class, "currentProcessName", new Class[]{});
                method.setAccessible(true);
                String str1 = (String)method.invoke(null, new Object[]{});
                if (str1 != null && str1.isEmpty()) {
                    return str1;
                }
                else {
                }
            }
            catch (Throwable var_1_4) {
                n.d("Tinker.TinkerInternals", new StringBuilder().append("getProcessNameInternal reflect activity thread exception:").append(var_1_4.getMessage()).toString(), new Object[]{});
            }
        }
        Object object = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/self/cmdline"), StandardCharsets.US_ASCII));
            String str3 = reader.readLine();
            if (str3 != null) {
                str3 = str3.trim();
                if (str3.isEmpty()) {
                    h.a(reader);
                    return str3;
                }
            }
            goto 227;
        }
        catch (Throwable var_2_3) {
            n.d("Tinker.TinkerInternals", new StringBuilder().append("getProcessNameInternal parse cmdline exception:").append(var_2_3.getMessage()).toString(), new Object[]{});
            goto 227;
        }
        finally {
            Throwable throwable = v_40;
            h.a(reader);
            throw throwable;
        }
        if (context != null) {
            try {
                int i0 = Process.myPid();
                int i1 = Process.myUid();
                ActivityManager manager = (ActivityManager)context.getSystemService("activity");
                if (manager != null) {
                    List list = manager.getRunningAppProcesses();
                    if (list != null) {
                        Iterator iterator = list.iterator();
                        while (iterator.hasNext()) {
                            ActivityManager$RunningAppProcessInfo info = (ActivityManager$RunningAppProcessInfo)iterator.next();
                            if (info.pid == i0 && info.uid == i1) {
                                return info.processName;
                            }
                            else {
                                continue;;
                            }
                        }
                    }
                }
            }
            catch (Throwable var_2_4) {
                n.d("Tinker.TinkerInternals", new StringBuilder().append("getProcessNameInternal getRunningAppProcesses exception:").append(var_2_4.getMessage()).toString(), new Object[]{});
            }
        }
        return null;
    }

    private static boolean d(String str0) {
        int i3 = false;
        if (str0 != null) {
            Matcher matcher = Pattern.compile("(\d+)\.(\d+)(\.\d+)?").matcher(str0);
            if (matcher.matches()) {
                try {
                    int i1 = Integer.parseInt(matcher.group(1));
                    int i2 = Integer.parseInt(matcher.group(2));
                    if (i1 <= 2) {
                    }
                    i3 = i1 == 2 && i2 >= 1 ? 0 : 1;
                }
                catch (NumberFormatException var_3_1) {
                }
            }
        }
        return i3;
    }

    private static boolean g() {
        try {
            Class class = Class.forName("android.os.SystemProperties");
            Method method = class.getDeclaredMethod("get", new Class[]{String.class});
            String str0 = (String)method.invoke(null, new Object[]{"dalvik.vm.usejit"});
            String str1 = (String)method.invoke(null, new Object[]{"dalvik.vm.usejitprofiles"});
            if (m.b(str0) && m.b(str1) && str0.equals("true")) {
                return true;
            }
            else {
            }
        }
        catch (Throwable var_0_1) {
            n.d("Tinker.TinkerInternals", new StringBuilder().append("isVmJitInternal ex:").append(var_0_1).toString(), new Object[]{});
        }
        return false;
    }

    public static boolean a(int i0, boolean bool0) {
        if (bool0 && Build$VERSION.SDK_INT >= 23) {
            if (Build$VERSION.SDK_INT < i0) {
                if (Build$VERSION.SDK_INT == i0 - 1 && Build$VERSION.PREVIEW_SDK_INT > 0) {
                    return false;
                }
            }
            return true;
        }
        else if (Build$VERSION.SDK_INT >= i0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean b(int i0, boolean bool0) {
        if (bool0 && Build$VERSION.SDK_INT >= 23) {
            if (Build$VERSION.SDK_INT > i0) {
                if (Build$VERSION.SDK_INT == i0 - 1 && Build$VERSION.PREVIEW_SDK_INT > 0) {
                    return false;
                }
            }
            return true;
        }
        else if (Build$VERSION.SDK_INT <= i0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean a(int bool0, int bool1, boolean bool2) {
        if (m.a(bool0, bool2) && m.b(bool1, bool2)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static String a(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        else {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream = new PrintStream(stream);
            try {
                while (true) {
                    Throwable throwableVar2 = throwable.getCause();
                    if (throwableVar2 == null) {
                        throwable.printStackTrace(stream);
                        String str0 = m.c(stream.toString());
                        h.a(stream);
                        return str0;
                    }
                    else {
                        throwableVar3 = throwableVar2;
                    }
                }
            }
            finally {
                Throwable throwableVar4 = v_6;
                h.a(stream);
                throw throwableVar4;
            }
        }
    }

    public static String c(String str0) {
        int i2 = 0;
        if (null == str0) {
            return null;
        }
        else {
            char[] charArr0 = str0.toCharArray();
            if (null == charArr0) {
                return null;
            }
            else {
                for (int i1 = 0; i1 < charArr0.length; i1 += 1) {
                    if (charArr0[i1] > 127) {
                        charArr0[i1] = 0;
                        i2 = 1;
                        break;;
                    }
                    else {
                    }
                }
                if (i2 != 0) {
                    return new String(charArr0, 0, i1);
                }
                else {
                    return str0;
                }
            }
        }
    }

    public static void m(Context context) {
        if (context == null) {
            throw new k("context is null");
        }
        else {
            File file = h.a(context);
            if (file.exists()) {
                n.a("Tinker.TinkerInternals", new Throwable(), "try to clean patch while there're not any applied patches.", new Object[]{});
            }
            else {
                File fileVar1 = h.a(file.getAbsolutePath());
                if (fileVar1.exists()) {
                    n.a("Tinker.TinkerInternals", new Throwable(), "try to clean patch while patch info file does not exist.", new Object[]{});
                }
                else {
                    File fileVar2 = h.b(file.getAbsolutePath());
                    i i = i.a(fileVar1, fileVar2);
                    if (i != null) {
                        if (i.b.equals(i.a)) {
                            String str0 = h.c(i.b);
                            h.d(new File(file, str0));
                            i.b = i.a;
                            i.e = "";
                        }
                        else {
                            i.e = i.b;
                        }
                        i.a(fileVar1, i, fileVar2);
                    }
                    else {
                        n.a("Tinker.TinkerInternals", new Throwable(), "fail to get patchInfo.", new Object[]{});
                    }
                }
            }
        }
    }

    static  {
        m.a = m.d(System.getProperty("java.vm.version"));
        m.b = m.g();
        m.c = null;
        m.d = null;
        m.e = new String[]{null};
        m.f = null;
        m.g = null;
    }

}
