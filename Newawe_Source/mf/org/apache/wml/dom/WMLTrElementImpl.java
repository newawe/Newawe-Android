package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLTrElement;

public class WMLTrElementImpl extends WMLElementImpl implements WMLTrElement {
    private static final long serialVersionUID = -4304021232051604343L;

    public WMLTrElementImpl(WMLDocumentImpl owner, String tagName) {
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
}
