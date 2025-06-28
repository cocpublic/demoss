/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/entry;

import com.tencent.tinker.anno.Keep;

// class: com/tencent/tinker/entry/ApplicationLifeCycle
@Keep
public interface ApplicationLifeCycle {

    void onCreate();

    void onLowMemory();

    void onTrimMemory(int p0);

    void onTerminate();

    void onConfigurationChanged(Configuration p0);

    void onBaseContextAttached(Context p0);

}
