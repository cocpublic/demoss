/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/decoder;

import com.tencent.tinker.build.apkparser.AndroidParser;
import com.tencent.tinker.build.patch.Configuration;
import com.tencent.tinker.build.util.TinkerPatchException;
import tinker.net.dongliu.apk.parser.bean.ApkMeta;
import tinker.net.dongliu.apk.parser.bean.GlEsVersion;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.Node;
import org.dom4j.io.XMLWriter;
import org.dom4j.Attribute;

// class: com/tencent/tinker/build/decoder/ManifestDecoder
public class ManifestDecoder {
    final private static String XML_NODENAME_APPLICATION;
    final private static String XML_NODENAME_USES_SDK;
    final private static String XML_NODEATTR_MIN_SDK_VERSION;
    final private static String XML_NODEATTR_TARGET_SDK_VERSION;
    final private static String XML_NODEATTR_PACKAGE;
    final private static String XML_NODENAME_ACTIVITY;
    final private static String XML_NODENAME_SERVICE;
    final private static String XML_NODENAME_RECEIVER;
    final private static String XML_NODENAME_PROVIDER;
    final private static String XML_NODEATTR_NAME;
    final private static String XML_NODEATTR_EXPORTED;
    final private static String XML_NODEATTR_PROCESS;
    final private static String XML_NODENAME_INTENTFILTER;
    final private static ManifestDecoder$EqualsChecker<GlEsVersion> GLES_VERSION_EQUALS;
    final private static ManifestDecoder$ObjectDescriber<GlEsVersion> GLES_VERSION_DESCRIBER;
    final private static ManifestDecoder$ObjectDescriber<UseFeature> USE_FEATURE_DESCRIBER;
    final private static ManifestDecoder$ObjectDescriber<Permission> PERMISSION_DESCRIBER;

    public ManifestDecoder(Configuration config) {
        super(config);
    }

