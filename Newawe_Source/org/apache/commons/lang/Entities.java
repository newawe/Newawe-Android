package org.apache.commons.lang;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.media.TransportMediator;
import com.astuetz.PagerSlidingTabStrip;
import com.astuetz.pagerslidingtabstrip.C0302R;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.http.HttpStatus;

class Entities {
    private static final String[][] APOS_ARRAY;
    private static final String[][] BASIC_ARRAY;
    public static final Entities HTML32;
    public static final Entities HTML40;
    static final String[][] HTML40_ARRAY;
    static final String[][] ISO8859_1_ARRAY;
    public static final Entities XML;
    private final EntityMap map;

    interface EntityMap {
        void add(String str, int i);

        String name(int i);

        int value(String str);
    }

    static class ArrayEntityMap implements EntityMap {
        protected final int growBy;
        protected String[] names;
        protected int size;
        protected int[] values;

        public ArrayEntityMap() {
            this.size = 0;
            this.growBy = 100;
            this.names = new String[this.growBy];
            this.values = new int[this.growBy];
        }

        public ArrayEntityMap(int growBy) {
            this.size = 0;
            this.growBy = growBy;
            this.names = new String[growBy];
            this.values = new int[growBy];
        }

        public void add(String name, int value) {
            ensureCapacity(this.size + 1);
            this.names[this.size] = name;
            this.values[this.size] = value;
            this.size++;
        }

        protected void ensureCapacity(int capacity) {
            if (capacity > this.names.length) {
                int newSize = Math.max(capacity, this.size + this.growBy);
                String[] newNames = new String[newSize];
                System.arraycopy(this.names, 0, newNames, 0, this.size);
                this.names = newNames;
                int[] newValues = new int[newSize];
                System.arraycopy(this.values, 0, newValues, 0, this.size);
                this.values = newValues;
            }
        }

        public String name(int value) {
            for (int i = 0; i < this.size; i++) {
                if (this.values[i] == value) {
                    return this.names[i];
                }
            }
            return null;
        }

        public int value(String name) {
            for (int i = 0; i < this.size; i++) {
                if (this.names[i].equals(name)) {
                    return this.values[i];
                }
            }
            return -1;
        }
    }

    static abstract class MapIntMap implements EntityMap {
        protected final Map mapNameToValue;
        protected final Map mapValueToName;

        MapIntMap(Map nameToValue, Map valueToName) {
            this.mapNameToValue = nameToValue;
            this.mapValueToName = valueToName;
        }

        public void add(String name, int value) {
            this.mapNameToValue.put(name, new Integer(value));
            this.mapValueToName.put(new Integer(value), name);
        }

        public String name(int value) {
            return (String) this.mapValueToName.get(new Integer(value));
        }

        public int value(String name) {
            Object value = this.mapNameToValue.get(name);
            if (value == null) {
                return -1;
            }
            return ((Integer) value).intValue();
        }
    }

    static class PrimitiveEntityMap implements EntityMap {
        private final Map mapNameToValue;
        private final IntHashMap mapValueToName;

        PrimitiveEntityMap() {
            this.mapNameToValue = new HashMap();
            this.mapValueToName = new IntHashMap();
        }

        public void add(String name, int value) {
            this.mapNameToValue.put(name, new Integer(value));
            this.mapValueToName.put(value, name);
        }

        public String name(int value) {
            return (String) this.mapValueToName.get(value);
        }

        public int value(String name) {
            Object value = this.mapNameToValue.get(name);
            if (value == null) {
                return -1;
            }
            return ((Integer) value).intValue();
        }
    }

    static class BinaryEntityMap extends ArrayEntityMap {
        public BinaryEntityMap(int growBy) {
            super(growBy);
        }

        private int binarySearch(int key) {
            int low = 0;
            int high = this.size - 1;
            while (low <= high) {
                int i = (low + high) >>> 1;
                int midVal = this.values[i];
                if (midVal < key) {
                    low = i + 1;
                } else if (midVal <= key) {
                    return i;
                } else {
                    high = i - 1;
                }
            }
            return -(low + 1);
        }

