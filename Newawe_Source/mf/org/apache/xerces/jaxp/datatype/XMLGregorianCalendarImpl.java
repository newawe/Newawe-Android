package mf.org.apache.xerces.jaxp.datatype;

import android.support.v4.widget.ExploreByTouchHelper;
import com.astuetz.pagerslidingtabstrip.C0302R;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import mf.javax.xml.datatype.DatatypeConstants;
import mf.javax.xml.datatype.Duration;
import mf.javax.xml.datatype.XMLGregorianCalendar;
import mf.javax.xml.namespace.QName;
import mf.org.apache.xerces.dom3.as.ASContentModel;
import mf.org.apache.xerces.util.DatatypeMessageFormatter;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpStatus;

class XMLGregorianCalendarImpl extends XMLGregorianCalendar implements Serializable, Cloneable {
    private static final BigInteger BILLION_B;
    private static final int BILLION_I = 1000000000;
    private static final int DAY = 2;
    private static final BigDecimal DECIMAL_ONE;
    private static final BigDecimal DECIMAL_SIXTY;
    private static final BigDecimal DECIMAL_ZERO;
    private static final String[] FIELD_NAME;
    private static final BigInteger FOUR;
    private static final BigInteger FOUR_HUNDRED;
    private static final int HOUR = 3;
    private static final BigInteger HUNDRED;
    public static final XMLGregorianCalendar LEAP_YEAR_DEFAULT;
    private static final int[] MAX_FIELD_VALUE;
    private static final int MILLISECOND = 6;
    private static final int MINUTE = 4;
    private static final int[] MIN_FIELD_VALUE;
    private static final int MONTH = 1;
    private static final Date PURE_GREGORIAN_CHANGE;
    private static final int SECOND = 5;
    private static final BigInteger SIXTY;
    private static final int TIMEZONE = 7;
    private static final BigInteger TWELVE;
    private static final BigInteger TWENTY_FOUR;
    private static final int YEAR = 0;
    private static final long serialVersionUID = 3905403108073447394L;
    private int day;
    private BigInteger eon;
    private BigDecimal fractionalSecond;
    private int hour;
    private int minute;
    private int month;
    private int orig_day;
    private BigInteger orig_eon;
    private BigDecimal orig_fracSeconds;
    private int orig_hour;
    private int orig_minute;
    private int orig_month;
    private int orig_second;
    private int orig_timezone;
    private int orig_year;
    private int second;
    private int timezone;
    private int year;

    private static class DaysInMonth {
        private static final int[] table;

        private DaysInMonth() {
        }

        static {
            int[] iArr = new int[13];
            iArr[XMLGregorianCalendarImpl.MONTH] = 31;
            iArr[XMLGregorianCalendarImpl.DAY] = 28;
            iArr[XMLGregorianCalendarImpl.HOUR] = 31;
            iArr[XMLGregorianCalendarImpl.MINUTE] = 30;
            iArr[XMLGregorianCalendarImpl.SECOND] = 31;
            iArr[XMLGregorianCalendarImpl.MILLISECOND] = 30;
            iArr[XMLGregorianCalendarImpl.TIMEZONE] = 31;
            iArr[8] = 31;
            iArr[9] = 30;
            iArr[10] = 31;
            iArr[11] = 30;
            iArr[12] = 31;
            table = iArr;
        }
    }

    private final class Parser {
        private int fidx;
        private final int flen;
        private final String format;
        private final String value;
        private int vidx;
        private final int vlen;

        private Parser(String format, String value) {
            this.format = format;
            this.value = value;
            this.flen = format.length();
            this.vlen = value.length();
        }

        public void parse() throws IllegalArgumentException {
            while (this.fidx < this.flen) {
                String str = this.format;
                int i = this.fidx;
                this.fidx = i + XMLGregorianCalendarImpl.MONTH;
                char fch = str.charAt(i);
                if (fch != '%') {
                    skip(fch);
                } else {
                    str = this.format;
                    i = this.fidx;
                    this.fidx = i + XMLGregorianCalendarImpl.MONTH;
                    switch (str.charAt(i)) {
                        case C0302R.styleable.Theme_searchViewStyle /*68*/:
                            XMLGregorianCalendarImpl.this.setDay(parseInt(XMLGregorianCalendarImpl.DAY, XMLGregorianCalendarImpl.DAY));
                            break;
                        case C0302R.styleable.Theme_textAppearanceListItemSmall /*77*/:
                            XMLGregorianCalendarImpl.this.setMonth(parseInt(XMLGregorianCalendarImpl.DAY, XMLGregorianCalendarImpl.DAY));
                            break;
                        case C0302R.styleable.Theme_colorSwitchThumbNormal /*89*/:
                            parseYear();
                            break;
                        case C0302R.styleable.Theme_editTextStyle /*104*/:
                            XMLGregorianCalendarImpl.this.setHour(parseInt(XMLGregorianCalendarImpl.DAY, XMLGregorianCalendarImpl.DAY));
                            break;
                        case C0302R.styleable.Theme_switchStyle /*109*/:
                            XMLGregorianCalendarImpl.this.setMinute(parseInt(XMLGregorianCalendarImpl.DAY, XMLGregorianCalendarImpl.DAY));
                            break;
                        case 's':
                            XMLGregorianCalendarImpl.this.setSecond(parseInt(XMLGregorianCalendarImpl.DAY, XMLGregorianCalendarImpl.DAY));
                            if (peek() != ClassUtils.PACKAGE_SEPARATOR_CHAR) {
                                break;
                            }
                            XMLGregorianCalendarImpl.this.setFractionalSecond(parseBigDecimal());
                            break;
                        case 'z':
                            char vch = peek();
                            if (vch != 'Z') {
                                if (vch != '+' && vch != '-') {
                                    break;
                                }
                                this.vidx += XMLGregorianCalendarImpl.MONTH;
                                int h = parseInt(XMLGregorianCalendarImpl.DAY, XMLGregorianCalendarImpl.DAY);
                                skip(':');
                                XMLGregorianCalendarImpl.this.setTimezone((vch == '+' ? XMLGregorianCalendarImpl.MONTH : -1) * ((h * 60) + parseInt(XMLGregorianCalendarImpl.DAY, XMLGregorianCalendarImpl.DAY)));
                                break;
                            }
                            this.vidx += XMLGregorianCalendarImpl.MONTH;
                            XMLGregorianCalendarImpl.this.setTimezone(XMLGregorianCalendarImpl.YEAR);
                            break;
                        default:
                            throw new InternalError();
                    }
                }
            }
            if (this.vidx != this.vlen) {
                throw new IllegalArgumentException(this.value);
            }
        }

        private char peek() throws IllegalArgumentException {
            if (this.vidx == this.vlen) {
                return '\uffff';
            }
            return this.value.charAt(this.vidx);
        }

        private char read() throws IllegalArgumentException {
            if (this.vidx == this.vlen) {
                throw new IllegalArgumentException(this.value);
            }
            String str = this.value;
            int i = this.vidx;
            this.vidx = i + XMLGregorianCalendarImpl.MONTH;
            return str.charAt(i);
        }

