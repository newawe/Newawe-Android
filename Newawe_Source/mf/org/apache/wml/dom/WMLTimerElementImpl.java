package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLTimerElement;

public class WMLTimerElementImpl extends WMLElementImpl implements WMLTimerElement {
    private static final long serialVersionUID = 9055622169756832726L;

    public WMLTimerElementImpl(WMLDocumentImpl owner, String tagName) {
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
