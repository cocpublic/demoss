/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;

import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map$Entry;
import java.io.File;
import java.io.File[];
import java.io.PrintWriter;
import java.io.FileOutputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

// class: com/tencent/tinker/build/aapt/AaptUtil
public final class AaptUtil {
    final private static String ID_DEFINITION_PREFIX;
    final private static String ITEM_TAG;
    final private static XPathExpression ANDROID_ID_USAGE;
    final private static XPathExpression ANDROID_ID_DEFINITION;
    final private static Map<String, RDotTxtEntry$RType> RESOURCE_TYPES;
    final private static List<String> IGNORED_TAGS;

    public AaptUtil() {
        super();
    }

    private static XPathExpression createExpression(String expressionStr) {
        try {
            return XPathFactory.newInstance().newXPath().compile(expressionStr);
        }
        catch (XPathExpressionException e) {
            throw new AaptUtil$AaptUtilException(e);
        }
    }

    private static Map<String, RDotTxtEntry$RType> getResourceTypes() {
        HashMap types = new HashMap();
        RDotTxtEntry$RType[] typeArr0 = RDotTxtEntry$RType.values();
        for (int i1 = 0; i1 < typeArr0.length; i1 += 1) {
            RDotTxtEntry$RType rType = typeArr0[i1];
            types.put(rType.toString(), rType);
        }
        types.put("string-array", RDotTxtEntry$RType.ARRAY);
        types.put("integer-array", RDotTxtEntry$RType.ARRAY);
        types.put("declare-styleable", RDotTxtEntry$RType.STYLEABLE);
        return types;
    }

    public static AaptResourceCollector collectResource(List<String> resourceDirectoryList) {
        return AaptUtil.collectResource(resourceDirectoryList, null);
    }

