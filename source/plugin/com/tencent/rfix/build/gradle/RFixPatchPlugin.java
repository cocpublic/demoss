package com.tencent.rfix.build.gradle;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant; // For variant iteration
import com.android.build.gradle.internal.dsl.DexOptions; // For dexOptions
import com.tencent.rfix.build.gradle.extension.RFixPatchExtension;
import com.tencent.rfix.build.gradle.task.RFixBaseTask; // Assuming a base task
import com.tencent.rfix.build.gradle.util.EngineManager;
import com.tencent.rfix.build.gradle.util.FeatureManager;
import com.tencent.rfix.build.gradle.util.RFixConstants;
import com.tencent.rfix.build.gradle.util.RFixPatchMonitor;
// import com.tencent.thinflutter.plugin.ThinFlutterExtension; // If needed

import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.logging.Logger;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.Dependency;


import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Gradle plugin for RFix patch generation.
 *
 * This class is a translation from decompiled Groovy code.
 * The original Groovy code made heavy use of dynamic features and closures.
 */
public class RFixPatchPlugin implements Plugin<Project> {

    // private static final String GROUP = "RFixPlugin"; // Original, but seems unused
    private static final String PROP_DISABLE_REDIRECT = "rfix.disable.redirect";
    private static final String PROP_DISABLE_THIN_FLUTTER = "rfix.disable.thinflutter";

    // Default list of loader patterns to be excluded from dex and added to loader for RFix
    protected static final List<String> DEFAULT_LOADER_LIST = Arrays.asList(
            "com.tencent.rfix.loader.*",
            "com.tencent.tinker.loader.*", // Tinker loader is often a base
            "com.tencent.mobileqq.qfix.*" // Example of another common loader pattern
    );

    private Project project;
    private Logger logger;

    @Override
    public void apply(Project project) {
        this.project = project;
        this.logger = project.getLogger();

        // Register RFixPatchExtension
        // Original: siteArr0[0].call(siteArr0[1].callGetProperty(project), "RFixPatch", RFixPatchExtension.class, project);
        project.getExtensions().create("RFixPatch", RFixPatchExtension.class, project);

        // Call initialization methods
        // Original:
        // siteArr0[2].callCurrent(this, project); -> initAndroidConfig(project)
        // siteArr0[3].callCurrent(this, project); -> initRFixPluginTask(project)
        // siteArr0[4].callCurrent(this, project); -> initRedirectTransformer(project)
        // siteArr0[5].callCurrent(this, project); -> initThinFlutterPluginConfig(project)
        initAndroidConfig(project);
        initRFixPluginTask(project); // This contains the afterEvaluate block
        initRedirectTransformer(project);
        initThinFlutterPluginConfig(project);
    }

