package mf.org.apache.xml.serialize;

import android.support.v4.internal.view.SupportMenu;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Locale;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.protocol.HTTP;

public final class HTMLdtd {
    private static final int ALLOWED_HEAD = 32;
    private static final int CLOSE_DD_DT = 128;
    private static final int CLOSE_P = 64;
    private static final int CLOSE_SELF = 256;
    private static final int CLOSE_TABLE = 512;
    private static final int CLOSE_TH_TD = 16384;
    private static final int ELEM_CONTENT = 2;
    private static final int EMPTY = 17;
    private static final String ENTITIES_RESOURCE = "HTMLEntities.res";
    public static final String HTMLPublicId = "-//W3C//DTD HTML 4.01//EN";
    public static final String HTMLSystemId = "http://www.w3.org/TR/html4/strict.dtd";
    private static final int ONLY_OPENING = 1;
    private static final int OPT_CLOSING = 8;
    private static final int PRESERVE = 4;
    public static final String XHTMLPublicId = "-//W3C//DTD XHTML 1.0 Strict//EN";
    public static final String XHTMLSystemId = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
    private static Hashtable _boolAttrs;
    private static Hashtable _byChar;
    private static Hashtable _byName;
    private static Hashtable _elemDefs;

    public static boolean isEmptyTag(String tagName) {
        return isElement(tagName, EMPTY);
    }

    public static boolean isElementContent(String tagName) {
        return isElement(tagName, ELEM_CONTENT);
    }

    public static boolean isPreserveSpace(String tagName) {
        return isElement(tagName, PRESERVE);
    }

    public static boolean isOptionalClosing(String tagName) {
        return isElement(tagName, OPT_CLOSING);
    }

    public static boolean isOnlyOpening(String tagName) {
        return isElement(tagName, ONLY_OPENING);
    }

    public static boolean isClosing(String tagName, String openTag) {
        if (openTag.equalsIgnoreCase(HttpHead.METHOD_NAME)) {
            if (isElement(tagName, ALLOWED_HEAD)) {
                return false;
            }
            return true;
        } else if (openTag.equalsIgnoreCase("P")) {
            return isElement(tagName, CLOSE_P);
        } else {
            if (openTag.equalsIgnoreCase("DT") || openTag.equalsIgnoreCase("DD")) {
                return isElement(tagName, CLOSE_DD_DT);
            }
            if (openTag.equalsIgnoreCase("LI") || openTag.equalsIgnoreCase("OPTION")) {
                return isElement(tagName, CLOSE_SELF);
            }
            if (openTag.equalsIgnoreCase("THEAD") || openTag.equalsIgnoreCase("TFOOT") || openTag.equalsIgnoreCase("TBODY") || openTag.equalsIgnoreCase("TR") || openTag.equalsIgnoreCase("COLGROUP")) {
                return isElement(tagName, CLOSE_TABLE);
            }
            if (openTag.equalsIgnoreCase("TH") || openTag.equalsIgnoreCase("TD")) {
                return isElement(tagName, CLOSE_TH_TD);
            }
            return false;
        }
    }

    public static boolean isURI(String tagName, String attrName) {
        return attrName.equalsIgnoreCase("href") || attrName.equalsIgnoreCase("src");
    }

    public static boolean isBoolean(String tagName, String attrName) {
        String[] attrNames = (String[]) _boolAttrs.get(tagName.toUpperCase(Locale.ENGLISH));
        if (attrNames == null) {
            return false;
        }
        for (int i = 0; i < attrNames.length; i += ONLY_OPENING) {
            if (attrNames[i].equalsIgnoreCase(attrName)) {
                return true;
            }
        }
        return false;
    }

    public static int charFromName(String name) {
        initialize();
        Object value = _byName.get(name);
        if (value == null || !(value instanceof Integer)) {
            return -1;
        }
        return ((Integer) value).intValue();
    }

    public static String fromChar(int value) {
        if (value > SupportMenu.USER_MASK) {
            return null;
        }
        initialize();
        return (String) _byChar.get(new Integer(value));
    }

