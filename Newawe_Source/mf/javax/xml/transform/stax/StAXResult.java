package mf.javax.xml.transform.stax;

import mf.javax.xml.stream.XMLEventWriter;
import mf.javax.xml.stream.XMLStreamWriter;
import mf.javax.xml.transform.Result;

public class StAXResult implements Result {
    public static final String FEATURE = "http://javax.xml.transform.stax.StAXResult/feature";
    private String systemId;
    private XMLEventWriter xmlEventWriter;
    private XMLStreamWriter xmlStreamWriter;

    public StAXResult(XMLEventWriter xmlEventWriter) {
        this.xmlEventWriter = null;
        this.xmlStreamWriter = null;
        this.systemId = null;
        if (xmlEventWriter == null) {
            throw new IllegalArgumentException("StAXResult(XMLEventWriter) with XMLEventWriter == null");
        }
        this.xmlEventWriter = xmlEventWriter;
    }

    public StAXResult(XMLStreamWriter xmlStreamWriter) {
        this.xmlEventWriter = null;
        this.xmlStreamWriter = null;
        this.systemId = null;
        if (xmlStreamWriter == null) {
            throw new IllegalArgumentException("StAXResult(XMLStreamWriter) with XMLStreamWriter == null");
        }
        this.xmlStreamWriter = xmlStreamWriter;
    }

    public XMLEventWriter getXMLEventWriter() {
        return this.xmlEventWriter;
    }

    public XMLStreamWriter getXMLStreamWriter() {
        return this.xmlStreamWriter;
    }

    public void setSystemId(String systemId) {
        throw new UnsupportedOperationException("StAXResult#setSystemId(systemId) cannot set the system identifier for a StAXResult");
    }

    public String getSystemId() {
        return null;
    }
}
