package mf.org.apache.xml.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Locale;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xml.serialize.OutputFormat.DTD;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;
import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;

public class HTMLSerializer extends BaseMarkupSerializer {
    public static final String XHTMLNamespace = "http://www.w3.org/1999/xhtml";
    private boolean _xhtml;
    private String fUserXHTMLNamespace;

    protected HTMLSerializer(boolean xhtml, OutputFormat format) {
        super(format);
        this.fUserXHTMLNamespace = null;
        this._xhtml = xhtml;
    }

    public HTMLSerializer() {
        this(false, new OutputFormat(Method.HTML, HTTP.ISO_8859_1, false));
    }

    public HTMLSerializer(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.HTML, HTTP.ISO_8859_1, false);
        }
        this(false, format);
    }

    public HTMLSerializer(Writer writer, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.HTML, HTTP.ISO_8859_1, false);
        }
        this(false, format);
        setOutputCharStream(writer);
    }

    public HTMLSerializer(OutputStream output, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.HTML, HTTP.ISO_8859_1, false);
        }
        this(false, format);
        setOutputByteStream(output);
    }

    public void setOutputFormat(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.HTML, HTTP.ISO_8859_1, false);
        }
        super.setOutputFormat(format);
    }

    public void setXHTMLNamespace(String newNamespace) {
        this.fUserXHTMLNamespace = newNamespace;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startElement(java.lang.String r21, java.lang.String r22, java.lang.String r23, org.xml.sax.Attributes r24) throws org.xml.sax.SAXException {
        /*
        r20 = this;
        r4 = 0;
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 != 0) goto L_0x0022;
    L_0x0009:
        r16 = new java.lang.IllegalStateException;	 Catch:{ IOException -> 0x0019 }
        r17 = "http://apache.org/xml/serializer";
        r18 = "NoWriterSupplied";
        r19 = 0;
        r17 = mf.org.apache.xerces.dom.DOMMessageFormatter.formatMessage(r17, r18, r19);	 Catch:{ IOException -> 0x0019 }
        r16.<init>(r17);	 Catch:{ IOException -> 0x0019 }
        throw r16;	 Catch:{ IOException -> 0x0019 }
    L_0x0019:
        r7 = move-exception;
        r16 = new org.xml.sax.SAXException;
        r0 = r16;
        r0.<init>(r7);
        throw r16;
    L_0x0022:
        r14 = r20.getElementState();	 Catch:{ IOException -> 0x0019 }
        r16 = r20.isDocumentState();	 Catch:{ IOException -> 0x0019 }
        if (r16 == 0) goto L_0x0141;
    L_0x002c:
        r0 = r20;
        r0 = r0._started;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 != 0) goto L_0x0045;
    L_0x0034:
        if (r22 == 0) goto L_0x003c;
    L_0x0036:
        r16 = r22.length();	 Catch:{ IOException -> 0x0019 }
        if (r16 != 0) goto L_0x013d;
    L_0x003c:
        r16 = r23;
    L_0x003e:
        r0 = r20;
        r1 = r16;
        r0.startDocument(r1);	 Catch:{ IOException -> 0x0019 }
    L_0x0045:
        r13 = r14.preserveSpace;	 Catch:{ IOException -> 0x0019 }
        if (r21 == 0) goto L_0x0177;
    L_0x0049:
        r16 = r21.length();	 Catch:{ IOException -> 0x0019 }
        if (r16 == 0) goto L_0x0177;
    L_0x004f:
        r8 = 1;
    L_0x0050:
        if (r23 == 0) goto L_0x0058;
    L_0x0052:
        r16 = r23.length();	 Catch:{ IOException -> 0x0019 }
        if (r16 != 0) goto L_0x0084;
    L_0x0058:
        r23 = r22;
        if (r8 == 0) goto L_0x0083;
    L_0x005c:
        r12 = r20.getPrefix(r21);	 Catch:{ IOException -> 0x0019 }
        if (r12 == 0) goto L_0x0083;
    L_0x0062:
        r16 = r12.length();	 Catch:{ IOException -> 0x0019 }
        if (r16 == 0) goto L_0x0083;
    L_0x0068:
        r16 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0019 }
        r17 = java.lang.String.valueOf(r12);	 Catch:{ IOException -> 0x0019 }
        r16.<init>(r17);	 Catch:{ IOException -> 0x0019 }
        r17 = ":";
        r16 = r16.append(r17);	 Catch:{ IOException -> 0x0019 }
        r0 = r16;
        r1 = r22;
        r16 = r0.append(r1);	 Catch:{ IOException -> 0x0019 }
        r23 = r16.toString();	 Catch:{ IOException -> 0x0019 }
    L_0x0083:
        r4 = 1;
    L_0x0084:
        if (r8 != 0) goto L_0x017a;
    L_0x0086:
        r9 = r23;
    L_0x0088:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = 60;
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._xhtml;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 == 0) goto L_0x01a5;
    L_0x009b:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = java.util.Locale.ENGLISH;	 Catch:{ IOException -> 0x0019 }
        r0 = r23;
        r1 = r17;
        r17 = r0.toLowerCase(r1);	 Catch:{ IOException -> 0x0019 }
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
    L_0x00ae:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r16.indent();	 Catch:{ IOException -> 0x0019 }
        if (r24 == 0) goto L_0x00c2;
    L_0x00b9:
        r10 = 0;
    L_0x00ba:
        r16 = r24.getLength();	 Catch:{ IOException -> 0x0019 }
        r0 = r16;
        if (r10 < r0) goto L_0x01b4;
    L_0x00c2:
        if (r9 == 0) goto L_0x00cb;
    L_0x00c4:
        r16 = mf.org.apache.xml.serialize.HTMLdtd.isPreserveSpace(r9);	 Catch:{ IOException -> 0x0019 }
        if (r16 == 0) goto L_0x00cb;
    L_0x00ca:
        r13 = 1;
    L_0x00cb:
        if (r4 == 0) goto L_0x00e1;
    L_0x00cd:
        r0 = r20;
        r0 = r0._prefixes;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r16 = r16.entrySet();	 Catch:{ IOException -> 0x0019 }
        r5 = r16.iterator();	 Catch:{ IOException -> 0x0019 }
    L_0x00db:
        r16 = r5.hasNext();	 Catch:{ IOException -> 0x0019 }
        if (r16 != 0) goto L_0x02b5;
    L_0x00e1:
        r0 = r20;
        r1 = r21;
        r2 = r22;
        r3 = r23;
        r14 = r0.enterElementState(r1, r2, r3, r13);	 Catch:{ IOException -> 0x0019 }
        if (r9 == 0) goto L_0x0114;
    L_0x00ef:
        r16 = "A";
        r0 = r16;
        r16 = r9.equalsIgnoreCase(r0);	 Catch:{ IOException -> 0x0019 }
        if (r16 != 0) goto L_0x0103;
    L_0x00f9:
        r16 = "TD";
        r0 = r16;
        r16 = r9.equalsIgnoreCase(r0);	 Catch:{ IOException -> 0x0019 }
        if (r16 == 0) goto L_0x0114;
    L_0x0103:
        r16 = 0;
        r0 = r16;
        r14.empty = r0;	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = 62;
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
    L_0x0114:
        if (r9 == 0) goto L_0x013c;
    L_0x0116:
        r16 = "SCRIPT";
        r0 = r23;
        r1 = r16;
        r16 = r0.equalsIgnoreCase(r1);	 Catch:{ IOException -> 0x0019 }
        if (r16 != 0) goto L_0x012e;
    L_0x0122:
        r16 = "STYLE";
        r0 = r23;
        r1 = r16;
        r16 = r0.equalsIgnoreCase(r1);	 Catch:{ IOException -> 0x0019 }
        if (r16 == 0) goto L_0x013c;
    L_0x012e:
        r0 = r20;
        r0 = r0._xhtml;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 == 0) goto L_0x0326;
    L_0x0136:
        r16 = 1;
        r0 = r16;
        r14.doCData = r0;	 Catch:{ IOException -> 0x0019 }
    L_0x013c:
        return;
    L_0x013d:
        r16 = r22;
        goto L_0x003e;
    L_0x0141:
        r0 = r14.empty;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 == 0) goto L_0x0152;
    L_0x0147:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = 62;
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
    L_0x0152:
        r0 = r20;
        r0 = r0._indenting;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 == 0) goto L_0x0045;
    L_0x015a:
        r0 = r14.preserveSpace;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 != 0) goto L_0x0045;
    L_0x0160:
        r0 = r14.empty;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 != 0) goto L_0x016c;
    L_0x0166:
        r0 = r14.afterElement;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 == 0) goto L_0x0045;
    L_0x016c:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r16.breakLine();	 Catch:{ IOException -> 0x0019 }
        goto L_0x0045;
    L_0x0177:
        r8 = 0;
        goto L_0x0050;
    L_0x017a:
        r16 = "http://www.w3.org/1999/xhtml";
        r0 = r21;
        r1 = r16;
        r16 = r0.equals(r1);	 Catch:{ IOException -> 0x0019 }
        if (r16 != 0) goto L_0x019e;
    L_0x0186:
        r0 = r20;
        r0 = r0.fUserXHTMLNamespace;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 == 0) goto L_0x01a2;
    L_0x018e:
        r0 = r20;
        r0 = r0.fUserXHTMLNamespace;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r0 = r16;
        r1 = r21;
        r16 = r0.equals(r1);	 Catch:{ IOException -> 0x0019 }
        if (r16 == 0) goto L_0x01a2;
    L_0x019e:
        r9 = r22;
        goto L_0x0088;
    L_0x01a2:
        r9 = 0;
        goto L_0x0088;
    L_0x01a5:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r0 = r16;
        r1 = r23;
        r0.printText(r1);	 Catch:{ IOException -> 0x0019 }
        goto L_0x00ae;
    L_0x01b4:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r16.printSpace();	 Catch:{ IOException -> 0x0019 }
        r0 = r24;
        r16 = r0.getQName(r10);	 Catch:{ IOException -> 0x0019 }
        r17 = java.util.Locale.ENGLISH;	 Catch:{ IOException -> 0x0019 }
        r11 = r16.toLowerCase(r17);	 Catch:{ IOException -> 0x0019 }
        r0 = r24;
        r15 = r0.getValue(r10);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._xhtml;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        if (r16 != 0) goto L_0x01d9;
    L_0x01d7:
        if (r8 == 0) goto L_0x021c;
    L_0x01d9:
        if (r15 != 0) goto L_0x01f5;
    L_0x01db:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r0 = r16;
        r0.printText(r11);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = "=\"\"";
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
    L_0x01f1:
        r10 = r10 + 1;
        goto L_0x00ba;
    L_0x01f5:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r0 = r16;
        r0.printText(r11);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = "=\"";
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0.printEscaped(r15);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = 34;
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        goto L_0x01f1;
    L_0x021c:
        if (r15 != 0) goto L_0x0220;
    L_0x021e:
        r15 = "";
    L_0x0220:
        r0 = r20;
        r0 = r0._format;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r16 = r16.getPreserveEmptyAttributes();	 Catch:{ IOException -> 0x0019 }
        if (r16 != 0) goto L_0x023e;
    L_0x022c:
        r16 = r15.length();	 Catch:{ IOException -> 0x0019 }
        if (r16 != 0) goto L_0x023e;
    L_0x0232:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r0 = r16;
        r0.printText(r11);	 Catch:{ IOException -> 0x0019 }
        goto L_0x01f1;
    L_0x023e:
        r0 = r23;
        r16 = mf.org.apache.xml.serialize.HTMLdtd.isURI(r0, r11);	 Catch:{ IOException -> 0x0019 }
        if (r16 == 0) goto L_0x0278;
    L_0x0246:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r0 = r16;
        r0.printText(r11);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = "=\"";
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r0 = r20;
        r17 = r0.escapeURI(r15);	 Catch:{ IOException -> 0x0019 }
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = 34;
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        goto L_0x01f1;
    L_0x0278:
        r0 = r23;
        r16 = mf.org.apache.xml.serialize.HTMLdtd.isBoolean(r0, r11);	 Catch:{ IOException -> 0x0019 }
        if (r16 == 0) goto L_0x028d;
    L_0x0280:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r0 = r16;
        r0.printText(r11);	 Catch:{ IOException -> 0x0019 }
        goto L_0x01f1;
    L_0x028d:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r0 = r16;
        r0.printText(r11);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = "=\"";
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0.printEscaped(r15);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = 34;
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        goto L_0x01f1;
    L_0x02b5:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r16.printSpace();	 Catch:{ IOException -> 0x0019 }
        r6 = r5.next();	 Catch:{ IOException -> 0x0019 }
        r6 = (java.util.Map.Entry) r6;	 Catch:{ IOException -> 0x0019 }
        r15 = r6.getKey();	 Catch:{ IOException -> 0x0019 }
        r15 = (java.lang.String) r15;	 Catch:{ IOException -> 0x0019 }
        r11 = r6.getValue();	 Catch:{ IOException -> 0x0019 }
        r11 = (java.lang.String) r11;	 Catch:{ IOException -> 0x0019 }
        r16 = r11.length();	 Catch:{ IOException -> 0x0019 }
        if (r16 != 0) goto L_0x02f3;
    L_0x02d6:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = "xmlns=\"";
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0.printEscaped(r15);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = 34;
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        goto L_0x00db;
    L_0x02f3:
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = "xmlns:";
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r0 = r16;
        r0.printText(r11);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = "=\"";
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0.printEscaped(r15);	 Catch:{ IOException -> 0x0019 }
        r0 = r20;
        r0 = r0._printer;	 Catch:{ IOException -> 0x0019 }
        r16 = r0;
        r17 = 34;
        r16.printText(r17);	 Catch:{ IOException -> 0x0019 }
        goto L_0x00db;
    L_0x0326:
        r16 = 1;
        r0 = r16;
        r14.unescaped = r0;	 Catch:{ IOException -> 0x0019 }
        goto L_0x013c;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xml.serialize.HTMLSerializer.startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes):void");
    }

    public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        try {
            endElementIO(namespaceURI, localName, rawName);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElementIO(String namespaceURI, String localName, String rawName) throws IOException {
        String htmlName;
        this._printer.unindent();
        ElementState state = getElementState();
        if (state.namespaceURI == null || state.namespaceURI.length() == 0) {
            htmlName = state.rawName;
        } else if (state.namespaceURI.equals(XHTMLNamespace) || (this.fUserXHTMLNamespace != null && this.fUserXHTMLNamespace.equals(state.namespaceURI))) {
            htmlName = state.localName;
        } else {
            htmlName = null;
        }
        if (!this._xhtml) {
            if (state.empty) {
                this._printer.printText('>');
            }
            if (htmlName == null || !HTMLdtd.isOnlyOpening(htmlName)) {
                if (this._indenting && !state.preserveSpace && state.afterElement) {
                    this._printer.breakLine();
                }
                if (state.inCData) {
                    this._printer.printText("]]>");
                }
                this._printer.printText("</");
                this._printer.printText(state.rawName);
                this._printer.printText('>');
            }
        } else if (state.empty) {
            this._printer.printText(" />");
        } else {
            if (state.inCData) {
                this._printer.printText("]]>");
            }
            this._printer.printText("</");
            this._printer.printText(state.rawName.toLowerCase(Locale.ENGLISH));
            this._printer.printText('>');
        }
        state = leaveElementState();
        if (htmlName == null || !(htmlName.equalsIgnoreCase("A") || htmlName.equalsIgnoreCase("TD"))) {
            state.afterElement = true;
        }
        state.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
        }
    }

    public void characters(char[] chars, int start, int length) throws SAXException {
        try {
            content().doCData = false;
            super.characters(chars, start, length);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void startElement(String tagName, AttributeList attrs) throws SAXException {
        try {
            if (this._printer == null) {
                throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
            }
            ElementState state = getElementState();
            if (!isDocumentState()) {
                if (state.empty) {
                    this._printer.printText('>');
                }
                if (this._indenting && !state.preserveSpace && (state.empty || state.afterElement)) {
                    this._printer.breakLine();
                }
            } else if (!this._started) {
                startDocument(tagName);
            }
            boolean preserveSpace = state.preserveSpace;
            this._printer.printText('<');
            if (this._xhtml) {
                this._printer.printText(tagName.toLowerCase(Locale.ENGLISH));
            } else {
                this._printer.printText(tagName);
            }
            this._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); i++) {
                    this._printer.printSpace();
                    String name = attrs.getName(i).toLowerCase(Locale.ENGLISH);
                    String value = attrs.getValue(i);
                    if (!this._xhtml) {
                        if (value == null) {
                            value = StringUtils.EMPTY;
                        }
                        if (!this._format.getPreserveEmptyAttributes() && value.length() == 0) {
                            this._printer.printText(name);
                        } else if (HTMLdtd.isURI(tagName, name)) {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            this._printer.printText(escapeURI(value));
                            this._printer.printText('\"');
                        } else if (HTMLdtd.isBoolean(tagName, name)) {
                            this._printer.printText(name);
                        } else {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            printEscaped(value);
                            this._printer.printText('\"');
                        }
                    } else if (value == null) {
                        this._printer.printText(name);
                        this._printer.printText("=\"\"");
                    } else {
                        this._printer.printText(name);
                        this._printer.printText("=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    }
                }
            }
            if (HTMLdtd.isPreserveSpace(tagName)) {
                preserveSpace = true;
            }
            state = enterElementState(null, null, tagName, preserveSpace);
            if (tagName.equalsIgnoreCase("A") || tagName.equalsIgnoreCase("TD")) {
                state.empty = false;
                this._printer.printText('>');
            }
            if (!tagName.equalsIgnoreCase("SCRIPT") && !tagName.equalsIgnoreCase("STYLE")) {
                return;
            }
            if (this._xhtml) {
                state.doCData = true;
            } else {
                state.unescaped = true;
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElement(String tagName) throws SAXException {
        endElement(null, null, tagName);
    }

    protected void startDocument(String rootTagName) throws IOException {
        this._printer.leaveDTD();
        if (!this._started) {
            if (this._docTypePublicId == null && this._docTypeSystemId == null) {
                if (this._xhtml) {
                    this._docTypePublicId = DTD.XHTMLPublicId;
                    this._docTypeSystemId = DTD.XHTMLSystemId;
                } else {
                    this._docTypePublicId = DTD.HTMLPublicId;
                    this._docTypeSystemId = DTD.HTMLSystemId;
                }
            }
            if (!this._format.getOmitDocumentType()) {
                if (this._docTypePublicId != null && (!this._xhtml || this._docTypeSystemId != null)) {
                    if (this._xhtml) {
                        this._printer.printText("<!DOCTYPE html PUBLIC ");
                    } else {
                        this._printer.printText("<!DOCTYPE HTML PUBLIC ");
                    }
                    printDoctypeURL(this._docTypePublicId);
                    if (this._docTypeSystemId != null) {
                        if (this._indenting) {
                            this._printer.breakLine();
                            this._printer.printText("                      ");
                        } else {
                            this._printer.printText(' ');
                        }
                        printDoctypeURL(this._docTypeSystemId);
                    }
                    this._printer.printText('>');
                    this._printer.breakLine();
                } else if (this._docTypeSystemId != null) {
                    if (this._xhtml) {
                        this._printer.printText("<!DOCTYPE html SYSTEM ");
                    } else {
                        this._printer.printText("<!DOCTYPE HTML SYSTEM ");
                    }
                    printDoctypeURL(this._docTypeSystemId);
                    this._printer.printText('>');
                    this._printer.breakLine();
                }
            }
        }
        this._started = true;
        serializePreRoot();
    }

    protected void serializeElement(Element elem) throws IOException {
        String tagName = elem.getTagName();
        ElementState state = getElementState();
        if (!isDocumentState()) {
            if (state.empty) {
                this._printer.printText('>');
            }
            if (this._indenting && !state.preserveSpace && (state.empty || state.afterElement)) {
                this._printer.breakLine();
            }
        } else if (!this._started) {
            startDocument(tagName);
        }
        boolean preserveSpace = state.preserveSpace;
        this._printer.printText('<');
        if (this._xhtml) {
            this._printer.printText(tagName.toLowerCase(Locale.ENGLISH));
        } else {
            this._printer.printText(tagName);
        }
        this._printer.indent();
        NamedNodeMap attrMap = elem.getAttributes();
        if (attrMap != null) {
            for (int i = 0; i < attrMap.getLength(); i++) {
                Attr attr = (Attr) attrMap.item(i);
                String name = attr.getName().toLowerCase(Locale.ENGLISH);
                String value = attr.getValue();
                if (attr.getSpecified()) {
                    this._printer.printSpace();
                    if (!this._xhtml) {
                        if (value == null) {
                            value = StringUtils.EMPTY;
                        }
                        if (!this._format.getPreserveEmptyAttributes() && value.length() == 0) {
                            this._printer.printText(name);
                        } else if (HTMLdtd.isURI(tagName, name)) {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            this._printer.printText(escapeURI(value));
                            this._printer.printText('\"');
                        } else if (HTMLdtd.isBoolean(tagName, name)) {
                            this._printer.printText(name);
                        } else {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            printEscaped(value);
                            this._printer.printText('\"');
                        }
                    } else if (value == null) {
                        this._printer.printText(name);
                        this._printer.printText("=\"\"");
                    } else {
                        this._printer.printText(name);
                        this._printer.printText("=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    }
                }
            }
        }
        if (HTMLdtd.isPreserveSpace(tagName)) {
            preserveSpace = true;
        }
        if (elem.hasChildNodes() || !HTMLdtd.isEmptyTag(tagName)) {
            state = enterElementState(null, null, tagName, preserveSpace);
            if (tagName.equalsIgnoreCase("A") || tagName.equalsIgnoreCase("TD")) {
                state.empty = false;
                this._printer.printText('>');
            }
            if (tagName.equalsIgnoreCase("SCRIPT") || tagName.equalsIgnoreCase("STYLE")) {
                if (this._xhtml) {
                    state.doCData = true;
                } else {
                    state.unescaped = true;
                }
            }
            for (Node child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                serializeNode(child);
            }
            endElementIO(null, null, tagName);
            return;
        }
        this._printer.unindent();
        if (this._xhtml) {
            this._printer.printText(" />");
        } else {
            this._printer.printText('>');
        }
        state.afterElement = true;
        state.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
        }
    }

    protected void characters(String text) throws IOException {
        content();
        super.characters(text);
    }

    protected String getEntityRef(int ch) {
        return HTMLdtd.fromChar(ch);
    }

    protected String escapeURI(String uri) {
        int index = uri.indexOf("\"");
        if (index >= 0) {
            return uri.substring(0, index);
        }
        return uri;
    }
}
