package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLSmallElement;

public class WMLSmallElementImpl extends WMLElementImpl implements WMLSmallElement {
    private static final long serialVersionUID = 2654490940644799492L;

    public WMLSmallElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setClassName(String newValue) {
        setAttribute("class", newValue);
    }

    public String getClassName() {
        return getAttribute("class");
    }

    public void setXmlLang(String newValue) {
        setAttribute("xml:lang", newValue);
    }

    public String getXmlLang() {
        return getAttribute("xml:lang");
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }
}
