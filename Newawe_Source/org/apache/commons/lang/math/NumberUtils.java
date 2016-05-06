package org.apache.commons.lang.math;

import com.android.volley.DefaultRetryPolicy;
import com.astuetz.pagerslidingtabstrip.C0302R;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

public class NumberUtils {
    public static final Byte BYTE_MINUS_ONE;
    public static final Byte BYTE_ONE;
    public static final Byte BYTE_ZERO;
    public static final Double DOUBLE_MINUS_ONE;
    public static final Double DOUBLE_ONE;
    public static final Double DOUBLE_ZERO;
    public static final Float FLOAT_MINUS_ONE;
    public static final Float FLOAT_ONE;
    public static final Float FLOAT_ZERO;
    public static final Integer INTEGER_MINUS_ONE;
    public static final Integer INTEGER_ONE;
    public static final Integer INTEGER_ZERO;
    public static final Long LONG_MINUS_ONE;
    public static final Long LONG_ONE;
    public static final Long LONG_ZERO;
    public static final Short SHORT_MINUS_ONE;
    public static final Short SHORT_ONE;
    public static final Short SHORT_ZERO;

    static {
        LONG_ZERO = new Long(0);
        LONG_ONE = new Long(1);
        LONG_MINUS_ONE = new Long(-1);
        INTEGER_ZERO = new Integer(0);
        INTEGER_ONE = new Integer(1);
        INTEGER_MINUS_ONE = new Integer(-1);
        SHORT_ZERO = new Short((short) 0);
        SHORT_ONE = new Short((short) 1);
        SHORT_MINUS_ONE = new Short((short) -1);
        BYTE_ZERO = new Byte((byte) 0);
        BYTE_ONE = new Byte((byte) 1);
        BYTE_MINUS_ONE = new Byte((byte) -1);
        DOUBLE_ZERO = new Double(0.0d);
        DOUBLE_ONE = new Double(1.0d);
        DOUBLE_MINUS_ONE = new Double(-1.0d);
        FLOAT_ZERO = new Float(0.0f);
        FLOAT_ONE = new Float(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        FLOAT_MINUS_ONE = new Float(-1.0f);
    }

    public static int stringToInt(String str) {
        return toInt(str);
    }

    public static int toInt(String str) {
        return toInt(str, 0);
    }

    public static int stringToInt(String str, int defaultValue) {
        return toInt(str, defaultValue);
    }

    public static int toInt(String str, int defaultValue) {
        if (str != null) {
            try {
                defaultValue = Integer.parseInt(str);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    public static long toLong(String str) {
        return toLong(str, 0);
    }

    public static long toLong(String str, long defaultValue) {
        if (str != null) {
            try {
                defaultValue = Long.parseLong(str);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    public static float toFloat(String str) {
        return toFloat(str, 0.0f);
    }

    public static float toFloat(String str, float defaultValue) {
        if (str != null) {
            try {
                defaultValue = Float.parseFloat(str);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    public static double toDouble(String str) {
        return toDouble(str, 0.0d);
    }

    public static double toDouble(String str, double defaultValue) {
        if (str != null) {
            try {
                defaultValue = Double.parseDouble(str);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    public static byte toByte(String str) {
        return toByte(str, (byte) 0);
    }

    public static byte toByte(String str, byte defaultValue) {
        if (str != null) {
            try {
                defaultValue = Byte.parseByte(str);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    public static short toShort(String str) {
        return toShort(str, (short) 0);
    }

    public static short toShort(String str, short defaultValue) {
        if (str != null) {
            try {
                defaultValue = Short.parseShort(str);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    public static Number createNumber(String str) throws NumberFormatException {
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        } else if (str.startsWith("--")) {
            return null;
        } else {
            if (str.startsWith("0x") || str.startsWith("-0x")) {
                return createInteger(str);
            }
            String dec;
            String mant;
            char lastChar = str.charAt(str.length() - 1);
            int decPos = str.indexOf(46);
            int expPos = (str.indexOf(HttpStatus.SC_SWITCHING_PROTOCOLS) + str.indexOf(69)) + 1;
            if (decPos > -1) {
                if (expPos <= -1) {
                    dec = str.substring(decPos + 1);
                } else if (expPos < decPos || expPos > str.length()) {
                    throw new NumberFormatException(new StringBuffer().append(str).append(" is not a valid number.").toString());
                } else {
                    dec = str.substring(decPos + 1, expPos);
                }
                mant = str.substring(0, decPos);
            } else {
                if (expPos <= -1) {
                    mant = str;
                } else if (expPos > str.length()) {
                    throw new NumberFormatException(new StringBuffer().append(str).append(" is not a valid number.").toString());
                } else {
                    mant = str.substring(0, expPos);
                }
                dec = null;
            }
            String exp;
            boolean allZeros;
            Number f;
            Number d;
            if (Character.isDigit(lastChar) || lastChar == ClassUtils.PACKAGE_SEPARATOR_CHAR) {
                if (expPos <= -1 || expPos >= str.length() - 1) {
                    exp = null;
                } else {
                    exp = str.substring(expPos + 1, str.length());
                }
                if (dec == null && exp == null) {
                    try {
                        return createInteger(str);
                    } catch (NumberFormatException e) {
                        try {
                            return createLong(str);
                        } catch (NumberFormatException e2) {
                            return createBigInteger(str);
                        }
                    }
                }
                allZeros = isAllZeros(mant) && isAllZeros(exp);
                try {
                    f = createFloat(str);
                    if (!f.isInfinite() && (f.floatValue() != 0.0f || allZeros)) {
                        return f;
                    }
                } catch (NumberFormatException e3) {
                }
                try {
                    d = createDouble(str);
                    if (!d.isInfinite() && (d.doubleValue() != 0.0d || allZeros)) {
                        return d;
                    }
                } catch (NumberFormatException e4) {
                }
                return createBigDecimal(str);
            }
            if (expPos <= -1 || expPos >= str.length() - 1) {
                exp = null;
            } else {
                exp = str.substring(expPos + 1, str.length() - 1);
            }
            String numeric = str.substring(0, str.length() - 1);
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
                    throw new NumberFormatException(new StringBuffer().append(str).append(" is not a valid number.").toString());
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
                throw new NumberFormatException(new StringBuffer().append(str).append(" is not a valid number.").toString());
            }
        }
    }

    private static boolean isAllZeros(String str) {
        if (str == null) {
            return true;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        if (str.length() <= 0) {
            return false;
        }
        return true;
    }

    public static Float createFloat(String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    public static Double createDouble(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    public static Integer createInteger(String str) {
        if (str == null) {
            return null;
        }
        return Integer.decode(str);
    }

    public static Long createLong(String str) {
        if (str == null) {
            return null;
        }
        return Long.valueOf(str);
    }

    public static BigInteger createBigInteger(String str) {
        if (str == null) {
            return null;
        }
        return new BigInteger(str);
    }

    public static BigDecimal createBigDecimal(String str) {
        if (str == null) {
            return null;
        }
        if (!StringUtils.isBlank(str)) {
            return new BigDecimal(str);
        }
        throw new NumberFormatException("A blank string is not a valid number");
    }

    public static long min(long[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            long min = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                }
            }
            return min;
        }
    }

    public static int min(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            int min = array[0];
            for (int j = 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                }
            }
            return min;
        }
    }

    public static short min(short[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            short min = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                }
            }
            return min;
        }
    }

    public static byte min(byte[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            byte min = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] < min) {
                    min = array[i];
                }
            }
            return min;
        }
    }

    public static double min(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            double min = array[0];
            for (int i = 1; i < array.length; i++) {
                if (Double.isNaN(array[i])) {
                    return Double.NaN;
                }
                if (array[i] < min) {
                    min = array[i];
                }
            }
            return min;
        }
    }

    public static float min(float[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            float min = array[0];
            for (int i = 1; i < array.length; i++) {
                if (Float.isNaN(array[i])) {
                    return Float.NaN;
                }
                if (array[i] < min) {
                    min = array[i];
                }
            }
            return min;
        }
    }

    public static long max(long[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            long max = array[0];
            for (int j = 1; j < array.length; j++) {
                if (array[j] > max) {
                    max = array[j];
                }
            }
            return max;
        }
    }

    public static int max(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            int max = array[0];
            for (int j = 1; j < array.length; j++) {
                if (array[j] > max) {
                    max = array[j];
                }
            }
            return max;
        }
    }

    public static short max(short[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            short max = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] > max) {
                    max = array[i];
                }
            }
            return max;
        }
    }

    public static byte max(byte[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            byte max = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] > max) {
                    max = array[i];
                }
            }
            return max;
        }
    }

    public static double max(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            double max = array[0];
            for (int j = 1; j < array.length; j++) {
                if (Double.isNaN(array[j])) {
                    return Double.NaN;
                }
                if (array[j] > max) {
                    max = array[j];
                }
            }
            return max;
        }
    }

    public static float max(float[] array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        } else {
            float max = array[0];
            for (int j = 1; j < array.length; j++) {
                if (Float.isNaN(array[j])) {
                    return Float.NaN;
                }
                if (array[j] > max) {
                    max = array[j];
                }
            }
            return max;
        }
    }

    public static long min(long a, long b, long c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static int min(int a, int b, int c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static short min(short a, short b, short c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static byte min(byte a, byte b, byte c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            return c;
        }
        return a;
    }

    public static double min(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }

    public static float min(float a, float b, float c) {
        return Math.min(Math.min(a, b), c);
    }

    public static long max(long a, long b, long c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static int max(int a, int b, int c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static short max(short a, short b, short c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static byte max(byte a, byte b, byte c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            return c;
        }
        return a;
    }

    public static double max(double a, double b, double c) {
        return Math.max(Math.max(a, b), c);
    }

    public static float max(float a, float b, float c) {
        return Math.max(Math.max(a, b), c);
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
        if (StringUtils.isEmpty(str)) {
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
        if (r5 >= r10) goto L_0x00fc;
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
        r10 = r1[r5];
        r11 = 46;
        if (r10 != r11) goto L_0x00ca;
    L_0x00c3:
        if (r3 != 0) goto L_0x000e;
    L_0x00c5:
        if (r4 != 0) goto L_0x000e;
    L_0x00c7:
        r9 = r2;
        goto L_0x000e;
    L_0x00ca:
        if (r0 != 0) goto L_0x00e7;
    L_0x00cc:
        r10 = r1[r5];
        r11 = 100;
        if (r10 == r11) goto L_0x00e4;
    L_0x00d2:
        r10 = r1[r5];
        r11 = 68;
        if (r10 == r11) goto L_0x00e4;
    L_0x00d8:
        r10 = r1[r5];
        r11 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r10 == r11) goto L_0x00e4;
    L_0x00de:
        r10 = r1[r5];
        r11 = 70;
        if (r10 != r11) goto L_0x00e7;
    L_0x00e4:
        r9 = r2;
        goto L_0x000e;
    L_0x00e7:
        r10 = r1[r5];
        r11 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        if (r10 == r11) goto L_0x00f3;
    L_0x00ed:
        r10 = r1[r5];
        r11 = 76;
        if (r10 != r11) goto L_0x000e;
    L_0x00f3:
        if (r2 == 0) goto L_0x00fa;
    L_0x00f5:
        if (r4 != 0) goto L_0x00fa;
    L_0x00f7:
        r9 = r8;
        goto L_0x000e;
    L_0x00fa:
        r8 = r9;
        goto L_0x00f7;
    L_0x00fc:
        if (r0 != 0) goto L_0x0103;
    L_0x00fe:
        if (r2 == 0) goto L_0x0103;
    L_0x0100:
        r9 = r8;
        goto L_0x000e;
    L_0x0103:
        r8 = r9;
        goto L_0x0100;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.math.NumberUtils.isNumber(java.lang.String):boolean");
    }
}
