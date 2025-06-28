/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

// class: com/tencent/rfix/lib/config/c
public class c {
    final private int a;
    final private String b;
    final private List<c$a> c;
    final private Map<String, String> d;

    public c(int i0, String str0) {
        super();
        this.a = i0;
        this.b = str0;
        this.c = new ArrayList();
        this.d = new HashMap();
        this.c();
    }

    public int a() {
        return this.a;
    }

    public c$a a(Context context) {
        String str0 = d.a(context);
        for (Iterator iterator = this.c.iterator(); iterator.hasNext(); i1 += 1) {
            c$a c$a = (c$a)iterator.next();
            if (TextUtils.isEmpty(c$a.c)) {
                String[] stringArr0 = c$a.c.split(",");
                int i1 = 0;
                while (i1 < stringArr0.length) {
                    String str1 = stringArr0[i1];
                    if (TextUtils.equals(str0, str1)) {
                        return c$a;
                    }
                    else {
                    }
                }
            }
        }
        RFixLog.e("RFix.RDeliveryConfig", "getMatchResource no one match, return first.");
        if (this.c.isEmpty()) {
            return null;
        }
        else {
            return (c$a)this.c.get(0);
        }
    }

    public String b() {
        String str0 = (String)this.d.get("patch_process");
        if (TextUtils.isEmpty(str0)) {
            return null;
        }
        else {
            String[] stringArr0 = str0.split("\|");
            if (stringArr0.length == 0) {
                return null;
            }
            else {
                ArrayList list = new ArrayList();
                for (int i1 = 0; i1 < stringArr0.length; i1 += 1) {
                    String str2 = stringArr0[i1];
                    str2 = str2.trim();
                    if (TextUtils.isEmpty(str2)) {
                        list.add(str2);
                    }
                }
                StringBuilder builder = new StringBuilder();
                i3 = 1;
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    String str3 = (String)iterator.next();
                    if (stringArr0.length != 0) {
                        i3 = 0;
                        builder.append(str3);
                        continue;;
                    }
                    else {
                        builder.append("|").append(str3);
                    }
                }
                return builder.toString();
            }
        }
    }

    private void c() {
        JSONObject object = new JSONObject(this.b);
        JSONArray array = object.optJSONArray("resources");
        if (array != null) {
            for (int i0 = 0; i0 < array.length(); i0 += 1) {
                JSONObject objectVar1 = array.optJSONObject(i0);
                if (objectVar1 != null) {
                    c$a c$a = new c$a();
                    c$a.a = objectVar1.optString("url");
                    c$a.b = objectVar1.optString("md5");
                    c$a.c = objectVar1.optString("identify_id");
                    this.c.add(c$a);
                }
            }
        }
        JSONObject objectVar2 = object.optJSONObject("custom_content");
        if (objectVar2 != null) {
            JSONObject objectVar3 = objectVar2.optJSONObject("data");
            if (objectVar3 != null) {
                Iterator iterator = objectVar3.keys();
                while (iterator.hasNext()) {
                    String str0 = (String)iterator.next();
                    String str1 = objectVar3.optString(str0);
                    this.d.put(str0, str1);
                }
            }
        }
    }

    public String toString() {
        return new StringBuilder().append("RDeliveryConfig{configId=").append(this.a).append(", content='").append(this.b).append(39).append(125).toString();
    }

    // class: com/tencent/rfix/lib/config/c$a
    public class c$a {
        public String a;
        public String b;
        public String c;

        public c$a() {
            super();
        }

    }
    // class: com/tencent/rfix/lib/config/c$a
    public class c$a {
        public String a;
        public String b;
        public String c;

        public c$a() {
            super();
        }

    }
}
