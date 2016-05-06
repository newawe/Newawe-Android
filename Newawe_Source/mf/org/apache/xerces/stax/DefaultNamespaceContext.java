package mf.org.apache.xerces.stax;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import mf.javax.xml.XMLConstants;
import mf.javax.xml.namespace.NamespaceContext;
import mf.org.apache.xml.serialize.Method;
import org.apache.commons.lang.StringUtils;

public final class DefaultNamespaceContext implements NamespaceContext {
    private static final DefaultNamespaceContext DEFAULT_NAMESPACE_CONTEXT_INSTANCE;

    /* renamed from: mf.org.apache.xerces.stax.DefaultNamespaceContext.1 */
    class C08961 implements Iterator {
        boolean more;

        C08961() {
            this.more = true;
        }

        public boolean hasNext() {
            return this.more;
        }

        public Object next() {
            if (hasNext()) {
                this.more = false;
                return Method.XML;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: mf.org.apache.xerces.stax.DefaultNamespaceContext.2 */
    class C08972 implements Iterator {
        boolean more;

        C08972() {
            this.more = true;
        }

        public boolean hasNext() {
            return this.more;
        }

        public Object next() {
            if (hasNext()) {
                this.more = false;
                return XMLConstants.XMLNS_ATTRIBUTE;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    static {
        DEFAULT_NAMESPACE_CONTEXT_INSTANCE = new DefaultNamespaceContext();
    }

    private DefaultNamespaceContext() {
    }

    public static DefaultNamespaceContext getInstance() {
        return DEFAULT_NAMESPACE_CONTEXT_INSTANCE;
    }

    public String getNamespaceURI(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Prefix cannot be null.");
        } else if (Method.XML.equals(prefix)) {
            return XMLConstants.XML_NS_URI;
        } else {
            if (XMLConstants.XMLNS_ATTRIBUTE.equals(prefix)) {
                return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
            }
            return StringUtils.EMPTY;
        }
    }

    public String getPrefix(String namespaceURI) {
        if (namespaceURI == null) {
            throw new IllegalArgumentException("Namespace URI cannot be null.");
        } else if (XMLConstants.XML_NS_URI.equals(namespaceURI)) {
            return Method.XML;
        } else {
            if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI)) {
                return XMLConstants.XMLNS_ATTRIBUTE;
            }
            return null;
        }
    }

    public Iterator getPrefixes(String namespaceURI) {
        if (namespaceURI == null) {
            throw new IllegalArgumentException("Namespace URI cannot be null.");
        } else if (XMLConstants.XML_NS_URI.equals(namespaceURI)) {
            return new C08961();
        } else {
            return XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI) ? new C08972() : Collections.EMPTY_LIST.iterator();
        }
    }
}
