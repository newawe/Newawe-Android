package mf.org.apache.xerces.impl.xs.traversers;

import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.Vector;
import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaNamespaceSupport;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAttributeDecl;
import mf.org.apache.xerces.impl.xs.XSGrammarBucket;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.impl.xs.util.XIntPool;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xml.serialize.Method;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.Element;
import org.apache.commons.lang.CharUtils;

public class XSAttributeChecker {
    public static final int ATTIDX_ABSTRACT;
    public static final int ATTIDX_AFORMDEFAULT;
    public static final int ATTIDX_BASE;
    public static final int ATTIDX_BLOCK;
    public static final int ATTIDX_BLOCKDEFAULT;
    private static int ATTIDX_COUNT = 0;
    public static final int ATTIDX_DEFAULT;
    public static final int ATTIDX_EFORMDEFAULT;
    public static final int ATTIDX_ENUMNSDECLS;
    public static final int ATTIDX_FINAL;
    public static final int ATTIDX_FINALDEFAULT;
    public static final int ATTIDX_FIXED;
    public static final int ATTIDX_FORM;
    public static final int ATTIDX_FROMDEFAULT;
    public static final int ATTIDX_ID;
    public static final int ATTIDX_ISRETURNED;
    public static final int ATTIDX_ITEMTYPE;
    public static final int ATTIDX_MAXOCCURS;
    public static final int ATTIDX_MEMBERTYPES;
    public static final int ATTIDX_MINOCCURS;
    public static final int ATTIDX_MIXED;
    public static final int ATTIDX_NAME;
    public static final int ATTIDX_NAMESPACE;
    public static final int ATTIDX_NAMESPACE_LIST;
    public static final int ATTIDX_NILLABLE;
    public static final int ATTIDX_NONSCHEMA;
    public static final int ATTIDX_PROCESSCONTENTS;
    public static final int ATTIDX_PUBLIC;
    public static final int ATTIDX_REF;
    public static final int ATTIDX_REFER;
    public static final int ATTIDX_SCHEMALOCATION;
    public static final int ATTIDX_SOURCE;
    public static final int ATTIDX_SUBSGROUP;
    public static final int ATTIDX_SYSTEM;
    public static final int ATTIDX_TARGETNAMESPACE;
    public static final int ATTIDX_TYPE;
    public static final int ATTIDX_USE;
    public static final int ATTIDX_VALUE;
    public static final int ATTIDX_VERSION;
    public static final int ATTIDX_XML_LANG;
    public static final int ATTIDX_XPATH;
    private static final String ATTRIBUTE_N = "attribute_n";
    private static final String ATTRIBUTE_R = "attribute_r";
    protected static final int DT_ANYURI = 0;
    protected static final int DT_BLOCK = -1;
    protected static final int DT_BLOCK1 = -2;
    protected static final int DT_BOOLEAN = -15;
    protected static final int DT_COUNT = 9;
    protected static final int DT_FINAL = -3;
    protected static final int DT_FINAL1 = -4;
    protected static final int DT_FINAL2 = -5;
    protected static final int DT_FORM = -6;
    protected static final int DT_ID = 1;
    protected static final int DT_LANGUAGE = 8;
    protected static final int DT_MAXOCCURS = -7;
    protected static final int DT_MAXOCCURS1 = -8;
    protected static final int DT_MEMBERTYPES = -9;
    protected static final int DT_MINOCCURS1 = -10;
    protected static final int DT_NAMESPACE = -11;
    protected static final int DT_NCNAME = 5;
    protected static final int DT_NONNEGINT = -16;
    protected static final int DT_POSINT = -17;
    protected static final int DT_PROCESSCONTENTS = -12;
    protected static final int DT_QNAME = 2;
    protected static final int DT_STRING = 3;
    protected static final int DT_TOKEN = 4;
    protected static final int DT_USE = -13;
    protected static final int DT_WHITESPACE = -14;
    protected static final int DT_XPATH = 6;
    protected static final int DT_XPATH1 = 7;
    private static final String ELEMENT_N = "element_n";
    private static final String ELEMENT_R = "element_r";
    static final int INC_POOL_SIZE = 10;
    static final int INIT_POOL_SIZE = 10;
    private static final XInt INT_ANY_ANY;
    private static final XInt INT_ANY_LAX;
    private static final XInt INT_ANY_LIST;
    private static final XInt INT_ANY_NOT;
    private static final XInt INT_ANY_SKIP;
    private static final XInt INT_ANY_STRICT;
    private static final XInt INT_EMPTY_SET;
    private static final XInt INT_QUALIFIED;
    private static final XInt INT_UNBOUNDED;
    private static final XInt INT_UNQUALIFIED;
    private static final XInt INT_USE_OPTIONAL;
    private static final XInt INT_USE_PROHIBITED;
    private static final XInt INT_USE_REQUIRED;
    private static final XInt INT_WS_COLLAPSE;
    private static final XInt INT_WS_PRESERVE;
    private static final XInt INT_WS_REPLACE;
    private static final Hashtable fEleAttrsMapG;
    private static final Hashtable fEleAttrsMapL;
    private static final XSSimpleType[] fExtraDVs;
    private static boolean[] fSeenTemp;
    private static Object[] fTempArray;
    private static final XIntPool fXIntPool;
    Object[][] fArrayPool;
    protected Vector fNamespaceList;
    protected Hashtable fNonSchemaAttrs;
    int fPoolPos;
    protected XSDHandler fSchemaHandler;
    protected boolean[] fSeen;
    protected SymbolTable fSymbolTable;

