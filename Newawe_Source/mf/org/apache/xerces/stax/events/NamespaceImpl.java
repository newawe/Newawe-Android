package mf.org.apache.xerces.stax.events;

import mf.javax.xml.XMLConstants;
import mf.javax.xml.namespace.QName;
import mf.javax.xml.stream.Location;
import mf.javax.xml.stream.events.Namespace;
import org.apache.commons.lang.StringUtils;

public final class NamespaceImpl extends AttributeImpl implements Namespace {
    private final String fNamespaceURI;
    private final String fPrefix;

    public NamespaceImpl(String prefix, String namespaceURI, Location location) {
        super(13, makeAttributeQName(prefix), namespaceURI, null, true, location);
        if (prefix == null) {
            prefix = StringUtils.EMPTY;
        }
        this.fPrefix = prefix;
        this.fNamespaceURI = namespaceURI;
    }

    private static QName makeAttributeQName(String prefix) {
        if (prefix == null || prefix.equals(StringUtils.EMPTY)) {
            return new QName(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, XMLConstants.XMLNS_ATTRIBUTE, StringUtils.EMPTY);
        }
        return new QName(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, prefix, XMLConstants.XMLNS_ATTRIBUTE);
    }

    public String getPrefix() {
        return this.fPrefix;
    }

    public String getNamespaceURI() {
        return this.fNamespaceURI;
    }

    public boolean isDefaultNamespaceDeclaration() {
        return this.fPrefix.length() == 0;
    }
}
