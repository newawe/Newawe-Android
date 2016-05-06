package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLSetvarElement;

public class WMLSetvarElementImpl extends WMLElementImpl implements WMLSetvarElement {
    private static final long serialVersionUID = -1944519734782236471L;

    public WMLSetvarElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setValue(String newValue) {
        setAttribute("value", newValue);
    }

    public String getValue() {
        return getAttribute("value");
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

    public void setName(String newValue) {
        setAttribute("name", newValue);
    }

    public String getName() {
        return getAttribute("name");
    }
}
