/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import java.io.File;
import java.io.FileOutputStream;

// class: com/tencent/tinker/build/aapt/JavaXmlUtil
public final class JavaXmlUtil {

    public JavaXmlUtil() {
        super();
    }

    private static DocumentBuilder getDocumentBuilder() {
        Object documentBuilder = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            builder.setEntityResolver(new JavaXmlUtil$1());
        }
        catch (Exception e) {
            throw new JavaXmlUtil$JavaXmlUtilException(e);
        }
        return builder;
    }

    public static Document getEmptyDocument() {
        Object document = null;
        try {
            DocumentBuilder documentBuilder = JavaXmlUtil.getDocumentBuilder();
            document = documentBuilder.newDocument();
            document.normalize();
        }
        catch (Exception e) {
            throw new JavaXmlUtil$JavaXmlUtilException(e);
        }
        return document;
    }

    public static Document parse(String filename) {
        Object document = null;
        try {
            DocumentBuilder documentBuilder = JavaXmlUtil.getDocumentBuilder();
            document = documentBuilder.parse(new File(filename));
            document.normalize();
        }
        catch (Exception e) {
            throw new JavaXmlUtil$JavaXmlUtilException(e);
        }
        return document;
    }

    public static Document parse(InputStream inputStream) {
        Object document = null;
        try {
            DocumentBuilder documentBuilder = JavaXmlUtil.getDocumentBuilder();
            document = documentBuilder.parse(inputStream);
            document.normalize();
        }
        catch (Exception e) {
            throw new JavaXmlUtil$JavaXmlUtilException(e);
        }
        return document;
    }

    public static void saveDocument(Document document, String outputFullFilename) {
        Object outputStream = null;
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            transformer.setOutputProperty("encoding", "UTF-8");
            FileOutputStream stream = new FileOutputStream(outputFullFilename);
            StreamResult result = new StreamResult(stream);
            transformer.transform(domSource, result);
            return;
        }
        catch (Exception e) {
            throw new JavaXmlUtil$JavaXmlUtilException(e);
        }
        finally {
            Throwable throwable = v_18;
            IOHelper.closeQuietly(stream);
            throw throwable;
        }
    }

    // class: com/tencent/tinker/build/aapt/JavaXmlUtil$JavaXmlUtilException
    public class JavaXmlUtil$JavaXmlUtilException {
        final private static long serialVersionUID;

        public JavaXmlUtil$JavaXmlUtilException(Throwable cause) {
            super(cause);
        }

    }
    // class: com/tencent/tinker/build/aapt/JavaXmlUtil$JavaXmlUtilException
    public class JavaXmlUtil$JavaXmlUtilException {
        final private static long serialVersionUID;

        public JavaXmlUtil$JavaXmlUtilException(Throwable cause) {
            super(cause);
        }

    }
}