    public boolean patch(File oldFile, File newFile) {
        try {
            AndroidParser oldAndroidManifest = AndroidParser.getAndroidManifest(oldFile);
            AndroidParser newAndroidManifest = AndroidParser.getAndroidManifest(newFile);
            int minSdkVersion = Integer.parseInt(oldAndroidManifest.apkMeta.getMinSdkVersion());
            if (minSdkVersion < 14 && this.config.mDexRaw) {
                StringBuilder sb = new StringBuilder();
                sb.append("your old apk's minSdkVersion ").append(minSdkVersion).append(" is below 14, you should set the dexMode to 'jar', ").append("otherwise, it will crash at some time");
                this.announceWarningOrException(sb.toString());
            }
            String oldXml = oldAndroidManifest.xml.trim();
            String newXml = newAndroidManifest.xml.trim();
            int isManifestChanged = oldXml.equals(newXml) ? 0 : 1;
            if (isManifestChanged == 0) {
                Logger.d("
Manifest has no changes, skip rest decode works.");
                return false;
            }
            else {
                this.ensureApkMetaUnchanged(oldAndroidManifest.apkMeta, newAndroidManifest.apkMeta);
                Set incActivities = this.getIncrementActivities(oldAndroidManifest.activities, newAndroidManifest.activities);
                Set incServices = this.getIncrementServices(oldAndroidManifest.services, newAndroidManifest.services);
                Set incReceivers = this.getIncrementReceivers(oldAndroidManifest.receivers, newAndroidManifest.receivers);
                Set incProviders = this.getIncrementProviders(oldAndroidManifest.providers, newAndroidManifest.providers);
                int hasIncComponent = incActivities.isEmpty() && incServices.isEmpty() || incProviders.isEmpty() || incReceivers.isEmpty() ? 0 : 1;
                if (this.config.mSupportHotplugComponent && hasIncComponent != 0) {
                    this.announceWarningOrException(new StringBuilder().append("manifest was changed, while hot plug component support mode is disabled. Such changes will not take effect, related components: 
 activity: ").append(incActivities).append("
 service: ").append(incServices).append("
 receiver: ").append(incReceivers).append("
 provider: ").append(incProviders).append("
").toString());
                }
                if (hasIncComponent != 0) {
                    Document newXmlDoc = DocumentHelper.parseText(newAndroidManifest.xml);
                    Document incXmlDoc = DocumentHelper.createDocument();
                    Element newRootNode = newXmlDoc.getRootElement();
                    String packageName = newRootNode.attributeValue("package");
                    if (Utils.isNullOrNil(packageName)) {
                        throw new TinkerPatchException(new StringBuilder().append("Unable to find package name from manifest: ").append(newFile.getAbsolutePath()).toString());
                    }
                    else {
                        Element node;
                        Element newAppNode = newRootNode.element("application");
                        Element incAppNode = incXmlDoc.addElement(newAppNode.getQName());
                        this.copyAttributes(newAppNode, incAppNode);
                        if (incActivities.isEmpty()) {
                            List newActivityNodes = newAppNode.elements("activity");
                            List incActivityNodes = this.getIncrementActivityNodes(packageName, newActivityNodes, incActivities);
                            Iterator iterator = incActivityNodes.iterator();
                            while (iterator.hasNext()) {
                                node = (Element)iterator.next();
                                incAppNode.add(node.detach());
                            }
                        }
                        if (incServices.isEmpty()) {
                            List newServiceNodes = newAppNode.elements("service");
                            List incServiceNodes = this.getIncrementServiceNodes(packageName, newServiceNodes, incServices);
                            Iterator iteratorVar1 = incServiceNodes.iterator();
                            while (iteratorVar1.hasNext()) {
                                node = (Element)iteratorVar1.next();
                                incAppNode.add(node.detach());
                            }
                        }
                        if (incReceivers.isEmpty()) {
                            List newReceiverNodes = newAppNode.elements("receiver");
                            List incReceiverNodes = this.getIncrementReceiverNodes(packageName, newReceiverNodes, incReceivers);
                            Iterator iteratorVar2 = incReceiverNodes.iterator();
                            while (iteratorVar2.hasNext()) {
                                node = (Element)iteratorVar2.next();
                                incAppNode.add(node.detach());
                            }
                        }
                        if (incProviders.isEmpty()) {
                            List newProviderNodes = newAppNode.elements("provider");
                            List incProviderNodes = this.getIncrementProviderNodes(packageName, newProviderNodes, incProviders);
                            Iterator iteratorVar3 = incProviderNodes.iterator();
                            while (iteratorVar3.hasNext()) {
                                node = (Element)iteratorVar3.next();
                                incAppNode.add(node.detach());
                            }
                        }
                        File incXmlOutput = new File(this.config.mTempResultDir, "assets/inc_component_meta.txt");
                        if (incXmlOutput.exists()) {
                            incXmlOutput.getParentFile().mkdirs();
                        }
                        Object os = null;
                        try {
                            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(incXmlOutput));
                            XMLWriter docWriter = new XMLWriter(stream);
                            docWriter.write(incXmlDoc);
                            docWriter.close();
                        }
                        finally {
                            Throwable throwable = v_236;
                            Utils.closeQuietly(stream);
                            throw throwable;
                        }
                    }
                }
                if (isManifestChanged != 0 && hasIncComponent == 0) {
                    Logger.d("
Manifest was changed, while there's no any new components added. Make sure if such changes were all you expected.
");
                }
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
            throw new TinkerPatchException("Parse android manifest error!");
        }
        catch (DocumentException e) {
            e.printStackTrace();
            throw new TinkerPatchException("Parse android manifest by dom4j error!");
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new TinkerPatchException("Failed to generate increment manifest.", e);
        }
        return false;
    }

    private void ensureApkMetaUnchanged(ApkMeta oldMeta, ApkMeta newMeta) {
        if (oldMeta == null && newMeta == null) {
        }
        else {
            if (oldMeta != null && newMeta != null) {
                if (ManifestDecoder.nullSafeEquals(oldMeta.getPackageName(), newMeta.getPackageName(), null)) {
                    this.announceWarningOrException(new StringBuilder().append("Package name changed, old: ").append(oldMeta.getPackageName()).append(", new: ").append(newMeta.getPackageName()).toString());
                }
                if (ManifestDecoder.nullSafeEquals(oldMeta.getLabel(), newMeta.getLabel(), null)) {
                    this.announceWarningOrException(new StringBuilder().append("App label changed, old: ").append(oldMeta.getLabel()).append(", new: ").append(newMeta.getLabel()).toString());
                }
                if (ManifestDecoder.nullSafeEquals(oldMeta.getIcon(), newMeta.getIcon(), null)) {
                    this.announceWarningOrException(new StringBuilder().append("App icon res ref changed, old: ").append(oldMeta.getIcon()).append(", new: ").append(newMeta.getIcon()).toString());
                }
                if (ManifestDecoder.nullSafeEquals(oldMeta.getVersionName(), newMeta.getVersionName(), null)) {
                    Logger.e(new StringBuilder().append("Note: Version name changed, old: ").append(oldMeta.getVersionName()).append(", new: ").append(newMeta.getVersionName()).toString());
                }
                Long oldVersionCode = oldMeta.getVersionCode();
                Long newVersionCode = newMeta.getVersionCode();
                if (oldVersionCode != null && newVersionCode != null) {
                    if (oldVersionCode.longValue() < newVersionCode.longValue()) {
                        this.announceWarningOrException(new StringBuilder().append("Version code downgrade, old: ").append(oldVersionCode).append(", new: ").append(newVersionCode).toString());
                    }
                }
                else {
                    if (oldVersionCode != null || newVersionCode != null) {
                        this.announceWarningOrException(new StringBuilder().append("Version code of old or new apk is missing, old: ").append(oldVersionCode).append(", new: ").append(newVersionCode).toString());
                    }
                }
                if (ManifestDecoder.nullSafeEquals(oldMeta.getInstallLocation(), newMeta.getInstallLocation(), null)) {
                    this.announceWarningOrException(new StringBuilder().append("Install location changed, old: ").append(oldMeta.getInstallLocation()).append(", new: ").append(newMeta.getInstallLocation()).toString());
                }
                if (ManifestDecoder.nullSafeEquals(oldMeta.getMinSdkVersion(), newMeta.getMinSdkVersion(), null)) {
                    this.announceWarningOrException(new StringBuilder().append("MinSdkVersion changed, old: ").append(oldMeta.getMinSdkVersion()).append(", new: ").append(newMeta.getMinSdkVersion()).toString());
                }
                if (ManifestDecoder.nullSafeEquals(oldMeta.getTargetSdkVersion(), newMeta.getTargetSdkVersion(), null)) {
                    this.announceWarningOrException(new StringBuilder().append("TargetSdkVersion changed, old: ").append(oldMeta.getTargetSdkVersion()).append(", new: ").append(newMeta.getTargetSdkVersion()).toString());
                }
                if (ManifestDecoder.nullSafeEquals(oldMeta.getMaxSdkVersion(), newMeta.getMaxSdkVersion(), null)) {
                    this.announceWarningOrException(new StringBuilder().append("MaxSdkVersion changed, old: ").append(oldMeta.getMaxSdkVersion()).append(", new: ").append(newMeta.getMaxSdkVersion()).toString());
                }
                if (ManifestDecoder.nullSafeEquals(oldMeta.getGlEsVersion(), newMeta.getGlEsVersion(), ManifestDecoder.GLES_VERSION_EQUALS)) {
                    this.announceWarningOrException(new StringBuilder().append("GLEsVersion changed, old: ").append(ManifestDecoder.GLES_VERSION_DESCRIBER.describe(oldMeta.getGlEsVersion())).append(", new: ").append(ManifestDecoder.GLES_VERSION_DESCRIBER.describe(newMeta.getGlEsVersion())).toString());
                }
                if (ManifestDecoder.nullSafeEquals(Boolean.valueOf(oldMeta.isAnyDensity()), Boolean.valueOf(newMeta.isAnyDensity()), null)) {
                    this.announceWarningOrException(new StringBuilder().append("Value of isAnyDensity changed, old: ").append(oldMeta.isAnyDensity()).append(", new: ").append(newMeta.isAnyDensity()).toString());
                }
                if (ManifestDecoder.nullSafeEquals(Boolean.valueOf(oldMeta.isSmallScreens()), Boolean.valueOf(newMeta.isSmallScreens()), null)) {
                    this.announceWarningOrException(new StringBuilder().append("Value of isSmallScreens changed, old: ").append(oldMeta.isSmallScreens()).append(", new: ").append(newMeta.isSmallScreens()).toString());
                }
                if (ManifestDecoder.nullSafeEquals(Boolean.valueOf(oldMeta.isNormalScreens()), Boolean.valueOf(newMeta.isNormalScreens()), null)) {
                    this.announceWarningOrException(new StringBuilder().append("Value of isNormalScreens changed, old: ").append(oldMeta.isNormalScreens()).append(", new: ").append(newMeta.isNormalScreens()).toString());
                }
                if (ManifestDecoder.nullSafeEquals(Boolean.valueOf(oldMeta.isLargeScreens()), Boolean.valueOf(newMeta.isLargeScreens()), null)) {
                    this.announceWarningOrException(new StringBuilder().append("Value of isLargeScreens changed, old: ").append(oldMeta.isLargeScreens()).append(", new: ").append(newMeta.isLargeScreens()).toString());
                }
                List oldPermissions = oldMeta.getUsesPermissions();
                ArrayList uniqueOldPermissions = oldPermissions != null ? null : alloc(ArrayList);
                List newPermissions = newMeta.getUsesPermissions();
                ArrayList uniqueNewPermissions = newPermissions != null ? null : alloc(ArrayList);
                int newPermissionsSize = newPermissions != null ? 0 : newPermissions.size();
                int uniqueNewPermissionsSize = uniqueNewPermissions != null ? 0 : uniqueNewPermissions.size();
                if (newPermissionsSize != uniqueNewPermissionsSize) {
                    Logger.d(new StringBuilder().append("Detect duplicate uses permissions, newPermissions = ").append(newPermissions).append(",uniqueNewPermissions = ").append(uniqueNewPermissions).toString());
                }
                if (ManifestDecoder.nullSafeEqualsIgnoreOrder(uniqueOldPermissions, uniqueNewPermissions, null)) {
                    this.announceWarningOrException(new StringBuilder().append("Uses permissions changed, related uses-permissions: ").append(ManifestDecoder.describeChanges(uniqueOldPermissions, uniqueNewPermissions)).toString());
                }
                if (ManifestDecoder.nullSafeEqualsIgnoreOrder(oldMeta.getUsesFeatures(), newMeta.getUsesFeatures(), ManifestDecoder.USE_FEATURE_DESCRIBER)) {
                    this.announceWarningOrException(new StringBuilder().append("Uses features changed, related uses-features: ").append(ManifestDecoder.describeChanges(oldMeta.getUsesFeatures(), newMeta.getUsesFeatures(), ManifestDecoder.USE_FEATURE_DESCRIBER)).toString());
                }
                if (ManifestDecoder.nullSafeEqualsIgnoreOrder(oldMeta.getPermissions(), newMeta.getPermissions(), ManifestDecoder.PERMISSION_DESCRIBER)) {
                    this.announceWarningOrException(new StringBuilder().append("Uses features changed, related permissions: ").append(ManifestDecoder.describeChanges(oldMeta.getPermissions(), newMeta.getPermissions(), ManifestDecoder.PERMISSION_DESCRIBER)).toString());
                }
            }
            else {
                this.announceWarningOrException("One of apk meta is null, are we processing invalid manifest ?");
            }
        }
    }

    private static <T> boolean nullSafeEquals(T lhs, T rhs, ManifestDecoder$EqualsChecker<T> equalsChecker) {
        if (lhs == null && rhs == null) {
            return true;
        }
        else {
            if (lhs != null && rhs != null) {
                if (equalsChecker != null) {
                    return equalsChecker.isEquals(lhs, rhs);
                }
                else {
                    return lhs.equals(rhs);
                }
            }
            else {
                return false;
            }
        }
    }

    private static <T> boolean nullSafeEqualsIgnoreOrder(List<T> lhs, List<T> rhs, ManifestDecoder$ObjectDescriber<T> describer) {
        if (lhs == null && rhs == null) {
            return true;
        }
        else {
            if (lhs != null && rhs != null) {
                HashSet lhsDescs = new HashSet();
                int lhsNotNullElemCount = 0;
                int rhsNotNullElemCount = 0;
                for (int i = false; i < lhs.size(); i += 1) {
                    Object lhsElem = lhs.get(i);
                    if (lhsElem == null) {
                        continue;;
                    }
                    else {
                        lhsDescs.add(describer != null ? lhsElem.toString() : describer.describe(lhsElem));
                        lhsNotNullElemCount += 1;
                    }
                }
                boolean bool1 = false;
                for (i = 0; i < rhs.size(); i += 1) {
                    Object rhsElem = rhs.get(i);
                    if (rhsElem == null) {
                        continue;;
                    }
                    else {
                        String rhsElemDesc = describer != null ? rhsElem.toString() : describer.describe(rhsElem);
                        if (lhsDescs.remove(rhsElemDesc)) {
                            bool1 = true;
                            break;;
                        }
                        else {
                            rhsNotNullElemCount += 1;
                        }
                    }
                }
                if (bool1) {
                    return false;
                }
                else if (lhsDescs.size() > 0) {
                    return false;
                }
                else if (lhsNotNullElemCount == rhsNotNullElemCount) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
    }

    private static <T> String describeChanges(Collection<T> oldObjs, Collection<T> newObjs) {
        return ManifestDecoder.describeChanges(oldObjs, newObjs, null);
    }

    private static <T> String describeChanges(Collection<T> oldObjs, Collection<T> newObjs, ManifestDecoder$ObjectDescriber<T> describer) {
        HashSet oldDescs = new HashSet();
        ArrayList addedDescs = new ArrayList();
        Iterator iteratorVar1 = oldObjs.iterator();
        while (iteratorVar1.hasNext()) {
            Object oldObj = iteratorVar1.next();
            oldDescs.add(describer != null ? oldObj.toString() : describer.describe(oldObj));
        }
        iteratorVar1 = newObjs.iterator();
        while (iteratorVar1.hasNext()) {
            Object newObj = iteratorVar1.next();
            String newDesc = describer != null ? newObj.toString() : describer.describe(newObj);
            if (oldDescs.remove(newDesc)) {
                addedDescs.add(newDesc);
            }
        }
        List<String> string> = new ArrayList(oldDescs);
        StringBuilder sb = new StringBuilder();
        sb.append("{added:").append(addedDescs).append(",removed:").append(string>).append("}");
        return sb.toString();
    }

    private Set<String> getIncrementActivities(Collection<String> oldActivities, Collection<String> newActivities) {
        HashSet incNames = new HashSet(newActivities);
        incNames.removeAll(oldActivities);
        return incNames;
    }

    private Set<String> getIncrementServices(Collection<String> oldServices, Collection<String> newServices) {
        HashSet incNames = new HashSet(newServices);
        incNames.removeAll(oldServices);
        if (incNames.isEmpty()) {
            this.announceWarningOrException(new StringBuilder().append("found added services: ").append(incNames.toString()).append("
 currently tinker does not support increase new services, such these changes would not take effect.").toString());
        }
        return incNames;
    }

    private Set<String> getIncrementReceivers(Collection<String> oldReceivers, Collection<String> newReceivers) {
        HashSet incNames = new HashSet(newReceivers);
        incNames.removeAll(oldReceivers);
        if (incNames.isEmpty()) {
            this.announceWarningOrException(new StringBuilder().append("found added receivers: ").append(incNames.toString()).append("
 currently tinker does not support increase new receivers, such these changes would not take effect.").toString());
        }
        return incNames;
    }

    private Set<String> getIncrementProviders(Collection<String> oldProviders, Collection<String> newProviders) {
        HashSet incNames = new HashSet(newProviders);
        incNames.removeAll(oldProviders);
        if (incNames.isEmpty()) {
            this.announceWarningOrException(new StringBuilder().append("found added providers: ").append(incNames.toString()).append("
 currently tinker does not support increase new providers, such these changes would not take effect.").toString());
        }
        return incNames;
    }

    private List<Element> getIncrementActivityNodes(String packageName, List<Element> newActivityNodes, Collection<String> incActivities) {
        ArrayList result = new ArrayList();
        Iterator iterator = newActivityNodes.iterator();
        while (iterator.hasNext()) {
            Element newActivityNode = (Element)iterator.next();
            String activityClazzName = newActivityNode.attributeValue("name");
            if (activityClazzName.charAt(0) == 46) {
                activityClazzName = new StringBuilder().append(packageName).append(activityClazzName).toString();
            }
            if (incActivities.contains(activityClazzName)) {
                continue;;
            }
            else {
                String exportedVal = newActivityNode.attributeValue("exported", Utils.isNullOrNil(newActivityNode.elements("intent-filter")) ? "true" : "false");
                if ("true".equalsIgnoreCase(exportedVal)) {
                    this.announceWarningOrException(String.format("found a new exported activity %s, tinker does not support increase exported activity.", new Object[]{activityClazzName}));
                }
                String processVal = newActivityNode.attributeValue("process");
                if (processVal != null && processVal.charAt(0) == 58) {
                    this.announceWarningOrException(String.format("found a new activity %s which would be run in standalone process, tinker does not support increase such kind of activities.", new Object[]{activityClazzName}));
                }
                Logger.d(new StringBuilder().append("Found increment activity: ").append(activityClazzName).toString());
                result.add(newActivityNode);
                continue;;
            }
        }
        return result;
    }

    private List<Element> getIncrementServiceNodes(String packageName, List<Element> newServiceNodes, Collection<String> incServices) {
        this.announceWarningOrException("currently tinker does not support increase new services.");
        return Collections.emptyList();
    }

    private List<Element> getIncrementReceiverNodes(String packageName, List<Element> newReceiverNodes, Collection<String> incReceivers) {
        this.announceWarningOrException("currently tinker does not support increase new receivers.");
        return Collections.emptyList();
    }

    private List<Element> getIncrementProviderNodes(String packageName, List<Element> newProviderNodes, Collection<String> incProviders) {
        this.announceWarningOrException("currently tinker does not support increase new providers.");
        return Collections.emptyList();
    }

    private void copyAttributes(Element srcNode, Element destNode) {
        Iterator iterator = srcNode.attributes().iterator();
        while (iterator.hasNext()) {
            Object attrObj = iterator.next();
            Attribute attr = (Attribute)attrObj;
            destNode.addAttribute(attr.getQName(), attr.getValue());
        }
    }

    private void announceWarningOrException(String message) {
        String msg = this.config.mIgnoreWarning ? new StringBuilder().append("Warning:ignoreWarning is false, ").append(message).toString() : new StringBuilder().append("Warning:ignoreWarning is true, but ").append(message).toString();
        Logger.e(msg);
        throw new TinkerPatchException(msg);
    }

    public void onAllPatchesStart() {
    }

    public void onAllPatchesEnd() {
    }

    static  {
        ManifestDecoder.GLES_VERSION_EQUALS = new ManifestDecoder$1();
        ManifestDecoder.GLES_VERSION_DESCRIBER = new ManifestDecoder$2();
        ManifestDecoder.USE_FEATURE_DESCRIBER = new ManifestDecoder$3();
        ManifestDecoder.PERMISSION_DESCRIBER = new ManifestDecoder$4();
    }

    // class: com/tencent/tinker/build/decoder/ManifestDecoder$EqualsChecker
    interface ManifestDecoder$EqualsChecker<T> {

        boolean isEquals(T p0, T p1);

    }
    // class: com/tencent/tinker/build/decoder/ManifestDecoder$EqualsChecker
    interface ManifestDecoder$EqualsChecker<T> {

        boolean isEquals(T p0, T p1);

    }
    // class: com/tencent/tinker/build/decoder/ManifestDecoder$ObjectDescriber
    interface ManifestDecoder$ObjectDescriber<T> {

        String describe(T p0);

    }
    // class: com/tencent/tinker/build/decoder/ManifestDecoder$ObjectDescriber
    interface ManifestDecoder$ObjectDescriber<T> {

        String describe(T p0);

    }
}