    private static void initialize() {
        BufferedReader bufferedReader;
        Exception except;
        Throwable th;
        InputStream is = null;
        if (_byName == null) {
            Object[] objArr;
            try {
                _byName = new Hashtable();
                _byChar = new Hashtable();
                is = HTMLdtd.class.getResourceAsStream(ENTITIES_RESOURCE);
                if (is == null) {
                    objArr = new Object[ONLY_OPENING];
                    objArr[0] = ENTITIES_RESOURCE;
                    throw new RuntimeException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ResourceNotFound", objArr));
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, HTTP.ASCII));
                try {
                    String line = reader.readLine();
                    while (line != null) {
                        if (line.length() == 0 || line.charAt(0) == '#') {
                            line = reader.readLine();
                        } else {
                            int index = line.indexOf(ALLOWED_HEAD);
                            if (index > ONLY_OPENING) {
                                String name = line.substring(0, index);
                                index += ONLY_OPENING;
                                if (index < line.length()) {
                                    String value = line.substring(index);
                                    index = value.indexOf(ALLOWED_HEAD);
                                    if (index > 0) {
                                        value = value.substring(0, index);
                                    }
                                    defineEntity(name, (char) Integer.parseInt(value));
                                }
                            }
                            line = reader.readLine();
                        }
                    }
                    is.close();
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Exception e) {
                        }
                    }
                    bufferedReader = reader;
                } catch (Exception e2) {
                    except = e2;
                    bufferedReader = reader;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = reader;
                }
            } catch (Exception e3) {
                except = e3;
                try {
                    objArr = new Object[ELEM_CONTENT];
                    objArr[0] = ENTITIES_RESOURCE;
                    objArr[ONLY_OPENING] = except.toString();
                    throw new RuntimeException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ResourceNotLoaded", objArr));
                } catch (Throwable th3) {
                    th = th3;
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            }
        }
    }

    private static void defineEntity(String name, char value) {
        if (_byName.get(name) == null) {
            _byName.put(name, new Integer(value));
            _byChar.put(new Integer(value), name);
        }
    }

    private static void defineElement(String name, int flags) {
        _elemDefs.put(name, new Integer(flags));
    }

    private static void defineBoolean(String tagName, String attrName) {
        String[] strArr = new String[ONLY_OPENING];
        strArr[0] = attrName;
        defineBoolean(tagName, strArr);
    }

    private static void defineBoolean(String tagName, String[] attrNames) {
        _boolAttrs.put(tagName, attrNames);
    }

    private static boolean isElement(String name, int flag) {
        Integer flags = (Integer) _elemDefs.get(name.toUpperCase(Locale.ENGLISH));
        if (flags != null && (flags.intValue() & flag) == flag) {
            return true;
        }
        return false;
    }

    static {
        _elemDefs = new Hashtable();
        defineElement("ADDRESS", CLOSE_P);
        defineElement("AREA", EMPTY);
        defineElement("BASE", 49);
        defineElement("BASEFONT", EMPTY);
        defineElement("BLOCKQUOTE", CLOSE_P);
        defineElement("BODY", OPT_CLOSING);
        defineElement("BR", EMPTY);
        defineElement("COL", EMPTY);
        defineElement("COLGROUP", 522);
        defineElement("DD", 137);
        defineElement("DIV", CLOSE_P);
        defineElement("DL", 66);
        defineElement("DT", 137);
        defineElement("FIELDSET", CLOSE_P);
        defineElement("FORM", CLOSE_P);
        defineElement("FRAME", 25);
        defineElement("H1", CLOSE_P);
        defineElement("H2", CLOSE_P);
        defineElement("H3", CLOSE_P);
        defineElement("H4", CLOSE_P);
        defineElement("H5", CLOSE_P);
        defineElement("H6", CLOSE_P);
        defineElement(HttpHead.METHOD_NAME, 10);
        defineElement("HR", 81);
        defineElement("HTML", 10);
        defineElement("IMG", EMPTY);
        defineElement("INPUT", EMPTY);
        defineElement("ISINDEX", 49);
        defineElement("LI", 265);
        defineElement("LINK", 49);
        defineElement("MAP", ALLOWED_HEAD);
        defineElement("META", 49);
        defineElement("OL", 66);
        defineElement("OPTGROUP", ELEM_CONTENT);
        defineElement("OPTION", 265);
        defineElement("P", 328);
        defineElement("PARAM", EMPTY);
        defineElement("PRE", 68);
        defineElement("SCRIPT", 36);
        defineElement("NOSCRIPT", 36);
        defineElement("SELECT", ELEM_CONTENT);
        defineElement("STYLE", 36);
        defineElement("TABLE", 66);
        defineElement("TBODY", 522);
        defineElement("TD", 16392);
        defineElement("TEXTAREA", PRESERVE);
        defineElement("TFOOT", 522);
        defineElement("TH", 16392);
        defineElement("THEAD", 522);
        defineElement("TITLE", ALLOWED_HEAD);
        defineElement("TR", 522);
        defineElement("UL", 66);
        _boolAttrs = new Hashtable();
        defineBoolean("AREA", "href");
        defineBoolean("BUTTON", "disabled");
        defineBoolean("DIR", "compact");
        defineBoolean("DL", "compact");
        defineBoolean("FRAME", "noresize");
        defineBoolean("HR", "noshade");
        defineBoolean("IMAGE", "ismap");
        String[] strArr = new String[PRESERVE];
        strArr[0] = "defaultchecked";
        strArr[ONLY_OPENING] = "checked";
        strArr[ELEM_CONTENT] = "readonly";
        strArr[3] = "disabled";
        defineBoolean("INPUT", strArr);
        defineBoolean("LINK", "link");
        defineBoolean("MENU", "compact");
        defineBoolean("OBJECT", "declare");
        defineBoolean("OL", "compact");
        defineBoolean("OPTGROUP", "disabled");
        defineBoolean("OPTION", new String[]{"default-selected", "selected", "disabled"});
        defineBoolean("SCRIPT", "defer");
        strArr = new String[ELEM_CONTENT];
        strArr[0] = "multiple";
        strArr[ONLY_OPENING] = "disabled";
        defineBoolean("SELECT", strArr);
        defineBoolean("STYLE", "disabled");
        defineBoolean("TD", "nowrap");
        defineBoolean("TH", "nowrap");
        strArr = new String[ELEM_CONTENT];
        strArr[0] = "disabled";
        strArr[ONLY_OPENING] = "readonly";
        defineBoolean("TEXTAREA", strArr);
        defineBoolean("UL", "compact");
        initialize();
    }
}
