package mf.org.apache.xerces.impl.dv.xs;

import android.support.v4.widget.ExploreByTouchHelper;
import java.math.BigDecimal;
import mf.javax.xml.datatype.DatatypeFactory;
import mf.javax.xml.datatype.Duration;
import mf.javax.xml.datatype.XMLGregorianCalendar;
import mf.org.apache.xerces.jaxp.datatype.DatatypeFactoryImpl;
import mf.org.apache.xerces.xs.datatypes.XSDateTime;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpStatus;

public abstract class AbstractDateTimeDV extends TypeValidator {
    protected static final int DAY = 1;
    private static final boolean DEBUG = false;
    protected static final int MONTH = 1;
    protected static final int YEAR = 2000;
    protected static final DatatypeFactory datatypeFactory;

    static final class DateTimeData implements XSDateTime {
        private String canonical;
        int day;
        int hour;
        int minute;
        int month;
        boolean normalized;
        private String originalValue;
        int position;
        double second;
        int timezoneHr;
        int timezoneMin;
        final AbstractDateTimeDV type;
        int unNormDay;
        int unNormHour;
        int unNormMinute;
        int unNormMonth;
        double unNormSecond;
        int unNormYear;
        int utc;
        int year;

        public DateTimeData(String originalValue, AbstractDateTimeDV type) {
            this.normalized = true;
            this.originalValue = originalValue;
            this.type = type;
        }

        public DateTimeData(int year, int month, int day, int hour, int minute, double second, int utc, String originalValue, boolean normalized, AbstractDateTimeDV type) {
            this.normalized = true;
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            this.utc = utc;
            this.type = type;
            this.originalValue = originalValue;
        }

        public boolean equals(Object obj) {
            if ((obj instanceof DateTimeData) && this.type.compareDates(this, (DateTimeData) obj, true) == (short) 0) {
                return true;
            }
            return AbstractDateTimeDV.DEBUG;
        }

        public synchronized String toString() {
            if (this.canonical == null) {
                this.canonical = this.type.dateToString(this);
            }
            return this.canonical;
        }

        public int getYears() {
            if (this.type instanceof DurationDV) {
                return 0;
            }
            return this.normalized ? this.year : this.unNormYear;
        }

        public int getMonths() {
            if (this.type instanceof DurationDV) {
                return (this.year * 12) + this.month;
            }
            return this.normalized ? this.month : this.unNormMonth;
        }

        public int getDays() {
            if (this.type instanceof DurationDV) {
                return 0;
            }
            return this.normalized ? this.day : this.unNormDay;
        }

        public int getHours() {
            if (this.type instanceof DurationDV) {
                return 0;
            }
            return this.normalized ? this.hour : this.unNormHour;
        }

        public int getMinutes() {
            if (this.type instanceof DurationDV) {
                return 0;
            }
            return this.normalized ? this.minute : this.unNormMinute;
        }

        public double getSeconds() {
            if (this.type instanceof DurationDV) {
                return ((double) (((((this.day * 24) * 60) * 60) + ((this.hour * 60) * 60)) + (this.minute * 60))) + this.second;
            }
            return this.normalized ? this.second : this.unNormSecond;
        }

        public boolean hasTimeZone() {
            return this.utc != 0 ? true : AbstractDateTimeDV.DEBUG;
        }

        public int getTimeZoneHours() {
            return this.timezoneHr;
        }

        public int getTimeZoneMinutes() {
            return this.timezoneMin;
        }

        public String getLexicalValue() {
            return this.originalValue;
        }

        public XSDateTime normalize() {
            if (this.normalized) {
                return this;
            }
            DateTimeData dt = (DateTimeData) clone();
            dt.normalized = true;
            return dt;
        }

        public boolean isNormalized() {
            return this.normalized;
        }

