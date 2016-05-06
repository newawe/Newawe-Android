package mf.org.apache.xerces.util;

import android.support.v4.media.TransportMediator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Arrays;
import mf.org.apache.xerces.impl.io.UCSReader;

public class XML11Char {
    public static final int MASK_XML11_CONTENT = 32;
    public static final int MASK_XML11_CONTENT_INTERNAL = 48;
    public static final int MASK_XML11_CONTROL = 16;
    public static final int MASK_XML11_NAME = 8;
    public static final int MASK_XML11_NAME_START = 4;
    public static final int MASK_XML11_NCNAME = 128;
    public static final int MASK_XML11_NCNAME_START = 64;
    public static final int MASK_XML11_SPACE = 2;
    public static final int MASK_XML11_VALID = 1;
    private static final byte[] XML11CHARS;

    static {
        XML11CHARS = new byte[AccessibilityNodeInfoCompat.ACTION_CUT];
        Arrays.fill(XML11CHARS, MASK_XML11_VALID, 9, (byte) 17);
        XML11CHARS[9] = (byte) 35;
        XML11CHARS[10] = (byte) 3;
        Arrays.fill(XML11CHARS, 11, 13, (byte) 17);
        XML11CHARS[13] = (byte) 3;
        Arrays.fill(XML11CHARS, 14, MASK_XML11_CONTENT, (byte) 17);
        XML11CHARS[MASK_XML11_CONTENT] = (byte) 35;
        Arrays.fill(XML11CHARS, 33, 38, (byte) 33);
        XML11CHARS[38] = (byte) 1;
        Arrays.fill(XML11CHARS, 39, 45, (byte) 33);
        Arrays.fill(XML11CHARS, 45, 47, (byte) -87);
        XML11CHARS[47] = (byte) 33;
        Arrays.fill(XML11CHARS, MASK_XML11_CONTENT_INTERNAL, 58, (byte) -87);
        XML11CHARS[58] = (byte) 45;
        XML11CHARS[59] = (byte) 33;
        XML11CHARS[60] = (byte) 1;
        Arrays.fill(XML11CHARS, 61, 65, (byte) 33);
        Arrays.fill(XML11CHARS, 65, 91, (byte) -19);
        Arrays.fill(XML11CHARS, 91, 93, (byte) 33);
        XML11CHARS[93] = (byte) 1;
        XML11CHARS[94] = (byte) 33;
        XML11CHARS[95] = (byte) -19;
        XML11CHARS[96] = (byte) 33;
        Arrays.fill(XML11CHARS, 97, 123, (byte) -19);
        Arrays.fill(XML11CHARS, 123, TransportMediator.KEYCODE_MEDIA_PAUSE, (byte) 33);
        Arrays.fill(XML11CHARS, TransportMediator.KEYCODE_MEDIA_PAUSE, 133, (byte) 17);
        XML11CHARS[133] = (byte) 35;
        Arrays.fill(XML11CHARS, 134, 160, (byte) 17);
        Arrays.fill(XML11CHARS, 160, 183, (byte) 33);
        XML11CHARS[183] = (byte) -87;
        Arrays.fill(XML11CHARS, 184, 192, (byte) 33);
        Arrays.fill(XML11CHARS, 192, 215, (byte) -19);
        XML11CHARS[215] = (byte) 33;
        Arrays.fill(XML11CHARS, 216, 247, (byte) -19);
        XML11CHARS[247] = (byte) 33;
        Arrays.fill(XML11CHARS, 248, 768, (byte) -19);
        Arrays.fill(XML11CHARS, 768, 880, (byte) -87);
        Arrays.fill(XML11CHARS, 880, 894, (byte) -19);
        XML11CHARS[894] = (byte) 33;
        Arrays.fill(XML11CHARS, 895, UCSReader.DEFAULT_BUFFER_SIZE, (byte) -19);
        Arrays.fill(XML11CHARS, UCSReader.DEFAULT_BUFFER_SIZE, 8204, (byte) 33);
        Arrays.fill(XML11CHARS, 8204, 8206, (byte) -19);
        Arrays.fill(XML11CHARS, 8206, 8232, (byte) 33);
        XML11CHARS[8232] = (byte) 35;
        Arrays.fill(XML11CHARS, 8233, 8255, (byte) 33);
        Arrays.fill(XML11CHARS, 8255, 8257, (byte) -87);
        Arrays.fill(XML11CHARS, 8257, 8304, (byte) 33);
        Arrays.fill(XML11CHARS, 8304, 8592, (byte) -19);
        Arrays.fill(XML11CHARS, 8592, 11264, (byte) 33);
        Arrays.fill(XML11CHARS, 11264, 12272, (byte) -19);
        Arrays.fill(XML11CHARS, 12272, 12289, (byte) 33);
        Arrays.fill(XML11CHARS, 12289, 55296, (byte) -19);
        Arrays.fill(XML11CHARS, 57344, 63744, (byte) 33);
        Arrays.fill(XML11CHARS, 63744, 64976, (byte) -19);
        Arrays.fill(XML11CHARS, 64976, 65008, (byte) 33);
        Arrays.fill(XML11CHARS, 65008, 65534, (byte) -19);
    }

    public static boolean isXML11Space(int c) {
        return c < AccessibilityNodeInfoCompat.ACTION_CUT && (XML11CHARS[c] & MASK_XML11_SPACE) != 0;
    }

    public static boolean isXML11Valid(int c) {
        return (c < AccessibilityNodeInfoCompat.ACTION_CUT && (XML11CHARS[c] & MASK_XML11_VALID) != 0) || (AccessibilityNodeInfoCompat.ACTION_CUT <= c && c <= 1114111);
    }