    static {
        ATTIDX_COUNT = DT_ANYURI;
        int i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_ABSTRACT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_AFORMDEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_BASE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_BLOCK = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_BLOCKDEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_DEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_EFORMDEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_FINAL = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_FINALDEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_FIXED = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_FORM = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_ID = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_ITEMTYPE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_MAXOCCURS = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_MEMBERTYPES = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_MINOCCURS = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_MIXED = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_NAME = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_NAMESPACE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_NAMESPACE_LIST = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_NILLABLE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_NONSCHEMA = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_PROCESSCONTENTS = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_PUBLIC = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_REF = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_REFER = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_SCHEMALOCATION = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_SOURCE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_SUBSGROUP = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_SYSTEM = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_TARGETNAMESPACE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_TYPE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_USE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_VALUE = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_ENUMNSDECLS = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_VERSION = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_XML_LANG = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_XPATH = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_FROMDEFAULT = i;
        i = ATTIDX_COUNT;
        ATTIDX_COUNT = i + DT_ID;
        ATTIDX_ISRETURNED = i;
        fXIntPool = new XIntPool();
        INT_QUALIFIED = fXIntPool.getXInt(DT_ID);
        INT_UNQUALIFIED = fXIntPool.getXInt(DT_ANYURI);
        INT_EMPTY_SET = fXIntPool.getXInt(DT_ANYURI);
        INT_ANY_STRICT = fXIntPool.getXInt(DT_ID);
        INT_ANY_LAX = fXIntPool.getXInt(DT_STRING);
        INT_ANY_SKIP = fXIntPool.getXInt(DT_QNAME);
        INT_ANY_ANY = fXIntPool.getXInt(DT_ID);
        INT_ANY_LIST = fXIntPool.getXInt(DT_STRING);
        INT_ANY_NOT = fXIntPool.getXInt(DT_QNAME);
        INT_USE_OPTIONAL = fXIntPool.getXInt(DT_ANYURI);
        INT_USE_REQUIRED = fXIntPool.getXInt(DT_ID);
        INT_USE_PROHIBITED = fXIntPool.getXInt(DT_QNAME);
        INT_WS_PRESERVE = fXIntPool.getXInt(DT_ANYURI);
        INT_WS_REPLACE = fXIntPool.getXInt(DT_ID);
        INT_WS_COLLAPSE = fXIntPool.getXInt(DT_QNAME);
        INT_UNBOUNDED = fXIntPool.getXInt(DT_BLOCK);
        fEleAttrsMapG = new Hashtable(29);
        fEleAttrsMapL = new Hashtable(79);
        fExtraDVs = new XSSimpleType[DT_COUNT];
        SchemaGrammar grammar = SchemaGrammar.SG_SchemaNS;
        fExtraDVs[DT_ANYURI] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_ANYURI);
        fExtraDVs[DT_ID] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_ID);
        fExtraDVs[DT_QNAME] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_QNAME);
        fExtraDVs[DT_STRING] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_STRING);
        fExtraDVs[DT_TOKEN] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_TOKEN);
        fExtraDVs[DT_NCNAME] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_NCNAME);
        fExtraDVs[DT_XPATH] = fExtraDVs[DT_STRING];
        fExtraDVs[DT_XPATH] = fExtraDVs[DT_STRING];
        fExtraDVs[DT_LANGUAGE] = (XSSimpleType) grammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_LANGUAGE);
        int attCount = DT_ANYURI + DT_ID;
        int ATT_ABSTRACT_D = DT_ANYURI;
        int attCount2 = attCount + DT_ID;
        int ATT_ATTRIBUTE_FD_D = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_BASE_R = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_BASE_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_BLOCK_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_BLOCK1_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_BLOCK_D_D = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_DEFAULT_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_ELEMENT_FD_D = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_FINAL_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_FINAL1_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_FINAL_D_D = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_FIXED_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_FIXED_D = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_FORM_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_ID_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_ITEMTYPE_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_MAXOCCURS_D = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_MAXOCCURS1_D = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_MEMBER_T_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_MINOCCURS_D = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_MINOCCURS1_D = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_MIXED_D = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_MIXED_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_NAME_R = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_NAMESPACE_D = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_NAMESPACE_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_NILLABLE_D = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_PROCESS_C_D = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_PUBLIC_R = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_REF_R = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_REFER_R = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_SCHEMA_L_R = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_SCHEMA_L_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_SOURCE_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_SUBSTITUTION_G_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_SYSTEM_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_TARGET_N_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_TYPE_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_USE_D = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_VALUE_NNI_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_VALUE_PI_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_VALUE_STR_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_VALUE_WS_N = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_VERSION_N = attCount2;
        attCount2 = attCount + DT_ID;
        int ATT_XML_LANG = attCount;
        attCount = attCount2 + DT_ID;
        int ATT_XPATH_R = attCount2;
        int ATT_XPATH1_R = attCount;
        OneAttr[] allAttrs = new OneAttr[(attCount + DT_ID)];
        allAttrs[ATT_ABSTRACT_D] = new OneAttr(SchemaSymbols.ATT_ABSTRACT, DT_BOOLEAN, ATTIDX_ABSTRACT, Boolean.FALSE);
        allAttrs[ATT_ATTRIBUTE_FD_D] = new OneAttr(SchemaSymbols.ATT_ATTRIBUTEFORMDEFAULT, DT_FORM, ATTIDX_AFORMDEFAULT, INT_UNQUALIFIED);
        allAttrs[ATT_BASE_R] = new OneAttr(SchemaSymbols.ATT_BASE, DT_QNAME, ATTIDX_BASE, null);
        allAttrs[ATT_BASE_N] = new OneAttr(SchemaSymbols.ATT_BASE, DT_QNAME, ATTIDX_BASE, null);
        allAttrs[ATT_BLOCK_N] = new OneAttr(SchemaSymbols.ATT_BLOCK, DT_BLOCK, ATTIDX_BLOCK, null);
        allAttrs[ATT_BLOCK1_N] = new OneAttr(SchemaSymbols.ATT_BLOCK, DT_BLOCK1, ATTIDX_BLOCK, null);
        allAttrs[ATT_BLOCK_D_D] = new OneAttr(SchemaSymbols.ATT_BLOCKDEFAULT, DT_BLOCK, ATTIDX_BLOCKDEFAULT, INT_EMPTY_SET);
        allAttrs[ATT_DEFAULT_N] = new OneAttr(SchemaSymbols.ATT_DEFAULT, DT_STRING, ATTIDX_DEFAULT, null);
        allAttrs[ATT_ELEMENT_FD_D] = new OneAttr(SchemaSymbols.ATT_ELEMENTFORMDEFAULT, DT_FORM, ATTIDX_EFORMDEFAULT, INT_UNQUALIFIED);
        allAttrs[ATT_FINAL_N] = new OneAttr(SchemaSymbols.ATT_FINAL, DT_FINAL, ATTIDX_FINAL, null);
        allAttrs[ATT_FINAL1_N] = new OneAttr(SchemaSymbols.ATT_FINAL, DT_FINAL1, ATTIDX_FINAL, null);
        allAttrs[ATT_FINAL_D_D] = new OneAttr(SchemaSymbols.ATT_FINALDEFAULT, DT_FINAL2, ATTIDX_FINALDEFAULT, INT_EMPTY_SET);
        allAttrs[ATT_FIXED_N] = new OneAttr(SchemaSymbols.ATT_FIXED, DT_STRING, ATTIDX_FIXED, null);
        allAttrs[ATT_FIXED_D] = new OneAttr(SchemaSymbols.ATT_FIXED, DT_BOOLEAN, ATTIDX_FIXED, Boolean.FALSE);
        allAttrs[ATT_FORM_N] = new OneAttr(SchemaSymbols.ATT_FORM, DT_FORM, ATTIDX_FORM, null);
        allAttrs[ATT_ID_N] = new OneAttr(SchemaSymbols.ATT_ID, DT_ID, ATTIDX_ID, null);
        allAttrs[ATT_ITEMTYPE_N] = new OneAttr(SchemaSymbols.ATT_ITEMTYPE, DT_QNAME, ATTIDX_ITEMTYPE, null);
        allAttrs[ATT_MAXOCCURS_D] = new OneAttr(SchemaSymbols.ATT_MAXOCCURS, DT_MAXOCCURS, ATTIDX_MAXOCCURS, fXIntPool.getXInt(DT_ID));
        allAttrs[ATT_MAXOCCURS1_D] = new OneAttr(SchemaSymbols.ATT_MAXOCCURS, DT_MAXOCCURS1, ATTIDX_MAXOCCURS, fXIntPool.getXInt(DT_ID));
        allAttrs[ATT_MEMBER_T_N] = new OneAttr(SchemaSymbols.ATT_MEMBERTYPES, DT_MEMBERTYPES, ATTIDX_MEMBERTYPES, null);
        allAttrs[ATT_MINOCCURS_D] = new OneAttr(SchemaSymbols.ATT_MINOCCURS, DT_NONNEGINT, ATTIDX_MINOCCURS, fXIntPool.getXInt(DT_ID));
        allAttrs[ATT_MINOCCURS1_D] = new OneAttr(SchemaSymbols.ATT_MINOCCURS, DT_MINOCCURS1, ATTIDX_MINOCCURS, fXIntPool.getXInt(DT_ID));
        allAttrs[ATT_MIXED_D] = new OneAttr(SchemaSymbols.ATT_MIXED, DT_BOOLEAN, ATTIDX_MIXED, Boolean.FALSE);
        allAttrs[ATT_MIXED_N] = new OneAttr(SchemaSymbols.ATT_MIXED, DT_BOOLEAN, ATTIDX_MIXED, null);
        allAttrs[ATT_NAME_R] = new OneAttr(SchemaSymbols.ATT_NAME, DT_NCNAME, ATTIDX_NAME, null);
        allAttrs[ATT_NAMESPACE_D] = new OneAttr(SchemaSymbols.ATT_NAMESPACE, DT_NAMESPACE, ATTIDX_NAMESPACE, INT_ANY_ANY);
        allAttrs[ATT_NAMESPACE_N] = new OneAttr(SchemaSymbols.ATT_NAMESPACE, DT_ANYURI, ATTIDX_NAMESPACE, null);
        allAttrs[ATT_NILLABLE_D] = new OneAttr(SchemaSymbols.ATT_NILLABLE, DT_BOOLEAN, ATTIDX_NILLABLE, Boolean.FALSE);
        allAttrs[ATT_PROCESS_C_D] = new OneAttr(SchemaSymbols.ATT_PROCESSCONTENTS, DT_PROCESSCONTENTS, ATTIDX_PROCESSCONTENTS, INT_ANY_STRICT);
        allAttrs[ATT_PUBLIC_R] = new OneAttr(SchemaSymbols.ATT_PUBLIC, DT_TOKEN, ATTIDX_PUBLIC, null);
        allAttrs[ATT_REF_R] = new OneAttr(SchemaSymbols.ATT_REF, DT_QNAME, ATTIDX_REF, null);
        allAttrs[ATT_REFER_R] = new OneAttr(SchemaSymbols.ATT_REFER, DT_QNAME, ATTIDX_REFER, null);
        allAttrs[ATT_SCHEMA_L_R] = new OneAttr(SchemaSymbols.ATT_SCHEMALOCATION, DT_ANYURI, ATTIDX_SCHEMALOCATION, null);
        allAttrs[ATT_SCHEMA_L_N] = new OneAttr(SchemaSymbols.ATT_SCHEMALOCATION, DT_ANYURI, ATTIDX_SCHEMALOCATION, null);
        allAttrs[ATT_SOURCE_N] = new OneAttr(SchemaSymbols.ATT_SOURCE, DT_ANYURI, ATTIDX_SOURCE, null);
        allAttrs[ATT_SUBSTITUTION_G_N] = new OneAttr(SchemaSymbols.ATT_SUBSTITUTIONGROUP, DT_QNAME, ATTIDX_SUBSGROUP, null);
        allAttrs[ATT_SYSTEM_N] = new OneAttr(SchemaSymbols.ATT_SYSTEM, DT_ANYURI, ATTIDX_SYSTEM, null);
        allAttrs[ATT_TARGET_N_N] = new OneAttr(SchemaSymbols.ATT_TARGETNAMESPACE, DT_ANYURI, ATTIDX_TARGETNAMESPACE, null);
        allAttrs[ATT_TYPE_N] = new OneAttr(SchemaSymbols.ATT_TYPE, DT_QNAME, ATTIDX_TYPE, null);
        allAttrs[ATT_USE_D] = new OneAttr(SchemaSymbols.ATT_USE, DT_USE, ATTIDX_USE, INT_USE_OPTIONAL);
        allAttrs[ATT_VALUE_NNI_N] = new OneAttr(SchemaSymbols.ATT_VALUE, DT_NONNEGINT, ATTIDX_VALUE, null);
        allAttrs[ATT_VALUE_PI_N] = new OneAttr(SchemaSymbols.ATT_VALUE, DT_POSINT, ATTIDX_VALUE, null);
        allAttrs[ATT_VALUE_STR_N] = new OneAttr(SchemaSymbols.ATT_VALUE, DT_STRING, ATTIDX_VALUE, null);
        allAttrs[ATT_VALUE_WS_N] = new OneAttr(SchemaSymbols.ATT_VALUE, DT_WHITESPACE, ATTIDX_VALUE, null);
        allAttrs[ATT_VERSION_N] = new OneAttr(SchemaSymbols.ATT_VERSION, DT_TOKEN, ATTIDX_VERSION, null);
        allAttrs[ATT_XML_LANG] = new OneAttr(SchemaSymbols.ATT_XML_LANG, DT_LANGUAGE, ATTIDX_XML_LANG, null);
        allAttrs[ATT_XPATH_R] = new OneAttr(SchemaSymbols.ATT_XPATH, DT_XPATH, ATTIDX_XPATH, null);
        allAttrs[ATT_XPATH1_R] = new OneAttr(SchemaSymbols.ATT_XPATH, DT_XPATH1, ATTIDX_XPATH, null);
        Container attrList = Container.getContainer(DT_NCNAME);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_ATTRIBUTE, attrList);
        attrList = Container.getContainer(DT_XPATH1);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_FORM, allAttrs[ATT_FORM_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        attrList.put(SchemaSymbols.ATT_USE, allAttrs[ATT_USE_D]);
        fEleAttrsMapL.put(ATTRIBUTE_N, attrList);
        attrList = Container.getContainer(DT_NCNAME);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        attrList.put(SchemaSymbols.ATT_USE, allAttrs[ATT_USE_D]);
        fEleAttrsMapL.put(ATTRIBUTE_R, attrList);
        attrList = Container.getContainer(INIT_POOL_SIZE);
        attrList.put(SchemaSymbols.ATT_ABSTRACT, allAttrs[ATT_ABSTRACT_D]);
        attrList.put(SchemaSymbols.ATT_BLOCK, allAttrs[ATT_BLOCK_N]);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_NILLABLE, allAttrs[ATT_NILLABLE_D]);
        attrList.put(SchemaSymbols.ATT_SUBSTITUTIONGROUP, allAttrs[ATT_SUBSTITUTION_G_N]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_ELEMENT, attrList);
        attrList = Container.getContainer(INIT_POOL_SIZE);
        attrList.put(SchemaSymbols.ATT_BLOCK, allAttrs[ATT_BLOCK_N]);
        attrList.put(SchemaSymbols.ATT_DEFAULT, allAttrs[ATT_DEFAULT_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_N]);
        attrList.put(SchemaSymbols.ATT_FORM, allAttrs[ATT_FORM_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_NILLABLE, allAttrs[ATT_NILLABLE_D]);
        attrList.put(SchemaSymbols.ATT_TYPE, allAttrs[ATT_TYPE_N]);
        fEleAttrsMapL.put(ELEMENT_N, attrList);
        attrList = Container.getContainer(DT_TOKEN);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        fEleAttrsMapL.put(ELEMENT_R, attrList);
        attrList = Container.getContainer(DT_XPATH);
        attrList.put(SchemaSymbols.ATT_ABSTRACT, allAttrs[ATT_ABSTRACT_D]);
        attrList.put(SchemaSymbols.ATT_BLOCK, allAttrs[ATT_BLOCK1_N]);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MIXED, allAttrs[ATT_MIXED_D]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_COMPLEXTYPE, attrList);
        attrList = Container.getContainer(DT_TOKEN);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_PUBLIC, allAttrs[ATT_PUBLIC_R]);
        attrList.put(SchemaSymbols.ATT_SYSTEM, allAttrs[ATT_SYSTEM_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_NOTATION, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MIXED, allAttrs[ATT_MIXED_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_COMPLEXTYPE, attrList);
        attrList = Container.getContainer(DT_ID);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_SIMPLECONTENT, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_BASE, allAttrs[ATT_BASE_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_RESTRICTION, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_BASE, allAttrs[ATT_BASE_R]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_EXTENSION, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ATTRIBUTEGROUP, attrList);
        attrList = Container.getContainer(DT_STRING);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAMESPACE, allAttrs[ATT_NAMESPACE_D]);
        attrList.put(SchemaSymbols.ATT_PROCESSCONTENTS, allAttrs[ATT_PROCESS_C_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ANYATTRIBUTE, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MIXED, allAttrs[ATT_MIXED_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_COMPLEXCONTENT, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_ATTRIBUTEGROUP, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_GROUP, attrList);
        attrList = Container.getContainer(DT_TOKEN);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_REF, allAttrs[ATT_REF_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_GROUP, attrList);
        attrList = Container.getContainer(DT_STRING);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS1_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS1_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ALL, attrList);
        attrList = Container.getContainer(DT_STRING);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_CHOICE, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_SEQUENCE, attrList);
        attrList = Container.getContainer(DT_NCNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MAXOCCURS, allAttrs[ATT_MAXOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_MINOCCURS, allAttrs[ATT_MINOCCURS_D]);
        attrList.put(SchemaSymbols.ATT_NAMESPACE, allAttrs[ATT_NAMESPACE_D]);
        attrList.put(SchemaSymbols.ATT_PROCESSCONTENTS, allAttrs[ATT_PROCESS_C_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ANY, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_UNIQUE, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_KEY, attrList);
        attrList = Container.getContainer(DT_STRING);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        attrList.put(SchemaSymbols.ATT_REFER, allAttrs[ATT_REFER_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_KEYREF, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_XPATH, allAttrs[ATT_XPATH_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_SELECTOR, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_XPATH, allAttrs[ATT_XPATH1_R]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_FIELD, attrList);
        attrList = Container.getContainer(DT_ID);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_ANNOTATION, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ANNOTATION, attrList);
        attrList = Container.getContainer(DT_ID);
        attrList.put(SchemaSymbols.ATT_SOURCE, allAttrs[ATT_SOURCE_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_APPINFO, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_APPINFO, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_SOURCE, allAttrs[ATT_SOURCE_N]);
        attrList.put(SchemaSymbols.ATT_XML_LANG, allAttrs[ATT_XML_LANG]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_DOCUMENTATION, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_DOCUMENTATION, attrList);
        attrList = Container.getContainer(DT_STRING);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL1_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAME, allAttrs[ATT_NAME_R]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_SIMPLETYPE, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_FINAL, allAttrs[ATT_FINAL1_N]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_SIMPLETYPE, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_ITEMTYPE, allAttrs[ATT_ITEMTYPE_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_LIST, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_MEMBERTYPES, allAttrs[ATT_MEMBER_T_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_UNION, attrList);
        attrList = Container.getContainer(DT_LANGUAGE);
        attrList.put(SchemaSymbols.ATT_ATTRIBUTEFORMDEFAULT, allAttrs[ATT_ATTRIBUTE_FD_D]);
        attrList.put(SchemaSymbols.ATT_BLOCKDEFAULT, allAttrs[ATT_BLOCK_D_D]);
        attrList.put(SchemaSymbols.ATT_ELEMENTFORMDEFAULT, allAttrs[ATT_ELEMENT_FD_D]);
        attrList.put(SchemaSymbols.ATT_FINALDEFAULT, allAttrs[ATT_FINAL_D_D]);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_TARGETNAMESPACE, allAttrs[ATT_TARGET_N_N]);
        attrList.put(SchemaSymbols.ATT_VERSION, allAttrs[ATT_VERSION_N]);
        attrList.put(SchemaSymbols.ATT_XML_LANG, allAttrs[ATT_XML_LANG]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_SCHEMA, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_SCHEMALOCATION, allAttrs[ATT_SCHEMA_L_R]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_INCLUDE, attrList);
        fEleAttrsMapG.put(SchemaSymbols.ELT_REDEFINE, attrList);
        attrList = Container.getContainer(DT_STRING);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_NAMESPACE, allAttrs[ATT_NAMESPACE_N]);
        attrList.put(SchemaSymbols.ATT_SCHEMALOCATION, allAttrs[ATT_SCHEMA_L_N]);
        fEleAttrsMapG.put(SchemaSymbols.ELT_IMPORT, attrList);
        attrList = Container.getContainer(DT_STRING);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_NNI_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_LENGTH, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MINLENGTH, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MAXLENGTH, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_FRACTIONDIGITS, attrList);
        attrList = Container.getContainer(DT_STRING);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_PI_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_TOTALDIGITS, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_STR_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_PATTERN, attrList);
        attrList = Container.getContainer(DT_QNAME);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_STR_N]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_ENUMERATION, attrList);
        attrList = Container.getContainer(DT_STRING);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_WS_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_WHITESPACE, attrList);
        attrList = Container.getContainer(DT_STRING);
        attrList.put(SchemaSymbols.ATT_ID, allAttrs[ATT_ID_N]);
        attrList.put(SchemaSymbols.ATT_VALUE, allAttrs[ATT_VALUE_STR_N]);
        attrList.put(SchemaSymbols.ATT_FIXED, allAttrs[ATT_FIXED_D]);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MAXINCLUSIVE, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MAXEXCLUSIVE, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MININCLUSIVE, attrList);
        fEleAttrsMapL.put(SchemaSymbols.ELT_MINEXCLUSIVE, attrList);
        fSeenTemp = new boolean[ATTIDX_COUNT];
        fTempArray = new Object[ATTIDX_COUNT];
    }

    public XSAttributeChecker(XSDHandler schemaHandler) {
        this.fSchemaHandler = null;
        this.fSymbolTable = null;
        this.fNonSchemaAttrs = new Hashtable();
        this.fNamespaceList = new Vector();
        this.fSeen = new boolean[ATTIDX_COUNT];
        this.fArrayPool = (Object[][]) Array.newInstance(Object.class, new int[]{INIT_POOL_SIZE, ATTIDX_COUNT});
        this.fPoolPos = DT_ANYURI;
        this.fSchemaHandler = schemaHandler;
    }

    public void reset(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
        this.fNonSchemaAttrs.clear();
    }

    public Object[] checkAttributes(Element element, boolean isGlobal, XSDocumentInfo schemaDoc) {
        return checkAttributes(element, isGlobal, schemaDoc, false);
    }

    public Object[] checkAttributes(Element element, boolean isGlobal, XSDocumentInfo schemaDoc, boolean enumAsQName) {
        if (element == null) {
            return null;
        }
        Attr[] attrs = DOMUtil.getAttrs(element);
        resolveNamespace(element, attrs, schemaDoc.fNamespaceSupport);
        String uri = DOMUtil.getNamespaceURI(element);
        String elName = DOMUtil.getLocalName(element);
        if (!SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(uri)) {
            Object[] objArr = new Object[DT_ID];
            objArr[DT_ANYURI] = elName;
            reportSchemaError("s4s-elt-schema-ns", objArr, element);
        }
        Hashtable eleAttrsMap = fEleAttrsMapG;
        String lookupName = elName;
        if (!isGlobal) {
            eleAttrsMap = fEleAttrsMapL;
            if (elName.equals(SchemaSymbols.ELT_ELEMENT)) {
                lookupName = DOMUtil.getAttr(element, SchemaSymbols.ATT_REF) != null ? ELEMENT_R : ELEMENT_N;
            } else if (elName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                lookupName = DOMUtil.getAttr(element, SchemaSymbols.ATT_REF) != null ? ATTRIBUTE_R : ATTRIBUTE_N;
            }
        }
        Container attrList = (Container) eleAttrsMap.get(lookupName);
        if (attrList == null) {
            objArr = new Object[DT_ID];
            objArr[DT_ANYURI] = elName;
            reportSchemaError("s4s-elt-invalid", objArr, element);
            return null;
        }
        Object[] attrValues = getAvailableArray();
        long fromDefault = 0;
        System.arraycopy(fSeenTemp, DT_ANYURI, this.fSeen, DT_ANYURI, ATTIDX_COUNT);
        int length = attrs.length;
        int i = DT_ANYURI;
        while (i < length) {
            OneAttr oneAttr;
            Attr sattr = attrs[i];
            String attrName = sattr.getName();
            String attrURI = DOMUtil.getNamespaceURI(sattr);
            String attrVal = DOMUtil.getValue(sattr);
            if (attrName.startsWith(Method.XML)) {
                if (!(XMLConstants.XMLNS_ATTRIBUTE.equals(DOMUtil.getPrefix(sattr)) || XMLConstants.XMLNS_ATTRIBUTE.equals(attrName))) {
                    if (SchemaSymbols.ATT_XML_LANG.equals(attrName) && (SchemaSymbols.ELT_SCHEMA.equals(elName) || SchemaSymbols.ELT_DOCUMENTATION.equals(elName))) {
                        attrURI = null;
                    }
                }
                i += DT_ID;
            }
            if (attrURI == null || attrURI.length() == 0) {
                oneAttr = attrList.get(attrName);
                if (oneAttr == null) {
                    objArr = new Object[DT_QNAME];
                    objArr[DT_ANYURI] = elName;
                    objArr[DT_ID] = attrName;
                    reportSchemaError("s4s-att-not-allowed", objArr, element);
                } else {
                    this.fSeen[oneAttr.valueIndex] = true;
                    try {
                        if (oneAttr.dvIndex < 0) {
                            attrValues[oneAttr.valueIndex] = validate(attrValues, attrName, attrVal, oneAttr.dvIndex, schemaDoc);
                            attrValues[ATTIDX_ENUMNSDECLS] = new SchemaNamespaceSupport(schemaDoc.fNamespaceSupport);
                        } else if (oneAttr.dvIndex == DT_STRING || oneAttr.dvIndex == DT_XPATH || oneAttr.dvIndex == DT_XPATH1) {
                            attrValues[oneAttr.valueIndex] = attrVal;
                            if (elName.equals(SchemaSymbols.ELT_ENUMERATION) && enumAsQName) {
                                attrValues[ATTIDX_ENUMNSDECLS] = new SchemaNamespaceSupport(schemaDoc.fNamespaceSupport);
                            }
                        } else {
                            Object avalue = fExtraDVs[oneAttr.dvIndex].validate(attrVal, schemaDoc.fValidationContext, null);
                            if (oneAttr.dvIndex == DT_QNAME) {
                                QName qname = (QName) avalue;
                                if (qname.prefix == XMLSymbols.EMPTY_STRING && qname.uri == null && schemaDoc.fIsChameleonSchema) {
                                    qname.uri = schemaDoc.fTargetNamespace;
                                }
                            }
                            attrValues[oneAttr.valueIndex] = avalue;
                            attrValues[ATTIDX_ENUMNSDECLS] = new SchemaNamespaceSupport(schemaDoc.fNamespaceSupport);
                        }
                    } catch (InvalidDatatypeValueException ide) {
                        objArr = new Object[DT_STRING];
                        objArr[DT_ANYURI] = elName;
                        objArr[DT_ID] = attrName;
                        objArr[DT_QNAME] = ide.getMessage();
                        reportSchemaError("s4s-att-invalid-value", objArr, element);
                        if (oneAttr.dfltValue != null) {
                            attrValues[oneAttr.valueIndex] = oneAttr.dfltValue;
                        }
                    }
                }
                i += DT_ID;
            } else {
                if (attrURI.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA)) {
                    objArr = new Object[DT_QNAME];
                    objArr[DT_ANYURI] = elName;
                    objArr[DT_ID] = attrName;
                    reportSchemaError("s4s-att-not-allowed", objArr, element);
                } else {
                    if (attrValues[ATTIDX_NONSCHEMA] == null) {
                        attrValues[ATTIDX_NONSCHEMA] = new Vector(DT_TOKEN, DT_QNAME);
                    }
                    ((Vector) attrValues[ATTIDX_NONSCHEMA]).addElement(attrName);
                    ((Vector) attrValues[ATTIDX_NONSCHEMA]).addElement(attrVal);
                }
                i += DT_ID;
            }
        }
        OneAttr[] reqAttrs = attrList.values;
        for (i = DT_ANYURI; i < reqAttrs.length; i += DT_ID) {
            oneAttr = reqAttrs[i];
            if (!(oneAttr.dfltValue == null || this.fSeen[oneAttr.valueIndex])) {
                attrValues[oneAttr.valueIndex] = oneAttr.dfltValue;
                fromDefault |= (long) (DT_ID << oneAttr.valueIndex);
            }
        }
        attrValues[ATTIDX_FROMDEFAULT] = new Long(fromDefault);
        if (attrValues[ATTIDX_MAXOCCURS] == null) {
            return attrValues;
        }
        int min = ((XInt) attrValues[ATTIDX_MINOCCURS]).intValue();
        int max = ((XInt) attrValues[ATTIDX_MAXOCCURS]).intValue();
        if (max == DT_BLOCK || min <= max) {
            return attrValues;
        }
        objArr = new Object[DT_STRING];
        objArr[DT_ANYURI] = elName;
        objArr[DT_ID] = attrValues[ATTIDX_MINOCCURS];
        objArr[DT_QNAME] = attrValues[ATTIDX_MAXOCCURS];
        reportSchemaError("p-props-correct.2.1", objArr, element);
        attrValues[ATTIDX_MINOCCURS] = attrValues[ATTIDX_MAXOCCURS];
        return attrValues;
    }

    private Object validate(Object[] attrValues, String attr, String ivalue, int dvIndex, XSDocumentInfo schemaDoc) throws InvalidDatatypeValueException {
        if (ivalue == null) {
            return null;
        }
        String value = XMLChar.trim(ivalue);
        Object retValue;
        Object[] objArr;
        String token;
        StringTokenizer t;
        int choice;
        switch (dvIndex) {
            case DT_POSINT /*-17*/:
                try {
                    if (value.length() > 0 && value.charAt(DT_ANYURI) == '+') {
                        value = value.substring(DT_ID);
                    }
                    retValue = fXIntPool.getXInt(Integer.parseInt(value));
                    if (((XInt) retValue).intValue() > 0) {
                        return retValue;
                    }
                    objArr = new Object[DT_QNAME];
                    objArr[DT_ANYURI] = value;
                    objArr[DT_ID] = SchemaSymbols.ATTVAL_POSITIVEINTEGER;
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", objArr);
                } catch (NumberFormatException e) {
                    objArr = new Object[DT_QNAME];
                    objArr[DT_ANYURI] = value;
                    objArr[DT_ID] = SchemaSymbols.ATTVAL_POSITIVEINTEGER;
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", objArr);
                }
            case DT_NONNEGINT /*-16*/:
                try {
                    if (value.length() > 0 && value.charAt(DT_ANYURI) == '+') {
                        value = value.substring(DT_ID);
                    }
                    retValue = fXIntPool.getXInt(Integer.parseInt(value));
                    if (((XInt) retValue).intValue() >= 0) {
                        return retValue;
                    }
                    objArr = new Object[DT_QNAME];
                    objArr[DT_ANYURI] = value;
                    objArr[DT_ID] = SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER;
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", objArr);
                } catch (NumberFormatException e2) {
                    objArr = new Object[DT_QNAME];
                    objArr[DT_ANYURI] = value;
                    objArr[DT_ID] = SchemaSymbols.ATTVAL_NONNEGATIVEINTEGER;
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", objArr);
                }
            case DT_BOOLEAN /*-15*/:
                if (value.equals(SchemaSymbols.ATTVAL_FALSE) || value.equals(SchemaSymbols.ATTVAL_FALSE_0)) {
                    return Boolean.FALSE;
                }
                if (value.equals(SchemaSymbols.ATTVAL_TRUE) || value.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                    return Boolean.TRUE;
                }
                objArr = new Object[DT_QNAME];
                objArr[DT_ANYURI] = value;
                objArr[DT_ID] = SchemaSymbols.ATTVAL_BOOLEAN;
                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", objArr);
            case DT_WHITESPACE /*-14*/:
                if (value.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
                    return INT_WS_PRESERVE;
                }
                if (value.equals(SchemaSymbols.ATTVAL_REPLACE)) {
                    return INT_WS_REPLACE;
                }
                if (value.equals(SchemaSymbols.ATTVAL_COLLAPSE)) {
                    return INT_WS_COLLAPSE;
                }
                objArr = new Object[DT_QNAME];
                objArr[DT_ANYURI] = value;
                objArr[DT_ID] = "(preserve | replace | collapse)";
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", objArr);
            case DT_USE /*-13*/:
                if (value.equals(SchemaSymbols.ATTVAL_OPTIONAL)) {
                    return INT_USE_OPTIONAL;
                }
                if (value.equals(SchemaSymbols.ATTVAL_REQUIRED)) {
                    return INT_USE_REQUIRED;
                }
                if (value.equals(SchemaSymbols.ATTVAL_PROHIBITED)) {
                    return INT_USE_PROHIBITED;
                }
                objArr = new Object[DT_QNAME];
                objArr[DT_ANYURI] = value;
                objArr[DT_ID] = "(optional | prohibited | required)";
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", objArr);
            case DT_PROCESSCONTENTS /*-12*/:
                if (value.equals(SchemaSymbols.ATTVAL_STRICT)) {
                    return INT_ANY_STRICT;
                }
                if (value.equals(SchemaSymbols.ATTVAL_LAX)) {
                    return INT_ANY_LAX;
                }
                if (value.equals(SchemaSymbols.ATTVAL_SKIP)) {
                    return INT_ANY_SKIP;
                }
                objArr = new Object[DT_QNAME];
                objArr[DT_ANYURI] = value;
                objArr[DT_ID] = "(lax | skip | strict)";
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", objArr);
            case DT_NAMESPACE /*-11*/:
                if (value.equals(SchemaSymbols.ATTVAL_TWOPOUNDANY)) {
                    return INT_ANY_ANY;
                }
                String[] list;
                if (value.equals(SchemaSymbols.ATTVAL_TWOPOUNDOTHER)) {
                    retValue = INT_ANY_NOT;
                    list = new String[DT_QNAME];
                    list[DT_ANYURI] = schemaDoc.fTargetNamespace;
                    list[DT_ID] = null;
                    attrValues[ATTIDX_NAMESPACE_LIST] = list;
                    return retValue;
                }
                retValue = INT_ANY_LIST;
                this.fNamespaceList.removeAllElements();
                StringTokenizer stringTokenizer = new StringTokenizer(value, " \n\t\r");
                while (stringTokenizer.hasMoreTokens()) {
                    try {
                        String tempNamespace;
                        token = stringTokenizer.nextToken();
                        if (token.equals(SchemaSymbols.ATTVAL_TWOPOUNDLOCAL)) {
                            tempNamespace = null;
                        } else {
                            if (token.equals(SchemaSymbols.ATTVAL_TWOPOUNDTARGETNS)) {
                                tempNamespace = schemaDoc.fTargetNamespace;
                            } else {
                                fExtraDVs[DT_ANYURI].validate(token, schemaDoc.fValidationContext, null);
                                tempNamespace = this.fSymbolTable.addSymbol(token);
                            }
                        }
                        if (!this.fNamespaceList.contains(tempNamespace)) {
                            this.fNamespaceList.addElement(tempNamespace);
                        }
                    } catch (InvalidDatatypeValueException e3) {
                        objArr = new Object[DT_QNAME];
                        objArr[DT_ANYURI] = value;
                        objArr[DT_ID] = "((##any | ##other) | List of (anyURI | (##targetNamespace | ##local)) )";
                        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", objArr);
                    }
                }
                list = new String[this.fNamespaceList.size()];
                this.fNamespaceList.copyInto(list);
                attrValues[ATTIDX_NAMESPACE_LIST] = list;
                return retValue;
            case DT_MINOCCURS1 /*-10*/:
                if (value.equals(SchemaSymbols.ATTVAL_FALSE_0)) {
                    return fXIntPool.getXInt(DT_ANYURI);
                }
                if (value.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                    return fXIntPool.getXInt(DT_ID);
                }
                objArr = new Object[DT_QNAME];
                objArr[DT_ANYURI] = value;
                objArr[DT_ID] = "(0 | 1)";
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", objArr);
            case DT_MEMBERTYPES /*-9*/:
                Vector memberType = new Vector();
                try {
                    t = new StringTokenizer(value, " \n\t\r");
                    while (t.hasMoreTokens()) {
                        token = t.nextToken();
                        QName qname = (QName) fExtraDVs[DT_QNAME].validate(token, schemaDoc.fValidationContext, null);
                        if (qname.prefix == XMLSymbols.EMPTY_STRING && qname.uri == null && schemaDoc.fIsChameleonSchema) {
                            qname.uri = schemaDoc.fTargetNamespace;
                        }
                        memberType.addElement(qname);
                    }
                    return memberType;
                } catch (InvalidDatatypeValueException e4) {
                    objArr = new Object[DT_QNAME];
                    objArr[DT_ANYURI] = value;
                    objArr[DT_ID] = "(List of QName)";
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.2", objArr);
                }
            case DT_MAXOCCURS1 /*-8*/:
                if (value.equals(SchemaSymbols.ATTVAL_TRUE_1)) {
                    return fXIntPool.getXInt(DT_ID);
                }
                objArr = new Object[DT_QNAME];
                objArr[DT_ANYURI] = value;
                objArr[DT_ID] = "(1)";
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", objArr);
            case DT_MAXOCCURS /*-7*/:
                if (value.equals(SchemaSymbols.ATTVAL_UNBOUNDED)) {
                    return INT_UNBOUNDED;
                }
                try {
                    return validate(attrValues, attr, value, DT_NONNEGINT, schemaDoc);
                } catch (NumberFormatException e5) {
                    objArr = new Object[DT_QNAME];
                    objArr[DT_ANYURI] = value;
                    objArr[DT_ID] = "(nonNegativeInteger | unbounded)";
                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", objArr);
                }
            case DT_FORM /*-6*/:
                if (value.equals(SchemaSymbols.ATTVAL_QUALIFIED)) {
                    return INT_QUALIFIED;
                }
                if (value.equals(SchemaSymbols.ATTVAL_UNQUALIFIED)) {
                    return INT_UNQUALIFIED;
                }
                objArr = new Object[DT_QNAME];
                objArr[DT_ANYURI] = value;
                objArr[DT_ID] = "(qualified | unqualified)";
                throw new InvalidDatatypeValueException("cvc-enumeration-valid", objArr);
            case DT_FINAL2 /*-5*/:
                choice = DT_ANYURI;
                if (value.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    choice = 31;
                } else {
                    t = new StringTokenizer(value, " \n\t\r");
                    while (t.hasMoreTokens()) {
                        token = t.nextToken();
                        if (token.equals(SchemaSymbols.ATTVAL_EXTENSION)) {
                            choice |= DT_ID;
                        } else {
                            if (token.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                                choice |= DT_QNAME;
                            } else {
                                if (token.equals(SchemaSymbols.ATTVAL_LIST)) {
                                    choice |= 16;
                                } else {
                                    if (token.equals(SchemaSymbols.ATTVAL_UNION)) {
                                        choice |= DT_LANGUAGE;
                                    } else {
                                        objArr = new Object[DT_QNAME];
                                        objArr[DT_ANYURI] = value;
                                        objArr[DT_ID] = "(#all | List of (extension | restriction | list | union))";
                                        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", objArr);
                                    }
                                }
                            }
                        }
                    }
                }
                return fXIntPool.getXInt(choice);
            case DT_FINAL1 /*-4*/:
                choice = DT_ANYURI;
                if (value.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    choice = 31;
                } else {
                    t = new StringTokenizer(value, " \n\t\r");
                    while (t.hasMoreTokens()) {
                        token = t.nextToken();
                        if (token.equals(SchemaSymbols.ATTVAL_LIST)) {
                            choice |= 16;
                        } else {
                            if (token.equals(SchemaSymbols.ATTVAL_UNION)) {
                                choice |= DT_LANGUAGE;
                            } else {
                                if (token.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                                    choice |= DT_QNAME;
                                } else {
                                    objArr = new Object[DT_QNAME];
                                    objArr[DT_ANYURI] = value;
                                    objArr[DT_ID] = "(#all | List of (list | union | restriction))";
                                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", objArr);
                                }
                            }
                        }
                    }
                }
                return fXIntPool.getXInt(choice);
            case DT_FINAL /*-3*/:
            case DT_BLOCK1 /*-2*/:
                choice = DT_ANYURI;
                if (value.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    choice = 31;
                } else {
                    t = new StringTokenizer(value, " \n\t\r");
                    while (t.hasMoreTokens()) {
                        token = t.nextToken();
                        if (token.equals(SchemaSymbols.ATTVAL_EXTENSION)) {
                            choice |= DT_ID;
                        } else {
                            if (token.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                                choice |= DT_QNAME;
                            } else {
                                objArr = new Object[DT_QNAME];
                                objArr[DT_ANYURI] = value;
                                objArr[DT_ID] = "(#all | List of (extension | restriction))";
                                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", objArr);
                            }
                        }
                    }
                }
                return fXIntPool.getXInt(choice);
            case DT_BLOCK /*-1*/:
                choice = DT_ANYURI;
                if (value.equals(SchemaSymbols.ATTVAL_POUNDALL)) {
                    choice = 31;
                } else {
                    t = new StringTokenizer(value, " \n\t\r");
                    while (t.hasMoreTokens()) {
                        token = t.nextToken();
                        if (token.equals(SchemaSymbols.ATTVAL_EXTENSION)) {
                            choice |= DT_ID;
                        } else {
                            if (token.equals(SchemaSymbols.ATTVAL_RESTRICTION)) {
                                choice |= DT_QNAME;
                            } else {
                                if (token.equals(SchemaSymbols.ATTVAL_SUBSTITUTION)) {
                                    choice |= DT_TOKEN;
                                } else {
                                    objArr = new Object[DT_QNAME];
                                    objArr[DT_ANYURI] = value;
                                    objArr[DT_ID] = "(#all | List of (extension | restriction | substitution))";
                                    throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.3", objArr);
                                }
                            }
                        }
                    }
                }
                return fXIntPool.getXInt(choice);
            default:
                return null;
        }
    }

    void reportSchemaError(String key, Object[] args, Element ele) {
        this.fSchemaHandler.reportSchemaError(key, args, ele);
    }

    public void checkNonSchemaAttributes(XSGrammarBucket grammarBucket) {
        for (Entry entry : this.fNonSchemaAttrs.entrySet()) {
            String attrRName = (String) entry.getKey();
            String attrURI = attrRName.substring(DT_ANYURI, attrRName.indexOf(44));
            String attrLocal = attrRName.substring(attrRName.indexOf(44) + DT_ID);
            SchemaGrammar sGrammar = grammarBucket.getGrammar(attrURI);
            if (sGrammar != null) {
                XSAttributeDecl attrDecl = sGrammar.getGlobalAttributeDecl(attrLocal);
                if (attrDecl != null) {
                    XSSimpleType dv = (XSSimpleType) attrDecl.getTypeDefinition();
                    if (dv != null) {
                        Vector values = (Vector) entry.getValue();
                        String attrName = (String) values.elementAt(DT_ANYURI);
                        int count = values.size();
                        for (int i = DT_ID; i < count; i += DT_QNAME) {
                            String elName = (String) values.elementAt(i);
                            try {
                                dv.validate((String) values.elementAt(i + DT_ID), null, null);
                            } catch (InvalidDatatypeValueException ide) {
                                String[] strArr = new Object[DT_STRING];
                                strArr[DT_ANYURI] = elName;
                                strArr[DT_ID] = attrName;
                                strArr[DT_QNAME] = ide.getMessage();
                                reportSchemaError("s4s-att-invalid-value", strArr, null);
                            }
                        }
                    }
                }
            }
        }
    }

    public static String normalize(String content, short ws) {
        int len = content == null ? DT_ANYURI : content.length();
        if (len == 0 || ws == (short) 0) {
            return content;
        }
        StringBuffer sb = new StringBuffer();
        int i;
        char ch;
        if (ws == (short) 1) {
            for (i = DT_ANYURI; i < len; i += DT_ID) {
                ch = content.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == CharUtils.CR) {
                    sb.append(' ');
                } else {
                    sb.append(ch);
                }
            }
        } else {
            boolean isLeading = true;
            i = DT_ANYURI;
            while (i < len) {
                ch = content.charAt(i);
                if (ch == '\t' || ch == '\n' || ch == CharUtils.CR || ch == ' ') {
                    while (i < len + DT_BLOCK) {
                        ch = content.charAt(i + DT_ID);
                        if (ch != '\t' && ch != '\n' && ch != CharUtils.CR && ch != ' ') {
                            break;
                        }
                        i += DT_ID;
                    }
                    if (i < len + DT_BLOCK && !isLeading) {
                        sb.append(' ');
                    }
                } else {
                    sb.append(ch);
                    isLeading = false;
                }
                i += DT_ID;
            }
        }
        return sb.toString();
    }

    protected Object[] getAvailableArray() {
        if (this.fArrayPool.length == this.fPoolPos) {
            this.fArrayPool = new Object[(this.fPoolPos + INIT_POOL_SIZE)][];
            for (int i = this.fPoolPos; i < this.fArrayPool.length; i += DT_ID) {
                this.fArrayPool[i] = new Object[ATTIDX_COUNT];
            }
        }
        Object[] retArray = this.fArrayPool[this.fPoolPos];
        Object[][] objArr = this.fArrayPool;
        int i2 = this.fPoolPos;
        this.fPoolPos = i2 + DT_ID;
        objArr[i2] = null;
        System.arraycopy(fTempArray, DT_ANYURI, retArray, DT_ANYURI, ATTIDX_COUNT + DT_BLOCK);
        retArray[ATTIDX_ISRETURNED] = Boolean.FALSE;
        return retArray;
    }

    public void returnAttrArray(Object[] attrArray, XSDocumentInfo schemaDoc) {
        if (schemaDoc != null) {
            schemaDoc.fNamespaceSupport.popContext();
        }
        if (this.fPoolPos != 0 && attrArray != null && attrArray.length == ATTIDX_COUNT && !((Boolean) attrArray[ATTIDX_ISRETURNED]).booleanValue()) {
            attrArray[ATTIDX_ISRETURNED] = Boolean.TRUE;
            if (attrArray[ATTIDX_NONSCHEMA] != null) {
                ((Vector) attrArray[ATTIDX_NONSCHEMA]).clear();
            }
            Object[][] objArr = this.fArrayPool;
            int i = this.fPoolPos + DT_BLOCK;
            this.fPoolPos = i;
            objArr[i] = attrArray;
        }
    }

    public void resolveNamespace(Element element, Attr[] attrs, SchemaNamespaceSupport nsSupport) {
        nsSupport.pushContext();
        int length = attrs.length;
        for (int i = DT_ANYURI; i < length; i += DT_ID) {
            Attr sattr = attrs[i];
            String rawname = DOMUtil.getName(sattr);
            String prefix = null;
            if (rawname.equals(XMLSymbols.PREFIX_XMLNS)) {
                prefix = XMLSymbols.EMPTY_STRING;
            } else if (rawname.startsWith("xmlns:")) {
                prefix = this.fSymbolTable.addSymbol(DOMUtil.getLocalName(sattr));
            }
            if (prefix != null) {
                String uri = this.fSymbolTable.addSymbol(DOMUtil.getValue(sattr));
                if (uri.length() == 0) {
                    uri = null;
                }
                nsSupport.declarePrefix(prefix, uri);
            }
        }
    }
}