        public Object clone() {
            DateTimeData dt = new DateTimeData(this.year, this.month, this.day, this.hour, this.minute, this.second, this.utc, this.originalValue, this.normalized, this.type);
            dt.canonical = this.canonical;
            dt.position = this.position;
            dt.timezoneHr = this.timezoneHr;
            dt.timezoneMin = this.timezoneMin;
            dt.unNormYear = this.unNormYear;
            dt.unNormMonth = this.unNormMonth;
            dt.unNormDay = this.unNormDay;
            dt.unNormHour = this.unNormHour;
            dt.unNormMinute = this.unNormMinute;
            dt.unNormSecond = this.unNormSecond;
            return dt;
        }

        public XMLGregorianCalendar getXMLGregorianCalendar() {
            return this.type.getXMLGregorianCalendar(this);
        }

        public Duration getDuration() {
            return this.type.getDuration(this);
        }
    }

    static {
        datatypeFactory = new DatatypeFactoryImpl();
    }

    public short getAllowedFacets() {
        return (short) 2552;
    }

    public boolean isIdentical(Object value1, Object value2) {
        if (!(value1 instanceof DateTimeData) || !(value2 instanceof DateTimeData)) {
            return DEBUG;
        }
        DateTimeData v1 = (DateTimeData) value1;
        DateTimeData v2 = (DateTimeData) value2;
        if (v1.timezoneHr == v2.timezoneHr && v1.timezoneMin == v2.timezoneMin) {
            return v1.equals(v2);
        }
        return DEBUG;
    }

    public int compare(Object value1, Object value2) {
        return compareDates((DateTimeData) value1, (DateTimeData) value2, true);
    }

    protected short compareDates(DateTimeData date1, DateTimeData date2, boolean strict) {
        if (date1.utc == date2.utc) {
            return compareOrder(date1, date2);
        }
        DateTimeData tempDate = new DateTimeData(null, this);
        short c1;
        short c2;
        if (date1.utc == 90) {
            cloneDate(date2, tempDate);
            tempDate.timezoneHr = 14;
            tempDate.timezoneMin = 0;
            tempDate.utc = 43;
            normalize(tempDate);
            c1 = compareOrder(date1, tempDate);
            if (c1 == (short) -1) {
                return c1;
            }
            cloneDate(date2, tempDate);
            tempDate.timezoneHr = -14;
            tempDate.timezoneMin = 0;
            tempDate.utc = 45;
            normalize(tempDate);
            c2 = compareOrder(date1, tempDate);
            if (c2 == (short) 1) {
                return c2;
            }
            return (short) 2;
        } else if (date2.utc != 90) {
            return (short) 2;
        } else {
            cloneDate(date1, tempDate);
            tempDate.timezoneHr = -14;
            tempDate.timezoneMin = 0;
            tempDate.utc = 45;
            normalize(tempDate);
            c1 = compareOrder(tempDate, date2);
            if (c1 == (short) -1) {
                return c1;
            }
            cloneDate(date1, tempDate);
            tempDate.timezoneHr = 14;
            tempDate.timezoneMin = 0;
            tempDate.utc = 43;
            normalize(tempDate);
            c2 = compareOrder(tempDate, date2);
            if (c2 == (short) 1) {
                return c2;
            }
            return (short) 2;
        }
    }

    protected short compareOrder(DateTimeData date1, DateTimeData date2) {
        if (date1.position < MONTH) {
            if (date1.year < date2.year) {
                return (short) -1;
            }
            if (date1.year > date2.year) {
                return (short) 1;
            }
        }
        if (date1.position < 2) {
            if (date1.month < date2.month) {
                return (short) -1;
            }
            if (date1.month > date2.month) {
                return (short) 1;
            }
        }
        if (date1.day < date2.day) {
            return (short) -1;
        }
        if (date1.day > date2.day) {
            return (short) 1;
        }
        if (date1.hour < date2.hour) {
            return (short) -1;
        }
        if (date1.hour > date2.hour) {
            return (short) 1;
        }
        if (date1.minute < date2.minute) {
            return (short) -1;
        }
        if (date1.minute > date2.minute) {
            return (short) 1;
        }
        if (date1.second < date2.second) {
            return (short) -1;
        }
        if (date1.second > date2.second) {
            return (short) 1;
        }
        if (date1.utc < date2.utc) {
            return (short) -1;
        }
        if (date1.utc > date2.utc) {
            return (short) 1;
        }
        return (short) 0;
    }

