/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/commons/dexpatcher;


// class: com/tencent/tinker/commons/dexpatcher/DexPatcherLogger
public final class DexPatcherLogger {
    private DexPatcherLogger$IDexPatcherLogger loggerImpl;

    public DexPatcherLogger() {
        super();
        this.loggerImpl = null;
    }

    public DexPatcherLogger$IDexPatcherLogger getLoggerImpl() {
        return this.loggerImpl;
    }

    public void setLoggerImpl(DexPatcherLogger$IDexPatcherLogger dexPatcherLogger) {
        this.loggerImpl = dexPatcherLogger;
    }

    public void v(String tag, String v_19, Object[] vals) {
        if (this.loggerImpl != null) {
            v_19 = new StringBuilder().append("[V][").append(tag).append("] ").append(v_19).toString();
            this.loggerImpl.v(String.format(vals == null || vals.length == 0 ? v_19 : v_19, vals));
        }
    }

    public void d(String tag, String v_19, Object[] vals) {
        if (this.loggerImpl != null) {
            v_19 = new StringBuilder().append("[D][").append(tag).append("] ").append(v_19).toString();
            this.loggerImpl.d(String.format(vals == null || vals.length == 0 ? v_19 : v_19, vals));
        }
    }

    public void i(String tag, String v_19, Object[] vals) {
        if (this.loggerImpl != null) {
            v_19 = new StringBuilder().append("[I][").append(tag).append("] ").append(v_19).toString();
            this.loggerImpl.i(String.format(vals == null || vals.length == 0 ? v_19 : v_19, vals));
        }
    }

    public void w(String tag, String v_19, Object[] vals) {
        if (this.loggerImpl != null) {
            v_19 = new StringBuilder().append("[W][").append(tag).append("] ").append(v_19).toString();
            this.loggerImpl.w(String.format(vals == null || vals.length == 0 ? v_19 : v_19, vals));
        }
    }

    public void e(String tag, String v_19, Object[] vals) {
        if (this.loggerImpl != null) {
            v_19 = new StringBuilder().append("[E][").append(tag).append("] ").append(v_19).toString();
            this.loggerImpl.e(String.format(vals == null || vals.length == 0 ? v_19 : v_19, vals));
        }
    }

    // class: com/tencent/tinker/commons/dexpatcher/DexPatcherLogger$IDexPatcherLogger
    public interface DexPatcherLogger$IDexPatcherLogger {

        void v(String p0);

        void d(String p0);

        void i(String p0);

        void w(String p0);

        void e(String p0);

    }
    // class: com/tencent/tinker/commons/dexpatcher/DexPatcherLogger$IDexPatcherLogger
    public interface DexPatcherLogger$IDexPatcherLogger {

        void v(String p0);

        void d(String p0);

        void i(String p0);

        void w(String p0);

        void e(String p0);

    }
}
