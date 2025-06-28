package com.tencent.tinker.loader;

import android.content.Intent;
import com.tencent.tinker.loader.app.TinkerApplication;

/**
 * Abstract base class for Tinker loaders.
 * Concrete loader implementations (like {@link TinkerLoader}) must extend this class
 * and implement the {@link #tryLoad(TinkerApplication)} method.
 *
 * The {@link #tryLoad(TinkerApplication)} method is responsible for attempting to
 * load a patch and returning an {@link Intent} that contains the result of
 * this operation (e.g., success, failure codes, paths to loaded dex/libs).
 *
 * This class is typically specified as the {@code loaderClassName} in the
 * {@link TinkerApplication} constructor. An instance of the concrete loader
 * will be created via reflection, and its {@code tryLoad} method will be invoked.
 *
 * Note: This class was translated from a decompiled and obfuscated class `a.java`.
 * The decompiler may have omitted the abstract method signature if no concrete
 * implementations were present in its analysis scope. The method signature has been
 * restored based on its known usage in Tinker.
 */
public abstract class AbstractTinkerLoader {

    public AbstractTinkerLoader() {
        super();
    }

    /**
     * Attempts to load the Tinker patch.
     * This method is called by {@link TinkerApplication} during its initialization.
     *
     * @param app The {@link TinkerApplication} instance.
     * @return An {@link Intent} containing the result of the patch loading attempt.
     *         This intent should include a return code (see {@link com.tencent.tinker.loader.shareutil.ShareConstants})
     *         and potentially other data like patch version, cost time, or exception information.
     */
    public abstract Intent tryLoad(TinkerApplication app);
}
