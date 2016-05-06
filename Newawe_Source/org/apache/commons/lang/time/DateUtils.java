package org.apache.commons.lang.time;

import com.google.android.gms.common.ConnectionResult;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;

public class DateUtils {
    public static final int MILLIS_IN_DAY = 86400000;
    public static final int MILLIS_IN_HOUR = 3600000;
    public static final int MILLIS_IN_MINUTE = 60000;
    public static final int MILLIS_IN_SECOND = 1000;
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    private static final int MODIFY_CEILING = 2;
    private static final int MODIFY_ROUND = 1;
    private static final int MODIFY_TRUNCATE = 0;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    public static final TimeZone UTC_TIME_ZONE;
    private static final int[][] fields;

    static class DateIterator implements Iterator {
        private final Calendar endFinal;
        private final Calendar spot;

        DateIterator(Calendar startFinal, Calendar endFinal) {
            this.endFinal = endFinal;
            this.spot = startFinal;
            this.spot.add(DateUtils.RANGE_MONTH_SUNDAY, -1);
        }

        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        public Object next() {
            if (this.spot.equals(this.endFinal)) {
                throw new NoSuchElementException();
            }
            this.spot.add(DateUtils.RANGE_MONTH_SUNDAY, DateUtils.RANGE_WEEK_SUNDAY);
            return this.spot.clone();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    static {
        UTC_TIME_ZONE = TimeZone.getTimeZone("GMT");
        r0 = new int[8][];
        int[] iArr = new int[RANGE_WEEK_SUNDAY];
        iArr[MODIFY_TRUNCATE] = 14;
        r0[MODIFY_TRUNCATE] = iArr;
        iArr = new int[RANGE_WEEK_SUNDAY];
        iArr[MODIFY_TRUNCATE] = 13;
        r0[RANGE_WEEK_SUNDAY] = iArr;
        iArr = new int[RANGE_WEEK_SUNDAY];
        iArr[MODIFY_TRUNCATE] = 12;
        r0[RANGE_WEEK_MONDAY] = iArr;
        r0[RANGE_WEEK_RELATIVE] = new int[]{11, 10};
        r0[RANGE_WEEK_CENTER] = new int[]{RANGE_MONTH_SUNDAY, RANGE_MONTH_SUNDAY, 9};
        r0[RANGE_MONTH_SUNDAY] = new int[]{RANGE_WEEK_MONDAY, SEMI_MONTH};
        int[] iArr2 = new int[RANGE_WEEK_SUNDAY];
        iArr2[MODIFY_TRUNCATE] = RANGE_WEEK_SUNDAY;
        r0[RANGE_MONTH_MONDAY] = iArr2;
        iArr2 = new int[RANGE_WEEK_SUNDAY];
        iArr2[MODIFY_TRUNCATE] = MODIFY_TRUNCATE;
        r0[7] = iArr2;
        fields = r0;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (cal1.get(MODIFY_TRUNCATE) == cal2.get(MODIFY_TRUNCATE) && cal1.get(RANGE_WEEK_SUNDAY) == cal2.get(RANGE_WEEK_SUNDAY) && cal1.get(RANGE_MONTH_MONDAY) == cal2.get(RANGE_MONTH_MONDAY)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSameInstant(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            return date1.getTime() == date2.getTime();
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.getTime().getTime() == cal2.getTime().getTime();
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameLocalTime(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (cal1.get(14) == cal2.get(14) && cal1.get(13) == cal2.get(13) && cal1.get(12) == cal2.get(12) && cal1.get(10) == cal2.get(10) && cal1.get(RANGE_MONTH_MONDAY) == cal2.get(RANGE_MONTH_MONDAY) && cal1.get(RANGE_WEEK_SUNDAY) == cal2.get(RANGE_WEEK_SUNDAY) && cal1.get(MODIFY_TRUNCATE) == cal2.get(MODIFY_TRUNCATE) && cal1.getClass() == cal2.getClass()) {
            return true;
        } else {
            return false;
        }
    }

    public static Date parseDate(String str, String[] parsePatterns) throws ParseException {
        return parseDateWithLeniency(str, parsePatterns, true);
    }

    public static Date parseDateStrictly(String str, String[] parsePatterns) throws ParseException {
        return parseDateWithLeniency(str, parsePatterns, false);
    }

    private static Date parseDateWithLeniency(String str, String[] parsePatterns, boolean lenient) throws ParseException {
        if (str == null || parsePatterns == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        SimpleDateFormat parser = new SimpleDateFormat();
        parser.setLenient(lenient);
        ParsePosition pos = new ParsePosition(MODIFY_TRUNCATE);
        for (int i = MODIFY_TRUNCATE; i < parsePatterns.length; i += RANGE_WEEK_SUNDAY) {
            String pattern = parsePatterns[i];
            if (parsePatterns[i].endsWith("ZZ")) {
                pattern = pattern.substring(MODIFY_TRUNCATE, pattern.length() - 1);
            }
            parser.applyPattern(pattern);
            pos.setIndex(MODIFY_TRUNCATE);
            String str2 = str;
            if (parsePatterns[i].endsWith("ZZ")) {
                int signIdx = indexOfSignChars(str2, MODIFY_TRUNCATE);
                while (signIdx >= 0) {
                    str2 = reformatTimezone(str2, signIdx);
                    signIdx = indexOfSignChars(str2, signIdx + RANGE_WEEK_SUNDAY);
                }
            }
            Date date = parser.parse(str2, pos);
            if (date != null && pos.getIndex() == str2.length()) {
                return date;
            }
        }
        throw new ParseException(new StringBuffer().append("Unable to parse the date: ").append(str).toString(), -1);
    }

    private static int indexOfSignChars(String str, int startPos) {
        int idx = StringUtils.indexOf(str, '+', startPos);
        if (idx < 0) {
            return StringUtils.indexOf(str, '-', startPos);
        }
        return idx;
    }

    private static String reformatTimezone(String str, int signIdx) {
        String str2 = str;
        if (signIdx >= 0 && signIdx + RANGE_MONTH_SUNDAY < str.length() && Character.isDigit(str.charAt(signIdx + RANGE_WEEK_SUNDAY)) && Character.isDigit(str.charAt(signIdx + RANGE_WEEK_MONDAY)) && str.charAt(signIdx + RANGE_WEEK_RELATIVE) == ':' && Character.isDigit(str.charAt(signIdx + RANGE_WEEK_CENTER)) && Character.isDigit(str.charAt(signIdx + RANGE_MONTH_SUNDAY))) {
            return new StringBuffer().append(str.substring(MODIFY_TRUNCATE, signIdx + RANGE_WEEK_RELATIVE)).append(str.substring(signIdx + RANGE_WEEK_CENTER)).toString();
        }
        return str2;
    }

    public static Date addYears(Date date, int amount) {
        return add(date, RANGE_WEEK_SUNDAY, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, RANGE_WEEK_MONDAY, amount);
    }

    public static Date addWeeks(Date date, int amount) {
        return add(date, RANGE_WEEK_RELATIVE, amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, RANGE_MONTH_SUNDAY, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static Date setYears(Date date, int amount) {
        return set(date, RANGE_WEEK_SUNDAY, amount);
    }

    public static Date setMonths(Date date, int amount) {
        return set(date, RANGE_WEEK_MONDAY, amount);
    }

    public static Date setDays(Date date, int amount) {
        return set(date, RANGE_MONTH_SUNDAY, amount);
    }

    public static Date setHours(Date date, int amount) {
        return set(date, 11, amount);
    }

    public static Date setMinutes(Date date, int amount) {
        return set(date, 12, amount);
    }

    public static Date setSeconds(Date date, int amount) {
        return set(date, 13, amount);
    }

    public static Date setMilliseconds(Date date, int amount) {
        return set(date, 14, amount);
    }

    private static Date set(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setLenient(false);
        c.setTime(date);
        c.set(calendarField, amount);
        return c.getTime();
    }

    public static Calendar toCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    public static Date round(Date date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar gval = Calendar.getInstance();
        gval.setTime(date);
        modify(gval, field, RANGE_WEEK_SUNDAY);
        return gval.getTime();
    }

    public static Calendar round(Calendar date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar rounded = (Calendar) date.clone();
        modify(rounded, field, RANGE_WEEK_SUNDAY);
        return rounded;
    }

    public static Date round(Object date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (date instanceof Date) {
            return round((Date) date, field);
        } else {
            if (date instanceof Calendar) {
                return round((Calendar) date, field).getTime();
            }
            throw new ClassCastException(new StringBuffer().append("Could not round ").append(date).toString());
        }
    }

    public static Date truncate(Date date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar gval = Calendar.getInstance();
        gval.setTime(date);
        modify(gval, field, MODIFY_TRUNCATE);
        return gval.getTime();
    }

    public static Calendar truncate(Calendar date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar truncated = (Calendar) date.clone();
        modify(truncated, field, MODIFY_TRUNCATE);
        return truncated;
    }

    public static Date truncate(Object date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (date instanceof Date) {
            return truncate((Date) date, field);
        } else {
            if (date instanceof Calendar) {
                return truncate((Calendar) date, field).getTime();
            }
            throw new ClassCastException(new StringBuffer().append("Could not truncate ").append(date).toString());
        }
    }

    public static Date ceiling(Date date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar gval = Calendar.getInstance();
        gval.setTime(date);
        modify(gval, field, RANGE_WEEK_MONDAY);
        return gval.getTime();
    }

    public static Calendar ceiling(Calendar date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar ceiled = (Calendar) date.clone();
        modify(ceiled, field, RANGE_WEEK_MONDAY);
        return ceiled;
    }

    public static Date ceiling(Object date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (date instanceof Date) {
            return ceiling((Date) date, field);
        } else {
            if (date instanceof Calendar) {
                return ceiling((Calendar) date, field).getTime();
            }
            throw new ClassCastException(new StringBuffer().append("Could not find ceiling of for type: ").append(date.getClass()).toString());
        }
    }

    private static void modify(Calendar val, int field, int modType) {
        if (val.get(RANGE_WEEK_SUNDAY) > 280000000) {
            throw new ArithmeticException("Calendar value too large for accurate calculations");
        } else if (field != 14) {
            Date date = val.getTime();
            long time = date.getTime();
            boolean done = false;
            int millisecs = val.get(14);
            if (modType == 0 || millisecs < 500) {
                time -= (long) millisecs;
            }
            if (field == 13) {
                done = true;
            }
            int seconds = val.get(13);
            if (!done && (modType == 0 || seconds < 30)) {
                time -= ((long) seconds) * MILLIS_PER_SECOND;
            }
            if (field == 12) {
                done = true;
            }
            int minutes = val.get(12);
            if (!done && (modType == 0 || minutes < 30)) {
                time -= ((long) minutes) * MILLIS_PER_MINUTE;
            }
            if (date.getTime() != time) {
                date.setTime(time);
                val.setTime(date);
            }
            boolean roundUp = false;
            int i = MODIFY_TRUNCATE;
            while (true) {
                int length = fields.length;
                if (i < r0) {
                    int j = MODIFY_TRUNCATE;
                    while (true) {
                        length = fields[i].length;
                        if (j >= r0) {
                            int offset = MODIFY_TRUNCATE;
                            boolean offsetSet = false;
                            switch (field) {
                                case ConnectionResult.SERVICE_INVALID /*9*/:
                                    if (fields[i][MODIFY_TRUNCATE] == 11) {
                                        offset = val.get(11);
                                        if (offset >= 12) {
                                            offset -= 12;
                                        }
                                        roundUp = offset >= RANGE_MONTH_MONDAY;
                                        offsetSet = true;
                                        break;
                                    }
                                    break;
                                case SEMI_MONTH /*1001*/:
                                    if (fields[i][MODIFY_TRUNCATE] == RANGE_MONTH_SUNDAY) {
                                        offset = val.get(RANGE_MONTH_SUNDAY) - 1;
                                        if (offset >= 15) {
                                            offset -= 15;
                                        }
                                        roundUp = offset > 7;
                                        offsetSet = true;
                                        break;
                                    }
                                    break;
                            }
                            if (!offsetSet) {
                                int min = val.getActualMinimum(fields[i][MODIFY_TRUNCATE]);
                                offset = val.get(fields[i][MODIFY_TRUNCATE]) - min;
                                roundUp = offset > (val.getActualMaximum(fields[i][MODIFY_TRUNCATE]) - min) / RANGE_WEEK_MONDAY;
                            }
                            if (offset != 0) {
                                val.set(fields[i][MODIFY_TRUNCATE], val.get(fields[i][MODIFY_TRUNCATE]) - offset);
                            }
                            i += RANGE_WEEK_SUNDAY;
                        } else if (fields[i][j] != field) {
                            j += RANGE_WEEK_SUNDAY;
                        } else if (modType != RANGE_WEEK_MONDAY && (modType != RANGE_WEEK_SUNDAY || !roundUp)) {
                            return;
                        } else {
                            if (field == SEMI_MONTH) {
                                if (val.get(RANGE_MONTH_SUNDAY) == RANGE_WEEK_SUNDAY) {
                                    val.add(RANGE_MONTH_SUNDAY, 15);
                                    return;
                                }
                                val.add(RANGE_MONTH_SUNDAY, -15);
                                val.add(RANGE_WEEK_MONDAY, RANGE_WEEK_SUNDAY);
                                return;
                            } else if (field != 9) {
                                val.add(fields[i][MODIFY_TRUNCATE], RANGE_WEEK_SUNDAY);
                                return;
                            } else if (val.get(11) == 0) {
                                val.add(11, 12);
                                return;
                            } else {
                                val.add(11, -12);
                                val.add(RANGE_MONTH_SUNDAY, RANGE_WEEK_SUNDAY);
                                return;
                            }
                        }
                    }
                }
                throw new IllegalArgumentException(new StringBuffer().append("The field ").append(field).append(" is not supported").toString());
            }
        }
    }

    public static Iterator iterator(Date focus, int rangeStyle) {
        if (focus == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar gval = Calendar.getInstance();
        gval.setTime(focus);
        return iterator(gval, rangeStyle);
    }

    public static Iterator iterator(Calendar focus, int rangeStyle) {
        if (focus == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar start;
        Calendar end;
        int startCutoff = RANGE_WEEK_SUNDAY;
        int endCutoff = 7;
        switch (rangeStyle) {
            case RANGE_WEEK_SUNDAY /*1*/:
            case RANGE_WEEK_MONDAY /*2*/:
            case RANGE_WEEK_RELATIVE /*3*/:
            case RANGE_WEEK_CENTER /*4*/:
                start = truncate(focus, (int) RANGE_MONTH_SUNDAY);
                end = truncate(focus, (int) RANGE_MONTH_SUNDAY);
                switch (rangeStyle) {
                    case RANGE_WEEK_SUNDAY /*1*/:
                        break;
                    case RANGE_WEEK_MONDAY /*2*/:
                        startCutoff = RANGE_WEEK_MONDAY;
                        endCutoff = RANGE_WEEK_SUNDAY;
                        break;
                    case RANGE_WEEK_RELATIVE /*3*/:
                        startCutoff = focus.get(7);
                        endCutoff = startCutoff - 1;
                        break;
                    case RANGE_WEEK_CENTER /*4*/:
                        startCutoff = focus.get(7) - 3;
                        endCutoff = focus.get(7) + RANGE_WEEK_RELATIVE;
                        break;
                    default:
                        break;
                }
            case RANGE_MONTH_SUNDAY /*5*/:
            case RANGE_MONTH_MONDAY /*6*/:
                start = truncate(focus, (int) RANGE_WEEK_MONDAY);
                end = (Calendar) start.clone();
                end.add(RANGE_WEEK_MONDAY, RANGE_WEEK_SUNDAY);
                end.add(RANGE_MONTH_SUNDAY, -1);
                if (rangeStyle == RANGE_MONTH_MONDAY) {
                    startCutoff = RANGE_WEEK_MONDAY;
                    endCutoff = RANGE_WEEK_SUNDAY;
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException(new StringBuffer().append("The range style ").append(rangeStyle).append(" is not valid.").toString());
        }
        if (startCutoff < RANGE_WEEK_SUNDAY) {
            startCutoff += 7;
        }
        if (startCutoff > 7) {
            startCutoff -= 7;
        }
        if (endCutoff < RANGE_WEEK_SUNDAY) {
            endCutoff += 7;
        }
        if (endCutoff > 7) {
            endCutoff -= 7;
        }
        while (start.get(7) != startCutoff) {
            start.add(RANGE_MONTH_SUNDAY, -1);
        }
        while (end.get(7) != endCutoff) {
            end.add(RANGE_MONTH_SUNDAY, RANGE_WEEK_SUNDAY);
        }
        return new DateIterator(start, end);
    }

    public static Iterator iterator(Object focus, int rangeStyle) {
        if (focus == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (focus instanceof Date) {
            return iterator((Date) focus, rangeStyle);
        } else {
            if (focus instanceof Calendar) {
                return iterator((Calendar) focus, rangeStyle);
            }
            throw new ClassCastException(new StringBuffer().append("Could not iterate based on ").append(focus).toString());
        }
    }

    public static long getFragmentInMilliseconds(Date date, int fragment) {
        return getFragment(date, fragment, 14);
    }

    public static long getFragmentInSeconds(Date date, int fragment) {
        return getFragment(date, fragment, 13);
    }

    public static long getFragmentInMinutes(Date date, int fragment) {
        return getFragment(date, fragment, 12);
    }

    public static long getFragmentInHours(Date date, int fragment) {
        return getFragment(date, fragment, 11);
    }

    public static long getFragmentInDays(Date date, int fragment) {
        return getFragment(date, fragment, (int) RANGE_MONTH_MONDAY);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, 14);
    }

    public static long getFragmentInSeconds(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, 13);
    }

    public static long getFragmentInMinutes(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, 12);
    }

    public static long getFragmentInHours(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, 11);
    }

    public static long getFragmentInDays(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, (int) RANGE_MONTH_MONDAY);
    }

    private static long getFragment(Date date, int fragment, int unit) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFragment(calendar, fragment, unit);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long getFragment(java.util.Calendar r8, int r9, int r10) {
        /*
        r6 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        if (r8 != 0) goto L_0x000d;
    L_0x0005:
        r4 = new java.lang.IllegalArgumentException;
        r5 = "The date must not be null";
        r4.<init>(r5);
        throw r4;
    L_0x000d:
        r0 = getMillisPerUnit(r10);
        r2 = 0;
        switch(r9) {
            case 1: goto L_0x0038;
            case 2: goto L_0x0042;
            default: goto L_0x0016;
        };
    L_0x0016:
        switch(r9) {
            case 1: goto L_0x004c;
            case 2: goto L_0x004c;
            case 3: goto L_0x0019;
            case 4: goto L_0x0019;
            case 5: goto L_0x004c;
            case 6: goto L_0x004c;
            case 7: goto L_0x0019;
            case 8: goto L_0x0019;
            case 9: goto L_0x0019;
            case 10: goto L_0x0019;
            case 11: goto L_0x0059;
            case 12: goto L_0x0066;
            case 13: goto L_0x0072;
            case 14: goto L_0x007d;
            default: goto L_0x0019;
        };
    L_0x0019:
        r4 = new java.lang.IllegalArgumentException;
        r5 = new java.lang.StringBuffer;
        r5.<init>();
        r6 = "The fragment ";
        r5 = r5.append(r6);
        r5 = r5.append(r9);
        r6 = " is not supported";
        r5 = r5.append(r6);
        r5 = r5.toString();
        r4.<init>(r5);
        throw r4;
    L_0x0038:
        r4 = 6;
        r4 = r8.get(r4);
        r4 = (long) r4;
        r4 = r4 * r6;
        r4 = r4 / r0;
        r2 = r2 + r4;
        goto L_0x0016;
    L_0x0042:
        r4 = 5;
        r4 = r8.get(r4);
        r4 = (long) r4;
        r4 = r4 * r6;
        r4 = r4 / r0;
        r2 = r2 + r4;
        goto L_0x0016;
    L_0x004c:
        r4 = 11;
        r4 = r8.get(r4);
        r4 = (long) r4;
        r6 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r4 = r4 * r6;
        r4 = r4 / r0;
        r2 = r2 + r4;
    L_0x0059:
        r4 = 12;
        r4 = r8.get(r4);
        r4 = (long) r4;
        r6 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r4 = r4 * r6;
        r4 = r4 / r0;
        r2 = r2 + r4;
    L_0x0066:
        r4 = 13;
        r4 = r8.get(r4);
        r4 = (long) r4;
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r4 * r6;
        r4 = r4 / r0;
        r2 = r2 + r4;
    L_0x0072:
        r4 = 14;
        r4 = r8.get(r4);
        r4 = r4 * 1;
        r4 = (long) r4;
        r4 = r4 / r0;
        r2 = r2 + r4;
    L_0x007d:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.time.DateUtils.getFragment(java.util.Calendar, int, int):long");
    }

    public static boolean truncatedEquals(Calendar cal1, Calendar cal2, int field) {
        return truncatedCompareTo(cal1, cal2, field) == 0;
    }

    public static boolean truncatedEquals(Date date1, Date date2, int field) {
        return truncatedCompareTo(date1, date2, field) == 0;
    }

    public static int truncatedCompareTo(Calendar cal1, Calendar cal2, int field) {
        return truncate(cal1, field).getTime().compareTo(truncate(cal2, field).getTime());
    }

    public static int truncatedCompareTo(Date date1, Date date2, int field) {
        return truncate(date1, field).compareTo(truncate(date2, field));
    }

    private static long getMillisPerUnit(int unit) {
        switch (unit) {
            case RANGE_MONTH_SUNDAY /*5*/:
            case RANGE_MONTH_MONDAY /*6*/:
                return MILLIS_PER_DAY;
            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                return MILLIS_PER_HOUR;
            case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                return MILLIS_PER_MINUTE;
            case ConnectionResult.CANCELED /*13*/:
                return MILLIS_PER_SECOND;
            case ConnectionResult.TIMEOUT /*14*/:
                return 1;
            default:
                throw new IllegalArgumentException(new StringBuffer().append("The unit ").append(unit).append(" cannot be represented is milleseconds").toString());
        }
    }
}
