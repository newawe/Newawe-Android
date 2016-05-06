package mf.org.apache.xerces.parsers;

import android.support.v4.media.TransportMediator;
import com.google.android.gms.common.ConnectionResult;
import java.util.Locale;
import java.util.Stack;
import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.dom.AttrImpl;
import mf.org.apache.xerces.dom.CoreDocumentImpl;
import mf.org.apache.xerces.dom.DOMErrorImpl;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.dom.DeferredDocumentImpl;
import mf.org.apache.xerces.dom.DocumentImpl;
import mf.org.apache.xerces.dom.DocumentTypeImpl;
import mf.org.apache.xerces.dom.ElementDefinitionImpl;
import mf.org.apache.xerces.dom.ElementImpl;
import mf.org.apache.xerces.dom.ElementNSImpl;
import mf.org.apache.xerces.dom.EntityImpl;
import mf.org.apache.xerces.dom.EntityReferenceImpl;
import mf.org.apache.xerces.dom.NodeImpl;
import mf.org.apache.xerces.dom.NotationImpl;
import mf.org.apache.xerces.dom.PSVIAttrNSImpl;
import mf.org.apache.xerces.dom.PSVIDocumentImpl;
import mf.org.apache.xerces.dom.PSVIElementNSImpl;
import mf.org.apache.xerces.dom.TextImpl;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.util.DOMErrorHandlerWrapper;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;
import mf.org.apache.xerces.xs.AttributePSVI;
import mf.org.apache.xerces.xs.ElementPSVI;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.CDATASection;
import mf.org.w3c.dom.Comment;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.EntityReference;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.ProcessingInstruction;
import mf.org.w3c.dom.Text;
import mf.org.w3c.dom.ls.LSParserFilter;
import mf.org.w3c.dom.traversal.NodeFilter;

public class AbstractDOMParser extends AbstractXMLDocumentParser {
    protected static final String CORE_DOCUMENT_CLASS_NAME = "mf.org.apache.xerces.dom.CoreDocumentImpl";
    protected static final String CREATE_CDATA_NODES_FEATURE = "http://apache.org/xml/features/create-cdata-nodes";
    protected static final String CREATE_ENTITY_REF_NODES = "http://apache.org/xml/features/dom/create-entity-ref-nodes";
    protected static final String CURRENT_ELEMENT_NODE = "http://apache.org/xml/properties/dom/current-element-node";
    private static final boolean DEBUG_BASEURI = false;
    private static final boolean DEBUG_EVENTS = false;
    protected static final String DEFAULT_DOCUMENT_CLASS_NAME = "mf.org.apache.xerces.dom.DocumentImpl";
    protected static final String DEFER_NODE_EXPANSION = "http://apache.org/xml/features/dom/defer-node-expansion";
    protected static final String DOCUMENT_CLASS_NAME = "http://apache.org/xml/properties/dom/document-class-name";
    protected static final String INCLUDE_COMMENTS_FEATURE = "http://apache.org/xml/features/include-comments";
    protected static final String INCLUDE_IGNORABLE_WHITESPACE = "http://apache.org/xml/features/dom/include-ignorable-whitespace";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String PSVI_DOCUMENT_CLASS_NAME = "mf.org.apache.xerces.dom.PSVIDocumentImpl";
    private static final String[] RECOGNIZED_FEATURES;
    private static final String[] RECOGNIZED_PROPERTIES;
    private final QName fAttrQName;
    protected final Stack fBaseURIStack;
    protected boolean fCreateCDATANodes;
    protected boolean fCreateEntityRefNodes;
    protected CDATASection fCurrentCDATASection;
    protected int fCurrentCDATASectionIndex;
    protected EntityImpl fCurrentEntityDecl;
    protected Node fCurrentNode;
    protected int fCurrentNodeIndex;
    protected LSParserFilter fDOMFilter;
    protected boolean fDeferNodeExpansion;
    protected DeferredDocumentImpl fDeferredDocumentImpl;
    protected int fDeferredEntityDecl;
    protected Document fDocument;
    protected String fDocumentClassName;
    protected CoreDocumentImpl fDocumentImpl;
    protected int fDocumentIndex;
    protected DocumentType fDocumentType;
    protected int fDocumentTypeIndex;
    protected DOMErrorHandlerWrapper fErrorHandler;
    protected boolean fFilterReject;
    protected boolean fFirstChunk;
    protected boolean fInCDATASection;
    protected boolean fInDTD;
    protected boolean fInDTDExternalSubset;
    protected boolean fInEntityRef;
    protected boolean fIncludeComments;
    protected boolean fIncludeIgnorableWhitespace;
    protected StringBuffer fInternalSubset;
    private XMLLocator fLocator;
    protected boolean fNamespaceAware;
    protected int fRejectedElementDepth;
    protected Node fRoot;
    protected Stack fSkippedElemStack;
    protected boolean fStorePSVI;
    protected final StringBuffer fStringBuffer;

    static final class Abort extends RuntimeException {
        static final Abort INSTANCE;
        private static final long serialVersionUID = 1687848994976808490L;

        static {
            INSTANCE = new Abort();
        }

        private Abort() {
        }

        public Throwable fillInStackTrace() {
            return this;
        }
    }

    static {
        RECOGNIZED_FEATURES = new String[]{NAMESPACES, CREATE_ENTITY_REF_NODES, INCLUDE_COMMENTS_FEATURE, CREATE_CDATA_NODES_FEATURE, INCLUDE_IGNORABLE_WHITESPACE, DEFER_NODE_EXPANSION};
        RECOGNIZED_PROPERTIES = new String[]{DOCUMENT_CLASS_NAME, CURRENT_ELEMENT_NODE};
    }

    protected AbstractDOMParser(XMLParserConfiguration config) {
        super(config);
        this.fErrorHandler = null;
        this.fStringBuffer = new StringBuffer(50);
        this.fFirstChunk = DEBUG_EVENTS;
        this.fFilterReject = DEBUG_EVENTS;
        this.fBaseURIStack = new Stack();
        this.fRejectedElementDepth = 0;
        this.fSkippedElemStack = null;
        this.fInEntityRef = DEBUG_EVENTS;
        this.fAttrQName = new QName();
        this.fDOMFilter = null;
        this.fConfiguration.addRecognizedFeatures(RECOGNIZED_FEATURES);
        this.fConfiguration.setFeature(CREATE_ENTITY_REF_NODES, true);
        this.fConfiguration.setFeature(INCLUDE_IGNORABLE_WHITESPACE, true);
        this.fConfiguration.setFeature(DEFER_NODE_EXPANSION, true);
        this.fConfiguration.setFeature(INCLUDE_COMMENTS_FEATURE, true);
        this.fConfiguration.setFeature(CREATE_CDATA_NODES_FEATURE, true);
        this.fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
        this.fConfiguration.setProperty(DOCUMENT_CLASS_NAME, DEFAULT_DOCUMENT_CLASS_NAME);
    }

    protected String getDocumentClassName() {
        return this.fDocumentClassName;
    }

