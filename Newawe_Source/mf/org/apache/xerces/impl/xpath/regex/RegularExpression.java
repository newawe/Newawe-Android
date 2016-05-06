package mf.org.apache.xerces.impl.xpath.regex;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.ads.AdSize;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.io.Serializable;
import java.text.CharacterIterator;
import java.util.Locale;
import org.apache.commons.lang.CharUtils;
import org.apache.http.conn.params.ConnManagerParams;

public class RegularExpression implements Serializable {
    static final int CARRIAGE_RETURN = 13;
    static final boolean DEBUG = false;
    static final int EXTENDED_COMMENT = 16;
    static final int IGNORE_CASE = 2;
    static final int LINE_FEED = 10;
    static final int LINE_SEPARATOR = 8232;
    static final int MULTIPLE_LINES = 8;
    static final int PARAGRAPH_SEPARATOR = 8233;
    static final int PROHIBIT_FIXED_STRING_OPTIMIZATION = 256;
    static final int PROHIBIT_HEAD_CHARACTER_OPTIMIZATION = 128;
    static final int SINGLE_LINE = 4;
    static final int SPECIAL_COMMA = 1024;
    static final int UNICODE_WORD_BOUNDARY = 64;
    static final int USE_UNICODE_CATEGORY = 32;
    private static final int WT_IGNORE = 0;
    private static final int WT_LETTER = 1;
    private static final int WT_OTHER = 2;
    static final int XMLSCHEMA_MODE = 512;
    private static final long serialVersionUID = 6242499334195006401L;
    transient Context context;
    transient RangeToken firstChar;
    transient String fixedString;
    transient boolean fixedStringOnly;
    transient int fixedStringOptions;
    transient BMPattern fixedStringTable;
    boolean hasBackReferences;
    transient int minlength;
    int nofparen;
    transient int numberOfClosures;
    transient Op operations;
    int options;
    String regex;
    Token tokentree;

    static final class ClosureContext {
        int currentIndex;
        int[] offsets;

        ClosureContext() {
            this.offsets = new int[RegularExpression.SINGLE_LINE];
            this.currentIndex = RegularExpression.WT_IGNORE;
        }

        boolean contains(int offset) {
            for (int i = RegularExpression.WT_IGNORE; i < this.currentIndex; i += RegularExpression.WT_LETTER) {
                if (this.offsets[i] == offset) {
                    return true;
                }
            }
            return RegularExpression.DEBUG;
        }

        void reset() {
            this.currentIndex = RegularExpression.WT_IGNORE;
        }

        void addOffset(int offset) {
            if (this.currentIndex == this.offsets.length) {
                this.offsets = expandOffsets();
            }
            int[] iArr = this.offsets;
            int i = this.currentIndex;
            this.currentIndex = i + RegularExpression.WT_LETTER;
            iArr[i] = offset;
        }

        private int[] expandOffsets() {
            int[] newOffsets = new int[(this.offsets.length << RegularExpression.WT_LETTER)];
            System.arraycopy(this.offsets, RegularExpression.WT_IGNORE, newOffsets, RegularExpression.WT_IGNORE, this.currentIndex);
            return newOffsets;
        }
    }

    static final class Context {
        private CharArrayTarget charArrayTarget;
        private CharacterIteratorTarget characterIteratorTarget;
        ClosureContext[] closureContexts;
        boolean inuse;
        int length;
        int limit;
        Match match;
        int start;
        private StringTarget stringTarget;
        ExpressionTarget target;

        Context() {
            this.inuse = RegularExpression.DEBUG;
        }

        private void resetCommon(int nofclosures) {
            this.length = this.limit - this.start;
            setInUse(true);
            this.match = null;
            if (this.closureContexts == null || this.closureContexts.length != nofclosures) {
                this.closureContexts = new ClosureContext[nofclosures];
            }
            for (int i = RegularExpression.WT_IGNORE; i < nofclosures; i += RegularExpression.WT_LETTER) {
                if (this.closureContexts[i] == null) {
                    this.closureContexts[i] = new ClosureContext();
                } else {
                    this.closureContexts[i].reset();
                }
            }
        }

        void reset(CharacterIterator target, int start, int limit, int nofclosures) {
            if (this.characterIteratorTarget == null) {
                this.characterIteratorTarget = new CharacterIteratorTarget(target);
            } else {
                this.characterIteratorTarget.resetTarget(target);
            }
            this.target = this.characterIteratorTarget;
            this.start = start;
            this.limit = limit;
            resetCommon(nofclosures);
        }

        void reset(String target, int start, int limit, int nofclosures) {
            if (this.stringTarget == null) {
                this.stringTarget = new StringTarget(target);
            } else {
                this.stringTarget.resetTarget(target);
            }
            this.target = this.stringTarget;
            this.start = start;
            this.limit = limit;
            resetCommon(nofclosures);
        }

        void reset(char[] target, int start, int limit, int nofclosures) {
            if (this.charArrayTarget == null) {
                this.charArrayTarget = new CharArrayTarget(target);
            } else {
                this.charArrayTarget.resetTarget(target);
            }
            this.target = this.charArrayTarget;
            this.start = start;
            this.limit = limit;
            resetCommon(nofclosures);
        }

        synchronized void setInUse(boolean inUse) {
            this.inuse = inUse;
        }
    }

    static abstract class ExpressionTarget {
        abstract char charAt(int i);

        abstract boolean regionMatches(boolean z, int i, int i2, int i3, int i4);

        abstract boolean regionMatches(boolean z, int i, int i2, String str, int i3);

        ExpressionTarget() {
        }
    }

    static final class CharArrayTarget extends ExpressionTarget {
        char[] target;

        CharArrayTarget(char[] target) {
            this.target = target;
        }

        final void resetTarget(char[] target) {
            this.target = target;
        }

        char charAt(int index) {
            return this.target[index];
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, String part, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return RegularExpression.DEBUG;
            }
            if (ignoreCase) {
                return regionMatchesIgnoreCase(offset, limit, part, partlen);
            }
            return regionMatches(offset, limit, part, partlen);
        }

