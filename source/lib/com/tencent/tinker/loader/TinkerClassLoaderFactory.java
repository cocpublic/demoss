package com.tencent.tinker.loader;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import dalvik.system.PathClassLoader; // TinkerClassLoader extends this

import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tencent.tinker.loader.shareutil.ShareConstants;


import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator; // For iterating native library directories

// Need to import DelegateLastClassLoader if it's used and available at compile time.
// For now, assume it's handled by reflection if not directly linkable, or available on target SDKs.
// import dalvik.system.DelegateLastClassLoader; // Available API 27+

/**
 * Factory class responsible for creating and injecting Tinker's custom ClassLoader.
 * It handles the complexities of creating either a {@link TinkerClassLoader} or,
 * on newer Android versions (Android O+), a {@code dalvik.system.DelegateLastClassLoader},
 * and then reflectively setting this ClassLoader into the Application's context.
 *
 * Note: This class was translated from a decompiled and obfuscated class `c.java`.
 */
public final class TinkerClassLoaderFactory {
    private static final String TAG = "Tinker.ClassLoaderFactory";

    private TinkerClassLoaderFactory() {
        // Utility class
    }

    /**
     * Creates a Tinker-compatible ClassLoader with the given dex files and injects it into the Application.
     *
     * @param application         The Application instance.
     * @param originalParent      The original parent ClassLoader.
     * @param optimizedDir        The directory for optimized dex files.
     * @param useDelegateLast     Hint for whether DelegateLastClassLoader strategy should be preferred if available.
     * @param dexFiles            A list of dex files to load.
     * @return The newly created and injected ClassLoader.
     * @throws Throwable If ClassLoader creation or injection fails.
     */
    public static ClassLoader createAndInjectPatchedClassLoader(
            Application application,
            ClassLoader originalParent,
            File optimizedDir,
            boolean useDelegateLast, // Corresponds to original 'isVmArt' which influenced DelegateLast choice
            List<File> dexFiles) throws Throwable {

        ArrayList<String> dexPathList = new ArrayList<>();
        if (dexFiles != null && !dexFiles.isEmpty()) {
            for (File dexFile : dexFiles) {
                if (dexFile != null && dexFile.exists()) { // Ensure file exists before adding path
                    dexPathList.add(dexFile.getAbsolutePath());
                }
            }
        }

        String[] dexPathArray = dexPathList.toArray(new String[0]);

        // The 'useDelegateLastStrategy' from original decompiled code (bool1) was also related to setting definingContext.
        // Let's rename it to reflect that.
        boolean setPathListDefiningContext = useDelegateLast;


        ClassLoader newClassLoader = createClassLoaderInternal(
                originalParent,
                optimizedDir,
                useDelegateLast, // This flag primarily influences DelegateLast vs TinkerClassLoader choice
                setPathListDefiningContext, // This flag influences setting definingContext on pathList
                dexPathArray
        );

        overrideApplicationClassLoader(application, newClassLoader);
        return newClassLoader;
    }

    /**
     * Creates a Tinker-compatible ClassLoader (without injecting it into Application).
     * This version was likely used for scenarios not directly tied to full app context override.
     *
     * @param context             A Context to get the initial ClassLoader.
     * @param optimizedDir        Directory for optimized dex files.
     * @param useDelegateLast     Hint for DelegateLastClassLoader strategy.
     * @param dexPathArray        Array of dex file paths.
     * @return The newly created ClassLoader.
     * @throws Throwable If ClassLoader creation fails.
     */
    public static ClassLoader createDexClassLoader(
            Context context,
            File optimizedDir,
            boolean useDelegateLast,
            String[] dexPathArray) throws Throwable {
        // The 'setPathListDefiningContext' was implicitly true in the original 3-arg public method
        // as it directly called the internal method with bool1 (useDelegateLastStrategy) = true.
        // For this public variant, let's assume it's also true, or make it configurable if needed.
        return createClassLoaderInternal(context.getClassLoader(), optimizedDir, useDelegateLast, true, dexPathArray);
    }


