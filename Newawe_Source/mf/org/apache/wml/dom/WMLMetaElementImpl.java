package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLMetaElement;

public class WMLMetaElementImpl extends WMLElementImpl implements WMLMetaElement {
    private static final long serialVersionUID = -2791663042188681846L;

    public WMLMetaElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setForua(boolean newValue) {
        setAttribute("forua", newValue);
    }

    public boolean getForua() {
        return getAttribute("forua", false);
    }

    public void setScheme(String newValue) {
        setAttribute("scheme", newValue);
    }

    public String getScheme() {
        return getAttribute("scheme");
    }

    public void setClassName(String newValue) {
        setAttribute("class", newValue);
    }

    public String getClassName() {
        return getAttribute("class");
    }

    public void setHttpEquiv(String newValue) {
        setAttribute("http-equiv", newValue);
    }

    public String getHttpEquiv() {
        return getAttribute("http-equiv");
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setContent(String newValue) {
        setAttribute("content", newValue);
    }

    public String getContent() {
        return getAttribute("content");
    }

    public void setName(String newValue) {
        setAttribute("name", newValue);
    }

    public String getName() {
        return getAttribute("name");
    }
}
