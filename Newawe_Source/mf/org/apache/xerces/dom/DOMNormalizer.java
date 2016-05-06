package mf.org.apache.xerces.dom;

import android.support.v4.media.TransportMediator;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.impl.RevalidationHandler;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.dtd.XMLDTDLoader;
import mf.org.apache.xerces.impl.dtd.XMLDTDValidator;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XMLSchemaValidator;
import mf.org.apache.xerces.impl.xs.util.SimpleLocator;
import mf.org.apache.xerces.jaxp.JAXPConstants;
import mf.org.apache.xerces.util.AugmentationsImpl;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XML11Char;
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
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLDocumentSource;
import mf.org.apache.xerces.xs.AttributePSVI;
import mf.org.apache.xerces.xs.ElementPSVI;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.Comment;
import mf.org.w3c.dom.DOMErrorHandler;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Entity;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.ProcessingInstruction;
import mf.org.w3c.dom.Text;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

public class DOMNormalizer implements XMLDocumentHandler {
    protected static final boolean DEBUG = false;
    protected static final boolean DEBUG_EVENTS = false;
    protected static final boolean DEBUG_ND = false;
    public static final XMLString EMPTY_STRING;
    protected static final String PREFIX = "NS";
    public static final RuntimeException abort;
    private boolean fAllWhitespace;
    protected final XMLAttributesProxy fAttrProxy;
    private final QName fAttrQName;
    protected final ArrayList fAttributeList;
    protected DOMConfigurationImpl fConfiguration;
    protected Node fCurrentNode;
    protected CoreDocumentImpl fDocument;
    private final DOMErrorImpl fError;
    protected DOMErrorHandler fErrorHandler;
    protected final NamespaceContext fLocalNSBinder;
    protected final DOMLocatorImpl fLocator;
    protected final NamespaceContext fNamespaceContext;
    protected boolean fNamespaceValidation;
    final XMLString fNormalizedValue;
    protected boolean fPSVI;
    protected final QName fQName;
    protected SymbolTable fSymbolTable;
    protected RevalidationHandler fValidationHandler;

    protected final class XMLAttributesProxy implements XMLAttributes {
        protected AttributeMap fAttributes;
        protected final Vector fAugmentations;
        protected final Vector fDTDTypes;
        protected CoreDocumentImpl fDocument;
        protected ElementImpl fElement;

        protected XMLAttributesProxy() {
            this.fDTDTypes = new Vector(5);
            this.fAugmentations = new Vector(5);
        }

        public void setAttributes(AttributeMap attributes, CoreDocumentImpl doc, ElementImpl elem) {
            this.fDocument = doc;
            this.fAttributes = attributes;
            this.fElement = elem;
            if (attributes != null) {
                int length = attributes.getLength();
                this.fDTDTypes.setSize(length);
                this.fAugmentations.setSize(length);
                for (int i = 0; i < length; i++) {
                    this.fAugmentations.setElementAt(new AugmentationsImpl(), i);
                }
                return;
            }
            this.fDTDTypes.setSize(0);
            this.fAugmentations.setSize(0);
        }

        public int addAttribute(QName qname, String attrType, String attrValue) {
            int index = this.fElement.getXercesAttribute(qname.uri, qname.localpart);
            if (index >= 0) {
                return index;
            }
            AttrImpl attr = (AttrImpl) ((CoreDocumentImpl) this.fElement.getOwnerDocument()).createAttributeNS(qname.uri, qname.rawname, qname.localpart);
            attr.setNodeValue(attrValue);
            index = this.fElement.setXercesAttributeNode(attr);
            this.fDTDTypes.insertElementAt(attrType, index);
            this.fAugmentations.insertElementAt(new AugmentationsImpl(), index);
            attr.setSpecified(DOMNormalizer.DEBUG_ND);
            return index;
        }

        public void removeAllAttributes() {
        }

        public void removeAttributeAt(int attrIndex) {
        }

        public int getLength() {
            return this.fAttributes != null ? this.fAttributes.getLength() : 0;
        }

        public int getIndex(String qName) {
            return -1;
        }

        public int getIndex(String uri, String localPart) {
            return -1;
        }

        public void setName(int attrIndex, QName attrName) {
        }

        public void getName(int attrIndex, QName attrName) {
            if (this.fAttributes != null) {
                DOMNormalizer.this.updateQName((Node) this.fAttributes.getItem(attrIndex), attrName);
            }
        }

        public String getPrefix(int index) {
            if (this.fAttributes == null) {
                return null;
            }
            String prefix = ((Node) this.fAttributes.getItem(index)).getPrefix();
            if (prefix == null || prefix.length() == 0) {
                return null;
            }
            return DOMNormalizer.this.fSymbolTable.addSymbol(prefix);
        }

        public String getURI(int index) {
            if (this.fAttributes == null) {
                return null;
            }
            String namespace = ((Node) this.fAttributes.getItem(index)).getNamespaceURI();
            if (namespace != null) {
                return DOMNormalizer.this.fSymbolTable.addSymbol(namespace);
            }
            return null;
        }

        public String getLocalName(int index) {
            if (this.fAttributes == null) {
                return null;
            }
            String localName = ((Node) this.fAttributes.getItem(index)).getLocalName();
            if (localName != null) {
                return DOMNormalizer.this.fSymbolTable.addSymbol(localName);
            }
            return null;
        }

        public String getQName(int index) {
            if (this.fAttributes == null) {
                return null;
            }
            return DOMNormalizer.this.fSymbolTable.addSymbol(((Node) this.fAttributes.getItem(index)).getNodeName());
        }

        public void setType(int attrIndex, String attrType) {
            this.fDTDTypes.setElementAt(attrType, attrIndex);
        }

        public String getType(int index) {
            String type = (String) this.fDTDTypes.elementAt(index);
            return type != null ? getReportableType(type) : "CDATA";
        }

        public String getType(String qName) {
            return "CDATA";
        }

        public String getType(String uri, String localName) {
            return "CDATA";
        }

        private String getReportableType(String type) {
            if (type.charAt(0) == '(') {
                return SchemaSymbols.ATTVAL_NMTOKEN;
            }
            return type;
        }

