package mf.org.apache.xerces.dom;

import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.util.ArrayList;
import mf.org.w3c.dom.CharacterData;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DocumentFragment;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.ranges.Range;
import mf.org.w3c.dom.ranges.RangeException;

public class RangeImpl implements Range {
    static final int CLONE_CONTENTS = 2;
    static final int DELETE_CONTENTS = 3;
    static final int EXTRACT_CONTENTS = 1;
    private Node fDeleteNode;
    private boolean fDetach;
    private DocumentImpl fDocument;
    private Node fEndContainer;
    private int fEndOffset;
    private Node fInsertNode;
    private boolean fInsertedFromRange;
    private Node fRemoveChild;
    private Node fSplitNode;
    private Node fStartContainer;
    private int fStartOffset;

    public RangeImpl(DocumentImpl document) {
        this.fDetach = false;
        this.fInsertNode = null;
        this.fDeleteNode = null;
        this.fSplitNode = null;
        this.fInsertedFromRange = false;
        this.fRemoveChild = null;
        this.fDocument = document;
        this.fStartContainer = document;
        this.fEndContainer = document;
        this.fStartOffset = 0;
        this.fEndOffset = 0;
        this.fDetach = false;
    }

    public Node getStartContainer() {
        if (!this.fDetach) {
            return this.fStartContainer;
        }
        throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
    }

    public int getStartOffset() {
        if (!this.fDetach) {
            return this.fStartOffset;
        }
        throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
    }

    public Node getEndContainer() {
        if (!this.fDetach) {
            return this.fEndContainer;
        }
        throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
    }

    public int getEndOffset() {
        if (!this.fDetach) {
            return this.fEndOffset;
        }
        throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
    }

    public boolean getCollapsed() {
        if (!this.fDetach) {
            return this.fStartContainer == this.fEndContainer && this.fStartOffset == this.fEndOffset;
        } else {
            throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        }
    }

    public Node getCommonAncestorContainer() {
        if (this.fDetach) {
            throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        }
        Node node;
        ArrayList startV = new ArrayList();
        for (node = this.fStartContainer; node != null; node = node.getParentNode()) {
            startV.add(node);
        }
        ArrayList endV = new ArrayList();
        for (node = this.fEndContainer; node != null; node = node.getParentNode()) {
            endV.add(node);
        }
        int s = startV.size() - 1;
        int e = endV.size() - 1;
        Object obj = null;
        while (s >= 0 && e >= 0 && startV.get(s) == endV.get(e)) {
            obj = startV.get(s);
            s--;
            e--;
        }
        return (Node) obj;
    }

