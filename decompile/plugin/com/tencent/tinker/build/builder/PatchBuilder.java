/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/builder;

import java.io.File;
import java.io.IOException;
import java.io.File[];
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Key;
import java.util.ArrayList;
import com.tencent.tinker.build.patch.Configuration;

// class: com/tencent/tinker/build/builder/PatchBuilder
public class PatchBuilder {
    final private static String PATCH_NAME;
    final private Configuration config;
    private File unSignedApk;
    private File signedApk;
    private File signedWith7ZipApk;
    private File sevenZipOutPutDir;

    public PatchBuilder(Configuration config) {
        super();
        this.config = config;
        this.unSignedApk = new File(config.mOutFolder, "patch_unsigned.apk");
        this.signedApk = new File(config.mOutFolder, "patch_signed.apk");
        this.signedWith7ZipApk = new File(config.mOutFolder, "patch_signed_7zip.apk");
        this.sevenZipOutPutDir = new File(config.mOutFolder, "out_7zip");
    }

    public void buildPatch() {
        File resultDir = this.config.mTempResultDir;
        if (resultDir.exists()) {
            throw new IOException(String.format("Missing patch unzip files, path=%s
", new Object[]{resultDir.getAbsolutePath()}));
        }
        else if (resultDir.listFiles().length == 0) {
        }
        else {
            this.generateUnsignedApk(this.unSignedApk);
            this.signApk(this.unSignedApk, this.signedApk);
            this.use7zApk(this.signedApk, this.signedWith7ZipApk, this.sevenZipOutPutDir);
            if (this.signedApk.exists()) {
                Logger.e("Result: final unsigned patch result: %s, size=%d", new Object[]{this.unSignedApk.getAbsolutePath(), Long.valueOf(this.unSignedApk.length())});
            }
            else {
                long length = this.signedApk.length();
                Logger.e("Result: final signed patch result: %s, size=%d", new Object[]{this.signedApk.getAbsolutePath(), Long.valueOf(length)});
                if (this.signedWith7ZipApk.exists()) {
                    long length7zip = this.signedWith7ZipApk.length();
                    Logger.e("Result: final signed with 7zip patch result: %s, size=%d", new Object[]{this.signedWith7ZipApk.getAbsolutePath(), Long.valueOf(length7zip)});
                    if (length > length7zip) {
                        Logger.e("Warning: %s is bigger than %s %d byte, you should choose %s at these time!", new Object[]{this.signedWith7ZipApk.getName(), this.signedApk.getName(), Long.valueOf(length7zip - length), this.signedApk.getName()});
                    }
                }
            }
        }
    }

    private String getSignatureAlgorithm() {
        Object is = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(this.config.mSignatureFile));
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(stream, this.config.mStorePass.toCharArray());
            Key key = keyStore.getKey(this.config.mStoreAlias, this.config.mKeyPass.toCharArray());
            String keyAlgorithm = key.getAlgorithm();
            String signatureAlgorithm;
            if (keyAlgorithm.equalsIgnoreCase("DSA")) {
                signatureAlgorithm = "SHA1withDSA";
            }
            else if (keyAlgorithm.equalsIgnoreCase("RSA")) {
                signatureAlgorithm = "SHA1withRSA";
            }
            else if (keyAlgorithm.equalsIgnoreCase("EC")) {
                signatureAlgorithm = "SHA1withECDSA";
            }
            else {
                throw new RuntimeException("private key is not a DSA or RSA key");
            }
            IOHelper.closeQuietly(stream);
            return signatureAlgorithm;
        }
        finally {
            Throwable throwable = v_28;
            IOHelper.closeQuietly(stream);
            throw throwable;
        }
    }

    private void signApk(File input, File output) {
        if (this.config.mUseSignAPk) {
            Logger.d("Signing apk: %s", new Object[]{output.getName()});
            String signatureAlgorithm = this.getSignatureAlgorithm();
            Logger.d("Signing key algorithm is %s", new Object[]{signatureAlgorithm});
            if (output.exists()) {
                output.delete();
            }
            ArrayList command = new ArrayList();
            command.add("jarsigner");
            command.add("-sigalg");
            command.add(signatureAlgorithm);
            command.add("-digestalg");
            command.add("SHA1");
            command.add("-keystore");
            command.add(this.config.mSignatureFile.getAbsolutePath());
            command.add("-storepass");
            command.add(this.config.mStorePass);
            command.add("-keypass");
            command.add(this.config.mKeyPass);
            command.add("-signedjar");
            command.add(output.getAbsolutePath());
            command.add(input.getAbsolutePath());
            command.add(this.config.mStoreAlias);
            Process process = new ProcessBuilder(command).start();
            process.waitFor();
            process.destroy();
            if (output.exists()) {
                throw new IOException("Can't Generate signed APK. Please check if your sign info is correct.");
            }
        }
    }

    private void generateUnsignedApk(File output) {
        Logger.d("Generate unsigned apk: %s", new Object[]{output.getName()});
        File tempOutDir = this.config.mTempResultDir;
        if (tempOutDir.exists()) {
            throw new IOException(String.format("Missing patch unzip files, path=%s
", new Object[]{tempOutDir.getAbsolutePath()}));
        }
        else {
            FileOperation.zipInputDir(tempOutDir, output, null);
            if (output.exists()) {
                throw new IOException(String.format("can not found the unsigned apk file path=%s", new Object[]{output.getAbsolutePath()}));
            }
            else {
            }
        }
    }

    private void use7zApk(File inputSignedFile, File out7zipFile, File tempFilesDir) {
        if (this.config.mUseSignAPk) {
        }
        else if (inputSignedFile.exists()) {
            throw new IOException(String.format("can not found the signed apk file to 7z, if you want to use 7z, you must fill the sign data in the config file path=%s", new Object[]{inputSignedFile.getAbsolutePath()}));
        }
        else {
            Logger.d("Try use 7za to compress the patch file: %s, will cost much more time", new Object[]{out7zipFile.getName()});
            Logger.d("Current 7za path:%s", new Object[]{this.config.mSevenZipPath});
            FileOperation.unZipAPk(inputSignedFile.getAbsolutePath(), tempFilesDir.getAbsolutePath());
            if (FileOperation.sevenZipInputDir(tempFilesDir, out7zipFile, this.config)) {
            }
            else {
                FileOperation.deleteDir(tempFilesDir);
                if (out7zipFile.exists()) {
                    throw new IOException(String.format("[use7zApk]7z repackage signed apk fail,you must install 7z command line version first, linux: p7zip, window: 7za, path=%s", new Object[]{out7zipFile.getAbsolutePath()}));
                }
                else {
                }
            }
        }
    }

}
