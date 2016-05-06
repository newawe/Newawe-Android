package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLHtmlElement;
import org.apache.http.cookie.ClientCookie;

public class HTMLHtmlElementImpl extends HTMLElementImpl implements HTMLHtmlElement {
    private static final long serialVersionUID = -4489734201536616166L;

    public String getVersion() {
        return capitalize(getAttribute(ClientCookie.VERSION_ATTR));
    }

    public void setVersion(String version) {
        setAttribute(ClientCookie.VERSION_ATTR, version);
    }

    public HTMLHtmlElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
