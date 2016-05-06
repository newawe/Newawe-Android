package mf.org.apache.xml.serialize;

import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.android.gms.common.ConnectionResult;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map.Entry;
import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import org.apache.commons.lang.StringUtils;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class XMLSerializer extends BaseMarkupSerializer {
    protected static final boolean DEBUG = false;
    protected static final String PREFIX = "NS";
    protected NamespaceSupport fLocalNSBinder;
    protected NamespaceSupport fNSBinder;
    protected boolean fNamespacePrefixes;
    protected boolean fNamespaces;
    private boolean fPreserveSpace;
    protected SymbolTable fSymbolTable;

    public XMLSerializer() {
        super(new OutputFormat(Method.XML, null, (boolean) DEBUG));
        this.fNamespaces = DEBUG;
        this.fNamespacePrefixes = true;
    }

    public XMLSerializer(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.XML, null, (boolean) DEBUG);
        }
        super(format);
        this.fNamespaces = DEBUG;
        this.fNamespacePrefixes = true;
        this._format.setMethod(Method.XML);
    }

    public XMLSerializer(Writer writer, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.XML, null, (boolean) DEBUG);
        }
        super(format);
        this.fNamespaces = DEBUG;
        this.fNamespacePrefixes = true;
        this._format.setMethod(Method.XML);
        setOutputCharStream(writer);
    }

    public XMLSerializer(OutputStream output, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.XML, null, (boolean) DEBUG);
        }
        super(format);
        this.fNamespaces = DEBUG;
        this.fNamespacePrefixes = true;
        this._format.setMethod(Method.XML);
        setOutputByteStream(output);
    }

    public void setOutputFormat(OutputFormat format) {
        if (format == null) {
            format = new OutputFormat(Method.XML, null, (boolean) DEBUG);
        }
        super.setOutputFormat(format);
    }

    public void setNamespaces(boolean namespaces) {
        this.fNamespaces = namespaces;
        if (this.fNSBinder == null) {
            this.fNSBinder = new NamespaceSupport();
            this.fLocalNSBinder = new NamespaceSupport();
            this.fSymbolTable = new SymbolTable();
        }
    }

    public void startElement(String namespaceURI, String localName, String rawName, Attributes attrs) throws SAXException {
        try {
            if (this._printer == null) {
                throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
            }
            String prefix;
            String name;
            String value;
            ElementState state = getElementState();
            if (!isDocumentState()) {
                if (state.empty) {
                    this._printer.printText('>');
                }
                if (state.inCData) {
                    this._printer.printText("]]>");
                    state.inCData = DEBUG;
                }
                if (this._indenting && !state.preserveSpace && (state.empty || state.afterElement || state.afterComment)) {
                    this._printer.breakLine();
                }
            } else if (!this._started) {
                String str;
                if (localName == null || localName.length() == 0) {
                    str = rawName;
                } else {
                    str = localName;
                }
                startDocument(str);
            }
            boolean preserveSpace = state.preserveSpace;
            attrs = extractNamespaces(attrs);
            if (rawName == null || rawName.length() == 0) {
                if (localName == null) {
                    throw new SAXException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoName", null));
                }
                if (namespaceURI != null) {
                    if (!namespaceURI.equals(StringUtils.EMPTY)) {
                        prefix = getPrefix(namespaceURI);
                        if (prefix == null || prefix.length() <= 0) {
                            rawName = localName;
                        } else {
                            rawName = new StringBuilder(String.valueOf(prefix)).append(":").append(localName).toString();
                        }
                    }
                }
                rawName = localName;
            }
            this._printer.printText('<');
            this._printer.printText(rawName);
            this._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); i++) {
                    this._printer.printSpace();
                    name = attrs.getQName(i);
                    if (name != null && name.length() == 0) {
                        name = attrs.getLocalName(i);
                        String attrURI = attrs.getURI(i);
                        if (!(attrURI == null || attrURI.length() == 0 || (namespaceURI != null && namespaceURI.length() != 0 && attrURI.equals(namespaceURI)))) {
                            prefix = getPrefix(attrURI);
                            if (prefix != null && prefix.length() > 0) {
                                name = new StringBuilder(String.valueOf(prefix)).append(":").append(name).toString();
                            }
                        }
                    }
                    value = attrs.getValue(i);
                    if (value == null) {
                        value = StringUtils.EMPTY;
                    }
                    this._printer.printText(name);
                    this._printer.printText("=\"");
                    printEscaped(value);
                    this._printer.printText('\"');
                    if (name.equals("xml:space")) {
                        if (value.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
                            preserveSpace = true;
                        } else {
                            preserveSpace = this._format.getPreserveSpace();
                        }
                    }
                }
            }
            if (this._prefixes != null) {
                for (Entry entry : this._prefixes.entrySet()) {
                    this._printer.printSpace();
                    value = (String) entry.getKey();
                    name = (String) entry.getValue();
                    if (name.length() == 0) {
                        this._printer.printText("xmlns=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    } else {
                        this._printer.printText("xmlns:");
                        this._printer.printText(name);
                        this._printer.printText("=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    }
                }
            }
            state = enterElementState(namespaceURI, localName, rawName, preserveSpace);
            name = (localName == null || localName.length() == 0) ? rawName : new StringBuilder(String.valueOf(namespaceURI)).append("^").append(localName).toString();
            state.doCData = this._format.isCDataElement(name);
            state.unescaped = this._format.isNonEscapingElement(name);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
        try {
            endElementIO(namespaceURI, localName, rawName);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElementIO(String namespaceURI, String localName, String rawName) throws IOException {
        this._printer.unindent();
        ElementState state = getElementState();
        if (state.empty) {
            this._printer.printText("/>");
        } else {
            if (state.inCData) {
                this._printer.printText("]]>");
            }
            if (this._indenting && !state.preserveSpace && (state.afterElement || state.afterComment)) {
                this._printer.breakLine();
            }
            this._printer.printText("</");
            this._printer.printText(state.rawName);
            this._printer.printText('>');
        }
        state = leaveElementState();
        state.afterElement = true;
        state.afterComment = DEBUG;
        state.empty = DEBUG;
        if (isDocumentState()) {
            this._printer.flush();
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
                if (state.inCData) {
                    this._printer.printText("]]>");
                    state.inCData = DEBUG;
                }
                if (this._indenting && !state.preserveSpace && (state.empty || state.afterElement || state.afterComment)) {
                    this._printer.breakLine();
                }
            } else if (!this._started) {
                startDocument(tagName);
            }
            boolean preserveSpace = state.preserveSpace;
            this._printer.printText('<');
            this._printer.printText(tagName);
            this._printer.indent();
            if (attrs != null) {
                for (int i = 0; i < attrs.getLength(); i++) {
                    this._printer.printSpace();
                    String name = attrs.getName(i);
                    String value = attrs.getValue(i);
                    if (value != null) {
                        this._printer.printText(name);
                        this._printer.printText("=\"");
                        printEscaped(value);
                        this._printer.printText('\"');
                    }
                    if (name.equals("xml:space")) {
                        if (value.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
                            preserveSpace = true;
                        } else {
                            preserveSpace = this._format.getPreserveSpace();
                        }
                    }
                }
            }
            state = enterElementState(null, null, tagName, preserveSpace);
            state.doCData = this._format.isCDataElement(tagName);
            state.unescaped = this._format.isNonEscapingElement(tagName);
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    public void endElement(String tagName) throws SAXException {
        endElement(null, null, tagName);
    }

    protected void startDocument(String rootTagName) throws IOException {
        String dtd = this._printer.leaveDTD();
        if (!this._started) {
            if (!this._format.getOmitXMLDeclaration()) {
                StringBuffer buffer = new StringBuffer("<?xml version=\"");
                if (this._format.getVersion() != null) {
                    buffer.append(this._format.getVersion());
                } else {
                    buffer.append("1.0");
                }
                buffer.append('\"');
                String format_encoding = this._format.getEncoding();
                if (format_encoding != null) {
                    buffer.append(" encoding=\"");
                    buffer.append(format_encoding);
                    buffer.append('\"');
                }
                if (this._format.getStandalone() && this._docTypeSystemId == null && this._docTypePublicId == null) {
                    buffer.append(" standalone=\"yes\"");
                }
                buffer.append("?>");
                this._printer.printText(buffer);
                this._printer.breakLine();
            }
            if (!this._format.getOmitDocumentType()) {
                if (this._docTypeSystemId != null) {
                    this._printer.printText("<!DOCTYPE ");
                    this._printer.printText(rootTagName);
                    if (this._docTypePublicId != null) {
                        this._printer.printText(" PUBLIC ");
                        printDoctypeURL(this._docTypePublicId);
                        if (this._indenting) {
                            this._printer.breakLine();
                            for (int i = 0; i < rootTagName.length() + 18; i++) {
                                this._printer.printText(" ");
                            }
                        } else {
                            this._printer.printText(" ");
                        }
                        printDoctypeURL(this._docTypeSystemId);
                    } else {
                        this._printer.printText(" SYSTEM ");
                        printDoctypeURL(this._docTypeSystemId);
                    }
                    if (dtd != null && dtd.length() > 0) {
                        this._printer.printText(" [");
                        printText(dtd, true, true);
                        this._printer.printText(']');
                    }
                    this._printer.printText(">");
                    this._printer.breakLine();
                } else if (dtd != null && dtd.length() > 0) {
                    this._printer.printText("<!DOCTYPE ");
                    this._printer.printText(rootTagName);
                    this._printer.printText(" [");
                    printText(dtd, true, true);
                    this._printer.printText("]>");
                    this._printer.breakLine();
                }
            }
        }
        this._started = true;
        serializePreRoot();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void serializeElement(mf.org.w3c.dom.Element r30) throws java.io.IOException {
        /*
        r29 = this;
        r0 = r29;
        r0 = r0.fNamespaces;
        r24 = r0;
        if (r24 == 0) goto L_0x001a;
    L_0x0008:
        r0 = r29;
        r0 = r0.fLocalNSBinder;
        r24 = r0;
        r24.reset();
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r24.pushContext();
    L_0x001a:
        r21 = r30.getTagName();
        r20 = r29.getElementState();
        r24 = r29.isDocumentState();
        if (r24 == 0) goto L_0x00ec;
    L_0x0028:
        r0 = r29;
        r0 = r0._started;
        r24 = r0;
        if (r24 != 0) goto L_0x0037;
    L_0x0030:
        r0 = r29;
        r1 = r21;
        r0.startDocument(r1);
    L_0x0037:
        r0 = r20;
        r0 = r0.preserveSpace;
        r24 = r0;
        r0 = r24;
        r1 = r29;
        r1.fPreserveSpace = r0;
        r14 = 0;
        r6 = 0;
        r24 = r30.hasAttributes();
        if (r24 == 0) goto L_0x0053;
    L_0x004b:
        r6 = r30.getAttributes();
        r14 = r6.getLength();
    L_0x0053:
        r0 = r29;
        r0 = r0.fNamespaces;
        r24 = r0;
        if (r24 != 0) goto L_0x0172;
    L_0x005b:
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r25 = 60;
        r24.printText(r25);
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r0 = r24;
        r1 = r21;
        r0.printText(r1);
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r24.indent();
        r13 = 0;
    L_0x007d:
        if (r13 < r14) goto L_0x014d;
    L_0x007f:
        r24 = r30.hasChildNodes();
        if (r24 == 0) goto L_0x0696;
    L_0x0085:
        r24 = 0;
        r25 = 0;
        r0 = r29;
        r0 = r0.fPreserveSpace;
        r26 = r0;
        r0 = r29;
        r1 = r24;
        r2 = r25;
        r3 = r21;
        r4 = r26;
        r20 = r0.enterElementState(r1, r2, r3, r4);
        r0 = r29;
        r0 = r0._format;
        r24 = r0;
        r0 = r24;
        r1 = r21;
        r24 = r0.isCDataElement(r1);
        r0 = r24;
        r1 = r20;
        r1.doCData = r0;
        r0 = r29;
        r0 = r0._format;
        r24 = r0;
        r0 = r24;
        r1 = r21;
        r24 = r0.isNonEscapingElement(r1);
        r0 = r24;
        r1 = r20;
        r1.unescaped = r0;
        r7 = r30.getFirstChild();
    L_0x00c9:
        if (r7 != 0) goto L_0x068b;
    L_0x00cb:
        r0 = r29;
        r0 = r0.fNamespaces;
        r24 = r0;
        if (r24 == 0) goto L_0x00dc;
    L_0x00d3:
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r24.popContext();
    L_0x00dc:
        r24 = 0;
        r25 = 0;
        r0 = r29;
        r1 = r24;
        r2 = r25;
        r3 = r21;
        r0.endElementIO(r1, r2, r3);
    L_0x00eb:
        return;
    L_0x00ec:
        r0 = r20;
        r0 = r0.empty;
        r24 = r0;
        if (r24 == 0) goto L_0x00ff;
    L_0x00f4:
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r25 = 62;
        r24.printText(r25);
    L_0x00ff:
        r0 = r20;
        r0 = r0.inCData;
        r24 = r0;
        if (r24 == 0) goto L_0x011a;
    L_0x0107:
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r25 = "]]>";
        r24.printText(r25);
        r24 = 0;
        r0 = r24;
        r1 = r20;
        r1.inCData = r0;
    L_0x011a:
        r0 = r29;
        r0 = r0._indenting;
        r24 = r0;
        if (r24 == 0) goto L_0x0037;
    L_0x0122:
        r0 = r20;
        r0 = r0.preserveSpace;
        r24 = r0;
        if (r24 != 0) goto L_0x0037;
    L_0x012a:
        r0 = r20;
        r0 = r0.empty;
        r24 = r0;
        if (r24 != 0) goto L_0x0142;
    L_0x0132:
        r0 = r20;
        r0 = r0.afterElement;
        r24 = r0;
        if (r24 != 0) goto L_0x0142;
    L_0x013a:
        r0 = r20;
        r0 = r0.afterComment;
        r24 = r0;
        if (r24 == 0) goto L_0x0037;
    L_0x0142:
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r24.breakLine();
        goto L_0x0037;
    L_0x014d:
        r5 = r6.item(r13);
        r5 = (mf.org.w3c.dom.Attr) r5;
        r18 = r5.getName();
        r23 = r5.getValue();
        if (r23 != 0) goto L_0x015f;
    L_0x015d:
        r23 = "";
    L_0x015f:
        r24 = r5.getSpecified();
        r0 = r29;
        r1 = r18;
        r2 = r23;
        r3 = r24;
        r0.printAttribute(r1, r2, r3, r5);
        r13 = r13 + 1;
        goto L_0x007d;
    L_0x0172:
        r13 = 0;
    L_0x0173:
        if (r13 < r14) goto L_0x02c6;
    L_0x0175:
        r22 = r30.getNamespaceURI();
        r19 = r30.getPrefix();
        if (r22 == 0) goto L_0x03ab;
    L_0x017f:
        if (r19 == 0) goto L_0x03ab;
    L_0x0181:
        r24 = r22.length();
        if (r24 != 0) goto L_0x03ab;
    L_0x0187:
        r24 = r19.length();
        if (r24 == 0) goto L_0x03ab;
    L_0x018d:
        r19 = 0;
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r25 = 60;
        r24.printText(r25);
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r25 = r30.getLocalName();
        r24.printText(r25);
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r24.indent();
    L_0x01b0:
        if (r22 == 0) goto L_0x03de;
    L_0x01b2:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r22;
        r22 = r0.addSymbol(r1);
        if (r19 == 0) goto L_0x01c8;
    L_0x01c2:
        r24 = r19.length();
        if (r24 != 0) goto L_0x03ce;
    L_0x01c8:
        r19 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
    L_0x01ca:
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r24 = r0.getURI(r1);
        r0 = r24;
        r1 = r22;
        if (r0 == r1) goto L_0x020d;
    L_0x01de:
        r0 = r29;
        r0 = r0.fNamespacePrefixes;
        r24 = r0;
        if (r24 == 0) goto L_0x01ef;
    L_0x01e6:
        r0 = r29;
        r1 = r19;
        r2 = r22;
        r0.printNamespaceAttr(r1, r2);
    L_0x01ef:
        r0 = r29;
        r0 = r0.fLocalNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r2 = r22;
        r0.declarePrefix(r1, r2);
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r2 = r22;
        r0.declarePrefix(r1, r2);
    L_0x020d:
        r13 = 0;
    L_0x020e:
        if (r13 >= r14) goto L_0x007f;
    L_0x0210:
        r5 = r6.item(r13);
        r5 = (mf.org.w3c.dom.Attr) r5;
        r23 = r5.getValue();
        r18 = r5.getNodeName();
        r22 = r5.getNamespaceURI();
        if (r22 == 0) goto L_0x0230;
    L_0x0224:
        r24 = r22.length();
        if (r24 != 0) goto L_0x0230;
    L_0x022a:
        r22 = 0;
        r18 = r5.getLocalName();
    L_0x0230:
        if (r23 != 0) goto L_0x0234;
    L_0x0232:
        r23 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
    L_0x0234:
        if (r22 == 0) goto L_0x0612;
    L_0x0236:
        r19 = r5.getPrefix();
        if (r19 != 0) goto L_0x047c;
    L_0x023c:
        r19 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
    L_0x023e:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r25 = r5.getLocalName();
        r16 = r24.addSymbol(r25);
        if (r22 == 0) goto L_0x04d9;
    L_0x024e:
        r24 = mf.org.apache.xerces.xni.NamespaceContext.XMLNS_URI;
        r0 = r22;
        r1 = r24;
        r24 = r0.equals(r1);
        if (r24 == 0) goto L_0x04d9;
    L_0x025a:
        r19 = r5.getPrefix();
        if (r19 == 0) goto L_0x0266;
    L_0x0260:
        r24 = r19.length();
        if (r24 != 0) goto L_0x048c;
    L_0x0266:
        r19 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
    L_0x0268:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r25 = r5.getLocalName();
        r16 = r24.addSymbol(r25);
        r24 = mf.org.apache.xerces.util.XMLSymbols.PREFIX_XMLNS;
        r0 = r19;
        r1 = r24;
        if (r0 != r1) goto L_0x049c;
    L_0x027e:
        r0 = r29;
        r0 = r0.fLocalNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r16;
        r15 = r0.getURI(r1);
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r23;
        r23 = r0.addSymbol(r1);
        r24 = r23.length();
        if (r24 == 0) goto L_0x02c2;
    L_0x02a0:
        if (r15 != 0) goto L_0x02c2;
    L_0x02a2:
        r0 = r29;
        r0 = r0.fNamespacePrefixes;
        r24 = r0;
        if (r24 == 0) goto L_0x02b3;
    L_0x02aa:
        r0 = r29;
        r1 = r16;
        r2 = r23;
        r0.printNamespaceAttr(r1, r2);
    L_0x02b3:
        r0 = r29;
        r0 = r0.fLocalNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r16;
        r2 = r23;
        r0.declarePrefix(r1, r2);
    L_0x02c2:
        r13 = r13 + 1;
        goto L_0x020e;
    L_0x02c6:
        r5 = r6.item(r13);
        r5 = (mf.org.w3c.dom.Attr) r5;
        r22 = r5.getNamespaceURI();
        if (r22 == 0) goto L_0x0378;
    L_0x02d2:
        r24 = mf.org.apache.xerces.xni.NamespaceContext.XMLNS_URI;
        r0 = r22;
        r1 = r24;
        r24 = r0.equals(r1);
        if (r24 == 0) goto L_0x0378;
    L_0x02de:
        r23 = r5.getNodeValue();
        if (r23 != 0) goto L_0x02e6;
    L_0x02e4:
        r23 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
    L_0x02e6:
        r24 = mf.org.apache.xerces.xni.NamespaceContext.XMLNS_URI;
        r24 = r23.equals(r24);
        if (r24 == 0) goto L_0x0331;
    L_0x02ee:
        r0 = r29;
        r0 = r0.fDOMErrorHandler;
        r24 = r0;
        if (r24 == 0) goto L_0x0378;
    L_0x02f6:
        r24 = "http://www.w3.org/TR/1998/REC-xml-19980210";
        r25 = "CantBindXMLNS";
        r26 = 0;
        r17 = mf.org.apache.xerces.dom.DOMMessageFormatter.formatMessage(r24, r25, r26);
        r24 = 2;
        r25 = 0;
        r0 = r29;
        r1 = r17;
        r2 = r24;
        r3 = r25;
        r0.modifyDOMError(r1, r2, r3, r5);
        r0 = r29;
        r0 = r0.fDOMErrorHandler;
        r24 = r0;
        r0 = r29;
        r0 = r0.fDOMError;
        r25 = r0;
        r8 = r24.handleError(r25);
        if (r8 != 0) goto L_0x0378;
    L_0x0321:
        r24 = new java.lang.RuntimeException;
        r25 = "http://apache.org/xml/serializer";
        r26 = "SerializationStopped";
        r27 = 0;
        r25 = mf.org.apache.xerces.dom.DOMMessageFormatter.formatMessage(r25, r26, r27);
        r24.<init>(r25);
        throw r24;
    L_0x0331:
        r19 = r5.getPrefix();
        if (r19 == 0) goto L_0x033d;
    L_0x0337:
        r24 = r19.length();
        if (r24 != 0) goto L_0x037c;
    L_0x033d:
        r19 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
    L_0x033f:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r25 = r5.getLocalName();
        r16 = r24.addSymbol(r25);
        r24 = mf.org.apache.xerces.util.XMLSymbols.PREFIX_XMLNS;
        r0 = r19;
        r1 = r24;
        if (r0 != r1) goto L_0x038b;
    L_0x0355:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r23;
        r23 = r0.addSymbol(r1);
        r24 = r23.length();
        if (r24 == 0) goto L_0x0378;
    L_0x0369:
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r16;
        r2 = r23;
        r0.declarePrefix(r1, r2);
    L_0x0378:
        r13 = r13 + 1;
        goto L_0x0173;
    L_0x037c:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r19 = r0.addSymbol(r1);
        goto L_0x033f;
    L_0x038b:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r23;
        r23 = r0.addSymbol(r1);
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r25 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r0 = r24;
        r1 = r25;
        r2 = r23;
        r0.declarePrefix(r1, r2);
        goto L_0x0378;
    L_0x03ab:
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r25 = 60;
        r24.printText(r25);
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r0 = r24;
        r1 = r21;
        r0.printText(r1);
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r24.indent();
        goto L_0x01b0;
    L_0x03ce:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r19 = r0.addSymbol(r1);
        goto L_0x01ca;
    L_0x03de:
        r24 = r30.getLocalName();
        if (r24 != 0) goto L_0x0437;
    L_0x03e4:
        r0 = r29;
        r0 = r0.fDOMErrorHandler;
        r24 = r0;
        if (r24 == 0) goto L_0x020d;
    L_0x03ec:
        r24 = "http://www.w3.org/dom/DOMTR";
        r25 = "NullLocalElementName";
        r26 = 1;
        r0 = r26;
        r0 = new java.lang.Object[r0];
        r26 = r0;
        r27 = 0;
        r28 = r30.getNodeName();
        r26[r27] = r28;
        r17 = mf.org.apache.xerces.dom.DOMMessageFormatter.formatMessage(r24, r25, r26);
        r24 = 2;
        r25 = 0;
        r0 = r29;
        r1 = r17;
        r2 = r24;
        r3 = r25;
        r4 = r30;
        r0.modifyDOMError(r1, r2, r3, r4);
        r0 = r29;
        r0 = r0.fDOMErrorHandler;
        r24 = r0;
        r0 = r29;
        r0 = r0.fDOMError;
        r25 = r0;
        r8 = r24.handleError(r25);
        if (r8 != 0) goto L_0x020d;
    L_0x0427:
        r24 = new java.lang.RuntimeException;
        r25 = "http://apache.org/xml/serializer";
        r26 = "SerializationStopped";
        r27 = 0;
        r25 = mf.org.apache.xerces.dom.DOMMessageFormatter.formatMessage(r25, r26, r27);
        r24.<init>(r25);
        throw r24;
    L_0x0437:
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r25 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r22 = r24.getURI(r25);
        if (r22 == 0) goto L_0x020d;
    L_0x0445:
        r24 = r22.length();
        if (r24 <= 0) goto L_0x020d;
    L_0x044b:
        r0 = r29;
        r0 = r0.fNamespacePrefixes;
        r24 = r0;
        if (r24 == 0) goto L_0x0460;
    L_0x0453:
        r24 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r25 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r0 = r29;
        r1 = r24;
        r2 = r25;
        r0.printNamespaceAttr(r1, r2);
    L_0x0460:
        r0 = r29;
        r0 = r0.fLocalNSBinder;
        r24 = r0;
        r25 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r26 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r24.declarePrefix(r25, r26);
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r25 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r26 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r24.declarePrefix(r25, r26);
        goto L_0x020d;
    L_0x047c:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r19 = r0.addSymbol(r1);
        goto L_0x023e;
    L_0x048c:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r19 = r0.addSymbol(r1);
        goto L_0x0268;
    L_0x049c:
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r25 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r22 = r24.getURI(r25);
        r0 = r29;
        r0 = r0.fLocalNSBinder;
        r24 = r0;
        r25 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r15 = r24.getURI(r25);
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r23;
        r23 = r0.addSymbol(r1);
        if (r15 != 0) goto L_0x02c2;
    L_0x04c4:
        r0 = r29;
        r0 = r0.fNamespacePrefixes;
        r24 = r0;
        if (r24 == 0) goto L_0x02c2;
    L_0x04cc:
        r24 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r0 = r29;
        r1 = r24;
        r2 = r23;
        r0.printNamespaceAttr(r1, r2);
        goto L_0x02c2;
    L_0x04d9:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r22;
        r22 = r0.addSymbol(r1);
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r12 = r0.getURI(r1);
        r24 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r0 = r19;
        r1 = r24;
        if (r0 == r1) goto L_0x0501;
    L_0x04fd:
        r0 = r22;
        if (r12 == r0) goto L_0x0538;
    L_0x0501:
        r18 = r5.getNodeName();
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r22;
        r11 = r0.getPrefix(r1);
        if (r11 == 0) goto L_0x054d;
    L_0x0515:
        r24 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r0 = r24;
        if (r11 == r0) goto L_0x054d;
    L_0x051b:
        r19 = r11;
        r24 = new java.lang.StringBuilder;
        r25 = java.lang.String.valueOf(r19);
        r24.<init>(r25);
        r25 = ":";
        r24 = r24.append(r25);
        r0 = r24;
        r1 = r16;
        r24 = r0.append(r1);
        r18 = r24.toString();
    L_0x0538:
        if (r23 != 0) goto L_0x060e;
    L_0x053a:
        r24 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
    L_0x053c:
        r25 = r5.getSpecified();
        r0 = r29;
        r1 = r18;
        r2 = r24;
        r3 = r25;
        r0.printAttribute(r1, r2, r3, r5);
        goto L_0x02c2;
    L_0x054d:
        r24 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r0 = r19;
        r1 = r24;
        if (r0 == r1) goto L_0x0565;
    L_0x0555:
        r0 = r29;
        r0 = r0.fLocalNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r24 = r0.getURI(r1);
        if (r24 == 0) goto L_0x05af;
    L_0x0565:
        r9 = 1;
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r25 = new java.lang.StringBuilder;
        r26 = "NS";
        r25.<init>(r26);
        r10 = r9 + 1;
        r0 = r25;
        r25 = r0.append(r9);
        r25 = r25.toString();
        r19 = r24.addSymbol(r25);
        r9 = r10;
    L_0x0584:
        r0 = r29;
        r0 = r0.fLocalNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r24 = r0.getURI(r1);
        if (r24 != 0) goto L_0x05ee;
    L_0x0594:
        r24 = new java.lang.StringBuilder;
        r25 = java.lang.String.valueOf(r19);
        r24.<init>(r25);
        r25 = ":";
        r24 = r24.append(r25);
        r0 = r24;
        r1 = r16;
        r24 = r0.append(r1);
        r18 = r24.toString();
    L_0x05af:
        r0 = r29;
        r0 = r0.fNamespacePrefixes;
        r24 = r0;
        if (r24 == 0) goto L_0x05c0;
    L_0x05b7:
        r0 = r29;
        r1 = r19;
        r2 = r22;
        r0.printNamespaceAttr(r1, r2);
    L_0x05c0:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r0 = r24;
        r1 = r23;
        r23 = r0.addSymbol(r1);
        r0 = r29;
        r0 = r0.fLocalNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r2 = r23;
        r0.declarePrefix(r1, r2);
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r0 = r24;
        r1 = r19;
        r2 = r22;
        r0.declarePrefix(r1, r2);
        goto L_0x0538;
    L_0x05ee:
        r0 = r29;
        r0 = r0.fSymbolTable;
        r24 = r0;
        r25 = new java.lang.StringBuilder;
        r26 = "NS";
        r25.<init>(r26);
        r10 = r9 + 1;
        r0 = r25;
        r25 = r0.append(r9);
        r25 = r25.toString();
        r19 = r24.addSymbol(r25);
        r9 = r10;
        goto L_0x0584;
    L_0x060e:
        r24 = r23;
        goto L_0x053c;
    L_0x0612:
        r24 = r5.getLocalName();
        if (r24 != 0) goto L_0x067a;
    L_0x0618:
        r0 = r29;
        r0 = r0.fDOMErrorHandler;
        r24 = r0;
        if (r24 == 0) goto L_0x0669;
    L_0x0620:
        r24 = "http://www.w3.org/dom/DOMTR";
        r25 = "NullLocalAttrName";
        r26 = 1;
        r0 = r26;
        r0 = new java.lang.Object[r0];
        r26 = r0;
        r27 = 0;
        r28 = r5.getNodeName();
        r26[r27] = r28;
        r17 = mf.org.apache.xerces.dom.DOMMessageFormatter.formatMessage(r24, r25, r26);
        r24 = 2;
        r25 = 0;
        r0 = r29;
        r1 = r17;
        r2 = r24;
        r3 = r25;
        r0.modifyDOMError(r1, r2, r3, r5);
        r0 = r29;
        r0 = r0.fDOMErrorHandler;
        r24 = r0;
        r0 = r29;
        r0 = r0.fDOMError;
        r25 = r0;
        r8 = r24.handleError(r25);
        if (r8 != 0) goto L_0x0669;
    L_0x0659:
        r24 = new java.lang.RuntimeException;
        r25 = "http://apache.org/xml/serializer";
        r26 = "SerializationStopped";
        r27 = 0;
        r25 = mf.org.apache.xerces.dom.DOMMessageFormatter.formatMessage(r25, r26, r27);
        r24.<init>(r25);
        throw r24;
    L_0x0669:
        r24 = r5.getSpecified();
        r0 = r29;
        r1 = r18;
        r2 = r23;
        r3 = r24;
        r0.printAttribute(r1, r2, r3, r5);
        goto L_0x02c2;
    L_0x067a:
        r24 = r5.getSpecified();
        r0 = r29;
        r1 = r18;
        r2 = r23;
        r3 = r24;
        r0.printAttribute(r1, r2, r3, r5);
        goto L_0x02c2;
    L_0x068b:
        r0 = r29;
        r0.serializeNode(r7);
        r7 = r7.getNextSibling();
        goto L_0x00c9;
    L_0x0696:
        r0 = r29;
        r0 = r0.fNamespaces;
        r24 = r0;
        if (r24 == 0) goto L_0x06a7;
    L_0x069e:
        r0 = r29;
        r0 = r0.fNSBinder;
        r24 = r0;
        r24.popContext();
    L_0x06a7:
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r24.unindent();
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r25 = "/>";
        r24.printText(r25);
        r24 = 1;
        r0 = r24;
        r1 = r20;
        r1.afterElement = r0;
        r24 = 0;
        r0 = r24;
        r1 = r20;
        r1.afterComment = r0;
        r24 = 0;
        r0 = r24;
        r1 = r20;
        r1.empty = r0;
        r24 = r29.isDocumentState();
        if (r24 == 0) goto L_0x00eb;
    L_0x06d9:
        r0 = r29;
        r0 = r0._printer;
        r24 = r0;
        r24.flush();
        goto L_0x00eb;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xml.serialize.XMLSerializer.serializeElement(mf.org.w3c.dom.Element):void");
    }

    private void printNamespaceAttr(String prefix, String uri) throws IOException {
        this._printer.printSpace();
        if (prefix == XMLSymbols.EMPTY_STRING) {
            this._printer.printText(XMLSymbols.PREFIX_XMLNS);
        } else {
            this._printer.printText("xmlns:" + prefix);
        }
        this._printer.printText("=\"");
        printEscaped(uri);
        this._printer.printText('\"');
    }

    private void printAttribute(String name, String value, boolean isSpecified, Attr attr) throws IOException {
        if (isSpecified || (this.features & 64) == 0) {
            if (!(this.fDOMFilter == null || (this.fDOMFilter.getWhatToShow() & 2) == 0)) {
                switch (this.fDOMFilter.acceptNode(attr)) {
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        return;
                }
            }
            this._printer.printSpace();
            this._printer.printText(name);
            this._printer.printText("=\"");
            printEscaped(value);
            this._printer.printText('\"');
        }
        if (!name.equals("xml:space")) {
            return;
        }
        if (value.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
            this.fPreserveSpace = true;
        } else {
            this.fPreserveSpace = this._format.getPreserveSpace();
        }
    }

    protected String getEntityRef(int ch) {
        switch (ch) {
            case Tokens.EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF /*34*/:
                return "quot";
            case Tokens.EXPRTOKEN_AXISNAME_DESCENDANT_OR_SELF /*38*/:
                return "amp";
            case Tokens.EXPRTOKEN_AXISNAME_FOLLOWING /*39*/:
                return "apos";
            case C0302R.styleable.Theme_popupMenuStyle /*60*/:
                return "lt";
            case C0302R.styleable.Theme_editTextColor /*62*/:
                return "gt";
            default:
                return null;
        }
    }

    private Attributes extractNamespaces(Attributes attrs) throws SAXException {
        if (attrs == null) {
            return null;
        }
        int length = attrs.getLength();
        Attributes attrsOnly = new AttributesImpl(attrs);
        for (int i = length - 1; i >= 0; i--) {
            String rawName = attrsOnly.getQName(i);
            if (rawName.startsWith(XMLConstants.XMLNS_ATTRIBUTE)) {
                if (rawName.length() == 5) {
                    startPrefixMapping(StringUtils.EMPTY, attrs.getValue(i));
                    attrsOnly.removeAttribute(i);
                } else if (rawName.charAt(5) == ':') {
                    startPrefixMapping(rawName.substring(6), attrs.getValue(i));
                    attrsOnly.removeAttribute(i);
                }
            }
        }
        return attrsOnly;
    }

    protected void printEscaped(String source) throws IOException {
        int length = source.length();
        int i = 0;
        while (i < length) {
            int ch = source.charAt(i);
            if (!XMLChar.isValid(ch)) {
                i++;
                if (i < length) {
                    surrogates(ch, source.charAt(i), DEBUG);
                } else {
                    fatalError("The character '" + ((char) ch) + "' is an invalid XML character");
                }
            } else if (ch == 10 || ch == 13 || ch == 9) {
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

    protected void printXMLChar(int ch) throws IOException {
        if (ch == 13) {
            printHex(ch);
        } else if (ch == 60) {
            this._printer.printText("&lt;");
        } else if (ch == 38) {
            this._printer.printText("&amp;");
        } else if (ch == 62) {
            this._printer.printText("&gt;");
        } else if (ch == 10 || ch == 9 || (ch >= 32 && this._encodingInfo.isPrintable((char) ch))) {
            this._printer.printText((char) ch);
        } else {
            printHex(ch);
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
                if (!XMLChar.isValid(ch)) {
                    index++;
                    if (index < length) {
                        surrogates(ch, text.charAt(index), true);
                    } else {
                        fatalError("The character '" + ch + "' is an invalid XML character");
                    }
                } else if (unescaped) {
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
            if (!XMLChar.isValid(ch)) {
                index++;
                if (index < length) {
                    surrogates(ch, text.charAt(index), true);
                } else {
                    fatalError("The character '" + ch + "' is an invalid XML character");
                }
            } else if (unescaped) {
                this._printer.printText(ch);
            } else {
                printXMLChar(ch);
            }
            index++;
        }
    }

    protected void printText(char[] chars, int start, int length, boolean preserveSpace, boolean unescaped) throws IOException {
        int length2;
        int start2;
        char ch;
        if (preserveSpace) {
            length2 = length;
            start2 = start;
            while (true) {
                length = length2 - 1;
                if (length2 <= 0) {
                    start = start2;
                    return;
                }
                start = start2 + 1;
                ch = chars[start2];
                if (!XMLChar.isValid(ch)) {
                    length2 = length - 1;
                    if (length > 0) {
                        start2 = start + 1;
                        surrogates(ch, chars[start], true);
                    } else {
                        fatalError("The character '" + ch + "' is an invalid XML character");
                        start2 = start;
                    }
                } else if (unescaped) {
                    this._printer.printText(ch);
                    length2 = length;
                    start2 = start;
                } else {
                    printXMLChar(ch);
                    length2 = length;
                    start2 = start;
                }
            }
        } else {
            while (true) {
                length2 = length;
                start2 = start;
                while (true) {
                    length = length2 - 1;
                    if (length2 > 0) {
                        start = start2 + 1;
                        ch = chars[start2];
                        if (XMLChar.isValid(ch)) {
                            if (!unescaped) {
                                break;
                            }
                            this._printer.printText(ch);
                            length2 = length;
                            start2 = start;
                        } else {
                            length2 = length - 1;
                            if (length > 0) {
                                start2 = start + 1;
                                surrogates(ch, chars[start], true);
                            } else {
                                fatalError("The character '" + ch + "' is an invalid XML character");
                                start2 = start;
                            }
                        }
                    } else {
                        start = start2;
                        return;
                    }
                }
                printXMLChar(ch);
            }
        }
    }

    protected void checkUnboundNamespacePrefixedNode(Node node) throws IOException {
        if (this.fNamespaces) {
            Node child = node.getFirstChild();
            while (child != null) {
                Node next = child.getNextSibling();
                String prefix = child.getPrefix();
                if (prefix == null || prefix.length() == 0) {
                    prefix = XMLSymbols.EMPTY_STRING;
                } else {
                    prefix = this.fSymbolTable.addSymbol(prefix);
                }
                if (this.fNSBinder.getURI(prefix) == null && prefix != null) {
                    fatalError("The replacement text of the entity node '" + node.getNodeName() + "' contains an element node '" + child.getNodeName() + "' with an undeclared prefix '" + prefix + "'.");
                }
                if (child.getNodeType() == (short) 1) {
                    NamedNodeMap attrs = child.getAttributes();
                    for (int i = 0; i < attrs.getLength(); i++) {
                        String attrPrefix = attrs.item(i).getPrefix();
                        attrPrefix = (attrPrefix == null || attrPrefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(attrPrefix);
                        if (this.fNSBinder.getURI(attrPrefix) == null && attrPrefix != null) {
                            fatalError("The replacement text of the entity node '" + node.getNodeName() + "' contains an element node '" + child.getNodeName() + "' with an attribute '" + attrs.item(i).getNodeName() + "' an undeclared prefix '" + attrPrefix + "'.");
                        }
                    }
                }
                if (child.hasChildNodes()) {
                    checkUnboundNamespacePrefixedNode(child);
                }
                child = next;
            }
        }
    }

    public boolean reset() {
        super.reset();
        if (this.fNSBinder != null) {
            this.fNSBinder.reset();
            this.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING);
        }
        return true;
    }
}
