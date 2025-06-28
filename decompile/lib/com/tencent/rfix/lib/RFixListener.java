/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/lib;

import androidx.annotation.Keep;

// class: com/tencent/rfix/lib/RFixListener
@Keep
public interface RFixListener {

    void onConfig(boolean p0, int p1, PatchConfig p2);

    void onDownload(boolean p0, int p1, PatchConfig p2, String p3);

    void onInstall(boolean p0, int p1, RFixPatchResult p2);

}
