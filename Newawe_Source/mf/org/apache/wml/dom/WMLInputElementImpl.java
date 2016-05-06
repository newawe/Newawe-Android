package mf.org.apache.wml.dom;

import com.Newawe.storage.DatabaseOpenHelper;
import mf.org.apache.wml.WMLInputElement;

public class WMLInputElementImpl extends WMLElementImpl implements WMLInputElement {
    private static final long serialVersionUID = 2897319793637966619L;

    public WMLInputElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setSize(int newValue) {
        setAttribute("size", newValue);
    }

    public int getSize() {
        return getAttribute("size", 0);
    }

    public void setFormat(String newValue) {
        setAttribute("format", newValue);
    }

    public String getFormat() {
        return getAttribute("format");
    }

    public void setValue(String newValue) {
        setAttribute("value", newValue);
    }

    public String getValue() {
        return getAttribute("value");
    }

    public void setMaxLength(int newValue) {
        setAttribute("maxlength", newValue);
    }

    public int getMaxLength() {
        return getAttribute("maxlength", 0);
    }

    public void setTabIndex(int newValue) {
        setAttribute("tabindex", newValue);
    }

    public int getTabIndex() {
        return getAttribute("tabindex", 0);
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

    public void setEmptyOk(boolean newValue) {
        setAttribute("emptyok", newValue);
    }

    public boolean getEmptyOk() {
        return getAttribute("emptyok", false);
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
