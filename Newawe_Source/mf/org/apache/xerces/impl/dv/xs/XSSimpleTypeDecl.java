package mf.org.apache.xerces.impl.dv.xs;

import android.support.v4.media.TransportMediator;
import com.google.android.gms.common.ConnectionResult;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.Locale;
import java.util.Vector;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.dv.DatatypeException;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.dv.XSFacets;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.util.ObjectListImpl;
import mf.org.apache.xerces.impl.xs.util.ShortListImpl;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSFacet;
import mf.org.apache.xerces.xs.XSMultiValueFacet;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObject;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.datatypes.ObjectList;
import mf.org.w3c.dom.TypeInfo;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

public class XSSimpleTypeDecl implements XSSimpleType, TypeInfo {
    public static final short ANYATOMICTYPE_DT = (short) 49;
    static final String ANY_TYPE = "anyType";
    public static final short DAYTIMEDURATION_DT = (short) 47;
    static final int DERIVATION_ANY = 0;
    static final int DERIVATION_EXTENSION = 2;
    static final int DERIVATION_LIST = 8;
    static final int DERIVATION_RESTRICTION = 1;
    static final int DERIVATION_UNION = 4;
    protected static final short DV_ANYATOMICTYPE = (short) 29;
    protected static final short DV_ANYSIMPLETYPE = (short) 0;
    protected static final short DV_ANYURI = (short) 17;
    protected static final short DV_BASE64BINARY = (short) 16;
    protected static final short DV_BOOLEAN = (short) 2;
    protected static final short DV_DATE = (short) 9;
    protected static final short DV_DATETIME = (short) 7;
    protected static final short DV_DAYTIMEDURATION = (short) 28;
    protected static final short DV_DECIMAL = (short) 3;
    protected static final short DV_DOUBLE = (short) 5;
    protected static final short DV_DURATION = (short) 6;
    protected static final short DV_ENTITY = (short) 23;
    protected static final short DV_FLOAT = (short) 4;
    protected static final short DV_GDAY = (short) 13;
    protected static final short DV_GMONTH = (short) 14;
    protected static final short DV_GMONTHDAY = (short) 12;
    protected static final short DV_GYEAR = (short) 11;
    protected static final short DV_GYEARMONTH = (short) 10;
    protected static final short DV_HEXBINARY = (short) 15;
    protected static final short DV_ID = (short) 21;
    protected static final short DV_IDREF = (short) 22;
    protected static final short DV_INTEGER = (short) 24;
    protected static final short DV_LIST = (short) 25;
    protected static final short DV_NOTATION = (short) 20;
    protected static final short DV_PRECISIONDECIMAL = (short) 19;
    protected static final short DV_QNAME = (short) 18;
    protected static final short DV_STRING = (short) 1;
    protected static final short DV_TIME = (short) 8;
    protected static final short DV_UNION = (short) 26;
    protected static final short DV_YEARMONTHDURATION = (short) 27;
    static final short NORMALIZE_FULL = (short) 2;
    static final short NORMALIZE_NONE = (short) 0;
    static final short NORMALIZE_TRIM = (short) 1;
    public static final short PRECISIONDECIMAL_DT = (short) 48;
    static final short SPECIAL_PATTERN_NAME = (short) 2;
    static final short SPECIAL_PATTERN_NCNAME = (short) 3;
    static final short SPECIAL_PATTERN_NMTOKEN = (short) 1;
    static final short SPECIAL_PATTERN_NONE = (short) 0;
    static final String[] SPECIAL_PATTERN_STRING;
    static final String URI_SCHEMAFORSCHEMA = "http://www.w3.org/2001/XMLSchema";
    static final String[] WS_FACET_STRING;
    public static final short YEARMONTHDURATION_DT = (short) 46;
    static final XSSimpleTypeDecl fAnyAtomicType;
    static final XSSimpleTypeDecl fAnySimpleType;
    static final short[] fDVNormalizeType;
    static final ValidationContext fDummyContext;
    static final ValidationContext fEmptyContext;
    private static final TypeValidator[] gDVs;
    public XSObjectList enumerationAnnotations;
    private ObjectList fActualEnumeration;
    private XSObjectList fAnnotations;
    private boolean fAnonymous;
    private XSSimpleTypeDecl fBase;
    private boolean fBounded;
    private short fBuiltInKind;
    private TypeValidator[] fDVs;
    private ValidatedInfo[] fEnumeration;
    private ObjectList fEnumerationItemTypeList;
    private int fEnumerationSize;
    private ShortList fEnumerationTypeList;
    private XSObjectListImpl fFacets;
    private short fFacetsDefined;
    private short fFinalSet;
    private boolean fFinite;
    private short fFixedFacet;
    private int fFractionDigits;
    private boolean fIsImmutable;
    private XSSimpleTypeDecl fItemType;
    private int fLength;
    private StringList fLexicalEnumeration;
    private StringList fLexicalPattern;
    private Object fMaxExclusive;
    private Object fMaxInclusive;
    private int fMaxLength;
    private XSSimpleTypeDecl[] fMemberTypes;
    private Object fMinExclusive;
    private Object fMinInclusive;
    private int fMinLength;
    private XSObjectListImpl fMultiValueFacets;
    private XSNamespaceItem fNamespaceItem;
    private boolean fNumeric;
    private short fOrdered;
    private Vector fPattern;
    private Vector fPatternStr;
    private short fPatternType;
    private String fTargetNamespace;
    private int fTotalDigits;
    private String fTypeName;
    private short fValidationDV;
    private short fVariety;
    private short fWhiteSpace;
    public XSAnnotation fractionDigitsAnnotation;
    public XSAnnotation lengthAnnotation;
    public XSAnnotation maxExclusiveAnnotation;
    public XSAnnotation maxInclusiveAnnotation;
    public XSAnnotation maxLengthAnnotation;
    public XSAnnotation minExclusiveAnnotation;
    public XSAnnotation minInclusiveAnnotation;
    public XSAnnotation minLengthAnnotation;
    public XSObjectListImpl patternAnnotations;
    public XSAnnotation totalDigitsAnnotation;
    public XSAnnotation whiteSpaceAnnotation;

    /* renamed from: mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl.1 */
    class C12651 implements ValidationContext {
        C12651() {
        }

        public boolean needFacetChecking() {
            return true;
        }

        public boolean needExtraChecking() {
            return false;
        }

        public boolean needToNormalize() {
            return true;
        }

        public boolean useNamespaces() {
            return true;
        }

        public boolean isEntityDeclared(String name) {
            return false;
        }

        public boolean isEntityUnparsed(String name) {
            return false;
        }

        public boolean isIdDeclared(String name) {
            return false;
        }

        public void addId(String name) {
        }

        public void addIdRef(String name) {
        }

        public String getSymbol(String symbol) {
            return symbol.intern();
        }

        public String getURI(String prefix) {
            return null;
        }

        public Locale getLocale() {
            return Locale.getDefault();
        }
    }

    /* renamed from: mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl.2 */
    class C12662 implements ValidationContext {
        C12662() {
        }

        public boolean needFacetChecking() {
            return true;
        }

        public boolean needExtraChecking() {
            return false;
        }

        public boolean needToNormalize() {
            return false;
        }

        public boolean useNamespaces() {
            return true;
        }

        public boolean isEntityDeclared(String name) {
            return false;
        }

        public boolean isEntityUnparsed(String name) {
            return false;
        }

        public boolean isIdDeclared(String name) {
            return false;
        }

        public void addId(String name) {
        }

        public void addIdRef(String name) {
        }

        public String getSymbol(String symbol) {
            return symbol.intern();
        }

        public String getURI(String prefix) {
            return null;
        }

        public Locale getLocale() {
            return Locale.getDefault();
        }
    }

    private static abstract class AbstractObjectList extends AbstractList implements ObjectList {
        private AbstractObjectList() {
        }

        public Object get(int index) {
            if (index >= 0 && index < getLength()) {
                return item(index);
            }
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        public int size() {
            return getLength();
        }
    }

    static final class ValidationContextImpl implements ValidationContext {
        final ValidationContext fExternal;
        NamespaceContext fNSContext;

        ValidationContextImpl(ValidationContext external) {
            this.fExternal = external;
        }

        void setNSContext(NamespaceContext nsContext) {
            this.fNSContext = nsContext;
        }

        public boolean needFacetChecking() {
            return this.fExternal.needFacetChecking();
        }

        public boolean needExtraChecking() {
            return this.fExternal.needExtraChecking();
        }

        public boolean needToNormalize() {
            return this.fExternal.needToNormalize();
        }

        public boolean useNamespaces() {
            return true;
        }

        public boolean isEntityDeclared(String name) {
            return this.fExternal.isEntityDeclared(name);
        }

        public boolean isEntityUnparsed(String name) {
            return this.fExternal.isEntityUnparsed(name);
        }

        public boolean isIdDeclared(String name) {
            return this.fExternal.isIdDeclared(name);
        }

        public void addId(String name) {
            this.fExternal.addId(name);
        }

        public void addIdRef(String name) {
            this.fExternal.addIdRef(name);
        }

        public String getSymbol(String symbol) {
            return this.fExternal.getSymbol(symbol);
        }

        public String getURI(String prefix) {
            if (this.fNSContext == null) {
                return this.fExternal.getURI(prefix);
            }
            return this.fNSContext.getURI(prefix);
        }

        public Locale getLocale() {
            return this.fExternal.getLocale();
        }
    }

    /* renamed from: mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl.3 */
    class C13323 extends AbstractObjectList {
        C13323() {
            super();
        }

        public int getLength() {
            return XSSimpleTypeDecl.this.fEnumeration != null ? XSSimpleTypeDecl.this.fEnumerationSize : XSSimpleTypeDecl.DERIVATION_ANY;
        }

        public boolean contains(Object item) {
            if (XSSimpleTypeDecl.this.fEnumeration == null) {
                return false;
            }
            for (int i = XSSimpleTypeDecl.DERIVATION_ANY; i < XSSimpleTypeDecl.this.fEnumerationSize; i += XSSimpleTypeDecl.DERIVATION_RESTRICTION) {
                if (XSSimpleTypeDecl.this.fEnumeration[i].getActualValue().equals(item)) {
                    return true;
                }
            }
            return false;
        }

        public Object item(int index) {
            if (index < 0 || index >= getLength()) {
                return null;
            }
            return XSSimpleTypeDecl.this.fEnumeration[index].getActualValue();
        }
    }

    /* renamed from: mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl.4 */
    class C13334 extends AbstractObjectList {
        C13334() {
            super();
        }

        public int getLength() {
            return XSSimpleTypeDecl.this.fEnumeration != null ? XSSimpleTypeDecl.this.fEnumerationSize : XSSimpleTypeDecl.DERIVATION_ANY;
        }

        public boolean contains(Object item) {
            if (XSSimpleTypeDecl.this.fEnumeration == null || !(item instanceof ShortList)) {
                return false;
            }
            for (int i = XSSimpleTypeDecl.DERIVATION_ANY; i < XSSimpleTypeDecl.this.fEnumerationSize; i += XSSimpleTypeDecl.DERIVATION_RESTRICTION) {
                if (XSSimpleTypeDecl.this.fEnumeration[i].itemValueTypes == item) {
                    return true;
                }
            }
            return false;
        }

        public Object item(int index) {
            if (index < 0 || index >= getLength()) {
                return null;
            }
            return XSSimpleTypeDecl.this.fEnumeration[index].itemValueTypes;
        }
    }

    private static final class XSFacetImpl implements XSFacet {
        final XSObjectList annotations;
        Object avalue;
        final boolean fixed;
        final int ivalue;
        final short kind;
        final String svalue;

        public XSFacetImpl(short kind, String svalue, int ivalue, Object avalue, boolean fixed, XSAnnotation annotation) {
            this.kind = kind;
            this.svalue = svalue;
            this.ivalue = ivalue;
            this.avalue = avalue;
            this.fixed = fixed;
            if (annotation != null) {
                this.annotations = new XSObjectListImpl();
                ((XSObjectListImpl) this.annotations).addXSObject(annotation);
                return;
            }
            this.annotations = XSObjectListImpl.EMPTY_LIST;
        }

        public XSAnnotation getAnnotation() {
            return (XSAnnotation) this.annotations.item(XSSimpleTypeDecl.DERIVATION_ANY);
        }

        public XSObjectList getAnnotations() {
            return this.annotations;
        }

        public short getFacetKind() {
            return this.kind;
        }

        public String getLexicalFacetValue() {
            return this.svalue;
        }

        public Object getActualFacetValue() {
            if (this.avalue == null) {
                if (this.kind == XSSimpleTypeDecl.DV_BASE64BINARY) {
                    this.avalue = this.svalue;
                } else {
                    this.avalue = BigInteger.valueOf((long) this.ivalue);
                }
            }
            return this.avalue;
        }

        public int getIntFacetValue() {
            return this.ivalue;
        }

        public boolean getFixed() {
            return this.fixed;
        }

        public String getName() {
            return null;
        }

        public String getNamespace() {
            return null;
        }

        public XSNamespaceItem getNamespaceItem() {
            return null;
        }

        public short getType() {
            return XSSimpleTypeDecl.DV_GDAY;
        }
    }

    private static final class XSMVFacetImpl implements XSMultiValueFacet {
        final XSObjectList annotations;
        final ObjectList avalues;
        final short kind;
        final StringList svalues;

        public XSMVFacetImpl(short kind, StringList svalues, ObjectList avalues, XSObjectList annotations) {
            this.kind = kind;
            this.svalues = svalues;
            this.avalues = avalues;
            if (annotations == null) {
                annotations = XSObjectListImpl.EMPTY_LIST;
            }
            this.annotations = annotations;
        }

        public short getFacetKind() {
            return this.kind;
        }

        public XSObjectList getAnnotations() {
            return this.annotations;
        }

        public StringList getLexicalFacetValues() {
            return this.svalues;
        }

        public ObjectList getEnumerationValues() {
            return this.avalues;
        }

        public String getName() {
            return null;
        }

        public String getNamespace() {
            return null;
        }

        public XSNamespaceItem getNamespaceItem() {
            return null;
        }

        public short getType() {
            return XSSimpleTypeDecl.DV_GMONTH;
        }
    }

    static {
        gDVs = new TypeValidator[]{new AnySimpleDV(), new StringDV(), new BooleanDV(), new DecimalDV(), new FloatDV(), new DoubleDV(), new DurationDV(), new DateTimeDV(), new TimeDV(), new DateDV(), new YearMonthDV(), new YearDV(), new MonthDayDV(), new DayDV(), new MonthDV(), new HexBinaryDV(), new Base64BinaryDV(), new AnyURIDV(), new QNameDV(), new PrecisionDecimalDV(), new QNameDV(), new IDDV(), new IDREFDV(), new EntityDV(), new IntegerDV(), new ListDV(), new UnionDV(), new YearMonthDurationDV(), new DayTimeDurationDV(), new AnyAtomicDV()};
        r0 = new short[30];
        fDVNormalizeType = r0;
        String[] strArr = new String[DERIVATION_UNION];
        strArr[DERIVATION_ANY] = "NONE";
        strArr[DERIVATION_RESTRICTION] = SchemaSymbols.ATTVAL_NMTOKEN;
        strArr[DERIVATION_EXTENSION] = SchemaSymbols.ATTVAL_NAME;
        strArr[3] = SchemaSymbols.ATTVAL_NCNAME;
        SPECIAL_PATTERN_STRING = strArr;
        WS_FACET_STRING = new String[]{SchemaSymbols.ATTVAL_PRESERVE, SchemaSymbols.ATTVAL_REPLACE, SchemaSymbols.ATTVAL_COLLAPSE};
        fEmptyContext = new C12651();
        fAnySimpleType = new XSSimpleTypeDecl(null, SchemaSymbols.ATTVAL_ANYSIMPLETYPE, SPECIAL_PATTERN_NONE, SPECIAL_PATTERN_NONE, false, true, false, true, SPECIAL_PATTERN_NMTOKEN);
        fAnyAtomicType = new XSSimpleTypeDecl(fAnySimpleType, "anyAtomicType", DV_ANYATOMICTYPE, SPECIAL_PATTERN_NONE, false, true, false, true, ANYATOMICTYPE_DT);
        fDummyContext = new C12662();
    }

    protected static TypeValidator[] getGDVs() {
        return (TypeValidator[]) gDVs.clone();
    }

    protected void setDVs(TypeValidator[] dvs) {
        this.fDVs = dvs;
    }

    public XSSimpleTypeDecl() {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = SPECIAL_PATTERN_NONE;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = SPECIAL_PATTERN_NONE;
        this.fFixedFacet = SPECIAL_PATTERN_NONE;
        this.fWhiteSpace = SPECIAL_PATTERN_NONE;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = SPECIAL_PATTERN_NONE;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
    }

    protected XSSimpleTypeDecl(XSSimpleTypeDecl base, String name, short validateDV, short ordered, boolean bounded, boolean finite, boolean numeric, boolean isImmutable, short builtInKind) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = SPECIAL_PATTERN_NONE;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = SPECIAL_PATTERN_NONE;
        this.fFixedFacet = SPECIAL_PATTERN_NONE;
        this.fWhiteSpace = SPECIAL_PATTERN_NONE;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = SPECIAL_PATTERN_NONE;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fIsImmutable = isImmutable;
        this.fBase = base;
        this.fTypeName = name;
        this.fTargetNamespace = URI_SCHEMAFORSCHEMA;
        this.fVariety = SPECIAL_PATTERN_NMTOKEN;
        this.fValidationDV = validateDV;
        this.fFacetsDefined = DV_BASE64BINARY;
        if (validateDV == (short) 0 || validateDV == DV_ANYATOMICTYPE || validateDV == SPECIAL_PATTERN_NMTOKEN) {
            this.fWhiteSpace = SPECIAL_PATTERN_NONE;
        } else {
            this.fWhiteSpace = SPECIAL_PATTERN_NAME;
            this.fFixedFacet = DV_BASE64BINARY;
        }
        this.fOrdered = ordered;
        this.fBounded = bounded;
        this.fFinite = finite;
        this.fNumeric = numeric;
        this.fAnnotations = null;
        this.fBuiltInKind = builtInKind;
    }

