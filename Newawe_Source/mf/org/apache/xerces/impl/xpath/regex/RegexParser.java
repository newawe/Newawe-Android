package mf.org.apache.xerces.impl.xpath.regex;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.ads.AdSize;
import com.startapp.android.publish.model.MetaData;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;
import mf.org.apache.xerces.impl.XMLEntityManager;
import org.apache.http.HttpStatus;

class RegexParser {
    protected static final int S_INBRACKETS = 1;
    protected static final int S_INXBRACKETS = 2;
    protected static final int S_NORMAL = 0;
    static final int T_BACKSOLIDUS = 10;
    static final int T_CARET = 11;
    static final int T_CHAR = 0;
    static final int T_COMMENT = 21;
    static final int T_CONDITION = 23;
    static final int T_DOLLAR = 12;
    static final int T_DOT = 8;
    static final int T_EOF = 1;
    static final int T_INDEPENDENT = 18;
    static final int T_LBRACKET = 9;
    static final int T_LOOKAHEAD = 14;
    static final int T_LOOKBEHIND = 16;
    static final int T_LPAREN = 6;
    static final int T_LPAREN2 = 13;
    static final int T_MODIFIERS = 22;
    static final int T_NEGATIVELOOKAHEAD = 15;
    static final int T_NEGATIVELOOKBEHIND = 17;
    static final int T_OR = 2;
    static final int T_PLUS = 4;
    static final int T_POSIX_CHARCLASS_START = 20;
    static final int T_QUESTION = 5;
    static final int T_RPAREN = 7;
    static final int T_SET_OPERATIONS = 19;
    static final int T_STAR = 3;
    static final int T_XMLSCHEMA_CC_SUBTRACTION = 24;
    int chardata;
    int context;
    boolean hasBackReferences;
    int nexttoken;
    int offset;
    int options;
    int parenOpened;
    int parennumber;
    Vector references;
    String regex;
    int regexlen;
    ResourceBundle resources;

    static class ReferencePosition {
        int position;
        int refNumber;

        ReferencePosition(int n, int pos) {
            this.refNumber = n;
            this.position = pos;
        }
    }

    public RegexParser() {
        this.context = T_CHAR;
        this.parenOpened = T_EOF;
        this.parennumber = T_EOF;
        this.references = null;
        setLocale(Locale.getDefault());
    }

    public RegexParser(Locale locale) {
        this.context = T_CHAR;
        this.parenOpened = T_EOF;
        this.parennumber = T_EOF;
        this.references = null;
        setLocale(locale);
    }

    public void setLocale(Locale locale) {
        if (locale != null) {
            try {
                this.resources = ResourceBundle.getBundle("mf.org.apache.xerces.impl.xpath.regex.message", locale);
                return;
            } catch (MissingResourceException mre) {
                throw new RuntimeException("Installation Problem???  Couldn't load messages: " + mre.getMessage());
            }
        }
        this.resources = ResourceBundle.getBundle("mf.org.apache.xerces.impl.xpath.regex.message");
    }

    final ParseException ex(String key, int loc) {
        return new ParseException(this.resources.getString(key), loc);
    }

    protected final boolean isSet(int flag) {
        return (this.options & flag) == flag;
    }

    synchronized Token parse(String regex, int options) throws ParseException {
        Token ret;
        this.options = options;
        this.offset = T_CHAR;
        setContext(T_CHAR);
        this.parennumber = T_EOF;
        this.parenOpened = T_EOF;
        this.hasBackReferences = false;
        this.regex = regex;
        if (isSet(T_LOOKBEHIND)) {
            this.regex = REUtil.stripExtendedComment(this.regex);
        }
        this.regexlen = this.regex.length();
        next();
        ret = parseRegex();
        if (this.offset != this.regexlen) {
            throw ex("parser.parse.1", this.offset);
        } else if (this.references != null) {
            for (int i = T_CHAR; i < this.references.size(); i += T_EOF) {
                ReferencePosition position = (ReferencePosition) this.references.elementAt(i);
                if (this.parennumber <= position.refNumber) {
                    throw ex("parser.parse.2", position.position);
                }
            }
            this.references.removeAllElements();
        }
        return ret;
    }

    protected final void setContext(int con) {
        this.context = con;
    }

