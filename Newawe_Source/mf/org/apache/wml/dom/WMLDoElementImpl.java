package mf.org.apache.wml.dom;

import mf.org.apache.wml.WMLDoElement;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class WMLDoElementImpl extends WMLElementImpl implements WMLDoElement {
    private static final long serialVersionUID = 7755861458464251322L;

    public WMLDoElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setOptional(String newValue) {
        setAttribute(SchemaSymbols.ATTVAL_OPTIONAL, newValue);
    }

    public String getOptional() {
        return getAttribute(SchemaSymbols.ATTVAL_OPTIONAL);
    }

    public void setClassName(String newValue) {
        setAttribute("class", newValue);
    }

    public String getClassName() {
        return getAttribute("class");
    }

    public void setXmlLang(String newValue) {
        setAttribute("xml:lang", newValue);
    }

    public String getXmlLang() {
        return getAttribute("xml:lang");
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setLabel(String newValue) {
        setAttribute("label", newValue);
    }

    public String getLabel() {
        return getAttribute("label");
    }

    public void setType(String newValue) {
        setAttribute("type", newValue);
    }

    public String getType() {
        return getAttribute("type");
    }

    public void setName(String newValue) {
        setAttribute("name", newValue);
    }

    public String getName() {
        return getAttribute("name");
    }
}
