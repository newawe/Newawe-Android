package mf.org.apache.xerces.impl;

import java.io.IOException;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;

public class XML11DocumentScannerImpl extends XMLDocumentScannerImpl {
    private final XMLString fString;
    private final XMLStringBuffer fStringBuffer;
    private final XMLStringBuffer fStringBuffer2;
    private final XMLStringBuffer fStringBuffer3;

    public XML11DocumentScannerImpl() {
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fStringBuffer3 = new XMLStringBuffer();
    }

    protected int scanContent() throws IOException, XNIException {
        XMLString content = this.fString;
        int c = this.fEntityScanner.scanContent(content);
        if (c == 13 || c == 133 || c == 8232) {
            this.fEntityScanner.scanChar();
            this.fStringBuffer.clear();
            this.fStringBuffer.append(this.fString);
            this.fStringBuffer.append((char) c);
            content = this.fStringBuffer;
            c = -1;
        }
        if (this.fDocumentHandler != null && content.length > 0) {
            this.fDocumentHandler.characters(content, null);
        }
        if (c != 93 || this.fString.length != 0) {
            return c;
        }
        this.fStringBuffer.clear();
        this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
        this.fInScanContent = true;
        if (this.fEntityScanner.skipChar(93)) {
            this.fStringBuffer.append(']');
            while (this.fEntityScanner.skipChar(93)) {
                this.fStringBuffer.append(']');
            }
            if (this.fEntityScanner.skipChar(62)) {
                reportFatalError("CDEndInContent", null);
            }
        }
        if (!(this.fDocumentHandler == null || this.fStringBuffer.length == 0)) {
            this.fDocumentHandler.characters(this.fStringBuffer, null);
        }
        this.fInScanContent = false;
        return -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean scanAttributeValue(mf.org.apache.xerces.xni.XMLString r13, mf.org.apache.xerces.xni.XMLString r14, java.lang.String r15, boolean r16, java.lang.String r17) throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r12 = this;
        r7 = r12.fEntityScanner;
        r6 = r7.peekChar();
        r7 = 39;
        if (r6 == r7) goto L_0x001c;
    L_0x000a:
        r7 = 34;
        if (r6 == r7) goto L_0x001c;
    L_0x000e:
        r7 = "OpenQuoteExpected";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
    L_0x001c:
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r3 = r12.fEntityDepth;
        r7 = r12.fEntityScanner;
        r0 = r7.scanLiteral(r6, r13);
        r5 = 0;
        if (r0 != r6) goto L_0x004e;
    L_0x002c:
        r5 = r12.isUnchangedByNormalization(r13);
        r7 = -1;
        if (r5 != r7) goto L_0x004e;
    L_0x0033:
        r14.setValues(r13);
        r7 = r12.fEntityScanner;
        r2 = r7.scanChar();
        if (r2 == r6) goto L_0x004c;
    L_0x003e:
        r7 = "CloseQuoteExpected";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
    L_0x004c:
        r7 = 1;
    L_0x004d:
        return r7;
    L_0x004e:
        r7 = r12.fStringBuffer2;
        r7.clear();
        r7 = r12.fStringBuffer2;
        r7.append(r13);
        r12.normalizeWhitespace(r13, r5);
        if (r0 == r6) goto L_0x00c5;
    L_0x005d:
        r7 = 1;
        r12.fScanningAttribute = r7;
        r7 = r12.fStringBuffer;
        r7.clear();
    L_0x0065:
        r7 = r12.fStringBuffer;
        r7.append(r13);
        r7 = 38;
        if (r0 != r7) goto L_0x01ac;
    L_0x006e:
        r7 = r12.fEntityScanner;
        r8 = 38;
        r7.skipChar(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0080;
    L_0x0079:
        r7 = r12.fStringBuffer2;
        r8 = 38;
        r7.append(r8);
    L_0x0080:
        r7 = r12.fEntityScanner;
        r8 = 35;
        r7 = r7.skipChar(r8);
        if (r7 == 0) goto L_0x00ec;
    L_0x008a:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0095;
    L_0x008e:
        r7 = r12.fStringBuffer2;
        r8 = 35;
        r7.append(r8);
    L_0x0095:
        r7 = r12.fStringBuffer;
        r8 = r12.fStringBuffer2;
        r1 = r12.scanCharReferenceValue(r7, r8);
        r7 = -1;
        if (r1 == r7) goto L_0x00a0;
    L_0x00a0:
        r7 = r12.fEntityScanner;
        r0 = r7.scanLiteral(r6, r13);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00af;
    L_0x00aa:
        r7 = r12.fStringBuffer2;
        r7.append(r13);
    L_0x00af:
        r12.normalizeWhitespace(r13);
        if (r0 != r6) goto L_0x0065;
    L_0x00b4:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0065;
    L_0x00b8:
        r7 = r12.fStringBuffer;
        r7.append(r13);
        r7 = r12.fStringBuffer;
        r13.setValues(r7);
        r7 = 0;
        r12.fScanningAttribute = r7;
    L_0x00c5:
        r7 = r12.fStringBuffer2;
        r14.setValues(r7);
        r7 = r12.fEntityScanner;
        r2 = r7.scanChar();
        if (r2 == r6) goto L_0x00e0;
    L_0x00d2:
        r7 = "CloseQuoteExpected";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
    L_0x00e0:
        r7 = r13.ch;
        r8 = r13.offset;
        r9 = r13.length;
        r7 = r14.equals(r7, r8, r9);
        goto L_0x004d;
    L_0x00ec:
        r7 = r12.fEntityScanner;
        r4 = r7.scanName();
        if (r4 != 0) goto L_0x011b;
    L_0x00f4:
        r7 = "NameRequiredInReference";
        r8 = 0;
        r12.reportFatalError(r7, r8);
    L_0x00fa:
        r7 = r12.fEntityScanner;
        r8 = 59;
        r7 = r7.skipChar(r8);
        if (r7 != 0) goto L_0x0125;
    L_0x0104:
        r7 = "SemicolonRequiredInReference";
        r8 = 1;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r4;
        r12.reportFatalError(r7, r8);
    L_0x010f:
        r7 = fAmpSymbol;
        if (r4 != r7) goto L_0x0131;
    L_0x0113:
        r7 = r12.fStringBuffer;
        r8 = 38;
        r7.append(r8);
        goto L_0x00a0;
    L_0x011b:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00fa;
    L_0x011f:
        r7 = r12.fStringBuffer2;
        r7.append(r4);
        goto L_0x00fa;
    L_0x0125:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x010f;
    L_0x0129:
        r7 = r12.fStringBuffer2;
        r8 = 59;
        r7.append(r8);
        goto L_0x010f;
    L_0x0131:
        r7 = fAposSymbol;
        if (r4 != r7) goto L_0x013e;
    L_0x0135:
        r7 = r12.fStringBuffer;
        r8 = 39;
        r7.append(r8);
        goto L_0x00a0;
    L_0x013e:
        r7 = fLtSymbol;
        if (r4 != r7) goto L_0x014b;
    L_0x0142:
        r7 = r12.fStringBuffer;
        r8 = 60;
        r7.append(r8);
        goto L_0x00a0;
    L_0x014b:
        r7 = fGtSymbol;
        if (r4 != r7) goto L_0x0158;
    L_0x014f:
        r7 = r12.fStringBuffer;
        r8 = 62;
        r7.append(r8);
        goto L_0x00a0;
    L_0x0158:
        r7 = fQuotSymbol;
        if (r4 != r7) goto L_0x0165;
    L_0x015c:
        r7 = r12.fStringBuffer;
        r8 = 34;
        r7.append(r8);
        goto L_0x00a0;
    L_0x0165:
        r7 = r12.fEntityManager;
        r7 = r7.isExternalEntity(r4);
        if (r7 == 0) goto L_0x017a;
    L_0x016d:
        r7 = "ReferenceToExternalEntity";
        r8 = 1;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r4;
        r12.reportFatalError(r7, r8);
        goto L_0x00a0;
    L_0x017a:
        r7 = r12.fEntityManager;
        r7 = r7.isDeclaredEntity(r4);
        if (r7 != 0) goto L_0x0198;
    L_0x0182:
        if (r16 == 0) goto L_0x01a0;
    L_0x0184:
        r7 = r12.fValidation;
        if (r7 == 0) goto L_0x0198;
    L_0x0188:
        r7 = r12.fErrorReporter;
        r8 = "http://www.w3.org/TR/1998/REC-xml-19980210";
        r9 = "EntityNotDeclared";
        r10 = 1;
        r10 = new java.lang.Object[r10];
        r11 = 0;
        r10[r11] = r4;
        r11 = 1;
        r7.reportError(r8, r9, r10, r11);
    L_0x0198:
        r7 = r12.fEntityManager;
        r8 = 1;
        r7.startEntity(r4, r8);
        goto L_0x00a0;
    L_0x01a0:
        r7 = "EntityNotDeclared";
        r8 = 1;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r4;
        r12.reportFatalError(r7, r8);
        goto L_0x0198;
    L_0x01ac:
        r7 = 60;
        if (r0 != r7) goto L_0x01cf;
    L_0x01b0:
        r7 = "LessthanInAttValue";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a0;
    L_0x01c7:
        r7 = r12.fStringBuffer2;
        r8 = (char) r0;
        r7.append(r8);
        goto L_0x00a0;
    L_0x01cf:
        r7 = 37;
        if (r0 == r7) goto L_0x01d7;
    L_0x01d3:
        r7 = 93;
        if (r0 != r7) goto L_0x01ee;
    L_0x01d7:
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fStringBuffer;
        r8 = (char) r0;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a0;
    L_0x01e6:
        r7 = r12.fStringBuffer2;
        r8 = (char) r0;
        r7.append(r8);
        goto L_0x00a0;
    L_0x01ee:
        r7 = 10;
        if (r0 == r7) goto L_0x01fe;
    L_0x01f2:
        r7 = 13;
        if (r0 == r7) goto L_0x01fe;
    L_0x01f6:
        r7 = 133; // 0x85 float:1.86E-43 double:6.57E-322;
        if (r0 == r7) goto L_0x01fe;
    L_0x01fa:
        r7 = 8232; // 0x2028 float:1.1535E-41 double:4.067E-320;
        if (r0 != r7) goto L_0x0217;
    L_0x01fe:
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fStringBuffer;
        r8 = 32;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a0;
    L_0x020e:
        r7 = r12.fStringBuffer2;
        r8 = 10;
        r7.append(r8);
        goto L_0x00a0;
    L_0x0217:
        r7 = -1;
        if (r0 == r7) goto L_0x0241;
    L_0x021a:
        r7 = mf.org.apache.xerces.util.XMLChar.isHighSurrogate(r0);
        if (r7 == 0) goto L_0x0241;
    L_0x0220:
        r7 = r12.fStringBuffer3;
        r7.clear();
        r7 = r12.fStringBuffer3;
        r7 = r12.scanSurrogates(r7);
        if (r7 == 0) goto L_0x00a0;
    L_0x022d:
        r7 = r12.fStringBuffer;
        r8 = r12.fStringBuffer3;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a0;
    L_0x0238:
        r7 = r12.fStringBuffer2;
        r8 = r12.fStringBuffer3;
        r7.append(r8);
        goto L_0x00a0;
    L_0x0241:
        r7 = -1;
        if (r0 == r7) goto L_0x00a0;
    L_0x0244:
        r7 = r12.isInvalidLiteral(r0);
        if (r7 == 0) goto L_0x00a0;
    L_0x024a:
        r7 = "InvalidCharInAttValue";
        r8 = 3;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r9 = 2;
        r10 = 16;
        r10 = java.lang.Integer.toString(r0, r10);
        r8[r9] = r10;
        r12.reportFatalError(r7, r8);
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a0;
    L_0x026a:
        r7 = r12.fStringBuffer2;
        r8 = (char) r0;
        r7.append(r8);
        goto L_0x00a0;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XML11DocumentScannerImpl.scanAttributeValue(mf.org.apache.xerces.xni.XMLString, mf.org.apache.xerces.xni.XMLString, java.lang.String, boolean, java.lang.String):boolean");
    }

    protected boolean scanPubidLiteral(XMLString literal) throws IOException, XNIException {
        int quote = this.fEntityScanner.scanChar();
        if (quote == 39 || quote == 34) {
            this.fStringBuffer.clear();
            boolean skipSpace = true;
            boolean dataok = true;
            while (true) {
                int c = this.fEntityScanner.scanChar();
                if (c == 32 || c == 10 || c == 13 || c == 133 || c == 8232) {
                    if (!skipSpace) {
                        this.fStringBuffer.append(' ');
                        skipSpace = true;
                    }
                } else if (c == quote) {
                    break;
                } else if (XMLChar.isPubid(c)) {
                    this.fStringBuffer.append((char) c);
                    skipSpace = false;
                } else if (c == -1) {
                    reportFatalError("PublicIDUnterminated", null);
                    return false;
                } else {
                    dataok = false;
                    reportFatalError("InvalidCharInPublicID", new Object[]{Integer.toHexString(c)});
                }
            }
            if (skipSpace) {
                XMLString xMLString = this.fStringBuffer;
                xMLString.length = xMLString.length - 1;
            }
            literal.setValues(this.fStringBuffer);
            return dataok;
        }
        reportFatalError("QuoteRequiredInPublicID", null);
        return false;
    }

    protected void normalizeWhitespace(XMLString value) {
        int end = value.offset + value.length;
        for (int i = value.offset; i < end; i++) {
            if (XMLChar.isSpace(value.ch[i])) {
                value.ch[i] = ' ';
            }
        }
    }

    protected void normalizeWhitespace(XMLString value, int fromIndex) {
        int end = value.offset + value.length;
        for (int i = value.offset + fromIndex; i < end; i++) {
            if (XMLChar.isSpace(value.ch[i])) {
                value.ch[i] = ' ';
            }
        }
    }

    protected int isUnchangedByNormalization(XMLString value) {
        int end = value.offset + value.length;
        for (int i = value.offset; i < end; i++) {
            if (XMLChar.isSpace(value.ch[i])) {
                return i - value.offset;
            }
        }
        return -1;
    }

    protected boolean isInvalid(int value) {
        return XML11Char.isXML11Invalid(value);
    }

    protected boolean isInvalidLiteral(int value) {
        return !XML11Char.isXML11ValidLiteral(value);
    }

    protected boolean isValidNameChar(int value) {
        return XML11Char.isXML11Name(value);
    }

    protected boolean isValidNameStartChar(int value) {
        return XML11Char.isXML11NameStart(value);
    }

    protected boolean isValidNCName(int value) {
        return XML11Char.isXML11NCName(value);
    }

    protected boolean isValidNameStartHighSurrogate(int value) {
        return XML11Char.isXML11NameHighSurrogate(value);
    }

    protected boolean versionSupported(String version) {
        return version.equals("1.1") || version.equals("1.0");
    }

    protected String getVersionNotSupportedKey() {
        return "VersionNotSupported11";
    }
}