        public void add(String name, int value) {
            ensureCapacity(this.size + 1);
            int insertAt = binarySearch(value);
            if (insertAt <= 0) {
                insertAt = -(insertAt + 1);
                System.arraycopy(this.values, insertAt, this.values, insertAt + 1, this.size - insertAt);
                this.values[insertAt] = value;
                System.arraycopy(this.names, insertAt, this.names, insertAt + 1, this.size - insertAt);
                this.names[insertAt] = name;
                this.size++;
            }
        }

        public String name(int value) {
            int index = binarySearch(value);
            if (index < 0) {
                return null;
            }
            return this.names[index];
        }
    }

    static class HashEntityMap extends MapIntMap {
        public HashEntityMap() {
            super(new HashMap(), new HashMap());
        }
    }

    static class LookupEntityMap extends PrimitiveEntityMap {
        private static final int LOOKUP_TABLE_SIZE = 256;
        private String[] lookupTable;

        LookupEntityMap() {
        }

        public String name(int value) {
            if (value < LOOKUP_TABLE_SIZE) {
                return lookupTable()[value];
            }
            return super.name(value);
        }

        private String[] lookupTable() {
            if (this.lookupTable == null) {
                createLookupTable();
            }
            return this.lookupTable;
        }

        private void createLookupTable() {
            this.lookupTable = new String[LOOKUP_TABLE_SIZE];
            for (int i = 0; i < LOOKUP_TABLE_SIZE; i++) {
                this.lookupTable[i] = super.name(i);
            }
        }
    }

    static class TreeEntityMap extends MapIntMap {
        public TreeEntityMap() {
            super(new TreeMap(), new TreeMap());
        }
    }

