/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/patch;

import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import com.tencent.tinker.build.util.TinkerPatchException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

// class: com/tencent/tinker/build/patch/Configuration
public class Configuration {
    final protected static String TAG_ISSUE;
    final protected static String DEX_ISSUE;
    final protected static String SO_ISSUE;
    final protected static String RES_ISSUE;
    final protected static String ARKHOT_ISSUE;
    final protected static String SIGN_ISSUE;
    final protected static String PACKAGE_CONFIG_ISSUE;
    final protected static String PROPERTY_ISSUE;
    final protected static String ATTR_ID;
    final protected static String ATTR_VALUE;
    final protected static String ATTR_NAME;
    final protected static String ATTR_IGNORE_WARNING;
    final protected static String ATTR_ALLOW_LOADER_IN_ANY_DEX;
    final protected static String ATTR_REMOVE_LOADER_FOR_ALL_DEX;
    final protected static String ATTR_IS_PROTECTED_APP;
    final protected static String ATTR_SUPPORT_HOTPLUG_COMPONENT;
    final protected static String ATTR_USE_SIGN;
    final protected static String ATTR_SEVEN_ZIP_PATH;
    final protected static String ATTR_CUSTOM_DIFF_PATH;
    final protected static String ATTR_CUSTOM_DIFF_PATH_ARGS;
    final protected static String ATTR_DEX_MODE;
    final protected static String ATTR_PATTERN;
    final protected static String ATTR_IGNORE_CHANGE;
    final protected static String ATTR_IGNORE_CHANGE_WARNING;
    final protected static String ATTR_RES_LARGE_MOD;
    final protected static String ATTR_ARKHOT_PATH;
    final protected static String ATTR_ARKHOT_NAME;
    final protected static String ATTR_LOADER;
    final protected static String ATTR_CONFIG_FIELD;
    final protected static String ATTR_SIGN_FILE_PATH;
    final protected static String ATTR_SIGN_FILE_KEYPASS;
    final protected static String ATTR_SIGN_FILE_STOREPASS;
    final protected static String ATTR_SIGN_FILE_ALIAS;
    public String mOldApkPath;
    public String mNewApkPath;
    public String mOutFolder;
    public File mOldApkFile;
    public File mNewApkFile;
    public boolean mIgnoreWarning;
    public boolean mAllowLoaderInAnyDex;
    public boolean mIsProtectedApp;
    public boolean mRemoveLoaderForAllDex;
    public boolean mSupportHotplugComponent;
    public HashSet<Pattern> mSoFilePattern;
    public HashSet<Pattern> mDexFilePattern;
    public HashSet<String> mDexLoaderPattern;
    public HashSet<String> mDexIgnoreWarningLoaderPattern;
    public boolean mDexRaw;
    public HashSet<Pattern> mResFilePattern;
    public HashSet<Pattern> mResIgnoreChangePattern;
    public HashSet<Pattern> mResIgnoreChangeWarningPattern;
    public HashSet<String> mResRawPattern;
    public int mLargeModSize;
    public boolean mUseApplyResource;
    public HashMap<String, String> mPackageFields;
    public String mSevenZipPath;
    public String mCustomDiffPath;
    public String mCustomDiffPathArgs;
    public boolean mUseSignAPk;
    public File mSignatureFile;
    public String mKeyPass;
    public String mStoreAlias;
    public String mStorePass;
    public File mTempResultDir;
    public File mTempUnzipOldDir;
    public File mTempUnzipNewDir;
    public boolean mUsingGradle;
    public String mArkHotPatchPath;
    public String mArkHotPatchName;

