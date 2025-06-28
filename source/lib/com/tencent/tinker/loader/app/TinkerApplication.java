package com.tencent.tinker.loader.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.SystemClock; // For SystemClock.elapsedRealtime()

import com.tencent.tinker.anno.Keep;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.AbstractTinkerLoader; // Assuming loaderClassName implements something like this
import com.tencent.tinker.loader.TinkerLoader; // Common loader class name
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals; // For setSafeModeCount
import com.tencent.tinker.loader.TinkerUncaughtHandler; // Assuming com.tencent.tinker.loader.m is this

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Base class for Tinker applications. Users should extend this class for their own Application
 * if they are not using the {@code DefaultApplicationLike} approach.
 *
 * This class is responsible for:
 * 1. Receiving Tinker flags and class names for the delegate ApplicationLike and loader.
 * 2. Initializing the Tinker loader, which attempts to load a patch.
 * 3. Creating an instance of the user-defined ApplicationLike delegate.
 * 4. Wrapping the ApplicationLike instance with a proxy (TinkerApplicationInlineFence)
 *    to manage lifecycle callbacks and resource overrides.
 * 5. Delegating Application lifecycle events and resource access to the ApplicationLike proxy.
 *
 * Note: This class was translated from a decompiled class.
 * The obfuscated class `com.tencent.tinker.loader.app.a` is assumed to be
 * `ShareApplicationLifeCycleProxy` or a similar utility for delegation.
 */
public abstract class TinkerApplication extends Application { // TinkerApplication should extend Application
    private static final String TAG = "Tinker.TinkerApplication";

    private static TinkerApplication sInstance; // Original: c (array of 1)

    private final int tinkerFlags;
    private final boolean tinkerLoadVerifyFlag;
    private final String delegateClassName;
    private final String loaderClassName;

    /**
     * Whether Tinker is enabled in VR mode. Added in later Tinker versions.
     */
    private final boolean tinkerEnabledForVrMode;
    /**
     * Whether Tinker should use safe mode logic. Added in later Tinker versions.
     */
    private final boolean tinkerUseSafeMode;

    /**
     * This is set by Tinker internally if safe mode is triggered.
     * Different from tinkerUseSafeMode which is a build-time configuration.
     */
    private boolean mUseSafeModeSettingByTinker = false;

    protected Intent mPatchResultIntent = null;
    protected ClassLoader mCurrentClassLoader = null;

    // This is the ApplicationLifeCycle proxy, often TinkerApplicationInlineFence wrapping ApplicationLike
    private Object mLifeCycleProxy = null; // Original: i (Handler), but it's an ApplicationLifeCycle impl

    /**
     * Constructor for TinkerApplication.
     *
     * @param tinkerFlags            Tinker flags. See {@link com.tencent.tinker.loader.shareutil.ShareConstants}.
     * @param delegateClassName      The fully qualified name of the ApplicationLike delegate class.
     * @param loaderClassName        The fully qualified name of the Tinker loader class.
     * @param tinkerLoadVerifyFlag   Flag to verify patch loading.
     */
    protected TinkerApplication(int tinkerFlags, String delegateClassName, String loaderClassName, boolean tinkerLoadVerifyFlag) {
        // Calling the more complete constructor with defaults for newer flags
        this(tinkerFlags, delegateClassName, loaderClassName, tinkerLoadVerifyFlag, true, false);
    }

    /**
     * Full constructor for TinkerApplication, including newer flags.
     *
     * @param tinkerFlags             Tinker flags.
     * @param delegateClassName       The fully qualified name of the ApplicationLike delegate class.
     * @param loaderClassName         The fully qualified name of the Tinker loader class.
     * @param tinkerLoadVerifyFlag    Flag to verify patch loading.
     * @param tinkerEnabledForVrMode  Enable Tinker for VR mode.
     * @param tinkerUseSafeModeConfig Enable Tinker's safe mode logic (build time config).
     */
    protected TinkerApplication(int tinkerFlags, String delegateClassName, String loaderClassName,
                                boolean tinkerLoadVerifyFlag, boolean tinkerEnabledForVrMode, boolean tinkerUseSafeModeConfig) {
        super();
        synchronized (TinkerApplication.class) { // Synchronize on class object for static field
            if (sInstance != null) {
                // This scenario should ideally not happen if Application is instantiated once.
                // However, the original code had `c[0] = this`, implying it could be overwritten.
                ShareTinkerLog.e(TAG, "TinkerApplication instance was already set. Overwriting with new instance.");
            }
            sInstance = this;
        }
        this.tinkerFlags = tinkerFlags;
        this.delegateClassName = delegateClassName;
        this.loaderClassName = loaderClassName;
        this.tinkerLoadVerifyFlag = tinkerLoadVerifyFlag;
        this.tinkerEnabledForVrMode = tinkerEnabledForVrMode; // From decompiled bool1 in 5-arg constructor
        this.tinkerUseSafeMode = tinkerUseSafeModeConfig;    // From decompiled bool2 in 6-arg constructor
    }

