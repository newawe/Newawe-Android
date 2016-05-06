package mf.org.apache.html.dom;

import mf.org.w3c.dom.html.HTMLFrameElement;

public class HTMLFrameElementImpl extends HTMLElementImpl implements HTMLFrameElement {
    private static final long serialVersionUID = 635237057173695984L;

    public String getFrameBorder() {
        return getAttribute("frameborder");
    }

    public void setFrameBorder(String frameBorder) {
        setAttribute("frameborder", frameBorder);
    }

    public String getLongDesc() {
        return getAttribute("longdesc");
    }

    public void setLongDesc(String longDesc) {
        setAttribute("longdesc", longDesc);
    }

    public String getMarginHeight() {
        return getAttribute("marginheight");
    }

    public void setMarginHeight(String marginHeight) {
        setAttribute("marginheight", marginHeight);
    }

    public String getMarginWidth() {
        return getAttribute("marginwidth");
    }

    public void setMarginWidth(String marginWidth) {
        setAttribute("marginwidth", marginWidth);
    }

    public String getName() {
        return getAttribute("name");
    }

    public void setName(String name) {
        setAttribute("name", name);
    }

    public boolean getNoResize() {
        return getBinary("noresize");
    }

    public void setNoResize(boolean noResize) {
        setAttribute("noresize", noResize);
    }

    public String getScrolling() {
        return capitalize(getAttribute("scrolling"));
    }

    public void setScrolling(String scrolling) {
        setAttribute("scrolling", scrolling);
    }

    public String getSrc() {
        return getAttribute("src");
    }

    public void setSrc(String src) {
        setAttribute("src", src);
    }

    public HTMLFrameElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
