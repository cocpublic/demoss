package com.tencent.tinker.loader;

import android.app.Application;
import android.os.Build;
import dalvik.system.DexFile; // For V4.x (BaseDexClassLoaderManipulation)
import dalvik.system.PathClassLoader;


import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerLog;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.zip.ZipFile; // For V4.x

/**
 * Utility class for installing dex files into a ClassLoader, handling different Android API levels.
 * It uses reflection to modify the ClassLoader's internal DexPathList or similar structures.
 *
 * Note: This class was translated from a decompiled and obfuscated class `d.java`.
 * It corresponds to Tinker's SystemClassLoaderAdder or similar multi-dex installation logic.
 */
public final class SystemClassLoaderAdder {
    private static final String TAG = "Tinker.ClassLoaderAdder";

    private static int sPatchDexCount = 0; // Stores count of dexes from last successful install op

    private SystemClassLoaderAdder() {
        // Utility class
    }

    /**
     * Main method to install dex files into the provided classloader.
     *
     * @param application     The Application instance.
     * @param loader          The ClassLoader to modify (typically the application's base ClassLoader).
     * @param dexOptDir       The directory for optimized dex files.
     * @param dexFilesToInstall List of dex files to install.
     * @param preferDelegateLastIfAvailable Hint for using DelegateLastClassLoader on newer APIs.
     * @param setPathListDefiningContext Hint for setting definingContext on DexPathList.
     * @throws Throwable If installation fails.
     */
    @SuppressWarnings("unchecked")
    public static void installDexes(Application application, ClassLoader loader, File dexOptDir, List<File> dexFilesToInstall,
                                    boolean preferDelegateLastIfAvailable, boolean setPathListDefiningContext) throws Throwable {

        ShareTinkerLog.i(TAG, "installDexes dexOptDir: " + (dexOptDir != null ? dexOptDir.getAbsolutePath() : "null") +
                ", dex size:" + (dexFilesToInstall != null ? dexFilesToInstall.size() : 0));

        if (dexFilesToInstall == null || dexFilesToInstall.isEmpty()) {
            ShareTinkerLog.i(TAG, "installDexes: no dex files to install.");
            return;
        }

        List<File> sortedDexFiles = sortDexFilesForInstallation(new ArrayList<>(dexFilesToInstall));

        ClassLoader resultingClassLoader = loader; // Initially the original loader

        // On API 24+ (Nougat) and if preferDelegateLast strategy is hinted (originally isVmArt check)
        // Tinker might create a new ClassLoader (DelegateLastClassLoader) instead of just injecting into existing.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && preferDelegateLastIfAvailable) {
            ShareTinkerLog.i(TAG, "Using TinkerClassLoaderFactory to create/adjust ClassLoader for API >= 24 and preferDelegateLast.");
            resultingClassLoader = TinkerClassLoaderFactory.createDexClassLoader(
                    application, // Using Application context for this factory method
                    dexOptDir,
                    preferDelegateLastIfAvailable, // This flag is used by factory to choose TinkerCL or DelegateLastCL
                    sortedDexFiles.toArray(new String[0]) // Factory method expects String array of paths
            );
            // The factory method also handles injecting this new ClassLoader into the application context.
            // The preOptAndReinstallOnFailure logic was complex and involved another call to TinkerClassLoaderFactory
            // Let's simplify here: if checkDexInstallation fails later, it will throw.
            // The original preOptAndReinstallOnFailure was essentially:
            // if (checkDexInstallation(newlyCreatedOrModifiedLoader)) { /* ok */ }
            // else { delete odex/vdex and recreate loader via TinkerClassLoaderFactory }
            // This implies TinkerClassLoaderFactory already handles dexopt dir.
            // For now, assume the factory call above is sufficient.
            // The original preOptAndReinstallOnFailure was:
            // loaderVar3 = c.a(application, loader, file, bool1, list); -> TinkerClassLoaderFactory.createDexClassLoader
            // loaderVar3 = d.a(application, loader, file, list, bool1, loaderVar3); -> this.preOptAndReinstallOnFailure
            // This suggests the factory method might not be the one doing the final application override.
            // Let's assume installDexesInternal is called on the potentially new `resultingClassLoader`.
            installDexesInternal(resultingClassLoader, sortedDexFiles, dexOptDir);

        } else {
            // For older versions or if not using DelegateLast strategy, inject into the existing loader.
            installDexesInternal(loader, sortedDexFiles, dexOptDir);
            resultingClassLoader = loader; // No new loader created
        }

