package mf.org.apache.xerces.impl.dtd;

import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.io.IOException;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.RevalidationHandler;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.dv.DTDDVFactory;
import mf.org.apache.xerces.impl.dv.DatatypeValidator;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.impl.validation.ValidationState;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.jaxp.JAXPConstants;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLDocumentHandler;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLDocumentFilter;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.http.cookie.ClientCookie;

public class XMLDTDValidator implements XMLComponent, XMLDocumentFilter, XMLDTDValidatorFilter, RevalidationHandler {
    protected static final String BALANCE_SYNTAX_TREES = "http://apache.org/xml/features/validation/balance-syntax-trees";
    protected static final String DATATYPE_VALIDATOR_FACTORY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    private static final boolean DEBUG_ATTRIBUTES = false;
    private static final boolean DEBUG_ELEMENT_CHILDREN = false;
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final Boolean[] FEATURE_DEFAULTS;
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final Object[] PROPERTY_DEFAULTS;
    private static final String[] RECOGNIZED_FEATURES;
    private static final String[] RECOGNIZED_PROPERTIES;
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final int TOP_LEVEL_SCOPE = -1;
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected boolean fBalanceSyntaxTrees;
    private final StringBuffer fBuffer;
    private int[] fContentSpecTypeStack;
    private int fCurrentContentSpecType;
    private final QName fCurrentElement;
    private int fCurrentElementIndex;
    protected DTDGrammar fDTDGrammar;
    protected boolean fDTDValidation;
    protected DTDDVFactory fDatatypeValidatorFactory;
    protected XMLLocator fDocLocation;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fDocumentSource;
    protected boolean fDynamicValidation;
    private QName[] fElementChildren;
    private int fElementChildrenLength;
    private int[] fElementChildrenOffsetStack;
    private int fElementDepth;
    private int[] fElementIndexStack;
    private QName[] fElementQNamePartsStack;
    private final XMLEntityDecl fEntityDecl;
    protected XMLErrorReporter fErrorReporter;
    protected DTDGrammarBucket fGrammarBucket;
    protected XMLGrammarPool fGrammarPool;
    private boolean fInCDATASection;
    private boolean fInElementContent;
    protected NamespaceContext fNamespaceContext;
    protected boolean fNamespaces;
    private boolean fPerformValidation;
    private final QName fRootElement;
    private String fSchemaType;
    protected boolean fSeenDoctypeDecl;
    private boolean fSeenRootElement;
    protected SymbolTable fSymbolTable;
    private final XMLAttributeDecl fTempAttDecl;
    private XMLElementDecl fTempElementDecl;
    private final QName fTempQName;
    protected DatatypeValidator fValENTITIES;
    protected DatatypeValidator fValENTITY;
    protected DatatypeValidator fValID;
    protected DatatypeValidator fValIDRef;
    protected DatatypeValidator fValIDRefs;
    protected DatatypeValidator fValNMTOKEN;
    protected DatatypeValidator fValNMTOKENS;
    protected DatatypeValidator fValNOTATION;
    protected boolean fValidation;
    protected ValidationManager fValidationManager;
    protected final ValidationState fValidationState;
    protected boolean fWarnDuplicateAttdef;

    static {
        RECOGNIZED_FEATURES = new String[]{NAMESPACES, VALIDATION, DYNAMIC_VALIDATION, BALANCE_SYNTAX_TREES};
        Boolean[] boolArr = new Boolean[4];
        boolArr[2] = Boolean.FALSE;
        boolArr[3] = Boolean.FALSE;
        FEATURE_DEFAULTS = boolArr;
        RECOGNIZED_PROPERTIES = new String[]{SYMBOL_TABLE, ERROR_REPORTER, GRAMMAR_POOL, DATATYPE_VALIDATOR_FACTORY, VALIDATION_MANAGER};
        PROPERTY_DEFAULTS = new Object[5];
    }

    public XMLDTDValidator() {
        this.fValidationManager = null;
        this.fValidationState = new ValidationState();
        this.fNamespaceContext = null;
        this.fSeenDoctypeDecl = DEBUG_ELEMENT_CHILDREN;
        this.fCurrentElement = new QName();
        this.fCurrentElementIndex = TOP_LEVEL_SCOPE;
        this.fCurrentContentSpecType = TOP_LEVEL_SCOPE;
        this.fRootElement = new QName();
        this.fInCDATASection = DEBUG_ELEMENT_CHILDREN;
        this.fElementIndexStack = new int[8];
        this.fContentSpecTypeStack = new int[8];
        this.fElementQNamePartsStack = new QName[8];
        this.fElementChildren = new QName[32];
        this.fElementChildrenLength = 0;
        this.fElementChildrenOffsetStack = new int[32];
        this.fElementDepth = TOP_LEVEL_SCOPE;
        this.fSeenRootElement = DEBUG_ELEMENT_CHILDREN;
        this.fInElementContent = DEBUG_ELEMENT_CHILDREN;
        this.fTempElementDecl = new XMLElementDecl();
        this.fTempAttDecl = new XMLAttributeDecl();
        this.fEntityDecl = new XMLEntityDecl();
        this.fTempQName = new QName();
        this.fBuffer = new StringBuffer();
        for (int i = 0; i < this.fElementQNamePartsStack.length; i++) {
            this.fElementQNamePartsStack[i] = new QName();
        }
        this.fGrammarBucket = new DTDGrammarBucket();
    }

