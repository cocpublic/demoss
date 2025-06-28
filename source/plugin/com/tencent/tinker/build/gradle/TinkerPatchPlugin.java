package com.tencent.tinker.build.gradle;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.api.BaseVariantOutput; // For iterating variant outputs
import com.android.build.gradle.internal.dsl.DexOptions;
import com.tencent.tinker.build.gradle.extension.TinkerArkHotExtension;
import com.tencent.tinker.build.gradle.extension.TinkerBuildConfigExtension;
import com.tencent.tinker.build.gradle.extension.TinkerDexExtension;
import com.tencent.tinker.build.gradle.extension.TinkerLibExtension;
import com.tencent.tinker.build.gradle.extension.TinkerPackageConfigExtension;
import com.tencent.tinker.build.gradle.extension.TinkerPatchExtension;
import com.tencent.tinker.build.gradle.extension.TinkerResourceExtension;
import com.tencent.tinker.build.gradle.extension.TinkerSevenZipExtension;
// TODO: Import specific Task classes once they are translated/created
// import com.tencent.tinker.build.gradle.task.TinkerManifestAction;
// import com.tencent.tinker.build.gradle.task.TinkerMultidexConfigTask;
// import com.tencent.tinker.build.gradle.task.TinkerPatchSchemaTask;
// import com.tencent.tinker.build.gradle.task.TinkerProguardConfigAction;
// import com.tencent.tinker.build.gradle.task.TinkerResourceIdTask;
// import com.tencent.tinker.build.gradle.transform.ImmutableDexTransform;
import com.tencent.tinker.build.util.FileOperation;
import com.tencent.tinker.build.util.TinkerBuildPath;
// import com.tencent.tinker.build.util.Utils; // Not directly used in this refactoring pass

import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.logging.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
// import sun.misc.Unsafe; // Avoid Unsafe if possible, comment out for now

/**
 * Gradle plugin for Tinker patch generation.
 *
 * This class is a translation from decompiled (originally Groovy) code.
 * The aim is to make it more readable Java, representing Groovy closures
 * with Java lambdas or Action classes, and clarifying dynamic dispatch.
 *
 * TODO: Translate helper classes like `Compatibilities.java` which are crucial for AGP interaction.
 * TODO: Translate actual Task classes (TinkerPatchSchemaTask, etc.) and Action classes.
 * TODO: Refine AGP task interaction once `Compatibilities.java` is clearer or replaced with direct AGP APIs.
 */
public class TinkerPatchPlugin implements Plugin<Project> {

    public static final String ISSUE_URL = "https://github.com/Tencent/tinker/issues";
    private Project project;
    private Logger logger;

    // Placeholder for the TinkerManifestAction logic, as it's complex and involves map state.
    // In a full translation, this would be its own class.
    private static class TinkerManifestActionHelper implements Action<Task> {
        private final Project project;
        public final Map<String, String> outputNameToManifestMap = new HashMap<>();

        public TinkerManifestActionHelper(Project project) {
            this.project = project;
        }

        @Override
        public void execute(Task task) {
            // Actual manifest modification logic would go here if this Action was directly performing it.
            // In Tinker, it seems this action primarily collects manifest paths.
            project.getLogger().info("TinkerManifestActionHelper: Task " + task.getName() + " finished. Manifest map collected: " + outputNameToManifestMap.size() + " entries.");
        }
    }


