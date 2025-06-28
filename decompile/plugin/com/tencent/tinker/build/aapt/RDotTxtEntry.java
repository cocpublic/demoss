/*
 * Decompiled by Garlic
 * Version: 1.0
 */ 
package com/tencent/tinker/build/aapt;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import com.google.common.base.Optional;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.collect.ComparisonChain;

// class: com/tencent/tinker/build/aapt/RDotTxtEntry
public class RDotTxtEntry implements Comparable<RDotTxtEntry> {
    final private static Pattern TEXT_SYMBOLS_LINE;
    final public static Function<String, RDotTxtEntry> TO_ENTRY;
    final public RDotTxtEntry$IdType idType;
    final public RDotTxtEntry$RType type;
    final public String name;
    public String idValue;

    public RDotTxtEntry(RDotTxtEntry$IdType idType, RDotTxtEntry$RType type, String name, String idValue) {
        super();
        this.idType = (RDotTxtEntry$IdType)Preconditions.checkNotNull(idType);
        this.type = (RDotTxtEntry$RType)Preconditions.checkNotNull(type);
        this.name = (String)Preconditions.checkNotNull(name);
        this.idValue = (String)Preconditions.checkNotNull(idValue);
    }

    public static Optional<RDotTxtEntry> parse(String rDotTxtLine) {
        Matcher matcher = RDotTxtEntry.TEXT_SYMBOLS_LINE.matcher(rDotTxtLine);
        if (matcher.matches()) {
            return Optional.absent();
        }
        else {
            RDotTxtEntry$IdType idType = RDotTxtEntry$IdType.from(matcher.group(1));
            RDotTxtEntry$RType type = RDotTxtEntry$RType.valueOf(matcher.group(2).toUpperCase());
            String name = matcher.group(3);
            String idValue = matcher.group(4);
            return Optional.of(new RDotTxtEntry(idType, type, name, idValue));
        }
    }

    public RDotTxtEntry copyWithNewIdValue(String newIdValue) {
        return new RDotTxtEntry(this.idType, this.type, this.name, newIdValue);
    }

