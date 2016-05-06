package mf.org.apache.xerces.impl.xpath.regex;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.android.gms.common.ConnectionResult;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;

class Token implements Serializable {
    static final int ANCHOR = 8;
    static final int BACKREFERENCE = 12;
    static final int CHAR = 0;
    static final int CHAR_FINAL_QUOTE = 30;
    static final int CHAR_INIT_QUOTE = 29;
    static final int CHAR_LETTER = 31;
    static final int CHAR_MARK = 32;
    static final int CHAR_NUMBER = 33;
    static final int CHAR_OTHER = 35;
    static final int CHAR_PUNCTUATION = 36;
    static final int CHAR_SEPARATOR = 34;
    static final int CHAR_SYMBOL = 37;
    static final int CLOSURE = 3;
    static final int CONCAT = 1;
    static final int CONDITION = 26;
    static final boolean COUNTTOKENS = true;
    static final int DOT = 11;
    static final int EMPTY = 7;
    static final int FC_ANY = 2;
    static final int FC_CONTINUE = 0;
    static final int FC_TERMINAL = 1;
    static final int INDEPENDENT = 24;
    static final int LOOKAHEAD = 20;
    static final int LOOKBEHIND = 22;
    static final int MODIFIERGROUP = 25;
    static final int NEGATIVELOOKAHEAD = 21;
    static final int NEGATIVELOOKBEHIND = 23;
    private static final int NONBMP_BLOCK_START = 84;
    static final int NONGREEDYCLOSURE = 9;
    static final int NRANGE = 5;
    static final int PAREN = 6;
    static final int RANGE = 4;
    static final int STRING = 10;
    static final int UNION = 2;
    static final int UTF16_MAX = 1114111;
    private static final String[] blockNames;
    static final String blockRanges = "\u0000\u007f\u0080\u00ff\u0100\u017f\u0180\u024f\u0250\u02af\u02b0\u02ff\u0300\u036f\u0370\u03ff\u0400\u04ff\u0530\u058f\u0590\u05ff\u0600\u06ff\u0700\u074f\u0780\u07bf\u0900\u097f\u0980\u09ff\u0a00\u0a7f\u0a80\u0aff\u0b00\u0b7f\u0b80\u0bff\u0c00\u0c7f\u0c80\u0cff\u0d00\u0d7f\u0d80\u0dff\u0e00\u0e7f\u0e80\u0eff\u0f00\u0fff\u1000\u109f\u10a0\u10ff\u1100\u11ff\u1200\u137f\u13a0\u13ff\u1400\u167f\u1680\u169f\u16a0\u16ff\u1780\u17ff\u1800\u18af\u1e00\u1eff\u1f00\u1fff\u2000\u206f\u2070\u209f\u20a0\u20cf\u20d0\u20ff\u2100\u214f\u2150\u218f\u2190\u21ff\u2200\u22ff\u2300\u23ff\u2400\u243f\u2440\u245f\u2460\u24ff\u2500\u257f\u2580\u259f\u25a0\u25ff\u2600\u26ff\u2700\u27bf\u2800\u28ff\u2e80\u2eff\u2f00\u2fdf\u2ff0\u2fff\u3000\u303f\u3040\u309f\u30a0\u30ff\u3100\u312f\u3130\u318f\u3190\u319f\u31a0\u31bf\u3200\u32ff\u3300\u33ff\u3400\u4db5\u4e00\u9fff\ua000\ua48f\ua490\ua4cf\uac00\ud7a3\ue000\uf8ff\uf900\ufaff\ufb00\ufb4f\ufb50\ufdff\ufe20\ufe2f\ufe30\ufe4f\ufe50\ufe6f\ufe70\ufefe\ufeff\ufeff\uff00\uffef";
    private static final Hashtable categories;
    private static final Hashtable categories2;
    private static final String[] categoryNames;
    static final int[] nonBMPBlockRanges;
    static Hashtable nonxs = null;
    private static final long serialVersionUID = 8484976002585487481L;
    static Token token_0to9 = null;
    private static Token token_ccs = null;
    static Token token_dot = null;
    static Token token_empty = null;
    private static Token token_grapheme = null;
    static Token token_linebeginning = null;
    static Token token_linebeginning2 = null;
    static Token token_lineend = null;
    static Token token_not_0to9 = null;
    static Token token_not_spaces = null;
    static Token token_not_wordchars = null;
    static Token token_not_wordedge = null;
    static Token token_spaces = null;
    static Token token_stringbeginning = null;
    static Token token_stringend = null;
    static Token token_stringend2 = null;
    static Token token_wordbeginning = null;
    static Token token_wordchars = null;
    static Token token_wordedge = null;
    static Token token_wordend = null;
    static int tokens = 0;
    static final String viramaString = "\u094d\u09cd\u0a4d\u0acd\u0b4d\u0bcd\u0c4d\u0ccd\u0d4d\u0e3a\u0f84";
    final int type;

    static class FixedStringContainer {
        int options;
        Token token;

        FixedStringContainer() {
            this.token = null;
            this.options = Token.FC_CONTINUE;
        }
    }

    static class CharToken extends Token implements Serializable {
        private static final long serialVersionUID = -4394272816279496989L;
        final int chardata;

        CharToken(int type, int ch) {
            super(type);
            this.chardata = ch;
        }

        int getChar() {
            return this.chardata;
        }

        public String toString(int options) {
            switch (this.type) {
                case Token.FC_CONTINUE /*0*/:
                    switch (this.chardata) {
                        case Token.NONGREEDYCLOSURE /*9*/:
                            return "\\t";
                        case Token.STRING /*10*/:
                            return "\\n";
                        case Token.BACKREFERENCE /*12*/:
                            return "\\f";
                        case ConnectionResult.CANCELED /*13*/:
                            return "\\r";
                        case Tokens.EXPRTOKEN_OPERATOR_NOT_EQUAL /*27*/:
                            return "\\e";
                        case Tokens.EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING /*40*/:
                        case Tokens.EXPRTOKEN_AXISNAME_NAMESPACE /*41*/:
                        case Tokens.EXPRTOKEN_AXISNAME_PARENT /*42*/:
                        case Tokens.EXPRTOKEN_AXISNAME_PRECEDING /*43*/:
                        case Tokens.EXPRTOKEN_LITERAL /*46*/:
                        case C0302R.styleable.Theme_editTextBackground /*63*/:
                        case C0302R.styleable.Theme_alertDialogStyle /*91*/:
                        case C0302R.styleable.Theme_alertDialogButtonGroupStyle /*92*/:
                        case 123:
                        case 124:
                            return "\\" + ((char) this.chardata);
                        default:
                            if (this.chardata < AccessibilityNodeInfoCompat.ACTION_CUT) {
                                return ((char) this.chardata);
                            }
                            String pre = new StringBuilder(SchemaSymbols.ATTVAL_FALSE_0).append(Integer.toHexString(this.chardata)).toString();
                            return "\\v" + pre.substring(pre.length() - 6, pre.length());
                    }
                case Token.ANCHOR /*8*/:
                    if (this == Token.token_linebeginning || this == Token.token_lineend) {
                        return ((char) this.chardata);
                    }
                    return "\\" + ((char) this.chardata);
                default:
                    return null;
            }
        }

        boolean match(int ch) {
            if (this.type == 0) {
                return ch == this.chardata ? Token.COUNTTOKENS : false;
            } else {
                throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
            }
        }
    }

    static class ClosureToken extends Token implements Serializable {
        private static final long serialVersionUID = 1308971930673997452L;
        final Token child;
        int max;
        int min;

        ClosureToken(int type, Token tok) {
            super(type);
            this.child = tok;
            setMin(-1);
            setMax(-1);
        }

        int size() {
            return Token.FC_TERMINAL;
        }

        Token getChild(int index) {
            return this.child;
        }

        final void setMin(int min) {
            this.min = min;
        }

        final void setMax(int max) {
            this.max = max;
        }

        final int getMin() {
            return this.min;
        }

        final int getMax() {
            return this.max;
        }

