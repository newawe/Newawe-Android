package org.apache.commons.lang;

import com.astuetz.pagerslidingtabstrip.C0302R;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.http.HttpStatus;

public final class NumberUtils {
    public static int stringToInt(String str) {
        return stringToInt(str, 0);
    }

    public static int stringToInt(String str, int defaultValue) {
        try {
            defaultValue = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return defaultValue;
    }

    public static Number createNumber(String val) throws NumberFormatException {
        if (val == null) {
            return null;
        }
        if (val.length() == 0) {
            throw new NumberFormatException("\"\" is not a valid number.");
        } else if (val.length() == 1 && !Character.isDigit(val.charAt(0))) {
            throw new NumberFormatException(new StringBuffer().append(val).append(" is not a valid number.").toString());
        } else if (val.startsWith("--")) {
            return null;
        } else {
            if (val.startsWith("0x") || val.startsWith("-0x")) {
                return createInteger(val);
            }
            String dec;
            String mant;
            char lastChar = val.charAt(val.length() - 1);
            int decPos = val.indexOf(46);
            int expPos = (val.indexOf(HttpStatus.SC_SWITCHING_PROTOCOLS) + val.indexOf(69)) + 1;
            if (decPos > -1) {
                if (expPos <= -1) {
                    dec = val.substring(decPos + 1);
                } else if (expPos < decPos) {
                    throw new NumberFormatException(new StringBuffer().append(val).append(" is not a valid number.").toString());
                } else {
                    dec = val.substring(decPos + 1, expPos);
                }
                mant = val.substring(0, decPos);
            } else {
                if (expPos > -1) {
                    mant = val.substring(0, expPos);
                } else {
                    mant = val;
                }
                dec = null;
            }
            String exp;
            boolean allZeros;
            Number f;
            Number d;
            if (Character.isDigit(lastChar)) {
                if (expPos <= -1 || expPos >= val.length() - 1) {
                    exp = null;
                } else {
                    exp = val.substring(expPos + 1, val.length());
                }
                if (dec == null && exp == null) {
                    try {
                        return createInteger(val);
                    } catch (NumberFormatException e) {
                        try {
                            return createLong(val);
                        } catch (NumberFormatException e2) {
                            return createBigInteger(val);
                        }
                    }
                }
                allZeros = isAllZeros(mant) && isAllZeros(exp);
                try {
                    f = createFloat(val);
                    if (!f.isInfinite() && (f.floatValue() != 0.0f || allZeros)) {
                        return f;
                    }
                } catch (NumberFormatException e3) {
                }
                try {
                    d = createDouble(val);
                    if (!d.isInfinite() && (d.doubleValue() != 0.0d || allZeros)) {
                        return d;
                    }
                } catch (NumberFormatException e4) {
                }
                return createBigDecimal(val);
            }
            if (expPos <= -1 || expPos >= val.length() - 1) {
                exp = null;
            } else {
                exp = val.substring(expPos + 1, val.length() - 1);
            }
            String numeric = val.substring(0, val.length() - 1);
            allZeros = isAllZeros(mant) && isAllZeros(exp);
            switch (lastChar) {
                case C0302R.styleable.Theme_searchViewStyle /*68*/:
                case HttpStatus.SC_CONTINUE /*100*/:
                    break;
                case C0302R.styleable.Theme_listPreferredItemHeightSmall /*70*/:
                case HttpStatus.SC_PROCESSING /*102*/:
                    try {
                        f = createFloat(numeric);
                        if (!f.isInfinite() && (f.floatValue() != 0.0f || allZeros)) {
                            return f;
                        }
                    } catch (NumberFormatException e5) {
                        break;
                    }
                case C0302R.styleable.Theme_textAppearanceListItem /*76*/:
                case C0302R.styleable.Theme_spinnerStyle /*108*/:
                    if (dec == null && exp == null && ((numeric.charAt(0) == '-' && isDigits(numeric.substring(1))) || isDigits(numeric))) {
                        try {
                            return createLong(numeric);
                        } catch (NumberFormatException e6) {
                            return createBigInteger(numeric);
                        }
                    }
                    throw new NumberFormatException(new StringBuffer().append(val).append(" is not a valid number.").toString());
            }
            try {
                d = createDouble(numeric);
                if (!d.isInfinite() && (((double) d.floatValue()) != 0.0d || allZeros)) {
                    return d;
                }
            } catch (NumberFormatException e7) {
            }
            try {
                return createBigDecimal(numeric);
            } catch (NumberFormatException e8) {
                throw new NumberFormatException(new StringBuffer().append(val).append(" is not a valid number.").toString());
            }
        }
    }

    private static boolean isAllZeros(String s) {
        if (s == null) {
            return true;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                return false;
            }
        }
        if (s.length() <= 0) {
            return false;
        }
        return true;
    }

