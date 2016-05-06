package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLEmElement;

public class WMLEmElementImpl extends WMLElementImpl implements WMLEmElement {
    private static final long serialVersionUID = 7962278391619830801L;

    public WMLEmElementImpl(WMLDocumentImpl owner, String tagName) {
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