    public static TinkerApplicationgetInstance() {
        synchronized (TinkerApplication.class) {
            if (sInstance == null) {
                throw new IllegalStateException("TinkerApplication is not initialized. Ensure your Application class extends TinkerApplication and is correctly registered in AndroidManifest.xml.");
            }
            return sInstance;
        }
    }

    /**
     * Attempts to load the patch using the specified loader class.
     * The result (success/failure intent) is stored in mPatchResultIntent.
     */
    private void tryLoadPatch() {
        try {
            // Dynamically load the Tinker loader class (e.g., com.tencent.tinker.loader.TinkerLoader)
            Class<?> loaderClazz = Class.forName(this.loaderClassName, false, TinkerApplication.class.getClassLoader());
            // The loader class should have a static method like `tryLoad(TinkerApplication application)`
            // which returns an Intent.
            Method tryLoadMethod = ShareReflectUtil.findMethod(loaderClazz, "tryLoad", TinkerApplication.class);
            // TinkerLoader's tryLoad is static, so invoke with null as instance.
            this.mPatchResultIntent = (Intent) tryLoadMethod.invoke(null, this);
        } catch (Throwable t) {
            // If any error occurs, create an error intent
            ShareTinkerLog.e(TAG, "tryLoadPatch: Tinker load failed.", t);
            this.mPatchResultIntent = new Intent();
            ShareIntentUtil.setIntentReturnCode(this.mPatchResultIntent, ShareConstants.ERROR_LOAD_PATCH_UNKNOWN_EXCEPTION); // Use constant
            this.mPatchResultIntent.putExtra(ShareIntentUtil.INTENT_PATCH_EXCEPTION, t);
        }
    }

    /**
     * Creates the ApplicationLike delegate instance and wraps it with TinkerApplicationInlineFence.
     *
     * @param application The main application instance.
     * @param tinkerFlags Tinker initialization flags.
     * @param delegateClassName The FQN of the ApplicationLike class.
     * @param tinkerLoadVerifyFlag Verification flag for patch loading.
     * @param applicationStartElapsedTime Time since app start (elapsed).
     * @param applicationStartMillisTime Wall clock time of app start.
     * @param resultIntent Intent from patch loading.
     * @return The ApplicationLifeCycle proxy (TinkerApplicationInlineFence instance).
     */
    private Object createApplicationLikeProxy(
            Application application, int tinkerFlags, String delegateClassName,
            boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
            long applicationStartMillisTime, Intent resultIntent) {
        try {
            // Load the user's ApplicationLike delegate class
            Class<?> delegateClazz = Class.forName(delegateClassName, false, this.mCurrentClassLoader);
            Constructor<?> delegateConstructor = delegateClazz.getConstructor(
                    Application.class, int.class, boolean.class,
                    long.class, long.class, Intent.class);
            Object applicationLikeInstance = delegateConstructor.newInstance(
                    application, tinkerFlags, tinkerLoadVerifyFlag,
                    applicationStartElapsedTime, applicationStartMillisTime, resultIntent);

            // Load TinkerApplicationInlineFence and wrap the ApplicationLike instance
            Class<?> inlineFenceClazz = Class.forName(
                    "com.tencent.tinker.entry.TinkerApplicationInlineFence", false, this.mCurrentClassLoader);
            // The constructor of TinkerApplicationInlineFence takes an ApplicationLike instance.
            Class<?> applicationLikeInterface = Class.forName(
                    "com.tencent.tinker.entry.ApplicationLike", false, this.mCurrentClassLoader);

            Constructor<?> fenceConstructor = inlineFenceClazz.getDeclaredConstructor(applicationLikeInterface);
            fenceConstructor.setAccessible(true); // It's usually package-private or protected
            return fenceConstructor.newInstance(applicationLikeInstance);

        } catch (Throwable t) {
            throw new TinkerRuntimeException("createApplicationLikeProxy failed", t);
        }
    }

