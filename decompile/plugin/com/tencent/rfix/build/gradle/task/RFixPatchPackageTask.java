/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/rfix/build/gradle/task;

import org.codehaus.groovy.runtime.callsite.CallSite[];
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.reflection.ClassInfo;
import org.jf.dexlib2.iface.MultiDexContainer;
import org.jf.dexlib2.dexbacked.DexBackedDexFile;
import org.jf.dexlib2.dexbacked.DexBackedClassDef;
import org.jf.dexlib2.dexbacked.DexBackedField;
import org.gradle.api.tasks.TaskAction;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import com.tencent.rfix.build.gradle.extension.RFixPatchExtension;
import com.android.builder.model.SigningConfig;
import java.util.List;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.io.File;
import java.io.OutputStream;

// class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask
public class RFixPatchPackageTask implements RFixPatchMonitor$IShouldMonitorTask, GroovyObject {
    final private static String TAG;
    protected RFixPatchExtension configuration;
    protected SigningConfig signingConfig;
    protected List<File> inputFolders;
    protected File outputFolder;
    protected String patchType;
    protected boolean deleteOutputFolder;
    protected boolean packageSeparateMode;
    private static synthetic ClassInfo $staticClassInfo;
    public static transient synthetic boolean __$stMC;
    private transient synthetic MetaClass metaClass;
    private static synthetic ClassInfo $staticClassInfo$;
    private static synthetic SoftReference $callSiteArray;

    public RFixPatchPackageTask() {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        super();
        MetaClass class = this.$getStaticMetaClass();
        class.metaClass = this;
        Object object = siteArr0[0].callGetProperty(RFixPatchPlugin.class);
        ScriptBytecodeAdapter.setGroovyObjectProperty(object, RFixPatchPackageTask.class, this, (String)"group");
        Object objectVar1 = siteArr0[1].callGetProperty(siteArr0[2].callGetProperty(siteArr0[3].callGroovyObjectGetProperty(this)));
        (RFixPatchExtension)ScriptBytecodeAdapter.castToType(objectVar1, RFixPatchExtension.class).configuration = this;
        Object objectVar2 = siteArr0[4].callConstructor(ArrayList.class);
        (List)ScriptBytecodeAdapter.castToType(objectVar2, List.class).inputFolders = this;
    }

    public boolean shouldMonitorTask() {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        return true;
    }

