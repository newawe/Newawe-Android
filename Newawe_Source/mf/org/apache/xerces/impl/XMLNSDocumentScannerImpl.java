package mf.org.apache.xerces.impl;

import java.io.IOException;
import mf.org.apache.xerces.impl.dtd.XMLDTDValidatorFilter;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.util.XMLAttributesImpl;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;

public class XMLNSDocumentScannerImpl extends XMLDocumentScannerImpl {
    protected boolean fBindNamespaces;
    private XMLDTDValidatorFilter fDTDValidator;
    protected boolean fPerformValidation;
    private boolean fSawSpace;

    protected final class NSContentDispatcher extends ContentDispatcher {
        protected NSContentDispatcher() {
            super();
        }

        protected boolean scanRootElementHook() throws IOException, XNIException {
            if (XMLNSDocumentScannerImpl.this.fExternalSubsetResolver == null || XMLNSDocumentScannerImpl.this.fSeenDoctypeDecl || XMLNSDocumentScannerImpl.this.fDisallowDoctype || !(XMLNSDocumentScannerImpl.this.fValidation || XMLNSDocumentScannerImpl.this.fLoadExternalDTD)) {
                reconfigurePipeline();
                if (XMLNSDocumentScannerImpl.this.scanStartElement()) {
                    XMLNSDocumentScannerImpl.this.setScannerState(12);
                    XMLNSDocumentScannerImpl.this.setDispatcher(XMLNSDocumentScannerImpl.this.fTrailingMiscDispatcher);
                    return true;
                }
            }
            XMLNSDocumentScannerImpl.this.scanStartElementName();
            resolveExternalSubsetAndRead();
            reconfigurePipeline();
            if (XMLNSDocumentScannerImpl.this.scanStartElementAfterName()) {
                XMLNSDocumentScannerImpl.this.setScannerState(12);
                XMLNSDocumentScannerImpl.this.setDispatcher(XMLNSDocumentScannerImpl.this.fTrailingMiscDispatcher);
                return true;
            }
            return false;
        }

        private void reconfigurePipeline() {
            if (XMLNSDocumentScannerImpl.this.fDTDValidator == null) {
                XMLNSDocumentScannerImpl.this.fBindNamespaces = true;
            } else if (!XMLNSDocumentScannerImpl.this.fDTDValidator.hasGrammar()) {
                XMLNSDocumentScannerImpl.this.fBindNamespaces = true;
                XMLNSDocumentScannerImpl.this.fPerformValidation = XMLNSDocumentScannerImpl.this.fDTDValidator.validate();
                XMLDocumentSource source = XMLNSDocumentScannerImpl.this.fDTDValidator.getDocumentSource();
                XMLDocumentHandler handler = XMLNSDocumentScannerImpl.this.fDTDValidator.getDocumentHandler();
                source.setDocumentHandler(handler);
                if (handler != null) {
                    handler.setDocumentSource(source);
                }
                XMLNSDocumentScannerImpl.this.fDTDValidator.setDocumentSource(null);
                XMLNSDocumentScannerImpl.this.fDTDValidator.setDocumentHandler(null);
            }
        }
    }