    public static AaptResourceCollector collectResource(List<String> resourceDirectoryList, Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> rTypeResourceMap) {
        String resourceDirectory;
        AaptResourceCollector resourceCollector = new AaptResourceCollector(rTypeResourceMap);
        ArrayList references = new ArrayList();
        Iterator iteratorVar1 = resourceDirectoryList.iterator();
        while (iteratorVar1.hasNext()) {
            resourceDirectory = (String)iteratorVar1.next();
            try {
                AaptUtil.collectResources(resourceDirectory, resourceCollector);
                continue;;
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        iteratorVar1 = resourceDirectoryList.iterator();
        while (iteratorVar1.hasNext()) {
            resourceDirectory = (String)iteratorVar1.next();
            try {
                AaptUtil.processXmlFilesForIds(resourceDirectory, references, resourceCollector);
                continue;;
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return resourceCollector;
    }

    public static void processXmlFilesForIds(String resourceDirectory, List<RDotTxtEntry> references, AaptResourceCollector resourceCollector) {
        List xmlFullFilenameList = FileUtil.findMatchFile(resourceDirectory, ".xml");
        if (xmlFullFilenameList != null) {
            Iterator iterator = xmlFullFilenameList.iterator();
            while (iterator.hasNext()) {
                String xmlFullFilename = (String)iterator.next();
                File xmlFile = new File(xmlFullFilename);
                String parentFullFilename = xmlFile.getParent();
                File parentFile = new File(parentFullFilename);
                if (AaptUtil.isAValuesDirectory(parentFile.getName())) {
                    if (parentFile.getName().startsWith("raw")) {
                        continue;;
                    }
                    else {
                        AaptUtil.processXmlFile(xmlFullFilename, references, resourceCollector);
                        continue;;
                    }
                }
            }
        }
    }

    private static void collectResources(String resourceDirectory, AaptResourceCollector resourceCollector) {
        File resourceDirectoryFile = new File(resourceDirectory);
        File[] fileArray = resourceDirectoryFile.listFiles();
        if (fileArray != null) {
            for (int i1 = 0; i1 < fileArray.length; i1 += 1) {
                File file = fileArray[i1];
                if (file.isDirectory()) {
                    String directoryName = file.getName();
                    if (directoryName.startsWith("values")) {
                        if (AaptUtil.isAValuesDirectory(directoryName)) {
                            throw new AaptUtil$AaptUtilException(new StringBuilder().append("'").append(directoryName).append("' is not a valid values directory.").toString());
                        }
                        else {
                            AaptUtil.processValues(file.getAbsolutePath(), resourceCollector);
                            continue;;
                        }
                    }
                    else {
                        AaptUtil.processFileNamesInDirectory(file.getAbsolutePath(), resourceCollector);
                    }
                }
            }
        }
    }

    public static boolean isAValuesDirectory(String directoryName) {
        if (directoryName == null) {
            throw new NullPointerException("directoryName can not be null");
        }
        else {
            if (! directoryName.equals("values") || directoryName.startsWith("values-")) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public static void processFileNamesInDirectory(String resourceDirectory, AaptResourceCollector resourceCollector) {
        File resourceDirectoryFile = new File(resourceDirectory);
        String directoryName = resourceDirectoryFile.getName();
        int dashIndex = directoryName.indexOf(45);
        if (dashIndex != -1) {
            directoryName = directoryName.substring(0, dashIndex);
        }
        if (AaptUtil.RESOURCE_TYPES.containsKey(directoryName)) {
            throw new AaptUtil$AaptUtilException(new StringBuilder().append(resourceDirectoryFile.getAbsolutePath()).append(" is not a valid resource sub-directory.").toString());
        }
        else {
            File[] fileArray = resourceDirectoryFile.listFiles();
            if (fileArray != null) {
                for (int i1 = 0; i1 < fileArray.length; i1 += 1) {
                    File file = fileArray[i1];
                    if (file.isHidden()) {
                        continue;;
                    }
                    else {
                        String v_49 = file.getName();
                        int dotIndex = v_49.indexOf(46);
                        String resourceName = dotIndex != -1 ? v_49 : v_49;
                        RDotTxtEntry$RType rType = (RDotTxtEntry$RType)AaptUtil.RESOURCE_TYPES.get(directoryName);
                        resourceCollector.addIntResourceIfNotPresent(rType, resourceName);
                        ResourceDirectory resourceDirectoryBean = new ResourceDirectory(file.getParentFile().getName(), file.getAbsolutePath());
                        resourceCollector.addRTypeResourceName(rType, resourceName, null, resourceDirectoryBean);
                    }
                }
            }
        }
    }

    public static void processValues(String resourceDirectory, AaptResourceCollector resourceCollector) {
        File resourceDirectoryFile = new File(resourceDirectory);
        File[] fileArray = resourceDirectoryFile.listFiles();
        if (fileArray != null) {
            for (int i1 = 0; i1 < fileArray.length; i1 += 1) {
                File file = fileArray[i1];
                if (file.isHidden()) {
                    continue;;
                }
                else if (file.isFile()) {
                    continue;;
                }
                else {
                    AaptUtil.processValuesFile(file.getAbsolutePath(), resourceCollector);
                }
            }
        }
    }

    public static void processValuesFile(String valuesFullFilename, AaptResourceCollector resourceCollector) {
        Document document = JavaXmlUtil.parse(valuesFullFilename);
        String directoryName = new File(valuesFullFilename).getParentFile().getName();
        Element root = document.getDocumentElement();
        Node node = root.getFirstChild();
        while (node != null) {
            if (node.getNodeType() != 1) {
                continue;;
            }
            else {
                String resourceType = node.getNodeName();
                if (resourceType.equals("item")) {
                    resourceType = node.getAttributes().getNamedItem("type").getNodeValue();
                    if (resourceType.equals("id")) {
                        resourceCollector.addIgnoreId(node.getAttributes().getNamedItem("name").getNodeValue());
                    }
                }
                if (AaptUtil.IGNORED_TAGS.contains(resourceType)) {
                    continue;;
                }
                else if (AaptUtil.RESOURCE_TYPES.containsKey(resourceType)) {
                    throw new AaptUtil$AaptUtilException(new StringBuilder().append("Invalid resource type '<").append(resourceType).append(">' in '").append(valuesFullFilename).append("'.").toString());
                }
                else {
RDotTxtEntry$RType rType = (RDotTxtEntry$RType)AaptUtil.RESOURCE_TYPES.get(resourceType);
Object resourceValue = null;
                    switch(AaptUtil$1.$SwitchMap$com$tencent$tinker$build$aapt$RDotTxtEntry$RType[rType.ordinal()]) {
                        String str0;
                        case 1: {
                            str0 = node.getTextContent().trim();
                            break;;
                        }
                        case 7: {
                            str0 = AaptUtil.subNodeToString(node);
                            break;;
                        }
                        case 11: {
                            str0 = AaptUtil.nodeToString(node, 1);
                            break;;
                        }
                        case 12: {
                            str0 = AaptUtil.nodeToString(node, 1);
                        }
                    }
                    try {
                        AaptUtil.addToResourceCollector(resourceCollector, new ResourceDirectory(directoryName, valuesFullFilename), node, rType, resourceValue);
                        continue;;
                    }
                    catch (Exception e) {
                        throw new AaptUtil$AaptUtilException(new StringBuilder().append(e.getMessage()).append(",Process file error:").append(valuesFullFilename).toString(), e);
                    }
                }
            }
            node = node.getNextSibling();
        }
    }

    public static void processXmlFile(String xmlFullFilename, List<RDotTxtEntry> references, AaptResourceCollector resourceCollector) {
        String resourceName;
        Document document = JavaXmlUtil.parse(xmlFullFilename);
        NodeList nodesWithIds = (NodeList)AaptUtil.ANDROID_ID_DEFINITION.evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodesWithIds.getLength(); i += 1) {
            resourceName = nodesWithIds.item(i).getNodeValue();
            if (resourceName.startsWith("@+id/")) {
                throw new AaptUtil$AaptUtilException(new StringBuilder().append("Invalid definition of a resource: '").append(resourceName).append("'").toString());
            }
            else {
                resourceCollector.addIntResourceIfNotPresent(RDotTxtEntry$RType.ID, resourceName.substring("@+id/".length()));
            }
        }
        NodeList nodesUsingIds = (NodeList)AaptUtil.ANDROID_ID_USAGE.evaluate(document, XPathConstants.NODESET);
        for (i = 0; i < nodesUsingIds.getLength(); i += 1) {
            resourceName = nodesUsingIds.item(i).getNodeValue();
            int slashPosition = resourceName.indexOf(47);
            if (slashPosition < 0) {
                continue;;
            }
            else {
                String rawRType = resourceName.substring(1, slashPosition);
                String name = resourceName.substring(slashPosition + 1);
                if (name.startsWith("android:")) {
                    continue;;
                }
                else if (rawRType.startsWith("tools:")) {
                    continue;;
                }
                else if (AaptUtil.RESOURCE_TYPES.containsKey(rawRType)) {
                    throw new AaptUtil$AaptUtilException(new StringBuilder().append("Invalid reference '").append(resourceName).append("' in '").append(xmlFullFilename).append("'").toString());
                }
                else {
                    RDotTxtEntry$RType rType = (RDotTxtEntry$RType)AaptUtil.RESOURCE_TYPES.get(rawRType);
                    references.add(new FakeRDotTxtEntry(RDotTxtEntry$IdType.INT, rType, AaptUtil.sanitizeName(rType, resourceCollector, name)));
                }
            }
        }
    }

    private static void addToResourceCollector(AaptResourceCollector resourceCollector, ResourceDirectory resourceDirectory, Node node, RDotTxtEntry$RType rType, String resourceValue) {
        String resourceName = AaptUtil.sanitizeName(rType, resourceCollector, AaptUtil.extractNameAttribute(node));
        resourceCollector.addRTypeResourceName(rType, resourceName, resourceValue, resourceDirectory);
        if (rType.equals(RDotTxtEntry$RType.STYLEABLE)) {
            int count = 0;
            Node attrNode = node.getFirstChild();
            while (attrNode != null) {
                if (attrNode.getNodeType() == 1) {
                    if (attrNode.getNodeName().equals("attr")) {
                        continue;;
                    }
                    else {
                        String rawAttrName = AaptUtil.extractNameAttribute(attrNode);
                        String attrName = AaptUtil.sanitizeName(rType, resourceCollector, rawAttrName);
                        count += 1;
                        resourceCollector.addResource(RDotTxtEntry$RType.STYLEABLE, RDotTxtEntry$IdType.INT, String.format("%s_%s", new Object[]{resourceName, attrName}), Integer.toString(count));
                        if (rawAttrName.startsWith("android:")) {
                            resourceCollector.addIntResourceIfNotPresent(RDotTxtEntry$RType.ATTR, rawAttrName);
                            resourceCollector.addRTypeResourceName(RDotTxtEntry$RType.ATTR, rawAttrName, AaptUtil.nodeToString(attrNode, 1), resourceDirectory);
                        }
                    }
                }
                attrNode = attrNode.getNextSibling();
            }
            resourceCollector.addIntArrayResourceIfNotPresent(rType, resourceName, count);
        }
        else {
            resourceCollector.addIntResourceIfNotPresent(rType, resourceName);
        }
    }

    private static String sanitizeName(RDotTxtEntry$RType rType, AaptResourceCollector resourceCollector, String rawName) {
        String sanitizeName = rawName.replaceAll("[.:]", "_");
        resourceCollector.putSanitizeName(rType, sanitizeName, rawName);
        return sanitizeName;
    }

    private static String extractNameAttribute(Node node) {
        return node.getAttributes().getNamedItem("name").getNodeValue();
    }

    public static Map<String, Map<RDotTxtEntry$RType, Set<RDotTxtEntry>>> mergePackageRTypeResourceMap(List<AaptUtil$PackageRTypeResourceMap> packageRTypeResourceMapList) {
        Map$Entry entry;
        HashMap packageRTypeResourceMergeMap = new HashMap();
        HashMap aaptResourceCollectorMap = new HashMap();
        Iterator iterator = packageRTypeResourceMapList.iterator();
        while (iterator.hasNext()) {
            AaptUtil$PackageRTypeResourceMap packageRTypeResourceMap = (AaptUtil$PackageRTypeResourceMap)iterator.next();
            String packageName = AaptUtil$PackageRTypeResourceMap.access$000(packageRTypeResourceMap);
            Map rTypeResourceMap = AaptUtil$PackageRTypeResourceMap.access$100(packageRTypeResourceMap);
            Object aaptResourceCollector = null;
            AaptResourceCollector collector = aaptResourceCollectorMap.containsKey(packageName) ? new AaptResourceCollector() : (AaptResourceCollector)aaptResourceCollectorMap.get(packageName);
            aaptResourceCollectorMap.put(packageName, collector);
            iterator = rTypeResourceMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = (Map$Entry)iterator.next();
                RDotTxtEntry$RType rType = (RDotTxtEntry$RType)entry.getKey();
                Set rDotTxtEntrySet = (Set)entry.getValue();
                Iterator iteratorVar1 = rDotTxtEntrySet.iterator();
                while (iteratorVar1.hasNext()) {
                    RDotTxtEntry rDotTxtEntry = (RDotTxtEntry)iteratorVar1.next();
                    if (rDotTxtEntry.idType.equals(RDotTxtEntry$IdType.INT)) {
                        var_7_0.addIntResourceIfNotPresent(rType, rDotTxtEntry.name);
                        continue;;
                    }
                    else if (rDotTxtEntry.idType.equals(RDotTxtEntry$IdType.INT_ARRAY)) {
                        var_7_0.addResource(rType, rDotTxtEntry.idType, rDotTxtEntry.name, rDotTxtEntry.idValue.trim());
                    }
                }
            }
        }
        Iterator<Map$Entry<String, AaptResourceCollector>> collector>> = aaptResourceCollectorMap.entrySet().iterator();
        while (collector>>.hasNext()) {
            entry = (Map$Entry)collector>>.next();
            packageRTypeResourceMergeMap.put((String)entry.getKey(), (AaptResourceCollector)entry.getValue().getRTypeResourceMap());
        }
        return packageRTypeResourceMergeMap;
    }

    public static void writeRJava(String outputDirectory, String packageName, Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> rTypeResourceMap, boolean isFinal) {
        String outputFullFilename = new StringBuilder().append(new File(outputDirectory).getAbsolutePath()).append("/").append(packageName.replace(".", "/")).append("/").append("R").append(".").append("java").toString();
        FileUtil.createFile(outputFullFilename);
        Object writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(outputFullFilename));
            writer.format("package %s;

", new Object[]{packageName});
            writer.println("public final class R {
");
            Iterator iterator = rTypeResourceMap.keySet().iterator();
            while (iterator.hasNext()) {
                RDotTxtEntry$RType rType = (RDotTxtEntry$RType)iterator.next();
                writer.format("  public static final class %s {
", new Object[]{rType.toString()});
                Iterator iteratorVar1 = (Set)rTypeResourceMap.get(rType).iterator();
                while (iteratorVar1.hasNext()) {
                    RDotTxtEntry rDotTxtEntry = (RDotTxtEntry)iteratorVar1.next();
                    writer.format("    public static%s%s %s=%s;
", new Object[]{isFinal ? " " : " final ", rDotTxtEntry.idType, rDotTxtEntry.name, rDotTxtEntry.idValue.trim()});
                }
                writer.println("  }
");
            }
            writer.println("}");
            return;
        }
        catch (Exception e) {
            throw new AaptUtil$AaptUtilException(e);
        }
        finally {
            Throwable throwable = v_40;
            IOHelper.closeQuietly(writer);
            throw throwable;
        }
    }

    public static void writeRJava(String outputDirectory, Map<String, Map<RDotTxtEntry$RType, Set<RDotTxtEntry>>> packageRTypeResourceMap, boolean isFinal) {
        Iterator iterator = packageRTypeResourceMap.keySet().iterator();
        while (iterator.hasNext()) {
            String packageName = (String)iterator.next();
            Map rTypeResourceMap = (Map)packageRTypeResourceMap.get(packageName);
            AaptUtil.writeRJava(outputDirectory, packageName, rTypeResourceMap, isFinal);
        }
    }

    private static String subNodeToString(Node node) {
        StringBuilder stringBuilder = new StringBuilder();
        if (node != null) {
            NodeList nodeList = node.getChildNodes();
            stringBuilder.append(AaptUtil.nodeToString(node, 0));
            stringBuilder.append("
");
            int nodeListLength = nodeList.getLength();
            for (int i = 0; i < nodeListLength; i += 1) {
                Node childNode = nodeList.item(i);
                if (childNode.getNodeType() != 1) {
                    continue;;
                }
                else {
                    stringBuilder.append(AaptUtil.nodeToString(childNode, 1));
                    stringBuilder.append("
");
                }
            }
            if (stringBuilder.length() > "
".length()) {
                stringBuilder.delete(stringBuilder.length() - "
".length(), stringBuilder.length());
            }
        }
        return stringBuilder.toString();
    }

    private static String nodeToString(Node node, boolean isNoChild) {
        StringBuilder stringBuilder = new StringBuilder();
        if (node != null) {
            stringBuilder.append(node.getNodeName());
            NamedNodeMap namedNodeMap = node.getAttributes();
            stringBuilder.append("[");
            int namedNodeMapLength = namedNodeMap.getLength();
            for (int j = 0; j < namedNodeMapLength; j += 1) {
                Node attributeNode = namedNodeMap.item(j);
                stringBuilder.append(new StringBuilder().append("@").append(attributeNode.getNodeName()).append("=").append(attributeNode.getNodeValue()).toString());
                if (j < namedNodeMapLength - 1) {
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append("]");
            String value = StringUtil.nullToBlank(isNoChild ? node.getNodeValue() : node.getTextContent()).trim();
            if (StringUtil.isNotBlank(value)) {
                stringBuilder.append(new StringBuilder().append("=").append(value).toString());
            }
        }
        return stringBuilder.toString();
    }

    static  {
        AaptUtil.ANDROID_ID_USAGE = AaptUtil.createExpression("//@*[starts-with(., '@') and not(starts-with(., '@+')) and not(starts-with(., '@android:')) and not(starts-with(., '@null'))]");
        AaptUtil.ANDROID_ID_DEFINITION = AaptUtil.createExpression("//@*[starts-with(., '@+') and not(starts-with(., '@+android:id')) and not(starts-with(., '@+id/android:'))]");
        AaptUtil.RESOURCE_TYPES = AaptUtil.getResourceTypes();
        AaptUtil.IGNORED_TAGS = Arrays.asList(new String[]{"eat-comment", "skip"});
    }

    // class: com/tencent/tinker/build/aapt/AaptUtil$AaptUtilException
    public class AaptUtil$AaptUtilException {
        final private static long serialVersionUID;

        public AaptUtil$AaptUtilException(String message) {
            super(message);
        }

        public AaptUtil$AaptUtilException(Throwable cause) {
            super(cause);
        }

        public AaptUtil$AaptUtilException(String message, Throwable cause) {
            super(message, cause);
        }

    }
    // class: com/tencent/tinker/build/aapt/AaptUtil$AaptUtilException
    public class AaptUtil$AaptUtilException {
        final private static long serialVersionUID;

        public AaptUtil$AaptUtilException(String message) {
            super(message);
        }

        public AaptUtil$AaptUtilException(Throwable cause) {
            super(cause);
        }

        public AaptUtil$AaptUtilException(String message, Throwable cause) {
            super(message, cause);
        }

    }
    // class: com/tencent/tinker/build/aapt/AaptUtil$PackageRTypeResourceMap
    public class AaptUtil$PackageRTypeResourceMap {
        private String packageName;
        private Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> rTypeResourceMap;

        publicvoid AaptUtil$PackageRTypeResourceMap(String packageName, Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> rTypeResourceMap) {
            super();
            this.packageName = null;
            this.rTypeResourceMap = null;
            this.packageName = packageName;
            this.rTypeResourceMap = rTypeResourceMap;
        }

        static /* synthetic */ String access$000(AaptUtil$PackageRTypeResourceMap x0) {
            return x0.packageName;
        }

        static /* synthetic */ Map access$100(AaptUtil$PackageRTypeResourceMap x0) {
            return x0.rTypeResourceMap;
        }

    }
    // class: com/tencent/tinker/build/aapt/AaptUtil$PackageRTypeResourceMap
    public class AaptUtil$PackageRTypeResourceMap {
        private String packageName;
        private Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> rTypeResourceMap;

        publicvoid AaptUtil$PackageRTypeResourceMap(String packageName, Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> rTypeResourceMap) {
            super();
            this.packageName = null;
            this.rTypeResourceMap = null;
            this.packageName = packageName;
            this.rTypeResourceMap = rTypeResourceMap;
        }

        static /* synthetic */ String access$000(AaptUtil$PackageRTypeResourceMap x0) {
            return x0.packageName;
        }

        static /* synthetic */ Map access$100(AaptUtil$PackageRTypeResourceMap x0) {
            return x0.rTypeResourceMap;
        }

    }
}
