/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/service;


// class: com/tencent/tinker/lib/service/b
public class b implements Serializable {
    public boolean a;
    public String b;
    public boolean c;
    public long d;
    public long e;
    public long f;
    public long g;
    public int h;
    public long i;
    public boolean j;
    public Throwable k;
    public String l;

    public b() {
        super();
        this.h = -1;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("
PatchResult: 
");
        buffer.append(new StringBuilder().append("isSuccess:").append(this.a).append("
").toString());
        buffer.append(new StringBuilder().append("rawPatchFilePath:").append(this.b).append("
").toString());
        buffer.append(new StringBuilder().append("useEmergencyMode:").append(this.c).append("
").toString());
        buffer.append(new StringBuilder().append("costTime:").append(this.d).append("
").toString());
        buffer.append(new StringBuilder().append("dexoptTriggerTime:").append(this.i).append("
").toString());
        buffer.append(new StringBuilder().append("isOatGenerated:").append(this.j).append("
").toString());
        if (this.l != null) {
            buffer.append(new StringBuilder().append("patchVersion:").append(this.l).append("
").toString());
        }
        if (this.k != null) {
            buffer.append(new StringBuilder().append("Throwable:").append(this.k.getMessage()).append("
").toString());
        }
        return buffer.toString();
    }

}