        private void skip(char ch) throws IllegalArgumentException {
            if (read() != ch) {
                throw new IllegalArgumentException(this.value);
            }
        }

        private void parseYear() throws IllegalArgumentException {
            int vstart = this.vidx;
            int sign = XMLGregorianCalendarImpl.YEAR;
            if (peek() == '-') {
                this.vidx += XMLGregorianCalendarImpl.MONTH;
                sign = XMLGregorianCalendarImpl.MONTH;
            }
            while (XMLGregorianCalendarImpl.isDigit(peek())) {
                this.vidx += XMLGregorianCalendarImpl.MONTH;
            }
            int digits = (this.vidx - vstart) - sign;
            if (digits < XMLGregorianCalendarImpl.MINUTE) {
                throw new IllegalArgumentException(this.value);
            }
            String yearString = this.value.substring(vstart, this.vidx);
            if (digits < 10) {
                XMLGregorianCalendarImpl.this.setYear(Integer.parseInt(yearString));
            } else {
                XMLGregorianCalendarImpl.this.setYear(new BigInteger(yearString));
            }
        }

        private int parseInt(int minDigits, int maxDigits) throws IllegalArgumentException {
            int vstart = this.vidx;
            while (XMLGregorianCalendarImpl.isDigit(peek()) && this.vidx - vstart < maxDigits) {
                this.vidx += XMLGregorianCalendarImpl.MONTH;
            }
            if (this.vidx - vstart >= minDigits) {
                return Integer.parseInt(this.value.substring(vstart, this.vidx));
            }
            throw new IllegalArgumentException(this.value);
        }

        private BigDecimal parseBigDecimal() throws IllegalArgumentException {
            int vstart = this.vidx;
            if (peek() == ClassUtils.PACKAGE_SEPARATOR_CHAR) {
                this.vidx += XMLGregorianCalendarImpl.MONTH;
                while (XMLGregorianCalendarImpl.isDigit(peek())) {
                    this.vidx += XMLGregorianCalendarImpl.MONTH;
                }
                return new BigDecimal(this.value.substring(vstart, this.vidx));
            }
            throw new IllegalArgumentException(this.value);
        }
    }

    static {
        BILLION_B = BigInteger.valueOf(1000000000);
        PURE_GREGORIAN_CHANGE = new Date(Long.MIN_VALUE);
        r0 = new int[8];
        MIN_FIELD_VALUE = r0;
        MAX_FIELD_VALUE = new int[]{ASContentModel.AS_UNBOUNDED, 12, 31, 24, 59, 60, 999, DatatypeConstants.MIN_TIMEZONE_OFFSET};
        FIELD_NAME = new String[]{"Year", "Month", "Day", "Hour", "Minute", "Second", "Millisecond", "Timezone"};
        LEAP_YEAR_DEFAULT = createDateTime((int) HttpStatus.SC_BAD_REQUEST, (int) MONTH, MONTH, (int) YEAR, YEAR, YEAR, (int) ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID);
        FOUR = BigInteger.valueOf(4);
        HUNDRED = BigInteger.valueOf(100);
        FOUR_HUNDRED = BigInteger.valueOf(400);
        SIXTY = BigInteger.valueOf(60);
        TWENTY_FOUR = BigInteger.valueOf(24);
        TWELVE = BigInteger.valueOf(12);
        DECIMAL_ZERO = BigDecimal.valueOf(0);
        DECIMAL_ONE = BigDecimal.valueOf(1);
        DECIMAL_SIXTY = BigDecimal.valueOf(60);
    }

    protected XMLGregorianCalendarImpl(String lexicalRepresentation) throws IllegalArgumentException {
        String format;
        this.orig_year = ExploreByTouchHelper.INVALID_ID;
        this.orig_month = ExploreByTouchHelper.INVALID_ID;
        this.orig_day = ExploreByTouchHelper.INVALID_ID;
        this.orig_hour = ExploreByTouchHelper.INVALID_ID;
        this.orig_minute = ExploreByTouchHelper.INVALID_ID;
        this.orig_second = ExploreByTouchHelper.INVALID_ID;
        this.orig_timezone = ExploreByTouchHelper.INVALID_ID;
        this.eon = null;
        this.year = ExploreByTouchHelper.INVALID_ID;
        this.month = ExploreByTouchHelper.INVALID_ID;
        this.day = ExploreByTouchHelper.INVALID_ID;
        this.timezone = ExploreByTouchHelper.INVALID_ID;
        this.hour = ExploreByTouchHelper.INVALID_ID;
        this.minute = ExploreByTouchHelper.INVALID_ID;
        this.second = ExploreByTouchHelper.INVALID_ID;
        this.fractionalSecond = null;
        String lexRep = lexicalRepresentation;
        int lexRepLength = lexRep.length();
        if (lexRep.indexOf(84) != -1) {
            format = "%Y-%M-%DT%h:%m:%s%z";
        } else if (lexRepLength >= HOUR && lexRep.charAt(DAY) == ':') {
            format = "%h:%m:%s%z";
        } else if (!lexRep.startsWith("--")) {
            int countSeparator = YEAR;
            if (lexRep.indexOf(58) != -1) {
                lexRepLength -= 6;
            }
            for (int i = MONTH; i < lexRepLength; i += MONTH) {
                if (lexRep.charAt(i) == '-') {
                    countSeparator += MONTH;
                }
            }
            if (countSeparator == 0) {
                format = "%Y%z";
            } else if (countSeparator == MONTH) {
                format = "%Y-%M%z";
            } else {
                format = "%Y-%M-%D%z";
            }
        } else if (lexRepLength >= HOUR && lexRep.charAt(DAY) == '-') {
            format = "---%D%z";
        } else if (lexRepLength == MINUTE || (lexRepLength >= MILLISECOND && (lexRep.charAt(MINUTE) == '+' || (lexRep.charAt(MINUTE) == '-' && (lexRep.charAt(SECOND) == '-' || lexRepLength == 10))))) {
            try {
                new Parser("--%M--%z", lexRep, null).parse();
                if (isValid()) {
                    save();
                    return;
                }
                Object[] objArr = new Object[MONTH];
                objArr[YEAR] = lexicalRepresentation;
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCRepresentation", objArr));
            } catch (IllegalArgumentException e) {
                format = "--%M%z";
            }
        } else {
            format = "--%M-%D%z";
        }
        new Parser(format, lexRep, null).parse();
        if (isValid()) {
            save();
            return;
        }
        objArr = new Object[MONTH];
        objArr[YEAR] = lexicalRepresentation;
        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCRepresentation", objArr));
    }

    private void save() {
        this.orig_eon = this.eon;
        this.orig_year = this.year;
        this.orig_month = this.month;
        this.orig_day = this.day;
        this.orig_hour = this.hour;
        this.orig_minute = this.minute;
        this.orig_second = this.second;
        this.orig_fracSeconds = this.fractionalSecond;
        this.orig_timezone = this.timezone;
    }

