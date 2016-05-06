package mf.org.apache.html.dom;

import java.io.StringWriter;
import java.util.Hashtable;
import java.util.Locale;
import mf.org.apache.xerces.dom.DocumentImpl;
import mf.org.apache.xerces.dom.ElementImpl;
import mf.org.apache.xml.serialize.LineSeparator;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.html.HTMLCollection;
import mf.org.w3c.dom.html.HTMLDocument;
import mf.org.w3c.dom.html.HTMLElement;
import mf.org.w3c.dom.html.HTMLHtmlElement;
import mf.org.w3c.dom.html.HTMLTitleElement;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpHead;

public class HTMLDocumentImpl extends DocumentImpl implements HTMLDocument {
    private static final Class[] _elemClassSigHTML;
    private static Hashtable _elementTypesHTML = null;
    private static final long serialVersionUID = 4285791750126227180L;
    private HTMLCollectionImpl _anchors;
    private HTMLCollectionImpl _applets;
    private HTMLCollectionImpl _forms;
    private HTMLCollectionImpl _images;
    private HTMLCollectionImpl _links;
    private StringWriter _writer;

    static {
        _elemClassSigHTML = new Class[]{HTMLDocumentImpl.class, String.class};
    }

    public HTMLDocumentImpl() {
        populateElementTypes();
    }

