package com.tencent.tinker.loader;

import com.tencent.tinker.anno.Keep;

/**
 * A simple class used by Tinker to test if a patch has been successfully loaded.
 *
 * In the base APK, this class will have {@link #isPatch} set to {@code false}.
 * In a patch dex, a modified version of this class is included with {@link #isPatch}
 * set to {@code true}.
 *
 * After a patch is applied, if Tinker's ClassLoader correctly loads this class
 * from the patch, accessing {@code TinkerTestDexLoad.isPatch} will return {@code true},
 * indicating successful patch class loading.
 */
@Keep
public class TinkerTestDexLoad {

    /**
     * Flag indicating if this version of the class is from a patch.
     * Initialized to {@code false} in the base APK.
     * Should be {@code true} in the patched version of this class.
     */
    public static boolean isPatch;

    static {
        isPatch = false;
    }

    public TinkerTestDexLoad() {
        super();
    }
}