        private final boolean regionMatches(int offset, int limit, String part, int partlen) {
            int i = RegularExpression.WT_IGNORE;
            int partlen2 = partlen;
            int offset2 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset2;
                    return true;
                }
                offset = offset2 + RegularExpression.WT_LETTER;
                i2 = i + RegularExpression.WT_LETTER;
                if (this.target[offset2] != part.charAt(i)) {
                    return RegularExpression.DEBUG;
                }
                i = i2;
                partlen2 = partlen;
                offset2 = offset;
            }
        }

        private final boolean regionMatchesIgnoreCase(int offset, int limit, String part, int partlen) {
            int i = RegularExpression.WT_IGNORE;
            int partlen2 = partlen;
            int offset2 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset2;
                    return true;
                }
                offset = offset2 + RegularExpression.WT_LETTER;
                char ch1 = this.target[offset2];
                i2 = i + RegularExpression.WT_LETTER;
                char ch2 = part.charAt(i);
                if (ch1 == ch2) {
                    i = i2;
                    partlen2 = partlen;
                    offset2 = offset;
                } else {
                    char uch1 = Character.toUpperCase(ch1);
                    char uch2 = Character.toUpperCase(ch2);
                    if (uch1 == uch2) {
                        i = i2;
                        partlen2 = partlen;
                        offset2 = offset;
                    } else if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2)) {
                        return RegularExpression.DEBUG;
                    } else {
                        i = i2;
                        partlen2 = partlen;
                        offset2 = offset;
                    }
                }
            }
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, int offset2, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return RegularExpression.DEBUG;
            }
            if (ignoreCase) {
                return regionMatchesIgnoreCase(offset, limit, offset2, partlen);
            }
            return regionMatches(offset, limit, offset2, partlen);
        }

        private final boolean regionMatches(int offset, int limit, int offset2, int partlen) {
            int i = offset2;
            int partlen2 = partlen;
            int offset3 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset3;
                    return true;
                }
                offset = offset3 + RegularExpression.WT_LETTER;
                i2 = i + RegularExpression.WT_LETTER;
                if (this.target[offset3] != this.target[i]) {
                    return RegularExpression.DEBUG;
                }
                i = i2;
                partlen2 = partlen;
                offset3 = offset;
            }
        }

        private final boolean regionMatchesIgnoreCase(int offset, int limit, int offset2, int partlen) {
            int i = offset2;
            int partlen2 = partlen;
            int offset3 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset3;
                    return true;
                }
                offset = offset3 + RegularExpression.WT_LETTER;
                char ch1 = this.target[offset3];
                i2 = i + RegularExpression.WT_LETTER;
                char ch2 = this.target[i];
                if (ch1 == ch2) {
                    i = i2;
                    partlen2 = partlen;
                    offset3 = offset;
                } else {
                    char uch1 = Character.toUpperCase(ch1);
                    char uch2 = Character.toUpperCase(ch2);
                    if (uch1 == uch2) {
                        i = i2;
                        partlen2 = partlen;
                        offset3 = offset;
                    } else if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2)) {
                        return RegularExpression.DEBUG;
                    } else {
                        i = i2;
                        partlen2 = partlen;
                        offset3 = offset;
                    }
                }
            }
        }
    }

    static final class CharacterIteratorTarget extends ExpressionTarget {
        CharacterIterator target;

        CharacterIteratorTarget(CharacterIterator target) {
            this.target = target;
        }

        final void resetTarget(CharacterIterator target) {
            this.target = target;
        }

        final char charAt(int index) {
            return this.target.setIndex(index);
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, String part, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return RegularExpression.DEBUG;
            }
            if (ignoreCase) {
                return regionMatchesIgnoreCase(offset, limit, part, partlen);
            }
            return regionMatches(offset, limit, part, partlen);
        }

        private final boolean regionMatches(int offset, int limit, String part, int partlen) {
            int i = RegularExpression.WT_IGNORE;
            int partlen2 = partlen;
            int offset2 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset2;
                    return true;
                }
                offset = offset2 + RegularExpression.WT_LETTER;
                i2 = i + RegularExpression.WT_LETTER;
                if (this.target.setIndex(offset2) != part.charAt(i)) {
                    return RegularExpression.DEBUG;
                }
                i = i2;
                partlen2 = partlen;
                offset2 = offset;
            }
        }

        private final boolean regionMatchesIgnoreCase(int offset, int limit, String part, int partlen) {
            int i = RegularExpression.WT_IGNORE;
            int partlen2 = partlen;
            int offset2 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset2;
                    return true;
                }
                offset = offset2 + RegularExpression.WT_LETTER;
                char ch1 = this.target.setIndex(offset2);
                i2 = i + RegularExpression.WT_LETTER;
                char ch2 = part.charAt(i);
                if (ch1 == ch2) {
                    i = i2;
                    partlen2 = partlen;
                    offset2 = offset;
                } else {
                    char uch1 = Character.toUpperCase(ch1);
                    char uch2 = Character.toUpperCase(ch2);
                    if (uch1 == uch2) {
                        i = i2;
                        partlen2 = partlen;
                        offset2 = offset;
                    } else if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2)) {
                        return RegularExpression.DEBUG;
                    } else {
                        i = i2;
                        partlen2 = partlen;
                        offset2 = offset;
                    }
                }
            }
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, int offset2, int partlen) {
            if (offset < 0 || limit - offset < partlen) {
                return RegularExpression.DEBUG;
            }
            if (ignoreCase) {
                return regionMatchesIgnoreCase(offset, limit, offset2, partlen);
            }
            return regionMatches(offset, limit, offset2, partlen);
        }

        private final boolean regionMatches(int offset, int limit, int offset2, int partlen) {
            int i = offset2;
            int partlen2 = partlen;
            int offset3 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset3;
                    return true;
                }
                offset = offset3 + RegularExpression.WT_LETTER;
                i2 = i + RegularExpression.WT_LETTER;
                if (this.target.setIndex(offset3) != this.target.setIndex(i)) {
                    return RegularExpression.DEBUG;
                }
                i = i2;
                partlen2 = partlen;
                offset3 = offset;
            }
        }

        private final boolean regionMatchesIgnoreCase(int offset, int limit, int offset2, int partlen) {
            int i = offset2;
            int partlen2 = partlen;
            int offset3 = offset;
            while (true) {
                partlen = partlen2 - 1;
                if (partlen2 <= 0) {
                    int i2 = i;
                    offset = offset3;
                    return true;
                }
                offset = offset3 + RegularExpression.WT_LETTER;
                char ch1 = this.target.setIndex(offset3);
                i2 = i + RegularExpression.WT_LETTER;
                char ch2 = this.target.setIndex(i);
                if (ch1 == ch2) {
                    i = i2;
                    partlen2 = partlen;
                    offset3 = offset;
                } else {
                    char uch1 = Character.toUpperCase(ch1);
                    char uch2 = Character.toUpperCase(ch2);
                    if (uch1 == uch2) {
                        i = i2;
                        partlen2 = partlen;
                        offset3 = offset;
                    } else if (Character.toLowerCase(uch1) != Character.toLowerCase(uch2)) {
                        return RegularExpression.DEBUG;
                    } else {
                        i = i2;
                        partlen2 = partlen;
                        offset3 = offset;
                    }
                }
            }
        }
    }

    static final class StringTarget extends ExpressionTarget {
        private String target;

        StringTarget(String target) {
            this.target = target;
        }

        final void resetTarget(String target) {
            this.target = target;
        }

        final char charAt(int index) {
            return this.target.charAt(index);
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, String part, int partlen) {
            if (limit - offset < partlen) {
                return RegularExpression.DEBUG;
            }
            return ignoreCase ? this.target.regionMatches(true, offset, part, RegularExpression.WT_IGNORE, partlen) : this.target.regionMatches(offset, part, RegularExpression.WT_IGNORE, partlen);
        }

        final boolean regionMatches(boolean ignoreCase, int offset, int limit, int offset2, int partlen) {
            if (limit - offset < partlen) {
                return RegularExpression.DEBUG;
            }
            if (!ignoreCase) {
                return this.target.regionMatches(offset, this.target, offset2, partlen);
            }
            return this.target.regionMatches(true, offset, this.target, offset2, partlen);
        }
    }

    private synchronized void compile(Token tok) {
        if (this.operations == null) {
            this.numberOfClosures = WT_IGNORE;
            this.operations = compile(tok, null, DEBUG);
        }
    }

    private Op compile(Token tok, Op next, boolean reverse) {
        Op ret;
        int i;
        switch (tok.type) {
            case WT_IGNORE /*0*/:
                ret = Op.createChar(tok.getChar());
                ret.next = next;
                return ret;
            case WT_LETTER /*1*/:
                ret = next;
                if (reverse) {
                    for (i = WT_IGNORE; i < tok.size(); i += WT_LETTER) {
                        ret = compile(tok.getChild(i), ret, true);
                    }
                    return ret;
                }
                for (i = tok.size() - 1; i >= 0; i--) {
                    ret = compile(tok.getChild(i), ret, DEBUG);
                }
                return ret;
            case WT_OTHER /*2*/:
                Op uni = Op.createUnion(tok.size());
                for (i = WT_IGNORE; i < tok.size(); i += WT_LETTER) {
                    uni.addElement(compile(tok.getChild(i), next, reverse));
                }
                return uni;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
            case ConnectionResult.SERVICE_INVALID /*9*/:
                Token child = tok.getChild(WT_IGNORE);
                int min = tok.getMin();
                int max = tok.getMax();
                if (min < 0 || min != max) {
                    if (min > 0 && max > 0) {
                        max -= min;
                    }
                    int i2;
                    if (max > 0) {
                        ret = next;
                        for (i = WT_IGNORE; i < max; i += WT_LETTER) {
                            i2 = tok.type;
                            Op q = Op.createQuestion(r0 == 9 ? true : DEBUG);
                            q.next = next;
                            q.setChild(compile(child, ret, reverse));
                            ret = q;
                        }
                    } else {
                        Op op;
                        i2 = tok.type;
                        if (r0 == 9) {
                            op = Op.createNonGreedyClosure();
                        } else {
                            i2 = this.numberOfClosures;
                            this.numberOfClosures = i2 + WT_LETTER;
                            op = Op.createClosure(i2);
                        }
                        op.next = next;
                        op.setChild(compile(child, op, reverse));
                        ret = op;
                    }
                    if (min <= 0) {
                        return ret;
                    }
                    for (i = WT_IGNORE; i < min; i += WT_LETTER) {
                        ret = compile(child, ret, reverse);
                    }
                    return ret;
                }
                ret = next;
                for (i = WT_IGNORE; i < min; i += WT_LETTER) {
                    ret = compile(child, ret, reverse);
                }
                return ret;
            case SINGLE_LINE /*4*/:
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                ret = Op.createRange(tok);
                ret.next = next;
                return ret;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                if (tok.getParenNumber() == 0) {
                    return compile(tok.getChild(WT_IGNORE), next, reverse);
                } else if (reverse) {
                    return Op.createCapture(-tok.getParenNumber(), compile(tok.getChild(WT_IGNORE), Op.createCapture(tok.getParenNumber(), next), reverse));
                } else {
                    return Op.createCapture(tok.getParenNumber(), compile(tok.getChild(WT_IGNORE), Op.createCapture(-tok.getParenNumber(), next), reverse));
                }
            case ConnectionResult.NETWORK_ERROR /*7*/:
                return next;
            case MULTIPLE_LINES /*8*/:
                ret = Op.createAnchor(tok.getChar());
                ret.next = next;
                return ret;
            case LINE_FEED /*10*/:
                ret = Op.createString(tok.getString());
                ret.next = next;
                return ret;
            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                ret = Op.createDot();
                ret.next = next;
                return ret;
            case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                ret = Op.createBackReference(tok.getReferenceNumber());
                ret.next = next;
                return ret;
            case ConnManagerParams.DEFAULT_MAX_TOTAL_CONNECTIONS /*20*/:
                return Op.createLook(20, next, compile(tok.getChild(WT_IGNORE), null, DEBUG));
            case Tokens.EXPRTOKEN_OPERATOR_SLASH /*21*/:
                return Op.createLook(21, next, compile(tok.getChild(WT_IGNORE), null, DEBUG));
            case Tokens.EXPRTOKEN_OPERATOR_DOUBLE_SLASH /*22*/:
                return Op.createLook(22, next, compile(tok.getChild(WT_IGNORE), null, true));
            case Tokens.EXPRTOKEN_OPERATOR_UNION /*23*/:
                return Op.createLook(23, next, compile(tok.getChild(WT_IGNORE), null, true));
            case Tokens.EXPRTOKEN_OPERATOR_PLUS /*24*/:
                return Op.createIndependent(next, compile(tok.getChild(WT_IGNORE), null, reverse));
            case Tokens.EXPRTOKEN_OPERATOR_MINUS /*25*/:
                return Op.createModifier(next, compile(tok.getChild(WT_IGNORE), null, reverse), ((ModifierToken) tok).getOptions(), ((ModifierToken) tok).getOptionsMask());
            case Tokens.EXPRTOKEN_OPERATOR_EQUAL /*26*/:
                Op no;
                ConditionToken ctok = (ConditionToken) tok;
                int ref = ctok.refNumber;
                Op condition = ctok.condition == null ? null : compile(ctok.condition, null, reverse);
                Op yes = compile(ctok.yes, next, reverse);
                if (ctok.no == null) {
                    no = null;
                } else {
                    no = compile(ctok.no, next, reverse);
                }
                return Op.createCondition(next, ref, condition, yes, no);
            default:
                throw new RuntimeException("Unknown token type: " + tok.type);
        }
    }

    public boolean matches(char[] target) {
        return matches(target, (int) WT_IGNORE, target.length, null);
    }

    public boolean matches(char[] target, int start, int end) {
        return matches(target, start, end, null);
    }

    public boolean matches(char[] target, Match match) {
        return matches(target, (int) WT_IGNORE, target.length, match);
    }

    public boolean matches(char[] target, int start, int end, Match match) {
        Context con;
        synchronized (this) {
            if (this.operations == null) {
                prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        synchronized (this.context) {
            con = this.context.inuse ? new Context() : this.context;
            con.reset(target, start, end, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(target);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        con.match = match;
        int matchEnd;
        if (isSet(this.options, XMLSCHEMA_MODE)) {
            matchEnd = match(con, this.operations, con.start, WT_LETTER, this.options);
            if (matchEnd != con.limit) {
                return DEBUG;
            }
            if (con.match != null) {
                con.match.setBeginning(WT_IGNORE, con.start);
                con.match.setEnd(WT_IGNORE, matchEnd);
            }
            con.setInUse(DEBUG);
            return true;
        } else if (this.fixedStringOnly) {
            int o = this.fixedStringTable.matches(target, con.start, con.limit);
            if (o >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(WT_IGNORE, o);
                    con.match.setEnd(WT_IGNORE, this.fixedString.length() + o);
                }
                con.setInUse(DEBUG);
                return true;
            }
            con.setInUse(DEBUG);
            return DEBUG;
        } else {
            int matchStart;
            if (this.fixedString != null) {
                if (this.fixedStringTable.matches(target, con.start, con.limit) < 0) {
                    con.setInUse(DEBUG);
                    return DEBUG;
                }
            }
            int limit = con.limit - this.minlength;
            matchEnd = -1;
            if (this.operations == null || this.operations.type != 7 || this.operations.getChild().type != 0) {
                if (this.firstChar == null) {
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        matchEnd = match(con, this.operations, matchStart, WT_LETTER, this.options);
                        if (matchEnd >= 0) {
                            break;
                        }
                        matchStart += WT_LETTER;
                    }
                } else {
                    RangeToken range = this.firstChar;
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        int ch = target[matchStart];
                        if (REUtil.isHighSurrogate(ch) && matchStart + WT_LETTER < con.limit) {
                            ch = REUtil.composeFromSurrogates(ch, target[matchStart + WT_LETTER]);
                        }
                        if (range.match(ch)) {
                            matchEnd = match(con, this.operations, matchStart, WT_LETTER, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        matchStart += WT_LETTER;
                    }
                }
            } else if (isSet(this.options, SINGLE_LINE)) {
                int matchStart2 = con.start;
                matchEnd = match(con, this.operations, con.start, WT_LETTER, this.options);
                matchStart = matchStart2;
            } else {
                boolean previousIsEOL = true;
                matchStart = con.start;
                while (matchStart <= limit) {
                    if (isEOLChar(target[matchStart])) {
                        previousIsEOL = true;
                    } else {
                        if (previousIsEOL) {
                            matchEnd = match(con, this.operations, matchStart, WT_LETTER, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        previousIsEOL = DEBUG;
                    }
                    matchStart += WT_LETTER;
                }
            }
            if (matchEnd >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(WT_IGNORE, matchStart);
                    con.match.setEnd(WT_IGNORE, matchEnd);
                }
                con.setInUse(DEBUG);
                return true;
            }
            con.setInUse(DEBUG);
            return DEBUG;
        }
    }

    public boolean matches(String target) {
        return matches(target, (int) WT_IGNORE, target.length(), null);
    }

    public boolean matches(String target, int start, int end) {
        return matches(target, start, end, null);
    }

    public boolean matches(String target, Match match) {
        return matches(target, (int) WT_IGNORE, target.length(), match);
    }

    public boolean matches(String target, int start, int end, Match match) {
        Context con;
        synchronized (this) {
            if (this.operations == null) {
                prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        synchronized (this.context) {
            con = this.context.inuse ? new Context() : this.context;
            con.reset(target, start, end, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(target);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        con.match = match;
        int matchEnd;
        if (isSet(this.options, XMLSCHEMA_MODE)) {
            matchEnd = match(con, this.operations, con.start, WT_LETTER, this.options);
            if (matchEnd != con.limit) {
                return DEBUG;
            }
            if (con.match != null) {
                con.match.setBeginning(WT_IGNORE, con.start);
                con.match.setEnd(WT_IGNORE, matchEnd);
            }
            con.setInUse(DEBUG);
            return true;
        } else if (this.fixedStringOnly) {
            int o = this.fixedStringTable.matches(target, con.start, con.limit);
            if (o >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(WT_IGNORE, o);
                    con.match.setEnd(WT_IGNORE, this.fixedString.length() + o);
                }
                con.setInUse(DEBUG);
                return true;
            }
            con.setInUse(DEBUG);
            return DEBUG;
        } else {
            int matchStart;
            if (this.fixedString != null) {
                if (this.fixedStringTable.matches(target, con.start, con.limit) < 0) {
                    con.setInUse(DEBUG);
                    return DEBUG;
                }
            }
            int limit = con.limit - this.minlength;
            matchEnd = -1;
            if (this.operations == null || this.operations.type != 7 || this.operations.getChild().type != 0) {
                if (this.firstChar == null) {
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        matchEnd = match(con, this.operations, matchStart, WT_LETTER, this.options);
                        if (matchEnd >= 0) {
                            break;
                        }
                        matchStart += WT_LETTER;
                    }
                } else {
                    RangeToken range = this.firstChar;
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        int ch = target.charAt(matchStart);
                        if (REUtil.isHighSurrogate(ch) && matchStart + WT_LETTER < con.limit) {
                            ch = REUtil.composeFromSurrogates(ch, target.charAt(matchStart + WT_LETTER));
                        }
                        if (range.match(ch)) {
                            matchEnd = match(con, this.operations, matchStart, WT_LETTER, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        matchStart += WT_LETTER;
                    }
                }
            } else if (isSet(this.options, SINGLE_LINE)) {
                int matchStart2 = con.start;
                matchEnd = match(con, this.operations, con.start, WT_LETTER, this.options);
                matchStart = matchStart2;
            } else {
                boolean previousIsEOL = true;
                matchStart = con.start;
                while (matchStart <= limit) {
                    if (isEOLChar(target.charAt(matchStart))) {
                        previousIsEOL = true;
                    } else {
                        if (previousIsEOL) {
                            matchEnd = match(con, this.operations, matchStart, WT_LETTER, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        previousIsEOL = DEBUG;
                    }
                    matchStart += WT_LETTER;
                }
            }
            if (matchEnd >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(WT_IGNORE, matchStart);
                    con.match.setEnd(WT_IGNORE, matchEnd);
                }
                con.setInUse(DEBUG);
                return true;
            }
            con.setInUse(DEBUG);
            return DEBUG;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int match(mf.org.apache.xerces.impl.xpath.regex.RegularExpression.Context r30, mf.org.apache.xerces.impl.xpath.regex.Op r31, int r32, int r33, int r34) {
        /*
        r29 = this;
        r0 = r30;
        r3 = r0.target;
        r22 = new java.util.Stack;
        r22.<init>();
        r17 = new mf.org.apache.xerces.util.IntStack;
        r17.<init>();
        r2 = 2;
        r0 = r34;
        r10 = isSet(r0, r2);
        r24 = -1;
        r25 = 0;
    L_0x0019:
        if (r31 == 0) goto L_0x002b;
    L_0x001b:
        r0 = r30;
        r2 = r0.limit;
        r0 = r32;
        if (r0 > r2) goto L_0x002b;
    L_0x0023:
        r0 = r30;
        r2 = r0.start;
        r0 = r32;
        if (r0 >= r2) goto L_0x0052;
    L_0x002b:
        if (r31 != 0) goto L_0x004f;
    L_0x002d:
        r2 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r0 = r34;
        r2 = isSet(r0, r2);
        if (r2 == 0) goto L_0x004c;
    L_0x0037:
        r0 = r30;
        r2 = r0.limit;
        r0 = r32;
        if (r0 == r2) goto L_0x004c;
    L_0x003f:
        r24 = -1;
    L_0x0041:
        r25 = 1;
    L_0x0043:
        if (r25 == 0) goto L_0x0019;
    L_0x0045:
        r2 = r22.isEmpty();
        if (r2 == 0) goto L_0x040a;
    L_0x004b:
        return r24;
    L_0x004c:
        r24 = r32;
        goto L_0x0041;
    L_0x004f:
        r24 = -1;
        goto L_0x0041;
    L_0x0052:
        r24 = -1;
        r0 = r31;
        r2 = r0.type;
        switch(r2) {
            case 0: goto L_0x00a3;
            case 1: goto L_0x0074;
            case 2: goto L_0x005b;
            case 3: goto L_0x0117;
            case 4: goto L_0x0117;
            case 5: goto L_0x016f;
            case 6: goto L_0x0213;
            case 7: goto L_0x024e;
            case 8: goto L_0x0283;
            case 9: goto L_0x026f;
            case 10: goto L_0x0283;
            case 11: goto L_0x0299;
            case 12: goto L_0x005b;
            case 13: goto L_0x005b;
            case 14: goto L_0x005b;
            case 15: goto L_0x02c0;
            case 16: goto L_0x018b;
            case 17: goto L_0x005b;
            case 18: goto L_0x005b;
            case 19: goto L_0x005b;
            case 20: goto L_0x031c;
            case 21: goto L_0x031c;
            case 22: goto L_0x031c;
            case 23: goto L_0x031c;
            case 24: goto L_0x034c;
            case 25: goto L_0x0360;
            case 26: goto L_0x038d;
            default: goto L_0x005b;
        };
    L_0x005b:
        r2 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r5 = "Unknown operation type: ";
        r4.<init>(r5);
        r0 = r31;
        r5 = r0.type;
        r4 = r4.append(r5);
        r4 = r4.toString();
        r2.<init>(r4);
        throw r2;
    L_0x0074:
        if (r33 <= 0) goto L_0x0097;
    L_0x0076:
        r21 = r32;
    L_0x0078:
        r0 = r30;
        r2 = r0.limit;
        r0 = r21;
        if (r0 >= r2) goto L_0x0094;
    L_0x0080:
        if (r21 < 0) goto L_0x0094;
    L_0x0082:
        r2 = r31.getData();
        r0 = r21;
        r4 = r3.charAt(r0);
        r0 = r29;
        r2 = r0.matchChar(r2, r4, r10);
        if (r2 != 0) goto L_0x009a;
    L_0x0094:
        r25 = 1;
        goto L_0x0043;
    L_0x0097:
        r21 = r32 + -1;
        goto L_0x0078;
    L_0x009a:
        r32 = r32 + r33;
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x00a3:
        if (r33 <= 0) goto L_0x00b4;
    L_0x00a5:
        r21 = r32;
    L_0x00a7:
        r0 = r30;
        r2 = r0.limit;
        r0 = r21;
        if (r0 >= r2) goto L_0x00b1;
    L_0x00af:
        if (r21 >= 0) goto L_0x00b7;
    L_0x00b1:
        r25 = 1;
        goto L_0x0043;
    L_0x00b4:
        r21 = r32 + -1;
        goto L_0x00a7;
    L_0x00b7:
        r2 = 4;
        r0 = r34;
        r2 = isSet(r0, r2);
        if (r2 == 0) goto L_0x00e6;
    L_0x00c0:
        r0 = r21;
        r2 = r3.charAt(r0);
        r2 = mf.org.apache.xerces.impl.xpath.regex.REUtil.isHighSurrogate(r2);
        if (r2 == 0) goto L_0x00da;
    L_0x00cc:
        r2 = r21 + r33;
        if (r2 < 0) goto L_0x00da;
    L_0x00d0:
        r2 = r21 + r33;
        r0 = r30;
        r4 = r0.limit;
        if (r2 >= r4) goto L_0x00da;
    L_0x00d8:
        r21 = r21 + r33;
    L_0x00da:
        if (r33 <= 0) goto L_0x0114;
    L_0x00dc:
        r32 = r21 + 1;
    L_0x00de:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x00e6:
        r0 = r21;
        r15 = r3.charAt(r0);
        r2 = mf.org.apache.xerces.impl.xpath.regex.REUtil.isHighSurrogate(r15);
        if (r2 == 0) goto L_0x010a;
    L_0x00f2:
        r2 = r21 + r33;
        if (r2 < 0) goto L_0x010a;
    L_0x00f6:
        r2 = r21 + r33;
        r0 = r30;
        r4 = r0.limit;
        if (r2 >= r4) goto L_0x010a;
    L_0x00fe:
        r21 = r21 + r33;
        r0 = r21;
        r2 = r3.charAt(r0);
        r15 = mf.org.apache.xerces.impl.xpath.regex.REUtil.composeFromSurrogates(r15, r2);
    L_0x010a:
        r2 = isEOLChar(r15);
        if (r2 == 0) goto L_0x00da;
    L_0x0110:
        r25 = 1;
        goto L_0x0043;
    L_0x0114:
        r32 = r21;
        goto L_0x00de;
    L_0x0117:
        if (r33 <= 0) goto L_0x0129;
    L_0x0119:
        r21 = r32;
    L_0x011b:
        r0 = r30;
        r2 = r0.limit;
        r0 = r21;
        if (r0 >= r2) goto L_0x0125;
    L_0x0123:
        if (r21 >= 0) goto L_0x012c;
    L_0x0125:
        r25 = 1;
        goto L_0x0043;
    L_0x0129:
        r21 = r32 + -1;
        goto L_0x011b;
    L_0x012c:
        r0 = r32;
        r15 = r3.charAt(r0);
        r2 = mf.org.apache.xerces.impl.xpath.regex.REUtil.isHighSurrogate(r15);
        if (r2 == 0) goto L_0x0150;
    L_0x0138:
        r2 = r21 + r33;
        r0 = r30;
        r4 = r0.limit;
        if (r2 >= r4) goto L_0x0150;
    L_0x0140:
        r2 = r21 + r33;
        if (r2 < 0) goto L_0x0150;
    L_0x0144:
        r21 = r21 + r33;
        r0 = r21;
        r2 = r3.charAt(r0);
        r15 = mf.org.apache.xerces.impl.xpath.regex.REUtil.composeFromSurrogates(r15, r2);
    L_0x0150:
        r27 = r31.getToken();
        r0 = r27;
        r2 = r0.match(r15);
        if (r2 != 0) goto L_0x0160;
    L_0x015c:
        r25 = 1;
        goto L_0x0043;
    L_0x0160:
        if (r33 <= 0) goto L_0x016c;
    L_0x0162:
        r32 = r21 + 1;
    L_0x0164:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x016c:
        r32 = r21;
        goto L_0x0164;
    L_0x016f:
        r2 = r29;
        r4 = r31;
        r5 = r30;
        r6 = r32;
        r7 = r34;
        r2 = r2.matchAnchor(r3, r4, r5, r6, r7);
        if (r2 != 0) goto L_0x0183;
    L_0x017f:
        r25 = 1;
        goto L_0x0043;
    L_0x0183:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x018b:
        r23 = r31.getData();
        if (r23 <= 0) goto L_0x0199;
    L_0x0191:
        r0 = r29;
        r2 = r0.nofparen;
        r0 = r23;
        if (r0 < r2) goto L_0x01b0;
    L_0x0199:
        r2 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r5 = "Internal Error: Reference number must be more than zero: ";
        r4.<init>(r5);
        r0 = r23;
        r4 = r4.append(r0);
        r4 = r4.toString();
        r2.<init>(r4);
        throw r2;
    L_0x01b0:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r2 = r2.getBeginning(r0);
        if (r2 < 0) goto L_0x01c8;
    L_0x01bc:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r2 = r2.getEnd(r0);
        if (r2 >= 0) goto L_0x01cc;
    L_0x01c8:
        r25 = 1;
        goto L_0x0043;
    L_0x01cc:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r7 = r2.getBeginning(r0);
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r2 = r2.getEnd(r0);
        r8 = r2 - r7;
        if (r33 <= 0) goto L_0x01ff;
    L_0x01e4:
        r0 = r30;
        r6 = r0.limit;
        r4 = r10;
        r5 = r32;
        r2 = r3.regionMatches(r4, r5, r6, r7, r8);
        if (r2 != 0) goto L_0x01f5;
    L_0x01f1:
        r25 = 1;
        goto L_0x0043;
    L_0x01f5:
        r32 = r32 + r8;
    L_0x01f7:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x01ff:
        r5 = r32 - r8;
        r0 = r30;
        r6 = r0.limit;
        r4 = r10;
        r2 = r3.regionMatches(r4, r5, r6, r7, r8);
        if (r2 != 0) goto L_0x0210;
    L_0x020c:
        r25 = 1;
        goto L_0x0043;
    L_0x0210:
        r32 = r32 - r8;
        goto L_0x01f7;
    L_0x0213:
        r13 = r31.getString();
        r8 = r13.length();
        if (r33 <= 0) goto L_0x0239;
    L_0x021d:
        r0 = r30;
        r12 = r0.limit;
        r9 = r3;
        r11 = r32;
        r14 = r8;
        r2 = r9.regionMatches(r10, r11, r12, r13, r14);
        if (r2 != 0) goto L_0x022f;
    L_0x022b:
        r25 = 1;
        goto L_0x0043;
    L_0x022f:
        r32 = r32 + r8;
    L_0x0231:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x0239:
        r11 = r32 - r8;
        r0 = r30;
        r12 = r0.limit;
        r9 = r3;
        r14 = r8;
        r2 = r9.regionMatches(r10, r11, r12, r13, r14);
        if (r2 != 0) goto L_0x024b;
    L_0x0247:
        r25 = 1;
        goto L_0x0043;
    L_0x024b:
        r32 = r32 - r8;
        goto L_0x0231;
    L_0x024e:
        r18 = r31.getData();
        r0 = r30;
        r2 = r0.closureContexts;
        r2 = r2[r18];
        r0 = r32;
        r2 = r2.contains(r0);
        if (r2 == 0) goto L_0x0264;
    L_0x0260:
        r25 = 1;
        goto L_0x0043;
    L_0x0264:
        r0 = r30;
        r2 = r0.closureContexts;
        r2 = r2[r18];
        r0 = r32;
        r2.addOffset(r0);
    L_0x026f:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r31 = r31.getChild();
        goto L_0x0043;
    L_0x0283:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x0299:
        r2 = r31.size();
        if (r2 != 0) goto L_0x02a3;
    L_0x029f:
        r25 = 1;
        goto L_0x0043;
    L_0x02a3:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r2 = 0;
        r0 = r17;
        r0.push(r2);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r2 = 0;
        r0 = r31;
        r31 = r0.elementAt(r2);
        goto L_0x0043;
    L_0x02c0:
        r23 = r31.getData();
        r0 = r30;
        r2 = r0.match;
        if (r2 == 0) goto L_0x02f4;
    L_0x02ca:
        if (r23 <= 0) goto L_0x02fc;
    L_0x02cc:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r2 = r2.getBeginning(r0);
        r0 = r17;
        r0.push(r2);
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r1 = r32;
        r2.setBeginning(r0, r1);
    L_0x02e6:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
    L_0x02f4:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x02fc:
        r0 = r23;
        r0 = -r0;
        r19 = r0;
        r0 = r30;
        r2 = r0.match;
        r0 = r19;
        r2 = r2.getEnd(r0);
        r0 = r17;
        r0.push(r2);
        r0 = r30;
        r2 = r0.match;
        r0 = r19;
        r1 = r32;
        r2.setEnd(r0, r1);
        goto L_0x02e6;
    L_0x031c:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r33;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r0 = r31;
        r2 = r0.type;
        r4 = 20;
        if (r2 == r4) goto L_0x0341;
    L_0x0339:
        r0 = r31;
        r2 = r0.type;
        r4 = 21;
        if (r2 != r4) goto L_0x0349;
    L_0x0341:
        r33 = 1;
    L_0x0343:
        r31 = r31.getChild();
        goto L_0x0043;
    L_0x0349:
        r33 = -1;
        goto L_0x0343;
    L_0x034c:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r31 = r31.getChild();
        goto L_0x0043;
    L_0x0360:
        r20 = r34;
        r2 = r31.getData();
        r20 = r20 | r2;
        r2 = r31.getData2();
        r2 = r2 ^ -1;
        r20 = r20 & r2;
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r34;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r34 = r20;
        r31 = r31.getChild();
        goto L_0x0043;
    L_0x038d:
        r16 = r31;
        r16 = (mf.org.apache.xerces.impl.xpath.regex.Op.ConditionOp) r16;
        r0 = r16;
        r2 = r0.refNumber;
        if (r2 <= 0) goto L_0x03f4;
    L_0x0397:
        r0 = r16;
        r2 = r0.refNumber;
        r0 = r29;
        r4 = r0.nofparen;
        if (r2 < r4) goto L_0x03ba;
    L_0x03a1:
        r2 = new java.lang.RuntimeException;
        r4 = new java.lang.StringBuilder;
        r5 = "Internal Error: Reference number must be more than zero: ";
        r4.<init>(r5);
        r0 = r16;
        r5 = r0.refNumber;
        r4 = r4.append(r5);
        r4 = r4.toString();
        r2.<init>(r4);
        throw r2;
    L_0x03ba:
        r0 = r30;
        r2 = r0.match;
        r0 = r16;
        r4 = r0.refNumber;
        r2 = r2.getBeginning(r4);
        if (r2 < 0) goto L_0x03de;
    L_0x03c8:
        r0 = r30;
        r2 = r0.match;
        r0 = r16;
        r4 = r0.refNumber;
        r2 = r2.getEnd(r4);
        if (r2 < 0) goto L_0x03de;
    L_0x03d6:
        r0 = r16;
        r0 = r0.yes;
        r31 = r0;
        goto L_0x0043;
    L_0x03de:
        r0 = r16;
        r2 = r0.no;
        if (r2 == 0) goto L_0x03ec;
    L_0x03e4:
        r0 = r16;
        r0 = r0.no;
        r31 = r0;
        goto L_0x0043;
    L_0x03ec:
        r0 = r16;
        r0 = r0.next;
        r31 = r0;
        goto L_0x0043;
    L_0x03f4:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r0 = r16;
        r0 = r0.condition;
        r31 = r0;
        goto L_0x0043;
    L_0x040a:
        r31 = r22.pop();
        r31 = (mf.org.apache.xerces.impl.xpath.regex.Op) r31;
        r32 = r17.pop();
        r0 = r31;
        r2 = r0.type;
        switch(r2) {
            case 7: goto L_0x041d;
            case 8: goto L_0x0429;
            case 9: goto L_0x041d;
            case 10: goto L_0x0429;
            case 11: goto L_0x0433;
            case 12: goto L_0x041b;
            case 13: goto L_0x041b;
            case 14: goto L_0x041b;
            case 15: goto L_0x0468;
            case 16: goto L_0x041b;
            case 17: goto L_0x041b;
            case 18: goto L_0x041b;
            case 19: goto L_0x041b;
            case 20: goto L_0x048f;
            case 21: goto L_0x04a1;
            case 22: goto L_0x048f;
            case 23: goto L_0x04a1;
            case 24: goto L_0x04b7;
            case 25: goto L_0x04b3;
            case 26: goto L_0x04c5;
            default: goto L_0x041b;
        };
    L_0x041b:
        goto L_0x0043;
    L_0x041d:
        if (r24 >= 0) goto L_0x0043;
    L_0x041f:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        r25 = 0;
        goto L_0x0043;
    L_0x0429:
        if (r24 >= 0) goto L_0x0043;
    L_0x042b:
        r31 = r31.getChild();
        r25 = 0;
        goto L_0x0043;
    L_0x0433:
        r28 = r17.pop();
        if (r24 >= 0) goto L_0x0043;
    L_0x0439:
        r28 = r28 + 1;
        r2 = r31.size();
        r0 = r28;
        if (r0 >= r2) goto L_0x0464;
    L_0x0443:
        r0 = r22;
        r1 = r31;
        r0.push(r1);
        r0 = r17;
        r1 = r28;
        r0.push(r1);
        r0 = r17;
        r1 = r32;
        r0.push(r1);
        r0 = r31;
        r1 = r28;
        r31 = r0.elementAt(r1);
        r25 = 0;
        goto L_0x0043;
    L_0x0464:
        r24 = -1;
        goto L_0x0043;
    L_0x0468:
        r23 = r31.getData();
        r26 = r17.pop();
        if (r24 >= 0) goto L_0x0043;
    L_0x0472:
        if (r23 <= 0) goto L_0x0481;
    L_0x0474:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r1 = r26;
        r2.setBeginning(r0, r1);
        goto L_0x0043;
    L_0x0481:
        r0 = r30;
        r2 = r0.match;
        r0 = r23;
        r4 = -r0;
        r0 = r26;
        r2.setEnd(r4, r0);
        goto L_0x0043;
    L_0x048f:
        r33 = r17.pop();
        if (r24 < 0) goto L_0x049d;
    L_0x0495:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        r25 = 0;
    L_0x049d:
        r24 = -1;
        goto L_0x0043;
    L_0x04a1:
        r33 = r17.pop();
        if (r24 >= 0) goto L_0x04af;
    L_0x04a7:
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        r25 = 0;
    L_0x04af:
        r24 = -1;
        goto L_0x0043;
    L_0x04b3:
        r34 = r17.pop();
    L_0x04b7:
        if (r24 < 0) goto L_0x0043;
    L_0x04b9:
        r32 = r24;
        r0 = r31;
        r0 = r0.next;
        r31 = r0;
        r25 = 0;
        goto L_0x0043;
    L_0x04c5:
        r16 = r31;
        r16 = (mf.org.apache.xerces.impl.xpath.regex.Op.ConditionOp) r16;
        if (r24 < 0) goto L_0x04d5;
    L_0x04cb:
        r0 = r16;
        r0 = r0.yes;
        r31 = r0;
    L_0x04d1:
        r25 = 0;
        goto L_0x0043;
    L_0x04d5:
        r0 = r16;
        r2 = r0.no;
        if (r2 == 0) goto L_0x04e2;
    L_0x04db:
        r0 = r16;
        r0 = r0.no;
        r31 = r0;
        goto L_0x04d1;
    L_0x04e2:
        r0 = r16;
        r0 = r0.next;
        r31 = r0;
        goto L_0x04d1;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xpath.regex.RegularExpression.match(mf.org.apache.xerces.impl.xpath.regex.RegularExpression$Context, mf.org.apache.xerces.impl.xpath.regex.Op, int, int, int):int");
    }

    private boolean matchChar(int ch, int other, boolean ignoreCase) {
        if (ignoreCase) {
            return matchIgnoreCase(ch, other);
        }
        return ch == other ? true : DEBUG;
    }

    boolean matchAnchor(ExpressionTarget target, Op op, Context con, int offset, int opts) {
        int after;
        switch (op.getData()) {
            case Tokens.EXPRTOKEN_AXISNAME_CHILD /*36*/:
                if (isSet(opts, MULTIPLE_LINES)) {
                    if (offset != con.limit) {
                        if (offset >= con.limit) {
                            return DEBUG;
                        }
                        if (!isEOLChar(target.charAt(offset))) {
                            return DEBUG;
                        }
                    }
                } else if (!(offset == con.limit || (offset + WT_LETTER == con.limit && isEOLChar(target.charAt(offset))))) {
                    if (offset + WT_OTHER != con.limit || target.charAt(offset) != CharUtils.CR) {
                        return DEBUG;
                    }
                    if (target.charAt(offset + WT_LETTER) != '\n') {
                        return DEBUG;
                    }
                }
                break;
            case C0302R.styleable.Theme_popupMenuStyle /*60*/:
                if (con.length == 0 || offset == con.limit || getWordType(target, con.start, con.limit, offset, opts) != WT_LETTER) {
                    return DEBUG;
                }
                if (getPreviousWordType(target, con.start, con.limit, offset, opts) != WT_OTHER) {
                    return DEBUG;
                }
                break;
            case C0302R.styleable.Theme_editTextColor /*62*/:
                if (con.length == 0 || offset == con.start || getWordType(target, con.start, con.limit, offset, opts) != WT_OTHER) {
                    return DEBUG;
                }
                if (getPreviousWordType(target, con.start, con.limit, offset, opts) != WT_LETTER) {
                    return DEBUG;
                }
                break;
            case UNICODE_WORD_BOUNDARY /*64*/:
                if (offset != con.start) {
                    if (offset <= con.start) {
                        return DEBUG;
                    }
                    if (!isEOLChar(target.charAt(offset - 1))) {
                        return DEBUG;
                    }
                }
                break;
            case C0302R.styleable.Theme_textAppearanceSearchResultTitle /*65*/:
                if (offset != con.start) {
                    return DEBUG;
                }
                break;
            case C0302R.styleable.Theme_textAppearanceSearchResultSubtitle /*66*/:
                boolean go;
                if (con.length == 0) {
                    go = true;
                } else {
                    after = getWordType(target, con.start, con.limit, offset, opts);
                    if (after == 0 || after == getPreviousWordType(target, con.start, con.limit, offset, opts)) {
                        go = true;
                    } else {
                        go = DEBUG;
                    }
                }
                if (!go) {
                    return DEBUG;
                }
                break;
            case AdSize.LARGE_AD_HEIGHT /*90*/:
                if (!(offset == con.limit || (offset + WT_LETTER == con.limit && isEOLChar(target.charAt(offset))))) {
                    if (offset + WT_OTHER != con.limit || target.charAt(offset) != CharUtils.CR) {
                        return DEBUG;
                    }
                    if (target.charAt(offset + WT_LETTER) != '\n') {
                        return DEBUG;
                    }
                }
                break;
            case C0302R.styleable.Theme_alertDialogTheme /*94*/:
                if (isSet(opts, MULTIPLE_LINES)) {
                    if (offset != con.start) {
                        if (offset <= con.start || offset >= con.limit) {
                            return DEBUG;
                        }
                        if (!isEOLChar(target.charAt(offset - 1))) {
                            return DEBUG;
                        }
                    }
                } else if (offset != con.start) {
                    return DEBUG;
                }
                break;
            case C0302R.styleable.Theme_buttonBarNeutralButtonStyle /*98*/:
                if (con.length == 0) {
                    return DEBUG;
                }
                after = getWordType(target, con.start, con.limit, offset, opts);
                if (after == 0) {
                    return DEBUG;
                }
                if (after == getPreviousWordType(target, con.start, con.limit, offset, opts)) {
                    return DEBUG;
                }
                break;
            case 122:
                if (offset != con.limit) {
                    return DEBUG;
                }
                break;
        }
        return true;
    }

    private static final int getPreviousWordType(ExpressionTarget target, int begin, int end, int offset, int opts) {
        offset--;
        int ret = getWordType(target, begin, end, offset, opts);
        while (ret == 0) {
            offset--;
            ret = getWordType(target, begin, end, offset, opts);
        }
        return ret;
    }

    private static final int getWordType(ExpressionTarget target, int begin, int end, int offset, int opts) {
        if (offset < begin || offset >= end) {
            return WT_OTHER;
        }
        return getWordType0(target.charAt(offset), opts);
    }

    public boolean matches(CharacterIterator target) {
        return matches(target, null);
    }

    public boolean matches(CharacterIterator target, Match match) {
        Context con;
        int start = target.getBeginIndex();
        int end = target.getEndIndex();
        synchronized (this) {
            if (this.operations == null) {
                prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        synchronized (this.context) {
            con = this.context.inuse ? new Context() : this.context;
            con.reset(target, start, end, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(target);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        con.match = match;
        int matchEnd;
        if (isSet(this.options, XMLSCHEMA_MODE)) {
            matchEnd = match(con, this.operations, con.start, WT_LETTER, this.options);
            if (matchEnd != con.limit) {
                return DEBUG;
            }
            if (con.match != null) {
                con.match.setBeginning(WT_IGNORE, con.start);
                con.match.setEnd(WT_IGNORE, matchEnd);
            }
            con.setInUse(DEBUG);
            return true;
        } else if (this.fixedStringOnly) {
            int o = this.fixedStringTable.matches(target, con.start, con.limit);
            if (o >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(WT_IGNORE, o);
                    con.match.setEnd(WT_IGNORE, this.fixedString.length() + o);
                }
                con.setInUse(DEBUG);
                return true;
            }
            con.setInUse(DEBUG);
            return DEBUG;
        } else {
            int matchStart;
            if (this.fixedString != null) {
                if (this.fixedStringTable.matches(target, con.start, con.limit) < 0) {
                    con.setInUse(DEBUG);
                    return DEBUG;
                }
            }
            int limit = con.limit - this.minlength;
            matchEnd = -1;
            if (this.operations == null || this.operations.type != 7 || this.operations.getChild().type != 0) {
                if (this.firstChar == null) {
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        matchEnd = match(con, this.operations, matchStart, WT_LETTER, this.options);
                        if (matchEnd >= 0) {
                            break;
                        }
                        matchStart += WT_LETTER;
                    }
                } else {
                    RangeToken range = this.firstChar;
                    matchStart = con.start;
                    while (matchStart <= limit) {
                        int ch = target.setIndex(matchStart);
                        if (REUtil.isHighSurrogate(ch) && matchStart + WT_LETTER < con.limit) {
                            ch = REUtil.composeFromSurrogates(ch, target.setIndex(matchStart + WT_LETTER));
                        }
                        if (range.match(ch)) {
                            matchEnd = match(con, this.operations, matchStart, WT_LETTER, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        matchStart += WT_LETTER;
                    }
                }
            } else if (isSet(this.options, SINGLE_LINE)) {
                int matchStart2 = con.start;
                matchEnd = match(con, this.operations, con.start, WT_LETTER, this.options);
                matchStart = matchStart2;
            } else {
                boolean previousIsEOL = true;
                matchStart = con.start;
                while (matchStart <= limit) {
                    if (isEOLChar(target.setIndex(matchStart))) {
                        previousIsEOL = true;
                    } else {
                        if (previousIsEOL) {
                            matchEnd = match(con, this.operations, matchStart, WT_LETTER, this.options);
                            if (matchEnd >= 0) {
                                break;
                            }
                        }
                        previousIsEOL = DEBUG;
                    }
                    matchStart += WT_LETTER;
                }
            }
            if (matchEnd >= 0) {
                if (con.match != null) {
                    con.match.setBeginning(WT_IGNORE, matchStart);
                    con.match.setEnd(WT_IGNORE, matchEnd);
                }
                con.setInUse(DEBUG);
                return true;
            }
            con.setInUse(DEBUG);
            return DEBUG;
        }
    }

    void prepare() {
        compile(this.tokentree);
        this.minlength = this.tokentree.getMinLength();
        this.firstChar = null;
        if (!(isSet(this.options, PROHIBIT_HEAD_CHARACTER_OPTIMIZATION) || isSet(this.options, XMLSCHEMA_MODE))) {
            RangeToken firstChar = Token.createRange();
            if (this.tokentree.analyzeFirstCharacter(firstChar, this.options) == WT_LETTER) {
                firstChar.compactRanges();
                this.firstChar = firstChar;
            }
        }
        if (this.operations != null && ((this.operations.type == 6 || this.operations.type == WT_LETTER) && this.operations.next == null)) {
            this.fixedStringOnly = true;
            if (this.operations.type == 6) {
                this.fixedString = this.operations.getString();
            } else if (this.operations.getData() >= AccessibilityNodeInfoCompat.ACTION_CUT) {
                this.fixedString = REUtil.decomposeToSurrogates(this.operations.getData());
            } else {
                char[] ac = new char[WT_LETTER];
                ac[WT_IGNORE] = (char) this.operations.getData();
                this.fixedString = new String(ac);
            }
            this.fixedStringOptions = this.options;
            this.fixedStringTable = new BMPattern(this.fixedString, PROHIBIT_FIXED_STRING_OPTIMIZATION, isSet(this.fixedStringOptions, WT_OTHER));
        } else if (!isSet(this.options, PROHIBIT_FIXED_STRING_OPTIMIZATION) && !isSet(this.options, XMLSCHEMA_MODE)) {
            FixedStringContainer container = new FixedStringContainer();
            this.tokentree.findFixedString(container, this.options);
            this.fixedString = container.token == null ? null : container.token.getString();
            this.fixedStringOptions = container.options;
            if (this.fixedString != null && this.fixedString.length() < WT_OTHER) {
                this.fixedString = null;
            }
            if (this.fixedString != null) {
                this.fixedStringTable = new BMPattern(this.fixedString, PROHIBIT_FIXED_STRING_OPTIMIZATION, isSet(this.fixedStringOptions, WT_OTHER));
            }
        }
    }

    private static final boolean isSet(int options, int flag) {
        return (options & flag) == flag ? true : DEBUG;
    }

    public RegularExpression(String regex) throws ParseException {
        this(regex, null);
    }

    public RegularExpression(String regex, String options) throws ParseException {
        this.hasBackReferences = DEBUG;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = DEBUG;
        setPattern(regex, options);
    }

    public RegularExpression(String regex, String options, Locale locale) throws ParseException {
        this.hasBackReferences = DEBUG;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = DEBUG;
        setPattern(regex, options, locale);
    }

    RegularExpression(String regex, Token tok, int parens, boolean hasBackReferences, int options) {
        this.hasBackReferences = DEBUG;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = DEBUG;
        this.regex = regex;
        this.tokentree = tok;
        this.nofparen = parens;
        this.options = options;
        this.hasBackReferences = hasBackReferences;
    }

    public void setPattern(String newPattern) throws ParseException {
        setPattern(newPattern, Locale.getDefault());
    }

    public void setPattern(String newPattern, Locale locale) throws ParseException {
        setPattern(newPattern, this.options, locale);
    }

    private void setPattern(String newPattern, int options, Locale locale) throws ParseException {
        this.regex = newPattern;
        this.options = options;
        RegexParser rp = isSet(this.options, XMLSCHEMA_MODE) ? new ParserForXMLSchema(locale) : new RegexParser(locale);
        this.tokentree = rp.parse(this.regex, this.options);
        this.nofparen = rp.parennumber;
        this.hasBackReferences = rp.hasBackReferences;
        this.operations = null;
        this.context = null;
    }

    public void setPattern(String newPattern, String options) throws ParseException {
        setPattern(newPattern, options, Locale.getDefault());
    }

    public void setPattern(String newPattern, String options, Locale locale) throws ParseException {
        setPattern(newPattern, REUtil.parseOptions(options), locale);
    }

    public String getPattern() {
        return this.regex;
    }

    public String toString() {
        return this.tokentree.toString(this.options);
    }

    public String getOptions() {
        return REUtil.createOptionString(this.options);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof RegularExpression)) {
            return DEBUG;
        }
        RegularExpression r = (RegularExpression) obj;
        if (this.regex.equals(r.regex) && this.options == r.options) {
            return true;
        }
        return DEBUG;
    }

    boolean equals(String pattern, int options) {
        return (this.regex.equals(pattern) && this.options == options) ? true : DEBUG;
    }

    public int hashCode() {
        return (this.regex + "/" + getOptions()).hashCode();
    }

    public int getNumberOfGroups() {
        return this.nofparen;
    }

    private static final int getWordType0(char ch, int opts) {
        if (isSet(opts, UNICODE_WORD_BOUNDARY)) {
            switch (Character.getType(ch)) {
                case WT_LETTER /*1*/:
                case WT_OTHER /*2*/:
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                case SINGLE_LINE /*4*/:
                case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                case MULTIPLE_LINES /*8*/:
                case ConnectionResult.SERVICE_INVALID /*9*/:
                case LINE_FEED /*10*/:
                case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                    return WT_LETTER;
                case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                case ConnectionResult.NETWORK_ERROR /*7*/:
                case EXTENDED_COMMENT /*16*/:
                    return WT_IGNORE;
                case ConnectionResult.INTERRUPTED /*15*/:
                    switch (ch) {
                        case ConnectionResult.SERVICE_INVALID /*9*/:
                        case LINE_FEED /*10*/:
                        case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                        case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                        case CARRIAGE_RETURN /*13*/:
                            return WT_OTHER;
                        default:
                            return WT_IGNORE;
                    }
                default:
                    return WT_OTHER;
            }
        } else if (isSet(opts, USE_UNICODE_CATEGORY)) {
            if (Token.getRange("IsWord", true).match(ch)) {
                return WT_LETTER;
            }
            return WT_OTHER;
        } else if (isWordChar(ch)) {
            return WT_LETTER;
        } else {
            return WT_OTHER;
        }
    }

    private static final boolean isEOLChar(int ch) {
        return (ch == LINE_FEED || ch == CARRIAGE_RETURN || ch == LINE_SEPARATOR || ch == PARAGRAPH_SEPARATOR) ? true : DEBUG;
    }

    private static final boolean isWordChar(int ch) {
        if (ch == 95) {
            return true;
        }
        if (ch < 48) {
            return DEBUG;
        }
        if (ch > 122) {
            return DEBUG;
        }
        if (ch <= 57) {
            return true;
        }
        if (ch < 65) {
            return DEBUG;
        }
        if (ch <= 90 || ch >= 97) {
            return true;
        }
        return DEBUG;
    }

    private static final boolean matchIgnoreCase(int chardata, int ch) {
        if (chardata == ch) {
            return true;
        }
        if (chardata > SupportMenu.USER_MASK || ch > SupportMenu.USER_MASK) {
            return DEBUG;
        }
        char uch1 = Character.toUpperCase((char) chardata);
        char uch2 = Character.toUpperCase((char) ch);
        if (uch1 == uch2 || Character.toLowerCase(uch1) == Character.toLowerCase(uch2)) {
            return true;
        }
        return DEBUG;
    }
}
