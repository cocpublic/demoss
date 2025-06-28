/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/lib/service;


// class: com/tencent/tinker/lib/service/TinkerPatchForeService
public class TinkerPatchForeService {

    public TinkerPatchForeService() {
        super();
    }

    public int onStartCommand(Intent intent, int i0, int i1) {
        super.onStartCommand(intent, i0, i1);
        return 2;
    }

    public IBinder onBind(Intent intent) {
        return new TinkerPatchForeService$1(this);
    }

}
