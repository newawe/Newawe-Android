package mf.org.apache.xerces.impl.dtd;

import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xni.XNIException;

public class XML11NSDTDValidator extends XML11DTDValidator {
    private final QName fAttributeQName;

    public XML11NSDTDValidator() {
        this.fAttributeQName = new QName();
    }

    protected final void startNamespaceScope(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {
        int i;
        this.fNamespaceContext.pushContext();
        if (element.prefix == XMLSymbols.PREFIX_XMLNS) {
            Object[] objArr = new Object[1];
            objArr[0] = element.rawname;
            this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "ElementXMLNSPrefix", objArr, (short) 2);
        }
        int length = attributes.getLength();
        for (i = 0; i < length; i++) {
            String localpart = attributes.getLocalName(i);
            String prefix = attributes.getPrefix(i);
            if (prefix == XMLSymbols.PREFIX_XMLNS || (prefix == XMLSymbols.EMPTY_STRING && localpart == XMLSymbols.PREFIX_XMLNS)) {
                String uri = this.fSymbolTable.addSymbol(attributes.getValue(i));
                if (prefix == XMLSymbols.PREFIX_XMLNS && localpart == XMLSymbols.PREFIX_XMLNS) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXMLNS", new Object[]{attributes.getQName(i)}, (short) 2);
                }
                if (uri == NamespaceContext.XMLNS_URI) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXMLNS", new Object[]{attributes.getQName(i)}, (short) 2);
                }
                if (localpart == XMLSymbols.PREFIX_XML) {
                    if (uri != NamespaceContext.XML_URI) {
                        this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXML", new Object[]{attributes.getQName(i)}, (short) 2);
                    }
                } else if (uri == NamespaceContext.XML_URI) {
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "CantBindXML", new Object[]{attributes.getQName(i)}, (short) 2);
                }
                prefix = localpart != XMLSymbols.PREFIX_XMLNS ? localpart : XMLSymbols.EMPTY_STRING;
                NamespaceContext namespaceContext = this.fNamespaceContext;
                if (uri.length() == 0) {
                    uri = null;
                }
                namespaceContext.declarePrefix(prefix, uri);
            }
        }
        element.uri = this.fNamespaceContext.getURI(element.prefix != null ? element.prefix : XMLSymbols.EMPTY_STRING);
        if (element.prefix == null && element.uri != null) {
            element.prefix = XMLSymbols.EMPTY_STRING;
        }
        if (element.prefix != null && element.uri == null) {
            objArr = new Object[2];
            objArr[0] = element.prefix;
            objArr[1] = element.rawname;
            this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "ElementPrefixUnbound", objArr, (short) 2);
        }
        for (i = 0; i < length; i++) {
            attributes.getName(i, this.fAttributeQName);
            String aprefix = this.fAttributeQName.prefix != null ? this.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
            String arawname = this.fAttributeQName.rawname;
            if (arawname == XMLSymbols.PREFIX_XMLNS) {
                this.fAttributeQName.uri = this.fNamespaceContext.getURI(XMLSymbols.PREFIX_XMLNS);
                attributes.setName(i, this.fAttributeQName);
            } else if (aprefix != XMLSymbols.EMPTY_STRING) {
                this.fAttributeQName.uri = this.fNamespaceContext.getURI(aprefix);
                if (this.fAttributeQName.uri == null) {
                    objArr = new Object[3];
                    objArr[0] = element.rawname;
                    objArr[1] = arawname;
                    objArr[2] = aprefix;
                    this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "AttributePrefixUnbound", objArr, (short) 2);
                }
                attributes.setName(i, this.fAttributeQName);
            }
        }
        int attrCount = attributes.getLength();
        for (i = 0; i < attrCount - 1; i++) {
            String auri = attributes.getURI(i);
            if (!(auri == null || auri == NamespaceContext.XMLNS_URI)) {
                String alocalpart = attributes.getLocalName(i);
                for (int j = i + 1; j < attrCount; j++) {
                    String blocalpart = attributes.getLocalName(j);
                    String buri = attributes.getURI(j);
                    if (alocalpart == blocalpart && auri == buri) {
                        objArr = new Object[3];
                        objArr[0] = element.rawname;
                        objArr[1] = alocalpart;
                        objArr[2] = auri;
                        this.fErrorReporter.reportError(XMLMessageFormatter.XMLNS_DOMAIN, "AttributeNSNotUnique", objArr, (short) 2);
                    }
                }
            }
        }
    }

    protected void endNamespaceScope(QName element, Augmentations augs, boolean isEmpty) throws XNIException {
        String eprefix = element.prefix != null ? element.prefix : XMLSymbols.EMPTY_STRING;
        element.uri = this.fNamespaceContext.getURI(eprefix);
        if (element.uri != null) {
            element.prefix = eprefix;
        }
        if (!(this.fDocumentHandler == null || isEmpty)) {
            this.fDocumentHandler.endElement(element, augs);
        }
        this.fNamespaceContext.popContext();
    }
}
