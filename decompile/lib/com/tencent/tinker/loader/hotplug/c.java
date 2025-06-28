/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/loader/hotplug;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Map$Entry;
import java.io.StringReader;
import java.io.IOException;
import android.content.ContextWrapper;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.IntentFilter;
import android.content.ComponentName;
import android.os.Bundle;
import android.net.Uri;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// class: com/tencent/tinker/loader/hotplug/c
public final class c {
    private static Context a;
    private static String b;
    private static volatile boolean c;
    final private static Map<String, ActivityInfo> d;
    final private static Map<String, IntentFilter> e;
    final private static c$a<ActivityInfo> f;

    public static boolean a(Context context, l l) {
        if (l.a().containsKey("assets/inc_component_meta.txt")) {
            n.b("Tinker.IncrementCompMgr", "package has no incremental component meta, skip init.", new Object[]{});
            return false;
        }
        else {
            while ((context instanceof ContextWrapper)) {
                Context contextVar1 = (ContextWrapper)context.getBaseContext();
                if (contextVar1 == null) {
                    break;;
                }
                else {
                    continue;;
                }
            }
            c.a = contextVar1;
            c.b = contextVar1.getPackageName();
            String str0 = (String)l.a().get("assets/inc_component_meta.txt");
            StringReader reader = new StringReader(str0);
            Object object = null;
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(reader);
                int i1 = parser.getEventType();
                while (i1 != 1) {
                    switch(i1) {
                        case 2: {
                            String str1 = parser.getName();
                            if ("activity".equalsIgnoreCase(str1)) {
                                ActivityInfo info = c.a(contextVar1, parser);
                                c.d.put(info.name, info);
                                continue;;
                            }
                            else if ("service".equalsIgnoreCase(str1)) {
                                continue;;
                            }
                            else if ("receiver".equalsIgnoreCase(str1)) {
                                continue;;
                            }
                            else if ("provider".equalsIgnoreCase(str1)) {
                                continue;;
                            }
                        }
                    }
                    i1 = parser.next();
                }
                c.c = true;
                int i2 = true;
                if (parser != null) {
                    try {
                        parser.setInput(null);
                    }
                    catch (Throwable var_7_1) {
                    }
                }
                h.a(reader);
                return i2;
            }
            catch (XmlPullParserException var_5_1) {
                throw new IOException(var_5_1);
            }
            finally {
                Throwable throwable = v_33;
                if (parser != null) {
                    try {
                        parser.setInput(null);
                    }
                    catch (Throwable var_9_0) {
                    }
                }
                h.a(reader);
                throw throwable;
            }
        }
    }

    private static ActivityInfo a(Context context, XmlPullParser parser) {
        v_0 = new ActivityInfo();
        ActivityInfo info = new ActivityInfo();
        info = context.getApplicationInfo();
        info.applicationInfo = info;
        info.packageName = c.b;
        info.processName = info.processName;
        info.launchMode = 0;
        info.permission = info.permission;
        info.screenOrientation = -1;
        info.taskAffinity = info.taskAffinity;
        if (Build$VERSION.SDK_INT >= 11 && info.flags & 536870912 != 0) {
            v_74.flags = info.flags | 512;
        }
        if (Build$VERSION.SDK_INT >= 21) {
            info.documentLaunchMode = 0;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            info.uiOptions = info.uiOptions;
        }
        c.f.a(context, 0, parser, info);
        int i0 = parser.getDepth();
        while (i1 != 1) {
            int i1 = parser.next();
            if (i1 != 1 ? i1 != 3 : i1 == 3 && parser.getDepth() <= i0) {
                if (i1 == 4) {
                    continue;;
                }
                else {
                    String str0 = parser.getName();
                    if ("intent-filter".equalsIgnoreCase(str0)) {
                        c.a(context, info.name, parser);
                    }
                    else if ("meta-data".equalsIgnoreCase(str0)) {
                        c.a(context, info, parser);
                    }
                    continue;;
                }
            }
        }
        return info;
    }

    private static void a(Context context, String str0, XmlPullParser parser) {
        IntentFilter filter = new IntentFilter();
        String str1 = parser.getAttributeValue(null, "priority");
        if (TextUtils.isEmpty(str1)) {
            filter.setPriority(Integer.decode(str1).intValue());
        }
        String str2 = parser.getAttributeValue(null, "autoVerify");
        if (TextUtils.isEmpty(str2)) {
            try {
                Method method = j.a(IntentFilter.class, "setAutoVerify", new Class[]{Boolean.TYPE});
                method.invoke(filter, new Object[]{Boolean.valueOf("true".equalsIgnoreCase(str2))});
            }
            catch (Throwable var_6_2) {
            }
        }
        int i0 = parser.getDepth();
        while (i1 != 1) {
            int i1 = parser.next();
            if (i1 != 1 ? i1 != 3 : i1 == 3 && parser.getDepth() <= i0) {
                if (i1 == 4) {
                    continue;;
                }
                else {
                    String str3 = parser.getName();
                    if ("action".equals(str3)) {
                        String str4 = parser.getAttributeValue(null, "name");
                        if (str4 != null) {
                            filter.addAction(str4);
                        }
                    }
                    else if ("category".equals(str3)) {
                        String str5 = parser.getAttributeValue(null, "name");
                        if (str5 != null) {
                            filter.addCategory(str5);
                        }
                    }
                    else if ("data".equals(str3)) {
                        String str6 = parser.getAttributeValue(null, "mimeType");
                        if (str6 != null) {
                            try {
                                filter.addDataType(str6);
                            }
                            catch (IntentFilter$MalformedMimeTypeException var_10_1) {
                                throw new XmlPullParserException("bad mimeType", parser, var_10_1);
                            }
                        }
                        String str7 = parser.getAttributeValue(null, "scheme");
                        if (str7 != null) {
                            filter.addDataScheme(str7);
                        }
                        if (Build$VERSION.SDK_INT >= 19) {
                            String str8 = parser.getAttributeValue(null, "ssp");
                            if (str8 != null) {
                                filter.addDataSchemeSpecificPart(str8, 0);
                            }
                            String str9 = parser.getAttributeValue(null, "sspPrefix");
                            if (str9 != null) {
                                filter.addDataSchemeSpecificPart(str9, 1);
                            }
                            String str10 = parser.getAttributeValue(null, "sspPattern");
                            if (str10 != null) {
                                filter.addDataSchemeSpecificPart(str10, 2);
                            }
                        }
                        String str11 = parser.getAttributeValue(null, "host");
                        String str12 = parser.getAttributeValue(null, "port");
                        if (str11 != null) {
                            filter.addDataAuthority(str11, str12);
                        }
                        String str13 = parser.getAttributeValue(null, "path");
                        if (str13 != null) {
                            filter.addDataPath(str13, 0);
                        }
                        String str14 = parser.getAttributeValue(null, "pathPrefix");
                        if (str14 != null) {
                            filter.addDataPath(str14, 1);
                        }
                        String str15 = parser.getAttributeValue(null, "pathPattern");
                        if (str15 != null) {
                            filter.addDataPath(str15, 2);
                        }
                    }
                    c.a(parser);
                    continue;;
                }
            }
        }
        c.e.put(str0, filter);
    }

    private static void a(Context context, ActivityInfo info, XmlPullParser parser) {
        ClassLoader loader = c.class.getClassLoader();
        String str0 = parser.getAttributeValue(null, "name");
        String str1 = parser.getAttributeValue(null, "value");
        if (TextUtils.isEmpty(str0)) {
            if (info.metaData == null) {
                info.metaData = new Bundle(loader);
            }
            info.metaData.putString(str0, str1);
        }
    }

    private static void a(XmlPullParser parser) {
        int i0 = parser.getDepth();
        while (true) {
            int i1 = parser.next();
            if (parser.next() != 1) {
                if (i1 == 3) {
                    if (parser.getDepth() > i0) {
                    }
                }
            }
        }
    }

    private static void b() {
        if (c.c) {
            throw new IllegalStateException("Not initialized!!");
        }
        else {
        }
    }

    public static boolean a(String str0) {
        c.b();
        if (str0 != null && c.d.containsKey(str0)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static ActivityInfo b(String str0) {
        c.b();
        if (str0 != null) {
            return (ActivityInfo)c.d.get(str0);
        }
        else {
            return null;
        }
    }

    public static ResolveInfo a(Intent intent) {
        c.b();
        int i6 = -1;
        Object object = null;
        Object objectVar1 = null;
        int i7 = 0;
        ComponentName name = intent.getComponent();
        if (name != null) {
            String str0 = name.getClassName();
            if (c.d.containsKey(str0)) {
                i6 = 0;
            }
            goto 209;
        }
        else {
            Iterator iterator = c.e.entrySet().iterator();
            while (iterator.hasNext()) {
                Map$Entry entry = (Map$Entry)iterator.next();
                String str1 = (String)entry.getKey();
                IntentFilter filter = (IntentFilter)entry.getValue();
                int i3 = filter.match(intent.getAction(), intent.getType(), intent.getScheme(), intent.getData(), intent.getCategories(), "Tinker.IncrementCompMgr");
                int i4 = i3 != 253 && i3 != 252 && i3 != 254 && i3 != -1 ? 0 : 1;
                int i5 = filter.getPriority();
                if (i4 != 0 && i5 > i6) {
                    object = str1;
                }
            }
        }
        if (str0 != null) {
            ResolveInfo info = new ResolveInfo();
            info.activityInfo = (ActivityInfo)c.d.get(str0);
            info.filter = filter;
            info.match = i3;
            info.priority = i5;
            info.resolvePackageName = c.b;
            info.icon = info.activityInfo.icon;
            info.labelRes = info.activityInfo.labelRes;
            return info;
        }
        else {
            return null;
        }
    }

    private c() {
        super();
        throw new UnsupportedOperationException();
    }

    static /* synthetic */ String a() {
        return c.b;
    }

    static  {
        c.a = null;
        c.b = null;
        c.c = false;
        c.d = new HashMap();
        c.e = new HashMap();
        c.f = new c$1();
    }

    // class: com/tencent/tinker/loader/hotplug/c$a
    abstract class c$a<T_RESULT> {

        private c$a() {
            super();
        }

        final void a(Context context, int i0, XmlPullParser parser, T_RESULT object) {
            this.a(context, i0, parser);
            int i1 = parser.getAttributeCount();
            for (int i2 = 0; i2 < i1; i2 += 1) {
                String str0 = parser.getAttributePrefix(i2);
                if ("android".equals(str0)) {
                    continue;;
                }
                else {
                    String str1 = parser.getAttributeName(i2);
                    String str2 = parser.getAttributeValue(i2);
                    this.a(context, i0, str1, str2, object);
                }
            }
        }

        void a(Context context, int i0, XmlPullParser parser) {
        }

        void a(Context p0, int p1, String p2, String p3, T_RESULT p4);

        /* synthetic */ c$a(c$1 c$1) {
            super();
        }

    }
    // class: com/tencent/tinker/loader/hotplug/c$a
    abstract class c$a<T_RESULT> {

        private c$a() {
            super();
        }

        final void a(Context context, int i0, XmlPullParser parser, T_RESULT object) {
            this.a(context, i0, parser);
            int i1 = parser.getAttributeCount();
            for (int i2 = 0; i2 < i1; i2 += 1) {
                String str0 = parser.getAttributePrefix(i2);
                if ("android".equals(str0)) {
                    continue;;
                }
                else {
                    String str1 = parser.getAttributeName(i2);
                    String str2 = parser.getAttributeValue(i2);
                    this.a(context, i0, str1, str2, object);
                }
            }
        }

        void a(Context context, int i0, XmlPullParser parser) {
        }

        void a(Context p0, int p1, String p2, String p3, T_RESULT p4);

        /* synthetic */ c$a(c$1 c$1) {
            super();
        }

    }
}
