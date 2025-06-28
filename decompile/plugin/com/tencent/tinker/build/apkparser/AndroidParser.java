/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/apkparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import tinker.net.dongliu.apk.parser.ApkParser;
import tinker.net.dongliu.apk.parser.struct.resource.ResourceTable;
import tinker.net.dongliu.apk.parser.struct.StringPool;
import tinker.net.dongliu.apk.parser.struct.ResourceValue;
import tinker.net.dongliu.apk.parser.parser.ResourceTableParser;
import tinker.net.dongliu.apk.parser.parser.BinaryXmlParser;
import tinker.net.dongliu.apk.parser.parser.ApkMetaTranslator;
import tinker.net.dongliu.apk.parser.parser.CompositeXmlStreamer;
import tinker.net.dongliu.apk.parser.parser.XmlStreamer;
import tinker.net.dongliu.apk.parser.bean.ApkMeta;
import tinker.net.dongliu.apk.parser.exception.ParserException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

// class: com/tencent/tinker/build/apkparser/AndroidParser
public class AndroidParser {
    final public static int TYPE_SERVICE;
    final public static int TYPE_ACTIVITY;
    final public static int TYPE_BROADCAST_RECEIVER;
    final public static int TYPE_CONTENT_PROVIDER;
    final public List<String> activities;
    final public List<String> receivers;
    final public List<String> services;
    final public List<String> providers;
    final public ApkMeta apkMeta;
    final public String xml;
    final public HashMap<String, String> metaDatas;

    public AndroidParser(ApkMeta apkMeta, String xml) {
        super();
        this.activities = new ArrayList();
        this.receivers = new ArrayList();
        this.services = new ArrayList();
        this.providers = new ArrayList();
        this.metaDatas = new HashMap();
        this.apkMeta = apkMeta;
        this.xml = xml;
        this.parse();
    }

    public static boolean resourceTableLogicalChange(Configuration config) {
        ApkParser parser = new ApkParser(config.mOldApkFile);
        ApkParser newParser = new ApkParser(config.mNewApkFile);
        parser.parseResourceTable();
        newParser.parseResourceTable();
        return parser.getResourceTable().equals(newParser.getResourceTable());
    }

    public static void editResourceTableString(String from, String to, File originFile, File destFile) {
        if (from == null || to == null) {
            return;
        }
        else if (originFile.exists()) {
            throw new RuntimeException(new StringBuilder().append("origin resources.arsc is not exist, path:").append(originFile.getPath()).toString());
        }
        else if (from.length() != to.length()) {
            throw new RuntimeException("only support the same string length now!");
        }
        else {
            ApkParser parser = new ApkParser();
            parser.parseResourceTable(originFile);
            ResourceTable resourceTable = parser.getResourceTable();
            StringPool stringPool = resourceTable.getStringPool();
            ByteBuffer buffer = resourceTable.getBuffers();
            byte[] array = buffer.array();
            int found = 0;
            for (int i = 0; i < stringPool.getPool().length; i += 1) {
                String value = stringPool.get(i);
                if (value.equals(from)) {
                    found = 1;
                    long offset = (Long)stringPool.getPoolOffsets().get(Integer.valueOf(i)).longValue();
                    offset += 2L;
                    byte[] tempByte = stringPool.isUtf8() ? to.getBytes(ParseUtils.charsetUTF16) : to.getBytes(ParseUtils.charsetUTF8);
                    if (to.length() * 2 != tempByte.length) {
                        throw new RuntimeException(String.format("editResourceTableString length is different, name %d, tempByte %d
", new Object[]{Integer.valueOf(to.length()), Integer.valueOf(tempByte.length)}));
                    }
                    System.arraycopy(tempByte, 0, array, (int)offset, tempByte.length);
                }
            }
            if (found == 0) {
                throw new RuntimeException(new StringBuilder().append("can't found string:").append(from).append(" in the resources.arsc file's string pool!").toString());
            }
            else {
                Object fileOutputStream = null;
                try {
                    FileOutputStream stream = new FileOutputStream(destFile);
                    stream.write(array);
                }
                finally {
                    Throwable throwable = v_125;
                    IOHelper.closeQuietly(stream);
                    throw throwable;
                }
            }
        }
    }

    public static AndroidParser getAndroidManifest(File file) {
        Object zf = null;
        try {
            file = new ZipFile(file);
            ByteBuffer arscData = AndroidParser.getZipEntryData(file, "resources.arsc");
            ResourceTableParser resTableParser = new ResourceTableParser(arscData);
            resTableParser.parse();
            ResourceTable resTable = resTableParser.getResourceTable();
            ByteBuffer manifestData = AndroidParser.getZipEntryData(file, "AndroidManifest.xml");
            BinaryXmlParser xmlParser = new BinaryXmlParser(manifestData, resTable);
            ApkMetaTranslator metaTranslator = new ApkMetaTranslator();
            AndroidParser$XmlTranslatorForPatch xmlTranslator = new AndroidParser$XmlTranslatorForPatch(null);
            CompositeXmlStreamer compositeStreamer = new CompositeXmlStreamer(new XmlStreamer[]{metaTranslator, xmlTranslator});
            xmlParser.setXmlStreamer(compositeStreamer);
            xmlParser.parse();
            AndroidParser androidManifest = new AndroidParser(metaTranslator.getApkMeta(), xmlTranslator.getXml());
            if (file != null) {
                try {
                    file.close();
                }
                catch (Throwable var_12_0) {
                }
            }
            return androidManifest;
        }
        finally {
            Throwable throwable = v_37;
            if (file != null) {
                try {
                    file.close();
                }
                catch (Throwable var_14_0) {
                }
            }
            throw throwable;
        }
    }

    private static ByteBuffer getZipEntryData(ZipFile zf, String entryPath) {
        ZipEntry entry = zf.getEntry(entryPath);
        Object is = null;
        try {
            BufferedInputStream stream = new BufferedInputStream(zf.getInputStream(entry));
            byte[] data = Utils.toByteArray(stream);
            ByteBuffer buffer = ByteBuffer.wrap(data);
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (Throwable var_6_0) {
                }
            }
            return buffer;
        }
        finally {
            Throwable throwable = v_13;
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (Throwable var_8_0) {
                }
            }
            throw throwable;
        }
    }