    protected void getTime(String buffer, int start, int end, DateTimeData data) throws RuntimeException {
        int stop = start + 2;
        data.hour = parseInt(buffer, start, stop);
        int stop2 = stop + MONTH;
        if (buffer.charAt(stop) != ':') {
            throw new RuntimeException("Error in parsing time zone");
        }
        start = stop2;
        stop = stop2 + 2;
        data.minute = parseInt(buffer, start, stop);
        stop2 = stop + MONTH;
        if (buffer.charAt(stop) != ':') {
            throw new RuntimeException("Error in parsing time zone");
        }
        int sign = findUTCSign(buffer, start, end);
        start = stop2;
        if (sign < 0) {
            stop = end;
        } else {
            stop = sign;
        }
        data.second = parseSecond(buffer, start, stop);
        if (sign > 0) {
            getTimeZone(buffer, data, sign, end);
        }
    }

    protected int getDate(String buffer, int start, int end, DateTimeData date) throws RuntimeException {
        start = getYearMonth(buffer, start, end, date);
        int start2 = start + MONTH;
        if (buffer.charAt(start) != '-') {
            throw new RuntimeException("CCYY-MM must be followed by '-' sign");
        }
        int stop = start2 + 2;
        date.day = parseInt(buffer, start2, stop);
        return stop;
    }

    protected int getYearMonth(String buffer, int start, int end, DateTimeData date) throws RuntimeException {
        if (buffer.charAt(0) == '-') {
            start += MONTH;
        }
        int i = indexOf(buffer, start, end, '-');
        if (i == -1) {
            throw new RuntimeException("Year separator is missing or misplaced");
        }
        int length = i - start;
        if (length < 4) {
            throw new RuntimeException("Year must have 'CCYY' format");
        } else if (length <= 4 || buffer.charAt(start) != '0') {
            date.year = parseIntYear(buffer, i);
            if (buffer.charAt(i) != '-') {
                throw new RuntimeException("CCYY must be followed by '-' sign");
            }
            start = i + MONTH;
            i = start + 2;
            date.month = parseInt(buffer, start, i);
            return i;
        } else {
            throw new RuntimeException("Leading zeros are required if the year value would otherwise have fewer than four digits; otherwise they are forbidden");
        }
    }

    protected void parseTimeZone(String buffer, int start, int end, DateTimeData date) throws RuntimeException {
        if (start >= end) {
            return;
        }
        if (isNextCharUTCSign(buffer, start, end)) {
            getTimeZone(buffer, date, start, end);
            return;
        }
        throw new RuntimeException("Error in month parsing");
    }

    protected void getTimeZone(String buffer, DateTimeData data, int sign, int end) throws RuntimeException {
        data.utc = buffer.charAt(sign);
        if (buffer.charAt(sign) == 'Z') {
            if (end > sign + MONTH) {
                throw new RuntimeException("Error in parsing time zone");
            }
        } else if (sign <= end - 6) {
            int negate = buffer.charAt(sign) == '-' ? -1 : MONTH;
            sign += MONTH;
            int stop = sign + 2;
            data.timezoneHr = parseInt(buffer, sign, stop) * negate;
            int stop2 = stop + MONTH;
            if (buffer.charAt(stop) != ':') {
                throw new RuntimeException("Error in parsing time zone");
            }
            data.timezoneMin = parseInt(buffer, stop2, stop2 + 2) * negate;
            if (stop2 + 2 != end) {
                throw new RuntimeException("Error in parsing time zone");
            } else if (data.timezoneHr != 0 || data.timezoneMin != 0) {
                data.normalized = DEBUG;
            }
        } else {
            throw new RuntimeException("Error in parsing time zone");
        }
    }

