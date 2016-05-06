package mf.org.apache.xerces.impl.dtd;

import mf.org.apache.xerces.xni.QName;

public class XMLAttributeDecl {
    public final QName name;
    public boolean optional;
    public final XMLSimpleType simpleType;

    public XMLAttributeDecl() {
        this.name = new QName();
        this.simpleType = new XMLSimpleType();
    }

    public void setValues(QName name, XMLSimpleType simpleType, boolean optional) {
        this.name.setValues(name);
        this.simpleType.setValues(simpleType);
        this.optional = optional;
    }

    public void clear() {
        this.name.clear();
        this.simpleType.clear();
        this.optional = false;
    }
}
