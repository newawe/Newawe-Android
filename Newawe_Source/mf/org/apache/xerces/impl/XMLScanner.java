package mf.org.apache.xerces.impl;

import java.io.IOException;
import mf.javax.xml.transform.OutputKeys;
import mf.org.apache.xerces.impl.XMLEntityManager.ScannedEntity;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLResourceIdentifierImpl;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.cookie.ClientCookie;

public abstract class XMLScanner implements XMLComponent {
    protected static final boolean DEBUG_ATTR_NORMALIZATION = false;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String fAmpSymbol;
    protected static final String fAposSymbol;
    protected static final String fEncodingSymbol;
    protected static final String fGtSymbol;
    protected static final String fLtSymbol;
    protected static final String fQuotSymbol;
    protected static final String fStandaloneSymbol;
    protected static final String fVersionSymbol;
    protected String fCharRefLiteral;
    protected int fEntityDepth;
    protected XMLEntityManager fEntityManager;
    protected XMLEntityScanner fEntityScanner;
    protected XMLErrorReporter fErrorReporter;
    protected boolean fNamespaces;
    protected boolean fNotifyCharRefs;
    protected boolean fParserSettings;
    protected boolean fReportEntity;
    protected final XMLResourceIdentifierImpl fResourceIdentifier;
    protected boolean fScanningAttribute;
    private final XMLString fString;
    private final XMLStringBuffer fStringBuffer;
    private final XMLStringBuffer fStringBuffer2;
    private final XMLStringBuffer fStringBuffer3;
    protected SymbolTable fSymbolTable;
    protected boolean fValidation;

    public XMLScanner() {
        this.fValidation = DEBUG_ATTR_NORMALIZATION;
        this.fNotifyCharRefs = DEBUG_ATTR_NORMALIZATION;
        this.fParserSettings = true;
        this.fCharRefLiteral = null;
        this.fString = new XMLString();
        this.fStringBuffer = new XMLStringBuffer();
        this.fStringBuffer2 = new XMLStringBuffer();
        this.fStringBuffer3 = new XMLStringBuffer();
        this.fResourceIdentifier = new XMLResourceIdentifierImpl();
    }