    private static String getAttribute(NamedNodeMap namedNodeMap, String name) {
        Node node = namedNodeMap.getNamedItem(name);
        if (node == null) {
            if (name.startsWith("android:")) {
                name = name.substring("android:".length());
            }
            node = namedNodeMap.getNamedItem(name);
            if (node == null) {
                return null;
            }
        }
        return node.getNodeValue();
    }

    public List<String> getComponents() {
        ArrayList components = new ArrayList();
        components.addAll(this.activities);
        components.addAll(this.services);
        components.addAll(this.receivers);
        components.addAll(this.providers);
        return components;
    }

    private void parse() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            builder.setEntityResolver(new AndroidParser$1(this));
            Document document = builder.parse(new ByteArrayInputStream(this.xml.getBytes("UTF-8")));
            Node manifestNode = document.getElementsByTagName("manifest").item(0);
            NodeList nodes = manifestNode.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i += 1) {
                Node node = nodes.item(i);
                String nodeName = node.getNodeName();
                if (nodeName.equals("application")) {
                    NodeList children = node.getChildNodes();
                    for (int j = 0; j < children.getLength(); j += 1) {
Node child = children.item(j);
String childName = child.getNodeName();
int i5 = -1;
                        switch(childName.hashCode()) {
                            case 1984153269: {
                                if (childName.equals("service")) {
                                    i5 = 0;
                                }
                            }
                            case -1655966961: {
                                if (childName.equals("activity")) {
                                    i5 = 1;
                                }
                            }
                            case -808719889: {
                                if (childName.equals("receiver")) {
                                    i5 = 2;
                                }
                            }
                            case -987494927: {
                                if (childName.equals("provider")) {
                                    i5 = 3;
                                }
                            }
                            case -1115949454: {
                                if (childName.equals("meta-data")) {
                                    i5 = 4;
                                }
                            }
                        }
                        switch(i5) {
                            case 0: {
                                this.services.add(this.getAndroidComponent(child, 1));
                                continue;;
                            }
                            case 1: {
                                this.activities.add(this.getAndroidComponent(child, 2));
                                continue;;
                            }
                            case 2: {
                                this.receivers.add(this.getAndroidComponent(child, 3));
                                continue;;
                            }
                            case 3: {
                                this.providers.add(this.getAndroidComponent(child, 4));
                                continue;;
                            }
                            case 4: {
                                NamedNodeMap attributes = child.getAttributes();
                                this.metaDatas.put(AndroidParser.getAttribute(attributes, "android:name"), AndroidParser.getAttribute(attributes, "android:value"));
                                continue;;
                            }
                        }
                    }
                }
            }
            return;
        }
        catch (Exception e) {
            throw new ParserException("Error parsing AndroidManifest.xml", e);
        }
    }

    private String getAndroidComponent(Node node, int type) {
        NamedNodeMap attributes = node.getAttributes();
        return AndroidParser.getAttribute(attributes, "android:name");
    }

    // class: com/tencent/tinker/build/apkparser/AndroidParser$XmlTranslatorForPatch
    final class AndroidParser$XmlTranslatorForPatch {

        private AndroidParser$XmlTranslatorForPatch() {
            super();
        }

        public void onAttribute(Attribute attribute) {
            ResourceValue attrVal = attribute.getTypedValue();
            if (attrVal != null && (attrVal instanceof ResourceValue$ReferenceResourceValue)) {
                attribute.setValue(attrVal.toString());
            }
            super.onAttribute(attribute);
        }

        /* synthetic */ AndroidParser$XmlTranslatorForPatch(AndroidParser$1 x0) {
            super();
        }

    }
    // class: com/tencent/tinker/build/apkparser/AndroidParser$XmlTranslatorForPatch
    final class AndroidParser$XmlTranslatorForPatch {

        private AndroidParser$XmlTranslatorForPatch() {
            super();
        }

        public void onAttribute(Attribute attribute) {
            ResourceValue attrVal = attribute.getTypedValue();
            if (attrVal != null && (attrVal instanceof ResourceValue$ReferenceResourceValue)) {
                attribute.setValue(attrVal.toString());
            }
            super.onAttribute(attribute);
        }

        /* synthetic */ AndroidParser$XmlTranslatorForPatch(AndroidParser$1 x0) {
            super();
        }

    }
}