    public void setDTDValidator(XMLDTDValidatorFilter dtdValidator) {
        this.fDTDValidator = dtdValidator;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean scanStartElement() throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r17 = this;
        r0 = r17;
        r11 = r0.fEntityScanner;
        r0 = r17;
        r12 = r0.fElementQName;
        r11.scanQName(r12);
        r0 = r17;
        r11 = r0.fElementQName;
        r8 = r11.rawname;
        r0 = r17;
        r11 = r0.fBindNamespaces;
        if (r11 == 0) goto L_0x0068;
    L_0x0017:
        r0 = r17;
        r11 = r0.fNamespaceContext;
        r11.pushContext();
        r0 = r17;
        r11 = r0.fScannerState;
        r12 = 6;
        if (r11 != r12) goto L_0x0068;
    L_0x0025:
        r0 = r17;
        r11 = r0.fPerformValidation;
        if (r11 == 0) goto L_0x0068;
    L_0x002b:
        r0 = r17;
        r11 = r0.fErrorReporter;
        r12 = "http://www.w3.org/TR/1998/REC-xml-19980210";
        r13 = "MSG_GRAMMAR_NOT_FOUND";
        r14 = 1;
        r14 = new java.lang.Object[r14];
        r15 = 0;
        r14[r15] = r8;
        r15 = 1;
        r11.reportError(r12, r13, r14, r15);
        r0 = r17;
        r11 = r0.fDoctypeName;
        if (r11 == 0) goto L_0x004d;
    L_0x0043:
        r0 = r17;
        r11 = r0.fDoctypeName;
        r11 = r11.equals(r8);
        if (r11 != 0) goto L_0x0068;
    L_0x004d:
        r0 = r17;
        r11 = r0.fErrorReporter;
        r12 = "http://www.w3.org/TR/1998/REC-xml-19980210";
        r13 = "RootElementTypeMustMatchDoctypedecl";
        r14 = 2;
        r14 = new java.lang.Object[r14];
        r15 = 0;
        r0 = r17;
        r0 = r0.fDoctypeName;
        r16 = r0;
        r14[r15] = r16;
        r15 = 1;
        r14[r15] = r8;
        r15 = 1;
        r11.reportError(r12, r13, r14, r15);
    L_0x0068:
        r0 = r17;
        r11 = r0.fElementStack;
        r0 = r17;
        r12 = r0.fElementQName;
        r11 = r11.pushElement(r12);
        r0 = r17;
        r0.fCurrentElement = r11;
        r3 = 0;
        r0 = r17;
        r11 = r0.fAttributes;
        r11.removeAllAttributes();
    L_0x0080:
        r0 = r17;
        r11 = r0.fEntityScanner;
        r9 = r11.skipSpaces();
        r0 = r17;
        r11 = r0.fEntityScanner;
        r2 = r11.peekChar();
        r11 = 62;
        if (r2 != r11) goto L_0x01f6;
    L_0x0094:
        r0 = r17;
        r11 = r0.fEntityScanner;
        r11.scanChar();
    L_0x009b:
        r0 = r17;
        r11 = r0.fBindNamespaces;
        if (r11 == 0) goto L_0x0196;
    L_0x00a1:
        r0 = r17;
        r11 = r0.fElementQName;
        r11 = r11.prefix;
        r12 = mf.org.apache.xerces.util.XMLSymbols.PREFIX_XMLNS;
        if (r11 != r12) goto L_0x00c9;
    L_0x00ab:
        r0 = r17;
        r11 = r0.fErrorReporter;
        r12 = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
        r13 = "ElementXMLNSPrefix";
        r14 = 1;
        r14 = new java.lang.Object[r14];
        r15 = 0;
        r0 = r17;
        r0 = r0.fElementQName;
        r16 = r0;
        r0 = r16;
        r0 = r0.rawname;
        r16 = r0;
        r14[r15] = r16;
        r15 = 2;
        r11.reportError(r12, r13, r14, r15);
    L_0x00c9:
        r0 = r17;
        r11 = r0.fElementQName;
        r11 = r11.prefix;
        if (r11 == 0) goto L_0x023f;
    L_0x00d1:
        r0 = r17;
        r11 = r0.fElementQName;
        r7 = r11.prefix;
    L_0x00d7:
        r0 = r17;
        r11 = r0.fElementQName;
        r0 = r17;
        r12 = r0.fNamespaceContext;
        r12 = r12.getURI(r7);
        r11.uri = r12;
        r0 = r17;
        r11 = r0.fCurrentElement;
        r0 = r17;
        r12 = r0.fElementQName;
        r12 = r12.uri;
        r11.uri = r12;
        r0 = r17;
        r11 = r0.fElementQName;
        r11 = r11.prefix;
        if (r11 != 0) goto L_0x0111;
    L_0x00f9:
        r0 = r17;
        r11 = r0.fElementQName;
        r11 = r11.uri;
        if (r11 == 0) goto L_0x0111;
    L_0x0101:
        r0 = r17;
        r11 = r0.fElementQName;
        r12 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r11.prefix = r12;
        r0 = r17;
        r11 = r0.fCurrentElement;
        r12 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r11.prefix = r12;
    L_0x0111:
        r0 = r17;
        r11 = r0.fElementQName;
        r11 = r11.prefix;
        if (r11 == 0) goto L_0x014e;
    L_0x0119:
        r0 = r17;
        r11 = r0.fElementQName;
        r11 = r11.uri;
        if (r11 != 0) goto L_0x014e;
    L_0x0121:
        r0 = r17;
        r11 = r0.fErrorReporter;
        r12 = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
        r13 = "ElementPrefixUnbound";
        r14 = 2;
        r14 = new java.lang.Object[r14];
        r15 = 0;
        r0 = r17;
        r0 = r0.fElementQName;
        r16 = r0;
        r0 = r16;
        r0 = r0.prefix;
        r16 = r0;
        r14[r15] = r16;
        r15 = 1;
        r0 = r17;
        r0 = r0.fElementQName;
        r16 = r0;
        r0 = r16;
        r0 = r0.rawname;
        r16 = r0;
        r14[r15] = r16;
        r15 = 2;
        r11.reportError(r12, r13, r14, r15);
    L_0x014e:
        r0 = r17;
        r11 = r0.fAttributes;
        r5 = r11.getLength();
        r4 = 0;
    L_0x0157:
        if (r4 < r5) goto L_0x0243;
    L_0x0159:
        r11 = 1;
        if (r5 <= r11) goto L_0x0196;
    L_0x015c:
        r0 = r17;
        r11 = r0.fAttributes;
        r6 = r11.checkDuplicatesNS();
        if (r6 == 0) goto L_0x0196;
    L_0x0166:
        r11 = r6.uri;
        if (r11 == 0) goto L_0x02bf;
    L_0x016a:
        r0 = r17;
        r11 = r0.fErrorReporter;
        r12 = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
        r13 = "AttributeNSNotUnique";
        r14 = 3;
        r14 = new java.lang.Object[r14];
        r15 = 0;
        r0 = r17;
        r0 = r0.fElementQName;
        r16 = r0;
        r0 = r16;
        r0 = r0.rawname;
        r16 = r0;
        r14[r15] = r16;
        r15 = 1;
        r0 = r6.localpart;
        r16 = r0;
        r14[r15] = r16;
        r15 = 2;
        r0 = r6.uri;
        r16 = r0;
        r14[r15] = r16;
        r15 = 2;
        r11.reportError(r12, r13, r14, r15);
    L_0x0196:
        r0 = r17;
        r11 = r0.fDocumentHandler;
        if (r11 == 0) goto L_0x01f5;
    L_0x019c:
        if (r3 == 0) goto L_0x02e6;
    L_0x019e:
        r0 = r17;
        r11 = r0.fMarkupDepth;
        r11 = r11 + -1;
        r0 = r17;
        r0.fMarkupDepth = r11;
        r0 = r17;
        r11 = r0.fMarkupDepth;
        r0 = r17;
        r12 = r0.fEntityStack;
        r0 = r17;
        r13 = r0.fEntityDepth;
        r13 = r13 + -1;
        r12 = r12[r13];
        if (r11 >= r12) goto L_0x01cd;
    L_0x01ba:
        r11 = "ElementEntityMismatch";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r0 = r17;
        r14 = r0.fCurrentElement;
        r14 = r14.rawname;
        r12[r13] = r14;
        r0 = r17;
        r0.reportFatalError(r11, r12);
    L_0x01cd:
        r0 = r17;
        r11 = r0.fDocumentHandler;
        r0 = r17;
        r12 = r0.fElementQName;
        r0 = r17;
        r13 = r0.fAttributes;
        r14 = 0;
        r11.emptyElement(r12, r13, r14);
        r0 = r17;
        r11 = r0.fBindNamespaces;
        if (r11 == 0) goto L_0x01ea;
    L_0x01e3:
        r0 = r17;
        r11 = r0.fNamespaceContext;
        r11.popContext();
    L_0x01ea:
        r0 = r17;
        r11 = r0.fElementStack;
        r0 = r17;
        r12 = r0.fElementQName;
        r11.popElement(r12);
    L_0x01f5:
        return r3;
    L_0x01f6:
        r11 = 47;
        if (r2 != r11) goto L_0x021d;
    L_0x01fa:
        r0 = r17;
        r11 = r0.fEntityScanner;
        r11.scanChar();
        r0 = r17;
        r11 = r0.fEntityScanner;
        r12 = 62;
        r11 = r11.skipChar(r12);
        if (r11 != 0) goto L_0x021a;
    L_0x020d:
        r11 = "ElementUnterminated";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r12[r13] = r8;
        r0 = r17;
        r0.reportFatalError(r11, r12);
    L_0x021a:
        r3 = 1;
        goto L_0x009b;
    L_0x021d:
        r0 = r17;
        r11 = r0.isValidNameStartChar(r2);
        if (r11 == 0) goto L_0x0227;
    L_0x0225:
        if (r9 != 0) goto L_0x0234;
    L_0x0227:
        r11 = "ElementUnterminated";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r12[r13] = r8;
        r0 = r17;
        r0.reportFatalError(r11, r12);
    L_0x0234:
        r0 = r17;
        r11 = r0.fAttributes;
        r0 = r17;
        r0.scanAttribute(r11);
        goto L_0x0080;
    L_0x023f:
        r7 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        goto L_0x00d7;
    L_0x0243:
        r0 = r17;
        r11 = r0.fAttributes;
        r0 = r17;
        r12 = r0.fAttributeQName;
        r11.getName(r4, r12);
        r0 = r17;
        r11 = r0.fAttributeQName;
        r11 = r11.prefix;
        if (r11 == 0) goto L_0x0278;
    L_0x0256:
        r0 = r17;
        r11 = r0.fAttributeQName;
        r1 = r11.prefix;
    L_0x025c:
        r0 = r17;
        r11 = r0.fNamespaceContext;
        r10 = r11.getURI(r1);
        r0 = r17;
        r11 = r0.fAttributeQName;
        r11 = r11.uri;
        if (r11 == 0) goto L_0x027b;
    L_0x026c:
        r0 = r17;
        r11 = r0.fAttributeQName;
        r11 = r11.uri;
        if (r11 != r10) goto L_0x027b;
    L_0x0274:
        r4 = r4 + 1;
        goto L_0x0157;
    L_0x0278:
        r1 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        goto L_0x025c;
    L_0x027b:
        r11 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        if (r1 == r11) goto L_0x0274;
    L_0x027f:
        r0 = r17;
        r11 = r0.fAttributeQName;
        r11.uri = r10;
        if (r10 != 0) goto L_0x02b7;
    L_0x0287:
        r0 = r17;
        r11 = r0.fErrorReporter;
        r12 = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
        r13 = "AttributePrefixUnbound";
        r14 = 3;
        r14 = new java.lang.Object[r14];
        r15 = 0;
        r0 = r17;
        r0 = r0.fElementQName;
        r16 = r0;
        r0 = r16;
        r0 = r0.rawname;
        r16 = r0;
        r14[r15] = r16;
        r15 = 1;
        r0 = r17;
        r0 = r0.fAttributeQName;
        r16 = r0;
        r0 = r16;
        r0 = r0.rawname;
        r16 = r0;
        r14[r15] = r16;
        r15 = 2;
        r14[r15] = r1;
        r15 = 2;
        r11.reportError(r12, r13, r14, r15);
    L_0x02b7:
        r0 = r17;
        r11 = r0.fAttributes;
        r11.setURI(r4, r10);
        goto L_0x0274;
    L_0x02bf:
        r0 = r17;
        r11 = r0.fErrorReporter;
        r12 = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
        r13 = "AttributeNotUnique";
        r14 = 2;
        r14 = new java.lang.Object[r14];
        r15 = 0;
        r0 = r17;
        r0 = r0.fElementQName;
        r16 = r0;
        r0 = r16;
        r0 = r0.rawname;
        r16 = r0;
        r14[r15] = r16;
        r15 = 1;
        r0 = r6.rawname;
        r16 = r0;
        r14[r15] = r16;
        r15 = 2;
        r11.reportError(r12, r13, r14, r15);
        goto L_0x0196;
    L_0x02e6:
        r0 = r17;
        r11 = r0.fDocumentHandler;
        r0 = r17;
        r12 = r0.fElementQName;
        r0 = r17;
        r13 = r0.fAttributes;
        r14 = 0;
        r11.startElement(r12, r13, r14);
        goto L_0x01f5;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLNSDocumentScannerImpl.scanStartElement():boolean");
    }

