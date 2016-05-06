package org.apache.commons.lang.text;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;

public class ExtendedMessageFormat extends MessageFormat {
    private static final String DUMMY_PATTERN = "";
    private static final char END_FE = '}';
    private static final String ESCAPED_QUOTE = "''";
    private static final int HASH_SEED = 31;
    private static final char QUOTE = '\'';
    private static final char START_FE = '{';
    private static final char START_FMT = ',';
    private static final long serialVersionUID = -2362048321261811743L;
    private final Map registry;
    private String toPattern;

    public ExtendedMessageFormat(String pattern) {
        this(pattern, Locale.getDefault());
    }

    public ExtendedMessageFormat(String pattern, Locale locale) {
        this(pattern, locale, null);
    }

    public ExtendedMessageFormat(String pattern, Map registry) {
        this(pattern, Locale.getDefault(), registry);
    }

    public ExtendedMessageFormat(String pattern, Locale locale, Map registry) {
        super(DUMMY_PATTERN);
        setLocale(locale);
        this.registry = registry;
        applyPattern(pattern);
    }

    public String toPattern() {
        return this.toPattern;
    }

    public final void applyPattern(String pattern) {
        if (this.registry == null) {
            super.applyPattern(pattern);
            this.toPattern = super.toPattern();
            return;
        }
        ArrayList foundFormats = new ArrayList();
        ArrayList foundDescriptions = new ArrayList();
        StrBuilder stripCustom = new StrBuilder(pattern.length());
        ParsePosition pos = new ParsePosition(0);
        char[] c = pattern.toCharArray();
        int fmtCount = 0;
        while (pos.getIndex() < pattern.length()) {
            switch (c[pos.getIndex()]) {
                case Tokens.EXPRTOKEN_AXISNAME_FOLLOWING /*39*/:
                    appendQuotedString(pattern, pos, stripCustom, true);
                    continue;
                case '{':
                    fmtCount++;
                    seekNonWs(pattern, pos);
                    int start = pos.getIndex();
                    stripCustom.append((char) START_FE).append(readArgumentIndex(pattern, next(pos)));
                    seekNonWs(pattern, pos);
                    Format format = null;
                    String formatDescription = null;
                    if (c[pos.getIndex()] == ',') {
                        formatDescription = parseFormatDescription(pattern, next(pos));
                        format = getFormat(formatDescription);
                        if (format == null) {
                            stripCustom.append((char) START_FMT).append(formatDescription);
                        }
                    }
                    foundFormats.add(format);
                    if (format == null) {
                        formatDescription = null;
                    }
                    foundDescriptions.add(formatDescription);
                    Validate.isTrue(foundFormats.size() == fmtCount);
                    Validate.isTrue(foundDescriptions.size() == fmtCount);
                    if (c[pos.getIndex()] != '}') {
                        throw new IllegalArgumentException(new StringBuffer().append("Unreadable format element at position ").append(start).toString());
                    }
                    break;
            }
            stripCustom.append(c[pos.getIndex()]);
            next(pos);
        }
        super.applyPattern(stripCustom.toString());
        this.toPattern = insertFormats(super.toPattern(), foundDescriptions);
        if (containsElements(foundFormats)) {
            Format[] origFormats = getFormats();
            int i = 0;
            Iterator it = foundFormats.iterator();
            while (it.hasNext()) {
                Format f = (Format) it.next();
                if (f != null) {
                    origFormats[i] = f;
                }
                i++;
            }
            super.setFormats(origFormats);
        }
    }

    public void setFormat(int formatElementIndex, Format newFormat) {
        throw new UnsupportedOperationException();
    }

    public void setFormatByArgumentIndex(int argumentIndex, Format newFormat) {
        throw new UnsupportedOperationException();
    }

    public void setFormats(Format[] newFormats) {
        throw new UnsupportedOperationException();
    }