        public String toString(int options) {
            if (this.type == Token.CLOSURE) {
                if (getMin() < 0 && getMax() < 0) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("*").toString();
                }
                if (getMin() == getMax()) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append("}").toString();
                }
                if (getMin() >= 0 && getMax() >= 0) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append(",").append(getMax()).append("}").toString();
                }
                if (getMin() >= 0 && getMax() < 0) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append(",}").toString();
                }
                throw new RuntimeException("Token#toString(): CLOSURE " + getMin() + ", " + getMax());
            } else if (getMin() < 0 && getMax() < 0) {
                return new StringBuilder(String.valueOf(this.child.toString(options))).append("*?").toString();
            } else {
                if (getMin() == getMax()) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append("}?").toString();
                }
                if (getMin() >= 0 && getMax() >= 0) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append(",").append(getMax()).append("}?").toString();
                }
                if (getMin() >= 0 && getMax() < 0) {
                    return new StringBuilder(String.valueOf(this.child.toString(options))).append("{").append(getMin()).append(",}?").toString();
                }
                throw new RuntimeException("Token#toString(): NONGREEDYCLOSURE " + getMin() + ", " + getMax());
            }
        }
    }

    static class ConcatToken extends Token implements Serializable {
        private static final long serialVersionUID = 8717321425541346381L;
        final Token child;
        final Token child2;

        ConcatToken(Token t1, Token t2) {
            super(Token.FC_TERMINAL);
            this.child = t1;
            this.child2 = t2;
        }

        int size() {
            return Token.UNION;
        }

        Token getChild(int index) {
            return index == 0 ? this.child : this.child2;
        }

        public String toString(int options) {
            if (this.child2.type == Token.CLOSURE && this.child2.getChild(Token.FC_CONTINUE) == this.child) {
                return new StringBuilder(String.valueOf(this.child.toString(options))).append("+").toString();
            }
            if (this.child2.type == Token.NONGREEDYCLOSURE && this.child2.getChild(Token.FC_CONTINUE) == this.child) {
                return new StringBuilder(String.valueOf(this.child.toString(options))).append("+?").toString();
            }
            return new StringBuilder(String.valueOf(this.child.toString(options))).append(this.child2.toString(options)).toString();
        }
    }

    static class ConditionToken extends Token implements Serializable {
        private static final long serialVersionUID = 4353765277910594411L;
        final Token condition;
        final Token no;
        final int refNumber;
        final Token yes;

        ConditionToken(int refno, Token cond, Token yespat, Token nopat) {
            super(Token.CONDITION);
            this.refNumber = refno;
            this.condition = cond;
            this.yes = yespat;
            this.no = nopat;
        }

        int size() {
            return this.no == null ? Token.FC_TERMINAL : Token.UNION;
        }

        Token getChild(int index) {
            if (index == 0) {
                return this.yes;
            }
            if (index == Token.FC_TERMINAL) {
                return this.no;
            }
            throw new RuntimeException("Internal Error: " + index);
        }

        public String toString(int options) {
            String ret;
            if (this.refNumber > 0) {
                ret = "(?(" + this.refNumber + ")";
            } else if (this.condition.type == Token.ANCHOR) {
                ret = "(?(" + this.condition + ")";
            } else {
                ret = "(?" + this.condition;
            }
            if (this.no == null) {
                return new StringBuilder(String.valueOf(ret)).append(this.yes).append(")").toString();
            }
            return new StringBuilder(String.valueOf(ret)).append(this.yes).append("|").append(this.no).append(")").toString();
        }
    }

    static class ModifierToken extends Token implements Serializable {
        private static final long serialVersionUID = -9114536559696480356L;
        final int add;
        final Token child;
        final int mask;

        ModifierToken(Token tok, int add, int mask) {
            super(Token.MODIFIERGROUP);
            this.child = tok;
            this.add = add;
            this.mask = mask;
        }

        int size() {
            return Token.FC_TERMINAL;
        }

        Token getChild(int index) {
            return this.child;
        }

        int getOptions() {
            return this.add;
        }

        int getOptionsMask() {
            return this.mask;
        }

        public String toString(int options) {
            return "(?" + (this.add == 0 ? StringUtils.EMPTY : REUtil.createOptionString(this.add)) + (this.mask == 0 ? StringUtils.EMPTY : REUtil.createOptionString(this.mask)) + ":" + this.child.toString(options) + ")";
        }
    }

    static class ParenToken extends Token implements Serializable {
        private static final long serialVersionUID = -5938014719827987704L;
        final Token child;
        final int parennumber;

        ParenToken(int type, Token tok, int paren) {
            super(type);
            this.child = tok;
            this.parennumber = paren;
        }

        int size() {
            return Token.FC_TERMINAL;
        }

        Token getChild(int index) {
            return this.child;
        }

        int getParenNumber() {
            return this.parennumber;
        }

        public String toString(int options) {
            switch (this.type) {
                case Token.PAREN /*6*/:
                    if (this.parennumber == 0) {
                        return "(?:" + this.child.toString(options) + ")";
                    }
                    return "(" + this.child.toString(options) + ")";
                case Token.LOOKAHEAD /*20*/:
                    return "(?=" + this.child.toString(options) + ")";
                case Token.NEGATIVELOOKAHEAD /*21*/:
                    return "(?!" + this.child.toString(options) + ")";
                case Token.LOOKBEHIND /*22*/:
                    return "(?<=" + this.child.toString(options) + ")";
                case Token.NEGATIVELOOKBEHIND /*23*/:
                    return "(?<!" + this.child.toString(options) + ")";
                case Token.INDEPENDENT /*24*/:
                    return "(?>" + this.child.toString(options) + ")";
                default:
                    return null;
            }
        }
    }

    static class StringToken extends Token implements Serializable {
        private static final long serialVersionUID = -4614366944218504172L;
        final int refNumber;
        String string;

        StringToken(int type, String str, int n) {
            super(type);
            this.string = str;
            this.refNumber = n;
        }

        int getReferenceNumber() {
            return this.refNumber;
        }

        String getString() {
            return this.string;
        }

        public String toString(int options) {
            if (this.type == Token.BACKREFERENCE) {
                return "\\" + this.refNumber;
            }
            return REUtil.quoteMeta(this.string);
        }
    }

    static class UnionToken extends Token implements Serializable {
        private static final long serialVersionUID = -2568843945989489861L;
        Vector children;

        UnionToken(int type) {
            super(type);
        }

        void addChild(Token tok) {
            int nextMaxLength = Token.UNION;
            if (tok != null) {
                if (this.children == null) {
                    this.children = new Vector();
                }
                if (this.type == Token.UNION) {
                    this.children.addElement(tok);
                } else if (tok.type == Token.FC_TERMINAL) {
                    for (int i = Token.FC_CONTINUE; i < tok.size(); i += Token.FC_TERMINAL) {
                        addChild(tok.getChild(i));
                    }
                } else {
                    int size = this.children.size();
                    if (size == 0) {
                        this.children.addElement(tok);
                        return;
                    }
                    Token previous = (Token) this.children.elementAt(size - 1);
                    if ((previous.type == 0 || previous.type == Token.STRING) && (tok.type == 0 || tok.type == Token.STRING)) {
                        StringBuffer buffer;
                        int ch;
                        if (tok.type != 0) {
                            nextMaxLength = tok.getString().length();
                        }
                        if (previous.type == 0) {
                            buffer = new StringBuffer(nextMaxLength + Token.UNION);
                            ch = previous.getChar();
                            if (ch >= AccessibilityNodeInfoCompat.ACTION_CUT) {
                                buffer.append(REUtil.decomposeToSurrogates(ch));
                            } else {
                                buffer.append((char) ch);
                            }
                            previous = Token.createString(null);
                            this.children.setElementAt(previous, size - 1);
                        } else {
                            buffer = new StringBuffer(previous.getString().length() + nextMaxLength);
                            buffer.append(previous.getString());
                        }
                        if (tok.type == 0) {
                            ch = tok.getChar();
                            if (ch >= AccessibilityNodeInfoCompat.ACTION_CUT) {
                                buffer.append(REUtil.decomposeToSurrogates(ch));
                            } else {
                                buffer.append((char) ch);
                            }
                        } else {
                            buffer.append(tok.getString());
                        }
                        ((StringToken) previous).string = new String(buffer);
                        return;
                    }
                    this.children.addElement(tok);
                }
            }
        }

        int size() {
            return this.children == null ? Token.FC_CONTINUE : this.children.size();
        }

        Token getChild(int index) {
            return (Token) this.children.elementAt(index);
        }

        public String toString(int options) {
            String ret;
            StringBuffer sb;
            int i;
            if (this.type == Token.FC_TERMINAL) {
                if (this.children.size() == Token.UNION) {
                    Token ch = getChild(Token.FC_CONTINUE);
                    Token ch2 = getChild(Token.FC_TERMINAL);
                    if (ch2.type == Token.CLOSURE && ch2.getChild(Token.FC_CONTINUE) == ch) {
                        ret = ch.toString(options) + "+";
                    } else if (ch2.type == Token.NONGREEDYCLOSURE && ch2.getChild(Token.FC_CONTINUE) == ch) {
                        ret = ch.toString(options) + "+?";
                    } else {
                        ret = ch.toString(options) + ch2.toString(options);
                    }
                } else {
                    sb = new StringBuffer();
                    for (i = Token.FC_CONTINUE; i < this.children.size(); i += Token.FC_TERMINAL) {
                        sb.append(((Token) this.children.elementAt(i)).toString(options));
                    }
                    ret = new String(sb);
                }
                return ret;
            }
            if (this.children.size() == Token.UNION && getChild(Token.FC_TERMINAL).type == Token.EMPTY) {
                ret = new StringBuilder(String.valueOf(getChild(Token.FC_CONTINUE).toString(options))).append("?").toString();
            } else if (this.children.size() == Token.UNION && getChild(Token.FC_CONTINUE).type == Token.EMPTY) {
                ret = new StringBuilder(String.valueOf(getChild(Token.FC_TERMINAL).toString(options))).append("??").toString();
            } else {
                sb = new StringBuffer();
                sb.append(((Token) this.children.elementAt(Token.FC_CONTINUE)).toString(options));
                for (i = Token.FC_TERMINAL; i < this.children.size(); i += Token.FC_TERMINAL) {
                    sb.append('|');
                    sb.append(((Token) this.children.elementAt(i)).toString(options));
                }
                ret = new String(sb);
            }
            return ret;
        }
    }

    static {
        tokens = FC_CONTINUE;
        token_empty = new Token(EMPTY);
        token_linebeginning = createAnchor(94);
        token_linebeginning2 = createAnchor(64);
        token_lineend = createAnchor(CHAR_PUNCTUATION);
        token_stringbeginning = createAnchor(65);
        token_stringend = createAnchor(122);
        token_stringend2 = createAnchor(90);
        token_wordedge = createAnchor(98);
        token_not_wordedge = createAnchor(66);
        token_wordbeginning = createAnchor(60);
        token_wordend = createAnchor(62);
        token_dot = new Token(DOT);
        token_0to9 = createRange();
        token_0to9.addRange(48, 57);
        token_wordchars = createRange();
        token_wordchars.addRange(48, 57);
        token_wordchars.addRange(65, 90);
        token_wordchars.addRange(95, 95);
        token_wordchars.addRange(97, 122);
        token_spaces = createRange();
        token_spaces.addRange(NONGREEDYCLOSURE, NONGREEDYCLOSURE);
        token_spaces.addRange(STRING, STRING);
        token_spaces.addRange(BACKREFERENCE, BACKREFERENCE);
        token_spaces.addRange(13, 13);
        token_spaces.addRange(CHAR_MARK, CHAR_MARK);
        token_not_0to9 = complementRanges(token_0to9);
        token_not_wordchars = complementRanges(token_wordchars);
        token_not_spaces = complementRanges(token_spaces);
        categories = new Hashtable();
        categories2 = new Hashtable();
        String[] strArr = new String[38];
        strArr[FC_CONTINUE] = "Cn";
        strArr[FC_TERMINAL] = "Lu";
        strArr[UNION] = "Ll";
        strArr[CLOSURE] = "Lt";
        strArr[RANGE] = "Lm";
        strArr[NRANGE] = "Lo";
        strArr[PAREN] = "Mn";
        strArr[EMPTY] = "Me";
        strArr[ANCHOR] = "Mc";
        strArr[NONGREEDYCLOSURE] = "Nd";
        strArr[STRING] = "Nl";
        strArr[DOT] = "No";
        strArr[BACKREFERENCE] = "Zs";
        strArr[13] = "Zl";
        strArr[14] = "Zp";
        strArr[15] = "Cc";
        strArr[16] = "Cf";
        strArr[18] = "Co";
        strArr[19] = "Cs";
        strArr[LOOKAHEAD] = "Pd";
        strArr[NEGATIVELOOKAHEAD] = "Ps";
        strArr[LOOKBEHIND] = "Pe";
        strArr[NEGATIVELOOKBEHIND] = "Pc";
        strArr[INDEPENDENT] = "Po";
        strArr[MODIFIERGROUP] = "Sm";
        strArr[CONDITION] = "Sc";
        strArr[27] = "Sk";
        strArr[28] = "So";
        strArr[CHAR_INIT_QUOTE] = "Pi";
        strArr[CHAR_FINAL_QUOTE] = "Pf";
        strArr[CHAR_LETTER] = "L";
        strArr[CHAR_MARK] = "M";
        strArr[CHAR_NUMBER] = "N";
        strArr[CHAR_SEPARATOR] = "Z";
        strArr[CHAR_OTHER] = "C";
        strArr[CHAR_PUNCTUATION] = "P";
        strArr[CHAR_SYMBOL] = "S";
        categoryNames = strArr;
        blockNames = new String[]{"Basic Latin", "Latin-1 Supplement", "Latin Extended-A", "Latin Extended-B", "IPA Extensions", "Spacing Modifier Letters", "Combining Diacritical Marks", "Greek", "Cyrillic", "Armenian", "Hebrew", "Arabic", "Syriac", "Thaana", "Devanagari", "Bengali", "Gurmukhi", "Gujarati", "Oriya", "Tamil", "Telugu", "Kannada", "Malayalam", "Sinhala", "Thai", "Lao", "Tibetan", "Myanmar", "Georgian", "Hangul Jamo", "Ethiopic", "Cherokee", "Unified Canadian Aboriginal Syllabics", "Ogham", "Runic", "Khmer", "Mongolian", "Latin Extended Additional", "Greek Extended", "General Punctuation", "Superscripts and Subscripts", "Currency Symbols", "Combining Marks for Symbols", "Letterlike Symbols", "Number Forms", "Arrows", "Mathematical Operators", "Miscellaneous Technical", "Control Pictures", "Optical Character Recognition", "Enclosed Alphanumerics", "Box Drawing", "Block Elements", "Geometric Shapes", "Miscellaneous Symbols", "Dingbats", "Braille Patterns", "CJK Radicals Supplement", "Kangxi Radicals", "Ideographic Description Characters", "CJK Symbols and Punctuation", "Hiragana", "Katakana", "Bopomofo", "Hangul Compatibility Jamo", "Kanbun", "Bopomofo Extended", "Enclosed CJK Letters and Months", "CJK Compatibility", "CJK Unified Ideographs Extension A", "CJK Unified Ideographs", "Yi Syllables", "Yi Radicals", "Hangul Syllables", "Private Use", "CJK Compatibility Ideographs", "Alphabetic Presentation Forms", "Arabic Presentation Forms-A", "Combining Half Marks", "CJK Compatibility Forms", "Small Form Variants", "Arabic Presentation Forms-B", "Specials", "Halfwidth and Fullwidth Forms", "Old Italic", "Gothic", "Deseret", "Byzantine Musical Symbols", "Musical Symbols", "Mathematical Alphanumeric Symbols", "CJK Unified Ideographs Extension B", "CJK Compatibility Ideographs Supplement", "Tags"};
        nonBMPBlockRanges = new int[]{66304, 66351, 66352, 66383, 66560, 66639, 118784, 119039, 119040, 119295, 119808, 120831, AccessibilityNodeInfoCompat.ACTION_SET_SELECTION, 173782, 194560, 195103, 917504, 917631};
        nonxs = null;
        token_grapheme = null;
        token_ccs = null;
    }

    static ParenToken createLook(int type, Token child) {
        tokens += FC_TERMINAL;
        return new ParenToken(type, child, FC_CONTINUE);
    }

    static ParenToken createParen(Token child, int pnumber) {
        tokens += FC_TERMINAL;
        return new ParenToken(PAREN, child, pnumber);
    }

    static ClosureToken createClosure(Token tok) {
        tokens += FC_TERMINAL;
        return new ClosureToken(CLOSURE, tok);
    }

    static ClosureToken createNGClosure(Token tok) {
        tokens += FC_TERMINAL;
        return new ClosureToken(NONGREEDYCLOSURE, tok);
    }

    static ConcatToken createConcat(Token tok1, Token tok2) {
        tokens += FC_TERMINAL;
        return new ConcatToken(tok1, tok2);
    }

    static UnionToken createConcat() {
        tokens += FC_TERMINAL;
        return new UnionToken(FC_TERMINAL);
    }

    static UnionToken createUnion() {
        tokens += FC_TERMINAL;
        return new UnionToken(UNION);
    }

    static Token createEmpty() {
        return token_empty;
    }

    static RangeToken createRange() {
        tokens += FC_TERMINAL;
        return new RangeToken(RANGE);
    }

    static RangeToken createNRange() {
        tokens += FC_TERMINAL;
        return new RangeToken(NRANGE);
    }

    static CharToken createChar(int ch) {
        tokens += FC_TERMINAL;
        return new CharToken(FC_CONTINUE, ch);
    }

    private static CharToken createAnchor(int ch) {
        tokens += FC_TERMINAL;
        return new CharToken(ANCHOR, ch);
    }

    static StringToken createBackReference(int refno) {
        tokens += FC_TERMINAL;
        return new StringToken(BACKREFERENCE, null, refno);
    }

    static StringToken createString(String str) {
        tokens += FC_TERMINAL;
        return new StringToken(STRING, str, FC_CONTINUE);
    }

    static ModifierToken createModifierGroup(Token child, int add, int mask) {
        tokens += FC_TERMINAL;
        return new ModifierToken(child, add, mask);
    }

    static ConditionToken createCondition(int refno, Token condition, Token yespat, Token nopat) {
        tokens += FC_TERMINAL;
        return new ConditionToken(refno, condition, yespat, nopat);
    }

    protected Token(int type) {
        this.type = type;
    }

    int size() {
        return FC_CONTINUE;
    }

    Token getChild(int index) {
        return null;
    }

    void addChild(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void addRange(int start, int end) {
        throw new RuntimeException("Not supported.");
    }

    protected void sortRanges() {
        throw new RuntimeException("Not supported.");
    }

    protected void compactRanges() {
        throw new RuntimeException("Not supported.");
    }

    protected void mergeRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void subtractRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    protected void intersectRanges(Token tok) {
        throw new RuntimeException("Not supported.");
    }

    static Token complementRanges(Token tok) {
        return RangeToken.complementRanges(tok);
    }

    void setMin(int min) {
    }

    void setMax(int max) {
    }

    int getMin() {
        return -1;
    }

    int getMax() {
        return -1;
    }

    int getReferenceNumber() {
        return FC_CONTINUE;
    }

    String getString() {
        return null;
    }

    int getParenNumber() {
        return FC_CONTINUE;
    }

    int getChar() {
        return -1;
    }

    public String toString() {
        return toString(FC_CONTINUE);
    }

    public String toString(int options) {
        return this.type == DOT ? "." : StringUtils.EMPTY;
    }

    final int getMinLength() {
        int i;
        switch (this.type) {
            case FC_CONTINUE /*0*/:
            case RANGE /*4*/:
            case NRANGE /*5*/:
            case DOT /*11*/:
                return FC_TERMINAL;
            case FC_TERMINAL /*1*/:
                int sum = FC_CONTINUE;
                for (i = FC_CONTINUE; i < size(); i += FC_TERMINAL) {
                    sum += getChild(i).getMinLength();
                }
                return sum;
            case UNION /*2*/:
            case CONDITION /*26*/:
                if (size() == 0) {
                    return FC_CONTINUE;
                }
                int ret = getChild(FC_CONTINUE).getMinLength();
                for (i = FC_TERMINAL; i < size(); i += FC_TERMINAL) {
                    int min = getChild(i).getMinLength();
                    if (min < ret) {
                        ret = min;
                    }
                }
                return ret;
            case CLOSURE /*3*/:
            case NONGREEDYCLOSURE /*9*/:
                if (getMin() >= 0) {
                    return getMin() * getChild(FC_CONTINUE).getMinLength();
                }
                return FC_CONTINUE;
            case PAREN /*6*/:
            case INDEPENDENT /*24*/:
            case MODIFIERGROUP /*25*/:
                return getChild(FC_CONTINUE).getMinLength();
            case EMPTY /*7*/:
            case ANCHOR /*8*/:
            case BACKREFERENCE /*12*/:
            case LOOKAHEAD /*20*/:
            case NEGATIVELOOKAHEAD /*21*/:
            case LOOKBEHIND /*22*/:
            case NEGATIVELOOKBEHIND /*23*/:
                return FC_CONTINUE;
            case STRING /*10*/:
                return getString().length();
            default:
                throw new RuntimeException("Token#getMinLength(): Invalid Type: " + this.type);
        }
    }

    final int getMaxLength() {
        int i;
        switch (this.type) {
            case FC_CONTINUE /*0*/:
                return FC_TERMINAL;
            case FC_TERMINAL /*1*/:
                int sum = FC_CONTINUE;
                for (i = FC_CONTINUE; i < size(); i += FC_TERMINAL) {
                    int d = getChild(i).getMaxLength();
                    if (d < 0) {
                        return -1;
                    }
                    sum += d;
                }
                return sum;
            case UNION /*2*/:
            case CONDITION /*26*/:
                if (size() == 0) {
                    return FC_CONTINUE;
                }
                int ret = getChild(FC_CONTINUE).getMaxLength();
                i = FC_TERMINAL;
                while (ret >= 0 && i < size()) {
                    int max = getChild(i).getMaxLength();
                    if (max < 0) {
                        ret = -1;
                    } else {
                        if (max > ret) {
                            ret = max;
                        }
                        i += FC_TERMINAL;
                    }
                }
                return ret;
            case CLOSURE /*3*/:
            case NONGREEDYCLOSURE /*9*/:
                return getMax() >= 0 ? getMax() * getChild(FC_CONTINUE).getMaxLength() : -1;
            case RANGE /*4*/:
            case NRANGE /*5*/:
            case DOT /*11*/:
                return UNION;
            case PAREN /*6*/:
            case INDEPENDENT /*24*/:
            case MODIFIERGROUP /*25*/:
                return getChild(FC_CONTINUE).getMaxLength();
            case EMPTY /*7*/:
            case ANCHOR /*8*/:
            case LOOKAHEAD /*20*/:
            case NEGATIVELOOKAHEAD /*21*/:
            case LOOKBEHIND /*22*/:
            case NEGATIVELOOKBEHIND /*23*/:
                return FC_CONTINUE;
            case STRING /*10*/:
                return getString().length();
            case BACKREFERENCE /*12*/:
                return -1;
            default:
                throw new RuntimeException("Token#getMaxLength(): Invalid Type: " + this.type);
        }
    }

    private static final boolean isSet(int options, int flag) {
        return (options & flag) == flag ? COUNTTOKENS : false;
    }

    final int analyzeFirstCharacter(RangeToken result, int options) {
        int i;
        switch (this.type) {
            case FC_CONTINUE /*0*/:
                int ch = getChar();
                result.addRange(ch, ch);
                if (ch < AccessibilityNodeInfoCompat.ACTION_CUT && isSet(options, UNION)) {
                    ch = Character.toUpperCase((char) ch);
                    result.addRange(ch, ch);
                    ch = Character.toLowerCase((char) ch);
                    result.addRange(ch, ch);
                }
                return FC_TERMINAL;
            case FC_TERMINAL /*1*/:
                int ret = FC_CONTINUE;
                for (i = FC_CONTINUE; i < size(); i += FC_TERMINAL) {
                    ret = getChild(i).analyzeFirstCharacter(result, options);
                    if (ret != 0) {
                        return ret;
                    }
                }
                return ret;
            case UNION /*2*/:
                if (size() == 0) {
                    return FC_CONTINUE;
                }
                int ret2 = FC_CONTINUE;
                boolean hasEmpty = false;
                i = FC_CONTINUE;
                while (i < size()) {
                    ret2 = getChild(i).analyzeFirstCharacter(result, options);
                    if (ret2 != UNION) {
                        if (ret2 == 0) {
                            hasEmpty = COUNTTOKENS;
                        }
                        i += FC_TERMINAL;
                    } else {
                        if (hasEmpty) {
                            ret2 = FC_CONTINUE;
                        }
                        return ret2;
                    }
                }
                if (hasEmpty) {
                    ret2 = FC_CONTINUE;
                }
                return ret2;
            case CLOSURE /*3*/:
            case NONGREEDYCLOSURE /*9*/:
                getChild(FC_CONTINUE).analyzeFirstCharacter(result, options);
                return FC_CONTINUE;
            case RANGE /*4*/:
                result.mergeRanges(this);
                return FC_TERMINAL;
            case NRANGE /*5*/:
                result.mergeRanges(complementRanges(this));
                return FC_TERMINAL;
            case PAREN /*6*/:
            case INDEPENDENT /*24*/:
                return getChild(FC_CONTINUE).analyzeFirstCharacter(result, options);
            case EMPTY /*7*/:
            case ANCHOR /*8*/:
            case LOOKAHEAD /*20*/:
            case NEGATIVELOOKAHEAD /*21*/:
            case LOOKBEHIND /*22*/:
            case NEGATIVELOOKBEHIND /*23*/:
                return FC_CONTINUE;
            case STRING /*10*/:
                int cha = getString().charAt(FC_CONTINUE);
                if (REUtil.isHighSurrogate(cha) && getString().length() >= UNION) {
                    int ch2 = getString().charAt(FC_TERMINAL);
                    if (REUtil.isLowSurrogate(ch2)) {
                        cha = REUtil.composeFromSurrogates(cha, ch2);
                    }
                }
                result.addRange(cha, cha);
                if (cha < AccessibilityNodeInfoCompat.ACTION_CUT && isSet(options, UNION)) {
                    cha = Character.toUpperCase((char) cha);
                    result.addRange(cha, cha);
                    cha = Character.toLowerCase((char) cha);
                    result.addRange(cha, cha);
                }
                return FC_TERMINAL;
            case DOT /*11*/:
                return UNION;
            case BACKREFERENCE /*12*/:
                result.addRange(FC_CONTINUE, UTF16_MAX);
                return UNION;
            case MODIFIERGROUP /*25*/:
                return getChild(FC_CONTINUE).analyzeFirstCharacter(result, (options | ((ModifierToken) this).getOptions()) & (((ModifierToken) this).getOptionsMask() ^ -1));
            case CONDITION /*26*/:
                int ret3 = getChild(FC_CONTINUE).analyzeFirstCharacter(result, options);
                if (size() == FC_TERMINAL) {
                    return FC_CONTINUE;
                }
                if (ret3 == UNION) {
                    return ret3;
                }
                int ret4 = getChild(FC_TERMINAL).analyzeFirstCharacter(result, options);
                if (ret4 == UNION) {
                    return ret4;
                }
                if (ret3 == 0 || ret4 == 0) {
                    return FC_CONTINUE;
                }
                return FC_TERMINAL;
            default:
                throw new RuntimeException("Token#analyzeHeadCharacter(): Invalid Type: " + this.type);
        }
    }

    private final boolean isShorterThan(Token tok) {
        if (tok == null) {
            return false;
        }
        if (this.type == STRING) {
            int mylength = getString().length();
            if (tok.type != STRING) {
                throw new RuntimeException("Internal Error: Illegal type: " + tok.type);
            } else if (mylength < tok.getString().length()) {
                return COUNTTOKENS;
            } else {
                return false;
            }
        }
        throw new RuntimeException("Internal Error: Illegal type: " + this.type);
    }

    final void findFixedString(FixedStringContainer container, int options) {
        switch (this.type) {
            case FC_CONTINUE /*0*/:
                container.token = null;
            case FC_TERMINAL /*1*/:
                Token prevToken = null;
                int prevOptions = FC_CONTINUE;
                for (int i = FC_CONTINUE; i < size(); i += FC_TERMINAL) {
                    getChild(i).findFixedString(container, options);
                    if (prevToken == null || prevToken.isShorterThan(container.token)) {
                        prevToken = container.token;
                        prevOptions = container.options;
                    }
                }
                container.token = prevToken;
                container.options = prevOptions;
            case UNION /*2*/:
            case CLOSURE /*3*/:
            case RANGE /*4*/:
            case NRANGE /*5*/:
            case EMPTY /*7*/:
            case ANCHOR /*8*/:
            case NONGREEDYCLOSURE /*9*/:
            case DOT /*11*/:
            case BACKREFERENCE /*12*/:
            case LOOKAHEAD /*20*/:
            case NEGATIVELOOKAHEAD /*21*/:
            case LOOKBEHIND /*22*/:
            case NEGATIVELOOKBEHIND /*23*/:
            case CONDITION /*26*/:
                container.token = null;
            case PAREN /*6*/:
            case INDEPENDENT /*24*/:
                getChild(FC_CONTINUE).findFixedString(container, options);
            case STRING /*10*/:
                container.token = this;
                container.options = options;
            case MODIFIERGROUP /*25*/:
                getChild(FC_CONTINUE).findFixedString(container, (options | ((ModifierToken) this).getOptions()) & (((ModifierToken) this).getOptionsMask() ^ -1));
            default:
                throw new RuntimeException("Token#findFixedString(): Invalid Type: " + this.type);
        }
    }

    boolean match(int ch) {
        throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static mf.org.apache.xerces.impl.xpath.regex.RangeToken getRange(java.lang.String r26, boolean r27) {
        /*
        r22 = categories;
        r22 = r22.size();
        if (r22 != 0) goto L_0x03b8;
    L_0x0008:
        r23 = categories;
        monitor-enter(r23);
        r22 = categoryNames;	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r0 = r0.length;	 Catch:{ all -> 0x0451 }
        r22 = r0;
        r0 = r22;
        r0 = new mf.org.apache.xerces.impl.xpath.regex.Token[r0];	 Catch:{ all -> 0x0451 }
        r17 = r0;
        r6 = 0;
    L_0x0019:
        r0 = r17;
        r0 = r0.length;	 Catch:{ all -> 0x0451 }
        r22 = r0;
        r0 = r22;
        if (r6 < r0) goto L_0x03c9;
    L_0x0022:
        r6 = 0;
    L_0x0023:
        r22 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r0 = r22;
        if (r6 < r0) goto L_0x03d3;
    L_0x0029:
        r22 = 0;
        r22 = r17[r22];	 Catch:{ all -> 0x0451 }
        r24 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r25 = 1114111; // 0x10ffff float:1.561202E-39 double:5.50444E-318;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.addRange(r1, r2);	 Catch:{ all -> 0x0451 }
        r6 = 0;
    L_0x003c:
        r0 = r17;
        r0 = r0.length;	 Catch:{ all -> 0x0451 }
        r22 = r0;
        r0 = r22;
        if (r6 < r0) goto L_0x0473;
    L_0x0045:
        r4 = new java.lang.StringBuffer;	 Catch:{ all -> 0x0451 }
        r22 = 50;
        r0 = r22;
        r4.<init>(r0);	 Catch:{ all -> 0x0451 }
        r6 = 0;
    L_0x004f:
        r22 = blockNames;	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r0 = r0.length;	 Catch:{ all -> 0x0451 }
        r22 = r0;
        r0 = r22;
        if (r6 < r0) goto L_0x04b5;
    L_0x005a:
        r22 = "ASSIGNED";
        r24 = "Cn";
        r25 = 0;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "UNASSIGNED";
        r24 = "Cn";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r3 = createRange();	 Catch:{ all -> 0x0451 }
        r22 = 0;
        r24 = 1114111; // 0x10ffff float:1.561202E-39 double:5.50444E-318;
        r0 = r22;
        r1 = r24;
        r3.addRange(r0, r1);	 Catch:{ all -> 0x0451 }
        r22 = categories;	 Catch:{ all -> 0x0451 }
        r24 = "ALL";
        r0 = r22;
        r1 = r24;
        r0.put(r1, r3);	 Catch:{ all -> 0x0451 }
        r22 = categories2;	 Catch:{ all -> 0x0451 }
        r24 = "ALL";
        r25 = complementRanges(r3);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.put(r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "ASSIGNED";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "UNASSIGNED";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "ALL";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r8 = createRange();	 Catch:{ all -> 0x0451 }
        r22 = 1;
        r22 = r17[r22];	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r8.mergeRanges(r0);	 Catch:{ all -> 0x0451 }
        r22 = 2;
        r22 = r17[r22];	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r8.mergeRanges(r0);	 Catch:{ all -> 0x0451 }
        r22 = 5;
        r22 = r17[r22];	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r8.mergeRanges(r0);	 Catch:{ all -> 0x0451 }
        r22 = categories;	 Catch:{ all -> 0x0451 }
        r24 = "IsAlpha";
        r0 = r22;
        r1 = r24;
        r0.put(r1, r8);	 Catch:{ all -> 0x0451 }
        r22 = categories2;	 Catch:{ all -> 0x0451 }
        r24 = "IsAlpha";
        r25 = complementRanges(r8);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.put(r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsAlpha";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r7 = createRange();	 Catch:{ all -> 0x0451 }
        r7.mergeRanges(r8);	 Catch:{ all -> 0x0451 }
        r22 = 9;
        r22 = r17[r22];	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r7.mergeRanges(r0);	 Catch:{ all -> 0x0451 }
        r22 = categories;	 Catch:{ all -> 0x0451 }
        r24 = "IsAlnum";
        r0 = r22;
        r1 = r24;
        r0.put(r1, r7);	 Catch:{ all -> 0x0451 }
        r22 = categories2;	 Catch:{ all -> 0x0451 }
        r24 = "IsAlnum";
        r25 = complementRanges(r7);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.put(r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsAlnum";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r11 = createRange();	 Catch:{ all -> 0x0451 }
        r22 = token_spaces;	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r11.mergeRanges(r0);	 Catch:{ all -> 0x0451 }
        r22 = 34;
        r22 = r17[r22];	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r11.mergeRanges(r0);	 Catch:{ all -> 0x0451 }
        r22 = categories;	 Catch:{ all -> 0x0451 }
        r24 = "IsSpace";
        r0 = r22;
        r1 = r24;
        r0.put(r1, r11);	 Catch:{ all -> 0x0451 }
        r22 = categories2;	 Catch:{ all -> 0x0451 }
        r24 = "IsSpace";
        r25 = complementRanges(r11);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.put(r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsSpace";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r12 = createRange();	 Catch:{ all -> 0x0451 }
        r12.mergeRanges(r7);	 Catch:{ all -> 0x0451 }
        r22 = 95;
        r24 = 95;
        r0 = r22;
        r1 = r24;
        r12.addRange(r0, r1);	 Catch:{ all -> 0x0451 }
        r22 = categories;	 Catch:{ all -> 0x0451 }
        r24 = "IsWord";
        r0 = r22;
        r1 = r24;
        r0.put(r1, r12);	 Catch:{ all -> 0x0451 }
        r22 = categories2;	 Catch:{ all -> 0x0451 }
        r24 = "IsWord";
        r25 = complementRanges(r12);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.put(r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsWord";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r9 = createRange();	 Catch:{ all -> 0x0451 }
        r22 = 0;
        r24 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r0 = r22;
        r1 = r24;
        r9.addRange(r0, r1);	 Catch:{ all -> 0x0451 }
        r22 = categories;	 Catch:{ all -> 0x0451 }
        r24 = "IsASCII";
        r0 = r22;
        r1 = r24;
        r0.put(r1, r9);	 Catch:{ all -> 0x0451 }
        r22 = categories2;	 Catch:{ all -> 0x0451 }
        r24 = "IsASCII";
        r25 = complementRanges(r9);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.put(r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsASCII";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r10 = createRange();	 Catch:{ all -> 0x0451 }
        r22 = 35;
        r22 = r17[r22];	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r10.mergeRanges(r0);	 Catch:{ all -> 0x0451 }
        r22 = 32;
        r24 = 32;
        r0 = r22;
        r1 = r24;
        r10.addRange(r0, r1);	 Catch:{ all -> 0x0451 }
        r22 = categories;	 Catch:{ all -> 0x0451 }
        r24 = "IsGraph";
        r25 = complementRanges(r10);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.put(r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = categories2;	 Catch:{ all -> 0x0451 }
        r24 = "IsGraph";
        r0 = r22;
        r1 = r24;
        r0.put(r1, r10);	 Catch:{ all -> 0x0451 }
        r22 = "IsGraph";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r13 = createRange();	 Catch:{ all -> 0x0451 }
        r22 = 48;
        r24 = 57;
        r0 = r22;
        r1 = r24;
        r13.addRange(r0, r1);	 Catch:{ all -> 0x0451 }
        r22 = 65;
        r24 = 70;
        r0 = r22;
        r1 = r24;
        r13.addRange(r0, r1);	 Catch:{ all -> 0x0451 }
        r22 = 97;
        r24 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        r0 = r22;
        r1 = r24;
        r13.addRange(r0, r1);	 Catch:{ all -> 0x0451 }
        r22 = categories;	 Catch:{ all -> 0x0451 }
        r24 = "IsXDigit";
        r25 = complementRanges(r13);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.put(r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = categories2;	 Catch:{ all -> 0x0451 }
        r24 = "IsXDigit";
        r0 = r22;
        r1 = r24;
        r0.put(r1, r13);	 Catch:{ all -> 0x0451 }
        r22 = "IsXDigit";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "IsDigit";
        r24 = "Nd";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsUpper";
        r24 = "Lu";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsLower";
        r24 = "Ll";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsCntrl";
        r24 = "C";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsPrint";
        r24 = "C";
        r25 = 0;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsPunct";
        r24 = "P";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "IsDigit";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "IsUpper";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "IsLower";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "IsCntrl";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "IsPrint";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "IsPunct";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "alpha";
        r24 = "IsAlpha";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "alnum";
        r24 = "IsAlnum";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "ascii";
        r24 = "IsASCII";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "cntrl";
        r24 = "IsCntrl";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "digit";
        r24 = "IsDigit";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "graph";
        r24 = "IsGraph";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "lower";
        r24 = "IsLower";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "print";
        r24 = "IsPrint";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "punct";
        r24 = "IsPunct";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "space";
        r24 = "IsSpace";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "upper";
        r24 = "IsUpper";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "word";
        r24 = "IsWord";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "xdigit";
        r24 = "IsXDigit";
        r25 = 1;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        setAlias(r0, r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = "alpha";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "alnum";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "ascii";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "cntrl";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "digit";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "graph";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "lower";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "print";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "punct";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "space";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "upper";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "word";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        r22 = "xdigit";
        registerNonXS(r22);	 Catch:{ all -> 0x0451 }
        monitor-exit(r23);	 Catch:{ all -> 0x0451 }
    L_0x03b8:
        if (r27 == 0) goto L_0x059e;
    L_0x03ba:
        r22 = categories;
        r0 = r22;
        r1 = r26;
        r22 = r0.get(r1);
        r22 = (mf.org.apache.xerces.impl.xpath.regex.RangeToken) r22;
        r20 = r22;
    L_0x03c8:
        return r20;
    L_0x03c9:
        r22 = createRange();	 Catch:{ all -> 0x0451 }
        r17[r6] = r22;	 Catch:{ all -> 0x0451 }
        r6 = r6 + 1;
        goto L_0x0019;
    L_0x03d3:
        r0 = (char) r6;	 Catch:{ all -> 0x0451 }
        r22 = r0;
        r21 = java.lang.Character.getType(r22);	 Catch:{ all -> 0x0451 }
        r22 = 21;
        r0 = r21;
        r1 = r22;
        if (r0 == r1) goto L_0x03ea;
    L_0x03e2:
        r22 = 22;
        r0 = r21;
        r1 = r22;
        if (r0 != r1) goto L_0x042a;
    L_0x03ea:
        r22 = 171; // 0xab float:2.4E-43 double:8.45E-322;
        r0 = r22;
        if (r6 == r0) goto L_0x040e;
    L_0x03f0:
        r22 = 8216; // 0x2018 float:1.1513E-41 double:4.059E-320;
        r0 = r22;
        if (r6 == r0) goto L_0x040e;
    L_0x03f6:
        r22 = 8219; // 0x201b float:1.1517E-41 double:4.0607E-320;
        r0 = r22;
        if (r6 == r0) goto L_0x040e;
    L_0x03fc:
        r22 = 8220; // 0x201c float:1.1519E-41 double:4.061E-320;
        r0 = r22;
        if (r6 == r0) goto L_0x040e;
    L_0x0402:
        r22 = 8223; // 0x201f float:1.1523E-41 double:4.0627E-320;
        r0 = r22;
        if (r6 == r0) goto L_0x040e;
    L_0x0408:
        r22 = 8249; // 0x2039 float:1.156E-41 double:4.0755E-320;
        r0 = r22;
        if (r6 != r0) goto L_0x0410;
    L_0x040e:
        r21 = 29;
    L_0x0410:
        r22 = 187; // 0xbb float:2.62E-43 double:9.24E-322;
        r0 = r22;
        if (r6 == r0) goto L_0x0428;
    L_0x0416:
        r22 = 8217; // 0x2019 float:1.1514E-41 double:4.0597E-320;
        r0 = r22;
        if (r6 == r0) goto L_0x0428;
    L_0x041c:
        r22 = 8221; // 0x201d float:1.152E-41 double:4.0617E-320;
        r0 = r22;
        if (r6 == r0) goto L_0x0428;
    L_0x0422:
        r22 = 8250; // 0x203a float:1.1561E-41 double:4.076E-320;
        r0 = r22;
        if (r6 != r0) goto L_0x042a;
    L_0x0428:
        r21 = 30;
    L_0x042a:
        r22 = r17[r21];	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r0.addRange(r6, r6);	 Catch:{ all -> 0x0451 }
        switch(r21) {
            case 0: goto L_0x046a;
            case 1: goto L_0x0454;
            case 2: goto L_0x0454;
            case 3: goto L_0x0454;
            case 4: goto L_0x0454;
            case 5: goto L_0x0454;
            case 6: goto L_0x0461;
            case 7: goto L_0x0461;
            case 8: goto L_0x0461;
            case 9: goto L_0x0464;
            case 10: goto L_0x0464;
            case 11: goto L_0x0464;
            case 12: goto L_0x0467;
            case 13: goto L_0x0467;
            case 14: goto L_0x0467;
            case 15: goto L_0x046a;
            case 16: goto L_0x046a;
            case 17: goto L_0x0434;
            case 18: goto L_0x046a;
            case 19: goto L_0x046a;
            case 20: goto L_0x046d;
            case 21: goto L_0x046d;
            case 22: goto L_0x046d;
            case 23: goto L_0x046d;
            case 24: goto L_0x046d;
            case 25: goto L_0x0470;
            case 26: goto L_0x0470;
            case 27: goto L_0x0470;
            case 28: goto L_0x0470;
            case 29: goto L_0x046d;
            case 30: goto L_0x046d;
            default: goto L_0x0434;
        };	 Catch:{ all -> 0x0451 }
    L_0x0434:
        r22 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0451 }
        r24 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0451 }
        r25 = "mf.org.apache.xerces.utils.regex.Token#getRange(): Unknown Unicode category: ";
        r24.<init>(r25);	 Catch:{ all -> 0x0451 }
        r0 = r24;
        r1 = r21;
        r24 = r0.append(r1);	 Catch:{ all -> 0x0451 }
        r24 = r24.toString();	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r0.<init>(r1);	 Catch:{ all -> 0x0451 }
        throw r22;	 Catch:{ all -> 0x0451 }
    L_0x0451:
        r22 = move-exception;
        monitor-exit(r23);	 Catch:{ all -> 0x0451 }
        throw r22;
    L_0x0454:
        r21 = 31;
    L_0x0456:
        r22 = r17[r21];	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r0.addRange(r6, r6);	 Catch:{ all -> 0x0451 }
        r6 = r6 + 1;
        goto L_0x0023;
    L_0x0461:
        r21 = 32;
        goto L_0x0456;
    L_0x0464:
        r21 = 33;
        goto L_0x0456;
    L_0x0467:
        r21 = 34;
        goto L_0x0456;
    L_0x046a:
        r21 = 35;
        goto L_0x0456;
    L_0x046d:
        r21 = 36;
        goto L_0x0456;
    L_0x0470:
        r21 = 37;
        goto L_0x0456;
    L_0x0473:
        r22 = categoryNames;	 Catch:{ all -> 0x0451 }
        r22 = r22[r6];	 Catch:{ all -> 0x0451 }
        if (r22 == 0) goto L_0x04b1;
    L_0x0479:
        if (r6 != 0) goto L_0x048b;
    L_0x047b:
        r22 = r17[r6];	 Catch:{ all -> 0x0451 }
        r24 = 65536; // 0x10000 float:9.18355E-41 double:3.2379E-319;
        r25 = 1114111; // 0x10ffff float:1.561202E-39 double:5.50444E-318;
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.addRange(r1, r2);	 Catch:{ all -> 0x0451 }
    L_0x048b:
        r22 = categories;	 Catch:{ all -> 0x0451 }
        r24 = categoryNames;	 Catch:{ all -> 0x0451 }
        r24 = r24[r6];	 Catch:{ all -> 0x0451 }
        r25 = r17[r6];	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.put(r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = categories2;	 Catch:{ all -> 0x0451 }
        r24 = categoryNames;	 Catch:{ all -> 0x0451 }
        r24 = r24[r6];	 Catch:{ all -> 0x0451 }
        r25 = r17[r6];	 Catch:{ all -> 0x0451 }
        r25 = complementRanges(r25);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r2 = r25;
        r0.put(r1, r2);	 Catch:{ all -> 0x0451 }
    L_0x04b1:
        r6 = r6 + 1;
        goto L_0x003c;
    L_0x04b5:
        r16 = createRange();	 Catch:{ all -> 0x0451 }
        r22 = 84;
        r0 = r22;
        if (r6 >= r0) goto L_0x0569;
    L_0x04bf:
        r14 = r6 * 2;
        r22 = "\u0000\u007f\u0080\u00ff\u0100\u017f\u0180\u024f\u0250\u02af\u02b0\u02ff\u0300\u036f\u0370\u03ff\u0400\u04ff\u0530\u058f\u0590\u05ff\u0600\u06ff\u0700\u074f\u0780\u07bf\u0900\u097f\u0980\u09ff\u0a00\u0a7f\u0a80\u0aff\u0b00\u0b7f\u0b80\u0bff\u0c00\u0c7f\u0c80\u0cff\u0d00\u0d7f\u0d80\u0dff\u0e00\u0e7f\u0e80\u0eff\u0f00\u0fff\u1000\u109f\u10a0\u10ff\u1100\u11ff\u1200\u137f\u13a0\u13ff\u1400\u167f\u1680\u169f\u16a0\u16ff\u1780\u17ff\u1800\u18af\u1e00\u1eff\u1f00\u1fff\u2000\u206f\u2070\u209f\u20a0\u20cf\u20d0\u20ff\u2100\u214f\u2150\u218f\u2190\u21ff\u2200\u22ff\u2300\u23ff\u2400\u243f\u2440\u245f\u2460\u24ff\u2500\u257f\u2580\u259f\u25a0\u25ff\u2600\u26ff\u2700\u27bf\u2800\u28ff\u2e80\u2eff\u2f00\u2fdf\u2ff0\u2fff\u3000\u303f\u3040\u309f\u30a0\u30ff\u3100\u312f\u3130\u318f\u3190\u319f\u31a0\u31bf\u3200\u32ff\u3300\u33ff\u3400\u4db5\u4e00\u9fff\ua000\ua48f\ua490\ua4cf\uac00\ud7a3\ue000\uf8ff\uf900\ufaff\ufb00\ufb4f\ufb50\ufdff\ufe20\ufe2f\ufe30\ufe4f\ufe50\ufe6f\ufe70\ufefe\ufeff\ufeff\uff00\uffef";
        r0 = r22;
        r19 = r0.charAt(r14);	 Catch:{ all -> 0x0451 }
        r22 = "\u0000\u007f\u0080\u00ff\u0100\u017f\u0180\u024f\u0250\u02af\u02b0\u02ff\u0300\u036f\u0370\u03ff\u0400\u04ff\u0530\u058f\u0590\u05ff\u0600\u06ff\u0700\u074f\u0780\u07bf\u0900\u097f\u0980\u09ff\u0a00\u0a7f\u0a80\u0aff\u0b00\u0b7f\u0b80\u0bff\u0c00\u0c7f\u0c80\u0cff\u0d00\u0d7f\u0d80\u0dff\u0e00\u0e7f\u0e80\u0eff\u0f00\u0fff\u1000\u109f\u10a0\u10ff\u1100\u11ff\u1200\u137f\u13a0\u13ff\u1400\u167f\u1680\u169f\u16a0\u16ff\u1780\u17ff\u1800\u18af\u1e00\u1eff\u1f00\u1fff\u2000\u206f\u2070\u209f\u20a0\u20cf\u20d0\u20ff\u2100\u214f\u2150\u218f\u2190\u21ff\u2200\u22ff\u2300\u23ff\u2400\u243f\u2440\u245f\u2460\u24ff\u2500\u257f\u2580\u259f\u25a0\u25ff\u2600\u26ff\u2700\u27bf\u2800\u28ff\u2e80\u2eff\u2f00\u2fdf\u2ff0\u2fff\u3000\u303f\u3040\u309f\u30a0\u30ff\u3100\u312f\u3130\u318f\u3190\u319f\u31a0\u31bf\u3200\u32ff\u3300\u33ff\u3400\u4db5\u4e00\u9fff\ua000\ua48f\ua490\ua4cf\uac00\ud7a3\ue000\uf8ff\uf900\ufaff\ufb00\ufb4f\ufb50\ufdff\ufe20\ufe2f\ufe30\ufe4f\ufe50\ufe6f\ufe70\ufefe\ufeff\ufeff\uff00\uffef";
        r24 = r14 + 1;
        r0 = r22;
        r1 = r24;
        r18 = r0.charAt(r1);	 Catch:{ all -> 0x0451 }
        r0 = r16;
        r1 = r19;
        r2 = r18;
        r0.addRange(r1, r2);	 Catch:{ all -> 0x0451 }
    L_0x04de:
        r22 = blockNames;	 Catch:{ all -> 0x0451 }
        r15 = r22[r6];	 Catch:{ all -> 0x0451 }
        r22 = "Specials";
        r0 = r22;
        r22 = r15.equals(r0);	 Catch:{ all -> 0x0451 }
        if (r22 == 0) goto L_0x04fb;
    L_0x04ec:
        r22 = 65520; // 0xfff0 float:9.1813E-41 double:3.2371E-319;
        r24 = 65533; // 0xfffd float:9.1831E-41 double:3.23776E-319;
        r0 = r16;
        r1 = r22;
        r2 = r24;
        r0.addRange(r1, r2);	 Catch:{ all -> 0x0451 }
    L_0x04fb:
        r22 = "Private Use";
        r0 = r22;
        r22 = r15.equals(r0);	 Catch:{ all -> 0x0451 }
        if (r22 == 0) goto L_0x0521;
    L_0x0505:
        r22 = 983040; // 0xf0000 float:1.377532E-39 double:4.856863E-318;
        r24 = 1048573; // 0xffffd float:1.469364E-39 double:5.18064E-318;
        r0 = r16;
        r1 = r22;
        r2 = r24;
        r0.addRange(r1, r2);	 Catch:{ all -> 0x0451 }
        r22 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r24 = 1114109; // 0x10fffd float:1.561199E-39 double:5.50443E-318;
        r0 = r16;
        r1 = r22;
        r2 = r24;
        r0.addRange(r1, r2);	 Catch:{ all -> 0x0451 }
    L_0x0521:
        r22 = categories;	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r16;
        r0.put(r15, r1);	 Catch:{ all -> 0x0451 }
        r22 = categories2;	 Catch:{ all -> 0x0451 }
        r24 = complementRanges(r16);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r1 = r24;
        r0.put(r15, r1);	 Catch:{ all -> 0x0451 }
        r22 = 0;
        r0 = r22;
        r4.setLength(r0);	 Catch:{ all -> 0x0451 }
        r22 = "Is";
        r0 = r22;
        r4.append(r0);	 Catch:{ all -> 0x0451 }
        r22 = 32;
        r0 = r22;
        r22 = r15.indexOf(r0);	 Catch:{ all -> 0x0451 }
        if (r22 < 0) goto L_0x059a;
    L_0x054f:
        r5 = 0;
    L_0x0550:
        r22 = r15.length();	 Catch:{ all -> 0x0451 }
        r0 = r22;
        if (r5 < r0) goto L_0x0582;
    L_0x0558:
        r22 = r4.toString();	 Catch:{ all -> 0x0451 }
        r24 = 1;
        r0 = r22;
        r1 = r24;
        setAlias(r0, r15, r1);	 Catch:{ all -> 0x0451 }
        r6 = r6 + 1;
        goto L_0x004f;
    L_0x0569:
        r22 = r6 + -84;
        r14 = r22 * 2;
        r22 = nonBMPBlockRanges;	 Catch:{ all -> 0x0451 }
        r22 = r22[r14];	 Catch:{ all -> 0x0451 }
        r24 = nonBMPBlockRanges;	 Catch:{ all -> 0x0451 }
        r25 = r14 + 1;
        r24 = r24[r25];	 Catch:{ all -> 0x0451 }
        r0 = r16;
        r1 = r22;
        r2 = r24;
        r0.addRange(r1, r2);	 Catch:{ all -> 0x0451 }
        goto L_0x04de;
    L_0x0582:
        r22 = r15.charAt(r5);	 Catch:{ all -> 0x0451 }
        r24 = 32;
        r0 = r22;
        r1 = r24;
        if (r0 == r1) goto L_0x0597;
    L_0x058e:
        r22 = r15.charAt(r5);	 Catch:{ all -> 0x0451 }
        r0 = r22;
        r4.append(r0);	 Catch:{ all -> 0x0451 }
    L_0x0597:
        r5 = r5 + 1;
        goto L_0x0550;
    L_0x059a:
        r4.append(r15);	 Catch:{ all -> 0x0451 }
        goto L_0x0558;
    L_0x059e:
        r22 = categories2;
        r0 = r22;
        r1 = r26;
        r22 = r0.get(r1);
        r22 = (mf.org.apache.xerces.impl.xpath.regex.RangeToken) r22;
        r20 = r22;
        goto L_0x03c8;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xpath.regex.Token.getRange(java.lang.String, boolean):mf.org.apache.xerces.impl.xpath.regex.RangeToken");
    }

    protected static RangeToken getRange(String name, boolean positive, boolean xs) {
        RangeToken range = getRange(name, positive);
        if (xs && range != null && isRegisterNonXS(name)) {
            return null;
        }
        return range;
    }

    protected static void registerNonXS(String name) {
        if (nonxs == null) {
            nonxs = new Hashtable();
        }
        nonxs.put(name, name);
    }

    protected static boolean isRegisterNonXS(String name) {
        if (nonxs == null) {
            return false;
        }
        return nonxs.containsKey(name);
    }

    private static void setAlias(String newName, String name, boolean positive) {
        Token t1 = (Token) categories.get(name);
        Token t2 = (Token) categories2.get(name);
        if (positive) {
            categories.put(newName, t1);
            categories2.put(newName, t2);
            return;
        }
        categories2.put(newName, t1);
        categories.put(newName, t2);
    }

    static synchronized Token getGraphemePattern() {
        Token token;
        synchronized (Token.class) {
            if (token_grapheme != null) {
                token = token_grapheme;
            } else {
                Token base_char = createRange();
                base_char.mergeRanges(getRange("ASSIGNED", COUNTTOKENS));
                base_char.subtractRanges(getRange("M", COUNTTOKENS));
                base_char.subtractRanges(getRange("C", COUNTTOKENS));
                Token virama = createRange();
                for (int i = FC_CONTINUE; i < viramaString.length(); i += FC_TERMINAL) {
                    virama.addRange(i, i);
                }
                Token combiner_wo_virama = createRange();
                combiner_wo_virama.mergeRanges(getRange("M", COUNTTOKENS));
                combiner_wo_virama.addRange(4448, 4607);
                combiner_wo_virama.addRange(65438, 65439);
                Token left = createUnion();
                left.addChild(base_char);
                left.addChild(token_empty);
                Token foo = createUnion();
                foo.addChild(createConcat(virama, getRange("L", COUNTTOKENS)));
                foo.addChild(combiner_wo_virama);
                token_grapheme = createConcat(left, createClosure(foo));
                token = token_grapheme;
            }
        }
        return token;
    }

    static synchronized Token getCombiningCharacterSequence() {
        Token token;
        synchronized (Token.class) {
            if (token_ccs != null) {
                token = token_ccs;
            } else {
                token_ccs = createConcat(getRange("M", false), createClosure(getRange("M", COUNTTOKENS)));
                token = token_ccs;
            }
        }
        return token;
    }
}
