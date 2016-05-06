package mf.org.apache.xerces.dom;

import java.io.InputStream;
import java.io.Reader;
import mf.org.w3c.dom.ls.LSInput;

public class DOMInputImpl implements LSInput {
    protected String fBaseSystemId;
    protected InputStream fByteStream;
    protected boolean fCertifiedText;
    protected Reader fCharStream;
    protected String fData;
    protected String fEncoding;
    protected String fPublicId;
    protected String fSystemId;

    public DOMInputImpl() {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fCertifiedText = false;
    }

    public DOMInputImpl(String publicId, String systemId, String baseSystemId) {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fCertifiedText = false;
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
    }

    public DOMInputImpl(String publicId, String systemId, String baseSystemId, InputStream byteStream, String encoding) {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fCertifiedText = false;
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
        this.fByteStream = byteStream;
        this.fEncoding = encoding;
    }

    public DOMInputImpl(String publicId, String systemId, String baseSystemId, Reader charStream, String encoding) {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fCertifiedText = false;
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
        this.fCharStream = charStream;
        this.fEncoding = encoding;
    }

    public DOMInputImpl(String publicId, String systemId, String baseSystemId, String data, String encoding) {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fCertifiedText = false;
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
        this.fData = data;
        this.fEncoding = encoding;
    }

    public InputStream getByteStream() {
        return this.fByteStream;
    }

    public void setByteStream(InputStream byteStream) {
        this.fByteStream = byteStream;
    }

    public Reader getCharacterStream() {
        return this.fCharStream;
    }

    public void setCharacterStream(Reader characterStream) {
        this.fCharStream = characterStream;
    }

    public String getStringData() {
        return this.fData;
    }

    public void setStringData(String stringData) {
        this.fData = stringData;
    }

    public String getEncoding() {
        return this.fEncoding;
    }

    public void setEncoding(String encoding) {
        this.fEncoding = encoding;
    }

    public String getPublicId() {
        return this.fPublicId;
    }

    public void setPublicId(String publicId) {
        this.fPublicId = publicId;
    }

    public String getSystemId() {
        return this.fSystemId;
    }

    public void setSystemId(String systemId) {
        this.fSystemId = systemId;
    }

    public String getBaseURI() {
        return this.fBaseSystemId;
    }

    public void setBaseURI(String baseURI) {
        this.fBaseSystemId = baseURI;
    }

    public boolean getCertifiedText() {
        return this.fCertifiedText;
    }

    public void setCertifiedText(boolean certifiedText) {
        this.fCertifiedText = certifiedText;
    }
}