    protected void setDocumentClassName(String documentClassName) {
        if (documentClassName == null) {
            documentClassName = DEFAULT_DOCUMENT_CLASS_NAME;
        }
        if (!(documentClassName.equals(DEFAULT_DOCUMENT_CLASS_NAME) || documentClassName.equals(PSVI_DOCUMENT_CLASS_NAME))) {
            try {
                if (!Document.class.isAssignableFrom(ObjectFactory.findProviderClass(documentClassName, ObjectFactory.findClassLoader(), true))) {
                    throw new IllegalArgumentException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "InvalidDocumentClassName", new Object[]{documentClassName}));
                }
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "MissingDocumentClassName", new Object[]{documentClassName}));
            }
        }
        this.fDocumentClassName = documentClassName;
        if (!documentClassName.equals(DEFAULT_DOCUMENT_CLASS_NAME)) {
            this.fDeferNodeExpansion = DEBUG_EVENTS;
        }
    }

    public Document getDocument() {
        return this.fDocument;
    }

    public final void dropDocumentReferences() {
        this.fDocument = null;
        this.fDocumentImpl = null;
        this.fDeferredDocumentImpl = null;
        this.fDocumentType = null;
        this.fCurrentNode = null;
        this.fCurrentCDATASection = null;
        this.fCurrentEntityDecl = null;
        this.fRoot = null;
    }

    public void reset() throws XNIException {
        super.reset();
        this.fCreateEntityRefNodes = this.fConfiguration.getFeature(CREATE_ENTITY_REF_NODES);
        this.fIncludeIgnorableWhitespace = this.fConfiguration.getFeature(INCLUDE_IGNORABLE_WHITESPACE);
        this.fDeferNodeExpansion = this.fConfiguration.getFeature(DEFER_NODE_EXPANSION);
        this.fNamespaceAware = this.fConfiguration.getFeature(NAMESPACES);
        this.fIncludeComments = this.fConfiguration.getFeature(INCLUDE_COMMENTS_FEATURE);
        this.fCreateCDATANodes = this.fConfiguration.getFeature(CREATE_CDATA_NODES_FEATURE);
        setDocumentClassName((String) this.fConfiguration.getProperty(DOCUMENT_CLASS_NAME));
        this.fDocument = null;
        this.fDocumentImpl = null;
        this.fStorePSVI = DEBUG_EVENTS;
        this.fDocumentType = null;
        this.fDocumentTypeIndex = -1;
        this.fDeferredDocumentImpl = null;
        this.fCurrentNode = null;
        this.fStringBuffer.setLength(0);
        this.fRoot = null;
        this.fInDTD = DEBUG_EVENTS;
        this.fInDTDExternalSubset = DEBUG_EVENTS;
        this.fInCDATASection = DEBUG_EVENTS;
        this.fFirstChunk = DEBUG_EVENTS;
        this.fCurrentCDATASection = null;
        this.fCurrentCDATASectionIndex = -1;
        this.fBaseURIStack.removeAllElements();
    }

    public void setLocale(Locale locale) {
        this.fConfiguration.setLocale(locale);
    }

    public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        if (this.fDeferNodeExpansion) {
            int er = this.fDeferredDocumentImpl.createDeferredEntityReference(name, identifier.getExpandedSystemId());
            if (this.fDocumentTypeIndex != -1) {
                int node = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, DEBUG_EVENTS);
                while (node != -1) {
                    if (this.fDeferredDocumentImpl.getNodeType(node, DEBUG_EVENTS) == (short) 6 && this.fDeferredDocumentImpl.getNodeName(node, DEBUG_EVENTS).equals(name)) {
                        this.fDeferredEntityDecl = node;
                        this.fDeferredDocumentImpl.setInputEncoding(node, encoding);
                        break;
                    }
                    node = this.fDeferredDocumentImpl.getRealPrevSibling(node, DEBUG_EVENTS);
                }
            }
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, er);
            this.fCurrentNodeIndex = er;
        } else if (!this.fFilterReject) {
            setCharacterData(true);
            EntityReference er2 = this.fDocument.createEntityReference(name);
            if (this.fDocumentImpl != null) {
                EntityReferenceImpl erImpl = (EntityReferenceImpl) er2;
                erImpl.setBaseURI(identifier.getExpandedSystemId());
                if (this.fDocumentType != null) {
                    this.fCurrentEntityDecl = (EntityImpl) this.fDocumentType.getEntities().getNamedItem(name);
                    if (this.fCurrentEntityDecl != null) {
                        this.fCurrentEntityDecl.setInputEncoding(encoding);
                    }
                }
                erImpl.needsSyncChildren(DEBUG_EVENTS);
            }
            this.fInEntityRef = true;
            this.fCurrentNode.appendChild(er2);
            this.fCurrentNode = er2;
        }
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
        if (!this.fInDTD) {
            if (this.fDeferNodeExpansion) {
                if (this.fDeferredEntityDecl != -1) {
                    this.fDeferredDocumentImpl.setEntityInfo(this.fDeferredEntityDecl, version, encoding);
                }
            } else if (this.fCurrentEntityDecl != null && !this.fFilterReject) {
                this.fCurrentEntityDecl.setXmlEncoding(encoding);
                if (version != null) {
                    this.fCurrentEntityDecl.setXmlVersion(version);
                }
            }
        }
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
        if (this.fInDTD) {
            if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
                this.fInternalSubset.append("<!--");
                if (text.length > 0) {
                    this.fInternalSubset.append(text.ch, text.offset, text.length);
                }
                this.fInternalSubset.append("-->");
            }
        } else if (this.fIncludeComments && !this.fFilterReject) {
            if (this.fDeferNodeExpansion) {
                this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDeferredDocumentImpl.createDeferredComment(text.toString()));
                return;
            }
            Comment comment = this.fDocument.createComment(text.toString());
            setCharacterData(DEBUG_EVENTS);
            this.fCurrentNode.appendChild(comment);
            if (this.fDOMFilter != null && !this.fInEntityRef && (this.fDOMFilter.getWhatToShow() & TransportMediator.FLAG_KEY_MEDIA_NEXT) != 0) {
                switch (this.fDOMFilter.acceptNode(comment)) {
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        this.fCurrentNode.removeChild(comment);
                        this.fFirstChunk = true;
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        throw Abort.INSTANCE;
                    default:
                }
            }
        }
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
        if (this.fInDTD) {
            if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
                this.fInternalSubset.append("<?");
                this.fInternalSubset.append(target);
                if (data.length > 0) {
                    this.fInternalSubset.append(' ').append(data.ch, data.offset, data.length);
                }
                this.fInternalSubset.append("?>");
            }
        } else if (this.fDeferNodeExpansion) {
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDeferredDocumentImpl.createDeferredProcessingInstruction(target, data.toString()));
        } else if (!this.fFilterReject) {
            ProcessingInstruction pi = this.fDocument.createProcessingInstruction(target, data.toString());
            setCharacterData(DEBUG_EVENTS);
            this.fCurrentNode.appendChild(pi);
            if (this.fDOMFilter != null && !this.fInEntityRef && (this.fDOMFilter.getWhatToShow() & 64) != 0) {
                switch (this.fDOMFilter.acceptNode(pi)) {
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        this.fCurrentNode.removeChild(pi);
                        this.fFirstChunk = true;
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        throw Abort.INSTANCE;
                    default:
                }
            }
        }
    }

    public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
        this.fLocator = locator;
        if (this.fDeferNodeExpansion) {
            this.fDeferredDocumentImpl = new DeferredDocumentImpl(this.fNamespaceAware);
            this.fDocument = this.fDeferredDocumentImpl;
            this.fDocumentIndex = this.fDeferredDocumentImpl.createDeferredDocument();
            this.fDeferredDocumentImpl.setInputEncoding(encoding);
            this.fDeferredDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
            this.fCurrentNodeIndex = this.fDocumentIndex;
            return;
        }
        if (this.fDocumentClassName.equals(DEFAULT_DOCUMENT_CLASS_NAME)) {
            this.fDocument = new DocumentImpl();
            this.fDocumentImpl = (CoreDocumentImpl) this.fDocument;
            this.fDocumentImpl.setStrictErrorChecking(DEBUG_EVENTS);
            this.fDocumentImpl.setInputEncoding(encoding);
            this.fDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
        } else if (this.fDocumentClassName.equals(PSVI_DOCUMENT_CLASS_NAME)) {
            this.fDocument = new PSVIDocumentImpl();
            this.fDocumentImpl = (CoreDocumentImpl) this.fDocument;
            this.fStorePSVI = true;
            this.fDocumentImpl.setStrictErrorChecking(DEBUG_EVENTS);
            this.fDocumentImpl.setInputEncoding(encoding);
            this.fDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
        } else {
            try {
                ClassLoader cl = ObjectFactory.findClassLoader();
                Class documentClass = ObjectFactory.findProviderClass(this.fDocumentClassName, cl, true);
                this.fDocument = (Document) documentClass.newInstance();
                if (ObjectFactory.findProviderClass(CORE_DOCUMENT_CLASS_NAME, cl, true).isAssignableFrom(documentClass)) {
                    this.fDocumentImpl = (CoreDocumentImpl) this.fDocument;
                    if (ObjectFactory.findProviderClass(PSVI_DOCUMENT_CLASS_NAME, cl, true).isAssignableFrom(documentClass)) {
                        this.fStorePSVI = true;
                    }
                    this.fDocumentImpl.setStrictErrorChecking(DEBUG_EVENTS);
                    this.fDocumentImpl.setInputEncoding(encoding);
                    if (locator != null) {
                        this.fDocumentImpl.setDocumentURI(locator.getExpandedSystemId());
                    }
                }
            } catch (ClassNotFoundException e) {
            } catch (Exception e2) {
                throw new RuntimeException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "CannotCreateDocumentClass", new Object[]{this.fDocumentClassName}));
            }
        }
        this.fCurrentNode = this.fDocument;
    }

    public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
        if (this.fDeferNodeExpansion) {
            if (version != null) {
                this.fDeferredDocumentImpl.setXmlVersion(version);
            }
            this.fDeferredDocumentImpl.setXmlEncoding(encoding);
            this.fDeferredDocumentImpl.setXmlStandalone("yes".equals(standalone));
        } else if (this.fDocumentImpl != null) {
            if (version != null) {
                this.fDocumentImpl.setXmlVersion(version);
            }
            this.fDocumentImpl.setXmlEncoding(encoding);
            this.fDocumentImpl.setXmlStandalone("yes".equals(standalone));
        }
    }

    public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
        if (this.fDeferNodeExpansion) {
            this.fDocumentTypeIndex = this.fDeferredDocumentImpl.createDeferredDocumentType(rootElement, publicId, systemId);
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDocumentTypeIndex);
        } else if (this.fDocumentImpl != null) {
            this.fDocumentType = this.fDocumentImpl.createDocumentType(rootElement, publicId, systemId);
            this.fCurrentNode.appendChild(this.fDocumentType);
        }
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        XSTypeDefinition type;
        int i;
        AttributePSVI attrPSVI;
        boolean id;
        if (this.fDeferNodeExpansion) {
            int el = this.fDeferredDocumentImpl.createDeferredElement(this.fNamespaceAware ? element.uri : null, element.rawname);
            type = null;
            i = attributes.getLength() - 1;
            while (i >= 0) {
                XSTypeDefinition xSTypeDefinition;
                attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
                id = DEBUG_EVENTS;
                if (attrPSVI != null && this.fNamespaceAware) {
                    type = attrPSVI.getMemberTypeDefinition();
                    if (type == null) {
                        type = attrPSVI.getTypeDefinition();
                        if (type != null) {
                            id = ((XSSimpleType) type).isIDType();
                            xSTypeDefinition = type;
                        } else {
                            xSTypeDefinition = type;
                        }
                    } else {
                        id = ((XSSimpleType) type).isIDType();
                        xSTypeDefinition = type;
                    }
                } else if (Boolean.TRUE.equals(attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_DECLARED))) {
                    type = attributes.getType(i);
                    id = SchemaSymbols.ATTVAL_ID.equals(type);
                    xSTypeDefinition = type;
                } else {
                    xSTypeDefinition = type;
                }
                this.fDeferredDocumentImpl.setDeferredAttribute(el, attributes.getQName(i), attributes.getURI(i), attributes.getValue(i), attributes.isSpecified(i), id, xSTypeDefinition);
                i--;
                type = xSTypeDefinition;
            }
            this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, el);
            this.fCurrentNodeIndex = el;
        } else if (this.fFilterReject) {
            this.fRejectedElementDepth++;
        } else {
            Element el2 = createElementNode(element);
            int attrCount = attributes.getLength();
            boolean seenSchemaDefault = DEBUG_EVENTS;
            for (i = 0; i < attrCount; i++) {
                attributes.getName(i, this.fAttrQName);
                Attr attr = createAttrNode(this.fAttrQName);
                String attrValue = attributes.getValue(i);
                attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
                if (this.fStorePSVI && attrPSVI != null) {
                    ((PSVIAttrNSImpl) attr).setPSVI(attrPSVI);
                }
                attr.setValue(attrValue);
                boolean specified = attributes.isSpecified(i);
                if (specified || (!seenSchemaDefault && (this.fAttrQName.uri == null || this.fAttrQName.uri == NamespaceContext.XMLNS_URI || this.fAttrQName.prefix != null))) {
                    el2.setAttributeNode(attr);
                } else {
                    el2.setAttributeNodeNS(attr);
                    seenSchemaDefault = true;
                }
                if (this.fDocumentImpl != null) {
                    AttrImpl attrImpl = (AttrImpl) attr;
                    String type2 = null;
                    id = DEBUG_EVENTS;
                    if (attrPSVI == null || !this.fNamespaceAware) {
                        if (Boolean.TRUE.equals(attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_DECLARED))) {
                            type2 = attributes.getType(i);
                            id = SchemaSymbols.ATTVAL_ID.equals(type2);
                        }
                        attrImpl.setType(type2);
                    } else {
                        Object type3 = attrPSVI.getMemberTypeDefinition();
                        if (type3 == null) {
                            type3 = attrPSVI.getTypeDefinition();
                            if (type3 != null) {
                                id = ((XSSimpleType) type3).isIDType();
                                attrImpl.setType(type3);
                            }
                        } else {
                            id = ((XSSimpleType) type3).isIDType();
                            attrImpl.setType(type3);
                        }
                    }
                    if (id) {
                        ((ElementImpl) el2).setIdAttributeNode(attr, true);
                    }
                    attrImpl.setSpecified(specified);
                }
            }
            setCharacterData(DEBUG_EVENTS);
            if (augs != null) {
                ElementPSVI elementPSVI = (ElementPSVI) augs.getItem(Constants.ELEMENT_PSVI);
                if (elementPSVI != null && this.fNamespaceAware) {
                    type = elementPSVI.getMemberTypeDefinition();
                    if (type == null) {
                        type = elementPSVI.getTypeDefinition();
                    }
                    ((ElementNSImpl) el2).setType(type);
                }
            }
            if (!(this.fDOMFilter == null || this.fInEntityRef)) {
                if (this.fRoot != null) {
                    switch (this.fDOMFilter.startElement(el2)) {
                        case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                            this.fFilterReject = true;
                            this.fRejectedElementDepth = 0;
                            return;
                        case ConnectionResult.SERVICE_DISABLED /*3*/:
                            this.fFirstChunk = true;
                            this.fSkippedElemStack.push(Boolean.TRUE);
                            return;
                        case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                            throw Abort.INSTANCE;
                        default:
                            if (!this.fSkippedElemStack.isEmpty()) {
                                this.fSkippedElemStack.push(Boolean.FALSE);
                                break;
                            }
                            break;
                    }
                }
                this.fRoot = el2;
            }
            this.fCurrentNode.appendChild(el2);
            this.fCurrentNode = el2;
        }
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        startElement(element, attributes, augs);
        endElement(element, augs);
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
        if (this.fDeferNodeExpansion) {
            if (this.fInCDATASection && this.fCreateCDATANodes) {
                if (this.fCurrentCDATASectionIndex == -1) {
                    int cs = this.fDeferredDocumentImpl.createDeferredCDATASection(text.toString());
                    this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, cs);
                    this.fCurrentCDATASectionIndex = cs;
                    this.fCurrentNodeIndex = cs;
                    return;
                }
                this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDeferredDocumentImpl.createDeferredTextNode(text.toString(), DEBUG_EVENTS));
            } else if (!this.fInDTD && text.length != 0) {
                this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDeferredDocumentImpl.createDeferredTextNode(text.toString(), DEBUG_EVENTS));
            }
        } else if (!this.fFilterReject) {
            if (this.fInCDATASection && this.fCreateCDATANodes) {
                if (this.fCurrentCDATASection == null) {
                    this.fCurrentCDATASection = this.fDocument.createCDATASection(text.toString());
                    this.fCurrentNode.appendChild(this.fCurrentCDATASection);
                    this.fCurrentNode = this.fCurrentCDATASection;
                    return;
                }
                this.fCurrentCDATASection.appendData(text.toString());
            } else if (!this.fInDTD && text.length != 0) {
                Node child = this.fCurrentNode.getLastChild();
                if (child == null || child.getNodeType() != (short) 3) {
                    this.fFirstChunk = true;
                    this.fCurrentNode.appendChild(this.fDocument.createTextNode(text.toString()));
                    return;
                }
                if (this.fFirstChunk) {
                    if (this.fDocumentImpl != null) {
                        this.fStringBuffer.append(((TextImpl) child).removeData());
                    } else {
                        this.fStringBuffer.append(((Text) child).getData());
                        ((Text) child).setNodeValue(null);
                    }
                    this.fFirstChunk = DEBUG_EVENTS;
                }
                if (text.length > 0) {
                    this.fStringBuffer.append(text.ch, text.offset, text.length);
                }
            }
        }
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        if (this.fIncludeIgnorableWhitespace && !this.fFilterReject) {
            if (this.fDeferNodeExpansion) {
                this.fDeferredDocumentImpl.appendChild(this.fCurrentNodeIndex, this.fDeferredDocumentImpl.createDeferredTextNode(text.toString(), true));
                return;
            }
            Node child = this.fCurrentNode.getLastChild();
            if (child == null || child.getNodeType() != (short) 3) {
                Text textNode = this.fDocument.createTextNode(text.toString());
                if (this.fDocumentImpl != null) {
                    ((TextImpl) textNode).setIgnorableWhitespace(true);
                }
                this.fCurrentNode.appendChild(textNode);
                return;
            }
            ((Text) child).appendData(text.toString());
        }
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        ElementPSVI elementPSVI;
        XSTypeDefinition type;
        if (this.fDeferNodeExpansion) {
            if (augs != null) {
                elementPSVI = (ElementPSVI) augs.getItem(Constants.ELEMENT_PSVI);
                if (elementPSVI != null) {
                    type = elementPSVI.getMemberTypeDefinition();
                    if (type == null) {
                        type = elementPSVI.getTypeDefinition();
                    }
                    this.fDeferredDocumentImpl.setTypeInfo(this.fCurrentNodeIndex, type);
                }
            }
            this.fCurrentNodeIndex = this.fDeferredDocumentImpl.getParentNode(this.fCurrentNodeIndex, DEBUG_EVENTS);
            return;
        }
        if (!(augs == null || this.fDocumentImpl == null || (!this.fNamespaceAware && !this.fStorePSVI))) {
            elementPSVI = (ElementPSVI) augs.getItem(Constants.ELEMENT_PSVI);
            if (elementPSVI != null) {
                if (this.fNamespaceAware) {
                    type = elementPSVI.getMemberTypeDefinition();
                    if (type == null) {
                        type = elementPSVI.getTypeDefinition();
                    }
                    ((ElementNSImpl) this.fCurrentNode).setType(type);
                }
                if (this.fStorePSVI) {
                    ((PSVIElementNSImpl) this.fCurrentNode).setPSVI(elementPSVI);
                }
            }
        }
        if (this.fDOMFilter == null) {
            setCharacterData(DEBUG_EVENTS);
            this.fCurrentNode = this.fCurrentNode.getParentNode();
        } else if (this.fFilterReject) {
            int i = this.fRejectedElementDepth;
            this.fRejectedElementDepth = i - 1;
            if (i == 0) {
                this.fFilterReject = DEBUG_EVENTS;
            }
        } else if (this.fSkippedElemStack.isEmpty() || this.fSkippedElemStack.pop() != Boolean.TRUE) {
            setCharacterData(DEBUG_EVENTS);
            if (!(this.fCurrentNode == this.fRoot || this.fInEntityRef || (this.fDOMFilter.getWhatToShow() & 1) == 0)) {
                Node parent;
                switch (this.fDOMFilter.acceptNode(this.fCurrentNode)) {
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                        parent = this.fCurrentNode.getParentNode();
                        parent.removeChild(this.fCurrentNode);
                        this.fCurrentNode = parent;
                        return;
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        this.fFirstChunk = true;
                        parent = this.fCurrentNode.getParentNode();
                        NodeList ls = this.fCurrentNode.getChildNodes();
                        int length = ls.getLength();
                        for (int i2 = 0; i2 < length; i2++) {
                            parent.appendChild(ls.item(0));
                        }
                        parent.removeChild(this.fCurrentNode);
                        this.fCurrentNode = parent;
                        return;
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        throw Abort.INSTANCE;
                }
            }
            this.fCurrentNode = this.fCurrentNode.getParentNode();
        }
    }

    public void startCDATA(Augmentations augs) throws XNIException {
        this.fInCDATASection = true;
        if (!this.fDeferNodeExpansion && !this.fFilterReject && this.fCreateCDATANodes) {
            setCharacterData(DEBUG_EVENTS);
        }
    }

    public void endCDATA(Augmentations augs) throws XNIException {
        this.fInCDATASection = DEBUG_EVENTS;
        if (this.fDeferNodeExpansion) {
            if (this.fCurrentCDATASectionIndex != -1) {
                this.fCurrentNodeIndex = this.fDeferredDocumentImpl.getParentNode(this.fCurrentNodeIndex, DEBUG_EVENTS);
                this.fCurrentCDATASectionIndex = -1;
            }
        } else if (!this.fFilterReject && this.fCurrentCDATASection != null) {
            if (!(this.fDOMFilter == null || this.fInEntityRef || (this.fDOMFilter.getWhatToShow() & 8) == 0)) {
                switch (this.fDOMFilter.acceptNode(this.fCurrentCDATASection)) {
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        Node parent = this.fCurrentNode.getParentNode();
                        parent.removeChild(this.fCurrentCDATASection);
                        this.fCurrentNode = parent;
                        return;
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        throw Abort.INSTANCE;
                }
            }
            this.fCurrentNode = this.fCurrentNode.getParentNode();
            this.fCurrentCDATASection = null;
        }
    }

    public void endDocument(Augmentations augs) throws XNIException {
        if (this.fDeferNodeExpansion) {
            if (this.fLocator != null) {
                this.fDeferredDocumentImpl.setInputEncoding(this.fLocator.getEncoding());
            }
            this.fCurrentNodeIndex = -1;
            return;
        }
        if (this.fDocumentImpl != null) {
            if (this.fLocator != null) {
                this.fDocumentImpl.setInputEncoding(this.fLocator.getEncoding());
            }
            this.fDocumentImpl.setStrictErrorChecking(true);
        }
        this.fCurrentNode = null;
    }

    public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
        if (this.fDeferNodeExpansion) {
            int prevIndex;
            int childIndex;
            int i = this.fDocumentTypeIndex;
            if (r0 != -1) {
                int node = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, DEBUG_EVENTS);
                while (node != -1) {
                    if (this.fDeferredDocumentImpl.getNodeType(node, DEBUG_EVENTS) == (short) 6) {
                        if (this.fDeferredDocumentImpl.getNodeName(node, DEBUG_EVENTS).equals(name)) {
                            this.fDeferredEntityDecl = node;
                            break;
                        }
                    }
                    node = this.fDeferredDocumentImpl.getRealPrevSibling(node, DEBUG_EVENTS);
                }
            }
            i = this.fDeferredEntityDecl;
            if (r0 != -1) {
                if (this.fDeferredDocumentImpl.getLastChild(this.fDeferredEntityDecl, DEBUG_EVENTS) == -1) {
                    prevIndex = -1;
                    childIndex = this.fDeferredDocumentImpl.getLastChild(this.fCurrentNodeIndex, DEBUG_EVENTS);
                    while (childIndex != -1) {
                        int cloneIndex = this.fDeferredDocumentImpl.cloneNode(childIndex, true);
                        this.fDeferredDocumentImpl.insertBefore(this.fDeferredEntityDecl, cloneIndex, prevIndex);
                        prevIndex = cloneIndex;
                        childIndex = this.fDeferredDocumentImpl.getRealPrevSibling(childIndex, DEBUG_EVENTS);
                    }
                }
            }
            if (this.fCreateEntityRefNodes) {
                this.fCurrentNodeIndex = this.fDeferredDocumentImpl.getParentNode(this.fCurrentNodeIndex, DEBUG_EVENTS);
            } else {
                int sibling;
                childIndex = this.fDeferredDocumentImpl.getLastChild(this.fCurrentNodeIndex, DEBUG_EVENTS);
                int parentIndex = this.fDeferredDocumentImpl.getParentNode(this.fCurrentNodeIndex, DEBUG_EVENTS);
                prevIndex = this.fCurrentNodeIndex;
                int lastChild = childIndex;
                while (childIndex != -1) {
                    handleBaseURI(childIndex);
                    sibling = this.fDeferredDocumentImpl.getRealPrevSibling(childIndex, DEBUG_EVENTS);
                    this.fDeferredDocumentImpl.insertBefore(parentIndex, childIndex, prevIndex);
                    prevIndex = childIndex;
                    childIndex = sibling;
                }
                if (lastChild != -1) {
                    this.fDeferredDocumentImpl.setAsLastChild(parentIndex, lastChild);
                } else {
                    sibling = this.fDeferredDocumentImpl.getRealPrevSibling(prevIndex, DEBUG_EVENTS);
                    this.fDeferredDocumentImpl.setAsLastChild(parentIndex, sibling);
                }
                this.fCurrentNodeIndex = parentIndex;
            }
            this.fDeferredEntityDecl = -1;
        } else if (!this.fFilterReject) {
            Node child;
            Node parent;
            setCharacterData(true);
            if (this.fDocumentType != null) {
                this.fCurrentEntityDecl = (EntityImpl) this.fDocumentType.getEntities().getNamedItem(name);
                if (this.fCurrentEntityDecl != null) {
                    if (this.fCurrentEntityDecl != null) {
                        if (this.fCurrentEntityDecl.getFirstChild() == null) {
                            this.fCurrentEntityDecl.setReadOnly(DEBUG_EVENTS, true);
                            for (child = this.fCurrentNode.getFirstChild(); child != null; child = child.getNextSibling()) {
                                Node copy = child.cloneNode(true);
                                this.fCurrentEntityDecl.appendChild(copy);
                            }
                            this.fCurrentEntityDecl.setReadOnly(true, true);
                        }
                    }
                    this.fCurrentEntityDecl = null;
                }
            }
            this.fInEntityRef = DEBUG_EVENTS;
            boolean removeEntityRef = DEBUG_EVENTS;
            if (this.fCreateEntityRefNodes) {
                if (this.fDocumentImpl != null) {
                    ((NodeImpl) this.fCurrentNode).setReadOnly(true, true);
                }
                if (this.fDOMFilter != null) {
                    if ((this.fDOMFilter.getWhatToShow() & 16) != 0) {
                        switch (this.fDOMFilter.acceptNode(this.fCurrentNode)) {
                            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                                parent = this.fCurrentNode.getParentNode();
                                parent.removeChild(this.fCurrentNode);
                                this.fCurrentNode = parent;
                                return;
                            case ConnectionResult.SERVICE_DISABLED /*3*/:
                                this.fFirstChunk = true;
                                removeEntityRef = true;
                                break;
                            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                                throw Abort.INSTANCE;
                            default:
                                this.fCurrentNode = this.fCurrentNode.getParentNode();
                                break;
                        }
                    }
                }
                this.fCurrentNode = this.fCurrentNode.getParentNode();
            }
            if (!this.fCreateEntityRefNodes || removeEntityRef) {
                NodeList children = this.fCurrentNode.getChildNodes();
                parent = this.fCurrentNode.getParentNode();
                int length = children.getLength();
                if (length > 0) {
                    Node node2 = this.fCurrentNode.getPreviousSibling();
                    child = children.item(0);
                    if (node2 != null && node2.getNodeType() == (short) 3 && child.getNodeType() == (short) 3) {
                        ((Text) node2).appendData(child.getNodeValue());
                        this.fCurrentNode.removeChild(child);
                    } else {
                        handleBaseURI(parent.insertBefore(child, this.fCurrentNode));
                    }
                    for (int i2 = 1; i2 < length; i2++) {
                        handleBaseURI(parent.insertBefore(children.item(0), this.fCurrentNode));
                    }
                }
                parent.removeChild(this.fCurrentNode);
                this.fCurrentNode = parent;
            }
        }
    }

    protected final void handleBaseURI(Node node) {
        if (this.fDocumentImpl != null) {
            short nodeType = node.getNodeType();
            String baseURI;
            if (nodeType == (short) 1) {
                if (this.fNamespaceAware) {
                    if (((Element) node).getAttributeNodeNS(XMLConstants.XML_NS_URI, "base") != null) {
                        return;
                    }
                } else if (((Element) node).getAttributeNode("xml:base") != null) {
                    return;
                }
                baseURI = ((EntityReferenceImpl) this.fCurrentNode).getBaseURI();
                if (baseURI != null && !baseURI.equals(this.fDocumentImpl.getDocumentURI())) {
                    if (this.fNamespaceAware) {
                        ((Element) node).setAttributeNS(XMLConstants.XML_NS_URI, "xml:base", baseURI);
                    } else {
                        ((Element) node).setAttribute("xml:base", baseURI);
                    }
                }
            } else if (nodeType == (short) 7) {
                baseURI = ((EntityReferenceImpl) this.fCurrentNode).getBaseURI();
                if (baseURI != null && this.fErrorHandler != null) {
                    DOMErrorImpl error = new DOMErrorImpl();
                    error.fType = "pi-base-uri-not-preserved";
                    error.fRelatedData = baseURI;
                    error.fSeverity = (short) 1;
                    this.fErrorHandler.getErrorHandler().handleError(error);
                }
            }
        }
    }

    protected final void handleBaseURI(int node) {
        short nodeType = this.fDeferredDocumentImpl.getNodeType(node, DEBUG_EVENTS);
        String baseURI;
        if (nodeType == (short) 1) {
            baseURI = this.fDeferredDocumentImpl.getNodeValueString(this.fCurrentNodeIndex, DEBUG_EVENTS);
            if (baseURI == null) {
                baseURI = this.fDeferredDocumentImpl.getDeferredEntityBaseURI(this.fDeferredEntityDecl);
            }
            if (baseURI != null && !baseURI.equals(this.fDeferredDocumentImpl.getDocumentURI())) {
                this.fDeferredDocumentImpl.setDeferredAttribute(node, "xml:base", XMLConstants.XML_NS_URI, baseURI, true);
            }
        } else if (nodeType == (short) 7) {
            baseURI = this.fDeferredDocumentImpl.getNodeValueString(this.fCurrentNodeIndex, DEBUG_EVENTS);
            if (baseURI == null) {
                baseURI = this.fDeferredDocumentImpl.getDeferredEntityBaseURI(this.fDeferredEntityDecl);
            }
            if (baseURI != null && this.fErrorHandler != null) {
                DOMErrorImpl error = new DOMErrorImpl();
                error.fType = "pi-base-uri-not-preserved";
                error.fRelatedData = baseURI;
                error.fSeverity = (short) 1;
                this.fErrorHandler.getErrorHandler().handleError(error);
            }
        }
    }

    public void startDTD(XMLLocator locator, Augmentations augs) throws XNIException {
        this.fInDTD = true;
        if (locator != null) {
            this.fBaseURIStack.push(locator.getBaseSystemId());
        }
        if (this.fDeferNodeExpansion || this.fDocumentImpl != null) {
            this.fInternalSubset = new StringBuffer(NodeFilter.SHOW_DOCUMENT_FRAGMENT);
        }
    }

    public void endDTD(Augmentations augs) throws XNIException {
        this.fInDTD = DEBUG_EVENTS;
        if (!this.fBaseURIStack.isEmpty()) {
            this.fBaseURIStack.pop();
        }
        String internalSubset = (this.fInternalSubset == null || this.fInternalSubset.length() <= 0) ? null : this.fInternalSubset.toString();
        if (this.fDeferNodeExpansion) {
            if (internalSubset != null) {
                this.fDeferredDocumentImpl.setInternalSubset(this.fDocumentTypeIndex, internalSubset);
            }
        } else if (this.fDocumentImpl != null && internalSubset != null) {
            ((DocumentTypeImpl) this.fDocumentType).setInternalSubset(internalSubset);
        }
    }

    public void startConditional(short type, Augmentations augs) throws XNIException {
    }

    public void endConditional(Augmentations augs) throws XNIException {
    }

    public void startExternalSubset(XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
        this.fBaseURIStack.push(identifier.getBaseSystemId());
        this.fInDTDExternalSubset = true;
    }

    public void endExternalSubset(Augmentations augs) throws XNIException {
        this.fInDTDExternalSubset = DEBUG_EVENTS;
        this.fBaseURIStack.pop();
    }

    public void internalEntityDecl(String name, XMLString text, XMLString nonNormalizedText, Augmentations augs) throws XNIException {
        if (!(this.fInternalSubset == null || this.fInDTDExternalSubset)) {
            this.fInternalSubset.append("<!ENTITY ");
            if (name.startsWith("%")) {
                this.fInternalSubset.append("% ");
                this.fInternalSubset.append(name.substring(1));
            } else {
                this.fInternalSubset.append(name);
            }
            this.fInternalSubset.append(' ');
            String value = nonNormalizedText.toString();
            boolean singleQuote = value.indexOf(39) == -1 ? true : DEBUG_EVENTS;
            this.fInternalSubset.append(singleQuote ? '\'' : '\"');
            this.fInternalSubset.append(value);
            this.fInternalSubset.append(singleQuote ? '\'' : '\"');
            this.fInternalSubset.append(">\n");
        }
        if (!name.startsWith("%")) {
            if (this.fDocumentType != null) {
                NamedNodeMap entities = this.fDocumentType.getEntities();
                if (((EntityImpl) entities.getNamedItem(name)) == null) {
                    EntityImpl entity = (EntityImpl) this.fDocumentImpl.createEntity(name);
                    entity.setBaseURI((String) this.fBaseURIStack.peek());
                    entities.setNamedItem(entity);
                }
            }
            if (this.fDocumentTypeIndex != -1) {
                boolean found = DEBUG_EVENTS;
                int node = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, DEBUG_EVENTS);
                while (node != -1) {
                    if (this.fDeferredDocumentImpl.getNodeType(node, DEBUG_EVENTS) == (short) 6 && this.fDeferredDocumentImpl.getNodeName(node, DEBUG_EVENTS).equals(name)) {
                        found = true;
                        break;
                    }
                    node = this.fDeferredDocumentImpl.getRealPrevSibling(node, DEBUG_EVENTS);
                }
                if (!found) {
                    this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createDeferredEntity(name, null, null, null, (String) this.fBaseURIStack.peek()));
                }
            }
        }
    }

    public void externalEntityDecl(String name, XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
        String publicId = identifier.getPublicId();
        String literalSystemId = identifier.getLiteralSystemId();
        if (!(this.fInternalSubset == null || this.fInDTDExternalSubset)) {
            this.fInternalSubset.append("<!ENTITY ");
            if (name.startsWith("%")) {
                this.fInternalSubset.append("% ");
                this.fInternalSubset.append(name.substring(1));
            } else {
                this.fInternalSubset.append(name);
            }
            this.fInternalSubset.append(' ');
            if (publicId != null) {
                this.fInternalSubset.append("PUBLIC '");
                this.fInternalSubset.append(publicId);
                this.fInternalSubset.append("' '");
            } else {
                this.fInternalSubset.append("SYSTEM '");
            }
            this.fInternalSubset.append(literalSystemId);
            this.fInternalSubset.append("'>\n");
        }
        if (!name.startsWith("%")) {
            if (this.fDocumentType != null) {
                NamedNodeMap entities = this.fDocumentType.getEntities();
                if (((EntityImpl) entities.getNamedItem(name)) == null) {
                    EntityImpl entity = (EntityImpl) this.fDocumentImpl.createEntity(name);
                    entity.setPublicId(publicId);
                    entity.setSystemId(literalSystemId);
                    entity.setBaseURI(identifier.getBaseSystemId());
                    entities.setNamedItem(entity);
                }
            }
            if (this.fDocumentTypeIndex != -1) {
                boolean found = DEBUG_EVENTS;
                int nodeIndex = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, DEBUG_EVENTS);
                while (nodeIndex != -1) {
                    if (this.fDeferredDocumentImpl.getNodeType(nodeIndex, DEBUG_EVENTS) == (short) 6 && this.fDeferredDocumentImpl.getNodeName(nodeIndex, DEBUG_EVENTS).equals(name)) {
                        found = true;
                        break;
                    }
                    nodeIndex = this.fDeferredDocumentImpl.getRealPrevSibling(nodeIndex, DEBUG_EVENTS);
                }
                if (!found) {
                    this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createDeferredEntity(name, publicId, literalSystemId, null, identifier.getBaseSystemId()));
                }
            }
        }
    }

    public void startParameterEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
        if (!(augs == null || this.fInternalSubset == null || this.fInDTDExternalSubset || !Boolean.TRUE.equals(augs.getItem(Constants.ENTITY_SKIPPED)))) {
            this.fInternalSubset.append(name).append(";\n");
        }
        this.fBaseURIStack.push(identifier.getExpandedSystemId());
    }

    public void endParameterEntity(String name, Augmentations augs) throws XNIException {
        this.fBaseURIStack.pop();
    }

    public void unparsedEntityDecl(String name, XMLResourceIdentifier identifier, String notation, Augmentations augs) throws XNIException {
        String publicId = identifier.getPublicId();
        String literalSystemId = identifier.getLiteralSystemId();
        if (!(this.fInternalSubset == null || this.fInDTDExternalSubset)) {
            this.fInternalSubset.append("<!ENTITY ");
            this.fInternalSubset.append(name);
            this.fInternalSubset.append(' ');
            if (publicId != null) {
                this.fInternalSubset.append("PUBLIC '");
                this.fInternalSubset.append(publicId);
                if (literalSystemId != null) {
                    this.fInternalSubset.append("' '");
                    this.fInternalSubset.append(literalSystemId);
                }
            } else {
                this.fInternalSubset.append("SYSTEM '");
                this.fInternalSubset.append(literalSystemId);
            }
            this.fInternalSubset.append("' NDATA ");
            this.fInternalSubset.append(notation);
            this.fInternalSubset.append(">\n");
        }
        if (this.fDocumentType != null) {
            NamedNodeMap entities = this.fDocumentType.getEntities();
            if (((EntityImpl) entities.getNamedItem(name)) == null) {
                EntityImpl entity = (EntityImpl) this.fDocumentImpl.createEntity(name);
                entity.setPublicId(publicId);
                entity.setSystemId(literalSystemId);
                entity.setNotationName(notation);
                entity.setBaseURI(identifier.getBaseSystemId());
                entities.setNamedItem(entity);
            }
        }
        if (this.fDocumentTypeIndex != -1) {
            boolean found = DEBUG_EVENTS;
            int nodeIndex = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, DEBUG_EVENTS);
            while (nodeIndex != -1) {
                if (this.fDeferredDocumentImpl.getNodeType(nodeIndex, DEBUG_EVENTS) == (short) 6 && this.fDeferredDocumentImpl.getNodeName(nodeIndex, DEBUG_EVENTS).equals(name)) {
                    found = true;
                    break;
                }
                nodeIndex = this.fDeferredDocumentImpl.getRealPrevSibling(nodeIndex, DEBUG_EVENTS);
            }
            if (!found) {
                this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createDeferredEntity(name, publicId, literalSystemId, notation, identifier.getBaseSystemId()));
            }
        }
    }

    public void notationDecl(String name, XMLResourceIdentifier identifier, Augmentations augs) throws XNIException {
        String publicId = identifier.getPublicId();
        String literalSystemId = identifier.getLiteralSystemId();
        if (!(this.fInternalSubset == null || this.fInDTDExternalSubset)) {
            this.fInternalSubset.append("<!NOTATION ");
            this.fInternalSubset.append(name);
            if (publicId != null) {
                this.fInternalSubset.append(" PUBLIC '");
                this.fInternalSubset.append(publicId);
                if (literalSystemId != null) {
                    this.fInternalSubset.append("' '");
                    this.fInternalSubset.append(literalSystemId);
                }
            } else {
                this.fInternalSubset.append(" SYSTEM '");
                this.fInternalSubset.append(literalSystemId);
            }
            this.fInternalSubset.append("'>\n");
        }
        if (!(this.fDocumentImpl == null || this.fDocumentType == null)) {
            NamedNodeMap notations = this.fDocumentType.getNotations();
            if (notations.getNamedItem(name) == null) {
                NotationImpl notation = (NotationImpl) this.fDocumentImpl.createNotation(name);
                notation.setPublicId(publicId);
                notation.setSystemId(literalSystemId);
                notation.setBaseURI(identifier.getBaseSystemId());
                notations.setNamedItem(notation);
            }
        }
        if (this.fDocumentTypeIndex != -1) {
            boolean found = DEBUG_EVENTS;
            int nodeIndex = this.fDeferredDocumentImpl.getLastChild(this.fDocumentTypeIndex, DEBUG_EVENTS);
            while (nodeIndex != -1) {
                if (this.fDeferredDocumentImpl.getNodeType(nodeIndex, DEBUG_EVENTS) == (short) 12 && this.fDeferredDocumentImpl.getNodeName(nodeIndex, DEBUG_EVENTS).equals(name)) {
                    found = true;
                    break;
                }
                nodeIndex = this.fDeferredDocumentImpl.getPrevSibling(nodeIndex, DEBUG_EVENTS);
            }
            if (!found) {
                this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, this.fDeferredDocumentImpl.createDeferredNotation(name, publicId, literalSystemId, identifier.getBaseSystemId()));
            }
        }
    }

    public void ignoredCharacters(XMLString text, Augmentations augs) throws XNIException {
    }

    public void elementDecl(String name, String contentModel, Augmentations augs) throws XNIException {
        if (this.fInternalSubset != null && !this.fInDTDExternalSubset) {
            this.fInternalSubset.append("<!ELEMENT ");
            this.fInternalSubset.append(name);
            this.fInternalSubset.append(' ');
            this.fInternalSubset.append(contentModel);
            this.fInternalSubset.append(">\n");
        }
    }

    public void attributeDecl(String elementName, String attributeName, String type, String[] enumeration, String defaultType, XMLString defaultValue, XMLString nonNormalizedDefaultValue, Augmentations augs) throws XNIException {
        if (!(this.fInternalSubset == null || this.fInDTDExternalSubset)) {
            int i;
            this.fInternalSubset.append("<!ATTLIST ");
            this.fInternalSubset.append(elementName);
            this.fInternalSubset.append(' ');
            this.fInternalSubset.append(attributeName);
            this.fInternalSubset.append(' ');
            if (type.equals("ENUMERATION")) {
                this.fInternalSubset.append('(');
                for (i = 0; i < enumeration.length; i++) {
                    if (i > 0) {
                        this.fInternalSubset.append('|');
                    }
                    this.fInternalSubset.append(enumeration[i]);
                }
                this.fInternalSubset.append(')');
            } else {
                this.fInternalSubset.append(type);
            }
            if (defaultType != null) {
                this.fInternalSubset.append(' ');
                this.fInternalSubset.append(defaultType);
            }
            if (defaultValue != null) {
                this.fInternalSubset.append(" '");
                for (i = 0; i < defaultValue.length; i++) {
                    char c = defaultValue.ch[defaultValue.offset + i];
                    if (c == '\'') {
                        this.fInternalSubset.append("&apos;");
                    } else {
                        this.fInternalSubset.append(c);
                    }
                }
                this.fInternalSubset.append('\'');
            }
            this.fInternalSubset.append(">\n");
        }
        String namespaceURI;
        if (this.fDeferredDocumentImpl != null) {
            if (defaultValue != null) {
                int elementDefIndex = this.fDeferredDocumentImpl.lookupElementDefinition(elementName);
                if (elementDefIndex == -1) {
                    elementDefIndex = this.fDeferredDocumentImpl.createDeferredElementDefinition(elementName);
                    this.fDeferredDocumentImpl.appendChild(this.fDocumentTypeIndex, elementDefIndex);
                }
                namespaceURI = null;
                if (this.fNamespaceAware) {
                    if (attributeName.startsWith("xmlns:") || attributeName.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
                        namespaceURI = NamespaceContext.XMLNS_URI;
                    } else if (attributeName.startsWith("xml:")) {
                        namespaceURI = NamespaceContext.XML_URI;
                    }
                }
                int attrIndex = this.fDeferredDocumentImpl.createDeferredAttribute(attributeName, namespaceURI, defaultValue.toString(), DEBUG_EVENTS);
                if (SchemaSymbols.ATTVAL_ID.equals(type)) {
                    this.fDeferredDocumentImpl.setIdAttribute(attrIndex);
                }
                this.fDeferredDocumentImpl.appendChild(elementDefIndex, attrIndex);
            }
        } else if (this.fDocumentImpl != null && defaultValue != null) {
            AttrImpl attr;
            ElementDefinitionImpl elementDef = (ElementDefinitionImpl) ((DocumentTypeImpl) this.fDocumentType).getElements().getNamedItem(elementName);
            if (elementDef == null) {
                elementDef = this.fDocumentImpl.createElementDefinition(elementName);
                ((DocumentTypeImpl) this.fDocumentType).getElements().setNamedItem(elementDef);
            }
            boolean nsEnabled = this.fNamespaceAware;
            if (nsEnabled) {
                namespaceURI = null;
                if (attributeName.startsWith("xmlns:") || attributeName.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
                    namespaceURI = NamespaceContext.XMLNS_URI;
                } else if (attributeName.startsWith("xml:")) {
                    namespaceURI = NamespaceContext.XML_URI;
                }
                attr = (AttrImpl) this.fDocumentImpl.createAttributeNS(namespaceURI, attributeName);
            } else {
                attr = (AttrImpl) this.fDocumentImpl.createAttribute(attributeName);
            }
            attr.setValue(defaultValue.toString());
            attr.setSpecified(DEBUG_EVENTS);
            attr.setIdAttribute(SchemaSymbols.ATTVAL_ID.equals(type));
            if (nsEnabled) {
                elementDef.getAttributes().setNamedItemNS(attr);
            } else {
                elementDef.getAttributes().setNamedItem(attr);
            }
        }
    }

    public void startAttlist(String elementName, Augmentations augs) throws XNIException {
    }

    public void endAttlist(Augmentations augs) throws XNIException {
    }

    protected Element createElementNode(QName element) {
        if (!this.fNamespaceAware) {
            return this.fDocument.createElement(element.rawname);
        }
        if (this.fDocumentImpl != null) {
            return this.fDocumentImpl.createElementNS(element.uri, element.rawname, element.localpart);
        }
        return this.fDocument.createElementNS(element.uri, element.rawname);
    }

    protected Attr createAttrNode(QName attrQName) {
        if (!this.fNamespaceAware) {
            return this.fDocument.createAttribute(attrQName.rawname);
        }
        if (this.fDocumentImpl != null) {
            return this.fDocumentImpl.createAttributeNS(attrQName.uri, attrQName.rawname, attrQName.localpart);
        }
        return this.fDocument.createAttributeNS(attrQName.uri, attrQName.rawname);
    }

    protected void setCharacterData(boolean sawChars) {
        this.fFirstChunk = sawChars;
        Node child = this.fCurrentNode.getLastChild();
        if (child != null) {
            if (this.fStringBuffer.length() > 0) {
                if (child.getNodeType() == (short) 3) {
                    if (this.fDocumentImpl != null) {
                        ((TextImpl) child).replaceData(this.fStringBuffer.toString());
                    } else {
                        ((Text) child).setData(this.fStringBuffer.toString());
                    }
                }
                this.fStringBuffer.setLength(0);
            }
            if (this.fDOMFilter != null && !this.fInEntityRef && child.getNodeType() == (short) 3 && (this.fDOMFilter.getWhatToShow() & 4) != 0) {
                switch (this.fDOMFilter.acceptNode(child)) {
                    case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    case ConnectionResult.SERVICE_DISABLED /*3*/:
                        this.fCurrentNode.removeChild(child);
                    case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                        throw Abort.INSTANCE;
                    default:
                }
            }
        }
    }

    public void abort() {
        throw Abort.INSTANCE;
    }
}