    final int read() {
        return this.nexttoken;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void next() {
        /*
        r9 = this;
        r8 = 41;
        r7 = 1;
        r6 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x0011;
    L_0x000b:
        r3 = -1;
        r9.chardata = r3;
        r9.nexttoken = r7;
    L_0x0010:
        return;
    L_0x0011:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r0 = r3.charAt(r4);
        r9.chardata = r0;
        r3 = r9.context;
        if (r3 != r7) goto L_0x00b0;
    L_0x0023:
        switch(r0) {
            case 45: goto L_0x0072;
            case 91: goto L_0x008f;
            case 92: goto L_0x0050;
            default: goto L_0x0026;
        };
    L_0x0026:
        r3 = mf.org.apache.xerces.impl.xpath.regex.REUtil.isHighSurrogate(r0);
        if (r3 == 0) goto L_0x004c;
    L_0x002c:
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 >= r4) goto L_0x004c;
    L_0x0032:
        r3 = r9.regex;
        r4 = r9.offset;
        r1 = r3.charAt(r4);
        r3 = mf.org.apache.xerces.impl.xpath.regex.REUtil.isLowSurrogate(r1);
        if (r3 == 0) goto L_0x004c;
    L_0x0040:
        r3 = mf.org.apache.xerces.impl.xpath.regex.REUtil.composeFromSurrogates(r0, r1);
        r9.chardata = r3;
        r3 = r9.offset;
        r3 = r3 + 1;
        r9.offset = r3;
    L_0x004c:
        r2 = 0;
    L_0x004d:
        r9.nexttoken = r2;
        goto L_0x0010;
    L_0x0050:
        r2 = 10;
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x0063;
    L_0x0058:
        r3 = "parser.next.1";
        r4 = r9.offset;
        r4 = r4 + -1;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x0063:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r3 = r3.charAt(r4);
        r9.chardata = r3;
        goto L_0x004d;
    L_0x0072:
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 >= r4) goto L_0x008d;
    L_0x0078:
        r3 = r9.regex;
        r4 = r9.offset;
        r3 = r3.charAt(r4);
        r4 = 91;
        if (r3 != r4) goto L_0x008d;
    L_0x0084:
        r3 = r9.offset;
        r3 = r3 + 1;
        r9.offset = r3;
        r2 = 24;
        goto L_0x004d;
    L_0x008d:
        r2 = 0;
        goto L_0x004d;
    L_0x008f:
        r3 = r9.isSet(r6);
        if (r3 != 0) goto L_0x0026;
    L_0x0095:
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 >= r4) goto L_0x0026;
    L_0x009b:
        r3 = r9.regex;
        r4 = r9.offset;
        r3 = r3.charAt(r4);
        r4 = 58;
        if (r3 != r4) goto L_0x0026;
    L_0x00a7:
        r3 = r9.offset;
        r3 = r3 + 1;
        r9.offset = r3;
        r2 = 20;
        goto L_0x004d;
    L_0x00b0:
        switch(r0) {
            case 36: goto L_0x00d3;
            case 40: goto L_0x00de;
            case 41: goto L_0x00c0;
            case 42: goto L_0x00ba;
            case 43: goto L_0x00bc;
            case 46: goto L_0x00c2;
            case 63: goto L_0x00be;
            case 91: goto L_0x00c5;
            case 92: goto L_0x01b6;
            case 94: goto L_0x00c8;
            case 124: goto L_0x00b8;
            default: goto L_0x00b3;
        };
    L_0x00b3:
        r2 = 0;
    L_0x00b4:
        r9.nexttoken = r2;
        goto L_0x0010;
    L_0x00b8:
        r2 = 2;
        goto L_0x00b4;
    L_0x00ba:
        r2 = 3;
        goto L_0x00b4;
    L_0x00bc:
        r2 = 4;
        goto L_0x00b4;
    L_0x00be:
        r2 = 5;
        goto L_0x00b4;
    L_0x00c0:
        r2 = 7;
        goto L_0x00b4;
    L_0x00c2:
        r2 = 8;
        goto L_0x00b4;
    L_0x00c5:
        r2 = 9;
        goto L_0x00b4;
    L_0x00c8:
        r3 = r9.isSet(r6);
        if (r3 == 0) goto L_0x00d0;
    L_0x00ce:
        r2 = 0;
        goto L_0x00b4;
    L_0x00d0:
        r2 = 11;
        goto L_0x00b4;
    L_0x00d3:
        r3 = r9.isSet(r6);
        if (r3 == 0) goto L_0x00db;
    L_0x00d9:
        r2 = 0;
        goto L_0x00b4;
    L_0x00db:
        r2 = 12;
        goto L_0x00b4;
    L_0x00de:
        r2 = 6;
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 >= r4) goto L_0x00b4;
    L_0x00e5:
        r3 = r9.regex;
        r4 = r9.offset;
        r3 = r3.charAt(r4);
        r4 = 63;
        if (r3 != r4) goto L_0x00b4;
    L_0x00f1:
        r3 = r9.offset;
        r3 = r3 + 1;
        r9.offset = r3;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x0106;
    L_0x00fb:
        r3 = "parser.next.2";
        r4 = r9.offset;
        r4 = r4 + -1;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x0106:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r0 = r3.charAt(r4);
        switch(r0) {
            case 33: goto L_0x0139;
            case 35: goto L_0x0198;
            case 58: goto L_0x0132;
            case 60: goto L_0x0145;
            case 61: goto L_0x0135;
            case 62: goto L_0x0141;
            case 91: goto L_0x013d;
            default: goto L_0x0115;
        };
    L_0x0115:
        r3 = 45;
        if (r0 == r3) goto L_0x0129;
    L_0x0119:
        r3 = 97;
        if (r3 > r0) goto L_0x0121;
    L_0x011d:
        r3 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        if (r0 <= r3) goto L_0x0129;
    L_0x0121:
        r3 = 65;
        if (r3 > r0) goto L_0x01a3;
    L_0x0125:
        r3 = 90;
        if (r0 > r3) goto L_0x01a3;
    L_0x0129:
        r3 = r9.offset;
        r3 = r3 + -1;
        r9.offset = r3;
        r2 = 22;
        goto L_0x00b4;
    L_0x0132:
        r2 = 13;
        goto L_0x00b4;
    L_0x0135:
        r2 = 14;
        goto L_0x00b4;
    L_0x0139:
        r2 = 15;
        goto L_0x00b4;
    L_0x013d:
        r2 = 19;
        goto L_0x00b4;
    L_0x0141:
        r2 = 18;
        goto L_0x00b4;
    L_0x0145:
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x0156;
    L_0x014b:
        r3 = "parser.next.2";
        r4 = r9.offset;
        r4 = r4 + -3;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x0156:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r0 = r3.charAt(r4);
        r3 = 61;
        if (r0 != r3) goto L_0x016a;
    L_0x0166:
        r2 = 16;
        goto L_0x00b4;
    L_0x016a:
        r3 = 33;
        if (r0 != r3) goto L_0x0172;
    L_0x016e:
        r2 = 17;
        goto L_0x00b4;
    L_0x0172:
        r3 = "parser.next.3";
        r4 = r9.offset;
        r4 = r4 + -3;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x017d:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r0 = r3.charAt(r4);
        if (r0 != r8) goto L_0x0198;
    L_0x018b:
        if (r0 == r8) goto L_0x019f;
    L_0x018d:
        r3 = "parser.next.4";
        r4 = r9.offset;
        r4 = r4 + -1;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x0198:
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x017d;
    L_0x019e:
        goto L_0x018b;
    L_0x019f:
        r2 = 21;
        goto L_0x00b4;
    L_0x01a3:
        r3 = 40;
        if (r0 != r3) goto L_0x01ab;
    L_0x01a7:
        r2 = 23;
        goto L_0x00b4;
    L_0x01ab:
        r3 = "parser.next.2";
        r4 = r9.offset;
        r4 = r4 + -2;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x01b6:
        r2 = 10;
        r3 = r9.offset;
        r4 = r9.regexlen;
        if (r3 < r4) goto L_0x01c9;
    L_0x01be:
        r3 = "parser.next.1";
        r4 = r9.offset;
        r4 = r4 + -1;
        r3 = r9.ex(r3, r4);
        throw r3;
    L_0x01c9:
        r3 = r9.regex;
        r4 = r9.offset;
        r5 = r4 + 1;
        r9.offset = r5;
        r3 = r3.charAt(r4);
        r9.chardata = r3;
        goto L_0x00b4;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xpath.regex.RegexParser.next():void");
    }

    Token parseRegex() throws ParseException {
        Token tok = parseTerm();
        Token parent = null;
        while (read() == T_OR) {
            next();
            if (parent == null) {
                parent = Token.createUnion();
                parent.addChild(tok);
                tok = parent;
            }
            tok.addChild(parseTerm());
        }
        return tok;
    }

    Token parseTerm() throws ParseException {
        int ch = read();
        if (ch == T_OR || ch == T_RPAREN || ch == T_EOF) {
            return Token.createEmpty();
        }
        Token tok = parseFactor();
        Token concat = null;
        while (true) {
            ch = read();
            if (ch == T_OR || ch == T_RPAREN || ch == T_EOF) {
                return tok;
            }
            if (concat == null) {
                concat = Token.createConcat();
                concat.addChild(tok);
                tok = concat;
            }
            concat.addChild(parseFactor());
        }
    }

    Token processCaret() throws ParseException {
        next();
        return Token.token_linebeginning;
    }

    Token processDollar() throws ParseException {
        next();
        return Token.token_lineend;
    }

    Token processLookahead() throws ParseException {
        next();
        Token tok = Token.createLook(T_POSIX_CHARCLASS_START, parseRegex());
        if (read() != T_RPAREN) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processNegativelookahead() throws ParseException {
        next();
        Token tok = Token.createLook(T_COMMENT, parseRegex());
        if (read() != T_RPAREN) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processLookbehind() throws ParseException {
        next();
        Token tok = Token.createLook(T_MODIFIERS, parseRegex());
        if (read() != T_RPAREN) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processNegativelookbehind() throws ParseException {
        next();
        Token tok = Token.createLook(T_CONDITION, parseRegex());
        if (read() != T_RPAREN) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processBacksolidus_A() throws ParseException {
        next();
        return Token.token_stringbeginning;
    }

    Token processBacksolidus_Z() throws ParseException {
        next();
        return Token.token_stringend2;
    }

    Token processBacksolidus_z() throws ParseException {
        next();
        return Token.token_stringend;
    }

    Token processBacksolidus_b() throws ParseException {
        next();
        return Token.token_wordedge;
    }

    Token processBacksolidus_B() throws ParseException {
        next();
        return Token.token_not_wordedge;
    }

    Token processBacksolidus_lt() throws ParseException {
        next();
        return Token.token_wordbeginning;
    }

    Token processBacksolidus_gt() throws ParseException {
        next();
        return Token.token_wordend;
    }

    Token processStar(Token tok) throws ParseException {
        next();
        if (read() != T_QUESTION) {
            return Token.createClosure(tok);
        }
        next();
        return Token.createNGClosure(tok);
    }

    Token processPlus(Token tok) throws ParseException {
        next();
        if (read() != T_QUESTION) {
            return Token.createConcat(tok, Token.createClosure(tok));
        }
        next();
        return Token.createConcat(tok, Token.createNGClosure(tok));
    }

    Token processQuestion(Token tok) throws ParseException {
        next();
        Token par = Token.createUnion();
        if (read() == T_QUESTION) {
            next();
            par.addChild(Token.createEmpty());
            par.addChild(tok);
        } else {
            par.addChild(tok);
            par.addChild(Token.createEmpty());
        }
        return par;
    }

    boolean checkQuestion(int off) {
        return off < this.regexlen && this.regex.charAt(off) == '?';
    }

    Token processParen() throws ParseException {
        next();
        int p = this.parenOpened;
        this.parenOpened = p + T_EOF;
        Token tok = Token.createParen(parseRegex(), p);
        if (read() != T_RPAREN) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        this.parennumber += T_EOF;
        next();
        return tok;
    }

    Token processParen2() throws ParseException {
        next();
        Token tok = Token.createParen(parseRegex(), T_CHAR);
        if (read() != T_RPAREN) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    mf.org.apache.xerces.impl.xpath.regex.Token processCondition() throws mf.org.apache.xerces.impl.xpath.regex.ParseException {
        /*
        r12 = this;
        r8 = 57;
        r11 = 7;
        r10 = 2;
        r9 = 1;
        r6 = r12.offset;
        r6 = r6 + 1;
        r7 = r12.regexlen;
        if (r6 < r7) goto L_0x0016;
    L_0x000d:
        r6 = "parser.factor.4";
        r7 = r12.offset;
        r6 = r12.ex(r6, r7);
        throw r6;
    L_0x0016:
        r4 = -1;
        r1 = 0;
        r6 = r12.regex;
        r7 = r12.offset;
        r0 = r6.charAt(r7);
        r6 = 49;
        if (r6 > r0) goto L_0x00b4;
    L_0x0024:
        if (r0 > r8) goto L_0x00b4;
    L_0x0026:
        r4 = r0 + -48;
        r2 = r4;
        r6 = r12.parennumber;
        if (r6 > r4) goto L_0x0057;
    L_0x002d:
        r6 = "parser.parse.2";
        r7 = r12.offset;
        r6 = r12.ex(r6, r7);
        throw r6;
    L_0x0036:
        r6 = r12.regex;
        r7 = r12.offset;
        r7 = r7 + 1;
        r0 = r6.charAt(r7);
        r6 = 48;
        if (r6 > r0) goto L_0x005f;
    L_0x0044:
        if (r0 > r8) goto L_0x005f;
    L_0x0046:
        r6 = r4 * 10;
        r7 = r0 + -48;
        r4 = r6 + r7;
        r6 = r12.parennumber;
        if (r4 >= r6) goto L_0x005f;
    L_0x0050:
        r2 = r4;
        r6 = r12.offset;
        r6 = r6 + 1;
        r12.offset = r6;
    L_0x0057:
        r6 = r12.offset;
        r6 = r6 + 1;
        r7 = r12.regexlen;
        if (r6 < r7) goto L_0x0036;
    L_0x005f:
        r12.hasBackReferences = r9;
        r6 = r12.references;
        if (r6 != 0) goto L_0x006c;
    L_0x0065:
        r6 = new java.util.Vector;
        r6.<init>();
        r12.references = r6;
    L_0x006c:
        r6 = r12.references;
        r7 = new mf.org.apache.xerces.impl.xpath.regex.RegexParser$ReferencePosition;
        r8 = r12.offset;
        r7.<init>(r2, r8);
        r6.addElement(r7);
        r6 = r12.offset;
        r6 = r6 + 1;
        r12.offset = r6;
        r6 = r12.regex;
        r7 = r12.offset;
        r6 = r6.charAt(r7);
        r7 = 41;
        if (r6 == r7) goto L_0x0093;
    L_0x008a:
        r6 = "parser.factor.1";
        r7 = r12.offset;
        r6 = r12.ex(r6, r7);
        throw r6;
    L_0x0093:
        r6 = r12.offset;
        r6 = r6 + 1;
        r12.offset = r6;
    L_0x0099:
        r12.next();
        r5 = r12.parseRegex();
        r3 = 0;
        r6 = r5.type;
        if (r6 != r10) goto L_0x00ed;
    L_0x00a5:
        r6 = r5.size();
        if (r6 == r10) goto L_0x00e4;
    L_0x00ab:
        r6 = "parser.factor.6";
        r7 = r12.offset;
        r6 = r12.ex(r6, r7);
        throw r6;
    L_0x00b4:
        r6 = 63;
        if (r0 != r6) goto L_0x00be;
    L_0x00b8:
        r6 = r12.offset;
        r6 = r6 + -1;
        r12.offset = r6;
    L_0x00be:
        r12.next();
        r1 = r12.parseFactor();
        r6 = r1.type;
        switch(r6) {
            case 8: goto L_0x00d3;
            case 20: goto L_0x0099;
            case 21: goto L_0x0099;
            case 22: goto L_0x0099;
            case 23: goto L_0x0099;
            default: goto L_0x00ca;
        };
    L_0x00ca:
        r6 = "parser.factor.5";
        r7 = r12.offset;
        r6 = r12.ex(r6, r7);
        throw r6;
    L_0x00d3:
        r6 = r12.read();
        if (r6 == r11) goto L_0x0099;
    L_0x00d9:
        r6 = "parser.factor.1";
        r7 = r12.offset;
        r7 = r7 + -1;
        r6 = r12.ex(r6, r7);
        throw r6;
    L_0x00e4:
        r3 = r5.getChild(r9);
        r6 = 0;
        r5 = r5.getChild(r6);
    L_0x00ed:
        r6 = r12.read();
        if (r6 == r11) goto L_0x00fe;
    L_0x00f3:
        r6 = "parser.factor.1";
        r7 = r12.offset;
        r7 = r7 + -1;
        r6 = r12.ex(r6, r7);
        throw r6;
    L_0x00fe:
        r12.next();
        r6 = mf.org.apache.xerces.impl.xpath.regex.Token.createCondition(r4, r1, r5, r3);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xpath.regex.RegexParser.processCondition():mf.org.apache.xerces.impl.xpath.regex.Token");
    }

    Token processModifiers() throws ParseException {
        int add = T_CHAR;
        int mask = T_CHAR;
        int ch = -1;
        while (this.offset < this.regexlen) {
            ch = this.regex.charAt(this.offset);
            int v = REUtil.getOptionValue(ch);
            if (v == 0) {
                break;
            }
            add |= v;
            this.offset += T_EOF;
        }
        if (this.offset >= this.regexlen) {
            throw ex("parser.factor.2", this.offset - 1);
        }
        if (ch == 45) {
            this.offset += T_EOF;
            while (this.offset < this.regexlen) {
                ch = this.regex.charAt(this.offset);
                v = REUtil.getOptionValue(ch);
                if (v == 0) {
                    break;
                }
                mask |= v;
                this.offset += T_EOF;
            }
            if (this.offset >= this.regexlen) {
                throw ex("parser.factor.2", this.offset - 1);
            }
        }
        if (ch == 58) {
            this.offset += T_EOF;
            next();
            Token tok = Token.createModifierGroup(parseRegex(), add, mask);
            if (read() != T_RPAREN) {
                throw ex("parser.factor.1", this.offset - 1);
            }
            next();
            return tok;
        } else if (ch == 41) {
            this.offset += T_EOF;
            next();
            return Token.createModifierGroup(parseRegex(), add, mask);
        } else {
            throw ex("parser.factor.3", this.offset);
        }
    }

    Token processIndependent() throws ParseException {
        next();
        Token tok = Token.createLook(T_XMLSCHEMA_CC_SUBTRACTION, parseRegex());
        if (read() != T_RPAREN) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return tok;
    }

    Token processBacksolidus_c() throws ParseException {
        if (this.offset < this.regexlen) {
            String str = this.regex;
            int i = this.offset;
            this.offset = i + T_EOF;
            int ch2 = str.charAt(i);
            if ((65504 & ch2) == 64) {
                next();
                return Token.createChar(ch2 - 64);
            }
        }
        throw ex("parser.atom.1", this.offset - 1);
    }

    Token processBacksolidus_C() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_i() throws ParseException {
        Token tok = Token.createChar(C0302R.styleable.Theme_radioButtonStyle);
        next();
        return tok;
    }

    Token processBacksolidus_I() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    Token processBacksolidus_g() throws ParseException {
        next();
        return Token.getGraphemePattern();
    }

    Token processBacksolidus_X() throws ParseException {
        next();
        return Token.getCombiningCharacterSequence();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    mf.org.apache.xerces.impl.xpath.regex.Token processBackreference() throws mf.org.apache.xerces.impl.xpath.regex.ParseException {
        /*
        r7 = this;
        r4 = r7.chardata;
        r2 = r4 + -48;
        r1 = r2;
        r4 = r7.parennumber;
        if (r4 > r2) goto L_0x0037;
    L_0x0009:
        r4 = "parser.parse.2";
        r5 = r7.offset;
        r5 = r5 + -2;
        r4 = r7.ex(r4, r5);
        throw r4;
    L_0x0014:
        r4 = r7.regex;
        r5 = r7.offset;
        r0 = r4.charAt(r5);
        r4 = 48;
        if (r4 > r0) goto L_0x003d;
    L_0x0020:
        r4 = 57;
        if (r0 > r4) goto L_0x003d;
    L_0x0024:
        r4 = r2 * 10;
        r5 = r0 + -48;
        r2 = r4 + r5;
        r4 = r7.parennumber;
        if (r2 >= r4) goto L_0x003d;
    L_0x002e:
        r4 = r7.offset;
        r4 = r4 + 1;
        r7.offset = r4;
        r1 = r2;
        r7.chardata = r0;
    L_0x0037:
        r4 = r7.offset;
        r5 = r7.regexlen;
        if (r4 < r5) goto L_0x0014;
    L_0x003d:
        r3 = mf.org.apache.xerces.impl.xpath.regex.Token.createBackReference(r1);
        r4 = 1;
        r7.hasBackReferences = r4;
        r4 = r7.references;
        if (r4 != 0) goto L_0x004f;
    L_0x0048:
        r4 = new java.util.Vector;
        r4.<init>();
        r7.references = r4;
    L_0x004f:
        r4 = r7.references;
        r5 = new mf.org.apache.xerces.impl.xpath.regex.RegexParser$ReferencePosition;
        r6 = r7.offset;
        r6 = r6 + -2;
        r5.<init>(r1, r6);
        r4.addElement(r5);
        r7.next();
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xpath.regex.RegexParser.processBackreference():mf.org.apache.xerces.impl.xpath.regex.Token");
    }

    Token parseFactor() throws ParseException {
        switch (read()) {
            case T_BACKSOLIDUS /*10*/:
                switch (this.chardata) {
                    case C0302R.styleable.Theme_popupMenuStyle /*60*/:
                        return processBacksolidus_lt();
                    case C0302R.styleable.Theme_editTextColor /*62*/:
                        return processBacksolidus_gt();
                    case C0302R.styleable.Theme_textAppearanceSearchResultTitle /*65*/:
                        return processBacksolidus_A();
                    case C0302R.styleable.Theme_textAppearanceSearchResultSubtitle /*66*/:
                        return processBacksolidus_B();
                    case AdSize.LARGE_AD_HEIGHT /*90*/:
                        return processBacksolidus_Z();
                    case C0302R.styleable.Theme_buttonBarNeutralButtonStyle /*98*/:
                        return processBacksolidus_b();
                    case 122:
                        return processBacksolidus_z();
                    default:
                        break;
                }
            case T_CARET /*11*/:
                return processCaret();
            case T_DOLLAR /*12*/:
                return processDollar();
            case T_LOOKAHEAD /*14*/:
                return processLookahead();
            case T_NEGATIVELOOKAHEAD /*15*/:
                return processNegativelookahead();
            case T_LOOKBEHIND /*16*/:
                return processLookbehind();
            case T_NEGATIVELOOKBEHIND /*17*/:
                return processNegativelookbehind();
            case T_COMMENT /*21*/:
                next();
                return Token.createEmpty();
        }
        Token tok = parseAtom();
        switch (read()) {
            case T_CHAR /*0*/:
                if (this.chardata != 123 || this.offset >= this.regexlen) {
                    return tok;
                }
                int off = this.offset;
                int off2 = off + T_EOF;
                int ch = this.regex.charAt(off);
                if (ch < 48 || ch > 57) {
                    throw ex("parser.quantifier.1", this.offset);
                }
                int max;
                int min = ch - 48;
                off = off2;
                while (off < this.regexlen) {
                    off2 = off + T_EOF;
                    ch = this.regex.charAt(off);
                    if (ch >= 48 && ch <= 57) {
                        min = ((min * T_BACKSOLIDUS) + ch) - 48;
                        if (min < 0) {
                            throw ex("parser.quantifier.5", this.offset);
                        }
                        off = off2;
                    }
                    max = min;
                    if (ch == 44) {
                        off = off2;
                    } else if (off2 < this.regexlen) {
                        throw ex("parser.quantifier.3", this.offset);
                    } else {
                        off = off2 + T_EOF;
                        ch = this.regex.charAt(off2);
                        if (ch >= 48 || ch > 57) {
                            max = -1;
                        } else {
                            max = ch - 48;
                            while (off < this.regexlen) {
                                off2 = off + T_EOF;
                                ch = this.regex.charAt(off);
                                if (ch < 48 || ch > 57) {
                                    off = off2;
                                    if (min > max) {
                                        throw ex("parser.quantifier.4", this.offset);
                                    }
                                }
                                max = ((max * T_BACKSOLIDUS) + ch) - 48;
                                if (max < 0) {
                                    throw ex("parser.quantifier.5", this.offset);
                                }
                                off = off2;
                            }
                            if (min > max) {
                                throw ex("parser.quantifier.4", this.offset);
                            }
                        }
                    }
                    if (ch == 125) {
                        throw ex("parser.quantifier.2", this.offset);
                    }
                    if (checkQuestion(off)) {
                        tok = Token.createClosure(tok);
                        this.offset = off;
                    } else {
                        tok = Token.createNGClosure(tok);
                        this.offset = off + T_EOF;
                    }
                    tok.setMin(min);
                    tok.setMax(max);
                    next();
                    return tok;
                }
                off2 = off;
                max = min;
                if (ch == 44) {
                    off = off2;
                } else if (off2 < this.regexlen) {
                    off = off2 + T_EOF;
                    ch = this.regex.charAt(off2);
                    if (ch >= 48) {
                    }
                    max = -1;
                } else {
                    throw ex("parser.quantifier.3", this.offset);
                }
                if (ch == 125) {
                    if (checkQuestion(off)) {
                        tok = Token.createClosure(tok);
                        this.offset = off;
                    } else {
                        tok = Token.createNGClosure(tok);
                        this.offset = off + T_EOF;
                    }
                    tok.setMin(min);
                    tok.setMax(max);
                    next();
                    return tok;
                }
                throw ex("parser.quantifier.2", this.offset);
            case T_STAR /*3*/:
                return processStar(tok);
            case T_PLUS /*4*/:
                return processPlus(tok);
            case T_QUESTION /*5*/:
                return processQuestion(tok);
            default:
                return tok;
        }
    }