    protected int indexOf(String buffer, int start, int end, char ch) {
        for (int i = start; i < end; i += MONTH) {
            if (buffer.charAt(i) == ch) {
                return i;
            }
        }
        return -1;
    }

    protected void validateDateTime(DateTimeData data) {
        if (data.year == 0) {
            throw new RuntimeException("The year \"0000\" is an illegal year value");
        } else if (data.month < MONTH || data.month > 12) {
            throw new RuntimeException("The month must have values 1 to 12");
        } else if (data.day > maxDayInMonthFor(data.year, data.month) || data.day < MONTH) {
            throw new RuntimeException("The day must have values 1 to 31");
        } else {
            if (data.hour > 23 || data.hour < 0) {
                if (data.hour == 24 && data.minute == 0 && data.second == 0.0d) {
                    data.hour = 0;
                    int i = data.day + MONTH;
                    data.day = i;
                    if (i > maxDayInMonthFor(data.year, data.month)) {
                        data.day = MONTH;
                        i = data.month + MONTH;
                        data.month = i;
                        if (i > 12) {
                            data.month = MONTH;
                            i = data.year + MONTH;
                            data.year = i;
                            if (i == 0) {
                                data.year = MONTH;
                            }
                        }
                    }
                } else {
                    throw new RuntimeException("Hour must have values 0-23, unless 24:00:00");
                }
            }
            if (data.minute > 59 || data.minute < 0) {
                throw new RuntimeException("Minute must have values 0-59");
            } else if (data.second >= 60.0d || data.second < 0.0d) {
                throw new RuntimeException("Second must have values 0-59");
            } else if (data.timezoneHr > 14 || data.timezoneHr < -14) {
                throw new RuntimeException("Time zone should have range -14:00 to +14:00");
            } else if ((data.timezoneHr == 14 || data.timezoneHr == -14) && data.timezoneMin != 0) {
                throw new RuntimeException("Time zone should have range -14:00 to +14:00");
            } else if (data.timezoneMin > 59 || data.timezoneMin < -59) {
                throw new RuntimeException("Minute must have values 0-59");
            }
        }
    }

    protected int findUTCSign(String buffer, int start, int end) {
        int i = start;
        while (i < end) {
            int c = buffer.charAt(i);
            if (c == 90 || c == 43 || c == 45) {
                return i;
            }
            i += MONTH;
        }
        return -1;
    }

    protected final boolean isNextCharUTCSign(String buffer, int start, int end) {
        if (start >= end) {
            return DEBUG;
        }
        char c = buffer.charAt(start);
        if (c == 'Z' || c == '+' || c == '-') {
            return true;
        }
        return DEBUG;
    }

    protected int parseInt(String buffer, int start, int end) throws NumberFormatException {
        int result = 0;
        int multmin = -2147483647 / 10;
        int i = start;
        do {
            int digit = TypeValidator.getDigit(buffer.charAt(i));
            if (digit < 0) {
                throw new NumberFormatException("'" + buffer + "' has wrong format");
            } else if (result < multmin) {
                throw new NumberFormatException("'" + buffer + "' has wrong format");
            } else {
                result *= 10;
                if (result < -2147483647 + digit) {
                    throw new NumberFormatException("'" + buffer + "' has wrong format");
                }
                result -= digit;
                i += MONTH;
            }
        } while (i < end);
        return -result;
    }

    protected int parseIntYear(String buffer, int end) {
        int limit;
        int result = 0;
        boolean negative = DEBUG;
        int i = 0;
        if (buffer.charAt(0) == '-') {
            negative = true;
            limit = ExploreByTouchHelper.INVALID_ID;
            i = 0 + MONTH;
        } else {
            limit = -2147483647;
        }
        int multmin = limit / 10;
        int i2 = i;
        while (i2 < end) {
            i = i2 + MONTH;
            int digit = TypeValidator.getDigit(buffer.charAt(i2));
            if (digit < 0) {
                throw new NumberFormatException("'" + buffer + "' has wrong format");
            } else if (result < multmin) {
                throw new NumberFormatException("'" + buffer + "' has wrong format");
            } else {
                result *= 10;
                if (result < limit + digit) {
                    throw new NumberFormatException("'" + buffer + "' has wrong format");
                }
                result -= digit;
                i2 = i;
            }
        }
        if (!negative) {
            return -result;
        }
        if (i2 > MONTH) {
            return result;
        }
        throw new NumberFormatException("'" + buffer + "' has wrong format");
    }

