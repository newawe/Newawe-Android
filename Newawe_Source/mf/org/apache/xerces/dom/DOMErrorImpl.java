package mf.org.apache.xerces.dom;

import mf.org.apache.xerces.xni.parser.XMLParseException;
import mf.org.w3c.dom.DOMError;
import mf.org.w3c.dom.DOMLocator;

public class DOMErrorImpl implements DOMError {
    public Exception fException;
    public DOMLocatorImpl fLocator;
    public String fMessage;
    public Object fRelatedData;
    public short fSeverity;
    public String fType;

    public DOMErrorImpl() {
        this.fSeverity = (short) 1;
        this.fMessage = null;
        this.fLocator = new DOMLocatorImpl();
        this.fException = null;
    }

    public DOMErrorImpl(short severity, XMLParseException exception) {
        this.fSeverity = (short) 1;
        this.fMessage = null;
        this.fLocator = new DOMLocatorImpl();
        this.fException = null;
        this.fSeverity = severity;
        this.fException = exception;
        this.fLocator = createDOMLocator(exception);
    }

    public short getSeverity() {
        return this.fSeverity;
    }

    public String getMessage() {
        return this.fMessage;
    }

    public DOMLocator getLocation() {
        return this.fLocator;
    }

    private DOMLocatorImpl createDOMLocator(XMLParseException exception) {
        return new DOMLocatorImpl(exception.getLineNumber(), exception.getColumnNumber(), exception.getCharacterOffset(), exception.getExpandedSystemId());
    }

    public Object getRelatedException() {
        return this.fException;
    }

    public void reset() {
        this.fSeverity = (short) 1;
        this.fException = null;
    }

    public String getType() {
        return this.fType;
    }

    public Object getRelatedData() {
        return this.fRelatedData;
    }
}
