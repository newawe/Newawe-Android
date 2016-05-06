package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLOneventElement;

public class WMLOneventElementImpl extends WMLElementImpl implements WMLOneventElement {
    private static final long serialVersionUID = -4279215241146570871L;

    public WMLOneventElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setClassName(String newValue) {
        setAttribute("class", newValue);
    }

    public String getClassName() {
        return getAttribute("class");
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setType(String newValue) {
        setAttribute("type", newValue);
    }

    public String getType() {
        return getAttribute("type");
    }
}