    @SuppressLint("NewApi") // For DelegateLastClassLoader and potential reflective access
    private static ClassLoader createClassLoaderInternal(
            ClassLoader originalParent,
            File optimizedDir,
            boolean preferDelegateLastIfAvailable, // Original 'isVmArt' param for choosing DelegateLast
            boolean setPathListDefiningContext,  // Original 'useDelegateLastStrategy' for setting definingContext
            String[] dexPathArray) throws Throwable {

        // 1. Construct the dex path string
        StringBuilder dexPathBuilder = new StringBuilder();
        boolean firstDex = true;
        if (dexPathArray != null && dexPathArray.length > 0) {
            for (String path : dexPathArray) {
                if (path == null || path.isEmpty()) continue;
                if (firstDex) {
                    firstDex = false;
                } else {
                    dexPathBuilder.append(File.pathSeparator);
                }
                dexPathBuilder.append(path);
            }
        }
        String finalDexPath = dexPathBuilder.toString();

        // 2. Get native library directories from the original parent's pathList
        // This ensures the new ClassLoader can find native libraries loaded by the original.
        Field pathListField = findFieldRecursive(Class.forName("dalvik.system.BaseDexClassLoader", false, originalParent), "pathList");
        Object pathListObject = pathListField.get(originalParent);

        Field nativeLibraryDirectoriesField = findFieldRecursive(pathListObject.getClass(), "nativeLibraryDirectories");
        List<File> nativeLibraryDirectories;
        Object nativeLibDirsObj = nativeLibraryDirectoriesField.get(pathListObject);
        if (nativeLibraryDirectoriesField.getType().isArray()) {
            nativeLibraryDirectories = Arrays.asList((File[]) nativeLibDirsObj);
        } else { // Assume it's a List<File> on newer Android versions
            nativeLibraryDirectories = (List<File>) nativeLibDirsObj;
        }

        StringBuilder nativeLibraryPathBuilder = new StringBuilder();
        boolean firstLib = true;
        if (nativeLibraryDirectories != null) {
            for (File libDir : nativeLibraryDirectories) {
                if (libDir == null) continue;
                if (firstLib) {
                    firstLib = false;
                } else {
                    nativeLibraryPathBuilder.append(File.pathSeparator);
                }
                nativeLibraryPathBuilder.append(libDir.getAbsolutePath());
            }
        }
        String finalNativeLibraryPath = nativeLibraryPathBuilder.toString();

        // 3. Create the ClassLoader instance
        ClassLoader newClassLoader;
        // Prefer DelegateLastClassLoader on API 27+ if preferDelegateLastIfAvailable is true.
        // Original m.a(27,1) -> ShareTinkerInternals.isAboveApiLevel(27, trueish_flag)
        // The 'trueish_flag' for isAboveApiLevel's second param was complex.
        // Let's assume 'preferDelegateLastIfAvailable' corresponds to the original 'isVmArt' check.
        if (preferDelegateLastIfAvailable && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) { // API 27
            // On API 31+ (Android S), DelegateLastClassLoader's parent should be SystemClassLoader.
            // Original: m.a(31,1) ? ClassLoader.getSystemClassLoader() : loader (originalParent)
            ClassLoader delegateParent = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) // API 31
                    ? ClassLoader.getSystemClassLoader()
                    : originalParent;

            newClassLoader = new dalvik.system.DelegateLastClassLoader(finalDexPath, finalNativeLibraryPath, delegateParent);

            // Hack: Set parent of DelegateLastClassLoader to itself to prioritize its paths.
            // This is a known technique.
            Field parentField = ShareReflectUtil.findField(ClassLoader.class, "parent");
            parentField.set(newClassLoader, newClassLoader); // Original: fieldVar2.set(loader, loader);
        } else {
            newClassLoader = new TinkerClassLoader(finalDexPath, optimizedDir, finalNativeLibraryPath, originalParent);
        }

        // 4. Set definingContext on DexPathList for API 26+ if requested
        // Original: if (bool1 && m.a(26, 1)) { c.a(object.getClass(), "definingContext").set(object, var_15_2); }
        // bool1 was 'useDelegateLastStrategy' -> setPathListDefiningContext
        // m.a(26,1) -> ShareTinkerInternals.isAboveApiLevel(26, trueish_flag)
        if (setPathListDefiningContext && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // API 26
            try {
                Field definingContextField = findFieldRecursive(pathListObject.getClass(), "definingContext");
                definingContextField.set(pathListObject, newClassLoader);
            } catch (NoSuchFieldException e) {
                // This field might not exist on all Android O+ versions or OEM ROMs.
                ShareTinkerLog.w(TAG, "definingContext field not found in DexPathList, skipping: " + e.getMessage());
            }
        }
        return newClassLoader;
    }

    /**
     * Reflectively overrides the ClassLoader fields in various Application and Resources objects.
     *
     * @param application    The Application instance.
     * @param newClassLoader The ClassLoader to inject.
     * @throws Throwable If any reflection fails.
     */
    public static void overrideApplicationClassLoader(Application application, ClassLoader newClassLoader) throws Throwable {
        Thread.currentThread().setContextClassLoader(newClassLoader);

        Context baseContext = (Context) findFieldRecursive(Application.class, "mBase").get(application);
        try {
            findFieldRecursive(baseContext.getClass(), "mClassLoader").set(baseContext, newClassLoader);
        } catch (Throwable t) {
            // Ignored in original decompiled code, means it's not critical if this one fails.
            ShareTinkerLog.w(TAG, "Failed to set mClassLoader in Application's mBase context: " + t.getMessage());
        }

        Object loadedApk = findFieldRecursive(baseContext.getClass(), "mPackageInfo").get(baseContext);
        findFieldRecursive(loadedApk.getClass(), "mClassLoader").set(loadedApk, newClassLoader);

        // Also update Resources objects if possible
        Resources resources = application.getResources();
        try {
            findFieldRecursive(resources.getClass(), "mClassLoader").set(resources, newClassLoader);
        } catch (Throwable t) {
            ShareTinkerLog.w(TAG, "Failed to set mClassLoader in Resources: " + t.getMessage());
        }

        try {
            Object drawableInflater = findFieldRecursive(resources.getClass(), "mDrawableInflater").get(resources);
            if (drawableInflater != null) {
                findFieldRecursive(drawableInflater.getClass(), "mClassLoader").set(drawableInflater, newClassLoader);
            }
        } catch (Throwable t) {
            ShareTinkerLog.w(TAG, "Failed to set mClassLoader in mDrawableInflater: " + t.getMessage());
        }
    }

    /**
     * Finds a field in the given class or its superclasses.
     * Makes the field accessible.
     */
    private static Field findFieldRecursive(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            try {
                Field field = currentClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                currentClass = currentClass.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field " + fieldName + " not found in " + clazz.getName() + " and its superclasses.");
    }
}
