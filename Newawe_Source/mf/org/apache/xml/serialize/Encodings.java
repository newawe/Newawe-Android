package mf.org.apache.xml.serialize;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Locale;
import mf.org.apache.xerces.util.EncodingMap;
import org.apache.http.protocol.HTTP;

public class Encodings {
    static final String DEFAULT_ENCODING = "UTF8";
    static final int DEFAULT_LAST_PRINTABLE = 127;
    static final String JIS_DANGER_CHARS = "\\~\u007f\u00a2\u00a3\u00a5\u00ac\u2014\u2015\u2016\u2026\u203e\u203e\u2225\u222f\u301c\uff3c\uff5e\uffe0\uffe1\uffe2\uffe3";
    static final int LAST_PRINTABLE_UNICODE = 65535;
    static final String[] UNICODE_ENCODINGS;
    static Hashtable _encodings;

    static {
        UNICODE_ENCODINGS = new String[]{"Unicode", "UnicodeBig", "UnicodeLittle", "GB2312", DEFAULT_ENCODING, HTTP.UTF_16};
        _encodings = new Hashtable();
    }

    static EncodingInfo getEncodingInfo(String encoding, boolean allowJavaNames) throws UnsupportedEncodingException {
        EncodingInfo eInfo;
        if (encoding == null) {
            eInfo = (EncodingInfo) _encodings.get(DEFAULT_ENCODING);
            if (eInfo != null) {
                return eInfo;
            }
            eInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(DEFAULT_ENCODING), DEFAULT_ENCODING, LAST_PRINTABLE_UNICODE);
            _encodings.put(DEFAULT_ENCODING, eInfo);
            return eInfo;
        }
        encoding = encoding.toUpperCase(Locale.ENGLISH);
        String jName = EncodingMap.getIANA2JavaMapping(encoding);
        int i;
        if (jName != null) {
            eInfo = (EncodingInfo) _encodings.get(jName);
            if (eInfo != null) {
                return eInfo;
            }
            i = 0;
            while (i < UNICODE_ENCODINGS.length) {
                if (UNICODE_ENCODINGS[i].equalsIgnoreCase(jName)) {
                    eInfo = new EncodingInfo(encoding, jName, LAST_PRINTABLE_UNICODE);
                    break;
                }
                i++;
            }
            if (i == UNICODE_ENCODINGS.length) {
                eInfo = new EncodingInfo(encoding, jName, DEFAULT_LAST_PRINTABLE);
            }
            _encodings.put(jName, eInfo);
            return eInfo;
        } else if (allowJavaNames) {
            EncodingInfo.testJavaEncodingName(encoding);
            eInfo = (EncodingInfo) _encodings.get(encoding);
            if (eInfo != null) {
                return eInfo;
            }
            i = 0;
            while (i < UNICODE_ENCODINGS.length) {
                if (UNICODE_ENCODINGS[i].equalsIgnoreCase(encoding)) {
                    eInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(encoding), encoding, LAST_PRINTABLE_UNICODE);
                    break;
                }
                i++;
            }
            if (i == UNICODE_ENCODINGS.length) {
                eInfo = new EncodingInfo(EncodingMap.getJava2IANAMapping(encoding), encoding, DEFAULT_LAST_PRINTABLE);
            }
            _encodings.put(encoding, eInfo);
            return eInfo;
        } else {
            throw new UnsupportedEncodingException(encoding);
        }
    }
}