    public static boolean isXML11Invalid(int c) {
        return !isXML11Valid(c);
    }

    public static boolean isXML11ValidLiteral(int c) {
        return (c < AccessibilityNodeInfoCompat.ACTION_CUT && (XML11CHARS[c] & MASK_XML11_VALID) != 0 && (XML11CHARS[c] & MASK_XML11_CONTROL) == 0) || (AccessibilityNodeInfoCompat.ACTION_CUT <= c && c <= 1114111);
    }

    public static boolean isXML11Content(int c) {
        return (c < AccessibilityNodeInfoCompat.ACTION_CUT && (XML11CHARS[c] & MASK_XML11_CONTENT) != 0) || (AccessibilityNodeInfoCompat.ACTION_CUT <= c && c <= 1114111);
    }

    public static boolean isXML11InternalEntityContent(int c) {
        return (c < AccessibilityNodeInfoCompat.ACTION_CUT && (XML11CHARS[c] & MASK_XML11_CONTENT_INTERNAL) != 0) || (AccessibilityNodeInfoCompat.ACTION_CUT <= c && c <= 1114111);
    }

    public static boolean isXML11NameStart(int c) {
        return (c < AccessibilityNodeInfoCompat.ACTION_CUT && (XML11CHARS[c] & MASK_XML11_NAME_START) != 0) || (AccessibilityNodeInfoCompat.ACTION_CUT <= c && c < 983040);
    }

    public static boolean isXML11Name(int c) {
        return (c < AccessibilityNodeInfoCompat.ACTION_CUT && (XML11CHARS[c] & MASK_XML11_NAME) != 0) || (c >= AccessibilityNodeInfoCompat.ACTION_CUT && c < 983040);
    }

    public static boolean isXML11NCNameStart(int c) {
        return (c < AccessibilityNodeInfoCompat.ACTION_CUT && (XML11CHARS[c] & MASK_XML11_NCNAME_START) != 0) || (AccessibilityNodeInfoCompat.ACTION_CUT <= c && c < 983040);
    }

    public static boolean isXML11NCName(int c) {
        return (c < AccessibilityNodeInfoCompat.ACTION_CUT && (XML11CHARS[c] & MASK_XML11_NCNAME) != 0) || (AccessibilityNodeInfoCompat.ACTION_CUT <= c && c < 983040);
    }

    public static boolean isXML11NameHighSurrogate(int c) {
        return 55296 <= c && c <= 56191;
    }

    public static boolean isXML11ValidName(String name) {
        int length = name.length();
        if (length == 0) {
            return false;
        }
        char ch2;
        int i = MASK_XML11_VALID;
        char ch = name.charAt(0);
        if (!isXML11NameStart(ch)) {
            if (length <= MASK_XML11_VALID || !isXML11NameHighSurrogate(ch)) {
                return false;
            }
            ch2 = name.charAt(MASK_XML11_VALID);
            if (!XMLChar.isLowSurrogate(ch2) || !isXML11NameStart(XMLChar.supplemental(ch, ch2))) {
                return false;
            }
            i = MASK_XML11_SPACE;
        }
        while (i < length) {
            ch = name.charAt(i);
            if (!isXML11Name(ch)) {
                i += MASK_XML11_VALID;
                if (i >= length || !isXML11NameHighSurrogate(ch)) {
                    return false;
                }
                ch2 = name.charAt(i);
                if (!XMLChar.isLowSurrogate(ch2) || !isXML11Name(XMLChar.supplemental(ch, ch2))) {
                    return false;
                }
            }
            i += MASK_XML11_VALID;
        }
        return true;
    }

    public static boolean isXML11ValidNCName(String ncName) {
        int length = ncName.length();
        if (length == 0) {
            return false;
        }
        char ch2;
        int i = MASK_XML11_VALID;
        char ch = ncName.charAt(0);
        if (!isXML11NCNameStart(ch)) {
            if (length <= MASK_XML11_VALID || !isXML11NameHighSurrogate(ch)) {
                return false;
            }
            ch2 = ncName.charAt(MASK_XML11_VALID);
            if (!XMLChar.isLowSurrogate(ch2) || !isXML11NCNameStart(XMLChar.supplemental(ch, ch2))) {
                return false;
            }
            i = MASK_XML11_SPACE;
        }
        while (i < length) {
            ch = ncName.charAt(i);
            if (!isXML11NCName(ch)) {
                i += MASK_XML11_VALID;
                if (i >= length || !isXML11NameHighSurrogate(ch)) {
                    return false;
                }
                ch2 = ncName.charAt(i);
                if (!XMLChar.isLowSurrogate(ch2) || !isXML11NCName(XMLChar.supplemental(ch, ch2))) {
                    return false;
                }
            }
            i += MASK_XML11_VALID;
        }
        return true;
    }

    public static boolean isXML11ValidNmtoken(String nmtoken) {
        int length = nmtoken.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        while (i < length) {
            char ch = nmtoken.charAt(i);
            if (!isXML11Name(ch)) {
                i += MASK_XML11_VALID;
                if (i >= length || !isXML11NameHighSurrogate(ch)) {
                    return false;
                }
                char ch2 = nmtoken.charAt(i);
                if (!XMLChar.isLowSurrogate(ch2) || !isXML11Name(XMLChar.supplemental(ch, ch2))) {
                    return false;
                }
            }
            i += MASK_XML11_VALID;
        }
        return true;
    }
}