    public int compareTo(RDotTxtEntry that) {
        return ComparisonChain.start().compare(this.type, that.type).compare(this.name, that.name).result();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof RDotTxtEntry)) {
            return false;
        }
        else {
            RDotTxtEntry that = (RDotTxtEntry)obj;
            if (Objects.equal(this.type, that.type) && Objects.equal(this.name, that.name)) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.type, this.name});
    }

    public String toString() {
        return MoreObjects.toStringHelper(RDotTxtEntry.class).add("idType", this.idType).add("type", this.type).add("name", this.name).add("idValue", this.idValue.trim()).toString();
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.compareTo((RDotTxtEntry)object);
    }

    static  {
        RDotTxtEntry.TEXT_SYMBOLS_LINE = Pattern.compile("(\S+) (\S+) (\S+) (.+)");
        RDotTxtEntry.TO_ENTRY = new RDotTxtEntry$1();
    }

    // class: com/tencent/tinker/build/aapt/RDotTxtEntry$IdType
    public final enum RDotTxtEntry$IdType {

        privatevoid RDotTxtEntry$IdType(String str0, int i0) {
        }

        public static RDotTxtEntry$IdType from(String raw) {
            if (raw.equals("int")) {
                return RDotTxtEntry$IdType.INT;
            }
            else if (raw.equals("int[]")) {
                return RDotTxtEntry$IdType.INT_ARRAY;
            }
            else {
                throw new IllegalArgumentException(String.format("'%s' is not a valid ID type.", new Object[]{raw}));
            }
        }

        public String toString() {
            if (this.equals(RDotTxtEntry$IdType.INT)) {
                return "int";
            }
            else {
                return "int[]";
            }
        }

        static  {
            RDotTxtEntry$IdType.INT = new RDotTxtEntry$IdType("INT", 0);
            RDotTxtEntry$IdType.INT_ARRAY = new RDotTxtEntry$IdType("INT_ARRAY", 1);
            RDotTxtEntry$IdType.$VALUES = new RDotTxtEntry$IdType[]{RDotTxtEntry$IdType.INT, RDotTxtEntry$IdType.INT_ARRAY};
        }

    }
    // class: com/tencent/tinker/build/aapt/RDotTxtEntry$IdType
    public final enum RDotTxtEntry$IdType {

        privatevoid RDotTxtEntry$IdType(String str0, int i0) {
        }

        public static RDotTxtEntry$IdType from(String raw) {
            if (raw.equals("int")) {
                return RDotTxtEntry$IdType.INT;
            }
            else if (raw.equals("int[]")) {
                return RDotTxtEntry$IdType.INT_ARRAY;
            }
            else {
                throw new IllegalArgumentException(String.format("'%s' is not a valid ID type.", new Object[]{raw}));
            }
        }

        public String toString() {
            if (this.equals(RDotTxtEntry$IdType.INT)) {
                return "int";
            }
            else {
                return "int[]";
            }
        }

        static  {
            RDotTxtEntry$IdType.INT = new RDotTxtEntry$IdType("INT", 0);
            RDotTxtEntry$IdType.INT_ARRAY = new RDotTxtEntry$IdType("INT_ARRAY", 1);
            RDotTxtEntry$IdType.$VALUES = new RDotTxtEntry$IdType[]{RDotTxtEntry$IdType.INT, RDotTxtEntry$IdType.INT_ARRAY};
        }

    }
    // class: com/tencent/tinker/build/aapt/RDotTxtEntry$RType
    public final enum RDotTxtEntry$RType {

        privatevoid RDotTxtEntry$RType(String str0, int i0) {
        }

        public String toString() {
            return super.toString().toLowerCase();
        }

        static  {
            RDotTxtEntry$RType.ANIM = new RDotTxtEntry$RType("ANIM", 0);
            RDotTxtEntry$RType.ANIMATOR = new RDotTxtEntry$RType("ANIMATOR", 1);
            RDotTxtEntry$RType.ARRAY = new RDotTxtEntry$RType("ARRAY", 2);
            RDotTxtEntry$RType.ATTR = new RDotTxtEntry$RType("ATTR", 3);
            RDotTxtEntry$RType.BOOL = new RDotTxtEntry$RType("BOOL", 4);
            RDotTxtEntry$RType.COLOR = new RDotTxtEntry$RType("COLOR", 5);
            RDotTxtEntry$RType.DIMEN = new RDotTxtEntry$RType("DIMEN", 6);
            RDotTxtEntry$RType.DRAWABLE = new RDotTxtEntry$RType("DRAWABLE", 7);
            RDotTxtEntry$RType.FONT = new RDotTxtEntry$RType("FONT", 8);
            RDotTxtEntry$RType.FRACTION = new RDotTxtEntry$RType("FRACTION", 9);
            RDotTxtEntry$RType.ID = new RDotTxtEntry$RType("ID", 10);
            RDotTxtEntry$RType.INTEGER = new RDotTxtEntry$RType("INTEGER", 11);
            RDotTxtEntry$RType.INTERPOLATOR = new RDotTxtEntry$RType("INTERPOLATOR", 12);
            RDotTxtEntry$RType.LAYOUT = new RDotTxtEntry$RType("LAYOUT", 13);
            RDotTxtEntry$RType.MENU = new RDotTxtEntry$RType("MENU", 14);
            RDotTxtEntry$RType.MIPMAP = new RDotTxtEntry$RType("MIPMAP", 15);
            RDotTxtEntry$RType.PLURALS = new RDotTxtEntry$RType("PLURALS", 16);
            RDotTxtEntry$RType.RAW = new RDotTxtEntry$RType("RAW", 17);
            RDotTxtEntry$RType.STRING = new RDotTxtEntry$RType("STRING", 18);
            RDotTxtEntry$RType.STYLE = new RDotTxtEntry$RType("STYLE", 19);
            RDotTxtEntry$RType.STYLEABLE = new RDotTxtEntry$RType("STYLEABLE", 20);
            RDotTxtEntry$RType.TRANSITION = new RDotTxtEntry$RType("TRANSITION", 21);
            RDotTxtEntry$RType.XML = new RDotTxtEntry$RType("XML", 22);
            RDotTxtEntry$RType.NAVIGATION = new RDotTxtEntry$RType("NAVIGATION", 23);
            RDotTxtEntry$RType.$VALUES = new RDotTxtEntry$RType[]{RDotTxtEntry$RType.ANIM, RDotTxtEntry$RType.ANIMATOR, RDotTxtEntry$RType.ARRAY, RDotTxtEntry$RType.ATTR, RDotTxtEntry$RType.BOOL, RDotTxtEntry$RType.COLOR, RDotTxtEntry$RType.DIMEN, RDotTxtEntry$RType.DRAWABLE, RDotTxtEntry$RType.FONT, RDotTxtEntry$RType.FRACTION, RDotTxtEntry$RType.ID, RDotTxtEntry$RType.INTEGER, RDotTxtEntry$RType.INTERPOLATOR, RDotTxtEntry$RType.LAYOUT, RDotTxtEntry$RType.MENU, RDotTxtEntry$RType.MIPMAP, RDotTxtEntry$RType.PLURALS, RDotTxtEntry$RType.RAW, RDotTxtEntry$RType.STRING, RDotTxtEntry$RType.STYLE, RDotTxtEntry$RType.STYLEABLE, RDotTxtEntry$RType.TRANSITION, RDotTxtEntry$RType.XML, RDotTxtEntry$RType.NAVIGATION};
        }

    }
    // class: com/tencent/tinker/build/aapt/RDotTxtEntry$RType
    public final enum RDotTxtEntry$RType {

        privatevoid RDotTxtEntry$RType(String str0, int i0) {
        }

        public String toString() {
            return super.toString().toLowerCase();
        }

        static  {
            RDotTxtEntry$RType.ANIM = new RDotTxtEntry$RType("ANIM", 0);
            RDotTxtEntry$RType.ANIMATOR = new RDotTxtEntry$RType("ANIMATOR", 1);
            RDotTxtEntry$RType.ARRAY = new RDotTxtEntry$RType("ARRAY", 2);
            RDotTxtEntry$RType.ATTR = new RDotTxtEntry$RType("ATTR", 3);
            RDotTxtEntry$RType.BOOL = new RDotTxtEntry$RType("BOOL", 4);
            RDotTxtEntry$RType.COLOR = new RDotTxtEntry$RType("COLOR", 5);
            RDotTxtEntry$RType.DIMEN = new RDotTxtEntry$RType("DIMEN", 6);
            RDotTxtEntry$RType.DRAWABLE = new RDotTxtEntry$RType("DRAWABLE", 7);
            RDotTxtEntry$RType.FONT = new RDotTxtEntry$RType("FONT", 8);
            RDotTxtEntry$RType.FRACTION = new RDotTxtEntry$RType("FRACTION", 9);
            RDotTxtEntry$RType.ID = new RDotTxtEntry$RType("ID", 10);
            RDotTxtEntry$RType.INTEGER = new RDotTxtEntry$RType("INTEGER", 11);
            RDotTxtEntry$RType.INTERPOLATOR = new RDotTxtEntry$RType("INTERPOLATOR", 12);
            RDotTxtEntry$RType.LAYOUT = new RDotTxtEntry$RType("LAYOUT", 13);
            RDotTxtEntry$RType.MENU = new RDotTxtEntry$RType("MENU", 14);
            RDotTxtEntry$RType.MIPMAP = new RDotTxtEntry$RType("MIPMAP", 15);
            RDotTxtEntry$RType.PLURALS = new RDotTxtEntry$RType("PLURALS", 16);
            RDotTxtEntry$RType.RAW = new RDotTxtEntry$RType("RAW", 17);
            RDotTxtEntry$RType.STRING = new RDotTxtEntry$RType("STRING", 18);
            RDotTxtEntry$RType.STYLE = new RDotTxtEntry$RType("STYLE", 19);
            RDotTxtEntry$RType.STYLEABLE = new RDotTxtEntry$RType("STYLEABLE", 20);
            RDotTxtEntry$RType.TRANSITION = new RDotTxtEntry$RType("TRANSITION", 21);
            RDotTxtEntry$RType.XML = new RDotTxtEntry$RType("XML", 22);
            RDotTxtEntry$RType.NAVIGATION = new RDotTxtEntry$RType("NAVIGATION", 23);
            RDotTxtEntry$RType.$VALUES = new RDotTxtEntry$RType[]{RDotTxtEntry$RType.ANIM, RDotTxtEntry$RType.ANIMATOR, RDotTxtEntry$RType.ARRAY, RDotTxtEntry$RType.ATTR, RDotTxtEntry$RType.BOOL, RDotTxtEntry$RType.COLOR, RDotTxtEntry$RType.DIMEN, RDotTxtEntry$RType.DRAWABLE, RDotTxtEntry$RType.FONT, RDotTxtEntry$RType.FRACTION, RDotTxtEntry$RType.ID, RDotTxtEntry$RType.INTEGER, RDotTxtEntry$RType.INTERPOLATOR, RDotTxtEntry$RType.LAYOUT, RDotTxtEntry$RType.MENU, RDotTxtEntry$RType.MIPMAP, RDotTxtEntry$RType.PLURALS, RDotTxtEntry$RType.RAW, RDotTxtEntry$RType.STRING, RDotTxtEntry$RType.STYLE, RDotTxtEntry$RType.STYLEABLE, RDotTxtEntry$RType.TRANSITION, RDotTxtEntry$RType.XML, RDotTxtEntry$RType.NAVIGATION};
        }

    }
}