    public synchronized Element getDocumentElement() {
        Element element;
        Node html;
        for (html = getFirstChild(); html != null; html = html.getNextSibling()) {
            if (html instanceof HTMLHtmlElement) {
                Object obj = (HTMLElement) html;
                break;
            }
        }
        html = new HTMLHtmlElementImpl(this, "HTML");
        Node child = getFirstChild();
        while (child != null) {
            Node next = child.getNextSibling();
            html.appendChild(child);
            child = next;
        }
        appendChild(html);
        element = (HTMLElement) html;
        return element;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized mf.org.w3c.dom.html.HTMLElement getHead() {
        /*
        r6 = this;
        monitor-enter(r6);
        r3 = r6.getDocumentElement();	 Catch:{ all -> 0x003b }
        monitor-enter(r3);	 Catch:{ all -> 0x003b }
        r2 = r3.getFirstChild();	 Catch:{ all -> 0x0038 }
    L_0x000a:
        if (r2 == 0) goto L_0x0010;
    L_0x000c:
        r5 = r2 instanceof mf.org.w3c.dom.html.HTMLHeadElement;	 Catch:{ all -> 0x0038 }
        if (r5 == 0) goto L_0x0023;
    L_0x0010:
        if (r2 == 0) goto L_0x003e;
    L_0x0012:
        monitor-enter(r2);	 Catch:{ all -> 0x0038 }
        r1 = r3.getFirstChild();	 Catch:{ all -> 0x0035 }
    L_0x0017:
        if (r1 == 0) goto L_0x001b;
    L_0x0019:
        if (r1 != r2) goto L_0x0028;
    L_0x001b:
        monitor-exit(r2);	 Catch:{ all -> 0x0035 }
        r0 = r2;
        r0 = (mf.org.w3c.dom.html.HTMLElement) r0;	 Catch:{ all -> 0x0038 }
        r5 = r0;
        monitor-exit(r3);	 Catch:{ all -> 0x0038 }
    L_0x0021:
        monitor-exit(r6);
        return r5;
    L_0x0023:
        r2 = r2.getNextSibling();	 Catch:{ all -> 0x0038 }
        goto L_0x000a;
    L_0x0028:
        r4 = r1.getNextSibling();	 Catch:{ all -> 0x0035 }
        r5 = r2.getFirstChild();	 Catch:{ all -> 0x0035 }
        r2.insertBefore(r1, r5);	 Catch:{ all -> 0x0035 }
        r1 = r4;
        goto L_0x0017;
    L_0x0035:
        r5 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0035 }
        throw r5;	 Catch:{ all -> 0x0038 }
    L_0x0038:
        r5 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0038 }
        throw r5;	 Catch:{ all -> 0x003b }
    L_0x003b:
        r5 = move-exception;
        monitor-exit(r6);
        throw r5;
    L_0x003e:
        r2 = new mf.org.apache.html.dom.HTMLHeadElementImpl;	 Catch:{ all -> 0x0038 }
        r5 = "HEAD";
        r2.<init>(r6, r5);	 Catch:{ all -> 0x0038 }
        r5 = r3.getFirstChild();	 Catch:{ all -> 0x0038 }
        r3.insertBefore(r2, r5);	 Catch:{ all -> 0x0038 }
        monitor-exit(r3);	 Catch:{ all -> 0x0038 }
        r0 = r2;
        r0 = (mf.org.w3c.dom.html.HTMLElement) r0;	 Catch:{ all -> 0x003b }
        r5 = r0;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.html.dom.HTMLDocumentImpl.getHead():mf.org.w3c.dom.html.HTMLElement");
    }

    public synchronized String getTitle() {
        String text;
        NodeList list = getHead().getElementsByTagName("TITLE");
        if (list.getLength() > 0) {
            text = ((HTMLTitleElement) list.item(0)).getText();
        } else {
            text = StringUtils.EMPTY;
        }
        return text;
    }

    public synchronized void setTitle(String newTitle) {
        Node head = getHead();
        NodeList list = head.getElementsByTagName("TITLE");
        Node title;
        if (list.getLength() > 0) {
            title = list.item(0);
            if (title.getParentNode() != head) {
                head.appendChild(title);
            }
            ((HTMLTitleElement) title).setText(newTitle);
        } else {
            title = new HTMLTitleElementImpl(this, "TITLE");
            ((HTMLTitleElement) title).setText(newTitle);
            head.appendChild(title);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized mf.org.w3c.dom.html.HTMLElement getBody() {
        /*
        r7 = this;
        monitor-enter(r7);
        r4 = r7.getDocumentElement();	 Catch:{ all -> 0x0043 }
        r3 = r7.getHead();	 Catch:{ all -> 0x0043 }
        monitor-enter(r4);	 Catch:{ all -> 0x0043 }
        r1 = r3.getNextSibling();	 Catch:{ all -> 0x0040 }
    L_0x000e:
        if (r1 == 0) goto L_0x0018;
    L_0x0010:
        r6 = r1 instanceof mf.org.w3c.dom.html.HTMLBodyElement;	 Catch:{ all -> 0x0040 }
        if (r6 != 0) goto L_0x0018;
    L_0x0014:
        r6 = r1 instanceof mf.org.w3c.dom.html.HTMLFrameSetElement;	 Catch:{ all -> 0x0040 }
        if (r6 == 0) goto L_0x002b;
    L_0x0018:
        if (r1 == 0) goto L_0x0046;
    L_0x001a:
        monitor-enter(r1);	 Catch:{ all -> 0x0040 }
        r2 = r3.getNextSibling();	 Catch:{ all -> 0x003d }
    L_0x001f:
        if (r2 == 0) goto L_0x0023;
    L_0x0021:
        if (r2 != r1) goto L_0x0030;
    L_0x0023:
        monitor-exit(r1);	 Catch:{ all -> 0x003d }
        r0 = r1;
        r0 = (mf.org.w3c.dom.html.HTMLElement) r0;	 Catch:{ all -> 0x0040 }
        r6 = r0;
        monitor-exit(r4);	 Catch:{ all -> 0x0040 }
    L_0x0029:
        monitor-exit(r7);
        return r6;
    L_0x002b:
        r1 = r1.getNextSibling();	 Catch:{ all -> 0x0040 }
        goto L_0x000e;
    L_0x0030:
        r5 = r2.getNextSibling();	 Catch:{ all -> 0x003d }
        r6 = r1.getFirstChild();	 Catch:{ all -> 0x003d }
        r1.insertBefore(r2, r6);	 Catch:{ all -> 0x003d }
        r2 = r5;
        goto L_0x001f;
    L_0x003d:
        r6 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x003d }
        throw r6;	 Catch:{ all -> 0x0040 }
    L_0x0040:
        r6 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0040 }
        throw r6;	 Catch:{ all -> 0x0043 }
    L_0x0043:
        r6 = move-exception;
        monitor-exit(r7);
        throw r6;
    L_0x0046:
        r1 = new mf.org.apache.html.dom.HTMLBodyElementImpl;	 Catch:{ all -> 0x0040 }
        r6 = "BODY";
        r1.<init>(r7, r6);	 Catch:{ all -> 0x0040 }
        r4.appendChild(r1);	 Catch:{ all -> 0x0040 }
        monitor-exit(r4);	 Catch:{ all -> 0x0040 }
        r0 = r1;
        r0 = (mf.org.w3c.dom.html.HTMLElement) r0;	 Catch:{ all -> 0x0043 }
        r6 = r0;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.html.dom.HTMLDocumentImpl.getBody():mf.org.w3c.dom.html.HTMLElement");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setBody(mf.org.w3c.dom.html.HTMLElement r7) {
        /*
        r6 = this;
        monitor-enter(r6);
        monitor-enter(r7);	 Catch:{ all -> 0x0038 }
        r3 = r6.getDocumentElement();	 Catch:{ all -> 0x0035 }
        r2 = r6.getHead();	 Catch:{ all -> 0x0035 }
        monitor-enter(r3);	 Catch:{ all -> 0x0035 }
        r5 = "BODY";
        r4 = r6.getElementsByTagName(r5);	 Catch:{ all -> 0x0042 }
        r5 = r4.getLength();	 Catch:{ all -> 0x0042 }
        if (r5 <= 0) goto L_0x004a;
    L_0x0017:
        r5 = 0;
        r0 = r4.item(r5);	 Catch:{ all -> 0x0042 }
        monitor-enter(r0);	 Catch:{ all -> 0x0042 }
        r1 = r2;
    L_0x001e:
        if (r1 != 0) goto L_0x0028;
    L_0x0020:
        r3.appendChild(r7);	 Catch:{ all -> 0x003f }
        monitor-exit(r0);	 Catch:{ all -> 0x003f }
        monitor-exit(r3);	 Catch:{ all -> 0x0042 }
        monitor-exit(r7);	 Catch:{ all -> 0x0035 }
    L_0x0026:
        monitor-exit(r6);
        return;
    L_0x0028:
        r5 = r1 instanceof mf.org.w3c.dom.Element;	 Catch:{ all -> 0x003f }
        if (r5 == 0) goto L_0x0045;
    L_0x002c:
        if (r1 == r0) goto L_0x003b;
    L_0x002e:
        r3.insertBefore(r7, r1);	 Catch:{ all -> 0x003f }
    L_0x0031:
        monitor-exit(r0);	 Catch:{ all -> 0x003f }
        monitor-exit(r3);	 Catch:{ all -> 0x0042 }
        monitor-exit(r7);	 Catch:{ all -> 0x0035 }
        goto L_0x0026;
    L_0x0035:
        r5 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0035 }
        throw r5;	 Catch:{ all -> 0x0038 }
    L_0x0038:
        r5 = move-exception;
        monitor-exit(r6);
        throw r5;
    L_0x003b:
        r3.replaceChild(r7, r0);	 Catch:{ all -> 0x003f }
        goto L_0x0031;
    L_0x003f:
        r5 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x003f }
        throw r5;	 Catch:{ all -> 0x0042 }
    L_0x0042:
        r5 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0042 }
        throw r5;	 Catch:{ all -> 0x0035 }
    L_0x0045:
        r1 = r1.getNextSibling();	 Catch:{ all -> 0x003f }
        goto L_0x001e;
    L_0x004a:
        r3.appendChild(r7);	 Catch:{ all -> 0x0042 }
        monitor-exit(r3);	 Catch:{ all -> 0x0042 }
        monitor-exit(r7);	 Catch:{ all -> 0x0035 }
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.html.dom.HTMLDocumentImpl.setBody(mf.org.w3c.dom.html.HTMLElement):void");
    }

    public synchronized Element getElementById(String elementId) {
        Element idElement;
        idElement = super.getElementById(elementId);
        if (idElement == null) {
            idElement = getElementById(elementId, this);
        }
        return idElement;
    }

    public NodeList getElementsByName(String elementName) {
        return new NameNodeListImpl(this, elementName);
    }

    public final NodeList getElementsByTagName(String tagName) {
        return super.getElementsByTagName(tagName.toUpperCase(Locale.ENGLISH));
    }

    public final NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        if (namespaceURI == null || namespaceURI.length() <= 0) {
            return super.getElementsByTagName(localName.toUpperCase(Locale.ENGLISH));
        }
        return super.getElementsByTagNameNS(namespaceURI, localName.toUpperCase(Locale.ENGLISH));
    }

    public Element createElementNS(String namespaceURI, String qualifiedName, String localpart) throws DOMException {
        return createElementNS(namespaceURI, qualifiedName);
    }

    public Element createElementNS(String namespaceURI, String qualifiedName) {
        if (namespaceURI == null || namespaceURI.length() == 0) {
            return createElement(qualifiedName);
        }
        return super.createElementNS(namespaceURI, qualifiedName);
    }

    public Element createElement(String tagName) throws DOMException {
        tagName = tagName.toUpperCase(Locale.ENGLISH);
        Class elemClass = (Class) _elementTypesHTML.get(tagName);
        if (elemClass == null) {
            return new HTMLElementImpl(this, tagName);
        }
        try {
            return (Element) elemClass.getConstructor(_elemClassSigHTML).newInstance(new Object[]{this, tagName});
        } catch (Exception e) {
            throw new IllegalStateException("HTM15 Tag '" + tagName + "' associated with an Element class that failed to construct.\n" + tagName);
        }
    }

    public Attr createAttribute(String name) throws DOMException {
        return super.createAttribute(name.toLowerCase(Locale.ENGLISH));
    }

    public String getReferrer() {
        return null;
    }

    public String getDomain() {
        return null;
    }

    public String getURL() {
        return null;
    }

    public String getCookie() {
        return null;
    }

    public void setCookie(String cookie) {
    }

    public HTMLCollection getImages() {
        if (this._images == null) {
            this._images = new HTMLCollectionImpl(getBody(), (short) 3);
        }
        return this._images;
    }

    public HTMLCollection getApplets() {
        if (this._applets == null) {
            this._applets = new HTMLCollectionImpl(getBody(), (short) 4);
        }
        return this._applets;
    }

    public HTMLCollection getLinks() {
        if (this._links == null) {
            this._links = new HTMLCollectionImpl(getBody(), (short) 5);
        }
        return this._links;
    }

    public HTMLCollection getForms() {
        if (this._forms == null) {
            this._forms = new HTMLCollectionImpl(getBody(), (short) 2);
        }
        return this._forms;
    }

    public HTMLCollection getAnchors() {
        if (this._anchors == null) {
            this._anchors = new HTMLCollectionImpl(getBody(), (short) 1);
        }
        return this._anchors;
    }

    public void open() {
        if (this._writer == null) {
            this._writer = new StringWriter();
        }
    }

    public void close() {
        if (this._writer != null) {
            this._writer = null;
        }
    }

    public void write(String text) {
        if (this._writer != null) {
            this._writer.write(text);
        }
    }

    public void writeln(String text) {
        if (this._writer != null) {
            this._writer.write(new StringBuilder(String.valueOf(text)).append(LineSeparator.Web).toString());
        }
    }

    public Node cloneNode(boolean deep) {
        HTMLDocumentImpl newdoc = new HTMLDocumentImpl();
        callUserDataHandlers(this, newdoc, (short) 1);
        cloneNode(newdoc, deep);
        return newdoc;
    }

    protected boolean canRenameElements(String newNamespaceURI, String newNodeName, ElementImpl el) {
        if (el.getNamespaceURI() != null) {
            if (newNamespaceURI != null) {
                return true;
            }
            return false;
        } else if (((Class) _elementTypesHTML.get(newNodeName.toUpperCase(Locale.ENGLISH))) != ((Class) _elementTypesHTML.get(el.getTagName()))) {
            return false;
        } else {
            return true;
        }
    }

    private Element getElementById(String elementId, Node node) {
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof Element) {
                if (elementId.equals(((Element) child).getAttribute("id"))) {
                    return (Element) child;
                }
                Element result = getElementById(elementId, child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private static synchronized void populateElementTypes() {
        synchronized (HTMLDocumentImpl.class) {
            if (_elementTypesHTML == null) {
                _elementTypesHTML = new Hashtable(63);
                populateElementType("A", "HTMLAnchorElementImpl");
                populateElementType("APPLET", "HTMLAppletElementImpl");
                populateElementType("AREA", "HTMLAreaElementImpl");
                populateElementType("BASE", "HTMLBaseElementImpl");
                populateElementType("BASEFONT", "HTMLBaseFontElementImpl");
                populateElementType("BLOCKQUOTE", "HTMLQuoteElementImpl");
                populateElementType("BODY", "HTMLBodyElementImpl");
                populateElementType("BR", "HTMLBRElementImpl");
                populateElementType("BUTTON", "HTMLButtonElementImpl");
                populateElementType("DEL", "HTMLModElementImpl");
                populateElementType("DIR", "HTMLDirectoryElementImpl");
                populateElementType("DIV", "HTMLDivElementImpl");
                populateElementType("DL", "HTMLDListElementImpl");
                populateElementType("FIELDSET", "HTMLFieldSetElementImpl");
                populateElementType("FONT", "HTMLFontElementImpl");
                populateElementType("FORM", "HTMLFormElementImpl");
                populateElementType("FRAME", "HTMLFrameElementImpl");
                populateElementType("FRAMESET", "HTMLFrameSetElementImpl");
                populateElementType(HttpHead.METHOD_NAME, "HTMLHeadElementImpl");
                populateElementType("H1", "HTMLHeadingElementImpl");
                populateElementType("H2", "HTMLHeadingElementImpl");
                populateElementType("H3", "HTMLHeadingElementImpl");
                populateElementType("H4", "HTMLHeadingElementImpl");
                populateElementType("H5", "HTMLHeadingElementImpl");
                populateElementType("H6", "HTMLHeadingElementImpl");
                populateElementType("HR", "HTMLHRElementImpl");
                populateElementType("HTML", "HTMLHtmlElementImpl");
                populateElementType("IFRAME", "HTMLIFrameElementImpl");
                populateElementType("IMG", "HTMLImageElementImpl");
                populateElementType("INPUT", "HTMLInputElementImpl");
                populateElementType("INS", "HTMLModElementImpl");
                populateElementType("ISINDEX", "HTMLIsIndexElementImpl");
                populateElementType("LABEL", "HTMLLabelElementImpl");
                populateElementType("LEGEND", "HTMLLegendElementImpl");
                populateElementType("LI", "HTMLLIElementImpl");
                populateElementType("LINK", "HTMLLinkElementImpl");
                populateElementType("MAP", "HTMLMapElementImpl");
                populateElementType("MENU", "HTMLMenuElementImpl");
                populateElementType("META", "HTMLMetaElementImpl");
                populateElementType("OBJECT", "HTMLObjectElementImpl");
                populateElementType("OL", "HTMLOListElementImpl");
                populateElementType("OPTGROUP", "HTMLOptGroupElementImpl");
                populateElementType("OPTION", "HTMLOptionElementImpl");
                populateElementType("P", "HTMLParagraphElementImpl");
                populateElementType("PARAM", "HTMLParamElementImpl");
                populateElementType("PRE", "HTMLPreElementImpl");
                populateElementType("Q", "HTMLQuoteElementImpl");
                populateElementType("SCRIPT", "HTMLScriptElementImpl");
                populateElementType("SELECT", "HTMLSelectElementImpl");
                populateElementType("STYLE", "HTMLStyleElementImpl");
                populateElementType("TABLE", "HTMLTableElementImpl");
                populateElementType("CAPTION", "HTMLTableCaptionElementImpl");
                populateElementType("TD", "HTMLTableCellElementImpl");
                populateElementType("TH", "HTMLTableCellElementImpl");
                populateElementType("COL", "HTMLTableColElementImpl");
                populateElementType("COLGROUP", "HTMLTableColElementImpl");
                populateElementType("TR", "HTMLTableRowElementImpl");
                populateElementType("TBODY", "HTMLTableSectionElementImpl");
                populateElementType("THEAD", "HTMLTableSectionElementImpl");
                populateElementType("TFOOT", "HTMLTableSectionElementImpl");
                populateElementType("TEXTAREA", "HTMLTextAreaElementImpl");
                populateElementType("TITLE", "HTMLTitleElementImpl");
                populateElementType("UL", "HTMLUListElementImpl");
            }
        }
    }

    private static void populateElementType(String tagName, String className) {
        try {
            _elementTypesHTML.put(tagName, ObjectFactory.findProviderClass("org.apache.html.dom." + className, HTMLDocumentImpl.class.getClassLoader(), true));
        } catch (Exception e) {
            throw new RuntimeException("HTM019 OpenXML Error: Could not find or execute class " + className + " implementing HTML element " + tagName + LineSeparator.Web + className + "\t" + tagName);
        }
    }
}
