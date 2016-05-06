package org.apache.commons.lang;

import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import org.apache.commons.lang.exception.NestableRuntimeException;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.http.HttpStatus;

public class StringEscapeUtils {
    private static final char CSV_DELIMITER = ',';
    private static final char CSV_QUOTE = '\"';
    private static final String CSV_QUOTE_STR;
    private static final char[] CSV_SEARCH_CHARS;

    static {
        CSV_QUOTE_STR = String.valueOf(CSV_QUOTE);
        CSV_SEARCH_CHARS = new char[]{CSV_DELIMITER, CSV_QUOTE, CharUtils.CR, '\n'};
    }

    public static String escapeJava(String str) {
        return escapeJavaStyleString(str, false, false);
    }

    public static void escapeJava(Writer out, String str) throws IOException {
        escapeJavaStyleString(out, str, false, false);
    }

    public static String escapeJavaScript(String str) {
        return escapeJavaStyleString(str, true, true);
    }

    public static void escapeJavaScript(Writer out, String str) throws IOException {
        escapeJavaStyleString(out, str, true, true);
    }

    private static String escapeJavaStyleString(String str, boolean escapeSingleQuotes, boolean escapeForwardSlash) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter writer = new StringWriter(str.length() * 2);
            escapeJavaStyleString(writer, str, escapeSingleQuotes, escapeForwardSlash);
            return writer.toString();
        } catch (IOException ioe) {
            throw new UnhandledException(ioe);
        }
    }

    private static void escapeJavaStyleString(Writer out, String str, boolean escapeSingleQuote, boolean escapeForwardSlash) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        } else if (str != null) {
            int sz = str.length();
            for (int i = 0; i < sz; i++) {
                char ch = str.charAt(i);
                if (ch <= '\u0fff') {
                    if (ch <= '\u00ff') {
                        if (ch <= '\u007f') {
                            if (ch >= ' ') {
                                switch (ch) {
                                    case Tokens.EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF /*34*/:
                                        out.write(92);
                                        out.write(34);
                                        break;
                                    case Tokens.EXPRTOKEN_AXISNAME_FOLLOWING /*39*/:
                                        if (escapeSingleQuote) {
                                            out.write(92);
                                        }
                                        out.write(39);
                                        break;
                                    case Tokens.EXPRTOKEN_NUMBER /*47*/:
                                        if (escapeForwardSlash) {
                                            out.write(92);
                                        }
                                        out.write(47);
                                        break;
                                    case C0302R.styleable.Theme_alertDialogButtonGroupStyle /*92*/:
                                        out.write(92);
                                        out.write(92);
                                        break;
                                    default:
                                        out.write(ch);
                                        break;
                                }
                            }
                            switch (ch) {
                                case ConnectionResult.INTERNAL_ERROR /*8*/:
                                    out.write(92);
                                    out.write(98);
                                    break;
                                case ConnectionResult.SERVICE_INVALID /*9*/:
                                    out.write(92);
                                    out.write(116);
                                    break;
                                case MetaData.DEFAULT_MAX_ADS /*10*/:
                                    out.write(92);
                                    out.write(110);
                                    break;
                                case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                                    out.write(92);
                                    out.write(HttpStatus.SC_PROCESSING);
                                    break;
                                case ConnectionResult.CANCELED /*13*/:
                                    out.write(92);
                                    out.write(114);
                                    break;
                                default:
                                    if (ch <= '\u000f') {
                                        out.write(new StringBuffer().append("\\u000").append(hex(ch)).toString());
                                        break;
                                    } else {
                                        out.write(new StringBuffer().append("\\u00").append(hex(ch)).toString());
                                        break;
                                    }
                            }
                        }
                        out.write(new StringBuffer().append("\\u00").append(hex(ch)).toString());
                    } else {
                        out.write(new StringBuffer().append("\\u0").append(hex(ch)).toString());
                    }
                } else {
                    out.write(new StringBuffer().append("\\u").append(hex(ch)).toString());
                }
            }
        }
    }

    private static String hex(char ch) {
        return Integer.toHexString(ch).toUpperCase(Locale.ENGLISH);
    }

    public static String unescapeJava(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter writer = new StringWriter(str.length());
            unescapeJava(writer, str);
            return writer.toString();
        } catch (IOException ioe) {
            throw new UnhandledException(ioe);
        }
    }

    public static void unescapeJava(Writer out, String str) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        } else if (str != null) {
            int sz = str.length();
            StrBuilder unicode = new StrBuilder(4);
            boolean hadSlash = false;
            boolean inUnicode = false;
            for (int i = 0; i < sz; i++) {
                char ch = str.charAt(i);
                if (inUnicode) {
                    unicode.append(ch);
                    if (unicode.length() == 4) {
                        try {
                            out.write((char) Integer.parseInt(unicode.toString(), 16));
                            unicode.setLength(0);
                            inUnicode = false;
                            hadSlash = false;
                        } catch (NumberFormatException nfe) {
                            throw new NestableRuntimeException(new StringBuffer().append("Unable to parse unicode value: ").append(unicode).toString(), nfe);
                        }
                    }
                    continue;
                } else if (hadSlash) {
                    hadSlash = false;
                    switch (ch) {
                        case Tokens.EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF /*34*/:
                            out.write(34);
                            break;
                        case Tokens.EXPRTOKEN_AXISNAME_FOLLOWING /*39*/:
                            out.write(39);
                            break;
                        case C0302R.styleable.Theme_alertDialogButtonGroupStyle /*92*/:
                            out.write(92);
                            break;
                        case C0302R.styleable.Theme_buttonBarNeutralButtonStyle /*98*/:
                            out.write(8);
                            break;
                        case HttpStatus.SC_PROCESSING /*102*/:
                            out.write(12);
                            break;
                        case 'n':
                            out.write(10);
                            break;
                        case 'r':
                            out.write(13);
                            break;
                        case 't':
                            out.write(9);
                            break;
                        case 'u':
                            inUnicode = true;
                            break;
                        default:
                            out.write(ch);
                            break;
                    }
                } else if (ch == '\\') {
                    hadSlash = true;
                } else {
                    out.write(ch);
                }
            }
            if (hadSlash) {
                out.write(92);
            }
        }
    }

    public static String unescapeJavaScript(String str) {
        return unescapeJava(str);
    }

    public static void unescapeJavaScript(Writer out, String str) throws IOException {
        unescapeJava(out, str);
    }

    public static String escapeHtml(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter writer = new StringWriter((int) (((double) str.length()) * 1.5d));
            escapeHtml(writer, str);
            return writer.toString();
        } catch (IOException ioe) {
            throw new UnhandledException(ioe);
        }
    }

    public static void escapeHtml(Writer writer, String string) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null.");
        } else if (string != null) {
            Entities.HTML40.escape(writer, string);
        }
    }

    public static String unescapeHtml(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter writer = new StringWriter((int) (((double) str.length()) * 1.5d));
            unescapeHtml(writer, str);
            return writer.toString();
        } catch (IOException ioe) {
            throw new UnhandledException(ioe);
        }
    }

    public static void unescapeHtml(Writer writer, String string) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null.");
        } else if (string != null) {
            Entities.HTML40.unescape(writer, string);
        }
    }

    public static void escapeXml(Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null.");
        } else if (str != null) {
            Entities.XML.escape(writer, str);
        }
    }

    public static String escapeXml(String str) {
        if (str == null) {
            return null;
        }
        return Entities.XML.escape(str);
    }

    public static void unescapeXml(Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("The Writer must not be null.");
        } else if (str != null) {
            Entities.XML.unescape(writer, str);
        }
    }

    public static String unescapeXml(String str) {
        if (str == null) {
            return null;
        }
        return Entities.XML.unescape(str);
    }

    public static String escapeSql(String str) {
        if (str == null) {
            return null;
        }
        return StringUtils.replace(str, "'", "''");
    }

    public static String escapeCsv(String str) {
        if (!StringUtils.containsNone(str, CSV_SEARCH_CHARS)) {
            try {
                StringWriter writer = new StringWriter();
                escapeCsv(writer, str);
                str = writer.toString();
            } catch (IOException ioe) {
                throw new UnhandledException(ioe);
            }
        }
        return str;
    }

    public static void escapeCsv(Writer out, String str) throws IOException {
        if (!StringUtils.containsNone(str, CSV_SEARCH_CHARS)) {
            out.write(34);
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == CSV_QUOTE) {
                    out.write(34);
                }
                out.write(c);
            }
            out.write(34);
        } else if (str != null) {
            out.write(str);
        }
    }

    public static String unescapeCsv(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter writer = new StringWriter();
            unescapeCsv(writer, str);
            return writer.toString();
        } catch (IOException ioe) {
            throw new UnhandledException(ioe);
        }
    }

    public static void unescapeCsv(Writer out, String str) throws IOException {
        if (str != null) {
            if (str.length() < 2) {
                out.write(str);
            } else if (str.charAt(0) == CSV_QUOTE && str.charAt(str.length() - 1) == CSV_QUOTE) {
                String quoteless = str.substring(1, str.length() - 1);
                if (StringUtils.containsAny(quoteless, CSV_SEARCH_CHARS)) {
                    str = StringUtils.replace(quoteless, new StringBuffer().append(CSV_QUOTE_STR).append(CSV_QUOTE_STR).toString(), CSV_QUOTE_STR);
                }
                out.write(str);
            } else {
                out.write(str);
            }
        }
    }
}