    /**
     * Called by attachBaseContext after the super call.
     * This is where Tinker initializes.
     */
    protected void onBaseContextAttached(Context base, long applicationStartElapsedTime, long applicationStartMillisTime) {
        try {
            tryLoadPatch(); // Attempt to load patch, result in mPatchResultIntent
            this.mCurrentClassLoader = base.getClassLoader(); // Get classloader after potential patch loading

            this.mLifeCycleProxy = createApplicationLikeProxy(
                    this, this.tinkerFlags, this.delegateClassName, this.tinkerLoadVerifyFlag,
                    applicationStartElapsedTime, applicationStartMillisTime, this.mPatchResultIntent
            );

            // Delegate onBaseContextAttached to the proxy
            // Original: a.a(this.i, context); -> ShareApplicationLifeCycleProxy.onBaseContextAttached(proxy, context)
            ShareApplicationLifeCycleProxy.onBaseContextAttached(this.mLifeCycleProxy, base);

            // If Tinker requested to use safe mode (e.g., due to repeated crashes)
            if (this.mUseSafeModeSettingByTinker) {
                // Original: m.a(this, 0); -> ShareTinkerInternals.setSafeModeCount(this, 0) or similar
                // This seems to reset the safe mode count if Tinker decided to enter it.
                // The exact method in ShareTinkerInternals might be different, like clearSafeMode.
                ShareTinkerInternals.setSafeModeCount(this, 0); // Assuming 0 resets it.
                ShareTinkerLog.w(TAG, "onBaseContextAttached: Tinker is in safe mode, safe mode count cleared.");
            }
        } catch (TinkerRuntimeException e) {
            throw e;
        } catch (Throwable t) {
            throw new TinkerRuntimeException("TinkerApplication onBaseContextAttached failed.", t);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        long applicationStartElapsedTime = SystemClock.elapsedRealtime();
        long applicationStartMillisTime = System.currentTimeMillis();

        // Set up uncaught exception handler
        Thread.setDefaultUncaughtExceptionHandler(new TinkerUncaughtHandler(this));

        onBaseContextAttached(base, applicationStartElapsedTime, applicationStartMillisTime);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mLifeCycleProxy != null) {
            ShareApplicationLifeCycleProxy.onCreate(mLifeCycleProxy);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mLifeCycleProxy != null) {
            ShareApplicationLifeCycleProxy.onTerminate(mLifeCycleProxy);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mLifeCycleProxy != null) {
            ShareApplicationLifeCycleProxy.onLowMemory(mLifeCycleProxy);
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (mLifeCycleProxy != null) {
            ShareApplicationLifeCycleProxy.onTrimMemory(mLifeCycleProxy, level);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mLifeCycleProxy != null) {
            ShareApplicationLifeCycleProxy.onConfigurationChanged(mLifeCycleProxy, newConfig);
        }
    }

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        if (mLifeCycleProxy == null) {
            return resources;
        }
        return ShareApplicationLifeCycleProxy.getResources(mLifeCycleProxy, resources);
    }

    @Override
    public ClassLoader getClassLoader() {
        ClassLoader classLoader = super.getClassLoader();
        if (mLifeCycleProxy == null) {
            return classLoader;
        }
        return ShareApplicationLifeCycleProxy.getClassLoader(mLifeCycleProxy, classLoader);
    }

    @Override
    public AssetManager getAssets() {
        AssetManager assetManager = super.getAssets();
        if (mLifeCycleProxy == null) {
            return assetManager;
        }
        return ShareApplicationLifeCycleProxy.getAssets(mLifeCycleProxy, assetManager);
    }

    @Override
    public Object getSystemService(String name) {
        Object service = super.getSystemService(name);
        if (mLifeCycleProxy == null) {
            return service;
        }
        return ShareApplicationLifeCycleProxy.getSystemService(mLifeCycleProxy, name, service);
    }

    @Override
    public Context getBaseContext() {
        Context base = super.getBaseContext();
        if (mLifeCycleProxy == null) {
            return base;
        }
        return ShareApplicationLifeCycleProxy.getBaseContext(mLifeCycleProxy, base);
    }

    @Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        if (mLifeCycleProxy == null) {
            return theme;
        }
        return ShareApplicationLifeCycleProxy.getTheme(mLifeCycleProxy, theme);
    }

    /**
     * For Meizu night mode compatibility.
     */
    @Keep
    public int mzNightModeUseOf() {
        if (mLifeCycleProxy != null) {
            return ShareApplicationLifeCycleProxy.mzNightModeUseOf(mLifeCycleProxy);
        }
        return super.mzNightModeUseOf(); // Call super if proxy is null, or Tinker's default (1)
    }


    /**
     * Called by Tinker internals to indicate if safe mode should be used.
     * Original: public void a(boolean bool0)
     * @param useSafeMode true if safe mode should be used.
     */
    public void setUseSafeMode(boolean useSafeMode) {
        this.mUseSafeModeSettingByTinker = useSafeMode;
    }

    public boolean getTinkerLoadVerifyFlag() {
        return tinkerLoadVerifyFlag;
    }

    public int getTinkerFlags() {
        return tinkerFlags;
    }

    /**
     * @return true if Tinker is configured to be enabled for VR mode.
     */
    public boolean isTinkerEnabledForVrMode() {
        return tinkerEnabledForVrMode;
    }

    /**
     * @return true if Tinker is configured to use its safe mode logic (build time config).
     */
    public boolean isTinkerUseSafeMode() {
        return tinkerUseSafeMode;
    }
}
