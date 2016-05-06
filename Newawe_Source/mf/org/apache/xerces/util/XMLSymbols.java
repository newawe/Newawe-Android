package mf.org.apache.xerces.util;

import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.serialize.Method;
import org.apache.commons.lang.StringUtils;

public class XMLSymbols {
    public static final String EMPTY_STRING;
    public static final String PREFIX_XML;
    public static final String PREFIX_XMLNS;
    public static final String fANYSymbol;
    public static final String fCDATASymbol;
    public static final String fENTITIESSymbol;
    public static final String fENTITYSymbol;
    public static final String fENUMERATIONSymbol;
    public static final String fFIXEDSymbol;
    public static final String fIDREFSSymbol;
    public static final String fIDREFSymbol;
    public static final String fIDSymbol;
    public static final String fIMPLIEDSymbol;
    public static final String fNMTOKENSSymbol;
    public static final String fNMTOKENSymbol;
    public static final String fNOTATIONSymbol;
    public static final String fREQUIREDSymbol;

    static {
        EMPTY_STRING = StringUtils.EMPTY.intern();
        PREFIX_XML = Method.XML.intern();
        PREFIX_XMLNS = XMLConstants.XMLNS_ATTRIBUTE.intern();
        fANYSymbol = "ANY".intern();
        fCDATASymbol = "CDATA".intern();
        fIDSymbol = SchemaSymbols.ATTVAL_ID.intern();
        fIDREFSymbol = SchemaSymbols.ATTVAL_IDREF.intern();
        fIDREFSSymbol = SchemaSymbols.ATTVAL_IDREFS.intern();
        fENTITYSymbol = SchemaSymbols.ATTVAL_ENTITY.intern();
        fENTITIESSymbol = SchemaSymbols.ATTVAL_ENTITIES.intern();
        fNMTOKENSymbol = SchemaSymbols.ATTVAL_NMTOKEN.intern();
        fNMTOKENSSymbol = SchemaSymbols.ATTVAL_NMTOKENS.intern();
        fNOTATIONSymbol = SchemaSymbols.ATTVAL_NOTATION.intern();
        fENUMERATIONSymbol = "ENUMERATION".intern();
        fIMPLIEDSymbol = "#IMPLIED".intern();
        fREQUIREDSymbol = "#REQUIRED".intern();
        fFIXEDSymbol = "#FIXED".intern();
    }
}
