package mf.org.apache.html.dom;

import com.google.android.gms.common.ConnectionResult;
import com.immersion.hapticmediasdk.HapticContentSDK;
import com.startapp.android.publish.model.MetaData;
import java.io.Serializable;
import mf.org.apache.xerces.impl.dtd.DTDGrammar;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.html.HTMLAnchorElement;
import mf.org.w3c.dom.html.HTMLAppletElement;
import mf.org.w3c.dom.html.HTMLAreaElement;
import mf.org.w3c.dom.html.HTMLCollection;
import mf.org.w3c.dom.html.HTMLElement;
import mf.org.w3c.dom.html.HTMLFormElement;
import mf.org.w3c.dom.html.HTMLImageElement;
import mf.org.w3c.dom.html.HTMLObjectElement;
import mf.org.w3c.dom.html.HTMLOptionElement;
import mf.org.w3c.dom.html.HTMLTableCellElement;
import mf.org.w3c.dom.html.HTMLTableRowElement;
import mf.org.w3c.dom.html.HTMLTableSectionElement;
import org.apache.http.entity.ContentLengthStrategy;

class HTMLCollectionImpl implements HTMLCollection, Serializable {
    static final short ANCHOR = (short) 1;
    static final short APPLET = (short) 4;
    static final short AREA = (short) -1;
    static final short CELL = (short) -3;
    static final short ELEMENT = (short) 8;
    static final short FORM = (short) 2;
    static final short IMAGE = (short) 3;
    static final short LINK = (short) 5;
    static final short OPTION = (short) 6;
    static final short ROW = (short) 7;
    static final short TBODY = (short) -2;
    private static final long serialVersionUID = 9112122196669185082L;
    private short _lookingFor;
    private Element _topLevel;

    HTMLCollectionImpl(HTMLElement topLevel, short lookingFor) {
        if (topLevel == null) {
            throw new NullPointerException("HTM011 Argument 'topLevel' is null.");
        }
        this._topLevel = topLevel;
        this._lookingFor = lookingFor;
    }

    public final int getLength() {
        return getLength(this._topLevel);
    }

    public final Node item(int index) {
        if (index >= 0) {
            return item(this._topLevel, new CollectionIndex(index));
        }
        throw new IllegalArgumentException("HTM012 Argument 'index' is negative.");
    }

    public final Node namedItem(String name) {
        if (name != null) {
            return namedItem(this._topLevel, name);
        }
        throw new NullPointerException("HTM013 Argument 'name' is null.");
    }

    private int getLength(Element topLevel) {
        int length;
        synchronized (topLevel) {
            length = 0;
            for (Node node = topLevel.getFirstChild(); node != null; node = node.getNextSibling()) {
                if (node instanceof Element) {
                    if (collectionMatch((Element) node, null)) {
                        length++;
                    } else if (recurse()) {
                        length += getLength((Element) node);
                    }
                }
            }
        }
        return length;
    }

    private Node item(Element topLevel, CollectionIndex index) {
        synchronized (topLevel) {
            for (Node node = topLevel.getFirstChild(); node != null; node = node.getNextSibling()) {
                if (node instanceof Element) {
                    if (collectionMatch((Element) node, null)) {
                        if (index.isZero()) {
                            return node;
                        }
                        index.decrement();
                    } else if (recurse()) {
                        Node result = item((Element) node, index);
                        if (result != null) {
                            return result;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        }
    }

    private Node namedItem(Element topLevel, String name) {
        synchronized (topLevel) {
            Node node = topLevel.getFirstChild();
            while (node != null) {
                if (node instanceof Element) {
                    if (collectionMatch((Element) node, name)) {
                        return node;
                    } else if (recurse()) {
                        Node result = namedItem((Element) node, name);
                        if (result != null) {
                            return result;
                        }
                    }
                }
                node = node.getNextSibling();
            }
            return node;
        }
    }

    protected boolean recurse() {
        return this._lookingFor > (short) 0;
    }

    protected boolean collectionMatch(Element elem, String name) {
        synchronized (elem) {
            boolean match = false;
            switch (this._lookingFor) {
                case HapticContentSDK.PERMISSION_DENIED /*-3*/:
                    match = elem instanceof HTMLTableCellElement;
                    break;
                case ContentLengthStrategy.CHUNKED /*-2*/:
                    if ((elem instanceof HTMLTableSectionElement) && elem.getTagName().equals("TBODY")) {
                        match = true;
                    } else {
                        match = false;
                    }
                    break;
                case DTDGrammar.TOP_LEVEL_SCOPE /*-1*/:
                    match = elem instanceof HTMLAreaElement;
                    break;
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    if (!(elem instanceof HTMLAnchorElement) || elem.getAttribute("name").length() <= 0) {
                        match = false;
                    } else {
                        match = true;
                    }
                    break;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    match = elem instanceof HTMLFormElement;
                    break;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    match = elem instanceof HTMLImageElement;
                    break;
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    if ((elem instanceof HTMLAppletElement) || ((elem instanceof HTMLObjectElement) && ("application/java".equals(elem.getAttribute("codetype")) || elem.getAttribute("classid").startsWith("java:")))) {
                        match = true;
                    } else {
                        match = false;
                    }
                    break;
                case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                    if (((elem instanceof HTMLAnchorElement) || (elem instanceof HTMLAreaElement)) && elem.getAttribute("href").length() > 0) {
                        match = true;
                    } else {
                        match = false;
                    }
                    break;
                case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                    match = elem instanceof HTMLOptionElement;
                    break;
                case ConnectionResult.NETWORK_ERROR /*7*/:
                    match = elem instanceof HTMLTableRowElement;
                    break;
                case ConnectionResult.INTERNAL_ERROR /*8*/:
                    match = elem instanceof HTMLFormControl;
                    break;
            }
            if (match && name != null) {
                if ((elem instanceof HTMLAnchorElement) && name.equals(elem.getAttribute("name"))) {
                    return true;
                }
                match = name.equals(elem.getAttribute("id"));
            }
            return match;
        }
    }
}
