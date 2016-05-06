package mf.org.apache.xml.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.apache.xerces.util.XMLChar;
import org.xml.sax.SAXException;

public class XML11Serializer extends XMLSerializer {
    protected static final boolean DEBUG = false;
    protected static final String PREFIX = "NS";
    protected boolean fDOML1;
    protected NamespaceSupport fLocalNSBinder;
    protected NamespaceSupport fNSBinder;
    protected int fNamespaceCounter;
    protected boolean fNamespaces;
    protected SymbolTable fSymbolTable;

    public XML11Serializer() {
        this.fDOML1 = DEBUG;
        this.fNamespaceCounter = 1;
        this.fNamespaces = DEBUG;
        this._format.setVersion("1.1");
    }

    public XML11Serializer(OutputFormat format) {
        super(format);
        this.fDOML1 = DEBUG;
        this.fNamespaceCounter = 1;
        this.fNamespaces = DEBUG;
        this._format.setVersion("1.1");
    }

    public XML11Serializer(Writer writer, OutputFormat format) {
        super(writer, format);
        this.fDOML1 = DEBUG;
        this.fNamespaceCounter = 1;
        this.fNamespaces = DEBUG;
        this._format.setVersion("1.1");
    }

    public XML11Serializer(OutputStream output, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.XML, null, (boolean) DEBUG);
        }
        super(output, format);
        this.fDOML1 = DEBUG;
        this.fNamespaceCounter = 1;
        this.fNamespaces = DEBUG;
        this._format.setVersion("1.1");
    }

    public void characters(char[] chars, int start, int length) throws SAXException {
        try {
            ElementState state = content();
            int saveIndent;
            if (state.inCData || state.doCData) {
                if (!state.inCData) {
                    this._printer.printText("<![CDATA[");
                    state.inCData = true;
                }
                saveIndent = this._printer.getNextIndent();
                this._printer.setNextIndent(0);
                int end = start + length;
                int index = start;
                while (index < end) {
                    char ch = chars[index];
                    if (ch == ']' && index + 2 < end && chars[index + 1] == ']' && chars[index + 2] == '>') {
                        this._printer.printText("]]]]><![CDATA[>");
                        index += 2;
                    } else if (!XML11Char.isXML11Valid(ch)) {
                        index++;
                        if (index < end) {
                            surrogates(ch, chars[index], true);
                        } else {
                            fatalError("The character '" + ch + "' is an invalid XML character");
                        }
                    } else if (this._encodingInfo.isPrintable(ch) && XML11Char.isXML11ValidLiteral(ch)) {
                        this._printer.printText(ch);
                    } else {
                        this._printer.printText("]]>&#x");
                        this._printer.printText(Integer.toHexString(ch));
                        this._printer.printText(";<![CDATA[");
                    }
                    index++;
                }
                this._printer.setNextIndent(saveIndent);
            } else if (state.preserveSpace) {
                saveIndent = this._printer.getNextIndent();
                this._printer.setNextIndent(0);
                printText(chars, start, length, true, state.unescaped);
                this._printer.setNextIndent(saveIndent);
            } else {
                printText(chars, start, length, DEBUG, state.unescaped);
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    protected void printEscaped(String source) throws IOException {
        int length = source.length();
        int i = 0;
        while (i < length) {
            int ch = source.charAt(i);
            if (!XML11Char.isXML11Valid(ch)) {
                i++;
                if (i < length) {
                    surrogates(ch, source.charAt(i), DEBUG);
                } else {
                    fatalError("The character '" + ((char) ch) + "' is an invalid XML character");
                }
            } else if (ch == 10 || ch == 13 || ch == 9 || ch == 133 || ch == 8232) {
                printHex(ch);
            } else if (ch == 60) {
                this._printer.printText("&lt;");
            } else if (ch == 38) {
                this._printer.printText("&amp;");
            } else if (ch == 34) {
                this._printer.printText("&quot;");
            } else if (ch < 32 || !this._encodingInfo.isPrintable((char) ch)) {
                printHex(ch);
            } else {
                this._printer.printText((char) ch);
            }
            i++;
        }
    }

    protected final void printCDATAText(String text) throws IOException {
        int length = text.length();
        int index = 0;
        while (index < length) {
            char ch = text.charAt(index);
            if (ch == ']' && index + 2 < length && text.charAt(index + 1) == ']' && text.charAt(index + 2) == '>') {
                if (this.fDOMErrorHandler != null) {
                    if ((this.features & 16) == 0 && (this.features & 2) == 0) {
                        modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "EndingCDATA", null), (short) 3, null, this.fCurrentNode);
                        if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                            throw new IOException();
                        }
                    }
                    modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SplittingCDATA", null), (short) 1, null, this.fCurrentNode);
                    this.fDOMErrorHandler.handleError(this.fDOMError);
                }
                this._printer.printText("]]]]><![CDATA[>");
                index += 2;
            } else if (!XML11Char.isXML11Valid(ch)) {
                index++;
                if (index < length) {
                    surrogates(ch, text.charAt(index), true);
                } else {
                    fatalError("The character '" + ch + "' is an invalid XML character");
                }
            } else if (this._encodingInfo.isPrintable(ch) && XML11Char.isXML11ValidLiteral(ch)) {
                this._printer.printText(ch);
            } else {
                this._printer.printText("]]>&#x");
                this._printer.printText(Integer.toHexString(ch));
                this._printer.printText(";<![CDATA[");
            }
            index++;
        }
    }

    protected final void printXMLChar(int ch) throws IOException {
        if (ch == 13 || ch == 133 || ch == 8232) {
            printHex(ch);
        } else if (ch == 60) {
            this._printer.printText("&lt;");
        } else if (ch == 38) {
            this._printer.printText("&amp;");
        } else if (ch == 62) {
            this._printer.printText("&gt;");
        } else if (this._encodingInfo.isPrintable((char) ch) && XML11Char.isXML11ValidLiteral(ch)) {
            this._printer.printText((char) ch);
        } else {
            printHex(ch);
        }
    }

    protected final void surrogates(int high, int low, boolean inContent) throws IOException {
        if (!XMLChar.isHighSurrogate(high)) {
            fatalError("The character '" + ((char) high) + "' is an invalid XML character");
        } else if (XMLChar.isLowSurrogate(low)) {
            int supplemental = XMLChar.supplemental((char) high, (char) low);
            if (!XML11Char.isXML11Valid(supplemental)) {
                fatalError("The character '" + ((char) supplemental) + "' is an invalid XML character");
            } else if (inContent && content().inCData) {
                this._printer.printText("]]>&#x");
                this._printer.printText(Integer.toHexString(supplemental));
                this._printer.printText(";<![CDATA[");
            } else {
                printHex(supplemental);
            }
        } else {
            fatalError("The character '" + ((char) low) + "' is an invalid XML character");
        }
    }

    protected void printText(String text, boolean preserveSpace, boolean unescaped) throws IOException {
        int length = text.length();
        int index;
        char ch;
        if (preserveSpace) {
            index = 0;
            while (index < length) {
                ch = text.charAt(index);
                if (!XML11Char.isXML11Valid(ch)) {
                    index++;
                    if (index < length) {
                        surrogates(ch, text.charAt(index), true);
                    } else {
                        fatalError("The character '" + ch + "' is an invalid XML character");
                    }
                } else if (unescaped && XML11Char.isXML11ValidLiteral(ch)) {
                    this._printer.printText(ch);
                } else {
                    printXMLChar(ch);
                }
                index++;
            }
            return;
        }
        index = 0;
        while (index < length) {
            ch = text.charAt(index);
            if (!XML11Char.isXML11Valid(ch)) {
                index++;
                if (index < length) {
                    surrogates(ch, text.charAt(index), true);
                } else {
                    fatalError("The character '" + ch + "' is an invalid XML character");
                }
            } else if (unescaped && XML11Char.isXML11ValidLiteral(ch)) {
                this._printer.printText(ch);
            } else {
                printXMLChar(ch);
            }
            index++;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void printText(char[] r7, int r8, int r9, boolean r10, boolean r11) throws java.io.IOException {
        /*
        r6 = this;
        r5 = 1;
        if (r10 == 0) goto L_0x0099;
    L_0x0003:
        r1 = r9;
        r2 = r8;
    L_0x0005:
        r9 = r1 + -1;
        if (r1 > 0) goto L_0x000b;
    L_0x0009:
        r8 = r2;
    L_0x000a:
        return;
    L_0x000b:
        r8 = r2 + 1;
        r0 = r7[r2];
        r3 = mf.org.apache.xerces.util.XML11Char.isXML11Valid(r0);
        if (r3 != 0) goto L_0x003b;
    L_0x0015:
        r1 = r9 + -1;
        if (r9 <= 0) goto L_0x0021;
    L_0x0019:
        r2 = r8 + 1;
        r3 = r7[r8];
        r6.surrogates(r0, r3, r5);
        goto L_0x0005;
    L_0x0021:
        r3 = new java.lang.StringBuilder;
        r4 = "The character '";
        r3.<init>(r4);
        r3 = r3.append(r0);
        r4 = "' is an invalid XML character";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r6.fatalError(r3);
        r2 = r8;
        goto L_0x0005;
    L_0x003b:
        if (r11 == 0) goto L_0x004b;
    L_0x003d:
        r3 = mf.org.apache.xerces.util.XML11Char.isXML11ValidLiteral(r0);
        if (r3 == 0) goto L_0x004b;
    L_0x0043:
        r3 = r6._printer;
        r3.printText(r0);
        r1 = r9;
        r2 = r8;
        goto L_0x0005;
    L_0x004b:
        r6.printXMLChar(r0);
        r1 = r9;
        r2 = r8;
        goto L_0x0005;
    L_0x0051:
        r8 = r2 + 1;
        r0 = r7[r2];
        r3 = mf.org.apache.xerces.util.XML11Char.isXML11Valid(r0);
        if (r3 != 0) goto L_0x0086;
    L_0x005b:
        r1 = r9 + -1;
        if (r9 <= 0) goto L_0x006c;
    L_0x005f:
        r2 = r8 + 1;
        r3 = r7[r8];
        r6.surrogates(r0, r3, r5);
    L_0x0066:
        r9 = r1 + -1;
        if (r1 > 0) goto L_0x0051;
    L_0x006a:
        r8 = r2;
        goto L_0x000a;
    L_0x006c:
        r3 = new java.lang.StringBuilder;
        r4 = "The character '";
        r3.<init>(r4);
        r3 = r3.append(r0);
        r4 = "' is an invalid XML character";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r6.fatalError(r3);
        r2 = r8;
        goto L_0x0066;
    L_0x0086:
        if (r11 == 0) goto L_0x0096;
    L_0x0088:
        r3 = mf.org.apache.xerces.util.XML11Char.isXML11ValidLiteral(r0);
        if (r3 == 0) goto L_0x0096;
    L_0x008e:
        r3 = r6._printer;
        r3.printText(r0);
        r1 = r9;
        r2 = r8;
        goto L_0x0066;
    L_0x0096:
        r6.printXMLChar(r0);
    L_0x0099:
        r1 = r9;
        r2 = r8;
        goto L_0x0066;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xml.serialize.XML11Serializer.printText(char[], int, int, boolean, boolean):void");
    }

    public boolean reset() {
        super.reset();
        return true;
    }
}