    protected void scanStartElementName() throws IOException, XNIException {
        this.fEntityScanner.scanQName(this.fElementQName);
        this.fSawSpace = this.fEntityScanner.skipSpaces();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean scanStartElementAfterName() throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r15 = this;
        r9 = r15.fElementQName;
        r7 = r9.rawname;
        r9 = r15.fBindNamespaces;
        if (r9 == 0) goto L_0x0047;
    L_0x0008:
        r9 = r15.fNamespaceContext;
        r9.pushContext();
        r9 = r15.fScannerState;
        r10 = 6;
        if (r9 != r10) goto L_0x0047;
    L_0x0012:
        r9 = r15.fPerformValidation;
        if (r9 == 0) goto L_0x0047;
    L_0x0016:
        r9 = r15.fErrorReporter;
        r10 = "http://www.w3.org/TR/1998/REC-xml-19980210";
        r11 = "MSG_GRAMMAR_NOT_FOUND";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r12[r13] = r7;
        r13 = 1;
        r9.reportError(r10, r11, r12, r13);
        r9 = r15.fDoctypeName;
        if (r9 == 0) goto L_0x0032;
    L_0x002a:
        r9 = r15.fDoctypeName;
        r9 = r9.equals(r7);
        if (r9 != 0) goto L_0x0047;
    L_0x0032:
        r9 = r15.fErrorReporter;
        r10 = "http://www.w3.org/TR/1998/REC-xml-19980210";
        r11 = "RootElementTypeMustMatchDoctypedecl";
        r12 = 2;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r14 = r15.fDoctypeName;
        r12[r13] = r14;
        r13 = 1;
        r12[r13] = r7;
        r13 = 1;
        r9.reportError(r10, r11, r12, r13);
    L_0x0047:
        r9 = r15.fElementStack;
        r10 = r15.fElementQName;
        r9 = r9.pushElement(r10);
        r15.fCurrentElement = r9;
        r2 = 0;
        r9 = r15.fAttributes;
        r9.removeAllAttributes();
    L_0x0057:
        r9 = r15.fEntityScanner;
        r1 = r9.peekChar();
        r9 = 62;
        if (r1 != r9) goto L_0x0159;
    L_0x0061:
        r9 = r15.fEntityScanner;
        r9.scanChar();
    L_0x0066:
        r9 = r15.fBindNamespaces;
        if (r9 == 0) goto L_0x0117;
    L_0x006a:
        r9 = r15.fElementQName;
        r9 = r9.prefix;
        r10 = mf.org.apache.xerces.util.XMLSymbols.PREFIX_XMLNS;
        if (r9 != r10) goto L_0x0086;
    L_0x0072:
        r9 = r15.fErrorReporter;
        r10 = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
        r11 = "ElementXMLNSPrefix";
        r12 = 1;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r14 = r15.fElementQName;
        r14 = r14.rawname;
        r12[r13] = r14;
        r13 = 2;
        r9.reportError(r10, r11, r12, r13);
    L_0x0086:
        r9 = r15.fElementQName;
        r9 = r9.prefix;
        if (r9 == 0) goto L_0x019e;
    L_0x008c:
        r9 = r15.fElementQName;
        r6 = r9.prefix;
    L_0x0090:
        r9 = r15.fElementQName;
        r10 = r15.fNamespaceContext;
        r10 = r10.getURI(r6);
        r9.uri = r10;
        r9 = r15.fCurrentElement;
        r10 = r15.fElementQName;
        r10 = r10.uri;
        r9.uri = r10;
        r9 = r15.fElementQName;
        r9 = r9.prefix;
        if (r9 != 0) goto L_0x00ba;
    L_0x00a8:
        r9 = r15.fElementQName;
        r9 = r9.uri;
        if (r9 == 0) goto L_0x00ba;
    L_0x00ae:
        r9 = r15.fElementQName;
        r10 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r9.prefix = r10;
        r9 = r15.fCurrentElement;
        r10 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        r9.prefix = r10;
    L_0x00ba:
        r9 = r15.fElementQName;
        r9 = r9.prefix;
        if (r9 == 0) goto L_0x00e1;
    L_0x00c0:
        r9 = r15.fElementQName;
        r9 = r9.uri;
        if (r9 != 0) goto L_0x00e1;
    L_0x00c6:
        r9 = r15.fErrorReporter;
        r10 = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
        r11 = "ElementPrefixUnbound";
        r12 = 2;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r14 = r15.fElementQName;
        r14 = r14.prefix;
        r12[r13] = r14;
        r13 = 1;
        r14 = r15.fElementQName;
        r14 = r14.rawname;
        r12[r13] = r14;
        r13 = 2;
        r9.reportError(r10, r11, r12, r13);
    L_0x00e1:
        r9 = r15.fAttributes;
        r4 = r9.getLength();
        r3 = 0;
    L_0x00e8:
        if (r3 < r4) goto L_0x01a2;
    L_0x00ea:
        r9 = 1;
        if (r4 <= r9) goto L_0x0117;
    L_0x00ed:
        r9 = r15.fAttributes;
        r5 = r9.checkDuplicatesNS();
        if (r5 == 0) goto L_0x0117;
    L_0x00f5:
        r9 = r5.uri;
        if (r9 == 0) goto L_0x01fa;
    L_0x00f9:
        r9 = r15.fErrorReporter;
        r10 = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
        r11 = "AttributeNSNotUnique";
        r12 = 3;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r14 = r15.fElementQName;
        r14 = r14.rawname;
        r12[r13] = r14;
        r13 = 1;
        r14 = r5.localpart;
        r12[r13] = r14;
        r13 = 2;
        r14 = r5.uri;
        r12[r13] = r14;
        r13 = 2;
        r9.reportError(r10, r11, r12, r13);
    L_0x0117:
        r9 = r15.fDocumentHandler;
        if (r9 == 0) goto L_0x0158;
    L_0x011b:
        if (r2 == 0) goto L_0x0215;
    L_0x011d:
        r9 = r15.fMarkupDepth;
        r9 = r9 + -1;
        r15.fMarkupDepth = r9;
        r9 = r15.fMarkupDepth;
        r10 = r15.fEntityStack;
        r11 = r15.fEntityDepth;
        r11 = r11 + -1;
        r10 = r10[r11];
        if (r9 >= r10) goto L_0x013e;
    L_0x012f:
        r9 = "ElementEntityMismatch";
        r10 = 1;
        r10 = new java.lang.Object[r10];
        r11 = 0;
        r12 = r15.fCurrentElement;
        r12 = r12.rawname;
        r10[r11] = r12;
        r15.reportFatalError(r9, r10);
    L_0x013e:
        r9 = r15.fDocumentHandler;
        r10 = r15.fElementQName;
        r11 = r15.fAttributes;
        r12 = 0;
        r9.emptyElement(r10, r11, r12);
        r9 = r15.fBindNamespaces;
        if (r9 == 0) goto L_0x0151;
    L_0x014c:
        r9 = r15.fNamespaceContext;
        r9.popContext();
    L_0x0151:
        r9 = r15.fElementStack;
        r10 = r15.fElementQName;
        r9.popElement(r10);
    L_0x0158:
        return r2;
    L_0x0159:
        r9 = 47;
        if (r1 != r9) goto L_0x017a;
    L_0x015d:
        r9 = r15.fEntityScanner;
        r9.scanChar();
        r9 = r15.fEntityScanner;
        r10 = 62;
        r9 = r9.skipChar(r10);
        if (r9 != 0) goto L_0x0177;
    L_0x016c:
        r9 = "ElementUnterminated";
        r10 = 1;
        r10 = new java.lang.Object[r10];
        r11 = 0;
        r10[r11] = r7;
        r15.reportFatalError(r9, r10);
    L_0x0177:
        r2 = 1;
        goto L_0x0066;
    L_0x017a:
        r9 = r15.isValidNameStartChar(r1);
        if (r9 == 0) goto L_0x0184;
    L_0x0180:
        r9 = r15.fSawSpace;
        if (r9 != 0) goto L_0x018f;
    L_0x0184:
        r9 = "ElementUnterminated";
        r10 = 1;
        r10 = new java.lang.Object[r10];
        r11 = 0;
        r10[r11] = r7;
        r15.reportFatalError(r9, r10);
    L_0x018f:
        r9 = r15.fAttributes;
        r15.scanAttribute(r9);
        r9 = r15.fEntityScanner;
        r9 = r9.skipSpaces();
        r15.fSawSpace = r9;
        goto L_0x0057;
    L_0x019e:
        r6 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        goto L_0x0090;
    L_0x01a2:
        r9 = r15.fAttributes;
        r10 = r15.fAttributeQName;
        r9.getName(r3, r10);
        r9 = r15.fAttributeQName;
        r9 = r9.prefix;
        if (r9 == 0) goto L_0x01c9;
    L_0x01af:
        r9 = r15.fAttributeQName;
        r0 = r9.prefix;
    L_0x01b3:
        r9 = r15.fNamespaceContext;
        r8 = r9.getURI(r0);
        r9 = r15.fAttributeQName;
        r9 = r9.uri;
        if (r9 == 0) goto L_0x01cc;
    L_0x01bf:
        r9 = r15.fAttributeQName;
        r9 = r9.uri;
        if (r9 != r8) goto L_0x01cc;
    L_0x01c5:
        r3 = r3 + 1;
        goto L_0x00e8;
    L_0x01c9:
        r0 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        goto L_0x01b3;
    L_0x01cc:
        r9 = mf.org.apache.xerces.util.XMLSymbols.EMPTY_STRING;
        if (r0 == r9) goto L_0x01c5;
    L_0x01d0:
        r9 = r15.fAttributeQName;
        r9.uri = r8;
        if (r8 != 0) goto L_0x01f4;
    L_0x01d6:
        r9 = r15.fErrorReporter;
        r10 = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
        r11 = "AttributePrefixUnbound";
        r12 = 3;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r14 = r15.fElementQName;
        r14 = r14.rawname;
        r12[r13] = r14;
        r13 = 1;
        r14 = r15.fAttributeQName;
        r14 = r14.rawname;
        r12[r13] = r14;
        r13 = 2;
        r12[r13] = r0;
        r13 = 2;
        r9.reportError(r10, r11, r12, r13);
    L_0x01f4:
        r9 = r15.fAttributes;
        r9.setURI(r3, r8);
        goto L_0x01c5;
    L_0x01fa:
        r9 = r15.fErrorReporter;
        r10 = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
        r11 = "AttributeNotUnique";
        r12 = 2;
        r12 = new java.lang.Object[r12];
        r13 = 0;
        r14 = r15.fElementQName;
        r14 = r14.rawname;
        r12[r13] = r14;
        r13 = 1;
        r14 = r5.rawname;
        r12[r13] = r14;
        r13 = 2;
        r9.reportError(r10, r11, r12, r13);
        goto L_0x0117;
    L_0x0215:
        r9 = r15.fDocumentHandler;
        r10 = r15.fElementQName;
        r11 = r15.fAttributes;
        r12 = 0;
        r9.startElement(r10, r11, r12);
        goto L_0x0158;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLNSDocumentScannerImpl.scanStartElementAfterName():boolean");
    }

