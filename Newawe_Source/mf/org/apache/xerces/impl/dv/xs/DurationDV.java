package mf.org.apache.xerces.impl.dv.xs;

import android.support.v4.widget.ExploreByTouchHelper;
import java.math.BigDecimal;
import java.math.BigInteger;
import mf.javax.xml.datatype.DatatypeFactory;
import mf.javax.xml.datatype.Duration;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.ClassUtils;

public class DurationDV extends AbstractDateTimeDV {
    private static final DateTimeData[] DATETIMES;
    public static final int DAYTIMEDURATION_TYPE = 2;
    public static final int DURATION_TYPE = 0;
    public static final int YEARMONTHDURATION_TYPE = 1;

    static {
        DATETIMES = new DateTimeData[]{new DateTimeData(1696, 9, YEARMONTHDURATION_TYPE, DURATION_TYPE, DURATION_TYPE, 0.0d, 90, null, true, null), new DateTimeData(1697, DAYTIMEDURATION_TYPE, YEARMONTHDURATION_TYPE, DURATION_TYPE, DURATION_TYPE, 0.0d, 90, null, true, null), new DateTimeData(1903, 3, YEARMONTHDURATION_TYPE, DURATION_TYPE, DURATION_TYPE, 0.0d, 90, null, true, null), new DateTimeData(1903, 7, YEARMONTHDURATION_TYPE, DURATION_TYPE, DURATION_TYPE, 0.0d, 90, null, true, null)};
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return parse(content, DURATION_TYPE);
        } catch (Exception e) {
            Object[] objArr = new Object[DAYTIMEDURATION_TYPE];
            objArr[DURATION_TYPE] = content;
            objArr[YEARMONTHDURATION_TYPE] = SchemaSymbols.ATTVAL_DURATION;
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", objArr);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected mf.org.apache.xerces.impl.dv.xs.AbstractDateTimeDV.DateTimeData parse(java.lang.String r17, int r18) throws mf.org.apache.xerces.impl.dv.xs.SchemaDateTimeException {
        /*
        r16 = this;
        r7 = r17.length();
        r3 = new mf.org.apache.xerces.impl.dv.xs.AbstractDateTimeDV$DateTimeData;
        r0 = r17;
        r1 = r16;
        r3.<init>(r0, r1);
        r9 = 0;
        r10 = r9 + 1;
        r0 = r17;
        r2 = r0.charAt(r9);
        r11 = 80;
        if (r2 == r11) goto L_0x0024;
    L_0x001a:
        r11 = 45;
        if (r2 == r11) goto L_0x0024;
    L_0x001e:
        r11 = new mf.org.apache.xerces.impl.dv.xs.SchemaDateTimeException;
        r11.<init>();
        throw r11;
    L_0x0024:
        r11 = 45;
        if (r2 != r11) goto L_0x0042;
    L_0x0028:
        r11 = 45;
    L_0x002a:
        r3.utc = r11;
        r11 = 45;
        if (r2 != r11) goto L_0x0044;
    L_0x0030:
        r9 = r10 + 1;
        r0 = r17;
        r11 = r0.charAt(r10);
        r12 = 80;
        if (r11 == r12) goto L_0x0045;
    L_0x003c:
        r11 = new mf.org.apache.xerces.impl.dv.xs.SchemaDateTimeException;
        r11.<init>();
        throw r11;
    L_0x0042:
        r11 = 0;
        goto L_0x002a;
    L_0x0044:
        r9 = r10;
    L_0x0045:
        r8 = 1;
        r11 = r3.utc;
        r12 = 45;
        if (r11 != r12) goto L_0x004d;
    L_0x004c:
        r8 = -1;
    L_0x004d:
        r4 = 0;
        r11 = 84;
        r0 = r16;
        r1 = r17;
        r6 = r0.indexOf(r1, r9, r7, r11);
        r11 = -1;
        if (r6 != r11) goto L_0x0074;
    L_0x005b:
        r6 = r7;
    L_0x005c:
        r11 = 89;
        r0 = r16;
        r1 = r17;
        r5 = r0.indexOf(r1, r9, r6, r11);
        r11 = -1;
        if (r5 == r11) goto L_0x008d;
    L_0x0069:
        r11 = 2;
        r0 = r18;
        if (r0 != r11) goto L_0x007f;
    L_0x006e:
        r11 = new mf.org.apache.xerces.impl.dv.xs.SchemaDateTimeException;
        r11.<init>();
        throw r11;
    L_0x0074:
        r11 = 1;
        r0 = r18;
        if (r0 != r11) goto L_0x005c;
    L_0x0079:
        r11 = new mf.org.apache.xerces.impl.dv.xs.SchemaDateTimeException;
        r11.<init>();
        throw r11;
    L_0x007f:
        r0 = r16;
        r1 = r17;
        r11 = r0.parseInt(r1, r9, r5);
        r11 = r11 * r8;
        r3.year = r11;
        r9 = r5 + 1;
        r4 = 1;
    L_0x008d:
        r11 = 77;
        r0 = r16;
        r1 = r17;
        r5 = r0.indexOf(r1, r9, r6, r11);
        r11 = -1;
        if (r5 == r11) goto L_0x00b3;
    L_0x009a:
        r11 = 2;
        r0 = r18;
        if (r0 != r11) goto L_0x00a5;
    L_0x009f:
        r11 = new mf.org.apache.xerces.impl.dv.xs.SchemaDateTimeException;
        r11.<init>();
        throw r11;
    L_0x00a5:
        r0 = r16;
        r1 = r17;
        r11 = r0.parseInt(r1, r9, r5);
        r11 = r11 * r8;
        r3.month = r11;
        r9 = r5 + 1;
        r4 = 1;
    L_0x00b3:
        r11 = 68;
        r0 = r16;
        r1 = r17;
        r5 = r0.indexOf(r1, r9, r6, r11);
        r11 = -1;
        if (r5 == r11) goto L_0x00d9;
    L_0x00c0:
        r11 = 1;
        r0 = r18;
        if (r0 != r11) goto L_0x00cb;
    L_0x00c5:
        r11 = new mf.org.apache.xerces.impl.dv.xs.SchemaDateTimeException;
        r11.<init>();
        throw r11;
    L_0x00cb:
        r0 = r16;
        r1 = r17;
        r11 = r0.parseInt(r1, r9, r5);
        r11 = r11 * r8;
        r3.day = r11;
        r9 = r5 + 1;
        r4 = 1;
    L_0x00d9:
        if (r7 != r6) goto L_0x00e3;
    L_0x00db:
        if (r9 == r7) goto L_0x00e3;
    L_0x00dd:
        r11 = new mf.org.apache.xerces.impl.dv.xs.SchemaDateTimeException;
        r11.<init>();
        throw r11;
    L_0x00e3:
        if (r7 == r6) goto L_0x014d;
    L_0x00e5:
        r9 = r9 + 1;
        r11 = 72;
        r0 = r16;
        r1 = r17;
        r5 = r0.indexOf(r1, r9, r7, r11);
        r11 = -1;
        if (r5 == r11) goto L_0x0102;
    L_0x00f4:
        r0 = r16;
        r1 = r17;
        r11 = r0.parseInt(r1, r9, r5);
        r11 = r11 * r8;
        r3.hour = r11;
        r9 = r5 + 1;
        r4 = 1;
    L_0x0102:
        r11 = 77;
        r0 = r16;
        r1 = r17;
        r5 = r0.indexOf(r1, r9, r7, r11);
        r11 = -1;
        if (r5 == r11) goto L_0x011d;
    L_0x010f:
        r0 = r16;
        r1 = r17;
        r11 = r0.parseInt(r1, r9, r5);
        r11 = r11 * r8;
        r3.minute = r11;
        r9 = r5 + 1;
        r4 = 1;
    L_0x011d:
        r11 = 83;
        r0 = r16;
        r1 = r17;
        r5 = r0.indexOf(r1, r9, r7, r11);
        r11 = -1;
        if (r5 == r11) goto L_0x0139;
    L_0x012a:
        r12 = (double) r8;
        r0 = r16;
        r1 = r17;
        r14 = r0.parseSecond(r1, r9, r5);
        r12 = r12 * r14;
        r3.second = r12;
        r9 = r5 + 1;
        r4 = 1;
    L_0x0139:
        if (r9 != r7) goto L_0x0147;
    L_0x013b:
        r9 = r9 + -1;
        r0 = r17;
        r11 = r0.charAt(r9);
        r12 = 84;
        if (r11 != r12) goto L_0x014d;
    L_0x0147:
        r11 = new mf.org.apache.xerces.impl.dv.xs.SchemaDateTimeException;
        r11.<init>();
        throw r11;
    L_0x014d:
        if (r4 != 0) goto L_0x0155;
    L_0x014f:
        r11 = new mf.org.apache.xerces.impl.dv.xs.SchemaDateTimeException;
        r11.<init>();
        throw r11;
    L_0x0155:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.dv.xs.DurationDV.parse(java.lang.String, int):mf.org.apache.xerces.impl.dv.xs.AbstractDateTimeDV$DateTimeData");
    }

    protected short compareDates(DateTimeData date1, DateTimeData date2, boolean strict) {
        if (compareOrder(date1, date2) == (short) 0) {
            return (short) 0;
        }
        DateTimeData[] result = new DateTimeData[DAYTIMEDURATION_TYPE];
        result[DURATION_TYPE] = new DateTimeData(null, this);
        result[YEARMONTHDURATION_TYPE] = new DateTimeData(null, this);
        short resultA = compareOrder(addDuration(date1, DATETIMES[DURATION_TYPE], result[DURATION_TYPE]), addDuration(date2, DATETIMES[DURATION_TYPE], result[YEARMONTHDURATION_TYPE]));
        if (resultA == (short) 2) {
            return (short) 2;
        }
        resultA = compareResults(resultA, compareOrder(addDuration(date1, DATETIMES[YEARMONTHDURATION_TYPE], result[DURATION_TYPE]), addDuration(date2, DATETIMES[YEARMONTHDURATION_TYPE], result[YEARMONTHDURATION_TYPE])), strict);
        if (resultA == (short) 2) {
            return (short) 2;
        }
        resultA = compareResults(resultA, compareOrder(addDuration(date1, DATETIMES[DAYTIMEDURATION_TYPE], result[DURATION_TYPE]), addDuration(date2, DATETIMES[DAYTIMEDURATION_TYPE], result[YEARMONTHDURATION_TYPE])), strict);
        if (resultA == (short) 2) {
            return (short) 2;
        }
        return compareResults(resultA, compareOrder(addDuration(date1, DATETIMES[3], result[DURATION_TYPE]), addDuration(date2, DATETIMES[3], result[YEARMONTHDURATION_TYPE])), strict);
    }

    private short compareResults(short resultA, short resultB, boolean strict) {
        if (resultB == (short) 2) {
            return (short) 2;
        }
        if (resultA != resultB && strict) {
            return (short) 2;
        }
        if (resultA == resultB || strict) {
            return resultA;
        }
        if (resultA != (short) 0 && resultB != (short) 0) {
            return (short) 2;
        }
        if (resultA == (short) 0) {
            return resultB;
        }
        return resultA;
    }

    private DateTimeData addDuration(DateTimeData date, DateTimeData addto, DateTimeData duration) {
        resetDateObj(duration);
        int temp = addto.month + date.month;
        duration.month = modulo(temp, YEARMONTHDURATION_TYPE, 13);
        duration.year = (addto.year + date.year) + fQuotient(temp, YEARMONTHDURATION_TYPE, 13);
        double dtemp = addto.second + date.second;
        int carry = (int) Math.floor(dtemp / 60.0d);
        duration.second = dtemp - ((double) (carry * 60));
        temp = (addto.minute + date.minute) + carry;
        carry = fQuotient(temp, 60);
        duration.minute = mod(temp, 60, carry);
        temp = (addto.hour + date.hour) + carry;
        carry = fQuotient(temp, 24);
        duration.hour = mod(temp, 24, carry);
        duration.day = (addto.day + date.day) + carry;
        while (true) {
            temp = maxDayInMonthFor(duration.year, duration.month);
            if (duration.day < YEARMONTHDURATION_TYPE) {
                duration.day += maxDayInMonthFor(duration.year, duration.month - 1);
                carry = -1;
            } else if (duration.day > temp) {
                duration.day -= temp;
                carry = YEARMONTHDURATION_TYPE;
            } else {
                duration.utc = 90;
                return duration;
            }
            temp = duration.month + carry;
            duration.month = modulo(temp, YEARMONTHDURATION_TYPE, 13);
            duration.year += fQuotient(temp, YEARMONTHDURATION_TYPE, 13);
        }
    }

    protected double parseSecond(String buffer, int start, int end) throws NumberFormatException {
        int dot = -1;
        for (int i = start; i < end; i += YEARMONTHDURATION_TYPE) {
            char ch = buffer.charAt(i);
            if (ch == ClassUtils.PACKAGE_SEPARATOR_CHAR) {
                dot = i;
            } else if (ch > '9' || ch < '0') {
                throw new NumberFormatException("'" + buffer + "' has wrong format");
            }
        }
        if (dot + YEARMONTHDURATION_TYPE == end) {
            throw new NumberFormatException("'" + buffer + "' has wrong format");
        }
        double value = Double.parseDouble(buffer.substring(start, end));
        if (value != Double.POSITIVE_INFINITY) {
            return value;
        }
        throw new NumberFormatException("'" + buffer + "' has wrong format");
    }

    protected String dateToString(DateTimeData date) {
        int i;
        int i2 = -1;
        StringBuffer message = new StringBuffer(30);
        if (date.year < 0 || date.month < 0 || date.day < 0 || date.hour < 0 || date.minute < 0 || date.second < 0.0d) {
            message.append('-');
        }
        message.append('P');
        message.append((date.year < 0 ? -1 : YEARMONTHDURATION_TYPE) * date.year);
        message.append('Y');
        if (date.month < 0) {
            i = -1;
        } else {
            i = YEARMONTHDURATION_TYPE;
        }
        message.append(i * date.month);
        message.append('M');
        if (date.day < 0) {
            i = -1;
        } else {
            i = YEARMONTHDURATION_TYPE;
        }
        message.append(i * date.day);
        message.append('D');
        message.append('T');
        if (date.hour < 0) {
            i = -1;
        } else {
            i = YEARMONTHDURATION_TYPE;
        }
        message.append(i * date.hour);
        message.append('H');
        if (date.minute < 0) {
            i = -1;
        } else {
            i = YEARMONTHDURATION_TYPE;
        }
        message.append(i * date.minute);
        message.append('M');
        if (date.second >= 0.0d) {
            i2 = YEARMONTHDURATION_TYPE;
        }
        append2(message, ((double) i2) * date.second);
        message.append('S');
        return message.toString();
    }

    protected Duration getDuration(DateTimeData date) {
        BigInteger valueOf;
        BigInteger valueOf2;
        BigInteger valueOf3;
        BigInteger valueOf4;
        BigInteger valueOf5;
        boolean z = true;
        BigDecimal bigDecimal = null;
        int sign = YEARMONTHDURATION_TYPE;
        if (date.year < 0 || date.month < 0 || date.day < 0 || date.hour < 0 || date.minute < 0 || date.second < 0.0d) {
            sign = -1;
        }
        DatatypeFactory datatypeFactory = datatypeFactory;
        if (sign != YEARMONTHDURATION_TYPE) {
            z = false;
        }
        if (date.year != ExploreByTouchHelper.INVALID_ID) {
            valueOf = BigInteger.valueOf((long) (date.year * sign));
        } else {
            valueOf = null;
        }
        if (date.month != ExploreByTouchHelper.INVALID_ID) {
            valueOf2 = BigInteger.valueOf((long) (date.month * sign));
        } else {
            valueOf2 = null;
        }
        if (date.day != ExploreByTouchHelper.INVALID_ID) {
            valueOf3 = BigInteger.valueOf((long) (date.day * sign));
        } else {
            valueOf3 = null;
        }
        if (date.hour != ExploreByTouchHelper.INVALID_ID) {
            valueOf4 = BigInteger.valueOf((long) (date.hour * sign));
        } else {
            valueOf4 = null;
        }
        if (date.minute != ExploreByTouchHelper.INVALID_ID) {
            valueOf5 = BigInteger.valueOf((long) (date.minute * sign));
        } else {
            valueOf5 = null;
        }
        if (date.second != -2.147483648E9d) {
            bigDecimal = new BigDecimal(String.valueOf(((double) sign) * date.second));
        }
        return datatypeFactory.newDuration(z, valueOf, valueOf2, valueOf3, valueOf4, valueOf5, bigDecimal);
    }
}
