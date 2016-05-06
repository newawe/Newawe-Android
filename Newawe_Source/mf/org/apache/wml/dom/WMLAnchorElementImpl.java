package mf.org.apache.wml.dom;

import com.Newawe.storage.DatabaseOpenHelper;
import mf.org.apache.wml.WMLAnchorElement;

public class WMLAnchorElementImpl extends WMLElementImpl implements WMLAnchorElement {
    private static final long serialVersionUID = 5720492496046133176L;

    public WMLAnchorElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
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
}
