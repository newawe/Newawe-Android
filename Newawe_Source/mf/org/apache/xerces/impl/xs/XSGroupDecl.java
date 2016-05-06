package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSModelGroup;
import mf.org.apache.xerces.xs.XSModelGroupDefinition;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;

public class XSGroupDecl implements XSModelGroupDefinition {
    public XSObjectList fAnnotations;
    public XSModelGroupImpl fModelGroup;
    public String fName;
    private XSNamespaceItem fNamespaceItem;
    public String fTargetNamespace;

    public XSGroupDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fModelGroup = null;
        this.fAnnotations = null;
        this.fNamespaceItem = null;
    }

    public short getType() {
        return (short) 6;
    }

    public String getName() {
        return this.fName;
    }

    public String getNamespace() {
        return this.fTargetNamespace;
    }

    public XSModelGroup getModelGroup() {
        return this.fModelGroup;
    }

    public XSAnnotation getAnnotation() {
        return this.fAnnotations != null ? (XSAnnotation) this.fAnnotations.item(0) : null;
    }

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

    public XSNamespaceItem getNamespaceItem() {
        return this.fNamespaceItem;
    }

    void setNamespaceItem(XSNamespaceItem namespaceItem) {
        this.fNamespaceItem = namespaceItem;
    }
}