    public XMLGregorianCalendarImpl() {
        this.orig_year = ExploreByTouchHelper.INVALID_ID;
        this.orig_month = ExploreByTouchHelper.INVALID_ID;
        this.orig_day = ExploreByTouchHelper.INVALID_ID;
        this.orig_hour = ExploreByTouchHelper.INVALID_ID;
        this.orig_minute = ExploreByTouchHelper.INVALID_ID;
        this.orig_second = ExploreByTouchHelper.INVALID_ID;
        this.orig_timezone = ExploreByTouchHelper.INVALID_ID;
        this.eon = null;
        this.year = ExploreByTouchHelper.INVALID_ID;
        this.month = ExploreByTouchHelper.INVALID_ID;
        this.day = ExploreByTouchHelper.INVALID_ID;
        this.timezone = ExploreByTouchHelper.INVALID_ID;
        this.hour = ExploreByTouchHelper.INVALID_ID;
        this.minute = ExploreByTouchHelper.INVALID_ID;
        this.second = ExploreByTouchHelper.INVALID_ID;
        this.fractionalSecond = null;
    }

    protected XMLGregorianCalendarImpl(BigInteger year, int month, int day, int hour, int minute, int second, BigDecimal fractionalSecond, int timezone) {
        this.orig_year = ExploreByTouchHelper.INVALID_ID;
        this.orig_month = ExploreByTouchHelper.INVALID_ID;
        this.orig_day = ExploreByTouchHelper.INVALID_ID;
        this.orig_hour = ExploreByTouchHelper.INVALID_ID;
        this.orig_minute = ExploreByTouchHelper.INVALID_ID;
        this.orig_second = ExploreByTouchHelper.INVALID_ID;
        this.orig_timezone = ExploreByTouchHelper.INVALID_ID;
        this.eon = null;
        this.year = ExploreByTouchHelper.INVALID_ID;
        this.month = ExploreByTouchHelper.INVALID_ID;
        this.day = ExploreByTouchHelper.INVALID_ID;
        this.timezone = ExploreByTouchHelper.INVALID_ID;
        this.hour = ExploreByTouchHelper.INVALID_ID;
        this.minute = ExploreByTouchHelper.INVALID_ID;
        this.second = ExploreByTouchHelper.INVALID_ID;
        this.fractionalSecond = null;
        setYear(year);
        setMonth(month);
        setDay(day);
        setTime(hour, minute, second, fractionalSecond);
        setTimezone(timezone);
        if (isValid()) {
            save();
        } else {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCValue-fractional", new Object[]{year, new Integer(month), new Integer(day), new Integer(hour), new Integer(minute), new Integer(second), fractionalSecond, new Integer(timezone)}));
        }
    }

    private XMLGregorianCalendarImpl(int year, int month, int day, int hour, int minute, int second, int millisecond, int timezone) {
        this.orig_year = ExploreByTouchHelper.INVALID_ID;
        this.orig_month = ExploreByTouchHelper.INVALID_ID;
        this.orig_day = ExploreByTouchHelper.INVALID_ID;
        this.orig_hour = ExploreByTouchHelper.INVALID_ID;
        this.orig_minute = ExploreByTouchHelper.INVALID_ID;
        this.orig_second = ExploreByTouchHelper.INVALID_ID;
        this.orig_timezone = ExploreByTouchHelper.INVALID_ID;
        this.eon = null;
        this.year = ExploreByTouchHelper.INVALID_ID;
        this.month = ExploreByTouchHelper.INVALID_ID;
        this.day = ExploreByTouchHelper.INVALID_ID;
        this.timezone = ExploreByTouchHelper.INVALID_ID;
        this.hour = ExploreByTouchHelper.INVALID_ID;
        this.minute = ExploreByTouchHelper.INVALID_ID;
        this.second = ExploreByTouchHelper.INVALID_ID;
        this.fractionalSecond = null;
        setYear(year);
        setMonth(month);
        setDay(day);
        setTime(hour, minute, second);
        setTimezone(timezone);
        BigDecimal realMilliseconds = null;
        if (millisecond != ExploreByTouchHelper.INVALID_ID) {
            realMilliseconds = BigDecimal.valueOf((long) millisecond, HOUR);
        }
        setFractionalSecond(realMilliseconds);
        if (isValid()) {
            save();
        } else {
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCValue-milli", new Object[]{new Integer(year), new Integer(month), new Integer(day), new Integer(hour), new Integer(minute), new Integer(second), new Integer(millisecond), new Integer(timezone)}));
        }
    }

    public XMLGregorianCalendarImpl(GregorianCalendar cal) {
        this.orig_year = ExploreByTouchHelper.INVALID_ID;
        this.orig_month = ExploreByTouchHelper.INVALID_ID;
        this.orig_day = ExploreByTouchHelper.INVALID_ID;
        this.orig_hour = ExploreByTouchHelper.INVALID_ID;
        this.orig_minute = ExploreByTouchHelper.INVALID_ID;
        this.orig_second = ExploreByTouchHelper.INVALID_ID;
        this.orig_timezone = ExploreByTouchHelper.INVALID_ID;
        this.eon = null;
        this.year = ExploreByTouchHelper.INVALID_ID;
        this.month = ExploreByTouchHelper.INVALID_ID;
        this.day = ExploreByTouchHelper.INVALID_ID;
        this.timezone = ExploreByTouchHelper.INVALID_ID;
        this.hour = ExploreByTouchHelper.INVALID_ID;
        this.minute = ExploreByTouchHelper.INVALID_ID;
        this.second = ExploreByTouchHelper.INVALID_ID;
        this.fractionalSecond = null;
        int year = cal.get(MONTH);
        if (cal.get(YEAR) == 0) {
            year = -year;
        }
        setYear(year);
        setMonth(cal.get(DAY) + MONTH);
        setDay(cal.get(SECOND));
        setTime(cal.get(11), cal.get(12), cal.get(13), cal.get(14));
        setTimezone((cal.get(15) + cal.get(16)) / DateUtils.MILLIS_IN_MINUTE);
        save();
    }

    public static XMLGregorianCalendar createDateTime(BigInteger year, int month, int day, int hours, int minutes, int seconds, BigDecimal fractionalSecond, int timezone) {
        return new XMLGregorianCalendarImpl(year, month, day, hours, minutes, seconds, fractionalSecond, timezone);
    }

    public static XMLGregorianCalendar createDateTime(int year, int month, int day, int hour, int minute, int second) {
        return new XMLGregorianCalendarImpl(year, month, day, hour, minute, second, (int) ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID);
    }

    public static XMLGregorianCalendar createDateTime(int year, int month, int day, int hours, int minutes, int seconds, int milliseconds, int timezone) {
        return new XMLGregorianCalendarImpl(year, month, day, hours, minutes, seconds, milliseconds, timezone);
    }

    public static XMLGregorianCalendar createDate(int year, int month, int day, int timezone) {
        return new XMLGregorianCalendarImpl(year, month, day, (int) ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, timezone);
    }

