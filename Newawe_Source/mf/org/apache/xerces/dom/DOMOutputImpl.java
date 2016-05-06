package mf.org.apache.xerces.dom;

import java.io.OutputStream;
import java.io.Writer;
import mf.org.w3c.dom.ls.LSOutput;

public class DOMOutputImpl implements LSOutput {
    protected OutputStream fByteStream;
    protected Writer fCharStream;
    protected String fEncoding;
    protected String fSystemId;

    public DOMOutputImpl() {
        this.fCharStream = null;
        this.fByteStream = null;
        this.fSystemId = null;
        this.fEncoding = null;
    }

    public Writer getCharacterStream() {
        return this.fCharStream;
    }

    public void setCharacterStream(Writer characterStream) {
        this.fCharStream = characterStream;
    }

    public OutputStream getByteStream() {
        return this.fByteStream;
    }

    public void setByteStream(OutputStream byteStream) {
        this.fByteStream = byteStream;
    }

    public String getSystemId() {
        return this.fSystemId;
    }

    public void setSystemId(String systemId) {
        this.fSystemId = systemId;
    }

    public String getEncoding() {
        return this.fEncoding;
    }

    public void setEncoding(String encoding) {
        this.fEncoding = encoding;
    }
}
