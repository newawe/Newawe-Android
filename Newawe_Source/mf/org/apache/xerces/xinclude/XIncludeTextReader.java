package mf.org.apache.xerces.xinclude;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.io.ASCIIReader;
import mf.org.apache.xerces.impl.io.Latin1Reader;
import mf.org.apache.xerces.impl.io.UTF16Reader;
import mf.org.apache.xerces.impl.io.UTF8Reader;
import mf.org.apache.xerces.impl.msg.XMLMessageFormatter;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.commons.lang.CharEncoding;
import org.apache.http.protocol.HTTP;

public class XIncludeTextReader {
    private XMLErrorReporter fErrorReporter;
    private final XIncludeHandler fHandler;
    private Reader fReader;
    private XMLInputSource fSource;
    private XMLString fTempString;

    public XIncludeTextReader(XMLInputSource source, XIncludeHandler handler, int bufferSize) throws IOException {
        this.fTempString = new XMLString();
        this.fHandler = handler;
        this.fSource = source;
        this.fTempString = new XMLString(new char[(bufferSize + 1)], 0, 0);
    }

    public void setErrorReporter(XMLErrorReporter errorReporter) {
        this.fErrorReporter = errorReporter;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.io.Reader getReader(mf.org.apache.xerces.xni.parser.XMLInputSource r27) throws java.io.IOException {
        /*
        r26 = this;
        r22 = r27.getCharacterStream();
        if (r22 == 0) goto L_0x000b;
    L_0x0006:
        r22 = r27.getCharacterStream();
    L_0x000a:
        return r22;
    L_0x000b:
        r17 = 0;
        r8 = r27.getEncoding();
        if (r8 != 0) goto L_0x0015;
    L_0x0013:
        r8 = "UTF-8";
    L_0x0015:
        r22 = r27.getByteStream();
        if (r22 == 0) goto L_0x0068;
    L_0x001b:
        r17 = r27.getByteStream();
        r0 = r17;
        r0 = r0 instanceof java.io.BufferedInputStream;
        r22 = r0;
        if (r22 != 0) goto L_0x0045;
    L_0x0027:
        r18 = new java.io.BufferedInputStream;
        r0 = r26;
        r0 = r0.fTempString;
        r22 = r0;
        r0 = r22;
        r0 = r0.ch;
        r22 = r0;
        r0 = r22;
        r0 = r0.length;
        r22 = r0;
        r0 = r18;
        r1 = r17;
        r2 = r22;
        r0.<init>(r1, r2);
        r17 = r18;
    L_0x0045:
        r22 = java.util.Locale.ENGLISH;
        r0 = r22;
        r8 = r8.toUpperCase(r0);
        r0 = r26;
        r1 = r17;
        r8 = r0.consumeBOM(r1, r8);
        r22 = "UTF-8";
        r0 = r22;
        r22 = r8.equals(r0);
        if (r22 == 0) goto L_0x01be;
    L_0x005f:
        r0 = r26;
        r1 = r17;
        r22 = r0.createUTF8Reader(r1);
        goto L_0x000a;
    L_0x0068:
        r22 = r27.getSystemId();
        r23 = r27.getBaseSystemId();
        r24 = 0;
        r10 = mf.org.apache.xerces.impl.XMLEntityManager.expandSystemId(r22, r23, r24);
        r19 = new java.net.URL;
        r0 = r19;
        r0.<init>(r10);
        r20 = r19.openConnection();
        r0 = r20;
        r0 = r0 instanceof java.net.HttpURLConnection;
        r22 = r0;
        if (r22 == 0) goto L_0x00ae;
    L_0x0089:
        r0 = r27;
        r0 = r0 instanceof mf.org.apache.xerces.util.HTTPInputSource;
        r22 = r0;
        if (r22 == 0) goto L_0x00ae;
    L_0x0091:
        r21 = r20;
        r21 = (java.net.HttpURLConnection) r21;
        r12 = r27;
        r12 = (mf.org.apache.xerces.util.HTTPInputSource) r12;
        r15 = r12.getHTTPRequestProperties();
    L_0x009d:
        r22 = r15.hasNext();
        if (r22 != 0) goto L_0x0170;
    L_0x00a3:
        r11 = r12.getFollowHTTPRedirects();
        if (r11 != 0) goto L_0x00ae;
    L_0x00a9:
        r0 = r21;
        r0.setInstanceFollowRedirects(r11);
    L_0x00ae:
        r17 = new java.io.BufferedInputStream;
        r22 = r20.getInputStream();
        r0 = r17;
        r1 = r22;
        r0.<init>(r1);
        r16 = r20.getContentType();
        if (r16 == 0) goto L_0x0187;
    L_0x00c1:
        r22 = 59;
        r0 = r16;
        r1 = r22;
        r13 = r0.indexOf(r1);
    L_0x00cb:
        r6 = 0;
        r5 = 0;
        r22 = -1;
        r0 = r22;
        if (r13 == r0) goto L_0x018c;
    L_0x00d3:
        r22 = 0;
        r0 = r16;
        r1 = r22;
        r22 = r0.substring(r1, r13);
        r6 = r22.trim();
        r22 = r13 + 1;
        r0 = r16;
        r1 = r22;
        r22 = r0.substring(r1);
        r5 = r22.trim();
        r22 = "charset=";
        r0 = r22;
        r22 = r5.startsWith(r0);
        if (r22 == 0) goto L_0x018a;
    L_0x00f9:
        r22 = 8;
        r0 = r22;
        r22 = r5.substring(r0);
        r5 = r22.trim();
        r22 = 0;
        r0 = r22;
        r22 = r5.charAt(r0);
        r23 = 34;
        r0 = r22;
        r1 = r23;
        if (r0 != r1) goto L_0x0129;
    L_0x0115:
        r22 = r5.length();
        r22 = r22 + -1;
        r0 = r22;
        r22 = r5.charAt(r0);
        r23 = 34;
        r0 = r22;
        r1 = r23;
        if (r0 == r1) goto L_0x014d;
    L_0x0129:
        r22 = 0;
        r0 = r22;
        r22 = r5.charAt(r0);
        r23 = 39;
        r0 = r22;
        r1 = r23;
        if (r0 != r1) goto L_0x015d;
    L_0x0139:
        r22 = r5.length();
        r22 = r22 + -1;
        r0 = r22;
        r22 = r5.charAt(r0);
        r23 = 39;
        r0 = r22;
        r1 = r23;
        if (r0 != r1) goto L_0x015d;
    L_0x014d:
        r22 = 1;
        r23 = r5.length();
        r23 = r23 + -1;
        r0 = r22;
        r1 = r23;
        r5 = r5.substring(r0, r1);
    L_0x015d:
        r7 = 0;
        r22 = "text/xml";
        r0 = r22;
        r22 = r6.equals(r0);
        if (r22 == 0) goto L_0x0194;
    L_0x0168:
        if (r5 == 0) goto L_0x0191;
    L_0x016a:
        r7 = r5;
    L_0x016b:
        if (r7 == 0) goto L_0x0045;
    L_0x016d:
        r8 = r7;
        goto L_0x0045;
    L_0x0170:
        r9 = r15.next();
        r9 = (java.util.Map.Entry) r9;
        r22 = r9.getKey();
        r22 = (java.lang.String) r22;
        r23 = r9.getValue();
        r23 = (java.lang.String) r23;
        r21.setRequestProperty(r22, r23);
        goto L_0x009d;
    L_0x0187:
        r13 = -1;
        goto L_0x00cb;
    L_0x018a:
        r5 = 0;
        goto L_0x015d;
    L_0x018c:
        r6 = r16.trim();
        goto L_0x015d;
    L_0x0191:
        r7 = "US-ASCII";
        goto L_0x016b;
    L_0x0194:
        r22 = "application/xml";
        r0 = r22;
        r22 = r6.equals(r0);
        if (r22 == 0) goto L_0x01ab;
    L_0x019e:
        if (r5 == 0) goto L_0x01a2;
    L_0x01a0:
        r7 = r5;
        goto L_0x016b;
    L_0x01a2:
        r0 = r26;
        r1 = r17;
        r7 = r0.getEncodingName(r1);
        goto L_0x016b;
    L_0x01ab:
        r22 = "+xml";
        r0 = r22;
        r22 = r6.endsWith(r0);
        if (r22 == 0) goto L_0x016b;
    L_0x01b5:
        r0 = r26;
        r1 = r17;
        r7 = r0.getEncodingName(r1);
        goto L_0x016b;
    L_0x01be:
        r22 = "UTF-16BE";
        r0 = r22;
        r22 = r8.equals(r0);
        if (r22 == 0) goto L_0x01d6;
    L_0x01c8:
        r22 = 1;
        r0 = r26;
        r1 = r17;
        r2 = r22;
        r22 = r0.createUTF16Reader(r1, r2);
        goto L_0x000a;
    L_0x01d6:
        r22 = "UTF-16LE";
        r0 = r22;
        r22 = r8.equals(r0);
        if (r22 == 0) goto L_0x01ee;
    L_0x01e0:
        r22 = 0;
        r0 = r26;
        r1 = r17;
        r2 = r22;
        r22 = r0.createUTF16Reader(r1, r2);
        goto L_0x000a;
    L_0x01ee:
        r14 = mf.org.apache.xerces.util.EncodingMap.getIANA2JavaMapping(r8);
        if (r14 != 0) goto L_0x0226;
    L_0x01f4:
        r0 = r26;
        r0 = r0.fErrorReporter;
        r22 = r0;
        r23 = "http://www.w3.org/TR/1998/REC-xml-19980210";
        r3 = r22.getMessageFormatter(r23);
        r0 = r26;
        r0 = r0.fErrorReporter;
        r22 = r0;
        r4 = r22.getLocale();
        r22 = new java.io.IOException;
        r23 = "EncodingDeclInvalid";
        r24 = 1;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r8;
        r0 = r23;
        r1 = r24;
        r23 = r3.formatMessage(r4, r0, r1);
        r22.<init>(r23);
        throw r22;
    L_0x0226:
        r22 = "ASCII";
        r0 = r22;
        r22 = r14.equals(r0);
        if (r22 == 0) goto L_0x023a;
    L_0x0230:
        r0 = r26;
        r1 = r17;
        r22 = r0.createASCIIReader(r1);
        goto L_0x000a;
    L_0x023a:
        r22 = "ISO8859_1";
        r0 = r22;
        r22 = r14.equals(r0);
        if (r22 == 0) goto L_0x024e;
    L_0x0244:
        r0 = r26;
        r1 = r17;
        r22 = r0.createLatin1Reader(r1);
        goto L_0x000a;
    L_0x024e:
        r22 = new java.io.InputStreamReader;
        r0 = r22;
        r1 = r17;
        r0.<init>(r1, r14);
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.xinclude.XIncludeTextReader.getReader(mf.org.apache.xerces.xni.parser.XMLInputSource):java.io.Reader");
    }

    private Reader createUTF8Reader(InputStream stream) {
        return new UTF8Reader(stream, this.fTempString.ch.length, this.fErrorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN), this.fErrorReporter.getLocale());
    }

    private Reader createUTF16Reader(InputStream stream, boolean isBigEndian) {
        return new UTF16Reader(stream, this.fTempString.ch.length << 1, isBigEndian, this.fErrorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN), this.fErrorReporter.getLocale());
    }

    private Reader createASCIIReader(InputStream stream) {
        return new ASCIIReader(stream, this.fTempString.ch.length, this.fErrorReporter.getMessageFormatter(XMLMessageFormatter.XML_DOMAIN), this.fErrorReporter.getLocale());
    }

    private Reader createLatin1Reader(InputStream stream) {
        return new Latin1Reader(stream, this.fTempString.ch.length);
    }

    protected String getEncodingName(InputStream stream) throws IOException {
        byte[] b4 = new byte[4];
        stream.mark(4);
        int count = stream.read(b4, 0, 4);
        stream.reset();
        if (count == 4) {
            return getEncodingName(b4);
        }
        return null;
    }

    protected String consumeBOM(InputStream stream, String encoding) throws IOException {
        byte[] b = new byte[3];
        stream.mark(3);
        int b1;
        if (encoding.equals(HTTP.UTF_8)) {
            if (stream.read(b, 0, 3) == 3) {
                b1 = b[1] & MotionEventCompat.ACTION_MASK;
                int b2 = b[2] & MotionEventCompat.ACTION_MASK;
                if ((b[0] & MotionEventCompat.ACTION_MASK) == 239 && b1 == 187 && b2 == 191) {
                    return encoding;
                }
                stream.reset();
                return encoding;
            }
            stream.reset();
            return encoding;
        } else if (!encoding.startsWith(HTTP.UTF_16)) {
            return encoding;
        } else {
            if (stream.read(b, 0, 2) == 2) {
                int b0 = b[0] & MotionEventCompat.ACTION_MASK;
                b1 = b[1] & MotionEventCompat.ACTION_MASK;
                if (b0 == 254 && b1 == MotionEventCompat.ACTION_MASK) {
                    return CharEncoding.UTF_16BE;
                }
                if (b0 == MotionEventCompat.ACTION_MASK && b1 == 254) {
                    return CharEncoding.UTF_16LE;
                }
            }
            stream.reset();
            return encoding;
        }
    }

    protected String getEncodingName(byte[] b4) {
        int b0 = b4[0] & MotionEventCompat.ACTION_MASK;
        int b1 = b4[1] & MotionEventCompat.ACTION_MASK;
        if (b0 == 254 && b1 == MotionEventCompat.ACTION_MASK) {
            return CharEncoding.UTF_16BE;
        }
        if (b0 == MotionEventCompat.ACTION_MASK && b1 == 254) {
            return CharEncoding.UTF_16LE;
        }
        int b2 = b4[2] & MotionEventCompat.ACTION_MASK;
        if (b0 == 239 && b1 == 187 && b2 == 191) {
            return HTTP.UTF_8;
        }
        int b3 = b4[3] & MotionEventCompat.ACTION_MASK;
        if (b0 == 0 && b1 == 0 && b2 == 0 && b3 == 60) {
            return "ISO-10646-UCS-4";
        }
        if (b0 == 60 && b1 == 0 && b2 == 0 && b3 == 0) {
            return "ISO-10646-UCS-4";
        }
        if (b0 == 0 && b1 == 0 && b2 == 60 && b3 == 0) {
            return "ISO-10646-UCS-4";
        }
        if (b0 == 0 && b1 == 60 && b2 == 0 && b3 == 0) {
            return "ISO-10646-UCS-4";
        }
        if (b0 == 0 && b1 == 60 && b2 == 0 && b3 == 63) {
            return CharEncoding.UTF_16BE;
        }
        if (b0 == 60 && b1 == 0 && b2 == 63 && b3 == 0) {
            return CharEncoding.UTF_16LE;
        }
        if (b0 == 76 && b1 == 111 && b2 == 167 && b3 == 148) {
            return "CP037";
        }
        return null;
    }

    public void parse() throws IOException {
        this.fReader = getReader(this.fSource);
        this.fSource = null;
        int readSize = this.fReader.read(this.fTempString.ch, 0, this.fTempString.ch.length - 1);
        this.fHandler.fHasIncludeReportedContent = true;
        while (readSize != -1) {
            int i = 0;
            int readSize2 = readSize;
            while (i < readSize2) {
                char ch = this.fTempString.ch[i];
                if (!isValid(ch)) {
                    if (XMLChar.isHighSurrogate(ch)) {
                        int ch2;
                        i++;
                        if (i < readSize2) {
                            ch2 = this.fTempString.ch[i];
                            readSize = readSize2;
                        } else {
                            ch2 = this.fReader.read();
                            if (ch2 != -1) {
                                readSize = readSize2 + 1;
                                this.fTempString.ch[readSize2] = (char) ch2;
                            } else {
                                readSize = readSize2;
                            }
                        }
                        if (XMLChar.isLowSurrogate(ch2)) {
                            if (!isValid(XMLChar.supplemental(ch, (char) ch2))) {
                                this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInContent", new Object[]{Integer.toString(XMLChar.supplemental(ch, (char) ch2), 16)}, (short) 2);
                            }
                        } else {
                            this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInContent", new Object[]{Integer.toString(ch2, 16)}, (short) 2);
                        }
                        i++;
                        readSize2 = readSize;
                    } else {
                        this.fErrorReporter.reportError(XMLMessageFormatter.XML_DOMAIN, "InvalidCharInContent", new Object[]{Integer.toString(ch, 16)}, (short) 2);
                    }
                }
                readSize = readSize2;
                i++;
                readSize2 = readSize;
            }
            if (this.fHandler != null && readSize2 > 0) {
                this.fTempString.offset = 0;
                this.fTempString.length = readSize2;
                this.fHandler.characters(this.fTempString, this.fHandler.modifyAugmentations(null, true));
            }
            readSize = this.fReader.read(this.fTempString.ch, 0, this.fTempString.ch.length - 1);
        }
    }

    public void setInputSource(XMLInputSource source) {
        this.fSource = source;
    }

    public void close() throws IOException {
        if (this.fReader != null) {
            this.fReader.close();
            this.fReader = null;
        }
    }

    protected boolean isValid(int ch) {
        return XMLChar.isValid(ch);
    }

    protected void setBufferSize(int bufferSize) {
        bufferSize++;
        if (this.fTempString.ch.length != bufferSize) {
            this.fTempString.ch = new char[bufferSize];
        }
    }
}
