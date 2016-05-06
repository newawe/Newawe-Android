package mf.org.apache.xerces.util;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

public class URI implements Serializable {
    private static final int ASCII_ALPHA_CHARACTERS = 16;
    private static final int ASCII_DIGIT_CHARACTERS = 32;
    private static final int ASCII_HEX_CHARACTERS = 64;
    private static boolean DEBUG = false;
    private static final int MARK_CHARACTERS = 2;
    private static final int MASK_ALPHA_NUMERIC = 48;
    private static final int MASK_PATH_CHARACTER = 178;
    private static final int MASK_SCHEME_CHARACTER = 52;
    private static final int MASK_UNRESERVED_MASK = 50;
    private static final int MASK_URI_CHARACTER = 51;
    private static final int MASK_USERINFO_CHARACTER = 58;
    private static final int PATH_CHARACTERS = 128;
    private static final int RESERVED_CHARACTERS = 1;
    private static final int SCHEME_CHARACTERS = 4;
    private static final int USERINFO_CHARACTERS = 8;
    private static final byte[] fgLookupTable;
    static final long serialVersionUID = 1601921774685357214L;
    private String m_fragment;
    private String m_host;
    private String m_path;
    private int m_port;
    private String m_queryString;
    private String m_regAuthority;
    private String m_scheme;
    private String m_userinfo;

    public static class MalformedURIException extends IOException {
        static final long serialVersionUID = -6695054834342951930L;

        public MalformedURIException(String p_msg) {
            super(p_msg);
        }
    }

