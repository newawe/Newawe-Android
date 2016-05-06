package mf.org.apache.xerces.impl.dv.xs;

import android.support.v4.media.TransportMediator;
import java.io.UnsupportedEncodingException;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.util.URI;
import mf.org.apache.xerces.util.URI.MalformedURIException;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.http.protocol.HTTP;

public class AnyURIDV extends TypeValidator {
    private static final URI BASE_URI;
    private static char[] gAfterEscaping1;
    private static char[] gAfterEscaping2;
    private static char[] gHexChs;
    private static boolean[] gNeedEscaping;

    static {
        URI uri = null;
        try {
            uri = new URI("abc://def.ghi.jkl");
        } catch (MalformedURIException e) {
        }
        BASE_URI = uri;
        gNeedEscaping = new boolean[TransportMediator.FLAG_KEY_MEDIA_NEXT];
        gAfterEscaping1 = new char[TransportMediator.FLAG_KEY_MEDIA_NEXT];
        gAfterEscaping2 = new char[TransportMediator.FLAG_KEY_MEDIA_NEXT];
        gHexChs = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0; i <= 31; i++) {
            gNeedEscaping[i] = true;
            gAfterEscaping1[i] = gHexChs[i >> 4];
            gAfterEscaping2[i] = gHexChs[i & 15];
        }
        gNeedEscaping[TransportMediator.KEYCODE_MEDIA_PAUSE] = true;
        gAfterEscaping1[TransportMediator.KEYCODE_MEDIA_PAUSE] = '7';
        gAfterEscaping2[TransportMediator.KEYCODE_MEDIA_PAUSE] = 'F';
        for (char ch : new char[]{' ', '<', '>', '\"', '{', '}', '|', '\\', '^', '~', '`'}) {
            gNeedEscaping[ch] = true;
            gAfterEscaping1[ch] = gHexChs[ch >> 4];
            gAfterEscaping2[ch] = gHexChs[ch & 15];
        }
    }

    public short getAllowedFacets() {
        return (short) 2079;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            if (content.length() != 0) {
                URI uri = new URI(BASE_URI, encode(content));
            }
            return content;
        } catch (MalformedURIException e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_ANYURI});
        }
    }

    private static String encode(String anyURI) {
        int len = anyURI.length();
        StringBuffer buffer = new StringBuffer(len * 3);
        int i = 0;
        while (i < len) {
            int ch = anyURI.charAt(i);
            if (ch >= TransportMediator.FLAG_KEY_MEDIA_NEXT) {
                break;
            }
            if (gNeedEscaping[ch]) {
                buffer.append('%');
                buffer.append(gAfterEscaping1[ch]);
                buffer.append(gAfterEscaping2[ch]);
            } else {
                buffer.append((char) ch);
            }
            i++;
        }
        if (i < len) {
            try {
                for (byte b : anyURI.substring(i).getBytes(HTTP.UTF_8)) {
                    if (b < null) {
                        ch = b + NodeFilter.SHOW_DOCUMENT;
                        buffer.append('%');
                        buffer.append(gHexChs[ch >> 4]);
                        buffer.append(gHexChs[ch & 15]);
                    } else if (gNeedEscaping[b]) {
                        buffer.append('%');
                        buffer.append(gAfterEscaping1[b]);
                        buffer.append(gAfterEscaping2[b]);
                    } else {
                        buffer.append((char) b);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                return anyURI;
            }
        }
        if (buffer.length() != len) {
            return buffer.toString();
        }
        return anyURI;
    }
}
