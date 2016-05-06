package mf.org.apache.html.dom;

import java.util.Vector;
import mf.org.apache.xerces.dom.ElementImpl;
import mf.org.apache.xerces.dom.ProcessingInstructionImpl;
import mf.org.apache.xml.serialize.LineSeparator;
import mf.org.w3c.dom.html.HTMLDocument;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class HTMLBuilder implements DocumentHandler {
    protected ElementImpl _current;
    protected HTMLDocumentImpl _document;
    private boolean _done;
    private boolean _ignoreWhitespace;
    protected Vector _preRootNodes;

    public HTMLBuilder() {
        this._ignoreWhitespace = true;
        this._done = true;
    }

    public void startDocument() throws SAXException {
        if (this._done) {
            this._document = null;
            this._done = false;
            return;
        }
        throw new SAXException("HTM001 State error: startDocument fired twice on one builder.");
    }

    public void endDocument() throws SAXException {
        if (this._document == null) {
            throw new SAXException("HTM002 State error: document never started or missing document element.");
        } else if (this._current != null) {
            throw new SAXException("HTM003 State error: document ended before end of document element.");
        } else {
            this._current = null;
            this._done = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void startElement(java.lang.String r6, org.xml.sax.AttributeList r7) throws org.xml.sax.SAXException {
        /*
        r5 = this;
        monitor-enter(r5);
        if (r6 != 0) goto L_0x000e;
    L_0x0003:
        r3 = new org.xml.sax.SAXException;	 Catch:{ all -> 0x000b }
        r4 = "HTM004 Argument 'tagName' is null.";
        r3.<init>(r4);	 Catch:{ all -> 0x000b }
        throw r3;	 Catch:{ all -> 0x000b }
    L_0x000b:
        r3 = move-exception;
        monitor-exit(r5);
        throw r3;
    L_0x000e:
        r3 = r5._document;	 Catch:{ all -> 0x000b }
        if (r3 != 0) goto L_0x005b;
    L_0x0012:
        r3 = new mf.org.apache.html.dom.HTMLDocumentImpl;	 Catch:{ all -> 0x000b }
        r3.<init>();	 Catch:{ all -> 0x000b }
        r5._document = r3;	 Catch:{ all -> 0x000b }
        r3 = r5._document;	 Catch:{ all -> 0x000b }
        r0 = r3.getDocumentElement();	 Catch:{ all -> 0x000b }
        r0 = (mf.org.apache.xerces.dom.ElementImpl) r0;	 Catch:{ all -> 0x000b }
        r5._current = r0;	 Catch:{ all -> 0x000b }
        r3 = r5._current;	 Catch:{ all -> 0x000b }
        if (r3 != 0) goto L_0x002f;
    L_0x0027:
        r3 = new org.xml.sax.SAXException;	 Catch:{ all -> 0x000b }
        r4 = "HTM005 State error: Document.getDocumentElement returns null.";
        r3.<init>(r4);	 Catch:{ all -> 0x000b }
        throw r3;	 Catch:{ all -> 0x000b }
    L_0x002f:
        r3 = r5._preRootNodes;	 Catch:{ all -> 0x000b }
        if (r3 == 0) goto L_0x0041;
    L_0x0033:
        r3 = r5._preRootNodes;	 Catch:{ all -> 0x000b }
        r1 = r3.size();	 Catch:{ all -> 0x000b }
        r2 = r1;
    L_0x003a:
        r1 = r2 + -1;
        if (r2 > 0) goto L_0x004c;
    L_0x003e:
        r3 = 0;
        r5._preRootNodes = r3;	 Catch:{ all -> 0x000b }
    L_0x0041:
        if (r7 == 0) goto L_0x004a;
    L_0x0043:
        r1 = 0;
    L_0x0044:
        r3 = r7.getLength();	 Catch:{ all -> 0x000b }
        if (r1 < r3) goto L_0x0077;
    L_0x004a:
        monitor-exit(r5);
        return;
    L_0x004c:
        r4 = r5._document;	 Catch:{ all -> 0x000b }
        r3 = r5._preRootNodes;	 Catch:{ all -> 0x000b }
        r3 = r3.elementAt(r1);	 Catch:{ all -> 0x000b }
        r3 = (mf.org.w3c.dom.Node) r3;	 Catch:{ all -> 0x000b }
        r4.insertBefore(r3, r0);	 Catch:{ all -> 0x000b }
        r2 = r1;
        goto L_0x003a;
    L_0x005b:
        r3 = r5._current;	 Catch:{ all -> 0x000b }
        if (r3 != 0) goto L_0x0067;
    L_0x005f:
        r3 = new org.xml.sax.SAXException;	 Catch:{ all -> 0x000b }
        r4 = "HTM006 State error: startElement called after end of document element.";
        r3.<init>(r4);	 Catch:{ all -> 0x000b }
        throw r3;	 Catch:{ all -> 0x000b }
    L_0x0067:
        r3 = r5._document;	 Catch:{ all -> 0x000b }
        r0 = r3.createElement(r6);	 Catch:{ all -> 0x000b }
        r0 = (mf.org.apache.xerces.dom.ElementImpl) r0;	 Catch:{ all -> 0x000b }
        r3 = r5._current;	 Catch:{ all -> 0x000b }
        r3.appendChild(r0);	 Catch:{ all -> 0x000b }
        r5._current = r0;	 Catch:{ all -> 0x000b }
        goto L_0x0041;
    L_0x0077:
        r3 = r7.getName(r1);	 Catch:{ all -> 0x000b }
        r4 = r7.getValue(r1);	 Catch:{ all -> 0x000b }
        r0.setAttribute(r3, r4);	 Catch:{ all -> 0x000b }
        r1 = r1 + 1;
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.html.dom.HTMLBuilder.startElement(java.lang.String, org.xml.sax.AttributeList):void");
    }

    public void endElement(String tagName) throws SAXException {
        if (this._current == null) {
            throw new SAXException("HTM007 State error: endElement called with no current node.");
        } else if (!this._current.getNodeName().equalsIgnoreCase(tagName)) {
            throw new SAXException("HTM008 State error: mismatch in closing tag name " + tagName + LineSeparator.Web + tagName);
        } else if (this._current.getParentNode() == this._current.getOwnerDocument()) {
            this._current = null;
        } else {
            this._current = (ElementImpl) this._current.getParentNode();
        }
    }

    public void characters(String text) throws SAXException {
        if (this._current == null) {
            throw new SAXException("HTM009 State error: character data found outside of root element.");
        }
        this._current.appendChild(this._document.createTextNode(text));
    }

    public void characters(char[] text, int start, int length) throws SAXException {
        if (this._current == null) {
            throw new SAXException("HTM010 State error: character data found outside of root element.");
        }
        this._current.appendChild(this._document.createTextNode(new String(text, start, length)));
    }

    public void ignorableWhitespace(char[] text, int start, int length) throws SAXException {
        if (!this._ignoreWhitespace) {
            this._current.appendChild(this._document.createTextNode(new String(text, start, length)));
        }
    }

    public void processingInstruction(String target, String instruction) throws SAXException {
        if (this._current == null && this._document == null) {
            if (this._preRootNodes == null) {
                this._preRootNodes = new Vector();
            }
            this._preRootNodes.addElement(new ProcessingInstructionImpl(null, target, instruction));
        } else if (this._current != null || this._document == null) {
            this._current.appendChild(this._document.createProcessingInstruction(target, instruction));
        } else {
            this._document.appendChild(this._document.createProcessingInstruction(target, instruction));
        }
    }

    public HTMLDocument getHTMLDocument() {
        return this._document;
    }

    public void setDocumentLocator(Locator locator) {
    }
}