    protected void normalize(DateTimeData date) {
        int temp = date.minute + (date.timezoneMin * -1);
        int carry = fQuotient(temp, 60);
        date.minute = mod(temp, 60, carry);
        temp = (date.hour + (date.timezoneHr * -1)) + carry;
        carry = fQuotient(temp, 24);
        date.hour = mod(temp, 24, carry);
        date.day += carry;
        while (true) {
            temp = maxDayInMonthFor(date.year, date.month);
            if (date.day < MONTH) {
                date.day += maxDayInMonthFor(date.year, date.month - 1);
                carry = -1;
            } else if (date.day > temp) {
                date.day -= temp;
                carry = MONTH;
            } else {
                date.utc = 90;
                return;
            }
            temp = date.month + carry;
            date.month = modulo(temp, MONTH, 13);
            date.year += fQuotient(temp, MONTH, 13);
            if (date.year == 0) {
                int i;
                if (date.timezoneHr < 0 || date.timezoneMin < 0) {
                    i = MONTH;
                } else {
                    i = -1;
                }
                date.year = i;
            }
        }
    }

    protected void saveUnnormalized(DateTimeData date) {
        date.unNormYear = date.year;
        date.unNormMonth = date.month;
        date.unNormDay = date.day;
        date.unNormHour = date.hour;
        date.unNormMinute = date.minute;
        date.unNormSecond = date.second;
    }

    protected void resetDateObj(DateTimeData data) {
        data.year = 0;
        data.month = 0;
        data.day = 0;
        data.hour = 0;
        data.minute = 0;
        data.second = 0.0d;
        data.utc = 0;
        data.timezoneHr = 0;
        data.timezoneMin = 0;
    }