    static {
        fVersionSymbol = ClientCookie.VERSION_ATTR.intern();
        fEncodingSymbol = OutputKeys.ENCODING.intern();
        fStandaloneSymbol = OutputKeys.STANDALONE.intern();
        fAmpSymbol = "amp".intern();
        fLtSymbol = "lt".intern();
        fGtSymbol = "gt".intern();
        fQuotSymbol = "quot".intern();
        fAposSymbol = "apos".intern();
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        try {
            this.fParserSettings = componentManager.getFeature(PARSER_SETTINGS);
        } catch (XMLConfigurationException e) {
            this.fParserSettings = true;
        }
        if (this.fParserSettings) {
            this.fSymbolTable = (SymbolTable) componentManager.getProperty(SYMBOL_TABLE);
            this.fErrorReporter = (XMLErrorReporter) componentManager.getProperty(ERROR_REPORTER);
            this.fEntityManager = (XMLEntityManager) componentManager.getProperty(ENTITY_MANAGER);
            try {
                this.fValidation = componentManager.getFeature(VALIDATION);
            } catch (XMLConfigurationException e2) {
                this.fValidation = DEBUG_ATTR_NORMALIZATION;
            }
            try {
                this.fNamespaces = componentManager.getFeature(NAMESPACES);
            } catch (XMLConfigurationException e3) {
                this.fNamespaces = true;
            }
            try {
                this.fNotifyCharRefs = componentManager.getFeature(NOTIFY_CHAR_REFS);
            } catch (XMLConfigurationException e4) {
                this.fNotifyCharRefs = DEBUG_ATTR_NORMALIZATION;
            }
            init();
            return;
        }
        init();
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int suffixLength = propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length();
            if (suffixLength == Constants.SYMBOL_TABLE_PROPERTY.length() && propertyId.endsWith(Constants.SYMBOL_TABLE_PROPERTY)) {
                this.fSymbolTable = (SymbolTable) value;
            } else if (suffixLength == Constants.ERROR_REPORTER_PROPERTY.length() && propertyId.endsWith(Constants.ERROR_REPORTER_PROPERTY)) {
                this.fErrorReporter = (XMLErrorReporter) value;
            } else if (suffixLength == Constants.ENTITY_MANAGER_PROPERTY.length() && propertyId.endsWith(Constants.ENTITY_MANAGER_PROPERTY)) {
                this.fEntityManager = (XMLEntityManager) value;
            }
        }
    }

    public void setFeature(String featureId, boolean value) throws XMLConfigurationException {
        if (VALIDATION.equals(featureId)) {
            this.fValidation = value;
        } else if (NOTIFY_CHAR_REFS.equals(featureId)) {
            this.fNotifyCharRefs = value;
        }
    }

    public boolean getFeature(String featureId) throws XMLConfigurationException {
        if (VALIDATION.equals(featureId)) {
            return this.fValidation;
        }
        if (NOTIFY_CHAR_REFS.equals(featureId)) {
            return this.fNotifyCharRefs;
        }
        throw new XMLConfigurationException((short) 0, featureId);
    }

    protected void reset() {
        init();
        this.fValidation = true;
        this.fNotifyCharRefs = DEBUG_ATTR_NORMALIZATION;
    }

    protected void scanXMLDeclOrTextDecl(boolean scanningTextDecl, String[] pseudoAttributeValues) throws IOException, XNIException {
        String version = null;
        String encoding = null;
        String standalone = null;
        int state = 0;
        boolean dataFoundForTarget = DEBUG_ATTR_NORMALIZATION;
        boolean sawSpace = this.fEntityScanner.skipDeclSpaces();
        ScannedEntity currEnt = this.fEntityManager.getCurrentEntity();
        boolean currLiteral = currEnt.literal;
        currEnt.literal = DEBUG_ATTR_NORMALIZATION;
        while (this.fEntityScanner.peekChar() != 63) {
            dataFoundForTarget = true;
            String name = scanPseudoAttribute(scanningTextDecl, this.fString);
            String str;
            switch (state) {
                case DurationDV.DURATION_TYPE /*0*/:
                    if (name != fVersionSymbol) {
                        if (name != fEncodingSymbol) {
                            if (!scanningTextDecl) {
                                reportFatalError("VersionInfoRequired", null);
                                break;
                            } else {
                                reportFatalError("EncodingDeclRequired", null);
                                break;
                            }
                        }
                        if (!scanningTextDecl) {
                            reportFatalError("VersionInfoRequired", null);
                        }
                        if (!sawSpace) {
                            if (scanningTextDecl) {
                                str = "SpaceRequiredBeforeEncodingInTextDecl";
                            } else {
                                str = "SpaceRequiredBeforeEncodingInXMLDecl";
                            }
                            reportFatalError(str, null);
                        }
                        encoding = this.fString.toString();
                        state = scanningTextDecl ? 3 : 2;
                        break;
                    }
                    if (!sawSpace) {
                        if (scanningTextDecl) {
                            str = "SpaceRequiredBeforeVersionInTextDecl";
                        } else {
                            str = "SpaceRequiredBeforeVersionInXMLDecl";
                        }
                        reportFatalError(str, null);
                    }
                    version = this.fString.toString();
                    state = 1;
                    if (!versionSupported(version)) {
                        reportFatalError(getVersionNotSupportedKey(), new Object[]{version});
                        break;
                    }
                    break;
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    if (name != fEncodingSymbol) {
                        if (!scanningTextDecl && name == fStandaloneSymbol) {
                            if (!sawSpace) {
                                reportFatalError("SpaceRequiredBeforeStandalone", null);
                            }
                            standalone = this.fString.toString();
                            state = 3;
                            if (!(standalone.equals("yes") || standalone.equals("no"))) {
                                reportFatalError("SDDeclInvalid", new Object[]{standalone});
                                break;
                            }
                        }
                        reportFatalError("EncodingDeclRequired", null);
                        break;
                    }
                    if (!sawSpace) {
                        if (scanningTextDecl) {
                            str = "SpaceRequiredBeforeEncodingInTextDecl";
                        } else {
                            str = "SpaceRequiredBeforeEncodingInXMLDecl";
                        }
                        reportFatalError(str, null);
                    }
                    encoding = this.fString.toString();
                    state = scanningTextDecl ? 3 : 2;
                    break;
                    break;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    if (name != fStandaloneSymbol) {
                        reportFatalError("EncodingDeclRequired", null);
                        break;
                    }
                    if (!sawSpace) {
                        reportFatalError("SpaceRequiredBeforeStandalone", null);
                    }
                    standalone = this.fString.toString();
                    state = 3;
                    if (!(standalone.equals("yes") || standalone.equals("no"))) {
                        reportFatalError("SDDeclInvalid", new Object[]{standalone});
                        break;
                    }
                default:
                    reportFatalError("NoMorePseudoAttributes", null);
                    break;
            }
            sawSpace = this.fEntityScanner.skipDeclSpaces();
        }
        if (currLiteral) {
            currEnt.literal = true;
        }
        if (scanningTextDecl && state != 3) {
            reportFatalError("MorePseudoAttributes", null);
        }
        if (scanningTextDecl) {
            if (!dataFoundForTarget && encoding == null) {
                reportFatalError("EncodingDeclRequired", null);
            }
        } else if (!dataFoundForTarget && version == null) {
            reportFatalError("VersionInfoRequired", null);
        }
        if (!this.fEntityScanner.skipChar(63)) {
            reportFatalError("XMLDeclUnterminated", null);
        }
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("XMLDeclUnterminated", null);
        }
        pseudoAttributeValues[0] = version;
        pseudoAttributeValues[1] = encoding;
        pseudoAttributeValues[2] = standalone;
    }

    public String scanPseudoAttribute(boolean scanningTextDecl, XMLString value) throws IOException, XNIException {
        String str;
        String name = this.fEntityScanner.scanName();
        XMLEntityManager.print(this.fEntityManager.getCurrentEntity());
        if (name == null) {
            reportFatalError("PseudoAttrNameExpected", null);
        }
        this.fEntityScanner.skipDeclSpaces();
        if (!this.fEntityScanner.skipChar(61)) {
            if (scanningTextDecl) {
                str = "EqRequiredInTextDecl";
            } else {
                str = "EqRequiredInXMLDecl";
            }
            reportFatalError(str, new Object[]{name});
        }
        this.fEntityScanner.skipDeclSpaces();
        int quote = this.fEntityScanner.peekChar();
        if (!(quote == 39 || quote == 34)) {
            if (scanningTextDecl) {
                str = "QuoteRequiredInTextDecl";
            } else {
                str = "QuoteRequiredInXMLDecl";
            }
            reportFatalError(str, new Object[]{name});
        }
        this.fEntityScanner.scanChar();
        int c = this.fEntityScanner.scanLiteral(quote, value);
        if (c != quote) {
            this.fStringBuffer2.clear();
            do {
                this.fStringBuffer2.append(value);
                if (c != -1) {
                    if (c == 38 || c == 37 || c == 60 || c == 93) {
                        this.fStringBuffer2.append((char) this.fEntityScanner.scanChar());
                    } else if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(this.fStringBuffer2);
                    } else if (isInvalidLiteral(c)) {
                        reportFatalError(scanningTextDecl ? "InvalidCharInTextDecl" : "InvalidCharInXMLDecl", new Object[]{Integer.toString(c, 16)});
                        this.fEntityScanner.scanChar();
                    }
                }
                c = this.fEntityScanner.scanLiteral(quote, value);
            } while (c != quote);
            this.fStringBuffer2.append(value);
            value.setValues(this.fStringBuffer2);
        }
        if (!this.fEntityScanner.skipChar(quote)) {
            if (scanningTextDecl) {
                str = "CloseQuoteMissingInTextDecl";
            } else {
                str = "CloseQuoteMissingInXMLDecl";
            }
            reportFatalError(str, new Object[]{name});
        }
        return name;
    }

    protected void scanPI() throws IOException, XNIException {
        String target;
        this.fReportEntity = DEBUG_ATTR_NORMALIZATION;
        if (this.fNamespaces) {
            target = this.fEntityScanner.scanNCName();
        } else {
            target = this.fEntityScanner.scanName();
        }
        if (target == null) {
            reportFatalError("PITargetRequired", null);
        }
        scanPIData(target, this.fString);
        this.fReportEntity = true;
    }

    protected void scanPIData(String target, XMLString data) throws IOException, XNIException {
        if (target.length() == 3) {
            char c0 = Character.toLowerCase(target.charAt(0));
            char c1 = Character.toLowerCase(target.charAt(1));
            char c2 = Character.toLowerCase(target.charAt(2));
            if (c0 == 'x' && c1 == 'm' && c2 == 'l') {
                reportFatalError("ReservedPITarget", null);
            }
        }
        if (!this.fEntityScanner.skipSpaces()) {
            if (this.fEntityScanner.skipString("?>")) {
                data.clear();
                return;
            } else if (this.fNamespaces && this.fEntityScanner.peekChar() == 58) {
                this.fEntityScanner.scanChar();
                XMLStringBuffer colonName = new XMLStringBuffer(target);
                colonName.append(':');
                String str = this.fEntityScanner.scanName();
                if (str != null) {
                    colonName.append(str);
                }
                reportFatalError("ColonNotLegalWithNS", new Object[]{colonName.toString()});
                this.fEntityScanner.skipSpaces();
            } else {
                reportFatalError("SpaceRequiredInPI", null);
            }
        }
        this.fStringBuffer.clear();
        if (this.fEntityScanner.scanData("?>", this.fStringBuffer)) {
            do {
                int c = this.fEntityScanner.peekChar();
                if (c != -1) {
                    if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(this.fStringBuffer);
                    } else if (isInvalidLiteral(c)) {
                        reportFatalError("InvalidCharInPI", new Object[]{Integer.toHexString(c)});
                        this.fEntityScanner.scanChar();
                    }
                }
            } while (this.fEntityScanner.scanData("?>", this.fStringBuffer));
        }
        data.setValues(this.fStringBuffer);
    }

    protected void scanComment(XMLStringBuffer text) throws IOException, XNIException {
        text.clear();
        while (this.fEntityScanner.scanData("--", text)) {
            int c = this.fEntityScanner.peekChar();
            if (c != -1) {
                if (XMLChar.isHighSurrogate(c)) {
                    scanSurrogates(text);
                } else if (isInvalidLiteral(c)) {
                    reportFatalError("InvalidCharInComment", new Object[]{Integer.toHexString(c)});
                    this.fEntityScanner.scanChar();
                }
            }
        }
        if (!this.fEntityScanner.skipChar(62)) {
            reportFatalError("DashDashInComment", null);
        }
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
        if (r0 == r7) goto L_0x01f6;
    L_0x01f2:
        r7 = 13;
        if (r0 != r7) goto L_0x020f;
    L_0x01f6:
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fStringBuffer;
        r8 = 32;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a0;
    L_0x0206:
        r7 = r12.fStringBuffer2;
        r8 = 10;
        r7.append(r8);
        goto L_0x00a0;
    L_0x020f:
        r7 = -1;
        if (r0 == r7) goto L_0x0239;
    L_0x0212:
        r7 = mf.org.apache.xerces.util.XMLChar.isHighSurrogate(r0);
        if (r7 == 0) goto L_0x0239;
    L_0x0218:
        r7 = r12.fStringBuffer3;
        r7.clear();
        r7 = r12.fStringBuffer3;
        r7 = r12.scanSurrogates(r7);
        if (r7 == 0) goto L_0x00a0;
    L_0x0225:
        r7 = r12.fStringBuffer;
        r8 = r12.fStringBuffer3;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a0;
    L_0x0230:
        r7 = r12.fStringBuffer2;
        r8 = r12.fStringBuffer3;
        r7.append(r8);
        goto L_0x00a0;
    L_0x0239:
        r7 = -1;
        if (r0 == r7) goto L_0x00a0;
    L_0x023c:
        r7 = r12.isInvalidLiteral(r0);
        if (r7 == 0) goto L_0x00a0;
    L_0x0242:
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
    L_0x0262:
        r7 = r12.fStringBuffer2;
        r8 = (char) r0;
        r7.append(r8);
        goto L_0x00a0;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLScanner.scanAttributeValue(mf.org.apache.xerces.xni.XMLString, mf.org.apache.xerces.xni.XMLString, java.lang.String, boolean, java.lang.String):boolean");
    }

    protected void scanExternalID(String[] identifiers, boolean optionalSystemId) throws IOException, XNIException {
        String systemId = null;
        String publicId = null;
        if (this.fEntityScanner.skipString("PUBLIC")) {
            if (!this.fEntityScanner.skipSpaces()) {
                reportFatalError("SpaceRequiredAfterPUBLIC", null);
            }
            scanPubidLiteral(this.fString);
            publicId = this.fString.toString();
            if (!(this.fEntityScanner.skipSpaces() || optionalSystemId)) {
                reportFatalError("SpaceRequiredBetweenPublicAndSystem", null);
            }
        }
        if (publicId != null || this.fEntityScanner.skipString("SYSTEM")) {
            if (publicId == null && !this.fEntityScanner.skipSpaces()) {
                reportFatalError("SpaceRequiredAfterSYSTEM", null);
            }
            int quote = this.fEntityScanner.peekChar();
            if (!(quote == 39 || quote == 34)) {
                if (publicId == null || !optionalSystemId) {
                    reportFatalError("QuoteRequiredInSystemID", null);
                } else {
                    identifiers[0] = null;
                    identifiers[1] = publicId;
                    return;
                }
            }
            this.fEntityScanner.scanChar();
            XMLString ident = this.fString;
            if (this.fEntityScanner.scanLiteral(quote, ident) != quote) {
                this.fStringBuffer.clear();
                do {
                    this.fStringBuffer.append(ident);
                    int c = this.fEntityScanner.peekChar();
                    if (XMLChar.isMarkup(c) || c == 93) {
                        this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
                    } else if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(this.fStringBuffer);
                    } else if (isInvalidLiteral(c)) {
                        reportFatalError("InvalidCharInSystemID", new Object[]{Integer.toHexString(c)});
                        this.fEntityScanner.scanChar();
                    }
                } while (this.fEntityScanner.scanLiteral(quote, ident) != quote);
                this.fStringBuffer.append(ident);
                ident = this.fStringBuffer;
            }
            systemId = ident.toString();
            if (!this.fEntityScanner.skipChar(quote)) {
                reportFatalError("SystemIDUnterminated", null);
            }
        }
        identifiers[0] = systemId;
        identifiers[1] = publicId;
    }

    protected boolean scanPubidLiteral(XMLString literal) throws IOException, XNIException {
        int quote = this.fEntityScanner.scanChar();
        if (quote == 39 || quote == 34) {
            this.fStringBuffer.clear();
            boolean skipSpace = true;
            boolean dataok = true;
            while (true) {
                int c = this.fEntityScanner.scanChar();
                if (c == 32 || c == 10 || c == 13) {
                    if (!skipSpace) {
                        this.fStringBuffer.append(' ');
                        skipSpace = true;
                    }
                } else if (c == quote) {
                    break;
                } else if (XMLChar.isPubid(c)) {
                    this.fStringBuffer.append((char) c);
                    skipSpace = DEBUG_ATTR_NORMALIZATION;
                } else if (c == -1) {
                    reportFatalError("PublicIDUnterminated", null);
                    return DEBUG_ATTR_NORMALIZATION;
                } else {
                    dataok = DEBUG_ATTR_NORMALIZATION;
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
        return DEBUG_ATTR_NORMALIZATION;
    }

    protected void normalizeWhitespace(XMLString value) {
        int end = value.offset + value.length;
        for (int i = value.offset; i < end; i++) {
            if (value.ch[i] < 32) {
                value.ch[i] = ' ';
            }
        }
    }

    protected void normalizeWhitespace(XMLString value, int fromIndex) {
        int end = value.offset + value.length;
        for (int i = value.offset + fromIndex; i < end; i++) {
            if (value.ch[i] < 32) {
                value.ch[i] = ' ';
            }
        }
    }

    protected int isUnchangedByNormalization(XMLString value) {
        int end = value.offset + value.length;
        for (int i = value.offset; i < end; i++) {
            if (value.ch[i] < 32) {
                return i - value.offset;
            }
        }
        return -1;
    }

    public void startEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        this.fEntityDepth++;
        this.fEntityScanner = this.fEntityManager.getEntityScanner();
    }

    public void endEntity(String name, Augmentations augs) throws XNIException {
        this.fEntityDepth--;
    }

    protected int scanCharReferenceValue(XMLStringBuffer buf, XMLStringBuffer buf2) throws IOException, XNIException {
        boolean hex = DEBUG_ATTR_NORMALIZATION;
        int c;
        boolean digit;
        if (this.fEntityScanner.skipChar(120)) {
            if (buf2 != null) {
                buf2.append('x');
            }
            hex = true;
            this.fStringBuffer3.clear();
            c = this.fEntityScanner.peekChar();
            digit = ((c < 48 || c > 57) && ((c < 97 || c > HttpStatus.SC_PROCESSING) && (c < 65 || c > 70))) ? DEBUG_ATTR_NORMALIZATION : true;
            if (digit) {
                if (buf2 != null) {
                    buf2.append((char) c);
                }
                this.fEntityScanner.scanChar();
                this.fStringBuffer3.append((char) c);
                do {
                    c = this.fEntityScanner.peekChar();
                    digit = ((c < 48 || c > 57) && ((c < 97 || c > HttpStatus.SC_PROCESSING) && (c < 65 || c > 70))) ? DEBUG_ATTR_NORMALIZATION : true;
                    if (digit) {
                        if (buf2 != null) {
                            buf2.append((char) c);
                        }
                        this.fEntityScanner.scanChar();
                        this.fStringBuffer3.append((char) c);
                        continue;
                    }
                } while (digit);
            } else {
                reportFatalError("HexdigitRequiredInCharRef", null);
            }
        } else {
            this.fStringBuffer3.clear();
            c = this.fEntityScanner.peekChar();
            digit = (c < 48 || c > 57) ? DEBUG_ATTR_NORMALIZATION : true;
            if (digit) {
                if (buf2 != null) {
                    buf2.append((char) c);
                }
                this.fEntityScanner.scanChar();
                this.fStringBuffer3.append((char) c);
                do {
                    c = this.fEntityScanner.peekChar();
                    digit = (c < 48 || c > 57) ? DEBUG_ATTR_NORMALIZATION : true;
                    if (digit) {
                        if (buf2 != null) {
                            buf2.append((char) c);
                        }
                        this.fEntityScanner.scanChar();
                        this.fStringBuffer3.append((char) c);
                        continue;
                    }
                } while (digit);
            } else {
                reportFatalError("DigitRequiredInCharRef", null);
            }
        }
        if (!this.fEntityScanner.skipChar(59)) {
            reportFatalError("SemicolonRequiredInCharRef", null);
        }
        if (buf2 != null) {
            buf2.append(';');
        }
        int value = -1;
        StringBuffer errorBuf;
        try {
            value = Integer.parseInt(this.fStringBuffer3.toString(), hex ? 16 : 10);
            if (isInvalid(value)) {
                errorBuf = new StringBuffer(this.fStringBuffer3.length + 1);
                if (hex) {
                    errorBuf.append('x');
                }
                errorBuf.append(this.fStringBuffer3.ch, this.fStringBuffer3.offset, this.fStringBuffer3.length);
                reportFatalError("InvalidCharRef", new Object[]{errorBuf.toString()});
            }
        } catch (NumberFormatException e) {
            errorBuf = new StringBuffer(this.fStringBuffer3.length + 1);
            if (hex) {
                errorBuf.append('x');
            }
            errorBuf.append(this.fStringBuffer3.ch, this.fStringBuffer3.offset, this.fStringBuffer3.length);
            reportFatalError("InvalidCharRef", new Object[]{errorBuf.toString()});
        }
        if (XMLChar.isSupplemental(value)) {
            buf.append(XMLChar.highSurrogate(value));
            buf.append(XMLChar.lowSurrogate(value));
        } else {
            buf.append((char) value);
        }
        if (this.fNotifyCharRefs && value != -1) {
            String literal = "#" + (hex ? "x" : StringUtils.EMPTY) + this.fStringBuffer3.toString();
            if (!this.fScanningAttribute) {
                this.fCharRefLiteral = literal;
            }
        }
        return value;
    }

    protected boolean isInvalid(int value) {
        return XMLChar.isInvalid(value);
    }

    protected boolean isInvalidLiteral(int value) {
        return XMLChar.isInvalid(value);
    }

    protected boolean isValidNameChar(int value) {
        return XMLChar.isName(value);
    }

    protected boolean isValidNameStartChar(int value) {
        return XMLChar.isNameStart(value);
    }

    protected boolean isValidNCName(int value) {
        return XMLChar.isNCName(value);
    }

    protected boolean isValidNameStartHighSurrogate(int value) {
        return DEBUG_ATTR_NORMALIZATION;
    }

    protected boolean versionSupported(String version) {
        return version.equals("1.0");
    }

    protected String getVersionNotSupportedKey() {
        return "VersionNotSupported";
    }

    protected boolean scanSurrogates(XMLStringBuffer buf) throws IOException, XNIException {
        int high = this.fEntityScanner.scanChar();
        int low = this.fEntityScanner.peekChar();
        if (XMLChar.isLowSurrogate(low)) {
            this.fEntityScanner.scanChar();
            if (isInvalid(XMLChar.supplemental((char) high, (char) low))) {
                reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(XMLChar.supplemental((char) high, (char) low), 16)});
                return DEBUG_ATTR_NORMALIZATION;
            }
            buf.append((char) high);
            buf.append((char) low);
            return true;
        }
        reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(high, 16)});
        return DEBUG_ATTR_NORMALIZATION;
    }

    protected void reportFatalError(String msgId, Object[] args) throws XNIException {
        this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, msgId, args, (short) 2);
    }

    private void init() {
        this.fEntityScanner = null;
        this.fEntityDepth = 0;
        this.fReportEntity = true;
        this.fResourceIdentifier.clear();
    }
}