    public static Float createFloat(String val) {
        return Float.valueOf(val);
    }

    public static Double createDouble(String val) {
        return Double.valueOf(val);
    }

    public static Integer createInteger(String val) {
        return Integer.decode(val);
    }

    public static Long createLong(String val) {
        return Long.valueOf(val);
    }

    public static BigInteger createBigInteger(String val) {
        return new BigInteger(val);
    }

    public static BigDecimal createBigDecimal(String val) {
        return new BigDecimal(val);
    }

    public static long minimum(long a, long b, long c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static int minimum(int a, int b, int c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static long maximum(long a, long b, long c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static int maximum(int a, int b, int c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static int compare(double lhs, double rhs) {
        if (lhs < rhs) {
            return -1;
        }
        if (lhs > rhs) {
            return 1;
        }
        long lhsBits = Double.doubleToLongBits(lhs);
        long rhsBits = Double.doubleToLongBits(rhs);
        if (lhsBits == rhsBits) {
            return 0;
        }
        if (lhsBits >= rhsBits) {
            return 1;
        }
        return -1;
    }

    public static int compare(float lhs, float rhs) {
        if (lhs < rhs) {
            return -1;
        }
        if (lhs > rhs) {
            return 1;
        }
        int lhsBits = Float.floatToIntBits(lhs);
        int rhsBits = Float.floatToIntBits(rhs);
        if (lhsBits == rhsBits) {
            return 0;
        }
        if (lhsBits >= rhsBits) {
            return 1;
        }
        return -1;
    }

    public static boolean isDigits(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isNumber(java.lang.String r15) {
        /*
        r14 = 45;
        r13 = 57;
        r12 = 48;
        r8 = 1;
        r9 = 0;
        r10 = org.apache.commons.lang.StringUtils.isEmpty(r15);
        if (r10 == 0) goto L_0x000f;
    L_0x000e:
        return r9;
    L_0x000f:
        r1 = r15.toCharArray();
        r7 = r1.length;
        r4 = 0;
        r3 = 0;
        r0 = 0;
        r2 = 0;
        r10 = r1[r9];
        if (r10 != r14) goto L_0x0057;
    L_0x001c:
        r6 = r8;
    L_0x001d:
        r10 = r6 + 1;
        if (r7 <= r10) goto L_0x005b;
    L_0x0021:
        r10 = r1[r6];
        if (r10 != r12) goto L_0x005b;
    L_0x0025:
        r10 = r6 + 1;
        r10 = r1[r10];
        r11 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        if (r10 != r11) goto L_0x005b;
    L_0x002d:
        r5 = r6 + 2;
        if (r5 == r7) goto L_0x000e;
    L_0x0031:
        r10 = r1.length;
        if (r5 >= r10) goto L_0x0059;
    L_0x0034:
        r10 = r1[r5];
        if (r10 < r12) goto L_0x003c;
    L_0x0038:
        r10 = r1[r5];
        if (r10 <= r13) goto L_0x0054;
    L_0x003c:
        r10 = r1[r5];
        r11 = 97;
        if (r10 < r11) goto L_0x0048;
    L_0x0042:
        r10 = r1[r5];
        r11 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r10 <= r11) goto L_0x0054;
    L_0x0048:
        r10 = r1[r5];
        r11 = 65;
        if (r10 < r11) goto L_0x000e;
    L_0x004e:
        r10 = r1[r5];
        r11 = 70;
        if (r10 > r11) goto L_0x000e;
    L_0x0054:
        r5 = r5 + 1;
        goto L_0x0031;
    L_0x0057:
        r6 = r9;
        goto L_0x001d;
    L_0x0059:
        r9 = r8;
        goto L_0x000e;
    L_0x005b:
        r7 = r7 + -1;
        r5 = r6;
    L_0x005e:
        if (r5 < r7) goto L_0x0068;
    L_0x0060:
        r10 = r7 + 1;
        if (r5 >= r10) goto L_0x00a3;
    L_0x0064:
        if (r0 == 0) goto L_0x00a3;
    L_0x0066:
        if (r2 != 0) goto L_0x00a3;
    L_0x0068:
        r10 = r1[r5];
        if (r10 < r12) goto L_0x0075;
    L_0x006c:
        r10 = r1[r5];
        if (r10 > r13) goto L_0x0075;
    L_0x0070:
        r2 = 1;
        r0 = 0;
    L_0x0072:
        r5 = r5 + 1;
        goto L_0x005e;
    L_0x0075:
        r10 = r1[r5];
        r11 = 46;
        if (r10 != r11) goto L_0x0081;
    L_0x007b:
        if (r3 != 0) goto L_0x000e;
    L_0x007d:
        if (r4 != 0) goto L_0x000e;
    L_0x007f:
        r3 = 1;
        goto L_0x0072;
    L_0x0081:
        r10 = r1[r5];
        r11 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r10 == r11) goto L_0x008d;
    L_0x0087:
        r10 = r1[r5];
        r11 = 69;
        if (r10 != r11) goto L_0x0094;
    L_0x008d:
        if (r4 != 0) goto L_0x000e;
    L_0x008f:
        if (r2 == 0) goto L_0x000e;
    L_0x0091:
        r4 = 1;
        r0 = 1;
        goto L_0x0072;
    L_0x0094:
        r10 = r1[r5];
        r11 = 43;
        if (r10 == r11) goto L_0x009e;
    L_0x009a:
        r10 = r1[r5];
        if (r10 != r14) goto L_0x000e;
    L_0x009e:
        if (r0 == 0) goto L_0x000e;
    L_0x00a0:
        r0 = 0;
        r2 = 0;
        goto L_0x0072;
    L_0x00a3:
        r10 = r1.length;
        if (r5 >= r10) goto L_0x00ef;
    L_0x00a6:
        r10 = r1[r5];
        if (r10 < r12) goto L_0x00b1;
    L_0x00aa:
        r10 = r1[r5];
        if (r10 > r13) goto L_0x00b1;
    L_0x00ae:
        r9 = r8;
        goto L_0x000e;
    L_0x00b1:
        r10 = r1[r5];
        r11 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r10 == r11) goto L_0x000e;
    L_0x00b7:
        r10 = r1[r5];
        r11 = 69;
        if (r10 == r11) goto L_0x000e;
    L_0x00bd:
        if (r0 != 0) goto L_0x00da;
    L_0x00bf:
        r10 = r1[r5];
        r11 = 100;
        if (r10 == r11) goto L_0x00d7;
    L_0x00c5:
        r10 = r1[r5];
        r11 = 68;
        if (r10 == r11) goto L_0x00d7;
    L_0x00cb:
        r10 = r1[r5];
        r11 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r10 == r11) goto L_0x00d7;
    L_0x00d1:
        r10 = r1[r5];
        r11 = 70;
        if (r10 != r11) goto L_0x00da;
    L_0x00d7:
        r9 = r2;
        goto L_0x000e;
    L_0x00da:
        r10 = r1[r5];
        r11 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        if (r10 == r11) goto L_0x00e6;
    L_0x00e0:
        r10 = r1[r5];
        r11 = 76;
        if (r10 != r11) goto L_0x000e;
    L_0x00e6:
        if (r2 == 0) goto L_0x00ed;
    L_0x00e8:
        if (r4 != 0) goto L_0x00ed;
    L_0x00ea:
        r9 = r8;
        goto L_0x000e;
    L_0x00ed:
        r8 = r9;
        goto L_0x00ea;
    L_0x00ef:
        if (r0 != 0) goto L_0x00f6;
    L_0x00f1:
        if (r2 == 0) goto L_0x00f6;
    L_0x00f3:
        r9 = r8;
        goto L_0x000e;
    L_0x00f6:
        r8 = r9;
        goto L_0x00f3;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.NumberUtils.isNumber(java.lang.String):boolean");
    }
}
