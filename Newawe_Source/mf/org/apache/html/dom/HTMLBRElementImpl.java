package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLBRElement;

public class HTMLBRElementImpl extends HTMLElementImpl implements HTMLBRElement {
    private static final long serialVersionUID = 311960206282154750L;

    public String getClear() {
        return capitalize(getAttribute("clear"));
    }

    public void setClear(String clear) {
        setAttribute("clear", clear);
    }

    public HTMLBRElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