    public void setSigningConfig(SigningConfig signingConfig) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        (SigningConfig)ScriptBytecodeAdapter.castToType(signingConfig, SigningConfig.class).signingConfig = this;
    }

    public void setPatchType(String patchType) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        (String)ShortTypeHandling.castToString(patchType).patchType = this;
    }

    public void setDeleteOutputFolder(boolean deleteOutputFolder) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        DefaultTypeTransformation.booleanUnbox(Boolean.valueOf(deleteOutputFolder)).deleteOutputFolder = this;
    }

    public void setPackageSeparateMode(boolean packageSeparateMode) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        DefaultTypeTransformation.booleanUnbox(Boolean.valueOf(packageSeparateMode)).packageSeparateMode = this;
    }

    public void setInputFolders(List<String> folders) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        siteArr0[5].call(this.inputFolders);
        Object folder = null;
        Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[6].call(folders), Iterator.class);
        while (iterator.hasNext()) {
            String str0 = (String)ShortTypeHandling.castToString(iterator.next());
            siteArr0[7].call(this.inputFolders, siteArr0[8].callConstructor(File.class, str0));
        }
    }

    public void setOutputFolder(ApkVariant variant, BaseVariantOutput variantOutput) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        Object patchDirName = siteArr0[9].callGetProperty(RFixConstants.class);
        Object outputFolder = siteArr0[10].callGroovyObjectGetProperty(this.configuration);
        GStringImpl implVar1;
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[11].call(Utils.class, outputFolder)) ? 0 : 1 != 0) {
            GStringImpl impl = new GStringImpl(new Object[]{outputFolder, patchDirName, siteArr0[12].callGetProperty(variant)}, new String[]{"", "/", "/", ""});
        }
        else {
            File outputsFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[13].callGetProperty(siteArr0[14].callGetProperty(siteArr0[15].callGetProperty(siteArr0[16].callGetProperty(variantOutput)))), File.class);
            File patchFile = (File)ScriptBytecodeAdapter.castToType(siteArr0[17].callConstructor(File.class, outputsFile, siteArr0[18].callGetProperty(RFixConstants.class)), File.class);
            GStringImpl implVar2 = new GStringImpl(new Object[]{siteArr0[19].callGetProperty(patchFile), patchDirName, siteArr0[20].callGetProperty(variant)}, new String[]{"", "/", "/", ""});
            implVar1 = implVar2;
        }
        Object object = siteArr0[21].callConstructor(File.class, impl);
        (File)ScriptBytecodeAdapter.castToType(object, File.class).outputFolder = this;
    }

    @TaskAction
    public void packagePatch() {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        siteArr0[22].callCurrent(this, "package patch...");
        siteArr0[23].callCurrent(this, siteArr0[24].call(new GStringImpl(new Object[]{this.patchType, this.inputFolders, this.outputFolder}, new String[]{"patchType=", " inputFolders=", " outputFolder=", ""}), new GStringImpl(new Object[]{this.signingConfig}, new String[]{" signingConfig=", ""})));
        if (RFixPatchPackageTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 154;
            this.checkParams();
        }
        else {
            siteArr0[25].callCurrent(this);
        }
        if (RFixPatchPackageTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 189;
            this.prepare();
            v_57 = null;
        }
        else {
            siteArr0[26].callCurrent(this);
        }
        if (BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && RFixPatchPackageTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 292;
            if (this.packageSeparateMode && ScriptBytecodeAdapter.compareGreaterThan(siteArr0[30].call(this.inputFolders), Integer.valueOf(1)) ? 0 : 1 != 0) {
                this.packageSeparatePatch();
                v_75 = null;
            }
            else {
                this.packageUniversalPatch();
                v_92 = null;
            }
        }
        else {
            if (this.packageSeparateMode && ScriptBytecodeAdapter.compareGreaterThan(siteArr0[27].call(this.inputFolders), Integer.valueOf(1)) ? 0 : 1 != 0) {
                siteArr0[28].callCurrent(this);
            }
            else {
                siteArr0[29].callCurrent(this);
            }
        }
        siteArr0[31].call(this.inputFolders, new RFixPatchPackageTask$_packagePatch_closure1(this, this));
        siteArr0[32].callCurrent(this, "package patch done!");
    }

    private boolean checkParams() {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(this.signingConfig, null)) {
            siteArr0[33].callCurrent(this, "checkParams no sign has config?");
        }
        if (BytecodeInterface8.isOrigZ() && RFixPatchPackageTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 159;
            if (! ScriptBytecodeAdapter.compareEqual(this.inputFolders, null) || DefaultTypeTransformation.booleanUnbox(siteArr0[36].call(this.inputFolders)) ? 0 : 1 != 0 || ScriptBytecodeAdapter.compareEqual(this.outputFolder, null) ? 0 : 1 != 0 || ScriptBytecodeAdapter.compareEqual(this.patchType, null) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[37].callConstructor(IllegalArgumentException.class, "checkParams fail!");
            }
            else {
                return DefaultTypeTransformation.booleanUnbox(null);
            }
        }
        else {
            if (! ScriptBytecodeAdapter.compareEqual(this.inputFolders, null) || DefaultTypeTransformation.booleanUnbox(siteArr0[34].call(this.inputFolders)) ? 0 : 1 != 0 || ScriptBytecodeAdapter.compareEqual(this.outputFolder, null) ? 0 : 1 != 0 || ScriptBytecodeAdapter.compareEqual(this.patchType, null) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[35].callConstructor(IllegalArgumentException.class, "checkParams fail!");
            }
            else {
                return DefaultTypeTransformation.booleanUnbox(null);
            }
        }
    }

    private void prepare() {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        if (this.deleteOutputFolder) {
            siteArr0[38].call(PatchFileUtils.class, this.outputFolder);
            siteArr0[39].call(this.outputFolder);
        }
    }

    private void packageUniversalPatch() {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        String patchSuffix = "";
        List inputs = this.inputFolders;
        Object oldApks = siteArr0[40].callGroovyObjectGetProperty(this.configuration);
        siteArr0[41].callCurrent(this, patchSuffix, inputs, oldApks);
    }

    private void packageSeparatePatch() {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        siteArr0[42].call(this.inputFolders, new RFixPatchPackageTask$_packageSeparatePatch_closure2(this, this));
    }

    protected void packageOnePatch(Object patchSuffix, List<File> inputs, Iterable<String> oldApks) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        siteArr0[43].callCurrent(this, new GStringImpl(new Object[]{patchSuffix, inputs, oldApks}, new String[]{"packageOnePatch patchSuffix=", " inputs=", " oldApks=", ""}));
        GStringImpl tempFolderName = new GStringImpl(new Object[]{siteArr0[44].callGetProperty(RFixConstants.class), this.patchType, patchSuffix}, new String[]{"", "-", "", ""});
        v_86 = alloc(Reference);
        new (File)ScriptBytecodeAdapter.castToType(siteArr0[45].callConstructor(File.class, siteArr0[46].callGetProperty(siteArr0[47].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[]{siteArr0[48].callGetProperty(RFixConstants.class), tempFolderName}, new String[]{"", "/", ""})), File.class).<init>(v_86);
        Reference tempFolder = v_86;
        siteArr0[49].call(PatchFileUtils.class, (File)tempFolder.get());
        siteArr0[50].call((File)tempFolder.get());
        siteArr0[51].call(inputs, new RFixPatchPackageTask$_packageOnePatch_closure3(this, this, tempFolder));
        siteArr0[52].callCurrent(this, this.patchType, oldApks, (File)tempFolder.get());
        Object apkFileName = siteArr0[53].call(String.class, siteArr0[54].callGetProperty(RFixConstants.class), this.patchType, patchSuffix);
        Object apkFile = siteArr0[55].callConstructor(File.class, (File)tempFolder.get(), apkFileName);
        siteArr0[56].callCurrent(this, (File)tempFolder.get(), apkFile);
        Object destFile = siteArr0[57].callConstructor(File.class, this.outputFolder, apkFileName);
        siteArr0[58].call(FileOperation.class, apkFile, destFile);
        if (ScriptBytecodeAdapter.compareNotEqual(this.signingConfig, null)) {
            Object signedFileName = siteArr0[59].call(String.class, siteArr0[60].callGetProperty(RFixConstants.class), this.patchType, patchSuffix);
            Object signedFile = siteArr0[61].callConstructor(File.class, this.outputFolder, signedFileName);
            Object patchSigner = siteArr0[62].callConstructor(RFixPatchSigner.class, destFile, signedFile, this.signingConfig);
            siteArr0[63].call(patchSigner);
        }
        siteArr0[64].callCurrent(this, new GStringImpl(new Object[]{this.outputFolder}, new String[]{"packageOnePatch package success! you can find them in ", ""}));
    }

    protected boolean copyPatchProduct(int index, File src, File dest) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        siteArr0[65].callCurrent(this, new GStringImpl(new Object[]{Integer.valueOf(index), src, dest}, new String[]{"copyPatchProduct index=", " src=", " dest=", ""}));
        GStringImpl suffix = new GStringImpl(new Object[]{Integer.valueOf(index)}, new String[]{"-", ""});
        Object destSubFolder;
        if (ScriptBytecodeAdapter.compareEqual(this.patchType, siteArr0[66].callGetProperty(RFixConstants.class))) {
            destSubFolder = siteArr0[67].call(siteArr0[68].callGetProperty(RFixConstants.class), suffix);
            return DefaultTypeTransformation.booleanUnbox(siteArr0[69].callCurrent(this, src, dest, destSubFolder));
        }
        else if (ScriptBytecodeAdapter.compareEqual(this.patchType, siteArr0[70].callGetProperty(RFixConstants.class))) {
            destSubFolder = siteArr0[71].call(siteArr0[72].callGetProperty(RFixConstants.class), suffix);
            return DefaultTypeTransformation.booleanUnbox(siteArr0[73].callCurrent(this, src, dest, destSubFolder));
        }
        else if (ScriptBytecodeAdapter.compareEqual(this.patchType, siteArr0[74].callGetProperty(RFixConstants.class))) {
            destSubFolder = siteArr0[75].call(siteArr0[76].callGetProperty(RFixConstants.class), suffix);
            return DefaultTypeTransformation.booleanUnbox(siteArr0[77].callCurrent(this, src, dest, destSubFolder));
        }
        else {
            return false;
        }
    }

    private boolean copyQFixPatchProduct(File src, File dest, String destSubFolder) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[78].callGetProperty(siteArr0[79].callGroovyObjectGetProperty(this.configuration)))) {
            siteArr0[80].callCurrent(this, src, dest, destSubFolder);
        }
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[81].callGetProperty(siteArr0[82].callGroovyObjectGetProperty(this.configuration)))) {
            siteArr0[83].callCurrent(this, src, dest, destSubFolder);
        }
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[84].callGetProperty(siteArr0[85].callGroovyObjectGetProperty(this.configuration)))) {
            siteArr0[86].callCurrent(this, src, dest, destSubFolder);
        }
        return true;
    }

    private boolean copyQFixDexPatch(File src, File dest, String destSubFolder) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        Object dexFile = siteArr0[87].callConstructor(File.class, src, siteArr0[88].callGetProperty(RFixConstants.class));
        Object dexConfigFile = siteArr0[89].callConstructor(File.class, src, siteArr0[90].callGetProperty(RFixConstants.class));
        if (BytecodeInterface8.isOrigZ() && RFixPatchPackageTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 173;
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[94].call(dexFile)) ? 0 : 1 == 0) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[95].call(dexConfigFile)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[96].callCurrent(this, "copyQFixDexPatch dex not exists!");
                return false;
            }
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[91].call(dexFile)) ? 0 : 1 == 0) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[92].call(dexConfigFile)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[93].callCurrent(this, "copyQFixDexPatch dex not exists!");
                return false;
            }
            else {
            }
        }
        Object destFolder = siteArr0[97].callConstructor(File.class, dest, destSubFolder);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[98].call(destFolder)) ? 0 : 1 != 0) {
            siteArr0[99].call(destFolder);
        }
        siteArr0[100].callCurrent(this, new GStringImpl(new Object[]{src, destFolder}, new String[]{"copyQFixDexPatch src=", " destFolder=", ""}));
        Object destDexFile = siteArr0[101].callConstructor(File.class, destFolder, siteArr0[102].callGetProperty(RFixConstants.class));
        siteArr0[103].call(FileOperation.class, dexFile, destDexFile);
        Object destConfigFile = siteArr0[104].callConstructor(File.class, destFolder, siteArr0[105].callGetProperty(RFixConstants.class));
        siteArr0[106].call(FileOperation.class, dexConfigFile, destConfigFile);
        return true;
    }

    private void copyQFixResPatch(File src, File dest, String destSubFolder) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        Object resFile = siteArr0[107].callConstructor(File.class, src, siteArr0[108].callGetProperty(RFixConstants.class));
        Object resConfigFile = siteArr0[109].callConstructor(File.class, src, siteArr0[110].callGetProperty(RFixConstants.class));
        if (BytecodeInterface8.isOrigZ() && RFixPatchPackageTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 172;
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[114].call(resFile)) ? 0 : 1 == 0) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[115].call(resConfigFile)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[116].callCurrent(this, "copyQFixResPatch res not exists!");
            }
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[111].call(resFile)) ? 0 : 1 == 0) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[112].call(resConfigFile)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[113].callCurrent(this, "copyQFixResPatch res not exists!");
            }
            else {
            }
        }
        Object destFolder = siteArr0[117].callConstructor(File.class, dest, destSubFolder);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[118].call(destFolder)) ? 0 : 1 != 0) {
            siteArr0[119].call(destFolder);
        }
        siteArr0[120].callCurrent(this, new GStringImpl(new Object[]{src, destFolder}, new String[]{"copyQFixResPatch src=", " destFolder=", ""}));
        Object destResFile = siteArr0[121].callConstructor(File.class, destFolder, siteArr0[122].callGetProperty(RFixConstants.class));
        siteArr0[123].call(FileOperation.class, resFile, destResFile);
        Object destConfigFile = siteArr0[124].callConstructor(File.class, destFolder, siteArr0[125].callGetProperty(RFixConstants.class));
        siteArr0[126].call(FileOperation.class, resConfigFile, destConfigFile);
    }

    private void copyQFixSoPatch(File src, File dest, String destSubFolder) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        Object srcFolder = siteArr0[127].callConstructor(File.class, src, siteArr0[128].callGetProperty(RFixConstants.class));
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[129].call(srcFolder)) ? 0 : 1 != 0) {
            siteArr0[130].callCurrent(this, "copyQFixSoPatch lib not exists! ");
        }
        else {
            v_31 = alloc(Reference);
            new siteArr0[131].call(srcFolder).<init>(v_31);
            Reference sourceDir = v_31;
            v_58 = alloc(Reference);
            new siteArr0[132].call(siteArr0[133].callConstructor(File.class, dest, siteArr0[134].call(siteArr0[135].call(destSubFolder, "/"), siteArr0[136].callGetProperty(RFixConstants.class)))).<init>(v_58);
            Reference destDir = v_58;
            siteArr0[137].callCurrent(this, new GStringImpl(new Object[]{src, destDir.get()}, new String[]{"copyQFixSoPatch src=", " destFolder=", ""}));
            siteArr0[138].call(siteArr0[139].call(Files.class, sourceDir.get()), new RFixPatchPackageTask$1(this, sourceDir, destDir));
        }
    }

    private boolean copyRedirectPatchProduct(File src, File dest, String destSubFolder) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        siteArr0[140].callCurrent(this, src, dest, destSubFolder);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[141].callGetProperty(siteArr0[142].callGroovyObjectGetProperty(this.configuration)))) {
            siteArr0[143].callCurrent(this, src, dest, destSubFolder);
        }
        return true;
    }

    private boolean copyRedirectDexPatch(File src, File dest, String destSubFolder) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        Object srcDexFile = siteArr0[144].callConstructor(File.class, src, siteArr0[145].callGetProperty(RFixConstants.class));
        Object srcConfigFile = siteArr0[146].callConstructor(File.class, src, siteArr0[147].callGetProperty(RFixConstants.class));
        if (BytecodeInterface8.isOrigZ() && RFixPatchPackageTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            goto 173;
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[151].call(srcDexFile)) ? 0 : 1 == 0) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[152].call(srcConfigFile)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[153].callCurrent(this, "copyRedirectDexPatch dex not exists!");
                return false;
            }
        }
        else {
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[148].call(srcDexFile)) ? 0 : 1 == 0) {
            }
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[149].call(srcConfigFile)) ? 0 : 1 != 0 ? 0 : 1 != 0) {
                siteArr0[150].callCurrent(this, "copyRedirectDexPatch dex not exists!");
                return false;
            }
            else {
            }
        }
        Object destFolder = siteArr0[154].callConstructor(File.class, dest, destSubFolder);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[155].call(destFolder)) ? 0 : 1 != 0) {
            siteArr0[156].call(destFolder);
        }
        siteArr0[157].callCurrent(this, new GStringImpl(new Object[]{src, destFolder}, new String[]{"copyRedirectDexPatch src=", " destFolder=", ""}));
        Object destDexFile = siteArr0[158].callConstructor(File.class, destFolder, siteArr0[159].callGetProperty(RFixConstants.class));
        siteArr0[160].call(FileOperation.class, srcDexFile, destDexFile);
        Object destConfigFile = siteArr0[161].callConstructor(File.class, destFolder, siteArr0[162].callGetProperty(RFixConstants.class));
        siteArr0[163].call(FileOperation.class, srcConfigFile, destConfigFile);
        return true;
    }

    private boolean copyTinkerPatchProduct(File src, File dest, String destSubFolder) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        v_2 = alloc(Reference);
        new null.<init>(v_2);
        Reference patchFile = v_2;
        patchFile.get();
        siteArr0[164].call(src, siteArr0[165].callGetProperty(FileType.class), new RFixPatchPackageTask$_copyTinkerPatchProduct_closure4(this, this, patchFile));
        if (ScriptBytecodeAdapter.compareEqual(patchFile.get(), null)) {
            siteArr0[166].callCurrent(this, "copyTinkerPatchProduct apk not exists!");
            return false;
        }
        else {
            Object destFolder = siteArr0[167].callConstructor(File.class, dest, destSubFolder);
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[168].call(destFolder)) ? 0 : 1 != 0) {
                siteArr0[169].call(destFolder);
            }
            siteArr0[170].callCurrent(this, new GStringImpl(new Object[]{src, destFolder}, new String[]{"copyTinkerPatchProduct src=", " destFolder=", ""}));
            Object destApkFile = siteArr0[171].callConstructor(File.class, destFolder, siteArr0[172].callGetProperty(RFixConstants.class));
            siteArr0[173].call(FileOperation.class, patchFile.get(), destApkFile);
            return true;
        }
    }

    private void generatePackageMeta(String patchType, Iterable<String> oldApks, File destDir) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        v_2 = alloc(Reference);
        new "".<init>(v_2);
        Reference patchId = v_2;
        siteArr0[174].call(oldApks, new RFixPatchPackageTask$_generatePackageMeta_closure5(this, this, patchId));
        siteArr0[175].callCurrent(this, new GStringImpl(new Object[]{patchId.get()}, new String[]{"generatePackageMeta patchId=", ""}));
        Properties properties;
        File assertFolder;
        Object outputStream;
        Object packageMetaFile;
        if (BytecodeInterface8.isOrigZ() && RFixPatchPackageTask.__$stMC && BytecodeInterface8.disabledStandardMetaClass()) {
            if (ScriptBytecodeAdapter.compareNotEqual(patchType, null) && ScriptBytecodeAdapter.compareNotEqual(patchId.get(), null) ? 0 : 1 != 0) {
                properties = (Properties)ScriptBytecodeAdapter.castToType(siteArr0[210].callConstructor(Properties.class), Properties.class);
                siteArr0[211].call(properties, siteArr0[212].callGetProperty(RFixConstants.class), patchType);
                siteArr0[213].call(properties, siteArr0[214].callGetProperty(RFixConstants.class), patchId.get());
                if (ScriptBytecodeAdapter.compareEqual(patchType, siteArr0[215].callGetProperty(RFixConstants.class))) {
                    siteArr0[216].call(properties, siteArr0[217].callGetProperty(RFixConstants.class), siteArr0[218].call(String.class, siteArr0[219].callGetProperty(siteArr0[220].callGroovyObjectGetProperty(this.configuration))));
                }
                siteArr0[221].call(properties, siteArr0[222].callGetProperty(RFixConstants.class), siteArr0[223].callCurrent(this, patchType, siteArr0[224].callGetProperty(RFixConstants.class)));
                siteArr0[225].call(properties, siteArr0[226].callGetProperty(RFixConstants.class), siteArr0[227].callCurrent(this, patchType, siteArr0[228].callGetProperty(RFixConstants.class)));
                siteArr0[229].call(properties, siteArr0[230].callGetProperty(RFixConstants.class), siteArr0[231].callCurrent(this, patchType, siteArr0[232].callGetProperty(RFixConstants.class)));
                assertFolder = (File)ScriptBytecodeAdapter.castToType(siteArr0[233].callConstructor(File.class, destDir, siteArr0[234].callGetProperty(RFixConstants.class)), File.class);
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[235].call(assertFolder)) ? 0 : 1 != 0) {
                    siteArr0[236].call(assertFolder);
                }
                outputStream = null;
                try {
                    packageMetaFile = siteArr0[237].callConstructor(File.class, assertFolder, siteArr0[238].callGetProperty(RFixConstants.class));
                    Object objectVar1 = siteArr0[239].callConstructor(BufferedOutputStream.class, siteArr0[240].callConstructor(FileOutputStream.class, packageMetaFile, Boolean.valueOf(false)));
                    OutputStream streamVar1 = (OutputStream)ScriptBytecodeAdapter.castToType(objectVar1, OutputStream.class);
                    siteArr0[241].call(properties, streamVar1, null);
                    siteArr0[242].call(PatchFileUtils.class, streamVar1);
                }
                finally {
                    Throwable throwableVar1 = v_234;
                    siteArr0[243].call(PatchFileUtils.class, streamVar1);
                    throw throwableVar1;
                }
            }
            else {
            }
        }
        else {
            if (ScriptBytecodeAdapter.compareNotEqual(patchType, null) && ScriptBytecodeAdapter.compareNotEqual(patchId.get(), null) ? 0 : 1 != 0) {
                properties = (Properties)ScriptBytecodeAdapter.castToType(siteArr0[176].callConstructor(Properties.class), Properties.class);
                siteArr0[177].call(properties, siteArr0[178].callGetProperty(RFixConstants.class), patchType);
                siteArr0[179].call(properties, siteArr0[180].callGetProperty(RFixConstants.class), patchId.get());
                if (ScriptBytecodeAdapter.compareEqual(patchType, siteArr0[181].callGetProperty(RFixConstants.class))) {
                    siteArr0[182].call(properties, siteArr0[183].callGetProperty(RFixConstants.class), siteArr0[184].call(String.class, siteArr0[185].callGetProperty(siteArr0[186].callGroovyObjectGetProperty(this.configuration))));
                }
                siteArr0[187].call(properties, siteArr0[188].callGetProperty(RFixConstants.class), siteArr0[189].callCurrent(this, patchType, siteArr0[190].callGetProperty(RFixConstants.class)));
                siteArr0[191].call(properties, siteArr0[192].callGetProperty(RFixConstants.class), siteArr0[193].callCurrent(this, patchType, siteArr0[194].callGetProperty(RFixConstants.class)));
                siteArr0[195].call(properties, siteArr0[196].callGetProperty(RFixConstants.class), siteArr0[197].callCurrent(this, patchType, siteArr0[198].callGetProperty(RFixConstants.class)));
                assertFolder = (File)ScriptBytecodeAdapter.castToType(siteArr0[199].callConstructor(File.class, destDir, siteArr0[200].callGetProperty(RFixConstants.class)), File.class);
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[201].call(assertFolder)) ? 0 : 1 != 0) {
                    siteArr0[202].call(assertFolder);
                }
                outputStream = null;
                try {
                    packageMetaFile = siteArr0[203].callConstructor(File.class, assertFolder, siteArr0[204].callGetProperty(RFixConstants.class));
                    Object object = siteArr0[205].callConstructor(BufferedOutputStream.class, siteArr0[206].callConstructor(FileOutputStream.class, packageMetaFile, Boolean.valueOf(false)));
                    OutputStream stream = (OutputStream)ScriptBytecodeAdapter.castToType(object, OutputStream.class);
                    siteArr0[207].call(properties, stream, null);
                    siteArr0[208].call(PatchFileUtils.class, stream);
                }
                finally {
                    Throwable throwable = v_444;
                    siteArr0[209].call(PatchFileUtils.class, stream);
                    throw throwable;
                }
            }
        }
    }

    private static String getPatchIdInApk(RFixPatchExtension configuration, String apk) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        Object apkFile = siteArr0[244].callConstructor(File.class, apk);
        Object parser = siteArr0[245].call(AndroidParser.class, apkFile);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[246].callGetProperty(siteArr0[247].callGroovyObjectGetProperty(configuration)))) {
            return (String)ShortTypeHandling.castToString(siteArr0[248].call(siteArr0[249].callGetProperty(parser), siteArr0[250].callGetProperty(RFixConstants.class)));
        }
        else {
            Object buildConfigClassName = siteArr0[251].call(siteArr0[252].callGetProperty(siteArr0[253].callGetProperty(parser)), ".BuildConfig");
            GStringImpl transformedClassName = new GStringImpl(new Object[]{siteArr0[254].call(buildConfigClassName, ".", "/")}, new String[]{"L", ";"});
            MultiDexContainer container = (MultiDexContainer)ScriptBytecodeAdapter.castToType(siteArr0[255].call(DexFileFactory.class, apkFile, null), MultiDexContainer.class);
            Object name = null;
            Iterator iterator = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[256].call(siteArr0[257].call(container)), Iterator.class);
            while (iterator.hasNext()) {
                String str0 = (String)ShortTypeHandling.castToString(iterator.next());
                if (DefaultTypeTransformation.booleanUnbox(siteArr0[258].call(str0, "classes")) ? 0 : 1 != 0) {
                    continue;;
                }
                else {
                    Object buildConfigClassDef = null;
                    Object defaultPatchIdField = null;
                    DexBackedDexFile dexFile = (DexBackedDexFile)ScriptBytecodeAdapter.castToType(siteArr0[259].call(siteArr0[260].call(container, str0)), DexBackedDexFile.class);
                    Set classes = (Set)ScriptBytecodeAdapter.castToType(siteArr0[261].call(dexFile), Set.class);
                    Object clazz = null;
                    Iterator iteratorVar1 = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[262].call(classes), Iterator.class);
                    while (iteratorVar1.hasNext()) {
                        DexBackedClassDef def = (DexBackedClassDef)ScriptBytecodeAdapter.castToType(iteratorVar1.next(), DexBackedClassDef.class);
                        ScriptBytecodeAdapter.compareEqual(siteArr0[263].call(def), transformedClassName);
                        DexBackedClassDef defVar1 = def;
                        break;;
                    }
                    if (ScriptBytecodeAdapter.compareEqual(defVar1, null)) {
                        continue;;
                    }
                    else {
                        Object field = null;
                        Iterator iteratorVar2 = (Iterator)ScriptBytecodeAdapter.castToType(siteArr0[264].call(siteArr0[265].call(defVar1)), Iterator.class);
                        while (iteratorVar2.hasNext()) {
                            field = (DexBackedField)ScriptBytecodeAdapter.castToType(iteratorVar2.next(), DexBackedField.class);
                            ScriptBytecodeAdapter.compareEqual(siteArr0[266].call(field), siteArr0[267].callGetProperty(RFixConstants.class));
                            DexBackedField fieldVar1 = field;
                            break;;
                        }
                        if (ScriptBytecodeAdapter.compareEqual(fieldVar1, null)) {
                            throw (Throwable)siteArr0[268].callConstructor(GradleException.class, new GStringImpl(new Object[]{siteArr0[269].callGetProperty(RFixConstants.class)}, new String[]{"find field of '", "' fail!"}));
                        }
                        else {
                            return (String)ShortTypeHandling.castToString(siteArr0[270].call(siteArr0[271].call(siteArr0[272].callGetProperty(fieldVar1)), """, ""));
                        }
                    }
                }
            }
            return (String)ShortTypeHandling.castToString(null);
        }
    }

    private String calcAssertValue(String patchType, String assertKey) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        int var_4_0 = 0;
        boolean bool0;
        if (ScriptBytecodeAdapter.compareEqual(patchType, siteArr0[273].callGetProperty(RFixConstants.class))) {
            if (ScriptBytecodeAdapter.compareEqual(assertKey, siteArr0[274].callGetProperty(RFixConstants.class))) {
                Object object = siteArr0[275].call(siteArr0[276].callGetProperty(siteArr0[277].callGroovyObjectGetProperty(this.configuration)), siteArr0[278].callGetProperty(siteArr0[279].callGroovyObjectGetProperty(this.configuration)));
                bool0 = DefaultTypeTransformation.booleanUnbox(object);
            }
            else if (ScriptBytecodeAdapter.compareEqual(assertKey, siteArr0[280].callGetProperty(RFixConstants.class))) {
                Object objectVar1 = siteArr0[281].call(siteArr0[282].callGetProperty(siteArr0[283].callGroovyObjectGetProperty(this.configuration)), siteArr0[284].callGetProperty(siteArr0[285].callGroovyObjectGetProperty(this.configuration)));
                bool0 = DefaultTypeTransformation.booleanUnbox(objectVar1);
            }
            else if (ScriptBytecodeAdapter.compareEqual(assertKey, siteArr0[286].callGetProperty(RFixConstants.class))) {
                Object objectVar2 = siteArr0[287].call(siteArr0[288].callGetProperty(siteArr0[289].callGroovyObjectGetProperty(this.configuration)), siteArr0[290].callGetProperty(siteArr0[291].callGroovyObjectGetProperty(this.configuration)));
                bool0 = DefaultTypeTransformation.booleanUnbox(objectVar2);
            }
        }
        else if (ScriptBytecodeAdapter.compareEqual(patchType, siteArr0[292].callGetProperty(RFixConstants.class))) {
            if (ScriptBytecodeAdapter.compareEqual(assertKey, siteArr0[293].callGetProperty(RFixConstants.class))) {
                Object objectVar3 = siteArr0[294].callGetProperty(siteArr0[295].callGroovyObjectGetProperty(this.configuration));
                bool0 = DefaultTypeTransformation.booleanUnbox(objectVar3);
            }
            else if (ScriptBytecodeAdapter.compareEqual(assertKey, siteArr0[296].callGetProperty(RFixConstants.class))) {
                Object objectVar4 = siteArr0[297].callGetProperty(siteArr0[298].callGroovyObjectGetProperty(this.configuration));
                bool0 = DefaultTypeTransformation.booleanUnbox(objectVar4);
            }
            else if (ScriptBytecodeAdapter.compareEqual(assertKey, siteArr0[299].callGetProperty(RFixConstants.class))) {
                Object objectVar5 = siteArr0[300].callGetProperty(siteArr0[301].callGroovyObjectGetProperty(this.configuration));
                bool0 = DefaultTypeTransformation.booleanUnbox(objectVar5);
            }
        }
        else if (ScriptBytecodeAdapter.compareEqual(patchType, siteArr0[302].callGetProperty(RFixConstants.class))) {
            if (ScriptBytecodeAdapter.compareEqual(assertKey, siteArr0[303].callGetProperty(RFixConstants.class))) {
                Object objectVar6 = siteArr0[304].callGetProperty(siteArr0[305].callGroovyObjectGetProperty(this.configuration));
                bool0 = DefaultTypeTransformation.booleanUnbox(objectVar6);
            }
            else if (ScriptBytecodeAdapter.compareEqual(assertKey, siteArr0[306].callGetProperty(RFixConstants.class))) {
                Object objectVar7 = siteArr0[307].call(siteArr0[308].callGetProperty(siteArr0[309].callGroovyObjectGetProperty(this.configuration)), siteArr0[310].callGetProperty(siteArr0[311].callGroovyObjectGetProperty(this.configuration)));
                bool0 = DefaultTypeTransformation.booleanUnbox(objectVar7);
            }
            else {
                int i0 = 0;
            }
        }
        return (String)ShortTypeHandling.castToString(siteArr0[312].call(String.class, Boolean.valueOf(i0)));
    }

    protected void generatePatch(File src, File apkFile) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        siteArr0[313].callCurrent(this, new GStringImpl(new Object[]{src, apkFile}, new String[]{"generatePatch src=", " apkFile=", ""}));
        siteArr0[314].call(FileOperation.class, src, apkFile, null);
        if (DefaultTypeTransformation.booleanUnbox(siteArr0[315].call(apkFile)) ? 0 : 1 != 0) {
            throw (Throwable)siteArr0[316].callConstructor(RuntimeException.class, "generate patch fail!");
        }
        else {
        }
    }

    protected void copyPatchSummary(int index, File src) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(this.patchType, siteArr0[317].callGetProperty(RFixConstants.class))) {
            Object srcSummaryFile = siteArr0[318].callConstructor(File.class, src, siteArr0[319].callGetProperty(RFixConstants.class));
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[320].call(srcSummaryFile))) {
                GStringImpl patchSuffix = new GStringImpl(new Object[]{Integer.valueOf(index)}, new String[]{"-", ""});
                Object destSummaryName = siteArr0[321].call(String.class, siteArr0[322].callGetProperty(RFixConstants.class), this.patchType, patchSuffix);
                Object destSummaryFile = siteArr0[323].callConstructor(File.class, this.outputFolder, destSummaryName);
                siteArr0[324].call(FileOperation.class, srcSummaryFile, destSummaryFile);
            }
        }
    }

    private void printLog(Object log) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        siteArr0[325].callCurrent(this, new GStringImpl(new Object[]{RFixPatchPackageTask.TAG, log}, new String[]{"", ": ", ""}));
    }

    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() != RFixPatchPackageTask.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        else {
            if (RFixPatchPackageTask.$staticClassInfo == null) {
                infoVar1 = ClassInfo.getClassInfo(this.getClass());
                RFixPatchPackageTask.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
            }
            return RFixPatchPackageTask.$staticClassInfo.getMetaClass();
        }
    }

    public /* synthetic */ Object this$dist$invoke$3(String name, Object args) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(RFixPatchPackageTask.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})), ScriptBytecodeAdapter.despreadList(new Object[]{}, new Object[]{args}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$3(String name, Object value) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty(value, RFixPatchPackageTask.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
    }

    public /* synthetic */ Object this$dist$get$3(String name) {
        CallSite[] siteArr0 = RFixPatchPackageTask.$getCallSiteArray();
        return ScriptBytecodeAdapter.getGroovyObjectProperty(RFixPatchPackageTask.class, this, (String)ShortTypeHandling.castToString(new GStringImpl(new Object[]{name}, new String[]{"", ""})));
    }

    @Generated
    @Internal
    public /* synthetic */ MetaClass getMetaClass() {
        if (this.metaClass != null) {
            return this.metaClass;
        }
        else {
            this.metaClass = this.$getStaticMetaClass();
            return this.metaClass;
        }
    }

    @Generated
    @Internal
    public /* synthetic */ void setMetaClass(MetaClass class) {
        this.metaClass = class;
    }

    @Generated
    @Internal
    public /* synthetic */ Object invokeMethod(String str0, Object object) {
        return this.getMetaClass().invokeMethod(this, str0, object);
    }

    @Generated
    @Internal
    public /* synthetic */ Object getProperty(String str0) {
        return this.getMetaClass().getProperty(this, str0);
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
        stringArr0[0] = "GROUP";
        stringArr0[1] = "RFixPatch";
        stringArr0[2] = "extensions";
        stringArr0[3] = "project";
        stringArr0[4] = "<$constructor$>";
        stringArr0[5] = "clear";
        stringArr0[6] = "iterator";
        stringArr0[7] = "add";
        stringArr0[8] = "<$constructor$>";
        stringArr0[9] = "RFIX_OUTPUT_DIR";
        stringArr0[10] = "outputFolder";
        stringArr0[11] = "isNullOrNil";
        stringArr0[12] = "dirName";
        stringArr0[13] = "parentFile";
        stringArr0[14] = "parentFile";
        stringArr0[15] = "parentFile";
        stringArr0[16] = "outputFile";
        stringArr0[17] = "<$constructor$>";
        stringArr0[18] = "PATCH_DIR";
        stringArr0[19] = "absolutePath";
        stringArr0[20] = "dirName";
        stringArr0[21] = "<$constructor$>";
        stringArr0[22] = "printLog";
        stringArr0[23] = "printLog";
        stringArr0[24] = "plus";
        stringArr0[25] = "checkParams";
        stringArr0[26] = "prepare";
        stringArr0[27] = "size";
        stringArr0[28] = "packageSeparatePatch";
        stringArr0[29] = "packageUniversalPatch";
        stringArr0[30] = "size";
        stringArr0[31] = "eachWithIndex";
        stringArr0[32] = "printLog";
        stringArr0[33] = "printLog";
        stringArr0[34] = "isEmpty";
        stringArr0[35] = "<$constructor$>";
        stringArr0[36] = "isEmpty";
        stringArr0[37] = "<$constructor$>";
        stringArr0[38] = "deleteFile";
        stringArr0[39] = "mkdirs";
        stringArr0[40] = "oldApks";
        stringArr0[41] = "packageOnePatch";
        stringArr0[42] = "eachWithIndex";
        stringArr0[43] = "printLog";
        stringArr0[44] = "RFIX_OUTPUT_DIR";
        stringArr0[45] = "<$constructor$>";
        stringArr0[46] = "buildDir";
        stringArr0[47] = "project";
        stringArr0[48] = "RFIX_TEMP_DIR";
        stringArr0[49] = "deleteFile";
        stringArr0[50] = "mkdirs";
        stringArr0[51] = "eachWithIndex";
        stringArr0[52] = "generatePackageMeta";
        stringArr0[53] = "format";
        stringArr0[54] = "APK_RFIX_UNSIGNED_NAME";
        stringArr0[55] = "<$constructor$>";
        stringArr0[56] = "generatePatch";
        stringArr0[57] = "<$constructor$>";
        stringArr0[58] = "copyFileUsingStream";
        stringArr0[59] = "format";
        stringArr0[60] = "APK_RFIX_SIGNED_NAME";
        stringArr0[61] = "<$constructor$>";
        stringArr0[62] = "<$constructor$>";
        stringArr0[63] = "signPatch";
        stringArr0[64] = "printLog";
        stringArr0[65] = "printLog";
        stringArr0[66] = "PATCH_TYPE_QFIX";
        stringArr0[67] = "plus";
        stringArr0[68] = "FOLDER_QFIX";
        stringArr0[69] = "copyQFixPatchProduct";
        stringArr0[70] = "PATCH_TYPE_REDIRECT";
        stringArr0[71] = "plus";
        stringArr0[72] = "FOLDER_REDIRECT";
        stringArr0[73] = "copyRedirectPatchProduct";
        stringArr0[74] = "PATCH_TYPE_TINKER";
        stringArr0[75] = "plus";
        stringArr0[76] = "FOLDER_TINKER";
        stringArr0[77] = "copyTinkerPatchProduct";
        stringArr0[78] = "dexFixEnable";
        stringArr0[79] = "qfixExtension";
        stringArr0[80] = "copyQFixDexPatch";
        stringArr0[81] = "resFixEnable";
        stringArr0[82] = "qfixExtension";
        stringArr0[83] = "copyQFixResPatch";
        stringArr0[84] = "libFixEnable";
        stringArr0[85] = "qfixExtension";
        stringArr0[86] = "copyQFixSoPatch";
        stringArr0[87] = "<$constructor$>";
        stringArr0[88] = "DEX_FILE_NAME";
        stringArr0[89] = "<$constructor$>";
        stringArr0[90] = "DEX_CONFIG_NAME";
        stringArr0[91] = "exists";
        stringArr0[92] = "exists";
        stringArr0[93] = "printLog";
        stringArr0[94] = "exists";
        stringArr0[95] = "exists";
        stringArr0[96] = "printLog";
        stringArr0[97] = "<$constructor$>";
        stringArr0[98] = "exists";
        stringArr0[99] = "mkdirs";
        stringArr0[100] = "printLog";
        stringArr0[101] = "<$constructor$>";
        stringArr0[102] = "DEX_FILE_NAME";
        stringArr0[103] = "copyFileUsingStream";
        stringArr0[104] = "<$constructor$>";
        stringArr0[105] = "DEX_CONFIG_NAME";
        stringArr0[106] = "copyFileUsingStream";
        stringArr0[107] = "<$constructor$>";
        stringArr0[108] = "RES_FILE_NAME";
        stringArr0[109] = "<$constructor$>";
        stringArr0[110] = "RES_CONFIG_NAME";
        stringArr0[111] = "exists";
        stringArr0[112] = "exists";
        stringArr0[113] = "printLog";
        stringArr0[114] = "exists";
        stringArr0[115] = "exists";
        stringArr0[116] = "printLog";
        stringArr0[117] = "<$constructor$>";
        stringArr0[118] = "exists";
        stringArr0[119] = "mkdirs";
        stringArr0[120] = "printLog";
        stringArr0[121] = "<$constructor$>";
        stringArr0[122] = "RES_FILE_NAME";
        stringArr0[123] = "copyFileUsingStream";
        stringArr0[124] = "<$constructor$>";
        stringArr0[125] = "RES_CONFIG_NAME";
        stringArr0[126] = "copyFileUsingStream";
        stringArr0[127] = "<$constructor$>";
        stringArr0[128] = "SO_PATCH_DIR";
        stringArr0[129] = "exists";
        stringArr0[130] = "printLog";
        stringArr0[131] = "toPath";
        stringArr0[132] = "toPath";
        stringArr0[133] = "<$constructor$>";
        stringArr0[134] = "plus";
        stringArr0[135] = "plus";
        stringArr0[136] = "SO_PATCH_DIR";
        stringArr0[137] = "printLog";
        stringArr0[138] = "forEach";
        stringArr0[139] = "walk";
        stringArr0[140] = "copyRedirectDexPatch";
        stringArr0[141] = "libFixEnable";
        stringArr0[142] = "redirectConfig";
        stringArr0[143] = "copyQFixSoPatch";
        stringArr0[144] = "<$constructor$>";
        stringArr0[145] = "DEX_FILE_NAME";
        stringArr0[146] = "<$constructor$>";
        stringArr0[147] = "DEX_CONFIG_NAME";
        stringArr0[148] = "exists";
        stringArr0[149] = "exists";
        stringArr0[150] = "printLog";
        stringArr0[151] = "exists";
        stringArr0[152] = "exists";
        stringArr0[153] = "printLog";
        stringArr0[154] = "<$constructor$>";
        stringArr0[155] = "exists";
        stringArr0[156] = "mkdirs";
        stringArr0[157] = "printLog";
        stringArr0[158] = "<$constructor$>";
        stringArr0[159] = "DEX_FILE_NAME";
        stringArr0[160] = "copyFileUsingStream";
        stringArr0[161] = "<$constructor$>";
        stringArr0[162] = "DEX_CONFIG_NAME";
        stringArr0[163] = "copyFileUsingStream";
        stringArr0[164] = "eachFile";
        stringArr0[165] = "FILES";
        stringArr0[166] = "printLog";
        stringArr0[167] = "<$constructor$>";
        stringArr0[168] = "exists";
        stringArr0[169] = "mkdirs";
        stringArr0[170] = "printLog";
        stringArr0[171] = "<$constructor$>";
        stringArr0[172] = "APK_PATCH_NAME";
        stringArr0[173] = "copyFileUsingStream";
        stringArr0[174] = "eachWithIndex";
        stringArr0[175] = "printLog";
        stringArr0[176] = "<$constructor$>";
        stringArr0[177] = "put";
        stringArr0[178] = "PATCH_TYPE";
        stringArr0[179] = "put";
        stringArr0[180] = "PATCH_ID";
        stringArr0[181] = "PATCH_TYPE_REDIRECT";
        stringArr0[182] = "put";
        stringArr0[183] = "EFFECT_IMMEDIATE";
        stringArr0[184] = "valueOf";
        stringArr0[185] = "effectImmediate";
        stringArr0[186] = "redirectConfig";
        stringArr0[187] = "put";
        stringArr0[188] = "ENABLE_ASSERT_DEX";
        stringArr0[189] = "calcAssertValue";
        stringArr0[190] = "ENABLE_ASSERT_DEX";
        stringArr0[191] = "put";
        stringArr0[192] = "ENABLE_ASSERT_LIB";
        stringArr0[193] = "calcAssertValue";
        stringArr0[194] = "ENABLE_ASSERT_LIB";
        stringArr0[195] = "put";
        stringArr0[196] = "ENABLE_ASSERT_RES";
        stringArr0[197] = "calcAssertValue";
        stringArr0[198] = "ENABLE_ASSERT_RES";
        stringArr0[199] = "<$constructor$>";
        stringArr0[200] = "FOLDER_ASSETS";
        stringArr0[201] = "exists";
        stringArr0[202] = "mkdirs";
        stringArr0[203] = "<$constructor$>";
        stringArr0[204] = "META_PACKAGE_NAME";
        stringArr0[205] = "<$constructor$>";
        stringArr0[206] = "<$constructor$>";
        stringArr0[207] = "store";
        stringArr0[208] = "closeQuietly";
        stringArr0[209] = "closeQuietly";
        stringArr0[210] = "<$constructor$>";
        stringArr0[211] = "put";
        stringArr0[212] = "PATCH_TYPE";
        stringArr0[213] = "put";
        stringArr0[214] = "PATCH_ID";
        stringArr0[215] = "PATCH_TYPE_REDIRECT";
        stringArr0[216] = "put";
        stringArr0[217] = "EFFECT_IMMEDIATE";
        stringArr0[218] = "valueOf";
        stringArr0[219] = "effectImmediate";
        stringArr0[220] = "redirectConfig";
        stringArr0[221] = "put";
        stringArr0[222] = "ENABLE_ASSERT_DEX";
        stringArr0[223] = "calcAssertValue";
        stringArr0[224] = "ENABLE_ASSERT_DEX";
        stringArr0[225] = "put";
        stringArr0[226] = "ENABLE_ASSERT_LIB";
        stringArr0[227] = "calcAssertValue";
        stringArr0[228] = "ENABLE_ASSERT_LIB";
        stringArr0[229] = "put";
        stringArr0[230] = "ENABLE_ASSERT_RES";
        stringArr0[231] = "calcAssertValue";
        stringArr0[232] = "ENABLE_ASSERT_RES";
        stringArr0[233] = "<$constructor$>";
        stringArr0[234] = "FOLDER_ASSETS";
        stringArr0[235] = "exists";
        stringArr0[236] = "mkdirs";
        stringArr0[237] = "<$constructor$>";
        stringArr0[238] = "META_PACKAGE_NAME";
        stringArr0[239] = "<$constructor$>";
        stringArr0[240] = "<$constructor$>";
        stringArr0[241] = "store";
        stringArr0[242] = "closeQuietly";
        stringArr0[243] = "closeQuietly";
        stringArr0[244] = "<$constructor$>";
        stringArr0[245] = "getAndroidManifest";
        stringArr0[246] = "enablePatchIdToManifest";
        stringArr0[247] = "buildConfig";
        stringArr0[248] = "get";
        stringArr0[249] = "metaDatas";
        stringArr0[250] = "PATCH_ID";
        stringArr0[251] = "plus";
        stringArr0[252] = "packageName";
        stringArr0[253] = "apkMeta";
        stringArr0[254] = "replace";
        stringArr0[255] = "loadDexContainer";
        stringArr0[256] = "iterator";
        stringArr0[257] = "getDexEntryNames";
        stringArr0[258] = "startsWith";
        stringArr0[259] = "getDexFile";
        stringArr0[260] = "getEntry";
        stringArr0[261] = "getClasses";
        stringArr0[262] = "iterator";
        stringArr0[263] = "getType";
        stringArr0[264] = "iterator";
        stringArr0[265] = "getStaticFields";
        stringArr0[266] = "getName";
        stringArr0[267] = "DEFAULT_PATCH_ID";
        stringArr0[268] = "<$constructor$>";
        stringArr0[269] = "DEFAULT_PATCH_ID";
        stringArr0[270] = "replace";
        stringArr0[271] = "toString";
        stringArr0[272] = "initialValue";
        stringArr0[273] = "PATCH_TYPE_QFIX";
        stringArr0[274] = "ENABLE_ASSERT_DEX";
        stringArr0[275] = "and";
        stringArr0[276] = "enableAssert";
        stringArr0[277] = "dex";
        stringArr0[278] = "dexFixEnable";
        stringArr0[279] = "qfixExtension";
        stringArr0[280] = "ENABLE_ASSERT_LIB";
        stringArr0[281] = "and";
        stringArr0[282] = "enableAssert";
        stringArr0[283] = "lib";
        stringArr0[284] = "libFixEnable";
        stringArr0[285] = "qfixExtension";
        stringArr0[286] = "ENABLE_ASSERT_RES";
        stringArr0[287] = "and";
        stringArr0[288] = "enableAssert";
        stringArr0[289] = "res";
        stringArr0[290] = "resFixEnable";
        stringArr0[291] = "qfixExtension";
        stringArr0[292] = "PATCH_TYPE_TINKER";
        stringArr0[293] = "ENABLE_ASSERT_DEX";
        stringArr0[294] = "enableAssert";
        stringArr0[295] = "dex";
        stringArr0[296] = "ENABLE_ASSERT_LIB";
        stringArr0[297] = "enableAssert";
        stringArr0[298] = "lib";
        stringArr0[299] = "ENABLE_ASSERT_RES";
        stringArr0[300] = "enableAssert";
        stringArr0[301] = "res";
        stringArr0[302] = "PATCH_TYPE_REDIRECT";
        stringArr0[303] = "ENABLE_ASSERT_DEX";
        stringArr0[304] = "enableAssert";
        stringArr0[305] = "dex";
        stringArr0[306] = "ENABLE_ASSERT_LIB";
        stringArr0[307] = "and";
        stringArr0[308] = "enableAssert";
        stringArr0[309] = "lib";
        stringArr0[310] = "libFixEnable";
        stringArr0[311] = "redirectConfig";
        stringArr0[312] = "valueOf";
        stringArr0[313] = "printLog";
        stringArr0[314] = "zipInputDir";
        stringArr0[315] = "exists";
        stringArr0[316] = "<$constructor$>";
        stringArr0[317] = "PATCH_TYPE_REDIRECT";
        stringArr0[318] = "<$constructor$>";
        stringArr0[319] = "PATCH_SUMMARY_NAME";
        stringArr0[320] = "exists";
        stringArr0[321] = "format";
        stringArr0[322] = "PATCH_SUMMARY_OUTPUT_NAME";
        stringArr0[323] = "<$constructor$>";
        stringArr0[324] = "copyFileUsingStream";
        stringArr0[325] = "println";
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String str0 = new String[]{};
        RFixPatchPackageTask.$createCallSiteArray_1(str0);
        return new CallSiteArray(RFixPatchPackageTask.class, str0);
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray array = RFixPatchPackageTask.$callSiteArray != null ? RFixPatchPackageTask.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask.$callSiteArray.get();
        RFixPatchPackageTask.$callSiteArray = new SoftReference(array);
        return var_0_0.array;
    }

    // class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask$_generatePackageMeta_closure5
    public final class RFixPatchPackageTask$_generatePackageMeta_closure5 implements GeneratedClosure {
        private synthetic Reference patchId;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPackageTask$_generatePackageMeta_closure5(Object _outerInstance, Object _thisObject, Reference patchId) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_generatePackageMeta_closure5.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            patchId.patchId = this;
        }

        public Object doCall(Object oldApk, Object index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_generatePackageMeta_closure5.$getCallSiteArray();
            Object patchIdInApk = siteArr0[0].callStatic(RFixPatchPackageTask.class, siteArr0[1].callGroovyObjectGetProperty(this), oldApk);
            v_24 = siteArr0[2].call(this.patchId.get(), ScriptBytecodeAdapter.compareEqual(index, Integer.valueOf(0)) ? "," : "");
            siteArr0[2].call(this.patchId.get(), ScriptBytecodeAdapter.compareEqual(index, Integer.valueOf(0)) ? "," : "").set(this.patchId);
            siteArr0[3].call(this.patchId.get(), patchIdInApk).set(this.patchId);
            return siteArr0[3].call(this.patchId.get(), patchIdInApk);
        }

        public Object call(Object oldApk, Object index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_generatePackageMeta_closure5.$getCallSiteArray();
            return siteArr0[4].callCurrent(this, oldApk, index);
        }

        @Generated
        public Object getPatchId() {
            CallSite[] siteArr0 = RFixPatchPackageTask$_generatePackageMeta_closure5.$getCallSiteArray();
            return this.patchId.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPackageTask$_generatePackageMeta_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPackageTask$_generatePackageMeta_closure5.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPackageTask$_generatePackageMeta_closure5.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPackageTask$_generatePackageMeta_closure5.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "getPatchIdInApk";
            stringArr0[1] = "configuration";
            stringArr0[2] = "plus";
            stringArr0[3] = "plus";
            stringArr0[4] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPackageTask$_generatePackageMeta_closure5.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPackageTask$_generatePackageMeta_closure5.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPackageTask$_generatePackageMeta_closure5.$callSiteArray != null ? RFixPatchPackageTask$_generatePackageMeta_closure5.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask$_generatePackageMeta_closure5.$callSiteArray.get();
            RFixPatchPackageTask$_generatePackageMeta_closure5.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask$_generatePackageMeta_closure5
    public final class RFixPatchPackageTask$_generatePackageMeta_closure5 implements GeneratedClosure {
        private synthetic Reference patchId;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPackageTask$_generatePackageMeta_closure5(Object _outerInstance, Object _thisObject, Reference patchId) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_generatePackageMeta_closure5.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            patchId.patchId = this;
        }

        public Object doCall(Object oldApk, Object index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_generatePackageMeta_closure5.$getCallSiteArray();
            Object patchIdInApk = siteArr0[0].callStatic(RFixPatchPackageTask.class, siteArr0[1].callGroovyObjectGetProperty(this), oldApk);
            v_24 = siteArr0[2].call(this.patchId.get(), ScriptBytecodeAdapter.compareEqual(index, Integer.valueOf(0)) ? "," : "");
            siteArr0[2].call(this.patchId.get(), ScriptBytecodeAdapter.compareEqual(index, Integer.valueOf(0)) ? "," : "").set(this.patchId);
            siteArr0[3].call(this.patchId.get(), patchIdInApk).set(this.patchId);
            return siteArr0[3].call(this.patchId.get(), patchIdInApk);
        }

        public Object call(Object oldApk, Object index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_generatePackageMeta_closure5.$getCallSiteArray();
            return siteArr0[4].callCurrent(this, oldApk, index);
        }

        @Generated
        public Object getPatchId() {
            CallSite[] siteArr0 = RFixPatchPackageTask$_generatePackageMeta_closure5.$getCallSiteArray();
            return this.patchId.get();
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPackageTask$_generatePackageMeta_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPackageTask$_generatePackageMeta_closure5.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPackageTask$_generatePackageMeta_closure5.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPackageTask$_generatePackageMeta_closure5.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "getPatchIdInApk";
            stringArr0[1] = "configuration";
            stringArr0[2] = "plus";
            stringArr0[3] = "plus";
            stringArr0[4] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPackageTask$_generatePackageMeta_closure5.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPackageTask$_generatePackageMeta_closure5.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPackageTask$_generatePackageMeta_closure5.$callSiteArray != null ? RFixPatchPackageTask$_generatePackageMeta_closure5.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask$_generatePackageMeta_closure5.$callSiteArray.get();
            RFixPatchPackageTask$_generatePackageMeta_closure5.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask$_packageSeparatePatch_closure2
    public final class RFixPatchPackageTask$_packageSeparatePatch_closure2 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPackageTask$_packageSeparatePatch_closure2(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageSeparatePatch_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object input, Object index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageSeparatePatch_closure2.$getCallSiteArray();
            GStringImpl patchSuffix = new GStringImpl(new Object[]{index}, new String[]{"-", ""});
            List inputs = ScriptBytecodeAdapter.createList(new Object[]{input});
            List oldApks = ScriptBytecodeAdapter.createList(new Object[]{siteArr0[0].call(siteArr0[1].callGroovyObjectGetProperty(siteArr0[2].callGroovyObjectGetProperty(this)), index)});
            return siteArr0[3].callCurrent(this, patchSuffix, inputs, oldApks);
        }

        public Object call(Object input, Object index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageSeparatePatch_closure2.$getCallSiteArray();
            return siteArr0[4].callCurrent(this, input, index);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPackageTask$_packageSeparatePatch_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPackageTask$_packageSeparatePatch_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPackageTask$_packageSeparatePatch_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPackageTask$_packageSeparatePatch_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "getAt";
            stringArr0[1] = "oldApks";
            stringArr0[2] = "configuration";
            stringArr0[3] = "packageOnePatch";
            stringArr0[4] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPackageTask$_packageSeparatePatch_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPackageTask$_packageSeparatePatch_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPackageTask$_packageSeparatePatch_closure2.$callSiteArray != null ? RFixPatchPackageTask$_packageSeparatePatch_closure2.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask$_packageSeparatePatch_closure2.$callSiteArray.get();
            RFixPatchPackageTask$_packageSeparatePatch_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask$_packageSeparatePatch_closure2
    public final class RFixPatchPackageTask$_packageSeparatePatch_closure2 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPackageTask$_packageSeparatePatch_closure2(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageSeparatePatch_closure2.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(Object input, Object index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageSeparatePatch_closure2.$getCallSiteArray();
            GStringImpl patchSuffix = new GStringImpl(new Object[]{index}, new String[]{"-", ""});
            List inputs = ScriptBytecodeAdapter.createList(new Object[]{input});
            List oldApks = ScriptBytecodeAdapter.createList(new Object[]{siteArr0[0].call(siteArr0[1].callGroovyObjectGetProperty(siteArr0[2].callGroovyObjectGetProperty(this)), index)});
            return siteArr0[3].callCurrent(this, patchSuffix, inputs, oldApks);
        }

        public Object call(Object input, Object index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageSeparatePatch_closure2.$getCallSiteArray();
            return siteArr0[4].callCurrent(this, input, index);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPackageTask$_packageSeparatePatch_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPackageTask$_packageSeparatePatch_closure2.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPackageTask$_packageSeparatePatch_closure2.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPackageTask$_packageSeparatePatch_closure2.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "getAt";
            stringArr0[1] = "oldApks";
            stringArr0[2] = "configuration";
            stringArr0[3] = "packageOnePatch";
            stringArr0[4] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPackageTask$_packageSeparatePatch_closure2.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPackageTask$_packageSeparatePatch_closure2.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPackageTask$_packageSeparatePatch_closure2.$callSiteArray != null ? RFixPatchPackageTask$_packageSeparatePatch_closure2.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask$_packageSeparatePatch_closure2.$callSiteArray.get();
            RFixPatchPackageTask$_packageSeparatePatch_closure2.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask$_packageOnePatch_closure3
    public final class RFixPatchPackageTask$_packageOnePatch_closure3 implements GeneratedClosure {
        private synthetic Reference tempFolder;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPackageTask$_packageOnePatch_closure3(Object _outerInstance, Object _thisObject, Reference tempFolder) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageOnePatch_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            tempFolder.tempFolder = this;
        }

        public Object doCall(File src, int index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageOnePatch_closure3.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].callCurrent(this, Integer.valueOf(index), src, this.tempFolder.get())) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[1].callConstructor(RuntimeException.class, "copy patch products fail!");
            }
            else {
                return null;
            }
        }

        public Object call(File src, int index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageOnePatch_closure3.$getCallSiteArray();
            return siteArr0[2].callCurrent(this, src, Integer.valueOf(index));
        }

        @Generated
        public File getTempFolder() {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageOnePatch_closure3.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.tempFolder.get(), File.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPackageTask$_packageOnePatch_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPackageTask$_packageOnePatch_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPackageTask$_packageOnePatch_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPackageTask$_packageOnePatch_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "copyPatchProduct";
            stringArr0[1] = "<$constructor$>";
            stringArr0[2] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPackageTask$_packageOnePatch_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPackageTask$_packageOnePatch_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPackageTask$_packageOnePatch_closure3.$callSiteArray != null ? RFixPatchPackageTask$_packageOnePatch_closure3.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask$_packageOnePatch_closure3.$callSiteArray.get();
            RFixPatchPackageTask$_packageOnePatch_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask$_packageOnePatch_closure3
    public final class RFixPatchPackageTask$_packageOnePatch_closure3 implements GeneratedClosure {
        private synthetic Reference tempFolder;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPackageTask$_packageOnePatch_closure3(Object _outerInstance, Object _thisObject, Reference tempFolder) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageOnePatch_closure3.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            tempFolder.tempFolder = this;
        }

        public Object doCall(File src, int index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageOnePatch_closure3.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].callCurrent(this, Integer.valueOf(index), src, this.tempFolder.get())) ? 0 : 1 != 0) {
                throw (Throwable)siteArr0[1].callConstructor(RuntimeException.class, "copy patch products fail!");
            }
            else {
                return null;
            }
        }

        public Object call(File src, int index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageOnePatch_closure3.$getCallSiteArray();
            return siteArr0[2].callCurrent(this, src, Integer.valueOf(index));
        }

        @Generated
        public File getTempFolder() {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packageOnePatch_closure3.$getCallSiteArray();
            return (File)ScriptBytecodeAdapter.castToType(this.tempFolder.get(), File.class);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPackageTask$_packageOnePatch_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPackageTask$_packageOnePatch_closure3.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPackageTask$_packageOnePatch_closure3.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPackageTask$_packageOnePatch_closure3.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "copyPatchProduct";
            stringArr0[1] = "<$constructor$>";
            stringArr0[2] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPackageTask$_packageOnePatch_closure3.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPackageTask$_packageOnePatch_closure3.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPackageTask$_packageOnePatch_closure3.$callSiteArray != null ? RFixPatchPackageTask$_packageOnePatch_closure3.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask$_packageOnePatch_closure3.$callSiteArray.get();
            RFixPatchPackageTask$_packageOnePatch_closure3.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask$_copyTinkerPatchProduct_closure4
    public final class RFixPatchPackageTask$_copyTinkerPatchProduct_closure4 implements GeneratedClosure {
        private synthetic Reference patchFile;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPackageTask$_copyTinkerPatchProduct_closure4(Object _outerInstance, Object _thisObject, Reference patchFile) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            patchFile.patchFile = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(it), "unsigned.apk"))) {
                it.set(this.patchFile);
                return it;
            }
            else {
                return null;
            }
        }

        @Generated
        public Object getPatchFile() {
            CallSite[] siteArr0 = RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$getCallSiteArray();
            return this.patchFile.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$callSiteArray != null ? RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$callSiteArray.get();
            RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask$_copyTinkerPatchProduct_closure4
    public final class RFixPatchPackageTask$_copyTinkerPatchProduct_closure4 implements GeneratedClosure {
        private synthetic Reference patchFile;
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPackageTask$_copyTinkerPatchProduct_closure4(Object _outerInstance, Object _thisObject, Reference patchFile) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$getCallSiteArray();
            super(_outerInstance, _thisObject);
            patchFile.patchFile = this;
        }

        public Object doCall(Object it) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox(siteArr0[0].call(siteArr0[1].callGetProperty(it), "unsigned.apk"))) {
                it.set(this.patchFile);
                return it;
            }
            else {
                return null;
            }
        }

        @Generated
        public Object getPatchFile() {
            CallSite[] siteArr0 = RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$getCallSiteArray();
            return this.patchFile.get();
        }

        @Generated
        public Object doCall() {
            CallSite[] siteArr0 = RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$getCallSiteArray();
            return this.doCall(null);
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "endsWith";
            stringArr0[1] = "name";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$callSiteArray != null ? RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$callSiteArray.get();
            RFixPatchPackageTask$_copyTinkerPatchProduct_closure4.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask$_packagePatch_closure1
    public final class RFixPatchPackageTask$_packagePatch_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPackageTask$_packagePatch_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packagePatch_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(File src, int index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packagePatch_closure1.$getCallSiteArray();
            return siteArr0[0].callCurrent(this, Integer.valueOf(index), src);
        }

        public Object call(File src, int index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packagePatch_closure1.$getCallSiteArray();
            return siteArr0[1].callCurrent(this, src, Integer.valueOf(index));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPackageTask$_packagePatch_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPackageTask$_packagePatch_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPackageTask$_packagePatch_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPackageTask$_packagePatch_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "copyPatchSummary";
            stringArr0[1] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPackageTask$_packagePatch_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPackageTask$_packagePatch_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPackageTask$_packagePatch_closure1.$callSiteArray != null ? RFixPatchPackageTask$_packagePatch_closure1.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask$_packagePatch_closure1.$callSiteArray.get();
            RFixPatchPackageTask$_packagePatch_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
    // class: com/tencent/rfix/build/gradle/task/RFixPatchPackageTask$_packagePatch_closure1
    public final class RFixPatchPackageTask$_packagePatch_closure1 implements GeneratedClosure {
        private static synthetic ClassInfo $staticClassInfo;
        public static transient synthetic boolean __$stMC;
        private static synthetic SoftReference $callSiteArray;

        public RFixPatchPackageTask$_packagePatch_closure1(Object _outerInstance, Object _thisObject) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packagePatch_closure1.$getCallSiteArray();
            super(_outerInstance, _thisObject);
        }

        public Object doCall(File src, int index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packagePatch_closure1.$getCallSiteArray();
            return siteArr0[0].callCurrent(this, Integer.valueOf(index), src);
        }

        public Object call(File src, int index) {
            CallSite[] siteArr0 = RFixPatchPackageTask$_packagePatch_closure1.$getCallSiteArray();
            return siteArr0[1].callCurrent(this, src, Integer.valueOf(index));
        }

        protected /* synthetic */ MetaClass $getStaticMetaClass() {
            if (this.getClass() != RFixPatchPackageTask$_packagePatch_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            else {
                if (RFixPatchPackageTask$_packagePatch_closure1.$staticClassInfo == null) {
                    infoVar1 = ClassInfo.getClassInfo(this.getClass());
                    RFixPatchPackageTask$_packagePatch_closure1.$staticClassInfo = ClassInfo.getClassInfo(this.getClass());
                }
                return RFixPatchPackageTask$_packagePatch_closure1.$staticClassInfo.getMetaClass();
            }
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] stringArr0) {
            stringArr0[0] = "copyPatchSummary";
            stringArr0[1] = "doCall";
        }

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String str0 = new String[]{};
            RFixPatchPackageTask$_packagePatch_closure1.$createCallSiteArray_1(str0);
            return new CallSiteArray(RFixPatchPackageTask$_packagePatch_closure1.class, str0);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray array = RFixPatchPackageTask$_packagePatch_closure1.$callSiteArray != null ? RFixPatchPackageTask$_packagePatch_closure1.$createCallSiteArray() : (CallSiteArray)RFixPatchPackageTask$_packagePatch_closure1.$callSiteArray.get();
            RFixPatchPackageTask$_packagePatch_closure1.$callSiteArray = new SoftReference(array);
            return var_0_0.array;
        }

    }
}