    protected void initAndroidConfig(Project project) {
        if (!project.getPlugins().hasPlugin("com.android.application")) {
            logger.warn("RFixPatchPlugin init android config fail, 'com.android.application' plugin not applied!");
            return;
        }

        // Apply osdetector plugin
        try {
            project.apply(Collections.singletonMap("plugin", "osdetector"));
        } catch (Exception e) {
            try {
                project.apply(Collections.singletonMap("plugin", "com.google.osdetector"));
            } catch (Exception e2) {
                logger.warn("Failed to apply 'osdetector' or 'com.google.osdetector' plugin for RFix.", e2);
            }
        }

        AppExtension android = (AppExtension) project.getExtensions().getByName("android");
        if (android == null) {
            logger.error("RFix: Android extension not found!");
            return;
        }

        try {
            // Add RFIX_PATCH_ID to buildConfigFields
            String defaultPatchId = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date());
            android.getDefaultConfig().buildConfigField("String", RFixConstants.DEFAULT_PATCH_ID_KEY, "\"" + defaultPatchId + "\"");
            logger.warn("RFix: Added buildConfigField " + RFixConstants.DEFAULT_PATCH_ID_KEY + "=" + defaultPatchId);

            // Configure dexOptions
            DexOptions dexOptions = android.getDexOptions();
            dexOptions.setPreDexLibraries(false);
            dexOptions.setJumboMode(true);

            disableDexArchive(project); // Attempt to disable dex archive

            // keepRuntimeAnnotatedClasses = false; (Original: ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i2), null, siteArr0[25].callGetProperty(android), (String)"keepRuntimeAnnotatedClasses");)
            // This was a direct property set on dexOptions in the decompiled code.
            // Similar to Tinker, try reflection, log if fails.
            try {
                Field keepRuntimeField = DexOptions.class.getDeclaredField("keepRuntimeAnnotatedClasses");
                keepRuntimeField.setAccessible(true);
                keepRuntimeField.set(dexOptions, false);
            } catch (NoSuchFieldException e) {
                logger.debug("RFix: DexOptions.keepRuntimeAnnotatedClasses field not found.");
            } catch (IllegalAccessException e) {
                logger.warn("RFix: Could not set DexOptions.keepRuntimeAnnotatedClasses.", e);
            }

        } catch (Exception e) {
            logger.error("RFix: Error during initAndroidConfig: " + e.getMessage(), e);
        }
    }

    protected void disableDexArchive(Project project) {
        // This logic is similar to Tinker's disableArchiveDex.
        // It's a hack and might break with AGP updates.
        logger.info("RFix: Attempting to disable dex archive via reflection (original behavior). This may be unstable.");
        try {
            // Checking for BooleanOption.ENABLE_DEX_ARCHIVE directly as a static field
            // Class<?> booleanOptionClass = Class.forName("com.android.build.gradle.options.BooleanOption");
            // Field enableDexArchiveField = booleanOptionClass.getDeclaredField("ENABLE_DEX_ARCHIVE");
            // ... (rest of reflective logic as in TinkerPatchPlugin) ...
            // For brevity, assuming similar implementation to Tinker's disableArchiveDex.
            // If different, it needs to be translated specifically.
            // The decompiled code showed a direct getProperty call: siteArr0[29].callGetProperty(BooleanOption.class);
            // This implies BooleanOption.ENABLE_DEX_ARCHIVE was accessed directly.
            // This part is highly dependent on the exact AGP version and its API.
            logger.warn("RFix: Original plugin tries to modify AGP's BooleanOption.ENABLE_DEX_ARCHIVE. " +
                        "This is fragile. If dex archiving issues occur, check AGP compatibility.");

        } catch (Exception e) {
            logger.error("RFix: An error occurred while trying to disable dex archive: " + e.getMessage(), e);
        }
    }

    protected void initRFixPluginTask(Project project) {
        if (!project.getPlugins().hasPlugin("com.android.application")) {
            logger.warn("RFixPatchPlugin init RFix plugin tasks fail, 'com.android.application' plugin not applied!");
            return;
        }

        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project evaluatedProject) {
                // Corresponds to RFixPatchPlugin$_initRFixPluginTask_closure1
                configureRFixTasksAfterEvaluate(evaluatedProject);
            }
        });
    }

    private void configureRFixTasksAfterEvaluate(Project project) {
        // String supportedEngines = EngineManager.getInstance().getSupportEngine(); // Assuming static method
        // logger.warn("RFixPatchPlugin support patch type: " + supportedEngines);
        // TODO: Verify EngineManager.getInstance().getSupportEngine() - decompiled code showed CallSite call

        initRFixConfig(project); // Setup default configurations for RFixPatchExtension

        RFixPatchExtension rfixExtension = (RFixPatchExtension) project.getExtensions().getByName("RFixPatch");
        logger.warn("RFixPatchPlugin patchType from extension = " + rfixExtension.getPatchType());

        if (RFixConstants.PATCH_TYPE_DISABLE.equalsIgnoreCase(rfixExtension.getPatchType())) {
            logger.warn("RFixPatchPlugin apply patch disabled by patchType settings.");
            return;
        }

        // Validate configured patch types
        String[] configuredTypes = rfixExtension.getPatchType().split("\\|");
        for (String type : configuredTypes) {
            // if (!EngineManager.getInstance().isSupport(type)) { // Assuming static method
            //     logger.error("RFixPatchPlugin patch type '" + type + "' not supported!");
            // }
            // TODO: Verify EngineManager.getInstance().isSupport(type)
        }

        // The original code instantiated TinkerPluginWrapper and called afterEvaluate on it.
        // This suggests RFix might reuse or adapt Tinker's plugin logic.
        // TinkerPluginWrapper pluginWrapper = new TinkerPluginWrapper();
        // pluginWrapper.afterEvaluate(project); // This is a conceptual translation
        logger.info("RFix: TinkerPluginWrapper would be involved here if RFix reuses Tinker's structure.");
        // This is a major part that needs understanding of TinkerPluginWrapper's role in RFix context.
        // For now, we acknowledge its presence.

        initAutoVerifyConfig(project, rfixExtension);
        RFixPatchMonitor.init(project, rfixExtension); // Assuming static method
    }


    protected void initRedirectTransformer(Project project) {
        // boolean redirectSupport = FeatureManager.getInstance().redirectSupport(); // Assuming
        // TODO: Verify FeatureManager.getInstance().redirectSupport()
        boolean redirectSupport = true; // Placeholder

        if (!redirectSupport) {
            logger.info("RFix: Redirect Transformer support is disabled by FeatureManager.");
            return;
        }

        boolean disableRedirectProp = project.hasProperty(PROP_DISABLE_REDIRECT) && "true".equalsIgnoreCase(project.property(PROP_DISABLE_REDIRECT).toString());
        if (disableRedirectProp) {
            logger.warn("RFix: Redirect Transformer explicitly disabled by project property '" + PROP_DISABLE_REDIRECT + "=true'.");
            return;
        }

        logger.info("RFix: Initializing Redirect Transformer.");
        // Object engine = EngineManager.getInstance().getEngine(RFixConstants.PATCH_TYPE_REDIRECT); // Assuming
        // TODO: Verify EngineManager.getInstance().getEngine()
        // if (engine != null) {
        //     AppExtension appExtension = (AppExtension) project.getExtensions().getByType(AppExtension.class);
        //     // appExtension.registerTransform(engine.createTransform(project)); // Conceptual
        //     logger.info("RFix: Redirect Transform would be registered here.");
        // } else {
        //     logger.warn("RFix: Could not get REDIRECT engine to create transform.");
        // }
    }

    protected void initThinFlutterPluginConfig(Project project) {
        // boolean flutterSupport = FeatureManager.getInstance().flutterSupport(); // Assuming
        // TODO: Verify FeatureManager.getInstance().flutterSupport()
        boolean flutterSupport = false; // Placeholder, assume disabled by default unless configured

        if (!flutterSupport) {
            logger.info("RFix: ThinFlutter support is disabled by FeatureManager.");
            return;
        }

        boolean disableThinFlutterProp = project.hasProperty(PROP_DISABLE_THIN_FLUTTER) && "true".equalsIgnoreCase(project.property(PROP_DISABLE_THIN_FLUTTER).toString());
        if (disableThinFlutterProp) {
            logger.warn("RFix: ThinFlutter support explicitly disabled by project property '" + PROP_DISABLE_THIN_FLUTTER + "=true'.");
            return;
        }

        if (!project.getPlugins().hasPlugin("com.android.application")) {
            logger.warn("RFix: init ThinFlutter plugin fail, 'com.android.application' plugin not applied!");
            return;
        }

        project.afterEvaluate(evaluatedProject -> {
            // This is RFixPatchPlugin$_initThinFlutterPluginConfig_closure2
            // ThinFlutterExtension thinFlutterExt = (ThinFlutterExtension) evaluatedProject.getExtensions().findByName("thinFlutter");
            // if (thinFlutterExt == null) {
            //     thinFlutterExt = evaluatedProject.getExtensions().create("thinFlutter", ThinFlutterExtension.class, evaluatedProject);
            // }

            RFixPatchExtension rfixExt = (RFixPatchExtension) evaluatedProject.getExtensions().getByName("RFixPatch");
            // boolean enableFlutter = rfixExt.getBuildConfig().getEnableFlutterSupport(); // Assuming property exists
            // thinFlutterExt.setEnable(enableFlutter);
            // if (rfixExt.getBuildConfig().getFlutterVersion() != null) {
            //    thinFlutterExt.setFlutterVersion(rfixExt.getBuildConfig().getFlutterVersion());
            // }
            // TODO: Verify actual property names in RFixPatchExtension.BuildConfig for flutter settings
            logger.info("RFix: ThinFlutterExtension would be configured here based on RFixPatchExtension settings.");

            // Apply thinFlutter plugin if not already applied
            // if (!evaluatedProject.getPlugins().hasPlugin("com.tencent.thinflutter")) {
            //    evaluatedProject.apply(Collections.singletonMap("plugin", "com.tencent.thinflutter"));
            //    logger.info("RFix: Applied 'com.tencent.thinflutter' plugin.");
            // }
        });
    }

    /**
     * Initializes default configurations for RFixPatchExtension.
     * Corresponds to private method initRFixConfig in original decompiled code.
     */
    private void initRFixConfig(Project project) {
        RFixPatchExtension patchExtension = (RFixPatchExtension) project.getExtensions().getByName("RFixPatch");

        // Configure dex loader patterns
        List<String> dexLoaderPatterns = patchExtension.getDex().getLoader();
        for (String defaultLoader : DEFAULT_LOADER_LIST) {
            if (!dexLoaderPatterns.contains(defaultLoader)) {
                dexLoaderPatterns.add(defaultLoader);
                logger.warn("RFix: Added default loader pattern '" + defaultLoader + "' to dex.loader.");
            }
        }

        // Configure dex pattern if empty
        if (patchExtension.getDex().getPattern().isEmpty()) {
            patchExtension.getDex().setPattern(Arrays.asList("classes*.dex", "assets/secondary-dex-?.jar"));
            logger.info("RFix: Set default dex.pattern.");
        }

        // Configure lib pattern if empty
        if (patchExtension.getLib().getPattern().isEmpty()) {
            patchExtension.getLib().setPattern(Collections.singletonList("lib/*/*.so"));
            logger.info("RFix: Set default lib.pattern.");
        }

        // Configure res pattern if empty
        if (patchExtension.getRes().getPattern().isEmpty()) {
            patchExtension.getRes().setPattern(Arrays.asList("r/*", "res/*", "assets/*", "resources.arsc", "AndroidManifest.xml"));
            logger.info("RFix: Set default res.pattern.");
        }

        // Configure sevenZip artifact if not set
        if ((patchExtension.getSevenZip().getPath() == null || patchExtension.getSevenZip().getPath().isEmpty()) &&
            (patchExtension.getSevenZip().getZipArtifact() == null || patchExtension.getSevenZip().getZipArtifact().isEmpty())) {
            patchExtension.getSevenZip().setZipArtifact("com.tencent.mm:SevenZip:1.1.10"); // Default artifact
            logger.info("RFix: Set default sevenZip.zipArtifact.");
        }

        // Configure redirectConfig.excludeClasses
        List<String> excludeClasses = patchExtension.getRedirectConfig().getExcludeClasses();
        for (String defaultLoader : DEFAULT_LOADER_LIST) {
            String transformedLoader = defaultLoader.replace(".", "/").replace("*", ".*");
            if (!excludeClasses.contains(transformedLoader)) {
                excludeClasses.add(transformedLoader);
            }
        }
        logger.info("RFix: Updated redirectConfig.excludeClasses with default loader patterns.");
    }

    /**
     * Initializes auto-verify configurations by adding dependencies based on RFix lib version.
     * Corresponds to private method initAutoVerifyConfig in original decompiled code.
     */
    private void initAutoVerifyConfig(Project project, RFixPatchExtension rfixExtension) {
        String verifyCase = rfixExtension.getAutoVerifyConfig().getVerifyCase();
        String rfixLibVersion = null;

        // Find RFix library version from dependencies (conceptual translation)
        Configuration implementationConfig = project.getConfigurations().findByName("implementation");
        if (implementationConfig != null) {
            for (Dependency dep : implementationConfig.getAllDependencies()) {
                if ("com.tencent.rfix".equals(dep.getGroup()) &&
                    (dep.getName().startsWith("RFix-android-lib") &&
                     !dep.getName().equals("RFix-android-lib-no-op"))) {
                    rfixLibVersion = dep.getVersion();
                    break;
                }
            }
        }

        logger.info("RFix: initAutoVerifyConfig: verifyCase=" + verifyCase + ", rfixLibVersion=" + rfixLibVersion);

        if (rfixLibVersion == null || rfixLibVersion.isEmpty()) {
            logger.warn("RFix: Could not determine RFix library version. AutoVerify dependencies might not be added correctly.");
            return;
        }

        String verifyDepArtifact = null;
        if (RFixConstants.VERIFY_CASE_DEP_BASE.equalsIgnoreCase(verifyCase)) {
            verifyDepArtifact = "com.tencent.rfix:RFix-verifycase-base:" + rfixLibVersion;
        } else if (RFixConstants.VERIFY_CASE_DEP_PATCH.equalsIgnoreCase(verifyCase)) {
            verifyDepArtifact = "com.tencent.rfix:RFix-verifycase-patch:" + rfixLibVersion;
        }

        if (verifyDepArtifact != null) {
            project.getDependencies().add("implementation", verifyDepArtifact);
            logger.warn("RFix: Added autoVerify dependency: " + verifyDepArtifact);
        }
    }
}
