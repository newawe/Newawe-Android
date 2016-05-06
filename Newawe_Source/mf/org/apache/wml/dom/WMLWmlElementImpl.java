package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLWmlElement;

public class WMLWmlElementImpl extends WMLElementImpl implements WMLWmlElement {
    private static final long serialVersionUID = -7008023851895920651L;

    public WMLWmlElementImpl(WMLDocumentImpl owner, String tagName) {
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