    protected XSSimpleTypeDecl(XSSimpleTypeDecl base, String name, String uri, short finalSet, boolean isImmutable, XSObjectList annotations, short builtInKind) {
        this(base, name, uri, finalSet, isImmutable, annotations);
        this.fBuiltInKind = builtInKind;
    }

    protected XSSimpleTypeDecl(XSSimpleTypeDecl base, String name, String uri, short finalSet, boolean isImmutable, XSObjectList annotations) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = SPECIAL_PATTERN_NONE;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = SPECIAL_PATTERN_NONE;
        this.fFixedFacet = SPECIAL_PATTERN_NONE;
        this.fWhiteSpace = SPECIAL_PATTERN_NONE;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = SPECIAL_PATTERN_NONE;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fBase = base;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = this.fBase.fVariety;
        this.fValidationDV = this.fBase.fValidationDV;
        switch (this.fVariety) {
            case DERIVATION_EXTENSION /*2*/:
                this.fItemType = this.fBase.fItemType;
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                this.fMemberTypes = this.fBase.fMemberTypes;
                break;
        }
        this.fLength = this.fBase.fLength;
        this.fMinLength = this.fBase.fMinLength;
        this.fMaxLength = this.fBase.fMaxLength;
        this.fPattern = this.fBase.fPattern;
        this.fPatternStr = this.fBase.fPatternStr;
        this.fEnumeration = this.fBase.fEnumeration;
        this.fEnumerationSize = this.fBase.fEnumerationSize;
        this.fWhiteSpace = this.fBase.fWhiteSpace;
        this.fMaxExclusive = this.fBase.fMaxExclusive;
        this.fMaxInclusive = this.fBase.fMaxInclusive;
        this.fMinExclusive = this.fBase.fMinExclusive;
        this.fMinInclusive = this.fBase.fMinInclusive;
        this.fTotalDigits = this.fBase.fTotalDigits;
        this.fFractionDigits = this.fBase.fFractionDigits;
        this.fPatternType = this.fBase.fPatternType;
        this.fFixedFacet = this.fBase.fFixedFacet;
        this.fFacetsDefined = this.fBase.fFacetsDefined;
        this.lengthAnnotation = this.fBase.lengthAnnotation;
        this.minLengthAnnotation = this.fBase.minLengthAnnotation;
        this.maxLengthAnnotation = this.fBase.maxLengthAnnotation;
        this.patternAnnotations = this.fBase.patternAnnotations;
        this.enumerationAnnotations = this.fBase.enumerationAnnotations;
        this.whiteSpaceAnnotation = this.fBase.whiteSpaceAnnotation;
        this.maxExclusiveAnnotation = this.fBase.maxExclusiveAnnotation;
        this.maxInclusiveAnnotation = this.fBase.maxInclusiveAnnotation;
        this.minExclusiveAnnotation = this.fBase.minExclusiveAnnotation;
        this.minInclusiveAnnotation = this.fBase.minInclusiveAnnotation;
        this.totalDigitsAnnotation = this.fBase.totalDigitsAnnotation;
        this.fractionDigitsAnnotation = this.fBase.fractionDigitsAnnotation;
        calcFundamentalFacets();
        this.fIsImmutable = isImmutable;
        this.fBuiltInKind = base.fBuiltInKind;
    }

    protected XSSimpleTypeDecl(String name, String uri, short finalSet, XSSimpleTypeDecl itemType, boolean isImmutable, XSObjectList annotations) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = SPECIAL_PATTERN_NONE;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = SPECIAL_PATTERN_NONE;
        this.fFixedFacet = SPECIAL_PATTERN_NONE;
        this.fWhiteSpace = SPECIAL_PATTERN_NONE;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = SPECIAL_PATTERN_NONE;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fBase = fAnySimpleType;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = SPECIAL_PATTERN_NAME;
        this.fItemType = itemType;
        this.fValidationDV = DV_LIST;
        this.fFacetsDefined = DV_BASE64BINARY;
        this.fFixedFacet = DV_BASE64BINARY;
        this.fWhiteSpace = SPECIAL_PATTERN_NAME;
        calcFundamentalFacets();
        this.fIsImmutable = isImmutable;
        this.fBuiltInKind = (short) 44;
    }

    protected XSSimpleTypeDecl(String name, String uri, short finalSet, XSSimpleTypeDecl[] memberTypes, XSObjectList annotations) {
        this.fDVs = gDVs;
        this.fIsImmutable = false;
        this.fFinalSet = SPECIAL_PATTERN_NONE;
        this.fVariety = (short) -1;
        this.fValidationDV = (short) -1;
        this.fFacetsDefined = SPECIAL_PATTERN_NONE;
        this.fFixedFacet = SPECIAL_PATTERN_NONE;
        this.fWhiteSpace = SPECIAL_PATTERN_NONE;
        this.fLength = -1;
        this.fMinLength = -1;
        this.fMaxLength = -1;
        this.fTotalDigits = -1;
        this.fFractionDigits = -1;
        this.fAnnotations = null;
        this.fPatternType = SPECIAL_PATTERN_NONE;
        this.fNamespaceItem = null;
        this.fAnonymous = false;
        this.fBase = fAnySimpleType;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = SPECIAL_PATTERN_NCNAME;
        this.fMemberTypes = memberTypes;
        this.fValidationDV = DV_UNION;
        this.fFacetsDefined = DV_BASE64BINARY;
        this.fWhiteSpace = SPECIAL_PATTERN_NAME;
        calcFundamentalFacets();
        this.fIsImmutable = false;
        this.fBuiltInKind = (short) 45;
    }

    protected XSSimpleTypeDecl setRestrictionValues(XSSimpleTypeDecl base, String name, String uri, short finalSet, XSObjectList annotations) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = base;
        this.fAnonymous = false;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = this.fBase.fVariety;
        this.fValidationDV = this.fBase.fValidationDV;
        switch (this.fVariety) {
            case DERIVATION_EXTENSION /*2*/:
                this.fItemType = this.fBase.fItemType;
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                this.fMemberTypes = this.fBase.fMemberTypes;
                break;
        }
        this.fLength = this.fBase.fLength;
        this.fMinLength = this.fBase.fMinLength;
        this.fMaxLength = this.fBase.fMaxLength;
        this.fPattern = this.fBase.fPattern;
        this.fPatternStr = this.fBase.fPatternStr;
        this.fEnumeration = this.fBase.fEnumeration;
        this.fEnumerationSize = this.fBase.fEnumerationSize;
        this.fWhiteSpace = this.fBase.fWhiteSpace;
        this.fMaxExclusive = this.fBase.fMaxExclusive;
        this.fMaxInclusive = this.fBase.fMaxInclusive;
        this.fMinExclusive = this.fBase.fMinExclusive;
        this.fMinInclusive = this.fBase.fMinInclusive;
        this.fTotalDigits = this.fBase.fTotalDigits;
        this.fFractionDigits = this.fBase.fFractionDigits;
        this.fPatternType = this.fBase.fPatternType;
        this.fFixedFacet = this.fBase.fFixedFacet;
        this.fFacetsDefined = this.fBase.fFacetsDefined;
        calcFundamentalFacets();
        this.fBuiltInKind = base.fBuiltInKind;
        return this;
    }

    protected XSSimpleTypeDecl setListValues(String name, String uri, short finalSet, XSSimpleTypeDecl itemType, XSObjectList annotations) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = fAnySimpleType;
        this.fAnonymous = false;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = SPECIAL_PATTERN_NAME;
        this.fItemType = itemType;
        this.fValidationDV = DV_LIST;
        this.fFacetsDefined = DV_BASE64BINARY;
        this.fFixedFacet = DV_BASE64BINARY;
        this.fWhiteSpace = SPECIAL_PATTERN_NAME;
        calcFundamentalFacets();
        this.fBuiltInKind = (short) 44;
        return this;
    }

    protected XSSimpleTypeDecl setUnionValues(String name, String uri, short finalSet, XSSimpleTypeDecl[] memberTypes, XSObjectList annotations) {
        if (this.fIsImmutable) {
            return null;
        }
        this.fBase = fAnySimpleType;
        this.fAnonymous = false;
        this.fTypeName = name;
        this.fTargetNamespace = uri;
        this.fFinalSet = finalSet;
        this.fAnnotations = annotations;
        this.fVariety = SPECIAL_PATTERN_NCNAME;
        this.fMemberTypes = memberTypes;
        this.fValidationDV = DV_UNION;
        this.fFacetsDefined = DV_BASE64BINARY;
        this.fWhiteSpace = SPECIAL_PATTERN_NAME;
        calcFundamentalFacets();
        this.fBuiltInKind = (short) 45;
        return this;
    }

    public short getType() {
        return SPECIAL_PATTERN_NCNAME;
    }

    public short getTypeCategory() {
        return DV_BASE64BINARY;
    }

    public String getName() {
        return getAnonymous() ? null : this.fTypeName;
    }

    public String getTypeName() {
        return this.fTypeName;
    }

    public String getNamespace() {
        return this.fTargetNamespace;
    }

    public short getFinal() {
        return this.fFinalSet;
    }

    public boolean isFinal(short derivation) {
        return (this.fFinalSet & derivation) != 0;
    }

    public XSTypeDefinition getBaseType() {
        return this.fBase;
    }

    public boolean getAnonymous() {
        return this.fAnonymous || this.fTypeName == null;
    }

    public short getVariety() {
        return this.fValidationDV == (short) 0 ? SPECIAL_PATTERN_NONE : this.fVariety;
    }

    public boolean isIDType() {
        switch (this.fVariety) {
            case DERIVATION_RESTRICTION /*1*/:
                if (this.fValidationDV != DV_ID) {
                    return false;
                }
                return true;
            case DERIVATION_EXTENSION /*2*/:
                return this.fItemType.isIDType();
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                for (int i = DERIVATION_ANY; i < this.fMemberTypes.length; i += DERIVATION_RESTRICTION) {
                    if (this.fMemberTypes[i].isIDType()) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public short getWhitespace() throws DatatypeException {
        if (this.fVariety != SPECIAL_PATTERN_NCNAME) {
            return this.fWhiteSpace;
        }
        Object[] objArr = new Object[DERIVATION_RESTRICTION];
        objArr[DERIVATION_ANY] = this.fTypeName;
        throw new DatatypeException("dt-whitespace", objArr);
    }

    public short getPrimitiveKind() {
        if (this.fVariety != SPECIAL_PATTERN_NMTOKEN || this.fValidationDV == (short) 0) {
            return SPECIAL_PATTERN_NONE;
        }
        if (this.fValidationDV == DV_ID || this.fValidationDV == DV_IDREF || this.fValidationDV == DV_ENTITY) {
            return SPECIAL_PATTERN_NMTOKEN;
        }
        if (this.fValidationDV == DV_INTEGER) {
            return SPECIAL_PATTERN_NCNAME;
        }
        return this.fValidationDV;
    }

    public short getBuiltInKind() {
        return this.fBuiltInKind;
    }

    public XSSimpleTypeDefinition getPrimitiveType() {
        if (this.fVariety != SPECIAL_PATTERN_NMTOKEN || this.fValidationDV == (short) 0) {
            return null;
        }
        XSSimpleTypeDecl pri = this;
        while (pri.fBase != fAnySimpleType) {
            pri = pri.fBase;
        }
        return pri;
    }

    public XSSimpleTypeDefinition getItemType() {
        if (this.fVariety == SPECIAL_PATTERN_NAME) {
            return this.fItemType;
        }
        return null;
    }

    public XSObjectList getMemberTypes() {
        if (this.fVariety == SPECIAL_PATTERN_NCNAME) {
            return new XSObjectListImpl(this.fMemberTypes, this.fMemberTypes.length);
        }
        return XSObjectListImpl.EMPTY_LIST;
    }

    public void applyFacets(XSFacets facets, short presentFacet, short fixedFacet, ValidationContext context) throws InvalidDatatypeFacetException {
        if (context == null) {
            context = fEmptyContext;
        }
        applyFacets(facets, presentFacet, fixedFacet, SPECIAL_PATTERN_NONE, context);
    }

    void applyFacets1(XSFacets facets, short presentFacet, short fixedFacet) {
        try {
            applyFacets(facets, presentFacet, fixedFacet, SPECIAL_PATTERN_NONE, fDummyContext);
            this.fIsImmutable = true;
        } catch (InvalidDatatypeFacetException e) {
            throw new RuntimeException("internal error");
        }
    }

    void applyFacets1(XSFacets facets, short presentFacet, short fixedFacet, short patternType) {
        try {
            applyFacets(facets, presentFacet, fixedFacet, patternType, fDummyContext);
            this.fIsImmutable = true;
        } catch (InvalidDatatypeFacetException e) {
            throw new RuntimeException("internal error");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void applyFacets(mf.org.apache.xerces.impl.dv.XSFacets r24, short r25, short r26, short r27, mf.org.apache.xerces.impl.dv.ValidationContext r28) throws mf.org.apache.xerces.impl.dv.InvalidDatatypeFacetException {
        /*
        r23 = this;
        r0 = r23;
        r0 = r0.fIsImmutable;
        r19 = r0;
        if (r19 == 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r18 = new mf.org.apache.xerces.impl.dv.ValidatedInfo;
        r18.<init>();
        r19 = 0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r19 = 0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;
        r16 = 0;
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r5 = r19.getAllowedFacets();
        r19 = r25 & 1;
        if (r19 == 0) goto L_0x005d;
    L_0x0036:
        r19 = r5 & 1;
        if (r19 != 0) goto L_0x12ae;
    L_0x003a:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "length";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x005d:
        r19 = r25 & 2;
        if (r19 == 0) goto L_0x0088;
    L_0x0061:
        r19 = r5 & 2;
        if (r19 != 0) goto L_0x12f2;
    L_0x0065:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "minLength";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0088:
        r19 = r25 & 4;
        if (r19 == 0) goto L_0x00b3;
    L_0x008c:
        r19 = r5 & 4;
        if (r19 != 0) goto L_0x1336;
    L_0x0090:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "maxLength";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x00b3:
        r19 = r25 & 8;
        if (r19 == 0) goto L_0x00de;
    L_0x00b7:
        r19 = r5 & 8;
        if (r19 != 0) goto L_0x137a;
    L_0x00bb:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "pattern";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x00de:
        r19 = r25 & 16;
        if (r19 == 0) goto L_0x0109;
    L_0x00e2:
        r19 = r5 & 16;
        if (r19 != 0) goto L_0x1425;
    L_0x00e6:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "whiteSpace";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0109:
        r0 = r25;
        r0 = r0 & 2048;
        r19 = r0;
        if (r19 == 0) goto L_0x013a;
    L_0x0111:
        r0 = r5 & 2048;
        r19 = r0;
        if (r19 != 0) goto L_0x1469;
    L_0x0117:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "enumeration";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x013a:
        r19 = r25 & 32;
        if (r19 == 0) goto L_0x0165;
    L_0x013e:
        r19 = r5 & 32;
        if (r19 != 0) goto L_0x153a;
    L_0x0142:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "maxInclusive";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0165:
        r13 = 1;
        r19 = r25 & 64;
        if (r19 == 0) goto L_0x0191;
    L_0x016a:
        r19 = r5 & 64;
        if (r19 != 0) goto L_0x16c4;
    L_0x016e:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "maxExclusive";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0191:
        r13 = 1;
        r0 = r25;
        r0 = r0 & 128;
        r19 = r0;
        if (r19 == 0) goto L_0x01c3;
    L_0x019a:
        r0 = r5 & 128;
        r19 = r0;
        if (r19 != 0) goto L_0x18b8;
    L_0x01a0:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "minExclusive";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x01c3:
        r0 = r25;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 == 0) goto L_0x01f4;
    L_0x01cb:
        r0 = r5 & 256;
        r19 = r0;
        if (r19 != 0) goto L_0x1ac4;
    L_0x01d1:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "minInclusive";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x01f4:
        r0 = r25;
        r0 = r0 & 512;
        r19 = r0;
        if (r19 == 0) goto L_0x0225;
    L_0x01fc:
        r0 = r5 & 512;
        r19 = r0;
        if (r19 != 0) goto L_0x1c62;
    L_0x0202:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "totalDigits";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0225:
        r0 = r25;
        r0 = r0 & 1024;
        r19 = r0;
        if (r19 == 0) goto L_0x0256;
    L_0x022d:
        r0 = r5 & 1024;
        r19 = r0;
        if (r19 != 0) goto L_0x1cb2;
    L_0x0233:
        r19 = "cos-applicable-facets";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "fractionDigits";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0256:
        if (r27 == 0) goto L_0x025e;
    L_0x0258:
        r0 = r27;
        r1 = r23;
        r1.fPatternType = r0;
    L_0x025e:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        if (r19 == 0) goto L_0x0e06;
    L_0x0266:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 2;
        if (r19 == 0) goto L_0x02c5;
    L_0x0270:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 == 0) goto L_0x02c5;
    L_0x027a:
        r0 = r23;
        r0 = r0.fMinLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fMaxLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 <= r1) goto L_0x02c5;
    L_0x028c:
        r19 = "minLength-less-than-equal-to-maxLength";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x02c5:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 64;
        if (r19 == 0) goto L_0x030a;
    L_0x02cf:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 32;
        if (r19 == 0) goto L_0x030a;
    L_0x02d9:
        r19 = "maxInclusive-maxExclusive";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fMaxInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMaxExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x030a:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 128;
        r19 = r0;
        if (r19 == 0) goto L_0x0357;
    L_0x0318:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 == 0) goto L_0x0357;
    L_0x0326:
        r19 = "minInclusive-minExclusive";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fMinInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMinExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0357:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 32;
        if (r19 == 0) goto L_0x03c8;
    L_0x0361:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 == 0) goto L_0x03c8;
    L_0x036f:
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.fMinInclusive;
        r20 = r0;
        r0 = r23;
        r0 = r0.fMaxInclusive;
        r21 = r0;
        r16 = r19.compare(r20, r21);
        r19 = -1;
        r0 = r16;
        r1 = r19;
        if (r0 == r1) goto L_0x03c8;
    L_0x0395:
        if (r16 == 0) goto L_0x03c8;
    L_0x0397:
        r19 = "minInclusive-less-than-equal-to-maxInclusive";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fMinInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMaxInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x03c8:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 64;
        if (r19 == 0) goto L_0x0439;
    L_0x03d2:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 128;
        r19 = r0;
        if (r19 == 0) goto L_0x0439;
    L_0x03e0:
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.fMinExclusive;
        r20 = r0;
        r0 = r23;
        r0 = r0.fMaxExclusive;
        r21 = r0;
        r16 = r19.compare(r20, r21);
        r19 = -1;
        r0 = r16;
        r1 = r19;
        if (r0 == r1) goto L_0x0439;
    L_0x0406:
        if (r16 == 0) goto L_0x0439;
    L_0x0408:
        r19 = "minExclusive-less-than-equal-to-maxExclusive";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fMinExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMaxExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0439:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 32;
        if (r19 == 0) goto L_0x04a8;
    L_0x0443:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 128;
        r19 = r0;
        if (r19 == 0) goto L_0x04a8;
    L_0x0451:
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.fMinExclusive;
        r20 = r0;
        r0 = r23;
        r0 = r0.fMaxInclusive;
        r21 = r0;
        r19 = r19.compare(r20, r21);
        r20 = -1;
        r0 = r19;
        r1 = r20;
        if (r0 == r1) goto L_0x04a8;
    L_0x0477:
        r19 = "minExclusive-less-than-maxInclusive";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fMinExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMaxInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x04a8:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 64;
        if (r19 == 0) goto L_0x0517;
    L_0x04b2:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 == 0) goto L_0x0517;
    L_0x04c0:
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.fMinInclusive;
        r20 = r0;
        r0 = r23;
        r0 = r0.fMaxExclusive;
        r21 = r0;
        r19 = r19.compare(r20, r21);
        r20 = -1;
        r0 = r19;
        r1 = r20;
        if (r0 == r1) goto L_0x0517;
    L_0x04e6:
        r19 = "minInclusive-less-than-maxExclusive";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fMinInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMaxExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0517:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 1024;
        r19 = r0;
        if (r19 == 0) goto L_0x057e;
    L_0x0525:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 512;
        r19 = r0;
        if (r19 == 0) goto L_0x057e;
    L_0x0533:
        r0 = r23;
        r0 = r0.fFractionDigits;
        r19 = r0;
        r0 = r23;
        r0 = r0.fTotalDigits;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 <= r1) goto L_0x057e;
    L_0x0545:
        r19 = "fractionDigits-totalDigits";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fFractionDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTotalDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x057e:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 1;
        if (r19 == 0) goto L_0x06bd;
    L_0x0588:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 2;
        if (r19 == 0) goto L_0x05ef;
    L_0x0598:
        r0 = r23;
        r0 = r0.fLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fMinLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 >= r1) goto L_0x05ef;
    L_0x05b0:
        r19 = "length-minLength-maxLength.1.1";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x05ef:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 == 0) goto L_0x0656;
    L_0x05ff:
        r0 = r23;
        r0 = r0.fLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fMaxLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 <= r1) goto L_0x0656;
    L_0x0617:
        r19 = "length-minLength-maxLength.2.1";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0656:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 1;
        if (r19 == 0) goto L_0x06bd;
    L_0x0666:
        r0 = r23;
        r0 = r0.fLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 == r1) goto L_0x06bd;
    L_0x067e:
        r19 = "length-valid-restriction";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x06bd:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 1;
        if (r19 != 0) goto L_0x06d7;
    L_0x06cd:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 1;
        if (r19 == 0) goto L_0x08a7;
    L_0x06d7:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 2;
        if (r19 == 0) goto L_0x07bc;
    L_0x06e1:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fMinLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 >= r1) goto L_0x0738;
    L_0x06f9:
        r19 = "length-minLength-maxLength.1.1";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0738:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 2;
        if (r19 != 0) goto L_0x0765;
    L_0x0748:
        r19 = "length-minLength-maxLength.1.2.a";
        r20 = 1;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0765:
        r0 = r23;
        r0 = r0.fMinLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fMinLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 == r1) goto L_0x07bc;
    L_0x077d:
        r19 = "length-minLength-maxLength.1.2.b";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x07bc:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 == 0) goto L_0x08a7;
    L_0x07c6:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fMaxLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 <= r1) goto L_0x081d;
    L_0x07de:
        r19 = "length-minLength-maxLength.2.1";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x081d:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 != 0) goto L_0x084a;
    L_0x082d:
        r19 = "length-minLength-maxLength.2.2.a";
        r20 = 1;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x084a:
        r0 = r23;
        r0 = r0.fMaxLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fMaxLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 == r1) goto L_0x08a7;
    L_0x0862:
        r19 = "length-minLength-maxLength.2.2.b";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x08a7:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 2;
        if (r19 == 0) goto L_0x0918;
    L_0x08b1:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 == 0) goto L_0x1d02;
    L_0x08c1:
        r0 = r23;
        r0 = r0.fMinLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fMaxLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 <= r1) goto L_0x0918;
    L_0x08d9:
        r19 = "minLength-less-than-equal-to-maxLength";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0918:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 == 0) goto L_0x097f;
    L_0x0922:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 2;
        if (r19 == 0) goto L_0x097f;
    L_0x0932:
        r0 = r23;
        r0 = r0.fMaxLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fMinLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 >= r1) goto L_0x097f;
    L_0x094a:
        r19 = "minLength-less-than-equal-to-maxLength";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x097f:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 == 0) goto L_0x0a5d;
    L_0x0989:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 == 0) goto L_0x0a5d;
    L_0x0999:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 == 0) goto L_0x0a06;
    L_0x09a9:
        r0 = r23;
        r0 = r0.fMaxLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fMaxLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 == r1) goto L_0x0a06;
    L_0x09c1:
        r19 = "FixedFacetValue";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "maxLength";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0a06:
        r0 = r23;
        r0 = r0.fMaxLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fMaxLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 <= r1) goto L_0x0a5d;
    L_0x0a1e:
        r19 = "maxLength-valid-restriction";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMaxLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0a5d:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 512;
        r19 = r0;
        if (r19 == 0) goto L_0x0b47;
    L_0x0a6b:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 512;
        r19 = r0;
        if (r19 == 0) goto L_0x0b47;
    L_0x0a7f:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 512;
        r19 = r0;
        if (r19 == 0) goto L_0x0af0;
    L_0x0a93:
        r0 = r23;
        r0 = r0.fTotalDigits;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fTotalDigits;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 == r1) goto L_0x0af0;
    L_0x0aab:
        r19 = "FixedFacetValue";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "totalDigits";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTotalDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fTotalDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0af0:
        r0 = r23;
        r0 = r0.fTotalDigits;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fTotalDigits;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 <= r1) goto L_0x0b47;
    L_0x0b08:
        r19 = "totalDigits-valid-restriction";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTotalDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fTotalDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0b47:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 1024;
        r19 = r0;
        if (r19 == 0) goto L_0x0bba;
    L_0x0b55:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 512;
        r19 = r0;
        if (r19 == 0) goto L_0x0bba;
    L_0x0b69:
        r0 = r23;
        r0 = r0.fFractionDigits;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fTotalDigits;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 <= r1) goto L_0x0bba;
    L_0x0b81:
        r19 = "fractionDigits-totalDigits";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fFractionDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fTotalDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0bba:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 1024;
        r19 = r0;
        if (r19 == 0) goto L_0x0cba;
    L_0x0bc8:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 1024;
        r19 = r0;
        if (r19 == 0) goto L_0x1dd8;
    L_0x0bdc:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 1024;
        r19 = r0;
        if (r19 == 0) goto L_0x0c08;
    L_0x0bf0:
        r0 = r23;
        r0 = r0.fFractionDigits;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fFractionDigits;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x0c1e;
    L_0x0c08:
        r0 = r23;
        r0 = r0.fValidationDV;
        r19 = r0;
        r20 = 24;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x0c63;
    L_0x0c16:
        r0 = r23;
        r0 = r0.fFractionDigits;
        r19 = r0;
        if (r19 == 0) goto L_0x0c63;
    L_0x0c1e:
        r19 = "FixedFacetValue";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "fractionDigits";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fFractionDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fFractionDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0c63:
        r0 = r23;
        r0 = r0.fFractionDigits;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fFractionDigits;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 <= r1) goto L_0x0cba;
    L_0x0c7b:
        r19 = "fractionDigits-valid-restriction";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fFractionDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fFractionDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0cba:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 16;
        if (r19 == 0) goto L_0x0e06;
    L_0x0cc4:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 16;
        if (r19 == 0) goto L_0x0e06;
    L_0x0cd4:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r19 = r19 & 16;
        if (r19 == 0) goto L_0x0d49;
    L_0x0ce4:
        r0 = r23;
        r0 = r0.fWhiteSpace;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fWhiteSpace;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 == r1) goto L_0x0d49;
    L_0x0cfc:
        r19 = "FixedFacetValue";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "whiteSpace";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fWhiteSpace;
        r22 = r0;
        r0 = r23;
        r1 = r22;
        r22 = r0.whiteSpaceValue(r1);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fWhiteSpace;
        r22 = r0;
        r0 = r23;
        r1 = r22;
        r22 = r0.whiteSpaceValue(r1);
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0d49:
        r0 = r23;
        r0 = r0.fWhiteSpace;
        r19 = r0;
        if (r19 != 0) goto L_0x0d88;
    L_0x0d51:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fWhiteSpace;
        r19 = r0;
        r20 = 2;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x0d88;
    L_0x0d65:
        r19 = "whiteSpace-valid-restriction.1";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r22 = "preserve";
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0d88:
        r0 = r23;
        r0 = r0.fWhiteSpace;
        r19 = r0;
        r20 = 1;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x0dcd;
    L_0x0d96:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fWhiteSpace;
        r19 = r0;
        r20 = 2;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x0dcd;
    L_0x0daa:
        r19 = "whiteSpace-valid-restriction.1";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r22 = "replace";
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0dcd:
        r0 = r23;
        r0 = r0.fWhiteSpace;
        r19 = r0;
        if (r19 != 0) goto L_0x0e06;
    L_0x0dd5:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fWhiteSpace;
        r19 = r0;
        r20 = 1;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x0e06;
    L_0x0de9:
        r19 = "whiteSpace-valid-restriction.2";
        r20 = 1;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x0e06:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 1;
        if (r19 != 0) goto L_0x0e57;
    L_0x0e10:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 1;
        if (r19 == 0) goto L_0x0e57;
    L_0x0e20:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 1;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fLength;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fLength = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.lengthAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.lengthAnnotation = r0;
    L_0x0e57:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 2;
        if (r19 != 0) goto L_0x0ea8;
    L_0x0e61:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 2;
        if (r19 == 0) goto L_0x0ea8;
    L_0x0e71:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 2;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fMinLength;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fMinLength = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.minLengthAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.minLengthAnnotation = r0;
    L_0x0ea8:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 != 0) goto L_0x0ef9;
    L_0x0eb2:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 4;
        if (r19 == 0) goto L_0x0ef9;
    L_0x0ec2:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 4;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fMaxLength;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fMaxLength = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.maxLengthAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.maxLengthAnnotation = r0;
    L_0x0ef9:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 8;
        if (r19 == 0) goto L_0x0f5c;
    L_0x0f09:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 8;
        if (r19 != 0) goto L_0x1e27;
    L_0x0f13:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 8;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fPattern;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fPattern = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fPatternStr;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fPatternStr = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.patternAnnotations;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.patternAnnotations = r0;
    L_0x0f5c:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 16;
        if (r19 != 0) goto L_0x0fad;
    L_0x0f66:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 16;
        if (r19 == 0) goto L_0x0fad;
    L_0x0f76:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 16;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fWhiteSpace;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fWhiteSpace = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.whiteSpaceAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.whiteSpaceAnnotation = r0;
    L_0x0fad:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 2048;
        r19 = r0;
        if (r19 != 0) goto L_0x101c;
    L_0x0fbb:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 2048;
        r19 = r0;
        if (r19 == 0) goto L_0x101c;
    L_0x0fcf:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 2048;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fEnumeration;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fEnumeration = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fEnumerationSize;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fEnumerationSize = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.enumerationAnnotations;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.enumerationAnnotations = r0;
    L_0x101c:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 64;
        if (r19 == 0) goto L_0x1077;
    L_0x102c:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 64;
        if (r19 != 0) goto L_0x1077;
    L_0x1036:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 32;
        if (r19 != 0) goto L_0x1077;
    L_0x1040:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 64;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fMaxExclusive;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fMaxExclusive = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.maxExclusiveAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.maxExclusiveAnnotation = r0;
    L_0x1077:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 32;
        if (r19 == 0) goto L_0x10d2;
    L_0x1087:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 64;
        if (r19 != 0) goto L_0x10d2;
    L_0x1091:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 32;
        if (r19 != 0) goto L_0x10d2;
    L_0x109b:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 32;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fMaxInclusive;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fMaxInclusive = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.maxInclusiveAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.maxInclusiveAnnotation = r0;
    L_0x10d2:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 128;
        r19 = r0;
        if (r19 == 0) goto L_0x113d;
    L_0x10e6:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 128;
        r19 = r0;
        if (r19 != 0) goto L_0x113d;
    L_0x10f4:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 != 0) goto L_0x113d;
    L_0x1102:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 128;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fMinExclusive;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fMinExclusive = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.minExclusiveAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.minExclusiveAnnotation = r0;
    L_0x113d:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 == 0) goto L_0x11a8;
    L_0x1151:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 128;
        r19 = r0;
        if (r19 != 0) goto L_0x11a8;
    L_0x115f:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 != 0) goto L_0x11a8;
    L_0x116d:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 256;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fMinInclusive;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fMinInclusive = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.minInclusiveAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.minInclusiveAnnotation = r0;
    L_0x11a8:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 512;
        r19 = r0;
        if (r19 == 0) goto L_0x1205;
    L_0x11bc:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 512;
        r19 = r0;
        if (r19 != 0) goto L_0x1205;
    L_0x11ca:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 512;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fTotalDigits;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fTotalDigits = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.totalDigitsAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.totalDigitsAnnotation = r0;
    L_0x1205:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 1024;
        r19 = r0;
        if (r19 == 0) goto L_0x1262;
    L_0x1219:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 1024;
        r19 = r0;
        if (r19 != 0) goto L_0x1262;
    L_0x1227:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 1024;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFractionDigits;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFractionDigits = r0;
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fractionDigitsAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fractionDigitsAnnotation = r0;
    L_0x1262:
        r0 = r23;
        r0 = r0.fPatternType;
        r19 = r0;
        if (r19 != 0) goto L_0x128a;
    L_0x126a:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fPatternType;
        r19 = r0;
        if (r19 == 0) goto L_0x128a;
    L_0x1278:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fPatternType;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fPatternType = r0;
    L_0x128a:
        r0 = r23;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fFixedFacet;
        r20 = r0;
        r19 = r19 | r20;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;
        r23.calcFundamentalFacets();
        goto L_0x0008;
    L_0x12ae:
        r0 = r24;
        r0 = r0.length;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fLength = r0;
        r0 = r24;
        r0 = r0.lengthAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.lengthAnnotation = r0;
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 1;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r19 = r26 & 1;
        if (r19 == 0) goto L_0x005d;
    L_0x12dd:
        r0 = r23;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r19 = r19 | 1;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;
        goto L_0x005d;
    L_0x12f2:
        r0 = r24;
        r0 = r0.minLength;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fMinLength = r0;
        r0 = r24;
        r0 = r0.minLengthAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.minLengthAnnotation = r0;
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 2;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r19 = r26 & 2;
        if (r19 == 0) goto L_0x0088;
    L_0x1321:
        r0 = r23;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r19 = r19 | 2;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;
        goto L_0x0088;
    L_0x1336:
        r0 = r24;
        r0 = r0.maxLength;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fMaxLength = r0;
        r0 = r24;
        r0 = r0.maxLengthAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.maxLengthAnnotation = r0;
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 4;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r19 = r26 & 4;
        if (r19 == 0) goto L_0x00b3;
    L_0x1365:
        r0 = r23;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r19 = r19 | 4;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;
        goto L_0x00b3;
    L_0x137a:
        r0 = r24;
        r0 = r0.patternAnnotations;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.patternAnnotations = r0;
        r14 = 0;
        r15 = new mf.org.apache.xerces.impl.xpath.regex.RegularExpression;	 Catch:{ Exception -> 0x13fd }
        r0 = r24;
        r0 = r0.pattern;	 Catch:{ Exception -> 0x13fd }
        r19 = r0;
        r20 = "X";
        r21 = r28.getLocale();	 Catch:{ Exception -> 0x13fd }
        r0 = r19;
        r1 = r20;
        r2 = r21;
        r15.<init>(r0, r1, r2);	 Catch:{ Exception -> 0x13fd }
        r14 = r15;
    L_0x139f:
        if (r14 == 0) goto L_0x00de;
    L_0x13a1:
        r19 = new java.util.Vector;
        r19.<init>();
        r0 = r19;
        r1 = r23;
        r1.fPattern = r0;
        r0 = r23;
        r0 = r0.fPattern;
        r19 = r0;
        r0 = r19;
        r0.addElement(r14);
        r19 = new java.util.Vector;
        r19.<init>();
        r0 = r19;
        r1 = r23;
        r1.fPatternStr = r0;
        r0 = r23;
        r0 = r0.fPatternStr;
        r19 = r0;
        r0 = r24;
        r0 = r0.pattern;
        r20 = r0;
        r19.addElement(r20);
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 8;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r19 = r26 & 8;
        if (r19 == 0) goto L_0x00de;
    L_0x13e8:
        r0 = r23;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r19 = r19 | 8;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;
        goto L_0x00de;
    L_0x13fd:
        r7 = move-exception;
        r19 = "InvalidRegex";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r24;
        r0 = r0.pattern;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r22 = r7.getLocalizedMessage();
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x139f;
    L_0x1425:
        r0 = r24;
        r0 = r0.whiteSpace;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fWhiteSpace = r0;
        r0 = r24;
        r0 = r0.whiteSpaceAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.whiteSpaceAnnotation = r0;
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 | 16;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r19 = r26 & 16;
        if (r19 == 0) goto L_0x0109;
    L_0x1454:
        r0 = r23;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r19 = r19 | 16;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;
        goto L_0x0109;
    L_0x1469:
        r0 = r24;
        r9 = r0.enumeration;
        r17 = r9.size();
        r0 = r17;
        r0 = new mf.org.apache.xerces.impl.dv.ValidatedInfo[r0];
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fEnumeration = r0;
        r0 = r24;
        r8 = r0.enumNSDecls;
        r6 = new mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl$ValidationContextImpl;
        r0 = r28;
        r6.<init>(r0);
        r0 = r24;
        r0 = r0.enumAnnotations;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.enumerationAnnotations = r0;
        r19 = 0;
        r0 = r19;
        r1 = r23;
        r1.fEnumerationSize = r0;
        r10 = 0;
    L_0x149d:
        r0 = r17;
        if (r10 < r0) goto L_0x14d9;
    L_0x14a1:
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 2048;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r26;
        r0 = r0 & 2048;
        r19 = r0;
        if (r19 == 0) goto L_0x013a;
    L_0x14c0:
        r0 = r23;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 2048;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;
        goto L_0x013a;
    L_0x14d9:
        if (r8 == 0) goto L_0x14e6;
    L_0x14db:
        r19 = r8.elementAt(r10);
        r19 = (mf.org.apache.xerces.xni.NamespaceContext) r19;
        r0 = r19;
        r6.setNSContext(r0);
    L_0x14e6:
        r19 = r9.elementAt(r10);	 Catch:{ InvalidDatatypeValueException -> 0x1511 }
        r19 = (java.lang.String) r19;	 Catch:{ InvalidDatatypeValueException -> 0x1511 }
        r20 = 0;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r12 = r0.getActualEnumValue(r1, r6, r2);	 Catch:{ InvalidDatatypeValueException -> 0x1511 }
        r0 = r23;
        r0 = r0.fEnumeration;	 Catch:{ InvalidDatatypeValueException -> 0x1511 }
        r19 = r0;
        r0 = r23;
        r0 = r0.fEnumerationSize;	 Catch:{ InvalidDatatypeValueException -> 0x1511 }
        r20 = r0;
        r21 = r20 + 1;
        r0 = r21;
        r1 = r23;
        r1.fEnumerationSize = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1511 }
        r19[r20] = r12;	 Catch:{ InvalidDatatypeValueException -> 0x1511 }
    L_0x150e:
        r10 = r10 + 1;
        goto L_0x149d;
    L_0x1511:
        r11 = move-exception;
        r19 = "enumeration-valid-restriction";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = r9.elementAt(r10);
        r20[r21] = r22;
        r21 = 1;
        r22 = r23.getBaseType();
        r22 = r22.getName();
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x150e;
    L_0x153a:
        r0 = r24;
        r0 = r0.maxInclusiveAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.maxInclusiveAnnotation = r0;
        r0 = r23;
        r0 = r0.fBase;	 Catch:{ InvalidDatatypeValueException -> 0x1675 }
        r19 = r0;
        r0 = r24;
        r0 = r0.maxInclusive;	 Catch:{ InvalidDatatypeValueException -> 0x1675 }
        r20 = r0;
        r21 = 1;
        r0 = r19;
        r1 = r20;
        r2 = r28;
        r3 = r18;
        r4 = r21;
        r19 = r0.getActualValue(r1, r2, r3, r4);	 Catch:{ InvalidDatatypeValueException -> 0x1675 }
        r0 = r19;
        r1 = r23;
        r1.fMaxInclusive = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1675 }
        r0 = r23;
        r0 = r0.fFacetsDefined;	 Catch:{ InvalidDatatypeValueException -> 0x1675 }
        r19 = r0;
        r19 = r19 | 32;
        r0 = r19;
        r0 = (short) r0;	 Catch:{ InvalidDatatypeValueException -> 0x1675 }
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1675 }
        r19 = r26 & 32;
        if (r19 == 0) goto L_0x1592;
    L_0x157f:
        r0 = r23;
        r0 = r0.fFixedFacet;	 Catch:{ InvalidDatatypeValueException -> 0x1675 }
        r19 = r0;
        r19 = r19 | 32;
        r0 = r19;
        r0 = (short) r0;	 Catch:{ InvalidDatatypeValueException -> 0x1675 }
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1675 }
    L_0x1592:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 32;
        if (r19 == 0) goto L_0x1615;
    L_0x15a2:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r19 = r19 & 32;
        if (r19 == 0) goto L_0x1615;
    L_0x15b2:
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.fMaxInclusive;
        r20 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r21 = r0;
        r0 = r21;
        r0 = r0.fMaxInclusive;
        r21 = r0;
        r19 = r19.compare(r20, r21);
        if (r19 == 0) goto L_0x1615;
    L_0x15d8:
        r19 = "FixedFacetValue";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "maxInclusive";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMaxInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMaxInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x1615:
        r0 = r23;
        r0 = r0.fBase;	 Catch:{ InvalidDatatypeValueException -> 0x1626 }
        r19 = r0;
        r0 = r19;
        r1 = r28;
        r2 = r18;
        r0.validate(r1, r2);	 Catch:{ InvalidDatatypeValueException -> 0x1626 }
        goto L_0x0165;
    L_0x1626:
        r11 = move-exception;
        r19 = r11.getKey();
        r20 = r11.getArgs();
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        r19 = "FacetValueFromBase";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.maxInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r22 = "maxInclusive";
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r22 = r22.getName();
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x0165;
    L_0x1675:
        r11 = move-exception;
        r19 = r11.getKey();
        r20 = r11.getArgs();
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        r19 = "FacetValueFromBase";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.maxInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r22 = "maxInclusive";
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r22 = r22.getName();
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x1592;
    L_0x16c4:
        r0 = r24;
        r0 = r0.maxExclusiveAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.maxExclusiveAnnotation = r0;
        r0 = r23;
        r0 = r0.fBase;	 Catch:{ InvalidDatatypeValueException -> 0x1804 }
        r19 = r0;
        r0 = r24;
        r0 = r0.maxExclusive;	 Catch:{ InvalidDatatypeValueException -> 0x1804 }
        r20 = r0;
        r21 = 1;
        r0 = r19;
        r1 = r20;
        r2 = r28;
        r3 = r18;
        r4 = r21;
        r19 = r0.getActualValue(r1, r2, r3, r4);	 Catch:{ InvalidDatatypeValueException -> 0x1804 }
        r0 = r19;
        r1 = r23;
        r1.fMaxExclusive = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1804 }
        r0 = r23;
        r0 = r0.fFacetsDefined;	 Catch:{ InvalidDatatypeValueException -> 0x1804 }
        r19 = r0;
        r19 = r19 | 64;
        r0 = r19;
        r0 = (short) r0;	 Catch:{ InvalidDatatypeValueException -> 0x1804 }
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1804 }
        r19 = r26 & 64;
        if (r19 == 0) goto L_0x171c;
    L_0x1709:
        r0 = r23;
        r0 = r0.fFixedFacet;	 Catch:{ InvalidDatatypeValueException -> 0x1804 }
        r19 = r0;
        r19 = r19 | 64;
        r0 = r19;
        r0 = (short) r0;	 Catch:{ InvalidDatatypeValueException -> 0x1804 }
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1804 }
    L_0x171c:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 64;
        if (r19 == 0) goto L_0x17a2;
    L_0x172c:
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.fMaxExclusive;
        r20 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r21 = r0;
        r0 = r21;
        r0 = r0.fMaxExclusive;
        r21 = r0;
        r16 = r19.compare(r20, r21);
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r19 = r19 & 64;
        if (r19 == 0) goto L_0x179f;
    L_0x1760:
        if (r16 == 0) goto L_0x179f;
    L_0x1762:
        r19 = "FixedFacetValue";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "maxExclusive";
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.maxExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMaxExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x179f:
        if (r16 != 0) goto L_0x17a2;
    L_0x17a1:
        r13 = 0;
    L_0x17a2:
        if (r13 == 0) goto L_0x1853;
    L_0x17a4:
        r0 = r23;
        r0 = r0.fBase;	 Catch:{ InvalidDatatypeValueException -> 0x17b5 }
        r19 = r0;
        r0 = r19;
        r1 = r28;
        r2 = r18;
        r0.validate(r1, r2);	 Catch:{ InvalidDatatypeValueException -> 0x17b5 }
        goto L_0x0191;
    L_0x17b5:
        r11 = move-exception;
        r19 = r11.getKey();
        r20 = r11.getArgs();
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        r19 = "FacetValueFromBase";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.maxExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r22 = "maxExclusive";
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r22 = r22.getName();
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x0191;
    L_0x1804:
        r11 = move-exception;
        r19 = r11.getKey();
        r20 = r11.getArgs();
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        r19 = "FacetValueFromBase";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.maxExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r22 = "maxExclusive";
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r22 = r22.getName();
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x171c;
    L_0x1853:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 32;
        if (r19 == 0) goto L_0x0191;
    L_0x1863:
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.fMaxExclusive;
        r20 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r21 = r0;
        r0 = r21;
        r0 = r0.fMaxInclusive;
        r21 = r0;
        r19 = r19.compare(r20, r21);
        if (r19 <= 0) goto L_0x0191;
    L_0x1889:
        r19 = "maxExclusive-valid-restriction.2";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r24;
        r0 = r0.maxExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMaxInclusive;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x0191;
    L_0x18b8:
        r0 = r24;
        r0 = r0.minExclusiveAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.minExclusiveAnnotation = r0;
        r0 = r23;
        r0 = r0.fBase;	 Catch:{ InvalidDatatypeValueException -> 0x1a0c }
        r19 = r0;
        r0 = r24;
        r0 = r0.minExclusive;	 Catch:{ InvalidDatatypeValueException -> 0x1a0c }
        r20 = r0;
        r21 = 1;
        r0 = r19;
        r1 = r20;
        r2 = r28;
        r3 = r18;
        r4 = r21;
        r19 = r0.getActualValue(r1, r2, r3, r4);	 Catch:{ InvalidDatatypeValueException -> 0x1a0c }
        r0 = r19;
        r1 = r23;
        r1.fMinExclusive = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1a0c }
        r0 = r23;
        r0 = r0.fFacetsDefined;	 Catch:{ InvalidDatatypeValueException -> 0x1a0c }
        r19 = r0;
        r0 = r19;
        r0 = r0 | 128;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;	 Catch:{ InvalidDatatypeValueException -> 0x1a0c }
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1a0c }
        r0 = r26;
        r0 = r0 & 128;
        r19 = r0;
        if (r19 == 0) goto L_0x191c;
    L_0x1905:
        r0 = r23;
        r0 = r0.fFixedFacet;	 Catch:{ InvalidDatatypeValueException -> 0x1a0c }
        r19 = r0;
        r0 = r19;
        r0 = r0 | 128;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;	 Catch:{ InvalidDatatypeValueException -> 0x1a0c }
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1a0c }
    L_0x191c:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 128;
        r19 = r0;
        if (r19 == 0) goto L_0x19aa;
    L_0x1930:
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.fMinExclusive;
        r20 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r21 = r0;
        r0 = r21;
        r0 = r0.fMinExclusive;
        r21 = r0;
        r16 = r19.compare(r20, r21);
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 128;
        r19 = r0;
        if (r19 == 0) goto L_0x19a7;
    L_0x1968:
        if (r16 == 0) goto L_0x19a7;
    L_0x196a:
        r19 = "FixedFacetValue";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "minExclusive";
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.minExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMinExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x19a7:
        if (r16 != 0) goto L_0x19aa;
    L_0x19a9:
        r13 = 0;
    L_0x19aa:
        if (r13 == 0) goto L_0x1a5b;
    L_0x19ac:
        r0 = r23;
        r0 = r0.fBase;	 Catch:{ InvalidDatatypeValueException -> 0x19bd }
        r19 = r0;
        r0 = r19;
        r1 = r28;
        r2 = r18;
        r0.validate(r1, r2);	 Catch:{ InvalidDatatypeValueException -> 0x19bd }
        goto L_0x01c3;
    L_0x19bd:
        r11 = move-exception;
        r19 = r11.getKey();
        r20 = r11.getArgs();
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        r19 = "FacetValueFromBase";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.minExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r22 = "minExclusive";
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r22 = r22.getName();
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x01c3;
    L_0x1a0c:
        r11 = move-exception;
        r19 = r11.getKey();
        r20 = r11.getArgs();
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        r19 = "FacetValueFromBase";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.minExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r22 = "minExclusive";
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r22 = r22.getName();
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x191c;
    L_0x1a5b:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 == 0) goto L_0x01c3;
    L_0x1a6f:
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.fMinExclusive;
        r20 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r21 = r0;
        r0 = r21;
        r0 = r0.fMinInclusive;
        r21 = r0;
        r19 = r19.compare(r20, r21);
        if (r19 >= 0) goto L_0x01c3;
    L_0x1a95:
        r19 = "minExclusive-valid-restriction.3";
        r20 = 2;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r24;
        r0 = r0.minExclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMinInclusive;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x01c3;
    L_0x1ac4:
        r0 = r24;
        r0 = r0.minInclusiveAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.minInclusiveAnnotation = r0;
        r0 = r23;
        r0 = r0.fBase;	 Catch:{ InvalidDatatypeValueException -> 0x1c13 }
        r19 = r0;
        r0 = r24;
        r0 = r0.minInclusive;	 Catch:{ InvalidDatatypeValueException -> 0x1c13 }
        r20 = r0;
        r21 = 1;
        r0 = r19;
        r1 = r20;
        r2 = r28;
        r3 = r18;
        r4 = r21;
        r19 = r0.getActualValue(r1, r2, r3, r4);	 Catch:{ InvalidDatatypeValueException -> 0x1c13 }
        r0 = r19;
        r1 = r23;
        r1.fMinInclusive = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1c13 }
        r0 = r23;
        r0 = r0.fFacetsDefined;	 Catch:{ InvalidDatatypeValueException -> 0x1c13 }
        r19 = r0;
        r0 = r19;
        r0 = r0 | 256;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;	 Catch:{ InvalidDatatypeValueException -> 0x1c13 }
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1c13 }
        r0 = r26;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 == 0) goto L_0x1b28;
    L_0x1b11:
        r0 = r23;
        r0 = r0.fFixedFacet;	 Catch:{ InvalidDatatypeValueException -> 0x1c13 }
        r19 = r0;
        r0 = r19;
        r0 = r0 | 256;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;	 Catch:{ InvalidDatatypeValueException -> 0x1c13 }
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;	 Catch:{ InvalidDatatypeValueException -> 0x1c13 }
    L_0x1b28:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 == 0) goto L_0x1bb3;
    L_0x1b3c:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r0 = r19;
        r0 = r0 & 256;
        r19 = r0;
        if (r19 == 0) goto L_0x1bb3;
    L_0x1b50:
        r0 = r23;
        r0 = r0.fDVs;
        r19 = r0;
        r0 = r23;
        r0 = r0.fValidationDV;
        r20 = r0;
        r19 = r19[r20];
        r0 = r23;
        r0 = r0.fMinInclusive;
        r20 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r21 = r0;
        r0 = r21;
        r0 = r0.fMinInclusive;
        r21 = r0;
        r19 = r19.compare(r20, r21);
        if (r19 == 0) goto L_0x1bb3;
    L_0x1b76:
        r19 = "FixedFacetValue";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "minInclusive";
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.minInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMinInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x1bb3:
        r0 = r23;
        r0 = r0.fBase;	 Catch:{ InvalidDatatypeValueException -> 0x1bc4 }
        r19 = r0;
        r0 = r19;
        r1 = r28;
        r2 = r18;
        r0.validate(r1, r2);	 Catch:{ InvalidDatatypeValueException -> 0x1bc4 }
        goto L_0x01f4;
    L_0x1bc4:
        r11 = move-exception;
        r19 = r11.getKey();
        r20 = r11.getArgs();
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        r19 = "FacetValueFromBase";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.minInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r22 = "minInclusive";
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r22 = r22.getName();
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x01f4;
    L_0x1c13:
        r11 = move-exception;
        r19 = r11.getKey();
        r20 = r11.getArgs();
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        r19 = "FacetValueFromBase";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r21 = 1;
        r0 = r24;
        r0 = r0.minInclusive;
        r22 = r0;
        r20[r21] = r22;
        r21 = 2;
        r22 = "minInclusive";
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r22 = r22.getName();
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x1b28;
    L_0x1c62:
        r0 = r24;
        r0 = r0.totalDigitsAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.totalDigitsAnnotation = r0;
        r0 = r24;
        r0 = r0.totalDigits;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fTotalDigits = r0;
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 512;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r26;
        r0 = r0 & 512;
        r19 = r0;
        if (r19 == 0) goto L_0x0225;
    L_0x1c99:
        r0 = r23;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 512;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;
        goto L_0x0225;
    L_0x1cb2:
        r0 = r24;
        r0 = r0.fractionDigits;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFractionDigits = r0;
        r0 = r24;
        r0 = r0.fractionDigitsAnnotation;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fractionDigitsAnnotation = r0;
        r0 = r23;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 1024;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFacetsDefined = r0;
        r0 = r26;
        r0 = r0 & 1024;
        r19 = r0;
        if (r19 == 0) goto L_0x0256;
    L_0x1ce9:
        r0 = r23;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r0 = r19;
        r0 = r0 | 1024;
        r19 = r0;
        r0 = r19;
        r0 = (short) r0;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.fFixedFacet = r0;
        goto L_0x0256;
    L_0x1d02:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFacetsDefined;
        r19 = r0;
        r19 = r19 & 2;
        if (r19 == 0) goto L_0x0918;
    L_0x1d12:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fFixedFacet;
        r19 = r0;
        r19 = r19 & 2;
        if (r19 == 0) goto L_0x1d7f;
    L_0x1d22:
        r0 = r23;
        r0 = r0.fMinLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fMinLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 == r1) goto L_0x1d7f;
    L_0x1d3a:
        r19 = "FixedFacetValue";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "minLength";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
    L_0x1d7f:
        r0 = r23;
        r0 = r0.fMinLength;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fMinLength;
        r20 = r0;
        r0 = r19;
        r1 = r20;
        if (r0 >= r1) goto L_0x0918;
    L_0x1d97:
        r19 = "minLength-valid-restriction";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r0 = r23;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fBase;
        r22 = r0;
        r0 = r22;
        r0 = r0.fMinLength;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x0918;
    L_0x1dd8:
        r0 = r23;
        r0 = r0.fValidationDV;
        r19 = r0;
        r20 = 24;
        r0 = r19;
        r1 = r20;
        if (r0 != r1) goto L_0x0cba;
    L_0x1de6:
        r0 = r23;
        r0 = r0.fFractionDigits;
        r19 = r0;
        if (r19 == 0) goto L_0x0cba;
    L_0x1dee:
        r19 = "FixedFacetValue";
        r20 = 4;
        r0 = r20;
        r0 = new java.lang.Object[r0];
        r20 = r0;
        r21 = 0;
        r22 = "fractionDigits";
        r20[r21] = r22;
        r21 = 1;
        r0 = r23;
        r0 = r0.fFractionDigits;
        r22 = r0;
        r22 = java.lang.Integer.toString(r22);
        r20[r21] = r22;
        r21 = 2;
        r22 = "0";
        r20[r21] = r22;
        r21 = 3;
        r0 = r23;
        r0 = r0.fTypeName;
        r22 = r0;
        r20[r21] = r22;
        r0 = r23;
        r1 = r19;
        r2 = r20;
        r0.reportError(r1, r2);
        goto L_0x0cba;
    L_0x1e27:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.fPattern;
        r19 = r0;
        r19 = r19.size();
        r10 = r19 + -1;
    L_0x1e39:
        if (r10 >= 0) goto L_0x1e83;
    L_0x1e3b:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.patternAnnotations;
        r19 = r0;
        if (r19 == 0) goto L_0x0f5c;
    L_0x1e49:
        r0 = r23;
        r0 = r0.patternAnnotations;
        r19 = r0;
        if (r19 == 0) goto L_0x1ebd;
    L_0x1e51:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.patternAnnotations;
        r19 = r0;
        r19 = r19.getLength();
        r10 = r19 + -1;
    L_0x1e63:
        if (r10 < 0) goto L_0x0f5c;
    L_0x1e65:
        r0 = r23;
        r0 = r0.patternAnnotations;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.patternAnnotations;
        r20 = r0;
        r0 = r20;
        r20 = r0.item(r10);
        r19.addXSObject(r20);
        r10 = r10 + -1;
        goto L_0x1e63;
    L_0x1e83:
        r0 = r23;
        r0 = r0.fPattern;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fPattern;
        r20 = r0;
        r0 = r20;
        r20 = r0.elementAt(r10);
        r19.addElement(r20);
        r0 = r23;
        r0 = r0.fPatternStr;
        r19 = r0;
        r0 = r23;
        r0 = r0.fBase;
        r20 = r0;
        r0 = r20;
        r0 = r0.fPatternStr;
        r20 = r0;
        r0 = r20;
        r20 = r0.elementAt(r10);
        r19.addElement(r20);
        r10 = r10 + -1;
        goto L_0x1e39;
    L_0x1ebd:
        r0 = r23;
        r0 = r0.fBase;
        r19 = r0;
        r0 = r19;
        r0 = r0.patternAnnotations;
        r19 = r0;
        r0 = r19;
        r1 = r23;
        r1.patternAnnotations = r0;
        goto L_0x0f5c;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl.applyFacets(mf.org.apache.xerces.impl.dv.XSFacets, short, short, short, mf.org.apache.xerces.impl.dv.ValidationContext):void");
    }

    public Object validate(String content, ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        } else {
            validatedInfo.memberType = null;
        }
        boolean needNormalize = context == null || context.needToNormalize();
        Object ob = getActualValue(content, context, validatedInfo, needNormalize);
        validate(context, validatedInfo);
        return ob;
    }

    protected ValidatedInfo getActualEnumValue(String lexical, ValidationContext ctx, ValidatedInfo info) throws InvalidDatatypeValueException {
        return this.fBase.validateWithInfo(lexical, ctx, info);
    }

    public ValidatedInfo validateWithInfo(String content, ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        } else {
            validatedInfo.memberType = null;
        }
        boolean needNormalize = context == null || context.needToNormalize();
        getActualValue(content, context, validatedInfo, needNormalize);
        validate(context, validatedInfo);
        return validatedInfo;
    }

    public Object validate(Object content, ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = fEmptyContext;
        }
        if (validatedInfo == null) {
            validatedInfo = new ValidatedInfo();
        } else {
            validatedInfo.memberType = null;
        }
        boolean needNormalize = context == null || context.needToNormalize();
        Object ob = getActualValue(content, context, validatedInfo, needNormalize);
        validate(context, validatedInfo);
        return ob;
    }

    public void validate(ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        if (context == null) {
            context = fEmptyContext;
        }
        if (!(!context.needFacetChecking() || this.fFacetsDefined == (short) 0 || this.fFacetsDefined == DV_BASE64BINARY)) {
            checkFacets(validatedInfo);
        }
        if (context.needExtraChecking()) {
            checkExtraRules(context, validatedInfo);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkFacets(mf.org.apache.xerces.impl.dv.ValidatedInfo r28) throws mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException {
        /*
        r27 = this;
        r0 = r28;
        r10 = r0.actualValue;
        r0 = r28;
        r3 = r0.normalizedValue;
        r0 = r28;
        r0 = r0.actualValueType;
        r19 = r0;
        r0 = r28;
        r7 = r0.itemValueTypes;
        r0 = r27;
        r0 = r0.fValidationDV;
        r22 = r0;
        r23 = 18;
        r0 = r22;
        r1 = r23;
        if (r0 == r1) goto L_0x011a;
    L_0x0020:
        r0 = r27;
        r0 = r0.fValidationDV;
        r22 = r0;
        r23 = 20;
        r0 = r22;
        r1 = r23;
        if (r0 == r1) goto L_0x011a;
    L_0x002e:
        r0 = r27;
        r0 = r0.fDVs;
        r22 = r0;
        r0 = r27;
        r0 = r0.fValidationDV;
        r23 = r0;
        r22 = r22[r23];
        r0 = r22;
        r9 = r0.getDataLength(r10);
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r22 = r0;
        r22 = r22 & 4;
        if (r22 == 0) goto L_0x008a;
    L_0x004c:
        r0 = r27;
        r0 = r0.fMaxLength;
        r22 = r0;
        r0 = r22;
        if (r9 <= r0) goto L_0x008a;
    L_0x0056:
        r22 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r23 = "cvc-maxLength-valid";
        r24 = 4;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r3;
        r25 = 1;
        r26 = java.lang.Integer.toString(r9);
        r24[r25] = r26;
        r25 = 2;
        r0 = r27;
        r0 = r0.fMaxLength;
        r26 = r0;
        r26 = java.lang.Integer.toString(r26);
        r24[r25] = r26;
        r25 = 3;
        r0 = r27;
        r0 = r0.fTypeName;
        r26 = r0;
        r24[r25] = r26;
        r22.<init>(r23, r24);
        throw r22;
    L_0x008a:
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r22 = r0;
        r22 = r22 & 2;
        if (r22 == 0) goto L_0x00d2;
    L_0x0094:
        r0 = r27;
        r0 = r0.fMinLength;
        r22 = r0;
        r0 = r22;
        if (r9 >= r0) goto L_0x00d2;
    L_0x009e:
        r22 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r23 = "cvc-minLength-valid";
        r24 = 4;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r3;
        r25 = 1;
        r26 = java.lang.Integer.toString(r9);
        r24[r25] = r26;
        r25 = 2;
        r0 = r27;
        r0 = r0.fMinLength;
        r26 = r0;
        r26 = java.lang.Integer.toString(r26);
        r24[r25] = r26;
        r25 = 3;
        r0 = r27;
        r0 = r0.fTypeName;
        r26 = r0;
        r24[r25] = r26;
        r22.<init>(r23, r24);
        throw r22;
    L_0x00d2:
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r22 = r0;
        r22 = r22 & 1;
        if (r22 == 0) goto L_0x011a;
    L_0x00dc:
        r0 = r27;
        r0 = r0.fLength;
        r22 = r0;
        r0 = r22;
        if (r9 == r0) goto L_0x011a;
    L_0x00e6:
        r22 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r23 = "cvc-length-valid";
        r24 = 4;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r3;
        r25 = 1;
        r26 = java.lang.Integer.toString(r9);
        r24[r25] = r26;
        r25 = 2;
        r0 = r27;
        r0 = r0.fLength;
        r26 = r0;
        r26 = java.lang.Integer.toString(r26);
        r24[r25] = r26;
        r25 = 3;
        r0 = r27;
        r0 = r0.fTypeName;
        r26 = r0;
        r24[r25] = r26;
        r22.<init>(r23, r24);
        throw r22;
    L_0x011a:
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r22 = r0;
        r0 = r22;
        r0 = r0 & 2048;
        r22 = r0;
        if (r22 == 0) goto L_0x0220;
    L_0x0128:
        r11 = 0;
        r0 = r27;
        r5 = r0.fEnumerationSize;
        r0 = r27;
        r1 = r19;
        r14 = r0.convertToPrimitiveKind(r1);
        r6 = 0;
    L_0x0136:
        if (r6 < r5) goto L_0x0162;
    L_0x0138:
        if (r11 != 0) goto L_0x0220;
    L_0x013a:
        r16 = new java.lang.StringBuffer;
        r16.<init>();
        r0 = r27;
        r1 = r16;
        r0.appendEnumString(r1);
        r22 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r23 = "cvc-enumeration-valid";
        r24 = 2;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r3;
        r25 = 1;
        r26 = r16.toString();
        r24[r25] = r26;
        r22.<init>(r23, r24);
        throw r22;
    L_0x0162:
        r0 = r27;
        r0 = r0.fEnumeration;
        r22 = r0;
        r22 = r22[r6];
        r0 = r22;
        r0 = r0.actualValueType;
        r22 = r0;
        r0 = r27;
        r1 = r22;
        r15 = r0.convertToPrimitiveKind(r1);
        if (r14 == r15) goto L_0x0192;
    L_0x017a:
        r22 = 1;
        r0 = r22;
        if (r14 != r0) goto L_0x0186;
    L_0x0180:
        r22 = 2;
        r0 = r22;
        if (r15 == r0) goto L_0x0192;
    L_0x0186:
        r22 = 2;
        r0 = r22;
        if (r14 != r0) goto L_0x021c;
    L_0x018c:
        r22 = 1;
        r0 = r22;
        if (r15 != r0) goto L_0x021c;
    L_0x0192:
        r0 = r27;
        r0 = r0.fEnumeration;
        r22 = r0;
        r22 = r22[r6];
        r0 = r22;
        r0 = r0.actualValue;
        r22 = r0;
        r0 = r22;
        r22 = r0.equals(r10);
        if (r22 == 0) goto L_0x021c;
    L_0x01a8:
        r22 = 44;
        r0 = r22;
        if (r14 == r0) goto L_0x01b4;
    L_0x01ae:
        r22 = 43;
        r0 = r22;
        if (r14 != r0) goto L_0x0219;
    L_0x01b4:
        r0 = r27;
        r0 = r0.fEnumeration;
        r22 = r0;
        r22 = r22[r6];
        r0 = r22;
        r4 = r0.itemValueTypes;
        if (r7 == 0) goto L_0x01de;
    L_0x01c2:
        r20 = r7.getLength();
    L_0x01c6:
        if (r4 == 0) goto L_0x01e1;
    L_0x01c8:
        r21 = r4.getLength();
    L_0x01cc:
        r0 = r20;
        r1 = r21;
        if (r0 != r1) goto L_0x021c;
    L_0x01d2:
        r8 = 0;
    L_0x01d3:
        r0 = r20;
        if (r8 < r0) goto L_0x01e4;
    L_0x01d7:
        r0 = r20;
        if (r8 != r0) goto L_0x021c;
    L_0x01db:
        r11 = 1;
        goto L_0x0138;
    L_0x01de:
        r20 = 0;
        goto L_0x01c6;
    L_0x01e1:
        r21 = 0;
        goto L_0x01cc;
    L_0x01e4:
        r22 = r7.item(r8);
        r0 = r27;
        r1 = r22;
        r12 = r0.convertToPrimitiveKind(r1);
        r22 = r4.item(r8);
        r0 = r27;
        r1 = r22;
        r13 = r0.convertToPrimitiveKind(r1);
        if (r12 == r13) goto L_0x0216;
    L_0x01fe:
        r22 = 1;
        r0 = r22;
        if (r12 != r0) goto L_0x020a;
    L_0x0204:
        r22 = 2;
        r0 = r22;
        if (r13 == r0) goto L_0x0216;
    L_0x020a:
        r22 = 2;
        r0 = r22;
        if (r12 != r0) goto L_0x01d7;
    L_0x0210:
        r22 = 1;
        r0 = r22;
        if (r13 != r0) goto L_0x01d7;
    L_0x0216:
        r8 = r8 + 1;
        goto L_0x01d3;
    L_0x0219:
        r11 = 1;
        goto L_0x0138;
    L_0x021c:
        r6 = r6 + 1;
        goto L_0x0136;
    L_0x0220:
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r22 = r0;
        r0 = r22;
        r0 = r0 & 1024;
        r22 = r0;
        if (r22 == 0) goto L_0x0278;
    L_0x022e:
        r0 = r27;
        r0 = r0.fDVs;
        r22 = r0;
        r0 = r27;
        r0 = r0.fValidationDV;
        r23 = r0;
        r22 = r22[r23];
        r0 = r22;
        r17 = r0.getFractionDigits(r10);
        r0 = r27;
        r0 = r0.fFractionDigits;
        r22 = r0;
        r0 = r17;
        r1 = r22;
        if (r0 <= r1) goto L_0x0278;
    L_0x024e:
        r22 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r23 = "cvc-fractionDigits-valid";
        r24 = 3;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r3;
        r25 = 1;
        r26 = java.lang.Integer.toString(r17);
        r24[r25] = r26;
        r25 = 2;
        r0 = r27;
        r0 = r0.fFractionDigits;
        r26 = r0;
        r26 = java.lang.Integer.toString(r26);
        r24[r25] = r26;
        r22.<init>(r23, r24);
        throw r22;
    L_0x0278:
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r22 = r0;
        r0 = r22;
        r0 = r0 & 512;
        r22 = r0;
        if (r22 == 0) goto L_0x02d0;
    L_0x0286:
        r0 = r27;
        r0 = r0.fDVs;
        r22 = r0;
        r0 = r27;
        r0 = r0.fValidationDV;
        r23 = r0;
        r22 = r22[r23];
        r0 = r22;
        r18 = r0.getTotalDigits(r10);
        r0 = r27;
        r0 = r0.fTotalDigits;
        r22 = r0;
        r0 = r18;
        r1 = r22;
        if (r0 <= r1) goto L_0x02d0;
    L_0x02a6:
        r22 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r23 = "cvc-totalDigits-valid";
        r24 = 3;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r3;
        r25 = 1;
        r26 = java.lang.Integer.toString(r18);
        r24[r25] = r26;
        r25 = 2;
        r0 = r27;
        r0 = r0.fTotalDigits;
        r26 = r0;
        r26 = java.lang.Integer.toString(r26);
        r24[r25] = r26;
        r22.<init>(r23, r24);
        throw r22;
    L_0x02d0:
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r22 = r0;
        r22 = r22 & 32;
        if (r22 == 0) goto L_0x0326;
    L_0x02da:
        r0 = r27;
        r0 = r0.fDVs;
        r22 = r0;
        r0 = r27;
        r0 = r0.fValidationDV;
        r23 = r0;
        r22 = r22[r23];
        r0 = r27;
        r0 = r0.fMaxInclusive;
        r23 = r0;
        r0 = r22;
        r1 = r23;
        r2 = r0.compare(r10, r1);
        r22 = -1;
        r0 = r22;
        if (r2 == r0) goto L_0x0326;
    L_0x02fc:
        if (r2 == 0) goto L_0x0326;
    L_0x02fe:
        r22 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r23 = "cvc-maxInclusive-valid";
        r24 = 3;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r3;
        r25 = 1;
        r0 = r27;
        r0 = r0.fMaxInclusive;
        r26 = r0;
        r24[r25] = r26;
        r25 = 2;
        r0 = r27;
        r0 = r0.fTypeName;
        r26 = r0;
        r24[r25] = r26;
        r22.<init>(r23, r24);
        throw r22;
    L_0x0326:
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r22 = r0;
        r22 = r22 & 64;
        if (r22 == 0) goto L_0x037a;
    L_0x0330:
        r0 = r27;
        r0 = r0.fDVs;
        r22 = r0;
        r0 = r27;
        r0 = r0.fValidationDV;
        r23 = r0;
        r22 = r22[r23];
        r0 = r27;
        r0 = r0.fMaxExclusive;
        r23 = r0;
        r0 = r22;
        r1 = r23;
        r2 = r0.compare(r10, r1);
        r22 = -1;
        r0 = r22;
        if (r2 == r0) goto L_0x037a;
    L_0x0352:
        r22 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r23 = "cvc-maxExclusive-valid";
        r24 = 3;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r3;
        r25 = 1;
        r0 = r27;
        r0 = r0.fMaxExclusive;
        r26 = r0;
        r24[r25] = r26;
        r25 = 2;
        r0 = r27;
        r0 = r0.fTypeName;
        r26 = r0;
        r24[r25] = r26;
        r22.<init>(r23, r24);
        throw r22;
    L_0x037a:
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r22 = r0;
        r0 = r22;
        r0 = r0 & 256;
        r22 = r0;
        if (r22 == 0) goto L_0x03d4;
    L_0x0388:
        r0 = r27;
        r0 = r0.fDVs;
        r22 = r0;
        r0 = r27;
        r0 = r0.fValidationDV;
        r23 = r0;
        r22 = r22[r23];
        r0 = r27;
        r0 = r0.fMinInclusive;
        r23 = r0;
        r0 = r22;
        r1 = r23;
        r2 = r0.compare(r10, r1);
        r22 = 1;
        r0 = r22;
        if (r2 == r0) goto L_0x03d4;
    L_0x03aa:
        if (r2 == 0) goto L_0x03d4;
    L_0x03ac:
        r22 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r23 = "cvc-minInclusive-valid";
        r24 = 3;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r3;
        r25 = 1;
        r0 = r27;
        r0 = r0.fMinInclusive;
        r26 = r0;
        r24[r25] = r26;
        r25 = 2;
        r0 = r27;
        r0 = r0.fTypeName;
        r26 = r0;
        r24[r25] = r26;
        r22.<init>(r23, r24);
        throw r22;
    L_0x03d4:
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r22 = r0;
        r0 = r22;
        r0 = r0 & 128;
        r22 = r0;
        if (r22 == 0) goto L_0x042c;
    L_0x03e2:
        r0 = r27;
        r0 = r0.fDVs;
        r22 = r0;
        r0 = r27;
        r0 = r0.fValidationDV;
        r23 = r0;
        r22 = r22[r23];
        r0 = r27;
        r0 = r0.fMinExclusive;
        r23 = r0;
        r0 = r22;
        r1 = r23;
        r2 = r0.compare(r10, r1);
        r22 = 1;
        r0 = r22;
        if (r2 == r0) goto L_0x042c;
    L_0x0404:
        r22 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r23 = "cvc-minExclusive-valid";
        r24 = 3;
        r0 = r24;
        r0 = new java.lang.Object[r0];
        r24 = r0;
        r25 = 0;
        r24[r25] = r3;
        r25 = 1;
        r0 = r27;
        r0 = r0.fMinExclusive;
        r26 = r0;
        r24[r25] = r26;
        r25 = 2;
        r0 = r27;
        r0 = r0.fTypeName;
        r26 = r0;
        r24[r25] = r26;
        r22.<init>(r23, r24);
        throw r22;
    L_0x042c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl.checkFacets(mf.org.apache.xerces.impl.dv.ValidatedInfo):void");
    }

    private void checkExtraRules(ValidationContext context, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        ListData ob = validatedInfo.actualValue;
        if (this.fVariety == SPECIAL_PATTERN_NMTOKEN) {
            this.fDVs[this.fValidationDV].checkExtraRules(ob, context);
        } else if (this.fVariety == SPECIAL_PATTERN_NAME) {
            ListData values = ob;
            XSSimpleType memberType = validatedInfo.memberType;
            int len = values.getLength();
            try {
                int i;
                if (this.fItemType.fVariety == SPECIAL_PATTERN_NCNAME) {
                    XSSimpleTypeDecl[] memberTypes = validatedInfo.memberTypes;
                    for (i = len - 1; i >= 0; i--) {
                        validatedInfo.actualValue = values.item(i);
                        validatedInfo.memberType = memberTypes[i];
                        this.fItemType.checkExtraRules(context, validatedInfo);
                    }
                } else {
                    for (i = len - 1; i >= 0; i--) {
                        validatedInfo.actualValue = values.item(i);
                        this.fItemType.checkExtraRules(context, validatedInfo);
                    }
                }
                validatedInfo.actualValue = values;
                validatedInfo.memberType = memberType;
            } catch (Throwable th) {
                validatedInfo.actualValue = values;
                validatedInfo.memberType = memberType;
            }
        } else {
            ((XSSimpleTypeDecl) validatedInfo.memberType).checkExtraRules(context, validatedInfo);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object getActualValue(java.lang.Object r28, mf.org.apache.xerces.impl.dv.ValidationContext r29, mf.org.apache.xerces.impl.dv.ValidatedInfo r30, boolean r31) throws mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException {
        /*
        r27 = this;
        if (r31 == 0) goto L_0x007c;
    L_0x0002:
        r0 = r27;
        r0 = r0.fWhiteSpace;
        r21 = r0;
        r0 = r27;
        r1 = r28;
        r2 = r21;
        r15 = r0.normalize(r1, r2);
    L_0x0012:
        r0 = r27;
        r0 = r0.fFacetsDefined;
        r21 = r0;
        r21 = r21 & 8;
        if (r21 == 0) goto L_0x002a;
    L_0x001c:
        r0 = r27;
        r0 = r0.fPattern;
        r21 = r0;
        r21 = r21.size();
        r11 = r21 + -1;
    L_0x0028:
        if (r11 >= 0) goto L_0x0081;
    L_0x002a:
        r0 = r27;
        r0 = r0.fVariety;
        r21 = r0;
        r22 = 1;
        r0 = r21;
        r1 = r22;
        if (r0 != r1) goto L_0x0133;
    L_0x0038:
        r0 = r27;
        r0 = r0.fPatternType;
        r21 = r0;
        if (r21 == 0) goto L_0x0102;
    L_0x0040:
        r18 = 0;
        r0 = r27;
        r0 = r0.fPatternType;
        r21 = r0;
        r22 = 1;
        r0 = r21;
        r1 = r22;
        if (r0 != r1) goto L_0x00cc;
    L_0x0050:
        r21 = mf.org.apache.xerces.util.XMLChar.isValidNmtoken(r15);
        if (r21 == 0) goto L_0x00c9;
    L_0x0056:
        r18 = 0;
    L_0x0058:
        if (r18 == 0) goto L_0x0102;
    L_0x005a:
        r21 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r22 = "cvc-datatype-valid.1.2.1";
        r23 = 2;
        r0 = r23;
        r0 = new java.lang.Object[r0];
        r23 = r0;
        r24 = 0;
        r23[r24] = r15;
        r24 = 1;
        r25 = SPECIAL_PATTERN_STRING;
        r0 = r27;
        r0 = r0.fPatternType;
        r26 = r0;
        r25 = r25[r26];
        r23[r24] = r25;
        r21.<init>(r22, r23);
        throw r21;
    L_0x007c:
        r15 = r28.toString();
        goto L_0x0012;
    L_0x0081:
        r0 = r27;
        r0 = r0.fPattern;
        r21 = r0;
        r0 = r21;
        r17 = r0.elementAt(r11);
        r17 = (mf.org.apache.xerces.impl.xpath.regex.RegularExpression) r17;
        r0 = r17;
        r21 = r0.matches(r15);
        if (r21 != 0) goto L_0x00c5;
    L_0x0097:
        r21 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r22 = "cvc-pattern-valid";
        r23 = 3;
        r0 = r23;
        r0 = new java.lang.Object[r0];
        r23 = r0;
        r24 = 0;
        r23[r24] = r28;
        r24 = 1;
        r0 = r27;
        r0 = r0.fPatternStr;
        r25 = r0;
        r0 = r25;
        r25 = r0.elementAt(r11);
        r23[r24] = r25;
        r24 = 2;
        r0 = r27;
        r0 = r0.fTypeName;
        r25 = r0;
        r23[r24] = r25;
        r21.<init>(r22, r23);
        throw r21;
    L_0x00c5:
        r11 = r11 + -1;
        goto L_0x0028;
    L_0x00c9:
        r18 = 1;
        goto L_0x0058;
    L_0x00cc:
        r0 = r27;
        r0 = r0.fPatternType;
        r21 = r0;
        r22 = 2;
        r0 = r21;
        r1 = r22;
        if (r0 != r1) goto L_0x00e7;
    L_0x00da:
        r21 = mf.org.apache.xerces.util.XMLChar.isValidName(r15);
        if (r21 == 0) goto L_0x00e4;
    L_0x00e0:
        r18 = 0;
    L_0x00e2:
        goto L_0x0058;
    L_0x00e4:
        r18 = 1;
        goto L_0x00e2;
    L_0x00e7:
        r0 = r27;
        r0 = r0.fPatternType;
        r21 = r0;
        r22 = 3;
        r0 = r21;
        r1 = r22;
        if (r0 != r1) goto L_0x0058;
    L_0x00f5:
        r21 = mf.org.apache.xerces.util.XMLChar.isValidNCName(r15);
        if (r21 == 0) goto L_0x00ff;
    L_0x00fb:
        r18 = 0;
    L_0x00fd:
        goto L_0x0058;
    L_0x00ff:
        r18 = 1;
        goto L_0x00fd;
    L_0x0102:
        r0 = r30;
        r0.normalizedValue = r15;
        r0 = r27;
        r0 = r0.fDVs;
        r21 = r0;
        r0 = r27;
        r0 = r0.fValidationDV;
        r22 = r0;
        r21 = r21[r22];
        r0 = r21;
        r1 = r29;
        r7 = r0.getActualValue(r15, r1);
        r0 = r30;
        r0.actualValue = r7;
        r0 = r27;
        r0 = r0.fBuiltInKind;
        r21 = r0;
        r0 = r21;
        r1 = r30;
        r1.actualValueType = r0;
        r0 = r27;
        r1 = r30;
        r1.actualType = r0;
    L_0x0132:
        return r7;
    L_0x0133:
        r0 = r27;
        r0 = r0.fVariety;
        r21 = r0;
        r22 = 2;
        r0 = r21;
        r1 = r22;
        if (r0 != r1) goto L_0x023b;
    L_0x0141:
        r16 = new java.util.StringTokenizer;
        r21 = " ";
        r0 = r16;
        r1 = r21;
        r0.<init>(r15, r1);
        r8 = r16.countTokens();
        r7 = new java.lang.Object[r8];
        r0 = r27;
        r0 = r0.fItemType;
        r21 = r0;
        r21 = r21.getVariety();
        r22 = 3;
        r0 = r21;
        r1 = r22;
        if (r0 != r1) goto L_0x01c7;
    L_0x0164:
        r12 = 1;
    L_0x0165:
        if (r12 == 0) goto L_0x01c9;
    L_0x0167:
        r21 = r8;
    L_0x0169:
        r0 = r21;
        r13 = new short[r0];
        if (r12 != 0) goto L_0x017f;
    L_0x016f:
        r21 = 0;
        r0 = r27;
        r0 = r0.fItemType;
        r22 = r0;
        r0 = r22;
        r0 = r0.fBuiltInKind;
        r22 = r0;
        r13[r21] = r22;
    L_0x017f:
        r14 = new mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl[r8];
        r10 = 0;
    L_0x0182:
        if (r10 < r8) goto L_0x01cc;
    L_0x0184:
        r20 = new mf.org.apache.xerces.impl.dv.xs.ListDV$ListData;
        r0 = r20;
        r0.<init>(r7);
        r0 = r20;
        r1 = r30;
        r1.actualValue = r0;
        if (r12 == 0) goto L_0x0237;
    L_0x0193:
        r21 = 43;
    L_0x0195:
        r0 = r21;
        r1 = r30;
        r1.actualValueType = r0;
        r21 = 0;
        r0 = r21;
        r1 = r30;
        r1.memberType = r0;
        r0 = r30;
        r0.memberTypes = r14;
        r21 = new mf.org.apache.xerces.impl.xs.util.ShortListImpl;
        r0 = r13.length;
        r22 = r0;
        r0 = r21;
        r1 = r22;
        r0.<init>(r13, r1);
        r0 = r21;
        r1 = r30;
        r1.itemValueTypes = r0;
        r0 = r30;
        r0.normalizedValue = r15;
        r0 = r27;
        r1 = r30;
        r1.actualType = r0;
        r7 = r20;
        goto L_0x0132;
    L_0x01c7:
        r12 = 0;
        goto L_0x0165;
    L_0x01c9:
        r21 = 1;
        goto L_0x0169;
    L_0x01cc:
        r0 = r27;
        r0 = r0.fItemType;
        r21 = r0;
        r22 = r16.nextToken();
        r23 = 0;
        r0 = r21;
        r1 = r22;
        r2 = r29;
        r3 = r30;
        r4 = r23;
        r21 = r0.getActualValue(r1, r2, r3, r4);
        r7[r10] = r21;
        r21 = r29.needFacetChecking();
        if (r21 == 0) goto L_0x021d;
    L_0x01ee:
        r0 = r27;
        r0 = r0.fItemType;
        r21 = r0;
        r0 = r21;
        r0 = r0.fFacetsDefined;
        r21 = r0;
        if (r21 == 0) goto L_0x021d;
    L_0x01fc:
        r0 = r27;
        r0 = r0.fItemType;
        r21 = r0;
        r0 = r21;
        r0 = r0.fFacetsDefined;
        r21 = r0;
        r22 = 16;
        r0 = r21;
        r1 = r22;
        if (r0 == r1) goto L_0x021d;
    L_0x0210:
        r0 = r27;
        r0 = r0.fItemType;
        r21 = r0;
        r0 = r21;
        r1 = r30;
        r0.checkFacets(r1);
    L_0x021d:
        r0 = r30;
        r0 = r0.memberType;
        r21 = r0;
        r21 = (mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl) r21;
        r14[r10] = r21;
        if (r12 == 0) goto L_0x0233;
    L_0x0229:
        r21 = r14[r10];
        r0 = r21;
        r0 = r0.fBuiltInKind;
        r21 = r0;
        r13[r10] = r21;
    L_0x0233:
        r10 = r10 + 1;
        goto L_0x0182;
    L_0x0237:
        r21 = 44;
        goto L_0x0195;
    L_0x023b:
        r0 = r27;
        r0 = r0.fMemberTypes;
        r21 = r0;
        r0 = r21;
        r0 = r0.length;
        r21 = r0;
        r22 = 1;
        r0 = r21;
        r1 = r22;
        if (r0 <= r1) goto L_0x029f;
    L_0x024e:
        if (r28 == 0) goto L_0x029f;
    L_0x0250:
        r5 = r28.toString();
    L_0x0254:
        r10 = 0;
    L_0x0255:
        r0 = r27;
        r0 = r0.fMemberTypes;
        r21 = r0;
        r0 = r21;
        r0 = r0.length;
        r21 = r0;
        r0 = r21;
        if (r10 < r0) goto L_0x02a2;
    L_0x0264:
        r19 = new java.lang.StringBuffer;
        r19.<init>();
        r10 = 0;
    L_0x026a:
        r0 = r27;
        r0 = r0.fMemberTypes;
        r21 = r0;
        r0 = r21;
        r0 = r0.length;
        r21 = r0;
        r0 = r21;
        if (r10 < r0) goto L_0x030f;
    L_0x0279:
        r21 = new mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
        r22 = "cvc-datatype-valid.1.2.3";
        r23 = 3;
        r0 = r23;
        r0 = new java.lang.Object[r0];
        r23 = r0;
        r24 = 0;
        r23[r24] = r28;
        r24 = 1;
        r0 = r27;
        r0 = r0.fTypeName;
        r25 = r0;
        r23[r24] = r25;
        r24 = 2;
        r25 = r19.toString();
        r23[r24] = r25;
        r21.<init>(r22, r23);
        throw r21;
    L_0x029f:
        r5 = r28;
        goto L_0x0254;
    L_0x02a2:
        r0 = r27;
        r0 = r0.fMemberTypes;	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r21 = r0;
        r21 = r21[r10];	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r22 = 1;
        r0 = r21;
        r1 = r29;
        r2 = r30;
        r3 = r22;
        r6 = r0.getActualValue(r5, r1, r2, r3);	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r21 = r29.needFacetChecking();	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        if (r21 == 0) goto L_0x02f3;
    L_0x02be:
        r0 = r27;
        r0 = r0.fMemberTypes;	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r21 = r0;
        r21 = r21[r10];	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r0 = r21;
        r0 = r0.fFacetsDefined;	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r21 = r0;
        if (r21 == 0) goto L_0x02f3;
    L_0x02ce:
        r0 = r27;
        r0 = r0.fMemberTypes;	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r21 = r0;
        r21 = r21[r10];	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r0 = r21;
        r0 = r0.fFacetsDefined;	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r21 = r0;
        r22 = 16;
        r0 = r21;
        r1 = r22;
        if (r0 == r1) goto L_0x02f3;
    L_0x02e4:
        r0 = r27;
        r0 = r0.fMemberTypes;	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r21 = r0;
        r21 = r21[r10];	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r0 = r21;
        r1 = r30;
        r0.checkFacets(r1);	 Catch:{ InvalidDatatypeValueException -> 0x030a }
    L_0x02f3:
        r0 = r27;
        r0 = r0.fMemberTypes;	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r21 = r0;
        r21 = r21[r10];	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r0 = r21;
        r1 = r30;
        r1.memberType = r0;	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r0 = r27;
        r1 = r30;
        r1.actualType = r0;	 Catch:{ InvalidDatatypeValueException -> 0x030a }
        r7 = r6;
        goto L_0x0132;
    L_0x030a:
        r21 = move-exception;
        r10 = r10 + 1;
        goto L_0x0255;
    L_0x030f:
        if (r10 == 0) goto L_0x031a;
    L_0x0311:
        r21 = " | ";
        r0 = r19;
        r1 = r21;
        r0.append(r1);
    L_0x031a:
        r0 = r27;
        r0 = r0.fMemberTypes;
        r21 = r0;
        r9 = r21[r10];
        r0 = r9.fTargetNamespace;
        r21 = r0;
        if (r21 == 0) goto L_0x0345;
    L_0x0328:
        r21 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        r0 = r19;
        r1 = r21;
        r0.append(r1);
        r0 = r9.fTargetNamespace;
        r21 = r0;
        r0 = r19;
        r1 = r21;
        r0.append(r1);
        r21 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r0 = r19;
        r1 = r21;
        r0.append(r1);
    L_0x0345:
        r0 = r9.fTypeName;
        r21 = r0;
        r0 = r19;
        r1 = r21;
        r0.append(r1);
        r0 = r9.fEnumeration;
        r21 = r0;
        if (r21 == 0) goto L_0x0364;
    L_0x0356:
        r21 = " : ";
        r0 = r19;
        r1 = r21;
        r0.append(r1);
        r0 = r19;
        r9.appendEnumString(r0);
    L_0x0364:
        r10 = r10 + 1;
        goto L_0x026a;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl.getActualValue(java.lang.Object, mf.org.apache.xerces.impl.dv.ValidationContext, mf.org.apache.xerces.impl.dv.ValidatedInfo, boolean):java.lang.Object");
    }

    public boolean isEqual(Object value1, Object value2) {
        if (value1 == null) {
            return false;
        }
        return value1.equals(value2);
    }

    public boolean isIdentical(Object value1, Object value2) {
        if (value1 == null) {
            return false;
        }
        return this.fDVs[this.fValidationDV].isIdentical(value1, value2);
    }

    public static String normalize(String content, short ws) {
        int len = content == null ? DERIVATION_ANY : content.length();
        if (len == 0 || ws == (short) 0) {
            return content;
        }
        StringBuffer sb = new StringBuffer();
        int i;
        char ch;
        if (ws == SPECIAL_PATTERN_NMTOKEN) {
            for (i = DERIVATION_ANY; i < len; i += DERIVATION_RESTRICTION) {
                ch = content.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == CharUtils.CR) {
                    sb.append(' ');
                } else {
                    sb.append(ch);
                }
            }
        } else {
            boolean isLeading = true;
            i = DERIVATION_ANY;
            while (i < len) {
                ch = content.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == CharUtils.CR || ch == ' ') {
                    while (i < len - 1) {
                        ch = content.charAt(i + DERIVATION_RESTRICTION);
                        if (ch != '\t' && ch != '\n' && ch != CharUtils.CR && ch != ' ') {
                            break;
                        }
                        i += DERIVATION_RESTRICTION;
                    }
                    if (i < len - 1 && !isLeading) {
                        sb.append(' ');
                    }
                } else {
                    sb.append(ch);
                    isLeading = false;
                }
                i += DERIVATION_RESTRICTION;
            }
        }
        return sb.toString();
    }

    protected String normalize(Object content, short ws) {
        if (content == null) {
            return null;
        }
        if ((this.fFacetsDefined & DERIVATION_LIST) == 0) {
            short norm_type = fDVNormalizeType[this.fValidationDV];
            if (norm_type == (short) 0) {
                return content.toString();
            }
            if (norm_type == SPECIAL_PATTERN_NMTOKEN) {
                return XMLChar.trim(content.toString());
            }
        }
        if (!(content instanceof StringBuffer)) {
            return normalize(content.toString(), ws);
        }
        StringBuffer sb = (StringBuffer) content;
        int len = sb.length();
        if (len == 0) {
            return StringUtils.EMPTY;
        }
        if (ws == (short) 0) {
            return sb.toString();
        }
        int i;
        char ch;
        if (ws == SPECIAL_PATTERN_NMTOKEN) {
            for (i = DERIVATION_ANY; i < len; i += DERIVATION_RESTRICTION) {
                ch = sb.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == CharUtils.CR) {
                    sb.setCharAt(i, ' ');
                }
            }
        } else {
            boolean isLeading = true;
            i = DERIVATION_ANY;
            int j = DERIVATION_ANY;
            while (i < len) {
                int j2;
                ch = sb.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == CharUtils.CR || ch == ' ') {
                    while (i < len - 1) {
                        ch = sb.charAt(i + DERIVATION_RESTRICTION);
                        if (ch != '\t' && ch != '\n' && ch != CharUtils.CR && ch != ' ') {
                            break;
                        }
                        i += DERIVATION_RESTRICTION;
                    }
                    if (i >= len - 1 || isLeading) {
                        j2 = j;
                    } else {
                        j2 = j + DERIVATION_RESTRICTION;
                        sb.setCharAt(j, ' ');
                    }
                } else {
                    j2 = j + DERIVATION_RESTRICTION;
                    sb.setCharAt(j, ch);
                    isLeading = false;
                }
                i += DERIVATION_RESTRICTION;
                j = j2;
            }
            sb.setLength(j);
        }
        return sb.toString();
    }

    void reportError(String key, Object[] args) throws InvalidDatatypeFacetException {
        throw new InvalidDatatypeFacetException(key, args);
    }

    private String whiteSpaceValue(short ws) {
        return WS_FACET_STRING[ws];
    }

    public short getOrdered() {
        return this.fOrdered;
    }

    public boolean getBounded() {
        return this.fBounded;
    }

    public boolean getFinite() {
        return this.fFinite;
    }

    public boolean getNumeric() {
        return this.fNumeric;
    }

    public boolean isDefinedFacet(short facetName) {
        if (this.fValidationDV == (short) 0 || this.fValidationDV == DV_ANYATOMICTYPE) {
            return false;
        }
        if ((this.fFacetsDefined & facetName) != 0) {
            return true;
        }
        if (this.fPatternType != (short) 0) {
            if (facetName != DV_TIME) {
                return false;
            }
            return true;
        } else if (this.fValidationDV != DV_INTEGER) {
            return false;
        } else {
            if (facetName == DV_TIME || facetName == XSSimpleTypeDefinition.FACET_FRACTIONDIGITS) {
                return true;
            }
            return false;
        }
    }

    public short getDefinedFacets() {
        if (this.fValidationDV == (short) 0 || this.fValidationDV == DV_ANYATOMICTYPE) {
            return SPECIAL_PATTERN_NONE;
        }
        if (this.fPatternType != (short) 0) {
            return (short) (this.fFacetsDefined | DERIVATION_LIST);
        }
        if (this.fValidationDV == DV_INTEGER) {
            return (short) ((this.fFacetsDefined | DERIVATION_LIST) | NodeFilter.SHOW_DOCUMENT_FRAGMENT);
        }
        return this.fFacetsDefined;
    }

    public boolean isFixedFacet(short facetName) {
        if ((this.fFixedFacet & facetName) != 0) {
            return true;
        }
        if (this.fValidationDV != DV_INTEGER) {
            return false;
        }
        if (facetName != XSSimpleTypeDefinition.FACET_FRACTIONDIGITS) {
            return false;
        }
        return true;
    }

    public short getFixedFacets() {
        if (this.fValidationDV == DV_INTEGER) {
            return (short) (this.fFixedFacet | NodeFilter.SHOW_DOCUMENT_FRAGMENT);
        }
        return this.fFixedFacet;
    }

    public String getLexicalFacetValue(short facetName) {
        switch (facetName) {
            case DERIVATION_RESTRICTION /*1*/:
                if (this.fLength != -1) {
                    return Integer.toString(this.fLength);
                }
                return null;
            case DERIVATION_EXTENSION /*2*/:
                if (this.fMinLength != -1) {
                    return Integer.toString(this.fMinLength);
                }
                return null;
            case DERIVATION_UNION /*4*/:
                if (this.fMaxLength != -1) {
                    return Integer.toString(this.fMaxLength);
                }
                return null;
            case ConnectionResult.API_UNAVAILABLE /*16*/:
                if (this.fValidationDV == (short) 0 || this.fValidationDV == DV_ANYATOMICTYPE) {
                    return null;
                }
                return WS_FACET_STRING[this.fWhiteSpace];
            case XMLStringBuffer.DEFAULT_SIZE /*32*/:
                if (this.fMaxInclusive != null) {
                    return this.fMaxInclusive.toString();
                }
                return null;
            case XMLEntityManager.DEFAULT_XMLDECL_BUFFER_SIZE /*64*/:
                if (this.fMaxExclusive != null) {
                    return this.fMaxExclusive.toString();
                }
                return null;
            case TransportMediator.FLAG_KEY_MEDIA_NEXT /*128*/:
                if (this.fMinExclusive != null) {
                    return this.fMinExclusive.toString();
                }
                return null;
            case NodeFilter.SHOW_DOCUMENT /*256*/:
                if (this.fMinInclusive != null) {
                    return this.fMinInclusive.toString();
                }
                return null;
            case XMLEntityManager.DEFAULT_INTERNAL_BUFFER_SIZE /*512*/:
                if (this.fTotalDigits != -1) {
                    return Integer.toString(this.fTotalDigits);
                }
                return null;
            case NodeFilter.SHOW_DOCUMENT_FRAGMENT /*1024*/:
                if (this.fValidationDV == DV_INTEGER) {
                    return SchemaSymbols.ATTVAL_FALSE_0;
                }
                return this.fFractionDigits != -1 ? Integer.toString(this.fFractionDigits) : null;
            default:
                return null;
        }
    }

    public StringList getLexicalEnumeration() {
        if (this.fLexicalEnumeration == null) {
            if (this.fEnumeration == null) {
                return StringListImpl.EMPTY_LIST;
            }
            int size = this.fEnumerationSize;
            String[] strs = new String[size];
            for (int i = DERIVATION_ANY; i < size; i += DERIVATION_RESTRICTION) {
                strs[i] = this.fEnumeration[i].normalizedValue;
            }
            this.fLexicalEnumeration = new StringListImpl(strs, size);
        }
        return this.fLexicalEnumeration;
    }

    public ObjectList getActualEnumeration() {
        if (this.fActualEnumeration == null) {
            this.fActualEnumeration = new C13323();
        }
        return this.fActualEnumeration;
    }

    public ObjectList getEnumerationItemTypeList() {
        if (this.fEnumerationItemTypeList == null) {
            if (this.fEnumeration == null) {
                return null;
            }
            this.fEnumerationItemTypeList = new C13334();
        }
        return this.fEnumerationItemTypeList;
    }

    public ShortList getEnumerationTypeList() {
        if (this.fEnumerationTypeList == null) {
            if (this.fEnumeration == null) {
                return ShortListImpl.EMPTY_LIST;
            }
            short[] list = new short[this.fEnumerationSize];
            for (int i = DERIVATION_ANY; i < this.fEnumerationSize; i += DERIVATION_RESTRICTION) {
                list[i] = this.fEnumeration[i].actualValueType;
            }
            this.fEnumerationTypeList = new ShortListImpl(list, this.fEnumerationSize);
        }
        return this.fEnumerationTypeList;
    }

    public StringList getLexicalPattern() {
        if (this.fPatternType == (short) 0 && this.fValidationDV != DV_INTEGER && this.fPatternStr == null) {
            return StringListImpl.EMPTY_LIST;
        }
        if (this.fLexicalPattern == null) {
            String[] strs;
            int size = this.fPatternStr == null ? DERIVATION_ANY : this.fPatternStr.size();
            if (this.fPatternType == SPECIAL_PATTERN_NMTOKEN) {
                strs = new String[(size + DERIVATION_RESTRICTION)];
                strs[size] = "\\c+";
            } else if (this.fPatternType == SPECIAL_PATTERN_NAME) {
                strs = new String[(size + DERIVATION_RESTRICTION)];
                strs[size] = "\\i\\c*";
            } else if (this.fPatternType == SPECIAL_PATTERN_NCNAME) {
                strs = new String[(size + DERIVATION_EXTENSION)];
                strs[size] = "\\i\\c*";
                strs[size + DERIVATION_RESTRICTION] = "[\\i-[:]][\\c-[:]]*";
            } else if (this.fValidationDV == DV_INTEGER) {
                strs = new String[(size + DERIVATION_RESTRICTION)];
                strs[size] = "[\\-+]?[0-9]+";
            } else {
                strs = new String[size];
            }
            for (int i = DERIVATION_ANY; i < size; i += DERIVATION_RESTRICTION) {
                strs[i] = (String) this.fPatternStr.elementAt(i);
            }
            this.fLexicalPattern = new StringListImpl(strs, strs.length);
        }
        return this.fLexicalPattern;
    }

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

    private void calcFundamentalFacets() {
        setOrdered();
        setNumeric();
        setBounded();
        setCardinality();
    }

    private void setOrdered() {
        if (this.fVariety == SPECIAL_PATTERN_NMTOKEN) {
            this.fOrdered = this.fBase.fOrdered;
        } else if (this.fVariety == SPECIAL_PATTERN_NAME) {
            this.fOrdered = SPECIAL_PATTERN_NONE;
        } else if (this.fVariety != SPECIAL_PATTERN_NCNAME) {
        } else {
            if (this.fMemberTypes.length == 0) {
                this.fOrdered = SPECIAL_PATTERN_NMTOKEN;
                return;
            }
            boolean commonAnc;
            boolean allFalse;
            short ancestorId = getPrimitiveDV(this.fMemberTypes[DERIVATION_ANY].fValidationDV);
            if (ancestorId != (short) 0) {
                commonAnc = true;
            } else {
                commonAnc = false;
            }
            if (this.fMemberTypes[DERIVATION_ANY].fOrdered == (short) 0) {
                allFalse = true;
            } else {
                allFalse = false;
            }
            for (int i = DERIVATION_RESTRICTION; i < this.fMemberTypes.length && (commonAnc || allFalse); i += DERIVATION_RESTRICTION) {
                if (commonAnc) {
                    if (ancestorId == getPrimitiveDV(this.fMemberTypes[i].fValidationDV)) {
                        commonAnc = true;
                    } else {
                        commonAnc = false;
                    }
                }
                if (allFalse) {
                    if (this.fMemberTypes[i].fOrdered == (short) 0) {
                        allFalse = true;
                    } else {
                        allFalse = false;
                    }
                }
            }
            if (commonAnc) {
                this.fOrdered = this.fMemberTypes[DERIVATION_ANY].fOrdered;
            } else if (allFalse) {
                this.fOrdered = SPECIAL_PATTERN_NONE;
            } else {
                this.fOrdered = SPECIAL_PATTERN_NMTOKEN;
            }
        }
    }

    private void setNumeric() {
        if (this.fVariety == SPECIAL_PATTERN_NMTOKEN) {
            this.fNumeric = this.fBase.fNumeric;
        } else if (this.fVariety == SPECIAL_PATTERN_NAME) {
            this.fNumeric = false;
        } else if (this.fVariety == SPECIAL_PATTERN_NCNAME) {
            XSSimpleType[] memberTypes = this.fMemberTypes;
            int i = DERIVATION_ANY;
            while (i < memberTypes.length) {
                if (memberTypes[i].getNumeric()) {
                    i += DERIVATION_RESTRICTION;
                } else {
                    this.fNumeric = false;
                    return;
                }
            }
            this.fNumeric = true;
        }
    }

    private void setBounded() {
        if (this.fVariety == SPECIAL_PATTERN_NMTOKEN) {
            if (((this.fFacetsDefined & NodeFilter.SHOW_DOCUMENT) == 0 && (this.fFacetsDefined & TransportMediator.FLAG_KEY_MEDIA_NEXT) == 0) || ((this.fFacetsDefined & 32) == 0 && (this.fFacetsDefined & 64) == 0)) {
                this.fBounded = false;
            } else {
                this.fBounded = true;
            }
        } else if (this.fVariety == SPECIAL_PATTERN_NAME) {
            if ((this.fFacetsDefined & DERIVATION_RESTRICTION) == 0 && ((this.fFacetsDefined & DERIVATION_EXTENSION) == 0 || (this.fFacetsDefined & DERIVATION_UNION) == 0)) {
                this.fBounded = false;
            } else {
                this.fBounded = true;
            }
        } else if (this.fVariety == SPECIAL_PATTERN_NCNAME) {
            XSSimpleTypeDecl[] memberTypes = this.fMemberTypes;
            short ancestorId = SPECIAL_PATTERN_NONE;
            if (memberTypes.length > 0) {
                ancestorId = getPrimitiveDV(memberTypes[DERIVATION_ANY].fValidationDV);
            }
            int i = DERIVATION_ANY;
            while (i < memberTypes.length) {
                if (memberTypes[i].getBounded() && ancestorId == getPrimitiveDV(memberTypes[i].fValidationDV)) {
                    i += DERIVATION_RESTRICTION;
                } else {
                    this.fBounded = false;
                    return;
                }
            }
            this.fBounded = true;
        }
    }

    private boolean specialCardinalityCheck() {
        if (this.fBase.fValidationDV == DV_DATE || this.fBase.fValidationDV == DV_GYEARMONTH || this.fBase.fValidationDV == DV_GYEAR || this.fBase.fValidationDV == DV_GMONTHDAY || this.fBase.fValidationDV == DV_GDAY || this.fBase.fValidationDV == DV_GMONTH) {
            return true;
        }
        return false;
    }

    private void setCardinality() {
        if (this.fVariety == SPECIAL_PATTERN_NMTOKEN) {
            if (this.fBase.fFinite) {
                this.fFinite = true;
            } else if ((this.fFacetsDefined & DERIVATION_RESTRICTION) != 0 || (this.fFacetsDefined & DERIVATION_UNION) != 0 || (this.fFacetsDefined & XMLEntityManager.DEFAULT_INTERNAL_BUFFER_SIZE) != 0) {
                this.fFinite = true;
            } else if (((this.fFacetsDefined & NodeFilter.SHOW_DOCUMENT) == 0 && (this.fFacetsDefined & TransportMediator.FLAG_KEY_MEDIA_NEXT) == 0) || ((this.fFacetsDefined & 32) == 0 && (this.fFacetsDefined & 64) == 0)) {
                this.fFinite = false;
            } else if ((this.fFacetsDefined & NodeFilter.SHOW_DOCUMENT_FRAGMENT) != 0 || specialCardinalityCheck()) {
                this.fFinite = true;
            } else {
                this.fFinite = false;
            }
        } else if (this.fVariety == SPECIAL_PATTERN_NAME) {
            if ((this.fFacetsDefined & DERIVATION_RESTRICTION) == 0 && ((this.fFacetsDefined & DERIVATION_EXTENSION) == 0 || (this.fFacetsDefined & DERIVATION_UNION) == 0)) {
                this.fFinite = false;
            } else {
                this.fFinite = true;
            }
        } else if (this.fVariety == SPECIAL_PATTERN_NCNAME) {
            XSSimpleType[] memberTypes = this.fMemberTypes;
            int i = DERIVATION_ANY;
            while (i < memberTypes.length) {
                if (memberTypes[i].getFinite()) {
                    i += DERIVATION_RESTRICTION;
                } else {
                    this.fFinite = false;
                    return;
                }
            }
            this.fFinite = true;
        }
    }

    private short getPrimitiveDV(short validationDV) {
        if (validationDV == DV_ID || validationDV == DV_IDREF || validationDV == DV_ENTITY) {
            return SPECIAL_PATTERN_NMTOKEN;
        }
        if (validationDV == DV_INTEGER) {
            return SPECIAL_PATTERN_NCNAME;
        }
        return validationDV;
    }

    public boolean derivedFromType(XSTypeDefinition ancestor, short derivation) {
        if (ancestor == null) {
            return false;
        }
        while (ancestor instanceof XSSimpleTypeDelegate) {
            ancestor = ((XSSimpleTypeDelegate) ancestor).type;
        }
        if (ancestor.getBaseType() == ancestor) {
            return true;
        }
        XSTypeDefinition type = this;
        while (type != ancestor && type != fAnySimpleType) {
            type = type.getBaseType();
        }
        if (type == ancestor) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean derivedFrom(java.lang.String r5, java.lang.String r6, short r7) {
        /*
        r4 = this;
        r2 = 1;
        r1 = 0;
        if (r6 != 0) goto L_0x0005;
    L_0x0004:
        return r1;
    L_0x0005:
        r3 = "http://www.w3.org/2001/XMLSchema";
        r3 = r3.equals(r5);
        if (r3 == 0) goto L_0x0017;
    L_0x000d:
        r3 = "anyType";
        r3 = r3.equals(r6);
        if (r3 == 0) goto L_0x0017;
    L_0x0015:
        r1 = r2;
        goto L_0x0004;
    L_0x0017:
        r0 = r4;
    L_0x0018:
        r3 = r0.getName();
        r3 = r6.equals(r3);
        if (r3 == 0) goto L_0x0036;
    L_0x0022:
        if (r5 != 0) goto L_0x002a;
    L_0x0024:
        r3 = r0.getNamespace();
        if (r3 == 0) goto L_0x003a;
    L_0x002a:
        if (r5 == 0) goto L_0x0036;
    L_0x002c:
        r3 = r0.getNamespace();
        r3 = r5.equals(r3);
        if (r3 != 0) goto L_0x003a;
    L_0x0036:
        r3 = fAnySimpleType;
        if (r0 != r3) goto L_0x0040;
    L_0x003a:
        r3 = fAnySimpleType;
        if (r0 == r3) goto L_0x0004;
    L_0x003e:
        r1 = r2;
        goto L_0x0004;
    L_0x0040:
        r0 = r0.getBaseType();
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl.derivedFrom(java.lang.String, java.lang.String, short):boolean");
    }

    public boolean isDOMDerivedFrom(String ancestorNS, String ancestorName, int derivationMethod) {
        if (ancestorName == null) {
            return false;
        }
        if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(ancestorNS) && ANY_TYPE.equals(ancestorName) && ((derivationMethod & DERIVATION_RESTRICTION) != 0 || derivationMethod == 0)) {
            return true;
        }
        if ((derivationMethod & DERIVATION_RESTRICTION) != 0 && isDerivedByRestriction(ancestorNS, ancestorName, this)) {
            return true;
        }
        if ((derivationMethod & DERIVATION_LIST) != 0 && isDerivedByList(ancestorNS, ancestorName, this)) {
            return true;
        }
        if ((derivationMethod & DERIVATION_UNION) != 0 && isDerivedByUnion(ancestorNS, ancestorName, this)) {
            return true;
        }
        if (((derivationMethod & DERIVATION_EXTENSION) == 0 || (derivationMethod & DERIVATION_RESTRICTION) != 0 || (derivationMethod & DERIVATION_LIST) != 0 || (derivationMethod & DERIVATION_UNION) != 0) && (derivationMethod & DERIVATION_EXTENSION) == 0 && (derivationMethod & DERIVATION_RESTRICTION) == 0 && (derivationMethod & DERIVATION_LIST) == 0 && (derivationMethod & DERIVATION_UNION) == 0) {
            return isDerivedByAny(ancestorNS, ancestorName, this);
        }
        return false;
    }

    private boolean isDerivedByAny(String ancestorNS, String ancestorName, XSTypeDefinition type) {
        boolean derivedFrom = false;
        XSTypeDefinition oldType = null;
        while (type != null && type != oldType) {
            if (ancestorName.equals(type.getName()) && ((ancestorNS == null && type.getNamespace() == null) || (ancestorNS != null && ancestorNS.equals(type.getNamespace())))) {
                derivedFrom = true;
                break;
            } else if (isDerivedByRestriction(ancestorNS, ancestorName, type)) {
                return true;
            } else {
                if (isDerivedByList(ancestorNS, ancestorName, type)) {
                    return true;
                }
                if (isDerivedByUnion(ancestorNS, ancestorName, type)) {
                    return true;
                }
                oldType = type;
                if (((XSSimpleTypeDecl) type).getVariety() == (short) 0 || ((XSSimpleTypeDecl) type).getVariety() == SPECIAL_PATTERN_NMTOKEN) {
                    type = type.getBaseType();
                } else if (((XSSimpleTypeDecl) type).getVariety() == SPECIAL_PATTERN_NCNAME) {
                    if (DERIVATION_ANY < ((XSSimpleTypeDecl) type).getMemberTypes().getLength()) {
                        return isDerivedByAny(ancestorNS, ancestorName, (XSTypeDefinition) ((XSSimpleTypeDecl) type).getMemberTypes().item(DERIVATION_ANY));
                    }
                } else if (((XSSimpleTypeDecl) type).getVariety() == SPECIAL_PATTERN_NAME) {
                    type = ((XSSimpleTypeDecl) type).getItemType();
                }
            }
        }
        return derivedFrom;
    }

    private boolean isDerivedByRestriction(String ancestorNS, String ancestorName, XSTypeDefinition type) {
        XSTypeDefinition oldType = null;
        while (type != null && type != oldType) {
            if (ancestorName.equals(type.getName()) && ((ancestorNS != null && ancestorNS.equals(type.getNamespace())) || (type.getNamespace() == null && ancestorNS == null))) {
                return true;
            }
            oldType = type;
            type = type.getBaseType();
        }
        return false;
    }

    private boolean isDerivedByList(String ancestorNS, String ancestorName, XSTypeDefinition type) {
        if (type != null && ((XSSimpleTypeDefinition) type).getVariety() == SPECIAL_PATTERN_NAME) {
            XSTypeDefinition itemType = ((XSSimpleTypeDefinition) type).getItemType();
            if (itemType != null && isDerivedByRestriction(ancestorNS, ancestorName, itemType)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDerivedByUnion(String ancestorNS, String ancestorName, XSTypeDefinition type) {
        if (type != null && ((XSSimpleTypeDefinition) type).getVariety() == SPECIAL_PATTERN_NCNAME) {
            XSObjectList memberTypes = ((XSSimpleTypeDefinition) type).getMemberTypes();
            int i = DERIVATION_ANY;
            while (i < memberTypes.getLength()) {
                if (memberTypes.item(i) != null && isDerivedByRestriction(ancestorNS, ancestorName, (XSSimpleTypeDefinition) memberTypes.item(i))) {
                    return true;
                }
                i += DERIVATION_RESTRICTION;
            }
        }
        return false;
    }

    public void reset() {
        if (!this.fIsImmutable) {
            this.fItemType = null;
            this.fMemberTypes = null;
            this.fTypeName = null;
            this.fTargetNamespace = null;
            this.fFinalSet = SPECIAL_PATTERN_NONE;
            this.fBase = null;
            this.fVariety = (short) -1;
            this.fValidationDV = (short) -1;
            this.fFacetsDefined = SPECIAL_PATTERN_NONE;
            this.fFixedFacet = SPECIAL_PATTERN_NONE;
            this.fWhiteSpace = SPECIAL_PATTERN_NONE;
            this.fLength = -1;
            this.fMinLength = -1;
            this.fMaxLength = -1;
            this.fTotalDigits = -1;
            this.fFractionDigits = -1;
            this.fPattern = null;
            this.fPatternStr = null;
            this.fEnumeration = null;
            this.fLexicalPattern = null;
            this.fLexicalEnumeration = null;
            this.fActualEnumeration = null;
            this.fEnumerationTypeList = null;
            this.fEnumerationItemTypeList = null;
            this.fMaxInclusive = null;
            this.fMaxExclusive = null;
            this.fMinExclusive = null;
            this.fMinInclusive = null;
            this.lengthAnnotation = null;
            this.minLengthAnnotation = null;
            this.maxLengthAnnotation = null;
            this.whiteSpaceAnnotation = null;
            this.totalDigitsAnnotation = null;
            this.fractionDigitsAnnotation = null;
            this.patternAnnotations = null;
            this.enumerationAnnotations = null;
            this.maxInclusiveAnnotation = null;
            this.maxExclusiveAnnotation = null;
            this.minInclusiveAnnotation = null;
            this.minExclusiveAnnotation = null;
            this.fPatternType = SPECIAL_PATTERN_NONE;
            this.fAnnotations = null;
            this.fFacets = null;
        }
    }

    public XSNamespaceItem getNamespaceItem() {
        return this.fNamespaceItem;
    }

    public void setNamespaceItem(XSNamespaceItem namespaceItem) {
        this.fNamespaceItem = namespaceItem;
    }

    public String toString() {
        return this.fTargetNamespace + "," + this.fTypeName;
    }

    public XSObjectList getFacets() {
        if (this.fFacets == null && (this.fFacetsDefined != (short) 0 || this.fValidationDV == DV_INTEGER)) {
            XSFacetImpl[] facets = new XSFacetImpl[10];
            int count = DERIVATION_ANY;
            if (!((this.fFacetsDefined & 16) == 0 || this.fValidationDV == (short) 0 || this.fValidationDV == DV_ANYATOMICTYPE)) {
                facets[DERIVATION_ANY] = new XSFacetImpl(DV_BASE64BINARY, WS_FACET_STRING[this.fWhiteSpace], DERIVATION_ANY, null, (this.fFixedFacet & 16) != 0, this.whiteSpaceAnnotation);
                count = DERIVATION_ANY + DERIVATION_RESTRICTION;
            }
            if (this.fLength != -1) {
                facets[count] = new XSFacetImpl(SPECIAL_PATTERN_NMTOKEN, Integer.toString(this.fLength), this.fLength, null, (this.fFixedFacet & DERIVATION_RESTRICTION) != 0, this.lengthAnnotation);
                count += DERIVATION_RESTRICTION;
            }
            if (this.fMinLength != -1) {
                facets[count] = new XSFacetImpl(SPECIAL_PATTERN_NAME, Integer.toString(this.fMinLength), this.fMinLength, null, (this.fFixedFacet & DERIVATION_EXTENSION) != 0, this.minLengthAnnotation);
                count += DERIVATION_RESTRICTION;
            }
            if (this.fMaxLength != -1) {
                facets[count] = new XSFacetImpl(DV_FLOAT, Integer.toString(this.fMaxLength), this.fMaxLength, null, (this.fFixedFacet & DERIVATION_UNION) != 0, this.maxLengthAnnotation);
                count += DERIVATION_RESTRICTION;
            }
            if (this.fTotalDigits != -1) {
                facets[count] = new XSFacetImpl(XSSimpleTypeDefinition.FACET_TOTALDIGITS, Integer.toString(this.fTotalDigits), this.fTotalDigits, null, (this.fFixedFacet & XMLEntityManager.DEFAULT_INTERNAL_BUFFER_SIZE) != 0, this.totalDigitsAnnotation);
                count += DERIVATION_RESTRICTION;
            }
            if (this.fValidationDV == DV_INTEGER) {
                facets[count] = new XSFacetImpl(XSSimpleTypeDefinition.FACET_FRACTIONDIGITS, SchemaSymbols.ATTVAL_FALSE_0, DERIVATION_ANY, null, true, this.fractionDigitsAnnotation);
                count += DERIVATION_RESTRICTION;
            } else if (this.fFractionDigits != -1) {
                facets[count] = new XSFacetImpl(XSSimpleTypeDefinition.FACET_FRACTIONDIGITS, Integer.toString(this.fFractionDigits), this.fFractionDigits, null, (this.fFixedFacet & NodeFilter.SHOW_DOCUMENT_FRAGMENT) != 0, this.fractionDigitsAnnotation);
                count += DERIVATION_RESTRICTION;
            }
            if (this.fMaxInclusive != null) {
                facets[count] = new XSFacetImpl((short) 32, this.fMaxInclusive.toString(), DERIVATION_ANY, this.fMaxInclusive, (this.fFixedFacet & 32) != 0, this.maxInclusiveAnnotation);
                count += DERIVATION_RESTRICTION;
            }
            if (this.fMaxExclusive != null) {
                facets[count] = new XSFacetImpl((short) 64, this.fMaxExclusive.toString(), DERIVATION_ANY, this.fMaxExclusive, (this.fFixedFacet & 64) != 0, this.maxExclusiveAnnotation);
                count += DERIVATION_RESTRICTION;
            }
            if (this.fMinExclusive != null) {
                facets[count] = new XSFacetImpl(XSSimpleTypeDefinition.FACET_MINEXCLUSIVE, this.fMinExclusive.toString(), DERIVATION_ANY, this.fMinExclusive, (this.fFixedFacet & TransportMediator.FLAG_KEY_MEDIA_NEXT) != 0, this.minExclusiveAnnotation);
                count += DERIVATION_RESTRICTION;
            }
            if (this.fMinInclusive != null) {
                facets[count] = new XSFacetImpl(XSSimpleTypeDefinition.FACET_MININCLUSIVE, this.fMinInclusive.toString(), DERIVATION_ANY, this.fMinInclusive, (this.fFixedFacet & NodeFilter.SHOW_DOCUMENT) != 0, this.minInclusiveAnnotation);
                count += DERIVATION_RESTRICTION;
            }
            this.fFacets = count > 0 ? new XSObjectListImpl(facets, count) : XSObjectListImpl.EMPTY_LIST;
        }
        if (this.fFacets != null) {
            return this.fFacets;
        }
        return XSObjectListImpl.EMPTY_LIST;
    }

    public XSObject getFacet(int facetType) {
        XSObjectList list;
        int i;
        if (facetType == XMLEntityManager.DEFAULT_BUFFER_SIZE || facetType == DERIVATION_LIST) {
            list = getMultiValueFacets();
            for (i = DERIVATION_ANY; i < list.getLength(); i += DERIVATION_RESTRICTION) {
                XSMultiValueFacet f = (XSMultiValueFacet) list.item(i);
                if (f.getFacetKind() == facetType) {
                    return f;
                }
            }
        } else {
            list = getFacets();
            for (i = DERIVATION_ANY; i < list.getLength(); i += DERIVATION_RESTRICTION) {
                XSFacet f2 = (XSFacet) list.item(i);
                if (f2.getFacetKind() == facetType) {
                    return f2;
                }
            }
        }
        return null;
    }

    public XSObjectList getMultiValueFacets() {
        if (this.fMultiValueFacets == null && !((this.fFacetsDefined & XMLEntityManager.DEFAULT_BUFFER_SIZE) == 0 && (this.fFacetsDefined & DERIVATION_LIST) == 0 && this.fPatternType == (short) 0 && this.fValidationDV != DV_INTEGER)) {
            XSMVFacetImpl[] facets = new XSMVFacetImpl[DERIVATION_EXTENSION];
            int count = DERIVATION_ANY;
            if (!((this.fFacetsDefined & DERIVATION_LIST) == 0 && this.fPatternType == (short) 0 && this.fValidationDV != DV_INTEGER)) {
                facets[DERIVATION_ANY] = new XSMVFacetImpl(DV_TIME, getLexicalPattern(), null, this.patternAnnotations);
                count = DERIVATION_ANY + DERIVATION_RESTRICTION;
            }
            if (this.fEnumeration != null) {
                facets[count] = new XSMVFacetImpl(XSSimpleTypeDefinition.FACET_ENUMERATION, getLexicalEnumeration(), new ObjectListImpl(this.fEnumeration, this.fEnumerationSize), this.enumerationAnnotations);
                count += DERIVATION_RESTRICTION;
            }
            this.fMultiValueFacets = new XSObjectListImpl(facets, count);
        }
        return this.fMultiValueFacets != null ? this.fMultiValueFacets : XSObjectListImpl.EMPTY_LIST;
    }

    public Object getMinInclusiveValue() {
        return this.fMinInclusive;
    }

    public Object getMinExclusiveValue() {
        return this.fMinExclusive;
    }

    public Object getMaxInclusiveValue() {
        return this.fMaxInclusive;
    }

    public Object getMaxExclusiveValue() {
        return this.fMaxExclusive;
    }

    public void setAnonymous(boolean anon) {
        this.fAnonymous = anon;
    }

    public String getTypeNamespace() {
        return getNamespace();
    }

    public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
        return isDOMDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
    }

    private short convertToPrimitiveKind(short valueType) {
        if (valueType <= DV_NOTATION) {
            return valueType;
        }
        if (valueType <= DV_ANYATOMICTYPE) {
            return SPECIAL_PATTERN_NAME;
        }
        if (valueType <= (short) 42) {
            return DV_FLOAT;
        }
        return valueType;
    }

    private void appendEnumString(StringBuffer sb) {
        sb.append('[');
        for (int i = DERIVATION_ANY; i < this.fEnumerationSize; i += DERIVATION_RESTRICTION) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(this.fEnumeration[i].actualValue);
        }
        sb.append(']');
    }
}