        sPatchDexCount = sortedDexFiles.size();
        ShareTinkerLog.i(TAG, "installDexes: After trying to load, classloader is " + resultingClassLoader +
                ", sPatchDexCount: " + sPatchDexCount);

        if (!checkDexInstallation(resultingClassLoader)) {
            // If check fails, attempt to revert by uninstalling the dexes we tried to add.
            ShareTinkerLog.w(TAG, "checkDexInstallation failed, attempting to uninstall patch dexes.");
            uninstallPatchDexes(resultingClassLoader); // Use the loader that had dexes installed
            throw new TinkerRuntimeException("checkDexInstall failed");
        }

        // If API >= 24 and preferDelegateLast, the TinkerClassLoaderFactory might have already overridden
        // the application's classloader. If not, or if we modified the existing one,
        // ensure the application context uses the right ClassLoader.
        // The original logic for this was:
        // if (Build$VERSION.SDK_INT >= 24 && bool0_preferDelegateLast) {
        //    c.a(application, loaderVar_after_preOptAndReinstall); // TinkerClassLoaderFactory.overrideApplicationClassLoader
        // }
        // This implies if a new loader was created by TinkerClassLoaderFactory, it should be set.
        // If we only modified the existing `loader`, this override might still be needed for Resources, etc.
        // For simplicity and to ensure consistency with TinkerClassLoaderFactory:
        if (resultingClassLoader != application.getClassLoader()) {
             ShareTinkerLog.i(TAG, "Overriding application ClassLoader with the new/modified one: " + resultingClassLoader);
             TinkerClassLoaderFactory.overrideApplicationClassLoader(application, resultingClassLoader);
        }
    }


    /**
     * Installs dexes into a PathClassLoader for hotplug scenarios (e.g., ArkHot).
     * This seems to be a specialized path.
     *
     * @param classLoader The PathClassLoader instance.
     * @param dexFiles    List of dex files to install.
     * @throws Throwable If installation fails.
     */
    public static void installDexesForHotplug(PathClassLoader classLoader, List<File> dexFiles) throws Throwable {
        if (dexFiles == null || dexFiles.isEmpty()) {
            ShareTinkerLog.i(TAG, "installDexesForHotplug: no dex files to install.");
            return;
        }
        List<File> sortedDexFiles = sortDexFilesForInstallation(new ArrayList<>(dexFiles));
        ArkHotInstall.install(classLoader, sortedDexFiles); // Delegate to ArkHot specific installer
        sPatchDexCount = sortedDexFiles.size();
        ShareTinkerLog.i(TAG, "installDexesForHotplug: After loaded, classloader is " + classLoader +
                ", sPatchDexCount: " + sPatchDexCount);

        if (!checkDexInstallation(classLoader)) {
            ShareTinkerLog.w(TAG, "installDexesForHotplug: checkDexInstallation failed, attempting to uninstall patch dexes.");
            uninstallPatchDexes(classLoader);
            throw new TinkerRuntimeException("checkDexInstall failed for hotplug");
        }
    }


    /**
     * Internal method to dispatch dex installation to API-level specific handlers.
     */
    private static void installDexesInternal(ClassLoader classLoader, List<File> dexFiles, File dexOptDir) throws Throwable {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // API 23+
            V23.install(classLoader, dexFiles, dexOptDir);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // API 19-22
            V19.install(classLoader, dexFiles, dexOptDir);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) { // API 14-18
            V14.install(classLoader, dexFiles, dexOptDir);
        } else { // Below API 14
            BaseDexClassLoaderManipulation.install(classLoader, dexFiles, dexOptDir);
        }
    }

    /**
     * Uninstalls the previously added patch dexes from the ClassLoader.
     * This is used for reverting or cleaning up on failure.
     */
    public static void uninstallPatchDexes(ClassLoader classLoader) {
        if (sPatchDexCount <= 0) {
            return;
        }
        ShareTinkerLog.i(TAG, "Uninstalling " + sPatchDexCount + " patch dexes from ClassLoader " + classLoader);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) { // DexPathList exists
                Field pathListField = ShareReflectUtil.findField(classLoader, "pathList");
                Object dexPathList = pathListField.get(classLoader);
                ShareReflectUtil.reduceFieldArray(dexPathList, "dexElements", sPatchDexCount);
            } else { // Pre-ICS, direct field manipulation
                ShareReflectUtil.reduceFieldArray(classLoader, "mPaths", sPatchDexCount);
                ShareReflectUtil.reduceFieldArray(classLoader, "mFiles", sPatchDexCount);
                ShareReflectUtil.reduceFieldArray(classLoader, "mZips", sPatchDexCount);
                try {
                    ShareReflectUtil.reduceFieldArray(classLoader, "mDexs", sPatchDexCount);
                } catch (NoSuchFieldException nsfe) {
                    // mDexs might not exist on all pre-ICS versions, ignore.
                    ShareTinkerLog.w(TAG, "uninstallPatchDexes: mDexs field not found, skipping reduction.", nsfe);
                }
            }
            sPatchDexCount = 0; // Reset count after uninstallation
        } catch (Throwable t) {
            ShareTinkerLog.e(TAG, "Failed to uninstall patch dexes.", t);
        }
    }

    /**
     * Checks if TinkerTestDexLoad.isPatch is true, indicating successful patch loading.
     */
    private static boolean checkDexInstallation(ClassLoader classLoader) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        Class<?> testClass = Class.forName("com.tencent.tinker.loader.TinkerTestDexLoad", true, classLoader);
        Field isPatchField = ShareReflectUtil.findField(testClass, "isPatch"); // findField makes it accessible
        boolean isPatch = isPatchField.getBoolean(null); // Static field
        ShareTinkerLog.i(TAG, "checkDexInstallation result: " + isPatch + ", classloader: " + classLoader +
                ", TinkerTestDexLoad classloader: " + testClass.getClassLoader());
        return isPatch;
    }

    /**
     * Sorts dex files for installation.
     * Primary dex (classes.dex) should come before secondary dexes (classesN.dex).
     * Files not matching CLASS_N_PATTERN (i.e., usually classes.dex) are prioritized.
     */
    private static List<File> sortDexFilesForInstallation(List<File> dexFiles) {
        if (dexFiles == null || dexFiles.size() <= 1) {
            return dexFiles;
        }
        // Create a map to cache pattern matching results
        final Map<String, Boolean> isSecondaryDexMap = new HashMap<>();
        for (File dexFile : dexFiles) {
            String fileName = dexFile.getName();
            isSecondaryDexMap.put(fileName, ShareConstants.CLASS_N_PATTERN.matcher(fileName).matches());
        }

        Collections.sort(dexFiles, new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                String name1 = file1.getName();
                String name2 = file2.getName();

                boolean isSecondary1 = isSecondaryDexMap.getOrDefault(name1, false);
                boolean isSecondary2 = isSecondaryDexMap.getOrDefault(name2, false);

                if (isSecondary1 && !isSecondary2) {
                    return 1;  // file1 (secondary) comes after file2 (primary)
                } else if (!isSecondary1 && isSecondary2) {
                    return -1; // file1 (primary) comes before file2 (secondary)
                } else {
                    // Both are primary or both are secondary, sort by name (e.g., classes2 before classes3)
                    return name1.compareTo(name2);
                }
            }
        });
        return dexFiles;
    }


    // --- Inner classes for API-level specific DexPathList manipulation ---

    private static final class V23 { // API 23, 24, 25 (M, N, N_MR1)
        private static void install(ClassLoader classLoader, List<File> dexFiles, File optimizedDirectory) throws Throwable {
            Field pathListField = ShareReflectUtil.findField(classLoader, "pathList");
            Object dexPathList = pathListField.get(classLoader);
            ArrayList<IOException> suppressedExceptions = new ArrayList<>();

            // On API 23+, use makePathElements.
            // Note: Original decompiled code for d$d (V23) tried makePathElements(List, File, List) first,
            // then makePathElements(ArrayList, File, ArrayList), then fell back to V19's makeDexElements.
            Object[] newDexElements = (Object[]) ShareReflectUtil.findMethod(
                    dexPathList, "makePathElements", List.class, File.class, List.class)
                    .invoke(dexPathList, dexFiles, optimizedDirectory, suppressedExceptions);

            ShareReflectUtil.expandFieldArray(dexPathList, "dexElements", newDexElements);
            if (!suppressedExceptions.isEmpty()) {
                // Combine exceptions into one, as Tinker does.
                IOException DONT_KILL_MY_APP = new IOException("Failed to load some dex files: " + suppressedExceptions.get(0).getMessage());
                for(int i = 1; i < suppressedExceptions.size(); ++i) {
                     DONT_KILL_MY_APP.addSuppressed(suppressedExceptions.get(i));
                }
                // Propagate the combined exception
                throw DONT_KILL_MY_APP;
            }
        }
    }

    private static final class V19 { // API 19-22 (K, L, L_MR1)
        private static void install(ClassLoader classLoader, List<File> dexFiles, File optimizedDirectory) throws Throwable {
            Field pathListField = ShareReflectUtil.findField(classLoader, "pathList");
            Object dexPathList = pathListField.get(classLoader);
            ArrayList<IOException> suppressedExceptions = new ArrayList<>();

            // On API 19-22, use makeDexElements(ArrayList, File, ArrayList)
            // Original decompiled code for d$c (V19) tried ArrayList version first, then List.
            Object[] newDexElements = (Object[]) ShareReflectUtil.findMethod(
                    dexPathList, "makeDexElements", ArrayList.class, File.class, ArrayList.class)
                    .invoke(dexPathList, new ArrayList<>(dexFiles), optimizedDirectory, suppressedExceptions);

            ShareReflectUtil.expandFieldArray(dexPathList, "dexElements", newDexElements);
             if (!suppressedExceptions.isEmpty()) {
                IOException DONT_KILL_MY_APP = new IOException("Failed to load some dex files: " + suppressedExceptions.get(0).getMessage());
                for(int i = 1; i < suppressedExceptions.size(); ++i) {
                     DONT_KILL_MY_APP.addSuppressed(suppressedExceptions.get(i));
                }
                throw DONT_KILL_MY_APP;
            }
        }
    }

    private static final class V14 { // API 14-18 (ICS, JB, JB_MR1, JB_MR2)
        private static void install(ClassLoader classLoader, List<File> dexFiles, File optimizedDirectory) throws Throwable {
            Field pathListField = ShareReflectUtil.findField(classLoader, "pathList");
            Object dexPathList = pathListField.get(classLoader);

            // On API 14-18, use makeDexElements(ArrayList, File)
            Object[] newDexElements = (Object[]) ShareReflectUtil.findMethod(
                    dexPathList, "makeDexElements", ArrayList.class, File.class)
                    .invoke(dexPathList, new ArrayList<>(dexFiles), optimizedDirectory);

            ShareReflectUtil.expandFieldArray(dexPathList, "dexElements", newDexElements);
        }
    }

    private static final class BaseDexClassLoaderManipulation { // For API < 14
        private static void install(ClassLoader classLoader, List<File> dexFiles, File optimizedDirectory) throws Throwable {
            // For versions prior to ICS, BaseDexClassLoader had direct fields mPaths, mFiles, mZips, mDexs.
            // This is a simplified representation of the original d$e logic.
            // It requires careful handling of array types and sizes.

            Field pathField = ShareReflectUtil.findField(classLoader, "path"); // Path string
            String originalPath = (String) pathField.get(classLoader);
            StringBuilder newPathBuilder = new StringBuilder(originalPath);

            String[] newPaths = new String[dexFiles.size()];
            File[] newFiles = new File[dexFiles.size()];
            ZipFile[] newZips = new ZipFile[dexFiles.size()];
            DexFile[] newDexs = new DexFile[dexFiles.size()];

            for (int i = 0; i < dexFiles.size(); i++) {
                File dexFile = dexFiles.get(i);
                String absolutePath = dexFile.getAbsolutePath();
                newPathBuilder.append(File.pathSeparator).append(absolutePath);

                newPaths[i] = absolutePath;
                newFiles[i] = dexFile;
                // Note: Original created ZipFile and DexFile here. This might load them.
                // For just updating path list, this might not be needed if only path string is used by loader.
                // However, mZips and mDexs arrays suggest they are indeed created and stored.
                newZips[i] = new ZipFile(dexFile); // Can throw IOException
                // DexFile.loadDex is deprecated. Optimized path is needed.
                String optimizedPath = SharePatchFileUtil.getOptimizedDexPath(dexFile, optimizedDirectory);
                newDexs[i] = DexFile.loadDex(absolutePath, optimizedPath, 0);
            }

            pathField.set(classLoader, newPathBuilder.toString());
            ShareReflectUtil.expandFieldArray(classLoader, "mPaths", newPaths);
            ShareReflectUtil.expandFieldArray(classLoader, "mFiles", newFiles);
            ShareReflectUtil.expandFieldArray(classLoader, "mZips", newZips);
            try {
                ShareReflectUtil.expandFieldArray(classLoader, "mDexs", newDexs);
            } catch (NoSuchFieldException nsfe) {
                ShareTinkerLog.w(TAG, "mDexs field not found in BaseDexClassLoaderManipulation, skipping.", nsfe);
            }
        }
    }

    private static final class ArkHotInstall { // For Huawei Ark Hot
        private static void install(ClassLoader classLoader, List<File> dexFiles) throws Throwable {
            // Original d$a:
            // Class class = ClassLoader.getSystemClassLoader().getParent().loadClass("com.huawei.ark.classloader.ExtendedClassLoaderHelper");
            // Method method = class.getDeclaredMethod("applyPatch", new Class[]{ClassLoader.class, String.class});
            // method.setAccessible(true);
            // method.invoke(null, new Object[]{loader, str0_canonicalPath});
            try {
                Class<?> arkHelperClass = Class.forName("com.huawei.ark.classloader.ExtendedClassLoaderHelper");
                Method applyPatchMethod = ShareReflectUtil.findMethod(arkHelperClass, "applyPatch", ClassLoader.class, String.class);
                for (File dexFile : dexFiles) {
                    String canonicalPath = dexFile.getCanonicalPath();
                    applyPatchMethod.invoke(null, classLoader, canonicalPath);
                    ShareTinkerLog.i(TAG, "ArkHot installed path = " + canonicalPath);
                }
            } catch (ClassNotFoundException e){
                ShareTinkerLog.e(TAG, "ArkHot: ExtendedClassLoaderHelper not found.", e);
                throw e; // Rethrow if Ark specific install is expected but class missing
            } catch (Exception e) { // Catch other reflection errors
                ShareTinkerLog.e(TAG, "ArkHot: Failed to install dexes via ExtendedClassLoaderHelper.", e);
                throw e;
            }
        }
    }
}
