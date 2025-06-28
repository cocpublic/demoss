/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib/b;

import com.tencent.rfix.loader.c.b;
import java.util.Calendar;
import java.util.Date;
import androidx.annotation.NonNull;

// class: com/tencent/rfix/lib/b/a
public class a {
    private int a;
    private int b;
    private int c;
    private long d;
    private long e;
    private long f;

    public a(Context context) {
        super(context, "task_covered_record");
    }

    public void a(int i0) {
        this.a = i0;
        this.d = System.currentTimeMillis();
    }

    public void b(int i0) {
        this.b = i0;
        this.e = System.currentTimeMillis();
    }

    public void c(int i0) {
        this.c = i0;
        this.f = System.currentTimeMillis();
    }

    public int a() {
        if (super.a(this.d)) {
            return this.a;
        }
        else {
            return 0;
        }
    }

    public int d() {
        if (this.a(this.e)) {
            return this.b;
        }
        else {
            return 0;
        }
    }

    public int e() {
        if (this.a(this.f)) {
            return this.c;
        }
        else {
            return 0;
        }
    }

    public void b() {
        super.b();
        this.a = this.p.a("config_last_report_version", 0);
        this.b = this.p.a("install_last_report_version", 0);
        this.c = this.p.a("load_last_report_version", 0);
        this.d = this.p.a("config_last_report_time", 0L);
        this.e = this.p.a("install_last_report_time", 0L);
        this.f = this.p.a("load_last_report_time", 0L);
        RFixLog.c("RFix.TaskCoveredRecord", new StringBuilder().append("loadStoreInfo ").append(this).toString());
    }

    public void c() {
        this.p.b("config_last_report_version", this.a);
        this.p.b("install_last_report_version", this.b);
        this.p.b("load_last_report_version", this.c);
        this.p.b("config_last_report_time", this.d);
        this.p.b("install_last_report_time", this.e);
        this.p.b("load_last_report_time", this.f);
        super.c();
        RFixLog.c("RFix.TaskCoveredRecord", new StringBuilder().append("saveStoreInfo ").append(this).toString());
    }

    @NonNull
    public String toString() {
        return new StringBuilder().append("TaskCoveredRecord{lastReportConfigVersion=").append(this.a).append(", lastReportInstallVersion=").append(this.b).append(", lastReportLoadVersion=").append(this.c).append(", lastReportConfigTime=").append(this.d).append(", lastReportInstallTime=").append(this.e).append(", lastReportLoadTime=").append(this.f).append(125).toString();
    }

    private boolean a(long l1) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendarVar1 = Calendar.getInstance();
        calendarVar1.setTime(new Date(l1));
        if (calendarVar1.get(1) == calendar.get(1) && calendarVar1.get(6) == calendar.get(6)) {
            return true;
        }
        else {
            return false;
        }
    }

}