    public void setFormatsByArgumentIndex(Format[] newFormats) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (ObjectUtils.notEqual(getClass(), obj.getClass())) {
            return false;
        }
        ExtendedMessageFormat rhs = (ExtendedMessageFormat) obj;
        if (ObjectUtils.notEqual(this.toPattern, rhs.toPattern)) {
            return false;
        }
        if (ObjectUtils.notEqual(this.registry, rhs.registry)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((super.hashCode() * HASH_SEED) + ObjectUtils.hashCode(this.registry)) * HASH_SEED) + ObjectUtils.hashCode(this.toPattern);
    }

    private Format getFormat(String desc) {
        if (this.registry != null) {
            String name = desc;
            String args = null;
            int i = desc.indexOf(44);
            if (i > 0) {
                name = desc.substring(0, i).trim();
                args = desc.substring(i + 1).trim();
            }
            FormatFactory factory = (FormatFactory) this.registry.get(name);
            if (factory != null) {
                return factory.getFormat(name, args, getLocale());
            }
        }
        return null;
    }

    private int readArgumentIndex(String pattern, ParsePosition pos) {
        int start = pos.getIndex();
        seekNonWs(pattern, pos);
        StrBuilder result = new StrBuilder();
        boolean error = false;
        while (!error && pos.getIndex() < pattern.length()) {
            char c = pattern.charAt(pos.getIndex());
            if (Character.isWhitespace(c)) {
                seekNonWs(pattern, pos);
                c = pattern.charAt(pos.getIndex());
                if (!(c == START_FMT || c == END_FE)) {
                    error = true;
                    next(pos);
                }
            }
            if ((c == START_FMT || c == END_FE) && result.length() > 0) {
                try {
                    return Integer.parseInt(result.toString());
                } catch (NumberFormatException e) {
                }
            }
            error = !Character.isDigit(c);
            result.append(c);
            next(pos);
        }
        if (error) {
            throw new IllegalArgumentException(new StringBuffer().append("Invalid format argument index at position ").append(start).append(": ").append(pattern.substring(start, pos.getIndex())).toString());
        }
        throw new IllegalArgumentException(new StringBuffer().append("Unterminated format element at position ").append(start).toString());
    }

    private String parseFormatDescription(String pattern, ParsePosition pos) {
        int start = pos.getIndex();
        seekNonWs(pattern, pos);
        int text = pos.getIndex();
        int depth = 1;
        while (pos.getIndex() < pattern.length()) {
            switch (pattern.charAt(pos.getIndex())) {
                case Tokens.EXPRTOKEN_AXISNAME_FOLLOWING /*39*/:
                    getQuotedString(pattern, pos, false);
                    break;
                case '{':
                    depth++;
                    break;
                case '}':
                    depth--;
                    if (depth != 0) {
                        break;
                    }
                    return pattern.substring(text, pos.getIndex());
                default:
                    break;
            }
            next(pos);
        }
        throw new IllegalArgumentException(new StringBuffer().append("Unterminated format element at position ").append(start).toString());
    }

    private String insertFormats(String pattern, ArrayList customPatterns) {
        if (!containsElements(customPatterns)) {
            return pattern;
        }
        StrBuilder sb = new StrBuilder(pattern.length() * 2);
        ParsePosition pos = new ParsePosition(0);
        int fe = -1;
        int depth = 0;
        while (pos.getIndex() < pattern.length()) {
            char c = pattern.charAt(pos.getIndex());
            switch (c) {
                case Tokens.EXPRTOKEN_AXISNAME_FOLLOWING /*39*/:
                    appendQuotedString(pattern, pos, sb, false);
                    continue;
                case '{':
                    depth++;
                    if (depth == 1) {
                        fe++;
                        sb.append((char) START_FE).append(readArgumentIndex(pattern, next(pos)));
                        String customPattern = (String) customPatterns.get(fe);
                        if (customPattern == null) {
                            break;
                        }
                        sb.append((char) START_FMT).append(customPattern);
                        break;
                    }
                    continue;
                case '}':
                    depth--;
                    break;
            }
            sb.append(c);
            next(pos);
        }
        return sb.toString();
    }

    private void seekNonWs(String pattern, ParsePosition pos) {
        char[] buffer = pattern.toCharArray();
        do {
            int len = StrMatcher.splitMatcher().isMatch(buffer, pos.getIndex());
            pos.setIndex(pos.getIndex() + len);
            if (len <= 0) {
                return;
            }
        } while (pos.getIndex() < pattern.length());
    }

    private ParsePosition next(ParsePosition pos) {
        pos.setIndex(pos.getIndex() + 1);
        return pos;
    }

    private StrBuilder appendQuotedString(String pattern, ParsePosition pos, StrBuilder appendTo, boolean escapingOn) {
        int start = pos.getIndex();
        char[] c = pattern.toCharArray();
        if (escapingOn && c[start] == QUOTE) {
            next(pos);
            if (appendTo == null) {
                return null;
            }
            return appendTo.append((char) QUOTE);
        }
        int lastHold = start;
        int i = pos.getIndex();
        while (i < pattern.length()) {
            if (!escapingOn || !pattern.substring(i).startsWith(ESCAPED_QUOTE)) {
                switch (c[pos.getIndex()]) {
                    case Tokens.EXPRTOKEN_AXISNAME_FOLLOWING /*39*/:
                        next(pos);
                        if (appendTo != null) {
                            return appendTo.append(c, lastHold, pos.getIndex() - lastHold);
                        }
                        return null;
                    default:
                        next(pos);
                        break;
                }
            }
            appendTo.append(c, lastHold, pos.getIndex() - lastHold).append((char) QUOTE);
            pos.setIndex(ESCAPED_QUOTE.length() + i);
            lastHold = pos.getIndex();
            i++;
        }
        throw new IllegalArgumentException(new StringBuffer().append("Unterminated quoted string at position ").append(start).toString());
    }

    private void getQuotedString(String pattern, ParsePosition pos, boolean escapingOn) {
        appendQuotedString(pattern, pos, null, escapingOn);
    }

    private boolean containsElements(Collection coll) {
        if (coll == null || coll.size() == 0) {
            return false;
        }
        for (Object obj : coll) {
            if (obj != null) {
                return true;
            }
        }
        return false;
    }
}