    public Configuration(File config, File outputFile, File oldApkFile, File newApkFile) {
        super();
        this.mUsingGradle = false;
        this.mSoFilePattern = new HashSet();
        this.mDexFilePattern = new HashSet();
        this.mDexLoaderPattern = new HashSet();
        this.mDexIgnoreWarningLoaderPattern = new HashSet();
        this.mResFilePattern = new HashSet();
        this.mResRawPattern = new HashSet();
        this.mResIgnoreChangePattern = new HashSet();
        this.mResIgnoreChangeWarningPattern = new HashSet();
        this.mPackageFields = new HashMap();
        this.mOutFolder = outputFile.getAbsolutePath();
        FileOperation.cleanDir(outputFile);
        this.mOldApkFile = oldApkFile;
        this.mOldApkPath = oldApkFile.getAbsolutePath();
        this.mNewApkFile = newApkFile;
        this.mNewApkPath = newApkFile.getAbsolutePath();
        this.mLargeModSize = 100;
        this.readXmlConfig(config);
        this.createTempDirectory();
        this.checkInputPatternParameter();
    }

    public Configuration(InputParam param) {
        String item;
        super();
        this.mUsingGradle = true;
        this.mSoFilePattern = new HashSet();
        this.mDexFilePattern = new HashSet();
        this.mDexLoaderPattern = new HashSet();
        this.mDexIgnoreWarningLoaderPattern = new HashSet();
        this.mResFilePattern = new HashSet();
        this.mResRawPattern = new HashSet();
        this.mResIgnoreChangePattern = new HashSet();
        this.mResIgnoreChangeWarningPattern = new HashSet();
        this.mPackageFields = new HashMap();
        Iterator iteratorVar4 = param.soFilePattern.iterator();
        while (iteratorVar4.hasNext()) {
            item = (String)iteratorVar4.next();
            this.addToPatterns(item, this.mSoFilePattern);
        }
        iteratorVar4 = param.dexFilePattern.iterator();
        while (iteratorVar4.hasNext()) {
            item = (String)iteratorVar4.next();
            this.addToPatterns(item, this.mDexFilePattern);
        }
        iteratorVar4 = param.resourceFilePattern.iterator();
        while (iteratorVar4.hasNext()) {
            item = (String)iteratorVar4.next();
            this.mResRawPattern.add(item);
            this.addToPatterns(item, this.mResFilePattern);
        }
        iteratorVar4 = param.resourceIgnoreChangePattern.iterator();
        while (iteratorVar4.hasNext()) {
            item = (String)iteratorVar4.next();
            this.addToPatterns(item, this.mResIgnoreChangePattern);
        }
        iteratorVar4 = param.resourceIgnoreChangeWarningPattern.iterator();
        while (iteratorVar4.hasNext()) {
            item = (String)iteratorVar4.next();
            this.addToPatterns(item, this.mResIgnoreChangeWarningPattern);
        }
        this.mLargeModSize = param.largeModSize;
        this.mUseApplyResource = param.useApplyResource;
        this.mDexLoaderPattern.addAll(param.dexLoaderPattern);
        this.mDexIgnoreWarningLoaderPattern.addAll(param.dexIgnoreWarningLoaderPattern);
        if (param.dexMode.equals("raw")) {
            this.mDexRaw = true;
        }
        this.mOldApkPath = param.oldApk;
        this.mOldApkFile = new File(this.mOldApkPath);
        this.mNewApkPath = param.newApk;
        this.mNewApkFile = new File(this.mNewApkPath);
        this.mOutFolder = param.outFolder;
        this.mIgnoreWarning = param.ignoreWarning;
        this.mAllowLoaderInAnyDex = param.allowLoaderInAnyDex;
        this.mRemoveLoaderForAllDex = param.removeLoaderForAllDex;
        this.mIsProtectedApp = param.isProtectedApp;
        this.mSupportHotplugComponent = param.supportHotplugComponent;
        this.mSevenZipPath = param.sevenZipPath;
        this.mPackageFields = param.configFields;
        this.mUseSignAPk = param.useSign;
        this.mCustomDiffPath = param.customDiffPath;
        this.mCustomDiffPathArgs = param.customDiffPathArgs;
        this.setSignData(param.signFile, param.keypass, param.storealias, param.storepass);
        FileOperation.cleanDir(new File(this.mOutFolder));
        this.createTempDirectory();
        this.checkInputPatternParameter();
        this.mArkHotPatchName = param.arkHotPatchName;
        this.mArkHotPatchPath = param.arkHotPatchPath;
    }

