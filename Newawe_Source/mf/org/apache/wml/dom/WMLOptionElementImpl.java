package mf.org.apache.wml.dom;

import com.Newawe.storage.DatabaseOpenHelper;
import mf.org.apache.wml.WMLOptionElement;

public class WMLOptionElementImpl extends WMLElementImpl implements WMLOptionElement {
    private static final long serialVersionUID = -3432299264888771937L;

    public WMLOptionElementImpl(WMLDocumentImpl owner, String tagName) {
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

    public void setXmlLang(String newValue) {
        setAttribute("xml:lang", newValue);
    }

    public String getXmlLang() {
        return getAttribute("xml:lang");
    }

    public void setTitle(String newValue) {
        setAttribute(DatabaseOpenHelper.HISTORY_ROW_TITLE, newValue);
    }

    public String getTitle() {
        return getAttribute(DatabaseOpenHelper.HISTORY_ROW_TITLE);
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setOnPick(String newValue) {
        setAttribute("onpick", newValue);
    }

    public String getOnPick() {
        return getAttribute("onpick");
    }
}