    public static XMLGregorianCalendar createTime(int hours, int minutes, int seconds, int timezone) {
        return new XMLGregorianCalendarImpl((int) ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, hours, minutes, seconds, ExploreByTouchHelper.INVALID_ID, timezone);
    }

    public static XMLGregorianCalendar createTime(int hours, int minutes, int seconds, BigDecimal fractionalSecond, int timezone) {
        return new XMLGregorianCalendarImpl(null, (int) ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, hours, minutes, seconds, fractionalSecond, timezone);
    }

    public static XMLGregorianCalendar createTime(int hours, int minutes, int seconds, int milliseconds, int timezone) {
        return new XMLGregorianCalendarImpl((int) ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, hours, minutes, seconds, milliseconds, timezone);
    }

    public BigInteger getEon() {
        return this.eon;
    }

    public int getYear() {
        return this.year;
    }

    public BigInteger getEonAndYear() {
        if (this.year != ExploreByTouchHelper.INVALID_ID && this.eon != null) {
            return this.eon.add(BigInteger.valueOf((long) this.year));
        }
        if (this.year == ExploreByTouchHelper.INVALID_ID || this.eon != null) {
            return null;
        }
        return BigInteger.valueOf((long) this.year);
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getTimezone() {
        return this.timezone;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getSecond() {
        return this.second;
    }

    private BigDecimal getSeconds() {
        if (this.second == ExploreByTouchHelper.INVALID_ID) {
            return DECIMAL_ZERO;
        }
        BigDecimal result = BigDecimal.valueOf((long) this.second);
        if (this.fractionalSecond != null) {
            return result.add(this.fractionalSecond);
        }
        return result;
    }

    public int getMillisecond() {
        if (this.fractionalSecond == null) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        return this.fractionalSecond.movePointRight(HOUR).intValue();
    }

    public BigDecimal getFractionalSecond() {
        return this.fractionalSecond;
    }

    public void setYear(BigInteger year) {
        if (year == null) {
            this.eon = null;
            this.year = ExploreByTouchHelper.INVALID_ID;
            return;
        }
        BigInteger temp = year.remainder(BILLION_B);
        this.year = temp.intValue();
        setEon(year.subtract(temp));
    }

    public void setYear(int year) {
        if (year == ExploreByTouchHelper.INVALID_ID) {
            this.year = ExploreByTouchHelper.INVALID_ID;
            this.eon = null;
        } else if (Math.abs(year) < BILLION_I) {
            this.year = year;
            this.eon = null;
        } else {
            BigInteger theYear = BigInteger.valueOf((long) year);
            BigInteger remainder = theYear.remainder(BILLION_B);
            this.year = remainder.intValue();
            setEon(theYear.subtract(remainder));
        }
    }

    private void setEon(BigInteger eon) {
        if (eon == null || eon.compareTo(BigInteger.ZERO) != 0) {
            this.eon = eon;
        } else {
            this.eon = null;
        }
    }

    public void setMonth(int month) {
        checkFieldValueConstraint(MONTH, month);
        this.month = month;
    }

    public void setDay(int day) {
        checkFieldValueConstraint(DAY, day);
        this.day = day;
    }

    public void setTimezone(int offset) {
        checkFieldValueConstraint(TIMEZONE, offset);
        this.timezone = offset;
    }

    public void setTime(int hour, int minute, int second) {
        setTime(hour, minute, second, null);
    }

    private void checkFieldValueConstraint(int field, int value) throws IllegalArgumentException {
        if ((value < MIN_FIELD_VALUE[field] && value != ExploreByTouchHelper.INVALID_ID) || value > MAX_FIELD_VALUE[field]) {
            Object[] objArr = new Object[DAY];
            objArr[YEAR] = new Integer(value);
            objArr[MONTH] = FIELD_NAME[field];
            throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidFieldValue", objArr));
        }
    }

    public void setHour(int hour) {
        checkFieldValueConstraint(HOUR, hour);
        this.hour = hour;
    }

    public void setMinute(int minute) {
        checkFieldValueConstraint(MINUTE, minute);
        this.minute = minute;
    }

    public void setSecond(int second) {
        checkFieldValueConstraint(SECOND, second);
        this.second = second;
    }

    public void setTime(int hour, int minute, int second, BigDecimal fractional) {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
        setFractionalSecond(fractional);
    }

    public void setTime(int hour, int minute, int second, int millisecond) {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
        setMillisecond(millisecond);
    }

    public int compare(XMLGregorianCalendar rhs) {
        XMLGregorianCalendar Q = rhs;
        XMLGregorianCalendar P;
        if (getTimezone() == Q.getTimezone()) {
            return internalCompare(P, Q);
        }
        if (getTimezone() != ExploreByTouchHelper.INVALID_ID && Q.getTimezone() != ExploreByTouchHelper.INVALID_ID) {
            return internalCompare((XMLGregorianCalendarImpl) normalize(), (XMLGregorianCalendarImpl) Q.normalize());
        }
        int result;
        if (getTimezone() != ExploreByTouchHelper.INVALID_ID) {
            if (getTimezone() != 0) {
                P = (XMLGregorianCalendarImpl) normalize();
            }
            result = internalCompare(P, normalizeToTimezone(Q, DatatypeConstants.MIN_TIMEZONE_OFFSET));
            if (result == -1) {
                return result;
            }
            result = internalCompare(P, normalizeToTimezone(Q, DatatypeConstants.MAX_TIMEZONE_OFFSET));
            if (result == MONTH) {
                return result;
            }
            return DAY;
        }
        if (Q.getTimezone() != 0) {
            Q = (XMLGregorianCalendarImpl) normalizeToTimezone(Q, Q.getTimezone());
        }
        result = internalCompare(normalizeToTimezone(P, DatatypeConstants.MAX_TIMEZONE_OFFSET), Q);
        if (result == -1) {
            return result;
        }
        result = internalCompare(normalizeToTimezone(P, DatatypeConstants.MIN_TIMEZONE_OFFSET), Q);
        if (result == MONTH) {
            return result;
        }
        return DAY;
    }

    public XMLGregorianCalendar normalize() {
        XMLGregorianCalendar normalized = normalizeToTimezone(this, this.timezone);
        if (getTimezone() == ExploreByTouchHelper.INVALID_ID) {
            normalized.setTimezone(ExploreByTouchHelper.INVALID_ID);
        }
        if (getMillisecond() == ExploreByTouchHelper.INVALID_ID) {
            normalized.setMillisecond(ExploreByTouchHelper.INVALID_ID);
        }
        return normalized;
    }

    private XMLGregorianCalendar normalizeToTimezone(XMLGregorianCalendar cal, int timezone) {
        boolean z;
        XMLGregorianCalendar result = (XMLGregorianCalendar) cal.clone();
        int minutes = -timezone;
        if (minutes >= 0) {
            z = true;
        } else {
            z = false;
        }
        result.add(new DurationImpl(z, (int) YEAR, YEAR, YEAR, YEAR, minutes < 0 ? -minutes : minutes, YEAR));
        result.setTimezone(YEAR);
        return result;
    }

    private static int internalCompare(XMLGregorianCalendar P, XMLGregorianCalendar Q) {
        int result;
        if (P.getEon() == Q.getEon()) {
            result = compareField(P.getYear(), Q.getYear());
            if (result != 0) {
                return result;
            }
        }
        result = compareField(P.getEonAndYear(), Q.getEonAndYear());
        if (result != 0) {
            return result;
        }
        result = compareField(P.getMonth(), Q.getMonth());
        if (result != 0) {
            return result;
        }
        result = compareField(P.getDay(), Q.getDay());
        if (result != 0) {
            return result;
        }
        result = compareField(P.getHour(), Q.getHour());
        if (result != 0) {
            return result;
        }
        result = compareField(P.getMinute(), Q.getMinute());
        if (result != 0) {
            return result;
        }
        result = compareField(P.getSecond(), Q.getSecond());
        if (result != 0) {
            return result;
        }
        return compareField(P.getFractionalSecond(), Q.getFractionalSecond());
    }

    private static int compareField(int Pfield, int Qfield) {
        if (Pfield == Qfield) {
            return YEAR;
        }
        if (Pfield == ExploreByTouchHelper.INVALID_ID || Qfield == ExploreByTouchHelper.INVALID_ID) {
            return DAY;
        }
        return Pfield < Qfield ? -1 : MONTH;
    }

    private static int compareField(BigInteger Pfield, BigInteger Qfield) {
        if (Pfield == null) {
            if (Qfield == null) {
                return YEAR;
            }
            return DAY;
        } else if (Qfield != null) {
            return Pfield.compareTo(Qfield);
        } else {
            return DAY;
        }
    }

    private static int compareField(BigDecimal Pfield, BigDecimal Qfield) {
        if (Pfield == Qfield) {
            return YEAR;
        }
        if (Pfield == null) {
            Pfield = DECIMAL_ZERO;
        }
        if (Qfield == null) {
            Qfield = DECIMAL_ZERO;
        }
        return Pfield.compareTo(Qfield);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XMLGregorianCalendar)) {
            return false;
        }
        if (compare((XMLGregorianCalendar) obj) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int timezone = getTimezone();
        if (timezone == ExploreByTouchHelper.INVALID_ID) {
            timezone = YEAR;
        }
        XMLGregorianCalendar gc = this;
        if (timezone != 0) {
            gc = normalizeToTimezone(this, getTimezone());
        }
        return ((((gc.getYear() + gc.getMonth()) + gc.getDay()) + gc.getHour()) + gc.getMinute()) + gc.getSecond();
    }

    public static XMLGregorianCalendar parse(String lexicalRepresentation) {
        return new XMLGregorianCalendarImpl(lexicalRepresentation);
    }

    public String toXMLFormat() {
        QName typekind = getXMLSchemaType();
        String formatString = null;
        if (typekind == DatatypeConstants.DATETIME) {
            formatString = "%Y-%M-%DT%h:%m:%s%z";
        } else if (typekind == DatatypeConstants.DATE) {
            formatString = "%Y-%M-%D%z";
        } else if (typekind == DatatypeConstants.TIME) {
            formatString = "%h:%m:%s%z";
        } else if (typekind == DatatypeConstants.GMONTH) {
            formatString = "--%M--%z";
        } else if (typekind == DatatypeConstants.GDAY) {
            formatString = "---%D%z";
        } else if (typekind == DatatypeConstants.GYEAR) {
            formatString = "%Y%z";
        } else if (typekind == DatatypeConstants.GYEARMONTH) {
            formatString = "%Y-%M%z";
        } else if (typekind == DatatypeConstants.GMONTHDAY) {
            formatString = "--%M-%D%z";
        }
        return format(formatString);
    }

    public QName getXMLSchemaType() {
        if (this.year != ExploreByTouchHelper.INVALID_ID && this.month != ExploreByTouchHelper.INVALID_ID && this.day != ExploreByTouchHelper.INVALID_ID && this.hour != ExploreByTouchHelper.INVALID_ID && this.minute != ExploreByTouchHelper.INVALID_ID && this.second != ExploreByTouchHelper.INVALID_ID) {
            return DatatypeConstants.DATETIME;
        }
        if (this.year != ExploreByTouchHelper.INVALID_ID && this.month != ExploreByTouchHelper.INVALID_ID && this.day != ExploreByTouchHelper.INVALID_ID && this.hour == ExploreByTouchHelper.INVALID_ID && this.minute == ExploreByTouchHelper.INVALID_ID && this.second == ExploreByTouchHelper.INVALID_ID) {
            return DatatypeConstants.DATE;
        }
        if (this.year == ExploreByTouchHelper.INVALID_ID && this.month == ExploreByTouchHelper.INVALID_ID && this.day == ExploreByTouchHelper.INVALID_ID && this.hour != ExploreByTouchHelper.INVALID_ID && this.minute != ExploreByTouchHelper.INVALID_ID && this.second != ExploreByTouchHelper.INVALID_ID) {
            return DatatypeConstants.TIME;
        }
        if (this.year != ExploreByTouchHelper.INVALID_ID && this.month != ExploreByTouchHelper.INVALID_ID && this.day == ExploreByTouchHelper.INVALID_ID && this.hour == ExploreByTouchHelper.INVALID_ID && this.minute == ExploreByTouchHelper.INVALID_ID && this.second == ExploreByTouchHelper.INVALID_ID) {
            return DatatypeConstants.GYEARMONTH;
        }
        if (this.year == ExploreByTouchHelper.INVALID_ID && this.month != ExploreByTouchHelper.INVALID_ID && this.day != ExploreByTouchHelper.INVALID_ID && this.hour == ExploreByTouchHelper.INVALID_ID && this.minute == ExploreByTouchHelper.INVALID_ID && this.second == ExploreByTouchHelper.INVALID_ID) {
            return DatatypeConstants.GMONTHDAY;
        }
        if (this.year != ExploreByTouchHelper.INVALID_ID && this.month == ExploreByTouchHelper.INVALID_ID && this.day == ExploreByTouchHelper.INVALID_ID && this.hour == ExploreByTouchHelper.INVALID_ID && this.minute == ExploreByTouchHelper.INVALID_ID && this.second == ExploreByTouchHelper.INVALID_ID) {
            return DatatypeConstants.GYEAR;
        }
        if (this.year == ExploreByTouchHelper.INVALID_ID && this.month != ExploreByTouchHelper.INVALID_ID && this.day == ExploreByTouchHelper.INVALID_ID && this.hour == ExploreByTouchHelper.INVALID_ID && this.minute == ExploreByTouchHelper.INVALID_ID && this.second == ExploreByTouchHelper.INVALID_ID) {
            return DatatypeConstants.GMONTH;
        }
        if (this.year == ExploreByTouchHelper.INVALID_ID && this.month == ExploreByTouchHelper.INVALID_ID && this.day != ExploreByTouchHelper.INVALID_ID && this.hour == ExploreByTouchHelper.INVALID_ID && this.minute == ExploreByTouchHelper.INVALID_ID && this.second == ExploreByTouchHelper.INVALID_ID) {
            return DatatypeConstants.GDAY;
        }
        throw new IllegalStateException(new StringBuilder(String.valueOf(getClass().getName())).append("#getXMLSchemaType() :").append(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCFields", null)).toString());
    }

    public boolean isValid() {
        if (!(this.month == ExploreByTouchHelper.INVALID_ID || this.day == ExploreByTouchHelper.INVALID_ID)) {
            if (this.year != ExploreByTouchHelper.INVALID_ID) {
                if (this.eon == null) {
                    if (this.day > maximumDayInMonthFor(this.year, this.month)) {
                        return false;
                    }
                } else if (this.day > maximumDayInMonthFor(getEonAndYear(), this.month)) {
                    return false;
                }
            } else if (this.day > maximumDayInMonthFor(2000, this.month)) {
                return false;
            }
        }
        if (this.hour == 24) {
            if (this.minute != 0 || this.second != 0) {
                return false;
            }
            if (!(this.fractionalSecond == null || this.fractionalSecond.compareTo(DECIMAL_ZERO) == 0)) {
                return false;
            }
        }
        if (this.eon == null && this.year == 0) {
            return false;
        }
        return true;
    }

    public void add(Duration duration) {
        BigDecimal startSeconds;
        BigInteger tempDays;
        boolean[] fieldUndefined = new boolean[MILLISECOND];
        int signum = duration.getSign();
        int startMonth = getMonth();
        if (startMonth == Integer.MIN_VALUE) {
            startMonth = MIN_FIELD_VALUE[MONTH];
            fieldUndefined[MONTH] = true;
        }
        BigInteger dMonths = sanitize(duration.getField(DatatypeConstants.MONTHS), signum);
        BigInteger temp = BigInteger.valueOf((long) startMonth).add(dMonths);
        setMonth(temp.subtract(BigInteger.ONE).mod(TWELVE).intValue() + MONTH);
        BigInteger carry = new BigDecimal(temp.subtract(BigInteger.ONE)).divide(new BigDecimal(TWELVE), HOUR).toBigInteger();
        BigInteger startYear = getEonAndYear();
        if (startYear == null) {
            fieldUndefined[YEAR] = true;
            startYear = BigInteger.ZERO;
        }
        setYear(startYear.add(sanitize(duration.getField(DatatypeConstants.YEARS), signum)).add(carry));
        if (getSecond() == Integer.MIN_VALUE) {
            fieldUndefined[SECOND] = true;
            startSeconds = DECIMAL_ZERO;
        } else {
            startSeconds = getSeconds();
        }
        BigDecimal tempBD = startSeconds.add(DurationImpl.sanitize((BigDecimal) duration.getField(DatatypeConstants.SECONDS), signum));
        BigDecimal fQuotient = new BigDecimal(new BigDecimal(tempBD.toBigInteger()).divide(DECIMAL_SIXTY, HOUR).toBigInteger());
        BigDecimal endSeconds = tempBD.subtract(fQuotient.multiply(DECIMAL_SIXTY));
        carry = fQuotient.toBigInteger();
        setSecond(endSeconds.intValue());
        BigDecimal tempFracSeconds = endSeconds.subtract(new BigDecimal(BigInteger.valueOf((long) getSecond())));
        if (tempFracSeconds.compareTo(DECIMAL_ZERO) < 0) {
            setFractionalSecond(DECIMAL_ONE.add(tempFracSeconds));
            if (getSecond() == 0) {
                setSecond(59);
                carry = carry.subtract(BigInteger.ONE);
            } else {
                setSecond(getSecond() - 1);
            }
        } else {
            setFractionalSecond(tempFracSeconds);
        }
        int startMinutes = getMinute();
        if (startMinutes == Integer.MIN_VALUE) {
            fieldUndefined[MINUTE] = true;
            startMinutes = MIN_FIELD_VALUE[MINUTE];
        }
        BigInteger dMinutes = sanitize(duration.getField(DatatypeConstants.MINUTES), signum);
        temp = BigInteger.valueOf((long) startMinutes).add(dMinutes).add(carry);
        setMinute(temp.mod(SIXTY).intValue());
        carry = new BigDecimal(temp).divide(DECIMAL_SIXTY, HOUR).toBigInteger();
        int startHours = getHour();
        if (startHours == Integer.MIN_VALUE) {
            fieldUndefined[HOUR] = true;
            startHours = MIN_FIELD_VALUE[HOUR];
        }
        BigInteger dHours = sanitize(duration.getField(DatatypeConstants.HOURS), signum);
        temp = BigInteger.valueOf((long) startHours).add(dHours).add(carry);
        setHour(temp.mod(TWENTY_FOUR).intValue());
        carry = new BigDecimal(temp).divide(new BigDecimal(TWENTY_FOUR), HOUR).toBigInteger();
        int startDay = getDay();
        if (startDay == Integer.MIN_VALUE) {
            fieldUndefined[DAY] = true;
            startDay = MIN_FIELD_VALUE[DAY];
        }
        BigInteger dDays = sanitize(duration.getField(DatatypeConstants.DAYS), signum);
        int maxDayInMonth = maximumDayInMonthFor(getEonAndYear(), getMonth());
        if (startDay > maxDayInMonth) {
            tempDays = BigInteger.valueOf((long) maxDayInMonth);
        } else if (startDay < MONTH) {
            tempDays = BigInteger.ONE;
        } else {
            tempDays = BigInteger.valueOf((long) startDay);
        }
        BigInteger endDays = tempDays.add(dDays).add(carry);
        while (true) {
            int monthCarry;
            int quotient;
            if (endDays.compareTo(BigInteger.ONE) >= 0) {
                if (endDays.compareTo(BigInteger.valueOf((long) maximumDayInMonthFor(getEonAndYear(), getMonth()))) <= 0) {
                    break;
                }
                endDays = endDays.add(BigInteger.valueOf((long) (-maximumDayInMonthFor(getEonAndYear(), getMonth()))));
                monthCarry = MONTH;
            } else {
                BigInteger mdimf;
                int i = this.month;
                if (r0 >= DAY) {
                    mdimf = BigInteger.valueOf((long) maximumDayInMonthFor(getEonAndYear(), getMonth() - 1));
                } else {
                    mdimf = BigInteger.valueOf((long) maximumDayInMonthFor(getEonAndYear().subtract(BigInteger.valueOf(1)), 12));
                }
                endDays = endDays.add(mdimf);
                monthCarry = -1;
            }
            int intTemp = getMonth() + monthCarry;
            int endMonth = (intTemp - 1) % 12;
            if (endMonth < 0) {
                endMonth = (endMonth + 12) + MONTH;
                quotient = BigDecimal.valueOf((long) (intTemp - 1)).divide(new BigDecimal(TWELVE), YEAR).intValue();
            } else {
                quotient = (intTemp - 1) / 12;
                endMonth += MONTH;
            }
            setMonth(endMonth);
            if (quotient != 0) {
                setYear(getEonAndYear().add(BigInteger.valueOf((long) quotient)));
            }
        }
        setDay(endDays.intValue());
        for (int i2 = YEAR; i2 <= SECOND; i2 += MONTH) {
            if (fieldUndefined[i2]) {
                switch (i2) {
                    case YEAR /*0*/:
                        setYear(ExploreByTouchHelper.INVALID_ID);
                        break;
                    case MONTH /*1*/:
                        setMonth(ExploreByTouchHelper.INVALID_ID);
                        break;
                    case DAY /*2*/:
                        setDay(ExploreByTouchHelper.INVALID_ID);
                        break;
                    case HOUR /*3*/:
                        setHour(ExploreByTouchHelper.INVALID_ID);
                        break;
                    case MINUTE /*4*/:
                        setMinute(ExploreByTouchHelper.INVALID_ID);
                        break;
                    case SECOND /*5*/:
                        setSecond(ExploreByTouchHelper.INVALID_ID);
                        setFractionalSecond(null);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static int maximumDayInMonthFor(BigInteger year, int month) {
        if (month != DAY) {
            return DaysInMonth.table[month];
        }
        if (year.mod(FOUR_HUNDRED).equals(BigInteger.ZERO) || (!year.mod(HUNDRED).equals(BigInteger.ZERO) && year.mod(FOUR).equals(BigInteger.ZERO))) {
            return 29;
        }
        return DaysInMonth.table[month];
    }

    private static int maximumDayInMonthFor(int year, int month) {
        if (month != DAY) {
            return DaysInMonth.table[month];
        }
        if (year % HttpStatus.SC_BAD_REQUEST == 0 || (year % 100 != 0 && year % MINUTE == 0)) {
            return 29;
        }
        return DaysInMonth.table[DAY];
    }

    public GregorianCalendar toGregorianCalendar() {
        GregorianCalendar result = new GregorianCalendar(getTimeZone(ExploreByTouchHelper.INVALID_ID), Locale.getDefault());
        result.clear();
        result.setGregorianChange(PURE_GREGORIAN_CHANGE);
        if (this.year != ExploreByTouchHelper.INVALID_ID) {
            if (this.eon == null) {
                result.set(YEAR, this.year < 0 ? YEAR : MONTH);
                result.set(MONTH, Math.abs(this.year));
            } else {
                int i;
                BigInteger eonAndYear = getEonAndYear();
                if (eonAndYear.signum() == -1) {
                    i = YEAR;
                } else {
                    i = MONTH;
                }
                result.set(YEAR, i);
                result.set(MONTH, eonAndYear.abs().intValue());
            }
        }
        if (this.month != ExploreByTouchHelper.INVALID_ID) {
            result.set(DAY, this.month - 1);
        }
        if (this.day != ExploreByTouchHelper.INVALID_ID) {
            result.set(SECOND, this.day);
        }
        if (this.hour != ExploreByTouchHelper.INVALID_ID) {
            result.set(11, this.hour);
        }
        if (this.minute != ExploreByTouchHelper.INVALID_ID) {
            result.set(12, this.minute);
        }
        if (this.second != ExploreByTouchHelper.INVALID_ID) {
            result.set(13, this.second);
        }
        if (this.fractionalSecond != null) {
            result.set(14, getMillisecond());
        }
        return result;
    }

    public GregorianCalendar toGregorianCalendar(TimeZone timezone, Locale aLocale, XMLGregorianCalendar defaults) {
        TimeZone tz = timezone;
        if (tz == null) {
            int defaultZoneoffset = ExploreByTouchHelper.INVALID_ID;
            if (defaults != null) {
                defaultZoneoffset = defaults.getTimezone();
            }
            tz = getTimeZone(defaultZoneoffset);
        }
        if (aLocale == null) {
            aLocale = Locale.getDefault();
        }
        GregorianCalendar result = new GregorianCalendar(tz, aLocale);
        result.clear();
        result.setGregorianChange(PURE_GREGORIAN_CHANGE);
        if (this.year != ExploreByTouchHelper.INVALID_ID) {
            if (this.eon == null) {
                result.set(YEAR, this.year < 0 ? YEAR : MONTH);
                result.set(MONTH, Math.abs(this.year));
            } else {
                BigInteger eonAndYear = getEonAndYear();
                result.set(YEAR, eonAndYear.signum() == -1 ? YEAR : MONTH);
                result.set(MONTH, eonAndYear.abs().intValue());
            }
        } else if (defaults != null) {
            int defaultYear = defaults.getYear();
            if (defaultYear != ExploreByTouchHelper.INVALID_ID) {
                if (defaults.getEon() == null) {
                    result.set(YEAR, defaultYear < 0 ? YEAR : MONTH);
                    result.set(MONTH, Math.abs(defaultYear));
                } else {
                    BigInteger defaultEonAndYear = defaults.getEonAndYear();
                    result.set(YEAR, defaultEonAndYear.signum() == -1 ? YEAR : MONTH);
                    result.set(MONTH, defaultEonAndYear.abs().intValue());
                }
            }
        }
        if (this.month != ExploreByTouchHelper.INVALID_ID) {
            result.set(DAY, this.month - 1);
        } else {
            int defaultMonth = defaults != null ? defaults.getMonth() : ExploreByTouchHelper.INVALID_ID;
            if (defaultMonth != ExploreByTouchHelper.INVALID_ID) {
                result.set(DAY, defaultMonth - 1);
            }
        }
        if (this.day != ExploreByTouchHelper.INVALID_ID) {
            result.set(SECOND, this.day);
        } else {
            int defaultDay = defaults != null ? defaults.getDay() : ExploreByTouchHelper.INVALID_ID;
            if (defaultDay != ExploreByTouchHelper.INVALID_ID) {
                result.set(SECOND, defaultDay);
            }
        }
        if (this.hour != ExploreByTouchHelper.INVALID_ID) {
            result.set(11, this.hour);
        } else {
            int defaultHour = defaults != null ? defaults.getHour() : ExploreByTouchHelper.INVALID_ID;
            if (defaultHour != ExploreByTouchHelper.INVALID_ID) {
                result.set(11, defaultHour);
            }
        }
        if (this.minute != ExploreByTouchHelper.INVALID_ID) {
            result.set(12, this.minute);
        } else {
            int defaultMinute = defaults != null ? defaults.getMinute() : ExploreByTouchHelper.INVALID_ID;
            if (defaultMinute != ExploreByTouchHelper.INVALID_ID) {
                result.set(12, defaultMinute);
            }
        }
        if (this.second != ExploreByTouchHelper.INVALID_ID) {
            result.set(13, this.second);
        } else {
            int defaultSecond = defaults != null ? defaults.getSecond() : ExploreByTouchHelper.INVALID_ID;
            if (defaultSecond != ExploreByTouchHelper.INVALID_ID) {
                result.set(13, defaultSecond);
            }
        }
        if (this.fractionalSecond != null) {
            result.set(14, getMillisecond());
        } else {
            if ((defaults != null ? defaults.getFractionalSecond() : null) != null) {
                result.set(14, defaults.getMillisecond());
            }
        }
        return result;
    }

    public TimeZone getTimeZone(int defaultZoneoffset) {
        int zoneoffset = getTimezone();
        if (zoneoffset == ExploreByTouchHelper.INVALID_ID) {
            zoneoffset = defaultZoneoffset;
        }
        if (zoneoffset == ExploreByTouchHelper.INVALID_ID) {
            return TimeZone.getDefault();
        }
        char sign = zoneoffset < 0 ? '-' : '+';
        if (sign == '-') {
            zoneoffset = -zoneoffset;
        }
        int hour = zoneoffset / 60;
        int minutes = zoneoffset - (hour * 60);
        StringBuffer customTimezoneId = new StringBuffer(8);
        customTimezoneId.append("GMT");
        customTimezoneId.append(sign);
        customTimezoneId.append(hour);
        if (minutes != 0) {
            if (minutes < 10) {
                customTimezoneId.append('0');
            }
            customTimezoneId.append(minutes);
        }
        return TimeZone.getTimeZone(customTimezoneId.toString());
    }

    public Object clone() {
        return new XMLGregorianCalendarImpl(getEonAndYear(), this.month, this.day, this.hour, this.minute, this.second, this.fractionalSecond, this.timezone);
    }

    public void clear() {
        this.eon = null;
        this.year = ExploreByTouchHelper.INVALID_ID;
        this.month = ExploreByTouchHelper.INVALID_ID;
        this.day = ExploreByTouchHelper.INVALID_ID;
        this.timezone = ExploreByTouchHelper.INVALID_ID;
        this.hour = ExploreByTouchHelper.INVALID_ID;
        this.minute = ExploreByTouchHelper.INVALID_ID;
        this.second = ExploreByTouchHelper.INVALID_ID;
        this.fractionalSecond = null;
    }

    public void setMillisecond(int millisecond) {
        if (millisecond == ExploreByTouchHelper.INVALID_ID) {
            this.fractionalSecond = null;
            return;
        }
        checkFieldValueConstraint(MILLISECOND, millisecond);
        this.fractionalSecond = BigDecimal.valueOf((long) millisecond, HOUR);
    }

    public void setFractionalSecond(BigDecimal fractional) {
        if (fractional == null || (fractional.compareTo(DECIMAL_ZERO) >= 0 && fractional.compareTo(DECIMAL_ONE) <= 0)) {
            this.fractionalSecond = fractional;
            return;
        }
        Object[] objArr = new Object[MONTH];
        objArr[YEAR] = fractional;
        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidFractional", objArr));
    }

    private static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    private String format(String format) {
        StringBuffer buf = new StringBuffer();
        int flen = format.length();
        int fidx = YEAR;
        while (fidx < flen) {
            int fidx2 = fidx + MONTH;
            char fch = format.charAt(fidx);
            if (fch != '%') {
                buf.append(fch);
                fidx = fidx2;
            } else {
                fidx = fidx2 + MONTH;
                switch (format.charAt(fidx2)) {
                    case C0302R.styleable.Theme_searchViewStyle /*68*/:
                        printNumber(buf, getDay(), (int) DAY);
                        break;
                    case C0302R.styleable.Theme_textAppearanceListItemSmall /*77*/:
                        printNumber(buf, getMonth(), (int) DAY);
                        break;
                    case C0302R.styleable.Theme_colorSwitchThumbNormal /*89*/:
                        if (this.eon != null) {
                            printNumber(buf, getEonAndYear(), (int) MINUTE);
                            break;
                        }
                        int absYear = this.year;
                        if (absYear < 0) {
                            buf.append('-');
                            absYear = -this.year;
                        }
                        printNumber(buf, absYear, (int) MINUTE);
                        break;
                    case C0302R.styleable.Theme_editTextStyle /*104*/:
                        printNumber(buf, getHour(), (int) DAY);
                        break;
                    case C0302R.styleable.Theme_switchStyle /*109*/:
                        printNumber(buf, getMinute(), (int) DAY);
                        break;
                    case 's':
                        printNumber(buf, getSecond(), (int) DAY);
                        if (getFractionalSecond() == null) {
                            break;
                        }
                        String frac = toString(getFractionalSecond());
                        buf.append(frac.substring(MONTH, frac.length()));
                        break;
                    case 'z':
                        int offset = getTimezone();
                        if (offset != 0) {
                            if (offset != ExploreByTouchHelper.INVALID_ID) {
                                if (offset < 0) {
                                    buf.append('-');
                                    offset *= -1;
                                } else {
                                    buf.append('+');
                                }
                                printNumber(buf, offset / 60, (int) DAY);
                                buf.append(':');
                                printNumber(buf, offset % 60, (int) DAY);
                                break;
                            }
                            break;
                        }
                        buf.append('Z');
                        break;
                    default:
                        throw new InternalError();
                }
            }
        }
        return buf.toString();
    }

    private void printNumber(StringBuffer out, int number, int nDigits) {
        String s = String.valueOf(number);
        for (int i = s.length(); i < nDigits; i += MONTH) {
            out.append('0');
        }
        out.append(s);
    }

    private void printNumber(StringBuffer out, BigInteger number, int nDigits) {
        String s = number.toString();
        for (int i = s.length(); i < nDigits; i += MONTH) {
            out.append('0');
        }
        out.append(s);
    }

    private String toString(BigDecimal bd) {
        String intString = bd.unscaledValue().toString();
        int scale = bd.scale();
        if (scale == 0) {
            return intString;
        }
        int insertionPoint = intString.length() - scale;
        if (insertionPoint == 0) {
            return "0." + intString;
        }
        StringBuffer buf;
        if (insertionPoint > 0) {
            buf = new StringBuffer(intString);
            buf.insert(insertionPoint, ClassUtils.PACKAGE_SEPARATOR_CHAR);
        } else {
            buf = new StringBuffer((3 - insertionPoint) + intString.length());
            buf.append("0.");
            for (int i = YEAR; i < (-insertionPoint); i += MONTH) {
                buf.append('0');
            }
            buf.append(intString);
        }
        return buf.toString();
    }

    static BigInteger sanitize(Number value, int signum) {
        if (signum == 0 || value == null) {
            return BigInteger.ZERO;
        }
        return signum < 0 ? ((BigInteger) value).negate() : (BigInteger) value;
    }

    public void reset() {
        this.eon = this.orig_eon;
        this.year = this.orig_year;
        this.month = this.orig_month;
        this.day = this.orig_day;
        this.hour = this.orig_hour;
        this.minute = this.orig_minute;
        this.second = this.orig_second;
        this.fractionalSecond = this.orig_fracSeconds;
        this.timezone = this.orig_timezone;
    }

    private Object writeReplace() throws IOException {
        return new SerializedXMLGregorianCalendar(toXMLFormat());
    }
}