        public void setValue(int attrIndex, String attrValue) {
            if (this.fAttributes != null) {
                AttrImpl attr = (AttrImpl) this.fAttributes.getItem(attrIndex);
                boolean specified = attr.getSpecified();
                attr.setValue(attrValue);
                attr.setSpecified(specified);
            }
        }

        public String getValue(int index) {
            return this.fAttributes != null ? this.fAttributes.item(index).getNodeValue() : StringUtils.EMPTY;
        }

        public String getValue(String qName) {
            return null;
        }

        public String getValue(String uri, String localName) {
            if (this.fAttributes == null) {
                return null;
            }
            Node node = this.fAttributes.getNamedItemNS(uri, localName);
            if (node != null) {
                return node.getNodeValue();
            }
            return null;
        }

        public void setNonNormalizedValue(int attrIndex, String attrValue) {
        }

        public String getNonNormalizedValue(int attrIndex) {
            return null;
        }

        public void setSpecified(int attrIndex, boolean specified) {
            ((AttrImpl) this.fAttributes.getItem(attrIndex)).setSpecified(specified);
        }

        public boolean isSpecified(int attrIndex) {
            return ((Attr) this.fAttributes.getItem(attrIndex)).getSpecified();
        }

        public Augmentations getAugmentations(int attributeIndex) {
            return (Augmentations) this.fAugmentations.elementAt(attributeIndex);
        }

        public Augmentations getAugmentations(String uri, String localPart) {
            return null;
        }

        public Augmentations getAugmentations(String qName) {
            return null;
        }

        public void setAugmentations(int attrIndex, Augmentations augs) {
            this.fAugmentations.setElementAt(augs, attrIndex);
        }
    }

    static {
        abort = new RuntimeException();
        EMPTY_STRING = new XMLString();
    }

    public DOMNormalizer() {
        this.fConfiguration = null;
        this.fDocument = null;
        this.fAttrProxy = new XMLAttributesProxy();
        this.fQName = new QName();
        this.fError = new DOMErrorImpl();
        this.fNamespaceValidation = DEBUG_ND;
        this.fPSVI = DEBUG_ND;
        this.fNamespaceContext = new NamespaceSupport();
        this.fLocalNSBinder = new NamespaceSupport();
        this.fAttributeList = new ArrayList(5);
        this.fLocator = new DOMLocatorImpl();
        this.fCurrentNode = null;
        this.fAttrQName = new QName();
        this.fNormalizedValue = new XMLString(new char[16], 0, 0);
        this.fAllWhitespace = DEBUG_ND;
    }