    public void setStart(Node refNode, int offset) throws RangeException, DOMException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            } else if (!isLegalContainer(refNode)) {
                throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
            } else if (!(this.fDocument == refNode.getOwnerDocument() || this.fDocument == refNode)) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        checkIndex(refNode, offset);
        this.fStartContainer = refNode;
        this.fStartOffset = offset;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(true);
        }
    }

    public void setEnd(Node refNode, int offset) throws RangeException, DOMException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            } else if (!isLegalContainer(refNode)) {
                throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
            } else if (!(this.fDocument == refNode.getOwnerDocument() || this.fDocument == refNode)) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        checkIndex(refNode, offset);
        this.fEndContainer = refNode;
        this.fEndOffset = offset;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(false);
        }
    }

    public void setStartBefore(Node refNode) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            } else if (!hasLegalRootContainer(refNode) || !isLegalContainedNode(refNode)) {
                throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
            } else if (!(this.fDocument == refNode.getOwnerDocument() || this.fDocument == refNode)) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        this.fStartContainer = refNode.getParentNode();
        int i = 0;
        for (Node n = refNode; n != null; n = n.getPreviousSibling()) {
            i += EXTRACT_CONTENTS;
        }
        this.fStartOffset = i - 1;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(true);
        }
    }

    public void setStartAfter(Node refNode) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            } else if (!hasLegalRootContainer(refNode) || !isLegalContainedNode(refNode)) {
                throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
            } else if (!(this.fDocument == refNode.getOwnerDocument() || this.fDocument == refNode)) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        this.fStartContainer = refNode.getParentNode();
        int i = 0;
        for (Node n = refNode; n != null; n = n.getPreviousSibling()) {
            i += EXTRACT_CONTENTS;
        }
        this.fStartOffset = i;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(true);
        }
    }

    public void setEndBefore(Node refNode) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            } else if (!hasLegalRootContainer(refNode) || !isLegalContainedNode(refNode)) {
                throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
            } else if (!(this.fDocument == refNode.getOwnerDocument() || this.fDocument == refNode)) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        this.fEndContainer = refNode.getParentNode();
        int i = 0;
        for (Node n = refNode; n != null; n = n.getPreviousSibling()) {
            i += EXTRACT_CONTENTS;
        }
        this.fEndOffset = i - 1;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(false);
        }
    }

    public void setEndAfter(Node refNode) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            } else if (!hasLegalRootContainer(refNode) || !isLegalContainedNode(refNode)) {
                throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
            } else if (!(this.fDocument == refNode.getOwnerDocument() || this.fDocument == refNode)) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        this.fEndContainer = refNode.getParentNode();
        int i = 0;
        for (Node n = refNode; n != null; n = n.getPreviousSibling()) {
            i += EXTRACT_CONTENTS;
        }
        this.fEndOffset = i;
        if (getCommonAncestorContainer() == null || (this.fStartContainer == this.fEndContainer && this.fEndOffset < this.fStartOffset)) {
            collapse(false);
        }
    }

    public void collapse(boolean toStart) {
        if (this.fDetach) {
            throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        } else if (toStart) {
            this.fEndContainer = this.fStartContainer;
            this.fEndOffset = this.fStartOffset;
        } else {
            this.fStartContainer = this.fEndContainer;
            this.fStartOffset = this.fEndOffset;
        }
    }

    public void selectNode(Node refNode) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            } else if (!isLegalContainer(refNode.getParentNode()) || !isLegalContainedNode(refNode)) {
                throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
            } else if (!(this.fDocument == refNode.getOwnerDocument() || this.fDocument == refNode)) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        Node parent = refNode.getParentNode();
        if (parent != null) {
            this.fStartContainer = parent;
            this.fEndContainer = parent;
            int i = 0;
            for (Node n = refNode; n != null; n = n.getPreviousSibling()) {
                i += EXTRACT_CONTENTS;
            }
            this.fStartOffset = i - 1;
            this.fEndOffset = this.fStartOffset + EXTRACT_CONTENTS;
        }
    }

    public void selectNodeContents(Node refNode) throws RangeException {
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            } else if (!isLegalContainer(refNode)) {
                throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
            } else if (!(this.fDocument == refNode.getOwnerDocument() || this.fDocument == refNode)) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        this.fStartContainer = refNode;
        this.fEndContainer = refNode;
        Node first = refNode.getFirstChild();
        this.fStartOffset = 0;
        if (first == null) {
            this.fEndOffset = 0;
            return;
        }
        int i = 0;
        for (Node n = first; n != null; n = n.getNextSibling()) {
            i += EXTRACT_CONTENTS;
        }
        this.fEndOffset = i;
    }

    public short compareBoundaryPoints(short how, Range sourceRange) throws DOMException {
        Node endPointA;
        Node endPointB;
        int offsetA;
        int offsetB;
        if (this.fDocument.errorChecking) {
            if (this.fDetach) {
                throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            } else if (!((this.fDocument == sourceRange.getStartContainer().getOwnerDocument() || this.fDocument == sourceRange.getStartContainer() || sourceRange.getStartContainer() == null) && (this.fDocument == sourceRange.getEndContainer().getOwnerDocument() || this.fDocument == sourceRange.getEndContainer() || sourceRange.getStartContainer() == null))) {
                throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            }
        }
        if (how == (short) 0) {
            endPointA = sourceRange.getStartContainer();
            endPointB = this.fStartContainer;
            offsetA = sourceRange.getStartOffset();
            offsetB = this.fStartOffset;
        } else if (how == EXTRACT_CONTENTS) {
            endPointA = sourceRange.getStartContainer();
            endPointB = this.fEndContainer;
            offsetA = sourceRange.getStartOffset();
            offsetB = this.fEndOffset;
        } else if (how == DELETE_CONTENTS) {
            endPointA = sourceRange.getEndContainer();
            endPointB = this.fStartContainer;
            offsetA = sourceRange.getEndOffset();
            offsetB = this.fStartOffset;
        } else {
            endPointA = sourceRange.getEndContainer();
            endPointB = this.fEndContainer;
            offsetA = sourceRange.getEndOffset();
            offsetB = this.fEndOffset;
        }
        if (endPointA != endPointB) {
            Node n;
            Node c = endPointB;
            Node p = c.getParentNode();
            while (p != null) {
                if (p != endPointA) {
                    c = p;
                    p = p.getParentNode();
                } else if (offsetA <= indexOf(c, endPointA)) {
                    return (short) 1;
                } else {
                    return (short) -1;
                }
            }
            c = endPointA;
            p = c.getParentNode();
            while (p != null) {
                if (p != endPointB) {
                    c = p;
                    p = p.getParentNode();
                } else if (indexOf(c, endPointB) < offsetB) {
                    return (short) 1;
                } else {
                    return (short) -1;
                }
            }
            int depthDiff = 0;
            for (n = endPointA; n != null; n = n.getParentNode()) {
                depthDiff += EXTRACT_CONTENTS;
            }
            for (n = endPointB; n != null; n = n.getParentNode()) {
                depthDiff--;
            }
            while (depthDiff > 0) {
                endPointA = endPointA.getParentNode();
                depthDiff--;
            }
            while (depthDiff < 0) {
                endPointB = endPointB.getParentNode();
                depthDiff += EXTRACT_CONTENTS;
            }
            Node pA = endPointA.getParentNode();
            for (Node pB = endPointB.getParentNode(); pA != pB; pB = pB.getParentNode()) {
                endPointA = pA;
                endPointB = pB;
                pA = pA.getParentNode();
            }
            for (n = endPointA.getNextSibling(); n != null; n = n.getNextSibling()) {
                if (n == endPointB) {
                    return (short) 1;
                }
            }
            return (short) -1;
        } else if (offsetA < offsetB) {
            return (short) 1;
        } else {
            if (offsetA == offsetB) {
                return (short) 0;
            }
            return (short) -1;
        }
    }

    public void deleteContents() throws DOMException {
        traverseContents(DELETE_CONTENTS);
    }

    public DocumentFragment extractContents() throws DOMException {
        return traverseContents(EXTRACT_CONTENTS);
    }

    public DocumentFragment cloneContents() throws DOMException {
        return traverseContents(CLONE_CONTENTS);
    }

    public void insertNode(Node newNode) throws DOMException, RangeException {
        if (newNode != null) {
            int type = newNode.getNodeType();
            if (this.fDocument.errorChecking) {
                if (this.fDetach) {
                    throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                } else if (this.fDocument != newNode.getOwnerDocument()) {
                    throw new DOMException((short) 4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                } else if (type == CLONE_CONTENTS || type == 6 || type == 12 || type == 9) {
                    throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
                }
            }
            int currentChildren = 0;
            this.fInsertedFromRange = true;
            if (this.fStartContainer.getNodeType() == (short) 3) {
                Node parent = this.fStartContainer.getParentNode();
                currentChildren = parent.getChildNodes().getLength();
                Node cloneCurrent = this.fStartContainer.cloneNode(false);
                ((TextImpl) cloneCurrent).setNodeValueInternal(cloneCurrent.getNodeValue().substring(this.fStartOffset));
                ((TextImpl) this.fStartContainer).setNodeValueInternal(this.fStartContainer.getNodeValue().substring(0, this.fStartOffset));
                Node next = this.fStartContainer.getNextSibling();
                if (next != null) {
                    if (parent != null) {
                        parent.insertBefore(newNode, next);
                        parent.insertBefore(cloneCurrent, next);
                    }
                } else if (parent != null) {
                    parent.appendChild(newNode);
                    parent.appendChild(cloneCurrent);
                }
                if (this.fEndContainer == this.fStartContainer) {
                    this.fEndContainer = cloneCurrent;
                    this.fEndOffset -= this.fStartOffset;
                } else if (this.fEndContainer == parent) {
                    this.fEndOffset += parent.getChildNodes().getLength() - currentChildren;
                }
                signalSplitData(this.fStartContainer, cloneCurrent, this.fStartOffset);
            } else {
                if (this.fEndContainer == this.fStartContainer) {
                    currentChildren = this.fEndContainer.getChildNodes().getLength();
                }
                Node current = this.fStartContainer.getFirstChild();
                for (int i = 0; i < this.fStartOffset && current != null; i += EXTRACT_CONTENTS) {
                    current = current.getNextSibling();
                }
                if (current != null) {
                    this.fStartContainer.insertBefore(newNode, current);
                } else {
                    this.fStartContainer.appendChild(newNode);
                }
                if (this.fEndContainer == this.fStartContainer && this.fEndOffset != 0) {
                    this.fEndOffset += this.fEndContainer.getChildNodes().getLength() - currentChildren;
                }
            }
            this.fInsertedFromRange = false;
        }
    }

    public void surroundContents(Node newParent) throws DOMException, RangeException {
        if (newParent != null) {
            int type = newParent.getNodeType();
            if (this.fDocument.errorChecking) {
                if (this.fDetach) {
                    throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
                } else if (type == CLONE_CONTENTS || type == 6 || type == 12 || type == 10 || type == 9 || type == 11) {
                    throw new RangeExceptionImpl((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_NODE_TYPE_ERR", null));
                }
            }
            Node realStart = this.fStartContainer;
            Node realEnd = this.fEndContainer;
            if (this.fStartContainer.getNodeType() == (short) 3) {
                realStart = this.fStartContainer.getParentNode();
            }
            if (this.fEndContainer.getNodeType() == (short) 3) {
                realEnd = this.fEndContainer.getParentNode();
            }
            if (realStart != realEnd) {
                throw new RangeExceptionImpl((short) 1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "BAD_BOUNDARYPOINTS_ERR", null));
            }
            DocumentFragment frag = extractContents();
            insertNode(newParent);
            newParent.appendChild(frag);
            selectNode(newParent);
        }
    }

    public Range cloneRange() {
        if (this.fDetach) {
            throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        }
        Range range = this.fDocument.createRange();
        range.setStart(this.fStartContainer, this.fStartOffset);
        range.setEnd(this.fEndContainer, this.fEndOffset);
        return range;
    }

    public String toString() {
        if (this.fDetach) {
            throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        }
        Node node = this.fStartContainer;
        Node stopNode = this.fEndContainer;
        StringBuffer sb = new StringBuffer();
        if (this.fStartContainer.getNodeType() != (short) 3 && this.fStartContainer.getNodeType() != (short) 4) {
            node = node.getFirstChild();
            if (this.fStartOffset > 0) {
                for (int counter = 0; counter < this.fStartOffset && node != null; counter += EXTRACT_CONTENTS) {
                    node = node.getNextSibling();
                }
            }
            if (node == null) {
                node = nextNode(this.fStartContainer, false);
            }
        } else if (this.fStartContainer == this.fEndContainer) {
            sb.append(this.fStartContainer.getNodeValue().substring(this.fStartOffset, this.fEndOffset));
            return sb.toString();
        } else {
            sb.append(this.fStartContainer.getNodeValue().substring(this.fStartOffset));
            node = nextNode(node, true);
        }
        if (!(this.fEndContainer.getNodeType() == (short) 3 || this.fEndContainer.getNodeType() == (short) 4)) {
            int i = this.fEndOffset;
            stopNode = this.fEndContainer.getFirstChild();
            while (i > 0 && stopNode != null) {
                i--;
                stopNode = stopNode.getNextSibling();
            }
            if (stopNode == null) {
                stopNode = nextNode(this.fEndContainer, false);
            }
        }
        while (node != stopNode && node != null) {
            if (node.getNodeType() == (short) 3 || node.getNodeType() == (short) 4) {
                sb.append(node.getNodeValue());
            }
            node = nextNode(node, true);
        }
        if (this.fEndContainer.getNodeType() == (short) 3 || this.fEndContainer.getNodeType() == (short) 4) {
            sb.append(this.fEndContainer.getNodeValue().substring(0, this.fEndOffset));
        }
        return sb.toString();
    }

    public void detach() {
        if (this.fDetach) {
            throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        }
        this.fDetach = true;
        this.fDocument.removeRange(this);
    }

    void signalSplitData(Node node, Node newNode, int offset) {
        this.fSplitNode = node;
        this.fDocument.splitData(node, newNode, offset);
        this.fSplitNode = null;
    }

    void receiveSplitData(Node node, Node newNode, int offset) {
        if (node != null && newNode != null && this.fSplitNode != node) {
            if (node == this.fStartContainer && this.fStartContainer.getNodeType() == (short) 3 && this.fStartOffset > offset) {
                this.fStartOffset -= offset;
                this.fStartContainer = newNode;
            }
            if (node == this.fEndContainer && this.fEndContainer.getNodeType() == (short) 3 && this.fEndOffset > offset) {
                this.fEndOffset -= offset;
                this.fEndContainer = newNode;
            }
        }
    }

    void deleteData(CharacterData node, int offset, int count) {
        this.fDeleteNode = node;
        node.deleteData(offset, count);
        this.fDeleteNode = null;
    }

    void receiveDeletedText(CharacterDataImpl node, int offset, int count) {
        if (node != null && this.fDeleteNode != node) {
            if (node == this.fStartContainer) {
                if (this.fStartOffset > offset + count) {
                    this.fStartOffset = (this.fStartOffset - (offset + count)) + offset;
                } else if (this.fStartOffset > offset) {
                    this.fStartOffset = offset;
                }
            }
            if (node != this.fEndContainer) {
                return;
            }
            if (this.fEndOffset > offset + count) {
                this.fEndOffset = (this.fEndOffset - (offset + count)) + offset;
            } else if (this.fEndOffset > offset) {
                this.fEndOffset = offset;
            }
        }
    }

    void insertData(CharacterData node, int index, String insert) {
        this.fInsertNode = node;
        node.insertData(index, insert);
        this.fInsertNode = null;
    }

    void receiveInsertedText(CharacterDataImpl node, int index, int len) {
        if (node != null && this.fInsertNode != node) {
            if (node == this.fStartContainer && index < this.fStartOffset) {
                this.fStartOffset += len;
            }
            if (node == this.fEndContainer && index < this.fEndOffset) {
                this.fEndOffset += len;
            }
        }
    }

    void receiveReplacedText(CharacterDataImpl node) {
        if (node != null) {
            if (node == this.fStartContainer) {
                this.fStartOffset = 0;
            }
            if (node == this.fEndContainer) {
                this.fEndOffset = 0;
            }
        }
    }

    public void insertedNodeFromDOM(Node node) {
        if (node != null && this.fInsertNode != node && !this.fInsertedFromRange) {
            Node parent = node.getParentNode();
            if (parent == this.fStartContainer && indexOf(node, this.fStartContainer) < this.fStartOffset) {
                this.fStartOffset += EXTRACT_CONTENTS;
            }
            if (parent == this.fEndContainer && indexOf(node, this.fEndContainer) < this.fEndOffset) {
                this.fEndOffset += EXTRACT_CONTENTS;
            }
        }
    }

    Node removeChild(Node parent, Node child) {
        this.fRemoveChild = child;
        Node n = parent.removeChild(child);
        this.fRemoveChild = null;
        return n;
    }

    void removeNode(Node node) {
        if (node != null && this.fRemoveChild != node) {
            Node parent = node.getParentNode();
            if (parent == this.fStartContainer && indexOf(node, this.fStartContainer) < this.fStartOffset) {
                this.fStartOffset--;
            }
            if (parent == this.fEndContainer && indexOf(node, this.fEndContainer) < this.fEndOffset) {
                this.fEndOffset--;
            }
            if (parent != this.fStartContainer || parent != this.fEndContainer) {
                if (isAncestorOf(node, this.fStartContainer)) {
                    this.fStartContainer = parent;
                    this.fStartOffset = indexOf(node, parent);
                }
                if (isAncestorOf(node, this.fEndContainer)) {
                    this.fEndContainer = parent;
                    this.fEndOffset = indexOf(node, parent);
                }
            }
        }
    }

    private DocumentFragment traverseContents(int how) throws DOMException {
        if (this.fStartContainer == null || this.fEndContainer == null) {
            return null;
        }
        if (this.fDetach) {
            throw new DOMException((short) 11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
        } else if (this.fStartContainer == this.fEndContainer) {
            return traverseSameContainer(how);
        } else {
            Node p;
            int endContainerDepth = 0;
            Node c = this.fEndContainer;
            for (p = c.getParentNode(); p != null; p = p.getParentNode()) {
                if (p == this.fStartContainer) {
                    return traverseCommonStartContainer(c, how);
                }
                endContainerDepth += EXTRACT_CONTENTS;
                c = p;
            }
            int startContainerDepth = 0;
            c = this.fStartContainer;
            for (p = c.getParentNode(); p != null; p = p.getParentNode()) {
                if (p == this.fEndContainer) {
                    return traverseCommonEndContainer(c, how);
                }
                startContainerDepth += EXTRACT_CONTENTS;
                c = p;
            }
            int depthDiff = startContainerDepth - endContainerDepth;
            Node startNode = this.fStartContainer;
            while (depthDiff > 0) {
                startNode = startNode.getParentNode();
                depthDiff--;
            }
            Node endNode = this.fEndContainer;
            while (depthDiff < 0) {
                endNode = endNode.getParentNode();
                depthDiff += EXTRACT_CONTENTS;
            }
            Node sp = startNode.getParentNode();
            for (Node ep = endNode.getParentNode(); sp != ep; ep = ep.getParentNode()) {
                startNode = sp;
                endNode = ep;
                sp = sp.getParentNode();
            }
            return traverseCommonAncestors(startNode, endNode, how);
        }
    }

    private DocumentFragment traverseSameContainer(int how) {
        DocumentFragment frag = null;
        if (how != DELETE_CONTENTS) {
            frag = this.fDocument.createDocumentFragment();
        }
        if (this.fStartOffset == this.fEndOffset) {
            return frag;
        }
        short nodeType = this.fStartContainer.getNodeType();
        if (nodeType == (short) 3 || nodeType == (short) 4 || nodeType == (short) 8 || nodeType == (short) 7) {
            String sub = this.fStartContainer.getNodeValue().substring(this.fStartOffset, this.fEndOffset);
            if (how != CLONE_CONTENTS) {
                ((CharacterDataImpl) this.fStartContainer).deleteData(this.fStartOffset, this.fEndOffset - this.fStartOffset);
                collapse(true);
            }
            if (how == DELETE_CONTENTS) {
                return null;
            }
            if (nodeType == (short) 3) {
                frag.appendChild(this.fDocument.createTextNode(sub));
                return frag;
            } else if (nodeType == (short) 4) {
                frag.appendChild(this.fDocument.createCDATASection(sub));
                return frag;
            } else if (nodeType == (short) 8) {
                frag.appendChild(this.fDocument.createComment(sub));
                return frag;
            } else {
                frag.appendChild(this.fDocument.createProcessingInstruction(this.fStartContainer.getNodeName(), sub));
                return frag;
            }
        }
        Node n = getSelectedNode(this.fStartContainer, this.fStartOffset);
        int cnt = this.fEndOffset - this.fStartOffset;
        while (cnt > 0) {
            Node sibling = n.getNextSibling();
            Node xferNode = traverseFullySelected(n, how);
            if (frag != null) {
                frag.appendChild(xferNode);
            }
            cnt--;
            n = sibling;
        }
        if (how == CLONE_CONTENTS) {
            return frag;
        }
        collapse(true);
        return frag;
    }

    private DocumentFragment traverseCommonStartContainer(Node endAncestor, int how) {
        DocumentFragment frag = null;
        if (how != DELETE_CONTENTS) {
            frag = this.fDocument.createDocumentFragment();
        }
        Node n = traverseRightBoundary(endAncestor, how);
        if (frag != null) {
            frag.appendChild(n);
        }
        int cnt = indexOf(endAncestor, this.fStartContainer) - this.fStartOffset;
        if (cnt > 0) {
            n = endAncestor.getPreviousSibling();
            while (cnt > 0) {
                Node sibling = n.getPreviousSibling();
                Node xferNode = traverseFullySelected(n, how);
                if (frag != null) {
                    frag.insertBefore(xferNode, frag.getFirstChild());
                }
                cnt--;
                n = sibling;
            }
            if (how != CLONE_CONTENTS) {
                setEndBefore(endAncestor);
                collapse(false);
            }
        } else if (how != CLONE_CONTENTS) {
            setEndBefore(endAncestor);
            collapse(false);
        }
        return frag;
    }

    private DocumentFragment traverseCommonEndContainer(Node startAncestor, int how) {
        DocumentFragment frag = null;
        if (how != DELETE_CONTENTS) {
            frag = this.fDocument.createDocumentFragment();
        }
        Node n = traverseLeftBoundary(startAncestor, how);
        if (frag != null) {
            frag.appendChild(n);
        }
        int cnt = this.fEndOffset - (indexOf(startAncestor, this.fEndContainer) + EXTRACT_CONTENTS);
        n = startAncestor.getNextSibling();
        while (cnt > 0) {
            Node sibling = n.getNextSibling();
            Node xferNode = traverseFullySelected(n, how);
            if (frag != null) {
                frag.appendChild(xferNode);
            }
            cnt--;
            n = sibling;
        }
        if (how != CLONE_CONTENTS) {
            setStartAfter(startAncestor);
            collapse(true);
        }
        return frag;
    }

    private DocumentFragment traverseCommonAncestors(Node startAncestor, Node endAncestor, int how) {
        DocumentFragment frag = null;
        if (how != DELETE_CONTENTS) {
            frag = this.fDocument.createDocumentFragment();
        }
        Node n = traverseLeftBoundary(startAncestor, how);
        if (frag != null) {
            frag.appendChild(n);
        }
        Node commonParent = startAncestor.getParentNode();
        Node sibling = startAncestor.getNextSibling();
        for (int cnt = indexOf(endAncestor, commonParent) - (indexOf(startAncestor, commonParent) + EXTRACT_CONTENTS); cnt > 0; cnt--) {
            Node nextSibling = sibling.getNextSibling();
            n = traverseFullySelected(sibling, how);
            if (frag != null) {
                frag.appendChild(n);
            }
            sibling = nextSibling;
        }
        n = traverseRightBoundary(endAncestor, how);
        if (frag != null) {
            frag.appendChild(n);
        }
        if (how != CLONE_CONTENTS) {
            setStartAfter(startAncestor);
            collapse(true);
        }
        return frag;
    }

    private Node traverseRightBoundary(Node root, int how) {
        boolean isFullySelected;
        Node next = getSelectedNode(this.fEndContainer, this.fEndOffset - 1);
        if (next != this.fEndContainer) {
            isFullySelected = true;
        } else {
            isFullySelected = false;
        }
        if (next == root) {
            return traverseNode(next, isFullySelected, false, how);
        }
        Node parent = next.getParentNode();
        Node clonedParent = traverseNode(parent, false, false, how);
        while (parent != null) {
            while (next != null) {
                Node prevSibling = next.getPreviousSibling();
                Node clonedChild = traverseNode(next, isFullySelected, false, how);
                if (how != DELETE_CONTENTS) {
                    clonedParent.insertBefore(clonedChild, clonedParent.getFirstChild());
                }
                isFullySelected = true;
                next = prevSibling;
            }
            if (parent == root) {
                return clonedParent;
            }
            next = parent.getPreviousSibling();
            parent = parent.getParentNode();
            Node clonedGrandParent = traverseNode(parent, false, false, how);
            if (how != DELETE_CONTENTS) {
                clonedGrandParent.appendChild(clonedParent);
            }
            clonedParent = clonedGrandParent;
        }
        return null;
    }

    private Node traverseLeftBoundary(Node root, int how) {
        boolean isFullySelected;
        Node next = getSelectedNode(getStartContainer(), getStartOffset());
        if (next != getStartContainer()) {
            isFullySelected = true;
        } else {
            isFullySelected = false;
        }
        if (next == root) {
            return traverseNode(next, isFullySelected, true, how);
        }
        Node parent = next.getParentNode();
        Node clonedParent = traverseNode(parent, false, true, how);
        while (parent != null) {
            while (next != null) {
                Node nextSibling = next.getNextSibling();
                Node clonedChild = traverseNode(next, isFullySelected, true, how);
                if (how != DELETE_CONTENTS) {
                    clonedParent.appendChild(clonedChild);
                }
                isFullySelected = true;
                next = nextSibling;
            }
            if (parent == root) {
                return clonedParent;
            }
            next = parent.getNextSibling();
            parent = parent.getParentNode();
            Node clonedGrandParent = traverseNode(parent, false, true, how);
            if (how != DELETE_CONTENTS) {
                clonedGrandParent.appendChild(clonedParent);
            }
            clonedParent = clonedGrandParent;
        }
        return null;
    }

    private Node traverseNode(Node n, boolean isFullySelected, boolean isLeft, int how) {
        if (isFullySelected) {
            return traverseFullySelected(n, how);
        }
        short nodeType = n.getNodeType();
        if (nodeType == (short) 3 || nodeType == (short) 4 || nodeType == (short) 8 || nodeType == (short) 7) {
            return traverseCharacterDataNode(n, isLeft, how);
        }
        return traversePartiallySelected(n, how);
    }

    private Node traverseFullySelected(Node n, int how) {
        switch (how) {
            case EXTRACT_CONTENTS /*1*/:
                if (n.getNodeType() != (short) 10) {
                    return n;
                }
                throw new DOMException((short) 3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
            case CLONE_CONTENTS /*2*/:
                return n.cloneNode(true);
            case DELETE_CONTENTS /*3*/:
                n.getParentNode().removeChild(n);
                return null;
            default:
                return null;
        }
    }

    private Node traversePartiallySelected(Node n, int how) {
        switch (how) {
            case EXTRACT_CONTENTS /*1*/:
            case CLONE_CONTENTS /*2*/:
                return n.cloneNode(false);
            default:
                return null;
        }
    }

    private Node traverseCharacterDataNode(Node n, boolean isLeft, int how) {
        String newNodeValue;
        String oldNodeValue;
        String txtValue = n.getNodeValue();
        int offset;
        if (isLeft) {
            offset = getStartOffset();
            newNodeValue = txtValue.substring(offset);
            oldNodeValue = txtValue.substring(0, offset);
        } else {
            offset = getEndOffset();
            newNodeValue = txtValue.substring(0, offset);
            oldNodeValue = txtValue.substring(offset);
        }
        if (how != CLONE_CONTENTS) {
            n.setNodeValue(oldNodeValue);
        }
        if (how == DELETE_CONTENTS) {
            return null;
        }
        Node newNode = n.cloneNode(false);
        newNode.setNodeValue(newNodeValue);
        return newNode;
    }

    void checkIndex(Node refNode, int offset) throws DOMException {
        if (offset < 0) {
            throw new DOMException((short) 1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
        }
        int type = refNode.getNodeType();
        if (type == DELETE_CONTENTS || type == 4 || type == 8 || type == 7) {
            if (offset > refNode.getNodeValue().length()) {
                throw new DOMException((short) 1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
            }
        } else if (offset > refNode.getChildNodes().getLength()) {
            throw new DOMException((short) 1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INDEX_SIZE_ERR", null));
        }
    }

    private Node getRootContainer(Node node) {
        if (node == null) {
            return null;
        }
        while (node.getParentNode() != null) {
            node = node.getParentNode();
        }
        return node;
    }

    private boolean isLegalContainer(Node node) {
        if (node == null) {
            return false;
        }
        while (node != null) {
            switch (node.getNodeType()) {
                case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                case MetaData.DEFAULT_MAX_ADS /*10*/:
                case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                    return false;
                default:
                    node = node.getParentNode();
            }
        }
        return true;
    }

    private boolean hasLegalRootContainer(Node node) {
        if (node == null) {
            return false;
        }
        switch (getRootContainer(node).getNodeType()) {
            case CLONE_CONTENTS /*2*/:
            case ConnectionResult.SERVICE_INVALID /*9*/:
            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                return true;
            default:
                return false;
        }
    }

    private boolean isLegalContainedNode(Node node) {
        if (node == null) {
            return false;
        }
        switch (node.getNodeType()) {
            case CLONE_CONTENTS /*2*/:
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
            case ConnectionResult.SERVICE_INVALID /*9*/:
            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
            case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                return false;
            default:
                return true;
        }
    }

    Node nextNode(Node node, boolean visitChildren) {
        if (node == null) {
            return null;
        }
        Node result;
        if (visitChildren) {
            result = node.getFirstChild();
            if (result != null) {
                return result;
            }
        }
        result = node.getNextSibling();
        if (result != null) {
            return result;
        }
        Node parent = node.getParentNode();
        while (parent != null && parent != this.fDocument) {
            result = parent.getNextSibling();
            if (result != null) {
                return result;
            }
            parent = parent.getParentNode();
        }
        return null;
    }

    boolean isAncestorOf(Node a, Node b) {
        for (Node node = b; node != null; node = node.getParentNode()) {
            if (node == a) {
                return true;
            }
        }
        return false;
    }

    int indexOf(Node child, Node parent) {
        if (child.getParentNode() != parent) {
            return -1;
        }
        int i = 0;
        for (Node node = parent.getFirstChild(); node != child; node = node.getNextSibling()) {
            i += EXTRACT_CONTENTS;
        }
        return i;
    }

    private Node getSelectedNode(Node container, int offset) {
        if (container.getNodeType() == (short) 3 || offset < 0) {
            return container;
        }
        Node child = container.getFirstChild();
        while (child != null && offset > 0) {
            offset--;
            child = child.getNextSibling();
        }
        if (child != null) {
            return child;
        }
        return container;
    }
}
