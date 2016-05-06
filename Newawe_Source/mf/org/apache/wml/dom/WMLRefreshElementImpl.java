package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLRefreshElement;

public class WMLRefreshElementImpl extends WMLElementImpl implements WMLRefreshElement {
    private static final long serialVersionUID = 8781837880806459398L;

    public WMLRefreshElementImpl(WMLDocumentImpl owner, String tagName) {
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