    @Override
    public void apply(Project project) {
        this.project = project;
        this.logger = project.getLogger();

        // Apply osdetector plugin
        try {
            project.apply(Collections.singletonMap("plugin", "osdetector"));
        } catch (Exception e) {
            try {
                project.apply(Collections.singletonMap("plugin", "com.google.osdetector"));
            } catch (Exception e2) {
                logger.warn("Failed to apply 'osdetector' or 'com.google.osdetector' plugin.", e2);
            }
        }

        // Register extensions
        // Original: siteArr0[2].call(siteArr0[3].callGetProperty(this.mProject), "tinkerPatch", TinkerPatchExtension.class);
        final TinkerPatchExtension tinkerPatchExtension = project.getExtensions().create("tinkerPatch", TinkerPatchExtension.class);
        project.getExtensions().add("tinkerPatch", tinkerPatchExtension); // Make it accessible

        // Nested extensions within tinkerPatchExtension
        // Original: siteArr0[4].call(siteArr0[5].callGetProperty(siteArr0[6].callGetProperty(this.mProject)), "buildConfig", TinkerBuildConfigExtension.class, this.mProject);
        tinkerPatchExtension.getExtensions().create("buildConfig", TinkerBuildConfigExtension.class, project);
        tinkerPatchExtension.getExtensions().create("dex", TinkerDexExtension.class, project);
        tinkerPatchExtension.getExtensions().create("lib", TinkerLibExtension.class);
        tinkerPatchExtension.getExtensions().create("res", TinkerResourceExtension.class);
        tinkerPatchExtension.getExtensions().create("arkHot", TinkerArkHotExtension.class); // Assuming TinkerArkHotExtension exists
        tinkerPatchExtension.getExtensions().create("packageConfig", TinkerPackageConfigExtension.class, project);
        tinkerPatchExtension.getExtensions().create("sevenZip", TinkerSevenZipExtension.class, project);


        // Check for Android Application plugin
        // Original: if (DefaultTypeTransformation.booleanUnbox(siteArr0[25].call(siteArr0[26].callGetProperty(this.mProject), "com.android.application")) ? 0 : 1 != 0)
        if (!project.getPlugins().hasPlugin("com.android.application")) {
            throw new GradleException("TinkerPatchPlugin: Android Application plugin required ('com.android.application').");
        }

        // Configure Android specifics
        // Original: android = new siteArr0[28].callGetProperty(siteArr0[29].callGetProperty(this.mProject))
        AppExtension androidExtension = (AppExtension) project.getExtensions().getByName("android");

        // Modify dexOptions
        // Original: ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i0), null, siteArr0[30].callGetProperty(android.get()), (String)"preDexLibraries");
        // Original: ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i1), null, siteArr0[31].callGetProperty(android.get()), (String)"jumboMode");
        // Original: ScriptBytecodeAdapter.setProperty(Boolean.valueOf(i2), null, siteArr0[33].callGetProperty(android.get()), (String)"keepRuntimeAnnotatedClasses");
        if (androidExtension != null) {
            DexOptions dexOptions = androidExtension.getDexOptions();
            if (dexOptions != null) {
                dexOptions.setPreDexLibraries(false);
                dexOptions.setJumboMode(true);
                // dexOptions.keepRuntimeAnnotatedClasses was a property, might not be a setter
                // For AGP 3.0+, this might be handled differently or not available.
                // It was originally: ScriptBytecodeAdapter.setProperty(Boolean.valueOf(false), null, dexOptions, "keepRuntimeAnnotatedClasses");
                // We'll log if we can't set it.
                try {
                    Field keepRuntimeField = DexOptions.class.getDeclaredField("keepRuntimeAnnotatedClasses");
                    keepRuntimeField.setAccessible(true);
                    keepRuntimeField.set(dexOptions, false);
                } catch (NoSuchFieldException e) {
                    logger.debug("DexOptions.keepRuntimeAnnotatedClasses field not found, might be AGP version specific.");
                } catch (IllegalAccessException e) {
                    logger.warn("Could not set DexOptions.keepRuntimeAnnotatedClasses.", e);
                }
            }

            // Attempt to disable archive dex. This is a hack and might break with AGP updates.
            // Original: siteArr0[32].callCurrent(this);
            disableArchiveDex();
        }


        // The main logic is in an afterEvaluate block.
        // Original: siteArr0[34].call(this.mProject, new TinkerPatchPlugin$_apply_closure1(this, this, reference, android));
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project evaluatedProject) {
                configureTasksAfterEvaluate(evaluatedProject, tinkerPatchExtension, androidExtension);
            }
        });
    }

    /**
     * This method contains the logic originally in the main afterEvaluate closure.
     */
    private void configureTasksAfterEvaluate(Project project, TinkerPatchExtension tinkerPatchExt, AppExtension androidExt) {
        // Original closure: TinkerPatchPlugin$_apply_closure1
        // Object configuration = project.getExtensions().getByName("tinkerPatch"); // This is tinkerPatchExt

        if (!tinkerPatchExt.getTinkerEnable()) {
            logger.error("Tinker tasks are disabled by tinkerPatch.tinkerEnable = false.");
            return;
        }

        // Log warnings and information
        logger.error("----------------------tinker build warning ------------------------------------");
        logger.error("tinker auto operation: ");
        logger.error("excluding annotation processor and source template from app packaging. Enable dx jumboMode to reduce package size.");
        logger.error("enable dx jumboMode to reduce package size.");
        logger.error("disable preDexLibraries to prevent ClassDefNotFoundException when your app is booting.");
        logger.error("disable archive dex mode so far for keeping dex apply.");
        logger.error("");
        logger.error("tinker will change your build configs:");
        logger.error("we will add TINKER_ID=" + tinkerPatchExt.getBuildConfig().getTinkerId() +
                     " in your build output manifest file " + project.getBuildDir() + "/intermediates/manifests/full/*");
        logger.error("");
        logger.error("if minifyEnabled is true");

        String tempMappingPath = tinkerPatchExt.getBuildConfig().getApplyMapping();
        if (FileOperation.isLegalFile(tempMappingPath)) {
            logger.error("we will build " + project.getName() + " apk with apply mapping file " + tempMappingPath);
        }
        logger.error("you will find the gen proguard rule file at " + TinkerBuildPath.getProguardConfigPath(project));
        logger.error("and we will help you to put it in the proguardFiles.");
        logger.error("");
        logger.error("if multiDexEnabled is true");
        logger.error("you will find the gen multiDexKeepProguard file at " + TinkerBuildPath.getMultidexConfigPath(project));
        logger.error("and we will help you to put it in the MultiDexKeepProguardFile.");
        logger.error("");
        logger.error("if applyResourceMapping file is exist");

        String tempResourceMappingPath = tinkerPatchExt.getBuildConfig().getApplyResourceMapping();
        if (FileOperation.isLegalFile(tempResourceMappingPath)) {
            logger.error("we will build " + project.getName() + " apk with resource R.txt " + tempResourceMappingPath + " file");
        } else {
            logger.error("we will build " + project.getName() + " apk with resource R.txt file");
        }
        logger.error("if resources.arsc has changed, you should use applyResource mode to build the new apk!");
        logger.error("-----------------------------------------------------------------");

        // Iterate over application variants to configure tasks
        // Original: siteArr0[95].call(siteArr0[96].callGetProperty(this.android.get()), new TinkerPatchPlugin$_apply_closure1$_closure2(this, this.getThisObject(), this.project, configuration));
        androidExt.getApplicationVariants().all(new Action<ApplicationVariant>() {
            @Override
            public void execute(ApplicationVariant variant) {
                // This maps to the closure: TinkerPatchPlugin$_apply_closure1$_closure2
                configureVariantTasks(project, tinkerPatchExt, androidExt, variant);
            }
        });
    }

    /**
     * Configures Tinker tasks for a specific Android application variant.
     * Originally TinkerPatchPlugin$_apply_closure1$_closure2 closure.
     */
    private void configureVariantTasks(Project project, TinkerPatchExtension tinkerExt, AppExtension androidExt, ApplicationVariant variant) {
        String variantName = variant.getName();
        String capitalizedVariantName = variantName.substring(0, 1).toUpperCase() + variantName.substring(1);

        // Check for Instant Run
        // Object instantRunTask = Compatibilities.getInstantRunTask(project, variant); // Assuming Compatibilities class
        // if (instantRunTask != null) {
        //     throw new GradleException("Tinker does not support instant run mode, please trigger build by assemble" +
        //                               capitalizedVariantName + " or disable instant run in 'File->Settings...'.");
        // }
        // TODO: Implement or verify Compatibilities.getInstantRunTask if needed. For now, logging.
        if (project.getGradle().getStartParameter().getTaskNames().stream().anyMatch(s -> s.contains("InstantRun"))) {
             logger.warn("Tinker might not be compatible with Instant Run for variant: " + variantName);
        }


        // Create tinkerPatch<VariantName> task (TinkerPatchSchemaTask)
        // Original: tinkerPatchBuildTask = new (TinkerPatchSchemaTask)ScriptBytecodeAdapter.castToType(siteArr0[6].call(siteArr0[7].callGetProperty(siteArr0[8].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{capitalizedVariantName}, new String[]{"tinkerPatch", ""}), TinkerPatchSchemaTask.class), TinkerPatchSchemaTask.class)
        TinkerPatchSchemaTask tinkerPatchBuildTask = project.getTasks().create("tinkerPatch" + capitalizedVariantName, TinkerPatchSchemaTask.class);
        if (variant.getSigningConfig() != null) {
            // tinkerPatchBuildTask.setSignConfig(variant.getSigningConfig()); // Property might not exist directly
            // Original: ScriptBytecodeAdapter.setGroovyObjectProperty(object, TinkerPatchPlugin$_apply_closure1$_closure2.class, (TinkerPatchSchemaTask)tinkerPatchBuildTask.get(), (String)"signConfig");
            // This suggests a dynamic property or a convention. For now, we assume a setter or direct field access if available.
            // TODO: Verify how signConfig is set on TinkerPatchSchemaTask
        }

        // Process Manifest Task
        // Original: agpProcessManifestTask = new siteArr0[10].call(Compatibilities.class, this.project.get(), (ApkVariant)reference.get())
        // Task processManifestTask = Compatibilities.getProcessManifestTask(project, variant); // Assuming
        // TODO: Implement or verify Compatibilities.getProcessManifestTask
        // For now, we'll try to get it by name convention, this is fragile.
        Task processManifestTask = project.getTasks().findByName("process" + capitalizedVariantName + "Manifest");

        if (processManifestTask != null) {
            // TinkerManifestAction tinkerManifestAction = new TinkerManifestAction(project);
            // processManifestTask.doLast(tinkerManifestAction); // This was the original structure
            // TODO: Re-evaluate TinkerManifestAction and its integration.
            // The decompiled code is very complex here with nested closures.
            // siteArr0[13].call(siteArr0[14].callGetProperty((ApkVariant)reference.get()), new TinkerPatchPlugin$_apply_closure1$_closure2$_closure3(this, this.getThisObject(), this.configuration, reference, tinkerPatchBuildTask, tinkerManifestAction, this.project, agpProcessManifestTask));
            // This iterates over variant.getOutputs() and configures tinkerPatchBuildTask & tinkerManifestAction.
            // This part needs careful translation of the TinkerPatchPlugin$_apply_closure1$_closure2$_closure3 logic.
            logger.info("Manifest processing for " + variantName + " would be configured here.");
        } else {
            logger.warn("Could not find process" + capitalizedVariantName + "Manifest task.");
        }

        // Process Resources Task & TinkerResourceIdTask
        // Task processResourcesTask = Compatibilities.getProcessResourcesTask(project, variant);
        // TinkerResourceIdTask applyResourceTask = project.getTasks().create("tinkerProcess" + capitalizedVariantName + "ResourceId", TinkerResourceIdTask.class);
        // applyResourceTask.setVariant(variant);
        // applyResourceTask.setApplicationId(Compatibilities.getApplicationId(project, variant));
        // applyResourceTask.setResDir(Compatibilities.getInputResourcesDirectory(project, processResourcesTask));
        // if (processManifestTask != null) applyResourceTask.mustRunAfter(processManifestTask);
        // if (processResourcesTask != null) processResourcesTask.dependsOn(applyResourceTask);
        // Task mergeResourcesTask = Compatibilities.getMergeResourcesTask(project, variant);
        // if (mergeResourcesTask != null) applyResourceTask.dependsOn(mergeResourcesTask);
        // TODO: Implement or verify Compatibilities methods and complete this section.
        logger.info("Resource ID processing for " + variantName + " would be configured here.");


        // Proguard and Multidex configuration also happens here, similar complexity.
        // boolean proguardEnable = variant.getBuildType().isMinifyEnabled();
        // if (proguardEnable) { ... }
        // boolean multiDexEnabled = variant.getMergedFlavor().getMultiDexEnabled() == Boolean.TRUE;
        // if (multiDexEnabled) { ... }

        // ImmutableDexTransform injection
        // if (tinkerExt.getBuildConfig().getKeepDexApply() && FileOperation.isLegalFile(tinkerExt.getTinkerPatch().getOldApk())) {
        //     ImmutableDexTransform.inject(project, variant);
        // }
        logger.info("Proguard, Multidex, and ImmutableDexTransform for " + variantName + " would be configured here.");
    }


    /**
     * Attempts to disable dex archiving by modifying AGP's BooleanOption.ENABLE_DEX_ARCHIVE.
     * This is a reflective hack and may not work or be stable across AGP versions.
     */
    public void disableArchiveDex() {
        // Original code uses reflection on com.android.build.gradle.options.BooleanOption.ENABLE_DEX_ARCHIVE
        // This is highly version-dependent and risky.
        // For simplicity and stability in a translated version, this might be omitted or
        // replaced with a warning that manual configuration might be needed if issues arise.
        logger.info("Attempting to disable dex archive via reflection (original behavior). This may be unstable.");
        try {
            Class<?> booleanOptionClass = Class.forName("com.android.build.gradle.options.BooleanOption");
            Field enableDexArchiveField = booleanOptionClass.getDeclaredField("ENABLE_DEX_ARCHIVE");
            enableDexArchiveField.setAccessible(true);
            Object enableDexArchiveEnumValue = enableDexArchiveField.get(null); // It's an enum instance

            Field defaultValueField = enableDexArchiveEnumValue.getClass().getDeclaredField("defaultValue");
            Field finalField = Field.class.getDeclaredField("modifiers");
            finalField.setAccessible(true);
            finalField.setInt(defaultValueField, defaultValueField.getModifiers() & ~java.lang.reflect.Modifier.FINAL);

            defaultValueField.setAccessible(true);
            defaultValueField.set(enableDexArchiveEnumValue, false); // Try to set default to false

            // The original code also tried to set the accessible flag of the enum field itself,
            // and then set its value. This is even more complex and fragile.
            // For now, just logging the intent.
            logger.warn("Original Tinker plugin tries to modify BooleanOption.ENABLE_DEX_ARCHIVE directly. " +
                        "If dex archiving issues occur, investigate AGP compatibility.");

        } catch (ClassNotFoundException e) {
            logger.warn("Could not find AGP's BooleanOption class. Dex archive settings might not be modified. Error: " + e.getMessage());
        } catch (NoSuchFieldException e) {
            logger.warn("Could not find necessary fields in BooleanOption/enum. Dex archive settings might not be modified. Error: " + e.getMessage());
        } catch (IllegalAccessException e) {
            logger.warn("Could not access fields to modify dex archive settings. Error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred while trying to disable dex archive: " + e.getMessage(), e);
        }
    }

    // The following methods were part of the original decompiled class but are complex
    // due to Groovy-to-Java translation artifacts (CallSite, ScriptBytecodeAdapter, etc.)
    // They would require significant effort to fully translate into clean Java.
    // For now, they are stubbed or commented out, with their intent noted.

    /**
     * Sets the output folder for the patch.
     * Original was heavily reliant on Groovy dynamic calls.
     */
    public void setPatchOutputFolder(Object configuration, Object output, Object variant, Object tinkerPatchBuildTask) {
        // CallSite[] siteArr0 = TinkerPatchPlugin.$getCallSiteArray();
        // File parentFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[35].callGetProperty(output), File.class);
        // String outputFolder = (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{siteArr0[36].callGetProperty(configuration)}, new String[]{"", ""}));
        // ... complex logic using GStringImpl and Utils.isNullOrNil ...
        // ScriptBytecodeAdapter.setProperty(outputFolder, null, tinkerPatchBuildTask, (String)"outputFolder");
        logger.info("[STUB] setPatchOutputFolder called. Original logic was complex.");
        // TODO: Translate this method if its exact logic is critical.
        // It seems to determine the output folder based on configuration and variant, then sets it on the task.
    }

    /**
     * Sets the path for the new APK, potentially depending on the assemble task.
     * Original was heavily reliant on Groovy dynamic calls.
     */
    public void setPatchNewApkPath(Object configuration, Object output, Object variant, Object tinkerPatchBuildTask) {
        // CallSite[] siteArr0 = TinkerPatchPlugin.$getCallSiteArray();
        // Object newApkPath = siteArr0[55].callGetProperty(configuration);
        // if (DefaultTypeTransformation.booleanUnbox(siteArr0[56].call(Utils.class, newApkPath)) ? 0 : 1 != 0 && DefaultTypeTransformation.booleanUnbox(siteArr0[57].call(FileOperation.class, newApkPath))) {
        //     ScriptBytecodeAdapter.setProperty(newApkPath, null, tinkerPatchBuildTask, (String)"buildApkPath");
        // } else {
        //     Object objectVar1 = siteArr0[58].callGetProperty(output);
        //     ScriptBytecodeAdapter.setProperty(objectVar1, null, tinkerPatchBuildTask, (String)"buildApkPath");
        //     siteArr0[59].call(tinkerPatchBuildTask, siteArr0[60].call(Compatibilities.class, this.mProject, variant)); // dependsOn assemble task
        // }
        logger.info("[STUB] setPatchNewApkPath called. Original logic was complex.");
        // TODO: Translate this method. It sets the 'buildApkPath' on the task,
        // potentially making it depend on the AGP assemble task for the variant.
    }

    /**
     * Uses Unsafe to replace a final field in a Kotlin class. Extremely unsafe.
     * Original was heavily reliant on Groovy dynamic calls.
     */
    public void replaceKotlinFinalField(String className, String fieldName, Object instance, Object fieldValue) {
        logger.warn("Attempting to use Unsafe.putObject to modify a final field: " + className + "#" + fieldName + ". This is highly discouraged.");
        try {
            Field field = Class.forName(className).getDeclaredField(fieldName);
            field.setAccessible(true); // Make the field accessible first

            // Get the Unsafe instance
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);

            // Make the target field non-final (if possible via reflection on Field itself)
            // This is a common trick to bypass finality for reflective modification.
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~java.lang.reflect.Modifier.FINAL);

            // Now set the value
            // For static fields, instance would be field.getDeclaringClass(), for instance fields, it's the instance.
            // Unsafe.putObject needs an object and an offset, or just the object for static fields if using putStaticObject.
            // The original code used objectFieldOffset, which is for instance fields.
            long fieldOffset = unsafe.objectFieldOffset(field);
            unsafe.putObject(instance, fieldOffset, fieldValue);

        } catch (Exception e) {
            logger.error("Failed to replace Kotlin final field " + className + "#" + fieldName, e);
            // throw new RuntimeException("Failed to replace Kotlin final field", e);
        }
    }


    /**
     * Retrieves the multiDexKeepProguard file using various reflective calls into AGP.
     * This is extremely brittle and likely to break with AGP updates.
     * Original was a huge chain of CallSite calls.
     */
    public File getManifestMultiDexKeepProguard(Object applicationVariant) {
        logger.warn("[STUB] getManifestMultiDexKeepProguard called. Original logic was extremely complex and relied on AGP internals via reflection.");
        // The original code tries multiple ways to get this file, accessing properties like:
        // - applicationVariant.variantData.artifacts.get(LEGACY_MULTIDEX_AAPT_DERIVED_PROGUARD_RULES).asFile
        // - applicationVariant.variantData.scope.artifacts.getFinalProduct(LEGACY_MULTIDEX_AAPT_DERIVED_PROGUARD_RULES).asFile
        // - applicationVariant.variantData.scope.artifacts.getFinalArtifactFiles(LEGACY_MULTIDEX_AAPT_DERIVED_PROGUARD_RULES).singleFile
        // - applicationVariant.variantData.scope.manifestKeepListProguardFile
        // - applicationVariant.variantData.scope.manifestKeepListFile
        // This indicates a high degree of coupling with specific AGP versions and internal structures.
        // A robust translation would require abstracting this access or relying on newer, more stable AGP APIs if available.
        // For now, returning null and logging.
        return null;
        // TODO: If this functionality is critical, it needs a careful, version-aware reimplementation.
    }
}
