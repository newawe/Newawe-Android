package org.apache.commons.lang.time;

import android.support.v4.widget.ExploreByTouchHelper;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.ads.AdSize;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import mf.org.apache.xml.serialize.OutputFormat.Defaults;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.http.HttpStatus;

public class FastDateFormat extends Format {
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static final Map cDateInstanceCache;
    private static final Map cDateTimeInstanceCache;
    private static String cDefaultPattern = null;
    private static final Map cInstanceCache;
    private static final Map cTimeInstanceCache;
    private static final Map cTimeZoneDisplayCache;
    private static final long serialVersionUID = 1;
    private final Locale mLocale;
    private final boolean mLocaleForced;
    private transient int mMaxLengthEstimate;
    private final String mPattern;
    private transient Rule[] mRules;
    private final TimeZone mTimeZone;
    private final boolean mTimeZoneForced;

    private static class Pair {
        private final Object mObj1;
        private final Object mObj2;

        public Pair(Object obj1, Object obj2) {
            this.mObj1 = obj1;
            this.mObj2 = obj2;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r6) {
            /*
            r5 = this;
            r1 = 1;
            r2 = 0;
            if (r5 != r6) goto L_0x0005;
        L_0x0004:
            return r1;
        L_0x0005:
            r3 = r6 instanceof org.apache.commons.lang.time.FastDateFormat.Pair;
            if (r3 != 0) goto L_0x000b;
        L_0x0009:
            r1 = r2;
            goto L_0x0004;
        L_0x000b:
            r0 = r6;
            r0 = (org.apache.commons.lang.time.FastDateFormat.Pair) r0;
            r3 = r5.mObj1;
            if (r3 != 0) goto L_0x0020;
        L_0x0012:
            r3 = r0.mObj1;
            if (r3 != 0) goto L_0x001e;
        L_0x0016:
            r3 = r5.mObj2;
            if (r3 != 0) goto L_0x002b;
        L_0x001a:
            r3 = r0.mObj2;
            if (r3 == 0) goto L_0x0004;
        L_0x001e:
            r1 = r2;
            goto L_0x0004;
        L_0x0020:
            r3 = r5.mObj1;
            r4 = r0.mObj1;
            r3 = r3.equals(r4);
            if (r3 == 0) goto L_0x001e;
        L_0x002a:
            goto L_0x0016;
        L_0x002b:
            r3 = r5.mObj2;
            r4 = r0.mObj2;
            r3 = r3.equals(r4);
            if (r3 == 0) goto L_0x001e;
        L_0x0035:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.FastDateFormat.Pair.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = FastDateFormat.FULL;
            int hashCode = this.mObj1 == null ? FastDateFormat.FULL : this.mObj1.hashCode();
            if (this.mObj2 != null) {
                i = this.mObj2.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            return new StringBuffer().append("[").append(this.mObj1).append(':').append(this.mObj2).append(']').toString();
        }
    }

    private interface Rule {
        void appendTo(StringBuffer stringBuffer, Calendar calendar);

        int estimateLength();
    }

    private static class TimeZoneDisplayKey {
        private final Locale mLocale;
        private final int mStyle;
        private final TimeZone mTimeZone;

        TimeZoneDisplayKey(TimeZone timeZone, boolean daylight, int style, Locale locale) {
            this.mTimeZone = timeZone;
            if (daylight) {
                style |= ExploreByTouchHelper.INVALID_ID;
            }
            this.mStyle = style;
            this.mLocale = locale;
        }

        public int hashCode() {
            return (this.mStyle * 31) + this.mLocale.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TimeZoneDisplayKey)) {
                return false;
            }
            TimeZoneDisplayKey other = (TimeZoneDisplayKey) obj;
            if (this.mTimeZone.equals(other.mTimeZone) && this.mStyle == other.mStyle && this.mLocale.equals(other.mLocale)) {
                return true;
            }
            return false;
        }
    }

    private static class CharacterLiteral implements Rule {
        private final char mValue;

        CharacterLiteral(char value) {
            this.mValue = value;
        }

        public int estimateLength() {
            return FastDateFormat.LONG;
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            buffer.append(this.mValue);
        }
    }

    private interface NumberRule extends Rule {
        void appendTo(StringBuffer stringBuffer, int i);
    }

    private static class StringLiteral implements Rule {
        private final String mValue;

        StringLiteral(String value) {
            this.mValue = value;
        }

        public int estimateLength() {
            return this.mValue.length();
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            buffer.append(this.mValue);
        }
    }

    private static class TextField implements Rule {
        private final int mField;
        private final String[] mValues;

        TextField(int field, String[] values) {
            this.mField = field;
            this.mValues = values;
        }

        public int estimateLength() {
            int max = FastDateFormat.FULL;
            int i = this.mValues.length;
            while (true) {
                i--;
                if (i < 0) {
                    return max;
                }
                int len = this.mValues[i].length();
                if (len > max) {
                    max = len;
                }
            }
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            buffer.append(this.mValues[calendar.get(this.mField)]);
        }
    }

    private static class TimeZoneNameRule implements Rule {
        private final String mDaylight;
        private final Locale mLocale;
        private final String mStandard;
        private final int mStyle;
        private final TimeZone mTimeZone;
        private final boolean mTimeZoneForced;

        TimeZoneNameRule(TimeZone timeZone, boolean timeZoneForced, Locale locale, int style) {
            this.mTimeZone = timeZone;
            this.mTimeZoneForced = timeZoneForced;
            this.mLocale = locale;
            this.mStyle = style;
            if (timeZoneForced) {
                this.mStandard = FastDateFormat.getTimeZoneDisplay(timeZone, false, style, locale);
                this.mDaylight = FastDateFormat.getTimeZoneDisplay(timeZone, true, style, locale);
                return;
            }
            this.mStandard = null;
            this.mDaylight = null;
        }

        public int estimateLength() {
            if (this.mTimeZoneForced) {
                return Math.max(this.mStandard.length(), this.mDaylight.length());
            }
            if (this.mStyle == 0) {
                return 4;
            }
            return 40;
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            if (!this.mTimeZoneForced) {
                TimeZone timeZone = calendar.getTimeZone();
                if (!timeZone.useDaylightTime() || calendar.get(16) == 0) {
                    buffer.append(FastDateFormat.getTimeZoneDisplay(timeZone, false, this.mStyle, this.mLocale));
                } else {
                    buffer.append(FastDateFormat.getTimeZoneDisplay(timeZone, true, this.mStyle, this.mLocale));
                }
            } else if (!this.mTimeZone.useDaylightTime() || calendar.get(16) == 0) {
                buffer.append(this.mStandard);
            } else {
                buffer.append(this.mDaylight);
            }
        }
    }

    private static class TimeZoneNumberRule implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON;
        static final TimeZoneNumberRule INSTANCE_NO_COLON;
        final boolean mColon;

        static {
            INSTANCE_COLON = new TimeZoneNumberRule(true);
            INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
        }

        TimeZoneNumberRule(boolean colon) {
            this.mColon = colon;
        }

        public int estimateLength() {
            return 5;
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            int offset = calendar.get(15) + calendar.get(16);
            if (offset < 0) {
                buffer.append('-');
                offset = -offset;
            } else {
                buffer.append('+');
            }
            int hours = offset / DateUtils.MILLIS_IN_HOUR;
            buffer.append((char) ((hours / 10) + 48));
            buffer.append((char) ((hours % 10) + 48));
            if (this.mColon) {
                buffer.append(':');
            }
            int minutes = (offset / DateUtils.MILLIS_IN_MINUTE) - (hours * 60);
            buffer.append((char) ((minutes / 10) + 48));
            buffer.append((char) ((minutes % 10) + 48));
        }
    }

    private static class PaddedNumberField implements NumberRule {
        private final int mField;
        private final int mSize;

        PaddedNumberField(int field, int size) {
            if (size < FastDateFormat.SHORT) {
                throw new IllegalArgumentException();
            }
            this.mField = field;
            this.mSize = size;
        }

        public int estimateLength() {
            return 4;
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            appendTo(buffer, calendar.get(this.mField));
        }

        public final void appendTo(StringBuffer buffer, int value) {
            int i;
            if (value < 100) {
                i = this.mSize;
                while (true) {
                    i--;
                    if (i >= FastDateFormat.MEDIUM) {
                        buffer.append('0');
                    } else {
                        buffer.append((char) ((value / 10) + 48));
                        buffer.append((char) ((value % 10) + 48));
                        return;
                    }
                }
            }
            int digits;
            if (value < DateUtils.MILLIS_IN_SECOND) {
                digits = FastDateFormat.SHORT;
            } else {
                Validate.isTrue(value > -1, "Negative values should not be possible", (long) value);
                digits = Integer.toString(value).length();
            }
            i = this.mSize;
            while (true) {
                i--;
                if (i >= digits) {
                    buffer.append('0');
                } else {
                    buffer.append(Integer.toString(value));
                    return;
                }
            }
        }
    }

    private static class TwelveHourField implements NumberRule {
        private final NumberRule mRule;

        TwelveHourField(NumberRule rule) {
            this.mRule = rule;
        }

        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            int value = calendar.get(10);
            if (value == 0) {
                value = calendar.getLeastMaximum(10) + FastDateFormat.LONG;
            }
            this.mRule.appendTo(buffer, value);
        }

        public void appendTo(StringBuffer buffer, int value) {
            this.mRule.appendTo(buffer, value);
        }
    }

    private static class TwentyFourHourField implements NumberRule {
        private final NumberRule mRule;

        TwentyFourHourField(NumberRule rule) {
            this.mRule = rule;
        }

        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            int value = calendar.get(11);
            if (value == 0) {
                value = calendar.getMaximum(11) + FastDateFormat.LONG;
            }
            this.mRule.appendTo(buffer, value);
        }

        public void appendTo(StringBuffer buffer, int value) {
            this.mRule.appendTo(buffer, value);
        }
    }

    private static class TwoDigitMonthField implements NumberRule {
        static final TwoDigitMonthField INSTANCE;

        static {
            INSTANCE = new TwoDigitMonthField();
        }

        TwoDigitMonthField() {
        }

        public int estimateLength() {
            return FastDateFormat.MEDIUM;
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            appendTo(buffer, calendar.get(FastDateFormat.MEDIUM) + FastDateFormat.LONG);
        }

        public final void appendTo(StringBuffer buffer, int value) {
            buffer.append((char) ((value / 10) + 48));
            buffer.append((char) ((value % 10) + 48));
        }
    }

    private static class TwoDigitNumberField implements NumberRule {
        private final int mField;

        TwoDigitNumberField(int field) {
            this.mField = field;
        }

        public int estimateLength() {
            return FastDateFormat.MEDIUM;
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            appendTo(buffer, calendar.get(this.mField));
        }

        public final void appendTo(StringBuffer buffer, int value) {
            if (value < 100) {
                buffer.append((char) ((value / 10) + 48));
                buffer.append((char) ((value % 10) + 48));
                return;
            }
            buffer.append(Integer.toString(value));
        }
    }

    private static class TwoDigitYearField implements NumberRule {
        static final TwoDigitYearField INSTANCE;

        static {
            INSTANCE = new TwoDigitYearField();
        }

        TwoDigitYearField() {
        }

        public int estimateLength() {
            return FastDateFormat.MEDIUM;
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            appendTo(buffer, calendar.get(FastDateFormat.LONG) % 100);
        }

        public final void appendTo(StringBuffer buffer, int value) {
            buffer.append((char) ((value / 10) + 48));
            buffer.append((char) ((value % 10) + 48));
        }
    }

    private static class UnpaddedMonthField implements NumberRule {
        static final UnpaddedMonthField INSTANCE;

        static {
            INSTANCE = new UnpaddedMonthField();
        }

        UnpaddedMonthField() {
        }

        public int estimateLength() {
            return FastDateFormat.MEDIUM;
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            appendTo(buffer, calendar.get(FastDateFormat.MEDIUM) + FastDateFormat.LONG);
        }

        public final void appendTo(StringBuffer buffer, int value) {
            if (value < 10) {
                buffer.append((char) (value + 48));
                return;
            }
            buffer.append((char) ((value / 10) + 48));
            buffer.append((char) ((value % 10) + 48));
        }
    }

    private static class UnpaddedNumberField implements NumberRule {
        private final int mField;

        UnpaddedNumberField(int field) {
            this.mField = field;
        }

        public int estimateLength() {
            return 4;
        }

        public void appendTo(StringBuffer buffer, Calendar calendar) {
            appendTo(buffer, calendar.get(this.mField));
        }

        public final void appendTo(StringBuffer buffer, int value) {
            if (value < 10) {
                buffer.append((char) (value + 48));
            } else if (value < 100) {
                buffer.append((char) ((value / 10) + 48));
                buffer.append((char) ((value % 10) + 48));
            } else {
                buffer.append(Integer.toString(value));
            }
        }
    }

    static {
        cInstanceCache = new HashMap(7);
        cDateInstanceCache = new HashMap(7);
        cTimeInstanceCache = new HashMap(7);
        cDateTimeInstanceCache = new HashMap(7);
        cTimeZoneDisplayCache = new HashMap(7);
    }

    public static FastDateFormat getInstance() {
        return getInstance(getDefaultPattern(), null, null);
    }

    public static FastDateFormat getInstance(String pattern) {
        return getInstance(pattern, null, null);
    }

    public static FastDateFormat getInstance(String pattern, TimeZone timeZone) {
        return getInstance(pattern, timeZone, null);
    }

    public static FastDateFormat getInstance(String pattern, Locale locale) {
        return getInstance(pattern, null, locale);
    }

    public static synchronized FastDateFormat getInstance(String pattern, TimeZone timeZone, Locale locale) {
        FastDateFormat format;
        synchronized (FastDateFormat.class) {
            FastDateFormat emptyFormat = new FastDateFormat(pattern, timeZone, locale);
            format = (FastDateFormat) cInstanceCache.get(emptyFormat);
            if (format == null) {
                format = emptyFormat;
                format.init();
                cInstanceCache.put(format, format);
            }
        }
        return format;
    }

    public static FastDateFormat getDateInstance(int style) {
        return getDateInstance(style, null, null);
    }

    public static FastDateFormat getDateInstance(int style, Locale locale) {
        return getDateInstance(style, null, locale);
    }

    public static FastDateFormat getDateInstance(int style, TimeZone timeZone) {
        return getDateInstance(style, timeZone, null);
    }

    public static synchronized FastDateFormat getDateInstance(int style, TimeZone timeZone, Locale locale) {
        FastDateFormat format;
        synchronized (FastDateFormat.class) {
            Object pair;
            Integer key = new Integer(style);
            if (timeZone != null) {
                pair = new Pair(key, timeZone);
            } else {
                Integer key2 = key;
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
            key = new Pair(pair, locale);
            format = (FastDateFormat) cDateInstanceCache.get(key);
            if (format == null) {
                try {
                    format = getInstance(((SimpleDateFormat) DateFormat.getDateInstance(style, locale)).toPattern(), timeZone, locale);
                    cDateInstanceCache.put(key, format);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException(new StringBuffer().append("No date pattern for locale: ").append(locale).toString());
                }
            }
        }
        return format;
    }

    public static FastDateFormat getTimeInstance(int style) {
        return getTimeInstance(style, null, null);
    }

    public static FastDateFormat getTimeInstance(int style, Locale locale) {
        return getTimeInstance(style, null, locale);
    }

    public static FastDateFormat getTimeInstance(int style, TimeZone timeZone) {
        return getTimeInstance(style, timeZone, null);
    }

    public static synchronized FastDateFormat getTimeInstance(int style, TimeZone timeZone, Locale locale) {
        FastDateFormat format;
        synchronized (FastDateFormat.class) {
            Object pair;
            Integer key = new Integer(style);
            if (timeZone != null) {
                Object pair2 = new Pair(key, timeZone);
            } else {
                Integer key2 = key;
            }
            if (locale != null) {
                pair = new Pair(pair2, locale);
            } else {
                pair = pair2;
            }
            format = (FastDateFormat) cTimeInstanceCache.get(pair);
            if (format == null) {
                if (locale == null) {
                    locale = Locale.getDefault();
                }
                try {
                    format = getInstance(((SimpleDateFormat) DateFormat.getTimeInstance(style, locale)).toPattern(), timeZone, locale);
                    cTimeInstanceCache.put(pair, format);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException(new StringBuffer().append("No date pattern for locale: ").append(locale).toString());
                }
            }
        }
        return format;
    }

    public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle) {
        return getDateTimeInstance(dateStyle, timeStyle, null, null);
    }

    public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, Locale locale) {
        return getDateTimeInstance(dateStyle, timeStyle, null, locale);
    }

    public static FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone) {
        return getDateTimeInstance(dateStyle, timeStyle, timeZone, null);
    }

    public static synchronized FastDateFormat getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone, Locale locale) {
        FastDateFormat format;
        synchronized (FastDateFormat.class) {
            Pair key;
            Pair key2 = new Pair(new Integer(dateStyle), new Integer(timeStyle));
            if (timeZone != null) {
                key = new Pair(key2, timeZone);
            } else {
                key = key2;
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
            key2 = new Pair(key, locale);
            format = (FastDateFormat) cDateTimeInstanceCache.get(key2);
            if (format == null) {
                try {
                    format = getInstance(((SimpleDateFormat) DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale)).toPattern(), timeZone, locale);
                    cDateTimeInstanceCache.put(key2, format);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException(new StringBuffer().append("No date time pattern for locale: ").append(locale).toString());
                }
            }
        }
        return format;
    }

    static synchronized String getTimeZoneDisplay(TimeZone tz, boolean daylight, int style, Locale locale) {
        String value;
        synchronized (FastDateFormat.class) {
            TimeZoneDisplayKey key = new TimeZoneDisplayKey(tz, daylight, style, locale);
            value = (String) cTimeZoneDisplayCache.get(key);
            if (value == null) {
                value = tz.getDisplayName(daylight, style, locale);
                cTimeZoneDisplayCache.put(key, value);
            }
        }
        return value;
    }

    private static synchronized String getDefaultPattern() {
        String str;
        synchronized (FastDateFormat.class) {
            if (cDefaultPattern == null) {
                cDefaultPattern = new SimpleDateFormat().toPattern();
            }
            str = cDefaultPattern;
        }
        return str;
    }

    protected FastDateFormat(String pattern, TimeZone timeZone, Locale locale) {
        boolean z = true;
        if (pattern == null) {
            throw new IllegalArgumentException("The pattern must not be null");
        }
        this.mPattern = pattern;
        this.mTimeZoneForced = timeZone != null;
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        this.mTimeZone = timeZone;
        if (locale == null) {
            z = false;
        }
        this.mLocaleForced = z;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        this.mLocale = locale;
    }

    protected void init() {
        List rulesList = parsePattern();
        this.mRules = (Rule[]) rulesList.toArray(new Rule[rulesList.size()]);
        int len = FULL;
        int i = this.mRules.length;
        while (true) {
            i--;
            if (i >= 0) {
                len += this.mRules[i].estimateLength();
            } else {
                this.mMaxLengthEstimate = len;
                return;
            }
        }
    }

    protected List parsePattern() {
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(this.mLocale);
        List rules = new ArrayList();
        String[] ERAs = dateFormatSymbols.getEras();
        String[] months = dateFormatSymbols.getMonths();
        String[] shortMonths = dateFormatSymbols.getShortMonths();
        String[] weekdays = dateFormatSymbols.getWeekdays();
        String[] shortWeekdays = dateFormatSymbols.getShortWeekdays();
        String[] AmPmStrings = dateFormatSymbols.getAmPmStrings();
        int length = this.mPattern.length();
        int[] indexRef = new int[LONG];
        int i = FULL;
        while (i < length) {
            indexRef[FULL] = i;
            String token = parseToken(this.mPattern, indexRef);
            i = indexRef[FULL];
            int tokenLen = token.length();
            if (tokenLen == 0) {
                return rules;
            }
            Rule rule;
            switch (token.charAt(FULL)) {
                case Tokens.EXPRTOKEN_AXISNAME_FOLLOWING /*39*/:
                    String sub = token.substring(LONG);
                    if (sub.length() != LONG) {
                        rule = new StringLiteral(sub);
                        break;
                    }
                    rule = new CharacterLiteral(sub.charAt(FULL));
                    break;
                case C0302R.styleable.Theme_searchViewStyle /*68*/:
                    rule = selectNumberRule(6, tokenLen);
                    break;
                case C0302R.styleable.Theme_listPreferredItemHeight /*69*/:
                    String[] strArr;
                    if (tokenLen < 4) {
                        strArr = shortWeekdays;
                    } else {
                        strArr = weekdays;
                    }
                    rule = new TextField(7, strArr);
                    break;
                case C0302R.styleable.Theme_listPreferredItemHeightSmall /*70*/:
                    rule = selectNumberRule(8, tokenLen);
                    break;
                case C0302R.styleable.Theme_listPreferredItemHeightLarge /*71*/:
                    rule = new TextField(FULL, ERAs);
                    break;
                case Defaults.LineWidth /*72*/:
                    rule = selectNumberRule(11, tokenLen);
                    break;
                case C0302R.styleable.Theme_listPopupWindowStyle /*75*/:
                    rule = selectNumberRule(10, tokenLen);
                    break;
                case C0302R.styleable.Theme_textAppearanceListItemSmall /*77*/:
                    if (tokenLen < 4) {
                        if (tokenLen != SHORT) {
                            if (tokenLen != MEDIUM) {
                                rule = UnpaddedMonthField.INSTANCE;
                                break;
                            }
                            rule = TwoDigitMonthField.INSTANCE;
                            break;
                        }
                        rule = new TextField(MEDIUM, shortMonths);
                        break;
                    }
                    rule = new TextField(MEDIUM, months);
                    break;
                case C0302R.styleable.Theme_colorPrimaryDark /*83*/:
                    rule = selectNumberRule(14, tokenLen);
                    break;
                case C0302R.styleable.Theme_colorControlHighlight /*87*/:
                    rule = selectNumberRule(4, tokenLen);
                    break;
                case AdSize.LARGE_AD_HEIGHT /*90*/:
                    if (tokenLen != LONG) {
                        rule = TimeZoneNumberRule.INSTANCE_COLON;
                        break;
                    }
                    rule = TimeZoneNumberRule.INSTANCE_NO_COLON;
                    break;
                case C0302R.styleable.Theme_buttonBarNegativeButtonStyle /*97*/:
                    rule = new TextField(9, AmPmStrings);
                    break;
                case HttpStatus.SC_CONTINUE /*100*/:
                    rule = selectNumberRule(5, tokenLen);
                    break;
                case C0302R.styleable.Theme_editTextStyle /*104*/:
                    rule = new TwelveHourField(selectNumberRule(10, tokenLen));
                    break;
                case C0302R.styleable.Theme_seekBarStyle /*107*/:
                    rule = new TwentyFourHourField(selectNumberRule(11, tokenLen));
                    break;
                case C0302R.styleable.Theme_switchStyle /*109*/:
                    rule = selectNumberRule(12, tokenLen);
                    break;
                case 's':
                    rule = selectNumberRule(13, tokenLen);
                    break;
                case 'w':
                    rule = selectNumberRule(SHORT, tokenLen);
                    break;
                case 'y':
                    if (tokenLen < 4) {
                        rule = TwoDigitYearField.INSTANCE;
                        break;
                    }
                    rule = selectNumberRule(LONG, tokenLen);
                    break;
                case 'z':
                    if (tokenLen < 4) {
                        rule = new TimeZoneNameRule(this.mTimeZone, this.mTimeZoneForced, this.mLocale, FULL);
                        break;
                    }
                    rule = new TimeZoneNameRule(this.mTimeZone, this.mTimeZoneForced, this.mLocale, LONG);
                    break;
                default:
                    throw new IllegalArgumentException(new StringBuffer().append("Illegal pattern component: ").append(token).toString());
            }
            rules.add(rule);
            i += LONG;
        }
        return rules;
    }

    protected String parseToken(String pattern, int[] indexRef) {
        StrBuilder buf = new StrBuilder();
        int i = indexRef[FULL];
        int length = pattern.length();
        char c = pattern.charAt(i);
        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
            buf.append('\'');
            boolean inLiteral = false;
            while (i < length) {
                c = pattern.charAt(i);
                if (c != '\'') {
                    if (!inLiteral && ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                        i--;
                        break;
                    }
                    buf.append(c);
                } else if (i + LONG >= length || pattern.charAt(i + LONG) != '\'') {
                    inLiteral = !inLiteral;
                } else {
                    i += LONG;
                    buf.append(c);
                }
                i += LONG;
            }
        } else {
            buf.append(c);
            while (i + LONG < length && pattern.charAt(i + LONG) == c) {
                buf.append(c);
                i += LONG;
            }
        }
        indexRef[FULL] = i;
        return buf.toString();
    }

    protected NumberRule selectNumberRule(int field, int padding) {
        switch (padding) {
            case LONG /*1*/:
                return new UnpaddedNumberField(field);
            case MEDIUM /*2*/:
                return new TwoDigitNumberField(field);
            default:
                return new PaddedNumberField(field, padding);
        }
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (obj instanceof Date) {
            return format((Date) obj, toAppendTo);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj, toAppendTo);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), toAppendTo);
        }
        throw new IllegalArgumentException(new StringBuffer().append("Unknown class: ").append(obj == null ? "<null>" : obj.getClass().getName()).toString());
    }

    public String format(long millis) {
        return format(new Date(millis));
    }

    public String format(Date date) {
        Calendar c = new GregorianCalendar(this.mTimeZone, this.mLocale);
        c.setTime(date);
        return applyRules(c, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    public String format(Calendar calendar) {
        return format(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    public StringBuffer format(long millis, StringBuffer buf) {
        return format(new Date(millis), buf);
    }

    public StringBuffer format(Date date, StringBuffer buf) {
        Calendar c = new GregorianCalendar(this.mTimeZone);
        c.setTime(date);
        return applyRules(c, buf);
    }

    public StringBuffer format(Calendar calendar, StringBuffer buf) {
        if (this.mTimeZoneForced) {
            calendar.getTime();
            calendar = (Calendar) calendar.clone();
            calendar.setTimeZone(this.mTimeZone);
        }
        return applyRules(calendar, buf);
    }

    protected StringBuffer applyRules(Calendar calendar, StringBuffer buf) {
        Rule[] rules = this.mRules;
        int len = this.mRules.length;
        for (int i = FULL; i < len; i += LONG) {
            rules[i].appendTo(buf, calendar);
        }
        return buf;
    }

    public Object parseObject(String source, ParsePosition pos) {
        pos.setIndex(FULL);
        pos.setErrorIndex(FULL);
        return null;
    }

    public String getPattern() {
        return this.mPattern;
    }

    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    public boolean getTimeZoneOverridesCalendar() {
        return this.mTimeZoneForced;
    }

    public Locale getLocale() {
        return this.mLocale;
    }

    public int getMaxLengthEstimate() {
        return this.mMaxLengthEstimate;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDateFormat)) {
            return false;
        }
        FastDateFormat other = (FastDateFormat) obj;
        if (this.mPattern != other.mPattern && !this.mPattern.equals(other.mPattern)) {
            return false;
        }
        if (this.mTimeZone != other.mTimeZone && !this.mTimeZone.equals(other.mTimeZone)) {
            return false;
        }
        if ((this.mLocale == other.mLocale || this.mLocale.equals(other.mLocale)) && this.mTimeZoneForced == other.mTimeZoneForced && this.mLocaleForced == other.mLocaleForced) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = LONG;
        int total = (FULL + this.mPattern.hashCode()) + this.mTimeZone.hashCode();
        if (this.mTimeZoneForced) {
            i = LONG;
        } else {
            i = FULL;
        }
        total = (total + i) + this.mLocale.hashCode();
        if (!this.mLocaleForced) {
            i2 = FULL;
        }
        return total + i2;
    }

    public String toString() {
        return new StringBuffer().append("FastDateFormat[").append(this.mPattern).append("]").toString();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        init();
    }
}
