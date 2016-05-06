package mf.org.apache.xerces.impl;

import android.support.v4.media.TransportMediator;
import java.io.IOException;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XMLSchemaValidator;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.XMLDTDContentModelHandler;
import mf.org.apache.xerces.xni.XMLDTDHandler;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDTDScanner;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import mf.org.apache.xml.serialize.Method;

public class XMLDTDScannerImpl extends XMLScanner implements XMLDTDScanner, XMLComponent, XMLEntityHandler {
    private static final boolean DEBUG_SCANNER_STATE = false;
    private static final Boolean[] FEATURE_DEFAULTS;
    private static final Object[] PROPERTY_DEFAULTS;
    private static final String[] RECOGNIZED_FEATURES;
    private static final String[] RECOGNIZED_PROPERTIES;
    protected static final int SCANNER_STATE_END_OF_INPUT = 0;
    protected static final int SCANNER_STATE_MARKUP_DECL = 2;
    protected static final int SCANNER_STATE_TEXT_DECL = 1;
    private int fContentDepth;
    private int[] fContentStack;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected XMLDTDHandler fDTDHandler;
    private String[] fEnumeration;
    private int fEnumerationCount;
    private int fExtEntityDepth;
    private final XMLStringBuffer fIgnoreConditionalBuffer;
    private int fIncludeSectDepth;
    private final XMLString fLiteral;
    private final XMLString fLiteral2;
    private int fMarkUpDepth;
    private int fPEDepth;
    private boolean[] fPEReport;
    private int[] fPEStack;
    protected int fScannerState;
    protected boolean fSeenExternalDTD;
    protected boolean fSeenPEReferences;
    protected boolean fStandalone;
    private boolean fStartDTDCalled;
    private final XMLString fString;
    private final XMLStringBuffer fStringBuffer;
    private final XMLStringBuffer fStringBuffer2;
    private final String[] fStrings;

    static {
        String[] strArr = new String[SCANNER_STATE_MARKUP_DECL];
        strArr[SCANNER_STATE_END_OF_INPUT] = "http://xml.org/sax/features/validation";
        strArr[SCANNER_STATE_TEXT_DECL] = "http://apache.org/xml/features/scanner/notify-char-refs";
        RECOGNIZED_FEATURES = strArr;
        Boolean[] boolArr = new Boolean[SCANNER_STATE_MARKUP_DECL];
        boolArr[SCANNER_STATE_TEXT_DECL] = Boolean.FALSE;
        FEATURE_DEFAULTS = boolArr;
        RECOGNIZED_PROPERTIES = new String[]{XMLSchemaValidator.SYMBOL_TABLE, XMLSchemaValidator.ERROR_REPORTER, "http://apache.org/xml/properties/internal/entity-manager"};
        PROPERTY_DEFAULTS = new Object[3];
    }

    public XMLDTDScannerImpl() {
        this.fContentStack = new int[5];
        this.fPEStack = new int[5];
        this.fPEReport = new boolean[5];
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fLiteral = new XMLString();
        this.fLiteral2 = new XMLString();
        this.fEnumeration = new String[5];
        this.fIgnoreConditionalBuffer = new XMLStringBuffer((int) TransportMediator.FLAG_KEY_MEDIA_NEXT);
    }

    public XMLDTDScannerImpl(SymbolTable symbolTable, XMLErrorReporter errorReporter, XMLEntityManager entityManager) {
        this.fContentStack = new int[5];
        this.fPEStack = new int[5];
        this.fPEReport = new boolean[5];
        this.fStrings = new String[3];
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fLiteral = new XMLString();
        this.fLiteral2 = new XMLString();
        this.fEnumeration = new String[5];
        this.fIgnoreConditionalBuffer = new XMLStringBuffer((int) TransportMediator.FLAG_KEY_MEDIA_NEXT);
        this.fSymbolTable = symbolTable;
        this.fErrorReporter = errorReporter;
        this.fEntityManager = entityManager;
        entityManager.setProperty(XMLSchemaValidator.SYMBOL_TABLE, this.fSymbolTable);
    }

    public void setInputSource(XMLInputSource inputSource) throws IOException {
        if (inputSource != null) {
            this.fEntityManager.setEntityHandler(this);
            this.fEntityManager.startDTDEntity(inputSource);
        } else if (this.fDTDHandler != null) {
            this.fDTDHandler.startDTD(null, null);
            this.fDTDHandler.endDTD(null);
        }
    }

    public boolean scanDTDExternalSubset(boolean complete) throws IOException, XNIException {
        this.fEntityManager.setEntityHandler(this);
        if (this.fScannerState == SCANNER_STATE_TEXT_DECL) {
            this.fSeenExternalDTD = true;
            boolean textDecl = scanTextDecl();
            if (this.fScannerState == 0) {
                return DEBUG_SCANNER_STATE;
            }
            setScannerState(SCANNER_STATE_MARKUP_DECL);
            if (textDecl && !complete) {
                return true;
            }
        }
        while (scanDecls(complete)) {
            if (!complete) {
                return true;
            }
        }
        return DEBUG_SCANNER_STATE;
    }