    Token parseAtom() throws ParseException {
        Token tok;
        switch (read()) {
            case T_CHAR /*0*/:
                if (this.chardata != 93 && this.chardata != 123 && this.chardata != 125) {
                    tok = Token.createChar(this.chardata);
                    int high = this.chardata;
                    next();
                    if (REUtil.isHighSurrogate(high) && read() == 0 && REUtil.isLowSurrogate(this.chardata)) {
                        char[] sur = new char[T_OR];
                        sur[T_CHAR] = (char) high;
                        sur[T_EOF] = (char) this.chardata;
                        tok = Token.createParen(Token.createString(new String(sur)), T_CHAR);
                        next();
                        break;
                    }
                }
                throw ex("parser.atom.4", this.offset - 1);
                break;
            case T_LPAREN /*6*/:
                return processParen();
            case T_DOT /*8*/:
                next();
                tok = Token.token_dot;
                break;
            case T_LBRACKET /*9*/:
                return parseCharacterClass(true);
            case T_BACKSOLIDUS /*10*/:
                switch (this.chardata) {
                    case C0302R.styleable.Theme_actionButtonStyle /*49*/:
                    case MetaData.DEFAULT_HTML_3D_PROBABILITY_3D /*50*/:
                    case C0302R.styleable.Theme_buttonBarButtonStyle /*51*/:
                    case C0302R.styleable.Theme_selectableItemBackground /*52*/:
                    case C0302R.styleable.Theme_selectableItemBackgroundBorderless /*53*/:
                    case C0302R.styleable.Theme_borderlessButtonStyle /*54*/:
                    case C0302R.styleable.Theme_dividerVertical /*55*/:
                    case C0302R.styleable.Theme_dividerHorizontal /*56*/:
                    case C0302R.styleable.Theme_activityChooserViewStyle /*57*/:
                        return processBackreference();
                    case C0302R.styleable.Theme_textColorSearchUrl /*67*/:
                        return processBacksolidus_C();
                    case C0302R.styleable.Theme_searchViewStyle /*68*/:
                    case C0302R.styleable.Theme_colorPrimaryDark /*83*/:
                    case C0302R.styleable.Theme_colorControlHighlight /*87*/:
                    case HttpStatus.SC_CONTINUE /*100*/:
                    case 115:
                    case 119:
                        tok = getTokenForShorthand(this.chardata);
                        next();
                        return tok;
                    case C0302R.styleable.Theme_listPreferredItemPaddingRight /*73*/:
                        return processBacksolidus_I();
                    case MetaData.DEFAULT_PROBABILITY_3D /*80*/:
                    case 112:
                        int pstart = this.offset;
                        tok = processBacksolidus_pP(this.chardata);
                        if (tok == null) {
                            throw ex("parser.atom.5", pstart);
                        }
                        break;
                    case C0302R.styleable.Theme_colorButtonNormal /*88*/:
                        return processBacksolidus_X();
                    case C0302R.styleable.Theme_autoCompleteTextViewStyle /*99*/:
                        return processBacksolidus_c();
                    case HttpStatus.SC_SWITCHING_PROTOCOLS /*101*/:
                    case HttpStatus.SC_PROCESSING /*102*/:
                    case 110:
                    case 114:
                    case 116:
                    case 117:
                    case 118:
                    case 120:
                        int ch2 = decodeEscaped();
                        if (ch2 >= AccessibilityNodeInfoCompat.ACTION_CUT) {
                            tok = Token.createString(REUtil.decomposeToSurrogates(ch2));
                            break;
                        }
                        tok = Token.createChar(ch2);
                        break;
                    case C0302R.styleable.Theme_checkedTextViewStyle /*103*/:
                        return processBacksolidus_g();
                    case C0302R.styleable.Theme_radioButtonStyle /*105*/:
                        return processBacksolidus_i();
                    default:
                        tok = Token.createChar(this.chardata);
                        break;
                }
                next();
                break;
            case T_LPAREN2 /*13*/:
                return processParen2();
            case T_INDEPENDENT /*18*/:
                return processIndependent();
            case T_SET_OPERATIONS /*19*/:
                return parseSetOperations();
            case T_MODIFIERS /*22*/:
                return processModifiers();
            case T_CONDITION /*23*/:
                return processCondition();
            default:
                throw ex("parser.atom.4", this.offset - 1);
        }
        return tok;
    }