    static {
        r3 = new String[4][];
        r3[0] = new String[]{"quot", "34"};
        r3[1] = new String[]{"amp", "38"};
        r3[2] = new String[]{"lt", "60"};
        r3[3] = new String[]{"gt", "62"};
        BASIC_ARRAY = r3;
        r3 = new String[1][];
        r3[0] = new String[]{"apos", "39"};
        APOS_ARRAY = r3;
        r3 = new String[96][];
        r3[0] = new String[]{"nbsp", "160"};
        r3[1] = new String[]{"iexcl", "161"};
        r3[2] = new String[]{"cent", "162"};
        r3[3] = new String[]{"pound", "163"};
        r3[4] = new String[]{"curren", "164"};
        r3[5] = new String[]{"yen", "165"};
        r3[6] = new String[]{"brvbar", "166"};
        r3[7] = new String[]{"sect", "167"};
        r3[8] = new String[]{"uml", "168"};
        r3[9] = new String[]{"copy", "169"};
        r3[10] = new String[]{"ordf", "170"};
        r3[11] = new String[]{"laquo", "171"};
        r3[12] = new String[]{"not", "172"};
        r3[13] = new String[]{"shy", "173"};
        r3[14] = new String[]{"reg", "174"};
        r3[15] = new String[]{"macr", "175"};
        r3[16] = new String[]{"deg", "176"};
        r3[17] = new String[]{"plusmn", "177"};
        r3[18] = new String[]{"sup2", "178"};
        r3[19] = new String[]{"sup3", "179"};
        r3[20] = new String[]{"acute", "180"};
        r3[21] = new String[]{"micro", "181"};
        r3[22] = new String[]{"para", "182"};
        r3[23] = new String[]{"middot", "183"};
        r3[24] = new String[]{"cedil", "184"};
        r3[25] = new String[]{"sup1", "185"};
        r3[26] = new String[]{"ordm", "186"};
        r3[27] = new String[]{"raquo", "187"};
        r3[28] = new String[]{"frac14", "188"};
        r3[29] = new String[]{"frac12", "189"};
        r3[30] = new String[]{"frac34", "190"};
        r3[31] = new String[]{"iquest", "191"};
        r3[32] = new String[]{"Agrave", "192"};
        r3[33] = new String[]{"Aacute", "193"};
        r3[34] = new String[]{"Acirc", "194"};
        r3[35] = new String[]{"Atilde", "195"};
        r3[36] = new String[]{"Auml", "196"};
        r3[37] = new String[]{"Aring", "197"};
        r3[38] = new String[]{"AElig", "198"};
        r3[39] = new String[]{"Ccedil", "199"};
        r3[40] = new String[]{"Egrave", "200"};
        r3[41] = new String[]{"Eacute", "201"};
        r3[42] = new String[]{"Ecirc", "202"};
        r3[43] = new String[]{"Euml", "203"};
        r3[44] = new String[]{"Igrave", "204"};
        r3[45] = new String[]{"Iacute", "205"};
        r3[46] = new String[]{"Icirc", "206"};
        r3[47] = new String[]{"Iuml", "207"};
        r3[48] = new String[]{"ETH", "208"};
        r3[49] = new String[]{"Ntilde", "209"};
        r3[50] = new String[]{"Ograve", "210"};
        r3[51] = new String[]{"Oacute", "211"};
        r3[52] = new String[]{"Ocirc", "212"};
        r3[53] = new String[]{"Otilde", "213"};
        r3[54] = new String[]{"Ouml", "214"};
        r3[55] = new String[]{"times", "215"};
        r3[56] = new String[]{"Oslash", "216"};
        r3[57] = new String[]{"Ugrave", "217"};
        r3[58] = new String[]{"Uacute", "218"};
        r3[59] = new String[]{"Ucirc", "219"};
        r3[60] = new String[]{"Uuml", "220"};
        r3[61] = new String[]{"Yacute", "221"};
        r3[62] = new String[]{"THORN", "222"};
        r3[63] = new String[]{"szlig", "223"};
        r3[64] = new String[]{"agrave", "224"};
        r3[65] = new String[]{"aacute", "225"};
        r3[66] = new String[]{"acirc", "226"};
        r3[67] = new String[]{"atilde", "227"};
        r3[68] = new String[]{"auml", "228"};
        r3[69] = new String[]{"aring", "229"};
        r3[70] = new String[]{"aelig", "230"};
        r3[71] = new String[]{"ccedil", "231"};
        r3[72] = new String[]{"egrave", "232"};
        r3[73] = new String[]{"eacute", "233"};
        r3[74] = new String[]{"ecirc", "234"};
        r3[75] = new String[]{"euml", "235"};
        r3[76] = new String[]{"igrave", "236"};
        r3[77] = new String[]{"iacute", "237"};
        r3[78] = new String[]{"icirc", "238"};
        r3[79] = new String[]{"iuml", "239"};
        r3[80] = new String[]{"eth", "240"};
        r3[81] = new String[]{"ntilde", "241"};
        r3[82] = new String[]{"ograve", "242"};
        r3[83] = new String[]{"oacute", "243"};
        r3[84] = new String[]{"ocirc", "244"};
        r3[85] = new String[]{"otilde", "245"};
        r3[86] = new String[]{"ouml", "246"};
        r3[87] = new String[]{"divide", "247"};
        r3[88] = new String[]{"oslash", "248"};
        r3[89] = new String[]{"ugrave", "249"};
        r3[90] = new String[]{"uacute", "250"};
        r3[91] = new String[]{"ucirc", "251"};
        r3[92] = new String[]{"uuml", "252"};
        r3[93] = new String[]{"yacute", "253"};
        r3[94] = new String[]{"thorn", "254"};
        r3[95] = new String[]{"yuml", "255"};
        ISO8859_1_ARRAY = r3;
        r3 = new String[151][];
        r3[0] = new String[]{"fnof", "402"};
        r3[1] = new String[]{"Alpha", "913"};
        r3[2] = new String[]{"Beta", "914"};
        r3[3] = new String[]{"Gamma", "915"};
        r3[4] = new String[]{"Delta", "916"};
        r3[5] = new String[]{"Epsilon", "917"};
        r3[6] = new String[]{"Zeta", "918"};
        r3[7] = new String[]{"Eta", "919"};
        r3[8] = new String[]{"Theta", "920"};
        r3[9] = new String[]{"Iota", "921"};
        r3[10] = new String[]{"Kappa", "922"};
        r3[11] = new String[]{"Lambda", "923"};
        r3[12] = new String[]{"Mu", "924"};
        r3[13] = new String[]{"Nu", "925"};
        r3[14] = new String[]{"Xi", "926"};
        r3[15] = new String[]{"Omicron", "927"};
        r3[16] = new String[]{"Pi", "928"};
        r3[17] = new String[]{"Rho", "929"};
        r3[18] = new String[]{"Sigma", "931"};
        r3[19] = new String[]{"Tau", "932"};
        r3[20] = new String[]{"Upsilon", "933"};
        r3[21] = new String[]{"Phi", "934"};
        r3[22] = new String[]{"Chi", "935"};
        r3[23] = new String[]{"Psi", "936"};
        r3[24] = new String[]{"Omega", "937"};
        r3[25] = new String[]{"alpha", "945"};
        r3[26] = new String[]{"beta", "946"};
        r3[27] = new String[]{"gamma", "947"};
        r3[28] = new String[]{"delta", "948"};
        r3[29] = new String[]{"epsilon", "949"};
        r3[30] = new String[]{"zeta", "950"};
        r3[31] = new String[]{"eta", "951"};
        r3[32] = new String[]{"theta", "952"};
        r3[33] = new String[]{"iota", "953"};
        r3[34] = new String[]{"kappa", "954"};
        r3[35] = new String[]{"lambda", "955"};
        r3[36] = new String[]{"mu", "956"};
        r3[37] = new String[]{"nu", "957"};
        r3[38] = new String[]{"xi", "958"};
        r3[39] = new String[]{"omicron", "959"};
        r3[40] = new String[]{"pi", "960"};
        r3[41] = new String[]{"rho", "961"};
        r3[42] = new String[]{"sigmaf", "962"};
        r3[43] = new String[]{"sigma", "963"};
        r3[44] = new String[]{"tau", "964"};
        r3[45] = new String[]{"upsilon", "965"};
        r3[46] = new String[]{"phi", "966"};
        r3[47] = new String[]{"chi", "967"};
        r3[48] = new String[]{"psi", "968"};
        r3[49] = new String[]{"omega", "969"};
        r3[50] = new String[]{"thetasym", "977"};
        r3[51] = new String[]{"upsih", "978"};
        r3[52] = new String[]{"piv", "982"};
        r3[53] = new String[]{"bull", "8226"};
        r3[54] = new String[]{"hellip", "8230"};
        r3[55] = new String[]{"prime", "8242"};
        r3[56] = new String[]{"Prime", "8243"};
        r3[57] = new String[]{"oline", "8254"};
        r3[58] = new String[]{"frasl", "8260"};
        r3[59] = new String[]{"weierp", "8472"};
        r3[60] = new String[]{"image", "8465"};
        r3[61] = new String[]{"real", "8476"};
        r3[62] = new String[]{"trade", "8482"};
        r3[63] = new String[]{"alefsym", "8501"};
        r3[64] = new String[]{"larr", "8592"};
        r3[65] = new String[]{"uarr", "8593"};
        r3[66] = new String[]{"rarr", "8594"};
        r3[67] = new String[]{"darr", "8595"};
        r3[68] = new String[]{"harr", "8596"};
        r3[69] = new String[]{"crarr", "8629"};
        r3[70] = new String[]{"lArr", "8656"};
        r3[71] = new String[]{"uArr", "8657"};
        r3[72] = new String[]{"rArr", "8658"};
        r3[73] = new String[]{"dArr", "8659"};
        r3[74] = new String[]{"hArr", "8660"};
        r3[75] = new String[]{"forall", "8704"};
        r3[76] = new String[]{"part", "8706"};
        r3[77] = new String[]{"exist", "8707"};
        r3[78] = new String[]{"empty", "8709"};
        r3[79] = new String[]{"nabla", "8711"};
        r3[80] = new String[]{"isin", "8712"};
        r3[81] = new String[]{"notin", "8713"};
        r3[82] = new String[]{"ni", "8715"};
        r3[83] = new String[]{"prod", "8719"};
        r3[84] = new String[]{"sum", "8721"};
        r3[85] = new String[]{"minus", "8722"};
        r3[86] = new String[]{"lowast", "8727"};
        r3[87] = new String[]{"radic", "8730"};
        r3[88] = new String[]{"prop", "8733"};
        r3[89] = new String[]{"infin", "8734"};
        r3[90] = new String[]{"ang", "8736"};
        r3[91] = new String[]{"and", "8743"};
        r3[92] = new String[]{"or", "8744"};
        r3[93] = new String[]{"cap", "8745"};
        r3[94] = new String[]{"cup", "8746"};
        r3[95] = new String[]{SchemaSymbols.ATTVAL_INT, "8747"};
        r3[96] = new String[]{"there4", "8756"};
        r3[97] = new String[]{"sim", "8764"};
        r3[98] = new String[]{"cong", "8773"};
        r3[99] = new String[]{"asymp", "8776"};
        r3[100] = new String[]{"ne", "8800"};
        r3[HttpStatus.SC_SWITCHING_PROTOCOLS] = new String[]{"equiv", "8801"};
        r3[HttpStatus.SC_PROCESSING] = new String[]{"le", "8804"};
        r3[C0302R.styleable.Theme_checkedTextViewStyle] = new String[]{"ge", "8805"};
        r3[C0302R.styleable.Theme_editTextStyle] = new String[]{"sub", "8834"};
        r3[C0302R.styleable.Theme_radioButtonStyle] = new String[]{"sup", "8835"};
        r3[C0302R.styleable.Theme_ratingBarStyle] = new String[]{"sube", "8838"};
        r3[C0302R.styleable.Theme_seekBarStyle] = new String[]{"supe", "8839"};
        r3[C0302R.styleable.Theme_spinnerStyle] = new String[]{"oplus", "8853"};
        r3[C0302R.styleable.Theme_switchStyle] = new String[]{"otimes", "8855"};
        r3[110] = new String[]{"perp", "8869"};
        r3[111] = new String[]{"sdot", "8901"};
        r3[112] = new String[]{"lceil", "8968"};
        r3[113] = new String[]{"rceil", "8969"};
        r3[114] = new String[]{"lfloor", "8970"};
        r3[115] = new String[]{"rfloor", "8971"};
        r3[116] = new String[]{"lang", "9001"};
        r3[117] = new String[]{"rang", "9002"};
        r3[118] = new String[]{"loz", "9674"};
        r3[119] = new String[]{"spades", "9824"};
        r3[120] = new String[]{"clubs", "9827"};
        r3[121] = new String[]{"hearts", "9829"};
        r3[122] = new String[]{"diams", "9830"};
        r3[123] = new String[]{"OElig", "338"};
        r3[124] = new String[]{"oelig", "339"};
        r3[125] = new String[]{"Scaron", "352"};
        r3[TransportMediator.KEYCODE_MEDIA_PLAY] = new String[]{"scaron", "353"};
        r3[TransportMediator.KEYCODE_MEDIA_PAUSE] = new String[]{"Yuml", "376"};
        r3[TransportMediator.FLAG_KEY_MEDIA_NEXT] = new String[]{"circ", "710"};
        r3[129] = new String[]{"tilde", "732"};
        r3[TransportMediator.KEYCODE_MEDIA_RECORD] = new String[]{"ensp", "8194"};
        r3[131] = new String[]{"emsp", "8195"};
        r3[132] = new String[]{"thinsp", "8201"};
        r3[133] = new String[]{"zwnj", "8204"};
        r3[134] = new String[]{"zwj", "8205"};
        r3[135] = new String[]{"lrm", "8206"};
        r3[136] = new String[]{"rlm", "8207"};
        r3[137] = new String[]{"ndash", "8211"};
        r3[138] = new String[]{"mdash", "8212"};
        r3[139] = new String[]{"lsquo", "8216"};
        r3[140] = new String[]{"rsquo", "8217"};
        r3[141] = new String[]{"sbquo", "8218"};
        r3[142] = new String[]{"ldquo", "8220"};
        r3[143] = new String[]{"rdquo", "8221"};
        r3[144] = new String[]{"bdquo", "8222"};
        r3[145] = new String[]{"dagger", "8224"};
        r3[146] = new String[]{"Dagger", "8225"};
        r3[147] = new String[]{"permil", "8240"};
        r3[148] = new String[]{"lsaquo", "8249"};
        r3[149] = new String[]{"rsaquo", "8250"};
        r3[PagerSlidingTabStrip.DEF_VALUE_TAB_TEXT_ALPHA] = new String[]{"euro", "8364"};
        HTML40_ARRAY = r3;
        Entities xml = new Entities();
        xml.addEntities(BASIC_ARRAY);
        xml.addEntities(APOS_ARRAY);
        XML = xml;
        Entities html32 = new Entities();
        html32.addEntities(BASIC_ARRAY);
        html32.addEntities(ISO8859_1_ARRAY);
        HTML32 = html32;
        Entities html40 = new Entities();
        fillWithHtml40Entities(html40);
        HTML40 = html40;
    }