    public boolean scanDTDInternalSubset(boolean complete, boolean standalone, boolean hasExternalSubset) throws IOException, XNIException {
        this.fEntityScanner = this.fEntityManager.getEntityScanner();
        this.fEntityManager.setEntityHandler(this);
        this.fStandalone = standalone;
        if (this.fScannerState == SCANNER_STATE_TEXT_DECL) {
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startDTD(this.fEntityScanner, null);
                this.fStartDTDCalled = true;
            }
            setScannerState(SCANNER_STATE_MARKUP_DECL);
        }
        while (scanDecls(complete)) {
            if (!complete) {
                return true;
            }
        }
        if (!(this.fDTDHandler == null || hasExternalSubset)) {
            this.fDTDHandler.endDTD(null);
        }
        setScannerState(SCANNER_STATE_TEXT_DECL);
        return DEBUG_SCANNER_STATE;
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        super.reset(componentManager);
        init();
    }

    public void reset() {
        super.reset();
        init();
    }

    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public Boolean getFeatureDefault(String featureId) {
        for (int i = SCANNER_STATE_END_OF_INPUT; i < RECOGNIZED_FEATURES.length; i += SCANNER_STATE_TEXT_DECL) {
            if (RECOGNIZED_FEATURES[i].equals(featureId)) {
                return FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }

    public Object getPropertyDefault(String propertyId) {
        for (int i = SCANNER_STATE_END_OF_INPUT; i < RECOGNIZED_PROPERTIES.length; i += SCANNER_STATE_TEXT_DECL) {
            if (RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }

    public void setDTDHandler(XMLDTDHandler dtdHandler) {
        this.fDTDHandler = dtdHandler;
    }

    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    public void setDTDContentModelHandler(XMLDTDContentModelHandler dtdContentModelHandler) {
        this.fDTDContentModelHandler = dtdContentModelHandler;
    }

    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }

    public void startEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        super.startEntity(name, identifier, encoding, augs);
        boolean dtdEntity = name.equals("[dtd]");
        if (dtdEntity) {
            if (!(this.fDTDHandler == null || this.fStartDTDCalled)) {
                this.fDTDHandler.startDTD(this.fEntityScanner, null);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startExternalSubset(identifier, null);
            }
            this.fEntityManager.startExternalSubset();
            this.fExtEntityDepth += SCANNER_STATE_TEXT_DECL;
        } else if (name.charAt(SCANNER_STATE_END_OF_INPUT) == '%') {
            pushPEStack(this.fMarkUpDepth, this.fReportEntity);
            if (this.fEntityScanner.isExternal()) {
                this.fExtEntityDepth += SCANNER_STATE_TEXT_DECL;
            }
        }
        if (this.fDTDHandler != null && !dtdEntity && this.fReportEntity) {
            this.fDTDHandler.startParameterEntity(name, identifier, encoding, augs);
        }
    }

    public void endEntity(String name, Augmentations augs) throws XNIException {
        super.endEntity(name, augs);
        if (this.fScannerState != 0) {
            boolean reportEntity = this.fReportEntity;
            if (name.startsWith("%")) {
                Object[] objArr;
                reportEntity = peekReportEntity();
                int startMarkUpDepth = popPEStack();
                if (startMarkUpDepth == 0 && startMarkUpDepth < this.fMarkUpDepth) {
                    objArr = new Object[SCANNER_STATE_TEXT_DECL];
                    objArr[SCANNER_STATE_END_OF_INPUT] = this.fEntityManager.fCurrentEntity.name;
                    this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "ILL_FORMED_PARAMETER_ENTITY_WHEN_USED_IN_DECL", objArr, (short) 2);
                }
                if (startMarkUpDepth != this.fMarkUpDepth) {
                    reportEntity = DEBUG_SCANNER_STATE;
                    if (this.fValidation) {
                        objArr = new Object[SCANNER_STATE_TEXT_DECL];
                        objArr[SCANNER_STATE_END_OF_INPUT] = name;
                        this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "ImproperDeclarationNesting", objArr, (short) 1);
                    }
                }
                if (this.fEntityScanner.isExternal()) {
                    this.fExtEntityDepth--;
                }
                if (this.fDTDHandler != null && reportEntity) {
                    this.fDTDHandler.endParameterEntity(name, augs);
                }
            } else if (name.equals("[dtd]")) {
                if (this.fIncludeSectDepth != 0) {
                    reportFatalError("IncludeSectUnterminated", null);
                }
                this.fScannerState = SCANNER_STATE_END_OF_INPUT;
                this.fEntityManager.endExternalSubset();
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endExternalSubset(null);
                    this.fDTDHandler.endDTD(null);
                }
                this.fExtEntityDepth--;
            }
        }
    }

    protected final void setScannerState(int state) {
        this.fScannerState = state;
    }

    private static String getScannerStateName(int state) {
        return "??? (" + state + ')';
    }

    protected final boolean scanningInternalSubset() {
        return this.fExtEntityDepth == 0 ? true : DEBUG_SCANNER_STATE;
    }

    protected void startPE(String name, boolean literal) throws IOException, XNIException {
        int depth = this.fPEDepth;
        String pName = "%" + name;
        if (!this.fSeenPEReferences) {
            this.fSeenPEReferences = true;
            this.fEntityManager.notifyHasPEReferences();
        }
        if (this.fValidation && !this.fEntityManager.isDeclaredEntity(pName)) {
            Object[] objArr = new Object[SCANNER_STATE_TEXT_DECL];
            objArr[SCANNER_STATE_END_OF_INPUT] = name;
            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "EntityNotDeclared", objArr, (short) 1);
        }
        this.fEntityManager.startEntity(this.fSymbolTable.addSymbol(pName), literal);
        if (depth != this.fPEDepth && this.fEntityScanner.isExternal()) {
            scanTextDecl();
        }
    }

    protected final boolean scanTextDecl() throws IOException, XNIException {
        boolean textDecl = DEBUG_SCANNER_STATE;
        if (this.fEntityScanner.skipString("<?xml")) {
            this.fMarkUpDepth += SCANNER_STATE_TEXT_DECL;
            if (isValidNameChar(this.fEntityScanner.peekChar())) {
                this.fStringBuffer.clear();
                this.fStringBuffer.append(Method.XML);
                if (this.fNamespaces) {
                    while (isValidNCName(this.fEntityScanner.peekChar())) {
                        this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
                    }
                } else {
                    while (isValidNameChar(this.fEntityScanner.peekChar())) {
                        this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
                    }
                }
                scanPIData(this.fSymbolTable.addSymbol(this.fStringBuffer.ch, this.fStringBuffer.offset, this.fStringBuffer.length), this.fString);
            } else {
                scanXMLDeclOrTextDecl(true, this.fStrings);
                textDecl = true;
                this.fMarkUpDepth--;
                String version = this.fStrings[SCANNER_STATE_END_OF_INPUT];
                String encoding = this.fStrings[SCANNER_STATE_TEXT_DECL];
                this.fEntityScanner.setXMLVersion(version);
                if (!this.fEntityScanner.fCurrentEntity.isEncodingExternallySpecified()) {
                    this.fEntityScanner.setEncoding(encoding);
                }
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.textDecl(version, encoding, null);
                }
            }
        }
        this.fEntityManager.fCurrentEntity.mayReadChunks = true;
        return textDecl;
    }

    protected final void scanPIData(String target, XMLString data) throws IOException, XNIException {
        super.scanPIData(target, data);
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.processingInstruction(target, data, null);
        }
    }

    protected final void scanComment() throws IOException, XNIException {
        this.fReportEntity = DEBUG_SCANNER_STATE;
        scanComment(this.fStringBuffer);
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.comment(this.fStringBuffer, null);
        }
        this.fReportEntity = true;
    }

    protected final void scanElementDecl() throws IOException, XNIException {
        boolean z;
        String contentModel;
        this.fReportEntity = DEBUG_SCANNER_STATE;
        if (scanningInternalSubset()) {
            z = DEBUG_SCANNER_STATE;
        } else {
            z = true;
        }
        if (!skipSeparator(true, z)) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ELEMENTDECL", null);
        }
        String name = this.fEntityScanner.scanName();
        if (name == null) {
            reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ELEMENTDECL", null);
        }
        if (scanningInternalSubset()) {
            z = DEBUG_SCANNER_STATE;
        } else {
            z = true;
        }
        if (!skipSeparator(true, z)) {
            Object[] objArr = new Object[SCANNER_STATE_TEXT_DECL];
            objArr[SCANNER_STATE_END_OF_INPUT] = name;
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_CONTENTSPEC_IN_ELEMENTDECL", objArr);
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.startContentModel(name, null);
        }
        this.fReportEntity = true;
        if (this.fEntityScanner.skipString("EMPTY")) {
            contentModel = "EMPTY";
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.empty(null);
            }
        } else if (this.fEntityScanner.skipString("ANY")) {
            contentModel = "ANY";
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.any(null);
            }
        } else {
            if (!this.fEntityScanner.skipChar(40)) {
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = name;
                reportFatalError("MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN", objArr);
            }
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.startGroup(null);
            }
            this.fStringBuffer.clear();
            this.fStringBuffer.append('(');
            this.fMarkUpDepth += SCANNER_STATE_TEXT_DECL;
            skipSeparator(DEBUG_SCANNER_STATE, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true);
            if (this.fEntityScanner.skipString("#PCDATA")) {
                scanMixed(name);
            } else {
                scanChildren(name);
            }
            contentModel = this.fStringBuffer.toString();
        }
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.endContentModel(null);
        }
        this.fReportEntity = DEBUG_SCANNER_STATE;
        skipSeparator(DEBUG_SCANNER_STATE, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true);
        if (!this.fEntityScanner.skipChar(62)) {
            objArr = new Object[SCANNER_STATE_TEXT_DECL];
            objArr[SCANNER_STATE_END_OF_INPUT] = name;
            reportFatalError("ElementDeclUnterminated", objArr);
        }
        this.fReportEntity = true;
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fDTDHandler.elementDecl(name, contentModel, null);
        }
    }

    private final void scanMixed(String elName) throws IOException, XNIException {
        String childName = null;
        this.fStringBuffer.append("#PCDATA");
        if (this.fDTDContentModelHandler != null) {
            this.fDTDContentModelHandler.pcdata(null);
        }
        skipSeparator(DEBUG_SCANNER_STATE, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true);
        while (this.fEntityScanner.skipChar(124)) {
            boolean z;
            this.fStringBuffer.append('|');
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.separator((short) 0, null);
            }
            skipSeparator(DEBUG_SCANNER_STATE, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true);
            childName = this.fEntityScanner.scanName();
            if (childName == null) {
                Object[] objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = elName;
                reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_MIXED_CONTENT", objArr);
            }
            this.fStringBuffer.append(childName);
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.element(childName, null);
            }
            if (scanningInternalSubset()) {
                z = DEBUG_SCANNER_STATE;
            } else {
                z = true;
            }
            skipSeparator(DEBUG_SCANNER_STATE, z);
        }
        if (this.fEntityScanner.skipString(")*")) {
            this.fStringBuffer.append(")*");
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.endGroup(null);
                this.fDTDContentModelHandler.occurrence((short) 3, null);
            }
        } else if (childName != null) {
            r3 = new Object[SCANNER_STATE_TEXT_DECL];
            r3[SCANNER_STATE_END_OF_INPUT] = elName;
            reportFatalError("MixedContentUnterminated", r3);
        } else if (this.fEntityScanner.skipChar(41)) {
            this.fStringBuffer.append(')');
            if (this.fDTDContentModelHandler != null) {
                this.fDTDContentModelHandler.endGroup(null);
            }
        } else {
            r3 = new Object[SCANNER_STATE_TEXT_DECL];
            r3[SCANNER_STATE_END_OF_INPUT] = elName;
            reportFatalError("MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN", r3);
        }
        this.fMarkUpDepth--;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void scanChildren(java.lang.String r12) throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r11 = this;
        r10 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r9 = 44;
        r6 = 1;
        r8 = 0;
        r5 = 0;
        r11.fContentDepth = r5;
        r11.pushContentStack(r5);
        r2 = 0;
    L_0x000d:
        r4 = r11.fEntityScanner;
        r7 = 40;
        r4 = r4.skipChar(r7);
        if (r4 == 0) goto L_0x003e;
    L_0x0017:
        r4 = r11.fMarkUpDepth;
        r4 = r4 + 1;
        r11.fMarkUpDepth = r4;
        r4 = r11.fStringBuffer;
        r7 = 40;
        r4.append(r7);
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x002d;
    L_0x0028:
        r4 = r11.fDTDContentModelHandler;
        r4.startGroup(r8);
    L_0x002d:
        r11.pushContentStack(r2);
        r2 = 0;
        r4 = r11.scanningInternalSubset();
        if (r4 == 0) goto L_0x003c;
    L_0x0037:
        r4 = r5;
    L_0x0038:
        r11.skipSeparator(r5, r4);
        goto L_0x000d;
    L_0x003c:
        r4 = r6;
        goto L_0x0038;
    L_0x003e:
        r4 = r11.scanningInternalSubset();
        if (r4 == 0) goto L_0x005a;
    L_0x0044:
        r4 = r5;
    L_0x0045:
        r11.skipSeparator(r5, r4);
        r4 = r11.fEntityScanner;
        r1 = r4.scanName();
        if (r1 != 0) goto L_0x005c;
    L_0x0050:
        r4 = "MSG_OPEN_PAREN_OR_ELEMENT_TYPE_REQUIRED_IN_CHILDREN";
        r6 = new java.lang.Object[r6];
        r6[r5] = r12;
        r11.reportFatalError(r4, r6);
    L_0x0059:
        return;
    L_0x005a:
        r4 = r6;
        goto L_0x0045;
    L_0x005c:
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x0065;
    L_0x0060:
        r4 = r11.fDTDContentModelHandler;
        r4.element(r1, r8);
    L_0x0065:
        r4 = r11.fStringBuffer;
        r4.append(r1);
        r4 = r11.fEntityScanner;
        r0 = r4.peekChar();
        r4 = 63;
        if (r0 == r4) goto L_0x007c;
    L_0x0074:
        r4 = 42;
        if (r0 == r4) goto L_0x007c;
    L_0x0078:
        r4 = 43;
        if (r0 != r4) goto L_0x0095;
    L_0x007c:
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x008a;
    L_0x0080:
        r4 = 63;
        if (r0 != r4) goto L_0x00c9;
    L_0x0084:
        r3 = 2;
    L_0x0085:
        r4 = r11.fDTDContentModelHandler;
        r4.occurrence(r3, r8);
    L_0x008a:
        r4 = r11.fEntityScanner;
        r4.scanChar();
        r4 = r11.fStringBuffer;
        r7 = (char) r0;
        r4.append(r7);
    L_0x0095:
        r4 = r11.scanningInternalSubset();
        if (r4 == 0) goto L_0x00d1;
    L_0x009b:
        r4 = r5;
    L_0x009c:
        r11.skipSeparator(r5, r4);
        r4 = r11.fEntityScanner;
        r0 = r4.peekChar();
        if (r0 != r9) goto L_0x00d3;
    L_0x00a7:
        if (r2 == r10) goto L_0x00d3;
    L_0x00a9:
        r2 = r0;
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x00b3;
    L_0x00ae:
        r4 = r11.fDTDContentModelHandler;
        r4.separator(r6, r8);
    L_0x00b3:
        r4 = r11.fEntityScanner;
        r4.scanChar();
        r4 = r11.fStringBuffer;
        r4.append(r9);
    L_0x00bd:
        r4 = r11.scanningInternalSubset();
        if (r4 == 0) goto L_0x0172;
    L_0x00c3:
        r4 = r5;
    L_0x00c4:
        r11.skipSeparator(r5, r4);
        goto L_0x000d;
    L_0x00c9:
        r4 = 42;
        if (r0 != r4) goto L_0x00cf;
    L_0x00cd:
        r3 = 3;
        goto L_0x0085;
    L_0x00cf:
        r3 = 4;
        goto L_0x0085;
    L_0x00d1:
        r4 = r6;
        goto L_0x009c;
    L_0x00d3:
        if (r0 != r10) goto L_0x00ec;
    L_0x00d5:
        if (r2 == r9) goto L_0x00ec;
    L_0x00d7:
        r2 = r0;
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x00e1;
    L_0x00dc:
        r4 = r11.fDTDContentModelHandler;
        r4.separator(r5, r8);
    L_0x00e1:
        r4 = r11.fEntityScanner;
        r4.scanChar();
        r4 = r11.fStringBuffer;
        r4.append(r10);
        goto L_0x00bd;
    L_0x00ec:
        r4 = 41;
        if (r0 == r4) goto L_0x00f9;
    L_0x00f0:
        r4 = "MSG_CLOSE_PAREN_REQUIRED_IN_CHILDREN";
        r7 = new java.lang.Object[r6];
        r7[r5] = r12;
        r11.reportFatalError(r4, r7);
    L_0x00f9:
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x0102;
    L_0x00fd:
        r4 = r11.fDTDContentModelHandler;
        r4.endGroup(r8);
    L_0x0102:
        r2 = r11.popContentStack();
        r4 = r11.fEntityScanner;
        r7 = ")?";
        r4 = r4.skipString(r7);
        if (r4 == 0) goto L_0x012d;
    L_0x0110:
        r4 = r11.fStringBuffer;
        r7 = ")?";
        r4.append(r7);
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x0121;
    L_0x011b:
        r3 = 2;
        r4 = r11.fDTDContentModelHandler;
        r4.occurrence(r3, r8);
    L_0x0121:
        r4 = r11.fMarkUpDepth;
        r4 = r4 + -1;
        r11.fMarkUpDepth = r4;
        r4 = r11.fContentDepth;
        if (r4 != 0) goto L_0x0095;
    L_0x012b:
        goto L_0x0059;
    L_0x012d:
        r4 = r11.fEntityScanner;
        r7 = ")+";
        r4 = r4.skipString(r7);
        if (r4 == 0) goto L_0x0149;
    L_0x0137:
        r4 = r11.fStringBuffer;
        r7 = ")+";
        r4.append(r7);
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x0121;
    L_0x0142:
        r3 = 4;
        r4 = r11.fDTDContentModelHandler;
        r4.occurrence(r3, r8);
        goto L_0x0121;
    L_0x0149:
        r4 = r11.fEntityScanner;
        r7 = ")*";
        r4 = r4.skipString(r7);
        if (r4 == 0) goto L_0x0165;
    L_0x0153:
        r4 = r11.fStringBuffer;
        r7 = ")*";
        r4.append(r7);
        r4 = r11.fDTDContentModelHandler;
        if (r4 == 0) goto L_0x0121;
    L_0x015e:
        r3 = 3;
        r4 = r11.fDTDContentModelHandler;
        r4.occurrence(r3, r8);
        goto L_0x0121;
    L_0x0165:
        r4 = r11.fEntityScanner;
        r4.scanChar();
        r4 = r11.fStringBuffer;
        r7 = 41;
        r4.append(r7);
        goto L_0x0121;
    L_0x0172:
        r4 = r6;
        goto L_0x00c4;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLDTDScannerImpl.scanChildren(java.lang.String):void");
    }

    protected final void scanAttlistDecl() throws IOException, XNIException {
        this.fReportEntity = DEBUG_SCANNER_STATE;
        if (!skipSeparator(true, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true)) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ELEMENT_TYPE_IN_ATTLISTDECL", null);
        }
        String elName = this.fEntityScanner.scanName();
        if (elName == null) {
            reportFatalError("MSG_ELEMENT_TYPE_REQUIRED_IN_ATTLISTDECL", null);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.startAttlist(elName, null);
        }
        if (!skipSeparator(true, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true)) {
            if (this.fEntityScanner.skipChar(62)) {
                if (this.fDTDHandler != null) {
                    this.fDTDHandler.endAttlist(null);
                }
                this.fMarkUpDepth--;
                return;
            }
            Object[] objArr = new Object[SCANNER_STATE_TEXT_DECL];
            objArr[SCANNER_STATE_END_OF_INPUT] = elName;
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTRIBUTE_NAME_IN_ATTDEF", objArr);
        }
        while (!this.fEntityScanner.skipChar(62)) {
            boolean z;
            String name = this.fEntityScanner.scanName();
            if (name == null) {
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = elName;
                reportFatalError("AttNameRequiredInAttDef", objArr);
            }
            if (!skipSeparator(true, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true)) {
                objArr = new Object[SCANNER_STATE_MARKUP_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = elName;
                objArr[SCANNER_STATE_TEXT_DECL] = name;
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ATTTYPE_IN_ATTDEF", objArr);
            }
            String type = scanAttType(elName, name);
            if (!skipSeparator(true, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true)) {
                objArr = new Object[SCANNER_STATE_MARKUP_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = elName;
                objArr[SCANNER_STATE_TEXT_DECL] = name;
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_DEFAULTDECL_IN_ATTDEF", objArr);
            }
            String defaultType = scanAttDefaultDecl(elName, name, type, this.fLiteral, this.fLiteral2);
            if (this.fDTDHandler != null) {
                String[] enumeration = null;
                if (this.fEnumerationCount != 0) {
                    enumeration = new String[this.fEnumerationCount];
                    System.arraycopy(this.fEnumeration, SCANNER_STATE_END_OF_INPUT, enumeration, SCANNER_STATE_END_OF_INPUT, this.fEnumerationCount);
                }
                if (defaultType == null || !(defaultType.equals("#REQUIRED") || defaultType.equals("#IMPLIED"))) {
                    this.fDTDHandler.attributeDecl(elName, name, type, enumeration, defaultType, this.fLiteral, this.fLiteral2, null);
                } else {
                    this.fDTDHandler.attributeDecl(elName, name, type, enumeration, defaultType, null, null, null);
                }
            }
            if (scanningInternalSubset()) {
                z = DEBUG_SCANNER_STATE;
            } else {
                z = true;
            }
            skipSeparator(DEBUG_SCANNER_STATE, z);
        }
        if (this.fDTDHandler != null) {
            this.fDTDHandler.endAttlist(null);
        }
        this.fMarkUpDepth--;
        this.fReportEntity = true;
    }

    private final String scanAttType(String elName, String atName) throws IOException, XNIException {
        this.fEnumerationCount = SCANNER_STATE_END_OF_INPUT;
        if (this.fEntityScanner.skipString("CDATA")) {
            return "CDATA";
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_IDREFS)) {
            return SchemaSymbols.ATTVAL_IDREFS;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_IDREF)) {
            return SchemaSymbols.ATTVAL_IDREF;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ID)) {
            return SchemaSymbols.ATTVAL_ID;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ENTITY)) {
            return SchemaSymbols.ATTVAL_ENTITY;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_ENTITIES)) {
            return SchemaSymbols.ATTVAL_ENTITIES;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NMTOKENS)) {
            return SchemaSymbols.ATTVAL_NMTOKENS;
        }
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NMTOKEN)) {
            return SchemaSymbols.ATTVAL_NMTOKEN;
        }
        boolean z;
        Object[] objArr;
        int c;
        if (this.fEntityScanner.skipString(SchemaSymbols.ATTVAL_NOTATION)) {
            String type = SchemaSymbols.ATTVAL_NOTATION;
            if (scanningInternalSubset()) {
                z = DEBUG_SCANNER_STATE;
            } else {
                z = true;
            }
            if (!skipSeparator(true, z)) {
                objArr = new Object[SCANNER_STATE_MARKUP_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = elName;
                objArr[SCANNER_STATE_TEXT_DECL] = atName;
                reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_IN_NOTATIONTYPE", objArr);
            }
            if (this.fEntityScanner.scanChar() != 40) {
                objArr = new Object[SCANNER_STATE_MARKUP_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = elName;
                objArr[SCANNER_STATE_TEXT_DECL] = atName;
                reportFatalError("MSG_OPEN_PAREN_REQUIRED_IN_NOTATIONTYPE", objArr);
            }
            this.fMarkUpDepth += SCANNER_STATE_TEXT_DECL;
            do {
                if (scanningInternalSubset()) {
                    z = DEBUG_SCANNER_STATE;
                } else {
                    z = true;
                }
                skipSeparator(DEBUG_SCANNER_STATE, z);
                String aName = this.fEntityScanner.scanName();
                if (aName == null) {
                    objArr = new Object[SCANNER_STATE_MARKUP_DECL];
                    objArr[SCANNER_STATE_END_OF_INPUT] = elName;
                    objArr[SCANNER_STATE_TEXT_DECL] = atName;
                    reportFatalError("MSG_NAME_REQUIRED_IN_NOTATIONTYPE", objArr);
                    c = skipInvalidEnumerationValue();
                    if (c != 124) {
                        break;
                    }
                } else {
                    ensureEnumerationSize(this.fEnumerationCount + SCANNER_STATE_TEXT_DECL);
                    String[] strArr = this.fEnumeration;
                    int i = this.fEnumerationCount;
                    this.fEnumerationCount = i + SCANNER_STATE_TEXT_DECL;
                    strArr[i] = aName;
                    if (scanningInternalSubset()) {
                        z = DEBUG_SCANNER_STATE;
                    } else {
                        z = true;
                    }
                    skipSeparator(DEBUG_SCANNER_STATE, z);
                    c = this.fEntityScanner.scanChar();
                    continue;
                }
            } while (c == 124);
            if (c != 41) {
                objArr = new Object[SCANNER_STATE_MARKUP_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = elName;
                objArr[SCANNER_STATE_TEXT_DECL] = atName;
                reportFatalError("NotationTypeUnterminated", objArr);
            }
            this.fMarkUpDepth--;
            return type;
        }
        type = "ENUMERATION";
        if (this.fEntityScanner.scanChar() != 40) {
            objArr = new Object[SCANNER_STATE_MARKUP_DECL];
            objArr[SCANNER_STATE_END_OF_INPUT] = elName;
            objArr[SCANNER_STATE_TEXT_DECL] = atName;
            reportFatalError("AttTypeRequiredInAttDef", objArr);
        }
        this.fMarkUpDepth += SCANNER_STATE_TEXT_DECL;
        do {
            if (scanningInternalSubset()) {
                z = DEBUG_SCANNER_STATE;
            } else {
                z = true;
            }
            skipSeparator(DEBUG_SCANNER_STATE, z);
            String token = this.fEntityScanner.scanNmtoken();
            if (token == null) {
                objArr = new Object[SCANNER_STATE_MARKUP_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = elName;
                objArr[SCANNER_STATE_TEXT_DECL] = atName;
                reportFatalError("MSG_NMTOKEN_REQUIRED_IN_ENUMERATION", objArr);
                c = skipInvalidEnumerationValue();
                if (c != 124) {
                    break;
                }
            } else {
                ensureEnumerationSize(this.fEnumerationCount + SCANNER_STATE_TEXT_DECL);
                strArr = this.fEnumeration;
                i = this.fEnumerationCount;
                this.fEnumerationCount = i + SCANNER_STATE_TEXT_DECL;
                strArr[i] = token;
                if (scanningInternalSubset()) {
                    z = DEBUG_SCANNER_STATE;
                } else {
                    z = true;
                }
                skipSeparator(DEBUG_SCANNER_STATE, z);
                c = this.fEntityScanner.scanChar();
                continue;
            }
        } while (c == 124);
        if (c != 41) {
            objArr = new Object[SCANNER_STATE_MARKUP_DECL];
            objArr[SCANNER_STATE_END_OF_INPUT] = elName;
            objArr[SCANNER_STATE_TEXT_DECL] = atName;
            reportFatalError("EnumerationUnterminated", objArr);
        }
        this.fMarkUpDepth--;
        return type;
    }

    protected final String scanAttDefaultDecl(String elName, String atName, String type, XMLString defaultVal, XMLString nonNormalizedDefaultVal) throws IOException, XNIException {
        String defaultType = null;
        this.fString.clear();
        defaultVal.clear();
        if (this.fEntityScanner.skipString("#REQUIRED")) {
            return "#REQUIRED";
        }
        if (this.fEntityScanner.skipString("#IMPLIED")) {
            return "#IMPLIED";
        }
        boolean isVC;
        if (this.fEntityScanner.skipString("#FIXED")) {
            boolean z;
            defaultType = "#FIXED";
            if (scanningInternalSubset()) {
                z = DEBUG_SCANNER_STATE;
            } else {
                z = true;
            }
            if (!skipSeparator(true, z)) {
                Object[] objArr = new Object[SCANNER_STATE_MARKUP_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = elName;
                objArr[SCANNER_STATE_TEXT_DECL] = atName;
                reportFatalError("MSG_SPACE_REQUIRED_AFTER_FIXED_IN_DEFAULTDECL", objArr);
            }
        }
        if (this.fStandalone || !(this.fSeenExternalDTD || this.fSeenPEReferences)) {
            isVC = DEBUG_SCANNER_STATE;
        } else {
            isVC = true;
        }
        scanAttributeValue(defaultVal, nonNormalizedDefaultVal, atName, isVC, elName);
        return defaultType;
    }

    private final void scanEntityDecl() throws IOException, XNIException {
        String name;
        boolean isPEDecl = DEBUG_SCANNER_STATE;
        boolean sawPERef = DEBUG_SCANNER_STATE;
        this.fReportEntity = DEBUG_SCANNER_STATE;
        if (this.fEntityScanner.skipSpaces()) {
            if (this.fEntityScanner.skipChar(37)) {
                if (skipSeparator(true, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true)) {
                    isPEDecl = true;
                } else if (scanningInternalSubset()) {
                    reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_PEDECL", null);
                    isPEDecl = true;
                } else if (this.fEntityScanner.peekChar() == 37) {
                    skipSeparator(DEBUG_SCANNER_STATE, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true);
                    isPEDecl = true;
                } else {
                    sawPERef = true;
                }
            } else {
                isPEDecl = DEBUG_SCANNER_STATE;
            }
        } else if (scanningInternalSubset() || !this.fEntityScanner.skipChar(37)) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_ENTITY_NAME_IN_ENTITYDECL", null);
            isPEDecl = DEBUG_SCANNER_STATE;
        } else if (this.fEntityScanner.skipSpaces()) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_PERCENT_IN_PEDECL", null);
            isPEDecl = DEBUG_SCANNER_STATE;
        } else {
            sawPERef = true;
        }
        if (sawPERef) {
            while (true) {
                String peName = this.fEntityScanner.scanName();
                if (peName == null) {
                    reportFatalError("NameRequiredInPEReference", null);
                } else if (this.fEntityScanner.skipChar(59)) {
                    startPE(peName, DEBUG_SCANNER_STATE);
                } else {
                    Object[] objArr = new Object[SCANNER_STATE_TEXT_DECL];
                    objArr[SCANNER_STATE_END_OF_INPUT] = peName;
                    reportFatalError("SemicolonRequiredInPEReference", objArr);
                }
                this.fEntityScanner.skipSpaces();
                if (!this.fEntityScanner.skipChar(37)) {
                    break;
                } else if (!isPEDecl) {
                    if (skipSeparator(true, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true)) {
                        break;
                    }
                    isPEDecl = this.fEntityScanner.skipChar(37);
                }
            }
            isPEDecl = true;
        }
        if (this.fNamespaces) {
            name = this.fEntityScanner.scanNCName();
        } else {
            name = this.fEntityScanner.scanName();
        }
        if (name == null) {
            reportFatalError("MSG_ENTITY_NAME_REQUIRED_IN_ENTITYDECL", null);
        }
        if (!skipSeparator(true, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true)) {
            if (this.fNamespaces && this.fEntityScanner.peekChar() == 58) {
                this.fEntityScanner.scanChar();
                XMLStringBuffer colonName = new XMLStringBuffer(name);
                colonName.append(':');
                String str = this.fEntityScanner.scanName();
                if (str != null) {
                    colonName.append(str);
                }
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = colonName.toString();
                reportFatalError("ColonNotLegalWithNS", objArr);
                if (!skipSeparator(true, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true)) {
                    objArr = new Object[SCANNER_STATE_TEXT_DECL];
                    objArr[SCANNER_STATE_END_OF_INPUT] = name;
                    reportFatalError("MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL", objArr);
                }
            } else {
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = name;
                reportFatalError("MSG_SPACE_REQUIRED_AFTER_ENTITY_NAME_IN_ENTITYDECL", objArr);
            }
        }
        scanExternalID(this.fStrings, DEBUG_SCANNER_STATE);
        String systemId = this.fStrings[SCANNER_STATE_END_OF_INPUT];
        String publicId = this.fStrings[SCANNER_STATE_TEXT_DECL];
        String notation = null;
        boolean sawSpace = skipSeparator(true, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true);
        if (!isPEDecl && this.fEntityScanner.skipString("NDATA")) {
            if (!sawSpace) {
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = name;
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NDATA_IN_UNPARSED_ENTITYDECL", objArr);
            }
            if (!skipSeparator(true, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true)) {
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = name;
                reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_UNPARSED_ENTITYDECL", objArr);
            }
            notation = this.fEntityScanner.scanName();
            if (notation == null) {
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = name;
                reportFatalError("MSG_NOTATION_NAME_REQUIRED_FOR_UNPARSED_ENTITYDECL", objArr);
            }
        }
        if (systemId == null) {
            scanEntityValue(this.fLiteral, this.fLiteral2);
            this.fStringBuffer.clear();
            this.fStringBuffer2.clear();
            this.fStringBuffer.append(this.fLiteral.ch, this.fLiteral.offset, this.fLiteral.length);
            this.fStringBuffer2.append(this.fLiteral2.ch, this.fLiteral2.offset, this.fLiteral2.length);
        }
        skipSeparator(DEBUG_SCANNER_STATE, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true);
        if (!this.fEntityScanner.skipChar(62)) {
            objArr = new Object[SCANNER_STATE_TEXT_DECL];
            objArr[SCANNER_STATE_END_OF_INPUT] = name;
            reportFatalError("EntityDeclUnterminated", objArr);
        }
        this.fMarkUpDepth--;
        if (isPEDecl) {
            name = "%" + name;
        }
        if (systemId != null) {
            String baseSystemId = this.fEntityScanner.getBaseSystemId();
            if (notation != null) {
                this.fEntityManager.addUnparsedEntity(name, publicId, systemId, baseSystemId, notation);
            } else {
                this.fEntityManager.addExternalEntity(name, publicId, systemId, baseSystemId);
            }
            if (this.fDTDHandler != null) {
                this.fResourceIdentifier.setValues(publicId, systemId, baseSystemId, XMLEntityManager.expandSystemId(systemId, baseSystemId, DEBUG_SCANNER_STATE));
                if (notation != null) {
                    this.fDTDHandler.unparsedEntityDecl(name, this.fResourceIdentifier, notation, null);
                } else {
                    this.fDTDHandler.externalEntityDecl(name, this.fResourceIdentifier, null);
                }
            }
        } else {
            this.fEntityManager.addInternalEntity(name, this.fStringBuffer.toString());
            if (this.fDTDHandler != null) {
                this.fDTDHandler.internalEntityDecl(name, this.fStringBuffer, this.fStringBuffer2, null);
            }
        }
        this.fReportEntity = true;
    }

    protected final void scanEntityValue(XMLString value, XMLString nonNormalizedValue) throws IOException, XNIException {
        int quote = this.fEntityScanner.scanChar();
        if (!(quote == 39 || quote == 34)) {
            reportFatalError("OpenQuoteMissingInDecl", null);
        }
        int entityDepth = this.fEntityDepth;
        XMLString literal = this.fString;
        XMLString literal2 = this.fString;
        if (this.fEntityScanner.scanLiteral(quote, this.fString) != quote) {
            this.fStringBuffer.clear();
            this.fStringBuffer2.clear();
            do {
                this.fStringBuffer.append(this.fString);
                this.fStringBuffer2.append(this.fString);
                Object[] objArr;
                if (this.fEntityScanner.skipChar(38)) {
                    if (this.fEntityScanner.skipChar(35)) {
                        this.fStringBuffer2.append("&#");
                        scanCharReferenceValue(this.fStringBuffer, this.fStringBuffer2);
                    } else {
                        this.fStringBuffer.append('&');
                        this.fStringBuffer2.append('&');
                        String eName = this.fEntityScanner.scanName();
                        if (eName == null) {
                            reportFatalError("NameRequiredInReference", null);
                        } else {
                            this.fStringBuffer.append(eName);
                            this.fStringBuffer2.append(eName);
                        }
                        if (this.fEntityScanner.skipChar(59)) {
                            this.fStringBuffer.append(';');
                            this.fStringBuffer2.append(';');
                        } else {
                            objArr = new Object[SCANNER_STATE_TEXT_DECL];
                            objArr[SCANNER_STATE_END_OF_INPUT] = eName;
                            reportFatalError("SemicolonRequiredInReference", objArr);
                        }
                    }
                } else if (this.fEntityScanner.skipChar(37)) {
                    do {
                        this.fStringBuffer2.append('%');
                        String peName = this.fEntityScanner.scanName();
                        if (peName == null) {
                            reportFatalError("NameRequiredInPEReference", null);
                        } else if (this.fEntityScanner.skipChar(59)) {
                            if (scanningInternalSubset()) {
                                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                                objArr[SCANNER_STATE_END_OF_INPUT] = peName;
                                reportFatalError("PEReferenceWithinMarkup", objArr);
                            }
                            this.fStringBuffer2.append(peName);
                            this.fStringBuffer2.append(';');
                        } else {
                            objArr = new Object[SCANNER_STATE_TEXT_DECL];
                            objArr[SCANNER_STATE_END_OF_INPUT] = peName;
                            reportFatalError("SemicolonRequiredInPEReference", objArr);
                        }
                        startPE(peName, true);
                        this.fEntityScanner.skipSpaces();
                    } while (this.fEntityScanner.skipChar(37));
                } else {
                    int c = this.fEntityScanner.peekChar();
                    if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(this.fStringBuffer2);
                    } else if (isInvalidLiteral(c)) {
                        objArr = new Object[SCANNER_STATE_TEXT_DECL];
                        objArr[SCANNER_STATE_END_OF_INPUT] = Integer.toHexString(c);
                        reportFatalError("InvalidCharInLiteral", objArr);
                        this.fEntityScanner.scanChar();
                    } else if (!(c == quote && entityDepth == this.fEntityDepth)) {
                        this.fStringBuffer.append((char) c);
                        this.fStringBuffer2.append((char) c);
                        this.fEntityScanner.scanChar();
                    }
                }
            } while (this.fEntityScanner.scanLiteral(quote, this.fString) != quote);
            this.fStringBuffer.append(this.fString);
            this.fStringBuffer2.append(this.fString);
            literal = this.fStringBuffer;
            literal2 = this.fStringBuffer2;
        }
        value.setValues(literal);
        nonNormalizedValue.setValues(literal2);
        if (!this.fEntityScanner.skipChar(quote)) {
            reportFatalError("CloseQuoteMissingInDecl", null);
        }
    }

    private final void scanNotationDecl() throws IOException, XNIException {
        boolean z;
        String name;
        Object[] objArr;
        this.fReportEntity = DEBUG_SCANNER_STATE;
        if (scanningInternalSubset()) {
            z = DEBUG_SCANNER_STATE;
        } else {
            z = true;
        }
        if (!skipSeparator(true, z)) {
            reportFatalError("MSG_SPACE_REQUIRED_BEFORE_NOTATION_NAME_IN_NOTATIONDECL", null);
        }
        if (this.fNamespaces) {
            name = this.fEntityScanner.scanNCName();
        } else {
            name = this.fEntityScanner.scanName();
        }
        if (name == null) {
            reportFatalError("MSG_NOTATION_NAME_REQUIRED_IN_NOTATIONDECL", null);
        }
        if (scanningInternalSubset()) {
            z = DEBUG_SCANNER_STATE;
        } else {
            z = true;
        }
        if (!skipSeparator(true, z)) {
            if (this.fNamespaces && this.fEntityScanner.peekChar() == 58) {
                this.fEntityScanner.scanChar();
                XMLStringBuffer colonName = new XMLStringBuffer(name);
                colonName.append(':');
                colonName.append(this.fEntityScanner.scanName());
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = colonName.toString();
                reportFatalError("ColonNotLegalWithNS", objArr);
                if (scanningInternalSubset()) {
                    z = DEBUG_SCANNER_STATE;
                } else {
                    z = true;
                }
                skipSeparator(true, z);
            } else {
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = name;
                reportFatalError("MSG_SPACE_REQUIRED_AFTER_NOTATION_NAME_IN_NOTATIONDECL", objArr);
            }
        }
        scanExternalID(this.fStrings, true);
        String systemId = this.fStrings[SCANNER_STATE_END_OF_INPUT];
        String publicId = this.fStrings[SCANNER_STATE_TEXT_DECL];
        String baseSystemId = this.fEntityScanner.getBaseSystemId();
        if (systemId == null && publicId == null) {
            objArr = new Object[SCANNER_STATE_TEXT_DECL];
            objArr[SCANNER_STATE_END_OF_INPUT] = name;
            reportFatalError("ExternalIDorPublicIDRequired", objArr);
        }
        if (scanningInternalSubset()) {
            z = DEBUG_SCANNER_STATE;
        } else {
            z = true;
        }
        skipSeparator(DEBUG_SCANNER_STATE, z);
        if (!this.fEntityScanner.skipChar(62)) {
            objArr = new Object[SCANNER_STATE_TEXT_DECL];
            objArr[SCANNER_STATE_END_OF_INPUT] = name;
            reportFatalError("NotationDeclUnterminated", objArr);
        }
        this.fMarkUpDepth--;
        if (this.fDTDHandler != null) {
            this.fResourceIdentifier.setValues(publicId, systemId, baseSystemId, XMLEntityManager.expandSystemId(systemId, baseSystemId, DEBUG_SCANNER_STATE));
            this.fDTDHandler.notationDecl(name, this.fResourceIdentifier, null);
        }
        this.fReportEntity = true;
    }

    private final void scanConditionalSect(int currPEDepth) throws IOException, XNIException {
        this.fReportEntity = DEBUG_SCANNER_STATE;
        skipSeparator(DEBUG_SCANNER_STATE, scanningInternalSubset() ? DEBUG_SCANNER_STATE : true);
        boolean z;
        Object[] objArr;
        if (this.fEntityScanner.skipString("INCLUDE")) {
            if (scanningInternalSubset()) {
                z = DEBUG_SCANNER_STATE;
            } else {
                z = true;
            }
            skipSeparator(DEBUG_SCANNER_STATE, z);
            if (currPEDepth != this.fPEDepth && this.fValidation) {
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = this.fEntityManager.fCurrentEntity.name;
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "INVALID_PE_IN_CONDITIONAL", objArr, (short) 1);
            }
            if (!this.fEntityScanner.skipChar(91)) {
                reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startConditional((short) 0, null);
            }
            this.fIncludeSectDepth += SCANNER_STATE_TEXT_DECL;
            this.fReportEntity = true;
        } else if (this.fEntityScanner.skipString("IGNORE")) {
            if (scanningInternalSubset()) {
                z = DEBUG_SCANNER_STATE;
            } else {
                z = true;
            }
            skipSeparator(DEBUG_SCANNER_STATE, z);
            if (currPEDepth != this.fPEDepth && this.fValidation) {
                objArr = new Object[SCANNER_STATE_TEXT_DECL];
                objArr[SCANNER_STATE_END_OF_INPUT] = this.fEntityManager.fCurrentEntity.name;
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "INVALID_PE_IN_CONDITIONAL", objArr, (short) 1);
            }
            if (this.fDTDHandler != null) {
                this.fDTDHandler.startConditional((short) 1, null);
            }
            if (!this.fEntityScanner.skipChar(91)) {
                reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
            }
            this.fReportEntity = true;
            int initialDepth = this.fIncludeSectDepth + SCANNER_STATE_TEXT_DECL;
            this.fIncludeSectDepth = initialDepth;
            if (this.fDTDHandler != null) {
                this.fIgnoreConditionalBuffer.clear();
            }
            while (true) {
                if (this.fEntityScanner.skipChar(60)) {
                    if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append('<');
                    }
                    if (this.fEntityScanner.skipChar(33)) {
                        if (this.fEntityScanner.skipChar(91)) {
                            if (this.fDTDHandler != null) {
                                this.fIgnoreConditionalBuffer.append("![");
                            }
                            this.fIncludeSectDepth += SCANNER_STATE_TEXT_DECL;
                        } else if (this.fDTDHandler != null) {
                            this.fIgnoreConditionalBuffer.append("!");
                        }
                    }
                } else if (this.fEntityScanner.skipChar(93)) {
                    if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append(']');
                    }
                    if (this.fEntityScanner.skipChar(93)) {
                        if (this.fDTDHandler != null) {
                            this.fIgnoreConditionalBuffer.append(']');
                        }
                        while (this.fEntityScanner.skipChar(93)) {
                            if (this.fDTDHandler != null) {
                                this.fIgnoreConditionalBuffer.append(']');
                            }
                        }
                        if (this.fEntityScanner.skipChar(62)) {
                            int i = this.fIncludeSectDepth;
                            this.fIncludeSectDepth = i - 1;
                            if (i == initialDepth) {
                                break;
                            } else if (this.fDTDHandler != null) {
                                this.fIgnoreConditionalBuffer.append('>');
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else {
                    int c = this.fEntityScanner.scanChar();
                    if (this.fScannerState == 0) {
                        reportFatalError("IgnoreSectUnterminated", null);
                        return;
                    } else if (this.fDTDHandler != null) {
                        this.fIgnoreConditionalBuffer.append((char) c);
                    }
                }
            }
            this.fMarkUpDepth--;
            if (this.fDTDHandler != null) {
                this.fLiteral.setValues(this.fIgnoreConditionalBuffer.ch, SCANNER_STATE_END_OF_INPUT, this.fIgnoreConditionalBuffer.length - 2);
                this.fDTDHandler.ignoredCharacters(this.fLiteral, null);
                this.fDTDHandler.endConditional(null);
            }
        } else {
            reportFatalError("MSG_MARKUP_NOT_RECOGNIZED_IN_DTD", null);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected final boolean scanDecls(boolean r10) throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r9 = this;
        r8 = 45;
        r7 = 93;
        r3 = 1;
        r2 = 0;
        r6 = 0;
        r9.skipSeparator(r2, r3);
        r0 = 1;
    L_0x000b:
        if (r0 == 0) goto L_0x0012;
    L_0x000d:
        r4 = r9.fScannerState;
        r5 = 2;
        if (r4 == r5) goto L_0x0018;
    L_0x0012:
        r4 = r9.fScannerState;
        if (r4 == 0) goto L_0x0017;
    L_0x0016:
        r2 = r3;
    L_0x0017:
        return r2;
    L_0x0018:
        r0 = r10;
        r4 = r9.fEntityScanner;
        r5 = 60;
        r4 = r4.skipChar(r5);
        if (r4 == 0) goto L_0x00c6;
    L_0x0023:
        r4 = r9.fMarkUpDepth;
        r4 = r4 + 1;
        r9.fMarkUpDepth = r4;
        r4 = r9.fEntityScanner;
        r5 = 63;
        r4 = r4.skipChar(r5);
        if (r4 == 0) goto L_0x003a;
    L_0x0033:
        r9.scanPI();
    L_0x0036:
        r9.skipSeparator(r2, r3);
        goto L_0x000b;
    L_0x003a:
        r4 = r9.fEntityScanner;
        r5 = 33;
        r4 = r4.skipChar(r5);
        if (r4 == 0) goto L_0x00b9;
    L_0x0044:
        r4 = r9.fEntityScanner;
        r4 = r4.skipChar(r8);
        if (r4 == 0) goto L_0x005e;
    L_0x004c:
        r4 = r9.fEntityScanner;
        r4 = r4.skipChar(r8);
        if (r4 != 0) goto L_0x005a;
    L_0x0054:
        r4 = "MSG_MARKUP_NOT_RECOGNIZED_IN_DTD";
        r9.reportFatalError(r4, r6);
        goto L_0x0036;
    L_0x005a:
        r9.scanComment();
        goto L_0x0036;
    L_0x005e:
        r4 = r9.fEntityScanner;
        r5 = "ELEMENT";
        r4 = r4.skipString(r5);
        if (r4 == 0) goto L_0x006c;
    L_0x0068:
        r9.scanElementDecl();
        goto L_0x0036;
    L_0x006c:
        r4 = r9.fEntityScanner;
        r5 = "ATTLIST";
        r4 = r4.skipString(r5);
        if (r4 == 0) goto L_0x007a;
    L_0x0076:
        r9.scanAttlistDecl();
        goto L_0x0036;
    L_0x007a:
        r4 = r9.fEntityScanner;
        r5 = "ENTITY";
        r4 = r4.skipString(r5);
        if (r4 == 0) goto L_0x0088;
    L_0x0084:
        r9.scanEntityDecl();
        goto L_0x0036;
    L_0x0088:
        r4 = r9.fEntityScanner;
        r5 = "NOTATION";
        r4 = r4.skipString(r5);
        if (r4 == 0) goto L_0x0096;
    L_0x0092:
        r9.scanNotationDecl();
        goto L_0x0036;
    L_0x0096:
        r4 = r9.fEntityScanner;
        r5 = 91;
        r4 = r4.skipChar(r5);
        if (r4 == 0) goto L_0x00ac;
    L_0x00a0:
        r4 = r9.scanningInternalSubset();
        if (r4 != 0) goto L_0x00ac;
    L_0x00a6:
        r4 = r9.fPEDepth;
        r9.scanConditionalSect(r4);
        goto L_0x0036;
    L_0x00ac:
        r4 = r9.fMarkUpDepth;
        r4 = r4 + -1;
        r9.fMarkUpDepth = r4;
        r4 = "MSG_MARKUP_NOT_RECOGNIZED_IN_DTD";
        r9.reportFatalError(r4, r6);
        goto L_0x0036;
    L_0x00b9:
        r4 = r9.fMarkUpDepth;
        r4 = r4 + -1;
        r9.fMarkUpDepth = r4;
        r4 = "MSG_MARKUP_NOT_RECOGNIZED_IN_DTD";
        r9.reportFatalError(r4, r6);
        goto L_0x0036;
    L_0x00c6:
        r4 = r9.fIncludeSectDepth;
        if (r4 <= 0) goto L_0x0100;
    L_0x00ca:
        r4 = r9.fEntityScanner;
        r4 = r4.skipChar(r7);
        if (r4 == 0) goto L_0x0100;
    L_0x00d2:
        r4 = r9.fEntityScanner;
        r4 = r4.skipChar(r7);
        if (r4 == 0) goto L_0x00e4;
    L_0x00da:
        r4 = r9.fEntityScanner;
        r5 = 62;
        r4 = r4.skipChar(r5);
        if (r4 != 0) goto L_0x00e9;
    L_0x00e4:
        r4 = "IncludeSectUnterminated";
        r9.reportFatalError(r4, r6);
    L_0x00e9:
        r4 = r9.fDTDHandler;
        if (r4 == 0) goto L_0x00f2;
    L_0x00ed:
        r4 = r9.fDTDHandler;
        r4.endConditional(r6);
    L_0x00f2:
        r4 = r9.fIncludeSectDepth;
        r4 = r4 + -1;
        r9.fIncludeSectDepth = r4;
        r4 = r9.fMarkUpDepth;
        r4 = r4 + -1;
        r9.fMarkUpDepth = r4;
        goto L_0x0036;
    L_0x0100:
        r4 = r9.scanningInternalSubset();
        if (r4 == 0) goto L_0x010e;
    L_0x0106:
        r4 = r9.fEntityScanner;
        r4 = r4.peekChar();
        if (r4 == r7) goto L_0x0017;
    L_0x010e:
        r4 = r9.fEntityScanner;
        r4 = r4.skipSpaces();
        if (r4 != 0) goto L_0x0036;
    L_0x0116:
        r4 = "MSG_MARKUP_NOT_RECOGNIZED_IN_DTD";
        r9.reportFatalError(r4, r6);
    L_0x011b:
        r4 = r9.fEntityScanner;
        r4.scanChar();
        r9.skipSeparator(r2, r3);
        r4 = r9.fEntityScanner;
        r1 = r4.peekChar();
        r4 = 60;
        if (r1 == r4) goto L_0x0036;
    L_0x012d:
        if (r1 == r7) goto L_0x0036;
    L_0x012f:
        r4 = mf.org.apache.xerces.util.XMLChar.isSpace(r1);
        if (r4 == 0) goto L_0x011b;
    L_0x0135:
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLDTDScannerImpl.scanDecls(boolean):boolean");
    }

    private boolean skipSeparator(boolean spaceRequired, boolean lookForPERefs) throws IOException, XNIException {
        int depth = this.fPEDepth;
        boolean sawSpace = this.fEntityScanner.skipSpaces();
        if (lookForPERefs && this.fEntityScanner.skipChar(37)) {
            do {
                String name = this.fEntityScanner.scanName();
                if (name == null) {
                    reportFatalError("NameRequiredInPEReference", null);
                } else if (!this.fEntityScanner.skipChar(59)) {
                    Object[] objArr = new Object[SCANNER_STATE_TEXT_DECL];
                    objArr[SCANNER_STATE_END_OF_INPUT] = name;
                    reportFatalError("SemicolonRequiredInPEReference", objArr);
                }
                startPE(name, DEBUG_SCANNER_STATE);
                this.fEntityScanner.skipSpaces();
            } while (this.fEntityScanner.skipChar(37));
            return true;
        } else if (spaceRequired && !sawSpace && depth == this.fPEDepth) {
            return DEBUG_SCANNER_STATE;
        } else {
            return true;
        }
    }

    private final void pushContentStack(int c) {
        if (this.fContentStack.length == this.fContentDepth) {
            int[] newStack = new int[(this.fContentDepth * SCANNER_STATE_MARKUP_DECL)];
            System.arraycopy(this.fContentStack, SCANNER_STATE_END_OF_INPUT, newStack, SCANNER_STATE_END_OF_INPUT, this.fContentDepth);
            this.fContentStack = newStack;
        }
        int[] iArr = this.fContentStack;
        int i = this.fContentDepth;
        this.fContentDepth = i + SCANNER_STATE_TEXT_DECL;
        iArr[i] = c;
    }

    private final int popContentStack() {
        int[] iArr = this.fContentStack;
        int i = this.fContentDepth - 1;
        this.fContentDepth = i;
        return iArr[i];
    }

    private final void pushPEStack(int depth, boolean report) {
        if (this.fPEStack.length == this.fPEDepth) {
            int[] newIntStack = new int[(this.fPEDepth * SCANNER_STATE_MARKUP_DECL)];
            System.arraycopy(this.fPEStack, SCANNER_STATE_END_OF_INPUT, newIntStack, SCANNER_STATE_END_OF_INPUT, this.fPEDepth);
            this.fPEStack = newIntStack;
            boolean[] newBooleanStack = new boolean[(this.fPEDepth * SCANNER_STATE_MARKUP_DECL)];
            System.arraycopy(this.fPEReport, SCANNER_STATE_END_OF_INPUT, newBooleanStack, SCANNER_STATE_END_OF_INPUT, this.fPEDepth);
            this.fPEReport = newBooleanStack;
        }
        this.fPEReport[this.fPEDepth] = report;
        int[] iArr = this.fPEStack;
        int i = this.fPEDepth;
        this.fPEDepth = i + SCANNER_STATE_TEXT_DECL;
        iArr[i] = depth;
    }

    private final int popPEStack() {
        int[] iArr = this.fPEStack;
        int i = this.fPEDepth - 1;
        this.fPEDepth = i;
        return iArr[i];
    }

    private final boolean peekReportEntity() {
        return this.fPEReport[this.fPEDepth - 1];
    }

    private final void ensureEnumerationSize(int size) {
        if (this.fEnumeration.length == size) {
            String[] newEnum = new String[(size * SCANNER_STATE_MARKUP_DECL)];
            System.arraycopy(this.fEnumeration, SCANNER_STATE_END_OF_INPUT, newEnum, SCANNER_STATE_END_OF_INPUT, size);
            this.fEnumeration = newEnum;
        }
    }

    private void init() {
        this.fStartDTDCalled = DEBUG_SCANNER_STATE;
        this.fExtEntityDepth = SCANNER_STATE_END_OF_INPUT;
        this.fIncludeSectDepth = SCANNER_STATE_END_OF_INPUT;
        this.fMarkUpDepth = SCANNER_STATE_END_OF_INPUT;
        this.fPEDepth = SCANNER_STATE_END_OF_INPUT;
        this.fStandalone = DEBUG_SCANNER_STATE;
        this.fSeenExternalDTD = DEBUG_SCANNER_STATE;
        this.fSeenPEReferences = DEBUG_SCANNER_STATE;
        setScannerState(SCANNER_STATE_TEXT_DECL);
    }

    private int skipInvalidEnumerationValue() throws IOException {
        int c;
        do {
            c = this.fEntityScanner.scanChar();
            if (c == 124) {
                break;
            }
        } while (c != 41);
        ensureEnumerationSize(this.fEnumerationCount + SCANNER_STATE_TEXT_DECL);
        String[] strArr = this.fEnumeration;
        int i = this.fEnumerationCount;
        this.fEnumerationCount = i + SCANNER_STATE_TEXT_DECL;
        strArr[i] = XMLSymbols.EMPTY_STRING;
        return c;
    }
}
