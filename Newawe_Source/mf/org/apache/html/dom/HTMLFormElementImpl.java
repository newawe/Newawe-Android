package mf.org.apache.html.dom;

import mf.javax.xml.transform.OutputKeys;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.NodeList;
import mf.org.w3c.dom.html.HTMLCollection;
import mf.org.w3c.dom.html.HTMLFormElement;

public class HTMLFormElementImpl extends HTMLElementImpl implements HTMLFormElement {
    private static final long serialVersionUID = -7324749629151493210L;
    private HTMLCollectionImpl _elements;

    public HTMLCollection getElements() {
        if (this._elements == null) {
            this._elements = new HTMLCollectionImpl(this, (short) 8);
        }
        return this._elements;
    }

    public int getLength() {
        return getElements().getLength();
    }

    public String getName() {
        return getAttribute("name");
    }

    public void setName(String name) {
        setAttribute("name", name);
    }

    public String getAcceptCharset() {
        return getAttribute("accept-charset");
    }

    public void setAcceptCharset(String acceptCharset) {
        setAttribute("accept-charset", acceptCharset);
    }

    public String getAction() {
        return getAttribute("action");
    }

    public void setAction(String action) {
        setAttribute("action", action);
    }

    public String getEnctype() {
        return getAttribute("enctype");
    }

    public void setEnctype(String enctype) {
        setAttribute("enctype", enctype);
    }

    public String getMethod() {
        return capitalize(getAttribute(OutputKeys.METHOD));
    }

    public void setMethod(String method) {
        setAttribute(OutputKeys.METHOD, method);
    }

    public String getTarget() {
        return getAttribute("target");
    }

    public void setTarget(String target) {
        setAttribute("target", target);
    }

    public void submit() {
    }

    public void reset() {
    }

    public NodeList getChildNodes() {
        return getChildNodesUnoptimized();
    }

    public Node cloneNode(boolean deep) {
        HTMLFormElementImpl clonedNode = (HTMLFormElementImpl) super.cloneNode(deep);
        clonedNode._elements = null;
        return clonedNode;
    }

    public HTMLFormElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
