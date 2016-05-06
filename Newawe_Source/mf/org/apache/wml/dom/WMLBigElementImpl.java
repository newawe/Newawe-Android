package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLBigElement;

public class WMLBigElementImpl extends WMLElementImpl implements WMLBigElement {
    private static final long serialVersionUID = -8826061369691668904L;

    public WMLBigElementImpl(WMLDocumentImpl owner, String tagName) {
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
