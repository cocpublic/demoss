/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/loader/log;

import android.os.Looper;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.tencent.rfix.loader.d.a;
import androidx.annotation.Keep;

// class: com/tencent/rfix/loader/log/RFixLogInlineFence
public final class RFixLogInlineFence {
    final private Handler a;
    final private List<Object[]> b;

    @Keep
    public RFixLogInlineFence() {
        super(Looper.getMainLooper());
        this.a = new Handler(Looper.getMainLooper());
        this.b = new ArrayList();
    }

    public void handleMessage(Message message) {
        this.a(message);
    }

    private void a(Message message) {
        try {
            super.a();
            return;
        }
        finally {
            Throwable throwable = v_3;
            this.b(message);
            throw throwable;
        }
    }

    private void a() {
        if (RFixLogInlineFence.class.isPrimitive()) {
            throw new RuntimeException();
        }
        else {
        }
    }

    private void b(Message message) {
IRFixLog log = RFixLog.a();
boolean bool0 = RFixLog.b();
        switch(message.what) {
            case 2: {
                Object[] objectArr0 = (Object[])message.obj;
                this.a(log, objectArr0, 0);
                if (bool0) {
                    List list = this.b;
                    this.b;
                    synchronized () {
                        this.b.add(objectArr0);
                    }
                }
            }
            case 240: {
                this.a(log);
            }
        }
    }

    private void a(IRFixLog log, Object[] objectArr0, boolean bool0) {
        if (super.a(log, objectArr0)) {
        }
        else {
            try {
                int i0 = (Integer)objectArr0[0].intValue();
                long l0 = (Long)objectArr0[1].longValue();
                String str0 = (String)objectArr0[2];
                String str5 = (String)objectArr0[3];
                Throwable throwable = (Throwable)objectArr0[4];
                if (bool0) {
                    String str2 = "yyyy-MM-dd HH:mm:ss.SSS";
                    SimpleDateFormat format = new SimpleDateFormat(str2, Locale.ENGLISH);
                    String str3 = format.format(new Date(l0));
                    String str4 = new StringBuilder().append("[PendingLog @ ").append(str3).append("] ").toString();
                    str5 = new StringBuilder().append(str4).append(str5).toString();
                }
                switch(i0) {
                    case 2: {
                        log.v(str0, str5);
                        break;;
                    }
                    case 3: {
                        log.d(str0, str5);
                        break;;
                    }
                    case 4: {
                        log.i(str0, str5);
                        break;;
                    }
                    case 5: {
                        super.a(log, str0, str5, throwable);
                        break;;
                    }
                    case 6: {
                        this.b(log, str0, str5, throwable);
                        break;;
                    }
                }
            }
            catch (Exception var_4_1) {
            }
        }
    }

    private boolean a(IRFixLog log, Object[] objectArr0) {
        if (log != null && objectArr0 != null && objectArr0.length == 5) {
            return true;
        }
        else {
            return false;
        }
    }

    private void a(IRFixLog log, String str0, String str1, Throwable throwable) {
        if (throwable == null) {
            log.w(str0, str1);
        }
        else {
            log.w(str0, str1, throwable);
        }
    }

    private void b(IRFixLog log, String str0, String str1, Throwable throwable) {
        if (throwable == null) {
            log.e(str0, str1);
        }
        else {
            log.e(str0, str1, throwable);
        }
    }

    private void a(IRFixLog log) {
        List list = this.b;
        this.b;
        synchronized () {
            if (log == null || this.b.isEmpty()) {
            }
            else {
            }
        }
        b.a().a(new RFixLogInlineFence$1(this, log));
    }

    static /* synthetic */ List a(RFixLogInlineFence fence) {
        return fence.b;
    }

    static /* synthetic */ void a(RFixLogInlineFence fence, IRFixLog log, Object[] objectArr0, boolean bool0) {
        fence.a(log, objectArr0, bool0);
    }

    static /* synthetic */ Handler b(RFixLogInlineFence fence) {
        return fence.a;
    }

}