    static {
        int i;
        byte[] bArr;
        fgLookupTable = new byte[PATH_CHARACTERS];
        for (i = MASK_ALPHA_NUMERIC; i <= 57; i += RESERVED_CHARACTERS) {
            bArr = fgLookupTable;
            bArr[i] = (byte) (bArr[i] | 96);
        }
        for (i = 65; i <= 70; i += RESERVED_CHARACTERS) {
            bArr = fgLookupTable;
            bArr[i] = (byte) (bArr[i] | 80);
            bArr = fgLookupTable;
            int i2 = i + ASCII_DIGIT_CHARACTERS;
            bArr[i2] = (byte) (bArr[i2] | 80);
        }
        for (i = 71; i <= 90; i += RESERVED_CHARACTERS) {
            bArr = fgLookupTable;
            bArr[i] = (byte) (bArr[i] | ASCII_ALPHA_CHARACTERS);
            bArr = fgLookupTable;
            i2 = i + ASCII_DIGIT_CHARACTERS;
            bArr[i2] = (byte) (bArr[i2] | ASCII_ALPHA_CHARACTERS);
        }
        bArr = fgLookupTable;
        bArr[59] = (byte) (bArr[59] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[47] = (byte) (bArr[47] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[63] = (byte) (bArr[63] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[MASK_USERINFO_CHARACTER] = (byte) (bArr[MASK_USERINFO_CHARACTER] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[ASCII_HEX_CHARACTERS] = (byte) (bArr[ASCII_HEX_CHARACTERS] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[38] = (byte) (bArr[38] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[61] = (byte) (bArr[61] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[43] = (byte) (bArr[43] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[36] = (byte) (bArr[36] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[44] = (byte) (bArr[44] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[91] = (byte) (bArr[91] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[93] = (byte) (bArr[93] | RESERVED_CHARACTERS);
        bArr = fgLookupTable;
        bArr[45] = (byte) (bArr[45] | MARK_CHARACTERS);
        bArr = fgLookupTable;
        bArr[95] = (byte) (bArr[95] | MARK_CHARACTERS);
        bArr = fgLookupTable;
        bArr[46] = (byte) (bArr[46] | MARK_CHARACTERS);
        bArr = fgLookupTable;
        bArr[33] = (byte) (bArr[33] | MARK_CHARACTERS);
        bArr = fgLookupTable;
        bArr[TransportMediator.KEYCODE_MEDIA_PLAY] = (byte) (bArr[TransportMediator.KEYCODE_MEDIA_PLAY] | MARK_CHARACTERS);
        bArr = fgLookupTable;
        bArr[42] = (byte) (bArr[42] | MARK_CHARACTERS);
        bArr = fgLookupTable;
        bArr[39] = (byte) (bArr[39] | MARK_CHARACTERS);
        bArr = fgLookupTable;
        bArr[40] = (byte) (bArr[40] | MARK_CHARACTERS);
        bArr = fgLookupTable;
        bArr[41] = (byte) (bArr[41] | MARK_CHARACTERS);
        bArr = fgLookupTable;
        bArr[43] = (byte) (bArr[43] | SCHEME_CHARACTERS);
        bArr = fgLookupTable;
        bArr[45] = (byte) (bArr[45] | SCHEME_CHARACTERS);
        bArr = fgLookupTable;
        bArr[46] = (byte) (bArr[46] | SCHEME_CHARACTERS);
        bArr = fgLookupTable;
        bArr[59] = (byte) (bArr[59] | USERINFO_CHARACTERS);
        bArr = fgLookupTable;
        bArr[MASK_USERINFO_CHARACTER] = (byte) (bArr[MASK_USERINFO_CHARACTER] | USERINFO_CHARACTERS);
        bArr = fgLookupTable;
        bArr[38] = (byte) (bArr[38] | USERINFO_CHARACTERS);
        bArr = fgLookupTable;
        bArr[61] = (byte) (bArr[61] | USERINFO_CHARACTERS);
        bArr = fgLookupTable;
        bArr[43] = (byte) (bArr[43] | USERINFO_CHARACTERS);
        bArr = fgLookupTable;
        bArr[36] = (byte) (bArr[36] | USERINFO_CHARACTERS);
        bArr = fgLookupTable;
        bArr[44] = (byte) (bArr[44] | USERINFO_CHARACTERS);
        bArr = fgLookupTable;
        bArr[59] = (byte) (bArr[59] | PATH_CHARACTERS);
        bArr = fgLookupTable;
        bArr[47] = (byte) (bArr[47] | PATH_CHARACTERS);
        bArr = fgLookupTable;
        bArr[MASK_USERINFO_CHARACTER] = (byte) (bArr[MASK_USERINFO_CHARACTER] | PATH_CHARACTERS);
        bArr = fgLookupTable;
        bArr[ASCII_HEX_CHARACTERS] = (byte) (bArr[ASCII_HEX_CHARACTERS] | PATH_CHARACTERS);
        bArr = fgLookupTable;
        bArr[38] = (byte) (bArr[38] | PATH_CHARACTERS);
        bArr = fgLookupTable;
        bArr[61] = (byte) (bArr[61] | PATH_CHARACTERS);
        bArr = fgLookupTable;
        bArr[43] = (byte) (bArr[43] | PATH_CHARACTERS);
        bArr = fgLookupTable;
        bArr[36] = (byte) (bArr[36] | PATH_CHARACTERS);
        bArr = fgLookupTable;
        bArr[44] = (byte) (bArr[44] | PATH_CHARACTERS);
        DEBUG = false;
    }

    public URI() {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_regAuthority = null;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
    }

    public URI(URI p_other) {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_regAuthority = null;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        initialize(p_other);
    }

    public URI(String p_uriSpec) throws MalformedURIException {
        this(null, p_uriSpec);
    }

    public URI(String p_uriSpec, boolean allowNonAbsoluteURI) throws MalformedURIException {
        this(null, p_uriSpec, allowNonAbsoluteURI);
    }

    public URI(URI p_base, String p_uriSpec) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_regAuthority = null;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        initialize(p_base, p_uriSpec);
    }

    public URI(URI p_base, String p_uriSpec, boolean allowNonAbsoluteURI) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_regAuthority = null;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        initialize(p_base, p_uriSpec, allowNonAbsoluteURI);
    }

    public URI(String p_scheme, String p_schemeSpecificPart) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_regAuthority = null;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        if (p_scheme == null || p_scheme.trim().length() == 0) {
            throw new MalformedURIException("Cannot construct URI with null/empty scheme!");
        } else if (p_schemeSpecificPart == null || p_schemeSpecificPart.trim().length() == 0) {
            throw new MalformedURIException("Cannot construct URI with null/empty scheme-specific part!");
        } else {
            setScheme(p_scheme);
            setPath(p_schemeSpecificPart);
        }
    }

    public URI(String p_scheme, String p_host, String p_path, String p_queryString, String p_fragment) throws MalformedURIException {
        this(p_scheme, null, p_host, -1, p_path, p_queryString, p_fragment);
    }

    public URI(String p_scheme, String p_userinfo, String p_host, int p_port, String p_path, String p_queryString, String p_fragment) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_regAuthority = null;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        if (p_scheme == null || p_scheme.trim().length() == 0) {
            throw new MalformedURIException("Scheme is required!");
        }
        if (p_host == null) {
            if (p_userinfo != null) {
                throw new MalformedURIException("Userinfo may not be specified if host is not specified!");
            } else if (p_port != -1) {
                throw new MalformedURIException("Port may not be specified if host is not specified!");
            }
        }
        if (p_path != null) {
            if (p_path.indexOf(63) != -1 && p_queryString != null) {
                throw new MalformedURIException("Query string cannot be specified in path and query string!");
            } else if (!(p_path.indexOf(35) == -1 || p_fragment == null)) {
                throw new MalformedURIException("Fragment cannot be specified in both the path and fragment!");
            }
        }
        setScheme(p_scheme);
        setHost(p_host);
        setPort(p_port);
        setUserinfo(p_userinfo);
        setPath(p_path);
        setQueryString(p_queryString);
        setFragment(p_fragment);
    }

    private void initialize(URI p_other) {
        this.m_scheme = p_other.getScheme();
        this.m_userinfo = p_other.getUserinfo();
        this.m_host = p_other.getHost();
        this.m_port = p_other.getPort();
        this.m_regAuthority = p_other.getRegBasedAuthority();
        this.m_path = p_other.getPath();
        this.m_queryString = p_other.getQueryString();
        this.m_fragment = p_other.getFragment();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initialize(mf.org.apache.xerces.util.URI r13, java.lang.String r14, boolean r15) throws mf.org.apache.xerces.util.URI.MalformedURIException {
        /*
        r12 = this;
        r8 = r14;
        if (r8 == 0) goto L_0x0012;
    L_0x0003:
        r9 = r8.length();
    L_0x0007:
        if (r13 != 0) goto L_0x001c;
    L_0x0009:
        if (r9 != 0) goto L_0x001c;
    L_0x000b:
        if (r15 == 0) goto L_0x0014;
    L_0x000d:
        r10 = "";
        r12.m_path = r10;
    L_0x0011:
        return;
    L_0x0012:
        r9 = 0;
        goto L_0x0007;
    L_0x0014:
        r10 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r11 = "Cannot initialize URI with empty parameters.";
        r10.<init>(r11);
        throw r10;
    L_0x001c:
        if (r9 != 0) goto L_0x0022;
    L_0x001e:
        r12.initialize(r13);
        goto L_0x0011;
    L_0x0022:
        r2 = 0;
        r10 = 58;
        r0 = r8.indexOf(r10);
        r10 = -1;
        if (r0 == r10) goto L_0x007c;
    L_0x002c:
        r4 = r0 + -1;
        r10 = 47;
        r5 = r8.lastIndexOf(r10, r4);
        r10 = 63;
        r3 = r8.lastIndexOf(r10, r4);
        r10 = 35;
        r1 = r8.lastIndexOf(r10, r4);
        if (r0 == 0) goto L_0x004b;
    L_0x0042:
        r10 = -1;
        if (r5 != r10) goto L_0x004b;
    L_0x0045:
        r10 = -1;
        if (r3 != r10) goto L_0x004b;
    L_0x0048:
        r10 = -1;
        if (r1 == r10) goto L_0x005b;
    L_0x004b:
        if (r0 == 0) goto L_0x0053;
    L_0x004d:
        if (r13 != 0) goto L_0x0090;
    L_0x004f:
        if (r1 == 0) goto L_0x0090;
    L_0x0051:
        if (r15 != 0) goto L_0x0090;
    L_0x0053:
        r10 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r11 = "No scheme found in URI.";
        r10.<init>(r11);
        throw r10;
    L_0x005b:
        r12.initializeScheme(r8);
        r10 = r12.m_scheme;
        r10 = r10.length();
        r2 = r10 + 1;
        r10 = r9 + -1;
        if (r0 == r10) goto L_0x0074;
    L_0x006a:
        r10 = r0 + 1;
        r10 = r8.charAt(r10);
        r11 = 35;
        if (r10 != r11) goto L_0x0090;
    L_0x0074:
        r10 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r11 = "Scheme specific part cannot be empty.";
        r10.<init>(r11);
        throw r10;
    L_0x007c:
        if (r13 != 0) goto L_0x0090;
    L_0x007e:
        r10 = 35;
        r10 = r8.indexOf(r10);
        if (r10 == 0) goto L_0x0090;
    L_0x0086:
        if (r15 != 0) goto L_0x0090;
    L_0x0088:
        r10 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r11 = "No scheme found in URI.";
        r10.<init>(r11);
        throw r10;
    L_0x0090:
        r10 = r2 + 1;
        if (r10 >= r9) goto L_0x00ba;
    L_0x0094:
        r10 = r8.charAt(r2);
        r11 = 47;
        if (r10 != r11) goto L_0x00ba;
    L_0x009c:
        r10 = r2 + 1;
        r10 = r8.charAt(r10);
        r11 = 47;
        if (r10 != r11) goto L_0x00ba;
    L_0x00a6:
        r2 = r2 + 2;
        r6 = r2;
        r7 = 0;
    L_0x00aa:
        if (r2 < r9) goto L_0x00c4;
    L_0x00ac:
        if (r2 <= r6) goto L_0x00d7;
    L_0x00ae:
        r10 = r8.substring(r6, r2);
        r10 = r12.initializeAuthority(r10);
        if (r10 != 0) goto L_0x00ba;
    L_0x00b8:
        r2 = r6 + -2;
    L_0x00ba:
        r12.initializePath(r8, r2);
        if (r13 == 0) goto L_0x0011;
    L_0x00bf:
        r12.absolutize(r13);
        goto L_0x0011;
    L_0x00c4:
        r7 = r8.charAt(r2);
        r10 = 47;
        if (r7 == r10) goto L_0x00ac;
    L_0x00cc:
        r10 = 63;
        if (r7 == r10) goto L_0x00ac;
    L_0x00d0:
        r10 = 35;
        if (r7 == r10) goto L_0x00ac;
    L_0x00d4:
        r2 = r2 + 1;
        goto L_0x00aa;
    L_0x00d7:
        r10 = "";
        r12.m_host = r10;
        goto L_0x00ba;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.util.URI.initialize(mf.org.apache.xerces.util.URI, java.lang.String, boolean):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initialize(mf.org.apache.xerces.util.URI r13, java.lang.String r14) throws mf.org.apache.xerces.util.URI.MalformedURIException {
        /*
        r12 = this;
        r8 = r14;
        if (r8 == 0) goto L_0x0013;
    L_0x0003:
        r9 = r8.length();
    L_0x0007:
        if (r13 != 0) goto L_0x0015;
    L_0x0009:
        if (r9 != 0) goto L_0x0015;
    L_0x000b:
        r10 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r11 = "Cannot initialize URI with empty parameters.";
        r10.<init>(r11);
        throw r10;
    L_0x0013:
        r9 = 0;
        goto L_0x0007;
    L_0x0015:
        if (r9 != 0) goto L_0x001b;
    L_0x0017:
        r12.initialize(r13);
    L_0x001a:
        return;
    L_0x001b:
        r2 = 0;
        r10 = 58;
        r0 = r8.indexOf(r10);
        r10 = -1;
        if (r0 == r10) goto L_0x0073;
    L_0x0025:
        r4 = r0 + -1;
        r10 = 47;
        r5 = r8.lastIndexOf(r10, r4);
        r10 = 63;
        r3 = r8.lastIndexOf(r10, r4);
        r10 = 35;
        r1 = r8.lastIndexOf(r10, r4);
        if (r0 == 0) goto L_0x0044;
    L_0x003b:
        r10 = -1;
        if (r5 != r10) goto L_0x0044;
    L_0x003e:
        r10 = -1;
        if (r3 != r10) goto L_0x0044;
    L_0x0041:
        r10 = -1;
        if (r1 == r10) goto L_0x0052;
    L_0x0044:
        if (r0 == 0) goto L_0x004a;
    L_0x0046:
        if (r13 != 0) goto L_0x0085;
    L_0x0048:
        if (r1 == 0) goto L_0x0085;
    L_0x004a:
        r10 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r11 = "No scheme found in URI.";
        r10.<init>(r11);
        throw r10;
    L_0x0052:
        r12.initializeScheme(r8);
        r10 = r12.m_scheme;
        r10 = r10.length();
        r2 = r10 + 1;
        r10 = r9 + -1;
        if (r0 == r10) goto L_0x006b;
    L_0x0061:
        r10 = r0 + 1;
        r10 = r8.charAt(r10);
        r11 = 35;
        if (r10 != r11) goto L_0x0085;
    L_0x006b:
        r10 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r11 = "Scheme specific part cannot be empty.";
        r10.<init>(r11);
        throw r10;
    L_0x0073:
        if (r13 != 0) goto L_0x0085;
    L_0x0075:
        r10 = 35;
        r10 = r8.indexOf(r10);
        if (r10 == 0) goto L_0x0085;
    L_0x007d:
        r10 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r11 = "No scheme found in URI.";
        r10.<init>(r11);
        throw r10;
    L_0x0085:
        r10 = r2 + 1;
        if (r10 >= r9) goto L_0x00af;
    L_0x0089:
        r10 = r8.charAt(r2);
        r11 = 47;
        if (r10 != r11) goto L_0x00af;
    L_0x0091:
        r10 = r2 + 1;
        r10 = r8.charAt(r10);
        r11 = 47;
        if (r10 != r11) goto L_0x00af;
    L_0x009b:
        r2 = r2 + 2;
        r6 = r2;
        r7 = 0;
    L_0x009f:
        if (r2 < r9) goto L_0x00b9;
    L_0x00a1:
        if (r2 <= r6) goto L_0x00cc;
    L_0x00a3:
        r10 = r8.substring(r6, r2);
        r10 = r12.initializeAuthority(r10);
        if (r10 != 0) goto L_0x00af;
    L_0x00ad:
        r2 = r6 + -2;
    L_0x00af:
        r12.initializePath(r8, r2);
        if (r13 == 0) goto L_0x001a;
    L_0x00b4:
        r12.absolutize(r13);
        goto L_0x001a;
    L_0x00b9:
        r7 = r8.charAt(r2);
        r10 = 47;
        if (r7 == r10) goto L_0x00a1;
    L_0x00c1:
        r10 = 63;
        if (r7 == r10) goto L_0x00a1;
    L_0x00c5:
        r10 = 35;
        if (r7 == r10) goto L_0x00a1;
    L_0x00c9:
        r2 = r2 + 1;
        goto L_0x009f;
    L_0x00cc:
        r10 = "";
        r12.m_host = r10;
        goto L_0x00af;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.util.URI.initialize(mf.org.apache.xerces.util.URI, java.lang.String):void");
    }

    public void absolutize(URI p_base) {
        if (this.m_path.length() == 0 && this.m_scheme == null && this.m_host == null && this.m_regAuthority == null) {
            this.m_scheme = p_base.getScheme();
            this.m_userinfo = p_base.getUserinfo();
            this.m_host = p_base.getHost();
            this.m_port = p_base.getPort();
            this.m_regAuthority = p_base.getRegBasedAuthority();
            this.m_path = p_base.getPath();
            if (this.m_queryString == null) {
                this.m_queryString = p_base.getQueryString();
                if (this.m_fragment == null) {
                    this.m_fragment = p_base.getFragment();
                }
            }
        } else if (this.m_scheme == null) {
            this.m_scheme = p_base.getScheme();
            if (this.m_host == null && this.m_regAuthority == null) {
                this.m_userinfo = p_base.getUserinfo();
                this.m_host = p_base.getHost();
                this.m_port = p_base.getPort();
                this.m_regAuthority = p_base.getRegBasedAuthority();
                if (this.m_path.length() <= 0 || !this.m_path.startsWith("/")) {
                    int index;
                    int segIndex;
                    String path = StringUtils.EMPTY;
                    String basePath = p_base.getPath();
                    if (basePath != null && basePath.length() > 0) {
                        int lastSlash = basePath.lastIndexOf(47);
                        if (lastSlash != -1) {
                            path = basePath.substring(0, lastSlash + RESERVED_CHARACTERS);
                        }
                    } else if (this.m_path.length() > 0) {
                        path = "/";
                    }
                    path = path.concat(this.m_path);
                    while (true) {
                        index = path.indexOf("/./");
                        if (index == -1) {
                            break;
                        }
                        path = path.substring(0, index + RESERVED_CHARACTERS).concat(path.substring(index + 3));
                    }
                    if (path.endsWith("/.")) {
                        path = path.substring(0, path.length() - 1);
                    }
                    index = RESERVED_CHARACTERS;
                    while (true) {
                        index = path.indexOf("/../", index);
                        if (index <= 0) {
                            break;
                        }
                        String tempString = path.substring(0, path.indexOf("/../"));
                        segIndex = tempString.lastIndexOf(47);
                        if (segIndex == -1) {
                            index += SCHEME_CHARACTERS;
                        } else if (tempString.substring(segIndex).equals("..")) {
                            index += SCHEME_CHARACTERS;
                        } else {
                            path = path.substring(0, segIndex + RESERVED_CHARACTERS).concat(path.substring(index + SCHEME_CHARACTERS));
                            index = segIndex;
                        }
                    }
                    if (path.endsWith("/..")) {
                        segIndex = path.substring(0, path.length() - 3).lastIndexOf(47);
                        if (segIndex != -1) {
                            path = path.substring(0, segIndex + RESERVED_CHARACTERS);
                        }
                    }
                    this.m_path = path;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initializeScheme(java.lang.String r7) throws mf.org.apache.xerces.util.URI.MalformedURIException {
        /*
        r6 = this;
        r3 = r7.length();
        r0 = 0;
        r1 = 0;
        r2 = 0;
    L_0x0007:
        if (r0 < r3) goto L_0x001c;
    L_0x0009:
        r4 = 0;
        r1 = r7.substring(r4, r0);
        r4 = r1.length();
        if (r4 != 0) goto L_0x0033;
    L_0x0014:
        r4 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r5 = "No scheme found in URI.";
        r4.<init>(r5);
        throw r4;
    L_0x001c:
        r2 = r7.charAt(r0);
        r4 = 58;
        if (r2 == r4) goto L_0x0009;
    L_0x0024:
        r4 = 47;
        if (r2 == r4) goto L_0x0009;
    L_0x0028:
        r4 = 63;
        if (r2 == r4) goto L_0x0009;
    L_0x002c:
        r4 = 35;
        if (r2 == r4) goto L_0x0009;
    L_0x0030:
        r0 = r0 + 1;
        goto L_0x0007;
    L_0x0033:
        r6.setScheme(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.util.URI.initializeScheme(java.lang.String):void");
    }

    private boolean initializeAuthority(String p_uriSpec) {
        int index = 0;
        int end = p_uriSpec.length();
        String userinfo = null;
        if (p_uriSpec.indexOf(ASCII_HEX_CHARACTERS, 0) != -1) {
            while (index < end && p_uriSpec.charAt(index) != '@') {
                index += RESERVED_CHARACTERS;
            }
            userinfo = p_uriSpec.substring(0, index);
            index += RESERVED_CHARACTERS;
        }
        int start = index;
        boolean hasPort = false;
        if (index < end) {
            if (p_uriSpec.charAt(start) == '[') {
                int bracketIndex = p_uriSpec.indexOf(93, start);
                if (bracketIndex != -1) {
                    index = bracketIndex;
                } else {
                    index = end;
                }
                if (index + RESERVED_CHARACTERS >= end || p_uriSpec.charAt(index + RESERVED_CHARACTERS) != ':') {
                    index = end;
                } else {
                    index += RESERVED_CHARACTERS;
                    hasPort = true;
                }
            } else {
                int colonIndex = p_uriSpec.lastIndexOf(MASK_USERINFO_CHARACTER, end);
                if (colonIndex > start) {
                    index = colonIndex;
                } else {
                    index = end;
                }
                hasPort = index != end;
            }
        }
        String host = p_uriSpec.substring(start, index);
        int port = -1;
        if (host.length() > 0 && hasPort) {
            index += RESERVED_CHARACTERS;
            start = index;
            while (index < end) {
                index += RESERVED_CHARACTERS;
            }
            String portStr = p_uriSpec.substring(start, index);
            if (portStr.length() > 0) {
                try {
                    port = Integer.parseInt(portStr);
                    if (port == -1) {
                        port--;
                    }
                } catch (NumberFormatException e) {
                    port = -2;
                }
            }
        }
        if (isValidServerBasedAuthority(host, port, userinfo)) {
            this.m_host = host;
            this.m_port = port;
            this.m_userinfo = userinfo;
            return true;
        } else if (!isValidRegistryBasedAuthority(p_uriSpec)) {
            return false;
        } else {
            this.m_regAuthority = p_uriSpec;
            return true;
        }
    }

    private boolean isValidServerBasedAuthority(String host, int port, String userinfo) {
        if (!isWellFormedAddress(host) || port < -1 || port > SupportMenu.USER_MASK) {
            return false;
        }
        if (userinfo != null) {
            int index = 0;
            int end = userinfo.length();
            while (index < end) {
                char testChar = userinfo.charAt(index);
                if (testChar == '%') {
                    if (index + MARK_CHARACTERS >= end || !isHex(userinfo.charAt(index + RESERVED_CHARACTERS)) || !isHex(userinfo.charAt(index + MARK_CHARACTERS))) {
                        return false;
                    }
                    index += MARK_CHARACTERS;
                } else if (!isUserinfoCharacter(testChar)) {
                    return false;
                }
                index += RESERVED_CHARACTERS;
            }
        }
        return true;
    }

    private boolean isValidRegistryBasedAuthority(String authority) {
        int index = 0;
        int end = authority.length();
        while (index < end) {
            char testChar = authority.charAt(index);
            if (testChar == '%') {
                if (index + MARK_CHARACTERS >= end || !isHex(authority.charAt(index + RESERVED_CHARACTERS)) || !isHex(authority.charAt(index + MARK_CHARACTERS))) {
                    return false;
                }
                index += MARK_CHARACTERS;
            } else if (!isPathCharacter(testChar)) {
                return false;
            }
            index += RESERVED_CHARACTERS;
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initializePath(java.lang.String r10, int r11) throws mf.org.apache.xerces.util.URI.MalformedURIException {
        /*
        r9 = this;
        r8 = 63;
        r7 = 37;
        r6 = 35;
        if (r10 != 0) goto L_0x0010;
    L_0x0008:
        r4 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r5 = "Cannot initialize path from null string!";
        r4.<init>(r5);
        throw r4;
    L_0x0010:
        r1 = r11;
        r2 = r11;
        r0 = r10.length();
        r3 = 0;
        if (r2 >= r0) goto L_0x0029;
    L_0x0019:
        r4 = r9.getScheme();
        if (r4 == 0) goto L_0x0027;
    L_0x001f:
        r4 = r10.charAt(r2);
        r5 = 47;
        if (r4 != r5) goto L_0x00ca;
    L_0x0027:
        if (r1 < r0) goto L_0x004a;
    L_0x0029:
        r4 = r10.substring(r2, r1);
        r9.m_path = r4;
        if (r3 != r8) goto L_0x003c;
    L_0x0031:
        r1 = r1 + 1;
        r2 = r1;
    L_0x0034:
        if (r1 < r0) goto L_0x00e9;
    L_0x0036:
        r4 = r10.substring(r2, r1);
        r9.m_queryString = r4;
    L_0x003c:
        if (r3 != r6) goto L_0x0049;
    L_0x003e:
        r1 = r1 + 1;
        r2 = r1;
    L_0x0041:
        if (r1 < r0) goto L_0x0136;
    L_0x0043:
        r4 = r10.substring(r2, r1);
        r9.m_fragment = r4;
    L_0x0049:
        return;
    L_0x004a:
        r3 = r10.charAt(r1);
        if (r3 != r7) goto L_0x0079;
    L_0x0050:
        r4 = r1 + 2;
        if (r4 >= r0) goto L_0x006c;
    L_0x0054:
        r4 = r1 + 1;
        r4 = r10.charAt(r4);
        r4 = isHex(r4);
        if (r4 == 0) goto L_0x006c;
    L_0x0060:
        r4 = r1 + 2;
        r4 = r10.charAt(r4);
        r4 = isHex(r4);
        if (r4 != 0) goto L_0x0074;
    L_0x006c:
        r4 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r5 = "Path contains invalid escape sequence!";
        r4.<init>(r5);
        throw r4;
    L_0x0074:
        r1 = r1 + 2;
    L_0x0076:
        r1 = r1 + 1;
        goto L_0x0027;
    L_0x0079:
        r4 = isPathCharacter(r3);
        if (r4 != 0) goto L_0x0076;
    L_0x007f:
        if (r3 == r8) goto L_0x0029;
    L_0x0081:
        if (r3 == r6) goto L_0x0029;
    L_0x0083:
        r4 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r5 = new java.lang.StringBuilder;
        r6 = "Path contains invalid character: ";
        r5.<init>(r6);
        r5 = r5.append(r3);
        r5 = r5.toString();
        r4.<init>(r5);
        throw r4;
    L_0x0098:
        r3 = r10.charAt(r1);
        if (r3 == r8) goto L_0x0029;
    L_0x009e:
        if (r3 == r6) goto L_0x0029;
    L_0x00a0:
        if (r3 != r7) goto L_0x00ce;
    L_0x00a2:
        r4 = r1 + 2;
        if (r4 >= r0) goto L_0x00be;
    L_0x00a6:
        r4 = r1 + 1;
        r4 = r10.charAt(r4);
        r4 = isHex(r4);
        if (r4 == 0) goto L_0x00be;
    L_0x00b2:
        r4 = r1 + 2;
        r4 = r10.charAt(r4);
        r4 = isHex(r4);
        if (r4 != 0) goto L_0x00c6;
    L_0x00be:
        r4 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r5 = "Opaque part contains invalid escape sequence!";
        r4.<init>(r5);
        throw r4;
    L_0x00c6:
        r1 = r1 + 2;
    L_0x00c8:
        r1 = r1 + 1;
    L_0x00ca:
        if (r1 < r0) goto L_0x0098;
    L_0x00cc:
        goto L_0x0029;
    L_0x00ce:
        r4 = isURICharacter(r3);
        if (r4 != 0) goto L_0x00c8;
    L_0x00d4:
        r4 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r5 = new java.lang.StringBuilder;
        r6 = "Opaque part contains invalid character: ";
        r5.<init>(r6);
        r5 = r5.append(r3);
        r5 = r5.toString();
        r4.<init>(r5);
        throw r4;
    L_0x00e9:
        r3 = r10.charAt(r1);
        if (r3 == r6) goto L_0x0036;
    L_0x00ef:
        if (r3 != r7) goto L_0x011b;
    L_0x00f1:
        r4 = r1 + 2;
        if (r4 >= r0) goto L_0x010d;
    L_0x00f5:
        r4 = r1 + 1;
        r4 = r10.charAt(r4);
        r4 = isHex(r4);
        if (r4 == 0) goto L_0x010d;
    L_0x0101:
        r4 = r1 + 2;
        r4 = r10.charAt(r4);
        r4 = isHex(r4);
        if (r4 != 0) goto L_0x0115;
    L_0x010d:
        r4 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r5 = "Query string contains invalid escape sequence!";
        r4.<init>(r5);
        throw r4;
    L_0x0115:
        r1 = r1 + 2;
    L_0x0117:
        r1 = r1 + 1;
        goto L_0x0034;
    L_0x011b:
        r4 = isURICharacter(r3);
        if (r4 != 0) goto L_0x0117;
    L_0x0121:
        r4 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r5 = new java.lang.StringBuilder;
        r6 = "Query string contains invalid character: ";
        r5.<init>(r6);
        r5 = r5.append(r3);
        r5 = r5.toString();
        r4.<init>(r5);
        throw r4;
    L_0x0136:
        r3 = r10.charAt(r1);
        if (r3 != r7) goto L_0x0166;
    L_0x013c:
        r4 = r1 + 2;
        if (r4 >= r0) goto L_0x0158;
    L_0x0140:
        r4 = r1 + 1;
        r4 = r10.charAt(r4);
        r4 = isHex(r4);
        if (r4 == 0) goto L_0x0158;
    L_0x014c:
        r4 = r1 + 2;
        r4 = r10.charAt(r4);
        r4 = isHex(r4);
        if (r4 != 0) goto L_0x0160;
    L_0x0158:
        r4 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r5 = "Fragment contains invalid escape sequence!";
        r4.<init>(r5);
        throw r4;
    L_0x0160:
        r1 = r1 + 2;
    L_0x0162:
        r1 = r1 + 1;
        goto L_0x0041;
    L_0x0166:
        r4 = isURICharacter(r3);
        if (r4 != 0) goto L_0x0162;
    L_0x016c:
        r4 = new mf.org.apache.xerces.util.URI$MalformedURIException;
        r5 = new java.lang.StringBuilder;
        r6 = "Fragment contains invalid character: ";
        r5.<init>(r6);
        r5 = r5.append(r3);
        r5 = r5.toString();
        r4.<init>(r5);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.util.URI.initializePath(java.lang.String, int):void");
    }

    public String getScheme() {
        return this.m_scheme;
    }

    public String getSchemeSpecificPart() {
        StringBuffer schemespec = new StringBuffer();
        if (!(this.m_host == null && this.m_regAuthority == null)) {
            schemespec.append("//");
            if (this.m_host != null) {
                if (this.m_userinfo != null) {
                    schemespec.append(this.m_userinfo);
                    schemespec.append('@');
                }
                schemespec.append(this.m_host);
                if (this.m_port != -1) {
                    schemespec.append(':');
                    schemespec.append(this.m_port);
                }
            } else {
                schemespec.append(this.m_regAuthority);
            }
        }
        if (this.m_path != null) {
            schemespec.append(this.m_path);
        }
        if (this.m_queryString != null) {
            schemespec.append('?');
            schemespec.append(this.m_queryString);
        }
        if (this.m_fragment != null) {
            schemespec.append('#');
            schemespec.append(this.m_fragment);
        }
        return schemespec.toString();
    }

    public String getUserinfo() {
        return this.m_userinfo;
    }

    public String getHost() {
        return this.m_host;
    }

    public int getPort() {
        return this.m_port;
    }

    public String getRegBasedAuthority() {
        return this.m_regAuthority;
    }

    public String getAuthority() {
        StringBuffer authority = new StringBuffer();
        if (!(this.m_host == null && this.m_regAuthority == null)) {
            authority.append("//");
            if (this.m_host != null) {
                if (this.m_userinfo != null) {
                    authority.append(this.m_userinfo);
                    authority.append('@');
                }
                authority.append(this.m_host);
                if (this.m_port != -1) {
                    authority.append(':');
                    authority.append(this.m_port);
                }
            } else {
                authority.append(this.m_regAuthority);
            }
        }
        return authority.toString();
    }

    public String getPath(boolean p_includeQueryString, boolean p_includeFragment) {
        StringBuffer pathString = new StringBuffer(this.m_path);
        if (p_includeQueryString && this.m_queryString != null) {
            pathString.append('?');
            pathString.append(this.m_queryString);
        }
        if (p_includeFragment && this.m_fragment != null) {
            pathString.append('#');
            pathString.append(this.m_fragment);
        }
        return pathString.toString();
    }

    public String getPath() {
        return this.m_path;
    }

    public String getQueryString() {
        return this.m_queryString;
    }

    public String getFragment() {
        return this.m_fragment;
    }

    public void setScheme(String p_scheme) throws MalformedURIException {
        if (p_scheme == null) {
            throw new MalformedURIException("Cannot set scheme from null string!");
        } else if (isConformantSchemeName(p_scheme)) {
            this.m_scheme = p_scheme.toLowerCase(Locale.ENGLISH);
        } else {
            throw new MalformedURIException("The scheme is not conformant.");
        }
    }

    public void setUserinfo(String p_userinfo) throws MalformedURIException {
        if (p_userinfo == null) {
            this.m_userinfo = null;
        } else if (this.m_host == null) {
            throw new MalformedURIException("Userinfo cannot be set when host is null!");
        } else {
            int index = 0;
            int end = p_userinfo.length();
            while (index < end) {
                char testChar = p_userinfo.charAt(index);
                if (testChar == '%') {
                    if (index + MARK_CHARACTERS >= end || !isHex(p_userinfo.charAt(index + RESERVED_CHARACTERS)) || !isHex(p_userinfo.charAt(index + MARK_CHARACTERS))) {
                        throw new MalformedURIException("Userinfo contains invalid escape sequence!");
                    }
                } else if (!isUserinfoCharacter(testChar)) {
                    throw new MalformedURIException("Userinfo contains invalid character:" + testChar);
                }
                index += RESERVED_CHARACTERS;
            }
            this.m_userinfo = p_userinfo;
        }
    }

    public void setHost(String p_host) throws MalformedURIException {
        if (p_host == null || p_host.length() == 0) {
            if (p_host != null) {
                this.m_regAuthority = null;
            }
            this.m_host = p_host;
            this.m_userinfo = null;
            this.m_port = -1;
        } else if (isWellFormedAddress(p_host)) {
            this.m_host = p_host;
            this.m_regAuthority = null;
        } else {
            throw new MalformedURIException("Host is not a well formed address!");
        }
    }

    public void setPort(int p_port) throws MalformedURIException {
        if (p_port < 0 || p_port > SupportMenu.USER_MASK) {
            if (p_port != -1) {
                throw new MalformedURIException("Invalid port number!");
            }
        } else if (this.m_host == null) {
            throw new MalformedURIException("Port cannot be set when host is null!");
        }
        this.m_port = p_port;
    }

    public void setRegBasedAuthority(String authority) throws MalformedURIException {
        if (authority == null) {
            this.m_regAuthority = null;
        } else if (authority.length() >= RESERVED_CHARACTERS && isValidRegistryBasedAuthority(authority) && authority.indexOf(47) == -1) {
            this.m_regAuthority = authority;
            this.m_host = null;
            this.m_userinfo = null;
            this.m_port = -1;
        } else {
            throw new MalformedURIException("Registry based authority is not well formed.");
        }
    }

    public void setPath(String p_path) throws MalformedURIException {
        if (p_path == null) {
            this.m_path = null;
            this.m_queryString = null;
            this.m_fragment = null;
            return;
        }
        initializePath(p_path, 0);
    }

    public void appendPath(String p_addToPath) throws MalformedURIException {
        if (p_addToPath != null && p_addToPath.trim().length() != 0) {
            if (!isURIString(p_addToPath)) {
                throw new MalformedURIException("Path contains invalid character!");
            } else if (this.m_path == null || this.m_path.trim().length() == 0) {
                if (p_addToPath.startsWith("/")) {
                    this.m_path = p_addToPath;
                } else {
                    this.m_path = "/" + p_addToPath;
                }
            } else if (this.m_path.endsWith("/")) {
                if (p_addToPath.startsWith("/")) {
                    this.m_path = this.m_path.concat(p_addToPath.substring(RESERVED_CHARACTERS));
                } else {
                    this.m_path = this.m_path.concat(p_addToPath);
                }
            } else if (p_addToPath.startsWith("/")) {
                this.m_path = this.m_path.concat(p_addToPath);
            } else {
                this.m_path = this.m_path.concat("/" + p_addToPath);
            }
        }
    }

    public void setQueryString(String p_queryString) throws MalformedURIException {
        if (p_queryString == null) {
            this.m_queryString = null;
        } else if (!isGenericURI()) {
            throw new MalformedURIException("Query string can only be set for a generic URI!");
        } else if (getPath() == null) {
            throw new MalformedURIException("Query string cannot be set when path is null!");
        } else if (isURIString(p_queryString)) {
            this.m_queryString = p_queryString;
        } else {
            throw new MalformedURIException("Query string contains invalid character!");
        }
    }

    public void setFragment(String p_fragment) throws MalformedURIException {
        if (p_fragment == null) {
            this.m_fragment = null;
        } else if (!isGenericURI()) {
            throw new MalformedURIException("Fragment can only be set for a generic URI!");
        } else if (getPath() == null) {
            throw new MalformedURIException("Fragment cannot be set when path is null!");
        } else if (isURIString(p_fragment)) {
            this.m_fragment = p_fragment;
        } else {
            throw new MalformedURIException("Fragment contains invalid character!");
        }
    }

    public boolean equals(Object p_test) {
        if (p_test instanceof URI) {
            URI testURI = (URI) p_test;
            if (((this.m_scheme == null && testURI.m_scheme == null) || !(this.m_scheme == null || testURI.m_scheme == null || !this.m_scheme.equals(testURI.m_scheme))) && (((this.m_userinfo == null && testURI.m_userinfo == null) || !(this.m_userinfo == null || testURI.m_userinfo == null || !this.m_userinfo.equals(testURI.m_userinfo))) && (((this.m_host == null && testURI.m_host == null) || !(this.m_host == null || testURI.m_host == null || !this.m_host.equals(testURI.m_host))) && this.m_port == testURI.m_port && (((this.m_path == null && testURI.m_path == null) || !(this.m_path == null || testURI.m_path == null || !this.m_path.equals(testURI.m_path))) && (((this.m_queryString == null && testURI.m_queryString == null) || !(this.m_queryString == null || testURI.m_queryString == null || !this.m_queryString.equals(testURI.m_queryString))) && ((this.m_fragment == null && testURI.m_fragment == null) || !(this.m_fragment == null || testURI.m_fragment == null || !this.m_fragment.equals(testURI.m_fragment)))))))) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuffer uriSpecString = new StringBuffer();
        if (this.m_scheme != null) {
            uriSpecString.append(this.m_scheme);
            uriSpecString.append(':');
        }
        uriSpecString.append(getSchemeSpecificPart());
        return uriSpecString.toString();
    }

    public boolean isGenericURI() {
        return this.m_host != null;
    }

    public boolean isAbsoluteURI() {
        return this.m_scheme != null;
    }

    public static boolean isConformantSchemeName(String p_scheme) {
        if (p_scheme == null || p_scheme.trim().length() == 0 || !isAlpha(p_scheme.charAt(0))) {
            return false;
        }
        int schemeLength = p_scheme.length();
        for (int i = RESERVED_CHARACTERS; i < schemeLength; i += RESERVED_CHARACTERS) {
            if (!isSchemeCharacter(p_scheme.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWellFormedAddress(String address) {
        if (address == null) {
            return false;
        }
        int addrLength = address.length();
        if (addrLength == 0) {
            return false;
        }
        if (address.startsWith("[")) {
            return isWellFormedIPv6Reference(address);
        }
        if (address.startsWith(".") || address.startsWith("-") || address.endsWith("-")) {
            return false;
        }
        int index = address.lastIndexOf(46);
        if (address.endsWith(".")) {
            index = address.substring(0, index).lastIndexOf(46);
        }
        if (index + RESERVED_CHARACTERS < addrLength && isDigit(address.charAt(index + RESERVED_CHARACTERS))) {
            return isWellFormedIPv4Address(address);
        }
        if (addrLength > MotionEventCompat.ACTION_MASK) {
            return false;
        }
        int labelCharCount = 0;
        int i = 0;
        while (i < addrLength) {
            char testChar = address.charAt(i);
            if (testChar == ClassUtils.PACKAGE_SEPARATOR_CHAR) {
                if (!isAlphanum(address.charAt(i - 1))) {
                    return false;
                }
                if (i + RESERVED_CHARACTERS < addrLength && !isAlphanum(address.charAt(i + RESERVED_CHARACTERS))) {
                    return false;
                }
                labelCharCount = 0;
            } else if (!isAlphanum(testChar) && testChar != '-') {
                return false;
            } else {
                labelCharCount += RESERVED_CHARACTERS;
                if (labelCharCount > 63) {
                    return false;
                }
            }
            i += RESERVED_CHARACTERS;
        }
        return true;
    }

    public static boolean isWellFormedIPv4Address(String address) {
        int addrLength = address.length();
        int numDots = 0;
        int numDigits = 0;
        int i = 0;
        while (i < addrLength) {
            char testChar = address.charAt(i);
            if (testChar == ClassUtils.PACKAGE_SEPARATOR_CHAR) {
                if (i > 0 && !isDigit(address.charAt(i - 1))) {
                    return false;
                }
                if (i + RESERVED_CHARACTERS < addrLength && !isDigit(address.charAt(i + RESERVED_CHARACTERS))) {
                    return false;
                }
                numDigits = 0;
                numDots += RESERVED_CHARACTERS;
                if (numDots > 3) {
                    return false;
                }
            } else if (!isDigit(testChar)) {
                return false;
            } else {
                numDigits += RESERVED_CHARACTERS;
                if (numDigits > 3) {
                    return false;
                }
                if (numDigits == 3) {
                    char first = address.charAt(i - 2);
                    char second = address.charAt(i - 1);
                    if (first < '2') {
                        continue;
                    } else if (first != '2') {
                        return false;
                    } else {
                        if (second < '5') {
                            continue;
                        } else if (second != '5') {
                            return false;
                        } else {
                            if (testChar > '5') {
                                return false;
                            }
                        }
                    }
                } else {
                    continue;
                }
            }
            i += RESERVED_CHARACTERS;
        }
        if (numDots == 3) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isWellFormedIPv6Reference(java.lang.String r12) {
        /*
        r11 = 58;
        r10 = 8;
        r9 = -1;
        r7 = 1;
        r6 = 0;
        r0 = r12.length();
        r3 = 1;
        r2 = r0 + -1;
        r5 = 2;
        if (r0 <= r5) goto L_0x0021;
    L_0x0011:
        r5 = r12.charAt(r6);
        r8 = 91;
        if (r5 != r8) goto L_0x0021;
    L_0x0019:
        r5 = r12.charAt(r2);
        r8 = 93;
        if (r5 == r8) goto L_0x0023;
    L_0x0021:
        r5 = r6;
    L_0x0022:
        return r5;
    L_0x0023:
        r1 = new int[r7];
        r3 = scanHexSequence(r12, r3, r2, r1);
        if (r3 != r9) goto L_0x002d;
    L_0x002b:
        r5 = r6;
        goto L_0x0022;
    L_0x002d:
        if (r3 != r2) goto L_0x0037;
    L_0x002f:
        r5 = r1[r6];
        if (r5 != r10) goto L_0x0035;
    L_0x0033:
        r5 = r7;
        goto L_0x0022;
    L_0x0035:
        r5 = r6;
        goto L_0x0022;
    L_0x0037:
        r5 = r3 + 1;
        if (r5 >= r2) goto L_0x006e;
    L_0x003b:
        r5 = r12.charAt(r3);
        if (r5 != r11) goto L_0x006e;
    L_0x0041:
        r5 = r3 + 1;
        r5 = r12.charAt(r5);
        if (r5 != r11) goto L_0x0059;
    L_0x0049:
        r5 = r1[r6];
        r5 = r5 + 1;
        r1[r6] = r5;
        if (r5 <= r10) goto L_0x0053;
    L_0x0051:
        r5 = r6;
        goto L_0x0022;
    L_0x0053:
        r3 = r3 + 2;
        if (r3 != r2) goto L_0x0070;
    L_0x0057:
        r5 = r7;
        goto L_0x0022;
    L_0x0059:
        r5 = r1[r6];
        r8 = 6;
        if (r5 != r8) goto L_0x006c;
    L_0x005e:
        r5 = r3 + 1;
        r5 = r12.substring(r5, r2);
        r5 = isWellFormedIPv4Address(r5);
        if (r5 == 0) goto L_0x006c;
    L_0x006a:
        r5 = r7;
        goto L_0x0022;
    L_0x006c:
        r5 = r6;
        goto L_0x0022;
    L_0x006e:
        r5 = r6;
        goto L_0x0022;
    L_0x0070:
        r4 = r1[r6];
        r3 = scanHexSequence(r12, r3, r2, r1);
        if (r3 == r2) goto L_0x008e;
    L_0x0078:
        if (r3 == r9) goto L_0x008a;
    L_0x007a:
        r5 = r1[r6];
        if (r5 <= r4) goto L_0x008c;
    L_0x007e:
        r5 = r3 + 1;
    L_0x0080:
        r5 = r12.substring(r5, r2);
        r5 = isWellFormedIPv4Address(r5);
        if (r5 != 0) goto L_0x008e;
    L_0x008a:
        r5 = r6;
        goto L_0x0022;
    L_0x008c:
        r5 = r3;
        goto L_0x0080;
    L_0x008e:
        r5 = r7;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.util.URI.isWellFormedIPv6Reference(java.lang.String):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int scanHexSequence(java.lang.String r10, int r11, int r12, int[] r13) {
        /*
        r9 = 58;
        r8 = 8;
        r7 = 4;
        r6 = 0;
        r4 = -1;
        r1 = 0;
        r2 = r11;
    L_0x0009:
        if (r11 < r12) goto L_0x0017;
    L_0x000b:
        if (r1 <= 0) goto L_0x005e;
    L_0x000d:
        r5 = r13[r6];
        r5 = r5 + 1;
        r13[r6] = r5;
        if (r5 > r8) goto L_0x005e;
    L_0x0015:
        r4 = r12;
    L_0x0016:
        return r4;
    L_0x0017:
        r3 = r10.charAt(r11);
        if (r3 != r9) goto L_0x003b;
    L_0x001d:
        if (r1 <= 0) goto L_0x0027;
    L_0x001f:
        r5 = r13[r6];
        r5 = r5 + 1;
        r13[r6] = r5;
        if (r5 > r8) goto L_0x0016;
    L_0x0027:
        if (r1 == 0) goto L_0x0035;
    L_0x0029:
        r5 = r11 + 1;
        if (r5 >= r12) goto L_0x0037;
    L_0x002d:
        r5 = r11 + 1;
        r5 = r10.charAt(r5);
        if (r5 != r9) goto L_0x0037;
    L_0x0035:
        r4 = r11;
        goto L_0x0016;
    L_0x0037:
        r1 = 0;
    L_0x0038:
        r11 = r11 + 1;
        goto L_0x0009;
    L_0x003b:
        r5 = isHex(r3);
        if (r5 != 0) goto L_0x0059;
    L_0x0041:
        r5 = 46;
        if (r3 != r5) goto L_0x0016;
    L_0x0045:
        if (r1 >= r7) goto L_0x0016;
    L_0x0047:
        if (r1 <= 0) goto L_0x0016;
    L_0x0049:
        r5 = r13[r6];
        r6 = 6;
        if (r5 > r6) goto L_0x0016;
    L_0x004e:
        r4 = r11 - r1;
        r0 = r4 + -1;
        if (r0 < r2) goto L_0x0056;
    L_0x0054:
        r4 = r0;
        goto L_0x0016;
    L_0x0056:
        r0 = r0 + 1;
        goto L_0x0054;
    L_0x0059:
        r1 = r1 + 1;
        if (r1 <= r7) goto L_0x0038;
    L_0x005d:
        goto L_0x0016;
    L_0x005e:
        r12 = r4;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.util.URI.scanHexSequence(java.lang.String, int, int, int[]):int");
    }

    private static boolean isDigit(char p_char) {
        return p_char >= '0' && p_char <= '9';
    }

    private static boolean isHex(char p_char) {
        return p_char <= 'f' && (fgLookupTable[p_char] & ASCII_HEX_CHARACTERS) != 0;
    }

    private static boolean isAlpha(char p_char) {
        return (p_char >= 'a' && p_char <= 'z') || (p_char >= 'A' && p_char <= 'Z');
    }

    private static boolean isAlphanum(char p_char) {
        return p_char <= 'z' && (fgLookupTable[p_char] & MASK_ALPHA_NUMERIC) != 0;
    }

    private static boolean isReservedCharacter(char p_char) {
        return p_char <= ']' && (fgLookupTable[p_char] & RESERVED_CHARACTERS) != 0;
    }

    private static boolean isUnreservedCharacter(char p_char) {
        return p_char <= '~' && (fgLookupTable[p_char] & MASK_UNRESERVED_MASK) != 0;
    }

    private static boolean isURICharacter(char p_char) {
        return p_char <= '~' && (fgLookupTable[p_char] & MASK_URI_CHARACTER) != 0;
    }

    private static boolean isSchemeCharacter(char p_char) {
        return p_char <= 'z' && (fgLookupTable[p_char] & MASK_SCHEME_CHARACTER) != 0;
    }

    private static boolean isUserinfoCharacter(char p_char) {
        return p_char <= 'z' && (fgLookupTable[p_char] & MASK_USERINFO_CHARACTER) != 0;
    }

    private static boolean isPathCharacter(char p_char) {
        return p_char <= '~' && (fgLookupTable[p_char] & MASK_PATH_CHARACTER) != 0;
    }

    private static boolean isURIString(String p_uric) {
        if (p_uric == null) {
            return false;
        }
        int end = p_uric.length();
        int i = 0;
        while (i < end) {
            char testChar = p_uric.charAt(i);
            if (testChar == '%') {
                if (i + MARK_CHARACTERS >= end || !isHex(p_uric.charAt(i + RESERVED_CHARACTERS)) || !isHex(p_uric.charAt(i + MARK_CHARACTERS))) {
                    return false;
                }
                i += MARK_CHARACTERS;
            } else if (!isURICharacter(testChar)) {
                return false;
            }
            i += RESERVED_CHARACTERS;
        }
        return true;
    }
}