    protected int maxDayInMonthFor(int year, int month) {
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month != 2) {
            return 31;
        }
        if (isLeapYear(year)) {
            return 29;
        }
        return 28;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 != 0 || (year % 100 == 0 && year % HttpStatus.SC_BAD_REQUEST != 0)) ? DEBUG : true;
    }

    protected int mod(int a, int b, int quotient) {
        return a - (quotient * b);
    }

    protected int fQuotient(int a, int b) {
        return (int) Math.floor((double) (((float) a) / ((float) b)));
    }

    protected int modulo(int temp, int low, int high) {
        int a = temp - low;
        int b = high - low;
        return mod(a, b, fQuotient(a, b)) + low;
    }

    protected int fQuotient(int temp, int low, int high) {
        return fQuotient(temp - low, high - low);
    }

    protected String dateToString(DateTimeData date) {
        StringBuffer message = new StringBuffer(25);
        append(message, date.year, 4);
        message.append('-');
        append(message, date.month, 2);
        message.append('-');
        append(message, date.day, 2);
        message.append('T');
        append(message, date.hour, 2);
        message.append(':');
        append(message, date.minute, 2);
        message.append(':');
        append(message, date.second);
        append(message, (char) date.utc, 0);
        return message.toString();
    }

    protected final void append(StringBuffer message, int value, int nch) {
        if (value == ExploreByTouchHelper.INVALID_ID) {
            message.append(value);
            return;
        }
        if (value < 0) {
            message.append('-');
            value = -value;
        }
        if (nch == 4) {
            if (value < 10) {
                message.append("000");
            } else if (value < 100) {
                message.append("00");
            } else if (value < DateUtils.MILLIS_IN_SECOND) {
                message.append('0');
            }
            message.append(value);
        } else if (nch == 2) {
            if (value < 10) {
                message.append('0');
            }
            message.append(value);
        } else if (value != 0) {
            message.append((char) value);
        }
    }

    protected final void append(StringBuffer message, double value) {
        if (value < 0.0d) {
            message.append('-');
            value = -value;
        }
        if (value < 10.0d) {
            message.append('0');
        }
        append2(message, value);
    }

    protected final void append2(StringBuffer message, double value) {
        int intValue = (int) value;
        if (value == ((double) intValue)) {
            message.append(intValue);
        } else {
            append3(message, value);
        }
    }

    private void append3(StringBuffer message, double value) {
        String d = String.valueOf(value);
        int eIndex = d.indexOf(69);
        if (eIndex == -1) {
            message.append(d);
        } else if (value < 1.0d) {
            try {
                int exp = parseInt(d, eIndex + 2, d.length());
                message.append("0.");
                for (i = MONTH; i < exp; i += MONTH) {
                    message.append('0');
                }
                int end = eIndex - 1;
                while (end > 0 && d.charAt(end) == '0') {
                    end--;
                }
                for (i = 0; i <= end; i += MONTH) {
                    c = d.charAt(i);
                    if (c != ClassUtils.PACKAGE_SEPARATOR_CHAR) {
                        message.append(c);
                    }
                }
            } catch (Exception e) {
                message.append(d);
            }
        } else {
            try {
                int integerEnd = parseInt(d, eIndex + MONTH, d.length()) + 2;
                for (i = 0; i < eIndex; i += MONTH) {
                    c = d.charAt(i);
                    if (c != ClassUtils.PACKAGE_SEPARATOR_CHAR) {
                        if (i == integerEnd) {
                            message.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                        }
                        message.append(c);
                    }
                }
                for (i = integerEnd - eIndex; i > 0; i--) {
                    message.append('0');
                }
            } catch (Exception e2) {
                message.append(d);
            }
        }
    }

    protected double parseSecond(String buffer, int start, int end) throws NumberFormatException {
        int dot = -1;
        for (int i = start; i < end; i += MONTH) {
            char ch = buffer.charAt(i);
            if (ch == ClassUtils.PACKAGE_SEPARATOR_CHAR) {
                dot = i;
            } else if (ch > '9' || ch < '0') {
                throw new NumberFormatException("'" + buffer + "' has wrong format");
            }
        }
        if (dot == -1) {
            if (start + 2 != end) {
                throw new NumberFormatException("'" + buffer + "' has wrong format");
            }
        } else if (start + 2 != dot || dot + MONTH == end) {
            throw new NumberFormatException("'" + buffer + "' has wrong format");
        }
        return Double.parseDouble(buffer.substring(start, end));
    }

    private void cloneDate(DateTimeData finalValue, DateTimeData tempDate) {
        tempDate.year = finalValue.year;
        tempDate.month = finalValue.month;
        tempDate.day = finalValue.day;
        tempDate.hour = finalValue.hour;
        tempDate.minute = finalValue.minute;
        tempDate.second = finalValue.second;
        tempDate.utc = finalValue.utc;
        tempDate.timezoneHr = finalValue.timezoneHr;
        tempDate.timezoneMin = finalValue.timezoneMin;
    }

    protected XMLGregorianCalendar getXMLGregorianCalendar(DateTimeData data) {
        return null;
    }

    protected Duration getDuration(DateTimeData data) {
        return null;
    }

    protected final BigDecimal getFractionalSecondsAsBigDecimal(DateTimeData data) {
        StringBuffer buf = new StringBuffer();
        append3(buf, data.unNormSecond);
        String value = buf.toString();
        int index = value.indexOf(46);
        if (index == -1) {
            return null;
        }
        BigDecimal _val = new BigDecimal(value.substring(index));
        if (_val.compareTo(BigDecimal.valueOf(0)) == 0) {
            return null;
        }
        return _val;
    }
}
