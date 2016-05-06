package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLHeadElement;

public class WMLHeadElementImpl extends WMLElementImpl implements WMLHeadElement {
    private static final long serialVersionUID = 3311307374813188908L;

    public WMLHeadElementImpl(WMLDocumentImpl owner, String tagName) {
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
