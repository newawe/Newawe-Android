package mf.org.apache.xerces.impl.dv.xs;

import android.support.v4.widget.ExploreByTouchHelper;
import mf.javax.xml.datatype.DatatypeFactory;
import mf.javax.xml.datatype.XMLGregorianCalendar;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class DayDV extends AbstractDateTimeDV {
    private static final int DAY_SIZE = 5;

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return parse(content);
        } catch (Exception e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_DAY});
        }
    }

    protected DateTimeData parse(String str) throws SchemaDateTimeException {
        DateTimeData date = new DateTimeData(str, this);
        int len = str.length();
        if (str.charAt(0) == '-' && str.charAt(1) == '-' && str.charAt(2) == '-') {
            date.year = 2000;
            date.month = 1;
            date.day = parseInt(str, 3, DAY_SIZE);
            if (DAY_SIZE < len) {
                if (isNextCharUTCSign(str, DAY_SIZE, len)) {
                    getTimeZone(str, date, DAY_SIZE, len);
                } else {
                    throw new SchemaDateTimeException("Error in day parsing");
                }
            }
            validateDateTime(date);
            saveUnnormalized(date);
            if (!(date.utc == 0 || date.utc == 90)) {
                normalize(date);
            }
            date.position = 2;
            return date;
        }
        throw new SchemaDateTimeException("Error in day parsing");
    }

    protected String dateToString(DateTimeData date) {
        StringBuffer message = new StringBuffer(6);
        message.append('-');
        message.append('-');
        message.append('-');
        append(message, date.day, 2);
        append(message, (char) date.utc, 0);
        return message.toString();
    }

    protected XMLGregorianCalendar getXMLGregorianCalendar(DateTimeData date) {
        int i;
        DatatypeFactory datatypeFactory = datatypeFactory;
        int i2 = date.unNormDay;
        if (date.hasTimeZone()) {
            i = (date.timezoneHr * 60) + date.timezoneMin;
        } else {
            i = ExploreByTouchHelper.INVALID_ID;
        }
        return datatypeFactory.newXMLGregorianCalendar((int) ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, i2, ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, i);
    }
}
