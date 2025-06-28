/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;
import java.util.Map$Entry;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.File;

// class: com/tencent/tinker/build/aapt/PatchUtil
public final class PatchUtil {

    public PatchUtil() {
        super();
    }

    public static Map<RDotTxtEntry$RType, Set<RDotTxtEntry>> readRTxt(String rTxtFullFilename) {
        HashMap rTypeResourceMap = new HashMap();
        if (StringUtil.isNotBlank(rTxtFullFilename) && FileUtil.isExist(rTxtFullFilename)) {
            Object bufferedReader = null;
            try {
                Pattern textSymbolLine = Pattern.compile("(\S+) (\S+) (\S+) (.+)");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(rTxtFullFilename)));
                Object line = null;
                while (true) {
                    String str0 = reader.readLine();
                    if (reader.readLine() != null) {
                        Matcher matcher = textSymbolLine.matcher(str0);
                        if (matcher.matches()) {
                            RDotTxtEntry$IdType idType = RDotTxtEntry$IdType.from(matcher.group(1));
                            RDotTxtEntry$RType rType = RDotTxtEntry$RType.valueOf(matcher.group(2).toUpperCase());
                            String name = matcher.group(3);
                            String idValue = matcher.group(4);
                            RDotTxtEntry rDotTxtEntry = new RDotTxtEntry(idType, rType, name, idValue);
                            Object hashSet = null;
                            HashSet set = rTypeResourceMap.containsKey(rType) ? new HashSet() : (Set)rTypeResourceMap.get(rType);
                            rTypeResourceMap.put(rType, set);
                            var_11_0.add(rDotTxtEntry);
                        }
                    }
                    else {
                        break;;
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                Throwable throwable = v_14;
                IOHelper.closeQuietly(reader);
                throw throwable;
            }
        }
        return rTypeResourceMap;
    }

    public static void generatePublicResourceXml(AaptResourceCollector aaptResourceCollector, String outputIdsXmlFullFilename, String outputPublicXmlFullFilename) {
        if (aaptResourceCollector == null) {
        }
        else {
            FileUtil.createFile(outputIdsXmlFullFilename);
            FileUtil.createFile(outputPublicXmlFullFilename);
            Object idsWriter = null;
            Object publicWriter = null;
            try {
                FileUtil.createFile(outputIdsXmlFullFilename);
                FileUtil.createFile(outputPublicXmlFullFilename);
                PrintWriter writer = new PrintWriter(new File(outputIdsXmlFullFilename), "UTF-8");
                PrintWriter writerVar1 = new PrintWriter(new File(outputPublicXmlFullFilename), "UTF-8");
                writer.println("<?xml version="1.0" encoding="utf-8"?>");
                writerVar1.println("<?xml version="1.0" encoding="utf-8"?>");
                writer.println("<resources>");
                writerVar1.println("<resources>");
                Map map = aaptResourceCollector.getRTypeResourceMap();
                Iterator iterator = map.entrySet().iterator();
                while (v_36 = iterator) {
                    RDotTxtEntry rDotTxtEntry;
                    iterator.hasNext();
                    Map$Entry entry = (Map$Entry)iterator.next();
                    RDotTxtEntry$RType rType = (RDotTxtEntry$RType)entry.getKey();
                    rType.equals(RDotTxtEntry$RType.STYLEABLE);
                    Set set = (Set)entry.getValue();
                    iterator = set.iterator();
                    while (iterator.hasNext()) {
                        rDotTxtEntry = (RDotTxtEntry)iterator.next();
                        String rawName = aaptResourceCollector.getRawName(rType, rDotTxtEntry.name);
                        if (StringUtil.isBlank(rawName)) {
                            rawName = rDotTxtEntry.name;
                        }
                        writerVar1.println(new StringBuilder().append("<public type="").append(rType).append("" name="").append(rawName).append("" id="").append(rDotTxtEntry.idValue.trim()).append("" />").toString());
                    }
                    Set<String> string> = aaptResourceCollector.getIgnoreIdSet();
                    Iterator iteratorVar1 = set.iterator();
                    while (iteratorVar1.hasNext()) {
                        rDotTxtEntry = (RDotTxtEntry)iteratorVar1.next();
                        if (rType.equals(RDotTxtEntry$RType.ID) && string>.contains(rDotTxtEntry.name)) {
                            writer.println(new StringBuilder().append("<item type="").append(rType).append("" name="").append(rDotTxtEntry.name).append(""/>").toString());
                            continue;;
                        }
                        else {
                            ! rType.equals(RDotTxtEntry$RType.STYLE) || rDotTxtEntry.name.indexOf("_") > 0;
                        }
                    }
                    writer.flush();
                    writerVar1.flush();
                }
                writer.println("</resources>");
                writerVar1.println("</resources>");
                return;
            }
            catch (Exception e) {
                throw new PatchUtil$PatchUtilException(e);
            }
            finally {
                Throwable throwable = v_33;
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
                if (writerVar1 != null) {
                    writerVar1.flush();
                    writerVar1.close();
                }
                throw throwable;
            }
        }
    }

