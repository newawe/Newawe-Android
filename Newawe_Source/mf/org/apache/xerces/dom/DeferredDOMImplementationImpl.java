package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.DOMImplementation;

public class DeferredDOMImplementationImpl extends DOMImplementationImpl {
    static final DeferredDOMImplementationImpl singleton;

    static {
        singleton = new DeferredDOMImplementationImpl();
    }

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }
}
