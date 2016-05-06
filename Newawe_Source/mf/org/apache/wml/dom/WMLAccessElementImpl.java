package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLAccessElement;
import org.apache.http.cookie.ClientCookie;

public class WMLAccessElementImpl extends WMLElementImpl implements WMLAccessElement {
    private static final long serialVersionUID = -235627181817012806L;

    public WMLAccessElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setClassName(String newValue) {
        setAttribute("class", newValue);
    }

    public String getClassName() {
        return getAttribute("class");
    }

    public void setDomain(String newValue) {
        setAttribute(ClientCookie.DOMAIN_ATTR, newValue);
    }

    public String getDomain() {
        return getAttribute(ClientCookie.DOMAIN_ATTR);
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setPath(String newValue) {
        setAttribute(ClientCookie.PATH_ATTR, newValue);
    }

    public String getPath() {
        return getAttribute(ClientCookie.PATH_ATTR);
    }
}