    protected void scanAttribute(XMLAttributesImpl attributes) throws IOException, XNIException {
        int attrIndex;
        this.fEntityScanner.scanQName(this.fAttributeQName);
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(61)) {
            reportFatalError("EqRequiredInAttribute", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
        }
        this.fEntityScanner.skipSpaces();
        if (this.fBindNamespaces) {
            attrIndex = attributes.getLength();
            attributes.addAttributeNS(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
        } else {
            int oldLen = attributes.getLength();
            attrIndex = attributes.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
            if (oldLen == attributes.getLength()) {
                reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
            }
        }
        boolean isSameNormalizedAttr = scanAttributeValue(this.fTempString, this.fTempString2, this.fAttributeQName.rawname, this.fIsEntityDeclaredVC, this.fCurrentElement.rawname);
        String value = this.fTempString.toString();
        attributes.setValue(attrIndex, value);
        if (!isSameNormalizedAttr) {
            attributes.setNonNormalizedValue(attrIndex, this.fTempString2.toString());
        }
        attributes.setSpecified(attrIndex, true);
        if (this.fBindNamespaces) {
            String localpart = this.fAttributeQName.localpart;
            String prefix = this.fAttributeQName.prefix != null ? this.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
            if (prefix == XMLSymbols.PREFIX_XMLNS || (prefix == XMLSymbols.EMPTY_STRING && localpart == XMLSymbols.PREFIX_XMLNS)) {
                String uri = this.fSymbolTable.addSymbol(value);
                if (prefix == XMLSymbols.PREFIX_XMLNS && localpart == XMLSymbols.PREFIX_XMLNS) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXMLNS", new Object[]{this.fAttributeQName}, (short) 2);
                }
                if (uri == NamespaceContext.XMLNS_URI) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXMLNS", new Object[]{this.fAttributeQName}, (short) 2);
                }
                if (localpart == XMLSymbols.PREFIX_XML) {
                    if (uri != NamespaceContext.XML_URI) {
                        this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXML", new Object[]{this.fAttributeQName}, (short) 2);
                    }
                } else if (uri == NamespaceContext.XML_URI) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXML", new Object[]{this.fAttributeQName}, (short) 2);
                }
                prefix = localpart != XMLSymbols.PREFIX_XMLNS ? localpart : XMLSymbols.EMPTY_STRING;
                if (uri == XMLSymbols.EMPTY_STRING && localpart != XMLSymbols.PREFIX_XMLNS) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "EmptyPrefixedAttName", new Object[]{this.fAttributeQName}, (short) 2);
                }
                NamespaceContext namespaceContext = this.fNamespaceContext;
                if (uri.length() == 0) {
                    uri = null;
                }
                namespaceContext.declarePrefix(prefix, uri);
                attributes.setURI(attrIndex, this.fNamespaceContext.getURI(XMLSymbols.PREFIX_XMLNS));
            } else if (this.fAttributeQName.prefix != null) {
                attributes.setURI(attrIndex, this.fNamespaceContext.getURI(this.fAttributeQName.prefix));
            }
        }
    }

    protected int scanEndElement() throws IOException, XNIException {
        this.fElementStack.popElement(this.fElementQName);
        if (!this.fEntityScanner.skipString(this.fElementQName.rawname)) {
            reportFatalError("ETagRequired", new Object[]{this.fElementQName.rawname});
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("ETagUnterminated", new Object[]{this.fElementQName.rawname});
        }
        this.fMarkupDepth--;
        this.fMarkupDepth--;
        if (this.fMarkupDepth < this.fEntityStack[this.fEntityDepth - 1]) {
            reportFatalError("ElementEntityMismatch", new Object[]{this.fCurrentElement.rawname});
        }
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endElement(this.fElementQName, null);
            if (this.fBindNamespaces) {
                this.fNamespaceContext.popContext();
            }
        }
        return this.fMarkupDepth;
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        super.reset(componentManager);
        this.fPerformValidation = false;
        this.fBindNamespaces = false;
    }

    protected Dispatcher createContentDispatcher() {
        return new NSContentDispatcher();
    }
}
