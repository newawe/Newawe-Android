package mf.org.apache.wml.dom;

import com.Newawe.storage.DatabaseOpenHelper;
import mf.org.apache.wml.WMLTableElement;

public class WMLTableElementImpl extends WMLElementImpl implements WMLTableElement {
    private static final long serialVersionUID = 7676208849347355339L;

    public WMLTableElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setColumns(int newValue) {
        setAttribute("columns", newValue);
    }

    public int getColumns() {
        return getAttribute("columns", 0);
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

    public void setAlign(String newValue) {
        setAttribute("align", newValue);
    }

    public String getAlign() {
        return getAttribute("align");
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
