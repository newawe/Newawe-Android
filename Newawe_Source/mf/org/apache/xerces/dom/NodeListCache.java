package mf.org.apache.xerces.dom;

import java.io.Serializable;

class NodeListCache implements Serializable {
    private static final long serialVersionUID = -7927529254918631002L;
    ChildNode fChild;
    int fChildIndex;
    int fLength;
    ParentNode fOwner;
    NodeListCache next;

    NodeListCache(ParentNode owner) {
        this.fLength = -1;
        this.fChildIndex = -1;
        this.fOwner = owner;
    }
}