    DTDGrammarBucket getGrammarBucket() {
        return this.fGrammarBucket;
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        boolean parser_settings;
        this.fDTDGrammar = null;
        this.fSeenDoctypeDecl = DEBUG_ELEMENT_CHILDREN;
        this.fInCDATASection = DEBUG_ELEMENT_CHILDREN;
        this.fSeenRootElement = DEBUG_ELEMENT_CHILDREN;
        this.fInElementContent = DEBUG_ELEMENT_CHILDREN;
        this.fCurrentElementIndex = TOP_LEVEL_SCOPE;
        this.fCurrentContentSpecType = TOP_LEVEL_SCOPE;
        this.fRootElement.clear();
        this.fValidationState.resetIDTables();
        this.fGrammarBucket.clear();
        this.fElementDepth = TOP_LEVEL_SCOPE;
        this.fElementChildrenLength = 0;
        try {
            parser_settings = componentManager.getFeature(PARSER_SETTINGS);
        } catch (XMLConfigurationException e) {
            parser_settings = true;
        }
        if (parser_settings) {
            try {
                this.fNamespaces = componentManager.getFeature(NAMESPACES);
            } catch (XMLConfigurationException e2) {
                this.fNamespaces = true;
            }
            try {
                this.fValidation = componentManager.getFeature(VALIDATION);
            } catch (XMLConfigurationException e3) {
                this.fValidation = DEBUG_ELEMENT_CHILDREN;
            }
            try {
                boolean z;
                if (componentManager.getFeature("http://apache.org/xml/features/validation/schema")) {
                    z = DEBUG_ELEMENT_CHILDREN;
                } else {
                    z = true;
                }
                this.fDTDValidation = z;
            } catch (XMLConfigurationException e4) {
                this.fDTDValidation = true;
            }
            try {
                this.fDynamicValidation = componentManager.getFeature(DYNAMIC_VALIDATION);
            } catch (XMLConfigurationException e5) {
                this.fDynamicValidation = DEBUG_ELEMENT_CHILDREN;
            }
            try {
                this.fBalanceSyntaxTrees = componentManager.getFeature(BALANCE_SYNTAX_TREES);
            } catch (XMLConfigurationException e6) {
                this.fBalanceSyntaxTrees = DEBUG_ELEMENT_CHILDREN;
            }
            try {
                this.fWarnDuplicateAttdef = componentManager.getFeature(WARN_ON_DUPLICATE_ATTDEF);
            } catch (XMLConfigurationException e7) {
                this.fWarnDuplicateAttdef = DEBUG_ELEMENT_CHILDREN;
            }
            try {
                this.fSchemaType = (String) componentManager.getProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE);
            } catch (XMLConfigurationException e8) {
                this.fSchemaType = null;
            }
            this.fValidationManager = (ValidationManager) componentManager.getProperty(VALIDATION_MANAGER);
            this.fValidationManager.addValidationState(this.fValidationState);
            this.fValidationState.setUsingNamespaces(this.fNamespaces);
            this.fErrorReporter = (XMLErrorReporter) componentManager.getProperty(ERROR_REPORTER);
            this.fSymbolTable = (SymbolTable) componentManager.getProperty(SYMBOL_TABLE);
            try {
                this.fGrammarPool = (XMLGrammarPool) componentManager.getProperty(GRAMMAR_POOL);
            } catch (XMLConfigurationException e9) {
                this.fGrammarPool = null;
            }
            this.fDatatypeValidatorFactory = (DTDDVFactory) componentManager.getProperty(DATATYPE_VALIDATOR_FACTORY);
            init();
            return;
        }
        this.fValidationManager.addValidationState(this.fValidationState);
    }

    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
    }

    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
    }

    public Boolean getFeatureDefault(String featureId) {
        for (int i = 0; i < RECOGNIZED_FEATURES.length; i++) {
            if (RECOGNIZED_FEATURES[i].equals(featureId)) {
                return FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }

    public Object getPropertyDefault(String propertyId) {
        for (int i = 0; i < RECOGNIZED_PROPERTIES.length; i++) {
            if (RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }

    public void setDocumentHandler(XMLDocumentHandler documentHandler) {
        this.fDocumentHandler = documentHandler;
    }

    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    public void setDocumentSource(XMLDocumentSource source) {
        this.fDocumentSource = source;
    }

    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }

    public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
        if (this.fGrammarPool != null) {
            Grammar[] grammars = this.fGrammarPool.retrieveInitialGrammarSet(XMLGrammarDescription.XML_DTD);
            int length = grammars != null ? grammars.length : 0;
            for (int i = 0; i < length; i++) {
                this.fGrammarBucket.putGrammar((DTDGrammar) grammars[i]);
            }
        }
        this.fDocLocation = locator;
        this.fNamespaceContext = namespaceContext;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startDocument(locator, encoding, namespaceContext, augs);
        }
    }

    public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
        DTDGrammarBucket dTDGrammarBucket = this.fGrammarBucket;
        boolean z = (standalone == null || !standalone.equals("yes")) ? DEBUG_ELEMENT_CHILDREN : true;
        dTDGrammarBucket.setStandalone(z);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.xmlDecl(version, encoding, standalone, augs);
        }
    }

    public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
        this.fSeenDoctypeDecl = true;
        this.fRootElement.setValues(null, rootElement, rootElement, null);
        String eid = null;
        try {
            eid = XMLEntityManager.expandSystemId(systemId, this.fDocLocation.getExpandedSystemId(), DEBUG_ELEMENT_CHILDREN);
        } catch (IOException e) {
        }
        XMLDTDDescription grammarDesc = new XMLDTDDescription(publicId, systemId, this.fDocLocation.getExpandedSystemId(), eid, rootElement);
        this.fDTDGrammar = this.fGrammarBucket.getGrammar(grammarDesc);
        if (!(this.fDTDGrammar != null || this.fGrammarPool == null || (systemId == null && publicId == null))) {
            this.fDTDGrammar = (DTDGrammar) this.fGrammarPool.retrieveGrammar(grammarDesc);
        }
        if (this.fDTDGrammar != null) {
            this.fValidationManager.setCachedDTD(true);
        } else if (this.fBalanceSyntaxTrees) {
            this.fDTDGrammar = new BalancedDTDGrammar(this.fSymbolTable, grammarDesc);
        } else {
            this.fDTDGrammar = new DTDGrammar(this.fSymbolTable, grammarDesc);
        }
        this.fGrammarBucket.setActiveGrammar(this.fDTDGrammar);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.doctypeDecl(rootElement, publicId, systemId, augs);
        }
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        handleStartElement(element, attributes, augs);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startElement(element, attributes, augs);
        }
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        boolean removed = handleStartElement(element, attributes, augs);
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.emptyElement(element, attributes, augs);
        }
        if (!removed) {
            handleEndElement(element, augs, true);
        }
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        boolean callNextCharacters = true;
        boolean allWhiteSpace = true;
        for (int i = text.offset; i < text.offset + text.length; i++) {
            if (!isSpace(text.ch[i])) {
                allWhiteSpace = DEBUG_ELEMENT_CHILDREN;
                break;
            }
        }
        if (this.fInElementContent && allWhiteSpace && !this.fInCDATASection && this.fDocumentHandler != null) {
            this.fDocumentHandler.ignorableWhitespace(text, augs);
            callNextCharacters = DEBUG_ELEMENT_CHILDREN;
        }
        if (this.fPerformValidation) {
            if (this.fInElementContent) {
                if (this.fGrammarBucket.getStandalone() && this.fDTDGrammar.getElementDeclIsExternal(this.fCurrentElementIndex) && allWhiteSpace) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_WHITE_SPACE_IN_ELEMENT_CONTENT_WHEN_STANDALONE", null, (short) 1);
                }
                if (!allWhiteSpace) {
                    charDataInContent();
                }
                if (augs != null && augs.getItem(Constants.CHAR_REF_PROBABLE_WS) == Boolean.TRUE) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_CONTENT_INVALID_SPECIFIED", new Object[]{this.fCurrentElement.rawname, this.fDTDGrammar.getContentSpecAsString(this.fElementDepth), "character reference"}, (short) 1);
                }
            }
            if (this.fCurrentContentSpecType == 1) {
                charDataInContent();
            }
        }
        if (callNextCharacters && this.fDocumentHandler != null) {
            this.fDocumentHandler.characters(text, augs);
        }
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.ignorableWhitespace(text, augs);
        }
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        handleEndElement(element, augs, DEBUG_ELEMENT_CHILDREN);
    }

    public void startCDATA(Augmentations augs) throws XNIException {
        if (this.fPerformValidation && this.fInElementContent) {
            charDataInContent();
        }
        this.fInCDATASection = true;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startCDATA(augs);
        }
    }

    public void endCDATA(Augmentations augs) throws XNIException {
        this.fInCDATASection = DEBUG_ELEMENT_CHILDREN;
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endCDATA(augs);
        }
    }

    public void endDocument(Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endDocument(augs);
        }
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
        if (this.fPerformValidation && this.fElementDepth >= 0 && this.fDTDGrammar != null) {
            this.fDTDGrammar.getElementDecl(this.fCurrentElementIndex, this.fTempElementDecl);
            if (this.fTempElementDecl.type == (short) 1) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_CONTENT_INVALID_SPECIFIED", new Object[]{this.fCurrentElement.rawname, "EMPTY", ClientCookie.COMMENT_ATTR}, (short) 1);
            }
        }
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.comment(text, augs);
        }
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
        if (this.fPerformValidation && this.fElementDepth >= 0 && this.fDTDGrammar != null) {
            this.fDTDGrammar.getElementDecl(this.fCurrentElementIndex, this.fTempElementDecl);
            if (this.fTempElementDecl.type == (short) 1) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_CONTENT_INVALID_SPECIFIED", new Object[]{this.fCurrentElement.rawname, "EMPTY", "processing instruction"}, (short) 1);
            }
        }
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.processingInstruction(target, data, augs);
        }
    }

    public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        if (this.fPerformValidation && this.fElementDepth >= 0 && this.fDTDGrammar != null) {
            this.fDTDGrammar.getElementDecl(this.fCurrentElementIndex, this.fTempElementDecl);
            if (this.fTempElementDecl.type == (short) 1) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_CONTENT_INVALID_SPECIFIED", new Object[]{this.fCurrentElement.rawname, "EMPTY", SchemaSymbols.ATTVAL_ENTITY}, (short) 1);
            }
            if (this.fGrammarBucket.getStandalone()) {
                XMLDTDProcessor.checkStandaloneEntityRef(name, this.fDTDGrammar, this.fEntityDecl, this.fErrorReporter);
            }
        }
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.startGeneralEntity(name, identifier, encoding, augs);
        }
    }

    public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.endGeneralEntity(name, augs);
        }
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
        if (this.fDocumentHandler != null) {
            this.fDocumentHandler.textDecl(version, encoding, augs);
        }
    }

    public final boolean hasGrammar() {
        return this.fDTDGrammar != null ? true : DEBUG_ELEMENT_CHILDREN;
    }

    public final boolean validate() {
        return (this.fSchemaType == Constants.NS_XMLSCHEMA || (((this.fDynamicValidation || !this.fValidation) && !(this.fDynamicValidation && this.fSeenDoctypeDecl)) || !(this.fDTDValidation || this.fSeenDoctypeDecl))) ? DEBUG_ELEMENT_CHILDREN : true;
    }

    protected void addDTDDefaultAttrsAndValidate(QName elementName, int elementIndex, XMLAttributes attributes) throws XNIException {
        if (elementIndex != TOP_LEVEL_SCOPE && this.fDTDGrammar != null) {
            int attrCount;
            int i;
            Object[] args;
            int attlistIndex = this.fDTDGrammar.getFirstAttributeDeclIndex(elementIndex);
            while (attlistIndex != TOP_LEVEL_SCOPE) {
                this.fDTDGrammar.getAttributeDecl(attlistIndex, this.fTempAttDecl);
                String attPrefix = this.fTempAttDecl.name.prefix;
                String attLocalpart = this.fTempAttDecl.name.localpart;
                String attRawName = this.fTempAttDecl.name.rawname;
                String attType = getAttributeTypeName(this.fTempAttDecl);
                int attDefaultType = this.fTempAttDecl.simpleType.defaultType;
                String attValue = null;
                if (this.fTempAttDecl.simpleType.defaultValue != null) {
                    attValue = this.fTempAttDecl.simpleType.defaultValue;
                }
                boolean specified = DEBUG_ELEMENT_CHILDREN;
                boolean required = attDefaultType == 2 ? true : DEBUG_ELEMENT_CHILDREN;
                if (!((attType == XMLSymbols.fCDATASymbol ? true : DEBUG_ELEMENT_CHILDREN) && !required && attValue == null)) {
                    attrCount = attributes.getLength();
                    for (i = 0; i < attrCount; i++) {
                        if (attributes.getQName(i) == attRawName) {
                            specified = true;
                            break;
                        }
                    }
                }
                if (!specified) {
                    if (required) {
                        if (this.fPerformValidation) {
                            args = new Object[2];
                            args[0] = elementName.localpart;
                            args[1] = attRawName;
                            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_REQUIRED_ATTRIBUTE_NOT_SPECIFIED", args, (short) 1);
                        }
                    } else if (attValue != null) {
                        if (this.fPerformValidation) {
                            if (this.fGrammarBucket.getStandalone()) {
                                if (this.fDTDGrammar.getAttributeDeclIsExternal(attlistIndex)) {
                                    args = new Object[2];
                                    args[0] = elementName.localpart;
                                    args[1] = attRawName;
                                    this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_DEFAULTED_ATTRIBUTE_NOT_SPECIFIED", args, (short) 1);
                                }
                            }
                        }
                        if (this.fNamespaces) {
                            int index = attRawName.indexOf(58);
                            if (index != TOP_LEVEL_SCOPE) {
                                attPrefix = this.fSymbolTable.addSymbol(attRawName.substring(0, index));
                                attLocalpart = this.fSymbolTable.addSymbol(attRawName.substring(index + 1));
                            }
                        }
                        this.fTempQName.setValues(attPrefix, attLocalpart, attRawName, this.fTempAttDecl.name.uri);
                        attributes.addAttribute(this.fTempQName, attType, attValue);
                    }
                }
                attlistIndex = this.fDTDGrammar.getNextAttributeDeclIndex(attlistIndex);
            }
            attrCount = attributes.getLength();
            for (i = 0; i < attrCount; i++) {
                String attrRawName = attributes.getQName(i);
                boolean declared = DEBUG_ELEMENT_CHILDREN;
                if (this.fPerformValidation) {
                    if (this.fGrammarBucket.getStandalone()) {
                        String nonNormalizedValue = attributes.getNonNormalizedValue(i);
                        if (!(nonNormalizedValue == null || getExternalEntityRefInAttrValue(nonNormalizedValue) == null)) {
                            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", new Object[]{getExternalEntityRefInAttrValue(nonNormalizedValue)}, (short) 1);
                        }
                    }
                }
                int position = this.fDTDGrammar.getFirstAttributeDeclIndex(elementIndex);
                while (position != TOP_LEVEL_SCOPE) {
                    this.fDTDGrammar.getAttributeDecl(position, this.fTempAttDecl);
                    String str = this.fTempAttDecl.name.rawname;
                    if (r0 == attrRawName) {
                        int attDefIndex = position;
                        declared = true;
                        break;
                    }
                    position = this.fDTDGrammar.getNextAttributeDeclIndex(position);
                }
                if (declared) {
                    String type = getAttributeTypeName(this.fTempAttDecl);
                    attributes.setType(i, type);
                    attributes.getAugmentations(i).putItem(Constants.ATTRIBUTE_DECLARED, Boolean.TRUE);
                    String attrValue = attributes.getValue(i);
                    if (attributes.isSpecified(i) && type != XMLSymbols.fCDATASymbol) {
                        boolean changedByNormalization = normalizeAttrValue(attributes, i);
                        attrValue = attributes.getValue(i);
                        if (this.fPerformValidation) {
                            if (this.fGrammarBucket.getStandalone() && changedByNormalization) {
                                if (this.fDTDGrammar.getAttributeDeclIsExternal(position)) {
                                    this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_ATTVALUE_CHANGED_DURING_NORMALIZATION_WHEN_STANDALONE", new Object[]{attrRawName, oldValue, attrValue}, (short) 1);
                                }
                            }
                        }
                    }
                    if (this.fPerformValidation) {
                        short s = this.fTempAttDecl.simpleType.defaultType;
                        if (r0 == (short) 1) {
                            String defaultValue = this.fTempAttDecl.simpleType.defaultValue;
                            if (!attrValue.equals(defaultValue)) {
                                args = new Object[4];
                                args[0] = elementName.localpart;
                                args[1] = attrRawName;
                                args[2] = attrValue;
                                args[3] = defaultValue;
                                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_FIXED_ATTVALUE_INVALID", args, (short) 1);
                            }
                        }
                        s = this.fTempAttDecl.simpleType.type;
                        if (r0 != (short) 1) {
                            s = this.fTempAttDecl.simpleType.type;
                            if (r0 != (short) 2) {
                                s = this.fTempAttDecl.simpleType.type;
                                if (r0 != (short) 3) {
                                    s = this.fTempAttDecl.simpleType.type;
                                    if (r0 != (short) 4) {
                                        s = this.fTempAttDecl.simpleType.type;
                                        if (r0 != (short) 5) {
                                            s = this.fTempAttDecl.simpleType.type;
                                            if (r0 != (short) 6) {
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        validateDTDattribute(elementName, attrValue, this.fTempAttDecl);
                    }
                } else if (this.fPerformValidation) {
                    args = new Object[2];
                    args[0] = elementName.rawname;
                    args[1] = attrRawName;
                    this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_ATTRIBUTE_NOT_DECLARED", args, (short) 1);
                }
            }
        }
    }

    protected String getExternalEntityRefInAttrValue(String nonNormalizedValue) {
        int valLength = nonNormalizedValue.length();
        int ampIndex = nonNormalizedValue.indexOf(38);
        while (ampIndex != TOP_LEVEL_SCOPE) {
            if (ampIndex + 1 < valLength && nonNormalizedValue.charAt(ampIndex + 1) != '#') {
                String entityName = this.fSymbolTable.addSymbol(nonNormalizedValue.substring(ampIndex + 1, nonNormalizedValue.indexOf(59, ampIndex + 1)));
                int entIndex = this.fDTDGrammar.getEntityDeclIndex(entityName);
                if (entIndex > TOP_LEVEL_SCOPE) {
                    this.fDTDGrammar.getEntityDecl(entIndex, this.fEntityDecl);
                    if (this.fEntityDecl.inExternal) {
                        return entityName;
                    }
                    entityName = getExternalEntityRefInAttrValue(this.fEntityDecl.value);
                    if (entityName != null) {
                        return entityName;
                    }
                } else {
                    continue;
                }
            }
            ampIndex = nonNormalizedValue.indexOf(38, ampIndex + 1);
        }
        return null;
    }

    protected void validateDTDattribute(QName element, String attValue, XMLAttributeDecl attributeDecl) throws XNIException {
        boolean isAlistAttribute;
        switch (attributeDecl.simpleType.type) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (attributeDecl.simpleType.list) {
                    try {
                        this.fValENTITIES.validate(attValue, this.fValidationState);
                        return;
                    } catch (InvalidDatatypeValueException ex) {
                        this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, ex.getKey(), ex.getArgs(), (short) 1);
                        return;
                    }
                }
                this.fValENTITY.validate(attValue, this.fValidationState);
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                int i;
                boolean found = DEBUG_ELEMENT_CHILDREN;
                String[] enumVals = attributeDecl.simpleType.enumeration;
                if (enumVals == null) {
                    found = DEBUG_ELEMENT_CHILDREN;
                } else {
                    i = 0;
                    while (i < enumVals.length) {
                        if (attValue == enumVals[i] || attValue.equals(enumVals[i])) {
                            found = true;
                        } else {
                            i++;
                        }
                    }
                }
                if (!found) {
                    StringBuffer enumValueString = new StringBuffer();
                    if (enumVals != null) {
                        for (Object obj : enumVals) {
                            enumValueString.append(obj + " ");
                        }
                    }
                    this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_ATTRIBUTE_VALUE_NOT_IN_LIST", new Object[]{attributeDecl.name.rawname, attValue, enumValueString}, (short) 1);
                }
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                try {
                    this.fValID.validate(attValue, this.fValidationState);
                } catch (InvalidDatatypeValueException ex2) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, ex2.getKey(), ex2.getArgs(), (short) 1);
                }
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                isAlistAttribute = attributeDecl.simpleType.list;
                if (isAlistAttribute) {
                    try {
                        this.fValIDRefs.validate(attValue, this.fValidationState);
                        return;
                    } catch (InvalidDatatypeValueException ex22) {
                        if (isAlistAttribute) {
                            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "IDREFSInvalid", new Object[]{attValue}, (short) 1);
                            return;
                        }
                        this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, ex22.getKey(), ex22.getArgs(), (short) 1);
                        return;
                    }
                }
                this.fValIDRef.validate(attValue, this.fValidationState);
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                isAlistAttribute = attributeDecl.simpleType.list;
                if (isAlistAttribute) {
                    try {
                        this.fValNMTOKENS.validate(attValue, this.fValidationState);
                        return;
                    } catch (InvalidDatatypeValueException e) {
                        if (isAlistAttribute) {
                            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "NMTOKENSInvalid", new Object[]{attValue}, (short) 1);
                            return;
                        }
                        this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "NMTOKENInvalid", new Object[]{attValue}, (short) 1);
                        return;
                    }
                }
                this.fValNMTOKEN.validate(attValue, this.fValidationState);
            default:
        }
    }

    protected boolean invalidStandaloneAttDef(QName element, QName attribute) {
        return true;
    }

    private boolean normalizeAttrValue(XMLAttributes attributes, int index) {
        boolean leadingSpace = true;
        boolean spaceStart = DEBUG_ELEMENT_CHILDREN;
        boolean readingNonSpace = DEBUG_ELEMENT_CHILDREN;
        int count = 0;
        int eaten = 0;
        String attrValue = attributes.getValue(index);
        char[] attValue = new char[attrValue.length()];
        this.fBuffer.setLength(0);
        attrValue.getChars(0, attrValue.length(), attValue, 0);
        for (int i = 0; i < attValue.length; i++) {
            if (attValue[i] == ' ') {
                if (readingNonSpace) {
                    spaceStart = true;
                    readingNonSpace = DEBUG_ELEMENT_CHILDREN;
                }
                if (spaceStart && !leadingSpace) {
                    spaceStart = DEBUG_ELEMENT_CHILDREN;
                    this.fBuffer.append(attValue[i]);
                    count++;
                } else if (leadingSpace || !spaceStart) {
                    eaten++;
                }
            } else {
                readingNonSpace = true;
                spaceStart = DEBUG_ELEMENT_CHILDREN;
                leadingSpace = DEBUG_ELEMENT_CHILDREN;
                this.fBuffer.append(attValue[i]);
                count++;
            }
        }
        if (count > 0 && this.fBuffer.charAt(count + TOP_LEVEL_SCOPE) == ' ') {
            this.fBuffer.setLength(count + TOP_LEVEL_SCOPE);
        }
        String newValue = this.fBuffer.toString();
        attributes.setValue(index, newValue);
        if (attrValue.equals(newValue)) {
            return DEBUG_ELEMENT_CHILDREN;
        }
        return true;
    }

    private final void rootElementSpecified(QName rootElement) throws XNIException {
        if (this.fPerformValidation) {
            String root1 = this.fRootElement.rawname;
            String root2 = rootElement.rawname;
            if (root1 == null || !root1.equals(root2)) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "RootElementTypeMustMatchDoctypedecl", new Object[]{root1, root2}, (short) 1);
            }
        }
    }

    private int checkContent(int elementIndex, QName[] children, int childOffset, int childCount) throws XNIException {
        this.fDTDGrammar.getElementDecl(elementIndex, this.fTempElementDecl);
        String elementType = this.fCurrentElement.rawname;
        int contentType = this.fCurrentContentSpecType;
        if (contentType == 1) {
            if (childCount != 0) {
                return 0;
            }
            return TOP_LEVEL_SCOPE;
        } else if (contentType == 0) {
            return TOP_LEVEL_SCOPE;
        } else {
            if (contentType == 2 || contentType == 3) {
                return this.fTempElementDecl.contentModelValidator.validate(children, childOffset, childCount);
            }
            return contentType != TOP_LEVEL_SCOPE ? TOP_LEVEL_SCOPE : TOP_LEVEL_SCOPE;
        }
    }

    private int getContentSpecType(int elementIndex) {
        if (elementIndex <= TOP_LEVEL_SCOPE || !this.fDTDGrammar.getElementDecl(elementIndex, this.fTempElementDecl)) {
            return TOP_LEVEL_SCOPE;
        }
        return this.fTempElementDecl.type;
    }

    private void charDataInContent() {
        if (this.fElementChildren.length <= this.fElementChildrenLength) {
            QName[] newarray = new QName[(this.fElementChildren.length * 2)];
            System.arraycopy(this.fElementChildren, 0, newarray, 0, this.fElementChildren.length);
            this.fElementChildren = newarray;
        }
        QName qname = this.fElementChildren[this.fElementChildrenLength];
        if (qname == null) {
            for (int i = this.fElementChildrenLength; i < this.fElementChildren.length; i++) {
                this.fElementChildren[i] = new QName();
            }
            qname = this.fElementChildren[this.fElementChildrenLength];
        }
        qname.clear();
        this.fElementChildrenLength++;
    }

    private String getAttributeTypeName(XMLAttributeDecl attrDecl) {
        switch (attrDecl.simpleType.type) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                return attrDecl.simpleType.list ? XMLSymbols.fENTITIESSymbol : XMLSymbols.fENTITYSymbol;
            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                StringBuffer buffer = new StringBuffer();
                buffer.append('(');
                for (int i = 0; i < attrDecl.simpleType.enumeration.length; i++) {
                    if (i > 0) {
                        buffer.append('|');
                    }
                    buffer.append(attrDecl.simpleType.enumeration[i]);
                }
                buffer.append(')');
                return this.fSymbolTable.addSymbol(buffer.toString());
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return XMLSymbols.fIDSymbol;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                return attrDecl.simpleType.list ? XMLSymbols.fIDREFSSymbol : XMLSymbols.fIDREFSymbol;
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                return attrDecl.simpleType.list ? XMLSymbols.fNMTOKENSSymbol : XMLSymbols.fNMTOKENSymbol;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                return XMLSymbols.fNOTATIONSymbol;
            default:
                return XMLSymbols.fCDATASymbol;
        }
    }

    protected void init() {
        if (this.fValidation || this.fDynamicValidation) {
            try {
                this.fValID = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fIDSymbol);
                this.fValIDRef = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fIDREFSymbol);
                this.fValIDRefs = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fIDREFSSymbol);
                this.fValENTITY = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fENTITYSymbol);
                this.fValENTITIES = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fENTITIESSymbol);
                this.fValNMTOKEN = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fNMTOKENSymbol);
                this.fValNMTOKENS = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fNMTOKENSSymbol);
                this.fValNOTATION = this.fDatatypeValidatorFactory.getBuiltInDV(XMLSymbols.fNOTATIONSymbol);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private void ensureStackCapacity(int newElementDepth) {
        if (newElementDepth == this.fElementQNamePartsStack.length) {
            QName[] newStackOfQueue = new QName[(newElementDepth * 2)];
            System.arraycopy(this.fElementQNamePartsStack, 0, newStackOfQueue, 0, newElementDepth);
            this.fElementQNamePartsStack = newStackOfQueue;
            if (this.fElementQNamePartsStack[newElementDepth] == null) {
                for (int i = newElementDepth; i < this.fElementQNamePartsStack.length; i++) {
                    this.fElementQNamePartsStack[i] = new QName();
                }
            }
            int[] newStack = new int[(newElementDepth * 2)];
            System.arraycopy(this.fElementIndexStack, 0, newStack, 0, newElementDepth);
            this.fElementIndexStack = newStack;
            newStack = new int[(newElementDepth * 2)];
            System.arraycopy(this.fContentSpecTypeStack, 0, newStack, 0, newElementDepth);
            this.fContentSpecTypeStack = newStack;
        }
    }

    protected boolean handleStartElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        boolean z = true;
        if (!this.fSeenRootElement) {
            this.fPerformValidation = validate();
            this.fSeenRootElement = true;
            this.fValidationManager.setEntityState(this.fDTDGrammar);
            this.fValidationManager.setGrammarFound(this.fSeenDoctypeDecl);
            rootElementSpecified(element);
        }
        if (this.fDTDGrammar == null) {
            if (!this.fPerformValidation) {
                this.fCurrentElementIndex = TOP_LEVEL_SCOPE;
                this.fCurrentContentSpecType = TOP_LEVEL_SCOPE;
                this.fInElementContent = DEBUG_ELEMENT_CHILDREN;
            }
            if (this.fPerformValidation) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_GRAMMAR_NOT_FOUND", new Object[]{element.rawname}, (short) 1);
            }
            if (this.fDocumentSource != null) {
                this.fDocumentSource.setDocumentHandler(this.fDocumentHandler);
                if (this.fDocumentHandler == null) {
                    return true;
                }
                this.fDocumentHandler.setDocumentSource(this.fDocumentSource);
                return true;
            }
        }
        this.fCurrentElementIndex = this.fDTDGrammar.getElementDeclIndex(element);
        this.fCurrentContentSpecType = this.fDTDGrammar.getContentSpecType(this.fCurrentElementIndex);
        if (this.fCurrentContentSpecType == TOP_LEVEL_SCOPE && this.fPerformValidation) {
            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_ELEMENT_NOT_DECLARED", new Object[]{element.rawname}, (short) 1);
        }
        addDTDDefaultAttrsAndValidate(element, this.fCurrentElementIndex, attributes);
        if (this.fCurrentContentSpecType != 3) {
            z = DEBUG_ELEMENT_CHILDREN;
        }
        this.fInElementContent = z;
        this.fElementDepth++;
        if (this.fPerformValidation) {
            if (this.fElementChildrenOffsetStack.length <= this.fElementDepth) {
                int[] newarray = new int[(this.fElementChildrenOffsetStack.length * 2)];
                System.arraycopy(this.fElementChildrenOffsetStack, 0, newarray, 0, this.fElementChildrenOffsetStack.length);
                this.fElementChildrenOffsetStack = newarray;
            }
            this.fElementChildrenOffsetStack[this.fElementDepth] = this.fElementChildrenLength;
            if (this.fElementChildren.length <= this.fElementChildrenLength) {
                QName[] newarray2 = new QName[(this.fElementChildrenLength * 2)];
                System.arraycopy(this.fElementChildren, 0, newarray2, 0, this.fElementChildren.length);
                this.fElementChildren = newarray2;
            }
            QName qname = this.fElementChildren[this.fElementChildrenLength];
            if (qname == null) {
                for (int i = this.fElementChildrenLength; i < this.fElementChildren.length; i++) {
                    this.fElementChildren[i] = new QName();
                }
                qname = this.fElementChildren[this.fElementChildrenLength];
            }
            qname.setValues(element);
            this.fElementChildrenLength++;
        }
        this.fCurrentElement.setValues(element);
        ensureStackCapacity(this.fElementDepth);
        this.fElementQNamePartsStack[this.fElementDepth].setValues(this.fCurrentElement);
        this.fElementIndexStack[this.fElementDepth] = this.fCurrentElementIndex;
        this.fContentSpecTypeStack[this.fElementDepth] = this.fCurrentContentSpecType;
        startNamespaceScope(element, attributes, augs);
        return DEBUG_ELEMENT_CHILDREN;
    }

    protected void startNamespaceScope(QName element, XMLAttributes attributes, Augmentations augs) {
    }

    protected void handleEndElement(QName element, Augmentations augs, boolean isEmpty) throws XNIException {
        this.fElementDepth += TOP_LEVEL_SCOPE;
        if (this.fPerformValidation) {
            int elementIndex = this.fCurrentElementIndex;
            if (!(elementIndex == TOP_LEVEL_SCOPE || this.fCurrentContentSpecType == TOP_LEVEL_SCOPE)) {
                int childrenOffset = this.fElementChildrenOffsetStack[this.fElementDepth + 1] + 1;
                int childrenLength = this.fElementChildrenLength - childrenOffset;
                int result = checkContent(elementIndex, this.fElementChildren, childrenOffset, childrenLength);
                if (result != TOP_LEVEL_SCOPE) {
                    this.fDTDGrammar.getElementDecl(elementIndex, this.fTempElementDecl);
                    if (this.fTempElementDecl.type == (short) 1) {
                        this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_CONTENT_INVALID", new Object[]{element.rawname, "EMPTY"}, (short) 1);
                    } else {
                        this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, result != childrenLength ? "MSG_CONTENT_INVALID" : "MSG_CONTENT_INCOMPLETE", new Object[]{element.rawname, this.fDTDGrammar.getContentSpecAsString(elementIndex)}, (short) 1);
                    }
                }
            }
            this.fElementChildrenLength = this.fElementChildrenOffsetStack[this.fElementDepth + 1] + 1;
        }
        endNamespaceScope(this.fCurrentElement, augs, isEmpty);
        if (this.fElementDepth < TOP_LEVEL_SCOPE) {
            throw new RuntimeException("FWK008 Element stack underflow");
        } else if (this.fElementDepth < 0) {
            this.fCurrentElement.clear();
            this.fCurrentElementIndex = TOP_LEVEL_SCOPE;
            this.fCurrentContentSpecType = TOP_LEVEL_SCOPE;
            this.fInElementContent = DEBUG_ELEMENT_CHILDREN;
            if (this.fPerformValidation && this.fValidationState.checkIDRefID() != null) {
                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "MSG_ELEMENT_WITH_ID_REQUIRED", new Object[]{this.fValidationState.checkIDRefID()}, (short) 1);
            }
        } else {
            this.fCurrentElement.setValues(this.fElementQNamePartsStack[this.fElementDepth]);
            this.fCurrentElementIndex = this.fElementIndexStack[this.fElementDepth];
            this.fCurrentContentSpecType = this.fContentSpecTypeStack[this.fElementDepth];
            this.fInElementContent = this.fCurrentContentSpecType == 3 ? true : DEBUG_ELEMENT_CHILDREN;
        }
    }

    protected void endNamespaceScope(QName element, Augmentations augs, boolean isEmpty) {
        if (this.fDocumentHandler != null && !isEmpty) {
            this.fDocumentHandler.endElement(this.fCurrentElement, augs);
        }
    }

    protected boolean isSpace(int c) {
        return XMLChar.isSpace(c);
    }

    public boolean characterData(String data, Augmentations augs) {
        characters(new XMLString(data.toCharArray(), 0, data.length()), augs);
        return true;
    }
}