    protected RangeToken processBacksolidus_pP(int c) throws ParseException {
        next();
        if (read() == 0 && this.chardata == 123) {
            boolean positive = c == 112;
            int namestart = this.offset;
            int nameend = this.regex.indexOf(125, namestart);
            if (nameend < 0) {
                throw ex("parser.atom.3", this.offset);
            }
            String pname = this.regex.substring(namestart, nameend);
            this.offset = nameend + T_EOF;
            return Token.getRange(pname, positive, isSet(XMLEntityManager.DEFAULT_INTERNAL_BUFFER_SIZE));
        }
        throw ex("parser.atom.2", this.offset - 1);
    }

    int processCIinCharacterClass(RangeToken tok, int c) {
        return decodeEscaped();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected mf.org.apache.xerces.impl.xpath.regex.RangeToken parseCharacterClass(boolean r22) throws mf.org.apache.xerces.impl.xpath.regex.ParseException {
        /*
        r21 = this;
        r18 = 1;
        r0 = r21;
        r1 = r18;
        r0.setContext(r1);
        r21.next();
        r9 = 0;
        r3 = 0;
        r18 = r21.read();
        if (r18 != 0) goto L_0x006d;
    L_0x0014:
        r0 = r21;
        r0 = r0.chardata;
        r18 = r0;
        r19 = 94;
        r0 = r18;
        r1 = r19;
        if (r0 != r1) goto L_0x006d;
    L_0x0022:
        r9 = 1;
        r21.next();
        if (r22 == 0) goto L_0x0058;
    L_0x0028:
        r15 = mf.org.apache.xerces.impl.xpath.regex.Token.createNRange();
    L_0x002c:
        r6 = 1;
    L_0x002d:
        r17 = r21.read();
        r18 = 1;
        r0 = r17;
        r1 = r18;
        if (r0 != r1) goto L_0x0072;
    L_0x0039:
        r18 = r21.read();
        r19 = 1;
        r0 = r18;
        r1 = r19;
        if (r0 != r1) goto L_0x0303;
    L_0x0045:
        r18 = "parser.cc.2";
        r0 = r21;
        r0 = r0.offset;
        r19 = r0;
        r0 = r21;
        r1 = r18;
        r2 = r19;
        r18 = r0.ex(r1, r2);
        throw r18;
    L_0x0058:
        r3 = mf.org.apache.xerces.impl.xpath.regex.Token.createRange();
        r18 = 0;
        r19 = 1114111; // 0x10ffff float:1.561202E-39 double:5.50444E-318;
        r0 = r18;
        r1 = r19;
        r3.addRange(r0, r1);
        r15 = mf.org.apache.xerces.impl.xpath.regex.Token.createRange();
        goto L_0x002c;
    L_0x006d:
        r15 = mf.org.apache.xerces.impl.xpath.regex.Token.createRange();
        goto L_0x002c;
    L_0x0072:
        if (r17 != 0) goto L_0x0084;
    L_0x0074:
        r0 = r21;
        r0 = r0.chardata;
        r18 = r0;
        r19 = 93;
        r0 = r18;
        r1 = r19;
        if (r0 != r1) goto L_0x0084;
    L_0x0082:
        if (r6 == 0) goto L_0x0039;
    L_0x0084:
        r0 = r21;
        r4 = r0.chardata;
        r5 = 0;
        r18 = 10;
        r0 = r17;
        r1 = r18;
        if (r0 != r1) goto L_0x0121;
    L_0x0091:
        switch(r4) {
            case 67: goto L_0x00fa;
            case 68: goto L_0x00ed;
            case 73: goto L_0x00fa;
            case 80: goto L_0x0104;
            case 83: goto L_0x00ed;
            case 87: goto L_0x00ed;
            case 99: goto L_0x00fa;
            case 100: goto L_0x00ed;
            case 105: goto L_0x00fa;
            case 112: goto L_0x0104;
            case 115: goto L_0x00ed;
            case 119: goto L_0x00ed;
            default: goto L_0x0094;
        };
    L_0x0094:
        r4 = r21.decodeEscaped();
    L_0x0098:
        r21.next();
        if (r5 != 0) goto L_0x00c7;
    L_0x009d:
        r18 = r21.read();
        if (r18 != 0) goto L_0x00b1;
    L_0x00a3:
        r0 = r21;
        r0 = r0.chardata;
        r18 = r0;
        r19 = 45;
        r0 = r18;
        r1 = r19;
        if (r0 == r1) goto L_0x023f;
    L_0x00b1:
        r18 = 2;
        r0 = r21;
        r1 = r18;
        r18 = r0.isSet(r1);
        if (r18 == 0) goto L_0x00c4;
    L_0x00bd:
        r18 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r0 = r18;
        if (r4 <= r0) goto L_0x023a;
    L_0x00c4:
        r15.addRange(r4, r4);
    L_0x00c7:
        r18 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = r21;
        r1 = r18;
        r18 = r0.isSet(r1);
        if (r18 == 0) goto L_0x00ea;
    L_0x00d3:
        r18 = r21.read();
        if (r18 != 0) goto L_0x00ea;
    L_0x00d9:
        r0 = r21;
        r0 = r0.chardata;
        r18 = r0;
        r19 = 44;
        r0 = r18;
        r1 = r19;
        if (r0 != r1) goto L_0x00ea;
    L_0x00e7:
        r21.next();
    L_0x00ea:
        r6 = 0;
        goto L_0x002d;
    L_0x00ed:
        r0 = r21;
        r18 = r0.getTokenForShorthand(r4);
        r0 = r18;
        r15.mergeRanges(r0);
        r5 = 1;
        goto L_0x0098;
    L_0x00fa:
        r0 = r21;
        r4 = r0.processCIinCharacterClass(r15, r4);
        if (r4 >= 0) goto L_0x0098;
    L_0x0102:
        r5 = 1;
        goto L_0x0098;
    L_0x0104:
        r0 = r21;
        r11 = r0.offset;
        r0 = r21;
        r16 = r0.processBacksolidus_pP(r4);
        if (r16 != 0) goto L_0x011b;
    L_0x0110:
        r18 = "parser.atom.5";
        r0 = r21;
        r1 = r18;
        r18 = r0.ex(r1, r11);
        throw r18;
    L_0x011b:
        r15.mergeRanges(r16);
        r5 = 1;
        goto L_0x0098;
    L_0x0121:
        r18 = 20;
        r0 = r17;
        r1 = r18;
        if (r0 != r1) goto L_0x01ec;
    L_0x0129:
        r0 = r21;
        r0 = r0.regex;
        r18 = r0;
        r19 = 58;
        r0 = r21;
        r0 = r0.offset;
        r20 = r0;
        r8 = r18.indexOf(r19, r20);
        if (r8 >= 0) goto L_0x0150;
    L_0x013d:
        r18 = "parser.cc.1";
        r0 = r21;
        r0 = r0.offset;
        r19 = r0;
        r0 = r21;
        r1 = r18;
        r2 = r19;
        r18 = r0.ex(r1, r2);
        throw r18;
    L_0x0150:
        r10 = 1;
        r0 = r21;
        r0 = r0.regex;
        r18 = r0;
        r0 = r21;
        r0 = r0.offset;
        r19 = r0;
        r18 = r18.charAt(r19);
        r19 = 94;
        r0 = r18;
        r1 = r19;
        if (r0 != r1) goto L_0x0178;
    L_0x0169:
        r0 = r21;
        r0 = r0.offset;
        r18 = r0;
        r18 = r18 + 1;
        r0 = r18;
        r1 = r21;
        r1.offset = r0;
        r10 = 0;
    L_0x0178:
        r0 = r21;
        r0 = r0.regex;
        r18 = r0;
        r0 = r21;
        r0 = r0.offset;
        r19 = r0;
        r0 = r18;
        r1 = r19;
        r7 = r0.substring(r1, r8);
        r18 = 512; // 0x200 float:7.175E-43 double:2.53E-321;
        r0 = r21;
        r1 = r18;
        r18 = r0.isSet(r1);
        r0 = r18;
        r12 = mf.org.apache.xerces.impl.xpath.regex.Token.getRange(r7, r10, r0);
        if (r12 != 0) goto L_0x01b1;
    L_0x019e:
        r18 = "parser.cc.3";
        r0 = r21;
        r0 = r0.offset;
        r19 = r0;
        r0 = r21;
        r1 = r18;
        r2 = r19;
        r18 = r0.ex(r1, r2);
        throw r18;
    L_0x01b1:
        r15.mergeRanges(r12);
        r5 = 1;
        r18 = r8 + 1;
        r0 = r21;
        r0 = r0.regexlen;
        r19 = r0;
        r0 = r18;
        r1 = r19;
        if (r0 >= r1) goto L_0x01d7;
    L_0x01c3:
        r0 = r21;
        r0 = r0.regex;
        r18 = r0;
        r19 = r8 + 1;
        r18 = r18.charAt(r19);
        r19 = 93;
        r0 = r18;
        r1 = r19;
        if (r0 == r1) goto L_0x01e2;
    L_0x01d7:
        r18 = "parser.cc.1";
        r0 = r21;
        r1 = r18;
        r18 = r0.ex(r1, r8);
        throw r18;
    L_0x01e2:
        r18 = r8 + 2;
        r0 = r18;
        r1 = r21;
        r1.offset = r0;
        goto L_0x0098;
    L_0x01ec:
        r18 = 24;
        r0 = r17;
        r1 = r18;
        if (r0 != r1) goto L_0x0098;
    L_0x01f4:
        if (r6 != 0) goto L_0x0098;
    L_0x01f6:
        if (r9 == 0) goto L_0x0201;
    L_0x01f8:
        r9 = 0;
        if (r22 == 0) goto L_0x0235;
    L_0x01fb:
        r15 = mf.org.apache.xerces.impl.xpath.regex.Token.complementRanges(r15);
        r15 = (mf.org.apache.xerces.impl.xpath.regex.RangeToken) r15;
    L_0x0201:
        r18 = 0;
        r0 = r21;
        r1 = r18;
        r13 = r0.parseCharacterClass(r1);
        r15.subtractRanges(r13);
        r18 = r21.read();
        if (r18 != 0) goto L_0x0222;
    L_0x0214:
        r0 = r21;
        r0 = r0.chardata;
        r18 = r0;
        r19 = 93;
        r0 = r18;
        r1 = r19;
        if (r0 == r1) goto L_0x0039;
    L_0x0222:
        r18 = "parser.cc.5";
        r0 = r21;
        r0 = r0.offset;
        r19 = r0;
        r0 = r21;
        r1 = r18;
        r2 = r19;
        r18 = r0.ex(r1, r2);
        throw r18;
    L_0x0235:
        r3.subtractRanges(r15);
        r15 = r3;
        goto L_0x0201;
    L_0x023a:
        addCaseInsensitiveChar(r15, r4);
        goto L_0x00c7;
    L_0x023f:
        r18 = 24;
        r0 = r17;
        r1 = r18;
        if (r0 != r1) goto L_0x025c;
    L_0x0247:
        r18 = "parser.cc.8";
        r0 = r21;
        r0 = r0.offset;
        r19 = r0;
        r19 = r19 + -1;
        r0 = r21;
        r1 = r18;
        r2 = r19;
        r18 = r0.ex(r1, r2);
        throw r18;
    L_0x025c:
        r21.next();
        r17 = r21.read();
        r18 = 1;
        r0 = r17;
        r1 = r18;
        if (r0 != r1) goto L_0x027e;
    L_0x026b:
        r18 = "parser.cc.2";
        r0 = r21;
        r0 = r0.offset;
        r19 = r0;
        r0 = r21;
        r1 = r18;
        r2 = r19;
        r18 = r0.ex(r1, r2);
        throw r18;
    L_0x027e:
        if (r17 != 0) goto L_0x02b5;
    L_0x0280:
        r0 = r21;
        r0 = r0.chardata;
        r18 = r0;
        r19 = 93;
        r0 = r18;
        r1 = r19;
        if (r0 != r1) goto L_0x02b5;
    L_0x028e:
        r18 = 2;
        r0 = r21;
        r1 = r18;
        r18 = r0.isSet(r1);
        if (r18 == 0) goto L_0x02a1;
    L_0x029a:
        r18 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r0 = r18;
        if (r4 <= r0) goto L_0x02b1;
    L_0x02a1:
        r15.addRange(r4, r4);
    L_0x02a4:
        r18 = 45;
        r19 = 45;
        r0 = r18;
        r1 = r19;
        r15.addRange(r0, r1);
        goto L_0x00c7;
    L_0x02b1:
        addCaseInsensitiveChar(r15, r4);
        goto L_0x02a4;
    L_0x02b5:
        r0 = r21;
        r14 = r0.chardata;
        r18 = 10;
        r0 = r17;
        r1 = r18;
        if (r0 != r1) goto L_0x02c5;
    L_0x02c1:
        r14 = r21.decodeEscaped();
    L_0x02c5:
        r21.next();
        if (r4 <= r14) goto L_0x02df;
    L_0x02ca:
        r18 = "parser.ope.3";
        r0 = r21;
        r0 = r0.offset;
        r19 = r0;
        r19 = r19 + -1;
        r0 = r21;
        r1 = r18;
        r2 = r19;
        r18 = r0.ex(r1, r2);
        throw r18;
    L_0x02df:
        r18 = 2;
        r0 = r21;
        r1 = r18;
        r18 = r0.isSet(r1);
        if (r18 == 0) goto L_0x02f9;
    L_0x02eb:
        r18 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r0 = r18;
        if (r4 <= r0) goto L_0x02fe;
    L_0x02f2:
        r18 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r0 = r18;
        if (r14 <= r0) goto L_0x02fe;
    L_0x02f9:
        r15.addRange(r4, r14);
        goto L_0x00c7;
    L_0x02fe:
        addCaseInsensitiveCharRange(r15, r4, r14);
        goto L_0x00c7;
    L_0x0303:
        if (r22 != 0) goto L_0x030b;
    L_0x0305:
        if (r9 == 0) goto L_0x030b;
    L_0x0307:
        r3.subtractRanges(r15);
        r15 = r3;
    L_0x030b:
        r15.sortRanges();
        r15.compactRanges();
        r18 = 0;
        r0 = r21;
        r1 = r18;
        r0.setContext(r1);
        r21.next();
        return r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xpath.regex.RegexParser.parseCharacterClass(boolean):mf.org.apache.xerces.impl.xpath.regex.RangeToken");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected mf.org.apache.xerces.impl.xpath.regex.RangeToken parseSetOperations() throws mf.org.apache.xerces.impl.xpath.regex.ParseException {
        /*
        r10 = this;
        r9 = 45;
        r8 = 38;
        r7 = 4;
        r6 = 0;
        r2 = r10.parseCharacterClass(r6);
    L_0x000a:
        r3 = r10.read();
        r4 = 7;
        if (r3 != r4) goto L_0x0015;
    L_0x0011:
        r10.next();
        return r2;
    L_0x0015:
        r0 = r10.chardata;
        if (r3 != 0) goto L_0x001d;
    L_0x0019:
        if (r0 == r9) goto L_0x001f;
    L_0x001b:
        if (r0 == r8) goto L_0x001f;
    L_0x001d:
        if (r3 != r7) goto L_0x0053;
    L_0x001f:
        r10.next();
        r4 = r10.read();
        r5 = 9;
        if (r4 == r5) goto L_0x0035;
    L_0x002a:
        r4 = "parser.ope.1";
        r5 = r10.offset;
        r5 = r5 + -1;
        r4 = r10.ex(r4, r5);
        throw r4;
    L_0x0035:
        r1 = r10.parseCharacterClass(r6);
        if (r3 != r7) goto L_0x003f;
    L_0x003b:
        r2.mergeRanges(r1);
        goto L_0x000a;
    L_0x003f:
        if (r0 != r9) goto L_0x0045;
    L_0x0041:
        r2.subtractRanges(r1);
        goto L_0x000a;
    L_0x0045:
        if (r0 != r8) goto L_0x004b;
    L_0x0047:
        r2.intersectRanges(r1);
        goto L_0x000a;
    L_0x004b:
        r4 = new java.lang.RuntimeException;
        r5 = "ASSERT";
        r4.<init>(r5);
        throw r4;
    L_0x0053:
        r4 = "parser.ope.2";
        r5 = r10.offset;
        r5 = r5 + -1;
        r4 = r10.ex(r4, r5);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xpath.regex.RegexParser.parseSetOperations():mf.org.apache.xerces.impl.xpath.regex.RangeToken");
    }

    Token getTokenForShorthand(int ch) {
        switch (ch) {
            case C0302R.styleable.Theme_searchViewStyle /*68*/:
                return isSet(32) ? Token.getRange("Nd", false) : Token.token_not_0to9;
            case C0302R.styleable.Theme_colorPrimaryDark /*83*/:
                return isSet(32) ? Token.getRange("IsSpace", false) : Token.token_not_spaces;
            case C0302R.styleable.Theme_colorControlHighlight /*87*/:
                return isSet(32) ? Token.getRange("IsWord", false) : Token.token_not_wordchars;
            case HttpStatus.SC_CONTINUE /*100*/:
                if (isSet(32)) {
                    return Token.getRange("Nd", true);
                }
                return Token.token_0to9;
            case 115:
                return isSet(32) ? Token.getRange("IsSpace", true) : Token.token_spaces;
            case 119:
                return isSet(32) ? Token.getRange("IsWord", true) : Token.token_wordchars;
            default:
                throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(ch, T_LOOKBEHIND));
        }
    }

    int decodeEscaped() throws ParseException {
        if (read() != T_BACKSOLIDUS) {
            throw ex("parser.next.1", this.offset - 1);
        }
        int c = this.chardata;
        int v1;
        int uv;
        switch (c) {
            case C0302R.styleable.Theme_textAppearanceSearchResultTitle /*65*/:
            case AdSize.LARGE_AD_HEIGHT /*90*/:
            case 122:
                throw ex("parser.descape.5", this.offset - 2);
            case HttpStatus.SC_SWITCHING_PROTOCOLS /*101*/:
                return 27;
            case HttpStatus.SC_PROCESSING /*102*/:
                return T_DOLLAR;
            case 110:
                return T_BACKSOLIDUS;
            case 114:
                return T_LPAREN2;
            case 116:
                return T_LBRACKET;
            case 117:
                next();
                if (read() == 0) {
                    v1 = hexChar(this.chardata);
                    if (v1 >= 0) {
                        uv = v1;
                        next();
                        if (read() == 0) {
                            v1 = hexChar(this.chardata);
                            if (v1 >= 0) {
                                uv = (uv * T_LOOKBEHIND) + v1;
                                next();
                                if (read() == 0) {
                                    v1 = hexChar(this.chardata);
                                    if (v1 >= 0) {
                                        uv = (uv * T_LOOKBEHIND) + v1;
                                        next();
                                        if (read() == 0) {
                                            v1 = hexChar(this.chardata);
                                            if (v1 >= 0) {
                                                return (uv * T_LOOKBEHIND) + v1;
                                            }
                                        }
                                        throw ex("parser.descape.1", this.offset - 1);
                                    }
                                }
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                        }
                        throw ex("parser.descape.1", this.offset - 1);
                    }
                }
                throw ex("parser.descape.1", this.offset - 1);
            case 118:
                next();
                if (read() == 0) {
                    v1 = hexChar(this.chardata);
                    if (v1 >= 0) {
                        uv = v1;
                        next();
                        if (read() == 0) {
                            v1 = hexChar(this.chardata);
                            if (v1 >= 0) {
                                uv = (uv * T_LOOKBEHIND) + v1;
                                next();
                                if (read() == 0) {
                                    v1 = hexChar(this.chardata);
                                    if (v1 >= 0) {
                                        uv = (uv * T_LOOKBEHIND) + v1;
                                        next();
                                        if (read() == 0) {
                                            v1 = hexChar(this.chardata);
                                            if (v1 >= 0) {
                                                uv = (uv * T_LOOKBEHIND) + v1;
                                                next();
                                                if (read() == 0) {
                                                    v1 = hexChar(this.chardata);
                                                    if (v1 >= 0) {
                                                        uv = (uv * T_LOOKBEHIND) + v1;
                                                        next();
                                                        if (read() == 0) {
                                                            v1 = hexChar(this.chardata);
                                                            if (v1 >= 0) {
                                                                uv = (uv * T_LOOKBEHIND) + v1;
                                                                if (uv <= 1114111) {
                                                                    return uv;
                                                                }
                                                                throw ex("parser.descappe.4", this.offset - 1);
                                                            }
                                                        }
                                                        throw ex("parser.descape.1", this.offset - 1);
                                                    }
                                                }
                                                throw ex("parser.descape.1", this.offset - 1);
                                            }
                                        }
                                        throw ex("parser.descape.1", this.offset - 1);
                                    }
                                }
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                        }
                        throw ex("parser.descape.1", this.offset - 1);
                    }
                }
                throw ex("parser.descape.1", this.offset - 1);
            case 120:
                next();
                if (read() != 0) {
                    throw ex("parser.descape.1", this.offset - 1);
                } else if (this.chardata == 123) {
                    uv = T_CHAR;
                    while (true) {
                        next();
                        if (read() != 0) {
                            throw ex("parser.descape.1", this.offset - 1);
                        }
                        v1 = hexChar(this.chardata);
                        if (v1 < 0) {
                            if (this.chardata != 125) {
                                throw ex("parser.descape.3", this.offset - 1);
                            } else if (uv <= 1114111) {
                                return uv;
                            } else {
                                throw ex("parser.descape.4", this.offset - 1);
                            }
                        } else if (uv > uv * T_LOOKBEHIND) {
                            throw ex("parser.descape.2", this.offset - 1);
                        } else {
                            uv = (uv * T_LOOKBEHIND) + v1;
                        }
                    }
                } else {
                    if (read() == 0) {
                        v1 = hexChar(this.chardata);
                        if (v1 >= 0) {
                            uv = v1;
                            next();
                            if (read() == 0) {
                                v1 = hexChar(this.chardata);
                                if (v1 >= 0) {
                                    return (uv * T_LOOKBEHIND) + v1;
                                }
                            }
                            throw ex("parser.descape.1", this.offset - 1);
                        }
                    }
                    throw ex("parser.descape.1", this.offset - 1);
                }
            default:
                return c;
        }
    }

    private static final int hexChar(int ch) {
        if (ch < 48 || ch > HttpStatus.SC_PROCESSING) {
            return -1;
        }
        if (ch <= 57) {
            return ch - 48;
        }
        if (ch < 65) {
            return -1;
        }
        if (ch <= 70) {
            return (ch - 65) + T_BACKSOLIDUS;
        }
        if (ch >= 97) {
            return (ch - 97) + T_BACKSOLIDUS;
        }
        return -1;
    }

    protected static final void addCaseInsensitiveChar(RangeToken tok, int c) {
        int[] caseMap = CaseInsensitiveMap.get(c);
        tok.addRange(c, c);
        if (caseMap != null) {
            for (int i = T_CHAR; i < caseMap.length; i += T_OR) {
                tok.addRange(caseMap[i], caseMap[i]);
            }
        }
    }

    protected static final void addCaseInsensitiveCharRange(RangeToken tok, int start, int end) {
        int r1;
        int r2;
        if (start <= end) {
            r1 = start;
            r2 = end;
        } else {
            r1 = end;
            r2 = start;
        }
        tok.addRange(r1, r2);
        for (int ch = r1; ch <= r2; ch += T_EOF) {
            int[] caseMap = CaseInsensitiveMap.get(ch);
            if (caseMap != null) {
                for (int i = T_CHAR; i < caseMap.length; i += T_OR) {
                    tok.addRange(caseMap[i], caseMap[i]);
                }
            }
        }
    }
}