    protected void normalizeDocument(CoreDocumentImpl document, DOMConfigurationImpl config) {
        this.fDocument = document;
        this.fConfiguration = config;
        this.fAllWhitespace = DEBUG_ND;
        this.fNamespaceValidation = DEBUG_ND;
        String xmlVersion = this.fDocument.getXmlVersion();
        String schemaType = null;
        String[] schemaLocations = null;
        this.fSymbolTable = (SymbolTable) this.fConfiguration.getProperty(XMLSchemaValidator.SYMBOL_TABLE);
        this.fNamespaceContext.reset();
        this.fNamespaceContext.declarePrefix(XMLSymbols.EMPTY_STRING, null);
        if ((this.fConfiguration.features & 64) != 0) {
            String schemaLang = (String) this.fConfiguration.getProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE);
            if (schemaLang == null || !schemaLang.equals(Constants.NS_XMLSCHEMA)) {
                schemaType = XMLGrammarDescription.XML_DTD;
                if (schemaLang != null) {
                    schemaLocations = (String[]) this.fConfiguration.getProperty(JAXPConstants.JAXP_SCHEMA_SOURCE);
                }
                this.fConfiguration.setDTDValidatorFactory(xmlVersion);
                this.fValidationHandler = CoreDOMImplementationImpl.singleton.getValidator(schemaType, xmlVersion);
                this.fPSVI = DEBUG_ND;
            } else {
                boolean z;
                schemaType = XMLGrammarDescription.XML_SCHEMA;
                this.fValidationHandler = CoreDOMImplementationImpl.singleton.getValidator(schemaType, xmlVersion);
                this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", true);
                this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
                this.fNamespaceValidation = true;
                if ((this.fConfiguration.features & TransportMediator.FLAG_KEY_MEDIA_NEXT) != 0) {
                    z = true;
                } else {
                    z = DEBUG_ND;
                }
                this.fPSVI = z;
            }
            this.fConfiguration.setFeature("http://xml.org/sax/features/validation", true);
            this.fDocument.clearIdentifiers();
            if (this.fValidationHandler != null) {
                ((XMLComponent) this.fValidationHandler).reset(this.fConfiguration);
            }
        } else {
            this.fValidationHandler = null;
        }
        this.fErrorHandler = (DOMErrorHandler) this.fConfiguration.getParameter(Constants.DOM_ERROR_HANDLER);
        if (this.fValidationHandler != null) {
            this.fValidationHandler.setDocumentHandler(this);
            this.fValidationHandler.startDocument(new SimpleLocator(this.fDocument.fDocumentURI, this.fDocument.fDocumentURI, -1, -1), this.fDocument.encoding, this.fNamespaceContext, null);
            this.fValidationHandler.xmlDecl(this.fDocument.getXmlVersion(), this.fDocument.getXmlEncoding(), this.fDocument.getXmlStandalone() ? "yes" : "no", null);
        }
        try {
            if (schemaType == XMLGrammarDescription.XML_DTD) {
                String str;
                if (schemaLocations != null) {
                    str = schemaLocations[0];
                } else {
                    str = null;
                }
                processDTD(xmlVersion, str);
            }
            Node kid = this.fDocument.getFirstChild();
            while (kid != null) {
                Node next = kid.getNextSibling();
                kid = normalizeNode(kid);
                if (kid != null) {
                    next = kid;
                }
                kid = next;
            }
            if (this.fValidationHandler != null) {
                this.fValidationHandler.endDocument(null);
                this.fValidationHandler.setDocumentHandler(null);
                CoreDOMImplementationImpl.singleton.releaseValidator(schemaType, xmlVersion, this.fValidationHandler);
                this.fValidationHandler = null;
            }
        } catch (RuntimeException e) {
            if (this.fValidationHandler != null) {
                this.fValidationHandler.setDocumentHandler(null);
                CoreDOMImplementationImpl.singleton.releaseValidator(schemaType, xmlVersion, this.fValidationHandler);
                this.fValidationHandler = null;
            }
            if (e != abort) {
                throw e;
            }
        }
    }

    protected Node normalizeNode(Node node) {
        int type = node.getNodeType();
        this.fLocator.fRelatedNode = node;
        boolean wellformed;
        Node next;
        Node parent;
        Node prevSibling;
        switch (type) {
            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                if (this.fDocument.errorChecking && (this.fConfiguration.features & NodeFilter.SHOW_DOCUMENT) != 0 && this.fDocument.isXMLVersionChanged()) {
                    if (this.fNamespaceValidation) {
                        wellformed = CoreDocumentImpl.isValidQName(node.getPrefix(), node.getLocalName(), this.fDocument.isXML11Version());
                    } else {
                        wellformed = CoreDocumentImpl.isXMLName(node.getNodeName(), this.fDocument.isXML11Version());
                    }
                    if (!wellformed) {
                        reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "wf-invalid-character-in-node-name", new Object[]{"Element", node.getNodeName()}), (short) 2, "wf-invalid-character-in-node-name");
                    }
                }
                this.fNamespaceContext.pushContext();
                this.fLocalNSBinder.reset();
                ElementImpl elem = (ElementImpl) node;
                if (elem.needsSyncChildren()) {
                    elem.synchronizeChildren();
                }
                AttributeMap attributes = elem.hasAttributes() ? (AttributeMap) elem.getAttributes() : null;
                int i;
                if ((this.fConfiguration.features & 1) != 0) {
                    namespaceFixUp(elem, attributes);
                    if ((this.fConfiguration.features & XMLEntityManager.DEFAULT_INTERNAL_BUFFER_SIZE) == 0 && attributes != null) {
                        i = 0;
                        while (i < attributes.getLength()) {
                            Attr att = (Attr) attributes.getItem(i);
                            if (XMLSymbols.PREFIX_XMLNS.equals(att.getPrefix()) || XMLSymbols.PREFIX_XMLNS.equals(att.getName())) {
                                elem.removeAttributeNode(att);
                                i--;
                            }
                            i++;
                        }
                    }
                } else if (attributes != null) {
                    for (i = 0; i < attributes.getLength(); i++) {
                        Attr attr = (Attr) attributes.item(i);
                        attr.normalize();
                        if (this.fDocument.errorChecking && (this.fConfiguration.features & NodeFilter.SHOW_DOCUMENT) != 0) {
                            isAttrValueWF(this.fErrorHandler, this.fError, this.fLocator, attributes, attr, attr.getValue(), this.fDocument.isXML11Version());
                            if (this.fDocument.isXMLVersionChanged()) {
                                if (this.fNamespaceValidation) {
                                    wellformed = CoreDocumentImpl.isValidQName(node.getPrefix(), node.getLocalName(), this.fDocument.isXML11Version());
                                } else {
                                    wellformed = CoreDocumentImpl.isXMLName(node.getNodeName(), this.fDocument.isXML11Version());
                                }
                                if (!wellformed) {
                                    reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "wf-invalid-character-in-node-name", new Object[]{"Attr", node.getNodeName()}), (short) 2, "wf-invalid-character-in-node-name");
                                }
                            }
                        }
                    }
                }
                if (this.fValidationHandler != null) {
                    this.fAttrProxy.setAttributes(attributes, this.fDocument, elem);
                    updateQName(elem, this.fQName);
                    this.fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                    this.fCurrentNode = node;
                    this.fValidationHandler.startElement(this.fQName, this.fAttrProxy, null);
                }
                Node kid = elem.getFirstChild();
                while (kid != null) {
                    next = kid.getNextSibling();
                    kid = normalizeNode(kid);
                    if (kid != null) {
                        next = kid;
                    }
                    kid = next;
                }
                if (this.fValidationHandler != null) {
                    updateQName(elem, this.fQName);
                    this.fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                    this.fCurrentNode = node;
                    this.fValidationHandler.endElement(this.fQName, null);
                }
                this.fNamespaceContext.popContext();
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                next = node.getNextSibling();
                if (next == null || next.getNodeType() != (short) 3) {
                    if (node.getNodeValue().length() != 0) {
                        short nextType = next != null ? next.getNodeType() : (short) -1;
                        if (nextType == (short) -1 || !(((this.fConfiguration.features & 4) == 0 && nextType == (short) 6) || (((this.fConfiguration.features & 32) == 0 && nextType == (short) 8) || ((this.fConfiguration.features & 8) == 0 && nextType == (short) 4)))) {
                            if (this.fDocument.errorChecking && (this.fConfiguration.features & NodeFilter.SHOW_DOCUMENT) != 0) {
                                isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, node.getNodeValue(), this.fDocument.isXML11Version());
                            }
                            if (this.fValidationHandler != null) {
                                this.fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                                this.fCurrentNode = node;
                                this.fValidationHandler.characterData(node.getNodeValue(), null);
                                if (!this.fNamespaceValidation) {
                                    if (!this.fAllWhitespace) {
                                        ((TextImpl) node).setIgnorableWhitespace(DEBUG_ND);
                                        break;
                                    }
                                    this.fAllWhitespace = DEBUG_ND;
                                    ((TextImpl) node).setIgnorableWhitespace(true);
                                    break;
                                }
                            }
                        }
                    }
                    node.getParentNode().removeChild(node);
                    break;
                }
                ((Text) node).appendData(next.getNodeValue());
                node.getParentNode().removeChild(next);
                return node;
                break;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                if ((this.fConfiguration.features & 8) != 0) {
                    if (this.fValidationHandler != null) {
                        this.fConfiguration.fErrorHandlerWrapper.fCurrentNode = node;
                        this.fCurrentNode = node;
                        this.fValidationHandler.startCDATA(null);
                        this.fValidationHandler.characterData(node.getNodeValue(), null);
                        this.fValidationHandler.endCDATA(null);
                    }
                    String value = node.getNodeValue();
                    if ((this.fConfiguration.features & 16) == 0) {
                        if (this.fDocument.errorChecking) {
                            isCDataWF(this.fErrorHandler, this.fError, this.fLocator, value, this.fDocument.isXML11Version());
                            break;
                        }
                    }
                    parent = node.getParentNode();
                    if (this.fDocument.errorChecking) {
                        isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, node.getNodeValue(), this.fDocument.isXML11Version());
                    }
                    while (true) {
                        int index = value.indexOf("]]>");
                        if (index < 0) {
                            break;
                        }
                        node.setNodeValue(value.substring(0, index + 2));
                        value = value.substring(index + 2);
                        Node firstSplitNode = node;
                        Node newChild = this.fDocument.createCDATASection(value);
                        parent.insertBefore(newChild, node.getNextSibling());
                        node = newChild;
                        this.fLocator.fRelatedNode = firstSplitNode;
                        reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "cdata-sections-splitted", null), (short) 1, "cdata-sections-splitted");
                    }
                } else {
                    prevSibling = node.getPreviousSibling();
                    if (prevSibling == null || prevSibling.getNodeType() != (short) 3) {
                        Text text = this.fDocument.createTextNode(node.getNodeValue());
                        node = node.getParentNode().replaceChild(text, node);
                        return text;
                    }
                    ((Text) prevSibling).appendData(node.getNodeValue());
                    node.getParentNode().removeChild(node);
                    return prevSibling;
                }
                break;
            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                if ((this.fConfiguration.features & 4) != 0) {
                    if (this.fDocument.errorChecking && (this.fConfiguration.features & NodeFilter.SHOW_DOCUMENT) != 0 && this.fDocument.isXMLVersionChanged()) {
                        CoreDocumentImpl.isXMLName(node.getNodeName(), this.fDocument.isXML11Version());
                        break;
                    }
                }
                prevSibling = node.getPreviousSibling();
                parent = node.getParentNode();
                ((EntityReferenceImpl) node).setReadOnly(DEBUG_ND, true);
                expandEntityRef(parent, node);
                parent.removeChild(node);
                next = prevSibling != null ? prevSibling.getNextSibling() : parent.getFirstChild();
                if (prevSibling == null || next == null || prevSibling.getNodeType() != (short) 3 || next.getNodeType() != (short) 3) {
                    return next;
                }
                return prevSibling;
                break;
            case ConnectionResult.NETWORK_ERROR /*7*/:
                if (this.fDocument.errorChecking && (this.fConfiguration.features & NodeFilter.SHOW_DOCUMENT) != 0) {
                    ProcessingInstruction pinode = (ProcessingInstruction) node;
                    String target = pinode.getTarget();
                    if (this.fDocument.isXML11Version()) {
                        wellformed = XML11Char.isXML11ValidName(target);
                    } else {
                        wellformed = XMLChar.isValidName(target);
                    }
                    if (!wellformed) {
                        reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "wf-invalid-character-in-node-name", new Object[]{"Element", node.getNodeName()}), (short) 2, "wf-invalid-character-in-node-name");
                    }
                    isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, pinode.getData(), this.fDocument.isXML11Version());
                }
                if (this.fValidationHandler != null) {
                    this.fValidationHandler.processingInstruction(((ProcessingInstruction) node).getTarget(), EMPTY_STRING, null);
                    break;
                }
                break;
            case ConnectionResult.INTERNAL_ERROR /*8*/:
                if ((this.fConfiguration.features & 32) != 0) {
                    if (this.fDocument.errorChecking && (this.fConfiguration.features & NodeFilter.SHOW_DOCUMENT) != 0) {
                        isCommentWF(this.fErrorHandler, this.fError, this.fLocator, ((Comment) node).getData(), this.fDocument.isXML11Version());
                    }
                    if (this.fValidationHandler != null) {
                        this.fValidationHandler.comment(EMPTY_STRING, null);
                        break;
                    }
                }
                prevSibling = node.getPreviousSibling();
                parent = node.getParentNode();
                parent.removeChild(node);
                if (prevSibling != null && prevSibling.getNodeType() == (short) 3) {
                    Node nextSibling = prevSibling.getNextSibling();
                    if (nextSibling != null && nextSibling.getNodeType() == (short) 3) {
                        ((TextImpl) nextSibling).insertData(0, prevSibling.getNodeValue());
                        parent.removeChild(prevSibling);
                        return nextSibling;
                    }
                }
                break;
        }
        return null;
    }

    private void processDTD(String xmlVersion, String schemaLocation) {
        String rootName;
        String publicId = null;
        String systemId = schemaLocation;
        String baseSystemId = this.fDocument.getDocumentURI();
        String internalSubset = null;
        DocumentType docType = this.fDocument.getDoctype();
        if (docType != null) {
            rootName = docType.getName();
            publicId = docType.getPublicId();
            if (systemId == null || systemId.length() == 0) {
                systemId = docType.getSystemId();
            }
            internalSubset = docType.getInternalSubset();
        } else {
            Element elem = this.fDocument.getDocumentElement();
            if (elem != null) {
                rootName = elem.getNodeName();
                if (systemId == null) {
                    return;
                }
                if (systemId.length() == 0) {
                    return;
                }
            }
            return;
        }
        XMLDTDLoader loader = null;
        try {
            this.fValidationHandler.doctypeDecl(rootName, publicId, systemId, null);
            loader = CoreDOMImplementationImpl.singleton.getDTDLoader(xmlVersion);
            loader.setFeature("http://xml.org/sax/features/validation", true);
            loader.setEntityResolver(this.fConfiguration.getEntityResolver());
            loader.setErrorHandler(this.fConfiguration.getErrorHandler());
            loader.loadGrammarWithContext((XMLDTDValidator) this.fValidationHandler, rootName, publicId, systemId, baseSystemId, internalSubset);
            if (loader != null) {
                CoreDOMImplementationImpl.singleton.releaseDTDLoader(xmlVersion, loader);
            }
        } catch (IOException e) {
            if (loader != null) {
                CoreDOMImplementationImpl.singleton.releaseDTDLoader(xmlVersion, loader);
            }
        } catch (Throwable th) {
            if (loader != null) {
                CoreDOMImplementationImpl.singleton.releaseDTDLoader(xmlVersion, loader);
            }
        }
    }

    protected final void expandEntityRef(Node parent, Node reference) {
        Node kid = reference.getFirstChild();
        while (kid != null) {
            Node next = kid.getNextSibling();
            parent.insertBefore(kid, reference);
            kid = next;
        }
    }

    protected final void namespaceFixUp(ElementImpl element, AttributeMap attributes) {
        Attr attr;
        String uri;
        String value;
        String prefix;
        if (attributes != null) {
            for (int k = 0; k < attributes.getLength(); k++) {
                attr = (Attr) attributes.getItem(k);
                uri = attr.getNamespaceURI();
                if (uri != null) {
                    if (uri.equals(NamespaceContext.XMLNS_URI)) {
                        value = attr.getNodeValue();
                        if (value == null) {
                            value = XMLSymbols.EMPTY_STRING;
                        }
                        if (this.fDocument.errorChecking && value.equals(NamespaceContext.XMLNS_URI)) {
                            this.fLocator.fRelatedNode = attr;
                            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(XMLMessageFormatter.XML_DOMAIN, "CantBindXMLNS", null), (short) 2, "CantBindXMLNS");
                        } else {
                            prefix = attr.getPrefix();
                            if (prefix == null || prefix.length() == 0) {
                                prefix = XMLSymbols.EMPTY_STRING;
                            } else {
                                prefix = this.fSymbolTable.addSymbol(prefix);
                            }
                            String localpart = this.fSymbolTable.addSymbol(attr.getLocalName());
                            if (prefix == XMLSymbols.PREFIX_XMLNS) {
                                value = this.fSymbolTable.addSymbol(value);
                                if (value.length() != 0) {
                                    this.fNamespaceContext.declarePrefix(localpart, value);
                                }
                            } else {
                                value = this.fSymbolTable.addSymbol(value);
                                NamespaceContext namespaceContext = this.fNamespaceContext;
                                String str = XMLSymbols.EMPTY_STRING;
                                if (value.length() == 0) {
                                    value = null;
                                }
                                namespaceContext.declarePrefix(str, value);
                            }
                        }
                    }
                }
            }
        }
        uri = element.getNamespaceURI();
        prefix = element.getPrefix();
        if (uri != null) {
            uri = this.fSymbolTable.addSymbol(uri);
            if (prefix == null || prefix.length() == 0) {
                prefix = XMLSymbols.EMPTY_STRING;
            } else {
                prefix = this.fSymbolTable.addSymbol(prefix);
            }
            if (this.fNamespaceContext.getURI(prefix) != uri) {
                addNamespaceDecl(prefix, uri, element);
                this.fLocalNSBinder.declarePrefix(prefix, uri);
                this.fNamespaceContext.declarePrefix(prefix, uri);
            }
        } else if (element.getLocalName() != null) {
            uri = this.fNamespaceContext.getURI(XMLSymbols.EMPTY_STRING);
            if (uri != null && uri.length() > 0) {
                addNamespaceDecl(XMLSymbols.EMPTY_STRING, XMLSymbols.EMPTY_STRING, element);
                this.fLocalNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, null);
                this.fNamespaceContext.declarePrefix(XMLSymbols.EMPTY_STRING, null);
            }
        } else if (this.fNamespaceValidation) {
            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalElementName", new Object[]{element.getNodeName()}), (short) 3, "NullLocalElementName");
        } else {
            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalElementName", new Object[]{element.getNodeName()}), (short) 2, "NullLocalElementName");
        }
        if (attributes != null) {
            attributes.cloneMap(this.fAttributeList);
            for (int i = 0; i < this.fAttributeList.size(); i++) {
                attr = (Attr) this.fAttributeList.get(i);
                this.fLocator.fRelatedNode = attr;
                attr.normalize();
                value = attr.getValue();
                uri = attr.getNamespaceURI();
                if (value == null) {
                    value = XMLSymbols.EMPTY_STRING;
                }
                if (this.fDocument.errorChecking && (this.fConfiguration.features & NodeFilter.SHOW_DOCUMENT) != 0) {
                    isAttrValueWF(this.fErrorHandler, this.fError, this.fLocator, attributes, attr, value, this.fDocument.isXML11Version());
                    if (this.fDocument.isXMLVersionChanged()) {
                        boolean wellformed;
                        if (this.fNamespaceValidation) {
                            wellformed = CoreDocumentImpl.isValidQName(attr.getPrefix(), attr.getLocalName(), this.fDocument.isXML11Version());
                        } else {
                            wellformed = CoreDocumentImpl.isXMLName(attr.getNodeName(), this.fDocument.isXML11Version());
                        }
                        if (!wellformed) {
                            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "wf-invalid-character-in-node-name", new Object[]{"Attr", attr.getNodeName()}), (short) 2, "wf-invalid-character-in-node-name");
                        }
                    }
                }
                if (uri != null) {
                    prefix = attr.getPrefix();
                    if (prefix == null || prefix.length() == 0) {
                        prefix = XMLSymbols.EMPTY_STRING;
                    } else {
                        prefix = this.fSymbolTable.addSymbol(prefix);
                    }
                    this.fSymbolTable.addSymbol(attr.getLocalName());
                    if (uri != null) {
                        if (uri.equals(NamespaceContext.XMLNS_URI)) {
                        }
                    }
                    ((AttrImpl) attr).setIdAttribute(DEBUG_ND);
                    uri = this.fSymbolTable.addSymbol(uri);
                    String declaredURI = this.fNamespaceContext.getURI(prefix);
                    if (prefix == XMLSymbols.EMPTY_STRING || declaredURI != uri) {
                        String declaredPrefix = this.fNamespaceContext.getPrefix(uri);
                        if (declaredPrefix == null || declaredPrefix == XMLSymbols.EMPTY_STRING) {
                            if (prefix == XMLSymbols.EMPTY_STRING || this.fLocalNSBinder.getURI(prefix) != null) {
                                int counter = 1 + 1;
                                prefix = this.fSymbolTable.addSymbol(new StringBuilder(PREFIX).append(1).toString());
                                int counter2 = counter;
                                while (this.fLocalNSBinder.getURI(prefix) != null) {
                                    counter = counter2 + 1;
                                    prefix = this.fSymbolTable.addSymbol(new StringBuilder(PREFIX).append(counter2).toString());
                                    counter2 = counter;
                                }
                            }
                            addNamespaceDecl(prefix, uri, element);
                            this.fLocalNSBinder.declarePrefix(prefix, this.fSymbolTable.addSymbol(value));
                            this.fNamespaceContext.declarePrefix(prefix, uri);
                        } else {
                            prefix = declaredPrefix;
                        }
                        attr.setPrefix(prefix);
                    }
                } else {
                    ((AttrImpl) attr).setIdAttribute(DEBUG_ND);
                    if (attr.getLocalName() == null) {
                        if (this.fNamespaceValidation) {
                            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalAttrName", new Object[]{attr.getNodeName()}), (short) 3, "NullLocalAttrName");
                        } else {
                            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalAttrName", new Object[]{attr.getNodeName()}), (short) 2, "NullLocalAttrName");
                        }
                    }
                }
            }
        }
    }

    protected final void addNamespaceDecl(String prefix, String uri, ElementImpl element) {
        if (prefix == XMLSymbols.EMPTY_STRING) {
            element.setAttributeNS(NamespaceContext.XMLNS_URI, XMLSymbols.PREFIX_XMLNS, uri);
        } else {
            element.setAttributeNS(NamespaceContext.XMLNS_URI, "xmlns:" + prefix, uri);
        }
    }

    public static final void isCDataWF(DOMErrorHandler errorHandler, DOMErrorImpl error, DOMLocatorImpl locator, String datavalue, boolean isXML11Version) {
        if (datavalue != null && datavalue.length() != 0) {
            char[] dataarray = datavalue.toCharArray();
            int datalength = dataarray.length;
            int i;
            int i2;
            char c;
            char c2;
            DOMErrorHandler dOMErrorHandler;
            DOMErrorImpl dOMErrorImpl;
            DOMLocatorImpl dOMLocatorImpl;
            int count;
            if (isXML11Version) {
                i = 0;
                while (i < datalength) {
                    i2 = i + 1;
                    c = dataarray[i];
                    if (XML11Char.isXML11Invalid(c)) {
                        if (XMLChar.isHighSurrogate(c) && i2 < datalength) {
                            i = i2 + 1;
                            c2 = dataarray[i2];
                            if (!XMLChar.isLowSurrogate(c2) || !XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                                i2 = i;
                            }
                        }
                        dOMErrorHandler = errorHandler;
                        dOMErrorImpl = error;
                        dOMLocatorImpl = locator;
                        reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInCDSect", new Object[]{Integer.toString(c, 16)}), (short) 2, "wf-invalid-character");
                        i = i2;
                    } else {
                        if (c == ']') {
                            count = i2;
                            if (count < datalength && dataarray[count] == ']') {
                                do {
                                    count++;
                                    if (count >= datalength) {
                                        break;
                                    }
                                } while (dataarray[count] == ']');
                                if (count < datalength && dataarray[count] == '>') {
                                    reportDOMError(errorHandler, error, locator, DOMMessageFormatter.formatMessage(XMLMessageFormatter.XML_DOMAIN, "CDEndInContent", null), (short) 2, "wf-invalid-character");
                                }
                            }
                        }
                        i = i2;
                    }
                }
                i2 = i;
                return;
            }
            i = 0;
            while (i < datalength) {
                i2 = i + 1;
                c = dataarray[i];
                if (XMLChar.isInvalid(c)) {
                    if (XMLChar.isHighSurrogate(c) && i2 < datalength) {
                        i = i2 + 1;
                        c2 = dataarray[i2];
                        if (!XMLChar.isLowSurrogate(c2) || !XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                            i2 = i;
                        }
                    }
                    dOMErrorHandler = errorHandler;
                    dOMErrorImpl = error;
                    dOMLocatorImpl = locator;
                    reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInCDSect", new Object[]{Integer.toString(c, 16)}), (short) 2, "wf-invalid-character");
                    i = i2;
                } else {
                    if (c == ']') {
                        count = i2;
                        if (count < datalength && dataarray[count] == ']') {
                            do {
                                count++;
                                if (count >= datalength) {
                                    break;
                                }
                            } while (dataarray[count] == ']');
                            if (count < datalength && dataarray[count] == '>') {
                                reportDOMError(errorHandler, error, locator, DOMMessageFormatter.formatMessage(XMLMessageFormatter.XML_DOMAIN, "CDEndInContent", null), (short) 2, "wf-invalid-character");
                            }
                        }
                    }
                    i = i2;
                }
            }
            i2 = i;
        }
    }

    public static final void isXMLCharWF(DOMErrorHandler errorHandler, DOMErrorImpl error, DOMLocatorImpl locator, String datavalue, boolean isXML11Version) {
        if (datavalue != null && datavalue.length() != 0) {
            char[] dataarray = datavalue.toCharArray();
            int datalength = dataarray.length;
            int i;
            int i2;
            char ch;
            char ch2;
            DOMErrorHandler dOMErrorHandler;
            DOMErrorImpl dOMErrorImpl;
            DOMLocatorImpl dOMLocatorImpl;
            if (isXML11Version) {
                i = 0;
                while (i < datalength) {
                    i2 = i + 1;
                    if (XML11Char.isXML11Invalid(dataarray[i])) {
                        ch = dataarray[i2 - 1];
                        if (XMLChar.isHighSurrogate(ch) && i2 < datalength) {
                            i = i2 + 1;
                            ch2 = dataarray[i2];
                            if (!XMLChar.isLowSurrogate(ch2) || !XMLChar.isSupplemental(XMLChar.supplemental(ch, ch2))) {
                                i2 = i;
                            }
                        }
                        dOMErrorHandler = errorHandler;
                        dOMErrorImpl = error;
                        dOMLocatorImpl = locator;
                        reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "InvalidXMLCharInDOM", new Object[]{Integer.toString(dataarray[i2 - 1], 16)}), (short) 2, "wf-invalid-character");
                    }
                    i = i2;
                }
                i2 = i;
                return;
            }
            i = 0;
            while (i < datalength) {
                i2 = i + 1;
                if (XMLChar.isInvalid(dataarray[i])) {
                    ch = dataarray[i2 - 1];
                    if (XMLChar.isHighSurrogate(ch) && i2 < datalength) {
                        i = i2 + 1;
                        ch2 = dataarray[i2];
                        if (!XMLChar.isLowSurrogate(ch2) || !XMLChar.isSupplemental(XMLChar.supplemental(ch, ch2))) {
                            i2 = i;
                        }
                    }
                    dOMErrorHandler = errorHandler;
                    dOMErrorImpl = error;
                    dOMLocatorImpl = locator;
                    reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "InvalidXMLCharInDOM", new Object[]{Integer.toString(dataarray[i2 - 1], 16)}), (short) 2, "wf-invalid-character");
                }
                i = i2;
            }
            i2 = i;
        }
    }

    public static final void isCommentWF(DOMErrorHandler errorHandler, DOMErrorImpl error, DOMLocatorImpl locator, String datavalue, boolean isXML11Version) {
        if (datavalue != null && datavalue.length() != 0) {
            char[] dataarray = datavalue.toCharArray();
            int datalength = dataarray.length;
            int i;
            int i2;
            char c;
            char c2;
            DOMErrorHandler dOMErrorHandler;
            DOMErrorImpl dOMErrorImpl;
            DOMLocatorImpl dOMLocatorImpl;
            if (isXML11Version) {
                i = 0;
                while (i < datalength) {
                    i2 = i + 1;
                    c = dataarray[i];
                    if (XML11Char.isXML11Invalid(c)) {
                        if (XMLChar.isHighSurrogate(c) && i2 < datalength) {
                            i = i2 + 1;
                            c2 = dataarray[i2];
                            if (!XMLChar.isLowSurrogate(c2) || !XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                                i2 = i;
                            }
                        }
                        dOMErrorHandler = errorHandler;
                        dOMErrorImpl = error;
                        dOMLocatorImpl = locator;
                        reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInComment", new Object[]{Integer.toString(dataarray[i2 - 1], 16)}), (short) 2, "wf-invalid-character");
                        i = i2;
                    } else {
                        if (c == '-' && i2 < datalength && dataarray[i2] == '-') {
                            reportDOMError(errorHandler, error, locator, DOMMessageFormatter.formatMessage(XMLMessageFormatter.XML_DOMAIN, "DashDashInComment", null), (short) 2, "wf-invalid-character");
                        }
                        i = i2;
                    }
                }
                i2 = i;
                return;
            }
            i = 0;
            while (i < datalength) {
                i2 = i + 1;
                c = dataarray[i];
                if (XMLChar.isInvalid(c)) {
                    if (XMLChar.isHighSurrogate(c) && i2 < datalength) {
                        i = i2 + 1;
                        c2 = dataarray[i2];
                        if (!XMLChar.isLowSurrogate(c2) || !XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                            i2 = i;
                        }
                    }
                    dOMErrorHandler = errorHandler;
                    dOMErrorImpl = error;
                    dOMLocatorImpl = locator;
                    reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInComment", new Object[]{Integer.toString(dataarray[i2 - 1], 16)}), (short) 2, "wf-invalid-character");
                    i = i2;
                } else {
                    if (c == '-' && i2 < datalength && dataarray[i2] == '-') {
                        reportDOMError(errorHandler, error, locator, DOMMessageFormatter.formatMessage(XMLMessageFormatter.XML_DOMAIN, "DashDashInComment", null), (short) 2, "wf-invalid-character");
                    }
                    i = i2;
                }
            }
            i2 = i;
        }
    }

    public static final void isAttrValueWF(DOMErrorHandler errorHandler, DOMErrorImpl error, DOMLocatorImpl locator, NamedNodeMap attributes, Attr a, String value, boolean xml11Version) {
        if ((a instanceof AttrImpl) && ((AttrImpl) a).hasStringValue()) {
            isXMLCharWF(errorHandler, error, locator, value, xml11Version);
            return;
        }
        NodeList children = a.getChildNodes();
        for (int j = 0; j < children.getLength(); j++) {
            Node child = children.item(j);
            if (child.getNodeType() == (short) 5) {
                Document owner = a.getOwnerDocument();
                Entity ent = null;
                if (owner != null) {
                    DocumentType docType = owner.getDoctype();
                    if (docType != null) {
                        ent = (Entity) docType.getEntities().getNamedItemNS("*", child.getNodeName());
                    }
                }
                if (ent == null) {
                    DOMErrorHandler dOMErrorHandler = errorHandler;
                    DOMErrorImpl dOMErrorImpl = error;
                    DOMLocatorImpl dOMLocatorImpl = locator;
                    reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "UndeclaredEntRefInAttrValue", new Object[]{a.getNodeName()}), (short) 2, "UndeclaredEntRefInAttrValue");
                }
            } else {
                isXMLCharWF(errorHandler, error, locator, child.getNodeValue(), xml11Version);
            }
        }
    }

    public static final void reportDOMError(DOMErrorHandler errorHandler, DOMErrorImpl error, DOMLocatorImpl locator, String message, short severity, String type) {
        if (errorHandler != null) {
            error.reset();
            error.fMessage = message;
            error.fSeverity = severity;
            error.fLocator = locator;
            error.fType = type;
            error.fRelatedData = locator.fRelatedNode;
            if (!errorHandler.handleError(error)) {
                throw abort;
            }
        }
        if (severity == (short) 3) {
            throw abort;
        }
    }

    protected final void updateQName(Node node, QName qname) {
        String str;
        String str2 = null;
        String prefix = node.getPrefix();
        String namespace = node.getNamespaceURI();
        String localName = node.getLocalName();
        if (prefix == null || prefix.length() == 0) {
            str = null;
        } else {
            str = this.fSymbolTable.addSymbol(prefix);
        }
        qname.prefix = str;
        if (localName != null) {
            str = this.fSymbolTable.addSymbol(localName);
        } else {
            str = null;
        }
        qname.localpart = str;
        qname.rawname = this.fSymbolTable.addSymbol(node.getNodeName());
        if (namespace != null) {
            str2 = this.fSymbolTable.addSymbol(namespace);
        }
        qname.uri = str2;
    }

    final String normalizeAttributeValue(String value, Attr attr) {
        if (!attr.getSpecified()) {
            return value;
        }
        int end = value.length();
        if (this.fNormalizedValue.ch.length < end) {
            this.fNormalizedValue.ch = new char[end];
        }
        this.fNormalizedValue.length = 0;
        boolean normalized = DEBUG_ND;
        int i = 0;
        while (i < end) {
            char c = value.charAt(i);
            char[] cArr;
            XMLString xMLString;
            int i2;
            if (c == '\t' || c == '\n') {
                cArr = this.fNormalizedValue.ch;
                xMLString = this.fNormalizedValue;
                i2 = xMLString.length;
                xMLString.length = i2 + 1;
                cArr[i2] = ' ';
                normalized = true;
            } else if (c == CharUtils.CR) {
                normalized = true;
                cArr = this.fNormalizedValue.ch;
                xMLString = this.fNormalizedValue;
                i2 = xMLString.length;
                xMLString.length = i2 + 1;
                cArr[i2] = ' ';
                int next = i + 1;
                if (next < end && value.charAt(next) == '\n') {
                    i = next;
                }
            } else {
                cArr = this.fNormalizedValue.ch;
                xMLString = this.fNormalizedValue;
                i2 = xMLString.length;
                xMLString.length = i2 + 1;
                cArr[i2] = c;
            }
            i++;
        }
        if (normalized) {
            value = this.fNormalizedValue.toString();
            attr.setValue(value);
        }
        return value;
    }

    public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException {
    }

    public void xmlDecl(String version, String encoding, String standalone, Augmentations augs) throws XNIException {
    }

    public void doctypeDecl(String rootElement, String publicId, String systemId, Augmentations augs) throws XNIException {
    }

    public void comment(XMLString text, Augmentations augs) throws XNIException {
    }

    public void processingInstruction(String target, XMLString data, Augmentations augs) throws XNIException {
    }

    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        Element currentElement = this.fCurrentNode;
        int attrCount = attributes.getLength();
        for (int i = 0; i < attrCount; i++) {
            attributes.getName(i, this.fAttrQName);
            Attr attr = currentElement.getAttributeNodeNS(this.fAttrQName.uri, this.fAttrQName.localpart);
            if (attr == null) {
                attr = currentElement.getAttributeNode(this.fAttrQName.rawname);
            }
            AttributePSVI attrPSVI = (AttributePSVI) attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
            if (attrPSVI != null) {
                XSTypeDefinition decl = attrPSVI.getMemberTypeDefinition();
                boolean id = DEBUG_ND;
                if (decl != null) {
                    id = ((XSSimpleType) decl).isIDType();
                } else {
                    decl = attrPSVI.getTypeDefinition();
                    if (decl != null) {
                        id = ((XSSimpleType) decl).isIDType();
                    }
                }
                if (id) {
                    ((ElementImpl) currentElement).setIdAttributeNode(attr, true);
                }
                if (this.fPSVI) {
                    ((PSVIAttrNSImpl) attr).setPSVI(attrPSVI);
                }
                ((AttrImpl) attr).setType(decl);
                if ((this.fConfiguration.features & 2) != 0) {
                    String normalizedValue = attrPSVI.getSchemaNormalizedValue();
                    if (normalizedValue != null) {
                        boolean specified = attr.getSpecified();
                        attr.setValue(normalizedValue);
                        if (!specified) {
                            ((AttrImpl) attr).setSpecified(specified);
                        }
                    }
                }
            } else {
                String type = null;
                if (Boolean.TRUE.equals(attributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_DECLARED))) {
                    type = attributes.getType(i);
                    if (SchemaSymbols.ATTVAL_ID.equals(type)) {
                        ((ElementImpl) currentElement).setIdAttributeNode(attr, true);
                    }
                }
                ((AttrImpl) attr).setType(type);
            }
        }
    }

    public void emptyElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        startElement(element, attributes, augs);
        endElement(element, augs);
    }

    public void startGeneralEntity(String name, XMLResourceIdentifier identifier, String encoding, Augmentations augs) throws XNIException {
    }

    public void textDecl(String version, String encoding, Augmentations augs) throws XNIException {
    }

    public void endGeneralEntity(String name, Augmentations augs) throws XNIException {
    }

    public void characters(XMLString text, Augmentations augs) throws XNIException {
    }

    public void ignorableWhitespace(XMLString text, Augmentations augs) throws XNIException {
        this.fAllWhitespace = true;
    }

    public void endElement(QName element, Augmentations augs) throws XNIException {
        if (augs != null) {
            ElementPSVI elementPSVI = (ElementPSVI) augs.getItem(Constants.ELEMENT_PSVI);
            if (elementPSVI != null) {
                ElementImpl elementNode = this.fCurrentNode;
                if (this.fPSVI) {
                    ((PSVIElementNSImpl) this.fCurrentNode).setPSVI(elementPSVI);
                }
                if (elementNode instanceof ElementNSImpl) {
                    XSTypeDefinition type = elementPSVI.getMemberTypeDefinition();
                    if (type == null) {
                        type = elementPSVI.getTypeDefinition();
                    }
                    ((ElementNSImpl) elementNode).setType(type);
                }
                String normalizedValue = elementPSVI.getSchemaNormalizedValue();
                if ((this.fConfiguration.features & 2) != 0) {
                    if (normalizedValue != null) {
                        elementNode.setTextContent(normalizedValue);
                        return;
                    }
                    return;
                } else if (elementNode.getTextContent().length() == 0 && normalizedValue != null) {
                    elementNode.setTextContent(normalizedValue);
                    return;
                } else {
                    return;
                }
            }
        }
        if (this.fCurrentNode instanceof ElementNSImpl) {
            ((ElementNSImpl) this.fCurrentNode).setType(null);
        }
    }

    public void startCDATA(Augmentations augs) throws XNIException {
    }

    public void endCDATA(Augmentations augs) throws XNIException {
    }

    public void endDocument(Augmentations augs) throws XNIException {
    }

    public void setDocumentSource(XMLDocumentSource source) {
    }

    public XMLDocumentSource getDocumentSource() {
        return null;
    }
}
