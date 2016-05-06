package mf.javax.xml.datatype;

import mf.javax.xml.XMLConstants;
import mf.javax.xml.namespace.QName;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;

public final class DatatypeConstants {
    public static final int APRIL = 4;
    public static final int AUGUST = 8;
    public static final QName DATE;
    public static final QName DATETIME;
    public static final Field DAYS;
    public static final int DECEMBER = 12;
    public static final QName DURATION;
    public static final QName DURATION_DAYTIME;
    public static final QName DURATION_YEARMONTH;
    public static final int EQUAL = 0;
    public static final int FEBRUARY = 2;
    public static final int FIELD_UNDEFINED = Integer.MIN_VALUE;
    public static final QName GDAY;
    public static final QName GMONTH;
    public static final QName GMONTHDAY;
    public static final int GREATER = 1;
    public static final QName GYEAR;
    public static final QName GYEARMONTH;
    public static final Field HOURS;
    public static final int INDETERMINATE = 2;
    public static final int JANUARY = 1;
    public static final int JULY = 7;
    public static final int JUNE = 6;
    public static final int LESSER = -1;
    public static final int MARCH = 3;
    public static final int MAX_TIMEZONE_OFFSET = -840;
    public static final int MAY = 5;
    public static final Field MINUTES;
    public static final int MIN_TIMEZONE_OFFSET = 840;
    public static final Field MONTHS;
    public static final int NOVEMBER = 11;
    public static final int OCTOBER = 10;
    public static final Field SECONDS;
    public static final int SEPTEMBER = 9;
    public static final QName TIME;
    public static final Field YEARS;

    public static final class Field {
        private final int id;
        private final String str;

        private Field(String str, int id) {
            this.str = str;
            this.id = id;
        }

        public String toString() {
            return this.str;
        }

        public int getId() {
            return this.id;
        }
    }

    private DatatypeConstants() {
    }

    static {
        YEARS = new Field(EQUAL, null);
        MONTHS = new Field(JANUARY, null);
        DAYS = new Field(INDETERMINATE, null);
        HOURS = new Field(MARCH, null);
        MINUTES = new Field(APRIL, null);
        SECONDS = new Field(MAY, null);
        DATETIME = new QName(XMLGrammarDescription.XML_SCHEMA, SchemaSymbols.ATTVAL_DATETIME);
        TIME = new QName(XMLGrammarDescription.XML_SCHEMA, SchemaSymbols.ATTVAL_TIME);
        DATE = new QName(XMLGrammarDescription.XML_SCHEMA, SchemaSymbols.ATTVAL_DATE);
        GYEARMONTH = new QName(XMLGrammarDescription.XML_SCHEMA, SchemaSymbols.ATTVAL_YEARMONTH);
        GMONTHDAY = new QName(XMLGrammarDescription.XML_SCHEMA, SchemaSymbols.ATTVAL_MONTHDAY);
        GYEAR = new QName(XMLGrammarDescription.XML_SCHEMA, SchemaSymbols.ATTVAL_YEAR);
        GMONTH = new QName(XMLGrammarDescription.XML_SCHEMA, SchemaSymbols.ATTVAL_MONTH);
        GDAY = new QName(XMLGrammarDescription.XML_SCHEMA, SchemaSymbols.ATTVAL_DAY);
        DURATION = new QName(XMLGrammarDescription.XML_SCHEMA, SchemaSymbols.ATTVAL_DURATION);
        DURATION_DAYTIME = new QName(XMLConstants.W3C_XPATH_DATATYPE_NS_URI, "dayTimeDuration");
        DURATION_YEARMONTH = new QName(XMLConstants.W3C_XPATH_DATATYPE_NS_URI, "yearMonthDuration");
    }
}