    public String toString() {
        String name;
        StringBuffer sb = new StringBuffer();
        sb.append("configuration: 
");
        sb.append(new StringBuilder().append("oldApk:").append(this.mOldApkPath).append("
").toString());
        sb.append(new StringBuilder().append("newApk:").append(this.mNewApkPath).append("
").toString());
        sb.append(new StringBuilder().append("outputFolder:").append(this.mOutFolder).append("
").toString());
        sb.append(new StringBuilder().append("isIgnoreWarning:").append(this.mIgnoreWarning).append("
").toString());
        sb.append(new StringBuilder().append("isAllowLoaderClassInAnyDex:").append(this.mAllowLoaderInAnyDex).append("
").toString());
        sb.append(new StringBuilder().append("isRemoveLoaderForAllDex:").append(this.mRemoveLoaderForAllDex).append("
").toString());
        sb.append(new StringBuilder().append("isProtectedApp:").append(this.mIsProtectedApp).append("
").toString());
        sb.append(new StringBuilder().append("7-ZipPath:").append(this.mSevenZipPath).append("
").toString());
        sb.append(new StringBuilder().append("useSignAPk:").append(this.mUseSignAPk).append("
").toString());
        sb.append("package meta fields: 
");
        Iterator iteratorVar7 = this.mPackageFields.keySet().iterator();
        while (iteratorVar7.hasNext()) {
            name = (String)iteratorVar7.next();
            sb.append(new StringBuilder().append("filed name:").append(name).append(", filed value:").append((String)this.mPackageFields.get(name)).append("
").toString());
        }
        sb.append("dex configs: 
");
        if (this.mDexRaw) {
            sb.append("dexMode: raw
");
        }
        else {
            sb.append("dexMode: jar
");
        }
        iteratorVar7 = this.mDexFilePattern.iterator();
        while (iteratorVar7.hasNext()) {
            name = (Pattern)iteratorVar7.next();
            sb.append(new StringBuilder().append("dexPattern:").append(name.toString()).append("
").toString());
        }
        iteratorVar7 = this.mDexLoaderPattern.iterator();
        while (iteratorVar7.hasNext()) {
            name = (String)iteratorVar7.next();
            sb.append(new StringBuilder().append("dex loader:").append(name).append("
").toString());
        }
        iteratorVar7 = this.mDexIgnoreWarningLoaderPattern.iterator();
        while (iteratorVar7.hasNext()) {
            name = (String)iteratorVar7.next();
            sb.append(new StringBuilder().append("dex ignore warning loader:").append(name.toString()).append("
").toString());
        }
        sb.append("lib configs: 
");
        iteratorVar7 = this.mSoFilePattern.iterator();
        while (iteratorVar7.hasNext()) {
            name = (Pattern)iteratorVar7.next();
            sb.append(new StringBuilder().append("libPattern:").append(name.toString()).append("
").toString());
        }
        sb.append("resource configs: 
");
        iteratorVar7 = this.mResFilePattern.iterator();
        while (iteratorVar7.hasNext()) {
            name = (Pattern)iteratorVar7.next();
            sb.append(new StringBuilder().append("resPattern:").append(name.toString()).append("
").toString());
        }
        iteratorVar7 = this.mResIgnoreChangePattern.iterator();
        while (iteratorVar7.hasNext()) {
            name = (Pattern)iteratorVar7.next();
            sb.append(new StringBuilder().append("resIgnore change:").append(name.toString()).append("
").toString());
        }
        iteratorVar7 = this.mResIgnoreChangeWarningPattern.iterator();
        while (iteratorVar7.hasNext()) {
            name = (Pattern)iteratorVar7.next();
            sb.append(new StringBuilder().append("resIgnore change warning:").append(name.toString()).append("
").toString());
        }
        sb.append(new StringBuilder().append("largeModSize:").append(this.mLargeModSize).append("kb
").toString());
        sb.append(new StringBuilder().append("useApplyResource:").append(this.mUseApplyResource).append("
").toString());
        sb.append(new StringBuilder().append("ArkHot: ").append(this.mArkHotPatchPath).append(" / ").append(this.mArkHotPatchName).append("
").toString());
        return sb.toString();
    }

    private void createTempDirectory() {
        this.mTempResultDir = new File(new StringBuilder().append(this.mOutFolder).append(File.separator).append("tinker_result").toString());
        FileOperation.deleteDir(this.mTempResultDir);
        if (this.mTempResultDir.exists()) {
            this.mTempResultDir.mkdir();
        }
        String oldApkName = this.mOldApkFile.getName();
        if (oldApkName.endsWith(".apk")) {
            throw new TinkerPatchException(String.format("input apk file path must end with .apk, yours %s
", new Object[]{oldApkName}));
        }
        else {
            String newApkName = this.mNewApkFile.getName();
            if (newApkName.endsWith(".apk")) {
                throw new TinkerPatchException(String.format("input apk file path must end with .apk, yours %s
", new Object[]{newApkName}));
            }
            else {
                String tempOldName = oldApkName.substring(0, oldApkName.indexOf(".apk"));
                String tempNewName = newApkName.substring(0, newApkName.indexOf(".apk"));
                if (tempNewName.equalsIgnoreCase(tempOldName)) {
                    tempOldName = new StringBuilder().append(tempOldName).append("-old").toString();
                    tempNewName = new StringBuilder().append(tempNewName).append("-new").toString();
                }
                this.mTempUnzipOldDir = new File(this.mOutFolder, tempOldName);
                this.mTempUnzipNewDir = new File(this.mOutFolder, tempNewName);
            }
        }
    }

    public void setSignData(File signatureFile, String keypass, String storealias, String storepass) {
        if (this.mUseSignAPk) {
            this.mSignatureFile = signatureFile;
            if (this.mSignatureFile.exists()) {
                throw new IOException(String.format("the signature file do not exit, raw path= %s
", new Object[]{this.mSignatureFile.getAbsolutePath()}));
            }
            else {
                this.mKeyPass = keypass;
                this.mStoreAlias = storealias;
                this.mStorePass = storepass;
            }
        }
    }

    private void checkInputPatternParameter() {
        if (this.mSoFilePattern.isEmpty() && this.mDexFilePattern.isEmpty() && this.mResFilePattern.isEmpty()) {
            throw new TinkerPatchException("no dex, so or resource pattern are found");
        }
        else if (this.mLargeModSize <= 0) {
            throw new TinkerPatchException("largeModSize must be larger than 0");
        }
        else {
        }
    }

    void readXmlConfig(File xmlConfigFile) {
        if (xmlConfigFile.exists()) {
            return;
        }
        else {
            System.out.printf("reading config file, %s
", new Object[]{xmlConfigFile.getAbsolutePath()});
            Object input = null;
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                BufferedInputStream stream = new BufferedInputStream(new FileInputStream(xmlConfigFile));
                InputSource source = new InputSource(stream);
                factory.setNamespaceAware(false);
                factory.setValidating(false);
                DocumentBuilder builder = factory.newDocumentBuilder();
                builder.setEntityResolver(new Configuration$1(this));
                Document document = builder.parse(source);
                NodeList issues = document.getElementsByTagName("issue");
                int i = 0;
                for (int count = issues.getLength(); i < count; i += 1) {
                    Node node = issues.item(i);
                    Element element = (Element)node;
                    String id = element.getAttribute("id");
                    if (id.length() == 0) {
                        System.err.println("Invalid config file: Missing required issue id attribute");
                        continue;;
                    }
                    else if (id.equals("property")) {
                        this.readPropertyFromXml(node);
                        continue;;
                    }
                    else if (id.equals("dex")) {
                        this.readDexPatternsFromXml(node);
                        continue;;
                    }
                    else if (id.equals("lib")) {
                        this.readLibPatternsFromXml(node);
                        continue;;
                    }
                    else if (id.equals("resource")) {
                        this.readResPatternsFromXml(node);
                        continue;;
                    }
                    else if (id.equals("packageConfig")) {
                        this.readPackageConfigFromXml(node);
                        continue;;
                    }
                    else if (id.equals("sign")) {
                        if (this.mUseSignAPk) {
                            this.readSignFromXml(node);
                            continue;;
                        }
                    }
                    else if (id.equals("arkHot")) {
                        this.readArkHotPropertyFromXml(node);
                        continue;;
                    }
                    else {
                        System.err.println(new StringBuilder().append("unknown issue ").append(id).toString());
                    }
                }
            }
            finally {
                Throwable throwable = v_35;
                IOHelper.closeQuietly(stream);
                throw throwable;
            }
        }
    }

    private void readPropertyFromXml(Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            int j = 0;
            for (int n = childNodes.getLength(); j < n; j += 1) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == 1) {
                    String value;
                    Element check = (Element)child;
                    String tagName = check.getTagName();
                    value = check.getAttribute("value");
                    if (value == null) {
                        value = "";
                    }
                    if (tagName.equals("ignoreWarning")) {
                        this.mIgnoreWarning = value.equals("true");
                        continue;;
                    }
                    else if (tagName.equals("allowLoaderInAnyDex")) {
                        this.mAllowLoaderInAnyDex = value.equals("true");
                        continue;;
                    }
                    else if (tagName.equals("removeLoaderForAllDex")) {
                        this.mRemoveLoaderForAllDex = value.equals("true");
                        continue;;
                    }
                    else if (tagName.equals("isProtectedApp")) {
                        this.mIsProtectedApp = value.equals("true");
                        continue;;
                    }
                    else if (tagName.equals("supportHotplugComponent")) {
                        this.mSupportHotplugComponent = value.equals("true");
                        continue;;
                    }
                    else if (tagName.equals("useSign")) {
                        this.mUseSignAPk = value.equals("true");
                        continue;;
                    }
                    else if (tagName.equals("sevenZipPath")) {
                        File sevenZipFile = new File(value);
                        this.mSevenZipPath = sevenZipFile.exists() ? "7za" : value;
                        continue;;
                    }
                    else if (tagName.equals("customPath")) {
                        this.mCustomDiffPath = value;
                        continue;;
                    }
                    else if (tagName.equals("customPathArgs")) {
                        this.mCustomDiffPathArgs = value;
                        continue;;
                    }
                    else {
                        System.err.println(new StringBuilder().append("unknown property tag ").append(tagName).toString());
                    }
                }
            }
        }
    }

    private void readArkHotPropertyFromXml(Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            int j = 0;
            for (int n = childNodes.getLength(); j < n; j += 1) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == 1) {
                    Element check = (Element)child;
                    String tagName = check.getTagName();
                    String value = check.getAttribute("value");
                    if (tagName.equals("path")) {
                        this.mArkHotPatchPath = value;
                        this.mArkHotPatchPath.trim();
                        continue;;
                    }
                    else if (tagName.equals("name")) {
                        this.mArkHotPatchName = value;
                        this.mArkHotPatchName.trim();
                        continue;;
                    }
                    else {
                        System.err.println(new StringBuilder().append("unknown dex tag ").append(tagName).toString());
                    }
                }
            }
        }
    }

    private void readSignFromXml(Node node) {
        if (this.mSignatureFile != null) {
            System.err.println("already set the sign info from command line, ignore this");
        }
        else {
            NodeList childNodes = node.getChildNodes();
            if (childNodes.getLength() > 0) {
                int j = 0;
                for (int n = childNodes.getLength(); j < n; j += 1) {
                    Node child = childNodes.item(j);
                    if (child.getNodeType() == 1) {
                        Element check = (Element)child;
                        String tagName = check.getTagName();
                        String value = check.getAttribute("value");
                        if (value.length() == 0) {
                            throw new IOException(String.format("Invalid config file: Tag:%s Missing required attribute %s
", new Object[]{tagName, "value"}));
                        }
                        else if (tagName.equals("path")) {
                            this.mSignatureFile = new File(value);
                            if (this.mSignatureFile.exists()) {
                                throw new IOException(String.format("the signature file do not exit, raw path= %s
", new Object[]{this.mSignatureFile.getAbsolutePath()}));
                            }
                        }
                        else if (tagName.equals("storepass")) {
                            this.mStorePass = value;
                            this.mStorePass = this.mStorePass.trim();
                            continue;;
                        }
                        else if (tagName.equals("keypass")) {
                            this.mKeyPass = value;
                            this.mKeyPass = this.mKeyPass.trim();
                            continue;;
                        }
                        else if (tagName.equals("alias")) {
                            this.mStoreAlias = value;
                            this.mStoreAlias = this.mStoreAlias.trim();
                            continue;;
                        }
                        else {
                            System.err.println(new StringBuilder().append("unknown sign tag ").append(tagName).toString());
                        }
                    }
                }
            }
        }
    }

    private void readDexPatternsFromXml(Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            int j = 0;
            for (int n = childNodes.getLength(); j < n; j += 1) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == 1) {
                    Element check = (Element)child;
                    String tagName = check.getTagName();
                    String value = check.getAttribute("value");
                    if (tagName.equals("dexMode")) {
                        if (value.equals("raw")) {
                            this.mDexRaw = true;
                            continue;;
                        }
                    }
                    else if (tagName.equals("pattern")) {
                        this.addToPatterns(value, this.mDexFilePattern);
                        continue;;
                    }
                    else if (tagName.equals("loader")) {
                        this.mDexLoaderPattern.add(value);
                        continue;;
                    }
                    else if (tagName.equals("ignoreChange")) {
                        this.mDexIgnoreWarningLoaderPattern.add(value);
                        continue;;
                    }
                    else {
                        System.err.println(new StringBuilder().append("unknown dex tag ").append(tagName).toString());
                    }
                }
            }
        }
    }

    private void readLibPatternsFromXml(Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            int j = 0;
            for (int n = childNodes.getLength(); j < n; j += 1) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == 1) {
                    Element check = (Element)child;
                    String tagName = check.getTagName();
                    String value = check.getAttribute("value");
                    if (tagName.equals("pattern")) {
                        this.addToPatterns(value, this.mSoFilePattern);
                        continue;;
                    }
                    else {
                        System.err.println(new StringBuilder().append("unknown dex tag ").append(tagName).toString());
                    }
                }
            }
        }
    }

    private void readResPatternsFromXml(Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            int j = 0;
            for (int n = childNodes.getLength(); j < n; j += 1) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == 1) {
                    Element check = (Element)child;
                    String tagName = check.getTagName();
                    String value = check.getAttribute("value");
                    if (tagName.equals("pattern")) {
                        this.mResRawPattern.add(value);
                        this.addToPatterns(value, this.mResFilePattern);
                        continue;;
                    }
                    else if (tagName.equals("ignoreChange")) {
                        if (Utils.isBlank(value)) {
                            this.addToPatterns(value, this.mResIgnoreChangePattern);
                            continue;;
                        }
                    }
                    else if (tagName.equals("ignoreChangeWarning")) {
                        if (Utils.isBlank(value)) {
                            this.addToPatterns(value, this.mResIgnoreChangeWarningPattern);
                            continue;;
                        }
                    }
                    else if (tagName.equals("largeModSize")) {
                        this.mLargeModSize = Integer.valueOf(value).intValue();
                        continue;;
                    }
                    else {
                        System.err.println(new StringBuilder().append("unknown dex tag ").append(tagName).toString());
                    }
                }
            }
        }
    }

    private void readPackageConfigFromXml(Node node) {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            int j = 0;
            for (int n = childNodes.getLength(); j < n; j += 1) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == 1) {
                    Element check = (Element)child;
                    String tagName = check.getTagName();
                    String value = check.getAttribute("value");
                    String name = check.getAttribute("name");
                    if (tagName.equals("configField")) {
                        this.mPackageFields.put(name, value);
                        continue;;
                    }
                    else {
                        System.err.println(new StringBuilder().append("unknown package config tag ").append(tagName).toString());
                    }
                }
            }
        }
    }

    private void addToPatterns(String value, HashSet<Pattern> patterns) {
        if (value.length() == 0) {
            throw new IOException(String.format("Invalid config file: Missing required attribute %s
", new Object[]{"value"}));
        }
        else {
            value = Utils.convertToPatternString(value);
            Pattern pattern = Pattern.compile(value);
            patterns.add(pattern);
        }
    }

}