    static void fillWithHtml40Entities(Entities entities) {
        entities.addEntities(BASIC_ARRAY);
        entities.addEntities(ISO8859_1_ARRAY);
        entities.addEntities(HTML40_ARRAY);
    }

    public Entities() {
        this.map = new LookupEntityMap();
    }

    Entities(EntityMap emap) {
        this.map = emap;
    }

    public void addEntities(String[][] entityArray) {
        for (int i = 0; i < entityArray.length; i++) {
            addEntity(entityArray[i][0], Integer.parseInt(entityArray[i][1]));
        }
    }

    public void addEntity(String name, int value) {
        this.map.add(name, value);
    }

    public String entityName(int value) {
        return this.map.name(value);
    }

    public int entityValue(String name) {
        return this.map.value(name);
    }

    public String escape(String str) {
        StringWriter stringWriter = createStringWriter(str);
        try {
            escape(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public void escape(Writer writer, String str) throws IOException {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            String entityName = entityName(c);
            if (entityName != null) {
                writer.write(38);
                writer.write(entityName);
                writer.write(59);
            } else if (c > '\u007f') {
                writer.write("&#");
                writer.write(Integer.toString(c, 10));
                writer.write(59);
            } else {
                writer.write(c);
            }
        }
    }

    public String unescape(String str) {
        int firstAmp = str.indexOf(38);
        if (firstAmp < 0) {
            return str;
        }
        StringWriter stringWriter = createStringWriter(str);
        try {
            doUnescape(stringWriter, str, firstAmp);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    private StringWriter createStringWriter(String str) {
        return new StringWriter((int) (((double) str.length()) + (((double) str.length()) * 0.1d)));
    }

    public void unescape(Writer writer, String str) throws IOException {
        int firstAmp = str.indexOf(38);
        if (firstAmp < 0) {
            writer.write(str);
        } else {
            doUnescape(writer, str, firstAmp);
        }
    }

    private void doUnescape(Writer writer, String str, int firstAmp) throws IOException {
        writer.write(str, 0, firstAmp);
        int len = str.length();
        int i = firstAmp;
        while (i < len) {
            char c = str.charAt(i);
            if (c == '&') {
                int nextIdx = i + 1;
                int semiColonIdx = str.indexOf(59, nextIdx);
                if (semiColonIdx == -1) {
                    writer.write(c);
                } else {
                    int amphersandIdx = str.indexOf(38, i + 1);
                    if (amphersandIdx == -1 || amphersandIdx >= semiColonIdx) {
                        String entityContent = str.substring(nextIdx, semiColonIdx);
                        int entityValue = -1;
                        int entityContentLen = entityContent.length();
                        if (entityContentLen > 0) {
                            if (entityContent.charAt(0) != '#') {
                                entityValue = entityValue(entityContent);
                            } else if (entityContentLen > 1) {
                                switch (entityContent.charAt(1)) {
                                    case C0302R.styleable.Theme_colorButtonNormal /*88*/:
                                    case 'x':
                                        entityValue = Integer.parseInt(entityContent.substring(2), 16);
                                        break;
                                    default:
                                        try {
                                            entityValue = Integer.parseInt(entityContent.substring(1), 10);
                                            break;
                                        } catch (NumberFormatException e) {
                                            entityValue = -1;
                                            break;
                                        }
                                }
                                if (entityValue > SupportMenu.USER_MASK) {
                                    entityValue = -1;
                                }
                            }
                        }
                        if (entityValue == -1) {
                            writer.write(38);
                            writer.write(entityContent);
                            writer.write(59);
                        } else {
                            writer.write(entityValue);
                        }
                        i = semiColonIdx;
                    } else {
                        writer.write(c);
                    }
                }
            } else {
                writer.write(c);
            }
            i++;
        }
    }
}