    // class: com/tencent/tinker/build/aapt/PatchUtil$PatchUtilException
    public class PatchUtil$PatchUtilException {
        final private static long serialVersionUID;

        public PatchUtil$PatchUtilException(String message) {
            super(message);
        }

        public PatchUtil$PatchUtilException(Throwable cause) {
            super(cause);
        }

        public PatchUtil$PatchUtilException(String message, Throwable cause) {
            super(message, cause);
        }

    }
    // class: com/tencent/tinker/build/aapt/PatchUtil$PatchUtilException
    public class PatchUtil$PatchUtilException {
        final private static long serialVersionUID;

        public PatchUtil$PatchUtilException(String message) {
            super(message);
        }

        public PatchUtil$PatchUtilException(Throwable cause) {
            super(cause);
        }

        public PatchUtil$PatchUtilException(String message, Throwable cause) {
            super(message, cause);
        }

    }
    // class: com/tencent/tinker/build/aapt/PatchUtil$PublicResourceEntry
    public class PatchUtil$PublicResourceEntry {
        private RDotTxtEntry$RType rType;
        private String resourceName;

        public PatchUtil$PublicResourceEntry(RDotTxtEntry$RType rType, String resourceName) {
            super();
            this.rType = null;
            this.resourceName = null;
            this.rType = rType;
            this.resourceName = resourceName;
        }

        public boolean equals(Object obj) {
            if ((obj instanceof PatchUtil$PublicResourceEntry)) {
                return false;
            }
            else {
                PatchUtil$PublicResourceEntry that = (PatchUtil$PublicResourceEntry)obj;
                if (ObjectUtil.equal(this.rType, that.rType) && ObjectUtil.equal(this.resourceName, that.resourceName)) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.rType, this.resourceName});
        }

    }
    // class: com/tencent/tinker/build/aapt/PatchUtil$PublicResourceEntry
    public class PatchUtil$PublicResourceEntry {
        private RDotTxtEntry$RType rType;
        private String resourceName;

        public PatchUtil$PublicResourceEntry(RDotTxtEntry$RType rType, String resourceName) {
            super();
            this.rType = null;
            this.resourceName = null;
            this.rType = rType;
            this.resourceName = resourceName;
        }

        public boolean equals(Object obj) {
            if ((obj instanceof PatchUtil$PublicResourceEntry)) {
                return false;
            }
            else {
                PatchUtil$PublicResourceEntry that = (PatchUtil$PublicResourceEntry)obj;
                if (ObjectUtil.equal(this.rType, that.rType) && ObjectUtil.equal(this.resourceName, that.resourceName)) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.rType, this.resourceName});
        }

    }
}
