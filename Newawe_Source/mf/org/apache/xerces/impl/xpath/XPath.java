package mf.org.apache.xerces.impl.xpath;

import android.support.v4.media.TransportMediator;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xml.serialize.Method;
import org.apache.http.HttpStatus;
import org.apache.http.cookie.ClientCookie;

public class XPath {
    private static final boolean DEBUG_ALL = false;
    private static final boolean DEBUG_ANY = false;
    private static final boolean DEBUG_XPATH_PARSE = false;
    protected final String fExpression;
    protected final LocationPath[] fLocationPaths;
    protected final SymbolTable fSymbolTable;

    public static class Axis implements Cloneable {
        public static final short ATTRIBUTE = (short) 2;
        public static final short CHILD = (short) 1;
        public static final short DESCENDANT = (short) 4;
        public static final short SELF = (short) 3;
        public final short type;

        public Axis(short type) {
            this.type = type;
        }

        protected Axis(Axis axis) {
            this.type = axis.type;
        }

        public String toString() {
            switch (this.type) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    return "child";
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    return "attribute";
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    return "self";
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    return "descendant";
                default:
                    return "???";
            }
        }

        public Object clone() {
            return new Axis(this);
        }
    }

    public static class LocationPath implements Cloneable {
        public final Step[] steps;

        public LocationPath(Step[] steps) {
            this.steps = steps;
        }

        protected LocationPath(LocationPath path) {
            this.steps = new Step[path.steps.length];
            for (int i = 0; i < this.steps.length; i++) {
                this.steps[i] = (Step) path.steps[i].clone();
            }
        }

        public String toString() {
            StringBuffer str = new StringBuffer();
            int i = 0;
            while (i < this.steps.length) {
                if (!(i <= 0 || this.steps[i - 1].axis.type == (short) 4 || this.steps[i].axis.type == (short) 4)) {
                    str.append('/');
                }
                str.append(this.steps[i].toString());
                i++;
            }
            return str.toString();
        }

        public Object clone() {
            return new LocationPath(this);
        }
    }

    public static class NodeTest implements Cloneable {
        public static final short NAMESPACE = (short) 4;
        public static final short NODE = (short) 3;
        public static final short QNAME = (short) 1;
        public static final short WILDCARD = (short) 2;
        public final QName name;
        public final short type;

        public NodeTest(short type) {
            this.name = new QName();
            this.type = type;
        }

        public NodeTest(QName name) {
            this.name = new QName();
            this.type = QNAME;
            this.name.setValues(name);
        }

        public NodeTest(String prefix, String uri) {
            this.name = new QName();
            this.type = NAMESPACE;
            this.name.setValues(prefix, null, null, uri);
        }

        public NodeTest(NodeTest nodeTest) {
            this.name = new QName();
            this.type = nodeTest.type;
            this.name.setValues(nodeTest.name);
        }

        public String toString() {
            switch (this.type) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    if (this.name.prefix.length() == 0) {
                        return this.name.localpart;
                    }
                    if (this.name.uri != null) {
                        return new StringBuilder(String.valueOf(this.name.prefix)).append(':').append(this.name.localpart).toString();
                    }
                    return "{" + this.name.uri + '}' + this.name.prefix + ':' + this.name.localpart;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    return "*";
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    return "node()";
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    if (this.name.prefix.length() == 0) {
                        return "???:*";
                    }
                    if (this.name.uri != null) {
                        return new StringBuilder(String.valueOf(this.name.prefix)).append(":*").toString();
                    }
                    return "{" + this.name.uri + '}' + this.name.prefix + ":*";
                default:
                    return "???";
            }
        }

        public Object clone() {
            return new NodeTest(this);
        }
    }

    private static class Scanner {
        private static final byte CHARTYPE_ATSIGN = (byte) 19;
        private static final byte CHARTYPE_CLOSE_BRACKET = (byte) 22;
        private static final byte CHARTYPE_CLOSE_PAREN = (byte) 7;
        private static final byte CHARTYPE_COLON = (byte) 15;
        private static final byte CHARTYPE_COMMA = (byte) 10;
        private static final byte CHARTYPE_DIGIT = (byte) 14;
        private static final byte CHARTYPE_DOLLAR = (byte) 5;
        private static final byte CHARTYPE_EQUAL = (byte) 17;
        private static final byte CHARTYPE_EXCLAMATION = (byte) 3;
        private static final byte CHARTYPE_GREATER = (byte) 18;
        private static final byte CHARTYPE_INVALID = (byte) 0;
        private static final byte CHARTYPE_LESS = (byte) 16;
        private static final byte CHARTYPE_LETTER = (byte) 20;
        private static final byte CHARTYPE_MINUS = (byte) 11;
        private static final byte CHARTYPE_NONASCII = (byte) 25;
        private static final byte CHARTYPE_OPEN_BRACKET = (byte) 21;
        private static final byte CHARTYPE_OPEN_PAREN = (byte) 6;
        private static final byte CHARTYPE_OTHER = (byte) 1;
        private static final byte CHARTYPE_PERIOD = (byte) 12;
        private static final byte CHARTYPE_PLUS = (byte) 9;
        private static final byte CHARTYPE_QUOTE = (byte) 4;
        private static final byte CHARTYPE_SLASH = (byte) 13;
        private static final byte CHARTYPE_STAR = (byte) 8;
        private static final byte CHARTYPE_UNDERSCORE = (byte) 23;
        private static final byte CHARTYPE_UNION = (byte) 24;
        private static final byte CHARTYPE_WHITESPACE = (byte) 2;
        private static final byte[] fASCIICharMap;
        private static final String fAncestorOrSelfSymbol;
        private static final String fAncestorSymbol;
        private static final String fAndSymbol;
        private static final String fAttributeSymbol;
        private static final String fChildSymbol;
        private static final String fCommentSymbol;
        private static final String fDescendantOrSelfSymbol;
        private static final String fDescendantSymbol;
        private static final String fDivSymbol;
        private static final String fFollowingSiblingSymbol;
        private static final String fFollowingSymbol;
        private static final String fModSymbol;
        private static final String fNamespaceSymbol;
        private static final String fNodeSymbol;
        private static final String fOrSymbol;
        private static final String fPISymbol;
        private static final String fParentSymbol;
        private static final String fPrecedingSiblingSymbol;
        private static final String fPrecedingSymbol;
        private static final String fSelfSymbol;
        private static final String fTextSymbol;
        private SymbolTable fSymbolTable;

        static {
            byte[] bArr = new byte[TransportMediator.FLAG_KEY_MEDIA_NEXT];
            bArr[9] = CHARTYPE_WHITESPACE;
            bArr[10] = CHARTYPE_WHITESPACE;
            bArr[13] = CHARTYPE_WHITESPACE;
            bArr[32] = CHARTYPE_WHITESPACE;
            bArr[33] = CHARTYPE_EXCLAMATION;
            bArr[34] = CHARTYPE_QUOTE;
            bArr[35] = CHARTYPE_OTHER;
            bArr[36] = CHARTYPE_DOLLAR;
            bArr[37] = CHARTYPE_OTHER;
            bArr[38] = CHARTYPE_OTHER;
            bArr[39] = CHARTYPE_QUOTE;
            bArr[40] = CHARTYPE_OPEN_PAREN;
            bArr[41] = CHARTYPE_CLOSE_PAREN;
            bArr[42] = CHARTYPE_STAR;
            bArr[43] = CHARTYPE_PLUS;
            bArr[44] = CHARTYPE_COMMA;
            bArr[45] = CHARTYPE_MINUS;
            bArr[46] = CHARTYPE_PERIOD;
            bArr[47] = CHARTYPE_SLASH;
            bArr[48] = CHARTYPE_DIGIT;
            bArr[49] = CHARTYPE_DIGIT;
            bArr[50] = CHARTYPE_DIGIT;
            bArr[51] = CHARTYPE_DIGIT;
            bArr[52] = CHARTYPE_DIGIT;
            bArr[53] = CHARTYPE_DIGIT;
            bArr[54] = CHARTYPE_DIGIT;
            bArr[55] = CHARTYPE_DIGIT;
            bArr[56] = CHARTYPE_DIGIT;
            bArr[57] = CHARTYPE_DIGIT;
            bArr[58] = CHARTYPE_COLON;
            bArr[59] = CHARTYPE_OTHER;
            bArr[60] = CHARTYPE_LESS;
            bArr[61] = CHARTYPE_EQUAL;
            bArr[62] = CHARTYPE_GREATER;
            bArr[63] = CHARTYPE_OTHER;
            bArr[64] = CHARTYPE_ATSIGN;
            bArr[65] = CHARTYPE_LETTER;
            bArr[66] = CHARTYPE_LETTER;
            bArr[67] = CHARTYPE_LETTER;
            bArr[68] = CHARTYPE_LETTER;
            bArr[69] = CHARTYPE_LETTER;
            bArr[70] = CHARTYPE_LETTER;
            bArr[71] = CHARTYPE_LETTER;
            bArr[72] = CHARTYPE_LETTER;
            bArr[73] = CHARTYPE_LETTER;
            bArr[74] = CHARTYPE_LETTER;
            bArr[75] = CHARTYPE_LETTER;
            bArr[76] = CHARTYPE_LETTER;
            bArr[77] = CHARTYPE_LETTER;
            bArr[78] = CHARTYPE_LETTER;
            bArr[79] = CHARTYPE_LETTER;
            bArr[80] = CHARTYPE_LETTER;
            bArr[81] = CHARTYPE_LETTER;
            bArr[82] = CHARTYPE_LETTER;
            bArr[83] = CHARTYPE_LETTER;
            bArr[84] = CHARTYPE_LETTER;
            bArr[85] = CHARTYPE_LETTER;
            bArr[86] = CHARTYPE_LETTER;
            bArr[87] = CHARTYPE_LETTER;
            bArr[88] = CHARTYPE_LETTER;
            bArr[89] = CHARTYPE_LETTER;
            bArr[90] = CHARTYPE_LETTER;
            bArr[91] = CHARTYPE_OPEN_BRACKET;
            bArr[92] = CHARTYPE_OTHER;
            bArr[93] = CHARTYPE_CLOSE_BRACKET;
            bArr[94] = CHARTYPE_OTHER;
            bArr[95] = CHARTYPE_UNDERSCORE;
            bArr[96] = CHARTYPE_OTHER;
            bArr[97] = CHARTYPE_LETTER;
            bArr[98] = CHARTYPE_LETTER;
            bArr[99] = CHARTYPE_LETTER;
            bArr[100] = CHARTYPE_LETTER;
            bArr[HttpStatus.SC_SWITCHING_PROTOCOLS] = CHARTYPE_LETTER;
            bArr[HttpStatus.SC_PROCESSING] = CHARTYPE_LETTER;
            bArr[C0302R.styleable.Theme_checkedTextViewStyle] = CHARTYPE_LETTER;
            bArr[C0302R.styleable.Theme_editTextStyle] = CHARTYPE_LETTER;
            bArr[C0302R.styleable.Theme_radioButtonStyle] = CHARTYPE_LETTER;
            bArr[C0302R.styleable.Theme_ratingBarStyle] = CHARTYPE_LETTER;
            bArr[C0302R.styleable.Theme_seekBarStyle] = CHARTYPE_LETTER;
            bArr[C0302R.styleable.Theme_spinnerStyle] = CHARTYPE_LETTER;
            bArr[C0302R.styleable.Theme_switchStyle] = CHARTYPE_LETTER;
            bArr[110] = CHARTYPE_LETTER;
            bArr[111] = CHARTYPE_LETTER;
            bArr[112] = CHARTYPE_LETTER;
            bArr[113] = CHARTYPE_LETTER;
            bArr[114] = CHARTYPE_LETTER;
            bArr[115] = CHARTYPE_LETTER;
            bArr[116] = CHARTYPE_LETTER;
            bArr[117] = CHARTYPE_LETTER;
            bArr[118] = CHARTYPE_LETTER;
            bArr[119] = CHARTYPE_LETTER;
            bArr[120] = CHARTYPE_LETTER;
            bArr[121] = CHARTYPE_LETTER;
            bArr[122] = CHARTYPE_LETTER;
            bArr[123] = CHARTYPE_OTHER;
            bArr[124] = CHARTYPE_UNION;
            bArr[125] = CHARTYPE_OTHER;
            bArr[TransportMediator.KEYCODE_MEDIA_PLAY] = CHARTYPE_OTHER;
            bArr[TransportMediator.KEYCODE_MEDIA_PAUSE] = CHARTYPE_OTHER;
            fASCIICharMap = bArr;
            fAndSymbol = "and".intern();
            fOrSymbol = "or".intern();
            fModSymbol = "mod".intern();
            fDivSymbol = "div".intern();
            fCommentSymbol = ClientCookie.COMMENT_ATTR.intern();
            fTextSymbol = Method.TEXT.intern();
            fPISymbol = "processing-instruction".intern();
            fNodeSymbol = "node".intern();
            fAncestorSymbol = "ancestor".intern();
            fAncestorOrSelfSymbol = "ancestor-or-self".intern();
            fAttributeSymbol = "attribute".intern();
            fChildSymbol = "child".intern();
            fDescendantSymbol = "descendant".intern();
            fDescendantOrSelfSymbol = "descendant-or-self".intern();
            fFollowingSymbol = "following".intern();
            fFollowingSiblingSymbol = "following-sibling".intern();
            fNamespaceSymbol = "namespace".intern();
            fParentSymbol = "parent".intern();
            fPrecedingSymbol = "preceding".intern();
            fPrecedingSiblingSymbol = "preceding-sibling".intern();
            fSelfSymbol = "self".intern();
        }

        public Scanner(SymbolTable symbolTable) {
            this.fSymbolTable = symbolTable;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean scanExpr(mf.org.apache.xerces.util.SymbolTable r19, mf.org.apache.xerces.impl.xpath.XPath.Tokens r20, java.lang.String r21, int r22, int r23) throws mf.org.apache.xerces.impl.xpath.XPathException {
            /*
            r18 = this;
            r15 = 0;
        L_0x0001:
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x000a;
        L_0x0007:
            r16 = 1;
        L_0x0009:
            return r16;
        L_0x000a:
            r5 = r21.charAt(r22);
        L_0x000e:
            r16 = 32;
            r0 = r16;
            if (r5 == r0) goto L_0x003a;
        L_0x0014:
            r16 = 10;
            r0 = r16;
            if (r5 == r0) goto L_0x003a;
        L_0x001a:
            r16 = 9;
            r0 = r16;
            if (r5 == r0) goto L_0x003a;
        L_0x0020:
            r16 = 13;
            r0 = r16;
            if (r5 == r0) goto L_0x003a;
        L_0x0026:
            r0 = r22;
            r1 = r23;
            if (r0 == r1) goto L_0x0007;
        L_0x002c:
            r16 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
            r0 = r16;
            if (r5 < r0) goto L_0x0047;
        L_0x0032:
            r6 = 25;
        L_0x0034:
            switch(r6) {
                case 3: goto L_0x0291;
                case 4: goto L_0x0349;
                case 5: goto L_0x03bc;
                case 6: goto L_0x004c;
                case 7: goto L_0x0061;
                case 8: goto L_0x045a;
                case 9: goto L_0x024f;
                case 10: goto L_0x01af;
                case 11: goto L_0x0265;
                case 12: goto L_0x00a2;
                case 13: goto L_0x01f5;
                case 14: goto L_0x03a0;
                case 15: goto L_0x01c5;
                case 16: goto L_0x02c1;
                case 17: goto L_0x027b;
                case 18: goto L_0x0305;
                case 19: goto L_0x0199;
                case 20: goto L_0x047f;
                case 21: goto L_0x0076;
                case 22: goto L_0x008c;
                case 23: goto L_0x047f;
                case 24: goto L_0x0239;
                case 25: goto L_0x047f;
                default: goto L_0x0037;
            };
        L_0x0037:
            r16 = 0;
            goto L_0x0009;
        L_0x003a:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 == r1) goto L_0x0026;
        L_0x0042:
            r5 = r21.charAt(r22);
            goto L_0x000e;
        L_0x0047:
            r16 = fASCIICharMap;
            r6 = r16[r5];
            goto L_0x0034;
        L_0x004c:
            r16 = 0;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x0060:
            goto L_0x0001;
        L_0x0061:
            r16 = 1;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x0075:
            goto L_0x0001;
        L_0x0076:
            r16 = 2;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x008a:
            goto L_0x0001;
        L_0x008c:
            r16 = 3;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x00a0:
            goto L_0x0001;
        L_0x00a2:
            r16 = r22 + 1;
            r0 = r16;
            r1 = r23;
            if (r0 != r1) goto L_0x00ba;
        L_0x00aa:
            r16 = 4;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r22 = r22 + 1;
            goto L_0x0001;
        L_0x00ba:
            r16 = r22 + 1;
            r0 = r21;
            r1 = r16;
            r5 = r0.charAt(r1);
            r16 = 46;
            r0 = r16;
            if (r5 != r0) goto L_0x00e0;
        L_0x00ca:
            r16 = 5;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r22 = r22 + 2;
        L_0x00d8:
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x00de:
            goto L_0x0001;
        L_0x00e0:
            r16 = 48;
            r0 = r16;
            if (r5 < r0) goto L_0x0107;
        L_0x00e6:
            r16 = 57;
            r0 = r16;
            if (r5 > r0) goto L_0x0107;
        L_0x00ec:
            r16 = 47;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r0 = r18;
            r1 = r20;
            r2 = r21;
            r3 = r23;
            r4 = r22;
            r22 = r0.scanNumber(r1, r2, r3, r4);
            goto L_0x00d8;
        L_0x0107:
            r16 = 47;
            r0 = r16;
            if (r5 != r0) goto L_0x011c;
        L_0x010d:
            r16 = 4;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r22 = r22 + 1;
            goto L_0x00d8;
        L_0x011c:
            r16 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
            r0 = r16;
            if (r5 != r0) goto L_0x0132;
        L_0x0122:
            r16 = 4;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r22 = r22 + 1;
            goto L_0x0001;
        L_0x0132:
            r16 = 32;
            r0 = r16;
            if (r5 == r0) goto L_0x014a;
        L_0x0138:
            r16 = 10;
            r0 = r16;
            if (r5 == r0) goto L_0x014a;
        L_0x013e:
            r16 = 9;
            r0 = r16;
            if (r5 == r0) goto L_0x014a;
        L_0x0144:
            r16 = 13;
            r0 = r16;
            if (r5 != r0) goto L_0x0191;
        L_0x014a:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x016c;
        L_0x0152:
            r0 = r22;
            r1 = r23;
            if (r0 == r1) goto L_0x015e;
        L_0x0158:
            r16 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
            r0 = r16;
            if (r5 != r0) goto L_0x0189;
        L_0x015e:
            r16 = 4;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            goto L_0x0001;
        L_0x016c:
            r5 = r21.charAt(r22);
            r16 = 32;
            r0 = r16;
            if (r5 == r0) goto L_0x014a;
        L_0x0176:
            r16 = 10;
            r0 = r16;
            if (r5 == r0) goto L_0x014a;
        L_0x017c:
            r16 = 9;
            r0 = r16;
            if (r5 == r0) goto L_0x014a;
        L_0x0182:
            r16 = 13;
            r0 = r16;
            if (r5 == r0) goto L_0x014a;
        L_0x0188:
            goto L_0x0152;
        L_0x0189:
            r16 = new mf.org.apache.xerces.impl.xpath.XPathException;
            r17 = "c-general-xpath";
            r16.<init>(r17);
            throw r16;
        L_0x0191:
            r16 = new mf.org.apache.xerces.impl.xpath.XPathException;
            r17 = "c-general-xpath";
            r16.<init>(r17);
            throw r16;
        L_0x0199:
            r16 = 6;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x01ad:
            goto L_0x0001;
        L_0x01af:
            r16 = 7;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x01c3:
            goto L_0x0001;
        L_0x01c5:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x01d1;
        L_0x01cd:
            r16 = 0;
            goto L_0x0009;
        L_0x01d1:
            r5 = r21.charAt(r22);
            r16 = 58;
            r0 = r16;
            if (r5 == r0) goto L_0x01df;
        L_0x01db:
            r16 = 0;
            goto L_0x0009;
        L_0x01df:
            r16 = 8;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x01f3:
            goto L_0x0001;
        L_0x01f5:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x020b;
        L_0x01fd:
            r16 = 21;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            goto L_0x0001;
        L_0x020b:
            r5 = r21.charAt(r22);
            r16 = 47;
            r0 = r16;
            if (r5 != r0) goto L_0x022b;
        L_0x0215:
            r16 = 22;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x0229:
            goto L_0x0001;
        L_0x022b:
            r16 = 21;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            goto L_0x0001;
        L_0x0239:
            r16 = 23;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x024d:
            goto L_0x0001;
        L_0x024f:
            r16 = 24;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x0263:
            goto L_0x0001;
        L_0x0265:
            r16 = 25;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x0279:
            goto L_0x0001;
        L_0x027b:
            r16 = 26;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x028f:
            goto L_0x0001;
        L_0x0291:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x029d;
        L_0x0299:
            r16 = 0;
            goto L_0x0009;
        L_0x029d:
            r5 = r21.charAt(r22);
            r16 = 61;
            r0 = r16;
            if (r5 == r0) goto L_0x02ab;
        L_0x02a7:
            r16 = 0;
            goto L_0x0009;
        L_0x02ab:
            r16 = 27;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x02bf:
            goto L_0x0001;
        L_0x02c1:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x02d7;
        L_0x02c9:
            r16 = 28;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            goto L_0x0001;
        L_0x02d7:
            r5 = r21.charAt(r22);
            r16 = 61;
            r0 = r16;
            if (r5 != r0) goto L_0x02f7;
        L_0x02e1:
            r16 = 29;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x02f5:
            goto L_0x0001;
        L_0x02f7:
            r16 = 28;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            goto L_0x0001;
        L_0x0305:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x031b;
        L_0x030d:
            r16 = 30;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            goto L_0x0001;
        L_0x031b:
            r5 = r21.charAt(r22);
            r16 = 61;
            r0 = r16;
            if (r5 != r0) goto L_0x033b;
        L_0x0325:
            r16 = 31;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x0339:
            goto L_0x0001;
        L_0x033b:
            r16 = 30;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            goto L_0x0001;
        L_0x0349:
            r14 = r5;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0356;
        L_0x0352:
            r16 = 0;
            goto L_0x0009;
        L_0x0356:
            r5 = r21.charAt(r22);
            r10 = r22;
        L_0x035c:
            if (r5 != r14) goto L_0x038f;
        L_0x035e:
            r9 = r22 - r10;
            r16 = 46;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r16 = r10 + r9;
            r0 = r21;
            r1 = r16;
            r16 = r0.substring(r10, r1);
            r0 = r19;
            r1 = r16;
            r16 = r0.addSymbol(r1);
            r0 = r20;
            r1 = r16;
            r0.addToken(r1);
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x038d:
            goto L_0x0001;
        L_0x038f:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x039b;
        L_0x0397:
            r16 = 0;
            goto L_0x0009;
        L_0x039b:
            r5 = r21.charAt(r22);
            goto L_0x035c;
        L_0x03a0:
            r16 = 47;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r0 = r18;
            r1 = r20;
            r2 = r21;
            r3 = r23;
            r4 = r22;
            r22 = r0.scanNumber(r1, r2, r3, r4);
            goto L_0x0001;
        L_0x03bc:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x03c8;
        L_0x03c4:
            r16 = 0;
            goto L_0x0009;
        L_0x03c8:
            r12 = r22;
            r0 = r18;
            r1 = r21;
            r2 = r23;
            r3 = r22;
            r22 = r0.scanNCName(r1, r2, r3);
            r0 = r22;
            if (r0 != r12) goto L_0x03de;
        L_0x03da:
            r16 = 0;
            goto L_0x0009;
        L_0x03de:
            r0 = r22;
            r1 = r23;
            if (r0 >= r1) goto L_0x0418;
        L_0x03e4:
            r5 = r21.charAt(r22);
        L_0x03e8:
            r0 = r21;
            r1 = r22;
            r16 = r0.substring(r12, r1);
            r0 = r19;
            r1 = r16;
            r11 = r0.addSymbol(r1);
            r16 = 58;
            r0 = r16;
            if (r5 == r0) goto L_0x041a;
        L_0x03fe:
            r13 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        L_0x0400:
            r16 = 48;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r0 = r20;
            r0.addToken(r13);
            r0 = r20;
            r0.addToken(r11);
            goto L_0x0001;
        L_0x0418:
            r5 = -1;
            goto L_0x03e8;
        L_0x041a:
            r13 = r11;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0427;
        L_0x0423:
            r16 = 0;
            goto L_0x0009;
        L_0x0427:
            r12 = r22;
            r0 = r18;
            r1 = r21;
            r2 = r23;
            r3 = r22;
            r22 = r0.scanNCName(r1, r2, r3);
            r0 = r22;
            if (r0 != r12) goto L_0x043d;
        L_0x0439:
            r16 = 0;
            goto L_0x0009;
        L_0x043d:
            r0 = r22;
            r1 = r23;
            if (r0 >= r1) goto L_0x0458;
        L_0x0443:
            r5 = r21.charAt(r22);
        L_0x0447:
            r0 = r21;
            r1 = r22;
            r16 = r0.substring(r12, r1);
            r0 = r19;
            r1 = r16;
            r11 = r0.addSymbol(r1);
            goto L_0x0400;
        L_0x0458:
            r5 = -1;
            goto L_0x0447;
        L_0x045a:
            if (r15 == 0) goto L_0x0472;
        L_0x045c:
            r16 = 20;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
        L_0x0468:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x0470:
            goto L_0x0001;
        L_0x0472:
            r16 = 9;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            goto L_0x0468;
        L_0x047f:
            r12 = r22;
            r0 = r18;
            r1 = r21;
            r2 = r23;
            r3 = r22;
            r22 = r0.scanNCName(r1, r2, r3);
            r0 = r22;
            if (r0 != r12) goto L_0x0495;
        L_0x0491:
            r16 = 0;
            goto L_0x0009;
        L_0x0495:
            r0 = r22;
            r1 = r23;
            if (r0 >= r1) goto L_0x04c5;
        L_0x049b:
            r5 = r21.charAt(r22);
        L_0x049f:
            r0 = r21;
            r1 = r22;
            r16 = r0.substring(r12, r1);
            r0 = r19;
            r1 = r16;
            r11 = r0.addSymbol(r1);
            r8 = 0;
            r7 = 0;
            r13 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
            r16 = 58;
            r0 = r16;
            if (r5 != r0) goto L_0x04de;
        L_0x04b9:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x04c7;
        L_0x04c1:
            r16 = 0;
            goto L_0x0009;
        L_0x04c5:
            r5 = -1;
            goto L_0x049f;
        L_0x04c7:
            r5 = r21.charAt(r22);
            r16 = 42;
            r0 = r16;
            if (r5 != r0) goto L_0x0510;
        L_0x04d1:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 >= r1) goto L_0x04dd;
        L_0x04d9:
            r5 = r21.charAt(r22);
        L_0x04dd:
            r8 = 1;
        L_0x04de:
            r16 = 32;
            r0 = r16;
            if (r5 == r0) goto L_0x0558;
        L_0x04e4:
            r16 = 10;
            r0 = r16;
            if (r5 == r0) goto L_0x0558;
        L_0x04ea:
            r16 = 9;
            r0 = r16;
            if (r5 == r0) goto L_0x0558;
        L_0x04f0:
            r16 = 13;
            r0 = r16;
            if (r5 == r0) goto L_0x0558;
        L_0x04f6:
            if (r15 == 0) goto L_0x05ab;
        L_0x04f8:
            r16 = fAndSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x0566;
        L_0x04fe:
            r16 = 16;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
        L_0x050a:
            if (r8 == 0) goto L_0x05a5;
        L_0x050c:
            r16 = 0;
            goto L_0x0009;
        L_0x0510:
            r16 = 58;
            r0 = r16;
            if (r5 != r0) goto L_0x0524;
        L_0x0516:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 >= r1) goto L_0x0522;
        L_0x051e:
            r5 = r21.charAt(r22);
        L_0x0522:
            r7 = 1;
            goto L_0x04de;
        L_0x0524:
            r13 = r11;
            r12 = r22;
            r0 = r18;
            r1 = r21;
            r2 = r23;
            r3 = r22;
            r22 = r0.scanNCName(r1, r2, r3);
            r0 = r22;
            if (r0 != r12) goto L_0x053b;
        L_0x0537:
            r16 = 0;
            goto L_0x0009;
        L_0x053b:
            r0 = r22;
            r1 = r23;
            if (r0 >= r1) goto L_0x0556;
        L_0x0541:
            r5 = r21.charAt(r22);
        L_0x0545:
            r0 = r21;
            r1 = r22;
            r16 = r0.substring(r12, r1);
            r0 = r19;
            r1 = r16;
            r11 = r0.addSymbol(r1);
            goto L_0x04de;
        L_0x0556:
            r5 = -1;
            goto L_0x0545;
        L_0x0558:
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 == r1) goto L_0x04f6;
        L_0x0560:
            r5 = r21.charAt(r22);
            goto L_0x04de;
        L_0x0566:
            r16 = fOrSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x0579;
        L_0x056c:
            r16 = 17;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            goto L_0x050a;
        L_0x0579:
            r16 = fModSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x058d;
        L_0x057f:
            r16 = 18;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            goto L_0x050a;
        L_0x058d:
            r16 = fDivSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x05a1;
        L_0x0593:
            r16 = 19;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            goto L_0x050a;
        L_0x05a1:
            r16 = 0;
            goto L_0x0009;
        L_0x05a5:
            if (r7 == 0) goto L_0x0001;
        L_0x05a7:
            r16 = 0;
            goto L_0x0009;
        L_0x05ab:
            r16 = 40;
            r0 = r16;
            if (r5 != r0) goto L_0x0628;
        L_0x05b1:
            if (r8 != 0) goto L_0x0628;
        L_0x05b3:
            if (r7 != 0) goto L_0x0628;
        L_0x05b5:
            r16 = fCommentSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x05dc;
        L_0x05bb:
            r16 = 12;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
        L_0x05c6:
            r16 = 0;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x05da:
            goto L_0x0001;
        L_0x05dc:
            r16 = fTextSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x05ee;
        L_0x05e2:
            r16 = 13;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x05c6;
        L_0x05ee:
            r16 = fPISymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x0600;
        L_0x05f4:
            r16 = 14;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x05c6;
        L_0x0600:
            r16 = fNodeSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x0612;
        L_0x0606:
            r16 = 15;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x05c6;
        L_0x0612:
            r16 = 32;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r0 = r20;
            r0.addToken(r13);
            r0 = r20;
            r0.addToken(r11);
            goto L_0x05c6;
        L_0x0628:
            if (r7 != 0) goto L_0x064a;
        L_0x062a:
            r16 = 58;
            r0 = r16;
            if (r5 != r0) goto L_0x075d;
        L_0x0630:
            r16 = r22 + 1;
            r0 = r16;
            r1 = r23;
            if (r0 >= r1) goto L_0x075d;
        L_0x0638:
            r16 = r22 + 1;
            r0 = r21;
            r1 = r16;
            r16 = r0.charAt(r1);
            r17 = 58;
            r0 = r16;
            r1 = r17;
            if (r0 != r1) goto L_0x075d;
        L_0x064a:
            r16 = fAncestorSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x0661;
        L_0x0650:
            r16 = 33;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
        L_0x065b:
            if (r8 == 0) goto L_0x0743;
        L_0x065d:
            r16 = 0;
            goto L_0x0009;
        L_0x0661:
            r16 = fAncestorOrSelfSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x0673;
        L_0x0667:
            r16 = 34;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x0673:
            r16 = fAttributeSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x0685;
        L_0x0679:
            r16 = 35;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x0685:
            r16 = fChildSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x0697;
        L_0x068b:
            r16 = 36;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x0697:
            r16 = fDescendantSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x06a9;
        L_0x069d:
            r16 = 37;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x06a9:
            r16 = fDescendantOrSelfSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x06bb;
        L_0x06af:
            r16 = 38;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x06bb:
            r16 = fFollowingSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x06cd;
        L_0x06c1:
            r16 = 39;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x06cd:
            r16 = fFollowingSiblingSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x06e0;
        L_0x06d3:
            r16 = 40;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x06e0:
            r16 = fNamespaceSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x06f3;
        L_0x06e6:
            r16 = 41;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x06f3:
            r16 = fParentSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x0706;
        L_0x06f9:
            r16 = 42;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x0706:
            r16 = fPrecedingSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x0719;
        L_0x070c:
            r16 = 43;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x0719:
            r16 = fPrecedingSiblingSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x072c;
        L_0x071f:
            r16 = 44;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x072c:
            r16 = fSelfSymbol;
            r0 = r16;
            if (r11 != r0) goto L_0x073f;
        L_0x0732:
            r16 = 45;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            goto L_0x065b;
        L_0x073f:
            r16 = 0;
            goto L_0x0009;
        L_0x0743:
            r16 = 8;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 0;
            if (r7 != 0) goto L_0x0001;
        L_0x0751:
            r22 = r22 + 1;
            r22 = r22 + 1;
            r0 = r22;
            r1 = r23;
            if (r0 != r1) goto L_0x0001;
        L_0x075b:
            goto L_0x0001;
        L_0x075d:
            if (r8 == 0) goto L_0x0772;
        L_0x075f:
            r16 = 10;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r0 = r20;
            r0.addToken(r11);
            goto L_0x0001;
        L_0x0772:
            r16 = 11;
            r0 = r18;
            r1 = r20;
            r2 = r16;
            r0.addToken(r1, r2);
            r15 = 1;
            r0 = r20;
            r0.addToken(r13);
            r0 = r20;
            r0.addToken(r11);
            goto L_0x0001;
            */
            throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xpath.XPath.Scanner.scanExpr(mf.org.apache.xerces.util.SymbolTable, mf.org.apache.xerces.impl.xpath.XPath$Tokens, java.lang.String, int, int):boolean");
        }

        int scanNCName(String data, int endOffset, int currentOffset) {
            byte chartype;
            int ch = data.charAt(currentOffset);
            if (ch < TransportMediator.FLAG_KEY_MEDIA_NEXT) {
                chartype = fASCIICharMap[ch];
                if (!(chartype == CHARTYPE_LETTER || chartype == CHARTYPE_UNDERSCORE)) {
                    return currentOffset;
                }
            } else if (!XMLChar.isNameStart(ch)) {
                return currentOffset;
            }
            while (true) {
                currentOffset++;
                if (currentOffset < endOffset) {
                    ch = data.charAt(currentOffset);
                    if (ch < TransportMediator.FLAG_KEY_MEDIA_NEXT) {
                        chartype = fASCIICharMap[ch];
                        if (!(chartype == CHARTYPE_LETTER || chartype == 14 || chartype == 12 || chartype == 11 || chartype == CHARTYPE_UNDERSCORE)) {
                            break;
                        }
                    } else if (!XMLChar.isName(ch)) {
                        break;
                    }
                } else {
                    break;
                }
            }
            return currentOffset;
        }

        private int scanNumber(Tokens tokens, String data, int endOffset, int currentOffset) {
            int ch = data.charAt(currentOffset);
            int whole = 0;
            int part = 0;
            while (ch >= 48 && ch <= 57) {
                whole = (whole * 10) + (ch - 48);
                currentOffset++;
                if (currentOffset == endOffset) {
                    break;
                }
                ch = data.charAt(currentOffset);
            }
            if (ch == 46) {
                currentOffset++;
                if (currentOffset < endOffset) {
                    ch = data.charAt(currentOffset);
                    while (ch >= 48 && ch <= 57) {
                        part = (part * 10) + (ch - 48);
                        currentOffset++;
                        if (currentOffset == endOffset) {
                            break;
                        }
                        ch = data.charAt(currentOffset);
                    }
                    if (part != 0) {
                        throw new RuntimeException("find a solution!");
                    }
                }
            }
            tokens.addToken(whole);
            tokens.addToken(part);
            return currentOffset;
        }

        protected void addToken(Tokens tokens, int token) throws XPathException {
            tokens.addToken(token);
        }
    }

    public static class Step implements Cloneable {
        public final Axis axis;
        public final NodeTest nodeTest;

        public Step(Axis axis, NodeTest nodeTest) {
            this.axis = axis;
            this.nodeTest = nodeTest;
        }

        protected Step(Step step) {
            this.axis = (Axis) step.axis.clone();
            this.nodeTest = (NodeTest) step.nodeTest.clone();
        }

        public String toString() {
            if (this.axis.type == (short) 3) {
                return ".";
            }
            if (this.axis.type == (short) 2) {
                return "@" + this.nodeTest.toString();
            }
            if (this.axis.type == (short) 1) {
                return this.nodeTest.toString();
            }
            if (this.axis.type == (short) 4) {
                return "//";
            }
            return "??? (" + this.axis.type + ')';
        }

        public Object clone() {
            return new Step(this);
        }
    }

    private static final class Tokens {
        static final boolean DUMP_TOKENS = false;
        public static final int EXPRTOKEN_ATSIGN = 6;
        public static final int EXPRTOKEN_AXISNAME_ANCESTOR = 33;
        public static final int EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF = 34;
        public static final int EXPRTOKEN_AXISNAME_ATTRIBUTE = 35;
        public static final int EXPRTOKEN_AXISNAME_CHILD = 36;
        public static final int EXPRTOKEN_AXISNAME_DESCENDANT = 37;
        public static final int EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF = 38;
        public static final int EXPRTOKEN_AXISNAME_FOLLOWING = 39;
        public static final int EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING = 40;
        public static final int EXPRTOKEN_AXISNAME_NAMESPACE = 41;
        public static final int EXPRTOKEN_AXISNAME_PARENT = 42;
        public static final int EXPRTOKEN_AXISNAME_PRECEDING = 43;
        public static final int EXPRTOKEN_AXISNAME_PRECEDING_SIBLING = 44;
        public static final int EXPRTOKEN_AXISNAME_SELF = 45;
        public static final int EXPRTOKEN_CLOSE_BRACKET = 3;
        public static final int EXPRTOKEN_CLOSE_PAREN = 1;
        public static final int EXPRTOKEN_COMMA = 7;
        public static final int EXPRTOKEN_DOUBLE_COLON = 8;
        public static final int EXPRTOKEN_DOUBLE_PERIOD = 5;
        public static final int EXPRTOKEN_FUNCTION_NAME = 32;
        public static final int EXPRTOKEN_LITERAL = 46;
        public static final int EXPRTOKEN_NAMETEST_ANY = 9;
        public static final int EXPRTOKEN_NAMETEST_NAMESPACE = 10;
        public static final int EXPRTOKEN_NAMETEST_QNAME = 11;
        public static final int EXPRTOKEN_NODETYPE_COMMENT = 12;
        public static final int EXPRTOKEN_NODETYPE_NODE = 15;
        public static final int EXPRTOKEN_NODETYPE_PI = 14;
        public static final int EXPRTOKEN_NODETYPE_TEXT = 13;
        public static final int EXPRTOKEN_NUMBER = 47;
        public static final int EXPRTOKEN_OPEN_BRACKET = 2;
        public static final int EXPRTOKEN_OPEN_PAREN = 0;
        public static final int EXPRTOKEN_OPERATOR_AND = 16;
        public static final int EXPRTOKEN_OPERATOR_DIV = 19;
        public static final int EXPRTOKEN_OPERATOR_DOUBLE_SLASH = 22;
        public static final int EXPRTOKEN_OPERATOR_EQUAL = 26;
        public static final int EXPRTOKEN_OPERATOR_GREATER = 30;
        public static final int EXPRTOKEN_OPERATOR_GREATER_EQUAL = 31;
        public static final int EXPRTOKEN_OPERATOR_LESS = 28;
        public static final int EXPRTOKEN_OPERATOR_LESS_EQUAL = 29;
        public static final int EXPRTOKEN_OPERATOR_MINUS = 25;
        public static final int EXPRTOKEN_OPERATOR_MOD = 18;
        public static final int EXPRTOKEN_OPERATOR_MULT = 20;
        public static final int EXPRTOKEN_OPERATOR_NOT_EQUAL = 27;
        public static final int EXPRTOKEN_OPERATOR_OR = 17;
        public static final int EXPRTOKEN_OPERATOR_PLUS = 24;
        public static final int EXPRTOKEN_OPERATOR_SLASH = 21;
        public static final int EXPRTOKEN_OPERATOR_UNION = 23;
        public static final int EXPRTOKEN_PERIOD = 4;
        public static final int EXPRTOKEN_VARIABLE_REFERENCE = 48;
        private static final int INITIAL_TOKEN_COUNT = 256;
        private static final String[] fgTokenNames;
        private int fCurrentTokenIndex;
        private Hashtable fSymbolMapping;
        private SymbolTable fSymbolTable;
        private int fTokenCount;
        private Hashtable fTokenNames;
        private int[] fTokens;

        static {
            fgTokenNames = new String[]{"EXPRTOKEN_OPEN_PAREN", "EXPRTOKEN_CLOSE_PAREN", "EXPRTOKEN_OPEN_BRACKET", "EXPRTOKEN_CLOSE_BRACKET", "EXPRTOKEN_PERIOD", "EXPRTOKEN_DOUBLE_PERIOD", "EXPRTOKEN_ATSIGN", "EXPRTOKEN_COMMA", "EXPRTOKEN_DOUBLE_COLON", "EXPRTOKEN_NAMETEST_ANY", "EXPRTOKEN_NAMETEST_NAMESPACE", "EXPRTOKEN_NAMETEST_QNAME", "EXPRTOKEN_NODETYPE_COMMENT", "EXPRTOKEN_NODETYPE_TEXT", "EXPRTOKEN_NODETYPE_PI", "EXPRTOKEN_NODETYPE_NODE", "EXPRTOKEN_OPERATOR_AND", "EXPRTOKEN_OPERATOR_OR", "EXPRTOKEN_OPERATOR_MOD", "EXPRTOKEN_OPERATOR_DIV", "EXPRTOKEN_OPERATOR_MULT", "EXPRTOKEN_OPERATOR_SLASH", "EXPRTOKEN_OPERATOR_DOUBLE_SLASH", "EXPRTOKEN_OPERATOR_UNION", "EXPRTOKEN_OPERATOR_PLUS", "EXPRTOKEN_OPERATOR_MINUS", "EXPRTOKEN_OPERATOR_EQUAL", "EXPRTOKEN_OPERATOR_NOT_EQUAL", "EXPRTOKEN_OPERATOR_LESS", "EXPRTOKEN_OPERATOR_LESS_EQUAL", "EXPRTOKEN_OPERATOR_GREATER", "EXPRTOKEN_OPERATOR_GREATER_EQUAL", "EXPRTOKEN_FUNCTION_NAME", "EXPRTOKEN_AXISNAME_ANCESTOR", "EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF", "EXPRTOKEN_AXISNAME_ATTRIBUTE", "EXPRTOKEN_AXISNAME_CHILD", "EXPRTOKEN_AXISNAME_DESCENDANT", "EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF", "EXPRTOKEN_AXISNAME_FOLLOWING", "EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING", "EXPRTOKEN_AXISNAME_NAMESPACE", "EXPRTOKEN_AXISNAME_PARENT", "EXPRTOKEN_AXISNAME_PRECEDING", "EXPRTOKEN_AXISNAME_PRECEDING_SIBLING", "EXPRTOKEN_AXISNAME_SELF", "EXPRTOKEN_LITERAL", "EXPRTOKEN_NUMBER", "EXPRTOKEN_VARIABLE_REFERENCE"};
        }

        public Tokens(SymbolTable symbolTable) {
            this.fTokens = new int[INITIAL_TOKEN_COUNT];
            this.fTokenCount = EXPRTOKEN_OPEN_PAREN;
            this.fSymbolMapping = new Hashtable();
            this.fTokenNames = new Hashtable();
            this.fSymbolTable = symbolTable;
            String[] symbols = new String[EXPRTOKEN_NODETYPE_TEXT];
            symbols[EXPRTOKEN_OPEN_PAREN] = "ancestor";
            symbols[EXPRTOKEN_CLOSE_PAREN] = "ancestor-or-self";
            symbols[EXPRTOKEN_OPEN_BRACKET] = "attribute";
            symbols[EXPRTOKEN_CLOSE_BRACKET] = "child";
            symbols[EXPRTOKEN_PERIOD] = "descendant";
            symbols[EXPRTOKEN_DOUBLE_PERIOD] = "descendant-or-self";
            symbols[EXPRTOKEN_ATSIGN] = "following";
            symbols[EXPRTOKEN_COMMA] = "following-sibling";
            symbols[EXPRTOKEN_DOUBLE_COLON] = "namespace";
            symbols[EXPRTOKEN_NAMETEST_ANY] = "parent";
            symbols[EXPRTOKEN_NAMETEST_NAMESPACE] = "preceding";
            symbols[EXPRTOKEN_NAMETEST_QNAME] = "preceding-sibling";
            symbols[EXPRTOKEN_NODETYPE_COMMENT] = "self";
            for (int i = EXPRTOKEN_OPEN_PAREN; i < symbols.length; i += EXPRTOKEN_CLOSE_PAREN) {
                this.fSymbolMapping.put(this.fSymbolTable.addSymbol(symbols[i]), new Integer(i));
            }
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPEN_PAREN), "EXPRTOKEN_OPEN_PAREN");
            this.fTokenNames.put(new Integer(EXPRTOKEN_CLOSE_PAREN), "EXPRTOKEN_CLOSE_PAREN");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPEN_BRACKET), "EXPRTOKEN_OPEN_BRACKET");
            this.fTokenNames.put(new Integer(EXPRTOKEN_CLOSE_BRACKET), "EXPRTOKEN_CLOSE_BRACKET");
            this.fTokenNames.put(new Integer(EXPRTOKEN_PERIOD), "EXPRTOKEN_PERIOD");
            this.fTokenNames.put(new Integer(EXPRTOKEN_DOUBLE_PERIOD), "EXPRTOKEN_DOUBLE_PERIOD");
            this.fTokenNames.put(new Integer(EXPRTOKEN_ATSIGN), "EXPRTOKEN_ATSIGN");
            this.fTokenNames.put(new Integer(EXPRTOKEN_COMMA), "EXPRTOKEN_COMMA");
            this.fTokenNames.put(new Integer(EXPRTOKEN_DOUBLE_COLON), "EXPRTOKEN_DOUBLE_COLON");
            this.fTokenNames.put(new Integer(EXPRTOKEN_NAMETEST_ANY), "EXPRTOKEN_NAMETEST_ANY");
            this.fTokenNames.put(new Integer(EXPRTOKEN_NAMETEST_NAMESPACE), "EXPRTOKEN_NAMETEST_NAMESPACE");
            this.fTokenNames.put(new Integer(EXPRTOKEN_NAMETEST_QNAME), "EXPRTOKEN_NAMETEST_QNAME");
            this.fTokenNames.put(new Integer(EXPRTOKEN_NODETYPE_COMMENT), "EXPRTOKEN_NODETYPE_COMMENT");
            this.fTokenNames.put(new Integer(EXPRTOKEN_NODETYPE_TEXT), "EXPRTOKEN_NODETYPE_TEXT");
            this.fTokenNames.put(new Integer(EXPRTOKEN_NODETYPE_PI), "EXPRTOKEN_NODETYPE_PI");
            this.fTokenNames.put(new Integer(EXPRTOKEN_NODETYPE_NODE), "EXPRTOKEN_NODETYPE_NODE");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_AND), "EXPRTOKEN_OPERATOR_AND");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_OR), "EXPRTOKEN_OPERATOR_OR");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_MOD), "EXPRTOKEN_OPERATOR_MOD");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_DIV), "EXPRTOKEN_OPERATOR_DIV");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_MULT), "EXPRTOKEN_OPERATOR_MULT");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_SLASH), "EXPRTOKEN_OPERATOR_SLASH");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_DOUBLE_SLASH), "EXPRTOKEN_OPERATOR_DOUBLE_SLASH");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_UNION), "EXPRTOKEN_OPERATOR_UNION");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_PLUS), "EXPRTOKEN_OPERATOR_PLUS");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_MINUS), "EXPRTOKEN_OPERATOR_MINUS");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_EQUAL), "EXPRTOKEN_OPERATOR_EQUAL");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_NOT_EQUAL), "EXPRTOKEN_OPERATOR_NOT_EQUAL");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_LESS), "EXPRTOKEN_OPERATOR_LESS");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_LESS_EQUAL), "EXPRTOKEN_OPERATOR_LESS_EQUAL");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_GREATER), "EXPRTOKEN_OPERATOR_GREATER");
            this.fTokenNames.put(new Integer(EXPRTOKEN_OPERATOR_GREATER_EQUAL), "EXPRTOKEN_OPERATOR_GREATER_EQUAL");
            this.fTokenNames.put(new Integer(EXPRTOKEN_FUNCTION_NAME), "EXPRTOKEN_FUNCTION_NAME");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_ANCESTOR), "EXPRTOKEN_AXISNAME_ANCESTOR");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF), "EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_ATTRIBUTE), "EXPRTOKEN_AXISNAME_ATTRIBUTE");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_CHILD), "EXPRTOKEN_AXISNAME_CHILD");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_DESCENDANT), "EXPRTOKEN_AXISNAME_DESCENDANT");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF), "EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_FOLLOWING), "EXPRTOKEN_AXISNAME_FOLLOWING");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING), "EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_NAMESPACE), "EXPRTOKEN_AXISNAME_NAMESPACE");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_PARENT), "EXPRTOKEN_AXISNAME_PARENT");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_PRECEDING), "EXPRTOKEN_AXISNAME_PRECEDING");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_PRECEDING_SIBLING), "EXPRTOKEN_AXISNAME_PRECEDING_SIBLING");
            this.fTokenNames.put(new Integer(EXPRTOKEN_AXISNAME_SELF), "EXPRTOKEN_AXISNAME_SELF");
            this.fTokenNames.put(new Integer(EXPRTOKEN_LITERAL), "EXPRTOKEN_LITERAL");
            this.fTokenNames.put(new Integer(EXPRTOKEN_NUMBER), "EXPRTOKEN_NUMBER");
            this.fTokenNames.put(new Integer(EXPRTOKEN_VARIABLE_REFERENCE), "EXPRTOKEN_VARIABLE_REFERENCE");
        }

        public String getTokenString(int token) {
            return (String) this.fTokenNames.get(new Integer(token));
        }

        public void addToken(String tokenStr) {
            Integer tokenInt = (Integer) this.fTokenNames.get(tokenStr);
            if (tokenInt == null) {
                tokenInt = new Integer(this.fTokenNames.size());
                this.fTokenNames.put(tokenInt, tokenStr);
            }
            addToken(tokenInt.intValue());
        }

        public void addToken(int token) {
            try {
                this.fTokens[this.fTokenCount] = token;
            } catch (ArrayIndexOutOfBoundsException e) {
                int[] oldList = this.fTokens;
                this.fTokens = new int[(this.fTokenCount << EXPRTOKEN_CLOSE_PAREN)];
                System.arraycopy(oldList, EXPRTOKEN_OPEN_PAREN, this.fTokens, EXPRTOKEN_OPEN_PAREN, this.fTokenCount);
                this.fTokens[this.fTokenCount] = token;
            }
            this.fTokenCount += EXPRTOKEN_CLOSE_PAREN;
        }

        public void rewind() {
            this.fCurrentTokenIndex = EXPRTOKEN_OPEN_PAREN;
        }

        public boolean hasMore() {
            return this.fCurrentTokenIndex < this.fTokenCount ? true : DUMP_TOKENS;
        }

        public int nextToken() throws XPathException {
            if (this.fCurrentTokenIndex == this.fTokenCount) {
                throw new XPathException("c-general-xpath");
            }
            int[] iArr = this.fTokens;
            int i = this.fCurrentTokenIndex;
            this.fCurrentTokenIndex = i + EXPRTOKEN_CLOSE_PAREN;
            return iArr[i];
        }

        public int peekToken() throws XPathException {
            if (this.fCurrentTokenIndex != this.fTokenCount) {
                return this.fTokens[this.fCurrentTokenIndex];
            }
            throw new XPathException("c-general-xpath");
        }

        public String nextTokenAsString() throws XPathException {
            String s = getTokenString(nextToken());
            if (s != null) {
                return s;
            }
            throw new XPathException("c-general-xpath");
        }

        public void dumpTokens() {
            int i = EXPRTOKEN_OPEN_PAREN;
            while (i < this.fTokenCount) {
                switch (this.fTokens[i]) {
                    case EXPRTOKEN_OPEN_PAREN /*0*/:
                        System.out.print("<OPEN_PAREN/>");
                        break;
                    case EXPRTOKEN_CLOSE_PAREN /*1*/:
                        System.out.print("<CLOSE_PAREN/>");
                        break;
                    case EXPRTOKEN_OPEN_BRACKET /*2*/:
                        System.out.print("<OPEN_BRACKET/>");
                        break;
                    case EXPRTOKEN_CLOSE_BRACKET /*3*/:
                        System.out.print("<CLOSE_BRACKET/>");
                        break;
                    case EXPRTOKEN_PERIOD /*4*/:
                        System.out.print("<PERIOD/>");
                        break;
                    case EXPRTOKEN_DOUBLE_PERIOD /*5*/:
                        System.out.print("<DOUBLE_PERIOD/>");
                        break;
                    case EXPRTOKEN_ATSIGN /*6*/:
                        System.out.print("<ATSIGN/>");
                        break;
                    case EXPRTOKEN_COMMA /*7*/:
                        System.out.print("<COMMA/>");
                        break;
                    case EXPRTOKEN_DOUBLE_COLON /*8*/:
                        System.out.print("<DOUBLE_COLON/>");
                        break;
                    case EXPRTOKEN_NAMETEST_ANY /*9*/:
                        System.out.print("<NAMETEST_ANY/>");
                        break;
                    case EXPRTOKEN_NAMETEST_NAMESPACE /*10*/:
                        System.out.print("<NAMETEST_NAMESPACE");
                        i += EXPRTOKEN_CLOSE_PAREN;
                        System.out.print(" prefix=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    case EXPRTOKEN_NAMETEST_QNAME /*11*/:
                        System.out.print("<NAMETEST_QNAME");
                        i += EXPRTOKEN_CLOSE_PAREN;
                        if (this.fTokens[i] != -1) {
                            System.out.print(" prefix=\"" + getTokenString(this.fTokens[i]) + "\"");
                        }
                        i += EXPRTOKEN_CLOSE_PAREN;
                        System.out.print(" localpart=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    case EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                        System.out.print("<NODETYPE_COMMENT/>");
                        break;
                    case EXPRTOKEN_NODETYPE_TEXT /*13*/:
                        System.out.print("<NODETYPE_TEXT/>");
                        break;
                    case EXPRTOKEN_NODETYPE_PI /*14*/:
                        System.out.print("<NODETYPE_PI/>");
                        break;
                    case EXPRTOKEN_NODETYPE_NODE /*15*/:
                        System.out.print("<NODETYPE_NODE/>");
                        break;
                    case EXPRTOKEN_OPERATOR_AND /*16*/:
                        System.out.print("<OPERATOR_AND/>");
                        break;
                    case EXPRTOKEN_OPERATOR_OR /*17*/:
                        System.out.print("<OPERATOR_OR/>");
                        break;
                    case EXPRTOKEN_OPERATOR_MOD /*18*/:
                        System.out.print("<OPERATOR_MOD/>");
                        break;
                    case EXPRTOKEN_OPERATOR_DIV /*19*/:
                        System.out.print("<OPERATOR_DIV/>");
                        break;
                    case EXPRTOKEN_OPERATOR_MULT /*20*/:
                        System.out.print("<OPERATOR_MULT/>");
                        break;
                    case EXPRTOKEN_OPERATOR_SLASH /*21*/:
                        System.out.print("<OPERATOR_SLASH/>");
                        if (i + EXPRTOKEN_CLOSE_PAREN >= this.fTokenCount) {
                            break;
                        }
                        System.out.println();
                        System.out.print("  ");
                        break;
                    case EXPRTOKEN_OPERATOR_DOUBLE_SLASH /*22*/:
                        System.out.print("<OPERATOR_DOUBLE_SLASH/>");
                        break;
                    case EXPRTOKEN_OPERATOR_UNION /*23*/:
                        System.out.print("<OPERATOR_UNION/>");
                        break;
                    case EXPRTOKEN_OPERATOR_PLUS /*24*/:
                        System.out.print("<OPERATOR_PLUS/>");
                        break;
                    case EXPRTOKEN_OPERATOR_MINUS /*25*/:
                        System.out.print("<OPERATOR_MINUS/>");
                        break;
                    case EXPRTOKEN_OPERATOR_EQUAL /*26*/:
                        System.out.print("<OPERATOR_EQUAL/>");
                        break;
                    case EXPRTOKEN_OPERATOR_NOT_EQUAL /*27*/:
                        System.out.print("<OPERATOR_NOT_EQUAL/>");
                        break;
                    case EXPRTOKEN_OPERATOR_LESS /*28*/:
                        System.out.print("<OPERATOR_LESS/>");
                        break;
                    case EXPRTOKEN_OPERATOR_LESS_EQUAL /*29*/:
                        System.out.print("<OPERATOR_LESS_EQUAL/>");
                        break;
                    case EXPRTOKEN_OPERATOR_GREATER /*30*/:
                        System.out.print("<OPERATOR_GREATER/>");
                        break;
                    case EXPRTOKEN_OPERATOR_GREATER_EQUAL /*31*/:
                        System.out.print("<OPERATOR_GREATER_EQUAL/>");
                        break;
                    case EXPRTOKEN_FUNCTION_NAME /*32*/:
                        System.out.print("<FUNCTION_NAME");
                        i += EXPRTOKEN_CLOSE_PAREN;
                        if (this.fTokens[i] != -1) {
                            System.out.print(" prefix=\"" + getTokenString(this.fTokens[i]) + "\"");
                        }
                        i += EXPRTOKEN_CLOSE_PAREN;
                        System.out.print(" localpart=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    case EXPRTOKEN_AXISNAME_ANCESTOR /*33*/:
                        System.out.print("<AXISNAME_ANCESTOR/>");
                        break;
                    case EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF /*34*/:
                        System.out.print("<AXISNAME_ANCESTOR_OR_SELF/>");
                        break;
                    case EXPRTOKEN_AXISNAME_ATTRIBUTE /*35*/:
                        System.out.print("<AXISNAME_ATTRIBUTE/>");
                        break;
                    case EXPRTOKEN_AXISNAME_CHILD /*36*/:
                        System.out.print("<AXISNAME_CHILD/>");
                        break;
                    case EXPRTOKEN_AXISNAME_DESCENDANT /*37*/:
                        System.out.print("<AXISNAME_DESCENDANT/>");
                        break;
                    case EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF /*38*/:
                        System.out.print("<AXISNAME_DESCENDANT_OR_SELF/>");
                        break;
                    case EXPRTOKEN_AXISNAME_FOLLOWING /*39*/:
                        System.out.print("<AXISNAME_FOLLOWING/>");
                        break;
                    case EXPRTOKEN_AXISNAME_FOLLOWING_SIBLING /*40*/:
                        System.out.print("<AXISNAME_FOLLOWING_SIBLING/>");
                        break;
                    case EXPRTOKEN_AXISNAME_NAMESPACE /*41*/:
                        System.out.print("<AXISNAME_NAMESPACE/>");
                        break;
                    case EXPRTOKEN_AXISNAME_PARENT /*42*/:
                        System.out.print("<AXISNAME_PARENT/>");
                        break;
                    case EXPRTOKEN_AXISNAME_PRECEDING /*43*/:
                        System.out.print("<AXISNAME_PRECEDING/>");
                        break;
                    case EXPRTOKEN_AXISNAME_PRECEDING_SIBLING /*44*/:
                        System.out.print("<AXISNAME_PRECEDING_SIBLING/>");
                        break;
                    case EXPRTOKEN_AXISNAME_SELF /*45*/:
                        System.out.print("<AXISNAME_SELF/>");
                        break;
                    case EXPRTOKEN_LITERAL /*46*/:
                        System.out.print("<LITERAL");
                        i += EXPRTOKEN_CLOSE_PAREN;
                        System.out.print(" value=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    case EXPRTOKEN_NUMBER /*47*/:
                        System.out.print("<NUMBER");
                        i += EXPRTOKEN_CLOSE_PAREN;
                        System.out.print(" whole=\"" + getTokenString(this.fTokens[i]) + "\"");
                        i += EXPRTOKEN_CLOSE_PAREN;
                        System.out.print(" part=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    case EXPRTOKEN_VARIABLE_REFERENCE /*48*/:
                        System.out.print("<VARIABLE_REFERENCE");
                        i += EXPRTOKEN_CLOSE_PAREN;
                        if (this.fTokens[i] != -1) {
                            System.out.print(" prefix=\"" + getTokenString(this.fTokens[i]) + "\"");
                        }
                        i += EXPRTOKEN_CLOSE_PAREN;
                        System.out.print(" localpart=\"" + getTokenString(this.fTokens[i]) + "\"");
                        System.out.print("/>");
                        break;
                    default:
                        System.out.println("<???/>");
                        break;
                }
                i += EXPRTOKEN_CLOSE_PAREN;
            }
            System.out.println();
        }
    }

    /* renamed from: mf.org.apache.xerces.impl.xpath.XPath.1 */
    class C12671 extends Scanner {
        C12671(SymbolTable $anonymous0) {
            super($anonymous0);
        }

        protected void addToken(Tokens tokens, int token) throws XPathException {
            if (token == 6 || token == 11 || token == 21 || token == 4 || token == 9 || token == 10 || token == 22 || token == 23 || token == 36 || token == 35 || token == 8) {
                super.addToken(tokens, token);
                return;
            }
            throw new XPathException("c-general-xpath");
        }
    }

    public XPath(String xpath, SymbolTable symbolTable, NamespaceContext context) throws XPathException {
        this.fExpression = xpath;
        this.fSymbolTable = symbolTable;
        this.fLocationPaths = parseExpression(context);
    }

    public LocationPath[] getLocationPaths() {
        LocationPath[] ret = new LocationPath[this.fLocationPaths.length];
        for (int i = 0; i < this.fLocationPaths.length; i++) {
            ret[i] = (LocationPath) this.fLocationPaths[i].clone();
        }
        return ret;
    }

    public LocationPath getLocationPath() {
        return (LocationPath) this.fLocationPaths[0].clone();
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < this.fLocationPaths.length; i++) {
            if (i > 0) {
                buf.append('|');
            }
            buf.append(this.fLocationPaths[i].toString());
        }
        return buf.toString();
    }

    private static void check(boolean b) throws XPathException {
        if (!b) {
            throw new XPathException("c-general-xpath");
        }
    }

    private LocationPath buildLocationPath(Vector stepsVector) throws XPathException {
        int size = stepsVector.size();
        check(size != 0);
        Step[] steps = new Step[size];
        stepsVector.copyInto(steps);
        stepsVector.removeAllElements();
        return new LocationPath(steps);
    }

    private LocationPath[] parseExpression(NamespaceContext context) throws XPathException {
        Tokens xtokens = new Tokens(this.fSymbolTable);
        if (new C12671(this.fSymbolTable).scanExpr(this.fSymbolTable, xtokens, this.fExpression, 0, this.fExpression.length())) {
            Vector stepsVector = new Vector();
            ArrayList locationPathsVector = new ArrayList();
            boolean expectingStep = true;
            while (xtokens.hasMore()) {
                int token = xtokens.nextToken();
                switch (token) {
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        check(expectingStep);
                        expectingStep = false;
                        if (stepsVector.size() != 0) {
                            break;
                        }
                        stepsVector.addElement(new Step(new Axis((short) 3), new NodeTest((short) 3)));
                        if (xtokens.hasMore() && xtokens.peekToken() == 22) {
                            xtokens.nextToken();
                            stepsVector.addElement(new Step(new Axis((short) 4), new NodeTest((short) 3)));
                            expectingStep = true;
                            break;
                        }
                    case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                        check(expectingStep);
                        stepsVector.addElement(new Step(new Axis((short) 2), parseNodeTest(xtokens.nextToken(), xtokens, context)));
                        expectingStep = false;
                        break;
                    case ConnectionResult.INTERNAL_ERROR /*8*/:
                        throw new XPathException("c-general-xpath");
                    case ConnectionResult.SERVICE_INVALID /*9*/:
                    case MetaData.DEFAULT_MAX_ADS /*10*/:
                    case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                        check(expectingStep);
                        stepsVector.addElement(new Step(new Axis((short) 1), parseNodeTest(token, xtokens, context)));
                        expectingStep = false;
                        break;
                    case Tokens.EXPRTOKEN_OPERATOR_SLASH /*21*/:
                        check(!expectingStep);
                        expectingStep = true;
                        break;
                    case Tokens.EXPRTOKEN_OPERATOR_DOUBLE_SLASH /*22*/:
                        throw new XPathException("c-general-xpath");
                    case Tokens.EXPRTOKEN_OPERATOR_UNION /*23*/:
                        check(!expectingStep);
                        locationPathsVector.add(buildLocationPath(stepsVector));
                        expectingStep = true;
                        break;
                    case Tokens.EXPRTOKEN_AXISNAME_ATTRIBUTE /*35*/:
                        check(expectingStep);
                        if (xtokens.nextToken() == 8) {
                            stepsVector.addElement(new Step(new Axis((short) 2), parseNodeTest(xtokens.nextToken(), xtokens, context)));
                            expectingStep = false;
                            break;
                        }
                        throw new XPathException("c-general-xpath");
                    case Tokens.EXPRTOKEN_AXISNAME_CHILD /*36*/:
                        check(expectingStep);
                        if (xtokens.nextToken() == 8) {
                            stepsVector.addElement(new Step(new Axis((short) 1), parseNodeTest(xtokens.nextToken(), xtokens, context)));
                            expectingStep = false;
                            break;
                        }
                        throw new XPathException("c-general-xpath");
                    default:
                        throw new InternalError();
                }
            }
            check(!expectingStep);
            locationPathsVector.add(buildLocationPath(stepsVector));
            return (LocationPath[]) locationPathsVector.toArray(new LocationPath[locationPathsVector.size()]);
        }
        throw new XPathException("c-general-xpath");
    }

    private NodeTest parseNodeTest(int typeToken, Tokens xtokens, NamespaceContext context) throws XPathException {
        switch (typeToken) {
            case ConnectionResult.SERVICE_INVALID /*9*/:
                return new NodeTest((short) 2);
            case MetaData.DEFAULT_MAX_ADS /*10*/:
            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                String prefix = xtokens.nextTokenAsString();
                String uri = null;
                if (!(context == null || prefix == XMLSymbols.EMPTY_STRING)) {
                    uri = context.getURI(prefix);
                }
                if (prefix != XMLSymbols.EMPTY_STRING && context != null && uri == null) {
                    throw new XPathException("c-general-xpath-ns");
                } else if (typeToken == 10) {
                    return new NodeTest(prefix, uri);
                } else {
                    String rawname;
                    String localpart = xtokens.nextTokenAsString();
                    if (prefix != XMLSymbols.EMPTY_STRING) {
                        rawname = this.fSymbolTable.addSymbol(new StringBuilder(String.valueOf(prefix)).append(':').append(localpart).toString());
                    } else {
                        rawname = localpart;
                    }
                    return new NodeTest(new QName(prefix, localpart, rawname, uri));
                }
            default:
                throw new XPathException("c-general-xpath");
        }
    }

    public static void main(String[] argv) throws Exception {
        for (String expression : argv) {
            System.out.println("# XPath expression: \"" + expression + '\"');
            try {
                System.out.println("expanded xpath: \"" + new XPath(expression, new SymbolTable(), null).toString() + '\"');
            } catch (XPathException e) {
                System.out.println("error: " + e.getMessage());
            }
        }
    }
}
